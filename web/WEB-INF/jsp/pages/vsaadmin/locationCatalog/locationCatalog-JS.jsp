<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<script>
    var clientAction = "none";//"search","insert","update","delete","none";

    //********************************SEARCH************************************
    page.onSearch = function (){
        dijit.byId('gridId').vtReload("LocationAction!onSearch.do","locationForm") ;
    }
    //********************************INSERT************************************
    page.preInsert = function (){
        clientAction = "insert";
        page.clearFormOnDialog();
        var dialog = dijit.byId("dialogId");
        dialog.titleNode.innerHTML = "<sd:Property>global.new</sd:Property>";
        page.changeDialogButtonStyle(/*boolean isOnInsert*/true);
        dialog.show();
    }
    page.onInsert = function (){

        if(!dijit.byId("locationFormOnDialog.locationCode").getValue()){
            page.alert("Thông báo",'Bạn chưa nhập mã địa bàn.', "error");
            dijit.byId("locationFormOnDialog.locationCode").focus();
            return false;
        }

        if(!dijit.byId("locationFormOnDialog.locationName").getValue()){
            page.alert("Thông báo",'Bạn chưa nhập tên địa bàn.', "error");
            dijit.byId("locationFormOnDialog.locationName").focus();
            return false;
        }
        
        dijit.byId('gridId').vtReload("LocationAction!onInsert.do","locationFormOnDialog", null, page.callback);
    }

    page.onTreeClick = function(item, node){
        if (item.level == undefined){
            dijit.byId('tltpn').setTitle("<sd:Property>js.listCountry</sd:Property>");
        }
        else if (item.level == 0){
            dijit.byId('tltpn').setTitle("<sd:Property>js.listProvince</sd:Property>");
        }
        else if (item.level == 1){
            dijit.byId('tltpn').setTitle("<sd:Property>js.listHuyen</sd:Property>");
        }
        else if (item.level == 2){
            dijit.byId('tltpn').setTitle("<sd:Property>js.listXa</sd:Property>");
        }
        else{
            dijit.byId('tltpn').setTitle("Danh sách các địa bàn con");
        }

        if (item.id == undefined){
            sd.$("locationForm.parentId").value = ""; 
        }
        else{
            sd.$("locationForm.parentId").value = item.id;
        }
        page.onSearch();
    }

    //********************************UPDATE************************************
    page.preUpdate = function (inRow){
        clientAction = "update";
        page.clearFormOnDialog();
        var dialog = dijit.byId("dialogId");
        dialog.titleNode.innerHTML = "<sd:Property>global.modify</sd:Property>";
        page.changeDialogButtonStyle(false);

        var row = dijit.byId("gridId").getItem(inRow);

        if (row == undefined) {
            page.alert("Thông báo", "<sd:Property>global.selectWarning</sd:Property>", "success");
            return false;
        };
        sd._("locationFormOnDialog.locationCode").setValue(row.locationCode);
        sd._("locationFormOnDialog.description").setValue(row.description);
        sd._("locationFormOnDialog.locationName").setValue(row.locationName);
        sd.$("locationFormOnDialog.locationId").value = row.locationId;
        dialog.show();
    }
    page.onUpdate = function (){
        if(!dijit.byId("locationFormOnDialog.locationCode").getValue()){
            page.alert("Thông báo",'Bạn chưa nhập mã địa bàn.', "error");
            dijit.byId("locationFormOnDialog.locationCode").focus();
            return false;
        }

        if(!dijit.byId("locationFormOnDialog.locationName").getValue()){
            page.alert("Thông báo",'Bạn chưa nhập tên địa bàn.', "error");
            dijit.byId("locationFormOnDialog.locationName").focus();
            return false;
        }
        dijit.byId('gridId').vtReload("LocationAction!onUpdate.do","locationFormOnDialog", null, page.callback) ;
    }
    
    page.callDeleteLocation=function(){
        var content = dijit.byId("gridId").vtGetCheckedDataForPost("locationFormSelection", "isCheck");
        dijit.byId("gridId").vtReload("LocationAction!onDelete.do", "locationForm", content, page.callback);
    }

    //********************************DELETE************************************
    page.onDelete = function (){
        clientAction = "delete";

        if(!page.isChecked("gridId")){
            page.alert("Thông báo", "<sd:Property>global.selectWarning</sd:Property>", "success");
            return false;
        }
        try{
            page.confirmAsJS("<sd:Property>js.alertDeleteLocation</sd:Property>",page.callDeleteLocation);
        }catch(err){
            if (confirm("<sd:Property>js.alertDeleteLocation</sd:Property>")){
                page.callDeleteLocation();
            }
        }
    }

    //isOnInsert = true (on Insert)
    page.changeDialogButtonStyle = function (/*boolean isOnInsert*/ isOnInsert){
        dijit.byId( "locationFormOnDialog.btnInsert" ).domNode.style.display = isOnInsert?"":"none";
        dijit.byId( "locationFormOnDialog.btnUpdate" ).domNode.style.display = isOnInsert?"none":"";

        if (isOnInsert == false){
            sd.widget.__setReadOnly("locationFormOnDialog.locationCode",true);
        }
        else{
            sd.widget.__setReadOnly("locationFormOnDialog.locationCode",false);
        }
    }

    page.clearFormOnDialog = function(/*String*/){
        sd._("locationFormOnDialog.locationCode").setValue("");
        sd._("locationFormOnDialog.description").setValue("");
        sd._("locationFormOnDialog.locationName").setValue("");
        sd.$("locationFormOnDialog.parentId").value = sd.$("locationForm.parentId").value;
        sd.$("locationFormOnDialog.codeSearch").value = sd._("locationForm.codeSearch").getValue();
        sd.$("locationFormOnDialog.nameSearch").value = sd._("locationForm.nameSearch").getValue();
    }

    page.convertStringToDate = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dgDate){
        try{
            var temp = dgDate.split("-");
            return new Date(temp[1]+"/"+temp[2].split("T")[0]+"/"+temp[0]);
        }catch(e){
            page.alert("Thông báo", "function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss", "success");
            return undefined;
        }
    }

    page.selectAll = function(gridId){
        var grid = dijit.byId(gridId);
        for (var idx in grid._by_idx){
            var item = grid._by_idx[idx].item;
            //item["isCheck"]="checked";
            item["isCheck"]="true";
        }
        grid.render();

    }
    page.unSelectAll = function(gridId){
        var grid = dijit.byId(gridId);
        for (var idx in grid._by_idx){
            var item = grid._by_idx[idx].item;
            item["isCheck"]="";
        }
        grid.render();
    }

    page.callback = function(data){
        switch (data.customInfo[0]){
            case "addOk":
                page.alert("Thông báo", "<sd:Property>js.alertInsert</sd:Property>", "success");
                sd._("locationFormOnDialog.locationCode").setValue("");
                sd._("locationFormOnDialog.locationName").setValue("");
                sd._("locationFormOnDialog.description").setValue("");
                break;

            case "updateOk":
                page.alert("Thông báo", "<sd:Property>js.alertUpdate</sd:Property>", "success");
                break;

            case "exist":
                page.alert("Thông báo", "<sd:Property>js.alertExist</sd:Property>", "success");
                sd._("rolesFormOnDialog.code").setValue("");
                break;

            case "deleteOk":
                if (data.customInfo[1].length > 0){
                    page.alert("Thông báo", "Một số địa bàn không thể xóa được do đã được sử dụng trong hệ thống", "success");
                }
                else{
                    page.alert("Thông báo", "<sd:Property>js.alertDel</sd:Property>", "success");
                }
                break;
            }

            dijit.byId("treeId").reloadSelectedNode();
        }

        page.isChecked = function(gridId){
            var bReturn = false;
            var grid = dijit.byId(gridId);
            for (var idx in grid.store._arrayOfAllItems){
                var item = grid.store._arrayOfAllItems[idx];
                if (item["isCheck"][0] == "true" || item["isCheck"][0] ){

                    bReturn = true;
                    break;
                }
            }
            return bReturn;
        }
</script>