dojo.provide("vt.form.MultiSelect");
dojo.require("dijit.form.MultiSelect");
dojo.require("vt.selectBox.dataStore");

dojo.declare("vt.form.MultiSelect", dijit.form.MultiSelect, {
    vtReload : function(url2GetJSONData,formId,param,callback){
        var selectData = new vt.selectBox.dataStore({
            url : url2GetJSONData,
            urlPreventCache:true
        });
        try{
            selectData._forceLoadByPost(formId,param,callback);
            if (selectData._labelAttr){
                this.searchAttr = selectData._labelAttr;
            }
            this._reloadOptions(selectData);
        }catch(e){
            console.log("[Control ID:"+this.id+".Lỗi khi lấy json data từ server (hàm SelectBox.vtReload)");
            console.log(e);
            console.log("]");
        }
        
        //SET CONTROL HIEN THI VAO DONG DU LIEU DAU TIEN CUA JSON
        try{
        //eval("var firstLabel = selectData._arrayOfAllItems[0]."+this.searchAttr+"[0]");
        //this.setDisplayedValue(firstLabel);
        }catch(e){
            var labelTemp = (this.searchAttr != '')? this.searchAttr : 'name';
            console.log("[Control ID:"+this.id+".Lỗi khi get '"+labelTemp+"' từ data do server trả về(hàm SelectBox.vtReload)");
            console.log(e);
            console.log("]");
            return;
        }
    },
    removeSelection: function(onChange){
        dojo.query("option",this.containerNode).forEach(function(n){
            this.removeChild(n);
        },this.containerNode);
        this._handleOnChange(this.attr('value'), onChange == true);
    },
    _reloadOptions: function(/*vt.selectBox.dataStore*/store){
        this.removeSelection();
        
        var fetchArgs = {}
        // Add our new options
        if(store){
            this.store = store;
            var cb = function(items){
                if(this.sortByLabel && !fetchArgs.sort && items.length){
                    items.sort(dojo.data.util.sorter.createSortFunction([{
                        attribute: store.getLabelAttributes(items[0])[0]
                    }], store));
                }

                if(fetchArgs.onFetch){
                    items = fetchArgs.onFetch(items);
                }
                // TODO: Add these guys as a batch, instead of separately
                dojo.forEach(items, function(i){
                    var option = this._getOptionObjForItem(i,this.store);
                    this.addOption(option);
                }, this);

                // Set our value (which might be undefined), and then tweak
                // it to send a change event with the real value
                this._loadingStore = false;

                this._onChangeActive = true;

                this._handleOnChange(this.attr('value'));
            };
            var opts = dojo.mixin({
                onComplete:cb,
                scope: this
            }, fetchArgs);
            this._loadingStore = true;
            store.fetch(opts);
        }else{
            this.store = null;
        }
    },
    
    _getOptionObjForItem: function(item, store){
        var label = store.getLabel(item),
        value = (label ? store.getIdentity(item) : null);
        //        return {
        //            value: value,
        //            label: label,
        //            item:item
        //        };
        return this._getMenuItemForOption({
            value: value,
            label: label,
            item:item
        });

    },
    _getMenuItemForOption: function(/*dijit.form.__SelectOption*/ option){
        // summary:
        //		For the given option, return the menu item that should be
        //		used to display it.  This can be overridden as needed
        if(!option.value){
            // We are a separator (no label set for it)
            return new dijit.MenuSeparator();
        }else{
            // Just a regular menu option
            var click = dojo.hitch(this, "_setValueAttr", option);
            var item = dojo.doc.createElement("option");
            item.value = option.value;
            item.text = option.label;
            item.innerHTML = option.label;
            return item;
        }
    },
    addOption: function(/* dijit.form.__SelectOption, dijit.form.__SelectOption[] */ option){
        // summary:
        //		Adds an option or options to the end of the select.  If value
        //		of the option is empty or missing, a separator is created instead.
        //		Passing in an array of options will yield slightly better performance
        //		since the children are only loaded once.
        if(!dojo.isArray(option)){
            option = [option];
        }
        dojo.forEach(option, function(i){
            if(i && dojo.isObject(i)){
                this.containerNode.appendChild(i);
            }
        }, this);
    //this._loadChildren();
    }
});