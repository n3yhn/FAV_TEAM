<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<div id="token">
    <s:token id="tokenId"/>
</div>
<!-- [ Query section -->
<jsp:include page="/WEB-INF/jsp/pages/vsaadmin/config/alertDialog.jsp"/>
<jsp:include page="applicationsCatalog-JS.jsp"/>
<!-- ] Query section -->
<!-- [ Query section -->
<jsp:include page="applicationsCatalog-QueryForm.jsp"/>
<!-- ] Query section -->
<br/>
<sd:Dialog width="40%" id="dialogId" key="dialog.title">
    <div id="dialogDiv">
        <jsp:include page="applicationsCatalog-Dialog.jsp"/>
    </div>
</sd:Dialog>
<input type="hidden" id="appId">
<sd:Dialog width="900px" id="applicationsModuleId" key="applicationsForm.moduleList" >
    <div id="objectArea" style="overflow: auto;max-height: 500px;position: relative;">
        <jsp:include page="applicationsModule-Dialog.jsp"/>
    </div>
</sd:Dialog>

<sd:Dialog width="80%" id="dialogObjectId" key="">
    <div id="dialogObjectDiv" style="overflow: auto;max-height: 500px;position: relative;">
        <jsp:include page="objectsCatalog-Dialog.jsp"/>
    </div>
</sd:Dialog>

<sd:Dialog width="50%" id="msg" key="dialog.title">
    <div id="message">
    </div>
</sd:Dialog>