<%-- 
    Document   : guithongbaosdbs
    Created on : 
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<form id="feedbackGiveBackForm" name="createForm">
    <table width="100%" class="viewTable" id="tblFeedbackEvaluateForm">
        <tr hidden="true">
            <td width="30%" style="text-align: right"><sd:Label key="Dự thảo thông báo yêu cầu sửa đổi bổ sung hồ sơ"/></td>
            <td width="70%">
                <sd:TextBox key="" id="feedbackGiveBackForm.fileId" name="createForm.fileId" cssStyle="display:none"/>
                <input type="radio" id="feedbackGiveBackForm.statusDeny" name="createForm.status" value="20" checked="true"/>
                <sd:Label key=""/>
            </td>
        </tr>
        <tr hidden="true">
            <td width="30%" style="text-align: right"><sd:Label key="Nội dung thông báo"/></td>
            <td  width="70%" >
                <div id="feedbackGiveBackForm.leaderStaffRequest"></div>
            </td>
        </tr>
        <tr hidden="true">
            <td style="text-align: right"><sd:Label key="Yêu cầu chuyển loại hồ sơ"/></td>
            <td>
                <sd:CheckBox key="" id="ckbIsTypeChangeVT" name="ckbIsTypeChangeVT" value=""/>
                <sd:TextBox key="" id="feedbackGiveBackForm.isTypeChange" name="createForm.isTypeChange" cssStyle="display:none" />
            </td>
        </tr>
        <tr id="trWait" style="display: none">
            <td colspan="2" style="text-align: center;alignment-adjust: middle">
                <label id="labelWait" style="color: red">Vui lòng chờ  </label>
                <img src="/share/images/loading/loading2.gif" width="20px" height="20px">
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="onFeedbackGiveBackAction();"/>
                <sd:Button id="btnExportFBGBF" key="" onclick="page.downloadPDFFBGBF();" cssStyle="display:" cssClass="buttonGroup">
                    <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Xem truoc"/>
                    <span style="font-size:12px">Xuất công văn SĐBS</span>
                </sd:Button>
                <sx:ButtonClose onclick="onCloseFeedbackGiveBackForm();"/>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    afterFeedbackGiveBackAction = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            document.getElementById("trWait").style.display = "none";
            onCloseFeedbackGiveBackForm();
            page.search();
        }
    };

    onFeedbackGiveBackAction = function() {
        //page.clearContent();
        document.getElementById("labelWait").innerHTML = "Vui lòng chờ  ";
        signType = "CVBS";
        sd.connector.post("uploadiframe!openFileUserAttachPdfSign.do?fileId=" + signFileId + "&signType=" + signType, null, null, null, signAfterC);
    };

    onCloseFeedbackGiveBackForm = function() {
        document.getElementById("trWait").style.display = "none";
        dijit.byId("feedbackGiveBackFormDlg").hide();
        // page.clearContent();
    };
    page.downloadPDFFBGBF = function() {
        document.location = "uploadiframe!openFileUserAttachPdfCVBS.do?fileIdf=" + signFileId;
    };
//    page.downloadFBGBF = function () {//xuat file ket qua tham dinh
//        var fileId = dijit.byId("feedbackReviewForm.fileId").getValue();
//        var content = page.utf8_to_b64FBGBF(dijit.byId("feedbackReviewForm.leaderStaffRequest").getValue());
//        content = content.replaceAllFBGBF('+', '_');
//        document.location = "exportWord!onXuatTBSDBS.do?fileId=" + fileId + "&content=" + content;
//    };
    page.utf8_to_b64FBGBF = function(str) {
        return window.btoa(unescape(encodeURIComponent(str)));
    };
    String.prototype.replaceAllFBGBF = function(strTarget, strSubString) {
        var strText = this;
        var intIndexOfMatch = strText.indexOf(strTarget);
        while (intIndexOfMatch != -1) {
            strText = strText.replace(strTarget, strSubString)

            intIndexOfMatch = strText.indexOf(strTarget);
        }
        return(strText);
    };
</script>