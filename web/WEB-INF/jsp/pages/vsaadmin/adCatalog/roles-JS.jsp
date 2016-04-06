<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<script>
    var clientAction = "none";//"search","insert","update","delete","none";

    //********************************SEARCH************************************
    page.onSearchRoleOnDialog = function (){
        dijit.byId('gridRoleIdOnDialog').vtReload("AdRoleAction!onSearch.do","rolesFormOnDialog") ;
    }
    page.onInsertRole = function (){
       if(page.isChecked("gridRoleIdOnDialog")){
              var content1 = dijit.byId("gridRoleIdOnDialog").vtGetCheckedDataForPost("roleGridForm", "isCheck");
              var content2 = dijit.byId("gridId").vtGetCheckedDataForPost("userGridForm", "isCheck");
              var param = {};
                for (var prop in content1){
                    param[prop] = content1[prop];
                }
                for (var prop in content2){
                    param[prop] = content2[prop];
                }
               dijit.byId('gridRoleId').vtReload("AdRoleAction!onInsert.do",null,param,page.afterCallback) ;
            }
            else{
                 try{
                        page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
                        }catch(err){
                            alert("<sd:Property>js.alertRecord</sd:Property>");
                        }
          }
    }
    //********************************INSERT************************************
    page.preInsertRole = function (){
       
          if(page.isChecked("gridId")){
                var content = dijit.byId("gridId").vtGetCheckedDataForPost("userGridForm", "isCheck");
                sd.connector.post("AdRoleAction!prepareInsertRole.do","dialogRoleDiv",null,content,page.showInsertRoleDialog);
            }
            else{
                 try{
                        page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
                        }catch(err){
                            alert("<sd:Property>js.alertRecord</sd:Property>");
                        }
          }
    }
    /* for viewing some role which has been selected*/
    page.onViewRole = function(){
<%--        var row= dijit.byId("gridRoleId").selection.getSelected()[0];
        if (row == undefined) {
            alert('Chọn vai trò cần xem!');
            return false;
        };
        vt.connector.post("AdRoleAction!prepareListObject.do?roleId="+row.roleId+"&roleCode="+row.code,"objectArea","rolesForm",null,page.callback);--%>
        if(page.isChecked("gridRoleId")){
              var content = dijit.byId("gridRoleId").vtGetCheckedDataForPost("roleGridForm", "isCheck");
              sd.connector.post("AdRoleAction!prepareListObject.do","objectArea",null,content,page.callback);
        }
        else{
               try{
                    page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertRecord</sd:Property>");
                }
        }
    }
    /* viewing only role*/
    page.infoRole = function(inRow){
        var row = dijit.byId("gridRoleId").getItem(inRow);
        sd.connector.post("AdRoleAction!prepareListObject.do?roleId=" + row.roleId +"&roleCode=" + row.code,"objectArea",null,null,page.callback);
    }
    page.infoRoleIdx = function(inRow){
        var row = dijit.byId("gridRoleIdOnDialog").getItem(inRow);
        sd.connector.post("AdRoleAction!prepareListObject.do?roleId=" + row.roleId +"&roleCode=" + row.code,"objectArea",null,null,page.callback);
    }
    page.callEnableRole = function(){
        var content1 = dijit.byId("gridRoleId").vtGetCheckedDataForPost("roleGridForm", "isCheck");
        var content2 = dijit.byId("gridId").vtGetCheckedDataForPost("userGridForm", "isCheck");
        var param = {};
        for (var prop in content1){
            param[prop] = content1[prop];
        }
        for (var prop in content2){
            param[prop] = content2[prop];
        }
        dijit.byId('gridRoleId').vtReload("AdRoleAction!onUnlock.do",null,param,page.afterCallback) ;
    }
     page.onEnableRole = function(){
        if(page.isChecked("gridRoleId")){
             try{
                 page.confirmAsJS("<sd:Property>js.alertEnable</sd:Property>", page.callEnableRole);
             }catch(err){
                 if(confirm("<sd:Property>js.alertEnable</sd:Property>")){
                     page.callEnableRole();
                 }
             }
        }
        else{
              try{
                    page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertRecord</sd:Property>");
                }
        }
    }
    page.callDisableRole = function(){
        var content1 = dijit.byId("gridRoleId").vtGetCheckedDataForPost("roleGridForm", "isCheck");
        var content2 = dijit.byId("gridId").vtGetCheckedDataForPost("userGridForm", "isCheck");
        var param = {};
        for (var prop in content1){
        param[prop] = content1[prop];
        }
        for (var prop in content2){
        param[prop] = content2[prop];
        }
        dijit.byId('gridRoleId').vtReload("AdRoleAction!onLock.do",null,param,page.afterCallback) ;
    }
     page.onDisableRole = function(){
        if(page.isChecked("gridRoleId")){
             try{
                 page.confirmAsJS("<sd:Property>js.alertDisable</sd:Property>", page.callDisableRole);
             }catch(err){
                 if(confirm("<sd:Property>js.alertDisable</sd:Property>")){
                     page.callDisableRole();
                 }
             }
        }
        else{
             try{
                        page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
                        }catch(err){
                            alert("<sd:Property>js.alertRecord</sd:Property>");
                        }
        }
    }
    page.callback=function(){
        var dialog = dijit.byId("dialogObjectId");
        dialog.show();
    }
    page.showInsertRoleDialog=function(){
        <%--page.changeRoleDialogInputStyle();
        page.clearRoleFormOnDialog();--%>
        var dialog = dijit.byId("dialogRoleId");
        <%--page.changeDialogButtonStyle(/*boolean isOnInsert*/true);--%>
        dialog.show();
         try{
             dijit.byId("rolesFormOnDialog.code").focus();
             page.onCustomEnter("rolesFormOnDialog", page.onSearchRoleOnDialog);
        }catch(err){

        }
    }
    
    //********************************DELETE************************************
    page.callDeleteRole=function(){
        var content1 = dijit.byId("gridRoleId").vtGetCheckedDataForPost("roleGridForm", "isCheck");
        var content2 = dijit.byId("gridId").vtGetCheckedDataForPost("userGridForm", "isCheck");
        var param = {};
        for (var prop in content1){
            param[prop] = content1[prop];
        }
        for (var prop in content2){
            param[prop] = content2[prop];
        }
        dijit.byId('gridRoleId').vtReload("AdRoleAction!onDelete.do",null,param,page.afterCallback) ;
    }
    page.onDeleteRole = function (){
        if(page.isChecked("gridRoleId")){
             try{
                 page.confirmAsJS("<sd:Property>js.alertDelete</sd:Property>", page.callDeleteRole);
             }catch(err){
                 if(confirm("<sd:Property>js.alertDelete</sd:Property>")){
                     page.callDeleteRole();
                 }
             }
        }
        else{
            try{
                        page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
                        }catch(err){
                            alert("<sd:Property>js.alertRecord</sd:Property>");
                        }
        }
    }

    //**************************************************************************
    page.changeRoleDialogInputStyle = function(){
        if ((clientAction=="update") || (clientAction=="insert")){
            sd.widget.__setReadOnly("rolesFormOnDialog.roleId",true);
        }
    }

    //isOnInsert = true (on Insert)
    page.changeRoleDialogButtonStyle = function (/*boolean isOnInsert*/ isOnInsert){
        dijit.byId( "rolesFormOnDialog.btnInsert" ).domNode.style.display = isOnInsert?"":"none";
        dijit.byId( "rolesFormOnDialog.btnUpdate" ).domNode.style.display = isOnInsert?"none":"";
    }

    page.clearRoleFormOnDialog = function(/*String*/){
        sd._("rolesFormOnDialog.code").setValue("");
        sd._("rolesFormOnDialog.description").setValue("");
        sd._("rolesFormOnDialog.roleId").setValue("");
        sd._("rolesFormOnDialog.roleName").setValue("");
        sd._("rolesFormOnDialog.status").setValue("");
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
      page.convertStatusToStr=function(cellValue){
            switch(cellValue){
                case 1:
                    return 'Hoạt động';
                case 0:
                    return 'Bị khóa';
            }
        }
      page.urlInfo = function(inRow){
          try{
            var row = dijit.byId("gridRoleId").getItem(inRow);
            if(row != null){
                var url = "<div style='text-align:center;'><img src='${contextPath}/share/images/icons/bookmark.png' width='20px' height='20px' title='Xem chi tiết' onClick='page.infoRole(" + inRow + ")' /></div>";
                return url;
            }
        }catch(err){
            alert(err.message);
        }
    }
    page.urlInfoIdx = function(inRow){
          try{
            var row = dijit.byId("gridRoleIdOnDialog").getItem(inRow);
            if(row != null){
                var url = "<div style='text-align:center;'><img src='${contextPath}/share/images/icons/bookmark.png' width='20px' height='20px' title='Xem chi tiết' onClick='page.infoRoleIdx(" + inRow + ")' /></div>";
                return url;
            }
        }catch(err){
            alert(err.message);
        }
   }
</script>