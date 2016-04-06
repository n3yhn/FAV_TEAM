<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="testRegistrationDiv">
    <table class="editTable">
        <tr>
            <td width="25%"><sd:Label required="true">Tên cơ quan kiểm tra</sd:Label></td>
                <td width="25%">
                    <div id="createForm.testRegistration.testAgency">${fn:escapeXml(createForm.testRegistration.testAgency)}</div>
            </td>
            <td colspan="2"></td>
        </tr>
    </table>


    <sd:FieldSet key="Thương nhân xuất khẩu">
        <table class="editTable">
            <tr>
                <td width="25%"><sd:Label required="true">Thương nhân</sd:Label><font style="color:red">*</font></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.exportBusinessName">${fn:escapeXml(createForm.testRegistration.exportBusinessName)}</div>
                </td>
                <td colspan="2">
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label required="true">Địa chỉ</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.exportBusinessAdd">${fn:escapeXml(createForm.testRegistration.exportBusinessAdd)}</div>
                </td>
                <td width="25%" ><sd:Label required="true">Email</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.exportBusinessMail">${fn:escapeXml(createForm.testRegistration.exportBusinessMail)}</div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Điện thoại</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.exportBusinessTel">${fn:escapeXml(createForm.testRegistration.exportBusinessTel)}</div>
                </td>
                <td width="25%"><sd:Label>Fax</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.exportBusinessFax">${fn:escapeXml(createForm.testRegistration.exportBusinessFax)}</div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Số hợp đồng</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.exportContractCode">${fn:escapeXml(createForm.testRegistration.exportContractCode)}</div>
                </td>
                <td width="25%"><sd:Label>Ngày hợp đồng</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.exportContractDate">${fn:escapeXml(createForm.testRegistration.exportContractDate)}</div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Số vận đơn</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.exportLadingCode">${fn:escapeXml(createForm.testRegistration.exportLadingCode)}</div>
                </td>
                <td width="25%"><sd:Label>Ngày vận đơn</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.exportLadingDate">${fn:escapeXml(createForm.testRegistration.exportLadingDate)}</div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Bến đi</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.exportPort">${fn:escapeXml(createForm.testRegistration.exportPort)}</div>
                </td>
                <td colspan="2"></td>
            </tr>
        </table>
    </sd:FieldSet>
    <br/>
    <sd:FieldSet key="Thương nhân nhập khẩu">
        <table class="editTable">
            <tr>
                <td width="25%"><sd:Label required="true">Thương nhân</sd:Label><font style="color:red">*</font></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.importBusinessName">${fn:escapeXml(createForm.testRegistration.importBusinessName)}</div>
                </td>
                <td colspan="2">
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label required="true">Địa chỉ</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.importBusinessAddress">${fn:escapeXml(createForm.testRegistration.importBusinessAddress)}</div>
                </td>
                <td width="25%" ><sd:Label required="true">Email</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.importBusinessEmail">${fn:escapeXml(createForm.testRegistration.importBusinessEmail)}</div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Điện thoại</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.importBusinessTel">${fn:escapeXml(createForm.testRegistration.importBusinessTel)}</div>
                </td>
                <td width="25%"><sd:Label>Fax</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.importBusinessFax">${fn:escapeXml(createForm.testRegistration.importBusinessFax)}</div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Bến đến</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.importPort">${fn:escapeXml(createForm.testRegistration.importPort)}</div>
                </td>
                <td width="25%"><sd:Label>Thời gian nhập khẩu dự kiến</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.importDate">${fn:escapeXml(createForm.testRegistration.importDate)}</div>
                </td>
            </tr>
        </table>
    </sd:FieldSet>
    <br/>

    <sd:FieldSet key="Hàng hóa">
        <table class="editTable">
            <tr>
                <td width="25%"><sd:Label required="true">Mô tả hàng hóa</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.productDescription">${fn:escapeXml(createForm.testRegistration.productDescription)}</div>
                </td>
                <td width="25%"><sd:Label>Tên hàng hóa</sd:Label><font style="color:red">*</font></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.productName">${fn:escapeXml(createForm.testRegistration.productName)}</div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Ký hiệu mã</sd:Label><font style="color:red">*</font></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.productCode">${fn:escapeXml(createForm.testRegistration.productCode)}</div>
                </td>
                <td width="25%"><sd:Label>Xuất xứ</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.productOrigin">${fn:escapeXml(createForm.testRegistration.productOrigin)}</div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Số lượng</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.productAmount">${fn:escapeXml(createForm.testRegistration.productAmount)}</div>
                </td>
                <td width="25%"><sd:Label>Khối lượng</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.productWeight">${fn:escapeXml(createForm.testRegistration.productWeight)}</div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Giá trị hàng hóa</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.productValue">${fn:escapeXml(createForm.testRegistration.productValue)}</div>
                </td>
                <td width="25%"><sd:Label>Địa điểm tập kết</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.gatheringAdd">${fn:escapeXml(createForm.testRegistration.gatheringAdd)}</div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Thời gian kiểm tra</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.testDate">${fn:escapeXml(createForm.testRegistration.testDate)}</div>
                </td>
                <td width="25%"><sd:Label>Địa điểm kiểm tra</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.testAdd">${fn:escapeXml(createForm.testRegistration.testAdd)}</div>
                </td>
            </tr>
        </table>

    </sd:FieldSet> 
    <br/>
    <sd:FieldSet key="Đại diện xác nhận">
        <table class="editTable">
            <tr>
                <td width="25%"><sd:Label required="true">Đại diện thương nhân nhập khẩu</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.businessRepresent">${fn:escapeXml(createForm.testRegistration.businessRepresent)}</div>
                </td>
                <td colspan="2"></td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Địa điểm</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.businessSignAdd">${fn:escapeXml(createForm.testRegistration.businessSignAdd)}</div>
                </td>
                <td width="25%"><sd:Label>Ngày ký</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.businessSigndate">${fn:escapeXml(createForm.testRegistration.businessSigndate)}</div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Đại diện của cơ quan kiểm tra</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.agencyRepresent">${fn:escapeXml(createForm.testRegistration.agencyRepresent)}</div>
                </td>
                <td colspan="2"></td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Địa điểm</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.agencySignAdd">${fn:escapeXml(createForm.testRegistration.agencySignAdd)}</div>
                </td>
                <td width="25%"><sd:Label>Ngày ký</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.agencySigndate">${fn:escapeXml(createForm.testRegistration.agencySigndate)}</div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Số tiêu chuẩn, quy chuẩn</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.standardTargetNo">${fn:escapeXml(createForm.testRegistration.standardTargetNo)}</div>
                </td>
                <td width="25%"><sd:Label>Ngày cấp</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.standardTargetDate">${fn:escapeXml(createForm.testRegistration.standardTargetDate)}</div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Số công văn giải tỏa lô hàng</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.releaseDocumentNo">${fn:escapeXml(createForm.testRegistration.releaseDocumentNo)}</div>
                </td>
                <td width="25%"><sd:Label>Ngày cấp</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.testRegistration.releaseDocumentDate">${fn:escapeXml(createForm.testRegistration.releaseDocumentDate)}</div>
                </td>
            </tr>
        </table>

    </sd:FieldSet> 
