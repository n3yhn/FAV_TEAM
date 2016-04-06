<%@page import="java.util.Random"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib  prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../../util/util_js.jsp"/>
<jsp:include page="../../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="token">
    <s:token id="tokenId"/>
</div>
<script>

    page.getNo = function(index) {
        return dijit.byId("FeeGrid").currentRow + index + 1;
    };
    page.getIndex = function(index) {
        return index + 1;
    };
    page.formatfeePaymentType1 = function(inData)
    {
        var row = inData - 1;
        var item = dijit.byId("FeeGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var isFeePaymentType = parseInt(item.feePaymentType);
            var status = item.status;
            switch (isFeePaymentType) {
                case 1:
                    if (status == 1)
                    {
                        url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/icons/approve.png' width='17px' height='17px' \n\
                         title='<sd:Property>Xem thông tin thanh toán</sd:Property>' \n\
                        onClick='page.showPaymentOnline(" + row + ");' /></div>";
                    }
                    break;

                default:
                    url = "";
            }
        }
        return url;
    };

    page.formatfeePaymentType2 = function(inData)
    {
        var row = inData - 1;
        var item = dijit.byId("FeeGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var isFeePaymentType = parseInt(item.feePaymentType);
            var status = item.status;
            if (status == 1 && isFeePaymentType == 3)
            {
                url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/icons/approve.png' width='17px' height='17px' \n\
                         title='<sd:Property>Xem thông tin thanh toán</sd:Property>' \n\
                    onClick='page.showPaymentTransfer(" + row + ");' /></div>";
            }
        }
        return url;
    };

    page.formatfeePaymentType3 = function(inData)
    {
        var row = inData - 1;
        var item = dijit.byId("FeeGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var isFeePaymentType = parseInt(item.feePaymentType);
            switch (isFeePaymentType) {
                case 2:
                    if (item.status == 1) {
                        url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/icons/approve.png' width='17px' height='17px' \n\
                         title='<sd:Property>Xem thông tin thanh toán</sd:Property>' \n\
                        onClick='page.showPaymentCash(" + row + ");' /></div>";
                    }
                    break;

                default:
                    url = "";
            }
        }
        return url;
    };

    page.formatPay = function(inData)
    {
        var row = inData - 1;
        var item = dijit.byId("FeeGrid").getItem(inData - 1);
        // var url = "<div style='text-align:center;cursor:pointer;'><img src='share/images/icons/bill.png' width='17px' height='17px' title='Xác nhận nộp tiền' onClick='page.checkPaymentCash(" + row + ");' /></div>";
        var url = "";
        if (item != null) {
            var status = parseInt(item.status);

            switch (status) {
                case 1:
                    url = "";
                    break;
                case 2:
                    url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/icons/unapproved.png' width='17px' height='17px' \n\
                         title='<sd:Property>Kế toán từ chối tiếp nhận</sd:Property>' \n\
                           onClick='page.ShowCommentReject(" + row + ");' /></div>";
                    break;
                case 3:
                    url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/icons/process_icon.png' width='17px' height='17px' \n\
                         title='<sd:Property>Chờ xử lý</sd:Property>' \n\
                        onClick='page.CheckPaymentTransferNew(" + row + ");' /></div>";

                    break;
                case 4:
                    url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/icons/process_icon.png' width='17px' height='17px' \n\
                         title='<sd:Property>Chờ xử lý</sd:Property>' \n\
                        onClick='page.showPaymentOnline(" + row + ");' /></div>";
                    break;
                case 5:
                    url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/icons/process_icon.png' width='17px' height='17px' \n\
                         title='<sd:Property>Chờ xử lý</sd:Property>' \n\
                        onClick='page.CheckPaymentTransferNew(" + row + ");' /></div>";
                    break;
                default:
                    url += " <img src='share/images/icons/pay.jpg' width='17px' height='17px' title='Xác nhận nộp tiền mặt' onClick='page.checkPaymentCash(" + row + ");' />";
//                    url += " | <img src='share/images/icons/attach.png' width='17px' height='17px' title='Xác nhận chuyển khoản ' onClick='page.checkPaymentTransfer(" + row + ");' />";
                    url += "</div>";
                    // url;
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
    }

    dojo.connect(dojo.byId("searchFeeFormNew"), "onkeypress", page.searchDefault);

    </script>




    <div id ="searchDiv" style="display:none">

    <sd:TitlePane key="search.searchCondition" id="FeeTitle">
        <form id="searchFeeFormNew" name="searchFeeFormNew">
            <sd:TextBox key="" id="searchFeeFormNew.searchType" name="searchFeeFormNew.searchType" cssStyle="display:none" />
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
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchFeeFormNew.fileCode"
                                    name="searchFeeFormNew.fileCode"
                                    key=""
                                    maxlength="250"


                                    />
                    </td>

                    <td align="right">
                        <sd:Label key="Tên sản phẩm"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchFeeFormNew.productName"
                                    name="searchFeeFormNew.productName"
                                    key=""
                                    maxlength="250"


                                    />
                    </td>
                <tr>
                    <td align="right">
                        <sd:Label key="Giá tiền"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchFeeFormNew.cost"
                                    name="searchFeeFormNew.cost"
                                    key=""
                                    maxlength="18"
                                    />
                    </td>
                    <td align="right">
                        <sd:Label key="Loại hình thanh toán"/>
                    </td>
                    <td>
                        <sd:SelectBox id="searchFeeFormNew.feePaymentType" name="searchFeeFormNew.feePaymentType" key=""  cssStyle="width:100%" >
                            <sd:Option value='-1'>-- Chọn --</sd:Option>
                            <sd:Option value='1'>Thanh toán online</sd:Option>
                            <sd:Option value='2'>Tiền mặt</sd:Option>
                            <sd:Option value='3'>Chuyển khoản</sd:Option>
                        </sd:SelectBox>
                    </td>
                </tr>
                <tr>

                    <td align="right">
                        <sd:Label key="Trạng thái"/>
                    </td>
                    <td>
                        <sd:SelectBox id="searchFeeFormNew.status" name="searchFeeFormNew.status" key=""  cssStyle="width:100%" >
                            <sd:Option value='-1'>-- Chọn --</sd:Option>
                            <sd:Option value='1'>Đã xác nhận</sd:Option>
                            <sd:Option selected="true" value='0'>Chưa xác nhận</sd:Option>
                            <sd:Option value='2'>Đã từ chối</sd:Option>
                        </sd:SelectBox>
                    </td>

                    <td align="right">
                        <sd:Label key="Tên doanh nghiệp"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchFeeFormNew.businessName"
                                    name="searchFeeFormNew.businessName"
                                    key=""
                                    maxlength="250"


                                    />
                    </td>

                </tr>
                <tr>
                    <td align="right"> 
                        <sd:Label key="Nộp từ ngày"/>

                    </td>
                    <td align="right" colspan="1">

                        <sx:DatePicker id="searchFeeFormNew.dateFrom" name="searchFeeFormNew.dateFrom" key="" 
                                       value="" format="dd/MM/yyyy" cssStyle="width:100%;"/>                    

                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày" cssStyle="floating"/>
                    </td>
                    <td align="left" colspan="2">
                        <sx:DatePicker id="searchFeeFormNew.dateTo" name="searchFeeFormNew.dateTo" key="" 
                                       value="" format="dd/MM/yyyy" cssStyle="width:100%;"/>   
                    </td>
                </tr>
                <tr>
                    <td align="right"> 
                        <sd:Label key="Xác nhận từ ngày"/>

                    </td>
                    <td align="right" colspan="1">

                        <sx:DatePicker id="searchFeeFormNew.dateConfirmSearchFrom" name="searchFeeFormNew.dateConfirmSearchFrom" key="" 
                                       value="" format="dd/MM/yyyy" cssStyle="width:100%;"/>                    

                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày" cssStyle="floating"/>
                    </td>
                    <td align="left" colspan="2">
                        <sx:DatePicker id="searchFeeFormNew.dateConfirmSearchTo" name="searchFeeFormNew.dateConfirmSearchTo" key="" 
                                       value="" format="dd/MM/yyyy" cssStyle="width:100%;"/>   
                    </td>      

                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Người xác nhận"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchFeeFormNew.paymentConfirm"
                                    name="searchFeeFormNew.paymentConfirm"
                                    key=""
                                    maxlength="250"


                                    />
                    </td>
                                   <td align="right">
                        <sd:Label key="Mã thanh toán nhiều HS KeyPay"/>
                    </td>
                    <td>
                        <sd:TextBox  cssStyle="width:100%" trim="true"
                                     id="searchFeeFormNew.filesCode"
                                     name="searchFeeFormNew.filesCode"
                                     key=""
                                     maxlength="18" 
                                     />
                    </td>
                </tr>



                <tr style="text-align: center">
                    <td colspan="4">
                        <sx:ButtonSearch onclick="page.search();" />

                    </td>
                </tr>

            </table>
        </form>
    </sd:TitlePane>
</div>        

<sd:TitlePane key="Thông tin thanh toán"
              id="FeeList" >
    <sx:ResultMessage id="resultDeleteMessage" />
    <table width="100%">
        <tr>
            <td>

                <div id="" style="width:100%;">
                    <sd:DataGrid id="FeeGrid"
                                 getDataUrl=""
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="STT" get="page.getNo" width="2%" styles="text-align:center;"/>

                        <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode"
                                            width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                        <sd:ColumnDataGrid  key="Tên doanh nghiệp" field="businessName"
                                            width="5%"  headerStyles="text-align:center;" cellStyles="text-align:left;"/>
                        <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName"
                                            width="5%"  headerStyles="text-align:center;" cellStyles="text-align:left;"/>
                        <sd:ColumnDataGrid  key="Ngày nộp" field="paymentDate"
                                            width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Giá tiền(VND)" field="cost"
                                            width="5%"  headerStyles="text-align:center;"  styles="text-align:right;"/>                      
                        <sd:ColumnDataGrid  key="Người xác nhận" field="paymentConfirm"
                                            width="5%"  headerStyles="text-align:center;"  styles="text-align:left;"/> 
                        <sd:ColumnDataGrid  key="Ngày xác nhận" field="dateConfirm"
                                            width="5%"  headerStyles="text-align:center;"  styles="text-align:center;"/> 
                        <sd:ColumnDataGrid  key="Tiền mặt" get="page.getIndex" formatter="page.formatfeePaymentType3" cellStyles="text-align:center;"
                                            width="3%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Chuyển khoản" get="page.getIndex" formatter="page.formatfeePaymentType2" cellStyles="text-align:center;"
                                            width="3%"  headerStyles="text-align:center;" /> 
                        <sd:ColumnDataGrid  key="Nộp phí online" get="page.getIndex" formatter="page.formatfeePaymentType1" cellStyles="text-align:center;"
                                            width="3%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Mã thanh toán nhiều Keypay" field="filesCode"
                                            width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                        <sd:ColumnDataGrid  key="Xử lý" get="page.getIndex" formatter="page.formatPay" cellStyles="text-align:center;"
                                            width="3%"  headerStyles="text-align:center;" />

                    </sd:DataGrid>
                </div>
            </td>
        </tr>
        <tr>
            <td>

                <sx:ButtonSearch id="btnShowSearchPanel" onclick="page.showSearchPanel();" />
            </td>
        </tr>
    </table>
</sd:TitlePane>
<sd:Dialog  id="feeManageDlg" height="auto" width="600px"
            key="Xác nhận thanh toán" showFullscreenButton="false"
            >
    <div id="feeManageDiv">
        <jsp:include page="feeManageDlg.jsp" flush="false"/>
    </div>
</sd:Dialog> 
<sd:Dialog  id="feeManageViewDlg" height="auto" width="600px"
            key="Chi tiết thanh toán" showFullscreenButton="false"
            >
    <div id="feeManageViewDiv">
        <jsp:include page="feeManageViewDlg.jsp" flush="false"/>
    </div>
</sd:Dialog> 

<script>
    var insertDialog = true;
    var grid = dijit.byId("FeeGrid");

    var dlgfeeManage = dijit.byId("feeManageDlg");
    var dlgfeeViewManage = dijit.byId("feeManageViewDlg");

    page.search = function() {
        var cost = dijit.byId("searchFeeFormNew.cost").getValue();
        if (cost != null && cost.trim().length > 0) {
            if (isNaN(cost)) {
                alert("[Giá tiền] không đúng định dạng");
                dijit.byId("searchFeeFormNew.cost").focus();
                return false;
            }
        }
        var currentUserId = ${fn:escapeXml(userId)};
        grid.vtReload('filesAction!onSearchFeeManage.do?userId=' + currentUserId, "searchFeeFormNew", null, null);
    };
    page.showSearchPanel = function() {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };


    //hieptq update check tien mat 01.12.14
    page.checkPaymentCash = function(inData) {
        var row = inData;
        var item = dijit.byId("FeeGrid").getItem(row);
        var payType = "";
        var isFeePaymentType = 2;
        switch (isFeePaymentType) {
            case 1:
                payType = "Thanh toán online";
                break;
            case 3:
                payType = "Chuyển khoản";
                break;
            case 2:
                payType = "Tiền mặt";
                break;
            default:
                payType = "Chưa thanh toán";
        }
        dijit.byId("createFeeForm.paymentInfoId").setValue(item.paymentInfoId);
        dlgfeeManage.show();
    };


    page.showPaymentCash = function(inData)
    {
        var row = inData;
        var item = dijit.byId("FeeGrid").getItem(row);
        var payType = "";
        var isFeePaymentType = parseInt(item.feePaymentType);
        switch (isFeePaymentType) {
            case 1:
                payType = "Thanh toán online";
                break;
            case 3:
                payType = "Chuyển khoản";
                break;
            case 2:
                payType = "Tiền mặt";
                break;
            default:
                payType = "Chưa thanh toán";
        }
        document.getElementById("createFeeForm.businessNameView").innerHTML = item.businessName;
        document.getElementById("createFeeForm.businessAddressView").innerHTML = item.businessAddress;
        document.getElementById("createFeeForm.paymentDateView").innerHTML = item.paymentDate;
        document.getElementById("createFeeForm.paymentTypeView").innerHTML = payType;
        document.getElementById("createFeeForm.paymentCostView").innerHTML = item.cost;
        document.getElementById("createFeeForm.paymentPersonView").innerHTML = escapeHtml_(item.paymentPerson);
        document.getElementById("tdBill1View").style.display = "";
        document.getElementById("tdBill2View").style.display = "";
        document.getElementById("createFeeForm.paymentCodeView").innerHTML = item.paymentCode;
        //document.getElementById("createFeeForm.paymentConfirmView").innerHTML = item.paymentConfirm;
        document.getElementById("createFeeForm.billCodeView").innerHTML = item.billCode;
        document.getElementById("paymentCodeView1").style.display = "";
        document.getElementById("paymentCodeView2").style.display = "";
        document.getElementById("billCode1").style.display = "";
        document.getElementById("billCode2").style.display = "";
        document.getElementById("paymentConfirm1").style.display = "";
        document.getElementById("createFeeForm.paymentConfirmView").innerHTML = item.paymentConfirm;
        document.getElementById("createFeeForm.paymentConfirmView").style.display = "";
        dijit.byId("Deny").domNode.style.display = "none";
        document.getElementById("dateConfirm1").style.display = "";
        document.getElementById("createFeeForm.dateConfirmView").innerHTML = item.dateConfirm;
        document.getElementById("createFeeForm.dateConfirmView").style.display = "";

        // Attach file
        document.getElementById("browsesendForm.attachFileView").style.display = "none";
        clearAttFile("sendForm.attachFileView");
        getAttacthFileView(item.paymentInfoId, 25, "sendForm.attachFileView");
        dlgfeeViewManage.show();
    };

    page.showPaymentOnline = function(inData)

    {

        var row = inData;
        var item = dijit.byId("FeeGrid").getItem(row);
        var payType = "";
        var isFeePaymentType = parseInt(item.feePaymentType);
        switch (isFeePaymentType) {
            case 1:
                payType = "Thanh toán online";
                break;
            case 3:
                payType = "Chuyển khoản";
                break;
            case 2:
                payType = "Tiền mặt";
                break;
            default:
                payType = "Chưa thanh toán";
        }
        document.getElementById("createFeeForm.paymentDateView").innerHTML = item.paymentDate;
        document.getElementById("createFeeForm.paymentTypeView").innerHTML = payType;
        document.getElementById("createFeeForm.paymentCostView").innerHTML = item.cost;
        document.getElementById("createFeeForm.paymentPersonView").innerHTML = escapeHtml_(item.paymentPerson);
        document.getElementById("tdBill1View").style.display = "none";
        document.getElementById("tdBill2View").style.display = "none";
        dijit.byId("createFeeForm.paymentId").setValue(item.paymentInfoId);
        document.getElementById("browsesendForm.attachFileView").style.display = "none";
        //document.getElementById("createFeeForm.paymentConfirmView").style.display = "none";
        //document.getElementById("paymentConfirm1").style.display = "none";
        document.getElementById("paymentCodeView1").style.display = "none";
        document.getElementById("paymentCodeView2").style.display = "none";
        document.getElementById("billCode1").style.display = "none";
        document.getElementById("billCode2").style.display = "none";
        document.getElementById("createFeeForm.businessNameView").innerHTML = item.businessName;
        document.getElementById("createFeeForm.businessAddressView").innerHTML = item.businessAddress;
        if (item.status != 1)
        {
            dijit.byId("Save4").domNode.style.display = "";
            dijit.byId("Deny").domNode.style.display = "";
            document.getElementById("paymentConfirm1").style.display = "none";
            document.getElementById("createFeeForm.paymentConfirmView").style.display = "none";
            document.getElementById("dateConfirm1").style.display = "none";
            document.getElementById("createFeeForm.dateConfirmView").style.display = "none";
        } else {
            document.getElementById("paymentConfirm1").style.display = "";
            document.getElementById("createFeeForm.paymentConfirmView").innerHTML = item.paymentConfirm;
            document.getElementById("createFeeForm.paymentConfirmView").style.display = "";
            document.getElementById("dateConfirm1").style.display = "";
            document.getElementById("createFeeForm.dateConfirmView").innerHTML = item.dateConfirm;
            document.getElementById("createFeeForm.dateConfirmView").style.display = "";
        }

        //dijit.byId("Save2").domNode.style.display = "";
        dlgfeeViewManage.show();
    };

    page.showPaymentTransfer = function(inData)

    {
        var row = inData;
        var item = dijit.byId("FeeGrid").getItem(row);
        var payType = "";
        var isFeePaymentType = parseInt(item.feePaymentType);
        switch (isFeePaymentType) {
            case 1:
                payType = "Thanh toán online";
                break;
            case 3:
                payType = "Chuyển khoản";
                break;
            case 2:
                payType = "Tiền mặt";
                break;
            default:
                payType = "Chưa thanh toán";
        }
        document.getElementById("createFeeForm.businessNameView").innerHTML = item.businessName;
        document.getElementById("createFeeForm.businessAddressView").innerHTML = item.businessAddress;
        document.getElementById("createFeeForm.paymentDateView").innerHTML = item.paymentDate;
        document.getElementById("createFeeForm.paymentTypeView").innerHTML = payType;
        document.getElementById("createFeeForm.paymentCostView").innerHTML = item.cost;
        // document.getElementById("createFeeForm.paymentConfirmView").innerHTML = item.paymentConfirm;
        document.getElementById("createFeeForm.paymentPersonView").innerHTML = escapeHtml_(item.paymentPerson);
        document.getElementById("tdBill1View").style.display = "";
        document.getElementById("tdBill2View").style.display = "";
        document.getElementById("paymentCodeView1").style.display = "none";
        document.getElementById("paymentCodeView2").style.display = "none";
        document.getElementById("billCode1").style.display = "none";
        document.getElementById("billCode2").style.display = "none";
        document.getElementById("paymentConfirm1").style.display = "";
        document.getElementById("createFeeForm.paymentConfirmView").innerHTML = item.paymentConfirm;
        document.getElementById("createFeeForm.paymentConfirmView").style.display = "";
        document.getElementById("dateConfirm1").style.display = "";
        document.getElementById("createFeeForm.dateConfirmView").innerHTML = item.dateConfirm;
        document.getElementById("createFeeForm.dateConfirmView").style.display = "";
        dijit.byId("Deny").domNode.style.display = "none";
        clearAttFile("sendForm.attachFileView");
        getAttacthFileView(item.paymentInfoId, 25, "sendForm.attachFileView");
        document.getElementById("browsesendForm.attachFileView").style.display = "none";
        dlgfeeViewManage.show();

    };
    page.CheckPaymentTransferNew = function(inData)
    {
        var row = inData;
        var item = dijit.byId("FeeGrid").getItem(row);
        var payType = "";
        var isFeePaymentType = parseInt(item.feePaymentType);
        switch (isFeePaymentType) {
            case 1:
                payType = "Thanh toán online";
                break;
            case 3:
                payType = "Chuyển khoản";
                break;
            case 2:
                payType = "Tiền mặt";
                break;
            default:
                payType = "Chưa thanh toán";
        }
        document.getElementById("createFeeForm.paymentDateView").innerHTML = item.paymentDate;
        document.getElementById("createFeeForm.paymentTypeView").innerHTML = payType;
        document.getElementById("createFeeForm.paymentCostView").innerHTML = item.cost;
        document.getElementById("createFeeForm.paymentPersonView").innerHTML = escapeHtml_(item.paymentPerson);
        document.getElementById("createFeeForm.businessNameView").innerHTML = item.businessName;
        //document.getElementById("createFeeForm.paymentConfirmView").innerHTML = item.paymentConfirm;
        document.getElementById("createFeeForm.businessAddressView").innerHTML = item.businessAddress;
        if (item.feePaymentType == 2)
        {
            document.getElementById("createFeeForm.paymentCodeView").innerHTML = item.paymentCode;
            document.getElementById("createFeeForm.billCodeView").innerHTML = item.billCode;
            document.getElementById("paymentCodeView1").style.display = "";
            document.getElementById("paymentCodeView2").style.display = "";
            document.getElementById("billCode1").style.display = "";
            document.getElementById("billCode2").style.display = "";
        }
        if (item.feePaymentType == 3)
        {
            document.getElementById("paymentCodeView1").style.display = "none";
            document.getElementById("paymentCodeView2").style.display = "none";
            document.getElementById("billCode1").style.display = "none";
            document.getElementById("billCode2").style.display = "none";
        }
        dijit.byId("createFeeForm.paymentId").setValue(item.paymentInfoId);
        document.getElementById("tdBill1View").style.display = "";
        document.getElementById("tdBill2View").style.display = "";
        document.getElementById("paymentConfirm1").style.display = "none";
        document.getElementById("createFeeForm.paymentConfirmView").style.display = "none";
        document.getElementById("dateConfirm1").style.display = "none";
        document.getElementById("createFeeForm.dateConfirmView").style.display = "none";
        dijit.byId("Save3").domNode.style.display = "";
        dijit.byId("Deny").domNode.style.display = "";
        clearAttFile("sendForm.attachFileView");
        getAttacthFileView(item.paymentInfoId, 25, "sendForm.attachFileView");
        document.getElementById("browsesendForm.attachFileView").style.display = "none";
        dlgfeeViewManage.show();
    };
    page.ShowCommentReject = function(inData)
    {
        var row = inData;
        var item = dijit.byId("FeeGrid").getItem(row);
        msg.alert(item.commentReject);
    };
    page.search();

</script>
