/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


dojo.provide("vt.tree.treePicker.treePicker");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("vt.tree.treePicker.treePickerPopup");
dojo.declare("vt.tree.treePicker.treePicker",dijit.form.RangeBoundTextBox,{
    baseClass: "dijitTextBox",
    popupClass: "vt.tree.treePicker.treePickerPopup",
    getTopNodeUrl:"",
    getChildrenNodeUrl:"",
    selectedItem: null,
    rootLabel: "",
    useCache: false,
    popupStyle: "",
    readOnly:false,
    disabled:false,
    postCreate: function(){
        this.connect(this.focusNode, 'onkeypress', this._onKeyPress);
        console.debug(this.readOnly);
        if(this.readOnly) this.focusNode.readOnly=true;
        if(this.disabled) this.focusNode.readOnly=true;
    //        this.connect(this.focusNode, 'onkeyup', this._onKeyUp);
    },
    //    _onKeyUp: function(e){
    //        var dk = dojo.keys;
    //        if (e.keyCode!= dk.DOWN_ARROW && e.keyCode!= dk.ENTER){
    //            if (this._picker){
    //
    //        }
    //        }
    //    },
    _onKeyPress: function(e){
        var p = this._picker, dk = dojo.keys;
        // Handle the key in the picker, if it has a handler.  If the handler
        // returns false, then don't handle any other keys.
        if(p && this._opened && p.handleKey){
            if(p.handleKey(e) === false){
                return;
            }
        }
        if(this._opened && e.charOrCode == dk.ESCAPE && !(e.shiftKey || e.ctrlKey || e.altKey || e.metaKey)){
            this._close();
        }else if( e.charOrCode == dk.DOWN_ARROW){
            if (!this._opened){
                this._open();
            }
            this._picker.tree.focusNode(this._picker.tree.rootNode);
        }else if(e.charOrCode === dk.TAB){
            this._tabbingAway = true;
        }else if(this._opened && (e.keyChar || e.charOrCode === dk.BACKSPACE || e.charOrCode == dk.DELETE)){
            // Replace the element - but do it after a delay to allow for
            // filtering to occur
            setTimeout(dojo.hitch(this, function(){
                dijit.placeOnScreenAroundElement(p.domNode.parentNode, this.domNode, {
                    'BL':'TL',
                    'TL':'BL'
                }, p.orient ? dojo.hitch(p, "orient") : null);
            }), 1);
        }
    },
    _onFocus: function(){
        
        this._open();
    },
    
    _open: function(){
        try{
            var textBox = this;
            if(!this._opened){
                var PopupProto=dojo.getObject(this.popupClass, false);
                
                if (this._picker == null){
                    this._picker = new PopupProto({
                        treePicker: this
                    });
                }
                dijit.popup.open({
                    parent: this,
                    popup: this._picker,
                    around: this.domNode,
                    onCancel: function(){  },
                    onClose: function(){
                        textBox._opened=false;
                    }
                });
                this._picker.tree.startup();
                this._opened=true;
            }
        }catch(e){
            console.debug("_openTreePicker: " + e.message);
        }
    //        dojo.marginBox(this._picker.domNode,{ w:this.domNode.offsetWidth, h:"300px"});
    //        dojo.marginBox(this._picker.domNode,{
    //            w:300,
    //            h:300
    //        });
    },
    _close: function(){
        if(this._opened){
            dijit.popup.close(this._picker);
            this._opened=false;
        }
        
    },
    _onBlur: function(){
        // summary:
        //		Called magically when focus has shifted away from this widget and it's dropdown
        try{
            if (!this.useCache){
                if(this._picker){
                    var wids = dijit.findWidgets( this._picker.domNode );
                    var i;
                    for( i = 0; i < wids.length; i++ ) {
                        wids[i].destroyRecursive();
                    }
                    // teardown so that constraints will be rebuilt next time (redundant reference: #6002)
                    this._picker.reset();
                    this._picker.destroy();
                    delete this._picker;
                }
            }
            this._close();
        }
        catch(e){
            console.debug("dataPicker._onBlur:" + e.message);
        }
        this.inherited(arguments);
    // don't focus on <input>.  the user has explicitly focused on something else.
    },
    _pickData: function(item){
        this.selectedItem = item;
        this.focusNode.value = item["name"];
        this.onPickData(item);
    },
    onPickData: function(item){
    // user set
    },
    getSelectedItem:function(){
        return this.selectedItem;
    },
    setSelectedItem:function(item){
        //  this.selectedItem=item;
        // this.focusNode.value=item["name"];
        this._pickData(item);
    },
    destroy:function(){
        if(this._picker){
            var wids = dijit.findWidgets( this._picker.domNode );
            var i;
            for( i = 0; i < wids.length; i++ ) {
                wids[i].destroyRecursive();
            }
        };
        this.inherited(arguments);
    }
});
