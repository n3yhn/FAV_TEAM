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
        var item = dijit.byId("feePaymentInfoGridId").getItem(inData - 1);
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
<sd:TitlePane key="paymentHistory.paymentHistoryList" id="paymentTitle">
    <div id="feePaymentInfoGridDiv" style="width:100%;">
        <sd:DataGrid clientSort="true" 
                     id="feePaymentInfoGridId"
                     style="width:auto;height:auto"
                     getDataUrl="" 
                     container="feePaymentInfoGridDiv" 
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
<form id="returnFilesForm" name="returnFilesForm">
    <sx:ResultMessage id="resultMessage" />
    <table width="100%" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="25%">
                <input type="hidden" id="commentForm.objectId" name="commentForm.objectId"/>
                <input type="hidden" id="commentForm.objectType" name="commentForm.objectType" value="30"/>

            </td>
            <td width="75%"></td>
        </tr>
        <tr>
            <td style="text-align: left"><sd:Label key="Trạng thái thu phí HS:"/></td>
            <td>
                <input type="radio" id="returnFilesForm.isFeeAccept" name="createForm.isFee" value="1" disabled="true"/>
                <sd:Label key="Phí đã thu đầy đủ"/>
                </br>
                <input type="radio" id="returnFilesForm.isFeeDeny" name="createForm.isFee" value="-1" disabled="true"/>
                <sd:Label key="Chưa nộp phí"/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Ý kiến của văn thư" required="true"/></td>
            <td>
                <sd:Textarea key="" id="returnFilesForm.clericalRequest" name="createForm.clericalRequest" rows="4" cssStyle="width:99%" maxlength="1800" trim="true"></sd:Textarea>
                </td>
            </tr>
            <tr id='fileList' style='display: none;'>
            <b><td width="25%" style="text-align: right"><sd:Label key="Bổ sung giấy tờ"/></td></b>
        </tr>
        <tr>
            <td style="text-align: right"><sd:Label key="Trả giấy công bố tại" required="true"/></td>
            <td width="90%" id=''>
                <div id="recipientAddress0" style="display: none;" >
                    <sd:Label key="Nhận tại cục" required="true"/>
                </div>
                <div id="recipientAddress1"  style="display: none;" >
                    <div id="returnFilesForm.recipientAddress1" style="width:98%"></div>
                </div>
            </td>
        </tr>

        <tr>
            <td colspan="2" style="text-align: center">
                <sd:Button id="btnReturnedFiles" key="" onclick="page.returnFiles();" cssStyle="display:">
                    <img src="${contextPath}/share/images/icons/Answer.png" height="14" width="18">
                    <span style="font-size:12px">Trả hồ sơ</span>
                </sd:Button>
                <sx:ButtonBack onclick="page.onCloseReturnFilesDlg();"/>  
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">

    page.showReturnFilesDlg = function() {
        var id =${createForm.fileId};
        getFeePaymentInfo(id, "30");
        var dlg = dijit.byId("returnFilesDlg");
        dlg.show();
    };
    getFeePaymentInfo = function(objectId, objectType) {
        document.getElementById("commentForm.objectId").value = escapeHtml_(objectId);
        document.getElementById("commentForm.objectType").value = escapeHtml_(objectType);
        dijit.byId("feePaymentInfoGridId").vtReload("filesAction!getFeePaymentInfoForReturnFiles.do?objectId=" + objectId + "&objectType=" + objectType, null, null, page.afterGetFeePaymentInfo);
    };
    page.afterGetFeePaymentInfo = function(data) {
        var nullContent = "";
        document.getElementById("returnFilesForm.clericalRequest").value = nullContent;
        if (data.customInfo[0] == 1) {
            document.getElementById("returnFilesForm.isFeeAccept").checked = true;
        } else {
            document.getElementById("returnFilesForm.isFeeDeny").checked = true;
            dijit.byId("btnReturnedFiles").domNode.style.display = "none";
        }
        var divRecipientAddress0 = document.getElementById('recipientAddress0');
        var divRecipientAddress1 = document.getElementById('recipientAddress1');
        if (data.customInfo[1] == 1) {
            document.getElementById("returnFilesForm.recipientAddress1").innerHTML = data.customInfo[2];
            divRecipientAddress1.style.display = '';
            divRecipientAddress0.style.display = 'none';
        } else {
            divRecipientAddress1.style.display = 'none';
            divRecipientAddress0.style.display = '';
        }
    };
    page.afterFilesExecute = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {

        } else {
            backPage();
        }
    };

    page.returnFiles = function() {
        msg.confirm('Bạn có chắc chắn thực hiện trả hồ sơ này?', '<sd:Property>confirm.title1</sd:Property>', page.returnFilesExecute);
            };
            page.returnFilesExecute = function() {
                sd.connector.post("filesAction!onReturnFilesOldFlow.do?" + token.getTokenParamString(), null, "createForm", null, page.afterFilesExecute);
            };
            page.onCloseReturnFilesDlg = function() {
                var dlg = dijit.byId("returnFilesDlg");
                dlg.hide();
            };
</script>