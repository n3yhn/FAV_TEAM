<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>



<div>
    <table style="width: 98%">
        <tr id="trWaitViewFile">
            <td colspan="3" style="text-align: center;alignment-adjust: middle">
                <label id="labelWaitLoadFile" style="color: red">Vui lòng chờ  </label>
                <img src="/share/images/loading/loading2.gif" width="20px" height="20px">
            </td>
        </tr>
        <tr>
            <td style="width: 60%">
                <sd:TitlePane key="Thông tin hồ sơ" id="titlePaneViewFile_AFP">
                    <div id="divViewFile" style="overflow-y: auto;max-height: 600px"></div>
                </sd:TitlePane>
            </td>
            <td style="width: 38%">
                <sd:TitlePane key="Phê duyệt hồ sơ" id="titlePaneEvaluate_AFP">
                    <div style="overflow-y: auto;max-height: 600px">
                        <form id="businessSignForm" name="createForm">
                            <table width="100%" class="viewTable">
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Tên tổ chức, cá nhân"/></td>
                                    <td width="70%">
                                        <div id="businessSignForm.businessName" style="font-weight: bold"></div>
                                    </td>            
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Tên sản phẩm"/></td>
                                    <td width="70%">
                                        <sd:TextBox key="" 
                                                    id="businessSignForm.fileId" 
                                                    name="createForm.fileId" 
                                                    cssStyle="display:none"/>
                                        <div id="businessSignForm.productName" style="font-weight: bold">

                                        </div>
                                    </td>            
                                </tr>
                                <tr id="trWait" style="display: none">
                                    <td colspan="2" style="text-align: center;alignment-adjust: middle">
                                        <label id="labelWait" style="color: red">Vui lòng chờ  </label>
                                        <img src="/share/images/loading/loading2.gif" width="20px" height="20px">
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="2" style="text-align: center;alignment-adjust: middle">
                                        <div id="divSignProcess" style="display: none;text-align: center;alignment-adjust: middle">
                                            <table>
                                                <tr>
                                                    <td>
                                                        <div id="divProcess" style="color:red;font-weight: bold;margin-left: 10px;margin-top: 3px"></div>
                                                    </td>
                                                    <td>
                                                        <img src="/share/images/loading/loading2.gif" width="20px" height="20px" >
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </sd:TitlePane>
                </div>
            </td>
        </tr>        
    </table>
</div>


