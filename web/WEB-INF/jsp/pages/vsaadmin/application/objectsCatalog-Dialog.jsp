<%-- 
    Document   : objectsCatalog-Dialog
    Created on : Dec 16, 2009, 1:54:34 PM
    Author     : duongtb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<form id="objectsForm" name="objectsForm">
    <table width="100%">
        <tr>
            <th width="15%"></th>
            <th width="35%"></th>
            <th width="15%"></th>
            <th width="35%"></th>
        </tr>
        <tr>
            <td>
                <sd:Label key="objectsForm.objectCode"/>
            </td>
            <td>
                <sd:TextBox id="objectsFormOnDialog.objectCode" name="objectsForm.objectCode" key="" cssStyle="width:80%" maxlength="100" trim="true"/>
            </td>
            <td>
                <sd:Label key="objectsForm.objectName"/>
            </td>                
            <td>
                <sd:TextBox id="objectsFormOnDialog.objectName" name="objectsForm.objectName" key="" cssStyle="width:80%" maxlength="100" required="true" trim="true"/>
            </td>
        </tr>
        <tr>
            <td>
                <sd:Label key="objectsForm.parentId"/>
            </td> 
            <td>
                <sd:TreePicker id="parentObjTreeOnDialog" getChildrenNodeUrl="object!getChildrenDataByNode.do" getTopNodeUrl="object!getData.do?appId=${par}" key="" rootLabel="root" cssStyle="width:80%"/>
                <div style="display:none;"><sd:TextBox id="objectsFormOnDialog.parentId" name="objectsForm.parentId" key="" cssStyle="width:80%"/></div>
            </td>
            <td>
                <sd:Label key="objectsForm.objectType"/>
            </td> 
            <td>
                <sd:SelectBox key="" name="objectsForm.objectType" id="objectsFormOnDialog.objectType" cssStyle="width:80%;" required="true">
            <option value="0"><sd:Property>slt.module</sd:Property></option>
            <option value="1"><sd:Property>slt.component</sd:Property></option>
        </sd:SelectBox>
        </td>
        </tr>
        <tr>
            <td>
                <sd:Label key="objectsForm.objectUrl"/>
            </td> 
            <td>
                <sd:TextBox id="objectsFormOnDialog.objectUrl" name="objectsForm.objectUrl" key="" cssStyle="width:80%" maxlength="100" trim="true"/>
            </td>
            <td>
                <sd:Label key="objectsForm.ord"/>
            </td> 
            <td>
                <sd:TextBox id="objectsFormOnDialog.ord" name="objectsForm.ord" key="" cssStyle="width:80%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td>
                <sd:Label key="objectsForm.status"/>
            </td> 
            <td>
                <sd:SelectBox key="" name="objectsForm.status" id="objectsFormOnDialog.status" cssStyle="width:80%;" required="true" trim="true">
            <option value="1"><sd:Property>slt.active</sd:Property></option>
            <option value="0"><sd:Property>slt.deactive</sd:Property></option>
        </sd:SelectBox>
        </td>
        <td>
            <sd:Label key="objectsForm.description"/>
        </td>
        <td>
            <sd:Textarea labelWidth="30%" rows="1" id="objectsFormOnDialog.description" name="objectsForm.description" key="" cssStyle="width:80%" maxlength="200" trim="true"/>
        </td>
        </tr>
        <tr style="display:none;">
            <td colspan="2">
                <sd:TextBox id="objectsFormOnDialog.appId" name="objectsForm.appId" key="objectsForm.appId" cssStyle="width:80%"/>
            </td>

            <td colspan="2">
                <sd:TextBox id="objectsFormOnDialog.objectId" name="objectsForm.objectId" key="objectsForm.objectId" cssStyle="width:80%"/>
            </td>
        </tr>
    </table>
    <table width="100%">
        <tr>
            <td style="text-align:center;">
                <sd:Button id="objectsFormOnDialog.btnUpdate" key="" onclick="page.onUpdateObject()">
                    <img src="share/images/icons/a3.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnUpdate</sd:Property></span>
                </sd:Button>
                <sd:Button id="objectsFormOnDialog.btnInsert" key="" onclick="page.onInsertObject()">
                    <img src="share/images/icons/save.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                </sd:Button>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    dijit.byId("parentObjTreeOnDialog").onPickData = function(item){
        try{
            if(item.id){
                sd._("objectsFormOnDialog.parentId").setValue(item.id);
            }else {
                sd._("objectsFormOnDialog.parentId").setValue("");
                dijit.byId("parentObjTreeOnDialog").setValue("");
            }
        }catch(err){
            //alert(err.message);
        }
    }
    try{
        sd.widget.__setReadOnly("parentObjTreeOnDialog",true);
    }catch(e){

    }
</script>


