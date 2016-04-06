/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * DungDV
 * them mang vnCharacters, cac ham _stdArrayComparator, stdComparator vao class vt.dataGrid.dataUtil cho chuc nang sort
 * 10/07/2011
 * version 3.3
 */
dojo.provide("vt.dataGrid.dataStore");
dojo.require("dojo.data.ItemFileWriteStore");

dojo.declare("vt.dataGrid.dataStore", dojo.data.ItemFileWriteStore, {
    //[ Datbt
    // them thuoc tinh cho viec reload grid
    formId: "",
    param: null,
    callback: null,
    grid:null,
    vtReloading: false,
    vtCallback:null,
    chkPropName:"checked",
    //] Datbt
    _fetchItems: function(	/* Object */ keywordArgs,
        /* Function */ findCallback,
        /* Function */ errorCallback){
        //	summary:
        //		See dojo.data.util.simpleFetch.fetch()
        var self = this;
        var filter = function(requestArgs, arrayOfItems){
            var items = [];
            var i, key;
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
                for(i = 0; i < arrayOfItems.length; ++i){
                    var match = true;
                    var candidateItem = arrayOfItems[i];
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
                        items.push(candidateItem);
                    }
                }
                findCallback(items, requestArgs);
            }else{
                // We want a copy to pass back in case the parent wishes to sort the array.
                // We shouldn't allow resort of the internal list, so that multiple callers
                // can get lists and sort without affecting each other.  We also need to
                // filter out any null values that have been left as a result of deleteItem()
                // calls in ItemFileWriteStore.
                for(i = 0; i < arrayOfItems.length; ++i){
                    var item = arrayOfItems[i];
                    if(item !== null){
                        items.push(item);
                    }
                }
                findCallback(items, requestArgs);
            }
        };

        if(this._loadFinished){
            filter(keywordArgs, this._getItemsArray(keywordArgs.queryOptions));
        }else{
            //Do a check on the JsonFileUrl and crosscheck it.
            //If it doesn't match the cross-check, it needs to be updated
            //This allows for either url or _jsonFileUrl to he changed to
            //reset the store load location.  Done this way for backwards
            //compatibility.  People use _jsonFileUrl (even though officially
            //private.
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

            //See if there was any forced reset of data.
            if(this.data != null && this._jsonData == null){
                this._jsonData = this.data;
                this.data = null;
            }

            if(this._jsonFileUrl){
                //If fetches come in before the loading has finished, but while
                //a load is in progress, we have to defer the fetching to be
                //invoked in the callback.
                if(this._loadInProgress){
                    this._queuedFetches.push({
                        args: keywordArgs,
                        filter: filter
                    });
                }else{
                    this._loadInProgress = true;
                    var getArgs = {
                        url: self._jsonFileUrl,
                        // [ Datbt 2010/06/04
                        // handleAs: "json-comment-optional",
                        handleAs: "text",
                        // [ Datbt 2010/06/04
                        preventCache: this.urlPreventCache,
                        failOk: this.failOk,
                        //[Datbt 2010/06/04
                        form:this.formId,
                        content:this.param
                    //]Datbt
                    };
                    //[ Datbt 2010/06/04 đổi post thành get
                    //                    var getHandler = dojo.xhrGet(getArgs);
                    var getHandler = dojo.xhrPost(getArgs);
                    //] Datbt 2010/06/04
                    getHandler.addCallback(function(data){
                        try{
                            //[ Datbt 2010/06/04
                            // do handleAs text nên phải eval
                            eval("var data =  " + data + ";");
                            //]Datbt
                            if (data.type !="ERROR"){
                                self._getItemsFromLoadedData(data);
                                self._loadFinished = true;
                                self._loadInProgress = false;

                                filter(keywordArgs, self._getItemsArray(keywordArgs.queryOptions));
                                self._handleQueuedFetches();
                                //[ Datbt 2010/06/04
                                // nếu thành công mới set
                                if (self.vtReloading){
                                    self.vtReloading = false;
                                    self.grid.setStore(self);
                                    sd.operator.displayWaitScreen( false );
                                    if (dojo.isFunction(self.vtCallback)){
                                        self.vtCallback(data);
                                    }
                                }
                            }
                        //]Datbt
                        }catch(e){
                            console.debug("JSException in dataStore.js-_fetchItems.getHandler.addCallback: \n" + e.message);
                            
                            self._loadFinished = true;
                            self._loadInProgress = false;
                            errorCallback(e, keywordArgs);
                            //[ Datbt 2010/06/04
                            // nếu không thành công
                            if (self.vtReloading){
                                if (data.indexOf("This is logout page") != -1){
                                    var arr = data.split("//@@");
                                    parent.document.location.href = arr[1];
                                }
                                else{
                                    var messageObject = sd.operator.getMessageObject(data);
                                    if (messageObject){
                                        if (messageObject.type =="ERROR"){
                                            msg.error("Error in reloading: " + messageObject.content);
                                        }
                                    }
                                }
                                self.vtReloading = false;
                                sd.operator.displayWaitScreen( false );
                            }
                        //]Datbt
                        }
                    });
                    getHandler.addErrback(function(error){
                        self._loadInProgress = false;
                        errorCallback(error, keywordArgs);
                        //[ Datbt 2010/06/04
                        // nếu không thành công
                        if (self.vtReloading){
                            self.vtReloading = false;
                            sd.operator.displayWaitScreen( false );
                            
                        }
                    //]Datbt
                    });

                    //Wire up the cancel to abort of the request
                    //This call cancel on the deferred if it hasn't been called
                    //yet and then will chain to the simple abort of the
                    //simpleFetch keywordArgs
                    var oldAbort = null;
                    if(keywordArgs.abort){
                        oldAbort = keywordArgs.abort;
                    }
                    keywordArgs.abort = function(){
                        var df = getHandler;
                        if(df && df.fired === -1){
                            df.cancel();
                            df = null;
                        }
                        if(oldAbort){
                            oldAbort.call(keywordArgs);
                        }
                    };
                }
            }else if(this._jsonData){
                try{
                    this._loadFinished = true;
                    this._getItemsFromLoadedData(this._jsonData);
                    this._jsonData = null;
                    filter(keywordArgs, this._getItemsArray(keywordArgs.queryOptions));
                }catch(e){
                    errorCallback(e, keywordArgs);
                }
            }else{
                errorCallback(new Error("dojo.data.ItemFileReadStore: No JSON source data was provided as either URL or a nested Javascript object."), keywordArgs);
            }
        }
    },




    _forceLoadByPost: function(_thisGrid, formId,param, callback){
        //	summary:
        //		Internal function to force a load of the store if it hasn't occurred yet.  This is required
        //		for specific functions to work properly.
        var self = this;
        //Do a check on the JsonFileUrl and crosscheck it.
        //If it doesn't match the cross-check, it needs to be updated
        //This allows for either url or _jsonFileUrl to he changed to
        //reset the store load location.  Done this way for backwards
        //compatibility.  People use _jsonFileUrl (even though officially
        //private.
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

        //See if there was any forced reset of data.
        if(this.data != null && this._jsonData == null){
            this._jsonData = this.data;
            this.data = null;
        }
        if(this._jsonFileUrl){
            // Datbt
            _thisGrid.showMessage(_thisGrid.loadingMessage);
            var getArgs = {
                url: this._jsonFileUrl,
                //                handleAs: "json-comment-optional",
                handleAs: "text",
                preventCache: this.urlPreventCache,
                failOk: this.failOk,
                sync: true,
                //[Datbt
                form:formId,
                content:param
            //]Datbt
            };
            var getHandler = dojo.xhrPost(getArgs);
            getHandler.addCallback(function(data){
                try{
                    //Check to be sure there wasn't another load going on concurrently
                    //So we don't clobber data that comes in on it.  If there is a load going on
                    //then do not save this data.  It will potentially clobber current data.
                    //We mainly wanted to sync/wait here.
                    //TODO:  Revisit the loading scheme of this store to improve multi-initial
                    //request handling.
                    eval("var data =  " + data + ";");
                    if(self._loadInProgress !== true && !self._loadFinished){
                        self._getItemsFromLoadedData(data);
                        self._loadFinished = true;
                    }else if(self._loadInProgress){
                        //Okay, we hit an error state we can't recover from.  A forced load occurred
                        //while an async load was occurring.  Since we cannot block at this point, the best
                        //that can be managed is to throw an error.
                        throw new Error("dojo.data.ItemFileReadStore:  Unable to perform a synchronous load, an async load is in progress.");
                    }
                    // [ Datbt
                    _thisGrid._fetch(0);
                    callback(data,true);
                // ] Datbt
                }catch(e){
                    if (data.indexOf("This is logout page") != -1){
                        var arr = data.split("//@@");
                        parent.document.location.href = arr[1];
                    }
                    _thisGrid._onFetchError(e);
                    console.log(e);
                    throw e;
                }
            });
            getHandler.addErrback(function(error){
                throw error;
            });

        //[Datbt
        }else {
            self._jsonData = {
                identifier: null,
                label: null,
                items: []
            };
            self._getItemsFromLoadedData(self._jsonData);
            self._jsonData = null;
            self._loadFinished = true;
            _thisGrid._fetch(0);

        }
    //]Datbt
    },

    getValues: function(/* item */ item,/* attribute-name-string */ attribute){
        //	summary:
        //		See dojo.data.api.Read.getValues()
        this._assertIsItem(item);
        this._assertIsAttribute(attribute);
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
                    return this.getValues(parentValue[0],childAttr) || [];
                }
            }
            return [];
        }
      
    // ] Datbt
    },
    // dungdv modified 18/06/2011: comment Error
    _setValueOrValues: function(/* item */ item, /* attribute-name-string */ attribute, /* anything */ newValueOrValues, /*boolean?*/ callOnSet){
        this._assert(!this._saveInProgress);

        // Check for valid arguments
        this._assertIsItem(item);
        this._assert(dojo.isString(attribute));
        this._assert(typeof newValueOrValues !== "undefined");

        // Make sure the user isn't trying to change the item's identity
        var identifierAttribute = this._getIdentifierAttribute();
        if(attribute == identifierAttribute){
            throw new Error("ItemFileWriteStore does not have support for changing the value of an item's identifier.");
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
    }
});
dojo.declare("vt.dataGrid.dataUtil", null, {
    vnCharacters : {
        'a' : 0, 'b' : 36, 'c' : 38, 'd' : 40, 'e' : 44, 'f' : 68, 'g' : 70, 'h' : 72, 'i' : 74, 'j' : 87, 'k' : 89, 'l' : 91, 'm' : 93, 'n' : 95, 'o' : 97, 'p' : 133, 'q' : 135, 'r' : 137, 's' : 139, 't' : 141, 'u' : 143, 'v' : 167, 'w' : 169, 'x' : 171, 'y' : 173, 'z' : 185,
        'A' : 18, 'B' : 37, 'C' : 39, 'D' : 42, 'E' : 56, 'F' : 69, 'G' : 71, 'H' : 73, 'I' : 80, 'J' : 88, 'K' : 90, 'L' : 92, 'M' : 94, 'N' : 96, 'O' : 115, 'P' : 134, 'Q' : 136, 'R' : 138, 'S' : 140, 'T' : 142, 'U' : 155, 'V' : 168, 'W' : 170, 'X' : 172, 'Y' : 179, 'Z' : 186,
        'á' : 1, 'à' : 2, 'ả' : 3, 'ã' : 4, 'ạ' : 5,
        'ă' : 6, 'ắ' : 7, 'ằ' : 8, 'ẳ' : 9, 'ẵ' : 10, 'ặ' : 11,
        'â' : 12, 'ấ' : 13, 'ầ' : 14, 'ẩ' : 15, 'ẫ' : 16, 'ậ' : 17,
        'é' : 45, 'è' : 46, 'ẻ' : 47, 'ẽ' : 48, 'ẹ' : 49,
        'ê' : 50, 'ế' : 51, 'ề' : 52, 'ể' : 53, 'ễ' : 54, 'ệ' : 55,
        'ó' : 98, 'ò' : 99, 'ỏ' : 100, 'õ' : 101, 'ọ' : 102,
        'ô' : 103, 'ố' : 104, 'ồ' : 105, 'ổ' : 106, 'ỗ' : 107, 'ộ' : 108,
        'ơ' : 109, 'ớ' : 110, 'ờ' : 111, 'ở' : 112, 'ỡ' : 113, 'ợ' : 114,
        'ú' : 144, 'ù' : 145, 'ủ' : 146, 'ũ' : 147, 'ụ' : 148,
        'ư' : 149, 'ứ' : 150, 'ừ' : 151, 'ử' : 152, 'ữ' : 153, 'ự' : 154,
        'ý' : 174, 'ỳ' : 175, 'ỷ' : 176, 'ỹ' : 177, 'ỵ' : 178,
        'í' : 75, 'ì' : 76, 'ỉ' : 77, 'ĩ' : 78, 'ị' : 79,
        'đ' : 41,
        'Á' : 19, 'À' : 20, 'Ả' : 21, 'Ã' : 22, 'Ạ' : 23,
        'Ă' : 24, 'Ắ' : 25, 'Ằ' : 26, 'Ẳ' : 27, 'Ẵ' : 28, 'Ặ' : 29,
        'Â' : 30, 'Ấ' : 31, 'Ầ' : 32, 'Ẩ' : 33, 'Ẫ' : 34, 'Ậ' : 35,
        'É' : 57, 'È' : 58, 'Ẻ' : 59, 'Ẽ' : 60, 'Ẹ' : 61,
        'Ê' : 62, 'Ế' : 63, 'Ề' : 64, 'Ể' : 65, 'Ễ' : 66, 'Ệ' : 67,
        'Ó' : 116, 'Ò' : 117, 'Ỏ' : 118, 'Õ' : 119, 'Ọ' : 120,
        'Ô' : 121, 'Ố' : 122, 'Ồ' : 123, 'Ổ' : 124, 'Ỗ' : 125, 'Ộ' : 126,
        'Ơ' : 127, 'Ớ' : 128, 'Ờ' : 129, 'Ở' : 130, 'Ỡ' : 131, 'Ợ' : 132,
        'Ú' : 156, 'Ù' : 157, 'Ủ' : 158, 'Ũ' : 159, 'Ụ' : 160,
        'Ư' : 161, 'Ứ' : 162, 'Ừ' : 163, 'Ử' : 164, 'Ữ' : 165, 'Ự' : 166,
        'Ý' : 180, 'Ỳ' : 181, 'Ỷ' : 182, 'Ỹ' : 183, 'Ỵ' : 184,
        'Í' : 81, 'Ì' : 82, 'Ỉ' : 83, 'Ĩ' : 84, 'Ị' : 85,
        'Đ' : 43
    },
    _stdArrayComparator: function(a,b,aLow,bLow) {
        for (var i = 0, size = a.length; i < size; i++) {
            if (this.vnCharacters[a[i]] !== undefined && this.vnCharacters[b[i]] !== undefined) {
                if (this.vnCharacters[a[i]] == this.vnCharacters[b[i]])continue;
                return (this.vnCharacters[a[i]] > this.vnCharacters[b[i]] ? 1 : -1);
            } else {
                if (aLow[i] == bLow[i]) {
                    if (a[i] == b[i])continue;
                    return (a[i] > b[i] ? 1 : -1);
                }
                return (aLow[i] > bLow[i] ? 1 : -1);
            }
        }
        return (aLow.length == bLow.length ? 0 : -1);
    },
    stdComparator: function(a,b) {
        try{
            var dataUtil = new vt.dataGrid.dataUtil();
            if(a === undefined || a === null) {
                return -1;
            }
            if(b === undefined || b === null) {
                return 1;
            }
            var ach = a.split('');
            var achLow = a.toLowerCase().split('');
            var bch = b.split('');
            var bchLow = b.toLowerCase().split('');
            if (achLow.length <= bchLow.length) {
                return dataUtil._stdArrayComparator(ach,bch,achLow,bchLow);
            } else {
                return -dataUtil._stdArrayComparator(bch,ach,bchLow,achLow);
            }
        }catch(e){
            console.debug("error in stdComparator: " + e.message);
        }
    },
    viComparator: function(a,b){
        try{
            var dataUtil = new vt.dataGrid.dataUtil();
            var r = -1;
            if(a === null){
                a = undefined;
            }
            if(b === null){
                b = undefined;
            }
            if(dataUtil.convertVNString(a) == dataUtil.convertVNString(b)){
                r = 0;
            }else if(dataUtil.convertVNString(a) > dataUtil.convertVNString(b) || a == null){
                r = 1;
            }
            return r; //int {-1,0,1}
        }catch(e){
            console.debug("error in viComparator: " + e.message);
        }
    },
    numberComparator: function(a,b){
        try{
            
            var r = -1;
            var x = parseInt(a);
            if(x == NaN){
                x = undefined;
            }
            var y = parseInt(b);
            if(y == NaN){
                y = undefined;
            }
            if(x == y){
                r = 0;
            }else if(x > y || a == null){
                r = 1;
            }
            return r; //int {-1,0,1}
        }catch(e){
            console.debug("error in viComparator: " + e.message);
        }
    },
    dateComparator: function(a,b){
        try{
            var dataUtil = new vt.dataGrid.dataUtil();
            var r = -1;
            var x = dataUtil.convertToJSDate(a);
            if(x == null){
                x = undefined;
            }
            var y = dataUtil.convertToJSDate(b);
            if(y == null){
                y = undefined;
            }
            if(x == y){
                r = 0;
            }else if(x > y || a == null){
                r = 1;
            }
            return r; //int {-1,0,1}
        }catch(e){
            console.debug("error in dateComparator: " + e.message);
        }
    },
    convertToJSDate: function(inDatum){
        if((/[0-9]T[0-9]/).test(inDatum)){
            //_inDatum = inDatum.replace(/T/i,' ');
            var temp = inDatum.split("T");
            var inDatum_1 = temp[0];
            var inDatum_2 = temp[1];//[hh:mm:ss]
            if(inDatum_1.indexOf('-')!=-1){
                var tmp = inDatum_1.split("-");
                // MM/dd/yyyy for constructing
                var date_string = tmp[1]+"/"+tmp[2]+"/"+tmp[0] + " " + inDatum_2;
                return new Date(date_string);
            }
        }
        return null;
    },
    convertVNString: function (sValue){
        var s = "";
        for (var i=0; i < sValue.length; i++){
            switch (sValue[i]) {
                case 'a':
                    s += String.fromCharCode(58);
                    break;
                case 'A':
                    s += String.fromCharCode(58);
                    break;
                case 'à':
                    s += String.fromCharCode(59);
                    break;
                case 'ả':
                    s += String.fromCharCode(60);
                    break;
                case 'ã':
                    s += String.fromCharCode(61);
                    break;
                case 'á':
                    s += String.fromCharCode(62);
                    break;
                case 'ạ':
                    s += String.fromCharCode(63);
                    break;
                case 'ă':
                    s += String.fromCharCode(64);
                    break;
                case 'Ǎ':
                    s += String.fromCharCode(64);
                    break;
                case 'ằ':
                    s += String.fromCharCode(65);
                    break;
                case 'ắ':
                    s += String.fromCharCode(66);
                    break;
                case 'ã':
                    s += String.fromCharCode(67);
                    break;
                case 'ắ':
                    s += String.fromCharCode(68);
                    break;
                case 'ặ':
                    s += String.fromCharCode(69);
                    break;
                case 'â':
                    s += String.fromCharCode(70);
                    break;
                case 'Â':
                    s += String.fromCharCode(70);
                    break;
                case 'ầ':
                    s += String.fromCharCode(71);
                    break;
                case 'ẩ':
                    s += String.fromCharCode(72);
                    break;
                case 'ẫ':
                    s += String.fromCharCode(73);
                    break;
                case 'ấ':
                    s += String.fromCharCode(74);
                    break;
                case 'ậ':
                    s += String.fromCharCode(75);
                    break;
                case 'b':
                    s += String.fromCharCode(76);
                    break;
                case'B':
                    s += String.fromCharCode(76);
                    break;
                case 'c':
                    s += String.fromCharCode(77);
                    break;
                case 'C':
                    s += String.fromCharCode(77);
                    break;
                case 'd':
                    s += String.fromCharCode(78);
                    break;
                case 'D':
                    s += String.fromCharCode(78);
                    break;
                case 'đ':
                    s += String.fromCharCode(79);
                    break;
                case 'Đ':
                    s += String.fromCharCode(79);
                    break;
                case 'e':
                    s += String.fromCharCode(80);
                    break;
                case 'E':
                    s += String.fromCharCode(80);
                    break;
                case 'è':
                    s += String.fromCharCode(81);
                    break;
                case 'ẻ':
                    s += String.fromCharCode(82);
                    break;
                case 'ẽ':
                    s += String.fromCharCode(83);
                    break;
                case 'é':
                    s += String.fromCharCode(84);
                    break;
                case 'ẹ':
                    s += String.fromCharCode(85);
                    break;
                case 'ê':
                    s += String.fromCharCode(86);
                    break;
                case 'Ê':
                    s += String.fromCharCode(86);
                    break;
                case 'ề':
                    s += String.fromCharCode(87);
                    break;
                case 'ể':
                    s += String.fromCharCode(88);
                    break;
                case 'ễ':
                    s += String.fromCharCode(89);
                    break;
                case 'ế':
                    s += String.fromCharCode(90);
                    break;
                case 'ệ':
                    s += String.fromCharCode(91);
                    break;
                case 'f':
                    s += String.fromCharCode(92);
                    break;
                case 'F':
                    s += String.fromCharCode(92);
                    break;
                case 'g':
                    s += String.fromCharCode(93);
                    break;
                case 'G':
                    s += String.fromCharCode(93);
                    break;
                case 'h':
                    s += String.fromCharCode(94);
                    break;
                case 'H':
                    s += String.fromCharCode(94);
                    break;
                case 'i':
                    s += String.fromCharCode(95);
                    break;
                case 'I':
                    s += String.fromCharCode(95);
                    break;
                case 'ì':
                    s += String.fromCharCode(97);
                    break;
                case 'ỉ':
                    s += String.fromCharCode(98);
                    break;
                case 'ĩ':
                    s += String.fromCharCode(99);
                    break;
                case 'í':
                    s += String.fromCharCode(100);
                    break;
                case 'ị':
                    s += String.fromCharCode(101);
                    break;
                case 'j':
                    s += String.fromCharCode(102);
                    break;
                case 'J':
                    s += String.fromCharCode(102);
                    break;
                case 'k':
                    s += String.fromCharCode(103);
                    break;
                case 'K':
                    s += String.fromCharCode(103);
                    break;
                case 'l':
                    s += String.fromCharCode(104);
                    break;
                case 'L':
                    s += String.fromCharCode(104);
                    break;
                case 'm':
                    s += String.fromCharCode(105);
                    break;
                case 'M':
                    s += String.fromCharCode(105);
                    break;
                case 'n':
                    s += String.fromCharCode(106);
                    break;
                case 'N':
                    s += String.fromCharCode(106);
                    break;
                case 'o':
                    s += String.fromCharCode(107);
                    break;
                case 'O':
                    s += String.fromCharCode(107);
                    break;
                case 'ò':
                    s += String.fromCharCode(108);
                    break;
                case 'ỏ':
                    s += String.fromCharCode(109);
                    break;
                case 'õ':
                    s += String.fromCharCode(110);
                    break;
                case 'ó':
                    s += String.fromCharCode(111);
                    break;
                case 'ọ':
                    s += String.fromCharCode(112);
                    break;
                case 'ô':
                    s += String.fromCharCode(113);
                    break;
                case 'Ô':
                    s += String.fromCharCode(113);
                    break;
                case 'ồ':
                    s += String.fromCharCode(114);
                    break;
                case 'ổ':
                    s += String.fromCharCode(115);
                    break;
                case 'ỗ':
                    s += String.fromCharCode(116);
                    break;
                case 'ố':
                    s += String.fromCharCode(117);
                    break;
                case 'ộ':
                    s += String.fromCharCode(118);
                    break;
                case 'p':
                    s += String.fromCharCode(119);
                    break;
                case 'P':
                    s += String.fromCharCode(119);
                    break;
                case 'q':
                    s += String.fromCharCode(120);
                    break;
                case 'Q':
                    s += String.fromCharCode(120);
                    break;
                case 'r':
                    s += String.fromCharCode(121);
                    break;
                case 'R':
                    s += String.fromCharCode(121);
                    break;
                case 's':
                    s += String.fromCharCode(122);
                    break;
                case 'S':
                    s += String.fromCharCode(122);
                    break;
                case 't':
                    s += String.fromCharCode(123);
                    break;
                case 'T':
                    s += String.fromCharCode(123);
                    break;
                case 'u':
                    s += String.fromCharCode(126);
                    break;
                case 'U':
                    s += String.fromCharCode(126);
                    break;
                case 'ù':
                    s += String.fromCharCode(127);
                    break;
                case 'ủ':
                    s += String.fromCharCode(128);
                    break;
                case 'ũ':
                    s += String.fromCharCode(129);
                    break;
                case 'ú':
                    s += String.fromCharCode(130);
                    break;
                case 'ụ':
                    s += String.fromCharCode(131);
                    break;
                case 'ư':
                    s += String.fromCharCode(132);
                    break;
                case 'Ư':
                    s += String.fromCharCode(132);
                    break;
                case 'ừ':
                    s += String.fromCharCode(133);
                    break;
                case 'ử':
                    s += String.fromCharCode(134);
                    break;
                case 'ữ':
                    s += String.fromCharCode(135);
                    break;
                case 'ứ':
                    s += String.fromCharCode(136);
                    break;
                case 'ự':
                    s += String.fromCharCode(137);
                    break;
                case 'v':
                    s += String.fromCharCode(138);
                    break;
                case 'V':
                    s += String.fromCharCode(138);
                    break;
                case 'w':
                    s += String.fromCharCode(139);
                    break;
                case 'W':
                    s += String.fromCharCode(139);
                    break;
                case 'x':
                    s += String.fromCharCode(140);
                    break;
                case 'X':
                    s += String.fromCharCode(140);
                    break;
                case 'y':
                    s += String.fromCharCode(141);
                    break;
                case 'Y':
                    s += String.fromCharCode(141);
                    break;
                case 'ỳ':
                    s += String.fromCharCode(142);
                    break;
                case 'ỷ':
                    s += String.fromCharCode(143);
                    break;
                case 'ỹ':
                    s += String.fromCharCode(144);
                    break;
                case 'ý':
                    s += String.fromCharCode(145);
                    break;
                case 'ỵ':
                    s += String.fromCharCode(146);
                    break;
                case 'z':
                    s += String.fromCharCode(147);
                    break;
                case 'Z':
                    s += String.fromCharCode(147);
                    break;
                default:
                    s += sValue[i];
            }
        }
        return s;
    }
});