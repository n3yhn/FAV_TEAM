<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<object id="plugin0" type="application/x-viettelcasigner" width="3" height="10">
</object>

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
                <sd:TitlePane key="Thông tin hồ sơ" id="titlePaneViewFile_AFP">
                    <div id="divViewFile" style="overflow-y: auto;max-height: 600px"></div>
                </sd:TitlePane>
            </td>
            <td style="width: 38%">
                <sd:TitlePane key="Phê duyệt hồ sơ" id="titlePaneEvaluate_AFP">
                    <div style="overflow-y: auto;max-height: 600px">
                        <form id="approveForm" name="createForm">
                            <table width="100%" class="viewTable">
                                <tr>
                                    <td width="30%" style="text-align: right">
                                        <sd:Label key="Tên tổ chức, cá nhân"/></td>
                                    <td width="70%">
                                        <div id="approveForm.businessName" style="font-weight: bold"></div>
                                    </td>            
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right">
                                        <sd:Label key="Tên sản phẩm"/></td>
                                    <td width="70%">
                                        <div id="approveForm.productName" style="font-weight: bold"></div>
                                    </td>            
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right">
                                        <sd:Label key="Kết quả xem xét"/></td>
                                    <td width="70%">
                                        <div id="approveForm.status"></div>
                                    </td>            
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right">
                                        <sd:Label key="Nội dung thẩm định"/></td>
                                    <td width="70%">
                                        <div id="approveForm.staffRequest"></div>
                                    </td>

                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right">
                                        <sd:Label key="Nội dung xem xét"/></td>
                                    <td width="70%">
                                        <div id="approveForm.leaderStaffRequest"></div>
                                    </td>

                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right">
                                        <sd:Label key="Kết quả phê duyệt"/></td>
                                    <td width="70%">
                                        <sd:TextBox key="" 
                                                    id="approveForm.fileId" 
                                                    name="createForm.fileId" 
                                                    cssStyle="display:none"/>
                                        <input type="radio" id="approveForm.statusAccept" 
                                               name="createForm.status" 
                                               value="6" 
                                               onchange="changeStatusApprove();"/>
                                        <sd:Label key="Phê duyệt hồ sơ"/>
                                        <br/>
                                        <input type="radio" id="approveForm.statusDeny" 
                                               name="createForm.status" 
                                               value="27" 
                                               onchange="changeStatusApprove();"/>
                                        <sd:Label key="Phê duyệt công văn SĐBS"/>
                                        <br/>
                                        <input type="radio" id="approveForm.statusCT" 
                                               name="createForm.status" 
                                               value="29" onchange="changeStatusApprove();"/>
                                        <sd:Label key="Trình Cục Trưởng phê duyệt"/>
                                        <br/>
                                        <input type="radio" id="approveForm.statusReReview" 
                                               name="createForm.status" 
                                               value="9" onchange="changeStatusApprove();"/>
                                        <sd:Label key="Trả lại để xem xét lại"/>
                                    </td>
                                </tr>
                                <tr id="trLeaderRequest">
                                    <td style="text-align: right">
                                        <sd:Label key="Nội dung  phê duyệt hoặc yêu cầu SĐBS"/></td>
                                    <td>
                                        <sd:Textarea 
                                            key="" id="approveForm.leaderRequest"
                                            name="createForm.leaderRequest"
                                            rows="3" cssStyle="width:99%" maxlength="1800" trim="true"/>
                                    </td>
                                </tr>
                                <tr id="trTitleEditATTP" style="display:none">
                                    <td style="text-align: right">
                                        <sd:Label key="Tiêu đề hồ sơ SĐBS"/></td>
                                    <td>
                                        <sd:Textarea 
                                            id="approveForm.titleEditATTP" 
                                            name="createForm.titleEditATTP" 
                                            rows="1" cssStyle="width:99%" maxlength="255" trim="true" key=""/>
                                    </td>
                                </tr>
                                <tr id="trContentsEditATTP" style="display:none">
                                    <td style="text-align: right">
                                        <sd:Label key="Tiêu đề hồ sơ SĐBS"/></td>
                                    <td>
                                        <sd:Textarea 
                                            id="approveForm.contentsEditATTP"
                                            name="createForm.contentsEditATTP"
                                            key="" rows="10" cssStyle="width:99%" maxlength="2000" trim="true"/>
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
                                <tr>
                                    <td colspan="2" style="text-align: center">
                                        <sd:Button id="btnStatusAcceptAF" key="" 
                                                   onclick="page.onApproveSignPlugin();" cssStyle="display:none" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Phê duyệt hồ sơ</span>
                                        </sd:Button> 
                                        <sd:Button id="btnStatusDenyAF" key="" 
                                                   onclick="page.onApproveSignSdbsPlugin();" cssStyle="display:none" cssClass="buttonGroup">
                                            <img src="share/images/icons/signature-icon.gif" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Phê duyệt công văn SĐBS</span>
                                        </sd:Button>
                                        <sd:Button id="btnExportAFCV" key="" 
                                                   onclick="page.downloadAFCV();" cssStyle="display:none" cssClass="buttonGroup">
                                            <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Xem trước công văn SĐBS</span>
                                        </sd:Button>
                                        <sd:Button id="btnStatusCTAF" key="" 
                                                   onclick="page.onApproveSignPlugin();" cssStyle="display:none" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Trình lên Cục Trưởng</span>
                                        </sd:Button>   
                                        <sd:Button id="btnStatusReReview" 
                                                   key="" 
                                                   onclick="page.onReReviewPlugin();" 
                                                   cssStyle="display:none" 
                                                   cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" 
                                                 height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Trả lại để xem xét lại</span>
                                        </sd:Button> 
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
                <sx:ButtonClose onclick="onCloseApproveDlg();"/>
            </td>
        </tr>
    </table>
