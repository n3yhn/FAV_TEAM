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
        <td width="30%" style="text-align: right"><sd:Label key="Kết quả xem xét"/></td>
        <td width="70%">
            <div id="evaluateFormView.status"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Nội dung thẩm định"/></td>
        <td>
            <div id="evaluateFormView.staffRequest"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Nội dung xem xét"/></td>
        <td>
            <div id="evaluateFormView.leaderStaffRequest"></div>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center">
            <sx:ButtonClose onclick="onCloseEvaluateView();"/>
        </td>
    </tr>
</table>
<script type="text/javascript">

    onCloseEvaluateView = function() {
        dijit.byId("evaluateViewDlg").hide();
    };   
</script>