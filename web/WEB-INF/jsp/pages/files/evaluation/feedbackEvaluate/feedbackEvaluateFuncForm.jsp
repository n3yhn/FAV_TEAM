<%-- 
    Document   : evaluateForm
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<form id="evaluateForm" name="createForm">
    <table width="100%" class="viewTable">
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Kết quả thẩm định"/></td>
            <td width="70%">
                <sd:TextBox key="" id="evaluateForm.fileId" name="createForm.fileId" cssStyle="display:none"/>
                <input type="radio" id="evaluateForm.statusAccept" name="createForm.status" value="4"/>
                <sd:Label key="Duyệt: Hồ sơ đạt"/>
                </br>
                <input type="radio" id="evaluateForm.statusDeny" name="createForm.status" value="7"/>
                <sd:Label key="Duyệt: Yêu cầu bổ sung HS"/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Nội dung trình quản lý xem xét, hoặc nội dung yêu cầu bổ sung"/></td>
            <td>
                <sd:Textarea key="" id="evaluateForm.staffRequest" name="createForm.staffRequest" rows="4" cssStyle="width:99%" maxlength="1800" trim="true"/>
            </td>
        </tr>
        <div id="effectiveDateDiv" style="display:none">
            <tr>
                <td style="text-align: right"><sd:Label key="Thời hạn hiệu lực của giấy tiếp nhận"/></td>
                <td>
                    <sd:SelectBox id="evaluateForm.effectiveDate" name="createForm.effectiveDate" key="" >
                        <sd:Option value='0' selected="true">--Chọn--</sd:Option>
                        <sd:Option value='3'>3 năm</sd:Option>
                        <sd:Option value='5'>5 năm</sd:Option>
                    </sd:SelectBox>
                </td>
            </tr>
        </div>
        <tr>
            <td style="text-align: right"><sd:Label key="Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)"/></td>
            <td>
                <sd:SelectBox id="evaluationRecordsForm.legal" name="createForm.evaluationRecordsForm.legal" key="" >
                    <sd:Option value='1'>Đồng ý</sd:Option>
                    <sd:Option value='0'>Không đồng ý</sd:Option>
                    <sd:Option value='-1'>Bổ sung</sd:Option>
                </sd:SelectBox>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
            <td>
                <sd:Textarea key="" id="evaluationRecordsForm.legalContent" name="createForm.evaluationRecordsForm.legalContent" rows="4" cssStyle="width:99%" maxlength="1800" trim="true"/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Về chỉ tiêu chất lượng an toàn thực phẩm"/></td>
            <td>
                <sd:SelectBox id="evaluationRecordsForm.foodSafetyQuality" name="createForm.evaluationRecordsForm.foodSafetyQuality" key="" >
                    <sd:Option value='1'>Đồng ý</sd:Option>
                    <sd:Option value='0'>Không đồng ý</sd:Option>
                    <sd:Option value='-1'>Bổ sung</sd:Option>
                </sd:SelectBox>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
            <td>
                <sd:Textarea key="" id="evaluationRecordsForm.foodSafetyQualityContent" name="createForm.evaluationRecordsForm.foodSafetyQualityContent" rows="4" cssStyle="width:99%" maxlength="1800" trim="true"/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng"/></td>
            <td>
                <sd:SelectBox id="evaluationRecordsForm.effectUtility" name="createForm.evaluationRecordsForm.effectUtility" key="" >
                    <sd:Option value='1'>Đồng ý</sd:Option>
                    <sd:Option value='0'>Không đồng ý</sd:Option>
                    <sd:Option value='-1'>Bổ sung</sd:Option>
                </sd:SelectBox>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Lý do không cấp hoặc yêu cầu bổ sung"/></td>
            <td>
                <sd:Textarea key="" id="evaluationRecordsForm.effectUtilityContent" name="createForm.evaluationRecordsForm.effectUtilityContent" rows="4" cssStyle="width:99%" maxlength="1800" trim="true"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="onFEFAction();"/>
                <sx:ButtonClose onclick="onCloseEvaluate();"/>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    afterEvaluate = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseEvaluate();
            page.search();
        }
    };

    onFEFAction = function() {
        if (page.validate()) {
            sd.connector.post("filesAction!onEvaluate.do?" + token.getTokenParamString(), null, "evaluateForm", null, afterEvaluate);
        }
    };
    page.validate = function() {
        var legalContent = dijit.byId("evaluationRecordsForm.legalContent").getValue();
        var foodSafetyQualityContent = dijit.byId("evaluationRecordsForm.foodSafetyQualityContent").getValue();
        var effectUtilityContent = dijit.byId("evaluationRecordsForm.effectUtilityContent").getValue();
        if (document.getElementById("evaluateForm.statusAccept").checked || document.getElementById("evaluateForm.statusDeny").checked) {
            if (document.getElementById("evaluateForm.statusAccept").checked
                    && (dijit.byId("evaluationRecordsForm.legal").getValue() != 1
                            || dijit.byId("evaluationRecordsForm.foodSafetyQuality").getValue() != 1
                            || dijit.byId("evaluationRecordsForm.effectUtility").getValue() != 1)) {
                alert("Kết luân thẩm định và Nội dung thẩm định không đúng vui lòng kiểm tra lại!");
                return false;
            } else {
                if (document.getElementById("evaluateForm.statusDeny").checked
                        && dijit.byId("evaluationRecordsForm.legal").getValue() == 1
                        && dijit.byId("evaluationRecordsForm.foodSafetyQuality").getValue() == 1
                        && dijit.byId("evaluationRecordsForm.effectUtility").getValue() == 1) {
                    alert("Kết luân thẩm định và Nội dung thẩm định không đúng vui lòng kiểm tra lại!");
                    return false;
                } else {
                    if (document.getElementById("evaluateForm.statusDeny").checked) {
                        if (dijit.byId("evaluationRecordsForm.legal").getValue() == 1 && legalContent.trim().length == 0) {
                            alert("[Về pháp chế(Hồ sơ theo Nghị định số 38/2012/NĐ-CP & thông tư hướng dẫn)] chưa nhập lý do");
                            return false;
                        }
                        if (dijit.byId("evaluationRecordsForm.foodSafetyQuality").getValue() == 1 && foodSafetyQualityContent.trim().length == 0) {
                            alert("[Lý do không cấp hoặc yêu cầu bổ sung] chưa nhập lý do");
                            return false;
                        }
                        if (dijit.byId("evaluationRecordsForm.effectUtility").getValue() == 1 && effectUtilityContent.trim().length == 0) {
                            alert("[Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng] chưa nhập lý do");
                            return false;
                        }
                    }
                    alert("Kết luân thẩm định và Nội dung thẩm định không đúng vui lòng kiểm tra lại!");
                    return false;
                }
            }
        } else {
            alert("Bạn chưa chọn 'Duyệt: Hồ sơ đạt' hay 'Yêu cầu bổ sung'");
            return false;
        }
        return true;
    };

    onCloseEvaluate = function() {
        dijit.byId("evaluateDlg").hide();
        dijit.byId("evaluateForm.staffRequest").setValue("");
        //        dijit.byId("evaluateForm.statusAccept").checked = false;
        document.getElementById("evaluateForm.statusAccept").checked = false;
        document.getElementById("evaluateForm.statusDeny").checked = false;

        dijit.byId("evaluateForm.effectiveDate").setValue(0);
        dijit.byId("evaluationRecordsForm.legal").setValue(1);
        dijit.byId("evaluationRecordsForm.foodSafetyQuality").setValue(1);
        dijit.byId("evaluationRecordsForm.effectUtility").setValue(1);

        dijit.byId("evaluationRecordsForm.legalContent").setValue("");
        dijit.byId("evaluationRecordsForm.foodSafetyQualityContent").setValue("");
        dijit.byId("evaluationRecordsForm.effectUtilityContent").setValue("");
    };
</script>