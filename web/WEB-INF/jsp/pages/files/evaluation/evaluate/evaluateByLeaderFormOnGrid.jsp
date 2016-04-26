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
                        <form id="evaluateByLeaderFormOnGrid" name="createForm">
                            <table width="100%" class="viewTable">
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Kết quả chuyên viên thẩm định"/></td>
                                    <td width="70%">
                                        <div id="evaluateByLeaderFormOnGrid.statusL"></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Nội dung thẩm định"/></td>
                                    <td width="70%">
                                        <sd:TextBox key="" id="evaluateByLeaderFormOnGrid.fileId" 
                                                    name="createForm.fileId" 
                                                    cssStyle="display:none"/>
                                        <sd:TextBox key="" id="evaluateByLeaderFormOnGrid.status" 
                                                    name="createForm.status" 
                                                    cssStyle="display:none"/>
                                        <%-- <sd:TextBox key="" id="evaluateByLeaderFormOnGrid.ProductType" 
                                                     name="createForm.ProductType" cssStyle="display:none"/>--%>
                                        <input type="radio" id="evaluateByLeaderFormOnGrid.statusAccept" 
                                               name="createForm.status" value="4"/>
                                        <sd:Label key="Duyệt: Hồ sơ đạt"/>
                                        </br>
                                        <input type="radio" id="evaluateByLeaderFormOnGrid.statusDeny" 
                                               name="createForm.status" value="7"/>
                                        <sd:Label key="Duyệt: Yêu cầu bổ sung HS"/>
                                        </br>
                                        <input type="radio" 
                                               id="evaluateByLeaderFormOnGrid.statusDenyCV" 
                                               name="createForm.status" value="8"/>
                                        <sd:Label key="Yêu cầu: Chuyển hồ sơ cho chuyên viên thẩm định lại"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" id="evaluateByLeaderFormOnGrid.staffRequest" 
                                                     name="createForm.staffRequest" 
                                                     rows="5" cssStyle="width:98%" maxlength="2000" 
                                                     trim="true"/>
                                    </td>
                                </tr>
                                <div id="effectiveDateDiv" style="display:none">
                                    <tr>
                                        <td style="text-align: right"><sd:Label key="Thời hạn hiệu lực của giấy tiếp nhận"/></td>
                                        <td>
                                            <sd:SelectBox  cssStyle="width:98%"
                                                           id="evaluateByLeaderFormOnGrid.effectiveDate"
                                                           name="createForm.effectiveDate" key="" >
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
                                        <sd:SelectBox  cssStyle="width:98%"
                                                       id="evaluateByLeaderFormOnGrid.legal"
                                                       name="createForm.evaluationRecordsForm.legal"
                                                       key="" >
                                            <sd:Option value='1'>Đồng ý</sd:Option>
                                            <sd:Option value='0'>Không đồng ý</sd:Option>
                                            <sd:Option value='-1'>Bổ sung</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key=""
                                                     id="evaluateByLeaderFormOnGrid.legalContent"
                                                     name="createForm.evaluationRecordsForm.legalContent"
                                                     rows="5" cssStyle="width:98%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Về chỉ tiêu chất lượng an toàn thực phẩm"/></td>
                                    <td>
                                        <sd:SelectBox  cssStyle="width:98%"
                                                       id="evaluateByLeaderFormOnGrid.foodSafetyQuality"
                                                       name="createForm.evaluationRecordsForm.foodSafetyQuality" key="" >
                                            <sd:Option value='1'>Đồng ý</sd:Option>
                                            <sd:Option value='0'>Không đồng ý</sd:Option>
                                            <sd:Option value='-1'>Bổ sung</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" id="evaluateByLeaderFormOnGrid.foodSafetyQualityContent" 
                                                     name="createForm.evaluationRecordsForm.foodSafetyQualityContent" 
                                                     rows="5" cssStyle="width:98%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng"/></td>
                                    <td>
                                        <sd:SelectBox id="evaluateByLeaderFormOnGrid.effectUtility" 
                                                      name="createForm.evaluationRecordsForm.effectUtility" key=""  cssStyle="width:98%" >
                                            <sd:Option value='1'>Đồng ý</sd:Option>
                                            <sd:Option value='0'>Không đồng ý</sd:Option>
                                            <sd:Option value='-1'>Bổ sung</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" id="evaluateByLeaderFormOnGrid.effectUtilityContent" name="createForm.evaluationRecordsForm.effectUtilityContent" rows="5" cssStyle="width:98%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <tr>                    
                                    <td align="right">
                                        <sd:Label key="Chọn cán bộ xem xét"/>
                                    </td>
                                    <td>
                                        <sd:SelectBox  cssStyle="width:98%"
                                                       id="evaluateByLeaderFormOnGrid.leaderReviewId"
                                                       key="" data="lstLeaderOfStaff" valueField="userId" labelField="fullName"
                                                       name="createForm.leaderReviewId" >
                                        </sd:SelectBox>
                                        <sd:TextBox id="evaluateByLeaderFormOnGrid.leaderReviewName" name="createForm.leaderReviewName" cssStyle="display:none" key=""/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="text-align: center">
                                        <sx:ButtonSave onclick="onEvaluateByLeaderOnGrid();"/>
                                        <br>
                                        <sd:Button id="btnLoadCookieEFBLOG" key="" onclick="page.getCKEFBL();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Tải ND thẩm định gần đây</span>
                                        </sd:Button>
                                        <sd:Button id="btnSaveCookieEFBLOG" key="" onclick="page.setCKEFBL();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Lưu nháp ND thẩm định</span>
                                        </sd:Button>
                                        <sd:Button id="btnClearCookieEFBLOG" key="" onclick="page.clearCKEFBL();" cssStyle="display:" cssClass="buttonGroup">
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
                <sx:ButtonClose onclick="onCloseEvaluateByLeader();"/>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    onCloseEvaluateByLeader = function () {
        doGoToMenu("filesAction!toEvaluateLeaderPage.do");
    };

    onEvaluateByLeaderOnGrid = function () {
        if (validateLeaderOnGrid()) {
            sd.connector.post("filesAction!onEvaluate.do?" + token.getTokenParamString(), null, "evaluateByLeaderFormOnGrid", null, afterEvaluateByLeaderOnGrid);
        }
//        msg.confirm("Bạn có chắc chắn về kết quả thẩm định này không ?", "Thẩm định hồ sơ", afterOnEvaluateLeaderOnGrid1);
    };

