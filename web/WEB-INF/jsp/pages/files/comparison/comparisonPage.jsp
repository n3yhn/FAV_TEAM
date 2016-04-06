<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--
<jsp:include page="../util/util_js.jsp"/>
<jsp:include page="../common/commonJavascript.jsp"/>
--%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<style type="text/css">
    .box:hover{
        color: #ffff00;
        cursor: pointer;
    }
</style>
<script>
    page.getNo = function (index) {
        return dijit.byId("filesGrid").currentRow + index + 1;
    };

    page.getIndex = function (index) {
        return index + 1;
    };

    page.formatView = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var row = inData - 1;
        var url = "";
        if (item != null) {
            var status = parseInt(item.status);
            var isFee = parseInt(item.isFee);
            var isSignPdf = parseInt(item.isSignPdf);
            url += "<div style='text-align:center;cursor:pointer;'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + item.fileId + ");' />";
            switch (status) {
                case 23:
                    url += " | <img src='share/images/alertXacnhan.png' width='17px' height='17px' title='Đối chiếu hồ sơ' onClick='page.showComparisonDlg(" + row + ");' />";
                    break;
                case 16:
                    url += " | <img src='share/images/alertXacnhan.png' width='17px' height='17px' title='Đối chiếu hồ sơ' onClick='page.showComparisonDlg(" + row + ");' />";
                    break;
                case 6:
                    if (isSignPdf == 2)
                    {
                        if (isFee == 1) {
                           url += " | <img src='share/images/alertXacnhan.png' width='17px' height='17px' title='Thông báo đối chiếu hồ sơ' onClick='page.alertComparison(" + row + ");' />";
                        }
                    }                    
                    break;
            }
            url += "</div>";
        }
        return url;
    };

    page.formatStatus = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        if (item != null) {
            var status = item.status;
            if (status == 6)
                return 'Đã phê duyệt';
//            else
//            if (status == 2)
//                return 'Đề xuất';
//            else
//            if (status == 15)
//                return 'Đã đối chiếu';
            if (status == 16)
                return 'Đã đối chiếu, có sai lệch';
            else {
                return 'Đang xử lý';
            }
        }
        return '';
    };

    page.formatDate = function (date) {
        if (date != null)
            return
    };

    // enter key
    page.searchDefault = function (evt) {
        var dk = dojo.keys;
        switch (evt.keyCode) {
            case dk.ENTER:
                page.search();
                break;
        }
    }

    dojo.connect(dojo.byId("searchForm"), "onkeypress", page.searchDefault);
</script>

<div id="token">
    <s:token id="tokenId"/>
</div>
<div id="searchDiv">
    <sd:TitlePane key="search.searchCondition" id="categoryTitle">
        <form id="searchForm" name="searchForm">
            <s:hidden id="deptId" name="searchForm.deptId"/>
            <table width="100%;" cellpadding="0px" cellspacing="5px">
                <tr>
                    <td width="20%"></td>
                    <td width="30%"></td>
                    <td width="20%"></td>
                    <td width="30%"></td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Mã hồ sơ"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.fileCode"
                                    key=""
                                    name="searchForm.fileCode" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Tên loại hồ sơ"/>
                    </td>
                    <td>
                        <sd:SelectBox cssStyle="width:100%"
                                      id="searchForm.fileType"
                                      key="" data="lstFileType" valueField="procedureId" labelField="name"
                                      name="searchForm.fileType" >
                        </sd:SelectBox>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Gửi Từ ngày"/>
                    </td>
                    <td>
                        <sd:DatePicker cssStyle="width:100%"
                                       id="searchForm.sendDateFrom"
                                       key="" format="dd/MM/yyyy"
                                       name="searchForm.sendDateFrom"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày"/>
                    </td>
                    <td>
                        <sd:DatePicker cssStyle="width:100%"
                                       id="searchForm.sendDateTo"
                                       key="" format="dd/MM/yyyy"
                                       name="searchForm.sendDateTo"/>
                        <sd:TextBox key="" id="searchForm.status" name="searchForm.status" cssStyle="display:none"/>
                    </td>
                </tr>
                <tr style="text-align: center">
                    <td colspan="4">
                        <sx:ButtonSearch onclick="page.search();" />
                        <sd:Button key="" onclick="page.reset();" > 
                            <img src="share/images/icons/reset.png" height="14" width="18">
                            <span style="font-size:12px">Hủy<%--<sd:Property>btnCancel</sd:Property> --%></span>
                        </sd:Button>
                    </td>
                </tr>

            </table>
        </form>
    </sd:TitlePane>

    <sd:TitlePane key="Danh sách hồ sơ"
                  id="businessList" >    
        <table width="100%">
            <tr>
                <td>
                    <div style="width:100%;">
                        <sd:DataGrid id="filesGrid"
                                     getDataUrl=""
                                     rowSelector="0%"
                                     style="width:auto;height:auto"
                                     rowsPerPage="20"
                                     serverPaging="true"
                                     clientSort="false">
                            <sd:ColumnDataGrid key="category.No" get="page.getNo" width="5%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="Thao tác" headerStyles="text-align:center;" width="5%" cellStyles="text-align:center;"
                                               formatter="page.formatView" get="page.getIndex"/>                                                                      
                            <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode" width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Loại hồ sơ" field="fileTypeName"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Trạng thái hồ sơ" field="status" get="page.getIndex" formatter="page.formatStatus" cellStyles="text-align:center;"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên tổ chức, cá nhân" field="businessName"
                                                width="25%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày nộp" field="sendDate" format="dd/MM/yyyy" type="date"
                                                width="15%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>
