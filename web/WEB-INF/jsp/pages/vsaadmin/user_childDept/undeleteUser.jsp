<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

Người dùng sau không xóa được do đã được gán vai trò hoặc phòng ban:
<br>
<sd:FieldSet key="">
    <tr>
        <td>

            <form id="userDeptFormOnDialog1">
                <div id="userDeptGridDiv1" style="width:100%;height:200px">
                    <sd:DataGrid clientSort="true" getDataUrl="" id="undeleteUserGridId" style="width: 100%; height: 100%;" container="userDeptGridDiv1" rowSelector="20px" rowsPerPage="20">
                        <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="10px" />
                        <sd:ColumnDataGrid editable="false" key="usersForm.userName" field="userName" width="30%"/>
                        <sd:ColumnDataGrid editable="false" key="usersForm.fullName" field="fullName" width="30%"/>
                        <sd:ColumnDataGrid editable="false" key="usersForm.cellphone" field="cellphone" width="30%"/>
                    </sd:DataGrid>
                </div>
            </form>

        </td>
    </tr>
</sd:FieldSet>
