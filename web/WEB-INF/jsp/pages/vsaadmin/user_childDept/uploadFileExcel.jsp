<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<jsp:include page="/WEB-INF/jsp/pages/config/alertDialog.jsp"/>

<script>

    
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

    page.changeLink = function(){
        var action = dijit.byId("importAction").getValue();

        document.getElementById("linkFile").href =  "${contextPath}/share/Template_" + action +".xls";
    }

    page.exportFile = function(){

        var grid = dijit.byId("resultGridId");

        if (grid.rowCount > 0){
            var call = function(){
                window.location = "uploadFileAction!exportFile.do";
            }
            page.confirmAsJS('<sd:Property>msg.exportFileConfirm</sd:Property>', call);
        }
        else{
            page.alert("Thông báo", "Không có dữ liệu để trích xuất ra file.", "success");
        }
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

    page.upLoadFile= function(){
        // dijit.byId("resultGridId").vtSubmit("UserAction!getImportResult.do",null);

        var fileExtension = /(.xls)$/i;

        if(!document.getElementById("clientFile").value){
            page.alert("Thông báo", "Hãy chọn file trước khi thực hiện thao tác này.", "success");
            return false;
        }else{
            if(!fileExtension.test(document.getElementById("clientFile").value)){
                page.alert("Thông báo", "Bạn cần phải chọn file excel để thực hiện thao tác này.", "success");
                return false;
            }
        }
       
        document.getElementById('frmUpload').target='uploadFrame';
        document.getElementById('frmUpload').submit();
    }
    
    afterCb = function(msg, action){

        var afterCallback = function(){
            dijit.byId("importUserGridId").vtReload("uploadFileAction!getImportInfo.do",null);
        }

        sd._("importFile.curAction").setValue(action);

        if(msg == "success"){
            dijit.byId("resultGridId").vtReload("", null);

            if(action == "create"){
                sd.connector.post("uploadFileAction!preCreateUser.do","importFromFileDiv",null,null,afterCallback);
            }
            else{
                sd.connector.post("uploadFileAction!preImport.do","importFromFileDiv","frmUpload",null,afterCallback);
            }
            
        }else{
            dijit.byId("resultGridId").vtReload("uploadFileAction!getImportResult.do", null);
            dijit.byId("importUserGridId").vtReload("",null);
        }
    }
    
    page.agreeImport = function(){

        if(!page.isChecked("importUserGridId")){
            page.alert("Thông báo","Bạn chưa chọn dòng nào!","warning");
            return false;
        }

        var recordsToImport = dijit.byId("importUserGridId").vtGetCheckedDataForPost("importUserForm", "isCheck");

        var afterCallback = function(response){
            //page.alert("Thông báo","callback function!");
        }

        var call = function(){
            dijit.byId("resultGridId").vtReload("uploadFileAction!importUsers.do", null, recordsToImport, null);
        }

        page.confirmAsJS('<sd:Property>msg.importUserConfirm</sd:Property>', call);

    }

    page.agreeDo = function(){

        if(!page.isChecked("importUserGridId")){
            page.alert("Thông báo","Bạn chưa chọn dòng nào!","warning");
            return false;
        }

        var recordsToImport = dijit.byId("importUserGridId").vtGetCheckedDataForPost("importFileGridForm", "isCheck");

        var afterCallback = function(response){
            // page.alert("Thông báo","import file callback function!");
        }

        var call = function(){
            dijit.byId("resultGridId").vtReload("uploadFileAction!lockUsers.do", "frmUpload", recordsToImport, null);
        }

        page.confirmAsJS('<sd:Property>msg.actionConfirm</sd:Property>', call);

    }

    page.agreeDoAssignObject = function(){
        if(!page.isChecked("importUserGridId")){
            page.alert("Thông báo","Bạn chưa chọn dòng nào!","warning");
            return false;
        }

        var recordsToImport = dijit.byId("importUserGridId").vtGetCheckedDataForPost("importFileGridForm", "isCheck");

        var afterCallback = function(response){
            // page.alert("Thông báo","import file callback function!");
        }

        var call = function(){
            dijit.byId("resultGridId").vtReload("uploadFileAction!assignObject.do", "frmUpload", recordsToImport, null);
        }

        page.confirmAsJS('<sd:Property>msg.actionConfirm</sd:Property>', call);
    }
    page.agreeDoRevokeObject = function(){
        if(!page.isChecked("importUserGridId")){
            page.alert("Thông báo","Bạn chưa chọn dòng nào!","warning");
            return false;
        }

        var recordsToImport = dijit.byId("importUserGridId").vtGetCheckedDataForPost("importFileGridForm", "isCheck");

        var afterCallback = function(response){
            // page.alert("Thông báo","import file callback function!");
        }

        var call = function(){
            dijit.byId("resultGridId").vtReload("uploadFileAction!revokeObject.do", "frmUpload", recordsToImport, null);
        }

        page.confirmAsJS('<sd:Property>msg.actionConfirm</sd:Property>', call);
    }

    page.getIndex = function(inRowIndex){
        return inRowIndex + 1;
    }


    
</script>

<sd:TitlePane key="importFromExcel" id="uploadTltpn">
    <s:form  action="uploadFileAction!uploadFile.do" name="frmUpload" id="frmUpload" method="POST" enctype="multipart/form-data">
        <table width="100%">
            <tr>
                <td>

                </td>
                <td>
                    <sd:Label key="import.chooseFile" bind="" />
                </td>
            </tr>
            <tr>
                <td>
                    <s:file name="clientFile" id="clientFile" size="65%"/>
                </td>

            </tr>
            <br/>
            <tr>
                <td>

                </td>
                <td>
                    <sd:Label key="import.action" bind="" />
                </td>
            </tr>
            <tr>
                <td>

                </td>

                <td>
                    <sd:SelectBox id="importAction" key="" name="importForm.action" cssStyle="width:40%" onchange="page.changeLink()">
                <option value="create">Thêm mới người dùng</option>
<!--                <option value="delete">Xóa người dùng</option>-->
                <option value="lock">Khóa người dùng</option>
                <option value="unlock">Mở khóa người dùng</option>
                <option value="resetPassword">Reset mật khẩu người dùng</option>
                <option value="assignRole">Gán vai trò người dùng</option>
                <option value="revokeRole">Thu hồi vai trò của người dùng</option>
                <option value="setUncheckIP">Gán trạng thái không kiểm tra IP cho người dùng</option>
                <option value="setCheckIP">Xóa trạng thái không kiểm tra IP của người dùng</option>
                <option value="updateIP">Cập nhật địa chỉ IP của người dùng</option>
                <s:if test="#request.sys == 1">
                    <option value="assignModule">Gán chức năng cho vai trò</option>
                    <option value="revokeModule">Thu hồi chức năng của vai trò</option>
                </s:if>
            </sd:SelectBox>
        </td>
    </tr>
    <br/>
    <tr>
        <td>
            <div style="display:none;">
                <sd:TextBox id = "importFile.curAction" name = "importForm.currentAction" key=""/>
            </div>
        </td>
        <td>
            <div align="left">
                <sd:Button id="servicesForm.Upload" key="" onclick="page.upLoadFile()">
                    <img src="share/images/icons/upload.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnUploadFile</sd:Property></span>
                </sd:Button>
            </div>
            <div align="right">
                <a href="${contextPath}/share/Template_create.xls" target="self" id="linkFile">Click vào đây để tải về file mẫu</a>
            </div>
        </td>

    </tr>
</table>

</s:form>
</sd:TitlePane>

<br/>

<div id="importFromFileDiv" >

</div>

<sd:TitlePane key="listImportResult" id="lstImportResultTitlePane">
    <form id="importResultForm">
        <div id="importResultGrid" style="width:100%;height:250px">
            <sd:DataGrid clientSort="true" getDataUrl="" id="resultGridId" style="width: 100%; height: 100%;" container="importResultGrid" rowSelector="20px" rowsPerPage="">
                <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="5%" />
                <sd:ColumnDataGrid editable="false"  key="message" field="result" width="50%" />
            </sd:DataGrid>
        </div>
    </form>
    <div align="center" style="padding-top:20px;">
        <sd:Button id="servicesForm.Upload1" key="" onclick="page.exportFile()">
            <img src="share/images/icons/excel.png" height="20" width="20">
            <span style="font-size:12px"><sd:Property>global.excel</sd:Property></span>
        </sd:Button>
    </div>
</sd:TitlePane>

<div id="area" style="display:none;">
    <sd:Property>upload.msg</sd:Property>
</div>

<iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>


