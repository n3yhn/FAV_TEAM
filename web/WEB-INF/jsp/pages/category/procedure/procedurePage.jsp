<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../../util/util_js.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<div id="token">
    <s:token id="tokenId"/>
</div>
<div id ="searchDiv" style="display:none">

    <sd:TitlePane key="search.searchCondition" id="categoryTitle">
        <form id="searchForm">
            <table width="100%;" cellpadding="0px" cellspacing="5px">
                <tr>
                    <td width="20%"></td>
                    <td width="30%"></td>
                    <td width="20%"></td>
                    <td width="30%"></td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="category.code"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.code"
                                    key=""
                                    name="searchForm.code" maxlength="20">
                        </sd:TextBox>
                    </td>
                    <td align="right">
                        <sd:Label key="category.name"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.name"
                                    key=""
                                    name="searchForm.name" maxlength="255">
                        </sd:TextBox>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="category.isActive"/>
                    </td>
                    <td>
                        <sd:SelectBox id="searchForm.isActive" name="searchForm.isActive" key="" cssStyle="width:100%">
                            <sd:Option value='-1' selected="true">-- Chọn --</sd:Option>
                            <sd:Option value='0'>Không hoạt động</sd:Option>
                            <sd:Option value='1'>Hoạt động</sd:Option>                            
                        </sd:SelectBox>
                    </td>
                    <td align="right">
                    </td>
                    <td>
                    </td>
                </tr>
                <tr style="text-align: center">
                    <td colspan="4">
                        <sx:ButtonSearch onclick="page.search();" />
                        <sd:Button key="" onclick="page.reset();" > 
                            <img src="share/images/icons/reset.png" height="14" width="18">
                            <span style="font-size:12px">Hủy<%--<sd:Property>btnCancel</sd:Property> --%></span>
                        </sd:Button>
                    </td>
                </tr>

            </table>
        </form>
    </sd:TitlePane>
</div>        

