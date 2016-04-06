<%-- 
    Document   : changePassword
    Created on : Jul 4, 2012, 9:07:49 AM
    Author     : HanPT1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="token">
    <s:token id="tokenId"/>
</div> 
<div id="resetPasswordDiv">    
    <form id="userPasswordForm" name="userPasswordForm">
        <sd:FieldSet key="Đổi mật khẩu" theme="blue">
            <table width="100%">
                <tr>
                    <th width="15%"></th>
                    <th width="35%"></th>
                    <th width="15%"></th>
                    <th width="35%"></th>
                </tr>
                <tr id="trPassword">
                    <td>
                        <sx:Label key="usersFormOnDialog.oldPassword" require="true"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox id="userPasswordForm.oldPassword" name="userPasswordForm.oldPassword" key="" cssStyle="width:80%" type="password"/>
                    </td>
                </tr>
                <tr id="trPassword">
                    <td>
                        <sx:Label key="usersFormOnDialog.newPassword" require="true"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox id="userPasswordForm.password" name="userPasswordForm.password" key="" cssStyle="width:80%" type="password"/>
                    </td>
                    <td>
                        <sx:Label key="usersFormOnDialog.retypePassword" require="true"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox id="userPasswordForm.retypePassword" name="userPasswordForm.retypePassword" key="" required="true" cssStyle="width:80%" type="password"/>
                    </td>
                </tr>                
                <tr>
                    <td style="text-align:center;" colspan="4">
                        <sd:Button id="resetPassForm.btnReset" key="changePassword" onclick="resetPass();"/>                    
                    </td>
                </tr>
            </table>
        </sd:FieldSet>
        <sd:FieldSet key="Thông tin doanh nghiệp" id="tab.business">
            <table width="100%">
                <tr>
                    <td width="15%"></td>
                    <td width="35%"></td>
                    <td width="15%"></td>
                    <td width="35%"></td>
                </tr>
                <tr>
                    <td><sd:Label key="Loại hình DN" required="true"/></td>
                    <td>                    
                        <sd:SelectBox id="userPasswordForm.businessTypeId" 
                                      name="userPasswordForm.businessForm.businessTypeId" 
                                      key="" 
                                      data="lstBusinessType" 
                                      valueField="categoryId" 
                                      labelField="name" cssStyle="width:80%"/>
                        <sd:TextBox id="userPasswordForm.businessTypeName" 
                                    name="userPasswordForm.businessForm.businessTypeName" 
                                    cssStyle="display:none" 
                                    key=""
                                    trim="true"/>
                    </td>
                    <td><sd:Label key="Tên DN tiếng Việt " required="true"/><font style="color: red">*</font></td>
                    <td>
                        <sd:TextBox id="userPasswordForm.businessName" 
                                    name="userPasswordForm.businessForm.businessName" 
                                    key="" 
                                    maxlength="200" 
                                    trim="true" cssStyle="width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label key="Tên DN tiếng Anh"/></td>
                    <td>
                        <sd:TextBox id="userPasswordForm.businessNameEng" 
                                    name="userPasswordForm.businessForm.businessNameEng" 
                                    key="" 
                                    maxlength="200"
                                    trim="true" cssStyle="width:80%"/>
                    </td>
                    <td><sd:Label key="Tên viết tắt"/></td>
                    <td>
                        <sd:TextBox id="userPasswordForm.businessNameAlias" 
                                    name="userPasswordForm.businessForm.businessNameAlias" 
                                    key="" 
                                    maxlength="200"
                                    trim="true" cssStyle="width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label key="MST " required="true"/><font style="color: red">*</font></td>
                    <td>
                        <sd:TextBox id="userPasswordForm.businessTaxCode" 
                                    name="userPasswordForm.businessForm.businessTaxCode" 
                                    key="" 
                                    maxlength="50"
                                    trim="true" readonly="true" cssStyle="width:80%"/>
                    </td>
                    <td><sd:Label key="Số ĐKKD " required="true"/><font style="color: red">*</font></td>
                    <td>
                        <sd:TextBox id="userPasswordForm.businessLicense" 
                                    name="userPasswordForm.businessForm.businessLicense" 
                                    key="" 
                                    maxlength="100"
                                    trim="true" cssStyle="width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label key="Địa chỉ DN " required="true"/><font style="color: red">*</font></td>
                    <td>
                        <sd:TextBox id="userPasswordForm.businessAddress" 
                                    name="userPasswordForm.businessForm.businessAddress" 
                                    key="" 
                                    maxlength="200"
                                    trim="true" cssStyle="width:80%"/>
                    </td>
                    <td><sd:Label key="Tỉnh/Thành phố" required="true"/></td>
                    <td>
                        <sd:SelectBox id="userPasswordForm.businessProvinceId" name="userPasswordForm.businessForm.businessProvinceId" key="" data="lstProvince" valueField="categoryId" labelField="name" cssStyle="width:80%"/>
                        <sd:TextBox id="userPasswordForm.businessProvince" name="userPasswordForm.businessForm.businessProvince" cssStyle="display:none" key=""/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label key="Điện thoại"/></td>
                    <td>
                        <sd:TextBox id="userPasswordForm.businessTelephone" 
                                    name="userPasswordForm.businessForm.businessTelephone" 
                                    key="" 
                                    maxlength="15"
                                    trim="true" cssStyle="width:80%"/>
                    </td>
                    <td><sd:Label key="Fax"/></td>
                    <td>
                        <sd:TextBox id="userPasswordForm.businessFax" 
                                    name="userPasswordForm.businessForm.businessFax" 
                                    key="" 
                                    maxlength="15"
                                    trim="true" cssStyle="width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label key="Website"/></td>
                    <td>
                        <sd:TextBox id="userPasswordForm.businessWebsite" 
                                    name="userPasswordForm.businessForm.businessWebsite" 
                                    key="" 
                                    maxlength="150"
                                    trim="true" cssStyle="width:80%"/>
                    </td>
                    <td><sd:Label key="Email doanh nghiệp"/></td>
                    <td>
                        <sd:TextBox id="userPasswordForm.businessEmail" 
                                    name="userPasswordForm.businessForm.userEmail" 
                                    key="" 
                                    maxlength="100"
                                    trim="true" cssStyle="width:80%"/>
                    </td>
                </tr>
                <tr>                    
                    <td><sd:Label key="Đại diện theo pháp luật"/></td>
                    <td>
                        <sd:TextBox id="userPasswordForm.businessLawRep" 
                                    name="userPasswordForm.businessForm.businessLawRep" 
                                    key="" 
                                    maxlength="50"
                                    trim="true" cssStyle="width:80%"/>
                    </td>
                    <td><sd:Label key="Năm thành lập"/></td>
                    <td>
                        <sd:TextBox id="userPasswordForm.businessEstablishYear" 
                                    name="userPasswordForm.businessForm.businessEstablishYear" 
                                    key="" 
                                    maxlength="4" 
                                    mask="digit"
                                    trim="true" cssStyle="width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label key="Cơ quan chủ quản"/></td>
                    <td>
                        <sd:TextBox id="userPasswordForm.businessForm.governingBody" 
                                    name="userPasswordForm.businessForm.governingBody" 
                                    key="" 
                                    maxlength="500"
                                    trim="true" cssStyle="width:80%"/>
                    </td>
                    <td><sd:Label key="Mô tả"/></td>
                    <td>
                        <sd:TextBox id="userPasswordForm.businessForm.description" 
                                    name="userPasswordForm.businessForm.description" 
                                    key="" 
                                    maxlength="255"
                                    trim="true" cssStyle="width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:center;" colspan="4">
                        <sd:Button id="resetPassForm.btnUpdateBusiness" key="Cập nhật doanh nghiệp" onclick="updateBusiness();"/>
                    </td>
                </tr>
            </table>
        </sd:FieldSet>
        <sd:FieldSet key="usersForm.info" theme="blue">
            <table width="100%">
                <tr>
                    <th width="15%"></th>
                    <th width="35%"></th>
                    <th width="15%"></th>
                    <th width="35%"></th>
                </tr>
                <tr>
                    <td>
                        <sx:Label key="usersFormOnDialog.userName" require="true"/>
                    </td>
                    <td class="tdOnForm">

                        <sd:TextBox readonly="true" trim="true" id="userPasswordForm.userName" name="userPasswordForm.userName" key="" cssStyle="width:80%" required ="true" maxlength="100"/>                         
                        <input type="hidden" id="userPasswordForm.staffId" name="userPasswordForm.staffId"/>
                        <input type="hidden" id="canShowDlg" name="canShowDlg"/>
                    </td>

                    <td>
                        <sx:Label key="usersFormOnDialog.fullName" require="true"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="userPasswordForm.fullName" name="userPasswordForm.fullName" key="" cssStyle="width:80%" required="true" maxlength="100"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <sx:Label key="usersFormOnDialog.cellphone"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="userPasswordForm.cellphone" name="userPasswordForm.cellphone" key="" cssStyle="width:80%" mask="^\d*$" maxlength="20"/>

                    </td>
                    <td>
                        <sx:Label key="usersFormOnDialog.email"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox id="userPasswordForm.email" name="userPasswordForm.email" key="" cssStyle="width:80%" />
                    </td>                    
                </tr>
                <tr>
                    <td>
                        <sx:Label key="departmentForm.root" />
                    </td>
                    <td>
                        <%--        <sd:TreePicker name="userDeptTree" id="userDeptTree" 
                                               getChildrenNodeUrl="departmentAction!getDeptChildrenDataByNode.do" 
                                               getTopNodeUrl="departmentAction!getDeptData.do" 
                                               key="" rootLabel="root" cssStyle="width:80%"
                                               /> --%>
                        <sd:TextBox cssStyle="display:none;" id="userPasswordForm.deptId" name="userPasswordForm.deptIdStr" key=""/>
                        <sd:TextBox cssStyle="width:80%" id="userPasswordForm.deptName" name="userPasswordForm.deptName" key="" readonly="true"/>
                    </td>
                    <td><sx:Label key="usersFormOnDialog.represent"/></td>
                    <td>
                        <%--     <sd:TreePicker name="userDeptRepresentTree" id="userDeptRepresentTree" getChildrenNodeUrl="departmentAction!getDeptChildrenDataByNode.do"
                                            getTopNodeUrl="departmentAction!getMyDeptRootTree.do"  key="" rootLabel="root" cssStyle="width:80%" />--%>
                        <sd:TextBox cssStyle="display:none;" id="userPasswordForm.deptRepresentId" name="userPasswordForm.deptRepresentIdStr" key=""/>
                        <sd:TextBox cssStyle="width:80%" id="userPasswordForm.deptRepresentName" name="userPasswordForm.deptRepresentName" key="" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <sx:Label key="usersFormOnDialog.posId" require="true"/>
                    </td>
                    <td>
                        <sd:SelectBox id="userPasswordForm.posId" key="" name="userPasswordForm.posIdStr" cssStyle="width:80%" required="true"
                                      valueField="posId"
                                      labelField="posName"
                                      data="lstPosition"
                                      readonly="true"
                                      />
                    </td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </sd:FieldSet>
        <sd:FieldSet key="usersForm.addInfo" theme="blue">
            <%--<div style="height:100px">--%>
            <table width="100%">
                <tr>
                    <th width="50%"></th>
                    <th width="50%"></th>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="userPasswordForm.aliasName" name="userPasswordForm.aliasName" key="usersForm.aliasName" cssStyle="width:80%" maxlength="50" />
                    </td>

                    <td class="tdOnForm">
                        <sd:SelectBox id="userPasswordForm.gender" key="usersForm.gender" name="userPasswordForm.gender" cssStyle="width:80%">
                            <sd:Option value="-1">--Chọn--</sd:Option>
                            <sd:Option value="1">Nam</sd:Option>
                            <sd:Option value="0">Nữ</sd:Option>
                        </sd:SelectBox>
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="userPasswordForm.telephone" name="userPasswordForm.telephone" key="usersForm.telephone" mask="^\d*$" cssStyle="width:80%" maxlength="20"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="userPasswordForm.fax" name="userPasswordForm.fax" key="usersForm.fax" cssStyle="width:80%" mask="digit" maxlength="20"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:DatePicker id="userPasswordForm.dateOfBirth" name="userPasswordForm.dateOfBirthDate" key="usersForm.dateOfBirth" cssStyle="width:80%" format="dd/MM/yyyy" />
                    </td>

                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="userPasswordForm.birthPlace" name="userPasswordForm.birthPlace" key="usersForm.birthPlace" cssStyle="width:80%" maxlength="100" />
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="userPasswordForm.staffCode" name="userPasswordForm.staffCode" key="usersForm.staffCode" cssStyle="width:80%" maxlength="50"/>
                    </td>

                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="userPasswordForm.identityCard" name="userPasswordForm.identityCard" key="usersForm.identityCard" cssStyle="width:80%" maxlength="14" required="true" mask="digit"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:DatePicker id="userPasswordForm.issueDateIdent" name="userPasswordForm.issueDateIdentDate" key="usersForm.issueDateIdent" format="dd/MM/yyyy" cssStyle="width:80%"/>
                    </td>

                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="userPasswordForm.issuePlaceIdent" name="userPasswordForm.issuePlaceIdent" key="usersForm.issuePlaceIdent" cssStyle="width:80%" maxlength="100"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:Textarea trim="true" id="userPasswordForm.description" name="userPasswordForm.description" key="usersForm.description" cssStyle="width:80%" maxlength="100"/>
                    </td>
                </tr>
                <tr>
                    <td style="display:none;">
                        <sd:TextBox trim="true" id="userPasswordForm.userId" name="userPasswordForm.userId" key="" />
                    </td>
                </tr>
            </table>
        </sd:FieldSet>        
    </form>
