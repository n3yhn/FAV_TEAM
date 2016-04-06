<%-- 
    Document   : announcementReceiptPaperSearch
    Created on : Jul 1, 2013, 2:52:39 PM
    Author     : vtit_binhnt53
    Description: màn hình danh sách thông tin giấy tiếp nhận bản công bố hợp quy
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../../util/util_js.jsp"/>
<jsp:include page="../../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="mainDiv">
    <div id="token">
        <s:token id="tokenId"/>
    </div>
    <div id ="searchDiv" style="display:none">

        <sd:TitlePane key="search.searchCondition" id="receiptNoTitle">
            <form id="searchForm" name="searchForm">
                <table width="100%;" cellpadding="0px" cellspacing="5px">
                    <tr>
                        <td width="20%"></td>
                        <td width="30%"></td>
                        <td width="20%"></td>
                        <td width="30%"></td>
                    </tr>
                    <tr>
                        <td align="right">
                            <sd:Label key="Số giấy tiếp nhận:"/>
                        </td>
                        <td>
                            <sd:TextBox cssStyle="width:100%"
                                        id="searchForm.receiptNo"
                                        key=""
                                        name="searchForm.receiptNo" 
                                        maxlength="20"
                                        trim="true"/>
                        </td>
                        <td align="right">
                            <sd:Label key="Tên sản phẩm:"/>
                        </td>
                        <td>
                            <sd:TextBox cssStyle="width:100%"
                                        id="searchForm.productName"
                                        key=""
                                        name="searchForm.productName" 
                                        maxlength="255"
                                        trim="true"/>
                        </td>
                    </tr>
                    <tr style="text-align: center">
                        <td colspan="4">
                            <sx:ButtonSearch onclick="page.search();" />
                            <sd:Button key="" onclick="page.reset();" > 
                                <img src="${contextPath}/share/images/icons/reset.png" height="14" width="18">
                                <span style="font-size:12px">Hủy<%--<sd:Property>btnCancel</sd:Property> --%></span>
                            </sd:Button>
                        </td>
                    </tr>

                </table>
            </form>
        </sd:TitlePane>
    </div>        

    <sd:TitlePane key="Chức năng nhập thông tin giấy tiếp nhận bản công bố hợp quy"
                  id="List" >
        <sx:ResultMessage id="resultDeleteMessage" />
        <table width="100%">
            <tr>
                <td>
                    <script>
                        page.getNo = function(index) {
                            return dijit.byId("Grid").currentRow + index + 1;
                        };

                        page.getIndex = function(index) {
                            return index + 1;
                        };

                        page.formatEdit = function(inData) {
                            var row = inData - 1;
                            var item = dijit.byId("Grid").getItem(row);
                            var url = "";
                            if (item !== null) {
                                var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                            title='<sd:Property>category.edit</sd:Property>' \n\
                            onClick='page.showEditPopup(" + row + ");' /></div>";
                            }
                            return url;
                        };

                        // enter key
                        page.searchDefault = function(evt) {
                            var dk = dojo.keys;
                            switch (evt.keyCode) {
                                case dk.ENTER:
                                    page.search();
                                    break;
                            }
                        }

                        dojo.connect(dojo.byId("searchForm"), "onkeypress", page.searchDefault);

                        </script>
                        <div id="" style="width:100%;">
                        <sd:DataGrid id="Grid"
                                     getDataUrl=""
                                     rowSelector="0%"
                                     style="width:auto;height:auto"
                                     rowsPerPage="20"
                                     serverPaging="true"
                                     clientSort="false">
                            <sd:ColumnDataGrid key="STT" get="page.getNo" width="3%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatEdit" get="page.getIndex"
                                               width="5%"  headerStyles="text-align:center;"  />
                            <sd:ColumnDataGrid  key="Số giấy tiếp nhận" field="receiptNo"
                                                width="20%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName"
                                                width="50%"  headerStyles="text-align:center;" />
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
</div>
<div id="createDiv" style="display: none">    
    <jsp:include page="announcementReceiptPaperCreateDlg.jsp" flush="false"/>
</div>

