<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<script>
    page.getNo = function(index) {
        return dijit.byId("paymentHistoryGridId").currentRow + index + 1;
    };

    page.getIndex = function(index) {
        return index + 1;
    };
    page.formatStatus = function(inData) {
        var item = dijit.byId("paymentHistoryGridId").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var status = parseInt(item.status);
            switch (status) {
                case 1:
                    url = "Đã thanh toán";
                    break;
                case 0:
                    url = "Chưa thanh toán";
                    break;
                default:
                    url = "Chưa thanh toán";
            }
        }
        return url;
    };

</script>


<div>
    <table style="width: 98%">
        <tr id="trWaitViewFile">
            <td colspan="3" style="text-align: center;alignment-adjust: middle">
                <label id="labelWaitLoadFile" style="color: red">Vui lòng chờ  </label>
                <img src="/share/images/loading/loading2.gif" width="20px" height="20px">
            </td>
        </tr>
        <tr>
            <td style="width: 60%">
                <sd:TitlePane key="Thông tin hồ sơ" id="titlePaneViewFile">
                    <div id="divViewFile" style="overflow-y: auto;max-height: 600px"></div>
                </sd:TitlePane>
            </td>
            <td style="width: 38%">
                <sd:TitlePane key="Thông tin tiếp nhận" id="titlePaneViewReceiveFile">
                    <div style="overflow-y: auto;max-height: 600px">
                        <sd:TitlePane key="paymentHistory.paymentHistoryList" id="paymentHistoryTitle">
                            <div id="paymentHistoryGridDiv" style="width:100%;">
                                <sd:DataGrid clientSort="true" 
                                             id="paymentHistoryGridId"
                                             style="width:auto;height:auto"
                                             getDataUrl="" 
                                             container="paymentHistoryGridDiv" 
                                             serverPaging="false"
                                             rowSelector="0px" 
                                             rowsPerPage="10">
                                    <sd:ColumnDataGrid key="voPublishDocument.No" get="page.getNo" width="10%" 
                                                       headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" styles="text-align:center;" />            
                                    <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="paymentHistory.feeName" field="feeName" width="35%"/>            
                                    <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="paymentHistory.income" field="cost" width="20%" styles="text-align:right;"/>
                                    <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="paymentHistory.paymentPerson" field="paymentPerson" width="35%"/>
                                    <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" type="date" format="dd/MM/yyyy" key="paymentHistory.paymentDate" field="paymentDate" width="20%"/>                       
                                    <sd:ColumnDataGrid editable="true" key="category.isActive" headerStyles="text-align:center;" width="20%" cellStyles="text-align:center;" formatter="page.formatStatus" get="page.getIndex"/>
                                </sd:DataGrid>
                            </div>
                        </sd:TitlePane>
                        <form id="receivedForm" name="receivedForm">
                            <sx:ResultMessage id="resultMessage" />
                            <table width="100%" cellpadding="0px" cellspacing="5px">
                                <tr><td width="25%" style="text-align: right"> <sd:Label id="commentForm.aa" key="Số đến:"/></td> 
                                    <td width="75%"><div id="receiveNoDiv" style="color: red;font-weight: bold"></td>
                                </tr>
                                <tr>
                                    <td width="25%">
                                        <input type="hidden" id="commentForm.objectId" name="commentForm.objectId"/>
                                        <input type="hidden" id="commentForm.objectType" name="commentForm.objectType" value="30"/>
                                        <input type="hidden" id="commentForm.fileType" name="commentForm.fileType"/>        
                                    </td>
                                    <td width="75%"></td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Trạng thái thu phí HS" required="true"/></td>
                                    <td>
                                        <input type="radio" id="receivedForm.isFeeAccept" name="createForm.isFee" value="1" disabled="true"/>
                                        <sd:Label key="Phí đã thu đầy đủ"/>
                                        </br>
                                        <input type="radio" id="receivedForm.isFeeDeny" name="createForm.isFee" value="-1" disabled="true"/>
                                        <sd:Label key="Chưa nộp phí"/>

                                    </td>
                                </tr>
                                <tr><td width="25%"></td> 
                                    <td width="75%"></td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Trạng thái tiếp nhận"/></td>
                                    <td>
                                        <sd:SelectBox key="" id="receivedForm.Status" name="createForm.status" onchange="page.checkSelected()">
                                            <sd:Option value='1'>-- Chọn --</sd:Option>
                                            <sd:Option value='14' selected='true'>Tiếp nhận</sd:Option>
                                            <sd:Option value='21'>Từ chối tiếp nhận</sd:Option>
                                        </sd:SelectBox>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Đơn vị xử lý tiếp theo"/></td>
                                    <td>
                                        <div id='divLstDeptProcessFile'>
                                            <sd:SelectBox cssStyle="width:100%"
                                                          id="receivedForm.sbProcessDeptId"
                                                          key="" data="lstDeptProcessFile" valueField="processDeptId" labelField="processDeptName"
                                                          name="createForm.sbProcessDeptId" >
                                            </sd:SelectBox>                    
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                <tr>
                                    <td style="text-align: right"><sd:Label key="Nội dung yêu cầu bổ sung" required="true"/></td>
                                    <td>
                                        <sd:Textarea key="" id="receivedForm.clericalRequest" name="createForm.clericalRequest" rows="4" cssStyle="width:99%" maxlength="1800" trim="true"></sd:Textarea>
                                        </td>

                                    </tr>
                                    <tr id='fileList' style='display: none;'>
                                    <b><td width="25%" style="text-align: right"><sd:Label key="Bổ sung giấy tờ"/></td></b>
                                </tr>
                                <tr>
                                    <td width="10%" style="text-align: left">
                                    <td width="90%" id=''>
                                        <div id='fileListItem'  style="display: none;" >

                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="2" style="text-align: center">
                                        <sx:ButtonSave onclick="page.received();"/>
                                        <div id='fileListItemTemp'  style="display: none;" >
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </sd:TitlePane>
            </td>
        </tr>
        <tr>
            <td colspan="3" style="text-align: center">
                <br/>
            </td>
        </tr>
        <tr>
            <td colspan="3" style="text-align: center">
                <sx:ButtonClose onclick="onCloseApprove();"/>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    var processStatus = 0;
    onCloseApprove = function() {
        doGoToMenu("filesAction!toReceived.do");
    };

    page.getNo = function(index) {
        return dijit.byId("paymentHistoryGridId").currentRow + index + 1;
    };

    fileListChecked = function() {
        var files = document.forms["receivedForm"].fileMore;
        var fileListStr = document.getElementById('fileListItemTemp').innerHTML;
        var fileListArr = fileListStr.split("nl");
        var divContent = document.getElementById('receivedForm.clericalRequest');
        var string = "";

        for (var i = 0; i < files.length; i++) {
            if (files[i].checked) {
                string = string + fileListArr[i + 1] + "\r";
            }
            divContent.value = string;

        }
    };

    page.checkSelected = function() {
        var receivedFormStatus = dijit.byId("receivedForm.Status").getValue();
        var divFileListItem = document.getElementById('fileListItem');
        var divLstDeptProcessFile = document.getElementById('divLstDeptProcessFile');
        divFileListItem.innerHTML = '';
        if (receivedFormStatus == 21) {
            divLstDeptProcessFile.style.display = 'none';
            //var list = '${fileList}';
            var list = document.getElementById('fileListItemTemp').innerHTML;
            document.getElementById("createForm.status").value = "21";
            document.getElementById('fileList').style.display = ''; // Hiện div fileList
            divFileListItem.style.display = '';
            var fileList = list.split("nl");
            for (var i = 1; i < fileList.length; i++)
            {
                divFileListItem.innerHTML = divFileListItem.innerHTML + '<input onchange="fileListChecked();" type="checkbox" name="fileMore"  value="' + i + '"> ' + fileList[i] + '<br>';
            }

        }
        else {
            if (processStatus == 1) {
                divLstDeptProcessFile.style.display = '';
            }
            divFileListItem.style.display = 'none';
            var nullContent = "";
            document.getElementById("receivedForm.clericalRequest").value = nullContent;
            document.getElementById("createForm.status").value = "14";
            document.getElementById('fileList').style.display = 'none';
        }

    };

    showReceivedDlg = function(fileId) {
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + fileId + "&createForm.viewType=1&viewTypeDialog=1", "divViewFile", null, null, afterLoadViewFile);
        dijit.byId("receivedForm.sbProcessDeptId").vtReload("filesAction!loadDepartmentProcessFile.do?createForm.fileId=" + fileId);
    };

    afterLoadViewFile = function() {
        document.getElementById("trWaitViewFile").style.display = 'none';
        getPaymentHistory("30");
        document.getElementById("receivedForm.clericalRequest").value = "";
        dijit.byId("receivedDlg").show();
    };

    getPaymentHistory = function(objectType) {
        processStatus = dijit.byId("createForm.status").getValue();
        if (dijit.byId("createForm.status").getValue() != 1) {
            var divLstDeptProcessFile = document.getElementById('divLstDeptProcessFile');
            divLstDeptProcessFile.style.display = 'none';
        }
        document.getElementById("commentForm.objectId").value = escapeHtml_(objectId);
        document.getElementById("commentForm.objectType").value = escapeHtml_(objectType);
        var fileType = dijit.byId("createForm.fileType").getValue();
        var objectId = dijit.byId("createForm.fileId").getValue();
        dijit.byId("paymentHistoryGridId").vtReload("filesAction!getFeePaymentInfo.do?objectId=" + objectId + "&objectType=" + objectType + "&fileType=" + fileType, null, null, page.afterGetPaymentHistory);
    };

    page.afterGetPaymentHistory = function(data) {
        if (data.customInfo[0] == 1) {
            document.getElementById("receivedForm.isFeeAccept").checked = true;
        }
        else {
            document.getElementById("receivedForm.isFeeDeny").checked = true;
        }
        document.getElementById("receiveNoDiv").innerHTML = escapeHtml_(data.customInfo[1]);
        document.getElementById('fileListItemTemp').innerHTML = data.customInfo[2];
    };

    page.afterReceived = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        if (result[0] == "1") {
            onCloseApprove();
        }
    };
    page.afterReceivedMore = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        alert(result[1]);
        onCloseApprove();
    };
    page.receivedExecute = function() {
        sd.connector.post("filesAction!onReceivedFile.do?" + token.getTokenParamString(), null, "createForm", null, page.afterReceived);
    };

    page.receivedMoreExecute = function(lstObjectId) {
        sd.connector.post("filesAction!onReceivedMoreFile.do?" + token.getTokenParamString() + "&lstObjectId=" + lstObjectId, null, "createForm", null, page.afterReceivedMore);
    };

    page.received = function() {
        if (document.getElementById("receivedForm.isFeeAccept").checked == true
                || document.getElementById("receivedForm.isFeeDeny").checked == true) {
            var processDeptName = dijit.byId("receivedForm.sbProcessDeptId").attr("displayedValue");
            var processDeptId = dijit.byId("receivedForm.sbProcessDeptId").attr("value");
            dijit.byId("createForm.processDeptName").setValue(processDeptName);
            dijit.byId("createForm.processDeptId").setValue(processDeptId);
            if (document.getElementById("receivedForm.isFeeAccept").checked) {
                document.getElementById("createForm.isFee").value = "1";
            }
            if (document.getElementById("receivedForm.isFeeDeny").checked) {
                document.getElementById("createForm.isFee").value = "-1";
            }
            var clericalRequest = document.getElementById("receivedForm.clericalRequest").value;
            var receivedStatus = dijit.byId("receivedForm.Status").getValue();
            document.getElementById("createForm.clericalRequest").value = clericalRequest;
            if (processStatus == 1 && processDeptId == -1 && receivedStatus == 14) {
                alert('Bạn phải chọn đơn vị xử lý hồ sơ.');
                dijit.byId("receivedForm.sbProcessDeptId").focus();
                return false;
            }
            if (receivedStatus == 1) {
                alert('Bạn phải chọn trạng thái tiếp nhận');
            }
            else if (receivedStatus == 14) {
                msg.confirm('Bạn có chắc chắn muốn tiếp nhận hồ sơ này', '<sd:Property>confirm.title1</sd:Property>', page.receivedExecute);
                            }
                            else {
                                if (document.getElementById('createForm.clericalRequest').value == '') {
                                    alert('Bạn phải nhập nội dung từ chối, hoặc hướng dẫn bổ sung giấy tờ');
                                }
                                else {
                                    msg.confirm('Bạn có chắc chắn muốn thực hiện hành động này ?', '<sd:Property>confirm.title1</sd:Property>', page.receivedExecute);
                                                    }

                                                }
                                            }
                                            else {
                                                alert("Bạn chưa chọn Trạng thái thu phí hồ sơ");
                                            }
                                        };
</script>