<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<script type="text/javascript">
    var lstUnit = [];
    var ob;
    <c:forEach items="${lstUnit}" var="item">
    ob = {};
    ob.categoryId = ${fn:escapeXml(item.categoryId)};
    ob.name = "${fn:escapeXml(item.name)}";
    lstUnit.push(ob);
    </c:forEach>

    createSelectElement = function(id, name, value) {
        var select = document.createElement("div");
        select.setAttribute("id", id);
        for (var i = 0; i < lstUnit.length; i++) {
            var unit = lstUnit[i];
            if (unit.categoryId.toString() == value.toString()) {
                select.innerHTML = unit.name;
                break;
            }
        }
        return select;
    };

    createEmptyMainlytarget = function() {
        var row = document.createElement("tr");
        var tdIndex = document.createElement("td");
        tdIndex.setAttribute("colspan", 5);
        tdIndex.innerHTML = "Không có item nào";

        row.appendChild(tdIndex);
        document.getElementById("mainlyTargetTbl").appendChild(row);


    };

    createEmptyProducttarget = function(tableId) {
        var row = document.createElement("tr");
        var tdIndex = document.createElement("td");
        tdIndex.setAttribute("colspan", 4);
        tdIndex.innerHTML = "Không có item nào";

        row.appendChild(tdIndex);

        document.getElementById(tableId).appendChild(row);

    };

    createNewMainlyTargetRow = function(isTemp, mainlyTargetId, targetName, unitId, publishLevel) {
        if (mainlyTargetId == null) {
            mainlyTargetId = "";
        }
        if (targetName == null) {
            targetName = "";
        }
        if (publishLevel == null) {
            publishLevel = "";
        }
        if (isTemp == null) {
            isTemp = "";
        }
        targetName = escapeHtml_(targetName);
        publishLevel = escapeHtml_(publishLevel);
        targetName = stringToExponentDisplay(targetName);
        targetName = stringToExponentDisplaySupscript(targetName);
        publishLevel = stringToExponentDisplay(publishLevel);
        publishLevel = stringToExponentDisplaySupscript(publishLevel);
        isTemp = escapeHtml_(isTemp);
        var size = getRowLengthOfTable("mainlyTargetTbl") - 2;
        var preName = "createForm.lstMainlyTarget[" + size + "].";
        var row = document.createElement("tr");
        if (isTemp == 1) {
            row.style.backgroundColor = "ffff66";
        }
        var tdIndex = document.createElement("td");
        tdIndex.setAttribute("align", "center");
        tdIndex.innerHTML = size + 1;
        var tdTargetName = document.createElement("td");
        {
            var inpTargetName = document.createElement("div");
            inpTargetName.setAttribute("id", preName + "targetName");
            inpTargetName.innerHTML = targetName;
            tdTargetName.appendChild(inpTargetName);
        }
        var tdUnit = document.createElement("td");
        {
            var selectUnit = createSelectElement(preName + "unitId", preName + "unitId", unitId);
            tdUnit.appendChild(selectUnit);
        }
        var tdPublishLevel = document.createElement("td");
        {
            var inpPublishLevel = document.createElement("div");
            inpPublishLevel.setAttribute("id", preName + "publishLevel");
            inpPublishLevel.innerHTML = publishLevel;
            tdPublishLevel.appendChild(inpPublishLevel);
        }

        row.appendChild(tdIndex);
        row.appendChild(tdTargetName);
        row.appendChild(tdUnit);
        row.appendChild(tdPublishLevel);

        document.getElementById("mainlyTargetTbl").getElementsByTagName("tbody")[0].appendChild(row);
    };

    createNewRowOfTarget = function(isTemp, productTargetId, targetName, unitId, maxLevel, targetType, tableId) {
        if (productTargetId == null) {
            productTargetId = "";
        }
        if (targetName == null) {
            targetName = "";
        }
        if (maxLevel == null) {
            maxLevel = "";
        }
        if (targetType == null) {
            targetType = "";
        }
        if (isTemp == null) {
            isTemp = "";
        }

        targetName = escapeHtml_(targetName);
        maxLevel = escapeHtml_(maxLevel);
        isTemp = escapeHtml_(isTemp);
        targetType = escapeHtml_(targetType);

        targetName = stringToExponentDisplay(targetName);
        maxLevel = stringToExponentDisplay(maxLevel);
        targetType = stringToExponentDisplay(targetType);
        targetName = stringToExponentDisplaySupscript(targetName);
        maxLevel = stringToExponentDisplaySupscript(maxLevel);
        targetType = stringToExponentDisplaySupscript(targetType);

        tableId = "biologicTargetTbl";
        if (targetType == 3) {
            tableId = "chemicalTargetTbl";
        } else if (targetType == 2) {
            tableId = "heavyMetalTargetTbl";
        }

        var size = getRowLengthOfTable(tableId) - 2;

        var preName = "createForm.lstBioTarget[" + size + "].";
        if (targetType == 3) {
            preName = "createForm.lstChemical[" + size + "].";
            tableId = "chemicalTargetTbl";
        } else if (targetType == 2) {
            preName = "createForm.lstHeavyMetal[" + size + "].";
            tableId = "heavyMetalTargetTbl";
        }

        var row = document.createElement("tr");
        if (isTemp == 1) {
            row.style.backgroundColor = "ffff66";
        }
        var tdIndex = document.createElement("td");
        tdIndex.setAttribute("align", "center");
        tdIndex.innerHTML = size + 1;
        var tdTargetName = document.createElement("td");
        {
            var inpTargetName = document.createElement("div");
            inpTargetName.setAttribute("id", preName + "targetName");
            inpTargetName.setAttribute("style", "width:100%");
            inpTargetName.innerHTML = targetName;
            tdTargetName.appendChild(inpTargetName);
        }
        var tdUnit = document.createElement("td");
        {
            var selectUnit = createSelectElement(preName + "unitId", preName + "unitId", unitId);
            tdUnit.appendChild(selectUnit);
        }

        var tdMeetLevel = document.createElement("td");
        {
            var inpMeetLevel = document.createElement("div");
            inpMeetLevel.setAttribute("id", preName + "maxLevel");
            inpMeetLevel.innerHTML = maxLevel;
            inpMeetLevel.setAttribute("style", "width:100%");
            tdMeetLevel.appendChild(inpMeetLevel);
        }

        row.appendChild(tdIndex);
        row.appendChild(tdTargetName);
        row.appendChild(tdUnit);
        row.appendChild(tdMeetLevel);

        document.getElementById(tableId).getElementsByTagName("tbody")[0].appendChild(row);
    };

    createNewTarget = function(tableId, targetType) {
        createNewRowOfTarget('', '', '', null, '', targetType, tableId);
    };

