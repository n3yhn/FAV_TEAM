<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib  prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../../util/util_js.jsp"/>
<jsp:include page="../../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="token">
    <s:token id="tokenId"/>
</div>
<script>
    page.getNo = function(index) {
        return dijit.byId("NotiGrid").currentRow + index + 1;
    };
    page.getIndex = function(index) {
        return index + 1;
    };
    page.formatEdit = function(inData) {
        var row = inData - 1;
        var item = dijit.byId("NotiGrid").getItem(row);
        var url = "";
        if (item != null) {
            url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                        title='<sd:Property>category.edit</sd:Property>' \n\
                        onClick='page.showEditPopup(" + row + ");' /></div>";
        }
        return url;
    };
    page.formatStatus = function(inData) {
        var item = dijit.byId("NotiGrid").getItem(inData - 1);
        var url = "";

        return url;
    };
    page.formatIsActive = function(inData) {

        var item = dijit.byId("NotiGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var isDayOff = parseInt(item.isActive);
            switch (isDayOff) {
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
        var item = dijit.byId("NotiGrid").getItem(inData);
        var check = false;
        return check;
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

    dojo.connect(dojo.byId("createForm"), "onkeypress", page.searchDefault);

    </script>
    <div id ="searchDiv" style="display:none">

    <sd:TitlePane key="search.searchCondition" id="NotiTitle">
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
                        <sd:Label key="Nội dung"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.content"
                                    name="searchForm.content"
                                    key=""
                                    maxlength="250"


                                    />

                    </td>
                    <td align="right">
                        <sd:Label key="Trạng thái"/>
                    </td>
                    <td>
                        <sd:SelectBox id="searchForm.isActive" name="searchForm.isActive" key=""  cssStyle="width:100%" >
                            <sd:Option value='-1'>-- Chọn --</sd:Option>
                            <sd:Option selected="true" value='1'>Hoạt động</sd:Option>
                            <sd:Option value='0'>Không hoạt động</sd:Option>

                        </sd:SelectBox>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Thứ tự"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.sort"
                                    name="searchForm.sort"
                                    key=""
                                    maxlength="250"
                                    type="number"
                                    />
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

<sd:TitlePane key="Danh sách thông báo"
              id="NotiList" >
    <sx:ResultMessage id="resultDeleteMessage" />
    <table width="100%">
        <tr>
            <td>

                <div id="" style="width:100%;">
                    <sd:DataGrid id="NotiGrid"
                                 getDataUrl=""
                                 rowSelector="0%"
                                 style="width:auto;height:auto;"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="STT" get="page.getNo" width="2%" styles="text-align:center;"/>
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="1%" cellStyles="text-align:center;"  setDisabled="page.disabledCheckbox"/>
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatEdit" get="page.getIndex"
                                           width="1%"  headerStyles="text-align:center;"  />

                        <sd:ColumnDataGrid  key="Nội dung thông báo" field="content" 
                                            width="5%"  headerStyles="text-align:center;" styles="white-space:pre;"/>
                        <sd:ColumnDataGrid  key="Ngày tạo" field="createDate" format="dd/MM/yyyy" type="date"
                                            width="3%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Ngày cập nhật" field="modifiedDate" format="dd/MM/yyyy" type="date"
                                            width="3%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Thứ tự" field="sort" 
                                            width="5%"  headerStyles="text-align:center;" styles="text-align:center;"/>
                        <sd:ColumnDataGrid  key="Trạng Thái" get="page.getNo" formatter="page.formatIsActive" cellStyles="text-align:center;"
                                            width="3%"  headerStyles="text-align:center;" />
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

<sd:Dialog  id="dlgNoti" height="auto" width="auto"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="notificationCreateDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>

<script>
    var insertDialog = true;
    var grid = dijit.byId("NotiGrid");
    var dlgNoti = dijit.byId("dlgNoti");


    page.setItem = function(item) {
        insertDialog = false;
        //document.getElementById("createForm.content").innerHTML = escapeHtml_(item.content);
        document.getElementById("createForm.content").value = escapeHtml_(item.content);
        dijit.byId("createForm.sort").setValue(item.sort);
        dijit.byId("createForm.isActive").setValue(item.isActive);
        dijit.byId("createForm.noticeId").setValue(item.noticeId);
    };

    page.showEditPopup = function(row) {
        var item = dijit.byId("NotiGrid").getItem(row);
        page.setItem(item);
        dlgNoti.show();
    };

    page.search = function() {
        grid.vtReload('notificationAction!onSearch.do?', "searchForm", null, null);
    };
    page.reset = function() {
        document.getElementById("createForm.content").innerHTML = "";
        dijit.byId("createForm.sort").setValue("");
        dijit.byId("createForm.isActive").setValue(-1);
    };
    page.insert = function() {
        page.clearInsertForm();
        dlgNoti.show();
    };
    page.clearInsertForm = function() {
        insertDialog = true;
       // document.getElementById("createForm.content").innerHTML = "";
        document.getElementById("createForm.content").value = "";
        dijit.byId("createForm.sort").setValue("");
        dijit.byId("createForm.isActive").setValue(-1);
    };

    page.deleteItemExecute = function() {
        var items = dijit.byId("NotiGrid").vtGetCheckedItems();
        var lstObjectId = "";
        var check;
        if (items != null && items.length >= 0) {
            for (var i = 0; i < items.length; i++)
            {
                if (i != items.length - 1)
                {
                    lstObjectId += items[i].noticeId + ",";
                }
                else
                {
                    lstObjectId += items[i].noticeId;
                }
            }
        }
        sd.connector.post("notificationAction!onDelete.do?" + token.getTokenParamString() + "&lstObjectId=" + lstObjectId, null, null, null, page.returnMessageDelete);
    };
    page.returnMessageDelete = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
        page.search();
    };
    page.showSearchPanel = function() {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };
    page.search();
    page.deleteItem = function() {
        if (!dijit.byId("NotiGrid").vtIsChecked()) {
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
                    } else {
                        msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
                                }
                            };
</script>
