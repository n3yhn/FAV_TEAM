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

            <td width="25%"><sd:Label required="true">Tên tổ chức, cá nhân</sd:Label><font style="color:red">*</font></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.businessName" name="createForm.announcement.businessName" maxlength="255" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label >Số đăng ký kinh doanh/số CMT</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.businessLicence" name="createForm.announcement.businessLicence" maxlength="50" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label required="true">Địa chỉ</sd:Label><font style="color:red">*</font></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.businessAddress" name="createForm.announcement.businessAddress" maxlength="500" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%" ><sd:Label required="true">Điện thoại</sd:Label><font style="color:red">*</font></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.businessTelephone" name="createForm.announcement.businessTelephone" maxlength="20" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Fax</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.businessFax" name="createForm.announcement.businessFax" maxlength="20" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Email</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.businessEmail" name="createForm.announcement.businessEmail" maxlength="50" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label required="true">Tên sản phẩm</sd:Label><font style="color:red">*</font></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.productName" name="createForm.announcement.productName" maxlength="500" cssStyle="width:99%" required="true" trim="true"/>
            </td>
            <td colspan="2"/>
        </tr>    
    </table>
</sd:FieldSet>
<sd:Button id="btnAutoFill" key="" onclick="page.onFillData();" cssClass="buttonGroup" cssStyle="display:">
    <img src="${contextPath}/share/images/icons/assign.jpg" height="14" width="18">
    <span style="font-size:12px">Sao chép</span>
</sd:Button>
<br/>
<sd:FieldSet key="Xuất xứ">
    <table class="editTable">
        <tr>
            <td width="25%"><sd:Label required="true">Tên nhà sản xuất</sd:Label><font style="color:red">*</font></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.manufactureName" name="createForm.announcement.manufactureName" maxlength="255" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label required="true">Địa chỉ</sd:Label><font style="color:red">*</font></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.manufactureAddress" name="createForm.announcement.manufactureAddress" maxlength="255" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Điện thoại</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.manufactureTel" name="createForm.announcement.manufactureTel" maxlength="20" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Fax</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.manufactureFax" name="createForm.announcement.manufactureFax" maxlength="20" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Email</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.manufactureEmail" name="createForm.announcement.manufactureEmail" maxlength="50" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Tên nước xuất xứ</sd:Label><font style="color:red">*</font></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.nationName" name="createForm.announcement.nationName" maxlength="255" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Tên công ty xuất khẩu</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.nationCompanyName" name="createForm.announcement.nationCompanyName" maxlength="255" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Địa chỉ công ty xuất khẩu</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.nationCompanyAddress" name="createForm.announcement.nationCompanyAddress" maxlength="500" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
    </table>

</sd:FieldSet>
<br/>
<sd:FieldSet key="Thông tin công bố">
    <table class="editTable">
        <tr>
            <td width="25%"><sd:Label required="true">Số bản công bố</sd:Label><font style="color:red">*</font></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.announcementNo" name="createForm.announcement.announcementNo" maxlength="50" trim="true" value="${fn:escapeXml(createForm.announcement.announcementNo)}"/>
                <sd:TextBox key="" id="createForm.announcement.announcementId" name="createForm.announcement.announcementId" cssStyle="display:none"/>
            </td>
            <td width="25%"><sd:Label>Ngày công bố</sd:Label></td>
                <td width="25%">
                <sx:DatePicker key="" id="createForm.announcement.publishDate" name="createForm.announcement.publishDate" format="dd/MM/yyyy" cssStyle="width:99%"/>
            </td>


        </tr>
        <tr>
            <!-- Xóa phương thức đánh giá phù hợp binhnt53 createForm.announcement.assessmentMethod-->
            <td width="25%"><sd:Label required="true">Phù hợp với QCKT/QĐATTP</sd:Label><font style="color:red">*</font></td>
                <td width="25%">
                    <!-- <sd:Textarea key="" 
                             id="createForm.announcement.matchingTarget" 
                             name="createForm.announcement.matchingTarget" 
                             maxlength="2000" 
                             cssStyle="width:99%"
                             trim="true"/> -->

                <tags:MutipleSelect disabled="false" id="createForm.announcement.matchingTarget" name="createForm.announcement.matchingTarget" data="${lstStandard}"  allowCode="false" />
            </td>
            <td width="25%"><sd:Label>Người ký</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.announcement.signer" name="createForm.announcement.signer" maxlength="255" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
    </table>
</sd:FieldSet> 