</script>
<div id="productDetailDiv">
    <table class="viewTable" id="tblDetailDiv">
        <tr style="background: #f0efef">
            <td colspan="4" style="color: #15428b;font-weight: bold;padding-left: 10px">
                Thông tin chung
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Cơ quan chủ quản</sd:Label></td>
                <td width="25%">
                    <div id="createForm.governingBody" style="width:98%">${fn:escapeXml(createForm.governingBody)}</div>
            </td>
            <td width="25%"><sd:Label>Số bản công bố</sd:Label></td>
                <td width="25%">
                    <div  id="createForm.detailProduct.announcementNo" style="width:98%">${fn:escapeXml(createForm.announcement.announcementNo)}</div>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Tên tổ chức cá nhân</sd:Label></td>
                <td width="25%">
                    <div id="createForm.detailProduct.businessName" style="width:98%">${fn:escapeXml(createForm.announcement.businessName)}</div>
            </td>
            <td width="25%"><sd:Label>Tên sản phẩm</sd:Label></td>
                <td width="25%">
                    <div id="createForm.detailProduct.productName" style="width:98%">${fn:escapeXml(createForm.announcement.productName)}</div>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Nhóm sản phẩm</sd:Label></td>
                <td width="25%">
                    <div id="createForm.detailProduct.productType">${fn:escapeXml(createForm.detailProduct.productTypeName)}</div>
            </td>
            <td width="25%"><sd:Label>Số</sd:Label></td>
                <td width="25%">
                    <div id="createForm.detailProduct.productNo">${fn:escapeXml(createForm.detailProduct.productNo)}</div>
            </td>
        </tr>
        <tr style="background: #f0efef">
            <td colspan="4" style="color: #15428b;font-weight: bold;padding-left: 10px">
                Các chỉ tiêu cảm quan
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Trạng thái</sd:Label></td>
                <td width="25%">
                    <div id="createForm.detailProduct.productStatus">${fn:escapeXml(createForm.detailProduct.productStatus)}</div>
            </td>
            <td width="25%"><sd:Label>Màu sắc</sd:Label></td>
                <td width="25%">
                    <div id="createForm.detailProduct.productColor">${fn:escapeXml(createForm.detailProduct.productColor)}</div>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Mùi vị</sd:Label></td>
                <td width="25%">
                    <div id="createForm.detailProduct.productSmell">${fn:escapeXml(createForm.detailProduct.productSmell)}</div>
            </td>
            <td width="25%"><sd:Label>Trạng thái đặc trưng khác nếu có</sd:Label></td>
                <td width="25%">
                    <div id="createForm.detailProduct.productOtherStatus">${fn:escapeXml(createForm.detailProduct.productOtherStatus)}</div>
            </td>
        </tr>                     
    </table>
    <br/>
    <table id="mainlyTargetTbl" class="lstTable">
        <tr style="background: #f0efef">
            <td colspan="5" style="color: #15428b;font-weight: bold;height: 25px">
                Các chỉ tiêu chất lượng chủ yếu /Chỉ tiêu mức thôi nhiễm với sản phẩm bao bì thực phẩm
            </td>
        </tr>
        <tr class="headerRow">
            <th width="5%">STT</th>
            <th width="25%">Tên chỉ tiêu</th>
            <th width="20%">Đơn vị</th>
            <th width="25%">Mức công bố</th>
        </tr>
    </table>

    <table id="biologicTargetTbl" class="lstTable">
        <tr style="background: #f0efef">
            <td colspan="4" style="color: #15428b;font-weight: bold;height: 25px">
                Chỉ tiêu vi sinh vật
            </td>
        </tr>
        <tr class="headerRow">
            <th width="5%">STT</th>
            <th width="25%">Tên chỉ tiêu</th>
            <th width="20%">Đơn vị</th>
            <th width="45%">Mức Tối đa</th>
        </tr>
    </table>

    <table id="heavyMetalTargetTbl" class="lstTable">
        <tr style="background: #f0efef">
            <td colspan="4" style="color: #15428b;font-weight: bold;height: 25px">
                Hàm lượng kim loại nặng
            </td>
        </tr>
        <tr class="headerRow">
            <th width="5%">STT</th>
            <th width="25%">Tên chỉ tiêu</th>
            <th width="20%">Đơn vị</th>
            <th width="45%">Mức Tối đa</th>
        </tr>
    </table>

    <table id="chemicalTargetTbl" class="lstTable">
        <tr style="background: #f0efef">
            <td colspan="4" style="color: #15428b;font-weight: bold;height: 25px">
                Hàm lượng hóa chất không mong muốn
            </td>
        </tr>
        <tr class="headerRow">
            <th width="5%">STT</th>
            <th width="25%">Tên chỉ tiêu</th>
            <th width="20%">Đơn vị</th>
            <th width="45%">Mức Tối đa</th>
        </tr>                        
    </table>
    <table class="viewTable">
        <tr>
            <td colspan="4"><sd:Label>Thông tin bổ sung</sd:Label></td>
            </tr>
            <tr>
                <td colspan="4">
                    <div id="createForm.detailProduct.chemicalTargetUnwanted">${fn:escapeXml(createForm.detailProduct.chemicalTargetUnwanted)}</div>
            </td>
        </tr>
    </table>

    <table class="viewTable" id="detailDiv">

        <tr>
            <td><sd:Label>Các chỉ tiêu khác</sd:Label></td>
            </tr>
            <tr>
                <td>
                    <div id="createForm.detailProduct.otherTarget">${fn:escapeXml(createForm.detailProduct.otherTarget)}</div>
            </td>
        </tr>
        <tr>
            <td><sd:Label>Thành phần cấu tạo <font style="color:red">*</font></sd:Label></td>
            </tr>
            <tr>
                <td>
                    <div id="createForm.detailProduct.components">${fn:escapeXml(createForm.detailProduct.components)}</div>
            </td>
        </tr>
        <tr>
            <td><sd:Label>Thời hạn sử dụng <font style="color:red">*</font> </sd:Label></td>
            </tr>
            <tr>
                <td>
                    <div id="createForm.detailProduct.timeInUse">${fn:escapeXml(createForm.detailProduct.timeInUse)}</div>
            </td>
        </tr>
        <tr>
            <td><sd:Label>Công dụng</sd:Label></td>
            </tr>
            <tr>
                <td>
                    <div id="createForm.detailProduct.useage">${fn:escapeXml(createForm.detailProduct.useage)}</div>
            </td>
        </tr>
        <tr>
            <td><sd:Label>Đối tượng sử dụng</sd:Label></td>
            </tr>
            <tr>
                <td>
                    <div id="createForm.detailProduct.objectUse">${fn:escapeXml(createForm.detailProduct.objectUse)}</div>
            </td>
        </tr>
        <tr>
            <td><sd:Label>Hướng dẫn sử dụng và bảo quản<font style="color:red">*</font></sd:Label></td>
            </tr>
            <tr>
                <td>
                    <div id="createForm.detailProduct.guideline">${fn:escapeXml(createForm.detailProduct.guideline)}</div>
            </td>
        </tr>
        <tr>
            <td><sd:Label>Chất liệu bao bì và quy cách đóng gói <font style="color:red">*</font></sd:Label></td>
            </tr>
            <tr>
                <td>
                    <div id="createForm.detailProduct.packateMaterial">${fn:escapeXml(createForm.detailProduct.packateMaterial)}</div>
            </td>
        </tr>
        <tr>
            <td><sd:Label>Quy trình sản xuất</sd:Label></td>
            </tr>
            <tr>
                <td>
                    <div id="createForm.detailProduct.productionProcess">${fn:escapeXml(createForm.detailProduct.productionProcess)}</div>
            </td>
        </tr>
        <tr>
            <td><sd:Label>Các biện pháp phân biệt thật, giả (nếu có)</sd:Label></td>
            </tr>
            <tr>
                <td>
                    <div id="createForm.detailProduct.counterfeitDistinctive">${fn:escapeXml(createForm.detailProduct.counterfeitDistinctive)}</div>
            </td>
        </tr>
        <tr>
            <td><sd:Label>Xuất xứ và thương nhân chịu trách nhiệm về chất lượng hàng hóa <font style="color:red">*</font></sd:Label></td>
            </tr>
            <tr>
                <td>
                    <div id="createForm.detailProduct.origin">${fn:escapeXml(createForm.detailProduct.origin)}</div>
            </td>
        </tr>
        <tr>
            <td><sd:Label>Ngày ký</sd:Label></td>
            </tr>
            <tr>
                <td>
                    <div id="createForm.detailProduct.signDate">${fn:escapeXml(createForm.detailProduct.signDateStr)}</div>
            </td>
        </tr>
        <tr>
            <td><sd:Label>Người ký</sd:Label></td>
            </tr>
            <tr>
                <td>
                    <div id="createForm.detailProduct.signer">${fn:escapeXml(createForm.detailProduct.signer)}</div>
            </td>
        </tr>
    </table>

