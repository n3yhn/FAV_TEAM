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

    <sd:TitlePane key="search.searchCondition" id="technicalStandardTitle">
        <form id="searchForm" name="searchForm">
            <table width="100%">
                <tr>
                    <td width="10%"></td>
                    <td width="10%"></td>
                    <td width="35%"></td>
                    <td width="10%"></td>
                    <td width="35%"></td>
                </tr>
                <tr>
                    <td></td>
                    <td align="right">
                        <sd:Label key="Số hiệu qui chuẩn:"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" 
                                    id="searchForm.standardCode"
                                    key=""
                                    name="searchForm.standardCode" 
                                    maxlength="50" 
                                    trim="true"/>
                        <sd:TextBox id="searchForm.standardType" name="searchForm.standardType" cssStyle="display:none" key=""/>
                    </td>
                    <td align="right">
                        <sd:Label key="Tên qui chuẩn:"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.vietnameseName"
                                    key=""
                                    name="searchForm.vietnameseName" 
                                    maxlength="200"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td align="right">
                        <sd:Label key="Đơn vị ban hành:"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.publishAgencyName"
                                    key=""
                                    name="searchForm.publishAgencyName" 
                                    maxlength="20"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Ngày ban hành"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Từ ngày:"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.publishDateFrom"
                                       key=""
                                       name="searchForm.publishDateFrom" 
                                       format="dd/MM/yyyy"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày:"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.publishDateTo"
                                       key=""
                                       name="searchForm.publishDateTo" 
                                       format="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Ngày hiệu lực thi hành"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Từ ngày:"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.effectiveDateFrom"
                                       key=""
                                       name="searchForm.effectiveDateFrom" 
                                       format="dd/MM/yyyy"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày:"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.effectiveDateTo"
                                       key=""
                                       name="searchForm.effectiveDateTo" 
                                       format="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Ngày hết hạn"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Từ ngày:"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.expireDateFrom"
                                       key=""
                                       name="searchForm.expireDateFrom" 
                                       format="dd/MM/yyyy"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày:"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.expireDateTo"
                                       key=""
                                       name="searchForm.expireDateTo" 
                                       format="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr style="text-align: center">
                    <td></td>
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

<sd:TitlePane key="Quản lí qui chuẩn kĩ thuật"
              id="technicalStandardList" >
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
                        if (item != null) {
                            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                        title='<sd:Property>category.edit</sd:Property>' \n\
                        onClick='page.showEditPopup(" + row + ");' /></div>";
                        }
                        return url;
                    };
                    page.formatStatus = function(inData) {
                        var item = dijit.byId("Grid").getItem(inData - 1);
                        var url = "";
                        if (item != null) {
                            var status = parseInt(item.status);
                            switch (status) {
                                case 0:
                                    url = "Chưa công bố";
                                    break;
                                case 1:
                                    url = "Đã công bố";
                                    break;
                                case 2:
                                    url = "Đã chấm dứt";
                                    break;
                                default:
                                    url = "Chưa công bố";
                            }
                        }

                        return url;
                    };
                    page.disabledCheckbox = function(inData) {
                        var item = dijit.byId("Grid").getItem(inData);
                        var check = true;
                        if (item != null) {
                            var status = parseInt(item.status);
                            switch (status) {
                                case 0:
                                    check = true;
                                    break;
                                case 1:
                                    check = false;
                                    break;
                                default:
                                    check = true;
                            }
                        }

                        return check;
                    };
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
                        <sd:ColumnDataGrid  key="Số hiệu" field="standardCode"
                                            width="20%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Tên TV quy chuẩn" field="vietnameseName"
                                            width="25%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Đối tượng áp dụng" field="applicationObject"
                                            width="25%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Phạm vi áp dụng" field="scope"
                                            width="20%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Trạng thái" get="page.getNo" formatter="page.formatStatus" cellStyles="text-align:center;"
                                            width="10%"  headerStyles="text-align:center;" />
                    </sd:DataGrid>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <sx:ButtonAddCategory onclick="page.insert();"/>                
                <sx:ButtonDelete onclick="page.deleteItem()" />
                <sx:ButtonSearch id="btnShowSearchPanel" onclick="page.showSearchPanel();" />
                <sd:Button key="" onclick="page.publishedItem();" >
                    <img src="${contextPath}$/share/images/icons/Answer.png" height="14" width="18">
                    <span style="font-size:12px">Công bố</span>
                </sd:Button>
                <sd:Button key="" onclick="page.terminateItem();" > 
                    <img src="${contextPath}/share/images/icons/unapproved.png" height="14" width="18">
                    <span style="font-size:12px">Chấm dứt hiệu lực</span>
                </sd:Button>
            </td>
        </tr>
    </table>
</sd:TitlePane>

<sd:Dialog  id="dlg" height="auto" width="800px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="technicalStandardCreateDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>

