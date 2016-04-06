<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<jsp:include page="../util/util_js.jsp"/>

<script>
    page.getNo = function(index) {
        return dijit.byId("documentReceiveGrid").currentRow + index + 1;
    }

    page.getIndex = function(index) {
        return index + 1;
    }
  
</script>
<sd:TitlePane key="report.condition" id=""> 
    <form id="docReceiveSearchForm" name="docReceiveSearchForm">
        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <td width="20%"></td>
                <td width="30%"></td>
                <td width="20%"></td>
                <td width="20%"></td>
                <td width="10%"></td>
            </tr>

            <tr>
                <td align="right">
                    <sd:Label key="docReceive.bookNumber"/>
                </td>
                <td>
                    <sd:SelectBox id="docReceiveSearchForm.bookId" 
                                  cssStyle="width:100%"
                                  name="docReceiveSearchForm.bookId"
                                  labelField="bookName" valueField="bookId" key=""
                                  data="bookList"/>
                    <sd:TextBox cssStyle="display:none"
                                        id="docReceiveSearchForm.bookName" 
                                        name="docReceiveSearchForm.bookName" 
                                        key=""/>
                </td>
                <td align="right" style="display:">
                    <sd:Label key="docReceive.type"/>
                </td>
                <td colspan="2" style="display:">
                    <sd:SelectBox id="docReceiveSearchForm.documentTypeId" 
                                  cssStyle="width:100%"
                                  name="docReceiveSearchForm.documentTypeId"
                                  labelField="name" valueField="categoryId" key=""
                                  data="docType"/>
                    <sd:TextBox cssStyle="display:none"
                                        id="docReceiveSearchForm.documentType" 
                                        name="docReceiveSearchForm.documentType" 
                                        key=""/>
                </td>
            </tr>
            <tr>
                <td align="right"> 
                    <sd:Label key="docReceive.documentReceiveDate"/>

                </td>
                <td align="right" colspan="1">
                    <sd:Label key="docReceive.documentDateFrom" />
                    <sx:DatePicker id="docReceiveSearchForm.documentReceiveDateFrom" name="docReceiveSearchForm.documentReceiveDateFrom" key="" 
                                   value="" format="dd/MM/yyyy" cssStyle="width:80%;"/>                    

                </td>
                <td align="right">
                    <sd:Label key="docReceive.documentDateTo" cssStyle="floating"/>
                </td>
                <td align="left" colspan="2">
                    <sx:DatePicker id="docReceiveSearchForm.documentReceiveDateTo" name="docReceiveSearchForm.documentReceiveDateTo" key="" 
                                   value="" format="dd/MM/yyyy" cssStyle="width:100%;"/>   
                </td>
            </tr>
            <tr>
                <td colspan="5" align="center">
                    <sx:ButtonSearch onclick="page.searchDocReceive()"/>
                    <sx:ButtonExcel onclick="page.reportDocReceive()"/>
                </td>
            </tr>
        </table>
    </form>
</sd:TitlePane>
<sd:TitlePane id="documentReceiveGridTitle" key="documentReceiveTitle">    
    <sd:DataGrid id="documentReceiveGrid"
                 getDataUrl=""
                 style="width:auto;height:auto"
                 rowsPerPage="10"

                 serverPaging="true"
                 clientSort="false">
        <sd:ColumnDataGrid key="book.No" get="page.getNo" width="30px"
                           headerStyles="text-align:center;font-weight:bold" cellStyles="text-align:center"/>
        <sd:ColumnDataGrid  key="Số đến" field="bookNumber" width="7%"
                            headerStyles="text-align:center;font-weight:bold" cellStyles="text-align:center"/>
        <sd:ColumnDataGrid  key="Ngày đến" field="receiveDate" width="8%" format="dd/MM/yyyy" type="date"
                            headerStyles="text-align:center;font-weight:bold"/>
        <sd:ColumnDataGrid  key="Số hiệu gốc" field="documentCode" width="10%"
                            headerStyles="text-align:center;font-weight:bold" />
        <sd:ColumnDataGrid  key="Cơ quan ban hành" field="publisherName" width="15%"
                            headerStyles="text-align:center;font-weight:bold" />
        <sd:ColumnDataGrid  key="Trích yếu" field="documentAbstract" width="25%"
                            headerStyles="text-align:center;font-weight:bold" />
        <sd:ColumnDataGrid  key="Nơi xử lý" field="receiveGroup" width="15%"
                            headerStyles="text-align:center;font-weight:bold" />
        <sd:ColumnDataGrid  key="Lãnh đạo phụ trách" field="receiveUser" width="12%"
                            headerStyles="text-align:center;font-weight:bold" />
        <sd:ColumnDataGrid  key="Trạng thái" field="statusStr" width="10%"
                            headerStyles="text-align:center;font-weight:bold" />

    </sd:DataGrid>
    <sx:ResultMessage id="resultDocumentReceiveAction"/>
