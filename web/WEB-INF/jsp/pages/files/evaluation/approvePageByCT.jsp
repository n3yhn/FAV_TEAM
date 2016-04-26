<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<style type="text/css">
    .box:hover{
        color: #ffff00;
        cursor: pointer;
    }
</style>

<script type="text/javascript">

    page.getNo = function (index) {
        return dijit.byId("filesGrid").currentRow + index + 1;
    };

    page.getIndex = function (index) {
        return index + 1;
    };

    page.formatEdit = function (inData) {
        var url = "<div class='box' onclick='page.viewFile(" + inData + ");' />" + inData + "</div>";
        return url;
    };

    page.formatAction = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var row = inData - 1;
        if (item != null) {
            var status = parseInt(item.status);
            var url = "<div style='text-align:center;cursor:pointer;display:inline'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + item.fileId + ");' />";
            switch (status) {
                case 29:
                    url += " | <img src='share/images/signature.png' width='20px' height='20px' title='Phê duyệt hồ sơ' onClick='page.showApproveForm(" + row + ");' />";
                    url += " | <img src='share/images/icons/dept.png' width='20px' height='20px' title='Phân công Phê duyệt hồ sơ' onClick='page.showAssignApproveForm(" + row + ");' />";
                    break;
                default:
                    ;
            }
        }
        url += "</div>";
        return url;
    };

    page.getStatusName = function (status) {
        switch (status) {
            case 1:
                url = "Mới nộp";
                break;
            case 2:
                url = "Đã được đề xuất xử lý";
                break;
            case 3:
                url = "Đã phân công xử lý";
                break;
            case 4:
                url = "Đã thẩm định";
                break;
            case 5:
                url = "Đã xem xét kết quả";
                break;
            case 6:
                url = "Đã phê duyệt kết quả";
                break;
            case 7:
                url = "Chuyên viên KL: SĐBS";
                break;
            case 8:
                url = "Đã trả lại để thẩm định lại";
                break;
            case 9:
                url = "Đã trả lại để xem xét lại";
                break;
            case 19:
                url = "Đã xem xét SĐBS";
                break;
            case 24:
                url = "Đã xem xét đối chiếu";
                break;
            case 29:
                url = "Đã trình cục trưởng xem xét hồ sơ";
                break;
            default:
                url = "Mới tạo";
        }
        return url;
    };

    page.formatStatus = function (inData) {
        var row = inData - 1;
        var item = dijit.byId("filesGrid").getItem(row);
        var url = "";
        if (item != null) {
            var status = parseInt(item.status);
            url = page.getStatusName(status);
        }
        url = "<a href='#' onClick='showEvaludateResult(" + row + ")'>" + url + "</a>";
        return url;
    };

    // enter key
    page.searchDefault = function (evt) {
        var dk = dojo.keys;
        switch (evt.keyCode) {
            case dk.ENTER:
                page.search();
                break;
        }
    };

    dojo.connect(dojo.byId("searchForm"), "onkeypress", page.searchDefault);

</script>

<div id="token">
    <s:token id="tokenId"/>
</div>

