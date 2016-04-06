<%-- 
    Document   : books
    Created on : May 10, 2012, 9:14:22 AM
    Author     : sytv
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<jsp:include page="../util/util_js.jsp"/>
<jsp:include page="../common/commonJavascript.jsp"/>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="token">
    <s:token id="tokenId"/>
</div> 
<div id ="searchDiv" style="display:none">
<sd:TitlePane key="search.searchCondition" id="books">
    <form id="bookForm">
        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <td width="20%"></td>
                <td width="30%"></td>
                <td width="20%"></td>
                <td width="30%"></td>
            </tr>
            <tr>
                <td align="right">
                    <sd:Label key="book.bookName"/>
                </td>
                <td>
                    <sd:TextBox cssStyle="width:100% !important;"
                                id="bookForm.bookName"
                                key=""
                                name="bookForm.bookName"/>
                </td>
                <td align="right">
                    <sd:Label key="book.bookType"/>
                </td>
                <td>
                    <sd:SelectBox cssStyle="width:100% !important;"
                                  id="bookForm.bookType"
                                  key=""
                                  name="bookForm.bookType">
                        <sd:Option value="-1">-- Chọn --</sd:Option>
                        <sd:Option value="1"> Văn bản đến </sd:Option>
                        <sd:Option value="2">Văn bản đi</sd:Option>
                        <sd:Option value="3">Phiếu trình</sd:Option>
                        <%--     <sd:Option value="4">Tiếp nhận HS quảng cáo</sd:Option>
                             <sd:Option value="5">Tiếp nhận HS công bố</sd:Option>
     <sd:Option value="6">Tiếp nhận HS cấp GCN ĐĐK</sd:Option> --%>
                    </sd:SelectBox>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <sd:Label key="book.prefix"/>
                </td>
                <td>
                    <sd:TextBox cssStyle="width:100% !important;"
                                id="bookForm.prefix"
                                key=""
                                name="bookForm.prefix"/>
                </td>
                <td align="right">
                    <sd:Label key="book.currentNumber"/>
                </td>
                <td>
                    <sd:TextBox cssStyle="width:100% !important;"
                                id="bookForm.currentNumber"
                                key="" mask="digit"
                                name="bookForm.currentNumber"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <sd:Label key="book.office"/>
                </td>
                <td colspan="1">
                    <sd:TextBox cssStyle="width:100% !important;"
                                id="bookForm.officeName"
                                key=""
                                name="bookForm.officeName" 
                                readonly="true"/>
                    <sd:TextBox cssStyle="display: none" id="bookForm.officeId" key="" name="bookForm.officeName" />
                </td>
                <%--
                <td>
                    
                    <sd:Button cssStyle="margin: 0 0.2em!important;"
                               id="officeSelect" key="" onclick="page.officeSelect();">
                        <img src="share/images/icons/comment.png" height="10" width="10">
                    </sd:Button>    
                    <sd:Button cssStyle="margin: 0 0.2em!important;"
                               id="officeRemove" key="" onclick="page.officeRemove();">
                        <img src="share/images/icons/13.png" height="10" width="10">
                    </sd:Button>
                </td>
                --%>
            </tr>
            <tr style="display: none">
                <td align="right">
                    <sd:Label key="book.isDefault"/>
                </td>
                <td>
                    <sd:SelectBox cssStyle="width:100% !important;"
                                  id="bookForm.isDefault"
                                  key=""
                                  name="bookForm.isDefault">
                        <sd:Option value="-1">-- Chọn --</sd:Option>
                        <sd:Option value="0"> Sổ thường </sd:Option>
                        <sd:Option value="1">Sổ mặc định</sd:Option>
                    </sd:SelectBox>
                </td>
                <td align="right">
                    <sd:Label key="book.isPublic"/>
                </td>
                <td>
                    <sd:SelectBox cssStyle="width:100% !important;"
                                  id="bookForm.isPublic"
                                  key=""
                                  name="bookForm.isPublic">
                        <sd:Option value="-1">-- Chọn --</sd:Option>
                        <sd:Option value="0">Sổ cá nhân </sd:Option>
                        <sd:Option value="1">Sổ chung</sd:Option>
                    </sd:SelectBox>
                </td>
            </tr>
            <tr style="display: none">
                <td align="right">
                    <sd:Label key="book.followBy"/>
                </td>
                <td>
                    <sd:SelectBox cssStyle="width:100% !important;"
                                  id="bookForm.followBy"
                                  key=""
                                  name="bookForm.followBy">
                        <sd:Option value="-1">-- Chọn --</sd:Option>
                        <sd:Option value="0">Không </sd:Option>
                        <sd:Option value="2">Đơn vị</sd:Option>
                        <sd:Option value="3">Loại văn bản</sd:Option>
                    </sd:SelectBox>
                </td>
                <td align="right">
                    <sd:Label key="book.orderNumber"/>
                </td>
                <td>
                    <sd:TextBox cssStyle="width:100% !important;"
                                id="bookForm.orderNumber"
                                key="" mask="digit"
                                name="bookForm.orderNumber"/>
                </td>
            </tr>
            <tr style="text-align: center; ">
                <td colspan="4">
                    <sx:ButtonSearch onclick="page.search();" />

                    <sd:Button key="" onclick="page.reset();" > 
                        <img src="${contextPath}/share/images/icons/reset.png" height="14" width="14">
                        <span style="font-size:12px">Hủy</span>
                    </sd:Button>
                </td>
            </tr>

        </table>
    </form>
