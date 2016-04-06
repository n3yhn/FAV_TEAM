<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../util/util_js.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<div id="token">
    <s:token id="tokenId"/>
</div>
<div id ="searchDiv" style="display:none">

    <sd:TitlePane key="search.searchCondition" id="categoryTitle">
        <form id="categorySearchForm">
            <sd:TextBox cssStyle="width:100%;display:none"
                        id="categorySearchForm.type"
                        key=""
                        name="categorySearchForm.type"
                        value="${fn:escapeXml(type)}">
            </sd:TextBox>
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
                                    id="categorySearchForm.code"
                                    key=""
                                    name="categorySearchForm.code" maxlength="20">
                        </sd:TextBox>
                    </td>
                    <td align="right">
                        <sd:Label key="category.name"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="categorySearchForm.name"
                                    key=""
                                    name="categorySearchForm.name" maxlength="255">
                        </sd:TextBox>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="category.isActive"/>
                    </td>
                    <td>
                        <sd:SelectBox id="categorySearchForm.isActive" name="categorySearchForm.isActive" key="" cssStyle="width:100%">
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
                            <img src="${contextPath}/share/images/icons/reset.png" height="14" width="18">
                            <span style="font-size:12px">Hủy<%--<sd:Property>btnCancel</sd:Property> --%></span>
                        </sd:Button>
                    </td>
                </tr>

            </table>
        </form>
    </sd:TitlePane>
</div>        

