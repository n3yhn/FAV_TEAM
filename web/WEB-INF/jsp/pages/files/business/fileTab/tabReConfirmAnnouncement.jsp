<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<table class="editTable">
    <tr>
        <td width="25%"><sd:Label required="true">Tên tổ chức, cá nhân</sd:Label><font style="color:red">*</font></td>
            <td width="25%">
            <sd:TextBox key="" id="createForm.reIssueForm.businessName" name="createForm.reIssueForm.businessName" maxlength="200" cssStyle="width:98%" trim="true"/>
            <sd:TextBox key="" id="createForm.reIssueForm.reIssueFormId" name="createForm.reIssueForm.reIssueFormId" cssStyle="display:none"/>
        </td>
        <td width="25%"><sd:Label required="true">Số đăng ký kinh doanh/Số chứng minh thư</sd:Label><font style="color:red">*</font></td>
            <td width="25%">
            <sd:TextBox key="" id="createForm.reIssueForm.identificationNumber" name="createForm.reIssueForm.identificationNumber" maxlength="20" cssStyle="width:98%" trim="true"/>
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label required="true">Địa chỉ</sd:Label><font style="color:red">*</font></td>
            <td width="25%">
            <sd:TextBox key="" id="createForm.reIssueForm.address" name="createForm.reIssueForm.address" maxlength="250" cssStyle="width:98%" trim="true"/>
        </td>
        <td width="25%"><sd:Label required="true">Email</sd:Label><font style="color:red">*</font></td>
        <td width="25%">
        <sd:TextBox key="" id="createForm.reIssueForm.email" name="createForm.reIssueForm.email" cssStyle="width:98%" trim="true" maxlength="100"/>
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label>Điện thoại</sd:Label></td>
            <td width="25%">
            <sd:TextBox key="" id="createForm.reIssueForm.telephone" name="createForm.reIssueForm.telephone" maxlength="15" cssStyle="width:98%" trim="true"/>
        </td>
        <td width="25%"><sd:Label>Fax</sd:Label></td>
            <td width="25%">
            <sd:TextBox key="" id="createForm.reIssueForm.fax" name="createForm.reIssueForm.fax" maxlength="15" cssStyle="width:98%" trim="true"/>
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label required="true">Số ký hiệu giấy xác nhận</sd:Label></td>
            <td width="25%">
            <sd:TextBox key="" id="createForm.reIssueForm.formNumber" name="createForm.reIssueForm.formNumber" maxlength="20" cssStyle="width:98%" trim="true"/>
        </td>
        <td colspan="2"/>
    </tr>    
    <tr>
        <td width="25%"><sd:Label required="true">Cơ quan cấp</sd:Label></td>
            <td width="25%">
            <sd:TextBox key="" id="createForm.reIssueForm.issueAgency" name="createForm.reIssueForm.issueAgency" maxlength="250" cssStyle="width:98%" trim="true"/>
        </td>
        <td width="25%"><sd:Label required="true">Ngày ký</sd:Label></td>
            <td width="25%">
            <sx:DatePicker key="" id="createForm.reIssueForm.signDate" name="createForm.reIssueForm.signDate" format="dd/MM/yyyy" cssStyle="width:98%"/>
        </td>
    </tr>    
    <tr>
        <td width="25%"><sd:Label required="true">Mã hồ sơ cấp Giấy xác nhận gần nhất (nếu có)</sd:Label></td>
            <td width="25%">
            <sd:TextBox key="" id="createForm.reIssueForm.nearestFileNo" name="createForm.reIssueForm.nearestFileNo" maxlength="20" cssStyle="width:98%" trim="true"/>
        </td>
        <td colspan="2"/>
    </tr>    
</table>
<script type="text/javascript">
    page.validateReIssueForm = function(){
        var tabs = dijit.byId("files_tab");
        var panel = dijit.byId("tab.annoucement");
        tabs.selectChild(panel);        
        if(!dijit.byId("createForm.reIssueForm.businessName").getValue()){
            alert("[Tên tổ chức cá nhân] chưa nhập");
            dijit.byId("createForm.reIssueForm.businessName").focus();
            return false;       
        }
        if(!dijit.byId("createForm.reIssueForm.identificationNumber").getValue()){
            alert("[Số đăng ký kinh doanh/số chứng minh thư] chưa nhập");
            dijit.byId("createForm.reIssueForm.identificationNumber").focus();
            return false;       
        }
        if(!dijit.byId("createForm.reIssueForm.address").getValue()){
            alert("[Địa chỉ] chưa nhập");
            dijit.byId("createForm.reIssueForm.address").focus();
            return false;       
        }
        if(!dijit.byId("createForm.reIssueForm.email").getValue()){
            alert("[Email] chưa nhập");
            dijit.byId("createForm.reIssueForm.email").focus();
            return false;       
        } else {
            if(!sd.validator.isEmail(dijit.byId("createForm.reIssueForm.email").getValue())){
                alert("[Email] không đúng định dạng");
                dijit.byId("createForm.reIssueForm.email").focus();
                return false;       
            }
        }
        if(dijit.byId("createForm.reIssueForm.telephone").getValue()){
            if(!validatePhone(dijit.byId("createForm.reIssueForm.telephone").getValue())){
                alert("[Số điện thoại] không đúng định dạng");
                dijit.byId("createForm.reIssueForm.telephone").focus();
                return false;       
            }
        }
        if(dijit.byId("createForm.reIssueForm.fax").getValue()){
            if(!validatePhone(dijit.byId("createForm.reIssueForm.telephone").getValue())){
                alert("[Fax] không đúng định dạng");
                dijit.byId("createForm.reIssueForm.fax").focus();
                return false;       
            }
        }
        return true;       
    }
</script>