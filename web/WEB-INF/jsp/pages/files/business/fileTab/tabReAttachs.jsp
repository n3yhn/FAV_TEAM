<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="tags" tagdir="/WEB-INF/tags" %>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<script type="text/javascript">
    var labelType = ".pdf";
    function upclick(d) {
        var g = {
            element: null,
            action: "about:blank",
            frameRow: "0",
            action_params: {},
            maxsize: 0,
            onstart: null,
            oncomplete: null,
            dataname: "Filedata",
            target: null,
            zindex: "auto"
        };
        for (var h in g)
            d[h] = d[h] ? d[h] : g[h];
        var k = d.element;
        if (typeof k == "string")
            k = document.getElementById(k);
        var e = k.ownerDocument, b, c = e.createElement("div"), n = "frame" + (new Date).getTime().toString().substr(8);
        c.innerHTML = '<iframe name="' + n + '" src="about:blank" onload="this.onload_callback()"></iframe>';
        var i = c.childNodes[0];
        i.onload_callback =
                function() {
                    var a = e.createElement("form");
                    c.appendChild(a);
                    a.method = "post";
                    a.id = "formUpload" + d.frameRow;
                    a.enctype = "multipart/form-data";
                    a.encoding = "multipart/form-data";
                    if (d.target) {
                        a.target = d.target;
                        a.setAttribute("target", d.target);
                    } else {
                        a.target = n;
                        a.setAttribute("target", n);
                    }
                    a.action = d.action;
                    a.setAttribute("action", d.action);
                    a.style.margin = 0;
                    a.style.padding = 0;
                    a.style.height = "80px";
                    a.style.width = "40px";
                    a.runat = "server";
                    var j = d.action_params;
                    for (var p in j) {
                        var m = e.createElement("input");
                        m.type = "hidden";
                        m.name = p;
                        m.value = String(j[p]);
                        a.appendChild(m);
                    }
                    if (d.maxsize) {
                        j = e.createElement("input");
                        j.type = "hidden";
                        j.name = "MAX_FILE_SIZE";
                        j.value = String(d.maxsize);
                        a.appendChild(j);
                    }

                    b = e.createElement("input");
                    b.name = d.dataname;
                    b.id = d.id;
                    b.type = "file";
                    b.size = "1";
                    b.runat = "server";
                    a.appendChild(b);
                    b.style.position = "absolute";
                    b.style.display = "block";
                    b.style.top = 0;
                    b.style.left = 0;
                    b.style.height = a.style.height;
                    b.style.width = "80px";
                    b.style.opacity = 0;
                    b.style.filter = "alpha(opacity=0)";
                    b.style.fontSize = 8;
                    b.style.zIndex = 1;
                    b.style.visiblity = "hidden";
                    b.style.marginLeft = "-40px";
                    var o = function() {
                        if (b.value) {
                            var f = d.onstart;
                            if (f && f(b.value, d.uploadId, d.loadingId))
                                a.submit();
                        }
                    };
                    var s = function() {
                        if (b.value) {
                            var f = d.onselectfile;
                            f && f(b.value);
                            if (isValid)
                                a.submit();
                        }
                    }
                    if (b.addEventListener)
                        b.addEventListener("change", o, false);
                    else if (b.attachEvent)
                        b.attachEvent("onpropertychange", function(f) {
                            if (!f)
                                f = window.event;
                            f.propertyName == "value" && o()
                        });
                    else
                        b.onpropertychange = o;
                    i.onload_callback = function() {
                        var f = null;
                        if (i.contentWindow)
                            f = i.contentWindow;
                        else if (i.contentDocument)
                            f = i.contentDocument.defaultView;
                        f = f.document.body.innerHTML;
                        var q = d.oncomplete;
                        q && q(f, d.loadingId);
                        a.reset();
                    }
                };
        i.style.display = "none";
        i.width = 0;
        i.height = 0;
        i.marginHeight = 0;
        i.marginWidth = 0;
        e.body.insertBefore(c, e.body.firstChild);
        c.style.position = "absolute";
        c.style.overflow = "hidden";
        c.style.padding = 0;
        c.style.margin = 0;
        c.style.visiblity = "hidden";
        c.style.width = "0px";
        c.style.height = "0px";
        if (d.zindex == "auto") {
            g = 0;
            var l;
            for (h = k; h.tagName != "BODY"; ) {
                l = h.currentStyle ? h.currentStyle : getComputedStyle(h, null);
                l = parseInt(l.zIndex);
                l = isNaN(l) ? 0 : l;
                g += l + 1;
                h = h.parentNode;
            }
            c.style.zIndex = g;
        } else
            c.style.zIndex = d.zindex;
        g = function(a) {
            if (!a)
                a =
                        window.event;
            c.style.width = "0px";
            c.style.height = "0px";
            if (e.elementFromPoint(a.clientX, a.clientY) == k) {
                c.style.width = "40px";
                c.style.height = "80px";
            }
        };
        if (c.addEventListener)
            c.addEventListener("mousemove", g, false);
        else
            c.attachEvent && c.attachEvent("onmousemove", g);
        g = function(a) {
            if (!a)
                a = window.event;
            var j = y = 0;
            if (a.pageX)
                j = a.pageX;
            else if (a.clientX)
                j = a.clientX + (e.documentElement.scrollLeft ? e.documentElement.scrollLeft : e.body.scrollLeft);
            if (a.pageY)
                y = a.pageY;
            else if (a.clientY)
                y = a.clientY + (e.documentElement.scrollTop ?
                        e.documentElement.scrollTop : e.body.scrollTop);
            c.style.left = j - 20 + "px";
            c.style.top = y - 40 + "px";
            c.style.width = "40px";
            c.style.height = "80px";
        };
        if (k.addEventListener)
            k.addEventListener("mousemove", g, false);
        else
            k.attachEvent && k.attachEvent("onmousemove", g);
    }

    page.getFileExtension = function(fileName) {
        var ext = /^.+\.([^.]+)$/.exec(fileName);
        return ext == null ? "" : ext[1];
    };
    page.isValidExt = function(fileName) {

        var ext = page.getFileExtension(fileName);
        ext = ext.toString().toLowerCase();
        var allExt = ".pdf,.xls,.xlsx,.doc,.docx,.ppt,.pptx,.jpg,.png,.bmp,.jpeg";
        if (allExt.toLowerCase().indexOf(ext) == -1) {
            msg.alert("File đã chọn không đúng định dạng cho phép (" + allExt + ")", "<sd:Property>confirm.title</sd:Property>");
            return false;
        } else {
            return true;
        }
    }

    page.isValidSize = function(size) {
        var maxSizeMB = 20;
        if (size / 1024 / 1024 > maxSizeMB) {
            msg.alert("File đã chọn dung lượng không được vượt quá 20 MB", "<sd:Property>confirm.title</sd:Property>");
            return false;
        }
        return true;
    };
    createNewRowOfAttach = function(attachId, categoryName, attachDes, attachPath, attachName, originalId, tableId, allowDelete, objectType) {

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

        // Start create new row -DuND
        var row = document.createElement("tr");
        var tdIndex = document.createElement("td");
        tdIndex.setAttribute("align", "center");
        tdIndex.innerHTML = index;
        var tdCategoryName = document.createElement("td");
        {
            // Create Name
            var inpCategoryName = document.createElement("input");
            inpCategoryName.setAttribute("type", "text");
            inpCategoryName.setAttribute("name", preName + "categoryName");
            inpCategoryName.setAttribute("id", preName + "categoryName");
            inpCategoryName.setAttribute("style", "width:100%;");
            inpCategoryName.setAttribute("value", categoryName);
            inpCategoryName.setAttribute("maxlength", "250");
            tdCategoryName.appendChild(inpCategoryName);

            // Create Id - invisiable
            var inpAttachId = document.createElement("input");
            inpAttachId.setAttribute("type", "hidden");
            inpAttachId.setAttribute("name", preName + "attachId");
            inpAttachId.setAttribute("id", preName + "attachId");
            inpAttachId.setAttribute("value", attachId);
            tdCategoryName.appendChild(inpAttachId);
        }

        // Create Description
        var tdAttachDes = document.createElement("td");
        {
            var inpAttachDes = document.createElement("input");
            inpAttachDes.setAttribute("type", "text");
            inpAttachDes.setAttribute("name", preName + "attachDes");
            inpAttachDes.setAttribute("id", preName + "attachDes");
            inpAttachDes.setAttribute("value", attachDes);
            inpAttachDes.setAttribute("style", "width:100%");
            inpAttachDes.setAttribute("maxlength", "250");
            tdAttachDes.appendChild(inpAttachDes);
        }

        // Create Link download
        var tdLink = document.createElement("td");
        {
            var aLink = document.createElement("a");
            aLink.id = "lnk" + size;
            if (attachId != null && attachId != "") {
                aLink.href = 'uploadiframe!openFile.do?attachId=' + attachId;
            } else if (originalId != null && originalId != "") {
                aLink.href = 'uploadiframe!openFile.do?attachId=' + originalId;
            }

            if (attachName != null && attachName != "") {
                aLink.innerHTML = attachName;
            }
            tdLink.appendChild(aLink);

            var inpAttachPath = document.createElement("input");
            inpAttachPath.setAttribute("type", "hidden");
            inpAttachPath.setAttribute("name", preName + "attachPath");
            inpAttachPath.setAttribute("id", preName + "attachPath");
            inpAttachPath.setAttribute("value", attachPath);
            tdLink.appendChild(inpAttachPath);
            var inpAttachName = document.createElement("input");
            inpAttachName.setAttribute("type", "hidden");
            inpAttachName.setAttribute("name", preName + "attachName");
            inpAttachName.setAttribute("id", preName + "attachName");
            inpAttachName.setAttribute("value", attachName);
            tdLink.appendChild(inpAttachName);
            var inpOriginalId = document.createElement("input");
            inpOriginalId.setAttribute("type", "hidden");
            inpOriginalId.setAttribute("name", preName + "originalId");
            inpOriginalId.setAttribute("id", preName + "originalId");
            if(originalId != null){
                inpOriginalId.setAttribute("value", originalId);
            }
            tdLink.appendChild(inpOriginalId);
        }

        var tdUploadAtt = document.createElement("td");
        {
            tdUploadAtt.setAttribute("align", "center");
            var imgUpload = document.createElement("img");
            imgUpload.id = 'browse' + size;
            imgUpload.src = 'share/images/icons/browse2.png';
            imgUpload.setAttribute("title", 'Xóa tài liệu');
            imgUpload.onclick = "";
            tdUploadAtt.appendChild(imgUpload);

            var imgLoading = document.createElement("img");
            imgLoading.id = 'imgLoading' + size;
            imgLoading.src = 'share/images/loading/loading2.gif';
            imgLoading.setAttribute("width", '29px');
            imgLoading.setAttribute("height", '29px');
            imgLoading.setAttribute("style", 'display: none');
            tdUploadAtt.appendChild(imgLoading);
//                                    var button = document.createElement('button');
//                                    button.innerHTML = 'Hồ sơ riêng';
//                                    button.onclick = function() {
//                                        var dlg = dijit.byId("receivedDlg");
//                                        dlg.show();
//                                    };
//                                    tdUploadAtt.appendChild(button);
        }


        // Create Label button
        var tdLabel = document.createElement("td");
        {
            tdLabel.setAttribute("align", "center");
            var inpLabelCheckbox = document.createElement("input");

            inpLabelCheckbox.setAttribute("type", "checkbox");
            inpLabelCheckbox.setAttribute("title", "Chú ý nhãn đính kèm cần đúng định dạng: '" + labelType + "'");
            inpLabelCheckbox.setAttribute("name", preName + "Label");
            inpLabelCheckbox.onchange = function() {
                if (inpLabelCheckbox.checked == true)
                    msg.alert("Chú ý: cần tích chọn đủ nhãn chính và nhãn phụ hoặc bảng thông tin RNI (nếu có) để thực hiện xuất và in kèm hồ sơ về sau! (Chú ý chỉ tích hồ sơ là nhãn hoặc RNI nếu tích sai hồ sơ sẽ bị trả lại)", "Cảnh báo")
            };
            inpLabelCheckbox.id = 'label' + size;
            tdLabel.appendChild(inpLabelCheckbox);
            if (objectType !== null && objectType !== '' && objectType == '17')
                inpLabelCheckbox.setAttribute("checked", true);
        }

        // Create delete button
        var tdDeleteAtt = document.createElement("td");
        {
            tdDeleteAtt.setAttribute("align", "center");
            if (allowDelete == 1)
            {
                var img = document.createElement("img");
                img.src = 'share/images/icons/13.png';
                img.setAttribute("width", '20px');
                img.setAttribute("height", '20px');
                img.setAttribute("title", 'Xóa tài liệu');
                img.setAttribute("onclick", "page.deleteAttachs(this.parentNode.parentNode);");
                tdDeleteAtt.appendChild(img);
            }
        }

        // Add td to tr row
        row.appendChild(tdIndex);
        row.appendChild(tdCategoryName);
        row.appendChild(tdAttachDes);
        row.appendChild(tdLink);
        row.appendChild(tdUploadAtt);
        row.appendChild(tdLabel);
        row.appendChild(tdDeleteAtt);
        document.getElementById(tableId).appendChild(row);

        /*
         * Xu ly su kien cho uploader
         */
        var uploader = document.getElementById('browse' + size);
        upclick(
                {
                    element: uploader,
                    action: 'uploadiframe!uploadFile.do?' + token.getTokenParamString() + '&id=' + size,
                    frameRow: size,
                    maxsize: 20,
                    id: 'upload' + size,
                    uploadId: 'upload' + size,
                    loadingId: 'imgLoading' + size,
                    onstart: function(filename, uploadId, loadingId)
                    {
                        var isValid = false;
                        var file = document.getElementById(uploadId);
                        var files = file.files;
                        if (!files) {//for IE
                            if (file.size > 0) {
                                var fileName = file.value;
                                if (!page.isValidExt(fileName))
                                    return false;
                            }
                        } else {
                            if (document.getElementById(uploadId).files.length > 0) {
                                var fileName = document.getElementById(uploadId).files[0].name;
                                //alert(fileName);
                                if (!page.isValidExt(fileName))
                                    return false;
                                var size = document.getElementById(uploadId).files[0].size;
                                if (!page.isValidSize(size))
                                    return false;
                            }
                        }
                        document.getElementById(loadingId).style.display = '';
                        return true;
                    },
                    oncomplete: function(result, loadingId) {
                        document.getElementById(loadingId).style.display = 'none';
                    }
                });
    };

    uploadNewRow = function(attachName, attachId, id) {
        var a = document.getElementById("lnk" + id);
        a.innerHTML = attachName;
        a.href = 'uploadiframe!openFile.do?attachId=' + attachId;
        var attach = document.getElementById("createForm.lstAttachs[" + id + "].attachId");
        attach.value = attachId;
        var uploadForm = document.getElementById("formUpload" + id);
        uploadForm.action = 'uploadiframe!uploadFile.do?' + token.getTokenParamString() + '&id=' + id;
    };
    </script>
    <div id="lstFileUpload"></div>
    <br/>
    <div style="color:red;font-weight: bold" id="lstFileRequire"></div>
    <br/>
    <table id="uattachTbl" class="lstTable">
        <tr style="background: #f0efef">
            <td colspan="4" style="color: #15428b;font-weight: bold;padding-left: 10px; height: 25px">
                Danh sách hồ sơ pháp lý của doanh nghiệp
            </td>
        </tr>
        <tr><td><tags:MutipleSelect disabled="false" id="lstUserAttach" name="lstUserAttach" data="${lstUserAttach}"  allowCode="false" actionWhenNoItem="page.noItem()"/></td></tr>              