</sd:TitlePane>
</div>
<script>
    page.getNo = function(index) {
        return dijit.byId("booksGrid").currentRow + index + 1;
    }

    page.getIndex = function(index) {
        return index + 1;
    }

    page.formatBooks = function (inData) {

        var item= dijit.byId("booksGrid").getItem(inData-1);
        var url="";
        if (item!=null) {
            var bookId = item.bookId;
            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                                        title='<sd:Property>category.edit</sd:Property>' \n\
                                        onClick='page.showEditPopup("+bookId+");' /></div>";
        }
        return url;
    }
    
    page.formatBookDocType = function(inData){
        var item= dijit.byId("booksGrid").getItem(inData-1);
        var url="";
        if (item!=null) {
            var bookId = item.bookId;
            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/list.png' width='17px' height='17px' \n\
                                        title='Danh sách loại văn bản' \n\
                                        onClick='page.showDocTypePopup("+bookId+");' /></div>";
        }
        return url;
    }    
    
    page.bookType= function(inData) {
        var item= dijit.byId("booksGrid").getItem(inData-1);
        if(item != null && item.bookType != null){
            var bookType = item.bookType.toString();
            if(bookType =='1') {
                return 'Văn bản đến';
            }else if(bookType =='2') {
                return 'Văn bản đi';
            }else if(bookType =='3') {
                return 'Phiếu trình';
            }else if(bookType =='4' && item.recordsType.toString() == '1') {
                return 'Tiếp nhận HS quảng cáo';
            } else if(bookType =='4' && item.recordsType.toString() == '2'){
                return 'Tiếp nhận HS công bố';
            } else if(bookType =='4' && item.recordsType.toString() == '3'){
                return 'Tiếp nhận HS cấp GCN ĐĐK';
            } else {
                return "";
            }
        }
    }
    
    page.isDefault=function(inData) {        
        return (inData=='1') ? "Sổ mặc định" : "Sổ thường"; 
    }
    page.isPublic = function(inData) {
        return (inData=='1') ? "Sổ chung" : "Sổ cá nhân";
    }
    
    page.getRow = function(inRow){
        return inRow;
    }
</script>
<sd:TitlePane key="Danh mục sổ văn bản"
              id="flowRoleList" >
    <sx:ResultMessage id="deletedResultMessage"/>
    <table width="100%">       
        <tr>
            <td>
                <div id="" style="width:100%;">
                    <sd:DataGrid id="booksGrid"
                                 getDataUrl=""
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="book.No" get="page.getNo" width="3%"  styles="text-align:center;" />
                        <sd:ColumnDataGrid editable="true" key="book.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="2%" cellStyles="text-align:center;"/>
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatBooks" get="page.getIndex"
                                           width="5%"  headerStyles="text-align:center;"   >
                        </sd:ColumnDataGrid>
                        <sd:ColumnDataGrid key="Loại văn bản" formatter="page.formatBookDocType" get="page.getIndex"
                                           width="5%"  headerStyles="text-align:center;"   >
                        </sd:ColumnDataGrid>
                        <sd:ColumnDataGrid  key="book.bookNameGrid" field="bookName"
                                            width="27%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="book.bookTypeGrid" formatter="page.bookType" get="page.getIndex"
                                            width="12%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="book.prefixGrid" field="prefix"
                                            width="8%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="book.currentNumberGrid" field="currentNumber"
                                            width="7%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="book.officeGrid" field="officeName" 
                                            width="20%"  headerStyles="text-align:center;" />
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

<sd:Dialog  id="dlgbookAddEdit" height="auto" width="800"
            key="book.titleAddEdit" showFullscreenButton="false">
    <jsp:include page="booksAddEdit.jsp" flush="false"></jsp:include>
</sd:Dialog>

<sd:Dialog  id="dlgBookDocType" height="auto" width="800"
            key="Các loại văn bản trong sổ" showFullscreenButton="false">
    <jsp:include page="booksDocTypeDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>

