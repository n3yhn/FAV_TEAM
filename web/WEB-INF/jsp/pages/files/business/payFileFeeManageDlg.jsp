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
<form id="createFeeForm1" name="createFeeForm1">
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
                            id="createFeeForm1.paymentPerson"
                            name="createFeeForm1.paymentPerson"
                            key=""
                            maxlength="250"


                            />
            </td>
            <td align="right" >
                <sx:Label key="Ngày nộp tiền" require="true"/>
            </td>
            <td>
                <sx:DatePicker key="" 
                               id="createFeeForm1.paymentDate" 
                               name="createFeeForm1.paymentDate" 
                               format="dd/MM/yyyy" 
                               cssStyle="width:100%"/>
            </td>
        </tr>

        <tr>

            <td align="right">
                <sx:Label key="Kiểu thanh toán" require="true"/>
            </td>
            <td>
                <sd:SelectBox id="createFeeForm1.paymentTypeNew" name="searchFeeFormNew.paymentTypeNew" key=""  cssStyle="width:100%" readonly="true"  >
                    <sd:Option value='-1'>-- Chọn --</sd:Option>
                    <sd:Option value='3'>Chuyển khoản</sd:Option>
                    <sd:Option value='2'>Tiền mặt</sd:Option>
                </sd:SelectBox>
            </td>


        </tr>

        <tr>
            <td align="right">
                <sx:Label key="Giá tiền" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createFeeForm1.paymentCost"
                            name="createFeeForm1.paymentCost"
                            key=""
                            maxlength="250"                           
                            readonly="true"
                            />
            </td>
            <td align="right" id="tdBill1">
                <sx:Label  key="Hóa đơn" require="true"/>
            </td>
            <td id="tdBill2">
                <sd:TextBox trim="true" cssStyle="width:100%;display:none;" id="documentReceiveAddEditForm.attachId" name="documentReceiveAddEditForm.attachId" key="" />
                <sx:upload extension="*.pdf,*.doc,*.docx,*.zip,*.rar, *.docx, *.xls, *.xlsx"
                           auto="true" id="sendForm.attachFile"
                           action="uploadiframe!uploadFile.do?id=sendForm.attachFile" maxSize="5">
                </sx:upload>
                <sd:TextBox trim="true" cssStyle="width:100%;display:none;" id="sendForm.attachmentId" name="sendForm.attachmentId" key=""/>
            </td>
        </tr>
        <tr>

            <td id="paymentCode1" align="right">
                <sx:Label key="Mã hóa đơn" require="true"/>
            </td>
            <td id = "paymentCode2">
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createFeeForm1.paymentCode"
                            name="createFeeForm1.paymentCode"
                            key=""
                            maxlength="250"                           
                            />
            </td>

            <td id="billCode1" align="right">
                <sx:Label key="Ký hiệu hóa đơn" require="true"/>
            </td>
            <td id = "billCode2">
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createFeeForm1.billCode"
                            name="createFeeForm1.billCode"
                            key=""
                            maxlength="250"                           
                            />
            </td>
        <tr style="text-align: center">
            <td colspan="4">
                <sd:Button id="Save1" key="" onclick="page.confirmPayment();" cssStyle="display" >
                    <img src="${contextPath}/share/images/icons/Answer.png" height="14" width="18">
                    <span style="font-size:12px">Xác nhận</span>
                </sd:Button>
                <sx:ButtonClose id="close" onclick="page.close1()" />
            </td>
        </tr>
    </table>
</form>
<sd:TextBox key="" id="createFeeForm1.paymentInfoId" name="createFeeForm1.paymentInfoId" cssStyle="display:none" />
<%--<sd:TextBox key="" id="createFeeForm1.paymentTypeNew" name="createFeeForm1.paymentTypeNew" cssStyle="display:none" />--%>