</div>
<table width="100%">
    <tr>
        <td style="text-align:center;">
            <sd:Button id="resetPassForm.btnUpdateUser" key="Cập nhật thông tin người dùng" onclick="updateUser();"/>                    
            <sd:Button id="resetPassForm.btnClose" key="Đóng" onclick="changePwdClose();"/>
        </td>
    </tr>
</table>
<script type="text/javascript">
    if ('${fn:escapeXml(userPasswordForm.gender)}' == "0") {
        dijit.byId("userPasswordForm.gender").setSelectedIndex(2);
    } else if ('${fn:escapeXml(userPasswordForm.gender)}' == "1") {
        dijit.byId("userPasswordForm.gender").setSelectedIndex(1);
    }
    if ('${fn:escapeXml(userPasswordForm.businessId)}' <= 0 || '${fn:escapeXml(userPasswordForm.businessId)}' == null) {
        document.getElementById("tab.business").style.display = "none";
    } else {
    }
    /* dijit.byId("userDeptTree").onPickData = function(item){
     try{
     if(item.id){
     sd._("usersFormOnDialog.deptId").setValue(item.id);
     sd._("usersFormOnDialog.deptName").setValue(item.name);
     }else{
     sd._("usersFormOnDialog.deptId").setValue("");
     sd._("usersFormOnDialog.deptName").setValue("");
     sd._("userDeptTree").setValue("");
     }
     }catch(err){
     alert(err.message);
     }
     }
     <c:if test="${not empty userPasswordForm.deptName}">
     var deptName="${fn:escapeXml(userPasswordForm.deptName)}";
     sd._("userDeptTree").setValue(deptName);
     </c:if>
     dijit.byId("userDeptRepresentTree").onPickData = function(item){
     try{
     if(item.id){
     sd._("usersFormOnDialog.deptRepresentId").setValue(item.id);
     sd._("usersFormOnDialog.deptRepresentName").setValue(item.name);
     }else{
     sd._("usersFormOnDialog.deptRepresentId").setValue("");
     sd._("usersFormOnDialog.deptRepresentName").setValue("");
     sd._("userDeptTree").setValue("");
     }
     }catch(err){
     alert(err.message);
     }
     }
     <c:if test="${not empty userPasswordForm.deptRepresentName}">
     var deptRepresentName="${fn:escapeXml(userPasswordForm.deptRepresentName)}";
     sd._("userDeptRepresentTree").setValue(deptRepresentName);
     </c:if>
     */
    validatePassword = function() {

        if (!dijit.byId("userPasswordForm.oldPassword").getValue()) {
            page.alert("Thông báo", "<sd:Property>msg.user.oldPasswordNull</sd:Property>", "warning");
                        dijit.byId("userPasswordForm.oldPassword").focus();
                        return false;
                    }

                    if (!dijit.byId("userPasswordForm.password").getValue()) {
                        page.alert("Thông báo", "<sd:Property>msg.user.passwordNull</sd:Property>", "warning");
                                    dijit.byId("userPasswordForm.password").focus();
                                    return false;
                                }

                                if (!dijit.byId("userPasswordForm.retypePassword").getValue()) {
                                    page.alert("Thông báo", "<sd:Property>msg.user.retypePassNull</sd:Property>", "warning");
                                                dijit.byId("userPasswordForm.retypePassword").focus();
                                                return false;
                                            }

                                            if (dijit.byId("userPasswordForm.password").getValue() != dijit.byId("userPasswordForm.retypePassword").getValue()) {
                                                page.alert("Thông báo", "<sd:Property>msg.user.passNotEquals</sd:Property>", "warning");
                                                            dijit.byId("userPasswordForm.password").focus();
                                                            return false;
                                                        }
                                                        //linhdx
                                                        // Không bắt buộc nhập
                                                        if (!dijit.byId("userPasswordForm.email").getValue()) {
                                                            //isValidate = false;
                                                            page.alert("Thông báo", "<sd:Property>msg.user.emailNull</sd:Property>", "warning");
                                                                        dijit.byId("userPasswordForm.email").focus();
                                                                        return false;
                                                                    } else {
                                                                        if (!sd.validator.isEmail(dijit.byId("userPasswordForm.email").getValue())) {
                                                                            page.alert("Thông báo", "Địa chỉ Email không đúng", "warning");
                                                                            dijit.byId("userPasswordForm.email").focus();
                                                                            return false;
                                                                        }
                                                                    }

                                                                    return true;
                                                                };

                                                                validatePasswordFormat = function() {
                                                                    var password = dijit.byId("userPasswordForm.password").getValue();

                                                                    var re = /(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*-]).{8,}/;
                                                                    if (!re.test(password)) {

                                                                        dijit.byId("userPasswordForm.password").focus();
                                                                        return false;
                                                                    }
                                                                    return true;
                                                                };

                                                                resetPass = function() {
                                                                    if (validatePassword()) {
                                                                        if (!validatePasswordFormat()) {
                                                                            msg.confirm("Mật khẩu của bạn không phải là mật khẩu mạnh, bạn có muốn tiếp tục ?<br>(Mật khẩu mạnh là mật khẩu có ít nhất 8 ký tự gồm chữ, số và ký tự đặc biệt)", "Thông báo", onResetPassword);
                                                                        } else {
                                                                            msg.confirm('<sd:Property>msg.resetPassConfirm</sd:Property>', '<sd:Property>confirm.title</sd:Property>', onResetPassword);
                                                                                        }
                                                                                    }
                                                                                };

                                                                                onResetPassword = function() {
                                                                                    sd.connector.post("home!onResetPass.do?" + token.getTokenParamString(), null, "userPasswordForm", null, afterCallbackOfResetPass);
                                                                                };

                                                                                updateUser = function() {
                                                                                    msg.confirm('<sd:Property>Bạn có muốn tiếp tục cập nhật thông tin cá nhân không?</sd:Property>', '<sd:Property>confirm.title</sd:Property>', function() {
                                                                                                sd.connector.post("home!onResetPass.do?action=updateUserInfo&" + token.getTokenParamString(), null, "userPasswordForm", null, afterCallbackOfResetPass);
                                                                                            });
                                                                                        };

                                                                                        afterCallbackOfResetPass = function(data) {
                                                                                            var result = null;
                                                                                            var obj = dojo.fromJson(data);
                                                                                            var result = obj.items;
                                                                                            var result0 = result[0];
                                                                                            var result1 = result[1];
                                                                                            dijit.byId("userPasswordForm.oldPassword").setValue("");
                                                                                            dijit.byId("userPasswordForm.password").setValue("");
                                                                                            dijit.byId("userPasswordForm.retypePassword").setValue("");
                                                                                            if (result0 == "1") {
                                                                                                alert(result1);
                                                                                                var dialog = dijit.byId("vt-changePasswowrdDialog");
                                                                                                dialog.hide();
                                                                                            } else
                                                                                            {
                                                                                                alert(result1);
                                                                                            }
//            var dialog = dijit.byId("vt-changePasswowrdDialog");
//            dialog.hide();
                                                                                        };

                                                                                        changePwdClose = function() {
                                                                                            dijit.byId("userPasswordForm.oldPassword").setValue("");
                                                                                            dijit.byId("userPasswordForm.password").setValue("");
                                                                                            dijit.byId("userPasswordForm.retypePassword").setValue("");
                                                                                            var dialog = dijit.byId("vt-changePasswowrdDialog");
                                                                                            dialog.hide();
                                                                                        };
                                                                                        updateBusiness = function() {
                                                                                            if (page.validateBus()) {
                                                                                                msg.confirm('<sd:Property>Bạn có muốn tiếp tục cập nhật thông tin doanh nghiệp không?</sd:Property>', '<sd:Property>confirm.title</sd:Property>', function() {
                                                                                                                sd.connector.post("home!onResetPass.do?action=updateBusinessInfo&" + token.getTokenParamString(), null, "userPasswordForm", null, afterUpdateBusiness);
                                                                                                            });
                                                                                                        }
                                                                                                    };
                                                                                                    afterUpdateBusiness = function(data) {
                                                                                                        var result = null;
                                                                                                        var obj = dojo.fromJson(data);
                                                                                                        var result = obj.items;
                                                                                                        var result0 = result[0];
                                                                                                        var result1 = result[1];
                                                                                                        dijit.byId("userPasswordForm.oldPassword").setValue("");
                                                                                                        dijit.byId("userPasswordForm.password").setValue("");
                                                                                                        dijit.byId("userPasswordForm.retypePassword").setValue("");
                                                                                                        if (result0 == "1") {
                                                                                                            alert(result1);
                                                                                                            var dialog = dijit.byId("vt-changePasswowrdDialog");
                                                                                                            dialog.hide();
                                                                                                        } else
                                                                                                        {
                                                                                                            alert(result1);
                                                                                                        }
                                                                                                    };
                                                                                                    page.validateBus = function() {
                                                                                                        var businessTypeId = dijit.byId("userPasswordForm.businessTypeId").getValue();
                                                                                                        if (businessTypeId == 0) {
                                                                                                            alert("[Loại hình doanh nghiệp chưa chọn] chưa chọn");
                                                                                                            dijit.byId("userPasswordForm.businessTypeId").focus();
                                                                                                            return false;
                                                                                                        } else {
                                                                                                            var businessTypeName = dijit.byId("userPasswordForm.businessTypeName").attr("displayedValue");
                                                                                                            dijit.byId("userPasswordForm.businessTypeName").setValue(provinceName);
                                                                                                        }
                                                                                                        var businessName = dijit.byId("userPasswordForm.businessName").getValue();
                                                                                                        if (businessName != null && businessName.trim().length > 0) {
                                                                                                        } else {
                                                                                                            alert("[Tên doanh nghiệp] chưa nhập");
                                                                                                            dijit.byId("userPasswordForm.businessName").focus();
                                                                                                            return false;

                                                                                                        }

                                                                                                        var businessTaxCode = dijit.byId("userPasswordForm.businessTaxCode").getValue();
                                                                                                        if (businessTaxCode != null && businessTaxCode.trim().length > 0) {
                                                                                                        } else {
                                                                                                            alert("[Mã số thuế] chưa nhập");
                                                                                                            dijit.byId("userPasswordForm.businessTaxCode").focus();
                                                                                                            return false;

                                                                                                        }
                                                                                                        var businessLicense = dijit.byId("userPasswordForm.businessLicense").getValue();
                                                                                                        if (businessLicense != null && businessLicense.trim().length > 0) {
                                                                                                        } else {
                                                                                                            alert("[Giấy phép đăng ký] chưa nhập");
                                                                                                            dijit.byId("userPasswordForm.businessLicense").focus();
                                                                                                            return false;
                                                                                                        }
                                                                                                        ;
                                                                                                        userPasswordForm.businessAddress
                                                                                                        var businessAddress = dijit.byId("userPasswordForm.businessAddress").getValue();
                                                                                                        if (businessAddress != null && businessAddress.trim().length > 0) {
                                                                                                        } else {
                                                                                                            alert("[Địa chỉ doanh nghiệp] chưa nhập");
                                                                                                            dijit.byId("userPasswordForm.businessAddress").focus();
                                                                                                            return false;

                                                                                                        }
                                                                                                        var businessProvinceId = dijit.byId("userPasswordForm.businessProvinceId").getValue();
                                                                                                        if (businessProvinceId == 0) {
                                                                                                            alert("[Tỉnh thành] chưa chọn");
                                                                                                            dijit.byId("userPasswordForm.businessProvinceId").focus();
                                                                                                            return false;
                                                                                                        } else {
                                                                                                            var provinceName = dijit.byId("userPasswordForm.businessProvinceId").attr("displayedValue");
                                                                                                            dijit.byId("userPasswordForm.businessProvince").setValue(provinceName);
                                                                                                        }
                                                                                                        var businessTelephone = dijit.byId("userPasswordForm.businessTelephone").getValue();
                                                                                                        if (businessTelephone != null && businessTelephone.trim().length > 0) {
                                                                                                            if (!validatePhone(businessTelephone)) {
                                                                                                                alert("[Số điện thoại doanh nghiệp] không đúng định dạng");
                                                                                                                dijit.byId("userPasswordForm.businessTelephone").focus();
                                                                                                                return false;
                                                                                                            }
                                                                                                        }
                                                                                                        var businessFax = dijit.byId("userPasswordForm.businessFax").getValue();
                                                                                                        if (businessFax != null && businessFax.trim().length > 0) {
                                                                                                            if (!validatePhone(businessFax)) {
                                                                                                                alert("[Số fax doanh nghiệp] không đúng định dạng");
                                                                                                                dijit.byId("userPasswordForm.businessFax").focus();
                                                                                                                return false;
                                                                                                            }
                                                                                                        }
                                                                                                        var businessEmail = dijit.byId("userPasswordForm.businessEmail").getValue();
                                                                                                        if (!sd.validator.isEmail(businessEmail)) {
                                                                                                            page.alert("Thông báo", "Địa chỉ Email doanh nghiệp không đúng định dạng", "warning");
                                                                                                            dijit.byId("userPasswordForm.businessEmail").focus();
                                                                                                            return false;
                                                                                                        }

                                                                                                        var businessEstablishYear = dijit.byId("userPasswordForm.businessEstablishYear").getValue();
                                                                                                        if (businessEstablishYear != null && businessEstablishYear.trim().length > 0) {
                                                                                                            //var n = parseInt(businessEstablishYear);
                                                                                                            if (isNaN(businessEstablishYear)) {
                                                                                                                alert("[Năm thành lập] không đúng định dạng");
                                                                                                                dijit.byId("userPasswordForm.businessEstablishYear").focus();
                                                                                                                return false;
                                                                                                            }
                                                                                                        }
                                                                                                        return true;
                                                                                                    };
</script>
