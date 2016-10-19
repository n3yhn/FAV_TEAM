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
                <sd:TitlePane key="Lãnh đạo phòng Kết luận thẩm định" id="titlePaneEvaluate">
                    <div style="overflow-y: auto;max-height: 600px">
                        <form id="evaluationRecord" name="createForm">
                            <table width="100%" class="viewTable">
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Kết quả chuyên viên thẩm định"/></td>
                                    <td width="70%">
                                        <div id="evaluationRecord.statusL"></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right"><sd:Label key="Kết quả thẩm định"/></td>
                                    <td width="70%">
                                        <sd:TextBox key="" 
                                                    id="evaluationRecord.fileId" 
                                                    name="createForm.fileId" cssStyle="display:none"/>

                                        <sd:TextBox key="" 
                                                    id="evaluationRecord.status" 
                                                    name="evaluationRecord.status" cssStyle="display:none"/>

                                        <input type="radio" 
                                               id="evaluationRecord.statusAccept" 
                                               name="createForm.status" value="4"
                                               onchange="onchangeStatusEBLFOG();"/>
                                        <sd:Label key="Duyệt: Hồ sơ đạt"/>
                                        </br>
                                        <input type ="radio" 
                                               id="evaluationRecord.statusDeny" 
                                               name="createForm.status" value="7"
                                               onchange="onchangeStatusEBLFOG();"/>
                                        <sd:Label key="Duyệt: Yêu cầu bổ sung HS"/>
                                        </br>
                                        <input type="radio" 
                                               id="evaluationRecord.statusDenyCV" 
                                               name="createForm.status" value="8"
                                               onchange="onchangeStatusEBLFOG();"/>
                                        <sd:Label key="Yêu cầu: Chuyển hồ sơ cho chuyên viên thẩm định lại"/>
                                    </td>
                                </tr>

                                <tr id="trTitleEditATTP" style="display:">
                                    <td style="text-align: right"><sd:Label key="Tiêu đề trả lời công văn BS sau công bố"/></td>
                                    <td>
                                        <sd:Textarea key=""
                                                     id="evaluationRecord.titleEditATTP"
                                                     name="createForm.titleEditATTP"
                                                     rows="1" cssStyle="width:99%"
                                                     maxlength="255" trim="true"/>
                                    </td>
                                </tr>
                                <tr id="trContentsEditATTP" style="display:">
                                    <td style="text-align: right"><sd:Label key="Nội dung trả lời công văn BS sau công bố"/></td>
                                    <td>
                                        <sd:Textarea key=""
                                                     id="evaluationRecord.contentsEditATTP"
                                                     name="createForm.contentsEditATTP"
                                                     rows="10" cssStyle="width:99%"
                                                     maxlength="2000" trim="true"/>
                                    </td>
                                </tr>

                                <tr id="trStaffRequest" style="display:none">
                                    <td style="text-align: right">
                                        <sd:Label key="Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung"/>
                                    </td>
                                    <td>
                                        <sd:Textarea key="" 
                                                     id="evaluationRecord.staffRequest" 
                                                     name="createForm.staffRequest" 
                                                     rows="5" cssStyle="width:98%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <div id="effectiveDateDiv" style="display:none">
                                    <tr id="trEffectiveDate" style="display:none">
                                        <td style="text-align: right"><sd:Label key="Thời hạn hiệu lực của giấy tiếp nhận"/></td>
                                        <td>
                                            <sd:SelectBox  cssStyle="width:98%" 
                                                           id="evaluationRecord.effectiveDate" 
                                                           name="createForm.effectiveDate" key="" >
                                                <sd:Option value='-1' selected="true">--Chọn--</sd:Option>
                                                <sd:Option value='0'>Không thời hạn</sd:Option>
                                                <sd:Option value='3'>3 năm</sd:Option>
                                                <sd:Option value='5'>5 năm</sd:Option>
                                            </sd:SelectBox>
                                        </td>
                                    </tr>
                                </div>
                                <tr id="trLegal" style="display:none">
                                    <td style="text-align: right"><sd:Label key="Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)"/></td>
                                    <td>
                                        <sd:SelectBox  cssStyle="width:98%" 
                                                       id="evaluationRecord.legal" 
                                                       name="createForm.evaluationRecordsFormOnGrid.legal" key="" >
                                            <sd:Option value='1'>Đồng ý</sd:Option>
                                            <sd:Option value='0'>Không đồng ý</sd:Option>
                                            <sd:Option value='-1'>Bổ sung</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr id="trLegalContent" style="display:none">
                                    <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" 
                                                     id="evaluationRecord.legalContent" 
                                                     name="createForm.evaluationRecordsFormOnGrid.legalContent" 
                                                     rows="5" cssStyle="width:98%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <tr id="trFoodSafetyQuality" style="display:none">
                                    <td style="text-align: right"><sd:Label key="Về chỉ tiêu chất lượng an toàn thực phẩm"/></td>
                                    <td>
                                        <sd:SelectBox  cssStyle="width:98%" 
                                                       id="evaluationRecord.foodSafetyQuality" 
                                                       name="createForm.evaluationRecordsFormOnGrid.foodSafetyQuality" key="" >
                                            <sd:Option value='1'>Đồng ý</sd:Option>
                                            <sd:Option value='0'>Không đồng ý</sd:Option>
                                            <sd:Option value='-1'>Bổ sung</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr id="trFoodSafetyQualityContent" style="display:none">
                                    <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" 
                                                     id="evaluationRecord.foodSafetyQualityContent" 
                                                     name="createForm.evaluationRecordsFormOnGrid.foodSafetyQualityContent" 
                                                     rows="5" cssStyle="width:98%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <tr id="trEffectUtility" style="display:none">
                                    <td style="text-align: right"><sd:Label key="Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng"/></td>
                                    <td>
                                        <sd:SelectBox 
                                            id="evaluationRecord.effectUtility" 
                                            name="createForm.evaluationRecordsFormOnGrid.effectUtility" 
                                            key=""  cssStyle="width:98%" >
                                            <sd:Option value='1'>Đồng ý</sd:Option>
                                            <sd:Option value='0'>Không đồng ý</sd:Option>
                                            <sd:Option value='-1'>Bổ sung</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr id="trEffectUtilityContent" style="display:none">
                                    <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" 
                                                     id="evaluationRecord.effectUtilityContent" 
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
                                                       id="evaluationRecord.leaderReviewId"
                                                       key="" data="lstLeaderOfStaff" valueField="userId" labelField="fullName"
                                                       name="createForm.leaderReviewId" >
                                        </sd:SelectBox>
                                        <sd:TextBox 
                                            id="evaluationRecord.leaderReviewName" 
                                            name="createForm.leaderReviewName" 
                                            cssStyle="display:none" key=""/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="text-align: center">
                                        <sx:ButtonSave onclick="onEvaluateByLeaderFormOnGrid();"/>
                                        <sd:Button id="btnExportEBLFOGAA" key="" onclick="page.exportExportEBLFOGAA();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Xem trước CV phúc đáp</span>
                                        </sd:Button>
                                        <sd:Button id="btnExportFBRF1" key="" onclick="page.downloadFBRFAA();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Xem trước CV y/c bổ sung</span>
                                        </sd:Button>
                                        <sx:ButtonClose onclick="onCloseEvaluateLeader();"/>
                                        <br>
                                        <sd:Button id="btnLoadCookieEFBLOG" key="" onclick="page.getCKEFBLOG();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Tải ND thẩm định gần đây</span>
                                        </sd:Button>
                                        <sd:Button id="btnSaveCookieEFBLOG" key="" onclick="page.setCKEFBLOG();" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Lưu nháp ND thẩm định</span>
                                        </sd:Button>
                                        <sd:Button id="btnClearCookieEFBLOG" key="" onclick="page.clearCKEFBLOG();" cssStyle="display:" cssClass="buttonGroup">
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
            sd.connector.post("filesAction!onEvaluate.do?" + token.getTokenParamString(), null, "evaluationRecord", null, afterEvaluateByLeaderOnGrid);
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
        if (document.getElementById("evaluationRecord.statusAccept").checked == false
                && document.getElementById("evaluationRecord.statusDeny").checked == false
                && document.getElementById("evaluationRecord.statusDenyCV").checked == false) {
            alert("Bạn chưa chọn [Kết quả thẩm định]");
            return false;
        }
        var effectiveDate = dijit.byId("evaluationRecord.effectiveDate").getValue();