<sd:Dialog  id="comparisonDlg" height="auto" width="1200px"
            key="Đối chiếu hồ sơ" showFullscreenButton="false"
            >
    <jsp:include page="../../files/comparison/comparisonDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="alertComparisonDlg" height="auto" width="1000px" key="Thông báo đối chiếu hồ sơ gửi doanh nghiệp">
    <jsp:include page="../../files/comparison/alertComparisonDlg.jsp" flush="false"/>
</sd:Dialog>
<div id="createDiv"></div>

<script type="text/javascript">
    var workingFileId;
    backPage = function () {
        doGoToMenu(g_latestClickedMenu);
    };

    afterLoadForm = function (data) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
    };

    page.showViewFile = function (fileId) {
        doGoToMenu("filesAction!toFileDlgView.do?fileId=" + fileId + "&viewType=1" + "&backPage=4");
    };

    page.showNextNode = function (fileId) {
        page.loadFlow(fileId);
    };

    page.showAssignPopup = function (fileId) {
        dijit.byId("assignDlg").show();
        if (page.showAssignDlg)
            page.showAssignDlg(fileId);
    };
    page.search = function () {
        dijit.byId("filesGrid").vtReload('filesAction!onsearchLookupFilesByClerical.do?', "searchForm");
    };

    page.reset = function () {
        dijit.byId('searchForm.fileCode').attr('value', '');
        dijit.byId('searchForm.fileType').attr('value', '-1');
        dijit.byId('searchForm.sendDateFrom').attr('value', null);
        dijit.byId('searchForm.sendDateTo').attr('value', null);
    };


    page.insert = function () {
        page.clearInsertForm();
        dijit.byId("createDlg").show();
    };

    page.showEditPopup = function (row) {
        var item = dijit.byId("filesGrid").getItem(row);
        page.setItem(item);
        dijit.byId("createDlg").show();
    };

    page.returnMessageDelete = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
        page.search();
    };

    page.showSearchPanel = function () {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };
    page.showComparisonDlg = function (row) {
        var item = dijit.byId("filesGrid").getItem(row);
        dijit.byId("comparisonForm.fileId").setValue(item.fileId);
        document.getElementById("comparisonForm.lastContent").innerHTML = item.comparisonContent;
        workingFileId = item.fileId;
        page.rplBrTblComparisonDlg();
        dijit.byId("comparisonDlg").show();
        
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + item.fileId + "&createForm.viewType=1&viewTypeDialog=1", "divViewFile", null, null, afterLoadViewFile);
    };
    
    afterLoadViewFile = function() {
        document.getElementById("trWaitViewFile").style.display = 'none';
    };
    
    page.alertComparison = function (row) {//thong bao doi chieu ho so
        var item = dijit.byId("filesGrid").getItem(row);
        dijit.byId("alertComparisonForm.fileId").setValue(item.fileId);
        sd.connector.post("filesAction!getLastRequestComment.do?objectId=" + item.fileId + "&objectType=TBDC", null, null, null, afterGetLastRequestComment);
    };
    afterGetLastRequestComment = function (data) {//lay comment sau cung
        var obj = dojo.fromJson(data);
        document.getElementById("alertComparisonForm.requestCommentForm.lastContent").innerHTML = obj.customInfo[0];
        var lstFile = obj.customInfo[1];
        lstFile = lstFile.replace(/nl/g, "\n");
        dijit.byId("alertComparisonForm.content").setValue(lstFile);
        page.rplBrTblAlertComparisonForm();
        dijit.byId("alertComparisonDlg").show();
    };
    page.search();
</script>
