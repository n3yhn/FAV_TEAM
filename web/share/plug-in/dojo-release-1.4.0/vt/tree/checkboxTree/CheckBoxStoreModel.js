/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
dojo.provide("vt.tree.checkboxTree.CheckBoxStoreModel");

dojo.require("dijit.tree.ForestStoreModel");

dojo.declare( "vt.tree.checkboxTree.CheckBoxStoreModel", dijit.tree.ForestStoreModel,
{
    // checkboxAll: Boolean
    //		If true, every node in the tree will receive a checkbox regardless if the 'checkbox' attribute
    //		is specified in the dojo.data.
    checkboxAll: false,

    // checkboxState: Boolean
    // 		The default state applied to every checkbox unless otherwise specified in the dojo.data.
    //		(see also: checkboxIdent)
    checkboxState: false,

    // checkboxRoot: Boolean
    //		If true, the root node will receive a checkbox eventhough it's not a true entry in the store.
    //		This attribute is independent of the showRoot attribute of the tree itself. If the tree
    //		attribute 'showRoot' is set to false to checkbox for the root will not show either.
    checkboxRoot: false,

    // checkboxStrict: Boolean
    //		If true, a strict parent-child checkbox relation is maintained. For example, if all children
    //		are checked the parent will automatically be checked or if any of the children are unchecked
    //		the parent will be unchecked.
    checkboxStrict: false,

    // checkboxIdent: String
    //		The attribute name (attribute of the dojo.data.item) that specifies that items checkbox initial
    //		state. Example:	{ name:'Egypt', type:'country', checkbox: true }
    //		If a dojo.data.item has no 'checkbox' attribute specified it will depend on the attribute
    //		'checkboxAll' if one will be created automatically and if so what the initial state will be as
    //		specified by 'checkboxState'.
    checkboxIdent: "checkbox",
    updateCheckbox: function(/*dojo.data.Item*/ storeItem,/*Boolean*/ newState ) {
        // summary:
        //		Update the checkbox state (true/false) for the item and the associated parent and
        //		child checkboxes if any.
        // description:
        //		Update a single checkbox state (true/false) for the item and the associated parent
        //		and child checkboxes if any. This function is called from the tree if a user checked
        //		or unchecked a checkbox on the tree. The parent and child tree nodes are updated to
        //		maintain consistency if 'checkboxStrict' is set to true.
        //	storeItem:
        //		The item in the dojo.data.store whos checkbox state needs updating.
        //	newState:
        //		The new state of the checkbox: true or false
        //	example:
        //	| model.updateCheckboxState(item, true);
        //
        this._setCheckboxState( storeItem, newState );
        if( this.checkboxStrict ) {
            this._updateChildCheckbox( storeItem,newState );
            this._updateParentCheckbox( storeItem,newState );
        }
    },
    getCheckboxState: function(/*dojo.data.Item*/ storeItem) {
        // summary:
        //		Get the current checkbox state from the dojo.data.store.
        // description:
        //		Get the current checkbox state from the dojo.data store. A checkbox can have three
        //		different states: true, false or undefined. Undefined in this context means no
        //		checkbox identifier (checkboxIdent) was found in the dojo.data store. Depending on
        //		the checkbox attributes as specified above the following will take place:
        //		a) 	If the current checkbox state is undefined and the checkbox attribute 'checkboxAll' or
        //			'checkboxRoot' is true one will be created and the default state 'checkboxState' will
        //			be applied.
        //		b)	If the current state is undefined and 'checkboxAll' is false the state undefined remains
        //			unchanged and is returned. This will prevent any tree node from creating a checkbox.
        //
        //	storeItem:
        //		The item in the dojo.data.store whos checkbox state is returned.
        //	example:
        //	| var currState = model.getCheckboxState(item);
        //
        var currState = undefined;

        // Special handling required for the 'fake' root entry (the root is NOT a dojo.data.item).
        if ( storeItem == this.root ) {
            if( typeof(storeItem.checkbox) == "undefined" ) {
                this.root.checkbox = undefined;		// create a new checbox reference as undefined.
                if( this.checkboxRoot ) {
                    currState = this.root.checkbox = this.checkboxState;
                }
            } else {
                currState = this.root.checkbox;
            }
        } else {	// a valid dojo.store.item

            //[ Trường hợp không chứa trường type=checkbox thì trả về giá trị undefined LongH@4Aug2011
            if(this.store.getValue(storeItem, 'type') === 'checkbox') {
                //[ Dojo code
                currState = this.store.getValue(storeItem, this.checkboxIdent);
                if( currState == undefined && this.checkboxAll) {
                    this._setCheckboxState( storeItem, this.checkboxState );
                    currState = this.checkboxState;
                }
                //] Dojo code
            } else {
                currState = undefined;
            }
            //] Trường hợp không chứa trường type=checkbox thì trả về giá trị undefined LongH@4Aug2011
        }
        return currState  // the current state of the checkbox (true/false or undefined)
    },

    _setCheckboxState: function(/*dojo.data.Item*/ storeItem, /*Boolean*/ newState ) {
        // summary:
        //		Set/update the checkbox state on the dojo.data store.
        // description:
        //		Set/update the checkbox state on the dojo.data.store. Retreive the current
        //		state of the checkbox and validate if an update is required, this will keep
        //		update events to a minimum. On completion a 'onCheckboxChange' event is
        //		triggered.
        //		If the current state is undefined (ie: no checkbox attribute specified for
        //		this dojo.data.item) the 'checkboxAll' attribute is checked	to see if one
        //		needs to be created. In case of the root the 'checkboxRoot' attribute is checked.
        //		NOTE: the store.setValue function will create the 'checkbox' attribute for the
        //		item if none exists.
        //	storeItem:
        //		The item in the dojo.data.store whos checkbox state is updated.
        //	newState:
        //		The new state of the checkbox: true or false
        //	example:
        //	| model.setCheckboxState(item, true);
        //
        var stateChanged = true;

        if( storeItem != this.root ) {
            var currState = this.store.getValue(storeItem, this.checkboxIdent);
            if( currState != newState && ( currState !== undefined || this.checkboxAll ) ) {
                this.store.setValue(storeItem, this.checkboxIdent, newState);
            } else {
                stateChanged = false;	// No changes to the checkbox
            }
        } else {  // Tree root instance
            if( this.root.checkbox != newState && ( this.root.checkbox !== undefined || this.checkboxRoot ) ) {
                this.root.checkbox = newState;
            } else {
                stateChanged = false;
            }
        }
        if( stateChanged ) {	// In case of any changes trigger the update event.
            this.onCheckboxChange(storeItem);
        }
        return stateChanged;
    },
    _updateChildCheckbox: function(/*dojo.data.Item*/ parentItem, /*Boolean*/ newState ) {
        //	summary:
        //		Set all child checkboxes to true/false depending on the parent checkbox state.
        //	description:
        //		If a parent checkbox changes state, all child and grandchild checkboxes will be
        //		updated to reflect the change. For example, if the parent state is set to true,
        //		all child and grandchild checkboxes will receive that same 'true' state.
        //		If a child checkbox changes state and has multiple parent, all of its parents
        //		need to be re-evaluated.
        //	parentItem:
        //		The parent dojo.data.item whos child/grandchild checkboxes require updating.
        //	newState:
        //		The new state of the checkbox: true or false
        //
        if( this.mayHaveChkChildren( parentItem )) {
            this.getChkChildren( parentItem, dojo.hitch( this,
                function( children ) {     
                    dojo.forEach( children, function(child) {
                        if( this._setCheckboxState(child, newState) ) {
                            var parents = this._getParentsItem(child);
                            if( parents.length > 1 ) {
                                this._updateParentCheckbox( child, newState );
                            }
                        }
                        if( this.mayHaveChildren( child )) {
                            this._updateChildCheckbox( child, newState );
                        }
                    }, this );
                }),
            function(err) {
                console.error(this, ": updating child checkboxes: ", err);
            }
            );
        }
    },

    _updateParentCheckbox: function(/*dojo.data.Item*/ storeItem, /*Boolean*/ newState ) {
        //	summary:
        //		Update the parent checkbox state depending on the state of all child checkboxes.
        //	description:
        //		Update the parent checkbox state depending on the state of all child checkboxes.
        //		The parent checkbox automatically changes state if ALL child checkboxes are true
        //		or false. If, as a result, the parent checkbox changes state, we will check if
        //		its parent needs to be updated as well all the way upto the root.
        //	storeItem:
        //		The dojo.data.item whos parent checkboxes require updating.
        //	newState:
        //		The new state of the checkbox: true or false
        //
        var parents = this._getParentsItem(storeItem);

        dojo.forEach( parents, function( parentItem ) {
            if( newState ) { // new state = true (checked)
                this.getChkChildren( parentItem, dojo.hitch( this,
                    function(siblings) {
                        var allChecked  = true;
                        dojo.some( siblings, function(sibling) {
                            siblState = this.getCheckboxState(sibling);
                            if( siblState !== undefined && allChecked )
                                allChecked = siblState;
                            return !(allChecked);
                        }, this );
                        if( allChecked ) {
                            this._setCheckboxState( parentItem, true );
                            this._updateParentCheckbox( parentItem, true );
                        }
                    }),
                function(err) {
                    console.error(this, ": updating parent checkboxes: ", err);
                }
                );
            } else { 	// new state = false (unchecked)
                if( this._setCheckboxState( parentItem, false ) ) {
                    this._updateParentCheckbox( parentItem, false );
                }
            }
        }, this );
    },
       
    _getParentsItem: function(/*dojo.data.Item*/ storeItem ) {
        // summary:
        //		Get the parent(s) of a dojo.data item.
        // description:
        //		Get the parent(s) of a dojo.data item. The '_reverseRefMap' entry of the item is
        //		used to identify the parent(s). A child will have a parent reference if the parent
        //		specified the '_reference' attribute.
        //		For example: children:[{_reference:'Mexico'}, {_reference:'Canada'}, ...
        //	storeItem:
        //		The dojo.data.item whos parent(s) will be returned.
        //
        var parents = [];
        if( storeItem != this.root ) {
            var references = storeItem[this.store._reverseRefMap];
            for(itemId in references ) {
                parents.push(this.store._itemsByIdentity[itemId]);
            }
            if (!parents.length) {
                parents.push(this.root);
            }
        }
        return parents // parent(s) of a dojo.data.item (Array of dojo.data.items)
    },

    /**
     * Author: LongH
     * Description: Thay đổi cơ chế check
     * Date: 24/05/2011
     * FWVersion: 3.3
     **/
    validateData: function(/*dojo.data.Item*/ storeItem, /*thisObject*/ scope) {
        // summary:
        //		Validate/normalize the parent(s) checkbox data in the dojo.data store.
        // description:
        //		Validate/normalize the parent-child checkbox relationship if the attribute
        //		'checkboxStrict' is set to true. This function is called as part of the post
        //		creation of the Tree instance. All parent checkboxes are set to the appropriate
        //		state according to the actual state(s) of their children.
        //		This will potentionally overwrite whatever was specified for the parent in the
        //		dojo.data store. This will garantee the tree is in a consistent state after startup.
        //	storeItem:
        //		The element to start traversing the dojo.data.store, typically model.root
        //	scope:
        //		The scope to use when this method executes.
        //	example:
        //	| this.model.validateData(this.model.root, this.model);
        //
        if( !scope.checkboxStrict ) {
            return;
        }

        scope.getChkChildren( storeItem, dojo.hitch( scope,
            function(children) {
                var allChecked  = true;
                var haveChild = false; // LongH
                var finalState; // LongH
                var childState;

                dojo.forEach( children, function( child ) {
                    haveChild = true;
                    if( this.mayHaveChkChildren( child )) {
                        this.validateData( child, this );
                    }
                    childState = this.getCheckboxState( child );
                    
                    if( childState !== undefined && allChecked )
                        allChecked = childState;
                }, this);
                
                //[ LongH
                if(haveChild) {
                    finalState = allChecked;
                } else {
                    finalState = this.getCheckboxState(storeItem);
                }

                if ( this._setCheckboxState( storeItem, finalState) ) {
                    this._updateParentCheckbox( storeItem, finalState);
                }
            //] LongH
            }),
        function(err) {
            console.error(this, ": validating checkbox data: ", err);
        }
        );
    },

    onCheckboxChange: function(/*dojo.data.Item*/ storeItem ) {
    // summary:
    //		Callback whenever a checkbox state has changed state, so that
    //		the Tree can update the checkbox.  This callback is generally
    //		triggered by the '_setCheckboxState' function.
    // tags:
    //		callback
    },
    _getAllNodeForPost: function(item,outContent,form,arrProps){
        if (arrProps){
            for (var i = 0;i < arrProps.length; i++ ){
                var prop = arrProps[i];
                if (item[prop]){
                    var propForPost = form +  "." + prop;
                    //      propForPost.replace("children","");
                    if (!dojo.isObject(item[prop][0])||item[prop][0] == null){
                        if (!outContent[ propForPost]){
                            outContent[ propForPost] = [];
                        }
                        outContent[ propForPost].push(item[prop][0]);
                    }
                    else{
                        var compositItem = item[prop][0];
                        this._getAllNodeForPost(compositItem,outContent,propForPost,arrProps);
                    }
                }
            }
        }
        else{
            for (var prop in item){
                if ((prop != "_0") && (prop != "_R") && (prop!="_RS")
                    && (prop!="_RI") && (prop!="_S") && (prop!="_RRM")){
                    var propForPost = form +  "." + prop;
                    if(propForPost != (form + ".children"))
                        propForPost=propForPost.replace('.children','');
                    if (!dojo.isObject(item[prop][0])||item[prop][0] == null){
                        if (!outContent[ propForPost]){
                            outContent[ propForPost] = [];
                        }
                        outContent[ propForPost].push(item[prop][0]);
                    }
                //                    else{
                //                        var compositItem = item[prop][0];
                //                        console.debug("_compositeId:"+compositItem['id']);
                //                        this._getAllNodeForPost(compositItem,outContent,propForPost,arrProps);
                //                    }
                }
            }
        }

    },
    getAllChildrenForPost: function(item,outContent,form,state,arrProps){
        this.getChkChildren( item, dojo.hitch(this,
            function(children) {                
                dojo.forEach( children, function( child ) {
                    var currState=this.getCheckboxState(child);
                    if(currState==state && child['type']=='checkbox'){
                        this._getAllNodeForPost(child,outContent,form,arrProps);
                    }
                    if( this.mayHaveChkChildren( child )) {
                        this.getAllChildrenForPost(child,outContent,form,state,arrProps);
                    }
                }, this);
            }),
        function(err) {
            console.error(this, ": getAllChildrenForPost: ", err);
        }
        );
    }
});

