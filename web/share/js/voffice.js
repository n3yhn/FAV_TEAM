/*
 *  Author: HaVM
 *
 **/


function showMessage(success, message) {
    var timeout = 10000;
    var id = "message-holder";
    var divResult = dojo.byId(id);
    dojo.attr(divResult, {
        style: {
            display: "block"
        },
        innerHTML: message
    });

    if (success == "1") {
        dojo.addClass(divResult, "notifySuccess");
    } else if (success == "2") {
        dojo.addClass(divResult, "notifyWarning");
    } else {
        dojo.addClass(divResult, "notifyError");
    }

    setTimeout(
            resultMessage_hide = function() {
                if (success == "1") {
                    dojo.removeClass(divResult, "notifySuccess");
                } else if (success == "2") {
                    dojo.addClass(divResult, "notifyWarning");
                } else {
                    dojo.removeClass(divResult, "notifyWarning");
                }
                dojo.attr(divResult, {
                    style: {
                        display: "none"
                    },
                    innerHTML: ""
                });
            },
            timeout
            );
    return true;
}
function showResultMessage(id, success, message) {
    var timeout = 10000;
    //    var id = "message-holder";
    var divResult = dojo.byId(id);
    dojo.attr(divResult, {
        style: {
            display: "block"
        },
        innerHTML: message
    });

    if (success == "1") {
        dojo.addClass(divResult, "notifySuccess");
    } else if (success == "2") {
        dojo.addClass(divResult, "notifyWarning");
    } else {
        dojo.addClass(divResult, "notifyError");
    }

    setTimeout(
            resultMessage_hide = function() {
                if (success == "1") {
                    dojo.removeClass(divResult, "notifySuccess");
                } else if (success == "2") {
                    dojo.removeClass(divResult, "notifyWarning");
                } else {
                    dojo.removeClass(divResult, "notifyWarning");
                }
                dojo.attr(divResult, {
                    style: {
                        display: "none"
                    },
                    innerHTML: ""
                });
            },
            timeout
            );
    return true;
}
function addNewRow(id) {
    var grid = dijit.byId(id);
    grid.edit.apply();
    var newItem = {
    }

    grid.store.newItem(newItem);
    grid.scrollToRow(grid._by_idx.length);
}

var currentMenuItem = "";

function clickMenuItem(item) {
    var nextRow = item;
    do {
        nextRow = nextRow.nextSibling;
    } while (nextRow && nextRow.nodeType != 1);
    if (!nextRow) {
        try {
            if (currentMenuItem != null) {
                currentMenuItem.className = "";
            }
            // item.getElementsByTagName("td")[0].className = 'activeMenuItem';
            item.getElementsByTagName("td")[0].className = 'activeMenuItem';
            currentMenuItem = item.getElementsByTagName("td")[0];
        } catch (e) {
        }
        return;
    }
    var table = nextRow.getElementsByTagName("table");
    if (table == null || table.length == 0) {
        //linhdx
        //searchText();
        try {
            if (currentMenuItem != null) {
                currentMenuItem.className = "";
            }
            item.getElementsByTagName("td")[0].className = 'activeMenuItem';
            currentMenuItem = item.getElementsByTagName("td")[0];
        } catch (e) {
            alert(e);
        }
    } else {
        if (nextRow.style.display == "") {
            nextRow.style.display = "none";
        } else {
            nextRow.style.display = "";
        }
    }
    adjustBodyFrameSize();

    if (hideTimeLineForSearch == 1) {
        hideTimeLineForSearch = 0;
        showTimeLine();
    }
}

