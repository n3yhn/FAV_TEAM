/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


dojo.provide("vt.dataPicker.dataPicker");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("vt.dataPicker.dataPickerPopup");
dojo.declare("vt.dataPicker.dataPicker",[dijit._Widget, dijit._Templated],{
    widgetsInTemplate : true,
    templatePath: dojo.moduleUrl("vt", "templates/dataPicker.html"),
    popupClass: "vt.dataPicker.dataPickerPopup",
    searchParams:[],
    searchNumCol:2,
    gridUrl:"",
    valueField:"",
    gridStructure:[],
    selectedItem: {},
    useCache: false,
    searchUrl: "",
    bServerSearch: false,
    btnSearchLabel:"Search",
    searchDelay:1000,
    //lưu các field dưới dạng hidden mà dev muốn set them ngoài key và values
    hiddenFields:[],
    // hậu tố cho các ô text box trong control
    _suffixTextbox: "Textbox",

    //[ Callbacks cho sự kiện deleteicon-click @LongH-7Mar11
    onPreClose: null,
    onPostClose: null,

    attributeMap:dojo.delegate(dijit._Widget.prototype.attributeMap,{
        vtStyle:{
            node:"focusNode",
            type:"attribute",
            attribute:"style"
        }
    }),
    //]

    postCreate: function(){
        this.connect(this.focusNode.domNode, 'onkeypress', this._onKeyPress);
        this.connect(this.deleteIcon, 'onclick', this._onDeleteIcon);
        this.connect(this.pickDataIcon, 'onclick', this._onPickDataIcon);
        // dev cài dat them 1 so hidden field
        for (var i = 0; i < this.hiddenFields.length; i ++){
            var propName = this.hiddenFields[i] + this._suffixTextbox;
            // ex : this.idTextbox
            this[propName] = document.createElement("input");
            this[propName].type = "hidden";
            this[propName].name = this.name + "." + this.hiddenFields[i];
            this.hiddenFieldsContainer.appendChild(this[propName]);
        }
        this.setAllValue(this.selectedItem);

    },
    _onDeleteIcon:function(){
        //[ callback cho su kien pre-close @LongH-7Mar11
        if(this.onPreClose) {
            this.onPreClose();
        }
        //]
        this.doDeleteItem();
        //[ callback cho su kien post-close @LongH-7Mar11
        if(this.onPostClose) {
            this.onPostClose();
        }
        //]
    },
    setAllValue:function(obj){
        if (obj){
            for (var prop in obj){
                if (prop == this.valueField){
                    this.focusNode.setValue(obj[prop]);
                }else{
                    this[prop + this._suffixTextbox].value = obj[prop];
                }
            }
        }
    },
    doDeleteItem:function(){
        this.focusNode.setValue("");
        for (var i = 0; i < this.hiddenFields.length; i ++){
            var propName = this.hiddenFields[i] + this._suffixTextbox;
            // ex : this.idTextbox
            this[propName].value = "";
        }
        this.selectedItem = {};
    },
    _onKeyPress: function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.DOWN_ARROW:
                if (!this._opened){
                    this._open();
                }
                if (this._picker.dataGrid._by_idx.length >0){
                    try{
                        var index = 0;
                        if (this.selectedItem){
                            index = this._picker.dataGrid.getItemIndex(this.selectedItem);
                        }
                        index = (index == -1)?0:index;
                        this._picker.dataGrid.focus.focusGridView();
                        this._picker.dataGrid.selection.clickSelect(index,false,false);
                        this._picker.dataGrid.focus.setFocusIndex(index,0);
                        this._picker.dataGrid.scrollToRow(index);
                    }catch(e){
                        console.debug("_onKeyPress: " + e.message);
                    }
                }
                break;
            case dk.ENTER:
                break;
            case dk.BACKSPACE:
                this.doDeleteItem();
                break;
            case dk.DELETE:
                this.doDeleteItem();
                break;
            case dk.ESCAPE:
                this._close();
                break;
            default:
                break;
        }
    },
    
    _onPickDataIcon: function(){
        
        this._open();
    },
    
    setGridUrl: function(url){
        this.gridUrl = url;
    },
    
    _open: function(){
        try{
            var dp = this;
            if(!this._opened){
                var PopupProto=dojo.getObject(this.popupClass, false);
                
                if (this._picker == null){
                    this._picker = new PopupProto({
                        query:{},
                        dataPicker: this
                    });
                    this._picker.dataGrid.vtClickSelectCallBack = this._pickData;
                }
                dijit.popup.open({
                    parent: this,
                    popup: this._picker,
                    around: this.domNode,
                    onCancel: function(){  },
                    onClose: function(){
                        dp._opened=false;
                    }
                });
                this._picker.dataGrid.startup();
                
                if (this.selectedItem){
                    var index = this._picker.dataGrid.getItemIndex(this.selectedItem);
                    if (index != -1){
                        this._picker.dataGrid.selection.clickSelect(index,false,false);
                        this._picker.dataGrid.scrollToRow(index);
                    }
                }
                
                this._opened=true;
            }
        }catch(e){
            console.debug("_openDataPicker: " + e.message);
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
        try{
            this.dataPicker.setSelectedItem(item)
            this.dataPicker.onPickData(item);
        }catch(e){
            console.debug("dataPicker._pickData: " + e);
        }
        
    },
    onPickData: function(item){
    // user set
    },
    getSelectedItem: function(){
        return this.selectedItem;
    },
    setSelectedItem:function(obj){
        if (obj[this.valueField]){
            this.selectedItem[this.valueField] = obj[this.valueField];
        }
        for (var i = 0; i< this.hiddenFields.length; i++){
            var prop = this.hiddenFields[i];
            if (obj[prop]){
                this.selectedItem[prop] = obj[prop];
            }
        }
        this.setAllValue(this.selectedItem);
    },
    destroy:function(){
        if(this._picker){
            var wids = dijit.findWidgets( this._picker.domNode );
            var i;
            for( i = 0; i < wids.length; i++ ) {
                wids[i].destroyRecursive();
            }
            this._close();
        };
        this.inherited(arguments);
    }
});
