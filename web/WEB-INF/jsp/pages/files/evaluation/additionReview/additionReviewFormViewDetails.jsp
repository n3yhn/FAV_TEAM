<%-- 
    Document   : evaluateForm
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<table width="100%" class="viewTable">    
    <tr>
        <td style="text-align: right" width="40%"><sd:Label key="Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)"/></td>
        <td width="60%">
            <div id="evaluationRecordsForm.legal"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
        <td>
            <div id="evaluationRecordsForm.legalContent"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Về chỉ tiêu chất lượng an toàn thực phẩm"/></td>
        <td>
            <div id="evaluationRecordsForm.foodSafetyQuality"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
        <td>
            <div id="evaluationRecordsForm.foodSafetyQualityContent"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng"/></td>
        <td>
            <div id="evaluationRecordsForm.effectUtility"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
        <td>
            <div id="evaluationRecordsForm.effectUtilityContent"></div>
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
        dijit.byId("evaluateViewDetailsDlg").hide();
    };
</script>