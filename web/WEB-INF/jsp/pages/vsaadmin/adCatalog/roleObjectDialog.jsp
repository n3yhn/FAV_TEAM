<%--
    Document   : objectsCatalog-Dialog
    Created on : Dec 16, 2009, 1:54:34 PM
    Author     : duongtb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<form id="objectsFormOnDialog" name="objectsForm">
    <table width="100%">
        <tr>
            <th width="15%"></th>
            <th width="35%"></th>
            <th width="15%"></th>
            <th width="35%"></th>
        </tr>
        <tr>
            <td>
                <sd:Label key="objectsForm.objectName"/>
            </td>
            <td>
                <sd:TextBox id="objectsFormOnDialog.objectName" name="objectsForm.objectName" key="" cssStyle="width:80%" trim="true"/>
            </td>
            <td>
                <sd:Label key="objectsForm.description"/>
            </td>
            <td>
                <sd:TextBox id="objectsFormOnDialog.description" name="objectsForm.description" key="" cssStyle="width:80%" trim="true"/>
            </td>

        </tr>
        <tr>
            <td>
                <sd:Label key="objectsForm.parentId"/>
            </td> 
            <td>
                <%--<sd:TextBox id="objectsFormOnDialog.parentId" name="objectsForm.parentId" key="objectsForm.parentId" cssStyle="width:80%"/>--%>
                <sd:TreePicker id="parentObjTreeOnDialog" getChildrenNodeUrl="ObjectsAction!getChildrenDataByNode.do?appId=${fn:escapeXml(par)}" getTopNodeUrl="ObjectsAction!getData.do" key="" rootLabel="root" cssStyle="width:80%"/>
            </td>
            <td>
                <sd:Label key="objectsForm.objectType"/>
            </td> 
            <td>
                <sd:SelectBox key="" name="objectsForm.objectType" id="objectsFormOnDialog.objectType" cssStyle="width:80%;">
            <option value="M"><sd:Property>slt.module</sd:Property></option>
            <option value="C"><sd:Property>slt.component</sd:Property></option>
        </sd:SelectBox>
        </td>
        </tr>
        <tr>
            <td>
                <sd:Label key="objectsForm.objectUrl"/>
            </td> 
            <td>
                <sd:TextBox id="objectsFormOnDialog.objectUrl" name="objectsForm.objectUrl" key="objectsForm.objectUrl" cssStyle="width:80%" trim="true"/>
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
                <sd:SelectBox key="" name="objectsForm.status" id="objectsFormOnDialog.status" cssStyle="width:80%;">
                    <option value="1"><sd:Property>slt.active</sd:Property></option>
                    <option value="0"><sd:Property>slt.deactive</sd:Property></option>
                </sd:SelectBox>
            </td>
            <td colspan="2">
            </td>
        </tr>
        <tr style="display:none;">
            <td colspan="2">
                <sd:TextBox id="objectsFormOnDialog.appId" name="objectsForm.appId" key="objectsForm.appId" cssStyle="width:80%" trim="true"/>
            </td>

            <td colspan="2">
                <sd:TextBox id="objectsFormOnDialog.objectId" name="objectsForm.objectId" key="objectsForm.objectId" cssStyle="width:80%" trim="true"/>
            </td>

        </tr>
    </table>
    <table width="100%">
        <tr>
            <td style="text-align:center;">
                <sd:Button id="objectsFormOnDialog.btnUpdate" key="btnUpdate" onclick="page.onUpdateObject()"/>
                <sd:Button id="objectsFormOnDialog.btnInsert" key="btnInsert" onclick="page.onInsertObject()"/>
            </td>
        </tr>
    </table>
</form>


