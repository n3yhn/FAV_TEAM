<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="announcementDiv">
    <sd:FieldSet key="Thông tin doanh nghiệp">
        <table class="viewTable">
            <tr>
                <td width="25%"><sd:Label required="true">Tên tổ chức, cá nhân</sd:Label><font style="color:red">*</font></td>
                    <td width="25%">
                        <div  id="createForm.announcement.businessName"> ${fn:escapeXml(createForm.announcement.businessName)} </div>
                </td>
                <td width="25%"><sd:Label required="true">Địa chỉ</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.businessAddress">${fn:escapeXml(createForm.announcement.businessAddress)}</div>
                </td>
            </tr>
            <tr>

                <td width="25%" ><sd:Label required="true">Điện thoại</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.businessTelephone">${fn:escapeXml(createForm.announcement.businessTelephone)}</div>
                </td>
                <td width="25%"><sd:Label>Fax</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.businessFax"> ${fn:escapeXml(createForm.announcement.businessFax)} </div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Email</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.businessEmail" >${fn:escapeXml(createForm.announcement.businessEmail)}</div>
                </td>
                <td width="25%"><sd:Label required="true">Tên sản phẩm</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.productName"> ${fn:escapeXml(createForm.announcement.productName)} </div>
                </td>
            </tr>
        </table>
    </sd:FieldSet>
    <br/>
    <sd:FieldSet key="Xuất xứ">
        <table class="viewTable">
            <tr>
                <td width="25%"><sd:Label required="true">Tên nhà sản xuất</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.manufactureName"> ${fn:escapeXml(createForm.announcement.manufactureName)} </div>
                </td>
                <td width="25%"><sd:Label required="true">Địa chỉ</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.manufactureAddress"> ${fn:escapeXml(createForm.announcement.manufactureAddress)} </div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Điện thoại</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.manufactureTel"> ${fn:escapeXml(createForm.announcement.manufactureTel)} </div>
                </td>
                <td width="25%"><sd:Label>Fax</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.manufactureFax"> ${fn:escapeXml(createForm.announcement.manufactureFax)} </div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Email</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.manufactureEmail"> ${fn:escapeXml(createForm.announcement.manufactureEmail)}</div>
                </td>
                <td width="25%"><sd:Label>Tên nước xuất xứ</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.nationName"> ${fn:escapeXml(createForm.announcement.nationName)} </div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Tên công ty xuất khẩu</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.nationCompanyName"> ${fn:escapeXml(createForm.announcement.nationCompanyName)}</div>
                </td>
                <td width="25%"><sd:Label>Địa chỉ công ty xuất khẩu</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.nationCompanyAddress"> ${fn:escapeXml(createForm.announcement.nationCompanyAddress)} </div>
                </td>
            </tr>
        </table>
    </sd:FieldSet>
    <br/>
    <sd:FieldSet key="Thông tin công bố">
        <table class="viewTable" id="detailDiv1">
            <tr>
                <td width="25%"><sd:Label required="true">Số bản công bố</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.announcementNo"> ${fn:escapeXml(createForm.announcement.announcementNo)} </div>
                    <sd:TextBox key="" id="createForm.announcement.announcementId" name="createForm.announcement.announcementId" cssStyle="display:none"/>
                </td>
                <td width="25%"><sd:Label>Ngày công bố</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.publishDate"> ${fn:escapeXml(createForm.announcement.publishDateStr)} </div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label required="true">Phù hợp với QCKT/QĐATTP</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.matchingTarget"> ${fn:escapeXml(createForm.announcement.matchingTarget)} </div>
                </td>
                <td width="25%"><sd:Label>Phương thức đánh giá phù hợp</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.assessmentMethod"> ${fn:escapeXml(createForm.announcement.assessmentMethod)} </div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Người ký</sd:Label></td>
                    <td width="25%">
                        <div id="createForm.announcement.signer"> ${fn:escapeXml(createForm.announcement.signer)} </div>
                </td>
                <td colspan="2"></td>
            </tr>
        </table>
    </sd:FieldSet> 
