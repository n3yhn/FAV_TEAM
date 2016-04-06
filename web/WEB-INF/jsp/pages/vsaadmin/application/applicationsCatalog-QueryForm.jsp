<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<sd:TitlePane key="applicationsForm.title" id="tltpnapp1">
    <form id="applicationsForm" name="applicationsForm">
        <table width="100%">
            <tr>
                <td>
                    <sd:TextBox maxlength="50" id="applicationsForm.appCode" name="applicationsForm.appCode" key="applicationsForm.appCode" cssStyle="width:80%"/>
                </td>
                <td>
                    <sd:TextBox maxlength="100" id="applicationsForm.appName" name="applicationsForm.appName" key="applicationsForm.appName" cssStyle="width:80%"/>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:SelectBox key="applicationsForm.status" name="applicationsForm.status" id="applicationsForm.status" cssStyle="width:80%;">
                <option value="10"><sd:Property>slt.all</sd:Property></option>
                <option value="1"><sd:Property>slt.active</sd:Property></option>
                <option value="0"><sd:Property>slt.deactive</sd:Property></option>
            </sd:SelectBox>
            </td>
            <td>
                <%--<sd:Textarea rows="4" labelWidth="30%" maxlength="200" inputWidth="70%" id="applicationsForm.description" name="applicationsForm.description" key="applicationsForm.description" cssStyle="width:80%;"/>--%>
                <s:hidden name="applicationsForm.lockDescription" id="applicationsForm.lockDescription"/>
            </td>
            </tr>
        </table>
        <div style="text-align:center;">
            <sd:Button id="applicationsForm.btnSearch" key="" onclick="page.onSearch()">
                <img src="share/images/icons/a1.png" height="20" width="20">
                <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
            </sd:Button>
        </div>
    </form>
</sd:TitlePane>
<script type="text/javascript">
    hideMessage = function(){
        document.getElementById("tblSuccessMessage").style.display = 'none';
        document.getElementById("tblErrorMessage").style.display = 'none';
    };
    
</script>

<sd:TitlePane key="applicationsForm.searchList" id="tltpnapp2">
    <div id="tblSuccessMessage" style="display: none; margin-bottom: 3px; padding: 2px; border-width: 1px;border-color: gray; border-style: solid;background-color: #90EE90">
        <label style="color: green;font-size: 15px;font-family: Time News Roman;border-color: gray; font-weight: bold" id="lblSuccessMessage">
            <sd:Property>alert.updatesucess</sd:Property>
        </label>
    </div>
    <div id="tblErrorMessage" style="display: none;margin-bottom: 3px; padding: 2px; border-bottom-width: 2px; border-left-style: solid;background-color: #FDE5DD">
        <label style="color: red;font-size: 15px;font-family: Time News Roman; font-weight: bold" id="lblErrorMessage">
            <sd:Property>alert.updaterror</sd:Property>
        </label>
    </div>
    <div id="gridDiv" style="width:100%;height: auto;">
        <sd:DataGrid clientSort="true" getDataUrl="application!onSearch.do" id="gridId" rowsPerPage="10" style="width: 100%; height: 100%;" container="gridDiv" rowSelector="0px">
            <sd:ColumnDataGrid editable="false" key="customer.No" get="page.getIndex" styles="text-align:center;" width="5%;" />
            <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" field="isCheck" width="5%" styles="text-align:center;" type="checkbox"/>
            <sd:ColumnDataGrid editable="false" key="btnUpdate" field="" width="5%" get="page.getRow" formatter="page.urlEditApp" styles="text-align:center;"/>
            <sd:ColumnDataGrid editable="false" key="applicationsForm.module" field="" width="5%" styles="text-align:center;" get="page.getRow" formatter="page.urlListObj"/>
            <sd:ColumnDataGrid editable="false" key="applicationsForm.appCode" field="appCode" width="20%" headerStyles="text-align:center;"/>
            <sd:ColumnDataGrid editable="false" key="applicationsForm.appName" field="appName" width="30%" headerStyles="text-align:center;"/>
            <sd:ColumnDataGrid editable="false" key="applicationsForm.description" field="description" width="20%" headerStyles="text-align:center;"/>
            <sd:ColumnDataGrid editable="false" key="applicationsForm.status" field="status" formatter="page.convertStatusToStr" width="8%" headerStyles="text-align:center;"/>
            <!--<sd:ColumnDataGrid editable="false" key="global.view" field="" width="5%" styles="text-align:center;" get="page.getRow" formatter="page.urlListObj"/>-->
            <sd:ColumnDataGrid editable="false" key="applicationsForm.appId" field="appId" styles="display:none;"/>
        </sd:DataGrid>
    </div>
    <table style="width: 100%">
        <tr>
            <td>
                <div style="margin-top:5px;margin-right:5px;">
                    <%--<sd:Button id="applicationsForm.btnObject" key="" onclick="page.onListObject()">
                     <img src="${contextPath}/share/images/icons/a3.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnObject</sd:Property></span>
                    </sd:Button>
                    <sd:Button id="userReport.excel" key="" onclick="page.onExcel()" >
                        <img src="share/images/icons/excel.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>global.excel</sd:Property></span>
                    </sd:Button>--%>
                    <sd:Button id="applicationsForm.btnInsert" key="" onclick="page.preInsert()">
                        <img src="share/images/icons/6.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                    </sd:Button>
                    <%--<sd:Button id="applicationsForm.btnUpdate" key="btnUpdate" onclick="page.preUpdate()"/>--%>
                    <sd:Button id="applicationsForm.btnDelete" key="" onclick="page.onDelete()">
                        <img src="share/images/icons/13.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnDelete</sd:Property></span>
                    </sd:Button>
                    <!--<sd:Button id="applicationsForm.btnImport" key="" onclick="page.preImport()">
                        <img src="${contextPath}/share/images/icons/import.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnImport</sd:Property></span>
                    </sd:Button>
                    <sd:Button id="applicationsForm.btnExport" key="" onclick="page.onExport()">
                        <img src="${contextPath}/share/images/icons/export.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnExport</sd:Property></span>
                    </sd:Button>-->
                    <sd:Button id="applicationsForm.btnEnable" key="" onclick="page.onEnableApp()">
                        <img src="share/images/icons/unlock.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnEnable</sd:Property></span>
                    </sd:Button>
                    <sd:Button id="aapplicationsForm.btnDisable" key="" onclick="page.onDisableApp()">
                        <img src="share/images/icons/lock.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnDisable</sd:Property></span>
                    </sd:Button>
                </div>
            </td>
        </tr>
    </table>
</sd:TitlePane>
<div id="importArea" style="display:none;">
    <jsp:include page="importForm.jsp" />
    <div id="uploadResult" style="width:100%;height:auto;">
        <sd:DataGrid clientSort="true" getDataUrl="application!onLoadResult.do" rowsPerPage="10" id="gridUploadResultId" style="width: 100%; height: 100%;" container="uploadResult" rowSelector="20px">
            <sd:ColumnDataGrid editable="false" key="applicationsForm.appCode" field="appCode" width="20%"/>
            <sd:ColumnDataGrid editable="false" key="objectsForm.objectName" field="objectName" width="30%"/>
            <sd:ColumnDataGrid editable="false" key="result" field="err" width="30%"/>
        </sd:DataGrid>
    </div>
</div>



<script type="text/javascript">
    try{
        dijit.byId("applicationsForm.appCode").focus();
        page.onEnter("applicationsForm", page.onSearch);
    }catch(err){
        alert(err.message);
    }
</script>


