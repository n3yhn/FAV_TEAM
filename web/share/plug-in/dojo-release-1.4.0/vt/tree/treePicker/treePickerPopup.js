/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


dojo.provide("vt.tree.treePicker.treePickerPopup");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("dijit.form.TextBox");
dojo.require("vt.tree.ajaxTree.dataStore");
dojo.require("vt.tree.ajaxTree.tree");
dojo.require("vt.tree.ajaxTree.treeModel");
dojo.require("dijit.TitlePane");

dojo.declare("vt.tree.treePicker.treePickerPopup",[dijit._Widget, dijit._Templated],{
    templatePath: dojo.moduleUrl("vt", "templates/treePicker.html"),
    tree: null,
    treePicker:{},

    postCreate: function(){
        try{
            this.inherited(arguments);
            var dataStore = new vt.tree.ajaxTree.dataStore({
                getChildrenUrl: this.treePicker.getChildrenNodeUrl,
                url: this.treePicker.getTopNodeUrl,
                preventCache:true
            });
            var treeModel = new vt.tree.ajaxTree.treeModel({
                store: dataStore,
                rootLabel: this.treePicker.rootLabel
            });
            this.tree = new vt.tree.ajaxTree.tree({
                id: this.treePicker.id + "PopupTree",
                model: treeModel
            });
            this.tree.onClick = this.onTreeClick;
            this.tree.treePicker = this.treePicker;
            var tdTreePickerDiv = dojo.query(".treePickerDiv", this.domNode)[0];
            tdTreePickerDiv.appendChild(this.tree.domNode);
            
        }catch(e){
            console.debug("create dataPickerPopup: " + e.message);
        }
    },
    onTreeClick: function(/* dojo.data */ item, /*TreeNode*/ node, /*Event*/ evt){
        this.treePicker._pickData(item);
        this.treePicker.focusNode.focus();
        this.treePicker._close();
    },

    _onKeyPressSearch:function(/*Event*/ e){
        var dk = dojo.keys;
        var s = e.currentTarget.value;
        var field = e.currentTarget.id.replace(this.idPrifix, "");
        this.query[field] = "*" + s + "*";
        this.dataGrid.setQuery(this.query,null);
        this.dataGrid._refresh(true);
        e.currentTarget.focus();
    },
    reset:function(){
    }
    
});
