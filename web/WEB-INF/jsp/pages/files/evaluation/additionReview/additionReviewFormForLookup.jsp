<%-- 
    Document   : evaluateFormForLookup
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<form id="evaluateFormForLookup" name="createForm">
    <table width="100%" class="viewTable">
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Kết quả thẩm định"/></td>
            <td width="70%">
                <div id="evaluateFormForLookup.status"></div>
            </td>

        </tr>
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Nội dung thẩm định"/></td>
            <td width="70%">
                <div id="evaluateFormForLookup.staffRequest"></div>
            </td>
        </tr>
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Kết quả xem xét SĐBS"/></td>
            <td width="70%">
                <sd:TextBox key="" id="evaluateFormForLookup.fileId" name="createForm.fileId" cssStyle="display:none"/>
                <input type="radio" id="evaluateFormForLookup.statusAccept" name="createForm.status" value="5"/>
                <sd:Label key="Duyệt: Hồ sơ đạt"/>
                </br>
                <input type="radio" id="evaluateFormForLookup.statusDeny" name="createForm.status" value="19"/>
                <sd:Label key="Duyệt: Yêu cầu bổ sung HS"/>
                </br>
                <input type="radio" id="evaluateForm.statusAcceptSend" name="createForm.status" value="20"  cssStyle="display:none" disabled="true" hidden="true"/>
                <sd:Label key="Thông báo SĐBS tới DN" cssStyle="display:none"/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Nội dung yêu cầu bổ sung"/></td>
            <td>
                <sd:Textarea key="" id="evaluateFormForLookup.leaderStaffRequest" name="createForm.leaderStaffRequest" rows="4" cssStyle="width:99%" maxlength="1800"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="onReview();"/>
                <sx:ButtonClose onclick="onCloseReview();"/>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    afterReview = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseReview();
            page.search();
        }
    };

    onReview = function () {
        var leaderStaffRequest = dijit.byId("evaluateFormForLookup.leaderStaffRequest").getValue();
        if (document.getElementById("evaluateFormForLookup.statusDeny").checked == true || document.getElementById("evaluateFormForLookup.statusAccept").checked == true || document.getElementById("evaluateForm.statusAcceptSend").checked == true)
        {
            if ((document.getElementById("evaluateFormForLookup.statusDeny").checked == true && leaderStaffRequest.trim().length == 0) || (document.getElementById("evaluateForm.statusAcceptSend").checked == true && leaderStaffRequest.trim().length == 0)) {
                alert("Bạn chưa nhập yêu cầu bổ sung");
            } else {
                sd.connector.post("filesAction!onReview.do?" + token.getTokenParamString(), null, "evaluateFormForLookup", null, afterReview);
                return 0;
            }
        } else {
            alert("Bạn chưa chọn Kết quả xem xét SĐBS");
        }

    };

    onCloseReview = function () {
        dijit.byId("reviewAdditionDlg").hide();
        dijit.byId("evaluateFormForLookup.leaderStaffRequest").setValue("");
        document.getElementById("evaluateFormForLookup.statusAccept").checked = false;
        document.getElementById("evaluateFormForLookup.statusDeny").checked = false;
    };

</script>