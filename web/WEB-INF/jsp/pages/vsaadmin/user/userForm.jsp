
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<form id="usersFormOnDialog" name="usersForm">
    <sd:FieldSet key="usersForm.info">
        <table width="100%">
            <tr>
                <td>
                    <sd:TextBox id="usersFormOnDialog.userName" name="usersForm.userName" key="usersFormOnDialog.userName" cssStyle="width:80%" required ="true" maxlength="100" readonly="true" />
                </td>
                <td>
                    <sd:TextBox id="usersFormOnDialog.fullName" name="usersForm.fullName" key="usersFormOnDialog.fullName" cssStyle="width:80%" required="true" maxlength="180"/>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:TextBox id="usersFormOnDialog.email" name="usersForm.email" key="usersFormOnDialog.email" cssStyle="width:80%" maxlength="50" required="true" />
                </td>

                <td>
                    <sd:TextBox id="usersFormOnDialog.cellphone" name="usersForm.cellphone" key="usersFormOnDialog.cellphone" cssStyle="width:80%" mask="^\d*$" maxlength="20" required="true" />
                </td>
            </tr>
            <tr>
                <td>
                    <sd:TextBox id="usersFormOnDialog.aliasName" name="usersForm.aliasName" key="usersForm.aliasName" cssStyle="width:80%" maxlength="50" />
                </td>
                <td>
                    <sd:TextBox id="usersFormOnDialog.birthPlace" name="usersForm.birthPlace" key="usersForm.birthPlace" cssStyle="width:80%" maxlength="100" />
                </td>
            </tr>
            <tr>
                <td>
                    <sd:TextBox id="usersFormOnDialog.telephone" name="usersForm.telephone" key="usersForm.telephone" mask="^\d*$" cssStyle="width:80%" maxlength="20"/>
                </td>
                <td>
                    <sd:TextBox id="usersFormOnDialog.fax" name="usersForm.fax" key="usersForm.fax" mask="digit" cssStyle="width:80%" maxlength="20"/>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:TextBox id="usersFormOnDialog.staffCode" name="usersForm.staffCode" key="usersForm.staffCode" mask="digit" cssStyle="width:80%" maxlength="6"/>
                </td>

                <td>
                    <sd:TextBox id="usersFormOnDialog.identityCard" name="usersForm.identityCard" key="usersForm.identityCard" cssStyle="width:80%" required="true" maxlength="15" mask="string"/>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:TextBox id="usersFormOnDialog.issuePlaceIdent" name="usersForm.issuePlaceIdent" key="usersForm.issuePlaceIdent" cssStyle="width:80%" maxlength="100" />
                </td>
                <td>
                    <sd:TextBox id="usersFormOnDialog.description" name="usersForm.description" key="usersForm.description" cssStyle="width:81%" maxlength="100"/>
                </td>
            </tr>
            <tr>
                <td>

                </td>
                <td>
                    <div style="display:none;">
                        <sd:TextBox id="usersFormOnDialog.userId" name="usersForm.userId" key="" />
                    </div>
                </td>
            </tr>
            <%-- [congdh 25/10
              <tr>
                <td>
                    <sd:TextBox id="usersFormOnDialog.ip" name="usersForm.ip" key="ip" cssStyle="width:81%" maxlength="1000" required="true"/>
                </td>
                <td style="color:#0066cc;">
                    <sd:TreePicker id="deptTree" getChildrenNodeUrl="UserAction!getChildrenDataByNode.do" getTopNodeUrl="UserAction!getData.do"  key="usersForm.department" rootLabel="root" cssStyle="width:80%" required="true"/>
                    <div style="display:none"><sd:TextBox id="usersFormOnDialog.deptId" name="usersForm.deptId" key=""/></div>
                </td>
            </tr>
            congdh 25/10]--%>
            <tr>
                <%-- [congdh 25/10
                <td>
                    <sd:SelectBox id="usersFormOnDialog.posId" name="usersForm.posId" labelField="posName" valueField="posId" key="positionForm.posName" data="positionList" cssStyle="width:80%" required="true"/>
                </td>
                congdh 25/10]--%>
                <td>
                    <sd:TextBox id="retypePassword" name="usersForm.password" key="passwordVerify" cssStyle="width:80%" type="password" required="true"/>
                </td>
            </tr>
        </table>
    </sd:FieldSet>
</form>
<%--</br>
<center><font color="red" size="4">Lưu ý: Địa chỉ IP cần nhập là địa chỉ Wan của máy tính</font></center>--%>
<%-- [congdh 25/10
<script>
    dijit.byId("deptTree").setValue("${deptTree}");
    dijit.byId("usersFormOnDialog.deptId").setValue("${deptId}");
    dijit.byId("deptTree").onPickData = function(item){
        try{
            if(item){
                dojo.byId("usersFormOnDialog.deptId").value = item.id;
            }else {
                dojo.byId("usersFormOnDialog.deptId").value = "";
                dijit.byId("deptTree").setValue("");
            }
        }catch(err){
            alert(err.message);
        }
    };
    sd.widget.__setReadOnly("deptTree",true);
</script>
congdh 25/10]--%>