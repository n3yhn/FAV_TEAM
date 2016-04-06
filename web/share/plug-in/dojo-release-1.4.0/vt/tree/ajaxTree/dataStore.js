/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

dojo.provide("vt.tree.ajaxTree.dataStore");
dojo.require("dojo.data.ItemFileWriteStore");

dojo.declare("vt.tree.ajaxTree.dataStore", dojo.data.ItemFileWriteStore, {
    getChildrenUrl : "",

    vtTopUrlForm : "",
    vtTopUrlParam : {},

    vtChildrenUrlForm : "",
    vtChildrenUrlParam : {},
    
    constructor: function(/* object */ keywordParameters){
        //	keywordParameters: {typeMap: object)
        //		The structure of the typeMap object is as follows:
        //		{
        //			type0: function || object,
        //			type1: function || object,
        //			...
        //			typeN: function || object
        //		}
        //		Where if it is a function, it is assumed to be an object constructor that takes the
        //		value of _value as the initialization parameters.  It is serialized assuming object.toString()
        //		serialization.  If it is an object, then it is assumed
        //		to be an object of general form:
        //		{
        //			type: function, //constructor.
        //			deserialize:	function(value) //The function that parses the value and constructs the object defined by type appropriately.
        //			serialize:	function(object) //The function that converts the object back into the proper file format form.
        //		}

        // [ Datbt
        // add attrs getChildrenUrl
        this.getChildrenUrl = keywordParameters.getChildrenUrl;
        // ] Datbt

        //[ LongH @10May11
        this.vtTopUrlForm = keywordParameters.vtTopUrlForm;
        this.vtTopUrlParam = keywordParameters.vtTopUrlParam;

        this.vtChildrenUrlForm = keywordParameters.vtChildrenUrlForm;
        this.vtChildrenUrlParam = keywordParameters.vtChildrenUrlParam;
        //] LongH @10May11

        // ItemFileWriteStore extends ItemFileReadStore to implement these additional dojo.data APIs
        this._features['dojo.data.api.Write'] = true;
        this._features['dojo.data.api.Notification'] = true;

        // For keeping track of changes so that we can implement isDirty and revert
        this._pending = {
            _newItems:{},
            _modifiedItems:{},
            _deletedItems:{}
        };

        if(!this._datatypeMap['Date'].serialize){
            this._datatypeMap['Date'].serialize = function(obj){
                return dojo.date.stamp.toISOString(obj, {
                    zulu:true
                });
            };
        }
        //Disable only if explicitly set to false.
        if(keywordParameters && (keywordParameters.referenceIntegrity === false)){
            this.referenceIntegrity = false;
        }

        // this._saveInProgress is set to true, briefly, from when save() is first called to when it completes
        this._saveInProgress = false;
    },
    
    newItemNoNotification: function(/* Object? */ keywordArgs, /* Object? */ parentInfo){
        // summary: See dojo.data.api.Write.newItem()
        var oldItem;
        this._assert(!this._saveInProgress);

        if(!this._loadFinished){
            // We need to do this here so that we'll be able to find out what
            // identifierAttribute was specified in the data file.
            //this._forceLoad();
            console.log("do force load");
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
                
        //[ LongH says: overide current object which assoc with this id
        try {
            if(this._itemsByIdentity){
                this._assert(typeof this._itemsByIdentity[newIdentity] === "undefined");
            }
            this._assert(typeof this._pending._newItems[newIdentity] === "undefined");
            this._assert(typeof this._pending._deletedItems[newIdentity] === "undefined");
        } catch(e) {
            if(keywordArgs["vtIsOverrided"] === true) {
                console.log(["Force override item by LongH: ", newIdentity, e.message]);
            } else {
                throw e;
            }
            
        }
        //] LongH says: overide current object which assoc with this id
        
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
        // [ Datbt
        //console.debug("newItem, this.onNew: " + this.onNew);
        //this.onNew(newItem, pInfo); // dojo.data.api.Notification call
        // ] Datbt
        
        return newItem; // item
        
    },

    deleteItem: function(/* item */ item){
        // summary: See dojo.data.api.Write.deleteItem()
        this._assert(!this._saveInProgress);
        
        this._assertIsItem(item);

        // Remove this item from the _arrayOfAllItems, but leave a null value in place
        // of the item, so as not to change the length of the array, so that in newItem()
        // we can still safely do: newIdentity = this._arrayOfAllItems.length;
        var indexInArrayOfAllItems = item[this._itemNumPropName];
        var identity = this.getIdentity(item);

        //If we have reference integrity on, we need to do reference cleanup for the deleted item
        
        if(this.referenceIntegrity){
            //First scan all the attributes of this items for references and clean them up in the map
            //As this item is going away, no need to track its references anymore.

            //Get the attributes list before we generate the backup so it
            //doesn't pollute the attributes list.
            var attributes = this.getAttributes(item);

            //Backup the map, we'll have to restore it potentially, in a revert.
            if(item[this._reverseRefMap]){
                item["backup_" + this._reverseRefMap] = dojo.clone(item[this._reverseRefMap]);
            }

            //TODO:  This causes a reversion problem.  This list won't be restored on revert since it is
            //attached to the 'value'. item, not ours.  Need to back tese up somehow too.
            //Maybe build a map of the backup of the entries and attach it to the deleted item to be restored
            //later.  Or just record them and call _addReferenceToMap on them in revert.
            dojo.forEach(attributes, function(attribute){
                dojo.forEach(this.getValues(item, attribute), function(value){
                    if(this.isItem(value)){
                        //We have to back up all the references we had to others so they can be restored on a revert.
                        if(!item["backupRefs_" + this._reverseRefMap]){
                            item["backupRefs_" + this._reverseRefMap] = [];
                        }
                        item["backupRefs_" + this._reverseRefMap].push({
                            id: this.getIdentity(value),
                            attr: attribute
                        });
                        this._removeReferenceFromMap(value, item, attribute);
                    }
                }, this);
            }, this);

            //Next, see if we have references to this item, if we do, we have to clean them up too.
            var references = item[this._reverseRefMap];
            if(references){
                //Look through all the items noted as references to clean them up.
                for(var itemId in references){
                    var containingItem = null;
                    if(this._itemsByIdentity){
                        containingItem = this._itemsByIdentity[itemId];
                    }else{
                        containingItem = this._arrayOfAllItems[itemId];
                    }
                    //We have a reference to a containing item, now we have to process the
                    //attributes and clear all references to the item being deleted.
                    
                    if(containingItem){
                        for(var attribute in references[itemId]){
                            var oldValues = this.getValues(containingItem, attribute) || [];
                            
                            
                            var newValues = dojo.filter(oldValues, function(possibleItem){
                                return !(this.isItem(possibleItem) && this.getIdentity(possibleItem) == identity);
                            }, this);
                            
                            //Remove the note of the reference to the item and set the values on the modified attribute.
                            this._removeReferenceFromMap(item, containingItem, attribute);
                            /*
                            if(newValues.length < oldValues.length){
                                this._setValueOrValues(containingItem, attribute, newValues, true);
                            }
                            */
                           
                            //[ LongH
                            if(!item.vtIsOverrided[0] && newValues.length < oldValues.length){
                                this._setValueOrValues(containingItem, attribute, newValues, true);
                            }
                            //] LongH
                            
                        }
                    }
                    
                }
            }
        }
        
        //console.log([indexInArrayOfAllItems, item.id, item.name, this._removeArrayElement(this._arrayOfAllItems, item)]);
        
        //this._arrayOfAllItems[indexInArrayOfAllItems] = null;
        delete this._arrayOfAllItems[indexInArrayOfAllItems]; // by LongH

        item[this._storeRefPropName] = null;
        if(this._itemsByIdentity){
            delete this._itemsByIdentity[identity];
        }
        this._pending._deletedItems[identity] = item;

        //Remove from the toplevel items, if necessary...
        if(item[this._rootItemPropName]){
            this._removeArrayElement(this._arrayOfTopLevelItems, item);
        }
        this.onDelete(item); // dojo.data.api.Notification call
        
        return true;
    },

    // LongH says: to destroy all child items when being in delayLoading mode
    deleteChildItemsRecursive : function(/*dojo.data.Item*/ parentItem) {
        var i, childItem,
            str = "";
            
        if(parentItem.children && parentItem.children.length > 0) {
            for(i = 0; i < parentItem.children.length; i++) {
                childItem = parentItem.children[i];
                if(childItem.children && childItem.children.length > 0) {
                    this.deleteChildItemsRecursive(childItem);
                }
                else {
                    this.deleteItem(childItem);
                    str += "\ndelete: " + childItem.id;
                }
            }
            console.log(str);
        }
    },

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
                    
                    //[ LongH says: change method type & add additional parameters @10May11
                    var postArgs = {
                        url: self._jsonFileUrl,
                        handleAs: "json-comment-optional",
                        preventCache: this.urlPreventCache,
                        failOk: this.failOk
                    };

                    if(this.vtTopUrlForm && this.vtTopUrlForm.length > 0) {
                        postArgs.form = dojo.byId(this.vtTopUrlForm);
                    }

                    if(this.vtTopUrlParam) {
                        postArgs.content = this.vtTopUrlParam;
                    }

                    var getHandler = dojo.xhrPost(postArgs);
                    //] LongH says: change method & add additional parameters @10May11

                    getHandler.addCallback(function(data){
                        try{
                            self._getItemsFromLoadedData(data);
                            self._loadFinished = true;
                            self._loadInProgress = false;

                            filter(keywordArgs, self._getItemsArray(keywordArgs.queryOptions));
                            self._handleQueuedFetches();
                        }catch(e){
                            self._loadFinished = true;
                            self._loadInProgress = false;
                            errorCallback(e, keywordArgs);
                        }
                    });
                    getHandler.addErrback(function(error){
                        self._loadInProgress = false;
                        errorCallback(error, keywordArgs);
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

    _forceLoad: function(){
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
            var getArgs = {
                url: this._jsonFileUrl,
                handleAs: "json-comment-optional",
                preventCache: this.urlPreventCache,
                failOk: this.failOk,
                sync: true
            };
            var getHandler = dojo.xhrGet(getArgs);
            getHandler.addCallback(function(data){
                try{
                    //Check to be sure there wasn't another load going on concurrently
                    //So we don't clobber data that comes in on it.  If there is a load going on
                    //then do not save this data.  It will potentially clobber current data.
                    //We mainly wanted to sync/wait here.
                    //TODO:  Revisit the loading scheme of this store to improve multi-initial
                    //request handling.
                    if(self._loadInProgress !== true && !self._loadFinished){
                        self._getItemsFromLoadedData(data);
                        self._loadFinished = true;
                    }else if(self._loadInProgress){
                        //Okay, we hit an error state we can't recover from.  A forced load occurred
                        //while an async load was occurring.  Since we cannot block at this point, the best
                        //that can be managed is to throw an error.
                        throw new Error("dojo.data.ItemFileReadStore:  Unable to perform a synchronous load, an async load is in progress.");
                    }
                }catch(e){
                    console.log(e);
                    throw e;
                }
            });
            getHandler.addErrback(function(error){
                throw error;
            });
        }else if(this._jsonData){
            self._getItemsFromLoadedData(self._jsonData);
            self._jsonData = null;
            self._loadFinished = true;
        }
    }
});