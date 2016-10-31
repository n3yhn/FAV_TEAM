<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="tags" tagdir="/WEB-INF/tags" %>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<sd:FieldSet key="Thông tin doanh nghiệp">
    <table class="editTable">
        <tr>
            <td width="25%"><sd:Label required="true">Tên tổ chức, cá nhân</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.businessName"
                            name="createForm.announcement.businessName"
                            readonly="true"  maxlength="255" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label required="true">Địa chỉ</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.businessAddress"
                            name="createForm.announcement.businessAddress"
                            readonly="true"  maxlength="500" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>

            <td width="25%" ><sd:Label required="true">Điện thoại</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.businessTelephone"
                            name="createForm.announcement.businessTelephone"
                            readonly="true"  maxlength="20" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Fax</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.businessFax"
                            name="createForm.announcement.businessFax"
                            readonly="true"  maxlength="20" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Email</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.businessEmail"
                            name="createForm.announcement.businessEmail"
                            readonly="true"  maxlength="50" cssStyle="width:99%" trim="true"/>
            </td>
        </tr> 
    </table>
</sd:FieldSet>
<br/>
<sd:FieldSet key="Thông tin hồ sơ">
    <table class="editTable">
        <tr>
            <td width="25%"><sd:Label required="true">Mã hồ sơ gốc</sd:Label></td>
                <td width="25%">
                <sd:TextBox key=""
                            id="createForm.fileSourceCode"
                            name="createForm.fileSourceCode"
                            readonly="true" maxlength="255" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label required="true">Tên sản phẩm</sd:Label></td>
                <td width="25%">
                <sd:TextBox key=""
                            id="createForm.announcement.productName"
                            name="createForm.announcement.productName" 
                            readonly="true" maxlength="500" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label required="true">Số bản công bố</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.announcementNo"
                            name="createForm.announcement.announcementNo"
                            maxlength="50"  trim="true" value="${fn:escapeXml(createForm.announcement.announcementNo)}"/>
                <sd:TextBox key="" id="createForm.announcement.announcementId"
                            name="createForm.announcement.announcementId" 
                            readonly="true"  cssStyle="display:none"/>
            </td>
            <td width="25%"><sd:Label>Ngày công bố</sd:Label></td>
                <td width="25%">
                <sx:DatePicker key="" id="createForm.announcement.publishDate"
                               name="createForm.announcement.publishDate" 
                               format="dd/MM/yyyy" cssStyle="width:99%"/>
            </td>

        </tr>
        <tr>
            <td width="25%"><sd:Label required="true">Phù hợp với QCKT/QĐATTP</sd:Label></td>
                <td width="25%">
                <tags:MutipleSelect  id="createForm.announcement.matchingTarget"
                                     name="createForm.announcement.matchingTarget"
                                     data="${lstStandard}"  allowCode="false" /> 

            </td>
            <td width="25%"><sd:Label>Người ký</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.signer"
                            name="createForm.announcement.signer" 
                            maxlength="255" cssStyle="width:99%" trim="true" htmlAttributes="title='Tên người ký giấy'"/>
            </td>
        </tr>
    </table>
</sd:FieldSet>
<br/>
<sd:FieldSet key="Nội dung sửa đổi - bổ sung">
    <table class="editTable">
        <tr>
            <td width="10%" ><sd:Label required="true">Về việc</sd:Label></td>
                <td width="90%" >
                <sd:TextBox key="" id="createForm.titleEdit" name="createForm.titleEdit"  cssStyle="width:99%;margin:auto;" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="10%" ><sd:Label required="true">Nội dung sửa đổi</sd:Label></td>
                <td width="90%" >
                <sd:Textarea key="" id="createForm.contentsEdit" name="createForm.contentsEdit"  cssStyle="width:99%;margin:auto;" trim="true" rows="20"/>
            </td>
        </tr>
        <tr>
            <td width="10%"><sd:Label required="true">Ghi chú</sd:Label></td>
                <td width="90%">
                <sd:Textarea key="" id="createForm.noteEdit" name="createForm.noteEdit"  cssStyle="width:99%;margin:auto;" trim="true" rows="10"/>
            </td>
        </tr>
    </table>
</sd:FieldSet>
<script type="text/javascript">
    page.onFillData = function () {
        dijit.byId("createForm.announcement.manufactureName").setValue(dijit.byId("createForm.announcement.businessName").getValue());
        dijit.byId("createForm.announcement.manufactureTel").setValue(dijit.byId("createForm.announcement.businessTelephone").getValue());
        dijit.byId("createForm.announcement.manufactureAddress").setValue(dijit.byId("createForm.announcement.businessAddress").getValue());
        dijit.byId("createForm.announcement.manufactureFax").setValue(dijit.byId("createForm.announcement.businessFax").getValue());
        dijit.byId("createForm.announcement.manufactureEmail").setValue(dijit.byId("createForm.announcement.businessEmail").getValue());
    };
    page.validateAnnouncementAfterEdit = function () {
        if (!dijit.byId("createForm.titleEdit").getValue()) {
            alert("[Về việc] chưa nhập");
            dijit.byId("createForm.titleEdit").focus();
            return false;
        }
        if (!dijit.byId("createForm.contentsEdit").getValue()) {
            alert("[Nội dung sửa đổi] chưa nhập");
            dijit.byId("createForm.contentsEdit").focus();
            return false;
        }
        return true;
    }

</script>