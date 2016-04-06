<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<script type="text/javascript">

    createNewRowOfQualityControl = function(qualityControlPlanId, productProcessDetail, controlTarget, technicalRegulation, patternFrequence, testDevice, testMethod, noteForm, note, tableId, isTemp) {
        if (qualityControlPlanId == null) {
            qualityControlPlanId = "";
        }
        if (productProcessDetail == null) {
            productProcessDetail = "";
        }
        if (controlTarget == null) {
            controlTarget = "";
        }
        if (technicalRegulation == null) {
            technicalRegulation = "";
        }
        if (patternFrequence == null) {
            patternFrequence = "";
        }
        if (testDevice == null) {
            testDevice = "";
        }
        if (testMethod == null) {
            testMethod = "";
        }
        if (noteForm == null) {
            noteForm = "";
        }
        if (note == null) {
            note = "";
        }
        if (isTemp == null) {
            isTemp = "";
        }
        productProcessDetail = escapeHtml_(productProcessDetail);
        controlTarget = escapeHtml_(controlTarget);
        technicalRegulation = escapeHtml_(technicalRegulation);
        patternFrequence = escapeHtml_(patternFrequence);
        testDevice = escapeHtml_(testDevice);
        testMethod = escapeHtml_(testMethod);
        noteForm = escapeHtml_(noteForm);
        note = escapeHtml_(note);
        isTemp = escapeHtml_(isTemp);

        var size = getRowLengthOfTable(tableId) - 2;
        var preName = "createForm.lstQualityControl[" + size + "].";
        var row = document.createElement("tr");
        if (isTemp == 1) {
            row.style.backgroundColor = "ffff66";
        }

        var tdIndex = document.createElement("td");
        tdIndex.innerHTML = size + 1;
        var tdProductProcessDetail = document.createElement("td");
        {
            var inpProductProcessDetail = document.createElement("div");
            inpProductProcessDetail.setAttribute("id", preName + "productProcessDetail");
            inpProductProcessDetail.innerHTML = productProcessDetail;
            tdProductProcessDetail.appendChild(inpProductProcessDetail);

        }
        var tdControlTarget = document.createElement("td");
        {
            var inpControlTarget = document.createElement("div");
            inpControlTarget.setAttribute("id", preName + "controlTarget");
            inpControlTarget.innerHTML = controlTarget;
            tdControlTarget.appendChild(inpControlTarget);
        }

        var tdTechnicalRegulation = document.createElement("td");
        {
            var inpTechnicalRegulation = document.createElement("div");
            inpTechnicalRegulation.setAttribute("id", preName + "technicalRegulation");
            inpTechnicalRegulation.innerHTML = technicalRegulation;
            tdTechnicalRegulation.appendChild(inpTechnicalRegulation);
        }

        var tdPatternFrequence = document.createElement("td");
        {
            var inpPatternFrequence = document.createElement("div");
            inpPatternFrequence.setAttribute("id", preName + "patternFrequence");
            inpPatternFrequence.innerHTML = patternFrequence;
            tdPatternFrequence.appendChild(inpPatternFrequence);
        }


        var tdTestDevice = document.createElement("td");
        {
            var inpTestDevice = document.createElement("div");
            inpTestDevice.setAttribute("id", preName + "testDevice");
            inpTestDevice.innerHTML = testDevice;
            tdTestDevice.appendChild(inpTestDevice);
        }

        var tdTestMethod = document.createElement("td");
        {
            var inpTestMethod = document.createElement("div");
            inpTestMethod.setAttribute("id", preName + "testMethod");
            inpTestMethod.innerHTML = testMethod;
            tdTestMethod.appendChild(inpTestMethod);
        }

        var tdNoteForm = document.createElement("td");
        {
            var inpNoteForm = document.createElement("div");
            inpNoteForm.setAttribute("id", preName + "noteForm");
            inpNoteForm.innerHTML = noteForm;
            tdNoteForm.appendChild(inpNoteForm);
        }

        var tdNote = document.createElement("td");
        {
            var inpNote = document.createElement("div");
            inpNote.setAttribute("id", preName + "note");
            inpNote.innerHTML = note;
            tdNote.appendChild(inpNote);
        }


        row.appendChild(tdIndex);
        row.appendChild(tdProductProcessDetail);
        row.appendChild(tdControlTarget);
        row.appendChild(tdTechnicalRegulation);
        row.appendChild(tdPatternFrequence);
        row.appendChild(tdTestDevice);
        row.appendChild(tdTestMethod);
        row.appendChild(tdNoteForm);
        row.appendChild(tdNote);

        document.getElementById(tableId).appendChild(row);
    };

