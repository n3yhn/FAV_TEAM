<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sd:Tab id="declaration_tab" height="500px" width="100%">
    <sd:ContentPane key="Thông tin node" id="tab.nodeContent" refresh="alert('1');">
        <div id="tabDraftDocument" style="overflow: auto;">
            <jsp:include page="tabNodeContent.jsp"/>
        </div>
    </sd:ContentPane>    

    <sd:ContentPane key="Đơn vị, người tham gia" id="tab.deptUser" refresh="true" >
        <div id="tabReportDocument" style="overflow: auto" >
            <jsp:include page="tabNodeDeptUser.jsp"/>
        </div>
    </sd:ContentPane>
</sd:Tab> 

<script type="text/javascript">
    
    dojo.connect(dijit.byId('declaration_tab_tablist_tab.nodeContent'), "onClick", refreshNodeToNodeGrid = function() {       
        dijit.byId("nodeToNodeGrid").renderNoReload();
    });
    dojo.connect(dijit.byId('declaration_tab_tablist_tab.deptUser'), "onClick", refreshDeptUserNodeGrid = function() {       
        dijit.byId("processGridId").renderNoReload();
        hideGridHeader();
    });   
</script>