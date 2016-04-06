<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<jsp:include page="../util/util_js.jsp"/>
<jsp:include page="../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<div id="token">
    <s:token id="tokenId"/>
</div>
<div id ="searchDiv" style="display:none">
    <sd:TitlePane key="search.searchCondition" id="positionTitlePaneId">    
        <form id="areaSearchForm" name="areaSearchForm">
            <table width="100%;" cellpadding="0px" cellspacing="5px">
                <tr>
                    <td width="20%"></td>
                    <td width="30%"></td>
                    <td width="20%"></td>
                    <td width="30%"></td>
                </tr>

                <tr>                
                    <td align="right">
                        <sd:Label key="Mã lĩnh vực" cssStyle="100%"/>
                    </td>
                    <td>
                        <sd:TextBox id="areaSearchForm.abbreviate" name="areaSearchForm.abbreviate"
                                    key="" maxlength="20" cssStyle="width:100%">
                        </sd:TextBox>
                    </td>            

                    <td align="right">
                        <sd:Label key="Tên lĩnh vực" cssStyle="100%"/>
                    </td>
                    <td>
                        <sd:TextBox id="areaSearchForm.name" name="areaSearchForm.name"
                                    key="" maxlength="150" cssStyle="width:100%">
                        </sd:TextBox>
                    </td>                     
                </tr>

                <tr style="text-align: center">
                    <td colspan="4">
                        <sx:ButtonSearch onclick = "page.search();"></sx:ButtonSearch>
                        </td>
                    </tr>

                </table>
            </form>
    </sd:TitlePane>
</div>
<script>
    page.getNo = function(index) {
        return dijit.byId("areaGrid").currentRow + index + 1;
    };

    page.getIndex = function(index) {
        return index + 1;
    };

    page.formatArea = function(inData) {

        var item = dijit.byId("areaGrid").getItem(inData - 1);
        var url = "";
        if (item != null && item.areaId != null && item.areaId != "") {
            var areaId = item.areaId;
            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                                    title='<sd:Property>category.edit</sd:Property>' \n\
                                    onClick='page.showEditPopup(" + areaId + ");' /></div>";
        }
        return url;
    };

    </script>
<sd:TitlePane key="Danh mục lĩnh vực"
              id="" >
    <sx:ResultMessage id="positionDeleteMessage"/>
    <table width="100%">
        <tr>
            <td>
                <div id="" style="width:100%;">
                    <sd:DataGrid id="areaGrid"
                                 getDataUrl="voArea!onSearch.do"                                 
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="category.No" get="page.getNo" width="3%"  styles="text-align:center;" />
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;"/>
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatArea" get="page.getIndex"
                                           width="30px"  headerStyles="text-align:center;"/>
                        <sd:ColumnDataGrid key="Mã lĩnh vực" field="abbreviate" 
                                           width="20%"  headerStyles="text-align:center;"/>
                        <sd:ColumnDataGrid key="Tên lĩnh vực" field="name" 
                                           width="25%"  headerStyles="text-align:center;"/>
                        <sd:ColumnDataGrid key="Lĩnh vực cha" field="parentName"
                                           width="25%" headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                        <sd:ColumnDataGrid key="Thứ tự" field="orderNumber"
                                           width="10%" headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                        <sd:ColumnDataGrid key="positionAddEditForm.description" field="description" 
                                           width="20%"  headerStyles="text-align:center;"/>  
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

<sd:Dialog  id="dlgAddEditArea" height="auto" width="800px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="areaAddEdit.jsp" flush="false"></jsp:include> 
</sd:Dialog>

<script>

    var grid = dijit.byId("areaGrid");
    var dlgAddEditArea = dijit.byId("dlgAddEditArea");
    var insertDialog = false;

    page.search = function() {
        grid.vtReload('voArea!onSearch.do?', "areaSearchForm", null, null);
    };

    page.insert = function() {
        dijit.byId("areaAddEditForm.areaId").setValue("");
        dijit.byId("areaAddEditForm.abbreviate").setValue("");
        dijit.byId("areaAddEditForm.name").setValue("");
        dijit.byId("areaAddEditForm.description").setValue("");
        dijit.byId("areaAddEditForm.orderNumber").setValue("");
        dijit.byId("areaAddEditForm.parentId").setSelectedIndex(0);
        //dijit.byId("areaAddEditForm.isDisplay").attr("checked", "");
        insertDialog = true;
        dlgAddEditArea.show();
    };

    page.showEditPopup = function(areaId) {
        sd.connector.post("voArea!showEditPopup.do?areaId=" + areaId, null, null, null, page.afterShowEditPopup);
    };

    page.afterShowEditPopup = function(data) {
        var obj = dojo.fromJson(data);
        var customInfo = obj.customInfo;

        if (customInfo.areaId != null && customInfo.areaId != -1) {
            dijit.byId("areaAddEditForm.areaId").setValue(escapeHtml_(customInfo.areaId));
        }

        if (customInfo.abbreviate != null) {
            dijit.byId("areaAddEditForm.abbreviate").setValue(escapeHtml_(customInfo.abbreviate));
        }

        if (customInfo.name != null) {
            dijit.byId("areaAddEditForm.name").setValue(escapeHtml_(customInfo.name));
        }

        if (customInfo.description != null) {
            dijit.byId("areaAddEditForm.description").setValue(escapeHtml_(customInfo.description));
        }

        if (customInfo.orderNumber != null) {
            dijit.byId("areaAddEditForm.orderNumber").setValue(escapeHtml_(customInfo.orderNumber));
        }

        if (customInfo.parentId != null) {
            dijit.byId("areaAddEditForm.parentId").setValue(escapeHtml_(customInfo.parentId));
        }

        //if (customInfo.isDisplay != null && customInfo.isDisplay.toString() == "1") {
        //    dijit.byId("areaAddEditForm.isDisplay").attr("checked", "true");
        //}

        insertDialog = false;
        dlgAddEditArea.show();
    };

    page.deleteItem = function() {
        if (!grid.vtIsChecked()) {
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
                    }
                    else {
                        msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
                                }
                            };

                            page.deleteItemExecute = function() {
                                var content = grid.vtGetCheckedDataForPost("lstItemOnGrid");
                                sd.connector.post("voArea!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
                            };

                            page.returnMessageDelete = function(data) {
                                var obj = dojo.fromJson(data);
                                var result = obj.items;
                                resultMessage_show("positionDeleteMessage", result[0], result[1], 2000);
                                grid.vtReload('voArea!onSearch.do?', "areaSearchForm", null, null);
                            };

                            page.showSearchPanel = function() {
                                var panel = document.getElementById("searchDiv");
                                panel.setAttribute("style", "display:;");
                                dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
                            };
                            //    dijit.byId("positionTitlePaneId").attr("open", false);

</script>
