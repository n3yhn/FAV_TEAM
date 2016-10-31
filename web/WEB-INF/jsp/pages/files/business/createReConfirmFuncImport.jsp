<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div class="buttonDiv">
    <sx:ButtonBack onclick="backPage();"/>
    <sx:ButtonSave onclick="page.saveFiles();"/>
</div>
<sd:TitlePane key="${fn:escapeXml(fileNameFull)}"
              id="fileNameFull" >
    <sx:ResultMessage id="resultMessage"/>
    <form id="createForm" name="createForm">
        <sd:TextBox key="" id="createForm.fileId" name="createForm.fileId" cssStyle="display:none" />
        <sd:TextBox key="" id="createForm.fileType" name="createForm.fileType" cssStyle="display:none" />
        <sd:TextBox key="" id="createForm.countUA" name="createForm.countUA" cssStyle="display:none" />
        <sd:Tab id="files_tab" height="400px" width="100%">
            <sd:ContentPane key="Đơn đề nghị cấp lại" id="tab.reannoucement">
                <div id="tabReIssueFormDiv" style="overflow: auto;">
                    <jsp:include page="./fileTab/tabReConfirmAnnouncement.jsp"/>
                </div>
            </sd:ContentPane>
            <sd:ContentPane key="Bản công bố đã công bố" id="tab.annoucement">
                <div id="tabAnnouncementDiv" style="overflow: auto;">
                    <jsp:include page="./fileTab/tabReAnnouncementProductDetail.jsp"/>
                </div>
            </sd:ContentPane>
            <sd:ContentPane key="Tài liệu đính kèm" id="tab.attachs">
                <div id="tabAttachDiv" style="overflow: auto;">
                    <jsp:include page="./fileTab/tabAttachs.jsp"/>
                </div>
            </sd:ContentPane>    

        </sd:Tab>
    </form>     
</sd:TitlePane>
<script type="text/javascript">
    var ckInsert = false;
    page.afterCommit = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result0 = result[0];
        if (result0 == "3") {
            resultMessage_show("resultMessage", result[0], result[1], 5000);
        } else {
            resultMessage_show("resultMessage", result[0], result[1], 5000);
            backPage();
            page.search();
        }
        ckInsert = false;
    };

    page.validateFilesData = function () {
        if (!page.validateReIssueForm()) {
            return false;
        }
        if (!page.validateAttach()) {
            return false;
        }
        page.renameElementOfAttachs();
        return true;
    };

    page.saveFiles = function () {
        if (!ckInsert) {
            if (page.validateFilesData()) {
                ckInsert = true;
                sd.connector.post("filesAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterCommit);
            }
        }
    };
</script>