</div>
<script type="text/javascript">
    page.checkModifiredTabTestRegistration = function() {
        var testRegistrationId = '${fn:escapeXml(createFormClone.testRegistration.testRegistrationId)}';
        if (testRegistrationId != null && testRegistrationId > 0) {
            //        createForm.testRegistration.testAgency
            var a = '${fn:escapeXml(createFormOriginal.testRegistration.testAgency)}';
            var b = '${fn:escapeXml(createFormClone.testRegistration.testAgency)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.testAgency").style.color = "#ff0000";
            }
//        createForm.testRegistration.exportBusinessName
            a = '${fn:escapeXml(createFormOriginal.testRegistration.exportBusinessName)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.exportBusinessName)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.exportBusinessName").style.color = "#ff0000";
            }
//        createForm.testRegistration.exportBusinessAdd
            a = '${fn:escapeXml(createFormOriginal.testRegistration.exportBusinessAdd)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.exportBusinessAdd)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.exportBusinessAdd").style.color = "#ff0000";
            }
//        createForm.testRegistration.exportBusinessMail
            a = '${fn:escapeXml(createFormOriginal.testRegistration.exportBusinessMail)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.exportBusinessMail)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.exportBusinessMail").style.color = "#ff0000";
            }
//        createForm.testRegistration.exportBusinessTel
            a = '${fn:escapeXml(createFormOriginal.testRegistration.exportBusinessTel)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.exportBusinessTel)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.exportBusinessTel").style.color = "#ff0000";
            }
