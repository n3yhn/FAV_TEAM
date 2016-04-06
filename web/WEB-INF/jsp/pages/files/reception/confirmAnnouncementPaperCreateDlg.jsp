<%-- 
    Document   : rulesAnnounceCreateDlg - 
Nhập thông tin giấy xác nhận công bố phù hợp quy định an toàn thực phẩm
    Created on : 
    Author     : vtit_binhnt53
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<sx:ResultMessage id="resultMessage" />
<form id="createForm" name="createForm">
    <table width="100%;" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="40%"></td>
            <td width="60%"></td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Số ký hiệu giấy xác nhận:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%"
                            id="createForm.confirmationNo"
                            name="createForm.confirmationNo"
                            key=""
                            maxlength="20"
                            trim="true"/>
                <sd:TextBox id="createForm.confirmAnnouncementPaperId" 
                            name="createForm.confirmAnnouncementPaperId" 
                            cssStyle="display:none" 
                            key=""
                            trim="true"/>
                <sd:TextBox id="createForm.isActive" 
                            name="createForm.isActive" 
                            cssStyle="display:none" 
                            key=""
                            trim="true"/>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Ngày cấp:" require="true"/>
            </td>
            <td>
                <sx:DatePicker cssStyle="width:100%"
                               id="createForm.dateOfIssue"
                               key=""
                               name="createForm.dateOfIssue" 
                               format="dd/MM/yyyy"/>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Cơ quan cấp" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%"
                            id="createForm.issueAgencyName"
                            key=""
                            name="createForm.issueAgencyName"
                            maxlength="200"
                            trim="true"/>
            </td>
        </tr>
        <tr style="text-align: center">
            <td colspan="2">
                <sx:ButtonSave onclick="page.save()" />
                <sx:ButtonClose onclick="page.close()" />
            </td>
        </tr>
    </table>
</form>
<script>

    page.validate = function() {
        var confirmationNo = dijit.byId("createForm.confirmationNo").getValue();
        if (confirmationNo == null || confirmationNo.trim().length == 0) {
            alert("Bạn chưa nhập [Số ký hiệu giấy xác nhận]");
            dijit.byId("createForm.confirmationNo").focus();
            return false;
        }
        var dateOfIssue = dijit.byId("createForm.dateOfIssue").getValue();
        if (dateOfIssue == null) {
            alert("Bạn chưa nhập [Ngày cấp]");
            dijit.byId("createForm.dateOfIssue").focus();
            return false;
        }
        var issueAgencyName = dijit.byId("createForm.issueAgencyName").getValue();
        if (issueAgencyName == null || issueAgencyName.trim().length == 0) {
            alert("Bạn chưa nhập [Đơn vị cấp]");
            dijit.byId("createForm.issueAgencyName").focus();
            return false;
        }
        return true;
    }
    page.save = function() {
        if (page.validate()) {
            sd.connector.post("confirmAnnouncementPaperAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSave);
        }
    };

    page.afterSave = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {
            var Id = dijit.byId("createForm.confirmAnnouncementPaperId").getValue();
            if (Id == null || Id == "") {
                page.clearInsertForm();
            } else {
                page.close();
            }
            page.search();
        }
        
    };

    page.close = function() {
        dlgConfirmAnnouncementPaperCreate.hide();
    };
</script>