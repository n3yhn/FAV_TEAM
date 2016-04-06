<%-- 
    Document   : flowRoleAddEdit
    Created on : May 9, 2012, 11:35:48 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../util/util_js.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="token">
    <s:token id="tokenId"/>
</div> 

<sd:TitlePane key="dialog.titleAddEdit" id="flowRoleAddEdit">
    <sx:ResultMessage id="FlowDeptResultMessage"/>
    <form id="flowDepartmentForm" name="flowDepartmentForm">
        <sd:TextBox cssStyle="display:none"
                    id="flowDepartmentForm.flowDepartmentId"
                    key=""
                    name="flowDepartmentForm.flowDepartmentId" value="${fn:escapeXml(flowDepartmentId)}">
        </sd:TextBox>
        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <td width="30%"></td>
                <td width="70%"></td>
            </tr>
            <tr>
                <td align="right">
                    <sx:Label key="fromDepartment" require="true"/>
                </td>
                <!--                <td>
                <sd:SelectBox cssStyle="width:100%"
                              id="flowDepartmentForm.fromDepartmentId"
                              key=""
                              name="flowDepartmentForm.fromDepartmentIdStr"
                              data="lstDepartment"
                              labelField="deptName" valueField="deptId" onchange="page.changeFromDep()">
                </sd:SelectBox>
                <sd:TextBox trim="true" cssStyle="width:100%;display:none;"
                            id="flowDepartmentForm.fromDepartment" name="flowDepartmentForm.fromDepartment" key=""/>
            </td>-->
                <td>
                    <sd:TreePicker id="deptTreeFrom" getChildrenNodeUrl="departmentAction!getChildrenLevel1ByDept.do"
                                   getTopNodeUrl="departmentAction!getDeptData.do"  key="" rootLabel="root" cssStyle="width:80%" />

                    <sd:TextBox cssStyle="display:none;" id="flowDepartmentForm.fromDepartmentId" name="flowDepartmentForm.fromDepartmentId" key=""/>
                    <sd:TextBox trim="true" cssStyle="width:100%;display:none;"
                            id="flowDepartmentForm.fromDepartment" name="flowDepartmentForm.fromDepartment" key=""/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <sx:Label key="toDepartment" require="true"/>
                </td>
                <!--                <td>
                <sd:SelectBox cssStyle="width:100%"
                              id="flowDepartmentForm.toDepartmentId"
                              key=""
                              name="flowDepartmentForm.toDepartmentIdStr"
                              data="lstDepartment2"
                              labelField="deptName" valueField="deptId" onchange="page.changeToDep()">
                </sd:SelectBox>
                <sd:TextBox trim="true" cssStyle="width:100%;display:none;"
                            id="flowDepartmentForm.toDepartment" name="flowDepartmentForm.toDepartment" key=""/>
            </td>-->
                <td>
                    <sd:TreePicker id="deptTreeTo" getChildrenNodeUrl="departmentAction!getChildrenLevel1ByDept.do"
                                   getTopNodeUrl="departmentAction!getDeptData.do?type=LTNN"  key="" rootLabel="root" cssStyle="width:80%" />

                    <sd:TextBox cssStyle="display:none;" id="flowDepartmentForm.toDepartmentId" name="flowDepartmentForm.toDepartmentId" key=""/>
                    <sd:TextBox trim="true" cssStyle="width:100%;display:none;"
                            id="flowDepartmentForm.toDepartment" name="flowDepartmentForm.toDepartment" key=""/>
                </td>
            </tr>
            <tr style="text-align: center">
                <td colspan="2">
                    <sx:ButtonAddNew onclick="page.resetFlowDept()"/>
                    <sx:ButtonSave onclick="page.preInsertFlowDept()"/>
                    <sx:ButtonClose onclick="page.closeFlowForm()" />
                </td>
            </tr>

        </table>
    </form>
</sd:TitlePane>

