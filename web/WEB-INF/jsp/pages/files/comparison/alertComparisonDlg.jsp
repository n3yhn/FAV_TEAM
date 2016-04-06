<%-- 
    Document   : evaluateForm thong bao doi chieu ho so
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_binhnt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<form id="alertComparisonForm" name="alertComparisonForm">
    <table width="100%" class="viewTable" id="tblAlertComparisonForm">
        <tr>
            <td style="text-align: right" width="20%"><sd:Label key="Ý kiến yêu cầu thông báo"/></td>
            <td width="80%">
                <div id="alertComparisonForm.requestCommentForm.lastContent"></div>
            </td>
        </tr>
        <tr hidden="true">
            <td width="20%" style="text-align: right"><sd:Label key="Thông báo đối chiếu hồ sơ"/></td>
            <td width="80%">
                <sd:TextBox key="" id="alertComparisonForm.fileId" name="createForm.fileId" cssStyle="display:none"/>
                <input type="radio" id="alertComparisonForm.status" name="createForm.status" value="23" checked="true"/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Nội dung yêu cầu thông báo"/></td>
            <td>
                <sd:Textarea key="" id="alertComparisonForm.content" name="createForm.comparisonContent" rows="10" cssStyle="width:99%" maxlength="1800" trim="true"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="onAlertComparison();"/>
                <sx:ButtonClose onclick="onCloseAlertComparisonDlg();"/>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    afterAlertComparison = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseAlertComparisonDlg();
            page.search();
        }
    };

    onAlertComparison = function() {
        if (page.validate()) {
            sd.connector.post("filesAction!onAlertComparison.do?" + token.getTokenParamString(), null, "alertComparisonForm", null, afterAlertComparison);
        }
    };
    page.validate = function() {
        var content = dijit.byId("alertComparisonForm.content").getValue();
        if (content == null) {
            alert("Bạn chưa nhập [Nội dung]");
            return false;
        }
        return true;
    };

    onCloseAlertComparisonDlg = function() {
        page.search();
        dijit.byId("alertComparisonDlg").hide();
        dijit.byId("alertComparisonForm.content").setValue("");
    };
    page.rplBrTblAlertComparisonForm = function() {
        var content = "";
        content = document.getElementById("alertComparisonForm.requestCommentForm.lastContent").innerHTML;
        content = content.replace(/\n/g, "<br>");
        document.getElementById("alertComparisonForm.requestCommentForm.lastContent").innerHTML = content;
    };
</script>