//    afterOnEvaluateLeaderOnGrid1 = function () {
//        if (validateLeaderOnGrid()) {
//            sd.connector.post("filesAction!onEvaluate.do?" + token.getTokenParamString(), null, "evaluateByLeaderFormOnGrid", null, afterEvaluateLeaderOnGrid);
//        }
//    };

    afterEvaluateByLeaderOnGrid = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseEvaluateByLeader();
        }
    };

    validateLeaderOnGrid = function () {
        if (document.getElementById("evaluateByLeaderFormOnGrid.statusAccept").checked == false
                && document.getElementById("evaluateByLeaderFormOnGrid.statusDeny").checked == false
                && document.getElementById("evaluateByLeaderFormOnGrid.statusDenyCV").checked == false) {
            alert("Bạn chưa chọn [Kết quả thẩm định]");
            return false;
        }
        var effectiveDate = dijit.byId("evaluateByLeaderFormOnGrid.effectiveDate").getValue();
        if ((effectiveDate == null
                || effectiveDate.trim().length == -1
                || effectiveDate == "-1")
                && document.getElementById("evaluateByLeaderFormOnGrid.statusAccept").checked == true) {
            alert("Bạn chưa chọn thời hạn hiệu lực");
            dijit.byId("evaluateByLeaderFormOnGrid.effectiveDate").focus();
            return false;
        }
        var leaderReviewId = dijit.byId("evaluateByLeaderFormOnGrid.leaderReviewId").getValue();
        if (leaderReviewId == -1) {
            alert("Bạn chưa chọn lãnh đạo thực hiện");
            dijit.byId("evaluateByLeaderFormOnGrid.leaderReviewId").focus();
            return false;
        } else {
            var leaderReviewName = dijit.byId("evaluateByLeaderFormOnGrid.leaderReviewId").attr("displayedValue");
            dijit.byId("evaluateByLeaderFormOnGrid.leaderReviewName").setValue(leaderReviewName);
        }
        var staffRequest = dijit.byId("evaluateByLeaderFormOnGrid.staffRequest").getValue();
        var legalContent = dijit.byId("evaluateByLeaderFormOnGrid.legalContent").getValue();
        var foodSafetyQualityContent = dijit.byId("evaluateByLeaderFormOnGrid.foodSafetyQualityContent").getValue();
        var effectUtilityContent = dijit.byId("evaluateByLeaderFormOnGrid.effectUtilityContent").getValue();
        if (document.getElementById("evaluateByLeaderFormOnGrid.statusAccept").checked == true
                && (dijit.byId("evaluateByLeaderFormOnGrid.legal").getValue() != 1
                        || dijit.byId("evaluateByLeaderFormOnGrid.foodSafetyQuality").getValue() != 1
                        || dijit.byId("evaluateByLeaderFormOnGrid.effectUtility").getValue() != 1)) {
            alert("Kết luân thẩm định và Nội dung thẩm định không đúng vui lòng kiểm tra lại!");
            return false;
        } else {
            if (document.getElementById("evaluateByLeaderFormOnGrid.statusDeny").checked) {
                if (staffRequest.trim().length == 0) {
                    alert("Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung");
                    dijit.byId("evaluateByLeaderFormOnGrid.staffRequest").focus();
                    return false;
                }
                if ((dijit.byId("evaluateByLeaderFormOnGrid.legal").getValue() == 0
                        || dijit.byId("evaluateByLeaderFormOnGrid.legal").getValue() == -1)
                        && legalContent.trim().length == 0) {
                    alert("[Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)] chưa nhập lý do");
                    dijit.byId("evaluateByLeaderFormOnGrid.legalContent").focus();
                    return false;
                }

                if ((dijit.byId("evaluateByLeaderFormOnGrid.foodSafetyQuality").getValue() == 0
                        || dijit.byId("evaluateByLeaderFormOnGrid.foodSafetyQuality").getValue() == -1)
                        && foodSafetyQualityContent.trim().length == 0) {
                    alert("[Về chỉ tiêu chất lượng an toàn thực phẩm] chưa nhập lý do");
                    dijit.byId("evaluateByLeaderFormOnGrid.foodSafetyQualityContent").focus();
                    return false;
                }
                if ((dijit.byId("evaluateByLeaderFormOnGrid.effectUtility").getValue() == 0
                        || dijit.byId("evaluateByLeaderFormOnGrid.effectUtility").getValue() == -1)
                        && effectUtilityContent.trim().length == 0) {
                    alert("[Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng] chưa nhập lý do");
                    dijit.byId("evaluateByLeaderFormOnGrid.effectUtilityContent").focus();
                    return false;
                }
            }
            else {
                if (document.getElementById("evaluateByLeaderFormOnGrid.statusDenyCV").checked) {
                    if (staffRequest.trim().length == 0) {
                        alert("Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung");
                        dijit.byId("evaluateByLeaderFormOnGrid.staffRequest").focus();
                        return false;
                    }
                }
            }
        }
        return true;
    };

    onCloseEvaluateLeader = function () {
        dijit.byId("evaluateByLeaderFormOnGrid.staffRequest").setValue("");
        document.getElementById("evaluateByLeaderFormOnGrid.statusAccept").checked = false;
        document.getElementById("evaluateByLeaderFormOnGrid.statusDeny").checked = false;

        dijit.byId("evaluateByLeaderFormOnGrid.effectiveDate").setValue(-1);
        dijit.byId("evaluateByLeaderFormOnGrid.legal").setValue(1);
        dijit.byId("evaluateByLeaderFormOnGrid.foodSafetyQuality").setValue(1);
        dijit.byId("evaluateByLeaderFormOnGrid.effectUtility").setValue(1);
        //dijit.byId("evaluateByLeaderFormOnGrid.leaderReviewId").setValue(-1);

        dijit.byId("evaluateByLeaderFormOnGrid.legalContent").setValue("");
        dijit.byId("evaluateByLeaderFormOnGrid.foodSafetyQualityContent").setValue("");
        dijit.byId("evaluateByLeaderFormOnGrid.effectUtilityContent").setValue("");
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
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.effectiveDate", "-1");
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.legal", "1");
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.foodSafetyQuality", "1");
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.effectUtility", "1");
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.staffRequest", "");
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.legalContent", "");
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.foodSafetyQualityContent", "");
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.effectUtilityContent", "");
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
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.staffRequest", encodeBase64(dijit.byId("evaluateByLeaderFormOnGrid.staffRequest").getValue().toString().trim()));
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.effectiveDate", encodeBase64(dijit.byId("evaluateByLeaderFormOnGrid.effectiveDate").getValue().toString().trim()));
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.legal", encodeBase64(dijit.byId("evaluateByLeaderFormOnGrid.legal").getValue().toString().trim()));
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.foodSafetyQuality", encodeBase64(dijit.byId("evaluateByLeaderFormOnGrid.foodSafetyQuality").getValue().toString().trim()));
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.effectUtility", encodeBase64(dijit.byId("evaluateByLeaderFormOnGrid.effectUtility").getValue().toString().trim()));
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.legalContent", encodeBase64(dijit.byId("evaluateByLeaderFormOnGrid.legalContent").getValue().toString().trim()));
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.foodSafetyQualityContent", encodeBase64(dijit.byId("evaluateByLeaderFormOnGrid.foodSafetyQualityContent").getValue().toString().trim()));
            localStorage.setItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.effectUtilityContent", encodeBase64(dijit.byId("evaluateByLeaderFormOnGrid.effectUtilityContent").getValue().toString().trim()));
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
            dijit.byId("evaluateByLeaderFormOnGrid.staffRequest").setValue(decodeBase64(localStorage.getItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.staffRequest")));
            dijit.byId("evaluateByLeaderFormOnGrid.effectiveDate").setValue(decodeBase64(localStorage.getItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.effectiveDate")));
            dijit.byId("evaluateByLeaderFormOnGrid.legal").setValue(decodeBase64(localStorage.getItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.legal")));
            dijit.byId("evaluateByLeaderFormOnGrid.foodSafetyQuality").setValue(decodeBase64(localStorage.getItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.foodSafetyQuality")));
            dijit.byId("evaluateByLeaderFormOnGrid.effectUtility").setValue(decodeBase64(localStorage.getItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.effectUtility")));
            dijit.byId("evaluateByLeaderFormOnGrid.legalContent").setValue(decodeBase64(localStorage.getItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.legalContent")));
            dijit.byId("evaluateByLeaderFormOnGrid.foodSafetyQualityContent").setValue(decodeBase64(localStorage.getItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.foodSafetyQualityContent")));
            dijit.byId("evaluateByLeaderFormOnGrid.effectUtilityContent").setValue(decodeBase64(localStorage.getItem("evaluateByLeaderFormOnGrid.evaluateByLeaderFormOnGrid.effectUtilityContent")));
            alert("Tải nội thẩm định gần đây thành công!");
        }
        catch (err)
        {
            alert("Không thể Tải nội thẩm định gần đây!");
        }
    };
</script>