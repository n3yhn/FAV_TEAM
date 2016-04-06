<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="tags" tagdir="/WEB-INF/tags" %>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<sx:ResultMessage id="resultMessage" />
<form id="createFeeForm" name="createFeeForm">
    <table width="100%;" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="25%"></td>
            <td width="25%"></td>
            <td width="25%"></td>
            <td width="25%"></td>
        </tr>
        <tr>
            <td align="right" >
                <label><b>Người nộp tiền: </b></label>
            </td>
            <td>
                <div id="createFeeForm.paymentPersonView"/>
            </td>
            <td align="right">
                <label><b>Ngày nộp tiền: </b></label>
            </td>
            <td>
                <div id="createFeeForm.paymentDateView"/>
            </td>
        </tr>

        <tr>
            <td align="right">
                <label><b>Kiểu thanh toán: </b></label>
            </td>
            <td>
                <div id="createFeeForm.paymentTypeView"/>
            </td>
            <td id="paymentConfirm1" style="display: none;" align="right">
                <label><b>Người xác nhận: </b></label>
            </td>
            <td>
                <div id="createFeeForm.paymentConfirmView" style="display: none;"/>
            </td>

        </tr>

        <tr>
            <td align="right">
                <label><b>Giá tiền: </b></label>
            </td>
            <td>
                <div id="createFeeForm.paymentCostView"/>
            </td>
            <td align="right" id="tdBill1View">
                <label><b>Hóa đơn: </b></label>
            </td>
            <td id="tdBill2View">
                <sd:TextBox trim="true" cssStyle="width:100%;display:none;" id="documentReceiveAddEditForm.attachIdView" name="documentReceiveAddEditForm.attachIdView" key="" />
                <sx:upload extension="*.pdf,*.doc,*.docx,*.zip,*.rar, *.docx, *.xls, *.xlsx"
                           auto="true" id="sendForm.attachFileView"
                           action="uploadiframe!uploadFile.do?id=sendForm.attachFileView" maxSize="5">
                </sx:upload>
                <sd:TextBox trim="true" cssStyle="width:100%;display:none;" id="sendForm.attachmentIdView" name="sendForm.attachmentIdView" key=""/>
            </td>
        </tr>
        <tr> <td>
                <sd:TextBox key="" id="createFeeForm.paymentId" name="createFeeForm.paymentId" cssStyle="display:none"/>
            </td></tr>
        <tr>
            <td id="paymentCodeView1" align="right">
                <label id="paymentCodeView3"><b>Mã hóa đơn: </b></label>
            </td>
            <td id="paymentCodeView2">
                <div id="createFeeForm.paymentCodeView"/>
            </td>
            <td id="billCode1" align="right">
                <label id="billCode3"><b>Ký hiệu hóa đơn: </b></label>
            </td>
            <td id="billCode2">
                <div id="createFeeForm.billCodeView"/>
            </td>
        </tr>
        <tr>

            <td id="dateConfirm1" align="right">
                <label><b>Ngày xác nhận: </b></label>
            </td>
            <td id="dateConfirm2">
                <div id="createFeeForm.dateConfirmView"/>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label><b>Tên công ty </b></label>
            </td>
            <td>
                <div id="createFeeForm.businessNameView"/>
            </td>
            </td>
            <td align="right">
                <label><b>Địa chỉ </b></label>
            </td>
            <td>
                <div id="createFeeForm.businessAddressView"/>

            </td>
        </tr>
        <tr style="text-align: center">
            <td colspan="4">
                <sd:Button id="Save3" key="" onclick="page.CheckCash();" cssStyle="display:none" >
                    <img src="${contextPath}/share/images/icons/Answer.png" height="14" width="18">
                    <span style="font-size:12px">Xác nhận</span>
                </sd:Button>
                <sd:Button id="Deny" key="" onclick="page.ShowDeny();" cssStyle="display:none" >
                    <img src="${contextPath}/share/images/icons/unapproved.png" height="14" width="18">
                    <span style="font-size:12px">Từ chối</span>
                </sd:Button>
                <sd:Button id="Save4" key="" onclick="page.CheckOnline();" cssStyle="display:none" >
                    <img src="${contextPath}/share/images/icons/Answer.png" height="14" width="18">
                    <span style="font-size:12px">Xác nhận</span>
                </sd:Button>
                <sx:ButtonClose id="closeView" onclick="page.close()" />
            </td>
        </tr>
        <tr id="commentField" style="display:none;" >
            <td>
                <label><b>Nhập lý do từ chối </b></label>
            </td>
            <td>
                <sd:Textarea key="" id="createFeeForm.commentReject" name="createFeeForm.commentReject" cssStyle="display;width:100%;"/>
            </td>
            <td>
                <sd:Button id="CheckDeny" key="" onclick="page.Deny();" cssStyle="display" >
                    <img src="${contextPath}/share/images/icons/unapproved.png" height="14" width="18">
                    <span style="font-size:12px">Lưu</span>
                </sd:Button>
            </td>
        </tr>
    </table>
