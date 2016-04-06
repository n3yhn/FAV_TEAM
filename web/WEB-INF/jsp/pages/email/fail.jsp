<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils" %>   
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../util/util_js.jsp"/>
<jsp:include page="../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>



<div id="token">
    <s:token id="tokenId"/>
</div> 
<div>Không thể đăng nhập</div>
