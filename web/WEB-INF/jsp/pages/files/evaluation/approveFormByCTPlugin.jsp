<%-- 
    Document   : approveFormByCT
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<object id="plugin0" type="application/x-viettelcasigner" width="3" height="10">
</object>

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
                <sd:TitlePane key="Thông tin hồ sơ" id="titlePaneViewFile_CT">
                    <div id="divViewFile" style="overflow-y: auto;max-height: 600px"></div>
                </sd:TitlePane>
            </td>
            <td style="width: 38%">
                <sd:TitlePane key="Phê duyệt hồ sơ" id="titlePaneEvaluate_CT">
                    <div style="overflow-y: auto;max-height: 600px">
                        <form id="approveFormByCT" name="createForm">
                            <table width="100%" class="viewTable">
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Tên tổ chức, cá nhân"/></td>
                                    <td width="70%">
                                        <div id="approveFormByCT.businessName" style="font-weight: bold"></div>
                                    </td>            
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Tên sản phẩm"/></td>
                                    <td width="70%">
                                        <div id="approveFormByCT.productName" style="font-weight: bold"></div>
                                    </td>            
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Nội dung trình cục trưởng"/></td>
                                    <td width="70%">
                                        <div id="approveFormByCT.leaderRequest"></div>
                                    </td>            
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Kết quả phê duyệt"/></td>
                                    <td width="70%">
                                        <sd:TextBox key="" id="approveFormByCT.fileId" name="createForm.fileId" cssStyle="display:none"/>
                                        <input type="radio" id="approveFormByCT.statusAccept" name="createForm.status" value="6" checked="true"/>
                                        <sd:Label key="Duyệt: Hồ sơ đạt"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Nội dung  phê duyệt, hoặc yêu cầu xem xét lại"/></td>
                                    <td>
                                        <sd:Textarea key="" 
                                                     id="approveFormByCT.leaderRequest" 
                                                     name="createForm.leaderRequest" 
                                                     rows="4" 
                                                     cssStyle="width:99%" 
                                                     maxlength="1800" 
                                                     trim="true"/>
                                    </td>
                                </tr>
                                <tr id="trWait" style="display: none">
                                    <td colspan="2" style="text-align: center;alignment-adjust: middle">
                                        <label id="labelWait" style="color: red">Vui lòng chờ  </label>
                                        <img src="/share/images/loading/loading2.gif" width="20px" height="20px">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="text-align: center">
                                        <sd:Button 
                                            id="btnStatusAcceptAF" 
                                            key="" 
                                            onclick="page.onApproveSign();" 
                                            cssStyle="display:" 
                                            cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Cục trưởng Phê duyệt hồ sơ</span>
                                        </sd:Button>
                                        <%--<sx:ButtonClose onclick="onCloseApprove();"/>--%>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </sd:TitlePane>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="3" style="text-align: center">
                <br/>
            </td>
        </tr>
        <tr>
            <td colspan="3" style="text-align: center">
                <sx:ButtonClose onclick="onCloseApprove();"/>
            </td>
        </tr>
    </table>
</div>
<jsp:include page="pluginJSCT.jsp" flush="false"></jsp:include>
    <script type="text/javascript">
        afterApprove = function(data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            alert(result[1]);
            if (result[0] == "1") {
                document.getElementById("trWait").style.display = "none";
                page.search();
                onCloseApprove();
            }
        };

        onApprove = function() {
            sd.connector.post("filesAction!onApprove.do?" + token.getTokenParamString(), null, "approveFormByCT", null, afterApprove);
        };

        onCloseApprove = function() {
            count = 0;
            dijit.byId("approveDlg").hide();
            dijit.byId("approveFormByCT.leaderRequest").setValue("");
            document.getElementById("approveFormByCT.statusAccept").checked = false;
            document.getElementById("trWait").style.display = "none";
            var titlePaneViewFile_CT = dijit.byId('titlePaneViewFile_CT');
            if (titlePaneViewFile_CT) {
                titlePaneViewFile_CT.destroyRecursive(true);
            }
            var titlePaneEvaluate_CT = dijit.byId('titlePaneEvaluate_CT');
            if (titlePaneEvaluate_CT) {
                titlePaneEvaluate_CT.destroyRecursive(true);
            }
            var txtBase64HashCTCheck = dijit.byId('txtBase64HashCT');
            if (txtBase64HashCTCheck) {
                txtBase64HashCTCheck.destroyRecursive(true);
            }
            var txtCertSerialCTCheck = dijit.byId('txtCertSerialCT');
            if (txtCertSerialCTCheck) {
                txtCertSerialCTCheck.destroyRecursive(true);
            }
            doGoToMenu("filesAction!toApproveByCTPage.do");
        };

        page.onApproveSign = function() {
            if (document.getElementById("approveFormByCT.statusAccept").checked) {
                document.getElementById("trWait").style.display = "";
                document.getElementById("labelWait").innerHTML = "Hệ thống đang tạo bản công bố, vui lòng chờ  ";
                sd.connector.post("filesAction!onCreatePaper.do?" + token.getTokenParamString(), null, "approveFormByCT", null, afteronCreatePaper);
            }
            /*
             else{
             msg.alert("Bạn chưa chọn 'Phê duyệt hồ sơ'");
             return;
             }
             */
        };
        afteronCreatePaper = function(data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                document.getElementById("labelWait").value = "Hệ thống đang thực hiện ký và tải bản công bố, vui lòng chờ! ";
                var fileId = dijit.byId("approveFormByCT.fileId").getValue();
                sd.connector.post("exportWord!onExportPaperSignPlugin.do?fileId=" + fileId, null, null, null, page.signAfterCT);
            } else {
                msg.alert("Có lỗi trong quá trình tạo giấy", "Cảnh báo");
                document.getElementById("trWait").style.display = "none";
            }
        };
        function sleep(milliseconds) {
            var start = new Date().getTime();
            for (var i = 0; i < 1e7; i++) {
                if ((new Date().getTime() - start) > milliseconds) {
                    break;
                }
            }
        }
        var count = 0;
        var cert;
        var signType;
        page.signAfterCT = function(data)
        {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                var fileId = dijit.byId("approveFormByCT.fileId").getValue();
                if (count == 0) {
                    signType = "PDHS";
                    var item = uploadCertOfFile(fileId);
                    cert = encodeBase64(item.certChain);
                }
                var path = result[2];
                sd.connector.post("filesAction!actionSignCA.do?fileId=" + fileId + "&cert=" + cert + "&signType=" + signType + "&path=" + path, null, null, null, page.signPluginCT);
                count++;
            } else {
                alert(result[1]);
                document.getElementById("trWait").style.display = "none";
                document.getElementById("approveFormByCT.statusAccept").checked = false;
            }

        };

