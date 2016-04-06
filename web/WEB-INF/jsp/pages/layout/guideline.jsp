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
<!--                <li><a href="${contextPath}/share/huongdan/Mau-cap-giay-chung-nhan-co-so-du-dieu-kien-an-toan-thuc-pham-doi-voi-cac-co-so-san-xuat-kinh-doanh-thuc-pham.docx">Cấp giấy chứng nhận cơ sở đủ điều kiện an toàn thực phẩm đối với các cơ sở sản xuất, kinh doanh thực phẩm thuộc thẩm quyền Bộ Y tế qui định tại khoản 1 Điều 4Thông tư số 26/2012/TT-BYT ngày 30/11/2012 của Bộ Y tế</a></li>
                <li><a href="${contextPath}/share/huongdan/Mau-cap-lai-giay-tiep-nhan-ban-cong-bo-hop-quy-va-giay-xac-nhan-cong-bo-phu-hop-quy-dinh-an-toan-thuc-pham.docx">Cấp lại Giấy Tiếp nhận bản công bố hợp quy và Giấy Xác nhận công bố phù hợp quy định an toàn thực phẩm thuộc thẩm quyền của Bộ Y tế</a></li>
                <li><a href="${contextPath}/share/huongdan/Mau-xac-nhan-san-pham-thuc-pham-nhap-khau-tru-thuc-pham-chuc-nang-chi-nham-muc-dich-su-dung-trong-noi-bo-co-so-san-xuat-sieu-thi-khach-san-bon-sao-tro-len.docx">Xác nhận bằng văn bản đối với sản phẩm thực phẩm nhập khẩu (trừ thực phẩm chức năng) chỉ nhằm mục đích sử dụng trong nội bộ cơ sở sản xuất, siêu thị, khách sạn bốn sao trở lên</a></li>
                <li><a href="${contextPath}/share/huongdan/Mau-cap-giay-tiep-nhan-ban-cong-bo-phu-hop-quy-dinh-an-toan-thuc-pham-doi-voi-thuc-pham-chuc-nang-san-xuat-trong-nuoc.docx">Cấp giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm đối với thực phẩm chức năng và thực phẩm tăng cường vi chất dinh dưỡng sản xuất trong nước</a></li>
                <li><a href="${contextPath}/share/huongdan/Mau-cap-giay-tiep-nhan-ban-cong-bo-phu-hop-quy-dinh-an-toan-thuc-pham-doi-voi-thuc-pham-chuc-nang-nhap-khau.docx">Cấp giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm đối với thực phẩm chức năng và thực phẩm tăng cường vi chất dinh dưỡng nhập khẩu.</a></li>
                <li><a href="${contextPath}/share/huongdan/Mau-cap-giay-tiep-nhan-ban-cong-bo-phu-hop-quy-dinh-an-toan-thuc-pham-doi-voi-san-pham-chua-co-qui-chuan-ky-thuat-san-xuat-trong-nuoc-tru-thuc-pham-chuc-nang.docx">Cấp giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm đối với sản phẩm chưa có qui chuẩn kỹ thuật sản xuất trong nước (trừ thực phẩm chức năng và thực phẩm tăng cường vi chất dinh dưỡng) thuộc thẩm quyền của Bộ Y tế (Qui định tại khoản 1, điều 7 Thông tư số 19/2012/TT-BYT)</a></li>
                <li><a href="${contextPath}/share/huongdan/Mau-cap-giay-tiep-nhan-ban-cong-bo-phu-hop-quy-dinh-an-toan-thuc-pham-doi-voi-san-pham-chua-co-qui-chuan-ky-thuat-nhap-khau-tru-thuc-pham-chuc-nang.docx">Cấp giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm đối với sản phẩm chưa có qui chuẩn kỹ thuật nhập khẩu (trừ thực phẩm chức năng và thực phẩm tăng cường vi chất dinh dưỡng)</a></li>
                <li><a href="${contextPath}/share/huongdan/Mau-cap-giay-tiep-nhan-ban-cong-bo-hop-quy-doi-voi-san-pham-chua-co-qui-chuan-ky-thuat-dua-tren-kinh-doanh-thuc-pham-ben-thu-nhat.docx">Cấp giấy tiếp nhân bản công bố hợp quy đối với sản phẩm chưa có qui chuẩn kỹ thuật dựa trên kết quả tự đánh giá của tổ chức, cá nhân sản xuất, kinh doanh thực phẩm (bên thứ nhất) thuộc thẩm quyền của Bộ Y tế (Qui định tại khoản 1, điều 7 Thông tư số 19/2012/TT-BYT)</a></li>
                <li><a href="${contextPath}/share/huongdan/Mau-cap-giay-tiep-nhan-ban-cong-bo-hop-quy-doi-voi-san-pham-da-co-qui-chuan-ky-thuat-dua-tren-ket-qua-chung-nhan-hop-quy-ben-thu-ba.docx">Cấp giấy tiếp nhận bản công bố hợp quy đối với sản phẩm đã có qui chuẩn kỹ thuật dựa trên kết quả chứng nhận hợp quy của tổ chức chứng nhận hợp quy được chỉ định (bên thứ ba) thuộc thẩm quyền của Bộ Y tế (Qui định tại khoản 1, điều 7 Thông tư số 19/2012/TT-BYT)</a></li>
                <li><a href="${contextPath}/share/huongdan/38ND_PL.doc">Tài liệu Nghị định 38</a></li>
                <li><a href="${contextPath}/share/huongdan/Kem_QD_23-2007-QD-BYT_Quy_che.doc">Quy chế QD_23-2007-QD-BYT</a></li>
                <li><a href="${contextPath}/share/huongdan/HDSD_Voffice_Vai tro Van Thu_v1.0.doc">Tài liệu hướng dẫn sử dụng cho Văn thư</a></li>
                <li><a href="${contextPath}/share/huongdan/HDSD_Voffice_Vai tro Chanh van phong_v1.0.doc">Tài liệu hướng dẫn sử dụng cho Chánh văn phòng</a></li>
                <li><a href="${contextPath}/share/huongdan/HDSD_Voffice_Vai tro Chuyen vien_v1.0.doc">Tài liệu hướng dẫn sử dụng cho Chuyên viên</a></li>
                <li><a href="${contextPath}/share/huongdan/HDSD_Voffice_Vai tro Lanh Dao_v1.0.doc">Tài liệu hướng dẫn sử dụng cho Lãnh đạo</a></li>
                <li><a href="${contextPath}/share/huongdan/HDSD_Voffice_Vai tro Can bo luu tru_v1.0.doc">Tài liệu hướng dẫn sử dụng cho Cán bộ lưu trữ</a></li>-->
                <li><a href="${contextPath}/share/huongdan/HDSD_QT01_13004_HQMC_ATTP_Vanthu_v1.1.docx">Tài liệu hướng dẫn sử dụng cho Cán bộ vai trò Văn thư</a></li>
                <li><a href="${contextPath}/share/huongdan/HDSD_QT01_13004_HQMC_ATTP_Lanhdaophong_v1.1.docx">Tài liệu hướng dẫn sử dụng cho Cán bộ vai trò Lãnh đạo phòng</a></li>
                <li><a href="${contextPath}/share/huongdan/HDSD_QT01_13004_HQMC_ATTP_LanhdaoCuc_ v1.1.docx">Tài liệu hướng dẫn sử dụng cho Cán bộ vai trò Lãnh đạo cục</a></li>
                <li><a href="${contextPath}/share/huongdan/HDSD_QT01_13004_HQMC_ATTP_KeToan_v1.0.docx">Tài liệu hướng dẫn sử dụng cho Cán bộ vai trò Kế toán</a></li>
                <li><a href="${contextPath}/share/huongdan/HDSD_QT01_13004_HQMC_ATTP_Chuyenvien_v1.1.docx">Tài liệu hướng dẫn sử dụng cho Cán bộ vai trò Chuyên viên</a></li>
                <li><a href="${contextPath}/share/huongdan/HDSD_QT01_13004_HQMC_ATTP_Doanhnghiep_v1.2.docx">Tài liệu hướng dẫn sử dụng cho Cán bộ vai trò Doanh nghiệp</a></li>
                <li><a href="${contextPath}/share/huongdan/HDSD_QT01_13004_HQMC_ATTP_Kytudacbiet_v1.2.docx">Tài liệu hướng dẫn sử dụng ký tự đặc biệt</a></li>
        </td>
    </tr>
</table>
</td>
</tr>
</table>
