<%-- 
    Document   : evaluateForm
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<table width="100%" class="viewTable" id="tbleComparisonFormViewDetails">    
    <tr>
        <td style="text-align: right" width="40%"><sd:Label key="Kết quả đối chiếu:"/></td>
        <td width="60%">
            <div id="filesForm.isComparison"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right" width="40%"><sd:Label key="Nội dung đối chiếu:"/></td>
        <td width="60%">
            <div id="filesForm.comparisonContent"></div>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center">
            <sx:ButtonClose onclick="onCloseComparisonDetailsView();"/>
        </td>
    </tr>
</table>

<script type="text/javascript">
    onCloseComparisonDetailsView = function() {
        dijit.byId("comparisonViewDetailsDlg").hide();
    };
    page.replaceTbleComparisonFormViewDetails = function() {
        var table = document.getElementById("tbleComparisonFormViewDetails");
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