//        page.signPluginCT = function(data)
//        {
//            var obj = dojo.fromJson(data);
//            var result = obj.items;
//            if (result[0] == "1") {
//                var txtBase64HashNew = result[2];
//                var certSerialNew = result[3];
//                var fileId = result[4];
//                var outPutPath = result[5];
//                var fileName = result[6];
//                signType = "PDHS";
//                dijit.byId("txtBase64HashCT").setValue(txtBase64HashNew);
//                dijit.byId("txtCertSerialCT").setValue(certSerialNew);
//                var sign = signAndSubmit();
//                var signData = encodeBase64(sign);
//                sd.connector.post("filesAction!onSignPlugin.do?fileId=" + fileId + "&outPutPath=" + outPutPath + "&signData=" + signData + "&signType=" + signType + "&fileName=" + fileName, null, null, null, page.afterSignPluginCT);
//            } else
//            {
//                 alert("Ký số không thành công ! " + result[1]);
//            }
//        }

        page.signPluginCT = function(data)
        {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                var txtBase64HashNew = result[2];
                var certSerialNew = result[3];
                var fileId = result[4];
                var outPutPath = result[5];
                var fileName = result[6];
                var outPutPath2 = result[8];
                var txtBase64HashNew0 = result[7];
                var fileName0 = result[9];
//                signType = "PDHS";
                dijit.byId("txtBase64HashCT").setValue(txtBase64HashNew);
                dijit.byId("txtCertSerialCT").setValue(certSerialNew);
                dijit.byId("txtBase64HashCT0").setValue(txtBase64HashNew0);
                var sign = signAndSubmit();
                var signData = encodeBase64(sign);
                var signData2;
                var sign2;
                if (signType == "PDHS") {
                    sign2 = signAndSubmitOriginalFile();
                    signData2 = encodeBase64(sign2)
                }
                sd.connector.post("filesAction!onSignPlugin.do?fileId=" + fileId + "&outPutPath=" + outPutPath + "&signData=" + signData + "&signType=" + signType + "&fileName=" + fileName + "&outPutPath2=" + outPutPath2 + "&fileName0=" + fileName0 + "&signDataOriginal=" + signData2, null, null, null, page.afterSignPluginCT);
            } else
            {
                alert("Ký số không thành công ! " + result[1]);
            }
        };



        // hiepq update 231015
        page.afterSignPluginCT = function(data)
        {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                onApprove();
            } else
            {
                alert("Ký số không thành công !");
            }
        };

        page.replaceBrTblApproveFormByCT = function() {
            var content = "";
            content = document.getElementById("approveFormByCT.leaderRequest").innerHTML;
            content = content.replace(/\n/g, "<br>");
            document.getElementById("approveFormByCT.leaderRequest").innerHTML = content;
        };

        function deleteAllCookies() {
            var cookies = document.cookie.split(";");
            for (var i = 0; i < cookies.length; i++) {
                var cookie = cookies[i];
                var eqPos = cookie.indexOf("=");
                var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
                document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
            }
        };
        deleteAllCookies();

    </script>
    <input type="hidden" id="base64Hash0" value="" />
<sd:TextBox id="txtBase64HashCT0" key="" name="txtBase64Hash0" type="hidden"/>
<input type="hidden" id="base64Hash" value="" />
<sd:TextBox id="txtBase64HashCT" key="" name="txtBase64Hash" type="hidden"/>
<input type="hidden" id="certSerial" value="" />
<sd:TextBox id="txtCertSerialCT" key="" name="txtCertSerial" type="hidden"/>