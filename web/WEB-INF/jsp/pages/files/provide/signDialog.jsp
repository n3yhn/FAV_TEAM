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
            <td style="text-align: right"><sd:Label key="Ý kiến bút phê:" required="true"/></td>
            <td>                
                <sd:Textarea key="" id="signedForm.signContent" name="signedForm.signContent" rows="4" cssStyle="width:99%" trim="true"/>
                <sd:TextBox id="signedForm.status" name="signedForm.status" cssStyle="display:none" key=""/>
            </td>
        </tr>
        <tr>

            <td style="text-align: right"><sd:Label key="Ngày ký duyệt:" required="true"/></td>
            <td>
                <sx:DatePicker cssStyle="width:99%"
                               id="signedForm.signDate"
                               key=""
                               name="signedForm.signDate" format="dd/MM/yyyy"/>
                <sd:TextBox 
                    id="signedForm.fileId"
                    name="signedForm.fileId" 
                    cssStyle="display:none" 
                    key=""
                    trim="true"/>
            </td>
        </tr>
        
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="page.signed();"/>
                <sx:ButtonBack onclick="backPage();"/>  
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    page.afterSigning = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {            
            backPage();
        }
    };
    page.signExecute = function() {
        dijit.byId("signedForm.status").setValue(12);
        sd.connector.post("filesAction!onSign.do?" + token.getTokenParamString(), null, "signedForm", null, page.afterSigning);
    };
    page.signed = function(){
      msg.confirm('<sd:Property>Bạn có chắc chắn ký duyệt tài liệu này?</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.signExecute);  
    };
</script>