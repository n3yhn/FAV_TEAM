<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../../util/util_js.jsp"/>
<jsp:include page="../../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="token">
    <s:token id="tokenId"/>
</div>
<div id ="searchDiv" style="display:none">

    <sd:TitlePane key="search.searchCondition" id="categoryTypeTitle">
        <form id="searchForm" name="searchForm">
            <table width="100%;" cellpadding="0px" cellspacing="5px">
                <tr>
                    <td width="20%"></td>
                    <td width="30%"></td>
                    <td width="20%"></td>
                    <td width="30%"></td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Loại danh mục:"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.categoryType"
                                    key=""
                                    name="searchForm.categoryType" maxlength="20"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Tên loại danh mục:"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.categoryName"
                                    key=""
                                    name="searchForm.categoryName" maxlength="255"/>
                    </td>
                </tr>
                <tr style="text-align: center">
                    <td colspan="4">
                        <sx:ButtonSearch onclick="page.search();" />
                        <sd:Button key="" onclick="page.reset();" > 
                            <img src="${contextPath}/share/images/icons/reset.png" height="14" width="18">
                            <span style="font-size:12px">Hủy<%--<sd:Property>btnCancel</sd:Property> --%></span>
                        </sd:Button>
                    </td>
                </tr>

            </table>
        </form>
    </sd:TitlePane>
</div>        

<sd:TitlePane key="Loại danh mục động"
              id="categoryTypeList" >
    <sx:ResultMessage id="resultDeleteMessage" />
    <table width="100%">
        <tr>
            <td>
                <script>
                    page.getNo = function(index) {
                        return dijit.byId("categoryTypeGrid").currentRow + index + 1;
                    }

                    page.getIndex = function(index) {
                        return index + 1;
                    }

                    page.formatEdit = function(inData) {
                        var row = inData - 1;
                        var item = dijit.byId("categoryTypeGrid").getItem(row);
                        var url = "";
                        if (item != null) {
                            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                        title='<sd:Property>category.edit</sd:Property>' \n\
                        onClick='page.showEditPopup(" + row + ");' /></div>";
                        }
                        return url;
                    }
                    </script>
                    <div id="" style="width:100%;">
                    <sd:DataGrid id="categoryTypeGrid"
                                 getDataUrl=""
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="STT" get="page.getNo" width="3%" styles="text-align:center;"/>
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatEdit" get="page.getIndex"
                                           width="5%"  headerStyles="text-align:center;"  />
                        <sd:ColumnDataGrid  key="Loại danh mục" field="categoryType"
                                            width="20%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Tên danh mục" field="categoryName"
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

<sd:Dialog  id="dlgCategoryType" height="auto" width="400px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="categoryTypeCreateDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>

<script>
    var grid = dijit.byId("categoryTypeGrid");
    var dlgCategoryType = dijit.byId("dlgCategoryType");
    var isInsertDialog = false;

    page.showEditPopup = function(row) {
        var item = dijit.byId("categoryTypeGrid").getItem(row);
        page.setItem(item);
        dlgCategoryType.show();
    }

    page.setItem = function(item) {
        dijit.byId("createForm.categoryTypeId").setValue(item.categoryTypeId);
        dijit.byId("createForm.categoryType").setValue(item.categoryType);
        dijit.byId("createForm.categoryName").setValue(item.categoryName);
        dijit.byId("createForm.desciption").setValue(item.desciption);
        dijit.byId("createForm.isActive").setValue(item.isActive);
    }

    page.search = function() {
        grid.vtReload('categoryTypeAction!onSearch.do?', "searchForm", null, null);
    }

    page.reset = function() {
        dijit.byId("searchForm.categoryType").setValue("")
        dijit.byId("searchForm.categoryName").setValue("")
        grid.vtReload('categoryTypeAction!onSearch.do?', "searchForm", null, null);
    }


    page.insert = function() {
        page.clearInsertForm();
        isInsertDialog = true;
        dlgCategoryType.show();
    }

    page.clearInsertForm = function() {
        dijit.byId("createForm.categoryTypeId").setValue("");
        dijit.byId("createForm.categoryType").setValue("");
        dijit.byId("createForm.categoryName").setValue("");
        dijit.byId("createForm.desciption").setValue("");
        dijit.byId("createForm.isActive").setValue(-1);
    }
    page.deleteItem = function() {
        if (!dijit.byId("categoryTypeGrid").vtIsChecked()) {
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
        }
        else {
            msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
        }
    }

    page.deleteItemExecute = function() {
        var content = grid.vtGetCheckedDataForPost("lstItemOnGrid");
        sd.connector.post("categoryTypeAction!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
    }

    page.returnMessageDelete = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
        page.search();
    }

    page.showSearchPanel = function() {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    }

    page.search();
</script>