<sd:TitlePane key="${fn:escapeXml(categoryName)}"
              id="categoryList" >
    <sx:ResultMessage id="categoryResultDeleteMessage" />
    <table width="100%">
        <tr>
            <td>
                <script>
                    page.getNo = function(index) {
                        return dijit.byId("categoryGrid").currentRow + index + 1;
                    };
                    page.getIndex = function(index) {
                        return index + 1;
                    };
                    page.formatCategory = function(inData) {
                        var item = dijit.byId("categoryGrid").getItem(inData - 1);
                        var url = "";
                        if (item != null) {
                            var categoryId = item.categoryId;
                            if (item.createdBy == ${userId}) {
                                url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                                        title='<sd:Property>category.edit</sd:Property>' \n\
                                        onClick='page.showEditPopup(" + categoryId + ");' /></div>";
                            }


                        }
                        return url;
                    };
                    page.onDisable = function(idx) {
                        var item = dijit.byId('categoryGrid').getItem(idx);
                        if (((item.type == 'LHPC' || item.type == 'CQ' || item.type == 'DP') && page.checkForNotEditProfile(item.code, item.name))) {
                            return true;
                        }
                        else {
                            return false;
                        }
                        return false;
                    };
                    page.formatStatus = function(inData) {
                        var item = dijit.byId("categoryGrid").getItem(inData - 1);
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
                    page.disabledCheckbox = function(inData) {

                        var item = dijit.byId("categoryGrid").getItem(inData);
                        var check = true;
                        if (item != null) {
                            if (item.createdBy == ${fn:escapeXml(userId)}) {
                                check = false;
                            } else {
                                check = true;
                            }
                        }
                        return check;
                    }
                </script>
                <div id="" style="width:100%;">
                    <sd:DataGrid id="categoryGrid"
                                 getDataUrl=""
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="category.No" get="page.getNo" width="3%"  styles="text-align:center;" />
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;" setDisabled="page.disabledCheckbox"/>
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatCategory" get="page.getIndex"
                                           width="5%"  headerStyles="text-align:center;"   >
                        </sd:ColumnDataGrid>
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
    <jsp:include page="categoryAddEdit.jsp" flush="false"></jsp:include>
</sd:Dialog>

<sd:Dialog  id="dlgCategoryTypeSp" height="auto" width="400px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="categoryAddEditSp.jsp" flush="false"></jsp:include>
</sd:Dialog>

<script>
    var type = "${fn:escapeXml(type)}";
    var grid = dijit.byId("categoryGrid");
    var dlgCategory = dijit.byId("dlgCategory");
    var dlgCategoryTypeSp = dijit.byId("dlgCategoryTypeSp");
    var insertDialog = false;
    page.search = function() {
        grid.vtReload('category!search.do?type=${fn:escapeXml(type)}', "categorySearchForm", null, null);
    };
    page.reset = function() {
        dijit.byId("categorySearchForm.code").setValue("");
        dijit.byId("categorySearchForm.name").setValue("");
        dijit.byId("categorySearchForm.isActive").setValue("-1");
        grid.vtReload('category!search.do?type=${fn:escapeXml(type)}', "categorySearchForm", null, null);
    };
    page.insert = function() {
        if (type != "SP")
        {
            dijit.byId("categoryAddEditForm.categoryId").setValue("");
            dijit.byId("categoryAddEditForm.code").setValue("");
            dijit.byId("categoryAddEditForm.name").setValue("");
            dijit.byId("categoryAddEditForm.description").setValue("");
            dijit.byId("categoryAddEditForm.isActive").setValue("1");
            insertDialog = true;
            dlgCategory.show();
        } else
        {

            dijit.byId("categoryAddEditFormSp.categoryId").setValue("");
            dijit.byId("categoryAddEditFormSp.code").setValue("-- Chọn --");
            dijit.byId("categoryAddEditFormSp.name").setValue("");
            dijit.byId("categoryAddEditFormSp.description").setValue("");
            dijit.byId("categoryAddEditFormSp.isActive").setValue("1");
            insertDialog = true;
            dlgCategoryTypeSp.show();
        }
    };
    page.showEditPopup = function(categoryId) {
        sd.connector.post("category!showEditPopup.do?categoryId=" + categoryId, null, null, null, page.afterShowEditPopup);
    };
    page.afterShowEditPopup = function(data) {
        var obj = dojo.fromJson(data);
        var customInfo = obj.customInfo;

        if (type != "SP") {
            if (customInfo.categoryId != null) {
                dijit.byId("categoryAddEditForm.categoryId").setValue(customInfo.categoryId);
            }

            if (customInfo.code != null) {
                dijit.byId("categoryAddEditForm.code").setValue(customInfo.code);
            }
            if (customInfo.name != null) {
                dijit.byId("categoryAddEditForm.name").setValue(customInfo.name);
            }
            if (customInfo.description != null) {
                dijit.byId("categoryAddEditForm.description").setValue(customInfo.description);
            }
            if (customInfo.isActive != null) {
                dijit.byId("categoryAddEditForm.isActive").setValue(customInfo.isActive);
            }
            insertDialog = false;
            dlgCategory.show();
        }else
        {
             if (customInfo.categoryId != null) {
                dijit.byId("categoryAddEditFormSp.categoryId").setValue(customInfo.categoryId);
            }

            if (customInfo.code != null) {
                dijit.byId("categoryAddEditFormSp.code").setValue(customInfo.code);
            }
            if (customInfo.name != null) {
                dijit.byId("categoryAddEditFormSp.name").setValue(customInfo.name);
            }
            if (customInfo.description != null) {
                dijit.byId("categoryAddEditFormSp.description").setValue(customInfo.description);
            }
            if (customInfo.isActive != null) {
                dijit.byId("categoryAddEditFormSp.isActive").setValue(customInfo.isActive);
            }
            insertDialog = false;
            dlgCategoryTypeSp.show();
        }
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
                                sd.connector.post("category!gridDeleteData.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
                            };
                            page.returnMessageDelete = function(data) {
                                var obj = dojo.fromJson(data);
                                var result = obj.items;
                                resultMessage_show("categoryResultDeleteMessage", result[0], result[1], 5000);
                                grid.vtReload('category!search.do?type=${fn:escapeXml(type)}', "categorySearchForm", null, null);
                            };
                            page.showSearchPanel = function() {
                                var panel = document.getElementById("searchDiv");
                                panel.setAttribute("style", "display:;");
                                dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
                            };
                            page.search();

</script>
