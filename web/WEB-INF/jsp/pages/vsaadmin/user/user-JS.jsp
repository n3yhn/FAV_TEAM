<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script>
    var clientAction = "none";//"search","insert","update","delete","none";


    page.selectAll = function(gridId){
        var grid = document.getElementById(gridId);
        var lstItem = grid.getElementsByTagName("input");
        if(lstItem == null || lstItem.length ==0)
            return;
        for(var i=0;i<lstItem.length;i++){
            if(lstItem[i].getAttribute("type") == "checkbox"){
                lstItem[i].checked = true;
            }
        }
    }
    page.unSelectAll = function(gridId){
        var grid = document.getElementById(gridId);
        var lstItem = grid.getElementsByTagName("input");
        if(lstItem == null || lstItem.length ==0)
            return;
        for(var i=0;i<lstItem.length;i++){
            if(lstItem[i].getAttribute("type") == "checkbox"){
                lstItem[i].checked = false;
            }
        }
    }

    page.getIndex = function(inRowIndex){
        return dijit.byId("listUserGridId").currentRow + inRowIndex + 1;
    }


    page.onConvert = function(){
        sd.connector.post("UserAction!fromStaffToUser.do",null,null,null,null);
    }

    /********************* Check whether any row is checked *************************/
    page.isChecked = function(gridId){
        var bReturn = false;
        var items = dijit.byId(gridId).vtGetCheckedItems();
        if(items == null || items.length ==0){
            
        } else {
            bReturn = true;
        }
        return bReturn;
    };
    /*************Xu li ket qua tra ve sau loi goi vtReload********************/
    page.callback = function(data, bSuccess){
        switch (data.customInfo[0]){
            case "deleteSuccess":
                page.alert("Thông báo","<sd:Property>msg.deleteOk</sd:Property>", "info");
                page.onSearch();
                break;
            case "deletePart":

                //  dijit.byId("dialogUndeleteUser").show();
                //  sd._("undeleteUserGridId").vtReload("UserAction!listUndeleteUser.do", null);
                
                page.alert("Thông báo","Các bản ghi sau không xóa được do còn ràng buộc: " + data.customInfo[1],"warning");
                break;
            case "deleteFail":
                page.alert("Thông báo", "Có lỗi khi thực hiện delete","error");
                page.onSearch();
                break;
            case "insertOk":
                page.onSearch();
                if (data.items.length > 2) {
                    sd._("usersFormOnDialog.userId").setValue(data.items[0].userId);
                }
                page.alert("Thông báo","<sd:Property>msg.insertOk</sd:Property>", "info");                
                clearUserDialog();
                break;
            case "insertErr":
                page.alert("Thông báo","<sd:Property>msg.user.insertErr</sd:Property>","error");
                dijit.byId("usersFormOnDialog.userName").focus();
                break;
            case "updateOk":
                page.onSearch();
                page.alert("Thông báo","<sd:Property>msg.updateOk</sd:Property>","infos");
                var dialog = dijit.byId("dialogUserId");
                dialog.hide();
                break;
            case "updateErr":
                page.alert("Thông báo","<sd:Property>msg.user.insertErr</sd:Property>", "error");
                break;
            case "resetSuccess":
                page.alert("Thông báo","<sd:Property>msg.resetPassOk</sd:Property>", "info");
                break;
            case "resetFail":
                page.alert("Thông báo","<sd:Property>msg.resetPassErr</sd:Property>", "error");
                break;
            case "invalidIp":
                page.alert("Thông báo", "Địa chỉ IP không hợp lệ!","error");
                break;
            default:
                break;
            }
        };

        //********************************VIEW DETAILS******************************
    page.clickIconView = function (inRow){
            clientAction = "view";
            clearUserDialog();
            page.changeDialogInputStyle();
            var row = dijit.byId("listUserGridId").getItem(inRow);
            var dialog = dijit.byId("dialogUserId");
            dialog.titleNode.innerHTML = "Xem thông tin";
            page.changeDialogButtonStyle(false);


            sd._("usersFormOnDialog.userName").setValue(row.userName);
            sd._("usersFormOnDialog.fullName").setValue(row.fullName);
            //            sd._("usersForm.locationId").setValue(row.locationId);
            sd._("usersFormOnDialog.email").setValue(row.email);
            sd._("usersFormOnDialog.cellphone").setValue(row.cellphone);
            sd._("usersFormOnDialog.status").setValue(row.status);
            sd._("usersFormOnDialog.posId").setValue(row.posId);
            sd._("usersFormOnDialog.userId").setValue(row.userId);
            sd._("usersFormOnDialog.aliasName").setValue(row.aliasName);
            sd._("userDeptTree").setValue(row.deptName);
            sd._("usersFormOnDialog.deptId").setValue(row.deptId);
            sd._("usersFormOnDialog.businessId").setValue(row.businessId);
            //sd._("usersFormOnDialog.deptRepresentId").setValue(row.deptRepresentId);
            //sd._("usersFormOnDialog.staffName").setValue(row.staffName);
            //document.getElementById("usersFormOnDialog.staffId").value =row.staffId;
    <%--sd._("usersFormOnDialog.ip").setValue(row.ip);
    sd._("usersFormOnDialog.ignoreCheckIp").setValue(row.ignoreCheckIp);--%>
                /*
                if (row.profileId != ""){
                    sd._("usersFormOnDialog.profileId").setValue(row.profileId);
                }
                else{
                    sd._("usersFormOnDialog.profileId").setValue("0");
                }
                 */

    <%--            if(row.posId != ""){
                    sd._("usersFormOnDialog.posId").setValue(row.posId);
                }
                alert("posId");
    --%>
                var tmp = row.lastChangePassword + "";
                if (tmp != ""){
                    sd._("usersFormOnDialog.lastChangePassword").setValue(tmp);
                }

                tmp = row.lastResetPassword + "";
                if (tmp != ""){
                    sd._("usersFormOnDialog.lastResetPassword").setValue(tmp);
                }

                tmp = row.lastLogin + "";
                if (tmp != ""){
                    sd._("usersFormOnDialog.lastLogin").setValue(tmp);
                }

                document.getElementById("canShowDlg").value = "view";
                /*
                if(row.userTypeId != ""){
                    if(sd._("usersFormOnDialog.type").getValue() != row.userTypeId){
                        sd._("usersFormOnDialog.type").setValue(row.userTypeId);
                    }
                } else {
                    sd._("usersFormOnDialog.type").setValue(0);
                }*/

                if(row.gender != ""){
                    sd._("usersFormOnDialog.gender").setValue(row.gender);
                }

                sd._("usersFormOnDialog.telephone").setValue(row.telephone);
                sd._("usersFormOnDialog.fax").setValue(row.fax);

                if(row.dateOfBirth != ""){
                    sd._("usersFormOnDialog.dateOfBirth").setValue(page.convertStringToDate(row.dateOfBirth));
                }

                sd._("usersFormOnDialog.birthPlace").setValue(row.birthPlace);
                sd._("usersFormOnDialog.staffCode").setValue(row.staffCode);
                sd._("usersFormOnDialog.identityCard").setValue(row.identityCard);
                sd._("usersFormOnDialog.issuePlaceIdent").setValue(row.issuePlaceIdent);

                if(row.issueDateIdent != ""){
                    sd._("usersFormOnDialog.issueDateIdent").setValue(page.convertStringToDate(row.issueDateIdent));
                }
                if(row.description != ""){
                    sd._("usersFormOnDialog.description").setValue(row.description.toString());
                }
                dialog.show();
            }

            //********************************SEARCH************************************
    page.onSearch = function (){

                // trim text on form
                sd._("usersForm.userName").setValue(dojo.trim(dijit.byId("usersForm.userName").getValue()));
                sd._("usersForm.fullName").setValue(dojo.trim(dijit.byId("usersForm.fullName").getValue()));
                sd._("usersForm.cellphone").setValue(dojo.trim(dijit.byId("usersForm.cellphone").getValue()));
                
                dijit.byId('listUserGridId').vtReload("UserAction!onSearch.do","usersForm", null, null) ;
            }
            //********************************INSERT************************************
    page.preInsert = function (){
                clientAction = "insert";
                page.changeDialogInputStyle();
                clearUserDialog();
        
                var dialog = dijit.byId("dialogUserId");
                dialog.titleNode.innerHTML = "Thêm mới người dùng";
                document.getElementById("canShowDlg").value = "create";
                page.changeDialogButtonStyle(/*boolean isOnInsert*/true);
                dialog.show();
            }
    page.onInsert = function (){
        var currentDate = dojo.date.locale.format(new Date(), {
            datePattern : 'dd/MM/yyyy',
            selector : 'date'
        });

        // trim text on form
        sd._("usersFormOnDialog.userName").setValue(dojo.trim(dijit.byId("usersFormOnDialog.userName").getValue()));
        sd._("usersFormOnDialog.fullName").setValue(dojo.trim(dijit.byId("usersFormOnDialog.fullName").getValue()));
        sd._("usersFormOnDialog.cellphone").setValue(dojo.trim(dijit.byId("usersFormOnDialog.cellphone").getValue()));
        sd._("usersFormOnDialog.password").setValue(dojo.trim(dijit.byId("usersFormOnDialog.password").getValue()));
        sd._("retypePassword").setValue(dojo.trim(dijit.byId("retypePassword").getValue()));
        sd._("usersFormOnDialog.email").setValue(dojo.trim(dijit.byId("usersFormOnDialog.email").getValue()));
<%--sd._("usersFormOnDialog.ip").setValue(dojo.trim(dijit.byId("usersFormOnDialog.ip").getValue()));--%>

        //validation

        if(!dijit.byId("usersFormOnDialog.userName").getValue()){
            //isValidate = false;
            page.alert("Thông báo","<sd:Property>msg.user.userNameNull</sd:Property>", "warning");
            dijit.byId("usersFormOnDialog.userName").focus();
            return false;
        }
        if(!dijit.byId("usersFormOnDialog.fullName").getValue()){
            //isValidate = false;
            page.alert("Thông báo","<sd:Property>msg.user.fullNameNull</sd:Property>", "warning");
            dijit.byId("usersFormOnDialog.fullName").focus();
            return false;
        }



        if(!dijit.byId("usersFormOnDialog.password").getValue()){
            page.alert("Thông báo","<sd:Property>msg.user.passwordNull</sd:Property>", "warning");
            dijit.byId("usersFormOnDialog.password").focus();
            return false;
        }

        if(!dijit.byId("retypePassword").getValue()){
            page.alert("Thông báo","<sd:Property>msg.user.retypePassNull</sd:Property>", "warning");
            dijit.byId("retypePassword").focus();
            return false;
        }

        if(dijit.byId("usersFormOnDialog.password").getValue() != dijit.byId("retypePassword").getValue()){
            page.alert("Thông báo","<sd:Property>msg.user.passNotEquals</sd:Property>", "warning");
            dijit.byId("usersFormOnDialog.password").focus();
            return false;
        }

        var password = dijit.byId("usersFormOnDialog.password").getValue();

        var re = /(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*-]).{8,}/;
        if(!re.test(password)) {            
            page.alert("Thông báo","<sd:Property>Mật khẩu phải có ít nhất 8 ký tự gồm chữ, số và ký tự đặc biệt</sd:Property>", "warning");
            dijit.byId("usersFormOnDialog.password").focus();
            return false; 
        }
        if(dijit.byId("usersFormOnDialog.email").getValue() != "" && !sd.validator.isEmail(dijit.byId("usersFormOnDialog.email").getValue())){
            page.alert("Thông báo","Địa chỉ Email không đúng", "warning");
            dijit.byId("usersFormOnDialog.email").focus();
            return false;
        }
        if(!dijit.byId("usersFormOnDialog.cellphone").getValue()){
            page.alert("Thông báo","<sd:Property>msg.user.cellPhoneNull</sd:Property>", "warning");
            dijit.byId("usersFormOnDialog.cellphone").focus();
            return false;
        }

        if(!dijit.byId("usersFormOnDialog.cellphone").getValue()){
        }else if(!sd.validator.isIntegerNumber(dijit.byId("usersFormOnDialog.cellphone").getValue()))
        {
            page.alert("Thông báo",'Số di động nhập chưa đúng định dạng', "warning");
            dijit.byId("usersFormOnDialog.cellphone").focus();
            return false;
        }

