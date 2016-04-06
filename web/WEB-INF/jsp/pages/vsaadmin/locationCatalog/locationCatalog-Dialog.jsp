<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<script>
    dijit.byId("locationFormOnDialog.locationCode").focus();

    page.addOrSave = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                if (dijit.byId( "locationFormOnDialog.btnInsert" ).domNode.style.display.length == 0){
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

<form id="locationFormOnDialog" name="locationForm">
    <div id="addItemFormDiv">
        <table width="100%">
            <tr>
                <td>
                    <s:hidden name="locationForm.parentId" id="locationFormOnDialog.parentId"/>
                    <s:hidden name="locationForm.locationId" id="locationFormOnDialog.locationId"/>
                    <s:hidden name="locationForm.codeSearch" id="locationFormOnDialog.codeSearch"/>
                    <s:hidden name="locationForm.nameSearch" id="locationFormOnDialog.nameSearch"/>
                    <sd:TextBox id="locationFormOnDialog.locationCode" name="locationForm.locationCode" key="locationForm.locationCode" cssStyle="width:80%" trim="true" required="true"/>
                </td>
                <td>
                    <sd:TextBox id="locationFormOnDialog.locationName" name="locationForm.locationName" key="locationForm.locationName" cssStyle="width:80%" trim="true" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:TextBox id="locationFormOnDialog.description" name="locationForm.description" key="rolesFrom.objects.description" cssStyle="width:80%" trim="true"/>
                </td>
            </tr>
        </table>
    </div>

    <table width="100%">
        <tr>
            <td style="text-align:center;">
                <sd:Button id="locationFormOnDialog.btnUpdate" key="" onclick="page.onUpdate()" >
                    <img src="share/images/icons/a8.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnUpdate</sd:Property></span>
                </sd:Button>
                <sd:Button id="locationFormOnDialog.btnInsert" key="" onclick="page.onInsert()" >
                    <img src="share/images/icons/a8.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                </sd:Button>
            </td>
        </tr>
    </table>

</form>