<sd:Dialog  id="dlgBookAddDocType" height="auto" width="800"
            key="Thêm mới loại văn bản" showFullscreenButton="false">
    <jsp:include page="booksAddDocTypeDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>

<script>
    
    var grid = dijit.byId("booksGrid");
    var dialog = dijit.byId("dlgbookAddEdit");
    var insertDialog=false;
        
    page.search = function() {
        var dept = '${fn:escapeXml(dept)}';
        grid.vtReload('voBooks!search.do?dept='+dept, "bookForm",null,null);
    };

    page.reset = function() {
        dijit.byId("bookForm.bookName").setValue("");
        dijit.byId("bookForm.bookType").setValue("-1");
        dijit.byId("bookForm.prefix").setValue("");
        dijit.byId("bookForm.currentNumber").setValue("");
        dijit.byId("bookForm.isDefault").setValue("-1");
        dijit.byId("bookForm.isPublic").setValue("-1");
        dijit.byId("bookForm.followBy").setValue("-1");
        dijit.byId("bookForm.orderNumber").setValue("");
        // grid.vtReload('voBooks!search.do?', "bookForm",null,null);
    };

    page.insert = function() {
        dijit.byId("bookFormAddEdit.bookId").setValue("");
        dijit.byId("bookFormAddEdit.bookName").setValue("");
        dijit.byId("bookFormAddEdit.bookType").setValue("-1");
        dijit.byId("bookFormAddEdit.prefix").setValue("");
        dijit.byId("bookFormAddEdit.currentNumber").setValue("");
        dijit.byId("bookFormAddEdit.isDefault").setValue("-1");
        dijit.byId("bookFormAddEdit.isPublic").setValue("-1");
        dijit.byId("bookFormAddEdit.followBy").setValue("-1");
        dijit.byId("bookFormAddEdit.orderNumber").setValue("");
        insertDialog=true;
        dialog.show();
    };

    page.showEditPopup = function(bookId) {
        sd.connector.post("voBooks!showEditPopup.do?bookId=" + bookId, null, null, null, page.afterShowEditPopup);
    };
    
    page.showDocTypePopup = function(bookId){
        currentBookId= bookId;
        var docTypeDlg = dijit.byId("dlgBookDocType");
        docTypeDlg.show();
        dijit.byId("docTypeGrid").vtReload("voBooks!loadDocTypeOfBook.do?bookId="+currentBookId);
    };

    page.afterShowEditPopup = function(data) {
        var obj = dojo.fromJson(data);
        var customInfo = obj.customInfo;

        if (customInfo.bookId != null) {
            dijit.byId("bookFormAddEdit.bookId").setValue(escapeHtml_(customInfo.bookId));
        }

        if (customInfo.bookName != null) {
            dijit.byId("bookFormAddEdit.bookName").setValue(escapeHtml_(customInfo.bookName));
        }

        if (customInfo.bookType != null) {
            dijit.byId("bookFormAddEdit.bookType").setValue(escapeHtml_(customInfo.bookType));
        }
        if(customInfo.prefix!=null) {
            dijit.byId("bookFormAddEdit.prefix").setValue(escapeHtml_(customInfo.prefix));
        }
        if(customInfo.currentNumber!=null) {
            dijit.byId("bookFormAddEdit.currentNumber").setValue(escapeHtml_(customInfo.currentNumber));
        }
        if(customInfo.officeName!=null) {
            dijit.byId("bookFormAddEdit.officeName").setValue(escapeHtml_(customInfo.officeName));
        }
        if(customInfo.isDefault!=null) {
            dijit.byId("bookFormAddEdit.isDefault").setValue(escapeHtml_(customInfo.isDefault));
        }
        if(customInfo.isPublic!=null) {
            dijit.byId("bookFormAddEdit.isPublic").setValue(escapeHtml_(customInfo.isPublic));
        }
        if(customInfo.followBy!=null) {
            dijit.byId("bookFormAddEdit.followBy").setValue(escapeHtml_(customInfo.followBy));
        }
        if(customInfo.orderNumber!=null) {
            dijit.byId("bookFormAddEdit.orderNumber").setValue(escapeHtml_(customInfo.orderNumber));
        }
        insertDialog=false;
        dialog.show();
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
        sd.connector.post("voBooks!gridDeleteData.do?"+ token.getTokenParamString(), null, null, content, page.returnMessageDelete);
    };

    page.returnMessageDelete = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        showResultMessage("deletedResultMessage", result[0], result[1]);
        grid.vtReload('voBooks!search.do?', "bookForm",null,null);
    };
    
    page.showSearchPanel = function(){
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style","display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };

    page.search();
</script>

