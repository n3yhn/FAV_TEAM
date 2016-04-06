<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<table width="100%" class="viewTable" id="tblReviewFormViewDetails">    
    <tr>
        <td width="30%" style="text-align: right"><sd:Label key="Kết quả xem xét"/></td>
        <td width="70%">
            <div id="evaluationRecordsForm.status"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Nội dung thẩm định"/></td>
        <td>
            <div id="evaluationRecordsForm.staffRequest"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Nội dung xem xét"/></td>
        <td>
            <div id="evaluationRecordsForm.leaderStaffRequest"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right" width="40%"><sd:Label key="Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)"/></td>
        <td width="60%">
            <div id="evaluationRecordsForm.legalL"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
        <td>
            <div id="evaluationRecordsForm.legalContentL"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Về chỉ tiêu chất lượng an toàn thực phẩm"/></td>
        <td>
            <div id="evaluationRecordsForm.foodSafetyQualityL"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
        <td>
            <div id="evaluationRecordsForm.foodSafetyQualityContentL"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng"/></td>
        <td>
            <div id="evaluationRecordsForm.effectUtilityL"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
        <td>
            <div id="evaluationRecordsForm.effectUtilityContentL"></div>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center">
            <sx:ButtonClose onclick="onCloseEvaluateDetailsView();"/>
        </td>
    </tr>
</table>
<script type="text/javascript">
    onCloseEvaluateDetailsView = function() {
        dijit.byId("reviewFormViewDetailsEvaluateDlg").hide();
    };
    page.replaceBrTblReviewFormViewDetails = function() {
        var table = document.getElementById("tblReviewFormViewDetails");
        var divs = table.getElementsByTagName("div");
        var i = 0;
        var content = "";
        for (i = 0; i < divs.length; i++) {
            content = divs[i].innerHTML;
            content = content.replace(/\n/g, "<br>");
            divs[i].innerHTML = content;
        }
    };
</script>