</form>


<script>
    page.close = function() {
        dijit.byId("Save4").domNode.style.display = "none";
        dijit.byId("Save3").domNode.style.display = "none";
        dijit.byId("Deny").domNode.style.display = "none";
        document.getElementById("commentField").style.display = "none";
        dlgfeeViewManage.hide();
    };
    page.CheckOnline = function() {

        var paymentInfoId = dijit.byId("createFeeForm.paymentId").getValue();
        sd.connector.post("feeAction!onSavePaymentOnline.do?paymentInfoId=" + paymentInfoId + "&" + token.getTokenParamString(), null, null, null, page.afterSavePaymentNew1);
    };
    page.Deny = function()
    {
        var paymentInfoId = dijit.byId("createFeeForm.paymentId").getValue();
        //var commentReject = dijit.byId("createFeeForm.commmentReject").getValue();
        var commentReject = page.utf8_to_b64(dijit.byId("createFeeForm.commentReject").getValue());
        commentReject = commentReject.replaceAll('+', '_');
        sd.connector.post("feeAction!onDeny.do?paymentInfoId=" + paymentInfoId + "&commentReject=" + commentReject + "&" + token.getTokenParamString(), null, null, null, page.afterDeny);
    };
    page.ShowDeny = function()
    {
        document.getElementById("commentField").style.display = "";
    }
    page.utf8_to_b64 = function(str) {
        return window.btoa(unescape(encodeURIComponent(str)));
    };
    page.CheckCash = function()
    {
        var paymentInfoId = dijit.byId("createFeeForm.paymentId").getValue();

        sd.connector.post("feeAction!onSavePaymentFinal.do?paymentInfoId=" + paymentInfoId + "&" + token.getTokenParamString(), null, null, null, page.afterSavePaymentNew2);
    };
    page.afterSavePaymentNew2 = function()
    {
        dijit.byId("Save3").domNode.style.display = "none";
        dlgfeeViewManage.hide();
        msg.alert("Xác nhận thành công !");
        page.search();
    };
    page.afterSavePaymentNew1 = function()
    {
        dijit.byId("Save4").domNode.style.display = "none";
        dlgfeeViewManage.hide();
        msg.alert("Xác nhận thành công !");
        page.search();
    };
    page.afterDeny = function()
    {
        dijit.byId("Deny").domNode.style.display = "none";
        dlgfeeViewManage.hide();
        msg.alert("Từ chối thành công !");
        page.search();
    };
    page.ShowDeny = function()
    {
        document.getElementById("commentField").style.display = "";
    };
    String.prototype.replaceAll = function(
            strTarget,
            strSubString
            ) {
        var strText = this;
        var intIndexOfMatch = strText.indexOf(strTarget);
        while (intIndexOfMatch != -1) {
            strText = strText.replace(strTarget, strSubString)
            intIndexOfMatch = strText.indexOf(strTarget);
        }
        return(strText);
    };


</script>