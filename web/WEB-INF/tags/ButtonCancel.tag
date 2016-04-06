<%-- 
    Document   : CancelApproved
    Created on : Dec 7, 2011, 3:32:13 PM
    Author     : huantv
--%>

<%@tag description="CancelApproved Button" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="id"%>
<%@attribute name="onclick"%>
<%@attribute name="disable"%>

<sd:Button id="${id}" key="" onclick="${onclick}" disabled="${disable}">
    <img src="share/images/icons/unapproved.png" height="20" width="20" alt="Hủy bỏ">
    <span style="font-size:12px">Hủy bỏ</span>
</sd:Button>