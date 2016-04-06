<%-- 
    Document   : evaluateForm
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<table width="100%" class="viewTable" id="tblReviewFormViewDetailsEvaluate">    
    <tr>
        <td style="text-align: right" width="40%"><sd:Label key="Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung"/></td>
        <td width="60%">
            <div id="ctkqthamdinhDlg.status"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
        <td>
            <div id="ctkqthamdinhDlg.staffRequest"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right" width="40%"><sd:Label key="Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)"/></td>
        <td width="60%">
            <sd:TextBox key="" id="ctkqthamdinhDlg.fileId" name="ctkqthamdinhDlg.fileId" cssStyle="display:none" />
            <div id="ctkqthamdinhDlg.legal"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
        <td>
            <div id="ctkqthamdinhDlg.legalContent"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Về chỉ tiêu chất lượng an toàn thực phẩm"/></td>
        <td>
            <div id="ctkqthamdinhDlg.foodSafetyQuality"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
        <td>
            <div id="ctkqthamdinhDlg.foodSafetyQualityContent"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng"/></td>
        <td>
            <div id="ctkqthamdinhDlg.effectUtility"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
        <td>
            <div id="ctkqthamdinhDlg.effectUtilityContent"></div>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center">
            <sd:Button id="btnExportEvaluation" key="" onclick="page.checkEvaluationFile();" cssStyle="display:" cssClass="buttonGroup">
                <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Xuất biên bản thẩm xét"/>
                <span style="font-size:12px">Xuất biên bản thẩm xét</span>
            </sd:Button>    
            <sx:ButtonClose onclick="onCloseEvaluateDetailsView();"/>
        </td>
    </tr>
</table>

<script type="text/javascript">
    onCloseEvaluateDetailsView = function() {
        dijit.byId("ctkqthamdinhDlg").hide();
    };
    page.replaceTblReviewFormViewDetailsEvaluate = function() {
        var table = document.getElementById("tblReviewFormViewDetailsEvaluate");
        var divs = table.getElementsByTagName("div");
        var i = 0;
        var content = "";
        for (i = 0; i < divs.length; i++) {
            content = divs[i].innerHTML;
            content = content.replace(/\n/g, "<br>");
            divs[i].innerHTML = content;
        }
    };
    page.checkEvaluationFile = function() {//kiem tra ton tai cua phieu tham dinh ho so      
        var fileId = dijit.byId("ctkqthamdinhDlg.fileId").getValue();
        if (fileId > 0) {
            sd.connector.post("filesAction!onCheckEvaluationFile.do?objectId=" + fileId, null, null, null, page.afterCheck);
        } else {
            sd.connector.post("filesAction!onCheckEvaluationFile.do?", null, "createForm", null, page.afterCheck);
        }
    }
    page.afterCheck = function(data) {//sau khi kiem tra ton tai ket qua tham dinh
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result0 = result[0];
        if (result0 == "3") {
            alert("Không có dữ liệu thẩm xét hồ sơ");
        } else {
            page.downloadEvaluationFile();
        }
    };
    page.downloadEvaluationFile = function() {//xuat file ket qua tham dinh
        var fileIdInForm = dijit.byId("ctkqthamdinhDlg.fileId").getValue();
        if (fileIdInForm > 0) {
            document.location = "exportWord!onExportEvaluation.do?fileId=" + fileIdInForm;
        } else {
            var fileId = dijit.byId("createForm.fileId").getValue();
            document.location = "exportWord!onExportEvaluation.do?fileId=" + fileId;
        }
    };
</script>