//        createForm.testRegistration.exportBusinessFax
            a = '${fn:escapeXml(createFormOriginal.testRegistration.exportBusinessFax)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.exportBusinessFax)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.exportBusinessFax").style.color = "#ff0000";
            }
//        createForm.testRegistration.exportContractCode
            a = '${fn:escapeXml(createFormOriginal.testRegistration.exportContractCode)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.exportContractCode)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.exportContractCode").style.color = "#ff0000";
            }
//        createForm.testRegistration.exportContractDate
            a = '${fn:escapeXml(createFormOriginal.testRegistration.exportContractDate)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.exportContractDate)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.exportContractDate").style.color = "#ff0000";
            }
//        createForm.testRegistration.exportLadingCode
            a = '${fn:escapeXml(createFormOriginal.testRegistration.exportLadingCode)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.exportLadingCode)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.exportLadingCode").style.color = "#ff0000";
            }
//        createForm.testRegistration.exportLadingDate
            a = '${fn:escapeXml(createFormOriginal.testRegistration.exportLadingDate)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.exportLadingDate)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.exportLadingDate").style.color = "#ff0000";
            }
//        createForm.testRegistration.exportPort
            a = '${fn:escapeXml(createFormOriginal.testRegistration.exportPort)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.exportPort)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.exportPort").style.color = "#ff0000";
            }
//        createForm.testRegistration.importBusinessName
            a = '${fn:escapeXml(createFormOriginal.testRegistration.importBusinessName)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.importBusinessName)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.importBusinessName").style.color = "#ff0000";
            }
//        createForm.testRegistration.importBusinessAddress
            a = '${fn:escapeXml(createFormOriginal.testRegistration.importBusinessAddress)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.importBusinessAddress)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.importBusinessAddress").style.color = "#ff0000";
            }
//        createForm.testRegistration.importBusinessEmail
            a = '${fn:escapeXml(createFormOriginal.testRegistration.importBusinessEmail)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.importBusinessEmail)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.importBusinessEmail").style.color = "#ff0000";
            }
//        createForm.testRegistration.importBusinessTel
            a = '${fn:escapeXml(createFormOriginal.testRegistration.importBusinessTel)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.importBusinessTel)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.importBusinessTel").style.color = "#ff0000";
            }
//        createForm.testRegistration.importBusinessFax
            a = '${fn:escapeXml(createFormOriginal.testRegistration.importBusinessFax)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.importBusinessFax)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.importBusinessFax").style.color = "#ff0000";
            }
//        createForm.testRegistration.importPort
            a = '${fn:escapeXml(createFormOriginal.testRegistration.importPort)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.importPort)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.importPort").style.color = "#ff0000";
            }
//        createForm.testRegistration.importDate
            a = '${fn:escapeXml(createFormOriginal.testRegistration.importDate)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.importDate)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.importDate").style.color = "#ff0000";
            }
//        createForm.testRegistration.productDescription
            a = '${fn:escapeXml(createFormOriginal.testRegistration.productDescription)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.productDescription)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.productDescription").style.color = "#ff0000";
            }
//        createForm.testRegistration.productName
            a = '${fn:escapeXml(createFormOriginal.testRegistration.productName)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.productName)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.productName").style.color = "#ff0000";
            }
//        createForm.testRegistration.productCode
            a = '${fn:escapeXml(createFormOriginal.testRegistration.productCode)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.productCode)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.exportContractDate").style.color = "#ff0000";
            }
