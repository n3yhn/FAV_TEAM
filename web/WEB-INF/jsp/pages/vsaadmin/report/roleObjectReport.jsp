<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<sd:TitlePane key="menu.report.filter" id="tltpn1">
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
        </form>
    </div>
    <div align="center">
        <sd:Button id="userReport.view" key="" onclick="page.onView()" >
            <img src="share/images/icons/3.png" height="20" width="20">
            <span style="font-size:12px"><sd:Property>global.view</sd:Property></span>
        </sd:Button>
        <sd:Button id="createUserReportForm.excel" key="" onclick="page.onExcel()" >
            <img src="share/images/icons/excel.png" height="20" width="20">
            <span style="font-size:12px"><sd:Property>global.excel</sd:Property></span>
        </sd:Button>
    </div>
</sd:TitlePane>
<br/>
<br/>
<sd:TitlePane key="menu.report.result" id="tltpn2">
    <form id="form1">
        <div id="gridDiv" style="width:100%;height:500px">
            <sd:DataGrid clientSort="true" getDataUrl="/vsaadminv3/ChildDeptReportAction!onInitRoleObject.do" id="gridId" style="width:100%; height:100%;" container="gridDiv" rowSelector="20px">
                <sd:ColumnDataGrid editable="false" key="report.roleId" field="roleId" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.roleName" field="roleName" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.appName" field="appName" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.modul" field="modul" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="objectsForm.objectCode" field="code" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.func" field="func" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.des" field="description" width="20%"/>
            </sd:DataGrid>
        </div>
    </form>
</sd:TitlePane>

<script>
                                page.searchDefault = function(evt){
                                    var dk = dojo.keys;
                                    switch(evt.keyCode){
                                        case dk.ENTER:
                                            page.onView();
                                            break;
                                    }
                                }

                                dojo.connect(dojo.byId("filterFormDiv"),"onkeypress",page.searchDefault);

                                page.onView = function(){
                                    dijit.byId('gridId').vtReload("RoleObjectReportAction!onViewRoleObject.do","reportForm") ;
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

                                    window.location = "RoleObjectReportAction!onExcelRoleObject.do?ext=ext" + temp;
                                }
</script>