autoCompleteDate = function(id) {
    var isNav = navigator.appName;
    var now = new Date();
    var ngay = now.getDate();
    if (ngay < 10) {
        ngay = "0" + ngay;
    }
    var thang = now.getMonth() + 1;
    var nam = 0;
    if (thang < 10) {
        thang = "0" + thang;
    }
    var currNgay = "";
    var currThang = "";
    var currNam = "";
    var currDate = "";
    var obj = dojo.byId(id);
    if ((isNav == "Microsoft Internet Explorer") || (isNav == "Opera")) {
        //if (obj=="[object]"){	//dung cho IE	// kiem tra xem co ton tai id khong	/
        nam = now.getYear();
        //dung cho firefox	
    } else if (isNav == "Netscape") {
        nam = now.getYear();
        if (nam < 1900) {
            nam += 1900;
        }
    }
    // kiem tra xem co ton tai id khong	/
    var strDate = obj.value;
    if (strDate != "") {		// if strDate co gia tri 
        var index_ = strDate.indexOf("/");
        if (index_ == -1) {		//	khong ton tai /
            currNgay = strDate.substring(0, 2);
            currThang = strDate.substring(2, 4);
            currNam = strDate.substring(4, 8);
            if (currNgay == null || currNgay == "") {
                currNgay = ngay;
            } else {
                if (currNgay.length < 2) {
                    currNgay = "0" + currNgay;
                }
            }
            if (currThang == null || currThang == "") {
                currThang = thang;
            } else {
                if (currThang.length < 2) {
                    currThang = "0" + currThang;
                }
            }

            if (currNam == null || currNam == "") {
                currNam = nam;
            } else if (currNam.length < 4) {
                var addedValue;
                if (currNam.length == 1) {
                    addedValue = nam.toString().substring(0, 3);
                } else if (currNam.length == 2) {
                    addedValue = nam.toString().substring(0, 2);
                    if (currNam > nam.toString().substring(2, 4)) {
                        addedValue = (parseInt(addedValue) - 1).toString();
                    }
                } else {
                    addedValue = nam.toString().substring(0, 1);
                    if (currNam > nam.toString().substring(1, 4)) {
                        addedValue = (parseInt(addedValue) - 1).toString();
                    }
                }
                currNam = addedValue + currNam;

            }

            currDate = currNgay + "/" + currThang + "/" + currNam;
        }	//	if (index_==-1
        else {		// co ton tai /		co nghia la CO THE ton tai thang. 
            currNgay = strDate.substring(0, index_);
            if (currNgay.length <= 1)
                currNgay = "0" + currNgay;			// neu ngay co 1 ky tu 	vd :8	chuyen thanhg 08
            else if (currNgay.length > 2) {
                currNgay = currNgay.substring(0, 2);
            }
            strDate = strDate.substring(index_ + 1, strDate.length);			// strDate gio chi co the chua nam va thang (da tach ngay)
            if (strDate == "")		// truong hop nay la strDate ban dau co dang 		"12/"
                currDate = currNgay + "/" + thang + "/" + nam;
            else {						// truong hop nay la strDate ban dau co dang 		"12/11"
                // xu ly thang va nam 
                var index_ = strDate.indexOf("/");
                if (index_ == -1) {		//	khong ton tai /		co nghia la chi co thang khong 
                    currThang = strDate;
                    if (currThang.length == 1)
                        currThang = "0" + currThang;			// neu ngay co 1 ky tu 	vd :8	chuyen thanhg 08
                    else if (currThang.length > 2)
                        currThang = currThang.substring(0, 2);
                    currDate = currNgay + "/" + currThang + "/" + nam;
                }	//	if (index_==-1
                else {						// co ton tai /		co nghia la co the ton tai nam	
                    currThang = strDate.substring(0, index_);
                    strDate = strDate.substring(index_ + 1, strDate.length);			// strDate gio chi co the chua nam
                    if (currThang.length == 1)
                        currThang = "0" + currThang;			// neu ngay co 1 ky tu 	vd :8	chuyen thanhg 08
                    else if (currThang.length > 2)
                        currThang = currThang.substring(0, 2);
                    if (strDate == "")		// truong hop nay la strDate ban dau co dang 		"12/12/"
                        currDate = currNgay + "/" + currThang + "/" + nam;
                    else {						// co ton tai nam 			12/12/2008	
                        currNam = strDate;
                        if (currNam.length > 4) {
                            currNam = currNam.substring(0, 4);
                        }
                        currDate = currNgay + "/" + currThang + "/" + currNam;

                    }
                }
            }
        }
        var dd = currDate.substring(0, 2);
        var mm = currDate.substring(3, 5);
        var yyyy = currDate.substring(6, currDate.length);
        if (isNaN(dd)) {
            dd = ngay;
        }
        if (isNaN(mm)) {
            mm = thang;
        }
        if (isNaN(yyyy)) {
            yyyy = nam;
        }
        dijit.byId(id).attr("value", new Date(yyyy, mm - 1, dd));
    }
}

// view file 
viewFileOnline = function(id, name) {
    var ending = ['docx', 'pdf', 'jpg', 'gif', 'img', 'png', 'xls', 'xlsx', 'ppt', 'pptx'];
    var result = false;
    for (var i = 0; i < ending.length; i++) {
        if (name.toLowerCase().indexOf(ending[i]) != -1) {
            result = true;
            window.open("viewFile!openFile.do?attachId=" + id);
            break;
        }
    }
    if (result == false) {
        alert("Không hỗ trợ định dạng file!");
    }

}

