<%-- 
    Document   : flowRoleAddEdit
    Created on : May 9, 2012, 11:35:48 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<jsp:include page="../util/util_js.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>


<sd:TitlePane key="dialog.titleAddEdit" id="flowRoleAddEdit">
    <form id="flowRoleFormAddEdit">
        <sd:TextBox cssStyle="display:none"
                    id="flowRoleFormAddEdit.flowRoleId"
                    key=""
                    name="flowRoleFormAddEdit.flowRoleId"
                    value="">
        </sd:TextBox>
        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <td width="40%"></td>
                <td width="60%"></td>
            </tr>
            <tr>
                <td align="right">
                    <sd:Label key="Từ vai trò: "/>
                </td>
                <td>
                    <sd:SelectBox cssStyle="width:80% !important;"
                                  id="flowRoleFormAddEdit.fromRoleId"
                                  key=""
                                  name="flowRoleFormAddEdit.fromRoleId"
                                  data="fromRole"
                                  labelField="roleName" valueField="roleId">
                    </sd:SelectBox>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <sd:Label key="Đến vai trò: "/>
                </td>
                <td>
                    <sd:SelectBox cssStyle="width:80% !important;"
                                  id="flowRoleFormAddEdit.toRoleId"
                                  key=""
                                  name="flowRoleFormAddEdit.toRoleId"
                                  data="toRole"
                                  labelField="roleName" valueField="roleId">
                    </sd:SelectBox>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <sd:Label key="Thứ tự: "/>
                </td>
                <td>
                    <sd:TextBox cssStyle="width:80% !important;"
                                  id="flowRoleFormAddEdit.orderFlow"
                                  key="" mask="digit"
                                  name="flowRoleFormAddEdit.orderFlow">
                    </sd:TextBox>
                </td>
            </tr>
            <tr style="text-align: center">
                <td colspan="2">
                    <sx:ButtonSave onclick="page.save()" />
                    <sx:ButtonClose onclick="page.close()" />
                </td>
            </tr>

        </table>
    </form>
</sd:TitlePane>

<script>
    var fromRole = dijit.byId("flowRoleFormAddEdit.fromRoleId");
    var toRole = dijit.byId("flowRoleFormAddEdit.toRoleId");
    var flowRole= dijit.byId("flowRoleFormAddEdit.flowRoleId");
    page.save = function() {

        if (fromRole.getValue().toString() == "-1") {
            msg.alert("Từ vai trò không được để trống",'<sd:Property>confirm.title</sd:Property>');
            return false;
        }

        if (toRole.getValue().toString() == "-1") {
            msg.alert("Đến vai trò không được để trống",'<sd:Property>confirm.title</sd:Property>');
            return false;
        }
        sd.connector.post("flowRole!checkFlowRole.do", null, "flowRoleFormAddEdit", null, page.afterCheckCategory);

    }

    page.afterCheckCategory = function(data) {
        var obj = dojo.fromJson(data);
        var result = null;
        result=obj.items;
        var customInfo = obj.customInfo;

        var result0 = result[0];
        if (result0 == "3") {
            showMessage(result[0], result[1]);
        } else {
            sd.connector.post("flowRole!insertFlowRole.do?" + token.getTokenParamString(), null, "flowRoleFormAddEdit", null, page.afterInsertCategory);
        } 
    }

    page.afterInsertCategory = function(data) {
        var obj = dojo.fromJson(data);
        var result=null;
        result = obj.items;
        var customInfo = obj.customInfo;
        grid.vtReload('flowRole!search.do?', "flowRoleFormAddEdit",null,null);
        showMessage(result[0], result[1]);
        if(flowRole.getValue().toString()!= "" && flowRole.getValue != null) {
            dialog.hide();
        }
        dijit.byId("flowRoleFormAddEdit.flowRoleId").setValue("");
        dijit.byId("flowRoleFormAddEdit.orderFlow").setValue("");
        dijit.byId("flowRoleFormAddEdit.fromRoleId").setValue("-1");
        dijit.byId("flowRoleFormAddEdit.toRoleId").setValue("-1");
    }

    page.close = function() {
        dialog.hide();
//        dijit.byId("flowRoleFormAddEdit.flowRoleId").setValue("");
//        dijit.byId("flowRoleFormAddEdit.orderFlow").setValue("");
//        dijit.byId("flowRoleFormAddEdit.fromRoleId").setValue("-1");
//        dijit.byId("flowRoleFormAddEdit.toRoleId").setValue("-1");
 
    }
</script>