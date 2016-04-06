<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<sd:TitlePane key="listExcelRow" id="lstExcelRowTitlePane1">
    <form id="importUserForm">
        <div id="importUserGrid" style="width:100%;height:250px">
            <sd:DataGrid clientSort="true" getDataUrl="" id="importUserGridId" style="width: 100%; height: 100%;" container="importUserGrid" rowSelector="20px" rowsPerPage="20">
                <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="20px" />
                <sd:ColumnDataGrid editable="true"  key="usersForm.checkboxCol" field="isCheck" width="50px" type="checkbox" />
                <sd:ColumnDataGrid editable="false" key="usersForm.userName" field="userName" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.fullName" field="fullName" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.cellphone" field="cellphone" width="200px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.email" field="email" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.dateOfBirth" field="dateOfBirth" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.description" field="description" width="100px"/>

                <sd:ColumnDataGrid editable="false" key="usersForm.telephone" field="telephone" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.fax" field="fax" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.gender" field="gender" width="200px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.identityCard" field="identityCard" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.issueDateIdent" field="issueDateIdent" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.issueDatePassport" field="issueDatePassport" width="100px"/>

                <sd:ColumnDataGrid editable="false" key="usersForm.issuePlacePassport" field="issuePlacePassport" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.passportNumber" field="passportNumber" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.password" field="password" width="200px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.staffCode" field="staffCode" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.birthPlace" field="birthPlace" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.locationId" field="locationId" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="ip" field="ip" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="positionForm.code" field="posCode" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="departmentForm.code" field="deptCode" width="100px"/>

            </sd:DataGrid>
        </div>
    </form>
    <table style="padding-top:20px;">
        <tr>
            <td>
                <div>
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('importUserGridId');">Select all</span>
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('importUserGridId');">Unselect all</span>
                </div>
            </td>
            <td>
                <div align="center" >
                    <sd:Button id="btnImportUser" key="" onclick="page.agreeImport()">
                        <img src="share/images/icons/play.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnDoAction</sd:Property></span>
                    </sd:Button>
                </div>
            </td>
        </tr>
    </table>


</sd:TitlePane>

<script>

</script>