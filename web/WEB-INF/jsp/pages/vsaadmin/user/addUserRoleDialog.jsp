<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<script type="text/javascript">
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

<sd:TitlePane key="search.searchCondition" id="URDialog.tltpn">
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
</sd:TitlePane>
<sd:TitlePane key="search.searchResult" id="lstUserRoleTitlePane">
    <tr>
        <td>
            <form id="unassignedRoleFormOnDialog" name="unassignedRoleFormOnDialog">
                <div id="unassignedRoleGridDiv" style="width:100%;">
                    <sd:DataGrid clientSort="true" 
                                 getDataUrl="" 
                                 id="unassignedRoleGridId" 
                                 style="width: 100%; height: 100%;" 
                                 container="unassignedRoleGridDiv" 
                                 rowSelector="0px"
                                 rowsPerPage="10">
                        <sd:ColumnDataGrid editable="false" key="header.index" get="page.getIndex" width="40px" styles="text-align:center;"/>
                        <sd:ColumnDataGrid key="column.checkbox" width="40px" type="checkbox" field="isCheck" editable="true" headerCheckbox="true" styles="text-align:center;"/>
                        <sd:ColumnDataGrid editable="false" key="header.roleName" field="roleName" width="25%" headerStyles="text-align:center;"/>
                        <sd:ColumnDataGrid editable="false" key="header.roleCode" field="roleCode" width="25%" headerStyles="text-align:center;"/>
                        <sd:ColumnDataGrid editable="false" key="header.roleStatus" field="status" width="20%" formatter="page.convertRoleStatus" headerStyles="text-align:center;"/>
                        <sd:ColumnDataGrid editable="false" key="header.description" field="description" width="25%" headerStyles="text-align:center;"/>
                    </sd:DataGrid>
                </div>
            </form>
            <table>
                <tr>
                    <td>
                        <div align="center" >
                            <sd:Button id="userRoleFormOnDialog.btnAgree" key="btnAddRole" onclick="page.agreeToAddRole()"/>
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</sd:TitlePane>
