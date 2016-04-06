<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    page.getNo = function (index) {
        return dijit.byId("filesGrid_Changes").currentRow + index + 1;
    };
    page.getIndex = function (index) {
        return index + 1;
    };
    page.formatEdit = function (inData) {
        var item = dijit.byId("filesGrid_Changes").getItem(inData - 1);
        var url = "";
        if (item != null) {
            url = "<div style='text-align:center;cursor:pointer;'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + item.fileId + ");' /></div>";
        }
        return url;
    };
    page.formatStatus = function (inData) {
        var item = dijit.byId("filesGrid_Changes").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var status = parseInt(item.status);
            switch (status) {
                case 0:
                    url = "Mới tạo";
                    break;
                case 1:
                    url = "Mới nộp";
                    break;
                case 6:
                    url = "Đã phê duyệt kết quả";
                    break;
                case 14:
                    url = "Đã tiếp nhận";
                    break;
                case 15:
                    url = "Đã đối chiếu";
                    break;
                case 17:
                    url = "Đã tiếp nhận hồ sơ SĐBS";
                    break;
                case 18:
                    url = "Mới nộp - chờ tiếp nhận SĐBS";
                    break;
                case 20:
                    url = "Đã gửi công văn yêu cầu sửa đổi bổ sung";
                    break;
                case 21:
                    url = "Hồ sơ đã bị VT từ chối tiếp nhận";
                    break;
                case 22:
                    url = "Đã trả bản công bố";
                    break;
                case 33:
                    url = "Hồ sơ đã bị VT từ chối tiếp nhận SĐBS";
                    break;
                default:
                    url = "Đang xử lý";
            }
            var isFee = item.isFee;
            if (isFee == 2) {
                url = "Hồ sơ đã bị kế toán từ chối";
            }
        }

        return url;
    };
</script>
<div id="token">
    <s:token id="tokenId"/>
</div>
    <table width="100%">
        <tr>
            <td>
                <div id="fileGridDiv11" style="width:100%;">
                    <sd:DataGrid id="filesGrid_Changes"
                                 getDataUrl=""
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="category.No" get="page.getNo" width="5%"  styles="text-align:center;" />

                        <sd:ColumnDataGrid editable="true" key="Xem" headerStyles="text-align:center;" width="3%" cellStyles="text-align:center;"
                                           formatter="page.formatEdit" get="page.getIndex"/>                        
                        <!--                                     -->
                        <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode" width="7%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Loại hồ sơ" field="fileTypeName"
                                            width="20%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName"  cellStyles="text-align:left;"
                                            width="10%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Ngày nộp" field="sendDate" format="dd/MM/yyyy" type="date"
                                            width="8%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Ngày phê duyệt" field="approveDate" format="dd/MM/yyyy" type="date"
                                            width="8%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Ngày hẹn trả" field="deadlineApprove" format="dd/MM/yyyy" type="date"
                                            width="8%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />                            
                        <sd:ColumnDataGrid  key="Trạng thái" get="page.getIndex" formatter="page.formatStatus" cellStyles="text-align:center;"
                                            width="10%"  headerStyles="text-align:center;" />
                    </sd:DataGrid>
                </div>
            </td>
        </tr>
    </table>
<script type="text/javascript">    
    page.searchLstFilesChangeAfterAnnouned = function () {
        dijit.byId("filesGrid_Changes").vtReload('filesAction!onsearchListFilesChangesAfterAnnouned.do?', null);
    };
                                    page.searchLstFilesChangeAfterAnnouned();
</script>
