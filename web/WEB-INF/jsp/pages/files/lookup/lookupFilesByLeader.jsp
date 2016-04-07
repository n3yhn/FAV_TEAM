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
    page.getNo = function(index) {
        return dijit.byId("filesGrid").currentRow + index + 1;
    };

    page.getIndex = function(index) {
        return index + 1;
    };

    page.formatAction = function(inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var row = inData - 1;
        var url = "";
        if (item != null) {
            var status = parseInt(item.status);
            url = "<div style='text-align:center;cursor:pointer;display:inline'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + item.fileId + ");' />";
            if (item.userSigned == "fileUploaded")
            {
                url += " | <img src='share/images/icons/kdevelop_down.png' width='17px' height='17px' title='Hồ sơ đã upload' onClick='page.downloadFileSign(" + item.fileId + ");' />";
            }
            switch (status) {
                case 5:
                    //url += " | <img src='share/images/signature.png' width='17px' height='17px' title='Phê duyệt hồ sơ' onClick='page.showEvaluateForm(" + row + ");' />";
                    break;
                case 26:
                    //url += " | <img src='share/images/signature.png' width='20px' height='20px' title='Phê duyệt công văn SĐBS' onClick='page.showFeedbackApproveForm(" + row + ");' />";
                    break;
                case 6:
                    if ((item.announcementReceiptPaperId != null
                            && item.announcementReceiptPaperId > 0)
                            || (item.confirmImportSatistPaperId != null && item.confirmImportSatistPaperId > 0)
                            && (item.status == 24 || item.status == 25 || item.status == 15 || item.status == 16 || item.status == 23 || item.status == 22)) {
                        if ((item.isSignPdf == 1 || item.isSignPdf == 2) && item.isDownload == 1)
                        {
                            url += " | <img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.formatLinkDownloadPdf(" + item.fileId + ");' />";
                        }
                        if (item.isSignPdf != 1 && item.isSignPdf != 2) {
                            url += " | <img src='share/images/icons/UpArrow.png' width='17px' height='17px' title='Upload' onClick='page.UploadFileSign(" + row + ");' />";
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

    page.formatStatus = function(inData) {
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
                case 42:
                    url = "Thống kê trong ngày";
                    break;
                default:
                    url = "Mới tạo";
            }
        }

        return url;
    };

    page.formatWarning = function(inData) {//binhnt53 141208
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
    page.searchDefault = function(evt) {
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
                    <td align="right">
                        <sd:Label key="Tên doanh nghiệp"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.businessName"
                                    key=""
                                    name="searchForm.businessName" maxlength="250"/>
                    </td>
                    <td align="right"><sd:Label key="Trạng thái"/></td>
                    <td>
                        <sd:SelectBox key="" id="searchForm.status" name="searchForm.status" cssStyle="width:100%">
                            <sd:Option value="-1">-- Chọn --</sd:Option>
                            <sd:Option value="1">Mới nộp</sd:Option>
                            <sd:Option value="2">Đã được đề xuất xử lý</sd:Option>
                            <sd:Option value="3">Đã phân công xử lý</sd:Option>
                            <sd:Option value="4">Đã thẩm định</sd:Option>
                            <sd:Option value="5">Đã xem xét kết quả</sd:Option>
                            <sd:Option value="6">Đã phê duyệt kết quả</sd:Option>
                            <sd:Option value="15">Đã đối chiếu</sd:Option>
                            <sd:Option value="16">Đã đối chiếu có sai lệch</sd:Option>
                            <sd:Option value="7">Chuyên viên KL: SĐBS</sd:Option>
                            <sd:Option value="8">Đã trả lại để thẩm định lại</sd:Option>
                            <sd:Option value="9">Đã trả lại để xem xét lại</sd:Option>
                            <sd:Option value="22">Đã trả giấy công bố</sd:Option>
                            <sd:Option value="25">Hồ sơ đã trả đối chiếu lại</sd:Option>
                            <sd:Option value="42">Thống kê trong ngày</sd:Option>
                        </sd:SelectBox>

                    </td>
                </tr>
                <tr>
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
                                                width="7%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>                       
                            <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode" width="7%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Loại hồ sơ" field="fileTypeName"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên tổ chức, cá nhân" field="businessName" cellStyles="text-align:left;"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName" cellStyles="text-align:left;"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày nộp" field="sendDate" format="dd/MM/yyyy" type="date"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />                            
                            <sd:ColumnDataGrid  key="Ngày hẹn trả" field="deadlineApprove" format="dd/MM/yyyy" type="date"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Trạng thái hồ sơ"  cellStyles="text-align:left;" field="displayStatus"
                                                width="5%"  headerStyles="text-align:center;" />
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
<%--
<sd:Dialog  id="feedbackApproveFormDlg" height="auto" width="600px"
            key="Phê duyệt nội dung công văn SĐBS" showFullscreenButton="false"
            >
    <jsp:include page="../evaluation/feedbackEvaluate/feedbackApproveForm.jsp" flush="false"></jsp:include>
</sd:Dialog>
--%>
<sd:Dialog  id="upload" height="auto" width="400px"
            key="Upload hồ sơ lãnh đạo đã ký số" showFullscreenButton="false"
            >
    <jsp:include page="signUploadPdf.jsp" flush="false"></jsp:include>
</sd:Dialog>
<div id="createDiv"></div>
<script type="text/javascript">
    var workingFileId;

    backPage = function() {
        document.getElementById("searchDiv").style.display = "";
        document.getElementById("createDiv").style.display = "none";
    };

    //hieptq update 111214
    page.UploadFileSign = function(row)
    {
        var item = dijit.byId("filesGrid").getItem(row);
        workingFileId = item.fileId;
        clearAttFile("sendForm.attachFileNew");
        dijit.byId("upload").show();
    };
    page.reset = function() {
        dijit.byId("searchForm.fileCode").setValue("");
        dijit.byId("searchForm.fileType").setValue("-1");
        dijit.byId("searchForm.sendDateFrom").setValue("");
        dijit.byId("searchForm.sendDateTo").setValue("");
        dijit.byId("searchForm.businessName").setValue("");
        dijit.byId("searchForm.nameStaffProcess").setValue("");
        dijit.byId("searchForm.status").setValue("-1");

        page.clearSearch();
        page.search();
    };

    page.clearSearch = function() {
        try
        {
            localStorage.setItem("lookupFilesByLeader.searchForm.fileCode", "");
            localStorage.setItem("lookupFilesByLeader.searchForm.fileType", "-1");
            localStorage.setItem("lookupFilesByLeader.searchForm.sendDateFrom", "");
            localStorage.setItem("lookupFilesByLeader.searchForm.sendDateTo", "");
            localStorage.setItem("lookupFilesByLeader.searchForm.businessName", "");
            localStorage.setItem("lookupFilesByLeader.searchForm.status", "-1");
            localStorage.setItem("lookupFilesByLeader.searchForm.nameStaffProcess", "");
        }
        catch (err)
        {
            // nothing
        }
    };

    page.setSearch = function() {
        try
        {
            localStorage.setItem("lookupFilesByLeader.searchForm.fileCode", encodeBase64(dijit.byId("searchForm.fileCode").getValue().toString().trim()));
            localStorage.setItem("lookupFilesByLeader.searchForm.fileType", encodeBase64(dijit.byId("searchForm.fileType").getValue()));
            localStorage.setItem("lookupFilesByLeader.searchForm.sendDateFrom", dijit.byId("searchForm.sendDateFrom").getValue());
            localStorage.setItem("lookupFilesByLeader.searchForm.sendDateTo", dijit.byId("searchForm.sendDateTo").getValue());
            localStorage.setItem("lookupFilesByLeader.searchForm.businessName", encodeBase64(dijit.byId("searchForm.businessName").getValue().toString().trim()));
            localStorage.setItem("lookupFilesByLeader.searchForm.status", encodeBase64(dijit.byId("searchForm.status").getValue()));
            localStorage.setItem("lookupFilesByLeader.searchForm.nameStaffProcess", encodeBase64(dijit.byId("searchForm.nameStaffProcess").getValue().toString().trim()));
        }
        catch (err)
        {
            // nothing
        }
    };

    page.getSearch = function() {
        try
        {
            dijit.byId("searchForm.fileCode").setValue(decodeBase64(localStorage.getItem("lookupFilesByLeader.searchForm.fileCode")));
            dijit.byId("searchForm.fileType").setValue(decodeBase64(localStorage.getItem("lookupFilesByLeader.searchForm.fileType")));
            var sendDateFrom = getCookie("lookupFilesByLeader.searchForm.sendDateFrom");
            var sendDateTo = getCookie("lookupFilesByLeader.searchForm.sendDateTo");
            if (sendDateFrom != null && sendDateFrom.toString() != "null" && sendDateFrom.toString() != "")
                dijit.byId("searchForm.sendDateFrom").setValue(new Date(sendDateFrom));
            if (sendDateTo != null && sendDateTo.toString() != "null" && sendDateTo.toString() != "")
                dijit.byId("searchForm.sendDateTo").setValue(new Date(sendDateTo));
            dijit.byId("searchForm.businessName").setValue(decodeBase64(localStorage.getItem("lookupFilesByLeader.searchForm.businessName")));
            dijit.byId("searchForm.status").setValue(decodeBase64(localStorage.getItem("lookupFilesByLeader.searchForm.status")));
            dijit.byId("searchForm.nameStaffProcess").setValue(decodeBase64(localStorage.getItem("lookupFilesByLeader.searchForm.nameStaffProcess")));
        }
        catch (err)
        {
            // nothing
        }
    };

    page.search = function() {
        page.setSearch();
        dijit.byId("filesGrid").vtReload('filesAction!onsearchLookupFilesByLeader.do?', "searchForm", null, page.afterSearch);
    };

    page.afterSearch = function() {
        dijit.byId("searchForm.flagSavePaging").setValue("0");
    }

    afterLoadForm = function(data) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
    };

    page.showViewFile = function(fileId) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + fileId + "&createForm.viewType=2", "createDiv", null, null, afterLoadForm);
    };
//    page.showViewFlow = function(fileId) {
    //        var lookupProcessDlg = dijit.byId("lookupProcessDlg");
    //        lookupProcessDlg.show();
//        page.getProcess(fileId);
//    };

    page.showEditFile = function(fileId) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
        sd.connector.post("filesAction!toCreateFilePage.do?createForm.fileId=" + fileId, "createDiv", null, null, afterLoadForm);
    };

    page.showDept = function(fileId) {
        workingFileId = fileId;
        dijit.byId("selectDeptDlg").show();
    };

    page.sendFile = function(fileId) {
        //page.loadFlow(fileId); 
        page.showDept(fileId);
    };

    //    page.push = function() {
    //        document.getElementById('showContent').innerHTML = page.getHtml();
    //        document.getElementById("showContent").style.display = "";
    //        //dijit.byId("ssignpdf").show();
