<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--
<jsp:include page="../util/util_js.jsp"/>
<jsp:include page="../common/commonJavascript.jsp"/>
--%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<style type="text/css">
    .box:hover{
        color: #ffff00;
        cursor: pointer;
    }
</style>
<script>
    page.getNo = function (index) {
        return dijit.byId("filesGrid").currentRow + index + 1;
    };

    page.getIndex = function (index) {
        return index + 1;
    };

    //    page.formatView = function(inData) {
    //        var url = "<div class='box' onclick='page.viewFile(" + inData + ");' />" + inData + "</div>";
    //
    //        return url;
    //    }

    page.formatView = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var url = "<div style='text-align:center;cursor:pointer;'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + item.fileId + ");' /></div>";
        }
        return url;
    };

    page.formatEdit = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var fileId = item.fileId;
            url = "";
            if (item.flowId != null && item.flowId != "") {
                if (item.status == 1 || item.status == 2 || item.status == 14) {
                    url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/group.png' width='17px' height='17px' \n\
                                        title='Phân công hồ sơ' \n\
                                        onclick='page.showNextNode(" + fileId + ");' /></div>";
                }
            } else {
                url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/group.png' width='17px' height='17px' \n\
                                        title='Phân công' \n\
                                        onclick='page.showAssignPopup(" + fileId + ");' /></div>";
            }
        }
        return url;
    };



    page.formatStatus = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        if (item != null) {
            var status = item.status;
            if (status == 1)
                return 'Mới nộp';
            else if (status == 2)
                return 'Đề xuất';
            else if (status == 14)
                return 'Đã tiếp nhận hồ sơ';
            else {
                return 'Đã phân công';
            }
        }
        return '';
    };

    page.formatDate = function (date) {
        if (date != null)
            return;
    };

    // enter key
    page.searchDefault = function (evt) {
        var dk = dojo.keys;
        switch (evt.keyCode) {
            case dk.ENTER:
                page.search();
                break;
        }
    };

    dojo.connect(dojo.byId("searchForm"), "onkeypress", page.searchDefault);

</script>

<div id="token">
    <s:token id="tokenId"/>
