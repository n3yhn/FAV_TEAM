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
    <% if (isMSIE) {%>
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
    <% } else {%>
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
    <% }%>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <meta name="description" content="<s:property value="getText('projectName')"/>RDWF, RDWebFramework, RD Web FrameWork, R&D WebFrameWork, R&D Web Framework" />

            <title>Tra cứu</title>
            <tiles:insertTemplate template="/WEB-INF/jsp/pages/layout/include.jsp" />
            <link rel="shortcut icon" href="/share/homepage/images/logotitle.png" >
            <link href="share/homepage/css/style_comon.css" rel="stylesheet" type="text/css" />
            <link class="lfr-css-file" href="share/homepage/css/main_002.css" rel="stylesheet" type="text/css"> 
            <link charset="utf-8" href="share/homepage/css/a_002.css" rel="stylesheet" type="text/css" id="aui_3_4_0_1_57">
            <link charset="utf-8" href="share/homepage/css/a.css" rel="stylesheet" type="text/css" id="aui_3_4_0_1_73">
        </head>
        <script>page.getNo = function (index) {
                return dijit.byId("grid").currentRow + index + 1;
            };
            page.getRow = function (inRow) {
                return inRow;
            };
            page.getIndex = function (index) {
                return index + 1;
            };
            page.formatBusName = function (inData) {
                var row = inData - 1;
                var item = dijit.byId("grid").getItem(inData - 1);
                var url = "<div>";
                if (item != null) {
                    var businessName = item.businessName;
                    url = "<a style='cursor: pointer' onClick='page.searchByBusinessName(" + row + ")' >" + businessName + "</a>";
                }
                url += "</div>";
                return url;
            };
            page.fomatCode = function (inData) {
                var item = dijit.byId("grid").getItem(inData - 1);
                var url = "";
                if (item != null) {
                    var status = item.code;
                    if (status.toString() != "" && status.toString().trim() != "") {
                        switch (status) {
                            case "TPCN":
                                url = "Thực phẩm chức năng";
                                break;
                            case "DBT":
                                url = "Thực phẩm chức năng";
                                break;
                            default:
                                url = "Thực phẩm thường";
                                break;
                        }
                    } else {
                        url = "...";
                    }
                }
                return url;
            };
            page.formatAction = function (inData) {
                var item = dijit.byId("grid").getItem(inData - 1);
                var url = "";
                if (item != null) {
                    if (item.isDownload != null && item.isDownload == 1 && item.isExist != null && item.isExist.toString() != "")
                    {
                        url += "<div style='text-align:center;cursor:pointer;'>";
                        url += "<img src='share/images/document_open.png' width='17px' height='17px' title='Xuất giấy công bố' onClick='page.formatLinkDownloadPdf(" + item.fileId + ");' />";
                        url += "</div>";
                    }
                    return url;
                }
            };
            // enter key
            page.searchDefault = function (evt) {
                var dk = dojo.keys;
                switch (evt.keyCode) {
                    case dk.ENTER:
                        page.search();
                        break;
                }
            };
            dojo.connect(dojo.byId("searchForm"), "onkeypress", page.searchDefault);
        </script>
        <body class="${fn:escapeXml(JSLibTheme)}" <%--onkeydown="checkToRefresh(event);"--%> style="background-color: white !important; padding:2px" >
            <div id="vt-content-wrapper" class="no-space clear-both" style="align:right">
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
                        <div class="columns-1" id="main-content" role="main" style="min-height: 700px"> 
                            <div class="portlet-layout"> 
                                <div class="portlet-column portlet-column-only" id="column-1"> 
                                    <div class="portlet-dropzone portlet-column-content portlet-column-content-only" id="layout-column_column-1"> 
                                        <div id="p_p_id_thutuchanhchinhcucattpaction_WAR_moh_tthc_2014portlet_" class="portlet-boundary portlet-boundary_thutuchanhchinhcucattpaction_WAR_moh_tthc_2014portlet_ portlet-static portlet-static-end portlet-borderless thutuchanhchinhcucattpaction-portlet " style=""> 
                                            <span id="p_thutuchanhchinhcucattpaction_WAR_moh_tthc_2014portlet"></span> 
                                            <div class="portlet-body"> 
                                                <div class="portlet-borderless-container" style=""> 
                                                    <div class="portlet-body"> <div id="main"> <div class="dgdan"> <p class="iconthutuc"> <a class="dd" href="#">Tra cứu thông tin hồ sơ</a> </p> </div> <div class="box_nd"> 
                                                                <%--<div class="truong"> <table class="table1"> <tbody>
                                                                <tr> 
                                                                    <td><h3>Cấp giấy tiếp nhận bản công bố phù hợp quy định an toàn thực phẩm; Cấp lại Giấy Tiếp nhận bản công bố hợp quy và Giấy Xác nhận công bố phù hợp quy định an toàn thực phẩm thuộc thẩm quyền của Bộ Y tế</h3></td> 
                                                                    <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> <td>&nbsp;</td> 
                                                                </tr> </tbody></table> </div> 
                                                                --%>
                                                                <table style="width: 100%">
                                                                    <tr>
                                                                        <td align="center">
                                                                            <div style="width: 100%">
                                                                                <sd:TitlePane key="Thông tin tra cứu" id="categoryTypeTitle">
                                                                                    <form id="searchForm">
                                                                                        <table width="100%;" cellpadding="20px" cellspacing="20px">
                                                                                            <tr>
                                                                                                <td width="10%"></td>
                                                                                                <td width="40%"></td>
                                                                                                <td width="15%"></td>
                                                                                                <td width="35%"></td>
                                                                                            </tr>

                                                                                            <tr>
                                                                                                <td align="right">
                                                                                                    <sd:Label key="Tên doanh nghiệp"/>
                                                                                                </td>
                                                                                                <td>
                                                                                                    <sd:TextBox cssStyle="width:90%" trim="true"
                                                                                                                id="searchForm.businessName"
                                                                                                                key=""
                                                                                                                name="searchForm.businessName" maxlength="500"/>
                                                                                                </td>
                                                                                                <td align="right">
                                                                                                    <sd:Label key="Tên sản phẩm"/>
                                                                                                </td>
                                                                                                <td>
                                                                                                    <sd:TextBox cssStyle="width:90%" trim="true"
                                                                                                                id="searchForm.productName"
                                                                                                                key=""
                                                                                                                name="searchForm.productName" maxlength="255"/>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td colspan="4">&nbsp;</td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td align="right">
                                                                                                    <sd:Label key="Địa chỉ"/>
                                                                                                </td>
                                                                                                <td>
                                                                                                    <sd:TextBox cssStyle="width:90%" trim="true"
                                                                                                                id="searchForm.businessAddress"
                                                                                                                key=""
                                                                                                                name="searchForm.businessAddress" maxlength="500"/>
                                                                                                </td>
                                                                                                <td align="right">
                                                                                                    <sd:Label key="Số công bố"/>
                                                                                                </td>
                                                                                                <td>
                                                                                                    <sd:TextBox cssStyle="width:90%" trim="true"
                                                                                                                id="searchForm.announcementNo"
                                                                                                                key=""
                                                                                                                name="searchForm.announcementNo" maxlength="50"/>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td colspan="4">&nbsp;</td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td align="right">
                                                                                                    <sd:Label key="Ngày cấp Từ ngày"/>
                                                                                                </td>
                                                                                                <td>
                                                                                                    <sd:DatePicker cssStyle="width:40%"
                                                                                                                   id="searchForm.sendDateFrom"
                                                                                                                   key="" format="dd/MM/yyyy"
                                                                                                                   name="searchForm.sendDateFrom"/>
                                                                                                    <sd:Label key="Đến ngày"/>
                                                                                                    <sd:DatePicker cssStyle="width:40%; float: right;" 
                                                                                                                   id="searchForm.sendDateTo"
                                                                                                                   key="" format="dd/MM/yyyy"
                                                                                                                   name="searchForm.sendDateTo"/>
                                                                                                </td>
                                                                                                <td align="right">
                                                                                                    <sd:Label key="Nhóm sản phẩm"/>
                                                                                                </td>
                                                                                                <td>
                                                                                                    <sd:SelectBox cssStyle="width:90%" id="searchForm.fileTypeName" name="searchForm.fileTypeName" key="" >
                                                                                                        <sd:Option value='-1'>-- Chọn --</sd:Option>
                                                                                                        <sd:Option value='TPT'>Thực phẩm thường</sd:Option>
                                                                                                        <sd:Option value='TPCN'>Thực phẩm chức năng </sd:Option> 
                                                                                                        <sd:Option value='DBT'>Thực phẩm tăng cường vi chất dinh dưỡng </sd:Option>
                                                                                                    </sd:SelectBox>
                                                                                                </td>                                                                                                
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td colspan="4">&nbsp;</td>
                                                                                            </tr>
                                                                                            <tr style="text-align: center">
                                                                                                <td colspan="4">
                                                                                                    <sd:Button key="" onclick="page.search();" > 
                                                                                                        <img src="${contextPath}/share/images/icons/search.png" height="14" width="18">
                                                                                                        <span style="font-size:12px">Tìm kiếm<%--<sd:Property>btnCancel</sd:Property> --%></span>
                                                                                                    </sd:Button>
                                                                                                    <sd:Button key="" onclick="page.reset();" > 
                                                                                                        <img src="${contextPath}/share/images/icons/reset.png" height="14" width="18">
                                                                                                        <span style="font-size:12px">Hủy<%--<sd:Property>btnCancel</sd:Property> --%></span>
                                                                                                    </sd:Button>
                                                                                                </td>
                                                                                            </tr>

                                                                                        </table>
                                                                                    </form>
                                                                                </sd:TitlePane>
                                                                            </div>

                                                                        </td>
                                                                    </tr>

                                                                    <tr>
                                                                        <td align="center">
                                                                            <div style="width: 100%">
                                                                                <sd:TitlePane key="Kết quả tra cứu"
                                                                                              id="List" >
                                                                                    <sd:DataGrid id="grid"
                                                                                                 getDataUrl=""
                                                                                                 rowSelector="0%"
                                                                                                 style="width:auto;height:auto"
                                                                                                 rowsPerPage="20"
                                                                                                 serverPaging="true"
                                                                                                 clientSort="false"
                                                                                                 >
                                                                                        <sd:ColumnDataGrid key="STT" get="page.getNo" width="3%"  styles="text-align:center;" />                                                                                        
                                                                                        <sd:ColumnDataGrid  key="Doanh nghiệp" formatter="page.formatBusName" get="page.getIndex"
                                                                                                            width="20%"  headerStyles="text-align:center;" /> 
                                                                                        <sd:ColumnDataGrid  key="Địa chỉ" field="businessAddress"
                                                                                                            width="15%"  headerStyles="text-align:center;" />
                                                                                        <sd:ColumnDataGrid  key="Sản phẩm" field="productName"
                                                                                                            width="20%"  headerStyles="text-align:center;" />                                                                                        
                                                                                        <sd:ColumnDataGrid  key="Nhóm sản phẩm" get="page.getIndex" formatter="page.fomatCode" cellStyles="text-align:center;"
                                                                                                            width="10%"  headerStyles="text-align:center;" />
                                                                                        <sd:ColumnDataGrid  key="Số công bố" field="receiptNo"
                                                                                                            width="15%"  headerStyles="text-align:center;" />
                                                                                        <sd:ColumnDataGrid  key="Ngày cấp" field="receiptDate" format="dd/MM/yyyy" type="date"
                                                                                                            width="7%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                                                                                        <sd:ColumnDataGrid  key="Bản công bố" headerStyles="text-align:center;" width="5%" cellStyles="text-align:center;"
                                                                                                            formatter="page.formatAction" get="page.getIndex"/>
                                                                                    </sd:DataGrid>
                                                                                </sd:TitlePane>
                                                                            </div>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </div> 
                                                        </div> 
                                                    </div> 
                                                </div> 
                                            </div> 
                                        </div> 
                                    </div> 
                                </div> 
                            </div>
                        </div> 
                        <form action="#" id="hrefFm" method="post" name="hrefFm"> <span></span> </form> </div><!--End #main--> <div id="footer"> <p align="right">Copyright © 2014 Cục An toàn thực phẩm - Bộ Y tế</p> </div><!--End #footer-->
                </div>
            </div>
        </body>
        <%--
        <sd:Dialog  id="viewChangeReceipt" height="auto" width="500px"
                    key="Phiên bản hồ sơ" showFullscreenButton="false"
                    >
            <div id="selectOldVersionFilesDlgDiv">
                <jsp:include page="../other/selectOldVersionFilesDlg.jsp" flush="false"/>
            </div>
        </sd:Dialog>
        --%>
        <script>
            page.search = function () {
                dijit.byId("grid").vtReload('filesAction!onSearchLookUpHomePage.do?', "searchForm");
            };
            page.reset = function () {
                dijit.byId("searchForm.businessName").setValue("");
                dijit.byId("searchForm.productName").setValue("");
                dijit.byId("searchForm.businessAddress").setValue("");
                dijit.byId("searchForm.sendDateFrom").setValue("");
                dijit.byId("searchForm.sendDateTo").setValue("");
                page.search();
            };
            page.formatLinkDownloadPdf = function (fileId) {
                document.location = "uploadiframe!openFileSignPublic.do?fileId=" + fileId;
            };
//            page.checkHaveIsChange = function (fileId) {
//                document.location = "uploadiframe!openFileSignPublic.do?fileId=" + fileId;
//            };
            page.searchByBusinessName = function (row) {
                var item = dijit.byId("grid").getItem(row);
                dijit.byId("searchForm.businessName").setValue(item.businessName);
                dijit.byId("grid").vtReload('filesAction!onSearchLookUpHomePage.do?', "searchForm");
            };
            page.search();
        </script>
    </html>
</s:i18n>