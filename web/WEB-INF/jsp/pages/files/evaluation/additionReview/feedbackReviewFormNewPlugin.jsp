<%-- 
    Document   : xemxetthongbaosdbs
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<object id="plugin0" type="application/x-viettelcasigner" width="3" height="10">
</object>
<form id="feedbackReviewFormNew" name="createForm">
    <table width="100%" class="viewTable" id="tblFeedbackEvaluateForm">
        <tr hidden="true">
            <td width="30%" style="text-align: right"><sd:Label key="Dự thảo thông báo yêu cầu sửa đổi bổ sung hồ sơ"/></td>
            <td width="70%">
                <sd:TextBox key="" id="feedbackReviewFormNew.fileId" name="createForm.fileId" cssStyle="display:none"/>
                <input type="radio" id="feedbackReviewFormNew.statusDeny" name="createForm.status" value="26" checked="true"/>
                </br>
                <input type="radio" id="feedbackReviewFormNew.sendVt" name="createForm.status" value="27"/>
                <sd:Label key=""/>
            </td>
        </tr>
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Nội dung thông báo"/></td>
            <td  width="70%" >
                <sd:Textarea key="" id="feedbackReviewFormNew.leaderStaffRequest" name="createForm.leaderStaffRequest" rows="15" cssStyle="width:99%" maxlength="2000" trim="true"/>
            </td>
        </tr>
        <%--        <tr>
                    <td style="text-align: right"><sd:Label key="Yêu cầu chuyển loại hồ sơ"/></td>
                    <td>
                        <sd:CheckBox key="" id="ckbIsTypeChangeL" name="ckbIsTypeChangeL" value=""/>
                        <sd:TextBox key="" id="feedbackReviewFormNew.isTypeChange" name="createForm.isTypeChange" cssStyle="display:none" />
                    </td>
                </tr>
                <tr id="trLeaderFeedbackReview">                                
                    <td align="right">
                        <sd:Label key="Chọn lãnh đạo phê duyệt nội dung thông báo"/>
                    </td>
                    <td>
                        <sd:SelectBox  cssStyle="width:98%"
                                       id="feedbackReviewFormNew.leaderApproveId"
                                       key="" data="lstLeader" valueField="userId" labelField="fullName"
                                       name="createForm.leaderApproveId" >
                        </sd:SelectBox>
                        <sd:TextBox id="feedbackReviewFormNew.leaderApproveName" name="createForm.leaderApproveName" cssStyle="display:none" key=""/>
                    </td>
                </tr>--%>
        <!--        <tr id="trWait" style="display: none">
                    <td colspan="2" style="text-align: center;alignment-adjust: middle">
                        <label id="labelWait" style="color: red">Vui lòng chờ  </label>
                        <img src="/share/images/loading/loading2.gif" width="20px" height="20px">
                    </td>
                </tr>-->
        <tr>
            <td colspan="2" style="text-align: center;alignment-adjust: middle">
                <div id="divSignProcess" style="display: none;text-align: center;alignment-adjust: middle">
                    <table>
                        <tr>
                            <td>
                                <div id="trWait" style="color:red;font-weight: bold;margin-left: 10px;margin-top: 3px"></div>
                            </td>
                            <td>
                                <img src="/share/images/loading/loading2.gif" width="20px" height="20px" >
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <sd:Button id="btnSendLDCFRF1" key="" onclick="onFeedbackReviewAction();" cssStyle="display:none" cssClass="buttonGroup">
                    <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Trình lên lãnh đạo cục xem xét"/>
                    <span style="font-size:12px">Trình lên LĐC phê duyệt</span>
                </sd:Button>
                <sd:Button id="btnSendVTFRF1" key="" onclick="onSendFRFtoVT();" cssStyle="display:" cssClass="buttonGroup">
                    <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Ký duyệt công văn đến Văn thư"/>
                    <span style="font-size:12px">Ký duyệt công văn chuyển đến VT</span>
                </sd:Button>
                <sd:Button id="btnExportFBRF1" key="" onclick="page.downloadFBRF();" cssStyle="display:none" cssClass="buttonGroup">
                    <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Xem truoc"/>
                    <span style="font-size:12px">Xem trước công văn SĐBS</span>
                </sd:Button>
                <sx:ButtonClose onclick="onCloseFeedbackReviewFormNew();"/>
            </td>
        </tr>
    </table>
