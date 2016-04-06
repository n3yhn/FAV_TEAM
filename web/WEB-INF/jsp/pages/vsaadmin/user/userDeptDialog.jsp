<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script >

    //var arrPosition = ["Trưởng Phòng","Nhân viên","Trưởng ban","Trưởng nhóm"];

    var arrPosition = eval("${fn:escapeXml(position)}");
    var arrPositionId = eval("${fn:escapeXml(positionId)}");

    page.displayPos = function(inDatum){
        for(var i= 0; i<arrPosition.length ; i++){
            if(inDatum == arrPositionId[i]){
                return arrPosition[i];
            }
        }
    }
    
    dijit.byId("userDeptFormOnDialog.deptName").focus();

    // enter key
    page.searchDefault = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                page.searchDept();
                break;
        }
    }

    dojo.connect(dojo.byId("searchDeptDiv"),"onkeypress",page.searchDefault);

</script>


<sd:FieldSet key="userDeptDialog.title" id="UDDialog.tltpn" >
    <tr>
        <td>
            <div id="searchDeptDiv">
                <form id="userDeptFormOnDialog" name="userDeptFormOnDialog">
                    <table  width="100%">
                        <tr>
                            <td>
                                <sd:TextBox id="userDeptFormOnDialog.deptName" name="departmentForm.deptName" key="departmentForm.deptName" cssStyle="width:80%" trim="true"/>
                            </td>

                            <td>
                                <sd:TextBox id="userDeptFormOnDialog.code" name="departmentForm.code" key="departmentForm.code" cssStyle="width:80%" trim="true"/>
                            </td>

                        </tr>
                        <tr>

                            <td>
                                <sd:TextBox id="userDeptFormOnDialog.address" name="departmentForm.address" key="departmentForm.address" cssStyle="width:80%" trim="true"/>
                            </td>
                            <td>
                                <sd:TextBox id="userDeptFormOnDialog.telephone" name="departmentForm.telephone" key="departmentForm.telephone" cssStyle="width:80%" trim="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <sd:SelectBox id="userDeptFormOnDialog.type" key="departmentForm.type" name="departmentForm.deptTypeId" cssStyle="width:80%" data="deptTypeList" labelField="typeName" valueField="deptTypeId"/>

                            </td>
                            <td style="display:none;">
                                <sd:TextBox id="userDeptFormOnDialog.userId" name="departmentForm.userId" key="" cssStyle="width:80%" trim="true"/>
                            </td>
                        </tr>
                    </table>

                </form>
            </div>
            <div align="center" style="padding-top:10px;">
                <sd:Button id="userDeptFormOnDialog.btnSearch" key="btnSearch" onclick="page.searchDept()"/>
            </div>
        </td>
    </tr>
</sd:FieldSet>
<%--</sd:TitlePane>

<br/>
<sd:TitlePane key="userDeptDialog.deptTitle" id="userDeptTitlePane">--%>
<sd:FieldSet key="userDeptDialog.deptTitle" id="userDeptTitlePane" theme="blue">
    <tr>
        <td>
            <form id="userDeptFormOnDialog">
                <div id="userDeptGridDiv" style="width:100%;height:200px">
                    <sd:DataGrid clientSort="true" 
                                 getDataUrl="" 
                                 id="userDeptGridId" 
                                 style="width: 100%; height: 100%;" 
                                 container="userDeptGridDiv" 
                                 rowSelector="20px"
                                 rowsPerPage="10">
                        <sd:ColumnDataGrid key="userRoleFormOnDialog.checkboxCol" width="5%" type="checkbox" field="isCheck" editable="true"/>
                        <sd:ColumnDataGrid editable="false" key="departmentForm.deptName" field="deptName" width="20%"/>
                        <sd:ColumnDataGrid editable="false" key="departmentForm.code" field="code" width="20%"/>
                        <sd:ColumnDataGrid editable="false" key="departmentForm.status" field="status" width="20%" formatter="page.convertRoleStatus"/>
                        <sd:ColumnDataGrid editable="false" key="departmentForm.description" field="description" width="20%"/>
                        <sd:ColumnDataGrid editable="true"  key="usersForm.selectboxCol" field="position" width="20%" type="select" arrOption="arrPosition" arrValue="arrPositionId" formatter="page.displayPos"/>
                    </sd:DataGrid>
                </div>
            </form>
            <table  style="padding-top:20px">
                <tr>
                    <td>
                        <div>
                            <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('userDeptGridId');">Select all</span>
                            <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('userDeptGridId');">Unselect all</span>
                        </div>
                    </td>
                    <td>
                        <div align="center" >
                            <sd:Button id="userDeptFormOnDialog.btnAgree" key="btnAgree" onclick="page.agreeToAddDept()"/>
                        </div>
                    </td>
                </tr>
            </table>



        </td>
    </tr>
</sd:FieldSet>
