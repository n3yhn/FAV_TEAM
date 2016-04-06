<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags/"%>
<sx:ResultMessage id="resultMessage"/>
<div id="resetPasswordDiv">
    <form id="resetPassForm" name="resetPassForm">
        <sd:FieldSet key="usersForm.info" theme="blue">
            <table width="100%">
                <tr id="trPassword">
                    <td class="tdOnForm">
                        <sd:TextBox id="resetPassForm.password" name="resetPassForm.password" key="usersFormOnDialog.password" required="true"  cssStyle="width:80%" type="password"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:TextBox id="resetPassForm.retypePassword" name="resetPassForm.retypePassword" key="usersFormOnDialog.retypePassword" required="true" cssStyle="width:80%" type="password"/>
                    </td>
                </tr>
                <tr style="display:none;">
                    <td>
                        <sd:TextBox id="resetPassForm.userId" name="resetPassForm.userId" key="" />
                    </td>
                </tr>
            </table>
        </sd:FieldSet>
    </form>
</div>
<table width="100%">
    <tr>
        <td style="text-align:center;">
            <sd:Button id="" key="usersForm.reset" onclick="page.onResetPass();"/>
        </td>
    </tr>
</table>

