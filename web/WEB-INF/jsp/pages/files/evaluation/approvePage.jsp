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
            var isSignPdf = parseInt(item.isSignPdf);
            var url = "<div style='text-align:center;cursor:pointer;display:inline'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + item.fileId + ");' />";
            switch (status) {
//                case 5:
//                    url += " | <img src='share/images/signature.png' width='20px' height='20px' title='Phê duyệt hồ sơ' onClick='page.showApproveForm(" + row + ");' />";
//                    break;
                case 6:
                    if ((item.announcementReceiptPaperId != null && item.announcementReceiptPaperId > 0) || (item.confirmImportSatistPaperId != null && item.confirmImportSatistPaperId > 0)) {
                        url += " | <img src='share/images/Document.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.downloadWord(" + item.fileId + ");' />";
                    }
                    if ((isSignPdf == 1 || isSignPdf == 2) && item.isDownload == 1) {
                        url += " | <img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.formatLinkDownloadPdf(" + item.fileId + ");' />";
                    }
                    break;
                case 5:
                    // nut ky goc hieptq
                    //url += " | <img src='share/images/signature.png' width='20px' height='20px' title='Phê duyệt hồ sơ' onClick='page.showApproveForm(" + row + ");' />";

                    url += " | <img src='share/images/signature.png' width='20px' height='20px' title='Phê duyệt hồ sơ' onClick='page.showApproveFormPlugin(" + row + ");' />";
                    //hieptq xuat ho so dat

                    break;
                case 26:
                    url += " | <img src='share/images/signature.png' width='20px' height='20px' title='Phê duyệt hồ sơ' onClick='page.showApproveForm(" + row + ");' />";
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

    // enter key
    page.loginUsbCaDefault = function (evt) {
        var dk = dojo.keys;
        switch (evt.keyCode) {
            case dk.ENTER:
                page.loginUsbCa();
                break;
        }
    };

    dojo.connect(dojo.byId("searchForm"), "onkeypress", page.searchDefault);
    dojo.connect(dojo.byId("loginCAForm"), "onkeypress", page.loginUsbCaDefault);

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
                        <sd:SelectBox cssStyle="width:100%"
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
                            <sd:ColumnDataGrid key="STT" get="page.getNo" width="5%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="3%" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Chức năng" formatter="page.formatAction" get="page.getIndex"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
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
                            <sd:ColumnDataGrid editable="true" key="Cảnh báo" headerStyles="text-align:center;" width="3%" cellStyles="text-align:center;"
                                               formatter="page.formatWarning" get="page.getIndex"/>
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
            <tr id="trSignMore">
                <td>
                    <sd:Button id="btnSignMore" key="" onclick="page.showSignMore()">
                        <img src="${contextPath}/share/images/signature.png" height="20" width="20"/>
                        <span style="font-size:12px" title="Cho phép ký phê duyệt nhiều hồ sơ, vui lòng tích chọn hồ sơ cần phê duyệt trước khi thực hiện">Phê duyệt nhiều hồ sơ</span>
                    </sd:Button>
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>

<%--<sd:Dialog  id="approveDlg" height="auto" width="1200px"
            key="Nhập kết quả phê duyệt" showFullscreenButton="false"
            >
    <jsp:include page="approveForm.jsp" flush="false"></jsp:include>
</sd:Dialog>--%>

<sd:Dialog  id="approveDlg" height="auto" width="1200px"
            key="Nhập kết quả phê duyệt" showFullscreenButton="false"
            >
    <jsp:include page="approveFormPlugin.jsp" flush="false"></jsp:include>
</sd:Dialog>

<sd:Dialog  id="evaluateViewDlg" height="auto" width="600px"
            key="Kết quả xem xét" showFullscreenButton="false"
            >
    <jsp:include page="reviewFormView.jsp" flush="false"></jsp:include>
</sd:Dialog>
<script type="text/javascript">

    var flagSignMore = false;
    var itemsToSign = null;
    var signIndex = 0;

    backPage = function () {
        //doGoToMenu(g_latestClickedMenu);
        document.getElementById("searchDiv").style.display = "";
        document.getElementById("viewDiv").style.display = "none";
    };

    page.search = function () {
        page.setSearch();
        dijit.byId("filesGrid").vtReload('filesAction!onSearchFileToApprove.do', "searchForm", null, page.afterSearch);
    };

    page.afterSearch = function () {
        dijit.byId("searchForm.flagSavePaging").setValue("0");
    }

    afterLoadForm = function (data) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("viewDiv").style.display = "";
    };

    page.showViewFile = function (fileId) {
        doGoToMenu("filesAction!toFileDlgView.do?fileId=" + fileId + "&viewType=2" + "&backPage=8");
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
        document.getElementById("trWaitViewFile").style.display = '';
        page.onSign(file, false);
        //dijit.byId("approveDlg").show();
        dijit.byId("approveDlg").show();
    };

    page.showApproveFormPlugin = function (row) {
        var file = dijit.byId("filesGrid").getItem(row);
        var statusName = page.getStatusName(parseInt(file.status));
        dijit.byId("approveForm.fileId").setValue(file.fileId);
        document.getElementById("approveForm.status").innerHTML = statusName;
        document.getElementById("approveForm.businessName").innerHTML = escapeHtml_(file.businessName);
        document.getElementById("approveForm.productName").innerHTML = escapeHtml_(file.productName);
        document.getElementById("approveForm.staffRequest").innerHTML = escapeHtml_(file.staffRequest);
        document.getElementById("approveForm.leaderStaffRequest").innerHTML = escapeHtml_(file.leaderStaffRequest);
        dijit.byId("approveForm.leaderRequest").setValue("");
        page.hideContentAF();//a150108 binhnt53
        page.replaceBrTblApproveForm();
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + file.fileId + "&createForm.viewType=2&viewTypeDialog=1", "divViewFile", null, null, afterShowApproveViewFile);
        document.getElementById("trWaitViewFile").style.display = 'none';
        dijit.byId("approveDlg").show();
    };

    page.onSign = function (file, isSignMore) {
        flagSignMore = isSignMore;
        var statusName = page.getStatusName(parseInt(file.status));
        dijit.byId("approveForm.fileId").setValue(file.fileId);
        document.getElementById("approveForm.status").innerHTML = statusName;
        document.getElementById("approveForm.businessName").innerHTML = escapeHtml_(file.businessName);
        document.getElementById("approveForm.productName").innerHTML = escapeHtml_(file.productName);
        document.getElementById("approveForm.staffRequest").innerHTML = escapeHtml_(file.staffRequest);
        document.getElementById("approveForm.leaderStaffRequest").innerHTML = escapeHtml_(file.leaderStaffRequest);
        dijit.byId("approveForm.leaderRequest").setValue("");
//        document.getElementById("trWait").style.display = "";
        page.hideContentAF();//a150108 binhnt53
        page.replaceBrTblApproveForm();
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + file.fileId + "&createForm.viewType=2&viewTypeDialog=1", "divViewFile", null, null, afterShowApproveViewFile);
    };

    page.showSignMore = function () {
        if (!dijit.byId("filesGrid").vtIsChecked()) {
            msg.alert('Bạn chưa chọn hồ sơ để thực hiện phê duyệt', 'Cảnh báo');
        }
        else {
            itemsToSign = dijit.byId("filesGrid").vtGetCheckedItems();
            signIndex = 0;
            msg.confirm('Bạn có chắc chắn muốn phê duyệt nhiều hồ sơ?', '<sd:Property>confirm.title1</sd:Property>', page.signMoreFilesPlugin);
                    }
                }

