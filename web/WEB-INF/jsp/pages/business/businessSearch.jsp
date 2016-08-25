<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../util/util_js.jsp"/>
<jsp:include page="../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<script>
    page.getNo = function (index) {
        return dijit.byId("businessGrid").currentRow + index + 1;
    };

    page.getIndex = function (index) {
        return index + 1;
    };

    page.formatEdit = function (inData) {
        var row = inData - 1;
        var item = dijit.byId("businessGrid").getItem(row);
        var url = "";
        if (item != null) {
            var businessId = item.businessId;
            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                        title='<sd:Property>category.edit</sd:Property>' \n\
                        onClick='page.showEditPopup(" + row + ");' />\n\
                        | <img src='share/images/edit.png'\n\
                        width='17px' height='17px' \n\
                        title='Tạo thông báo cho doanh nghiệp' \n\
                        onClick='page.showAlertDlg(" + businessId + ");' /></div>";
        }
        return url;
    };

    </script>

    <div id="token">
    <s:token id="tokenId"/>
</div>

<div id ="searchDiv" style="display:none">

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
                        <sd:Label key="Tên doanh nghiệp"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.businessName"
                                    key=""
                                    name="searchForm.businessName" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Người đại diện"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.userFullname"
                                    key=""
                                    name="searchForm.userFullname" maxlength="255">
                        </sd:TextBox>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Mã số thuế"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.businessTaxCode"
                                    key=""
                                    name="searchForm.businessTaxCode" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Giấy phép kinh doanh"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.businessLicense"
                                    key=""
                                    name="searchForm.businessLicense" maxlength="255">
                        </sd:TextBox>
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
</div>        

<sd:TitlePane key="Danh mục doanh nghiệp"
              id="businessList" >
    <sx:ResultMessage id="resultDeleteMessage" />
    <table width="100%">
        <tr>
            <td>
                <div style="width:100%;">
                    <sd:DataGrid id="businessGrid"
                                 getDataUrl=""
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="category.No" get="page.getNo" width="5%"  styles="text-align:center;" />
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatEdit" get="page.getIndex"
                                           width="5%"  headerStyles="text-align:center;"  />
                        <sd:ColumnDataGrid  key="MST" field="businessTaxCode"
                                            width="10%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Tên doanh nghiệp" field="businessName"
                                            width="25%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Số GPKD" field="businessLicense"
                                            width="10%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Địa chỉ" field="businessAddress"
                                            width="15%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Tỉnh/Thành phố" field="businessProvince"
                                            width="15%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Điện thoại" field="businessTelephone"
                                            width="10%"  headerStyles="text-align:center;" />
                    </sd:DataGrid>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <sx:ButtonAddCategory onclick="page.insert();"/>
                <sx:ButtonDelete onclick="page.deleteItem()" />
                <sx:ButtonSearch id="btnShowSearchPanel" onclick="page.showSearchPanel();" />
                <sd:TextBox id="createForm.businessId" 
                                name="createForm.businessId" 
                                cssStyle="display:none" 
                                key=""
                                trim="true"/>
            </td>
        </tr>
    </table>
</sd:TitlePane>

<sd:Dialog  id="createDlg" height="auto" width="800px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="businessCreateDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>

<sd:Dialog  id="businessAlertDlg" height="auto" width="800px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="businessAlertDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>

