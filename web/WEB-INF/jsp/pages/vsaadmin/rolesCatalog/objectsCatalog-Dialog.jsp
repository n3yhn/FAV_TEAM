<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<link rel="stylesheet" href="${contextPath}/share/css/formStyle.css" charset="UTF-8" type="text/css" />


<script>
    page.onTreeClick = function(item, node){
        if (item.id == undefined){
            sd.$("objectsForm.parentId").value = "";
        }
        else{
            sd.$("objectsForm.parentId").value = item.id;
        }
        page.onSearchFunctionToAdd();
    }
</script>

<table width="100%">    
    <tr>
        <td style="width: 30%;">
            <sd:TitlePane id="deptTreePane" key="department.treeTitle">
                <div class="treeDiv1" style="height:360px; width:100%; overflow:auto; background-color:#F7F7F7;">
                    <sd:AjaxTree id="treeId"
                                 rootLabel="applicationsForm.moduleTree"
                                 getTopLevelUrl="RolesAction!getData.do"
                                 getChildrenUrl="RolesAction!getChildrenDataByNode.do"
                                 onClick="page.onTreeClick" />
                </div>
            </sd:TitlePane>
        <td width="70%">
            <sd:TitlePane key="applicationsForm.moduleListB" id="objectsForm.title">
                <form id="objectsForm" name="objectsForm">
                    <s:hidden name="objectsForm.roleId" id="objectsForm.roleId"/>
                    <s:hidden name="objectsForm.parentIdStr" id="objectsForm.parentId"/>
                    <div id="gridFunctionDialogDiv" style="width:100%;">
                        <sd:DataGrid clientSort="true" getDataUrl="RolesAction!onInitFunction.do"
                                     id="gridFunctionDialogId" style="width:100%; height:100%;"
                                     container="gridFunctionDialogDiv"
                                     rowsPerPage="10">
                            <sd:ColumnDataGrid key="customer.No" get="page.getNo" width="5%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true"  key="global.checkboxCol" field="isCheck" width="5%" type="checkbox" styles="text-align:center;" />
                            <sd:ColumnDataGrid editable="false" key="rolesForm.objects.name" field="objectName" width="20%"/>
                            <sd:ColumnDataGrid editable="false" key="rolesForm.objects.type" field="objectType" formatter="page.convertObjectsType" width="5%"/>
                            <sd:ColumnDataGrid editable="false" key="rolesForm.objects.action" field="objectUrl" width="20%"/>
                            <sd:ColumnDataGrid editable="false" key="rolesFrom.objects.description" field="description" width="15%"/>
                        </sd:DataGrid>
                    </div>
                </form>
                <div align="left">
                    <sd:Button id="objectsForm.btnInsert" key="" onclick="page.onInsertFunction()" >
                        <img src="share/images/icons/a7.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>rolesForm.objects.add</sd:Property></span>
                    </sd:Button>
                </div>                    
            </sd:TitlePane>
        </td>
    </tr>
</table>