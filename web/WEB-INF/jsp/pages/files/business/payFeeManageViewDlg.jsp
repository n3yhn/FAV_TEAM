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
<form id="createFeeForm2" name="createFeeForm2">
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
                <div id="createFeeForm2.paymentPersonView"/>
            </td>
            <td align="right">
                <label><b>Ngày nộp tiền: </b></label>
            </td>
            <td>
                <div id="createFeeForm2.paymentDateView"/>
            </td>
        </tr>

        <tr>
            <td align="right">
                <label><b>Kiểu thanh toán: </b></label>
            </td>
            <td>
                <div id="createFeeForm2.paymentTypeView"/>
            </td>
               <td id="paymentConfirm1" align="right" style="display: none;">
                <label><b>Người xác nhận: </b></label>
            </td>
            <td>
                <div id="createFeeForm2.paymentConfirmView" style="display: none;"/>
            </td>

        </tr>

        <tr>
            <td align="right">
                <label><b>Giá tiền: </b></label>
            </td>
            <td>
                <div id="createFeeForm2.paymentCostView"/>
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
        <tr>
            <td id="paymentCodeView1" align="right">
                <label id="paymentCodeView3"><b>Mã hóa đơn: </b></label>
            </td>
            <td id="paymentCodeView2">
                <div id="createFeeForm2.paymentCodeView"/>
            </td>
            
                   <td id="billCodeView1" align="right">
                <label id="billCodeView3"><b>Ký hiệu hóa đơn: </b></label>
            </td>
            <td id="billCodeView2">
                <div id="createFeeForm2.billCodeView"/>
            </td>

        </tr>

        <tr style="text-align: center">
            <td colspan="4">

                <sx:ButtonClose id="closeView" onclick="page.close()" />
            </td>
        </tr>
    </table>
</form>


<script>
    page.close = function() {
        dlgfeeViewManage.hide();
    };
</script>