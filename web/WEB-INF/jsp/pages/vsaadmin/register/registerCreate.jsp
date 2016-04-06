<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../../util/util_js.jsp"/>
<jsp:include page="../../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="token">
    <s:token id="tokenId"/>
</div>
<sx:ResultMessage id="resultMessage" />
<sd:TitlePane key="Đăng ký tài khoản" id="TitleTitle">
    <form id="createForm" name="createForm">
        <sd:FieldSet key="Người đại diện" id="tab.business">
            <table width="100%">
                <tr>
                    <td width="15%"></td>
                    <td width="35%"></td>
                    <td width="15%"></td>
                    <td width="35%"></td>
                </tr>
                <tr>
                    <td><sd:Label 
                            key="Họ và tên:"/><font style="color: red">*</font></td>
                    <td>
                        <sd:TextBox 
                            id="createForm.userFullName" trim="true"
                            name="createForm.userFullName" 
                            key="" 
                            maxlength="100" 
                            cssStyle="width:100%"/>                   
                    </td>
                    <td>
                        <sd:Label key="Điện thoại:"/>
                    </td>
                    <td>
                        <sd:TextBox 
                            id="createForm.userTelephone" trim="true"
                            name="createForm.userTelephone" 
                            key="" 
                            maxlength="15" 
                            cssStyle="width:100%"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label 
                            key="Số di động:"/><font style="color: red">*</font>
                    </td>
                    <td>
                        <sd:TextBox 
                            id="createForm.userMobile" trim="true"
                            name="createForm.userMobile" 
                            key="" 
                            maxlength="15" 
                            cssStyle="width:100%"/>
                    </td>
                    <td><sd:Label key="Email:"/><font style="color: red">*</font></td>
                    <td>
                        <sd:TextBox 
                            id="createForm.userEmail" trim="true"
                            name="createForm.userEmail" 
                            key="" 
                            maxlength="100" 
                            cssStyle="width:100%"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label key="Chức vụ:"/><font style="color: red">*</font></td>
                    <td>
                        <sd:SelectBox id="createForm.posId" 
                                      name="createForm.posId" 
                                      key="" 
                                      data="lstPosId" 
                                      valueField="posId" 
                                      labelField="posName"/>

                        <sd:TextBox id="createForm.posName" 
                                    name="createForm.posName" 
                                    cssStyle="display:none" 
                                    key=""
                                    trim="true"/>

                        <sd:TextBox id="createForm.businessTypeName" 
                                    name="createForm.businessTypeName" 
                                    cssStyle="display:none" 
                                    key=""
                                    trim="true"/>
                    </td>
                </tr>
            </table>
        </sd:FieldSet><br/>
        <sd:FieldSet key="Thông tin tài khoản" id="tab.userRep">
            <table width="100%">
                <tr>
                    <td width="15%"></td>
                    <td width="35%"></td>
                    <td width="15%"></td>
                    <td width="35%"></td>
                </tr>
                <tr>
                    <td><sd:Label 
                            key="Mật khẩu:"/><font style="color: red">*</font></td>
                    <td>
                        <sd:TextBox 
                            id="createForm.managePassword" trim="true"
                            name="createForm.managePassword" 
                            key="" 
                            maxlength="100" 
                            cssStyle="width:100%"
                            type="password"/>
                        <sd:TextBox 
                            id="createForm.registerId" trim="true"
                            name="createForm.registerId" 
                            cssStyle="display:none" 
                            key=""/>
                        <sd:TextBox 
                            id="createForm.status" trim="true"
                            name="createForm.status" 
                            cssStyle="display:none" 
                            key=""/>
                    </td>
                    <td><sd:Label 
                            key="Gõ lại mật khẩu:"/><font style="color: red">*</font></td>
                    <td>
                        <sd:TextBox 
                            id="createForm.manageRePassword" trim="true"
                            name="createForm.manageRePassword" 
                            key="" 
                            maxlength="100" 
                            cssStyle="width:100%"
                            type="password"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label key="Email:"/><font style="color: red">*</font></td>
                    <td>
                        <sd:TextBox 
                            id="createForm.manageEmail" trim="true"
                            name="createForm.manageEmail" 
                            key="" 
                            maxlength="100" 
                            cssStyle="width:100%"/>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </sd:FieldSet><br/>
        <sd:FieldSet key="Thông tin doanh nghiệp:" id="tab.business">
            <table width="100%">
                <tr>
                    <td width="15%"></td>
                    <td width="35%"></td>
                    <td width="15%"></td>
                    <td width="35%"></td>
                </tr>
                <tr>
                    <td><sd:Label key="Tên tiếng Việt:"/><font style="color: red">*</font></td>
                    <td>
                        <sd:TextBox 
                            id="createForm.businessNameVi" trim="true"
                            name="createForm.businessNameVi" 
                            key="" 
                            maxlength="255" 
                            cssStyle="width:100%"/>
                    </td>
                    <td>
                        <sd:Label key="Tên tiếng Anh:"/>
                    </td>
                    <td>
                        <sd:TextBox 
                            id="createForm.businessNameEng" trim="true"
                            name="createForm.businessNameEng" 
                            key="" 
                            maxlength="255" 
                            cssStyle="width:100%"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label key="Tên viết tắt:"/></td>
                    <td>
                        <sd:TextBox 
                            id="createForm.businessNameAlias" trim="true"
                            name="createForm.businessNameAlias"
                            key=""
                            maxlength="255"
                            cssStyle="width:100%"/>
                    </td>
                    <td><sd:Label key="Loại hình:"/><font style="color: red">*</font></td>
                    <td>
                        <sd:SelectBox id="createForm.businessTypeId" 
                                      name="createForm.businessTypeId" 
                                      key="" 
                                      data="lstBusinessType" 
                                      valueField="categoryId" 
                                      labelField="name" />    
                    </td>
                </tr>
                <tr>
                    <td><sd:Label key="Mã số thuế:"/><font style="color: red">*</font></td>
                    <td>
                        <sd:TextBox 
                            id="createForm.businessTaxCode" trim="true"
                            name="createForm.businessTaxCode"
                            key="" 
                            maxlength="30" 
                            cssStyle="width:100%"/>
                    </td>
                    <td><sd:Label key="Số ĐKKD:"/><font style="color: red">*</font></td>
                    <td>
                        <sd:TextBox
                            id="createForm.businessLicense" trim="true"
                            name="createForm.businessLicense"
                            key=""
                            maxlength="30"
                            cssStyle="width:100%"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label key="Địa chỉ:"/></td>
                    <td>
                        <sd:TextBox 
                            id="createForm.businessAdd" trim="true"
                            name="createForm.businessAdd"
                            key=""
                            maxlength="255"
                            cssStyle="width:100%"/>
                    </td>
                    <td><sd:Label key="Tỉnh:"/><font style="color: red">*</font></td>
                    <td>
                        <sd:SelectBox id="createForm.businessProvinceId" name="createForm.businessProvinceId" key="" data="lstProvince" valueField="categoryId" labelField="name" />
                        <sd:TextBox id="createForm.businessProvince" name="createForm.businessProvince" cssStyle="display:none" key=""/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label key="Điện thoại:"/></td>
                    <td>
                        <sd:TextBox
                            id="createForm.businessTelephone" trim="true"
                            name="createForm.businessTelephone"
                            key=""
                            maxlength="15"
                            cssStyle="width:100%"/>
                    </td>
                    <td><sd:Label key="Fax:"/></td>
                    <td>
                        <sd:TextBox 
                            id="createForm.businessFax" trim="true"
                            name="createForm.businessFax"
                            key=""
                            maxlength="15"
                            cssStyle="width:100%"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label key="Website:"/></td>
                    <td>
                        <sd:TextBox 
                            id="createForm.businessWebsite" trim="true"
                            name="createForm.businessWebsite"
                            key=""
                            maxlength="150"
                            cssStyle="width:100%"/>
                    </td>
                    <td><sd:Label key="Năm thành lập:"/></td>
                    <td>
                        <sd:TextBox 
                            id="createForm.businessEstablishYear" trim="true"
                            name="createForm.businessEstablishYear"
                            key=""
                            maxlength="4" cssStyle="width:100%"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label key="Đại diện theo pháp luật:"/></td>
                    <td>
                        <sd:TextBox 
                            id="createForm.businessLawRep" trim="true"
                            name="createForm.businessLawRep"
                            key=""
                            maxlength="50"
                            cssStyle="width:100%"/>
                    </td>
                    <td><sd:Label key="Mô tả:"/></td>
                    <td>
                        <sd:TextBox 
                            id="createForm.description" trim="true"
                            name="createForm.description" 
                            key="" 
                            maxlength="255" 
                            cssStyle="width:100%"/>
                    </td>
                </tr>
            </table>
        </sd:FieldSet>
        <div style="text-align: center">
            <sx:ButtonSave onclick="page.save();"></sx:ButtonSave>  
            <%--<sx:ButtonClose onclick="page.reset();"></sx:ButtonClose>--%>  
        </div>
    </form>
