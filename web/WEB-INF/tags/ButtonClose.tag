<%-- 
    Document   : ButtonAdd
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
<%@attribute name="cssStyle"%>
<sd:Button id="${id}" key="" onclick="${onclick}" disabled="${disable}" cssStyle="${cssStyle}">
    <img src="share/images/icons/13.png" height="14" width="14" alt="Đóng">
    <span style="font-size:12px">Đóng</span>
</sd:Button>