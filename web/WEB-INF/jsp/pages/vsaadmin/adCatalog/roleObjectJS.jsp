<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
  <%--  var clientAction = "none";//"search","insert","update","delete","none";

    //********************************SEARCH************************************
    page.onSearchObject = function (){
        dijit.byId('gridObjectId').vtSubmit("ObjectsAction!onSearch.do","objectsForm") ;
    }
    //********************************INSERT************************************
    page.preInsertObject = function (){
        clientAction = "insert";
        page.changeObjectDialogInputStyle();
        page.clearObjectFormOnDialog();
        var dialog = dijit.byId("dialogObjectId");
        dialog.titleNode.innerHTML = "<s:property value='getText(\'Thêm mới\')' />";
        page.changeObjectDialogButtonStyle(/*boolean isOnInsert*/true);
        dialog.show();
    }
    page.onInsertObject = function (){
        var objectName = null;
        var status = null;
        var objectType = null;
        try{
            objectName = dijit.byId('objectsForm.objectName').getValue();
            status = dijit.byId('objectsForm.status').getValue();
            objectType = dijit.byId('objectsForm.objectType').getValue();
        }catch(err){
            alert(err.toString());
        }
        if(objectName==null||objectName==""){
            alert("<s:property value='getText(\'Bạn phải nhập tên chức năng!\')' />");
        }
        else if(objectType==null||objectType==""){
            alert("<s:property value='getText(\'Bạn phải chọn loại chức năng!\')' />")
        }
        else if(status==null||status==""){
            alert("<s:property value='getText(\'Bạn phải chọn trạng thái!\')' />")
        }
        else{
            dijit.byId('gridObjectId').vtSubmit("ObjectsAction!onInsert.do","objectsForm") ;
        //}
    }

    //********************************UPDATE************************************
    page.preUpdateObject = function (){
        clientAction = "update";
        page.changeObjectDialogInputStyle();
        page.clearObjectFormOnDialog();
        var dialog = dijit.byId("dialogObjectId");
        dialog.titleNode.innerHTML = "Sửa thông tin chức năng";
        page.changeObjectDialogButtonStyle(false);

        var row= dijit.byId("gridObjectId").selection.getSelected()[0];
        if (row == undefined) {
            alert('Chọn bản ghi để chỉnh sửa');
            return false;
        };
        sd._("objectsFormOnDialog.appId").setValue(row.appId);
        sd._("objectsFormOnDialog.description").setValue(row.description);
        sd._("objectsFormOnDialog.objectId").setValue(row.objectId);
        sd._("objectsFormOnDialog.objectName").setValue(row.objectName);
        sd._("objectsFormOnDialog.objectType").setValue(row.objectType);
        sd._("objectsFormOnDialog.objectUrl").setValue(row.objectUrl);
        sd._("objectsFormOnDialog.ord").setValue(row.ord);
        sd._("objectsFormOnDialog.parentId").setValue(row.parentId);
        sd._("objectsFormOnDialog.status").setValue(row.status);

        dialog.show();
    }
    page.onUpdateObject = function (){
        dijit.byId('gridObjectId').vtSubmit("ObjectsAction!onUpdate.do","objectsFormOnDialog") ;
    }
    //********************************DELETE************************************
    page.onDeleteObject = function (){
        clientAction = "delete";
        page.clearObjectFormOnDialog();

        var row= dijit.byId("gridId").selection.getSelected()[0];
        if (row == undefined) {
            alert('Chá»?n báº£n ghi cáº§n xÃ³a!');
            return false;
        };
        sd._("objectsFormOnDialog.appId").setValue(row.appId);
        sd._("objectsFormOnDialog.description").setValue(row.description);
        sd._("objectsFormOnDialog.objectId").setValue(row.objectId);
        sd._("objectsFormOnDialog.objectName").setValue(row.objectName);
        sd._("objectsFormOnDialog.objectType").setValue(row.objectType);
        sd._("objectsFormOnDialog.objectUrl").setValue(row.objectUrl);
        sd._("objectsFormOnDialog.ord").setValue(row.ord);
        sd._("objectsFormOnDialog.parentId").setValue(row.parentId);
        sd._("objectsFormOnDialog.status").setValue(row.status);

        if (confirm("XÃ³a báº£n ghi Ä?Ã£ chá»?n?")){
            dijit.byId('gridId').vtSubmit("ObjectsAction!onDelete.do","objectsFormOnDialog") ;
        }
    }

    //**************************************************************************
    page.changeObjectDialogInputStyle = function(){
        if ((clientAction=="update") || (clientAction=="insert")){
            sd.widget.__setReadOnly("objectsFormOnDialog.objectId",true);
        }
    }

    //isOnInsert = true (on Insert)
    page.changeObjectDialogButtonStyle = function (/*boolean isOnInsert*/ isOnInsert){
        dijit.byId( "objectsFormOnDialog.btnInsert" ).domNode.style.display = isOnInsert?"":"none";
        dijit.byId( "objectsFormOnDialog.btnUpdate" ).domNode.style.display = isOnInsert?"none":"";
    }

    page.clearObjectFormOnDialog = function(/*String*/){
        sd._("objectsFormOnDialog.appId").setValue("");
        sd._("objectsFormOnDialog.description").setValue("");
        sd._("objectsFormOnDialog.objectId").setValue("");
        sd._("objectsFormOnDialog.objectName").setValue("");
        sd._("objectsFormOnDialog.objectType").setValue("");
        sd._("objectsFormOnDialog.objectUrl").setValue("");
        sd._("objectsFormOnDialog.ord").setValue("");
        sd._("objectsFormOnDialog.parentId").setValue("");
        sd._("objectsFormOnDialog.status").setValue("");
    }

    page.convertStringToDate = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dgDate){
        try{
            var temp = dgDate.split("-");
            return new Date(temp[1]+"/"+temp[2].split("T")[0]+"/"+temp[0]);
        }catch(e){
            alert("function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss");
            return undefined;
        }
    }--%>
        page.convertStatusToStr=function(cellValue){
            switch(cellValue){
                case 1:
                    return 'Hoạt động';
                case 0:
                    return 'Bị khóa';
            }
        }
        page.convertTypeToStr=function(cellValue){
            switch(cellValue){
                case 0:
                    return 'Module';
                case 1:
                    return 'Component';
            }
        }
</script>