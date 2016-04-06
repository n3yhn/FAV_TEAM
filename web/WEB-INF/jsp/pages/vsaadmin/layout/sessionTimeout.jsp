<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.net.URLEncoder"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%
    Locale locale = new Locale("en","US");
    ResourceBundle bundle = ResourceBundle.getBundle("cas",locale);
    request.setAttribute("logoutUrl", bundle.getString("logoutUrl") + "?service=" + URLEncoder.encode(bundle.getString("service"), "UTF-8"));
%>
<script type="text/javascript">
    parent.document.location.href = "${fn:escapeXml(logoutUrl)}";
</script>