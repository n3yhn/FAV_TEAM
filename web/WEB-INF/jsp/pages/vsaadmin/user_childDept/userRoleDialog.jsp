<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<script>
    dijit.byId("userRoleFormOnDialog.roleName").focus();

    // enter key
    page.searchDefault = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                page.searchUnassignedRole();
                break;
        }
    }

    dojo.connect(dojo.byId("searchUnassignedRoleDiv"),"onkeypress",page.searchDefault);
</script>

<sd:FieldSet key="userRoleDialog.title" id="URDialog.tltpn" theme="blue">
    <tr>
        <td>
            <div id="searchUnassignedRoleDiv">
                <form id="userRoleFormOnDialog" name="userRoleFormOnDialog">
                    <table  width="100%">

                        <tr>
                            <td>
                                <sd:TextBox id="userRoleFormOnDialog.roleName" name="userRoleFormOnDialog.roleName" key="roleUserForm.roleName" cssStyle="width:80%" trim="true"/>
                            </td>

                            <td>
                                <sd:TextBox id="userRoleFormOnDialog.roleCode" name="userRoleFormOnDialog.code" key="roleUserForm.code" cssStyle="width:80%" trim="true"/>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                <sd:SelectBox id="userRoleFormOnDialog.status" key="roleUserForm.status" name="userRoleFormOnDialog.status" cssStyle="width:80%">
                            <option value="ALL">-- Chọn --</option>
                            <option value="1">Hoạt động</option>
                            <option value="0">Bị khóa</option>
                        </sd:SelectBox>
                        </td>

                        <td style="display:none;">
                            <sd:TextBox id="userRoleFormOnDialog.userId" name="userRoleFormOnDialog.userId" key="" cssStyle="width:80%"/>
                        </td>
                        </tr>
                    </table>

                </form>
            </div>
            <div align="center" style="padding-top:10px;">
                <sd:Button id="userRoleFormOnDialog.btnSearch" key="btnSearch" onclick="page.searchUnassignedRole()"/>
            </div>

        </td>
    </tr>
</sd:FieldSet>

<br/>
<sd:FieldSet key="userRoleDialog.roleTitle" id="lstUserRoleTitlePane" theme="blue">
    <tr>
        <td>

            <form id="unassignedRoleFormOnDialog">
                <div id="roleGridDiv" style="width:100%;height:200px">
                    <sd:DataGrid clientSort="true" 
                                 getDataUrl="" 
                                 id="roleGridId" 
                                 style="width: 100%; height: 100%;" 
                                 container="roleGridDiv" 
                                 rowSelector="0px"
                                 rowsPerPage="10">
                        <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="20px" styles="text-align:center;"/>
                        <sd:ColumnDataGrid key="userRoleFormOnDialog.checkboxCol" width="35px" type="checkbox" field="isCheck" editable="true"/>
                        <sd:ColumnDataGrid editable="false" key="roleUserForm.roleName" field="roleName" width="25%"/>
                        <sd:ColumnDataGrid editable="false" key="roleUserForm.code" field="code" width="25%"/>
                        <sd:ColumnDataGrid editable="false" key="roleUserForm.status" field="status" width="20%" formatter="page.convertRoleStatus"/>
                        <sd:ColumnDataGrid editable="false" key="roleUserForm.description" field="description" width="25%"/>
                    </sd:DataGrid>
                </div>
            </form>
            
            <table style="padding-top:20px">
                <tr>
                    <td>
                        <div >
                            <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('unassignedRoleGridId');">Select all</span>
                            <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('unassignedRoleGridId');">Unselect all</span>
                        </div>
                    </td>
                    <td>
                        <div align="center" >
                            <sd:Button id="userRoleFormOnDialog.btnAgree" key="btnAgree" onclick="page.agreeToAddRole()"/>
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</sd:FieldSet>
