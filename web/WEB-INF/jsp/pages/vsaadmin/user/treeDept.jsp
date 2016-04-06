<%-- 
    Document   : tree
    Created on : Apr 6, 2011, 8:27:36 AM
    Author     : gpdn_trungnq7
--%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<table class="treeContainer">
    <tr>
        <td class="padding-10">
            <sd:AjaxTree id="deptTreeId" getChildrenUrl="departmentAction!getChildrenDataByNode.do" getTopLevelUrl="departmentAction!getData.do" rootLabel="usersForm.department" onClick="page.onNodeClick"/>
        </td>
    </tr>
</table>

<script type="text/javascript">
    afterUpdateRoleUserDept = function(data){
        var obj = JSON.parse(data);
        var lst=obj.items;
//        alert(lst[1]);
        var userId = sd._("usersForm.userId").getValue();
        dijit.byId("roleGridId").vtReload("UserAction!searchRoleOfUsers.do?userId="+userId, "usersForm", null, null);
        
    }

    page.onNodeClick = function(item, node, event){
        var deptId = null;
        var deptName = null;
        if(item.id == undefined){
        }else{
            deptId = item.id;
            deptName = item.name;
        }
        var grid =dijit.byId("roleGridId");
        var gridItem = grid.getItem(selectedItem);
        var roleId = gridItem.roleId;
        var roleName = gridItem.roleName;
        var userId = gridItem.userId;
        var userName = gridItem.userName;
        var roleUserDeptId = gridItem.roleUserDeptId;
        
        
        if(isInsertNew){
            gridItem.deptId = deptId;
            gridItem.deptName = deptName;
            grid.store.save(gridItem);
            grid.renderNoReload();
        } else {
            roleUserDeptId = null;
            var item={
                roleUserDeptId  :null,
                deptId          :deptId,
                deptName        :deptName,
                roleId          :roleId,
                roleName        :roleName,
                userId          :userId,
                userName        : userName,               
                isActive        :1               
            };

            grid.store.newItem(item);        
            grid.store.save();
            grid.renderNoReload();
        }

        var dlg = dijit.byId("deptOfUserRoleDlg");
        dlg.hide();
        
        if(roleUserDeptId == null || roleUserDeptId.toString() == ""){
            roleUserDeptId = 0;
        }
        
        sd.connector.post("UserAction!updateUserRoleDept.do?"+token.getTokenParamString()+"&userId="+userId+"&deptId="+deptId+"&roleId="+roleId+"&roleUserDeptId="+roleUserDeptId,null,null,null,afterUpdateRoleUserDept);
    }
</script>