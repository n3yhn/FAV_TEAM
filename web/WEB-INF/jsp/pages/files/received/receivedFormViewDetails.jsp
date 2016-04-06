<%-- 
    Document   : view chi tiet y kien yeu cau bo sung
    Created on : 
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<table width="100%" class="viewTable" id="tblReceivedFormViewDetails">
    <tr style="display: none">
        <td width="30%" style="text-align: right"><sd:Label key="Kết quả thẩm định"/></td>
        <td width="70%">
            <div id="receivedFormViewDetails.status"></div>            
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Nội dung yêu cầu bổ sung"/></td>
        <td>
            <div id="receivedFormViewDetails.clericalRequest"></div>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center">
            <sx:ButtonClose onclick="onCloseReceivedFormViewDetails();"/>
        </td>
    </tr>
</table>

<script type="text/javascript">

    onCloseReceivedFormViewDetails = function() {
        document.getElementById("receivedFormViewDetails.status").innerHTML = "";
        document.getElementById("receivedFormViewDetails.clericalRequest").innerHTML = "";
        dijit.byId("receivedFormViewDetailsDlg").hide();
    };
    page.replaceTblReceivedFormViewDetails = function() {
        var table = document.getElementById("tblReceivedFormViewDetails");
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