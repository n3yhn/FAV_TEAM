<%-- 
    Document   : 
    Created on : Jun 01, 2014, 4:09:25 PM
    Author     : vtit_binhnt53
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
    page.getNo = function(index) {
        return dijit.byId("commentGridId").currentRow + index + 1;
    };

    page.getRow = function(inRow) {
        return inRow;
    };
    page.formatDeleteComment = function(inRow) {
        var item = dijit.byId("commentGridId").getItem(inRow);
        var url = "";
        if (item != null) {
            if (item.editable == "1") {
                var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/delete.png' width='14px' height='14px' \n\
                                    title='Xóa' \n\
                                    onClick='page.deleteItem(" + item.processCommentId + ");' /></div>";
            }
        }
        return url;
    };
    page.attachFileLinks = function(inRow) {
        var item = dijit.byId("commentGridId").getItem(inRow);
        var url = "";
        if (item != null) {
            url = item.attachFileLinks;
        }
        return url;
    };
    page.deleteItem = function(id) {
        var deleteItemExecute = function() {
            sd.connector.post("filesAction!onDeleteComment.do?processCommentId=" + id + "&" + token.getTokenParamString(), null, null, null, page.returnMessageDelete);
        }
        msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title</sd:Property>', deleteItemExecute);
            };

            page.returnMessageDelete = function(data) {
                var objectId = document.getElementById("commentForm.objectId").value;
                var objectType = document.getElementById("commentForm.objectType").value;
                dijit.byId("commentGridId").vtReload("filesAction!getCommentsBusiness.do?objectId=" + objectId + "&objectType=" + objectType);
            };
            page.formatLinkSDBSdownload = function(inData) {
                var item = dijit.byId("versionGridId").getItem(inData - 1);
                var url = "";
                if (item != null) {
                    var attachId = parseInt(item.attachId);
                    var name = item.attachName;
                    url = "<a href='uploadiframe!openFileUserAttachPdfCVBSold.do?attachId=" + attachId + "' >" + name + "</a>";//141215u binhnt53
                }
                return url;
            };
    </script>
<sd:TitlePane key="documentReceive.staffRequest" id="staffRequestTiles">
    <table width="100%" class="viewTable" id="tblFeedbackEvaluateFormView">
        <tr style="display: none">
            <td width="30%" style="text-align: right"><sd:Label key="Trạng thái hồ sơ"/></td>
            <td width="70%">
                <div id="evaluateFormView.status"></div>            
            </td>
        </tr>
        <tr>
            <td style="text-align: right;width: 25%"><sd:Label key="Nội dung thẩm định của chuyên viên"/></td>
            <td>
                <div id="evaluateFormView.staffRequest"></div>
            </td>
        </tr>
        <tr id="evaluateFormView.isTypeChange" style="display: none">
            <td colspan="2" style="text-align: center"><sd:Label key="Yêu cầu chuyển loại hồ sơ"/></td>
        </tr>
        <tr>
            <td style="text-align: right;width: 25%"><sd:Label key="Thông tin liên hệ chuyên viên"/></td>
            <td>
                <div id="evaluateFormView.staffNameContact"></div>
            </td>
        </tr>
        <tr>
            <td style="text-align: right;width: 25%"><sd:Label key="Email"/></td>
            <td>
                <div id="evaluateFormView.staffEmailContact"></div>
            </td>
        </tr>
        <tr>
            <td style="text-align: right;width: 25%"><sd:Label key="Điện thoại"/></td>
            <td>
                <div id="evaluateFormView.staffTelContact"></div>
            </td>
        </tr>
        <tr>
            <td style="text-align: right;width: 25%"><sd:Label key="Công văn yêu cầu SĐBS hồ sơ"/></td>
            <td>
                <sd:Button id="btnExportFBEFV" key="" onclick="page.downloadPDFFBEFV();" cssStyle="display:" cssClass="buttonGroup">
                    <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Xem truoc"/>
                    <span style="font-size:12px">Xuất công văn SĐBS hiện tại</span>
                </sd:Button>
            </td>
        </tr>
    </table>
