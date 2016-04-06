<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<!-- [ Query section -->
<jsp:include page="/WEB-INF/jsp/pages/config/alertDialog.jsp"/>
<jsp:include page="adCatalog-JS.jsp"/>
<!-- ] Query section -->

<!-- [ Query section -->
<jsp:include page="adCatalog-QueryForm.jsp"/>
<!-- ] Query section -->
<sd:Dialog width="80%" id="dialogId" key="dialog.title">
    <div id="dialogDiv">
        <jsp:include page="adCatalog-Dialog.jsp" />
    </div>
</sd:Dialog>
<sd:Dialog width="60%" id="dlgId" key="dialog.title">
    <div id="dlgDiv">
    </div>
</sd:Dialog>
<sd:Dialog width="50%" id="adDialogMsg" key="">
    <div id="adMsg">
    </div>
</sd:Dialog>