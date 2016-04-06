<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<sd:TitlePane key="menu.report.filter" id="tltpn1">
    <div id="createUserReportFilterFormDiv">
        <form id="reportForm" name="reportForm">
            <table width="100%">
                <tr>
                    <th width="15%"></th>
                    <th width="35%"></th>
                    <th width="15%"></th>
                    <th width="35%"></th>
                </tr>
                <tr>
                    <td>
                        <sd:Label key="report.dateFrom"/>
                    </td>
                    <td>
                        <sd:DatePicker id="reportForm.dateFrom" name="reportForm.dateFrom" key="" cssStyle="width:80%" format="dd/MM/yyyy"/>
                    </td>
                    <td>
                        <sd:Label key="report.dateTo"/>
                    </td>
                    <td>
                        <sd:DatePicker id="reportForm.dateTo" name="reportForm.dateTo" key="" cssStyle="width:80%" format="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <sd:Label key="usersForm.department"/>
                    </td>
                    <td style="color:#0066cc;">
                        <sd:TreePicker id="deptTree" getChildrenNodeUrl="UserRoleReportAction!getChildrenDataByNode.do" getTopNodeUrl="UserRoleReportAction!getData.do"  key="" rootLabel="root" cssStyle="width:80%"/>
                        <div style="display:none"><sd:TextBox id="reportForm.deptId" name="reportForm.deptId" key=""/></div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <br/>
    <div align="center">
        <sd:Button id="userReport.view" key="" onclick="page.onView()" >
            <img src="share/images/icons/3.png" height="20" width="20">
            <span style="font-size:12px"><sd:Property>global.view</sd:Property></span>
        </sd:Button>
        <sd:Button id="createUserReportForm.excel" key="" onclick="page.onExcelCreateUser()" >
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
            <sd:DataGrid clientSort="true" getDataUrl="/vsaadminv3/UserReportAction!onInitUser.do" id="gridId" style="width:100%; height:100%;" container="gridDiv" rowSelector="20px">
                <sd:ColumnDataGrid editable="false" key="report.index" field="level" width="10%"/>
                <sd:ColumnDataGrid editable="false" key="report.deptName" field="deptName" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.userName" field="userName" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.fullName" field="fullName" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.actor" field="actor" width="10%"/>
                <sd:ColumnDataGrid editable="false" key="report.date" field="eventDate" width="10%"/>
                <sd:ColumnDataGrid editable="false" key="positionForm.description" field="description" width="30%"/>
            </sd:DataGrid>
        </div>
    </form>
</sd:TitlePane>

<script>
    dijit.byId("deptTree").onPickData = function(item){

        try{
            if(item.id){
                sd._("reportForm.deptId").setValue(item.id);
            }else {
                sd._("reportForm.deptId").setValue("");
                dijit.byId("deptTree").setValue("");
            }
        }catch(err){
            alert(err.message);
        }
    }

    sd.widget.__setReadOnly("deptTree",true);
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
        var temp = "" + dijit.byId('reportForm.deptId').value;
        if (temp == ""){
            alert('Bạn chưa chọn phòng ban!');
            return false;
        }
        var ext = "?pass=true";
        var selectedDateFrom = dijit.byId('reportForm.dateFrom').value;
        if (selectedDateFrom != null){
            var year = selectedDateFrom. getFullYear();
            var month = selectedDateFrom. getMonth() + 1;
            var date = selectedDateFrom.getDate();
            if (month <10){
                month = "0" + month;
            }
            if (date < 10){
                date = "0" + date;
            }
            ext = ext + "&dateFrom=" + date + "/" + month + "/" + year;
        }

        var selectedDateTo = dijit.byId('reportForm.dateTo').value;
        if (selectedDateTo != null){
            var year = selectedDateTo. getFullYear();
            var month = selectedDateTo. getMonth() + 1;
            var date = selectedDateTo.getDate();
            if (month <10){
                month = "0" + month;
            }
            if (date < 10){
                date = "0" + date;
            }
            ext = ext + "&dateTo=" + date + "/" + month + "/" + year;
        }


        dijit.byId('gridId').vtReload("ChangePasswordReportAction!onViewChangePassword.do" + ext,"reportForm") ;
    }
    page.onExcelCreateUser = function(){
        var temp = "" + dijit.byId('reportForm.deptId').value;
        if (temp == ""){
            alert('Bạn chưa chọn phòng ban!');
            return false;
        }
        var ext = "?pass=true";
        ext += "&deptId=";
        ext += temp;
        var selectedDateFrom = dijit.byId('reportForm.dateFrom').value;
        if (selectedDateFrom != null){
            var year = selectedDateFrom. getFullYear();
            var month = selectedDateFrom. getMonth() + 1;
            var date = selectedDateFrom.getDate();
            if (month <10){
                month = "0" + month;
            }
            if (date < 10){
                date = "0" + date;
            }
            ext = ext + "&dateFrom=" + date + "/" + month + "/" + year;
        }

        var selectedDateTo = dijit.byId('reportForm.dateTo').value;
        if (selectedDateTo != null){
            var year = selectedDateTo. getFullYear();
            var month = selectedDateTo. getMonth() + 1;
            var date = selectedDateTo.getDate();
            if (month <10){
                month = "0" + month;
            }
            if (date < 10){
                date = "0" + date;
            }
            ext = ext + "&dateTo=" + date + "/" + month + "/" + year;
        }

        window.location = "ChangePasswordReportAction!onExcelChangePassword.do" + ext;
    }
</script>