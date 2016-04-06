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
                <sx:Label key="Loại danh mục:" require="true"/>
            </td>
            <td>
                <sd:SelectBox id="createForm.type" name="createForm.type" key="" data="lstType" valueField="categoryType" labelField="categoryName" />
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Mã danh mục danh mục:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.code"
                            name="createForm.code"
                            key=""
                            maxlength="20"
                            />
                <sd:TextBox id="createForm.categoryId" name="createForm.categoryId" cssStyle="display:none" key=""/>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Tên danh mục" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.name"
                            key=""
                            name="createForm.name"
                            maxlength="255"
                            >
                </sd:TextBox>
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
                            maxlength="200"
                            >
                </sd:TextBox>
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
                <sx:ButtonClose onclick="page.close()" />
            </td>
        </tr>
    </table>
</form>

<script>

    page.validate = function() {
        var type = dijit.byId("createForm.type").getValue();
        if (type == null || type.trim().length == 0 || type == "0") {
            alert("Bạn chưa chọn loại danh mục");
            dijit.byId("createForm.type").focus();
            return false;
        }
        var code = dijit.byId("createForm.code").getValue();
        if (code == null || code.trim().length == 0) {
            alert("Bạn chưa nhập mã danh mục");
            dijit.byId("createForm.code").focus();
            return false;
        }
        var name = dijit.byId("createForm.name").getValue();
        if (name == null || name.trim().length == 0) {
            alert("Bạn chưa nhập tên danh mục");
            dijit.byId("createForm.name").focus();
            return false;
        }
        var isActive = dijit.byId("createForm.isActive").getValue();
        if (isActive < 0) {
            alert("Bạn chưa chọn trạng thái");
            dijit.byId("createForm.isActive").focus();
            return false;
        }
        return true;
    };
    page.save = function() {
        if (page.validate()) {
            sd.connector.post("categoryAction!onInsertCategory.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSave);
        }
    };

    page.afterSave = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {
            var Id = dijit.byId("createForm.categoryId").getValue();
            if (Id == null || Id == "") {
                page.clearInsertForm();
            } else {
                page.close();
            }
            page.search();
        }
    };

    page.close = function() {
        dojo.attr(resultMessage, {
                style:{
                    display:"none"
                },
                innerHTML: ""
            });
        dlg.hide();
    };
</script>