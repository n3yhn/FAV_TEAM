<%-- 
    Document   : approveForm
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

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
                <sd:TitlePane key="Phê duyệt hồ sơ" id="titlePaneEvaluate">
                    <div style="overflow-y: auto;max-height: 600px">
                        <form id="approveForm" name="createForm">
                            <table width="100%" class="viewTable">
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Tên tổ chức, cá nhân"/></td>
                                    <td width="70%">
                                        <div id="approveForm.businessName" style="font-weight: bold"></div>
                                    </td>            
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Tên sản phẩm"/></td>
                                    <td width="70%">
                                        <div id="approveForm.productName" style="font-weight: bold"></div>
                                    </td>            
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Kết quả xem xét"/></td>
                                    <td width="70%">
                                        <div id="approveForm.status"></div>
                                    </td>            
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Nội dung thẩm định"/></td>
                                    <td width="70%">
                                        <div id="approveForm.staffRequest"></div>
                                    </td>

                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Nội dung xem xét"/></td>
                                    <td width="70%">
                                        <div id="approveForm.leaderStaffRequest"></div>
                                    </td>

                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Kết quả phê duyệt"/></td>
                                    <td width="70%">
                                        <sd:TextBox key="" id="approveForm.fileId" name="createForm.fileId" cssStyle="display:none"/>
                                        <input type="radio" id="approveForm.statusAccept" name="createForm.status" value="6" onchange="changeStatusApprove();"/>
                                        <sd:Label key="Phê duyệt hồ sơ"/>
                                        <br/>
                                        <input type="radio" id="approveForm.statusDeny" name="createForm.status" value="27" onchange="changeStatusApprove();"/>
                                        <sd:Label key="Phê duyệt công văn SĐBS"/>
                                        <br/>
                                        <input type="radio" id="approveForm.statusCT" name="createForm.status" value="29" onchange="changeStatusApprove();"/>
                                        <sd:Label key="Trình Cục Trưởng phê duyệt"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Nội dung  phê duyệt hoặc yêu cầu SĐBS"/></td>
                                    <td>
                                        <sd:Textarea key="" id="approveForm.leaderRequest" name="createForm.leaderRequest" rows="3" cssStyle="width:99%" maxlength="1800" trim="true"/>
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
                                        <sd:Button id="btnStatusAcceptAF" key="" onclick="page.onApproveSign();" cssStyle="display:none" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Phê duyệt hồ sơ</span>
                                        </sd:Button>   
                                        <sd:Button id="btnStatusAcceptAFN" key="" onclick="page.onApproveSignPdf();" cssStyle="display:none" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Phê duyệt hồ sơ (xuất File để ký)</span>
                                        </sd:Button>  
                                        <sd:Button id="btnStatusDenyAF" key="" onclick="page.onApproveSignSdbs();" cssStyle="display:none" cssClass="buttonGroup">
                                            <img src="share/images/icons/signature-icon.gif" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Phê duyệt công văn SĐBS</span>
                                        </sd:Button>
                                        <sd:Button id="btnExportAFCV" key="" onclick="page.downloadAFCV();" cssStyle="display:none" cssClass="buttonGroup">
                                            <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Xem trước công văn SĐBS</span>
                                        </sd:Button>
                                        <sd:Button id="btnStatusCTAF" key="" onclick="page.onApproveSign();" cssStyle="display:none" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Trình lên Cục Trưởng</span>
                                        </sd:Button>               
                                        <%--<sx:ButtonClose onclick="onCloseApprove();"/>--%>
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

<sd:Dialog  id="loginCADlg" height="auto" width="500px"
            key="Ban Cơ yếu" showFullscreenButton="false"
            >
    <jsp:include page="loginCADlg.jsp" flush="false"></jsp:include>
</sd:Dialog>

