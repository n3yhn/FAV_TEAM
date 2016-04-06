<%-- 
    Document   : voOutsiteOfficeSearch
    Created on : Jun 15, 2012, 6:35:51 PM
    Author     : HanPT1
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
<div id ="searchDiv" style="display:none">

<sd:TitlePane key="search.searchCondition" id="positionTitlePaneId">    
    <form id="outsiteOfficeSearchForm" name="outsiteOfficeSearchForm">
        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <td width="20%"></td>
                <td width="30%"></td>
                <td width="20%"></td>
                <td width="30%"></td>
            </tr>

            <tr>                
                <td align="right">
                    <sd:Label key="outsiteOfficeAddEditForm.officeName" cssStyle="100%"/>
                </td>
                <td>
                    <sd:TextBox id="outsiteOfficeSearchForm.officeName" name="outsiteOfficeSearchForm.officeName"
                                key="" maxlength="250" cssStyle="width:100%">
                    </sd:TextBox>
                </td>            

                <td align="right">
                    <sd:Label key="outsiteOfficeAddEditForm.code" cssStyle="100%"/>
                </td>
                <td>
                    <sd:TextBox id="outsiteOfficeSearchForm.code" name="outsiteOfficeSearchForm.code"
                                key="" maxlength="20" cssStyle="width:100%">
                    </sd:TextBox>
                </td>                     
            </tr>
            <tr>
                <td align="right">
                    <sd:Label key="outsiteOfficeAddEditForm.officeLevel" cssStyle="100%"/>
                </td>
                <td>
                    <sd:SelectBox id="outsiteOfficeSearchForm.officeLevelId" 
                                  key="" 
                                  name="outsiteOfficeSearchForm.officeLevelId" 
                                  data="officeLevelList" 
                                  labelField="officeLevelName" valueField="officeLevelId"
                                  cssStyle="width:100%"
                                  />             
                </td>
            </tr>
            <tr style="text-align: center">
                <td colspan="4">
                    <sx:ButtonSearch onclick = "page.search();"></sx:ButtonSearch>
                </td>
            </tr>

        </table>
    </form>
</sd:TitlePane>
</div>

<sd:TitlePane key="Danh mục đơn vị ngoài"
              id="" >
    <sx:ResultMessage id="positionDeleteMessage"/>
    <table width="100%">
        <tr>
            <td>
                <script>
                    page.getNo = function(index) {
                        return dijit.byId("outsiteOfficeGrid").currentRow + index + 1;
                    }

                    page.getIndex = function(index) {
                        return index + 1;
                    }

                    page.formatOffice = function (inData) {

                        var item= dijit.byId("outsiteOfficeGrid").getItem(inData-1);
                        var url="";
                        if (item!=null) {
                            var officeId = item.officeId;
                            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                                        title='<sd:Property>category.edit</sd:Property>' \n\
                                        onClick='page.showEditPopup("+officeId+");' /></div>";
                        }
                        return url;
                    }
                    page.onDisable = function(idx){                                        
                        var item =dijit.byId('outsiteOfficeGrid').getItem(idx);
                        if(((item.type=='LHPC'||item.type=='CQ'||item.type=='DP') && page.checkForNotEditProfile(item.code,item.name))){
                            return true;
                        }
                        else{
                            return false;
                        }
                        return false;
                    }
                   
                </script>
                <div id="" style="width:100%;">
                    <sd:DataGrid id="outsiteOfficeGrid"
                                 getDataUrl="voOutsiteOffice!onSearch.do?"                                 
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="category.No" get="page.getNo" width="30px"  styles="text-align:center;" />
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="30px" cellStyles="text-align:center;" setDisabled="page.onDisable"/>
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatOffice" get="page.getIndex"
                                           width="30px"  headerStyles="text-align:center;"   >
                        </sd:ColumnDataGrid>
                        <sd:ColumnDataGrid key="outsiteOfficeAddEditForm.code" field="code" 
                                           width="40%"  headerStyles="text-align:center;"/>
                        <sd:ColumnDataGrid key="outsiteOfficeAddEditForm.officeName" field="officeName" 
                                           width="60%"  headerStyles="text-align:center;"/>        
                    </sd:DataGrid>
                </div>
            </td>
        </tr>   
        <tr>
            <td>
                <sx:ButtonAddCategory onclick="page.insert();"/>
                <sx:ButtonDelete onclick="page.deleteItem()" />
                <sx:ButtonSearch id="btnShowSearchPanel" onclick="page.showSearchPanel();" />
            </td>
        </tr>       
    </table>
