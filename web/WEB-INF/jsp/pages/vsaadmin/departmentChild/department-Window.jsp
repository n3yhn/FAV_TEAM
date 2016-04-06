<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<div id="token">
    <s:token id="tokenId"/>
</div>

<jsp:include page="/WEB-INF/jsp/pages/vsaadmin/config/alertDialog.jsp"/>

<!-- ] Query section -->
<jsp:include page="department-JS.jsp"/>

<!-- [ Query section -->
<jsp:include page="department-QueryForm.jsp"/>


<sd:Dialog id="dialogDeptId" key="dialog.title" width="67%">
    <div id="dialogDeptDiv">
        <jsp:include page="department-Dialog.jsp"/>
    </div>
</sd:Dialog>

<sd:Dialog id="dialogAddUserId" key="Bổ sung người dùng" width="67%">
    <div id="dialogAddUserDiv">
        <jsp:include page="addUserToDeptDialog.jsp"/>
    </div>
</sd:Dialog>

<%--<sd:Dialog id="dialogListUserOfDept" key="dialog.title" width="67%">
    <div id="listUserOfDeptDiv" >
        <jsp:include page="listUserOfDept.jsp"/>
    </div>
</sd:Dialog>--%>
