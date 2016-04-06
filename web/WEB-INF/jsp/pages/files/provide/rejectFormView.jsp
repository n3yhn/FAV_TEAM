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
        <td width="30%" style="text-align: right"><sd:Label key="Kết quả"/></td>
        <td width="70%">
            <div id="rejectFormView.status"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: right"><sd:Label key="Ý kiến từ chối phê duyệt"/></td>
        <td>
            <div id="rejectFormView.signContent"></div>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center">
            <sx:ButtonClose onclick="onCloseRejectView();"/>
        </td>
    </tr>
</table>

<script type="text/javascript">
    onCloseRejectView = function(){
        dijit.byId("rejectViewDlg").hide();
    };
    
</script>