<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<sd:FieldSet key="Thông tin doanh nghiệp">
    <table class="viewTable">
        <tr>
            <td width="25%"><sd:Label required="true">Tên tổ chức, cá nhân</sd:Label></td>
                <td width="25%">
                    <div  id="createForm.announcement.businessName"> ${fn:escapeXml(createForm.announcement.businessName)} </div>
            </td>
            <td width="25%"><sd:Label required="true">Địa chỉ</sd:Label></td>
                <td width="25%">
                    <div  id="createForm.announcement.businessAddress"> ${fn:escapeXml(createForm.announcement.businessAddress)} </div>
            </td>
        </tr>
        <tr>

            <td width="25%" ><sd:Label required="true">Điện thoại</sd:Label></td>
                <td width="25%">
                    <div  id="createForm.announcement.businessTelephone"> ${fn:escapeXml(createForm.announcement.businessTelephone)} </div>
            </td>
            <td width="25%"><sd:Label>Fax</sd:Label></td>
                <td width="25%">
                    <div  id="createForm.announcement.businessFax"> ${fn:escapeXml(createForm.announcement.businessFax)} </div>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Email</sd:Label></td>
                <td width="25%">
                    <div  id="createForm.announcement.businessEmail"> ${fn:escapeXml(createForm.announcement.businessEmail)} </div>
            </td>
        </tr> 
    </table>
</sd:FieldSet>
<br/>
<sd:FieldSet key="Thông tin hồ sơ">
    <table class="viewTable">
        <tr>
            <td width="25%"><sd:Label required="true">Mã hồ sơ gốc</sd:Label></td>
                <td width="25%">
                    <div  id="createForm.fileSourceCode"> ${fn:escapeXml(createForm.fileSourceCode)} </div>
            </td>
            <td width="25%"><sd:Label required="true">Tên sản phẩm</sd:Label></td>
                <td width="25%">
                    <div id="createForm.announcement.productName"> ${fn:escapeXml(createForm.announcement.productName)} </div>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label required="true">Số bản công bố</sd:Label></td>
                <td width="25%">
                    <div  id="createForm.announcement.announcementNo"> ${fn:escapeXml(createForm.announcement.announcementNo)} </div>
                <sd:TextBox key="" id="createForm.announcement.announcementId" name="createForm.announcement.announcementId" readonly="true"  cssStyle="display:none"/>
            </td>
            <td width="25%"><sd:Label>Ngày công bố</sd:Label></td>
                <td width="25%">
                    <div  id="createForm.announcement.publishDate"> ${fn:escapeXml(createForm.announcement.publishDate)} </div>
            </td>

        </tr>
        <tr>
            <td width="25%"><sd:Label required="true">Phù hợp với QCKT/QĐATTP</sd:Label></td>
                <td width="25%">
                    <div  id="createForm.announcement.matchingTarget"> ${fn:escapeXml(createForm.announcement.matchingTarget)} </div>
            </td>
            <td width="25%"><sd:Label>Người ký</sd:Label></td>
                <td width="25%">
                    <div  id="createForm.announcement.signer"> ${fn:escapeXml(createForm.announcement.signer)} </div>
            </td>
        </tr>
    </table>
</sd:FieldSet>
<br/>
<sd:FieldSet key="Nội dung sửa đổi - bổ sung">
    <table class="viewTable">
        <tr>
            <td width="10%" ><sd:Label required="true">Về việc</sd:Label></td>
                <td width="90%" >
                    <div  id="createForm.titleEdit"> ${fn:escapeXml(createForm.titleEdit)} </div>
            </td>
        </tr>
        <tr>
            <td width="10%" ><sd:Label required="true">Nội dung sửa đổi</sd:Label></td>
                <td width="90%" >
                    <div  id="createForm.contentsEdit"> ${fn:escapeXml(createForm.contentsEdit)} </div>
            </td>
        </tr>
        <tr>
            <td width="10%"><sd:Label required="true">Ghi chú</sd:Label></td>
                <td width="90%">
                    <div  id="createForm.noteEdit"> ${fn:escapeXml(createForm.noteEdit)} </div>
            </td>
        </tr>
    </table>
</sd:FieldSet>
