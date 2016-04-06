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
                    <td><input type="text" name="merchant_code" value="898980"
                               size="20" maxlength="8" /></td>
                    <!--                    <td><input type="text" name="merchant_code" value="898980"
                                                   size="20" maxlength="8" /></td>-->
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

<script>

    page.pay = function()
    {
        document.getElementById("amount").value = '${amount}';
        document.getElementById("shipfee").value = '0';
        var feeInfoId = '${fpiId}';
        var calink = '${fn:escapeXml(calink)}';
        var fileCode = '${fn:escapeXml(fileCode)}';
        var businessName = b64_to_utf8('${fn:escapeXml(businessName)}');
        var productName = b64_to_utf8('${fn:escapeXml(productName)}');
        var xmlDes = change_alias(businessName) + ";" + change_alias(productName);
        document.getElementById("good_code").value = fileCode;
        document.getElementById("xml_description").value = xmlDes;
        var url = calink + "&files_code=" + fileCode + "&feeInfoId=" + feeInfoId;
        document.getElementById("returnUrl").value = url;
        document.getElementById("pay").submit();
    };

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

    page.pay();

</script>
