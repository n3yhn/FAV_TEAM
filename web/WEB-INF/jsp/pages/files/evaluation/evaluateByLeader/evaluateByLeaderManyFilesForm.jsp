<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<form id="evaluateByLeaderManyFilesForm" name="createForm">
    <table width="100%" class="viewTable" id="tblReviewForm"> 
        <tr>                    
            <td align="right">
                <sd:Label key="Chọn lãnh đạo xem xét"/>
            </td>
            <td>
                <sd:SelectBox  cssStyle="width:98%"
                               id="evaluateByLeaderManyFilesForm.leaderReviewId"
                               key="" data="lstLeaderOfStaff" valueField="userId" labelField="fullName"
                               name="createForm.leaderReviewId" >
                </sd:SelectBox>
                <sd:TextBox
                    id="evaluateByLeaderManyFilesForm.leaderReviewName"
                    name="createForm.leaderReviewName"
                    cssStyle="display:none" key=""
                    />
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="onEvaluateByLeaderManyFilesSave();"/>
                <sx:ButtonClose onclick="onCloseEvaluateByLeaderForm();"/>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    onEvaluateByLeaderManyFilesSave = function () {
        msg.confirm("Bạn có chắc chắn sẽ thẩm định nhiều hồ sơ này không?", "Thẩm định nhiều hồ sơ", onEvaluateByLeaderManyFilesAction);
    };
    onEvaluateByLeaderManyFilesAction = function () {
        if (validateEvaluateByLeaderManyFiles()) {
            var leaderId = dijit.byId("evaluateByLeaderManyFilesForm.leaderReviewId").getValue();
            var leaderReviewName = page.utf8_to_b64(dijit.byId("evaluateByLeaderManyFilesForm.leaderReviewId").attr("displayedValue"));
            leaderReviewName = leaderReviewName.replaceAll('+', '_');
            var content = dijit.byId("filesGrid").vtGetCheckedDataForPost("lstItemOnGrid");
            sd.connector.post("filesAction!onEvaluateByLeaderManyFiles.do?" + token.getTokenParamString() + "&leaderId=" + leaderId + "&leaderName=" + leaderReviewName, null, "evaluateByLeaderManyFilesForm", content, page.afterReviewManyFiles);
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
    page.afterReviewManyFiles = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseReviewManyFilesForm();
            page.search();
        }
    };
    afterReviewFormSave = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseEvaluateByLeaderForm();
            backPage();
            page.search();
        }
    };
    validateEvaluateByLeaderManyFiles = function () {
        var leaderId = dijit.byId("evaluateByLeaderManyFilesForm.leaderReviewId").getValue();
        if (leaderId == -1 || leaderId == "" || leaderId == null) {
            alert("Bạn chưa chọn lãnh đạo phê duyệt");
            dijit.byId("evaluateByLeaderManyFilesForm.leaderReviewId").setValue(-1);
            dijit.byId("evaluateByLeaderManyFilesForm.leaderReviewId").focus();
            return false;
        } else {
            var leaderReviewName = dijit.byId("evaluateByLeaderManyFilesForm.leaderReviewId").attr("displayedValue");
            dijit.byId("evaluateByLeaderManyFilesForm.leaderReviewName").setValue(leaderReviewName);
        }
        return true;
    };
    onCloseReviewManyFilesForm = function () {
        dijit.byId("evaluateByLeaderManyFilesDlg").hide();
    };
</script>