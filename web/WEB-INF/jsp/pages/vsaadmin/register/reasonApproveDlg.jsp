<%-- 
    Document   : reasonApproveDlg
    Created on : Jul 5, 2013, 9:45:19 AM
    Author     : vtit_binhnt53
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<sx:ResultMessage id="resultMessage" />
<form id="reasonApprove" name="reasonApprove">
    <table width="100%;" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="40%"></td>
            <td width="60%"></td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Lí do:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%"
                    id="reasonApprove.reason"
                            name="reasonApprove.reason"
                            key=""
                            maxlength="255"
                            />
            </td>
        </tr>
        <tr style="text-align: center">
            <td colspan="2">
                <sx:ButtonSave onclick="page.notApprove()" />
                <sx:ButtonClose onclick="page.hideNotApprove()" />
            </td>
        </tr>
    </table>
</form>
