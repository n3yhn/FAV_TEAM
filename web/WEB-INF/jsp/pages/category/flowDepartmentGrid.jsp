<%-- 
    Document   : flowRoleForm
    Created on : May 9, 2012, 10:43:31 AM
    Author     : user
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<jsp:include page="../util/util_js.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<div id="token">
    <s:token id="tokenId"/>
</div> 
<script>
    page.getNo = function(index) {
        return dijit.byId("flowDepartmentGrid").currentRow + index + 1;
    }

    page.getIndex = function(index) {
        return index + 1;
    }

    page.formatflowRole = function (inData) {
        var item= dijit.byId("flowDepartmentGrid").getItem(inData-1);
        var url="";
        if (item!=null) {
            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                                    title='<sd:Property>category.edit</sd:Property>' \n\
                                    onClick='page.showEditPopup(" + item.flowDepartmentId + ");' /></div>";
        }
        return url;
    }
    page.showEditPopup = function(data){
        var url = "flowDepartment!prepareEidt.do?flowDepartmentId="+data;
        sd.connector.post(url, "editFlowDepartmentDiv", null, null);
        var dialog = dijit.byId("dialogEditFlowDepartment");
        dialog.show();
        //dojo.style('dialogEditFlowDepartment','top','15%');
    }
</script>
<sd:TitlePane key="search.searchCondition" id="flowRole">
    <form id="flowDepartmentFormSearch" name="flowDepartmentFormSearch">        
        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <td width="15%"></td>
                <td width="35%"></td>
                <td width="15%"></td>
                <td width="35%"></td>
            </tr>
            <tr>
                <td align="right">
                    <sd:Label key="fromDepartment"/>
                </td>
                <td>
                    <sd:TreePicker id="searchDeptTreeFrom" getChildrenNodeUrl="departmentAction!getChildrenLevel1ByDept.do"
                                   getTopNodeUrl="departmentAction!getDeptData.do"  key="" rootLabel="root" cssStyle="width:80%" />

                    <sd:TextBox cssStyle="display:none;" id="flowDepartmentFormSearch.fromDepartmentId" name="flowDepartmentFormSearch.fromDepartmentId" key=""/>
                </td>
<!--                <td>
                    <sd:SelectBox cssStyle="width:100%"
                                  id="flowDepartmentFormSearch.fromDepartmentId"
                                  key=""
                                  name="flowDepartmentFormSearch.fromDepartmentId"
                                  data="lstDepartment"
                                  labelField="deptName" valueField="deptId">
                    </sd:SelectBox>
                </td>-->
                <td align="right">
                    <sd:Label key="toDepartment"/>
                </td>
                <!--                <td>
                <sd:SelectBox cssStyle="width:100%"
                              id="flowDepartmentFormSearch.toDepartmentId"
                              key=""
                              name="flowDepartmentFormSearch.toDepartmentId"
                              data="lstDepartment2"
                              labelField="deptName" valueField="deptId">
                </sd:SelectBox>
            </td>-->
                <td>
                    <sd:TreePicker id="searchDeptTreeTo" getChildrenNodeUrl="departmentAction!getChildrenLevel1ByDept.do"
                                   getTopNodeUrl="departmentAction!getDeptData.do?type=LTNN"  key="" rootLabel="root" cssStyle="width:80%" />

                    <sd:TextBox cssStyle="display:none;" id="flowDepartmentFormSearch.toDepartmentId" name="flowDepartmentFormSearch.toDepartmentId" key=""/>
                </td>
            </tr>
            <tr style="text-align: center">
                <td colspan="4">
                    <sx:ButtonSearch onclick="page.searchFlowDepartment()"/>
                </td>
            </tr>

        </table>
    </form>
</sd:TitlePane>

<sd:TitlePane key="search.searchResult" id="flowRoleList">  
    <sx:ResultMessage id="InfoResultMessage"/>
    <div id="" style="width:100%;">
        <sd:DataGrid id="flowDepartmentGrid"
                     getDataUrl=""
                     rowSelector="0px"
                     style="width:auto;height:auto"
                     rowsPerPage="10"
                     serverPaging="true"
                     clientSort="false">
            <sd:ColumnDataGrid key="STT" get="page.getNo" width="30px"  styles="text-align:center;" />
            <sd:ColumnDataGrid editable="true" key=" " headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="2%" cellStyles="text-align:center;"/>
            <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatflowRole" get="page.getIndex"
                               width="5%"  headerStyles="text-align:center;"   >
            </sd:ColumnDataGrid>
            <sd:ColumnDataGrid  key="fromDepartment" field="fromDepartment"
                                width="350px"  headerStyles="text-align:center;" />
            <sd:ColumnDataGrid  key="toDepartment" field="toDepartment"
                                width="350px"  headerStyles="text-align:center;" />
        </sd:DataGrid>

        <table width="100%" cellspacing="3px" cellpadding="0" border="0">
            <tr>
                <td>
                    <div>
                        <sx:ButtonAddNew onclick="page.prepareAddFlowDepartment()"/>
                        <sx:ButtonDelete onclick="page.deleteFlowDept()"/>
                    </div>
                </td>
            </tr>
        </table>
    </sd:TitlePane>
    <sd:Dialog id="dialogEditFlowDepartment" key="dialogEditFlowDepartment" showCloseButton="true" height="auto" width="50%">
        <div id="editFlowDepartmentDiv">
        </div>
    </sd:Dialog>
    <script>
        dijit.byId("searchDeptTreeTo").onPickData = function(item){
            try{
                if(item.id){
                    sd._("flowDepartmentFormSearch.toDepartmentId").setValue(item.id);
                }else{
                    sd._("flowDepartmentFormSearch.toDepartmentId").setValue("");
                    sd._("searchDeptTreeTo").setValue("");
                }
            }catch(err){
                alert(err.message);
            }
        }
        
        dijit.byId("searchDeptTreeFrom").onPickData = function(item){
            try{
                if(item.id){
                    sd._("flowDepartmentFormSearch.fromDepartmentId").setValue(item.id);
                }else{
                    sd._("flowDepartmentFormSearch.fromDepartmentId").setValue("");
                    sd._("searchDeptTreeFrom").setValue("");
                }
            }catch(err){
                alert(err.message);
            }
        }
        
        page.prepareAddFlowDepartment = function(){
            var url = "flowDepartment!prepareAdd.do";
            sd.connector.post(url, "editFlowDepartmentDiv", null, null);
            var dialog = dijit.byId("dialogEditFlowDepartment");
            dialog.show();
            //dojo.style('dialogEditFlowDepartment','top','15%');
        }
    
        page.searchFlowDepartment = function(){        
            var grid = dijit.byId("flowDepartmentGrid");        
            var url = "flowDepartment!onSearchFlowDepartment.do";        
            grid.vtReload(url, "flowDepartmentFormSearch");
        }
    
        page.deleteFlowDept = function(){
            var grid = dijit.byId("flowDepartmentGrid");
            var content = grid.vtGetCheckedDataForPost("voFlowDepartmentFormGrid");
            sd.connector.post("flowDepartment!onDelete.do?"+token.getTokenParamString(), null, null, content, page.afterDeleteFlowDept);
        }
    
        page.afterDeleteFlowDept = function(data) {        
            var obj = dojo.fromJson(data);
            var result = obj.customInfo;
            page.searchFlowDepartment();
            resultMessage_show("InfoResultMessage", result[0], result[1], 2000);
        }
    
        page.searchFlowDepartment();
    </script>