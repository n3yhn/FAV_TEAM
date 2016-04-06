<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../../util/util_js.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<script type="text/javascript">

    page.formatViewNode = function(inData){
        var item= dijit.byId("nodeGrid").getItem(inData-1);
        var row= inData-1;
        var url="";
        if (item!=null) {
            var url = "<a href='#'><img src='${contextPath}/share/images/record_view.png' width='17px' height='17px'\
                                        title='<sd:Property>Chọn chuyên viên thẩm định hồ sơ</sd:Property>'\
                                        onClick='page.onSelectNode("+row+");' /></a>";
        }
        return url;
        
    }
    
    page.getNo = function(index) {
        return dijit.byId("nodeGrid").currentRow + index + 1;
    }

    page.getIndex = function(index) {
        return index + 1;
    }
                  
</script>

<sd:TitlePane key="Danh sách chuyên viên thẩm định"
              id="ListNodePanel" >
    <div style="width:100%;">
        <sd:DataGrid id="nodeGrid"
                     getDataUrl=""
                     rowSelector="0%"
                     style="width:auto;height:auto"
                     rowsPerPage="20"
                     serverPaging="true"
                     clientSort="false">
            <sd:ColumnDataGrid key="category.No" get="page.getNo" width="5%"  styles="text-align:center;" />
            <sd:ColumnDataGrid key="Chọn chuyên viên thẩm định" formatter="page.formatViewNode" get="page.getIndex"
                               width="20%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
            <sd:ColumnDataGrid  key="Chuyên viên xử lý" field="nodeName"
                                width="70%"  headerStyles="text-align:center;" />
        </sd:DataGrid>
    </div>
</sd:TitlePane>

<script type="text/javascript">
    
    var workingFileId;
    page.afterLoadNode = function(data,bSuccess){
        //var returnData=dojo.fromJson(data);
        var result=data.items;
        //
        // neu co nhieu node thi cho nguoi ta chon
        //
        if(result != null && result.length > 1){
        } else {
            //
            // tu dong chuyen sang node ke tiep
            //
            //sd.connector.post("flow!moveDocumentToNextNode.do?fileId="+workingFileId +"&"+token.getTokenParamString(),null,null,null,page.afterMoveToNextNode);
        }
    }
    
    page.loadFlow = function(fileId){
        workingFileId = fileId;
        var dlg = dijit.byId("nodeListDlg");
        dlg.show();
        //dijit.byId("nodeGrid").renderNoReload();
        dijit.byId("nodeGrid").vtReload("flow!loadNextNodeOfDoc.do?fileId="+fileId,null,null, page.afterLoadNode);
    }
    
    page.afterMoveToNextNode = function(data){
        var dlg = dijit.byId("nodeListDlg");
        dlg.hide();
        page.search();
    }
    
    page.onSelectNode = function(row){
        var item =dijit.byId("nodeGrid").getItem(row);
        sd.connector.post("flow!moveDocumentToNextNode.do?fileId="+workingFileId+"&nodeId="+item.nodeId +"&"+token.getTokenParamString(),null,null,null,page.afterMoveToNextNode);
        //        var dlg = dijit.byId("nodeListDlg");
        //        dlg.hide();
    }
</script>
