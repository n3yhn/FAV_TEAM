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
<sx:ResultMessage id="categoryResultAddMessage"/>        
<sd:TitlePane key="search.searchCondition" id="categoryTitle">
    <form id="categorySearchForm">
        <sd:TextBox cssStyle="width:100%;display:none"
                    id="categorySearchForm.type"
                    key=""
                    name="categorySearchForm.type"
                    value="${fn:escapeXml(type)}">
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
                    <sd:Label key="category.code"/>
                </td>
                <td>
                    <sd:TextBox cssStyle="width:100%"
                                id="categorySearchForm.code"
                                key=""
                                name="categorySearchForm.code">
                    </sd:TextBox>
                </td>
                <td align="right">
                    <sd:Label key="category.name"/>
                </td>
                <td>
                    <sd:TextBox cssStyle="width:100%"
                                id="categorySearchForm.name"
                                key=""
                                name="categorySearchForm.name">
                    </sd:TextBox>
                </td>
            </tr>
            <tr style="text-align: center">
                <td colspan="4">
                    <sx:ButtonSearch onclick="page.searchDocType();" />
                    <sd:Button key="" onclick="page.resetDocType();" > 
                        <img src="${contextPath}/share/images/icons/reset.png" height="14" width="18">
                        <span style="font-size:12px">Hủy</span>
                    </sd:Button>
                </td>
            </tr>

        </table>
    </form>
</sd:TitlePane>

<sd:TitlePane key="search.searchResult"
              id="categoryList" >
    <sx:ResultMessage id="categoryResultDeleteMessage" />
    <table width="100%">
        <tr>
            <td>
                <script>
                    page.getNo = function(index) {
                        return dijit.byId("categoryGrid").currentRow + index + 1;
                    }

                    page.getIndex = function(index) {
                        return index + 1;
                    }

                    page.formatCategory = function (inData) {

                        var item= dijit.byId("categoryGrid").getItem(inData-1);
                        var url="";
                        if (item!=null) {
                            var categoryId = item.categoryId;
                            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                                        title='<sd:Property>category.edit</sd:Property>' \n\
                                        onClick='page.showEditPopup("+categoryId+");' /></div>";
                        }
                        return url;
                    }
                   
                    page.onDisable = function(idx){                                        
                        var item =dijit.byId('categoryGrid').getItem(idx);
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
                <sd:DataGrid id="categoryGrid"
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
            </td>
        </tr>
        <tr>
            <td>
                <sx:ButtonAddCategory onclick="page.insertDocType();"/>
            </td>
        </tr>
    </table>
</sd:TitlePane>

<script>
    
    var gridOfCategory = dijit.byId("categoryGrid");

    page.searchDocType = function() {
        gridOfCategory.vtReload('voBooks!loadDocTypeNotInBook.do?bookId='+currentBookId, "categorySearchForm",null,null);
    }

    page.resetdocType = function() {
        dijit.byId("categorySearchForm.code").setValue("")
        dijit.byId("categorySearchForm.name").setValue("")
        page.searchDocType();
    }

    page.insertDocType = function() {
        var categoryIds = "";
        var items = dijit.byId("categoryGrid").vtGetCheckedItems();
        for(var j = 0; j < items.length;j++){
            if(categoryIds == ""){
                categoryIds = items[j].categoryId;
            } else {
                categoryIds = categoryIds +","+ items[j].categoryId;
            }
        }
        if(categoryIds == ""){
            msg.alert("Bạn phải chọn item để thêm");
            return;
        }
        sd.connector.post("voBooks!addDocTypeOfBook.do?"+token.getTokenParamString()+"&bookId="+currentBookId+"&categoryIds="+categoryIds,null,null,null,page.returnMessageAddDocType);
    }


    page.returnMessageAddDocType = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("categoryResultAddMessage", result[0], result[1], 5000);
        dijit.byId("docTypeGrid").vtReload('voBooks!loadDocTypeOfBook.do?bookId='+currentBookId, null,null,null);
        page.searchDocType();
    }
    
</script>