//                page.signMoreFiles = function() {
//                    document.getElementById('divProcess').innerHTML = "Hệ thống đang thực hiện phê duyệt:" + (signIndex + 1) + "/" + itemsToSign.length + " hồ sơ  ";
//                    document.getElementById("divSignProcess").style.display = "";
//                    document.getElementById("trWaitViewFile").style.display = 'none';
//
//                    page.onSign(itemsToSign[signIndex], true);
//                    document.getElementById("approveForm.statusAccept").checked = true;
//                    dijit.byId("approveDlg").show();
//                };


                //hieptq update 191015
                page.signMoreFilesPlugin = function () {
                    document.getElementById('divProcess').innerHTML = "Hệ thống đang thực hiện phê duyệt:" + (signIndex + 1) + "/" + itemsToSign.length + " hồ sơ  ";
                    document.getElementById("divSignProcess").style.display = "";
                    document.getElementById("trWaitViewFile").style.display = 'none';

                    page.onSign(itemsToSign[signIndex], true);
                    document.getElementById("approveForm.statusAccept").checked = true;
                    dijit.byId("approveDlg").show();
                };

                afterShowApproveViewFile = function () {
                    document.getElementById("trWaitViewFile").style.display = 'none';
                    if (flagSignMore)
                    {
                        page.onApproveSignPlugin();
                    }
                };

                page.showSearchPanel = function () {
                    var panel = document.getElementById("searchDiv");
                    panel.setAttribute("style", "display:;");
                    dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
                };

                page.downloadWord = function (fileId) {
                    document.location = "exportWord!onExportPaper.do?fileId=" + fileId;
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
                    dijit.byId('searchForm.Staff').attr('value', '');
                    dijit.byId('searchForm.fileType').attr('value', '-1');

                    page.clearSearch();
                    page.search();
                };

                page.clearSearch = function () {
                    try
                    {
                        localStorage.setItem("approvePage.searchForm.fileCode", "");
                        localStorage.setItem("approvePage.searchForm.announcementNo", "");
                        localStorage.setItem("approvePage.searchForm.businessName", "");
                        localStorage.setItem("approvePage.searchForm.businessLicence", "");
                        localStorage.setItem("approvePage.searchForm.businessAddress", "");
                        localStorage.setItem("approvePage.searchForm.productName", "");
                        localStorage.setItem("approvePage.searchForm.nationName", "");
                        localStorage.setItem("approvePage.searchForm.manufactureName", "");
                        localStorage.setItem("approvePage.searchForm.manufactureAddress", "");
                        localStorage.setItem("approvePage.searchForm.matchingTarget", "");
                        localStorage.setItem("approvePage.searchForm.Staff", "");
                        localStorage.setItem("approvePage.searchForm.fileType", "-1");
                    }
                    catch (err)
                    {
                        // nothing
                    }
                };

                page.setSearch = function () {
                    try
                    {
                        localStorage.setItem("approvePage.searchForm.fileCode", encodeBase64(dijit.byId("searchForm.fileCode").getValue().toString().trim()));
                        localStorage.setItem("approvePage.searchForm.announcementNo", encodeBase64(dijit.byId("searchForm.announcementNo").getValue().toString().trim()));
                        localStorage.setItem("approvePage.searchForm.businessName", encodeBase64(dijit.byId("searchForm.businessName").getValue().toString().trim()));
                        localStorage.setItem("approvePage.searchForm.businessLicence", encodeBase64(dijit.byId("searchForm.businessLicence").getValue().toString().trim()));
                        localStorage.setItem("approvePage.searchForm.businessAddress", encodeBase64(dijit.byId("searchForm.businessAddress").getValue().toString().trim()));
                        localStorage.setItem("approvePage.searchForm.productName", encodeBase64(dijit.byId("searchForm.productName").getValue().toString().trim()));
                        localStorage.setItem("approvePage.searchForm.nationName", encodeBase64(dijit.byId("searchForm.nationName").getValue().toString().trim()));
                        localStorage.setItem("approvePage.searchForm.manufactureName", encodeBase64(dijit.byId("searchForm.manufactureName").getValue().toString().trim()));
                        localStorage.setItem("approvePage.searchForm.manufactureAddress", encodeBase64(dijit.byId("searchForm.manufactureAddress").getValue().toString().trim()));
                        localStorage.setItem("approvePage.searchForm.matchingTarget", encodeBase64(dijit.byId("searchForm.matchingTarget").getValue().toString().trim()));
                        localStorage.setItem("approvePage.searchForm.Staff", encodeBase64(dijit.byId("searchForm.Staff").getValue().toString().trim()));
                        localStorage.setItem("approvePage.searchForm.fileType", encodeBase64(dijit.byId("searchForm.fileType").getValue()));
                    }
                    catch (err)
                    {
                        // nothing
                    }
                };
                page.getSearch = function () {
                    try
                    {
                        dijit.byId("searchForm.fileCode").setValue(decodeBase64(localStorage.getItem("approvePage.searchForm.fileCode")));
                        dijit.byId("searchForm.announcementNo").setValue(decodeBase64(localStorage.getItem("approvePage.searchForm.announcementNo")));
                        dijit.byId("searchForm.businessName").setValue(decodeBase64(localStorage.getItem("approvePage.searchForm.businessName")));
                        dijit.byId("searchForm.businessLicence").setValue(decodeBase64(localStorage.getItem("approvePage.searchForm.businessLicence")));
                        dijit.byId("searchForm.businessAddress").setValue(decodeBase64(localStorage.getItem("approvePage.searchForm.businessAddress")));
                        dijit.byId("searchForm.productName").setValue(decodeBase64(localStorage.getItem("approvePage.searchForm.productName")));
                        dijit.byId("searchForm.nationName").setValue(decodeBase64(localStorage.getItem("approvePage.searchForm.nationName")));
                        dijit.byId("searchForm.manufactureName").setValue(decodeBase64(localStorage.getItem("approvePage.searchForm.manufactureName")));
                        dijit.byId("searchForm.manufactureAddress").setValue(decodeBase64(localStorage.getItem("approvePage.searchForm.manufactureAddress")));
                        dijit.byId("searchForm.matchingTarget").setValue(decodeBase64(localStorage.getItem("approvePage.searchForm.matchingTarget")));
                        dijit.byId("searchForm.Staff").setValue(decodeBase64(localStorage.getItem("approvePage.searchForm.Staff")));
                        dijit.byId("searchForm.fileType").setValue(decodeBase64(localStorage.getItem("approvePage.searchForm.fileType")));
                    }
                    catch (err)
                    {
                        // nothing
                    }
                };

                page.hideSignMore = function ()
                {
                    var searchType = dijit.byId("searchForm.searchType").getValue();
                    if (searchType != null && searchType == "-3")
                    {
                        document.getElementById("trSignMore").style.display = "";
                    }
                    else
                    {
                        document.getElementById("trSignMore").style.display = "none";
                    }
                }
                //page.hideSignMore();
                page.getSearch();
                page.search();
//a150108 binhnt53
                page.hideContentAF = function () {
                    document.getElementById("approveForm.statusAccept").checked = false;
                    document.getElementById("approveForm.statusDeny").checked = false;
                    document.getElementById("approveForm.statusCT").checked = false;
                    dijit.byId("btnStatusAcceptAF").domNode.style.display = "none";
                    dijit.byId("btnStatusDenyAF").domNode.style.display = "none";
                    dijit.byId("btnExportAFCV").domNode.style.display = "none";
                    dijit.byId("btnStatusCTAF").domNode.style.display = "none";
                };
//!a150108 binhnt53
</script>
