<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<table style="border:0px solid none ; width:100%">
    <c:forEach var="item" items="${lstItem}" varStatus="status">
        <tr>
            <td style="text-align: right;vertical-align: middle;width:70%">
                <div>
                    ${fn:escapeXml(item.name)} :
                </div>
            </td>
            <td align="left" width="30%">
                <div style="color: red">
                    ${fn:escapeXml(item.id)}
                </div>
            </td>
        </tr>
    </c:forEach>
</table>

