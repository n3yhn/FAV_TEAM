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
        <td width="25%"><sd:Label required="true">Số công văn</sd:Label></td>
        <td width="25%">
            <div id="createForm.reIssueForm.documentNo"> ${fn:escapeXml(createForm.reIssueForm.documentNo)}</div>
        </td>
        <td width="25%"><sd:Label required="true">Ngày ban hành</sd:Label></td>
            <td width="25%">
                <div id="createForm.reIssueForm.documentDate"> ${fn:escapeXml(createForm.reIssueForm.documentDateStr)} </div>
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label required="true">Tên tổ chức, cá nhân</sd:Label></td>
            <td width="25%">
                <div id="createForm.reIssueForm.businessName"> ${fn:escapeXml(createForm.reIssueForm.businessName)} </div>
        </td>
        <td width="25%" ><sd:Label required="true">Cơ quan cấp GTN hoặc GXN</sd:Label></td>
            <td width="25%">
                <div id="createForm.reIssueForm.issueAgency"> ${fn:escapeXml(createForm.reIssueForm.issueAgency)}</div>
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label>Số cấp Giấy Tiếp nhận bản CBHQ</sd:Label></td>
            <td width="25%">
                <div id="createForm.reIssueForm.formNumber"> ${fn:escapeXml(createForm.reIssueForm.formNumber)}</div>
        </td>
        <td width="25%"><sd:Label>Ngày cấp</sd:Label></td>
            <td width="25%">
                <div id="createForm.reIssueForm.issueDate"> ${fn:escapeXml(createForm.reIssueForm.issueDateStr)}</div>
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label required="true">Cơ quan cấp</sd:Label></td>
            <td width="25%">
                <div id="createForm.reIssueForm.reIssueAgency"> ${fn:escapeXml(createForm.reIssueForm.reIssueAgency)} </div>
        </td>
        <td width="25%"><sd:Label required="true">Ngày ký</sd:Label></td>
            <td width="25%">
                <div id="createForm.reIssueForm.reIssueDate"> ${fn:escapeXml(createForm.reIssueForm.reIssueDateStr)}</div>
        </td>
    </tr>    
</table>
<script type="text/javascript">
    page.validateReIssueForm = function(){
        if(!dijit.byId("createForm.reIssueForm.documentNo").getValue()){
            alert("[Số công văn] chưa nhập");
            dijit.byId("createForm.reIssueForm.documentNo").focus();
            return false;       
        }
        if(!dijit.byId("createForm.reIssueForm.documentDate").getValue()){
            alert("[Ngày ban hành] chưa nhập");
            dijit.byId("createForm.reIssueForm.documentDate").focus();
            return false;       
        }
        if(!dijit.byId("createForm.reIssueForm.businessName").getValue()){
            alert("[Tên tổ chức, cá nhân] chưa nhập");
            dijit.byId("createForm.reIssueForm.businessName").focus();
            return false;       
        }
        if(!dijit.byId("createForm.reIssueForm.issueAgency").getValue()){
            alert("[Tên cơ quan cấp giấy tiếp nhận hoặc giấy xác nhận] chưa nhập");
            dijit.byId("createForm.reIssueForm.issueAgency").focus();
            return false;       
        } 
        return true;       
    }
</script>