<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<script type="text/javascript">

    page.formatViewFlow = function(inData) {
        var item = dijit.byId("flowGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var url = "<a href='#'><img src='${contextPath}/share/images/record_view.png' width='17px' height='17px'\
                                        title='Chọn'\
                                        onClick='page.onSelectFlow(" + item.flowId + ");' /></a>";
        }
        return url;

    };

    page.getNo = function(index) {
        return dijit.byId("flowGrid").currentRow + index + 1;
    };

    page.getIndex = function(index) {
        return index + 1;
    };

</script>

<sd:TitlePane key="Danh sách luồng xử lý"
              id="List" >
    <div style="width:100%;">
        <sd:DataGrid id="flowGrid"
                     getDataUrl=""
                     rowSelector="0%"
                     style="width:auto;height:auto"
                     rowsPerPage="20"
                     serverPaging="true"
                     clientSort="false">
            <sd:ColumnDataGrid key="category.No" get="page.getNo" width="5%"  styles="text-align:center;" />
            <sd:ColumnDataGrid key="Chọn" formatter="page.formatViewFlow" get="page.getIndex"
                               width="10%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
            <sd:ColumnDataGrid  key="Quy trình xử lý" field="flowName"
                                width="45%"  headerStyles="text-align:center;" />
            <sd:ColumnDataGrid  key="Đơn vị" field="deptName"
                                width="40%"  headerStyles="text-align:center;" />
        </sd:DataGrid>
    </div>
</sd:TitlePane>

<script type="text/javascript">

    var workingFileId;
    var dlg = dijit.byId("selectDeptDlg");
    page.afterMoveToNextNode = function(data) {
        var returnData = dojo.fromJson(data);
        var result = returnData.items;
        msg.alert(result[1]);
        page.search();
    }

    page.afterLoadNode = function(data, bSuccess) {
        //var returnData=dojo.fromJson(data);

        var result = data.items;
        //
        // neu co nhieu node thi cho nguoi ta chon
        //
        if (result != null && result.length > 1) {
            //
            // Cho de nguoi dung chon luong de xu ly thoi
            //
        } else if (result.length == 1) {
            //
            // tu dong chuyen vao luong
            //
            page.onSelectFlow(result[0].flowId);
        } else if (result.length == 0) {
            sd.connector.post("filesAction!onSelectFlow.do?createForm.fileId=" + workingFileId + "&createForm.flowId=" + flowId + "&" + token.getTokenParamString(), null, null, null, page.afterMoveToNextNode);
        }
    }

    page.loadFlow = function(fileId) {
        workingFileId = fileId;
        dlg.show();
        dijit.byId("flowGrid").vtReload("filesAction!getListOfAgency.do?createForm.fileId=" + workingFileId, null, null, page.afterLoadNode);
    };

    page.onSelectFlow = function(flowId) {
        dlg.hide();
        sd.connector.post("filesAction!onSelectFlow.do?createForm.fileId=" + workingFileId + "&createForm.flowId=" + flowId + "&" + token.getTokenParamString(), null, null, null, page.afterMoveToNextNode);
    };

</script>
