<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sx:ResultMessage id="categoryResultMessage" />
<form id="createForm">
    <table width="100%;" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="30%"></td>
            <td width="70%"></td>
        </tr>
        <tr>
            <td colspan="2" style="display: none">
                <sd:TextBox cssStyle="width:100%;" trim="true"
                            id="createForm.flowId"
                            key=""
                            name="createForm.flowId"
                            >
                </sd:TextBox>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Tên luồng" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.flowName"
                            key=""
                            name="createForm.flowName"
                            maxlength="100"/>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="TTHC" require="true"/>
            </td>
            <td>
                <sd:SelectBox cssStyle="width:100%" onchange="page.onChangeProcedure();"
                              id="createForm.flowType"
                              key=""
                              name="createForm.flowType" data="lstCategory" labelField="name" valueField="procedureId">
                    <sd:Option value="-1">-- Chọn --</sd:Option>
                </sd:SelectBox>
                <sd:TextBox cssStyle="display:none;" id="createForm.flowTypeName" name="createForm.flowTypeName" key=""/>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Đơn vị" require="true"/>
            </td>
            <td>
                <sd:TreePicker name="deptFlowTree" id="deptFlowTree" getChildrenNodeUrl="departmentAction!getDeptChildrenDataByNode.do"
                               getTopNodeUrl="departmentAction!getMyDeptRootTree.do"  key="" rootLabel="root" cssStyle="width:100%"/>
                <sd:TextBox cssStyle="display:none;" id="createForm.deptId" name="createForm.deptId" key=""/>
                <sd:TextBox cssStyle="display:none;" id="createForm.deptName" name="createForm.deptName" key=""/>
            </td>
        </tr>
        <tr>
            <td align="right">
                <sx:Label key="Ghi chú"/>
            </td>
            <td>
                <sd:Textarea cssStyle="width:100%" trim="true"
                             id="createForm.description"
                             key=""
                             name="createForm.description"
                             rows="4"
                             maxlength="255"/>
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

<script type="text/javascript">
    var first = true;
    page.validateSaveFlow = function() {
        if (!dijit.byId("createForm.flowName").getValue()) {
            //isValidate = false;
            alert("Chưa nhập tên luồng", "Thông báo", "warning");
            dijit.byId("createForm.flowName").focus();
            return false;
        }

        if (dijit.byId("createForm.flowType").getValue() <= 0) {
            //isValidate = false;
            alert("Chưa chọn thủ tục hành chính", "Thông báo", "warning");
            dijit.byId("createForm.flowType").focus();
            return false;
        } else {
            dijit.byId("createForm.flowTypeName").setValue(dijit.byId('createForm.flowType').attr('displayedValue'));
        }
        if (!dijit.byId("createForm.deptId").getValue()) {
            //isValidate = false;
            alert("Chưa chọn đơn vị", "Thông báo", "warning");
            return false;
        }
        return true;
    };

    page.save = function() {
        if (page.validateSaveFlow()) {
            sd.connector.post("flow!insertFlow.do?" + token.getTokenParamString(), null, "createForm", null, page.afterInsertFlow);
        }
        //sd.connector.post("category!insertCategory.do", null, "categoryAddEditForm", null, page.afterInsertCategory);

    };


    page.afterInsertFlow = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        //var customInfo = obj.customInfo;                

        if (insertDialog) {
            resultMessage_show("categoryResultMessage", result[0], result[1], 5000);
            dijit.byId("createForm.flowId").setValue("");
            dijit.byId("createForm.flowName").setValue("");
            //dijit.byId("createForm.deptId").setValue("");
            //dijit.byId("createForm.deptName").setValue("");
            dijit.byId("createForm.description").setValue("");
            dijit.byId("createForm.flowType").setValue(-1);
        } else {
            page.close();
            resultMessage_show("resultMessage", result[0], result[1], 5000);
        }
        page.search();
    };

    page.close = function() {
        dlgCategory.hide();
    };


    dijit.byId("deptFlowTree").onPickData = function(item) {
        try {
            if (item.id) {
                sd._("createForm.deptId").setValue(item.id);
                sd._("createForm.deptName").setValue(item.name);
            } else {
                sd._("createForm.deptId").setValue("");
                sd._("createForm.deptName").setValue("");
                sd._("deptFlowTree").setValue("");
            }
        } catch (err) {
            alert(err.message);
        }
    };
    try {
        sd.widget.__setReadOnly("deptFlowTree", true);
    } catch (e) {

    }
    ;
    //binhnt53
    page.onChangeProcedure = function() {
        if (!first) {
            var id = dijit.byId("createForm.flowType").attr("value");
            dijit.byId("createForm.flowType1").vtReload("flow!prepareDepartment.do?procedureId=" + id);
        } else {
            first = false;
        }
    };
</script>