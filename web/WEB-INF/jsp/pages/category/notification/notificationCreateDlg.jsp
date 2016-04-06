<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="tags" tagdir="/WEB-INF/tags" %>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<sx:ResultMessage id="resultMessage" />
<form id="createForm" name="createForm">
    <table width="100%;" cellpadding="0px" cellspacing="5px">
        <!--        <tr>
                    <td width="10%"></td>
                    <td width="35%"></td>
                    <td width="10%"></td>
                    <td width="40%"></td>
                </tr>-->
        <tr>
            <td align="center">
                <sx:Label key="Nội Dung Thông báo" require="true"/>
            </td>
            <td>
                <sd:Textarea cssStyle="width:100%" trim="true"
                             id="createForm.content"
                             name="createForm.content"
                             key=""
                             maxlength="10000"
                             />
                <sd:TextBox id="createForm.noticeId" name="createForm.noticeId" cssStyle="display:none" key=""/>
            </td>
        </tr>
        <tr>
            <td align="center">
                <sx:Label key="Thứ tự" require="true"/>
            </td>
            <td>                
                <sd:TextBox type="number" id="createForm.sort" name="createForm.sort" key="" maxlength="18" cssStyle="width:100%"  />

            </td>
        </tr>
        <tr>

            <td align="center">
                <sx:Label key="Trạng thái" require="true"/>
            </td>
            <td>
                <sd:SelectBox id="createForm.isActive" name="createForm.isActive" key="" cssStyle="width:100%">
                    <sd:Option value='-1'>-- Chọn --</sd:Option>
                    <sd:Option value='1' selected="true">Hoạt động</sd:Option>
                    <sd:Option value='0'>Không hoạt động</sd:Option>
                </sd:SelectBox>
            </td>
        </tr>




        <tr style="text-align: center">
            <td colspan="4">
                <sx:ButtonSave onclick="page.save()" />
                <sx:ButtonClose onclick="page.close()" />
            </td>
        </tr>
    </table>
</form>

<script>
    page.validate = function() {
        var name = dijit.byId("createForm.content").getValue();
        if (name == null || name.trim().length == 0) {
            alert("Bạn phải nhập nội dung thông báo!");
            dijit.byId("createForm.content").focus();
            return false;
        }

        var sort = dijit.byId("createForm.sort").getValue();

        if (sort == null || sort.trim().length == 0) {
            alert("Bạn chưa nhập số thứ tự!");
            dijit.byId("createForm.sort").focus();
            return false;
        }

        var isActive = dijit.byId("createForm.isActive").getValue();
        if (isActive == -1) {
            alert("Bạn chưa chọn trạng thái");
            dijit.byId("createForm.isActive").focus();
            return false;
        }

        return true;
    };
    page.save = function() {
        if (page.validate()) {
            sd.connector.post("notificationAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSave);
        }
    };
    page.afterSave = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        if (insertDialog) {
            page.close();
            resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
        } else {
            page.close();
            resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
        }
        page.search();
    };
    page.close = function() {
        page.clearInsertForm();
        dlgNoti.hide();
    };

</script>