<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="approve_CA_js.jsp" />
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div class="buttonDiv" id="buttonDiv">
    <%--<sx:ButtonBack onclick="backPage();"/> --%>   
    <sd:Button id="btnFeedbackReviewForm" key="" onclick="page.showFeedbackReviewForm();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Chức năng Lãnh đạo phòng xem xét công văn SĐBS"/>
        <span style="font-size:12px">Lãnh đạo xem xét công văn SĐBS</span>
    </sd:Button>
    <sd:Button id="btnFeedbackEvaluateForm" key="" onclick="page.showFeedbackEvaluateForm();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Chức năng Chuyên viên tạo dự thảo công văn SĐBS"/>
        <span style="font-size:12px">Chuyên viên dự thảo công văn SĐBS</span>
    </sd:Button>
    <sd:Button id="btnReturnFiles" key="" onclick="page.showReturnFilesDlg();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Chức năng trả hồ sơ cho doanhn nghiệp"/>
        <span style="font-size:12px">Trả hồ sơ</span>
    </sd:Button>
    <%--
    <sd:Button id="btnReviewFiles" key="" onclick="page.showReviewForm();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="share/images/edit.png" height="14" width="14" alt="Kết luận thẩm định"/>
        <span style="font-size:12px">Xem xét hồ sơ</span>
    </sd:Button>
    
    <sd:Button id="btnEvaluateFiles" key="" onclick="page.getCommentEvaluateForm();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="share/images/edit.png" height="14" width="14" alt="Kết luận thẩm định"/>
        <span style="font-size:12px">Chuyên viên kết luận thẩm định hồ sơ</span>
    </sd:Button>
    --%>
    <sd:Button id="btnEvaluateLeaderFiles" key="" onclick="page.getCommentEvaluateLeaderForm();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="share/images/edit.png" height="14" width="14" alt="Kết luận thẩm định"/>
        <span style="font-size:12px">Lãnh đạo kết luận thẩm định</span>
    </sd:Button>
    <sd:Button id="btnCommnetEvaluation" key="" onclick="page.showComment();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="share/images/icons/chat.png" height="14" width="14" alt="Ý kiến thẩm định"/>
        <span style="font-size:12px">Trao đổi Ý kiến thẩm định</span>
    </sd:Button>
    <%--
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
    <sd:Button id="btnCheckCA" key="" onclick=" page.checkCAValidNew();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Luồng hồ sơ"/>
        <span style="font-size:12px">Đối chiếu hồ sơ</span>
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
    <sd:Button id="btnComparison" key="" onclick="page.showViewComparisonForm();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="share/images/icons/compare.png" height="14" width="14" alt="Chức năng xem kết quả đối chiếu hồ sơ gốc"/>
        <span style="font-size:12px">Kết quả đối chiếu</span>
    </sd:Button>    
    <sd:Button id="btnOldVersion" key="" onclick="page.showOldVersionFilesDlg();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="${contextPath}/share/images/icons/old-versions1.png" height="14" width="18">
        <span style="font-size:12px"><sd:Property>btn.viewOldVersion</sd:Property></span>
    </sd:Button>
    <sd:Button id="downloadPdf" key="" onclick="page.downloadFileSignNew();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="${contextPath}/share/images/icons/kdevelop_down.png" height="14" width="18">
        <span style="font-size:12px"><sd:Property>Xem bản công bố doanh nghiệp đã tải lên</sd:Property></span>
    </sd:Button>
    <!-- check thong tu 30 - hieptq -->
    <sd:Button id="is30" key="" onclick="page.checkIs30();" cssStyle="display:none;" cssClass="buttonGroup">
        <img src="${contextPath}/share/images/icons/confirm_32.png" height="14" width="18">
        <span style="font-size:12px"><sd:Property>Theo thông tư 30</sd:Property></span>
    </sd:Button>
    <!-- bo check thong tu 30 hieptq -->
    <sd:Button id="unCheck30" key="" onclick="page.UnCheckIs30();" cssStyle="display:none;" cssClass="buttonGroup">
        <img src="${contextPath}/share/images/icons/confirm_32.png" height="14" width="18">
        <span style="font-size:12px"><sd:Property>Bỏ chọn theo thông tư 30</sd:Property></span>
    </sd:Button> 
    <!-- checkbox -->
    <span id="30" style="display:none">
        <sd:CheckBox id="check30" name="check30" 
                     checked="true" key="" value="0"
                     readonly="true"></sd:CheckBox><label>Thông tư 30</label>  
        </span>
        <!-- end check Thong tu 30 -->
        <img id="imgAlertWarning" src='share/images/icons/warning.png' width='17px' height='17px' title='' style="display:none;" />
        <img id="imgAlertError" src='share/images/icons/error.png' width='17px' height='17px' title='' style="display:none;" />
    </div>

    <div id="approveCAValid" style="display:none;">
        <table width="100%" cellspacing="5" cellpadding="0" border="0" class="viewTable">
            <tr>
                <td style="padding-right: 2em;text-align: left;"> 
                    <image src='${fn:escapeXml(contextPath)}/share/images/icons/certificate.png'/><span style="color: blue;font-style: italic;">Văn bản đã được ký CA! Xác minh hợp lệ!</span>
            </td>
        </tr>
    </table>
