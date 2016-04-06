<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<jsp:include page="../util/util_js.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sd:TitlePane key="dialog.titleAddEdit" id="exceptRoleCreateTitle">
    <sx:ResultMessage id="categoryResultMessage" />
    <form id="voExceptRoleEditForm" name="voExceptRoleEditForm">

        <sd:TextBox cssStyle="width:100%;display:none"
                    id="voExceptRoleEditForm.exceptRoleId"
                    key=""
                    name="voExceptRoleEditForm.exceptRoleId"
                    >
        </sd:TextBox> 
        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <td width="20%"></td>
                <td width="30%"></td>
                <td width="20%"></td>
                <td width="30%"></td>
            </tr>
            <tr>
                <td align="right">
                    <sd:Label key="voExceptRoleForm.role" cssStyle="100%"/>
                </td>
                <td>
                    <sd:SelectBox id="voExceptRoleEditForm.roleId" name="voExceptRoleEditForm.roleId"
                                  labelField="roleName" valueField="roleId" key="" 
                                  data="roleList">
                    </sd:SelectBox>
                </td>            

                <td align="right">
                    <sd:Label key="voExceptRoleForm.group" cssStyle="100%"/>
                </td>
                <td>
                    <sd:SelectBox id="voExceptRoleEditForm.groupId" name="voExceptRoleEditForm.groupId"
                                  labelField="deptName" valueField="deptId" key=""
                                  data="departmentList"/>            
                </td>     
            </tr>

            <tr>
                <td align="right">
                    <sd:Label key="voExceptRoleForm.canView"/>
                </td>
                <td>
                    <sd:CheckBox id="voExceptRoleEditForm.canView" name="voExceptRoleEditForm.canView" 
                                 checked="false" key="" value="0"
                                 onclick="page.setCheckboxValue();"></sd:CheckBox>                            
                </td>

                <td align="right">
                    <sd:Label key="voExceptRoleForm.canViewChild"/>
                </td>
                <td>
                    <sd:CheckBox id="voExceptRoleEditForm.canViewChild" name="voExceptRoleEditForm.canViewChild" 
                                 checked="false" key="" value="0"
                                 onclick="page.setCheckboxValue();"></sd:CheckBox>                                  
                </td>                
            </tr>            

            <tr style="text-align: center">
                <td colspan="4">
                    <sd:Button id="btnSave" key="btnSave" onclick="page.save();">
                        <img src="share/images/icons/save.png" height="20" width="20" alt="">
                        <span style="font-size:12px"></span>
                    </sd:Button>
                    <sd:Button id="btnClose" key="btn.close" onclick="page.close();">
                        <img src="share/images/icons/13.png" height="20" width="20" alt="">
                        <span style="font-size:12px"></span>
                    </sd:Button>
                </td>
            </tr>

        </table>
    </form>
</sd:TitlePane>

<script>

    var checkView = dijit.byId("voExceptRoleEditForm.canView");
    var checkViewChild = dijit.byId("voExceptRoleEditForm.canViewChild");
    page.setCheckboxValue = function() {
        if (checkView.checked) {
            document.getElementById("voExceptRoleEditForm.canView").value = "1";
        } else {
            document.getElementById("voExceptRoleEditForm.canView").value = "0";
        }
        
        if (checkViewChild.checked) {
            document.getElementById("voExceptRoleEditForm.canViewChild").value = "1";
        } else {
            document.getElementById("voExceptRoleEditForm.canViewChild").value = "0";
        }
    }

    page.save = function() {
        var roleId = dijit.byId("voExceptRoleEditForm.roleId");
        var groupId = dijit.byId("voExceptRoleEditForm.groupId");  

        if (roleId.getValue() == -1 ) {
            roleId.focus();
            msg.alert("Hãy chọn Tên vai trò",'<sd:Property>confirm.title</sd:Property>');
            return false;
        }
        
        if (groupId.getValue() == -1 ) {
            groupId.focus();
            msg.alert("Hãy chọn Đơn vị",'<sd:Property>confirm.title</sd:Property>');
            return false;
        }
        sd.connector.post("voExceptRole!onInsert.do?"+ token.getTokenParamString(), null, "voExceptRoleEditForm", null, page.afterInsert);   
    }    
     
    page.afterInsert = function(data) {
        
        var obj = dojo.fromJson(data);
        var result = obj.items;
        grid.vtReload('voExceptRole!onSearch.do?', null,null,null); 
       
        if (insertDialog) {
            resultMessage_show("categoryResultMessage", result[0], result[1], 5000);
            dijit.byId("voExceptRoleEditForm.roleId").setValue(-1);
            dijit.byId("voExceptRoleEditForm.groupId").setValue(-1);
            dijit.byId("voExceptRoleEditForm.canView").setValue(0);
            dijit.byId("voExceptRoleEditForm.canViewChild").setValue(0);
        } else {
            page.close();
            resultMessage_show("categoryResultDeleteMessage", result[0], result[1], 5000);
        }
    }     
     
    page.close = function() {
        dlgCreateExceptRole.hide();      
        dijit.byId("voExceptRoleEditForm.roleId").setValue(-1);
        dijit.byId("voExceptRoleEditForm.groupId").setValue(-1);
        dijit.byId("voExceptRoleEditForm.canView").setValue(0);
        dijit.byId("voExceptRoleEditForm.canViewChild").setValue(0);                 
    }  
   
</script>
