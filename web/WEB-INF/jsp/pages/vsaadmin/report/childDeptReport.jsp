<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<sd:TitlePane key="menu.report.filter" id="tltpn1">
    <div align="center">
        <sd:Button id="userReport.view" key="" onclick="page.onView()" >
            <img src="share/images/icons/3.png" height="20" width="20">
            <span style="font-size:12px"><sd:Property>global.view</sd:Property></span>
        </sd:Button>
        <sd:Button id="createUserReportForm.excel" key="" onclick="page.onExcel()" >
            <img src="share/images/icons/excel.png" height="20" width="20">
            <span style="font-size:12px"><sd:Property>global.excel</sd:Property></span>
        </sd:Button>
    </div>
</sd:TitlePane>
<br/>
<br/>
<sd:TitlePane key="menu.report.result" id="tltpn2">
    <form id="form1">
        <div id="gridDiv" style="width:100%;height:500px">
            <sd:DataGrid clientSort="true" getDataUrl="/vsaadminv3/ChildDeptReportAction!onInitChildDept.do" id="gridId" style="width:100%; height:100%;" container="gridDiv" rowSelector="20px">
                <sd:ColumnDataGrid editable="false" key="report.deptLevel" field="level" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.deptCode" field="deptCode" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.deptName" field="deptName" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.contactName" field="contactName" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.tin" field="tin" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.address" field="address" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.deptTypeName" field="typeName" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.status" field="status" width="20%"/>
            </sd:DataGrid>
        </div>
    </form>
</sd:TitlePane>

<script>
    page.searchDefault = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                page.onView();
                break;
        }
    }

    dojo.connect(dojo.byId("filterFormDiv"),"onkeypress",page.searchDefault);

    page.onView = function(){        
        dijit.byId('gridId').vtReload("ChildDeptReportAction!onViewChildDept.do",null) ;
    }
    page.onExcel = function(){
        window.location = "ChildDeptReportAction!onExcelChildDept.do";
    }
</script>