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
<div id="token">
    <s:token id="tokenId"/>
</div>
<s:i18n name="com/viettel/config/Language">   
    <% if (isMSIE) {%>
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
    <% } else {%>
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
    <% }%>
    <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <tiles:insertTemplate template="/WEB-INF/jsp/pages/layout/include.jsp" />
            <link rel="shortcut icon" href="share/homepage/images/logotitle.png" >
            <title>Lấy lại mật khẩu</title>
            <link href="share/homepage/css/style_comon.css" rel="stylesheet" type="text/css" />
            <link class="lfr-css-file" href="/share/homepage/css/main_002.css" rel="stylesheet" type="text/css"> 
            <link charset="utf-8" href="/share/homepage/css/a_002.css" rel="stylesheet" type="text/css" id="aui_3_4_0_1_57">
            <link charset="utf-8" href="/share/homepage/css/a.css" rel="stylesheet" type="text/css" id="aui_3_4_0_1_73">
        </head>
        <body class=" yui3-skin-sam controls-visible guest-site signed-out public-page site" style="font-family:'MYRIADPRO-COND'!important;
              font-size:20px !important;"> 
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
                                <div id="p_p_id_lienheattpaction_WAR_moh_tthc_2014portlet_" class="portlet-boundary portlet-boundary_lienheattpaction_WAR_moh_tthc_2014portlet_ portlet-static portlet-static-end portlet-borderless lienheattpaction-portlet " style=""> 
                                    <span id="p_lienheattpaction_WAR_moh_tthc_2014portlet"></span> 
                                    <div class="portlet-body"> 
                                        <div class="portlet-borderless-container" style=""> 
                                            <div class="portlet-body"> 

                                                <div id="main" style="min-height: 700px"> 
                                                    <div class="dgdan"> 
                                                        <p class="iconlienhe"> <a class="dd" href="#">Lấy lại mật khẩu</a> </p> 
                                                    </div> <div class="box_nd" style="margin-top: -30px !important">

                                                        <div class="Reg_form">
                                                            <form id="createForm" name="createForm">
                                                                <table class="table_reg" width="50%" border="0" cellspacing="0" cellpadding="0">
                                                                    <tr>
                                                                        <td class="Title_list_reg" colspan="4">Thông tin người dùng</td>
                                                                    </tr>                                                                    
                                                                    <tr>
                                                                        <td class="egov-label-bold">Mã số thuế<span>*</span></td>
                                                                        <td class="ego-input-form"><sd:TextBox 
                                                                                id="createForm.businessTaxCode" 
                                                                                trim="true"
                                                                                name="createForm.businessTaxCode" 
                                                                                cssClass="egov-inputfield hasKeypad"
                                                                                key="" 
                                                                                maxlength="100" 
                                                                                cssStyle="width:100%"/> </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="egov-label-bold">Email<span>*</span></td>
                                                                        <td class="ego-input-form" title="Chú ý địa chỉ Email sẽ được sử dụng để Cục thông báo thông tin tài khoản đăng nhập vào hệ thống khi được phê duyệt và các thông tin liên quan nên cần nhập chính xác và chỉ đăng ký 1 Email">
                                                                            <sd:TextBox 
                                                                                id="createForm.manageEmail" 
                                                                                trim="true"
                                                                                name="createForm.manageEmail" 
                                                                                key="" 
                                                                                maxlength="100" 
                                                                                cssStyle="width:100%" 
                                                                                cssClass="egov-inputfield hasKeypad"
                                                                                /></td>                                                                        
                                                                    </tr>                                                                  
                                                                    <tr>
                                                                        <td style="text-align: center; padding:40px 0" colspan="4">
                                                                            <input class="btn_dk" name="Dangky" type="button" value="Gửi yêu cầu" onclick="return save();" style="cursor: pointer"/>
                                                                        </td>
                                                                    </tr>
                                                                </table></form>
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
                </div>
            </div>
            <!--End #main--> 
            <div id="footer"> <p align="right">Copyright © 2014 Cục An toàn thực phẩm - Bộ Y tế</p> </div>
            <!--End #footer-->  
            <script>
                function save() {
                    if (validate()) {
                        sd.connector.post("registerCreateAction!onResetPasswordHomePage.do?" + token.getTokenParamString(), null, "createForm", null, afterSave);
                    }
                }
                ;
                function afterSave(data) {
                    var obj = dojo.fromJson(data);
                    var result = obj.items;
                    alert(result[1]);
                    if (result[0].toString() == '1')
                    {
                        clearData();
                        window.history.back(-1);
                    }
                }
                ;

                function clearData()
                {
                    dijit.byId("createForm.businessTaxCode").setValue("");
                    dijit.byId("createForm.manageEmail").setValue("");
                }
                ;
                function validate() {
                    var businessTaxCode = dijit.byId("createForm.businessTaxCode").getValue();
                    if (businessTaxCode != null && businessTaxCode.trim().length > 0) {
                    } else {
                        alert("Bạn chưa nhập mã số thuế");
                        dijit.byId("createForm.businessTaxCode").focus();
                        return false;

                    }
                    var manageEmail = dijit.byId("createForm.manageEmail").getValue();
                    if (manageEmail != null && manageEmail.trim().length > 0) {
                        if (!validateEmail(manageEmail)) {
                            alert("Bạn nhập địa chỉ Email không đúng định dạng");
                            dijit.byId("createForm.manageEmail").focus();
                            return false;
                        }
                    } else {
                        alert("Bạn chưa nhập nhập địa chỉ email tài khoản");
                        dijit.byId("createForm.manageEmail").focus();
                        return false;
                    }
                    return true;
                }
                ;
                validatePhone = function (phoneNumber) {
                    var bReturn = true;
                    if (phoneNumber == null) {
                        bReturn = false;
                    } else {
                        phoneNumber = phoneNumber.trim();
                        if (phoneNumber.length > 14) {
                            bReturn = false
                        } else {
                            try {
                                bReturn = !isNaN(parseInt(phoneNumber));
                            } catch (en) {
                                bReturn = false;
                            }
                        }
                    }
                    return bReturn;
                };
                document.getElementById("trNewPos").style.display = "none";
            </script>
        </body>
    </html>
</s:i18n>
