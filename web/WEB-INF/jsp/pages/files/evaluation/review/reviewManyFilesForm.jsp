<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<form id="reviewManyFilesForm" name="createForm">
    <table width="100%" class="viewTable" id="tblReviewForm"> 
        <tr>                    
            <td align="right">
                <sd:Label key="Chọn lãnh đạo phê duyệt"/>
            </td>
            <td>
                <sd:SelectBox  cssStyle="width:98%"
                               id="reviewManyFilesForm.leaderApproveId"
                               key="" data="lstLeader" valueField="userId" labelField="fullName"
                               name="createForm.leaderApproveId" >
                </sd:SelectBox>
                <sd:TextBox id="reviewManyFilesForm.leaderApproveName" name="createForm.leaderApproveName" cssStyle="display:none" key=""/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="onReviewManyFilesSave();"/>
                <sx:ButtonClose onclick="onCloseReviewForm();"/>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    onReviewManyFilesSave = function () {
        msg.confirm("Bạn có chắc chắn sẽ xem xét nhiều hồ sơ này không?", "Xem xét nhiều hồ sơ", onReviewManyFilesAction);
    };
    onReviewManyFilesAction = function () {
        if (validateReviewManyFiles()) {
            var leaderId = dijit.byId("reviewManyFilesForm.leaderApproveId").getValue();
            var leaderApproveName = page.utf8_to_b64(dijit.byId("reviewManyFilesForm.leaderApproveId").attr("displayedValue"));
            leaderApproveName = leaderApproveName.replaceAll('+', '_');
            var content = dijit.byId("filesGrid").vtGetCheckedDataForPost("lstItemOnGrid");
            sd.connector.post("filesAction!onReviewManyFiles.do?" + token.getTokenParamString() + "&leaderId=" + leaderId + "&leaderName=" + leaderApproveName, null, "reviewManyFilesForm", content, page.afterReviewManyFiles);
        }
    };
    page.utf8_to_b64 = function (str) {
        return window.btoa(unescape(encodeURIComponent(str)));
    };
    String.prototype.replaceAll = function (strTarget, strSubString) {
        var strText = this;
        var intIndexOfMatch = strText.indexOf(strTarget);
        while (intIndexOfMatch != -1) {
            strText = strText.replace(strTarget, strSubString)
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
            onCloseReviewForm();
            backPage();
            page.search();
        }
    };
    validateReviewManyFiles = function () {
        var leaderId = dijit.byId("reviewManyFilesForm.leaderApproveId").getValue();
        if (leaderId == -1 || leaderId == "" || leaderId == null) {
            alert("Bạn chưa chọn lãnh đạo phê duyệt");
            dijit.byId("reviewManyFilesForm.leaderApproveId").setValue(-1);
            dijit.byId("reviewManyFilesForm.leaderApproveId").focus();
            return false;
        } else {
            var leaderApproveName = dijit.byId("reviewManyFilesForm.leaderApproveId").attr("displayedValue");
            dijit.byId("reviewManyFilesForm.leaderApproveName").setValue(leaderApproveName);
        }
        return true;
    };
    onCloseReviewManyFilesForm = function () {
        dijit.byId("reviewManyFilesForm").hide();
    };
</script>