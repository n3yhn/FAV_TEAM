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
                <sx:Label key="Người nộp tiền" require="true" />
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createFeeForm.paymentPerson"
                            name="createFeeForm.paymentPerson"
                            key=""
                            maxlength="250"


                            />
            </td>
            <%-- <td align="right" >
                 <sx:Label key="Ngày nộp tiền" require="true"/>
             </td>
             <td>
                 <sx:DatePicker key="" 
                                id="createFeeForm.paymentDate" 
                                name="createFeeForm.paymentDate" 
                                format="dd/MM/yyyy" 
                                cssStyle="width:100%"/>
             </td>
         </tr> --%>

        <tr>
            <%--  <td align="right">
                  <sx:Label key="Kiểu thanh toán" require="true"/>
              </td> --%>
            <td>
                <sd:SelectBox id="createFeeForm.paymentTypeNew" name="searchFeeFormNew.paymentTypeNew" key=""  cssStyle="display:none;width:100%" readonly="true" >
                   <!-- <sd:Option value='-1'>-- Chọn --</sd:Option> -->
                    <sd:Option value='2'>Tiền mặt</sd:Option>


                </sd:SelectBox>
            </td>


        </tr>

        <tr>
            <td align="right" >
                <sx:Label key="Mã hóa đơn" require="true" />
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createFeeForm.paymentCode"
                            name="createFeeForm.paymentCode"
                            key=""
                            maxlength="250"


                            />
            </td>

            <td align="right" >
                <sx:Label key="Ký hiệu hóa đơn" require="true" />
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createFeeForm.billCode"
                            name="createFeeForm.billCode"
                            key=""
                            maxlength="250"


                            />
            </td>
        </tr>




        <tr style="text-align: center">
            <td colspan="4">
                <sd:Button id="Save1" key="" onclick="page.save();" cssStyle="display" >
                    <img src="${contextPath}/share/images/icons/Answer.png" height="14" width="18">
                    <span style="font-size:12px">Xác nhận</span>
                </sd:Button>
                <sx:ButtonClose id="close" onclick="page.close1()" />
            </td>
        </tr>
    </table>
</form>
<sd:TextBox key="" id="createFeeForm.paymentInfoId" name="createFeeForm.paymentInfoId" cssStyle="display:none" />
<%--<sd:TextBox key="" id="createFeeForm.paymentTypeNew" name="createFeeForm.paymentTypeNew" cssStyle="display:none" />--%>

<script>


    page.save = function()
    {
        var paymentInfoId = dijit.byId("createFeeForm.paymentInfoId").getValue();
        var paymentCode = dijit.byId("createFeeForm.paymentCode").getValue();
        var paymentPerson = dijit.byId("createFeeForm.paymentPerson").getValue();
        var billCode = dijit.byId("createFeeForm.billCode").getValue();
        if (paymentPerson.trim().length <= 0)
        {
            msg.alert("Tên người nộp tiền chưa nhập!");
        } else {
            if (paymentCode.trim().length <= 0)
            {
                msg.alert("Mã hóa đơn chưa nhập!");
            }
            else {
                if (billCode.trim().length <= 0)
                {
                    msg.alert("Ký hiệu hóa đơn chưa nhập!");
                }
                else
                {
                    sd.connector.post("feeAction!onSavePaymentCash.do?paymentInfoId=" + paymentInfoId + "&" + token.getTokenParamString(), null, "createFeeForm", null, page.afterSavePaymentNew);
                }
            }
        }


    };


    page.afterSavePaymentNew = function()
    {
        dijit.byId("feePayManageDlg").hide();
        page.cleardlg();
        msg.alert("Xác nhận thành công !");
        page.search();
    };


    page.cleardlg = function()
    {

        //dijit.byId("createFeeForm.paymentType").setValue("");
//        dijit.byId("createFeeForm.paymentCost").setValue("");
//        dijit.byId("createFeeForm.paymentDate").setValue("");
        dijit.byId("createFeeForm.paymentInfoId").setValue("");
        dijit.byId("createFeeForm.paymentCode").setValue("");
        dijit.byId("createFeeForm.billCode").setValue("");
//        dijit.byId("createFeeForm.paymentTypeNew").setValue(-1);
//        dijit.byId("createFeeForm.paymentPerson").setValue("");
//        clearAttFile("sendForm.attachFile");
    };

    page.close1 = function() {
        dijit.byId("createFeeForm.paymentInfoId").setValue("");
        dijit.byId("createFeeForm.paymentCode").setValue("");
        dijit.byId("createFeeForm.billCode").setValue("");
        dlgfeePayManage.hide();
    };
</script>