</table>
<div id="divUserAttach" style="width: 90%">   
</div>

<sd:Button id="btnUserAttach" key="" onclick="page.userAttachsDlg();" cssStyle="display:none" cssClass="buttonGroup">
    <img src="${contextPath}/share/images/icons/DownArrow.png" height="14" width="18">
    <span style="font-size:12px">Nhập từ hồ sơ pháp lý</span>
    <input type="text" id="createForm.lstAttachLabel" name="createForm.lstAttachLabel" style="display: none" />
</sd:Button>
<br/>
<br/>

<div style="width: 90%;color: red;">  
    <label style="color: red;font-weight: bold;text-decoration: underline">Khuyến cáo:</label>
    <br/>
    - Doanh nghiệp thực hiện tải tài liệu đính kèm theo thứ tự trên (để thứ tự chính xác bạn cần Click chọn tệp đính kèm theo thứ tự trên)
    <br/>
    - Doanh nghiệp bắt buộc phải tải lên và đánh dấu các tài liệu đính kèm là nhãn để in cùng hồ sơ hoặc bảng thông tin RNI nếu muốn in đính kèm chi tiết hồ sơ. Định dạng tài liệu yêu cầu: .pdf (Chú ý chỉ tích hồ sơ là nhãn hoặc RNI nếu tích sai hồ sơ sẽ bị trả lại)
    <br/>
    - Tên tài liệu yêu cầu rõ ràng.
    <br/>
    - Các tài liệu ko nêu rõ chỉ cần bản sao, yêu cầu Doanh nghiệp scan bản gốc.
    <br/>
    - Tất cả các loại tài liệu yêu cầu Doanh nghiệp scan màu, rõ ràng, có thể đọc được, đặc biệt nhãn in ra cần đọc được nếu không hồ sơ sẽ cần SĐBS để cập nhật lại nhãn.
    <br/>
    - Các loại tài liệu theo chuẩn sau: .pdf, .xls, .xlsx, .doc, .docx, .ppt, .pptx, .jpg, .png, .bmp, .jpeg;
    <br/>
    - Dung lượng tài liệu đính kèm giới hạn 20M.
    - Dung lượng nhãn/RNI đính kèm giới hạn 3M (Chú ý nếu dung lượng vượt quá quy định hồ sơ sẽ bị trả lại)
