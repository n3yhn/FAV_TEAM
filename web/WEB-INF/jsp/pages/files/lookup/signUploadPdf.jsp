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
<form id="createFeeFormNew" name="createFeeFormNew">
    <table width="100%;" cellpadding="0px" cellspacing="5px" style="table-layout: fixed; word-break: break-all;">
        <tr>
            <td width="30%">
                <sx:Label  key="File đã ký" require="true"/>
            </td>
            <td width="70%" id="tdBill20">
                <sd:TextBox trim="true" cssStyle="width:100%;display:none;" id="documentReceiveAddEditForm.attachIdNew" name="documentReceiveAddEditForm.attachIdNew" key="" />
                <sx:upload_one extension="*.pdf"
                               auto="true" id="sendForm.attachFileNew"
                               action="uploadiframe!uploadFilePdf.do?id=sendForm.attachFileNew" maxSize="8">
                </sx:upload_one>
                <sd:TextBox trim="true" cssStyle="width:100%;display:none;" id="sendForm.attachmentIdNew" name="sendForm.attachmentIdNew" key=""/>
            </td>
        </tr>




        <tr style="text-align: center">
            <td colspan="4">
                <sd:Button id="Save10" key="" onclick="page.confirmUpload();" cssStyle="display" >
                    <img src="${contextPath}/share/images/icons/Answer.png" height="14" width="18">
                    <span style="font-size:12px">Xác nhận</span>
                </sd:Button>
                <sx:ButtonClose id="close10" onclick="page.close10()" />
            </td>
        </tr>
    </table>
</form>
<sd:TextBox key="" id="createFeeFormNew.fileId" name="createFeeFormNew.fileId" cssStyle="display:none" />
<%--<sd:TextBox key="" id="createFeeFormNew.paymentTypeNew" name="createFeeFormNew.paymentTypeNew" cssStyle="display:none" />--%>

<script>

    page.confirmUpload = function()
    {
        msg.confirm('File bản công bố tải lên phải đúng với mã hồ sơ , bạn có chắc chắn thực hiện hành động này ?', '<sd:Property>Cảnh báo</sd:Property>', page.saveUpload);
            };
            page.saveUpload = function()
            {
                var billPath = getListAttachId("sendForm.attachFileNew");
                if (billPath != null && billPath.trim().length > 0)
                {
                    if (billPath.toString().indexOf(';') < 1)
                    {
                        sd.connector.post("filesAction!onUploadFileSignPdf.do?fileId=" + workingFileId + "&billPath=" + billPath + "&" + token.getTokenParamString(), null, null, null, page.afterUploadSignPdf);
                    }
                    else
                    {
                        msg.alert("Chỉ được upload một file !");
                    }
                }
                else
                {
                    msg.alert("Phải chọn file upload !");

                }
            };
            page.afterUploadSignPdf = function()
            {
                page.close10();
                onApprove();

            };


            page.cleardlgNew = function()
            {
                clearAttFile("sendForm.attachFileNew");
            };

            page.close10 = function() {
                page.cleardlgNew();
                dijit.byId("upload").hide();
            };
</script>