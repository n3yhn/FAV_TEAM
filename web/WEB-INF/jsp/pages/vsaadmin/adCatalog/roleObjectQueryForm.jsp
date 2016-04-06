<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

    <div id="gridObjectDiv" style="width:100%;height:400px;">
        <sd:DataGrid clientSort="true" getDataUrl="/vsaadminv3/AdRoleAction!onListObject.do?roleId=${fn:escapeXml(roleId)}" id="gridObjectId" style="width: 100%; height: 100%;" container="gridObjectDiv" rowSelector="20px" rowsPerPage="10">
            <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="20px" />
            <sd:ColumnDataGrid editable="false" key="objectsForm.objectName" field="objectName" width="150px;" />
            <sd:ColumnDataGrid editable="false" key="objectsForm.objectUrl" field="objectUrl" width="150px;" />
            <sd:ColumnDataGrid editable="false" key="objectsForm.objectType" field="objectType" width="100px;" formatter="page.convertTypeToStr"/>
            <sd:ColumnDataGrid editable="false" key="applicationsForm.appCode" field="appCode" width="150px;"/>
            <sd:ColumnDataGrid editable="false" key="applicationsForm.appName" field="appName" width="150px;"/>
            <sd:ColumnDataGrid editable="false" key="objectsForm.status" field="status" width="100px;" formatter="page.convertStatusToStr"/>
            <sd:ColumnDataGrid editable="false" key="objectsForm.description" field="description" width="100px;" />
            <sd:ColumnDataGrid editable="false" key="objectsForm.appId" field="appId" styles="display:none;"/>
            <sd:ColumnDataGrid editable="false" key="objectsForm.objectId" field="objectId" styles="display:none;"/>
        </sd:DataGrid>
    </div>


 <script type="text/javascript">
        try{
            document.getElementById("rspn").innerHTML = "${fn:escapeXml(roleCode)}";
            document.getElementById("rspn").style.color="red";
            document.getElementById("rspn").style.fontStyle="italic";
            document.getElementById("rspn").style.fontWeight="lighter";
        }catch(err){
        }
 </script>
