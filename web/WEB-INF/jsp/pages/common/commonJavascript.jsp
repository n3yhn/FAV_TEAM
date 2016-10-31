<%-- 
    Document   : commonJavascript
    Created on : Apr 8, 2011, 1:41:04 PM
    Author     : gpdn_linhdx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/jsp/pages/vsaadmin/config/alertDialog.jsp"/>

<div id="hideDiv" style="display: none">
</div>

<script>
    trimZero = function(str) {
        if (str != null) {
            var i = 0;
            for (; i < str.length; i++) {
                if (str.charAt(i) != '0')
                    break;
            }
            if (i > 0)
                str = str.substring(i);
        }
        return str;
    }
    onTrimZero = function(obj) {
        if (obj.value == null || sd.validator.trim(obj.value).length == 0) {
            obj.value = "0";
            //    obj.focus();
            return false;
        }
        var number = parseFloat(obj.value);
        dijit.byId(obj.id).setValue(number);
        obj.value = number;
        return true;
    }

    page.formatNull = function(data) {
        if (data == null || data == undefined) {
            return "";
        } else {
            return data;
        }
    }

    page.getRow = function(inRowIndex) {
        return inRowIndex;
    }

    page.getIndex = function(inRowIndex) {
        return inRowIndex + 1;
    }

    //ham nay convert date lay tu datePicker thanh dang "dd/MM/yyyy" de co the compare
    page.convertDate = function(date) {
        if (date != null) {
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var date = date.getDate();
            if (month < 10) {
                strM = "0" + month;
            } else {
                strM = "" + month;
            }
            if (date < 10) {
                strD = "0" + date;
            } else {
                strD = "" + date;

            }
            var strDate = strD + "/" + strM + "/" + year;
            return strDate;
        }
    }
    page.convertStringToDate = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dgDate) {
        try {
            var dateStr = page.getString(dgDate);
            var temp = dateStr.split("-");
            return new Date(temp[1] + "/" + temp[2].split("T")[0] + "/" + temp[0]);
        } catch (e) {
            //page.alert("Thông báo","function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss", "warning");
            return undefined;
        }
    }
    page.getString = function(obj) {
        var ret = "";
        if (obj.constructor == String) {
            ret = obj;
        }
        else if (obj.constructor == Array) {
            ret = obj.join("");
        }

        return sd.validator.trim(ret);
    }
    page.convertDateToString = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dateStr) {
        if (dateStr != null && dateStr != "") {
            try {
                var dgDate = page.getString(dateStr);
                var temp = dgDate.split("-");
                return temp[2].split("T")[0] + "/" + temp[1] + "/" + temp[0];
            } catch (e) {
                //alert("function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss");
                return undefined;
            }
        } else {
            return "";
        }
    }
    page.convertDateToStringForDetail = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dateStr) {
        //return dd/mm/yyyy
        try {

            if (dateStr != null) {
                if (dateStr.toString().length > 0)
                {
                    console.log("dateStr.toString() " + dateStr.toString());
                    var dgDate = dateStr.toString().substring(0, 10);
                    console.log("dgDate " + dgDate);
                    var arrDate = dgDate.split("-");
                    console.log("arrDate " + arrDate);
                    return arrDate[2] + "/" + arrDate[1] + "/" + arrDate[0];
                }
                else
                    return "";
            }
            else
                return "";
        } catch (e) {
            //alert("function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss");
            return undefined;
        }
    }
    page.isValidDate = function(strValue) {
        return sd.validator.isDate(strValue);
    }


    page.formatGender = function(inDatum) {
        return (inDatum == '0') ? "Nam" : "Nữ";
    }
    page.clearGridData = function(gridIdStr) {
        try {
            var grid = dijit.byId(gridIdStr);
            var rowCount = grid._getRowCountAttr();
            if (rowCount > 0) {
                for (var i = rowCount - 1; i >= 0; i--) {
                    var item = grid.getItem(i);
                    grid.store.deleteItem(item);
                }
                grid.store.save();
            }
        } catch (e) {
            console.log(e.message);
        }
    }

    page.deleteGridData = function(gridIdStr) {
        var grid = dijit.byId(gridIdStr);
        if (grid._getRowCountAttr() > 0)
            grid.edit.apply();
        if (!grid.vtIsChecked()) {
            alert("Bạn chưa chọn bản ghi để xóa");
            return false;
        }
        else {
            var items = grid.vtGetCheckedItems();
            var length = items.length;
            try {
                for (var i = length - 1; i >= 0; i--) {
                    var item = items[i];
                    grid.store.deleteItem(item);
                }
                grid.store.save();
                grid.vtUnCheckAll();
            } catch (e) {
                console.log(e.message);
            }
        }
    }

    page.validStringLength = function(element, size, isNull, name) {
        if (isNull == true) {
            if (dojo.trim(element.value) == "") {
                msg.alert("Bạn phải nhập " + name);
                element.focus();
                return false;
            }
        }
        if (dojo.trim(element.value).length > size) {
            msg.alert(name + " không được vượt quá " + size + " ký tự");
            element.focus();
            return false;
        }
        return true;
    }

    page.validateComboValue = function(element, name) {
        if (!dijit.byId(element).getValue() || dijit.byId(element).getValue() == -1) {
            page.alert("Thông báo", "Bạn chưa chọn " + name, "warning");
            dijit.byId(element).focus();
            return false;
        }
        return true;
    }
    page.validateTextBoxValue = function(element, name) {
        if (!dijit.byId(element).getValue()) {
            page.alert("Thông báo", "Bạn chưa nhập " + name, "warning");
            dijit.byId(element).focus();
            return false;
        }
        return true;
    }
    page.validateEditableString = function(element, size, isNull, name, rowNo, gridName, index, gridNameVN) {
        if (isNull == true) {
            if (element == null || element.toString().length == 0) {
                alert(gridNameVN + " - " + "Dòng " + rowNo + ": Bạn phải nhập " + name);
                dijit.byId(gridName).focus.setFocusIndex(rowNo - 1, index);
                return false;
            }
        }
        if (dojo.trim(element.toString()).length > size) {
            alert("Dòng " + rowNo + ":" + name + " không được vượt quá " + size + " ký tự");
            dijit.byId(gridName).focus.setFocusIndex(rowNo - 1, index);
            return false;
        }
        return true;
    }
    page.validateSelectableItem = function(element, name, rowNo, gridName, index, gridNameVN) {
        //    alert(element);
        if (element == null || element.toString().length == 0 || element.toString() == '-1') {
            alert(gridNameVN + " - " + "Dòng " + rowNo + ": Bạn phải chọn " + name);
            dijit.byId(gridName).focus.setFocusIndex(rowNo - 1, index);
            return false;
        }
        return true;
    }

    page.validateDateFormat = function(value) {
        //    var date=value.substr(0,10);
        var date = value;
        // Regular expression used to check if date is in correct format
        var pattern = new RegExp(/^\d{1,2}(\-|\/|\.)\d{1,2}\1\d{4}$/);

        // kiem tra date
        if (date.match(pattern))
            return true;
        else
            return false;
    }
    page.validDate = function(element, isNull, name) {
        if (isNull == true && dojo.trim(element.value) == "") {
            alert("Bạn phải nhập " + name);
            element.focus();
            return false;
        }
        else if (!dojo.trim(element.value) == "") {
            if (!page.validateDateFormat(dojo.trim(element.value)))
            {
                alert(name + " không đúng định dạng dd/MM/yyyy!");
                element.focus();
                return false;
            }
            if (!sd.validator.isDate(dojo.trim(element.value)))
            {
                alert(name + " đã nhập không tồn tại giá trị ngày tháng !");
                element.focus();
                return false;
            }
        }
        return true;
    }
    page.compareToCurrentDate = function(element, name, isEqual) {
        var dateStr = element.value;
        var str = dojo.date.locale.format(new Date(), {
            datePattern: 'dd/MM/yyyy',
            selector: 'date'
        });
        var result = false;
        result = sd.validator.compareDates(dateStr, str);
        if (isEqual) {
            result = result || (dateStr == str);
            if (!result) {
                page.alert("Thông báo", name + " phải nhỏ hơn hoặc bằng ngày hiện tại", "warning");
                element.focus();
                return false;
            }
        } else {
            if (!result) {
                page.alert("Thông báo", name + " phải nhỏ hơn ngày hiện tại", "warning");
                element.focus();
                return false;
            }
        }
        return true;
    }
    page.compareTwoDate = function(date1, date2, name1, name2, isEqual) {
        var dateStr1 = date1.value;
        var dateStr2 = date2.value;
//        var str = dojo.date.locale.format(new Date(), {
//            datePattern : 'dd/MM/yyyy',
//            selector : 'date'
//        });
        var result = false;
        result = sd.validator.compareDates(dateStr1, dateStr2);
        if (isEqual) {
            result = result || (dateStr1 == dateStr2);
            if (!result) {
                page.alert("Thông báo", name1 + " phải nhỏ hơn hoặc bằng ngày" + name2, "warning");
                element.focus();
                return false;
            }
        } else {
            if (!result) {
                page.alert("Thông báo", name1 + " phải nhỏ hơn ngày " + name2, "warning");
                element.focus();
                return false;
            }
        }
        return true;
    }
    formEnter = function(formId, functionStr, isClear) {
        if (isClear) {
            $('#' + formId).unbind("keypress");
        }
        var func = eval(functionStr);
        var dk = dojo.keys;
        $('#' + formId).keypress(function(e) {
            var keynum
            if (window.event) { //IE
                keynum = e.keyCode
            }
            else if (e.which) {// Netscape/Firefox/Opera
                keynum = e.which
            }
            switch (keynum) {
                case dk.ENTER:
                    func();
                    break;
            }
        });
    }

    setFirstFocus = function(formId) {
        var aForm = document.getElementById(formId);
        if (aForm.elements[0] != null) {
            var i;
            var max = aForm.length;
            for (i = 0; i < max; i++) {
                if (aForm.elements[ i ].type != "hidden" &&
                        !aForm.elements[ i ].disabled &&
                        !aForm.elements[ i ].readOnly) {
                    aForm.elements[ i ].focus();
                    break;
                }
            }
        }
    }

    clearForm = function(formId) {
        var aForm = document.getElementById(formId);
        var inputs = aForm.getElementsByTagName("input");
        if (inputs != null && inputs.length > 0) {
            for (var i = 0; i < inputs.length; i++) {
                inputs[ i ].value = "";
            }
        }
    }

    //DiuLTT sửa forcus => onFocus
    onFocus = function(elementId) {
        var element = document.getElementById(elementId);
        if (element != null) {
            element.focus();
        }
    }

    checkFile_ = function(uploadId, fileObj, extentions, fileSize) {
        if (extentions != null && extentions != "") {
            var stringArray = fileObj.name.split('.');
            var ext = stringArray[stringArray.length - 1];
            var exts = extentions.split(":");
            var isValid = false;
            var validExtString = "";
            var upload = "#" + uploadId;
            for (var i = 0; i < exts.length; i++) {
                var validExt = exts[i];
                if (validExt != "") {
                    validExtString = validExtString + validExt + ", ";
                    if (validExt == ext) {
                        isValid = true;
                        break;
                    }
                }
            }
            if (validExtString.length > 2) {
                validExtString = validExtString.substring(0, validExtString.length - 2);
            }
            if (!isValid) {
                msg.alert("Định dạng file không cho phép. Hãy chọn file " + validExtString, "<sd:Property>confirm.title</sd:Property>");
                jQuery(upload).uploadifyClearQueue();
                jQuery(upload).uploadifyCancel();
            }
            return isValid;
        }
        if (fileSize != null && fileSize < fileObj.size) {
            var sizeMB = fileSize / (1024 * 1024);
            msg.alert("Dung lượng file không vượt quá" + sizeMB + "MB", "<sd:Property>confirm.title</sd:Property>");
            jQuery(upload).uploadifyClearQueue();
            jQuery(upload).uploadifyCancel();
            return false;
        }
    }

    shortenFilename = function(filename, maxLength) {
        if (!(filename instanceof String)) {
            filename = filename.toString();
        }
        if (filename != null && dojo.trim(filename) != "") {
            var pos = filename.lastIndexOf(".");
            var ext = "";
            if (pos >= 0 && pos < filename.length) {
                ext = filename.substring(pos + 1);
                filename = filename.substring(0, pos);
            }
            if (filename.length > maxLength) {
                filename = filename.substring(0, maxLength - 1);
                filename += "~";
            }
            return filename + "." + ext;
        }
        return "";
    }

    shorten_ = function(content, maxLength) {
        var hideDiv = document.getElementById("hideDiv");
        var html = content.toString();
        hideDiv.innerHTML = content;
        content = escapeHtml_(hideDiv.textContent);
        if (content.length > maxLength) {
            content = content.substring(0, maxLength - 3);
            content += "...";
        }
        if (content == null || dojo.trim(content) == "") {
            if (html.indexOf("<img") >= 0) {
                content = "Hình minh họa...";
            } else if (html.indexOf("<table") >= 0) {
                content = "Bảng...";
            } else {
                content = "...";
            }
        }
        return content;
    }

    isFloat_ = function(str) {
        var value = str.toString();
        value = dojo.trim(value);
        return (value == parseFloat(value));
    }

    isInteger_ = function(str) {
        var value = str.toString();
        value = dojo.trim(value);
        return (value == parseInt(value));
    }

    escapeHtml_ = function(str) {
        var result = "";
        if (str != null && str != "") {
            result = str.toString().replace(/&/g, '&amp;');
            result = result.replace(/</g, '&lt;');
            result = result.replace(/>/g, '&gt;');
            result = result.replace(/'/g, '&#39;');
            result = result.replace(/"/g, '&quot;');
        }
        return result;
    }

    deEscapeHtml_ = function(str) {
        var result = "";
        if (str != null && str != "") {
            result = str.toString().replace(/&amp;/g, '&');
            result = result.replace(/&lt;/g, '<');
            result = result.replace(/&gt;/g, '>');
            result = result.replace(/&39;/g, ',');
            result = result.replace(/&quot;/g, '\"');
        }
        return result;
    }

    /**
     * For trim text in fck editor
     */
    htmlTrim_ = function(str) {
        var result = "";
        if (str != null && str != "") {
            result = str.toString().replace(/&nbsp;/g, ' ');
            result = dojo.trim(result);
            if (result.lastIndexOf("<br />") == result.length - 6) {
                result = result.substring(0, result.length - 6);
            }
        }
        return result;
    }

    changeGridSize = function(gridId) {
        var divParent = document.getElementById(gridId);
        var divHeader = divParent.firstChild.nextSibling;
        var divHeader = divHeader.firstChild.nextSibling;
        divParent.style.width = divHeader.style.width;
    }

    toggleSizeDialog = function(dialogID, func) {
        var divTitleDialog = document.getElementById(dialogID).firstChild.nextSibling;
        divTitleDialog.ondblclick = func;
        var buttonResize = divTitleDialog.firstChild.nextSibling.nextSibling.nextSibling.nextSibling;
        buttonResize.onclick = func;
    }

    click_ = function(elementId) {
        alert(elementId);
        var fireOnThis = document.getElementById(elementId);
        if (document.createEvent) {
            var evObj = document.createEvent('MouseEvents');
            evObj.initEvent('click', true, false);
            fireOnThis.dispatchEvent(evObj);
        } else if (document.createEventObject) {
            fireOnThis.fireEvent('onclick');
        }
        alert(elementId);
    }

    lowerFirstCharacter = function(str) {
        var content = str;
        if (str != null && str.length > 0) {
            content = str.substring(0, 1).toLowerCase() + str.substring(1);
        }
        return content;
    }

    getLinkAttach = function(prefix, attachId, filename, maxLength) {
        var link = "";
        if (attachId != null && attachId != undefined && filename != null && filename != undefined) {
            if (maxLength != null && maxLength != undefined) {
                filename = shortenFilename(filename, maxLength);
            }
            if (prefix != null && prefix != "") {
                prefix = prefix + "_";
            } else {
                prefix = "";
            }
            link = "<a id='" + prefix + "attach_" + attachId + "' href='/HVCT/attachAction%21download.do?attachId=" + attachId + "' target='downloadAttach_'>"
                    + filename + "</a>";
        }
        return link;
    }
    Date.fromUKFormat = function(sUK) {
        var A = sUK.split(/[\\\/]/);
        A = [A[1], A[0], A[2]];
        return new Date(Date.parse(A.join('/')));
    }

    page.attachError_ = function() {
        alert("Lỗi: Dữ liệu không tồn tại!");
    }

    function getFileSize(element) {
        var fileSize = null;
        if (/MSIE (\d+\.\d+);/.test(navigator.userAgent)) {
            // IE
            var myFSO = new ActiveXObject("Scripting.FileSystemObject");
            var filepath = element.value;
            var file = myFSO.getFile(filepath);
            fileSize = file.size;
        } else if (/Firefox[\/\s](\d+\.\d+)/.test(navigator.userAgent)) {
            // Firefox
            if (element.files) {
                fileSize = element.files.item(0).fileSize;
            }
        }
        return fileSize;
    }

    isEmpty_ = function(str) {
        if (str != null && str != "") {
            return false;
        }
        else
            return true;
    }

    isEmptySelect_ = function(str) {
        if (str != null && str != "" && str != "-1") {
            return false;
        }
        else
            return true;
    }

    validateURL = function(url) {
        var bReturn;
        url = url.toLowerCase();
        console.log("begin : " + url);
        var idx = 0;
        if (url.indexOf("fpt://") == 0) {
            idx = url.indexOf("fpt://");
            url = url.substring(6);
        } else if (url.indexOf("http://") == 0) {
            idx = url.indexOf("fpt://");
            url = url.substring(7);
        } else if (url.indexOf("https://") == 0) {
            idx = url.indexOf("fpt://");
            url = url.substring(8);
        }
        console.log(url);

        if (url.indexOf("www.") == 0) {
            idx = url.indexOf("www.");
            url = url.substring(4);
        }
        idx = url.indexOf(".");
        if (idx == -1) {
            return false;
        }

        //
        // check site name
        //
        do {
            idx = url.indexOf(".");
            if (idx == -1) {
            } else {
                var siteName = url.substring(0, idx);
                var url = url.substring(idx + 1, url.length);
                console.log("siteName:" + siteName);
                console.log("param:" + url);
                var formFormat = new RegExp("^[a-z,A-Z,0-9,_]*$");
                bReturn = formFormat.exec(siteName);
                if (!bReturn) {
                    return bReturn;
                }
            }
        } while (idx != -1);
        //
        // check param
        //
        var paramFormat = new RegExp("^[a-z,A-Z,0-9,_,?,=,:,/]*$");
        bReturn = paramFormat.exec(url);
        return bReturn;
    }

    validatePhone = function(phoneNumber) {
        var bReturn = true;
        if (phoneNumber == null) {
            bReturn = false;
        } else {
            phoneNumber = phoneNumber.trim();
            if (phoneNumber.length > 14 || phoneNumber.length < 5) {
                bReturn = false
            } else {
                try {
                    bReturn = !isNaN(parseInt(phoneNumber));
                } catch (en) {
                    bReturn = false;
                }
            }
        }
        return bReturn;
    }

    function validateEmail(email) {
        var re = /\S+@\S+\.\S+/;
        return re.test(email);
    }

    getRowLengthOfTable = function(tableId) {
        var table = document.getElementById(tableId);
        var size = table.getElementsByTagName("tr").length;
        return size;
    }

    stringToExponentDisplay = function(content) {

        var supIndex = content.indexOf("^(");
        var closesup = content.indexOf("^)");
        if (supIndex >= 0) {
            content = content.replace(/\^\(/g, "<sup>");
            content = content.replace(/\^\)/g, "</sup>");
        }
        return content;

    }
    stringToExponentDisplaySupscript = function(content) {

        var supIndex = content.indexOf("_(");
        var closesup = content.indexOf("_)");
        if (supIndex >= 0) {
            content = content.replace(/\_\(/g, "<sub>");
            content = content.replace(/\_\)/g, "</sub>");
        }
        return content;
    }



</script>
<iframe name="downloadAttach_" style="display:none;">
</iframe>