</sd:TitlePane>

<sd:Dialog  id="dlgAddEditOutsiteOffice" height="auto" width="800px"
            key="dialog.OfficeAddEdit" showFullscreenButton="true"
            >
    <jsp:include page="outsiteOfficeAddEdit.jsp" flush="false"></jsp:include> 
</sd:Dialog>

<script>

    var grid = dijit.byId("outsiteOfficeGrid");
    var dlgAddEditOutsiteOffice = dijit.byId("dlgAddEditOutsiteOffice");
    var insertDialog=false;
    
    page.search = function() {
        grid.vtReload('voOutsiteOffice!onSearch.do?', "outsiteOfficeSearchForm",null,null);  
    }    
    
    page.insert = function() {
        dijit.byId("outsiteOfficeAddEditForm.officeId").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.code").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.officeName").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.officeLevelId").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.email").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.address").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.receiveDocAddress").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.leader").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.officeComment").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.telephone").setValue("");
        insertDialog=true;
        dlgAddEditOutsiteOffice.show();
    }
         
    page.showEditPopup = function(officeId) {
        sd.connector.post("voOutsiteOffice!showEditPopup.do?officeId="+officeId, null, null, null, page.afterShowEditPopup);
    }
        
    page.afterShowEditPopup = function(data) {
        var obj = dojo.fromJson(data);
        var customInfo = obj.customInfo;
            
        if (customInfo.officeId != null && customInfo.officeId != -1) {           
            dijit.byId("outsiteOfficeAddEditForm.officeId").setValue(escapeHtml_(customInfo.officeId));
            
        }
            
        if (customInfo.code != null) {            
            dijit.byId("outsiteOfficeAddEditForm.code").setValue(escapeHtml_(customInfo.code));
        }
    
        if (customInfo.officeName != null) {           
            dijit.byId("outsiteOfficeAddEditForm.officeName").setValue(escapeHtml_(customInfo.officeName));
        }
        
        if (customInfo.officeLevelId != null && customInfo.officeLevelId != -1) {            
            dijit.byId("outsiteOfficeAddEditForm.officeLevelId").setValue(escapeHtml_(customInfo.officeLevelId));           
        }
        
        if (customInfo.email != null) {            
            dijit.byId("outsiteOfficeAddEditForm.email").setValue(escapeHtml_(customInfo.email));
        }
        
        if (customInfo.address != null) {            
            dijit.byId("outsiteOfficeAddEditForm.address").setValue(escapeHtml_(customInfo.address));
        }
        
        if (customInfo.receiveDocAddress != null) {            
            dijit.byId("outsiteOfficeAddEditForm.receiveDocAddress").setValue(escapeHtml_(customInfo.receiveDocAddress));
        }
    
        if (customInfo.leader != null) {            
            dijit.byId("outsiteOfficeAddEditForm.leader").setValue(escapeHtml_(customInfo.leader));
        }
      
        if (customInfo.officeComment != null) {           
            dijit.byId("outsiteOfficeAddEditForm.officeComment").setValue(escapeHtml_(customInfo.officeComment));
        }
        
        if (customInfo.officeComment != null) {            
            dijit.byId("outsiteOfficeAddEditForm.telephone").setValue(escapeHtml_(customInfo.telephone));
        }
   
        insertDialog=false;
        dlgAddEditOutsiteOffice.show();
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
        sd.connector.post("voOutsiteOffice!onDelete.do?" +token.getTokenParamString(), null, null, content, page.returnMessageDelete);
    }

    page.returnMessageDelete = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("positionDeleteMessage", result[0], result[1], 2000);      
        grid.vtReload('voOutsiteOffice!onSearch.do?', "outsiteOfficeSearchForm",null,null);
    }
    
    page.showSearchPanel = function(){
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style","display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    }    

</script>
