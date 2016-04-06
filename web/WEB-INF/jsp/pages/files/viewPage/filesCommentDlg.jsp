<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<script type="text/javascript">
    page.getNo = function (index) {
        return dijit.byId("filesCommentGridId").currentRow + index + 1;
    };

    page.getRow = function (inRow) {
        return inRow;
    };

    page.formatDeleteComment = function (inRow) {
        var item = dijit.byId("filesCommentGridId").getItem(inRow);
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
    page.attachFileLinks = function (inRow) {
        var item = dijit.byId("filesCommentGridId").getItem(inRow);
        var url = "";
        if (item != null) {
            url = item.attachFileLinks;
        }
        return url;
    };
    page.formatCommentType = function (inRow) {
        var item = dijit.byId("filesCommentGridId").getItem(inRow);
        var url = "";
        if (item != null) {
            var commentType = parseInt(item.commentType);
            switch (commentType) {
                case 0:
                    url = "Trao đổi doanh nghiệp";
                    break;
                case 1:
                    url = "Về pháp chế";
                    break;
                case 2:
                    url = "Về chỉ tiêu chất lượng an toàn thực phẩm";
                    break;
                case 3:
                    url = "Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng";
                    break;
                case 4:
                    url = "Ý kiến chung";
                    break;
                default:
                    url = "Ý kiến chung";
            }
        }
        return url;
    };
    page.deleteItem = function (id) {
        var deleteItemExecute = function () {
            sd.connector.post("filesAction!onDeleteComment.do?processCommentId=" + id + "&" + token.getTokenParamString(), null, null, null, page.returnMessageDelete);
        };
        msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title</sd:Property>', deleteItemExecute);
            };

            page.returnMessageDelete = function (data) {
                var objectId = document.getElementById("filesCommentForm.objectId").value;
                var objectType = document.getElementById("filesCommentForm.objectType").value;
                var commentType = -1;
                var viewType = dijit.byId("createForm.viewType").getValue();
                if (viewType == 0) {
                    commentType = 0;
                } else {
                    commentType = -1;
                }
                dijit.byId("filesCommentGridId").vtReload("filesAction!getComments.do?objectId=" + objectId + "&objectType=" + objectType + "&commentType=" + commentType);
            };
    </script>
<sd:TitlePane key="documentReceive.commentList" id="requestCommentTitle">
    <div id="requestCommentGridDiv" style="width:100%;">
        <sd:DataGrid clientSort="true" 
                     id="requestCommentGridId"
                     style="width:auto;height:auto"
                     getDataUrl="" 
                     container="requestCommentGridDiv" 
                     serverPaging="false"
                     rowSelector="0px" 
                     rowsPerPage="100">
            <sd:ColumnDataGrid key="voPublishDocument.No" get="page.getNo" width="2%" 
                               headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" styles="text-align:center;" />
            <sd:ColumnDataGrid  key="Ngày" field="createDate" type="date" format="dd/MM/yyyy"
                                width="3%"  headerStyles="text-align:center;" />
            <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="Cán bộ" field="userName" width="4%"/>
            <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="Nội dung" field="content" width="15%"/>
        </sd:DataGrid>

    </sd:TitlePane>
    <sd:TitlePane key="documentReceive.commentList" id="processCommentTitle">
        <div id="filesCommentGridDiv" style="width:100%;">

            <sd:DataGrid clientSort="true" 
                         id="filesCommentGridId"
                         style="width:auto;height:auto"
                         getDataUrl="" 
                         container="filesCommentGridDiv" 
                         serverPaging="false"
                         rowSelector="0px" 
                         rowsPerPage="10">
                <sd:ColumnDataGrid key="voPublishDocument.No" get="page.getNo" width="5%" 
                                   headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" styles="text-align:center;" />
                <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="processComment.userName" field="userName" width="10%"/>
                <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="processComment.groupName" field="groupName" width="10%"/>
                <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="processComment.commentText" field="commentText" width="30%"/>
                <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="processComment.commentType" width="15%" formatter="page.formatCommentType" get="page.getRow"/>
                <sd:ColumnDataGrid key="btnUploadGrid" headerStyles="text-align:center;text-align:center;font-weight: bold;" formatter="page.attachFileLinks" width="15%" get="page.getRow"/>
                <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" type="date" format="dd/MM/yyyy HH:mm" key="processComment.createdDate" field="createdDate" width="7%"/>
                <sd:ColumnDataGrid key="btnDelete" headerStyles="text-align:center;text-align:center;font-weight: bold;" formatter="page.formatDeleteComment" get="page.getRow" width="3%" styles="text-align:center;"/>
                <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="Lần SĐ" field="version" width="5%" styles="text-align:center;"/>
            </sd:DataGrid>

        </div>
        <form name="filesCommentForm" id="filesCommentForm">
            <sd:TextBox id="viewCommentOnPage" key="" name="viewCommentOnPage" cssStyle="display:none;"/>
            <table style="width:100%;overflow:auto ; padding: 0">
                <tr>
                    <td width="20%"></td>
                    <td width="80%"></td>
                </tr>
                <tr id="trCommentText">
                    <td style="text-align: right;padding-right: 10px">

                        <sd:Label key="Ý kiến chung"></sd:Label>
                        </td>
                        <td style="width:100%" >
                            <input type="hidden" id="filesCommentForm.objectId" name="filesCommentForm.objectId"/>
                            <input type="hidden" id="filesCommentForm.objectType" name="filesCommentForm.objectType" value="30"/>
                        <sd:Textarea key=""
                                     id="filesCommentForm.commentText" name="filesCommentForm.commentText" 
                                     maxlength="4000" cssStyle="width:100%" />
                    </td>
                </tr>
                <tr id="trLegal">
                    <td style="text-align: right;padding-right: 10px">
                        <sd:Label key="Về pháp chế"></sd:Label>
                        </td>
                        <td style="width:100%" >
                        <sd:Textarea key=""
                                     id="filesCommentForm.legal" name="filesCommentForm.legal" 
                                     maxlength="4000" cssStyle="width:100%" />
                    </td>
                </tr>
                <tr id="trFoodSafetyQuality">
                    <td style="text-align: right;padding-right: 10px">
                        <sd:Label key="Về chỉ tiêu chất lượng an toàn thực phẩm"></sd:Label>
                        </td>
                        <td style="width:100%" >
                        <sd:Textarea key=""
                                     id="filesCommentForm.foodSafetyQuality" name="filesCommentForm.foodSafetyQuality" 
                                     maxlength="4000" cssStyle="width:100%" />
                    </td>
                </tr>
                <tr id="trEffectUtility">
                    <td style="text-align: right;padding-right: 10px">
                        <sd:Label key="Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng"></sd:Label>
                        </td>
                        <td style="width:100%" >
                        <sd:Textarea key=""
                                     id="filesCommentForm.effectUtility" name="filesCommentForm.effectUtility" 
                                     maxlength="4000" cssStyle="width:100%" />
                    </td>
                </tr>
                <tr id="attachPath">
                    <td style="text-align: right;padding-right: 10px">
                        <sd:Label key="Tệp đính kèm ý kiến"></sd:Label>
                        </td>
                        <td style="width: 100%;">
                        <sx:upload extension="*.pdf,*.doc,*.docx,*.zip,*.rar, *.docx, *.xls, *.xlsx"
                                   auto="true" id="filesCommentForm.attachPath"
                                   action="uploadiframe!uploadFile.do?id=filesCommentForm.attachPath" maxSize="5" >
                        </sx:upload>
                        <sd:TextBox trim="true" cssStyle="width:100%;display:none;"
                                    id="filesCommentForm.attachIds"
                                    name="filesCommentForm.attachIds" key=""/>
                    </td>
                </tr>    
                <tr>
                    <td style="width:100px;text-align: center;margin-top: 5px;" colspan="2">
                        <sd:Button id="commentBtn.btnInsert" key="" onclick="insertComment()" cssStyle="display:">
                            <img src="share/images/icons/a7.png" height="14" width="14">
                            <span style="font-size:12px"><sd:Property>processComment.add</sd:Property></span>
                        </sd:Button>
                        <sd:Button id="commentBtn.btnClose" key="" onclick="closeCommentDialog()">
                            <img src="share/images/icons/deleteStand.png" height="14" width="14" alt="Đóng"/>
                            <span style="font-size:12px"><sd:Property>btn.close</sd:Property></span>
                        </sd:Button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</sd:TitlePane>
<br/>
<script type="text/javascript">
    page.showComment = function () {
        var id = escapeHtml_(${createForm.fileId});
        getComments(id, "30");
        dijit.byId("processCommentDlg").show();
    };

    getComments = function (objectId, objectType) {
        document.getElementById("filesCommentForm.objectId").value = escapeHtml_(objectId);
        document.getElementById("filesCommentForm.objectType").value = escapeHtml_(objectType);
        var commentType = -1;
        var viewType = dijit.byId("createForm.viewType").getValue();
        if (viewType == 0) {
            commentType = 0;
        } else {
            commentType = -1;
        }
        dijit.byId("filesCommentGridId").vtReload("filesAction!getComments.do?objectId=" + objectId + "&objectType=" + objectType + "&commentType=" + commentType);
        dijit.byId("requestCommentGridId").vtReload("filesAction!getRequestComments.do?objectId=" + objectId);
    };

    closeCommentDialog = function () {
        var dlg = dijit.byId("processCommentDlg").hide();
    };

    afterInsertCommentFCD = function (data) {
        var objectId = document.getElementById("filesCommentForm.objectId").value;
        var objectType = document.getElementById("filesCommentForm.objectType").value;
        var commentType = -1;
        var viewType = dijit.byId("createForm.viewType").getValue();
        if (viewType == 0) {
            commentType = 0;
        } else {
            commentType = -1;
        }
        dijit.byId("filesCommentGridId").vtReload("filesAction!getComments.do?objectId=" + objectId + "&objectType=" + objectType + "&commentType=" + commentType);
        dijit.byId("filesCommentForm.commentText").setValue("");
        dijit.byId("filesCommentForm.legal").setValue("");
        dijit.byId("filesCommentForm.foodSafetyQuality").setValue("");
        dijit.byId("filesCommentForm.effectUtility").setValue("");
    };

    page.showCommentButton = function () {//hiển thị kết quả thẩm định
        var status = parseInt(escapeHtml_(${createForm.status}));
        if (status == 6) {
            dijit.byId("commentBtn.btnInsert").domNode.style.display = "none";
            var pane = document.getElementById("filesCommentForm.commentText");
            pane.setAttribute("style", "display:none;");
            document.getElementById("attachPath").setAttribute("style", "display:none;");
        }
    };

    page.showCommentButton();

    insertComment = function () {
        if (!dijit.byId("filesCommentForm.commentText").getValue() && !dijit.byId("filesCommentForm.legal").getValue() && !dijit.byId("filesCommentForm.foodSafetyQuality").getValue() && !dijit.byId("filesCommentForm.effectUtility").getValue()) {
            dijit.byId("filesCommentForm.commentText").focus();
            msg.alert("Bạn phải nhập ý kiến", "<sd:Property>confirm.title</sd:Property>");
                        return;
                    }
                    var attIDs = getListAttachId("filesCommentForm.attachPath");
                    dojo.attr(dojo.byId("filesCommentForm.attachIds"), "value", attIDs);
                    sd.connector.post("filesAction!insertComment.do?" + token.getTokenParamString(), null, "filesCommentForm", null, afterInsertCommentFCD);
                    clearAttFile("filesCommentForm.attachPath");
                };
</script>