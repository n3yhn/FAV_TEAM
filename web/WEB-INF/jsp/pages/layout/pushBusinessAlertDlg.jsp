<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
    page.getNo = function (index) {
        return dijit.byId("gridListAlertBusinessId").currentRow + index + 1;
    };
    page.getIndex = function (index) {
        return index + 1;
    };

    page.formatEdit = function (inData) {
        var row = inData - 1;
        var item = dijit.byId("gridViewBusinessAlertContentId").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var url = "<div style='text-align:center;cursor:pointer;'>";
            url += "<img src='share/images/icons/deleteStand.png' width='17px' height='17px' title='Đã đọc' onClick='page.deleteProcessAction(" + item.processId + ");' />";
            url += "</div>";
        }
        return url;
    };
</script>
<sd:TitlePane key="Danh sách thông báo"
              id="viewBusinessAlertContentList" >
    <%--
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
                    <sd:ColumnDataGrid  key="Ngày" field="createdDate" type="date" format="dd/MM/yyyy HH:mm"
                                        width="12%"  headerStyles="text-align:center;" />     
                    <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="Nội dung" field="content" width="80%"/>
                </sd:DataGrid>        
            </div>
            </td>
        </tr>
        <tr>
            <td>
                <sd:Button id="btnUpdateSeenAll" key="" onclick="page.updateSeenAll();" cssStyle="display:" cssClass="buttonGroup">
                    <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Xem truoc"/>
                    <span style="font-size:12px">Đã đọc tất cả</span>
                </sd:Button>
            </td>
        </tr>
    </table>
    --%>
    <table class="viewTable" id="detailDiv">        
        <tr>
            <td>
                <div id="pushBusinessAlert.content"></div>
            </td>
        </tr>  
        <tr>
            <td>
                <sd:Button key="" onclick="page.checkSeenAlert();" >
                    <img src="share/images/signature.png" height="14" width="18" alt="">
                    <span style="font-size:12px">Đánh dấu đã đọc thông báo</span>
                </sd:Button>
            </td>

        </tr>
    </table>
</sd:TitlePane>
<script>
    page.replaceBrTblpushBusinessAlertForm = function () {
        var content = "";
        content = document.getElementById("pushBusinessAlert.content").innerHTML;
        content = content.replace(/\n/g, "<br>");
        document.getElementById("pushBusinessAlert.content").innerHTML = content;
    };
    page.checkSeenAlert = function () {
        msg.confirm("Nếu đánh dấu đã đọc thông báo này sẽ không hiển thị lại, chỉ khi có thông báo mới gửi tới bạn?", "Cảnh báo", onSaveCheckedSeenAlert);
    };
    onSaveCheckedSeenAlert = function () {
        sd.connector.post("businessAlertAction!onUpdateCheckedSeen.do?" + token.getTokenParamString(), null, null, null, afterSaveCheckedSeenAlert);
    };
    afterSaveCheckedSeenAlert = function () {
        var dialog = dijit.byId("vt-businessAlert");
        dialog.hide();
    };

</script>
