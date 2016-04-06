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

    <sd:TitlePane key="search.searchCondition" id="categoryTypeTitle">
        <form id="searchForm">
            <table width="100%;" cellpadding="0px" cellspacing="5px">
                <tr>
                    <td width="10%"></td>
                    <td width="10%"></td>
                    <td width="35%"></td>
                    <td width="10%"></td>
                    <td width="35%"></td>
                </tr>
                <tr><td></td>
                    <td align="right">
                        <sd:Label key="Số hiệu:"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.standardCode"
                                    key=""
                                    name="searchForm.standardCode" 
                                    maxlength="20"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Tên tiêu chuẩn:"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.vietnameseName"
                                    key=""
                                    name="searchForm.vietnameseName" maxlength="255"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td align="right">
                        <sd:Label key="Loại tiêu chuẩn:"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.standardType"
                                    key=""
                                    name="searchForm.standardType" maxlength="20"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Phạm vi:"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.scope"
                                    key=""
                                    name="searchForm.scope" maxlength="255"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td align="right">
                        <sd:Label key="Đơn vị ban hành:"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" 
                                    trim="true"
                                    id="searchForm.publishAgencyName"
                                    key=""
                                    name="searchForm.publishAgencyName" 
                                    maxlength="20"/>
                    </td>
                    <td align="right">

                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Ngày ban hành:"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Từ ngày:"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.publishDateFrom"
                                       key=""
                                       name="searchForm.publishDateFrom" format="dd/MM/yyyy"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày:"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.publishDateTo"
                                       key=""
                                       name="searchForm.publishDateTo" format="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Ngày hiệu lực:"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Từ ngày:"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.effectiveDateFrom"
                                       key=""
                                       name="searchForm.effectiveDateFrom" format="dd/MM/yyyy"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày:"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.effectiveDateTo"
                                       key=""
                                       name="searchForm.effectiveDateTo" format="dd/MM/yyyy"/>
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
                                       name="searchForm.expireDateFrom" format="dd/MM/yyyy"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày:"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.expireDateTo"
                                       key=""
                                       name="searchForm.expireDateTo" format="dd/MM/yyyy"/>
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

<sd:TitlePane key="Quản lí tiêu chuẩn VN về ATVSTP"
              id="vietnameseStandardList" >
    <sx:ResultMessage id="resultDeleteMessage" />
    <table width="100%">
        <tr>
            <td>
                <script>
                    page.getNo = function(index) {
                        return dijit.byId("Grid").currentRow + index + 1;
                    }

                    page.getIndex = function(index) {
                        return index + 1;
                    }

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
                    }
                    </script>
                    <div id="" style="width:100%;">
                    <sd:DataGrid id="Grid"
                                 getDataUrl=""
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="STT" get="page.getNo" width="5%"  styles="text-align:center;" />
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatEdit" get="page.getIndex"
                                           width="5%"  headerStyles="text-align:center;"  />
                        <sd:ColumnDataGrid  key="Số hiệu" field="standardCode"
                                            width="10%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Tên tiêu chuẩn" field="vietnameseName"
                                            width="20%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Loại tiêu chuẩn" field="standardType"
                                            width="15%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Đơn vị ban hành" field="publishAgencyName"
                                            width="20%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Ngày ban hành" field="publishDate"
                                            width="15%"  headerStyles="text-align:center;" type="date" format="dd/MM/yyyy" />
                        <sd:ColumnDataGrid  key="Ngày hiệu lực" field="effectiveDate" type="date" format="dd/MM/yyyy"
                                            width="15%"  headerStyles="text-align:center;" />
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

<sd:Dialog  id="dlg" height="auto" width="800px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="vietnameseStandardCreateDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>
<script>
    var grid = dijit.byId("Grid");
    var dlg = dijit.byId("dlg");

    page.showEditPopup = function(row) {
        var item = dijit.byId("Grid").getItem(row);
        page.setItem(item);
        dlg.show();
    }

    page.setItem = function(item) {
        dijit.byId("createForm.vietnameseStandardId").setValue(item.vietnameseStandardId);
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
        dijit.byId("createForm.isActive").setValue(item.isActive);
        dijit.byId("publishAgencyName").setValue(item.publishAgencyName);
        dijit.byId("createForm.publishAgencyId").setValue(item.publishAgencyId);
        clearAttFile("createForm.upload");
        getAttacthFile(item.vietnameseStandardId, 24, "createForm.upload");
        //dijit.byId("createForm.standardProduct").setValue(item.standardProduct);
    }

    page.search = function() {
        grid.vtReload('vietnameseStandardAction!onSearch.do?', "searchForm", null, null);
    }

    page.reset = function() {
        dijit.byId("searchForm.standardCode").setValue("");
        dijit.byId("searchForm.vietnameseName").setValue("");
        dijit.byId("searchForm.standardType").setValue("");
        dijit.byId("searchForm.scope").setValue("");
        dijit.byId("searchForm.publishAgencyName").setValue("");
        dijit.byId("searchForm.publishDateFrom").setValue("");
        dijit.byId("searchForm.publishDateTo").setValue("");
        dijit.byId("searchForm.effectiveDateFrom").setValue("");
        dijit.byId("searchForm.effectiveDateTo").setValue("");
        dijit.byId("searchForm.expireDateFrom").setValue("");
        dijit.byId("searchForm.expireDateTo").setValue("");
        page.search();
    }


    page.insert = function() {
        page.clearInsertForm();
        dlg.show();
    }

    page.clearInsertForm = function() {
        dijit.byId("createForm.vietnameseStandardId").setValue("");
        dijit.byId("createForm.standardCode").setValue("");
        dijit.byId("createForm.englishName").setValue("");
        dijit.byId("createForm.vietnameseName").setValue("");
        dijit.byId("createForm.summary").setValue("");
        dijit.byId("createForm.applicationObject").setValue("");
        dijit.byId("createForm.publishDate").setValue("");
        dijit.byId("createForm.effectiveDate").setValue("");
        dijit.byId("createForm.expireDate").setValue("");
        dijit.byId("createForm.scope").setValue("");
        dijit.byId("createForm.standardType").setValue(0);
        dijit.byId("createForm.isActive").setValue(0);
        dijit.byId("publishAgencyName").setValue("");
        dijit.byId("createForm.publishAgencyId").setValue("");
        clearAttFile("createForm.upload");
        //dijit.byId("createForm.standardProduct").setValue("");
    }
    //todo
    page.deleteItem = function() {
        if (!dijit.byId("Grid").vtIsChecked()) {
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
        }
        else {
            msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
        }
    }
//todo
    page.deleteItemExecute = function() {
        var content = grid.vtGetCheckedDataForPost("lstItemOnGrid");
        sd.connector.post("vietnameseStandardAction!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
    }

    page.returnMessageDelete = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
        page.search();
    }

    page.showSearchPanel = function() {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
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