</div>
<script type="text/javascript">
    dojo.connect(dijit.byId('files_tab_tablist_tab.productDetail'), "onClick", loadFormFromAnnounTab = function() {
    });
    page.afterLoadMainlyTarget = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result != null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                createNewMainlyTargetRow(item.isTemp, item.mainlyTargetId, item.targetName, item.unitId, item.publishLevel);
            }
        } else {
            createEmptyMainlytarget();
        }
    };
    page.loadInitMainlyTargets = function() {
        var fileId = dijit.byId("createForm.fileId").getValue();
        if (fileId == null || fileId == "") {
            createNewMainlyTargetRow('', '', '', null, '');
        } else {
            sd.connector.post("filesAction!loadMainlyTarget.do?createForm.fileId=" + fileId, null, null, null, page.afterLoadMainlyTarget);
        }
    };

    page.afterProductTarget = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result != null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                createNewRowOfTarget(item.isTemp, item.productTargetId, item.targetName, item.unitId, item.maxLevel, item.targetType, "")
            }
        }

        var tableId = "biologicTargetTbl";
        var size = getRowLengthOfTable(tableId) - 2;
        if (size.toString() == "0") {
            createEmptyProducttarget("biologicTargetTbl");
        }

        tableId = "heavyMetalTargetTbl";
        size = getRowLengthOfTable(tableId) - 2;
        if (size.toString() == "0") {
            createEmptyProducttarget("heavyMetalTargetTbl");
        }

        tableId = "chemicalTargetTbl";
        size = getRowLengthOfTable(tableId) - 2;
        if (size.toString() == "0") {
            createEmptyProducttarget("chemicalTargetTbl");
        }
    };

    page.loadInitProductTargets = function() {
        var fileId = dijit.byId("createForm.fileId").getValue();
        if (fileId == null || fileId == "") {
            createNewTarget('biologicTargetTbl', 1);
            createNewTarget('heavyMetalTargetTbl', 2);
            createNewTarget('chemicalTargetTbl', 3);
        } else {
            sd.connector.post("filesAction!loadProductTarget.do?createForm.fileId=" + fileId, null, null, null, page.afterProductTarget);
        }
    };
    page.replaceNewLineByBr = function() {
        var table = document.getElementById("detailDiv");
        var divs = table.getElementsByTagName("div");
        var i = 0;
        var content = "";
        for (i = 0; i < divs.length; i++) {
            content = divs[i].innerHTML;
            content = content.replace(/\n/g, "<br>");
            divs[i].innerHTML = content;
        }
    };
    
    page.replaceNewLineByBrTblDetailDiv = function() {
        var tblDetailDiv = document.getElementById("tblDetailDiv");
        var tblDetailDivs = tblDetailDiv.getElementsByTagName("div");
        for (i = 0; i < tblDetailDivs.length; i++) {
            content = tblDetailDivs[i].innerHTML;
            content = content.replace(/\n/g, "<br>");
            tblDetailDivs[i].innerHTML = content;
        }
    };
    
    page.loadInitMainlyTargets();
    page.loadInitProductTargets();
    page.replaceNewLineByBr();
    page.replaceNewLineByBrTblDetailDiv();

    replaceExponentByShift6("productDetailDiv");
    replaceExponentByShift6("tabProductDetailDiv");
    replaceSubscript("productDetailDiv");
    replaceSubscript("tabProductDetailDiv");
    page.checkModifiredTabProductDetail = function() {
        var detailProductId = '${fn:escapeXml(createFormClone.detailProduct.detailProductId)}';
        if (detailProductId != null && detailProductId > 0) {

            //announcement.announcementNo
            var a = '${fn:escapeXml(createFormOriginal.announcement.announcementNo)}';
            var b = '${fn:escapeXml(createFormClone.announcement.announcementNo)}';

            if (a != b) {
                document.getElementById("createForm.detailProduct.announcementNo").style.color = "#ff0000";
            }
            //            createForm.detailProduct.businessName
            a = '${fn:escapeXml(createFormOriginal.announcement.businessName)}';
            b = '${fn:escapeXml(createFormClone.announcement.businessName)}';

            if (a != b) {
                document.getElementById("createForm.detailProduct.businessName").style.color = "#ff0000";
            }
//        announcement.productName
            a = '${fn:escapeXml(createFormOriginal.announcement.productName)}';
            b = '${fn:escapeXml(createFormClone.announcement.productName)}';

            if (a != b) {
                document.getElementById("createForm.detailProduct.productName").style.color = "#ff0000";
            }
//        detailProduct.productTypeName
            a = '${fn:escapeXml(createFormOriginal.detailProduct.productTypeName)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.productTypeName)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.productType").style.color = "#ff0000";
            }
//        detailProduct.productNo
            a = '${fn:escapeXml(createFormOriginal.detailProduct.productNo)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.productNo)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.productNo").style.color = "#ff0000";
            }
