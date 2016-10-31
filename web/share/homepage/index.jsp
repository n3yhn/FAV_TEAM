<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="shortcut icon" href="/share/homepage/images/logotitle.png" >
            <title>Hệ thống cấp giấy tiếp nhận bản công bố hợp quy và giấy xác nhận công bố phù hợp quy định ATTP</title>
            <link href="share/homepage/css/style_comon.css" rel="stylesheet" type="text/css" />

            <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
            <script type="text/javascript" src="http://download.skype.com/share/skypebuttons/js/skypeCheck.js"></script>

            <script type="text/javascript">
                        function isOnlineYahoo($yahoo)
                        {
                        $img = file_get_contents('http://opi.yahoo.com/online?u='.$yahoo.'&m=l&t=12&l=us');
                                return (strpos($img, 'NOT ONLINE') !== - 1);
                        }

                function isOnlineSkype($skype)
                {
                $url = 'http://mystatus.skype.com/balloon/'.$skype;
                        $size = getSizeFile($url);
                        return $size == 1808;
                }

                function getSizeFile($url) {
                if (substr($url, 0, 4) == 'http') {
                $x = array_change_key_case(get_headers($url, 1), CASE_LOWER);
                        if (strcasecmp($x[0], 'HTTP/1.1 200 OK') != 0) { $x = $x['content-length'][1]; }
                else { $x = $x['content-length']; }
                }
                else { $x = @filesize($url); }
                return $x;
                }
            </script>
    </head>

    <body>        
        <form style="font-family:'MYRIADPRO-COND'!important;
              font-size:20px !important;">
            <div class="wrapper">
                <div class="header_hp">
                    <div class="logo1"><img src="share/homepage/images/logo.png" width="269" height="68" /></div>
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
                <div class="big_banner">
                    <div class="statistic">
                        <div class="titTK"><a>Thống kê hồ sơ</a></div>
                        <div class="contentTK">
                            <ul>
                                <li><a href="#">Tổng số hồ sơ đang chờ xác nhận phí thẩm xét: <span style="color: #C4F078">${countSelectReceivePay}</span></a></li>
                                <li><a href="#">Tổng số hồ sơ chờ tiếp nhận: <span style="color: #C4F078">${countSelectNewHome}</span></a></li>
                                <li><a href="#">Tổng số hồ sơ đang chờ bồ sung: <span style="color: #C4F078">${countSelectEVALUATED_TO_ADD}</span></a></li>
                                <li><a href="#">Tổng số hồ sơ chờ tiếp nhận bổ sung: <span style="color: #C4F078">${countSelectNewSdbs}</span></a></li>
                                <li><a href="#">Tổng số hồ sơ đang xử lý: <span style="color: #C4F078">${countFileIsProcess}</span></a></li>
                                <li><a href="#">Tổng số hồ sơ chờ xác nhận lệ phí: <span style="color: #C4F078">${countSelectApprovePay}</span></a></li>
                                <li><a href="/filesAction!toLookUpHomePage.do">Tổng số hồ sơ đã được công bố: <span style="color: #C4F078">${countSelectGIVE_BACK}</span></a></li>
                                <%--
                                <li><a href="#">Tổng số hồ sơ chờ đối chiếu trả giấy công bố: <span style="color: #C4F078">${countSelectWaitGiveBack}</span></a></li>
                                <li><a href="#">Tổng số hồ sơ đã trả giấy công bố: <span style="color: #C4F078">${countSelectGiveBack}</span></a></li>
                                --%>
                            </ul>
                        </div>
                    </div>
                    <div class="RegForm">
                        <div class="titReg"><a>Đăng ký dịch vụ công y tế qua mạng</a></div>
                        <div class="titReg" style="color:rgb(80, 45, 92);">
                            <span>
                                CẤP GIẤY TIẾP NHẬN BẢN CÔNG BỐ HỢP QUY
                            </span>
                            <br/>
                            <span>
                                CẤP GIẤY XÁC NHẬN CÔNG BỐ PHÙ HỢP QUY ĐỊNH ATTP
                            </span>
                        </div>
                        <div class="btnBox" style="margin-top: 10px">
                            <div class="Green_btn"><a href="/Login.do">ĐĂNG NHẬP</a></div>
                            <div class="Green_btn">
                                <a href="/RegisterPage.do">ĐĂNG KÝ</a>                        
                            </div>
                        </div>
                        <div class="contentReg" style="margin-top: 30px">
                            <!--    Doanh nghiệp có thể nộp hồ sơ, thanh toán phí và lệ phí, theo dõi tiến trình<br /> 
                            xử lý và nhận kết quả xử lý hồ sơ trên mạng. -->
                            <div style="margin-left: -40px;margin-top: -10px">
                                <div style="height: 32px;background: url(share/homepage/images/support_3.png) left 5px no-repeat;">
                                    <p style="margin-left: 40px;padding-top: 10px;color: #013a57;font-weight: bolder;text-decoration: underline">HỖ TRỢ TRỰC TUYẾN</p></div>
                                <ul style="margin-top: 5px;margin-left: 40px;">
                                    <li style="color: #013a57">
                                        <div id='show_status_1'></div>
                                    </li>
                                    <li style="color: #013a57">
                                        <div id='show_status_2'></div>
                                    </li>
                                    <li style="color: #013a57">
                                        Yahoo: <label style="color:Blue">hotrodichvucong_attp</label> | <a href="ymsgr:sendIM?hotrodichvucong_attp"><img border=0 src="http://opi.yahoo.com/online?u=hotrodichvucong_attp&m=g&t=1" /> </a>
                                    </li>
                                    <li style="color: #013a57">
                                        Email: <label style="color:Blue"> hotrokhachhang@vfa.gov.vn</label>
                                    </li>
                                    <li style="color: #013a57;margin-top: 5px;font-size:15px;">
                                        <table style="width: 100%">                                           
                                            <tr>
                                                <td>
                                                    Hỗ trợ chung
                                                </td>
                                                <td>
                                                    <label style="color:Blue"> Mr. Giang: 0916011001 Mr Việt:0946665888 Mr. Toán: 0904028182 </label>;                                                </td>
                                            </tr>                                                                                       

                                            <tr>
                                                <td>
                                                    Hỗ trợ nộp phí
                                                </td>
                                                <td>
                                                    <label style="color:Blue">Miss. Phương / 043.7366.440-3020</label>; Email:phongtaichinhketoan@vfa.gov.vn;      Hỗ trợ thanh toán keypay:0466728822</label>; Email:info@keypay.gov.vn
                                                </td>
                                            </tr>
                                            <%--
                                            <tr>
                                                <td style="width: 25%">
                                                    Than phiền & Góp ý
                                                </td>
                                                <td style="width: 75%">
                                                    <label style="color:Blue">Lê Văn Toán (Mr) / 090.4028.182</label>
                                                </td>
                                            </tr>
                                            --%>
                                            <tr>
                                                <td style="width: 20%">
                                                    Góp ý
                                                </td>
                                                <td style="width: 80%">
                                                    <label style="color:Blue">PCT. Nguyễn Hùng Long (Mr) / 091.2250.527 / nguyenhunglong@vfa.gov.vn</label>
                                                </td>
                                            </tr>

                                        </table>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="step4">
                    <div class="textStep">CÁC BƯỚC THỰC HIỆN</div>
                    <div class="textStep" style="width: 90px ! important;"><img src="share/homepage/images/arrow.png" width="43" height="69" /></div>
                    <div class="ContentStep">
                        <ul>
                            <li>
                                <div class="boxImgIcon"><img src="share/homepage//images/icon-dk.png" width="42" height="51" /></div>
                                <!--                                <div><a href="share/homepage/Share/HDSD_ATTP_Dang_ky_khai_bao_truc_tuyen (Quy trinh 4 buoc)_v1.1.docx">1. Đăng ký tài khoản <br />Doanh nghiệp </a></div>-->
                                <div><a href="/GuideRegisterHtmlPage.do?type=1">1. Đăng ký tài khoản <br />Doanh nghiệp </a></div>
                            </li>
                            <li>
                                <div class="boxImgIcon"><img src="share/homepage//images/icon-dn.png" width="60" height="46" /></div>
                                <!--                                <div><a href="share/homepage/Share/HDSD_ATTP_Dang_ky_khai_bao_truc_tuyen (Quy trinh 4 buoc)_v1.1.docx">2.Đăng nhập và nộp <br />hồ sơ trực tuyến </a></div>-->
                                <div><a href="/GuideRegisterHtmlPage.do?type=2">2.Đăng nhập và nộp <br />hồ sơ trực tuyến </a></div>
                            </li>
                            <li>
                                <div class="boxImgIcon"><img src="share/homepage//images/icon-thanhtoan.png" width="56" height="47" /></div>
                                <!--                                <div><a href="share/homepage/Share/HDSD_ATTP_Dang_ky_khai_bao_truc_tuyen (Quy trinh 4 buoc)_v1.1.docx">3.Nộp phí thẩm định <br />trực tuyến </a></div>-->
                                <div><a href="/GuideRegisterHtmlPage.do?type=3">3.Nộp phí thẩm định <br />trực tuyến </a></div>
                            </li>
                            <li>
                                <div class="boxImgIcon"><img src="share/homepage//images/icon-nhankq.png" width="45" height="45" /></div>
                                <!--                                <div><a href="share/homepage/Share/HDSD_ATTP_Dang_ky_khai_bao_truc_tuyen (Quy trinh 4 buoc)_v1.1.docx">4. Nộp lệ phí cấp sổ và nhận kết quả xử lý hồ sơ trực tuyến </a></div>-->
                                <div><a href="/GuideRegisterHtmlPage.do?type=4">4. Nộp lệ phí cấp số và nhận kết quả xử lý hồ sơ trực tuyến </a></div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="footer">
                    <div class="boxInfoFoot" id="notices" style="width: 60% !important;color: #013a57 !important">
                        <!-- <div class="TitleFoot iconReport"><a>Thông báo</a></div>
                            <div style="padding: 8px;">
                                Cục ATTP tổ chức dùng thử phần mềm "Cấp giấy tiếp nhận bản công bố hợp quy và giấy xác nhận công bố phù hợp quy định ATTP"
                            </div>
                            <ul>
                                <li>Cấp giấy tiếp nhận bản công bố hợp quy</li>
                                <li>Cấp giấy xác nhận công bố phù hợp quy định ATTP</li>
                            </ul>-->
                    </div>
                    <!--
                    <div class="boxInfoFoot">
                        <div class="TitleFoot iconContact"><a>Hỗ trợ trực tuyến</a></div>
                        <div style="padding: 8px;">
                            <span style="font-family:'MyriadPro-BoldCond'">Vui lòng liên hệ:</span>
                        </div>
                        <ul>
                            <li>Skype: hotrokhachhang.attp</li>
                        </ul>
                    </div>
                    -->
                    <div class="boxInfoFoot" style="width: 30% !important;color: #013a57 !important">
                        <table style="width:100%">
                            <tr>
                                <td>
                                    <div class="TitleFoot iconReport"><a>Hướng dẫn sử dụng</a></div>
                                    <div style="padding: 8px;">
                                        <span style="font-family:'MyriadPro-BoldCond'">DÀNH CHO DOANH NGHIỆP</span>
                                    </div>
                                    <ul>
                                        <li><a href="GuideRegisterHtmlPage.do?type=1">Các bước thực hiện quy trình trên hệ thống</a></li>                                                
                                        <li><a href="/GuideLinePage.do">Tài liệu & Video hướng dẫn sử dụng</a></li>
                                        <li>Biểu mẫu Excel khai báo Hồ sơ dành cho Doanh nghiệp,  tải về: <a href="http://vfa.gov.vn/BM_KhaiBao_2007.rar">Excel 2007</a> / <a href="http://vfa.gov.vn/BM_KhaiBao_2010.rar">Excel 2010</a></li>
                                       <li>Chú ý bắt buộc sử dụng trình duyệt Firefox (Phiên bản 7.0 trở lên), tải về: <a href="http://vfa.gov.vn/Firefox Setup 32.0_En.rar">Tiếng Anh</a> / <a href="http://vfa.gov.vn/Firefox Setup 32.0_Vi.rar">Tiếng Việt</a></li>
                                        <!--<li>Tải Java hỗ trợ Doanh nghiệp ký số: <a href="http://vfa.gov.vn/jdk-7u71-windows-i586.rar">Tải về</a></li>-->
                                        <li>Plugin Firefox hỗ trợ chữ ký số: <a href="http://congbomypham.cqldvn.gov.vn/Share/huongdan/ViettelCASigner.rar">Tải về</a></li>
                                        <li>Tài liệu Hướng dẫn chữ kí số: <a href="http://congbosanpham.vfa.gov.vn/share/download/BYT_HDSD_Cai dat ky so_v1.0.rar">Tải về</a></li>
                                        
                                    </ul>
                                </td>
                            </tr>
                            <!--
