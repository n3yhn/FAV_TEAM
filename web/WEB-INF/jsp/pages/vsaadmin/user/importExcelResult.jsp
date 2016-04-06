<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<sd:TitlePane key="users" id="lstRoleTitlePane">
    <form id="userRoleForm">
        <div id="roleGridDiv" style="width:100%;height:250px">
            <sd:DataGrid clientSort="true" getDataUrl="/vsaadminv3/UserAction!onInitRoleList.do" id="roleGridId" style="width: 100%; height: 100%;" container="roleGridDiv" rowSelector="20px" rowsPerPage="20">
                <sd:ColumnDataGrid editable="true"  key="usersForm.checkboxCol" field="isCheck" width="50px" type="checkbox" />
                <sd:ColumnDataGrid editable="false" key="roleUserForm.roleName" field="roleName" width="25%"/>
                <sd:ColumnDataGrid editable="false" key="roleUserForm.code" field="code" width="25%"/>
                <sd:ColumnDataGrid editable="false" key="roleUserForm.status" field="status" width="20%" formatter="page.convertRoleStatus"/>
                <sd:ColumnDataGrid editable="false" key="roleUserForm.description" field="description" width="25%"/>
            </sd:DataGrid>
        </div>
    </form>
    <div align="right" style="padding-top:20px;">
        <sd:TextBox id="listRoleOfUser.userName" key="" name="listRoleOfUser.userName" type="hidden"/>
        <sd:Button id="usersForm.btnAddRole" key="btnAddRole" onclick="page.preAddRole()"/>
        <sd:Button id="usersForm.btnRemoveRole" key="btnRemoveRole" onclick="page.onRemoveRole()"/>
        <sd:Button id="usersForm.btnEnableRole" key="btnEnableRole" onclick="page.onEnableRole()"/>
        <sd:Button id="usersForm.btnDisableRole" key="btnDisableRole" onclick="page.onDisableRole()"/>
    </div>
</sd:TitlePane>

<script>
    
</script>