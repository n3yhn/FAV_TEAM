<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<object id="plugin0" type="application/x-viettelcasigner" width="3" height="10">
</object>
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
        var row = inData - 1;
        var url = "";
        if (item != null) {
            var status = parseInt(item.status);
            var isFee = parseInt(item.isFee);
            var isSignPdf = parseInt(item.isSignPdf);
            url = "<div style='text-align:center;cursor:pointer;'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + item.fileId + ");' />";
            /*
             if (item.userSigned == "fileUploaded")
             {
             url += " | <img src='share/images/icons/kdevelop_down.png' width='17px' height='17px' title='Hồ sơ đã upload' onClick='page.downloadFileSign(" + item.fileId + ");' />";
             }
             */
            switch (status) {
                case 6:
                    /*
                     if (isSignPdf == 2 && item.isDownload == 1) {
                     url += " | <img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.formatLinkDownloadPdf(" + item.fileId + ");' />";
                     }
                     if (isSignPdf == 1) {
                     if (isFee == 1) {
                     url += " | <img src='share/images/signature.png' width='20px' height='20px' title='Ký số phê duyệt hồ sơ' onClick='page.onApproveSign(" + item.fileId + ");' />";
                     if (item.isDownload == 1) {
                     url += " | <img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố lãnh đạo cục đã kí duyệt' onClick='page.formatLinkDownloadFileLDCSignApprove(" + item.fileId + ");' />";
                     }
                     url += " | <img src='share/images/icons/UpArrow.png' width='17px' height='17px' title='Upload' onClick='page.UploadFileSignVT(" + row + ");' />";
                     }
                     }
                     if (isSignPdf == 2)
                     {
                     if (isFee == 1) {
                     url += " | <img src='share/images/alertXacnhan.png' width='17px' height='17px' title='Thông báo đối chiếu hồ sơ' onClick='page.alertComparison(" + row + ");' />";
                     }
                     }
                     */
                    break;
                case 22:
                    /*
                     if (item.isDownload == 1) {
                     url += " | <img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.formatLinkDownloadPdf(" + item.fileId + ");' />";
                     }
                     */
                    break;
                case 15:
                    /*
                     if (isSignPdf == 2 && item.isDownload == 1) {
                     url += " | <img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.formatLinkDownloadPdf(" + item.fileId + ");' />";
                     }
                     */
                    break;
                case 16:
//                    url += " | <img src='share/images/alertXacnhan.png' width='17px' height='17px' title='Đối chiếu hồ sơ' onClick='page.showComparisonDlg(" + row + ");' />";
                    break;
                case 23:
//                    url += " | <img src='share/images/alertXacnhan.png' width='17px' height='17px' title='Đối chiếu hồ sơ' onClick='page.showComparisonDlg(" + row + ");' />";
                    break;
                case 27:
//                    url += " | <img src='share/images/signature.png' width='20px' height='20px' title='Ký số công văn SĐBS' onClick='page.onFeedbackGiveBack(" + item.fileId + ");' />";
                    break;
                case 20:
//                    url += " | <img src='share/images/Document.png' width='20px' height='20px' title='Xuất công văn SĐBS' onClick='page.onExportGiveBackFile(" + item.fileId + ");' />";
                    break;
            }
            url += "</div>";
        }

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
                case 15:
                    url = "Đã đối chiếu";
                    break;
                case 16:
                    url = "Đã đối chiếu, có sai lệch";
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

    page.searchDefault = function (evt) {//enter key
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
            <sd:TextBox key="" id="searchForm.searchTypeNew" name="searchForm.searchTypeNew" cssStyle="display:none" />
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
                                    name="searchForm.fileCode"
                                    maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Tên loại hồ sơ"/>
                    </td>
                    <td>
                        <sd:SelectBox cssStyle="width:100%"
                                      id="searchForm.fileType"
                                      key="" data="lstFileType"
                                      valueField="procedureId"
                                      labelField="name"
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
                        <sd:Label key="Đóng dấu số từ ngày"/>
                    </td>
                    <td>
                        <sd:DatePicker cssStyle="width:100%"
                                       id="searchForm.signDateFrom"
                                       key="" format="dd/MM/yyyy"
                                       name="searchForm.signDateFrom"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày"/>
                    </td>
                    <td>
                        <sd:DatePicker cssStyle="width:100%"
                                       id="searchForm.signDateTo"
                                       key="" format="dd/MM/yyyy"
                                       name="searchForm.signDateTo"/>
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
                            <sd:Option value="-1" selected="true">-- Chọn --</sd:Option>
                            <sd:Option value="1">Mới nộp</sd:Option>
                            <sd:Option value="2">Đã được đề xuất xử lý</sd:Option>
                            <sd:Option value="3">Đã được lãnh đạo phòng phân công xử lý</sd:Option>
                            <sd:Option value="4">Đã được chuyên viên thẩm định</sd:Option>
                            <sd:Option value="5">Đã được lãnh đạo phòng xem xét kết quả</sd:Option>
                            <sd:Option value="14">Đã được văn thư tiếp nhận hồ sơ</sd:Option>
                            <sd:Option value="15">Đã được văn thư đối chiếu hồ sơ gốc</sd:Option>
                            <sd:Option value="16">Đã được văn thư đối chiếu hồ sơ gốc, có sai lệch</sd:Option>
                            <sd:Option value="17">Đã được văn thư tiếp nhận hồ sơ SĐBS</sd:Option>
                            <sd:Option value="18">Chờ văn thư tiếp nhận hồ sơ SĐBS</sd:Option>
                            <sd:Option value="6">Lãnh đạo cục đã phê duyệt kết quả</sd:Option>
                            <sd:Option value="7">Chuyên viên KL: SĐBS</sd:Option>
                            <sd:Option value="8">Lãnh đạo phòng trả lại để thẩm định lại</sd:Option>
                            <sd:Option value="9">Lãnh đạo cục trả lại để xem xét lại</sd:Option>
                            <sd:Option value="22">Hồ sơ đã được trả bản công bố</sd:Option>
                            <sd:Option value="23">Hồ sơ đã được văn thư thông báo đối chiều hồ sơ gốc</sd:Option>
                            <sd:Option value="28">Hồ sơ đã soạn dự thảo SĐBS</sd:Option>
                            <sd:Option value="20">Hồ sơ đã gửi công văn SĐBS</sd:Option>
                        </sd:SelectBox>

                    </td>
                </tr>
                <tr>
                    <td align="right"> 
                        <sd:Label key="Tiếp nhận từ ngày"/>

                    </td>
                    <td align="right" colspan="1">

                        <sx:DatePicker id="searchForm.receivedDate" name="searchForm.receivedDate" key="" 
                                       value="" format="dd/MM/yyyy" cssStyle="width:100%;"/>                    

                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày" cssStyle="floating"/>
                    </td>
                    <td align="left" colspan="2">
                        <sx:DatePicker id="searchForm.receivedDateTo" name="searchForm.receivedDateTo" key="" 
                                       value="" format="dd/MM/yyyy" cssStyle="width:100%;"/>   
                    </td>
                </tr>
                <tr> 
                    <td align="right">
                        <sd:Label key="Số đến"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.receiveNo"
                                    key=""
                                    name="searchForm.receiveNo" maxlength="250"/>
                    </td>
                    <!-- hieptq update -->
                    <td align="right"><sd:Label key="Nhóm thực phẩm"/></td>
                    <td>
                        <sd:SelectBox key="" id="searchForm.ProductTypeNew" name="searchForm.ProductTypeNew" cssStyle="width:100%">
                            <sd:Option value="-1" selected='true' >-- Chọn --</sd:Option>
                            <sd:Option value="1" >Nhóm thực phẩm thường</sd:Option>
                            <sd:Option value="2" >Các nhóm thực phẩm khác</sd:Option>
                        </sd:SelectBox>
                    </td>
                    <!-- -->
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
                            <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="3%" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="Xem" headerStyles="text-align:center;" width="4%" cellStyles="text-align:center;"
                                               formatter="page.formatEdit" get="page.getIndex"/>                        
                            <sd:ColumnDataGrid  key="Số đến" field="receiveNo" width="4%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode" width="8%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên tổ chức, cá nhân" field="businessName"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName"  cellStyles="text-align:center;"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày nộp" field="sendDate" format="dd/MM/yyyy" type="date"
                                                width="7%"  headerStyles="text-align:center;" cellStyles="text-align:center;" /> 
                            <sd:ColumnDataGrid  key="Ngày tiếp nhận" field="receivedDate" format="dd/MM/yyyy" type="date"
                                                width="7%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />   
                            <sd:ColumnDataGrid  key="Ngày hẹn trả" field="deadlineApprove" format="dd/MM/yyyy" type="date"
                                                width="7%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Trạng thái"  cellStyles="text-align:left;" field="displayStatus"
                                                width="8%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="Cảnh báo" headerStyles="text-align:center;" width="3%" cellStyles="text-align:center;"
                                               formatter="page.formatWarning" get="page.getIndex"/>
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
            <tr id="trSignMore">
                <td>
                    <table>
                        <tr>
                            <td>
                                <sd:Button id="btnSignMore" key="" onclick="page.showSignMore()">
                                    <img src="${contextPath}/share/images/signature.png" height="20" width="20"/>
                                    <span style="font-size:12px" title="Cho phép ký số nhiều hồ sơ, vui lòng tích chọn hồ sơ cần ký số trước khi thực hiện">Phê duyệt nhiều hồ sơ</span>
                                </sd:Button>
                            </td>
                            <td>
                                <div id="divSignProcess" style="display: none">
                                    <table>
                                        <tr>
                                            <td>
                                                <div id="divProcess" style="color:red;font-weight: bold;margin-left: 10px;margin-top: 3px"></div>
                                            </td>
                                            <td>
                                                <img src="/share/images/loading/loading2.gif" width="20px" height="20px" >
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>
<%--
<sd:Dialog  id="alertComparisonDlg" height="auto" width="1000px" key="Thông báo đối chiếu hồ sơ gửi doanh nghiệp">
    <jsp:include page="../comparison/alertComparisonDlg.jsp" flush="false"/>
</sd:Dialog>
<sd:Dialog  id="feedbackGiveBackFormDlg" height="auto" width="600px"
            key="Gửi nội dung công văn SĐBS" showFullscreenButton="false"
            >
    <jsp:include page="../evaluation/feedbackEvaluate/feedbackGiveBackForm.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog id="selectDeptDlg" key="Chọn cơ quan xử lý" width="500px">
    <div style="overflow: auto; max-height: 500px">
        <jsp:include page="../business/selectDept.jsp" />
    </div>
</sd:Dialog>
<sd:Dialog  id="comparisonDlg" height="auto" width="600px"
            key="Đối chiếu hồ sơ" showFullscreenButton="false"
            >
    <jsp:include page="../comparison/comparisonDlgSinger.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="upload" height="auto" width="400px"
            key="Upload hồ sơ đã đóng dấu sô" showFullscreenButton="false"
            >
    <jsp:include page="signUploadPdfVT.jsp" flush="false"></jsp:include>
</sd:Dialog>
<jsp:include page="pluginJSC.jsp" flush="false"></jsp:include>
--%>
<div id="createDiv"></div>
<script type="text/javascript">
    var workingFileId;
    var signFileId;
    var signType;
    var checkSignType;
    var flagSignMore = false;
    var itemsToSign = null;
    var signIndex = 0;

    backPage = function () {
        doGoToMenu(g_latestClickedMenu);
    };

    page.reset = function () {
        dijit.byId("searchForm.fileCode").setValue("");
        dijit.byId("searchForm.fileType").setValue("-1");
        dijit.byId("searchForm.sendDateFrom").setValue("");
        dijit.byId("searchForm.sendDateTo").setValue("");
        page.search();
    };

    page.search = function () {
        dijit.byId("filesGrid").vtReload('filesAction!onsearchLookupFilesAfterAnnounced.do?', "searchForm", null, page.afterSearch);
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
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + fileId + "&createForm.viewType=1", "createDiv", null, null, afterLoadForm);
    };

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
    page.reloadViewOldVersion = function (oldVersion, thisVersion) {//u140728
        document.getElementById('createDiv').value = '';
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
    page.formatLinkDownloadFileLDCSignApprove = function (fileId) {
        document.location = "uploadiframe!openFileLDCSignApprove.do?fileId=" + fileId;
    };
    page.onApproveSign = function (fileId) {
        page.onSign(fileId, false);
    };

    page.onSign = function (fileId, isSignMore) {
        flagSignMore = isSignMore;
        signFileId = fileId;
        // hieptq update check loai ky nhieu -6 ky hs moi, -27 ky cvsdbs
        if (checkSignType == "-6") {
            signType = "PDHS";
        }
        if (checkSignType == "-27")
        {
            signType = "CVBS";
        }
        sd.connector.post("uploadiframe!openFileUserAttachPdfSign.do?fileId=" + fileId + "&signType=" + signType, null, null, null, signAfterC);
    };


    var cert;
    var count = 0;
    signAfterC = function (data)
    {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result[0] == "success") {
            var fileId = signFileId;
            if (count == 0) {
                var item = uploadCertOfFile(fileId);
                cert = encodeBase64(item.certChain);
            }
            if (signType == "PDHS") {
                var signTypeCheck = "PDHS_VT";
            }
            if (signType == "CVBS") {
                var signTypeCheck = "CVBS_VT";
            }
            var path = result[1];
            sd.connector.post("filesAction!actionSignCA.do?fileId=" + fileId + "&cert=" + cert + "&signType=" + signTypeCheck + "&path=" + path, null, null, null, page.signPluginC);
            count++;
        }
        else
        {
            msg.alert("Ký số không thành công: Kiểm tra quá trình xử lý File", "Cảnh báo");
            document.getElementById("divSignProcess").style.display = "none";
        }
    };

    page.signPluginC = function (data)
    {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result[0] == "1") {
            var txtBase64HashNew = result[2];
            var certSerialNew = result[3];
            var fileId = result[4];
            var outPutPath = result[5];
            var fileName = result[6];
            var outPutPath2 = result[8];
            var txtBase64HashNew0 = result[7];
            var fileName0 = result[9];
            dijit.byId("txtBase64HashC").setValue(txtBase64HashNew);
            dijit.byId("txtCertSerialC").setValue(certSerialNew);
            dijit.byId("txtBase64HashC0").setValue(txtBase64HashNew0);
            var sign = signAndSubmit();
            var signData = encodeBase64(sign);
            var sign2;
            var signData2;
            if (signType == "PDHS") {
                var signTypeCheck = "PDHS_VT";
                sign2 = signAndSubmitOriginalFile();
                signData2 = encodeBase64(sign2);
            }
            if (signType == "CVBS") {
                var signTypeCheck = "CVBS_VT";
            }
            sd.connector.post("filesAction!onSignPlugin.do?fileId=" + fileId + "&outPutPath=" + outPutPath + "&signData=" + signData + "&signType=" + signTypeCheck + "&fileName=" + fileName + "&outPutPath2=" + outPutPath2 + "&fileName0=" + fileName0 + "&signDataOriginal=" + signData2, null, null, null, page.afterSignPluginC);
        } else
        {
            alert("Ký số không thành công ! " + result[1]);
        }
    };
    // hiepq update 231015
    page.afterSignPluginC = function (data)
    {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result[0] == "1") {
            onApprove();
        } else
        {
            alert("Ký số không thành công ! " + result[1]);
        }
    };

    page.showSignMore = function () {
        if (!dijit.byId("filesGrid").vtIsChecked()) {
            msg.alert('Bạn chưa chọn hồ sơ để thực hiện ký số', 'Cảnh báo');
        }
        else {
            itemsToSign = dijit.byId("filesGrid").vtGetCheckedItems();
            signIndex = 0;
            msg.confirm('Bạn có chắc chắn muốn ký số phê duyệt nhiều hồ sơ?', '<sd:Property>confirm.title1</sd:Property>', page.signMoreFiles);
                    }
                }

                page.signMoreFiles = function () {
                    document.getElementById('divProcess').innerHTML = "Hệ thống đang thực hiện ký số:" + (signIndex + 1) + "/" + itemsToSign.length + " hồ sơ  ";
                    document.getElementById("divSignProcess").style.display = "";
                    page.onSign(itemsToSign[signIndex].fileId, true);
                };



                onApprove = function () {
                    if (signType == "PDHS")
                    {
                        sd.connector.post("filesAction!onReturnFiles.do?signFileId=" + signFileId + "&" + token.getTokenParamString(), null, "feedbackGiveBackForm", null, page.afterAproveVT);
                    }
                    else if (signType == "CVBS")
                    {
                        dijit.byId("feedbackGiveBackForm.fileId").setValue(signFileId);
                        sd.connector.post("filesAction!onFeedbackGiveBack.do?" + token.getTokenParamString(), null, "feedbackGiveBackForm", null, page.afterAproveVT);
                    }
                };
                page.afterAproveVT = function (data) {
                    var obj = dojo.fromJson(data);
                    var result = obj.items;
                    if (!flagSignMore)
                    {
                        alert(result[1]);
                        if (result[0] == "1") {
                            document.getElementById("divSignProcess").style.display = "none";
                            onCloseFeedbackGiveBackForm();
                            page.search();
                            count = 0;
                        }
                    }
                    else
                    {
                        if (result[0] == "1") {
                            if (signIndex == itemsToSign.length - 1)
                            {
                                msg.alert('Ký số thành công', 'Thông báo');
                                document.getElementById("divSignProcess").style.display = "none";
                                page.search();
                                count = 0;
                            }
                            else
                            {
                                signIndex++;
                                page.signMoreFiles();
                            }
                        }
                        else
                        {
                            msg.alert('Ký số không thành công', 'Lỗi hệ thống');
                            document.getElementById("divSignProcess").style.display = "none";
                            page.search();
                            count = 0;
                        }
                    }

                };

                page.downloadFileSign = function (fileId) {
                    document.location = "uploadiframe!openFileUserUpload.do?fileId=" + fileId;
                };
                //--
                page.onFeedbackGiveBack = function (fileId) {
                    signFileId = fileId;
                    dijit.byId("feedbackGiveBackFormDlg").show();
                };

                page.onExportGiveBackFile = function (fileId) {
                    document.location = "uploadiframe!openFileUserAttachPdfCVBS.do?fileIdf=" + fileId;
                };
                //-!-
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
                page.showComparisonDlg = function (row) {
                    var item = dijit.byId("filesGrid").getItem(row);
                    dijit.byId("comparisonForm.fileId").setValue(item.fileId);
                    document.getElementById("comparisonForm.lastContent").innerHTML = item.comparisonContent;
                    workingFileId = item.fileId;
                    page.rplBrTblComparisonDlg();
                    dijit.byId("comparisonDlg").show();
                };
                page.UploadFileSignVT = function (row)
                {
                    var item = dijit.byId("filesGrid").getItem(row);
                    workingFileId = item.fileId;
                    clearAttFile("sendForm.attachFileNew");
                    dijit.byId("upload").show();
                };
                page.hideSignMore = function ()
                {
                    var searchType = dijit.byId("searchForm.searchType").getValue();
                    checkSignType = searchType;
                    // hieptq update -6 ky duyet hs, -27 cvsdbs
                    if ((searchType != null && searchType == "-6") || (searchType != null && searchType == "-27"))
                    {
                        document.getElementById("trSignMore").style.display = "";
                    }
                    else
                    {
                        document.getElementById("trSignMore").style.display = "none";
                    }
                };
                page.hideSignMore();
                page.search();
                function deleteAllCookies() {
                    var cookies = document.cookie.split(";");
                    for (var i = 0; i < cookies.length; i++) {
                        var cookie = cookies[i];
                        var eqPos = cookie.indexOf("=");
                        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
                        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
                    }
                }
                deleteAllCookies();
    </script>
    <input type="hidden" id="base64Hash0" value="" />
<sd:TextBox id="txtBase64HashC0" key="" name="txtBase64Hash0" type="hidden"/>
<input type="hidden" id="base64Hash" value="" />
<sd:TextBox id="txtBase64HashC" key="" name="txtBase64Hash" type="hidden"/>
<input type="hidden" id="certSerial" value="" />
<sd:TextBox id="txtCertSerialC" key="" name="txtCertSerial" type="hidden"/>
