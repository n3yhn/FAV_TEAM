<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sx:ResultMessage id="processResultMessage" />
<form id="filesStatusEditForm">
    <table width="100%;" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="30%"></td>
            <td width="70%"></td>
        </tr>
        <tr>
            <td colspan="2" style="display: none">
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="filesStatusEditForm.processId"
                            key=""
                            name="filesStatusEditForm.processId">
                </sd:TextBox>
            </td>
        </tr>
        <tr>
            <td align="left">
                <sx:Label key="status" require=""/>
            </td>
            <td align="left">
                <sx:Label key="processStatus" require=""/>
            </td></tr>  
        <tr>
            <td style="display:">
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="filesStatusEditForm.status"
                            key=""
                            name="filesStatusEditForm.status">
                </sd:TextBox>
            </td>
            <td style="display:">
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="filesStatusEditForm.processStatus"
                            key=""
                            name="filesStatusEditForm.processStatus">
                </sd:TextBox>
            </td>
        </tr>        
        <tr style="text-align: center">
            <td colspan="2">
                <sx:ButtonSave onclick="page.saveESF()" />
                <sx:ButtonClose onclick="page.onCloseESF()" />
            </td>
        </tr>
    </table>
</form>

<script>
    page.saveESF = function() {
        sd.connector.post("process!checkProcess.do", null, "filesStatusEditForm", null, page.afterSaveESF);
    };

    page.afterSaveESF = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var customInfo = obj.customInfo;

        var result0 = result[0];
        if (result0 == "3") {
            resultMessage_show("processResultMessage", result[0], result[1], 5000);
        } else {
            sd.connector.post("process!onSaveProcess.do?" + token.getTokenParamString(), null, "filesStatusEditForm", null, page.afterSaveProcess);
        }
    };

    page.afterSaveProcess = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result0 = result[0];
        if (result0 == "3") {
            resultMessage_show("processResultMessage", result[0], result[1], 5000);
        } else {
            page.getProcess(workingFileId);
            page.onClose();
            //grid.vtReload('category!search.do?', "categorySearchForm", null, null);
        }
    };

    page.close = function() {
        dlgCategory.hide();
        dijit.byId("filesStatusEditForm.categoryId").setValue("");
        dijit.byId("filesStatusEditForm.code").setValue("");
        dijit.byId("filesStatusEditForm.name").setValue("");
        dijit.byId("filesStatusEditForm.description").setValue("");
        dijit.byId("filesStatusEditForm.isActive").setValue("1");
    };

    page.onCloseESF = function() {
        msg.confirm('<sd:Property>confirm.close</sd:Property>', '<sd:Property>confirm.title</sd:Property>', page.closeExecuteESF);
            };
            page.closeExecuteESF = function() {
                dijit.byId("viewStatusFilesDlg").hide();
            };
</script>