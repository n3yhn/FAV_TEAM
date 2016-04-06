<%@page import="java.util.ResourceBundle"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Locale"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<%
    request.setAttribute("externalResourcesPath", request.getAttribute("contextPath"));

    request.setAttribute("PlugInFolderName", request.getAttribute("externalResourcesPath") + "/share/plug-in");
    request.setAttribute("ImgFolderName", request.getAttribute("externalResourcesPath") + "/share/images");
    request.setAttribute("CSSFolderName", request.getAttribute("externalResourcesPath") + "/share/css");
    request.setAttribute("JSVTFolderName", request.getAttribute("externalResourcesPath") + "/share/js");
    request.setAttribute("JSLibFolderName", request.getAttribute("externalResourcesPath") + "/share/plug-in/dojo-release-1.4.0");

    request.setAttribute("UploadFolderName", request.getAttribute("externalResourcesPath") + "/share/upload");
    request.setAttribute("UploadImageFolderName", "share/uploadImage");
    request.setAttribute("UploadFlashFolderName", request.getAttribute("externalResourcesPath") + "/share/uploadFlash");

    ResourceBundle bundle = ResourceBundle.getBundle("cas");
    request.setAttribute("logoutUrl", bundle.getString("logoutUrl") + "?appCode=" + bundle.getString("domainCode") + "&service=" + URLEncoder.encode(bundle.getString("service"), "UTF-8"));
    request.setAttribute("loginUrl", bundle.getString("loginUrl") + "?appCode=" + bundle.getString("domainCode") + "&service=" + URLEncoder.encode(bundle.getString("service"), "UTF-8"));

    ResourceBundle rb = ResourceBundle.getBundle("config");
    request.setAttribute("RDWFisCryptParameter", rb.getString("RDWF.isCryptParameter"));
%>

<!--[ RDWF css -->
<style type="text/css">
    @font-face {
        font-family: myFont;
        src: url(${CSSFolderName}/banque.ttf);
    }

    @font-face {
        font-family: slogan;
        src: url(${CSSFolderName}/pie.ttf);
    }
</style>

<%--Cuc ATTP--%>
<!--<link href="${ImgFolderName}/layout/new_3_3/favicon.png" type="image/x-icon" rel="shortcut icon" />-->
<link href="${CSSFolderName}/layout.css" charset="UTF-8" type="text/css" rel="stylesheet" />
<link href="${CSSFolderName}/inner-layout.css" charset="UTF-8" type="text/css" rel="stylesheet" />
<link href="${CSSFolderName}/CustomTree.css" type="text/css" rel="stylesheet" />
<link href="${CSSFolderName}/voffice.css" type="text/css" rel="stylesheet" />
<!--] -->

<!--[ JSCookMenu -->
<link href="${PlugInFolderName}/jscookmenu-2.0.4/ThemePanel/theme.css" type="text/css" rel="stylesheet" />
<script src="${PlugInFolderName}/jscookmenu-2.0.4/JSCookMenu_min.js" type="text/javascript"></script>
<script src="${PlugInFolderName}/jscookmenu-2.0.4/effect_min.js" type="text/javascript"></script>
<script src="${PlugInFolderName}/jscookmenu-2.0.4/ThemePanel/theme_min.js" type="text/javascript"></script>
<!--] -->

<link href="${JSLibFolderName}/dojox/layout/resources/ExpandoPane.css" type="text/css" rel="stylesheet" />
<link href="${JSLibFolderName}/dojo/resources/dojo.css" type="text/css" rel="stylesheet" />
<link href="${JSLibFolderName}/dojox/grid/resources/${JSLibTheme}Grid.css" type="text/css" rel="stylesheet" />
<link href="${JSLibFolderName}/dijit/themes/${JSLibTheme}/${JSLibTheme}.css" type="text/css" rel="stylesheet" />
<link href="${JSLibFolderName}/dijit/themes/${JSLibTheme}/${JSLibTheme}Mod.css" type="text/css" rel="stylesheet" />

<link href="${JSLibFolderName}/vt/css/dataPicker.css" type="text/css" rel="stylesheet" />
<link href="${JSLibFolderName}/vt/css/treePicker.css" type="text/css" rel="stylesheet" />
<link href="${CSSFolderName}/qllltp.css" charset="UTF-8" type="text/css" rel="stylesheet" />

<%--test prevent XSS Attack--%>
<script src="${PlugInFolderName}/esapi/resources/i18n/ESAPI_Standard_en_US.properties.js" type="text/javascript" language="JavaScript"></script>
<script src="${PlugInFolderName}/esapi/esapi.js" type="text/javascript" language="JavaScript"></script>
<script src="${PlugInFolderName}/esapi/resources/Base.esapi.properties.js" type="text/javascript" language="JavaScript"></script>
<%--test prevent XSS Attack--%>

<script src="${JSLibFolderName}/dojo/dojo.js" djConfig="parseOnLoad:true"  type="text/javascript"></script>
<script src="${JSLibFolderName}/dijit/form/_FormWidget.js" type="text/javascript"></script>
<%-- Note --%>
<script type="text/javascript" src="share/note/script/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="share/note/script/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="share/note/script/jquery.stickynotes.js"></script>
<script type="text/javascript" src="share/plug-in/tinymce/tinymce.min.js"></script>
<link rel="stylesheet" href="share/note/css/jquery.stickynotes.css" type="text/css">
<link rel="stylesheet" href="share/note/css/ui-lightness/jquery-ui-1.7.2.custom.css" type="text/css">


<%--cuongnx ma hoa--%>

<!--<script src="${JSVTFolderName}/aes.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/cryptoHelpers.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/md5.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/stopwatch.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/vt-crypt.js" type="text/javascript"></script>-->

<%--/cuongnx ma hoa--%>


<script src="${JSVTFolderName}/vt-token.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/vt.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/vt-operator.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/vt-connector.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/vt-widget.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/vt-widget-loader.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/vt-notification.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/vt-event.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/vt-layout.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/vt-appstate.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/vt-validator.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/vt-util.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/vt-log.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/vt-adapter.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/voffice.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/utils.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/voffice-shortcut.js" type="text/javascript"></script>
<%--[ LongH says: For debuging js-blackbox--%>
<script src="debug.js" type="text/javascript"></script>
<%--] LongH says: For debuging js-blackbox--%>

<%--[ LongH says: Test CKEditor--%>
<script src="${PlugInFolderName}/ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="${PlugInFolderName}/ckfinder/ckfinder.js" type="text/javascript"></script>
<%--] LongH says: Test CKEditor--%>


