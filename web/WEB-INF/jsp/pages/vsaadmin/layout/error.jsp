<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hệ thống quản trị và phân quyền người dùng 3.0</title>
        <script type="text/javascript">
            document.location.href = "${contextPath}/share/logout.jsp";
        </script>
    </head>
    <body>
        <table align="center" width="80%" >
            <tr>
                <td align="center" >
                    <font color="red">
                        <b>CÓ LỖI XẢY RA!</br></br>
                        <s:property value="#attr.message"/></br></br>
                        <%--<a href="/vsaadminv3">Quay lại trang chủ</a>--%>
                        </b>
                    </font>
                </td>
            </tr>
        </table>
    </body>
</html>
