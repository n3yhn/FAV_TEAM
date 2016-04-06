<%-- 
    Document   : approveFormByCT
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>


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
                <sd:TitlePane key="Thông tin hồ sơ" id="titlePaneViewFile">
                    <div id="divViewFile" style="overflow-y: auto;max-height: 600px"></div>
                </sd:TitlePane>
            </td>
            <td style="width: 38%">
                <sd:TitlePane key="Phê duyệt hồ sơ" id="titlePaneEvaluate">
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
                                        <sd:Textarea key="" id="approveFormByCT.leaderRequest" name="createForm.leaderRequest" rows="4" cssStyle="width:99%" maxlength="1800" trim="true"/>
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
                                        <sd:Button id="btnStatusAcceptAF" key="" onclick="page.onApproveSign();" cssStyle="display:" cssClass="buttonGroup">
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
<div id="showContentCT" style="visibility: hidden;height: 0px !important;" > 
</div>
<script type="text/javascript">
    afterApprove = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            document.getElementById("trWait").style.display = "none";
            page.search();
            onCloseApprove();
        }
    };

    onApprove = function () {
        page.clearContent();
        /*
         var leaderRequest = dijit.byId("approveFormByCT.leaderRequest").getValue();
         if (document.getElementById("approveFormByCT.statusAccept").checked == false){
         msg.alert("Bạn chưa chọn 'Phê duyệt hồ sơ'");
         }
         if (document.getElementById("approveFormByCT.statusAccept").checked) {
         
         }
         */
        sd.connector.post("filesAction!onApprove.do?" + token.getTokenParamString(), null, "approveFormByCT", null, afterApprove);
    };

    onCloseApprove = function () {
        dijit.byId("approveDlg").hide();
        dijit.byId("approveFormByCT.leaderRequest").setValue("");
        document.getElementById("approveFormByCT.statusAccept").checked = false;
        //document.getElementById("approveFormByCT.statusDeny").checked = false;
        //document.getElementById("approveFormByCT.statusDenyBus").checked = false;
        document.getElementById("trWait").style.display = "none";
        page.clearContent();
        
        doGoToMenu("filesAction!toApproveByCTPage.do");
    };

    page.onApproveSign = function () {
        page.clearContent();
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
    afteronCreatePaper = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result[0] == "1") {
            document.getElementById("labelWait").value = "Hệ thống đang thực hiện ký và tải bản công bố, vui lòng chờ! ";
            var fileId = dijit.byId("approveFormByCT.fileId").getValue();
            sd.connector.post("exportWord!onExportPaperSign.do?fileId=" + fileId, null, null, null, page.signAfter);
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
    page.signAfter = function (data)
    {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result[0] == "1") {
            page.showContent();
        } else {
            alert(result[1]);
            document.getElementById("trWait").style.display = "none";
            page.clearContent();
            document.getElementById("approveFormByCT.statusAccept").checked = false;
        }

    };
    page.showContent = function () {
        document.getElementById('showContentCT').innerHTML = page.getHtml();
        document.getElementById("showContentCT").style.display = "";
    };
    page.clearContent = function () {
        document.getElementById('showContentCT').innerHTML = '';
        document.getElementById("showContentCT").style.display = "none";
    };
    page.getHtml = function ()
    {
        var fileId = dijit.byId("approveFormByCT.fileId").getValue();
        var item = '<applet code="com.viettel.QLLLTP.ca.applet.DataSignApplet" archive="PdfSign.jar"  width="420" height="180" >';
        item += '<PARAM name="userId" value="' + ${userId} + '">';
        item += '<PARAM name="fileId" value="' + fileId + '">';
        item += '<PARAM name="signType" value="PDHS">';
        item += '</applet>';
        return item;
    };
    page.replaceBrTblApproveFormByCT = function () {
        var content = "";
        content = document.getElementById("approveFormByCT.leaderRequest").innerHTML;
        content = content.replace(/\n/g, "<br>");
        document.getElementById("approveFormByCT.leaderRequest").innerHTML = content;
    };
</script>