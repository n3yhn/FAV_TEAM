<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib  prefix="sx" uri="struts-dojo-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<s:i18n name="com/viettel/config/Language">
    <html >
        <head>
            <tiles:insertTemplate template="/WEB-INF/jsp/pages/layout/external_CSS_JS.jsp" />
        </head>

        <body style="background-image:none;background-color:#fff;">
            <div class="popup-Wrapper">
                <div class="popup-inner">
                    <div class="pageTitle">
                        <div id="pageTitleLabel">
                        <%--<tiles:getAsString name="title"/>huynd--%>
                        </div>
                        <span id="pageMessageSpan">
                            <span id="glbWarningSpan" class="glbWarningSpan_out" onclick="shortcutGlbMsg( this );"></span>
                            <span id="glbAlertSpan" class="glbAlertSpan_out" onclick="shortcutGlbMsg( this );"></span>
                            <span id="glbNoticeSpan" class="glbNoticeSpan_out" onclick="shortcutGlbMsg( this );"></span>
                            <span id="glbMessageSpan" class="glbMessageSpan_out" onclick="shortcutGlbMsg( this );"></span>
                        </span>
                    </div>
                    <div id="popupVCL" class="popup">
                        <%--
                        <div class="popup-top-left">
                            <div class="popup-top-right">
                                <div class="popup-top-middle">
                                </div>
                            </div>
                        </div>
                        <div class="popup-body-left">
                            <div class="popup-body-right">
                                <div class="popup-body-middle">
                                    <div id="popupVCL-content">
                                    --%>
                                        <%--<sx:div id="bodyContent" >huynd--%>
                                            <tiles:insertAttribute name="body"/>
                                        <%--</sx:div>--%>
                                    <%--
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="popup-bottom-left">
                            <div class="popup-bottom-right">
                                <div class="popup-bottom-middle"></div>
                            </div>
                        </div>
                        --%>
                    </div>
                </div>
            </div>

            <%--<tiles:insertTemplate template="/WEB-INF/jsp/pages/layout/core_HTML.jsp" />huynd--%>

        </body>
    </html>
</s:i18n>
