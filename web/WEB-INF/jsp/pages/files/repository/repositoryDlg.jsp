<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<form id="receivedForm" name="receivedForm">
    <sx:ResultMessage id="resultMessage" />
    <table width="100%" class="viewTable" cellpadding="0px" cellspacing="5px">
        <tr>
            <td  width="30%" style="text-align: right"><sd:Label key="Trạng thái lưu trữ:" required="true"/></td>
            <td width="70%">
                <div id="repository.repStatus"></div>
            </td>
        </tr>
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Tên kho:" required="true"/></td>
            <td width="70%">
                <div id="repository.repName"></div>
            </td>
        </tr>
        <tr>
            <td  width="30%" style="text-align: right"><sd:Label key="Địa điểm:" required="true"/></td>
            <td width="70%">
                <div id="repository.repAddress"></div>
            </td>
        </tr>
        <tr>
            <td width="25%"></td> 
            <td width="75%"></td>
        </tr>
      

        <tr>
            <td colspan="2" style="text-align: center">
             
                <sx:ButtonClose onclick="close();"/>  
            </td>
        </tr>
    </table>
</form>

<script>
    close = function()
    {
        dijit.byId("RepositoryDlg").hide();
    }
</script>
