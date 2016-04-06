<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<jsp:include page="../common/commonJavascript.jsp"/>
<jsp:include page="includeParams.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<div id="homepage-myLeftPanel" class="float-left" style="width:100%; padding-right: 0px; border-right: 0px solid #a9a9a9; ">
</div>

<div id="homepage-myRightPanel" class="float-right" style="width:75%; ">
</div>


<script type="text/javascript">
    page.gotoHome = function(){
        sd.operator.waitScreenDivId = "vt-loading-background";
        //eval(firstMenuItem.toString());
        sd.connector.post("home.do", "homepage-myLeftPanel", null, null);
        
    };
    dojo.ready(page.gotoHome);
</script>