</script>
<table class="viewTable">
    <tr style="background: #f0efef">
        <td colspan="4" style="color: #15428b;font-weight: bold;padding-left: 10px">
            Chỉ áp dụng đối với sản phẩm của cơ sở sản xuất trong nước có chứng chỉ hệ thống quản lý chất lượng tiên tiến: GMP, HACCP, ISO 22000 hoặc tương đương        
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label>Tên tổ chức, cá nhân</sd:Label></td>
            <td width="25%">
                <div id="createForm.qualityControlPlan.businessName" style="width:98%">${fn:escapeXml(createForm.announcement.businessName)}</div>
        </td>
        <td width="25%"><sd:Label>Địa chỉ</sd:Label></td>
            <td width="25%">
                <div id="createForm.qualityControlPlan.address" style="width:98%">${fn:escapeXml(createForm.announcement.businessAddress)}</div>
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label>Tên sản phẩm</sd:Label></td>
            <td width="25%">
                <div id="createForm.qualityControlPlan.productName" style="width:98%">${fn:escapeXml(createForm.announcement.productName)}</div>
        </td>
        <td width="25%" colspan="2"/>
    </tr>
    <tr>
        <td width="25%"><sd:Label>Ngày ký</sd:Label></td>
            <td width="25%">
                <div id="createForm.qualityControlPlan.signDate" style="width:98%">${fn:escapeXml(createForm.announcement.publishDateStr)}</div>
        </td>
        <td width="25%"><sd:Label>Người ký</sd:Label></td>
            <td width="25%">
                <div id="createForm.qualityControlPlan.signer" style="width:98%">${fn:escapeXml(createForm.announcement.signer)}</div>
        </td>
    </tr>
</table>
<table id="qualityControlTbl" class="lstTable">
    <tr style="background: #f0efef">
        <td colspan="10" style="color: #15428b;font-weight: bold;padding-left: 10px; height: 25px">
            Kế hoạch kiểm soát chất lượng
        </td>
    </tr>
    <tr class="headerRow">
        <th width="5%">STT</th>
        <th width="15%">Các quá trình SX</th>
        <th width="10%">Các chỉ tiêu kiểm soát</th>
        <th width="10%">Quy định kỹ thuật</th>
        <th width="10%">Tần xuất lấy mẫu/cỡ mẫu</th>
        <th width="10%">Thiết bị thử nghiệm/ kiểm tra</th>
        <th width="10%">Phương pháp thử/ kiểm tra</th>
        <th width="10%">Biểu ghi chép</th>
        <th width="15%">Ghi chú</th>
    </tr>
</table>
<div>
</div>

<script type="text/javascript">

    dojo.connect(dijit.byId('files_tab_tablist_tab.qualityControlPlan'), "onClick", loadFormFromAnnounTab = function() {
        //        document.getElementById("createForm.qualityControlPlan.businessName").innerHTML = dijit.byId("createForm.announcement.businessName").getValue();
        //        document.getElementById("createForm.qualityControlPlan.address").innerHTML = dijit.byId("createForm.announcement.businessAddress").getValue();
        //        document.getElementById("createForm.qualityControlPlan.productName").innerHTML = dijit.byId("createForm.announcement.productName").getValue();
        //        document.getElementById("createForm.qualityControlPlan.signDate").innerHTML = dijit.byId("createForm.announcement.publishDate").getValue();
        //        document.getElementById("createForm.qualityControlPlan.signer").innerHTML = dijit.byId("createForm.announcement.signer").getValue();
    });

    createEmptyQualityControl = function() {
        var row = document.createElement("tr");
        var tdIndex = document.createElement("td");
        tdIndex.setAttribute("colspan", 9);
        tdIndex.innerHTML = "Không có item nào";

        row.appendChild(tdIndex);

        document.getElementById("qualityControlTbl").appendChild(row);

    };

    page.afterLoadQualityControl = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result != null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                createNewRowOfQualityControl(item.qualityControlPlanId, item.productProcessDetail, item.controlTarget, item.technicalRegulation, item.patternFrequence, item.testDevice, item.testMethod, item.noteForm, item.note, 'qualityControlTbl', item.isTemp);
            }
        } else {
            createEmptyQualityControl();
        }
    };

    page.loadInitQualityControl = function() {
        var fileId = dijit.byId("createForm.fileId").getValue();
        if (fileId == null || fileId == "") {
            createNewRowOfQualityControl('', '', '', '', '', '', '', '', '', 'qualityControlTbl','');
        } else {
            sd.connector.post("filesAction!loadQualityControls.do?createForm.fileId=" + fileId, null, null, null, page.afterLoadQualityControl);
        }
    };
    page.loadInitQualityControl();

</script>
