dojo.provide("vt.selectBox.vtFilteringSelect");
dojo.require("dijit.form.FilteringSelect");
dojo.require("vt.selectBox.dataStore");

dojo.declare("vt.selectBox.vtFilteringSelect", dijit.form.FilteringSelect, {
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
            this.store = selectData;
        }catch(e){
            console.log("[Control ID:"+this.id+".Lỗi khi lấy json data từ server (hàm SelectBox.vtReload)");
            console.log(e);
            console.log("]");
        }
        
        //SET CONTROL HIEN THI VAO DONG DU LIEU DAU TIEN CUA JSON
        try{
            eval("var firstLabel = selectData._arrayOfAllItems[0]."+this.searchAttr+"[0]");
            this.setDisplayedValue(firstLabel);
        }catch(e){
            var labelTemp = (this.searchAttr != '')? this.searchAttr : 'name';
            console.log("[Control ID:"+this.id+".Lỗi khi get '"+labelTemp+"' từ data do server trả về(hàm SelectBox.vtReload)");
            console.log(e);
            console.log("]");
            return;
        }
    },

    setSelectedIndex: function(/*int*/ index){
        var OptionsTag = (this.store)? (this.store.root)? this.store.root.options:null:null;
        var Items = (this.store)?this.store._arrayOfAllItems:null;

        if (OptionsTag){
            try{
                this.attr("value",OptionsTag[index].value);
                return;
            }catch(e){
                fwlog(this.id, "Set index: '"+index+"' thất bại", e);
            }
        }else if (Items){
            try{
                this.attr("value",this.store.getIdentity(Items[index]));
                return;
            }catch(e){
                fwlog(this.id, "Set index: '"+index+"' thất bại", e);
            }
        }else{
            fwlog(this.id,"Set index: '"+index+"' thất bại. Không thấy biến store của control",null)
        }
    }
});