//        detailProduct.productStatus
            a = '${fn:escapeXml(createFormOriginal.detailProduct.productStatus)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.productStatus)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.productStatus").style.color = "#ff0000";
            }
//        detailProduct.productColor
            a = '${fn:escapeXml(createFormOriginal.detailProduct.productColor)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.productColor)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.productColor").style.color = "#ff0000";
            }
//        detailProduct.productSmell
            a = '${fn:escapeXml(createFormOriginal.detailProduct.productSmell)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.productSmell)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.productSmell").style.color = "#ff0000";
            }
//        detailProduct.productOtherStatus
            a = '${fn:escapeXml(createFormOriginal.detailProduct.productOtherStatus)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.productOtherStatus)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.productOtherStatus").style.color = "#ff0000";
            }
//        detailProduct.otherTarget
            a = '${fn:escapeXml(createFormOriginal.detailProduct.otherTarget)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.otherTarget)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.otherTarget").style.color = "#ff0000";
            }
//        detailProduct.components
            a = '${fn:escapeXml(createFormOriginal.detailProduct.components)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.components)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.components").style.color = "#ff0000";
            }
//        detailProduct.timeInUse
            a = '${fn:escapeXml(createFormOriginal.detailProduct.timeInUse)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.timeInUse)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.timeInUse").style.color = "#ff0000";
            }
