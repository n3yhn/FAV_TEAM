<%-- 
    Document   : flowRoleForm
    Created on : May 9, 2012, 10:43:31 AM
    Author     : user
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<jsp:include page="../util/util_js.jsp"/>
<jsp:include page="../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<div id="token">
    <s:token id="tokenId"/>
</div> 
<sd:TitlePane key="search.searchCondition" id="flowRole">
    <form id="flowRoleForm">
        <sd:TextBox cssStyle="display:none"
                    id="flowRoleForm.flowRoleId"
                    key=""
                    name="flowRoleForm.flowRoleId"
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
                                  id="flowRoleForm.fromRoleId"
                                  key=""
                                  name="flowRoleForm.fromRoleId"
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
                                  id="flowRoleForm.toRoleId"
                                  key=""
                                  name="flowRoleForm.toRoleId"
                                  data="toRole"
                                  labelField="roleName" valueField="roleId">
                    </sd:SelectBox>
                </td>
            </tr>
            <tr style="text-align: center">
                <td colspan="2">
                    <sx:ButtonSearch onclick="page.search();" />
                    <%--
                    <sd:Button key="" onclick="page.reset();" > 
                        <img src="${contextPath}/share/images/icons/reset.png" height="14" width="14">
                        <span style="font-size:12px">Hủy</span>
                    </sd:Button>
                    --%>
                </td>
            </tr>

        </table>
    </form>
</sd:TitlePane>
<script>
    page.getNo = function(index) {
        return dijit.byId("flowRoleGrid").currentRow + index + 1;
    }

    page.getIndex = function(index) {
        return index + 1;
    }

    page.formatflowRole = function (inData) {

        var item= dijit.byId("flowRoleGrid").getItem(inData-1);
        var url="";
        if (item!=null) {
            var flowRoleId = item.flowRoleId;
            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                                        title='<sd:Property>category.edit</sd:Property>' \n\
                                        onClick='page.showEditPopup("+flowRoleId+");' /></div>";
        }
        return url;
    }
</script>
<sd:TitlePane key="search.searchResult"
              id="flowRoleList" >
    <table width="100%">
        <tr>
            <td>
                <div id="" style="width:100%;">
                    <sd:DataGrid id="flowRoleGrid"
                                 getDataUrl="flowRole!search.do"
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="10"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="STT" get="page.getNo" width="3%"  styles="text-align:center;" />
                        <sd:ColumnDataGrid editable="true" key=" " headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="2%" cellStyles="text-align:center;"/>
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatflowRole" get="page.getIndex"
                                           width="5%"  headerStyles="text-align:center;"   >
                        </sd:ColumnDataGrid>
                        <sd:ColumnDataGrid  key="Từ vai trò" field="fromRole"
                                            width="35%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Đến vai trò" field="toRole"
                                            width="35%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Thứ tự" field="orderFlow"
                                            width="20%"  headerStyles="text-align:center;" />
                    </sd:DataGrid>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <sx:ButtonAddCategory onclick="page.insert();"/>
                <sx:ButtonDelete onclick="page.deleteItem()" />
            </td>
        </tr>
    </table>
</sd:TitlePane>

<sd:Dialog  id="dlgflowRole" height="auto" width="800"
            key="dialog.titleAddEdit" showFullscreenButton="true">
    <jsp:include page="flowRoleAddEdit.jsp" flush="false"></jsp:include>
</sd:Dialog>

<script>
    
    var grid = dijit.byId("flowRoleGrid");
    var dialog = dijit.byId("dlgflowRole");

    page.search = function() {
        grid.vtReload('flowRole!search.do?', "flowRoleForm",null,null);
    }

    page.reset = function() {
        dijit.byId("flowRoleFormAddEdit.fromRoleId").setValue("-1");
        dijit.byId("flowRoleFormAddEdit.toRoleId").setValue("-1");
        grid.vtReload('flowRole!search.do?', "flowRoleForm",null,null);
    }

    page.insert = function() {
        dijit.byId("flowRoleFormAddEdit.flowRoleId").setValue("");
        dijit.byId("flowRoleFormAddEdit.orderFlow").setValue("");
        dijit.byId("flowRoleFormAddEdit.fromRoleId").setValue("-1");
        dijit.byId("flowRoleFormAddEdit.toRoleId").setValue("-1");
        dijit.byId("flowRoleForm.flowRoleId").setValue("");
        dialog.show();
    }

    page.showEditPopup = function(categoryId) {
        sd.connector.post("flowRole!showEditPopup.do?flowRoleId=" + categoryId, null, null, null, page.afterShowEditPopup);
    }

    page.afterShowEditPopup = function(data) {
        var obj = dojo.fromJson(data);
        var customInfo = obj.customInfo;

        if (customInfo.flowRoleId != null) {
            dijit.byId("flowRoleFormAddEdit.flowRoleId").setValue(escapeHtml_(customInfo.flowRoleId));
        }

        if (customInfo.fromRoleId != null) {
            dijit.byId("flowRoleFormAddEdit.fromRoleId").setValue(escapeHtml_(customInfo.fromRoleId));
        }

        if (customInfo.toRoleId != null) {
            dijit.byId("flowRoleFormAddEdit.toRoleId").setValue(escapeHtml_(customInfo.toRoleId));
        }
        if(customInfo.orderFlow!=null) {
            dijit.byId("flowRoleFormAddEdit.orderFlow").setValue(escapeHtml_(customInfo.orderFlow));
        }

        dialog.show();
    }

    page.deleteItem = function(){
        if (!grid.vtIsChecked()){
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
        }
        else{
            msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title</sd:Property>', page.deleteItemExecute);
        }
    }

    page.deleteItemExecute=function(){
        var content = grid.vtGetCheckedDataForPost("lstItemOnGrid");
        alert(content);
        sd.connector.post("flowRole!gridDeleteData.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
    }

    page.returnMessageDelete = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        showMessage(result[0], result[1]);
        grid.vtReload('flowRole!search.do?', "flowRoleForm",null,null);
    }

</script>
