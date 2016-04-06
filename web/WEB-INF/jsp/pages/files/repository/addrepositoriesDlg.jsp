<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sx:ResultMessage id="resultMessage" />
<form id="repoForm" name="repoForm">
    <table width="100%;" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="40%"></td>
            <td width="60%"></td>
        </tr>
         <tr>
          
            <td>
                <sd:TextBox id="repoForm.repId" name="repoForm.repId" cssStyle="display:none" key=""/>
            </td>
           
        
        
        
        <tr>
            <td align="right">
                <sx:Label key="Tên Kho:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="repoForm.repName"
                            name="repoForm.repName"
                            key=""
                            maxlength="250"
                            />
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Nơi lưu trữ:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="repoForm.repAddress"
                            name="repoForm.repAddress"
                            key=""
                            maxlength="250"
                            />
            </td>
        </tr>

        <tr>
            <td align="right">
                <sx:Label key="Trạng thái" require="true"/>
            </td>
            <td>
                <sd:SelectBox id="repoForm.isActive" name="repoForm.isActive" key="" >
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
        var categoryType = dijit.byId("repoForm.repName").getValue();
        if (categoryType == null || categoryType.trim().length == 0) {
            alert("Bạn chưa nhập tên kho");
            dijit.byId("repoForm.repName").focus();
            return false;
        }
        var categoryName = dijit.byId("repoForm.repAddress").getValue();
        if (categoryName == null || categoryName.trim().length == 0) {
            alert("Bạn chưa nhập nơi lưu trữ");
            dijit.byId("repoForm.repAddress").focus();
            return false;
        }
        var isActive = dijit.byId("repoForm.isActive").getValue();
        if (isActive < 0) {
            alert("Trạng thái chưa chọn ");
            dijit.byId("repoForm.isActive").focus();
            return false;
        }
        return true;
    };
    page.save = function() {
        if (page.validate()) {
            sd.connector.post("filesAction!onInsertRepo.do?" + token.getTokenParamString(), null, "repoForm", null, page.afterSave);
        }
    };

    page.afterSave = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {
            page.close();
        }
        page.search();
    };

    page.close = function() {
        document.getElementById("repoForm.repName").value="";
         document.getElementById("repoForm.repAddress").value="";
        dijit.byId("RepositoryDlg").hide();
    };
</script>