</div>

<br/>
<table id="attachTbl" class="lstTable">
    <tr class="headerRow">
        <th width="5%">STT</th>
        <th width="20%">Tên tài liệu<font style="color:red">*</font></th>
        <th width="25%">Mô tả tài liệu</th>
        <th width="20%">Tải về</th>
        <th width="10%">Đính kèm tệp<font style="color:red">*</font></th>
        <th width="5%">Nhãn sản phẩm/RNI(Nếu có)</th>
        <th width="5%">Xóa</th>
    </tr>
</table>
<div>
    <sx:ButtonAddCategory id="btnAddCategory" onclick="createNewRowOfAttach('','','','','','','attachTbl',1,'');"/>
</div>
<script type="text/javascript">
    //140626-binhnt53

    page.showBtnUserAttach = function() {
        var result = dijit.byId("createForm.countUA").getValue();
        if (result == 1) {
            dijit.byId("btnUserAttach").domNode.style.display = "";
        } else {
            dijit.byId("btnUserAttach").domNode.style.display = "none";
        }
    };
    
    //140626-binhnt53
    page.renameAttachIndexs = function() {
        var table = document.getElementById("attachTbl");
        var rows = table.getElementsByTagName("tr");
        var i = 0;
        for (i = 1; i < rows.length; i++) {
            var tds = rows[i].getElementsByTagName("td");
            tds[0].innerHTML = i;
        }
    };
    
    page.renameElementOfAttachs = function() {
        var preName = "createForm.lstAttachs[";
        var table = document.getElementById("attachTbl");
        var rows = table.getElementsByTagName("tr");
        var i = 0;
        for (i = 1; i < rows.length; i++) {
            var tds = rows[i].getElementsByTagName("td");
            tds[0].innerHTML = i;
            var inputs = rows[i].getElementsByTagName("input");
            inputs[0].setAttribute("name", preName + (i - 1) + "].categoryName");
            inputs[0].setAttribute("id", preName + (i - 1) + "].categoryName");
            inputs[1].setAttribute("name", preName + (i - 1) + "].attachId");
            inputs[1].setAttribute("id", preName + (i - 1) + "].attachId");
            inputs[2].setAttribute("name", preName + (i - 1) + "].attachDes");
            inputs[2].setAttribute("id", preName + (i - 1) + "].attachDes");
            inputs[3].setAttribute("name", preName + (i - 1) + "].attachPath");
            inputs[3].setAttribute("id", preName + (i - 1) + "].attachPath");
            inputs[4].setAttribute("name", preName + (i - 1) + "].attachName");
            inputs[4].setAttribute("id", preName + (i - 1) + "].attachName");
            inputs[5].setAttribute("name", preName + (i - 1) + "].originalId");
            inputs[5].setAttribute("id", preName + (i - 1) + "].originalId");
            inputs[6].setAttribute("name", preName + (i - 1) + "].Label");
            //inputs[6].setAttribute("id", preName + (i - 1) + "].Label");
        }
    };
    
    page.deleteAttachs = function(rowElement) {
        msg.confirm("Bạn có chắc muốn xóa tài liệu không?", "Xóa tài liệu", function() {
            rowElement.parentNode.removeChild(rowElement);
            page.renameAttachIndexs();
        });
    };
    
    page.validateAttachData = function() {
        var countValidate = page.checkCountFile();
        var tabs = dijit.byId("files_tab");
        var panel = dijit.byId("tab.attachs");
        tabs.selectChild(panel);
        var table = document.getElementById("attachTbl");
        var rows = table.getElementsByTagName("tr");

        // Validate Count
        if (rows.length == 1)
        {
            msg.alert("Bạn chưa tải tài liệu đính kèm", "Cảnh báo");
            dijit.byId("btnAddCategory").focus();
            return false;
        }
        var numRow = rows.length - 1;
        if (numRow < countValidate)
        {
            msg.alert("Số lượng tài liệu đính kèm chưa đầy đủ, hồ sơ yêu cầu " + countValidate + " loại tài liệu", "Cảnh báo");
            dijit.byId("btnAddCategory").focus();
            return false;
        }

        var i = 0;
        for (i = 1; i < rows.length; i++) {
            var inputs = rows[i].getElementsByTagName("input");
            var content = inputs[0].value;
            if (content == null || content.trim().length == 0) {
                alert("[Tên tài liệu dòng " + i + "] chưa nhập");
                inputs[0].focus();
                return false;
            } else {
                if (content.trim().lenght >= 250) {
                    alert("[Tên tài liệu dòng " + i + "] quá 250 ký tự");
                    inputs[0].focus();
                    return false;
                }
            }
            var attachId = inputs[1].value;
            if (attachId == null || attachId == "") {
                attachId = inputs[5].value;
                if (attachId == null || attachId == "") {
                    alert("[Tài liệu dòng " + i + "] chưa upload file");
                    inputs[0].focus();
                    return false;
                }
            }
            var contentDes = inputs[2].value;
            if (contentDes.trim().lenght >= 250) {
                alert("[Mô tả tài liệu dòng " + i + "] quá 250 ký tự");
                inputs[0].focus();
                return false;
            }
            inputs[0].value = content.trim();
            inputs[2].value = contentDes.trim();
        }

        //
        var j = 0;
        var k = 0;
        for (j = 1; j < rows.length - 1; j++) {
            var input1 = rows[j].getElementsByTagName("input");
            var content1 = input1[0].value;
            for (k = j + 1; k < rows.length; k++) {
                var inputs2 = rows[k].getElementsByTagName("input");
                var content2 = inputs2[0].value;
                if (content1 == content2) {
                    alert("[Tên tài liệu dòng " + j + "] và  [Tên tài liệu dòng " + k + "] bị trùng");
                    inputs2[0].focus();
                    return false;
                }
            }
        }

        try
        {
            // Check Label exist
            var checked = false;
            for (j = 1; j < rows.length; j++) {
                var rowAttachLable = rows[j].getElementsByTagName("input");
                var lnk = rowAttachLable[6];
                if (lnk != null && lnk.checked)
                {
                    checked = true;
                    break;
                }
            }

            if (checked == false) {
                /*
                alert("Bạn chưa chọn 'nhãn' cho sản phẩm. Yêu cầu Doanh nghiệp đánh dấu đủ Nhãn bao gồm: Nhãn chính, Nhãn phụ(nếu có) & RNI(nếu có) - Chú ý chỉ tích chọn tài liệu đính kèm là nhãn hoặc RNI nếu tích sai hồ sơ sẽ bị trả lại).");
                dijit.byId("btnAddCategory").focus();
                return false;
                */
            }
            var lstAttachLabel = "";
            for (j = 1; j < rows.length; j++) {

                var rowAttachLableType = rows[j].getElementsByTagName("input");
                var lnk = rowAttachLableType[6];
                if (lnk != null && lnk.checked)
                {
                    var rowAttachLableLnk = rows[j].getElementsByTagName("a");
                    var lnk = rowAttachLableLnk[0];
                    var fileName = lnk.textContent;
                    var fileNameSplit = fileName.toString().split('.');
                    var fileType = "";
                    if (fileNameSplit.length > 1)
                        fileType = "." + fileNameSplit[fileNameSplit.length - 1];
                    if ((fileType != '') && (labelType.indexOf(fileType.toString().toLowerCase()) >= 0))
                    {
                        lstAttachLabel += (j - 1) + ";";
                    }
                    else
                    {
                        document.getElementById("createForm.lstAttachLabel").value = "";
                        alert("Tài liệu đính kèm dòng thứ [" + j + "] chưa đúng định dạng: '" + labelType + "'");
                        var inputFocus = rows[j].getElementsByTagName("input");
                        inputFocus[0].focus();
                        return false;
                    }
                }
            }
            document.getElementById("createForm.lstAttachLabel").value = lstAttachLabel;
        }
        catch (ex)
        {
            alert("Có lỗi xảy ra khi kiểm tra dữ liệu, chi tiết: " + ex);
            return false;
        }

        return true;
    };

    //
    // ham set attach label trong truong hop luu tam, khong validate
    //
    page.setAttachLabel = function() {
        var table = document.getElementById("attachTbl");
        var rows = table.getElementsByTagName("tr");
        var j;
        var lstAttachLabel = "";
        for (j = 1; j < rows.length; j++) {

            var rowAttachLableType = rows[j].getElementsByTagName("input");
            var lnk = rowAttachLableType[6];
            if (lnk != null && lnk.checked)
            {
                var rowAttachLableLnk = rows[j].getElementsByTagName("a");
                var lnk = rowAttachLableLnk[0];
                var fileName = lnk.textContent;
                var fileNameSplit = fileName.toString().split('.');
                var fileType = "";
                if (fileNameSplit.length > 1)
                    fileType = "." + fileNameSplit[fileNameSplit.length - 1];
                if ((fileType != '') && (labelType.indexOf(fileType.toString().toLowerCase()) >= 0))
                {
                    lstAttachLabel += (j - 1) + ";";
                }
            }
        }
        document.getElementById("createForm.lstAttachLabel").value = lstAttachLabel;

        page.renameElementOfAttachs();
    };

    page.validateAttach = function() {
        if (!page.validateAttachData()) {
            return false;
        }
        return true;
    };
    page.afterLoadAttach = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result != null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                createNewRowOfAttach(item.attachId, item.categoryName, item.attachDes, item.attachPath, item.attachName, '', 'attachTbl', 1, item.objectType);
            }
        } else
        {
            //page.checkFileList1();
        }
    };

    page.afterLoadAttachCopy = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result != null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                createNewRowOfAttach("", item.categoryName, item.attachDes, item.attachPath, item.attachName, item.attachId, 'attachTbl', 1, item.objectType);
            }
        } else {
            //page.checkFileList1();
        }
    };

    page.loadInitAttachs = function() {
        var fileLst = '${fn:escapeXml(fileList)}';
        fileLst = fileLst.replace(new RegExp("nl", "g"), "<br/>");
        //checkFileList = fileLst;
        document.getElementById("lstFileUpload").innerHTML = fileLst;
        var fileId = dijit.byId("createForm.fileId").getValue();
        if (fileId == null || fileId == "") {
            //createNewRowOfAttach('', '', '', "", 'attachTbl');
            //page.checkFileList1();
        } else {
            sd.connector.post("filesAction!loadAttachs.do?createForm.fileId=" + fileId, null, null, null, page.afterLoadAttach);
        }
    };

    page.loadInitCopyAttachs = function() {
        var fileLst = '${fn:escapeXml(fileList)}';
        fileLst = fileLst.replace(new RegExp("nl", "g"), "<br/>");
        //checkFileList = fileLst;
        document.getElementById("lstFileUpload").innerHTML = fileLst;
        var fileId = dijit.byId("createForm.fileId").getValue();
        if (fileId == null || fileId == "") {
            //createNewRowOfAttach('', '', '', "", 'attachTbl');
            //page.checkFileList1();
        } else {
            sd.connector.post("filesAction!loadAttachs.do?createForm.fileId=" + fileId, null, null, null, page.afterLoadAttachCopy);
        }
    };

    page.checkCountFile = function()
    {
        var count = 0;
        var checkFileList = '${fn:escapeXml(fileList)}';
        var res = checkFileList.split("nl");
        for (var i = 0; i < res.length; i++)
        {
            if (res[i].indexOf("*)") > 0)
            {
                //createNewRowOfAttach('', res[i].replace("(*)", ""), '', '', 'attachTbl', 0);
                //checkFileList= checkFileList.substring(checkFileList.indexOf('nl'));
                count++;
            }
        }
        return count;
    };

    var isCopy = false;
    try {
        isCopy = "${isCopy}";
    } catch (en) {
    }
    if (isCopy != "true") {
        page.loadInitAttachs();
    } else {
        page.loadInitCopyAttachs();
    }
    
    //140612
    page.userAttachsDlg = function() {//        
        page.loadInitAttachs1();
        document.getElementById("list-selected-itemlstUserAttach").innerHTML = "";
        dijit.byId("lstUserAttach").setValue("");
    };
    
    page.loadInitAttachs1 = function() {
        var content = page.utf8_to_b64(dijit.byId("lstUserAttach").getValue());
        content = content.replaceAll('+', '_');
        sd.connector.post("filesAction!loadUserAttachs.do?lstUserAttach=" + content, null, null, null, page.afterLoadAttach1);
    };
    
    page.utf8_to_b64 = function(str) {
        return window.btoa(unescape(encodeURIComponent(str)));
    };
    
    page.afterLoadAttach1 = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result != null && result.length > 0) {
            var ck = true;
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                var size = getRowLengthOfTable("attachTbl") - 1;
                ck = true;
                for (var j = 0; j < size; j++) {
                    var preName = "createForm.lstAttachs[" + j + "].";
                    var categoryName = document.getElementById(preName + "categoryName").value;
                    if (categoryName == item.categoryName) {
                        ck = false;
                        //alert("[Tài liệu dòng " + j + "] đã upload file");
                        break;
                    }
                }
                if (ck == true) {
                    createNewRowOfAttach(item.attachId, item.categoryName, item.attachDes, item.attachPath, item.attachName, item.originalId, 'attachTbl', 1, item.objectType);
                }
            }
        }
    };
    
    page.noItem = function() {
        document.getElementById("list-selected-itemlstUserAttach").innerHTML = "";
        //dijit.byId("lstUserAttach").setValue("");
    };
    
    page.showBtnUserAttach();
    // Replaces all instances of the given substring.
    String.prototype.replaceAll = function(
            strTarget, // The substring you want to replace
            strSubString // The string you want to replace in.
            ) {
        var strText = this;
        var intIndexOfMatch = strText.indexOf(strTarget);

// Keep looping while an instance of the target string
// still exists in the string.
        while (intIndexOfMatch != -1) {
// Relace out the current instance.
            strText = strText.replace(strTarget, strSubString)

// Get the index of any next matching substring.
            intIndexOfMatch = strText.indexOf(strTarget);
        }

// Return the updated string with ALL the target strings
// replaced out with the new substring.
        return(strText);
    };
    //!140612   
    document.getElementById("lstFileRequire").innerHTML = "Số lượng tài liệu bắt buộc(*): " + page.checkCountFile();
</script>