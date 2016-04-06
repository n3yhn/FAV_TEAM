<%-- 
    Document   : assignApproveDlgFormByCT
    Created on : Jun 26, 2013, 4:09:25 PM
    Author     : vtit_havm2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<form id="assignApproveDlgFormByCT" name="createForm">
    <table width="100%" class="viewTable" id="tblReviewForm">
        <tr style="display: none">
            <td width="30%" style="text-align: right"></td>
            <td width="70%">
                <sd:TextBox key="" id="assignApproveDlgFormByCT.fileId" name="createForm.fileId" cssStyle="display:none"/>
                <input type="radio" id="assignApproveDlgFormByCT.status" name="createForm.status" value="5" style="display: none" checked="true"/>
            </td>
        </tr>
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Tên tổ chức, cá nhân"/></td>
            <td width="70%">
                <div id="assignApproveDlgFormByCT.businessName" style="font-weight: bold"></div>
            </td>            
        </tr>
        <tr>
            <td width="30%" style="text-align: right"><sd:Label key="Tên sản phẩm"/></td>
            <td width="70%">
                <div id="assignApproveDlgFormByCT.productName" style="font-weight: bold"></div>
            </td>            
        </tr>
        <tr>                    
            <td align="right">
                <sd:Label key="Chọn lãnh đạo phê duyệt"/>
            </td>
            <td>
                <sd:SelectBox  cssStyle="width:98%"
                               id="assignApproveDlgFormByCT.leaderApproveId"
                               key="" data="lstLeaderP" valueField="userId" labelField="fullName"
                               name="createForm.leaderApproveId" >
                </sd:SelectBox>
                <sd:TextBox id="assignApproveDlgFormByCT.leaderApproveName" name="createForm.leaderApproveName" cssStyle="display:none" key=""/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Nội dung ý kiến của cục trưởng"/></td>
            <td>
                <sd:Textarea key="" id="assignApproveDlgFormByCT.leaderStaffRequest" name="createForm.leaderStaffRequest" rows="4" cssStyle="width:99%" maxlength="2000" trim="true"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <sx:ButtonSave onclick="onAssignApproveFormSave();"/>
                <sx:ButtonClose onclick="onCloseAssignApproveFormByCT();"/>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    afterOnAssignApproveFormSave = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        onCloseAssignApproveFormByCT();
        page.search();
    };
    onAssignApproveFormSave = function () {
        if (validateApproveAssignDlgByCT()) {
            sd.connector.post("filesAction!onAssignApproveByCT.do?" + token.getTokenParamString(), null, "assignApproveDlgFormByCT", null, afterOnAssignApproveFormSave);
        }
    };
    validateApproveAssignDlgByCT = function () {
        var leaderId = dijit.byId("assignApproveDlgFormByCT.leaderApproveId").getValue();
        if (leaderId == -1) {
            alert("Bạn chưa chọn lãnh đạo phê duyệt");
            dijit.byId("assignApproveDlgFormByCT.leaderApproveId").focus();
            return false;
        } else {
            /*
            var leaderStaffRequest = dijit.byId("assignApproveDlgFormByCT.leaderStaffRequest").getValue();
            if (leaderStaffRequest.trim().length == 0) {
                alert("Bạn chưa nhập 'Nội dung ý kiến của cục trưởng'");
                dijit.byId("reviewForm.leaderStaffRequest").focus();
                return false;
            } else {
            */
                var leaderApproveName = dijit.byId("assignApproveDlgFormByCT.leaderApproveId").attr("displayedValue");
                dijit.byId("assignApproveDlgFormByCT.leaderApproveName").setValue(leaderApproveName);
            //}
        }
        return true;
    };
    onCloseAssignApproveFormByCT = function () {
        dijit.byId("assignApproveDlgByCT").hide();
    };
</script>