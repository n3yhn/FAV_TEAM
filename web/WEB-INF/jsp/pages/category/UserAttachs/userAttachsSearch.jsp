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

    <sd:TitlePane key="search.searchCondition" id="userAttachsTitle">
        <form id="searchForm" name="searchForm">
            <table width="100%">
                <tr>
                    <td width="10%"></td>
                    <td width="10%"></td>
                    <td width="35%"></td>
                    <td width="10%"></td>
                    <td width="35%"></td>
                </tr>
                <tr>
                    <td width="10%"></td>
                    <td align="right">
                        <sd:Label key="Tên hồ sơ"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" 
                                    id="searchForm.attachName"
                                    key=""
                                    name="searchForm.attachName" 
                                    maxlength="50" 
                                    trim="true"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Trạng thái"/>
                    </td>
                    <td>
                        <sd:SelectBox key="" id="searchForm.status" name="searchForm.status" cssStyle="width:100%">
                            <sd:Option value="-1">-- Chọn --</sd:Option>
                            <sd:Option value="1">Hoạt động</sd:Option>
                            <sd:Option value="0">Không hoạt động</sd:Option>
                        </sd:SelectBox>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Ngày hiệu lực"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Từ ngày"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.effectiveDateFrom"
                                       key=""
                                       name="searchForm.effectiveDateFrom" 
                                       format="dd/MM/yyyy"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.effectiveDateTo"
                                       key=""
                                       name="searchForm.effectiveDateTo" 
                                       format="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Ngày hết hạn"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Từ ngày"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.expireDateFrom"
                                       key=""
                                       name="searchForm.expireDateFrom" 
                                       format="dd/MM/yyyy"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchForm.expireDateTo"
                                       key=""
                                       name="searchForm.expireDateTo" 
                                       format="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr style="text-align: center">
                    <td></td>
                    <td colspan="4">
                        <sx:ButtonSearch onclick="page.search();" />
                        <sd:Button key="" onclick="page.reset();" > 
                            <img src="${contextPath}/share/images/icons/reset.png" height="14" width="18">
                            <span style="font-size:12px">Hủy</span>
                        </sd:Button>
                    </td>
                </tr>                
            </table>
        </form>
    </sd:TitlePane>
</div>        

