<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<jsp:include page="/WEB-INF/jsp/pages/common/commonJavascript.jsp" />
<div id="token">
    <s:token id="tokenId"/>
</div>

<!-- [ Query section -->
<jsp:include page="user-JS.jsp"/>
<!-- ] Query section -->

<!-- [ Query section -->
<jsp:include page="user-QueryForm.jsp"/>
<!-- ] Query section -->

<div id="listRoleOfUserDiv" >
    
</div>

<sd:Dialog id="dialogUserId" key="dialog.title" width="900px">
    <div id="dialogUserDiv" style="overflow: auto; max-height: 500px;position: relative;">
        <jsp:include page="user-Dialog.jsp"/>
    </div>
</sd:Dialog>

<sd:Dialog id="resetPassDlg" key="dialog.title" width="40%">
    <div id="resetPassDiv">
        <jsp:include page="user-ResetPass.jsp"/>
    </div>
</sd:Dialog>

<sd:Dialog id="userRoleDlg" key="dialog.title" width="80%">
    <div id="userRoleDiv" style="overflow: auto;max-height: 500px;position: relative;">
        <jsp:include page="roleOfUser.jsp"/>
    </div>
</sd:Dialog>

<sd:Dialog id="deptOfUserRoleDlg" key="Chọn đơn vị" width="400px">
    <div id="deptOfUserRoleDiv" style="overflow: auto;max-height: 500px;position: relative;">
        <jsp:include page="treeDept.jsp"/>
    </div>
</sd:Dialog>

<sd:Dialog id="unAssignedRoleDlg" key="dialog.title" width="80%">
    <div id="unAssignedRoleDiv" style="overflow: auto;max-height: 500px;position: relative;">
        <jsp:include page="addUserRoleDialog.jsp"/>
    </div>
</sd:Dialog>

<%--<sd:Dialog id="dialogUserDeptId" key="dialog.title" width="67%">
    <div id="dialogUserDeptDiv">
        <jsp:include page="userDeptDialog.jsp"/>
    </div>
</sd:Dialog>
--%>
<%--<sd:Dialog id="dialogUndeleteUser" key="dialog.title" width="67%">
    <div id="dialogUndeleteUserDiv">
        <jsp:include page="undeleteUser.jsp"/>
    </div>
</sd:Dialog>
--%>