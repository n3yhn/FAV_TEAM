<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<script type="text/javascript">
    page.getNo = function (index) {
        return dijit.byId("versionGridIdOV").currentRow + index + 1;
    };

    page.getRow = function (inRow) {
        return inRow;
    };
    page.formatLinkSDBSdownloadOV = function (inData) {
        var item = dijit.byId("versionGridIdOV").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var attachId = parseInt(item.attachId);
            var name = item.attachName;
            url = "<a href='uploadiframe!openFileUserAttachPdfCVBSold.do?attachId=" + attachId + "' >" + name + "</a>";//141215u binhnt53
        }
        return url;
    };
    page.formatFilesAttachOV = function (inData) {
        var item = dijit.byId("versionAttachsGridIdOV").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var attachId = parseInt(item.attachId);
            var name = item.attachName;
            url = "<a href='uploadiframe!openFileUserAttachPdfCVBSold.do?attachId=" + attachId + "' >" + name + "</a>";//141215u binhnt53
        }
        return url;
    };
</script>
<sx:ResultMessage id="resultMessage" />
<form id="oldVersionFiles" name="oldVersionFiles">
    <table width="100%;" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="30%"></td>
            <td width="70%"></td>
        </tr>
        <tr>
            <td>Nội dung gần nhất:</td>
            <td><div id="oldVersionFiles.lastCommentOV"></div></td>
        </tr>
        <tr>
            <td colspan="2">
                <div id="versionRequestGridDivOV" style="width:100%;">
                    
                </div>
            </td>
        </tr>
    </table>
</form>

<script>
    page.reloadViewOldVersion = function (oldVersion, thisVersion) {//u140728
        var div = document.getElementById('viewDiv');
        var replaceDiv = "viewDiv";
        if (div != null) {
            div.Value = '';
        }
        div = document.getElementById('createDiv');
        if (div != null) {
            div.Value = '';
            replaceDiv = "createDiv";
        }
        var processCommentGridId_VTGrid = dijit.byId('processCommentGridId_VTGrid');
        if (processCommentGridId_VTGrid) {
            processCommentGridId_VTGrid.destroyRecursive(true);
        }
        var filesCommentGridId_VTGrid = dijit.byId('filesCommentGridId_VTGrid');
        if (filesCommentGridId_VTGrid) {
            filesCommentGridId_VTGrid.destroyRecursive(true);
        }
        var documentProcessGridId_VTGrid = dijit.byId('documentProcessGridId_VTGrid');
        if (documentProcessGridId_VTGrid) {
            documentProcessGridId_VTGrid.destroyRecursive(true);
        }
        var paymentHistoryGridId_VTGrid = dijit.byId('paymentHistoryGridId_VTGrid');
        if (paymentHistoryGridId_VTGrid) {
            paymentHistoryGridId_VTGrid.destroyRecursive(true);
        }
        var feePaymentInfoGridId_VTGrid = dijit.byId('feePaymentInfoGridId_VTGrid');
        if (feePaymentInfoGridId_VTGrid) {
            feePaymentInfoGridId_VTGrid.destroyRecursive(true);
        }
        var versionGridIdOV_VTGrid = dijit.byId('versionGridIdOV_VTGrid');
        if (versionGridIdOV_VTGrid != null) {
            versionGridIdOV_VTGrid.destroyRecursive(true);
        }
        sd.connector.post("filesAction!loadFilesByOldVersion.do?thisVersion=" + thisVersion + "&oldVersion=" + oldVersion, replaceDiv, null, null, afterReloadViewOldVersion);
    };
    page.getViewProcess = function (objectId) {
//        dijit.byId("gridProcessId").vtReload("filesAction!loadFLowView.do?flowForm.fileId=" + objectId, null);
    };
</script>