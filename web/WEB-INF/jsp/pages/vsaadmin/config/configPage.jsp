<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<sd:TitlePane key="menu.system.config.config" id="tltpn1">
    <table width="100%">
        <tr>
            <td>
                <sd:TextBox id="departmentForm.deptName1" name="departmentForm.deptName1" key="config.passwordTime" cssStyle="width:80%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td>
                <sd:TextBox id="departmentForm.deptName2" name="departmentForm.deptName2" key="config.timeLock" cssStyle="width:80%" trim="true"/>
            </td></tr>
        <tr>
            <td>
                <sd:TextBox id="departmentForm.deptName3" name="departmentForm.deptName3" key="config.timeTLock" cssStyle="width:80%" trim="true"/>
            </td></tr>
        <tr>
            <td>
                <sd:TextBox id="departmentForm.deptName4" name="departmentForm.deptName4" key="config.loginFailedCount" cssStyle="width:80%" trim="true"/>
            </td></tr>
        <tr>
            <td>
                <sd:TextBox id="departmentForm.deptName5" name="departmentForm.deptName5" key="config.lockCount" cssStyle="width:80%" trim="true"/>
            </td></tr>
        <tr>
            <td>
                <sd:TextBox id="departmentForm.deptName6" name="departmentForm.deptName6" key="config.loginTry" cssStyle="width:80%" trim="true"/>
            </td></tr>
        <tr>
            <td>
                <sd:TextBox id="departmentForm.deptName7" name="departmentForm.deptName7" key="config.connection" cssStyle="width:80%" trim="true"/>
            </td>
        </tr>
    </table>
    <div align="center">
        <sd:Button id="departmentForm.btnlistUser" key="btnCommit" onclick="page.listUserOfDept()"/>
    </div>
</sd:TitlePane>