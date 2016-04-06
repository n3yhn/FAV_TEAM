<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<script>
    page.addOrSave = function(evt) {
        var dk = dojo.keys;
        var id = dijit.byId("parameterCreateForm.parameterId").getValue();
        switch (evt.keyCode) {
            case dk.ENTER:
                if (id)
                {
                    page.onUpdate();
                }
                else
                {
                    page.onInsert();
                }
                break;
        }
    };
    dojo.connect(dojo.byId("parameterCreateForm"), "onkeypress", page.addOrSave);
</script>
<form id="parameterCreateForm" name="parameterForm">
    <table width="100%">
        <tr>
            <td>
                <sd:TextBox id="parameterCreateForm.name" name="parameterForm.name" key="position.create.Name" cssStyle="width:80%" required="true" maxlength="100"/>
            </td>


        </tr>
        <tr>
            <td>
                <sd:TextBox id="parameterCreateForm.code" name="parameterForm.code" key="position.create.Code" cssStyle="width:80%" required="true" maxlength="50"/>
            </td>
        </tr>
        <tr>

            <td>
                <sd:TextBox id="parameterCreateForm.description" name="parameterForm.description" key="categoryForm.Description" cssStyle="width:80%" maxlength="200"/>
            </td>
        </tr>
        <tr>

            <td>
                <div style="display:none;">
                    <sd:TextBox id="parameterCreateForm.rowNo" key="" name="parameterCreateForm.rowNo" value="-1" cssStyle="display:none" />
                    <sd:TextBox id="parameterCreateForm.parameterId" name="parameterForm.parameterId" key="parameterForm.parameterId" cssStyle="width:80%"/>
                </div>
            </td>
        </tr>
    </table>
    <table width="100%">
        <tr>
            <td style="text-align:center;">
                <%--sd:Button id="parameterCreateForm.btnUpdate" key="" onclick="page.onUpdate()">
                    <img src="${contextPath}/share/images/icons/a3.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnUpdate</sd:Property></span>
                </sd:Button>
                <sd:Button id="parameterCreateForm.btnInsert" key="" onclick="page.onInsert()">
                     <img src="${contextPath}/share/images/icons/save.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                </sd:Button--%>
                <sd:Button id="parameterCreateForm.btnUpdate" key="btnUpdate" onclick="page.onUpdate()"/>
                <sd:Button id="parameterCreateForm.btnInsert" key="btnSave" onclick="page.onInsert()"/>
                <sd:Button id="parameterCreateForm.btnReset" key="btnReset" onclick="page.onReset()"/>
                <sd:Button id="parameterCreateForm.btnClose" key="btn.close" onclick="page.onClose()"/>
            </td>
        </tr>
    </table>
</form>