</div>
<div id="searchDiv">
    <sd:TitlePane key="search.searchCondition" id="categoryTitle">
        <form id="searchForm" name="searchForm">
            <sd:TextBox key="" id="searchForm.searchTypeNew" name="searchForm.searchTypeNew" cssStyle="display:none" />
            <s:hidden id="deptId" name="searchForm.deptId"/>
            <table width="100%;" cellpadding="0px" cellspacing="5px">
                <tr>
                    <td width="20%"></td>
                    <td width="30%"></td>
                    <td width="20%"></td>
                    <td width="30%"></td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Mã hồ sơ"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.fileCode"
                                    key=""
                                    name="searchForm.fileCode" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Tên loại hồ sơ"/>
                    </td>
                    <td>
                        <sd:SelectBox cssStyle="width:100%"
                                      id="searchForm.fileType"
                                      key="" data="lstFileType" valueField="procedureId" labelField="name"
                                      name="searchForm.fileType" >
                        </sd:SelectBox>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Tên doanh nghiệp"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.businessName"
                                    key=""
                                    name="searchForm.businessName" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Tên sản phẩm"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.productName"
                                    key=""
                                    name="searchForm.productName" maxlength="250"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Gửi Từ ngày"/>
                    </td>
                    <td>
                        <sd:DatePicker cssStyle="width:100%"
                                       id="searchForm.sendDateFrom"
                                       key="" format="dd/MM/yyyy"
                                       name="searchForm.sendDateFrom"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày"/>
                    </td>
                    <td>
                        <sd:DatePicker cssStyle="width:100%"
                                       id="searchForm.sendDateTo"
                                       key="" format="dd/MM/yyyy"
                                       name="searchForm.sendDateTo"/>
                        <sd:TextBox key="" id="searchForm.status" name="searchForm.status" cssStyle="display:none"/>
                    </td>
                </tr>

                <!-- hieptq 181114 nhom san pham -->
                <tr>
                    <td align="right"><sd:Label key="Nhóm thực phẩm"/></td>
                    <td>
                        <sd:SelectBox key="" id="searchForm.ProductTypeNew" name="searchForm.ProductTypeNew" cssStyle="width:100%">
                            <sd:Option value="-1" selected='true' >-- Chọn --</sd:Option>
                            <sd:Option value="1" >Nhóm thực phẩm thường</sd:Option>
                            <sd:Option value="2" >Các nhóm thực phẩm khác</sd:Option>
                        </sd:SelectBox>
                    </td>
                </tr>
                <tr style="text-align: center">
                    <td colspan="4">
                        <sx:ButtonSearch onclick="page.search();" />
                        <sd:Button key="" onclick="page.reset();" > 
                            <img src="share/images/icons/reset.png" height="14" width="18">
                            <span style="font-size:12px">Hủy<%--<sd:Property>btnCancel</sd:Property> --%></span>
                        </sd:Button>
                        <sd:TextBox cssStyle="display:none" trim="true"
                                    id="searchForm.flagSavePaging"
                                    name="searchForm.flagSavePaging"
                                    key=""
                                    value="1"
                                    />
                    </td>
                </tr>
            </table>
        </form>
    </sd:TitlePane>

    <sd:TitlePane key="Danh sách hồ sơ"
                  id="businessList" >    
        <table width="100%">
            <tr>
                <td>
                    <div style="width:100%;">
                        <sd:DataGrid id="filesGrid"
                                     getDataUrl=""
                                     rowSelector="0%"
                                     style="width:auto;height:auto"
                                     rowsPerPage="20"
                                     serverPaging="true"
                                     clientSort="false">
                            <sd:ColumnDataGrid key="category.No" get="page.getNo" width="3%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="3%" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="Xem" headerStyles="text-align:center;" width="3%" cellStyles="text-align:center;"
                                               formatter="page.formatView" get="page.getIndex"/>                        
                            <sd:ColumnDataGrid editable="true" key="Phân công" headerStyles="text-align:center;" width="3%" cellStyles="text-align:center;"
                                               formatter="page.formatEdit" get="page.getIndex"/>                        
                            <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode" width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Loại hồ sơ" field="fileTypeName"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên tổ chức, cá nhân" field="businessName"
                                                width="25%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName"  cellStyles="text-align:left;"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Cán bộ xử lý trước đây" field="nameStaffProcess"  cellStyles="text-align:center;"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Trạng thái hồ sơ" field="status" get="page.getIndex" formatter="page.formatStatus" cellStyles="text-align:center;"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày nộp" field="sendDate" format="dd/MM/yyyy" type="date"
                                                width="7%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày đến" field="modifyDate" format="dd/MM/yyyy" type="date"
                                                width="7%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:Button id="btnshowAssignMore" key="" onclick="page.showAssignMore()">
                        <img src="${contextPath}/share/images/group.png" height="20" width="20"/>
                        <span style="font-size:12px" title="Cho phép phân công nhiều hồ sơ, vui lòng tích chọn hồ sơ cần phân công trước khi thực hiện">Phân công nhiều hồ sơ</span>
                    </sd:Button>
                </td>
            </tr>

        </table>
    </sd:TitlePane>
</div>

<sd:Dialog  id="assignDlg" height="auto" width="900px" key="dialog.titleAddEdit">
    <jsp:include page="../assignDlg.jsp" flush="false"/>
</sd:Dialog>
<sd:Dialog  id="nodeListDlg" height="auto" width="600px" key="Chọn chuyên viên thẩm định hồ sơ">
    <jsp:include page="../nextNodeList.jsp" flush="false"/>
</sd:Dialog>

<div id="createDiv"></div>