//                if ((dijit.byId("usersFormOnDialog.deptId").attr("value") != null || dijit.byId("usersFormOnDialog.deptId").attr("value") > 0) && (dijit.byId("usersFormOnDialog.businessId").getValue() != null || dijit.byId("usersFormOnDialog.businessId").getValue() > 0)) {
//                    page.alert("Thông báo","User Cán bộ nghiệp vụ bắt buộc chọn đơn vị, user doanh nghiệp bắt buộc chọn Doanh nghiệp", "warning");
//                    return false;
//                }
//                
//                if ((dijit.byId("usersFormOnDialog.deptId").attr("value") == null || dijit.byId("usersFormOnDialog.deptId").attr("value") <= 0) && (dijit.byId("usersFormOnDialog.businessId").getValue() == null || dijit.byId("usersFormOnDialog.businessId").getValue() < 0)) {
//                    page.alert("Thông báo","User Cán bộ nghiệp vụ bắt buộc chọn đơn vị, user doanh nghiệp bắt buộc chọn Doanh nghiệp", "warning");
//                    return false;
//                }
        if(!dijit.byId("usersFormOnDialog.posId").attr("value") || dijit.byId("usersFormOnDialog.posId").attr("value") < 0){
            //isValidate = false;
            page.alert("Thông báo","<sd:Property>msg.user.posIdNull</sd:Property>", "warning");
            dijit.byId("usersFormOnDialog.posId").focus();
            return false;
        }
        if(!dijit.byId("usersFormOnDialog.status").attr("value") || dijit.byId("usersFormOnDialog.status").attr("value") < 0){
            //isValidate = false;
            page.alert("Thông báo","<sd:Property>msg.user.statusNull</sd:Property>", "warning");
            dijit.byId("usersFormOnDialog.status").focus();
            return false;
        }
        if(!sd._("usersFormOnDialog.dateOfBirth").isValid()){
            page.alert("Thông báo","Ngày sinh không đúng định dạng", "warning");
            dijit.byId("usersFormOnDialog.dateOfBirth").focus();
            return false;
        } else {
            var birthDay = document.getElementById("usersFormOnDialog.dateOfBirth");
            if(birthDay.value != null && birthDay.value.toString().length >0)
                if(!sd.validator.compareDates(dojo.trim(birthDay.value),currentDate)){
                    page.alert("Thông báo","Ngày sinh phải nhỏ hơn ngày hiện tại", "warning");
                    birthDay.focus();
                    return false;
                }
        }
        /*
        var identityCardRegex=/^[a-zA-Z0-9]*$/;
        temp = dijit.byId('usersFormOnDialog.identityCard').value;
        if(!dijit.byId("usersFormOnDialog.identityCard").getValue()){
            page.alert("Thông báo","<sd:Property>msg.user.identityCardNull</sd:Property>", "warning");
            dijit.byId("usersFormOnDialog.identityCard").focus();
            return false;
        }


        if (temp != undefined && temp != null && temp.toString().length > 0){
            if (!identityCardRegex.test(temp)){
                page.alert('Số CMT chỉ được nhập chữ cái và chữ số');
                dijit.byId("usersFormOnDialog.identityCard").focus();
                return false;
            }
            while (temp.toString().length < 9){
                temp = "0" + temp;
            }
            dijit.byId('usersFormOnDialog.identityCard').setValue(temp);
        }
         */

        if(!sd._("usersFormOnDialog.issueDateIdent").isValid()){
            page.alert("Thông báo","Ngày cấp CMT không đúng định dạng", "warning");
            dijit.byId("usersFormOnDialog.issueDateIdent").focus();
            return false;
        } else {
            var issueDateIdent = document.getElementById("usersFormOnDialog.issueDateIdent");
            if(issueDateIdent.value != null && issueDateIdent.value.toString().length >0)
                if(!sd.validator.compareDates(dojo.trim(issueDateIdent.value),currentDate)){
                    page.alert("Thông báo","Ngày cấp chứng minh thư phải nhỏ hơn ngày hiện tại", "warning");
                    issueDateIdent.focus();
                    return false;
                }
        }

        var issuePlaceIdentRegex=/^[a-zA-Z ]*$/;
        temp = dijit.byId('usersFormOnDialog.issuePlaceIdent').value;
        if (temp != undefined && temp != null && temp.toString().length > 0){
            temp = page.convertStringFromVietnameseToEnglish(temp);
            if (!issuePlaceIdentRegex.test(temp)){
                alert('Nơi cấp CMT chỉ được nhập chữ cái và khoảng trắng');
                dijit.byId("usersFormOnDialog.issuePlaceIdent").focus();
                return false;
            }
        }

        page.checkIdentityCard(function() {
            msg.confirm('<sd:Property>confirm.update</sd:Property>',"<sd:Property>confirm.title</sd:Property>", function() {
                dijit.byId('listUserGridId').vtReload("UserAction!onInsert.do?"+ token.getTokenParamString(), "usersFormOnDialog", null, page.callback) ;
            });
        });
    };

            //********************************UPDATE************************************
            page.preUpdate = function (){
                clientAction = "update";
                page.changeDialogInputStyle();
                clearUserDialog();
        
                var dialog = dijit.byId("dialogUserId");
                dialog.titleNode.innerHTML = "Sửa thông tin";
                page.changeDialogButtonStyle(false);

                var row= dijit.byId("listUserGridId").selection.getSelected()[0];
                if (row == undefined) {
                    page.alert("Thông báo",'<sd:Property>msg.unselect</sd:Property>', "warning");
                    return false;
                }

                sd._("usersFormOnDialog.userName").setValue(row.userName);
                sd._("usersFormOnDialog.fullName").setValue(row.fullName);
                //                sd._("usersForm.locationId").setValue(row.locationId);
                sd._("usersFormOnDialog.email").setValue(row.email);
                sd._("usersFormOnDialog.cellphone").setValue(row.cellphone);
                
                sd._("userDeptTree").setValue(row.deptName);
                
                sd._("usersFormOnDialog.status").setValue(row.status);
                sd._("usersFormOnDialog.userId").setValue(row.userId);
                //sd._("usersFormOnDialog.type").setValue(row.userTypeId);

                sd._("usersFormOnDialog.aliasName").setValue(row.aliasName);
                //sd._("usersFormOnDialog.staffName").setValue(row.staffName);
                //document.getElementById("usersFormOnDialog.staffId").value = row.staffId;

    <%--sd._("usersFormOnDialog.ip").setValue(row.ip);--%>

    <%--                if(row.deptName != ""){
                        sd._("deptTreeDlg").setValue(row.deptName);
                    }

                if(row.deptId != ""){
                    sd._("usersFormOnDialog.deptId").setValue(row.deptId);
                }

                if(row.posId != ""){
                    sd._("usersFormOnDialog.posId").setValue(row.posId);
                }
    --%>
                var tmp = row.lastChangePassword + "";
                if (tmp != ""){
                    sd._("usersFormOnDialog.lastChangePassword").setValue(tmp);
                }

                tmp = row.lastResetPassword + "";
                if (tmp != ""){
                    sd._("usersFormOnDialog.lastResetPassword").setValue(tmp);
                }

                tmp = row.lastLogin + "";
                if (tmp != ""){
                    sd._("usersFormOnDialog.lastLogin").setValue(tmp);
                }


    <%--                if (row.profileId != ""){
                        sd._("usersFormOnDialog.profileId").setValue(row.profileId);
                    }
                    else{
                        sd._("usersFormOnDialog.profileId").setValue("0");
                    }
    --%>
        
                if(row.gender != ""){
                    sd._("usersFormOnDialog.gender").setValue(row.gender);
                }

                sd._("usersFormOnDialog.telephone").setValue(row.telephone);
                sd._("usersFormOnDialog.fax").setValue(row.fax);

                if(row.dateOfBirth != ""){
                    sd._("usersFormOnDialog.dateOfBirth").setValue(page.convertStringToDate(row.dateOfBirth));
                }


                sd._("usersFormOnDialog.birthPlace").setValue(row.birthPlace);
                sd._("usersFormOnDialog.staffCode").setValue(row.staffCode);
                sd._("usersFormOnDialog.identityCard").setValue(row.identityCard);

                if(row.issueDateIdent != ""){
                    sd._("usersFormOnDialog.issueDateIdent").setValue(page.convertStringToDate(row.issueDateIdent));
                }
        
                sd._("usersFormOnDialog.issuePlaceIdent").setValue(row.issuePlaceIdent);

                if(row.description != ""){
                    sd._("usersFormOnDialog.description").setValue(row.description.toString());
                }

                dialog.show();
            };

            page.clickIconUpdate = function (inRow){
                var row = dijit.byId("listUserGridId").getItem(inRow);

                clientAction = "update";
                clearUserDialog();
                var dialog = dijit.byId("dialogUserId");
                dialog.titleNode.innerHTML = "Sửa thông tin";
                page.changeDialogInputStyle();
                page.changeDialogButtonStyle(false);

                sd._("usersFormOnDialog.userName").setValue(row.userName);
                sd._("usersFormOnDialog.fullName").setValue(row.fullName);
                //                sd._("usersForm.locationId").setValue(row.locationId);
                sd._("usersFormOnDialog.email").setValue(row.email);
                sd._("usersFormOnDialog.cellphone").setValue(row.cellphone);
                sd._("usersFormOnDialog.status").setValue(row.status);
                sd._("usersFormOnDialog.posId").setValue(row.posId);
                sd._("usersFormOnDialog.userId").setValue(row.userId);
                sd._("userDeptTree").setValue(row.deptName);
                sd._("usersFormOnDialog.deptId").setValue(row.deptId);
                //sd._("userDeptRepresentTree").setValue(row.deptRepresentName);
                sd._("usersFormOnDialog.businessId").setValue(row.businessId);
                sd._("usersFormOnDialog.businessName").setValue(row.businessName);
                //                document.getElementById("usersFormOnDialog.staffId").value = row.staffId;
    <%--sd._("usersFormOnDialog.ip").setValue(row.ip);--%>
    <%--sd._("usersFormOnDialog.ignoreCheckIp").setValue(row.ignoreCheckIp);--%>

    <%--                if (row.profileId != ""){
                        sd._("usersFormOnDialog.profileId").setValue(row.profileId);
                    }
                    else{
                        sd._("usersFormOnDialog.profileId").setValue("0");
                    }
    --%>
                //                var userTypeId = row.userTypeId;
                //                if(userTypeId == null || userTypeId == "")
                //                    userTypeId = 0;

                //                if(sd._("usersFormOnDialog.type").getValue() != userTypeId){
                //                    sd._("usersFormOnDialog.type").setValue(userTypeId);
                //                    document.getElementById("canShowDlg").value = "edit";
                //                } else {
                //                    document.getElementById("canShowDlg").value = "true";
                //                }

                sd._("usersFormOnDialog.aliasName").setValue(row.aliasName);

                if(row.gender != ""){
                    sd._("usersFormOnDialog.gender").setValue(row.gender);
                }

    <%--                if(row.deptName != ""){
                        sd._("deptTreeDlg").setValue(row.deptName);
                    }

                if(row.deptId != ""){
                    sd._("usersFormOnDialog.deptId").setValue(row.deptId);
                }

                if(row.posId != ""){
                    sd._("usersFormOnDialog.posId").setValue(row.posId);
                }
    --%>
                var tmp = row.lastChangePassword + "";
                if (tmp != ""){
                    sd._("usersFormOnDialog.lastChangePassword").setValue(tmp);
                }

                tmp = row.lastResetPassword + "";
                if (tmp != ""){
                    sd._("usersFormOnDialog.lastResetPassword").setValue(tmp);
                }

                tmp = row.lastLogin + "";
                if (tmp != ""){
                    sd._("usersFormOnDialog.lastLogin").setValue(tmp);
                }
               
                sd._("usersFormOnDialog.telephone").setValue(row.telephone);
                sd._("usersFormOnDialog.fax").setValue(row.fax);

                if(row.dateOfBirth != ""){
                    sd._("usersFormOnDialog.dateOfBirth").setValue(page.convertStringToDate(row.dateOfBirth));
                }


                sd._("usersFormOnDialog.birthPlace").setValue(row.birthPlace);
                sd._("usersFormOnDialog.staffCode").setValue(row.staffCode);
                sd._("usersFormOnDialog.identityCard").setValue(row.identityCard);

                if(row.issueDateIdent != ""){
                    sd._("usersFormOnDialog.issueDateIdent").setValue(page.convertStringToDate(row.issueDateIdent));
                }

                sd._("usersFormOnDialog.issuePlaceIdent").setValue(row.issuePlaceIdent);

                if(row.description != ""){
                    sd._("usersFormOnDialog.description").setValue(row.description.toString());
                }

                dialog.show();
            }

            
            page.onUpdate = function (){
                var currentDate = dojo.date.locale.format(new Date(), {
                    datePattern : 'dd/MM/yyyy',
                    selector : 'date'
                });
                // trim text on form
                sd._("usersFormOnDialog.userName").setValue(dojo.trim(dijit.byId("usersFormOnDialog.userName").getValue()));
                sd._("usersFormOnDialog.fullName").setValue(dojo.trim(dijit.byId("usersFormOnDialog.fullName").getValue()));
                sd._("usersFormOnDialog.cellphone").setValue(dojo.trim(dijit.byId("usersFormOnDialog.cellphone").getValue()));
                sd._("usersFormOnDialog.password").setValue(dojo.trim(dijit.byId("usersFormOnDialog.password").getValue()));
                sd._("retypePassword").setValue(dojo.trim(dijit.byId("retypePassword").getValue()));
                sd._("usersFormOnDialog.email").setValue(dojo.trim(dijit.byId("usersFormOnDialog.email").getValue()));
    <%--sd._("usersFormOnDialog.ip").setValue(dojo.trim(dijit.byId("usersFormOnDialog.ip").getValue()));--%>

                //[ Validation
                if(!dijit.byId("usersFormOnDialog.userName").getValue()){
                    //isValidate = false;
                    page.alert("Thông báo","<sd:Property>msg.user.userNameNull</sd:Property>", "warning");
                    dijit.byId("usersFormOnDialog.userName").focus();
                    return false;
                }
                if(!dijit.byId("usersFormOnDialog.fullName").getValue()){
                    //isValidate = false;
                    page.alert("Thông báo","<sd:Property>msg.user.fullNameNull</sd:Property>", "warning");
                    dijit.byId("usersFormOnDialog.fullName").focus();
                    return false;
                }
                /*
                if(!dijit.byId("usersFormOnDialog.profileId").getValue()){
                    alert('Bạn chưa chọn profile cho người dùng.');
                    dijit.byId("usersFormOnDialog.profileId").focus();
                    return false;
                }
                 */
                if(!dijit.byId("usersFormOnDialog.cellphone").getValue()){
                    page.alert("Thông báo","<sd:Property>msg.user.cellPhoneNull</sd:Property>", "warning");
                    dijit.byId("usersFormOnDialog.cellphone").focus();
                    return false;
                }
                if(dijit.byId("usersFormOnDialog.email").getValue() != "" && !sd.validator.isEmail(dijit.byId("usersFormOnDialog.email").getValue())){
                    page.alert("Thông báo","Địa chỉ Email không đúng", "warning");
                    dijit.byId("usersFormOnDialog.email").focus();
                    return false;
                }
                //                }

                if(!dijit.byId("usersFormOnDialog.cellphone").getValue()){
                    //isValidate = false;
                    //                    page.alert("Thông báo","Chưa nhập số di động", "warning");
                    //                    dijit.byId("usersFormOnDialog.cellphone").focus();
                    //                    return false;
                }else if(!sd.validator.isIntegerNumber(dijit.byId("usersFormOnDialog.cellphone").getValue()))
                {
                    page.alert("Thông báo",'Số di động nhập chưa đúng định dạng', "warning");
                    dijit.byId("usersFormOnDialog.cellphone").focus();
                    return false;
                }
//                if ((dijit.byId("usersFormOnDialog.deptId").attr("value") != null || dijit.byId("usersFormOnDialog.deptId").attr("value") > 0) && (dijit.byId("usersFormOnDialog.businessId").getValue() != null || dijit.byId("usersFormOnDialog.businessId").getValue() > 0)) {
//                    page.alert("Thông báo","User Cán bộ nghiệp vụ bắt buộc chọn đơn vị, user doanh nghiệp bắt buộc chọn Doanh nghiệp", "warning");
//                    return false;
//                }
//                
//                if ((dijit.byId("usersFormOnDialog.deptId").attr("value") == null || dijit.byId("usersFormOnDialog.deptId").attr("value") <= 0) && (dijit.byId("usersFormOnDialog.businessId").getValue() == null || dijit.byId("usersFormOnDialog.businessId").getValue() < 0)) {
//                    page.alert("Thông báo","User Cán bộ nghiệp vụ bắt buộc chọn đơn vị, user doanh nghiệp bắt buộc chọn Doanh nghiệp", "warning");
//                    return false;
//                }
                
                if(!dijit.byId("usersFormOnDialog.posId").attr("value") || dijit.byId("usersFormOnDialog.posId").attr("value") < 0){
                    //isValidate = false;
                    page.alert("Thông báo","<sd:Property>msg.user.posIdNull</sd:Property>", "warning");
                    dijit.byId("usersFormOnDialog.posId").focus();
                    return false;
                }
                if(!dijit.byId("usersFormOnDialog.status").attr("value") || dijit.byId("usersFormOnDialog.status").attr("value") < 0){
                    //isValidate = false;
                    page.alert("Thông báo","<sd:Property>msg.user.statusNull</sd:Property>", "warning");
                    dijit.byId("usersFormOnDialog.status").focus();
                    return false;
                }
                //            //[congdh 30/10/2010
                //            temp = dijit.byId('usersFormOnDialog.staffCode').value;
                //            if (temp != undefined && temp != null && temp.toString().length > 0 && temp.toString().length != 6){
                //                alert('Mã nhân viên phải gồm 6 chữ số.');
                //                dijit.byId("usersFormOnDialog.staffCode").focus();
                //                return false;
                //            }

                if(!sd._("usersFormOnDialog.dateOfBirth").isValid()){
                    page.alert("Thông báo","Ngày sinh không đúng định dạng", "warning");
                    dijit.byId("usersFormOnDialog.dateOfBirth").focus();
                    return false;
                } else {
                    var birthDay = document.getElementById("usersFormOnDialog.dateOfBirth");
                    if (birthDay.value != null && birthDay.value.toString().length > 0)
                        if(!sd.validator.compareDates(dojo.trim(birthDay.value),currentDate)){
                            page.alert("Thông báo","Ngày sinh phải nhỏ hơn ngày hiện tại", "warning");
                            birthDay.focus();
                            return false;
                        }
                }

                if(!sd._("usersFormOnDialog.issueDateIdent").isValid()){
                    page.alert("Thông báo","Ngày cấp CMT không đúng định dạng", "warning");
                    dijit.byId("usersFormOnDialog.issueDateIdent").focus();
                    return false;
                } else {
                    var issueDateIdent = document.getElementById("usersFormOnDialog.issueDateIdent");
                    if (issueDateIdent.value != null && issueDateIdent.value.toString().length > 0)
                        if(!sd.validator.compareDates(dojo.trim(issueDateIdent.value),currentDate)){
                            page.alert("Thông báo","Ngày cấp chứng minh thư phải nhỏ hơn ngày hiện tại", "warning");
                            issueDateIdent.focus();
                            return false;
                        }
                }

                var issuePlaceIdentRegex=/^[a-zA-Z ]*$/;
                temp = dijit.byId('usersFormOnDialog.issuePlaceIdent').value;
                if (temp != undefined && temp != null && temp.toString().length > 0){
                    temp = page.convertStringFromVietnameseToEnglish(temp);
                    if (!issuePlaceIdentRegex.test(temp)){
                        alert('Nơi cấp CMT chỉ được nhập chữ cái và khoảng trắng');
                        dijit.byId("usersFormOnDialog.issuePlaceIdent").focus();
                        return false;
                    }
                }

                //congdh]

                //[congdh 06/10/2010


                //congdh 06/10/2010]
                page.checkIdentityCard(function() {
                    msg.confirm('<sd:Property>confirm.update</sd:Property>',"<sd:Property>confirm.title</sd:Property>", function() {
                        dijit.byId('listUserGridId').vtReload("UserAction!onUpdate.do?"+ token.getTokenParamString(),"usersFormOnDialog",null, page.callback) ;
                    });
                });
            };
            //********************************DELETE************************************

            page.onDelete = function (){

                if(!page.isChecked("listUserGridId")){
                    page.alert("Thông báo","<sd:Property>msg.unselect</sd:Property>", "warning");
                    return false;
                }

                var userId = "";
                var items = dijit.byId("listUserGridId").vtGetCheckedItems();
                if(items == null || items.length ==0){
                    page.alert("Thông báo","<sd:Property>msg.unselect</sd:Property>", "warning");
                    return false;
                } else {
                    for(var j = 0; j < items.length;j++){
                        if (!page.checkRight(items[j])) {
                            return;
                        }
                        userId = userId +","+ items[j].userId;
                    }
                }

                msg.confirm('<sd:Property>confirm.delete</sd:Property>',"<sd:Property>confirm.title</sd:Property>", function() {
                    sd.connector.post("UserAction!onDelete.do?userId="+userId+"&"+ token.getTokenParamString(), null, null, null, page.returnMessageDelete);
                });
            
                //page.confirmAsJS('<sd:Property>confirm.delete</sd:Property>', callDelete);
            };

            page.returnMessageDelete = function(data){
                var obj = JSON.parse(data);
                var status = obj.customInfo;
                var lst=obj.items;                
                document.getElementById("lblSuccessMessage").innerHTML=lst[0];
                document.getElementById("lblErrorMessage").innerHTML=lst[1];
                if(status.toString().indexOf('1') >= 0){
                    document.getElementById("tblSuccessMessage").style.display='';
                }else if(status.toString().indexOf('2') >= 0){
                    document.getElementById("tblErrorMessage").style.display='';
                }
                else if(status.toString().indexOf('3') >= 0){
                    document.getElementById("tblErrorMessage").style.display='';
                    document.getElementById("tblSuccessMessage").style.display='';
                }
                var items = dijit.byId("listUserGridId").vtGetCheckedItems();
                var lengthItems = items.length;
                for(var i = 0; i< lengthItems;i++){
                    var itemDel = items[i];
                    var notDel=false;
                    if(lst.length>2){
                        for(var j = 2;j<lst.length;j++){
                            if(itemDel.userId!=null&&lst[j]==itemDel.userId.toString()){
                                notDel=true;
                            }
                        }
                    }
                    if(!notDel){
                        dijit.byId("listUserGridId").store.deleteItem(itemDel);
                    }
                }
                page.onSearch();
                window.setTimeout( "hideMessage()", 5000 );
            };

            hideMessage = function(){
                document.getElementById("tblSuccessMessage").style.display = 'none';
                document.getElementById("tblErrorMessage").style.display = 'none';
            };

            //**************************** RESET PASS *****************
            page.clickResetPasswordImg = function(inRow){
                var row = dijit.byId("listUserGridId").getItem(inRow);
                sd._("resetPassForm.userId").setValue(row.userId);
                sd._("resetPassForm.password").setValue("");
                sd._("resetPassForm.retypePassword").setValue("");
                var dialog = dijit.byId("resetPassDlg");
                dialog.titleNode.innerHTML = "Sửa mật khẩu";
                dialog.show();
                setFirstFocus("resetPassForm");
            };

            page.onResetPass = function(){
                if(!dijit.byId("resetPassForm.password").getValue()){
                    page.alert("Thông báo","<sd:Property>msg.user.passwordNull</sd:Property>", "warning");
                    dijit.byId("resetPassForm.password").focus();
                    return false;
                }

                if(!dijit.byId("resetPassForm.retypePassword").getValue()){
                    page.alert("Thông báo","<sd:Property>msg.user.retypePassNull</sd:Property>", "warning");
                    dijit.byId("resetPassForm.retypePassword").focus();
                    return false;
                }

                if(dijit.byId("resetPassForm.password").getValue() != dijit.byId("resetPassForm.retypePassword").getValue()){
                    page.alert("Thông báo","<sd:Property>msg.user.passNotEquals</sd:Property>", "warning");
                    dijit.byId("resetPassForm.password").focus();
                    return false;
                }
                
                var password = dijit.byId("resetPassForm.password").getValue();
                var re = /(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*-]).{8,}/;
                if(!re.test(password)) {            
                    page.alert("Thông báo","<sd:Property>Mật khẩu phải có ít nhất 8 ký tự gồm chữ, số và ký tự đặc biệt</sd:Property>", "warning");
                    dijit.byId("resetPassForm.password").focus();
                    return false; 
                }

                var afterCallback = function(){
                    page.onSearch();
                    //page.alert("Thông báo","<sd:Property>msg.resetPassOk</sd:Property>", "info");
                    showResultMessage("resultMessage", "1", "<sd:Property>msg.resetPassOk</sd:Property>");
                }

                msg.confirm('<sd:Property>msg.resetPassConfirm</sd:Property>',"<sd:Property>confirm.title</sd:Property>", function() {
                    sd.connector.post("UserAction!onResetPass.do?"+ token.getTokenParamString(), null,"resetPassForm", null, afterCallback);
                });

                //page.confirmAsJS('<sd:Property>msg.resetPassConfirm</sd:Property>', callReset);
            };

            //**************************************************************************
            page.changeDialogInputStyle = function(){
                if (clientAction=="view"){
           
                    sd.widget.__setReadOnly("usersFormOnDialog.userName",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.fullName",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.status",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.email",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.cellphone",true);
                    //sd.widget.__setReadOnly("usersFormOnDialog.type",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.aliasName",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.gender",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.telephone",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.fax",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.dateOfBirth",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.birthPlace",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.staffCode",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.identityCard",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.issueDateIdent",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.issuePlaceIdent",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.description",true);
                    sd.widget.__setReadOnly("userDeptTree",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.posId",true);
                    sd.widget.__setReadOnly("usersFormOnDialog.businessId",true);
                    
                    
    <%--sd.widget.__setReadOnly("usersFormOnDialog.ip",true);
    sd.widget.__setReadOnly("usersFormOnDialog.ignoreCheckIp",true);--%>
                    //                            sd.widget.__setReadOnly("usersFormOnDialog.posId",true);
                }else{
                    if(clientAction == "update"){
                        sd.widget.__setReadOnly("usersFormOnDialog.userName",true);
                    } else {
                        sd.widget.__setReadOnly("usersFormOnDialog.userName",false);
                    }
                    sd.widget.__setReadOnly("usersFormOnDialog.fullName",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.status",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.email",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.cellphone",false);
                    //sd.widget.__setReadOnly("usersFormOnDialog.type",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.aliasName",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.gender",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.telephone",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.fax",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.dateOfBirth",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.birthPlace",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.staffCode",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.identityCard",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.issueDateIdent",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.issuePlaceIdent",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.description",false);
                    sd.widget.__setReadOnly("userDeptTree",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.posId",false);
                    sd.widget.__setReadOnly("usersFormOnDialog.businessId",false);
                    
                    
    <%-- sd.widget.__setReadOnly("usersFormOnDialog.ip",false);
     sd.widget.__setReadOnly("usersFormOnDialog.ignoreCheckIp",false);--%>
                     //                            sd.widget.__setReadOnly("usersFormOnDialog.posId",false);
                 }
             };

             //isOnInsert = true (on Insert)
             page.changeDialogButtonStyle = function (/*boolean isOnInsert*/ isOnInsert){
                 if(clientAction == "view"){
                     dijit.byId( "usersFormOnDialog.btnInsert" ).domNode.style.display = "none";
                     dijit.byId( "usersFormOnDialog.btnUpdate" ).domNode.style.display = "none";
                     document.getElementById("trPassword").style.display= "none";
                 }else{
                     dijit.byId( "usersFormOnDialog.btnInsert" ).domNode.style.display = isOnInsert?"":"none";
                     sd.widget.__setReadOnly("usersFormOnDialog.status",false);

                     dijit.byId( "usersFormOnDialog.btnUpdate" ).domNode.style.display = isOnInsert?"none":"";
                     document.getElementById("trPassword").style.display= isOnInsert?"":"none";
                 }
             };


             //*****************************************************************************
             page.convertUserStatus = function(inDatum){
                 switch(inDatum){
                     case "1":
                         return 'Hoạt động';
                     case "0":
                         return 'Bị khóa';
                 }
             };

             //***************************************************************************
             page.convertRoleStatus = function(inDatum){
                 switch(inDatum){
                     case 1:
                         return 'Hoạt động';
                     case 0:
                         return 'Bị khóa';
                 }
            
             };
             //####################################### ADD NEW ROLE FOR USER  ################################
             // [ list role of selected user
             page.clickViewRole = function(inRow){
                 var row= dijit.byId("listUserGridId").getItem(inRow);
                 sd._("usersForm.userId").setValue(row.userId);
                 sd._("usersForm2.userName").setValue(row.userName);

                 sd._("lstRoleTitlePane").setTitle("<sd:Property>usersForm.roleTitle</sd:Property>"+row.userName);
                 dijit.byId("roleGridId").vtReload("UserAction!searchRoleOfUsers.do?userId="+row.userId, "usersForm", null, null);
                 var dialog = dijit.byId("userRoleDlg");
                 dialog.titleNode.innerHTML = "Vai trò";
                 dialog.show();
             };
             //]

             page.searchUserRole = function(){

                 // trim text on form
                 sd._("userRoleFormOnDialog.roleName").setValue(dojo.trim(dijit.byId("userRoleFormOnDialog.roleName").getValue()));
                 sd._("userRoleFormOnDialog.roleCode").setValue(dojo.trim(dijit.byId("userRoleFormOnDialog.roleCode").getValue()));

                 dijit.byId("unassignedRoleGridId").vtReload("UserAction!searchRoleOfUsers.do", "userRoleFormOnDialog", null, null);
             };

             // [ Search roles to assign for user
             page.searchUnassignedRole= function(){

                 // trim text on form
                 sd._("userRoleFormOnDialog.roleName").setValue(dojo.trim(dijit.byId("userRoleFormOnDialog.roleName").getValue()));
                 sd._("userRoleFormOnDialog.roleCode").setValue(dojo.trim(dijit.byId("userRoleFormOnDialog.roleCode").getValue()));
            
                 dijit.byId("unassignedRoleGridId").vtReload("UserAction!searchRole.do", "userRoleFormOnDialog", null, null);
             };
             // ]

             page.preAddRole = function (){
                 clearAddRoleDialog();
                 sd._("userRoleFormOnDialog.userId").setValue(dojo.trim(dijit.byId("usersForm.userId").getValue()));
                 var dialog = dijit.byId("unAssignedRoleDlg");
                 dialog.titleNode.innerHTML = "Bổ sung vai trò cho người dùng";
                 dialog.show();
                 dijit.byId("unassignedRoleGridId").vtReload("UserAction!searchRole.do", "userRoleFormOnDialog", null, null);
             };

             //******************************* Agree adding role ****************
             page.agreeToAddRole = function(){

                 if(!page.isChecked("unassignedRoleGridId")){
                     page.alert("Thông báo","<sd:Property>msg.unselect</sd:Property>", "warning");
                     return false;
                 }
        
                 var grid = dijit.byId("unassignedRoleGridId");
                 var item;
                 var userId = sd._("userRoleFormOnDialog.userId").getValue();
                 var roleId = "";
                 for(var j = 0; j < grid.store._arrayOfTopLevelItems.length;j++){
                     item = grid.store._arrayOfTopLevelItems[j];
                     if(item != undefined && item.checked == true){
                         if(roleId == ""){
                             roleId = item.roleId;
                         } else {
                             roleId = roleId +","+ item.roleId;
                         }
                     }
                 }

                 var afterCallback = function(response){
                     dijit.byId("unAssignedRoleDlg").hide();
                 };

                 msg.confirm('<sd:Property>msg.assignRoleConfirm</sd:Property>',"<sd:Property>confirm.title</sd:Property>", function() {
                     dijit.byId("roleGridId").vtReload("UserAction!assignRole.do?roleId="+roleId+"&userId="+userId+"&"+token.getTokenParamString(), "", "", afterCallback);
                 });

                 //page.confirmAsJS('<sd:Property>msg.assignRoleConfirm</sd:Property>', call);
             };

             // Remove roles of user
             page.onRemoveRole = function(){

                 if(!page.isChecked("roleGridId")){
                     page.alert("Thông báo","<sd:Property>msg.unselect</sd:Property>", "warning");
                     return false;
                 }

                 var grid = dijit.byId("roleGridId");
                 var item;
                 var userId = sd._("usersForm.userId").getValue();
                 var userName = sd._("usersForm2.userName").getValue();
                 var roleId = "0";
                 var roleUserDeptId ="0";
                 if (page.checkRight2(userId, userName)) {
                     var items = dijit.byId("roleGridId").vtGetCheckedItems();
                     for(var j = 0; j < items.length;j++){
                         item = items[j];
                         if(item != undefined && item.isActive != 0){
                             
                             if(roleId == ""){
                                 roleId = item.roleId;
                             } else {
                                 roleId = roleId +","+ item.roleId;
                             }
                             
                             if(item.roleUserDeptId  == null){
                             } else {
                                 if(roleUserDeptId == ""){
                                     roleUserDeptId = item.roleUserDeptId;
                                 } else {
                                     roleUserDeptId = roleUserDeptId +","+ item.roleUserDeptId;
                                 }
                                 
                             }
                             
                             
                         } else {
                             count++;
                         }
                     }
                     var afterCallback = function(response){
                         page.alert("Thông báo","<sd:Property>msg.removeRoleOk</sd:Property>", "warning");
                     }

                     msg.confirm('<sd:Property>msg.revokeRoleConfirm</sd:Property>',"<sd:Property>confirm.title</sd:Property>", function() {
                         dijit.byId("roleGridId").vtReload("UserAction!removeRole.do?roleId="+roleId+"&userId="+userId+"&roleUserDeptId="+roleUserDeptId+"&"+token.getTokenParamString(), "", "", afterCallback);
                     });
                 }

                 //page.confirmAsJS('<sd:Property>msg.revokeRoleConfirm</sd:Property>', call);

             };

             // lock & unlock role

             page.onLockRole = function(){
                 if(!page.isChecked("roleGridId")){
                     page.alert("Thông báo","<sd:Property>msg.unselect</sd:Property>", "warning");
                     return false;
                 }

                 var grid = dijit.byId("roleGridId");
                 var item;
                 var userId = sd._("usersForm.userId").getValue();
                 var userName = sd._("usersForm2.userName").getValue();
                 var roleId = "0";
                 var roleUserDeptId = "0";
                 var count = 0;
                 if (page.checkRight2(userId, userName)) {
                     var items = dijit.byId("roleGridId").vtGetCheckedItems();
                     for(var j = 0; j < items.length;j++){
                         item = items[j];
                         if(item != undefined && item.isActive != 0){
                             
                             if(roleId == ""){
                                 roleId = item.roleId;
                             } else {
                                 roleId = roleId +","+ item.roleId;
                             }
                             
                             if(item.roleUserDeptId  == null){
                             } else {
                                 if(roleUserDeptId == ""){
                                     roleUserDeptId = item.roleUserDeptId;
                                 } else {
                                     roleUserDeptId = roleUserDeptId +","+ item.roleUserDeptId;
                                 }
                                 
                             }
                             
                             
                         } else {
                             count++;
                         }
                     }

                     //                var afterCallback = function(response){
                     //                    page.alert("Thông báo","<sd:Property>msg.lockOk</sd:Property>", "info");
                     //                }

                     msg.confirm('<sd:Property>msg.lockRoleConfirm</sd:Property>',"<sd:Property>confirm.title</sd:Property>", function() {
                         sd.connector.post("UserAction!lockRole.do?ignore=" + count + "&roleId="+roleId+"&userId="+userId+"&roleUserDeptId="+roleUserDeptId+"&"+token.getTokenParamString(),
                         null, null, null, page.returnMessageUpdateStatus2);
                     });
                 }
                 //page.confirmAsJS('<sd:Property>msg.lockRoleConfirm</sd:Property>', call);
             };

             page.onUnLockRole = function(){
                 if(!page.isChecked("roleGridId")){
                     page.alert("Thông báo","<sd:Property>msg.unselect</sd:Property>", "warning");
                     return false;
                 }

                 var grid = dijit.byId("roleGridId");
                 var item;
                 var userId = sd._("usersForm.userId").getValue();
                 var userName = sd._("usersForm2.userName").getValue();
                 var roleId = "";
                 var roleUserDeptId = "0";
                 var count = 0;
                 if (page.checkRight2(userId, userName)) {
                     var items = dijit.byId("roleGridId").vtGetCheckedItems();
                     for(var j = 0; j < items.length;j++){
                         item = items[j];
                         if(item != undefined && item.isActive == 0){
                             if(roleId == ""){
                                 roleId = item.roleId;
                             } else {
                                 roleId = roleId +","+ item.roleId;
                             }
                             
                             if(item.roleUserDeptId  == null){
                             } else {
                                 if(roleUserDeptId == ""){
                                     roleUserDeptId = item.roleUserDeptId;
                                 } else {
                                     roleUserDeptId = roleUserDeptId +","+ item.roleUserDeptId;
                                 }
                                 
                             }
                             
                         } else {
                             count++;
                         }
                     }

                     //                var afterCallback = function(response){
                     //                    page.alert("Thông báo","<sd:Property>msg.unlockOk</sd:Property>", "info");
                     //                }

                     msg.confirm('<sd:Property>msg.unlockRoleConfirm</sd:Property>',"<sd:Property>confirm.title</sd:Property>", function() {
                         sd.connector.post("UserAction!unLockRole.do?ignore=" + count + "&roleId="+roleId+"&userId="+userId+"&roleUserDeptId="+roleUserDeptId+"&"+token.getTokenParamString(),
                         null, null, null, page.returnMessageUpdateStatus2);
                     });
                 }
                 //page.confirmAsJS('<sd:Property>msg.unlockRoleConfirm</sd:Property>', call);
             };
             
             page.returnMessageUpdateStatus2 = function(data){
                 var obj = JSON.parse(data);
                 var status = obj.customInfo;
                 var lst=obj.items;
                 document.getElementById("lblSuccessMessage2").innerHTML=lst[0];
                 document.getElementById("lblErrorMessage2").innerHTML=lst[1];
                 if(status.toString().indexOf('1') >= 0){
                     document.getElementById("tblSuccessMessage2").style.display='';
                 }else if(status.toString().indexOf('2') >= 0){
                     document.getElementById("tblErrorMessage2").style.display='';
                 }
                 else if(status.toString().indexOf('3') >= 0){
                     document.getElementById("tblErrorMessage2").style.display='';
                     document.getElementById("tblSuccessMessage2").style.display='';
                 }
                 var userId = sd._("usersForm.userId").getValue();
                 dijit.byId("roleGridId").vtReload("UserAction!searchRoleOfUsers.do?userId="+userId, "usersForm", null, null);
                 window.setTimeout( "hideMessage2()", 5000 );
             };

             hideMessage2 = function(){
                 document.getElementById("tblSuccessMessage2").style.display = 'none';
                 document.getElementById("tblErrorMessage2").style.display = 'none';
             };

             //########################################### ACTION ON DEPARTMENT OF USER ##########################
             // [ list dept of selected user
             page.showDeptsOfUser = function(inRow){
                 clientAction = "listDepts";
                 var row= dijit.byId("listUserGridId").getItem(inRow);

                 var afterCallback = function(response){
                     sd._("userDeptFormOnDialog.userId").setValue(row.userId);
                     sd._("userDeptForm.userId").setValue(row.userId);
                     sd._("lstUserDeptTitlePane").setTitle("<sd:Property>usersForm.DeptTitle</sd:Property>" + row.userName);
                 }
                 sd._("usersFormOnDialog.userId").setValue(row.userId);
                 sd._("usersFormOnDialog.userName").setValue(row.userName);
        
                 sd.connector.post("UserAction!actionListDept.do","listRoleOfUserDiv","usersFormOnDialog",null,afterCallback);
             };
             //]

             // [ Search dept to add user
             page.searchDept= function(){

                 // trim text on form
                 sd._("userDeptFormOnDialog.deptName").setValue(dojo.trim(dijit.byId("userDeptFormOnDialog.deptName").getValue()));
                 sd._("userDeptFormOnDialog.code").setValue(dojo.trim(dijit.byId("userDeptFormOnDialog.code").getValue()));
                 sd._("userDeptFormOnDialog.address").setValue(dojo.trim(dijit.byId("userDeptFormOnDialog.address").getValue()));
                 sd._("userDeptFormOnDialog.telephone").setValue(dojo.trim(dijit.byId("userDeptFormOnDialog.telephone").getValue()));

                 dijit.byId("userDeptGridId").vtReload("UserAction!searchDept.do", "userDeptFormOnDialog", null, null);
             };
             // ]

             page.preAddDept = function (){

                 dijit.byId("userDeptGridId").vtReload("", null);

                 var dialog = dijit.byId("dialogUserDeptId");
                 dialog.titleNode.innerHTML = "Chọn phòng ban";
                 dialog.show();
             };

             //******************************* Agree adding dept ****************
             page.agreeToAddDept = function(){

                 dijit.byId("userDeptGridId").edit.apply();

                 if(!page.isChecked("userDeptGridId")){
                     page.alert("Thông báo","<sd:Property>msg.unselect</sd:Property>", "warning");
                     return false;
                 }

                 if(!page.isChoosePos("userDeptGridId")){
                     page.alert("Thông báo", "<sd:Property>msg.unselectPosition</sd:Property>", "warning");
                     return false;
                 }

                 var recordsToAssign = dijit.byId("userDeptGridId").vtGetCheckedDataForPost("userDeptFormOnDialog", "isCheck");

                 var afterCallback = function(response){
                     dijit.byId("dialogUserDeptId").hide();
                 }

                 msg.confirm('<sd:Property>msg.addDeptConfirm</sd:Property>',"<sd:Property>confirm.title</sd:Property>", function() {
                     dijit.byId("DeptGridId").vtReload("UserAction!assignDept.do", "userDeptFormOnDialog", recordsToAssign, afterCallback);
                 });

                 //page.confirmAsJS('<sd:Property>msg.addDeptConfirm</sd:Property>', call);
             };

             page.isChoosePos = function(gridId){
                 var bReturn = true;
                 var grid = dijit.byId(gridId);
                 for (var idx in grid.store._arrayOfAllItems){
                     var item = grid.store._arrayOfAllItems[idx];
                     if (item["isCheck"][0] == "true" || item["isCheck"][0] ){
                         if(item.position == null || item.position == ""){
                             bReturn = false;
                             break;
                         }
                     }
                 }
                 return bReturn;
             };

             // Remove department
             page.onRemoveDept = function(){

                 if(!page.isChecked("DeptGridId")){
                     page.alert("Thông báo","<sd:Property>msg.unselect</sd:Property>", "warning");
                     return false;
                 }

                 var recordsToRemove = dijit.byId("DeptGridId").vtGetCheckedDataForPost("userDeptForm", "isCheck");

                 var afterCallback = function(response){
                     page.alert("Thông báo","<sd:Property>msg.removeDeptOk</sd:Property>", "info");
                 }

                 msg.confirm('<sd:Property>msg.removeDeptConfirm</sd:Property>',"<sd:Property>confirm.title</sd:Property>", function() {
                     dijit.byId("DeptGridId").vtReload("UserAction!removeDept.do", "userDeptForm", recordsToRemove, afterCallback);
                 });

                 //page.confirmAsJS('<sd:Property>msg.removeDeptConfirm</sd:Property>', call);

             };

             //########################################################
    
             page.onLock = function(){
                 if(!page.isChecked("listUserGridId")){
                     page.alert("Thông báo","<sd:Property>msg.unselect</sd:Property>", "warning");
                     return false;
                 }
                 var item;
                 var count = 0;
                 var userId = "";
                 var items = dijit.byId("listUserGridId").vtGetCheckedItems();
                 for(var j = 0; j < items.length;j++){
                     item = items[j];
                     if(item != undefined && item.status.toString() != "0"){
                         if (!page.checkRight(item)) {
                             return;
                         }
                         if(userId == ""){
                             userId = item.userId;
                         } else {
                             userId = userId +","+ item.userId;
                         }
                     } else {
                         count++;
                     }
                 }

                 //            var afterCallback = function(response){
                 //                page.returnMessageDelete(response);
                 //                page.onSearch();
                 //                page.alert("Thông báo","<sd:Property>msg.lockOk</sd:Property>", "info");
                 //            }

                 msg.confirm('<sd:Property>msg.lockUserConfirm</sd:Property>',"<sd:Property>confirm.title</sd:Property>", function() {
                     sd.connector.post("UserAction!onLock.do?ignore=" + count + "&userId="+userId+"&"+token.getTokenParamString(), null, null, null, page.returnMessageUpdateStatus);
                 });

                 //page.confirmAsJS('<sd:Property>msg.lockUserConfirm</sd:Property>', call);
             };

             page.onUnLock = function(){
                 if(!page.isChecked("listUserGridId")){
                     page.alert("Thông báo","<sd:Property>msg.unselect</sd:Property>", "warning");
                     return false;
                 }
                 var item;
                 var count = 0;
                 var userId = "";
                 var items = dijit.byId("listUserGridId").vtGetCheckedItems();
                 for(var j = 0; j < items.length;j++){
                     item = items[j];
                     
                     if(item != undefined && item.status.toString() == "0"){
                         if (!page.checkRight(item)) {
                             return;
                         }
                         if(userId == ""){
                             userId = item.userId;
                         } else {
                             userId = userId +","+ item.userId;
                         }
                     } else {
                         count++;
                     }
                 }

                 //            var afterCallback = function(response){
                 //                page.returnMessageUpdateStatus(response);
                 //                page.onSearch();
                 //                page.alert("Thông báo","<sd:Property>msg.unlockOk</sd:Property>", "info");
                 //            }

                 msg.confirm('<sd:Property>msg.unlockUserConfirm</sd:Property>',"<sd:Property>confirm.title</sd:Property>", function() {
                     sd.connector.post("UserAction!onUnLock.do?ignore=" + count + "&userId="+userId+"&"+token.getTokenParamString(), null, null, null, page.returnMessageUpdateStatus);
                 });

                 //page.confirmAsJS('<sd:Property>msg.unlockUserConfirm</sd:Property>', call);
        
             };

             page.returnMessageUpdateStatus = function(data){
                 var obj = JSON.parse(data);
                 var status = obj.customInfo;
                 var lst=obj.items;
                 document.getElementById("lblSuccessMessage").innerHTML=lst[0];
                 document.getElementById("lblErrorMessage").innerHTML=lst[1];
                 if(status.toString().indexOf('1') >= 0){
                     document.getElementById("tblSuccessMessage").style.display='';
                 }else if(status.toString().indexOf('2') >= 0){
                     document.getElementById("tblErrorMessage").style.display='';
                 }
                 else if(status.toString().indexOf('3') >= 0){
                     document.getElementById("tblErrorMessage").style.display='';
                     document.getElementById("tblSuccessMessage").style.display='';
                 }
                 page.onSearch();
                 window.setTimeout( "hideMessage()", 5000 );
             };


             page.getString = function( obj ) {
                 var ret = "";
                 if( obj.constructor == String ) {
                     ret = obj;
                 }
                 else if( obj.constructor == Array ) {
                     ret = obj.join( "" );
                 }

                 return sd.validator.trim( ret );
             };

             page.convertStringToDate = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dgDate){
                 try{
                     var dateStr = page.getString( dgDate );
                     var temp = dateStr.split("-");
                     return new Date(temp[1]+"/"+temp[2].split("T")[0]+"/"+temp[0]);
                 }catch(e){
                     page.alert("Thông báo","function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss", "warning");
                     return undefined;
                 }
             };

             page.onExcel=function(){
                 var temp = "";
                 if (dijit.byId('usersForm.userName').value != ""){
                     temp += "&userName=";
                     temp += dijit.byId('usersForm.userName').value;
                 }
                 if (dijit.byId('usersForm.fullName').value != ""){
                     temp += "&fullName=";
                     temp += dijit.byId('usersForm.fullName').value;
                 }
                 if (dijit.byId('usersForm.type').value != ""){
                     temp += "&type=";
                     temp += dijit.byId('usersForm.type').value;
                 }
                 if (dijit.byId('usersForm.cellphone').value != ""){
                     temp += "&cellphone=";
                     temp += dijit.byId('usersForm.cellphone').value;
                 }
                 if (dijit.byId('usersForm.status').value != ""){
                     temp += "&status=";
                     temp += dijit.byId('usersForm.status').value;
                 }
                 if (dijit.byId('usersForm.deptId').value != ""){
                     temp += "&deptId=";
                     temp += dijit.byId('usersForm.deptId').value;
                 }
                 window.location = "UserAction!onExcel.do?ext=ext" + temp;
             };
             //[congdh 30/09/2010
             page.convertStringFromVietnameseToEnglish =function(str) {
                 str= str.toString().toLowerCase();
                 str= str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g,"a");
                 str= str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g,"e");
                 str= str.replace(/ì|í|ị|ỉ|ĩ/g,"i");
                 str= str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g,"o");
                 str= str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g,"u");
                 str= str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g,"y");
                 str= str.replace(/đ/g,"d");
                 //str= str.replace(/!|@|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\;|\'| |\"|\&|\#|\[|\]|~|$|_/g,"-");
                 /* tìm và thay thế các kí tự đặc biệt trong chuỗi sang kí tự - */
                 //str= str.replace(/-+-/g,"-"); //thay thế 2- thành 1-
                 //str= str.replace(/^\-+|\-+$/g,"");
                 //cắt bỏ ký tự - ở đầu và cuối chuỗi
                 return str;
             };
             //congdh 30/09/2010]
                    
             //[congdh 04/10/2010
             // Kiem tra chuoi dia chi IP dung dinh dang hay khong
             page.validateIp = function(ip){
                 ip = ip.toString();
                 var arr = ip.split("|");
                 for (var i=0; i<arr.length; i++){
                     var ipAddress = arr[i];
                     var octets = ipAddress.split(".");
                     if (octets.length != 4){
                         return false;
                     }
                     if (!page.isValidOctet(octets[0])
                         ||!page.isValidOctet(octets[1])
                         ||!page.isValidOctet(octets[2])                    ){
                         return false;
                     }
                     var octet = octets[3];

                     if (octet != "*"){
                         if (octet.charAt(0) == "[" && octet.charAt(octet.length - 1) == "]"){
                             octet = octet.substr(1, octet.length - 2);
                             var index = octet.indexOf("-");
                             if (index >= 0){
                                 var nums = octet.split("-");
                                 if (nums.length != 2){
                                     return false;
                                 }
                                 if (!page.isValidOctet(nums[0]) || !page.isValidOctet(nums[1])){
                                     return false;
                                 }
                                 if (parseInt(nums[0]) >= parseInt(nums[1])){
                                     return false;
                                 }
                             }else{
                                 var nums = octet.split(",");
                                 if (nums.length < 2){
                                     return false;
                                 }
                                 for (var k = 0; k < nums.length; k++){
                                     if (!page.isValidOctet(nums[k])){
                                         return false;
                                     }
                                 }
                             }
                         }else if (!page.isValidOctet(octet)){
                             return false;
                         }
                     }
                 }
                 return true;
             };

             page.isValidOctet = function(octet){
                 try {
                     octet = octet.toString();
                     var regex3 = /^[\d]{1,3}$/;

                     if( !regex3.test(octet) ) {
                         return false;
                     }

                     if (octet.toString().length > 1 && octet.charAt(0) == "0"){
                         return false;}

                     var num = parseInt( octet );
                     if( num != null && num != undefined && num != NaN ) {
                         if( num >= 0 && num <= 255 ) return true;
                         return false;
                     }
                     return false;
                 }
                 catch( e ) {
                     alert( "MSG: " + e.message );
                 }
             };
    
             page.checkIdentityCard = function(funcStr) {
                 var func = eval(funcStr);
                 var result = true;
                 sd.connector.post("UserAction!checkIdentityCard.do", null, "usersFormOnDialog", null, function(data) {
                     var obj = JSON.parse(data);
                     var status = obj.customInfo;
                     if (status == "exist") {
                         result = false;
                         msg.alert("Số CMT đã tồn tại.");
                         focus("usersFormOnDialog.identityCard");
                     } else {
                         func();
                     }
                 });
                 return result;
             };
             page.checkRight = function(userItem) {
                 var currentUserId = '${fn:escapeXml(currentUserId)}';
                 var grid = dijit.byId("listUserGridId");
                 var rowIndex = grid.getItemIndex(userItem) + 1;
                 var result = true;
                 if (currentUserId == userItem.userId) {
                     result = false;
                     alert("Không thể thao tác dữ liệu người dùng đang đăng nhập\n Dòng " + rowIndex);
                     grid.focus.setFocusIndex(rowIndex -1,1);
                 } else if (userItem.userName == "admin") {
                     result = false;
                     alert("Không thể thao tác dữ liệu người dùng admin\n Dòng " + rowIndex);
                     grid.focus.setFocusIndex(rowIndex-1,1);
                 }
                 return result;
             };
    
             page.checkRight2 = function(userId, userName) {
                 var currentUserId = '${fn:escapeXml(currentUserId)}';
                 var result = true;
                 if (currentUserId == userId) {
                     result = false;
                     alert("Không thể thao tác dữ liệu người dùng đang đăng nhập");
                 } else if (userName == "admin") {
                     result = false;
                     alert("Không thể thao tác dữ liệu người dùng admin");
                 }
                 return result;
             };
             clearUserDialog = function() {
                 clearForm("usersFormOnDialog");
                 //dijit.byId("usersFormOnDialog.type").setSelectedIndex(0);
                 dijit.byId("usersFormOnDialog.status").setSelectedIndex(0);
                 dijit.byId("usersFormOnDialog.posId").setSelectedIndex(0);
                 dijit.byId("usersFormOnDialog.gender").setSelectedIndex(0);
                 dijit.byId("usersFormOnDialog.businessId").setSelectedIndex(0);
                 
                 dijit.byId("usersFormOnDialog.description").setValue("");
                 dijit.byId("usersFormOnDialog.lastResetPassword").setValue("");
                 dijit.byId("usersFormOnDialog.lastLogin").setValue("");
                 dijit.byId("usersFormOnDialog.lastChangePassword").setValue("");
                 dijit.byId("usersFormOnDialog.userName").focus();
                 
             };
             clearAddRoleDialog = function() {
                 clearForm("userRoleFormOnDialog");
                 dijit.byId("userRoleFormOnDialog.status").setValue("ALL");
             };
             clearStudentDialog = function() {
                 clearForm("studentForm");
                 dijit.byId("studentForm.gender").setValue(2);
                 dijit.byId("studentForm.systemId").setValue(-1);
                 dijit.byId("studentForm.course").setValue(-1);
                 dijit.byId("studentForm.class").setValue(-1);
                 dijit.byId("studentForm.officerLevelId").setValue(-1);
             };
             clearStaffDialog = function() {
                 clearForm("userRoleFormOnDialog");
                 dijit.byId("staffForm.gender").setValue(-1);
             };
             page.importUsers = function (){
                 //clearAddRoleDialog();
                 //sd._("userRoleFormOnDialog.userId").setValue(dojo.trim(dijit.byId("usersForm.userId").getValue()));
                 var dialog = dijit.byId("importUser");
//                 /dialog.titleNode.innerHTML = "Bổ sung vai trò cho người dùng";
                 dialog.show();
                 //dijit.byId("unassignedRoleGridId").vtReload("UserAction!searchRole.do", "userRoleFormOnDialog", null, null);
             };
</script>