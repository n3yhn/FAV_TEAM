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
                            if (f && f(b.value, d.uploadId1, d.loadingId))
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

    page.isValidExt1 = function(fileName) {
        var ext = page.getFileExtension(fileName);
        ext = ext.toString().toLowerCase();
        
        var allExt = ".xlsx";
        if (ext!="xlsx") {
            
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

                            createNewRowOfAttach = function(attachId, categoryName, attachDes, attachName, tableId) {
                            
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

                                var row = document.createElement("tr");
                                var tdIndex = document.createElement("td");
                                tdIndex.setAttribute("align", "center");
                                tdIndex.innerHTML = index;
                                console.log('sdsfdsf');

                                var tdCategoryName = document.createElement("td");
                                {
                                    var inpCategoryName = document.createElement("input");
                                    inpCategoryName.setAttribute("type", "text");
                                    inpCategoryName.setAttribute("name", preName + "categoryName");
                                    inpCategoryName.setAttribute("id", preName + "categoryName");
                                    inpCategoryName.setAttribute("style", "width:100%;");
                                    inpCategoryName.setAttribute("value", categoryName);
                                    inpCategoryName.setAttribute("maxlength", "250");
                                    tdCategoryName.appendChild(inpCategoryName);

                                    var inpAttachId = document.createElement("input");
                                    inpAttachId.setAttribute("type", "hidden");
                                    inpAttachId.setAttribute("name", preName + "attachId");
                                    inpAttachId.setAttribute("id", preName + "attachId");
                                    inpAttachId.setAttribute("value", attachId);
                                    tdCategoryName.appendChild(inpAttachId);
                                }
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

                                var tdLink = document.createElement("td");
                                {
                                    var aLink = document.createElement("a");
                                    aLink.id = "lnk" + size;
                                    if (attachId != null && attachId != "") {
                                        
                                        aLink.href = 'uploadiframe!openFile.do?attachId=' + attachId;
                                    }
                                    if (attachName != null && attachName != "") {
                                        aLink.innerHTML = attachName;
                                    }
                                    tdLink.appendChild(aLink);
                                }

                                var tdUploadAtt = document.createElement("td");
                                {
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
                                var tdDeleteAtt = document.createElement("td");
                                {
                                    var img = document.createElement("img");
                                    img.src = 'share/images/icons/13.png';
                                    img.setAttribute("width", '20px');
                                    img.setAttribute("height", '20px');
                                    img.setAttribute("title", 'Xóa tài liệu');
                                    img.setAttribute("onclick", "page.deleteAttachs(this.parentNode.parentNode);");
                                    tdDeleteAtt.appendChild(img);
                                }

                                row.appendChild(tdIndex);
                                row.appendChild(tdCategoryName);
                                row.appendChild(tdAttachDes);
                                row.appendChild(tdLink);
                                row.appendChild(tdUploadAtt);
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
                                            uploadId1: 'upload' + size,
                                            loadingId: 'imgLoading' + size,
                                            onstart: function(filename, uploadId1, loadingId)
                                            {
                                                var isValid = false;
                                                var file = document.getElementById(uploadId1);
                                                //alert(uploadId1);
                                                console.log(file);
                                                var files = file.files;
                                                //alert(2);
                                                if (!files) {//for IE
                                                    if (file.size > 0) {
                                                        var fileName = file.value;
                                                        if (!page.isValidExt1(fileName))
                                                            return false;
                                                    }
                                                } else {
                                                    if (document.getElementById(uploadId1).files.length > 0) {
                                                        var fileName = document.getElementById(uploadId1).files[0].name;
                                                        //alert(fileName);
                                                        if (!page.isValidExt1(fileName))
                                                            return false;
                                                        var size = document.getElementById(uploadId1).files[0].size;
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
    <br/>
    <table id="uexcelTbl" class="lstTable">
      


<table id="excelTbl" class="lstTable">
    <tr class="headerRow">
        <th width="5%">STT</th>
        <th width="20%">Tên tài liệu<font style="color:red">*</font></th>
        <th width="30%">Mô tả tài liệu</th>
        <th width="20%">Tải về</th>
        <th width="10%">Đính kèm tệp<font style="color:red">*</font></th>
        <th width="5%">Xóa</th>
    </tr>
</table>
<div>
    <sx:ButtonAddCategory onclick="createNewRowOfAttach('','','','','excelTbl');"/>
</div>
<script type="text/javascript">
    page.renameAttachIndexs = function() {
        var table = document.getElementById("excelTbl");
        var rows = table.getElementsByTagName("tr");
        var i = 0;
        for (i = 1; i < rows.length; i++) {
            var tds = rows[i].getElementsByTagName("td");
            tds[0].innerHTML = i;
        }
    };

    page.renameElementOfAttachs = function() {
        var preName = "createForm.lstAttachs[";
        var table = document.getElementById("excelTbl");
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
        }
    };

    page.deleteAttachs = function(rowElement) {
        msg.confirm("Bạn có chắc muốn xóa tài liệu không ?", "Xóa tài liệu", function() {
            rowElement.parentNode.removeChild(rowElement);
            page.renameAttachIndexs();
        });
    };

    page.validateAttachData = function() {
        var tabs = dijit.byId("files_tab");
        var panel = dijit.byId("tab.excel");
        tabs.selectChild(panel);
        var table = document.getElementById("excelTbl");
        var rows = table.getElementsByTagName("tr");
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
                alert("[Tài liệu dòng " + i + "] chưa upload file");
                inputs[0].focus();
                return false;
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
        return true;
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
                createNewRowOfAttach(item.attachId, item.categoryName, item.attachDes, item.attachName, 'excelTbl');
            }
        }
    };

    page.loadInitAttachs = function() {
        var fileLst = '${fn:escapeXml(fileList)}';
        fileLst = fileLst.replace(new RegExp("nl", "g"), "<br/>");
        document.getElementById("lstFileUpload").innerHTML = fileLst;
        var fileId = dijit.byId("createForm.fileId").getValue();
        if (fileId == null || fileId == "") {
            //createNewRowOfAttach('', '', '', "", 'excelTbl');
        } else {
            sd.connector.post("filesAction!loadAttachs.do?createForm.fileId=" + fileId, null, null, null, page.afterLoadAttach);
        }
    };

    var isCopy = false;
    try {
        isCopy = "${isCopy}";
    } catch (en) {
    }
    if (isCopy != "true") {
        page.loadInitAttachs();
    }
    //140612
    page.userAttachsDlg = function() {//        
        page.loadInitAttachs1();
        document.getElementById("list-selected-itemlstUserAttach").innerHTML = "";
        dijit.byId("lstUserAttach").setValue("");
    };
    page.loadInitAttachs1 = function() {
        var lstUserAttach = dijit.byId("lstUserAttach").getValue();
        sd.connector.post("filesAction!loadUserAttachs.do?lstUserAttach=" + lstUserAttach, null, null, null, page.afterLoadAttach1);
    };
    page.afterLoadAttach1 = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result != null && result.length > 0) {
            var ck = true;
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                var size = getRowLengthOfTable("excelTbl") - 1;
                ck = true;
                for(var j = 0; j < size; j++){
                    var preName = "createForm.lstAttachs[" + j + "].";
                    var categoryName = document.getElementById(preName + "categoryName").value;
                    if (categoryName == item.categoryName){
                        ck = false;
                        alert("[Tài liệu dòng " + j + "] đã upload file");
                    break;}
                }
                if (ck == true) {
                    createNewRowOfAttach(item.attachId, item.categoryName, item.attachDes, item.attachName, 'excelTbl');
                }
            }
        }
    };
    page.noItem = function(){
        dijit.byId("lstUserAttach").setValue("");
    };
    //!140612
</script>