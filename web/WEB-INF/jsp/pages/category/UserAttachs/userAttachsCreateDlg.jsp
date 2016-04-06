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
    <table width="100%" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="25%"></td>
            <td width="25%"></td>
            <td width="25%"></td>
            <td width="25%"></td>
        </tr>
        <tr>
            <td>
                <sd:TextBox id="createForm.userAttachsId" name="createForm.userAttachsId" cssStyle="display:none" key=""/>
                <sx:Label key="Tên hồ sơ" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.attachName"
                            name="createForm.attachName"
                            key=""
                            maxlength="250"
                            />
            </td>
            <td>
                <sx:Label key="Mô tả hồ sơ" require="false"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.description"
                            name="createForm.description"
                            key=""
                            maxlength="1800"
                            />
            </td>
        </tr>
        <tr>
            <td>
                <sx:Label key="Ngày hiệu lực" require="true"/>
            </td>
            <td>
                <sx:DatePicker cssStyle="width:100%"
                               id="createForm.effectiveDate"
                               key=""
                               name="createForm.effectiveDate" format="dd/MM/yyyy"/>
            </td>
            <td>
                <sx:Label key="Ngày hết hạn"/>
            </td>
            <td>
                <sx:DatePicker cssStyle="width:100%"
                               id="createForm.expireDate"
                               key=""
                               name="createForm.expireDate" format="dd/MM/yyyy"/>
            </td>
        </tr>
        <tr>
            <td>
                <sx:Label key="File đính kèm:" require="true"/>
            </td>
            <td>
                <sx:upload_one action="userAttachsAction!uploadFile.do?id=createForm.upload" extension="*.jpg, *.jpeg, *.bmp, *.png, *.gif, *.tif,*.pdf,*.doc,*.docx,*.zip,*.rar, *.docx, *.xls, *.xlsx" id="createForm.upload" maxSize="20"/>
                <sd:TextBox id="createForm.uploadId" name="createForm.uploadId" cssStyle="display:none;" key=""/>
            </td>
            <td>
                <sx:Label key="Trạng thái" require="true"/>
            </td>
            <td>
                <sd:SelectBox id="createForm.status" name="createForm.status" key="" >
                    <sd:Option value='-1'>-- Chọn --</sd:Option>
                    <sd:Option value='0' selected="true">Không hoạt động</sd:Option>
                    <sd:Option value='1'>Hoạt động</sd:Option>
                </sd:SelectBox>
            </td>
        </tr>
    </table>
    <div style="text-align: center">
        <sx:ButtonSave onclick="page.save();"></sx:ButtonSave>  
        <sx:ButtonClose onclick="page.close();"></sx:ButtonClose>  
    </div>
</form>

<script>
    page.validate = function() {
        var standardCode = dijit.byId("createForm.attachName").getValue();
        if (standardCode == null || standardCode.trim().length == 0) {
            alert("Bạn chưa nhập tên hồ sơ");
            dijit.byId("createForm.attachName").focus();
            return false;
        }
        var effectiveDate = dijit.byId("createForm.effectiveDate").getValue();
        if (effectiveDate == null) {
            alert("Bạn chưa nhập ngày hiệu lực.");
            dijit.byId("createForm.effectiveDate").focus();
            return false;
        }
        var expireDate = dijit.byId("createForm.expireDate").getValue();
//        if (expireDate == null) {
//            alert("Bạn chưa nhập ngày hết hạn");
//            dijit.byId("createForm.expireDate").focus();
//            return false;
//        }
        if (expireDate != null && effectiveDate > expireDate) {
            alert("Ngày hiệu lực lớn hơn Hết hạn.");
            dijit.byId("createForm.effectiveDate").focus();
            return false;
        }
        var attachIds = getListAttachId("createForm.upload");
        if (attachIds == null || attachIds.trim().length == 0) {
            alert("Bạn chưa đính kèm file văn bản");
            return false;
        } else {
            dijit.byId("createForm.uploadId").setValue(attachIds);
        }
        var status = dijit.byId("createForm.status").getValue();
        if (status < 0) {
            alert("Trạng thái chưa chọn ");
            dijit.byId("createForm.status").focus();
            return false;
        }

        return true;
    };
    page.save = function() {
        if (page.validate()) {
            sd.connector.post("userAttachsAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSave);
        }
    };
    page.afterSave = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {
            var Id = dijit.byId("createForm.userAttachsId").getValue();
            if (Id == null || Id == "") {
                page.clearInsertForm();
            } else {
                page.close();
            }
            page.search();
        }
    };
    page.close = function() {
        dlg.hide();
    };
</script>