<sd:TitlePane key="Quản lý hồ sơ pháp lý"
              id="userAttachsList" >
    <sx:ResultMessage id="resultDeleteMessage" />
    <table width="100%">
        <tr>
            <td>
                <script>
                    page.getNo = function(index) {
                        return dijit.byId("Grid").currentRow + index + 1;
                    };

                    page.getIndex = function(index) {
                        return index + 1;
                    };

                    page.formatEdit = function(inData) {
                        var row = inData - 1;
                        var item = dijit.byId("Grid").getItem(row);
                        var url = "";
                        if (item != null) {
                            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                        title='<sd:Property>category.edit</sd:Property>' \n\
                        onClick='page.showEditPopup(" + row + ");' /></div>";
                        }
                        return url;
                    };
                    page.formatStatus = function(inData) {
                        var item = dijit.byId("Grid").getItem(inData - 1);
                        var url = "";
                        if (item != null) {
                            var status = parseInt(item.status);
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
                        var item = dijit.byId("Grid").getItem(inData);
                        var check = true;
                        if (item != null) {
                            var status = parseInt(item.status);
                            switch (status) {
                                case 0:
                                    check = true;
                                    break;
                                case 1:
                                    check = false;
                                    break;
                                default:
                                    check = true;
                            }
                        }

                        return check;
                    };
                    page.formatLinkdownload = function(inData) {
                        var item = dijit.byId("Grid").getItem(inData - 1);
                        var url = "";
                        if (item != null) {
                            var attachId = parseInt(item.userAttachsId);
                            var name = item.fileName;
                            url = "<a href='uploadiframe!openFileUserAttach.do?attachId=" + attachId + "' >" + name + "</a>";
                        }
                        return url;
                    };
                    </script>
                    <div id="" style="width:100%;">
                    <sd:DataGrid id="Grid"
                                 getDataUrl=""
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="STT" get="page.getNo" width="2%"  styles="text-align:center;" />
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="1%" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatEdit" get="page.getIndex"
                                           width="1%"  headerStyles="text-align:center;"  />
                        <sd:ColumnDataGrid  key="Tên hồ sơ" field="attachName"
                                            width="10%"  headerStyles="text-align:center;" />                        
                        <sd:ColumnDataGrid  key="Ngày hiệu lực" field="effectiveDate"
                                            format="dd/MM/yyyy" type="date" width="3%" headerStyles="text-align:center;" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Ngày hết hạn" field="expireDate"
                                            format="dd/MM/yyyy" type="date" width="3%" headerStyles="text-align:center;" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Link download đính kèm" get="page.getNo" formatter="page.formatLinkdownload" cellStyles="text-align:left;"
                                            width="15%" headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Trạng thái" get="page.getNo" formatter="page.formatStatus" cellStyles="text-align:center;"
                                            width="5%" headerStyles="text-align:center;" />
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
<sd:Dialog  id="dlg" height="auto" width="800px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="userAttachsCreateDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>
<script>
    var grid = dijit.byId("Grid");
    var dlg = dijit.byId("dlg");

    page.showEditPopup = function(row) {
        var item = grid.getItem(row);
        page.setItem(item);
        document.getElementById('browsecreateForm.upload').style.display = "none";
        dlg.show();
    };

    page.convertStringToDate = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dgDate) {
        try {
            var dateStr = page.getString(dgDate);
            var temp = dateStr.split("-");
            return new Date(temp[1] + "/" + temp[2].split("T")[0] + "/" + temp[0]);
        } catch (e) {
            page.alert("Thông báo", "function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss", "warning");
            return undefined;
        }
    };

    page.setItem = function(item) {
        dijit.byId("createForm.userAttachsId").setValue(item.userAttachsId);
        dijit.byId("createForm.attachName").setValue(item.attachName);
        dijit.byId("createForm.description").setValue(item.description);

        if (item.effectiveDate != "") {
            dijit.byId("createForm.effectiveDate").setValue(page.convertStringToDate(item.effectiveDate));
        }

        if (item.expireDate != "") {
            dijit.byId("createForm.expireDate").setValue(page.convertStringToDate(item.expireDate));
        }
        dijit.byId("createForm.status").setValue(item.status);
        clearAttFile("createForm.upload");
        getAttacthFileUserAttachs(item.userAttachsId, 24, "createForm.upload");
    };

    page.search = function() {
        grid.vtReload('userAttachsAction!onSearch.do?', "searchForm", null, null);
    };

    page.reset = function() {
        dijit.byId("searchForm.publishAgencyName").setValue("");
        dijit.byId("searchForm.effectiveDateFrom").setValue("");
        dijit.byId("searchForm.effectiveDateTo").setValue("");
        dijit.byId("searchForm.expireDateFrom").setValue("");
        dijit.byId("searchForm.expireDateTo").setValue("");
        page.search();
    };


    page.insert = function() {
        page.clearInsertForm();
        document.getElementById('browsecreateForm.upload').style.display = "";
        dlg.show();
    };

    page.clearInsertForm = function() {
        dijit.byId("createForm.userAttachsId").setValue("");
        dijit.byId("createForm.attachName").setValue("");
        dijit.byId("createForm.description").setValue("");
        dijit.byId("createForm.effectiveDate").setValue("");
        dijit.byId("createForm.expireDate").setValue("");
        dijit.byId("createForm.status").setValue(1);
        clearAttFile("createForm.upload");
        setVisible('createForm.upload','');
    };
    //delete
    page.deleteItem = function() {
        if (!dijit.byId("Grid").vtIsChecked()) {
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
                    }
                    else {
                        msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
                                }
                            };
                            page.deleteItemExecute = function() {
                                var content = dijit.byId("Grid").vtGetCheckedDataForPost("lstItemOnGrid");
                                sd.connector.post("userAttachsAction!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
                            };
                            page.returnMessageDelete = function(data) {
                                var obj = dojo.fromJson(data);
                                var result = obj.items;
                                resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
                                page.search();
                            };
                            //!delete
                            page.showSearchPanel = function() {
                                var panel = document.getElementById("searchDiv");
                                panel.setAttribute("style", "display:;");
                                dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
                                dijit.byId("searchForm.standardCode").focus();
                            };
                            page.search();
</script>
