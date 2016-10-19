<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<object id="plugin_REAAFOG" type="application/x-viettelcasigner" width="3" height="10">
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
                                    <td width="30%" style="text-align: right">
                                        <sd:Label key="Kết quả thẩm định"/>
                                    </td>
                                    <td width="70%">
                                        <div id="reviewForm.statusName"></div>
                                        <sd:TextBox key="" id="reviewForm.status" name="reviewForm.status" cssStyle="display:none" />
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right">
                                        <sd:Label key="Nội dung thẩm định"/>
                                    </td>
                                    <td width="70%">
                                        <div id="reviewForm.staffRequest">

                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%" style="text-align: right">
                                        <sd:Label key="Kết quả xem xét"/>
                                    </td>
                                    <td width="70%">
                                        <sd:TextBox key="" id="reviewForm.fileId" name="createForm.fileId" cssStyle="display:none"/>
                                        <input type="radio" value="5"
                                               id="reviewForm.statusAccept"
                                               name="createForm.status"
                                               onchange="onchangeStatusREAAFOG();"/>
                                        <sd:Label key="Xem xét: hồ sơ đạt gửi lên LĐC"/>                                             
                                        </br>
                                        <input type="radio" value="6"
                                               id="reviewForm.statusApprove"
                                               name="createForm.status"
                                               onchange="onchangeStatusREAAFOG();"/>
                                        <sd:Label key="Phê duyệt: CVBS sau công bố"/>   
                                        </br>
                                        <input type="radio" value="27" 
                                               id="reviewForm.statusDeny" 
                                               name="createForm.status"
                                               onchange="onchangeStatusREAAFOG();"/>
                                        <sd:Label key="Phê duyệt: Y/c sửa đổi bổ sung"/>
                                        </br>
                                        <input type="radio" value="8"
                                               id="reviewForm.statusDenyCV"
                                               name="createForm.status"
                                               onchange="onchangeStatusREAAFOG();"/>
                                        <sd:Label key="Yêu cầu: Chuyển hồ sơ cho chuyên viên thẩm định lại"/>
                                    </td>
                                </tr>
                                <tr id="trTitleEditATTP" style="display:">
                                    <td style="text-align: right">
                                        <sd:Label key="Tiêu đề công văn trả lời doanh nghiệp"/>
                                    </td>
                                    <td>
                                        <sd:Textarea 
                                            id="reviewForm.titleEditATTP" 
                                            name="createForm.titleEditATTP"
                                            key="" rows="5" cssStyle="width:99%" maxlength="255" trim="true"
                                            />
                                    </td>
                                </tr>
                                <tr id="trContentsEditATTP" style="display:">
                                    <td style="text-align: right">
                                        <sd:Label key="Nội dung công văn trả lời doanh nghiệp"/>
                                    </td>
                                    <td>
                                        <sd:Textarea 
                                            id="reviewForm.contentsEditATTP" 
                                            name="createForm.contentsEditATTP"
                                            key="" rows="10" cssStyle="width:99%" maxlength="2000" trim="true"
                                            />
                                    </td>
                                </tr>
                                <tr id="trLeaderStaffRequest" style="display: none">
                                    <td style="text-align: right">
                                        <sd:Label key="Nội dung trình quản lý phê duyệt, hoặc nội dung yêu cầu bổ sung"/>
                                    </td>
                                    <td>
                                        <sd:Textarea key="" rows="15" cssStyle="width:99%" maxlength="2000"
                                                     id="reviewForm.leaderStaffRequest" 
                                                     name="createForm.leaderStaffRequest" />
                                    </td>
                                </tr>                               
                                <tr style="display: none">
                                    <td style="text-align: right">
                                        <sd:Label key="Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)"/>
                                    </td>
                                    <td>
                                        <sd:SelectBox id="reviewForm.legalL" name="createForm.evaluationRecordsForm.legalL" key="" >
                                            <sd:Option value='1'>Đồng ý</sd:Option>
                                            <sd:Option value='0'>Không đồng ý</sd:Option>
                                            <sd:Option value='-1'>Bổ sung</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr style="display: none">
                                    <td style="text-align: right">
                                        <sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/>
                                    </td>
                                    <td>
                                        <sd:Textarea key="" id="reviewForm.legalContentL"
                                                     name="createForm.evaluationRecordsForm.legalContentL"
                                                     rows="4" cssStyle="width:99%" maxlength="2000" trim="true"/>
                                    </td>
                                </tr>
                                <tr style="display: none">
                                    <td style="text-align: right">
                                        <sd:Label key="Về chỉ tiêu chất lượng an toàn thực phẩm"/>
                                    </td>
                                    <td>
                                        <sd:SelectBox 
                                            id="reviewForm.foodSafetyQualityL"
                                            name="createForm.evaluationRecordsForm.foodSafetyQualityL"
                                            key="" >
                                            <sd:Option value='1'>Đồng ý</sd:Option>
                                            <sd:Option value='0'>Không đồng ý</sd:Option>
                                            <sd:Option value='-1'>Bổ sung</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr style="display: none">
                                    <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" rows="4" cssStyle="width:99%" maxlength="2000" trim="true"
                                                     id="reviewForm.foodSafetyQualityContentL"
                                                     name="createForm.evaluationRecordsForm.foodSafetyQualityContentL"/>
                                    </td>
                                </tr>
                                <tr style="display: none">
                                    <td style="text-align: right">
                                        <sd:Label key="Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng"/>
                                    </td>
                                    <td>
                                        <sd:SelectBox key="" 
                                                      id="reviewForm.effectUtilityL" 
                                                      name="createForm.evaluationRecordsForm.effectUtilityL" >
                                            <sd:Option value='1'>Đồng ý</sd:Option>
                                            <sd:Option value='0'>Không đồng ý</sd:Option>
                                            <sd:Option value='-1'>Bổ sung</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr style="display: none">
                                    <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
                                    <td>
                                        <sd:Textarea key="" rows="4" cssStyle="width:99%" maxlength="2000" trim="true"
                                                     id="reviewForm.effectUtilityContentL"
                                                     name="createForm.evaluationRecordsForm.effectUtilityContentL"/>
                                    </td>
                                </tr>
                                <tr>                    
                                    <td align="right">
                                        <sd:Label key="Chọn lãnh đạo phê duyệt"/>
                                    </td>
                                    <td>
                                        <sd:SelectBox  
                                            cssStyle="width:98%" key="" data="lstLeader" valueField="userId" labelField="fullName"
                                            id="reviewForm.leaderApproveId"                                                       
                                            name="createForm.leaderApproveId" >
                                        </sd:SelectBox>
                                        <sd:TextBox 
                                            id="reviewForm.leaderApproveName" 
                                            name="createForm.leaderApproveName" 
                                            cssStyle="display:none" key=""/>
                                    </td>
                                </tr>
                                <tr id="trLeader4AAWait" style="display: none">
                                    <td colspan="2" style="text-align: center;alignment-adjust: middle">
                                        <label id="labelLeader4AAWait" style="color: red">Vui lòng chờ  </label>
                                        <img src="/share/images/loading/loading2.gif" width="20px" height="20px">
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="2" style="text-align: center;alignment-adjust: middle">
                                        <div id="divSignLeader4AAProcess" style="display: none;text-align: center;alignment-adjust: middle">
                                            <table>
                                                <tr>
                                                    <td>
                                                        <div id="divLeader4AAProcess" style="color:red;font-weight: bold;margin-left: 10px;margin-top: 3px"></div>
                                                    </td>
                                                    <td>
                                                        <img src="/share/images/loading/loading2.gif" width="20px" height="20px" >
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="text-align: center">
                                        <sd:Button id="btnSaveREAAFOG" key="" onclick="onReviewFormSave();" cssStyle="display:none" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Lưu</span>
                                        </sd:Button>
                                        <br>
                                        <sd:Button id="btnLeaderSign4AA" key="" onclick="showLeaderSign4AAForm();" cssStyle="display:none" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">pDuyệt CVBS sau công bố</span>
                                        </sd:Button>
                                        <sd:Button id="btnExportLeaderSign4AA" key="" 
                                                   onclick="page.exportExportREAA();" cssStyle="display:none" cssClass="buttonGroup">
                                            <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">xTrước CVBS sau công bố</span>
                                        </sd:Button>
                                        <br>
                                        <sd:Button id="btnSignYCSDBS4AA" key="" 
                                                   onclick="page.onApproveSignSdbsPluginREAAFOG();" cssStyle="display:none" cssClass="buttonGroup">
                                            <img src="share/images/icons/signature-icon.gif" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">pDuyệt cVăn y/c SĐBS</span>
                                        </sd:Button>
                                        <sd:Button id="btnExportSignYCSDBS4AA" key="" onclick="page.exportSignYCSDBS4AA();" cssStyle="display:none" cssClass="buttonGroup">
                                            <img src="share/images/icons/process_icon.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">xTrước cVăn y/c SĐBS</span>
                                        </sd:Button>
                                        <br>
                                        <sd:Button 
                                            key=""  cssStyle="display:" cssClass="buttonGroup"
                                            id="btnLoadCookieRFOG" 
                                            onclick="page.getReviewForm();">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Tải ND thẩm định gần đây</span>
                                        </sd:Button>
                                        <sd:Button id="btnSaveCookieRFOG" 
                                                   onclick="page.setRreviewForm();"
                                                   key="" cssStyle="display:" cssClass="buttonGroup">
                                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                                            <span style="font-size:12px">Lưu nháp ND thẩm định</span>
                                        </sd:Button>
                                        <sd:Button 
                                            id="btnClearCookieRFOG"
                                            onclick="page.clearReviewForm();"
                                            key="" cssStyle="display:" cssClass="buttonGroup">
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
<jsp:include page="pluginJSREAAFOG.jsp" flush="false"></jsp:include>
    <script type="text/javascript">
        var signType;
        onCloseReviewForm = function () {
            doGoToMenu("filesAction!toReviewPage.do?IsChange=1");
        };
        afterReviewFormSave = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            alert(result[1]);
            if (result[0] == "1") {
                onCloseReviewForm();
            }
        };
        onReviewFormSave = function () {
            msg.confirm("Bạn có chắc chắn về kết quả xem xét này không ?", "Xem xét hồ sơ", onReviewFormSaveAction);
        };
        onReviewFormSaveAction = function () {
            if (validateReviewForm()) {
                sd.connector.post("filesAction!onReview.do?" + token.getTokenParamString(), null, "reviewForm", null, afterReviewFormSave);
            }
        };

        validateReviewForm = function () {
            if (document.getElementById("reviewForm.statusAccept").checked == false
                    && document.getElementById("reviewForm.statusApprove").checked == false
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
//                if (document.getElementById("reviewForm.statusSDBSLDC").checked) {
//                    if (leaderStaffRequest.trim().length == 0) {
//                        alert("Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung");
//                        dijit.byId("reviewForm.leaderStaffRequest").focus();
//                        return false;
//                    }
//                    if ((dijit.byId("reviewForm.legalL").getValue() == 0
//                            || dijit.byId("reviewForm.legalL").getValue() == -1)
//                            && legalContentL.trim().length == 0) {
//                        alert("[Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)] chưa nhập lý do");
//                        dijit.byId("reviewForm.legalContentL").focus();
//                        return false;
//                    }
//                    if ((dijit.byId("reviewForm.foodSafetyQualityL").getValue() == 0
//                            || dijit.byId("reviewForm.foodSafetyQualityL").getValue() == -1)
//                            && foodSafetyQualityContentL.trim().length == 0) {
//                        alert("[Về chỉ tiêu chất lượng an toàn thực phẩm] chưa nhập lý do");
//                        dijit.byId("reviewForm.foodSafetyQualityContentL").focus();
//                        return false;
//                    }
//                    if ((dijit.byId("reviewForm.effectUtilityL").getValue() == 0
//                            || dijit.byId("reviewForm.effectUtilityL").getValue() == -1)
//                            && effectUtilityContentL.trim().length == 0) {
//                        alert("[Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng] chưa nhập lý do");
//                        dijit.byId("reviewForm.effectUtilityContentL").focus();
//                        return false;
//                    }
//                } else {
                    if (document.getElementById("reviewForm.statusDenyCV").checked) {
                        if (leaderStaffRequest.trim().length == 0) {
                            alert("Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung");
                            dijit.byId("reviewForm.leaderStaffRequest").focus();
                            return false;
                        }
//                    }                    
                }
                if (document.getElementById("reviewForm.statusAccept").checked == true) {
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
        page.replaceBrTblReviewForm = function () {
            var content = "";
            content = document.getElementById("reviewForm.staffRequest").innerHTML;
            content = content.replace(/\n/g, "<br>");
            document.getElementById("reviewForm.staffRequest").innerHTML = content;
        };
        page.clearReviewForm = function () {
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
            } catch (err)
            {
                alert("Không thể Xóa nội dung thẩm định gần đây!");
            }
        };
        page.setRreviewForm = function () {
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
            } catch (err)
            {
                alert("Không thể Lưu nháp nội dung thẩm định!");
            }
        };
        page.getReviewForm = function () {
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
            } catch (err)
            {
                alert("Không thể Tải nội thẩm định gần đây!");
            }
        };
        onchangeStatusREAAFOG = function () {
            var trTitleEditATTP = document.getElementById('trTitleEditATTP');
            var trContentsEditATTP = document.getElementById('trContentsEditATTP');
            var trLeaderStaffRequest = document.getElementById('trLeaderStaffRequest');

            if (document.getElementById("reviewForm.statusApprove").checked) {
                trTitleEditATTP.style.display = ''; //
                trContentsEditATTP.style.display = ''; //
                trLeaderStaffRequest.style.display = 'none';


                dijit.byId("btnLeaderSign4AA").domNode.style.display = "";
                dijit.byId("btnExportLeaderSign4AA").domNode.style.display = "";
                dijit.byId("btnSignYCSDBS4AA").domNode.style.display = "none";
                dijit.byId("btnExportSignYCSDBS4AA").domNode.style.display = "none";
                dijit.byId("btnSaveREAAFOG").domNode.style.display = "none";
            } else if (document.getElementById("reviewForm.statusDeny").checked) {
                trTitleEditATTP.style.display = 'none';
                trContentsEditATTP.style.display = 'none';
                trLeaderStaffRequest.style.display = '';

                dijit.byId("btnLeaderSign4AA").domNode.style.display = "none";
                dijit.byId("btnExportLeaderSign4AA").domNode.style.display = "none";
                dijit.byId("btnSignYCSDBS4AA").domNode.style.display = "";
                dijit.byId("btnExportSignYCSDBS4AA").domNode.style.display = "";
                dijit.byId("btnSaveREAAFOG").domNode.style.display = "none";
            }  else if (document.getElementById("reviewForm.statusAccept").checked) {
                trTitleEditATTP.style.display = ''; //
                trContentsEditATTP.style.display = ''; //
                trLeaderStaffRequest.style.display = 'none';


                dijit.byId("btnLeaderSign4AA").domNode.style.display = "none";
                dijit.byId("btnExportLeaderSign4AA").domNode.style.display = "none";
                dijit.byId("btnSignYCSDBS4AA").domNode.style.display = "none";
                dijit.byId("btnExportSignYCSDBS4AA").domNode.style.display = "none";
                dijit.byId("btnSaveREAAFOG").domNode.style.display = "";
            }else if(document.getElementById("reviewForm.statusDenyCV").checked){
                trTitleEditATTP.style.display = 'none'; //
                trContentsEditATTP.style.display = 'none'; //
                trLeaderStaffRequest.style.display = '';


                dijit.byId("btnLeaderSign4AA").domNode.style.display = "none";
                dijit.byId("btnExportLeaderSign4AA").domNode.style.display = "none";
                dijit.byId("btnSignYCSDBS4AA").domNode.style.display = "none";
                dijit.byId("btnExportSignYCSDBS4AA").domNode.style.display = "none";
                dijit.byId("btnSaveREAAFOG").domNode.style.display = "";
            }
        };
        page.exportExportREAA = function () {
            var fileId = dijit.byId("reviewForm.fileId").getValue();
            var titleEditATTP = page.utf8_to_b64FBRF(dijit.byId("reviewForm.titleEditATTP").getValue());
            var contentsEditATTP = page.utf8_to_b64FBRF(dijit.byId("reviewForm.contentsEditATTP").getValue());
            contentsEditATTP = contentsEditATTP.replaceAllExportREAA('+', '_');
            document.location = "exportWord!onExportEEAA.do?fileId=" + fileId + "&title=" + titleEditATTP + "&contents=" + contentsEditATTP;
        };
        String.prototype.replaceAllExportREAA = function (strTarget, strSubString) {
            var strText = this;
            var intIndexOfMatch = strText.indexOf(strTarget);
            while (intIndexOfMatch != -1) {
                strText = strText.replace(strTarget, strSubString)

                intIndexOfMatch = strText.indexOf(strTarget);
            }
            return(strText);
        };
        showLeaderSign4AAForm = function () {
            document.getElementById("reviewForm.statusApprove").checked = true;
            document.getElementById("trLeader4AAWait").style.display = "";
            document.getElementById("labelLeader4AAWait").innerHTML = "Hệ thống đang tạo công văn và ký số, vui lòng chờ  ";
            var title4AA = page.utf8_to_b64REAAFOG(dijit.byId("reviewForm.titleEditATTP").getValue());
            var content4AA = page.utf8_to_b64REAAFOG(dijit.byId("reviewForm.contentsEditATTP").getValue());
            content4AA = content4AA.replaceAllREAAFOG('+', '_');
            title4AA = title4AA.replaceAllREAAFOG('+', '_');
            sd.connector.post("filesExplandAction!onCreatePaperByLeaderForAA.do?"
                    + token.getTokenParamString()
                    + "&content=" + content4AA
                    + "&title=" + title4AA, null, "reviewForm", null, afterOCPBL4AA_REAAFOG);
        };
        page.utf8_to_b64REAAFOG = function (str) {
            return window.btoa(unescape(encodeURIComponent(str)));
        };
        String.prototype.replaceAllREAAFOG = function (strTarget, strSubString) {
            var strText = this;
            var intIndexOfMatch = strText.indexOf(strTarget);
            while (intIndexOfMatch != -1) {
                strText = strText.replace(strTarget, strSubString)
                intIndexOfMatch = strText.indexOf(strTarget);
            }
            return(strText);
        };
        afterOCPBL4AA_REAAFOG = function (data) {//b4
            var obj = dojo.fromJson(data);
            onSendReviewSignByLeaderForAA_REAAFOG();
        };
        onSendReviewSignByLeaderForAA_REAAFOG = function () {//b5
            var fileId = dijit.byId("reviewForm.fileId").getValue();
            sd.connector.post("exportWord!onExportPaperSignPlugin.do?fileId=" + fileId, null, null, null, afterOnExportPaperSignPlugin_REAAFOG);
        };
        afterOnExportPaperSignPlugin_REAAFOG = function (data) {//b6
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                var signType = "PDHS";
                var fileId = dijit.byId("reviewForm.fileId").getValue();
                if (count == 0) {
                    var item = uploadCertOfFile_REAAFOG(fileId);
                    cert = encodeBase64(item.certChain);
                }
                var path = result[2];
                sd.connector.post("filesExplandAction!actionSignCA.do?fileId=" + fileId + "&cert=" + cert + "&signType=" + signType + "&path=" + path, null, null, null, page.signPlugin_REAAFOG);
            } else {
                msg.alert("Có lỗi trong quá trình xuất công văn SĐBS", "Cảnh báo");
                document.getElementById("trWait").style.display = "none";
            }
        };
        page.signPlugin_REAAFOG = function (data)//b7
        {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                var txtBase64HashNew = result[2];
                var certSerialNew = result[3];
                var fileId = result[4];
                var outPutPath = result[5];
                var fileName = result[6];
                dijit.byId("txtBase64Hash_REAAFOG").setValue(txtBase64HashNew);
                dijit.byId("txtCertSerial_REAAFOG").setValue(certSerialNew);
                var sign = signAndSubmit_REAAFOG();
                var signData = encodeBase64(sign);
                sd.connector.post("filesExplandAction!onSignPlugin.do?fileId=" + fileId + "&outPutPath=" + outPutPath + "&signData=" + signData + "&signType=" + signType + "&fileName=" + fileName, null, null, null, page.afterOnSignPlugin_REAAFOG);
            } else {
                alert("Ký số không thành công ! " + result[1]);
            }
        };
        page.afterOnSignPlugin_REAAFOG = function (data)//b8
        {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                onApproveLdp_REAAFOG();
            } else
            {
                alert("Ký số không thành công !");
            }
        };
        onApproveLdp_REAAFOG = function () {
            sd.connector.post("filesExplandAction!onApproveByLDP4AA.do?" + token.getTokenParamString(), null, "reviewForm", null, afterApprove_REAAFOG);
        };
        afterApprove_REAAFOG = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            alert(result[1]);
            if (result[0] == "1") {
                onCloseApproveDlg_REAAFOG();
                page.search();
            }
        };
        onCloseApproveDlg_REAAFOG = function () {
            var txtBase64Hash_REAAFOGCheck = dijit.byId('txtBase64Hash_REAAFOG');
            if (txtBase64Hash_REAAFOGCheck) {
                txtBase64Hash_REAAFOGCheck.destroyRecursive(true);
            }
            var txtCertSerial_REAAFOGCheck = dijit.byId('txtCertSerial_REAAFOG');
            if (txtCertSerial_REAAFOGCheck) {
                txtCertSerial_REAAFOGCheck.destroyRecursive(true);
            }
            var reviewSignForAAFormFileId = dijit.byId('reviewForm.fileId');
            if (reviewSignForAAFormFileId) {
                reviewSignForAAFormFileId.destroyRecursive(true);
            }
            var reviewSignForAAFormTitle = dijit.byId('reviewForm.titleEditATTP');
            if (reviewSignForAAFormTitle) {
                reviewSignForAAFormTitle.destroyRecursive(true);
            }
            var reviewSignForAAFormContent = dijit.byId('reviewForm.contentsEditATTP');
            if (reviewSignForAAFormContent) {
                reviewSignForAAFormContent.destroyRecursive(true);
            }
            var btnSendLDCFRF1 = dijit.byId('btnLeaderSign4AA');
            if (btnSendLDCFRF1) {
                btnSendLDCFRF1.destroyRecursive(true);
            }

            var titlePaneViewFile = dijit.byId('titlePaneViewFile');
            if (titlePaneViewFile) {
                titlePaneViewFile.destroyRecursive(true);
            }

            var titlePaneEvaluate = dijit.byId('titlePaneEvaluate');
            if (titlePaneEvaluate) {
                titlePaneEvaluate.destroyRecursive(true);
            }
            var tblEvaluateFormView = dijit.byId('tblEvaluateFormView');
            if (tblEvaluateFormView) {
                tblEvaluateFormView.destroyRecursive(true);
            }
            var reviewManyFilesForm = dijit.byId('reviewManyFilesForm');
            if (reviewManyFilesForm) {
                reviewManyFilesForm.destroyRecursive(true);
            }
            var reviewSignForAAForm = dijit.byId('reviewSignForAAForm');
            if (reviewSignForAAForm) {
                reviewSignForAAForm.destroyRecursive(true);
            }
            doGoToMenu("filesAction!toReviewPage.do?IsChange=1");
        };

        page.exportSignYCSDBS4AA = function () {
            var fileId = dijit.byId("reviewForm.fileId").getValue();
            var content = page.utf8_to_exportSignYCSDBS4AA(dijit.byId("reviewForm.leaderStaffRequest").getValue());
            content = content.replaceexportSignYCSDBS4AA('+', '_');
            document.location = "exportWord!onXuatTBSDBS.do?fileId=" + fileId + "&content=" + content;
        };

        page.utf8_to_exportSignYCSDBS4AA = function (str) {
            return window.btoa(unescape(encodeURIComponent(str)));
        };

        String.prototype.replaceexportSignYCSDBS4AA = function (strTarget, strSubString) {
            var strText = this;
            var intIndexOfMatch = strText.indexOf(strTarget);
            while (intIndexOfMatch != -1) {
                strText = strText.replace(strTarget, strSubString)
                intIndexOfMatch = strText.indexOf(strTarget);
            }
            return(strText);
        };
        //ki so
        page.validateAFCV = function () {
            if (document.getElementById("reviewForm.statusDeny").checked) {
                var leaderRequest = dijit.byId("reviewForm.leaderStaffRequest").getValue();
                if (leaderRequest.trim().length == 0) {
                    alert("Bạn chưa nhập [Nội dung]");
                    dijit.byId("reviewForm.leaderStaffRequest").focus();
                    return false;
                }
            } else {
                return false;
            }
            return true;
        };

        page.onApproveSignSdbsPluginREAAFOG = function () {
            signType = "CVBS";
            msg.confirm("Bạn có chắc muốn phê duyệt công văn SĐBS này?", "Phê duyệt công văn", function () {
                if (page.validateAFCV()) {
                    var fileId = dijit.byId("reviewForm.fileId").getValue();
                    document.getElementById("trWait").style.display = "";
                    document.getElementById("labelWait").innerHTML = "Hệ thống đang tạo công văn và ký số, vui lòng chờ  ";
                    var content = page.utf8_to_exportSignYCSDBS4AA(dijit.byId("reviewForm.leaderStaffRequest").getValue());
                    content = content.replaceexportSignYCSDBS4AA('+', '_');
                    sd.connector.post("filesAction!onExportCvSdbsSignPlugin.do?fileId=" + fileId + "&content=" + content, null, null, null, onFeedbackApproveAFPlugin);
                }
            });
        };

        onFeedbackApproveAFPlugin = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                document.getElementById("trWait").style.display = "";
                signType = "CVBS";
                var fileId = dijit.byId("reviewForm.fileId").getValue();
                if (count == 0) {
                    var item = uploadCertOfFile_REAAFOG(fileId);
                    cert = encodeBase64(item.certChain);
                }
                var path = result[2];
                sd.connector.post("filesAction!actionSignCA.do?fileId=" + fileId + "&cert=" + cert + "&signType=" + signType + "&path=" + path, null, null, null, page.signPluginYCSDBS4AA);
            } else {
                msg.alert("Có lỗi trong quá trình xuất công văn SĐBS", "Cảnh báo");
                document.getElementById("trWait").style.display = "none";
                document.getElementById("divSignProcess").style.display = "none";
            }
        };

        page.signPluginYCSDBS4AA = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                var txtBase64HashNew = result[2];
                var certSerialNew = result[3];
                var fileId = result[4];
                var outPutPath = result[5];
                var fileName = result[6];
                dijit.byId("txtBase64Hash_REAAFOG").setValue(txtBase64HashNew);
                dijit.byId("txtCertSerial_REAAFOG").setValue(certSerialNew);
                var sign = signAndSubmit_REAAFOG();
                var signData = encodeBase64(sign);
                sd.connector.post("filesAction!onSignPluginAA.do?fileId=" + fileId + "&outPutPath=" + outPutPath + "&signData=" + signData + "&signType=" + signType + "&fileName=" + fileName, null, null, null, page.afterSignPluginYCSDBS4AA);
            } else {
                alert("Ký số không thành công! " + result[1]);
            }
        };

        page.afterSignPluginYCSDBS4AA = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                onApproveLdp_REAAFOG();
            } else {
                alert("Ký số không thành công !");
            }
        };
    </script>
    <input type="hidden" id="base64Hash" value="" />
<sd:TextBox id="txtBase64Hash_REAAFOG" key="" name="txtBase64Hash" type="hidden"/>
<input type="hidden" id="certSerial" value="" />
<sd:TextBox id="txtCertSerial_REAAFOG" key="" name="txtCertSerial" type="hidden"/>