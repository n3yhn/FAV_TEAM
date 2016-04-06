<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<div id="token">
    <s:token id="tokenId"/>
</div>
<sd:TitlePane key="search.searchCondition" id="categoryTitle">
    <form id="searchForm" name="searchForm">
        <s:hidden id="deptId" name="searchForm.deptId"/>
        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <td width="10%"></td>
                <td width="35%"></td>
                <td width="15%"></td>
                <td width="40%"></td>
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
                                   id="searchForm.searchDateFrom"
                                   key="" format="dd/MM/yyyy"
                                   name="searchForm.searchDateFrom"/>
                </td>
                <td align="right">
                    <sd:Label key="Đến ngày"/>
                </td>
                <td>
                    <sd:DatePicker cssStyle="width:100%"
                                   id="searchForm.searchDateTo"
                                   key="" format="dd/MM/yyyy"
                                   name="searchForm.searchDateTo"/>
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
                <td style="display: none" align="right"><sd:Label key="Trạng thái"/></td>
                <td style="display: none">
                    <sd:SelectBox key="" id="searchForm.status" name="searchForm.status" cssStyle="width:100%">
                        <sd:Option value="-1" selected="true">-- Chọn --</sd:Option>
                        <sd:Option value="1">Mới nộp</sd:Option>
                        <sd:Option value="2">Đã được đề xuất xử lý</sd:Option>
                        <sd:Option value="3">Đã được lãnh đạo phòng phân công xử lý</sd:Option>
                        <sd:Option value="4">Đã được chuyên viên thẩm định</sd:Option>
                        <sd:Option value="5">Đã được lãnh đạo phòng xem xét kết quả</sd:Option>
                        <sd:Option value="14">Đã được văn thư tiếp nhận hồ sơ</sd:Option>
                        <sd:Option value="15">Đã được văn thư đối chiếu hồ sơ gốc</sd:Option>
                        <sd:Option value="17">Đã được văn thư tiếp nhận hồ sơ SĐBS</sd:Option>
                        <sd:Option value="18">Chờ văn thư tiếp nhận hồ sơ SĐBS</sd:Option>
                        <sd:Option value="6">Lãnh đạo cục đã phê duyệt kết quả</sd:Option>
                        <sd:Option value="7">Chuyên viên KL: SĐBS</sd:Option>
                        <sd:Option value="8">Lãnh đạo phòng trả lại để thẩm định lại</sd:Option>
                        <sd:Option value="9">Lãnh đạo cục trả lại để xem xét lại</sd:Option>
                        <sd:Option value="22">Hồ sơ đã được trả bản công bố</sd:Option>
                        <sd:Option value="28">Hồ sơ đã soạn dự thảo SĐBS</sd:Option>
                        <sd:Option value="20">Hồ sơ đã gửi công văn SĐBS</sd:Option>
                    </sd:SelectBox>
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
                <td align="right">
                    <sd:Label key="Trạng thái báo cáo"/>
                </td>
                <td>
                    <sd:SelectBox key="" id="searchForm.searchType" name="searchForm.searchType" cssStyle="width:100%">
                        <sd:Option value="-1" selected="true">-- Chọn --</sd:Option>
                        <sd:Option value="1">Hồ sơ đã tiếp nhận mới</sd:Option>
                        <sd:Option value="2">Hồ sơ đã tiếp nhận sửa đổi bổ sung</sd:Option>
                        <sd:Option value="3">Hồ sơ đã gửi công văn sửa đổi bổ sung</sd:Option>
                        <sd:Option value="4">Hồ sơ đã trả bản công bố</sd:Option>
                    </sd:SelectBox>
                </td>
            </tr>
            <tr style="text-align: center">
                <td colspan="4">
                    <br/>
                    <sd:Button id="totalStaffQualitySearchForm.reportButton" key="" onclick="page.report()">
                        <img src='share/images/icons/report_icon.jpg' height="20" width="20">
                        <span style="font-size:12px">Lập báo cáo</span>
                    </sd:Button>
                </td>
            </tr>

        </table>
    </form>
</sd:TitlePane>
<script>
    page.report = function () {
        var fromDateSearch = dijit.byId("searchForm.searchDateFrom");
        var toDateSearch = dijit.byId("searchForm.searchDateTo");
        if (!page.isDate(fromDateSearch, false, "Từ ngày"))
        {
            fromDateSearch.focus();
            return false;
        }
        if (!page.isDate(toDateSearch, false, "Đến ngày"))
        {
            toDateSearch.focus();
            return false;
        }

        if (!page.checkBlankDate(fromDateSearch.getValue()) && !page.checkBlankDate(toDateSearch.getValue()) && !page.compareDate(fromDateSearch, toDateSearch, "Từ ngày", "đến ngày", false))
        {
            fromDateSearch.focus();
            return false;
        }
        var searchType = dijit.byId("searchForm.searchType").getValue();
        if (searchType == -1) {
            alert("Vui lòng chọn loại báo cáo.");
            return false;
        }
        var url = "report!reportDayClerical.do";
        document.searchForm.action = url;
        document.searchForm.submit();
    };

    page.compareDate = function (id, value, name, valueName, isC) {
        try {
            if (id.getValue() == null || id.getValue() == "")
                return true;
            var valueDate = dojo.date.locale.format(value.getValue(), {
                datePattern: 'dd/MM/yyyy',
                selector: 'date'
            });
            var idDate = dojo.date.locale.format(id.getValue(), {
                datePattern: 'dd/MM/yyyy',
                selector: 'date'
            });
            if (isC == true) {
                if (!sd.validator.compareDates(valueDate, idDate)) {
                    id.focus();
                    alert(name + " phải lớn hơn " + valueName, '<sd:Property>confirm.title</sd:Property>');
                                        return false;
                                    }
                                }
                                else {
                                    if (sd.validator.compareDates(valueDate, idDate)) {
                                        id.focus();
                                        alert(name + " phải nhỏ hơn " + valueName, '<sd:Property>confirm.title</sd:Property>');
                                                            return false;
                                                        }
                                                    }
                                                }
                                                catch (e) {
                                                    alert(e.message);
                                                }
                                                return true;
                                            };

                                            page.checkBlankDate = function (value) {
                                                return value == null || dojo.trim(value.toString()) == "";
                                            };

                                            page.isDate = function (id, isNull, name) {
                                                if (isNull == true) {
                                                    if (dojo.trim(id.getValue()) == "") {
                                                        id.focus();
                                                        alert("Bạn phải nhập " + name, "<sd:Property>confirm.title</sd:Property>");
                                                                        return false;
                                                                    }
                                                                }
                                                                if (!id.isValid()) {
                                                                    id.focus();
                                                                    alert(name + " nhập không hợp lệ!", "<sd:Property>confirm.title</sd:Property>");
                                                                                return false;
                                                                            }
                                                                            return true;
                                                                        };
</script>