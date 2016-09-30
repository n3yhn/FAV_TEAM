<%-- 
    Document   : xemxetthongbaosdbs
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

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
                <sd:Button id="btnSendVTFRF1" key="" onclick="loginCa();" cssStyle="display:" cssClass="buttonGroup">
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
<div id="showContentLdpNew" style="visibility: hidden;height: 0px !important;" > 
</div>
<sd:Dialog  id="loginCADlg" height="auto" width="500px"
            key="Ban Cơ yếu" showFullscreenButton="false"
            >
    <jsp:include page="loginCADlg.jsp" flush="false"></jsp:include>
</sd:Dialog>
<script type="text/javascript">
    var passwordCa;

    onCloseDlg = function() {
        doGoToMenu("filesAction!toAdditionReviewPage.do");
    };
    afterFeedbackReviewActionNew = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        //alert(result[1]);
        if (result[0] == "1") {
            if (signIndex == itemsToSign.length - 1)
            {
                //msg.alert('Ký số thành công', 'Thông báo');
                check = true;
                //msg.alert('Ký số thành công', 'Thông báo');
                document.getElementById("trWait").style.display = "none";
                document.getElementById("divSignProcess").style.display = "none";
                page.clearContentLdpNew();
                //onCloseFeedbackReviewFormNew();
                //backPage();
                onCloseDlg();
                page.search();
            }
            else
            {
                check = false;
                dijit.byId("feedbackReviewFormDlgNew").hide();
                signIndex++;
                page.signMoreFiles();
            }
        }
        else
        {
            page.clearContentLdpNew();
            check = true;
        }
        //onCloseFeedbackReviewFormNew();
    };

    onFeedbackReviewAction = function() {
        if (page.validateFeedbackReviewForm()) {
            sd.connector.post("filesAction!onFeedbackReview.do?" + token.getTokenParamString(), null, "feedbackReviewFormNew", null, afterFeedbackReviewActionNew);
        }
    };
    page.validateFeedbackReviewForm = function() {
        document.getElementById("feedbackReviewFormNew.sendVt").checked = false;
        document.getElementById("feedbackReviewFormNew.statusDeny").checked = true;
//        if (document.getElementById("ckbIsTypeChangeL").checked == true) {
//            dijit.byId("feedbackReviewFormNew.isTypeChange").setValue(1);
//        } else {
//            dijit.byId("feedbackReviewFormNew.isTypeChange").setValue(0);
//        }
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

    loginCa = function()
    {
        var loginPas = '${passwordCa}';
        if (loginPas == null || loginPas.toString() == "null" || loginPas.toString() == "")
        {
            passwordCa = "";
            dijit.byId("loginCADlg").show();
        }
        else
        {
            passwordCa = loginPas;
            onSendFRFtoVT();
        }
    };
    var leaderStaffRequestNew;
    onSendFRFtoVT = function() {
        if (page.validateOnSendFRFtoVT_FRFN()) {
            var fileId = dijit.byId("feedbackReviewFormNew.fileId").getValue();
            // document.getElementById("trWait").style.display = "";
            //document.getElementById("labelWait").innerHTML = "Hệ thống đang tạo công văn và ký số, vui lòng chờ  ";
            document.getElementById('trWait').innerHTML = "Hệ thống đang thực hiện phê duyệt:" + (signIndex + 1) + "/" + itemsToSign.length + " hồ sơ  ";
            document.getElementById("divSignProcess").style.display = "";
            //document.getElementById("trWaitViewFile").style.display = 'none';
            var leaderStaffRequest = dijit.byId("feedbackReviewFormNew.leaderStaffRequest").getValue();
            if (check == true)
            {
//                alert(leaderStaffRequest);
                //leaderStaffRequestNew = leaderStaffRequest;
                var content = page.utf8_to_b64FBAF(leaderStaffRequest);
                content = content.replaceAllFBAF('+', '_');
                sd.connector.post("filesAction!onExportCvSdbsSign.do?fileId=" + fileId + "&content=" + content, null, null, null, onFeedbackReviewActionSign);
            }
            else
            {
                //var content = page.utf8_to_b64FBAF(leaderStaffRequestNew);
                //hieptq update 040515
//                 alert(leaderStaffRequest);
                var content = page.utf8_to_b64FBAF(leaderStaffRequest);
                content = content.replaceAllFBAF('+', '_');
                sd.connector.post("filesAction!onExportCvSdbsSign.do?fileId=" + fileId + "&content=" + content, null, null, null, onFeedbackReviewActionSign);
            }

        }
    };

    onFeedbackReviewActionSign = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result[0] == "1") {
            document.getElementById("trWait").style.display = "";
            page.showContentLdpNew();
        } else {
            msg.alert("Có lỗi trong quá trình xuất công văn SĐBS", "Cảnh báo");
            document.getElementById("trWait").style.display = "none";
        }
    };


    page.showContentLdpNew = function() {
        document.getElementById('showContentLdpNew').innerHTML = page.getHtmlLdp();
        document.getElementById("showContentLdpNew").style.display = "";
    };

    page.getHtmlLdp = function()
    {
        var fileId = dijit.byId("feedbackReviewFormNew.fileId").getValue();
        var item = '<applet code="com.viettel.QLLLTP.ca.applet.DataSignApplet" archive="PdfSign.jar"  width="420" height="180" >';
        item += '<PARAM name="userId" value="' + ${userId} + '">';
        item += '<PARAM name="fileId" value="' + fileId + '">';
        item += '<PARAM name="roleType" value="LDP">';
        item += '<PARAM name="signType" value="CVBS">';
        item += '<PARAM name="passwordCa" value="' + passwordCa + '">';
        item += '</applet>';
        return item;
    };

    onApproveLdp = function() {
        page.clearContentLdpNew();
        sd.connector.post("filesAction!onFeedbackReviewSendVt.do?" + token.getTokenParamString(), null, "feedbackReviewFormNew", null, afterFeedbackReviewActionNew);
    };

    page.clearContentLdpNew = function() {
        document.getElementById('showContentLdpNew').innerHTML = '';
        document.getElementById("showContentLdpNew").style.display = "none";
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

    page.validateOnSendFRFtoVT_FRFN = function() {
        document.getElementById("feedbackReviewFormNew.sendVt").checked = true;
        document.getElementById("feedbackReviewFormNew.statusDeny").checked = false;
//        if (document.getElementById("ckbIsTypeChangeL").checked == true) {
//            dijit.byId("feedbackReviewFormNew.isTypeChange").setValue(1);
//        } else {
//            dijit.byId("feedbackReviewFormNew.isTypeChange").setValue(0);
//        }
        var leaderStaffRequest = dijit.byId("feedbackReviewFormNew.leaderStaffRequest").getValue();
        if (leaderStaffRequest.trim().length == 0) {
            alert("Bạn chưa nhập [Nội dung]");
            return false;
        }
        return true;
    };
    onCloseFeedbackReviewFormNew = function() {
        dijit.byId("feedbackReviewFormDlgNew").hide();
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

</script>