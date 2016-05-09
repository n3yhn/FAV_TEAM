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
                        <form id="evaluationRecordsFormOnGrid" name="createForm">
                            <table width="100%" class="viewTable">
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Kết quả chuyên viên thẩm định"/></td>
                                    <td width="70%">
                                        <div id="evaluationRecordsFormOnGrid.statusL"></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Kết quả thẩm định"/></td>
                                    <td width="70%">
                                        <sd:TextBox key="" 
                                                    id="evaluationRecordsFormOnGrid.fileId" 
                                                    name="createForm.fileId" cssStyle="display:none"/>
                                        <%--<sd:TextBox key="" id="evaluationRecordsFormOnGrid.ProductType" name="createForm.ProductType" cssStyle="display:none"/>--%>
                                        
                                        <sd:TextBox key="" 
                                                    id="evaluationRecordsFormOnGrid.status" 
                                                    name="createForm.status" cssStyle="display:none"/>
                                        
                                        <input type="radio" 
                                               id="evaluationRecordsFormOnGrid.statusAccept" 
                                               name="createForm.status" value="4"/>
                                        <sd:Label key="Duyệt: Hồ sơ đạt"/>
                                        </br>
                                        <input type="radio" 
                                               id="evaluationRecordsFormOnGrid.statusDeny" 
                                               name="createForm.status" value="7"/>
                                        <sd:Label key="Duyệt: Yêu cầu bổ sung HS"/>
                                        </br>
                                        <input type="radio" 
                                               id="evaluationRecordsFormOnGrid.statusDenyCV" 
                                               name="createForm.status" value="8"/>
                                        <sd:Label key="Yêu cầu: Chuyển hồ sơ cho chuyên viên thẩm định lại"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" 
                                                     id="evaluationRecordsFormOnGrid.staffRequest" 
                                                     name="createForm.staffRequest" 
                                                     rows="5" cssStyle="width:98%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <div id="effectiveDateDiv" style="display:none">
                                    <tr>
                                        <td style="text-align: right"><sd:Label key="Thời hạn hiệu lực của giấy tiếp nhận"/></td>
                                        <td>
                                            <sd:SelectBox  cssStyle="width:98%" 
                                                           id="evaluationRecordsFormOnGrid.effectiveDate" 
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
                                                       id="evaluationRecordsFormOnGrid.legal" 
                                                       name="createForm.evaluationRecordsFormOnGrid.legal" key="" >
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
                                                     id="evaluationRecordsFormOnGrid.legalContent" 
                                                     name="createForm.evaluationRecordsFormOnGrid.legalContent" 
                                                     rows="5" cssStyle="width:98%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Về chỉ tiêu chất lượng an toàn thực phẩm"/></td>
                                    <td>
                                        <sd:SelectBox  cssStyle="width:98%" 
                                                       id="evaluationRecordsFormOnGrid.foodSafetyQuality" 
                                                       name="createForm.evaluationRecordsFormOnGrid.foodSafetyQuality" key="" >
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
                                                     id="evaluationRecordsFormOnGrid.foodSafetyQualityContent" 
                                                     name="createForm.evaluationRecordsFormOnGrid.foodSafetyQualityContent" 
                                                     rows="5" cssStyle="width:98%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng"/></td>
                                    <td>
                                        <sd:SelectBox 
                                            id="evaluationRecordsFormOnGrid.effectUtility" 
                                            name="createForm.evaluationRecordsFormOnGrid.effectUtility" 
                                            key=""  cssStyle="width:98%" >
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
                                                     id="evaluationRecordsFormOnGrid.effectUtilityContent" 
                                                     name="createForm.evaluationRecordsFormOnGrid.effectUtilityContent" 
                                                     rows="5" cssStyle="width:98%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <tr>                    
                                    <td align="right">
                                        <sd:Label key="Chọn cán bộ xem xét"/>
                                    </td>
                                    <td>
                                        <sd:SelectBox  cssStyle="width:98%"
                                                       id="evaluationRecordsFormOnGrid.leaderReviewId"
                                                       key="" data="lstLeaderOfStaff" valueField="userId" labelField="fullName"
                                                       name="createForm.leaderReviewId" >
                                        </sd:SelectBox>
                                        <sd:TextBox 
                                            id="evaluationRecordsFormOnGrid.leaderReviewName" 
                                            name="createForm.leaderReviewName" 
                                            cssStyle="display:none" key=""/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="text-align: center">
                                        <sx:ButtonSave onclick="onEvaluateByLeaderFormOnGrid();"/>
                                        <sx:ButtonClose onclick="onCloseEvaluateLeader();"/>
                                        <br>
                                        <sd:Button id="btnLoadCookieEFBL" key="" onclick="page.getCKEFBLOG();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Tải ND thẩm định gần đây</span>
                                        </sd:Button>
                                        <sd:Button id="btnSaveCookieEFBL" key="" onclick="page.setCKEFBLOG();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Lưu nháp ND thẩm định</span>
                                        </sd:Button>
                                        <sd:Button id="btnClearCookieEFBL" key="" onclick="page.clearCKEFBLOG();" cssStyle="display:" cssClass="buttonGroup">
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
    onEvaluateByLeaderFormOnGrid = function () {
        msg.confirm("Bạn có chắc chắn về kết quả thẩm định này không ?", "Thẩm định hồ sơ", onSaveEvaluateByLeaderFormOnGrid);
    };
    onSaveEvaluateByLeaderFormOnGrid = function () {
        if (validateEBLFG()) {            
            sd.connector.post("filesAction!onEvaluate.do?" + token.getTokenParamString(), null, "createForm", null, afterEvaluateByLeaderOnGrid);
        }
    };

    afterEvaluateByLeaderOnGrid = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseEvaluateByLeader();
        }
    };

    validateEBLFG = function () {
        if (document.getElementById("evaluationRecordsFormOnGrid.statusAccept").checked == false
                && document.getElementById("evaluationRecordsFormOnGrid.statusDeny").checked == false
                && document.getElementById("evaluationRecordsFormOnGrid.statusDenyCV").checked == false) {
            alert("Bạn chưa chọn [Kết quả thẩm định]");
            return false;
        }
        var effectiveDate = dijit.byId("evaluationRecordsFormOnGrid.effectiveDate").getValue();
        if ((effectiveDate == null
                || effectiveDate.trim().length == -1
                || effectiveDate == "-1")
                && document.getElementById("evaluationRecordsFormOnGrid.statusAccept").checked == true) {
            alert("Bạn chưa chọn thời hạn hiệu lực");
            dijit.byId("evaluationRecordsFormOnGrid.effectiveDate").focus();
            return false;
        }
        var leaderReviewId = dijit.byId("evaluationRecordsFormOnGrid.leaderReviewId").getValue();
        if (leaderReviewId == -1) {
            alert("Bạn chưa chọn lãnh đạo thực hiện");
            dijit.byId("evaluationRecordsFormOnGrid.leaderReviewId").focus();
            return false;
        } else {
            var leaderReviewName = dijit.byId("evaluationRecordsFormOnGrid.leaderReviewId").attr("displayedValue");
            dijit.byId("evaluationRecordsFormOnGrid.leaderReviewName").setValue(leaderReviewName);
        }
        var staffRequest = dijit.byId("evaluationRecordsFormOnGrid.staffRequest").getValue();
        var legalContent = dijit.byId("evaluationRecordsFormOnGrid.legalContent").getValue();
        var foodSafetyQualityContent = dijit.byId("evaluationRecordsFormOnGrid.foodSafetyQualityContent").getValue();
        var effectUtilityContent = dijit.byId("evaluationRecordsFormOnGrid.effectUtilityContent").getValue();
        if (document.getElementById("evaluationRecordsFormOnGrid.statusAccept").checked == true
                && (dijit.byId("evaluationRecordsFormOnGrid.legal").getValue() != 1
                        || dijit.byId("evaluationRecordsFormOnGrid.foodSafetyQuality").getValue() != 1
                        || dijit.byId("evaluationRecordsFormOnGrid.effectUtility").getValue() != 1)) {
            alert("Kết luân thẩm định và Nội dung thẩm định không đúng vui lòng kiểm tra lại!");
            return false;
        } else {
            if (document.getElementById("evaluationRecordsFormOnGrid.statusDeny").checked) {
                if (staffRequest.trim().length == 0) {
                    alert("Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung");
                    dijit.byId("evaluationRecordsFormOnGrid.staffRequest").focus();
                    return false;
                }
                if ((dijit.byId("evaluationRecordsFormOnGrid.legal").getValue() == 0
                        || dijit.byId("evaluationRecordsFormOnGrid.legal").getValue() == -1)
                        && legalContent.trim().length == 0) {
                    alert("[Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)] chưa nhập lý do");
                    dijit.byId("evaluationRecordsFormOnGrid.legalContent").focus();
                    return false;
                }
                if ((dijit.byId("evaluationRecordsFormOnGrid.foodSafetyQuality").getValue() == 0
                        || dijit.byId("evaluationRecordsFormOnGrid.foodSafetyQuality").getValue() == -1)
                        && foodSafetyQualityContent.trim().length == 0) {
                    alert("[Về chỉ tiêu chất lượng an toàn thực phẩm] chưa nhập lý do");
                    dijit.byId("evaluationRecordsFormOnGrid.foodSafetyQualityContent").focus();
                    return false;
                }
                if ((dijit.byId("evaluationRecordsFormOnGrid.effectUtility").getValue() == 0
                        || dijit.byId("evaluationRecordsFormOnGrid.effectUtility").getValue() == -1)
                        && effectUtilityContent.trim().length == 0) {
                    alert("[Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng] chưa nhập lý do");
                    dijit.byId("evaluationRecordsFormOnGrid.effectUtilityContent").focus();
                    return false;
                }
            }
            else {
                if (document.getElementById("evaluationRecordsFormOnGrid.statusDenyCV").checked) {
                    if (staffRequest.trim().length == 0) {
                        alert("Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung");
                        dijit.byId("evaluationRecordsFormOnGrid.staffRequest").focus();
                        return false;
                    }
                }
            }
        }
        return true;
    };

    page.clearCKEFBLOG = function () {
        try
        {
            localStorage.setItem("evaluationRecordsFormOnGrid.effectiveDate", "-1");
            localStorage.setItem("evaluationRecordsFormOnGrid.legal", "1");
            localStorage.setItem("evaluationRecordsFormOnGrid.foodSafetyQuality", "1");
            localStorage.setItem("evaluationRecordsFormOnGrid.effectUtility", "1");
            localStorage.setItem("evaluationRecordsFormOnGrid.staffRequest", "");
            localStorage.setItem("evaluationRecordsFormOnGrid.legalContent", "");
            localStorage.setItem("evaluationRecordsFormOnGrid.foodSafetyQualityContent", "");
            localStorage.setItem("evaluationRecordsFormOnGrid.effectUtilityContent", "");
            alert("Xóa nội dung thẩm định gần đây thành công!");
        }
        catch (err)
        {
            alert("Không thể Xóa nội dung thẩm định gần đây!");
        }
    };

    page.setCKEFBLOG = function () {
        try
        {
            localStorage.setItem("evaluationRecordsFormOnGrid.staffRequest", encodeBase64(dijit.byId("evaluationRecordsFormOnGrid.staffRequest").getValue().toString().trim()));
            localStorage.setItem("evaluationRecordsFormOnGrid.effectiveDate", encodeBase64(dijit.byId("evaluationRecordsFormOnGrid.effectiveDate").getValue().toString().trim()));
            localStorage.setItem("evaluationRecordsFormOnGrid.legal", encodeBase64(dijit.byId("evaluationRecordsFormOnGrid.legal").getValue().toString().trim()));
            localStorage.setItem("evaluationRecordsFormOnGrid.foodSafetyQuality", encodeBase64(dijit.byId("evaluationRecordsFormOnGrid.foodSafetyQuality").getValue().toString().trim()));
            localStorage.setItem("evaluationRecordsFormOnGrid.effectUtility", encodeBase64(dijit.byId("evaluationRecordsFormOnGrid.effectUtility").getValue().toString().trim()));
            localStorage.setItem("evaluationRecordsFormOnGrid.legalContent", encodeBase64(dijit.byId("evaluationRecordsFormOnGrid.legalContent").getValue().toString().trim()));
            localStorage.setItem("evaluationRecordsFormOnGrid.foodSafetyQualityContent", encodeBase64(dijit.byId("evaluationRecordsFormOnGrid.foodSafetyQualityContent").getValue().toString().trim()));
            localStorage.setItem("evaluationRecordsFormOnGrid.effectUtilityContent", encodeBase64(dijit.byId("evaluationRecordsFormOnGrid.effectUtilityContent").getValue().toString().trim()));
            alert("Lưu nháp nội dung thẩm định thành công!");
        }
        catch (err)
        {
            alert("Không thể Lưu nháp nội dung thẩm định!");
        }
    };

    page.getCKEFBLOG = function () {
        try
        {
            dijit.byId("evaluationRecordsFormOnGrid.staffRequest").setValue(decodeBase64(localStorage.getItem("evaluationRecordsFormOnGrid.staffRequest")));
            dijit.byId("evaluationRecordsFormOnGrid.effectiveDate").setValue(decodeBase64(localStorage.getItem("evaluationRecordsFormOnGrid.effectiveDate")));
            dijit.byId("evaluationRecordsFormOnGrid.legal").setValue(decodeBase64(localStorage.getItem("evaluationRecordsFormOnGrid.legal")));
            dijit.byId("evaluationRecordsFormOnGrid.foodSafetyQuality").setValue(decodeBase64(localStorage.getItem("evaluationRecordsFormOnGrid.foodSafetyQuality")));
            dijit.byId("evaluationRecordsFormOnGrid.effectUtility").setValue(decodeBase64(localStorage.getItem("evaluationRecordsFormOnGrid.effectUtility")));
            dijit.byId("evaluationRecordsFormOnGrid.legalContent").setValue(decodeBase64(localStorage.getItem("evaluationRecordsFormOnGrid.legalContent")));
            dijit.byId("evaluationRecordsFormOnGrid.foodSafetyQualityContent").setValue(decodeBase64(localStorage.getItem("evaluationRecordsFormOnGrid.foodSafetyQualityContent")));
            dijit.byId("evaluationRecordsFormOnGrid.effectUtilityContent").setValue(decodeBase64(localStorage.getItem("evaluationRecordsFormOnGrid.effectUtilityContent")));
            alert("Tải nội thẩm định gần đây thành công!");
        }
        catch (err)
        {
            alert("Không thể Tải nội thẩm định gần đây!");
        }
    };
</script>