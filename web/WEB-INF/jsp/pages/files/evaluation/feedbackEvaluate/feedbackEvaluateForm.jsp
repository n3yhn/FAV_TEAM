<%-- 
    Document   : thongbaosdbs
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<form id="feedbackEvaluateForm" name="createForm">
    <table width="100%" class="viewTable" id="tblFeedbackEvaluateForm">
        <tr>
            <td style="text-align: right" width="30%"><sd:Label key="Ý kiến Chuyên viên"/></td>
            <td width="70%">
                <div id="feedbackEvaluateForm.staffContent"></div>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Ý kiến lãnh đạo đơn vị xem xét"/></td>
            <td>
                <div id="feedbackEvaluateForm.leaderStaffContent"></div>
            </td>
        </tr>
        <tr hidden="true">
            <td width="30%" style="text-align: right"><sd:Label key="Yêu cầu bổ sung"/></td>
            <td width="70%">
                <sd:TextBox key="" id="feedbackEvaluateForm.fileId" name="createForm.fileId" cssStyle="display:none"/>
                <input type="radio" id="feedbackEvaluateForm.statusDeny" name="createForm.status" value="28" checked="true"/>
                <sd:Label key=""/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Nội dung yêu cầu bổ sung"/></td>
            <td>
                <sd:Textarea key="" id="feedbackEvaluateForm.staffRequest" name="createForm.staffRequest" rows="10" cssStyle="width:99%" maxlength="2000" trim="true"/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Yêu cầu chuyển loại hồ sơ"/></td>
            <td>
                <sd:CheckBox key="" id="ckbIsTypeChange" name="ckbIsTypeChange" value=""/>
                <sd:TextBox key="" id="feedbackEvaluateForm.isTypeChange" name="createForm.isTypeChange" cssStyle="display:none" />
            </td>
        </tr>
        <tr id="trLeaderFeedbackEvaluate">                    
            <td align="right">
                <sd:Label key="Chọn lãnh đạo xem xét nội dung thông báo"/>
            </td>
            <td>
                <sd:SelectBox cssStyle="width:100%"
                              id="feedbackEvaluateForm.leaderReviewId"
                              key="" data="lstLeaderOfStaff" valueField="userId" labelField="fullName"
                              name="createForm.leaderReviewId" >
                </sd:SelectBox>
                <sd:TextBox id="feedbackEvaluateForm.leaderReviewName" name="createForm.leaderReviewName" cssStyle="display:none" key=""/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="onFeedbackEvaluateAction();"/>
                <sd:Button id="btnExportFBEF" key="" onclick="page.downloadFBEF();" cssStyle="display:" cssClass="buttonGroup">
                    <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Xem truoc"/>
                    <span style="font-size:12px">Xuất dự thảo nội dung công văn SĐBS</span>
                </sd:Button>
                <sx:ButtonClose onclick="onCloseFeedbackEvaluateForm();"/>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    afterFeedbackEvaluateAction = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseFeedbackEvaluateForm();
            //backPage();
            page.search();
        }

    };

    onFeedbackEvaluateAction = function () {
        if (page.validateFBEF()) {
            sd.connector.post("filesAction!onFeedbackEvaluate.do?" + token.getTokenParamString(), null, "feedbackEvaluateForm", null, afterFeedbackEvaluateAction);
        }
    };
    page.validateFBEF = function () {
        document.getElementById("feedbackEvaluateForm.statusDeny").checked = true;
        if (document.getElementById("ckbIsTypeChange").checked == false) {
            dijit.byId("feedbackEvaluateForm.isTypeChange").setValue(0);
        } else {
            dijit.byId("feedbackEvaluateForm.isTypeChange").setValue(1);
        }
        if (!dijit.byId("feedbackEvaluateForm.staffRequest").getValue()) {
            alert("Bạn chưa nhập [Nội dung]");
            document.getElementById("feedbackEvaluateForm.staffRequest").focus();
            return false;
        }
        var leaderApproveId = dijit.byId("feedbackEvaluateForm.leaderReviewId").getValue();
        if (leaderApproveId == -1) {
            alert("Bạn chưa chọn lãnh đạo xem xét");
            dijit.byId("feedbackEvaluateForm.leaderReviewId").focus();
            return false;
        } else {
            var leaderReviewName = dijit.byId("feedbackEvaluateForm.leaderReviewId").attr("displayedValue");
            dijit.byId("feedbackEvaluateForm.leaderReviewName").setValue(leaderReviewName);
        }
        return true;
    };

    onCloseFeedbackEvaluateForm = function () {
        dijit.byId("feedbackEvaluateFormDlg").hide();
        dijit.byId("feedbackEvaluateForm.staffRequest").setValue("");
    };
    page.rplBrTblFeedbackEvaluateForm = function () {
        var content = "";
        content = document.getElementById("feedbackEvaluateForm.leaderStaffContent").innerHTML;
        content = content.replace(/\n/g, "<br>");
        document.getElementById("feedbackEvaluateForm.leaderStaffContent").innerHTML = content;

        content = document.getElementById("feedbackEvaluateForm.staffContent").innerHTML;
        content = content.replace(/\n/g, "<br>");
        document.getElementById("feedbackEvaluateForm.staffContent").innerHTML = content;
    };
    page.downloadFBEF = function () {//xuat file ket qua tham dinh
        var fileId = dijit.byId("feedbackEvaluateForm.fileId").getValue();
        var content = page.utf8_to_b64FBEF(dijit.byId("feedbackEvaluateForm.staffRequest").getValue());
        content = content.replaceAllFBEF('+', '_');
        document.location = "exportWord!onXuatTBSDBS.do?fileId=" + fileId + "&content=" + content;
    };
    page.utf8_to_b64FBEF = function (str) {
        return window.btoa(unescape(encodeURIComponent(str)));
    };
    String.prototype.replaceAllFBEF = function (strTarget, strSubString) {
        var strText = this;
        var intIndexOfMatch = strText.indexOf(strTarget);
        while (intIndexOfMatch != -1) {
            strText = strText.replace(strTarget, strSubString)

            intIndexOfMatch = strText.indexOf(strTarget);
        }
        return(strText);
    };
</script>