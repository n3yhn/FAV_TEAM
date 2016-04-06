<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<script>
    dijit.byId("unitTreeFormOnDialog.treeName").focus();

    page.addOrSave = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                if (dijit.byId( "unitTreeFormOnDialog.btnInsert" ).domNode.style.display.length == 0){
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

<form id="unitTreeFormOnDialog" name="unitTreeForm">
    <div id="addItemFormDiv">
        <s:hidden name="unitTreeForm.rootDeptId" id="unitTreeFormOnDialog.rootDeptId" value=""/>
        <s:hidden name="unitTreeForm.id" id="unitTreeFormOnDialog.id" value=""/>
        <table width="100%">
            <tr>
                <td>
                    <sd:TextBox id="unitTreeFormOnDialog.treeName" name="unitTreeForm.treeName" key="unitTreeForm.treeName" cssStyle="width:80%" trim="true" required="true"/>
                </td>
                <td>
                    <sd:TextBox id="unitTreeFormOnDialog.description" name="unitTreeForm.description" key="unitTreeForm.description" cssStyle="width:80%" trim="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:SelectBox id="unitTreeFormOnDialog.appId" name="unitTreeForm.appId" labelField="appName" valueField="appId" key="unitTreeForm.appId" data="appList" cssStyle="width:80%" required="true"/>
                </td>
                <td>
                </td>
            </tr>
        </table>
    </div>

    <table width="100%">
        <tr>
            <td style="text-align:center;">
                <sd:Button id="unitTreeFormOnDialog.btnUpdate" key="btnUpdate" onclick="page.onUpdate()"/>
                <sd:Button id="unitTreeFormOnDialog.btnInsert" key="btnInsert" onclick="page.onInsert()"/>
            </td>
        </tr>
    </table>
</form>

