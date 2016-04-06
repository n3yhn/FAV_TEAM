<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sd" uri="struts-dojo-tags" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<s:i18n name="com/viettel/config/Language">
    <html >
        <head>
            <tiles:insertTemplate template="/WEB-INF/jsp/pages/layout/external_CSS_JS.jsp" />
            <link href="share/homepage/images/logotitle.png" type="image/x-icon" rel="shortcut icon" />
        </head>
        <body class="tadalo">
    		<div id="vt-loading-background">
                <div id="vt-loading-icon"></div>
            </div>
            <div id="bodyFrame-body" class="bodyFrame-body">
                <div id="bodyFrame-body-inner" class="bodyFrame-body-inner">
                    <tiles:insertAttribute name="menu"/>
                    <div id="bodyContent" >
                        <tiles:insertAttribute name="body"/>
                    </div>
                </div>
            </div>
            <script type="text/javascript">
                parent.updatePageInfo( "<s:property value="#session.userToken.fullName"/>", '<sd:Property><tiles:getAsString name="title"/></sd:Property>');
            </script>
        </body>
    </html>
</s:i18n>

<div style="text-align:center; padding-top:50px;">
    <span style="color:#99caff;">VSA Admin v3.0</span> <br />
    Based on <span style="color:#99bbe8;">Struts 2.0, Hibernate 3.3.2, DojoToolKit 1.3.2</span> <br />
    Developed by <span style="color:#99bbe8;">Platform Division, R&D Department, Software Center, Viettel Telecom</span>
</div>