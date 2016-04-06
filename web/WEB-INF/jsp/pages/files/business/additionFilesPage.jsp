<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../util/util_js.jsp"/>
<jsp:include page="../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="token">
    <s:token id="tokenId"/>
</div>
<div id="selectFileDiv">
    <table style="width:100%">
        <c:forEach var="item" items="${lstCategory}" varStatus="status">
            <tr>
                <td style="text-align: right;vertical-align: middle;width:70%">
                    <div>
                        <a href="#" onclick="showCreateFile(${item.categoryId});">
                            ${fn:escapeXml(item.name)}
                        </a>
                    </div>
                </td>

            </tr>
        </c:forEach>
    </table>                
</div>
<div id="createFileDiv"/>
<script type="text/javascript">
    function afterLoadForm(data){
        document.getElementById("selectFileDiv").style.display="none";
        document.getElementById("createFileDiv").style.display="";
    }
    
    function showCreateFile(fileTypeId){
        sd.connector.post("filesAction!toCreateFile.do?createForm.fileTypeId="+fileTypeId,"createFileDiv",null,null,afterLoadForm);
    }
</script>