<script type="text/javascript">
    //[ LongH says: RDWF's global & utility functions @7Apr11
    function updatePageInfo(userName, actionTitle) {
        try {
            //updateActionInfo(actionTitle);
            profileIcon.innerHTML = userName;
        } catch (e) {
            alert("JSException in updatePageInfo: \n" + e.message);
        }
    }

    function updateDocumentTitle(docTitle) {
        document.title = docTitle;
    }

    function updateActionInfo(actionTitle) {
        //sd.$("vt-titleAction").innerHTML = actionTitle;
    }

    function cmDrawSubMenu(subMenu, prefix, id, nodeProperties, zIndexStart, menuInfo, menuID)
    {
        var str = '<div class="' + prefix + 'SubMenu" id="' + id + '" style="z-index: ' + zIndexStart + ';position: absolute; top: 0px; left: 0px;">';
        if (nodeProperties.subMenuHeader)
            str += nodeProperties.subMenuHeader;
        str += '<table summary="sub menu" id="' + id + 'Table" cellspacing="' + nodeProperties.subSpacing + '" class="' + prefix + 'SubMenuTable">';
        var strSub = '';
        var item;
        var idSub;
        var hasChild;
        var i;
        var classStr;
        for (i = 5; i < subMenu.length; ++i)
        {
            item = subMenu[i];
            if (!item)
                continue;
            if (item == _cmSplit)
                item = cmSplitItem(prefix, 0, true);
            item.parentItem = subMenu;
            item.subMenuID = id;
            hasChild = (item.length > 5);
            idSub = hasChild ? cmNewID() : null;
            str += '<tr class="' + prefix + 'MenuItem"';
            if (item[0] != _cmNoClick)
                str += cmActionItem(item, 0, idSub, menuInfo, menuID);
            else
                str += cmNoClickItem(item, 0, idSub, menuInfo, menuID);
            str += '>';
            if (item[0] == _cmNoAction || item[0] == _cmNoClick)
            {
                str += cmNoActionItem(item);
                str += '</tr>';
                continue;
            }
            classStr = prefix + 'Menu';
            classStr += hasChild ? 'Folder' : 'Item';
            str += '<td class="' + classStr + 'Left">';
            if (item[0] != null)
                str += item[0];
            else
                str += hasChild ? nodeProperties.folderLeft : nodeProperties.itemLeft;
            str += '</td><td class="' + classStr + 'Text">' + item[1];
            str += '</td><td class="' + classStr + 'Right">';
            if (hasChild)
            {
                str += nodeProperties.folderRight;
                strSub += cmDrawSubMenu(item, prefix, idSub, nodeProperties, zIndexStart + nodeProperties.zIndexInc, menuInfo, menuID);
            }
            else
                str += nodeProperties.itemRight;
            str += '</td></tr>';
        }
        str += '</table>';
        if (nodeProperties.subMenuFooter)
            str += nodeProperties.subMenuFooter;
        str += '</div>' + strSub;
        return str;
    }

    function cmActionItem(item, isMain, idSub, menuInfo, menuID)
    {
        _cmItemList[_cmItemList.length] = item;
        var index = _cmItemList.length - 1;
        idSub = (!idSub) ? 'null' : ('\'' + idSub + '\'');
        var clickOpen = menuInfo.nodeProperties.clickOpen;
        var onClick = (clickOpen == 3) || (clickOpen == 2 && isMain);
        var param = 'this,' + isMain + ',' + idSub + ',' + menuID + ',' + index;
        var returnStr;
        if (onClick)
            returnStr = ' onmouseover="cmItemMouseOver(' + param + ',false)" onmousedown="cmItemMouseDownOpenSub (' + param + ')"';
        else
            returnStr = ' onmouseover="cmItemMouseOverOpenSub (' + param + ')" onmousedown="cmItemMouseDown (' + param + ')"';
        return returnStr + ' onmouseout="cmItemMouseOut (' + param + ')" onmouseup="cmItemMouseUp (' + param + ')"';
    }

    function cmdDrawLeftMenu(id, menu, orient, nodeProperties, prefix)
    {
        var obj = cmGetObject(id);
        if (!prefix)
            prefix = nodeProperties.prefix;
        if (!prefix)
            prefix = '';
        if (!nodeProperties)
            nodeProperties = _cmNodeProperties
        if (!orient)
            orient = 'hbr';
        var menuID = cmAllocMenu(id, menu, orient, nodeProperties, prefix);
        var menuInfo = _cmMenuList[menuID];
        if (!nodeProperties.delay)
            nodeProperties.delay = _cmNodeProperties.delay;
        if (!nodeProperties.clickOpen)
            nodeProperties.clickOpen = _cmNodeProperties.clickOpen;
        if (!nodeProperties.zIndexStart)
            nodeProperties.zIndexStart = _cmNodeProperties.zIndexStart;
        if (!nodeProperties.zIndexInc)
            nodeProperties.zIndexInc = _cmNodeProperties.zIndexInc
        if (!nodeProperties.offsetHMainAdjust)
            nodeProperties.offsetHMainAdjust = _cmNodeProperties.offsetHMainAdjust;
        if (!nodeProperties.offsetVMainAdjust)
            nodeProperties.offsetVMainAdjust = _cmNodeProperties.offsetVMainAdjust;
        if (!nodeProperties.offsetSubAdjust)
            nodeProperties.offsetSubAdjust = _cmNodeProperties.offsetSubAdjust;
        menuInfo.cmFrameMasking = _cmFrameMasking;
        var str = '<table summary="main menu" class="' + prefix + 'Menu" cellspacing="' + nodeProperties.mainSpacing + '">';
        var strSub = '';
        var vertical;
        if (orient.charAt(0) == 'h') {
            str += '<tr>';
            vertical = false;
        } else {
            vertical = true;
        }
        var i;
        var item;
        var idSub;
        var hasChild;
        var classStr;
        for (i = 0; i < menu.length; ++i) {
            item = menu[i];
            alert(item);
            if (!item)
                continue;
            item.menu = menu;
            item.subMenuID = id;
            str += vertical ? '<tr' : '<td';
            str += ' class="' + prefix + 'MainItem"';
            hasChild = (item.length > 5);
            idSub = hasChild ? cmNewID() : null;
            str += cmActionItem(item, 1, idSub, menuInfo, menuID) + '>';
            if (item == _cmSplit)
                item = cmSplitItem(prefix, 1, vertical);
            if (item[0] == _cmNoAction || item[0] == _cmNoClick) {
                str += cmNoActionItem(item);
                str += vertical ? '</tr>' : '</td>';
                continue;
            }
            classStr = prefix + 'Main' + (hasChild ? 'Folder' : 'Item');
            str += vertical ? '<td' : '<span';
            str += ' class="' + classStr + 'Left">';
            str += (item[0] == null) ? (hasChild ? nodeProperties.mainFolderLeft : nodeProperties.mainItemLeft) : item[0];
            str += vertical ? '</td>' : '</span>';
            str += vertical ? '<td' : '<span';
            str += ' class="' + classStr + 'Text">';
            str += item[1];
            str += vertical ? '</td>' : '</span>';
            str += vertical ? '<td' : '<span';
            str += ' class="' + classStr + 'Right">';
            str += hasChild ? nodeProperties.mainFolderRight : nodeProperties.mainItemRight;
            str += vertical ? '</td>' : '</span>';
            str += vertical ? '</tr>' : '</td>';
            if (hasChild)
                strSub += cmDrawSubMenu(item, prefix, idSub, nodeProperties, nodeProperties.zIndexStart, menuInfo, menuID);
        }
        if (!vertical)
            str += '</tr>';
        str += '</table>' + strSub;
        obj.innerHTML = str;
    }

    //
    // 
    //
    function drawItem(item, level, showContent) {
        var strItem = "<tr onclick='clickMenuItem(this);" + item[2] + "'><td class='parentMenu'>" + item[1] + "</td></tr>";
        if (item.length > 5) {
            var i = 5;
            var subItem = "<tr style='display:none'><td><table class='tableLevel" + level + "'>";
            if (showContent == "true") {
                subItem = "<tr ><td><table class='tableLevel" + level + "'>";
            }
            for (i = 5; i < item.length; i++) {
                subItem = subItem + drawItem(item[i], level + 1);
            }
            subItem = subItem + "</table></td></tr>";
            strItem = strItem + subItem;
        } else {
            strItem = "<tr onclick='clickMenuItem(this);" + item[2] + "'><td>" + item[1] + "</td></tr>";
        }
        return strItem;
    }
    //
    // Ve thuan tuy menu ben trai
    //

    function drawLeft(id, menu) {
        var obj = cmGetObject(id);
        var i = 0;
        var str = "<table class='leftbar'>";
        var item;
        for (i = 0; i < menu.length; i++) {
            item = menu[i];
            if (!item)
                continue;
            var strItem = "";
            if (i <= 2)
                strItem = drawItem(item, "1", "true");
            else
                strItem = drawItem(item, "1", "false");
            str = str + strItem;
        }
        str = str + "</table>";
        obj.innerHTML = str;
    }
    //
    // Ve menu theo kieu tan danimages
    //
    var currentDisplayMenu = "";
    var hideTimeLineForSearch = 0;
    function showLeftMenu(item) {
        try {
            var mainMenu = document.getElementById("mainMenu" + currentDisplayMenu);
            var childMenu = document.getElementById("childMenu" + currentDisplayMenu);
            if (childMenu != null) {
                childMenu.style.display = "none";
            }
            var oldStyle = mainMenu.getAttribute("class").toString();
            var newStyle = "";
            if (oldStyle.indexOf("left") >= 0) {
                newStyle = "left";
            } else if (oldStyle.indexOf("right") >= 0) {
                newStyle = "right";
            } else if (oldStyle.indexOf("both") >= 0) {
                newStyle = "both";
            }

            mainMenu.setAttribute("class", newStyle);
            currentDisplayMenu = item;
            var showChildMenu = document.getElementById("childMenu" + currentDisplayMenu);
            var showMainMenu = document.getElementById("mainMenu" + currentDisplayMenu);
            if (showChildMenu != null) {
                showChildMenu.style.display = "";
            }
            oldStyle = "";
            if (showMainMenu.getAttribute("class") != null)
                oldStyle = showMainMenu.getAttribute("class").toString();
            newStyle = "activeTab";
            if (oldStyle.indexOf("left") >= 0) {
                newStyle = newStyle + " left";
            } else if (oldStyle.indexOf("right") >= 0) {
                newStyle = newStyle + " right";
            } else if (oldStyle.indexOf("both") >= 0) {
                newStyle = newStyle + " both";
            }
            showMainMenu.setAttribute("class", newStyle);
            if (showChildMenu != null) {
                var childRow = showChildMenu.getElementsByTagName("tr")[0];
                if (childRow != null) {
                    var childColumn = childRow.getElementsByTagName("td")[1];
                    if (childColumn.className == "parentMenu") {

                    } else {
                        currentMenuItem.className = "";
                        childColumn.setAttribute("class", "activeMenuItem");
                        currentMenuItem = childColumn;
                    }
                }
            }
        } catch (e) {
            alert(e);
        }

    }

    function checkLeftMenu(item) {
        var strIcon = "";
        //Admin
        if (item[2] != null && item[2].toString().indexOf("application.do") >= 0) {
            strIcon = "<img src='share/images/icons/process.jpg' width=16 height=16/>";
        }
        else if (item[2] != null && item[2].toString().indexOf("UserAction.do") >= 0) {
            strIcon = "<img src='share/images/icons/listUser.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("RolesAction.do") >= 0) {
            strIcon = "<img src='share/images/icons/role.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("UserAction!prepareUserOfDept.do") >= 0) {
            strIcon = "<img src='share/images/icons/listUser.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("departmentAction.do") >= 0) {
            strIcon = "<img src='share/images/group.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("departmentAction!prepareChildDept.do") >= 0) {
            strIcon = "<img src='share/images/group.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("home!gotoLogManager.do") >= 0) {
            strIcon = "<img src='share/images/icons/view.png' width=16 height=16/>";
        }

        //van ban den
        else if (item[2] != null && item[2].toString().indexOf("voDocumentReceive.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li1.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareSearchPublish.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li2.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!monitorLeader.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li3.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("record!prepareDeliveryDoc.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li4.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("record!prepareClerical.do") >= 0) {
            strIcon = "<img src='share/images/icons/10.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaff.do?type=forwardProcess") >= 0) {
            strIcon = "<img src='share/images/icons/wait.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaff.do?type=processing") >= 0) {
            strIcon = "<img src='share/images/icons/14.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaff.do?type=processed") >= 0) {
            strIcon = "<img src='share/images/icons/finished-work.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaff.do?type=coordinateProcessed") >= 0) {
            strIcon = "<img src='share/images/icons/finished-work.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaff.do?type=coordinateProcess") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li5.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaff.do?type=justReference") >= 0) {
            strIcon = "<img src='share/images/icons/info.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaffCoWait.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li5.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaffCoProcess.do") >= 0) {
            strIcon = "<img src='share/images/icons/finished-work.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaffJustRef.do") >= 0) {
            strIcon = "<img src='share/images/icons/info.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voDocumentReceive!prepareReport.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li7.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voDocumentReceive!prepareProcessReport.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li7.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("report.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li7.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!preSearchExpiredProcess.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li7.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!preSearchSendReceiveProcess.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li7.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaffProcessing.do") >= 0) {
            strIcon = "<img src='share/images/icons/14.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaffProcessed.do") >= 0) {
            strIcon = "<img src='share/images/icons/finished-work.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepare.do") >= 0) {
            strIcon = "<img src='share/images/icons/wait.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("assignDoc!monitor.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li3.png' width=16 height=16/>";
        }

        //Van ban di
        else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareEntryBook.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li1.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!preSearchDocRequestPublish.do") >= 0) {
            strIcon = "<img src='share/images/icons/document_wait.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareSearchDraftDocToDept.do") >= 0) {
            strIcon = "<img src='share/images/icons/document_wait.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareSearch.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li3.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareSearchDraft.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li6.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!preDraftApproved.do") >= 0) {
            strIcon = "<img src='share/images/icons/finished-work.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareSearchDrafResposeDocReceiveForDept.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/top-user-icon.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareSearchNotifyDoc.do") >= 0) {
            strIcon = "<img src='share/images/icons/tracking.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!preparePrintBookReport.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li7.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!preSearchDocRequest.do") >= 0) {
            strIcon = "<img src='share/images/icons/wait.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!preSearchDocApproved.do") >= 0) {
            strIcon = "<img src='share/images/icons/finished-work.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareSearchDrafResposeDocReceiveForLeader.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li5.png' width=16 height=16/>";
        }

        //danh muc
        else if (item[2] != null && item[2].toString().indexOf("category.do?type=") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/category.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voBooks.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/category.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("position.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/category.png' width=16 height=16/>";
        }

        //tien ich
        else if (item[2] != null && item[2].toString().indexOf("digitizing.do") >= 0) {
            strIcon = "<img src='share/images/icons/kedit.png' width=16 height=16/>";
        }

        //van ban dieu hanh
        else if (item[2] != null && item[2].toString().indexOf("voDocumentDirection!preInsert.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li1.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voDocumentDirection!publishToDirection.do") >= 0) {
            strIcon = "<img src='share/images/icons/document_wait.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voDocumentDirection!onPublish.do") >= 0) {
            strIcon = "<img src='share/images/icons/sign-in.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voDocumentDirection!onPublic.do") >= 0) {
            strIcon = "<img src='share/images/icons/send_document.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voDocumentDirection!isPublished.do") >= 0) {
            strIcon = "<img src='share/images/list.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voDocumentDirection!isPublic.do") >= 0) {
            strIcon = "<img src='share/images/list.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voDocumentDirection!search.do") >= 0) {
            strIcon = "<img src='share/images/layout/new_3_3/li3.png' width=16 height=16/>";
        }

        //ho so cong viec

        else if (item[2] != null && item[2].toString().indexOf("voFiles.do") >= 0) {
            strIcon = "<img src='share/images/list.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voFiles!preProcessedGrid.do") >= 0) {
            strIcon = "<img src='share/images/list.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voFiles!preLeaderGrid.do") >= 0) {
            strIcon = "<img src='share/images/list.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voFiles!preReportDocument.do") >= 0) {
            strIcon = "<img src='share/images/copy.gif' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voFiles!preReportDocumentApprove.do") >= 0) {
            strIcon = "<img src='share/images/icons/list-accept.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voFiles!preInsertDocument.do") >= 0) {
            strIcon = "<img src='share/images/icons/folder_16.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voFiles!preInsertWork.do") >= 0) {
            strIcon = "<img src='share/images/icons/folder_16.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voFiles!preInsertCoordinate.do") >= 0) {
            strIcon = "<img src='share/images/icons/folder_16.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voFiles!prepareReportFileByProcessor.do") >= 0) {
            strIcon = "<img src='share/images/icons/report_icon.jpg' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voFiles!prepareReportFileByType.do") >= 0) {
            strIcon = "<img src='share/images/icons/report_icon.jpg' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voFiles!prepareReportFileByProcessing.do") >= 0) {
            strIcon = "<img src='share/images/icons/report_icon.jpg' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("voFiles!prepareReportFileByLeader.do") >= 0) {
            strIcon = "<img src='share/images/icons/report_icon.jpg' width=16 height=16/>";
        }

        //ho so luu tru
        else if (item[2] != null && item[2].toString().indexOf("formBorrow!prepareReader.do") >= 0) {
            strIcon = "<img src='share/images/list.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("formBorrow!readerFormBorrowSent.do") >= 0) {
            strIcon = "<img src='share/images/list.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("formBorrow!readerFormBorrowApproved.do") >= 0) {
            strIcon = "<img src='share/images/icons/list-accept.png' width=16 height=16/>";
        } else if (item[2] != null && item[2].toString().indexOf("formBorrow!readerBorrowRecordApproved.do") >= 0) {
            strIcon = "<img src='share/images/icons/list-accept.png' width=16 height=16/>";
        } else if (item[2] != null) {
            strIcon = "<img src='share/images/layout/new_3_3/category.png' width=16 height=16/>";
        }
        return strIcon;
    }

    function checkWorkingTime(item) {
        var workingDay = 3;
        /**
         * 0: Tất cả
         * 1: Hôm nay
         * 2: Hôm qua
         * 3: Tuần này
         * 4: Tuần trước
         * 5: Tháng này
         * 6: Tháng trước
         * 7: Năm nay
         * 8: Năm trước
         **/
        try {
            if (item[2] == null || item[2].toString() == "") {
                workingDay = -1;
            }
            // danh sach cac chuc nang co mac dinh la hom nay
            else if (item[2] != null && item[2].toString().indexOf("voDocumentReceive.do") >= 0) {   // Vao so van ban den
                workingDay = 1;
            } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareEntryBook.do") >= 0) {   // Văn bản đến nội bộ
                workingDay = 1;
            }
//            else if(item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareSearchPublish.do")>=0){   // Văn bản đến nội bộ
//                workingDay = 1;
//            }

            //van ban den
            else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareSearchPublish.do") >= 0) {   // Văn bản đến nội bộ
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("assignDoc!monitorLeader.do") >= 0) {  // Tra cứu văn bản
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("record!prepareDeliveryDoc.do") >= 0) { // chuyen luu tru tai lieu
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("record!prepareClerical.do") >= 0) {  // chuyen luu tru ho so
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaff.do?type=forwardProcess") >= 0) {  // van ban cho xu ly
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaff.do?type=processing") >= 0) {    //  văn bản đang xử lý
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaff.do?type=coordinateProcess") >= 0) {     // phoi hop xu ly
                if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaff.do?type=coordinateProcessed") >= 0) {     // phoi hop da xu ly
                    workingDay = 3;
                } else {
                    workingDay = 7;
                }
            } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaff.do?type=justReference") >= 0) {
                workingDay = 5;
            } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaffCoWait.do") >= 0) {  //phoi hop cho xu ly
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaffCoProcess.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaffJustRef.do") >= 0) {
                workingDay = 5;
            } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaffProcessing.do") >= 0) {    // Văn bản đơn vị đang xử lý
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareStaffProcessed.do") >= 0) {     // Văn bản đơn vị đã xử lý   
            } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepare.do") >= 0) {    // van ban cho don vi xu ly
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("assignDoc!monitor.do") >= 0) {  // tra cuu van ban ca nhan
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("assignDoc!prepareForwardDoc.do") >= 0) {  // van ban cho phan phoi
                workingDay = 7;
            }

            //Van ban di
            else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareEntryBook.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!preSearchDocRequestPublish.do") >= 0) {   // van ban cho ban hanh
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareSearchDraftDocToDept.do") >= 0) {  // van ban cho duyet the thuc
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareSearch.do") >= 0) {  //tra cuu van ban di
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareSearchDraft.do") >= 0) {  //van ban du thao
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!preDraftApproved.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareSearchDrafResposeDocReceiveForDept.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareSearchNotifyDoc.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!preparePrintBookReport.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!preSearchDocRequest.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!preSearchDocApproved.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("voPublishDocument!prepareSearchDrafResposeDocReceiveForLeader.do") >= 0) {
                workingDay = 7;
            }


            //van ban dieu hanh
            else if (item[2] != null && item[2].toString().indexOf("voDocumentDirection!preInsert.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("voDocumentDirection!publishToDirection.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("voDocumentDirection!onPublish.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("voDocumentDirection!onPublic.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("voDocumentDirection!isPublished.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("voDocumentDirection!isPublic.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("voDocumentDirection!search.do") >= 0) {
                workingDay = 7;
            }

            //ho so cong viec

            else if (item[2] != null && item[2].toString().indexOf("voFiles.do") >= 0) { // danh sach ho so cho xu ly
                workingDay = 1;
            } else if (item[2] != null && item[2].toString().indexOf("voFiles!preProcessedGrid.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("voFiles!preLeaderGrid.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("voFiles!preReportDocument.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("voFiles!preReportDocumentApprove.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("voFiles!preInsertDocument.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("voFiles!preInsertWork.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("voFiles!preInsertCoordinate.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("voFiles!prepareReportFileByProcessor.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("voFiles!prepareReportFileByType.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("voFiles!prepareReportFileByProcessing.do") >= 0) {
            } else if (item[2] != null && item[2].toString().indexOf("voFiles!prepareReportFileByLeader.do") >= 0) {
            }

            //ho so luu tru
            else if (item[2] != null && item[2].toString().indexOf("formBorrow!prepareReader.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("formBorrow!readerFormBorrowSent.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("formBorrow!readerFormBorrowApproved.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("formBorrow!readerBorrowRecordApproved.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("record!prepareAddRecord.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("record.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("record!storedRecord.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("record!prepareSearch.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("record!waitDeliveryRecord.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("deliveryRecord!prepareClerical.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("record!deliveryRecord.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("deliveryRecord.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("borrowBook.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("formBorrow.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("formBorrow!reviewPage.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("formBorrow!approvePage.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("borrowBook!expiredDocumentList.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("record!recordPreDestroy.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("destroyRecord.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("destroyRecord!loadDestroyedRecordList.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("formBorrow!preReportFormBorrow.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("formBorrow!preReportBorrowedRecord.do") >= 0) {
                workingDay = 7;
            } else if (item[2] != null && item[2].toString().indexOf("deliveryRecord!prepareSearchReceiveRecord.do") >= 0) {
                workingDay = 7;
            }
        } catch (ex) {

        }
        return workingDay;
    }

    function drawLeftMenuItem(item, level, showContent) {
        var strIcon = checkLeftMenu(item);
        var workingDay = checkWorkingTime(item);
        var strItem = "<tr onclick='clickMenuItem(this);changeWorkingDay(" + workingDay + ");" + item[2] + "'><td width=16><img src='share/images/layout/new_3_3/li-unselect.png' width=16 height=16 alt='Mở menu con' style='float:right'/></td><td class='parentMenu' >" + strIcon + item[1] + "</td></tr>";
        var isFirst = false;
        if (workingDay == -1) {
            strItem = "<tr onclick='clickMenuItem(this);" + item[2] + "'><td width=16><img src='share/images/layout/new_3_3/li-unselect.png' width=16 height=16 alt='Mở menu con' style='float:right'/></td><td class='parentMenu' >" + strIcon + item[1] + "</td></tr>";
        } else {
            if (firstMenuItem == "") {
                firstMenuItem = "changeWorkingDay(" + workingDay + ");" + item[2];
                isFirst = true;
            }
        }
        if (level > 1) {
            //strItem ="<tr onclick='clickMenuItem(this);"+item[2]+"'><td class='parentMenu'>"+item[1]+"</td></tr>";
            if (isFirst) {
                strItem = "<tr onclick='clickMenuItem(this);changeWorkingDay(" + workingDay + ");" + item[2] + "'><td width=16 class='activeMenuItem'></td><td>" + item[1] + "</td></tr>";
            } else {
                strItem = "<tr onclick='clickMenuItem(this);changeWorkingDay(" + workingDay + ");" + item[2] + "'><td width=16></td><td>" + item[1] + "</td></tr>";
            }
        }
        if (item.length > 5) {
            var i = 5;
            var subItem = "<tr style='display:none'><td colspan='2'><table class='tableLevel" + level + "'>";
            if (showContent == "true") {
                subItem = "<tr ><td colspan='2'><table class='tableLevel" + level + "'>";
            }
            for (i = 5; i < item.length; i++) {
                subItem = subItem + drawLeftMenuItem(item[i], level + 1);
            }
            subItem = subItem + "</table></td></tr>";
            strItem = strItem + subItem;
        } else {
            //strItem ="<tr onclick='clickMenuItem(this);"+item[2]+"'><td class='parentMenu'>"+item[1]+"</td></tr>";
            if (isFirst) {
                strItem = "<tr onclick='clickMenuItem(this);changeWorkingDay(" + workingDay + ");" + item[2] + "'><td width='16'>" + strIcon + "</td><td>" + item[1] + "</td></tr>";
            } else {
                strItem = "<tr onclick='clickMenuItem(this);changeWorkingDay(" + workingDay + ");" + item[2] + "'><td width='16'>" + strIcon + "</td><td>" + item[1] + "</td></tr>";
            }

        }
        return strItem;
    }

    function drawMainMenuItem(count, item, isDefault) {
        var strItem = "";
        if (isDefault == "true") {
            currentDisplayMenu = count;
        }
        if (item.length > 5) {
            var i = 5;
            var subItem = "<tr id='childMenu" + count + "' style='display:none'><td><table class='leftMenu'>";
            if (isDefault == "true") {
                subItem = "<tr id='childMenu" + count + "' ><td><table class='leftMenu'>";
            }
            for (i = 5; i < item.length; i++) {
                subItem = subItem + drawLeftMenuItem(item[i], 1);
            }
            subItem = subItem + "</table></td></tr>";
            strItem = strItem + subItem;
        }
        return strItem;
    }

    function drawMainMenu(mainId, leftId, menu) {
        var mainMenu = document.getElementById(mainId);
        var leftMenu = document.getElementById(leftId);
        var strMainMenu = "<table style='padding:0; margin:0; border-spacing:0px'><tr>";
        var strLeftMenu = "<table class='leftbar'>";
        var i = 0;
        var countMenu = 0;
        for (i = 0; i < menu.length; i++) {
            try {
                var item = menu[i];
                if (!item)
                    continue;
                var strMainItem;
                var showTime = "showTimeLine();hideTimeLineForSearch=0;";
                if (item[1] == "Qu&#7843;n tr&#7883; h&#7879; th&#7889;ng") {
                    showTime = "hideTimeLine();hideTimeLineForSearch=2;";
                } else if (item[1] == "Danh m&#7909;c") {
                    showTime = "hideTimeLine();hideTimeLineForSearch=2;";
                } else if (item[1] == "Qu&#7843;n l&yacute; lu&#7891;ng") {
                    showTime = "hideTimeLine();hideTimeLineForSearch=2;";
                } else if (item[1] == "Ti&#7879;n &iacute;ch") {
                    showTime = "hideTimeLine();hideTimeLineForSearch=2;";
                } else if (item[1] == "V&#259;n b&#7843;n &#273;i&#7873;u h&agrave;nh") {
                    showTime = "hideTimeLine();hideTimeLineForSearch=2;";
                } else if (item[1] == "H&#7891; s&#417; l&#432;u tr&#7919;") {
                    showTime = "hideTimeLine();hideTimeLineForSearch=2;";
                }
                if (item.length > 5) {
                    var workingDay = checkWorkingTime(item[5]);
                    var timeFunction = "changeWorkingDay(" + workingDay + ");" + showTime;
                    if (workingDay == -1) {
                        timeFunction = "";
                    }
                    strMainItem = "<td id='mainMenu" + i.toString() + "' onclick='showLeftMenu(" + i + ");" + timeFunction + ";" + item[5][2] + "'>" + item[1] + "</td>";
                    if (countMenu == 0 && i == menu.length - 1) {
                        eval(showTime.toString());
                        strMainItem = "<td id='mainMenu" + i.toString() + "' class='activeTab both' onclick='showLeftMenu(" + i + ");" + timeFunction + ";" + item[5][2] + "'>" + item[1] + "</td>";
                    } else if (countMenu.toString() == "0") {
                        eval(showTime.toString());
                        strMainItem = "<td id='mainMenu" + i.toString() + "' class='activeTab left' onclick='showLeftMenu(" + i + ");" + timeFunction + ";" + item[5][2] + "'>" + item[1] + "</td>";
                    } else if (i == menu.length - 1) {
                        strMainItem = "<td id='mainMenu" + i.toString() + "' class='right' onclick='showLeftMenu(" + i + ");" + timeFunction + ";" + item[5][2] + "'>" + item[1] + "</td>";
                    }
                } else {
                    strMainItem = "<td id='mainMenu" + i.toString() + "' onclick='showLeftMenu(" + i + ");" + timeFunction + "'>" + item[1] + "</td>";
                }
                countMenu = countMenu + 1;
                var strLeftItem = "";
                if (i <= 1)
                    strLeftItem = drawMainMenuItem(i.toString(), item, "true");
                else
                    strLeftItem = drawMainMenuItem(i.toString(), item, "false");
                strMainMenu = strMainMenu + strMainItem;
                strLeftMenu = strLeftMenu + strLeftItem;
            } catch (e) {
            }
        }
        strMainMenu = strMainMenu + "</tr></table>";
        strLeftMenu = strLeftMenu + "</table>";
        mainMenu.innerHTML = strMainMenu;
        leftMenu.innerHTML = strLeftMenu;
    }

    function getImageURLOfFunction(url) {
        var urlImg = "share/images/menu/Phe duyet tai khoan.png";
        if (url.toString().indexOf("filesAction!toSelectProcedurePage.do") >= 0) {
            // Khai bao ho so
            urlImg = "share/images/menu/Khai bao.png";
        } else if (url.toString().indexOf("filesAction!toBusinessListPage.do") >= 0) {
            // Danh sach ho so
            urlImg = "share/images/menu/Danh sach.png";
        } else if (url.toString().indexOf("filesAction!toBusinessAdditionPage.do") >= 0) {
            // Nop ho so
            urlImg = "share/images/document/1_thamdinh.png";
        } else if (url.toString().indexOf("filesAction!toReceived.do") >= 0) {
            // tiep nhan ho so
            urlImg = "share/images/menu/Phan cong.png";
        } else if (url.toString().indexOf("filesAction!assignEvaluation.do") >= 0) {
            // Phan cong tham dinh ho so
            urlImg = "share/images/menu/Phan cong.png";
        } else if (url.toString().indexOf("filesAction!toApprovePage.do") >= 0) {
            // Phe duyet ho so
            urlImg = "share/images/document/1_thamdinh.png";
        } else if (url.toString().indexOf("filesAction!toEvaluatePage.do") >= 0) {
            // tham dinh ho so
            urlImg = "share/images/document/1_thamdinh.png";
        } else if (url.toString().indexOf("filesAction!proposeEvaluation.do") >= 0) {
            // de xuat tham dinh ho so
            urlImg = "share/images/menu/files.png";
        } else if (url.toString().indexOf("filesAction!toReviewPage.do") >= 0) {
            // xem xet ho so
            urlImg = "share/images/document/1_thamdinh.png";
        } else if (url.toString().indexOf("filesAction!toApprovePage.do") >= 0) {
            urlImg = "share/images/menu/files.png";
        } else if (url.toString().indexOf("filesAction!toApprovePage.do") >= 0) {
            urlImg = "share/images/menu/files.png";
        } else if (url.toString().indexOf("category.do?type=SP") >= 0) {
            // danh muc
            urlImg = "share/images/menu/Nhom san pham.png";
        } else if (url.toString().indexOf("categoryAction.do") >= 0) {
            // danh muc dong
            urlImg = "share/images/menu/Danh muc dong.png";
        } else if (url.toString().indexOf("categoryTypeAction.do") >= 0) {
            // danh muc dong
            urlImg = "share/images/menu/Don vi.png";
        } else if (url.toString().indexOf("procedureAction.do") >= 0) {
            // danh muc dong
            urlImg = "share/images/menu/Thu tuc hanh chinh.png";
        } else if (url.toString().indexOf("businessAction.do") >= 0) {
            // doanh nghiep
            urlImg = "share/images/menu/Doanh nghiep.png";
        } else if (url.toString().indexOf("position.do") >= 0) {
            // chuc danh
            urlImg = "share/images/menu/Chuc danh.png";
        } else if (url.toString().indexOf("technicalStandardAction.do?createForm.standardType=0") >= 0) {
            urlImg = "share/images/menu/Cau hinh he thong.png";
        } else if (url.toString().indexOf("application.do") >= 0) {
            urlImg = "share/images/menu/Ung dung.png";
        } else if (url.toString().indexOf("UserAction.do") >= 0) {
            urlImg = "share/images/menu/Nguoi dung.png";
        } else if (url.toString().indexOf("RolesAction.do") >= 0) {
            urlImg = "share/images/menu/Vai tro.png";
        } else if (url.toString().indexOf("departmentAction.do") >= 0) {
            urlImg = "share/images/menu/Phong ban.png";
        } else if (url.toString().indexOf("flow.do") >= 0) {
            urlImg = "share/images/menu/Quan ly luong.png";
        } else if (url.toString().indexOf("home!gotoLogManager.do") >= 0) {
            urlImg = "share/images/menu/Log he thong.png";
        } else if (url.toString().indexOf("eventLogLoginAction.do") >= 0) {
            urlImg = "share/images/menu/Log he thong.png";
        } else if (url.toString().indexOf("parameterAction.do") >= 0) {
            urlImg = "share/images/menu/Cau hinh he thong.png";
        } else if (url.toString().indexOf("registerSearchAction.do") >= 0) {
            urlImg = "share/images/menu/Phe duyet tai khoan.png";
        } else if (url.toString().indexOf("registerCreateAction.do") >= 0) {
            urlImg = "share/images/menu/Dang ky tai khoan.png";
        } else if (url.toString().indexOf("filesAction!lookupFilesByLeader.do") >= 0) {
            urlImg = "share/images/document/6_chuyenvientracuu.png";
        } else if (url.toString().indexOf("filesAction!lookupFilesByStaff.do") >= 0) {
            urlImg = "share/images/document/6_chuyenvientracuu.png";
        } else if (url.toString().indexOf("filesAction!toCoEvaluate.do") >= 0) {
            urlImg = "share/images/menu/cooperate.png";
        } else if (url.toString().indexOf("filesAction!toRepositorySearch.do") >= 0) {
            urlImg = "share/images/document/5_qlluutru.png";
        }
        else if (url.toString().indexOf("filesAction!lookupFilesByStaffDonothing.do") >= 0) {
            urlImg = "share/images/document/6_chuyenvientracuu.png";
        }
        else if (url.toString().indexOf("departmentAction!prepareChildDept.do") >= 0) {
            urlImg = "share/images/menu/Don vi.png";
        }
        else if (url.toString().indexOf("category.do?type=DVI") >= 0) {
            urlImg = "share/images/menu/Don vi.png";
        }
        else if (url.toString().indexOf("category.do?type=PROVINCE") >= 0) {
            urlImg = "share/images/menu/Tinh thanh pho.png";
        }
        else if (url.toString().indexOf("vietnameseStandardAction.do") >= 0) {
            urlImg = "share/images/menu/TCVN.png";
        }
        else if (url.toString().indexOf("category.do?type=DP") >= 0) {
            urlImg = "share/images/menu/San pham.png";
        }
        else if (url.toString().indexOf("category.do?type=NATION") >= 0) {
            urlImg = "share/images/menu/Quoc gia.png";
        }
        else if (url.toString().indexOf("technicalStandardAction.do?createForm.standardType=1") >= 0) {
            urlImg = "share/images/menu/Quy chuan ky thuat.png";
        }







        else if (url.toString().indexOf("UserAction!prepareUserOfDept.do") >= 0) {
            urlImg = "share/images/menu/Nguoi dung cua don vi.png";
        }

        // thong bao sua doi bo sung
        else if (url.toString().indexOf("filesAction!toFeedbackEvaluatePage.do") >= 0) {
            urlImg = "share/images/menu/SDBS.png";
        }
        // quan ly luu tru
        else if (url.toString().indexOf("filesAction!lookupRepositories.do") >= 0) {
            urlImg = "share/images/document/5_qlluutru.png";
        }
        // dat yeu cau nhap khau
        else if (url.toString().indexOf("confirmImportSatistPaperAction.do") >= 0) {
            urlImg = "share/images/menu/Xem xet.png";
        }
        // cong bo phu hop quy dinh attp
        else if (url.toString().indexOf("confirmAnnouncementPaperAction.do") >= 0) {
            urlImg = "share/images/menu/SDBS.png";
        }
        // tra cuu ( lanh dao phong )
        else if (url.toString().indexOf("filesAction!lookupFilesByLeaderOfStaff.do") >= 0) {
            urlImg = "share/images/menu/Tim kiem.png";
        }
        // sdbs (lanh dao phong)
        else if (url.toString().indexOf("filesAction!toAdditionReviewPage.do") >= 0) {
            urlImg = "share/images/menu/SDBS.png";
        }
        // doi chieu lai ( van thu )
        else if (url.toString().indexOf("filesAction!toComparisonFail.do") >= 0) {
            urlImg = "share/images/menu/Doi chieu lai.png";
        }
        // tra cuu ( van thu )
        else if (url.toString().indexOf("filesAction!lookupFilesByClerical.do") >= 0) {
            urlImg = "share/images/menu/Tim kiem.png";
        }
        // doi chieu ho so ( van thu )
        else if (url.toString().indexOf("filesAction!toComparison.do") >= 0) {
            urlImg = "share/images/menu/Doi chieu ho so.png";
        }

        else {
            urlImg = "share/images/menu/Log he thong.png";
        }
        return urlImg;
    }

    function showTab(name) {
        try {
            var hideItem = localStorage.currentItem;
            if (hideItem != null) {
                var hideTab = document.getElementsByName("li" + hideItem);
                hideTab[0].setAttribute("class", "");
                var hideItems = document.getElementsByName("group" + hideItem);
                var i = 0;
                for (i = 0; i < hideItems.length; i++) {
                    hideItems[i].setAttribute("style", "display:none");
                }
            }
        } catch (e) {

        }
        var items = document.getElementsByName("group" + name);
        if (items == null || items.length == 0) {
            name = "1";
            items = document.getElementsByName("group" + name);
        }
        var i = 0;
        for (i = 0; i < items.length; i++) {
            items[i].setAttribute("style", "display:''");
        }
        var displayTab = document.getElementsByName("li" + name);
        displayTab[0].setAttribute("class", "selected");
        localStorage.currentItem = name;
    }

    function drawOfficeMenu(tabId, contentId, menu) {
        var headerTab = document.getElementById(tabId);
        var contentMenu = document.getElementById(contentId);
        var i = 0;
        var countMenu = 0;
        for (i = 0; i < menu.length; i++) {
            try {
                var item = menu[i];
                if (!item)
                    continue;
                var strMainItem;
                if (item.length > 5) {
                    var tabItem = document.createElement("li");
                    tabItem.innerHTML = item[1];
                    tabItem.setAttribute("name", "li" + i);
                    tabItem.setAttribute("onclick", "showTab(" + i + ")");
                    headerTab.appendChild(tabItem);
                    var j = 0;
                    for (j = 5; j < item.length; j++) {
                        var groupItem = item[j];
                        var divGroup = document.createElement("div");
                        divGroup.setAttribute("class", "group");
                        divGroup.setAttribute("name", "group" + i);
                        divGroup.setAttribute("style", "display:none");
                        if (groupItem.length <= 5) {
                            var url = getImageURLOfFunction(groupItem[2]);
                            var img = document.createElement("img");
                            img.setAttribute("src", url);
                            img.setAttribute("style", "width:55px;height:55px");
                            divGroup.appendChild(img);
                            divGroup.setAttribute("onclick", groupItem[2]);
                        } else {
                            var h = 0;
                            for (h = 5; h < groupItem.length; h++) {
                                var divItem = document.createElement("div");
                                divItem.setAttribute("class", "item");
                                var url = getImageURLOfFunction(groupItem[h][2]);
                                var img = document.createElement("img");
                                img.setAttribute("src", url);
                                divItem.appendChild(img);
                                var br = document.createElement("br");
                                divItem.appendChild(br);
                                var divTitle = document.createElement("div")
                                divTitle.innerHTML = groupItem[h][1];
                                divItem.appendChild(divTitle);
                                divItem.setAttribute("onclick", groupItem[h][2]);
                                divGroup.appendChild(divItem);
                            }
                        }
                        var bottomTitle = document.createElement("div");
                        if (groupItem.length > 5) {
                            bottomTitle.setAttribute("class", "title");
                        } else {
                            bottomTitle.setAttribute("class", "function");
                        }
                        var title = groupItem[1];
                        bottomTitle.innerHTML = title;
                        divGroup.appendChild(bottomTitle);
                        //divGroup.setAttribute("title",groupItem[1]);
                        contentMenu.appendChild(divGroup);
                    }
                } else {
                }
                countMenu = countMenu + 1;
                var strLeftItem = "";
                if (i <= 1)
                    strLeftItem = drawMainMenuItem(i.toString(), item, "true");
                else
                    strLeftItem = drawMainMenuItem(i.toString(), item, "false");
            } catch (e) {
            }
        }
    }

    function makeMenu(menuModel) {
        try {
            drawOfficeMenu('menuUL', 'tabContent', menuModel);
            var item = localStorage.currentItem;
            if (item != null) {
                showTab(item);
            } else {
                showTab(1);
            }
            //drawMainMenu("vt-mainfunction","myMenuID",menuModel);
        } catch (e) {
            alert("JSException when create the menu: \n" + e.message);
        }
    }

    function checkToRefresh(event) {
        var keyID = (window.event) ? window.event.keyCode : event.keyCode;
        if (keyID == 116) {
            try {
                cancelEvent(event);
                doGoToMenu(g_latestClickedMenu);
                return false;
            } catch (ex) {
                alert("Exception in checkToRefresh: \n" + ex.message);
            }
        }
    }

    function cancelEvent(evt) {
        var e = evt ? evt : window.event;
        if (e.stopPropagation)
            e.stopPropagation();
        if (e.preventDefault)
            e.preventDefault();
        e.cancelBubble = true;
        e.cancel = true;
        e.returnValue = false;
        return false;
    }

    //[ LongH says: shortcuts to [sd/vt].[operator/connector] members @4Apr11
    function doGoToMenu() {
        var url, menuItemKey;
        switch (arguments.length) {
            case 1:
                url = arguments[0];
                menuItemKey = "";
                break;
            case 2:
                url = arguments[0];
                menuItemKey = arguments[1];
                break;
        }

        if (url && url.length > 0) {
            sd.operator.execMenu.call(sd.operator, url, menuItemKey);
        }
    }

    function doPost() {
        sd.connector.post.apply(sd.connector, arguments);
    }

    function doUpdateArea(inputParam) {
        sd.connector.updateArea.call(sd.connector, inputParam);
    }

    function doGetJSON(inputParam) {
        sd.connector.getJSON.call(sd.connector, inputParam);
    }

    function getCKE(/*String*/ id) {
        var ed = CKEDITOR.instances[id];
        return ed;
    }

    function getCKEValue(/*String*/ id) {
        var ed = getCKE(id);
        if (ed != null && ed != undefined) {
            return ed.getData();
        } else {
            return undefined;
        }
    }

    function deployCKE(/*String*/ id) {
        try {
            CKEDITOR.replace(id,
                    {
                        language:
                                'vi',
                        filebrowserBrowseUrl:
                                sd.operator.path["PlugInFolder"] + '/ckfinder/ckfinder.html',
                        filebrowserImageBrowseUrl:
                                sd.operator.path["PlugInFolder"] + '/ckfinder/ckfinder.html?type=Images',
                        filebrowserFlashBrowseUrl:
                                sd.operator.path["PlugInFolder"] + '/ckfinder/ckfinder.html?type=Flash',
                        filebrowserUploadUrl:
                                sd.operator.path["context"] + '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
                        filebrowserImageUploadUrl:
                                sd.operator.path["context"] + '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
                        filebrowserFlashUploadUrl:
                                sd.operator.path["context"] + '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
                    });
        } catch (e) {
            alert("JSEx, include.jsp, deployCKE: " + e.message);
        }
    }

    function delCKE(/*String*/ id) {
        var ed = getCKE(id);
        if (ed != null && ed != undefined) {
            try {
                ed.destroy();
            } catch (e) {
                ed.destroy();
            }
        }
    }
    //] LongH says: shortcuts to [sd/vt].[operator/connector] members @4Apr11
    //] LongH says: RDWF's global & utility functions @7Apr11

    /******************************************************************************************************************/

    //[ LongH says: dojo & 3rd party lib initiation
    dojo.require("dojo.parser");
    dojo.require("dijit._base.manager");
    dojo.require("dijit.dijit"); // optimize: load dijit layer
    dojo.require("dijit.TitlePane");
    dojo.require("dojo.data.ItemFileWriteStore");
    dojo.require("dojo.back");
    //[ Project team modify here
    sd.operator.setMenuTargetId("bodyContent");
    //sd.operator.setMenuTargetId("homepage-myRightPanel");
    sd.operator.setTimeout(180);
    sd.operator.setDebugMode(false);
    //] Project team modify here

    sd.operator.path["context"] = "${contextPath}";
    sd.operator.path["externalResources"] = "${externalResourcesPath}";
    sd.operator.path["PlugInFolder"] = "${PlugInFolderName}";
    sd.operator.path["ImgFolder"] = "${ImgFolderName}";
    sd.operator.path["CSSFolder"] = "${CSSFolderName}";
    sd.operator.path["JSVTFolder"] = "${JSVTFolderName}";
    sd.operator.path["JSLibFolder"] = "${JSLibFolderName}";
    sd.operator.path["UploadFolder"] = "${UploadFolderName}";
    sd.operator.path["UploadImageFolder"] = "${UploadImageFolderName}";
    sd.operator.path["UploadFlashFolder"] = "${UploadFlashFolderName}";
    sd.operator.logoutURL = "${logoutUrl}";
    sd.operator.loginURL = "${loginUrl}";
    sd.log.level = sd.log.TRACE;
    sd.log.logPrefix = "JSException has been thrown \n";
    sd.validator.stackPrefix = "vt-validator-stack-";
    var vt = sd; // previous code compatible
    var page = sd.page; // previous code compatible
    var app = sd.app;
    dojo.addOnLoad(function () {
        var appState = new ApplicationState("", "");
        dojo.back.setInitialState(appState);
    });
    fwlog = function (id, message, e) {
        console.log("[Control ID:" + id + ". " + message);
        if (e)
            console.log(e);
        console.log("]");
    }
    //
    Base.esapi.properties.application.Name = "My Application v1.0";
    // Initialize the api
    org.owasp.esapi.ESAPI.initialize();
    //
    var vtSecurity = $ESAPI;
    //] LongH says: dojo & 3rd party lib initiation

    function fillDataFromApplet(data)
    {
        if (typeof page.submitSignForm == 'function')
        {
            page.submitSignForm(data);
        }
    }

    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + "; " + expires;
    }

    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ')
                c = c.substring(1);
            if (c.indexOf(name) == 0)
                return c.substring(name.length, c.length);
        }
        return "";
    }


    function checkCookie() {
        var username = getCookie("username");
        if (username != "") {
            alert("Welcome again " + username);
        } else {
            username = prompt("Please enter your name:", "");
            if (username != "" && username != null) {
                setCookie("username", username, 365);
            }
        }
    }

    function decodeBase64(str) {
        return decodeURIComponent(escape(window.atob(str)));
    }

    function encodeBase64(str) {
        return window.btoa(unescape(encodeURIComponent(str)));
    }

    function sleep(milliseconds) {
        var start = new Date().getTime();
        for (var i = 0; i < 1e7; i++) {
            if ((new Date().getTime() - start) > milliseconds) {
                break;
            }
        }
    }

    function reloadFromApplet(data)
    {
        onApprove();
    }

    // Lanh dao phong
    function reloadFromAppletLdp(data)
    {
        onApproveLdp();
    }
</script>

<%--[ LongH says: JS Feature profiles--%>
<%--<script type="text/javascript" src="${CSS_JS_contextPath}/share/profile/profile_changeDialogPosition.js"></script>--%>
<%--] LongH says: JS Feature profiles--%>
<script src="${JSVTFolderName}/qllltp.js" type="text/javascript"></script>
<script src="${JSVTFolderName}/vt-token.js" type="text/javascript"></script>
