<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<script>
    var clientAction = "none";//"search","insert","update","delete","none";

    //**********************************   ROLE   **********************************
    //********************************SEARCH************************************
    page.onSearch = function (){
        dijit.byId('gridId').vtReload("RolesAction!onSearch.do","rolesForm") ;
    }
    
    //********************************INSERT************************************
    page.preInsert = function () {
        clientAction = "insert";
        page.clearFormOnDialog();
        var dialog = dijit.byId("dialogId");
        dialog.titleNode.innerHTML = "<sd:Property>global.new</sd:Property>";
        page.changeDialogButtonStyle(true); /*boolean isOnInsert*/
        dialog.show();
    }
    
    page.validateRole = function(){
        var code = document.getElementById("rolesFormOnDialog.code");
        if(code.value == null || code.value.toString().trim()== "") {
            page.alert("Thông báo",'Bạn chưa nhập mã vai trò.', "error");
            dijit.byId("rolesFormOnDialog.code").focus();
            return false;
        } else if(code.value.toString().length>50){
            page.alert("alert","Mã vai trò không được quá 50 ký tự");
            dijit.byId('rolesFormOnDialog.code').focus();
            return false;
        }

        var name = document.getElementById("rolesFormOnDialog.roleName");
        if(name.value == null || name.value.toString().trim()== "") {
            page.alert("Thông báo",'Bạn chưa nhập tên vai trò.', "error");
            dijit.byId("rolesFormOnDialog.roleName").focus();
            return false;
        } else if(name.value.toString().length>100){
            page.alert("alert","Tên vai trò không được quá 100 ký tự");
            dijit.byId('rolesFormOnDialog.roleName').focus();
            return false;
        }

        var description = document.getElementById("rolesFormOnDialog.description");
        if(description.value != null && description.value.toString().length>100){
            page.alert("alert","Mô tả không được quá 100 ký tự");
            dijit.byId('rolesFormOnDialog.description').focus();
            return false;
        }

        //        if(!dijit.byId("rolesFormOnDialog.status").getValue()) {
        //            page.alert("Thông báo",'Bạn chưa nhập trạng thái vai trò.', "error");
        //            dijit.byId("rolesFormOnDialog.status").focus();
        //            return false;
        //        }

        return true;
    }

    page.onInsert = function () {
        var callback = function(){
            dijit.byId('gridId').vtReload("RolesAction!onInsert.do?"+ token.getTokenParamString(),"rolesFormOnDialog", null, page.callback);
        }
        if(page.validateRole())
            page.confirmAsJS("Bạn có chắc muốn thêm mới", callback);
    }

    //********************************UPDATE************************************
    page.preUpdate = function (inRow) {
        var row = dijit.byId("gridId").getItem(inRow);
        clientAction = "update";
        page.clearFormOnDialog();
        var dialog = dijit.byId("dialogId");
        dialog.titleNode.innerHTML = "<sd:Property>global.modify</sd:Property>";
        page.changeDialogButtonStyle(false);

        // Thiet lap du lieu mac dinh tu du lieu cu
        sd._("rolesFormOnDialog.code").setValue(row.roleCode);
        sd._("rolesFormOnDialog.description").setValue(row.description);
        sd._("rolesFormOnDialog.roleName").setValue(row.roleName);
        sd._("rolesFormOnDialog.status").setValue(row.status);
        document.getElementById("rolesFormOnDialog.roleId").value = row.roleId;
        dialog.show();
    }
    
    page.viewFunction = function (inRow) {
        var row = dijit.byId("gridId").getItem(inRow);

        var roleName = " <font color='red'>"+ row.roleName + "</font>";
        dijit.byId('tltpnFunction').setTitle("<sd:Property>rolesForm.functionCatalog</sd:Property>" + roleName);

        sd.$("rolesForm.roleIdArr").value = row.roleId;

        var dialog = dijit.byId("roleObjectDialogId");
        dialog.titleNode.innerHTML = "<sd:Property>global.modify</sd:Property>";

        dijit.byId("rolesForm.objects.code").setValue("");
        dijit.byId("rolesForm.objects.name").setValue("");
        dijit.byId("rolesForm.objects.status").setValue("ALL");
        dijit.byId("rolesForm.objects.appInfo").setValue("");
        dialog.show();
        dijit.byId('gridFunctionId').vtReload("RolesAction!onSearchFunction.do","form2") ;
    }

    page.onUpdate = function () {
        var callback = function(){
            dijit.byId('gridId').vtReload("RolesAction!onUpdate.do?"+ token.getTokenParamString(),"rolesFormOnDialog", null, page.callback) ;
        }
        if(page.validateRole())
            page.confirmAsJS("Bạn có chắc muốn cập nhật", callback);
    }

    //********************************DELETE************************************
    page.onDelete = function () {
        if(!page.isChecked("gridId")){
            page.alert("Thông báo", "<sd:Property>global.selectWarning</sd:Property>", "success");
            return false;
        }

        /*
        if (page.isEnableSelect("gridId")){
            page.alert("Thông báo", "<sd:Property>global.deleteWarning1</sd:Property>", "success");
            return false;
        }
         */
        
        if (confirm("<sd:Property>js.alertDeleteRole</sd:Property>")) {
            page.callDeleteRole();
        }
    }

    page.callDeleteRole=function() {
        var gridDiv = dijit.byId("gridId");
        var content = gridDiv.vtGetCheckedDataForPost("roleFormSelection");
        //        sd.connector.post("RolesAction!onDelete.do", "bodyContent", null, content, page.callback);
        //
        //        var content = dijit.byId("gridId").vtGetCheckedDataForPost("roleFormSelection", "isCheck");
        dijit.byId("gridId").vtReload("RolesAction!onDelete.do?"+ token.getTokenParamString(), "rolesForm", content, page.callback);
    }

    //********************************LOCK**************************************
    page.validateAllLock = function(gridId){
        var bReturn = false;
        var lstItem = dijit.byId(gridId).vtGetCheckedItems();
        for(var i=0;i<lstItem.length;i++){
            var item = lstItem[i];
            if(item.status == 1 || item.status == "1"){
                //
                // Co item dang mo
                //
                bReturn = true;
                break;
            }
        }
        //
        // Tat ca cac item deu da khoa
        //
        return bReturn;
    }

    page.validateAllUnLock = function(gridId){
        var bReturn = false;
        var lstItem = dijit.byId(gridId).vtGetCheckedItems();
        for(var i=0;i<lstItem.length;i++){
            var item = lstItem[i];
            if(item.status == 0 || item.status == "0"){
                //
                // Co item dang khoa
                //
                bReturn = true;
                break;
            }
        }
        //
        // Tat ca cac item deu da mo
        //
        return bReturn;
    }

    page.onLock = function () {
        if(!page.isChecked("gridId")) {
            page.alert("Thông báo", "<sd:Property>global.selectWarning</sd:Property>", "success");
            return false;
        }

        if(!page.validateAllLock("gridId")){
            page.alert("Thông báo", "Bản ghi bạn chọn đang bị khóa");
            return false;
        }

        if (confirm("<sd:Property>js.alertLockRole</sd:Property>")) {
            page.callLockRole();
        }
    }

    page.callLockRole=function() {
        var gridDiv = dijit.byId("gridId");
        var content = gridDiv.vtGetCheckedDataForPost("roleFormSelection");
        var lstItem = gridDiv.vtGetCheckedItems();
        if(lstItem == null || lstItem.length == 0){
            page.alert("alert", "Không có dữ liệu cập nhật");
        }

        for(var i =0;i<lstItem.length;i++){
            
        }
        //        sd.connector.post("RolesAction!onLock.do", "bodyContent", null ,content, page.callback);
        //
        //        var content = dijit.byId("gridId").vtGetCheckedDataForPost("roleFormSelection", "isCheck");
        dijit.byId("gridId").vtReload("RolesAction!onLock.do?"+ token.getTokenParamString(), "rolesForm", content, page.callback);
    }

    //********************************UnLOCK**************************************
    page.onUnLock = function () {
        if(!page.isChecked("gridId")){
            page.alert("Thông báo", "<sd:Property>global.selectWarning</sd:Property>", "success");
            return false;
        }

        if(!page.validateAllUnLock("gridId")){
            page.alert("Thông báo", "Bản ghi bạn chọn đang hoạt động");
            return false;
        }

        if (confirm("<sd:Property>js.alertUnLockRole</sd:Property>")) {
            page.callUnLockRole();
        }
    }

    page.callUnLockRole=function() {
        var gridDiv = dijit.byId("gridId");
        var content = gridDiv.vtGetCheckedDataForPost("roleFormSelection");
        //        sd.connector.post("RolesAction!onUnLock.do", "bodyContent", null ,content, page.callback);
        //
        //        var content = dijit.byId("gridId").vtGetCheckedDataForPost("roleFormSelection", "isCheck");
        dijit.byId("gridId").vtReload("RolesAction!onUnLock.do?"+ token.getTokenParamString(), "rolesForm", content, page.callback);
    }

    //****************************    END ROLE    **********************************

    //****************************    FUNCTION    **********************************
    //****************************    SEARCH    ********************************
    page.onSearchFunction = function() {

        dijit.byId('gridFunctionId').vtReload("RolesAction!onSearchFunction.do","form2") ;
    }

    //****************************    INSERT    ********************************
    page.onPreInsertFunction = function() {
        
        dijit.byId('gridFunctionId').vtReload("RolesAction!onSearchFunction.do","form2") ;

        var dialog = dijit.byId("objectsDialogId");
        dialog.titleNode.innerHTML = "<sd:Property>rolesFrom.objects.title</sd:Property>";
        sd.$("objectsForm.roleId").value = sd.$("rolesForm.roleIdArr").value;
        dialog.show();
    }

    page.onSearchFunctionToAdd = function(){
        var roleId = sd.$("objectsForm.roleId").value;
        var parentId = sd.$("objectsForm.parentId").value;
        //page.alert("alert",roleId+"-"+parentId,"");
        dijit.byId('gridFunctionDialogId').vtReload("RolesAction!onSearchFunctionToAdd.do?roleId="+roleId +"&parentId="+parentId,"objectsForm") ;
    }

    page.onInsertFunction = function() {
        if (page.isChecked("gridFunctionDialogId") == false){
            page.alert("Thông báo", "<sd:Property>global.selectWarning</sd:Property>", "success");
            return;
        }

        var afterInsertFunction = function() {
            var roleId = sd.$("objectsForm.roleId").value;
            var parentId = sd.$("objectsForm.parentId").value;
            //dijit.byId('gridFunctionDialogId').vtReload("RolesAction!onSearchFunctionToAdd.do","objectsForm") ;
            dijit.byId('gridFunctionDialogId').vtReload("RolesAction!onSearchFunctionToAdd.do?roleId="+roleId +"&parentId="+parentId,"objectsForm") ;
        }

        sd.$("rolesForm.parentId").value = sd.$("objectsForm.parentId").value;
        var content = dijit.byId("gridFunctionDialogId").vtGetCheckedDataForPost("objectFormSelection");
        dijit.byId("gridFunctionId").vtReload("RolesAction!onInsertFunction.do?"+ token.getTokenParamString(), "form2", content, afterInsertFunction);
    }

    //****************************    LOCK    **********************************

    page.onLockFunction = function(){
        if(!page.isChecked("gridFunctionId")){
            page.alert("Thông báo", "<sd:Property>global.selectWarning</sd:Property>", "success");
            return false;
        }
       
        if (confirm("<sd:Property>js.alertLockFunctionInRole</sd:Property>")) {
            page.callLockFunctionInRole();
        }
    }
    
    page.callLockFunctionInRole=function() {
        //        var content = dijit.byId("gridFunctionId").vtGetCheckedDataForPost("objectFormSelection", "isCheck");
        //        dijit.byId("gridFunctionId").vtReload("RolesAction!onLockFunction.do", "form2", content, null);

        var gridFunction = dijit.byId("gridFunctionId");
        var content = gridFunction.vtGetCheckedDataForPost("objectFormSelection");
        dijit.byId("gridFunctionId").vtReload("RolesAction!onLockFunction.do?"+token.getTokenParamString(), "form2", content, page.callback);
        //sd.connector.post("RolesAction!onLockFunction.do", "bodyContent", "form2" ,content, page.callback);
    }

    //****************************    UNLOCK    ********************************
    page.onUnLockFunction = function(){
        if(!page.isChecked("gridFunctionId")){
            page.alert("Thông báo", "<sd:Property>global.selectWarning</sd:Property>", "success");
            return false;
        }

        try{
            page.confirmAsJS("<sd:Property>js.alertUnLockFunctionInRole</sd:Property>",page.callUnLockFunctionInRole);
        }catch(err){
            if (confirm("<sd:Property>js.alertUnLockFunctionInRole</sd:Property>")){
                page.callUnLockFunctionInRole();
            }
        }
    }
    
    page.callUnLockFunctionInRole=function(){
        var gridFunction = dijit.byId("gridFunctionId");
        var content = gridFunction.vtGetCheckedDataForPost("objectFormSelection");
        dijit.byId("gridFunctionId").vtReload("RolesAction!onUnLockFunction.do?"+token.getTokenParamString(), "form2", content, page.callback);
    }

    //****************************    DELETE    ********************************
    page.onDeleteFunction = function(){
        if(!page.isChecked("gridFunctionId")){
            page.alert("Thông báo", "<sd:Property>global.selectWarning</sd:Property>", "success");
            return false;
        }

        if (confirm("<sd:Property>js.alertDeleteFunctionInRole</sd:Property>")) {
            page.callDeleteFunctionInRole();
        }
    }
    
    page.callDeleteFunctionInRole=function(){
        //        var content = dijit.byId("gridFunctionId").vtGetCheckedDataForPost("objectFormSelection", "isCheck");
        //        dijit.byId("gridFunctionId").vtReload("RolesAction!onDeleteFunction.do", "form2", content, null);

        var gridFunction = dijit.byId("gridFunctionId");
        var content = gridFunction.vtGetCheckedDataForPost("objectFormSelection");
        var roleId = sd.$("rolesForm.roleIdArr").value;
        //        sd.connector.post("RolesAction!onDeleteFunction.do", "gridFunctionId", "form2" ,content, page.callback);
        dijit.byId("gridFunctionId").vtReload("RolesAction!onDeleteFunction.do?roleId="+roleId+"&"+token.getTokenParamString(), "form2", content, page.callback);
    }




    

    //isOnInsert = true (on Insert)
    page.changeDialogButtonStyle = function (isOnInsert){
        dijit.byId( "rolesFormOnDialog.btnInsert" ).domNode.style.display = isOnInsert ? "" : "none";
        dijit.byId( "rolesFormOnDialog.btnUpdate" ).domNode.style.display = isOnInsert ? "none" : "";

        /*
            if (isOnInsert){
                sd.widget.__setReadOnly("rolesFormOnDialog.status",false);
            } else {
                sd.widget.__setReadOnly("rolesFormOnDialog.status",true);
            }

            if ((clientAction=="update")){
                sd.widget.__setReadOnly("rolesFormOnDialog.code",true);
            } else {
                sd.widget.__setReadOnly("rolesFormOnDialog.code",false);
            }
         */
    }

    page.clearFormOnDialog = function(/*String*/){
        // Du lieu hidden
        sd._("rolesFormOnDialog.code").setValue("");
        sd._("rolesFormOnDialog.roleName").setValue("");
        sd._("rolesFormOnDialog.description").setValue("");
        sd._("rolesFormOnDialog.status").setValue("1");

        // Du lieu hien thi
        sd.$("rolesFormOnDialog.codeSearch").value = sd._("rolesForm.codeSearch").getValue();
        sd.$("rolesFormOnDialog.nameSearch").value = sd._("rolesForm.nameSearch").getValue();
        sd.$("rolesFormOnDialog.descriptionSearch").value = sd._("rolesForm.descriptionSearch").getValue();
        sd.$("rolesFormOnDialog.statusSearch").value = sd._("rolesForm.statusSearch").getValue();
    }

    page.convertStringToDate = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dgDate){
        try{
            var temp = dgDate.split("-");
            return new Date(temp[1]+"/"+temp[2].split("T")[0]+"/"+temp[0]);
        }catch(e){
            page.alert("Thông báo", "function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss", "success");
            page.dialogAlert("");
            return undefined;
        }
    }

    page.selectAll = function(gridId){
        var grid = dijit.byId(gridId);
        for (var idx in grid._by_idx){
            var item = grid._by_idx[idx].item;
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

    /********************* Check whether any row is checked *************************/
    page.isChecked = function(gridId){

        var grid = dijit.byId(gridId);
        if (!grid.vtIsChecked()){
            return false;
        }

        return true;

        //        var bReturn = false;
        //        var grid = dijit.byId(gridId);
        //        for (var idx in grid.store._arrayOfAllItems){
        //            var item = grid.store._arrayOfAllItems[idx];
        //            if (item["isCheck"][0] == "true" || item["isCheck"][0] ){
        //
        //                bReturn = true;
        //                break;
        //            }
        //        }
        //        return bReturn;
    }
    
    page.isEnableSelect = function(gridId){

        var grid = dijit.byId(gridId);
        var items = grid.vtGetCheckedItems();

        for(var i = 0;i<items.length;i++){
            var item = items[i];
            if (item["status"] == "1") {
                return true;
            }
        }
        return false;

        //        var bReturn = false;
        //        var grid = dijit.byId(gridId);
        //        for (var idx in grid.store._arrayOfAllItems){
        //            var item = grid.store._arrayOfAllItems[idx];
        //            alert(item["roleCode"]);
        //
        //            if (item["status"][0] == "1") {
        //                alert("KO XOA");
        //                bReturn = true;
        //                break;
        //            }
        //        }
        //        return bReturn;


        //        var bReturn = false;
        //        var grid = dijit.byId(gridId);
        //        for (var idx in grid.store._arrayOfAllItems){
        //            var item = grid.store._arrayOfAllItems[idx];
        //            if ((item["isCheck"][0] == "true" || item["isCheck"][0]) && item["status"][0] == "1"){
        //                bReturn = true;
        //                break;
        //            }
        //        }
        //        return bReturn;
    }

    page.callback = function(data) {
        
        //        var obj = data;
        //        alert(data.customInfo);
        var status = data.customInfo;
        switch (status) {
            case "addOk":
                page.alert("Thông báo", "<sd:Property>js.alertInsert</sd:Property>", "success");
                sd._("rolesFormOnDialog.code").setValue("");
                sd._("rolesFormOnDialog.description").setValue("");
                sd._("rolesFormOnDialog.roleName").setValue("");
                sd._("rolesFormOnDialog.status").setValue("1");
                break;
            case "existName":
                page.alert("Thông báo", "Trùng tên vai trò", "success");
                sd._("rolesFormOnDialog.roleName").focus();
                break;
            case "existCode":
                page.alert("Thông báo", "Trùng mã vai trò", "success");
                sd._("rolesFormOnDialog.code").focus();
                break;
            case "updateOk":
                page.alert("Thông báo", "<sd:Property>js.alertUpdate</sd:Property>", "success");
                break;

            case "exist":
                page.alert("Thông báo", "<sd:Property>js.alertExist</sd:Property>", "success");
                sd._("rolesFormOnDialog.code").setValue("");
                break;

            case "lockOk":
                page.alert("Thông báo", "<sd:Property>js.alertLockResult</sd:Property>", "success");
                break;

            case "lockNotOk":
                page.alert("Thông báo", "<sd:Property>js.alertNotLockResult</sd:Property>", "success");
                break;

            case "notLockByUser":
                page.alert("Thông báo", "Có vai trò không khóa được do đang đăng nhập", "success");
                break;

            case "unLockOk":
                page.alert("Thông báo", "<sd:Property>js.alertUnLockResult</sd:Property>", "success");
                break;

            case "unLockNotOk":
                page.alert("Thông báo", "<sd:Property>js.alertNotUnLockResult</sd:Property>", "success");
                break;

            case "deleteOk":
                page.alert("Thông báo", "<sd:Property>js.alertDel</sd:Property>", "success");
                break;

            case "deleteNotOk":
                page.alert("Thông báo", "Xóa không thành công do có ràng buộc!", "success");
                break;
            case "lockFunctionOk":
                page.alert("Thông báo", "Khóa chức năng thành công!", "success");
                break;
            case "lockFunctionError":
                page.alert("Thông báo", "Khóa chức năng không thành công!", "success");
                break;
            case "unlockFunctionOk":
                page.alert("Thông báo", "Mở khóa chức năng thành công!", "success");
                break;
            case "unlockFunctionError":
                page.alert("Thông báo", "Mở khóa chức năng không thành công!", "success");
                break;
            default :
                page.alert("Thông báo", status, "error");
                break;
            }
            //            page.onSearch();
        }
</script>