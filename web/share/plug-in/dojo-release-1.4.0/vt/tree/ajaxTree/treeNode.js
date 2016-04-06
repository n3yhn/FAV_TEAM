/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
dojo.provide("vt.tree.ajaxTree.treeNode");
dojo.require("vt.tree.checkboxTree._CheckBoxTreeNode");

dojo.declare("vt.tree.ajaxTree.treeNode",vt.tree.checkboxTree._CheckBoxTreeNode, {

    vtGetParents : function() {
        try {
        var outArr = [];
        this.vtGetParentRecursive(outArr);
        return outArr.reverse();
        } catch(e) {
            alert("LongH: " + e.message);
        }
    },

    vtGetParentRecursive : function(/*&Array*/ outArr) {
        var parent = this.getParent();
        if(parent) {
            outArr.push(parent);
            parent.vtGetParentRecursive(outArr);
        }
    },

    setChildItems: function(/* Object[] */ items){
        // summary:
        //		Sets the child items of this node, removing/adding nodes
        //		from current children to match specified items[] array.
        //		Also, if this.persist == true, expands any children that were previously
        // 		opened.
        // returns:
        //		Deferred object that fires after all previously opened children
        //		have been expanded again (or fires instantly if there are no such children).

        var tree = this.tree,
        model = tree.model,
        defs = [];	// list of deferreds that need to fire before I am complete


        // Orphan all my existing children.
        // If items contains some of the same items as before then we will reattach them.
        // Don't call this.removeChild() because that will collapse the tree etc.
        this.getChildren().forEach(function(child){
            dijit._Container.prototype.removeChild.call(this, child);
        }, this);

        this.state = "LOADED";

        if(items && items.length > 0){
            this.isExpandable = true;

            // Create _TreeNode widget for each specified tree node, unless one already
            // exists and isn't being used (presumably it's from a DnD move and was recently
            // released

            //[ LongH says: check to force add new child nodes
            //[ New behavior
            if(items.vtIsAllOverrided === true) {
                dojo.forEach(items, function(item){
                    var id = model.getIdentity(item),
                    node;

                    node = this.tree._createTreeNode({
                        item: item,
                        tree: tree,
                        isExpandable: model.mayHaveChildren(item),
                        label: tree.getLabel(item),
                        tooltip: tree.getTooltip(item),
                        indent: this.indent + 1
                    });

                    tree._itemNodesMap[id] = [node];
                    
                    this.addChild(node);

                    // If node was previously opened then open it again now (this may trigger
                    // more data store accesses, recursively)

                    if(this.tree.autoExpand || this.tree._state(item)){
                        defs.push(tree._expandNode(node));
                    }

                }, this);
            }
            //] New behavior
            //[ Original behavior
            else {
                dojo.forEach(items, function(item){
                    var id = model.getIdentity(item),
                    existingNodes = tree._itemNodesMap[id],
                    node;
                    
                    if(existingNodes){
                        for(var i=0;i<existingNodes.length;i++){
                            if(existingNodes[i] && !existingNodes[i].getParent()){
                                node = existingNodes[i];
                                node.attr('indent', this.indent+1);
                                break;
                            }
                        }
                    }

                    if(!node){
                        node = this.tree._createTreeNode({
                            item: item,
                            tree: tree,
                            isExpandable: model.mayHaveChildren(item),
                            label: tree.getLabel(item),
                            tooltip: tree.getTooltip(item),
                            indent: this.indent + 1
                        });
                        if(existingNodes){
                            existingNodes.push(node);
                        }else{
                            tree._itemNodesMap[id] = [node];
                        }
                    }
                
                    this.addChild(node);

                    // If node was previously opened then open it again now (this may trigger
                    // more data store accesses, recursively)

                    if(this.tree.autoExpand || this.tree._state(item)){
                        defs.push(tree._expandNode(node));
                    }

                }, this);
            }
            //] Original behavior
            //] LongH says: check to force add new child nodes

            // note that updateLayout() needs to be called on each child after
            // _all_ the children exist
            dojo.forEach(this.getChildren(), function(child, idx){
                child._updateLayout();
            });
        // ] Datbt
        }else{
            // [ Datbt
            if (this.item["mayHaveChildren"]== "true"){
                this.isExpandable = true;
            }
            else{
                this.isExpandable = false;
            }
        // ] Datbt
        // [ Datbt
        //this.isExpandable = false;
        // ] Datbt
        }

        if(this._setExpando){
            // change expando to/from dot or + icon, as appropriate
            this._setExpando(false);
        }

        // On initial tree show, make the selected TreeNode as either the root node of the tree,
        // or the first child, if the root node is hidden
        if(this == tree.rootNode){
            var fc = this.tree.showRoot ? this : this.getChildren()[0];
            if(fc){
                fc.setSelected(true);
                tree.lastFocused = fc;
            }else{
                // fallback: no nodes in tree so focus on Tree <div> itself
                tree.domNode.setAttribute("tabIndex", "0");
            }
        }

        return new dojo.DeferredList(defs);	// dojo.Deferred
    }
});
