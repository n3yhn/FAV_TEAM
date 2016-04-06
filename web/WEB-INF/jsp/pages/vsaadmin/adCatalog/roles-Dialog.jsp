<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<form id="rolesFormOnDialog" name="rolesForm">
    <table width="100%">
        <tr>
            <td>
                <sd:TextBox id="rolesFormOnDialog.code" name="rolesForm.code" key="rolesForm.code" cssStyle="width:80%"/>
            </td>

            <td>
                <sd:TextBox id="rolesFormOnDialog.roleName" name="rolesForm.roleName" key="rolesForm.roleName" cssStyle="width:80%"/>
            </td>
        </tr>
        <tr>
            <td>
                <sd:TextBox id="rolesFormOnDialog.description" name="rolesForm.description" key="rolesForm.description" cssStyle="width:80%"/>
            </td>
            <td>
                <sd:SelectBox id="rolesFormOnDialog.status" name="rolesForm.status" key="rolesForm.status" readonly="true" cssStyle="width:80%;">
                    <sd:Option value="1"><sd:Property>slt.active</sd:Property></sd:Option>
                </sd:SelectBox>
            </td>
        </tr>
        <tr>
            <td>
                <%--<sd:TextBox id="rolesFormOnDialog.objType" name="rolesForm.objType" key="rolesForm.objType" cssStyle="width:80%"/>--%>
            </td>

            <td>
                <div style="display:none;"><sd:TextBox id="rolesFormOnDialog.roleId" name="rolesForm.roleId" key="rolesForm.roleId" cssStyle="width:80%"/></div>
            </td>
        </tr>
    </table>

    <table width="100%">
        <tr>
            <td style="text-align:center;">
                <sd:Button id="rolesFormOnDialog.btnUpdate" key="" onclick="page.onSearchRoleOnDialog()">
                    <img src="${contextPath}/share/images/icons/a1.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
                </sd:Button>
                <sd:Button id="rolesFormOnDialog.btnInsert" key="" onclick="page.onInsertRole()">
                    <img src="${contextPath}/share/images/icons/save.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnSave</sd:Property></span>
                </sd:Button>
            </td>
        </tr>
    </table>

    <div id="gridRoleDivOnDialog" style="width:100%;height:400px">
        <sd:DataGrid clientSort="true" getDataUrl="/vsaadminv3/AdRoleAction!onInitDialog.do" id="gridRoleIdOnDialog" style="width: 100%; height: 100%;" container="gridRoleDivOnDialog" rowSelector="20px" rowsPerPage="20">
            <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="50px" />
            <sd:ColumnDataGrid editable="true" key="btnSelect" field="isCheck" type="checkbox" width="40px" styles="text-align:center;" />
            <sd:ColumnDataGrid editable="false" key="rolesForm.code" field="code" width="150px"/>
            <sd:ColumnDataGrid editable="false" key="rolesForm.roleName" field="roleName" width="200px"/>
            <sd:ColumnDataGrid editable="false" key="rolesForm.description" field="description" width="200px"/>
            <sd:ColumnDataGrid editable="false" key="rolesForm.status" field="status" width="80px" formatter="page.convertStatusToStr"/>
            <sd:ColumnDataGrid editable="false" key="global.view" field="" width="80px" get="page.getRow" formatter="page.urlInfoIdx" styles="text-align:center;"/>
            <sd:ColumnDataGrid editable="false" key="rolesForm.roleId" field="roleId" width="100px" styles="display:none;"/>
        </sd:DataGrid>
    </div>
    <div style="padding-top:20px">
        <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('gridRoleIdOnDialog');">Select all</span>
        <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('gridRoleIdOnDialog');">Unselect all</span>
    </div>
</form>
<%--<script type="text/javascript">
        try{
            document.getElementById("rd").innerHTML = "${par}";
        }catch(err){
        }
 </script>--%>



