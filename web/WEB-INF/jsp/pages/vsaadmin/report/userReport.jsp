<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<script>
    dijit.byId("reportForm.dateFrom").focus();
</script>

<sd:TitlePane key="menu.report.filter" id="tltpn1">
    <div id="filterFormDiv">
        <form id="reportForm" name="reportForm">
            <table width="100%">
                <tr>
                    <td>
                        <sd:DatePicker id="reportForm.dateFrom" name="reportForm.dateFrom" key="report.dateFrom" cssStyle="width:80%" />
                    </td>
                    <td>
                        <sd:DatePicker id="reportForm.dateTo" name="reportForm.dateTo" key="report.dateTo" cssStyle="width:80%" />
                    </td>
                    <td>
                        <sd:TextBox id="reportForm.user" name="reportForm.user" key="report.user" cssStyle="width:80%" trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <sd:TextBox id="reportForm.actor" name="reportForm.actor" key="report.actor" cssStyle="width:80%" trim="true"/>
                    </td>
                    <td>
                        <sd:SelectBox id="reportForm.action" name="reportForm.action" value="ALL" labelField="name" valueField="id" key="report.actionGroup" cssStyle="width:80%">
                    <option value="ALL">-- Chọn --</option>
                    <option value="CREATE_USER">Tạo người dùng</option>
                    <option value="EDIT_USER">Sửa thông tin người dùng</option>
                    <option value="DELETE_USER">Xóa người dùng</option>
                    <option value="LOCK_USER">Khóa người dùng</option>
                    <option value="UNLOCK_USER">Mở khóa người dùng</option>
                    <option value="RESET_PASSWORD">Reset mật khẩu người dùng</option>
                    <option value="PASSWORD_CHANGE">Đổi mật khẩu</option>
                    <option value="LOGIN_USER">Đăng nhập</option>
                    <option value="SET_UNCHECK_IP">Gán trạng thái không kiểm tra IP cho người dùng</option>
                    <option value="SET_CHECK_IP">Xóa trạng thái không kiểm tra IP của người dùng</option>
                    <option value="ERROR_SEND_SMS">Gửi tin nhắn lỗi</option>
                    <option value="SUCCESS_SEND_SMS">Gửi tin nhắn thành công</option>
                    <option value="UPDATE_IP">Cập nhật địa chỉ IP cho người dùng</option>
                </sd:SelectBox>
                </td>
                <td>
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
        <sd:Button id="userReport.excel" key="" onclick="page.onExcel()" >
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
                <sd:ColumnDataGrid editable="false" key="report.date" field="eventDate" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.actor" field="actor" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.user" field="userName" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.action" field="action" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="report.detail" field="description" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="wan" field="wan" width="200px"/>
                <sd:ColumnDataGrid editable="false" key="ip" field="ip" width="200px"/>
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
        var selectedDateFrom = dijit.byId('reportForm.dateFrom').value;
        var selectedDateTo = dijit.byId('reportForm.dateTo').value;

        if (selectedDateTo == null || selectedDateFrom == null){
            alert('Bạn phải nhập khoảng thời gian tra cứu trước.');
            return false;
        }

        var tmp = (selectedDateTo.getTime() - selectedDateFrom.getTime())/86400000;

        if (tmp < 0)
        {
            alert('Thời gian bắt đầu không được lớn hơn thời gian kết thúc.');
            return false;
        }

        if (tmp >=20){
            alert('Chỉ xem được lịch xử thao tác trong khoảng thời gian 20 ngày.');
            return false;
        }


        dijit.byId('gridId').vtReload("UserReportAction!onViewUser.do","reportForm") ;
    }
    page.onExcel = function(){
        var selectedDateFrom = dijit.byId('reportForm.dateFrom').value;
        var selectedDateTo = dijit.byId('reportForm.dateTo').value;

        if (selectedDateTo == null || selectedDateFrom == null){
            alert('Bạn phải nhập khoảng thời gian tra cứu trước.');
            return false;
        }

        var yearFrom = selectedDateFrom. getFullYear();
        var monthFrom = selectedDateFrom. getMonth() + 1;
        var dateFrom = selectedDateFrom.getDate();
        var yearTo = selectedDateTo. getFullYear();
        var monthTo = selectedDateTo. getMonth() + 1;
        var dateTo = selectedDateTo.getDate();
        
        var tmp = (selectedDateTo.getTime() - selectedDateFrom.getTime())/86400000;

        if (tmp < 0)
        {
            alert('Thời gian bắt đầu không được lớn hơn thời gian kết thúc.');
            return false;
        }

        if (tmp >=20){
            alert('Chỉ xem được lịch xử thao tác trong khoảng thời gian 20 ngày.');
            return false;
        }

        var ext = "?pass=true";

        ext = ext + "&dateFrom=" + yearFrom + "-" + monthFrom + "-" + dateFrom;



        ext = ext + "&dateTo=" + yearTo + "-" + monthTo + "-" + dateTo;

        var actor = dijit.byId('reportForm.actor').value;
        if (actor.length > 0){
            ext = ext + "&actor=" + actor;
        }

        var user = dijit.byId('reportForm.user').value;
        if (user.length > 0){
            ext = ext + "&user=" + user;
        }

        var action = dijit.byId('reportForm.action').value;
        if (action.length > 0){
            ext = ext + "&action=" + action;
        }
        var afterCallback = function(data){
            eval("var data1 = "+data);
            console.log(data1);
            if (data1.customInfo[0] == "error"){
                alert("Số lượng kết quả quá lớn, hãy nhập các tiêu chí tìm kiếm chính xác hơn");
            }else{
                window.location = "UserReportAction!onExcelUser.do" + ext;
            }

        }
        sd.connector.post("UserReportAction!checkReportResult.do" + ext, null, "reportForm", null, afterCallback);
        //window.location = "UserReportAction!onExcelUser.do" + ext;
    }
</script>