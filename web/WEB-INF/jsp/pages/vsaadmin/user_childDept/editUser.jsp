
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<jsp:include page="/WEB-INF/jsp/pages/config/alertDialog.jsp"/>

<link rel="stylesheet" href="${contextPath}/share/css/formStyle.css" charset="UTF-8" type="text/css" />
<link rel="stylesheet" href="${contextPath}/share/css/layout.css" charset="UTF-8" type="text/css" />




<div id="message" align="center">

</div>


<s:if test="#request.display == 1">
    <form id="usersFormOnDialogSearch" name="usersFormSearch">
        <sd:FieldSet key="btnSearch">
            <table width="100%">
                <tr>
                    <td class="tdOnForm">
                        <sd:TextBox id="usersFormOnDialog.userNameSearch" name="usersFormSearch.userName" key="usersFormOnDialog.userName" cssStyle="width:80%" />
                    </td>
                    <td class="tdOnForm">
                        <sd:Button id="usersFormOnDialog.btnSearch" key="btnSearch" onclick="page.onSearchToEdit()"/>
                    </td>
                </tr>
            </table>
        </sd:FieldSet>
    </form>
    <br/>
    <br/>
    <br/>
</s:if>

<center>
    <table width="100%">
        <tr>
            <td class="tdOnForm" align="center">
                <sd:Button id="usersFormOnDialog.btnUpdate" key="btnUpdate" onclick="page.onUpdate()"/>
            </td>
        </tr>
    </table>
</center>
<div id="res">
    <form id="usersFormOnDialog" name="usersForm">
        <sd:FieldSet key="usersForm.info">
            <table width="100%">
                <tr>
                    <td class="tdOnForm">
                        <sd:TextBox id="usersFormOnDialog.userName" name="usersForm.userName" key="usersFormOnDialog.userName" cssStyle="width:80%" required ="true" maxlength="100" readonly="true" />
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox id="usersFormOnDialog.fullName" name="usersForm.fullName" key="usersFormOnDialog.fullName" cssStyle="width:80%" required="true" maxlength="180"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:TextBox id="usersFormOnDialog.email" name="usersForm.email" key="usersFormOnDialog.email" cssStyle="width:80%" maxlength="50" required="true" />
                    </td>

                    <td class="tdOnForm">
                        <sd:TextBox id="usersFormOnDialog.cellphone" name="usersForm.cellphone" key="usersFormOnDialog.cellphone" cssStyle="width:80%" mask="^\d*$" maxlength="20" required="true" />
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:TextBox id="usersFormOnDialog.aliasName" name="usersForm.aliasName" key="usersForm.aliasName" cssStyle="width:80%" maxlength="50" />
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox id="usersFormOnDialog.birthPlace" name="usersForm.birthPlace" key="usersForm.birthPlace" cssStyle="width:80%" maxlength="100" />
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:TextBox id="usersFormOnDialog.telephone" name="usersForm.telephone" key="usersForm.telephone" mask="^\d*$" cssStyle="width:80%" maxlength="20"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox id="usersFormOnDialog.fax" name="usersForm.fax" key="usersForm.fax" cssStyle="width:80%" maxlength="20"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:TextBox id="usersFormOnDialog.staffCode" name="usersForm.staffCode" key="usersForm.staffCode" cssStyle="width:80%" maxlength="50"/>
                    </td>

                    <td class="tdOnForm">
                        <sd:TextBox id="usersFormOnDialog.identityCard" name="usersForm.identityCard" key="usersForm.identityCard" cssStyle="width:80%" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:TextBox id="usersFormOnDialog.issuePlaceIdent" name="usersForm.issuePlaceIdent" key="usersForm.issuePlaceIdent" cssStyle="width:80%" maxlength="100"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox id="usersFormOnDialog.description" name="usersForm.description" key="usersForm.description" cssStyle="width:81%" maxlength="100"/>
                    </td>
                </tr>
                <tr>
                    <td style="display:none;" class="tdOnForm" colspan="2">
                        <sd:TextBox id="usersFormOnDialog.userId" name="usersForm.userId" key="" />
                    </td>
                </tr>
                <%-- [congdh 25/10
                    <tr>
                    <td class="tdOnForm">
                        <sd:TextBox id="usersFormOnDialog.ip" name="usersForm.ip" key="ip" cssStyle="width:81%" maxlength="1000" required="true"/>
                    </td>
                    <td style="color:#0066cc;" class="tdOnForm">
                        <sd:TreePicker id="deptTree" getChildrenNodeUrl="UserAction!getChildrenDataByNode.do" getTopNodeUrl="UserAction!getData.do"  key="usersForm.department" rootLabel="root" cssStyle="width:80%" required="true"/>
                        <div style="display:none"><input type="text" id="usersFormOnDialog.deptId" name="usersForm.deptId"/></div>
                    </td>
                </tr>
                congdh 25/10]--%>
                <tr>
                    <%-- [congdh 25/10
                    <td class="tdOnForm">
                        <sd:SelectBox id="usersFormOnDialog.posId" name="usersForm.posId" labelField="posName" valueField="posId" key="positionForm.posName" data="positionList" cssStyle="width:80%" required="true"/>
                    </td>
                    congdh 25/10]--%>
                    <td class="tdOnForm">
                        <sd:TextBox id="retypePassword" name="usersForm.password" key="passwordVerify" cssStyle="width:80%" type="password" required="true"/>
                    </td>
                </tr>
            </table>
        </sd:FieldSet>
    </form>
</div>

