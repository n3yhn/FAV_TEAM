<%-- 
    Document   : bookAddEdit
    Created on : May 10, 2012, 9:47:53 AM
    Author     : sytv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<jsp:include page="../util/util_js.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sx:ResultMessage id="deletedDocTypeResultMessage"/>
<div id="" style="width:100%;">
    <sd:DataGrid id="docTypeGrid"
                 getDataUrl=""
                 rowSelector="0%"
                 style="width:auto;height:auto"
                 rowsPerPage="10"
                 serverPaging="true"
                 clientSort="false">
        <sd:ColumnDataGrid key="category.No" get="page.getNo" width="3%"  styles="text-align:center;" />
        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;" />
        <sd:ColumnDataGrid  key="category.code" field="code"
                            width="20%"  headerStyles="text-align:center;" />
        <sd:ColumnDataGrid  key="category.name" field="name"
                            width="50%"  headerStyles="text-align:center;" />
    </sd:DataGrid>
</div>
<br>
<sx:ButtonAddCategory onclick="insertDocType();"/>
<sx:ButtonDelete onclick="deleteDocType();" />

<script type="text/javascript">
    var currentBookId;
    insertDocType = function(){
        var addDocTypeDlg = dijit.byId("dlgBookAddDocType");
        addDocTypeDlg.show();  
        dijit.byId("categoryGrid").vtReload("voBooks!loadDocTypeNotInBook.do?bookId="+currentBookId);
    }
    
    deleteDocType = function(){
        var categoryIds = "";
        var items = dijit.byId("docTypeGrid").vtGetCheckedItems();
        for(var j = 0; j < items.length;j++){
            if(categoryIds == ""){
                categoryIds = items[j].categoryId;
            } else {
                categoryIds = categoryIds +","+ items[j].categoryId;
            }
        }
        if(categoryIds == ""){
            msg.alert("Bạn phải chọn item để xóa");
            return;
        }
        sd.connector.post("voBooks!removeDocTypeOfBook.do?"+token.getTokenParamString()+"&bookId="+currentBookId+"&categoryIds="+categoryIds,null,null,null,page.returnMessageDeletedocType);
    }
    
    page.returnMessageDeletedocType = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        showResultMessage("deletedDocTypeResultMessage", result[0], result[1]);
        dijit.byId("docTypeGrid").vtReload('voBooks!loadDocTypeOfBook.do?bookId='+currentBookId, null,null,null);
    }    
</script>