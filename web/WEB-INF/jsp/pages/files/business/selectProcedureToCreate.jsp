<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../util/util_js.jsp"/>
<jsp:include page="../../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<link class="lfr-css-file" href="share/homeLayout/style.css" rel="stylesheet" type="text/css">

<div id="token">
    <s:token id="tokenId"/>
</div>

<div id="main-boxes" style="margin-left: 100px;margin-top: 10px">
    <div class="box2">
        <span class="left-arrow">&nbsp;</span>
        <h3>Công bố phù hợp</h3>
        <div class="box-content">
            <ul class="link-list">
                <c:forEach var="item" items="${lstCategory}" varStatus="status">
                    <c:if test="${item.type == '1'}"> 
                        <li>
                            <a onclick="showCreateFile(${item.procedureId},${item.typeFee});">${fn:escapeXml(item.name)}</a>
                        </li>
                    </c:if>         
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="box2">
        <span class="left-arrow">&nbsp;</span>
        <h3>Công bố hợp quy</h3>
        <div class="box-content">
            <ul class="link-list">
                <c:forEach var="item" items="${lstCategory}" varStatus="status">
                    <%--  <c:if test="${item.type == '2' && (item.procedureId != 42 && item.procedureId != 41)}">  --%>
                           <c:if test="${item.type == '2'}">  
                        <li><a onclick="showCreateFile(${item.procedureId},${item.typeFee});">${fn:escapeXml(item.name)}</a></li>
                        </c:if>         
                    </c:forEach>
            </ul>
        </div>
    </div>
    <%--
        <div class="box2">
            <span class="left-arrow">&nbsp;</span>
            <h3>TTHC khác</h3>
            <div class="box-content">
                <ul class="link-list">
                    <c:forEach var="item" items="${lstCategory}" varStatus="status">
                        <c:if test="${item.type == '3'}"> 
                            <li><a onclick="showCreateFile(${item.procedureId});">${fn:escapeXml(item.name)}</a></li>
                            </c:if>         
                        </c:forEach>
                </ul>
            </div>
        </div>
    --%>
</div>    

<%--
<div id="selectFileDiv" style="width:100%;height: 100%">
    <sd:TitlePane key="Danh sách thủ tục hành chính" id="lstTTHC">
        <sx:ResultMessage id="resultCreateMessage"/>
        <table style="width:100%" class="viewTable">
            <c:forEach var="item" items="${lstCategory}" varStatus="status">
                <tr>
                    <td width="5%" align="center">${status.count}</td>
                    <td style="text-align: left;vertical-align: middle;width:95%">
                        <div>
                            <a href="#" onclick="showCreateFile(${item.procedureId});">
                                ${fn:escapeXml(item.name)}
                            </a>
                        </div>
                    </td>

                </tr>
            </c:forEach>
        </table>       
    </sd:TitlePane>
</div>--%>

<div id="createFileDiv">
</div>
<script type="text/javascript">
    afterLoadForm = function(data) {
        document.getElementById("main-boxes").style.display = "none";
        document.getElementById("createFileDiv").style.display = "";
    };
    backPage = function() {
        document.getElementById("main-boxes").style.display = "";
        document.getElementById("createFileDiv").style.display = "none";
        //doGoToMenu(g_latestClickedMenu);

    };
    showCreateFile = function(fileType, typeFee) {
        document.getElementById("main-boxes").style.display = "none";
        document.getElementById("createFileDiv").style.display = "";
        sd.connector.post("filesAction!toCreateFilePage.do?createForm.fileType=" + fileType + "&typeFee=" + typeFee, "createFileDiv", null, null, afterLoadForm);
    };
</script>
<a href="selectProcedureToCreate.jsp"></a>