<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<script>

    createNewRowOfAttach = function (attachId, categoryName, attachDes, attachName, tableId, isTemp, objectType) {
        if (attachId == null) {
            attachId = "";
        }
        if (categoryName == null) {
            categoryName = "";
        }
        if (attachDes == null) {
            attachDes = "";
        }
        if (attachName == null) {
            attachName = "";
        }
        if (isTemp == null) {
            isTemp = "";
        }
        if (objectType == null) {
            objectType = 0;
        }
        categoryName = escapeHtml_(categoryName);
        attachDes = escapeHtml_(attachDes);
        attachName = escapeHtml_(attachName);
        isTemp = escapeHtml_(isTemp);
        objectType = escapeHtml_(objectType);
        var size = getRowLengthOfTable(tableId) - 1;
        var index = size + 1;
        var preName = "createForm.lstAttachs[" + size + "].";
        //
        // check xem co ton tai row voi id va name nhu the ko
        //
        do {
            var item = document.getElementById(preName + "categoryName");
            if (item == null)
                break;
            size = size + 1;
            preName = "createForm.lstAttachs[" + size + "].";
        } while (true);

        var row = document.createElement("tr");
//        if (isTemp == 1) {
//            row.style.backgroundColor = "ffff66";
//        }
        var tdIndex = document.createElement("td");
        tdIndex.setAttribute("align", "center");
        tdIndex.innerHTML = index;
        var tdCategoryName = document.createElement("td");
        {
            var inpCategoryName = document.createElement("div");
            inpCategoryName.setAttribute("id", preName + "categoryName");
            inpCategoryName.setAttribute("style", "width:100%");
            inpCategoryName.innerHTML = categoryName;
            tdCategoryName.appendChild(inpCategoryName);
        }
        var tdAttachDes = document.createElement("td");
        {
            var inpAttachDes = document.createElement("div");
            inpAttachDes.setAttribute("id", preName + "attachDes");
            inpAttachDes.innerHTML = attachDes;
            inpAttachDes.setAttribute("style", "width:100%");
            tdAttachDes.appendChild(inpAttachDes);
        }

        var tdLink = document.createElement("td");
        {
            var aLink = document.createElement("a");
            aLink.id = "lnk" + size;
            if (attachId !== null && attachId !== "") {
                aLink.href = 'uploadiframe!openFile.do?attachId=' + attachId;
            }
            if (attachName !== null && attachName !== "") {
                aLink.innerHTML = attachName;
            }
            tdLink.appendChild(aLink);
        }
        var tdLable = document.createElement("td");
        {
            tdLable.setAttribute("align", "center");
            var imgLable = document.createElement("img");
            imgLable.id = 'imgLable' + size;
            if (objectType == 17)
            {
                imgLable.setAttribute("width", '20px');
                imgLable.setAttribute("height", '20px');
                imgLable.src = 'share/images/icons/approve.png';
                imgLable.setAttribute("title", 'Nhãn sản phẩm/RNI');
            } else {
                imgLable.setAttribute("width", '0px');
                imgLable.setAttribute("height", '0px');
                imgLable.src = '';
                imgLable.setAttribute("title", 'Tài liệu đính kèm');
            }

            tdLable.appendChild(imgLable);
        }

        row.appendChild(tdIndex);
        row.appendChild(tdCategoryName);
        row.appendChild(tdAttachDes);
        row.appendChild(tdLink);
        row.appendChild(tdLable);
        document.getElementById(tableId).appendChild(row);

        /*
         * Xu ly su kien cho uploader
         */
    };

    uploadNewRow = function (attachName, attachId, id) {
        var a = document.getElementById("lnk" + id);
        a.innerHTML = attachName;
        a.href = 'uploadiframe!openFile.do?attachId=' + attachId;
        var attach = document.getElementById("createForm.lstAttachs[" + id + "].attachId");
        attach.value = attachId;
    };
</script>
<div id="lstFileUpload"></div>
<br/>
<table id="attachTbl" class="lstTable">
    <tr class="headerRow">
        <th width="5%">STT</th>
        <th width="20%">Tên tài liệu</th>
        <th width="40%">Mô tả tài liệu</th>
        <th width="20%">Link tài liệu</th>
        <th width="5%">Nhãn sản phẩm/RNI</th>
    </tr>
</table>
<div>
</div>

<script>

    createEmptyAttachs = function () {
        var row = document.createElement("tr");
        var tdIndex = document.createElement("td");
        tdIndex.setAttribute("colspan", 5);
        tdIndex.innerHTML = "Không có item nào";

        row.appendChild(tdIndex);

        document.getElementById("attachTbl").appendChild(row);

    };

    page.afterLoadAttach = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result != null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                createNewRowOfAttach(item.attachId, item.categoryName, item.attachDes, item.attachName, 'attachTbl', item.isTemp, item.objectType);
            }
        } else {
            createEmptyAttachs();
        }
    };

    page.loadInitAttachs = function () {
        var fileLst = '${fn:escapeXml(fileList)}';
        fileLst = fileLst.replace(new RegExp("nl", "g"), "<br/>");
        document.getElementById("lstFileUpload").innerHTML = fileLst;
        var fileId = dijit.byId("createForm.fileId").getValue();
        if (fileId == null || fileId == "") {
            createNewRowOfAttach('', '', '', "", 'attachTbl', '');
        } else {
            sd.connector.post("filesAction!loadAttachs.do?createForm.fileId=" + fileId, null, null, null, page.afterLoadAttach);
        }
    };
    page.loadInitAttachs();

</script>