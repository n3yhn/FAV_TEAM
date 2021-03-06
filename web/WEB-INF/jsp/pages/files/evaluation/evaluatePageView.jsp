<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<style type="text/css">
    .box:hover{
        color: #ffff00;
        cursor: pointer;
    }
</style>

<div id="viewDiv">
    <div style="text-align: center">
        <br/>
        <br/>
        <br/>
        <span style="color: red">
            <label id="labelWait" style="color: red">Vui lòng chờ  </label>
                <img src="/share/images/loading/loading2.gif" width="20px" height="20px">
        </span>
    </div>
</div>
<script type="text/javascript">
    var fileId = ${fn:escapeXml(fileId)};
    var viewType = ${fn:escapeXml(viewType)};
    var backPageType = ${fn:escapeXml(backPage)};
    var backPageStr = "";

    if (backPageType == 1) {
        // LDC phe duyet SDBS
        backPageStr = "filesAction!toEvaluatePage.do";
    }
    else if (backPageType == 2)
    {
        // LDC phe duyet SDBS
        backPageStr = "filesAction!lookupFilesByLeader.do?searchForm.searchType=26";
    }
    else if (backPageType == 3)
    {
        // Tiep nhan ho so
        backPageStr = "filesAction!toReceived.do";
    }
    else if (backPageType == 4)
    {
        // Doi chieu ho so
        backPageStr = "filesAction!toComparison.do";
    }
    else if (backPageType == 5)
    {
        // TP Xem xet ho so
        backPageStr = "filesAction!toReviewPage.do";
    }
    else if (backPageType == 6)
    {
        // TP Xem xet ho so sđbs
        backPageStr = "filesAction!toAdditionReviewPage.do";
    }
    else if (backPageType == 7)
    {
        // Phan cong ho so
        backPageStr = "filesAction!assignEvaluation.do";
    }

    backPage = function() {
        doGoToMenu(backPageStr);
    };
    
    showViewFile = function() {
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + fileId + "&createForm.viewType=" + viewType, "viewDiv", null, null, null);
    };
    
    page.b64_to_utf8 = function(str) {
        return unescape(decodeURIComponent(window.atob(str)));
    };
    
    showViewFile();
</script>
