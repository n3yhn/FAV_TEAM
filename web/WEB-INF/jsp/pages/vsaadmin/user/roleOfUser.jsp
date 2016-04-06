<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<script>
    // enter key
    var selectedItem = null;
    var isInsertNew = null;
    page.searchDefault = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                page.searchCommonRole();
                break;
        }
    }
    
    page.selectDept = function(row)    {
        var url = "";
        var item = dijit.byId("roleGridId").getItem(row);
        if(item != null){
            url = "<a href='#'><img onclick='page.selectDeptOf("+row+");' src='share/images/icons/listUser.png' title='Chọn đơn vị' width=16 height=16/></a>"
            +"&nbsp;&nbsp;&nbsp;<a href='#'><img onclick='page.selectNewDeptOf("+row+");' src='share/images/icons/16.png' title='Bổ sung đơn vị cho vai trò' width=16 height=16/></a>";
        }
        return url;
    }
    
    page.selectDeptOf = function(rowId){
        selectedItem = rowId;
        isInsertNew = true;
        var dlg = dijit.byId("deptOfUserRoleDlg");
        dlg.show();
    }
    
    page.selectNewDeptOf = function(rowId){
        selectedItem = rowId;
        isInsertNew = false;
        var dlg = dijit.byId("deptOfUserRoleDlg");
        dlg.show();
    }
    
    afterUpdateRoleUserDept = function(data){
        
    }
    
    page.commitRoleUserDept = function(){
        var content = dijit.byId("roleGridId").vtGetModifiedDataForPost("lstProcess");
-        sd.connector.post("UserAction!updateUserRoleDept.do?"+token.getTokenParamString(),null,null,content,afterUpdateRoleUserDept);

    }

    dojo.connect(dojo.byId("roleSearchFormDiv"),"onkeypress",page.searchDefault);
</script>

<sd:TitlePane key="usersForm.roleTitle" id="lstRoleTitlePane">
    <div id="roleSearchFormDiv">
        <form id="userRoleForm">
        <div id="tblSuccessMessage2" style="display: none; margin-bottom: 3px; padding: 2px; border-width: 1px;border-color: gray; border-style: solid;background-color: #90EE90">
            <label style="color: green;font-size: 15px;font-family: Time News Roman;border-color: gray; font-weight: bold" id="lblSuccessMessage2">
                <sd:Property>alert.updatesucess</sd:Property>
            </label>
        </div>
        <div id="tblErrorMessage2" style="display: none;margin-bottom: 3px; padding: 2px; border-bottom-width: 2px; border-left-style: solid;background-color: #FDE5DD">
            <label style="color: red;font-size: 15px;font-family: Time News Roman; font-weight: bold" id="lblErrorMessage2">
                <sd:Property>alert.updaterror</sd:Property>
            </label>
        </div>
            <div id="roleGridDiv" style="width:100%;">
                <sd:DataGrid clientSort="true" 
                             getDataUrl="" 
                             id="roleGridId" 
                             style="width: 100%; height: 100%;" 
                             container="roleGridDiv" 
                             rowsPerPage="10">
                    <sd:ColumnDataGrid editable="false" key="header.index" get="page.getIndex" width="40px" styles="text-align:center;" />
                    <sd:ColumnDataGrid editable="true" headerCheckbox="true" key="column.checkbox" field="isCheck" width="40px" type="checkbox" styles="text-align:center;" />
                    <sd:ColumnDataGrid editable="false" key="header.roleName" field="roleName" width="25%" headerStyles="text-align:center;"/>
                    <sd:ColumnDataGrid editable="false" key="usersForm.deptName" field="deptName" width="25%" headerStyles="text-align:center;"/>
                    <sd:ColumnDataGrid editable="false" key="Chọn đơn vị" width="25%" headerStyles="text-align:center;" get="page.getRow" formatter="page.selectDept" styles="text-align:center;"/>
                    <sd:ColumnDataGrid editable="false" key="header.roleStatus" field="isActive" width="20%" formatter="page.convertRoleStatus" headerStyles="text-align:center;"/>
                </sd:DataGrid>
            </div>
            <div style="display:none;">
                <sd:TextBox id="usersForm.userId" name="usersForm.userId" key="" />
                <sd:TextBox id="usersForm2.userName" name="usersForm.userName" key="" />
            </div>
        </form>
    </div>
    <table style="width:100%">
        <tr>
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

                    <sd:Button id="usersForm.btnDisableRole" key="" onclick="page.onLockRole()">
                        <img src="share/images/icons/lock.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnDisableRole</sd:Property></span>
                    </sd:Button>

                    <sd:Button id="usersForm.btnEnableRole" key="" onclick="page.onUnLockRole()">
                        <img src="share/images/icons/unlock.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnEnableRole</sd:Property></span>
                    </sd:Button>
                </div>
            </td>
        </tr>
    </table>
</sd:TitlePane>