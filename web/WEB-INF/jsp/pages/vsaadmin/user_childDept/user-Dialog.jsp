<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>


<script>
    page.addOrSave = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                if (dijit.byId( "usersFormOnDialog.btnInsert" ).domNode.style.display.length == 0){
                    page.onInsert();
                }
                else{
                    page.onUpdate();
                }
                break;
        }
    }

    dojo.connect(dojo.byId("addItemFormDiv"),"onkeypress",page.addOrSave);
</script>

<div id="addItemFormDiv">
    <form id="usersFormOnDialog" name="usersForm">
        <sd:FieldSet key="usersForm.info" theme="blue">
            <table width="100%">
                <tr>
                    <th width="15%"></th>
                    <th width="35%"></th>
                    <th width="15%"></th>
                    <th width="35%"></th>
                </tr>
                <tr>
                    <td>
                        <sx:Label key="usersFormOnDialog.userName" require="true"/>
                    </td>
                    <td class="tdOnForm">
                        
                        <sd:TextBox trim="true" id="usersFormOnDialog.userName" name="usersForm.userName" key="" cssStyle="width:80%" required ="true" maxlength="100"/>                         
                        <input type="hidden" id="usersFormOnDialog.staffId" name="usersForm.staffId"/>
                        <input type="hidden" id="canShowDlg" name="canShowDlg"/>
                    </td>

                    <td>
                        <sx:Label key="usersFormOnDialog.fullName" require="true"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="usersFormOnDialog.fullName" name="usersForm.fullName" key="" cssStyle="width:80%" required="true" maxlength="100"/>
                    </td>
                </tr>
                <tr id="trPassword">
                    <td>
                        <sx:Label key="usersFormOnDialog.password" require="true"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="usersFormOnDialog.password" name="usersForm.password" key="" required="true"  cssStyle="width:80%" type="password"/>
                    </td>

                    <td>
                        <sx:Label key="usersFormOnDialog.retypePassword" require="true"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="retypePassword" name="retypePassword" key="" required="true" cssStyle="width:80%" type="password"/>

                    </td>
                </tr>
                <tr>
                    <td>
                        <sx:Label key="usersFormOnDialog.email"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="usersFormOnDialog.email" name="usersForm.email" key="" cssStyle="width:80%" maxlength="50" required="true" />
                    </td>

                    <td>
                        <sx:Label key="usersFormOnDialog.cellphone" require="true"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="usersFormOnDialog.cellphone" name="usersForm.cellphone" key="" cssStyle="width:80%" mask="^\d*$" maxlength="20"/>

                    </td>
                </tr>
                <tr>
                    <td>
                        <sx:Label key="departmentForm.root" require ="true"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TreePicker name="userDeptTree" id="userDeptTree" getChildrenNodeUrl="departmentAction!getDeptChildrenDataByNode.do"
                                       getTopNodeUrl="departmentAction!getMyDeptRootTree.do"  key="" rootLabel="root" cssStyle="width:80%" />
                        <sd:TextBox cssStyle="display:none;" id="usersFormOnDialog.deptId" name="usersForm.deptId" key=""/>
                    </td>
                    <td><sx:Label key="Doanh nghiệp"/></td>
                    <td>
                        <sd:SelectBox key="" id="usersFormOnDialog.businessId" name="usersForm.businessId" data="lstBusiness" valueField="businessId" labelField="businessName" cssStyle="width:80%"/>
                        <sd:TextBox cssStyle="display:none;" id="usersFormOnDialog.businessName" name="usersForm.businessName" key=""/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <sx:Label key="usersFormOnDialog.posId" require="true"/>
                    </td>
                    <td>
                        <sd:SelectBox id="usersFormOnDialog.posId" key="" name="usersForm.posId" cssStyle="width:80%" required="true"
                                      valueField="posId"
                                      labelField="posName"
                                      data="lstPosition"
                                      />
                    </td>
                    <td>
                        <sx:Label key="usersFormOnDialog.status" require="true"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:SelectBox id="usersFormOnDialog.status" key="" name="usersForm.status" cssStyle="width:80%" required="true">
                            <sd:Option value="-1">--Chọn--</sd:Option>
                            <sd:Option value="1">Hoạt động</sd:Option>
                            <sd:Option value="0">Bị khóa</sd:Option>
                        </sd:SelectBox>
                    </td>
                </tr>
            </table>
        </sd:FieldSet>

        <br/>

        <sd:FieldSet key="usersForm.addInfo" theme="blue">
            <%--<div style="height:100px">--%>
            <table width="100%">
                <tr>
                    <th width="50%"></th>
                    <th width="50%"></th>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="usersFormOnDialog.aliasName" name="usersForm.aliasName" key="usersForm.aliasName" cssStyle="width:80%" maxlength="50" />
                    </td>

                    <td class="tdOnForm">
                        <sd:SelectBox id="usersFormOnDialog.gender" key="usersForm.gender" name="usersForm.gender" cssStyle="width:80%">
                            <sd:Option value="-1">--Chọn--</sd:Option>
                            <sd:Option value="1">Nam</sd:Option>
                            <sd:Option value="0">Nữ</sd:Option>
                        </sd:SelectBox>
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="usersFormOnDialog.telephone" name="usersForm.telephone" key="usersForm.telephone" mask="^\d*$" cssStyle="width:80%" maxlength="20"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="usersFormOnDialog.fax" name="usersForm.fax" key="usersForm.fax" cssStyle="width:80%" mask="digit" maxlength="20"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:DatePicker id="usersFormOnDialog.dateOfBirth" name="usersForm.dateOfBirth" key="usersForm.dateOfBirth" cssStyle="width:80%" format="dd/MM/yyyy" />
                    </td>

                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="usersFormOnDialog.birthPlace" name="usersForm.birthPlace" key="usersForm.birthPlace" cssStyle="width:80%" maxlength="100" />
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="usersFormOnDialog.staffCode" name="usersForm.staffCode" key="usersForm.staffCode" cssStyle="width:80%" maxlength="50"/>
                    </td>

                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="usersFormOnDialog.identityCard" name="usersForm.identityCard" key="usersForm.identityCard" cssStyle="width:80%" maxlength="14" required="true" mask="digit"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:DatePicker id="usersFormOnDialog.issueDateIdent" name="usersForm.issueDateIdent" key="usersForm.issueDateIdent" format="dd/MM/yyyy" cssStyle="width:80%"/>
                    </td>

                    <td class="tdOnForm">
                        <sd:TextBox trim="true" id="usersFormOnDialog.issuePlaceIdent" name="usersForm.issuePlaceIdent" key="usersForm.issuePlaceIdent" cssStyle="width:80%" maxlength="100"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:Textarea trim="true" id="usersFormOnDialog.description" name="usersForm.description" key="usersForm.description" cssStyle="width:80%" maxlength="100"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:Textarea id="usersFormOnDialog.lastResetPassword" name="usersForm.lastResetPassword" key="usersForm.lastResetPassword" cssStyle="width:80%" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdOnForm">
                        <sd:Textarea id="usersFormOnDialog.lastChangePassword" name="usersForm.lastChangePassword" key="usersForm.lastChangePassword" cssStyle="width:80%" readonly="true"/>
                    </td>
                    <td class="tdOnForm">
                        <sd:Textarea id="usersFormOnDialog.lastLogin" name="usersForm.lastLogin" key="usersForm.lastLogin" cssStyle="width:80%" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <td style="display:none;">
                        <sd:TextBox trim="true" id="usersFormOnDialog.userId" name="usersForm.userId" key="" />
                    </td>
                </tr>
            </table>
        </sd:FieldSet>
    </form>
