<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<%--<jsp:include page="../../util/dataFlow.jsp"/>--%>
<!--Hiepvv-->

<script type="text/javascript">
    page.getNo = function(index) {
        return dijit.byId("documentProcessGridId123").currentRow + index + 1;
    };

    page.getRow = function(inRow) {
        return inRow;
    };

    page.getIndex = function(index) {
        return index + 1;
    };
//    page.processTypeFormat = function(inRow) {
//        var item = dijit.byId("documentProcessGridId").getItem(inRow);
//        if (item == null) {
//            return "";
//        }
//        var processType = item.processType;
//        var status = item.status;
//        var receiveUserType = item.receiveUserType;
//        var justReference = item.justReference;
//        var str = "Đồng xử lý <img src='share/images/icons/reset.png' width='16' height='16' title='Đổi sang là xử lý chính' onclick='setMainProcess(" + inRow + ")'>";
//        if (receiveUserType.toString() == "1") {
//            str = "Phân phối";
//        } else if (receiveUserType.toString() == "2") {
//            str = "Phân công";
//        } else if (status.toString() == "11" || justReference.toString() == "1") {
//            str = "Nhận để biết";
//        } else if (processType == "1") {
//            str = "Xử lý chính <img src='share/images/icons/reset.png' width='16' height='16' title='Đổi sang là đồng xử lý' onclick='setMainProcess(" + inRow + ")'>";
//        }
//        return str;
//    };

    page.statusFormat = function(inRow) {
        var item = dijit.byId("documentProcessGridId123").getItem(inRow - 1);
        if (item == null) {
            return "";
        }
        var str = "";
        if (item.processStatus != null) {
            var processStatus = item.processStatus.toString();
            var status = item.status.toString();
            var isFee = item.isFee;
            switch (processStatus) {
                case "1":
                    if (status == 0) {
                        if (isFee == 0) {
                            str = "Mới nộp - chờ xác nhận phí";
                        } else {
                            str = "Mới nộp - chờ tiếp nhận hồ sơ";
                        }
                    } else {
                        if (status != 14) {
                            str = "Mới nộp - chờ xác nhận phí";
                        } else {
                            str = "Mới nộp - chờ tiếp nhận hồ sơ";
                        }
                    }
                    break;
                case "2":
                    str = "Đã được đề xuất xử lý";
                    break;
                case "3":
                    str = "Đã được phân công xử lý";
                    break;
                case "4":
                    str = "Đã thẩm định hồ sơ";
                    break;
                case "5":
                    str = "Đã xem xét hồ sơ";
                    break;
                case "6":
                    str = "Đã phê duyệt hồ sơ - chờ nộp lệ phí cấp số";
                    /*
                     if (isFee == 1) {
                     str = "Đã phê duyệt hồ sơ - chờ đóng dấu số";
                     } else {
                     str = "Đã phê duyệt hồ sơ - chờ nộp lệ phí cấp số";
                     }
                     */
                    break;
                case "7":
                    str = "Chuyên viên KL: SĐBS";
                    break;
                case "8":
                    str = "Đã trả lại để thẩm định lại";
                    break;
                case "9":
                    str = "Đã trả lại để xem xét lại";
                    break;
                case "10":
                    str = "Đã tạo giấy phép";
                    break;
                case "11":
                    str = "Đã trình ký";
                    break;
                case "12":
                    str = "Đã ký công bố";
                    break;
                case "13":
                    str = "Từ chối ký";
                    break;
                case "14":
                    str = "Đã tiếp nhận hồ sơ";
                    break;
                case "15":
                    str = "Đã đối chiếu hồ sơ";
                    break;
                case "16":
                    str = "Đã đối chiếu hồ sơ, có sai lệch";
                    break;
                case "17":
                    str = "Đã tiếp nhận hồ sơ SĐBS";
                    break;
                case "18":
                    str = "Mới nộp - chờ tiếp nhận hồ sơ SĐBS";
                    break;
                case "19":
                    str = "Đã xem xét công văn yêu cầu SĐBS";
                    break;
                case "20":
                    str = "Đã gửi công văn yêu cầu SĐBS";
                    break;
                case "21":
                    str = "Hồ sơ Văn thư đã từ chối tiếp nhận";
                    break;
                case "22":
                    str = "Đã trả bản công bố hồ sơ";
                    break;
                case "23":
                    str = "Đã thông báo đối chiếu, chờ đối chiếu hồ sơ gốc";
                    break;
                case "26":
                    str = "Đã xem xét công văn yêu cầu SĐBS";
                    break;
                case "27":
                    str = "Đã phê duyệt công văn yêu cầu SĐBS";
                    break;
                case "28":
                    str = "Đã soạn công văn yêu cầu SĐBS";
                    break;
                case "29":
                    str = "Đã trình cục trưởng xem xét";
                    break;
                case "31":
                    str = "Đã xác nhận phí thẩm định, chờ Văn thư tiếp nhận";
                    break;
                case "32":
                    str = "Đã xác nhận lệ phí cấp số, chờ Văn thư trả hồ sơ";
                    break;
                default:
                    str = "Đang xử lý";
            }
        }
        return str;
    };

</script>

<div id="documentProcessGridDiv123" style="width:100%; overflow-y: auto;max-height: 500px">
    <%--
    <div style="width:100%; overflow: auto;background-color: white;display:none">
        <canvas id="dataFlow"></canvas>  
        <table>
            <tr>
                <td style="border: 1px gray solid;vertical-align: middle; width: 80px; text-align: center;background-color: rgb(192,192,192); ">Mới đến</td>
                <td style="width: 20px;"></td>
                <td style="border: 1px gray solid;vertical-align: middle; width: 80px; text-align: center;background-color: rgb(248,173,173); ">Đang xử lý</td>
                <td style="width: 20px;"></td>
                <td style="border: 1px gray solid;vertical-align: middle; width: 80px; text-align: center;background-color: rgb(173,248,198); ">Trả lại</td>
                <td style="width: 20px;"></td>
                <td style="border: 1px gray solid;vertical-align: middle; width: 80px; text-align: center;background-color: rgb(146,201,231); ">Hoàn thành</td>
                <td></td>
            </tr>
        </table>
    </div>

    <br/>
    --%>
    <sd:DataGrid clientSort="false" 
                 id="documentProcessGridId123"
                 style="width:auto;height:auto"
                 getDataUrl="" 
                 container="documentProcessGridDiv123" 
                 serverPaging="true"
                 rowSelector="0%" 
                 rowsPerPage="100"> 
        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" cellStyles="text-align:center;" key="customer.No" get="page.getNo" width="5%"/>
        <sd:ColumnDataGrid  key="Ngày" field="receiveDate" type="date" format="dd/MM/yyyy HH:mm"
                            width="15%"  headerStyles="text-align:center;" />
        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="processComment.groupName" field="sendGroup" width="20%"/>
        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="processComment.userName" field="sendUser" width="20%"/>
        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="documentReceive.receiveGroup" field="receiveGroup" width="20%"/>
        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="documentReceive.receiveUser" field="receiveUser" width="20%"/>
        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="documentReceive.status" get="page.getIndex" formatter="page.statusFormat" width="15%" styles="text-align:center;"/>
    </sd:DataGrid>
</div>

<script type="text/javascript">
    page.getProcess2= function(objectId) {
        dijit.byId("documentProcessGridId123").vtReload("filesAction!loadFLowView2.do?flowForm.fileId=" + objectId, null, null, null);
    };
</script>