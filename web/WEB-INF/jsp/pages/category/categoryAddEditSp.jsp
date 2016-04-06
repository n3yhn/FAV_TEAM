<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sx:ResultMessage id="categoryResultMessageSp" />
<form id="categoryAddEditFormSp">
    <table width="100%;" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="30%"></td>
            <td width="70%"></td>
        </tr>
        <tr>
            <td colspan="2" style="display: none">
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="categoryAddEditFormSp.type"
                            key=""
                            name="categoryAddEditFormSp.type"
                            value="${fn:escapeXml(type)}">
                </sd:TextBox>
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="categoryAddEditFormSp.categoryId"
                            key=""
                            name="categoryAddEditFormSp.categoryId">
                </sd:TextBox>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="category.code" require="true"/>
            </td>
            <td>

                <sd:SelectBox id="categoryAddEditFormSp.code" name="categoryAddEditFormSp.code" key="" data="lstSpCode" valueField="categoryName" labelField="categoryName" />
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="category.name" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="categoryAddEditFormSp.name"
                            key=""
                            name="categoryAddEditFormSp.name"
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
                            id="categoryAddEditFormSp.description"
                            key=""
                            name="categoryAddEditFormSp.description"
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
                <sd:SelectBox id="categoryAddEditFormSp.isActive" name="categoryAddEditFormSp.isActive" key="" >
                    <sd:Option value='-1'>-- Chọn --</sd:Option>
                    <sd:Option value='1' selected="true">Hoạt động</sd:Option>
                    <sd:Option value='0'>Không hoạt động</sd:Option>
                </sd:SelectBox>
            </td>
        </tr>
        <tr style="text-align: center">
            <td colspan="2">
                <sx:ButtonSave onclick="page.saveSp()" />
                <sx:ButtonClose onclick="page.onCloseSp()" />
            </td>
        </tr>
    </table>
</form>

<script>
    var codeSp = dijit.byId("categoryAddEditFormSp.code");
    var nameSp = dijit.byId("categoryAddEditFormSp.name");

    page.saveSp = function() {
        if (codeSp == "-- Chọn --") {
            codeSp.focus();
            msg.alert("Mã không được để trống", '<sd:Property>confirm.title</sd:Property>');
            return false;
        }
        if (page.getLengthText(nameSp.getValue()) == 0) {
            nameSp.focus();
            msg.alert("Tên không được để trống", '<sd:Property>confirm.title</sd:Property>');
            return false;
        }
        var isActive = dijit.byId("categoryAddEditFormSp.isActive").getValue();
        if (isActive < 0) {
            alert("Bạn chưa chọn trạng thái");
            dijit.byId("categoryAddEditFormSp.isActive").focus();
            return false;
        }
        //sd.connector.post("category!checkCategorySp.do", null, "categoryAddEditFormSp", null, page.afterCheckCategory);
      sd.connector.post("category!insertCategorySp.do?" + token.getTokenParamString(), null, "categoryAddEditFormSp", null, page.afterInsertCategorySp);
    };

   

            
            
        
    

    page.afterInsertCategorySp = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var customInfo = obj.customInfo;
        
        if (insertDialog) {
            resultMessage_show("categoryResultMessageSp", result[0], result[1], 5000);
            dijit.byId("categoryAddEditFormSp.categoryId").setValue("");
            dijit.byId("categoryAddEditFormSp.code").setValue("-- Chọn --");
            dijit.byId("categoryAddEditFormSp.name").setValue("");
            dijit.byId("categoryAddEditFormSp.description").setValue("");
            dijit.byId("categoryAddEditFormSp.isActive").setValue("1");
            grid.vtReload('category!search.do?', "categorySearchForm", null, null);
            dlgCategoryTypeSp.hide();
             msg.alert("Thêm mới danh mục thành công");
        } else {
            page.close();
            resultMessage_show("categoryResultDeleteMessage", result[0], result[1], 5000);
        }
    };

    page.closeSp = function() {
        dlgCategoryTypeSp.hide();
        dijit.byId("categoryAddEditFormSp.categoryId").setValue("");
        dijit.byId("categoryAddEditFormSp.code").setValue("-- Chọn --");
        dijit.byId("categoryAddEditFormSp.name").setValue("");
        dijit.byId("categoryAddEditFormSp.description").setValue("");
        dijit.byId("categoryAddEditFormSp.isActive").setValue("1");
    };
    page.onCloseSp = function() {
        msg.confirm('<sd:Property>confirm.close</sd:Property>', '<sd:Property>confirm.title</sd:Property>', page.closeSp);
    };
    page.closeExecuteSp = function() {
        dlgCategoryTypeSp.hide();
        page.search();
    };
</script>