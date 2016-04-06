<%-- 
    Document   : AnnouncementReceiptPaperCreateDlg - 
Chức năng nhập thông tin giấy tiếp nhận bản công bố hợp quy
    Created on : 
    Author     : vtit_binhnt53
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../../../util/util_js.jsp"/>
<jsp:include page="../../../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="token">
    <s:token id="tokenId"/>
</div>
<form id="signForm" name="signForm">
    <sx:ResultMessage id="resultMessage" />
    <div class="buttonDiv" style="display: none">
        <sx:ButtonBack onclick="backPage();"/>
        <sd:Button id="btnSign" key="" onclick="page.onSign();" >
            <img src="${contextPath}/share/images/icons/6.png" height="14" width="18">
            <span style="font-size:12px">Ký duyệt</span>
        </sd:Button>
        <sd:Button id="btnReject" key="" onclick="page.onReject();">
            <img src="${contextPath}/share/images/icons/13.png" height="14" width="18">
            <span style="font-size:12px">Từ chối ký duyệt</span>
        </sd:Button>
    </div>
    <sd:TitlePane key="Thông tin giấy tiếp nhận hồ sơ" id="TitleTitle">
        <sd:FieldSet key="Thông tin" id="tab.userRep">
            <table width="100%" class="editTable">
                <tr>
                    <td width="20%">
                        <sd:Label>Số giấy tiếp nhận:</sd:Label></td>
                        <td width="30%">
                            <div id="signForm.announcementReceiptPaperForm.receiptNo"> ${fn:escapeXml(signForm.announcementReceiptPaperForm.receiptNo)} </div>
                        <sd:TextBox 
                            id="signForm.announcementReceiptPaperForm.announcementReceiptPaperId" 
                            name="signForm.announcementReceiptPaperForm.announcementReceiptPaperId" 
                            cssStyle="display:none" 
                            key=""
                            trim="true"/>
                        <sd:TextBox 
                            id="signForm.fileId"
                            name="signForm.fileId" 
                            cssStyle="display:none" 
                            key=""
                            trim="true"/>
                        <sd:TextBox 
                            id="signForm.announcementReceiptPaperForm.isActive" 
                            name="signForm.announcementReceiptPaperForm.isActive" 
                            cssStyle="display:none" 
                            key=""
                            trim="true"/>
                    </td>
                    <td width="20%"><sd:Label>Ngày ban hành:</sd:Label></td>
                        <td width="30%">
                            <div id="signForm.announcementReceiptPaperForm.receiptDate"> ${fn:escapeXml(signForm.announcementReceiptPaperForm.receiptDateStr)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="20%"><sd:Label>Tên cơ quan tiếp nhận công bố:</sd:Label></td>
                        <td width="30%">
                            <div id="signForm.announcementReceiptPaperForm.receiptDeptName"> ${fn:escapeXml(signForm.announcementReceiptPaperForm.receiptDeptName)} </div>
                    </td>
                    <td width="20%"><sd:Label>Tên tổ chức, cá nhân:</sd:Label></td>
                        <td width="30%">
                            <div id="signForm.announcementReceiptPaperForm.businessName"> ${fn:escapeXml(signForm.announcementReceiptPaperForm.businessName)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="20%"><sd:Label>Điện thoại:</sd:Label></td>
                        <td width="30%">
                            <div id="signForm.announcementReceiptPaperForm.telephone"> ${fn:escapeXml(signForm.announcementReceiptPaperForm.telephone)} </div>
                    </td>
                    <td width="20%"><sd:Label>Fax:</sd:Label></td>
                        <td width="30%">
                            <div id="signForm.announcementReceiptPaperForm.fax"> ${fn:escapeXml(signForm.announcementReceiptPaperForm.fax)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="20%"><sd:Label>Email:</sd:Label></td>
                        <td width="30%">
                            <div id="signForm.announcementReceiptPaperForm.email"> ${fn:escapeXml(signForm.announcementReceiptPaperForm.email)} </div>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td width="20%"><sd:Label>Tên sản phẩm:</sd:Label></td>
                        <td width="30%">
                            <div id="signForm.announcementReceiptPaperForm.productName"> ${fn:escapeXml(signForm.announcementReceiptPaperForm.productName)} </div>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td width="20%"><sd:Label>Tên cơ sở sản xuất:</sd:Label></td>
                        <td width="30%">
                            <div id="signForm.announcementReceiptPaperForm.manufactureName"> ${fn:escapeXml(signForm.announcementReceiptPaperForm.manufactureName)} </div>
                    </td>
                    <td width="20%">
                        <sd:Label>Nước xuất xứ:</sd:Label></td>
                        <td width="30%">
                            <div id="signForm.announcementReceiptPaperForm.nationName"> ${fn:escapeXml(signForm.announcementReceiptPaperForm.nationName)} </div>
                    </td>
                </tr>
            </table>
        </sd:FieldSet>

        <sd:FieldSet key="Xác thực" id="tab.business">
            <table width="100%" class="editTable">
                <tr>
                    <td width="20%">

                        <sd:Label>Số hiệu qui chuẩn kĩ thuật:</sd:Label></td>
                        <td width="30%">
                            <div id="signForm.announcementReceiptPaperForm.matchingTarget"> ${fn:escapeXml(signForm.announcementReceiptPaperForm.matchingTarget)} </div>
                    </td>
                    <td width="20%">

                        <sd:Label>Thời gian hiệu lực của giấy tiếp nhận:</sd:Label></td>
                        <td width="30%">
                            <div id="signForm.announcementReceiptPaperForm.effectiveDate"></div>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%">

                        <sd:Label>Ngày kí:</sd:Label></td>
                        <td width="30%">
                            <div id="signForm.announcementReceiptPaperForm.signDate"> ${fn:escapeXml(signForm.announcementReceiptPaperForm.signDateStr)} </div>
                    </td>
                    <td width="20%">
                        <sd:Label>Người kí:</sd:Label></td>
                        <td width="30%">
                            <div id="signForm.announcementReceiptPaperForm.signerName"> ${fn:escapeXml(signForm.announcementReceiptPaperForm.signerName)} </div>
                    </td>
                </tr>
            </table>
        </sd:FieldSet>
        <sd:FieldSet key="QR CODE" id="tab.business">                
            <img id="imgQRCode" src="${imgsrc}" width="200" height="200"/>
        </sd:FieldSet>
    </form>
    <sd:Button id="btnExportAnnounment" key="" onclick="page.downloadWord();" cssStyle="display:none" cssClass="buttonGroup">
        <img src="share/images/icons/document_management.png" height="14" width="14" alt="Chi tiết giấy tiếp nhận hồ sơ"/>
        <span style="font-size:12px">Xuất giấy tiếp nhận hồ sơ</span>
    </sd:Button>
</sd:TitlePane>
<script>
//    page.onSign = function() {
//        dijit.byId("signedForm.fileId").setValue(dijit.byId("signForm.fileId").getValue());
//        dijit.byId("dlgSign").show();
//    };
//    page.onReject = function() {
//        dijit.byId("rejectForm.fileId").setValue(dijit.byId("signForm.fileId").getValue());
//        dijit.byId("dlgReject").show();
//    };
    page.downloadWord = function() {
        var fileId = dijit.byId("signForm.fileId").getValue();
        document.location = "exportWord!onExportPaper.do?fileId=" + fileId;
    };
    var effectiveDate = "${fn:escapeXml(signForm.effectiveDate)}";
    if (effectiveDate > 0) {
        document.getElementById("signForm.announcementReceiptPaperForm.effectiveDate").innerHTML = effectiveDate + ' năm';
    } else {
        document.getElementById("signForm.announcementReceiptPaperForm.effectiveDate").innerHTML = 'Không thời hạn';
    }
</script>
