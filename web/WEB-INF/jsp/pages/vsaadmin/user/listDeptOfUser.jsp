<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<sd:TitlePane key="usersForm.DeptTitle" id="lstUserDeptTitlePane">
    <form id="userDeptForm">
        <div id="userDeptGridDiv" style="width:100%;height:250px">
            <sd:DataGrid clientSort="true" getDataUrl="UserAction!onInitDeptList.do?userId=${fn:escapeXml(userId)}" id="DeptGridId" style="width: 100%; height: 100%;" container="userDeptGridDiv" rowSelector="20px" rowsPerPage="20">
                <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="20px" />
                <sd:ColumnDataGrid editable="true"  key="usersForm.checkboxCol" field="isCheck" width="50px" type="checkbox" />
                <sd:ColumnDataGrid editable="false" key="departmentForm.deptName" field="deptName" width="25%"/>
                <sd:ColumnDataGrid editable="false" key="departmentForm.code" field="code" width="25%"/>
                <sd:ColumnDataGrid editable="false" key="departmentForm.status" field="status" width="20%" formatter="page.convertRoleStatus"/>
                <sd:ColumnDataGrid editable="false" key="departmentForm.description" field="description" width="25%"/>
            </sd:DataGrid>
        </div>
        <div style="display:none;">
            <sd:TextBox id="userDeptForm.userId" name="usersForm.userId" key="" />
        </div>
    </form>
    <table style="padding-top:20px">
        <tr>
            <td>
                <div >
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('DeptGridId');">Select all</span>
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('DeptGridId');">Unselect all</span>
                </div>
            </td>
            <td>
                <div align="right">
                    <sd:Button id="usersForm.btnAddDept" key="" onclick="page.preAddDept()">
                        <img src="share/images/icons/a7.png" height="20" width="20"/>
                        <span style="font-size:12px"><sd:Property>btnAddDept</sd:Property></span>
                    </sd:Button>

                    <sd:Button id="usersForm.btnRemoveDept" key="" onclick="page.onRemoveDept()">
                        <img src="share/images/icons/13.png" height="20" width="20"/>
                        <span style="font-size:12px"><sd:Property>btnRemoveDept</sd:Property></span>
                    </sd:Button>
                </div>
            </td>
        </tr>
    </table>



</sd:TitlePane>

<script>
    
</script>