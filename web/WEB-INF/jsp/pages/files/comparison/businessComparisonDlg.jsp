<%-- 
    Document   : comparistionForm
    Created on : 250214
    Author     : binhnt53 chức năng đối chiếu hồ sơ - doanh nghiep
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<form id="createForm" name="createForm">
    <table width="100%" class="viewTable" id="tblBusinessComparisonDlg">
        <tr>
            <td style="text-align: right"width="20%"><sd:Label key="Nội dung kết quả"/>
                <sd:TextBox key="" id="comparisonForm.fileId" name="createForm.fileId" cssStyle="display:none"/>
            </td>
            <td width="80%">
                <div id="comparisonForm.lastContent"></div>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Gửi chuyển phát bản công bố?"/></td>
            <td>
                <sd:CheckBox key="" id="ckbIsCourier" name="ckbIsCourier" value="" onchange="onCheckedIsCourier()"/>
                <sd:TextBox key="" id="comparisonForm.isCourier" name="createForm.isCourier" cssStyle="display:none" />
            </td>
        </tr>

        <tr>
            <td style="text-align: right"width="20%"><sd:Label key="Địa chỉ nhận"/>
            </td>
            <td width="80%">
                <div id='recipientAddress'  style="display: none;" >  <sd:TextBox key="" id="comparisonForm.recipientAddress" name="createForm.recipientAddress"/></div>
            </td>
        </tr>

        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="onSaveBusComparison();"/>
                <sx:ButtonClose onclick="onCloseLeaderComparison();"/>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    onCloseLeaderComparison = function () {
        dijit.byId("comparisonByBusinessDlg").hide();
    };
    page.rplBrTblBusinessComparisonDlg = function () {
        var content = "";
        content = document.getElementById("comparisonForm.lastContent").innerHTML;
        content = content.replace(/\n/g, "<br>");
        document.getElementById("comparisonForm.lastContent").innerHTML = content;
    };
    onCheckedIsCourier = function () {
        var div = document.getElementById('recipientAddress');
        if (document.getElementById("ckbIsCourier").checked == false) {
            div.style.display = 'none';
            dijit.byId("feedbackApproveForm.isTypeChange").setValue(0);
        } else {
            div.style.display = '';
            dijit.byId("feedbackApproveForm.isTypeChange").setValue(1);
        }
    };
    onSaveBusComparison = function () {
        if (onValidateSaveButton()) {
            sd.connector.post("filesAction!onSetCourierAlertComparison.do?" + token.getTokenParamString(), null, "createForm", null, afterAlertComparison);
        }
    };
    onValidateSaveButton = function () {
        if (!document.getElementById("ckbIsCourier").checked) {
            dijit.byId("comparisonForm.isCourier").setValue(0);
            return true;
        } else {
            dijit.byId("comparisonForm.isCourier").setValue(1);
            var txtrecipientAddress = dijit.byId("comparisonForm.recipientAddress").getValue();
            if (txtrecipientAddress.trim().length == 0) {
                alert("Bạn chưa nhập [Địa chỉ]");
                dijit.byId("comparisonForm.recipientAddress").focus();
                return false;
            }
            return true;
        }
    };
    afterAlertComparison = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseLeaderComparison();
            page.search();
        }
    };
</script>