<%@page import="com.viettel.common.util.StringUtils"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>


<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sd" uri="struts-dojo-tags"%>

<%
    ResourceBundle rb = ResourceBundle.getBundle("config");
    String projectVersion = rb.getString("project.version");
    String RDWFVersion = rb.getString("RDWF.version");
    String RDWFTheme = rb.getString("RDWF.theme");

    String requestTheme = StringUtils.escapeHtml(request.getParameter("request_theme"));

    request.setAttribute("vt_locale", StringUtils.escapeHtml(request.getParameter("request_locale")));
    request.setAttribute("JSLibTheme", (requestTheme != null) ? requestTheme : RDWFTheme);
    request.setAttribute("projectVersion", projectVersion);
    request.setAttribute("RDWFVersion", RDWFVersion);
    request.setAttribute("contextPath", request.getContextPath());
    request.setAttribute("CSS_JS_contextPath", request.getContextPath());
%>

<s:i18n name="com/viettel/config/Language">

    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <html>
        <head>
            <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
            <meta name="description" content="<s:property value="getText('projectName')"/>RDWF, RDWebFramework, RD Web FrameWork, R&D WebFrameWork, R&D Web Framework" />
            <title><s:property value="getText('projectName')"/></title>

            <script type="text/javascript">
                var g_contextPath = "${contextPath}";
                var g_latestClickedMenu = "";

                var targetDivInBodyLayout;
                var returnPageType;

                //[ Testing purpose
                var useMenuTest = true;
                var userProfileTest = false;
                //] Testing purprose
            </script>

            <!--[ LongH says: Main static resources (JS/CSS/IMG/HTML)-->
            <tiles:insertTemplate template="/WEB-INF/jsp/pages/layout/include.jsp" />
            <!--] LongH says: Main static resources (JS/CSS/IMG/HTML)-->
        </head>
        <body class="${fn:escapeXml(JSLibTheme)}" <%--onkeydown="checkToRefresh(event);"--%> >
            <div id="token">
            </div>
            <!--[ LongH says: RDFW features-->
            <div id="message-holder"></div>
            <!--] LongH says: RDFW features-->

            <!--[ LongH says: Main middle-->
            <div id="vt-content-wrapper-popup" class="no-space clear-both">
                <div id="vt-content" class="no-space">
                    <div id="bodyContent">
                        <tiles:insertAttribute name="body"/>
                    </div>
                    <div class="clear-both"></div>
                </div>
            </div>
            <!--] LongH says: Main middle-->

            <!--[ LongH says: RDFW features-->
            <div id="vt-loading-background"><div id="vt-loading-icon"></div></div>
            <div id="componentBody" style="display:none;"></div>
            <!--] LongH says: RDFW features-->

            <!--[ LongH says: Make menu-feed script-->
            <!--] LongH says: Make menu-feed script-->

            <!--[ LongH says: After-page-rendered script-->
            <script type="text/javascript">
                sd.operator.waitScreenDivId = "vt-loading-background";
                sd.operator.breadCumbDivId = "vt-breadCumb";
                sd.operator.errorDisplayDialogId = "vt-errorDisplayDialog";
                sd.operator.errorDisplayIframeId = "vt-errorDisplayIframe";
                sd.operator.errorDisplayDivId = "vt-errorDisplayDiv";

                
   //             var profileIcon = sd.$( "vt-profile" );

 //               var actionTitle = "<sd:Property><tiles:getAsString name='title'/></sd:Property>";
 //               var userName;

//                if(userProfileTest) {
//                    userName = "Michael Phelps";
//                } else {
//                    userName = "<s:property value='#session.userToken.fullName'/>";
//                }

               // updatePageInfo(userName, actionTitle);
                
//                try{
//                    dojo.require("vt.dialog.vtDialog");
//                    var msg = new vt.dialog.innerDialog();
//
//                }catch(e){
//                    //alert("Exception in loading vt.dialog.vtDialog\n" + e.message);
//                }
            </script>
            <!--] LongH says: After-page-rendered script-->

        </body>
    </html>
</s:i18n>