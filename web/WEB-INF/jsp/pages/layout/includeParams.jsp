<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Locale"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    ResourceBundle rb = ResourceBundle.getBundle("config");
    request.setAttribute("RDWFisCryptParameter", rb.getString("RDWF.isCryptParameter"));
%>
<script type="text/javascript">
    sd.operator.keyString = "${fn:escapeXml(keyString)}";
    sd.operator.ivString = "${fn:escapeXml(ivString)}";
    sd.operator.isCryptParameter = ${fn:escapeXml(RDWFisCryptParameter)};
</script>