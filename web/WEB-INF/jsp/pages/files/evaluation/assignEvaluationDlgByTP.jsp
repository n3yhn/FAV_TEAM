<%-- 
    Document   : assignEvaluationDlgForm
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<form id="assignEvaluationDlgForm" name="createForm">
    <table width="100%" class="viewTable" id="tblReviewForm">
        <tr style="display: none">
            <td width="30%" style="text-align: right"></td>
            <td width="70%">
                <sd:TextBox key="" id="assignEvaluationDlgForm.fileId" name="createForm.fileId" cssStyle="display:none"/>
            </td>
        </tr>
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Tên tổ chức, cá nhân"/></td>
            <td width="70%">
                <div id="assignEvaluationDlgForm.businessName" style="font-weight: bold"></div>
            </td>            
        </tr>
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Tên sản phẩm"/></td>
            <td width="70%">
                <div id="assignEvaluationDlgForm.productName" style="font-weight: bold"></div>
            </td>            
        </tr>
        <tr>                    
            <td align="right">
                <sd:Label key="Chọn tổ trưởng thẩm định"/>
            </td>
            <td>
                <sd:SelectBox  cssStyle="width:98%"
                               id="assignEvaluationDlgForm.leaderEvaluateId"
                               key="" data="lstLeaderPP" valueField="userId" labelField="fullName"
                               name="createForm.leaderEvaluateId" >
                </sd:SelectBox>
                <sd:TextBox id="assignEvaluationDlgForm.leaderEvaluateName" name="createForm.leaderEvaluateName" cssStyle="display:none" key=""/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="onAssignEvaluationFormSave();"/>
                <sx:ButtonClose onclick="onCloseAssignEvaluationForm();"/>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    afterReviewFormSave = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        onCloseAssignEvaluationForm();
        page.search();
    };
    onAssignEvaluationFormSave = function () {
        if (validateAEForm()) {
            sd.connector.post("filesAction!onSaveAssignEvaluation.do?" + token.getTokenParamString(), null, "assignEvaluationDlgForm", null, afterReviewFormSave);
        }
    };
    validateAEForm = function () {
        var leaderId = dijit.byId("assignEvaluationDlgForm.leaderEvaluateId").getValue();
        if (leaderId == -1) {
            alert("Bạn chưa chọn tổ trưởng xử lý");
            dijit.byId("assignEvaluationDlgForm.leaderEvaluateId").focus();
            return false;
        } else {
            var leaderEvaluateName = dijit.byId("assignEvaluationDlgForm.leaderEvaluateId").attr("displayedValue");
            dijit.byId("assignEvaluationDlgForm.leaderEvaluateName").setValue(leaderEvaluateName);
        }
        return true;
    };
    onCloseAssignEvaluationForm = function () {
        dijit.byId("assignEvaluationDlgByTP").hide();
    };
</script>