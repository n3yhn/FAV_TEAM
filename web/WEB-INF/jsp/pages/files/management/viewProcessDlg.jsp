<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<jsp:include page="../../util/dataFlow.jsp"/>

<script type="text/javascript">
    page.getNo = function (inRow) {
        return inRow + 1;
    };

    page.getRow = function (inRow) {
        return inRow;
    };

    page.statusFormat = function (inRow) {
        var item = dijit.byId("gridProcessId").getItem(inRow);
        if (item == null) {
            return "";
        }
        var str = "";
        if (item.processStatus != null) {
            var status = item.processStatus.toString();
            var isFee = item.isFee;
            switch (status) {
                case "1":
                    if (isFee == 1) {
                        str = "Mới nộp - chờ tiếp nhận";
                    } else {
                        str = "Mới nộp - chờ xác nhận phí";
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
                    if (isFee == 1) {
                        str = "Đã phê duyệt hồ sơ - chờ đóng dấu số";
                    } else {
                        str = "Đã phê duyệt hồ sơ - chờ nộp lệ phí cấp số";
                    }
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
                    str = "Đã xác nhận phí thẩm định";
                    break;
                case "32":
                    str = "Đã xác nhận lệ phí cấp số";
                    break;
                default:
                    str = "Đang xử lý";
                    ;
            }
        }
        return str;
    };
    page.formatEdit = function (inData) {
        var row = inData - 1;
        var item = dijit.byId("gridProcessId").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var url = "<div style='text-align:left;cursor:pointer;'>";
            url += "<img src='share/images/icons/view.png' width='17px' height='17px' title='Sửa luồng hồ sơ' onClick='page.editProcessAction(" + item.processId + ");' />";
            url += "|<img src='share/images/icons/deleteStand.png' width='17px' height='17px' title='Xóa luồng hồ sơ' onClick='page.deleteProcessAction(" + item.processId + ");' />";
            url += "</div>";
        }
        return url;
    };
</script>
<div id="viewProcessGridDiv" style="width:100%; overflow:auto">
    <sd:DataGrid clientSort="true" 
                 id="gridProcessId"
                 style="width:auto;height:auto"
                 getDataUrl="" 
                 container="viewProcessGridDiv" 
                 serverPaging="true"
                 rowSelector="0px" 
                 rowsPerPage="100"> 
        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" cellStyles="text-align:center;" key="customer.No" get="page.getNo" width="5%"/>
        <sd:ColumnDataGrid  key="Ngày" field="receiveDate" type="date" format="dd/MM/yyyy HH:mm"
                            width="12%"  headerStyles="text-align:center;" />
        <sd:ColumnDataGrid editable="true" key="Hành động" headerStyles="text-align:center;" width="5%" cellStyles="text-align:center;"
                           formatter="page.formatEdit" get="page.getIndex"/>
        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="processComment.groupName" field="sendGroup" width="20%"/>
        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="processComment.userName" field="sendUser" width="20%"/>
        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="documentReceive.receiveGroup" field="receiveGroup" width="20%"/>
        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="documentReceive.receiveUser" field="receiveUser" width="20%"/>
        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="documentReceive.status" get="page.getRow" formatter="page.statusFormat" width="15%"/>
    </sd:DataGrid>        
</div>
<sd:Dialog  id="dlgEditProcess" height="auto" width="400px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="editProcessDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>
<script type="text/javascript">
    page.getViewProcess = function (objectId) {
//        dijit.byId("gridProcessId").vtReload("filesAction!loadFLowView.do?flowForm.fileId=" + objectId, null, null, null);
        dijit.byId("gridProcessId").vtReload("filesAction!loadFLowView.do?flowForm.fileId=" + objectId, null);
    };

    page.updateProcessToAddition = function (objectId) {
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + fileId + "&createForm.viewType=1", "createDiv", null, null, afterLoadForm);
    };
    page.afterUpdateProcessToAddition = function (data) {

    };
    page.editProcessAction = function (objectId) {
        dijit.byId("processAddEditForm.status").vtReload("filesAction!loadAllStatus.do");
        dijit.byId("processAddEditForm.processStatus").vtReload("filesAction!loadAllStatus.do");
        dijit.byId("processAddEditForm.sendUserId").vtReload("filesAction!loadAllUsers.do");
        dijit.byId("processAddEditForm.sendGroupId").vtReload("filesAction!loadAllBusiness.do");
        dijit.byId("processAddEditForm.receiveUserId").vtReload("filesAction!loadAllUsers.do");
        dijit.byId("processAddEditForm.receiveGroupId").vtReload("filesAction!loadAllBusiness.do");
        sd.connector.post("process!showEditPopup.do?processId=" + objectId, null, null, null, page.afterShowEditPopup);
    };
    page.afterShowEditPopup = function (data) {
        var obj = dojo.fromJson(data);
        var customInfo = obj.customInfo;
        dijit.byId("processAddEditForm.processId").setValue(customInfo.processId);
        dijit.byId("processAddEditForm.status").setValue(customInfo.status);
        dijit.byId("processAddEditForm.processStatus").setValue(customInfo.processStatus);
        dijit.byId("processAddEditForm.sendUserId").setValue(customInfo.sendUserId);
        dijit.byId("processAddEditForm.sendUser").setValue(customInfo.sendUser);
        dijit.byId("processAddEditForm.sendGroupId").setValue(customInfo.sendGroupId);
        dijit.byId("processAddEditForm.sendGroup").setValue(customInfo.sendGroup);
        dijit.byId("processAddEditForm.receiveUserId").setValue(customInfo.receiveUserId);
        dijit.byId("processAddEditForm.receiveUser").setValue(customInfo.receiveUser);
        dijit.byId("processAddEditForm.receiveGroupId").setValue(customInfo.receiveGroupId);
        dijit.byId("processAddEditForm.receiveGroup").setValue(customInfo.receiveGroup);
        var dlgCategory = dijit.byId("dlgEditProcess");
        dlgCategory.show();
    };
    page.deleteProcessAction = function (objectId) {
        msg.confirm('Bạn có chắc chắn muốn xóa luồng này không?', '<sd:Property>confirm.title</sd:Property>', page.executeDelete = function () {
                    sd.connector.post("process!onDelete.do?processId=" + objectId + "&" + token.getTokenParamString(), null, null, null, page.afterDeleteProcess);
                });
            };
            page.afterDeleteProcess = function (data) {
                var obj = dojo.fromJson(data);
                var result = obj.items;
                var result0 = result[0];
                if (result0 == "3") {
                    alert(result[1]);
                } else {
                    alert(result[1]);
                    page.getViewProcess(workingFileId);
                }
            };
</script>