<%-- 
    Document   : procedure
    Created on : Aug 22, 2014, 4:04:19 PM
    Author     : Administrator
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
                        <link href="share/homepage/VideoPlayer/video-js.css" rel="stylesheet">
                            <script src="share/homepage/VideoPlayer/video.js"></script>
                            <script>
                                videojs.options.flash.swf = "share/homepage/VideoPlayer/video-js.swf";
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
                                                                    <div class="portlet-body"> <div id="main"> <div class="dgdan"> <p class="iconthutuc"> <a class="dd" href="#">Hướng dẫn sử dụng</a> </p> </div> 

                                                                            <div class="box_nd" id="video1">
                                                                                <div class="truong"> <table class="table1"> <tbody>
                                                                                            <tr> 
                                                                                                <td><h3>Cài đặt môi trường</h3></td> 
                                                                                                <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                                            </tr> </tbody></table> </div>
                                                                                <div> 
                                                                                    <table class="table2"> 
                                                                                        <tbody>
                                                                                            <tr class="chan"> 
                                                                                                <td width="1050" style="text-align: center">
                                                                                                    <div style="width:638px;height:480px;margin-left: 300px">
                                                                                                        <iframe id="videoFrame1" width="600" height="480">
                                                                                                        </iframe> 
                                                                                                        <%
                                                                                                            HashMap<String, String> map = (HashMap) application.getAttribute("map");
                                                                                                            if (map == null) {
                                                                                                                map = new HashMap<String, String>();
                                                                                                                application.setAttribute("map", map);
                                                                                                            }
                                                                                                            String id = request.getParameter("vid");
                                                                                                            String title = request.getParameter("title");
                                                                                                            if (id != null && !"".equals(id.trim())
                                                                                                                    && title != null && !"".equals(title.trim())) {
                                                                                                                map.put(id.trim(), title.trim());
                                                                                                            }
                                                                                                            Iterator<String> iter = map.keySet().iterator();
                                                                                                            while (iter.hasNext()) {
                                                                                                                String i = iter.next();
                                                                                                                String t = map.get(i);
                                                                                                                out.print("<a href='?vid=" + i + "&title=" + t + "' >" + t + "</a> ");
                                                                                                            }
                                                                                                        %>
                                                                                                        <script>
                                                                                                            var url = "http://www.youtube.com/embed/rOEaB009W9Y";
                                                                                                            document.getElementById("videoFrame1").src = url;
                                                                                                        </script>
                                                                                                    </div>                                                                                                    
                                                                                                </td> 
                                                                                            </tr> 
                                                                                        </tbody>
                                                                                    </table> 
                                                                                </div> 
                                                                            </div> 

                                                                            <div class="box_nd" id="video2">
                                                                                <div class="truong"> <table class="table1"> <tbody>
                                                                                            <tr> 
                                                                                                <td><h3>Đăng ký CA tài khoản Doanh nghiệp</h3></td> 
                                                                                                <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                                            </tr> </tbody></table> </div>
                                                                                <div> 
                                                                                    <table class="table2"> 
                                                                                        <tbody>
                                                                                            <tr class="chan"> 
                                                                                                <td width="1050" style="text-align: center">
                                                                                                    <div style="width:638px;height:480px;margin-left: 300px">
                                                                                                        <iframe id="videoFrame2" width="600" height="480">
                                                                                                        </iframe> 
                                                                                                        <%
                                                                                                            map = (HashMap) application.getAttribute("map");
                                                                                                            if (map == null) {
                                                                                                                map = new HashMap<String, String>();
                                                                                                                application.setAttribute("map", map);
                                                                                                            }
                                                                                                            id = request.getParameter("vid");
                                                                                                            title = request.getParameter("title");
                                                                                                            if (id != null && !"".equals(id.trim())
                                                                                                                    && title != null && !"".equals(title.trim())) {
                                                                                                                map.put(id.trim(), title.trim());
                                                                                                            }
                                                                                                            iter = map.keySet().iterator();
                                                                                                            while (iter.hasNext()) {
                                                                                                                String i = iter.next();
                                                                                                                String t = map.get(i);
                                                                                                                out.print("<a href='?vid=" + i + "&title=" + t + "' >" + t + "</a> ");
                                                                                                            }
                                                                                                        %>
                                                                                                        <script>
                                                                                                            var url = "http://www.youtube.com/embed/C1De34CC--w";
                                                                                                            document.getElementById("videoFrame2").src = url;
                                                                                                        </script>
                                                                                                    </div>
                                                                                                </td> 
                                                                                            </tr> 
                                                                                        </tbody>
                                                                                    </table> 
                                                                                </div> 
                                                                            </div>

                                                                            <div class="box_nd" id="video3">
                                                                                <div class="truong"> <table class="table1"> <tbody>
                                                                                            <tr> 
                                                                                                <td><h3>Khai báo hồ sơ</h3></td> 
                                                                                                <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                                            </tr> </tbody></table> </div>
                                                                                <div> 
                                                                                    <table class="table2"> 
                                                                                        <tbody>
                                                                                            <tr class="chan"> 
                                                                                                <td width="1050" style="text-align: center">
                                                                                                    <div style="width:638px;height:480px;margin-left: 300px">
                                                                                                        <iframe id="videoFrame3" width="600" height="480">
                                                                                                        </iframe> 
                                                                                                        <%
                                                                                                            map = (HashMap) application.getAttribute("map");
                                                                                                            if (map == null) {
                                                                                                                map = new HashMap<String, String>();
                                                                                                                application.setAttribute("map", map);
                                                                                                            }
                                                                                                            id = request.getParameter("vid");
                                                                                                            title = request.getParameter("title");
                                                                                                            if (id != null && !"".equals(id.trim())
                                                                                                                    && title != null && !"".equals(title.trim())) {
                                                                                                                map.put(id.trim(), title.trim());
                                                                                                            }
                                                                                                            iter = map.keySet().iterator();
                                                                                                            while (iter.hasNext()) {
                                                                                                                String i = iter.next();
                                                                                                                String t = map.get(i);
                                                                                                                out.print("<a href='?vid=" + i + "&title=" + t + "' >" + t + "</a> ");
                                                                                                            }
                                                                                                        %>
                                                                                                        <script>
                                                                                                            var url = "http://www.youtube.com/embed/twj1FMDeWP8";
                                                                                                            document.getElementById("videoFrame3").src = url;
                                                                                                        </script>
                                                                                                    </div>
                                                                                                </td> 
                                                                                            </tr> 
                                                                                        </tbody>
                                                                                    </table> 
                                                                                </div> 
                                                                            </div>

                                                                            <div class="box_nd" id="video4">
                                                                                <div class="truong"> <table class="table1"> <tbody>
                                                                                            <tr> 
                                                                                                <td><h3>Ký CA trên Form</h3></td> 
                                                                                                <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                                            </tr> </tbody></table> </div>
                                                                                <div> 
                                                                                    <table class="table2"> 
                                                                                        <tbody>
                                                                                            <tr class="chan"> 
                                                                                                <td width="1050" style="text-align: center">
                                                                                                    <div style="width:638px;height:480px;margin-left: 300px">
                                                                                                        <iframe id="videoFrame4" width="600" height="480">
                                                                                                        </iframe> 
                                                                                                        <%
                                                                                                            map = (HashMap) application.getAttribute("map");
                                                                                                            if (map == null) {
                                                                                                                map = new HashMap<String, String>();
                                                                                                                application.setAttribute("map", map);
                                                                                                            }
                                                                                                            id = request.getParameter("vid");
                                                                                                            title = request.getParameter("title");
                                                                                                            if (id != null && !"".equals(id.trim())
                                                                                                                    && title != null && !"".equals(title.trim())) {
                                                                                                                map.put(id.trim(), title.trim());
                                                                                                            }
                                                                                                            iter = map.keySet().iterator();
                                                                                                            while (iter.hasNext()) {
                                                                                                                String i = iter.next();
                                                                                                                String t = map.get(i);
                                                                                                                out.print("<a href='?vid=" + i + "&title=" + t + "' >" + t + "</a> ");
                                                                                                            }
                                                                                                        %>
                                                                                                        <script>
                                                                                                            var url = "http://www.youtube.com/embed/AK3WqdkIEbw";
                                                                                                            document.getElementById("videoFrame4").src = url;
                                                                                                        </script>
                                                                                                    </div>
                                                                                                </td> 
                                                                                            </tr> 
                                                                                        </tbody>
                                                                                    </table> 
                                                                                </div> 
                                                                            </div>

                                                                            <div class="box_nd" id="video5">
                                                                                <div class="truong"> <table class="table1"> <tbody>
                                                                                            <tr> 
                                                                                                <td><h3>Ký File</h3></td> 
                                                                                                <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                                            </tr> </tbody></table> </div>
                                                                                <div> 
                                                                                    <table class="table2"> 
                                                                                        <tbody>
                                                                                            <tr class="chan"> 
                                                                                                <td width="1050" style="text-align: center">
                                                                                                    <div style="width:638px;height:480px;margin-left: 300px">
                                                                                                        <iframe id="videoFrame5" width="600" height="480">
                                                                                                        </iframe> 
                                                                                                        <%
                                                                                                            map = (HashMap) application.getAttribute("map");
                                                                                                            if (map == null) {
                                                                                                                map = new HashMap<String, String>();
                                                                                                                application.setAttribute("map", map);
                                                                                                            }
                                                                                                            id = request.getParameter("vid");
                                                                                                            title = request.getParameter("title");
                                                                                                            if (id != null && !"".equals(id.trim())
                                                                                                                    && title != null && !"".equals(title.trim())) {
                                                                                                                map.put(id.trim(), title.trim());
                                                                                                            }
                                                                                                            iter = map.keySet().iterator();
                                                                                                            while (iter.hasNext()) {
                                                                                                                String i = iter.next();
                                                                                                                String t = map.get(i);
                                                                                                                out.print("<a href='?vid=" + i + "&title=" + t + "' >" + t + "</a> ");
                                                                                                            }
                                                                                                        %>
                                                                                                        <script>
                                                                                                            var url = "http://www.youtube.com/embed/rVRhNChYgXc";
                                                                                                            document.getElementById("videoFrame5").src = url;
                                                                                                        </script>
                                                                                                    </div>
                                                                                                </td> 
                                                                                            </tr> 
                                                                                        </tbody>
                                                                                    </table> 
                                                                                </div> 
                                                                            </div>

                                                                            <div class="box_nd" id="video6">
                                                                                <div class="truong"> <table class="table1"> <tbody>
                                                                                            <tr> 
                                                                                                <td><h3>Thanh toán Online</h3></td> 
                                                                                                <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                                            </tr> </tbody></table> </div>
                                                                                <div> 
                                                                                    <table class="table2"> 
                                                                                        <tbody>
                                                                                            <tr class="chan"> 
                                                                                                <td width="1050" style="text-align: center">
                                                                                                    <div style="width:638px;height:480px;margin-left: 300px">
                                                                                                        <iframe id="videoFrame6" width="600" height="480">
                                                                                                        </iframe> 
                                                                                                        <%
                                                                                                            map = (HashMap) application.getAttribute("map");
                                                                                                            if (map == null) {
                                                                                                                map = new HashMap<String, String>();
                                                                                                                application.setAttribute("map", map);
                                                                                                            }
                                                                                                            id = request.getParameter("vid");
                                                                                                            title = request.getParameter("title");
                                                                                                            if (id != null && !"".equals(id.trim())
                                                                                                                    && title != null && !"".equals(title.trim())) {
                                                                                                                map.put(id.trim(), title.trim());
                                                                                                            }
                                                                                                            iter = map.keySet().iterator();
                                                                                                            while (iter.hasNext()) {
                                                                                                                String i = iter.next();
                                                                                                                String t = map.get(i);
                                                                                                                out.print("<a href='?vid=" + i + "&title=" + t + "' >" + t + "</a> ");
                                                                                                            }
                                                                                                        %>
                                                                                                        <script>
                                                                                                            var url = "http://www.youtube.com/embed/AktcLfx_Rfw";
                                                                                                            document.getElementById("videoFrame6").src = url;
                                                                                                        </script>
                                                                                                    </div>
                                                                                                </td> 
                                                                                            </tr> 
                                                                                        </tbody>
                                                                                    </table> 
                                                                                </div> 
                                                                            </div>

                                                                            <div class="box_nd" id="video7">
                                                                                <div class="truong"> <table class="table1"> <tbody>
                                                                                            <tr> 
                                                                                                <td><h3>Quản lý danh sách hồ sơ</h3></td> 
                                                                                                <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                                            </tr> </tbody></table> </div>
                                                                                <div> 
                                                                                    <table class="table2"> 
                                                                                        <tbody>
                                                                                            <tr class="chan"> 
                                                                                                <td width="1050" style="text-align: center">
                                                                                                    <div style="width:638px;height:480px;margin-left: 300px">
                                                                                                        <iframe id="videoFrame7" width="600" height="480">
                                                                                                        </iframe> 
                                                                                                        <%
                                                                                                            map = (HashMap) application.getAttribute("map");
                                                                                                            if (map == null) {
                                                                                                                map = new HashMap<String, String>();
                                                                                                                application.setAttribute("map", map);
                                                                                                            }
                                                                                                            id = request.getParameter("vid");
                                                                                                            title = request.getParameter("title");
                                                                                                            if (id != null && !"".equals(id.trim())
                                                                                                                    && title != null && !"".equals(title.trim())) {
                                                                                                                map.put(id.trim(), title.trim());
                                                                                                            }
                                                                                                            iter = map.keySet().iterator();
                                                                                                            while (iter.hasNext()) {
                                                                                                                String i = iter.next();
                                                                                                                String t = map.get(i);
                                                                                                                out.print("<a href='?vid=" + i + "&title=" + t + "' >" + t + "</a> ");
                                                                                                            }
                                                                                                        %>
                                                                                                        <script>
                                                                                                            var url = "http://www.youtube.com/embed/ufWoZcwtvvQ";
                                                                                                            document.getElementById("videoFrame7").src = url;
                                                                                                        </script>
                                                                                                    </div>
                                                                                                </td> 
                                                                                            </tr> 
                                                                                        </tbody>
                                                                                    </table> 
                                                                                </div> 
                                                                            </div>

                                                                            <div class="box_nd" id="video8">
                                                                                <div class="truong"> <table class="table1"> <tbody>
                                                                                            <tr> 
                                                                                                <td><h3>Sửa đổi bổ sung</h3></td> 
                                                                                                <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                                            </tr> </tbody></table> </div>
                                                                                <div> 
                                                                                    <table class="table2"> 
                                                                                        <tbody>
                                                                                            <tr class="chan"> 
                                                                                                <td width="1050" style="text-align: center">
                                                                                                    <div style="width:638px;height:480px;margin-left: 300px">
                                                                                                        <iframe id="videoFrame8" width="600" height="480">
                                                                                                        </iframe> 
                                                                                                        <%
                                                                                                            map = (HashMap) application.getAttribute("map");
                                                                                                            if (map == null) {
                                                                                                                map = new HashMap<String, String>();
                                                                                                                application.setAttribute("map", map);
                                                                                                            }
                                                                                                            id = request.getParameter("vid");
                                                                                                            title = request.getParameter("title");
                                                                                                            if (id != null && !"".equals(id.trim())
                                                                                                                    && title != null && !"".equals(title.trim())) {
                                                                                                                map.put(id.trim(), title.trim());
                                                                                                            }
                                                                                                            iter = map.keySet().iterator();
                                                                                                            while (iter.hasNext()) {
                                                                                                                String i = iter.next();
                                                                                                                String t = map.get(i);
                                                                                                                out.print("<a href='?vid=" + i + "&title=" + t + "' >" + t + "</a> ");
                                                                                                            }
                                                                                                        %>
                                                                                                        <script>
                                                                                                            var url = "http://www.youtube.com/embed/KsCWLStymLE";
                                                                                                            document.getElementById("videoFrame8").src = url;
                                                                                                        </script>
                                                                                                    </div>
                                                                                                </td> 
                                                                                            </tr> 
                                                                                        </tbody>
                                                                                    </table> 
                                                                                </div> 
                                                                            </div>

                                                                        </div> </div> </div> </div> </div> </div> </div> </div> </div> <form action="#" id="hrefFm" method="post" name="hrefFm"> <span></span> </form> </div><!--End #main--> <div id="footer"> <p align="right">Copyright © 2014 Cục An toàn thực phẩm - Bộ Y tế</p> </div><!--End #footer-->
                                </div>
                                <script type="text/javascript">
                                    document.getElementById("video1").style.display = 'none';
                                    document.getElementById("video2").style.display = 'none';
                                    document.getElementById("video3").style.display = 'none';
                                    document.getElementById("video4").style.display = 'none';
                                    document.getElementById("video5").style.display = 'none';
                                    document.getElementById("video6").style.display = 'none';
                                    document.getElementById("video7").style.display = 'none';
                                    document.getElementById("video8").style.display = 'none';
                                    document.getElementById("video" + ${fn:escapeXml(typeVideo)}).style.display = '';
                                </script>                                
                            </body>
                            </html>