<sd:TitlePane key="Thủ tục hành chính"
              id="categoryList" >
    <sx:ResultMessage id="categoryResultDeleteMessage" />
    <table width="100%">
        <tr>
            <td>
                <script type="text/javascript">
                    page.getNo = function(index) {
                        return dijit.byId("procedureGrid").currentRow + index + 1;
                    };

                    page.getIndex = function(index) {
                        return index + 1;
                    };

                    page.formatCategory = function(inData) {
                        var row = inData - 1;
                        var item = dijit.byId("procedureGrid").getItem(inData - 1);
                        var url = "";
                        if (item != null) {
                            var categoryId = item.categoryId;
                            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                                        title='<sd:Property>category.edit</sd:Property>' \n\
                                        onClick='page.showEditPopup(" + row + ");' /></div>";
                        }
                        return url;
                    };

                    page.formatDept = function(inData) {
                        var row = inData - 1;
                        var item = dijit.byId("procedureGrid").getItem(row);
                        var url = "";
                        if (item != null) {
                            var categoryId = item.categoryId;
                            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/list.png' width='17px' height='17px' \n\
                                        title='<sd:Property>category.edit</sd:Property>' \n\
                                        onClick='page.showDepartment(" + row + ");' /></div>";
                        }
                        return url;
                    }

                    page.formatStatus = function(inData) {
                        var item = dijit.byId("procedureGrid").getItem(inData - 1);
                        var url = "";
                        if (item != null) {
                            var status = parseInt(item.isActive);
                            switch (status) {
                                case 0:
                                    url = "Không hoạt động";
                                    break;
                                case 1:
                                    url = "Hoạt động";
                                    break;
                                default:
                                    url = "Không hoạt động";
                            }
                        }

                        return url;
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

                    dojo.connect(dojo.byId("searchForm"), "onkeypress", page.searchDefault);
                    </script>
                    <div style="width:100%;">
                    <sd:DataGrid id="procedureGrid"
                                 getDataUrl=""
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="category.No" get="page.getNo" width="3%"  styles="text-align:center;" />
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatCategory" get="page.getIndex"
                                           width="5%"  headerStyles="text-align:center;"   />
                        <sd:ColumnDataGrid key="Đơn vị" formatter="page.formatDept" get="page.getIndex"
                                           width="5%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="category.code" field="code"
                                            width="20%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="category.name" field="name"
                                            width="30%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="category.description" field="description"
                                            width="40%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="category.isActive" get="page.getNo" formatter="page.formatStatus" cellStyles="text-align:center;"
                                            width="20%"  headerStyles="text-align:center;" />
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

<sd:Dialog  id="dlgCategory" height="auto" width="400px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="procedureAddAndEdit.jsp" flush="false"/>
</sd:Dialog>
<sd:Dialog  id="dlgDeptProcedure" height="auto" width="800px"
            key="Danh sách đơn vị xử lý hồ sơ" showFullscreenButton="false"
            >
    <jsp:include page="listDeptOfProcedure.jsp" flush="false"/>
</sd:Dialog>
<sd:Dialog  id="selectMultiDepDlg" height="auto" width="800px"
            key="Chọn cơ quan xử lý hồ sơ" showFullscreenButton="false"
            >
    <jsp:include page="selectMultiDept.jsp" flush="false"/>
</sd:Dialog>
<sd:Dialog  id="selectDepProcessingDlg" height="auto" width="800px"
            key="Chọn đơn vị xử lý hồ sơ" showFullscreenButton="false"
            >
    <jsp:include page="selectProcessingDept.jsp" flush="false"/>
</sd:Dialog>

<script>

    var grid = dijit.byId("procedureGrid");
    var dlgCategory = dijit.byId("dlgCategory");
    var insertDialog = false;
    var workingProcedureId;

    page.search = function() {
        grid.vtReload('procedureAction!onSearch.do', "searchForm", null, null);
    };

    page.reset = function() {
        dijit.byId("searchForm.code").setValue("");
        dijit.byId("searchForm.name").setValue("");
        dijit.byId("searchForm.isActive").setValue("-1");
        page.search();
    };

    page.insert = function() {
        dijit.byId("createForm.procedureId").setValue("");
        dijit.byId("createForm.code").setValue("");
        dijit.byId("createForm.name").setValue("");
        dijit.byId("createForm.description").setValue("");
        sd._("createForm.fileList").setValue("");
        dijit.byId("createForm.deadline").setValue("");
        dijit.byId("createForm.isActive").setValue("1");
        dijit.byId("createForm.fee").setValue("");
        dijit.byId("createForm.feeAnnouce").setValue("");
        insertDialog = true;
        dlgCategory.show();
    };

    page.showEditPopup = function(row) {
        var item = grid.getItem(row);
        dijit.byId("createForm.procedureId").setValue(item.procedureId);
        dijit.byId("createForm.code").setValue(item.code);
        dijit.byId("createForm.name").setValue(item.name);
        dijit.byId("createForm.description").setValue(item.description);
        sd._("createForm.fileList").setValue(item.fileList.toString());
        dijit.byId("createForm.deadline").setValue(item.deadline);
        dijit.byId("createForm.isActive").setValue(item.isActive);
        dijit.byId("createForm.fee").setValue(item.fee);
        dijit.byId("createForm.feeAnnouce").setValue(item.feeAnnouce);
        dlgCategory.show();
    };

    page.showDepartment = function(row) {
        var item = grid.getItem(row);
        workingProcedureId = item.procedureId;
        showProcedureDeptDlg(workingProcedureId);
    };

    page.afterShowEditPopup = function(data) {
        var obj = dojo.fromJson(data);
        var customInfo = obj.customInfo;

        if (customInfo.categoryId != null) {
            dijit.byId("createForm.categoryId").setValue(customInfo.categoryId);
        }

        if (customInfo.code != null) {
            dijit.byId("createForm.code").setValue(customInfo.code);
        }
        if (customInfo.name != null) {
            dijit.byId("createForm.name").setValue(customInfo.name);
        }
        if (customInfo.description != null) {
            sd._("createForm.description").setValue(customInfo.description);
        }
        if (customInfo.isActive != null) {
            dijit.byId("createForm.isActive").setValue(customInfo.isActive);
        }
        insertDialog = false;
        dlgCategory.show();
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
                                sd.connector.post("procedureAction!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
                            };

                            page.returnMessageDelete = function(data) {
                                var obj = dojo.fromJson(data);
                                var result = obj.items;
                                resultMessage_show("categoryResultDeleteMessage", result[0], result[1], 5000);
                                page.search();
                            };

                            page.showSearchPanel = function() {
                                var panel = document.getElementById("searchDiv");
                                panel.setAttribute("style", "display:;");
                                dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
                            };

                            page.search();

</script>