</div>
<table width="100%">
    <tr>
        <td style="text-align:center;">
            <sd:Button id="usersFormOnDialog.btnUpdate" key="btnUpdate" onclick="page.onUpdate()"/>
            <sd:Button id="usersFormOnDialog.btnInsert" key="btnInsert" onclick="page.onInsert()"/>
        </td>
    </tr>
</table>


<script>
    dijit.byId("usersFormOnDialog.userName").focus();

    dijit.byId("userDeptTree").onPickData = function(item){
        try{
            if(item.id){
                sd._("usersFormOnDialog.deptId").setValue(item.id);
            }else{
                sd._("usersFormOnDialog.deptId").setValue("");
                sd._("userDeptTree").setValue("");
            }
        }catch(err){
            alert(err.message);
        }
    }
    
    selectUserType = function(){
        //alert(document.getElementById("canShowDlg").value);
        if(document.getElementById("canShowDlg").value == "view"){
            return;
        }
        if(document.getElementById("canShowDlg").value == "edit"){
            document.getElementById("canShowDlg").value = "true";
            return;
        }
        if(document.getElementById("canShowDlg").value == "create"){
            document.getElementById("canShowDlg").value = "true";
        }

        document.getElementById("usersFormOnDialog.staffId").value = 0;
        
        var selectItem = dijit.byId("usersFormOnDialog.type").getValue();

        if(selectItem == 1){
            clearStaffDialog();
            showSelectStaffPopup();
        } else if(selectItem == 2) {
            clearStudentDialog();
            showSelectStudentPopup();
        }
    }
    
    showSelectStaffPopup =function(){
        page.onSearchStaff();
        var dlg = dijit.byId("selectStaffDlg");
        dlg.show();
    }

    showSelectStudentPopup =function(){
        page.onSearchStudent();
        var dlg = dijit.byId("selectStudentDlg");
        dlg.show();
    }
</script>

