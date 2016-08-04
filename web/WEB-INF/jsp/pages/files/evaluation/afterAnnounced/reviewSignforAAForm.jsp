<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<object id="plugin1" type="application/x-viettelcasigner" width="3" height="10">
</object>
<form id="reviewSignForAAForm" name="createForm">
    <table width="100%" class="viewTable" id="tblReviewSignForAAForm">
        <tr>
            <td width="70%" style="text-align: center;alignment-adjust: middle">
                <div id="divSignProcess" style="display: none;text-align: center;alignment-adjust: middle">
                    <table>
                        <tr>
                            <td>
                                <div id="trWait" style="color:red;font-weight: bold;margin-left: 10px;margin-top: 3px"></div>
                            </td>
                            <td>
                                <img src="/share/images/loading/loading2.gif" width="20px" height="20px" >
                                <sd:TextBox key="" id="reviewSignForAAForm.fileId" name="createForm.fileId" cssStyle="display:none"/>
                                <sd:TextBox key="" id="reviewSignForAAForm.status" name="createForm.status" value="6" cssStyle="display:none"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
    </table>
</form>
<jsp:include page="pluginJSRSFAA.jsp" flush="false"></jsp:include>
    <script type="text/javascript">
        var signType = "";
        
        showLdpSignMoreAAForm = function (fileId) {//b3
            dijit.byId("reviewSignForAAForm.fileId").setValue(fileId);
            signType = "PDHS";
            document.getElementById('trWait').innerHTML = "Hệ thống đang thực hiện phê duyệt:" + (signIndex + 1) + "/" + itemsToSign.length + " hồ sơ  ";
            document.getElementById("divSignProcess").style.display = "";
            alert("qua trinh tao giay cong bo");
            sd.connector.post("filesExplandAction!onCreatePaperForAA.do?" + token.getTokenParamString(), null, "reviewSignForAAForm", null, afterOnCreatePaperForAA);
        };

        afterOnCreatePaperForAA = function (data) {//b4
            alert("hoan thanh qua trinh tao giay cong bo");
            var obj = dojo.fromJson(data);
//            dijit.byId("reviewSignforAAFormDlg").show();
            onSendReviewSignForAA();
        };

        onCloseDlg = function () {
            count = 0;
            var txtBase64HashFRFNPCheck = dijit.byId('txtBase64HashFRFNP');
            if (txtBase64HashFRFNPCheck) {
                txtBase64HashFRFNPCheck.destroyRecursive(true);
            }
            var txtCertSerialFRFNPCheck = dijit.byId('txtCertSerialFRFNP');
            if (txtCertSerialFRFNPCheck) {
                txtCertSerialFRFNPCheck.destroyRecursive(true);
            }
            var reviewSignForAAFormFileId = dijit.byId('reviewSignForAAForm.fileId');
            if (reviewSignForAAFormFileId) {
                reviewSignForAAFormFileId.destroyRecursive(true);
            }
            var reviewSignForAAFormLSR = dijit.byId('reviewSignForAAForm.leaderStaffRequest');
            if (reviewSignForAAFormLSR) {
                reviewSignForAAFormLSR.destroyRecursive(true);
            }
            var btnSendLDCFRF1 = dijit.byId('btnSendLDCFRF1');
            if (btnSendLDCFRF1) {
                btnSendLDCFRF1.destroyRecursive(true);
            }
            var btnSendVTFRF1 = dijit.byId('btnSendVTFRF1');
            if (btnSendVTFRF1) {
                btnSendVTFRF1.destroyRecursive(true);
            }
            var btnExportRSFAAF1 = dijit.byId('btnExportRSFAAF1');
            if (btnExportRSFAAF1) {
                btnExportRSFAAF1.destroyRecursive(true);
            }

            doGoToMenu("filesAction!toAdditionReviewPage.do");
        };

        afterFeedbackReviewActionNew = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                if (signIndex == itemsToSign.length - 1)
                {
                    check = true;
                    document.getElementById("trWait").style.display = "none";
                    document.getElementById("divSignProcess").style.display = "none";
                    onCloseDlg();
                    page.search();
                } else
                {
                    check = false;
                    signIndex++;
                    page.signMoreFiles();
                }
            } else
            {
                check = true;
            }
        };

        onFeedbackReviewAction = function () {
            if (page.validateFeedbackReviewForm()) {
                sd.connector.post("filesAction!onFeedbackReview.do?" + token.getTokenParamString(), null, "reviewSignForAAForm", null, afterFeedbackReviewActionNew);
            }
        };

        page.validateFeedbackReviewForm = function () {
            document.getElementById("reviewSignForAAForm.sendVt").checked = false;

            var leaderApproveId = dijit.byId("reviewSignForAAForm.leaderApproveId").getValue();
            if (leaderApproveId == -1) {
                alert("Bạn chưa chọn lãnh đạo phê duyệt");
                dijit.byId("reviewSignForAAForm.leaderApproveId").focus();
                return false;
            } else {
                var leaderApproveName = dijit.byId("reviewSignForAAForm.leaderApproveId").attr("displayedValue");
                dijit.byId("reviewSignForAAForm.leaderApproveName").setValue(leaderApproveName);
            }
            return true;
        };

        var leaderStaffRequestNew;
        var count = 0;
        var cert;

        onSendReviewSignForAA = function () {//b5
            alert("start onSendReviewSignForAA");
            var fileId = dijit.byId("reviewSignForAAForm.fileId").getValue();
            sd.connector.post("exportWord!onExportPaperSignPlugin.do?fileId=" + fileId, null, null, null, afterOnExportPaperSignPlugin);
        };

        afterOnExportPaperSignPlugin = function (data) {//b6
            alert("start afterOnExportPaperSignPlugin");
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                document.getElementById("trWait").style.display = "";
                signType = "PDHS";
                var fileId = dijit.byId("reviewSignForAAForm.fileId").getValue();
                if (count == 0) {
                    var item = uploadCertOfFileRSFAA(fileId);
                    cert = encodeBase64(item.certChain);
                }
                var path = result[2];
                alert(path);
                sd.connector.post("filesExplandAction!actionSignCA.do?fileId=" + fileId + "&cert=" + cert + "&signType=" + signType + "&path=" + path, null, null, null, page.signPluginFRFNP);
                count++;
            } else {
                msg.alert("Có lỗi trong quá trình xuất công văn SĐBS", "Cảnh báo");
                document.getElementById("trWait").style.display = "none";
            }
            alert("end afterOnExportPaperSignPlugin");
        };

        page.signPluginFRFNP = function (data)//b7
        {
            alert("start signPluginFRFNP");
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                var txtBase64HashNew = result[2];
                var certSerialNew = result[3];
                var fileId = result[4];
                var outPutPath = result[5];
                var fileName = result[6];
                dijit.byId("txtBase64HashFRFNP").setValue(txtBase64HashNew);
                dijit.byId("txtCertSerialFRFNP").setValue(certSerialNew);
                var sign = signAndSubmitRSFAA();
                var signData = encodeBase64(sign);
                sd.connector.post("filesExplandAction!onSignPlugin.do?fileId=" + fileId + "&outPutPath=" + outPutPath + "&signData=" + signData + "&signType=" + signType + "&fileName=" + fileName, null, null, null, page.afterSignPluginFRFNP);
            } else {
                alert("Ký số không thành công ! " + result[1]);
            }
        };

        page.afterSignPluginFRFNP = function (data)//b8
        {
            alert("start b8 afterSignPluginFRFNP");
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                onApproveLdp();
            } else
            {
                alert("Ký số không thành công !");
            }
        };

        onApproveLdp = function () {
            alert("start onApproveLdp");
//            sd.connector.post("filesAction!onFeedbackReviewSendVt.do?" + token.getTokenParamString(), null, "reviewSignForAAForm", null, afterFeedbackReviewActionNew);
            sd.connector.post("filesExplandAction!onApproveByLDP4AA.do?" + token.getTokenParamString(), null, "reviewSignForAAForm", null, afterApprove);
        };

        afterApprove = function (data) {
            alert("start afterApprove ");
            alert(flagSignMore);
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (!flagSignMore) {
                alert(result[1]);
                if (result[0] == "1") {
                    document.getElementById("divSignProcess").style.display = "none";
                    document.getElementById("trWait").style.display = "none";
                    onCloseApproveDlg();
                    page.search();
                }
            } else {
                if (result[0] == "1") {
                    if (signIndex == itemsToSign.length - 1) {
                        msg.alert('Ký số thành công', 'Thông báo');
                        document.getElementById("divSignProcess").style.display = "none";
                        document.getElementById("trWait").style.display = "none";
                        onCloseApproveDlg();
                        page.search();
                    } else {
                        signIndex++;
                        page.ldpSignMoreAA();
                    }
                } else {
                    msg.alert('Ký số không thành công', 'Lỗi hệ thống');
                    document.getElementById("divSignProcess").style.display = "none";
                    document.getElementById("trWait").style.display = "none";
                    page.search();
                }
            }
        };

        onCloseApproveDlg = function () {
            alert("start onCloseApproveDlg");
            flagSignMore = false;
            count = 0;
            var txtBase64HashFRFNPCheck = dijit.byId('txtBase64HashFRFNP');
            if (txtBase64HashFRFNPCheck) {
                txtBase64HashFRFNPCheck.destroyRecursive(true);
            }
            var txtCertSerialFRFNPCheck = dijit.byId('txtCertSerialFRFNP');
            if (txtCertSerialFRFNPCheck) {
                txtCertSerialFRFNPCheck.destroyRecursive(true);
            }
            var reviewSignForAAFormFileId = dijit.byId('reviewSignForAAForm.fileId');
            if (reviewSignForAAFormFileId) {
                reviewSignForAAFormFileId.destroyRecursive(true);
            }
            var reviewSignForAAFormLSR = dijit.byId('reviewSignForAAForm.leaderStaffRequest');
            if (reviewSignForAAFormLSR) {
                reviewSignForAAFormLSR.destroyRecursive(true);
            }
            var btnSendLDCFRF1 = dijit.byId('btnSendLDCFRF1');
            if (btnSendLDCFRF1) {
                btnSendLDCFRF1.destroyRecursive(true);
            }
            var btnSendVTFRF1 = dijit.byId('btnSendVTFRF1');
            if (btnSendVTFRF1) {
                btnSendVTFRF1.destroyRecursive(true);
            }
            var btnExportRSFAAF1 = dijit.byId('btnExportRSFAAF1');
            if (btnExportRSFAAF1) {
                btnExportRSFAAF1.destroyRecursive(true);
            }
            doGoToMenu("filesAction!toReviewPage.do?IsChange=1");
//            doGoToMenu("filesAction!toAdditionReviewPage.do");
        };

        page.utf8_to_b64FBAF = function (str) {
            return window.btoa(unescape(encodeURIComponent(str)));
        };

        String.prototype.replaceAllFBAF = function (strTarget, strSubString) {
            var strText = this;
            var intIndexOfMatch = strText.indexOf(strTarget);
            while (intIndexOfMatch != -1) {
                strText = strText.replace(strTarget, strSubString)

                intIndexOfMatch = strText.indexOf(strTarget);
            }
            return(strText);
        };

        onCloseFeedbackReviewFormNew = function () {
            dijit.byId("feedbackReviewFormDlgNewPlugin").hide();
            dijit.byId("reviewSignForAAForm.leaderStaffRequest").setValue("");
        };

        page.downloadRSFAAF = function () {//xuat file ket qua tham dinh
            var fileId = dijit.byId("reviewSignForAAForm.fileId").getValue();
            var content = page.utf8_to_b64RSFAAF(dijit.byId("reviewSignForAAForm.leaderStaffRequest").getValue());
            content = content.replaceAllRSFAAF('+', '_');
            document.location = "exportWord!onXuatTBSDBS.do?fileId=" + fileId + "&content=" + content;
        };

        page.utf8_to_b64RSFAAF = function (str) {
            return window.btoa(unescape(encodeURIComponent(str)));
        };

        String.prototype.replaceAllRSFAAF = function (strTarget, strSubString) {
            var strText = this;
            var intIndexOfMatch = strText.indexOf(strTarget);
            while (intIndexOfMatch != -1) {
                strText = strText.replace(strTarget, strSubString)

                intIndexOfMatch = strText.indexOf(strTarget);
            }
            return(strText);
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
<sd:TextBox id="txtBase64HashFRFNP" key="" name="txtBase64Hash" type="hidden"/>
<input type="hidden" id="certSerial" value="" />
<sd:TextBox id="txtCertSerialFRFNP" key="" name="txtCertSerial" type="hidden"/>