</div>
<div id="approveCAInValid" style="display:none;">
    <table width="100%" cellspacing="5" cellpadding="0" border="0" class="viewTable">
        <tr>
            <td style="padding-right: 2em;text-align: center">               
                <image src='${fn:escapeXml(contextPath)}/share/images/icons/uncertificate.png'/>
            </td>
            <td>
                <span style="color: red;font-style: italic">Văn bản đã được ký CA. Xác minh không hợp lệ!</span>
            </td>
        </tr>
    </table>
</div>
<sx:ResultMessage id="resultMessage"/>
<form id="createForm" name="createForm">
    <table width="100%" cellspacing="5" cellpadding="0" border="0" class="viewTable">
        <tr style="display: none">
            <td style="padding-right: 2em;text-align: center"> 
                <sd:TextBox key="" id="createForm.fileId" name="createForm.fileId" cssStyle="display:none" />
                <sd:TextBox key="" id="createForm.fileType" name="createForm.fileType" cssStyle="display:none" />
                <sd:TextBox key="" id="createForm.status" name="createForm.status" cssStyle="display:none" />
                <sd:TextBox key="" id="createForm.viewType" name="createForm.viewType" cssStyle="display:none" />
                <sd:TextBox key="" id="createForm.isFee" name="createForm.isFee" cssStyle="display:none" />
                <sd:Textarea key="" id="createForm.clericalRequest" name="createForm.clericalRequest" cssStyle="display:none" />
                <sd:TextBox key="" id="createForm.contentSigned" name="createForm.contentSigned" cssStyle="display:none"/>
                <sd:TextBox key="" id="createForm.userSigned" name="createForm.userSigned" cssStyle="display:none"/>
                <sd:TextBox id="createForm.contentXml" key="" name="createForm.contentXml" cssStyle="display:none"/>
                <sd:TextBox name="validCAStatus" key="" cssStyle="display:none;" id="validCAStatus" value="${fn:escapeXml(validCAStatus)}"/>                             
                <sd:Textarea key="" id="createForm.comparisonContent" name="createForm.comparisonContent" cssStyle="display:none" />
                <sd:DatePicker id="createForm.deadlineReceived" key="" name="createForm.deadlineReceived" cssStyle="display:none;"/>        
                <sd:DatePicker id="createForm.deadlineComment" key="" name="createForm.deadlineComment" cssStyle="display:none;"/>        
                <sd:DatePicker id="createForm.deadlineAddition" key="" name="createForm.deadlineAddition" cssStyle="display:none;"/>        
                <sd:DatePicker id="createForm.deadlineApprove" key="" name="createForm.deadlineApprove" cssStyle="display:none;"/>   
                <sd:TextBox key="" id="createForm.haveTemp" name="createForm.haveTemp" cssStyle="display:none" />                
                <sd:TextBox key="" id="createForm.staffProcess" name="createForm.staffProcess" cssStyle="display:none"/>  
                <sd:TextBox key="" id="createForm.leaderAssignId" name="createForm.leaderAssignId" cssStyle="display:none"/>
                <sd:TextBox key="" id="createForm.leaderReviewId" name="createForm.leaderReviewId" cssStyle="display:none"/>
                <sd:TextBox key="" id="createForm.staffRequest" name="createForm.staffRequest" cssStyle="display:none"/>
                <sd:TextBox key="" id="createForm.detailProduct.productType" name="createForm.detailProduct.productType" cssStyle="display:none"/>
                <sd:TextBox key="" id="createForm.leaderEvaluateId" name="createForm.leaderEvaluateId" cssStyle="display:none"/>
                <sd:TextBox key="" id="createForm.isTypeChange" name="createForm.isTypeChange" cssStyle="display:none"/>                
                <sd:TextBox key="" id="createForm.is30" name="createForm.is30" cssStyle="display:none"/>  
                <sd:TextBox id="createForm.processDeptName" name="createForm.processDeptName" cssStyle="display:none" key=""/>
                <sd:TextBox id="createForm.processDeptId" name="createForm.processDeptId" cssStyle="display:none" key=""/>
            </td>
        </tr>
    </table>
    <div>        
        <sd:TitlePane key="Bản công bố" id="tab.annoucement">
            <div id="tabAnnouncementDiv" style="overflow: auto;">
                <jsp:include page="../fileViewTab/tabAnnouncementForConfirm.jsp"/>
            </div>
        </sd:TitlePane>   
        <br/>
        <sd:TitlePane key="Thông tin chi tiết sản phẩm" id="tab.productDetail">
            <div id="tabProductDetailDiv" style="overflow: auto;">
                <jsp:include page="../fileViewTab/tabProductDetailTL.jsp"/>
            </div>
        </sd:TitlePane>  
        <br/>
        <sd:TitlePane key="Tài liệu đính kèm" id="tab.attachs">
            <div id="tabAttachDiv" style="overflow: auto;">
                <jsp:include page="../fileViewTab/tabAttachs.jsp"/>
            </div>
        </sd:TitlePane>    
    </div>
