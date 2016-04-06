<%-- 
    Document   : comparistionForm
    Created on : 250214
    Author     : binhnt53 chức năng đối chiếu hồ sơ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>


<form id="createFormCompare" name="createFormCompare">
    <table width="100%" class="viewTable" id="tblComparisonDlg">
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Nội dung kết quả"/></td>
            <td width="70%">
                <div id="comparisonForm.lastContent"></div>
            </td>
        </tr>
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Kết quả đối chiếu"/></td>
            <td width="70%">
                <sd:TextBox key="" id="comparisonForm.fileId" name="createFormCompare.fileId" cssStyle="display:none"/>
                <input type="radio" id="comparisonForm.statusAccept" name="createFormCompare.isComparison" value="1" onchange="page.radioChecked()"/>
                <sd:Label key="Nội dung hợp lệ"/>
                </br>
                <input type="radio" id="comparisonForm.statusDeny" name="createFormCompare.isComparison" value="0" onchange="page.radioChecked()"/>
                <sd:Label key="Nội dung không hợp lệ"/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Nội dung chi tiết kết quả đối chiếu"/></td>
            <td>
                <sd:Textarea key="" id="comparisonForm.comparisonContent" name="createFormCompare.comparisonContent" rows="4" cssStyle="width:99%" maxlength="1800" trim="true"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="onComparison();"/>
                <sx:ButtonClose onclick="onCloseComparison();"/>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    afterOnComparison = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseComparison();
            page.search();
        }        
    };

    onComparison = function () {
        if (document.getElementById("comparisonForm.statusAccept").checked || document.getElementById("comparisonForm.statusDeny").checked) {
            if (document.getElementById("comparisonForm.statusAccept").checked || (document.getElementById("comparisonForm.statusDeny").checked && document.getElementById("comparisonForm.comparisonContent").value != ""))
            {
                sd.connector.post("filesAction!onComparison.do?" + token.getTokenParamString(), null, "createFormCompare", null, afterOnComparison);
            }
            if (document.getElementById("comparisonForm.statusDeny").checked && document.getElementById("comparisonForm.comparisonContent").value == "") {
                alert("Phải lưu lại nội dung kết quả đối chiếu nếu không hợp lệ");
            }
        } else {
            alert("Bạn chưa chọn 'Hồ sơ hợp lệ' hay 'Hồ sơ không hợp lệ'");
        }
    };

    onCloseComparison = function () {
        document.getElementById("comparisonForm.statusAccept").checked = false;
        document.getElementById("comparisonForm.statusDeny").checked = false;
        document.getElementById("comparisonForm.comparisonContent").value = "";
        dijit.byId("comparisonDlg").hide();
    };
    page.rplBrTblComparisonDlg = function () {
        var content = "";
        content = document.getElementById("comparisonForm.lastContent").innerHTML;
        content = content.replace(/\n/g, "<br>");
        document.getElementById("comparisonForm.lastContent").innerHTML = content;
    };
    page.radioChecked = function () {
    };
</script>