<jsp:include page="pluginJSBSF.jsp" flush="false"></jsp:include>
    <script type="text/javascript">

        var signType;
        var passwordCa;
        var flagSign;
        var cert;
        var count = 0;       

        afterOnUpdateFileOnDb = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            
            if (result[0] == "1") {
                if (signIndex == itemsToSign.length - 1) {
                    msg.alert('Ký số thành công', 'Thông báo');
                    document.getElementById("divSignProcess").style.display = "none";
                    document.getElementById("trWait").style.display = "none";
                    count = 0;
                    onCloseBusinessSignFormDlg();
                    msg.alert('Ký số thành công', 'Thông báo');
                    page.search();
                } else {
                    signIndex++;
                    page.signFileUsingPlugin();
                }
            } else {
                msg.alert('Ký số không thành công', 'Lỗi hệ thống');
                onCloseBusinessSignFormDlg();
                document.getElementById("divSignProcess").style.display = "none";
                document.getElementById("trWait").style.display = "none";
                count = 0;
                page.search();
            }
            
        };

        page.utf8_to_b64AFCV = function (str) {
            return window.btoa(unescape(encodeURIComponent(str)));
        };

        String.prototype.replaceAllAFCV = function (strTarget, strSubString) {
            var strText = this;
            var intIndexOfMatch = strText.indexOf(strTarget);
            while (intIndexOfMatch != -1) {
                strText = strText.replace(strTarget, strSubString);
                intIndexOfMatch = strText.indexOf(strTarget);
            }
            return(strText);
        };



        page.afterApprovetoCT = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            alert(result[1]);
            if (result[0] == "1") {
                document.getElementById("trWait").style.display = "none";
                onCloseBusinessSignFormDlg();
                page.search();
            }
        };

        onCloseApprove = function () {
            dijit.byId("approveDlg").hide();
            document.getElementById("trWait").style.display = "none";
        };


        //hieptq update 121015
        page.onApproveSignPlugin = function () {
            signType = "CBDN";
            document.getElementById("trWait").style.display = "";
            document.getElementById("labelWait").innerHTML = "Hệ thống đang tạo bản công bố, vui lòng chờ  ";
            document.getElementById("labelWait").value = "Hệ thống đang thực hiện ký và tải bản công bố, vui lòng chờ! ";
            var fileId = dijit.byId("businessSignForm.fileId").getValue();
            sd.connector.post("exportWord!onExportPaperSignFilePlugin.do?fileId=" + fileId, null, null, null, page.afterOnApproveSignPlugin);
        };

        afteronCreatePaperPdf = function (data) {
        };

        function sleep(milliseconds) {
            var start = new Date().getTime();
            for (var i = 0; i < 1e7; i++) {
                if ((new Date().getTime() - start) > milliseconds) {
                    break;
                }
            }
        }
        ;


        page.afterOnApproveSignPlugin = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                var fileId = dijit.byId("businessSignForm.fileId").getValue();
                if (count == 0) {
                    var item = uploadCertOfFile(fileId);
                    cert = encodeBase64(item.certChain);
                }
                var path = result[2];
                sd.connector.post("filesAction!actionSignCAFile.do?fileId=" + fileId
                        + "&cert=" + cert
                        + "&signType=" + signType
                        + "&path=" + path, null, null, null, page.signPluginAFP);
                count++;
            } else {
                alert(result[1]);
                document.getElementById("trWait").style.display = "none";
            }
        };


        page.signPluginAFP = function (data)
        {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                var txtBase64HashNew = result[2];
                var certSerialNew = result[3];
                var fileId = result[4];
                var outPutPath = result[5];
                var fileName = result[6];
                dijit.byId("txtBase64HashAFP").setValue(txtBase64HashNew);
                dijit.byId("txtCertSerialAFP").setValue(certSerialNew);
                var sign = signAndSubmit();
                var signData = encodeBase64(sign);
                sd.connector.post("filesAction!onSignFileUsingPlugin.do?fileId=" + fileId
                        + "&outPutPath=" + outPutPath
                        + "&signData=" + signData
                        + "&signType=" + signType
                        + "&fileName=" + fileName, null, null, null, page.afterSignPluginAFP);
            } else
            {
                alert("Ký số không thành công ! " + result[1]);
                onCloseBusinessSignFormDlg();
            }
        };

        page.afterSignPluginAFP = function (data)
        {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                onUpdateFileOnDb();
            } else
            {
                alert("Ký số không thành công !");
                onCloseBusinessSignFormDlg();
            }
        };
        onUpdateFileOnDb = function () {
            sd.connector.post("filesAction!onUpdateIsSignFile.do?" + token.getTokenParamString(), null, "businessSignForm", null, afterOnUpdateFileOnDb);
        };
        function deleteAllCookies() {
            var cookies = document.cookie.split(";");
            for (var i = 0; i < cookies.length; i++) {
                var cookie = cookies[i];
                var eqPos = cookie.indexOf("=");
                var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
                document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
            }
        }
        deleteAllCookies();
    </script>

    <input type="hidden" id="base64Hash" value="" />
<sd:TextBox id="txtBase64HashAFP" key="" name="txtBase64Hash" type="hidden"/>
<input type="hidden" id="certSerial" value="" />
<sd:TextBox id="txtCertSerialAFP" key="" name="txtCertSerial" type="hidden"/>
<object id="plugin0" type="application/x-viettelcasigner" width="3" height="10">
</object>
