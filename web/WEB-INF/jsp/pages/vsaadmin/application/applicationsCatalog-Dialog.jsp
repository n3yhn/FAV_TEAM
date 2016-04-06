<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<div id="messageArea"></div>
<form id="applicationsFormOnDialog" name="applicationsForm">
    <table width="100%">
        <tr>
            <td>
                <sd:TextBox id="applicationsFormOnDialog.appCode" name="applicationsForm.appCode" key="applicationsForm.appCode" cssStyle="width:100%" maxlength="50" required="true"/>
            </td>
        </tr>
        <tr>
            <td>
                <sd:TextBox id="applicationsFormOnDialog.appName" name="applicationsForm.appName" key="applicationsForm.appName" cssStyle="width:100%" maxlength="100" required="true"/>
            </td>
        </tr>
        <tr>
            <td>
                <sd:Textarea rows="4" maxlength="200" id="applicationsFormOnDialog.description" name="applicationsForm.description" key="applicationsForm.description" cssStyle="width:100%;" />
            </td>
        </tr>
        <tr>
            <td valign="top">
                <sd:SelectBox key="applicationsForm.status" name="applicationsForm.status" id="applicationsFormOnDialog.status" cssStyle="width:100%;" required="true">
                    <option value="1"><sd:Property>slt.active</sd:Property></option>
                    <option value="0"><sd:Property>slt.deactive</sd:Property></option>
                </sd:SelectBox>
            </td>
        </tr>
        <tr style="display:none;">
            <td>
                <sd:TextBox id="applicationsFormOnDialog.appId" name="applicationsForm.appId" key="applicationsForm.appId" cssStyle="width:80%;"/>
            </td>
        </tr>
    </table>

    <table width="100%">
        <tr>
            <td style="text-align:center;">
                <sd:Button key="" onclick="page.submitModifiedData();" >
                    <img src="share/img/save.png" height="17" width="17">
                    <span style="font-size:12px"><sd:Property>btn.apply</sd:Property> </span>
                </sd:Button>
                <sd:Button key="" onclick="page.close();" >
                    <img src="share/img/close.png" height="17" width="17">
                    <span style="font-size:12px"><sd:Property>btn.close</sd:Property> </span>
                </sd:Button>
                <!--<sd:Button id="applicationsFormOnDialog.btnUpdate" key="btnUpdate" onclick="page.onUpdate()"/>
                <sd:Button id="applicationsFormOnDialog.btnInsert" key="btnInsert" onclick="page.onInsert()"/>-->
            </td>
        </tr>
    </table>

</form>

