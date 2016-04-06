<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<form id="rejectForm" name="rejectForm">
    <sx:ResultMessage id="resultMessage" />
    <table width="100%" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="35%"></td>
            <td width="65%"></td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Lí do từ chối:" required="true"/></td>
            <td>                
                <sd:Textarea key="" id="rejectForm.signContent" name="rejectForm.signContent" rows="4" cssStyle="width:99%" trim="true"/>
                <sd:TextBox id="rejectForm.status" name="rejectForm.status" cssStyle="display:none" key=""/>
            </td>
        </tr>
        <tr>

            <td style="text-align: right"><sd:Label key="Ngày quyết định:" required="true"/></td>
            <td>
                <sx:DatePicker cssStyle="width:99%"
                               id="rejectForm.signDate"
                               key=""
                               name="rejectForm.signDate" format="dd/MM/yyyy"/>
                <sd:TextBox 
                    id="rejectForm.fileId"
                    name="rejectForm.fileId" 
                    cssStyle="display:none" 
                    key=""
                    trim="true"/>
            </td>
        </tr>
        
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="page.reject();"/>
                <sx:ButtonBack onclick="backPage();"/>  
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    page.afterReject = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {            
            backPage();
        }
    };
    page.rejectExecute = function() {
        sd.connector.post("filesAction!onReject.do?" + token.getTokenParamString(), null, "rejectForm", null, page.afterReject);
    };
    page.reject = function(){
      msg.confirm('<sd:Property>Bạn có chắc từ chối tài liệu này?</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.rejectExecute);  
    };
</script>