<%-- 
    Document   : xemxetthongbaosdbs
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<form id="feedbackReviewForm" name="createForm">
    <table width="100%" class="viewTable" id="tblFeedbackEvaluateForm">
        <tr hidden="true">
            <td width="30%" style="text-align: right"><sd:Label key="Dự thảo thông báo yêu cầu sửa đổi bổ sung hồ sơ"/></td>
            <td width="70%">
                <sd:TextBox key="" id="feedbackReviewForm.fileId" name="createForm.fileId" cssStyle="display:none"/>
                <input type="radio" id="feedbackReviewForm.statusDeny" name="createForm.status" value="26" checked="true"/>
                </br>
                <input type="radio" id="feedbackReviewForm.sendVt" name="createForm.status" value="27"/>
                <sd:Label key=""/>
            </td>
        </tr>
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Nội dung thông báo"/></td>
            <td  width="70%" >
                <sd:Textarea key="" id="feedbackReviewForm.leaderStaffRequest" name="createForm.leaderStaffRequest" rows="15" cssStyle="width:99%" maxlength="2000" trim="true"/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Yêu cầu chuyển loại hồ sơ"/></td>
            <td>
                <sd:CheckBox key="" id="ckbIsTypeChangeL" name="ckbIsTypeChangeL" value=""/>
                <sd:TextBox key="" id="feedbackReviewForm.isTypeChange" name="createForm.isTypeChange" cssStyle="display:none" />
            </td>
        </tr>
        <tr id="trLeaderFeedbackReview">                                
            <td align="right">
                <sd:Label key="Chọn lãnh đạo phê duyệt nội dung thông báo"/>
            </td>
            <td>
                <sd:SelectBox  cssStyle="width:98%"
                               id="feedbackReviewForm.leaderApproveId"
                               key="" data="lstLeader" valueField="userId" labelField="fullName"
                               name="createForm.leaderApproveId" >
                </sd:SelectBox>
                <sd:TextBox id="feedbackReviewForm.leaderApproveName" name="createForm.leaderApproveName" cssStyle="display:none" key=""/>
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
                <sd:Button id="btnSendLDCFRF" key="" onclick="onFeedbackReviewAction();" cssStyle="display:" cssClass="buttonGroup">
                    <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Trình lên lãnh đạo cục xem xét"/>
                    <span style="font-size:12px">Trình lên LĐC phê duyệt</span>
                </sd:Button>
                <sd:Button id="btnSendVTFRF" key="" onclick="onSendFRFtoVT();" cssStyle="display:" cssClass="buttonGroup">
                    <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Ký duyệt công văn đến Văn thư"/>
                    <span style="font-size:12px">Ký duyệt công văn chuyển đến VT</span>
                </sd:Button>
                <sd:Button id="btnExportFBRF" key="" onclick="page.downloadFBRF();" cssStyle="display:" cssClass="buttonGroup">
                    <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Xem truoc"/>
                    <span style="font-size:12px">Xem trước công văn SĐBS</span>
                </sd:Button>
                <%-- hieptq update 070515 --%>
                <sd:Button id="btnSaveDraft" key="" onclick="saveDraftComment();" cssStyle="display:" cssClass="buttonGroup">
                    <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Luu nhap"/>
                    <span style="font-size:12px">Lưu nội dung công văn SĐBS</span>
                </sd:Button>

                <sx:ButtonClose onclick="onCloseFeedbackReviewForm();"/>
            </td>
        </tr>
    </table>
