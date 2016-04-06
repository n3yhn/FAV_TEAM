<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<script type="text/javascript">

    createNewRowOfProductInFile = function(productInFileId, productName, component, mainlyTarget, manufacture, deadline, packageRecipe, forProduct, useFor, tableId) {
        if (productInFileId == null) {
            productInFileId = "";
        }
        if (productName == null) {
            productName = "";
        }
        if (component == null) {
            component = "";
        }
        if (mainlyTarget == null) {
            mainlyTarget = "";
        }
        if (manufacture == null) {
            manufacture = "";
        }
        if (deadline == null) {
            deadline = "";
        }
        if (packageRecipe == null) {
            packageRecipe = "";
        }
        if (forProduct == null) {
            forProduct = "";
        }
        if (useFor == null) {
            useFor = "";
        }
        productName = escapeHtml_(productName);
        component = escapeHtml_(component);
        mainlyTarget = escapeHtml_(mainlyTarget);
        manufacture = escapeHtml_(manufacture);
        deadline = escapeHtml_(deadline);
        packageRecipe = escapeHtml_(packageRecipe);
        useFor = escapeHtml_(useFor);
        var size = getRowLengthOfTable(tableId) - 1;
        var row = document.createElement("tr");

        var tdIndex = document.createElement("td");
        tdIndex.setAttribute("align", "center");
        tdIndex.innerHTML = size + 1;

        var tdProductName = document.createElement("td");
        tdProductName.innerHTML = productName;

        var tdComponent = document.createElement("td");
        tdComponent.innerHTML = component;

        var tdMainlyTarget = document.createElement("td");
        tdMainlyTarget.innerHTML = mainlyTarget;

        var tdManufacture = document.createElement("td");
        tdManufacture.innerHTML = manufacture;

        var tdDeadline = document.createElement("td");
        tdDeadline.innerHTML = deadline;

        var tdPackageRecipe = document.createElement("td");
        tdPackageRecipe.innerHTML = packageRecipe;

        var tdUseFor = document.createElement("td");
        tdUseFor.innerHTML = useFor;
        
        row.appendChild(tdIndex);
        row.appendChild(tdProductName);
        row.appendChild(tdComponent);
        row.appendChild(tdMainlyTarget);
        row.appendChild(tdManufacture);
        row.appendChild(tdDeadline);
        row.appendChild(tdPackageRecipe);
        row.appendChild(tdUseFor);
        document.getElementById(tableId).appendChild(row);
    };

</script>
<div id="announcementDiv">
<sd:FieldSet key="Thông tin chung">
    <table class="editTable">
        <tr>
            <td width="25%"><sd:Label>Cơ quan chủ quản</sd:Label></td>
                <td width="25%">
                    <div id="createForm.detailProduct.agencyName" >${fn:escapeXml(agencyName)}</div>
            </td>
            <td width="25%"><sd:Label>Số bản công bố</sd:Label></td>
                <td width="25%">
                     <div id="createForm.announcement.announcementNo"> ${fn:escapeXml(createForm.announcement.announcementNo)} </div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Tên tổ chức cá nhân</sd:Label></td>
                <td width="25%">
                   <div  id="createForm.announcement.businessName"> ${fn:escapeXml(createForm.announcement.businessName)} </div>
                </td>
                <td width="25%" style="display: "><sd:Label>Số</sd:Label></td>
                <td width="25%">
                     <div  id="createForm.detailProduct.productNo"> ${fn:escapeXml(createForm.detailProduct.productNo)} </div>
                </td>
        </tr>
    </table>
</sd:FieldSet>
    </br>
<sd:FieldSet key="Thông tin danh sách chi tiết">
    <table id="productInFileTbl" class="lstTable">
        <tr class="headerRow">
            <th width="5%">TT</th>
            <th width="15%">Tên sản phẩm</th>
            <th width="10%">Thành phần cấu tạo</th>
            <th width="10%">Chỉ tiêu chất lượng, an toàn</th>
            <th width="10%">Tên hãng sản xuất, tên nước</th>
            <th width="10%">Thời hạn sử dụng</th>
            <th width="10%">Quy cách bao gói</th>
            <th width="10%">Ghi chú</th>
        </tr>
    </table>
</sd:FieldSet>
</div>

<script type="text/javascript">

    page.afterLoadProductInfile = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result != null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                createNewRowOfProductInFile(item.productInFileId, item.productName, item.component, item.mainlyTarget, item.manufacture, item.deadline, item.packageRecipe, item.forProduct,item.useFor, 'productInFileTbl');
            }
        }
    }

    page.loadInitProductInFile = function() {
        var fileId = dijit.byId("createForm.fileId").getValue();
        if (fileId == null || fileId == "") {
            createNewRowOfQualityControl('', '', '', '', '', '', '', '', '','', 'qualityControlTbl');
        } else {
            sd.connector.post("filesAction!loadProductInFiles.do?createForm.fileId=" + fileId, null, null, null, page.afterLoadProductInfile);
        }
    }
    page.loadInitProductInFile();
</script>