//        if ((effectiveDate == null
//                || effectiveDate.trim().length == -1
//                || effectiveDate == "-1")
//                && document.getElementById("evaluationRecord.statusAccept").checked == true) {
//            alert("Bạn chưa chọn thời hạn hiệu lực");
//            dijit.byId("evaluationRecord.effectiveDate").focus();
//            return false;
//        }
        if (document.getElementById("evaluationRecord.statusAccept").checked) {
            var titleEditATTP = dijit.byId("evaluationRecord.titleEditATTP").getValue();
            if (titleEditATTP.trim().length == 0) {
                alert("Nội dung tiêu đề công văn không được để trống");
                dijit.byId("evaluationRecord.titleEditATTP").focus();
                return false;
            }
            var contentsEditATTP = dijit.byId("evaluationRecord.contentsEditATTP").getValue();
            if (contentsEditATTP.trim().length == 0) {
                alert("Nội dung công văn không được để trống");
                dijit.byId("evaluationRecord.contentsEditATTP").focus();
                return false;
            }
        }
        var leaderReviewId = dijit.byId("evaluationRecord.leaderReviewId").getValue();
        if (leaderReviewId == -1 && document.getElementById("evaluationRecord.statusDenyCV").checked == false) {
            alert("Bạn chưa chọn lãnh đạo thực hiện");
            dijit.byId("evaluationRecord.leaderReviewId").focus();
            return false;
        } else {
            var leaderReviewName = dijit.byId("evaluationRecord.leaderReviewId").attr("displayedValue");
            dijit.byId("evaluationRecord.leaderReviewName").setValue(leaderReviewName);
        }
        var staffRequest = dijit.byId("evaluationRecord.staffRequest").getValue();
        var legalContent = dijit.byId("evaluationRecord.legalContent").getValue();
        var foodSafetyQualityContent = dijit.byId("evaluationRecord.foodSafetyQualityContent").getValue();
        var effectUtilityContent = dijit.byId("evaluationRecord.effectUtilityContent").getValue();
        if (document.getElementById("evaluationRecord.statusAccept").checked == true
                && (dijit.byId("evaluationRecord.legal").getValue() != 1
                        || dijit.byId("evaluationRecord.foodSafetyQuality").getValue() != 1
                        || dijit.byId("evaluationRecord.effectUtility").getValue() != 1)) {
            alert("Kết luân thẩm định và Nội dung thẩm định không đúng vui lòng kiểm tra lại!");
            return false;
        } else {
            if (document.getElementById("evaluationRecord.statusDeny").checked) {
                if (staffRequest.trim().length == 0) {
                    alert("Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung");
                    dijit.byId("evaluationRecord.staffRequest").focus();
                    return false;
                }
                if ((dijit.byId("evaluationRecord.legal").getValue() == 0
                        || dijit.byId("evaluationRecord.legal").getValue() == -1)
                        && legalContent.trim().length == 0) {
                    alert("[Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)] chưa nhập lý do");
                    dijit.byId("evaluationRecord.legalContent").focus();
                    return false;
                }
                if ((dijit.byId("evaluationRecord.foodSafetyQuality").getValue() == 0
                        || dijit.byId("evaluationRecord.foodSafetyQuality").getValue() == -1)
                        && foodSafetyQualityContent.trim().length == 0) {
                    alert("[Về chỉ tiêu chất lượng an toàn thực phẩm] chưa nhập lý do");
                    dijit.byId("evaluationRecord.foodSafetyQualityContent").focus();
                    return false;
                }
                if ((dijit.byId("evaluationRecord.effectUtility").getValue() == 0
                        || dijit.byId("evaluationRecord.effectUtility").getValue() == -1)
                        && effectUtilityContent.trim().length == 0) {
                    alert("[Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng] chưa nhập lý do");
                    dijit.byId("evaluationRecord.effectUtilityContent").focus();
                    return false;
                }
            } else {
                if (document.getElementById("evaluationRecord.statusDenyCV").checked) {
                    if (staffRequest.trim().length == 0) {
                        alert("Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung");
                        dijit.byId("evaluationRecord.staffRequest").focus();
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
            localStorage.setItem("evaluationRecord.effectiveDate", "-1");
            localStorage.setItem("evaluationRecord.legal", "1");
            localStorage.setItem("evaluationRecord.foodSafetyQuality", "1");
            localStorage.setItem("evaluationRecord.effectUtility", "1");
            localStorage.setItem("evaluationRecord.staffRequest", "");
            localStorage.setItem("evaluationRecord.legalContent", "");
            localStorage.setItem("evaluationRecord.foodSafetyQualityContent", "");
            localStorage.setItem("evaluationRecord.effectUtilityContent", "");
            alert("Xóa nội dung thẩm định gần đây thành công!");
        } catch (err)
        {
            alert("Không thể Xóa nội dung thẩm định gần đây!");
        }
    };
    page.setCKEFBLOG = function () {
        try
        {
            localStorage.setItem("evaluationRecord.staffRequest", encodeBase64(dijit.byId("evaluationRecord.staffRequest").getValue().toString().trim()));
            localStorage.setItem("evaluationRecord.effectiveDate", encodeBase64(dijit.byId("evaluationRecord.effectiveDate").getValue().toString().trim()));
            localStorage.setItem("evaluationRecord.legal", encodeBase64(dijit.byId("evaluationRecord.legal").getValue().toString().trim()));
            localStorage.setItem("evaluationRecord.foodSafetyQuality", encodeBase64(dijit.byId("evaluationRecord.foodSafetyQuality").getValue().toString().trim()));
            localStorage.setItem("evaluationRecord.effectUtility", encodeBase64(dijit.byId("evaluationRecord.effectUtility").getValue().toString().trim()));
            localStorage.setItem("evaluationRecord.legalContent", encodeBase64(dijit.byId("evaluationRecord.legalContent").getValue().toString().trim()));
            localStorage.setItem("evaluationRecord.foodSafetyQualityContent", encodeBase64(dijit.byId("evaluationRecord.foodSafetyQualityContent").getValue().toString().trim()));
            localStorage.setItem("evaluationRecord.effectUtilityContent", encodeBase64(dijit.byId("evaluationRecord.effectUtilityContent").getValue().toString().trim()));
            alert("Lưu nháp nội dung thẩm định thành công!");
        } catch (err)
        {
            alert("Không thể Lưu nháp nội dung thẩm định!");
        }
    };
    page.getCKEFBLOG = function () {
        try
        {
            dijit.byId("evaluationRecord.staffRequest").setValue(decodeBase64(localStorage.getItem("evaluationRecord.staffRequest")));
            dijit.byId("evaluationRecord.effectiveDate").setValue(decodeBase64(localStorage.getItem("evaluationRecord.effectiveDate")));
            dijit.byId("evaluationRecord.legal").setValue(decodeBase64(localStorage.getItem("evaluationRecord.legal")));
            dijit.byId("evaluationRecord.foodSafetyQuality").setValue(decodeBase64(localStorage.getItem("evaluationRecord.foodSafetyQuality")));
            dijit.byId("evaluationRecord.effectUtility").setValue(decodeBase64(localStorage.getItem("evaluationRecord.effectUtility")));
            dijit.byId("evaluationRecord.legalContent").setValue(decodeBase64(localStorage.getItem("evaluationRecord.legalContent")));
            dijit.byId("evaluationRecord.foodSafetyQualityContent").setValue(decodeBase64(localStorage.getItem("evaluationRecord.foodSafetyQualityContent")));
            dijit.byId("evaluationRecord.effectUtilityContent").setValue(decodeBase64(localStorage.getItem("evaluationRecord.effectUtilityContent")));
            alert("Tải nội thẩm định gần đây thành công!");
        } catch (err)
        {
            alert("Không thể Tải nội thẩm định gần đây!");
        }
    };
    onchangeStatusEBLFOG = function () {
        var trTitleEditATTP = document.getElementById('trTitleEditATTP');
        var trContentsEditATTP = document.getElementById('trContentsEditATTP');
        var trStaffRequest = document.getElementById('trStaffRequest');
        var trEffectiveDate = document.getElementById('trEffectiveDate');
        var trLegal = document.getElementById('trLegal');
        var trLegalContent = document.getElementById('trLegalContent');
        var trFoodSafetyQuality = document.getElementById('trFoodSafetyQuality');
        var trFoodSafetyQualityContent = document.getElementById('trFoodSafetyQualityContent');
        var trEffectUtility = document.getElementById('trEffectUtility');
        var trEffectUtilityContent = document.getElementById('trEffectUtilityContent');
//        var btnExportEBLFOGAA = document.getElementById('btnExportEBLFOGAA');
        if (!document.getElementById("evaluationRecord.statusAccept").checked) {
            trTitleEditATTP.style.display = 'none';
            trContentsEditATTP.style.display = 'none';
            trStaffRequest.style.display = '';
            trEffectiveDate.style.display = '';
            trLegal.style.display = '';
            trLegalContent.style.display = '';
            trFoodSafetyQuality.style.display = '';
            trFoodSafetyQualityContent.style.display = '';
            trEffectUtility.style.display = '';
            trEffectUtilityContent.style.display = '';
            dijit.byId("evaluationRecord.staffRequest").setValue("Yêu cầu bổ sung hồ sơ");
        } else {
            dijit.byId("evaluationRecord.staffRequest").setValue("");
            trTitleEditATTP.style.display = '';
            trContentsEditATTP.style.display = '';
            trStaffRequest.style.display = 'none';
            trEffectiveDate.style.display = 'none';
            trLegal.style.display = 'none';
            trLegalContent.style.display = 'none';
            trFoodSafetyQuality.style.display = 'none';
            trFoodSafetyQualityContent.style.display = 'none';
            trEffectUtility.style.display = '';
            trEffectUtilityContent.style.display = 'none';
        }
    };

    page.exportExportEBLFOGAA = function () {//xuat file ket qua tham dinh
        var fileId = dijit.byId("evaluationRecord.fileId").getValue();
        var titleEditATTP = page.utf8_to_b64EBLFOGAA(dijit.byId("evaluationRecord.titleEditATTP").getValue());
        var contentsEditATTP = page.utf8_to_b64EBLFOGAA(dijit.byId("evaluationRecord.contentsEditATTP").getValue());
        contentsEditATTP = contentsEditATTP.replaceAllExportEBLFOGAA('+', '_');
        document.location = "exportWord!onExportEEAA.do?fileId=" + fileId + "&title=" + titleEditATTP + "&contents=" + contentsEditATTP;
    };

    String.prototype.replaceAllExportEBLFOGAA = function (strTarget, strSubString) {
        var strText = this;
        var intIndexOfMatch = strText.indexOf(strTarget);
        while (intIndexOfMatch != -1) {
            strText = strText.replace(strTarget, strSubString)

            intIndexOfMatch = strText.indexOf(strTarget);
        }
        return(strText);
    };

    page.utf8_to_b64EBLFOGAA = function (str) {
        return window.btoa(unescape(encodeURIComponent(str)));
    };

    page.downloadFBRFAA = function () {//xuat file ket qua tham dinh
        var fileId = dijit.byId("evaluationRecord.fileId").getValue();
        var content = page.utf8_to_b64FBRFAA(dijit.byId("evaluationRecord.staffRequest").getValue());
        content = content.replaceAllFBRF('+', '_');
        document.location = "exportWord!onXuatTBSDBS.do?fileId=" + fileId + "&content=" + content;
    };
    page.utf8_to_b64FBRFAA = function (str) {
        return window.btoa(unescape(encodeURIComponent(str)));
    };
</script>