<script>

    page.callback = function(data, bSuccess) {
        data = dojo.fromJson(data);
        alert("error");
        switch (data.customInfo[0]) {

            case "deleteSuccess":
                page.alert("Thông báo", "<sd:Property>msg.deleteOk</sd:Property>", "info");

                break;
            case "deletePart":
                page.alert("Thông báo", "Các bản ghi sau không xóa được do còn ràng buộc: " + data.customInfo[1], "warning");

                break;
            case "deleteFail":
                page.alert("Thông báo", "<sd:Property>msg.deleteErr</sd:Property>", "error");
                break;
            case "insertOk":
                page.alert("Thông báo", "<sd:Property>msg.insertOk</sd:Property>", "info");
                page.onSearch();
                break;
            case "insertErr":
                page.alert("Thông báo", "<sd:Property>msg.insertErr</sd:Property>", "error");
                break;
            case "updateOk":
                page.alert("Thông báo", "<sd:Property>msg.updateOk</sd:Property>", "info");
                page.afterUpdate();

                break;
            case "updateErr":
                page.alert("Thông báo", "<sd:Property>msg.UpdateErr</sd:Property>", "error");
                break;
            case "parentNOK":
                page.alert("Thông báo", "<sd:Property>msg.department.parentNOK</sd:Property>", "warning");
                break;
            default:
                break;
        }
        //alert( dijit.byId("deptTreeId"));
        //  dijit.byId('deptGridId').vtReload("departmentAction!onUpdate.do","departmentFormOnDialog", null, page.callback) ;
        //
        //  var content=dijit.byId("deptTreeId").getAllNodeForPost("treeTableForm",true);
        //dijit.byId("demoTreeTable").vtReload("tree!reload.do",null,content,null,null);

        //    dijit.byId("deptTreeId").refresh();
    };
    page.onReset = function() {

        var id = dijit.byId("parameterCreateForm.parameterId").getValue();
        if (id)
        {
            page.prepareDataForUpdate(dijit.byId("parameterCreateForm.rowNo").value);

        }
        else
        {
            page.clearFormOnDialog();
        }

    };
    page.onClose = function() {
        msg.confirm('<sd:Property>confirm.update</sd:Property>', '<sd:Property>confirm.title</sd:Property>', page.closeExecute);
    };
    page.closeExecute = function() {
        dijit.byId("categoryDialog").hide();
    };
    page.onInsert = function() {

        if (page.validateData())
            msg.confirm('<sd:Property>confirm.update</sd:Property>', "<sd:Property>confirm.title</sd:Property>", page.modifierTrain)

    };
    page.onUpdate = function() {

        if (page.validateData(false))
            msg.confirm('<sd:Property>confirm.update</sd:Property>', "<sd:Property>confirm.title</sd:Property>", page.modifierTrain)

    };
    page.modifierTrain = function()
    {

        //     dijit.byId('gridTrain').vtReload("position"+"Action!onInsert.do","parameterCreateForm", null, page.callback) ;
        sd.connector.post("position" + "Action!onInsert.do?" + token.getTokenParamString(), null, "parameterCreateForm", null, page.callback);
    };


    page.validateData = function(isOnInsert)
    {
        if (!dijit.byId("parameterCreateForm.name").getValue()) {
            //isValidate = false;
            page.alert("Thông báo", "<sd:Property>msg.name.null</sd:Property>", "warning");
            dijit.byId("parameterCreateForm.name").focus();
            return false;
        }

        if (!dijit.byId("parameterCreateForm.code").getValue()) {
            //isValidate = false;
            page.alert("Thông báo", "<sd:Property>msg.code.null</sd:Property>", "warning");
            dijit.byId("parameterCreateForm.code").focus();
            return false;
        }
        var code = dijit.byId("parameterCreateForm.code").value;
        var name = dijit.byId("parameterCreateForm.name").value;
        var rowNo = dijit.byId('parameterCreateForm.rowNo').value + 1;
        var rowCount = dijit.byId("gridTrain")._getRowCountAttr();
        if (isOnInsert)
        {
            for (var i = 0; i < rowCount; i++) {
                var itemI = dijit.byId("gridTrain").getItem(i);
                if (dojo.trim(name.toString()) == dojo.trim(itemI.name.toString())) {
                    msg.alert("Tên trùng với tên dòng " + (i + 1));
                    return false;
                }
                if (dojo.trim(code.toString()) == dojo.trim(itemI.code.toString())) {
                    msg.alert("Mã trùng với mã dòng " + (i + 1));
                    return false;
                }
            }
        }
        else
        {
            {
                for (var i = 0; i < rowCount; i++) {
                    var itemI = dijit.byId("gridTrain").getItem(i);
                    if (dojo.trim(name.toString()) == dojo.trim(itemI.name.toString()) && (i + 1) != rowNo) {
                        msg.alert("Tên trùng với tên dòng " + (i + 1));
                        return false;
                    }
                    if (dojo.trim(code.toString()) == dojo.trim(itemI.code.toString()) && (i + 1) != rowNo) {
                        msg.alert("Mã trùng với mã dòng " + (i + 1));
                        return false;
                    }
                }
            }
        }
        return true;
    };
</script>