<script type="text/javascript">
    page.validateAnnouncement = function() {
        var tabs = dijit.byId("files_tab");
        var panel = dijit.byId("tab.annoucement");
        tabs.selectChild(panel);
        if (!dijit.byId("createForm.announcement.announcementNo").getValue()) {
            alert("[Số bản công bố] chưa nhập");
            dijit.byId("createForm.announcement.announcementNo").focus();
            return false;
        }
        if (!dijit.byId("createForm.announcement.businessName").getValue()) {
            alert("[Tên tổ chức, cá nhân] chưa nhập");
            dijit.byId("createForm.announcement.businessName").focus();
            return false;
        }
        if (!dijit.byId("createForm.announcement.businessAddress").getValue()) {
            alert("[Thông tin doanh nghiệp : địa chỉ] chưa nhập");
            dijit.byId("createForm.announcement.businessAddress").focus();
            return false;
        }
        if (!dijit.byId("createForm.announcement.businessTelephone").getValue()) {
            alert("[Thông tin doanh nghiệp : điện thoại] chưa nhập");
            dijit.byId("createForm.announcement.businessTelephone").focus();
            return false;
        } else {
            var content = dijit.byId("createForm.announcement.businessTelephone").getValue();
            if (!validatePhone(content)) {
                alert("[Thông tin doanh nghiệp : điện thoại] không đúng định dạng");
                dijit.byId("createForm.announcement.businessTelephone").focus();
                return false;
            }
        }
        if (dijit.byId("createForm.announcement.businessFax").getValue()) {
            var content = dijit.byId("createForm.announcement.businessFax").getValue();
            if (!validatePhone(content)) {
                alert("[Thông tin doanh nghiệp : Fax] không đúng định dạng");
                dijit.byId("createForm.announcement.businessFax").focus();
                return false;
            }
        }
        if (dijit.byId("createForm.announcement.businessEmail").getValue()) {
            var content = dijit.byId("createForm.announcement.businessEmail").getValue();
            if (!validateEmail(content)) {
                alert("[Thông tin doanh nghiệp : Email] không đúng định dạng");
                dijit.byId("createForm.announcement.businessEmail").focus();
                return false;
            }
        }
        if (!dijit.byId("createForm.announcement.productName").getValue()) {
            alert("[Tên sản phẩm] chưa nhập");
            dijit.byId("createForm.announcement.productName").focus();
            return false;
        }
        if (!dijit.byId("createForm.announcement.manufactureName").getValue()) {
            alert("[Tên nhà sản xuất] chưa nhập");
            dijit.byId("createForm.announcement.manufactureName").focus();
            return false;
        }
        if (!dijit.byId("createForm.announcement.manufactureAddress").getValue()) {
            alert("[Xuất xứ : địa chỉ] chưa nhập");
            dijit.byId("createForm.announcement.manufactureAddress").focus();
            return false;
        }
        if (!dijit.byId("createForm.announcement.nationName").getValue()) {
            alert("[Xuất xứ : Tên nước xuất xứ] chưa nhập");
            dijit.byId("createForm.announcement.nationName").focus();
            return false;
        }
        if (dijit.byId("createForm.announcement.manufactureTel").getValue()) {
            var content = dijit.byId("createForm.announcement.manufactureTel").getValue();
            if (!validatePhone(content)) {
                alert("[Xuất xứ : điện thoại] không đúng định dạng");
                dijit.byId("createForm.announcement.manufactureTel").focus();
                return false;
            }
        }
        if (dijit.byId("createForm.announcement.manufactureFax").getValue()) {
            var content = dijit.byId("createForm.announcement.manufactureFax").getValue();
            if (!validatePhone(content)) {
                alert("[Xuất xứ : Fax] không đúng định dạng");
                dijit.byId("createForm.announcement.manufactureFax").focus();
                return false;
            }
        }
        if (dijit.byId("createForm.announcement.manufactureEmail").getValue()) {
            var content = dijit.byId("createForm.announcement.manufactureEmail").getValue();
            if (!validateEmail(content)) {
                alert("[Xuất xứ : Email] không đúng định dạng");
                dijit.byId("createForm.announcement.manufactureEmail").focus();
                return false;
            }
        }
        if (!dijit.byId("createForm.announcement.matchingTarget").getValue()) {
            alert("[Phù hợp với QCKT/QĐATTP] chưa nhập");
            dijit.byId("createForm.announcement.matchingTarget").focus();
            return false;
        }
        return true;
    };
    page.onFillData = function() {
        dijit.byId("createForm.announcement.manufactureName").setValue(dijit.byId("createForm.announcement.businessName").getValue());
        dijit.byId("createForm.announcement.manufactureTel").setValue(dijit.byId("createForm.announcement.businessTelephone").getValue());
        dijit.byId("createForm.announcement.manufactureAddress").setValue(dijit.byId("createForm.announcement.businessAddress").getValue());
        dijit.byId("createForm.announcement.manufactureFax").setValue(dijit.byId("createForm.announcement.businessFax").getValue());
        dijit.byId("createForm.announcement.manufactureEmail").setValue(dijit.byId("createForm.announcement.businessEmail").getValue());
    };
</script>