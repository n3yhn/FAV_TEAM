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
            <td width="15%"></td>
            <td width="35%"></td>
            <td width="15%"></td>
            <td width="35%"></td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Tên" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.name"
                            name="createForm.name"
                            key=""
                            maxlength="250"
                            />
            </td>
            <td align="right">
                <sx:Label key="Loại" require="true"/>
            </td>
            <td>
                <sd:SelectBox id="createForm.isDayOff" name="createForm.isDayOff" key="" cssStyle="width:100%">
                    <sd:Option value='-1'>-- Chọn --</sd:Option>
                    <sd:Option value='0' selected="true">Ngày nghỉ</sd:Option>
                    <sd:Option value='1'>Ngày làm</sd:Option>
                </sd:SelectBox>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Từ ngày" require="true"/>
            </td>
            <td>
                <sx:DatePicker cssStyle="width:100%" onchange="page.timeProcessDateFrom()"
                               id="createForm.timeProcessDateFrom"
                               key=""
                               name="createForm.timeProcessDateFrom" format="dd/MM/yyyy"/>
            </td>
            <td align="right">
                <sx:Label key="Đến ngày" require="true"/>
            </td>
            <td>
                <sx:DatePicker cssStyle="width:100%"
                               id="createForm.timeProcessDateTo"
                               key=""
                               name="createForm.timeProcessDateTo" format="dd/MM/yyyy"/>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Mô tả" require="false"/>
            </td>
            <td>                
                <sd:Textarea id="createForm.description" name="createForm.description" key="" maxlength="1000" cssStyle="width:100%"/>
                <sd:TextBox id="createForm.timeProcessId" name="createForm.timeProcessId" cssStyle="display:none" key=""/>
            </td>
            <td align="right">
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
        var name = dijit.byId("createForm.name").getValue();
        if (name == null || name.trim().length == 0) {
            alert("Bạn phải nhập tên ngày lễ");
            dijit.byId("createForm.name").focus();
            return false;
        }

        var timeProcessDateFrom = dijit.byId("createForm.timeProcessDateFrom").getValue();
        var timeProcessDateTo = dijit.byId("createForm.timeProcessDateTo").getValue();
        if (timeProcessDateFrom == null) {
            alert("Bạn chưa nhập ngày nghỉ lễ [Từ ngày]");
            dijit.byId("createForm.timeProcessDateFrom").focus();
            return false;
        }
        if (timeProcessDateTo == null) {
            alert("Bạn chưa nhập ngày nghỉ lễ [đến ngày]");
            dijit.byId("createForm.timeProcessDateTo").focus();
            return false;
        }
        var today = new Date();
        if (timeProcessDateFrom < today && timeProcessDateTo < today) {
            alert("Ngày phải lớn hơn ngày hiện tại");
            dijit.byId("createForm.timeProcessDate").focus();
            return false;
        }
        if (timeProcessDateFrom > timeProcessDateTo) {
            alert("[Đến ngày] phải lớn hơn hoặc bằng [Từ ngày]");
            dijit.byId("createForm.timeProcessDateTo").focus();
            return false;
        }
        var isDayOff = dijit.byId("createForm.isDayOff").getValue();
        if (isDayOff == -1) {
            alert("Chọn loại ngày");
            dijit.byId("createForm.isDayOff").focus();
            return false;
        }
        var dayFrom = timeProcessDateFrom.getDay();
        var dayTo = timeProcessDateTo.getDay();
        if (isDayOff == 0 && (dayFrom == 6 || dayFrom == 0) && (dayTo == 6 || dayTo == 0)) {
            alert("Bạn đang chọn Thứ 7 hoặc chủ nhật là ngày nghỉ");
            dijit.byId("createForm.timeProcessDate").focus();
            return false;
        }
        if (isDayOff == 1 && dayFrom != 6 && dayFrom != 0) {
            alert("Bạn chọn một ngày làm việc với loại ngày làm");
            dijit.byId("createForm.timeProcessDate").focus();
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
            sd.connector.post("timeProcessAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSave);
        }
    };
    page.afterSave = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        if (insertDialog) {
            page.clearInsertForm();
        } else {
            page.close();
            resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
        }
        page.search();
    };
    page.close = function() {
        dlgTimeProcess.hide();
        page.clearInsertForm();
    };
    page.timeProcessDateFrom = function() {
        var timeProcessDateFrom = dijit.byId("createForm.timeProcessDateFrom").getValue();
        dijit.byId("createForm.timeProcessDateTo").setValue(timeProcessDateFrom);
    };
</script>