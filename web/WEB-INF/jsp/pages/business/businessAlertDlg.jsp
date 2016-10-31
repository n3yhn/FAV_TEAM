<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<script type="text/javascript">
    page.getNo = function (inRow) {
        return inRow + 1;
    };

    page.getRow = function (inRow) {
        return inRow;
    };

    page.formatEdit = function (inData) {
        var row = inData - 1;
        var item = dijit.byId("gridBusinessAlertId").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var url = "<div style='text-align:center;cursor:pointer;'>";
//            url += "<img src='share/images/icons/view.png' width='17px' height='17px' title='Sửa luồng hồ sơ' onClick='page.editProcessAction(" + item.processId + ");' />";
            url += "<img src='share/images/icons/deleteStand.png' width='17px' height='17px' title='Xóa luồng hồ sơ' onClick='page.deleteProcessAction(" + item.businessAlertId + ");' />";
            url += "</div>";
        }
        return url;
    };
</script>
<table width="100%">
    <tr>
        <td>
            <div id="viewProcessGridDiv" style="width:100%; overflow:auto">
                <sd:DataGrid clientSort="true" 
                             id="gridBusinessAlertId"
                             style="width:auto;height:auto"
                             getDataUrl="" 
                             container="viewProcessGridDiv" 
                             serverPaging="true"
                             rowSelector="0px" 
                             rowsPerPage="100"> 
                    <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" cellStyles="text-align:center;" key="customer.No" get="page.getNo" width="5%"/>
                    <sd:ColumnDataGrid  key="Ngày" field="createdDate" type="date" format="dd/MM/yyyy"
                                        width="12%"  headerStyles="text-align:center;" />
                    <sd:ColumnDataGrid editable="true" key="Hành động" headerStyles="text-align:center;" width="5%" cellStyles="text-align:center;"
                                       formatter="page.formatEdit" get="page.getIndex"/>        
                    <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="Nội dung" field="content" width="80%"/>
                </sd:DataGrid>        
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <sd:Button id="btnAddnewBusinessAlert" key="" onclick="page.addNewBusinessAlert();" cssStyle="display:" cssClass="buttonGroup">
                <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                <span style="font-size:12px">Thêm mới</span>
            </sd:Button>
        </td>
    </tr>
</table>

<sd:Dialog  id="dlgAddEditBusinessAlert" height="auto" width="400px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="addEditBusinessAlertDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>

<script type="text/javascript">
    var workingFileId = dijit.byId("createBusinessAlertForm.businessId").getValue();
    page.getViewLstBusinessAlert = function (objectId) {
        dijit.byId("createBusinessAlertForm.businessId").setValue(objectId);
        workingFileId = objectId;
        dijit.byId("gridBusinessAlertId").vtReload("businessAlertAction!loadBusinessAlertView.do?searchForm.businessId=" + objectId);
    };

    page.insert = function () {
        page.clearInsertForm();
        dijit.byId("dlgAddEditBusinessAlert").show();
    };

    page.clearInsertForm = function () {
        dijit.byId("createBusinessAlertForm.businessId").setValue("");
        dijit.byId("createBusinessAlertForm.content").setValue("");
        dijit.byId("createBusinessAlertForm.businessAlertId").setValue("");

    };

    page.setItem = function (item) {
        dijit.byId("createBusinessAlertForm.businessId").setValue(item.businessId);
        dijit.byId("createBusinessAlertForm.content").setValue(item.content);
        dijit.byId("createBusinessAlertForm.businessAlertId").setValue(item.businessAlertId);
    };

    page.clearInsertForm = function () {
        dijit.byId("createForm.businessId").setValue("");
        dijit.byId("createForm.content").setValue("");
        dijit.byId("createBusinessAlertForm.businessAlertId").setValue("");
    };

    page.addNewBusinessAlert = function () {
        page.clearInsertForm();
        dijit.byId("dlgAddEditBusinessAlert").show();
    };

    page.showEditPopup = function (row) {
        var item = dijit.byId("businessGrid").getItem(row);
        page.setItem(item);
        dijit.byId("dlgAddEditBusinessAlert").show();
    };
    page.deleteProcessAction = function (objectId) {
        msg.confirm('Bạn có chắc chắn muốn xóa thông báo này không?', '<sd:Property>confirm.title</sd:Property>', page.executeDelete = function () {
                    sd.connector.post("businessAlertAction!onDelete.do?businessAlertId=" + objectId + "&" + token.getTokenParamString(), null, null, null, page.afterDeleteBusinessAlert);
                });
            };
            page.afterDeleteBusinessAlert = function (data) {
                var obj = dojo.fromJson(data);
                var result = obj.items;
                var result0 = result[0];
                if (result0 == "3") {
                    alert(result[1]);
                } else {
                    alert(result[1]);
                    page.getViewLstBusinessAlert(workingFileId);
                }
            };

</script>