<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div class="buttonDiv" id="buttonDiv">
    <%--<sx:ButtonBack onclick="backPage();"/>
    <sd:Button id="btnReceived" key="" onclick="page.showReceivedDlg();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="${contextPath}/share/images/icons/Answer.png" height="14" width="18">
        <span style="font-size:12px">Tiếp nhận hồ sơ</span>
    </sd:Button>
    --%>
    <sd:Button id="btnRejectReceived" key="" onclick="page.rejectReceived();" cssStyle="display:none" cssClass="buttonGroup"> 
        <img src="${contextPath}/share/images/icons/unapproved.png" height="14" width="18">
        <span style="font-size:12px">Từ chối hồ sơ</span>
    </sd:Button>
    <sd:Button key="" onclick="page.downloadFileDetails();" >
        <img src="${contextPath}/share/images/icons/Answer.png" height="14" width="18">
        <span style="font-size:12px">Xuất hồ sơ</span>
    </sd:Button>
    <sd:Button id="btnViewFLow" key="" onclick="page.viewFlow();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Luồng hồ sơ"/>
        <span style="font-size:12px">Luồng hồ sơ</span>
    </sd:Button>
    <sd:Button id="btnCommnetEvaluation" key="" onclick="page.showComment();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="share/images/icons/chat.png" height="14" width="14" alt="Ý kiến thẩm định"/>
        <span style="font-size:12px">Ý kiến thẩm định</span>
    </sd:Button>
    <%--
    <sd:Button id="btnEvaluationDetails" key="" onclick="page.showEvaluateDetailsForm();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="share/images/icons/export.jpg" height="14" width="14" alt="Kết luận thẩm định"/>
        <span style="font-size:12px">Kết luận thẩm định</span>
    </sd:Button>
    --%>
    <sd:Button id="btnAnnouncementReceiptPaper" key="" onclick="page.showAnnouncementReceiptPaperDlg();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="share/images/icons/document_management.png" height="14" width="14" alt="Chi tiết giấy tiếp nhận hồ sơ"/>
        <span style="font-size:12px">Giấy tiếp nhận hồ sơ</span>
    </sd:Button>
</div>
<sx:ResultMessage id="resultMessage"/>
<form id="createForm" name="createForm">
    <sd:TextBox key="" id="createForm.fileId" name="createForm.fileId" cssStyle="display:none" />
    <sd:TextBox key="" id="createForm.fileType" name="createForm.fileType" cssStyle="display:none" />
    <sd:TextBox key="" id="createForm.status" name="createForm.status" cssStyle="display:none" />
    <sd:TextBox key="" id="createForm.viewType" name="createForm.viewType" cssStyle="display:none" />
    <sd:TextBox key="" id="createForm.isFee" name="createForm.isFee" cssStyle="display:none" />
    <sd:Textarea key="" id="createForm.clericalRequest" name="createForm.clericalRequest" cssStyle="display:none" />
    <sd:TextBox id="createForm.processDeptName" name="createForm.processDeptName" cssStyle="display:none" key=""/>
    <sd:TextBox id="createForm.processDeptId" name="createForm.processDeptId" cssStyle="display:none" key=""/>
    <div>
        <sd:TitlePane key="Đơn đề nghị cấp lại" id="tab.reannoucement">
            <div id="tabReIssueFormDiv" style="overflow: auto;">
                <jsp:include page="../fileViewTab/tabReConfirmAnnouncement.jsp"/>
            </div>
        </sd:TitlePane> 
        <sd:ContentPane key="Bản công bố" id="tab.annoucement">
            <div id="tabAnnouncementDiv" style="overflow: auto;">
                <jsp:include page="../fileViewTab/tabAnnouncement.jsp"/>
            </div>
        </sd:ContentPane>
        <sd:TitlePane key="Tài liệu đính kèm" id="tab.attachs">
            <div id="tabAttachDiv" style="overflow: auto;">
                <jsp:include page="../fileViewTab/tabAttachs.jsp"/>
            </div>
        </sd:TitlePane>    

    </div>
</form>     

<sd:Dialog  id="processCommentDlg" height="auto" width="600px"
            key="Ý kiến thẩm định hồ sơ" showFullscreenButton="false"
            >
    <div id="processCommentDiv">
        <jsp:include page="filesCommentDlg.jsp" flush="false"/>
    </div>
</sd:Dialog>    
<sd:Dialog  id="announcementReceiptPaperSignDlg" height="auto" width="800px"
            key="Thông tin giấy tiếp nhận hồ sơ" showFullscreenButton="false"
            >
    <div id="announcementReceiptPaperSignDiv">
        <jsp:include page="../provide/tabViewProvide/announcementReceiptPaperSignDlg.jsp" flush="false"/>
    </div>
</sd:Dialog>
<sd:Dialog id="lookupProcessDlg" key="Xem luồng xử lý hồ sơ" width="70%">
    <div style="overflow: auto; max-height: 500px">
        <jsp:include page="../lookup/lookupProcessDlg.jsp" />
    </div>
</sd:Dialog>
<%--<sd:Dialog  id="evaluateViewDetailsDlg" height="auto" width="800px"
            key="Chi tiết kết quả thẩm định" showFullscreenButton="false"
            >
    <jsp:include page="../evaluation/reviewFormViewDetails.jsp" flush="false"></jsp:include>
