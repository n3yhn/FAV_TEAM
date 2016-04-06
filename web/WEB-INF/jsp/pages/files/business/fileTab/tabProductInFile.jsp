<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<style>
    .editable p {
        margin-top: 0px;
    }
</style>


<script type="text/javascript">

    page.convertDateToString = function(dgDate) {
        var year = dgDate.getYear() + 1900;
        var month = dgDate.getMonth() + 1;
        var date = dgDate.getDate();
        if (month < 10) {
            month = "0" + month;
        }
        if (date < 10) {
            date = "0" + date;
        }
        var str = date + "/" + month + "/" + year;
        return str;
    }

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
//        productName = escapeHtml_(productName);
//        component = escapeHtml_(component);
//        mainlyTarget = escapeHtml_(mainlyTarget);
//        manufacture = escapeHtml_(manufacture);
//        deadline = escapeHtml_(deadline);
//        packageRecipe = escapeHtml_(packageRecipe);
//        forProduct = escapeHtml_(forProduct);
//        useFor = escapeHtml_(useFor);

        var size = getRowLengthOfTable(tableId) - 1;
        var preName = "createForm.lstProductInFile[" + size + "].";
        var row = document.createElement("tr");
        var tdIndex = document.createElement("td");
        tdIndex.setAttribute("align", "center");
        tdIndex.innerHTML = size + 1;
        var tdProductName = document.createElement("td");
        {
            var inpProductName = document.createElement("input");
            inpProductName.setAttribute("type", "text");
            inpProductName.setAttribute("name", preName + "productName");
            inpProductName.setAttribute("id", preName + "productName");
            inpProductName.setAttribute("style", "width:100%");
            inpProductName.setAttribute("value", productName);
            inpProductName.setAttribute("maxlength", "200");
            tdProductName.appendChild(inpProductName);

            var inpProductInFileId = document.createElement("input");
            inpProductInFileId.setAttribute("type", "hidden");
            inpProductInFileId.setAttribute("name", preName + "productInFileId");
            inpProductInFileId.setAttribute("id", preName + "productInFileId");
            inpProductInFileId.setAttribute("value", productInFileId);
            tdProductName.appendChild(inpProductInFileId);
        }
        var tdComponent = document.createElement("td");
        {
            var inpComponent = document.createElement("input");
            inpComponent.setAttribute("type", "text");
            inpComponent.setAttribute("name", preName + "component");
            inpComponent.setAttribute("id", preName + "component");
            inpComponent.setAttribute("value", component);
            inpComponent.setAttribute("style", "width:100%");
            inpComponent.setAttribute("maxlength", "200");
            tdComponent.appendChild(inpComponent);
        }

        var tdMainlyTarget = document.createElement("td");
        {
            var inpMainlyTarget = document.createElement("input");
            inpMainlyTarget.setAttribute("type", "text");
            inpMainlyTarget.setAttribute("name", preName + "mainlyTarget");
            inpMainlyTarget.setAttribute("id", preName + "mainlyTarget");
            inpMainlyTarget.setAttribute("value", mainlyTarget);
            inpMainlyTarget.setAttribute("style", "width:100%");
            inpMainlyTarget.setAttribute("maxlength", "200");
            tdMainlyTarget.appendChild(inpMainlyTarget);
        }

        var tdManufacture = document.createElement("td");
        {
            var inpManufacture = document.createElement("input");
            inpManufacture.setAttribute("type", "text");
            inpManufacture.setAttribute("name", preName + "manufacture");
            inpManufacture.setAttribute("id", preName + "manufacture");
            inpManufacture.setAttribute("value", manufacture);
            inpManufacture.setAttribute("style", "width:100%");
            inpManufacture.setAttribute("maxlength", "200");
            tdManufacture.appendChild(inpManufacture);
        }


        var tdDeadline = document.createElement("td");
        {
            var inpDeadline = document.createElement("input");
            inpDeadline.setAttribute("type", "text");
            inpDeadline.setAttribute("name", preName + "deadline");
            inpDeadline.setAttribute("id", preName + "deadline");
            inpDeadline.setAttribute("value", deadline);
            inpDeadline.setAttribute("style", "width:100%");
            inpDeadline.setAttribute("maxlength", "200");
            tdDeadline.appendChild(inpDeadline);
        }

        var tdPackageRecipe = document.createElement("td");
        {
            var inpPackageRecipe = document.createElement("input");
            inpPackageRecipe.setAttribute("type", "text");
            inpPackageRecipe.setAttribute("name", preName + "packageRecipe");
            inpPackageRecipe.setAttribute("id", preName + "packageRecipe");
            inpPackageRecipe.setAttribute("value", packageRecipe);
            inpPackageRecipe.setAttribute("style", "width:100%");
            inpPackageRecipe.setAttribute("maxlength", "200");
            tdPackageRecipe.appendChild(inpPackageRecipe);
        }
        var tdUseFor = document.createElement("td");
        {
            var inpUseFor = document.createElement("input");
            inpUseFor.setAttribute("type", "text");
            inpUseFor.setAttribute("name", preName + "useFor");
            inpUseFor.setAttribute("id", preName + "useFor");
            inpUseFor.setAttribute("value", useFor);
            inpUseFor.setAttribute("style", "width:100%");
            inpUseFor.setAttribute("maxlength", "200");
            tdUseFor.appendChild(inpUseFor);
        }
        var tdDelete = document.createElement("td");
        {
            var img = document.createElement("img");
            img.src = 'share/images/icons/13.png';
            img.setAttribute("width", '20px');
            img.setAttribute("height", '20px');
            img.setAttribute("title", 'Xóa tài liệu');
            img.setAttribute("onclick", "page.deleteProductInFile(this.parentNode.parentNode);");
            tdDelete.appendChild(img);
        }

        row.appendChild(tdIndex);
        row.appendChild(tdProductName);
        row.appendChild(tdComponent);
        row.appendChild(tdMainlyTarget);
        row.appendChild(tdManufacture);
        row.appendChild(tdDeadline);
        row.appendChild(tdPackageRecipe);
        row.appendChild(tdUseFor);
        //row.appendChild(tdForProduct);

        row.appendChild(tdDelete);
        document.getElementById(tableId).appendChild(row);
    };

