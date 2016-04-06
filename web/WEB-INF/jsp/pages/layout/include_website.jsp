<%@page import="java.util.ResourceBundle"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Locale"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


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

<link href="${ImgFolderName}/layout/new_3_3/favicon.ico" type="image/x-icon" rel="shortcut icon" />
<link href="${CSSFolderName}/layout_website.css" charset="UTF-8" type="text/css" rel="stylesheet" />
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

<%--[ LongH says: For debuging js-blackbox--%>
<script src="debug.js" type="text/javascript"></script>
<%--] LongH says: For debuging js-blackbox--%>

<%--[ LongH says: Test CKEditor--%>
<script src="${PlugInFolderName}/ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="${PlugInFolderName}/ckfinder/ckfinder.js" type="text/javascript"></script>
<%--] LongH says: Test CKEditor--%>

<script type="text/javascript">
    //[ LongH says: RDWF's global & utility functions @7Apr11
    function updatePageInfo( userName, actionTitle ) {
        try {
            updateActionInfo( actionTitle );
            profileIcon.innerHTML = userName;
        } catch(e) {
            alert("JSException in updatePageInfo: \n" + e.message);
        }
    }

    function updateDocumentTitle( docTitle ) {
        document.title = docTitle;
    }

    function updateActionInfo( actionTitle ) {
        sd.$( "vt-titleAction" ).innerHTML = actionTitle;
    }

    function cmDrawSubMenu(subMenu,prefix,id,nodeProperties,zIndexStart,menuInfo,menuID)
    {
        var str='<div class="'+prefix+'SubMenu" id="'+id+'" style="z-index: '+zIndexStart+';position: absolute; top: 0px; left: 0px;">';
        if(nodeProperties.subMenuHeader)
            str+=nodeProperties.subMenuHeader;
        str+='<table summary="sub menu" id="'+id+'Table" cellspacing="'+nodeProperties.subSpacing+'" class="'+prefix+'SubMenuTable">';
        var strSub='';
        var item;
        var idSub;
        var hasChild;
        var i;
        var classStr;
        for(i=5;i<subMenu.length;++i)
        {
            item=subMenu[i];
            if(!item)
                continue;
            if(item==_cmSplit)
                item=cmSplitItem(prefix,0,true);
            item.parentItem=subMenu;
            item.subMenuID=id;
            hasChild=(item.length>5);
            idSub=hasChild?cmNewID():null;
            str+='<tr class="'+prefix+'MenuItem"';
            if(item[0]!=_cmNoClick)
                str+=cmActionItem(item,0,idSub,menuInfo,menuID);
            else
                str+=cmNoClickItem(item,0,idSub,menuInfo,menuID);
            str+='>';
            if(item[0]==_cmNoAction||item[0]==_cmNoClick)
            {
                str+=cmNoActionItem(item);
                str+='</tr>';continue;
            }
            classStr=prefix+'Menu';
            classStr+=hasChild?'Folder':'Item';
            str+='<td class="'+classStr+'Left">';
            if(item[0]!=null)
                str+=item[0];
            else
                str+=hasChild?nodeProperties.folderLeft:nodeProperties.itemLeft;
            str+='</td><td class="'+classStr+'Text">'+item[1];
            str+='</td><td class="'+classStr+'Right">';
            if(hasChild)
            {
                str+=nodeProperties.folderRight;
                strSub+=cmDrawSubMenu(item,prefix,idSub,nodeProperties,zIndexStart+nodeProperties.zIndexInc,menuInfo,menuID);
            }
            else
                str+=nodeProperties.itemRight;
            str+='</td></tr>';
        }
        str+='</table>';
        if(nodeProperties.subMenuFooter)
            str+=nodeProperties.subMenuFooter;str+='</div>'+strSub;
        return str;
    }

    function cmActionItem(item,isMain,idSub,menuInfo,menuID)
    {
        _cmItemList[_cmItemList.length]=item;
        var index=_cmItemList.length-1;
        idSub=(!idSub)?'null':('\''+idSub+'\'');
        var clickOpen=menuInfo.nodeProperties.clickOpen;
        var onClick=(clickOpen==3)||(clickOpen==2&&isMain);
        var param='this,'+isMain+','+idSub+','+menuID+','+index;
        var returnStr;
        if(onClick)
            returnStr=' onmouseover="cmItemMouseOver('+param+',false)" onmousedown="cmItemMouseDownOpenSub ('+param+')"';
        else
            returnStr=' onmouseover="cmItemMouseOverOpenSub ('+param+')" onmousedown="cmItemMouseDown ('+param+')"';return returnStr+' onmouseout="cmItemMouseOut ('+param+')" onmouseup="cmItemMouseUp ('+param+')"';
    }
    
    function cmdDrawLeftMenu(id,menu,orient,nodeProperties,prefix)
    {
        var obj=cmGetObject(id);
        if(!prefix)
            prefix=nodeProperties.prefix;
        if(!prefix)
            prefix='';
        if(!nodeProperties)
            nodeProperties=_cmNodeProperties
        if(!orient)
            orient='hbr';
        var menuID=cmAllocMenu(id,menu,orient,nodeProperties,prefix);
        var menuInfo=_cmMenuList[menuID];if(!nodeProperties.delay)
            nodeProperties.delay=_cmNodeProperties.delay;
        if(!nodeProperties.clickOpen)
            nodeProperties.clickOpen=_cmNodeProperties.clickOpen;
        if(!nodeProperties.zIndexStart)
            nodeProperties.zIndexStart=_cmNodeProperties.zIndexStart;
        if(!nodeProperties.zIndexInc)
            nodeProperties.zIndexInc=_cmNodeProperties.zIndexInc
        if(!nodeProperties.offsetHMainAdjust)
            nodeProperties.offsetHMainAdjust=_cmNodeProperties.offsetHMainAdjust;
        if(!nodeProperties.offsetVMainAdjust)
            nodeProperties.offsetVMainAdjust=_cmNodeProperties.offsetVMainAdjust;
        if(!nodeProperties.offsetSubAdjust)
            nodeProperties.offsetSubAdjust=_cmNodeProperties.offsetSubAdjust;
        menuInfo.cmFrameMasking=_cmFrameMasking;
        var str='<table summary="main menu" class="'+prefix+'Menu" cellspacing="'+nodeProperties.mainSpacing+'">';
        var strSub='';
        var vertical ;
        
        if(orient.charAt(0)=='h'){
            str+='<tr>';
            vertical=false;
        }else{  
            vertical=true;
        }
        var i;
        var item;
        var idSub;
        var hasChild;
        var classStr;
        for(i=0;i<menu.length;++i){
            item=menu[i];
            alert(item);
            if(!item)
                continue;
            item.menu=menu;
            item.subMenuID=id;
            str+=vertical?'<tr':'<td';
            str+=' class="'+prefix+'MainItem"';
            hasChild=(item.length>5);
            idSub=hasChild?cmNewID():null;
            str+=cmActionItem(item,1,idSub,menuInfo,menuID)+'>';
            if(item==_cmSplit)
                item=cmSplitItem(prefix,1,vertical);
            if(item[0]==_cmNoAction||item[0]==_cmNoClick){
                str+=cmNoActionItem(item);
                str+=vertical?'</tr>':'</td>';continue;
            }
            classStr=prefix+'Main'+(hasChild?'Folder':'Item');
            str+=vertical?'<td':'<span';
            str+=' class="'+classStr+'Left">';
            str+=(item[0]==null)?(hasChild?nodeProperties.mainFolderLeft:nodeProperties.mainItemLeft):item[0];
            str+=vertical?'</td>':'</span>';
            str+=vertical?'<td':'<span';str+=' class="'+classStr+'Text">';
            str+=item[1];str+=vertical?'</td>':'</span>';
            str+=vertical?'<td':'<span';
            str+=' class="'+classStr+'Right">';
            str+=hasChild?nodeProperties.mainFolderRight:nodeProperties.mainItemRight;
            str+=vertical?'</td>':'</span>';
            str+=vertical?'</tr>':'</td>';
            if(hasChild)
                strSub+=cmDrawSubMenu(item,prefix,idSub,nodeProperties,nodeProperties.zIndexStart,menuInfo,menuID);
        }
        if(!vertical)
            str+='</tr>';str+='</table>'+strSub;
        obj.innerHTML=str;
    }
    
    //
    // 
    //
    function drawItem(item, level, showContent){
        var strItem ="<tr onclick='clickMenuItem(this);"+item[2]+"'><td class='parentMenu'>"+item[1]+"</td></tr>";
        if(item.length > 5){
            var i = 5;
            var subItem = "<tr style='display:none'><td><table class='tableLevel"+level+"'>";
            if(showContent == "true"){
                subItem = "<tr ><td><table class='tableLevel"+level+"'>";
            }
            for(i = 5;i<item.length;i++){
                subItem = subItem + drawItem(item[i],level+1);
            }
            subItem = subItem +"</table></td></tr>";
            strItem = strItem + subItem;
        } else {
            strItem ="<tr onclick='clickMenuItem(this);"+item[2]+"'><td>"+item[1]+"</td></tr>"; 
        }
        return strItem;
    }
    //
    // Ve thuan tuy menu ben trai
    //

    function drawLeft(id,menu){
        var obj=cmGetObject(id);
        var i=0;
        var str = "<table class='leftbar'>";
        var item;
        for(i=0;i<menu.length;i++){
            item = menu[i];
            if(!item)
                continue;
            var strItem ="";
            if(i<=2)
                strItem = drawItem(item,"1","true");
            else
                strItem = drawItem(item,"1","false");

            str = str+strItem;
        }
        str = str+"</table>";
        obj.innerHTML=str;
    }
    //
    // Ve menu theo kieu tan danimages
    //
    var currentDisplayMenu = "";
    
    function showLeftMenu(item){
        var mainMenu = document.getElementById("mainMenu"+currentDisplayMenu);
        var childMenu = document.getElementById("childMenu"+currentDisplayMenu);
        
        childMenu.style.display = "none";
        mainMenu.setAttribute("class", "");
        currentDisplayMenu = item;
        var showChildMenu = document.getElementById("childMenu"+currentDisplayMenu);
        var showMainMenu = document.getElementById("mainMenu"+currentDisplayMenu);
        showChildMenu.style.display = "";
        showMainMenu.setAttribute("class", "activeTab");
       
    }
    
    function drawLeftMenuItem(item, level, showContent){
        var strItem ="<tr onclick='clickMenuItem(this);"+item[2]+"'><td class='parentMenu'>"+item[1]+"<img src='share/images/icons/DownArrow.png' width=16 height=16 alt='Mở menu con' style='float:right'/></td></tr>";
        if(level > 1){
            //strItem ="<tr onclick='clickMenuItem(this);"+item[2]+"'><td class='parentMenu'>"+item[1]+"</td></tr>";
            strItem ="<tr onclick='clickMenuItem(this);"+item[2]+"'><td>"+item[1]+"</td></tr>"; 
        }
        if(item.length > 5){
            var i = 5;
            var subItem = "<tr style='display:none'><td><table class='tableLevel"+level+"'>";
            if(showContent == "true"){
                subItem = "<tr ><td><table class='tableLevel"+level+"'>";
            }
            for(i = 5;i<item.length;i++){
                subItem = subItem + drawLeftMenuItem(item[i],level+1);
            }
            subItem = subItem +"</table></td></tr>";
            strItem = strItem + subItem;
        } else {
            //strItem ="<tr onclick='clickMenuItem(this);"+item[2]+"'><td class='parentMenu'>"+item[1]+"</td></tr>";
            strItem ="<tr onclick='clickMenuItem(this);"+item[2]+"'><td>"+item[1]+"</td></tr>"; 
        }
        return strItem;
    }

    function drawMainMenuItem(count, item,isDefault){
        var strItem ="";
        if(isDefault == "true"){
            currentDisplayMenu = count;
        }
        if(item.length > 5){
            var i = 5;
            var subItem = "<tr id='childMenu"+count+"' style='display:none'><td><table class='leftMenu'>";
            if(isDefault == "true"){
                subItem = "<tr id='childMenu"+count+"' ><td><table class='leftMenu'>";
            }
            for(i = 5;i<item.length;i++){
                subItem = subItem + drawLeftMenuItem(item[i],1);
            }
            subItem = subItem +"</table></td></tr>";
            strItem = strItem + subItem;
        } 
        return strItem;       
    }
    
    function drawMainMenu(mainId,leftId, menu){
        var mainMenu = document.getElementById(mainId);
        var leftMenu = document.getElementById(leftId);
        var strMainMenu= "<table style='padding:0; margin:0; border-spacing:0px'><tr>";
        var strLeftMenu = "<table class='leftbar'>";
        var i=0;
        var countMenu = 0;
        for(i=0;i<menu.length;i++){
            var item = menu[i];
            if(!item)
                continue;
            var strMainItem ="<td id='mainMenu"+i.toString()+"' onclick='showLeftMenu("+i+");changeWorkingDay(3);"+item[5][2]+"'>"+item[1]+"</td>";
            if(countMenu.toString()=="0"){
                strMainItem ="<td id='mainMenu"+i.toString()+"' class='activeTab' onclick='showLeftMenu("+i+");changeWorkingDay(3);"+item[5][2]+"'>"+item[1]+"</td>";
            } 
            countMenu = countMenu+1;
            
            var strLeftItem = "";
            if(i<=1)
                strLeftItem = drawMainMenuItem(i.toString(),item,"true");
            else
                strLeftItem = drawMainMenuItem(i.toString(),item,"false");

            strMainMenu = strMainMenu+strMainItem;
            strLeftMenu = strLeftMenu+strLeftItem;
        }
        strMainMenu = strMainMenu+"</tr></table>";
        strLeftMenu = strLeftMenu +"</table>";
        mainMenu.innerHTML=strMainMenu;       
        leftMenu.innerHTML=strLeftMenu;
    }
    
    function makeMenu( menuModel ) {
        try {
            //drawLeft('myMenuID', menuModel);
            drawMainMenu("vt-mainfunction","myMenuID",menuModel);
        } catch(e) {
            alert("JSException when create the menu: \n" + e.message);
        }
    }

    function checkToRefresh(event) {
        var keyID = (window.event) ? window.event.keyCode : event.keyCode;
        if (keyID == 116) {
            try{
                cancelEvent(event);
                doGoToMenu(g_latestClickedMenu);
                return false;
            }catch(ex){
                alert("Exception in checkToRefresh: \n" + ex.message);
            }
        }
    }

    function cancelEvent(evt) {
        var e = evt ? evt : window.event;
        if(e.stopPropagation)
            e.stopPropagation();
        if(e.preventDefault)
            e.preventDefault();
        e.cancelBubble = true;
        e.cancel = true;
        e.returnValue = false;
        return false;
    }

    //[ LongH says: shortcuts to [sd/vt].[operator/connector] members @4Apr11
    function doGoToMenu() {

            sd.operator.initContext.call(sd.operator);
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
        if(ed != null && ed != undefined) {
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
                filebrowserBrowseUrl :
                    sd.operator.path["PlugInFolder"] + '/ckfinder/ckfinder.html',
                filebrowserImageBrowseUrl :
                    sd.operator.path["PlugInFolder"] + '/ckfinder/ckfinder.html?type=Images',
                filebrowserFlashBrowseUrl :
                    sd.operator.path["PlugInFolder"] + '/ckfinder/ckfinder.html?type=Flash',
                filebrowserUploadUrl :
                    sd.operator.path["context"] + '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
                filebrowserImageUploadUrl :
                    sd.operator.path["context"] + '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
                filebrowserFlashUploadUrl :
                    sd.operator.path["context"] + '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
            });
        } catch(e) {
            alert("JSEx, include.jsp, deployCKE: " + e.message);
        }
    }

    function delCKE(/*String*/ id) {
        var ed = getCKE(id);
        if(ed != null && ed != undefined) {
            try{
                ed.destroy();
            } catch(e) {
                ed.destroy();
            }
        }
    }
    //] LongH says: shortcuts to [sd/vt].[operator/connector] members @4Apr11
    //] LongH says: RDWF's global & utility functions @7Apr11

    /******************************************************************************************************************/

    //[ LongH says: dojo & 3rd party lib initiation
    dojo.require("dojo.parser" );
    dojo.require("dijit._base.manager");
    dojo.require("dijit.dijit"); // optimize: load dijit layer
    dojo.require("dijit.TitlePane");
    dojo.require("dojo.data.ItemFileWriteStore");
    dojo.require("dojo.back");

    //[ Project team modify here
    sd.operator.setMenuTargetId("bodyContent");
    //sd.operator.setMenuTargetId("homepage-myRightPanel");
    sd.operator.setTimeout(180);
    sd.operator.setDebugMode(true);
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

    dojo.addOnLoad(function(){
        var appState = new ApplicationState("", "");
        dojo.back.setInitialState(appState);
    });

    fwlog = function(id,message,e){
        console.log("[Control ID:"+id+". "+message);
        if (e) console.log(e);
        console.log("]");
    }
    //
    Base.esapi.properties.application.Name = "My Application v1.0";
    // Initialize the api
    org.owasp.esapi.ESAPI.initialize();
    //
    var vtSecurity=$ESAPI;
    //] LongH says: dojo & 3rd party lib initiation
</script>

<%--[ LongH says: JS Feature profiles--%>
<%--<script type="text/javascript" src="${CSS_JS_contextPath}/share/profile/profile_changeDialogPosition.js"></script>--%>
<%--] LongH says: JS Feature profiles--%>
<script src="${JSVTFolderName}/qllltp.js" type="text/javascript"></script>