</div>
<script type="text/javascript">
    page.replaceNewLineByBr = function() {
        var table = document.getElementById("detailDiv1");
        var divs = table.getElementsByTagName("div");
        var i = 0;
        var content = "";
        for (i = 0; i < divs.length; i++) {
            content = divs[i].innerHTML;
            if (content != "") {
                content = content.replace(/\n/g, "<br>");
                divs[i].innerHTML = content;
            }
        }
    };
    page.replace = function() {
        var content = "";
        content = document.getElementById("createForm.announcement.matchingTarget").innerHTML;
        if (content != "") {
            content = content.replace(/;/g, "<br>");
            document.getElementById("createForm.announcement.matchingTarget").innerHTML = content;
        }
    };

    page.checkModifiredTabAnnouncement = function() {
        var announcementId = '${fn:escapeXml(createFormClone.announcement.announcementId)}';

        if (announcementId != null && announcementId > 0) {
            var a = '${fn:escapeXml(createFormOriginal.announcement.businessName)}';
            var b = '${fn:escapeXml(createFormClone.announcement.businessName)}';

            if (a != b) {
                document.getElementById("createForm.announcement.businessName").style.color = "#ff0000";
            }
            //announcement.businessAddress
            a = '${fn:escapeXml(createFormOriginal.announcement.businessAddress)}';
            b = '${fn:escapeXml(createFormClone.announcement.businessAddress)}';
            if (a != b) {
                document.getElementById("createForm.announcement.businessAddress").style.color = "#ff0000";
            }
            //announcement.businessTelephone
            a = '${fn:escapeXml(createFormOriginal.announcement.businessTelephone)}';
            b = '${fn:escapeXml(createFormClone.announcement.businessTelephone)}';
            if (a != b) {
                document.getElementById("createForm.announcement.businessTelephone").style.color = "#ff0000";
            }
            //announcement.businessFax
            a = '${fn:escapeXml(createFormOriginal.announcement.businessFax)}';
            b = '${fn:escapeXml(createFormClone.announcement.businessFax)}';
            if (a != b) {
                document.getElementById("createForm.announcement.businessFax").style.color = "#ff0000";
            }
            //announcement.businessEmail
            a = '${fn:escapeXml(createFormOriginal.announcement.businessEmail)}';
            b = '${fn:escapeXml(createFormClone.announcement.businessEmail)}';
            if (a != b) {
                document.getElementById("createForm.announcement.businessEmail").style.color = "#ff0000";
            }
            //announcement.productName
            a = '${fn:escapeXml(createFormOriginal.announcement.productName)}';
            b = '${fn:escapeXml(createFormClone.announcement.productName)}';
            if (a != b) {
                document.getElementById("createForm.announcement.productName").style.color = "#ff0000";
            }
            //announcement.manufactureName
            a = '${fn:escapeXml(createFormOriginal.announcement.manufactureName)}';
            b = '${fn:escapeXml(createFormClone.announcement.manufactureName)}';
            if (a != b) {
                document.getElementById("createForm.announcement.manufactureName").style.color = "#ff0000";
            }

            //announcement.manufactureAddress
            a = '${fn:escapeXml(createFormOriginal.announcement.manufactureAddress)}';
            b = '${fn:escapeXml(createFormClone.announcement.manufactureAddress)}';
            if (a != b) {
                document.getElementById("createForm.announcement.manufactureAddress").style.color = "#ff0000";
            }
            //announcement.manufactureTel
            a = '${fn:escapeXml(createFormOriginal.announcement.manufactureTel)}';
            b = '${fn:escapeXml(createFormClone.announcement.manufactureTel)}';
            if (a != b) {
                document.getElementById("createForm.announcement.manufactureTel").style.color = "#ff0000";
            }
            //announcement.manufactureFax
            a = '${fn:escapeXml(createFormOriginal.announcement.manufactureFax)}';
            b = '${fn:escapeXml(createFormClone.announcement.manufactureFax)}';
            if (a != b) {
                document.getElementById("createForm.announcement.manufactureFax").style.color = "#ff0000";
            }
            //announcement.manufactureEmail
            a = '${fn:escapeXml(createFormOriginal.announcement.manufactureEmail)}';
            b = '${fn:escapeXml(createFormClone.announcement.manufactureEmail)}';
            if (a != b) {
                document.getElementById("createForm.announcement.manufactureEmail").style.color = "#ff0000";
            }
            //announcement.nationName
            a = '${fn:escapeXml(createFormOriginal.announcement.nationName)}';
            b = '${fn:escapeXml(createFormClone.announcement.nationName)}';
            if (a != b) {
                document.getElementById("createForm.announcement.nationName").style.color = "#ff0000";
            }
            //announcement.nationCompanyName
            a = '${fn:escapeXml(createFormOriginal.announcement.nationCompanyName)}';
            b = '${fn:escapeXml(createFormClone.announcement.nationCompanyName)}';
            if (a != b) {
                document.getElementById("createForm.announcement.nationCompanyName").style.color = "#ff0000";
            }
            //announcement.nationCompanyName
            a = '${fn:escapeXml(createFormOriginal.announcement.nationCompanyAddress)}';
            b = '${fn:escapeXml(createFormClone.announcement.nationCompanyAddress)}';
            if (a != b) {
                document.getElementById("createForm.announcement.nationCompanyAddress").style.color = "#ff0000";
            }
            //announcement.announcementNo
            a = '${fn:escapeXml(createFormOriginal.announcement.announcementNo)}';
            b = '${fn:escapeXml(createFormClone.announcement.announcementNo)}';
            if (a != b) {
                document.getElementById("createForm.announcement.announcementNo").style.color = "#ff0000";
            }
            //announcement.publishDate
            a = '${fn:escapeXml(createFormOriginal.announcement.publishDate)}';
            b = '${fn:escapeXml(createFormClone.announcement.publishDate)}';
            if (a != b) {
                document.getElementById("createForm.announcement.publishDate").style.color = "#ff0000";
            }
            //announcement.matchingTarget
            a = '${createFormOriginal.announcement.matchingTarget}';
            b = '${createFormClone.announcement.matchingTarget}';
            if (a != b) {
                document.getElementById("createForm.announcement.matchingTarget").style.color = "#ff0000";
            }
            //announcement.assessmentMethod
//            a = '${createFormOriginal.announcement.assessmentMethod}';
//            b = '${createFormClone.announcement.assessmentMethod}';alert(b);
//            if (a != b) {
//                document.getElementById("createForm.announcement.assessmentMethod").style.color = "#ff0000";
//            }
            //announcement.signer
            a = '${fn:escapeXml(createFormOriginal.announcement.signer)}';
            b = '${fn:escapeXml(createFormClone.announcement.signer)}';
            if (a != b) {
                document.getElementById("createForm.announcement.signer").style.color = "#ff0000";
            }
        }
    };

    replaceExponentByShift6("announcementDiv");
    replaceSubscript("announcementDiv");
    page.checkModifiredTabAnnouncement();
    page.replaceNewLineByBr();
    page.replace();
</script>