//        detailProduct.useage
            a = '${fn:escapeXml(createFormOriginal.detailProduct.useage)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.useage)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.useage").style.color = "#ff0000";
            }
//        detailProduct.objectUse
            a = '${fn:escapeXml(createFormOriginal.detailProduct.objectUse)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.objectUse)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.objectUse").style.color = "#ff0000";
            }
//        detailProduct.guideline
            a = '${fn:escapeXml(createFormOriginal.detailProduct.guideline)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.guideline)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.guideline").style.color = "#ff0000";
            }
//        detailProduct.packateMaterial
            a = '${fn:escapeXml(createFormOriginal.detailProduct.packateMaterial)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.packateMaterial)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.packateMaterial").style.color = "#ff0000";
            }
//        detailProduct.productionProcess
            a = '${fn:escapeXml(createFormOriginal.detailProduct.productionProcess)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.productionProcess)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.productionProcess").style.color = "#ff0000";
            }
//        detailProduct.counterfeitDistinctive
            a = '${fn:escapeXml(createFormOriginal.detailProduct.counterfeitDistinctive)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.counterfeitDistinctive)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.counterfeitDistinctive").style.color = "#ff0000";
            }
//        detailProduct.origin
            a = '${fn:escapeXml(createFormOriginal.detailProduct.origin)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.origin)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.origin").style.color = "#ff0000";
            }
//        detailProduct.signDateStr
            a = '${fn:escapeXml(createFormOriginal.detailProduct.signDate)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.signDate)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.signDate").style.color = "#ff0000";
            }
//        detailProduct.signer
            a = '${fn:escapeXml(createFormOriginal.detailProduct.signer)}';
            b = '${fn:escapeXml(createFormClone.detailProduct.signer)}';
            if (a != b) {
                document.getElementById("createForm.detailProduct.signer").style.color = "#ff0000";
            }
        }
    };
    page.checkModifiredTabProductDetail();
</script>
