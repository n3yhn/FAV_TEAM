<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            document.location.href = "${contextPath}/share/logout.jsp";
        </script>
    </head>
    <body>
        <table align="center" width="80%" >
            <tr>
                <td align="center" >
                    <font color="red"><b>CÓ LỖI XẢY RA, HÃY LIÊN HỆ VỚI QUẢN TRỊ ĐỂ ĐƯỢC HỖ TRỢ !</b></font>
                    <s:property value="exception.message"/>
                    <s:property value="exceptionStack"/>
                </td>
            </tr>
            
            <tr>
                <td align="center">
                    
                    <s:if test="actionErrors != null && actionErrors.size > 0">
                        <s:actionerror/>
                    </s:if>
                </td>
            </tr>
            <tr>
                <td>
                    <div>
                        <s:property value="%{#exception}"/>
                    </div>
                </td>
            </tr>
        </table>
        <%--
    This example uses JSTL, uncomment the taglib directive above.
    To test, display the page like this: index.jsp?sayHello=true&name=Murphy
    --%>
        <%--
    <c:if test="${param.sayHello}">
        <!-- Let's welcome the user ${param.name} -->
        Hello ${param.name}!
    </c:if>
        --%>
        
    </body>
</html>
