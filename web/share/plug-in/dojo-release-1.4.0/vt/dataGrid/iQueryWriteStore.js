/*
 * @Author duongtb
 */
/**
 * Author: DungDV
 * Description: chuyen thong bao Error sang Alert trong _setValueOrValues cho 2 loi:
 * Grid resize theo Dialog, edit cot ID cua Grid.
 * Date: 18/06/2011
 * FWVersion: 3.3
 **/
dojo.provide("vt.dataGrid.iQueryWriteStore");
dojo.require("dojo.string");
dojo.require("dojo.data.util.sorter");
dojo.require("dojo.data.ItemFileWriteStore");


dojo.declare("vt.dataGrid.iQueryWriteStore",dojo.data.ItemFileWriteStore,{

    url:"",

    requestMethod:"post",
    // need to set in query, ex: query:{name:'*', formId:''}
    formId:"",

    // purpose to fix bug when rowsPerPage has been changed
    _count:-1,

    _sortInfo:null,

    // identify if loading completely
    counter:0,

    grid:null,
    // increase when a new item has been added
    numOfNewRows:0,

    param:null,

    // mark the status of the checkbox, type of value is boolean
    chkPropName:"checked",

    // decide when to reload the grid
    vtReloading: false,

    // callback parameter in vtReload
    vtCallback:null,
    //useCache:false,

    // We use the name in the errors, once the name is fixed hardcode it, may be.
    _className:"vt.dataGrid.iQueryWriteStore",

    // This will contain the items we have loaded from the server.
    // The contents of this array is optimized to satisfy all read-api requirements
    // and for using lesser storage, so the keys and their content need some explaination:
    // 		this._items[0].i - the item itself
    //		this._items[0].r - a reference to the store, so we can identify the item
    //			securly. We set this reference right after receiving the item from the
    //			server.
    _items:[],

    _arrayOfTopLevelItems:[],

    _arrayOfAllItems:[],

    // Store the last query that triggered xhr request to the server.
    // So we can compare if the request changed and if we shall reload
    // (this also depends on other factors, such as is caching used, etc).
    _lastServerQuery:null,

    // Store how many rows we have so that we can pass it to a clientPaging handler
    _numRows:-1,

    // Store a hash of the last server request. Actually I introduced this
    // for testing, so I can check if no unnecessary requests were issued for
    // client-side-paging.
    lastRequestHash:null,

    // summary:
    //		By default every request for paging is sent to the server.
    doClientPaging:false,

    // summary:
    //		By default all the sorting is done serverside before the data is returned
    //		which is the proper place to be doing it for really large datasets.
    doClientSorting:false,

    // Items by identify for Identify API
    _itemsByIdentity:null,
    

    // Identifier used
    _identifier:null,

    _features: {
        //'dojo.data.api.Read':true,
        'dojo.data.api.Identity':true, // _canEdit
        'dojo.data.api.Write':true,
        'dojo.data.api.Notification':true
    },

    _labelAttr: "label",
    
    fetch:function(/* Object? */ request){
       
        //	summary:
        //		See dojo.data.util.simpleFetch.fetch() this is just a copy and I adjusted
        //		only the paging, since it happens on the server if doClientPaging is
        //		false, thx to http://trac.dojotoolkit.org/ticket/4761 reporting this.
        //		Would be nice to be able to use simpleFetch() to reduce copied code,
        //		but i dont know how yet. Ideas please!
        //              serverQuery:{start:,count:,sort:} load dynamically from server
        if (request.query == undefined){
            request.query = {name:'*'};
        }
        request.start = request.start || 0;
        request.serverQuery = {
            q:request.query.name,
            start:request.start,
            count:request.count
         };
        if(this.formId == null && request.query.formId !== undefined){ 
            this.formId = request.query.formId;
        }
        request = request || {};
        if(!request.store){
            request.store = this;
        }
        var self = this;

        var _errorHandler = function(errorData, requestObject){
            if(requestObject.onError){
                var scope = requestObject.scope || dojo.global;
                requestObject.onError.call(scope, errorData, requestObject);
            }
        };

        var _fetchHandler = function(items, requestObject, numRows){
            var oldAbortFunction = requestObject.abort || null;
            var aborted = false;

            var startIndex = requestObject.start?requestObject.start:0;
            if(self.doClientPaging == false){
                // For client paging we dont need no slicing of the result.
                startIndex = 0;
            }
            var endIndex = requestObject.count?(startIndex + requestObject.count):items.length;

            requestObject.abort = function(){
                aborted = true;
                if(oldAbortFunction){
                    oldAbortFunction.call(requestObject);
                }
            };

            var scope = requestObject.scope || dojo.global;
            if(!requestObject.store){
                requestObject.store = self;
            }
            if(requestObject.onBegin){
                requestObject.onBegin.call(scope, numRows, requestObject); // numRows: paging
            }
            if(requestObject.sort && self.doClientSorting){
                
                items.sort(dojo.data.util.sorter.createSortFunction(requestObject.sort, self));
            }
            if(requestObject.onItem){
                for(var i = startIndex; (i < items.length) && (i < endIndex); ++i){
                    var item = items[i];
                    if(!aborted){
                        requestObject.onItem.call(scope, item, requestObject);
                    }
                }
            }
            if(requestObject.onComplete && !aborted){
                var subset = null;
                if(!requestObject.onItem){
                    subset = items.slice(startIndex, endIndex);
                }
                requestObject.onComplete.call(scope, subset, requestObject);
            }
        };
         
        //this.grid.showMessage(this.grid.loadingMessage);
        sd.operator.displayWaitScreen(true); 
        this._fetchItems(request, _fetchHandler, _errorHandler);
        return request;	// Object
    },

    getFeatures: function(){
        return this._features;
    },

    _filterResponse: function(data){
        //	summary:
        //		If the data from servers needs to be processed before it can be processed by this
        //		store, then this function should be re-implemented in subclass. This default
        //		implementation just return the data unchanged.
        //	data:
        //		The data received from server
        return data;
    },


    _fetchItems: function(/* Object */ request, /* Function */ fetchHandler, /* Function */ errorHandler){
        //	summary:
        //		See dojo.data.util.simpleFetch.fetch()
        //	summary:
        // 		The request contains the data as defined in the Read-API.
        // 		Additionally there is following keyword "serverQuery".
        //
        //	The *serverQuery* parameter, optional.
        //		This parameter contains the data that will be sent to the server.
        //		If this parameter is not given the parameter "query"'s
        //		data are sent to the server. This is done for some reasons:
        //		- to specify explicitly which data are sent to the server, they
        //		  might also be a mix of what is contained in "query", "queryOptions"
        //		  and the paging parameters "start" and "count" or may be even
        //		  completely different things.
        //		- don't modify the request.query data, so the interface using this
        //		  store can rely on unmodified data, as the combobox dijit currently
        //		  does it, it compares if the query has changed
        //		- request.query is required by the Read-API
        //
        // 		I.e. the following examples might be sent via GET:
        //		  fetch({query:{name:"abc"}, queryOptions:{ignoreCase:true}})
        //		  the URL will become:   /url.php?name=abc
        //
        //		  fetch({serverQuery:{q:"abc", c:true}, query:{name:"abc"}, queryOptions:{ignoreCase:true}})
        //		  the URL will become:   /url.php?q=abc&c=true
        //		  // The serverQuery-parameter has overruled the query-parameter
        //		  // but the query parameter stays untouched, but is not sent to the server!
        //		  // The serverQuery contains more data than the query, so they might differ!
        //
       // alert("Vao fetchItems");
        var serverQuery = request.serverQuery || request.query || {};
        //Need to add start and count
        if(!this.doClientPaging){
            serverQuery.start = request.start || 0;
            // Count might not be sent if not given.
            if(request.count){
                serverQuery.count = request.count;
            }
        }
        if(!this.doClientSorting){
            if(request.sort){
                
                var sort = request.sort[0];
                if(sort && sort.attribute){
                    var sortStr = sort.attribute;
                    if(sort.descending){
                        sortStr = "-" + sortStr;
                    }
                    serverQuery.sort = sortStr;
                }
            }
        }



        var self = this;
        //sumary:
        //      coresponding to _xhrFetchHandler in QueryReadStore.js,
        //      local client-sorting : sorting depends on data of each page
        //var filter = function(requestArgs, arrayOfItems){
        var filter = function(requestArgs, data){
            self._items = [];
           // alert(data.totalRows);
            var numRows = data.numRows || -1;

            // [!important

            if(numRows == -1){
                numRows = data.items.length;
            }
            numRows += self.numOfNewRows;
            
            //]
            self._count = requestArgs.count; // fix bug when rowsPerPage has been changed
            if(request.sort){
                self._sortInfo = request.sort;
            }
            //]
            var start = requestArgs.start||0;

            var endIndex = start + data.items.length;
            endIndex = endIndex > data.numRows ? data.numRows : endIndex;
            var i, key;
            if(self.doClientSorting){//client-sorting
                
                if(requestArgs.query){
                    var value;
                    var ignoreCase = requestArgs.queryOptions ? requestArgs.queryOptions.ignoreCase : false;

                    //See if there are any string values that can be regexp parsed first to avoid multiple regexp gens on the
                    //same value for each item examined.  Much more efficient.
                    var regexpList = {};
                    for(key in requestArgs.query){
                        value = requestArgs.query[key];
                        if(typeof value === "string"){
                            regexpList[key] = dojo.data.util.filter.patternToRegExp(value, ignoreCase);
                        }else if(value instanceof RegExp){
                            regexpList[key] = value;
                        }
                    }
                    //for(i = start; i < arrayOfItems.length; ++i){
                    for(i = start; i < endIndex; ++i){
                        var match = true;
                        //var candidateItem = arrayOfItems[i];
                        var candidateItem = self._arrayOfTopLevelItems[i];
                        if(candidateItem === null){
                            match = false;
                        }else{
                            for(key in requestArgs.query){
                                value = requestArgs.query[key];
                                if(!self._containsValue(candidateItem, key, value, regexpList[key])){
                                    match = false;
                                }
                            }
                        }
                        if(match){
                            self._items.push(candidateItem);
                        }
                    }
                    fetchHandler(self._items, requestArgs,numRows);
                }
            } else{
                
                // We want a copy to pass back in case the parent wishes to sort the array.
                // We shouldn't allow resort of the internal list, so that multiple callers
                // can get lists and sort without affecting each other.  We also need to
                // filter out any null values that have been left as a result of deleteItem()
                // calls in ItemFileWriteStore.
                //for(i = 0; i < arrayOfItems.length; ++i){
                for(i = start; i < endIndex; i++){
                    //var item = arrayOfItems[i];
                    var item = self._arrayOfTopLevelItems[i];
                    if(item !== null){
                        self._items.push(item);
                    }
                }
                
                numRows = self._numRows = (numRows === -1) ? self._items.length : numRows;
                fetchHandler(self._items, requestArgs,self._numRows);
                self._numRows = numRows;
            }
        };
       
        //if(this._loadFinished){
        //filter(request, this._getItemsArray(request.queryOptions));
        //}
        // Compare the last query and the current query by simply json-encoding them,
        // so we dont have to do any deep object compare ... is there some dojo.areObjectsEqual()???
        if(this.doClientPaging && this._lastServerQuery !== null &&
            dojo.toJson(serverQuery) == dojo.toJson(this._lastServerQuery)
            ){
            this._numRows = (this._numRows === -1) ? this._items.length : this._numRows;
            fetchHandler(this._items, request, this._numRows);
        }
        else{
            //Do a check on the JsonFileUrl and crosscheck it.
            //If it doesn't match the cross-check, it needs to be updated
            //This allows for either url or _jsonFileUrl to he changed to
            //reset the store load location.  Done this way for backwards
            //compatibility.  People use _jsonFileUrl (even though officially
            //private.
            //[ set url: !important
            if(this._jsonFileUrl !== this._ccUrl){
                dojo.deprecated("dojo.data.ItemFileReadStore: ",
                    "To change the url, set the url property of the store," +
                    " not _jsonFileUrl.  _jsonFileUrl support will be removed in 2.0");
                this._ccUrl = this._jsonFileUrl;
                this.url = this._jsonFileUrl;
            }else if(this.url !== this._ccUrl){
                this._jsonFileUrl = this.url;
                this._ccUrl = this.url;
            }
            //]
            //See if there was any forced reset of data.
            //  keywordParameters: {url: String}
            //	keywordParameters: {data: jsonObject}
            //	keywordParameters: {typeMap: object)
            if(this.data != null && this._jsonData == null){ // if data had been setted to store when initting
                this._jsonData = this.data;
                this.data = null;
                
            }

            if(this._jsonFileUrl){
               
                //If fetches come in before the loading has finished, but while
                //a load is in progress, we have to defer the fetching to be
                //invoked in the callback.
                //                if(this._loadInProgress){
                //                    this._queuedFetches.push({
                //                        args: request,
                //                        filter: filter
                //                    });
                //}else{ //begin else 1
                this._loadInProgress = true;
                // do fetching from server
                var xhrFunc = this.requestMethod.toLowerCase() == "post" ? dojo.xhrPost : dojo.xhrGet;
              //  alert("xhrHandler");
                var xhrHandler = xhrFunc({
                    url:this.url,
                    handleAs:"json-comment-optional",
                    content:dojo.mixin(serverQuery, this.param), // mixin param into serverQuery
                    form:this.formId,
                    //preventCache: this.urlPreventCache,
                    failOk: this.failOk
                });
                xhrHandler.addCallback(dojo.hitch(this, function(data){
                    //this._xhrFetchHandler(data, request, fetchHandler, errorHandler);
                    try{
                        //[stupid code : purpose for fix bug when not to implement paging on server, means to
                        //the length of data is greater than request.count
                       // alert("Vao call back");
                        if(data.numRows == 0){
                                data.numRows = data.items.length;
                        }
                       
                        if(data.items.length > request.count){
                            var count;
                            if(request.start + request.count > data.items.length){
                                count = data.items.length - request.start;

                            }
                            else{
                                count = request.count;
                            }
                            data.items = data.items.splice(request.start,count);
                        }
                       
                        //]
                        // ]end stupid
                        //self._getItemsFromLoadedData(data);
                        //summary:
                        //      each loading time, data need to merge into _arrayOfAllItems,_arrayOfTopLevelItems
                        self._getItemsFromLoadedData(data, request); // review???
                        
                        if ((self.counter + 1)* request.count >= data.numRows) {
                            self._loadFinished = true;
                            self._loadInProgress = false;
                        }
                        self.counter++;
                        //filter(request, self._getItemsArray(request.queryOptions));
                        //summary:
                        //        delegate to _fetchHandler
                        filter(request, data);// get data coresponding to each page (see _xhrFetchHandler)
                        //self._xhrFetchHandler(data, request, fetchHandler, errorHandler);
                        self._handleQueuedFetches();
                        sd.operator.displayWaitScreen(false);
                        // set if successful
                        if (self.vtReloading){
                            self.vtReloading = false;
                            try{
                                this.grid.updateRowCount(data.numRows);
                                
                            }catch(e){
                                console.log("[updateRowCount in iQueryWriteStore:" + e.message + "]");
                            }
                            sd.operator.displayWaitScreen(false);
                            if (dojo.isFunction(self.vtCallback)){
                                self.vtCallback(data);
                            }
                        }
                    }catch(e){
                        // have some errors occur
                        self._loadFinished = true;
                        self._loadInProgress = false;
                        errorHandler(e, request);
                        // if failure
                        if (self.vtReloading){
                            if (data.indexOf("This is logout page") != -1){
                                var arr = data.split("//@@");
                                parent.document.location.href = arr[1];
                            }
                            else{
                                var messageObject = sd.operator.getMessageObject(data);
                                if (messageObject){
                                    if (messageObject.type =="ERROR"){
                                        console.log("Error in reloading: " + messageObject.content);
                                    }
                                }
                            }
                            self.vtReloading = false;
                            sd.operator.displayWaitScreen( false );
                        }
                    }
                }));

                xhrHandler.addErrback(function(error){
                    // have some errors occur
                    self._loadInProgress = false;
                    errorHandler(error, request);
                    // if failure
                    if (self.vtReloading){
                        self.vtReloading = false;
                        sd.operator.displayWaitScreen( false );

                    }
                });
              
                //this.lastRequestHash = new Date().getTime()+"-"+String(Math.random()).substring(2);
                //this._lastServerQuery = dojo.mixin({}, serverQuery);// smart convert serverQuery into {}
                //Wire up the cancel to abort of the request
                //This call cancel on the deferred if it hasn't been called
                //yet and then will chain to the simple abort of the
                //simpleFetch keywordArgs
                var oldAbort = null;
                if(request.abort){
                    oldAbort = request.abort;
                }
                request.abort = function(){
                    var df = xhrHandler;
                    if(df && df.fired === -1){
                        df.cancel();
                        df = null;
                    }
                    if(oldAbort){
                        oldAbort.call(request);
                    }
                };
                
            //} //end else 1
            }else if(this._jsonData){ // data had been loaded
                try{
                     if ((this.counter + 1)* request.count >= this._jsonData.numRows) {
                        this._loadFinished = true;
                        this._loadInProgress = false;
                    }
                    this.counter++;
                    //copy of stupid code
                    if(this._jsonData.numRows === undefined  ||this._jsonData.numRows == 0){
                        this._jsonData.numRows = this._jsonData.items.length;
                    }
                    if(this._jsonData.items.length > request.count){
                        var count;
                        if(request.start + request.count > this._jsonData.items.length){
                            count = this._jsonData.items.length - request.start;

                        }
                        else{
                            count = request.count;
                        }
                        this._jsonData.items = this._jsonData.items.splice(request.start,count)
                    }
                    //]
                    this._getItemsFromLoadedData(this._jsonData,request);

                    //this._jsonData = null;
                    //filter(request, this._getItemsArray(request.queryOptions));
                    filter(request, this._jsonData);
                }catch(e){
                    errorCallback(e, request);
                }
            }else{
                errorCallback(new Error("dojo.data.ItemFileReadStore: No JSON source data was provided as either URL or a nested Javascript object."), keywordArgs);
            }
        }
    },
   /* fetchItemByIdentity: function(/* Object */ /*keywordArgs){
        //	summary:
        //		See dojo.data.api.Identity.fetchItemByIdentity()
        //              care about when data has been loaded incompletely

        // Hasn't loaded yet, we have to trigger the load.
        var item;
        var scope;
        //	summary:
        //		See dojo.data.api.Identity.fetchItemByIdentity()

        // See if we have already loaded the item with that id
        // In case there hasn't been a fetch yet, _itemsByIdentity is null
        // and thus a fetch will be triggered below.

        // Already loaded.  We can just look it up and call back.
        if(this._itemsByIdentity){
            item = this._itemsByIdentity[keywordArgs.identity];
            if(!(item === undefined)){
                if(keywordArgs.onItem){
                    scope = keywordArgs.scope?keywordArgs.scope:dojo.global;
                    keywordArgs.onItem.call(scope, item);
                }
                return;
            }
        }
        if(keywordArgs.indexInPage){
            this.indexInPage = keywordArgs.indexInPage;
        }
        var self = this;
        // Otherwise we need to go remote
        // Set up error handler
        var _errorHandler = function(errorData, requestObject){
            var scope = keywordArgs.scope ? keywordArgs.scope : dojo.global;
            if(keywordArgs.onError){
                keywordArgs.onError.call(scope, errorData);
            }
        };

        // Set up fetch handler
        var _fetchHandler = function(items, requestObject){
            var scope = keywordArgs.scope ? keywordArgs.scope : dojo.global;
            try{
                // There is supposed to be only one result
                var item = null;
                if(items && items.length == 1){
                    item = items[0];
                }
                item = items[self.indexInPage]; // index of item in the page which is loading
                // If no item was found, item is still null and we'll
                // fire the onItem event with the null here
                if(keywordArgs.onItem){
                    keywordArgs.onItem.call(scope, item);
                }
            }catch(error){
                if(keywordArgs.onError){
                    keywordArgs.onError.call(scope, error);
                }
            }
        };

        // Construct query
        //var request = {serverQuery:{id:keywordArgs.identity}};
        var request = {
            serverQuery:{
                id:keywordArgs.identity,
                start:keywordArgs.start,
                count:keywordArgs.count,
                startval:keywordArgs.startval,//Linhntn: For edit in server paging
                q:'*'
            }
        };
        // Dispatch query
        this._fetchItems(request, _fetchHandler, _errorHandler);
    },
*/
    close: function(/*dojo.data.api.Request || keywordArgs || null */ request){
    // I have no idea if this is really needed ...
    },

    _getItemsFromLoadedData: function(/* Object */ dataObject,/* Object */ request){
        //	summary:
        //		Function to parse the loaded data into item format and build the internal items array.
        //	description:
        //		Function to parse the loaded data into item format and build the internal items array.
        //
        //	dataObject:
        //		The JS data object containing the raw data to convery into item format.
        //
        // 	returns: array
        //		Array of items in store item format.

        // First, we define a couple little utility functions...

        var addingArrays = false;
        var self = this;
        //preprocess data
        dataObject = this._filterResponse(dataObject);
       
        if(dataObject.label){
            this._labelAttr = dataObject.label;
        }
        var start = request.start || 0;
        
        var endIndex = start + dataObject.items.length;
        //var endIndex = start + request.count;
      
        endIndex = endIndex > dataObject.numRows ? dataObject.numRows : endIndex;
        

        function valueIsAnItem(/* anything */ aValue){
            // summary:
            //		Given any sort of value that could be in the raw json data,
            //		return true if we should interpret the value as being an
            //		item itself, rather than a literal value or a reference.
            // example:
            // 	|	false == valueIsAnItem("Kermit");
            // 	|	false == valueIsAnItem(42);
            // 	|	false == valueIsAnItem(new Date());
            // 	|	false == valueIsAnItem({_type:'Date', _value:'May 14, 1802'});
            // 	|	false == valueIsAnItem({_reference:'Kermit'});
            // 	|	true == valueIsAnItem({name:'Kermit', color:'green'});
            // 	|	true == valueIsAnItem({iggy:'pop'});
            // 	|	true == valueIsAnItem({foo:42});
            var isItem = (
                (aValue !== null) &&
                (typeof aValue === "object") &&
                (!dojo.isArray(aValue) || addingArrays) &&
                (!dojo.isFunction(aValue)) &&
                (aValue.constructor == Object || dojo.isArray(aValue)) &&
                (typeof aValue._reference === "undefined") &&
                (typeof aValue._type === "undefined") &&
                (typeof aValue._value === "undefined") &&
                self.hierarchical
                );
            return isItem;
        }

        function addItemAndSubItemsToArrayOfAllItems(/* Item */ anItem){
            self._arrayOfAllItems.push(anItem);
            for(var attribute in anItem){
                var valueForAttribute = anItem[attribute];
                if(valueForAttribute){
                    if(dojo.isArray(valueForAttribute)){
                        var valueArray = valueForAttribute;
                        for(var k = 0; k < valueArray.length; ++k){
                            var singleValue = valueArray[k];
                            if(valueIsAnItem(singleValue)){
                                addItemAndSubItemsToArrayOfAllItems(singleValue);
                            }
                        }
                    }else{
                        if(valueIsAnItem(valueForAttribute)){
                            addItemAndSubItemsToArrayOfAllItems(valueForAttribute);
                        }
                    }
                }
            }
        }


        // We need to do some transformations to convert the data structure
        // that we read from the file into a format that will be convenient
        // to work with in memory.

        // Step 1: Walk through the object hierarchy and build a list of all items
        var i;
        var item;
        if(request.sort){
                if(this._sortInfo == null ||( this._sortInfo && dojo.toJson(request.sort) !== dojo.toJson(this._sortInfo))){
                    
                    try{
                        this.grid.selection.deselectAll();
                    }catch(e){
                    }
                    if(this._arrayOfUnCheck === undefined || this.grid._checkAll == false)
                        this._arrayOfUnCheck = [];
                    if(this.grid._checkAll == true){
                        for(var j = 0; j < this._arrayOfTopLevelItems.length; j++){
                            var temp = this._arrayOfTopLevelItems[j];
                            // loop to add or remove item in this._arrayOfUnCheck each sorting time -> too stupid
                            if(temp !== undefined && temp !== null){
                                var tempj = dojo.mixin({}, temp);
                                var index = this.indexOf(this._arrayOfUnCheck, tempj);
                                if(temp.checked == false){
                                    if(index == -1){
                                        this._arrayOfUnCheck.push(tempj);
                                    }
                                } else if(temp.checked == true){
                                   if(index !== -1){
                                        this._arrayOfUnCheck.splice(index,1);
                                    }
                                }
                                
                            }
                            //
                        }
                        this.grid._markup = []; // review ???
                    }
                    this._arrayOfAllItems = [];
                    this._arrayOfTopLevelItems = [];
                    
                }
         }
        //this._arrayOfAllItems = [];
        //this._arrayOfTopLevelItems = dataObject.items;

        for(var k = 0; k < dataObject.items.length; k++){
            this._arrayOfTopLevelItems[start + k] = dataObject.items[k];
        }
      
      //stupid code
        if(start >= request.count){ // fix bug when addItemAndSubItemsToArrayOfAllItems
            if(this._arrayOfAllItems[start - 1] === null || this._arrayOfAllItems[start - 1] === undefined){
                this._arrayOfAllItems[start - 1] = null;
            }
        }
        //]end stupid
     
        for(i = start; i < endIndex; i++){ //exactly
            item = this._arrayOfTopLevelItems[i];
            if(dojo.isArray(item)){
                addingArrays = true;
            }
            //addItemAndSubItemsToArrayOfAllItems(item); review yet???
            this._arrayOfAllItems[i] = item;
            item[this._rootItemPropName]=true;

        }
   
        // Step 2: Walk through all the attribute values of all the items,
        // and replace single values with arrays.  For example, we change this:
        //		{ name:'Miss Piggy', pets:'Foo-Foo'}
        // into this:
        //		{ name:['Miss Piggy'], pets:['Foo-Foo']}
        //
        // We also store the attribute names so we can validate our store
        // reference and item id special properties for the O(1) isItem
        var allAttributeNames = {};
        var key;

        //for(i = 0; i < this._arrayOfAllItems.length; ++i){
        for(i = start; i < endIndex; i++){
            item = this._arrayOfAllItems[i];
            for(key in item){
                //if(key !== this._rootItemPropName){
                if(key !== this._rootItemPropName && key !== this.chkPropName){ // means to mark the status of checkbox
                    var value = item[key];
                    if(value !== null){
                        if(!dojo.isArray(value)){
                            item[key] = [value];
                        }
                    }else{
                        item[key] = [null];
                    }
                }
                allAttributeNames[key]=key;
            }
            //summary:
            //      add new attribute to mark the status of checkbox
            if(this.grid !== null && this.grid._checkAll == true){
                if(!this.existIn(this._arrayOfUnCheck, item)){
                    item[this.chkPropName] = true;
                    //if(!this.existIn(this.grid._markup, item)) // be careful, because comparing repeats too much times 
                    this.grid._markup.push(item);
                } else {
                    item[this.chkPropName] = false;
                }
            } 
        }
       

        // Step 3: Build unique property names to use for the _storeRefPropName and _itemNumPropName
        // This should go really fast, it will generally never even run the loop.
        while(allAttributeNames[this._storeRefPropName]){
            this._storeRefPropName += "_";
        }
        while(allAttributeNames[this._itemNumPropName]){
            this._itemNumPropName += "_";
        }
        while(allAttributeNames[this._reverseRefMap]){
            this._reverseRefMap += "_";
        }

        // Step 4: Some data files specify an optional 'identifier', which is
        // the name of an attribute that holds the identity of each item.
        // If this data file specified an identifier attribute, then build a
        // hash table of items keyed by the identity of the items.
        var arrayOfValues;

        var identifier = dataObject.identifier;
        if(identifier){
            
            if(this._itemsByIdentity === null){
                this._itemsByIdentity = {};
            }
            // fix when sorting if identifier has been setted
            else if((this._itemsByIdentity && request.sort) || this._count != request.count){
                this._loadFinished = false; // do do something
                this.counter = 0;
                this._itemsByIdentity = {};
            }
           
            this._features['dojo.data.api.Identity'] = identifier;
            //for(i = 0; i < this._arrayOfAllItems.length; ++i){
            for(i = start; i < endIndex; i++){
                item = this._arrayOfAllItems[i];
                arrayOfValues = item[identifier];
                var identity = arrayOfValues[0];
                if(!this._itemsByIdentity[identity]){
                    this._itemsByIdentity[identity] = item;
                }else{
                    if(this._jsonFileUrl){
                        throw new Error("dojo.data.ItemFileReadStore:  The json data as specified by: [" + this._jsonFileUrl + "] is malformed.  Items within the list have identifier: [" + identifier + "].  Value collided: [" + identity + "]");
                    }else if(this._jsonData){
                        throw new Error("dojo.data.ItemFileReadStore:  The json data provided by the creation arguments is malformed.  Items within the list have identifier: [" + identifier + "].  Value collided: [" + identity + "]");
                    }
                }
            }
        }else{
            
            this._features['dojo.data.api.Identity'] = Number;
        }

        // Step 5: Walk through all the items, and set each item's properties
        // for _storeRefPropName and _itemNumPropName, so that store.isItem() will return true.
        //for(i = 0; i < this._arrayOfAllItems.length; ++i){
        for(i = start; i < endIndex; i++){
            item = this._arrayOfAllItems[i];
            item[this._storeRefPropName] = this;
            item[this._itemNumPropName] = i;
        }

        // Step 6: We walk through all the attribute values of all the items,
        // looking for type/value literals and item-references.
        //
        // We replace item-references with pointers to items.  For example, we change:
        //		{ name:['Kermit'], friends:[{_reference:{name:'Miss Piggy'}}] }
        // into this:
        //		{ name:['Kermit'], friends:[miss_piggy] }
        // (where miss_piggy is the object representing the 'Miss Piggy' item).
        //
        // We replace type/value pairs with typed-literals.  For example, we change:
        //		{ name:['Nelson Mandela'], born:[{_type:'Date', _value:'July 18, 1918'}] }
        // into this:
        //		{ name:['Kermit'], born:(new Date('July 18, 1918')) }
        //
        // We also generate the associate map for all items for the O(1) isItem function.
        //for(i = 0; i < this._arrayOfAllItems.length; ++i){
        for(i = start; i < endIndex; i++){
            item = this._arrayOfAllItems[i]; // example: { name:['Kermit'], friends:[{_reference:{name:'Miss Piggy'}}] }
            for(key in item){
                arrayOfValues = item[key]; // example: [{_reference:{name:'Miss Piggy'}}]
                for(var j = 0; j < arrayOfValues.length; ++j){
                    value = arrayOfValues[j]; // example: {_reference:{name:'Miss Piggy'}}
                    if(value !== null && typeof value == "object"){
                        if(("_type" in value) && ("_value" in value)){
                            var type = value._type; // examples: 'Date', 'Color', or 'ComplexNumber'
                            var mappingObj = this._datatypeMap[type]; // examples: Date, dojo.Color, foo.math.ComplexNumber, {type: dojo.Color, deserialize(value){ return new dojo.Color(value)}}
                            if(!mappingObj){
                                throw new Error("dojo.data.ItemFileReadStore: in the typeMap constructor arg, no object class was specified for the datatype '" + type + "'");
                            }else if(dojo.isFunction(mappingObj)){
                                arrayOfValues[j] = new mappingObj(value._value);
                            }else if(dojo.isFunction(mappingObj.deserialize)){
                                arrayOfValues[j] = mappingObj.deserialize(value._value);
                            }else{
                                throw new Error("dojo.data.ItemFileReadStore: Value provided in typeMap was neither a constructor, nor a an object with a deserialize function");
                            }
                        }
                        if(value._reference){
                            var referenceDescription = value._reference; // example: {name:'Miss Piggy'}
                            if(!dojo.isObject(referenceDescription)){
                                // example: 'Miss Piggy'
                                // from an item like: { name:['Kermit'], friends:[{_reference:'Miss Piggy'}]}
                                arrayOfValues[j] = this._itemsByIdentity[referenceDescription];
                            }else{
                                // example: {name:'Miss Piggy'}
                                // from an item like: { name:['Kermit'], friends:[{_reference:{name:'Miss Piggy'}}] }
                                //for(var k = 0; k < this._arrayOfAllItems.length; ++k){
                                for(k = start; k < endIndex; k++){
                                    var candidateItem = this._arrayOfAllItems[k];
                                    var found = true;
                                    for(var refKey in referenceDescription){
                                        if(candidateItem[refKey] != referenceDescription[refKey]){
                                            found = false;
                                        }
                                    }
                                    if(found){
                                        arrayOfValues[j] = candidateItem;
                                    }
                                }
                            }
                            if(this.referenceIntegrity){
                                var refItem = arrayOfValues[j];
                                if(this.isItem(refItem)){
                                    this._addReferenceToMap(refItem, item, key);
                                }
                            }
                        }else if(this.isItem(value)){
                            //It's a child item (not one referenced through _reference).
                            //We need to treat this as a referenced item, so it can be cleaned up
                            //in a write store easily.
                            if(this.referenceIntegrity){
                                this._addReferenceToMap(value, item, key);
                            }
                        }
                    }
                }
            }
        }
    },
    /* dojo.data.api.Write */

	newItem: function(/* Object? */ keywordArgs, /* Object? */ parentInfo){
		// summary: See dojo.data.api.Write.newItem()
             try{
		this._assert(!this._saveInProgress);

		if(!this._loadFinished){
			// We need to do this here so that we'll be able to find out what
			// identifierAttribute was specified in the data file.
			//this._forceLoad();
		}
                
                if(this._numRows !== -1){// stupid code
                    if(this._arrayOfTopLevelItems[this._numRows - 1] === undefined || this._arrayOfTopLevelItems[this._numRows - 1] == null){
                        this._arrayOfTopLevelItems[this._numRows - 1] = null;
                        this._arrayOfAllItems[this._numRows - 1] = null;
                    }
                }
		if(typeof keywordArgs != "object" && typeof keywordArgs != "undefined"){
			throw new Error("newItem() was passed something other than an object");
		}
		var newIdentity = null;
		var identifierAttribute = this._getIdentifierAttribute();
		if(identifierAttribute === Number){
			newIdentity = this._arrayOfAllItems.length;
		}else{
			newIdentity = keywordArgs[identifierAttribute];
			if(typeof newIdentity === "undefined"){
				throw new Error("newItem() was not passed an identity for the new item");
			}
			if(dojo.isArray(newIdentity)){
				throw new Error("newItem() was not passed an single-valued identity");
			}
		}

		// make sure this identity is not already in use by another item, if identifiers were
		// defined in the file.  Otherwise it would be the item count,
		// which should always be unique in this case.
		if(this._itemsByIdentity){
			this._assert(typeof this._itemsByIdentity[newIdentity] === "undefined");
		}
		this._assert(typeof this._pending._newItems[newIdentity] === "undefined");
		this._assert(typeof this._pending._deletedItems[newIdentity] === "undefined");

		var newItem = {};
		newItem[this._storeRefPropName] = this;
		newItem[this._itemNumPropName] = this._arrayOfAllItems.length;
		if(this._itemsByIdentity){
			this._itemsByIdentity[newIdentity] = newItem;
			//We have to set the identifier now, otherwise we can't look it
			//up at calls to setValueorValues in parentInfo handling.
			newItem[identifierAttribute] = [newIdentity];
		}
		this._arrayOfAllItems.push(newItem);

		//We need to construct some data for the onNew call too...
		var pInfo = null;

		// Now we need to check to see where we want to assign this thingm if any.
		if(parentInfo && parentInfo.parent && parentInfo.attribute){
			pInfo = {
				item: parentInfo.parent,
				attribute: parentInfo.attribute,
				oldValue: undefined
			};

			//See if it is multi-valued or not and handle appropriately
			//Generally, all attributes are multi-valued for this store
			//So, we only need to append if there are already values present.
			var values = this.getValues(parentInfo.parent, parentInfo.attribute);
			if(values && values.length > 0){
				var tempValues = values.slice(0, values.length);
				if(values.length === 1){
					pInfo.oldValue = values[0];
				}else{
					pInfo.oldValue = values.slice(0, values.length);
				}
				tempValues.push(newItem);
				this._setValueOrValues(parentInfo.parent, parentInfo.attribute, tempValues, false);
				pInfo.newValue = this.getValues(parentInfo.parent, parentInfo.attribute);
			}else{
				this._setValueOrValues(parentInfo.parent, parentInfo.attribute, newItem, false);
				pInfo.newValue = newItem;
			}
		}else{
			//Toplevel item, add to both top list as well as all list.
			newItem[this._rootItemPropName]=true;
			this._arrayOfTopLevelItems.push(newItem);
		}

		this._pending._newItems[newIdentity] = newItem;

		//Clone over the properties to the new item
		for(var key in keywordArgs){
			if(key === this._storeRefPropName || key === this._itemNumPropName){
				// Bummer, the user is trying to do something like
				// newItem({_S:"foo"}).  Unfortunately, our superclass,
				// ItemFileReadStore, is already using _S in each of our items
				// to hold private info.  To avoid a naming collision, we
				// need to move all our private info to some other property
				// of all the items/objects.  So, we need to iterate over all
				// the items and do something like:
				//    item.__S = item._S;
				//    item._S = undefined;
				// But first we have to make sure the new "__S" variable is
				// not in use, which means we have to iterate over all the
				// items checking for that.
				throw new Error("encountered bug in ItemFileWriteStore.newItem");
			}
			var value = keywordArgs[key];
			if(!dojo.isArray(value)){
				value = [value];
			}
			newItem[key] = value;
			if(this.referenceIntegrity){
				for(var i = 0; i < value.length; i++){
					var val = value[i];
					if(this.isItem(val)){
						this._addReferenceToMap(val, newItem, key);
					}
				}
			}
		}
                //[
                this.numOfNewRows++;
                //]
		this.onNew(newItem, pInfo); // dojo.data.api.Notification call
		return newItem; // item
             }catch(e){
                 console.debug("[store.newItem:" + e.message + "]");
             }
	},

	_removeArrayElement: function(/* Array */ array, /* anything */ element){
		var index = dojo.indexOf(array, element);
		if(index != -1){
			array.splice(index, 1);
			return true;
		}
		return false;
	},

	
        existIn:function(arr, item){
              var value = this.copyOf(item);
              if(arr !== undefined && arr !== null){
                   for(var j = 0;j < arr.length; j++){
                      var tempj = this.copyOf(arr[j]);
                      if(dojo.toJson(value) == dojo.toJson(tempj)){
                          alert(true)
                          return true;

                      }
                   }
              }
               return false;
        },
        copyOf:function(item){
            var o1 = {};
            for(var key in item){
                if(key !== this._storeRefPropName && key != this._reverseRefMap &&
                    key !== this._itemNumPropName && key !== this._rootItemPropName
                    && key !== this.chkPropName && key !== '_RS' && key !== '_R'){
                    o1[key] = item[key];
                }
            }
            return o1;
        },
        indexOf:function(arr,item){
               var value = this.copyOf(item);
               for(var j = 0; j < arr.length; j++){
                    var arrj = this.copyOf(arr[j]);
                    if(dojo.toJson(value) == dojo.toJson(arrj)){
                        return j;
                    }
                }
               return -1;
       },
       _setValueOrValues: function(/* item */ item, /* attribute-name-string */ attribute, /* anything */ newValueOrValues, /*boolean?*/ callOnSet){
                this._assert(!this._saveInProgress);

		// Check for valid arguments
		this._assertIsItem(item);
		this._assert(dojo.isString(attribute));
		this._assert(typeof newValueOrValues !== "undefined");

		// Make sure the user isn't trying to change the item's identity
		var identifierAttribute = this._getIdentifierAttribute();
		if(attribute == identifierAttribute){
			alert("WF error: ItemFileWriteStore does not have support for changing the value of an item's identifier.");
                        return true;
		}

		// To implement the Notification API, we need to make a note of what
		// the old attribute value was, so that we can pass that info when
		// we call the onSet method.
		var oldValueOrValues = this._getValueOrValues(item, attribute);

		var identity = this.getIdentity(item);
		if(!this._pending._modifiedItems[identity]){
			// Before we actually change the item, we make a copy of it to
			// record the original state, so that we'll be able to revert if
			// the revert method gets called.  If the item has already been
			// modified then there's no need to do this now, since we already
			// have a record of the original state.
			var copyOfItemState = {};
			for(var key in item){
				if((key === this._storeRefPropName) ||(key === this.chkPropName) || (key === this._itemNumPropName) || (key === this._rootItemPropName)){
					copyOfItemState[key] = item[key];
				}else if(key === this._reverseRefMap){
					copyOfItemState[key] = dojo.clone(item[key]);
				}else{
					copyOfItemState[key] = item[key].slice(0, item[key].length);
				}
			}
			// Now mark the item as dirty, and save the copy of the original state
			this._pending._modifiedItems[identity] = copyOfItemState;
		}

		// Okay, now we can actually change this attribute on the item
		var success = false;

		if(dojo.isArray(newValueOrValues) && newValueOrValues.length === 0){

			// If we were passed an empty array as the value, that counts
			// as "unsetting" the attribute, so we need to remove this
			// attribute from the item.
			success = delete item[attribute];
			newValueOrValues = undefined; // used in the onSet Notification call below

			if(this.referenceIntegrity && oldValueOrValues){
				var oldValues = oldValueOrValues;
				if(!dojo.isArray(oldValues)){
					oldValues = [oldValues];
				}
				for(var i = 0; i < oldValues.length; i++){
					var value = oldValues[i];
					if(this.isItem(value)){
						this._removeReferenceFromMap(value, item, attribute);
					}
				}
			}
		}else{
			var newValueArray;
			if(dojo.isArray(newValueOrValues)){
				var newValues = newValueOrValues;
				// Unfortunately, it's not safe to just do this:
				//    newValueArray = newValues;
				// Instead, we need to copy the array, which slice() does very nicely.
				// This is so that our internal data structure won't
				// get corrupted if the user mucks with the values array *after*
				// calling setValues().
				newValueArray = newValueOrValues.slice(0, newValueOrValues.length);
			}else{
				newValueArray = [newValueOrValues];
			}

			//We need to handle reference integrity if this is on.
			//In the case of set, we need to see if references were added or removed
			//and update the reference tracking map accordingly.
			if(this.referenceIntegrity){
				if(oldValueOrValues){
					var oldValues = oldValueOrValues;
					if(!dojo.isArray(oldValues)){
						oldValues = [oldValues];
					}
					//Use an associative map to determine what was added/removed from the list.
					//Should be O(n) performant.  First look at all the old values and make a list of them
					//Then for any item not in the old list, we add it.  If it was already present, we remove it.
					//Then we pass over the map and any references left it it need to be removed (IE, no match in
					//the new values list).
					var map = {};
					dojo.forEach(oldValues, function(possibleItem){
						if(this.isItem(possibleItem)){
							var id = this.getIdentity(possibleItem);
							map[id.toString()] = true;
						}
					}, this);
					dojo.forEach(newValueArray, function(possibleItem){
						if(this.isItem(possibleItem)){
							var id = this.getIdentity(possibleItem);
							if(map[id.toString()]){
								delete map[id.toString()];
							}else{
								this._addReferenceToMap(possibleItem, item, attribute);
							}
						}
					}, this);
					for(var rId in map){
						var removedItem;
						if(this._itemsByIdentity){
							removedItem = this._itemsByIdentity[rId];
						}else{
							removedItem = this._arrayOfAllItems[rId];
						}
						this._removeReferenceFromMap(removedItem, item, attribute);
					}
				}else{
					//Everything is new (no old values) so we have to just
					//insert all the references, if any.
					for(var i = 0; i < newValueArray.length; i++){
						var value = newValueArray[i];
						if(this.isItem(value)){
							this._addReferenceToMap(value, item, attribute);
						}
					}
				}
			}
			item[attribute] = newValueArray;
			success = true;
		}

		// Now we make the dojo.data.api.Notification call
		if(callOnSet){
			this.onSet(item, attribute, oldValueOrValues, newValueOrValues);
		}
		return success; // boolean
	},
       
         setStoreEmpty: function(){
            var item;
            var n = this.grid._by_idx.length;
            for (var i = 0;i<n;i++){
               item = this.grid.getItem(0);
               this.deleteItem(item);
            }
             
          //  this._items = new Array();

         //   this._arrayOfTopLevelItems = new Array();
            
         //   this._arrayOfAllItems = new Array();
         },
    getValues: function(/* item */ item,/* attribute-name-string */ attribute, isChildValue){
        //	summary:
        //		See dojo.data.api.Read.getValues()
       /* if (isChildValue === undefined){ //Linhntn kiem tra co phai lay trong ham de quy goi lan 2 khong
            this._assertIsItem(item);
            this._assertIsAttribute(attribute);
        }*/
        //return item[attribute] || []; // Array
        // [ Datbt
        // check if field contain '.'
        if (attribute.indexOf(".") == -1){
            return item[attribute] || []; // Array
        }
        else{
            var arrAttr = attribute.split(".");
            var parentAttr = arrAttr[0];
            var parentValue = item[parentAttr];
            arrAttr.splice(0,1);
            var childAttr = arrAttr.join(".");
            if (parentValue.length > 0){
                if (parentValue[0]!=null){
                    return this.getValues(parentValue[0],childAttr, 1) || [];
                }
            }
            return [];
        }
      
    // ] Datbt
    },
    getValue: function(	/* item */ item, 
						/* attribute-name-string */ attribute, 
						/* value? */ defaultValue){
		//	summary: 
		//		See dojo.data.api.Read.getValue()
		var values = this.getValues(item, attribute);
		if (values instanceof Array){
                    return (values.length > 0)?values[0]:defaultValue; // mixed
                } else {
                    return ((values !== undefined) && (values!== null))?values:defaultValue;//Linhntn cho trường kép trả về
                }
	}
    }
);


