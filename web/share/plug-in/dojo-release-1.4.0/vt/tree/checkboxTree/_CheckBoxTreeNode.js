/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
dojo.provide("vt.tree.checkboxTree._CheckBoxTreeNode");

dojo.require("dijit.Tree");

dojo.declare( "vt.tree.checkboxTree._CheckBoxTreeNode", dijit._TreeNode,
{
    // _checkbox: [protected] dojo.doc.element
    //		Local reference to the dojo.doc.element of type 'checkbox'
    _checkbox: null,

    _createCheckbox: function() {
        // summary:
        //		Create a checkbox on the _CheckBoxTreeNode
        // description:
        //		Create a checkbox on the _CheckBoxTreeNode. The checkbox is ONLY created if a
        //		valid reference was found in the dojo.data store or the attribute 'checkboxAll'
        //		is set to true. If the current state is 'undefined' no reference was found and
        //		'checkboxAll' is set to false.
        //		Note: the attribute 'checkboxAll' is validated by the getCheckboxState function
        //		therefore no need to do that here. (see getCheckboxState for details).
        //
        try{
            var	currState = this.tree.model.getCheckboxState( this.item );
            if( currState !== undefined ) {
                this._checkbox = dojo.doc.createElement('input');
                this._checkbox.type    = 'checkbox';
                dojo.place(this._checkbox, this.expandoNode, 'after');
                this._checkbox.checked = currState;
            }
            //console.log("<2< " + this.item.id + " || " + this.item.name + " || " + this.item.checkbox + " || " + currState + " >2>");
        }
        catch(e){
        }
		
    },

    postCreate: function() {
        // summary:
        //		Handle the creation of the checkbox after the _CheckBoxTreeNode has been instanciated.
        // description:
        //		Handle the creation of the checkbox after the _CheckBoxTreeNode has been instanciated.
        try {
            if (this.item['type']=='checkbox'){
            this._createCheckbox();
        }
        this.inherited( arguments );
        }
        catch(e){
        }

    }

});

