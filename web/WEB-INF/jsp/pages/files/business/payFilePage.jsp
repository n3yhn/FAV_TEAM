<%@page import="java.util.ResourceBundle"%>
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
    page.getNo = function (index) {
        return dijit.byId("FeeGrid").currentRow + index + 1;
    };
    page.getIndex = function (index) {
        return index + 1;
    };
    page.formatEdit = function (inData) {
        var row = inData - 1;
        var item = dijit.byId("FeeGrid").getItem(row);
        var url = "";
        if (item != null) {

            url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                        title='<sd:Property>category.edit</sd:Property>' \n\
                        onClick='page.showEditPopup(" + row + ");' /></div>";
        }
        return url;
    };
    page.formatStatus = function (inData) {
        var item = dijit.byId("FeeGrid").getItem(inData - 1);
        var url = "";
        return url;
    };
    page.formatfeeStatus = function (inData) {
        var row = inData - 1;
        var item = dijit.byId("FeeGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var isStatus = parseInt(item.status);
            switch (isStatus) {
                case 1:

                    url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/icons/approve.png' width='17px' height='17px' \n\
                         title='<sd:Property>Xem thông tin thanh toán</sd:Property>' \n\
                        onClick='page.showTrasferPayment(" + row + ");' /></div>";
                    break;
                case 2:
//                    url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/icons/bill.png' width='17px' height='17px' \n\
//                         title='<sd:Property>Cập nhật tiền mặt</sd:Property>' \n\
//                        onClick='page.editPayment(" + row + ");' /> ";
//                    url += " | <img src='share/images/icons/attach.png' width='17px' height='17px' title='Cập nhật chuyển khoản' onClick='page.editPaymentNew(" + row + ");' />";
                    url += " | <img src='share/images/icons/view.png' width='17px' height='17px' title='Xem lý do từ chối' onClick='page.ShowCommentReject(" + row + ");' /></div>";
                    break;
                case 3:
                    url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/icons/process_icon.png' width='17px' height='17px' \n\
                         title='<sd:Property>Chờ xử lý</sd:Property>' \n\
                        onClick='page.showTrasferPayment(" + row + ");' /></div>";
                    break;
                case 4:
                    url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/icons/process_icon.png' width='17px' height='17px' \n\
                         title='<sd:Property>Chờ xử lý</sd:Property>' \n\
                       onClick='page.showTrasferPayment(" + row + ");' /></div>";
                    break;
                case 5:
                    url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/icons/process_icon.png' width='17px' height='17px' \n\
                         title='<sd:Property>Chờ xử lý</sd:Property>' \n\
                       onClick='page.showTrasferPayment(" + row + ");' /></div>";
                    break;
                default:
                    url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/icons/payment.png' width='17px' height='17px' \n\
                        title='<sd:Property>Keypay</sd:Property>' \n\
                        onClick='page.pay(" + row + ");' /> ";
                    url += " | <img src='share/images/icons/attach.png' width='17px' height='17px' title='Chuyển khoản' onClick='page.payFeeTransfer(" + row + ");' />";
//                    url += " | <img src='share/images/icons/bill.png' width='17px' height='17px' title='Tiền mặt' onClick='page.payFeeCash(" + row + ");' /></div>";
            }
        }
        return url;
    };
    page.formatfeeType = function (inData) {

        var item = dijit.byId("FeeGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var isFee = parseInt(item.feeType);
            switch (isFee) {
                case 1:
                    url = "Lệ phí";
                    break;
                case 2:
                    url = "Nộp phí";
                    break;
                default:
                    url = "Không rõ";
            }
        }
        return url;
    };
    page.formatfeePaymentType = function (inData)
    {

        var item = dijit.byId("FeeGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var isFeePaymentType = parseInt(item.feePaymentType);
            switch (isFeePaymentType) {
                case 1:
                    url = "Thanh toán online";
                    break;
                case 3:
                    url = "Chuyển khoản";
                    break;
                case 2:
                    url = "Tiền mặt";
                    break;
                default:
                    url = "Chưa thanh toán";
            }
        }
        return url;
    };
    page.disabledCheckbox = function (inData) {
        var item = dijit.byId("FeeGrid").getItem(inData);
        var check = false;
        return check;
    };
    // enter key
    page.searchDefault = function (evt) {
        var dk = dojo.keys;
        switch (evt.keyCode) {
            case dk.ENTER:
                page.search();
                break;
        }
    }

    dojo.connect(dojo.byId("searchFeeForm"), "onkeypress", page.searchDefault);
    </script>
    <br/>
    <div style="text-align: left;color: red">
        <div style="width: 100%;"><b><span>Khuyến cáo:</span></b></div>        
        Doanh nghiệp cần cân nhắc, xem xét kỹ hồ sơ trước khi thực hiện thanh toán. Các khoản phí sau khi được thanh toán sẽ không được hoàn trả lại với những hồ sơ lỗi hoặc không đủ điều kiện được phê duyệt cấp số!
        <br/>
    </div>
    <br/>
    <div style="text-align: left;color: red">
        <div style="width: 100%;"><b><span>Yêu cầu:</span></b></div>
        1. Scan đồng thời cả biên lai thu phí và giấy thông báo nộp tiền đối với hồ sơ nộp theo hình thức tiền mặt.
        <br/>
        2. Hồ sơ nộp tiền mặt, thông tin phần ghi mã hóa đơn ghi số series của biên lai nộp tiền, còn ký hiệu hóa đơn là ký hiệu nằm phiá trên của series biên lai.
        <br/>
        3. Scan ủy nhiệm chi hoặc giấy nộp tiền đối với hồ sơ nộp theo hình thức chuyển khoản.
        <br/>
        4. Các loại giấy tờ yêu cầu phải scan màu.
        <br/>
        5. Đối với công ty tư vấn nộp hộ tiền cho công ty công bố sản phẩm phải scan hợp đồng giữa 2 bên trong trường hợp bên tư vấn lấy biên lai nộp phí, lệ phí để thanh toán cho công ty mình.
        <br/>
        6. Chỉ nhận những hồ sơ có biên lai thu phí, lệ phí từ ngày 9/9/2014.
        <br/>
    </div>
    <br/>
    <div id ="searchFeeFileDiv" style="display:none">

    <sd:TitlePane key="search.searchCondition" id="FeeTitle">
        <form id="searchFeeForm" name="searchFeeForm">
            <table width="100%;" cellpadding="0px" cellspacing="5px">
                <tr>
                    <td width="20%"></td>
                    <td width="30%"></td>
                    <td width="20%"></td>
                    <td width="30%"></td>
                </tr>
                <tr>

                    <td align="right">
                        <sd:Label key="Tên phí"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchFeeForm.feeName"
                                    name="searchFeeForm.feeName"
                                    key=""
                                    maxlength="250"


                                    />
                    </td>
                    <td align="right">
                        <sd:Label key="Giá tiền"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchFeeForm.price"
                                    name="searchFeeForm.price"
                                    key=""
                                    maxlength="18"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Trạng thái"/>
                    </td>
                    <td>
                        <sd:SelectBox id="searchFeeForm.status" name="searchFeeForm.status" key=""  cssStyle="width:100%" >
                            <sd:Option value='-1'>-- Chọn --</sd:Option>
                            <sd:Option value='1'>Đã thanh toán</sd:Option>
                            <sd:Option value='0'>Chưa thanh toán</sd:Option>

                        </sd:SelectBox>
                    </td>
                    <td align="right">
                        <sd:Label key="Loại phí"/>
                    </td>
                    <td>
                        <sd:SelectBox id="searchFeeForm.feeType" name="searchFeeForm.feeType" key=""  cssStyle="width:100%" >
                            <sd:Option value='-1'>-- Chọn --</sd:Option>
                            <sd:Option value='1'>Lệ phí</sd:Option>
                            <sd:Option value='2'>Nộp phí</sd:Option>

                        </sd:SelectBox>
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

                        <sd:ColumnDataGrid  key="Tên phí" field="feeName"
                                            width="5%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Mô tả" field="description"
                                            width="5%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Giá tiền(VND)" field="price"
                                            width="3%"  headerStyles="text-align:center;" />                      
                        <sd:ColumnDataGrid  key="Loại phí" get="page.getIndex" formatter="page.formatfeeType" cellStyles="text-align:center;"
                                            width="3%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Hình thức thanh toán" get="page.getIndex" formatter="page.formatfeePaymentType" cellStyles="text-align:center;"
                                            width="3%"  headerStyles="text-align:center;" /> 
                        <sd:ColumnDataGrid  key="Người xác nhận" field="paymentConfirm"
                                            width="5%"  headerStyles="text-align:center;"  styles="text-align:left;"/>
                        <sd:ColumnDataGrid  key="Ngày xác nhận" field="dateConfirm"
                                            width="5%"  headerStyles="text-align:center;"  styles="text-align:center;"/>
                        <sd:ColumnDataGrid  key="Thanh toán" get="page.getIndex" formatter="page.formatfeeStatus" cellStyles="text-align:center;"
                                            width="3%"  headerStyles="text-align:center;" />

                    </sd:DataGrid>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <sx:ButtonBack onclick="backPage();"/>
                <sx:ButtonSearch id="btnShowSearchPanel" onclick="page.showSearchPanel();" />
            </td>
        </tr>
    </table>