</sd:TitlePane>
<script>

    //    dijit.byId("docReceiveSearchForm.documentReceiveDateFrom").setValue(new Date());
    //    dijit.byId("docReceiveSearchForm.documentReceiveDateTo").setValue(new Date());
    page.setForm = function (){
        var bookId = dijit.byId("docReceiveSearchForm.bookId").value;
        if(bookId != "" && bookId != -1){
            var bookName = document.getElementById("docReceiveSearchForm.bookId").value;
            dijit.byId("docReceiveSearchForm.bookName").setValue(bookName);
        }
//        alert(dijit.byId("docReceiveSearchForm.bookName").value);
        var documentTypeId = dijit.byId("docReceiveSearchForm.documentTypeId").value;
        if(documentTypeId != "" && documentTypeId != -1){
            dijit.byId("docReceiveSearchForm.documentType").attr("value",document.getElementById("docReceiveSearchForm.documentTypeId").value);
        }
    }
    page.reportDocReceive = function(){
        if(!page.validateFormSearch()){
            page.setForm();
            return;
        }
        var url = "voDocumentReceive!bookDocumentReport.do";        
        document.docReceiveSearchForm.action =url;
        document.docReceiveSearchForm.submit();
    }
    
    onSearch = function(){
        var text = document.getElementById("searchForm.text").value;
        var fromDate = dojo.attr(dojo.byId("searchForm.fromDate"),"value");
        var toDate = dojo.attr(dojo.byId("searchForm.toDate"),"value");
        if(fromDate!=null && fromDate.toString() != "") {
            var yyyy = fromDate.substring(6,10);
            var mm = fromDate.substring(3,5);
            var dd = fromDate.substring(0,2);
            dijit.byId("docReceiveSearchForm.documentReceiveDateFrom").attr("value",new Date(yyyy,mm - 1 ,dd));
        } else {
            dijit.byId("docReceiveSearchForm.documentReceiveDateFrom").attr("value","");
        }
        if(toDate!=null && toDate.toString() != "") {
            var yyyy = toDate.substring(6,10);
            var mm = toDate.substring(3,5);
            var dd = toDate.substring(0,2);
            dijit.byId("docReceiveSearchForm.documentReceiveDateTo").attr("value",new Date(yyyy,mm - 1 ,dd));
        } else {
            dijit.byId("docReceiveSearchForm.documentReceiveDateTo").attr("value","");
        }

        //page.searchDocReceive();
    }

    page.searchDocReceive = function(){        
        if(page.validateFormSearch()){
            page.setForm();
            dijit.byId("documentReceiveGrid").vtReload('voDocumentReceive!onSearchForBookDocumentReport.do?', "docReceiveSearchForm",null,null); 
        }
    }
    
    page.validateFormSearch= function(){    
        var bookId = dijit.byId("docReceiveSearchForm.bookId").getValue();
//        if(bookId < 0){
//            msg.alert("Bạn chưa chọn sổ");
//            return false;
//        }

        var startReceiveDate = dijit.byId("docReceiveSearchForm.documentReceiveDateFrom");
        if(startReceiveDate == null || startReceiveDate.toString() == ""){
            msg.alert("Bạn chưa nhập từ ngày");
            return false;
        }
        if (!page.isDate(startReceiveDate,false,"Từ ngày")) {
            return false;
        }
        var endReceiveDate = dijit.byId("docReceiveSearchForm.documentReceiveDateTo");
        if(endReceiveDate == null || endReceiveDate.toString() == ""){
            msg.alert("Bạn chưa nhập đến ngày");
            return false;
        }
        if (!page.isDate(endReceiveDate,false,"Đến ngày")) {
            return false;
        }
        if(!page.compareDate(startReceiveDate, endReceiveDate)) {
            startReceiveDate.focus();
            msg.alert("Từ ngày phải nhỏ hơn đến ngày ","<sd:Property>confirm.title</sd:Property>");            
            return false;
        }
        
        if (!page.compareDateXday(startReceiveDate,endReceiveDate,366)){
            startReceiveDate.focus();
            msg.alert("Thời gian thực hiện báo cáo không quá 1 năm ","<sd:Property>confirm.title</sd:Property>");            
            return false;
        }
        
        
        return true;
    }
    onSearch();
</script>