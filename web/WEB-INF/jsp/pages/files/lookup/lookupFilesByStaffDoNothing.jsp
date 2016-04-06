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

    page.formatEdit = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var url = "<div style='text-align:center;cursor:pointer;'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + item.fileId + ");' />";
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
                case 50:
                    url = "Đã phân công xử lý";
                    break;
                case 45:
                    url = "Đã thẩm định";
                    break;
                case 46:
                    url = "Đã xem xét kết quả";
                    break;
                case 47:
                    url = "Đã phê duyệt kết quả";
                    break;
                case 7:
                    url = "Chuyên viên KL: SĐBS";
                    break;
                case 43:
                    url = "Đã trả lại để thẩm định lại";
                    break;
                case 48:
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
                case 30:
                    url = "Sắp hết hạn bổ sung";
                    break;
                case 40:
                    url = "Tổng số hồ sơ trong ngày";
                    break;
                case 44:
                    url = "Đã phân công chờ ý kiến";
                    break;
                case 49:
                    url = "Đã gửi phối hợp và có ý kiến";
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
    page.formatStatusView = function (inData) {
        var row = inData;
        var item = dijit.byId("filesGrid").getItem(row);
        var url = "";
        var display = "";
        if (item != null) {
            display = item.displayStatus;
            var status = parseInt(item.status);
            switch (status) {
                case 8:
                    url = "<a href='#' onClick='page.showEvaludateResult(" + row + ");'>" + display + "</a>";
                    break;
                default:
                    url = display;
                    break;
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
    dojo.connect(dojo.byId("searchForm"), "onkeypress", page.searchDefault);
</script>
<div id="token">
    <s:token id="tokenId"/>
</div>
<div id="searchDiv" >
    <sd:TitlePane key="search.searchCondition" id="categoryTitle">
        <form id="searchForm" name="searchForm">
            <s:hidden id="deptId" name="searchForm.deptId"/>
            <sd:TextBox key="" id="searchForm.searchType" name="searchForm.searchType" cssStyle="display:none"/>
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
                        <sd:TextBox cssStyle="width:99%"
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
                        <sd:DatePicker cssStyle="width:35%"
                                       id="searchForm.sendDateFrom"
                                       key="" format="dd/MM/yyyy"
                                       name="searchForm.sendDateFrom"/>
                        <sd:Label key="Đến ngày "/>
                        <sd:DatePicker cssStyle="width:35%; float: right;" 
                                       id="searchForm.sendDateTo"
                                       key="" format="dd/MM/yyyy"
                                       name="searchForm.sendDateTo"/>
                    </td>
                    <td align="right"><sd:Label key="Trạng thái"/></td>
                    <td>
                        <sd:SelectBox key="" id="searchForm.status" name="searchForm.status" cssStyle="width:100%">
                            <sd:Option value="-1" selected='true' >-- Chọn --</sd:Option>
                            <sd:Option value="1"> Mới nộp</sd:Option>
                            <!--<sd:Option value="2"> Đã được đề xuất xử lý</sd:Option>-->
                            <sd:Option value="50">Đã được lãnh đạo phòng phân công</sd:Option>
                            <sd:Option value="45">Đã được chuyên viên thẩm định</sd:Option>
                            <sd:Option value="46">Đã được lãnh đạo phòng xem xét kết quả</sd:Option>
                            <sd:Option value="47">Đã được lãnh đạo cục phê duyệt kết quả</sd:Option>
                            <sd:Option value="7"> Chuyên viên KL: SĐBS</sd:Option>
                           <!-- <sd:Option value="43">Đã lãnh đạo phòng trả lại để thẩm định lại</sd:Option> -->
                           <!-- <sd:Option value="48">Đã lãnh đạo cục trả lại để xem xét lại</sd:Option> -->
                            <sd:Option value="15">Đã được văn thư đối chiếu hồ sơ gốc</sd:Option>
                            <sd:Option value="16">Đã được van thư đối chiếu, có sai lệch</sd:Option>
                           <!-- <sd:Option value="44">Đã phân công chờ ý kiến</sd:Option>   -->                         
                            <!-- <sd:Option value="49">Đã gửi phối hợp và có ý kiến</sd:Option> -->
                            <sd:Option value="30">Đã gửi công văn thông báo sửa đổi bổ sung</sd:Option>
                            <sd:Option value="22">Đã trả bản công bố</sd:Option>
                            <sd:Option value="40">Tổng số hồ sơ trong ngày</sd:Option>

                        </sd:SelectBox>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Tên tổ chức, cá nhân"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:99%"
                                    id="searchForm.businessName"
                                    key=""
                                    name="searchForm.businessName" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Địa chỉ"/>
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
                        <sd:Label key="Số ĐKKD"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:99%"
                                    id="searchForm.businessLicence"
                                    key=""
                                    name="searchForm.businessLicence" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Số chứng nhận CB"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:99%"
                                    id="searchForm.announcementNo"
                                    key=""
                                    name="searchForm.announcementNo" maxlength="250"/>
                    </td>
                </tr>
                <tr>
                    <td  align="right"><sd:Label>Nhóm sản phẩm</sd:Label></td>
                        <td >

                        <sd:SelectBox   key="" id="searchForm.productType" 
                                        name="searchForm.productType" cssStyle="width:99%"
                                        data="lstProductType" valueField="categoryId" labelField="name" />
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
                        <sd:Label key="Xuất xứ"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:99%"
                                    id="searchForm.nationName"
                                    key=""
                                    name="searchForm.nationName" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Nhà sản xuất"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:99%"
                                    id="searchForm.manufactureName"
                                    key=""
                                    name="searchForm.manufactureName" maxlength="250"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Ngày ký"/>
                    </td>
                    <td>
                        <sd:DatePicker cssStyle="width:35%"
                                       id="searchForm.approveDateFrom"
                                       key="" format="dd/MM/yyyy"
                                       name="searchForm.approveDateFrom"/>
                        <sd:Label key="Đến ngày"/>
                        <sd:DatePicker cssStyle="width:35%; float: right;" 
                                       id="searchForm.approveDateTo"
                                       key="" format="dd/MM/yyyy"
                                       name="searchForm.approveDateTo"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Người ký"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.leaderStaffSignName"
                                    key=""
                                    name="searchForm.leaderStaffSignName" maxlength="250"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Tỉnh/Thành Phố"/>
                    </td>
                    <td>
                        <sd:SelectBox id="searchForm.businessProvinceId" name="searchForm.businessProvinceId" key="" data="lstProvince" valueField="categoryId" labelField="name" />
                        <sd:TextBox id="searchForm.businessProvince" name="searchForm.businessProvince" cssStyle="display:none" key=""/> 
                    </td>
                    <td align="right">
                        <sd:Label key="Lãnh đạo đơn vị"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.leaderStaff"
                                    key=""
                                    name="searchForm.leaderStaff" maxlength="250"/>
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
                        <sd:Label key="Nơi lưu trữ"/>
                    </td>
                    <td>
                        <sd:SelectBox cssStyle="width:99%"
                                      id="searchForm.repositoriesId"
                                      key="" data="lstRepository" valueField="repId" labelField="repName"
                                      name="searchForm.repositoriesId" >
                        </sd:SelectBox>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Theo thông tư 30"/>
                    </td>
                    <td>
                        <sd:CheckBox id="searchForm.is30" name="searchForm.is30" 
                                     checked="false" key="" value="1"
                                     ></sd:CheckBox>                    
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                        <sd:Label key="Tìm theo từ"/>
                    </td>
                    <td colspan="3">
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.searchFullText"
                                    key=""
                                    name="searchForm.searchFullText" maxlength="250"/>
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
                            <sd:ColumnDataGrid editable="true" key="Xem" headerStyles="text-align:center;" width="3%" cellStyles="text-align:center;"
                                               formatter="page.formatEdit" get="page.getIndex"/>                        
                            <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode" width="7%" headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Loại hồ sơ" field="fileTypeName"
                                                width="20%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên tổ chức, cá nhân" field="businessName"
                                                width="20%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName"  cellStyles="text-align:center;"
                                                width="20%"  headerStyles="text-align:center;" />                            
                            <sd:ColumnDataGrid  key="Ngày nộp" field="sendDate" format="dd/MM/yyyy" type="date"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />                                                                                                                
                            <sd:ColumnDataGrid  key="Ngày hẹn trả" field="deadlineApprove" format="dd/MM/yyyy" type="date"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />                            
                            <sd:ColumnDataGrid  key="Kết quả" formatter="page.formatStatusView" get="page.getRow"
                                                width="7%"  headerStyles="text-align:center;" cellStyles="text-align:left;"/>
                            <sd:ColumnDataGrid  key="Cán bộ xử lý chính" field="nameStaffProcess"  cellStyles="text-align:center;"
                                                width="10%"  headerStyles="text-align:center;" />
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
<script type="text/javascript">
    var workingFileId;
    var fileId = 0;
    backPage = function () {
        document.getElementById("searchDiv").style.display = "";
        document.getElementById("createDiv").style.display = "none";
    };

    page.reset = function () {
        dijit.byId("searchForm.fileCode").setValue("");
        dijit.byId("searchForm.fileType").setValue("-1");
        dijit.byId("searchForm.sendDateFrom").setValue("");
        dijit.byId("searchForm.sendDateTo").setValue("");
        dijit.byId("searchForm.status").setValue("-1");
    };

    page.search = function () {
        dijit.byId("filesGrid").vtReload('filesAction!onsearchLookupFilesByStaffDonothing.do', "searchForm");
    };

    page.showViewFile = function (fileId) {
        var gridRegister = dijit.byId('processCommentGridId_VTGrid');
        if (gridRegister) {
            gridRegister.destroyRecursive(true);
        }

        document.getElementById("searchDiv").style.display = "none";
        document.getElementById('createDiv').Value = '';
        document.getElementById("createDiv").style.display = "";
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + fileId + "&createForm.viewType=-1", "createDiv", null, null, null);
    };

    page.showSearchPanel = function () {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
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

    //140704 binhnt53
    page.showAssignPopup = function (fileId) {
        dijit.byId("assignDlg").show();
        page.showAssignDlg(fileId);
    };
    //!140704
    page.downloadFileSign = function (fileId) {
        document.location = "uploadiframe!openFileUserUpload.do?fileId=" + fileId;
    };
    page.search();
</script>