<script>
    //Load DL khi edit
    var toDepartmentId = '${flowDepartmentForm.toDepartmentId}';
    if(toDepartmentId != ""){
        sd._("flowDepartmentForm.toDepartmentId").setValue(toDepartmentId);
        sd._("deptTreeTo").setValue('${flowDepartmentForm.toDepartment}');
    }
    var fromDepartmentId = '${flowDepartmentForm.fromDepartmentId}';
    if(fromDepartmentId != ""){
        sd._("flowDepartmentForm.fromDepartmentId").setValue('${flowDepartmentForm.fromDepartmentId}');
        sd._("deptTreeFrom").setValue('${flowDepartmentForm.fromDepartment}');
    };
    
    dijit.byId("deptTreeTo").onPickData = function(item){
        try{
            if(item.id){
                sd._("flowDepartmentForm.toDepartmentId").setValue(item.id);
                sd._("flowDepartmentForm.toDepartment").setValue(item.name);
            }else{
                sd._("flowDepartmentForm.toDepartmentId").setValue("");
                sd._("flowDepartmentForm.toDepartment").setValue("");
                sd._("deptTreeTo").setValue("");
            }
        }catch(err){
            alert(err.message);
        }
    };
        
    dijit.byId("deptTreeFrom").onPickData = function(item){
        try{
            if(item.id){
                sd._("flowDepartmentForm.fromDepartmentId").setValue(item.id);
                sd._("flowDepartmentForm.fromDepartment").setValue(item.name);
            }else{
                sd._("flowDepartmentForm.fromDepartmentId").setValue("");
                sd._("flowDepartmentForm.fromDepartment").setValue("");
                sd._("deptTreeFrom").setValue("");
            }
        }catch(err){
            alert(err.message);
        }
    };
    page.closeFlowForm = function() {
        var dialog = dijit.byId("dialogEditFlowDepartment");
        dialog.hide();
    };
//    page.changeFromDep = function(){
//        var name = document.getElementById("flowDepartmentForm.fromDepartmentId").value;
//        dojo.attr(dojo.byId("flowDepartmentForm.fromDepartment"),"value", name);
//    }
//    page.changeToDep = function(){
//        var name = document.getElementById("flowDepartmentForm.toDepartmentId").value;
//        dojo.attr(dojo.byId("flowDepartmentForm.toDepartment"),"value", name);
//    }
    
    page.validateFlowDeptForm = function(){        
        if(dijit.byId("flowDepartmentForm.fromDepartmentId").attr("value")=="-1"){
            dijit.byId("flowDepartmentForm.fromDepartmentId").focus();
            msg.alert("Bạn chưa chọn từ phòng ban","<sd:Property>confirm.title</sd:Property>");
            return false;
        }
        if(dijit.byId("flowDepartmentForm.toDepartmentId").attr("value")=="-1"){
            dijit.byId("flowDepartmentForm.toDepartmentId").focus();
            msg.alert("Bạn chưa chọn tới phòng ban","<sd:Property>confirm.title</sd:Property>");
            return false;
        }
        return true;
    };
    
    page.preInsertFlowDept = function(){
        if(page.validateFlowDeptForm()){
            var urlCheck ="";
            var frmToSubmit = "flowDepartmentForm";
            var flowDepartmentId = dojo.attr(dojo.byId("flowDepartmentForm.flowDepartmentId"),"value");
            if(flowDepartmentId == "0"){
                urlCheck = "flowDepartment!onInsertRecord.do?" + token.getTokenParamString();
            }else{
                urlCheck = "flowDepartment!onUpdateRecord.do?flowDepartmentId=" + flowDepartmentId +"&" + token.getTokenParamString();
            }
            sd.connector.post(urlCheck,null,frmToSubmit,null,page.callBackFlowDept);
        }
    };
    
    page.callBackFlowDept = function(data){
        data = dojo.fromJson(data);
        if(data.customInfo != "error"){   
            var result = data.items;
            resultMessage_show("FlowDeptResultMessage", result[0], result[1], 2000);    
            dojo.attr(dojo.byId("flowDepartmentForm.flowDepartmentId"),"value", data.customInfo);  
            page.searchFlowDepartment();
        }else{
            var result = data.items;
            resultMessage_show("FlowDeptResultMessage", result[0], result[1], 2000);
            return;
        }
    };
    
    page.resetFlowDept = function(){
        dojo.attr(dojo.byId("flowDepartmentForm.flowDepartmentId"),"value", "0");
//        dijit.byId("flowDepartmentForm.fromDepartmentId").setSelectedIndex(0);
        document.getElementById("flowDepartmentForm.fromDepartmentId").value="";
        document.getElementById("flowDepartmentForm.fromDepartment").value="";
        sd._("deptTreeFrom").setValue("");
//        dijit.byId("flowDepartmentForm.toDepartmentId").setSelectedIndex(0);
        document.getElementById("flowDepartmentForm.toDepartmentId").value="";
        document.getElementById("flowDepartmentForm.toDepartment").value="";
        sd._("deptTreeTo").setValue("");
    };
</script>