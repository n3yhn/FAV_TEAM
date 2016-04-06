<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<script>
    var clientAction = "none";//"search","insert","update","delete","none";

    page.getIndex = function(inRowIndex){
        return inRowIndex + 1;
    }

    page.selectAll = function(gridId){
      
        var grid = dijit.byId(gridId);
//        alert(grid._by_idx);
        for (var idx in grid._by_idx){
//            alert("3"+idx);
            var item = grid._by_idx[idx].item;
            item["isCheck"]="true";
        }
      
    }
    page.onCheckName=function()
    {
        //  var value=sd.validator.trim(obj.value);
        var value=sd.validator.trim(document.getElementById("departmentFormOnDialog.deptName").value);
        if(value.length>0){
            var id=document.getElementById("departmentFormOnDialog.deptId").value;
            var url="departmentAction!onCheckName.do?id="+id;
            var target="";
            var form   ="departmentFormOnDialog";
            var content = null;
            sd.connector.post(url,target,form,content,page.callbackCheckName);
        }
    }
    page.callbackCheckName=function(data, bSuccess)
    {
        var returnData=dojo.fromJson(data);
        var result=returnData.customInfo.toString();
        if(result=='false'){
            page.onCheckCode();
        }
        else if(result=='true'){
            page.alert("Thông báo","Tên phòng ban đã tồn tại " , "warning");
            dijit.byId("departmentFormOnDialog.deptName").focus();
        }
    }
    page.onCheckCode=function()
    {     var value=sd.validator.trim(document.getElementById("departmentFormOnDialog.deptCode").value);
        //   var value=sd.validator.trim(obj.value);
        if(value.length>0){
            var id=document.getElementById("departmentFormOnDialog.deptId").value;
            var url="departmentAction!onCheckCode.do?id="+id;
            var target="";
            var form   ="departmentFormOnDialog";
            var content = null;
            sd.connector.post(url,target,form,content,page.callbackCheckCode);
        }
    }
    page.callbackCheckCode=function(data, bSuccess){
        var returnData=dojo.fromJson(data);
        var result=returnData.customInfo.toString();
        if(result=='false'){

            if(dijit.byId("departmentFormOnDialog.type").value==-1)
            {
                page.alert("Thông báo","Bạn chưa chọn loại phòng ban", "warning");
                dijit.byId("departmentFormOnDialog.type").focus();
                return false;
            }
            if(!dijit.byId("departmentFormOnDialog.type").getValue()){
                //isValidate = false;
                page.alert("Thông báo","<sd:Property>msg.deptType.nullValue</sd:Property>", "warning");
                dijit.byId("departmentFormOnDialog.type").focus();
                return false;
            }
            if(!dijit.byId("departmentFormOnDialog.status").getValue()){
                //isValidate = false;
                page.alert("Thông báo",'<sd:Property>msg.deptStatus.null</sd:Property>', "warning");
                dijit.byId("departmentFormOnDialog.status").focus();
                return false;
            }

            if(dijit.byId("departmentFormOnDialog.email").getValue()){

                if(!sd.validator.isEmail(dijit.byId("departmentFormOnDialog.email").getValue())){
                    page.alert("Thông báo","<sd:Property>msg.incorrectEmail</sd:Property>", "warning");
                    dijit.byId("departmentFormOnDialog.email").focus();
                    return false;
                }
            }

            //]
            var depId=  sd._("departmentFormOnDialog.deptId").getValue();
            console.log("depId"+depId);
            if(depId.toString().length==0)
                msg.confirm('<sd:Property>confirm.update</sd:Property>',"<sd:Property>confirm.title</sd:Property>",page.insertDept)
            else
                msg.confirm('<sd:Property>confirm.update</sd:Property>',"<sd:Property>confirm.title</sd:Property>",page.updateDept )
             
        }
        else if(result=='true'){
            page.alert("Thông báo","Mã phòng ban đã tồn tại " , "warning");
            dijit.byId("departmentFormOnDialog.deptCode").focus();
        }
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
        page.clearFormOnDialog();
        //alert(data.customInfo[0]);
        switch (data.customInfo[0]){
            case "deleteSuccess":
                page.alert("Thông báo","<sd:Property>msg.deleteOk</sd:Property>", "info");
                sd.connector.post("departmentAction!getTreeDept.do","treeDiv",null,null);
                break;
            case "deletePart":
                page.alert("Thông báo","Các bản ghi sau không xóa được do còn ràng buộc: " + data.customInfo[1], "warning");
                sd.connector.post("departmentAction!getTreeDept.do","treeDiv",null,null);
                break;
            case "deleteFail":
                page.alert("Thông báo", "<sd:Property>msg.deleteErr</sd:Property>", "error");
                break;
            case "insertOk":
                page.alert("Thông báo","<sd:Property>msg.insertOk</sd:Property>", "info");
                sd.connector.post("departmentAction!getTreeDept.do","treeDiv",null,null);
                break;
            case "insertErr":
                page.alert("Thông báo","<sd:Property>msg.insertErr</sd:Property>", "error");
                break;
            case "updateOk":
                page.alert("Thông báo","<sd:Property>msg.updateOk</sd:Property>", "info");
                sd.connector.post("departmentAction!getTreeDept.do","treeDiv",null,null);
                break;
            case "updateErr":
                page.alert("Thông báo","<sd:Property>msg.UpdateErr</sd:Property>", "error");
                break;

            case "parentNOK":
                page.alert("Thông báo","<sd:Property>msg.department.parentNOK</sd:Property>", "warning");
                break;
            case "notFalcuty":
                page.alert("Thông báo","Bộ môn phải có phòng ban cha là khoa", "warning");
                break;
            default:
                break;
            }
        }

        page.callbackByUpdate = function(data, bSuccess){

            var dialog = dijit.byId("dialogDeptId");
            dialog.hide();
            page.clearFormOnDialog();
           
            switch (data.customInfo[0]){
                case "deleteSuccess":
                    page.alert("Thông báo","<sd:Property>msg.deleteOk</sd:Property>", "info");
                    sd.connector.post("departmentAction!getTreeDept.do","treeDiv",null,null);
                    break;
                case "deletePart":
                    page.alert("Thông báo","Các bản ghi sau không xóa được do còn ràng buộc: " + data.customInfo[1], "warning");
                    sd.connector.post("departmentAction!getTreeDept.do","treeDiv",null,null);
                    break;
                case "deleteFail":
                    page.alert("Thông báo", "<sd:Property>msg.deleteErr</sd:Property>", "error");
                    break;
                case "insertOk":
                    page.alert("Thông báo","<sd:Property>msg.insertOk</sd:Property>", "info");
                    sd.connector.post("departmentAction!getTreeDept.do","treeDiv",null,null);
                    break;
                case "insertErr":
                    page.alert("Thông báo","<sd:Property>msg.insertErr</sd:Property>", "error");
                    break;
                case "updateOk":
                    page.alert("Thông báo","<sd:Property>msg.updateOk</sd:Property>", "info");
                    sd.connector.post("departmentAction!getTreeDept.do","treeDiv",null,null);
                    break;
                case "updateErr":
                    page.alert("Thông báo","<sd:Property>msg.UpdateErr</sd:Property>", "error");
                    break;

                case "parentNOK":
                    page.alert("Thông báo","<sd:Property>msg.department.parentNOK</sd:Property>", "warning");
                    break;
                case "notFalcuty":
                    page.alert("Thông báo","Bộ môn phải có phòng ban cha là khoa", "warning");
                    break;
                default:
                    break;
                }
            }

            //********************************SEARCH************************************
            page.onSearch = function (){
                dijit.byId('deptGridId').vtReload("departmentAction!onSearchChildrenDept.do","departmentForm") ;
            }
            //********************************INSERT************************************
            page.preInsert = function (){
                
                page.clearFormOnDialog();
                var dialog = dijit.byId("dialogDeptId");
                dialog.titleNode.innerHTML = "Thêm mới phòng ban, đơn vị";
                page.changeDialogButtonStyle(/*boolean isOnInsert*/true);
                
                var deptId = sd._("departmentForm.deptId").getValue();
                sd._("departmentFormOnDialog.parentId").setValue(deptId);
//                alert(sd._("departmentForm.deptParentName").getValue());
                sd.connector.post("departmentAction!onGetParentName.do?parentId=" + deptId,null,null,null, function (data){
                    var returnData=dojo.fromJson(data);
                    var result=returnData.customInfo.toString();
                    sd._("parentDeptTree").setValue(result);
                 });
//                sd._("parentDeptTree").setValue(sd._("departmentForm.deptParentName").getValue());
                dijit.byId("parentDeptTree").getChildrenNodeUrl="departmentAction!getDeptChildrenDataByNode.do";
                dialog.show();
            }
            
            page.onInsert = function (){
                //dijit.byId('dialogDeptId').hide();

                //[ Validation
                if(!dijit.byId("departmentFormOnDialog.deptName").getValue()){
                    //isValidate = false;
                    page.alert("Thông báo","<sd:Property>msg.deptName.null</sd:Property>", "warning");
                    dijit.byId("departmentFormOnDialog.deptName").focus();
                    return false;
                }

                if(!dijit.byId("departmentFormOnDialog.deptCode").getValue()){
                    //isValidate = false;
                    page.alert("Thông báo","<sd:Property>msg.deptCode.null</sd:Property>", "warning");
                    dijit.byId("departmentFormOnDialog.deptCode").focus();
                    return false;
                }
                
                var provinceName = dijit.byId("departmentFormOnDialog.provinceId").attr("displayedValue");
                dijit.byId("departmentFormOnDialog.provinceName").setValue(provinceName);
                
                //page.onCheckName();
                page.onCheckCode();
            }
            page.insertDept = function (){
                var childDeptModule = "true";
                dijit.byId('deptGridId').vtReload("departmentAction!onInsert.do?childDeptModule="+childDeptModule+"&"+ token.getTokenParamString(),"departmentFormOnDialog", null, page.callback) ;
            }
            //********************************UPDATE************************************
            page.preUpdate = function (inRow){
                page.clearFormOnDialog();
                var dialog = dijit.byId("dialogDeptId");
                dialog.titleNode.innerHTML = "Cập nhật thông tin";
                page.changeDialogButtonStyle(false);

                var row= dijit.byId("deptGridId").getItem(inRow);
            
                sd._("departmentFormOnDialog.address").setValue(row.address);
                sd._("departmentFormOnDialog.deptCode").setValue(row.deptCode);
                sd._("departmentFormOnDialog.contactName").setValue(row.contactName);
                sd._("departmentFormOnDialog.contactTitle").setValue(row.contactTitle);
                sd._("departmentFormOnDialog.deptId").setValue(row.deptId);
                sd._("departmentFormOnDialog.deptName").setValue(row.deptName);
                sd._("departmentFormOnDialog.description").setValue(row.description);
                sd._("departmentFormOnDialog.email").setValue(row.email);
                sd._("departmentFormOnDialog.fax").setValue(row.fax);
        
                sd._("departmentFormOnDialog.status").setValue(row.status);
                sd._("departmentFormOnDialog.tel").setValue(row.tel);
                sd._("departmentFormOnDialog.telephone").setValue(row.telephone);
                sd._("departmentFormOnDialog.tin").setValue(row.tin);
                sd._("departmentFormOnDialog.type").setValue(row.deptTypeId);
                sd._("departmentFormOnDialog.provinceId").setValue(row.provinceId);
                sd._("departmentFormOnDialog.provinceName").setValue(row.provinceName);

                sd._("departmentFormOnDialog.parentId").setValue(row.parentId);
                sd.connector.post("departmentAction!onGetParentName.do?parentId=" + row.parentId,null,null,null, function (data){
                    var returnData=dojo.fromJson(data);
                    var result=returnData.customInfo.toString();
                    sd._("parentDeptTree").setValue(result);
                 });
                dijit.byId("parentDeptTree").getChildrenNodeUrl="departmentAction!getDeptChildrenDataByNode.do?deptId=" + row.deptId;
                console.log(row.parentId);
                if(row.parentId.toString().length==0){
                    sd._("parentDeptTree").setValue("");
                }
                else
                //        {row= dijit.byId("deptGridId").getItem(0);
                //         sd._("parentDeptTree").setValue(row.deptName);}
                    sd.connector.post("departmentAction!onGetParentName.do?parentId=" + row.parentId,null,null,null,   page.setParentDeptName);
                dialog.show();
            }

            page.setParentDeptName = function (data, bSuccess)
            {
                var returnData=dojo.fromJson(data);
                var result=returnData.customInfo.toString();
                sd._("parentDeptTree").setValue(result);
            }
            page.onUpdate = function (){

                //[ Validation
                if(!dijit.byId("departmentFormOnDialog.deptName").getValue()){

                    page.alert("Thông báo","<sd:Property>msg.deptName.null</sd:Property>", "warning");
                    dijit.byId("departmentFormOnDialog.deptName").focus();
                    return false;
                }

                if(!dijit.byId("departmentFormOnDialog.deptCode").getValue()){
                    //isValidate = false;
                    page.alert("Thông báo","<sd:Property>msg.deptCode.null</sd:Property>", "warning");
                    dijit.byId("departmentFormOnDialog.deptCode").focus();
                    return false;
                }
                //page.onCheckName();
                page.onCheckCode();
            }
            page.updateDept = function (){
                var childDept = "true";
                dijit.byId('deptGridId').vtReload("departmentAction!onUpdate.do?childDept="+childDept+"&"+ token.getTokenParamString(),"departmentFormOnDialog", null, page.callbackByUpdate) ;
            }
        
            page.isEnableSelect = function(gridId){
                var bReturn = true;
                var grid = dijit.byId(gridId);
                var checkedItems=grid.vtGetCheckedItems();
                for(var i = 0;i<checkedItems.length;i++){
                    var item = checkedItems[i];
                    if (item["status"][0] == "1"){
                        bReturn = true;
                        break;
                    }
                }
                return bReturn;
            }
            //********************************DELETE************************************
            page.onDelete = function (){
                if (!dijit.byId("deptGridId").vtIsChecked()){
                    page.alert("Thông báo","<sd:Property>msg.unselect</sd:Property>", "warning");
                    return false;
                }
                else{
                    msg.confirm('<sd:Property>confirm.delete</sd:Property>',"",page.deleteItems)
                }
            }

            page.deleteItems=function(){
                var recordsToDelete = dijit.byId("deptGridId").vtGetCheckedDataForPost("deptGridForm");
                sd.connector.post("departmentAction!onDelete.do?"+ token.getTokenParamString(),null,null,recordsToDelete,page.showMessage);
            }
            page.showMessage = function(data){
                var obj = JSON.parse(data);
                var status = obj.customInfo;
                var lst=obj.items;
                document.getElementById("lblSuccessMessage").innerHTML=lst[0];
                document.getElementById("lblErrorMessage").innerHTML=lst[1];
                if(status.toString().indexOf('1') >= 0){
                    document.getElementById("tblSuccessMessage").style.display='';
                }else if(status.toString().indexOf('2') >= 0){
                    document.getElementById("tblErrorMessage").style.display='';
                }
                else if(status.toString().indexOf('3') >= 0){
                    document.getElementById("tblErrorMessage").style.display='';
                    document.getElementById("tblSuccessMessage").style.display='';
                }
                var items = dijit.byId("deptGridId").vtGetCheckedItems();
                var lengthItems = items.length;

                for(var i = 0; i< lengthItems;i++){
                    var itemDel = items[i];
                    var notDel=false;
                    if(lst.length>2){
                        for(var j = 2;j<lst.length;j++){
                            if(itemDel.deptId!=null&&lst[j]==itemDel.deptId.toString()){
                                notDel=true;
                            }
                        }
                    }
                    if(!notDel){
                        dijit.byId("deptGridId").store.deleteItem(itemDel);
                    }
                }
                window.setTimeout( "hideMessage()", 5000 );
//                var items = dijit.byId("deptGridId").vtGetCheckedItems();
                var deptId = items[0].parentId;
                sd._("departmentForm.deptId").setValue(deptId);
                dijit.byId('deptGridId').vtReload("departmentAction!onSearchChildrenDept.do","departmentForm") ;
                //sd.connector.post("departmentAction!getTreeDept.do","treeDiv",null,null);
                dijit.byId("userDeptGridId").vtReload("", null, null, null);
            }
            hideMessage = function(){
                document.getElementById("tblErrorMessage").style.display='none';
                document.getElementById("tblSuccessMessage").style.display='none';
            }
            //**************************************************************************
            page.changeDialogInputStyle = function(){
                if ((clientAction=="update") || (clientAction=="insert")){
                    sd.widget.__setReadOnly("departmentFormOnDialog.deptId",true);
                }
            }

            //isOnInsert = true (on Insert)
            page.changeDialogButtonStyle = function (/*boolean isOnInsert*/ isOnInsert){
                dijit.byId( "departmentFormOnDialog.btnInsert" ).domNode.style.display = isOnInsert?"":"none";
                dijit.byId( "departmentFormOnDialog.btnUpdate" ).domNode.style.display = isOnInsert?"none":"";
            }

            page.clearFormOnDialog = function(/*String*/){
                sd._("departmentFormOnDialog.address").setValue("");
                sd._("departmentFormOnDialog.deptCode").setValue("");
                sd._("departmentFormOnDialog.contactName").setValue("");
                sd._("departmentFormOnDialog.contactTitle").setValue("");
                sd._("departmentFormOnDialog.deptId").setValue("");
                sd._("departmentFormOnDialog.deptName").setValue("");
                sd._("departmentFormOnDialog.description").setValue("");
                sd._("departmentFormOnDialog.email").setValue("");
                sd._("departmentFormOnDialog.fax").setValue("");
                sd._("departmentFormOnDialog.type").setValue("-1");
            
                sd._("departmentFormOnDialog.tel").setValue("");
                sd._("departmentFormOnDialog.telephone").setValue("");
                sd._("departmentFormOnDialog.tin").setValue("");
                sd._("departmentFormOnDialog.provinceId").setValue("-1");
                sd._("departmentFormOnDialog.provinceName").setValue("");
            
                //
                sd._("parentDeptTree").setValue("");
            }

            page.convertStringToDate = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dgDate){
                try{
                    var temp = dgDate.split("-");
                    return new Date(temp[1]+"/"+temp[2].split("T")[0]+"/"+temp[0]);
                }catch(e){
                    page.alert("Thông báo","function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss", "warning");
                    return undefined;
                }
            }

            //******************
            page.convertDeptStatus = function(inDatum){
                switch(inDatum){
                    case 1:
                        return 'Hoạt động';
                    case 0:
                        return 'Bị khóa';
                }
            }

            page.convertDeptType = function(inDatum){
                switch(inDatum){
                    case 61:
                        return 'Phòng ban';
                    case 62:
                        return 'Phòng ban';
                    default:
                        return 'Phòng ban';
                }
            }

            //*****************************************************************************
            page.convertUserStatus = function(inDatum){
                switch(inDatum){
                    case 1:
                        return 'Hoạt động';
                    case 0:
                        return 'Bị khóa';
                }
            }
            page.listUserOfDept= function (rowIndex){

                var row= dijit.byId("deptGridId").getItem(rowIndex);
                sd._("departmentFormOnDialog.deptId").setValue(row.deptId);
                sd._("addUserToDeptForm.deptId").setValue(row.deptId);
                sd._("userOfDeptForm.deptId").setValue(row.deptId);


                //     var dialog = dijit.byId("dialogListUserOfDept");
                //  sd._("addUserToDeptForm.deptId").setValue(row.deptId);
                //       dialog.show();
                //  var afterCallback = function(response){}
                dijit.byId("userDeptGridId").vtReload("departmentAction!onInitUserList.do", "departmentFormOnDialog", null, null);

            
            }

            //-------------
            page.onSearchUser = function(){
                dijit.byId('listUnassignedUserGridId').vtReload("departmentAction!searchUser.do", "addUserToDeptForm");
            }
            //-------------
            page.preAddUserToDept = function (rowIndex){
            
                var row= dijit.byId("deptGridId").getItem(rowIndex);




                var dialog = dijit.byId("dialogAddUserId");

                sd._("addUserToDeptForm.deptId").setValue(row.deptId);
                dialog.show();
                dijit.byId("listUnassignedUserGridId").vtReload("departmentAction!searchUser.do", null);
            }
            //---------------------
            page.addUser = function(){

                dijit.byId("listUnassignedUserGridId").edit.apply();
                // kiem tra da chon ban ghi nao chua
                if (!dijit.byId("listUnassignedUserGridId").vtIsChecked()){
                    page.alert("Thông báo","<sd:Property>msg.unselectUser</sd:Property>", "warning");
                    return false;
                }
                var recordsToAssign = dijit.byId("listUnassignedUserGridId").vtGetCheckedDataForPost("listUnassignedUserForm");
                // kiem tra da chon chuc danh chua
                //            if(!page.isChoosePos("listUnassignedUserGridId")){
                //                page.alert("Thông báo", "<sd:Property>msg.unselectPosition</sd:Property>", "warning");
                //                return false;
                //            }

                var afterCallback = function(response){
                    dijit.byId("dialogAddUserId").hide();
                }

                var call = function(){
                    dijit.byId("userDeptGridId").vtReload("departmentAction!assignUser.do?"+ token.getTokenParamString(), "addUserToDeptForm", recordsToAssign, afterCallback);
                }
                //listUnassignedUserForm
                page.confirmAsJS('<sd:Property>msg.actionConfirm</sd:Property>', call);
            }

            page.isChoosePos = function(gridId){
                var bReturn = true;
                var grid = dijit.byId(gridId);
                var checkedItems=grid.vtGetCheckedItems();
                for(var i = 0;i<checkedItems.length;i++){
                    var item = checkedItems[i];
                    if(item.posId == null || item.posId == ""){
                        bReturn = false;
                        break;
                    }
                }
                return bReturn;
            }

            //-----------
            page.onRemoveUser = function(){
                if (!dijit.byId("userDeptGridId").vtIsChecked()){
                    page.alert("Thông báo","<sd:Property>msg.unselectUser</sd:Property>", "warning");
                    return false;
                }
                var recordsToRemove = dijit.byId("userDeptGridId").vtGetCheckedDataForPost("userOfDeptForm");

                var afterCallback = function(response){
                    page.alert("Thông báo","<sd:Property>msg.removeUserOk</sd:Property>", "info");
                }

                var call = function(){
                    dijit.byId("userDeptGridId").vtReload("departmentAction!removeUser.do?"+ token.getTokenParamString(), "userOfDeptForm", recordsToRemove, afterCallback);
                }

                page.confirmAsJS('<sd:Property>msg.actionConfirm</sd:Property>', call);

            }

</script>