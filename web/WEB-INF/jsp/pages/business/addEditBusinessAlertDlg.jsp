<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sx:ResultMessage id="resultMessage" />
<form id="createBusinessAlertForm" name="createBusinessAlertForm">
    <table width="100%" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="25%"></td>
            <td width="75%"></td>
        </tr>
        <tr>
            <td>
                <sd:TextBox
                    id="createBusinessAlertForm.businessAlertId"
                    name="createBusinessAlertForm.businessAlertId"
                    cssStyle="display:none" key=""/>                
                <sd:TextBox
                    id="createBusinessAlertForm.businessId"
                    name="createBusinessAlertForm.businessId"
                    cssStyle="display:none" key=""/>
                <sx:Label key="Nội dung:" require="true"/>
            </td>
            <td>
                <sd:Textarea key="" 
                             id="createBusinessAlertForm.content" 
                             name="createBusinessAlertForm.content" 
                             maxlength="2000" 
                             cssStyle="width:98%"
                             trim="true" rows="10"/>
            </td>            
        </tr>
    </table>
    <div style="text-align: center">
        <sx:ButtonSave onclick="page.save();"></sx:ButtonSave>  
        <sx:ButtonClose onclick="page.close();"></sx:ButtonClose>  
    </div>
</form>

<script>
    page.save = function () {//add 29.7
        sd.connector.post("businessAlertAction!onInsert.do?" + token.getTokenParamString(), null, "createBusinessAlertForm", null, page.afterSave);
    };

    page.afterSave = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {
            page.close();
            page.getViewLstBusinessAlert(dijit.byId("createBusinessAlertForm.businessId").getValue());
        }
    };

    page.close = function () {
        dijit.byId("dlgAddEditBusinessAlert").hide();
    };
</script>