</sd:TitlePane>
<sd:TitlePane key="documentReceive.versionRequest" id="versionRequestTiles">
    <div id="versionRequestGridDiv" style="width:100%;">
        <sd:DataGrid clientSort="true" 
                     id="versionGridId"
                     style="width:auto;height:auto"
                     getDataUrl="" 
                     container="versionRequestGridDiv" 
                     serverPaging="false"
                     rowSelector="0px" 
                     rowsPerPage="10">
            <sd:ColumnDataGrid key="voPublishDocument.No" get="page.getNo" width="5%" 
                               headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" styles="text-align:center;" />
            <sd:ColumnDataGrid  key="Link download công văn SĐBS" get="page.getNo" formatter="page.formatLinkSDBSdownload" cellStyles="text-align:left;"
                                width="15%" headerStyles="text-align:center;" />
        </sd:DataGrid>
    </div>
</sd:TitlePane>
<sd:TitlePane key="documentReceive.commentList" id="documentReceiveStaffRequestTitle">
    <div id="processCommentGridDiv" style="width:100%;">
        <sd:DataGrid clientSort="true" 
                     id="commentGridId"
                     style="width:auto;height:auto"
                     getDataUrl="" 
                     container="processCommentGridDiv" 
                     serverPaging="false"
                     rowSelector="0px" 
                     rowsPerPage="10">
            <sd:ColumnDataGrid key="voPublishDocument.No" get="page.getNo" width="5%" 
                               headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" styles="text-align:center;" />
            <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="processComment.userName" field="userName" width="15%"/>            
            <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="processComment.groupName" field="groupName" width="15%"/>
            <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="processComment.commentText" field="commentText" width="35%"/>
            <sd:ColumnDataGrid key="btnUploadGrid" headerStyles="text-align:center;text-align:center;font-weight: bold;" formatter="page.attachFileLinks" width="20%" get="page.getRow"/>
            <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" type="date" format="dd/MM/yyyy HH:mm" key="processComment.createdDate" field="createdDate" width="10%"/>
            <sd:ColumnDataGrid key="btnDelete" headerStyles="text-align:center;text-align:center;font-weight: bold;" formatter="page.formatDeleteComment" get="page.getRow" width="5%" styles="text-align:center;"/>
            <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="Lần SĐ" field="version" width="5%" styles="text-align:center;"/>
        </sd:DataGrid>
    </div>
    <form name="commentForm" id="commentForm">
        <table style="width:100%;overflow:auto ; padding: 0">
            <tr>
                <td width="20%"><sd:TextBox id="commentForm.commentType" key="" name="commentForm.commentType" cssStyle="display:none;" value="0"/></td>
                <td width="80%"></td>
            </tr>
            <tr id="commentText">
                <td style="text-align: right;padding-right: 10px">

                    <sd:Label key="Ý kiến :"></sd:Label><span style="color: red">*</span>
                    </td>
                    <td style="width:100%" >
                        <input type="hidden" id="commentForm.objectId" name="commentForm.objectId"/>
                        <input type="hidden" id="commentForm.objectType" name="commentForm.objectType" value="30"/>                    
                    <sd:Textarea key=""
                                 id="commentForm.commentText" name="commentForm.commentText" 
                                 maxlength="4000" cssStyle="width:100%" />
                </td>
            </tr>
            <tr id="attachPath">
                <td style="text-align: right;padding-right: 10px">
                    <sd:Label key="Tệp đính kèm ý kiến: "></sd:Label>
                    </td>
                    <td style="width: 100%;">
                    <sx:upload extension="*.pdf,*.doc,*.docx,*.zip,*.rar, *.docx, *.xls, *.xlsx"
                               auto="true" id="commentForm.attachPath"
                               action="uploadiframe!uploadFile.do?id=commentForm.attachPath" maxSize="5" >
                    </sx:upload>
                    <sd:TextBox trim="true" cssStyle="width:100%;display:none;"
                                id="commentForm.attachIds"
                                name="commentForm.attachIds" key=""/>
                </td>
            </tr>    
            <tr>
                <td style="width:100px;text-align: center;margin-top: 5px;" colspan="2">
                    <sd:Button id="feedbackEvaluateBtn.btnInsert" key="" onclick="insertComment()" cssStyle="display:">
                        <img src="share/images/icons/a7.png" height="14" width="14">
                        <span style="font-size:12px"><sd:Property>processComment.add</sd:Property></span>
                    </sd:Button>
                    <sx:ButtonClose onclick="onCloseEvaluateView();"/>
                </td>
            </tr>
        </table>
    </form>
