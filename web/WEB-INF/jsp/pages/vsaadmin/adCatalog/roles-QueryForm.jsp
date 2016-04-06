<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<div id="rl" style="display:inline-block;">
    <div style="cursor:pointer;padding:3px 0px 0px 0px;float:left;border:1px solid;border-bottom:0px;border-color:#A9D0F5;-moz-border-radius-topleft:6px;
         -moz-border-radius-topright:6px;-webkit-border-top-right-radius:6px;-webkit-border-top-left-radius:6px;
         padding-left:2px;padding-right:2px;padding-bottom:2px;">
        <span style="padding-left:5px;padding-right:5px;
              margin:0px 0px 0px 0px;color:#15428B;
              font-family:Tahoma,helvetica,arial,'Times New Roman';
              font-style:normal;
              font-weight:lighter;">
            Danh sách vai trò quản lý <s:if test="%{#request.count>1}">chung</s:if> bởi admin lĩnh vực <span style="color:red;">${par}</span></span>
    </div>
</div>
<form id="rolesForm" name="rolesForm">
    <table width="100%">
        <tr>
            <td>
            <sd:TextBox id="rolesForm.code" name="rolesForm.code" key="rolesForm.code" cssStyle="width:80%" trim="true"/>
            </td>

                <td>
                    <sd:TextBox id="rolesForm.roleName" name="rolesForm.roleName" key="rolesForm.roleName" cssStyle="width:80%" trim="true"/>
                </td>

            </tr>

            <tr>
                <td>
                    <sd:TextBox id="rolesForm.description" name="rolesForm.description" key="rolesForm.description" cssStyle="width:80%"/>
                </td>
                <td>
                    <sd:SelectBox key="rolesForm.status" name="rolesForm.status" id="rolesForm.status" cssStyle="width:80%;">
                        <option value=""><s:property value="getText('slt.all')" /></option>
                        <option value="1"><s:property value="getText('slt.active')" /></option>
                        <option value="-1"><s:property value="getText('slt.deactive')" /></option>
                    </sd:SelectBox>
                </td>
            </tr>
             <tr>
                <td>
                    <sd:TextBox id="rolesForm.objType" name="rolesForm.objType" key="rolesForm.objType" cssStyle="width:80%"/>
                </td>

                <td>
                    <div style="display:none;"><sd:TextBox id="rolesForm.roleId" name="rolesForm.roleId" key="rolesForm.roleId" cssStyle="width:80%"/></div>
                </td>
            </tr>
        </table>

    </form>
     <div style="text-align:center;">
    <sd:Button id="rolesForm.btnSearch" key="btnSearch" onclick="page.onSearchRole()"/>
    </div>
<div id="gridRoleDiv" style="width:100%;height:300px">

    <sd:DataGrid clientSort="true" getDataUrl="/vsaadminv3/AdRoleAction!onInit.do" id="gridRoleId" style="width: 100%; height: 100%;" container="gridRoleDiv" rowSelector="20px" rowsPerPage="20">
        <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="5%" />
        <sd:ColumnDataGrid editable="true" key="title.select" field="isCheck" type="checkbox" width="5%" styles="text-align:center;" />
        <sd:ColumnDataGrid editable="false" key="rolesForm.code" field="code" width="20%"/>
        <sd:ColumnDataGrid editable="false" key="rolesForm.roleName" field="roleName" width="25%"/>
        <sd:ColumnDataGrid editable="false" key="rolesForm.description" field="description" width="30%"/>
        <%--<sd:ColumnDataGrid editable="false" key="rolesForm.status" field="status" width="100px" formatter="page.convertStatusToStr"/>--%>
        <sd:ColumnDataGrid editable="false" key="rolesForm.status" field="isActive" width="10%" formatter="page.convertStatusToStr"/>
        <sd:ColumnDataGrid editable="false" key="global.view" field="" width="5%" get="page.getRow" formatter="page.urlInfo" styles="text-align:center;"/>
        <sd:ColumnDataGrid editable="false" key="rolesForm.roleId" field="roleId" width="100px" styles="display:none;"/>
    </sd:DataGrid>
</div>
<table style="padding-top:20px">
    <tr>
        <td>
            <div>
                <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('gridRoleId');">Select all</span>
                <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('gridRoleId');">Unselect all</span>
            </div>
        </td>
        <td>
            <div style="text-align:right;margin-top:5px;margin-right:5px;">
                <sd:Button id="rolesForm.btnInsert" key="" onclick="page.preInsertRole()">
                    <img src="${contextPath}/share/images/icons/6.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnAdd</sd:Property></span>
                </sd:Button>
                <sd:Button id="rolesForm.btnDelete" key="" onclick="page.onDeleteRole()">
                    <img src="${contextPath}/share/images/icons/13.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnRemove</sd:Property></span>
                </sd:Button>
                <sd:Button id="rolesForm.btnEnable" key="" onclick="page.onEnableRole()">
                    <img src="${contextPath}/share/images/icons/unlock.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnEnable</sd:Property></span>
                </sd:Button>
                <sd:Button id="rolesForm.btnDisable" key="" onclick="page.onDisableRole()">
                    <img src="${contextPath}/share/images/icons/lock.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnDisable</sd:Property></span>
                </sd:Button>
            </div>
        </td>
    </tr>
</table>
<%--</sd:TitlePane>
    <script type="text/javascript">
        try{
            alert("${par}");
            document.getElementById("sub").innerHTML = "${par}";
        }catch(err){
        }
    </script>--%>

