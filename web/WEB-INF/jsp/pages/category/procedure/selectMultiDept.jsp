<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<script type="text/javascript">

    page.getIndexOfDeptGrid = function(index) {
        return dijit.byId("deptGridId").currentRow + index + 1;
    };

</script>
<sx:ResultMessage id="resultListDeptMessage" />
<sd:TitlePane key="departmentForm.title" id="deptPane">
    <div id="deptSearchDiv">
        <form id="departmentForm" name="departmentForm">
            <div>
                <table width="100%">
                    <tr>
                        <td class="tdOnForm">
                            <sd:TextBox id="departmentForm.deptName" name="departmentForm.deptName" key="departmentForm.deptName" cssStyle="width:80%" trim="true"/>

                        </td>

                        <td class="tdOnForm">
                            <sd:TextBox id="departmentForm.deptCode" name="departmentForm.deptCode" key="departmentForm.deptCode" cssStyle="width:80%" trim="true"/>
                        </td>

                    </tr>
                    <tr>
                        <td class="tdOnForm">
                            <sd:TextBox id="departmentForm.address" name="departmentForm.address" key="departmentForm.address" cssStyle="width:80%" trim="true"/>
                        </td>

                        <td class="tdOnForm">
                            <sd:TextBox id="departmentForm.telephone" name="departmentForm.telephone" key="departmentForm.telephone" cssStyle="width:80%" trim="true"/>
                        </td>

                    </tr>
                </table>
            </div>
            <div align="center" style="padding-top:10px;">
                <sd:Button key="" onclick="page.onSearchDept();" >
                    <img src="share/images/icons/search.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
                </sd:Button>
                <sx:ButtonClose onclick="page.closeMultiSelectDept();"/>
            </div>

        </form>
    </div>
    <div id="DeptGridDiv" style="width:100%;">
        <sd:DataGrid clientSort="true" 
                     getDataUrl="" 
                     id="deptGridId"
                     style="width: 100%; height: 100%;" 
                     container="DeptGridDiv" 
                     rowSelector="0px" 
                     rowsPerPage="10">
            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial " cellStyles="text-align:center;" editable="false" key="index" get="page.getIndexOfDeptGrid" width="5%" />
            <sd:ColumnDataGrid headerStyles="text-align:center;"  cellStyles="text-align:center;"  key="column.checkbox" headerCheckbox="true"  type="checkbox" width="5%"/>
            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" editable="false" key="departmentForm.deptName" field="deptName" width="50%"/>
            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" editable="false" key="departmentForm.deptCode" field="deptCode" width="20%"/>
            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" editable="false" key="departmentForm.telephone" field="telephone" width="20%"/>

        </sd:DataGrid>
    </div>

    <div style="text-align: center">
        <sd:Button id="departmentForm.btnInsert" key="" onclick="selectMultiDept()">
            <img src="share/images/icons/a7.png" height="20" width="20">
            <span style="font-size:12px"><sd:Property>btnSave</sd:Property></span>
        </sd:Button>
    </div>
</sd:TitlePane>
<script>

    afterAddDeptToProcedure = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result0 = result[0];
        if (result0 == "3") {
            resultMessage_show("resultListDeptMessage", result[0], result[1], 5000);
        } else {
            resultMessage_show("resultDeptMessage", result[0], result[1], 5000);
            page.closeMultiSelectDept();
            showProcedureDeptDlg(workingProcedureId);
        }
    };

    selectMultiDept = function() {
        var userGrid = dijit.byId("deptGridId");
        var participantGrid = dijit.byId("participantGrid");
        var items = userGrid.vtGetCheckedItems();
        if (items == null || items.length == 0) {
            alert("Bạn chưa chọn đơn vị tham gia");
            return;
        }
        var lstParam = dijit.byId("deptGridId").vtGetCheckedDataForPost("lstDept");
        sd.connector.post("procedureAction!onInsertDept.do?procedureId=" + workingProcedureId + "&" + token.getTokenParamString(), null, null, lstParam, afterAddDeptToProcedure);

    };

    page.closeMultiSelectDept = function() {
        dijit.byId("selectMultiDepDlg").hide();
    };

    page.onSearchDept = function() {
        dijit.byId('deptGridId').vtReload("procedureAction!searchDeptAddToProcedure.do?procedureId=" + workingProcedureId, "departmentForm");
    };    
</script>