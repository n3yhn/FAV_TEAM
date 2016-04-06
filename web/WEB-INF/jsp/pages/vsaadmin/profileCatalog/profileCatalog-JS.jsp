<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<script>
    var clientAction = "none";//"search","insert","update","delete","none";

    //********************************SEARCH************************************
    page.onSearch = function (){
        dijit.byId('gridId').vtReload("ProfileAction!onSearch.do",null) ;
    }
    //********************************INSERT************************************
    page.preInsert = function (){
        clientAction = "insert";
        page.changeDialogInputStyle();
        page.clearFormOnDialog();
        var dialog = dijit.byId("dialogId");
        dialog.titleNode.innerHTML = "<sd:Property>btnInsert</sd:Property>";
        page.changeDialogButtonStyle(/*boolean isOnInsert*/true);
        dialog.show();
    }
    page.onInsert = function (){
        if(!dijit.byId("profileFormOnDialog.name").getValue()){
            page.alert("Thông báo","Bạn chưa chọn tên profile.", "warning");
            dijit.byId("profileFormOnDialog.name").focus();
            return false;
        }
        dijit.byId('gridId').vtReload("ProfileAction!onInsert.do","profileFormOnDialog") ;
    }

    //********************************UPDATE************************************
    page.preUpdate = function (inRow){
        var row = dijit.byId("gridId").getItem(inRow);

        clientAction = "update";
        page.clearFormOnDialog();
        var dialog = dijit.byId("dialogId");
        dialog.titleNode.innerHTML = "<sd:Property>global.modify</sd:Property>";
        page.changeDialogButtonStyle(false);
       
        sd._("profileFormOnDialog.allowIp").setValue(row.allowIp);
        sd._("profileFormOnDialog.allowLoginTimeEnd").setValue(row.allowLoginTimeEnd);
        sd._("profileFormOnDialog.allowLoginTimeStart").setValue(row.allowLoginTimeStart);
        sd._("profileFormOnDialog.allowMultiIpLogin").setValue(row.allowMultiIpLogin);
        sd._("profileFormOnDialog.id").setValue(row.id);
        sd._("profileFormOnDialog.loginFailAllow").setValue(row.loginFailAllow);
        sd._("profileFormOnDialog.maxTmpLockAday").setValue(row.maxTmpLockAday);
        sd._("profileFormOnDialog.name").setValue(row.name);
        sd._("profileFormOnDialog.passwordValidTime").setValue(row.passwordValidTime);
        sd._("profileFormOnDialog.temporaryLockTime").setValue(row.temporaryLockTime);
        sd._("profileFormOnDialog.userValidTime").setValue(row.userValidTime);
        sd._("profileFormOnDialog.needChangePassword").setValue(row.needChangePassword);
        sd._("profileFormOnDialog.timeToChangePassword").setValue(row.timeToChangePassword);
                                                                        
        dialog.show();
    }
    page.onUpdate = function (){
        if(!dijit.byId("profileFormOnDialog.name").getValue()){
            page.alert("Thông báo","Bạn chưa chọn tên profile.", "warning");
            dijit.byId("profileFormOnDialog.name").focus();
            return false;
        }
        dijit.byId('gridId').vtReload("ProfileAction!onUpdate.do","profileFormOnDialog") ;
    }
    //********************************DELETE************************************
    page.onDelete = function (){
        clientAction = "delete";
        page.clearFormOnDialog();
        var recordsToDelete = dijit.byId("gridId").vtGetModifiedDataForPost("profileFormOnGrid");

        if (confirm('<sd:Property>global.deleteWarning</sd:Property>')){
            dijit.byId("gridId").vtReload("ProfileAction!onDelete.do",null,recordsToDelete);
        }
    }

    //**************************************************************************
    page.changeDialogInputStyle = function(){
        if ((clientAction=="update") || (clientAction=="insert")){
            sd.widget.__setReadOnly("profileFormOnDialog.id",true);
        }
    }

    //isOnInsert = true (on Insert)
    page.changeDialogButtonStyle = function (/*boolean isOnInsert*/ isOnInsert){
        dijit.byId( "profileFormOnDialog.btnInsert" ).domNode.style.display = isOnInsert?"":"none";
        dijit.byId( "profileFormOnDialog.btnUpdate" ).domNode.style.display = isOnInsert?"none":"";
    }

    page.clearFormOnDialog = function(/*String*/){
        sd._("profileFormOnDialog.allowIp").setValue("");
        sd._("profileFormOnDialog.allowLoginTimeEnd").setValue("");
        sd._("profileFormOnDialog.allowLoginTimeStart").setValue("");
        sd._("profileFormOnDialog.allowMultiIpLogin").setValue("0");
        sd._("profileFormOnDialog.id").setValue("");
        sd._("profileFormOnDialog.loginFailAllow").setValue("");
        sd._("profileFormOnDialog.maxTmpLockAday").setValue("");
        sd._("profileFormOnDialog.name").setValue("");
        sd._("profileFormOnDialog.passwordValidTime").setValue("");
        sd._("profileFormOnDialog.temporaryLockTime").setValue("");
        sd._("profileFormOnDialog.userValidTime").setValue("");
        sd._("profileFormOnDialog.needChangePassword").setValue("1");
        sd._("profileFormOnDialog.timeToChangePassword").setValue("");
    }

    page.convertStringToDate = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dgDate){
        try{
            var temp = dgDate.split("-");
            return new Date(temp[1]+"/"+temp[2].split("T")[0]+"/"+temp[0]);
        }catch(e){
            alert("function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss");
            return undefined;
        }
    }
</script>