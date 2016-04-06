<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<script type="text/javascript">
    page.deleteParticipantFormat = function(inRow) {
        var item = dijit.byId("participantGrid").getItem(inRow);
        if (item != null) {
            var url = "<a href='#'><img src='share/images/delete.png' width='16' height='16' onclick='deleteParticipant(" + inRow + ");sd.operator.displayWaitScreen(false);' /> </a>"
            return url;
        }
    };
    
    page.getIndexOfProcedureDept = function(index) {
        return dijit.byId("participantGrid").currentRow + index + 1;
    };
    
    page.selectProcessingDept = function(inRow){
        var row = inRow-1;
        var item = dijit.byId("participantGrid").getItem(row);
        if (item != null) {
            var url = "";
            if(item.processDeptName !== null && item.processDeptName != "" ){
                url = "<a href='#'>"+item.processDeptName+"<img src='share/images/icons/dept.png' width='16' height='16' title='Chọn đơn vị xử lý' onclick='showSelectDeptProcessing(" + row + ");' /><img src='share/images/delete.png' title='Xóa đơn vị xử lý' width='16' height='16' onclick='removeDeptProcessing(" + row + ");' /> </a>";
            } else {
                url = "<a href='#'><img src='share/images/icons/dept.png' width='16' height='16' onclick='showSelectDeptProcessing(" + row + ");' /> </a>"; 
            }
            return url;
        }
        
    };   
</script>
<sx:ResultMessage id="resultDeptMessage" />
<table width="100%">
    <tr>
        <td>
            <sd:DataGrid id="participantGrid" getDataUrl="" rowsPerPage="50">
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" 
                                   editable="false" key="index" get="page.getIndexOfProcedureDept" 
                                   width="5%" cellStyles="text-align:center;"/>
                <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="10%" cellStyles="text-align:center;" />
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" 
                                   editable="false" key="Đơn vị" field="deptName" 
                                   width="40%"/>
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" 
                                   editable="false" key="Phòng ban xử lý" formatter="page.selectProcessingDept" get="page.getIndex"
                                   width="40%"/>

            </sd:DataGrid>

        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <sd:Button key="" onclick="showMultiSelectDept();">
                <img src="share/images/icons/listUser.png" width="14" height="14" alt="Chọn đơn vị tham gia" title="Chọn đơn vị tham gia"/>
                <span style="font-size:12px">Thêm đơn vị</span>
            </sd:Button>
            <sx:ButtonDelete onclick="deleteProcedureDept();" />
            <sx:ButtonClose onclick="onCloseProcedureDept();"/>
        </td>
    </tr>
</table>

<script type="text/javascript">
    showMultiSelectDept = function() {
        dijit.byId("selectMultiDepDlg").show();
    };
    
    showSelectDeptProcessing = function(row){
        dijit.byId("selectDepProcessingDlg").show();
        var item = dijit.byId("participantGrid").getItem(row);
        dijit.byId("departmentProcessingForm.parentId").setValue(item.deptId);
        dijit.byId("departmentProcessingForm.procedureDepartmentId").setValue(item.procedureDepartmentId);
        page.onSearchDeptProcessing();        
    };
    
    removeDeptProcessing = function(row){
        var item = dijit.byId("participantGrid").getItem(row);
        onRemoveDeptProcessing(item.procedureId,item.deptId);
    };

    showProcedureDeptDlg = function(procedureId) {
        dijit.byId("participantGrid").vtReload("procedureAction!getDeptProcedure.do?procedureId=" + procedureId);
        dijit.byId("dlgDeptProcedure").show();
    };

    afterDeleteProcedureDept = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultDeptMessage", result[0], result[1], 5000);
        showProcedureDeptDlg(workingProcedureId);
    };

    deleteProcedureDept = function() {
        var lstParam = dijit.byId("participantGrid").vtGetCheckedDataForPost("lstProcedureDept");
        sd.connector.post("procedureAction!onDeleteDept.do?" + token.getTokenParamString(), null, null, lstParam, afterDeleteProcedureDept)
        return false;
    };

    validateData = function() {
        var title = dijit.byId("createForm.title").getValue();
        if (title == null || title.toString().trim().length == 0) {
            alert("Chưa nhập nội dung");
            dijit.byId("createForm.title").focus();
            return false;
        }

        var location = dijit.byId("createForm.location").getValue();
        if (location == null || location.toString().trim().length == 0) {
            alert("Chưa nhập địa điểm");
            dijit.byId("createForm.location").focus();
            return false;
        }

        var startTime = dijit.byId("createForm.startTime").getValue();
        if (startTime == null) {
            alert("Chưa nhập thời gian bắt đầu");
            dijit.byId("createForm.startTime").focus();
            return false;
        }
        var crtDate = new Date();
        if (startTime < crtDate) {
            alert("Thời gian bắt đầu đã qua!");
            dijit.byId("createForm.startTime").focus();
            return false;
        }

        //var curentTime = new Date();

        var endTime = dijit.byId("createForm.endTime").getValue();
//        if (endTime == null) {
//            alert("Chưa nhập thời gian kết thúc");
//            dijit.byId("createForm.endTime").focus();
//            return false;
//        }

        if (endTime != null && (startTime - endTime == 0)) {
            alert("Thời gian kết thúc phải lớn hơn Thời gian bắt đầu");
            dijit.byId("createForm.endTime").focus();
            return false;
        }
        //alert(startTime - endTime);
        //if (startTime == endTime) {
        //    alert("Thời gian kết thúc phải lớn hơn Thời gian bắt đầu");
        //    dijit.byId("createForm.startTime").focus();
        //   return false;
        //}

        if (endTime != null && (startTime > endTime)) {
            alert("Thời gian bắt đầu diễn ra sau thời gian kết thúc");
            dijit.byId("createForm.startTime").focus();
            return false;
        }

        var lstParam = dijit.byId("participantGrid").getItem(0);
        if (lstParam == null)
        {
            alert("Bạn chưa chọn người tham gia.");
            return;
        }

        var chiefName = dijit.byId("createForm.chiefName").getValue();
        //chiefName = chiefName.trim();
        // Check Radio
        var itemChief = dijit.byId("participantGrid").vtGetRadioChecked();
        if (itemChief == null || itemChief.length == 0) {

            if (chiefName == null || chiefName.length == 0)
            {
                alert("Bạn chưa chọn người chủ trì.");
                dijit.byId("createForm.chiefName").focus();
                return;
            }
        }

        if (itemChief != null)
        {
            itemChief.chieftFlag = '1L';
        }

        // Chck Prepare
        var lstPrepare = dijit.byId("participantGrid").vtGetCheckedItems();
        if (lstPrepare == null || lstPrepare.length == 0) {
            alert("Bạn chưa chọn người chuẩn bị.");
            return;
        }
        return true;
    };

    afterUpdateProcedureDept = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result0 = result[0];
        if (result0 == "3") {
            resultMessage_show("resultCreateMessage", result[0], result[1], 5000);
        } else {
            resultMessage_show("resultMessage", result[0], result[1], 5000);
            onClose();
            onSearch();
        }
    };

    onUpdateProcedureDept = function() {
        if (validateData()) {
            var lstParam = dijit.byId("participantGrid").vtGetTotalDataForPost("lstParticipant");
            sd.connector.post("calendarAction!onCreate.do?" + token.getTokenParamString(), null, "createForm", lstParam, afterUpdateProcedureDept);
        }
    };

    onCloseProcedureDept = function() {
        var dlg = dijit.byId("dlgDeptProcedure");
        dlg.hide();
    };
</script>