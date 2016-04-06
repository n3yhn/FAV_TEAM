/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
dojo.provide("vt.tree.ajaxTree.treeModel");
//dojo.require("dijit.tree.ForestStoreModel");
dojo.require("vt.tree.checkboxTree.CheckBoxStoreModel");

dojo.declare("vt.tree.ajaxTree.treeModel",vt.tree.checkboxTree.CheckBoxStoreModel, {
    vtDelayLoading : true,
    vtDelayLoadChildrenTime : 3, // In secs
    vtDelayLoadTopLevelTime : 10, // In secs
    vtDelayDefautLoadingTime : 10, // In secs, used when the above are not set

    mayHaveChildren: function(/*dojo.data.Item*/ item){
        // summary:
        //		Tells if an item has or may have children.  Implementing logic here
        //		avoids showing +/- expando icon for nodes that we know don't have children.
        //		(For efficiency reasons we may not want to check if an element actually
        //		has children until user clicks the expando node)
        // tags:
        //		extension

        // [ Datbt
        //              using user attr of item named mayHaveChildren
        // [ Datbt
        if (item["mayHaveChildren"]== undefined ||item["mayHaveChildren"]== ""){
            // [ Datbt
            // orginal, i don't know it
            return item === this.root || this.inherited(arguments);
        // [ Datbt
        }
        else {
            return (item["mayHaveChildren"] == "true")?true:false;
        }
    },

    /**
     * Author: LongH
     * Description: Thay đổi cơ chế lấy children
     * Date: 27/05/2011
     * FWVersion: 3.3
     **/
    getChildren: function(/*dojo.data.Item*/ parentItem, /*function(items)*/ callback, /*function*/ onError){
        // summary:
        // 		Calls onComplete() with array of child items of given parent item, all loaded.
        //
        var store = this.store;
        var _this = this;

        if(parentItem === this.root){
            //[ LongH says: if delayLoading is enabled, force reload root else do the old fashion
            //[ New behavior
            if(this.vtDelayLoading) {
                store._arrayOfAllItems = [];
                store._arrayOfTopLevelItems = [];
                store._loadFinished = false;
                store._itemsByIdentity = null;
                store._loadInProgress = false;
                store._queuedFetches = [];

                this.store.fetch({
                    query: this.query,
                    onComplete: dojo.hitch(this, function(items){
                        items.vtIsAllOverrided = true; // by LongH
                        this.root.children = items;
                        callback(items);
                    }),
                    onError: onError
                });
            }
            //] New behavior
            //[ Old behavior
            else {
                if(this.root.children){
                    // already loaded, just return
                    callback(this.root.children);
                }else{
                    this.store.fetch({
                        query: this.query,
                        onComplete: dojo.hitch(this, function(items){
                            this.root.children = items;
                            callback(items);
                        }),
                        onError: onError
                    });

                }
            }
        //] Old behavior
        //] LongH says: if delayLoading is enabled, force reload root else do the old fashion
            
        }else{
            //[ Datbt
            //this.inherited(arguments);
            //] Datbt

            //[ Datbt. Mod by LongH @10May11
            try {
                var postArgs = {
                    url: store.getChildrenUrl,
                    handleAs: "json-comment-optional",
                    preventCache: _this.urlPreventCache
                };

                if(store.vtChildrenUrlForm && store.vtChildrenUrlForm.length > 0) {
                    postArgs.form = dojo.byId(store.vtChildrenUrlForm);
                }

                if(store.vtChildrenUrlParam) {
                    postArgs.content = store.vtChildrenUrlParam;
                } else {
                    postArgs.content = {};
                }

                postArgs.content["parentItemId"] = parentItem.id; // LongH says: !important

                var getHandler = dojo.xhrPost(postArgs); // by LongH

                getHandler.addCallback(function(data){
                    try{
                        // [ Datbt
                        // insert children for parentItem
                        
                        //[ by LongH
                        var i, pInfo;
                        
                        if(_this.vtDelayLoading) {
                            //store.deleteChildItemsRecursive(parentItem);
                            _this.deleteChildren(parentItem);

                            parentItem.children = [];
                            
                            for(i = 0; i < data.length; i++){
                                pInfo = {
                                    parent: parentItem,
                                    attribute: _this.childrenAttrs[0]
                                };
                                data[i].vtIsOverrided = true; // by LongH
                                store.newItemNoNotification(data[i], pInfo);
                            }
                            parentItem.children.vtIsAllOverrided = true;

                        //[ LongH says: drop null-member from store._arrayOfAllItems, not used
                        /*
                            var item, newArrayOfAllItems = [];
                            for(i = 0; i < store._arrayOfAllItems.length; i++) {
                                item = store._arrayOfAllItems[i];
                                if(item) {
                                    newArrayOfAllItems[i] = item;
                                }
                            }
                            store._arrayOfAllItems = newArrayOfAllItems;
                            */
                        //] LongH says: drop null-member from store._arrayOfAllItems, not used
                        }
                        else {
                            parentItem.children = [];
                            for (i =0; i < data.length; i ++){
                                pInfo = {
                                    parent: parentItem,
                                    attribute: _this.childrenAttrs[0]
                                };
                                store.newItemNoNotification(data[i], pInfo);
                            }
                        }
                        //] by LongH
                        // ] Datbt
                        try {
                            callback(parentItem.children);
                        } catch(e) {
                            console.log("abc: " + e.message);
                        }
                    }catch(e){
                        console.debug("getChildren, getHandler.callback, error: " + e.message );
                    }
                });
            } catch(e) {
                alert("Error in treeModel, getChildren[when parent is not root]\n" + e.message);
            }
        //] Datbt. Mod by LongH @10May11
        }
    },
    deleteChildren:function(/*dojo.data.Item*/ parentItem) {
        this.getChkChildren( parentItem, dojo.hitch(this,
            function(children) {
                dojo.forEach( children, function( child ) {
                    if( this.mayHaveChkChildren( child )) {
                        this.deleteChildren(child);
                    }
                    this.store.deleteItem(child);

                }, this);
            }),
        function(err) {
            console.error(this, ": deleteChildren: ", err);
        }
        );
    },
    refresh: function(/*dojo.data.Item*/ parentItem,/*String*/ url){
        // summary:
        // 		Calls onComplete() with array of child items of given parent item, all loaded.
        this.deleteChildren(parentItem);
        var store = this.store;
        var _this = this;
        if (url.indexOf("?") != -1){
            url = url + "&parentItemId=" + parentItem.id;
        }
        else{
            url = url + "?parentItemId=" + parentItem.id;
        }
        var getArgs = {
            url: url,
            handleAs: "json-comment-optional",
            preventCache: _this.urlPreventCache
        };
        var getHandler = dojo.xhrGet(getArgs);
        getHandler.addCallback(function(data){
            //            try{
            //                // insert children for parentItem
            //                parentItem.children = [];
            //                for (var i =0; i < data.length; i ++){
            //                    var pInfo = {
            //                        parent: parentItem,
            //                        attribute: _this.childrenAttrs[0]
            //                    };
            //                    _this.store.newItemNoNotification(data[i], pInfo);
            //                }
            //            }catch(e){
            //                console.debug("refresh, getHandler.callback, error: " + e.message );
            //            }
            this.tree.setChildItems(data);
        });
    // ] Datbt
        
    }

});