<div id="searchDiv">
    <sd:TitlePane key="search.searchCondition" id="categoryTitle">
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
                        <sd:Label key="Mã hồ sơ"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.fileCode"
                                    key=""
                                    name="searchForm.fileCode" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Số bản công bố"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.announcementNo"
                                    key=""
                                    name="searchForm.announcementNo" maxlength="250"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Tên tổ chức, cá nhân"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.businessName"
                                    key=""
                                    name="searchForm.businessName" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Số đăng ký kinh doanh/số CMT"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.businessLicence"
                                    key=""
                                    name="searchForm.businessLicence" maxlength="250"/>
                    </td>

                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Địa chỉ"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.businessAddress"
                                    key=""
                                    name="searchForm.businessAddress" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Tên sản phẩm"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.productName"
                                    key=""
                                    name="searchForm.productName" maxlength="250"/>
                    </td>

                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Tên nước xuất xứ (nhập khẩu)"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.nationName"
                                    key=""
                                    name="searchForm.nationName" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Tên nhà sản xuất"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.manufactureName"
                                    key=""
                                    name="searchForm.manufactureName" maxlength="250"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Địa chỉ sản xuất"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.manufactureAddress"
                                    key=""
                                    name="searchForm.manufactureAddress" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Số ký hiệu QCKT/quy định ATTP"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.matchingTarget"
                                    key=""
                                    name="searchForm.matchingTarget" maxlength="250"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Loại hồ sơ"/>
                    </td>
                    <td>
                        <sd:SelectBox cssStyle="width:100%"
                                      id="searchForm.fileType"
                                      key="" data="lstFileType" valueField="procedureId" labelField="name"
                                      name="searchForm.fileType" >
                        </sd:SelectBox>
                        <sd:TextBox key="" id="searchForm.status" name="searchForm.status" cssStyle="display:none"/>
                    </td>
                    <td colspan="2"></td>
                </tr>
                <tr style="text-align: center">
                    <td colspan="4">
                        <sx:ButtonSearch onclick="page.search();" />
                        <sd:Button key="" onclick="page.reset();" > 
                            <img src="share/images/icons/reset.png" height="14" width="18"/>
                            <span style="font-size:12px">Hủy</span>
                        </sd:Button>
                        <sd:TextBox cssStyle="display:none" trim="true"
                                    id="searchForm.flagSavePaging"
                                    name="searchForm.flagSavePaging"
                                    key=""
                                    value="1"
                                    />
                    </td>
                </tr>

            </table>
        </form>
    </sd:TitlePane>

    <sd:TitlePane key="Danh sách hồ sơ"
                  id="businessList" >
        <sx:ResultMessage id="resultDeleteMessage" />
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
                            <sd:ColumnDataGrid key="STT" get="page.getNo" width="3%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Chức năng" formatter="page.formatAction" get="page.getIndex"
                                                width="3%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                            <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode" width="10%"  headerStyles="text-align:center;" />
                            <%--<sd:ColumnDataGrid  key="Số bản công bố" field="announcementNo" cellStyles="text-align:left;"
                                                width="10%"  headerStyles="text-align:center;" />--%>
                            <sd:ColumnDataGrid  key="Loại hồ sơ" field="fileTypeName"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên tổ chức, cá nhân" field="businessName" cellStyles="text-align:left;"
                                                width="25%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName" cellStyles="text-align:left;"
                                                width="10%"  headerStyles="text-align:center;" />
                            <%--<sd:ColumnDataGrid  key="Số quy chuẩn phù hợp" field="matchingTarget" cellStyles="text-align:left;"
                                                width="10%"  headerStyles="text-align:center;" />--%>
                            <sd:ColumnDataGrid  key="Ngày đến" field="modifyDate" format="dd/MM/yyyy" type="date"
                                                width="7%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />   
                            <sd:ColumnDataGrid  key="Ngày hẹn trả" field="deadlineApprove" format="dd/MM/yyyy" type="date"
                                                width="7%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />                                    
                            <sd:ColumnDataGrid  key="Kết quả xem xét" formatter="page.formatStatus" get="page.getIndex"
                                                width="10%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                            <sd:ColumnDataGrid  key="Cán bộ xử lý chính" field="nameStaffProcess"  cellStyles="text-align:center;"
                                                width="7%"  headerStyles="text-align:center;" />
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>

<%--<sd:Dialog  id="approveDlg" height="auto" width="1200px"
            key="Nhập kết quả phê duyệt" showFullscreenButton="false"
            >
    <jsp:include page="approveFormByCT.jsp" flush="false"></jsp:include>
</sd:Dialog>--%>
<sd:Dialog  id="approveDlg" height="auto" width="1200px"
            key="Nhập kết quả phê duyệt" showFullscreenButton="false"
            >
    <jsp:include page="approveFormByCTPlugin.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="assignApproveDlgByCT" height="auto" width="600px"
            key="Nhập kết quả phê duyệt" showFullscreenButton="false"
            >
    <jsp:include page="assignApproveDlgByCT.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="evaluateViewDlg" height="auto" width="600px"
            key="Kết quả xem xét" showFullscreenButton="false"
            >
    <jsp:include page="reviewFormView.jsp" flush="false"></jsp:include>
