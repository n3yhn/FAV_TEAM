<%-- 
    Document   : home
    Created on : Jun 29, 2012, 10:06:31 AM
    Author     : HanPT1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<jsp:include page="../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="token">
    <s:token id="tokenId"/>
</div> 
<table width="100%">
    <tr>
        <td>
            <table class="homeTable">
                <tr>
                    <td>
                <li><a href="http://vfa.gov.vn/HDSD_QT01_13004_HQMC_ATTP_Vanthu_v1.1.rar">Tài liệu hướng dẫn sử dụng cho Cán bộ vai trò Văn thư</a></li>
                <li><a href="http://vfa.gov.vn/HDSD_QT01_13004_HQMC_ATTP_Lanhdaophong_v1.1.rar">Tài liệu hướng dẫn sử dụng cho Cán bộ vai trò Lãnh đạo phòng</a></li>
                <li><a href="http://vfa.gov.vn/HDSD_QT01_13004_HQMC_ATTP_LanhdaoCuc_ v1.1.rar">Tài liệu hướng dẫn sử dụng cho Cán bộ vai trò Lãnh đạo cục</a></li>
                <li><a href="http://vfa.gov.vn/HDSD_QT01_13004_HQMC_ATTP_KeToan_v1.0.rar">Tài liệu hướng dẫn sử dụng cho Cán bộ vai trò Kế toán</a></li>
                <li><a href="http://vfa.gov.vn/HDSD_QT01_13004_HQMC_ATTP_Chuyenvien_v1.1.rar">Tài liệu hướng dẫn sử dụng cho Cán bộ vai trò Chuyên viên</a></li>
                <li><a href="http://vfa.gov.vn/HDSD_QT01_13004_HQMC_ATTP_Doanhnghiep_v1.2.rar">Tài liệu hướng dẫn sử dụng cho Cán bộ vai trò Doanh nghiệp</a></li>
                <li><a href="http://vfa.gov.vn/HDSD_QT01_13004_HQMC_ATTP_Kytudacbiet_v1.2.rar">Tài liệu hướng dẫn sử dụng ký tự đặc biệt</a></li>
        </td>
    </tr>
</table>
</td>
</tr>
</table>