showTimeLine = function() {
    var timeline = document.getElementById("timeLineTable");
    timeline.setAttribute("style", "display:");
//    if(timeline.style.display == "none"){
//        timeline.style.display = "";
//    }
}

hideTimeLine = function() {
    var timeline = document.getElementById("timeLineTable");
    timeline.setAttribute("style", "display:none");
//    if(timeline.style.display == ""){
//        timeline.style.display = "none";
//    }    
}

/*SyTV - upcase textbox*/
upperKey = function(obj) {
    var val = obj.value;
    if (val != null) {
        obj.value = val.toUpperCase();
    }
}

/*********************************************************************************
 * TEXTBOXLIST
 ********************************************************************************/

function xTextboxListOnfocus(name) {
    var disable = dijit.byId('selecter_disable' + name).attr('value').toString();
    if (disable.toString() == 'true')
        return;
    document.getElementById('selecter-input-div' + name).style.display = 'block';
    document.getElementById('selecter-input' + name).focus();
}

function xTextboxListOnclear(id, name) {
    var disable = dijit.byId('selecter_disable' + name).attr('value').toString();
    if (disable.toString() == 'true')
        return;
    document.getElementById('list-selected-item' + name).innerHTML = '';
    document.getElementById(id).value = "";
    document.getElementById(name).value = "";
}

function xTextboxListOnremove(id, name, index, removeId, removeName) {
    var disable = dijit.byId('selecter_disable' + name).attr('value').toString();
    if (disable.toString() == 'true')
        return;
    dojo.destroy(dojo.byId('selected-item' + name + '-' + index));
    var oldInner = ";" + document.getElementById(name).value;
    document.getElementById(name).value = oldInner.replace(";" + removeName, "").substring(1);

    var oldId = ";" + document.getElementById(id).value;
    if (removeId != "-1") {
        document.getElementById(id).value = oldId.replace(";" + removeId, "").substring(1);
    }
}

function xTextboxListReload(id, name, dataObj) {
    var getValue = dijit.byId(name).attr('value').toString();
    var getId = dijit.byId(id).attr('value').toString();
    if (getValue.length > 0) {
        var arrayValues = getValue.split(";");
        for (var i = 0; i < arrayValues.length; i++) {
            var showId = "-1";
            var showName = arrayValues[i].toString();
            if (getId.length > 0) {
                //arrayIds = getId.split(";");
                for (var j = 0; j < dataObj.length; j++) {
                    if (showName.toUpperCase() == dataObj[j].search1.toString().toUpperCase()) {
                        showId = dataObj[j].id;
                        break;
                    }
                }
            }
            if (showName != "") {
                var newItem = '<li class="x-superboxselect-item" id="selected-item' + name + '-' + i + '" >'
                        + showName
                        + '<a class="x-superboxselect-item-close" tabindex="0" href="javascript:xTextboxListOnremove(\'' + id + '\',\'' + name + '\',\'' + i + '\',\'' + showId + '\',\'' + showName + '\')">'
                        + '</a>'
                        + '</li>';

                var oldInner = document.getElementById("list-selected-item" + name).innerHTML;
                document.getElementById("list-selected-item" + name).innerHTML = oldInner + newItem;
            }
        }
    }
}

function xTextboxListSetDisable(id, bool) {
    if (bool === true) {
        dijit.byId('selecter_disable' + id).attr('value', 'true');
    } else {
        dijit.byId('selecter_disable' + id).attr('value', 'false');
    }
}

function replaceExponentByShift6(contentId) {

    var divs = document.getElementById(contentId).getElementsByTagName("div");
    var i = 0;
    var content = "";
    var supIndex = 0;
    for (i = 0; i < divs.length; i++) {
        content = divs[i].innerHTML;
        content = content.replace(/\^\(/g, "<sup>");
        content = content.replace(/\^\)/g, "</sup>");
        divs[i].innerHTML = content;
    }

}
function replaceSubscript(contentId) {
    var divs = document.getElementById(contentId).getElementsByTagName("div");
    var i = 0;
    var content = "";
    var subIndex = 0;
    for (i = 0; i < divs.length; i++) {
        content = divs[i].innerHTML;
        content = content.replace(/\_\(/g, "<sub>");
        content = content.replace(/\_\)/g, "</sub>");
        divs[i].innerHTML = content;
    }
}
