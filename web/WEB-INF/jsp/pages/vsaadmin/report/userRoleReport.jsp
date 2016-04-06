<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<sd:TitlePane key="menu.report.result" id="tltpn2">
    <div id="filterFormDiv">
        <form id="reportForm" name="reportForm">
            <div align="center">
                <table width="100%">
                    <tr>
                        <th width="30%"></th>
                        <th width="70%"></th>
                    </tr>
                    <tr>
                        <td>
                            <sd:Label key="usersForm.department"/>
                        </td>
                        <td style="color:#0066cc;" class="tdOnForm">
                            <sd:TreePicker id="deptTree" getChildrenNodeUrl="UserRoleReportAction!getChildrenDataByNode.do" getTopNodeUrl="UserRoleReportAction!getData.do"  key="" rootLabel="root" cssStyle="width:80%"/>
                            <div style="display:none"><sd:TextBox id="reportForm.deptId" name="reportForm.deptId" key=""/></div>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" colspan="2">
                            <sd:Button id="userReport.view" key="" onclick="page.onView()" >
                                <img src="share/images/icons/3.png" height="20" width="20">
                                <span style="font-size:12px"><sd:Property>global.view</sd:Property></span>
                            </sd:Button>
                            <sd:Button id="userReport.excel" key="" onclick="page.onExcel()" >
                                <img src="share/images/icons/excel.png" height="20" width="20">
                                <span style="font-size:12px"><sd:Property>global.excel</sd:Property></span>
                            </sd:Button>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <form id="form1">
        <div id="gridDiv" style="width:100%;height:500px">
            <sd:DataGrid clientSort="true" getDataUrl="/vsaadminv3/UserRoleReportAction!onInitUserRoleReport.do" id="gridId" style="width:100%; height:100%;" container="gridDiv" rowSelector="20px">
                <sd:ColumnDataGrid editable="false" key="report.index" field="deptLevel" width="5%"/>
                <sd:ColumnDataGrid editable="false" key="report.deptName" field="department" width="10%"/>
                <sd:ColumnDataGrid editable="false" key="report.userName" field="userName" width="15%"/>
                <sd:ColumnDataGrid editable="false" key="report.fullName" field="fullName" width="15%"/>
                <sd:ColumnDataGrid editable="false" key="report.roleId" field="roleCode" width="15%"/>
                <sd:ColumnDataGrid editable="false" key="report.roleName" field="roleName" width="10%"/>
                <sd:ColumnDataGrid editable="false" key="report.userStatus" field="userStatus" width="10%"/>
                <sd:ColumnDataGrid editable="false" key="report.roleStatus" field="roleStatus" width="10%"/>
                <sd:ColumnDataGrid editable="false" key="report.userRoleStatus" field="userRoleStatus" width="10%"/>
            </sd:DataGrid>
        </div>
    </form>
</sd:TitlePane>

<script>
    dijit.byId("deptTree").onPickData = function(item){

        try{
            if(item.id){
                sd._("reportForm.deptId").setValue(item.id);
            }else {
                sd._("reportForm.deptId").setValue("");
                dijit.byId("deptTree").setValue("");
            }
        }catch(err){
            alert(err.message);
        }
    }

    sd.widget.__setReadOnly("deptTree",true);
    page.onView = function(){
        var temp = "" + dijit.byId('reportForm.deptId').value;
        if (temp == ""){
            alert('Bạn chưa chọn phòng ban!');
            return false;
        }
        dijit.byId('gridId').vtReload("UserRoleReportAction!onViewUserRoleReport.do", "reportForm") ;
    }
    page.onExcel = function(){
        var temp = "" + dijit.byId('reportForm.deptId').value;
        if (temp == ""){
            alert('Bạn chưa chọn phòng ban!');
            return false;
        }
        window.location = "UserRoleReportAction!onExcelUserRoleReport.do?deptId=" + temp;
    }
</script>