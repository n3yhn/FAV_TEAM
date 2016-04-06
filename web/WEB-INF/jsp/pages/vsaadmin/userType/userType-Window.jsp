<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<link rel="stylesheet" href="${contextPath}/share/css/formStyle.css" charset="UTF-8" type="text/css" />
<link rel="stylesheet" href="${contextPath}/share/css/layout.css" charset="UTF-8" type="text/css" />

<jsp:include page="/WEB-INF/jsp/pages/config/alertDialog.jsp"/>

<!-- [ Query section -->
<jsp:include page="userType-JS.jsp"/>
<!-- ] Query section -->

<!-- [ Query section -->
<jsp:include page="userType-QueryForm.jsp"/>
<!-- ] Query section -->

<sd:Dialog id="dialogId" key="dialog.title" width="60%">
    <div id="dialogDiv">
        <jsp:include page="userType-Dialog.jsp"/>
    </div>
</sd:Dialog>