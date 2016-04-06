/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

dojo.provide("vt.selectBox.dataStore");
dojo.require("dojo.data.ItemFileReadStore");

dojo.declare("vt.selectBox.dataStore", dojo.data.ItemFileReadStore, {
    _forceLoadByPost: function(formId,param, callback){
        //	summary:
        //		Internal function to force a load of the store if it hasn't occurred yet.  This is required
        //		for specific functions to work properly.
        var self = this;
        if(this._jsonFileUrl){
            var getArgs = {
                url: self._jsonFileUrl,
                handleAs: "json-comment-optional",
                preventCache: true,
                sync: true,
                form:formId,
                content:param
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
                    if (callback){
                        callback(data,true);
                    }
                // ] Datbt
                }catch(e){
                    // [ Datbt
                    if (callback){
                        callback(data,false);
                    }
                    // ] Datbt
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