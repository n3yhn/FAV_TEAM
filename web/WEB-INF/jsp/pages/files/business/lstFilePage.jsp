<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
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
<script>
    page.getNo = function (index) {
        return dijit.byId("filesGrid").currentRow + index + 1;
    };
    page.getIndex = function (index) {
        return index + 1;
    };
    page.formatEdit = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            url = "<div style='text-align:center;cursor:pointer;'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + item.fileId + ");' /></div>";
        }
        return url;
    };
    page.formatStatus = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var status = parseInt(item.status);
            switch (status) {
                case 0:
                    url = "Mới tạo";
                    break;
                case 1:
                    url = "Mới nộp";
                    break;
                case 6:
                    url = "Đã phê duyệt kết quả";
                    break;
                case 14:
                    url = "Đã tiếp nhận";
                    break;
                case 15:
                    url = "Đã đối chiếu";
                    break;
                case 17:
                    url = "Đã tiếp nhận hồ sơ SĐBS";
                    break;
                case 18:
                    url = "Mới nộp - chờ tiếp nhận SĐBS";
                    break;
                case 20:
                    url = "Đã gửi công văn yêu cầu sửa đổi bổ sung";
                    break;
                case 21:
                    url = "Hồ sơ đã bị VT từ chối tiếp nhận";
                    break;
                case 22:
                    url = "Đã trả bản công bố";
                    break;
                case 33:
                    url = "Hồ sơ đã bị VT từ chối tiếp nhận SĐBS";
                    break;
                default:
                    url = "Đang xử lý";
            }
            var isFee = item.isFee;
            if (isFee == 2) {
                url = "Hồ sơ đã bị kế toán từ chối";
            }
        }

        return url;
    };
    page.formatAction = function (inData) {
        var row = inData - 1;
        var item = dijit.byId("filesGrid").getItem(inData - 1);
//        var checkCa = '${fn:escapeXml(isCa)}';
        var url = "";
        if (item != null) {
            var signed = true;
            if (item.userSigned == null || item.userSigned == "" || item.userSigned == "reset") {
                signed = false;
            }
            var deadlineAddition = item.deadlineAddition;
            var today = '${sysDate}';
            url = "<div style='text-align:left;cursor:pointer;'><img src='share/images/icons/copy_icon.png' width='17px' height='17px' title='Tạo bản sao hồ sơ' onClick='page.copyFile(" + item.fileId + ");' />";
            var fileSourceID = item.filesSourceID;
            var status = parseInt(item.status);
            switch (status) {
                case 0:
                    if (signed == false)
                    {
                        url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Bổ sung hồ sơ' onClick='page.showEditFile(" + item.fileId + ");' />";
//                        url += " | <img src='share/images/signature.png' width='20px' height='20px' title='Ký CA' onClick='page.signCa(" + row + ");' />";
//                        url += " | <img src='share/images/icons/UpArrow.png' width='17px' height='17px' title='Upload' onClick='page.Upload(" + row + ");' />";
                    } else {
                        url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Bổ sung hồ sơ' onClick='page.showEditFile(" + item.fileId + ");' />";
                        if (item.userSigned == "fileUploaded" || item.userSigned == "CBDN") {
                            url += " | <img src='share/images/icons/kdevelop_down.png' width='17px' height='17px' title='Hồ sơ đã upload' onClick='page.downloadFileSign(" + item.fileId + ");' />";
                        }
                        if (item.fileType != 3052 && item.fileType != 3051 && item.fileType != 3050 && item.fileType != 81) {
                            url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                        }
                        if (item.fileType == 3052 || item.fileType == 3051 || item.fileType == 3050 || item.fileType == 81) {
                            url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
                        } else {
                            //Hiepvv SuaDoiBoSung sau cong bo Fix ID Procedure sua doi sau cong bo
                            if (fileSourceID != null
                                    && fileSourceID > 0
                                    && status == 0
                                    && (signed == true || item.userSigned == "fileUploaded")) {
                                url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
                            }
                        }
                    }

                    break;
                case 1:
                    url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                    break;
                case 6:
                    if (item.isFee == 1 && item.isSignPdf == 2 && item.isDownload == 1)
                    {
                        url += " | <img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.formatLinkDownloadPdf(" + item.fileId + ");' />";
                    }
                    url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                    url += " | <img src='share/images/icons/kdevelop_down.png' width='17px' height='17px' title='Hồ sơ đã upload' onClick='page.downloadFileSign(" + item.fileId + ");' />";
                    break;
                case 7:
                    break;
                case 15:
                    url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                    if (item.isDownload == 1)
                    {
                        url += " | <img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.formatLinkDownloadPdf(" + item.fileId + ");' />";
                    }
                    break;
                case 16:
                    url += " | <img src='share/images/alertXacnhan.png' width='17px' height='17px' title='Thông báo đối chiếu lại hồ sơ' onClick='page.showAlertComparisonDlg(" + row + ");' />";
                    url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                    if (item.isDownload == 1)
                    {
                        url += " | <img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.formatLinkDownloadPdf(" + item.fileId + ");' />";
                    }
                    if (item.userSigned == "fileUploaded" || item.userSigned == "CBDN")
                    {
                        url += " | <img src='share/images/icons/kdevelop_down.png' width='17px' height='17px' title='Hồ sơ đã upload' onClick='page.downloadFileSign(" + item.fileId + ");' />";
                    }
                    break;
                case 20:
                    url += " | <img src='share/images/list.png' width='17px' height='17px' title='Nội dung thông báo sửa đổi bổ sung' onClick='page.showEvaludateResult(" + row + ");' /> ";

                    if (signed == false) {
                        url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Bổ sung hồ sơ' onClick='page.showEditFile(" + item.fileId + ");' />";
//                        url += " | <img src='share/images/signature.png' width='20px' height='20px' title='Ký CA' onClick='page.signCa(" + row + ");' />";
//                        url += " | <img src='share/images/icons/UpArrow.png' width='17px' height='17px' title='Upload' onClick='page.Upload(" + row + ");' />";
                    } else
                    {
                        url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Bổ sung hồ sơ' onClick='page.showEditFile(" + item.fileId + ");' />";
                        if (item.userSigned == "fileUploaded" || item.userSigned == "CBDN")
                        {
                            url += " | <img src='share/images/icons/kdevelop_down.png' width='17px' height='17px' title='Hồ sơ đã upload' onClick='page.downloadFileSign(" + item.fileId + ");' />";
                            if (deadlineAddition != null
                                    && (deadlineAddition > today
                                            || deadlineAddition == today)) {
                                url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
                            }
                            if (item.fileType != 3052
                                    && item.fileType != 3051
                                    && item.fileType != 3050) {
                                url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                            }
//                            if ((item.fileType == 3052
//                                    || item.fileType == 3051
//                                    || item.fileType || 3050)
//                                    && (deadlineAddition != null
//                                            && (deadlineAddition > today
//                                                    || deadlineAddition == today))) {
//                                url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
//                            }
                        } else {
                            if (deadlineAddition != null
                                    && (deadlineAddition > today
                                            || deadlineAddition == today)) {
                                url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
                            }
                            if (item.fileType != 3052
                                    && item.fileType != 3051
                                    && item.fileType != 3050) {
                                url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                            }
//                            if ((item.fileType == 3052
//                                    || item.fileType == 3051
//                                    || item.fileType || 3050)
//                                    && (deadlineAddition != null
//                                            && (deadlineAddition > today
//                                                    || deadlineAddition == today))) {
//                                url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
//                            }
                        }
                    }
                    break;
                case 21:
                    url += " | <img src='share/images/list.png' width='17px' height='17px' title='Nội dung thông báo bổ sung hồ sơ' onClick='page.showReceiveResult(" + row + ");' /> ";
//                    if (checkCa == 1)
//                    {
                    if (signed == false) {
                        url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Bổ sung hồ sơ' onClick='page.showEditFile(" + item.fileId + ");' />";
                        url += " | <img src='share/images/signature.png' width='20px' height='20px' title='Ký CA' onClick='page.signCa(" + row + ");' />";
                        url += " | <img src='share/images/icons/UpArrow.png' width='17px' height='17px' title='Upload' onClick='page.Upload(" + row + ");' />";
                    } else
                    {
                        url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Bổ sung hồ sơ' onClick='page.showEditFile(" + item.fileId + ");' />";
                        if (item.userSigned == "fileUploaded" || item.userSigned == "CBDN")
                        {
                            url += " | <img src='share/images/icons/kdevelop_down.png' width='17px' height='17px' title='Hồ sơ đã upload' onClick='page.downloadFileSign(" + item.fileId + ");' />";
                            url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
                            url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                        } else {
                            url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
                            url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                        }
                    }
                    break;
                case 22:
                    if (item.isDownload == 1)
                    {
                        url += " | <img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.formatLinkDownloadPdf(" + item.fileId + ");' />";
                    }
                    if (item.userSigned == "fileUploaded" || item.userSigned == "CBDN")
                    {
                        url += " | <img src='share/images/icons/kdevelop_down.png' width='17px' height='17px' title='Hồ sơ đã upload' onClick='page.downloadFileSign(" + item.fileId + ");' />";
                    }
                    break;
                case 23:
                    if (item.isFee == 1 && item.isSignPdf == 2 && item.isDownload == 1)
                    {
                        url += " | <img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.formatLinkDownloadPdf(" + item.fileId + ");' />";
                    }
                    url += " | <img src='share/images/alertXacnhan.png' width='17px' height='17px' title='Thông báo đối chiếu hồ sơ' onClick='page.showAlertComparisonDlg(" + row + ");' />";
                    url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                    if (item.userSigned == "fileUploaded")
                    {
                        url += " | <img src='share/images/icons/kdevelop_down.png' width='17px' height='17px' title='Hồ sơ đã upload' onClick='page.downloadFileSign(" + item.fileId + ");' />";
                    }

                    break;
                case 24:
                    if (item.isDownload == 1) {
                        url += " | <img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.formatLinkDownloadPdf(" + item.fileId + ");' />";
                    }
                    break;
                case 25:
                    if (item.isDownload == 1) {
                        url += " | <img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.formatLinkDownloadPdf(" + item.fileId + ");' />";
                    }
                    break;
                case 33:
                    url += " | <img src='share/images/list.png' width='17px' height='17px' title='Nội dung thông báo bổ sung hồ sơ' onClick='page.showReceiveResult(" + row + ");' /> ";
                    if (signed == false) {
                        url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Bổ sung hồ sơ' onClick='page.showEditFile(" + item.fileId + ");' />";
                        url += " | <img src='share/images/signature.png' width='20px' height='20px' title='Ký CA' onClick='page.signCa(" + row + ");' />";
                        url += " | <img src='share/images/icons/UpArrow.png' width='17px' height='17px' title='Upload' onClick='page.Upload(" + row + ");' />";
                    } else {
                        url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Bổ sung hồ sơ' onClick='page.showEditFile(" + item.fileId + ");' />";
                        if (item.userSigned == "fileUploaded" || item.userSigned == "CBDN")
                        {
                            url += " | <img src='share/images/icons/kdevelop_down.png' width='17px' height='17px' title='Hồ sơ đã upload' onClick='page.downloadFileSign(" + item.fileId + ");' />";
                            url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
                            url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                        } else {
                            url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
                            url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        url += "</div>";
        return url;
    };
    page.disabledCheckbox = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData);
        var check = true;
        if (item != null) {
            var status = parseInt(item.status);
            switch (status) {
                case 0:
                    check = false;
                    break;
                case 21:
                    check = false;
                    break;
                case 20:
                    check = false;
                    break;
                default:
                    check = true;
            }
        }
        return check;
    };
    page.formatWarning = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var url = "";
        var strWarning = "";
        var flag = 0;
        if (item != null) {
            var status = parseInt(item.status);
            var deadlineReceived = item.deadlineReceived;
            var deadlineAddition = item.deadlineAddition;
//            var deadlineApprove = item.deadlineApprove;
            var today = '${sysDate}';
            var isFee = parseInt(item.isFee);
            var dateAdd = 0;
            var fileType = item.fileType;
            var productTypeId = item.productTypeId;
            var modifyDate = item.modifyDate;
            if (isFee != 1 && (status == 6)) {
                strWarning += "- Chưa đóng phí hồ sơ đầy đủ&#13;";
            }
            switch (status) {
                case 1:
                    if (item.isFee == 1) {
                        if (deadlineReceived < today) {
                            strWarning += "- Quá hạn tiếp nhận&#13;";
                            flag = 1;
                        } else {
                            if (deadlineReceived == today) {
                                strWarning += "- Hôm nay là ngày tiếp nhận&#13;";
                            }
                        }
                    }
                    break;
                case 20:
                    if (deadlineAddition < today) {
                        strWarning += "- Quá hạn Sửa đổi bổ sung&#13;";
                        flag = 1;
                    } else {
                        if (deadlineAddition == today) {
                            strWarning += "- Hôm nay là ngày cuối nộp sửa đổi bổ sung&#13;";
                        }
                    }
                    break;
                case 5:
                    var diffDays = Math.ceil(Math.abs(today - modifyDate) / (1000 * 60 * 60 * 24)) + dateAdd;
                    if (fileType == 66701 || fileType == 66702) {
                        if (diffDays > 2) {
                            strWarning += "- Quá hạn phê duyệt&#13;";
                            flag = 1;
                        } else {
                            if (diffDays == 2) {
                                strWarning += "- Hôm nay là ngày cuối phê duyệt&#13;";
                            }
                        }
                    } else {
                        if (productTypeId == 3398258 || productTypeId == 3398259 || productTypeId == 3385104 || productTypeId == 66846) {
                            if (diffDays > 7) {
                                strWarning += "- Quá hạn phê duyệt&#13;";
                                flag = 1;
                            } else {
                                if (diffDays == 7) {
                                    strWarning += "- Hôm nay là ngày cuối phê duyệt&#13;";
                                }
                            }
                        } else {
                            if (fileType == 66746 || fileType == 66748) {
                                if (diffDays > 4) {
                                    strWarning += "- Quá hạn phê duyệt&#13;";
                                    flag = 1;
                                } else {
                                    if (diffDays == 4) {
                                        strWarning += "- Hôm nay là ngày cuối phê duyệt&#13;";
                                    }
                                }
                            }
                        }
                    }
                    break;
                default:
            }
        }
        if (strWarning.trim().length == 0) {
            url = "";
        } else {
            if (flag == 0) {
                url = "<img src='share/images/icons/warning.png' width='17px' height='17px' title='" + strWarning + "'/>";
            } else {
                url = "<img src='share/images/icons/error.png' width='17px' height='17px' title='" + strWarning + "'/>";
            }
        }
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
            <s:hidden id="deptId" name="searchForm.deptId"/>
            <sd:TextBox key="" id="searchForm.searchType" name="searchForm.searchType" cssStyle="display:none" />
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
                    </td>
                </tr>
                <tr>
                    <td align="right"><sd:Label key="Trạng thái"/></td>
                    <td>
                        <sd:SelectBox key="" id="searchForm.status" name="searchForm.status" cssStyle="width:100%">
                            <sd:Option value="-1">-- Chọn --</sd:Option>
                            <sd:Option value="0">Mới tạo</sd:Option>
                            <sd:Option value="1">Mới nộp</sd:Option>
                            <sd:Option value="14">Đã tiếp nhận</sd:Option>
                            <sd:Option value="17">Đã tiếp nhận hồ sơ SĐBS</sd:Option>
                            <sd:Option value="21">Đã bị từ chối tiếp nhận</sd:Option>
                            <sd:Option value="20">Đã tạo giấy thông báo sửa đổi bổ sung</sd:Option>   
                            <sd:Option value="15">Đã đối chiếu</sd:Option>                            
                            <sd:Option value="16">Đã đối chiếu có sai lệch</sd:Option>                                                                                
                            <sd:Option value="23">Hồ sơ chờ nộp giấy tờ gốc</sd:Option> 
                            <sd:Option value="6">Đã phê duyệt kết quả</sd:Option>
                            <sd:Option value="22">Đã trả bản công bố</sd:Option>                                                     
                        </sd:SelectBox>
                    </td>
                    <td align="right"><sd:Label key="Tên sản phẩm"/></td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.productName"
                                    key=""
                                    name="searchForm.productName" maxlength="250"/>
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
        <sx:ResultMessage id="resultCreateMessage" />
        <table width="100%">
            <tr>
                <td>
                    <div id="fileGridDiv" style="width:100%;">
                        <sd:DataGrid id="filesGrid"
                                     getDataUrl=""
                                     rowSelector="0%"
                                     style="width:auto;height:auto"
                                     rowsPerPage="20"
                                     serverPaging="true"
                                     clientSort="false">
                            <sd:ColumnDataGrid key="category.No" get="page.getNo" width="5%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;" setDisabled="page.disabledCheckbox"/>
                            <sd:ColumnDataGrid editable="true" key="Xem" headerStyles="text-align:center;" width="3%" cellStyles="text-align:center;"
                                               formatter="page.formatEdit" get="page.getIndex"/>                        
                            <sd:ColumnDataGrid  key="Hành động" 
                                                width="10%"  headerStyles="text-align:center;"
                                                formatter="page.formatAction" get="page.getIndex"/>                            
                            <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode" width="7%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Loại hồ sơ" field="fileTypeName"
                                                width="20%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName"  cellStyles="text-align:left;"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày nộp" field="sendDate" format="dd/MM/yyyy" type="date"
                                                width="8%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày phê duyệt" field="approveDate" format="dd/MM/yyyy" type="date"
                                                width="8%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày hẹn trả" field="deadlineApprove" format="dd/MM/yyyy" type="date"
                                                width="8%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />                            
                            <sd:ColumnDataGrid  key="Trạng thái" get="page.getIndex" formatter="page.formatStatus" cellStyles="text-align:center;"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="Cảnh báo" headerStyles="text-align:center;" width="3%" cellStyles="text-align:center;"
                                               formatter="page.formatWarning" get="page.getIndex"/>
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:Button key="" onclick="page.onDeleteFiles();" >
                        <img src="share/images/icons/deleteStand.png" height="14" width="18" alt="Hủy">
                        <span style="font-size:12px">Hủy hồ sơ</span>
                    </sd:Button>  
                    <sd:Button id="btnshowPayMore" key="" onclick="page.payFileMore()">
                        <img src="${contextPath}/share/images/icons/payment.png" height="20" width="20"/>
                        <span style="font-size:12px" title="Cho phép thanh toán nhiều hồ sơ qua KeyPay, vui lòng tích chọn các hồ sơ cần thanh toán phí thẩm định trước khi thực hiện">Thanh toán phí thẩm định nhiều hồ sơ qua KeyPay</span>
                    </sd:Button>
                    <sd:Button id="btnSignCAFile" key="" onclick="page.showSignCAFile()">
                        <img src="${contextPath}/share/images/signature.png" height="20" width="20"/>
                        <span
                            style="font-size:12px"
                            title="Cho phép ký số hồ sơ, vui lòng tích chọn hồ sơ cần ký số trước khi thực hiện">
                            Ký số hồ sơ
                        </span>
                    </sd:Button>
                </td>

            </tr>
        </table>
    </sd:TitlePane>
</div>
<sd:Dialog id="selectDeptDlg" key="Chọn cơ quan xử lý" width="500px">
    <div style="overflow: auto; max-height: 500px">
        <jsp:include page="selectDept.jsp" />
    </div>
</sd:Dialog>
<sd:Dialog  id="feedbackEvaluateViewDetailsDlg" height="auto" width="800px"
            key="Chi tiết công văn SĐBS" showFullscreenButton="false"
            >
    <jsp:include page="../evaluation/feedbackEvaluate/feedbackEvaluateFormView.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="receivedFormViewDetailsDlg" height="auto" width="800px"
            key="Nội dung yêu cầu" showFullscreenButton="false"
            >
    <jsp:include page="../received/receivedFormViewDetails.jsp" flush="false"></jsp:include>
</sd:Dialog>
<%--
<sd:Dialog  id="comparisonByBusinessDlg" height="auto" width="600px"
            key="Nội dung thông báo đối chiếu hồ sơ" showFullscreenButton="false"
            >
    <jsp:include page="../../files/comparison/businessComparisonDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>
--%>
<sd:Dialog  id="upload" height="auto" width="400px"
            key="Upload hồ sơ đã ký số" showFullscreenButton="false"
            >
    <jsp:include page="signUpload.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="businessSignFormPluginDlg" height="auto" width="1200px"
            key="Ký số hồ sơ" showFullscreenButton="false"
            >
    <jsp:include page="../evaluation/businessSignFormPlugin.jsp" flush="false"></jsp:include>
</sd:Dialog>
<div id="createDiv"></div>

<div id="myapplet" style="visibility:hidden; height: 0px;"> </div>

<script type="text/javascript">
    var workingFileId;
    var fileId = 0;
    var itemsToSign = null;
    var signIndex = 0;
    var flagSignMore = false;
    backPage = function () {
        var filesGrid_VTGrid = dijit.byId('filesGrid_VTGrid');
        if (filesGrid_VTGrid) {
            filesGrid_VTGrid.destroyRecursive(true);
        }
        var deptGrid_VTGrid = dijit.byId('deptGrid_VTGrid');
        if (deptGrid_VTGrid) {
            deptGrid_VTGrid.destroyRecursive(true);
        }
        var commentGridId_VTGrid = dijit.byId('commentGridId_VTGrid');
        if (commentGridId_VTGrid) {
            commentGridId_VTGrid.destroyRecursive(true);
        }
        var categoryTitle = dijit.byId('categoryTitle');
        if (categoryTitle) {
            categoryTitle.destroyRecursive(true);
        }
        var businessList = dijit.byId('businessList');
        if (businessList) {
            businessList.destroyRecursive(true);
        }
        var selectDeptDlg = dijit.byId('selectDeptDlg');
        if (selectDeptDlg) {
            selectDeptDlg.destroyRecursive(true);
        }

        var feedbackEvaluateViewDetailsDlg = dijit.byId('feedbackEvaluateViewDetailsDlg');
        if (feedbackEvaluateViewDetailsDlg) {
            feedbackEvaluateViewDetailsDlg.destroyRecursive(true);
        }
        var receivedFormViewDetailsDlg = dijit.byId('receivedFormViewDetailsDlg');
        if (receivedFormViewDetailsDlg) {
            receivedFormViewDetailsDlg.destroyRecursive(true);
        }
        var comparisonByBusinessDlg = dijit.byId('comparisonByBusinessDlg');
        if (comparisonByBusinessDlg) {
            comparisonByBusinessDlg.destroyRecursive(true);
        }
        var upload = dijit.byId('upload');
        if (upload) {
            upload.destroyRecursive(true);
        }

        var businessSignFormPluginDlg_underlay = dijit.byId('businessSignFormPluginDlg_underlay');
        if (businessSignFormPluginDlg_underlay) {
            businessSignFormPluginDlg_underlay.destroyRecursive(true);
        }
        var businessSignFormPluginDlg = dijit.byId('businessSignFormPluginDlg');
        if (businessSignFormPluginDlg) {
            businessSignFormPluginDlg.destroyRecursive(true);
        }

        var titlePaneViewFileAFP = dijit.byId('titlePaneViewFile_AFP');
        if (titlePaneViewFileAFP) {
            titlePaneViewFileAFP.destroyRecursive(true);
        }
        var titlePaneEvaluateAFP = dijit.byId('titlePaneEvaluate_AFP');
        if (titlePaneEvaluateAFP) {
            titlePaneEvaluateAFP.destroyRecursive(true);
        }
        var txtBase64HashAFPCheck = dijit.byId('txtBase64HashAFP');
        if (txtBase64HashAFPCheck) {
            txtBase64HashAFPCheck.destroyRecursive(true);
        }
        var txtBase64HashAFP0Check = dijit.byId('txtBase64HashAFP0');
        if (txtBase64HashAFP0Check) {
            txtBase64HashAFP0Check.destroyRecursive(true);
        }
        var txtCertSerialAFPCheck = dijit.byId('txtCertSerialAFP');
        if (txtCertSerialAFPCheck) {
            txtCertSerialAFPCheck.destroyRecursive(true);
        }
        sd.connector.post("filesAction!toBusinessListPage.do?", "createDiv", null, null, null);
    };

    page.reset = function () {
        dijit.byId("searchForm.fileCode").setValue("");
        dijit.byId("searchForm.fileType").setValue("-1");
        dijit.byId("searchForm.sendDateFrom").setValue("");
        dijit.byId("searchForm.sendDateTo").setValue("");
    };

    page.search = function () {
        dijit.byId("filesGrid").vtReload('filesAction!onSearchBusinessFiles.do?', "searchForm");
    };

    afterLoadForm = function (data) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
    };

    page.showViewFile = function (fileId) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + fileId + "&createForm.viewType=0", "createDiv", null, null, afterLoadForm);
    };

    page.showEditFile = function (fileId) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
        sd.connector.post("filesAction!toCreateFilePage.do?createForm.fileId=" + fileId + "&checkEdit=1", "createDiv", null, null, afterLoadForm);
    };

    page.onDeleteFiles = function () {
        if (!dijit.byId("filesGrid").vtIsChecked()) {
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
                    } else {
                        msg.confirm("Bạn có chắc muốn hủy hồ sơ không ?", "Xóa hồ sơ", page.deleteFiles);
                    }
                };

                page.deleteFiles = function () {
                    var content = dijit.byId("filesGrid").vtGetCheckedDataForPost("lstItemOnGrid");
                    sd.connector.post("filesAction!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
                };

                page.returnMessageDelete = function (data) {
                    var obj = dojo.fromJson(data);
                    var result = obj.items;
                    resultMessage_show("resultCreateMessage", result[0], result[1], 5000);
                    page.search();
                };

                page.showDept = function (row) {
                    var item = dijit.byId("filesGrid").getItem(row);
                    workingFileId = item.fileId;
                    workingStatus = item.status;
                    //dijit.byId("deptGrid").vtReload("procedureAction!getDeptProcedure.do?procedureId=" + item.fileType);
                    document.getElementById("Sign").checked = false;
                    document.getElementById("Unsign").checked = false;
                    //dijit.byId("selectDeptDlg").show();
                    var AgencyName = '${fn:escapeXml(AgencyName)}';
                    var AgencyId = '${fn:escapeXml(AgencyId)}';
                    msg.confirm('<sd:Property>Bạn có chắc chắn muốn gửi hồ sơ đến đơn vị "</sd:Property>' + AgencyName + '<sd:Property>" không?</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', function () {
                                if (workingStatus == 20) {
                                    //binhnt edit gui thang lai chuyen vien:
                                    sd.connector.post("filesAction!onReceivedFileToStaff.do?" + token.getTokenParamString() + "&createForm.fileId=" + workingFileId, null, null, null, page.afterSend);
                                } else {
                                    //sd.connector.post("filesAction!onReceivedFileToStaff.do?" + token.getTokenParamString() + "&createForm.fileId=" + workingFileId, null, null, null, page.afterSend);
                                    sd.connector.post("filesAction!onSelectFlow.do?" + token.getTokenParamString() + "&createForm.deptId=" + AgencyId + "&createForm.fileId=" + workingFileId, null, null, null, page.afterSend);
                                }
                            });
                        };
                        page.payFile = function (row)
                        {

                            var item = dijit.byId("filesGrid").getItem(row);
                            sd.connector.post("filesAction!preparePayment.do?createFeeForm.fileId=" + item.fileId, 'createDiv', null, null, afterLoadPayForm);
                        };
                        afterLoadPayForm = function (data) {
                            document.getElementById("searchDiv").style.display = "none";
                            document.getElementById("createDiv").style.display = "";
                        };
                        page.formatLinkDownloadPdf = function (fileId) {
                            document.location = "uploadiframe!openFileUserAttachPdf.do?fileId=" + fileId;
                        };
                        page.sendFile = function (row) {
                            var item = dijit.byId("filesGrid").getItem(row);
                            if (item.isFee == 0) {
                                msg.alert("Hồ sơ đã chuyển sang nhóm sản phẩm có phí cao, yêu cầu bạn thanh toán thêm lệ phí trước khi gửi thẩm định lại hồ sơ và đính kèm đầy đủ hóa đơn cũ và  mới khi xác nhận thanh toán.");
                            } else {
                                page.showDept(row);
                            }
                        };
                        page.signCa = function (row) {
                            var item = dijit.byId("filesGrid").getItem(row);
                            sd.connector.post("filesAction!onValidate.do?searchForm.fileId=" + item.fileId, null, null, null, function (data) {
                                var obj = dojo.fromJson(data);
                                var result = obj.items;
                                if (result[0] == "1") {
                                    var item = dijit.byId("filesGrid").getItem(row);
                                    workingFileId = item.fileId;
                                    page.onSelectDeptNew(row);
                                } else {
                                    msg.alert(result[1] + ", Hãy cập nhật lại thông tin hồ sơ", "Thông báo");
                                }
                            });
                        };
                        page.loadView = function () {
                            page.clearInsertForm();
                            dijit.byId("createDlg").show();
                        };
                        page.showSearchPanel = function () {
                            var panel = document.getElementById("searchDiv");
                            panel.setAttribute("style", "display:;");
                            dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
                        };
                        dijit.byId("searchForm.status").setValue(${fn:escapeXml(searchForm.status)});
                        page.search();
                        page.copyFile = function (fileId) {
                            document.getElementById("searchDiv").style.display = "none";
                            document.getElementById("createDiv").style.display = "";
                            sd.connector.post("filesAction!toCopyFilePage.do?createForm.fileId=" + fileId + "&checkEdit=1", "createDiv", null, null, function () {
                                document.getElementById("searchDiv").style.display = "none";
                                document.getElementById("createDiv").style.display = "";
                                dijit.byId("createForm.fileId").setValue(null);
                            });
                        };
                        page.showEvaludateResult = function (row) {//show kết quả thẩm định
                            var file = dijit.byId("filesGrid").getItem(row);
                            var statusName = page.getStatusName(parseInt(file.status));
                            fileId = file.fileId;
                            document.getElementById("evaluateFormView.status").innerHTML = statusName;
                            document.getElementById("evaluateFormView.staffRequest").innerHTML = escapeHtml_(file.staffRequest);
                            var isTypeChange = file.isTypeChange;
                            if (isTypeChange == 1) {
                                document.getElementById("evaluateFormView.isTypeChange").style.display = "";
                            } else {
                                document.getElementById("evaluateFormView.isTypeChange").style.display = "none";
                            }
                            page.replaceNewLineByBr();
                            sd.connector.post("filesAction!showStaffContact.do?objectId=" + fileId, null, null, null, page.afterShowStaffContact);
                        };
                        page.afterShowStaffContact = function (data) {
                            page.showComment();
                            page.showVersionSDBS();
                            dijit.byId("feedbackEvaluateViewDetailsDlg").show();
                            data = dojo.fromJson(data);
                            document.getElementById("evaluateFormView.staffNameContact").innerHTML = data.customInfo[0];
                            document.getElementById("evaluateFormView.staffEmailContact").innerHTML = data.customInfo[1];
                            document.getElementById("evaluateFormView.staffTelContact").innerHTML = data.customInfo[2];
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
                                    url = "Chuyên viên kết luận Đạt";
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
                        page.showReceiveResult = function (row) {//show ket qua tiep nhan ho so - van thu - 140410 binhnt53
                            var file = dijit.byId("filesGrid").getItem(row);
                            var statusName = page.getStatusName(parseInt(file.status));
                            document.getElementById("receivedFormViewDetails.status").innerHTML = statusName;
                            document.getElementById("receivedFormViewDetails.clericalRequest").innerHTML = escapeHtml_(file.clericalRequest);
                            page.replaceTblReceivedFormViewDetails();
                            dijit.byId("receivedFormViewDetailsDlg").show();
                        };
                        page.reloadViewOldVersion = function (oldVersion, thisVersion) {//u140728
                            document.getElementById('createDiv').Value = '';
                            //u140730-dund1
                            var gridRegister = dijit.byId('processCommentGridId_VTGrid');
                            if (gridRegister) {
                                gridRegister.destroyRecursive(true);
                            }//!u140730
                            sd.connector.post("filesAction!loadFilesByOldVersion.do?thisVersion=" + thisVersion + "&oldVersion=" + oldVersion, "createDiv", null, null, afterReloadViewOldVersion);
                        };
                        afterReloadViewOldVersion = function (data) {
                        }; //!u140728

                        page.showAlertComparisonDlg = function (row) {
                            var item = dijit.byId("filesGrid").getItem(row);
                            dijit.byId("comparisonForm.fileId").setValue(item.fileId);
                            document.getElementById("comparisonForm.lastContent").innerHTML = item.comparisonContent;
                            page.rplBrTblBusinessComparisonDlg();
                            dijit.byId("comparisonByBusinessDlg").show();
                        };
                        page.Upload = function (row)
                        {
                            var item = dijit.byId("filesGrid").getItem(row);
                            sd.connector.post("filesAction!onValidate.do?searchForm.fileId=" + item.fileId, null, null, null, function (data) {
                                var obj = dojo.fromJson(data);
                                var result = obj.items;
                                if (result[0] == "1") {
                                    workingFileId = item.fileId;
                                    clearAttFile("sendForm.attachFileNew");
                                    dijit.byId("upload").show();
                                } else {
                                    msg.alert(result[1] + ", Hãy cập nhật lại thông tin hồ sơ", "Thông báo");
                                }
                            });
                        };
                        page.downloadFileSign = function (fileId) {
                            document.location = "uploadiframe!openFileUserUpload.do?fileId=" + fileId;
                        };
                        page.setCreateDiv = function (fileId, fileType) {
                            var fileTypeTemp = fileType;
                            var fileIdTemp = fileId;
                            sd.connector.post("filesAction!toCopyFilePage.do?createForm.fileId=" + fileIdTemp + "&fileType=" + fileTypeTemp + "&checkEdit=1", "createDiv", null, null, function () {
                                document.getElementById("searchDiv").style.display = "none";
                                document.getElementById("createDiv").style.display = "";
                                dijit.byId("createForm.fileId").setValue(null);
                            });
                            document.getElementById('createDiv').Value = '';
                            //sd.connector.post("filesAction!toCreateFilePage.do?fileType=" + fileTypeTemp, "createDiv", null, null, afterLoadFormChange);
                        };
                        afterLoadFormChange = function (data) {
                        };
//hieptq update 130115
                        page.payFileMore = function ()
                        {
                            if (!dijit.byId("filesGrid").vtIsChecked()) {
                                msg.alert('Bạn chưa chọn hồ sơ để thanh toán phí thẩm định', 'Cảnh báo');
                            } else {
                                msg.confirm('Bạn có chắc chắn muốn thanh toán những hồ sơ đã chọn ?', '<sd:Property>confirm.title1</sd:Property>', page.payFileMoreKeyPay);
                                        }

                                    };
                                    page.payFileMoreKeyPay = function () {
                                        var items = dijit.byId("filesGrid").vtGetCheckedItems();
                                        var lstObjectId = "";
                                        var check;
                                        if (items != null && items.length >= 0) {
                                            for (var i = 0; i < items.length; i++)
                                            {
                                                if (i != items.length - 1)
                                                {
                                                    lstObjectId += items[i].fileId + ",";
                                                } else
                                                {
                                                    lstObjectId += items[i].fileId;
                                                }

                                                if (items[i].status == 0 && items[i].userSigned != "")
                                                {

                                                    check = true;
                                                } else
                                                {

                                                    check = false;
                                                    break;
                                                }
                                            }

                                            if (check == true) {
                                                page.payFileMoreKeyPayNew(lstObjectId);
                                            } else {
                                                msg.alert('Có hồ sơ được chọn không ở trạng thái có thể thanh toán', 'Cảnh báo');
                                            }
                                        }
                                    };
                                    page.payFileMoreKeyPayNew = function (lstObjectId)
                                    {
                                        sd.connector.post("filesAction!preparePaymentMore.do?lstObjectId=" + lstObjectId, 'createDiv', null, null, afterLoadPayForm);
                                    };
                                    page.showSignCAFile = function () {//160705 - 1
                                         if (!dijit.byId("filesGrid").vtIsChecked()) {
                                            msg.alert('Bạn chưa chọn hồ sơ để thực hiện ký số!', 'Cảnh báo');
                                        } else {
                                            itemsToSign = dijit.byId("filesGrid").vtGetCheckedItems();
                                            var length = itemsToSign.length;
                                            for(var i=0; i<length; i++){
                                                sd.connector.post("filesAction!onValidate.do?searchForm.fileId=" + itemsToSign[i].fileId, null, null, null, function (data) {
                                                    var obj = dojo.fromJson(data);
                                                    var result = obj.items;
                                                    if (result[0] != "1") {
                                                        msg.alert(result[1] + ", Hãy cập nhật lại thông tin hồ sơ", "Thông báo");
                                                    } 
                                                });

                                            }    
                                            signIndex = 0;
                                            msg.confirm('Bạn có chắc chắn muốn ký số hồ sơ?', '<sd:Property>confirm.title1</sd:Property>', page.signFileUsingPlugin);
                                        }
                                                };
                                                page.signFileUsingPlugin = function () {//160705 - 2
                                                    dijit.byId("businessSignFormPluginDlg").show();
                                                    document.getElementById('divProcess').innerHTML = "Hệ thống đang thực hiện ký số:" + (signIndex + 1) + "/" + itemsToSign.length + " hồ sơ  ";
                                                    document.getElementById("divSignProcess").style.display = "";
                                                    document.getElementById("trWaitViewFile").style.display = 'none';
                                                    page.onSign(itemsToSign[signIndex], true);
                                                };
                                                page.onSign = function (file, isSignMore) {//160705
                                                    flagSignMore = isSignMore;
                                                    dijit.byId("businessSignForm.fileId").setValue(file.fileId);
                                                    document.getElementById("businessSignForm.businessName").innerHTML = escapeHtml_(file.businessName);
                                                    document.getElementById("businessSignForm.productName").innerHTML = escapeHtml_(file.productName);
                                                    sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + file.fileId
                                                            + "&createForm.viewType=2&viewTypeDialog=1", "divViewFile", null, null, afterShowApproveViewFile);
                                                };
                                                afterShowApproveViewFile = function () {
                                                    document.getElementById("trWaitViewFile").style.display = 'none';
                                                    page.onApproveSignPlugin();
                                                };
                                                onCloseBusinessSignFormDlg = function () {
                                                    dijit.byId("businessSignFormPluginDlg").hide();
//            backPage();
//            sd.connector.post("filesAction!index.do?", null, null, null, null);
                                                };
</script>
