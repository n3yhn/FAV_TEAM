<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<table class="viewTable">
    <tr>
        <td width="25%"><sd:Label required="true">Tên tổ chức, cá nhân</sd:Label></td>
            <td width="25%">
                <div id="createForm.reIssueForm.businessName"> ${fn:escapeXml(createForm.reIssueForm.businessName)}</div>
        </td>
        <td width="25%"><sd:Label required="true">Số ĐKKD/Số CMT</sd:Label></td>
            <td width="25%">
            <div id="createForm.reIssueForm.identificationNumber"> ${fn:escapeXml(createForm.reIssueForm.identificationNumber)} </div>
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label required="true">Địa chỉ</sd:Label></td>
            <td width="25%">
            <div id="createForm.reIssueForm.address"> ${fn:escapeXml(createForm.reIssueForm.address)} </div>
        </td>
        <td width="25%"><sd:Label required="true">Email</sd:Label></td>
        <td width="25%">
            <div id="createForm.reIssueForm.email"> ${fn:escapeXml(createForm.reIssueForm.email)} </div>
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label>Điện thoại</sd:Label></td>
            <td width="25%">
            <div id="createForm.reIssueForm.telephone"> ${fn:escapeXml(createForm.reIssueForm.telephone)} </div>
        </td>
        <td width="25%"><sd:Label>Fax</sd:Label></td>
            <td width="25%">
            <div id="createForm.reIssueForm.fax"> ${fn:escapeXml(createForm.reIssueForm.fax)} </div>
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label required="true">Số ký hiệu giấy xác nhận</sd:Label></td>
            <td width="25%">
            <div id="createForm.reIssueForm.formNumber"> ${fn:escapeXml(createForm.reIssueForm.formNumber)} </div>
        </td>
        <td colspan="2"/>
    </tr>    
    <tr>
        <td width="25%"><sd:Label required="true">Cơ quan cấp</sd:Label></td>
            <td width="25%">
            <div id="createForm.reIssueForm.issueAgency"> ${fn:escapeXml(createForm.reIssueForm.issueAgency)} </div>
        </td>
        <td width="25%"><sd:Label required="true">Ngày ký</sd:Label></td>
            <td width="25%">
            <div id="createForm.reIssueForm.signDate"> ${fn:escapeXml(createForm.reIssueForm.signDateStr)} </div>
        </td>
    </tr>    
    <tr>
        <td width="25%"><sd:Label required="true">Mã hồ sơ cấp GXN gần nhất (nếu có)</sd:Label></td>
            <td width="25%">
            <div id="createForm.reIssueForm.nearestFileNo"> ${fn:escapeXml(createForm.reIssueForm.nearestFileNo)} </div>
        </td>
        <td colspan="2"/>
    </tr>    
</table>
<script type="text/javascript">
    page.validateReIssueForm = function(){
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