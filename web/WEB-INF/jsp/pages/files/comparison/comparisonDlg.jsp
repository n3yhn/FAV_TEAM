<%-- 
    Document   : comparistionForm
    Created on : 250214
    Author     : binhnt53 chức năng đối chiếu hồ sơ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>


<div>
    <table style="width: 98%">
        <tr id="trWaitViewFile">
            <td colspan="3" style="text-align: center;alignment-adjust: middle">
                <label id="labelWaitLoadFile" style="color: red">Vui lòng chờ  </label>
                <img src="/share/images/loading/loading2.gif" width="20px" height="20px">
            </td>
        </tr>
        <tr>
            <td style="width: 60%">
                <sd:TitlePane key="Thông tin hồ sơ" id="titlePaneViewFile">
                    <div id="divViewFile" style="overflow-y: auto;max-height: 600px"></div>
                </sd:TitlePane>
            </td>
            <td style="width: 38%">
                <sd:TitlePane key="Thông tin đối chiếu" id="titlePaneViewCompareFile">
                    <div style="overflow-y: auto;max-height: 600px">
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
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </sd:TitlePane>
            </td>
        </tr>
        <tr>
            <td colspan="3" style="text-align: center">
                <br/>
            </td>
        </tr>
        <tr>
            <td colspan="3" style="text-align: center">
                <sx:ButtonClose onclick="onCloseApprove();"/>
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript">
    onCloseApprove = function() {
        doGoToMenu("filesAction!toComparison.do");
    }
    
    afterReview = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseApprove();
        }
    };

    onComparison = function() {
        if (document.getElementById("comparisonForm.statusAccept").checked || document.getElementById("comparisonForm.statusDeny").checked) {
            if (document.getElementById("comparisonForm.statusAccept").checked || (document.getElementById("comparisonForm.statusDeny").checked && document.getElementById("comparisonForm.comparisonContent").value != ""))
            {
                sd.connector.post("filesAction!onComparison.do?" + token.getTokenParamString(), null, "createFormCompare", null, afterReview);
            }
            if (document.getElementById("comparisonForm.statusDeny").checked && document.getElementById("comparisonForm.comparisonContent").value == "") {
                alert("Phải lưu lại nội dung kết quả đối chiếu nếu không hợp lệ");
            }
        } else {
            alert("Bạn chưa chọn 'Hồ sơ hợp lệ' hay 'Hồ sơ không hợp lệ'");
        }
    };

    onCloseComparison = function() {
        document.getElementById("comparisonForm.statusAccept").checked = false;
        document.getElementById("comparisonForm.statusDeny").checked = false;
        document.getElementById("comparisonForm.comparisonContent").value = "";
        dijit.byId("comparisonDlg").hide();
    };
    page.rplBrTblComparisonDlg = function() {
        var content = "";
        content = document.getElementById("comparisonForm.lastContent").innerHTML;
        content = content.replace(/\n/g, "<br>");
        document.getElementById("comparisonForm.lastContent").innerHTML = content;
    };
    page.radioChecked = function() {
        /*
        if (document.getElementById("comparisonForm.statusAccept").checked) {
            document.getElementById("comparisonForm.comparisonContent").value = "";
        } else {
            document.getElementById("comparisonForm.comparisonContent").value = "";
        }
        */
    };
</script>