<script>


    page.confirmPayment = function() {
        msg.confirm('Sau khi xác nhận thanh toán phí, hồ sơ sẽ được gửi tới đơn vị tiếp nhận, bạn có chắc chắn thực hiện hành động này ?', '<sd:Property>Cảnh báo</sd:Property>', page.save);
            };

            page.save = function()
            {
                var paymentInfoId = dijit.byId("createFeeForm1.paymentInfoId").getValue();
                var paymentType = dijit.byId("createFeeForm1.paymentTypeNew").getValue();
                var paymentPerson = dijit.byId("createFeeForm1.paymentPerson").getValue();
                var cost = dijit.byId("createFeeForm1.paymentCost").getValue();
                var paymentDate = dijit.byId("createFeeForm1.paymentDate").getValue();
                var paymentCode = dijit.byId("createFeeForm1.paymentCode").getValue();

                var billCode = dijit.byId("createFeeForm1.billCode").getValue();
                //var paymentDate = dijit.byId("createFeeForm1.paymentDate").getValue();
                var billPath = getListAttachId("sendForm.attachFile");
                //var now =new Date().format("dd/M/yy").ToString();

                var currentDate = '${sysDate}';
                if (paymentPerson.trim().length <= 0)
                {
                    msg.alert("Nhập tên người nộp tiền");
                } else {
                    if (paymentDate == null)
                    {
                        msg.alert("Chưa nhập ngày gửi hóa đơn chuyển khoản");
                    }
                    else
                    if (paymentDate > currentDate)
                    {
                        msg.alert("Ngày nộp tiền không được lớn hơn ngày hiện tại");
                    }
                    else {
                        if (cost.trim().length <= 0)
                        {
                            msg.alert("Nhập giá tiền");
                        }
                        else {
                            if (paymentType == -1)
                            {
                                msg.alert("Chọn kiểu thanh toán");
                            }
                            else {
                                if (billPath != null && billPath.trim().length > 0)
                                {
                                    if (billPath.toString().indexOf(';') < 1)
                                    {
                                        if (paymentType == 3)
                                        {

                                            sd.connector.post("feeAction!onSavePaymentNew.do?paymentInfoId=" + paymentInfoId + "&paymentType=" + paymentType + "&billPath=" + billPath + "&paymentPerson=" + paymentPerson + "&" + token.getTokenParamString(), null, "createFeeForm1", null, page.afterSavePaymentNew);

                                        }
                                        if (paymentType == 2)
                                        {
                                            if (paymentCode.trim().length <= 0)
                                            {
                                                msg.alert("Chưa nhập mã hóa đơn");
                                            }
                                            else {

                                                if (billCode.trim().length <= 0)
                                                {
                                                    msg.alert("Chưa nhập ký hiệu hóa đơn");
                                                }
                                                else {
                                                    sd.connector.post("feeAction!onSavePaymentNew.do?paymentInfoId=" + paymentInfoId + "&paymentType=" + paymentType + "&billPath=" + billPath + "&paymentPerson=" + paymentPerson + "&" + token.getTokenParamString(), null, "createFeeForm1", null, page.afterSavePaymentNew);
                                                }

                                            }
                                        }

                                    }
                                    else
                                    {
                                        msg.alert("Chỉ được upload một file hóa đơn thu tiền hoặc hóa đơn chuyển khoản");
                                    }

                                }
                                else
                                {
                                    msg.alert("Phải có hóa đơn thu tiền hoặc hóa đơn chuyển khoản");

                                }
                            }
                        }
                    }
                }
            };



            page.afterSavePaymentNew = function()
            {
                page.search();
                page.cleardlg();
                dlgfeeManage.hide();
                msg.alert("Cập nhật thanh toán thành công !");
            };
            page.cleardlg = function()
            {
                dijit.byId("createFeeForm1.paymentTypeNew").setValue(-1);
                dijit.byId("createFeeForm1.paymentCost").setValue("");
                dijit.byId("createFeeForm1.paymentDate").setValue("");
                dijit.byId("createFeeForm1.paymentInfoId").setValue("");
                //dijit.byId("createFeeForm1.paymentTypeNew").setValue("");
                dijit.byId("createFeeForm1.paymentCode").setValue("");
                dijit.byId("createFeeForm1.paymentPerson").setValue("");
                dijit.byId("createFeeForm1.billCode").setValue("");
                clearAttFile("sendForm.attachFile");
            };
            page.close1 = function() {
                page.cleardlg();
                dlgfeeManage.hide();
            };
</script>