<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<script>
    var clientAction = "none";//"search","insert","update","delete","none";

    //********************************SEARCH************************************
    var callBack = function (data){
        eval("var temp = "+data.label+";");
        sd._("unitTreeForm.deptCode").setValue(temp.code);
        sd._("unitTreeForm.deptName").setValue(temp.name);
        sd._("unitTreeForm.deptEmail").setValue(temp.email);
        sd._("unitTreeForm.deptAddress").setValue(temp.address);
        sd._("unitTreeForm.deptPhone").setValue(temp.phone);
        sd._("unitTreeForm.deptDescription").setValue(temp.description);
    }
    
    page.onSearch = function (){
        dijit.byId('gridId').vtReload("UnitTreeAction!onSearch.do","unitTreeForm",null, callBack) ;
    }

    page.onTreeClick = function(item, node){
        if (item.id == undefined){
            sd.$("unitTreeForm.parentId").value = "0";
        }
        else{
            sd.$("unitTreeForm.parentId").value = item.id;
            
            if (item.treeName != undefined && item.treeName != ""){
                var dialog = dijit.byId("dialogId");
                sd._("unitTreeFormOnDialog.treeName").setValue(item.treeName);
                sd._("unitTreeFormOnDialog.description").setValue(item.description);
                sd._("unitTreeFormOnDialog.appId").setValue(item.appId);
                if (item.rootDeptId != undefined && item.rootDeptId != ""){
                    sd.$("unitTreeFormOnDialog.rootDeptId").value = item.rootDeptId;
                }
                sd.$("unitTreeFormOnDialog.id").value = item.treeId;
            }
        }
        page.onSearch();
    }
    //********************************INSERT************************************
    page.preInsert = function (){
        clientAction = "insert";
        page.changeDialogInputStyle();
        page.clearFormOnDialog();
        var dialog = dijit.byId("dialogId");
        dialog.titleNode.innerHTML = "<sd:Property>global.new</sd:Property>";
        page.changeDialogButtonStyle(/*boolean isOnInsert*/true);
        dialog.show();
    }
    page.onInsert = function (){
        if(!dijit.byId("unitTreeFormOnDialog.treeName").getValue()){
            page.alert("Thông báo",'Bạn chưa nhập tên cây.', "error");
            dijit.byId("unitTreeFormOnDialog.treeName").focus();
            return false;
        }

        if(!dijit.byId("unitTreeFormOnDialog.appId").getValue()){
            page.alert("Thông báo",'Bạn chưa chọn ứng dụng.', "error");
            dijit.byId("unitTreeFormOnDialog.appId").focus();
            return false;
        }

        sd.connector.post("UnitTreeAction!onInsert.do", null, "unitTreeFormOnDialog", null, page.callback);
    <%--dijit.byId('gridId').vtReload("UnitTreeAction!onInsert.do","unitTreeFormOnDialog", null, page.callback) ;--%>
        }

        page.onAddDept = function(){
            var treeId = sd.$("unitTreeForm.parentId").value;

            if (treeId == "0"){
                page.alert("Thông báo", "Hãy chọn cây đơn vị/node cần thêm phòng ban con.", "success");
                return;
            }
            else if (treeId.indexOf("tree") != -1){
                if (treeId.indexOf("_0") == -1){
                    page.alert("Thông báo", "Cây đã tồn tại gốc.", "success");
                    return;
                }
            }

            var dialog = dijit.byId("dialogIdDept");
            dialog.titleNode.innerHTML = "<sd:Property>unitTreeForm.adDept</sd:Property>";


            if (treeId.indexOf("tree") != -1){
                var temp = treeId.split("_");
                sd.$("unitTreeFormOnDialogDept.treeId").value = temp[1];
                sd.$("unitTreeFormOnDialogDept.parentId").value = "0";
            }
            else{
                sd.$("unitTreeFormOnDialogDept.parentId").value = treeId;
            }

            sd.$("unitTreeFormOnDialogDept.deptId").value = "0";
        
            dialog.show();
        }

        page.onReDept = function(){
            var treeId = sd.$("unitTreeForm.parentId").value;
            if (treeId.indexOf("tree") != -1 || treeId == "0"){
                page.alert("Thông báo", "<sd:Property>unitTreeForm.selectDeptWarning</sd:Property>", "success");
                return;
            }

            try{
                page.confirmAsJS("<sd:Property>js.alertRemoveDeptFromUnitTree</sd:Property>",page.callReDept);
            }catch(err){
                if (confirm("<sd:Property>js.alertRemoveDeptFromUnitTree</sd:Property>")){
                    page.callReDept();
                }
            }
        }
        
        page.callReDept = function(){
            sd.connector.post("UnitTreeAction!onReDept.do", null, "unitTreeForm", null, page.callback);
        }

        page.callReUser=function(){
            var content = dijit.byId("gridId").vtGetCheckedDataForPost("userGridForm", "isCheck");
            dijit.byId("gridId").vtReload("UnitTreeAction!onRemoveUser.do", "unitTreeForm", content, null);
        }

        page.onReUser = function(){
            if(!page.isChecked("gridId")){
                page.alert("Thông báo", "<sd:Property>global.selectWarning</sd:Property>", "success");
                return false;
            }
            try{
                page.confirmAsJS("<sd:Property>js.alertRemoveUserFromUnitTree</sd:Property>",page.callReUser);
            }catch(err){
                if (confirm("<sd:Property>js.alertRemoveUserFromUnitTree</sd:Property>")){
                    page.callReUser();
                }
            }
        }

        page.onAddUser = function(){
            var treeId = sd.$("unitTreeForm.parentId").value;

            if (treeId == "0"){
                page.alert("Thông báo", "Hãy chọn một phòng ban cần thêm người dùng.", "success");
                return;
            }
            else if (treeId.indexOf("tree") != -1){
                if (treeId.indexOf("_0") == -1){
                    page.alert("Thông báo", "Hãy chọn một phòng ban cần thêm người dùng.", "success");
                    return;
                }
            }

            var dialog = dijit.byId("dialogIdUser");
            dialog.titleNode.innerHTML = "<sd:Property>unitTreeForm.adUser</sd:Property>";

            sd.$("unitTreeFormOnDialogUser.parentId").value = treeId;

            dialog.show();
        }

        //********************************UPDATE************************************
        page.preUpdate = function (){
            var treeId = sd.$("unitTreeForm.parentId").value;

            if (treeId.indexOf("tree") == -1){
                page.alert("Thông báo", "<sd:Property>unitTreeForm.selectWarning</sd:Property>", "success");
                return;
            }

            clientAction = "update";
            page.changeDialogInputStyle();
        
            var dialog = dijit.byId("dialogId");
            dialog.titleNode.innerHTML = "<sd:Property>global.modify</sd:Property>";
            page.changeDialogButtonStyle(false);

            dialog.show();
        }
        page.onUpdate = function (){
            if(!dijit.byId("unitTreeFormOnDialog.treeName").getValue()){
                page.alert("Thông báo",'Bạn chưa nhập tên cây.', "error");
                dijit.byId("unitTreeFormOnDialog.treeName").focus();
                return false;
            }

            if(!dijit.byId("unitTreeFormOnDialog.appId").getValue()){
                page.alert("Thông báo",'Bạn chưa chọn ứng dụng.', "error");
                dijit.byId("unitTreeFormOnDialog.appId").focus();
                return false;
            }

            sd.connector.post("UnitTreeAction!onUpdate.do", null, "unitTreeFormOnDialog", null, page.callback);
    <%--dijit.byId('gridId').vtReload("UnitTreeAction!onUpdate.do","unitTreeFormOnDialog", null, page.callback) ;--%>
        }
        //********************************DELETE************************************
        page.onDelete = function (){
            var treeId = sd.$("unitTreeForm.parentId").value;
            if (treeId.indexOf("tree") == -1){
                page.alert("Thông báo", "<sd:Property>unitTreeForm.selectWarning</sd:Property>", "success");
                return;
            }

            try{
                page.confirmAsJS("<sd:Property>global.deleteWarning</sd:Property>",page.callDelete);
            }catch(err){
                if (confirm("<sd:Property>global.deleteWarning</sd:Property>")){
                    page.callDelete();
                }
            }
        }

        page.callDelete = function(){
            sd.connector.post("UnitTreeAction!onDelete.do", null, "unitTreeForm", null, page.callback);
        }

        //**************************************************************************
        page.changeDialogInputStyle = function(){
   
        }

        //isOnInsert = true (on Insert)
        page.changeDialogButtonStyle = function (/*boolean isOnInsert*/ isOnInsert){
            dijit.byId( "unitTreeFormOnDialog.btnInsert" ).domNode.style.display = isOnInsert?"":"none";
            dijit.byId( "unitTreeFormOnDialog.btnUpdate" ).domNode.style.display = isOnInsert?"none":"";

            if (isOnInsert == false){
                sd.widget.__setReadOnly("unitTreeFormOnDialog.appId",true);
            }
            else{
                sd.widget.__setReadOnly("unitTreeFormOnDialog.appId",false);
            }
        }

        page.clearFormOnDialog = function(/*String*/){
            sd._("unitTreeFormOnDialog.appId").setValue("");
            sd._("unitTreeFormOnDialog.description").setValue("");
            sd._("unitTreeFormOnDialog.treeName").setValue("");
            sd.$("unitTreeFormOnDialog.rootDeptId").value = "";
            sd.$("unitTreeFormOnDialog.id").value = "";
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

        page.onSearchUser = function(){
            dijit.byId('listUserGridId').vtReload("UnitTreeAction!onSearchUser.do","unitTreeFormOnDialogUser") ;
        }

        page.onSelect = function(){
            var afterInsertUser = function(){
                dijit.byId("listUserGridId").vtReload();
                dijit.byId("dialogIdUser").hide();
            }

            if (!page.isChecked("listUserGridId")){
                page.alert("Thông báo", "<sd:Property>global.selectWarning</sd:Property>", "success");
                return;
            }


            var content = dijit.byId("listUserGridId").vtGetCheckedDataForPost("userGridForm", "isCheck");

            dijit.byId("gridId").vtReload("UnitTreeAction!onSelectUser.do", "unitTreeFormOnDialogUser", content, afterInsertUser);
        }

        page.callback = function(data){
            eval("var obj = " + data + ";" );
            switch (obj.customInfo[0])
            {
                case "addOk":
                    page.alert("Thông báo", "<sd:Property>js.alertInsert</sd:Property>", "success");
                    sd._("unitTreeFormOnDialog.treeName").setValue("");
                    sd._("unitTreeFormOnDialog.description").setValue("");
                    sd._("unitTreeFormOnDialog.appId").setValue("");
                    break;

                case "updateOk":
                    page.alert("Thông báo", "<sd:Property>js.alertUpdate</sd:Property>", "success");
                    break;

                case "exist":
                    page.alert("Thông báo", "Ứng dụng bạn chọn đã tồn tồn tại cây đơn vị.", "success");
                    break;

                case "deleteOk":
                    if (obj.customInfo[1].length > 0){
                        page.alert("Thông báo", "Một số bản ghi không thể xóa được do đã được sử dụng trong hệ thống", "success");
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
