<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

    page.getRow = function (inRow) {
        return inRow;
    };
    page.formatEdit = function (inData) {
        var url = "<div class='box' onclick='page.viewFile(" + inData + ");' />" + inData + "</div>";

        return url;
    };

    page.formatStatus = function (inData) {
        var row = inData;
        var item = dijit.byId("filesGrid").getItem(row);
        var url = "";
        if (item != null) {
            url = item.displayStatus;
        }

        //url = "<a href='#' onClick='showEvaludateResult(" + row + ")'>" + url + "</a>"
        return url;
    };

    page.formatAction = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        if (item != null) {
            var fileId = item.fileId;
            var status = parseInt(item.status);
            var url = "<div style='text-align:left;cursor:pointer;'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + item.fileId + ");' />";
            switch (status) {
                case 3:
//                    url += "| <img src='share/images/edit.png' width='17px' height='17px' title='LĐP thẩm định hồ sơ' onClick='page.getCommentEvaluateForm(" + item.fileId + "," + item.fileType + "); ' />";
                    break;
                case 4:
                    url += "| <img src='share/images/edit.png' width='17px' height='17px' title='LĐP thẩm định hồ sơ' onClick='page.getCommentEvaluateByLeaderFormOnGrid(" + item.fileId + "," + item.fileType + "," + item.status + "); ' />";
                    break;
                case 7:
                    url += "| <img src='share/images/edit.png' width='17px' height='17px' title='LĐP thẩm định hồ sơ' onClick='page.getCommentEvaluateByLeaderFormOnGrid(" + item.fileId + "," + item.fileType + "," + item.status + "); ' />";
                    break;
                case 8:
//                    url += "| <img src='share/images/edit.png' width='17px' height='17px' title='LĐP thẩm định hồ sơ' onClick='page.getCommentEvaluateForm(" + item.fileId + "," + item.fileType + "); ' />";
                    break;
                case 17:
//                    url += "| <img src='share/images/edit.png' width='17px' height='17px' title='LĐP thẩm định hồ sơ' onClick='page.getCommentEvaluateForm(" + item.fileId + "," + item.fileType + "); ' />";
                    break;
                default:
                    ;
            }
            if (item.flowId == null
                    || item.flowId == "") {
                var lstRole = dijit.byId("lstRole").value;
                if ((lstRole.toString() != "") && (lstRole.toString().indexOf("voffice_lddv") >= 0)) {
                    url += "| <img src='${contextPath}/share/images/group.png' width='17px' height='17px' title='Phân công hồ sơ' onclick='page.showAssignPopup(" + fileId + ");' />";
                }
            }
        }
        url += "</div>";
        return url;
    };

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
                                    name="searchForm.fileCode" 
                                    maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Số bản công bố"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.announcementNo"
                                    key=""
                                    name="searchForm.announcementNo" 
                                    maxlength="250"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Tên tổ chức, cá nhân"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.businessName"
                                    key=""
                                    name="searchForm.businessName" 
                                    maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Số đăng ký kinh doanh/số CMT"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.businessLicence"
                                    key=""
                                    name="searchForm.businessLicence" 
                                    maxlength="250"/>
                    </td>

                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Địa chỉ"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.businessAddress"
                                    key=""
                                    name="searchForm.businessAddress" 
                                    maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Tên sản phẩm"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.productName"
                                    key=""
                                    name="searchForm.productName" 
                                    maxlength="250"/>
                    </td>

                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Tên nước xuất xứ (nhập khẩu)"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.nationName"
                                    key=""
                                    name="searchForm.nationName" 
                                    maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Tên nhà sản xuất"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.manufactureName"
                                    key=""
                                    name="searchForm.manufactureName" 
                                    maxlength="250"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Địa chỉ sản xuất"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.manufactureAddress"
                                    key=""
                                    name="searchForm.manufactureAddress" 
                                    maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Số ký hiệu QCKT/quy định ATTP"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.matchingTarget"
                                    key=""
                                    name="searchForm.matchingTarget" 
                                    maxlength="250"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Loại hồ sơ"/>
                    </td>
                    <td>
                        <sd:SelectBox cssStyle="width:100%"
                                      id="searchForm.fileType"
                                      key="" data="lstFileType" 
                                      valueField="procedureId" 
                                      labelField="name"
                                      name="searchForm.fileType" >
                        </sd:SelectBox>
                        <sd:TextBox key="" id="searchForm.status" 
                                    name="searchForm.status" 
                                    cssStyle="display:none"/>
                    </td>
                    <td colspan="2"></td>
                </tr>
                <tr style="text-align: center">
                    <td colspan="4">
                        <sx:ButtonSearch onclick="page.search();" />
                        <sd:Button key="" onclick="page.reset();" > 
                            <img src="share/images/icons/reset.png" height="14" width="18"/>
                            <span style="font-size:12px">Hủy</span>
                        </sd:Button>
                    </td>
                </tr>

            </table>
        </form>
    </sd:TitlePane>

    <sd:TitlePane key="Danh sách hồ sơ"
                  id="businessList" >
        <sx:ResultMessage id="resultDeleteMessage" />
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
                            <sd:ColumnDataGrid key="STT" get="page.getNo" width="5%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="3%" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Chức năng" 
                                                width="7%"  headerStyles="text-align:center;"
                                                formatter="page.formatAction" get="page.getIndex" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode" width="7%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Số bản công bố" field="announcementNo" cellStyles="text-align:left;"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Loại hồ sơ" field="fileTypeName"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên tổ chức, cá nhân" field="businessName" cellStyles="text-align:left;"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName" cellStyles="text-align:left;"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày nộp" field="sendDate" format="dd/MM/yyyy" type="date"
                                                width="7%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Trạng thái" formatter="page.formatStatus" get="page.getRow"
                                                width="10%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:Button id="btnshowAssignMore" key="" onclick="page.showAssignMore()" cssStyle="display:none">
                        <img src="${contextPath}/share/images/group.png" height="20" width="20"/>
                        <span style="font-size:12px" title="Cho phép phân công nhiều hồ sơ, vui lòng tích chọn hồ sơ cần phân công trước khi thực hiện">Phân công hồ sơ</span>
                    </sd:Button>
                    <sd:TextBox name="lstRole" key="" cssStyle="display:none;" id="lstRole" value="${fn:escapeXml(lstRole)}"/>
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>

