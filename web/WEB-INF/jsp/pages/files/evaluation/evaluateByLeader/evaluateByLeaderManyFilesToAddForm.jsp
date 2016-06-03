<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<form id="evaluateByLeaderManyFilesToAddForm" name="createForm">
    <table width="100%" class="viewTable" id="tblReviewForm"> 
        <tr>                    
            <td align="right">
                <sd:Label key="Chọn lãnh đạo xem xét"/>
            </td>
            <td>
                <sd:SelectBox  cssStyle="width:98%"
                               id="evaluateByLeaderManyFilesToAddForm.leaderReviewId"
                               key="" data="lstLeaderOfStaff" valueField="userId" labelField="fullName"
                               name="createForm.leaderReviewId" >
                </sd:SelectBox>
                <sd:TextBox
                    id="evaluateByLeaderManyFilesToAddForm.leaderReviewName"
                    name="createForm.leaderReviewName"
                    cssStyle="display:none" key=""
                    />
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="onEvaluateByLeaderManyFilesToAddSave();"/>
                <sx:ButtonClose onclick="onCloseEvaluateByLeaderToAddForm();"/>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    onEvaluateByLeaderManyFilesToAddSave = function () {
        msg.confirm("Bạn có chắc chắn sẽ thẩm định bổ sung nhiều hồ sơ này không?", "Thẩm định bổ sung nhiều hồ sơ", onEvaluateByLeaderManyFilesToAddAction);
    };
    onEvaluateByLeaderManyFilesToAddAction = function () {
        if (validateEvaluateByLeaderManyFilesToAdd()) {
            var leaderId = dijit.byId("evaluateByLeaderManyFilesToAddForm.leaderReviewId").getValue();
            var leaderReviewName = page.utf8_to_b64(dijit.byId("evaluateByLeaderManyFilesToAddForm.leaderReviewId").attr("displayedValue"));
            leaderReviewName = leaderReviewName.replaceAll('+', '_');
            var content = dijit.byId("filesGrid").vtGetCheckedDataForPost("lstItemOnGrid");
            sd.connector.post("filesAction!onEvaluateByLeaderManyFilesToAdd.do?" + token.getTokenParamString() + "&leaderId=" + leaderId + "&leaderName=" + leaderReviewName, null, "evaluateByLeaderManyFilesToAddForm", content, page.afterReviewManyFilesToAdd);
        }
    };
    page.utf8_to_b64 = function (str) {
        return window.btoa(unescape(encodeURIComponent(str)));
    };
    String.prototype.replaceAll = function (strTarget, strSubString) {
        var strText = this;
        var intIndexOfMatch = strText.indexOf(strTarget);
        while (intIndexOfMatch != -1) {
            strText = strText.replace(strTarget, strSubString);
            intIndexOfMatch = strText.indexOf(strTarget);
        }
        return(strText);
    };
    page.afterReviewManyFilesToAdd = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseReviewManyFilesToAddForm();
            page.search();
        }
    };
    afterReviewFormSaveToAdd = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseEvaluateByLeaderToAddForm();
            backPage();
            page.search();
        }
    };
    validateEvaluateByLeaderManyFilesToAdd = function () {
        var leaderId = dijit.byId("evaluateByLeaderManyFilesToAddForm.leaderReviewId").getValue();
        if (leaderId == -1 || leaderId == "" || leaderId == null) {
            alert("Bạn chưa chọn lãnh đạo phê duyệt");
            dijit.byId("evaluateByLeaderManyFilesToAddForm.leaderReviewId").setValue(-1);
            dijit.byId("evaluateByLeaderManyFilesToAddForm.leaderReviewId").focus();
            return false;
        } else {
            var leaderReviewName = dijit.byId("evaluateByLeaderManyFilesToAddForm.leaderReviewId").attr("displayedValue");
            dijit.byId("evaluateByLeaderManyFilesToAddForm.leaderReviewName").setValue(leaderReviewName);
        }
        return true;
    };
    onCloseReviewManyFilesToAddForm = function () {
        dijit.byId("evaluateByLeaderManyFilesToAddForm").hide();
    };
</script>