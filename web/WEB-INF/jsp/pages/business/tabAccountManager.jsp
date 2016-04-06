<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

    <sx:ResultMessage id="categoryResultMessage" />
    <form id="categoryAddEditForm">
        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <td width="30%"></td>
                <td width="70%"></td>
            </tr>
            <tr>
                <td colspan="2" style="display: none">
                    <sd:TextBox cssStyle="width:100%;"
                                id="categoryAddEditForm.type"
                                key=""
                                name="categoryAddEditForm.type"
                                value="${fn:escapeXml(type)}">
                    </sd:TextBox>
                    <sd:TextBox cssStyle="width:100%;" trim="true"
                                id="categoryAddEditForm.categoryId"
                                key=""
                                name="categoryAddEditForm.categoryId"
                                >
                    </sd:TextBox>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <sx:Label key="category.code" require="true"/>
                </td>
                <td>
                    <sd:TextBox cssStyle="width:100%" trim="true"
                                id="categoryAddEditForm.code"
                                key=""
                                name="categoryAddEditForm.code"
                                maxlength="20"
                                >
                    </sd:TextBox>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <sx:Label key="category.name" require="true"/>
                </td>
                <td>
                    <sd:TextBox cssStyle="width:100%" trim="true"
                                id="categoryAddEditForm.name"
                                key=""
                                name="categoryAddEditForm.name"
                                maxlength="255"
                                >
                    </sd:TextBox>
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
    var code = dijit.byId("categoryAddEditForm.code");
    var name = dijit.byId("categoryAddEditForm.name");
    
    page.save = function() {

        if (page.getLengthText(code.getValue()) == 0) {
            code.focus();
            msg.alert("Mã không được để trống",'<sd:Property>confirm.title</sd:Property>');
            return false;
        }

        if (page.getLengthText(name.getValue()) == 0) {
            name.focus();
            msg.alert("Tên không được để trống",'<sd:Property>confirm.title</sd:Property>');
            return false;
        }
        sd.connector.post("category!checkCategory.do", null, "categoryAddEditForm", null, page.afterCheckCategory);
        //sd.connector.post("category!insertCategory.do", null, "categoryAddEditForm", null, page.afterInsertCategory);

    };

    page.afterCheckCategory = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var customInfo = obj.customInfo;

        var result0 = result[0];
        if (result0 == "3") {
            resultMessage_show("categoryResultMessage", result[0], result[1], 5000);
        } else {
            sd.connector.post("category!insertCategory.do?" + token.getTokenParamString(), null, "categoryAddEditForm", null, page.afterInsertCategory);
        }
    };

    page.afterInsertCategory = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var customInfo = obj.customInfo;                
      
        grid.vtReload('category!search.do?', "categorySearchForm",null,null);
        
        if (insertDialog) {
            resultMessage_show("categoryResultMessage", result[0], result[1], 5000);
            dijit.byId("categoryAddEditForm.categoryId").setValue("");
            dijit.byId("categoryAddEditForm.code").setValue("")
            dijit.byId("categoryAddEditForm.name").setValue("")
        } else {
            page.close();
            resultMessage_show("categoryResultDeleteMessage", result[0], result[1], 5000);
        }
    };

    page.close = function() {
        dlgCategory.hide();
        dijit.byId("categoryAddEditForm.categoryId").setValue("");
        dijit.byId("categoryAddEditForm.code").setValue("")
        dijit.byId("categoryAddEditForm.name").setValue("")        
    };
</script>