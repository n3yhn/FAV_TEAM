<%-- 
    Document   : importForm
    Created on : Jan 4, 2010, 10:29:59 AM
    Author     : duongtb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<s:form id="frmImport" action="ApplicationsAction!onImport.do" theme="simple" method="POST" enctype="multipart/form-data">
    <s:file id="client" name="client" size="60" cssStyle="border:1px solid;border-color:red;" />
    <sd:Button key="" id="btnUpload" onclick="page.onImport()" >
        <img src="${contextPath}/share/images/icons/attach.png" height="20" width="20">
         <span style="font-size:12px"><sd:Property>btnUpload</sd:Property></span>
    </sd:Button>
</s:form>
         <div style="text-align:center;">
            <a href="${contextPath}/share/exportResult.jsp" target="_self" id="link"><sd:Property>js.alertDownload</sd:Property></a>
        </div>
<iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