</sd:Dialog>--%>
<sd:Dialog  id="rejectReceivedDlg" height="auto" width="800px"
            key="Từ chối tiếp nhận hồ sơ" showFullscreenButton="false"
            >
    <jsp:include page="../received/rejectReceivedDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>
<%--
<sd:Dialog  id="receivedDlg" height="auto" width="800px"
            key="Tiếp nhận hồ sơ" showFullscreenButton="false"
            >
    <jsp:include page="../received/receivedDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>
--%>

<script>
    page.downloadFileDetails = function () {
        var fileId = dijit.byId("createForm.fileId").getValue();
        document.location = "exportWord!onExportFileDetails.do?fileId=" + fileId;
    };
    page.showReceivedButton = function () {
        var status = dijit.byId("createForm.status").getValue();
        var viewType = dijit.byId("createForm.viewType").getValue();
        if (status == 1 && viewType == 1) {
            dijit.byId("btnReceived").domNode.style.display = "";
            //dijit.byId("btnRejectReceived").domNode.style.display = "";
        }
    };
    page.showResultEvaluationButton = function () {//hiển thị kết quả thẩm định
        var status = dijit.byId("createForm.status").getValue();
        if (status != 3 && status != 2 && status != 0 && status != 1) {
            dijit.byId("btnEvaluationDetails").domNode.style.display = "";
        }
    };
    page.showBtnViewFLow = function () {//hien thi btn xem luong ho so
        var status = dijit.byId("createForm.status").getValue();
        if (status >= 0) {
            dijit.byId("btnViewFLow").domNode.style.display = "";
        }
    };
    page.showAnnouncementReceiptPaperButton = function () {//kiem tra ton tai giay cong bo
        var status = dijit.byId("createForm.status").getValue();
        if (status == 6 || status == 14 || status == 15) {
            var fileId = dijit.byId("createForm.fileId").getValue();
            sd.connector.post("filesAction!oncheckAnnouncementReceiptPaper.do?createForm.fileId=" + fileId, null, null, null, page.aftershowAnnouncementReceiptPaperButton);
        }
    };
    page.aftershowAnnouncementReceiptPaperButton = function (data) {//sau khi kiem tra ton tai cong bo
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result0 = result[0];
        if (result0 == "3") {
            dijit.byId("btnAnnouncementReceiptPaper").domNode.style.display = "none";
        } else {
            dijit.byId("btnAnnouncementReceiptPaper").domNode.style.display = "";
        }
    };

    page.showCommentEvalutionButton = function () {//hiển thị kết quả thẩm định
        var status = dijit.byId("createForm.status").getValue();
        if (status != 0 && status != 1) {
            dijit.byId("btnCommnetEvaluation").domNode.style.display = "";
        }

    };

    page.showAnnouncementReceiptPaperDlg = function () {//show form giấy tiếp nhận công bố hợp quy
        var fileId = dijit.byId("createForm.fileId").getValue();
        sd.connector.post("filesAction!loadSignPage.do?signForm.fileId=" + fileId, "signForm", null, null, afterLoadPaperReceipt);
    };

    afterLoadPaperReceipt = function (data) {//sau khi load giay tiep nhan cong bo
        dijit.byId("announcementReceiptPaperSignDlg").show();
    };

    page.viewFlow = function () {//xem luong
        var lookupProcessDlg = dijit.byId("lookupProcessDlg");
        var fileId = dijit.byId("createForm.fileId").getValue();
        lookupProcessDlg.show();
        page.getProcess(fileId);
    };

    page.showEvaluateDetailsForm = function () {//hien thi y kien tham dinh ho so
        var fileId = dijit.byId("createForm.fileId").getValue();
        sd.connector.post("filesAction!getEvaluationRecordsDetails.do?createForm.fileId=" + fileId, null, null, null, afterLoadEvaluateDetailsForm);
    };
    afterLoadEvaluateDetailsForm = function (data) {
        var obj = dojo.fromJson(data);
        //var result = obj.items;
        var customInfo = obj.customInfo;
        document.getElementById("evaluationRecordsForm.legal").innerHTML = escapeHtml_(returnStatus(customInfo.legal));
        document.getElementById("evaluationRecordsForm.legalContent").innerHTML = escapeHtml_(customInfo.legalContent);
        document.getElementById("evaluationRecordsForm.foodSafetyQuality").innerHTML = escapeHtml_(returnStatus(customInfo.foodSafetyQuality));
        document.getElementById("evaluationRecordsForm.foodSafetyQualityContent").innerHTML = escapeHtml_(customInfo.foodSafetyQualityContent);
        document.getElementById("evaluationRecordsForm.effectUtility").innerHTML = escapeHtml_(returnStatus(customInfo.effectUtility));
        document.getElementById("evaluationRecordsForm.effectUtilityContent").innerHTML = escapeHtml_(customInfo.effectUtilityContent);
        dijit.byId("evaluateViewDetailsDlg").show();
    };
    returnStatus = function (status) {
        var strStatus = "";
        switch (status) {
            case -1:
                strStatus = "Bổ sung";
                break;
            case 1:
                strStatus = "Đồng ý";
                break;
            case 0:
                strStatus = "Không đồng ý";
                break;
        }
        ;
        return strStatus;
    };

    page.showAnnouncementReceiptPaperButton();
    //page.showResultEvaluationButton();
    page.showCommentEvalutionButton();
    page.showBtnViewFLow();
    //page.showReceivedButton();
</script>  