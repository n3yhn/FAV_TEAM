<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<jsp:include page="/WEB-INF/jsp/pages/config/alertDialog.jsp"/>

<!-- [ Query section -->
<jsp:include page="unitTreeCatalog-QueryForm.jsp"/>
<!-- ] Query section -->

<!-- [ Query section -->
<jsp:include page="unitTreeCatalog-JS.jsp"/>
<!-- ] Query section -->

<sd:Dialog id="dialogId" key="dialog.title">
    <div id="dialogDiv">
        <jsp:include page="unitTreeCatalog-Dialog.jsp"/>
    </div>
</sd:Dialog>

<sd:Dialog id="dialogIdDept" key="dialog.title" height="400" width="300">
    <div id="dialogDivDept">
        <jsp:include page="unitTreeCatalog-Dialog_1.jsp"/>
    </div>
</sd:Dialog>

<sd:Dialog id="dialogIdUser" key="dialog.title" height="600" width="800">
    <div id="dialogDivUser">
        <jsp:include page="unitTreeCatalog-Dialog_1_1.jsp"/>
    </div>
</sd:Dialog>