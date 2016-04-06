<%-- 
    Document   : confirmAnnouncementPaperSearch
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
<div id="token">
    <s:token id="tokenId"/>
</div>
<div id ="searchDiv" style="display:none">

    <sd:TitlePane key="search.searchCondition" id="confirmationNoTitle">
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
                        <sd:Label key="Số kí hiệu giấy xác nhận:"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.confirmationNo"
                                    key=""
                                    name="searchForm.confirmationNo" 
                                    maxlength="20"
                                    trim="true"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Ngày cấp:"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.dateOfIssue"
                                       key=""
                                       name="searchForm.dateOfIssue" format="dd/MM/yyyy"/>
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

<sd:TitlePane key="Chức năng nhập thông tin giấy xác nhận công bố phù hợp quy định an toàn thực phẩm"
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
                        <sd:ColumnDataGrid  key="Số hiệu" field="confirmationNo"
                                            width="20%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Ngày cấp" field="dateOfIssue"
                                            type="date" format="dd/MM/yyyy" width="50%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Cơ quan cấp" field="issueAgencyName"
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

<sd:Dialog  id="dlgConfirmAnnouncementPaperCreate" height="auto" width="400px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="confirmAnnouncementPaperCreateDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>

<script>
    //
    var grid = dijit.byId("Grid");
    var dlgConfirmAnnouncementPaperCreate = dijit.byId("dlgConfirmAnnouncementPaperCreate");
    //action search
    page.search = function() {
        grid.vtReload('confirmAnnouncementPaperAction!onSearch.do?', "searchForm", null, null);
    };
    //action reset field for search form
    page.reset = function() {
        dijit.byId("searchForm.confirmationNo").setValue("");
        dijit.byId("searchForm.dateOfIssue").setValue("");
        page.search();
    };
    //action insert show dialog
    page.insert = function() {
        page.clearInsertForm();
        dlgConfirmAnnouncementPaperCreate.show();
    };
    //action clear before insert
    page.clearInsertForm = function() {
        dijit.byId("createForm.confirmationNo").setValue("");
        dijit.byId("createForm.confirmAnnouncementPaperId").setValue("");
        dijit.byId("createForm.dateOfIssue").setValue("");
        dijit.byId("createForm.issueAgencyName").setValue("");
        dijit.byId("createForm.isActive").setValue(1);
    };
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
                                sd.connector.post("confirmAnnouncementPaperAction!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
                            };

                            page.returnMessageDelete = function(data) {
                                var obj = dojo.fromJson(data);
                                var result = obj.items;
                                resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
                                page.search();
                            };

                            page.showSearchPanel = function() {
                                var panel = document.getElementById("searchDiv");
                                panel.setAttribute("style", "display:;");
                                dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
                            };
                            page.showEditPopup = function(row) {
                                var item = dijit.byId("Grid").getItem(row);
                                page.setItem(item);
                                dlgConfirmAnnouncementPaperCreate.show();
                            }

                            page.setItem = function(item) {
                                dijit.byId("createForm.confirmationNo").setValue(item.confirmationNo);
                                dijit.byId("createForm.confirmAnnouncementPaperId").setValue(item.confirmAnnouncementPaperId);
                                if (item.dateOfIssue != "") {
                                    dijit.byId("createForm.dateOfIssue").setValue(page.convertStringToDate(item.dateOfIssue));
                                }
                                dijit.byId("createForm.issueAgencyName").setValue(item.issueAgencyName);
                                dijit.byId("createForm.isActive").setValue(1);
                            }
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
                            page.search();
</script>