<br/>
<!--<div align="left"><i><font color="red" size="4">Lưu ý: Địa chỉ IP cần nhập là địa chỉ Wan của máy tính</font></i></div>-->
<script>
    <%--page.searchDefaultRole = function(evt){
                                    var dk = dojo.keys;
                                    switch(evt.keyCode){
                                        case dk.ENTER:
                                            page.onSearchToEdit();
                                            break;
                                    }
                                }

    dojo.connect(dojo.byId("usersFormOnDialogSearch"),"onkeypress",page.searchDefaultRole);--%>

        page.onUpdate = function (){

            if(!dijit.byId("usersFormOnDialog.userName").getValue()){
                page.alert("Thông báo","Bạn chưa nhập tên đăng nhập", "warning");
                dijit.byId("usersFormOnDialog.fullName").focus();
                return false;
            }

            if(!dijit.byId("usersFormOnDialog.fullName").getValue()){
                //isValidate = false;
                page.alert("Thông báo","<sd:Property>msg.user.fullNameNull</sd:Property>", "warning");
                dijit.byId("usersFormOnDialog.fullName").focus();
                return false;
            }

            if(!dijit.byId("usersFormOnDialog.email").getValue()){
                //isValidate = false;
                page.alert("Thông báo","<sd:Property>msg.user.emailNull</sd:Property>", "warning");
                dijit.byId("usersFormOnDialog.email").focus();
                return false;
            }else{
                if(!sd.validator.isEmail(dijit.byId("usersFormOnDialog.email").getValue())){
                    page.alert("Thông báo","Địa chỉ Email không đúng", "warning");
                    dijit.byId("usersFormOnDialog.email").focus();
                    return false;
                }


            }

            if(!dijit.byId("usersFormOnDialog.cellphone").getValue()){
                //isValidate = false;
                page.alert("Thông báo",'<sd:Property>msg.user.cellPhoneNull</sd:Property>', "warning");
                dijit.byId("usersFormOnDialog.cellphone").focus();
                return false;
            }else if(!sd.validator.isIntegerNumber(dijit.byId("usersFormOnDialog.cellphone").getValue()))
            {
                page.alert("Thông báo",'Số di động nhập chưa đúng định dạng', "warning");
                dijit.byId("usersFormOnDialog.cellphone").focus();
                return false;
            }
            if(!dijit.byId("usersFormOnDialog.identityCard").getValue()){
                alert('Bạn chưa nhập CMT cho người dùng.');
                dijit.byId("usersFormOnDialog.identityCard").focus();
                return false;
            }

            <%-- [congdh 25/10
            if(!dijit.byId("usersFormOnDialog.ip").getValue()){
                alert('Bạn chưa nhập ip cho người dùng.');
                dijit.byId("usersFormOnDialog.ip").focus();
                return false;
            }

            

            var temp = "" + dojo.byId('usersFormOnDialog.deptId').value;
            if (temp == ""){
                alert('Bạn chưa chọn phòng ban!');
                return false;
            }

            if (temp == "undefined"){
                alert('Bạn chưa chọn phòng ban!');
                return false;
            }

            temp = "" + dijit.byId('usersFormOnDialog.posId').value;
            if (temp == ""){
                alert('Bạn chưa chọn chức danh!');
                return false;
            }

            if (temp == "0"){
                alert('Bạn chưa chọn chức danh!');
                return false;
            }

            congdh 25/10]--%>
            //[congdh
            temp = dijit.byId('usersFormOnDialog.staffCode').value;
            if (temp != undefined && temp != null && temp.toString().length > 0 && temp.toString().length != 6){
                alert('Mã nhân viên phải gồm 6 chữ số.');
                dijit.byId("usersFormOnDialog.staffCode").focus();
                return false;
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

            var identityCardRegex=/^[a-zA-Z0-9]*$/;
            temp = dijit.byId('usersFormOnDialog.identityCard').value;
            if (temp != undefined && temp != null && temp.toString().length > 0){
                if (!identityCardRegex.test(temp)){
                    alert('Số CMT chỉ được nhập chữ cái và chữ số');
                    dijit.byId("usersFormOnDialog.identityCard").focus();
                    return false;
                }
                while (temp.toString().length < 9){
                    temp = "0" + temp;
                }
                dijit.byId('usersFormOnDialog.identityCard').setValue(temp);
            }

            <%-- [congdh 25/10
            var ipRegex=/^[0-9\|\*\[\],.-]*$/;
            temp = dijit.byId('usersFormOnDialog.ip').value;
            if (temp != undefined && temp != null && temp.toString().length > 0){
                if (!ipRegex.test(temp)){
                    alert('Địa chỉ IP chỉ được nhập chữ số và các kí tự ,.|[]*-');
                    dijit.byId("usersFormOnDialog.ip").focus();
                    return false;
                }
            }
            
            if (!page.validateIp(temp.toString())){
                alert('Địa chỉ IP không đúng định dạng');
                dijit.byId("usersFormOnDialog.ip").focus();
                return false;
            }
            congdh 25/10]--%>
            //congdh]

            sd.connector.post("EditUserAction!editUserInfo.do",null,"usersFormOnDialog",null,page.afterUpdate);
        }
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
        }

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
        }
        //congdh 04/10/2010]

        page.onSearchToEdit = function (){
            var temp = "" + dijit.byId('usersFormOnDialog.userNameSearch').value;
            if (temp == ""){
                alert('Bạn chưa nhập chuỗi tìm kiếm');
                return false;
            }
            sd.connector.post("EditUserAction!onSearchToEdit.do","res","usersFormOnDialogSearch");
        }
        page.afterUpdate = function (data){
            document.getElementById("message").innerHTML = "<font color=\"red\" size=\"4\">" + data + "</font>";
        }
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
        }
</script>

