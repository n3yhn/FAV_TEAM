<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<%--<sd:TitlePane key="departmentForm.DeptTitle" id="lstUserOfDeptTitlePane">--%>
<sd:TitlePane key="departmentForm.listUser" id="listUser">
            <form id="userOfDeptForm">
                <div id="userOfDeptGridDiv" style="width:100%;">
                    <sd:DataGrid  rowsPerPage="10" 
                                  clientSort="true" 
                                  getDataUrl="" 
                                  id="userDeptGridId" 
                                  style="width: 100%; height: 100%;" 
                                  container="userOfDeptGridDiv" 
                                  rowSelector="0px">
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" editable="false" key="index" get="page.getIndex" width="5%" cellStyles="text-align:center;"/>
                        <sd:ColumnDataGrid key="column.checkbox" headerCheckbox="true" type="checkbox" width="20px" styles="text-align:center;"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="usersForm.userName" field="userName" width="20%"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="usersForm.fullName" field="fullName" width="20%"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="usersForm.cellphone" field="cellphone" width="15%"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="usersForm.staffCode" field="staffCode" width="15%"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="usersForm.status" field="status" width="10%" formatter="page.convertUserStatus"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="usersForm.description" field="description" width="20%"/>
                    </sd:DataGrid>
                </div>
                <div style="display:none;">
                    <sd:TextBox id="userOfDeptForm.deptId" name="departmentForm.deptId" key="" />
                </div>
            </form>

            <table>
                <tr>
                    <td>
                        <div align="right" style="color:black;">
                          
                            <sd:Button id="departmentForm.btnRemoveUser" key="" onclick="page.onRemoveUser()">
                                <img src="share/images/icons/13.png" height="20" width="20"/>
                                <span style="font-size:12px"><sd:Property>btnRemoveUserOfDept</sd:Property></span>
                            </sd:Button>
                        </div>
                    </td>
                </tr>
            </table>
</sd:TitlePane>

<script>
    <%--DepartmentAction!onInitUserList.do?deptId=${deptId}--%>

</script>