<script>
    var grid = dijit.byId("Grid");
    var dlg = dijit.byId("dlg");

    page.showEditPopup = function(row) {
        var item = grid.getItem(row);
        page.setItem(item);
        dlg.show();
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
    };

    page.setItem = function(item) {
        dijit.byId("createForm.technicalStandardId").setValue(item.technicalStandardId);
        dijit.byId("createForm.standardCode").setValue(item.standardCode);
        dijit.byId("createForm.englishName").setValue(item.englishName);
        dijit.byId("createForm.vietnameseName").setValue(item.vietnameseName);
        dijit.byId("createForm.summary").setValue(item.summary);
        dijit.byId("createForm.applicationObject").setValue(item.applicationObject);
        if (item.publishDate != "") {
            dijit.byId("createForm.publishDate").setValue(page.convertStringToDate(item.publishDate));
        }
        if (item.effectiveDate != "") {
            dijit.byId("createForm.effectiveDate").setValue(page.convertStringToDate(item.effectiveDate));
        }
        if (item.expireDate != "") {
            dijit.byId("createForm.expireDate").setValue(page.convertStringToDate(item.expireDate));
        }
        dijit.byId("createForm.scope").setValue(item.scope);
        dijit.byId("createForm.standardType").setValue(item.standardType);
        dijit.byId("createForm.status").setValue(item.status);
        dijit.byId("createForm.publishAgencyName").setValue(item.publishAgencyName);
        clearAttFile("createForm.upload");
        getAttacthFile(item.technicalStandardId, 23, "createForm.upload");
    };

    page.search = function() {
        grid.vtReload('technicalStandardAction!onSearch.do?', "searchForm", null, null);
    };

    page.reset = function() {
        dijit.byId("searchForm.standardCode").setValue("");
        dijit.byId("searchForm.vietnameseName").setValue("");
        dijit.byId("searchForm.publishAgencyName").setValue("");
        dijit.byId("searchForm.publishDateFrom").setValue("");
        dijit.byId("searchForm.publishDateTo").setValue("");
        dijit.byId("searchForm.effectiveDateFrom").setValue("");
        dijit.byId("searchForm.effectiveDateTo").setValue("");
        dijit.byId("searchForm.expireDateFrom").setValue("");
        dijit.byId("searchForm.expireDateTo").setValue("");
        page.search();
    };


    page.insert = function() {
        page.clearInsertForm();
        dlg.show();
    };

    page.clearInsertForm = function() {
        dijit.byId("createForm.technicalStandardId").setValue("");
        dijit.byId("createForm.standardCode").setValue("");
        dijit.byId("createForm.englishName").setValue("");
        dijit.byId("createForm.vietnameseName").setValue("");
        dijit.byId("createForm.summary").setValue("");
        dijit.byId("createForm.applicationObject").setValue("");
        dijit.byId("createForm.publishDate").setValue("");
        dijit.byId("createForm.effectiveDate").setValue("");
        dijit.byId("createForm.expireDate").setValue("");
//        alert(dijit.byId("createForm.expireDate").getValue(""));
        if (dijit.byId("searchForm.standardType").getValue() == 0) {
            dijit.byId("createForm.scope").setValue("Toàn quốc");
        } else {
            dijit.byId("createForm.scope").setValue("Toàn địa phương");
        }
        ;
        dijit.byId("createForm.status").setValue(0);
        dijit.byId("createForm.publishAgencyName").setValue("");
        clearAttFile("createForm.upload");
    };
    //delete
    page.deleteItem = function() {
        if (!dijit.byId("Grid").vtIsChecked()) {
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
        }
        else {
            msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
        }
    };

    page.deleteItemExecute = function() {
        var content = dijit.byId("Grid").vtGetCheckedDataForPost("lstItemOnGrid");
        sd.connector.post("technicalStandardAction!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
    };
    page.returnMessageDelete = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
        page.search();
    };
    //!delete
    //published
    page.publishedItem = function() {
        if (!dijit.byId("Grid").vtIsChecked()) {
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
        }
        else {
            msg.confirm('<sd:Property>Bạn có chắc chắn Công bố qui chuẩn kĩ thuật</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.publishedItemExecute);
        }
    };

    page.publishedItemExecute = function() {
        var content = dijit.byId("Grid").vtGetCheckedDataForPost("lstItemPublished");
        sd.connector.post("technicalStandardAction!onPublished.do?" + token.getTokenParamString(), null, null, content, page.returnMessagePublished);
    };
    page.returnMessagePublished = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
        page.search();
    };
    //!published
    //terminate
    page.terminateItem = function() {
        if (!dijit.byId("Grid").vtIsChecked()) {
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
        }
        else {
            msg.confirm('<sd:Property>Bạn có chắc chắn Chấm dứt hiệu lực qui chuẩn kĩ thuật</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.terminateItemExecute);
        }
    };

    page.terminateItemExecute = function() {
        var content = dijit.byId("Grid").vtGetCheckedDataForPost("lstItemTerminate");
        sd.connector.post("technicalStandardAction!onTerminate.do?" + token.getTokenParamString(), null, null, content, page.returnMessageTerminate);
    };
    page.returnMessageTerminate = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
        page.search();
    };
    //!terminate

    page.showSearchPanel = function() {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
        dijit.byId("searchForm.standardCode").focus();
    };
    page.search();
</script>
