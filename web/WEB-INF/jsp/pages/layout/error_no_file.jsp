<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table align="center" width="80%" >
            <tr>
                <td align="center" >
                    <font color="red"><b>BỊ LỖI TRONG QUÁ TRÌNH DOWNLOAD !</b></font>
                    <br/>
                    Nguyên nhân có thể do file không còn tồn tại trên hệ thống hoặc bạn không được quyền download file
                    <br/>
                    Liên hệ quản trị để biết thêm
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
        <script type="text/javascript">
//            gotoExit = function(){
//                document.location.href = "${contextPath}/Exit.do";            
//            }
//            setTimeout(gotoExit, 2000);
        </script>
    </body>
</html>