//        //page.upload();
//
//    };

    page.export = function(fileId) {
        //dijit.byId("upload").show();
        //page.loadInitAttachs();
        //document.getElementById('divAttachTbl').innerHTML = "";
        //var content = "<table id='attachTbl' class='lstTable'><tr class='headerRow'><th width='5%'>STT</th><th width='20%'>Tên tài liệu<font style='color:red'>*</font></th><th width='40%'>Mô tả tài liệu</th><th width='20%'>Tải về</th><th width='10%'>Đính kèm tệp<font style='color:red'>*</font></th><th width='5%'>Xóa</th></tr></table>";
        //        document.getElementById('divAttachTbl').innerHTML = content;
        //        createNewRowOfAttach('', '', '', "", 'attachTbl');
        sd.connector.post("filesAction!toDownloadPage.do?createForm.fileId=" + fileId, "createDiv", null, null, afterDownload);
    };

    afterDownload = function(data)
    {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "1") {

        } else {
            msg.alert("Giấy công bố chưa ký xác thực");
        }
    }
    //    page.getHtml = function ()
    //    {
    //        var item = '<applet code="com.viettel.QLLLTP.ca.applet.DataSignApplet" archive="PdfSign.jar" width="420" height="180" />';
    //        return item;
//    };

    page.loadView = function() {
        page.clearInsertForm();
        dijit.byId("createDlg").show();
    };

    page.showSearchPanel = function() {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };

    page.reloadViewOldVersion = function(oldVersion, thisVersion) {//u140728
        document.getElementById('createDiv').Value = '';
        var gridRegister = dijit.byId('processCommentGridId_VTGrid');
        if (gridRegister) {
            gridRegister.destroyRecursive(true);
        }
        sd.connector.post("filesAction!loadFilesByOldVersion.do?thisVersion=" + thisVersion + "&oldVersion=" + oldVersion, "createDiv", null, null, afterReloadViewOldVersion);
    };
    afterReloadViewOldVersion = function(data) {
    };//!u140728

    page.formatLinkDownloadPdf = function(fileId) {
        document.location = "uploadiframe!openFileUserAttachPdf.do?fileId=" + fileId;
    };
    page.submitSignPdf = function()
    {
        dijit.byId("searchForm.status").setValue("6");
        dijit.byId("filesGrid").vtReload('filesAction!onsearchLookupFilesByLeader.do?', "searchForm");
    };
    page.downloadFileSign = function(fileId) {
        document.location = "uploadiframe!openFileUserUpload.do?fileId=" + fileId;
    };
    //--111014
    page.showFeedbackApproveForm = function(row) {
        var file = dijit.byId("filesGrid").getItem(row);
        //        var isTypeChange = file.isTypeChange;
//        if (isTypeChange == '1') {
        //            dijit.byId("ckbIsTypeChangeLDC").attr("checked", "on");
//        } else {
//            dijit.byId("ckbIsTypeChangeLDC").attr("checked", "");
//        }
        dijit.byId("feedbackApproveForm.fileId").setValue(file.fileId);
        document.getElementById("trWait").style.display = "";
        document.getElementById("feedbackApproveForm.businessName").innerHTML = escapeHtml_(file.businessName);
        document.getElementById("feedbackApproveForm.productName").innerHTML = escapeHtml_(file.productName);
        sd.connector.post("filesAction!getCommentFeedbackApprove.do?objectId=" + file.fileId, null, null, null, afterShowFeedbackApproveForm);
    };
    afterShowFeedbackApproveForm = function(data) {
        var obj = dojo.fromJson(data);
        if (obj.customInfo[0] != "") {
            dijit.byId("feedbackApproveForm.leaderRequest").setValue(obj.customInfo[0]);
        } else {
            dijit.byId("feedbackApproveForm.leaderRequest").setValue("");
        }
        document.getElementById("trWait").style.display = "none";
        dijit.byId("feedbackApproveFormDlg").show();
    };
    dijit.byId("searchForm.fileType").setValue("-1");
    dijit.byId("searchForm.status").setValue("-1");
    page.getSearch();
    page.search();
</script>
