<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sx:ResultMessage id="processResultMessage" />
<form id="processAddEditForm">
    <table width="100%;" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="30%"></td>
            <td width="70%"></td>
        </tr>
        <tr>
            <td colspan="2" style="display: none">
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="processAddEditForm.processId"
                            key=""
                            name="processAddEditForm.processId">
                </sd:TextBox>
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="processAddEditForm.sendUser"
                            key=""
                            name="processAddEditForm.sendUser">
                </sd:TextBox>
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="processAddEditForm.sendGroup"
                            key=""
                            name="processAddEditForm.sendGroup">
                </sd:TextBox>
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="processAddEditForm.receiveUser"
                            key=""
                            name="processAddEditForm.receiveUser">
                </sd:TextBox>
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="processAddEditForm.receiveGroup"
                            key=""
                            name="processAddEditForm.receiveGroup">
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
                <sd:SelectBox cssStyle="width:100%"
                              id="processAddEditForm.status"
                              key="" valueField="code" labelField="name"
                              name="processAddEditForm.status" >
                </sd:SelectBox>
            </td>
            <td style="display:">                
                <sd:SelectBox cssStyle="width:100%"
                              id="processAddEditForm.processStatus"
                              key="" valueField="code" labelField="name"
                              name="processAddEditForm.processStatus" >
                </sd:SelectBox>
            </td>
        </tr> 
        <tr>
            <td style="display:">
                <sx:Label key="sendUserId" require=""/>
            </td>
            <td style="display:">
                <sd:SelectBox cssStyle="width:100%"
                              id="processAddEditForm.sendUserId"
                              key="" valueField="code" labelField="name"
                              name="processAddEditForm.sendUserId" >
                </sd:SelectBox>
            </td>
        </tr>
        <tr>
            <td align="left">
                <sx:Label key="sendGroupId" require=""/>
            </td>
            <td align="left">    
                <sd:SelectBox cssStyle="width:100%"
                              id="processAddEditForm.sendGroupId"
                              key="" valueField="code" labelField="name"
                              name="processAddEditForm.sendGroupId" >
                </sd:SelectBox>
            </td>
        </tr>          
        <tr>
            <td align="left">
                <sx:Label key="receiveUserId" require=""/>
            </td>
            <td align="left">  
                <sd:SelectBox cssStyle="width:100%"
                              id="processAddEditForm.receiveUserId"
                              key="" valueField="code" labelField="name"
                              name="processAddEditForm.receiveUserId" >
                </sd:SelectBox>
            </td>
        </tr>
        <tr>
            <td style="display:">
                <sx:Label key="receiveGroupId" require=""/>
            </td>
            <td style="display:">
                <sd:SelectBox cssStyle="width:100%"
                              id="processAddEditForm.receiveGroupId"
                              key="" valueField="code" labelField="name"
                              name="processAddEditForm.receiveGroupId" >
                </sd:SelectBox>                
            </td>
        </tr>     
        <tr style="text-align: center">
            <td colspan="2">
                <sx:ButtonSave onclick="page.saveEPdlg()" />
                <sx:ButtonClose onclick="page.onCloseEPdlg()" />
            </td>
        </tr>
    </table>
</form>

<script>
    page.saveEPdlg = function () {
        sd.connector.post("process!checkProcess.do", null, "processAddEditForm", null, page.afterSaveEPdlg);
    };

    page.afterSaveEPdlg = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var customInfo = obj.customInfo;

        var result0 = result[0];
        if (result0 == "3") {
            resultMessage_show("processResultMessage", result[0], result[1], 5000);
        } else {
            sd.connector.post("process!onSaveProcess.do?" + token.getTokenParamString(), null, "processAddEditForm", null, page.afterOnSaveProcessEPdlg);
        }
    };

    page.afterOnSaveProcessEPdlg = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result0 = result[0];
        resultMessage_show("processResultMessage", result[0], result[1], 5000);
        page.getViewProcess(workingFileId);
        if (result0 == "3") {
//            resultMessage_show("processResultMessage", result[0], result[1], 5000);
        } else {
            page.onCloseEPdlg();
            //grid.vtReload('category!search.do?', "categorySearchForm", null, null);
        }
    };

    page.onCloseEPdlg = function () {
        msg.confirm('<sd:Property>confirm.close</sd:Property>', '<sd:Property>confirm.title</sd:Property>', page.closeExecuteEPdlg);
            };
            page.closeExecuteEPdlg = function () {
                dijit.byId("dlgEditProcess").hide();
            };
</script>