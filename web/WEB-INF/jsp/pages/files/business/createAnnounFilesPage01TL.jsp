<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<%-- Ckeditor --%>
<script src="share/ckeditor/ckeditor.js" type="text/javascript"></script>
<%--****************************** CKEditor ************************************/--%>

<style>

    .cke_focused,
    .cke_editable.cke_focused
    {
        outline: 3px dotted blue !important;
        *border: 3px dotted blue !important;	/* For IE7 */
    }

</style>
<script>

    CKEDITOR.on('instanceReady', function(evt) {
        var editor = evt.editor;
        editor.setData('This editor has it\'s tabIndex set to <strong>' + editor.tabIndex + '</strong>');

        // Apply focus class name.
        editor.on('focus', function() {
            editor.container.addClass('cke_focused');
        });
        editor.on('blur', function() {
            editor.container.removeClass('cke_focused');
        });

        // Put startup focus on the first editor in tab order.
        if (editor.tabIndex == 1)
            editor.focus();
    });

</script>
<%--/****************************** End CKEditor ************************************/--%>

<div class="buttonDiv">
    <sx:ButtonBack onclick="backPage();"/>
    <sx:ButtonSave onclick="page.insertFiles();"/>
    <sx:ButtonSaveDraff onclick="page.insertFileDraff();"/>
    <%-- <sx:ButtonSave onclick="page.saveFiles();"/> 
    
    <!-- hieptq update lai 17.11.14 -->
    <sd:Button id="btnTypeChange" key="" onclick="page.onTypeChange();" cssClass="buttonGroup" cssStyle="display:none">
        <img src="${contextPath}/share/images/icons/Answer.png" height="14" width="18">
        <span style="font-size:12px">Chuyển loại hồ sơ</span>
    </sd:Button>
    <sd:SelectBox
        id="createForm.Type"
        key="" data="lstFileType" valueField="procedureId" labelField="name"
        name="createForm.Type" 
        cssStyle="display:none;width:70%;">
    </sd:SelectBox>
    
    --%>
</div>
<sd:TitlePane key="${fn:escapeXml(fileNameFull)}"
              id="fileNameFull" >
    <sx:ResultMessage id="resultMessage"/>
    <form id="createForm" name="createForm">
        <sd:TextBox key="" id="createForm.fileId" name="createForm.fileId" cssStyle="display:none" />
        <sd:TextBox key="" id="createForm.fileType" name="createForm.fileType" cssStyle="display:none" />
        <sd:TextBox key="" id="createForm.status" name="createForm.status" cssStyle="display:none" />
        <sd:TextBox key="" id="createForm.isTypeChange" name="createForm.isTypeChange" cssStyle="display:none" />
        <sd:TextBox key="" id="createForm.countUA" name="createForm.countUA" cssStyle="display:none" />
        <sd:Tab id="files_tab" height="750px" width="100%">
            <sd:ContentPane key="Bản công bố" id="tab.annoucement">
                <div id="tabAnnouncementDiv" style="overflow: auto;">
                    <jsp:include page="./fileTab/tabAnnouncement.jsp"/>
                </div>
            </sd:ContentPane>    
            <sd:ContentPane key="Thông tin chi tiết sản phẩm" id="tab.productDetail">
                <div id="tabProductDetailDiv" style="overflow: auto;">
                    <jsp:include page="./fileTab/tabProductDetailTHTL.jsp"/>
                </div>
            </sd:ContentPane>    
            <sd:ContentPane key="Tài liệu đính kèm" id="tab.attachs">
                <div id="tabAttachDiv" style="overflow: auto;">
                    <jsp:include page="./fileTab/tabAttachs.jsp"/>
                </div>
            </sd:ContentPane>               
            <sd:ContentPane key="Kế hoạch kiểm soát chất lượng" id="tab.qualityControlPlan">
                <div id="tabQualityControlDiv" style="overflow: auto;">
                    <jsp:include page="./fileTab/tabQualityControlPlan.jsp"/>
                </div>
            </sd:ContentPane>            
            <sd:ContentPane key="Nhập thông tin từ file Excel" id="tab.excel">
                <div id="tabExcel" style="overflow: auto;">
                    <jsp:include page="./fileTab/importFilesExcel.jsp"/>
                </div>
            </sd:ContentPane> 
        </sd:Tab>
    </form>     
</sd:TitlePane>
<script type="text/javascript">
    var ckInsert = false;
    page.afterCommit = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result0 = result[0];
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        if (result0 == "3") {
        } else {
            backPage();
            //page.search();
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

    page.insertFiles = function() {
        msg.confirm('Bạn có chắc chắn thực hiện lưu hồ sơ này ?', '<sd:Property>Cảnh báo</sd:Property>', page.saveFiles);
    };

//    page.checkExcel1 = function() {
//        var check = '${checkEdit}';
//        if (check == 1)
//        {
//            document.getElementById("checkExcel1").style.display = "none";
//        }
//    };
//    page.checkExcel1();

//hietq update 17.11.14
/*
    page.onTypeChange = function() {
        var fileType = dijit.byId("createForm.Type").getValue();
        var fileIdCopy = '${fileIdCopy}';
        //var fileId = dijit.byId("createForm.fileId").getValue();
        if (fileType == -1) {
            alert("Bạn chưa chọn loại hồ sơ muốn chuyển");
            return;
        }

        page.setCreateDiv(fileIdCopy, fileType);
    };
    page.showBtnTypeChange = function() {
        //var isChange = dijit.byId("createForm.isTypeChange").getValue();
        var checkCopy = '${isCopy}';
        //var status = dijit.byId("createForm.status").getValue();
        if (checkCopy) {
            dijit.byId("btnTypeChange").domNode.style.display = "";
            dijit.byId("createForm.Type").domNode.style.display = "";
        }
    };
    page.showBtnTypeChange();
*/
    page.insertFileDraff = function() {
        page.updateListDataBeforeSubmit();
        page.setAttachLabel();
        sd.connector.post("filesAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterCommit);
    };

</script>