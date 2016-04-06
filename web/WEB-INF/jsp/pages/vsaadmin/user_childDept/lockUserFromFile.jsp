<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<sd:TitlePane key="listExcelRow" id="lstExcelRowTitlePane2">
    <form id="importFileGridForm">
        <div id="importFileGridDiv" style="width:100%;height:250px">
            <sd:DataGrid clientSort="true" getDataUrl="" id="importUserGridId" style="width: 100%; height: 100%;" container="importFileGridDiv" rowSelector="20px" rowsPerPage="20">
                <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="20px" />
                <sd:ColumnDataGrid editable="true"  key="usersForm.checkboxCol" field="isCheck" width="50px" type="checkbox" />

                <s:if test="#request.action != 'assignModule'">
                    <s:if test="#request.action != 'revokeModule'">
                        <sd:ColumnDataGrid editable="false" key="usersForm.userName" field="userName" width="200px"/>
                    </s:if>
                </s:if>
               
                <s:if test="#request.action == 'assignModule'">

                    <sd:ColumnDataGrid editable="false" key="rolesForm.code" field="roleCode" width="300px"/>
                    <sd:ColumnDataGrid editable="false" key="objectsForm.objectName" field="objectCode" width="200px"/>
                </s:if>
                <s:if test="#request.action == 'revokeModule'">

                    <sd:ColumnDataGrid editable="false" key="rolesForm.code" field="roleCode" width="300px"/>
                    <sd:ColumnDataGrid editable="false" key="objectsForm.objectName" field="objectCode" width="200px"/>
                </s:if>

                <s:if test="#request.action == 'resetPass'">
                    <sd:ColumnDataGrid editable="false" key="usersForm.password" field="password" width="300px"/>
                </s:if>
                <s:if test="#request.action == 'assignOrRevoke'">
                    <sd:ColumnDataGrid editable="false" key="rolesForm.code" field="roleCode" width="300px"/>
                </s:if>
                <s:if test="#request.action == 'updateIP'">
                    <sd:ColumnDataGrid editable="false" key="usersForm.ip" field="ip" width="300px"/>
                </s:if>
            </sd:DataGrid>
        </div>
    </form>
    <table style="padding-top:20px">
        <tr>
            <td>
                <div >
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('importUserGridId');">Select all</span>
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('importUserGridId');">Unselect all</span>
                </div>
            </td>
            <td>
                <div align="center" >
                    <%--<sd:Button id="btnImportFile" key="" onclick="page.agreeDo()">
                        <img src="share/images/icons/play.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnDoAction</sd:Property></span>
                    </sd:Button>--%>
                    <%--<s:if test="#request.action != 'assignModule'"> --%>
                    <s:if test="#request.action not in {'assignModule','revokeModule'}">
                        <sd:Button id="btnImportFile" key="" onclick="page.agreeDo()">
                            <img src="share/images/icons/play.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnDoAction</sd:Property></span>
                        </sd:Button>
                    </s:if>
                    <s:if test="#request.action == 'assignModule'">
                        <sd:Button id="btnImportFile" key="" onclick="page.agreeDoAssignObject()">
                            <img src="share/images/icons/play.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnDoAction</sd:Property></span>
                        </sd:Button>
                    </s:if>
                    <s:elseif test="#request.action == 'revokeModule'">
                        <sd:Button id="btnImportFile" key="" onclick="page.agreeDoRevokeObject()">
                            <img src="share/images/icons/play.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnDoAction</sd:Property></span>
                        </sd:Button>
                    </s:elseif>
                </div>
            </td>
        </tr>
    </table>


</sd:TitlePane>