<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../util/util_js.jsp"/>
<jsp:include page="../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<script type="text/javascript">
    page.formatUpdate = function(inData) {
        var item = dijit.byId("flowGrid").getItem(inData - 1);
        var row = inData - 1;
        var url = "";
        if (item != null) {
            var flowId = item.flowId;
            var url = "<a href='#'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px'\
                                        title='<sd:Property>category.edit</sd:Property>'\
                                        onClick='page.showEditPopup(" + row + ");' /></a>";
        }
        return url;
    };

    page.formatView = function(inData) {
        var item = dijit.byId("flowGrid").getItem(inData - 1);
        var row = inData - 1;
        var url = "";
        if (item != null) {
            var url = "<a href='#'><img src='${contextPath}/share/images/record_view.png' width='17px' height='17px'\
                                        title='<sd:Property>category.edit</sd:Property>'\
                                        onClick='page.showViewPopup(" + row + ");' /></a>";
        }
        return url;

    };

    page.getNo = function(index) {
        return dijit.byId("flowGrid").currentRow + index + 1;
    };

    page.getIndex = function(index) {
        return index + 1;
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

    dojo.connect(dojo.byId("searchFlowForm"), "onkeypress", page.searchDefault);
    
    </script>

    <div id="token">
    <s:token id="tokenId"/>
</div>
<div id="flowListDivId">
    <div id ="searchDiv" style="display:none">

        <sd:TitlePane key="search.searchCondition" id="categoryTitle">
            <form id="searchFlowForm" name="searchFlowForm">
                <table width="100%;" cellpadding="0px" cellspacing="5px">
                    <tr>
                        <td width="20%"></td>
                        <td width="30%"></td>
                        <td width="20%"></td>
                        <td width="30%"></td>
                    </tr>
                    <tr>
                        <td align="right">
                            <sd:Label key="Tên luồng"/>
                        </td>
                        <td>
                            <sd:TextBox cssStyle="width:100%" trim="true"
                                        id="searchFlowForm.flowName"
                                        key=""
                                        name="searchFlowForm.flowName">
                            </sd:TextBox>
                        </td>
                        <td align="right">
                            <sd:Label key="Đơn vị"/>
                        </td>
                        <td>
                            <sd:TreePicker name="deptFlowTreeForSearch" id="deptFlowTreeForSearch" getChildrenNodeUrl="departmentAction!getDeptChildrenDataByNode.do"
                                           getTopNodeUrl="departmentAction!getMyDeptRootTree.do"  key="" rootLabel="root" cssStyle="width:100%"/>
                            <sd:TextBox cssStyle="display:none;" id="searchFlowForm.deptId" name="searchFlowForm.deptId" key=""/>
                            <sd:TextBox cssStyle="display:none;" id="searchFlowForm.deptName" name="searchFlowForm.deptName" key=""/>
                        </td>
                    </tr>
                    <tr style="text-align: center">
                        <td colspan="4">
                            <sx:ButtonSearch onclick="page.search();" />
                            <sd:Button key="" onclick="page.reset();" > 
                                <img src="share/images/icons/reset.png" height="14" width="18"/>
                                <span style="font-size:12px">Hủy<%--<sd:Property>btnCancel</sd:Property> --%></span>
                            </sd:Button>
                        </td>
                    </tr>

                </table>
            </form>
        </sd:TitlePane>
    </div>        

    <sd:TitlePane key="Danh sách luồng xử lý"
                  id="List" >
        <sx:ResultMessage id="resultMessage" />
        <table width="100%">
            <tr>
                <td>
                    <div  style="width:100%;">
                        <sd:DataGrid id="flowGrid"
                                     getDataUrl=""
                                     rowSelector="0%"
                                     style="width:auto;height:auto"
                                     rowsPerPage="20"
                                     serverPaging="true"
                                     clientSort="false">
                            <sd:ColumnDataGrid key="category.No" get="page.getNo" width="3%"  styles="text-align:center;"/>
                            <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid key="Sửa" formatter="page.formatUpdate" get="page.getIndex"
                                               width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                            <sd:ColumnDataGrid key="Xem" formatter="page.formatView" get="page.getIndex"
                                               width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                            <sd:ColumnDataGrid  key="Quy trình xử lý" field="flowName"
                                                width="20%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Đơn vị" field="deptName"
                                                width="50%"  headerStyles="text-align:center;" />
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <sx:ButtonAddCategory onclick="page.insert();"/>
                    <sx:ButtonDelete onclick="page.deleteItem()" />
                    <sx:ButtonSearch id="btnShowSearchPanel" onclick="page.showSearchPanel();" />
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>

<div id="flowViewDivId" style="display:none">
    <jsp:include page="flowView.jsp" flush="false"/>
</div>
<sd:Dialog  id="dlgCreateFlow" height="auto" width="400px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="flowAddAndEdit.jsp" flush="false"/>
</sd:Dialog>
<sd:Dialog  id="dlgViewNode" height="auto" width="900px"
            key="Node" showFullscreenButton="false"
            >
    <div id="divViewNode">
        <jsp:include page="nodeView.jsp" flush="false"/>
    </div>
</sd:Dialog>

<script type="text/javascript">

    var gridFlow = dijit.byId("flowGrid");
    var dlgCategory = dijit.byId("dlgCreateFlow");
    var insertDialog = false;
    page.search = function() {
        gridFlow.vtReload('flow!searchFlow.do', "searchFlowForm", null, null);
    };

    page.reset = function() {
        
        dijit.byId("searchFlowForm.flowName").setValue("");
        sd._("deptFlowTreeForSearch").setValue("");
        dijit.byId("searchFlowForm.deptId").setValue("");
        dijit.byId("searchFlowForm.deptName").setValue("");
        
        page.search();
    };

    page.showViewPopup = function(row) {
        var item = gridFlow.getItem(row);
        document.getElementById("viewForm.flowId").value = item.flowId;
        document.getElementById("viewForm.flowName").innerHTML = item.flowName;
        document.getElementById("viewForm.flowTypeName").innerHTML = item.flowTypeName;
        document.getElementById("viewForm.deptName").innerHTML = item.deptName;
        //dijit.byId("viewForm.flowType").setValue(item.flowType);
        if (item.description != null)
            document.getElementById("viewForm.description").innerHTML = item.description.toString();

        document.getElementById("flowListDivId").style.display = "none";
        document.getElementById("flowViewDivId").style.display = "";

        //        var dlg = dijit.byId("dlgViewFlow");
        //        dlg.show();
        drawProcess("flowNode");
    };

    page.insert = function() {
        dijit.byId("createForm.flowId").setValue("");
        dijit.byId("createForm.flowName").setValue("");
        dijit.byId("createForm.deptId").setValue("");
        dijit.byId("createForm.deptName").setValue("");
        dijit.byId("createForm.description").setValue("");
        dijit.byId("deptFlowTree").setValue("");
        //var dlg=dijit.byId("dlgCreateFlow");
        insertDialog = true;
        dlgCategory.show();
    };

    page.showEditPopup = function(row) {
        insertDialog = false;
        var item = gridFlow.getItem(row);
        dijit.byId("createForm.flowId").setValue(item.flowId);
        dijit.byId("createForm.flowName").setValue(item.flowName);
        dijit.byId("createForm.deptId").setValue(item.deptId);
        dijit.byId("createForm.deptName").setValue(item.deptName);
        dijit.byId("createForm.flowType").setValue(item.flowType);
        dijit.byId("createForm.flowTypeName").setValue(item.flowTypeName);
        if (item.description != null)
            dijit.byId("createForm.description").setValue(item.description.toString());
        sd._("deptFlowTree").setValue(item.deptName);
        dlgCategory.show();
        //sd.connector.post("category!showEditPopup.do?categoryId=" + categoryId, null, null, null, page.afterShowEditPopup);
    };

    page.deleteItem = function() {
        if (!gridFlow.vtIsChecked()) {
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
        }
        else {
            msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
            //                
            //                var content = grid.vtGetCheckedDataForPost("lstItemOnGrid");
            //                sd.connector.post("category!gridDeleteData.do", null, null, content, page.returnMessageDelete);
        }
    };

    page.deleteItemExecute = function() {
        var content = gridFlow.vtGetCheckedDataForPost("lstItemOnGrid");
        sd.connector.post("flow!deleteFlow.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
    };

    page.returnMessageDelete = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        page.search();
    };

    page.showSearchPanel = function() {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };

    dijit.byId("deptFlowTreeForSearch").onPickData = function(item) {
        try {
            if (item.id) {
                sd._("searchFlowForm.deptId").setValue(item.id);
                sd._("searchFlowForm.deptName").setValue(item.name);
            } else {
                sd._("searchFlowForm.deptId").setValue("");
                sd._("searchFlowForm.deptName").setValue("");
                sd._("deptFlowTreeForSearch").setValue("");
            }
        } catch (err) {
            alert(err.message);
        }
    };
    try {
        sd.widget.__setReadOnly("deptFlowTreeForSearch", true);
    } catch (e) {

    };
    page.search();

</script>
