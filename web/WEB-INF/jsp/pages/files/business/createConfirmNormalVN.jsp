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
    <%-- <sx:ButtonSave onclick="page.saveFiles();"/> --%>
    <sx:ButtonSave onclick="page.insertFiles();"/>
    <sx:ButtonSaveDraff onclick="page.insertFileDraff();"/>
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
                    <jsp:include page="./fileTab/tabAnnouncementForConfirm.jsp"/>
                </div>
            </sd:ContentPane>    
            <sd:ContentPane key="Thông tin chi tiết sản phẩm" id="tab.productDetail">
                <div id="tabProductDetailDiv" style="overflow: auto;">
                    <jsp:include page="./fileTab/tabProductDetailTH.jsp"/>
                </div>
            </sd:ContentPane>    
            <sd:ContentPane key="Tài liệu đính kèm" id="tab.attachs">
                <div id="tabAttachDiv" style="overflow: auto;">
                    <jsp:include page="./fileTab/tabAttachs.jsp"/>
                </div>
            </sd:ContentPane>

            <sd:ContentPane key="Nhập thông tin từ file Excel" id="tab.excel">
                <div id="tabExcel" style="overflow: auto;">
                    <jsp:include page="./fileTab/importFilesExcel03.jsp"/>
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

    page.insertFileDraff = function() {
        page.updateListDataBeforeSubmit();
        page.setAttachLabel();
        sd.connector.post("filesAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterCommit);
    }


</script>