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
        <td width="25%"><sd:Label required="true">Số công văn</sd:Label><font style="color:red">*</font></td>
            <td width="25%">
            <sd:TextBox key="" id="createForm.reIssueForm.documentNo" name="createForm.reIssueForm.documentNo" maxlength="50" cssStyle="width:98%" />
            <sd:TextBox key="" id="createForm.reIssueForm.reIssueFormId" name="createForm.reIssueForm.reIssueFormId" cssStyle="display:none"/>
        </td>
        <td width="25%"><sd:Label required="true">Ngày ban hành</sd:Label><font style="color:red">*</font></td>
        <td width="25%">
            <sx:DatePicker key="" id="createForm.reIssueForm.documentDate" name="createForm.reIssueForm.documentDate" cssStyle="width:98%" format="dd/MM/yyyy"/>
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label required="true">Tên tổ chức, cá nhân</sd:Label><font style="color:red">*</font></td>
            <td width="25%">
            <sd:TextBox key="" id="createForm.reIssueForm.businessName" name="createForm.reIssueForm.businessName" maxlength="250" cssStyle="width:98%" />
        </td>
        <td width="25%" ><sd:Label required="true">Cơ quan cấp GTN hoặc GXN</sd:Label><font style="color:red">*</font></td>
            <td width="25%">
            <sd:TextBox key="" id="createForm.reIssueForm.issueAgency" name="createForm.reIssueForm.issueAgency" maxlength="250" cssStyle="width:98%" />
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label>Số cấp Giấy Tiếp nhận bản CBHQ</sd:Label></td>
            <td width="25%">
            <sd:TextBox key="" id="createForm.reIssueForm.formNumber" name="createForm.reIssueForm.formNumber" maxlength="50" cssStyle="width:98%"/>
        </td>
        <td width="25%"><sd:Label>Ngày cấp</sd:Label></td>
        <td width="25%">
            <sx:DatePicker key="" id="createForm.reIssueForm.issueDate" name="createForm.reIssueForm.issueDate" format="dd/MM/yyyy" cssStyle="width:98%"/>
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label required="true">Cơ quan cấp</sd:Label></td>
            <td width="25%">
            <sd:TextBox key="" id="createForm.reIssueForm.reIssueAgency" name="createForm.reIssueForm.reIssueAgency" maxlength="250" cssStyle="width:98%" />
        </td>
        <td width="25%"><sd:Label required="true">Ngày ký</sd:Label></td>
        <td width="25%">
            <sx:DatePicker key="" id="createForm.reIssueForm.reIssueDate" name="createForm.reIssueForm.reIssueDate" format="dd/MM/yyyy" cssStyle="width:98%" />
        </td>
    </tr>    
</table>
<script type="text/javascript">
    page.validateReIssueForm = function(){
        var tabs = dijit.byId("files_tab");
        var panel = dijit.byId("tab.annoucement");
        tabs.selectChild(panel);        
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
    };
</script>