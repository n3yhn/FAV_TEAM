<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="token">
    <s:token id="tokenId"/>
</div>
<sd:TitlePane key="dialog.titleAddEdit" id="defaultRoleAddEditTitle">
    <sx:ResultMessage id="categoryResultMessage" />
    <form id="defaultRoleAddEditForm" name ="defaultRoleAddEditForm">
        <sd:TextBox cssStyle="width:100%;display:none"
                    id="defaultRoleAddEditForm.flowType"
                    key=""
                    name="defaultRoleAddEditForm.flowType"
                    value="${fn:escapeXml(flowType)}">
        </sd:TextBox>
        <sd:TextBox cssStyle="width:100%;display:none"
                    id="defaultRoleAddEditForm.defaultRoleId"
                    key=""
                    name="defaultRoleAddEditForm.defaultRoleId">
        </sd:TextBox>
        <sd:TextBox cssStyle="width:100%;display:none"
                    id="defaultRoleAddEditForm.role"
                    key=""
                    name="defaultRoleAddEditForm.role">
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
                    <sd:Label key="defaultRole.role"/>
                </td>
                <td>
                    <sd:SelectBox  cssStyle="width:120%" id="defaultRoleAddEditForm.roleId" name="defaultRoleAddEditForm.roleId" 
                                   labelField="roleName" valueField="roleId" key="" 
                                   data="role"/>

                </td>
                <td align="right">
                    <sd:Label key="defaultRole.type"/>
                </td>
                <td>
                    <sd:SelectBox cssStyle="width:80%" id="defaultRoleAddEditForm.type" name="defaultRoleAddEditForm.type"
                                  key="" value="-1">
                        <sd:Option value="-1">--Chọn--</sd:Option>
                        <sd:Option value="1">Lãnh đạo</sd:Option>
                        <sd:Option value="2">Văn thư</sd:Option>
                        <sd:Option value="3">Chuyên viên</sd:Option>
                        <sd:Option value="4">Khác</sd:Option>
                    </sd:SelectBox>
                </td>
            </tr>
            <tr></tr>
            <tr></tr>
            <tr style="text-align: center">
                <td colspan="4">
                    <sx:ButtonSave onclick="page.save()" />
                    <sx:ButtonClose onclick="page.close()" />
                </td>
            </tr>
        </table>
    </form>
</sd:TitlePane>
<script>
    var type = dijit.byId("defaultRoleAddEditForm.type");
    var roleId = dijit.byId("defaultRoleAddEditForm.roleId");
    
    page.save = function() {
        var role = document.getElementById("defaultRoleAddEditForm.roleId").value;
        dijit.byId("defaultRoleAddEditForm.role").setValue(role);
        
        if (type.getValue() == -1 ) {
            type.focus();
            msg.alert("Hãy chọn Loại vai trò",'<sd:Property>confirm.title</sd:Property>');
            return false;
        }

        if (roleId.getValue() == -1 ) {
            roleId.focus();
            msg.alert("Hãy chọn Tên vai trò",'<sd:Property>confirm.title</sd:Property>');
            return false;
        }
        
        sd.connector.post("voDefaultRole!checkDefaultRole.do", null, "defaultRoleAddEditForm", null, page.afterCheckDefaultRole);
    }

    page.afterCheckDefaultRole = function(data) {
        var obj = dojo.fromJson(data);
        var result = null;
        result= obj.items;

        var result0 = result[0];
        if (result0 == "3") {
            msg.alert(result[1]);
        } else {
            sd.connector.post("voDefaultRole!onInsert.do?" + token.getTokenParamString(), null, "defaultRoleAddEditForm", null, page.afterInsert);
        } 
    }
    
    page.afterInsert = function(data) {
        page.reset();
        var obj = dojo.fromJson(data);
        var result = obj.items;
        grid.vtReload('voDefaultRole!onSearch.do?', "defaultRoleSearchForm",null,null);
        
        if (insertDialog) {
            resultMessage_show("categoryResultMessage", result[0], result[1], 5000);
            document.getElementById("defaultRoleAddEditForm.defaultRoleId").value="";
            dijit.byId("defaultRoleAddEditForm.type").setSelectedIndex(0);
            dijit.byId("defaultRoleAddEditForm.roleId").setSelectedIndex(0);
        } else {
            page.close();
            resultMessage_show("categoryResultDeleteMessage", result[0], result[1], 5000);
        }
        
    }
    
    page.close = function() {
        dlgDefaultRole.hide();
        document.getElementById("defaultRoleAddEditForm.defaultRoleId").value="";
        dijit.byId("defaultRoleAddEditForm.type").setSelectedIndex(0);
        dijit.byId("defaultRoleAddEditForm.roleId").setSelectedIndex(0);
        
    }
    
    page.reset = function(){
        document.getElementById("defaultRoleAddEditForm.defaultRoleId").value="";
        dijit.byId("defaultRoleAddEditForm.type").setSelectedIndex(0);
        dijit.byId("defaultRoleAddEditForm.roleId").setSelectedIndex(0);
    }
</script>