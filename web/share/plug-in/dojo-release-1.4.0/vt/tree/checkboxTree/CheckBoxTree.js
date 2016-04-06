/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
dojo.provide("vt.tree.checkboxTree.CheckBoxTree");

dojo.require("dijit.Tree");
dojo.require("vt.tree.checkboxTree._CheckBoxTreeNode");

dojo.declare( "vt.tree.checkboxTree.CheckBoxTree", dijit.Tree,
{
    onNodeChecked: function(/*dojo.data.Item*/ storeItem, /*treeNode*/ treeNode) {
    // summary:
    //		Callback when a checkbox tree node is checked
    // tags:
    //		callback
    },

    onNodeUnchecked: function(/*dojo.data.Item*/ storeItem, /* treeNode */ treeNode) {
    // summary:
    //		Callback when a checkbox tree node is unchecked
    // tags:
    //		callback
    },
    onClick: function(item, nodeWidget, e ){
    },
    _onClick: function(/*TreeNode*/ nodeWidget, /*Event*/ e) {
        // summary:
        //		Translates click events into commands for the controller to process
        // description:
        //		the _onClick function is called whenever a 'click' is detected. This
        //		instance of _onClick only handles the click events associated with
        //		the checkbox whos DOM name is INPUT.
        //
        
        var domElement = e.target;
        // Only handle checkbox clicks here
        if(domElement.nodeName != 'INPUT') {
            return this.inherited( arguments );
        }
        this._publish("execute", {
            item: nodeWidget.item,
            node: nodeWidget
        } );
        // Go tell the model to update the checkbox state
        this.model.updateCheckbox( nodeWidget.item, nodeWidget._checkbox.checked );
        // Generate some additional events
        this.onClick( nodeWidget.item, nodeWidget, e );
        if(nodeWidget._checkbox.checked) {
            this.onNodeChecked( nodeWidget.item, nodeWidget);
        } else {
            this.onNodeUnchecked( nodeWidget.item, nodeWidget);
        }
        this.focusNode(nodeWidget);
    },

    _onCheckboxChange: function(/*dojo.data.Item*/ storeItem ) {
        // summary:
        //		Processes notification of a change to a checkbox state (triggered by the model).
        // description:
        //		Whenever the model changes the state of a checkbox in the dojo.data.store it will
        //		trigger the 'onCheckboxChange' event allowing the Tree to make the same changes
        //		on the tree Node. There are several conditions why a tree node or checkbox does not
        //		exists:
        //		a) 	The node has not been created yet (the user has not expanded the tree node yet).
        //		b) 	The checkbox may be null if condition (a) exists or no 'checkbox' attribute was
        //			specified for the associated dojo.data.item and the attribute 'checkboxAll' is
        //			set to false.
        // tags:
        //		callback
        
        var model 	 = this.model,
        identity = model.getIdentity(storeItem),
        nodes 	 = this._itemNodesMap[identity];

        // As of dijit.Tree 1.4 multiple references (parents) are supported, therefore we may have
        // to update multiple nodes which are all associated with the same dojo.data.item.
        if( nodes ) {
            dojo.forEach( nodes, function(node) {
                if( node._checkbox != null ) {
                    node._checkbox.checked = this.model.getCheckboxState( storeItem );
                }
            }, this );
        }
    },

    postCreate: function() {
        // summary:
        //		Handle any specifics related to the tree and model after the instanciation of the Tree.
        // description:
        //		Validate if we have a 'write' store first. Subscribe to the 'onCheckboxChange' event
        //		(triggered by the model) and kickoff the initial checkbox data validation.
        //
        try {
            var store = this.model.store;
            if(!store.getFeatures()['dojo.data.api.Write']){
                throw new Error("vt.tree.checkboxTree.CheckboxTree: store must support dojo.data.Write");
            }
            this.connect(this.model, "onCheckboxChange", "_onCheckboxChange");
            this.model.validateData( this.model.root, this.model );
            this.inherited(arguments);
            
        }
        catch(e){
            
        }
    },

    getIconClass: function(/*dojo.data.Item*/ item, /*Boolean*/ opened){
        // summary:
        //		Overridable function to return CSS class name to display icon
        // tags:
        //		extension
        
        var iconClass=item['iconClass'];
        if(iconClass == "" || iconClass==null){
            return  (!item || this.model.mayHaveChildren(item)) ? (opened ? "dijitFolderOpened" : "dijitFolderClosed") : "dijitLeaf"
        }
        else {
            return iconClass;
        }
    },

    getLabelClass: function(/*dojo.data.Item*/ item, /*Boolean*/ opened){
        // summary:
        //		Overridable function to return CSS class name to display label
        // tags:
        //		extension
        if(item['labelClass'] == "" || item['labelClass']==null){
            return "";
        }
        else {
            return item['labelClass'];
        }
    },

    getRowClass: function(/*dojo.data.Item*/ item, /*Boolean*/ opened){
        // summary:
        //		Overridable function to return CSS class name to display row
        // tags:
        //		extension
        if(item['rowClass'] == "" || item['rowClass']==null){
            return "";
        }
        else {
            return item['rowClass'];
        }
    },

    getIconStyle: function(/*dojo.data.Item*/ item, /*Boolean*/ opened){
        // summary:
        //		Overridable function to return CSS styles to display icon
        // returns:
        //		Object suitable for input to dojo.style() like {backgroundImage: "url(...)"}
        // tags:
        //		extension
        if(item['iconStyle'] == "" || item['iconStyle']==null){
            return "";
        }
        else {
            return item['iconStyle'];
        }
    },

    getLabelStyle: function(/*dojo.data.Item*/ item, /*Boolean*/ opened){
        // summary:
        //		Overridable function to return CSS styles to display label
        // returns:
        //		Object suitable for input to dojo.style() like {color: "red", background: "green"}
        // tags:
        //		extension
        if(item['labelStyle'] == "" || item['labelStyle']==null){
            return "";
        }
        else {
            return item['labelStyle'];
        }
    },

    getRowStyle: function(/*dojo.data.Item*/ item, /*Boolean*/ opened){
        // summary:
        //		Overridable function to return CSS styles to display row
        // returns:
        //		Object suitable for input to dojo.style() like {background-color: "#bbb"}
        // tags:
        //		extension
        if(item['rowStyle'] == "" || item['rowStyle']==null){
            return "";
        }
        else {
            return item['rowStyle'];
        }
    },

    getTooltip: function(/*dojo.data.Item*/ item){
        // summary:
        //		Overridable function to get the tooltip for a tree node (given the item)
        // tags:
        //		extension
        if(item['tooltip'] == "" || item['tooltip']==null){
            return "";
        }
        else {
            return item['tooltip'];
        }
    },
    _createTreeNode: function( args ) {
        // summary:
        //		Create a new _CheckBoxTreeNode instance.
        // description:
        //		Create a new _CheckBoxTreeNode instance.
        return new vt.tree.checkboxTree._CheckBoxTreeNode(args);
    },
    getAllNodeForPost: function(form,state,arrProps){
        var outContent = {};
        this.model.getAllChildrenForPost(this.model.root,outContent,form,state,arrProps);
        return outContent;
    }
});


