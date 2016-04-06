<%-- 
    Document   : AddButton
    Created on : Dec 7, 2011, 3:32:13 PM
    Author     : huantv
--%>

<%@tag description="Save Button" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="id"%>
<%@attribute name="onclick"%>

<sd:Button id="${id}" key="" onclick="${onclick}">
    <img src="share/images/icons/process_icon.png" height="14" width="18" alt="Gửi cung cấp">
    <span style="font-size:12px">Gửi cung cấp</span>
</sd:Button>