</div>


<jsp:include page="pluginJSAFP.jsp" flush="false"></jsp:include>
    <script type="text/javascript">

        var signType;
        var passwordCa;
        var flagSign;
        var cert;
        var count = 0;

        onCloseApproveDlg = function () {
            count = 0;
            var titlePaneViewFileAFP = dijit.byId('titlePaneViewFile_AFP');
            if (titlePaneViewFileAFP) {
                titlePaneViewFileAFP.destroyRecursive(true);
            }
            var titlePaneEvaluateAFP = dijit.byId('titlePaneEvaluate_AFP');
            if (titlePaneEvaluateAFP) {
                titlePaneEvaluateAFP.destroyRecursive(true);
            }
            var txtBase64HashAFPCheck = dijit.byId('txtBase64HashAFP');
            if (txtBase64HashAFPCheck) {
                txtBase64HashAFPCheck.destroyRecursive(true);
            }
            var txtBase64HashAFP0Check = dijit.byId('txtBase64HashAFP0');
            if (txtBase64HashAFP0Check) {
                txtBase64HashAFP0Check.destroyRecursive(true);
            }
            var txtCertSerialAFPCheck = dijit.byId('txtCertSerialAFP');
            if (txtCertSerialAFPCheck) {
                txtCertSerialAFPCheck.destroyRecursive(true);
            }
            doGoToMenu("filesAction!toApprovePageAA.do");
        };

        changeStatusApprove = function () {
            var trTitleEditATTP = document.getElementById('trTitleEditATTP');
            var trContentsEditATTP = document.getElementById('trContentsEditATTP');
            var trStaffRequest = document.getElementById('trStaffRequest');

            if (document.getElementById("approveForm.statusAccept").checked) {
                trLeaderRequest.style.display = 'none';
                trTitleEditATTP.style.display = '';
                trContentsEditATTP.style.display = '';

                dijit.byId("btnStatusAcceptAF").domNode.style.display = "";
                dijit.byId("btnStatusDenyAF").domNode.style.display = "none";
                dijit.byId("btnExportAFCV").domNode.style.display = "none";
                dijit.byId("btnStatusCTAF").domNode.style.display = "none";
                dijit.byId("btnStatusReReview").domNode.style.display = "none";
            } else {
                if (document.getElementById("approveForm.statusDeny").checked) {
                    dijit.byId("btnStatusAcceptAF").domNode.style.display = "none";
                    dijit.byId("btnStatusDenyAF").domNode.style.display = "";
                    dijit.byId("btnExportAFCV").domNode.style.display = "";
                    dijit.byId("btnStatusCTAF").domNode.style.display = "none";
                    dijit.byId("btnStatusReReview").domNode.style.display = "none";

                    trLeaderRequest.style.display = '';
                    trTitleEditATTP.style.display = 'none';
                    trContentsEditATTP.style.display = 'none';
                } else {
                    if (document.getElementById("approveForm.statusCT").checked) {
                        dijit.byId("btnStatusAcceptAF").domNode.style.display = "none";
                        dijit.byId("btnStatusDenyAF").domNode.style.display = "none";
                        dijit.byId("btnStatusDenyAF").domNode.style.display = "none";
                        dijit.byId("btnExportAFCV").domNode.style.display = "none";
                        dijit.byId("btnStatusCTAF").domNode.style.display = "";
                        dijit.byId("btnStatusReReview").domNode.style.display = "none";

                        trLeaderRequest.style.display = '';
                        trTitleEditATTP.style.display = 'none';
                        trContentsEditATTP.style.display = 'none';
                    } else {
                        if (document.getElementById("approveForm.statusReReview").checked)//tra lai de xem xet
                        {
                            dijit.byId("btnStatusAcceptAF").domNode.style.display = "none";
                            dijit.byId("btnStatusDenyAF").domNode.style.display = "none";
                            dijit.byId("btnStatusDenyAF").domNode.style.display = "none";
                            dijit.byId("btnExportAFCV").domNode.style.display = "none";
                            dijit.byId("btnStatusCTAF").domNode.style.display = "none";
                            dijit.byId("btnStatusReReview").domNode.style.display = "";

                            trLeaderRequest.style.display = '';
                            trTitleEditATTP.style.display = 'none';
                            trContentsEditATTP.style.display = 'none';
                        } else {
                            dijit.byId("btnStatusAcceptAF").domNode.style.display = "none";
                            dijit.byId("btnStatusDenyAF").domNode.style.display = "none";
                            dijit.byId("btnExportAFCV").domNode.style.display = "none";
                            dijit.byId("btnStatusCTAF").domNode.style.display = "none";
                            dijit.byId("btnStatusReReview").domNode.style.display = "none";

                            trLeaderRequest.style.display = 'none';
                            trTitleEditATTP.style.display = 'none';
                            trContentsEditATTP.style.display = 'none';
                        }
                    }
                }
            }
        };

        afterApprove = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (signType == "PDHS")
            {
                if (!flagSignMore)
                {
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
                            page.signMoreFilesPlugin();
                        }
                    } else {
                        msg.alert('Ký số không thành công', 'Lỗi hệ thống');
                        document.getElementById("divSignProcess").style.display = "none";
                        document.getElementById("trWait").style.display = "none";
                        page.search();
                    }
                }
            } else if (signType == "CVBS")
            {
                alert(result[1]);
                if (result[0] == "1") {
                    document.getElementById("trWait").style.display = "none";
                    onCloseApproveDlg();
                    //backPage();
                    page.search();
                }
            }
        };

        //////////////////////////////////////////////////////////////////////////////// Cong van SDBS - MOI BO SUNG
        page.onApproveSignSdbs = function () {
        };

        //hieptq update 201015
        page.onApproveSignSdbsPlugin = function () {
            signType = "CVBS";
            page.onApproveSignSdbsAffterPlugin();
        };

        page.onApproveSignSdbsAffterPlugin = function () {
            msg.confirm("Bạn có chắc muốn phê duyệt công văn SĐBS này?", "Phê duyệt công văn", function () {
                //  CAP NHAT HAM VALIDATE
                if (page.validateAFCV()) {
                    var fileId = dijit.byId("approveForm.fileId").getValue();
                    document.getElementById("trWait").style.display = "";
                    document.getElementById("labelWait").innerHTML = "Hệ thống đang tạo công văn và ký số, vui lòng chờ  ";
                    var content = page.utf8_to_b64AFCV(dijit.byId("approveForm.leaderRequest").getValue());
                    content = content.replaceAllAFCV('+', '_');
                    sd.connector.post("filesAction!onExportCvSdbsSignPlugin.do?fileId=" + fileId + "&content=" + content, null, null, null, onFeedbackApproveAFPlugin);
                }
            });
        };


        page.onApproveSignSdbsAffterLogin = function () {

        };

        page.validateAFCV = function () {
            if (document.getElementById("approveForm.statusDeny").checked) {
                var leaderRequest = dijit.byId("approveForm.leaderRequest").getValue();
                if (leaderRequest.trim().length == 0) {
                    alert("Bạn chưa nhập [Nội dung]");
                    dijit.byId("approveForm.leaderRequest").focus();
                    return false;
                }
            } else {
                return false;
            }
            return true;
        };

        onFeedbackApproveAFPlugin = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                document.getElementById("trWait").style.display = "";
                signType = "CVBS";
                var fileId = dijit.byId("approveForm.fileId").getValue();
                if (count == 0) {
                    var item = uploadCertOfFile(fileId);
                    cert = encodeBase64(item.certChain);
                }
                var path = result[2];
                sd.connector.post("filesAction!actionSignCA.do?fileId=" + fileId + "&cert=" + cert + "&signType=" + signType + "&path=" + path, null, null, null, page.signPluginAFP);
                count++;

            } else {
                msg.alert("Có lỗi trong quá trình xuất công văn SĐBS", "Cảnh báo");
                document.getElementById("trWait").style.display = "none";
                document.getElementById("divSignProcess").style.display = "none";
            }
        };


        onFeedbackApproveAF = function (data) {
        };

        page.downloadAFCV = function () {//xuat file ket qua tham dinh
            var fileId = dijit.byId("approveForm.fileId").getValue();
            var content = page.utf8_to_b64AFCV(dijit.byId("approveForm.leaderRequest").getValue());
            content = content.replaceAllAFCV('+', '_');
            document.location = "exportWord!onXuatTBSDBS.do?fileId=" + fileId + "&content=" + content;
        };

        page.utf8_to_b64AFCV = function (str) {
            return window.btoa(unescape(encodeURIComponent(str)));
        };

        String.prototype.replaceAllAFCV = function (strTarget, strSubString) {
            var strText = this;
            var intIndexOfMatch = strText.indexOf(strTarget);
            while (intIndexOfMatch != -1) {
                strText = strText.replace(strTarget, strSubString)
                intIndexOfMatch = strText.indexOf(strTarget);
            }
            return(strText);
        };

        onApprove = function () {
            if (signType == "PDHS")
            {
                var leaderRequest = dijit.byId("approveForm.leaderRequest").getValue();
                if (document.getElementById("approveForm.statusAccept").checked == false
                        && document.getElementById("approveForm.statusDeny").checked == false && document.getElementById("approveForm.statusCT").checked == false)
                {
                    msg.alert("Bạn chưa chọn 'Duyệt: Hồ sơ đạt' hay 'Yêu cầu bổ sung' hay 'Trình lên cục trưởng'", "Cảnh báo");
                }
                if (document.getElementById("approveForm.statusAccept").checked) {
                    sd.connector.post("filesAction!onApprove.do?" + token.getTokenParamString(), null, "approveForm", null, afterApprove);
                } else {
                    if (document.getElementById("approveForm.statusDeny").checked == true
                            || document.getElementById("approveForm.statusCT").checked == true)
                    {
                        if (leaderRequest.trim().length == 0)
                        {
                            msg.alert("Bạn chưa nhập nội dung", "Cảnh báo");
                        } else
                        {
                            sd.connector.post("filesAction!onApprove.do?" + token.getTokenParamString(), null, "approveForm", null, afterApprove);
                        }
                    }
                }
            } else if (signType == "CVBS") {
                // SUA LAI HAM CAP NHAT TRANG THAI, DANG DUNG FORM SDBS
                sd.connector.post("filesAction!onFeedbackApprove.do?" + token.getTokenParamString(), null, "approveForm", null, afterApprove);
            }
        };

        page.onApprovetoCT = function () {
            if (signType == "PDHS")
            {
                var leaderRequest = dijit.byId("approveForm.leaderRequest").getValue();
                if (document.getElementById("approveForm.statusAccept").checked == false && document.getElementById("approveForm.statusDeny").checked == false && document.getElementById("approveForm.statusCT").checked == false)
                {
                    msg.alert("Bạn chưa chọn 'Duyệt: Hồ sơ đạt' hay 'Yêu cầu bổ sung' hay 'Trình lên cục trưởng'", "Cảnh báo");
                }
                if (document.getElementById("approveForm.statusCT").checked == true)
                {
                    if (leaderRequest.trim().length == 0)
                    {
                        dijit.byId("approveForm.leaderRequest").focus();
                        msg.alert("Bạn chưa nhập nội dung", "Cảnh báo");
                    } else
                    {
                        sd.connector.post("filesAction!onApprove.do?" + token.getTokenParamString(), null, "approveForm", null, page.afterApprovetoCT);
                    }
                }
            }
        };

        page.afterApprovetoCT = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            alert(result[1]);
            if (result[0] == "1") {
                document.getElementById("trWait").style.display = "none";
                onCloseApproveDlg();
                page.search();
            }
        };

        onCloseApprove = function () {
            dijit.byId("approveDlg").hide();
            dijit.byId("approveForm.leaderRequest").setValue("");
            document.getElementById("approveForm.statusAccept").checked = false;
            document.getElementById("approveForm.statusDeny").checked = false;
            document.getElementById("approveForm.statusCT").checked = false;
            document.getElementById("approveForm.statusReReview").checked = false;
            document.getElementById("trWait").style.display = "none";
            // page.clearContent();
        };

        //hieptq update 121015
        page.onApproveSignPlugin = function () {
            if (document.getElementById("approveForm.statusAccept").checked) {
                signType = "PDHS";
                document.getElementById("trWait").style.display = "";
                document.getElementById("labelWait").innerHTML = "Hệ thống đang tạo bản công bố, vui lòng chờ  ";
                sd.connector.post("filesAction!onCreatePaper.do?" + token.getTokenParamString(), null, "approveForm", null, afteronCreatePaper);
            } else {
                ////binhnt53 update do nothing 141209
                signType = "PDHS";
                page.onApprovetoCT();
            }
        };

        afteronCreatePaper = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                document.getElementById("labelWait").value = "Hệ thống đang thực hiện ký và tải bản công bố, vui lòng chờ! ";
                var fileId = dijit.byId("approveForm.fileId").getValue();
                sd.connector.post("exportWord!onExportPaperSignPlugin.do?fileId=" + fileId, null, null, null, page.signAfterAFP);
            } else {
                msg.alert("Có lỗi trong quá trình tạo giấy", "Cảnh báo");
                document.getElementById("trWait").style.display = "none";
            }
        };

        function sleep(milliseconds) {
            var start = new Date().getTime();
            for (var i = 0; i < 1e7; i++) {
                if ((new Date().getTime() - start) > milliseconds) {
                    break;
                }
            }
        }
        ;

        page.signAfterAFP = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
