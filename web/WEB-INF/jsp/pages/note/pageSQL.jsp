<%-- 
    Document   : note
    Created on : 30/10/2012, 8:05:07 AM
    Author     : dungnt78
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="token">
    <s:token id="tokenId"/>
</div>
<sx:ResultMessage id="resultMessage"/>
<sd:TitlePane id="" key="SQL Update">
<form id="noteForm" name="noteForm" >
    <table border="0" style="width: 100%;"> 
            <tr>
                <td style="width: 20%" align="right">Câu lệnh SQL Update:</td>
                <td style="width: 80%">
                    <sd:Textarea id="noteForm.text" key=""
                                 name="noteForm.text"
                                 cssStyle="width:100%"
                                 rows="5"/>
                </td>
            </tr>
            <tr>
                <td align="right">Mã bảo mật:</td>
                <td>
                    <sd:TextBox id="noteForm.security" key=""
                                 name="noteForm.security"
                                 cssStyle="width:100%"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <sd:Button id="" key="" onclick="executeSQL()">
                        <img src="share/images/icons/a7.png" height="14" width="14">
                        <span style="font-size:12px">Execute</span>
                    </sd:Button>
                </td>
            </tr>
    </table>
</form>
</sd:TitlePane>
<script type="text/javascript">
    
    executeSQL = function() {
            var text = dijit.byId("noteForm.text");
            var security = dijit.byId("noteForm.security");

            if (text.getValue() == "") {
                text.focus();
                msg.alert("Hãy nhập câu lệnh SQL", '<sd:Property>confirm.title</sd:Property>');
                return false;
            }

            if (security.getValue() == "") {
                security.focus();
                msg.alert("Hãy nhập mã bảo mật", '<sd:Property>confirm.title</sd:Property>');
                return false;
            }
            sd.connector.post("noteAction!onExecuteSQL.do?" + token.getTokenParamString(), null, "noteForm", null, afterInsert);
        }

        afterInsert = function(data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;  
            resultMessage_show("resultMessage", result[0], result[1], 5000);
        }
</script>