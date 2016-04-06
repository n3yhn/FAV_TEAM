<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="messageContainer" />
<script>
    var result='${fn:escapeXml(result)}';
    page.getReportResult=function(){
        return result;
    }
    <c:if test="${not empty downloadLinkPath}">
        window.location = '${fn:escapeXml(downloadLinkPath)}';
        /*
        var downloadLinkPath = '${fn:escapeXml(downloadLinkPath)}';
        sd.connector.post("reportUtil!deleteFile.do?downloadLinkPath=" + downloadLinkPath,null,null,null);
        */
    </c:if>
</script>
