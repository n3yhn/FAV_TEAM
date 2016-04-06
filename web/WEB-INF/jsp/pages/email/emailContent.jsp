<%-- 
    Document   : viewPage
    Created on : Jun 4, 2012, 10:53:57 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/jsp/pages/common/commonJavascript.jsp"/>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>


<table >
    <tr>
        <td >
                ${fn:escapeXml(messageForm.content)} 
        </td>
    </tr>
</table>