</form>
<div id="myapplet" style="visibility:hidden">
</div>
<sd:Dialog  id="feedbackReviewFormDlg" height="auto" width="600px"
            key="Xem xét nội dung công văn SĐBS" showFullscreenButton="false"
            >
    <jsp:include page="../evaluation/feedbackEvaluate/feedbackReviewForm.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="feedbackEvaluateFormDlg" height="auto" width="600px"
            key="Thông báo hồ sơ" showFullscreenButton="false"
            >
    <jsp:include page="../evaluation/feedbackEvaluate/feedbackEvaluateForm.jsp" flush="false"></jsp:include>
</sd:Dialog>		
<sd:Dialog  id="selectOldVersionFilesDlg" height="auto" width="500px"
            key="Phiên bản hồ sơ" showFullscreenButton="false"
            >
    <div id="selectOldVersionFilesDlgDiv">
        <jsp:include page="../other/selectOldVersionFilesDlg.jsp" flush="false"/>
    </div>
</sd:Dialog>
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
<sd:Dialog  id="ctkqthamdinhDlg" height="auto" width="800px"
            key="Chi tiết kết quả thẩm định" showFullscreenButton="false"
            >
    <jsp:include page="../evaluation/reviewFormViewDetails.jsp" flush="false"></jsp:include>
</sd:Dialog>
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
<sd:Dialog  id="returnFilesDlg" height="auto" width="600px"
            key="Trả hồ sơ" showFullscreenButton="false"
            >
    <jsp:include page="../received/returnFilesDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="dlgReturn" height="auto" width="500px"
            key="" showFullscreenButton="true"
            >
    <jsp:include page="dlgReturnOrApprove.jsp"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="comparisonViewDetailsDlg" height="auto" width="800px"
            key="Chi tiết kết quả đối chiếu" showFullscreenButton="false"
            >
    <jsp:include page="../comparison/comparisonFormViewDetails.jsp" flush="false"></jsp:include>
</sd:Dialog>
<%--
<sd:Dialog  id="evaluateDlg" height="auto" width="600px"
            key="Kết luận thẩm định" showFullscreenButton="false"
            >
    <jsp:include page="../evaluation/evaluateForm.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="reviewDlg" height="auto" width="600px"
            key="Kết quả xem xét" showFullscreenButton="false"
            >
    <jsp:include page="../evaluation/reviewForm.jsp" flush="false"></jsp:include>
</sd:Dialog>
--%>
<sd:Dialog  id="evaluateLeaderDlg" height="auto" width="600px"
            key="Phó phòng kết luận thẩm định" showFullscreenButton="false"
            >
    <jsp:include page="../evaluation/evaluateFormByLeader.jsp" flush="false"></jsp:include>
</sd:Dialog>

<script>
    page.showReceivedButton = function() {//hien thi nut tiep nhan ho so - voi van thu
        var status = dijit.byId("createForm.status").getValue();
        var viewType = dijit.byId("createForm.viewType").getValue();
        if ((status == 1 || status == 18) && viewType == 1) {
            dijit.byId("btnReceived").domNode.style.display = "";
            //dijit.byId("btnRejectReceived").domNode.style.display = "";
        }
    };
//
    page.showCommentEvalutionButton = function() {//hiển thị kết quả thẩm định
//        var status = dijit.byId("createForm.status").getValue();
        var viewType = dijit.byId("createForm.viewType").getValue();
//         if ((status == 3 || status == 4 || status == 5 || status == 6 || status == 8 || status == 9 || status == 14 || status == 15 || status == 19 || status == 17 || status == 20 || status == 21) && (viewType == 4 || viewType == 2 || viewType == 3)) {
        if (viewType == 4 || viewType == 2 || viewType == 3) {
            dijit.byId("btnCommnetEvaluation").domNode.style.display = "";
        }
    };
//
    page.showResultEvaluationButton = function() {//hiển thị kết quả thẩm định
        var status = dijit.byId("createForm.status").getValue();
        var viewType = dijit.byId("createForm.viewType").getValue();
        if ((status == 7 || status == 4 || status == 5 || status == 6 || status == 8 || status == 9 || status == 14 || status == 15 || status == 19 || status == 17) && (viewType == 4 || viewType == 2 || viewType == 3)) {
            dijit.byId("btnEvaluationDetails").domNode.style.display = "";
        }
    };
