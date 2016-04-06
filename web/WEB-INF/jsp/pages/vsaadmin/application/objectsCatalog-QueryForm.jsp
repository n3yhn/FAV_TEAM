<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<table width="100%">
    <tr>
        <td valign="top">
            <sd:TitlePane id="ttpnObjectList" key="applicationsForm.moduleList">
                <table width="100%">
                    <tr>
                        <td><div id="gridObjectDiv" style="width:100%;">
                                <sd:DataGrid clientSort="true" 
                                             getDataUrl="object!onInit.do?parentId=0&appId=${par}" 
                                             rowsPerPage="10" id="gridObjectId" 
                                             container="gridObjectDiv" style="width: 100%; height: 100%;"  
                                             rowSelector="0px"
                                             serverPaging="true"
                                             >
                                    <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="20px" />
                                    <sd:ColumnDataGrid editable="true" key="usersForm.checkboxCol" field="isCheck" width="20px" styles="text-align:center;" type="checkbox" headerCheckbox="true"/>
                                    <sd:ColumnDataGrid editable="false" key="usersForm.edit" field="" width="60px;" get="page.getRow" formatter="page.urlEdit" styles="text-align:center;"/>
                                    <sd:ColumnDataGrid editable="false" key="objectsForm.objectCode" field="objectCode" width="100px;" />
                                    <sd:ColumnDataGrid editable="false" key="objectsForm.objectName" field="objectName" width="200px;" />
                                    <sd:ColumnDataGrid editable="false" key="objectsForm.objectUrl" field="objectUrl" width="350px;" />
                                    <sd:ColumnDataGrid editable="false" key="objectsForm.objectType" field="objectTypeId" width="100px;" formatter="page.convertTypeToStr"/>
                                    <sd:ColumnDataGrid editable="false" key="objectsForm.ord" field="ord" width="50px;"/>
                                    <sd:ColumnDataGrid editable="false" key="objectsForm.status" field="status" width="70px;" formatter="page.convertStatusToStr"/>
                                    <sd:ColumnDataGrid editable="false" key="objectsForm.description" field="description" width="150px;" />
                                    <sd:ColumnDataGrid editable="false" key="objectsForm.appId" field="appId" styles="display:none;"/>
                                    <sd:ColumnDataGrid editable="false" key="objectsForm.objectId" field="objectId" styles="display:none;"/>
                                    <sd:ColumnDataGrid editable="false" key="objectsForm.parentId" field="parentId" styles="display:none;"/>
                                </sd:DataGrid>
                            </div>
                            <table style="width: 100%">
                                <tr>
                                    <!--td>
                                        <div >
                                            <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('gridObjectId');">Select all</span>
                                            <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('gridObjectId');">Unselect all</span>
                                        </div>
                                    </td-->
                                    <td>
                                        <div>
                                            <sd:Button id="objectsForm.btnInsert" key="" onclick="page.preInsertObject()">
                                                <img src="share/images/icons/6.png" height="20" width="20">
                                                <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                                            </sd:Button>
                                            <%--<sd:Button id="objectsForm.btnUpdate" key="btnUpdate" onclick="page.preUpdateObject()"/>--%>
                                            <sd:Button id="objectsForm.btnDelete" key="" onclick="page.onDeleteObject()">
                                                <img src="share/images/icons/13.png" height="20" width="20">
                                                <span style="font-size:12px"><sd:Property>btnDelete</sd:Property></span>
                                            </sd:Button>
                                            <sd:Button id="adForm.btnEnable" key="" onclick="page.unlock()">
                                                <img src="share/images/icons/unlock.png" height="20" width="20">
                                                <span style="font-size:12px"><sd:Property>btnEnable</sd:Property></span>
                                            </sd:Button>
                                            <sd:Button id="adForm.btnDisable" key="" onclick="page.lock()">
                                                <img src="share/images/icons/lock.png" height="20" width="20">
                                                <span style="font-size:12px"><sd:Property>btnDisable</sd:Property></span>
                                            </sd:Button>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </sd:TitlePane>
        </td>
    </tr>
</table>
<script type="text/javascript">

        page.onNodeClick = function(item, node, event){
            try{
                if(item.id == undefined){
                    sd._("objectsFormOnDialog.parentId").setValue("");
                    dijit.byId("parentObjTreeOnDialog").setValue("");
                    try{
                        var panel = dijit.byId("ttpnObjectList");
                        panel.titleNode.innerHTML = "Danh sách chức năng";
                    }catch(ex){

                    }
                    dijit.byId('gridObjectId').vtReload("object!onGetChildrenObj.do?parentId=0",null) ;
                }else{
                    sd._("objectsFormOnDialog.parentId").setValue(item.id);
                    dijit.byId("parentObjTreeOnDialog").setValue(item.name);
        
                    try{
                        var panel = dijit.byId("ttpnObjectList");
                        panel.titleNode.innerHTML = "Danh sách chức năng con cấp 1 của " + item.name;
                    }catch(e){

                    }
                    dijit.byId('gridObjectId').vtReload("object!onGetChildrenObj.do?parentId=" + item.id,null) ;
//                    alert(panel.titleNode.innerHTML);
            }
        
            }catch(err){
            }
        };
</script>