<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<script>
    // enter key
    page.searchDefault = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                page.searchCommonRole();
                break;
        }
    }

    dojo.connect(dojo.byId("roleSearchFormDiv"),"onkeypress",page.searchDefault);
</script>

<sd:TitlePane key="usersForm.roleTitle" id="lstRoleTitlePane">
    <div id="roleSearchFormDiv">
        <form id="userRoleForm">

            <table  width="100%">

                <tr>
                    <td>
                        <sd:TextBox id="userRoleForm.roleName" name="userRoleFormOnDialog.roleName" key="roleUserForm.roleName" cssStyle="width:80%" trim="true"/>
                    </td>

                    <td>
                        <sd:TextBox id="userRoleForm.roleCode" name="userRoleFormOnDialog.code" key="roleUserForm.code" cssStyle="width:80%" trim="true"/>
                    </td>
                    <td>
                        <sd:SelectBox  id="userRoleForm.status" key="roleUserForm.status" name="userRoleFormOnDialog.status" cssStyle="width:80%">
                    <option value="ALL">-- Chọn --</option>
                    <option value="1">Hoạt động</option>
                    <option value="0">Bị khóa</option>
                </sd:SelectBox>
                </td>

                </tr>

            </table>

            <div align="center" style="padding-top:10px;">
                <sd:Button id="userRoleForm.btnSearch" key="btnSearch" onclick="page.searchCommonRole()"/>
            </div>

            <br>
            <div id="roleGridDiv" style="width:100%;height:250px">
                <sd:DataGrid clientSort="true" getDataUrl="" id="roleGridId" style="width: 100%; height: 100%;" container="roleGridDiv" rowSelector="20px" rowsPerPage="20">
                    <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="20px" />
                    <sd:ColumnDataGrid editable="true"  key="usersForm.checkboxCol" field="isCheck" width="50px" type="checkbox" />
                    <sd:ColumnDataGrid editable="false" key="roleUserForm.roleName" field="roleName" width="25%"/>
                    <sd:ColumnDataGrid editable="false" key="roleUserForm.code" field="code" width="25%"/>
                    <sd:ColumnDataGrid editable="false" key="roleUserForm.description" field="description" width="25%"/>
                    <sd:ColumnDataGrid editable="false" key="roleUserForm.status" field="isActive" width="20%" formatter="page.convertRoleStatus"/>
                </sd:DataGrid>
            </div>

            <div style="display:none;">
                <sd:TextBox id="userRoleForm.userId" name="usersForm.userId" key="" />
            </div>
        </form>
    </div>
    <table style="width:100%;">
        <tr>
            <td>
                <div >
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('roleGridId');">Select all</span>
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('roleGridId');">Unselect all</span>
                </div>
            </td>
            <td>
                <div>
                    <sd:Button id="usersForm.btnAddRole" key="" onclick="page.preAddRole()">
                        <img src="share/images/icons/a7.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnAddRole</sd:Property></span>
                    </sd:Button>

                    <sd:Button id="usersForm.btnRemoveRole" key="" onclick="page.onRemoveRole()">
                        <img src="share/images/icons/13.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnRemoveRole</sd:Property></span>
                    </sd:Button>

                    <sd:Button id="usersForm.btnEnableRole" key="" onclick="page.onLockRole()">
                        <img src="share/images/icons/lock.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnEnableRole</sd:Property></span>
                    </sd:Button>

                    <sd:Button id="usersForm.btnDisableRole" key="" onclick="page.onUnLockRole()">
                        <img src="share/images/icons/unlock.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnDisableRole</sd:Property></span>
                    </sd:Button>
                </div>
            </td>
        </tr>
    </table>



</sd:TitlePane>

<script>
    <%-- dijit.byId("roleGridId").vtReload("UserAction!onInitRoleList.do?userId=${userId}",null);--%>
</script>
