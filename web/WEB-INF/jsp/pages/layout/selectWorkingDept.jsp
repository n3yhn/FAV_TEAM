<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/commonJavascript.jsp"/>
<jsp:include page="includeParams.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>


<script type="text/javascript">
    selectDept = function(deptId){
        if(deptId == null){
            alert("Không có phòng ban nào");
            return;
        }
        document.location.href = "${contextPath}/Index!changeWorkingDept.do?deptId="+deptId+"&request_locale=${fn:escapeXml(vt_locale)}";
    }
    
    showDialog = function(){
        var dlg = dijit.byId("selectDeptDlg");
        dlg.show();
    }
    
</script>

<table width="100%" style="border-collapse: collapse">
    <c:forEach var="item" items="${lstDepartment}">
        <tr>
            <td  class="homeTD">
                <table style="width:100%; height:100%">
                    <tr onclick="selectDept(${item.deptId})" class="headerRow">
                        <td style="border: 1px solid #B0B0B0;text-align: center;vertical-align: middle">
                            <a href="#">
                                ${fn:escapeXml(item.deptName)}
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div id="waitingDiv${item.deptId}">

                            </div>
                        </td>
                    </tr>
                    <script type="text/javascript">
                        sd.connector.post("Index!getWaitingObject.do?deptId="+${item.deptId},"waitingDiv${item.deptId}");
                    </script>
                </table>
            </td>
            <td class="homeTD">
                <table style="width:100%; height:100%">
                    <tr onclick="selectDept(${item.parentId})" class="headerRow">
                        <td style="border: 1px solid #B0B0B0;text-align: center;vertical-align: middle">
                            <a href="#">
                                ${fn:escapeXml(item.deptParentName)}
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div id="waitingDiv${item.parentId}">

                            </div>
                        </td>
                    </tr>
                    <script type="text/javascript">
                        sd.connector.post("Index!getWaitingObject.do?deptId="+${item.parentId},"waitingDiv${item.parentId}");
                    </script>
                </table>
            </td>
        </tr>
    </c:forEach>
</table>
<!--    </div>-->
<%--</sd:Dialog>--%>

<script type="text/javascript">
    adjustBodyFrameSize(true);
</script>


