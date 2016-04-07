<%-- 
    Document   : evaluateForm
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
                <sd:TitlePane key="Kết luận thẩm định" id="titlePaneEvaluate">
                    <div style="overflow-y: auto;max-height: 600px">
                        <form id="evaluateForm" name="createForm">
                            <table width="100%" class="viewTable">
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Kết quả thẩm định"/></td>
                                    <td width="70%">
                                        <sd:TextBox key="" id="evaluateForm.fileId" name="createForm.fileId" cssStyle="display:none"/>                
                                        <input type="radio" id="evaluateForm.statusAccept" name="createForm.status" value="4" onchange="onchangeStatusEFOG();"/>
                                        <sd:Label key="Duyệt: Hồ sơ đạt"/>
                                        </br>
                                        <input type="radio" id="evaluateForm.statusDeny" name="createForm.status" value="7" onchange="onchangeStatusEFOG();"/>
                                        <sd:Label key="Duyệt: Yêu cầu bổ sung HS"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" id="evaluateForm.staffRequest" name="createForm.staffRequest" rows="15" cssStyle="width:99%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <!--                                <tr>
                                                                    <td style="text-align: right"><sd:Label key="Tiêu đề hồ sơ SĐBS"/></td>
                                                                    <td>
                                <sd:Textarea key="" id="evaluateForm.titleEditATTP" name="createForm.titleEditATTP" rows="3" cssStyle="width:99%" maxlength="255" trim="true"/>
                            </td>
                        </tr>-->
                                <div id="effectiveDateDiv" style="display:none">
                                    <tr style="display: none">
                                        <td style="text-align: right"><sd:Label key="Thời hạn hiệu lực của giấy tiếp nhận"/></td>
                                        <td>
                                            <sd:SelectBox id="evaluateForm.effectiveDate" name="createForm.effectiveDate" key="" >
                                                <sd:Option value='-1' selected="true">--Chọn--</sd:Option>
                                                <sd:Option value='0'>Không thời hạn</sd:Option>
                                                <sd:Option value='3'>3 năm</sd:Option>
                                                <sd:Option value='5'>5 năm</sd:Option>
                                            </sd:SelectBox>
                                        </td>
                                    </tr>
                                </div>
                                <tr style="display: none">
                                    <td style="text-align: right"><sd:Label key="Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)"/></td>
                                    <td>
                                        <sd:SelectBox id="evaluationRecordsForm.legal" name="createForm.evaluationRecordsForm.legal" key="" >
                                            <sd:Option value='1'>Đồng ý</sd:Option>
                                            <sd:Option value='0'>Không đồng ý</sd:Option>
                                            <sd:Option value='-1'>Bổ sung</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr style="display: none">
                                    <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" id="evaluationRecordsForm.legalContent" name="createForm.evaluationRecordsForm.legalContent" rows="5" cssStyle="width:99%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <tr style="display: none">
                                    <td style="text-align: right"><sd:Label key="Về chỉ tiêu chất lượng an toàn thực phẩm"/></td>
                                    <td>
                                        <sd:SelectBox id="evaluationRecordsForm.foodSafetyQuality" name="createForm.evaluationRecordsForm.foodSafetyQuality" key="" >
                                            <sd:Option value='1'>Đồng ý</sd:Option>
                                            <sd:Option value='0'>Không đồng ý</sd:Option>
                                            <sd:Option value='-1'>Bổ sung</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr style="display: none">
                                    <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" id="evaluationRecordsForm.foodSafetyQualityContent" name="createForm.evaluationRecordsForm.foodSafetyQualityContent" rows="5" cssStyle="width:99%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <tr style="display: none">
                                    <td style="text-align: right"><sd:Label key="Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng"/></td>
                                    <td>
                                        <sd:SelectBox id="evaluationRecordsForm.effectUtility" name="createForm.evaluationRecordsForm.effectUtility" key="" >
                                            <sd:Option value='1'>Đồng ý</sd:Option>
                                            <sd:Option value='0'>Không đồng ý</sd:Option>
                                            <sd:Option value='-1'>Bổ sung</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr style="display: none">
                                    <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" id="evaluationRecordsForm.effectUtilityContent" name="createForm.evaluationRecordsForm.effectUtilityContent" rows="5" cssStyle="width:99%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <tr id="trLeaderReviewApproveId">                    
                                    <td align="right">
                                        <sd:Label key="Chọn lãnh đạo"/>
                                    </td>
                                    <td>
                                        <sd:SelectBox cssStyle="width:100%"
                                                      id="evaluationRecordsForm.leaderReviewId"
                                                      key="" data="lstLeaderOfStaff" valueField="userId" labelField="fullName"
                                                      name="createForm.leaderReviewId" >
                                        </sd:SelectBox>
                                        <sd:TextBox id="evaluationRecordsForm.leaderReviewName" name="createForm.leaderReviewName" cssStyle="display:none" key=""/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Gửi lãnh đạo để thẩm định(Đối với thực phẩm chức năng)"/></td>
                                    <td>
                                        <sd:TextBox key="" id="evaluateForm.ProductType" name="createForm.ProductType" cssStyle="display:none"/>
                                        <sd:CheckBox key="" id="ckbAssignLeader" name="ckbAssignLeader" value=""/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="text-align: center">
                                        <sx:ButtonSave onclick="onEvaluate();"/>
                                        <br>
                                        <sd:Button id="btnLoadCookieEF" key="" onclick="page.getEvaluateForm();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Tải ND thẩm định gần đây</span>
                                        </sd:Button>
                                        <sd:Button id="btnSaveCookieEF" key="" onclick="page.setEvaluateForm();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Lưu nháp ND thẩm định</span>
                                        </sd:Button>
                                        <sd:Button id="btnClearCookieEF" key="" onclick="page.clearEvaluateForm();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Xóa ND thẩm định gần đây</span>
                                        </sd:Button>
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
        doGoToMenu("filesAction!toEvaluatePage.do");
    };

    onEvaluate = function () {
//        if (validateEFOG()) {
//            sd.connector.post("filesAction!onEvaluate.do?" + token.getTokenParamString(), null, "evaluateForm", null, afterEvaluate);
//        }
        msg.confirm("Bạn có chắc chắn về kết quả thẩm định này không ?", "Thẩm định hồ sơ", onEvaluateNew);
    };

    //hieptq update 160415
    onEvaluateNew = function () {
        if (validateEFOG()) {
            sd.connector.post("filesAction!onEvaluate.do?" + token.getTokenParamString(), null, "evaluateForm", null, afterEvaluate);
        }
    };


    afterEvaluate = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseApprove();
        }
    };

    validateEFOG = function () {
        if (document.getElementById("evaluateForm.statusAccept").checked == false 
                && document.getElementById("evaluateForm.statusDeny").checked == false) {
            alert("Bạn chưa chọn [Kết quả thẩm định]");
            return false;
        }
        var staffRequest = dijit.byId("evaluateForm.staffRequest").getValue();
        var legalContent = dijit.byId("evaluationRecordsForm.legalContent").getValue();
        var foodSafetyQualityContent = dijit.byId("evaluationRecordsForm.foodSafetyQualityContent").getValue();
        var effectUtilityContent = dijit.byId("evaluationRecordsForm.effectUtilityContent").getValue();
        if (document.getElementById("evaluateForm.statusAccept").checked == true
                && (dijit.byId("evaluationRecordsForm.legal").getValue() != 1
                        || dijit.byId("evaluationRecordsForm.foodSafetyQuality").getValue() != 1
                        || dijit.byId("evaluationRecordsForm.effectUtility").getValue() != 1)) {
            alert("Kết luân thẩm định và Nội dung thẩm định không đúng vui lòng kiểm tra lại!");
            return false;
        } else {
            if (document.getElementById("evaluateForm.statusDeny").checked) {
                if (staffRequest.trim().length == 0) {
                    alert("Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung");
                    dijit.byId("evaluateForm.staffRequest").focus();
                    return false;
                }
                if ((dijit.byId("evaluationRecordsForm.legal").getValue() == 0 
                        || dijit.byId("evaluationRecordsForm.legal").getValue() == -1) 
                        && legalContent.trim().length == 0) {
                    alert("[Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)] chưa nhập lý do");
                    dijit.byId("evaluationRecordsForm.legalContent").focus();
                    return false;
                }
                if ((dijit.byId("evaluationRecordsForm.foodSafetyQuality").getValue() == 0 
                        || dijit.byId("evaluationRecordsForm.foodSafetyQuality").getValue() == -1) 
                        && foodSafetyQualityContent.trim().length == 0) {
                    alert("[Về chỉ tiêu chất lượng an toàn thực phẩm] chưa nhập lý do");
                    dijit.byId("evaluationRecordsForm.foodSafetyQualityContent").focus();
                    return false;
                }
                if ((dijit.byId("evaluationRecordsForm.effectUtility").getValue() == 0 
                        || dijit.byId("evaluationRecordsForm.effectUtility").getValue() == -1) 
                        && effectUtilityContent.trim().length == 0) {
                    alert("[Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng] chưa nhập lý do");
                    dijit.byId("evaluationRecordsForm.effectUtilityContent").focus();
                    return false;
                }
            }
        }
        var leaderReviewId = dijit.byId("evaluationRecordsForm.leaderReviewId").getValue();
        if (leaderReviewId == -1) {
            alert("Bạn chưa chọn lãnh đạo thực hiện");
            dijit.byId("evaluationRecordsForm.leaderReviewId").focus();
            return false;
        } else {
            var leaderReviewName = dijit.byId("evaluationRecordsForm.leaderReviewId").attr("displayedValue");
            dijit.byId("evaluationRecordsForm.leaderReviewName").setValue(leaderReviewName);
        }
        if (document.getElementById("ckbAssignLeader").checked == false) {
            dijit.byId("evaluateForm.ProductType").setValue(0);
        } else {
            dijit.byId("evaluateForm.ProductType").setValue(1);
        }
        return true;
    };
    onCloseEvaluate = function () {
        dijit.byId("evaluateDlg").hide();
        dijit.byId("evaluateForm.staffRequest").setValue("");
        //        dijit.byId("evaluateForm.statusAccept").checked = false;
        document.getElementById("evaluateForm.statusAccept").checked = false;
        document.getElementById("evaluateForm.statusDeny").checked = false;
        dijit.byId("evaluateForm.effectiveDate").setValue(-1);
        dijit.byId("evaluationRecordsForm.legal").setValue(1);
        dijit.byId("evaluationRecordsForm.foodSafetyQuality").setValue(1);
        dijit.byId("evaluationRecordsForm.effectUtility").setValue(1);
        dijit.byId("evaluationRecordsForm.leaderReviewId").setValue(-1);
        dijit.byId("evaluationRecordsForm.legalContent").setValue("");
        dijit.byId("evaluationRecordsForm.foodSafetyQualityContent").setValue("");
        dijit.byId("evaluationRecordsForm.effectUtilityContent").setValue("");
    };
    onchangeStatusEFOG = function () {
        if (document.getElementById("evaluateForm.statusDeny").checked) {
            dijit.byId("evaluateForm.staffRequest").setValue("Yêu cầu bổ sung hồ sơ");
        } else {
            dijit.byId("evaluateForm.staffRequest").setValue("");
        }
    };
    page.clearEvaluateForm = function () {
        try
        {
            localStorage.setItem("evaluateForm.evaluateForm.staffRequest", "");
            localStorage.setItem("evaluateForm.evaluateForm.effectiveDate", "-1");
            localStorage.setItem("evaluateForm.evaluationRecordsForm.legal", "1");
            localStorage.setItem("evaluateForm.evaluationRecordsForm.foodSafetyQuality", "1");
            localStorage.setItem("evaluateForm.evaluationRecordsForm.effectUtility", "1");
            localStorage.setItem("evaluateForm.evaluationRecordsForm.leaderReviewId", "-1");
            localStorage.setItem("evaluateForm.evaluationRecordsForm.legalContent", "");
            localStorage.setItem("evaluateForm.evaluationRecordsForm.foodSafetyQualityContent", "");
            localStorage.setItem("evaluateForm.evaluationRecordsForm.effectUtilityContent", "");
            alert("Xóa nội dung thẩm định gần đây thành công!");
        }
        catch (err)
        {
            alert("Không thể Xóa nội dung thẩm định gần đây!");
        }
    };
    page.setEvaluateForm = function () {
        try
        {
            localStorage.setItem("evaluateForm.evaluateForm.staffRequest",
                    encodeBase64(dijit.byId("evaluateForm.staffRequest").getValue().toString().trim()));
            localStorage.setItem("evaluateForm.evaluateForm.effectiveDate",
                    encodeBase64(dijit.byId("evaluateForm.effectiveDate").getValue().toString().trim()));
            localStorage.setItem("evaluateForm.evaluationRecordsForm.legal",
                    encodeBase64(dijit.byId("evaluationRecordsForm.legal").getValue().toString().trim()));
            localStorage.setItem("evaluateForm.evaluationRecordsForm.foodSafetyQuality",
                    encodeBase64(dijit.byId("evaluationRecordsForm.foodSafetyQuality").getValue().toString().trim()));
            localStorage.setItem("evaluateForm.evaluationRecordsForm.effectUtility",
                    encodeBase64(dijit.byId("evaluationRecordsForm.effectUtility").getValue().toString().trim()));
            localStorage.setItem("evaluateForm.evaluationRecordsForm.leaderReviewId",
                    encodeBase64(dijit.byId("evaluationRecordsForm.leaderReviewId").getValue().toString().trim()));
            localStorage.setItem("evaluateForm.evaluationRecordsForm.legalContent",
                    encodeBase64(dijit.byId("evaluationRecordsForm.legalContent").getValue().toString().trim()));
            localStorage.setItem("evaluateForm.evaluationRecordsForm.foodSafetyQualityContent",
                    encodeBase64(dijit.byId("evaluationRecordsForm.foodSafetyQualityContent").getValue().toString().trim()));
            localStorage.setItem("evaluateForm.evaluationRecordsForm.effectUtilityContent", encodeBase64(dijit.byId("evaluationRecordsForm.effectUtilityContent").getValue().toString().trim()));
            alert("Lưu nháp nội dung thẩm định thành công!");
        }
        catch (err)
        {
            alert("Không thể Lưu nháp nội dung thẩm định!");
        }
    };
    page.getEvaluateForm = function () {
        try
        {
            dijit.byId("evaluateForm.staffRequest").setValue(decodeBase64(localStorage.getItem("evaluateForm.evaluateForm.staffRequest")));
            dijit.byId("evaluateForm.effectiveDate").setValue(decodeBase64(localStorage.getItem("evaluateForm.evaluateForm.effectiveDate")));
            dijit.byId("evaluationRecordsForm.legal").setValue(decodeBase64(localStorage.getItem("evaluateForm.evaluationRecordsForm.legal")));
            dijit.byId("evaluationRecordsForm.foodSafetyQuality").setValue(decodeBase64(localStorage.getItem("evaluateForm.evaluationRecordsForm.foodSafetyQuality")));
            dijit.byId("evaluationRecordsForm.effectUtility").setValue(decodeBase64(localStorage.getItem("evaluateForm.evaluationRecordsForm.effectUtility")));
            dijit.byId("evaluationRecordsForm.leaderReviewId").setValue(decodeBase64(localStorage.getItem("evaluateForm.evaluationRecordsForm.leaderReviewId")));
            dijit.byId("evaluationRecordsForm.legalContent").setValue(decodeBase64(localStorage.getItem("evaluateForm.evaluationRecordsForm.legalContent")));
            dijit.byId("evaluationRecordsForm.foodSafetyQualityContent").setValue(decodeBase64(localStorage.getItem("evaluateForm.evaluationRecordsForm.foodSafetyQualityContent")));
            dijit.byId("evaluationRecordsForm.effectUtilityContent").setValue(decodeBase64(localStorage.getItem("evaluateForm.evaluationRecordsForm.effectUtilityContent")));
            alert("Tải nội thẩm định gần đây thành công!");
        }
        catch (err)
        {
            alert("Không thể Tải nội thẩm định gần đây!");
        }
    };
</script>