//        createForm.testRegistration.productOrigin
            a = '${fn:escapeXml(createFormOriginal.testRegistration.productOrigin)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.productOrigin)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.productOrigin").style.color = "#ff0000";
            }
//        createForm.testRegistration.productAmount
            a = '${fn:escapeXml(createFormOriginal.testRegistration.productAmount)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.productAmount)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.productAmount").style.color = "#ff0000";
            }
//        createForm.testRegistration.productWeight
            a = '${fn:escapeXml(createFormOriginal.testRegistration.productWeight)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.productWeight)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.productWeight").style.color = "#ff0000";
            }
//        createForm.testRegistration.productValue
            a = '${fn:escapeXml(createFormOriginal.testRegistration.productValue)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.productValue)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.productValue").style.color = "#ff0000";
            }
//        createForm.testRegistration.gatheringAdd
            a = '${fn:escapeXml(createFormOriginal.testRegistration.gatheringAdd)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.gatheringAdd)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.gatheringAdd").style.color = "#ff0000";
            }
//        createForm.testRegistration.testDate
            a = '${fn:escapeXml(createFormOriginal.testRegistration.testDate)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.testDate)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.testDate").style.color = "#ff0000";
            }
//        createForm.testRegistration.testAdd
            a = '${fn:escapeXml(createFormOriginal.testRegistration.testAdd)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.testAdd)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.testAdd").style.color = "#ff0000";
            }
//        createForm.testRegistration.businessRepresent
            a = '${fn:escapeXml(createFormOriginal.testRegistration.businessRepresent)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.businessRepresent)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.businessRepresent").style.color = "#ff0000";
            }
//        createForm.testRegistration.businessSignAdd
            a = '${fn:escapeXml(createFormOriginal.testRegistration.businessSignAdd)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.businessSignAdd)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.businessSignAdd").style.color = "#ff0000";
            }
//        createForm.testRegistration.businessSigndate
            a = '${fn:escapeXml(createFormOriginal.testRegistration.businessSigndate)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.businessSigndate)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.exportContractDate").style.color = "#ff0000";
            }
//        createForm.testRegistration.agencyRepresent
            a = '${fn:escapeXml(createFormOriginal.testRegistration.agencyRepresent)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.agencyRepresent)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.agencyRepresent").style.color = "#ff0000";
            }
//        createForm.testRegistration.agencySignAdd
            a = '${fn:escapeXml(createFormOriginal.testRegistration.agencySignAdd)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.agencySignAdd)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.agencySignAdd").style.color = "#ff0000";
            }
//        createForm.testRegistration.agencySigndate
            a = '${fn:escapeXml(createFormOriginal.testRegistration.agencySigndate)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.agencySigndate)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.agencySigndate").style.color = "#ff0000";
            }
//        createForm.testRegistration.standardTargetNo
            a = '${fn:escapeXml(createFormOriginal.testRegistration.standardTargetNo)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.standardTargetNo)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.standardTargetNo").style.color = "#ff0000";
            }
//        createForm.testRegistration.standardTargetDate
            a = '${fn:escapeXml(createFormOriginal.testRegistration.standardTargetDate)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.standardTargetDate)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.standardTargetDate").style.color = "#ff0000";
            }
//        createForm.testRegistration.releaseDocumentNo
            a = '${fn:escapeXml(createFormOriginal.testRegistration.releaseDocumentNo)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.releaseDocumentNo)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.releaseDocumentNo").style.color = "#ff0000";
            }
//        createForm.testRegistration.releaseDocumentDate
            a = '${fn:escapeXml(createFormOriginal.testRegistration.releaseDocumentDate)}';
            b = '${fn:escapeXml(createFormClone.testRegistration.releaseDocumentDate)}';
            if (a != b) {
                document.getElementById("createForm.testRegistration.releaseDocumentDate").style.color = "#ff0000";
            }
        }
    };
    replaceExponentByShift6("testRegistrationDiv");
    replaceSubscript("testRegistrationDiv");
    page.checkModifiredTabTestRegistration();
</script>