<%-- 
    Document   : approveForm
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<table width="100%" class="viewTable">
    <tr>
        <td width="30%" style="text-align: right"><sd:Label key="Kết quả phê duyệt"/></td>
        <td width="70%">
            <div id="approveFormView.status"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Nội dung trình ký"/></td>
        <td>
            <div id="approveFormView.signingContent"></div>
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
        dijit.byId("signingViewDlg").hide();
    };
    
</script>