</script>
<sd:FieldSet key="Thông tin chung">
    <table class="editTable">
        <tr>
            <td width="25%"><sd:Label>Cơ quan chủ quản</sd:Label></td>
                <td width="25%">
                    <div id="createForm.detailProduct.agencyName" style="width:98%">${fn:escapeXml(agencyName)}</div>
            </td>
            <td width="25%"><sd:Label>Số bản công bố</sd:Label></td>
                <td width="25%">
                    <div  id="createForm.detailProduct.announcementNo" style="width:98%"></div>
                </td>
            </tr>
            <tr>
                <td width="25%"><sd:Label>Tên tổ chức cá nhân</sd:Label></td>
                <td width="25%">
                    <div id="createForm.detailProduct.businessName" style="width:98%"></div>
                </td>
                <td width="25%" style="display: "><sd:Label>Số</sd:Label></td>
                <td width="25%" style="display: ">
                <sd:TextBox key="" 
                            id="createForm.detailProduct.productNo" 
                            name="createForm.detailProduct.productNo" 
                            maxlength="255" 
                            cssStyle="width:98%"
                            trim="true"/>
            </td>
        </tr>
    </table>
</sd:FieldSet>
<br/>
<sd:FieldSet key="Thông tin danh sách sản phẩm">
    <table id="productInFileTbl" class="lstTable">
        <tr class="headerRow">
            <th width="5%">TT</th>
            <th width="15%">Tên sản phẩm<font style="color:red">*</font></th>
            <th width="10%">Thành phần cấu tạo</th>
            <th width="10%">Chỉ tiêu chất lượng, an toàn</th>
            <th width="10%">Tên hãng sản xuất, tên nước</th>
            <th width="10%">Thời hạn sử dụng</th>
            <th width="10%">Quy cách bao gói</th>
            <th width="10%">Ghi chú</th>
            <th width="5%">Xóa</th>
        </tr>
    </table>
    <div>
        <sx:ButtonAddCategory onclick="createNewRowOfProductInFile('','','','','','','','','','productInFileTbl');"/>
    </div>
</sd:FieldSet>