</sd:TitlePane>
<sd:TextBox key="" id="createFeeForm.fileId" name="createFeeForm.fileId" cssStyle="display:none" />

<%
    SimpleDateFormat sdf1 = new SimpleDateFormat("HHmmss");
    Date curDate = new Date();

    int[] fee_ship = {0, 20000, 10000, 15000, 35000, 40000};
    int randomFeeShip = (fee_ship[new Random().nextInt(fee_ship.length)]);
    int[] netCost = {20000000, 12000000, 6750000, 8640000, 14500000, 3500000};
    int randomNetCost = (netCost[new Random().nextInt(netCost.length)]);

    String return_url = "http://localhost:8661/sample_jsp/result.jsp";
    //String return_url = "filesAction!preparePayment.do?";
%>

<form id="pay" action="do" method="post" style="display: none">
    <input type="hidden" name="Title" value="VPC 3-Party" disabled />
    <table width="100%" align="center" border="0" cellpadding='0'
           cellspacing='0'>
        <tr class="shade">
            <td width="1%">&nbsp;</td>
            <td width="40%" align="right"><strong><em>URL cổng thanh toán - Virtual Payment Client
                        URL:&nbsp;</em></strong></td>
            <td width="59%"><input type="text"
                                   size="63" value="http://113.160.58.22:5555/keypay/process"
                                   maxlength="250" /></td>
        </tr>
    </table>
    <center>
        <input id="xml_description" type="hidden" name="xml_description" value="xml_desc">
        <input type="hidden" name="bank_code" value="">
        <table class="background-image" summary="Meeting Results">
            <thead>
                <tr>
                    <th scope="col" width="250px">Name</th>
                    <th scope="col" width="250px">Input</th>
                    <th scope="col" width="250px">Chú thích</th>
                    <th scope="col">Description</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><strong><em>Merchant Name</em></strong></td>
                    <td><input type="text" value="KeyPay Test" size="20"
                               maxlength="16" /></td>
                    <td>Được cấp bởi KeyPay</td>
                    <td>Provided by KeyPay</td>
                </tr>
                <tr>
                    <td><strong><em>Merchant Code</em></strong></td>
                    <!--                                        <td><input type="text" name="merchant_code" value="312"
                                                                       size="20" maxlength="8" /></td>-->
                    <td><input type="text" name="merchant_code" value="898980"
                               size="20" maxlength="8" /></td>
                    <td>Được cấp bởi KeyPay</td>
                    <td>Provided by KeyPay</td>
                </tr>
                <tr>
                    <td><strong><em>Merchant Transaction ID</em></strong></td>
                    <td><input type="text" name="merchant_trans_id"
                               value="<%= sdf1.format(curDate)%>" size="20"
                               maxlength="40" /></td>
                    <td>ID giao dịch, giá trị phải khác nhau trong mỗi lần thanh toán (tối đa 6 ký tự)</td>
                    <td>ID Transaction - (unique per transaction) - (max 40 char)</td>
                </tr>
                <tr>
                    <td><strong><em>Good Code</em></strong></td>
                    <td><input id="good_code" type="text" name="good_code" value="<%= "GC" + sdf1.format(curDate)%>"
                               size="50" maxlength="50" /></td>
                    <td>Mã đơn hàng - (tối đa 50 ký tự)</td>
                    <td>Good Code will show on payment gateway (max 20 char)</td>
                </tr>
                <tr>
                    <td><strong><em>Purchase Amount</em></strong></td>
                    <td><input id="amount" type="text" name="net_cost" value="<%=randomNetCost%>" size="20"
                               maxlength="10" /></td>               
                    <td>Số tiền cần thanh toán (VNĐ)</td>
                    <td>Amount (VNĐ)</td>
                </tr>
                <tr>
                    <td><strong><em>Ship Fee</em></strong></td>
                    <td><input id ="shipfee" type="text" name="ship_fee" value="<%=randomFeeShip%>" size="20"
                               maxlength="10" /></td>
                    <td>Số tiền vận chuyển hàng (VNĐ)</td>
                    <td>Ship Fee (VNĐ)</td>
                </tr>
                <tr>
                    <td><strong><em>Tax</em></strong></td>
                    <td><input type="text" name="tax" value="0" size="20"
                               maxlength="10" /></td>
                    <td>Thuế (VNĐ)</td>
                    <td>Tax</td>
                </tr>
                <tr>
                    <td><strong><em>Receipt ReturnURL</em></strong></td>
                    <td><input id="returnUrl" type="text" name="return_url" size="45"
                               value="<%=return_url%>"
                               maxlength="250" /></td>
                    <td>Url nhận kết quả trả về sau khi giao dịch hoàn thành.</td>
                    <td>URL for receiving payment result from gateway</td>
                </tr>
                <tr>
                    <td><strong><em>Version</em></strong></td>
                    <td><input type="text" name="version" value="1.0" size="20"
                               maxlength="8" /></td>
                    <td>Phiên bản module (cố định)</td>
                    <td>Version (fixed)</td>
                </tr>
                <tr>
                    <td><strong><em>Command Type</em></strong></td>
                    <td><input type="text" name='command' value='pay' size="20"
                               /></td>
                    <td>Loại request (cố định)</td>
                    <td>Command Type(fixed)</td>
                </tr>
                <tr>
                    <td><strong><em>Payment Server Display Language Locale</em></strong></td>
                    <td><input type="text" name='current_locale' value='vn' size="20" /></td>
                    <td>Ngôn ngữ hiện thị trên cổng (vn/en)</td>
                    <td>Language use on gateway (vn/en)</td>
                </tr>
                <tr>
                    <td><strong><em>Currency code</em></strong></td>
                    <td><input type="text" name="currency_code" value='704' size="20" /></td>
                    <td>Loại tiền tệ (VND)</td>
                    <td>Currency (VND)</td>
                </tr>
                <tr>
                    <td><strong><em>Service code</em></strong></td>
                    <td><input type="text" name="service_code" value="720" size="20"
                               maxlength="5" /></td>
                    <td>Mã dịch vụ</td>
                    <td>Service code</td>
                </tr>
                <tr>
                    <td><strong><em>Country code</em></strong></td>
                    <td><input type="text" name="country_code" value="+84" size="20"
                               maxlength="5" /></td>
                    <td>Mã quốc gia</td>
                    <td>Country code</td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td align="center" colspan="4"><input type="submit" value="Pay Now!" /></td>
                </tr>
            </tfoot>
        </table>

    </center>