<tr>
    <td>
        <br/>
    </td>
</tr>
<tr>
        <td>
                <div class="TitleFoot iconContact"><a>Liên hệ</a></div>
                <div style="padding: 8px;">
                        <span style="font-family:'MyriadPro-BoldCond'">CỤC AN TOÀN THỰC PHẨM - BỘ Y TẾ</span>
                </div>
                <ul>
                        <li>Địa chỉ: 138 A Giảng Võ - Ba Đình - Hà Nội</li>
                        <li>Email: hotrokhachhang@vfa.gov.vn</li>
                        <li>Điện thoại: 04.38464489/1010 - 04.38463702</li>
                        <li>ĐTDĐ: 0904028182 - Fax: 04.38463739</li>
                        <li>Chủ tài khoản: Cục An toàn thực phẩm - Số tài khoản: 1200208026339 - Chi nhánh: Sở Giao dịch Agribank - Địa chỉ: Số 2 Láng Hạ, Phường Thành Công, Quận Ba Đình, TP. Hà Nội</li>
                </ul>
        </td>
</tr>
                            -->
                            <!--
                            <tr>
                                <td>
                                    <br/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="TitleFoot iconReport"><a>Thống kê</a></div>
                                    <div style="padding: 8px;">
                                        <span style="font-family:'MyriadPro-BoldCond'">SỐ LƯỢNG TRUY CẬP VÀO HỆ THỐNG</span>
                                    </div>
                                    <div style="margin-top: 15px;margin-left: 20px">
                                        <!-- Histats.com  START (html only)
                                        <embed src="http://s10.histats.com/408.swf"  flashvars="jver=1&acsid=2807291&domi=4"  quality="high"  width="270" height="55" name="408.swf"  align="middle" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" wmode="transparent" />
                                        <img  src="http://sstatic1.histats.com/0.gif?2807291&101" alt="web counter" border="0">
                                    </div>
                                </td>
                            </tr>
                            --!>
                        </table>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
        </form>

        <script type="text/javascript">

            function loadNotice()
            {
            var contentNotice = "";
                    try
            {
            var getNotice = '${lstNotice}'.toString().split("<notice>");
                    contentNotice += "<div class=\"TitleFoot iconReport\"><a>Thông báo</a></div>";
                    contentNotice += "<div style=\"padding: 8px;\">";
                    if (getNotice != null && getNotice.length > 0)
            {
            contentNotice += getNotice[0];
            }
            contentNotice += "</div>";
                    contentNotice += "<ul>";
                    contentNotice += "<div style=\"overflow-y: auto\">";
                    if (getNotice != null && getNotice.length > 1)
            {
            for (var i = 1; i < getNotice.length; i++)
            {
            contentNotice += "<li>" + getNotice[i] + "</li>";
            }
            }
            }
            catch (err)
            {
            alert ("Có lỗi trong quá trình hiển thị thông báo: " + err.message);
            }

            document.getElementById("notices").innerHTML = contentNotice;
            }

            function getSkypeStatus(skypeId, iconType, skypeEvent, statusShowId, order){
            var image = new Image()
                    $(image).attr('src', 'http://mystatus.skype.com/' + iconType + '/' + skypeId);
                    var src = $(image).attr('src');
                    var html = "Skype" + order + ": " + "<label style=\"color:Blue\">" + skypeId + "</label>" + " | " + '<a href="skype:' + skypeId + '?' + skypeEvent + '" onclick="return skypeCheck();"><img src="' + src + '" border="0"></a>';
                    $('#' + statusShowId).append(html);
            }

            loadNotice();
                    getSkypeStatus('hotrokhachhang.attp', 'smallicon', 'chat', 'show_status_1', '1');
                    getSkypeStatus('toan182', 'smallicon', 'chat', 'show_status_2', '2');

        </script>
    </body>
</html>
