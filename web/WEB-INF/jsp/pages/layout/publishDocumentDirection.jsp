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
    request.setAttribute("externalResourcesPath", request.getAttribute("contextPath"));
    request.setAttribute("JSVTFolderName", request.getAttribute("externalResourcesPath") + "/share/js");

    String ua = request.getHeader("User-Agent");
    boolean isFirefox = (ua != null && ua.indexOf("Firefox/") != -1);
    boolean isMSIE = (ua != null && ua.indexOf("MSIE") != -1);
    boolean isNew = request.getSession(false).isNew();
%>
<s:i18n name="com/viettel/config/Language">
    <% if (isMSIE) {%>
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
    <% } else {%>
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
    <% }%>
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
            <tiles:insertTemplate template="/WEB-INF/jsp/pages/layout/include_website.jsp" />
            <!--] LongH says: Main static resources (JS/CSS/IMG/HTML)-->
        </head>

        <body class="${fn:escapeXml(JSLibTheme)}">

            <!--            <div id="token">
                        </div>-->
            <!--[ LongH says: RDFW features-->
            <!--div id="message-holder" style="text-align: center; font-weight: bold"></div-->
            <!--] LongH says: RDFW features-->
            <!--[ LongH says: Main header-->
            <div id="vt-header-wrapper" class="no-space">
                <div id="vt-projectBar" class="no-space">
                    <div id="vt-projectName" class="no-space float-ht"></div>
                </div>
                <div id="vt-titleBar" style="display:none" >
                    <div id="vt-iconAction">
                        <div id="vt-titleAction" class="float-left"><s:property value="getText('loadingText')"/></div>
                        <div id="vt-mainfunction" class="mainFunctionMenu float-right float-bottom"></div>
                        <div id="vt-breadCumb" class="no-float float-left" style="clear: left">Root</div>
                    </div>
                </div>
            </div>
            <!--] LongH says: Main header-->

            <!--[ LongH says: Main middle-->
            <div id="vt-loading-background"><div id="vt-loading-icon"></div></div>
            <div id="vt-content-wrapper" class="no-space clear-both" style="align:right">
                <div id="vt-content" class="no-space">
                    <div id="bodyContent" >
                        <tiles:insertAttribute name="body"/>  
                    </div>
                    <div class="clear-both"></div>
                </div>
            </div>
            <!--] LongH says: Main middle-->

            <!--[ LongH says: Main footer-->
            <div id="vt-footer" class="no-space clear-both">
                <div id="vt-copyRite" style="text-align:center;margin-top:2px;">
                    <br/>
                    Hệ thống cấp giấy tiếp nhận bản công bố hợp quy và giấy xác nhận công bố phù hợp quy định ATTP
                    <br/>
                    Địa chỉ: 138A Giảng Võ - Ba Đình - Hà Nội
                    <br/>
                    Điện thoại: 04.38464489; 04.38463702; Fax: 04.38463739; Email: vfa@vfa.gov.vn

                </div>
                <!--<div id="vt-poweredByIcon" class="float-right">
                        <div id="vt-poweredByText">${RDWFVersion}</div>
                    </div>-->
            </div>
            <!--] LongH says: Main footer-->

            <!--[ LongH says: RDFW features-->
            <div id="componentBody" style="display:none;"></div>
            <!--] LongH says: RDFW features-->

            <!--[ LongH says: Make menu-feed script-->
            <!--tiles:insertAttribute name="menu"/-->
            <!--] LongH says: Make menu-feed script-->
            <sd:Dialog id="vt-errorDisplayDialog" key="errorDisplayDialogDefaultTitle">
                <div id="vt-errorDisplayDiv"></div>
                <iframe name="vt-errorDisplayIframe" id="vt-errorDisplayIframe" frameborder="0"></iframe>
            </sd:Dialog>



            <!--[ LongH says: After-page-rendered script-->
            <script type="text/javascript">
                sd.operator.waitScreenDivId = "vt-loading-background";
                sd.operator.errorDisplayDialogId = "vt-errorDisplayDialog";
                sd.operator.errorDisplayIframeId = "vt-errorDisplayIframe";
                sd.operator.errorDisplayDivId = "vt-errorDisplayDiv";
                
                loadContent = function(){
                    sd.connector.post("home!getDocumentDirectionPortalContent.do","bodyContent");
                }
                
                try{
                    dojo.require("vt.dialog.vtDialog");
                    var msg = new vt.dialog.innerDialog();

                }catch(e){
                    alert("Exception in loading vt.dialog.vtDialog\n" + e.message);
                }
                
                //dojo.ready(loadContent);
            </script>
            <!--] LongH says: After-page-rendered script-->
        </body>
    </html>
</s:i18n>