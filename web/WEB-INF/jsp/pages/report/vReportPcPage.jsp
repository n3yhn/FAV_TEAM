<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<style type="text/css">
    .box:hover{
        color: #ffff00;
        cursor: pointer;
    }
</style>

<script>
    page.getNo = function(index) {
        return dijit.byId("filesGrid").currentRow + index + 1;
    };

    page.getIndex = function(index) {
        return index + 1;
    };
    page.getRow = function(inRow) {
        return inRow;
    };

    page.formatEdit = function(inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var url = "<div style='text-align:center;cursor:pointer;'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + item.fileId + ");' />";
            url += "</div>";
        }
        return url;
    };

    page.formatStatus = function(inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var status = parseInt(item.status);
            switch (status) {
                case 1:
                    url = "Mới nộp";
                    break;
                case 2:
                    url = "Đã được đề xuất xử lý";
                    break;
                case 50:
                    url = "Đã phân công xử lý";
                    break;
                case 45:
                    url = "Đã thẩm định";
                    break;
                case 46:
                    url = "Đã xem xét kết quả";
                    break;
                case 47:
                    url = "Đã phê duyệt kết quả";
                    break;
                case 7:
                    url = "Chuyên viên KL: SĐBS";
                    break;
                case 43:
                    url = "Đã trả lại để thẩm định lại";
                    break;
                case 48:
                    url = "Đã trả lại để xem xét lại";
                    break;
                case 10:
                    url = "Đã lập giấy";
                    break;
                case 11:
                    url = "Đã trình ký giấy";
                    break;
                case 12:
                    url = "Đã ký giấy";
                    break;
                case 13:
                    url = "Đã từ chối ký giấy";
                    break;
                case 15:
                    url = "Đã đối chiếu";
                    break;
                case 16:
                    url = "Đã đối chiếu, có sai lệch";
                    break;
                case 30:
                    url = "Sắp hết hạn bổ sung";
                    break;
                case 40:
                    url = "Tổng số hồ sơ trong ngày";
                    break;
                case 44:
                    url = "Đã phân công";
                    break;
                case 49:
                    url = "Đã gửi phối hợp và có ý kiến";
                    break;
                case 22:
                    url = "Đã trả bản công bố";
                    break;

                default:
                    url = "Mới tạo";
            }
        }

        return url;
    };
    page.formatStatusView = function(inData) {
        var row = inData;
        var item = dijit.byId("filesGrid").getItem(row);
        var url = "";
        var display = "";
        if (item != null) {
            display = item.displayStatus;
            var status = parseInt(item.status);
            switch (status) {
                case 8:
                    url = "<a href='#' onClick='page.showEvaludateResult(" + row + ");'>" + display + "</a>";
                    break;
                default:
                    url = display;
                    break;
            }
        }
        return url;
    };
    
    // enter key
    page.searchDefault = function(evt) {
        var dk = dojo.keys;
        switch (evt.keyCode) {
            case dk.ENTER:
                page.search();
                break;
        }
    }

    dojo.connect(dojo.byId("searchForm"), "onkeypress", page.searchDefault);
    
</script>

<div id="token">
    <s:token id="tokenId"/>
</div>
<div id="searchDiv">
        <form id="searchForm" name="searchForm">            
        </form>
    <sd:TitlePane key="Danh sách hồ sơ"
                  id="businessList" >
        <table width="100%">
            <tr>
                <td>
                    <div id="fileGridDiv" style="width:100%;">
                        <sd:DataGrid id="filesGrid"
                                     getDataUrl=""
                                     rowSelector="0%"
                                     style="width:auto;height:auto"
                                     rowsPerPage="20"
                                     serverPaging="true"
                                     clientSort="false">
                            <sd:ColumnDataGrid key="category.No" get="page.getNo" width="3%"  styles="text-align:center;" />                            
                            <sd:ColumnDataGrid  key="Tên cán bộ xử lý hồ sơ" field="receiveUser"
                                                width="20%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tổng hồ sơ thực phẩm thường" field="thuong"  cellStyles="text-align:center;"
                                                width="20%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tổng hồ sơ thực phẩm chức năng" field="chucNang"  cellStyles="text-align:center;"
                                                width="20%"  headerStyles="text-align:center;" />                            
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>
<div id="createDiv"></div>
<script type="text/javascript">
    var workingFileId;
    var fileId = 0;

    page.search = function() {
        dijit.byId("filesGrid").vtReload('vReportPcAction!onsearchReportLProcess.do', "searchForm");
    };
    page.showSearchPanel = function() {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };
    page.search();
</script>
