<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sx:ResultMessage id="ESFDResultMessage" />
<form id="createForm">
    <table width="100%;" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="30%" style="display: none"><sd:TextBox cssStyle="width:100%;" trim="true"
                        id="statusFileAddEditForm.fileId"
                        key=""
                        name="createForm.fileId">
                </sd:TextBox>
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="statusFileAddEditForm.nameStaffProcess"
                            key=""
                            name="createForm.nameStaffProcess">
                </sd:TextBox>
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="statusFileAddEditForm.leaderEvaluateName"
                            key=""
                            name="createForm.leaderEvaluateName">
                </sd:TextBox>  
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="statusFileAddEditForm.leaderReviewName"
                            key=""
                            name="createForm.leaderReviewName">
                </sd:TextBox>
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="statusFileAddEditForm.leaderApproveName"
                            key=""
                            name="createForm.leaderApproveName">
                </sd:TextBox>
            </td>
            <td width="70%" style="display: none"></td>
        </tr>
        <tr>
            <td align="left" width="30%">
                <sx:Label key="Trạng thái hồ sơ" require=""/>
            </td>
            <td style="display:" width="70%">
                <sd:SelectBox cssStyle="width:100%"
                              id="statusFileAddEditForm.status"
                              key="" valueField="code" labelField="name"
                              name="createForm.status" >
                </sd:SelectBox>                             
            </td>        
        <tr>
            <td align="left">
                <sx:Label key="Tên chuyên viên thẩm định" require=""/>
            </td>
            <td align="left">                
                <sd:SelectBox cssStyle="width:100%"
                              id="statusFileAddEditForm.staffProcess"
                              key="" valueField="userId" labelField="fullName"
                              name="createForm.staffProcess" >
                </sd:SelectBox>                
            </td></tr>  
        <tr>
            <td align="left">
                <sx:Label key="Tên lãnh đạo phòng thẩm định" require=""/>
            </td>
            <td align="left">
                <sd:SelectBox cssStyle="width:100%"
                              id="statusFileAddEditForm.leaderEvaluateId"
                              key="" valueField="userId" labelField="fullName"
                              name="createForm.leaderEvaluateId" >
                </sd:SelectBox>                           
            </td></tr>  
        <tr>
            <td align="left">
                <sx:Label key="Tên lãnh đạo phòng xem xét" require=""/>
            </td>
            <td align="left">                
                <sd:SelectBox cssStyle="width:100%"
                              id="statusFileAddEditForm.leaderReviewId"
                              key="" valueField="userId" labelField="fullName"
                              name="createForm.leaderReviewId" >
                </sd:SelectBox>                
            </td></tr>              
        <tr>
            <td align="left">
                <sx:Label key="Tên lãnh đạo cục phê duyệt" require=""/>
            </td>
            <td align="left">
                <sd:SelectBox cssStyle="width:100%"
                              id="statusFileAddEditForm.leaderApproveId"
                              key="" valueField="userId" labelField="fullName"
                              name="createForm.leaderApproveId" >
                </sd:SelectBox>                
            </td>
        </tr>    
        <tr style="text-align: center">
            <td colspan="2">
                <sx:ButtonSave onclick="page.saveESFD()" />
                <sx:ButtonClose onclick="page.onCloseESFD()" />
            </td>
        </tr>
    </table>
</form>

<script>
    page.saveESFD = function () {
        var leaderApproveName = dijit.byId("statusFileAddEditForm.status").attr("displayedValue");
        dijit.byId("statusFileAddEditForm.leaderApproveName").setValue(leaderApproveName);

        var staffProcess = dijit.byId("statusFileAddEditForm.staffProcess").attr("displayedValue");
        dijit.byId("statusFileAddEditForm.nameStaffProcess").setValue(staffProcess);

        var leaderEvaluateId = dijit.byId("statusFileAddEditForm.leaderEvaluateId").attr("displayedValue");
        dijit.byId("statusFileAddEditForm.leaderEvaluateName").setValue(leaderEvaluateId);

        var leaderReviewId = dijit.byId("statusFileAddEditForm.leaderReviewId").attr("displayedValue");
        dijit.byId("statusFileAddEditForm.leaderReviewName").setValue(leaderReviewId);

        var leaderApproveId = dijit.byId("statusFileAddEditForm.leaderApproveId").attr("displayedValue");
        dijit.byId("statusFileAddEditForm.leaderApproveName").setValue(leaderApproveId);

        sd.connector.post("filesAction!onSaveEditFileStatus.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSaveESFD);
//        sd.connector.post("filesAction!checkEditStatusFiles.do", null, "createForm", null, page.afterCheckEditStatusFiles);
    };

//    page.afterCheckEditStatusFiles = function (data) {
//        var obj = dojo.fromJson(data);
//    var result = obj.items;
//    var customInfo = obj.customInfo;
//
//    var result0 = result[0];
//    if (result0 == "3") {
//        resultMessage_show("ESFDResultMessage", result[0], result[1], 5000);
//    } else {
//        sd.connector.post("filesAction!onSaveEditFileStatus.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSaveESFD);
//    }
//    };

    page.afterSaveESFD = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result0 = result[0];
        if (result0 == "3") {
            resultMessage_show("ESFDResultMessage", result[0], result[1], 5000);
        } else {
            resultMessage_show("ESFDResultMessage", result[0], result[1], 5000);
            //page.onClose();
        }
    };

    page.onCloseESFD = function () {
        msg.confirm('<sd:Property>confirm.close</sd:Property>', '<sd:Property>confirm.title</sd:Property>', page.closeExecute);
            };
            page.closeExecute = function () {
                viewStatusFilesDlg.hide();
            };
</script>