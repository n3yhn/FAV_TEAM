<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<script>
    page.addOrSave = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                if (dijit.byId( "deptTypeFormOnDialog.btnInsert" ).domNode.style.display.length == 0){
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
<form id="deptTypeFormOnDialog" name="deptTypeForm">
    <table width="100%">
        <tr>

            <td>
                <sd:TextBox id="deptTypeFormOnDialog.typeName" name="deptTypeForm.typeName" key="deptTypeFormOnDialog.typeName" cssStyle="width:80%" maxlength="100" trim="true"/>
            </td>
            <td>
                <sd:TextBox id="deptTypeFormOnDialog.description" name="deptTypeForm.description" key="deptTypeForm.description" cssStyle="width:80%" maxlength="200" trim="true"/>
            </td>

            <td style="display:none;">
                <sd:TextBox id="deptTypeFormOnDialog.deptTypeId" name="deptTypeForm.deptTypeId" key=""/>
            </td>
        </tr>

    </table>

    <table width="100%">
        <tr>
            <td style="text-align:center;">
                <sd:Button id="deptTypeFormOnDialog.btnUpdate" key="btnUpdate" onclick="page.onUpdate()"/>
                <sd:Button id="deptTypeFormOnDialog.btnInsert" key="btnInsert" onclick="page.onInsert()"/>
            </td>
        </tr>
    </table>

</form>

<script>
    dijit.byId("deptTypeFormOnDialog.typeName").focus();
</script>

