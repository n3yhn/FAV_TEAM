<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<form id="evaluateFormByLeader" name="createForm">
    <table width="100%" class="viewTable">
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Kết quả chuyên viên thẩm định"/></td>
            <td width="70%">
                <div id="evaluateFormByLeader.statusL"></div>
            </td>
        </tr>
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Kết quả thẩm định"/></td>
            <td width="70%">
                <sd:TextBox key="" id="evaluateFormByLeader.fileId" name="createForm.fileId" cssStyle="display:none"/>
                <sd:TextBox key="" id="evaluateFormByLeader.ProductType" name="createForm.ProductType" cssStyle="display:none"/>
                <input type="radio" id="evaluateFormByLeader.statusAccept" name="createForm.status" value="4"/>
                <sd:Label key="Duyệt: Hồ sơ đạt"/>
                </br>
                <input type="radio" id="evaluateFormByLeader.statusDeny" name="createForm.status" value="7"/>
                <sd:Label key="Duyệt: Yêu cầu bổ sung HS"/>
                </br>
                <input type="radio" id="evaluateFormByLeader.statusDenyCV" name="createForm.status" value="8"/>
                <sd:Label key="Yêu cầu: Chuyển hồ sơ cho chuyên viên thẩm định lại"/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung"/></td>
            <td>
                <sd:Textarea key="" id="evaluateFormByLeader.staffRequest" name="createForm.staffRequest" rows="5" cssStyle="width:98%" maxlength="2000" trim="true"/>
            </td>
        </tr>
        <div id="effectiveDateDiv" style="display:none">
            <tr>
                <td style="text-align: right"><sd:Label key="Thời hạn hiệu lực của giấy tiếp nhận"/></td>
                <td>
                    <sd:SelectBox  cssStyle="width:98%" id="evaluateFormByLeader.effectiveDate" name="createForm.effectiveDate" key="" >
                        <sd:Option value='-1' selected="true">--Chọn--</sd:Option>
                        <sd:Option value='0'>Không thời hạn</sd:Option>
                        <sd:Option value='3'>3 năm</sd:Option>
                        <sd:Option value='5'>5 năm</sd:Option>
                    </sd:SelectBox>
                </td>
            </tr>
        </div>
        <tr>
            <td style="text-align: right"><sd:Label key="Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)"/></td>
            <td>
                <sd:SelectBox  cssStyle="width:98%" id="evaluationRecordsFormByLeader.legal" name="createForm.evaluationRecordsForm.legal" key="" >
                    <sd:Option value='1'>Đồng ý</sd:Option>
                    <sd:Option value='0'>Không đồng ý</sd:Option>
                    <sd:Option value='-1'>Bổ sung</sd:Option>
                </sd:SelectBox>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
            <td>
                <sd:Textarea key="" id="evaluationRecordsFormByLeader.legalContent" name="createForm.evaluationRecordsForm.legalContent" rows="5" cssStyle="width:98%" maxlength="2000" trim="true"/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Về chỉ tiêu chất lượng an toàn thực phẩm"/></td>
            <td>
                <sd:SelectBox  cssStyle="width:98%" id="evaluationRecordsFormByLeader.foodSafetyQuality" name="createForm.evaluationRecordsForm.foodSafetyQuality" key="" >
                    <sd:Option value='1'>Đồng ý</sd:Option>
                    <sd:Option value='0'>Không đồng ý</sd:Option>
                    <sd:Option value='-1'>Bổ sung</sd:Option>
                </sd:SelectBox>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
            <td>
                <sd:Textarea key="" id="evaluationRecordsFormByLeader.foodSafetyQualityContent" name="createForm.evaluationRecordsForm.foodSafetyQualityContent" rows="5" cssStyle="width:98%" maxlength="2000" trim="true"/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng"/></td>
            <td>
                <sd:SelectBox id="evaluationRecordsFormByLeader.effectUtility" name="createForm.evaluationRecordsForm.effectUtility" key=""  cssStyle="width:98%" >
                    <sd:Option value='1'>Đồng ý</sd:Option>
                    <sd:Option value='0'>Không đồng ý</sd:Option>
                    <sd:Option value='-1'>Bổ sung</sd:Option>
                </sd:SelectBox>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
            <td>
                <sd:Textarea key="" id="evaluationRecordsFormByLeader.effectUtilityContent" name="createForm.evaluationRecordsForm.effectUtilityContent" rows="5" cssStyle="width:98%" maxlength="2000" trim="true"/>
            </td>
        </tr>
        <tr>                    
            <td align="right">
                <sd:Label key="Chọn cán bộ xem xét"/>
            </td>
            <td>
                <sd:SelectBox  cssStyle="width:98%"
                               id="evaluationRecordsFormByLeader.leaderReviewId"
                               key="" data="lstLeaderOfStaff" valueField="userId" labelField="fullName"
                               name="createForm.leaderReviewId" >
                </sd:SelectBox>
                <sd:TextBox id="evaluationRecordsFormByLeader.leaderReviewName" name="createForm.leaderReviewName" cssStyle="display:none" key=""/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="onEvaluateLeader();"/>
                <sx:ButtonClose onclick="onCloseEvaluateLeader();"/>
                <br>
                <sd:Button id="btnLoadCookieEFBL" key="" onclick="page.getCKEFBL();" cssStyle="display:" cssClass="buttonGroup">
                    <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                    <span style="font-size:12px">Tải ND thẩm định gần đây</span>
                </sd:Button>
                <sd:Button id="btnSaveCookieEFBL" key="" onclick="page.setCKEFBL();" cssStyle="display:" cssClass="buttonGroup">
                    <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                    <span style="font-size:12px">Lưu nháp ND thẩm định</span>
                </sd:Button>
                <sd:Button id="btnClearCookieEFBL" key="" onclick="page.clearCKEFBL();" cssStyle="display:" cssClass="buttonGroup">
                    <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                    <span style="font-size:12px">Xóa ND thẩm định gần đây</span>
                </sd:Button>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    onEvaluateLeader = function () {
//        if (validateLeader()) {
//            sd.connector.post("filesAction!onEvaluate.do?" + token.getTokenParamString(), null, "evaluateFormByLeader", null, afterEvaluateLeader);
//        }
        msg.confirm("Bạn có chắc chắn về kết quả thẩm định này không ?", "Thẩm định hồ sơ", onEvaluateLeaderNew);
    };

    //hieptq update 160415
    onEvaluateLeaderNew = function () {
        if (validateLeader()) {
            sd.connector.post("filesAction!onEvaluate.do?" + token.getTokenParamString(), null, "evaluateFormByLeader", null, afterEvaluateLeader);
        }
    };

    afterEvaluateLeader = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseEvaluateLeader();
            backPage();
            page.search();
        }
    };

    validateLeader = function () {
        if (document.getElementById("evaluateFormByLeader.statusAccept").checked == false && document.getElementById("evaluateFormByLeader.statusDeny").checked == false && document.getElementById("evaluateFormByLeader.statusDenyCV").checked == false) {
            alert("Bạn chưa chọn [Kết quả thẩm định]");
            return false;
        }
        var effectiveDate = dijit.byId("evaluateFormByLeader.effectiveDate").getValue();
        if ((effectiveDate == null || effectiveDate.trim().length == -1 || effectiveDate == "-1") && document.getElementById("evaluateFormByLeader.statusAccept").checked == true) {
            alert("Bạn chưa chọn thời hạn hiệu lực");
            dijit.byId("evaluateFormByLeader.effectiveDate").focus();
            return false;
        }
        var leaderReviewId = dijit.byId("evaluationRecordsFormByLeader.leaderReviewId").getValue();
        if (leaderReviewId == -1) {
            alert("Bạn chưa chọn lãnh đạo thực hiện");
            dijit.byId("evaluationRecordsFormByLeader.leaderReviewId").focus();
            return false;
        } else {
            var leaderReviewName = dijit.byId("evaluationRecordsFormByLeader.leaderReviewId").attr("displayedValue");
            dijit.byId("evaluationRecordsFormByLeader.leaderReviewName").setValue(leaderReviewName);
        }
        var staffRequest = dijit.byId("evaluateFormByLeader.staffRequest").getValue();
        var legalContent = dijit.byId("evaluationRecordsFormByLeader.legalContent").getValue();
        var foodSafetyQualityContent = dijit.byId("evaluationRecordsFormByLeader.foodSafetyQualityContent").getValue();
        var effectUtilityContent = dijit.byId("evaluationRecordsFormByLeader.effectUtilityContent").getValue();
        if (document.getElementById("evaluateFormByLeader.statusAccept").checked == true
                && (dijit.byId("evaluationRecordsFormByLeader.legal").getValue() != 1
                        || dijit.byId("evaluationRecordsFormByLeader.foodSafetyQuality").getValue() != 1
                        || dijit.byId("evaluationRecordsFormByLeader.effectUtility").getValue() != 1)) {
            alert("Kết luân thẩm định và Nội dung thẩm định không đúng vui lòng kiểm tra lại!");
            return false;
        } else {
            if (document.getElementById("evaluateFormByLeader.statusDeny").checked) {
                if (staffRequest.trim().length == 0) {
                    alert("Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung");
                    dijit.byId("evaluateFormByLeader.staffRequest").focus();
                    return false;
                }
                if ((dijit.byId("evaluationRecordsFormByLeader.legal").getValue() == 0 || dijit.byId("evaluationRecordsFormByLeader.legal").getValue() == -1) && legalContent.trim().length == 0) {
                    alert("[Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)] chưa nhập lý do");
                    dijit.byId("evaluationRecordsFormByLeader.legalContent").focus();
                    return false;
                }

                if ((dijit.byId("evaluationRecordsFormByLeader.foodSafetyQuality").getValue() == 0 || dijit.byId("evaluationRecordsFormByLeader.foodSafetyQuality").getValue() == -1) && foodSafetyQualityContent.trim().length == 0) {
                    alert("[Về chỉ tiêu chất lượng an toàn thực phẩm] chưa nhập lý do");
                    dijit.byId("evaluationRecordsFormByLeader.foodSafetyQualityContent").focus();
                    return false;
                }
                if ((dijit.byId("evaluationRecordsFormByLeader.effectUtility").getValue() == 0 || dijit.byId("evaluationRecordsFormByLeader.effectUtility").getValue() == -1) && effectUtilityContent.trim().length == 0) {
                    alert("[Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng] chưa nhập lý do");
                    dijit.byId("evaluationRecordsFormByLeader.effectUtilityContent").focus();
                    return false;
                }
            }
            else {
                if (document.getElementById("evaluateFormByLeader.statusDenyCV").checked) {
                    if (staffRequest.trim().length == 0) {
                        alert("Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung");
                        dijit.byId("evaluateFormByLeader.staffRequest").focus();
                        return false;
                    }
                }
            }
        }        
        return true;
    };

    onCloseEvaluateLeader = function () {
        dijit.byId("evaluateFormByLeader.staffRequest").setValue("");
        document.getElementById("evaluateFormByLeader.statusAccept").checked = false;
        document.getElementById("evaluateFormByLeader.statusDeny").checked = false;

        dijit.byId("evaluateFormByLeader.effectiveDate").setValue(-1);
        dijit.byId("evaluationRecordsFormByLeader.legal").setValue(1);
        dijit.byId("evaluationRecordsFormByLeader.foodSafetyQuality").setValue(1);
        dijit.byId("evaluationRecordsFormByLeader.effectUtility").setValue(1);
        //dijit.byId("evaluationRecordsFormByLeader.leaderReviewId").setValue(-1);

        dijit.byId("evaluationRecordsFormByLeader.legalContent").setValue("");
        dijit.byId("evaluationRecordsFormByLeader.foodSafetyQualityContent").setValue("");
        dijit.byId("evaluationRecordsFormByLeader.effectUtilityContent").setValue("");
        dijit.byId("evaluateLeaderDlg").hide();
    };
    getStatusStaffEvaluate = function (status) {
        switch (status) {
            case 1:
                url = "Mới nộp";
                break;
            case 2:
                url = "Đã được đề xuất xử lý";
                break;
            case 3:
                url = "Đã phân công xử lý";
                break;
            case 4:
                url = "Đã thẩm định hồ sơ đạt";
                break;
            case 5:
                url = "Đã xem xét kết quả";
                break;
            case 6:
                url = "Đã phê duyệt kết quả";
                break;
            case 7:
                url = "Đã thẩm định yêu cầu SĐBS";
                break;
            case 8:
                url = "Đã trả lại để thẩm định lại";
                break;
            case 9:
                url = "Đã trả lại để xem xét lại";
                break;
            default:
                url = "Mới tạo";
        }
        return url;
    };

    page.clearCKEFBL = function () {
        try
        {
            localStorage.setItem("evaluateFormByLeader.evaluateFormByLeader.effectiveDate", "-1");
            localStorage.setItem("evaluateFormByLeader.evaluationRecordsFormByLeader.legal", "1");
            localStorage.setItem("evaluateFormByLeader.evaluationRecordsFormByLeader.foodSafetyQuality", "1");
            localStorage.setItem("evaluateFormByLeader.evaluationRecordsFormByLeader.effectUtility", "1");
            localStorage.setItem("evaluateFormByLeader.evaluateFormByLeader.staffRequest", "");
            localStorage.setItem("evaluateFormByLeader.evaluationRecordsFormByLeader.legalContent", "");
            localStorage.setItem("evaluateFormByLeader.evaluationRecordsFormByLeader.foodSafetyQualityContent", "");
            localStorage.setItem("evaluateFormByLeader.evaluationRecordsFormByLeader.effectUtilityContent", "");
            alert("Xóa nội dung thẩm định gần đây thành công!");
        }
        catch (err)
        {
            alert("Không thể Xóa nội dung thẩm định gần đây!");
        }
    };
    page.setCKEFBL = function () {
        try
        {
            localStorage.setItem("evaluateFormByLeader.evaluateFormByLeader.staffRequest", encodeBase64(dijit.byId("evaluateFormByLeader.staffRequest").getValue().toString().trim()));
            localStorage.setItem("evaluateFormByLeader.evaluateFormByLeader.effectiveDate", encodeBase64(dijit.byId("evaluateFormByLeader.effectiveDate").getValue().toString().trim()));
            localStorage.setItem("evaluateFormByLeader.evaluationRecordsFormByLeader.legal", encodeBase64(dijit.byId("evaluationRecordsFormByLeader.legal").getValue().toString().trim()));
            localStorage.setItem("evaluateFormByLeader.evaluationRecordsFormByLeader.foodSafetyQuality", encodeBase64(dijit.byId("evaluationRecordsFormByLeader.foodSafetyQuality").getValue().toString().trim()));
            localStorage.setItem("evaluateFormByLeader.evaluationRecordsFormByLeader.effectUtility", encodeBase64(dijit.byId("evaluationRecordsFormByLeader.effectUtility").getValue().toString().trim()));
            localStorage.setItem("evaluateFormByLeader.evaluationRecordsFormByLeader.legalContent", encodeBase64(dijit.byId("evaluationRecordsFormByLeader.legalContent").getValue().toString().trim()));
            localStorage.setItem("evaluateFormByLeader.evaluationRecordsFormByLeader.foodSafetyQualityContent", encodeBase64(dijit.byId("evaluationRecordsFormByLeader.foodSafetyQualityContent").getValue().toString().trim()));
            localStorage.setItem("evaluateFormByLeader.evaluationRecordsFormByLeader.effectUtilityContent", encodeBase64(dijit.byId("evaluationRecordsFormByLeader.effectUtilityContent").getValue().toString().trim()));
            alert("Lưu nháp nội dung thẩm định thành công!");
        }
        catch (err)
        {
            alert("Không thể Lưu nháp nội dung thẩm định!");
        }
    };
    page.getCKEFBL = function () {
        try
        {
            dijit.byId("evaluateFormByLeader.staffRequest").setValue(decodeBase64(localStorage.getItem("evaluateFormByLeader.evaluateFormByLeader.staffRequest")));
            dijit.byId("evaluateFormByLeader.effectiveDate").setValue(decodeBase64(localStorage.getItem("evaluateFormByLeader.evaluateFormByLeader.effectiveDate")));
            dijit.byId("evaluationRecordsFormByLeader.legal").setValue(decodeBase64(localStorage.getItem("evaluateFormByLeader.evaluationRecordsFormByLeader.legal")));
            dijit.byId("evaluationRecordsFormByLeader.foodSafetyQuality").setValue(decodeBase64(localStorage.getItem("evaluateFormByLeader.evaluationRecordsFormByLeader.foodSafetyQuality")));
            dijit.byId("evaluationRecordsFormByLeader.effectUtility").setValue(decodeBase64(localStorage.getItem("evaluateFormByLeader.evaluationRecordsFormByLeader.effectUtility")));
            dijit.byId("evaluationRecordsFormByLeader.legalContent").setValue(decodeBase64(localStorage.getItem("evaluateFormByLeader.evaluationRecordsFormByLeader.legalContent")));
            dijit.byId("evaluationRecordsFormByLeader.foodSafetyQualityContent").setValue(decodeBase64(localStorage.getItem("evaluateFormByLeader.evaluationRecordsFormByLeader.foodSafetyQualityContent")));
            dijit.byId("evaluationRecordsFormByLeader.effectUtilityContent").setValue(decodeBase64(localStorage.getItem("evaluateFormByLeader.evaluationRecordsFormByLeader.effectUtilityContent")));
            alert("Tải nội thẩm định gần đây thành công!");
        }
        catch (err)
        {
            alert("Không thể Tải nội thẩm định gần đây!");
        }
    };
</script>