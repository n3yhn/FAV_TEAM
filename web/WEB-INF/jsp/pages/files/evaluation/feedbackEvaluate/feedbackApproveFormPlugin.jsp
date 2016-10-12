<%-- 
    Document   : pheduyetthongbaosdbs
    Created on : 
    Author     : 
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
                <sd:TitlePane key="Thông tin hồ sơ" id="titlePaneViewFile_FAFP">
                    <div id="divViewFile" style="overflow-y: auto;max-height: 600px"></div>
                </sd:TitlePane>
            </td>
            <td style="width: 38%">
                <sd:TitlePane key="Kết luận thẩm định" id="titlePaneEvaluate_FAFP">
                    <div style="overflow-y: auto;max-height: 600px">
                        <form id="feedbackApproveForm" name="createForm">
                            <table width="100%" class="viewTable" id="tblFeedbackEvaluateForm">       
                                <tr hidden="true">
                                    <td width="30%" style="text-align: right"><sd:Label key="Yêu cầu bổ sung"/></td>
                                    <td width="70%">
                                        <sd:TextBox key="" id="feedbackApproveForm.fileId" name="createForm.fileId" cssStyle="display:none"/>
                                        <input type="radio"
                                               id="feedbackApproveForm.statusDeny"
                                               name="createForm.status" value="27" checked="true"/>
                                        </br>
                                        <input type="radio"
                                               id="feedbackApproveForm.statusAccept"
                                               name="createForm.status" value="9"/>
                                        </br>
                                        <input type="radio"
                                               id="feedbackApproveForm.statusApprove"
                                               name="createForm.status" value="6"/>
                                        <sd:Label key=""/>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Tên tổ chức, cá nhân"/></td>
                                    <td width="70%">
                                        <div id="feedbackApproveForm.businessName" style="font-weight: bold"></div>
                                    </td>            
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Tên sản phẩm"/></td>
                                    <td width="70%">
                                        <div id="feedbackApproveForm.productName" style="font-weight: bold"></div>
                                    </td>            
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Nội dung thông báo yêu cầu sđbs/Lý do phê duyệt hồ sơ"/></td>
                                    <td width="70%">
                                        <sd:Textarea key=""
                                                     id="feedbackApproveForm.leaderRequest"
                                                     name="createForm.leaderRequest"
                                                     rows="15" cssStyle="width:99%" maxlength="2000" trim="true"/>
                                        <sd:Textarea key=""
                                                     id="feedbackApproveForm.leaderStaffRequest"
                                                     name="createForm.leaderStaffRequest"
                                                     rows="10" cssStyle="width:99%;display:none" trim="true"/>
                                    </td>
                                </tr>
                                <tr style="display: none">
                                    <td style="text-align: right"><sd:Label key="Yêu cầu chuyển loại hồ sơ"/></td>
                                    <td>
                                        <sd:CheckBox key="" id="ckbIsTypeChangeLDC" name="ckbIsTypeChangeLDC" value=""/>
                                        <sd:TextBox key=""
                                                    id="feedbackApproveForm.isTypeChange"
                                                    name="createForm.isTypeChange" cssStyle="display:none" />
                                    </td>
                                </tr>                                
                                <tr>
                                    <td colspan="2" style="text-align: center">
                                        <%--   <sd:Button id="btnAcceptFBAF" key="" onclick="page.onApproveSign();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/signature-icon.gif" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Phê duyệt công văn SĐBS</span>
                                        </sd:Button>       --%>     
                                        <sd:Button id="btnAcceptFBAF" key=""
                                                   onclick="page.onApproveSignPlugin();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/signature-icon.gif" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Phê duyệt công văn SĐBS</span>
                                        </sd:Button>
                                        <sd:Button id="btnExportFBAF" key=""
                                                   onclick="page.downloadFBAF();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Xem trước công văn</span>
                                        </sd:Button>
                                        <sd:Button id="btnDenyFBAF" key=""
                                                   onclick="page.onDenyApproveFBAF();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/deleteStand.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Từ chối phê duyệt công văn SĐBS</span>
                                        </sd:Button>
                                        <%--<sd:Button id="btnStatusAcceptAF" key="" onclick="page.onApproveSignPD();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Phê duyệt hồ sơ</span>
                                        </sd:Button>--%>
                                        <sd:Button id="btnStatusAcceptAF" key=""
                                                   onclick="page.onApproveSignPDPlugin();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Phê duyệt hồ sơ</span>
                                        </sd:Button> 
                                        <%--<sx:ButtonClose onclick="onCloseFeedbackApproveForm();"/>--%>
                                    </td>
                                </tr>
                                <tr id="trWait" style="display: none">
                                    <td colspan="2" style="text-align: center;alignment-adjust: middle">
                                        <label id="labelWait" style="color: red">Vui lòng chờ  </label>
                                        <img src="/share/images/loading/loading2.gif" width="20px" height="20px">
                                    </td>
                                </tr>                                
                                <tr>
                                    <td colspan="2" style="text-align: center;alignment-adjust: middle">
                                        <div id="divSignProcess" style="display: none;text-align: center;alignment-adjust: middle">
                                            <table>
                                                <tr>
                                                    <td>
                                                        <div id="divProcess" style="color:red;font-weight: bold;margin-left: 10px;margin-top: 3px"></div>
                                                    </td>
                                                    <td>
                                                        <img src="/share/images/loading/loading2.gif" width="20px" height="20px" >
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </sd:TitlePane>
                </div>
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
<jsp:include page="pluginJSFAFP.jsp" flush="false"></jsp:include>
    <script type="text/javascript">
        var passwordCa;
        var signType;

        onCloseApprove = function () {
            count = 0;
            var titlePaneViewFile_FAFP = dijit.byId('titlePaneViewFile_FAFP');
            if (titlePaneViewFile_FAFP) {
                titlePaneViewFile_FAFP.destroyRecursive(true);
            }
            var titlePaneEvaluate_FAFP = dijit.byId('titlePaneEvaluate_FAFP');
            if (titlePaneEvaluate_FAFP) {
                titlePaneEvaluate_FAFP.destroyRecursive(true);
            }
            var txtBase64HashFAFPCheck = dijit.byId('txtBase64HashFAFP');
            if (txtBase64HashFAFPCheck) {
                txtBase64HashFAFPCheck.destroyRecursive(true);
            }
            var txtBase64HashAFP0Check = dijit.byId('txtBase64HashAFP0');
            if (txtBase64HashAFP0Check) {
                txtBase64HashAFP0Check.destroyRecursive(true);
            }
            var txtCertSerialFAFPCheck = dijit.byId('txtCertSerialFAFP');
            if (txtCertSerialFAFPCheck) {
                txtCertSerialFAFPCheck.destroyRecursive(true);
            }
            doGoToMenu("filesAction!lookupFilesByLeaderApproveSdbs.do?searchForm.searchType=26");
        };
        afterFeedbackApproveAction = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (!flagSignMore)
            {
                alert(result[1]);
                if (result[0] == "1") {
                    document.getElementById("trWait").style.display = "none";
                    onCloseFeedbackApproveForm();
                    page.search();
                }
                else
                {
                    page.clearContentFBAF();
                }
            }
            else
            {
                if (result[0] == "1") {
                    if (signIndex == itemsToSign.length - 1)
                    {
                        msg.alert('Ký số công văn thành công', 'Thông báo');
                        document.getElementById("divSignProcess").style.display = "none";
                        document.getElementById("trWait").style.display = "none";
                        // onCloseFeedbackApproveForm();
                        onCloseApprove();
                        // page.search();
                    }
                    else
                    {
                        signIndex++;
                        onCloseFeedbackApproveForm();
                        page.signMoreFilesSDBS();
                    }
                }
                else
                {
                    msg.alert('Ký số công văn không thành công', 'Lỗi hệ thống');
                    document.getElementById("divSignProcess").style.display = "none";
                    document.getElementById("trWait").style.display = "none";
                    page.clearContentFBAF();
                    page.search();
                }
            }
        };
        page.onDenyApproveFBAF = function () {
            //msg.confirm("Bạn có chắc muốn từ chối phê duyệt công văn SĐBS này?", "Từ chối phê duyệt công văn", function () {
            if (page.validateFeedbackApproveForm()) {
                document.getElementById("feedbackApproveForm.statusAccept").checked = true;
                document.getElementById("feedbackApproveForm.statusDeny").checked = false;
                document.getElementById("feedbackApproveForm.statusApprove").checked = false;
                sd.connector.post("filesAction!onFeedbackApprove.do?" + token.getTokenParamString(), null, "feedbackApproveForm", null, afterFeedbackApproveAction);
            }
            //});
        };

        onApprove = function () {
            page.clearContentFBAF();
            if (signType == "CVBS")
            {
                sd.connector.post("filesAction!onFeedbackApprove.do?" + token.getTokenParamString(), null, "feedbackApproveForm", null, afterFeedbackApproveAction);
            }
            else if (signType == "PDHS")
            {
                sd.connector.post("filesAction!onApprove.do?" + token.getTokenParamString(), null, "feedbackApproveForm", null, afterFeedbackApproveAction);
            }
        };

        //    page.onApproveSign = function() {
        //        dijit.byId("btnAcceptFBAF").domNode.style.display = "none";
        //        dijit.byId("btnDenyFBAF").domNode.style.display = "none";
        //        dijit.byId("btnExportFBAF").domNode.style.display = "none";
        //        dijit.byId("btnStatusAcceptAF").domNode.style.display = "none";
        //
        //        signType = "CVBS";
        //        var loginPas = '${passwordCa}';
        //        if (loginPas == null || loginPas.toString() == "null" || loginPas.toString() == "")
        //        {
        //            loginPas = passwordCa;
        //        }
        //        if (page.validateFeedbackApproveForm()) {
        //            if (loginPas == null || loginPas.toString() == "null" || loginPas.toString() == "")
        //            {
        //                passwordCa = "";
        //                //flagEnterDlg = true;
        //                dijit.byId("loginCADlg").show();
        //            }
        //            else
        //            {
        //                passwordCa = loginPas;
        //                page.onApproveSignSdbsAffterLogin();
        //            }
        //        }
        //        else
        //        {
        //            dijit.byId("btnAcceptFBAF").domNode.style.display = "";
        //            dijit.byId("btnDenyFBAF").domNode.style.display = "";
        //            dijit.byId("btnExportFBAF").domNode.style.display = "";
        //            dijit.byId("btnStatusAcceptAF").domNode.style.display = "";
        //        }
        //    };

        //hieptq update 221015
        page.onApproveSignPlugin = function () {//phe duyet ho so
            dijit.byId("btnAcceptFBAF").domNode.style.display = "none";
            dijit.byId("btnDenyFBAF").domNode.style.display = "none";
            dijit.byId("btnExportFBAF").domNode.style.display = "none";
            dijit.byId("btnStatusAcceptAF").domNode.style.display = "none";
            signType = "CVBS";
            if (page.validateFeedbackApproveForm()) {
                page.onApproveSignSdbsAffterLogin();
            }
            else
            {
                dijit.byId("btnAcceptFBAF").domNode.style.display = "";
                dijit.byId("btnDenyFBAF").domNode.style.display = "";
                dijit.byId("btnExportFBAF").domNode.style.display = "";
                dijit.byId("btnStatusAcceptAF").domNode.style.display = "";
            }
        };

        page.onApproveSignSdbsAffterLogin = function () {//ki so phe duyet ho so
            //msg.confirm("Bạn có chắc muốn phê duyệt công văn SĐBS này?", "Phê duyệt công văn", function () {
            page.clearContentFBAF();
            var fileId = dijit.byId("feedbackApproveForm.fileId").getValue();
            document.getElementById("trWait").style.display = "";
            document.getElementById("labelWait").innerHTML = "Hệ thống đang tạo công văn và ký số, vui lòng chờ  ";
            var content = page.utf8_to_b64FBAF(dijit.byId("feedbackApproveForm.leaderRequest").getValue());
            content = content.replaceAllFBAF('+', '_');
            sd.connector.post("filesAction!onExportCvSdbsSignPlugin.do?fileId=" + fileId + "&content=" + content + "&" + token.getTokenParamString(), null, null, null, page.onFeedbackApproveAction);
            //});
        };
        var count = 0;
        var cert;

        page.onFeedbackApproveAction = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                document.getElementById("trWait").style.display = "";
                var fileId = dijit.byId("feedbackApproveForm.fileId").getValue();
                if (count == 0) {
                    var item = uploadCertOfFile(fileId);
                    cert = encodeBase64(item.certChain);
                }
                var path = result[2];
                sd.connector.post("filesAction!actionSignCA.do?fileId=" + fileId + "&cert=" + cert + "&signType=" + signType + "&path=" + path, null, null, null, page.signPluginFAFP);
                // sd.connector.post("filesAction!actionSignCA.do?fileId=" + fileId + "&signType=" + signType + "&path=" + path + "&cert=" + cert, null, null, null, page.signPluginFAFP);
                count++;
            } else {
                msg.alert("Có lỗi trong quá trình xuất công văn SĐBS", "Cảnh báo");
                document.getElementById("trWait").style.display = "none";
                dijit.byId("btnAcceptFBAF").domNode.style.display = "";
                dijit.byId("btnDenyFBAF").domNode.style.display = "";
                dijit.byId("btnExportFBAF").domNode.style.display = "";
                dijit.byId("btnStatusAcceptAF").domNode.style.display = "";
            }
        };

        /*********************  PHE DUYET HO SO *************************/

        page.onApproveSignPDPlugin = function () {
            signType = "PDHS";
            document.getElementById("feedbackApproveForm.statusAccept").checked = false;
            document.getElementById("feedbackApproveForm.statusDeny").checked = false;
            document.getElementById("feedbackApproveForm.statusApprove").checked = true;
            if (page.validateApproveForm()) {
                dijit.byId("btnAcceptFBAF").domNode.style.display = "none";
                dijit.byId("btnDenyFBAF").domNode.style.display = "none";
                dijit.byId("btnExportFBAF").domNode.style.display = "none";
                dijit.byId("btnStatusAcceptAF").domNode.style.display = "none";
                page.onApproveSignAffterLogin();
            }
        };

        page.onApproveSignAffterLogin = function () {
            dijit.byId("feedbackApproveForm.leaderStaffRequest").setValue(dijit.byId("feedbackApproveForm.leaderRequest").getValue());
            document.getElementById("trWait").style.display = "";
            document.getElementById("labelWait").innerHTML = "Hệ thống đang tạo bản công bố, vui lòng chờ  ";
            sd.connector.post("filesAction!onCreatePaper.do?" + token.getTokenParamString(), null, "feedbackApproveForm", null, afteronCreatePaper);
        };

        afteronCreatePaper = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                document.getElementById("labelWait").value = "Hệ thống đang thực hiện ký và tải bản công bố, vui lòng chờ! ";
                var fileId = dijit.byId("feedbackApproveForm.fileId").getValue();
                sd.connector.post("exportWord!onExportPaperSignPlugin.do?fileId=" + fileId, null, null, null, page.signAfterFAFP);
            } else {
                msg.alert("Có lỗi trong quá trình tạo giấy", "Cảnh báo");
                document.getElementById("trWait").style.display = "none";
            }
        };

        page.signAfterFAFP = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                signType = "PDHS";
                var fileId = dijit.byId("feedbackApproveForm.fileId").getValue();
                if (count == 0) {
                    var item = uploadCertOfFile(fileId);
                    cert = encodeBase64(item.certChain);
                }
                var path = result[2];
                sd.connector.post("filesAction!actionSignCA.do?fileId=" + fileId + "&cert=" + cert + "&signType=" + signType + "&path=" + path, null, null, null, page.signPluginFAFP);
                count++;
            } else {
                alert(result[1]);
                document.getElementById("trWait").style.display = "none";
                page.clearContentFBAF();
            }

        };

        page.validateApproveForm = function () {
            if (document.getElementById("feedbackApproveForm.statusApprove").checked == false) {
                alert("Ký duyệt hồ sơ không thành công: Trạng thái chuyển không đúng!");
                return false;
            }
            var leaderRequest = dijit.byId("feedbackApproveForm.leaderRequest").getValue();
            if (leaderRequest.trim().length == 0) {
                alert("Bạn chưa nhập lý do phê duyệt hồ sơ");
                dijit.byId("feedbackApproveForm.leaderRequest").focus();
                return false;
            }
            return true;
        };

        /*********************  END - PHE DUYET HO SO *************************/
        page.clearContentFBAF = function () {
            //        document.getElementById('showContent').innerHTML = '';
            //        document.getElementById("showContent").style.display = "none";
            dijit.byId("btnAcceptFBAF").domNode.style.display = "";
            dijit.byId("btnDenyFBAF").domNode.style.display = "";
            dijit.byId("btnExportFBAF").domNode.style.display = "";
            dijit.byId("btnStatusAcceptAF").domNode.style.display = "";
        };

        page.signPluginFAFP = function (data)
        {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                var txtBase64HashNew = result[2];
                var certSerialNew = result[3];
                var fileId = result[4];
                var outPutPath = result[5];
                var fileName = result[6];
                var txtBase64HashNew0 = result[7];
                var outPutPath2 = result[8];
                var fileName0 = result[9];
                dijit.byId("txtBase64HashFAFP").setValue(txtBase64HashNew);
                dijit.byId("txtCertSerialFAFP").setValue(certSerialNew);
                dijit.byId("txtBase64HashAFP0").setValue(txtBase64HashNew0);
                var sign = signAndSubmit();
                var signData = encodeBase64(sign);
                var signData2;
                var sign2;
                if (signType == "PDHS") {
                    sign2 = signAndSubmitOriginalFile();
                    signData2 = encodeBase64(sign2);
                }
                //binhnt update 160520
                //sd.connector.post("filesAction!onSignPlugin.do?fileId=" + fileId + "&outPutPath=" + outPutPath + "&signData=" + signData + "&signType=" + signType + "&fileName=" + fileName, null, null, null, page.afterSignPluginFAFP);
                sd.connector.post("filesAction!onSignPlugin.do?fileId=" + fileId + "&outPutPath=" + outPutPath + "&signData=" + signData + "&signType=" + signType + "&fileName=" + fileName + "&outPutPath2=" + outPutPath2 + "&fileName0=" + fileName0 + "&signDataOriginal=" + signData2, null, null, null, page.afterSignPluginFAFP);
            } else
            {
                alert("Ký số không thành công !" + result[1]);
            }
        };

        page.afterSignPluginFAFP = function (data)
        {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                onApprove();
            } else
            {
                alert("Ký số không thành công !" + result[1]);
            }
        };

        page.validateFeedbackApproveForm = function () {
            document.getElementById("feedbackApproveForm.statusDeny").checked = true;
            if (document.getElementById("ckbIsTypeChangeLDC").checked == false) {
                dijit.byId("feedbackApproveForm.isTypeChange").setValue(0);
            } else {
                dijit.byId("feedbackApproveForm.isTypeChange").setValue(1);
            }
            var leaderRequest = dijit.byId("feedbackApproveForm.leaderRequest").getValue();
            if (leaderRequest.trim().length == 0) {
                alert("Bạn chưa nhập [Nội dung]");
                dijit.byId("feedbackApproveForm.leaderRequest").focus();
                return false;
            }
            return true;
        };

        onCloseFeedbackApproveForm = function () {
            dijit.byId("feedbackApproveFormDlgPlugin").hide();
            dijit.byId("feedbackApproveForm.leaderRequest").setValue("");
            document.getElementById("trWait").style.display = "none";
            document.getElementById("feedbackApproveForm.statusAccept").checked = false;
            document.getElementById("feedbackApproveForm.statusDeny").checked = true;
            document.getElementById("feedbackApproveForm.statusApprove").checked = false;
            page.clearContentFBAF();
        };
        page.downloadFBAF = function () {//xuat file ket qua tham dinh
            var fileId = dijit.byId("feedbackApproveForm.fileId").getValue();
            var content = page.utf8_to_b64FBAF(dijit.byId("feedbackApproveForm.leaderRequest").getValue());
            content = content.replaceAllFBAF('+', '_');
            document.location = "exportWord!onXuatTBSDBS.do?fileId=" + fileId + "&content=" + content;
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
<input type="hidden" id="base64Hash0" value="" />
<sd:TextBox id="txtBase64HashAFP0" key="" name="txtBase64Hash0" type="hidden"/>
<input type="hidden" id="base64Hash" value="" />
<sd:TextBox id="txtBase64HashFAFP" key="" name="txtBase64Hash" type="hidden"/>
<input type="hidden" id="certSerial" value="" />
<sd:TextBox id="txtCertSerialFAFP" key="" name="txtCertSerial" type="hidden"/>
<object id="plugin0" type="application/x-viettelcasigner" width="3" height="10">
</object>