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
                <sx:Label key="Mã cấu hình:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%"
                    id="createForm.parameterCode"
                            name="createForm.parameterCode"
                            key=""
                            maxlength="20"
                            />
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Tên loại cấu hình:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%"
                    id="createForm.parameterName"
                            name="createForm.parameterName"
                            key=""
                            maxlength="50"
                            />
                <sd:TextBox id="createForm.parameterId" name="createForm.parameterId" cssStyle="display:none" key=""/>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Kiểu dữ liệu:"/>
            </td>
            <td>
                <sd:SelectBox id="createForm.parameterDataTypeId" name="createForm.parameterDataTypeId" key="" >
                    <sd:Option value='-1' selected="true">-- Chọn --</sd:Option>                    
                    <sd:Option value='0'>Ký tự</sd:Option>
                    <sd:Option value='1'>Ngày tháng</sd:Option>
                    <sd:Option value='2'>Số nguyên</sd:Option>
                    <sd:Option value='3'>Số thực</sd:Option>
                </sd:SelectBox>
                    <sd:TextBox id="createForm.parameterDataTypeName" name="createForm.parameterDataTypeName" cssStyle="display:none" key=""/>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Giá trị:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%"
                            id="createForm.parameterValue"
                            key=""
                            name="createForm.parameterValue"
                            maxlength="200"
                            >
                </sd:TextBox>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Mô tả:"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%"
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
        var parameterCode = dijit.byId("createForm.parameterCode").getValue();
        if (parameterCode == null || parameterCode.trim().length == 0) {
            alert("Bạn chưa nhập mã cấu hình");
            dijit.byId("createForm.parameterCode").focus();
            return false;
        }
        var parameterName = dijit.byId("createForm.parameterName").getValue();
        if (parameterName == null || parameterName.trim().length == 0) {
            alert("Bạn chưa nhập tên cấu hình");
            dijit.byId("createForm.parameterName").focus();
            return false;
        }
        var parameterTypeId = dijit.byId("createForm.parameterDataTypeId").getValue();
        if (parameterTypeId < 0) {
            alert("Bạn chưa chọn kiểu dữ liệu");
            dijit.byId("createForm.parameterDataTypeId").focus();
            return false;
        }
        else{
          dijit.byId("createForm.parameterDataTypeName").setValue(dijit.byId("createForm.parameterDataTypeId").attr("displayedValue"));
        }
        
        var parameterValue = dijit.byId("createForm.parameterValue").getValue();
        if (parameterValue == null || parameterValue.trim().length == 0) {
            alert("Bạn chưa nhập giá trị");
            dijit.byId("createForm.parameterValue").focus();
            return false;
        }
        
        
        
          
        
        var isActive = dijit.byId("createForm.isActive").getValue();
        if (isActive < 0) {
            alert("Trạng thái chưa chọn ");
            dijit.byId("createForm.isActive").focus();
            return false;
        }
        return true;
    }
    page.save = function() {
        if (page.validate()) {
            sd.connector.post("parameterAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSave);
        }
    }

    page.afterSave = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {
            var Id = dijit.byId("createForm.parameterId").getValue();
            if( Id == null || Id == "" ){
                page.clearInsertForm();
            } else {
                page.close();
            }
            page.search();02
        }
    }

    page.close = function() {
        dlgParameter.hide();
    }
</script>