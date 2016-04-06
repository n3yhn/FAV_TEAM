<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../util/util_js.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="token">
    <s:token id="tokenId"/>
</div>
<sd:TitlePane key="search.searchCondition" id="defaultRoleSearchTitle">
    <form id="defaultRoleSearchForm" name ="defaultRoleSearchForm">
        <sd:TextBox cssStyle="width:100%;display:none"
                    id="defaultRoleSearchForm.flowType"
                    key=""
                    name="defaultRoleSearchForm.flowType"
                    value="${fn:escapeXml(flowType)}">
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
                    <sd:SelectBox  cssStyle="width:100%" id="defaultRoleSearchForm.roleId" name="defaultRoleSearchForm.roleId" 
                                   labelField="roleName" valueField="roleId" key="" 
                                   data="role"/>
                </td>
                <td align="right">
                    <sd:Label key="defaultRole.type"/>
                </td>
                <td>
                    <sd:SelectBox cssStyle="width:60%" id="defaultRoleSearchForm.type" name="defaultRoleSearchForm.type"
                                  key="" value="-1">
                        <sd:Option value="-1">--Chọn--</sd:Option>
                        <sd:Option value="1">Lãnh đạo</sd:Option>
                        <sd:Option value="2">Văn thư</sd:Option>
                        <sd:Option value="3">Chuyên viên</sd:Option>
                        <sd:Option value="4">Khác</sd:Option>
                    </sd:SelectBox>
                </td>
            </tr>
            <tr style="text-align: center">
                <td colspan="4">
                    <sx:ButtonSearch onclick="page.search();" />
                </td>
            </tr>

        </table>
    </form>
</sd:TitlePane>
<sd:TitlePane key="search.searchResult"
              id="defaultRoleList" >
    <sx:ResultMessage id="categoryResultDeleteMessage" />
    <table width="100%">
        <tr>
            <td>
                <script>
                    page.getNo = function(index) {
                        return dijit.byId("defaultRoleGrid").currentRow + index + 1;
                    }

                    page.getIndex = function(index) {
                        return index + 1;
                    }

                    page.formatRole = function (inData) {

                        var item= dijit.byId("defaultRoleGrid").getItem(inData-1);
                        var url="";
                        if (item!=null) {
                            var defaultRoleId = item.defaultRoleId;
                            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                                        title='<sd:Property>category.edit</sd:Property>' \n\
                                        onClick='page.showEditPopup("+defaultRoleId+");' /></div>";
                        }
                        return url;
                    }
                    
                    page.onDisable = function(idx){                                        
                       
                    }
                   
                </script>
                <div id="" style="width:100%;">
                    <sd:DataGrid id="defaultRoleGrid"
                                 getDataUrl="voDefaultRole!onSearch.do?flowType=${fn:escapeXml(flowType)}"
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="10"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="defaultRole.No" get="page.getNo" width="3%"  styles="text-align:center;" />
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" 
                                           headerStyles="text-align:center;" type="checkbox" width="5%" 
                                           cellStyles="text-align:center;" setDisabled="page.onDisable"/>
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatRole" get="page.getIndex"
                                           width="5%"  headerStyles="text-align:center;"   >
                        </sd:ColumnDataGrid>
                        <sd:ColumnDataGrid  key="defaultRole.role" field="role"
                                            width="40%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="defaultRole.type" field="typeName"
                                            width="30%"  headerStyles="text-align:center;" />
                    </sd:DataGrid>
                </div>
            </td>
        </tr>
        <tr>
            <td>                
                <sx:ButtonAddNew onclick="page.insert();" /> 
                <sx:ButtonDelete onclick="page.deleteItem()" />
            </td>
        </tr>
    </table>
</sd:TitlePane>
<sd:Dialog  id="dlgDefaultRole" height="auto" width="700"
            key="dialog.titleAddEdit" showFullscreenButton="true"
            >
    <jsp:include page="defaultRoleAddEdit.jsp" flush="false"></jsp:include>
</sd:Dialog>
<script>
    var grid = dijit.byId("defaultRoleGrid");
    var dlgDefaultRole = dijit.byId("dlgDefaultRole");
    var insertDialog=false;

    page.search = function() {
        grid.vtReload('voDefaultRole!onSearch.do?', "defaultRoleSearchForm",null,null);
    }
    page.insert = function() {
        insertDialog=true;
        dlgDefaultRole.show();
    }
    
    page.showEditPopup = function(defaultRoleId) {
        sd.connector.post("voDefaultRole!showEditPopup.do?defaultRoleId=" + defaultRoleId, null, null, null, page.afterShowEditPopup);
    }

    page.afterShowEditPopup = function(data) {
        var obj = dojo.fromJson(data);
        var customInfo = obj.customInfo;
        
        if (customInfo.defaultRoleId != null) {
            dijit.byId("defaultRoleAddEditForm.defaultRoleId").setValue(customInfo.defaultRoleId);
        }

        if (customInfo.type != -1) {
            dijit.byId("defaultRoleAddEditForm.type").setValue(customInfo.type);
        }

        if (customInfo.roleId != -1) {
            dijit.byId("defaultRoleAddEditForm.roleId").setValue(customInfo.roleId);
        }

        insertDialog=false;
        dlgDefaultRole.show();
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
        sd.connector.post("voDefaultRole!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
    }

    page.returnMessageDelete = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("categoryResultDeleteMessage", result[0], result[1], 5000);
        dlgDefaultRole.hide();
        grid.vtReload('voDefaultRole!onSearch.do?', "defaultRoleSearchForm",null,null);
    }
</script>