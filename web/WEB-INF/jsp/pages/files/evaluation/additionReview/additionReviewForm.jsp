<%-- 
    Document   : additionReviewForm
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
                <sd:TitlePane key="Xem xét hồ sơ" id="titlePaneEvaluate">
                    <div style="overflow-y: auto;max-height: 600px">
                        <form id="additionReviewForm" name="createForm">
                            <table width="100%" class="viewTable" id="tblAdditionReviewForm">
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Kết quả thẩm định"/></td>
                                    <td width="70%">
                                        <div id="additionReviewForm.status"></div>
                                    </td>

                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Nội dung thẩm định"/></td>
                                    <td width="70%">
                                        <div id="additionReviewForm.staffRequest"></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Kết quả xem xét SĐBS"/></td>
                                    <td width="70%">
                                        <sd:TextBox key="" id="additionReviewForm.fileId" name="createForm.fileId" cssStyle="display:none"/>
                                        <input type="radio" id="additionReviewForm.statusAccept" name="createForm.status" value="5"/>
                                        <sd:Label key="Duyệt: Hồ sơ đạt"/>
                                        </br>
                                        <input type="radio" id="additionReviewForm.statusDeny" name="createForm.status" value="19"/>
                                        <sd:Label key="Duyệt: Yêu cầu bổ sung HS"/>
                                        </br>
                                        <input type="radio" id="additionReviewForm.statusAcceptSend" name="createForm.status" value="20"  cssStyle="display:none" disabled="true" hidden="true"/>
                                        <sd:Label key="Thông báo SĐBS tới DN" cssStyle="display:none"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Nội dung yêu cầu bổ sung" required="true"/></td>
                                    <td>
                                        <sd:Textarea key="" id="additionReviewForm.leaderStaffRequest" name="createForm.leaderStaffRequest" rows="4" cssStyle="width:99%" maxlength="1800"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="text-align: center">
                                        <sx:ButtonSave onclick="onReview();"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </sd:TitlePane>
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

<script type="text/javascript">
    onCloseApprove = function () {
        doGoToMenu("filesAction!toAdditionReviewPage.do");
    };

    afterReview = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseReview();
            page.search();
        }
    };

    onReview = function () {
        var leaderStaffRequest = dijit.byId("additionReviewForm.leaderStaffRequest").getValue();
        if (document.getElementById("additionReviewForm.statusDeny").checked == true || document.getElementById("additionReviewForm.statusAccept").checked == true || document.getElementById("additionReviewForm.statusAcceptSend").checked == true) {
            if (document.getElementById("additionReviewForm.statusAccept").checked == true && leaderStaffRequest.trim().length == 0) {
                alert("Bạn chưa nhập nội dung phê duyệt");
            } else {
                sd.connector.post("filesAction!onReview.do?" + token.getTokenParamString(), null, "additionReviewForm", null, afterReview);
                return 0;
            }
        } else {
            alert("Bạn chưa chọn Kết quả xem xét SĐBS");
        }
    };

    page.replaceTblAdditionReviewForm = function () {
        var table = document.getElementById("additionReviewForm.staffRequest");
        //var divs = table.getElementsByTagName("div");
        //var i = 0;
        var content = "";
        //for (i = 0; i < divs.length; i++) {
        content = table.innerHTML;
        content = content.replace(/\n/g, "<br>");
        table.innerHTML = content;
//        }
    };

    onCloseReview = function () {
        dijit.byId("additionReviewFormDlg").hide();
        dijit.byId("additionReviewForm.leaderStaffRequest").setValue("");
        document.getElementById("additionReviewForm.statusAccept").checked = false;
        document.getElementById("additionReviewForm.statusDeny").checked = false;
        document.getElementById("additionReviewForm.statusAcceptSend").checked = false;
    };

</script>