</form>
<sd:Dialog  id="paymentDlg" height="auto" width="600px"
            key="Xem chi tiết thanh toán" showFullscreenButton="false"
            >
    <div id="paymentDiv">
        <jsp:include page="paymentDlg.jsp" flush="false"/>
    </div>
</sd:Dialog> 
<sd:Dialog  id="feeManageDlg" height="auto" width="600px"
            key="Thanh toán" showFullscreenButton="false"
            >
    <div id="feeManageDiv">
        <jsp:include page="payFileFeeManageDlg.jsp" flush="false"/>
    </div>
</sd:Dialog> 
<sd:Dialog  id="feeManageViewDlg" height="auto" width="600px"
            key="Chi tiết thanh toán" showFullscreenButton="false"
            >
    <div id="feeManageViewDiv">
        <jsp:include page="payFeeManageViewDlg.jsp" flush="false"/>
    </div>
</sd:Dialog> 
<script>
    var insertDialog = true;
    var grid = dijit.byId("FeeGrid");
    var dlgPayment = dijit.byId("paymentDlg");
    var fileId = dijit.byId("createFeeForm.fileId").getValue();
    var dlgfeeManage = dijit.byId("feeManageDlg");
    var dlgfeeViewManage = dijit.byId("feeManageViewDlg");
    page.search = function () {
        grid.vtReload('filesAction!onSearchPayment.do?fileId=' + fileId, "searchFeeForm", null, null);
    };
    page.showSearchPanel = function () {
        var panel = document.getElementById("searchFeeFileDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };
    page.pay = function (inData)
    {
        var row = inData;
        var item = dijit.byId("FeeGrid").getItem(row);
        document.getElementById("amount").value = item.price;
        document.getElementById("shipfee").value = '0';
        var feeId = item.feeId;
        var feeInfoId = item.paymentInfoId;
        var calink = '${fn:escapeXml(calink)}';
        var fileCode = '${fn:escapeXml(fileCode)}';
        var businessName = b64_to_utf8('${fn:escapeXml(businessName)}');
        var productName = b64_to_utf8('${fn:escapeXml(productName)}');
        var xmlDes = change_alias(businessName) + ";" + change_alias(productName);
        document.getElementById("good_code").value = fileCode;
        document.getElementById("xml_description").value = xmlDes;
        var url = calink + fileId + "&feeId=" + feeId + "&feeInfoId=" + feeInfoId;
        document.getElementById("returnUrl").value = url;
        document.getElementById("pay").submit();
    };
//    page.b64_to_utf8 = function(str) {
//        return unescape(decodeURIComponent(window.atob(str)));
//    }
    function change_alias(alias)
    {
        var str = alias;
        str = str.toLowerCase();
        str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ  |ặ|ẳ|ẵ/g, "a");
        str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
        str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
        str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ  |ợ|ở|ỡ/g, "o");
        str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
        str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
        str = str.replace(/đ/g, "d");
        str = str.replace(/!|@|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\;|\'| |\"|\&|\#|\[|\]|~|$|_/g, " ");
        /* tìm và thay thế các kí tự đặc biệt trong chuỗi sang kí tự - */
        str = str.replace(/-+-/g, "-"); //thay thế 2- thành 1-
        str = str.replace(/^\-+|\-+$/g, "");
        //cắt bỏ ký tự - ở đầu và cuối chuỗi 
        return str;
    }


    function b64_to_utf8(str) {
        return decodeURIComponent(escape(window.atob(str)));
    }
    page.showPayment = function (inData)
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
        //alert(item.price);
        dijit.byId("createFeeForm.paymentPerson").setValue(item.paymentPerson);
        dijit.byId("createFeeForm.paymentDate").setValue(item.paymentDate);
        dijit.byId("createFeeForm.paymentType").setValue(payType);
        //dijit.byId("createFeeForm.paymentInfo").setValue(item.paymentInfo);
        dijit.byId("createFeeForm.paymentCost").setValue(item.price);
        dijit.byId("createFeeForm.paymentBill").setValue(item.billPath);
        dlgPayment.show();
    };
    page.payFeeTransfer = function (inData)
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
        //alert(item.price);
        //dijit.byId("createFeeForm.paymentDate").setValue(item.paymentDate);
        //dijit.byId("createFeeForm1.paymentType").setValue(payType);
        dijit.byId("createFeeForm1.paymentCost").setValue(item.price);
        //dijit.byId("createFeeForm.paymentBill").setValue(item.billPath);
        dijit.byId("createFeeForm1.paymentInfoId").setValue(item.paymentInfoId);
        dijit.byId("createFeeForm1.paymentTypeNew").setValue(3);
        document.getElementById("paymentCode1").style.display = "none";
        document.getElementById("paymentCode2").style.display = "none";
        document.getElementById("billCode1").style.display = "none";
        document.getElementById("billCode2").style.display = "none";
        dijit.byId("Save1").domNode.style.display = "";
        dlgfeeManage.show();
    };
    page.payFeeCash = function (inData)
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
        //alert(item.price);
        //dijit.byId("createFeeForm.paymentDate").setValue(item.paymentDate);
        //dijit.byId("createFeeForm1.paymentType").setValue(payType);
        dijit.byId("createFeeForm1.paymentCost").setValue(item.price);
        //dijit.byId("createFeeForm.paymentBill").setValue(item.billPath);
        dijit.byId("createFeeForm1.paymentInfoId").setValue(item.paymentInfoId);
        dijit.byId("createFeeForm1.paymentTypeNew").setValue(2);
        document.getElementById("paymentCode1").style.display = "";
        document.getElementById("paymentCode2").style.display = "";
        document.getElementById("billCode1").style.display = "";
        document.getElementById("billCode2").style.display = "";
        dijit.byId("Save1").domNode.style.display = "";
        dlgfeeManage.show();
    };
    page.showTrasferPayment = function (inData)
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

        var parts = [];
        parts = item.paymentDate.toString().split("-");
        var part1 = parts[0]; // 004
        var part2 = parts[1];
        var part3 = parts[2];
        var paymentDate = part3 + "-" + part2 + "-" + part1;
        document.getElementById("createFeeForm2.paymentDateView").innerHTML = paymentDate;
        document.getElementById("createFeeForm2.paymentTypeView").innerHTML = payType;
        document.getElementById("createFeeForm2.paymentCostView").innerHTML = item.price;
        document.getElementById("createFeeForm2.paymentPersonView").innerHTML = item.paymentPerson;
        //document.getElementById("createFeeForm2.paymentConfirmView").innerHTML = item.paymentConfirm;
        if (item.status != null && item.status == 1)
        {
            document.getElementById("paymentConfirm1").style.display = "";
            document.getElementById("createFeeForm2.paymentConfirmView").innerHTML = item.paymentConfirm;
            document.getElementById("createFeeForm2.paymentConfirmView").style.display = "";
        }
        if (item.feePaymentType == 2)
        {
            document.getElementById("createFeeForm2.paymentCodeView").innerHTML = item.paymentCode;
            document.getElementById("createFeeForm2.billCodeView").innerHTML = item.billCode;
            document.getElementById("paymentCodeView1").style.display = "";
            document.getElementById("paymentCodeView2").style.display = "";
            document.getElementById("billCodeView1").style.display = "";
            document.getElementById("billCodeView2").style.display = "";
        }
        if (item.feePaymentType == 3)
        {
            document.getElementById("paymentCodeView1").style.display = "none";
            document.getElementById("paymentCodeView2").style.display = "none";
            document.getElementById("billCodeView1").style.display = "none";
            document.getElementById("billCodeView2").style.display = "none";
        }
        document.getElementById("tdBill1View").style.display = "";
        document.getElementById("tdBill2View").style.display = "";
        clearAttFile("sendForm.attachFileView");
        getAttacthFileView(item.paymentInfoId, 25, "sendForm.attachFileView");
        document.getElementById("browsesendForm.attachFileView").style.display = "none";
        dlgfeeViewManage.show();
    };
    // tien mat
    page.editPayment = function (inData)
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
//        if (item.feePaymentType == 2)
//        {
        dijit.byId("createFeeForm1.paymentCost").setValue(item.price);
        //dijit.byId("createFeeForm.paymentBill").setValue(item.billPath);
        dijit.byId("createFeeForm1.paymentInfoId").setValue(item.paymentInfoId);
        dijit.byId("createFeeForm1.paymentTypeNew").setValue(2);
        document.getElementById("paymentCode1").style.display = "";
        document.getElementById("paymentCode2").style.display = "";
        document.getElementById("billCode1").style.display = "";
        document.getElementById("billCode2").style.display = "";
        dijit.byId("Save1").domNode.style.display = "";
        dlgfeeManage.show();
        //}
