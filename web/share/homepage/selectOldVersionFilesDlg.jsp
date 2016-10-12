<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<script type="text/javascript">
    page.getNo = function(index) {
        return dijit.byId("versionGridIdOV").currentRow + index + 1;
    };

    page.getRow = function(inRow) {
        return inRow;
    };
    page.formatLinkSDBSdownloadOV = function(inData) {
        var item = dijit.byId("versionGridIdOV").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var attachId = parseInt(item.attachId);
            var name = item.attachName;
            url = "<a href='uploadiframe!openFileUserAttachPdfCVBSold.do?attachId=" + attachId + "' >" + name + "</a>";//141215u binhnt53
        }
        return url;
    };
    page.formatFilesAttachOV = function(inData) {
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
                    <sd:DataGrid clientSort="true" 
                                 id="versionGridIdOV"
                                 style="width:auto;height:auto"
                                 getDataUrl="" 
                                 container="versionRequestGridDivOV" 
                                 serverPaging="false"
                                 rowSelector="0px" 
                                 rowsPerPage="10">
                        <sd:ColumnDataGrid key="voPublishDocument.No" get="page.getNo" width="5%" 
                                           headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" styles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Link download công văn SĐBS" get="page.getNo" formatter="page.formatLinkSDBSdownloadOV" cellStyles="text-align:left;"
                                            width="15%" headerStyles="text-align:center;" />
                    </sd:DataGrid>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div id="versionAttachsGridDivOV" style="width:100%;">
                    <sd:DataGrid clientSort="true" 
                                 id="versionAttachsGridIdOV"
                                 style="width:auto;height:auto"
                                 getDataUrl="" 
                                 container="versionAttachsGridDivOV" 
                                 serverPaging="false"
                                 rowSelector="0px" 
                                 rowsPerPage="10">
                        <sd:ColumnDataGrid key="voPublishDocument.No" get="page.getNo" width="5%" 
                                           headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" styles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Tên tệp" field="attachName"  cellStyles="text-align:left;"
                                                width="50%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Link download" get="page.getNo" formatter="page.formatFilesAttachOV" cellStyles="text-align:left;"
                                            width="50%" headerStyles="text-align:center;" />
                    </sd:DataGrid>
                </div>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Phiên bản thứ nhất"/>
            </td>
            <td>
                <sd:SelectBox cssStyle="width:100%"
                              id="oldVersionFiles.version1"
                              key="" data="lstOldVersion" valueField="fileId" labelField="strVersion"
                              name="oldVersionFiles.version" >
                </sd:SelectBox>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Phiên bản thứ hai"/>
            </td>
            <td>
                <sd:SelectBox cssStyle="width:100%"
                              id="oldVersionFiles.version2"
                              key="" data="lstOldVersion" valueField="fileId" labelField="strVersion"
                              name="oldVersionFiles.version" >
                </sd:SelectBox>
            </td>
        </tr>
        <tr style="text-align: center">
            <td colspan="2">
                <sd:Button id="btnViewOldVersion" key="" onclick="page.viewOldVersion();">
                    <img src="${contextPath}/share/images/icons/Answer.png" height="14" width="18">
                    <span style="font-size:12px">Xem</span>
                </sd:Button>
                <sx:ButtonClose onclick="page.close()" />
            </td>
        </tr>
    </table>
</form>

<script>
    page.showOldVersionFilesDlg = function() {
        dijit.byId("selectOldVersionFilesDlg").show();
        page.showGridCommentOV();
    };
    page.close = function() {
        dijit.byId("selectOldVersionFilesDlg").hide();
    };
    page.viewOldVersion = function() {
        var thisVersion = dijit.byId("oldVersionFiles.version1").getValue();
        var oldVersion = dijit.byId("oldVersionFiles.version2").getValue();
        if (thisVersion == oldVersion) {
            alert("Vui lòng chọn 2 phiên bản khác nhau");
            return;
        }
        if (thisVersion > 0 && oldVersion > 0) {
            page.reloadViewOldVersion(oldVersion, thisVersion);
        } else {
            if (thisVersion == oldVersion) {
                alert("Vui lòng chọn 2 phiên bản khác nhau");
            } else {
                alert("Vui lòng chọn lần sửa đổi trước khi xem nội dung thay đổi");
            }
        }

    };

    page.reloadViewOldVersion = function(oldVersion, thisVersion) {//u140728
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
    
    afterReloadViewOldVersion = function(data) {
    };//!u140728

    page.showGridCommentOV = function() {
        var fileId = dijit.byId("createForm.fileId").getValue();
        sd.connector.post("filesAction!getCommentFeedbackApprove.do?objectId=" + fileId, null, null, null, afterShowGridCommentOV);
    };
    
    afterShowGridCommentOV = function(data) {
        var obj = dojo.fromJson(data);
        if (obj.customInfo[0] != "") {
            var content = escapeHtml_(obj.customInfo[0]);
            content = content.replace(/\n/g, "<br>");
            document.getElementById("oldVersionFiles.lastCommentOV").innerHTML = content;
        } else {
            document.getElementById("oldVersionFiles.lastCommentOV").innerHTML = "Không có dữ liệu";
        }
        var fileId = dijit.byId("createForm.fileId").getValue();
        var objectType = "71";
        document.getElementById("commentForm.objectId").value = escapeHtml_(fileId);
        document.getElementById("commentForm.objectType").value = escapeHtml_(objectType);
        dijit.byId("versionGridIdOV").vtReload("filesAction!getVersionSDBS.do?objectId=" + fileId + "&objectType=" + objectType);
        dijit.byId("versionAttachsGridIdOV").vtReload("filesAction!getAttachsSDBS.do?objectId=" + fileId + "&objectType=" + 30);
    };
    
</script>