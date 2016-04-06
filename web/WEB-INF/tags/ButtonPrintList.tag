<%-- 
    Document   : AddButton
    Created on : Dec 7, 2011, 3:32:13 PM
    Author     : gpdn_huannn
--%>

<%@tag description="Add Button" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="id"%>
<%@attribute name="onclick"%>
<%@attribute name="disable"%>

<sd:Button id="${id}" key="" onclick="${onclick}" disabled="${disable}">
    <img src="share/images/icons/print.gif" height="14" width="18" alt="In danh sách">
    <span style="font-size:12px">In danh sách</span>
</sd:Button>