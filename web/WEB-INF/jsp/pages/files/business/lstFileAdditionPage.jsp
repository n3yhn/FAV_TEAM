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
    page.getNo = function(index) {
        return dijit.byId("filesGrid").currentRow + index + 1;
    };

    page.getIndex = function(index) {
        return index + 1;
    };

    page.formatEdit = function(inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var url = "<div style='text-align:center;cursor:pointer;'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + item.fileId + ");' /></div>";
        }
        return url;
    };



    page.formatStatus = function(inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            switch (item.status) {
                case 1:
                    url = "Mới nộp";
                    break;
//                case 2:
//                    url = "Đã được đề xuất xử lý";
//                    break;
//                case 3:
//                    url = "Đã phân công xử lý";
//                    break;
//                case 4:
//                    url = "Đã thẩm định";
//                    break;
//                case 5:
//                    url = "Đã xem xét kết quả";
//                    break;
                case 6:
                    url = "Đã phê duyệt kết quả";
                    break;
//                case 7:
//                    url = "Đã trả lại bổ sung hồ sơ";
//                    break;
//                case 8:
//                    url = "Đã trả lại để thẩm định lại";
//                    break;
//                case 9:
//                    url = "Đã trả lại để xem xét lại";
//                    break;
                case 14:
                    url = "Đã tiếp nhận";
                    break;
                case 15:
                    url = "Đã đối chiếu";
                    break;
                case 20:
                    url = "Đã tạo giấy thông báo sửa đổi bổ sung";
                    break;
                case 21:
                    url = "Hồ sơ đã bị từ chối tiếp nhận";
                    break;
                default:
                    url = "Mới tạo";
            }
        }
        return url;
    };

    page.formatAction = function(inData) {
        var row = inData - 1;
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {

            url = "<div style='text-align:center;cursor:pointer;'><img src='share/images/icons/copy_icon.png' width='17px' height='17px' title='Tạo bản sao hồ sơ' onClick='page.copyFile(" + item.fileId + ");' />";
            var status = parseInt(item.status);
            var signed = true;
            if (item.userSigned == null || item.userSigned == "") {
                signed = false;
            }
            switch (status) {
                case 1, 2, 3, 4, 5, 6, 8, 9:
                    url = "";
                    break;
                case 7:
//                    url += " | <img src='share/images/list.png' width='17px' height='17px' title='Nội dung thông báo sửa đổi bổ sung' onClick='page.showEvaludateResult(" + row + ");' />";
//                    url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Bổ sung hồ sơ' onClick='page.showEditFile(" + item.fileId + ");' />";
//                    url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
                    break;
                case 20:
//                    url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Bổ sung hồ sơ' onClick='page.showEditFile(" + item.fileId + ");' />";
//                    url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
                    url += " | <img src='share/images/list.png' width='17px' height='17px' title='Nội dung thông báo sửa đổi bổ sung' onClick='page.showEvaludateResult(" + row + ");' />";
                    if (signed == false) {
                        url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Bổ sung hồ sơ' onClick='page.showEditFile(" + item.fileId + ");' />";
                        url += " | <img src='share/images/signature.png' width='17px' height='17px' title='Ký CA' onClick='page.signCa(" + row + ");' />";

                        url += " | <img src='share/images/icons/UpArrow.png' width='17px' height='17px' title='Upload' onClick='page.Upload(" + row + ");' />";
                    } else
                    {
                        url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Bổ sung hồ sơ' onClick='page.showEditFile(" + item.fileId + ");' />";
                        if (item.userSigned == "fileUploaded")
                        {
                            url += " | <img src='share/images/icons/kdevelop_down.png' width='17px' height='17px' title='Hồ sơ đã upload' onClick='page.downloadFileSign(" + item.fileId + ");' />";
                            url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
                            url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                        } else {
                            url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
                            url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                        }
                    }
                    break;
                case 21:
//                    url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Bổ sung hồ sơ' onClick='page.showEditFile(" + item.fileId + ");' />";
//                    url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
//                    url += " | <img src='share/images/list.png' width='17px' height='17px' title='Nội dung thông báo bổ sung hồ sơ' onClick='page.showReceiveResult(" + row + ");' />";
                    //url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
                    url += " | <img src='share/images/list.png' width='17px' height='17px' title='Nội dung thông báo bổ sung hồ sơ' onClick='page.showReceiveResult(" + row + ");' /> ";
                    if (signed == false) {
                        url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Bổ sung hồ sơ' onClick='page.showEditFile(" + item.fileId + ");' />";
                        url += " | <img src='share/images/signature.png' width='17px' height='17px' title='Ký CA' onClick='page.signCa(" + row + ");' />";
                        url += " | <img src='share/images/icons/UpArrow.png' width='17px' height='17px' title='Upload' onClick='page.Upload(" + row + ");' />";
                    }
                    else
                    {
                        url += " | <img src='share/images/edit.png' width='17px' height='17px' title='Bổ sung hồ sơ' onClick='page.showEditFile(" + item.fileId + ");' />";
                        if (item.userSigned == "fileUploaded")
                        {
                            url += " | <img src='share/images/icons/kdevelop_down.png' width='17px' height='17px' title='Hồ sơ đã upload' onClick='page.downloadFileSign(" + item.fileId + ");' />";
                            url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
                            url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                        } else {
                            url += " | <img src='share/images/icons/send_document.png' width='17px' height='17px' title='Gửi hồ sơ' onClick='page.sendFile(" + row + ");' />";
                            url += " | <img src='share/images/icons/payment.png' width='17px' height='17px' title='Thanh toán' onClick='page.payFile(" + row + ");' />";
                        }
                    }

                    break;
                default:
            }
        }

        return url;
    };
    page.formatWarning = function(inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var url = "";
        var strWarning = "";
        var flag = 0;
        if (item != null) {
            var isFee = parseInt(item.isFee);
            if (isFee != 1 && (status == 15 || status == 6)) {
                strWarning += "- Chưa đóng phí hồ sơ đầy đủ&#13;";
            }
            var status = parseInt(item.status);
            var deadlineReceived = item.deadlineReceived;
            var deadlineAddition = item.deadlineAddition;
            var deadlineApprove = item.deadlineApprove;
            var today = '${sysDate}';
            switch (status) {
                case 1:
                    if (item.isFee == 1) {
                        if (deadlineReceived < today) {
                            strWarning += "- Quá hạn tiếp nhận&#13;";
                            flag = 1;
                        } else {
                            if (deadlineReceived == today) {
                                strWarning += "- Hôm nay là ngày tiếp nhận&#13;";
                            } else {
                            }
                        }
                    }
                    break;
                case 20:
                    if (deadlineAddition < today) {
                        strWarning += "- Quá hạn Sửa đổi bổ sung&#13;";
                        flag = 1;
                    } else {
                        if (deadlineAddition == today) {
                            strWarning += "- Hôm nay là ngày cuối nộp sửa đổi bổ sung&#13;";
                        } else {
                        }
                    }
                    break;
                case 6:
                    if (deadlineApprove < today) {
                        strWarning += "- Quá hạn Phê duyệt hồ sơ&#13;";
                        flag = 1;
                    } else {
                        if (deadlineApprove == today) {
                            strWarning += "- Hôm nay là hạn phê duyệt hồ sơ&#13;";
                        } else {
                        }
                    }
                    break;
                default:
            }
        }
        if (strWarning.trim().length == 0) {
            url = "";
        } else {
            if (flag == 0) {
                url = "<img src='share/images/icons/warning.png' width='17px' height='17px' title='" + strWarning + "'/>";
            } else {
                url = "<img src='share/images/icons/error.png' width='17px' height='17px' title='" + strWarning + "'/>";
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

    dojo.connect(dojo.byId("searchConditionDiv"), "onkeypress", page.searchDefault);

</script>
<div id="token">
    <s:token id="tokenId"/>
</div>
<div id="searchDiv">
    <div id="searchConditionDiv" style="display:none">
        <sd:TitlePane key="search.searchCondition" id="categoryTitle">
            <form id="searchForm" name="searchForm">
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
                            <sd:TextBox cssStyle="display:none"
                                        id="searchForm.status"
                                        key=""
                                        name="searchForm.status" value='20'/>
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
                        </td>
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
    </div>
    <sd:TitlePane key="Danh sách hồ sơ"
                  id="businessList" >
        <sx:ResultMessage id="resultCreateMessage" />
        <table width="100%">
            <tr>
                <td>
                    <div id="fileGridDiv" style="width:100%;">
                        <sd:DataGrid id="filesGrid"
                                     getDataUrl=""
                                     rowSelector="0%"
                                     style="width:auto;height:auto"
                                     rowsPerPage="20"
                                     serverPaging="true"
                                     clientSort="false">
                            <sd:ColumnDataGrid key="category.No" get="page.getNo" width="5%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="Xem" headerStyles="text-align:center;" width="2%" cellStyles="text-align:center;"
                                               formatter="page.formatEdit" get="page.getIndex"/>    
                            <sd:ColumnDataGrid  key="Hành động" 
                                                width="7%"  headerStyles="text-align:center;"
                                                formatter="page.formatAction" get="page.getIndex"/>
                            <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode" width="7%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Loại hồ sơ" field="fileTypeName"
                                                width="20%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày nộp" field="sendDate" format="dd/MM/yyyy" type="date"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                            <!--sd:ColumnDataGrid  key="Ngày phê duyệt" field="approveDate" format="dd/MM/yyyy" type="date"
                                                width="15%"  headerStyles="text-align:center;" cellStyles="text-align:center;" /-->
                            <sd:ColumnDataGrid  key="Ngày hết hạn" field="deadlineApprove" format="dd/MM/yyyy" type="date"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                            <!--sd:ColumnDataGrid  key="Trạng thái hồ sơ" get="page.getNo" formatter="page.formatStatus" cellStyles="text-align:center;"
                                                width="10%"  headerStyles="text-align:center;" /-->
                            <sd:ColumnDataGrid  key="Trạng thái hồ sơ"  cellStyles="text-align:left;" field="displayStatus"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="Cảnh báo" headerStyles="text-align:center;" width="5%" cellStyles="text-align:center;"
                                               formatter="page.formatWarning" get="page.getIndex"/>
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>

<div id="createDiv"></div>

<sd:Dialog id="selectDeptDlg" key="Chọn cơ quan xử lý" width="500px">
    <div style="overflow: auto; max-height: 500px">
        <jsp:include page="selectDept.jsp" />
    </div>
</sd:Dialog>
<sd:Dialog  id="feedbackEvaluateViewDetailsDlg" height="auto" width="1000px"
            key="Chi tiết kết quả thẩm định" showFullscreenButton="false"
            >
    <jsp:include page="../evaluation/feedbackEvaluate/feedbackEvaluateFormView.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="receivedFormViewDetailsDlg" height="auto" width="800px"
            key="Nội dung yêu cầu" showFullscreenButton="false"
            >
    <jsp:include page="../received/receivedFormViewDetails.jsp" flush="false"></jsp:include>
</sd:Dialog>
<sd:Dialog  id="upload" height="auto" width="400px"
            key="Upload hồ sơ đã ký số" showFullscreenButton="false"
            >
    <jsp:include page="signUpload.jsp" flush="false"></jsp:include>
</sd:Dialog>
<script type="text/javascript">
    var fileId = 0;
    var workingFileId;

    backPage = function() {
        doGoToMenu(g_latestClickedMenu);

    };
    page.search = function() {
        dijit.byId("filesGrid").vtReload('filesAction!onSearchBusinessFiles.do?', "searchForm");
    };

    afterLoadForm = function(data) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
    };

    page.showViewFile = function(fileId) {
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + fileId + "&createForm.viewType=0", "createDiv", null, null, afterLoadForm);
    };

    page.showEditFile = function(fileId) {
        sd.connector.post("filesAction!toCreateFilePage.do?createForm.fileId=" + fileId, "createDiv", null, null, afterLoadForm);
    };

    page.loadView = function() {
        page.clearInsertForm();
        dijit.byId("createDlg").show();
    };

    page.showSearchPanel = function() {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };

    page.showDept = function(row) {
        var item = dijit.byId("filesGrid").getItem(row);
        workingFileId = item.fileId;
        dijit.byId("deptGrid").vtReload("procedureAction!getDeptProcedure.do?procedureId=" + item.fileType);
        document.getElementById("Sign").checked = false;
        document.getElementById("Unsign").checked = false;
        dijit.byId("selectDeptDlg").show();
    };

    page.sendFile = function(row) {
        page.showDept(row);
    };
    page.search();
    page.copyFile = function(fileId) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
        sd.connector.post("filesAction!toCopyFilePage.do?createForm.fileId=" + fileId, "createDiv", null, null, function() {
            document.getElementById("searchDiv").style.display = "none";
            document.getElementById("createDiv").style.display = "";
            dijit.byId("createForm.fileId").setValue(null);
        });
    };

    page.showEvaludateResult = function(row) {//show kết quả thẩm định       
        var file = dijit.byId("filesGrid").getItem(row);
        var statusName = page.getStatusName(parseInt(file.status));
        fileId = file.fileId;
        document.getElementById("evaluateFormView.status").innerHTML = statusName;
        document.getElementById("evaluateFormView.staffRequest").innerHTML = escapeHtml_(file.staffRequest);
        var isTypeChange = file.isTypeChange;
        if (isTypeChange == 1) {
            document.getElementById("evaluateFormView.isTypeChange").style.display = "";
        } else {
            document.getElementById("evaluateFormView.isTypeChange").style.display = "none";
        }
        page.replaceNewLineByBr();
        sd.connector.post("filesAction!showStaffContact.do?objectId=" + fileId, null, null, null, afterShowStaffContact);
    };
    afterShowStaffContact = function(data) {
        dijit.byId("feedbackEvaluateViewDetailsDlg").show();
        data = dojo.fromJson(data);
        document.getElementById("evaluateFormView.staffNameContact").innerHTML = data.customInfo[0];
        document.getElementById("evaluateFormView.staffEmailContact").innerHTML = data.customInfo[1];
        document.getElementById("evaluateFormView.staffTelContact").innerHTML = data.customInfo[2];
        page.showComment();
        page.showVersionSDBS();
    }
    page.showReceiveResult = function(row) {//show ket qua tiep nhan ho so - van thu - 140410 binhnt53
        var file = dijit.byId("filesGrid").getItem(row);
        var statusName = page.getStatusName(parseInt(file.status));
        document.getElementById("receivedFormViewDetails.status").innerHTML = statusName;
        document.getElementById("receivedFormViewDetails.clericalRequest").innerHTML = escapeHtml_(file.clericalRequest);
        page.replaceTblReceivedFormViewDetails();
        dijit.byId("receivedFormViewDetailsDlg").show();
    };

    page.getStatusName = function(status) {
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
                url = "Đã thẩm định";
                break;
            case 5:
                url = "Đã xem xét kết quả";
                break;
            case 6:
                url = "Đã phê duyệt kết quả";
                break;
            case 7:
                url = "Chuyên viên KL: SĐBS";
                break;
            case 8:
                url = "Đã trả lại để thẩm định lại";
                break;
            case 9:
                url = "Đã trả lại để xem xét lại";
                break;
            case 21:
                url = "Đã từ chối - Có HD bổ sung";
                break;
            default:
                url = "Mới tạo";
        }
        return url;
    };
    //update 140517
    page.setCreateDiv = function(fileId, fileType) {
        var fileTypeTemp = fileType;
        var fileIdTemp = fileId;
        document.getElementById('createDiv').Value = '';
        sd.connector.post("filesAction!toCreateFilePage.do?fileType=" + fileTypeTemp + "&fileId=" + fileIdTemp, "createDiv", null, null, afterLoadFormChange);
    };
    afterLoadFormChange = function(data) {
    };

    page.reloadViewOldVersion = function(oldVersion, thisVersion) {//u140728
        document.getElementById('createDiv').Value = '';
        var gridRegister = dijit.byId('processCommentGridId_VTGrid');
        if (gridRegister) {
            gridRegister.destroyRecursive(true);
        }
        sd.connector.post("filesAction!loadFilesByOldVersion.do?thisVersion=" + thisVersion + "&oldVersion=" + oldVersion, "createDiv", null, null, afterReloadViewOldVersion);
    };
    afterReloadViewOldVersion = function(data) {
    };//!u140728
    page.downloadFileSign = function(fileId) {
        document.location = "uploadiframe!openFileUserUpload.do?fileId=" + fileId;
    };
    page.payFile = function(row)
    {
        var item = dijit.byId("filesGrid").getItem(row);
        sd.connector.post("filesAction!preparePayment.do?createFeeForm.fileId=" + item.fileId, 'createDiv', null, null, afterLoadPayForm);
    }
    afterLoadPayForm = function(data) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
    };
    page.signCa = function(row) {
        var item = dijit.byId("filesGrid").getItem(row);
        workingFileId = item.fileId;
        page.onSelectDeptNew(row);
    };
    page.Upload = function(row)
    {
        var item = dijit.byId("filesGrid").getItem(row);
        workingFileId = item.fileId;
        clearAttFile("sendForm.attachFileNew");
        dijit.byId("upload").show();
    };
</script>