//        if (item.feePaymentType == 3)
//        {
//            dijit.byId("createFeeForm1.paymentCost").setValue(item.price);
//            //dijit.byId("createFeeForm.paymentBill").setValue(item.billPath);
//            dijit.byId("createFeeForm1.paymentInfoId").setValue(item.paymentInfoId);
//            dijit.byId("createFeeForm1.paymentTypeNew").setValue(3);
//            document.getElementById("paymentCode1").style.display = "none";
//            document.getElementById("paymentCode2").style.display = "none";
//            document.getElementById("billCode1").style.display = "none";
//            document.getElementById("billCode2").style.display = "none";
//            dijit.byId("Save1").domNode.style.display = "";
//            dlgfeeManage.show();
//        }
    };
    // chuyen khoan
    page.editPaymentNew = function (inData)
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
        dijit.byId("createFeeForm1.paymentCost").setValue(item.price);
        //dijit.byId("createFeeForm.paymentBill").setValue(item.billPath);
        dijit.byId("createFeeForm1.paymentInfoId").setValue(item.paymentInfoId);
        dijit.byId("createFeeForm1.paymentTypeNew").setValue(3);
        document.getElementById("paymentCode1").style.display = "none";
        document.getElementById("paymentCode2").style.display = "none";
        document.getElementById("billCode1").style.display = "none";
        document.getElementById("billCode2").style.display = "none";
        dijit.byId("Save1").domNode.style.display = "";
        dlgfeeManage.show();
    };
    page.ShowCommentReject = function (inData)
    {
        var row = inData;
        var item = dijit.byId("FeeGrid").getItem(row);
        msg.alert(item.commentReject);
    };

    page.search();

</script>
