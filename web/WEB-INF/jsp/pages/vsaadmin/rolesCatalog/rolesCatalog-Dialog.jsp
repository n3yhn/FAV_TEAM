<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<script>
<%--    page.addOrSave = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                if (dijit.byId( "rolesFormOnDialog.btnInsert" ).domNode.style.display.length == 0){
                    page.onInsert();
                }
                else{
                    page.onUpdate();
                }
                break;
        }
    }

    dojo.connect(dojo.byId("addItemFormDiv"),"onkeypress",page.addOrSave);
--%></script>

<form id="rolesFormOnDialog" name="rolesForm" onkeydown="return onKeyDownOnCreateForm(event);">
    <div id="addItemFormDiv">
        <table width="100%">
             <tr>
                    <th width="15%"></th>
                    <th width="35%"></th>
                    <th width="15%"></th>
                    <th width="35%"></th>
                </tr>
            <tr>
                <td>
                    <s:hidden id="rolesFormOnDialog.codeSearch" name="rolesForm.codeSearch" />
                    <s:hidden id="rolesFormOnDialog.nameSearch" name="rolesForm.nameSearch" />
                    <s:hidden id="rolesFormOnDialog.descriptionSearch" name="rolesForm.descriptionSearch" />
                    <s:hidden id="rolesFormOnDialog.statusSearch" name="rolesForm.statusSearch" />
                    <s:hidden id="rolesFormOnDialog.roleId" name="rolesForm.roleId" />
                </td>
                <td></td>
            </tr>
            <tr>
                <td>
                        <sx:Label key="rolesForm.code" require="true"/>
                    </td>
                <td>
                    <sd:TextBox maxlength="50" id="rolesFormOnDialog.code" name="rolesForm.code" key="" cssStyle="width:80%" trim="true" required="true"/>
                </td>
                 <td>
                        <sx:Label key="rolesForm.roleName" require="true"/>
                    </td>
                <td>
                    <sd:TextBox maxlength="100" id="rolesFormOnDialog.roleName" name="rolesForm.roleName" key="" cssStyle="width:80%" trim="true" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                        <sx:Label key="rolesForm.description"/>
                    </td>
                <td>
                    <sd:TextBox maxlength="100" id="rolesFormOnDialog.description" name="rolesForm.description" key="" cssStyle="width:80%" trim="true"/>
                </td>
                <td>
                        <sx:Label key="rolesForm.status"/>
                    </td>
                <td>
                    <sd:SelectBox id="rolesFormOnDialog.status" name="rolesForm.status" key="" cssStyle="width:80%">
                        <sd:Option value="1">Hoạt động</sd:Option>
                        <sd:Option value="0">Bị khóa</sd:Option>
                    </sd:SelectBox>
                </td>
            </tr>
        </table>
    </div>
    <table width="100%">
        <tr>
            <td style="text-align:center;">
                <sd:Button id="rolesFormOnDialog.btnUpdate" key="" onclick="page.onUpdate()" >
                    <img src="share/images/icons/a8.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnUpdate</sd:Property></span>
                </sd:Button>
                <sd:Button id="rolesFormOnDialog.btnInsert" key="" onclick="page.onInsert()" >
                    <img src="share/images/icons/a8.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                </sd:Button>
            </td>
        </tr>
    </table>
    <script>
        onKeyDownOnCreateForm = function(e){
            var keynum

            if(window.event) // IE
            {
                keynum = e.keyCode
            }
            else if(e.which) // Netscape/Firefox/Opera
            {
                keynum = e.which
            }

            if(keynum == 13){
                if (dijit.byId( "rolesFormOnDialog.btnInsert" ).domNode.style.display.length == 0){
                    page.onInsert();
                }
                else{
                    page.onUpdate();
                }
            }
            return keynum;
        }
    </script>

</form>

