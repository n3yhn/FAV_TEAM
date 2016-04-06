<%-- 
    Document   : bookAddEdit
    Created on : May 10, 2012, 9:47:53 AM
    Author     : sytv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<form id="loginCAForm" name="loginCAForm">
    <div style="background: url(../share/images/CAlogo.png) no-repeat;height: 90px;width: 100%"></div>
    <table width="100%" class="viewTable" id="tableLoginCAForm">
        
        <tr>
            <td width="25%" style="height: 15px;border: 0px !important">
            </td>
            <td width="75%" style="height: 15px;border: 0px !important">
            </td>
        </tr>
        <tr>
            <td colspan="2" style="border: 0px !important">
                <label><b>ĐĂNG NHẬP USB TOKEN</b></label>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="height: 15px;border: 0px !important">
            </td>
        </tr>
        <%--
        <tr>
            <td style="text-align: left;border: 0px !important"><sd:Label key="Tên truy cập"/></td>
            <td style="border: 0px !important">
                <sd:TextBox key="" id="loginCAForm.fullName" name="loginCAForm.fullName" value="" disabled="true"/>
            </td>            
        </tr>
        --%>
        <tr>
            <td style="text-align: left;border: 0px !important"><sd:Label key="Mật khẩu"/></td>
            <td style="border: 0px !important">
                <sd:TextBox key="" id="loginCAForm.password" name="loginCAForm.password" type="password" trim="true" />
            </td>            
        </tr>

        <tr>
            <td colspan="2" style="height: 15px;border: 0px !important">
            </td>
        </tr>

        <tr>
            <td style="border: 0px !important"></td>
            <td style="text-align: left;border: 0px !important">
                <sd:Button id="btnLogin" key="" onclick="page.loginUsbCa()">
                    <img src="share/images/usb.png" height="18" width="18"/>
                    <span style="font-size:12px">Đăng nhập</span>
                </sd:Button>
                <sd:Button id="btnCancel" key="" onclick="page.Close()">
                    <img src="share/images/Close.png" height="18" width="18"/>
                    <span style="font-size:12px">Hủy</span>
                </sd:Button>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="height: 15px;border: 0px !important">
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    
    page.loginUsbCa = function () {
        if (validateLoginForm()) {
            sd.connector.post("filesAction!loginUsbCA.do?" + token.getTokenParamString(), null, "loginCAForm", null, afterReviewFormSave);
        }
    }

    validateLoginForm = function () {
        var password = dijit.byId("loginCAForm.password").getValue();
        if (password == "") {
            msg.alert("Bạn chưa nhập mật khẩu", "Cảnh báo");
            dijit.byId("loginCAForm.password").focus();
            return false;
        }
        return true;
    };

    afterReviewFormSave = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result[0] == "1") {
            passwordCa = result[1];
            if (flagSign == "PDHS") {
                page.onApproveSignAffterLogin();
            }
            else if (flagSign == "CVBS") {
                page.onApproveSignSdbsAffterLogin();
            }
            page.Close();
        }
        else
        {
            msg.alert("Đăng nhập không thành công", "Cảnh báo");
        }
    };

    page.Close = function () {
        //flagEnterDlg = false;
        dijit.byId("loginCADlg").hide();
    }
    
</script>