<script>
    //
    var grid = dijit.byId("Grid");
    //set data
    //action search
    page.search = function() {
        grid.vtReload('announcementReceiptPaperAction!onSearch.do?', "searchForm", null, null);
    };
    //action reset field for search form
    page.reset = function() {
        dijit.byId("searchForm.receiptNo").setValue("");
        dijit.byId("searchForm.productName").setValue("");
        page.search();
    };
    //action insert show dialog
    page.insert = function() {
        page.clearInsertForm();
        document.getElementById("mainDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
    };
    //action clear before insert
    page.clearInsertForm = function() {
        dijit.byId("createForm.announcementReceiptPaperId").setValue("");
        dijit.byId("createForm.receiptNo").setValue("");
        dijit.byId("createForm.effectiveDate").setValue("");
        dijit.byId("createForm.receiptDate").setValue("");
        dijit.byId("createForm.receiptDeptName").setValue("");
        dijit.byId("createForm.businessName").setValue("");
        dijit.byId("createForm.telephone").setValue("");
        dijit.byId("createForm.fax").setValue("");
        dijit.byId("createForm.email").setValue("");
        dijit.byId("createForm.productName").setValue("");
        dijit.byId("createForm.manufactureName").setValue("");
        dijit.byId("createForm.nationName").setValue(-1);
        dijit.byId("createForm.matchingTarget").setValue("");
        dijit.byId("createForm.signDate").setValue("");
        dijit.byId("createForm.signerName").setValue("");
        dijit.byId("createForm.isActive").setValue(1);
    };
    page.showEditPopup = function(row) {
        var item = dijit.byId("Grid").getItem(row);
        page.setItem(item);
        document.getElementById("mainDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
    }

    page.setItem = function(item) {
        dijit.byId("createForm.announcementReceiptPaperId").setValue(item.announcementReceiptPaperId);
        dijit.byId("createForm.receiptNo").setValue(item.receiptNo);
        if (item.effectiveDate != "") {
            dijit.byId("createForm.effectiveDate").setValue(page.convertStringToDate(item.effectiveDate));
        }
        if (item.receiptDate != "") {
            dijit.byId("createForm.receiptDate").setValue(page.convertStringToDate(item.receiptDate));
        }
        dijit.byId("createForm.receiptDeptName").setValue(item.receiptDeptName);
        dijit.byId("createForm.businessName").setValue(item.businessName);
        dijit.byId("createForm.telephone").setValue(item.telephone);
        dijit.byId("createForm.fax").setValue(item.fax);
        dijit.byId("createForm.email").setValue(item.email);
        dijit.byId("createForm.productName").setValue(item.productName);
        dijit.byId("createForm.manufactureName").setValue(item.manufactureName);
        dijit.byId("createForm.nationName").setValue(item.nationName);
        dijit.byId("createForm.matchingTarget").setValue(item.matchingTarget);
        if (item.signDate != "") {
            dijit.byId("createForm.signDate").setValue(page.convertStringToDate(item.signDate));
        }
        dijit.byId("createForm.signerName").setValue(item.signerName);
        dijit.byId("createForm.isActive").setValue(1);
    }
    page.deleteItem = function() {
        if (!dijit.byId("Grid").vtIsChecked()) {
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
                    }
                    else {
                        msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
                                }
                            };

                            page.deleteItemExecute = function() {
                                var content = grid.vtGetCheckedDataForPost("lstItemOnGrid");
                                sd.connector.post("announcementReceiptPaperAction!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
                            };

                            page.returnMessageDelete = function(data) {
                                var obj = dojo.fromJson(data);
                                var result = obj.items;
                                resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
                                page.search();
                            };
                            page.convertStringToDate = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dgDate) {
                                try {
                                    var dateStr = page.getString(dgDate);
                                    var temp = dateStr.split("-");
                                    return new Date(temp[1] + "/" + temp[2].split("T")[0] + "/" + temp[0]);
                                } catch (e) {
                                    page.alert("Thông báo", "function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss", "warning");
                                    return undefined;
                                }
                            }
                            page.showSearchPanel = function() {
                                var panel = document.getElementById("searchDiv");
                                panel.setAttribute("style", "display:;");
                                dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
                            };
                            //action hide form
                            page.close = function() {
                                document.getElementById("mainDiv").style.display = "";
                                document.getElementById("createDiv").style.display = "none";
                                page.search();
                            };
                            page.search();
</script>
