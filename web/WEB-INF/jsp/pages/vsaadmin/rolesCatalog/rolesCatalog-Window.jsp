<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<div id="token">
    <s:token id="tokenId"/>
</div>
<!-- [ Query section -->
<jsp:include page="rolesCatalog-QueryForm.jsp"/>
<!-- ] Query section -->

<!-- [ Query section -->
<jsp:include page="rolesCatalog-JS.jsp"/>
<!-- ] Query section -->

<!-- Dialog them/sua [roles] -->
<sd:Dialog id="dialogId" key="dialog.title" height="auto">
        <jsp:include page="rolesCatalog-Dialog.jsp"/>
</sd:Dialog>

<!-- Dialog cac chuc nang cua roles -->
<sd:Dialog id="roleObjectDialogId" key="dialog.title" width="900px">
    <div id="divRoleObject" style="overflow: auto;max-height: 500px;position: relative;">
        <jsp:include page="rolesObject-Dialog.jsp"/>
    </div>
</sd:Dialog>

<!-- Dialog them [object] -->
<sd:Dialog id="objectsDialogId"
           key="dialog.title"
           width="900px"
           showCloseButton="true" showFullscreenButton="true">
    <div id="divObject" style="overflow: auto;max-height: 500px;position: relative;">
        <jsp:include page="objectsCatalog-Dialog.jsp"/>
    </div>
</sd:Dialog>

<jsp:include page="/WEB-INF/jsp/pages/vsaadmin/config/alertDialog.jsp"/>