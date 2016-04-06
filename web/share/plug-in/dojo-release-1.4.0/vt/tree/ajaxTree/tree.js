/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
dojo.provide("vt.tree.ajaxTree.tree");
dojo.require("vt.tree.checkboxTree.CheckBoxTree");
dojo.require("vt.tree.ajaxTree.treeNode");

dojo.declare("vt.tree.ajaxTree.tree",vt.tree.checkboxTree.CheckBoxTree, {
    //[ LongH @10May11
    //[ TopUrl
    vtGetTopUrlForm : function() {
        var store = this.vtGetStore();
        return store.vtTopUrlForm;
    },

    vtGetTopUrlParam : function() {
        var store = this.vtGetStore();
        return store.vtTopUrlParam;
    },

    vtGetTopUrl : function() {
        var store = this.vtGetStore();
        return store.store.url;
    },

    vtSetTopUrlForm : function(/*String*/ formId) {
        var store = this.vtGetStore();
        store.vtTopUrlForm = formId;
    },

    vtSetTopUrlParam : function(/*Object*/ param) {
        var store = this.vtGetStore();
        store.vtTopUrlParam = param;
    },

    vtSetTopUrl : function(/*String*/ newUrl) {
        var store = this.vtGetStore();
        store.url = newUrl;
    },
    //] TopUrl

    //[ ChildrenUrl
    vtGetChildrenUrlForm : function() {
        var store = this.vtGetStore();
        return store.vtChildrenUrlForm;
    },

    vtGetChildrenUrlParam : function() {
        var store = this.vtGetStore();
        return store.vtChildrenUrlParam;
    },

    vtGetChildrenUrl : function() {
        var store = this.vtGetStore();
        return store.getChildrenUrl;
    },

    vtSetChildrenUrlForm : function(/*String*/ formId) {
        var store = this.vtGetStore();
        store.vtChildrenUrlForm = formId;
    },

    vtSetChildrenUrlParam : function(/*Object*/ param) {
        var store = this.vtGetStore();
        store.vtChildrenUrlParam = param;
    },

    vtSetChildrenUrl : function(/*String*/ newUrl) {
        var store = this.vtGetStore();
        store.getChildrenUrl = newUrl;
    },
    //] ChildrenUrl

    vtGetModel : function() {
        return this.model;
    },

    vtGetStore : function() {
        return this.vtGetModel().store;
    },

    //[ Collapse node methods
    vtForceCollapseNodesByItem : function(/*Var*/ idOrItem) {
        var i,
        targetNodes;

        targetNodes = this.vtGetNodesByItem(idOrItem);
        for(i = 0; i < targetNodes.length; i++) {
            this.vtForceCollapseNode(targetNodes[i]);
        }

        if(targetNodes.length) {
            return targetNodes.length
        } else {
            return -1;
        }
    },

    vtForceCollapseRoot : function() {
        return this.vtForceCollapseNode(this.vtGetRootOrFirstNode());
    },

    vtForceCollapseNode : function(/*_TreeNode*/ node) {
        if(!node) {
            alert("vtForceCollapseNode says: \nNode is not existed.");
            return undefined;
        }
        return this._collapseNode(node, false);
    },
    //] Collapse node methods

    //[ Expand node methods
    vtForceExpandNodesByItem : function(/*Var*/ idOrItem) {
        var i,
        targetNodes;

        targetNodes = this.vtGetNodesByItem(idOrItem);
        for(i = 0; i < targetNodes.length; i++) {
            this.vtForceExpandNodeRecursive(targetNodes[i]);
        }

        if(targetNodes.length) {
            return targetNodes.length
        } else {
            return -1;
        }
    },

    vtForceExpandRoot : function() {
        return this.vtForceExpandNode(this.vtGetRootOrFirstNode());
    },

    vtForceExpandNode : function(/*_TreeNode*/ node) {
        if(!node) {
            alert("vtForceExpandNode says: \nNode is not existed.");
            return undefined;
        }
        return this._expandNode(node, false);
    },

    vtForceExpandNodeRecursive : function(/*_TreeNode*/ node) {
        try {
            if(!node) {
                alert("vtForceExpandNodesByItem says: \nNode is not existed.");
                return;
            }

            var i;

            if(this.vtGetModel().vtDelayLoading === true) {
                //[ LongH says: check whether 'node' is visible, if yes then expand node itself else show the notice & cancel the recursive
                if(node.getParent() && node.getParent().isExpanded == true) {
                    this.vtForceExpandNode(node);
                } else {
                    alert(
                        "vtForceExpandNodesByItem says: \n" +
                        "The node needs to be expanded is invisible, CANNOT expand an invisible node in delayLoading-mode.");
                }
                //] LongH says: check whether 'node' is visible
                return;
            }
            else {
                var parents = node.vtGetParents();
                for(i = 0; i < parents.length; i++) {
                    this.vtForceExpandNode(parents[i]);
                }
                this.vtForceExpandNode(node);
            }
            
        } catch(e) {
            alert("vtForceExpandNodeRecursive JSEXP: " + e.message);
        }
    },
    //] Expand node methods

    //[ State checking methods
    vtGetNodeExpandedStateByItem : function(/*Var*/ idOrItem) {
        var targetNodes;

        targetNodes = this.vtGetNodesByItem(idOrItem);
        if(targetNodes.length > 0){
            return this.vtGetNodeExpandedState(targetNodes[0]);
        } else {
            console.log("vtGetNodeExpandedStateByItem says: cannot find node match with the item.");
            return undefined;
        }
    },

    vtGetNodeExpandedState : function(/*_TreeNode*/ node) {
        if(!node) {
            alert("vtGetNodeExpandedState says: \nNode is not existed.");
            return undefined;
        }
        return node.isExpanded;
    },
    //] State checking methods

    vtGetNodesByItem : function(/*Item or Id*/ idOrItem) {
        return this.getNodesByItem(idOrItem);
    },

    vtGetRootOrFirstNode : function() {
        return this._getRootOrFirstNode();
    },
    //] LongH @10May11

    _createTreeNode: function(/*Object*/ args){
        // summary:
        //		creates a TreeNode
        // description:
        //		Developers can override this method to define their own TreeNode class;
        //		However it will probably be removed in a future release in favor of a way
        //		of just specifying a widget for the label, rather than one that contains
        //		the children too.
        //        return new dijit._TreeNode(args);
        return new vt.tree.ajaxTree.treeNode(args);
    },
    
    refresh: function(/*dojo.data.Item*/ parentItem,/*String*/ url){
        // summary:
        // 		Calls onComplete() with array of child items of given parent item, all loaded.
        this.model.refresh(parentItem,url);
    },

    // LongH says: modified to apply delayLoading-mode @13May11
    _expandNode: function(/*_TreeNode*/ node, /*Boolean?*/ recursive){
        // summary:
        //		Called when the user has requested to expand the node
        // recursive:
        //		Internal flag used when _expandNode() calls itself, don't set.
        // returns:
        //		Deferred that fires when the node is loaded and opened and (if persist=true) all it's descendants
        //		that were previously opened too

        if(node._expandNodeDeferred && !recursive){
            // there's already an expand in progress (or completed), so just return
            return node._expandNodeDeferred;	// dojo.Deferred
        }
        
        var model = this.model,
        item = node.item,
        _this = this;
        
        //[ LongH says: check time offset to reload children or not
        var isRoot = (item === model.root) ? true : false;
        var delayLoading = model.vtDelayLoading;
        var delayTime; // LongH says: in secs
        
        if(delayLoading) {
            if(isRoot) {
                delayTime = model.vtDelayLoadTopLevelTime;
            } else {
                delayTime = model.vtDelayLoadChildrenTime;
            }

            if(!delayTime) {
                delayTime = model.vtDelayDefautLoadingTime;
            }

            if(
                !recursive && item["vtLastLoadTime"] && 
                ((new Date()).getTime() - item["vtLastLoadTime"][0] > delayTime * 1000 )
                ) {
                node.state = "UNCHECKED";
            }
        }
        //] LongH

        switch(node.state){
            case "UNCHECKED":
                // need to load all the children, and then expand
                node.markProcessing();

                // Setup deferred to signal when the load and expand are finished.
                // Save that deferred in this._expandDeferred as a flag that operation is in progress.
                var def = (node._expandNodeDeferred = new dojo.Deferred());

                // Get the children
                model.getChildren(
                    item,
                    function(items){
                        node.unmarkProcessing();

                        //[ LongH says: used for lazyloading
                        item["vtLastLoadTime"] = [(new Date()).getTime()];
                        //] LongH says: used for lazyloading

                        // Display the children and also start expanding any children that were previously expanded
                        // (if this.persist == true).   The returned Deferred will fire when those expansions finish.
                        var scid = node.setChildItems(items);

                        // Call _expandNode() again but this time it will just to do the animation (default branch).
                        // The returned Deferred will fire when the animation completes.
                        // TODO: seems like I can avoid recursion and just use a deferred to sequence the events?
                        var ed = _this._expandNode(node, true);

                        // After the above two tasks (setChildItems() and recursive _expandNode()) finish,
                        // signal that I am done.
                        scid.addCallback(function(){
                            ed.addCallback(function(){
                                def.callback();
                            })
                        });
                    },
                    function(err){
                        console.error(_this, ": error loading root children: ", err);
                    }
                    );
                break;

            default:	// "LOADED"
                // data is already loaded; just expand node
                def = (node._expandNodeDeferred = node.expand());

                this.onOpen(node.item, node);

                if(item){
                    this._state(item, true);
                    this._saveState();
                }
        }

        return def;	// dojo.Deferred
    },

    deleteChildren:function(/*dojo.data.Item*/ parentItem) {
        this.model.deleteChildren(parentItem);
    },
    
    deleteChild:function(/*TreeNode*/ node) {
        node.removeChild(node);
    }
});