</sd:TitlePane>
<script type="text/javascript">
    var ckInsert = false;
    
    page.afterCommit = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result0 = result[0];
        if (result0 == "3") {
            resultMessage_show("resultMessage", result[0], result[1], 5000);
        } else {
            resultMessage_show("resultCreateMessage", result[0], result[1], 5000);
            backPage();
            page.search();
        }
        ckInsert = false;
    };
    
    page.validateFilesData = function() {
        if (!page.validateAnnouncement()) {
            return false;
        }
        if (!page.validateDetailProduct()) {
            return false;
        }
        if (!page.validateAttach()) {
            return false;
        }
        if (!page.validateQualityPlan()) {
            return false;
        }
        page.renameElementOfAttachs();
        return true;
    };
    
    page.saveFiles = function() {
        if (!ckInsert) {
            if (page.validateFilesData()) {
                ckInsert = true;
                sd.connector.post("filesAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterCommit);
            }
        }
    };
    
    onCloseEvaluateView = function() {
        dijit.byId("feedbackEvaluateViewDetailsDlg").hide();
    };
    
    page.replaceNewLineByBr = function() {
        var table = document.getElementById("tblFeedbackEvaluateFormView");
        var divs = table.getElementsByTagName("div");
        var i = 0;
        var content = "";
        for (i = 0; i < divs.length; i++) {
            content = divs[i].innerHTML;
            content = content.replace(/\n/g, "<br>");
            divs[i].innerHTML = content;
        }
    };
    
    page.showComment = function() {
        page.getComments(fileId, "30");
    };
    
    page.getComments = function(objectId, objectType) {
        document.getElementById("commentForm.objectId").value = escapeHtml_(objectId);
        document.getElementById("commentForm.objectType").value = escapeHtml_(objectType);
        dijit.byId("commentGridId").vtReload("filesAction!getCommentsBusiness.do?objectId=" + objectId + "&objectType=" + objectType);
    };
    
    page.showVersionSDBS = function() {
        page.getVersionSDBS(fileId, "71");
    };
    
    page.getVersionSDBS = function(objectId, objectType) {
        document.getElementById("commentForm.objectId").value = escapeHtml_(objectId);
        document.getElementById("commentForm.objectType").value = escapeHtml_(objectType);
        dijit.byId("versionGridId").vtReload("filesAction!getVersionSDBS.do?objectId=" + objectId + "&objectType=" + objectType);
    };

    page.downloadPDFFBEFV = function() {
        document.location = "uploadiframe!openFileUserAttachPdfCVBS.do?fileIdf=" + fileId;
    };
    
    afterInsertCommentFBEFV = function(data) {
        var objectId = document.getElementById("commentForm.objectId").value;
        var objectType = document.getElementById("commentForm.objectType").value;
        dijit.byId("commentGridId").vtReload("filesAction!getCommentsBusiness.do?objectId=" + objectId + "&objectType=" + objectType);
        sd._("commentForm.commentText").setValue("");
    };
    
    insertComment = function() {
        if (!dijit.byId("commentForm.commentText").getValue()) {
            dijit.byId("commentForm.commentText").focus();
            msg.alert("Bạn phải nhập ý kiến", "<sd:Property>confirm.title</sd:Property>");
                        return;
                    }
                    //
                    var attIDs = getListAttachId("commentForm.attachPath");
                    dojo.attr(dojo.byId("commentForm.attachIds"), "value", attIDs);
                    sd.connector.post("filesAction!insertComment.do?" + token.getTokenParamString(), null, "commentForm", null, afterInsertCommentFBEFV);
                    clearAttFile("commentForm.attachPath");
                };
</script>