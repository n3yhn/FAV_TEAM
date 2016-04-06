<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<jsp:include page="../common/commonJavascript.jsp"/>
<jsp:include page="includeParams.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>



<script type="text/javascript">
    page.gotoHome = function(){
        sd.operator.waitScreenDivId = "vt-loading-background";
        eval(firstMenuItem.toString());
        
    }
    dojo.ready(page.gotoHome);
</script>


