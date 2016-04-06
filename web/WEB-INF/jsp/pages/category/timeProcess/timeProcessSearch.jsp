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

    <sd:TitlePane key="search.searchCondition" id="TimeProcessTitle">
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
                        <sd:Label key="Ngày lễ từ ngày"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.timeProcessDateFrom"
                                       key=""
                                       name="searchForm.timeProcessDateFrom" format="dd/MM/yyyy"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.timeProcessDateTo"
                                       key=""
                                       name="searchForm.timeProcessDateTo" format="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Loại ngày"/>
                    </td>
                    <td>
                        <sd:SelectBox id="searchForm.isDayOff" name="searchForm.isDayOff" key=""  cssStyle="width:100%" >
                            <sd:Option value='-1' selected="true">-- Chọn --</sd:Option>
                            <sd:Option value='0'>Ngày nghỉ</sd:Option>
                            <sd:Option value='1'>Ngày làm</sd:Option>
                        </sd:SelectBox>
                    </td>
                    <td></td>
                    <td></td>
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

<sd:TitlePane key="category.lstTimeProcess"
              id="TimeProcessList" >
    <sx:ResultMessage id="resultDeleteMessage" />
    <table width="100%">
        <tr>
            <td>
                <script>
                    page.getNo = function(index) {
                        return dijit.byId("TimeProcessGrid").currentRow + index + 1;
                    };
                    page.getIndex = function(index) {
                        return index + 1;
                    };
                    page.formatEdit = function(inData) {
                        var row = inData - 1;
                        var item = dijit.byId("TimeProcessGrid").getItem(row);
                        var url = "";
                        var today = new Date();
                        if (item != null) {
                            if (page.convertStringToDate(item.timeProcessDate) > today) {
                                url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                        title='<sd:Property>category.edit</sd:Property>' \n\
                        onClick='page.showEditPopup(" + row + ");' /></div>";
                            }
                        }
                        return url;
                    };
                    page.formatStatus = function(inData) {
                        var item = dijit.byId("TimeProcessGrid").getItem(inData - 1);
                        var url = "";
                        if (item != null) {
                            var isDayOff = parseInt(item.isDayOff);
                            switch (isDayOff) {
                                case 0:
                                    url = "Ngày nghỉ";
                                    break;
                                case 1:
                                    url = "Ngày làm";
                                    break;
                                default:
                                    url = "Ngày nghỉ";
                            }
                        }
                        return url;
                    };
                    page.formatIsActive = function(inData) {
                        var item = dijit.byId("TimeProcessGrid").getItem(inData - 1);
                        var url = "";
                        if (item != null) {
                            var isDayOff = parseInt(item.isActive);
                            switch (isDayOff) {
                                case 0:
                                    url = "Không hoạt động";
                                    break;
                                case 1:
                                    url = "Hoạt động";
                                    break;
                                default:
                                    url = "Không hoạt động";
                            }
                        }
                        return url;
                    };
                    page.disabledCheckbox = function(inData) {
                        var item = dijit.byId("TimeProcessGrid").getItem(inData);
                        var check = true;
                        var today = new Date();
                        if (item != null) {
                            if (page.convertStringToDate(item.timeProcessDate) > today) {
                                check = false;
                            }
                        }
                        return check;
                    };
                    </script>
                    <div id="" style="width:100%;">
                    <sd:DataGrid id="TimeProcessGrid"
                                 getDataUrl=""
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="STT" get="page.getNo" width="2%" styles="text-align:center;"/>
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="1%" cellStyles="text-align:center;"  setDisabled="page.disabledCheckbox"/>
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatEdit" get="page.getIndex"
                                           width="1%"  headerStyles="text-align:center;"  />
                        <sd:ColumnDataGrid  key="Tên" field="name"
                                            width="20%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Ngày" field="timeProcessDate" format="dd/MM/yyyy" type="date"
                                            width="3%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Loại" get="page.getNo" formatter="page.formatStatus" cellStyles="text-align:center;"
                                            width="3%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Trạng Thái" get="page.getNo" formatter="page.formatIsActive" cellStyles="text-align:center;"
                                            width="3%"  headerStyles="text-align:center;" />
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

<sd:Dialog  id="dlgTimeProcess" height="auto" width="550px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="timeProcessCreateDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>

<script>
    var insertDialog = true;
    var grid = dijit.byId("TimeProcessGrid");
    var dlgTimeProcess = dijit.byId("dlgTimeProcess");
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
        insertDialog = false;
        dijit.byId("createForm.timeProcessDateFrom").attr("disabled", true);
        dijit.byId("createForm.timeProcessDateTo").attr("disabled", true);
        dijit.byId("createForm.name").setValue(item.name);
        if (item.timeProcessDate != "") {
            dijit.byId("createForm.timeProcessDateFrom").setValue(page.convertStringToDate(item.timeProcessDate));
            dijit.byId("createForm.timeProcessDateTo").setValue(page.convertStringToDate(item.timeProcessDate));
        }
        dijit.byId("createForm.isDayOff").setValue(item.isDayOff);
        dijit.byId("createForm.timeProcessId").setValue(item.timeProcessId);
        dijit.byId("createForm.isActive").setValue(item.isActive);
        var description = item.description;
        document.getElementById("createForm.description").innerHTML = escapeHtml_(description);
    };
    page.showEditPopup = function(row) {
        var item = dijit.byId("TimeProcessGrid").getItem(row);
        page.setItem(item);
        dlgTimeProcess.show();
    };


    page.search = function() {
        grid.vtReload('timeProcessAction!onSearch.do?', "searchForm", null, null);
    };
    page.reset = function() {
        dijit.byId("searchForm.TimeProcess").setValue("");
        dijit.byId("searchForm.categoryName").setValue("");
        grid.vtReload('timeProcessAction!onSearch.do?', "searchForm", null, null);
    };
    page.insert = function() {
        page.clearInsertForm();
        dlgTimeProcess.show();
    };
    page.clearInsertForm = function() {
        insertDialog = true;
        dijit.byId("createForm.timeProcessDateFrom").attr("disabled", false);
        dijit.byId("createForm.timeProcessDateTo").attr("disabled", false);
        dijit.byId("createForm.name").setValue("");
        dijit.byId("createForm.timeProcessDateFrom").setValue("");
        dijit.byId("createForm.timeProcessDateTo").setValue("");
        dijit.byId("createForm.description").setValue("");
        dijit.byId("createForm.isDayOff").setValue(-1);
        dijit.byId("createForm.timeProcessId").setValue("");
        isActive = dijit.byId("createForm.isActive").setValue(-1);
    };

    page.deleteItemExecute = function() {
        var content = grid.vtGetCheckedDataForPost("lstItemOnGrid");
        sd.connector.post("timeProcessAction!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
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
    page.search();
    page.deleteItem = function() {
        if (!dijit.byId("TimeProcessGrid").vtIsChecked()) {
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
                    } else {
                        msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
                                }
                            };
</script>
