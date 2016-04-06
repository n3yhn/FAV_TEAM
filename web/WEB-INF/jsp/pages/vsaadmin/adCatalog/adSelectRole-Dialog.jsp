<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<form id="adFormRoleOnDialog" name="rolesForm" action="">
    <table width="100%">
        <tr>
            <td>
                <sd:TextBox id="adFormRoleOnDialog.code" name="rolesForm.code" key="rolesForm.code" cssStyle="width:80%"/>
            </td>

            <td>
                <sd:TextBox id="adFormRoleOnDialog.roleName" name="rolesForm.roleName" key="rolesForm.roleName" cssStyle="width:80%"/>
            </td>

        </tr>

        <tr>
            <td>
                <sd:TextBox id="adFormRoleOnDialog.description" name="rolesForm.description" key="rolesForm.description" cssStyle="width:80%"/>
            </td>
            <td>
                <sd:SelectBox readonly="true" key="rolesForm.status" name="rolesForm.status" id="adFormRoleOnDialog.status" cssStyle="width:80%;">
            <option value="1"><sd:Property>slt.active</sd:Property></option>
        </sd:SelectBox>
        </td>
        </tr>
        <tr>
            <td>
            </td>

            <td>
                <div style="display:none;"><sd:TextBox id="rolesForm.roleId" name="rolesForm.roleId" key="rolesForm.roleId" cssStyle="width:80%"/></div>
            </td>
        </tr>
    </table>
</form>
<div style="text-align:center;">
    <sd:Button id="adFormRoleOnDialog.btnSearch" key="" onclick="page.onSearchRole()">
        <img src="${contextPath}/share/images/icons/a1.png" height="20" width="20">
        <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
    </sd:Button>
</div>
<br/>
<br/>
<div id="gridfrmRoleDiv" style="width:100%;height:300px">
    <sd:DataGrid clientSort="true" getDataUrl="/vsaadminv3/AdRoleAction!onInitWhenAddingAd.do" id="gridfrmRoleId" style="width: 100%; height: 100%;" container="gridfrmRoleDiv" rowSelector="20px">
        <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="5%" />
        <sd:ColumnDataGrid editable="true" key="title.select" field="isCheck" type="checkbox" width="5%" styles="text-align:center;" />
        <sd:ColumnDataGrid editable="false" key="rolesForm.code" field="code" width="20%"/>
        <sd:ColumnDataGrid editable="false" key="rolesForm.roleName" field="roleName" width="25%"/>
        <sd:ColumnDataGrid editable="false" key="rolesForm.description" field="description" width="30%"/>
        <sd:ColumnDataGrid editable="false" key="rolesForm.status" field="status" width="100px" formatter="page.convertStatusToStr"/>
        <sd:ColumnDataGrid editable="false" key="rolesForm.roleId" field="roleId" width="100px" styles="display:none;"/>
    </sd:DataGrid>
</div>
<div style="padding-top:20px">
    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('gridfrmRoleId');">Select all</span>
    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('gridfrmRoleId');">Unselect all</span>
</div>
<script type="text/javascript">
    try{
        dijit.byId("adFormRoleOnDialog.code").focus();
        page.onEnter("adFormRoleOnDialog", page.onSearchRole);
    }catch(err){

    }
</script>