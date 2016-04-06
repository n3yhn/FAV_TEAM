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
        <tr>
            <td width="10%"></td>
            <td width="35%"></td>
            <td width="10%"></td>
            <td width="40%"></td>
        </tr>
        <tr>
            <td align="center">
                <sx:Label key="Tên" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.feeName"
                            name="createForm.feeName"
                            key=""
                            maxlength="250"
                            />
            </td>
            <td id="name1" align="center">
                <sd:Label key="Loại hồ sơ"/>
            </td>
            <td id="name2" width="25%">
                <tags:MutipleSelect_Std disabled="false" id="createForm.procedureId" name="createForm.procedureId" data="${lstCategory}"  allowCode="false"  /> 
            </td>
        </tr>
        <tr>
            <td align="center">
                <sx:Label key="Số tiền" require="true"/>
            </td>
            <td>                
                <sd:TextBox id="createForm.price" name="createForm.price" key="" maxlength="18" cssStyle="width:100%" />

            </td>

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
        <tr>
            <td align="center">
                <sx:Label key="Mô tả" require="false"/>
            </td>
            <td>                
                <sd:Textarea id="createForm.description" name="createForm.description" key="" maxlength="500" cssStyle="width:100%"/>
                <sd:TextBox id="createForm.feeId" name="createForm.feeId" cssStyle="display:none" key=""/>
            </td>
            <td align="center">
                <sx:Label key="Loại phí" require="true"/>
            </td>
            <td>
                <sd:SelectBox id="createForm.feeType" name="createForm.feeType" key="" cssStyle="width:100%" onchange="page.checkSelected()">
                    <sd:Option value='-1'>-- Chọn --</sd:Option>
                    <sd:Option value='1'>Lệ phí</sd:Option>
                    <sd:Option value='2'>Nộp phí</sd:Option>
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
        var name = dijit.byId("createForm.feeName").getValue();
        if (name == null || name.trim().length == 0) {
            alert("Bạn phải nhập tên loại phí");
            dijit.byId("createForm.feeName").focus();
            return false;
        }

        //var createDate = dijit.byId("createForm.createDate").getValue();
//
//        if (createDate == null) {
//            alert("Bạn chưa nhập ngày tạo");
//            dijit.byId("createForm.createDate").focus();
//            return false;
//        }
        var price = dijit.byId("createForm.price").getValue();

        if (price == null || price.trim().length == 0) {
            alert("Bạn chưa nhập số tiền");
            dijit.byId("createForm.price").focus();
            return false;
        }

        if (!sd.validator.isIntegerNumber(price)) {
            alert("Bạn nhập số tiền không hợp lệ");
            dijit.byId("createForm.price").focus();
            return false;
        }
        var feeType = dijit.byId("createForm.feeType").getValue();
        if (feeType == -1) {
            alert("Bạn chưa chọn loại phí");
            dijit.byId("createForm.feeType").focus();
            return false;
        }
        if (feeType == 2)
        {
            dijit.byId("createForm.procedureId").setValue("");
        }
        else
        {
            var procedureid = dijit.byId("createForm.procedureId").getValue();
            if (procedureid == null || procedureid.toString() == "") {
                alert("Bạn chưa chọn loại hồ sơ");
                dijit.byId("createForm.procedureId").focus();
                return false;
            }
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
            sd.connector.post("feeAction!onInsertFee.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSave);
        }
    };
    page.afterSave = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        if (insertDialog) {
            page.close();

        } else {
            page.close();
            resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
        }
        page.search();
    };
    page.close = function() {
        dlgFee.hide();
        page.clearInsertForm();
    };

    page.checkSelected = function() {

        var e = dijit.byId("createForm.feeType").getValue();
        var div = document.getElementById('name1');
        var div2 = document.getElementById('name2');

        if (e == 2) {
            div.style.display = 'none';
            div2.style.display = 'none';
        }
        else
        {
            div.style.display = '';
            div2.style.display = '';
        }

    };

</script>