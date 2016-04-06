<%-- 
    Document   : callbackTarget
    Created on : Mar 8, 2011, 8:35:09 AM
    Author     : cn_longh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Callback target iframe upload JSP page</title>
    </head>
    <body>
        <script>
            parent.page.callback("${varFromDAO}");
        </script>
    </body>
</html>

