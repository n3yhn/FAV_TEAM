<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<table width="100%" class="viewTable" id="tblFeedbackLeaderFormView">
    <tr>
        <td width="30%" style="text-align: right"><sd:Label key="Kết quả thẩm định"/></td>
        <td width="70%">
            <div id="evaluateFormView.status"></div>            
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

    onCloseEvaluateView = function(){
        dijit.byId("evaluateViewDlg").hide();
    };
    page.replaceNewLineByBr = function() {
        var table = document.getElementById("tblFeedbackLeaderFormView");
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