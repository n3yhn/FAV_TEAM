<%-- 
    Document   : reviewForm
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
                        <form id="reviewForm" name="createForm">
                            <table width="100%" class="viewTable" id="tblReviewForm">
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Kết quả thẩm định"/></td>
                                    <td width="70%">
                                        <div id="reviewForm.status"></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Nội dung thẩm định"/></td>
                                    <td width="70%">
                                        <div id="reviewForm.staffRequest"></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Kết quả xem xét"/></td>
                                    <td width="70%">
                                        <sd:TextBox key="" id="reviewForm.fileId" name="createForm.fileId" cssStyle="display:none"/>
                                        <input type="radio" id="reviewForm.statusAccept" name="createForm.status" value="5"/>
                                        <sd:Label key="Duyệt: Hồ sơ đạt"/>   
                                        </br>
                                        <input type="radio" id="reviewForm.statusDeny" name="createForm.status" value="26"/>
                                        <sd:Label key="Duyệt: Yêu cầu bổ sung HS"/>
                                        </br>
                                        <input type="radio" id="reviewForm.statusDenyCV" name="createForm.status" value="8"/>
                                        <sd:Label key="Yêu cầu: Chuyển hồ sơ cho chuyên viên thẩm định lại"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Nội dung trình quản lý phê duyệt, hoặc nội dung yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" id="reviewForm.leaderStaffRequest" name="createForm.leaderStaffRequest" rows="4" cssStyle="width:99%" maxlength="2000"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)"/></td>
                                    <td>
                                        <sd:SelectBox id="reviewForm.legalL" name="createForm.evaluationRecordsForm.legalL" key="" >
                                            <sd:Option value='1'>Đồng ý</sd:Option>
                                            <sd:Option value='0'>Không đồng ý</sd:Option>
                                            <sd:Option value='-1'>Bổ sung</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" id="reviewForm.legalContentL" name="createForm.evaluationRecordsForm.legalContentL" rows="4" cssStyle="width:99%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Về chỉ tiêu chất lượng an toàn thực phẩm"/></td>
                                    <td>
                                        <sd:SelectBox id="reviewForm.foodSafetyQualityL" name="createForm.evaluationRecordsForm.foodSafetyQualityL" key="" >
                                            <sd:Option value='1'>Đồng ý</sd:Option>
                                            <sd:Option value='0'>Không đồng ý</sd:Option>
                                            <sd:Option value='-1'>Bổ sung</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" id="reviewForm.foodSafetyQualityContentL" name="createForm.evaluationRecordsForm.foodSafetyQualityContentL" rows="4" cssStyle="width:99%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng"/></td>
                                    <td>
                                        <sd:SelectBox id="reviewForm.effectUtilityL" name="createForm.evaluationRecordsForm.effectUtilityL" key="" >
                                            <sd:Option value='1'>Đồng ý</sd:Option>
                                            <sd:Option value='0'>Không đồng ý</sd:Option>
                                            <sd:Option value='-1'>Bổ sung</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" id="reviewForm.effectUtilityContentL" name="createForm.evaluationRecordsForm.effectUtilityContentL" rows="4" cssStyle="width:99%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <tr>                    
                                    <td align="right">
                                        <sd:Label key="Chọn lãnh đạo phê duyệt"/>
                                    </td>
                                    <td>
                                        <sd:SelectBox  cssStyle="width:98%"
                                                       id="reviewForm.leaderApproveId"
                                                       key="" data="lstLeader" valueField="userId" labelField="fullName"
                                                       name="createForm.leaderApproveId" >
                                        </sd:SelectBox>
                                        <sd:TextBox id="reviewForm.leaderApproveName" name="createForm.leaderApproveName" cssStyle="display:none" key=""/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="text-align: center">
                                        <sx:ButtonSave onclick="onReviewFormSave();"/>
                                        <br>
                                        <sd:Button id="btnLoadCookieRFOG" key="" onclick="page.getReviewForm();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Tải ND thẩm định gần đây</span>
                                        </sd:Button>
                                        <sd:Button id="btnSaveCookieRFOG" key="" onclick="page.setRreviewForm();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Lưu nháp ND thẩm định</span>
                                        </sd:Button>
                                        <sd:Button id="btnClearCookieRFOG" key="" onclick="page.clearReviewForm();" cssStyle="display:" cssClass="buttonGroup">
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
                <sx:ButtonClose onclick="onCloseReviewForm();"/>                
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript">
    onCloseReviewForm = function() {
        //clearReviewFormOnGrid();
        doGoToMenu("filesAction!toReviewPage.do");
    };

    afterReviewFormSave = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseReviewForm();
        }
    };
    onReviewFormSave = function() {
//        if (validateReviewForm()) {
//            sd.connector.post("filesAction!onReview.do?" + token.getTokenParamString(), null, "reviewForm", null, afterReviewFormSave);
//        }
        msg.confirm("Bạn có chắc chắn về kết quả xem xét này không ?", "Xem xét hồ sơ", onReviewFormSaveAction);
    };

    onReviewFormSaveAction = function() {
        if (validateReviewForm()) {
            sd.connector.post("filesAction!onReview.do?" + token.getTokenParamString(), null, "reviewForm", null, afterReviewFormSave);
        }
    };

    validateReviewForm = function() {
        if (document.getElementById("reviewForm.statusAccept").checked == false
                && document.getElementById("reviewForm.statusDeny").checked == false
                && document.getElementById("reviewForm.statusDenyCV").checked == false) {
            alert("Bạn chưa chọn [Kết quả xem xét]");
            return false;
        }
        var leaderStaffRequest = dijit.byId("reviewForm.leaderStaffRequest").getValue();
        var legalContentL = dijit.byId("reviewForm.legalContentL").getValue();
        var foodSafetyQualityContentL = dijit.byId("reviewForm.foodSafetyQualityContentL").getValue();
        var effectUtilityContentL = dijit.byId("reviewForm.effectUtilityContentL").getValue();
        if (document.getElementById("reviewForm.statusAccept").checked == true
                && (dijit.byId("reviewForm.legalL").getValue() != 1
                        || dijit.byId("reviewForm.foodSafetyQualityL").getValue() != 1
                        || dijit.byId("reviewForm.effectUtilityL").getValue() != 1)) {
            alert("Kết luân thẩm định và Nội dung thẩm định không đúng vui lòng kiểm tra lại!");
            return false;
        } else {
            if (document.getElementById("reviewForm.statusDeny").checked) {
                if (leaderStaffRequest.trim().length == 0) {
                    alert("Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung");
                    dijit.byId("reviewForm.leaderStaffRequest").focus();
                    return false;
                }
                if ((dijit.byId("reviewForm.legalL").getValue() == 0
                        || dijit.byId("reviewForm.legalL").getValue() == -1)
                        && legalContentL.trim().length == 0) {
                    alert("[Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)] chưa nhập lý do");
                    dijit.byId("reviewForm.legalContentL").focus();
                    return false;
                }
                if ((dijit.byId("reviewForm.foodSafetyQualityL").getValue() == 0
                        || dijit.byId("reviewForm.foodSafetyQualityL").getValue() == -1)
                        && foodSafetyQualityContentL.trim().length == 0) {
                    alert("[Về chỉ tiêu chất lượng an toàn thực phẩm] chưa nhập lý do");
                    dijit.byId("reviewForm.foodSafetyQualityContentL").focus();
                    return false;
                }
                if ((dijit.byId("reviewForm.effectUtilityL").getValue() == 0
                        || dijit.byId("reviewForm.effectUtilityL").getValue() == -1)
                        && effectUtilityContentL.trim().length == 0) {
                    alert("[Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng] chưa nhập lý do");
                    dijit.byId("reviewForm.effectUtilityContentL").focus();
                    return false;
                }
            } else {
                if (document.getElementById("reviewForm.statusDenyCV").checked) {
                    if (leaderStaffRequest.trim().length == 0) {
                        alert("Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung");
                        dijit.byId("reviewForm.leaderStaffRequest").focus();
                        return false;
                    }
                }
            }
            if (document.getElementById("reviewForm.statusDeny").checked == true
                    || document.getElementById("reviewForm.statusAccept").checked == true) {
                var leaderId = dijit.byId("reviewForm.leaderApproveId").getValue();
                if (leaderId == null
                        || leaderId == ""
                        || leaderId == -1) {
                    alert("Bạn chưa chọn lãnh đạo phê duyệt");
                    dijit.byId("reviewForm.leaderApproveId").focus();
                    return false;
                } else {
                    var leaderApproveName = dijit.byId("reviewForm.leaderApproveId").attr("displayedValue");
                    dijit.byId("reviewForm.leaderApproveName").setValue(leaderApproveName);
                }
            }
        }
        return true;
    };
    /*
     clearReviewFormOnGrid = function() {
     dijit.byId("reviewForm.leaderStaffRequest").setValue("");
     dijit.byId("reviewForm.legalContentL").setValue("");
     dijit.byId("reviewForm.foodSafetyQualityContentL").setValue("");
     dijit.byId("reviewForm.effectUtilityContentL").setValue("");
     
     document.getElementById("reviewForm.statusAccept").checked = true;
     document.getElementById("reviewForm.statusDeny").checked = false;
     
     dijit.byId("reviewForm.legalL").setValue(1);
     dijit.byId("reviewForm.foodSafetyQualityL").setValue(1);
     dijit.byId("reviewForm.effectUtilityL").setValue(1);
     dijit.byId("reviewDlg").hide();
     };
     */
    page.replaceBrTblReviewForm = function() {
        var content = "";
        content = document.getElementById("reviewForm.staffRequest").innerHTML;
        content = content.replace(/\n/g, "<br>");
        document.getElementById("reviewForm.staffRequest").innerHTML = content;
    };

    page.clearReviewForm = function() {
        try
        {
            localStorage.setItem("reviewForm.reviewForm.leaderApproveId", "-1");
            localStorage.setItem("reviewForm.reviewForm.leaderStaffRequest", "");
            localStorage.setItem("reviewForm.reviewForm.legalL", "1");
            localStorage.setItem("reviewForm.reviewForm.legalContentL", "");
            localStorage.setItem("reviewForm.reviewForm.foodSafetyQualityL", "1");
            localStorage.setItem("reviewForm.reviewForm.foodSafetyQualityContentL", "");
            localStorage.setItem("reviewForm.reviewForm.effectUtilityL", "1");
            localStorage.setItem("reviewForm.reviewForm.effectUtilityContentL", "");

            alert("Xóa nội dung thẩm định gần đây thành công!");
        }
        catch (err)
        {
            alert("Không thể Xóa nội dung thẩm định gần đây!");
        }
    };
    page.setRreviewForm = function() {
        try
        {
            localStorage.setItem("reviewForm.reviewForm.leaderApproveId", encodeBase64(dijit.byId("reviewForm.leaderApproveId").getValue().toString().trim()));
            localStorage.setItem("reviewForm.reviewForm.leaderStaffRequest", encodeBase64(dijit.byId("reviewForm.leaderStaffRequest").getValue().toString().trim()));
            localStorage.setItem("reviewForm.reviewForm.legalL", encodeBase64(dijit.byId("reviewForm.legalL").getValue().toString().trim()));
            localStorage.setItem("reviewForm.reviewForm.legalContentL", encodeBase64(dijit.byId("reviewForm.legalContentL").getValue().toString().trim()));
            localStorage.setItem("reviewForm.reviewForm.foodSafetyQualityL", encodeBase64(dijit.byId("reviewForm.foodSafetyQualityL").getValue().toString().trim()));
            localStorage.setItem("reviewForm.reviewForm.foodSafetyQualityContentL", encodeBase64(dijit.byId("reviewForm.foodSafetyQualityContentL").getValue().toString().trim()));
            localStorage.setItem("reviewForm.reviewForm.effectUtilityL", encodeBase64(dijit.byId("reviewForm.effectUtilityL").getValue().toString().trim()));
            localStorage.setItem("reviewForm.reviewForm.effectUtilityContentL", encodeBase64(dijit.byId("reviewForm.effectUtilityContentL").getValue().toString().trim()));
            alert("Lưu nháp nội dung thẩm định thành công!");
        }
        catch (err)
        {
            alert("Không thể Lưu nháp nội dung thẩm định!");
        }
    };
    page.getReviewForm = function() {
        try
        {
            dijit.byId("reviewForm.leaderApproveId").setValue(decodeBase64(localStorage.getItem("reviewForm.reviewForm.leaderApproveId")));
            dijit.byId("reviewForm.leaderStaffRequest").setValue(decodeBase64(localStorage.getItem("reviewForm.reviewForm.leaderStaffRequest")));
            dijit.byId("reviewForm.legalL").setValue(decodeBase64(localStorage.getItem("reviewForm.reviewForm.legalL")));
            dijit.byId("reviewForm.legalContentL").setValue(decodeBase64(localStorage.getItem("reviewForm.reviewForm.legalContentL")));
            dijit.byId("reviewForm.foodSafetyQualityL").setValue(decodeBase64(localStorage.getItem("reviewForm.reviewForm.foodSafetyQualityL")));
            dijit.byId("reviewForm.foodSafetyQualityContentL").setValue(decodeBase64(localStorage.getItem("reviewForm.reviewForm.foodSafetyQualityContentL")));
            dijit.byId("reviewForm.effectUtilityL").setValue(decodeBase64(localStorage.getItem("reviewForm.reviewForm.effectUtilityL")));
            dijit.byId("reviewForm.effectUtilityContentL").setValue(decodeBase64(localStorage.getItem("reviewForm.reviewForm.effectUtilityContentL")));
            alert("Tải nội thẩm định gần đây thành công!");
        }
        catch (err)
        {
            alert("Không thể Tải nội thẩm định gần đây!");
        }
    };
</script>