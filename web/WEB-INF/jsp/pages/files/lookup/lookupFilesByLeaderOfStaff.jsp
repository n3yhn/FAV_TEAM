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
<script>
    page.getNo = function (index) {
        return dijit.byId("filesGrid").currentRow + index + 1;
    };

    page.getIndex = function (index) {
        return index + 1;
    };
    page.getRow = function (inRow) {
        return inRow;
    };
    page.formatAction = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var row = inData;
        if (item != null) {
            var status = parseInt(item.status);

            var url = "<div style='text-align:center;cursor:pointer;display:inline'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + item.fileId + ");' />";
            if (item.userSigned == "fileUploaded")
            {
                url += " | <img src='share/images/icons/kdevelop_down.png' width='17px' height='17px' title='Hồ sơ đã upload' onClick='page.downloadFileSign(" + item.fileId + ");' />";
            }
            switch (status) {
                case 4:
                    //url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Xem xét hồ sơ' onClick='page.showReviewForm(" + row + ");' />";

                    break;
                case 7:
                    //url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Xem xét SĐBS' onClick='page.showReviewAdditionForm(" + row + ");' />";
                    break;
                case 9:
//                    url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Xem xét' onClick='page.showReviewAdditionForm(" + row + ");' />";
//                    url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Xem xét hồ sơ' onClick='page.showReviewForm(" + row + ");' />";
                    break;
                case 15:
                    url += " | <img src='share/images/alertXacnhan.png' width='17px' height='17px' title='Xem xét kết quả đối chiếu hồ sơ' onClick='page.showLeaderOfStaffComparisonDlg(" + row + ");' />";
                    break;
                case 22:
                    if (item.isDownload == 1) {
                        url += " | <img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.formatLinkDownloadPdf(" + item.fileId + ");' />";
                    }
                    break;
                case 6:
                    if (item.isDownload == 1) {
                        url += " | <img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.formatLinkDownloadPdf(" + item.fileId + ");' />";
                    }
                    break;
                case 25:
                    url += " | <img src='share/images/alertXacnhan.png' width='17px' height='17px' title='Xem xét kết quả đối chiếu hồ sơ' onClick='page.showLeaderOfStaffComparisonDlg(" + row + ");' />";
                    break;
                default:
                    ;
            }
        }
        url += "</div>"
        return url;
    };

    page.formatStatus = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var status = parseInt(item.status);
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
                case 41:
                    url = "Thống kê trong ngày";
                    break;
                case 30:
                    url = "Hồ sơ sắp hết hạn bổ sung";
                    break;
                case 22:
                    url = "Đã trả bản công bố";
                    break;
                default:
                    url = "Mới tạo";
            }
        }

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
            case 22:
                url = "Đã trả bản công bố";
                break;
            case 15:
                url = "Đã đối chiếu";
                break;
            default:
                url = "Mới tạo";
        }
        return url;
    };

    page.formatStatusView = function (inData) {
        var row = inData - 1;
        var item = dijit.byId("filesGrid").getItem(row);
        var url = "";
        if (item != null) {
            url = item.displayStatus;
        }
        url = "<a href='#' onClick='showEvaludateResult(" + row + ")'>" + url + "</a>";
        return url;
    };

    page.formatWarning = function (inData) {//binhnt53 141208
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var url = "";
        var strWarning = "";
        var flag = 0;
        if (item != null) {
            var todayCl = new Date();
            var status = parseInt(item.status);
            var today = '${sysDate}';
            var receivedDate = item.receivedDate;
            var modifyDate = item.modifyDate;
            var deadlineReceived = item.deadlineReceived;
            var deadlineAddition = item.deadlineAddition;
            //var deadlineApprove = Date.parse(item.deadlineApprove);
            var productTypeId = item.productTypeId;
            var isFee = parseInt(item.isFee);
            var fileType = item.fileType;
            var dateAdd = 0;
            if (todayCl.getDay() == 6) {
                dateAdd = 2;
            } else {
                if (todayCl.getDay() == 0) {
                    dateAdd = 1;
                }
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
                case 14:
                    var diffDays = Math.ceil(Math.abs(today - receivedDate) / (1000 * 60 * 60 * 24)) + dateAdd;
                    if (diffDays > 1) {
                        strWarning += "- Quá hạn phân công&#13;";
                        flag = 1;
                    } else {
                        if (diffDays == 1) {
                            strWarning += "- Hôm nay là ngày phân công&#13;";
                        }
                    }
                    break;
                case 3:
                    var diffDays = Math.ceil(Math.abs(today - modifyDate) / (1000 * 60 * 60 * 24)) + dateAdd;
                    if (fileType == 66701 || fileType == 66702) {
                        if (diffDays > 2) {
                            strWarning += "- Quá hạn thẩm định&#13;";
                            flag = 1;
                        } else {
                            if (diffDays == 2) {
                                strWarning += "- Hôm nay là ngày cuối thẩm định&#13;";
                            }
                        }
                    } else {
                        if (productTypeId == 3398258 || productTypeId == 3398259 || productTypeId == 3385104 || productTypeId == 66846) {
                            if (diffDays > 7) {
                                strWarning += "- Quá hạn thẩm định&#13;";
                                flag = 1;
                            } else {
                                if (diffDays == 7) {
                                    strWarning += "- Hôm nay là ngày cuối thẩm định&#13;";
                                }
                            }
                        } else {
                            if (diffDays > 4) {
                                strWarning += "- Quá hạn thẩm định&#13;";
                                flag = 1;
                            } else {
                                if (diffDays == 4) {
                                    strWarning += "- Hôm nay là ngày cuối thẩm định&#13;";
                                }
                            }
                        }
                    }
                    break;
                case 4:
                    var diffDays = Math.ceil(Math.abs(today - modifyDate) / (1000 * 60 * 60 * 24)) + dateAdd;
                    if (fileType == 66701 || fileType == 66702) {
                        if (diffDays > 2) {
                            strWarning += "- Quá hạn xem xét&#13;";
                            flag = 1;
                        } else {
                            if (diffDays == 2) {
                                strWarning += "- Hôm nay là ngày cuối xem xét&#13;";
                            }
                        }
                    } else {
                        if (productTypeId == 3398258 || productTypeId == 3398259 || productTypeId == 3385104 || productTypeId == 66846) {
                            if (diffDays > 13) {
                                strWarning += "- Quá hạn xem xét&#13;";
                                flag = 1;
                            } else {
                                if (diffDays == 13) {
                                    strWarning += "- Hôm nay là ngày cuối xem xét&#13;";
                                }
                            }
                        } else {
                            if (fileType == 66746 || fileType == 66748) {
                                if (diffDays > 4) {
                                    strWarning += "- Quá hạn xem xét&#13;";
                                    flag = 1;
                                } else {
                                    if (diffDays == 4) {
                                        strWarning += "- Hôm nay là ngày cuối xem xét&#13;";
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 7:
                    var diffDays = Math.ceil(Math.abs(today - modifyDate) / (1000 * 60 * 60 * 24)) + dateAdd;
                    if (fileType == 66701 || fileType == 66702) {
                        if (diffDays > 2) {
                            strWarning += "- Quá hạn xem xét&#13;";
                            flag = 1;
                        } else {
                            if (diffDays == 2) {
                                strWarning += "- Hôm nay là ngày cuối xem xét&#13;";
                            }
                        }
                    } else {
                        if (productTypeId == 3398258 || productTypeId == 3398259 || productTypeId == 3385104 || productTypeId == 66846) {
                            if (diffDays > 13) {
                                strWarning += "- Quá hạn xem xét&#13;";
                                flag = 1;
                            } else {
                                if (diffDays == 13) {
                                    strWarning += "- Hôm nay là ngày cuối xem xét&#13;";
                                }
                            }
                        } else {
                            if (fileType == 66746 || fileType == 66748) {
                                if (diffDays > 4) {
                                    strWarning += "- Quá hạn xem xét&#13;";
                                    flag = 1;
                                } else {
                                    if (diffDays == 4) {
                                        strWarning += "- Hôm nay là ngày cuối xem xét&#13;";
                                    }
                                }
                            }
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
                case 6:
                    if (isFee != 1) {
                        strWarning += "- Chưa đóng phí hồ sơ đầy đủ&#13;";
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
                case 17:
                    var diffDays = Math.ceil(Math.abs(today - modifyDate) / (1000 * 60 * 60 * 24)) + dateAdd;
                    if (fileType == 66701 || fileType == 66702) {
                        if (diffDays > 2) {
                            strWarning += "- Quá hạn thẩm định&#13;";
                            flag = 1;
                        } else {
                            if (diffDays == 2) {
                                strWarning += "- Hôm nay là ngày cuối thẩm định&#13;";
                            }
                        }
                    } else {
                        if (productTypeId == 3398258 || productTypeId == 3398259 || productTypeId == 3385104 || productTypeId == 66846) {
                            if (diffDays > 7) {
                                strWarning += "- Quá hạn thẩm định&#13;";
                                flag = 1;
                            } else {
                                if (diffDays == 7) {
                                    strWarning += "- Hôm nay là ngày cuối thẩm định&#13;";
                                }
                            }
                        } else {
                            if (diffDays > 4) {
                                strWarning += "- Quá hạn thẩm định&#13;";
                                flag = 1;
                            } else {
                                if (diffDays == 4) {
                                    strWarning += "- Hôm nay là ngày cuối thẩm định&#13;";
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
                        <sd:Label key="Tên doanh nghiệp"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.businessName"
                                    key=""
                                    name="searchForm.businessName" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Địa chỉ doanh nghiệp"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.businessAddress"
                                    key=""
                                    name="searchForm.businessAddress" maxlength="250"/>
                    </td>                    
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Số điện thoại doanh nghiệp"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.businessTelephone"
                                    key=""
                                    name="searchForm.businessTelephone" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Fax doanh nghiệp"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.businessFax"
                                    key=""
                                    name="searchForm.businessFax" maxlength="250"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Xuất xứ và thương nhân chịu TN về CL hàng hóa"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.origin"
                                    key=""
                                    name="searchForm.origin" maxlength="250"/>
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
                        <sd:Label key="Số XNCB/TNCB"/>
                    </td>
                    <td>              
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.announcementNo"
                                    key=""
                                    name="searchForm.announcementNo" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Ngày cấp"/>
                    </td>
                    <td>
                        <sd:DatePicker cssStyle="width:100%"
                                       id="searchForm.receiptDate"
                                       key="" format="dd/MM/yyyy"
                                       name="searchForm.receiptDate"/>
                    </td>                    
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Cán bộ phân công hồ sơ"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.leaderAssignName"
                                    key=""
                                    name="searchForm.leaderAssignName" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Người thẩm định"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.nameStaffProcess"
                                    key=""
                                    name="searchForm.nameStaffProcess" maxlength="250"/>
                    </td>                    
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Lãnh đạo phòng duyệt"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.leaderReviewName"
                                    key=""
                                    name="searchForm.leaderReviewName" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Lãnh đạo cục duyệt"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.leaderApproveName"
                                    key=""
                                    name="searchForm.leaderApproveName" maxlength="250"/>                        
                    </td>
                </tr>                

                <tr>                    
                    <td align="right">
                        <sd:Label key="Nhóm sản phẩm"/>
                    </td>
                    <td>
                        <sd:SelectBox key="" id="searchForm.ProductTypeNew" name="searchForm.ProductTypeNew" cssStyle="width:100%">
                            <sd:Option value="-1" selected='true' >-- Chọn --</sd:Option>
                            <sd:Option value="1" >Nhóm thực phẩm thường</sd:Option>
                            <sd:Option value="2" >Các nhóm thực phẩm khác</sd:Option>
                        </sd:SelectBox>
                    </td>
                    <td align="right"><sd:Label key="Trạng thái"/></td>
                    <td>
                        <sd:SelectBox key="" id="searchForm.status" name="searchForm.status" cssStyle="width:100%">
                            <sd:Option value="-1" selected="true">-- Chọn --</sd:Option>
                            <sd:Option value="1">Mới nộp</sd:Option>
                            <sd:Option value="2">Đã được đề xuất xử lý</sd:Option>
                            <sd:Option value="3">Đã phân công xử lý</sd:Option>
                            <sd:Option value="4">Đã thẩm định</sd:Option>
                            <sd:Option value="5">Đã xem xét kết quả</sd:Option>
                            <sd:Option value="6">Đã phê duyệt kết quả</sd:Option>
                            <sd:Option value="15">Đã đối chiếu hồ sơ</sd:Option>
                            <sd:Option value="7">Chuyên viên KL: SĐBS</sd:Option>
                            <sd:Option value="8">Đã trả lại để thẩm định lại</sd:Option>
                            <sd:Option value="9">Đã trả lại để xem xét lại</sd:Option>
                            <sd:Option value="25">Chờ xem xét đối chiếu lại</sd:Option>
                            <sd:Option value="41">Thống kê trong ngày</sd:Option>
                            <sd:Option value="30">Sắp hết hạn bổ sung</sd:Option>
                            <sd:Option value="22">Đã trả bản công bố</sd:Option>
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
                    <td align="right">
                        <sd:Label key="Tìm theo từ"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.searchFullText"
                                    key=""
                                    name="searchForm.searchFullText" maxlength="250"/>
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
                            <sd:ColumnDataGrid key="category.No" get="page.getNo" width="3%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Chức năng" formatter="page.formatAction" get="page.getIndex"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:left;"/>
                            <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode" width="5%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Loại hồ sơ" field="fileTypeName"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên tổ chức, cá nhân" field="businessName" cellStyles="text-align:left;"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName" cellStyles="text-align:left;"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày nộp" field="sendDate" format="dd/MM/yyyy" type="date"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />                            
                            <sd:ColumnDataGrid  key="Ngày hẹn trả" field="deadlineApprove" format="dd/MM/yyyy" type="date"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Kết quả thẩm định" formatter="page.formatStatusView" get="page.getIndex"
                                                width="10%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                            <sd:ColumnDataGrid  key="Cán bộ xử lý chính" field="nameStaffProcess"  cellStyles="text-align:center;"
                                                width="7%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="Cảnh báo" headerStyles="text-align:center;" width="3%" cellStyles="text-align:center;"
                                               formatter="page.formatWarning" get="page.getIndex"/>
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>
<div id="createDiv"></div>
<%--<sd:Dialog  id="reviewDlg" height="auto" width="600px"
            key="Kết quả xem xét" showFullscreenButton="false"
            >
    <jsp:include page="../evaluation/reviewForm.jsp" flush="false"></jsp:include>
</sd:Dialog>--%>
<sd:Dialog  id="reviewAdditionDlg" height="auto" width="600px"
            key="Kết quả xem xét SĐBS" showFullscreenButton="false">
    <jsp:include page="../evaluation/additionReview/additionReviewFormForLookup.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="evaluateViewDlg" height="auto" width="600px"
            key="Kết quả Thẩm định/Đối chiếu" showFullscreenButton="false"
            >
    <jsp:include page="../evaluation/additionReview/additionEvaluateFormView.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="comparisonByLeaderOfStaffDlg" height="auto" width="600px"
            key="Xem xét kết quả đổi chiếu hồ sơ" showFullscreenButton="false"
            >
    <jsp:include page="../../files/comparison/leaderOfStaffComparisonDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>
<script type="text/javascript">
    var workingFileId;

    backPage = function () {
        document.getElementById("searchDiv").style.display = "";
        document.getElementById("createDiv").style.display = "none";
    };

    page.reset = function () {
        dijit.byId("searchForm.fileCode").setValue("");
        dijit.byId("searchForm.fileType").setValue("-1");
        dijit.byId("searchForm.leaderAssignId").setValue("-1");
        dijit.byId("searchForm.sendDateFrom").setValue("");
        dijit.byId("searchForm.sendDateTo").setValue("");
        dijit.byId("searchForm.searchFullText").setValue("");

        page.search();
    };

    page.search = function () {
        dijit.byId("filesGrid").vtReload('filesAction!onsearchLookupFilesByLeaderOfStaff.do?', "searchForm", null, page.afterSearch);
    };

    page.afterSearch = function () {
        dijit.byId("searchForm.flagSavePaging").setValue("0");
    };

    afterLoadForm = function (data) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
    };

    page.showViewFile = function (fileId) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + fileId + "&createForm.viewType=3", "createDiv", null, null, afterLoadForm);
    };
//    page.showViewFlow = function(fileId) {
//        var lookupProcessDlg = dijit.byId("lookupProcessDlg");
//        lookupProcessDlg.show();
//        page.getProcess(fileId);
//    };
    page.showEditFile = function (fileId) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
        sd.connector.post("filesAction!toCreateFilePage.do?createForm.fileId=" + fileId, "createDiv", null, null, afterLoadForm);
    };
    page.showDept = function (fileId) {
        workingFileId = fileId;
        dijit.byId("selectDeptDlg").show();
    };
    page.sendFile = function (fileId) {
        //page.loadFlow(fileId); 
        page.showDept(fileId);
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
//    page.showReviewForm = function(row) {//show form kết luận xem xét hồ sơ
//        var file = dijit.byId("filesGrid").getItem(row);
//        var statusName = page.getStatusName(parseInt(file.status));
//        dijit.byId("reviewForm.fileId").setValue(file.fileId);
//        document.getElementById("reviewForm.status").innerHTML = statusName;
//        document.getElementById("reviewForm.staffRequest").innerHTML = escapeHtml_(file.staffRequest);
//        dijit.byId("reviewForm.leaderStaffRequest").setValue("");
//        dijit.byId("reviewDlg").show();
//        page.replaceBrTblReviewForm();//on reviewForm.jsp
//    };
    page.showReviewAdditionForm = function (row) {//show form kết luận xem xét hồ sơ
        var file = dijit.byId("filesGrid").getItem(row);
        var statusName = page.getStatusName(parseInt(file.status));
        dijit.byId("evaluateFormForLookup.fileId").setValue(file.fileId);
        document.getElementById("evaluateFormForLookup.status").innerHTML = statusName;
        document.getElementById("evaluateFormForLookup.staffRequest").innerHTML = escapeHtml_(file.staffRequest);
        dijit.byId("evaluateFormForLookup.leaderStaffRequest").setValue("");
        dijit.byId("reviewAdditionDlg").show();
    };
    showEvaludateResult = function (row) {//show kết quả thẩm định
        var file = dijit.byId("filesGrid").getItem(row);
        var statusName = page.getStatusName(parseInt(file.status));
        document.getElementById("evaluateFormView.status").innerHTML = statusName;

        document.getElementById("evaluateFormView.staffRequest").innerHTML = file.staffRequest;
        document.getElementById("evaluateFormView.leaderStaffRequest").innerHTML = escapeHtml_(file.leaderStaffRequest);
        document.getElementById("evaluateFormView.leaderRequest").innerHTML = escapeHtml_(file.leaderRequest);
        page.replaceTblAdditionEvaluateFormView();
        dijit.byId("evaluateViewDlg").show();
    };
    page.reloadViewOldVersion = function (oldVersion, thisVersion) {//u140728
        document.getElementById('createDiv').Value = '';
        var gridRegister = dijit.byId('processCommentGridId_VTGrid');
        if (gridRegister) {
            gridRegister.destroyRecursive(true);
        }
        sd.connector.post("filesAction!loadFilesByOldVersion.do?thisVersion=" + thisVersion + "&oldVersion=" + oldVersion, "createDiv", null, null, afterReloadViewOldVersion);
    };
    afterReloadViewOldVersion = function (data) {
    };//!u140728
    page.formatLinkDownloadPdf = function (fileId) {
        document.location = "uploadiframe!openFileUserAttachPdf.do?fileId=" + fileId;
    };
    page.showLeaderOfStaffComparisonDlg = function (row) {//xem xet doi chieu ho so
        var item = dijit.byId("filesGrid").getItem(row);
        dijit.byId("comparisonForm.fileId").setValue(item.fileId);
        document.getElementById("comparisonForm.lastContent").innerHTML = item.comparisonContent;
        page.rplBrTblLeaderOfStaffComparisonDlg();
        dijit.byId("comparisonByLeaderOfStaffDlg").show();
    };
    page.downloadFileSign = function (fileId) {
        document.location = "uploadiframe!openFileUserUpload.do?fileId=" + fileId;
    };
    page.search();
</script>