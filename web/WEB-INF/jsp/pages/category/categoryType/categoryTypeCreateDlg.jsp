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
                <sd:TextBox cssStyle="width:100%" trim="true"
                    id="createForm.categoryType"
                            name="createForm.categoryType"
                            key=""
                            maxlength="20"
                            />
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Tên loại danh mục:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                    id="createForm.categoryName"
                            name="createForm.categoryName"
                            key=""
                            maxlength="50"
                            />
                <sd:TextBox id="createForm.categoryTypeId" name="createForm.categoryTypeId" cssStyle="display:none" key=""/>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Mô tả loại danh mục"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.desciption"
                            key=""
                            name="createForm.desciption"
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
        var categoryType = dijit.byId("createForm.categoryType").getValue();
        if (categoryType == null || categoryType.trim().length == 0) {
            alert("Bạn chưa nhập loại danh mục");
            dijit.byId("createForm.categoryType").focus();
            return false;
        }
        var categoryName = dijit.byId("createForm.categoryName").getValue();
        if (categoryName == null || categoryName.trim().length == 0) {
            alert("Bạn chưa nhập tên loại danh mục");
            dijit.byId("createForm.categoryName").focus();
            return false;
        }
        var isActive = dijit.byId("createForm.isActive").getValue();
        if (isActive < 0) {
            alert("Trạng thái chưa chọn ");
            dijit.byId("createForm.isActive").focus();
            return false;
        }
        return true;
    };
    page.save = function() {
        if (page.validate()) {
            sd.connector.post("categoryTypeAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSave);
        }
    };

    page.afterSave = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {
            var Id = dijit.byId("createForm.categoryTypeId").getValue();
            if( Id == null || Id == "" ){
                page.clearInsertForm();
            } else {
                page.close();
            }
            page.search();02
        }
    };

    page.close = function() {
        dlgCategoryType.hide();
    };
</script>