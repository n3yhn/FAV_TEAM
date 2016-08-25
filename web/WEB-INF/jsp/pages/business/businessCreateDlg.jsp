<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sx:ResultMessage id="resultMessage" />
<form id="createForm" name="createForm">
    <sd:FieldSet key="Thông tin người đại diện" id="tab.userRep">
        <table width="100%">
            <tr>
                <td width="25%"></td>
                <td width="25%"></td>
                <td width="25%"></td>
                <td width="25%"></td>
            </tr>
            <tr>
                <td><sd:Label key="Họ và tên " required="true"/><font style="color: red">*</font></td>
                <td>
                    <sd:TextBox id="createForm.userFullname" 
                                name="createForm.userFullname" 
                                key="" 
                                maxlength="100" 
                                trim="true"/>                    
                </td>
                <td><sd:Label key="Số điện thoại"/></td>
                <td>
                    <sd:TextBox id="createForm.userTelephone" 
                                name="createForm.userTelephone" 
                                key="" 
                                maxlength="15"
                                trim="true"/>
                </td>
            </tr>
            <tr>
                <td><sd:Label key="Số di động"/></td>
                <td>
                    <sd:TextBox id="createForm.userMobile" 
                                name="createForm.userMobile" 
                                key="" 
                                maxlength="15"
                                trim="true"/>
                </td>
                <td><sd:Label key="Email"/></td>
                <td>
                    <sd:TextBox id="createForm.userEmail" 
                                name="createForm.userEmail" 
                                key="" 
                                maxlength="50"
                                trim="true"/>
                </td>
            </tr>
            <tr>
                <td><sd:Label key="Tên tài khoản"/></td>
                <td><sd:TextBox id="createForm.userName" 
                            name="createForm.userName" 
                            key="" maxlength="50"
                            trim="true"/>
                </td>
                <td><sd:Label key="Trạng thái"/></td>
                <td>
                    <sd:SelectBox id="createForm.isActive" name="createForm.isActive" key="" >
                        <sd:Option value='-1'>-- Chọn --</sd:Option>
                        <sd:Option value='0'>Không hoạt động</sd:Option>
                        <sd:Option value='1' selected="true">Hoạt động</sd:Option>
                    </sd:SelectBox>
                </td>
            </tr>
        </table>
    </sd:FieldSet>

    <sd:FieldSet key="Thông tin doanh nghiệp" id="tab.business">
        <table width="100%">
            <tr>
                <td width="25%"></td>
                <td width="25%"></td>
                <td width="25%"></td>
                <td width="25%"></td>
            </tr>
            <tr>
                <td><sd:Label key="Loại hình doanh nghiệp" required="true"/></td>
                <td>                    
                    <sd:SelectBox id="createForm.businessTypeId" 
                                  name="createForm.businessTypeId" 
                                  key="" 
                                  data="lstBusinessType" 
                                  valueField="categoryId" 
                                  labelField="name" />
                    <sd:TextBox id="createForm.businessTypeName" 
                                name="createForm.businessTypeName" 
                                cssStyle="display:none" 
                                key=""
                                trim="true"/>
                </td>
                <td><sd:Label key="Tên DN tiếng Việt " required="true"/><font style="color: red">*</font></td>
                <td>
                    <sd:TextBox id="createForm.businessName" 
                                name="createForm.businessName" 
                                key="" 
                                maxlength="200" 
                                trim="true"/>
                </td>
            </tr>
            <tr>
                <td><sd:Label key="Tên DN tiếng Anh"/></td>
                <td>
                    <sd:TextBox id="createForm.businessNameEng" 
                                name="createForm.businessNameEng" 
                                key="" 
                                maxlength="200"
                                trim="true"/>
                </td>
                <td><sd:Label key="Tên viết tắt"/></td>
                <td>
                    <sd:TextBox id="createForm.businessNameAlias" 
                                name="createForm.businessNameAlias" 
                                key="" 
                                maxlength="200"
                                trim="true"/>
                </td>
            </tr>
            <tr>
                <td><sd:Label key="MST " required="true"/><font style="color: red">*</font></td>
                <td>
                    <sd:TextBox id="createForm.businessTaxCode" 
                                name="createForm.businessTaxCode" 
                                key="" 
                                maxlength="50"
                                trim="true"/>
                </td>
                <td><sd:Label key="Số ĐKKD " required="true"/><font style="color: red">*</font></td>
                <td>
                    <sd:TextBox id="createForm.businessLicense" 
                                name="createForm.businessLicense" 
                                key="" 
                                maxlength="100"
                                trim="true"/>
                </td>
            </tr>
            <tr>
                <td><sd:Label key="Địa chỉ doanh nghiệp " required="true"/><font style="color: red">*</font></td>
                <td>
                    <sd:TextBox id="createForm.businessAddress" 
                                name="createForm.businessAddress" 
                                key="" 
                                maxlength="200"
                                trim="true"/>
                </td>
                <td><sd:Label key="Tỉnh/Thành phố" required="true"/></td>
                <td>
                    <sd:SelectBox id="createForm.businessProvinceId" name="createForm.businessProvinceId" key="" data="lstProvince" valueField="categoryId" labelField="name" />
                    <sd:TextBox id="createForm.businessProvince" name="createForm.businessProvince" cssStyle="display:none" key=""/>
                </td>
            </tr>
            <tr>
                <td><sd:Label key="Điện thoại"/></td>
                <td>
                    <sd:TextBox id="createForm.businessTelephone" 
                                name="createForm.businessTelephone" 
                                key="" 
                                maxlength="15"
                                trim="true"/>
                </td>
                <td><sd:Label key="Fax"/></td>
                <td>
                    <sd:TextBox id="createForm.businessFax" 
                                name="createForm.businessFax" 
                                key="" 
                                maxlength="15"
                                trim="true"/>
                </td>
            </tr>
            <tr>
                <td><sd:Label key="Website"/></td>
                <td>
                    <sd:TextBox id="createForm.businessWebsite" 
                                name="createForm.businessWebsite" 
                                key="" 
                                maxlength="150"
                                trim="true"/>
                </td>
                <td><sd:Label key="Năm thành lập"/></td>
                <td>
                    <sd:TextBox id="createForm.businessEstablishYear" 
                                name="createForm.businessEstablishYear" 
                                key="" 
                                maxlength="4" 
                                mask="digit"
                                trim="true"/>
                </td>
            </tr>
            <tr>
                <td><sd:Label key="Đại diện theo pháp luật"/></td>
                <td>
                    <sd:TextBox id="createForm.businessLawRep" 
                                name="createForm.businessLawRep" 
                                key="" 
                                maxlength="50"
                                trim="true"/>
                </td>
                <td><sd:Label key="Mô tả"/></td>
                <td>
                    <sd:TextBox id="createForm.description" 
                                name="createForm.description" 
                                key="" 
                                maxlength="255"
                                trim="true"/>
                </td>
            </tr>
        </table>
    </sd:FieldSet>
    <div style="text-align: center">
        <sx:ButtonSave onclick="page.save();"></sx:ButtonSave>  
        <sx:ButtonClose onclick="page.close();"></sx:ButtonClose>  
        </div>

    </form>

    <script>

        page.validate = function() {
            var userFullname = dijit.byId("createForm.userFullname").getValue();
            if (userFullname == null || userFullname.trim().length == 0) {
                alert("Bạn chưa nhập tên người đại diện");
                dijit.byId("createForm.userFullname").focus();
                return false;
            }
            //        var userTelephone = dijit.byId("createForm.userTelephone").getValue();
            //        if (userTelephone != null && userTelephone.trim().length > 0) {
            //            if (!validatePhone(userTelephone)) {
            //                alert("[Số điện thoại] không đúng định dạng");
            //                dijit.byId("createForm.userTelephone").focus();
            //                return false;
            //            }
            //        }

            var userMobile = dijit.byId("createForm.userMobile").getValue();
            if (userMobile != null && userMobile.trim().length > 0) {
                if (!validatePhone(userMobile)) {
                    alert("[Số di động] không đúng định dạng");
                    dijit.byId("createForm.userMobile").focus();
                    return false;
                }
            }
            var userEmail = dijit.byId("createForm.userEmail").getValue();
            if (userEmail != null && userEmail.trim().length > 0) {
                if (!validateEmail(userEmail)) {
                    alert("[Email] không đúng định dạng");
                    dijit.byId("createForm.userEmail").focus();
                    return false;
                }
            }

            var isActive = dijit.byId("createForm.isActive").getValue();
            if (isActive < 0) {
                alert("[Trạng thái] chưa chọn ");
                dijit.byId("createForm.isActive").focus();
                return false;
            }
            var businessTypeId = dijit.byId("createForm.businessTypeId").getValue();
            if (businessTypeId == 0) {
                alert("[Loại hình doanh nghiệp chưa chọn] chưa chọn");
                dijit.byId("createForm.businessTypeId").focus();
                return false;
            } else {
                var businessTypeName = dijit.byId("createForm.businessTypeName").attr("displayedValue");
                dijit.byId("createForm.businessTypeName").setValue(provinceName);
            }
            var businessName = dijit.byId("createForm.businessName").getValue();
            if (businessName != null && businessName.trim().length > 0) {
            } else {
                alert("[Tên doanh nghiệp] chưa nhập");
                dijit.byId("createForm.businessName").focus();
                return false;

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
            ;
            createForm.businessAddress
            var businessAddress = dijit.byId("createForm.businessAddress").getValue();
            if (businessAddress != null && businessAddress.trim().length > 0) {
            } else {
                alert("[Địa chỉ doanh nghiệp] chưa nhập");
                dijit.byId("createForm.businessAddress").focus();
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
            var businessEstablishYear = dijit.byId("createForm.businessEstablishYear").getValue();
            if (businessEstablishYear != null && businessEstablishYear.trim().length > 0) {
                //var n = parseInt(businessEstablishYear);
                if (isNaN(businessEstablishYear)) {
                    alert("[Năm thành lập] không đúng định dạng");
                    dijit.byId("createForm.businessEstablishYear").focus();
                    return false;
                }
            }
            return true;
        };

        page.save = function() {
            if (page.validate()) {
                sd.connector.post("businessAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSave);
            }
        };

        page.afterSave = function(data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            resultMessage_show("resultMessage", result[0], result[1], 5000);
            var result0 = result[0];
            if (result0 == "3") {
            } else {
                var businessId = dijit.byId("createForm.businessId").getValue();
                if (businessId == null || businessId == "") {
                    page.clearInsertForm();
                } else {
                    page.close();
                }
                page.search();
            }
        };


        page.close = function() {
            dijit.byId("createDlg").hide();
        };
        page.afterClose = function() {
            msg.confirm('<sd:Property>confirm.close</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.close);
        };
</script>