<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<!-- [ Query section -->
<jsp:include page="roles-JS.jsp"/>
<!-- ] Query section -->

<!-- [ Query section -->
<jsp:include page="roles-QueryForm.jsp"/>
<!-- ] Query section -->

<sd:Dialog id="dialogRoleId" key="ad.insertRole" width="70%">
    <div id="dialogRoleDiv"></div>
</sd:Dialog>
<sd:Dialog width="75%" id="dialogObjectId" key="objRoleForm.title">
   <div id="objectArea"></div>
</sd:Dialog>
