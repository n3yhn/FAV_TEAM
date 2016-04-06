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
    
<sd:TitlePane key="search.searchCondition" id="categoryTitle">
    <form id="voExceptRoleForm" name="voExceptRoleForm">
        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <th width="20%"></th>
                <th width="30%"></th>
                <th width="20%"></th>
                <th width="30%"></th>
            </tr>

            <tr>
                <td align="right">
                    <sd:Label key="voExceptRoleForm.role"/>
                </td>
                <td>
                    <sd:SelectBox id="voExceptRoleForm.roleId" name="voExceptRoleForm.roleId"
                                  labelField="roleName" valueField="roleId" key=""
                                  data="roleList"/>
                </td>

                <td align="right">
                    <sd:Label key="voExceptRoleForm.group"/>
                </td>
                <td align="left">
                    <sd:SelectBox id="voExceptRoleForm.groupId" name="voExceptRoleForm.groupId"
                                  labelField="deptName" valueField="deptId" key=""
                                  data="departmentList"/>            
                </td>                
            </tr>
            
            <tr style="text-align: center">
                <td colspan="4">
                    <sd:Button id="btnSearch" key="btnSearch" onclick="page.search();">
                        <isd:Buttonmg src="share/images/icons/search.png" height="20" width="20" alt="">
                        <span style="font-size:12px"></span>
                    </sd:Button>
                </td>
            </tr>

        </table>
    </form>
</sd:TitlePane>

<sd:TitlePane key="search.searchResult"
              id="" >
    <sx:ResultMessage id="categoryResultDeleteMessage" />
    <table width="100%">
        <tr>
            <td>
                <script>
                    page.getNo = function(index) {
                        return dijit.byId("voExceptRoleGrid").currentRow + index + 1;
                    }

                    page.getIndex = function(index) {
                        return index + 1;
                    }

                    page.formatExceptRole = function (inData) {

                        var item= dijit.byId("voExceptRoleGrid").getItem(inData-1);
                        var url="";
                        if (item!=null) {
                            var exceptRoleId = item.exceptRoleId;
                            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                                        title='<sd:Property>category.edit</sd:Property>' \n\
                                        onClick='page.showEditPopup("+exceptRoleId+");' /></div>";
                        }
                        return url;
                    }
                    page.onDisable = function(idx){                                        
                        var item =dijit.byId('voExceptRoleGrid').getItem(idx);
                        if(((item.type=='LHPC'||item.type=='CQ'||item.type=='DP') && page.checkForNotEditProfile(item.code,item.name))){
                            return true;
                        }
                        else{
                            return false;
                        }
                        return false;
                    }
                    page.canView = function(data) {
                        if (data == 0) {
                            return "Không";
                        } else if (data == 1) {
                            return "Có";
                        }
                    }

                </script>
                <div id="" style="width:100%;">
                    <sd:DataGrid id="voExceptRoleGrid"
                                 getDataUrl="voExceptRole!onSearch.do?"                                 
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="10"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="category.No" get="page.getNo" width="3%"  styles="text-align:center;" />
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;" setDisabled="page.onDisable"/>
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatExceptRole" get="page.getIndex"
                                           width="5%"  headerStyles="text-align:center;"   >
                        </sd:ColumnDataGrid>
                        <sd:ColumnDataGrid key="voExceptRoleForm.role" field="role" 
                                           width="20%"  headerStyles="text-align:center;"/>
                        <sd:ColumnDataGrid key="voExceptRoleForm.group" field="groupName" 
                                           width="20%"  headerStyles="text-align:center;"/>
                        <sd:ColumnDataGrid key="voExceptRoleForm.canView" field="canView" 
                                           width="20%"  headerStyles="text-align:center;" formatter="page.canView"/>
                        <sd:ColumnDataGrid key="voExceptRoleForm.canViewChild" field="canViewChild"
                                           width="20%"  headerStyles="text-align:center;" formatter="page.canView"/>
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

<sd:Dialog  id="dlgCreateExceptRole" height="auto" width="auto"
            key="dialog.titleAddEdit" showFullscreenButton="true"
            >
    <jsp:include page="voExceptRoleCreateEdit.jsp" flush="false"></jsp:include> 
</sd:Dialog>

<script>

    var grid = dijit.byId("voExceptRoleGrid");
    var dlgCreateExceptRole = dijit.byId("dlgCreateExceptRole");
    var insertDialog=false;
    
    page.search = function() {
        grid.vtReload('voExceptRole!onSearch.do?', "voExceptRoleForm",null,null);  
    }    
    
    page.insert = function() {
        dijit.byId("voExceptRoleEditForm.exceptRoleId").setValue(null);
        dijit.byId("voExceptRoleEditForm.roleId").setValue(-1);
        dijit.byId("voExceptRoleEditForm.groupId").setValue(-1);
        dijit.byId("voExceptRoleEditForm.canView").setValue(0);
        dijit.byId("voExceptRoleEditForm.canViewChild").setValue(0);
        insertDialog = true;
        dlgCreateExceptRole.show();
    }
     
    page.showEditPopup = function(exceptRoleId) {
        sd.connector.post("voExceptRole!showEditPopup.do?exceptRoleId=" + exceptRoleId, null, null, null, page.afterShowEditPopup);
    }
    
    page.afterShowEditPopup = function(data) {
        var obj = dojo.fromJson(data);
        var customInfo = obj.customInfo;
        
        if (customInfo.exceptRoleId != null) {
            dijit.byId("voExceptRoleEditForm.exceptRoleId").setValue(escapeHtml_(customInfo.exceptRoleId));
        }

        if (customInfo.roleId != -1) {
            dijit.byId("voExceptRoleEditForm.roleId").setValue(escapeHtml_(customInfo.roleId));
        }

        if (customInfo.groupId != -1) {
            dijit.byId("voExceptRoleEditForm.groupId").setValue(escapeHtml_(customInfo.groupId));
        }

        if (customInfo.canView != -1) {
            dijit.byId("voExceptRoleEditForm.canView").setValue(escapeHtml_(customInfo.canView));
        }
        
        if (customInfo.canViewChild != -1) {
            dijit.byId("voExceptRoleEditForm.canViewChild").setValue(escapeHtml_(customInfo.canViewChild));
        }

        insertDialog = false;
        dlgCreateExceptRole.show();
    }
    
    page.deleteItem = function(){
        if (!grid.vtIsChecked()){
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
        }
        else{
            msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
         
        }
    }

    page.deleteItemExecute=function(){
        var content = grid.vtGetCheckedDataForPost("lstItemOnGrid");
        sd.connector.post("voExceptRole!onDelete.do?" +token.getTokenParamString(), null, null, content, page.returnMessageDelete);
    }

    page.returnMessageDelete = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;         
        resultMessage_show("categoryResultDeleteMessage", result[0], result[1], 5000);
        dlgCreateExceptRole.hide();
        grid.vtReload('voExceptRole!onSearch.do?', "voExceptRoleEditForm",null,null);
    }
    
    dijit.byId("categoryTitle").attr("open", false);

</script>
