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

    <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <tiles:insertTemplate template="/WEB-INF/jsp/pages/layout/include.jsp" />
            <link rel="shortcut icon" href="share/homepage/images/logotitle.png" >
            <title>Đăng ký tài khoản Doanh nghiệp</title>
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
                                                        <p class="iconlienhe"> <a class="dd" href="#">Đăng ký tài khoản Doanh nghiệp</a> </p> 
                                                    </div> <div class="box_nd" style="margin-top: -30px !important">

                                                        <div class="Reg_form">
                                                            <form id="createForm" name="createForm">
                                                                <table class="table_reg" width="94%" border="0" cellspacing="0" cellpadding="0">
                                                                    <tr>
                                                                        <td class="Title_list_reg" colspan="4">Thông tin Doanh nghiệp</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="egov-label-bold">Tên tiếng Việt<font style="color:red">*</font></td>
                                                                        <td class="ego-input-form" colspan="3"><sd:TextBox 
                                                                                id="createForm.businessNameVi" 
                                                                                trim="true"
                                                                                name="createForm.businessNameVi"  
                                                                                cssClass="egov-inputfield hasKeypad"
                                                                                key="" 
                                                                                maxlength="100" 
                                                                                cssStyle="width:100%"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="egov-label-bold">Tên tiếng Anh</td>
                                                                        <td class="ego-input-form" colspan="3"><sd:TextBox 
                                                                                id="createForm.businessNameEng" 
                                                                                trim="true"
                                                                                name="createForm.businessNameEng" 
                                                                                cssClass="egov-inputfield hasKeypad"
                                                                                key="" 
                                                                                maxlength="100" 
                                                                                cssStyle="width:100%"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="egov-label-bold">Tên viết tắt</td>
                                                                        <td class="ego-input-form" colspan="3" title="Chú ý cần nhập chính xác MST Doanh nghiệp nếu không hệ thống sẽ từ chối đăng ký trên hệ thống">
                                                                            <sd:TextBox 
                                                                                id="createForm.businessNameAlias" 
                                                                                trim="true"
                                                                                name="createForm.businessNameAlias"  
                                                                                cssClass="egov-inputfield hasKeypad"
                                                                                key="" 
                                                                                maxlength="100" 
                                                                                cssStyle="width:100%"/></td>
                                                                    </tr>                            
                                                                    <tr>
                                                                        <td class="egov-label-bold">Mã số thuế<font style="color:red">*</font></td>
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
                                                                        <td class="egov-label-bold">Loại hình<font style="color:red">*</font></td>
                                                                        <td class="ego-input-form">
                                                                            <sd:SelectBox id="createForm.businessTypeId" 
                                                                                          name="createForm.businessTypeId" 
                                                                                          key="" 
                                                                                          data="lstBusinessType" 
                                                                                          valueField="categoryId" 
                                                                                          labelField="name" cssClass="egov-select"/>
                                                                            <sd:TextBox id="createForm.businessTypeName" 
                                                                                        name="createForm.businessTypeName" 
                                                                                        cssStyle="display:none" 
                                                                                        key=""
                                                                                        trim="true"/>
                                                                        </td>                                
                                                                        <td class="egov-label-bold">Số đăng ký KD<font style="color:red">*</font></td>
                                                                        <td class="ego-input-form"><sd:TextBox 
                                                                                id="createForm.businessLicense" 
                                                                                trim="true"
                                                                                name="createForm.businessLicense" 
                                                                                cssClass="egov-inputfield hasKeypad"
                                                                                key="" 
                                                                                maxlength="100" 
                                                                                cssStyle="width:100%"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="egov-label-bold">Cơ quan chủ quản<font style="color:red">*</font></td>
                                                                        <td class="ego-input-form" colspan="3"><sd:TextBox 
                                                                                id="createForm.governingBody" 
                                                                                trim="true"
                                                                                name="createForm.governingBody"  
                                                                                cssClass="egov-inputfield hasKeypad"
                                                                                key="" 
                                                                                maxlength="100" 
                                                                                cssStyle="width:100%"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="Title_list_reg" colspan="4">Địa chỉ</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="egov-label-bold">Tỉnh/Thành phố<font style="color:red">*</font></td>
                                                                        <td class="ego-input-form">
                                                                            <sd:SelectBox 
                                                                                id="createForm.businessProvinceId" 
                                                                                name="createForm.businessProvinceId" 
                                                                                key="" 
                                                                                data="lstProvince" 
                                                                                valueField="categoryId" 
                                                                                labelField="name" 
                                                                                cssClass="egov-select"/>
                                                                            <sd:TextBox id="createForm.businessProvince" 
                                                                                        name="createForm.businessProvince" 
                                                                                        cssStyle="display:none" 
                                                                                        key=""/>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="egov-label-bold">Địa chỉ chi tiết<font style="color:red">*</font></td>
                                                                        <td class="ego-input-form" colspan="3"><sd:TextBox 
                                                                                id="createForm.businessAdd" 
                                                                                trim="true"
                                                                                name="createForm.businessAdd"  
                                                                                cssClass="egov-inputfield hasKeypad"
                                                                                key="" 
                                                                                maxlength="500" 
                                                                                cssStyle="width:100%"/>
                                                                        </td>
                                                                    </tr>                            
                                                                    <tr>
                                                                        <td class="egov-label-bold">Điện thoại<font style="color:red">*</font></td>
                                                                        <td class="ego-input-form"><sd:TextBox 
                                                                                id="createForm.businessTelephone" 
                                                                                trim="true"
                                                                                name="createForm.businessTelephone" 
                                                                                cssClass="egov-inputfield hasKeypad"
                                                                                key="" 
                                                                                maxlength="100" 
                                                                                cssStyle="width:100%"/>
                                                                        </td>
                                                                        <td class="egov-label-bold">Fax</td>
                                                                        <td class="ego-input-form"><sd:TextBox 
                                                                                id="createForm.businessFax" 
                                                                                trim="true"
                                                                                name="createForm.businessFax" 
                                                                                cssClass="egov-inputfield hasKeypad"
                                                                                key="" 
                                                                                maxlength="100" 
                                                                                cssStyle="width:100%"/>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td></td>
                                                                        <td>
                                                                            <span>Ví dụ: 0431234567, 0831234567.. không chứa các ký tự '+84', '()', dấu cách.. chỉ cho phép nhập số!</span>
                                                                        </td>
                                                                        <td colspan = "2">
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="egov-label-bold">Email<font style="color:red">*</font></td>
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
                                                                        <td class="egov-label-bold">Website</td>
                                                                        <td class="ego-input-form"><sd:TextBox 
                                                                                id="createForm.businessWebsite" 
                                                                                trim="true"
                                                                                name="createForm.businessWebsite" 
                                                                                cssClass="egov-inputfield hasKeypad"
                                                                                key="" 
                                                                                maxlength="100" 
                                                                                cssStyle="width:100%"/>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td></td>
                                                                        <td>
                                                                            <span>Chú ý địa chỉ Email sẽ được sử dụng để Cục thông báo thông tin tài khoản đăng nhập vào hệ thống khi được phê duyệt và các thông tin liên quan nên cần nhập chính xác và chỉ đăng ký 1 Email, ví dụ: congtya@tenmien.com</span>
                                                                        </td>
                                                                        <td colspan = "2">
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="Title_list_reg" colspan="4">Người đại diện</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="egov-label-bold">Họ và Tên<font style="color:red">*</font></td>
                                                                        <td class="ego-input-form" colspan="3"><sd:TextBox 
                                                                                id="createForm.userFullName" 
                                                                                trim="true"
                                                                                name="createForm.userFullName" 
                                                                                cssClass="egov-inputfield hasKeypad"
                                                                                key="" 
                                                                                maxlength="100" 
                                                                                cssStyle="width:100%"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="egov-label-bold">Chức vụ<font style="color:red">*</font></td>
                                                                        <td class="ego-input-form" title="Nếu bạn không thấy chức vụ tương ứng, bạn có thể chọn 'Chức vụ khác' để có thể nhập chức vụ mới phù hợp">
                                                                            <sd:SelectBox id="createForm.posId" 
                                                                                          name="createForm.posId" 
                                                                                          key="" 
                                                                                          data="lstPosId" 
                                                                                          valueField="posId" 
                                                                                          labelField="posName" 
                                                                                          cssClass="egov-select"
                                                                                          onchange="checkPos()"
                                                                                          />

                                                                            <sd:TextBox id="createForm.posName" 
                                                                                        name="createForm.posName" 
                                                                                        cssStyle="display:none" 
                                                                                        key=""
                                                                                        trim="true"/></td>
                                                                        <td class="egov-label-bold">Số di động<font style="color:red">*</font></td>
                                                                        <td class="ego-input-form" title="Chú ý số di động sẽ được sử dụng để Cục thông báo các thông tin liên quan qua tin nhắn nên cần nhập chính xác">
                                                                            <sd:TextBox 
                                                                                id="createForm.userTelephone" 
                                                                                trim="true"
                                                                                name="createForm.userTelephone"  
                                                                                cssClass="egov-inputfield hasKeypad"
                                                                                key="" 
                                                                                maxlength="100" 
                                                                                cssStyle="width:100%"                                                                                
                                                                                /></td>
                                                                        <td class="egov-label-bold">&nbsp;</td>
                                                                        <td class="ego-input-form">&nbsp;</td>
                                                                    </tr> 
                                                                    <tr>
                                                                        <td></td>
                                                                        <td>
                                                                            <span>Nếu bạn không thấy chức vụ tương ứng, bạn có thể chọn 'Chức vụ khác' để có thể nhập chức vụ mới phù hợp</span>
                                                                        </td>
                                                                        <td></td>
                                                                        <td>
                                                                            <span>Chú ý số di động sẽ được sử dụng để Cục thông báo các thông tin liên quan qua tin nhắn nên cần nhập chính xác! Ví dụ: 0912345678, 0903868686.. không chứa các ký tự '+84', '()', dấu cách.. chỉ cho phép nhập số!</span>
                                                                        </td>
                                                                    </tr>
                                                                    <tr id="trNewPos">
                                                                        <td class="egov-label-bold">Chức vụ mới<font style="color:red">*</font></td>
                                                                        <td class="ego-input-form" ><sd:TextBox 
                                                                                id="createForm.newPosName" 
                                                                                trim="true"
                                                                                name="createForm.newPosName" 
                                                                                cssClass="egov-inputfield hasKeypad"
                                                                                key="" 
                                                                                maxlength="100" 
                                                                                cssStyle="width:100%"/></td>
                                                                        <td class="ego-input-form" colspan="2">&nbsp;</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: center; padding:40px 0" colspan="4">
                                                                            <input class="btn_dk" name="Dangky" type="button" value="Đăng ký" onclick="return save();" style="cursor: pointer"/>
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

                function checkPos() {
                    var posId = dijit.byId("createForm.posId").getValue();
                    if (posId == -1)
                    {
                        document.getElementById("trNewPos").style.display = "";
                    }
                    else
                    {
                        document.getElementById("trNewPos").style.display = "none";
                    }
                }
                ;

                function save() {
                    if (validate()) {
                        sd.connector.post("registerCreateAction!onInsertHomePage.do?", null, "createForm", null, afterSave);
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
                    dijit.byId("createForm.businessNameVi").setValue("");
                    dijit.byId("createForm.businessNameEng").setValue("");
                    dijit.byId("createForm.businessNameAlias").setValue("");
                    dijit.byId("createForm.businessTaxCode").setValue("");
                    dijit.byId("createForm.businessTypeId").setValue(0);
                    dijit.byId("createForm.businessLicense").setValue("");
                    dijit.byId("createForm.businessProvinceId").setValue(0);
                    dijit.byId("createForm.businessAdd").setValue("");
                    dijit.byId("createForm.businessTelephone").setValue("");
                    dijit.byId("createForm.businessFax").setValue("");
                    dijit.byId("createForm.manageEmail").setValue("");
                    dijit.byId("createForm.businessWebsite").setValue("");
                    dijit.byId("createForm.userFullName").setValue("");
                    dijit.byId("createForm.posId").setValue(0);
                    dijit.byId("createForm.posName").setValue("");
                    dijit.byId("createForm.userTelephone").setValue("");
                    dijit.byId("createForm.newPosName").setValue("");
                }
                function validate() {
                    var businessNameVi = dijit.byId("createForm.businessNameVi").getValue();
                    if (businessNameVi != null && businessNameVi.trim().length > 0) {
                    } else {
                        alert("Bạn chưa nhập tên doanh nghiệp tiếng Việt");
                        dijit.byId("createForm.businessNameVi").focus();
                        return false;
                    }
                    var businessTaxCode = dijit.byId("createForm.businessTaxCode").getValue();
                    if (businessTaxCode != null && businessTaxCode.trim().length > 0) {
                    } else {
                        alert("Bạn chưa nhập mã số thuế");
                        dijit.byId("createForm.businessTaxCode").focus();
                        return false;

                    }
                    var businessTypeId = dijit.byId("createForm.businessTypeId").getValue();
                    if (businessTypeId == 0) {
                        alert("Bạn chưa chọn loại hình doanh nghiệp");
                        dijit.byId("createForm.businessTypeId").focus();
                        return false;
                    } else {
                        var businessTypeName = dijit.byId("createForm.businessTypeId").attr("displayedValue");
                        dijit.byId("createForm.businessTypeName").setValue(businessTypeName);
                    }
                    var businessLicense = dijit.byId("createForm.businessLicense").getValue();
                    if (businessLicense != null && businessLicense.trim().length > 0) {
                    } else {
                        alert("Bạn chưa nhập giấy phép đăng ký");
                        dijit.byId("createForm.businessLicense").focus();

                        return false;
                    }

                    var governingBody = dijit.byId("createForm.governingBody").getValue();
                    if (governingBody == null
                            || governingBody.trim().length == 0) {
                        alert("Bạn chưa nhập nhập Cơ quan chủ quản.");
                        dijit.byId("createForm.governingBody").focus();
                        return false;
                    }

                    var businessProvinceId = dijit.byId("createForm.businessProvinceId").getValue();
                    if (businessProvinceId == 0) {
                        alert("Bạn chưa chọn Tỉnh/Thành phố");
                        dijit.byId("createForm.businessProvinceId").focus();
                        return false;
                    } else {
                        var provinceName = dijit.byId("createForm.businessProvinceId").attr("displayedValue");
                        dijit.byId("createForm.businessProvince").setValue(provinceName);
                    }

                    var businessAdd = dijit.byId("createForm.businessAdd").getValue();
                    if (businessAdd != null && businessAdd.trim().length > 0) {
                    } else {
                        alert("Bạn chưa nhập địa chỉ chi tiết");
                        dijit.byId("createForm.businessAdd").focus();
                        return false;

                    }

                    var businessTelephone = dijit.byId("createForm.businessTelephone").getValue();
                    if (businessTelephone != null && businessTelephone.trim().length > 0) {
                        if (!validatePhone(businessTelephone)) {
                            alert("Bạn nhập số điện thoại không đúng định dạng");
                            dijit.byId("createForm.businessTelephone").focus();
                            return false;
                        }
                    }
                    else {
                        alert("Bạn chưa nhập số điện thoại");
                        dijit.byId("createForm.businessTelephone").focus();
                        return false;
                    }

                    var businessFax = dijit.byId("createForm.businessFax").getValue();
                    if (businessFax != null && businessFax.trim().length > 0) {
                        if (!validatePhone(businessFax)) {
                            alert("Bạn nhập số Fax không đúng định dạng");
                            dijit.byId("createForm.businessFax").focus();
                            return false;
                        }
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

                    var userFullName = dijit.byId("createForm.userFullName").getValue();
                    if (userFullName != null && userFullName.trim().length > 0) {

                    } else {
                        alert("Bạn chưa nhập nhập họ tên đầy đủ người đại diện");
                        dijit.byId("createForm.userFullName").focus();
                        return false;
                    }

                    //
                    var posId = dijit.byId("createForm.posId").getValue();
                    if (posId == 0) {
                        alert("Bạn chưa chọn chức vụ người đại diện");
                        dijit.byId("createForm.posId").focus();
                        return false;
                    } else {
                        var posName = dijit.byId("createForm.posId").attr("displayedValue");
                        dijit.byId("createForm.posName").setValue(posName);
                    }

                    var newPosName = dijit.byId("createForm.newPosName").getValue();
                    if (posId == -1 && (newPosName == null || newPosName.trim().length == 0)) {
                        alert("Bạn chưa chọn chức vụ mới người đại diện");
                        dijit.byId("createForm.newPosName").focus();
                        return false;
                    }

                    var userTelephone = dijit.byId("createForm.userTelephone").getValue();
                    if (userTelephone != null && userTelephone.trim().length > 0) {
                        if (!validatePhone(userTelephone)) {
                            alert("Bạn nhập số điện thoại người đại diện không đúng định dạng");
                            dijit.byId("createForm.userTelephone").focus();
                            return false;
                        }
                    }
                    else {
                        alert("Bạn chưa nhập số điện thoại người đại diện");
                        dijit.byId("createForm.userTelephone").focus();
                        return false;
                    }

                    return true;
                }

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
                }
                document.getElementById("trNewPos").style.display = "none";
            </script>
        </body>
    </html>
</s:i18n>
