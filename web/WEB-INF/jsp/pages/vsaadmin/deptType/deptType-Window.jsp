<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<link rel="stylesheet" href="${contextPath}/share/css/formStyle.css" charset="UTF-8" type="text/css" />
<link rel="stylesheet" href="${contextPath}/share/css/layout.css" charset="UTF-8" type="text/css" />

<jsp:include page="/WEB-INF/jsp/pages/config/alertDialog.jsp"/>
<!-- [ Query section -->
<jsp:include page="deptType-JS.jsp"/>
<!-- ] Query section -->

<!-- [ Query section -->
<jsp:include page="deptType-QueryForm.jsp"/>
<!-- ] Query section -->

<sd:Dialog id="deptTypeDialogId" key="dialog.title" width="60%">
    <div id="deptTypeDialogDiv">
        <jsp:include page="deptType-Dialog.jsp"/>
    </div>
</sd:Dialog>