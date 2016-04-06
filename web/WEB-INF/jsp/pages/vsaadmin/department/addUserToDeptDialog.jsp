<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div style="display: none">
    <sd:TextBox id="arrPosition" name="arrPosition" key="" cssStyle="display:none" value="${fn:escapeXml(position)}"/>
    <sd:TextBox id="arrPositionId" name="arrPositionId" key="" cssStyle="display:none" value="${fn:escapeXml(positionId)}"/>
</div>
<script >
//    var arrPosition = eval("${fn:escapeXml(position)}");
//    var arrPositionId = eval("${fn:escapeXml(positionId)}");
    var arrPosition = dijit.byId("arrPosition").getValue();
    var arrPositionId = dijit.byId("arrPositionId").getValue();

    page.displayPos = function(inDatum){
        for(var i= 0; i<arrPosition.length ; i++){
            if(inDatum == arrPositionId[i]){
                return arrPosition[i];
            }
        }
    }

    dijit.byId("addUserToDeptForm.userName").focus();

    // enter key
    page.searchDefault = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                page.onSearchUser();
                break;
        }
    }

    dojo.connect(dojo.byId("searchUserDiv"),"onkeypress",page.searchDefault);

    

</script>


<sd:FieldSet key="usersForm.info" id="userOfDeptForm" >
    <tr>
        <td>
            <div id="searchUserDiv">
                <form id="addUserToDeptForm" name="addUserToDeptForm">
                    <table  width="100%">
                        <tr>
                            <td class="tdOnForm">
                                <sd:TextBox id="addUserToDeptForm.userName" name="usersForm.userName" key="usersForm.userName" cssStyle="width:80%" trim="true"/>
                            </td>

                            <td class="tdOnForm">
                                <sd:TextBox id="addUserToDeptForm.fullName" name="usersForm.fullName" key="usersForm.fullName" cssStyle="width:80%" trim="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdOnForm">
                                <sd:TextBox id="addUserToDeptForm.cellphone" name="usersForm.cellphone" mask="digit" key="usersForm.cellphone" cssStyle="width:80%" trim="true"/>
                            </td>

                            <td class="tdOnForm">
                                <sd:TextBox id="addUserToDeptForm.email" name="usersForm.email" key="usersForm.email" cssStyle="width:80%" trim="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdOnForm">
                                <sd:SelectBox id="addUserToDeptForm.status" disabled="true" key="usersForm.status" name="usersForm.status" cssStyle="width:80%" >
                            <option value="1">Hoạt động</option>
                        </sd:SelectBox>

                        </td>
                        <td style="display:none;">
                            <sd:TextBox id="addUserToDeptForm.deptId" name="usersForm.deptId" key="" cssStyle="width:80%" trim="true"/>
                        </td>
                        </tr>
                    </table>

                </form>
            </div>
            <div align="center" style="padding-top:10px;">
                <sd:Button id="addUserToDeptForm.btnSearch" key="btnSearch" onclick="page.onSearchUser()"/>
            </div>
        </td>
    </tr>
</sd:FieldSet>
<br/>

<sd:FieldSet key="usersForm.userTitle" id="lstUserToAddTitlePane" >
    <tr>
        <td>
            <form id="listUnassignedUserForm">
                <div id="listUnassignedUserGridDiv" style="width:100%;">
                    <sd:DataGrid  rowsPerPage="10" clientSort="true" getDataUrl="" id="listUnassignedUserGridId" style="width: 100%; height: 100%;" container="listUnassignedUserGridDiv" rowSelector="20px">
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial " editable="false" key="index" get="page.getIndex" width="5%" />
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial "  key="column.checkbox" headerCheckbox="true"   type="checkbox" width="10%" cellStyles="text-align:center;"/>
                        
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial " editable="false" key="usersForm.userName" field="userName" width="20%"/>
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial " editable="false" key="usersForm.fullName" field="fullName" width="20%"/>
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial " editable="false" key="usersForm.cellphone" field="cellphone" width="20%"/>
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial " editable="false" key="usersForm.status" field="status" width="10%" formatter="page.convertUserStatus"/>
                    </sd:DataGrid>
                </div>
            </form>

            <table>
                <tr>
                    <td>
                        <div align="left">
                            <sd:Button id="addUserToDeptForm.btnInsert" key="global.checkboxCol" onclick="page.addUser()"/>
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</sd:FieldSet>

