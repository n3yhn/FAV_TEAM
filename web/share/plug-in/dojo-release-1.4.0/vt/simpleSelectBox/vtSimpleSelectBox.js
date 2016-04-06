dojo.provide("vt.simpleSelectBox.vtSimpleSelectBox");
dojo.require("dijit.form.Select");
dojo.require("vt.selectBox.dataStore");

dojo.declare("vt.simpleSelectBox.vtSimpleSelectBox", dijit.form.Select, {

    vtReload : function(url2GetJSONData,formId,param,callback){
        var selectData = new vt.selectBox.dataStore({
            url : url2GetJSONData,
            urlPreventCache:true
        });
        try{
            selectData._forceLoadByPost(formId,param,callback);
            this.setStore(selectData);
        }catch(e){//Loi khi get json data tu phia server
            logger.error("[Control ID:"+this.id+".Lỗi khi lấy json data từ server (hàm SelectBox.vtReload)");
            logger.error(e);
            logger.error("]");
        }
    },

    setSelectedIndex:function(index){
        if (this.options){
            if (index < this.options.length ){
                this._setValueAttr(this.options[index].value);
            }else{
                logger.warn("[Control ID:"+this.id+". selectedIndex lớn hơn số lượng options của control]");
            }
        }
    }
});