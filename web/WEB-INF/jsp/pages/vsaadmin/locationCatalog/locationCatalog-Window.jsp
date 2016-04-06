<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<jsp:include page="/WEB-INF/jsp/pages/config/alertDialog.jsp"/>

<!-- [ Query section -->
<jsp:include page="locationCatalog-QueryForm.jsp"/>
<!-- ] Query section -->

<!-- [ Query section -->
<jsp:include page="locationCatalog-JS.jsp"/>
<!-- ] Query section -->

<sd:Dialog width="70%" id="dialogId" key="dialog.title">
    <div id="dialogDiv">
        <jsp:include page="locationCatalog-Dialog.jsp"/>
    </div>
</sd:Dialog>