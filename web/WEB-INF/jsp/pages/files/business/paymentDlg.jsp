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
                <sx:Label key="Người nộp tiền" />
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createFeeForm.paymentPerson"
                            name="createFeeForm.paymentPerson"
                            key=""
                            maxlength="250"
                            readonly="true"
                           
                            />
            </td>
            <td align="right">
                <sx:Label key="Ngày nộp tiền" />
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createFeeForm.paymentDate"
                            name="createFeeForm.paymentDate"
                            key=""
                            maxlength="250"
                            readonly="true"
                            />
            </td>
        </tr>

        <tr>
            <td align="right">
                <sx:Label key="Kiểu thanh toán" />
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createFeeForm.paymentType"
                            name="createFeeForm.paymentType"
                            key=""
                            maxlength="250"
                            readonly="true"
                            />
            </td>
           

        </tr>

        <tr>
            <td align="right">
                <sx:Label key="Giá tiền" />
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createFeeForm.paymentCost"
                            name="createFeeForm.paymentCost"
                            key=""
                            maxlength="250"
                            readonly="true"
                            />
            </td>

            <td align="right">
                <sx:Label key="Hóa đơn" />
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createFeeForm.paymentBill"
                            name="createFeeForm.paymentBill"
                            key=""
                            maxlength="250"
                           readonly="true"
                            />
            </td>

        </tr>




        <tr style="text-align: center">
            <td colspan="4">

                <sx:ButtonClose onclick="page.close5()" />
            </td>
        </tr>
    </table>
</form>

<script>



    page.close5 = function() {
        
        dlgPayment.hide();

    };



</script>