<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<form id="profileFormOnDialog" name="profileForm">
    <table width="100%">
        <tr>
            <td>
                <sd:TextBox id="profileFormOnDialog.id" name="profileForm.id" key="profileForm.id" labelWidth="60%" inputWidth="39%" cssStyle="width:100%"/>
            </td>
        </tr>
        <tr>
            <td>
                <sd:TextBox id="profileFormOnDialog.allowIp" name="profileForm.allowIp" key="profileForm.allowIp" labelWidth="60%" inputWidth="39%" cssStyle="width:100%"/>
            </td>

        </tr>
        <tr>
            <td>
                <sd:TextBox id="profileFormOnDialog.allowLoginTimeEnd" name="profileForm.allowLoginTimeEnd" key="profileForm.allowLoginTimeEnd" labelWidth="60%" inputWidth="39%" cssStyle="width:100%"/>
            </td>

        </tr>
        <tr>
            <td>
                <sd:TextBox id="profileFormOnDialog.allowLoginTimeStart" name="profileForm.allowLoginTimeStart" key="profileForm.allowLoginTimeStart" labelWidth="60%" inputWidth="39%" cssStyle="width:100%"/>
            </td>
        </tr>
        <tr>
            <td>
                <sd:SelectBox id="profileFormOnDialog.allowMultiIpLogin" name="profileForm.allowMultiIpLogin" key="profileForm.allowMultiIpLogin" labelWidth="60%" inputWidth="39%" cssStyle="width:100%">
                <option value="1">Có cho phép</option>
                <option value="0">Không cho phép</option>
            </sd:SelectBox>
            </td>

        </tr>

        <tr>
            <td>
                <sd:TextBox id="profileFormOnDialog.loginFailAllow" name="profileForm.loginFailAllow" key="profileForm.loginFailAllow" labelWidth="60%" inputWidth="39%" cssStyle="width:100%"/>
            </td>

        </tr>
        <tr>
            <td>
                <sd:TextBox id="profileFormOnDialog.maxTmpLockAday" name="profileForm.maxTmpLockAday" key="profileForm.maxTmpLockAday" labelWidth="60%" inputWidth="39%" cssStyle="width:100%"/>
            </td>
        </tr>
        <tr>
            <td>
                <sd:TextBox id="profileFormOnDialog.name" name="profileForm.name" key="profileForm.name" labelWidth="60%" inputWidth="39%" cssStyle="width:100%"/>
            </td>

        </tr>
        <tr>
            <td>
                <sd:TextBox id="profileFormOnDialog.passwordValidTime" name="profileForm.passwordValidTime" key="profileForm.passwordValidTime" labelWidth="60%" inputWidth="39%" cssStyle="width:100%"/>
            </td>
        </tr>
        <tr>
            <td>
                <sd:TextBox id="profileFormOnDialog.temporaryLockTime" name="profileForm.temporaryLockTime" key="profileForm.temporaryLockTime" labelWidth="60%" inputWidth="39%" cssStyle="width:100%"/>
            </td>

        </tr>
        <tr>
            <td>
                <sd:TextBox id="profileFormOnDialog.userValidTime" name="profileForm.userValidTime" key="profileForm.userValidTime" labelWidth="60%" inputWidth="39%" cssStyle="width:100%"/>
            </td>
        </tr>
        <tr>
            <td>
                <sd:SelectBox id="profileFormOnDialog.needChangePassword" name="profileForm.needChangePassword" key="profileForm.needChangePassword" labelWidth="60%" inputWidth="39%" cssStyle="width:100%">
                <option value="1">Thiết lập</option>
                <option value="0">Không thiết lập</option>
            </sd:SelectBox>
            </td>

        </tr>
        <tr>
            <td>
                <sd:TextBox id="profileFormOnDialog.timeToChangePassword" name="profileForm.timeToChangePassword" key="profileForm.timeToChangePassword" labelWidth="60%" inputWidth="39%" cssStyle="width:100%"/>
            </td>
        </tr>
    </table>

    <table width="100%">
        <tr>
            <td style="text-align:center;">
                <sd:Button id="profileFormOnDialog.btnUpdate" key="" onclick="page.onUpdate()" >
                    <img src="share/images/icons/a8.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnUpdate</sd:Property></span>
                </sd:Button>
                <sd:Button id="profileFormOnDialog.btnInsert" key="" onclick="page.onInsert()" >
                    <img src="share/images/icons/a8.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                </sd:Button>
            </td>
        </tr>
    </table>

</form>