</sd:Dialog>

<div id="viewDiv"></div>

<script type="text/javascript">

    backPage = function () {
        //doGoToMenu(g_latestClickedMenu);
        document.getElementById("searchDiv").style.display = "";
        document.getElementById("viewDiv").style.display = "none";
    };

    page.search = function () {
        dijit.byId("filesGrid").vtReload('filesAction!onSearchFileToApproveByCT.do?' + token.getTokenParamString(), "searchForm",null,page.afterSearch);
    };
    
    page.afterSearch = function () {
        dijit.byId("searchForm.flagSavePaging").setValue("0");
    }

    page.reset = function () {
        dijit.byId('searchForm.fileCode').attr('value', '');
        dijit.byId('searchForm.announcementNo').attr('value', '');
        dijit.byId('searchForm.businessName').attr('value', '');
        dijit.byId('searchForm.businessLicence').attr('value', '');
        dijit.byId('searchForm.businessAddress').attr('value', '');
        dijit.byId('searchForm.productName').attr('value', '');
        dijit.byId('searchForm.nationName').attr('value', '');
        dijit.byId('searchForm.manufactureName').attr('value', '');
        dijit.byId('searchForm.manufactureAddress').attr('value', '');
        dijit.byId('searchForm.matchingTarget').attr('value', '');
        dijit.byId('searchForm.fileType').attr('value', '-1');
    };

    afterLoadForm = function (data) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("viewDiv").style.display = "";
    };

    page.showViewFile = function (fileId) {
        doGoToMenu("filesAction!toFileDlgView.do?fileId=" + fileId + "&viewType=2" + "&backPage=9");
    };

    showEvaludateResult = function (row) {
        var file = dijit.byId("filesGrid").getItem(row);
        var statusName = page.getStatusName(parseInt(file.status));
        document.getElementById("evaluateFormView.status").innerHTML = statusName;
        document.getElementById("evaluateFormView.staffRequest").innerHTML = escapeHtml_(file.staffRequest);
        document.getElementById("evaluateFormView.leaderStaffRequest").innerHTML = escapeHtml_(file.leaderStaffRequest);
        page.replaceBrTblReviewFormView();
        dijit.byId("evaluateViewDlg").show();
    };

    page.showApproveForm = function (row) {
        var file = dijit.byId("filesGrid").getItem(row);
        dijit.byId("approveFormByCT.fileId").setValue(file.fileId);
//        document.getElementById("approveFormByCT.status").innerHTML = statusName;
        document.getElementById("approveFormByCT.businessName").innerHTML = escapeHtml_(file.businessName);
        document.getElementById("approveFormByCT.productName").innerHTML = escapeHtml_(file.productName);
//        document.getElementById("approveFormByCT.staffRequest").innerHTML = escapeHtml_(file.staffRequest);
        document.getElementById("approveFormByCT.leaderRequest").innerHTML = escapeHtml_(file.leaderRequest);
        dijit.byId("approveFormByCT.leaderRequest").setValue("");
        document.getElementById("trWait").style.display = "none";
        dijit.byId("approveDlg").show();
        page.replaceBrTblApproveFormByCT();//6969

        document.getElementById("trWaitViewFile").style.display = '';
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + file.fileId + "&createForm.viewType=2&viewTypeDialog=1", "divViewFile", null, null, afterShowApproveViewFile);
    };

    afterShowApproveViewFile = function () {
        document.getElementById("trWaitViewFile").style.display = 'none';
    }

    page.showAssignApproveForm = function (row) {
        var file = dijit.byId("filesGrid").getItem(row);
        dijit.byId("assignApproveDlgFormByCT.fileId").setValue(file.fileId);
        document.getElementById("assignApproveDlgFormByCT.businessName").innerHTML = escapeHtml_(file.businessName);
        document.getElementById("assignApproveDlgFormByCT.productName").innerHTML = escapeHtml_(file.productName);
        dijit.byId("assignApproveDlgByCT").show();
    };
    page.showSearchPanel = function () {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };

    page.downloadWord = function (fileId) {
        document.location = "exportWord!onExportPaper.do?fileId=" + fileId;
    };

    page.search();

</script>