<script type="text/javascript">
    backPage = function () {
        doGoToMenu(g_latestClickedMenu);
    };

    afterLoadForm = function (data) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
    };

    page.showViewFile = function (fileId) {
        doGoToMenu("filesAction!toFileDlgView.do?fileId=" + fileId + "&viewType=-1" + "&backPage=10");
    };

    page.showNextNode = function (fileId) {
        page.loadFlow(fileId);
    };

    page.showAssignPopup = function (fileId) {
        page.showAssignDlgPrepare(fileId, null, 1);
        dijit.byId("assignDlg").show();
    };

    page.showAssignMore = function ()
    {
        if (!dijit.byId("filesGrid").vtIsChecked()) {
            msg.alert('Bạn chưa chọn hồ sơ để thực hiện phân công', 'Cảnh báo');
        }
        else {
            msg.confirm('Bạn có chắc chắn muốn phân công nhiều hồ sơ?', '<sd:Property>confirm.title1</sd:Property>', page.showAssignMorePopup);
                    }
                };

                page.showAssignMorePopup = function () {
                    var gridProcess = dijit.byId("processGridId");
                    // check xem da ton tai tren grid chua
                    var i = 0;
                    for (i = 0; i < gridProcess._by_idx.length; i++) {
                        var processItem = gridProcess.getItem(i);
                        gridProcess.store.deleteItem(processItem);
                    }
                    gridProcess.scrollToRow(gridProcess._by_idx.length);

                    var items = dijit.byId("filesGrid").vtGetCheckedItems();
                    var lstObjectId = "";
                    if (items != null && items.length >= 0) {
                        for (var i = 0; i < items.length; i++)
                        {
                            if (i != items.length - 1)
                                lstObjectId += items[i].fileId + ",";
                            else
                                lstObjectId += items[i].fileId;
                        }
                        dijit.byId("assignDlg").show();
                        page.showAssignDlgPrepare(null, lstObjectId, 2);
                    }
                };

                page.search = function () {
                    if (page.validateSearch()) {
                        dijit.byId("filesGrid").vtReload('filesAction!onAssignEvaluationAfterAnnounced.do?', "searchForm", null, page.afterSearch);
                    }
                };
                page.afterSearch = function () {
                    dijit.byId("searchForm.flagSavePaging").setValue("0");
                };

                page.reset = function () {
                    dijit.byId('searchForm.fileCode').attr('value', '');
                    dijit.byId('searchForm.fileType').attr('value', '-1');
                    dijit.byId('searchForm.sendDateFrom').attr('value', null);
                    dijit.byId('searchForm.sendDateTo').attr('value', null);

                    page.search();
                };

                page.insert = function () {
                    page.clearInsertForm();
                    dijit.byId("createDlg").show();
                };
                page.showEditPopup = function (row) {
                    var item = dijit.byId("filesGrid").getItem(row);
                    page.setItem(item);
                    dijit.byId("createDlg").show();
                };

                page.deleteItemExecute = function () {
                    var content = dijit.byId("filesGrid").vtGetCheckedDataForPost("lstItemOnGrid");
                    sd.connector.post("businessAction!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
                };

                page.returnMessageDelete = function (data) {
                    var obj = dojo.fromJson(data);
                    var result = obj.items;
                    resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
                    page.search();
                };

                page.showSearchPanel = function () {
                    var panel = document.getElementById("searchDiv");
                    panel.setAttribute("style", "display:;");
                    dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
                };
                page.validateSearch = function () {
                    var datefrom = dijit.byId("searchForm.sendDateFrom").getValue();
                    var dateto = dijit.byId("searchForm.sendDateTo").getValue();
                    if (datefrom != null && dateto != null) {
                        if (datefrom > dateto) {
                            alert("Điều kiện tìm kiếm Từ ngày đến ngày không đúng");
                            dijit.byId("searchForm.sendDateFrom").focus();
                            return false;
                        }
                    }
                    return true;
                };
                page.reloadViewOldVersion = function (oldVersion, thisVersion) {//u140728
                    document.getElementById('createDiv').Value = '';
                    var gridRegister = dijit.byId('processCommentGridId_VTGrid');
                    if (gridRegister) {
                        gridRegister.destroyRecursive(true);
                    }
                    sd.connector.post("filesAction!loadFilesByOldVersion.do?thisVersion=" + thisVersion + "&oldVersion=" + oldVersion, "createDiv", null, null, afterReloadViewOldVersion);
                };
                afterReloadViewOldVersion = function (data) {
                };//!u140728
                page.deleteItem = function () {
                    if (!dijit.byId("filesGrid").vtIsChecked()) {
                        msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
                                }
                                else {
                                    msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
                                            }
                                        };
                                        page.propose = function () {
                                            if (!dijit.byId("filesGrid").vtIsChecked()) {
                                                msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
                                                        }
                                                        else {
                                                            msg.confirm('<sd:Property>confirm.propose</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.proposeItem);
                                                                    }
                                                                };
                                                                page.proposeItem = function () {
                                                                    var content = dijit.byId("filesGrid").vtGetCheckedDataForPost("lstItemOnGrid");
                                                                    sd.connector.post("filesAction!onPropose.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
                                                                };
                                                                page.search();
</script>
