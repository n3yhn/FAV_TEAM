<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<script>
    var clientAction = "none";//"search","insert","update","delete","none";

    page.getIndex = function(inRowIndex){
        return inRowIndex + 1;
    }

    /********************* Check whether any row is checked *************************/
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
    /*************Xu li ket qua tra ve sau loi goi vtReload********************/
    page.callback = function(data, bSuccess){

        switch (data.customInfo[0]){
            case "deleteSuccess":
                page.alert("Thông báo","<sd:Property>msg.deleteOk</sd:Property>", "info");
                break;
            case "deletePart":
                page.alert("Thông báo","Các bản ghi sau không xóa được do còn ràng buộc: " + data.customInfo[1], "warning");
                break;
            case "deleteFail":
                page.alert("Thông báo", "<sd:Property>msg.deleteErr</sd:Property>", "error");
                break;
            case "insertOk":
                page.alert("Thông báo","<sd:Property>msg.insertOk</sd:Property>", "info");
                break;
            case "insertErr":
                page.alert("Thông báo","<sd:Property>msg.userType.insertErr</sd:Property>", "error");
                break;
            case "updateOk":
                page.alert("Thông báo","<sd:Property>msg.updateOk</sd:Property>", "info");
                break;
            case "updateErr":
                page.alert("Thông báo","<sd:Property>msg.userType.insertErr</sd:Property>", "error");
                break;
            default:
                break;
            }
        }

        //********************************SEARCH************************************
        page.onSearch = function (){
            dijit.byId('userTypeGridId').vtReload("UserTypeAction!onSearch.do","userTypeForm") ;
        }
        //********************************INSERT************************************
        page.preInsert = function (){

            page.clearFormOnDialog();
            var dialog = dijit.byId("dialogId");
            dialog.titleNode.innerHTML = "Thêm mới loại người dùng";
            page.changeDialogButtonStyle(/*boolean isOnInsert*/true);
            dialog.show();

        }
    
        page.onInsert = function (){

            if(!dijit.byId("userTypeFormOnDialog.typeName").getValue()){
                page.alert("Thông báo","<sd:Property>msg.userTypeName.null</sd:Property>", "warning");
                dijit.byId("userTypeFormOnDialog.typeName").focus();
                return false;
            }

            dijit.byId('userTypeGridId').vtReload("UserTypeAction!onInsert.do","userTypeFormOnDialog", null, page.callback) ;
        }

        //********************************UPDATE************************************
        page.preUpdate = function (inRow){

            page.clearFormOnDialog();
            var dialog = dijit.byId("dialogId");
            dialog.titleNode.innerHTML = "Sửa thông tin";
            page.changeDialogButtonStyle(false);

            var row = dijit.byId("userTypeGridId").getItem(inRow);

            sd._("userTypeFormOnDialog.description").setValue(row.description);
            sd._("userTypeFormOnDialog.typeName").setValue(row.typeName);
            sd._("userTypeFormOnDialog.userTypeId").setValue(row.userTypeId);
                                                                        
            dialog.show();
        }
        page.onUpdate = function (){

            if(!dijit.byId("userTypeFormOnDialog.typeName").getValue()){
                page.alert("Thông báo","<sd:Property>msg.userTypeName.null</sd:Property>", "warning");
                dijit.byId("userTypeFormOnDialog.typeName").focus();
                return false;
            }
            dijit.byId('userTypeGridId').vtReload("UserTypeAction!onUpdate.do","userTypeFormOnDialog", null, page.callback) ;
        }
        //********************************DELETE************************************
        page.onDelete = function (){

            if(!page.isChecked("userTypeGridId")){
                page.alert("Thông báo","<sd:Property>msg.unselect</sd:Property>", "warning");
                return false;
            }

            var content = dijit.byId("userTypeGridId").vtGetCheckedDataForPost("userTypeGridForm","isCheck");

            var call = function(){
               dijit.byId('userTypeGridId').vtReload("UserTypeAction!onDelete.do",  null, content, page.callback) ;
            }

            page.confirmAsJS('<sd:Property>js.alertDelete</sd:Property>', call);
        }

        //**************************************************************************
        page.changeDialogInputStyle = function(){
            if ((clientAction=="update") || (clientAction=="insert")){
                // sd.widget.__setReadOnly("userTypeFormOnDialog.id",true);
            }
        }

        //isOnInsert = true (on Insert)
        page.changeDialogButtonStyle = function (/*boolean isOnInsert*/ isOnInsert){
            dijit.byId( "userTypeFormOnDialog.btnInsert" ).domNode.style.display = isOnInsert?"":"none";
            dijit.byId( "userTypeFormOnDialog.btnUpdate" ).domNode.style.display = isOnInsert?"none":"";
        }

        page.clearFormOnDialog = function(/*String*/){
            sd._("userTypeFormOnDialog.description").setValue("");
            sd._("userTypeFormOnDialog.typeName").setValue("");
            sd._("userTypeFormOnDialog.userTypeId").setValue("");
        }

        page.convertStringToDate = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dgDate){
            try{
                var temp = dgDate.split("-");
                return new Date(temp[1]+"/"+temp[2].split("T")[0]+"/"+temp[0]);
            }catch(e){
                page.alert("Thông báo","function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss","warning");
                return undefined;
            }
        }
</script>