<div id="showContent" style="visibility: hidden;height: 0px !important;" > 
</div>
<script type="text/javascript">
    var signType;
    var passwordCa;
    var flagSign;

    onCloseApproveDlg = function () {
        doGoToMenu("filesAction!toApprovePage.do");
    };

    changeStatusApprove = function () {
        if (document.getElementById("approveForm.statusAccept").checked) {
            dijit.byId("btnStatusAcceptAF").domNode.style.display = "";
            //dijit.byId("btnStatusAcceptAFN").domNode.style.display = "";
            dijit.byId("btnStatusDenyAF").domNode.style.display = "none";
            dijit.byId("btnExportAFCV").domNode.style.display = "none";
            dijit.byId("btnStatusCTAF").domNode.style.display = "none";
        } else {
            if (document.getElementById("approveForm.statusDeny").checked) {
                dijit.byId("btnStatusAcceptAF").domNode.style.display = "none";
                dijit.byId("btnStatusDenyAF").domNode.style.display = "";
                dijit.byId("btnExportAFCV").domNode.style.display = "";
                dijit.byId("btnStatusCTAF").domNode.style.display = "none";
            } else {
                if (document.getElementById("approveForm.statusCT").checked) {
                    dijit.byId("btnStatusAcceptAF").domNode.style.display = "none";
                    dijit.byId("btnStatusDenyAF").domNode.style.display = "none";
                    dijit.byId("btnExportAFCV").domNode.style.display = "none";
                    dijit.byId("btnStatusCTAF").domNode.style.display = "";
                } else {
                    dijit.byId("btnStatusAcceptAF").domNode.style.display = "none";
                    dijit.byId("btnStatusDenyAF").domNode.style.display = "none";
                    dijit.byId("btnExportAFCV").domNode.style.display = "none";
                    dijit.byId("btnStatusCTAF").domNode.style.display = "none";
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
            }
            else
            {
                if (result[0] == "1") {
                    if (signIndex == itemsToSign.length - 1)
                    {
                        msg.alert('Ký số thành công', 'Thông báo');
                        document.getElementById("divSignProcess").style.display = "none";
                        document.getElementById("trWait").style.display = "none";
                        onCloseApproveDlg();
                        page.search();
                    }

                    else
                    {
                        signIndex++;
                        page.signMoreFiles();
                    }
                }
                else
                {
                    msg.alert('Ký số không thành công', 'Lỗi hệ thống');
                    document.getElementById("divSignProcess").style.display = "none";
                    document.getElementById("trWait").style.display = "none";
                    page.search();
                }
            }
        }
        else if (signType == "CVBS")
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
        flagSign = "CVBS";
        var loginPas = '${passwordCa}';
        if (loginPas == null || loginPas.toString() == "null" || loginPas.toString() == "")
        {
            loginPas = passwordCa;
        }
        if (loginPas == null || loginPas.toString() == "null" || loginPas.toString() == "")
        {
            passwordCa = "";
            dijit.byId("loginCADlg").show();
        }
        else
        {
            passwordCa = loginPas;
            page.onApproveSignSdbsAffterLogin();
        }
    };

    page.onApproveSignSdbsAffterLogin = function () {
        //msg.confirm("Bạn có chắc muốn phê duyệt công văn SĐBS này?", "Phê duyệt công văn", function () {
        //CAP NHAT HAM VALIDATE
        if (page.validateAFCV()) {
            var fileId = dijit.byId("approveForm.fileId").getValue();
            document.getElementById("trWait").style.display = "";
            document.getElementById("labelWait").innerHTML = "Hệ thống đang tạo công văn và ký số, vui lòng chờ  ";
            var content = page.utf8_to_b64AFCV(dijit.byId("approveForm.leaderRequest").getValue());
            content = content.replaceAllAFCV('+', '_');
            sd.connector.post("filesAction!onExportCvSdbsSign.do?fileId=" + fileId + "&content=" + content, null, null, null, onFeedbackApproveAF);
        }
        //});
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

    onFeedbackApproveAF = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result[0] == "1") {
            document.getElementById("trWait").style.display = "";
            signType = "CVBS";
            page.showContent();
        } else {
            msg.alert("Có lỗi trong quá trình xuất công văn SĐBS", "Cảnh báo");
            document.getElementById("trWait").style.display = "none";
            document.getElementById("divSignProcess").style.display = "none";
        }
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
        page.clearContent();
        if (signType == "PDHS")
        {
            var leaderRequest = dijit.byId("approveForm.leaderRequest").getValue();
            if (document.getElementById("approveForm.statusAccept").checked == false && document.getElementById("approveForm.statusDeny").checked == false && document.getElementById("approveForm.statusCT").checked == false)
            {
                msg.alert("Bạn chưa chọn 'Duyệt: Hồ sơ đạt' hay 'Yêu cầu bổ sung' hay 'Trình lên cục trưởng'", "Cảnh báo");
            }
            if (document.getElementById("approveForm.statusAccept").checked) {
                sd.connector.post("filesAction!onApprove.do?" + token.getTokenParamString(), null, "approveForm", null, afterApprove);
            } else {
                if (document.getElementById("approveForm.statusDeny").checked == true || document.getElementById("approveForm.statusCT").checked == true)
                {
                    if (leaderRequest.trim().length == 0)
                    {
                        msg.alert("Bạn chưa nhập nội dung", "Cảnh báo");
                    }
                    else
                    {
                        sd.connector.post("filesAction!onApprove.do?" + token.getTokenParamString(), null, "approveForm", null, afterApprove);
                    }
                }
            }
        }
        else if (signType == "CVBS") {
            // SUA LAI HAM CAP NHAT TRANG THAI, DANG DUNG FORM SDBS
            sd.connector.post("filesAction!onFeedbackApprove.do?" + token.getTokenParamString(), null, "approveForm", null, afterApprove);
        }
    };


    //hieptq update 111214
    onApprovePdf = function () {

        var leaderRequest = dijit.byId("approveForm.leaderRequest").getValue();
        if (document.getElementById("approveForm.statusAccept").checked == false && document.getElementById("approveForm.statusDeny").checked == false && document.getElementById("approveForm.statusCT").checked == false)
        {
            msg.alert("Bạn chưa chọn 'Duyệt: Hồ sơ đạt' hay 'Yêu cầu bổ sung' hay 'Trình lên cục trưởng'", "Cảnh báo");
        }
        if (document.getElementById("approveForm.statusAccept").checked) {
            sd.connector.post("filesAction!onApprove.do?" + token.getTokenParamString(), null, "approveForm", null, afterApprove);
        } else {
            if (document.getElementById("approveForm.statusDeny").checked == true || document.getElementById("approveForm.statusCT").checked == true)
            {
                if (leaderRequest.trim().length == 0)
                {
                    msg.alert("Bạn chưa nhập nội dung", "Cảnh báo");
                }
                else
                {
                    sd.connector.post("filesAction!onApprove.do?" + token.getTokenParamString(), null, "approveForm", null, afterApprove);
                }
            }
        }
    };


    page.onApprovetoCT = function () {
        page.clearContent();
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
                }
                else
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
        document.getElementById("trWait").style.display = "none";
        page.clearContent();
    };

    page.onApproveSign = function () {
        flagSign = "PDHS";
        var loginPas = '${passwordCa}';
        if (loginPas == null || loginPas.toString() == "null" || loginPas.toString() == "")
        {
            loginPas = passwordCa;
        }
        if (!document.getElementById("approveForm.statusCT").checked)
        {
            if (loginPas == null || loginPas.toString() == "null" || loginPas.toString() == "")
            {
                passwordCa = "";
                dijit.byId("loginCADlg").show();
            }
            else
            {
                passwordCa = loginPas;
                page.onApproveSignAffterLogin();
            }
        }
        else
        {
            page.onApproveSignAffterLogin();
        }
    };



    page.onApproveSignAffterLogin = function () {
        //msg.confirm("Bạn có chắc muốn phê duyệt/xin ý kiến Cục trưởng hồ sơ này?", "Phê duyệt/Xin ý kiến Cục trưởng hồ sơ", function () {
        page.clearContent();
        if (document.getElementById("approveForm.statusAccept").checked) {
            // Login

            document.getElementById("trWait").style.display = "";
            document.getElementById("labelWait").innerHTML = "Hệ thống đang tạo bản công bố, vui lòng chờ  ";
            sd.connector.post("filesAction!onCreatePaper.do?" + token.getTokenParamString(), null, "approveForm", null, afteronCreatePaper);
        } else {
            ////binhnt53 update do nothing 141209
            signType = "PDHS";
            page.onApprovetoCT();
        }
        //});
    };

    page.onApproveSignPdf = function () {
        msg.confirm("Bạn có chắc muốn phê duyệt/xin ý kiến Cục trưởng hồ sơ này?", "Phê duyệt/Xin ý kiến Cục trưởng hồ sơ", function () {
            page.clearContent();
            if (document.getElementById("approveForm.statusAccept").checked) {
                document.getElementById("trWait").style.display = "";
                document.getElementById("labelWait").innerHTML = "Hệ thống đang tạo bản công bố, vui lòng chờ  ";
                sd.connector.post("filesAction!onCreatePaper.do?" + token.getTokenParamString(), null, "approveForm", null, afteronCreatePaperPdf);
            } else {
                ////binhnt53 update do nothing 141209
                signType = "PDHS";
                page.onApprovetoCT();
            }
        });
    };

    afteronCreatePaper = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result[0] == "1") {
            document.getElementById("labelWait").value = "Hệ thống đang thực hiện ký và tải bản công bố, vui lòng chờ! ";
            var fileId = dijit.byId("approveForm.fileId").getValue();
            sd.connector.post("exportWord!onExportPaperSign.do?fileId=" + fileId, null, null, null, page.signAfter);
        } else {
            msg.alert("Có lỗi trong quá trình tạo giấy", "Cảnh báo");
            document.getElementById("trWait").style.display = "none";
        }
    };

    afteronCreatePaperPdf = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result[0] == "1") {
            document.getElementById("labelWait").value = "Hệ thống đang thực hiện tạo và xuất bản công bố, vui lòng chờ! ";
            var fileId = dijit.byId("approveForm.fileId").getValue();
            document.location = "exportWord!onExportPaperSignDownload.do?fileId=" + fileId;
            onApprovePdf();
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

    page.signAfter = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result[0] == "1") {
            signType = "PDHS";
            page.showContent();
        } else {
            alert(result[1]);
            document.getElementById("trWait").style.display = "none";
            page.clearContent();
            //dijit.byId("approveDlg").hide();
            document.getElementById("approveForm.statusAccept").checked = false;
            document.getElementById("approveForm.statusDeny").checked = false;
            document.getElementById("approveForm.statusCT").checked = false;
            //page.search();
        }

    };

    page.showContent = function () {
        document.getElementById('showContent').innerHTML = page.getHtml();
        document.getElementById("showContent").style.display = "";
    };

    page.clearContent = function () {
        document.getElementById('showContent').innerHTML = '';
        document.getElementById("showContent").style.display = "none";
    };

    page.getHtml = function () {
        var fileId = dijit.byId("approveForm.fileId").getValue();
        var item = '<applet code="com.viettel.QLLLTP.ca.applet.DataSignApplet" archive="PdfSign.jar"  width="420" height="180" >';
        item += '<PARAM name="userId" value="' + ${userId} + '">';
        item += '<PARAM name="fileId" value="' + fileId + '">';
        item += '<PARAM name="roleType" value="LDC">';
        item += '<PARAM name="signType" value="' + signType + '">';
        item += '<PARAM name="passwordCa" value="' + passwordCa + '">';
        item += '</applet>';
        return item;
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
</script>