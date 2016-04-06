<%-- 
    Document   : callbackTarget
    Created on : Mar 8, 2011, 8:35:09 AM
    Author     : cn_longh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
    </head>
    <body>
        <%request.setAttribute("contextPath", request.getContextPath());%>
        <script>
            parent.uploadNewRowSh("${fn:escapeXml(fileName)}", "${fn:escapeXml(attachId)}", "${fn:escapeXml(id)}");
//            parent.page.callbackAfterUpload("${fn:escapeXml(attachId)}");
        </script>
    </body>
</html>