<%--<sd:Dialog  id="evaluateDlg" height="auto" width="600px"
            key="Kết quả thẩm định" showFullscreenButton="false"
            >
    <jsp:include page="evaluateForm.jsp" flush="false"></jsp:include>
</sd:Dialog>--%>
<sd:Dialog  id="assignDlg" height="auto" width="900px" key="dialog.titleAddEdit">
    <jsp:include page="../assignCoDlg.jsp" flush="false"/>
</sd:Dialog>
<sd:Dialog  id="evaluateByLeaderFormOnGridDlg" height="auto" width="1200px"
            key="Thẩm định hồ sơ" showFullscreenButton="false"
            >
    <jsp:include page="evaluateByLeaderFormOnGrid.jsp" flush="false"></jsp:include>
</sd:Dialog>
<div id="viewDiv"></div>
<script type="text/javascript">
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    page.search = function () {
        dijit.byId("filesGrid").vtReload('filesAction!onSearchFileToEvaluateLeader.do', "searchForm");
    };

    page.showViewFile = function (fileId) {
        doGoToMenu("filesAction!toFileDlgView.do?fileId=" + fileId + "&viewType=3" + "&backPage=1");
    };

    page.getCommentEvaluateByLeaderFormOnGrid = function (fileId, fileType, status) {
        dijit.byId("evaluateByLeaderFormOnGrid.fileId").setValue(fileId);
        dijit.byId("evaluateByLeaderFormOnGrid.status").setValue(status);
        if (fileType != 66750) {
            var panel = document.getElementById("effectiveDateDiv");
            panel.setAttribute("style", "display:;");
        }
        dijit.byId("evaluateByLeaderFormOnGridDlg").show();
        document.getElementById("trWaitViewFile").style.display = '';
        sd.connector.post("filesAction!getCommentEvaluateFormByLeader.do?objectId=" + fileId, null, null, null, afterCommentEvaluateLeaderFormOnGrid);
    };

    afterCommentEvaluateLeaderFormOnGrid = function (data) {
        var obj = dojo.fromJson(data);
        if (obj.customInfo[0] != "") {
            document.getElementById("evaluateByLeaderFormOnGrid.legalContent").value = obj.customInfo[0];
        } else {
            document.getElementById("evaluateByLeaderFormOnGrid.legalContent").value = "";
        }
        if (obj.customInfo[1] != "") {
            document.getElementById("evaluateByLeaderFormOnGrid.foodSafetyQualityContent").value = obj.customInfo[1];
        } else {
            document.getElementById("evaluateByLeaderFormOnGrid.foodSafetyQualityContent").value = "";
        }
        if (obj.customInfo[2] != "") {
            document.getElementById("evaluateByLeaderFormOnGrid.effectUtilityContent").value = obj.customInfo[2];
        } else {
            document.getElementById("evaluateByLeaderFormOnGrid.effectUtilityContent").value = "";
        }
        if (obj.customInfo[3] != "") {
            document.getElementById("evaluateByLeaderFormOnGrid.staffRequest").value = obj.customInfo[3];
        } else {
            document.getElementById("evaluateByLeaderFormOnGrid.staffRequest").value = "";
        }
        if (obj.customInfo[4] != null) {
            dijit.byId("evaluateByLeaderFormOnGrid.effectiveDate").setValue(obj.customInfo[4]);
        } else {
            dijit.byId("evaluateByLeaderFormOnGrid.effectiveDate").setValue(-1);
        }

        if (obj.customInfo[5] != null) {
            dijit.byId("evaluateByLeaderFormOnGrid.legal").setValue(obj.customInfo[5]);
        } else {
            dijit.byId("evaluateByLeaderFormOnGrid.legal").setValue(1);
        }

        if (obj.customInfo[6] != null) {
            dijit.byId("evaluateByLeaderFormOnGrid.foodSafetyQuality").setValue(obj.customInfo[6]);
        } else {
            dijit.byId("evaluateByLeaderFormOnGrid.foodSafetyQuality").setValue(1);
        }

        if (obj.customInfo[7] != null) {
            dijit.byId("evaluateByLeaderFormOnGrid.effectUtility").setValue(obj.customInfo[7]);
        } else {
            dijit.byId("evaluateByLeaderFormOnGrid.effectUtility").setValue(1);
        }

//        dijit.byId("evaluateByLeaderFormOnGrid.ProductType").setValue(0);

        var status = dijit.byId("evaluateByLeaderFormOnGrid.status").getValue();
        if (status == 4) {
            document.getElementById("evaluateByLeaderFormOnGrid.statusAccept").checked = true;

        } else if (status == 7) {
            document.getElementById("evaluateByLeaderFormOnGrid.statusDeny").checked = true;
        }
        var statusName = getStatusStaffEvaluate(parseInt(status));
        document.getElementById("evaluateByLeaderFormOnGrid.statusL").innerHTML = statusName;
        var fileId = dijit.byId("evaluateByLeaderFormOnGrid.fileId").getValue();
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + fileId + "&createForm.viewType=4&viewTypeDialog=1", "divViewFile", null, null, afterLoadViewFileOnGrid);
    };

    afterLoadViewFileOnGrid = function () {
        document.getElementById("trWaitViewFile").style.display = 'none';
    };

    getStatusStaffEvaluate = function (status) {
        switch (status) {
            case 1:
                url = "Mới nộp";
                break;
            case 2:
                url = "Đã được đề xuất xử lý";
                break;
            case 3:
                url = "Đã phân công xử lý";
                break;
            case 4:
                url = "Đã thẩm định hồ sơ đạt";
                break;
            case 5:
                url = "Đã xem xét kết quả";
                break;
            case 6:
                url = "Đã phê duyệt kết quả";
                break;
            case 7:
                url = "Đã thẩm định yêu cầu SĐBS";
                break;
            case 8:
                url = "Đã trả lại để thẩm định lại";
                break;
            case 9:
                url = "Đã trả lại để xem xét lại";
                break;
            default:
                url = "Mới tạo";
        }
        return url;
    };
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    page.showAssignPopup = function (fileId) {
        dijit.byId("assignDlg").show();
        page.showAssignDlg(fileId);
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
                    if (items != null
                            && items.length >= 0) {
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

                backPage = function () {
                    document.getElementById("searchDiv").style.display = "";
                    document.getElementById("viewDiv").style.display = "none";
                };

                page.reset = function () {
                    dijit.byId('searchForm.fileCode').attr('value', '');
                    dijit.byId('searchForm.announcementNo').attr('value', '');
                    dijit.byId('searchForm.businessName').attr('value', '');
                    dijit.byId('searchForm.businessLicence').attr('value', '');
                    dijit.byId('searchForm.businessAddress').attr('value', '');
                    dijit.byId('searchForm.productName').attr('value', '');
                    dijit.byId('searchForm.nationName').attr('value', '');
                    dijit.byId('searchForm.manufactureName').attr('value', '');
                    dijit.byId('searchForm.manufactureAddress').attr('value', '');
                    dijit.byId('searchForm.matchingTarget').attr('value', '');
                    dijit.byId('searchForm.fileType').attr('value', '-1');
                };

                afterLoadForm = function (data) {
                    document.getElementById("searchDiv").style.display = "none";
                    document.getElementById("viewDiv").style.display = "";
                };
                page.showSearchPanel = function () {
                    var panel = document.getElementById("searchDiv");
                    panel.setAttribute("style", "display:;");
                    dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
                };
                showEvaludateResult = function (row) {//show kết quả thẩm định
                    var file = dijit.byId("filesGrid").getItem(row);
                    var statusName = page.getStatusName(parseInt(file.status));
                    document.getElementById("evaluateFormView.status").innerHTML = statusName;
                    document.getElementById("evaluateFormView.staffRequest").innerHTML = escapeHtml_(file.staffRequest);
                    document.getElementById("evaluateFormView.leaderStaffRequest").innerHTML = escapeHtml_(file.leaderStaffRequest);
                    document.getElementById("evaluateFormView.leaderRequest").innerHTML = escapeHtml_(file.leaderRequest);
                    dijit.byId("evaluateViewDlg").show();
                };
                page.reloadViewOldVersion = function (oldVersion, thisVersion) {
                    document.getElementById('viewDiv').Value = '';
                    var processCommentGridId_VTGrid = dijit.byId('processCommentGridId_VTGrid');
                    if (processCommentGridId_VTGrid) {
                        processCommentGridId_VTGrid.destroyRecursive(true);
                    }
                    var filesCommentGridId_VTGrid = dijit.byId('filesCommentGridId_VTGrid');
                    if (filesCommentGridId_VTGrid) {
                        filesCommentGridId_VTGrid.destroyRecursive(true);
                    }
                    var documentProcessGridId_VTGrid = dijit.byId('documentProcessGridId_VTGrid');
                    if (documentProcessGridId_VTGrid) {
                        documentProcessGridId_VTGrid.destroyRecursive(true);
                    }
                    var paymentHistoryGridId_VTGrid = dijit.byId('paymentHistoryGridId_VTGrid');
                    if (paymentHistoryGridId_VTGrid) {
                        paymentHistoryGridId_VTGrid.destroyRecursive(true);
                    }
                    var feePaymentInfoGridId_VTGrid = dijit.byId('feePaymentInfoGridId_VTGrid');
                    if (feePaymentInfoGridId_VTGrid) {
                        feePaymentInfoGridId_VTGrid.destroyRecursive(true);
                    }
                    sd.connector.post("filesAction!loadFilesByOldVersion.do?thisVersion=" + thisVersion + "&oldVersion=" + oldVersion, "viewDiv", null, null, afterReloadViewOldVersion);
                };
                afterReloadViewOldVersion = function (data) {
                };//!u140728

                page.showBtnAssignEvalution = function () {
                    var lstRole = dijit.byId("lstRole").value;
                    if ((lstRole.toString() != "") && (lstRole.toString().indexOf("voffice_lddv") >= 0)) {
                        dijit.byId("btnshowAssignMore").domNode.style.display = "";
                    }
                };
                page.search();
                page.showBtnAssignEvalution();
</script>