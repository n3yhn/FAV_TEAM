<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<script>
    page.addOrSave = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                if (dijit.byId( "departmentFormOnDialog.btnInsert" ).domNode.style.display.length == 0){
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
    <form id="departmentFormOnDialog" name="departmentForm">
        <table width="100%">
            <tr>
                <th width="15%"></th>
                <th width="35%"></th>
                <th width="15%"></th>
                <th width="35%"></th>
            </tr>
            <tr>
                <td>
                    <sx:Label key="deptFormOnDialog.deptName" require="true"/>
                </td>
                <td class="tdOnForm">
                    <sd:TextBox id="departmentFormOnDialog.deptName" name="departmentForm.deptName" key=""  cssStyle="width:80%" maxlength="300" trim="true" />
                </td>
                <td>
                    <sx:Label key="departmentForm.deptCode" require="true"/>
                </td>
                <td class="tdOnForm">
                    <sd:TextBox id="departmentFormOnDialog.deptCode" name="departmentForm.deptCode" key="" cssStyle="width:80%" maxlength="20" trim="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    <sx:Label key="deptFormOnDialog.type" require="true"/>
                </td>
                <td class="tdOnForm">
                    <sd:SelectBox id="departmentFormOnDialog.type" key=""
                                  name="departmentForm.deptTypeId" cssStyle="width:80%" data="deptTypeList"
                                  labelField="typeName" valueField="deptTypeId"   onchange="page.reloadDeptType()"/>
                </td>
                <td>
                    <sx:Label key="deptFormOnDialog.status" require="true"/>
                </td>
                <td class="tdOnForm">
                    <sd:SelectBox id="departmentFormOnDialog.status" key="" name="departmentForm.status" cssStyle="width:80%" trim="true">
                        <sd:Option value="1">Hoạt động</sd:Option>
                        <sd:Option value="0">Bị khóa</sd:Option>
                    </sd:SelectBox>
                </td>
            </tr>
            <tr>
                <td>                        
                </td>
                
                <td class="tdOnForm">
                </td>
                <td> 
                    <sx:Label key="departmentForm.parent" />
                </td>
                <td>
                    <div>
                        <sd:TreePicker id="parentDeptTree" getChildrenNodeUrl="departmentAction!getDeptChildrenDataByNode.do"
                                       getTopNodeUrl="departmentAction!getDeptData.do"  key="" rootLabel="root" cssStyle="width:80%" />

                        <sd:TextBox cssStyle="display:none;" id="departmentFormOnDialog.parentId" name="departmentForm.parentId" key=""/>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <sx:Label key="departmentForm.telephone" />
                </td>
                <td class="tdOnForm">
                    <sd:TextBox id="departmentFormOnDialog.telephone" name="departmentForm.telephone" key="" cssStyle="width:80%" mask="^\d*$" maxlength="20" trim="true"/>
                </td>

                <td>
                    <sx:Label key="departmentForm.address" />
                </td>
                <td class="tdOnForm">
                    <sd:TextBox id="departmentFormOnDialog.address" name="departmentForm.address" key="" cssStyle="width:80%" maxlength="250" trim="true"/>
                </td>

            </tr>
            <tr>
                <td>
                    <sx:Label key="departmentForm.contactName" />
                </td>
                <td class="tdOnForm">
                    <sd:TextBox id="departmentFormOnDialog.contactName" name="departmentForm.contactName" key="" cssStyle="width:80%" maxlength="50" trim="true"/>
                </td >

                <td>
                    <sx:Label key="departmentForm.contactTitle" />
                </td>
                <td class="tdOnForm">
                    <sd:TextBox id="departmentFormOnDialog.contactTitle" name="departmentForm.contactTitle" key="" cssStyle="width:80%" maxlength="50" trim="true"/>
                </td>

            </tr>

            <tr>
                <td>
                    <sx:Label key="departmentForm.description" />
                </td>
                <td class="tdOnForm">
                    <sd:TextBox id="departmentFormOnDialog.description" name="departmentForm.description" key="" cssStyle="width:80%" maxlength="100" trim="true" />
                </td>

                <td>
                    <sx:Label key="departmentForm.email" />
                </td>
                <td class="tdOnForm">
                    <sd:TextBox id="departmentFormOnDialog.email" name="departmentForm.email" key="" cssStyle="width:80%" maxlength="50" trim="true"/>
                </td>

            </tr>

            <tr>
                <td>
                    <sx:Label key="departmentForm.tel" />
                </td>
                <td class="tdOnForm">
                    <sd:TextBox id="departmentFormOnDialog.tel" name="departmentForm.tel" key="" cssStyle="width:80%" mask="^\d*$" maxlength="20" trim="true"/>
                </td>
                <%--
                <td>
                    <sx:Label key="departmentForm.tin" />
                </td>
                <td class="tdOnForm">
                    <sd:TextBox id="departmentFormOnDialog.tin" name="departmentForm.tin" key="" cssStyle="width:80%" maxlength="20" trim="true"/>
                </td>
                --%>
                <%-- Them so thu tu vao Department --%>
                <td>
                    <sx:Label key="Số thứ tự sắp xếp" />
                </td>
                <td class="tdOnForm">
                    <sd:TextBox id="departmentFormOnDialog.tin" name="departmentForm.tin" key="" cssStyle="width:80%" maxlength="10" trim="true" mask="digit"/>
                </td>
            </tr>
            <tr>
                <td>
                    <sx:Label key="departmentForm.fax" />
                </td>
                <td class="tdOnForm">
                    <sd:TextBox id="departmentFormOnDialog.fax" name="departmentForm.fax" key="" cssStyle="width:80%" mask="^\d*$" maxlength="20" trim="true"/>
                </td>
                <td><sx:Label key="Tỉnh/Thành phố" /></td>
                <td>
                    <sd:TextBox id="departmentFormOnDialog.deptId" name="departmentForm.deptId" key="" trim="true" cssStyle="display:none"/>
                    <sd:SelectBox id="departmentFormOnDialog.provinceId" name="departmentForm.provinceId" key="" valueField="categoryId" labelField="name" data="lstProvince" cssStyle="width:80%" />
                    <sd:TextBox id="departmentFormOnDialog.provinceName" name="departmentForm.provinceName" key="" trim="true" cssStyle="display:none"/>
                </td>
            </tr>

        </table>

        <table width="100%">
            <tr>
                <td style="text-align:center;">
                    <sd:Button id="departmentFormOnDialog.btnUpdate" key="Lưu" onclick="page.onUpdate()"/>
                    <sd:Button id="departmentFormOnDialog.btnInsert" key="Lưu" onclick="page.onInsert()"/>
                    <sd:Button id="departmentFormOnDialog.btnClose" key="Đóng" onclick="page.closeDialogDeptId()"/>
                </td>
            </tr>
        </table>

    </form>
</div>
<script>
    page.reloadDeptType= function(){
        ///  alert(value);
        var value=dijit.byId("departmentFormOnDialog.type").value;
        dijit.byId("parentDeptTree").getChildrenNodeUrl="departmentAction!getDeptChildrenDataByNode.do?deptTypeId="+value;
        dijit.byId("parentDeptTree").getTopNodeUrl="departmentAction!getDeptData.do?deptTypeId="+value;
        //   alert(value);
    }
    dijit.byId("parentDeptTree").onPickData = function(item){
        try{
            if(item.id){
                sd._("departmentFormOnDialog.parentId").setValue(item.id);
            }else{
                sd._("departmentFormOnDialog.parentId").setValue("");
                sd._("parentDeptTree").setValue("");
            }
        }catch(err){
            alert(err.message);
        }
    }

    sd.widget.__setReadOnly("parentDeptTree",true);
</script>

