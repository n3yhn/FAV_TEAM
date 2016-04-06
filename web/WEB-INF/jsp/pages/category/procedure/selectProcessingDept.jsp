<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<script type="text/javascript">

    page.getIndexOfDeptProcessingGrid = function(index) {
        return dijit.byId("deptProcessingGridId").currentRow + index + 1;
    };

    page.selectDeptProcessing = function(inRow) {
        var row = inRow - 1;
        var item = dijit.byId("deptProcessingGridId").getItem(row);
        var url = "";
        if (item != null) {
            url = "<a href='#'><img src='share/images/icons/dept.png' width='16' height='16' onclick='onSelectDeptProcessing(" + row + ");' /> </a>";
        }
        return url;
    };

</script>
<sx:ResultMessage id="resultListDeptProcessingMessage" />

<sd:TitlePane key="departmentForm.title" id="deptProcessingPane">
    <div id="deptSearchDiv">
        <form id="departmentProcessingForm" name="departmentProcessingForm">
            <div>
                <table width="100%">
                    <tr>
                        <td class="tdOnForm">
                            <sd:TextBox id="departmentProcessingForm.deptName" name="departmentForm.deptName" key="departmentForm.deptName" cssStyle="width:80%" trim="true"/>
                            <sd:TextBox id="departmentProcessingForm.parentId" name="departmentForm.parentId" key="" cssStyle="display:none"/>
                            <sd:TextBox id="departmentProcessingForm.procedureDepartmentId" name="departmentForm.procedureDepartmentId" key="" cssStyle="display:none"/>
                        </td>

                        <td class="tdOnForm">
                            <sd:TextBox id="departmentProcessingForm.deptCode" name="departmentForm.deptCode" key="departmentForm.deptCode" cssStyle="width:80%" trim="true"/>
                        </td>

                    </tr>
                    <tr>
                        <td class="tdOnForm">
                            <sd:TextBox id="departmentProcessingForm.address" name="departmentForm.address" key="departmentForm.address" cssStyle="width:80%" trim="true"/>
                        </td>

                        <td class="tdOnForm">
                            <sd:TextBox id="departmentProcessingForm.telephone" name="departmentForm.telephone" key="departmentForm.telephone" cssStyle="width:80%" trim="true"/>
                        </td>

                    </tr>
                </table>
            </div>
            <div align="center" style="padding-top:10px;">
                <sd:Button key="" onclick="page.onSearchDeptProcessing();" >
                    <img src="share/images/icons/search.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
                </sd:Button>
                <sx:ButtonClose onclick="page.closeSelectDeptProcessing();"/>
            </div>

        </form>
    </div>
    <div id="deptProcessingGridDiv" style="width:100%;">
        <sd:DataGrid clientSort="true" 
                     getDataUrl="" 
                     id="deptProcessingGridId"
                     style="width: 100%; height: 100%;" 
                     container="deptProcessingGridDiv" 
                     rowSelector="0px" 
                     rowsPerPage="10">
            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial " cellStyles="text-align:center;" editable="false" key="index" get="page.getIndexOfDeptProcessingGrid" width="5%" />
            <sd:ColumnDataGrid headerStyles="text-align:center;"  cellStyles="text-align:center;"  key="Chọn" get="page.getIndex" formatter="page.selectDeptProcessing" width="5%"/>
            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" editable="false" key="departmentForm.deptName" field="deptName" width="50%"/>
            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" editable="false" key="departmentForm.deptCode" field="deptCode" width="20%"/>
            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" editable="false" key="departmentForm.telephone" field="telephone" width="20%"/>

        </sd:DataGrid>
    </div>
</sd:TitlePane>
<script>

    afterAddDeptProcessingToProcedure = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result0 = result[0];
        if (result0 == "3") {
            resultMessage_show("resultListDeptProcessingMessage", result[0], result[1], 5000);
        } else {
            resultMessage_show("resultDeptMessage", result[0], result[1], 5000);
            page.closeSelectDeptProcessing();
            showProcedureDeptDlg(workingProcedureId);
        }
    };

    afterRemoveDeptProcessingToProcedure = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result0 = result[0];
        resultMessage_show("resultDeptMessage", result[0], result[1], 5000);
        if (result0 == "3") {
        } else {
            showProcedureDeptDlg(workingProcedureId);
        }
    };

    onSelectDeptProcessing = function(row) {
        var item = dijit.byId("deptProcessingGridId").getItem(row);
        var processDeptId = item.deptId;
        var procedureDepartmentId = dijit.byId("departmentProcessingForm.procedureDepartmentId").getValue();
        sd.connector.post("procedureAction!onUpdateDeptProcessing.do?procedureDepartmentId=" + procedureDepartmentId + "&processDeptId=" + processDeptId + "&" + token.getTokenParamString(), null, null, null, afterAddDeptProcessingToProcedure);
    };


    onRemoveDeptProcessing = function(procedureId, deptId) {
        sd.connector.post("procedureAction!onRemoveDeptProcessing.do?procedureId=" + procedureId + "&deptId=" + deptId + "&" + token.getTokenParamString(), null, null, null, afterRemoveDeptProcessingToProcedure);
    };

    page.closeSelectDeptProcessing = function() {
        dijit.byId("selectDepProcessingDlg").hide();
    };

    page.onSearchDeptProcessing = function() {
        dijit.byId('deptProcessingGridId').vtReload("procedureAction!searchDeptAddToProcedure.do?procedureId=" + workingProcedureId, "departmentProcessingForm");
    };

</script>