//                signType = "PDHS";
                var fileId = dijit.byId("approveForm.fileId").getValue();
                if (count == 0) {
                    var item = uploadCertOfFile(fileId);
                    cert = encodeBase64(item.certChain);
                }
                var path = result[2];
                sd.connector.post("filesAction!actionSignCAForAA.do?fileId=" + fileId + "&cert=" + cert + "&signType=" + signType + "&path=" + path, null, null, null, page.signPluginAFP);
                count++;
            } else {
                alert(result[1]);
                document.getElementById("trWait").style.display = "none";
                document.getElementById("approveForm.statusAccept").checked = false;
                document.getElementById("approveForm.statusDeny").checked = false;
                document.getElementById("approveForm.statusCT").checked = false;
            }
        };

        page.signPluginAFP = function (data)
        {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                var txtBase64HashNew = result[2];
                var certSerialNew = result[3];
                var fileId = result[4];
                var outPutPath = result[5];
                var fileName = result[6];
                var outPutPath2 = result[8];
                var txtBase64HashNew0 = result[7];
                var fileName0 = result[9];
//                signType = "PDHS";
                dijit.byId("txtBase64HashAFP").setValue(txtBase64HashNew);
                dijit.byId("txtCertSerialAFP").setValue(certSerialNew);
                dijit.byId("txtBase64HashAFP0").setValue(txtBase64HashNew0);
                var sign = signAndSubmit();
                var signData = encodeBase64(sign);
                var signData2;
                var sign2;
                if (signType == "PDHS") {
                    sign2 = signAndSubmitOriginalFileAA();
                    signData2 = encodeBase64(sign2);
                }
                sd.connector.post("filesAction!onSignPluginAA.do?fileId=" + fileId + "&outPutPath=" + outPutPath + "&signData=" + signData + "&signType=" + signType + "&fileName=" + fileName + "&outPutPath2=" + outPutPath2 + "&fileName0=" + fileName0 + "&signDataOriginal=" + signData2, null, null, null, page.afterSignPluginAFP);
            } else
            {
                alert("Ký số không thành công ! " + result[1]);
            }
        };

        page.afterSignPluginAFP = function (data)
        {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                onApprove();
            } else
            {
                alert("Ký số không thành công !");
            }
        };

        page.replaceBrTblApproveForm = function () {
            var content = "";
            content = document.getElementById("approveForm.staffRequest").innerHTML;
            content = content.replace(/\n/g, "<br>");
            document.getElementById("approveForm.staffRequest").innerHTML = content;
            content = document.getElementById("approveForm.leaderStaffRequest").innerHTML;
            content = content.replace(/\n/g, "<br>");
            document.getElementById("approveForm.leaderStaffRequest").innerHTML = content;
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

        //160406 luong tra lai de xem xet lai        
        page.onReReviewPlugin = function () {
            msg.confirm("Bạn có chắc muốn trả lại y/c xem xét lại hồ sơ này?", "Trả lại y/c xem xét lại hồ sơ này?", function () {
                sd.connector.post("filesAction!onApprove.do?" + token.getTokenParamString(),
                        null, "approveForm", null, affterOnReReviewPlugin);
            });
        };
        affterOnReReviewPlugin = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            alert(result[1]);
            if (result[0] == "1") {
                document.getElementById("trWait").style.display = "none";
                onCloseApproveDlg();
                page.search();
            }
        };
    </script>

    <input type="hidden" id="base64Hash0" value="" />
<sd:TextBox id="txtBase64HashAFP0" key="" name="txtBase64Hash0" type="hidden"/>
<input type="hidden" id="base64Hash" value="" />
<sd:TextBox id="txtBase64HashAFP" key="" name="txtBase64Hash" type="hidden"/>
<input type="hidden" id="certSerial" value="" />
<sd:TextBox id="txtCertSerialAFP" key="" name="txtCertSerial" type="hidden"/>

