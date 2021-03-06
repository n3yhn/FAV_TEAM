<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="token">
    <s:token id="tokenId"/>
</div>

<sd:TitlePane key="search.searchCondition" id="categoryTitle">
    <form id="searchForm" name="searchForm">
        <s:hidden id="deptId" name="searchForm.deptId"/>
        <sd:TextBox key="" id="searchForm.searchType" name="searchForm.searchType" cssStyle="display:none" />
        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <td width="10%"></td>
                <td width="35%"></td>
                <td width="15%"></td>
                <td width="40%"></td>
            </tr>
            <tr>
                <td align="right"> 
                    <sd:Label key="Từ ngày"/>

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
                    <sd:Label key="Theo thông tư 30"/>
                </td>
                <td>
                    <sd:CheckBox id="searchForm.is30" name="searchForm.is30" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox>                    
                    </td>
                    <td align="right">
                    <sd:Label key="Nhóm sản phẩm"/>
                </td>
                <td>
                    <sd:SelectBox key="" id="searchForm.productTypeId" name="searchForm.productTypeId" cssStyle="width:100%"
                                  data="lstProductType" valueField="categoryId" labelField="name" value="${fn:escapeXml(searchForm.productTypeId)}"/>        
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

    page.report = function() {
        var fromDateSearch = dijit.byId("searchForm.receivedDate");
        var toDateSearch = dijit.byId("searchForm.receivedDateTo");

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

        var url = "report!reportStaff.do";
        document.searchForm.action = url;
        document.searchForm.submit();
    }

    page.compareDate = function(id, value, name, valueName, isC) {
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
                                            }

    page.checkBlankDate = function(value) {
        return value == null || dojo.trim(value.toString()) == "";
    }

    page.isDate = function(id, isNull, name) {
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
                                }
</script>