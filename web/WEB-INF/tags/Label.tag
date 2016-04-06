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
<%@attribute name="key"%>
<%@attribute name="require"%>
<%
            request.setAttribute("require", require);
%>

<s:if test="%{#request.require=='true'}">
    <sd:Label id="${id}" key="${key}"/>  <span style="color:red">*</span>
</s:if>
<s:else>
    <sd:Label id="${id}" key="${key}"/>
</s:else>

