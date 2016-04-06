<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<!-- [ Query section -->
<jsp:include page="/WEB-INF/jsp/pages/config/alertDialog.jsp"/>
<jsp:include page="position-JS.jsp"/>
<!-- ] Query section -->

<!-- [ Query section -->
<jsp:include page="position-QueryForm.jsp"/>
<!-- ] Query section -->

<sd:Dialog width="70%" id="dialogId" key="dialog.title">
    <div id="dialogDiv">
        <jsp:include page="position-Dialog.jsp"/>
    </div>
</sd:Dialog>
<sd:Dialog width="50%" id="msgs" key="dialog.title">
    <div id="mess">
        
    </div>
</sd:Dialog>