</form>
<jsp:include page="pluginJSFRFNP.jsp" flush="false"></jsp:include>
    <script type="text/javascript">
        onCloseDlg = function() {
            count = 0;
            var txtBase64HashFRFNPCheck = dijit.byId('txtBase64HashFRFNP');
            if (txtBase64HashFRFNPCheck) {
                txtBase64HashFRFNPCheck.destroyRecursive(true);
            }
            var txtCertSerialFRFNPCheck = dijit.byId('txtCertSerialFRFNP');
            if (txtCertSerialFRFNPCheck) {
                txtCertSerialFRFNPCheck.destroyRecursive(true);
            }
            var feedbackReviewFormNewFileId = dijit.byId('feedbackReviewFormNew.fileId');
            if (feedbackReviewFormNewFileId) {
                feedbackReviewFormNewFileId.destroyRecursive(true);
            }
            var feedbackReviewFormNewLSR = dijit.byId('feedbackReviewFormNew.leaderStaffRequest');
            if (feedbackReviewFormNewLSR) {
                feedbackReviewFormNewLSR.destroyRecursive(true);
            }
            var btnSendLDCFRF1 = dijit.byId('btnSendLDCFRF1');
            if (btnSendLDCFRF1) {
                btnSendLDCFRF1.destroyRecursive(true);
            }
            var btnSendVTFRF1 = dijit.byId('btnSendVTFRF1');
            if (btnSendVTFRF1) {
                btnSendVTFRF1.destroyRecursive(true);
            }
            var btnExportFBRF1 = dijit.byId('btnExportFBRF1');
            if (btnExportFBRF1) {
                btnExportFBRF1.destroyRecursive(true);
            }

            doGoToMenu("filesAction!toAdditionReviewPage.do");
        };
        afterFeedbackReviewActionNew = function(data) {
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
                }
                else
                {
                    check = false;
                    //dijit.byId("feedbackReviewFormDlgNewPlugin").hide();
                    signIndex++;
                    page.signMoreFiles();
                }
            }
            else
            {
                check = true;
            }
        };

        onFeedbackReviewAction = function() {
            if (page.validateFeedbackReviewForm()) {
                sd.connector.post("filesAction!onFeedbackReview.do?" + token.getTokenParamString(), null, "feedbackReviewFormNew", null, afterFeedbackReviewActionNew);
            }
        };
        page.validateFeedbackReviewForm = function() {
            document.getElementById("feedbackReviewFormNew.sendVt").checked = false;
            document.getElementById("feedbackReviewFormNew.statusDeny").checked = true;
            if (!dijit.byId("feedbackReviewFormNew.leaderStaffRequest").getValue()) {
                alert("Bạn chưa nhập [Nội dung]");
                document.getElementById("feedbackReviewFormNew.leaderStaffRequest").focus();
                return false;
            }
            var leaderApproveId = dijit.byId("feedbackReviewFormNew.leaderApproveId").getValue();
            if (leaderApproveId == -1) {
                alert("Bạn chưa chọn lãnh đạo phê duyệt");
                dijit.byId("feedbackReviewFormNew.leaderApproveId").focus();
                return false;
            } else {
                var leaderApproveName = dijit.byId("feedbackReviewFormNew.leaderApproveId").attr("displayedValue");
                dijit.byId("feedbackReviewFormNew.leaderApproveName").setValue(leaderApproveName);
            }
            return true;
        };

        var leaderStaffRequestNew;
        var count = 0;
        var cert;
        onSendFRFtoVT = function() {
            if (page.validateOnSendFRFtoVTPlugin()) {
                var fileId = dijit.byId("feedbackReviewFormNew.fileId").getValue();
                document.getElementById('trWait').innerHTML = "Hệ thống đang thực hiện phê duyệt:" + (signIndex + 1) + "/" + itemsToSign.length + " hồ sơ  ";
                document.getElementById("divSignProcess").style.display = "";
                var leaderStaffRequest = dijit.byId("feedbackReviewFormNew.leaderStaffRequest").getValue();
                if (check == true)
                {
                    var content = page.utf8_to_b64FBAF(leaderStaffRequest);
                    content = content.replaceAllFBAF('+', '_');
                    sd.connector.post("filesAction!onExportCvSdbsSignPlugin.do?fileId=" + fileId + "&content=" + content, null, null, null, onFeedbackReviewActionSign);
                }
                else
                {
                    var content = page.utf8_to_b64FBAF(leaderStaffRequest);
                    content = content.replaceAllFBAF('+', '_');
                    sd.connector.post("filesAction!onExportCvSdbsSignPlugin.do?fileId=" + fileId + "&content=" + content, null, null, null, onFeedbackReviewActionSign);
                }

            }
        };
        var signType;
        onFeedbackReviewActionSign = function(data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                document.getElementById("trWait").style.display = "";
                signType = "CVBS";
                var fileId = dijit.byId("feedbackReviewFormNew.fileId").getValue();
                if (count == 0) {
                    var item = uploadCertOfFile(fileId);
                    cert = encodeBase64(item.certChain);
                }
                var path = result[2];
                sd.connector.post("filesAction!actionSignCA.do?fileId=" + fileId + "&cert=" + cert + "&signType=" + signType + "&path=" + path, null, null, null, page.signPluginFRFNP);
                count++;
            } else {
                msg.alert("Có lỗi trong quá trình xuất công văn SĐBS", "Cảnh báo");
                document.getElementById("trWait").style.display = "none";
            }
        };

        page.signPluginFRFNP = function(data)
        {
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
                var sign = signAndSubmit();
                var signData = encodeBase64(sign);
                sd.connector.post("filesAction!onSignPlugin.do?fileId=" + fileId + "&outPutPath=" + outPutPath + "&signData=" + signData + "&signType=" + signType + "&fileName=" + fileName, null, null, null, page.afterSignPluginFRFNP);
            } else
            {
                 alert("Ký số không thành công ! " + result[1]);
            }
        }

        page.afterSignPluginFRFNP = function(data)
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
            sd.connector.post("filesAction!onFeedbackReviewSendVt.do?" + token.getTokenParamString(), null, "feedbackReviewFormNew", null, afterFeedbackReviewActionNew);
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

        page.validateOnSendFRFtoVTPlugin = function() {
            document.getElementById("feedbackReviewFormNew.sendVt").checked = true;
            document.getElementById("feedbackReviewFormNew.statusDeny").checked = false;
            var leaderStaffRequest = dijit.byId("feedbackReviewFormNew.leaderStaffRequest").getValue();
            if (leaderStaffRequest.trim().length == 0) {
                alert("Bạn chưa nhập [Nội dung]");
                return false;
            }
            return true;
        };
        onCloseFeedbackReviewFormNew = function() {
            dijit.byId("feedbackReviewFormDlgNewPlugin").hide();
            dijit.byId("feedbackReviewFormNew.leaderStaffRequest").setValue("");
        };

        page.downloadFBRF = function() {//xuat file ket qua tham dinh
            var fileId = dijit.byId("feedbackReviewFormNew.fileId").getValue();
            var content = page.utf8_to_b64FBRF(dijit.byId("feedbackReviewFormNew.leaderStaffRequest").getValue());
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