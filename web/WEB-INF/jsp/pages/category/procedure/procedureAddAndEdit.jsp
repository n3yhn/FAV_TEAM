<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sx:ResultMessage id="categoryResultMessage" />
<form id="createForm">
    <table width="100%;" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="30%"></td>
            <td width="70%"></td>
        </tr>
        <tr>
            <td colspan="2" style="display: none">
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="createForm.procedureId"
                            key=""
                            name="createForm.procedureId"/>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="category.code" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.code"
                            key=""
                            name="createForm.code"
                            maxlength="20"
                            />
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="category.name" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.name"
                            key=""
                            name="createForm.name"
                            maxlength="255"
                            />
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Mô tả"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.description"
                            key=""
                            name="createForm.description"
                            maxlength="200"/>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Danh sách tài liệu"/>
            </td>
            <td>
                <sd:Textarea cssStyle="width:100%" trim="true"
                             id="createForm.fileList"
                             key=""
                             name="createForm.fileList"
                             maxlength="1000"
                             rows="4"
                             >
                </sd:Textarea>

            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Deadline"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.deadline"
                            key=""
                            name="createForm.deadline"
                            maxlength="5" mask="digit"/>
            </td>
        </tr>

        <tr>
            <td align="right">
                <sx:Label key="Phí thẩm xét"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.fee"
                            key=""
                            name="createForm.fee"
                            maxlength="5" mask="digit"/>
            </td>
        </tr>

        <tr>
            <td align="right">
                <sx:Label key="Phí cấp giấy công bố"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.feeAnnouce"
                            key=""
                            name="createForm.feeAnnouce"
                            maxlength="5" mask="digit"/>
            </td>
        </tr>

        <tr>
            <td align="right">
                <sx:Label key="Trạng thái" require="true"/>
            </td>
            <td>
                <sd:SelectBox id="createForm.isActive" name="createForm.isActive" key="" >
                    <sd:Option value='-1'>-- Chọn --</sd:Option>
                    <sd:Option value='1' selected="true">Hoạt động</sd:Option>
                    <sd:Option value='0'>Không hoạt động</sd:Option>
                </sd:SelectBox>
            </td>
        </tr>
        <tr style="text-align: center">
            <td colspan="2">
                <sx:ButtonSave onclick="page.save()" />
                <sx:ButtonClose onclick="page.onClose()" />
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    var code = dijit.byId("createForm.code");
    var name = dijit.byId("createForm.name");

    page.save = function() {

        if (page.getLengthText(code.getValue()) == 0) {
            code.focus();
            msg.alert("Mã không được để trống", '<sd:Property>confirm.title</sd:Property>');
                        return false;
                    }

                    if (page.getLengthText(name.getValue()) == 0) {
                        name.focus();
                        msg.alert("Tên không được để trống", '<sd:Property>confirm.title</sd:Property>');
                                    return false;
                                }

                                var deadline = dijit.byId("createForm.deadline").getValue();
                                if (isNaN(parseFloat(deadline)) || !isFinite(deadline)) {
                                    dijit.byId("createForm.deadline").focus();
                                    msg.alert("Deadline không phải là số", '<sd:Property>confirm.title</sd:Property>');
                                                return false;
                                            }

                                            if (dijit.byId("createForm.code"))
                                                var isActive = dijit.byId("createForm.isActive").getValue();
                                            if (isActive < 0) {
                                                alert("Bạn chưa chọn trạng thái");
                                                dijit.byId("createForm.isActive").focus();
                                                return false;
                                            }
                                            sd.connector.post("procedureAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterInsert);

                                        };

                                        page.afterInsert = function(data) {
                                            var obj = dojo.fromJson(data);
                                            var result = obj.items;
                                            if (result[0] == 1) {
                                                if (dijit.byId("createForm.procedureId").getValue() == null || dijit.byId("createForm.procedureId").getValue() == "") {
                                                    resultMessage_show("categoryResultMessage", result[0], result[1], 5000);
                                                    dijit.byId("createForm.procedureId").setValue("");
                                                    dijit.byId("createForm.code").setValue("");
                                                    dijit.byId("createForm.name").setValue("");
                                                    dijit.byId("createForm.description").setValue("");
                                                    sd._("createForm.fileList").setValue("");
                                                    dijit.byId("createForm.deadline").setValue("");
                                                    dijit.byId("createForm.isActive").setValue("1");
                                                } else {
                                                    page.close();
                                                    resultMessage_show("categoryResultDeleteMessage", result[0], result[1], 5000);
                                                }
                                                page.search();
                                            } else {
                                                resultMessage_show("categoryResultMessage", result[0], result[1], 5000);
                                            }
                                        };

                                        page.close = function() {
                                            dlgCategory.hide();
                                            dijit.byId("createForm.procedureId").setValue("");
                                            dijit.byId("createForm.code").setValue("");
                                            dijit.byId("createForm.name").setValue("");
                                            dijit.byId("createForm.description").setValue("");
                                            sd._("createForm.fileList").setValue("");
                                            dijit.byId("createForm.deadline").setValue("");
                                            dijit.byId("createForm.isActive").setValue("1");
                                        };
                                        page.onClose = function() {
                                            dlgCategory.hide();
                                        };
</script>