//
    page.showComparisonButton = function() {
        var status = dijit.byId("createForm.status").getValue();
        if (status == 15 || status == 16) {
            dijit.byId("btnComparison").domNode.style.display = "";
        }
    };
    page.showBtnCheckCA = function() {
        var userSigned = dijit.byId("createForm.userSigned").getValue();
        if (userSigned != null && userSigned.trim().length > 0) {
            if (userSigned != "fileUploaded") {
                dijit.byId("btnCheckCA").domNode.style.display = "";
            }
        }
    };
    page.downloadFileDetails = function() {
        var fileId = dijit.byId("createForm.fileId").getValue();
        document.location = "exportWord!onExportFileDetails.do?fileId=" + fileId;
    };
    page.showBtnViewFLow = function() {//hien thi btn xem luong ho so
        var status = dijit.byId("createForm.status").getValue();
        if (status >= 0) {
            dijit.byId("btnViewFLow").domNode.style.display = "";
        }
    };
    page.showAnnouncementReceiptPaperButton = function() {//kiem tra ton tai giay cong bo
        var status = dijit.byId("createForm.status").getValue();
        if (status == 6 || status == 14 || status == 15) {
            var fileId = dijit.byId("createForm.fileId").getValue();
            sd.connector.post("filesAction!oncheckAnnouncementReceiptPaper.do?createForm.fileId=" + fileId, null, null, null, page.aftershowAnnouncementReceiptPaperButton);
        }
    };
    page.aftershowAnnouncementReceiptPaperButton = function(data) {//sau khi kiem tra ton tai cong bo
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result0 = result[0];
        if (result0 == "3") {
            dijit.byId("btnAnnouncementReceiptPaper").domNode.style.display = "none";
        } else {
            dijit.byId("btnAnnouncementReceiptPaper").domNode.style.display = "";
        }
    };
    page.showAnnouncementReceiptPaperDlg = function() {//show form giấy tiếp nhận công bố hợp quy
        var fileId = dijit.byId("createForm.fileId").getValue();
        sd.connector.post("filesAction!loadSignPage.do?signForm.fileId=" + fileId, "signForm", null, null, afterLoadPaperReceipt);
    };
    afterLoadPaperReceipt = function(data) {//sau khi load giay tiep nhan cong bo
        dijit.byId("announcementReceiptPaperSignDlg").show();
    };
    page.viewFlow = function() {//xem luong
        var lookupProcessDlg = dijit.byId("lookupProcessDlg");
        var fileId = dijit.byId("createForm.fileId").getValue();
        lookupProcessDlg.show();
        page.getProcess(fileId);
    };
    page.showEvaluateDetailsForm = function() {//hien thi y kien tham dinh ho so
        var fileId = dijit.byId("createForm.fileId").getValue();
        sd.connector.post("filesAction!getEvaluationRecordsDetails.do?createForm.fileId=" + fileId, null, null, null, afterLoadEvaluateDetailsForm);
    };
    afterLoadEvaluateDetailsForm = function(data) {
        var obj = dojo.fromJson(data);
        //var result = obj.items;
        var customInfo = obj.customInfo;
        document.getElementById("ctkqthamdinhDlg.staffRequest").innerHTML = escapeHtml_(customInfo.mainContent);
        document.getElementById("ctkqthamdinhDlg.legal").innerHTML = escapeHtml_(returnStatus(customInfo.legal));
        document.getElementById("ctkqthamdinhDlg.legalContent").innerHTML = escapeHtml_(customInfo.legalContent);
        document.getElementById("ctkqthamdinhDlg.foodSafetyQuality").innerHTML = escapeHtml_(returnStatus(customInfo.foodSafetyQuality));
        document.getElementById("ctkqthamdinhDlg.foodSafetyQualityContent").innerHTML = escapeHtml_(customInfo.foodSafetyQualityContent);
        document.getElementById("ctkqthamdinhDlg.effectUtility").innerHTML = escapeHtml_(returnStatus(customInfo.effectUtility));
        document.getElementById("ctkqthamdinhDlg.effectUtilityContent").innerHTML = escapeHtml_(customInfo.effectUtilityContent);
        page.replaceTblReviewFormViewDetailsEvaluate();
        dijit.byId("ctkqthamdinhDlg").show();
    };
    returnStatus = function(status) {
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
    page.checkCAValid = function(data) {
        if (data == "NG") {
            document.getElementById("approveCAInValid").style.display = "";
            return false;
        } else if (data == "OK") {
            document.getElementById("approveCAValid").style.display = "";
        }
        return true;
    };
    var validCAStatus = dijit.byId("validCAStatus").value;
    page.checkCAValidNew = function() {
        page.checkCAValid(validCAStatus);
    };
    page.showViewComparisonForm = function() {//hien thi ket qua doi chieu ho so
        var fileId = dijit.byId("createForm.fileId").getValue();
        sd.connector.post("filesAction!getComparisonRecordsDetails.do?createForm.fileId=" + fileId, null, null, null, afterLoadViewComparisonForm);
    };
    afterLoadViewComparisonForm = function(data) {
        var obj = dojo.fromJson(data);
        var item = obj.items;
        document.getElementById("filesForm.isComparison").innerHTML = escapeHtml_(returnStatusComparison(item[0]));
        document.getElementById("filesForm.comparisonContent").innerHTML = escapeHtml_(item[1]);
        page.replaceTbleComparisonFormViewDetails();
        dijit.byId("comparisonViewDetailsDlg").show();
    };
    returnStatusComparison = function(status) {
        var strStatus = "";
        switch (status) {
            case 1:
                strStatus = "Hồ sơ hợp lệ";
                break;
            case 0:
                strStatus = "Hồ sơ không hợp lệ";
                break;
        }
        return strStatus;
    };
    page.checkTimeProcessFile = function() {
        var viewType = dijit.byId("createForm.viewType").getValue();
        if (viewType == 0) {
            var status = parseInt(dijit.byId("createForm.status").getValue());
            var alertContent = "";
            var isFee = parseInt(dijit.byId("createForm.isFee").getValue());
            var flag = 0;
            if (isFee != 1 && (status == 15 || status == 6)) {
                alertContent += "- Chưa đóng phí hồ sơ đầy đủ\x0A";
            }
            var today = '${sysDate}';
            switch (status) {
                case 1:
                    var deadlineReceived = Date.parse(dijit.byId("createForm.deadlineReceived").getValue());
                    if (deadlineReceived < today) {
                        alertContent += "- Quá hạn tiếp nhận\x0A";
                        flag = 1;
                    } else {
                        if (deadlineReceived == today) {
                            alertContent += "- Sắp quá hạn tiếp nhận\x0A";
                        } else {
                        }
                    }
                    break;
                case 20:
                    var deadlineAddition = Date.parse(dijit.byId("createForm.deadlineAddition").getValue());
                    if (deadlineAddition < today) {
                        alertContent += "- Quá hạn Sửa đổi bổ sung\x0A";
                        flag = 1;
                    } else {
                        if (deadlineAddition == today) {
                            alertContent += "- Sắp quá hạn nộp sửa đổi bổ sung\x0A";
                        } else {
                        }
                    }
                    break;
                case 6:
                    var deadlineApprove = Date.parse(dijit.byId("createForm.deadlineApprove").getValue());
                    if (deadlineApprove < today) {
                        alertContent += "- Quá hạn Phê duyệt hồ sơ\x0A";
                        flag = 1;
                    } else {
                        if (deadlineApprove == today) {
                            alertContent += "- Sắp quá hạn phê duyệt hồ sơ\x0A";
                        } else {
                        }
                    }
                    break;
                default:
            }
            if (alertContent.trim().length > 0) {
                var imgAlertWarning = document.getElementById('imgAlertWarning');
                var imgAlertError = document.getElementById('imgAlertError');
                if (flag == 1) {
                    imgAlertError.setAttribute('title', alertContent);
                    imgAlertError.setAttribute('style', 'display');
                } else {
                    imgAlertWarning.setAttribute('title', alertContent);
                    imgAlertWarning.setAttribute('style', 'display');
                }
            }
        }
    };
    page.showReturnFilesButton = function() {
        var status = dijit.byId("createForm.status").getValue();
        var viewType = dijit.byId("createForm.viewType").getValue();
        if (status == 15 && viewType == 1) {
            dijit.byId("btnReturnFiles").domNode.style.display = "";
        }
    };
    page.showBtnOldVersion = function() {
        //binhnt 141125
        var viewType = dijit.byId("createForm.viewType").getValue();
        if (viewType != 0) {
            dijit.byId("btnOldVersion").domNode.style.display = "";
        }
        //!binhnt 141125
//        var haveTemp = document.getElementById("createForm.haveTemp");
//        if (haveTemp != null) {
//            var haveTempValue = dijit.byId("createForm.haveTemp").getValue();
//            if (haveTempValue != null && haveTempValue > 0) {
//                dijit.byId("btnOldVersion").domNode.style.display = "";
//            }
//        }
    };
    page.downloadFileSignNew = function() {
        var fileId = dijit.byId("createForm.fileId").getValue();
        document.location = "uploadiframe!openFileUserUpload.do?fileId=" + fileId;
    };
    page.checkButtonFileSign = function() {
        var userSigned = dijit.byId("createForm.userSigned").getValue();
        if (userSigned != null && userSigned.trim().length > 0) {
            if (userSigned == "fileUploaded") {
                dijit.byId("downloadPdf").domNode.style.display = "";
            }
        }
    };
    //140916
    page.showBtnEvaluateFiles = function() {
        var status = dijit.byId("createForm.status").getValue();
        var staffProcess = dijit.byId("createForm.staffProcess").getValue();
        var viewType = dijit.byId("createForm.viewType").getValue();
        if ((status == 3 || status == 8 || status == 17) && (viewType == 4) && staffProcess == '${userId}') {
            dijit.byId("btnEvaluateFiles").domNode.style.display = "";
        }
    };
    page.showBtnReviewFiles = function() {
        var status = dijit.byId("createForm.status").getValue();
        var leaderReviewId = dijit.byId("createForm.leaderReviewId").getValue();
        var viewType = dijit.byId("createForm.viewType").getValue();
        if ((status == 4 || status == 7 || status == 9) && (viewType == 3) && leaderReviewId == '${userId}') {
            dijit.byId("btnReviewFiles").domNode.style.display = "";
        }
    };
    page.showEvaluateLeaderFiles = function() {
        var status = dijit.byId("createForm.status").getValue();
        var leaderEvaluateId = dijit.byId("createForm.leaderEvaluateId").getValue();
        var viewType = dijit.byId("createForm.viewType").getValue();
        if ((status == 4 || status == 7) && (viewType == 3) && leaderEvaluateId == '${userId}') {
            dijit.byId("btnEvaluateLeaderFiles").domNode.style.display = "";
        }
    };
    page.getCommentEvaluateLeaderForm = function() {
        var fileId = dijit.byId("createForm.fileId").getValue();
        var fileType = dijit.byId("createForm.fileType").getValue();
        dijit.byId("evaluateFormByLeader.fileId").setValue(fileId);
        if (fileType != 66750) {//error
            var panel = document.getElementById("effectiveDateDiv");
            panel.setAttribute("style", "display:;");
        }
        sd.connector.post("filesAction!getCommentEvaluateFormByLeader.do?objectId=" + fileId, null, null, null, afterCommentEvaluateLeaderForm);
    };
    afterCommentEvaluateLeaderForm = function(data) {

        var obj = dojo.fromJson(data);
        if (obj.customInfo[0] != "") {
            document.getElementById("evaluationRecordsFormByLeader.legalContent").value = obj.customInfo[0];
        } else {
            document.getElementById("evaluationRecordsFormByLeader.legalContent").value = "";
        }
        if (obj.customInfo[1] != "") {
            document.getElementById("evaluationRecordsFormByLeader.foodSafetyQualityContent").value = obj.customInfo[1];
        } else {
            document.getElementById("evaluationRecordsFormByLeader.foodSafetyQualityContent").value = "";
        }
        if (obj.customInfo[2] != "") {
            document.getElementById("evaluationRecordsFormByLeader.effectUtilityContent").value = obj.customInfo[2];
        } else {
            document.getElementById("evaluationRecordsFormByLeader.effectUtilityContent").value = "";
        }
        if (obj.customInfo[3] != "") {
            document.getElementById("evaluateFormByLeader.staffRequest").value = obj.customInfo[3];
        } else {
            document.getElementById("evaluateFormByLeader.staffRequest").value = "";
        }
        if (obj.customInfo[4] != -1) {
            dijit.byId("evaluateFormByLeader.effectiveDate").setValue(obj.customInfo[4]);
        } else {
            dijit.byId("evaluateFormByLeader.effectiveDate").setValue(-1);
        }

        if (obj.customInfo[5] != -1) {
            dijit.byId("evaluationRecordsFormByLeader.legal").setValue(obj.customInfo[5]);
        } else {
            dijit.byId("evaluationRecordsFormByLeader.legal").setValue(1);
        }

        if (obj.customInfo[6] != -1) {
            dijit.byId("evaluationRecordsFormByLeader.foodSafetyQuality").setValue(obj.customInfo[6]);
        } else {
            dijit.byId("evaluationRecordsFormByLeader.foodSafetyQuality").setValue(1);
        }

        if (obj.customInfo[7] != -1) {
            dijit.byId("evaluationRecordsFormByLeader.effectUtility").setValue(obj.customInfo[7]);
        } else {
            dijit.byId("evaluationRecordsFormByLeader.effectUtility").setValue(1);
        }

        dijit.byId("evaluateFormByLeader.ProductType").setValue(0);
        //var leaderAssignId = dijit.byId("createForm.leaderAssignId").getValue();
        //dijit.byId("evaluationRecordsFormByLeader.leaderReviewId").setValue(leaderAssignId);
        var status = dijit.byId("createForm.status").getValue();
        if (status == 4) {
            document.getElementById("evaluateFormByLeader.statusAccept").checked = true;
        } else if (status == 7) {
            document.getElementById("evaluateFormByLeader.statusDeny").checked = true;
        }
        var status = dijit.byId("createForm.status").getValue();
        var statusName = getStatusStaffEvaluate(parseInt(status));
        document.getElementById("evaluateFormByLeader.statusL").innerHTML = statusName;

        dijit.byId("evaluateLeaderDlg").show();
    };
    page.getCommentEvaluateForm = function() {
        var fileId = dijit.byId("createForm.fileId").getValue();
        var fileType = dijit.byId("createForm.fileType").getValue();
        var productType = dijit.byId("createForm.detailProduct.productType").getValue();
        dijit.byId("evaluateForm.fileId").setValue(fileId);
        if (fileType != 66750) {//error
            var panel = document.getElementById("effectiveDateDiv");
            panel.setAttribute("style", "display:;");
        }
        sd.connector.post("filesAction!getCommentEvaluateForm.do?objectId=" + fileId + "&objectType=30&productType=" + productType, null, null, null, afterCommentEvaluateForm);
    };
    afterCommentEvaluateForm = function(data) {
        var obj = dojo.fromJson(data);
        if (obj.customInfo[0] != "") {
            document.getElementById("evaluationRecordsForm.legalContent").value = obj.customInfo[0];
        } else {
            document.getElementById("evaluationRecordsForm.legalContent").value = "";
        }
        if (obj.customInfo[1] != "") {
            document.getElementById("evaluationRecordsForm.foodSafetyQualityContent").value = obj.customInfo[1];
        } else {
            document.getElementById("evaluationRecordsForm.foodSafetyQualityContent").value = "";
        }
        if (obj.customInfo[2] != "") {
            document.getElementById("evaluationRecordsForm.effectUtilityContent").value = obj.customInfo[2];
        } else {
            document.getElementById("evaluationRecordsForm.effectUtilityContent").value = "";
        }
        if (obj.customInfo[3] != "") {
            document.getElementById("evaluateForm.staffRequest").value = obj.customInfo[3];
        } else {
            document.getElementById("evaluateForm.staffRequest").value = "";
        }
        /*
         dijit.byId("evaluateForm.ProductType").setValue(obj.customInfo[4]);
         if (obj.customInfo[4] == 1) {//neu la thuc pham chuc nang
         document.getElementById('trLeaderReviewApproveId').style.display = "";
         } else {//khong phai thuc pham chuc nang
         document.getElementById('trLeaderReviewApproveId').style.display = "none";
         }
         */
        dijit.byId("evaluateForm.effectiveDate").setValue(-1);
        dijit.byId("evaluationRecordsForm.legal").setValue(1);
        dijit.byId("evaluationRecordsForm.foodSafetyQuality").setValue(1);
        dijit.byId("evaluationRecordsForm.effectUtility").setValue(1);
        var leaderAssignId = dijit.byId("createForm.leaderAssignId").getValue();
        dijit.byId("evaluationRecordsForm.leaderReviewId").setValue(leaderAssignId);
        dijit.byId("evaluateDlg").show();
    };
    //!140916
    page.showReviewForm = function() {//show form kết luận xem xét hồ sơ
        var fileId = dijit.byId("createForm.fileId").getValue();
        dijit.byId("reviewForm.fileId").setValue(fileId);
        sd.connector.post("filesAction!getCommentEvaluateFormByLeader.do?objectId=" + fileId, null, null, null, afterShowReviewForm);
    };
    afterShowReviewForm = function(data) {
        var obj = dojo.fromJson(data);
        if (obj.customInfo[0] != "") {
            document.getElementById("reviewForm.legalContentL").value = obj.customInfo[0];
        } else {
            document.getElementById("reviewForm.legalContentL").value = "";
        }
        if (obj.customInfo[1] != "") {
            document.getElementById("reviewForm.foodSafetyQualityContentL").value = obj.customInfo[1];
        } else {
            document.getElementById("reviewForm.foodSafetyQualityContentL").value = "";
        }
        if (obj.customInfo[2] != "") {
            document.getElementById("reviewForm.effectUtilityContentL").value = obj.customInfo[2];
        } else {
            document.getElementById("reviewForm.effectUtilityContentL").value = "";
        }
        if (obj.customInfo[3] != "") {
            document.getElementById("reviewForm.leaderStaffRequest").value = obj.customInfo[3];
        } else {
            document.getElementById("reviewForm.leaderStaffRequest").value = "";
        }
        var status = dijit.byId("createForm.status").getValue();
        var statusName = page.getStatusName(parseInt(status));

        document.getElementById("reviewForm.status").innerHTML = statusName;
        document.getElementById("reviewForm.staffRequest").innerHTML = escapeHtml_(obj.customInfo[3]);
        if (status == 4) {
            document.getElementById("reviewForm.statusAccept").checked = true;
        } else {
            document.getElementById("reviewForm.statusDeny").checked = true;
        }

        dijit.byId("reviewForm.legalL").setValue(1);
        dijit.byId("reviewForm.foodSafetyQualityL").setValue(1);
        dijit.byId("reviewForm.effectUtilityL").setValue(1);
        dijit.byId("reviewDlg").show();
        page.replaceBrTblReviewForm();//on reviewForm.jsp
    };
    page.getStatusName = function(status) {
        switch (status) {
            case 1:
                url = "Mới nộp";
                break;
            case 2:
                url = "Đã được đề xuất xử lý";
                break;
            case 3:
                url = "Đã phân công xử lý";
                break;
            case 4:
                url = "Đã thẩm định";
                break;
            case 5:
                url = "Đã xem xét kết quả";
                break;
            case 6:
                url = "Đã phê duyệt kết quả";
                break;
            case 7:
                url = "Chuyên viên KL: SĐBS";
                break;
            case 8:
                url = "Đã trả lại để thẩm định lại";
                break;
            case 9:
                url = "Đã trả lại để xem xét lại";
                break;
            default:
                url = "Mới tạo";
        }
        return url;
    };

    //------14 10 08 -------
    page.showBtnFeedbackEvaluateForm = function() {
        var status = dijit.byId("createForm.status").getValue();
        var staffProcess = dijit.byId("createForm.staffProcess").getValue();
        var viewType = dijit.byId("createForm.viewType").getValue();
        if ((status == 19) && (viewType == 4) && staffProcess == '${userId}') {
            dijit.byId("btnFeedbackEvaluateForm").domNode.style.display = "";
        }
    };
    page.showFeedbackEvaluateForm = function() {
        var fileId = dijit.byId("createForm.fileId").getValue();
        dijit.byId("feedbackEvaluateForm.fileId").setValue(fileId);
        sd.connector.post("filesAction!getCommentEvaluateFeedbackEvaluate.do?objectId=" + fileId, null, null, null, afterShowFeedbackEvaluateForm);
    };
    afterShowFeedbackEvaluateForm = function(data) {
        var obj = dojo.fromJson(data);
        if (obj.customInfo[0] != "") {
            document.getElementById("feedbackEvaluateForm.staffContent").innerHTML = obj.customInfo[0];
        } else {
            document.getElementById("feedbackEvaluateForm.staffContent").innerHTML = "";
        }
        if (obj.customInfo[1] != "") {
            document.getElementById("feedbackEvaluateForm.leaderStaffContent").innerHTML = obj.customInfo[1];
            dijit.byId("feedbackEvaluateForm.staffRequest").setValue(obj.customInfo[1]);
        } else {
            document.getElementById("feedbackEvaluateForm.leaderStaffContent").innerHTML = "";
            dijit.byId("feedbackEvaluateForm.staffRequest").setValue("");
        }
        page.rplBrTblFeedbackEvaluateForm();
        dijit.byId("feedbackEvaluateFormDlg").show();
    };
    //--
    page.showBtnFeedbackReviewForm = function() {
        var status = dijit.byId("createForm.status").getValue();
        var leaderReviewId = dijit.byId("createForm.leaderReviewId").getValue();
        var viewType = dijit.byId("createForm.viewType").getValue();
        if ((status == 28) && (viewType == 3) && leaderReviewId == '${userId}') {
            dijit.byId("btnFeedbackReviewForm").domNode.style.display = "";
        }
    };
    page.showFeedbackReviewForm = function() {
        var fileId = dijit.byId("createForm.fileId").getValue();
        var isTypeChange = dijit.byId("createForm.isTypeChange").getValue();
        if (isTypeChange == '1') {
            dijit.byId("ckbIsTypeChangeL").attr("checked", "on");
        } else {
            dijit.byId("ckbIsTypeChangeL").attr("checked", "");
        }
        dijit.byId("feedbackReviewForm.fileId").setValue(fileId);
        sd.connector.post("filesAction!getCommentFeedbackReview.do?objectId=" + fileId, null, null, null, afterShowFeedbackReviewForm);
    };
    afterShowFeedbackReviewForm = function(data) {
        var obj = dojo.fromJson(data);
        if (obj.customInfo[0] != "") {
            dijit.byId("feedbackReviewForm.leaderStaffRequest").setValue(obj.customInfo[0]);
        } else {
            dijit.byId("feedbackReviewForm.leaderStaffRequest").setValue("");
        }
//        page.rplBrTblFeedbackEvaluateForm();
        dijit.byId("feedbackReviewFormDlg").show();
    };


    page.checkIs30 = function()
    {
        msg.confirm('Xác nhận hồ sơ theo thông tư 30 ?', '<sd:Property>confirm.title1</sd:Property>', page.checkIs30New);
            };

            page.checkIs30New = function()
            {
                var fileId = dijit.byId("createForm.fileId").getValue();
                sd.connector.post("filesAction!check30.do?fileId=" + fileId, null, null, null, page.back1);
            };

            page.back1 = function()
            {
                document.getElementById("30").style.display = "";
                dijit.byId("is30").domNode.style.display = "none";
                dijit.byId("unCheck30").domNode.style.display = "";
            };
            page.checkIs30New1 = function()
            {
                var is30 = dijit.byId("createForm.is30").getValue();
                var status = dijit.byId("createForm.status").getValue();
                var leaderReviewId = dijit.byId("createForm.leaderReviewId").getValue();
                var viewType = dijit.byId("createForm.viewType").getValue();
//                if ((status == 4 || status == 7 || status == 9) && (viewType == 3) && leaderReviewId == '${userId}') {
                if ((viewType == 3) && leaderReviewId == '${userId}') {
                    if (is30 == 1)
                    {
                        document.getElementById("30").style.display = "";
                        dijit.byId("is30").domNode.style.display = "none";
                        dijit.byId("unCheck30").domNode.style.display = "";
                    } else
                    {
                        document.getElementById("30").style.display = "none";
                        dijit.byId("is30").domNode.style.display = "";
                        dijit.byId("unCheck30").domNode.style.display = "none";
                    }
                }
            };
            page.checkIs30New2 = function()
            {
                var is30 = dijit.byId("createForm.is30").getValue();
                var status = dijit.byId("createForm.status").getValue();
                var staffProcess = dijit.byId("createForm.staffProcess").getValue();
                var viewType = dijit.byId("createForm.viewType").getValue();
//                if ((status == 3 || status == 8 || status == 17) && (viewType == 4) && staffProcess == '${userId}') {
                if ((viewType == 4) && staffProcess == '${userId}') {
                    if (is30 == 1)
                    {
                        document.getElementById("30").style.display = "";
                        dijit.byId("is30").domNode.style.display = "none";
                        dijit.byId("unCheck30").domNode.style.display = "";
                    } else
                    {
                        document.getElementById("30").style.display = "none";
                        dijit.byId("is30").domNode.style.display = "";
                        dijit.byId("unCheck30").domNode.style.display = "none";
                    }
                }
            };
            page.UnCheckIs30 = function() {

                msg.confirm('Xác nhận hồ sơ không theo thông tư 30 ?', '<sd:Property>confirm.title1</sd:Property>', page.UnCheckIs30New);
                    };
                    page.UnCheckIs30New = function()
                    {
                        var fileId = dijit.byId("createForm.fileId").getValue();
                        sd.connector.post("filesAction!UnCheck30.do?fileId=" + fileId, null, null, null, page.back2);
                    };
                    page.back2 = function()
                    {
                        document.getElementById("30").style.display = "none";
                        dijit.byId("is30").domNode.style.display = "";
                        dijit.byId("unCheck30").domNode.style.display = "none";
//                
                    };
                    page.checkIs30New1();
                    page.checkIs30New2();

                    //!------14 10 08 -------
                    page.checkButtonFileSign();
                    page.showAnnouncementReceiptPaperButton();
                    //page.showResultEvaluationButton();
                    page.showCommentEvalutionButton();
                    page.showBtnViewFLow();
                    //page.showReceivedButton();
                    page.showBtnCheckCA();
                    page.showComparisonButton();
                    page.checkTimeProcessFile();
                    page.showReturnFilesButton();
                    page.showBtnOldVersion();
                    //page.showBtnEvaluateFiles();
                    page.showEvaluateLeaderFiles();
                    //page.showBtnReviewFiles();
                    page.showBtnFeedbackEvaluateForm();
                    page.showBtnFeedbackReviewForm();
</script>