</sd:TitlePane>     

<script>
    var ckInsert = false;
    page.validate = function() {
        //check fullname
        //alert(dijit.byId("createForm.posId").getValue());
        var userFullName = dijit.byId("createForm.userFullName").getValue();
        if (userFullName != null && userFullName.trim().length > 0) {

        } else {
            alert("Vui lòng nhập họ tên đầy đủ người đại diện");
            dijit.byId("createForm.userFullName").focus();
            return false;
        }
        //check telephone if exist
        var userTelephone = dijit.byId("createForm.userTelephone").getValue();
        if (userTelephone != null && userTelephone.trim().length > 0) {
            if (!validatePhone(userTelephone)) {
                alert("[Số điện thoại] không đúng định dạng");
                dijit.byId("createForm.userTelephone").focus();
                return false;
            }
        }
        //check mobile if exist
        var userMobile = dijit.byId("createForm.userMobile").getValue();
        if (userMobile != null && userMobile.trim().length > 0) {
            if (!validatePhone(userMobile)) {
                alert("[Số di động] không đúng định dạng");
                dijit.byId("createForm.userMobile").focus();
                return false;
            }
        } else {
            alert("[Số di động] chưa nhập.");
            dijit.byId("createForm.userMobile").focus();
            return false;
        }
        //check email
        var userEmail = dijit.byId("createForm.userEmail").getValue();
        if (userEmail != null && userEmail.trim().length > 0) {
            if (!validateEmail(userEmail)) {
                alert("[Email] không đúng định dạng");
                dijit.byId("createForm.userEmail").focus();
                return false;
            }
        } else {
            alert("[Email] Email chưa nhập.");
            dijit.byId("createForm.userEmail").focus();
            return false;
        }        
        //
        var posId = dijit.byId("createForm.posId").getValue();
        if (posId == 0) {
            alert("[Chức vụ] chưa chọn");
            dijit.byId("createForm.posId").focus();
            return false;
        } else {
            var posName = dijit.byId("createForm.posId").attr("displayedValue");
            dijit.byId("createForm.posName").setValue(posName);
        }
        //check pass
        var managePassword = dijit.byId("createForm.managePassword").getValue();
        if (managePassword != null && managePassword.trim().length > 0) {

        } else {
            alert("Vui lòng nhập mật khẩu");
            dijit.byId("createForm.managePassword").focus();
            return false;
        }
        //check repass
        var manageRePassword = dijit.byId("createForm.manageRePassword").getValue();
        if (manageRePassword != managePassword) {
            alert("Mật khẩu xác thực không trùng. Vui lòng nhập lại");
            dijit.byId("createForm.manageRePassword").focus();
            return false;
        }
        var manageEmail = dijit.byId("createForm.manageEmail").getValue();
        if (manageEmail != null && manageEmail.trim().length > 0) {
            if (!validateEmail(manageEmail)) {
                alert("[Email] không đúng định dạng");
                dijit.byId("createForm.manageEmail").focus();
                return false;
            }
        } else {
            alert("Vui lòng nhập địa chỉ email tài khoản");
            dijit.byId("createForm.manageEmail").focus();
            return false;
        }
        var businessNameVi = dijit.byId("createForm.businessNameVi").getValue();
        if (businessNameVi != null && businessNameVi.trim().length > 0) {
        } else {
            alert("[Tên doanh nghiệp tiếng Việt] chưa nhập");
            dijit.byId("createForm.businessNameVi").focus();
            return false;
        }
        var businessTypeId = dijit.byId("createForm.businessTypeId").getValue();
        if (businessTypeId == 0) {
            alert("[Loại hình doanh nghiệp chưa chọn] chưa chọn");
            dijit.byId("createForm.businessTypeId").focus();
            return false;
        } else {
            var businessTypeName = dijit.byId("createForm.businessTypeId").attr("displayedValue");
            dijit.byId("createForm.businessTypeName").setValue(businessTypeName);
            //var posName = dijit.byId("createForm.posId").attr("displayedValue");
            //dijit.byId("createForm.posName").setValue(posName);
        }
        var businessTaxCode = dijit.byId("createForm.businessTaxCode").getValue();
        if (businessTaxCode != null && businessTaxCode.trim().length > 0) {
        } else {
            alert("[Mã số thuế] chưa nhập");
            dijit.byId("createForm.businessTaxCode").focus();
            return false;

        }

        var businessLicense = dijit.byId("createForm.businessLicense").getValue();
        if (businessLicense != null && businessLicense.trim().length > 0) {
        } else {
            alert("[Giấy phép đăng ký] chưa nhập");
            dijit.byId("createForm.businessLicense").focus();

            return false;
        }
        var businessProvinceId = dijit.byId("createForm.businessProvinceId").getValue();
        if (businessProvinceId == 0) {
            alert("[Tỉnh thành] chưa chọn");
            dijit.byId("createForm.businessProvinceId").focus();
            return false;
        } else {
            var provinceName = dijit.byId("createForm.businessProvinceId").attr("displayedValue");
            dijit.byId("createForm.businessProvince").setValue(provinceName);
        }
        var businessTelephone = dijit.byId("createForm.businessTelephone").getValue();
        if (businessTelephone != null && businessTelephone.trim().length > 0) {
            if (!validatePhone(businessTelephone)) {
                alert("[Số điện thoại doanh nghiệp] không đúng định dạng");
                dijit.byId("createForm.businessTelephone").focus();
                return false;
            }
        }
        var businessFax = dijit.byId("createForm.businessFax").getValue();
        if (businessFax != null && businessFax.trim().length > 0) {
            if (!validatePhone(businessFax)) {
                alert("[Số fax doanh nghiệp] không đúng định dạng");
                dijit.byId("createForm.businessFax").focus();
                return false;
            }
        }
//        var businessEstablishYear = dijit.byId("createForm.businessEstablishYear").getValue();
//        if (businessEstablishYear != null && businessEstablishYear.trim().length > 0) {
//            //var n = parseInt(businessEstablishYear);
//            if (isNaN(businessEstablishYear)) {
//                alert("[Năm thành lập] không đúng định dạng");
//                dijit.byId("createForm.businessEstablishYear").focus();
//                return false;
//            }
//        }
        return true;
    };

    page.save = function() {
        if (!ckInsert) {
            if (page.validate()) {
                dijit.byId("createForm.status").setValue(0);
                ckInsert = true;
                sd.connector.post("registerCreateAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSave);
            }
        }
    };

    page.afterSave = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {
            var businessId = dijit.byId("createForm.registerId").getValue();
            if (businessId == null || businessId == "") {
                page.clearInsertForm();
            } else {
                page.close();
            }
            page.search();
        }
        ckInsert = false;
    };
    page.clearForm = function() {
    };
    dijit.byId("createForm.userFullName").focus();
//    page.close = function() {
//        dijit.byId("createDlg").hide();
//    }
</script>
