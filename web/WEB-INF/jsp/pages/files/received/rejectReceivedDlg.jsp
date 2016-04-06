<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<form id="signedForm" name="signedForm">
    <sx:ResultMessage id="resultMessage" />
    <table width="100%" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="35%"></td>
            <td width="65%"></td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Lý do từ chối:" required="true"/></td>
            <td>                
                
            </td>
        </tr>
        <tr>

            <td style="text-align: right"><sd:Label key="Ngày từ chối:" required="true"/></td>
            <td>
                
            </td>
        </tr>
        
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="page.rejectReveived();"/>
                <sx:ButtonBack onclick="backPage();"/>  
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    page.rejectReceived = function() {
        var dlg = dijit.byId("rejectReceivedDlg");
        dlg.show();
    };
    page.afterRejectReveived = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {            
            backPage();
        }
    };
    page.rejectReveivedExecute = function() {
        dijit.byId("createForm.status").setValue(12);
        sd.connector.post("filesAction!onRejectReveived.do?" + token.getTokenParamString(), null, "createForm", null, page.afterRejectReveived);
    };
    page.rejectReveived = function(){
      msg.confirm('<sd:Property>Bạn có chắc chắn từ chối hồ sơ này này?</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.rejectReveivedExecute);  
    };
</script>