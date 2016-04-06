<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
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

    String ua = request.getHeader("User-Agent");
    boolean isFirefox = (ua != null && ua.indexOf("Firefox/") != -1);
    boolean isMSIE = (ua != null && ua.indexOf("MSIE") != -1);
    boolean isNew = request.getSession(false).isNew();
%>
<s:i18n name="com/viettel/config/Language">
    <html>
        <body style="background-color: white !important; padding:2px">
            <h2>Dịch vụ công cấp 4</h2>
            <ul>
                <li>Tổng số hồ sơ đang xử lý: <span style="color: #ff0000">${countFileIsProcess}</span></li>
                <li>Tổng số hồ sơ chưa tiếp nhận: <span style="color: #ff0000">${countSelectNewHome}</span></li>
                <li>Tổng số hồ sơ đang chờ bổ sung: <span style="color: #ff0000">${countSelectEVALUATED_TO_ADD}</span></li>                
                <li>Tổng số hồ sơ đã trả kết quả: <span style="color: #ff0000">${countSelectGIVE_BACK}</span></li>
            </ul>
            <a href="http://congbosanpham.vfa.gov.vn/filesAction!toLookUpHomePage.do">Tra cứu thông tin hồ sơ đã công bố</a>
        </body>
    </html>        
</s:i18n>