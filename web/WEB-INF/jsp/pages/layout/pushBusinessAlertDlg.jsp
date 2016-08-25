<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<script type="text/javascript">
    page.getNo = function (inRow) {
        return inRow + 1;
    };

    page.getRow = function (inRow) {
        return inRow;
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
<sd:TitlePane key="Thông báo doanh nghiệp"
              id="viewBusinessAlertContentList" >
    <table width="100%">
        <tr>
            <td>
                <div id="viewBusinessAlertContentDiv" style="width:100%; overflow:auto">
                    <%--<sd:DataGrid clientSort="true" 
                                 id="gridViewBusinessAlertContentId"
                                 style="width:auto;height:auto"
                                 getDataUrl="" 
                                 container="gridViewBusinessAlertContentDiv" 
                                 serverPaging="true"
                                 rowSelector="0px" 
                                 rowsPerPage="100"> 
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" cellStyles="text-align:center;" key="customer.No" get="page.getNo" width="5%"/>
                        <sd:ColumnDataGrid  key="Ngày thông báo" field="createdDate" type="date" format="dd/MM/yyyy HH:mm"
                                            width="12%"  headerStyles="text-align:center;" />                         
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="Nội dung" field="content" width="80%"/>
                        <sd:ColumnDataGrid editable="true" key="Hành động" headerStyles="text-align:center;" width="5%" cellStyles="text-align:center;"
                                           formatter="page.formatEdit" get="page.getIndex"/>   
                    </sd:DataGrid>--%>        
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
</sd:TitlePane>