<script type="text/javascript">

    page.search = function () {
        dijit.byId("businessGrid").vtReload('businessAction!onSearch.do?', "searchForm");
    };

    page.setItem = function (item) {
        dijit.byId("createForm.businessId").setValue(item.businessId);
        dijit.byId("createForm.userFullname").setValue(item.userFullname);
        dijit.byId("createForm.userTelephone").setValue(item.userTelephone);
        dijit.byId("createForm.userMobile").setValue(item.userMobile);
        dijit.byId("createForm.userEmail").setValue(item.userEmail);
        dijit.byId("createForm.isActive").setValue(item.isActive);
        dijit.byId("createForm.businessTypeId").setValue(item.businessTypeId);
        dijit.byId("createForm.businessTypeName").setValue(item.businessTypeName);
        dijit.byId("createForm.businessName").setValue(item.businessName);
        dijit.byId("createForm.businessNameEng").setValue(item.businessNameEng);
        dijit.byId("createForm.businessNameAlias").setValue(item.businessNameAlias);
        dijit.byId("createForm.businessTaxCode").setValue(item.businessTaxCode);
        dijit.byId("createForm.businessLicense").setValue(item.businessLicense);
        dijit.byId("createForm.businessAddress").setValue(item.businessAddress);
        dijit.byId("createForm.businessProvinceId").setValue(item.businessProvinceId);
        //var provinceName = dijit.byId("createForm.businessProvinceId").attr("displayedValue");
        dijit.byId("createForm.businessProvince").setValue(item.businessProvince);
        dijit.byId("createForm.businessTelephone").setValue(item.businessTelephone);
        dijit.byId("createForm.businessFax").setValue(item.businessFax);
        dijit.byId("createForm.businessWebsite").setValue(item.businessWebsite);
        dijit.byId("createForm.businessEstablishYear").setValue(item.businessEstablishYear);
        dijit.byId("createForm.businessLawRep").setValue(item.businessLawRep);
        dijit.byId("createForm.description").setValue(item.description);
        dijit.byId("createForm.userName").setValue(item.userName);
    };

    page.clearInsertForm = function () {
        dijit.byId("createForm.businessId").setValue("");
        dijit.byId("createForm.userFullname").setValue("");
        dijit.byId("createForm.userTelephone").setValue("");
        dijit.byId("createForm.userMobile").setValue("");
        dijit.byId("createForm.userEmail").setValue("");
        dijit.byId("createForm.isActive").setValue(1);
        dijit.byId("createForm.businessTypeId").setValue(0);
        dijit.byId("createForm.businessTypeName").setValue("");
        dijit.byId("createForm.businessName").setValue("");
        dijit.byId("createForm.businessNameEng").setValue("");
        dijit.byId("createForm.businessNameAlias").setValue("");
        dijit.byId("createForm.businessTaxCode").setValue("");
        dijit.byId("createForm.businessLicense").setValue("");
        dijit.byId("createForm.businessAddress").setValue("");
        dijit.byId("createForm.businessProvince").setValue("");
        dijit.byId("createForm.businessProvinceId").setValue(0);
        dijit.byId("createForm.businessTelephone").setValue("");
        dijit.byId("createForm.businessFax").setValue("");
        dijit.byId("createForm.businessWebsite").setValue("");
        dijit.byId("createForm.businessEstablishYear").setValue("");
        dijit.byId("createForm.businessLawRep").setValue("");
        dijit.byId("createForm.description").setValue("");
        dijit.byId("createForm.userName").setValue("");
    };

    page.insert = function () {
        page.clearInsertForm();
        dijit.byId("createDlg").show();
    };

    page.showEditPopup = function (row) {
        var item = dijit.byId("businessGrid").getItem(row);
        page.setItem(item);
        dijit.byId("createDlg").show();
    };
    
    page.showAlertDlg = function (businessId) {
        page.getViewLstBusinessAlert(businessId);
        dijit.byId("businessAlertDlg").show();
    };

    page.deleteItem = function () {
        if (!dijit.byId("businessGrid").vtIsChecked()) {
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
                    } else {
                        msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
                                }
    };

    page.deleteItemExecute = function () {
        var content = dijit.byId("businessGrid").vtGetCheckedDataForPost("lstItemOnGrid");
        sd.connector.post("businessAction!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
    };

    page.returnMessageDelete = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
        page.search();
    };

    page.showSearchPanel = function () {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };

    page.reset = function () {
        dijit.byId("searchForm.businessName").setValue("");
        dijit.byId("searchForm.userFullname").setValue("");
        dijit.byId("searchForm.businessTaxCode").setValue("");
        dijit.byId("searchForm.businessLicense").setValue("");
    };
    //dijit.byId("categoryTitle").attr("open", false);
    page.search();

</script>
