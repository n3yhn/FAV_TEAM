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
    <form id="positionSearchForm" name="positionSearchForm">
        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <td width="20%"></td>
                <td width="30%"></td>
                <td width="20%"></td>
                <td width="30%"></td>
            </tr>

            <tr>                
                <td align="right">
                    <sd:Label key="positionAddEditForm.posCode" cssStyle="100%"/>
                </td>
                <td>
                    <sd:TextBox id="positionSearchForm.posCode" name="positionSearchForm.posCode"
                                key="" maxlength="20" cssStyle="width:100%" trim="true">
                    </sd:TextBox>
                </td>            

                <td align="right">
                    <sd:Label key="positionAddEditForm.posName" cssStyle="100%"/>
                </td>
                <td>
                    <sd:TextBox id="positionSearchForm.posName" name="positionSearchForm.posName"
                                key="" maxlength="180" cssStyle="width:100%" trim="true"/>
                </td>                     
            </tr>

            <tr style="text-align: center">
                <td colspan="4">
                    <sx:ButtonSearch onclick = "page.search();"></sx:ButtonSearch>
                    <sd:Button key="" onclick="page.reset();" > 
                            <img src="share/images/icons/reset.png" height="14" width="18"/>
                            <span style="font-size:12px">Hủy<%--<sd:Property>btnCancel</sd:Property> --%></span>
                        </sd:Button>
                    </td>
                </tr>

            </table>
        </form>
</sd:TitlePane>
</div>
    
<sd:TitlePane key="Danh mục chức danh"
              id="" >
    <sx:ResultMessage id="positionDeleteMessage"/>
    <table width="100%">
        <tr>
            <td>
                <script>
                    page.getNo = function(index) {
                        return dijit.byId("positionGrid").currentRow + index + 1;
                    }

                    page.getIndex = function(index) {
                        return index + 1;
                    }

                    page.formatPosition = function (inData) {

                        var item= dijit.byId("positionGrid").getItem(inData-1);
                        var url="";
                        if (item!=null) {
                            var posId = item.posId;
                            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                                        title='<sd:Property>category.edit</sd:Property>' \n\
                                        onClick='page.showEditPopup("+posId+");' /></div>";
                        }
                        return url;
                    }
                    page.onDisable = function(idx){                                        
                        var item =dijit.byId('positionGrid').getItem(idx);
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
                    <sd:DataGrid id="positionGrid"
                                 getDataUrl="position!onSearch.do?"                                 
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="category.No" get="page.getNo" width="3%"  styles="text-align:center;" />
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;" setDisabled="page.onDisable"/>
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatPosition" get="page.getIndex"
                                           width="30px"  headerStyles="text-align:center;"   >
                        </sd:ColumnDataGrid>
                        <sd:ColumnDataGrid key="positionAddEditForm.posCode" field="posCode" 
                                           width="20%"  headerStyles="text-align:center;"/>
                        <sd:ColumnDataGrid key="positionAddEditForm.posName" field="posName" 
                                           width="30%"  headerStyles="text-align:center;"/>
                        <sd:ColumnDataGrid key="positionAddEditForm.description" field="description" 
                                           width="50%"  headerStyles="text-align:center;"/>                     
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

<sd:Dialog  id="dlgAddEditPosition" height="auto" width="400px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="positionAddEdit.jsp" flush="false"></jsp:include> 
</sd:Dialog>

<script>

    var grid = dijit.byId("positionGrid");
    var dlgAddEditPosition = dijit.byId("dlgAddEditPosition");
    var insertDialog=false;
    
    page.search = function() {
        grid.vtReload('position!onSearch.do?', "positionSearchForm",null,null);  
    };
    
    page.insert = function() {
        dijit.byId("positionAddEditForm.posCode").setValue("");
        dijit.byId("positionAddEditForm.posName").setValue("");
        dijit.byId("positionAddEditForm.description").setValue("");
        insertDialog=true;
        dlgAddEditPosition.show();
    };
     
    page.showEditPopup = function(posId) {
        sd.connector.post("position!showEditPopup.do?posId="+posId, null, null, null, page.afterShowEditPopup);
    };
    
    page.afterShowEditPopup = function(data) {
        var obj = dojo.fromJson(data);
        var customInfo = obj.customInfo;
        
        if (customInfo.posId != null && customInfo.posId != -1) {
            dijit.byId("positionAddEditForm.posId").setValue(escapeHtml_(customInfo.posId));
        }
        
        if (customInfo.posCode != null) {
            dijit.byId("positionAddEditForm.posCode").setValue(escapeHtml_(customInfo.posCode));
        }

        if (customInfo.posName != null) {
            dijit.byId("positionAddEditForm.posName").setValue(escapeHtml_(customInfo.posName));
        }

        if (customInfo.description != null) {
            dijit.byId("positionAddEditForm.description").setValue(escapeHtml_(customInfo.description));
        }

        insertDialog=false;
        dlgAddEditPosition.show();
    };
    
    page.deleteItem = function(){
        if (!grid.vtIsChecked()){
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
        }
        else{
            msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);         
        }
    };

    page.deleteItemExecute=function(){
        var content = grid.vtGetCheckedDataForPost("lstItemOnGrid");
        sd.connector.post("position!onDelete.do?" +token.getTokenParamString(), null, null, content, page.returnMessageDelete);
    };

    page.returnMessageDelete = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("positionDeleteMessage", result[0], result[1], 2000);      
        grid.vtReload('position!onSearch.do?', "positionSearchForm",null,null);
    };
    
    page.showSearchPanel = function(){
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style","display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };
    page.reset = function(){
        dijit.byId("positionAddEditForm.posCode").setValue("");
        dijit.byId("positionAddEditForm.posName").setValue("");
    };
    page.search();
    //dijit.byId("positionTitlePaneId").attr("open", false);

</script>
