<%-- 
    Document   : dialogOutsiteOffice
    Created on : May 29, 2012, 11:17:50 AM
    Author     : user
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<table width="100%">
    <tr>
        <td width="250px">

            <sd:TitlePane key="department.ParentDepartment" id="">
                <div id="DeptTreeDiv" style="width:250px;height: 329px; overflow:scroll;">
                    <sd:AjaxTree  id="departmentTreeId" 
                                  getChildrenUrl="departmentAction!getChildrenDataByNode.do" 
                                  getTopLevelUrl="departmentAction!getData.do" 
                                  rootLabel="usersForm.department" onClick="page.onNodeClick"/>
                </div>
            </sd:TitlePane>


        </td>
        <td width="550px" valign="top">
            <form id="departmentForm" name="departmentForm">
                <sd:TextBox id="departmentForm.deptId" key=""
                            name="departmentForm.deptId" 
                            cssStyle="display:none;"/>
                <sd:TextBox id="departmentForm.deptParentName" key=""
                            name="departmentForm.deptParentName" 
                            cssStyle="display:none;"/>
            </form>
            <sd:TitlePane key="departmentForm.title" id="deptPane">
                <div id="DeptGridDiv" style="width: 100%;overflow: auto;text-align: center;">
                    <sd:DataGrid clientSort="true" getDataUrl="" 
                                 id="deptGridId" style="width: 500px; height: 100%;margin-right:10px;" 
                                 container="DeptGridDiv" rowSelector="0px" rowsPerPage="10">
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial"  
                                           cellStyles="text-align:center;"  
                                           key="column.radio"   
                                           type="radio" width="10%"/>
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" 
                                           editable="false" key="departmentForm.deptName" 
                                           field="deptName" width="90%"/>
                    </sd:DataGrid>
                </div>
                 <div style="width: 100%;text-align:center;margin-top: 5px;">
                <sx:ButtonChose onclick="page.choseDepartment();"/>
                <sd:Button  key="" onclick="page.closeDlgDepartment();" >
                    <img src="share/images/icons/13.png" height="14" width="14" alt="Đóng">
                    <span style="font-size:12px">Đóng</span>
                </sd:Button>

            </div>
            </sd:TitlePane>
        </td>
    </tr>
</table>
<script>
    page.onNodeClick = function(item, node, event){
        console.log("item:"+item);
        if(item.id == undefined){
                        sd._("departmentForm.deptId").setValue(null);
        }else{
                        sd._("departmentForm.deptId").setValue(item.id);
                        sd._("departmentForm.deptParentName").setValue(item.name);
        }
        page.onSearch();
    }
    
    page.onSearch = function (){
        dijit.byId('deptGridId').vtReload("departmentAction!onSearchChildren.do","departmentForm") ;
    }
</script>    