<script type="text/javascript">

    page.renameElementOfProductInFile = function() {
        var preName = "createForm.lstProductInFile[";
        var table = document.getElementById("productInFileTbl");
        var rows = table.getElementsByTagName("tr");
        var i = 0;
        for (i = 1; i < rows.length; i++) {
            var tds = rows[i].getElementsByTagName("td");
            tds[0].innerHTML = i;
            var inputs = rows[i].getElementsByTagName("input");
            inputs[0].setAttribute("name", preName + (i - 1) + "].productName");
            inputs[0].setAttribute("id", preName + (i - 1) + "].productName");
            inputs[1].setAttribute("name", preName + (i - 1) + "].productInFileId");
            inputs[1].setAttribute("id", preName + (i - 1) + "].productInFileId");
            inputs[2].setAttribute("name", preName + (i - 1) + "].component");
            inputs[2].setAttribute("id", preName + (i - 1) + "].component");
            inputs[3].setAttribute("name", preName + (i - 1) + "].mainlyTarget");
            inputs[3].setAttribute("id", preName + (i - 1) + "].mainlyTarget");
            inputs[4].setAttribute("name", preName + (i - 1) + "].manufacture");
            inputs[4].setAttribute("id", preName + (i - 1) + "].manufacture");
            inputs[5].setAttribute("name", preName + (i - 1) + "].deadline");
            inputs[5].setAttribute("id", preName + (i - 1) + "].deadline");
            inputs[6].setAttribute("name", preName + (i - 1) + "].packageRecipe");
            inputs[6].setAttribute("id", preName + (i - 1) + "].packageRecipe");
//            inputs[7].setAttribute("name", preName + (i - 1) + "].forProduct");
//            inputs[7].setAttribute("id", preName + (i - 1) + "].forProduct");
            inputs[7].setAttribute("name", preName + (i - 1) + "].useFor");
            inputs[7].setAttribute("id", preName + (i - 1) + "].useFor");
        }
    }

    page.deleteProductInFile = function(rowElement) {
        msg.confirm("Bạn có chắc muốn xóa không ?", "Xác nhận xóa", function() {
            rowElement.parentNode.removeChild(rowElement);
            page.renameElementOfProductInFile();
        });
    }

    page.validateProductInFileData = function() {
        var tabs = dijit.byId("files_tab");
        var panel = dijit.byId("tab.productInFile");
        tabs.selectChild(panel);
        var table = document.getElementById("productInFileTbl");
        var rows = table.getElementsByTagName("tr");
        var i = 0;
        for (i = 1; i < rows.length; i++) {
            var inputs = rows[i].getElementsByTagName("input");
            var content = inputs[0].value.trim();
            inputs[0].value = content;
            if (content == null || content == "") {
                alert("[Tên sản phẩm dòng " + (i) + "] chưa nhập");
                inputs[0].focus();
                return false;
            } else if (content.length > 200) {
                alert("[Tên sản phẩm dòng " + (i) + "] dài hơn 200 ký tự");
                inputs[0].focus();
                return false;
            }

            content = inputs[2].value.trim();
            inputs[2].value = content;
            if (content.length > 200) {
                alert("[Thành phần cấu tạo dòng " + (i - 1) + "] dài hơn 200 ký tự");
                inputs[1].focus();
                return false;
            }

            content = inputs[3].value.trim();
            inputs[3].value = content;
            if (content.length > 200) {
                alert("[Chỉ tiêu chất lượng, an toàn dòng " + (i - 1) + "] dài hơn 200 ký tự");
                inputs[3].focus();
                return false;
            }

            content = inputs[4].value.trim();
            inputs[4].value = content;
            if (content.length > 200) {
                alert("[Tên hãng sản xuất, tên nước dòng " + (i - 1) + "] dài hơn 200 ký tự");
                inputs[4].focus();
                return false;
            }
            content = inputs[5].value.trim();
            inputs[5].value = content;
            if (content.length > 200) {
                alert("[Thời hạn sử dụng dòng " + (i - 1) + "] dài hơn 200 ký tự");
                inputs[5].focus();
                return false;
            }

            content = inputs[6].value.trim();
            inputs[6].value = content;
            if (content.length > 200) {
                alert("[Quy cách bao gói dòng " + (i - 1) + "] dài hơn 200 ký tự");
                inputs[6].focus();
                return false;
            }

            content = inputs[7].value.trim();
            inputs[7].value = content;
            if (content.length > 200) {
                alert("[Sản phẩm sử dụng " + (i - 1) + "] dài hơn 200 ký tự");
                inputs[7].focus();
                return false;
            }
        }
        return true;
    }

    page.validateProductInFile = function() {

        if (!page.validateProductInFileData()) {
            return false;
        }

        return true;

    }

    page.afterLoadProductInfile = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result != null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                createNewRowOfProductInFile(item.productInFileId, item.productName, item.component, item.mainlyTarget, item.manufacture, item.deadline, item.packageRecipe, item.forProduct, item.useFor, 'productInFileTbl');
            }
        }
    }

    page.loadInitProductInFile = function() {
        var fileId = dijit.byId("createForm.fileId").getValue();
        if (fileId == null || fileId == "") {
            createNewRowOfProductInFile('', '', '', '', '', '', '', '', '', 'productInFileTbl');
        } else {
            sd.connector.post("filesAction!loadProductInFiles.do?createForm.fileId=" + fileId, null, null, null, page.afterLoadProductInfile);
        }
    }
    page.loadInitProductInFile();
    
    dojo.connect(dijit.byId('files_tab_tablist_tab.productInFile'), "onClick", loadFormFromAnnounTab = function() {
        //document.getElementById("createForm.detailProduct.assessmentMethod").innerHTML = dijit.byId("createForm.announcement.businessAddress").getValue();
        document.getElementById("createForm.detailProduct.businessName").innerHTML = escapeHtml_(dijit.byId("createForm.announcement.businessName").getValue());
        document.getElementById("createForm.detailProduct.announcementNo").innerHTML = escapeHtml_(dijit.byId("createForm.announcement.announcementNo").getValue());
    });
</script>