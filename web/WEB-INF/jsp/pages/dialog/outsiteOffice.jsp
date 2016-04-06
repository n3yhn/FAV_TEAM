<%-- 
    Document   : outsiteOffice
    Created on : May 10, 2012, 5:40:37 PM
    Author     : user
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="" style="width:100%; overflow:auto">
    <sd:MultiSelect id="outsiteOffice" 
                    key="" 
                    name="outsiteOffice" 
                    data="outsiteOfficeList" 
                    labelField="officeName" valueField="officeId"
                    cssStyle="height: 300px;width:98%;" 
                    />                    
</div>
