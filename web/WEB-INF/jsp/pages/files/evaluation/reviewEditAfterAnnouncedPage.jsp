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

    page.getRow = function (inRow) {
        return inRow;
    };

    page.formatEdit = function (inData) {
        var url = "<div class='box' onclick='page.viewFile(" + inData + ");' />" + inData + "</div>";

        return url;
    };

    page.formatAction = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData);
        //var row = inData;
        if (item != null) {
            var status = parseInt(item.status);
            var fileId = item.fileId;
            var url = "<div style='text-align:center;cursor:pointer;display:inline'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + fileId + ");' />";
            switch (status) {
                case 4:
                    url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Xem xét hồ sơ' onClick='page.showReviewForm(" + fileId + "," + status + ");' />";
                    break;
                case 7:
                    url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Xem xét hồ sơ' onClick='page.showReviewForm(" + fileId + "," + status + ");' />";
                    break;
                case 9:
                    url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Xem xét hồ sơ' onClick='page.showReviewForm(" + fileId + "," + status + ");' />";
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
            default:
                url = "Mới tạo";
        }
        return url;
    };

    page.formatStatus = function (inData) {
        var row = inData;
        var item = dijit.byId("filesGrid").getItem(row);
        var url = "";
        if (item != null) {
//            var status = parseInt(item.status);
//            url = page.getStatusName(status);
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
    <sx:ResultMessage id="resultCreateMessage" />
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
                                      key="" data="lstFileType" valueField="procedureId" labelField="name" readonly="true"
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
                        <sd:TextBox key="" id="item.status" name="item.status" cssStyle="display:none" />
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
                            <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="2%" cellStyles="text-align:center;" setDisabled="page.disabledCheckbox"/>
                            <sd:ColumnDataGrid  key="Chức năng" formatter="page.formatAction" get="page.getRow"
                                                width="3%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                            <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode" width="7%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Loại hồ sơ" field="fileTypeName"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên tổ chức, cá nhân" field="businessName" cellStyles="text-align:left;"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName" cellStyles="text-align:left;"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày nộp" field="sendDate" format="dd/MM/yyyy" type="date"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                            <sd:ColumnDataGrid  key="Ngày đến" field="modifyDate" format="dd/MM/yyyy" type="date"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />           
                            <sd:ColumnDataGrid  key="Kết quả thẩm định" formatter="page.formatStatus" get="page.getRow"
                                                width="7%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                            <sd:ColumnDataGrid  key="Cán bộ xử lý chính" field="nameStaffProcess"  cellStyles="text-align:center;"
                                                width="7%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="Cảnh báo" headerStyles="text-align:center;" width="3%" cellStyles="text-align:center;"
                                               formatter="page.formatWarning" get="page.getIndex"/>
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:Button key="" onclick="page.onReviewManyFiles();" >
                        <img src="share/images/signature.png" height="14" width="18" alt="Hủy">
                        <span style="font-size:12px">Xem xét đạt nhiều hồ sơ</span>
                    </sd:Button>
                </td>

            </tr>
        </table>
    </sd:TitlePane>
</div>

<sd:Dialog  id="reviewDlg" height="auto" width="1200px"
            key="Kết quả xem xét" showFullscreenButton="false"
            >
    <jsp:include page="reviewEditAfterAnnouncedFormOnGrid.jsp" flush="false"></jsp:include>
</sd:Dialog>

<sd:Dialog  id="evaluateViewDlg" height="auto" width="600px"
            key="Kết quả thẩm định" showFullscreenButton="false"
            >
    <jsp:include page="evaluateFormView.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="reviewManyFilesForm" height="auto" width="600px"
            key="Xem xét nhiều hồ sơ" showFullscreenButton="false"
            >
    <jsp:include page="review/reviewManyFilesForm.jsp" flush="false"></jsp:include>
</sd:Dialog>
<div id="viewDiv"></div>

<script type="text/javascript">
    page.showReviewForm = function (fileId, status) {//show form kết luận xem xét hồ sơ
        dijit.byId("reviewForm.fileId").setValue(fileId);
        dijit.byId("item.status").setValue(status);
        sd.connector.post("filesAction!getCommentEvaluateFormByLeaderForAA.do?objectId=" + fileId, null, null, null, afterShowReviewForm);
    };
    afterShowReviewForm = function (data) {
        var obj = dojo.fromJson(data);
        if (obj.customInfo[0] != "") {
            document.getElementById("reviewForm.legalContentL").innerHTML = obj.customInfo[0];
        } else {
            document.getElementById("reviewForm.legalContentL").innerHTML = "";
        }
        if (obj.customInfo[1] != "") {
            document.getElementById("reviewForm.foodSafetyQualityContentL").innerHTML = obj.customInfo[1];
        } else {
            document.getElementById("reviewForm.foodSafetyQualityContentL").innerHTML = "";
        }
        if (obj.customInfo[2] != "") {
            document.getElementById("reviewForm.effectUtilityContentL").innerHTML = obj.customInfo[2];
        } else {
            document.getElementById("reviewForm.effectUtilityContentL").innerHTML = "";
        }
        if (obj.customInfo[3] != "") {
            document.getElementById("reviewForm.leaderStaffRequest").innerHTML = obj.customInfo[3];
        } else {
            document.getElementById("reviewForm.leaderStaffRequest").innerHTML = "";
        }
        if (obj.customInfo[4] != "") {
            document.getElementById("reviewForm.titleEditATTP").innerHTML = obj.customInfo[4];
        } else {
            document.getElementById("reviewForm.titleEditATTP").innerHTML = "";
        }
        if (obj.customInfo[5] != "") {
            document.getElementById("reviewForm.contentsEditATTP").innerHTML = obj.customInfo[5];
        } else {
            document.getElementById("reviewForm.contentsEditATTP").innerHTML = "";
        }
        
        var status = dijit.byId("item.status").getValue();
        var statusName = page.getStatusName(parseInt(status));

        document.getElementById("reviewForm.status").innerHTML = statusName;
        document.getElementById("reviewForm.staffRequest").innerHTML = escapeHtml_(obj.customInfo[3]);

        if (status == 4) {
            document.getElementById("reviewForm.statusAccept").checked = true;
        } else {
            document.getElementById("reviewForm.statusDeny").checked = true;
        }
        dijit.byId("reviewForm.legalL").setValue(1);
        dijit.byId("reviewForm.foodSafetyQualityL").setValue(1);
        dijit.byId("reviewForm.effectUtilityL").setValue(1);
        dijit.byId("reviewDlg").show();
        
        page.replaceBrTblReviewForm();//on reviewForm.jsp
        // Load view form
        var fileId = dijit.byId("reviewForm.fileId").getValue();
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + fileId + "&createForm.viewType=3&viewTypeDialog=1", "divViewFile", null, null, afterLoadViewFile);
    };

    afterLoadViewFile = function () {
        document.getElementById("trWaitViewFile").style.display = 'none';
    };

    backPage = function () {
        //doGoToMenu(g_latestClickedMenu);
        document.getElementById("searchDiv").style.display = "";
        document.getElementById("viewDiv").style.display = "none";

    };

    page.search = function () {
        dijit.byId("filesGrid").vtReload('filesAction!onSearchFileToReview.do', "searchForm");
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

    page.showViewFile = function (fileId) {//show hiển thị chi tiết hồ sơ
        doGoToMenu("filesAction!toFileDlgView.do?fileId=" + fileId + "&viewType=3" + "&backPage=5");
    };

    showEvaludateResult = function (row) {//show kết quả thẩm định
        var file = dijit.byId("filesGrid").getItem(row);
        var statusName = page.getStatusName(parseInt(file.status));
        document.getElementById("evaluateFormView.status").innerHTML = statusName;
        document.getElementById("evaluateFormView.staffRequest").innerHTML = escapeHtml_(file.staffRequest);
        document.getElementById("evaluateFormView.leaderStaffRequest").innerHTML = escapeHtml_(file.leaderStaffRequest);
        document.getElementById("evaluateFormView.leaderRequest").innerHTML = escapeHtml_(file.leaderRequest);
        page.replaceBrTblEvaluateFormView();
        dijit.byId("evaluateViewDlg").show();
    };

//    page.showReviewForm = function(row) {//show form kết luận xem xét hồ sơ
//        var file = dijit.byId("filesGrid").getItem(row);
//        var statusName = page.getStatusName(parseInt(file.status));
//        dijit.byId("reviewForm.fileId").setValue(file.fileId);
//        document.getElementById("reviewForm.status").innerHTML = statusName;
//        document.getElementById("reviewForm.staffRequest").innerHTML = escapeHtml_(file.staffRequest);
//        dijit.byId("reviewForm.leaderStaffRequest").setValue("");
//        document.getElementById("reviewForm.statusAccept").checked = true;
//
//        dijit.byId("reviewForm.legalL").setValue(1);
//        dijit.byId("reviewForm.foodSafetyQualityL").setValue(1);
//        dijit.byId("reviewForm.effectUtilityL").setValue(1);
//        dijit.byId("reviewDlg").show();
//        page.replaceBrTblReviewForm();//on reviewForm.jsp
//    };

    page.showSearchPanel = function () {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };
    page.downloadWord = function (fileId) {
        document.location = "exportWord!onExportEvaluation.do?fileId=" + fileId;
    };
    page.reloadViewOldVersion = function (oldVersion, thisVersion) {//u140728
        document.getElementById('viewDiv').Value = '';
        var gridRegister = dijit.byId('processCommentGridId_VTGrid');
        if (gridRegister) {
            gridRegister.destroyRecursive(true);
        }
        sd.connector.post("filesAction!loadFilesByOldVersion.do?thisVersion=" + thisVersion + "&oldVersion=" + oldVersion, "viewDiv", null, null, afterReloadViewOldVersion);
    };
    afterReloadViewOldVersion = function (data) {
    };//!u140728
    page.search();
    page.onReviewManyFiles = function () {
        if (!dijit.byId("filesGrid").vtIsChecked()) {
            msg.alert('Bạn chưa chọn hồ sơ để thực hiện xem xét nhiều hồ sơ', 'Cảnh báo');
        } else {
            dijit.byId("reviewManyFilesForm").show();
        }
    };
</script>
