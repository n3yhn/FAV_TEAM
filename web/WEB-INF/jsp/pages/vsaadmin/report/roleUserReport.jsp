<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<sd:TitlePane key="menu.report.result" id="tltpn2">
    <div id="filterFormDiv">
        <form id="reportForm" name="reportForm">
            <div align="center">
                <table width="100%">
                    <tr>
                        <td>
                            <sd:TextBox id="reportForm.roleCodeSearch" name="reportForm.roleCode" key="roleUserForm.code" cssStyle="width:80%" trim="true"/>
                        </td>
                        <td>
                            <sd:TextBox id="reportForm.roleNameSearch" name="reportForm.roleName" key="roleUserForm.roleName" cssStyle="width:80%" trim="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <sd:TextBox id="reportForm.objectCodeSearch" name="reportForm.objectCode" key="objectsForm.objectCode" cssStyle="width:80%" trim="true"/>
                        </td>
                        <td>
                            <sd:TextBox id="reportForm.objectNameSearch" name="reportForm.objectName" key="objectsForm.objectName" cssStyle="width:80%" trim="true"/>
                        </td>
                    </tr>
                </table>
            </div>
            <div align="center">
                <table width="100%">
                    <tr>
                        <td align="center">
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
            <sd:DataGrid clientSort="true" getDataUrl="/vsaadminv3/RoleUserReportAction!onInitUserRoleReport.do" id="gridId" style="width:100%; height:100%;" container="gridDiv" rowSelector="20px">
                <sd:ColumnDataGrid editable="false" key="report.index" field="deptLevel" width="10%"/>
                <%--<sd:ColumnDataGrid editable="false" key="report.deptName" field="department" width="20%"/>--%>
                <sd:ColumnDataGrid editable="false" key="report.userName" field="userName" width="15%"/>
                <sd:ColumnDataGrid editable="false" key="report.fullName" field="fullName" width="15%"/>
                <sd:ColumnDataGrid editable="false" key="report.roleId" field="roleCode" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.roleName" field="roleName" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.userStatus" field="userStatus" width="10%"/>
                <sd:ColumnDataGrid editable="false" key="report.roleStatus" field="roleStatus" width="10%"/>
                <sd:ColumnDataGrid editable="false" key="report.userRoleStatus" field="userRoleStatus" width="10%"/>
            </sd:DataGrid>
        </div>
    </form>
</sd:TitlePane>

<script>
    page.onView = function(){
        dijit.byId('gridId').vtReload("RoleUserReportAction!onViewRoleUserReport.do", "reportForm") ;
    }
    page.onExcel = function(){
        var temp = "";
                                    if (dijit.byId('reportForm.roleCodeSearch').value != ""){
                                        temp += "&roleCode=";
                                        temp += dijit.byId('reportForm.roleCodeSearch').value;
                                    }
                                    if (dijit.byId('reportForm.roleNameSearch').value != ""){
                                        temp += "&roleName=";
                                        temp += dijit.byId('reportForm.roleNameSearch').value;
                                    }
                                    if (dijit.byId('reportForm.objectCodeSearch').value != ""){
                                        temp += "&objectCode=";
                                        temp += dijit.byId('reportForm.objectCodeSearch').value;
                                    }
                                    if (dijit.byId('reportForm.objectNameSearch').value != ""){
                                        temp += "&objectName=";
                                        temp += dijit.byId('reportForm.objectNameSearch').value;
                                    }

                                    window.location = "RoleUserReportAction!onExcelRoleUserReport.do?ext=ext" + temp;
    }
</script>