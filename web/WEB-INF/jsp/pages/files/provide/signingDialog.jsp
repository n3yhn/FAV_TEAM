<%-- 
    Document   : 
    Created on : 
    Author     : vtit_binhnt53
    Description: màn hình danh sách giấy tiếp nhận trình kí
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<form id="signingForm" name="signingForm">
    <sx:ResultMessage id="resultMessage" />
    <table width="100%" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="35%"></td>
            <td width="65%"></td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Ý kiến trình ký:" required="true"/></td>
            <td>
                <sd:Textarea key="" id="signingForm.signingContent" name="signingForm.signingContent" rows="4" cssStyle="width:99%" trim="true"/>
                <sd:TextBox id="signForm.status" name="signForm.status" cssStyle="display:none" key=""/>
            </td>
        </tr>
        <tr>

            <td style="text-align: right"><sd:Label key="Ngày trình ký:" required="true"/></td>
            <td>
                <sx:DatePicker cssStyle="width:99%"
                               id="provideForm.signingDate"
                               key=""
                               name="provideForm.signingDate" format="dd/MM/yyyy"/>
                <sd:TextBox 
                    id="signingForm.fileId"
                    name="signingForm.fileId" 
                    cssStyle="display:none" 
                    key=""
                    trim="true"/>
            </td>
        </tr>
        
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="onSendToSign();"/>
                <sx:ButtonBack onclick="backPage();"/>  
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    page.afterSigning = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {
            page.onCloseReview();
            backPage();
        }
    };

    onSendToSign = function() {
        sd.connector.post("filesAction!onSendToSign.do?" + token.getTokenParamString(), null, "signingForm", null, page.afterSigning);
    };
    page.onCloseReview = function() {
        dijit.byId("signingDlg").hide();
        dijit.byId("provideForm.signingDate").setValue("");
        dijit.byId("signingForm.signingContent").setValue("");
    };
</script>