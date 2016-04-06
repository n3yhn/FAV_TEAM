<%-- 
    Document   : assignApproveDlgForm
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<form id="assignApproveDlgForm" name="createForm">
    <table width="100%" class="viewTable" id="tblReviewForm">
        <tr style="display: none">
            <td width="30%" style="text-align: right"></td>
            <td width="70%">
                <sd:TextBox key="" id="assignApproveDlgForm.fileId" name="createForm.fileId" cssStyle="display:none"/>
            </td>
        </tr>
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Tên tổ chức, cá nhân"/></td>
            <td width="70%">
                <div id="assignApproveDlgForm.businessName" style="font-weight: bold"></div>
            </td>            
        </tr>
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Tên sản phẩm"/></td>
            <td width="70%">
                <div id="assignApproveDlgForm.productName" style="font-weight: bold"></div>
            </td>            
        </tr>
        <tr>                    
            <td align="right">
                <sd:Label key="Chọn lãnh đạo phê duyệt"/>
            </td>
            <td>
                <sd:SelectBox  cssStyle="width:98%"
                               id="assignApproveDlgForm.leaderApproveId"
                               key="" data="lstLeaderP" valueField="userId" labelField="fullName"
                               name="createForm.leaderApproveId" >
                </sd:SelectBox>
                <sd:TextBox id="assignApproveDlgForm.leaderApproveName" name="createForm.leaderApproveName" cssStyle="display:none" key=""/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="onAssignApproveFormSave();"/>
                <sx:ButtonClose onclick="onCloseAssignApproveForm();"/>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    afterReviewFormSave = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        onCloseAssignApproveForm();
        page.search();
    };
    onAssignApproveFormSave = function () {
        if (validateReviewForm()) {
            sd.connector.post("filesAction!onAssignApprove.do?" + token.getTokenParamString(), null, "assignApproveDlgForm", null, afterReviewFormSave);
        }
    };
    validateReviewForm = function () {
        var leaderId = dijit.byId("assignApproveDlgForm.leaderApproveId").getValue();
        if (leaderId == -1) {
            alert("Bạn chưa chọn lãnh đạo phê duyệt");
            dijit.byId("assignApproveDlgForm.leaderApproveId").focus();
            return false;
        } else {
            var leaderApproveName = dijit.byId("assignApproveDlgForm.leaderApproveId").attr("displayedValue");
            dijit.byId("assignApproveDlgForm.leaderApproveName").setValue(leaderApproveName);
        }
        return true;
    };
    onCloseAssignApproveForm = function () {
        dijit.byId("assignApproveDlg").hide();
    };
</script>