</form>
<jsp:include page="pluginJSFRF.jsp" flush="false"></jsp:include>
    <script type="text/javascript">

        afterFeedbackReviewAction = function(data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            alert(result[1]);
            if (result[0] == "1") {
                document.getElementById("trWait").style.display = "none";
                onCloseFeedbackReviewForm();
                backPage();
                page.search();
            }
            else
            {
                alert("Ký số không thành công !");
            }
        };

        onFeedbackReviewAction = function() {
            if (page.validateFeedbackReviewForm()) {
                sd.connector.post("filesAction!onFeedbackReview.do?" + token.getTokenParamString(), null, "feedbackReviewForm", null, afterFeedbackReviewAction);
            }
        };
        page.validateFeedbackReviewForm = function() {
            document.getElementById("feedbackReviewForm.sendVt").checked = false;
            document.getElementById("feedbackReviewForm.statusDeny").checked = true;
            if (document.getElementById("ckbIsTypeChangeL").checked == true) {
                dijit.byId("feedbackReviewForm.isTypeChange").setValue(1);
            } else {
                dijit.byId("feedbackReviewForm.isTypeChange").setValue(0);
            }
            if (!dijit.byId("feedbackReviewForm.leaderStaffRequest").getValue()) {
                alert("Bạn chưa nhập [Nội dung]");
                document.getElementById("feedbackReviewForm.leaderStaffRequest").focus();
                return false;
            }
            var leaderApproveId = dijit.byId("feedbackReviewForm.leaderApproveId").getValue();
            if (leaderApproveId == -1) {
                alert("Bạn chưa chọn lãnh đạo phê duyệt");
                dijit.byId("feedbackReviewForm.leaderApproveId").focus();
                return false;
            } else {
                var leaderApproveName = dijit.byId("feedbackReviewForm.leaderApproveId").attr("displayedValue");
                dijit.byId("feedbackReviewForm.leaderApproveName").setValue(leaderApproveName);
            }
            return true;
        };
        onSendFRFtoVT = function() {
            if (page.validateOnSendFRFtoVT_FRF()) {
                var fileId = dijit.byId("feedbackReviewForm.fileId").getValue();
                document.getElementById("trWait").style.display = "";
                document.getElementById("labelWait").innerHTML = "Hệ thống đang tạo công văn và ký số, vui lòng chờ  ";
                var leaderStaffRequest = dijit.byId("feedbackReviewForm.leaderStaffRequest").getValue();
                var content = page.utf8_to_b64FBAF(leaderStaffRequest);
                content = content.replaceAllFBAF('+', '_');
                sd.connector.post("filesAction!onExportCvSdbsSignPlugin.do?fileId=" + fileId + "&content=" + content, null, null, null, onFeedbackReviewActionSign);
            }
        };
        var signType;
        var cert;
        var count = 0;
        onFeedbackReviewActionSign = function(data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                document.getElementById("trWait").style.display = "";
                signType = "CVBS";
                var fileId = dijit.byId("feedbackReviewForm.fileId").getValue();
                if (count == 0) {
                    var item = uploadCertOfFile(fileId);
                    cert = encodeBase64(item.certChain);
                }
                var path = result[2];
                sd.connector.post("filesAction!actionSignCA.do?fileId=" + fileId + "&cert=" + cert + "&signType=" + signType + "&path=" + path, null, null, null, page.signPluginFRP);
            } else {
                msg.alert("Có lỗi trong quá trình xuất công văn SĐBS", "Cảnh báo");
                document.getElementById("trWait").style.display = "none";
            }
        };

        page.signPluginFRP = function(data)
        {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                var txtBase64HashNew = result[2];
                var certSerialNew = result[3];
                var fileId = result[4];
                var outPutPath = result[5];
                var fileName = result[6];
                dijit.byId("txtBase64HashFRF").setValue(txtBase64HashNew);
                dijit.byId("txtCertSerialFRF").setValue(certSerialNew);
                var sign = signAndSubmit();
                var signData = encodeBase64(sign);
                sd.connector.post("filesAction!onSignPlugin.do?fileId=" + fileId + "&outPutPath=" + outPutPath + "&signData=" + signData + "&signType=" + signType + "&fileName=" + fileName, null, null, null, page.afterSignPluginFRP);
            } else
            {
                alert("Ký số không thành công ! " + result[1]);
            }
        }

        page.afterSignPluginFRP = function(data)
        {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                onApproveLdp();
            } else
            {
                alert("Ký số không thành công !");
            }
        }
        onApproveLdp = function() {
            sd.connector.post("filesAction!onFeedbackReviewSendVt.do?" + token.getTokenParamString(), null, "feedbackReviewForm", null, afterFeedbackReviewAction);
        };

        page.utf8_to_b64FBAF = function(str) {
            return window.btoa(unescape(encodeURIComponent(str)));
        };

        String.prototype.replaceAllFBAF = function(strTarget, strSubString) {
            var strText = this;
            var intIndexOfMatch = strText.indexOf(strTarget);
            while (intIndexOfMatch != -1) {
                strText = strText.replace(strTarget, strSubString)

                intIndexOfMatch = strText.indexOf(strTarget);
            }
            return(strText);
        };

        page.validateOnSendFRFtoVT_FRF = function() {
            document.getElementById("feedbackReviewForm.sendVt").checked = true;
            document.getElementById("feedbackReviewForm.statusDeny").checked = false;
            if (document.getElementById("ckbIsTypeChangeL").checked == true) {
                dijit.byId("feedbackReviewForm.isTypeChange").setValue(1);
            } else {
                dijit.byId("feedbackReviewForm.isTypeChange").setValue(0);
            }
            var leaderStaffRequest = dijit.byId("feedbackReviewForm.leaderStaffRequest").getValue();
            if (leaderStaffRequest.trim().length == 0) {
                alert("Bạn chưa nhập [Nội dung]");
                return false;
            }
            return true;
        };
        onCloseFeedbackReviewForm = function() {
            dijit.byId("feedbackReviewFormDlg").hide();
            dijit.byId("feedbackReviewForm.leaderStaffRequest").setValue("");
        };

        page.downloadFBRF = function() {//xuat file ket qua tham dinh
            var fileId = dijit.byId("feedbackReviewForm.fileId").getValue();
            var content = page.utf8_to_b64FBRF(dijit.byId("feedbackReviewForm.leaderStaffRequest").getValue());
            content = content.replaceAllFBRF('+', '_');
            document.location = "exportWord!onXuatTBSDBS.do?fileId=" + fileId + "&content=" + content;
        };
        page.utf8_to_b64FBRF = function(str) {
            return window.btoa(unescape(encodeURIComponent(str)));
        };
        String.prototype.replaceAllFBRF = function(strTarget, strSubString) {
            var strText = this;
            var intIndexOfMatch = strText.indexOf(strTarget);
            while (intIndexOfMatch != -1) {
                strText = strText.replace(strTarget, strSubString)

                intIndexOfMatch = strText.indexOf(strTarget);
            }
            return(strText);
        };
        //hieptq update 070515
        saveDraftComment = function() {
            sd.connector.post("filesAction!saveDraftLeaderComment.do?" + token.getTokenParamString(), null, "feedbackReviewForm", null, afterSaveDraft);
        };
        afterSaveDraft = function(data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            alert(result[1]);
        };
        function deleteAllCookies() {
            var cookies = document.cookie.split(";");
            for (var i = 0; i < cookies.length; i++) {
                var cookie = cookies[i];
                var eqPos = cookie.indexOf("=");
                var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
                document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
            }
        }
        deleteAllCookies();
    </script>
    <input type="hidden" id="base64Hash" value="" />
<sd:TextBox id="txtBase64HashFRF" key="" name="txtBase64Hash" type="hidden"/>
<input type="hidden" id="certSerial" value="" />
<sd:TextBox id="txtCertSerialFRF" key="" name="txtCertSerial" type="hidden"/>
<object id="plugin0" type="application/x-viettelcasigner" width="3" height="10">
</object>