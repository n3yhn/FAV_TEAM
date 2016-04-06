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

    page.getNo = function(index) {
        return dijit.byId("filesGrid").currentRow + index + 1;
    };

    page.getIndex = function(index) {
        return index + 1;
    };

    page.getRow = function(inRow) {
        return inRow;
    };

    page.formatEdit = function(inData) {
        var url = "<div class='box' onclick='page.viewFile(" + inData + ");' />" + inData + "</div>";

        return url;
    };

    page.formatAction = function(inData) {
        var item = dijit.byId("filesGrid").getItem(inData);
        var row = inData;
        if (item != null) {
            var url = "<div style='text-align:center;cursor:pointer;display:inline'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + item.fileId + ");' />";
            if (item.status == 11) {
                url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Xem chi tiết giấy tiếp nhận công bố hợp quy' onClick='page.showSignDlg(" + row + ");' />";
            }
            if (item.status == 12) {
                url += " | <img src='share/images/Document.png' width='17px' height='17px' title='Xuất file word' onClick='page.downloadWord(" + item.fileId + ");' />";
            }
        }
        url += "</div>"
        return url;
    };

    page.getStatusName = function(status) {
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
            case 10:
                url = "Đã lập giấy";
                break;
            case 11:
                url = "Đã trình ký giấy";
                break;
            case 12:
                url = "Đã ký giấy";
                break;
            case 13:
                url = "Đã từ chối ký giấy";
                break;
            default:
                url = status;
        }
        return url;
    };

    page.formatStatus = function(inData) {
        var row = inData;
        var item = dijit.byId("filesGrid").getItem(row);
        var url = "";
        if (item != null) {
            var status = parseInt(item.status);
            url = page.getStatusName(status);
            if (item.status != 13)
            {
                url = "<a href='#' onClick='showSigningResult(" + row + ")'>" + url + "</a>"
            } else {
                url = "<a href='#' onClick='showRejectResult(" + row + ")'>" + url + "</a>"
            }
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
                    </td>
                </tr>

            </table>
        </form>
    </sd:TitlePane>

    <sd:TitlePane key="Danh sách hồ sơ đã phê duyệt"
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
                            <sd:ColumnDataGrid key="STT" get="page.getNo" width="5%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;" />                        
                            <sd:ColumnDataGrid  key="Số bản công bố" field="announcementNo" cellStyles="text-align:left;"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Loại hồ sơ" field="fileTypeName"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên tổ chức, cá nhân" field="businessName" cellStyles="text-align:left;"
                                                width="25%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName" cellStyles="text-align:left;"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Số quy chuẩn phù hợp" field="matchingTarget" cellStyles="text-align:left;"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày nộp" field="sendDate" format="dd/MM/yyyy" type="date"
                                                width="15%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Nội dung" formatter="page.formatStatus" get="page.getRow"
                                                width="10%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                            <sd:ColumnDataGrid  key="Chức năng" formatter="page.formatAction" get="page.getRow"
                                                width="10%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>

<sd:Dialog  id="signingViewDlg" height="auto" width="600px"
            key="Nội dung trình ký" showFullscreenButton="false"
            >
    <jsp:include page="signingFormView.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="rejectViewDlg" height="auto" width="600px"
            key="Nội dung từ chối ký" showFullscreenButton="false"
            >
    <jsp:include page="rejectFormView.jsp" flush="false"></jsp:include>
</sd:Dialog>
<div id="createDiv"></div>
<div id="viewDiv"></div>
<script type="text/javascript">
// back page
    backPage = function() {
        doGoToMenu(g_latestClickedMenu);
    };
// tim kiem
    page.search = function() {
        dijit.byId("filesGrid").vtReload('filesAction!onSearchFileToSigned.do', "searchForm");
    };
//reset form
    page.reset = function() {
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
//trước khi load form
    afterLoadForm = function(data) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("viewDiv").style.display = "";
        document.getElementById("createDiv").style.display = "";
        document.getElementById("signingDiv").style.display = "";
    };
//view file details
    page.showViewFile = function(fileId) {//show hiển thị chi tiết hồ sơ
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + fileId, "viewDiv", null, null, afterLoadForm);
    };
//show resutl signing
    showSigningResult = function(row) {//show phe duyet
        var file = dijit.byId("filesGrid").getItem(row);
        var statusName = page.getStatusName(parseInt(file.status));
        document.getElementById("approveFormView.status").innerHTML = statusName;
        document.getElementById("approveFormView.signingContent").innerHTML = escapeHtml_(file.signingContent);
        dijit.byId("signingViewDlg").show();
    };
// show reject result
    showRejectResult = function(row) {//show phe duyet
        var file = dijit.byId("filesGrid").getItem(row);
        var statusName = page.getStatusName(parseInt(file.status));
        document.getElementById("rejectFormView.status").innerHTML = statusName;
        document.getElementById("rejectFormView.signContent").innerHTML = escapeHtml_(file.signContent);
        dijit.byId("rejectViewDlg").show();
    };

    page.showSignDlg = function(row) {//show form khai báo giấy tiếp nhận công bố hợp quy
        var file = dijit.byId("filesGrid").getItem(row);
        sd.connector.post("filesAction!loadSignPage.do?signForm.fileId=" + file.fileId, "createDiv", null, null, afterLoadForm);
    };
    page.showSearchPanel = function() {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };

    page.downloadWord = function(fileId) {
        document.location = "exportWord!onExportPaper.do?fileId=" + fileId;
    };
    page.search();
</script>
