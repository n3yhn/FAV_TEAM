<%-- 

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    var msg="<%=request.getAttribute("msg")%>";
    var action= "<%=request.getAttribute("action")%>";

    window.parent.afterCb(msg, action);
</script>