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

<script type="text/javascript">

    page.getNo = function (index) {
        return dijit.byId("filesGrid").currentRow + index + 1;
    };
    page.getRow = function (inRow) {
        return inRow;
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
				/*
                case 6:
                    if ((item.announcementReceiptPaperId != null && item.announcementReceiptPaperId > 0) || (item.confirmImportSatistPaperId != null && item.confirmImportSatistPaperId > 0)) {
                        if (item.isSignPdf == 2)
                        {
                            url += " | <img src='share/images/Document.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.downloadWord(" + item.fileId + ");' />";
                        }
                    }

                    break;
				*/
                case 22:
                    if ((item.announcementReceiptPaperId != null && item.announcementReceiptPaperId > 0) || (item.confirmImportSatistPaperId != null && item.confirmImportSatistPaperId > 0)) {
                        if (item.isSignPdf == 2)
                        {
                            url += " | <img src='share/images/Document.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.downloadWord(" + item.fileId + ");' />";
                        }
                    }

                    break;
				/*
                case 23:
                    if ((item.announcementReceiptPaperId != null && item.announcementReceiptPaperId > 0) || (item.confirmImportSatistPaperId != null && item.confirmImportSatistPaperId > 0)) {
                        if (item.isSignPdf == 2)
                        {
                            url += " | <img src='share/images/Document.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.downloadWord(" + item.fileId + ");' />";
                        }
                    }

                    break;
				
                case 24:
                    if ((item.announcementReceiptPaperId != null && item.announcementReceiptPaperId > 0) || (item.confirmImportSatistPaperId != null && item.confirmImportSatistPaperId > 0)) {
                        if (item.isSignPdf == 2)
                        {
                            url += " | <img src='share/images/Document.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.downloadWord(" + item.fileId + ");' />";
                        }
                    }

                    break;
                case 16:
                    if ((item.announcementReceiptPaperId != null && item.announcementReceiptPaperId > 0) || (item.confirmImportSatistPaperId != null && item.confirmImportSatistPaperId > 0)) {
                        if (item.isSignPdf == 2)
                        {
                            url += " | <img src='share/images/Document.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.downloadWord(" + item.fileId + ");' />";
                        }
                    }

                    break;
                case 25:
                    if ((item.announcementReceiptPaperId != null && item.announcementReceiptPaperId > 0) || (item.confirmImportSatistPaperId != null && item.confirmImportSatistPaperId > 0)) {
                        if (item.isSignPdf == 2)
                        {
                            url += " | <img src='share/images/Document.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.downloadWord(" + item.fileId + ");' />";
                        }
                    }

                    break;
				*/
                case 15:
                    if ((item.announcementReceiptPaperId != null && item.announcementReceiptPaperId > 0) || (item.confirmImportSatistPaperId != null && item.confirmImportSatistPaperId > 0)) {
                        if (item.isSignPdf == 2)
                        {
                            url += " | <img src='share/images/Document.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.downloadWord(" + item.fileId + ");' />";
                        }
                    }

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
                url = "Đã trả lại bổ dung hồ sơ";
                break;
            case 8:
                url = "Đã trả lại để thẩm định lại";
                break;
            case 9:
                url = "Đã trả lại để xem xét lại";
                break;
            default:
                url = "Mới tạo";
        }
        return url;
    };
    page.formatStatusView = function (inData) {
        var row = inData;
        var item = dijit.byId("filesGrid").getItem(row);
        var display = "";
        if (item != null) {
            display = item.displayStatus;
        }
        return display;
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
                        <sd:Label key="Người thẩm xét"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.Staff"
                                    key=""
                                    name="searchForm.Staff" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Loại hồ sơ"/>
                    </td>
                    <td>
                        <sd:SelectBox cssStyle="width:100%" readonly="true"
                                      id="searchForm.fileType"
                                      key="" data="lstFileType" valueField="procedureId" labelField="name"
                                      name="searchForm.fileType" >
                        </sd:SelectBox>
                        <sd:TextBox key="" id="searchForm.status" name="searchForm.status" cssStyle="display:none"/>
                    </td>                    
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
                            <sd:ColumnDataGrid key="category.No" get="page.getNo" width="5%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="Xem" headerStyles="text-align:center;" width="5%" cellStyles="text-align:center;"
                                               formatter="page.formatAction" get="page.getIndex"/>                        
                            <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode" width="5%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Loại hồ sơ" field="fileTypeName"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên tổ chức, cá nhân" field="businessName"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName"  cellStyles="text-align:center;"
                                                width="15%"  headerStyles="text-align:center;" />                            
                            <sd:ColumnDataGrid  key="Ngày nộp" field="sendDate" format="dd/MM/yyyy" type="date"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />                                                                                                                
                            <sd:ColumnDataGrid  key="Ngày hẹn trả" field="deadlineApprove" format="dd/MM/yyyy" type="date"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />                            
                            <sd:ColumnDataGrid  key="Kết quả" formatter="page.formatStatusView" get="page.getRow"
                                                width="7%"  headerStyles="text-align:center;" cellStyles="text-align:left;"/>
                            <sd:ColumnDataGrid  key="Cán bộ xử lý chính" field="nameStaffProcess"  cellStyles="text-align:center;"
                                                width="10%"  headerStyles="text-align:center;" />                        
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>
<div id="signapplet" style="visibility:hidden"> 
</div>

<div id="viewDiv"></div>
<script type="text/javascript">

    backPage = function () {
        //doGoToMenu(g_latestClickedMenu);
        document.getElementById("searchDiv").style.display = "";
        document.getElementById("viewDiv").style.display = "none";
    };

    page.search = function () {
        dijit.byId("filesGrid").vtReload('filesAction!onSearchFileToApproved.do', "searchForm");
    };

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
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("viewDiv").style.display = "";
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + fileId + "&createForm.viewType=2", "viewDiv", null, null, afterLoadForm);
    };

    showEvaludateResult = function (row) {
        var file = dijit.byId("filesGrid").getItem(row);
        var statusName = page.getStatusName(parseInt(file.status));
        document.getElementById("evaluateFormView.status").innerHTML = statusName;
        document.getElementById("evaluateFormView.staffRequest").innerHTML = escapeHtml_(file.staffRequest);
        document.getElementById("evaluateFormView.leaderStaffRequest").innerHTML = escapeHtml_(file.leaderStaffRequest);
        dijit.byId("evaluateViewDlg").show();
    };

    page.showSearchPanel = function () {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };

    page.downloadWord = function (fileId) {
        document.location = "exportWord!onExportPaper.do?fileId=" + fileId + "&typeExport=docx";
        //page.push();
        //document.location = "uploadiframe!openFileUserAttachPdf.do?fileId=" + fileId;
    };
    page.push = function () {
        //alert(check);

        document.getElementById('signapplet').innerHTML = buildApplet();
    };
    buildApplet = function () {
        var applet = "<applet WIDTH=\"300px\" HEIGHT=\"300px\" code=\"com.viettel.QLLLTP.ca.applet.DataSignApplet\" name=\"signApplet12\" id=\"signApplet12\" archive=\"PdfSign.jar?v=091510052018\">\n"
//                + "        <param value=\"JavaProject4.jar?v=091510052015,\n"
//                + "               \"\n"
//                + "               name=\"archive\">\n"
                + "    </applet>\n"
                + "</div>    \n";
        return applet;

    };
    page.search();

</script>
