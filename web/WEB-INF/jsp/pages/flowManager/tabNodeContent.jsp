<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<script type="text/javascript">
    page.formatDeleteNodeToNode = function(inData) {
        var item = dijit.byId("nodeToNodeGrid").getItem(inData - 1);
        var row = inData - 1;
        var url = "";
        if (item != null) {
            var url = "<a href='#'><img src='${contextPath}/share/images/delete.png' width='12px' height='12px'\
                                        title='<sd:Property>category.edit</sd:Property>'\
                                        onClick='page.deleteNodeToNode(" + row + ");' /></a>";
        }
        return url;
    }
    </script>
<sd:TitlePane key="Thông tin Node" id="nodeViewPanel">
    <form id="viewNodeForm" name="viewNodeForm">
        <table class="viewTable" width="100%">
            <tr>
                <td>Tên node</td>
                <td>
                    <input type="hidden" id="viewNodeForm.nodeId" name="viewNodeForm.nodeId"/>
                    <input type="hidden" id="viewNodeForm.id" name="viewNodeForm.id"/>
                    <input type="hidden" id="viewNodeForm.flowId" name="viewNodeForm.flowId"/>
                    <input type="hidden" id="viewNodeForm.x" name="viewNodeForm.x"/>
                    <input type="hidden" id="viewNodeForm.y" name="viewNodeForm.y"/>
                    <input type="text" id="viewNodeForm.nodeName" name="viewNodeForm.nodeName"/>
                </td>
                <td>Kiểu node</td>
                <td>
                    <select id="viewNodeForm.status" name="viewNodeForm.status" style="width:100%">
                        <option value="-1">-- Chọn --</option>
                        <option value="1">Node bắt đầu</option>
                        <option value="2">Node xử lý</option>
                        <option value="3">Node kết thúc</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="center">
                    <sx:ButtonSave onclick="page.saveNodeContent();"/>
                    <sx:ButtonDelete onclick="page.deleteNode()" />
                    <sx:ButtonClose onclick="page.closeViewNodeDlg()" />
                </td>
            </tr>
        </table>
    </form>

</sd:TitlePane>
<sd:TitlePane key="Danh sách các node kế tiếp" id="nodeToNodePanel">
    <div style="width:100%;">
        <sd:DataGrid id="nodeToNodeGrid"
                     getDataUrl=""
                     rowSelector="0%"
                     style="width:auto;height:auto"
                     rowsPerPage="20"
                     serverPaging="true"
                     clientSort="false">
            <sd:ColumnDataGrid key="category.No" get="page.getNo" width="5%"  styles="text-align:center;" />
            <sd:ColumnDataGrid key="Xóa" formatter="page.formatDeleteNodeToNode" get="page.getIndex"
                               width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
            <sd:ColumnDataGrid  key="Node Kế tiếp" field="nextNodeName"
                                width="45%"  headerStyles="text-align:center;" />
            <sd:ColumnDataGrid  key="Action" field="action" editable="true"
                                width="45%"  headerStyles="text-align:center;" />
        </sd:DataGrid>
    </div>
</sd:TitlePane>
<script type="text/javascript">
    page.closeViewNodeDlg = function() {
        var dlg = dijit.byId("dlgViewNode");
        dlg.hide();
    };

    page.deleteNode = function() {
        var id = document.getElementById("viewNodeForm.id").value;
        graph.deleteNode(id);
        page.closeViewNodeDlg();
    };

    page.deleteNodeToNode = function(row) {
        var grid = dijit.byId("nodeToNodeGrid");
        var item = grid.getItem(row);
        grid.store.deleteItem(item);
        grid.renderNoReload();
    };

    page.afterUpdateNodeContent = function(data) {
        var returnData = dojo.fromJson(data);
        var result = returnData.items;
        document.getElementById("viewNodeForm.nodeId").value = result[0];
        graph.updateFromForm();
        page.closeViewNodeDlg();

    };

    page.saveNodeContent = function() {
        if (document.getElementById("viewNodeForm.status").value <= 0) {
            alert("Chưa chọn trạng thái trên luồng");
            document.getElementById("viewNodeForm.status").focus();
            return;
        }
        sd.connector.post("flow!updateNode.do?" + token.getTokenParamString(), null, "viewNodeForm", null, page.afterUpdateNodeContent);
    };

</script>