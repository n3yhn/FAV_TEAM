<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<sd:TitlePane key="rolesForm.functionCatalog" id="tltpnFunction">
    <form id ="form2" name="rolesForm">
        <table width="100%" cellpadding="2px" cellspacing="3px">
            <tr>
                <td>
                    <sd:TextBox id="rolesForm.objects.code" name="rolesForm.functionCodeSearch"
                                key="rolesForm.objects.code" cssStyle="width:80%" trim="true"/>
                </td>
                <td>
                    <s:hidden name="rolesForm.parentIdStr" id="rolesForm.parentId"/>
                    <s:hidden name="rolesForm.roleIdArr" id="rolesForm.roleIdArr"/>
                    <sd:TextBox id="rolesForm.objects.name" name="rolesForm.functionNameSearch"
                                key="rolesForm.objects.name" cssStyle="width:80%" trim="true"/>
                </td>

            </tr>
            <tr>
                <td>
                    <sd:SelectBox id="rolesForm.objects.status" name="rolesForm.functionStatusSearch"
                                  key="rolesFrom.objects.status" cssStyle="width:80%">
                        <sd:Option value="ALL">-- Chọn --</sd:Option>
                        <sd:Option value="1">Hoạt động</sd:Option>
                        <sd:Option value="0">Bị khóa</sd:Option>
                    </sd:SelectBox>
                </td>
                <td>
                    <sd:TextBox id="rolesForm.objects.appInfo" name="rolesForm.appInfo"
                                key="Applications" cssStyle="width:80%" trim="true"/>
                </td>
            </tr>
            <tr style="text-align: center">
                <td colspan="2">
                    <sd:Button id="rolesForm.btnSearchFunction" key="" onclick="page.onSearchFunction()" >
                        <img src="share/images/icons/3.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
                    </sd:Button>
                </td>
            </tr>

            <tr style="text-align: center">
                <td colspan="2">
                    <sd:DataGrid clientSort="true" getDataUrl="RolesAction!onInitFunction.do"
                                 id="gridFunctionId" style="width:100%; height:100%;"
                                 rowSelector="0%" rowsPerPage="10">
                        <sd:ColumnDataGrid key="customer.No" get="page.getNo" width="3%"  styles="text-align:center;" />
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true"
                                           type="checkbox" width="1cm" styles="text-align:center;"/>
                        <sd:ColumnDataGrid editable="false" key="objectsForm.objectCode" field="objectCode" width="20%"/>
                        <sd:ColumnDataGrid editable="false" key="rolesForm.objects.name" field="objectName" width="20%"/>
                        <sd:ColumnDataGrid editable="false" key="rolesForm.objects.type" field="objectType" formatter="page.convertObjectsType" width="5%"/>
                        <sd:ColumnDataGrid editable="false" key="applicationsForm.appCode" field="appCode" width="10%"/>
                        <sd:ColumnDataGrid editable="false" key="applicationsForm.appName" field="appName" width="15%"/>
                        <sd:ColumnDataGrid editable="false" key="rolesFrom.objects.status" formatter="page.convertStatus" field="status" width="10%"/>
                        <sd:ColumnDataGrid editable="false" key="rolesForm.objects.action" field="objectUrl" width="20%"/>
                        <sd:ColumnDataGrid editable="false" key="rolesFrom.objects.description" field="description" width="15%"/>
                    </sd:DataGrid>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:Button id="rolesForm.btnInsertFunction" key="" onclick="page.onPreInsertFunction()" >
                        <img src="share/images/icons/a7.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                    </sd:Button>
                    <sd:Button id="rolesForm.btnLockFunction" key="" onclick="page.onLockFunction()" >
                        <img src="share/images/icons/lock.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnDisable</sd:Property></span>
                    </sd:Button>
                    <sd:Button id="rolesForm.btnUnLockFunction" key="" onclick="page.onUnLockFunction()" >
                        <img src="share/images/icons/unlock.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnEnable</sd:Property></span>
                    </sd:Button>
                    <sd:Button id="rolesForm.btnDeleteFunction" key="" onclick="page.onDeleteFunction()" >
                        <img src="share/images/icons/a4.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnDelete</sd:Property></span>
                    </sd:Button>
                </td>
            </tr>
        </table>
    </form>
</sd:TitlePane>