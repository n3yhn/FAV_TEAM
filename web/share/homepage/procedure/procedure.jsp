<%-- 
    Document   : procedure
    Created on : Aug 22, 2014, 4:04:19 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>



        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="shortcut icon" href="share/homepage/images/logotitle.png" >
            <title>Thủ tục hành chính</title>     
            <link href="share/homepage/css/style_comon.css" rel="stylesheet" type="text/css" />
            <link class="lfr-css-file" href="share/homepage/css/main_002.css" rel="stylesheet" type="text/css"> 
                <link charset="utf-8" href="share/homepage/css/a_002.css" rel="stylesheet" type="text/css" id="aui_3_4_0_1_57">
                    <link charset="utf-8" href="share/homepage/css/a.css" rel="stylesheet" type="text/css" id="aui_3_4_0_1_73">

                        <script type="text/javascript">
                            function changeTTHC(index)
                            {
                                for (var i = 0; i < 8; i++)
                                {
                                    if (i == index)
                                    {
                                        document.getElementById("tthc0" + index).style.display = '';
                                    }
                                    else {
                                        document.getElementById("tthc0" + i).style.display = 'none';
                                    }
                                }
                                return;
                            }



                        </script>

                        </head> 
                        <body >
                            <div class="wrapper_reg" style="background-color: white;">
                                <div class="header_hp" style="background-color: white;">
                                    <div class="logo1"><img hspace="5" src="share/homepage/images/logo.png" width="269" height="68" /></div>
                                    <div class="hmenu">
                                        <ul class="ul_nav">
                                            <li class="li_nav iconContact" style="float:right"><a href="/ContactPage.do">Liên hệ</a></li>
                                            <li class="li_nav iconSearch" style="float:right"><a href="/filesAction!toLookUpHomePage.do">Tra cứu</a></li>
                                            <li class="li_nav icontt" style="float:right"><a href="/ProcedurePage.do">Thủ tục hành chính</a></li>
                                            <li class="li_nav iconhome" style="float:right"><a href="/HomePage.do">Trang chủ</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="hr_name"><a>HỆ THỐNG CẤP GIẤY TIẾP NHẬN BẢN CÔNG BỐ HỢP QUY VÀ GIẤY XÁC NHẬN CÔNG BỐ PHÙ HỢP QUY ĐỊNH ATTP</a></div>
                                <!--End #header--> 
                                <div id="main" style="height: auto; min-height: 415px;"> 
                                    <div id="p_p_id_103_" class="portlet-boundary portlet-boundary_103_ portlet-static portlet-static-end portlet-borderless " style=""> 
                                        <span id="p_103"></span> 
                                        <div class="portlet-body"> </div> 
                                    </div> 
                                    <div class="columns-1" id="main-content" role="main"> 
                                        <div class="portlet-layout"> 
                                            <div class="portlet-column portlet-column-only" id="column-1"> 
                                                <div class="portlet-dropzone portlet-column-content portlet-column-content-only" id="layout-column_column-1"> 
                                                    <div id="p_p_id_thutuchanhchinhcucattpaction_WAR_moh_tthc_2014portlet_" class="portlet-boundary portlet-boundary_thutuchanhchinhcucattpaction_WAR_moh_tthc_2014portlet_ portlet-static portlet-static-end portlet-borderless thutuchanhchinhcucattpaction-portlet " style=""> 
                                                        <span id="p_thutuchanhchinhcucattpaction_WAR_moh_tthc_2014portlet"></span> 
                                                        <div class="portlet-body"> 
                                                            <div class="portlet-borderless-container" style=""> 
                                                                <div class="portlet-body"> <div id="main"> <div class="dgdan"> <p class="iconthutuc"> <a class="dd" onclick="return changeTTHC('0');" style="cursor: pointer">Thủ tục hành chính</a> </p> </div> 
                                                                        <div class="box_nd" id="tthc00"> 
                                                                            <%--<div class="truong"> <table class="table1"> <tbody>
                                                                            <tr> 
                                                                                <td><h3>Cấp giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm; Cấp lại Giấy Tiếp nhận bản công bố hợp quy và Giấy Xác nhận công bố phù hợp quy định an toàn thực phẩm thuộc thẩm quyền của Bộ Y tế</h3></td> 
                                                                                <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                            </tr> </tbody></table> </div> 
                                                                            --%>
                                                                            <div> 
                                                                                <table class="table2"> 
                                                                                    <tbody>
                                                                                        <tr class="le"> 
                                                                                            <td width="50" style="text-align: center"><strong>STT</strong></td>
                                                                                            <td width="700" style="text-align: center"><strong>Tên thủ tục</strong></td>
                                                                                            <td width="100" style="text-align: center"><strong>Lĩnh vực</strong></td>
                                                                                            <td width="200" style="text-align: center"><strong>Cơ quan có thẩm quyền quyết định</strong></td>
                                                                                        </tr>
                                                                                        <%-- TTHC 1 --%>
                                                                                        <tr class="le"> 
                                                                                            <td style="text-align: center">1</td>
                                                                                            <td >
                                                                                                <a onclick="return changeTTHC('1');" style="cursor: pointer">
                                                                                                    Cấp giấy tiếp nhân bản công bố hợp quy đối với sản phẩm chưa có qui chuẩn kỹ thuật dựa trên kết quả tự đánh giá của tổ chức, cá nhân sản xuất, kinh doanh thực phẩm (bên thứ nhất) thuộc thẩm quyền của Bộ Y tế (Qui định tại khoản 1, điều 7 Thông tư số 19/20
                                                                                                </a>
                                                                                            </td>
                                                                                            <td >
                                                                                                An toàn thực phẩm
                                                                                            </td>
                                                                                            <td >
                                                                                                Cục An toàn thực phẩm
                                                                                            </td>
                                                                                        </tr>
                                                                                        <%-- TTHC 2 --%>
                                                                                        <tr class="le"> 
                                                                                            <td style="text-align: center">2</td>
                                                                                            <td >
                                                                                                <a onclick="return changeTTHC('2');" style="cursor: pointer">
                                                                                                    Cấp giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm đối với thực phẩm chức năng và thực phẩm tăng cường vi chất dinh dưỡng sản xuất trong nước
                                                                                                </a>
                                                                                            </td>
                                                                                            <td >
                                                                                                An toàn thực phẩm
                                                                                            </td>
                                                                                            <td >
                                                                                                Cục An toàn thực phẩm
                                                                                            </td>
                                                                                        </tr>
                                                                                        <%-- TTHC 3 --%>
                                                                                        <tr class="le"> 
                                                                                            <td style="text-align: center">3</td>
                                                                                            <td >
                                                                                                <a onclick="return changeTTHC('3');" style="cursor: pointer">
                                                                                                    Cấp giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm đối với thực phẩm chức năng và thực phẩm tăng cường vi chất dinh dưỡng nhập khẩu
                                                                                                </a>
                                                                                            </td>
                                                                                            <td >
                                                                                                An toàn thực phẩm
                                                                                            </td>
                                                                                            <td >
                                                                                                Cục An toàn thực phẩm
                                                                                            </td>
                                                                                        </tr>
                                                                                        <%-- TTHC 4 --%>
                                                                                        <tr class="le"> 
                                                                                            <td style="text-align: center">4</td>
                                                                                            <td >
                                                                                                <a onclick="return changeTTHC('4');" style="cursor: pointer">
                                                                                                    Cấp giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm đối với sản phẩm chưa có qui chuẩn kỹ thuật sản xuất trong nước (trừ thực phẩm chức năng và thực phẩm tăng cường vi chất dinh dưỡng) thuộc thẩm quyền của Bộ Y tế (Qui định tại khoản 1, điều 7 Thông tư số 19/2012/TT-BYT)
                                                                                                </a>
                                                                                            </td>
                                                                                            <td >
                                                                                                An toàn thực phẩm
                                                                                            </td>
                                                                                            <td >
                                                                                                Cục An toàn thực phẩm
                                                                                            </td>
                                                                                        </tr>
                                                                                        <%-- TTHC 5 --%>
                                                                                        <tr class="le"> 
                                                                                            <td style="text-align: center">5</td>
                                                                                            <td >
                                                                                                <a onclick="return changeTTHC('5');" style="cursor: pointer">
                                                                                                    Cấp giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm đối với sản phẩm chưa có qui chuẩn kỹ thuật nhập khẩu (trừ thực phẩm chức năng và thực phẩm tăng cường vi chất dinh dưỡng)
                                                                                                </a>
                                                                                            </td>
                                                                                            <td >
                                                                                                An toàn thực phẩm
                                                                                            </td>
                                                                                            <td >
                                                                                                Cục An toàn thực phẩm
                                                                                            </td>
                                                                                        </tr>
                                                                                        <%-- TTHC 6 --%>
                                                                                        <tr class="le"> 
                                                                                            <td style="text-align: center">6</td>
                                                                                            <td >
                                                                                                <a onclick="return changeTTHC('6');" style="cursor: pointer">
                                                                                                    Cấp giấy tiếp nhân bản công bố hợp quy đối với sản phẩm chưa có qui chuẩn kỹ thuật dựa trên kết quả tự đánh giá của tổ chức, cá nhân sản xuất, kinh doanh thực phẩm (bên thứ nhất) thuộc thẩm quyền của Bộ Y tế (Qui định tại khoản 1, điều 7 Thông tư số 19/2012/TT-BYT)
                                                                                                </a>
                                                                                            </td>
                                                                                            <td >
                                                                                                An toàn thực phẩm
                                                                                            </td>
                                                                                            <td >
                                                                                                Cục An toàn thực phẩm
                                                                                            </td>
                                                                                        </tr>
                                                                                        <%-- TTHC 7 --%>
                                                                                        <tr class="le"> 
                                                                                            <td style="text-align: center">7</td>
                                                                                            <td >
                                                                                                <a onclick="return changeTTHC('7');" style="cursor: pointer">
                                                                                                    Cấp giấy tiếp nhận bản công bố hợp quy đối với sản phẩm đã có qui chuẩn kỹ thuật dựa trên kết quả chứng nhận hợp quy của tổ chức chứng nhận hợp quy được chỉ định (bên thứ ba) thuộc thẩm quyền của Bộ Y tế (Qui định tại khoản 1, điều 7 Thông tư số 19/2012/TT-BYT)
                                                                                                </a>
                                                                                            </td>
                                                                                            <td >
                                                                                                An toàn thực phẩm
                                                                                            </td>
                                                                                            <td >
                                                                                                Cục An toàn thực phẩm
                                                                                            </td>
                                                                                        </tr>
                                                                                    </tbody>
                                                                                </table> 
                                                                            </div> 
                                                                        </div> 


                                                                        <div class="box_nd" id="tthc01"> 
                                                                            <div class="truong"> 
                                                                                <table class="table1"> <tbody>
                                                                                        <tr> 
                                                                                            <td><h3>Cấp lại Giấy Tiếp nhận bản công bố hợp quy và Giấy Xác nhận công bố phù hợp quy định an toàn thực phẩm thuộc thẩm quyền của Bộ Y tế</h3></td> 
                                                                                            <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                                        </tr> </tbody></table> </div> 
                                                                            <div> 
                                                                                <table class="table2"> 
                                                                                    <tbody><tr class="le"> 
                                                                                            <td width="200"><strong>Lĩnh vực</strong></td> 
                                                                                            <td width="850">An toàn thực phẩm</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cơ quan có thẩm quyền quyết định</strong></td> 
                                                                                            <td>Cục An toàn thực phẩm</td> 
                                                                                        </tr> 

                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Trình tự thực hiện</strong></td> 
                                                                                            <td> 
                                                                                                <p class="texttble"><strong>Bước 1:</strong> Tổ chức, cá nhân gửi hồ sơ về Cục An toàn thực phẩm.</p>
                                                                                                <p class="texttble"><strong>Bước 2:</strong> Cục An toàn thực phẩm tiếp nhận hồ sơ và kiểm tra hồ sơ. Trong vòng 07 ngày làm việc kể từ khi nhận đủ hồ sơ hợp lệ phải cấp giấy tiếp nhận. Trường hợp không cáp Giấy tiếp nhận phải trả lời bằng văn bản nêu rõ lý do.</p>
                                                                                                <p class="texttble"><strong>Bước 3:</strong> Trả kết quả cho tổ chức, cá nhân công bố sản phẩm.</p>
                                                                                            </td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cách thực hiện</strong></td> 
                                                                                            <td>Qua đường bưu điện hoặc nộp trực tiếp tại Cục An toàn thực phẩm theo quy trình một cửa.</td> 
                                                                                        </tr> 

                                                                                        <tr class="le">     
                                                                                            <td><strong>Thành phần số lượng hồ sơ</strong></td> 
                                                                                            <td><p class="texttble">a)Thành phần hồ sơ bao gồm:
                                                                                                </p><p class="texttble">A) Hồ sơ Công bố hơp qui hoặc xác nhận phù hợp quy định an toàn thực phẩm
                                                                                                </p><p class="texttble">1- Đơn đề nghị cấp lại Giấy Tiếp nhận bản công bố hợp quy hoặc Giấy Xác nhận công bố phù hợp quy định an toàn thực phẩm theo mẫu được quy định tại Mẫu số 05 ban hành kèm theo Nghị định 38/2012/NĐ-CP (có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">2- Giấy Tiếp nhận bản công bố hợp quy hoặc Giấy Xác nhận công bố phù hợp quy định an toàn thực phẩm lần gần nhất (bản sao);
                                                                                                </p><p class="texttble">3- Kết quả kiểm nghiệm sản phẩm định kỳ do phòng kiểm nghiệm được công nhận do cơ quan nhà nước có thẩm quyền chỉ định (bản sao có công chứng hoặc bản sao có xuất trình bản chính để đối chiếu):
                                                                                                </p><p class="texttble">- 1 lần/năm đối với cơ sở có một trong các chứng chỉ về hệ thống quản lý chất lượng tiên tiến: GMP, HACCP, ISO 22000 và tương đương.
                                                                                                </p><p class="texttble">- 2 lần/năm đối với các cơ sở không có các chứng chỉ trên.
                                                                                                </p><p class="texttble">B) Hồ sơ pháp lý chung
                                                                                                </p><p class="texttble">4- Chứng chỉ phù hợp tiêu chuẩn HACCP hoặc ISO 22000 hoặc tương đương trong trường hợp tổ chức, cá nhân có hệ thống quản lý chất lượng được chứng nhận phù hợp tiêu chuẩn HACCP hoặc ISO 22000 hoặc tương đương (bản sao có công chứng hoặc bản sao có xuất trình bản chính để đối chiếu);
                                                                                                </p><p class="texttble">b) Số lượng hồ sơ: 02 bộ hồ sơ công bố hợp quy và  01 bộ hồ sơ pháp lý chung
                                                                                                </p>
                                                                                            </td>
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Thời hạn giải quyết</strong></td> 
                                                                                            <td>07 ngày làm việc</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Đối tượng thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Tổ chức, cá nhân</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cơ quan thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Cục An toàn thực phẩm - Bộ Y tế</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Kết quả thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Giấy Tiếp nhận bản công bố hợp quy hoặc Giấy xác nhân bản công bố phù hợp quy định an toàn thực phẩm</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>TTHC yêu cầu trả phí, lệ phí</strong></td> 
                                                                                            <td>150.000 đồng/ 1 sản phẩm đối với hồ sơ công bố phù hợp quy định an toàn thực phẩm.</td> 
                                                                                        </tr> 

                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Yêu cầu</strong></td> 
                                                                                            <td><p class="texttble">1- Luật An toàn thực phẩm số 55/2010/QH12 ngày 17/6/2010;
                                                                                                </p><p class="texttble">2- Nghị định số 38/2012/NĐ-CP ngày 25/4/2012 của Chính phủ quy định chi tiết thi hành một số điều của Luật An toàn thực phẩm
                                                                                                </p><p class="texttble">3- Thông tư số 19/2012/TT-BYT ngày 09/11/2012 của Bộ Y tế Hướng dẫn việc công bố hợp quy và công bố phù hợp quy định an toàn thực phẩm.</td> 
                                                                                            </p>
                                                                                        </tr>
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Tập tin đính kèm</strong></td> 
                                                                                            <td><a href="http://vfa.gov.vn/thu-tuc-hanh-chinh/detail/cap-lai-giay-tiep-nhan-ban-cong-bo-hop-quy-va-giay-xac-nhan-cong-bo-phu-hop-quy-dinh-an-toan-thuc-pham-thuoc-tham-quyen-cua-bo-y-te-15.vfa">QDCB 476-CUC ATTP.doc</a></td> 
                                                                                        </tr> 

                                                                                    </tbody></table> </div> 
                                                                        </div>

                                                                        <div class="box_nd" id="tthc02"> 
                                                                            <div class="truong"> <table class="table1"> <tbody>
                                                                                        <tr> 
                                                                                            <td><h3>Cấp giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm đối với thực phẩm chức năng và thực phẩm tăng cường vi chất dinh dưỡng sản xuất trong nước</h3></td> 
                                                                                            <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                                        </tr> </tbody></table> </div> 
                                                                            <div> 
                                                                                <table class="table2"> 
                                                                                    <tbody><tr class="le"> 
                                                                                            <td width="200"><strong>Lĩnh vực</strong></td> 
                                                                                            <td width="850">An toàn thực phẩm</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cơ quan có thẩm quyền quyết định</strong></td> 
                                                                                            <td>Cục An toàn thực phẩm</td> 
                                                                                        </tr> 

                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Trình tự thực hiện</strong></td> 
                                                                                            <td> 
                                                                                                <p class="texttble"><strong>Bước 1:</strong> Tổ chức, cá nhân gửi hồ sơ về Cục An toàn thực phẩm.</p>
                                                                                                <p class="texttble"><strong>Bước 2:</strong> Cục An toàn thực phẩm tiếp nhận hồ sơ và kiểm tra hồ sơ. Trong vòng 30 ngày làm việc kể từ khi nhận đủ hồ sơ hợp lệ phải cấp Giấy Tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm. Trường hợp không cấp Giấy Tiếp nhận phải trả lời bằng văn bản lý do không cấp.</p>
                                                                                                <p class="texttble"><strong>Bước 3:</strong> Trả Giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm cho tổ chức, cá nhân công bố sản phẩm.</p>
                                                                                            </td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cách thực hiện</strong></td> 
                                                                                            <td>Qua đường bưu điện hoặc nộp trực tiếp tại Cục An toàn thực phẩm theo quy trình một cửa.</td> 
                                                                                        </tr> 

                                                                                        <tr class="le">     
                                                                                            <td><strong>Thành phần số lượng hồ sơ</strong></td> 
                                                                                            <td><p class="texttble">a)Thành phần hồ sơ bao gồm:
                                                                                                </p><p class="texttble">A) Hồ sơ công bố phù hợp quy định an toàn thực phẩm
                                                                                                </p><p class="texttble">1-  Bản công bố phù hợp quy định an toàn thực phẩm, được quy định tại Mẫu số 02 ban hành kèm theo Nghị định 38/2012/NĐ-CP;
                                                                                                </p><p class="texttble">2-  Bản thông tin chi tiết về sản phẩm, được quy định tại Mẫu số 03a hoặc Mẫu số 03c ban hành kèm theo Nghị định 38/2012/NĐ-CP;
                                                                                                </p><p class="texttble">3- Kết quả kiểm nghiệm sản phẩm trong vòng 12 tháng (bản gốc hoặc bản sao công chứng có kèm bản gốc để đối chiếu hoặc được hợp pháp hóa lãnh sự), gồm các chỉ tiêu theo yêu cầu của quy chuẩn kỹ thuật tương ứng, của phòng kiểm nghiệm được chỉ định hoặc phòng kiểm nghiệm độc lập được công nhận hoặc phòng kiểm nghiệm được thừa nhận;
                                                                                                </p><p class="texttble">4- Mẫu nhãn sản phẩm (có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">5- Mẫu sản phẩm hoàn chỉnh để đối chiếu khi nộp hồ sơ;
                                                                                                </p><p class="texttble">6- Thông tin, tài liệu khoa học chứng minh về tác dụng của mỗi thành phần tạo nên chức năng đã công bố (bản sao có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">7- Kết quả thử nghiệm hiệu quả về công dụng của sản phẩm đối với thực phẩm chức năng có công dụng mới, được chế biến từ các chất mới hoặc theo công nghệ mới lần đầu tiên đưa ra lưu thông trên thị trường Việt Nam chưa được chứng minh là an toàn và hiệu quả;
                                                                                                </p><p class="texttble">8- Kế hoạch kiểm soát chất lượng được xây dựng và áp dụng theo mẫu được quy định tại Mẫu số 04 ban hành kèm theo Nghị định 38/2012/NĐ-CP (có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">9- Kế hoạch giám sát định kỳ (có xác nhận của tổ chức, cá nhân).
                                                                                                </p><p class="texttble">B) Hồ sơ pháp lý chung
                                                                                                </p><p class="texttble">10-  Giấy đăng ký kinh doanh có ngành nghề kinh doanh thực phẩm hoặc chứng nhận pháp nhân đối với tổ chức, cá nhân nhập khẩu thực phẩm (bản sao có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">11- Giấy chứng nhận cơ sở đủ điều kiện an toàn thực phẩm đối với cơ sở nhập khẩu thuộc đối tượng phải cấp giấy chứng nhận cơ sở đủ điều kiện an toàn thực phẩm theo quy định (bản sao có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">12- Chứng chỉ phù hợp tiêu chuẩn HACCP hoặc ISO 22000 hoặc tương đương trong trường hợp tổ chức, cá nhân sản xuất sản phẩm có hệ thống quản lý chất lượng được chứng nhận phù hợp tiêu chuẩn HACCP hoặc ISO 22000 hoặc tương đương (bản sao có công chứng hoặc bản sao có xuất trình bản chính để đối chiếu).
                                                                                                </p><p class="texttble">b) Số lượng hồ sơ: 02 bộ hồ sơ công bố hợp quy và  01 bộ hồ sơ pháp lý chung
                                                                                                </p>
                                                                                            </td>
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Thời hạn giải quyết</strong></td> 
                                                                                            <td>07 ngày làm việc</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Đối tượng thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Tổ chức, cá nhân</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cơ quan thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Cục An toàn thực phẩm - Bộ Y tế</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Kết quả thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Giấy Tiếp nhận bản công bố hợp quy hoặc Giấy xác nhân bản công bố phù hợp quy định an toàn thực phẩm</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>TTHC yêu cầu trả phí, lệ phí</strong></td> 
                                                                                            <td>150.000 đồng/ 1 sản phẩm đối với hồ sơ công bố phù hợp quy định an toàn thực phẩm.</td> 
                                                                                        </tr> 

                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Yêu cầu</strong></td> 
                                                                                            <td><p class="texttble">1- Luật An toàn thực phẩm số 55/2010/QH12 ngày 17/6/2010;
                                                                                                </p><p class="texttble">2- Nghị định số 38/2012/NĐ-CP ngày 25/4/2012 của Chính phủ quy định chi tiết thi hành một số điều của Luật An toàn thực phẩm
                                                                                                </p><p class="texttble">3- Thông tư số 19/2012/TT-BYT ngày 09/11/2012 của Bộ Y tế Hướng dẫn việc công bố hợp quy và công bố phù hợp quy định an toàn thực phẩm.</td> 
                                                                                            </p>
                                                                                        </tr>
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Tập tin đính kèm</strong></td> 
                                                                                            <td><a href="http://vfa.gov.vn/thu-tuc-hanh-chinh/detail/cap-giay-tiep-nhan-ban-cong-bo-phu-hop-quy-dinh-an-toan-thuc-pham-doi-voi-thuc-pham-chuc-nang-va-thuc-pham-tang-cuong-vi-chat-dinh-duong-san-xuat-trong-nuoc-13.vfa">cap-giay-tiep-nhan-ban-cong-bo-phu-hop-quy-dinh-an-toan-thuc-pham-doi-voi-thuc-pham-chuc-nang-va-thuc-pham-tang-cuong-vi-chat-dinh-duong-san-xuat-trong-nuoc.docx</a></td> 
                                                                                        </tr> 

                                                                                    </tbody></table> </div> </div>

                                                                        <div class="box_nd" id="tthc03"> 
                                                                            <div class="truong"> <table class="table1"> <tbody>
                                                                                        <tr> 
                                                                                            <td><h3>Cấp giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm đối với thực phẩm chức năng và thực phẩm tăng cường vi chất dinh dưỡng nhập khẩu.</h3></td> 
                                                                                            <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                                        </tr> </tbody></table> </div> 
                                                                            <div> 
                                                                                <table class="table2"> 
                                                                                    <tbody><tr class="le"> 
                                                                                            <td width="200"><strong>Lĩnh vực</strong></td> 
                                                                                            <td width="850">An toàn thực phẩm</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cơ quan có thẩm quyền quyết định</strong></td> 
                                                                                            <td>Cục An toàn thực phẩm</td> 
                                                                                        </tr> 

                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Trình tự thực hiện</strong></td> 
                                                                                            <td> 
                                                                                                <p class="texttble"><strong>Bước 1:</strong> Tổ chức, cá nhân gửi hồ sơ về Cục An toàn thực phẩm.</p>
                                                                                                <p class="texttble"><strong>Bước 2:</strong> Cục An toàn thực phẩm tiếp nhận hồ sơ và kiểm tra hồ sơ. Trong vòng 30 ngày làm việc kể từ khi nhận đủ hồ sơ hợp lệ phải cấp Giấy Tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm. Trường hợp không cấp Giấy Tiếp nhận phải trả lời bằng văn bản lý do không cấp.</p>
                                                                                                <p class="texttble"><strong>Bước 3:</strong> Trả Giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm cho tổ chức, cá nhân công bố sản phẩm.</p>
                                                                                            </td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cách thực hiện</strong></td> 
                                                                                            <td>Qua đường bưu điện hoặc nộp trực tiếp tại Cục An toàn thực phẩm theo quy trình một cửa.</td> 
                                                                                        </tr> 

                                                                                        <tr class="le">     
                                                                                            <td><strong>Thành phần số lượng hồ sơ</strong></td> 
                                                                                            <td><p class="texttble">a)Thành phần hồ sơ bao gồm:
                                                                                                </p><p class="texttble">A) Hồ sơ công bố phù hợp quy định an toàn thực phẩm
                                                                                                </p><p class="texttble">1- Bản công bố phù hợp quy định an toàn thực phẩm, được quy định tại Mẫu số 02 ban hành kèm theo Nghị định 38/2012/NĐ-CP;
                                                                                                </p><p class="texttble">2- Bản thông tin chi tiết về sản phẩm, được quy định tại Mẫu số 03b ban hành kèm theo Nghị định 38/2012/NĐ-CP (có đóng dấu giáp lai của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">3- Giấy chứng nhận lưu hành tự do hoặc chứng nhận y tế hoặc giấy chứng nhận tương đương do cơ quan nhà nước có thẩm quyền của nước xuất xứ cấp trong đó có nội dung thể hiện sản phẩm an toàn với sức khỏe người tiêu dùng và phù hợp với pháp luật về thực phẩm (bản gốc hoặc bản sao công chứng hoặc hợp pháp hóa lãnh sự);
                                                                                                </p><p class="texttble">4- Kết quả kiểm nghiệm sản phẩm trong vòng 12 tháng (bản gốc hoặc bản sao công chứng có kèm bản gốc để đối chiếu hoặc được hợp pháp hóa lãnh sự), gồm các chỉ tiêu theo yêu cầu của quy chuẩn kỹ thuật tương ứng, của phòng kiểm nghiệm được chỉ định hoặc phòng kiểm nghiệm độc lập được công nhận hoặc phòng kiểm nghiệm được thừa nhận;
                                                                                                </p><p class="texttble">5- Kế hoạch giám sát định kỳ (có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">6- Nhãn sản phẩm lưu hành tại nước xuất xứ và nhãn phụ bằng tiếng Việt (có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">7- Mẫu sản phẩm hoàn chỉnh để đối chiếu khi nộp hồ sơ;
                                                                                                </p><p class="texttble">8- Thông tin, tài liệu khoa học chứng minh về tác dụng của mỗi thành phần tạo nên chức năng đã công bố (bản sao có xác nhận của tổ chức, cá nhân).
                                                                                                </p><p class="texttble">B) Hồ sơ pháp lý chung
                                                                                                </p><p class="texttble">9-  Giấy đăng ký kinh doanh có ngành nghề kinh doanh thực phẩm hoặc chứng nhận pháp nhân đối với tổ chức, cá nhân nhập khẩu thực phẩm (bản sao có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">10- Giấy chứng nhận cơ sở đủ điều kiện an toàn thực phẩm đối với cơ sở nhập khẩu thuộc đối tượng phải cấp giấy chứng nhận cơ sở đủ điều kiện an toàn thực phẩm theo quy định (bản sao có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">11- Chứng chỉ phù hợp tiêu chuẩn HACCP hoặc ISO 22000 hoặc tương đương trong trường hợp tổ chức, cá nhân sản xuất sản phẩm có hệ thống quản lý chất lượng được chứng nhận phù hợp tiêu chuẩn HACCP hoặc ISO 22000 hoặc tương đương (bản sao có công chứng hoặc bản sao có xuất trình bản chính để đối chiếu).
                                                                                                </p><p class="texttble">b) Số lượng hồ sơ: 02 bộ hồ sơ công bố hợp quy và  01 bộ hồ sơ pháp lý chung
                                                                                                </p>
                                                                                            </td>
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Thời hạn giải quyết</strong></td> 
                                                                                            <td>07 ngày làm việc</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Đối tượng thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Tổ chức, cá nhân</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cơ quan thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Cục An toàn thực phẩm - Bộ Y tế</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Kết quả thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Giấy Tiếp nhận bản công bố hợp quy hoặc Giấy xác nhân bản công bố phù hợp quy định an toàn thực phẩm</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>TTHC yêu cầu trả phí, lệ phí</strong></td> 
                                                                                            <td>150.000 đồng/ 1 sản phẩm đối với hồ sơ công bố phù hợp quy định an toàn thực phẩm.</td> 
                                                                                        </tr> 

                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Yêu cầu</strong></td> 
                                                                                            <td><p class="texttble">1- Luật An toàn thực phẩm số 55/2010/QH12 ngày 17/6/2010;
                                                                                                </p><p class="texttble">2- Nghị định số 38/2012/NĐ-CP ngày 25/4/2012 của Chính phủ quy định chi tiết thi hành một số điều của Luật An toàn thực phẩm
                                                                                                </p><p class="texttble">3- Thông tư số 19/2012/TT-BYT ngày 09/11/2012 của Bộ Y tế Hướng dẫn việc công bố hợp quy và công bố phù hợp quy định an toàn thực phẩm.</td> 
                                                                                            </p>
                                                                                        </tr>
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Tập tin đính kèm</strong></td> 
                                                                                            <td><a href="http://vfa.gov.vn/thutuc/download/cap-giay-tiep-nhan-ban-cong-bo-phu-hop-quy-dinh-an-toan-thuc-pham-doi-voi-thuc-pham-chuc-nang-va-thuc-pham-tang-cuong-vi-chat-dinh-duong-nhap-khau-12.vfa">cap-giay-tiep-nhan-ban-cong-bo-phu-hop-quy-dinh-an-toan-thuc-pham-doi-voi-thuc-pham-chuc-nang-va-thuc-pham-tang-cuong-vi-chat-dinh-duong-nhap-khau.docx</a></td> 
                                                                                        </tr> 

                                                                                    </tbody></table> </div> </div>

                                                                        <div class="box_nd" id="tthc04"> 
                                                                            <div class="truong"> <table class="table1"> <tbody>
                                                                                        <tr> 
                                                                                            <td><h3>Cấp giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm đối với sản phẩm chưa có qui chuẩn kỹ thuật sản xuất trong nước (trừ thực phẩm chức năng và thực phẩm tăng cường vi chất dinh dưỡng) thuộc thẩm quyền của Bộ Y tế (Qui định tại khoản 1, điều 7 Thông tư số 19/2012/TT-BYT)</h3></td> 
                                                                                            <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                                        </tr> </tbody></table> </div> 
                                                                            <div> 
                                                                                <table class="table2"> 
                                                                                    <tbody><tr class="le"> 
                                                                                            <td width="200"><strong>Lĩnh vực</strong></td> 
                                                                                            <td width="850">An toàn thực phẩm</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cơ quan có thẩm quyền quyết định</strong></td> 
                                                                                            <td>Cục An toàn thực phẩm</td> 
                                                                                        </tr> 

                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Trình tự thực hiện</strong></td> 
                                                                                            <td> 
                                                                                                <p class="texttble"><strong>Bước 1:</strong> Tổ chức, cá nhân gửi hồ sơ về Cục An toàn thực phẩm.</p>
                                                                                                <p class="texttble"><strong>Bước 2:</strong> Cục An toàn thực phẩm tiếp nhận hồ sơ và kiểm tra hồ sơ. Trong vòng 15 ngày làm việc kể từ khi nhận đủ hồ sơ hợp lệ phải cấp Giấy Tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm. Trường hợp không cấp Giấy Tiếp nhận phải trả lời bằng văn bản lý do không cấp.</p>
                                                                                                <p class="texttble"><strong>Bước 3:</strong> Trả Giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm cho tổ chức, cá nhân công bố sản phẩm.</p>
                                                                                            </td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cách thực hiện</strong></td> 
                                                                                            <td>Qua đường bưu điện hoặc nộp trực tiếp tại Cục An toàn thực phẩm theo quy trình một cửa.</td> 
                                                                                        </tr> 

                                                                                        <tr class="le">     
                                                                                            <td><strong>Thành phần số lượng hồ sơ</strong></td> 
                                                                                            <td><p class="texttble">a)Thành phần hồ sơ bao gồm:
                                                                                                </p><p class="texttble">A) Hồ sơ công bố phù hợp quy định an toàn thực phẩm
                                                                                                </p><p class="texttble">1-  Bản công bố phù hợp quy định an toàn thực phẩm, được quy định tại Mẫu số 02 ban hành kèm theo Nghị định 38/2012/NĐ-CP;
                                                                                                </p><p class="texttble">2-  Bản thông tin chi tiết về sản phẩm, được quy định tại Mẫu số 03a hoặc Mẫu số 03c ban hành kèm theo Nghị định 38/2012/NĐ-CP;
                                                                                                </p><p class="texttble">3-  Kết quả kiểm nghiệm sản phẩm trong vòng 12 tháng (bản gốc hoặc bản sao công chứng có kèm bản gốc để đối chiếu hoặc được hợp pháp hóa lãnh sự), gồm các chỉ tiêu theo yêu cầu của quy chuẩn kỹ thuật tương ứng, của phòng kiểm nghiệm được chỉ định hoặc phòng kiểm nghiệm độc lập được công nhận hoặc phòng kiểm nghiệm được thừa nhận;
                                                                                                </p><p class="texttble">4- Kế hoạch kiểm soát chất lượng được xây dựng và áp dụng theo mẫu được quy định tại Mẫu số 04 ban hành kèm theo Nghị định 38/2012/NĐ-CP (có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">5- Kế hoạch giám sát định kỳ (có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">6- Mẫu nhãn sản phẩm (có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">B) Hồ sơ pháp lý chung
                                                                                                </p><p class="texttble">7-  Giấy đăng ký kinh doanh có ngành nghề kinh doanh thực phẩm hoặc chứng nhận pháp nhân đối với tổ chức, cá nhân nhập khẩu thực phẩm (bản sao có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">8- Giấy chứng nhận cơ sở đủ điều kiện an toàn thực phẩm đối với cơ sở nhập khẩu thuộc đối tượng phải cấp giấy chứng nhận cơ sở đủ điều kiện an toàn thực phẩm theo quy định (bản sao có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">9- Chứng chỉ phù hợp tiêu chuẩn HACCP hoặc ISO 22000 hoặc tương đương trong trường hợp tổ chức, cá nhân sản xuất sản phẩm có hệ thống quản lý chất lượng được chứng nhận phù hợp tiêu chuẩn HACCP hoặc ISO 22000 hoặc tương đương (bản sao có công chứng hoặc bản sao có xuất trình bản chính để đối chiếu).
                                                                                                </p><p class="texttble">b) Số lượng hồ sơ: 02 bộ hồ sơ công bố hợp quy và  01 bộ hồ sơ pháp lý chung
                                                                                                </p>
                                                                                            </td>
                                                                                        </tr>  
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Thời hạn giải quyết</strong></td> 
                                                                                            <td>07 ngày làm việc</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Đối tượng thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Tổ chức, cá nhân</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cơ quan thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Cục An toàn thực phẩm - Bộ Y tế</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Kết quả thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Giấy Tiếp nhận bản công bố hợp quy hoặc Giấy xác nhân bản công bố phù hợp quy định an toàn thực phẩm</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>TTHC yêu cầu trả phí, lệ phí</strong></td> 
                                                                                            <td>150.000 đồng/ 1 sản phẩm đối với hồ sơ công bố phù hợp quy định an toàn thực phẩm.</td> 
                                                                                        </tr> 

                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Yêu cầu</strong></td> 
                                                                                            <td></td>
                                                                                        </tr>
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Tập tin đính kèm</strong></td> 
                                                                                            <td><a href="http://vfa.gov.vn/thu-tuc-hanh-chinh/detail/cap-giay-tiep-nhan-ban-cong-bo-phu-hop-quy-dinh-an-toan-thuc-pham-doi-voi-san-pham-chua-co-qui-chuan-ky-thuat-san-xuat-trong-nuoc-tru-thuc-pham-chuc-nang-va-thuc-pham-tang-cuong-vi-chat-dinh-duong-thuoc-tham-quyen-cua-bo-y-te-qui-dinh-tai-khoan-1-dieu-7-thong-tu-so-192012tt-byt-11.vfa">cap-giay-tiep-nhan-ban-cong-bo-phu-hop-quy-dinh-an-toan-thuc-pham-doi-voi-san-pham-chua-co-qui-chuan-ky-thuat-san-xuat-trong-nuoc-tru-thuc-pham-chuc-nang-va-thuc-pham-tang-cuong-vi-chat-dinh-duong-thuoc-tham-quyen-cu.docx</a></td> 
                                                                                        </tr> 

                                                                                    </tbody></table> </div> </div>

                                                                        <div class="box_nd" id="tthc05"> 
                                                                            <div class="truong"> <table class="table1"> <tbody>
                                                                                        <tr> 
                                                                                            <td><h3>Cấp giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm đối với sản phẩm chưa có qui chuẩn kỹ thuật nhập khẩu (trừ thực phẩm chức năng và thực phẩm tăng cường vi chất dinh dưỡng)</h3></td> 
                                                                                            <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                                        </tr> </tbody></table> </div> 
                                                                            <div> 
                                                                                <table class="table2"> 
                                                                                    <tbody><tr class="le"> 
                                                                                            <td width="200"><strong>Lĩnh vực</strong></td> 
                                                                                            <td width="850">An toàn thực phẩm</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cơ quan có thẩm quyền quyết định</strong></td> 
                                                                                            <td>Cục An toàn thực phẩm</td> 
                                                                                        </tr> 

                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Trình tự thực hiện</strong></td> 
                                                                                            <td> 
                                                                                                <p class="texttble"><strong>Bước 1:</strong> Tổ chức, cá nhân gửi hồ sơ về Cục An toàn thực phẩm.</p>
                                                                                                <p class="texttble"><strong>Bước 2:</strong> Cục An toàn thực phẩm tiếp nhận hồ sơ và kiểm tra hồ sơ. Trong vòng 15 ngày làm việc kể từ khi nhận đủ hồ sơ hợp lệ phải cấp Giấy Tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm . Trường hợp không cấp Giấy Tiếp nhận phải trả lời bằng văn bản lý do không cấp
                                                                                                    <p class="texttble"><strong>Bước 3:</strong> Trả Giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm cho tổ chức, cá nhân công bố sản phẩm.</p>
                                                                                            </td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cách thực hiện</strong></td> 
                                                                                            <td>Qua đường bưu điện hoặc nộp trực tiếp tại Cục An toàn thực phẩm theo quy trình một cửa.</td> 
                                                                                        </tr> 

                                                                                        <tr class="le">     
                                                                                            <td><strong>Thành phần số lượng hồ sơ</strong></td> 
                                                                                            <td><p class="texttble">a)Thành phần hồ sơ bao gồm:
                                                                                                </p><p class="texttble">A) Hồ sơ công bố phù hợp quy định an toàn thực phẩm
                                                                                                </p><p class="texttble">1-  Bản công bố phù hợp quy định an toàn thực phẩm, được quy định tại Mẫu số 02 ban hành kèm theo Nghị định 38/2012/NĐ-CP;
                                                                                                </p><p class="texttble">2-  Bản thông tin chi tiết về sản phẩm, được quy định tại Mẫu số 03a hoặc Mẫu số 03c ban hành kèm theo Nghị định 38/2012/NĐ-CP;
                                                                                                </p><p class="texttble">3-  Kết quả kiểm nghiệm sản phẩm trong vòng 12 tháng (bản gốc hoặc bản sao công chứng có kèm bản gốc để đối chiếu hoặc được hợp pháp hóa lãnh sự), gồm các chỉ tiêu theo yêu cầu của quy chuẩn kỹ thuật tương ứng, của phòng kiểm nghiệm được chỉ định hoặc phòng kiểm nghiệm độc lập được công nhận hoặc phòng kiểm nghiệm được thừa nhận;
                                                                                                </p><p class="texttble">4- Kế hoạch giám sát định kỳ (có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">5-  Mẫu nhãn sản phẩm lưu hành tại nước xuất xứ và nhãn phụ bằng tiếng Việt (có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">6-  Mẫu sản phẩm hoàn chỉnh đối với sản phẩm lần đầu tiên nhập khẩu vào Việt Nam để đối chiếu khi nộp hồ sơ;
                                                                                                </p><p class="texttble">B) Hồ sơ pháp lý chung
                                                                                                </p><p class="texttble">7-  Giấy đăng ký kinh doanh có ngành nghề kinh doanh thực phẩm hoặc chứng nhận pháp nhân đối với tổ chức, cá nhân nhập khẩu thực phẩm (bản sao có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">8- Giấy chứng nhận cơ sở đủ điều kiện an toàn thực phẩm đối với cơ sở nhập khẩu thuộc đối tượng phải cấp giấy chứng nhận cơ sở đủ điều kiện an toàn thực phẩm theo quy định (bản sao có xác nhận của tổ chức, cá nhân);
                                                                                                </p><p class="texttble">9- Chứng chỉ phù hợp tiêu chuẩn HACCP hoặc ISO 22000 hoặc tương đương trong trường hợp tổ chức, cá nhân sản xuất sản phẩm có hệ thống quản lý chất lượng được chứng nhận phù hợp tiêu chuẩn HACCP hoặc ISO 22000 hoặc tương đương (bản sao có công chứng hoặc bản sao có xuất trình bản chính để đối chiếu).
                                                                                                </p><p class="texttble">b) Số lượng hồ sơ: 02 bộ hồ sơ công bố hợp quy và  01 bộ hồ sơ pháp lý chung
                                                                                                </p>
                                                                                            </td>
                                                                                        </tr>  
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Thời hạn giải quyết</strong></td> 
                                                                                            <td>07 ngày làm việc</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Đối tượng thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Tổ chức, cá nhân</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cơ quan thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Cục An toàn thực phẩm - Bộ Y tế</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Kết quả thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Giấy Tiếp nhận bản công bố hợp quy hoặc Giấy xác nhân bản công bố phù hợp quy định an toàn thực phẩm</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>TTHC yêu cầu trả phí, lệ phí</strong></td> 
                                                                                            <td>150.000 đồng/ 1 sản phẩm đối với hồ sơ công bố phù hợp quy định an toàn thực phẩm.</td> 
                                                                                        </tr> 

                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Yêu cầu</strong></td> 
                                                                                            <td><p class="texttble">1- Luật An toàn thực phẩm số 55/2010/QH12 ngày 17/6/2010;
                                                                                                </p><p class="texttble">2- Nghị định số 38/2012/NĐ-CP ngày 25/4/2012 của Chính phủ quy định chi tiết thi hành một số điều của Luật An toàn thực phẩm
                                                                                                </p><p class="texttble">3- Thông tư số 19/2012/TT-BYT ngày 09/11/2012 của Bộ Y tế Hướng dẫn việc công bố hợp quy và công bố phù hợp quy định an toàn thực phẩm.</td> 
                                                                                            </p>
                                                                                        </tr>
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Tập tin đính kèm</strong></td> 
                                                                                            <td><a href="http://vfa.gov.vn/thutuc/download/cap-giay-tiep-nhan-ban-cong-bo-phu-hop-quy-dinh-an-toan-thuc-pham-doi-voi-san-pham-chua-co-qui-chuan-ky-thuat-nhap-khau-tru-thuc-pham-chuc-nang-va-thuc-pham-tang-cuong-vi-chat-dinh-duong-10.vfa">cap-giay-tiep-nhan-ban-cong-bo-phu-hop-quy-dinh-an-toan-thuc-pham-doi-voi-san-pham-chua-co-qui-chuan-ky-thuat-nhap-khau-tru-thuc-pham-chuc-nang-va-thuc-pham-tang-cuong-vi-chat-dinh-duong.docx</a></td> 
                                                                                        </tr> 

                                                                                    </tbody></table> </div> </div>

                                                                        <div class="box_nd" id="tthc06"> 
                                                                            <div class="truong"> <table class="table1"> <tbody>
                                                                                        <tr> 
                                                                                            <td><h3>Cấp giấy tiếp nhân bản công bố hợp quy đối với sản phẩm chưa có qui chuẩn kỹ thuật dựa trên kết quả tự đánh giá của tổ chức, cá nhân sản xuất, kinh doanh thực phẩm (bên thứ nhất) thuộc thẩm quyền của Bộ Y tế (Qui định tại khoản 1, điều 7 Thông tư số 19/2012/TT-BYT)</h3></td> 
                                                                                            <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                                        </tr> </tbody></table> </div> 
                                                                            <div> 
                                                                                <table class="table2"> 
                                                                                    <tbody><tr class="le"> 
                                                                                            <td width="200"><strong>Lĩnh vực</strong></td> 
                                                                                            <td width="850">An toàn thực phẩm</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cơ quan có thẩm quyền quyết định</strong></td> 
                                                                                            <td>Cục An toàn thực phẩm</td> 
                                                                                        </tr> 

                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Trình tự thực hiện</strong></td> 
                                                                                            <td> 
                                                                                                <p class="texttble"><strong>Bước 1:</strong> Tổ chức, cá nhân gửi hồ sơ về Cục An toàn thực phẩm.</p>
                                                                                                <p class="texttble"><strong>Bước 2:</strong> Cục An toàn thực phẩm tiếp nhận hồ sơ và kiểm tra hồ sơ. Trong vòng 07 ngày làm việc kể từ khi nhận đủ hồ sơ hợp lệ phải cấp giấy tiếp nhận. Trường hợp không cáp Giấy tiếp nhận phải trả lời bằng văn bản nêu rõ lý do.</p>
                                                                                                <p class="texttble"><strong>Bước 3:</strong> Trả kết quả cho tổ chức, cá nhân công bố sản phẩm.</p>
                                                                                            </td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cách thực hiện</strong></td> 
                                                                                            <td>Qua đường bưu điện hoặc nộp trực tiếp tại Cục An toàn thực phẩm theo quy trình một cửa.</td> 
                                                                                        </tr> 

                                                                                        <tr class="le">     
                                                                                            <td><strong>Thành phần số lượng hồ sơ</strong></td> 
                                                                                            <td><p class="texttble">a)Thành phần hồ sơ bao gồm:
                                                                                                </p><p class="texttble">A) Hồ sơ công bố phù hợp quy định an toàn thực phẩm
                                                                                                </p><p class="texttble">1- Bản công bố hợp quy được quy định tại Mẫu số 02 ban hành kèm theo Nghị định 38/2012/NĐ-CP;
                                                                                                </p><p class="texttble">2- Bản thông tin chi tiết sản phẩm, được quy định tại Mẫu số 03a hoặc Mẫu số 03c ban hành kèm theo Nghị định 38/2012/NĐ-CP (có đóng dấu giáp lai của bên thứ ba);
                                                                                                </p><p class="texttble">3- Kết quả kiểm nghiệm sản phẩm trong vòng 12 tháng (bản gốc hoặc bản sao công chứng có kèm bản gốc để đối chiếu hoặc được hợp pháp hóa lãnh sự), gồm các chỉ tiêu theo yêu cầu của quy chuẩn kỹ thuật tương ứng, của phòng kiểm nghiệm được chỉ định hoặc phòng kiểm nghiệm độc lập được công nhận hoặc phòng kiểm nghiệm được thừa nhận;
                                                                                                </p><p class="texttble">4-  Kế hoạch kiểm soát chất lượng được xây dựng và áp dụng theo mẫu được quy định tại Mẫu số 04 ban hành kèm theo Nghị định 38/2012/NĐ-CP (bản xác nhận của bên thứ nhất);
                                                                                                </p><p class="texttble">5- Kế hoạch giám sát định kỳ (bản xác nhận của bên thứ nhất);
                                                                                                </p><p class="texttble">6- Báo cáo đánh giá hợp quy (bản xác nhận của bên thứ nhất);
                                                                                                </p><p class="texttble">B) Hồ sơ pháp lý chung
                                                                                                </p><p class="texttble">7- Chứng chỉ phù hợp tiêu chuẩn HACCP hoặc ISO 22000 hoặc tương đương trong trường hợp tổ chức, cá nhân sản xuất sản phẩm có hệ thống quản lý chất lượng được chứng nhận phù hợp tiêu chuẩn HACCP hoặc ISO 22000 hoặc tương đương (bản sao công chứng hoặc bản sao có xuất trình bản chính để đối chiếu).
                                                                                                </p><p class="texttble">b)Số lượng hồ sơ: 02 bộ hồ sơ công bố hợp quy và  01 bộ hồ sơ pháp lý chung
                                                                                                </p>
                                                                                            </td>
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Thời hạn giải quyết</strong></td> 
                                                                                            <td>07 ngày làm việc</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Đối tượng thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Tổ chức, cá nhân</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cơ quan thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Cục An toàn thực phẩm - Bộ Y tế</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Kết quả thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Giấy Tiếp nhận bản công bố hợp quy hoặc Giấy xác nhân bản công bố phù hợp quy định an toàn thực phẩm</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>TTHC yêu cầu trả phí, lệ phí</strong></td> 
                                                                                            <td>150.000 đồng/ 1 sản phẩm đối với hồ sơ công bố phù hợp quy định an toàn thực phẩm.</td> 
                                                                                        </tr> 

                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Yêu cầu</strong></td> 
                                                                                            <td></td>
                                                                                        </tr>
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Tập tin đính kèm</strong></td> 
                                                                                            <td><a href="http://vfa.gov.vn/thutuc/download/cap-giay-tiep-nhan-ban-cong-bo-hop-quy-doi-voi-san-pham-chua-co-qui-chuan-ky-thuat-dua-tren-ket-qua-tu-danh-gia-cua-to-chuc-ca-nhan-san-xuat-kinh-doanh-thuc-pham-ben-thu-nhat-thuoc-tham-quyen-cua-bo-y-te-qui-dinh-tai-khoan-1-dieu-7-thong-tu-so-192012tt-byt-9.vfa">cap-giay-tiep-nhan-ban-cong-bo-hop-quy-doi-voi-san-pham-chua-co-qui-chuan-ky-thuat-dua-tren-ket-qua-tu-danh-gia-cua-to-chuc-ca-nhan-san-xuat-kinh-doanh-thuc-pham-ben-thu-nhat-thuoc-tham-quyen-cua-bo-y-te-qui-dinh-tai.docx</a></td> 
                                                                                        </tr> 

                                                                                    </tbody></table> </div> </div>

                                                                        <div class="box_nd" id="tthc07"> 
                                                                            <div class="truong"> <table class="table1"> <tbody>
                                                                                        <tr> 
                                                                                            <td><h3>Cấp giấy tiếp nhận bản công bố hợp quy đối với sản phẩm đã có qui chuẩn kỹ thuật dựa trên kết quả chứng nhận hợp quy của tổ chức chứng nhận hợp quy được chỉ định (bên thứ ba) thuộc thẩm quyền của Bộ Y tế (Qui định tại khoản 1, điều 7 Thông tư số 19/2012/TT-BYT)</h3></td> 
                                                                                            <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                                        </tr> </tbody></table> </div> 
                                                                            <div> 
                                                                                <table class="table2"> 
                                                                                    <tbody><tr class="le"> 
                                                                                            <td width="200"><strong>Lĩnh vực</strong></td> 
                                                                                            <td width="850">An toàn thực phẩm</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cơ quan có thẩm quyền quyết định</strong></td> 
                                                                                            <td>Cục An toàn thực phẩm</td> 
                                                                                        </tr> 

                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Trình tự thực hiện</strong></td> 
                                                                                            <td> 
                                                                                                <p class="texttble"><strong>Bước 1:</strong> Tổ chức, cá nhân gửi hồ sơ về Cục An toàn thực phẩm.</p>
                                                                                                <p class="texttble"><strong>Bước 2:</strong> Cục An toàn thực phẩm tiếp nhận hồ sơ và kiểm tra hồ sơ. Trong vòng 7 ngày làm việc kể từ khi nhận đủ hồ sơ hợp lệ phải cấp Giấy Tiếp nhận bản công bố hợp quy. Trường hợp không cấp Giấy Tiếp nhận phải trả lời bằng văn bản lý do không cấp.</p>
                                                                                                <p class="texttble"><strong>Bước 3:</strong> Trả Giấy tiếp nhận bản công bố hợp quy cho tổ chức, cá nhân công bố sản phẩm.</p>
                                                                                            </td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cách thực hiện</strong></td> 
                                                                                            <td>Qua đường bưu điện hoặc nộp trực tiếp tại Cục An toàn thực phẩm theo quy trình một cửa.</td> 
                                                                                        </tr> 

                                                                                        <tr class="le">     
                                                                                            <td><strong>Thành phần số lượng hồ sơ</strong></td> 
                                                                                            <td><p class="texttble">a)Thành phần hồ sơ bao gồm:
                                                                                                </p><p class="texttble">A) Hồ sơ công bố phù hợp quy định an toàn thực phẩm
                                                                                                </p><p class="texttble">1- Bản công bố hợp quy được quy định tại Mẫu số 02 ban hành kèm theo Nghị định 38/2012/NĐ-CP;
                                                                                                </p><p class="texttble">2- Bản thông tin chi tiết sản phẩm, được quy định tại Mẫu số 03a hoặc Mẫu số 03c ban hành kèm theo Nghị định 38/2012/NĐ-CP (có đóng dấu giáp lai của bên thứ ba);
                                                                                                </p><p class="texttble">3- Chứng chỉ chứng nhận sự phù hợp của bên thứ ba (bản sao có công chứng hoặc bản sao có xuất trình bản chính để đối chiếu);
                                                                                                </p><p class="texttble">B) Hồ sơ pháp lý chung
                                                                                                </p><p class="texttble">4- Chứng chỉ phù hợp tiêu chuẩn HACCP hoặc ISO 22000 hoặc tương đương trong trường hợp tổ chức, cá nhân sản xuất sản phẩm có hệ thống quản lý chất lượng được chứng nhận phù hợp tiêu chuẩn HACCP hoặc ISO 22000 hoặc tương đương (bản sao công chứng hoặc bản sao có xuất trình bản chính để đối chiếu).
                                                                                                </p><p class="texttble">b)Số lượng hồ sơ: 02 bộ hồ sơ công bố hợp quy và  01 bộ hồ sơ pháp lý chung
                                                                                                </p>
                                                                                            </td>
                                                                                        </tr>  
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Thời hạn giải quyết</strong></td> 
                                                                                            <td>07 ngày làm việc</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Đối tượng thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Tổ chức, cá nhân</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Cơ quan thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Cục An toàn thực phẩm - Bộ Y tế</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Kết quả thực hiện thủ tục hành chính</strong></td> 
                                                                                            <td>Giấy Tiếp nhận bản công bố hợp quy hoặc Giấy xác nhân bản công bố phù hợp quy định an toàn thực phẩm</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>TTHC yêu cầu trả phí, lệ phí</strong></td> 
                                                                                            <td>150.000 đồng/ 1 sản phẩm đối với hồ sơ công bố phù hợp quy định an toàn thực phẩm.</td> 
                                                                                        </tr> 
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Yêu cầu</strong></td> 
                                                                                            <td><p class="texttble">1- Luật An toàn thực phẩm số 55/2010/QH12 ngày 17/6/2010;
                                                                                                </p><p class="texttble">2- Nghị định số 38/2012/NĐ-CP ngày 25/4/2012 của Chính phủ quy định chi tiết thi hành một số điều của Luật An toàn thực phẩm
                                                                                                </p><p class="texttble">3- Thông tư số 19/2012/TT-BYT ngày 09/11/2012 của Bộ Y tế Hướng dẫn việc công bố hợp quy và công bố phù hợp quy định an toàn thực phẩm.</td> 
                                                                                            </p>
                                                                                        </tr>
                                                                                        <tr class="chan"> 
                                                                                            <td><strong>Tập tin đính kèm</strong></td> 
                                                                                            <td><a href="http://vfa.gov.vn/thutuc/download/cap-giay-tiep-nhan-ban-cong-bo-hop-quy-doi-voi-san-pham-da-co-qui-chuan-ky-thuat-dua-tren-ket-qua-chung-nhan-hop-quy-cua-to-chuc-chung-nhan-hop-quy-duoc-chi-dinh-ben-thu-ba-thuoc-tham-quyen-cua-bo-y-te-qui-dinh-tai-khoan-1-dieu-7-thong-tu-so-192012tt-byt-8.vfa">cap-giay-tiep-nhan-ban-cong-bo-hop-quy-doi-voi-san-pham-da-co-qui-chuan-ky-thuat-dua-tren-ket-qua-chung-nhan-hop-quy-cua-to-chuc-chung-nhan-hop-quy-duoc-chi-dinh-ben-thu-ba-thuoc-tham-quyen-cua-bo-y-te-qui-dinh-tai-k.docx</a></td> 
                                                                                        </tr> 

                                                                                    </tbody></table> </div> </div>            

                                                                    </div> 
                                                                </div> 
                                                            </div> 
                                                        </div> 
                                                    </div> 
                                                </div>
                                            </div> 
                                        </div> 
                                    </div> 
                                    <form action="#" id="hrefFm" method="post" name="hrefFm"> <span></span> </form> 
                                </div><!--End #main--> 
                                <div id="footer"> <p align="right">Copyright © 2014 Cục An toàn thực phẩm - Bộ Y tế</p> </div><!--End #footer-->
                            </div>
                            <script type="text/javascript">
                                document.getElementById("tthc00").style.display = '';
                                document.getElementById("tthc01").style.display = 'none';
                                document.getElementById("tthc02").style.display = 'none';
                                document.getElementById("tthc03").style.display = 'none';
                                document.getElementById("tthc04").style.display = 'none';
                                document.getElementById("tthc05").style.display = 'none';
                                document.getElementById("tthc06").style.display = 'none';
                                document.getElementById("tthc07").style.display = 'none';
                            </script>
                        </body>

                        </html>
