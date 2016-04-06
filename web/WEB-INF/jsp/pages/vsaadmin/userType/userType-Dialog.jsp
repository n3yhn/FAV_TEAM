<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>


<script>
    page.addOrSave = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                if (dijit.byId( "userTypeFormOnDialog.btnInsert" ).domNode.style.display.length == 0){
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
    <form id="userTypeFormOnDialog" name="userTypeForm">
        <table width="100%">
            <tr>
                <td>
                    <sd:TextBox id="userTypeFormOnDialog.typeName" name="userTypeForm.typeName" key="userTypeFormOnDialog.typeName" cssStyle="width:90%" maxlength="100" trim="true"/>
                </td>
                <td>
                    <sd:TextBox id="userTypeFormOnDialog.description" name="userTypeForm.description" key="userTypeForm.description" cssStyle="width:90%" maxlength="200" trim="true"/>
                </td>
                <td style="display:none;">
                    <sd:TextBox id="userTypeFormOnDialog.userTypeId" name="userTypeForm.userTypeId" key=""/>
                </td>
            </tr>
        </table>

        <table width="100%">
            <tr>
                <td style="text-align:center;">
                    <sd:Button id="userTypeFormOnDialog.btnUpdate" key="btnUpdate" onclick="page.onUpdate()"/>
                    <sd:Button id="userTypeFormOnDialog.btnInsert" key="btnInsert" onclick="page.onInsert()"/>
                </td>
            </tr>
        </table>

    </form>
</div>
<script>
    dijit.byId("userTypeFormOnDialog.typeName").focus();
</script>
