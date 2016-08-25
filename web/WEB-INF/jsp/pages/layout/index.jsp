<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="com.viettel.common.util.StringUtils"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>


<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sd" uri="struts-dojo-tags"%>

<%
    ResourceBundle rb = ResourceBundle.getBundle("config");
    String projectVersion = rb.getString("project.version");
    String RDWFVersion = rb.getString("RDWF.version");
    String RDWFTheme = rb.getString("RDWF.theme");

    String requestTheme = StringUtils.escapeHtml(request.getParameter("request_theme"));

    request.setAttribute("vt_locale", StringUtils.escapeHtml(request.getParameter("request_locale")));
    request.setAttribute("JSLibTheme", (requestTheme != null) ? requestTheme : RDWFTheme);
    request.setAttribute("projectVersion", projectVersion);
    request.setAttribute("RDWFVersion", RDWFVersion);
    request.setAttribute("contextPath", request.getContextPath());
    request.setAttribute("CSS_JS_contextPath", request.getContextPath());

    String ua = request.getHeader("User-Agent");
    boolean isFirefox = (ua != null && ua.indexOf("Firefox/") != -1);
    boolean isMSIE = (ua != null && ua.indexOf("MSIE") != -1);
    boolean isNew = request.getSession(false).isNew();
%>
<div id="token">
    <s:token id="tokenId"/>
</div>
<s:i18n name="com/viettel/config/Language">
    <% if (isMSIE) {%>
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
    <% } else {%>
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
    <% }%>
    <html>
        <head>
            <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
            <meta name="description" content="<s:property value="getText('projectName')"/>RDWF, RDWebFramework, RD Web FrameWork, R&D WebFrameWork, R&D Web Framework" />
            <title><s:property value="getText('projectName')"/></title>
            <link href="share/homepage/images/logotitle.png" type="image/x-icon" rel="shortcut icon" />
            <script type="text/javascript">
                var g_contextPath = "${contextPath}";
                var g_latestClickedMenu = "";

                var targetDivInBodyLayout;
                var returnPageType;

                //[ Testing purpose
                var useMenuTest = true;
                var userProfileTest = false;
                var firstMenuItem = "";
                //] Testing purprose
            </script>

            <!--[ LongH says: Main static resources (JS/CSS/IMG/HTML)-->
            <tiles:insertTemplate template="/WEB-INF/jsp/pages/layout/include.jsp" />
            <!--] LongH says: Main static resources (JS/CSS/IMG/HTML)-->
        </head>

        <body class="${fn:escapeXml(JSLibTheme)}" <%--onkeydown="checkToRefresh(event);"--%> style="background-color: rgb(107,173,246); padding:2px" >
            <!--            <div id="token">
                        </div>-->
            <!--[ LongH says: RDFW features-->
            <!--div id="message-holder" style="text-align: center; font-weight: bold"></div-->
            <!--] LongH says: RDFW features-->
            <!--[ LongH says: Main header-->
            <div id="vt-header-wrapper" class="no-space">
                <div id="vt-projectBar" class="no-space">
                    <div id="vt-projectName" class="no-space float-ht" style="height: 40px">HỆ THỐNG CẤP GIẤY TIẾP NHẬN BẢN CÔNG BỐ HỢP QUY VÀ GIẤY XÁC NHẬN CÔNG BỐ PHÙ HỢP QUY ĐỊNH ATTP</div>


                    <br><br>
                    <!--[ Language bar -->
                    <br/>
                    <!--] Language bar -->
                </div>
            </div>
            <!--] LongH says: Main header-->

            <!--[ LongH says: Main middle-->
            <div id="vt-loading-background"><div id="vt-loading-icon"></div></div>
            <div id="vt-content-wrapper" class="no-space clear-both" style="align:right">
                <div class="headerTab" id="headerTab">
                    <ul id="menuUL">
                        <div id="vt-user-info" class="no-space no-float" style="color:#3E6AAA; font-family: helvetica, sans-serif; font-weight: bold; font-size: 11px;padding-top: 5px"> 
                            <!--] Dashboard (User bar)-->
                            <div id="vt-logout" class="float-right cursor-pointer"><s:property value="getText('logout')"/></div> 
                            <div id="vt-help" class="float-right cursor-pointer" style="margin-right: 10px"><s:property value="getText('userguild')"/></div>
                            <div id="vt-changePwd" class="float-right cursor-pointer" style="margin-right: 10px"><s:property value="getText('changePassword')"/></div> 
                            <div id="vt-profile" class="float-right cursor-pointer" style="margin-right: 10px"><s:property value="getText('loadingText')"/></div> 
                            <div id="vt-backFirstPage" class="float-right cursor-pointer" style="margin-right: 10px"><s:property value="getText('firstPage')"/></div>
                            <div id="vt-home" class="float-right cursor-pointer" style="margin-right: 10px"><s:property value="getText('homePage')"/></div>                       
                            <div id="vt-keyboard" class="float-right cursor-pointer" style="margin-right: 10px">
                                <img src="share/images/icons/keyboard.png" width="16" height="16" title="Bàn phím ảo"/> 
                            </div>                      
                            <!--] Dashboard (User bar)-->
                        </div>
                    </ul>
                </div>
                <div class="tabContent" id="tabContent">

                </div>
                <div id="vt-content" class="no-space">
                    <div id="bodyContent">
                        <tiles:insertAttribute name="body"/>  
                    </div>
                    <div class="clear-both"></div>
                </div>
            </div>
            <!--] LongH says: Main middle-->

            <!--[ LongH says: Main footer-->
            <div id="vt-footer" class="no-space clear-both">
                <div id="vt-copyRite" style="text-align:center;margin-top:2px;">
                    <br/>
                    Hệ thống cấp giấy tiếp nhận bản công bố hợp quy và giấy xác nhận công bố phù hợp quy định ATTP
                    <br/>
                    Địa chỉ: 138A Giảng Võ - Ba Đình - Hà Nội
                    <br/>
                    Điện thoại: 04.38464489; 04.38463702; Fax: 04.38463739; Email: vfa@vfa.gov.vn
                </div>
            </div>
            <!--] LongH says: Main footer-->

            <!--[ LongH says: RDFW features-->
            <div id="componentBody" style="display:none;"></div>
            <!--] LongH says: RDFW features-->

            <!--[ LongH says: Make menu-feed script-->
            <tiles:insertAttribute name="menu"/>
            <!--] LongH says: Make menu-feed script-->
            <sd:Dialog id="vt-changePasswowrdDialog" key="changePasswordTitle" width="900px"
                       showFullscreenButton="false">
                <div id="vt-changePasswordDiv">
                    <!--jsp:include page="changePassword.jsp"/-->
                </div>
            </sd:Dialog>
            <sd:Dialog id="vt-guidelineDialog" key="Tài liệu hướng dẫn sử dụng" height="300px" width="500px"
                       showFullscreenButton="false">
                <div id="vt-guidelineDiv">
                    <jsp:include page="guideline.jsp"/>
                </div>
            </sd:Dialog>

            <sd:Dialog id="vt-profileDialog" key="Thông tin người dùng" height="700px"  width="900px"
                       showFullscreenButton="false">
                <div id="vt-profileDiv">
                    <jsp:include page="guideline.jsp"/>
                    <%--  <jsp:include page="changePassword.jsp"/> --%>

                </div>
            </sd:Dialog>


            <sd:Dialog id="vt-errorDisplayDialog" key="errorDisplayDialogDefaultTitle">
                <div id="vt-errorDisplayDiv"></div>
                <iframe name="vt-errorDisplayIframe" id="vt-errorDisplayIframe" frameborder="0"></iframe>
                </sd:Dialog>

            <jsp:include page="keyboard.jsp"/>
            <sd:Dialog  id="vt-businessAlert" height="auto" width="800px"
                        key="dialog.titleAddEdit" showFullscreenButton="false"
                        >
                <jsp:include page="pushBusinessAlertDlg.jsp" flush="false"></jsp:include>
            </sd:Dialog>

            <!--[ LongH says: After-page-rendered script-->
            <script type="text/javascript">
                page.checkChangePassword = function () {//kiem tra ton tai giay cong bo
                    sd.connector.post("UserAction!checkPassIsChanged.do", null, null, null, page.aftercheckChangePassword);
                };

                page.pushBusinessAlert = function () {//kiem tra ton tai giay cong bo
                    sd.connector.post("UserAction!pushBusinessAlert.do", null, null, null, page.afterPushBusinessAlert);
                };

                page.aftercheckChangePassword = function (data) {//sau khi kiem tra ton tai cong bo
                    var obj = dojo.fromJson(data);
                    var result = obj.items;
                    var result0 = result[0];
                    var result2 = result[3];
                    if (result2 != null) {
                        profileIcon.innerHTML = result2;
                        document.getElementById("vt-help").style.display = "none";
                    }
                    if (result0 == 0) {
                        page.alert("Thông báo", result[1], "warning");
                        changePasswordIcon.onclick();
                    }
                };

                page.afterPushBusinessAlert = function (data) {//sau khi kiem tra ton tai cong bo
                    var obj = dojo.fromJson(data);
                    var result = obj.items;
                    var result0 = result[0];
                    var result1 = result[1];
                    if (result0 == 1) {
                        var dialog = dijit.byId("vt-businessAlert");
                        dialog.show();

                        //dijit.byId("gridViewBusinessAlertContentId").vtReload("businessAlertAction!loadBusinessAlertView.do?searchForm.businessId=" + result1);
                    }
                };

//                page.pushBusinessAlert();
                page.checkChangePassword();

                var mainProcess = false;
                var workingTime = 1;

                sd.operator.waitScreenDivId = "vt-loading-background";
                sd.operator.breadCumbDivId = "vt-breadCumb";
                sd.operator.errorDisplayDialogId = "vt-errorDisplayDialog";
                sd.operator.errorDisplayIframeId = "vt-errorDisplayIframe";
                sd.operator.errorDisplayDivId = "vt-errorDisplayDiv";

                var homeIcon = sd.$("vt-home");
                var logoutIcon = sd.$("vt-logout");
                var changePasswordIcon = sd.$("vt-changePwd");
                var profileIcon = sd.$("vt-profile");
                var backFistPageIcon = sd.$("vt-backFirstPage");
                var vt_help = sd.$("vt-help");//không hiển thị action hdsd
                var vt_keyboard = sd.$("vt-keyboard");

//                var changeWorkingDeptIcon = sd.$("vt-changeWorkingDept");
//                changeWorkingDeptIcon.onclick = function() {
                //sd.operator.getBreadCumb("1.3.1"); // Test breadcump feature by LongH
//                    document.location.href = "${contextPath}/Index.do?changeDept=true&request_locale=${fn:escapeXml(vt_locale)}";
//                };


                vt_help.onclick = function () {//không hiển thị action hdsd
                    var dialog = dijit.byId("vt-guidelineDialog");
                    sd.connector.post("home!gotoGuidelinePage.do", "vt-guidelineDiv", null);
                    dialog.show();
                };

                vt_keyboard.onclick = function () {
                    if (document.getElementById("keyboardInput").style.display == "none") {
                        document.getElementById("keyboardInput").style.display = "";
                        var left = vt_keyboard.offsetLeft;
                        var top = vt_keyboard.offsetTop;
                        document.getElementById("keyboardInput").style.top = top + 20;
                        document.getElementById("keyboardInput").style.left = left;

                    } else {
                        document.getElementById("keyboardInput").style.display = "none";

                    }
                };

                profileIcon.onclick = function () {
//                    var dialog = dijit.byId("vt-profileDialog");
//                    sd.connector.post("UserAction!gotoProfilePage.do", "vt-profileDiv", null,null,null);
//                    dialog.show();
                    var dialog = dijit.byId("vt-changePasswowrdDialog");
                    sd.connector.post("UserAction!loadPasswordChange.do", "vt-changePasswordDiv", null);
                    dialog.show();

                };

                var actionTitle = "<sd:Property><tiles:getAsString name='title'/></sd:Property>";
                var userName;

                if (userProfileTest) {
                    userName = "admin";
                } else {
                    userName = "<s:property value='#session.userPosition' escapeHtml="true"/>-" + "<s:property value='#session.userToken.fullName' escapeHtml="true"/>";
                }

                addDay = function (date, day) {
                    var time = date.getTime();
                    time = time + day * 24 * 60 * 60 * 1000;
                    var newDate = new Date();
                    newDate.setTime(time);
                    return newDate;
                }

                dateToString = function (date) {
                    var day = "";
                    if (date.getDate() < 10)
                        day = "0" + date.getDate().toString();
                    else
                        day = date.getDate();
                    var month = "";
                    if (date.getMonth() + 1 < 10)
                        month = "0" + (date.getMonth() + 1).toString();
                    else
                        month = date.getMonth() + 1;
                    var str = day + "/" + month + "/" + (date.getFullYear());
                    return str;
                }

                changeWorkingDay = function (type) {
                    try {
                        if (document.getElementById("workingTime" + workingTime) != null)
                            document.getElementById("workingTime" + workingTime).setAttribute("class", "");
                        workingTime = type;
                        var obj = document.getElementById("workingTime" + workingTime);
                        if (obj != null)
                            obj.setAttribute("class", "selected");
                    } catch (en) {

                    }
                    var fromDate = new Date();
                    var toDate = new Date();
                    var year = fromDate.getFullYear();
                    var month = fromDate.getMonth();
                    var day = fromDate.getDay();
                    //linhdx                    
                    //                    document.getElementById("searchForm.selectedYear").value = year;
                    switch (type) {
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:    // HOM QUA
                            fromDate = addDay(new Date(), -1);
                            toDate = addDay(new Date(), -1);
                            break;
                        case 3:    // TUAN NAY
                            fromDate = addDay(new Date(), -1 * day);
                            toDate = addDay(new Date(), 6 - day);
                            break;
                        case 4:    // TUAN TRUOC 
                            fromDate = addDay(new Date(), -1 * (day + 7));
                            toDate = addDay(new Date(), -1 - day);
                            break;
                        case 5:     // THANG NAY
                            fromDate = new Date(year, month, 1, 0, 0, 0, 0);
                            month = month + 1;
                            if (month == 12) {
                                month = 0;
                                year = year + 1;
                            }
                            toDate = new Date(year, month, 1, 0, 0, 0, 0);
                            toDate = addDay(toDate, -1);
                            break;
                        case 6:     // THANG TRUOC
                            toDate = new Date(year, month, 1, 0, 0, 0, 0);
                            month = month - 1;
                            if (month < 0) {
                                month = 11;
                                year = year - 1;
                            }
                            fromDate = new Date(year, month, 1, 0, 0, 0, 0);
                            toDate = addDay(toDate, -1);
                            break;
                        case 7:     // NAM NAY
                            toDate = new Date(year, 11, 31, 0, 0, 0, 0);
                            fromDate = new Date(year, 0, 1, 0, 0, 0, 0);
                            break;
                        case 8:     // NAM TRUOC
                            toDate = new Date(year - 1, 11, 31, 0, 0, 0, 0);
                            fromDate = new Date(year - 1, 0, 1, 0, 0, 0, 0);
                            break;
                        default:
                            fromDate = addDay(new Date(), -1 * day);
                            toDate = addDay(new Date(), 6 - day);
                            break;

                    }

                    if (type == 0) {
                        document.getElementById("searchMainForm.fromDate").value = "";
                        document.getElementById("searchMainForm.toDate").value = "";
                    } else {
                        document.getElementById("searchMainForm.fromDate").value = dateToString(fromDate);
                        document.getElementById("searchMainForm.toDate").value = dateToString(toDate);
                    }
                    //linhdx
                    //searchText();
                }

                searchText = function () {
                    try {
                        var searchFun = window["onSearch"];
                        if (searchFun == null || searchFun.toString() == "undefined") {

                        } else {
                            searchFun();
                        }
                    } catch (en) {
                    }
                    return false;
                }

                searchTextAdvance = function () {
                    try {
                        var searchFun = window["onSearch"];
                        if (searchFun == null || searchFun.toString() == "undefined") {

                        } else {
                            //"vt-breadCumb"
                            var actionFunction = "";
                            var breadCumb = document.getElementById("vt-breadCumb");
                            if (breadCumb != null) {
                                var anchor = breadCumb.getElementsByTagName("a");
                                if (anchor != null && anchor.length > 0) {
                                    actionFunction = anchor[0].href;
                                }
                            }

                            var myMenuID = document.getElementById("myMenuID");
                            var menuMonitorLeader = null;
                            var menuMonitor = null;
                            var menuPublishDoc = null;
                            if (myMenuID != null) {
                                var i = 0;
                                var trMoniterLeaders = myMenuID.getElementsByTagName("tr");
                                for (i = 0; i < trMoniterLeaders.length; i++) {
                                    var onclkValue = trMoniterLeaders[i];
                                    if (onclkValue.onclick != null && onclkValue.onclick.toString().indexOf("assignDoc!monitorLeader.do") >= 0) {
                                        menuMonitorLeader = onclkValue;
                                    }
                                    if (onclkValue.onclick != null && onclkValue.onclick.toString().indexOf("assignDoc!monitor.do") >= 0) {
                                        menuMonitor = onclkValue;
                                    }
                                    if (onclkValue.onclick != null && onclkValue.onclick.toString().indexOf("voPublishDocument!prepareSearch.do") >= 0) {
                                        menuPublishDoc = onclkValue;
                                    }
                                }
                            }
                            changeWorkingDay(7);
                            // Văn bản đến
                            if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voDocumentReceive.do") >= 0) {
                                doGoToMenu("assignDoc!monitorLeader.do");
                                clickMenuItem(menuMonitorLeader);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("assignDoc!prepareStaffCoWait.do") >= 0) {
                                doGoToMenu("assignDoc!monitorLeader.do");
                                clickMenuItem(menuMonitorLeader);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("assignDoc!prepareStaffCoProcess.do") >= 0) {
                                doGoToMenu("assignDoc!monitorLeader.do");
                                clickMenuItem(menuMonitorLeader);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("assignDoc!prepareStaffJustRef.do") >= 0) {
                                doGoToMenu("assignDoc!monitorLeader.do");
                                clickMenuItem(menuMonitorLeader);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("assignDoc!prepareStaff.do") >= 0) {
                                doGoToMenu("assignDoc!monitor.do");
                                clickMenuItem(menuMonitor);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("assignDoc!prepare.do") >= 0) {
                                doGoToMenu("assignDoc!monitorLeader.do");
                                clickMenuItem(menuMonitorLeader);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("assignDoc!prepareStaffProcessing.do") >= 0) {
                                doGoToMenu("assignDoc!monitorLeader.do");
                                clickMenuItem(menuMonitorLeader);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("assignDoc!prepareStaffProcessed.do") >= 0) {
                                doGoToMenu("assignDoc!monitorLeader.do");
                                clickMenuItem(menuMonitorLeader);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("assignDoc!prepareForwardDoc.do") >= 0) {
                                doGoToMenu("assignDoc!monitorLeader.do");
                                clickMenuItem(menuMonitorLeader);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("assignDoc!prepareAssignedDoc.do") >= 0) {
                                doGoToMenu("assignDoc!monitorLeader.do");
                                clickMenuItem(menuMonitorLeader);

                                // bao cao
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voDocumentReceive!prepareReport.do") >= 0) {
                                doGoToMenu("assignDoc!monitorLeader.do");
                                clickMenuItem(menuMonitorLeader);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voDocumentReceive!prepareProcessReport.do") >= 0) {
                                doGoToMenu("assignDoc!monitorLeader.do");
                                clickMenuItem(menuMonitorLeader);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("report.do") >= 0) {
                                doGoToMenu("assignDoc!monitorLeader.do");
                                clickMenuItem(menuMonitorLeader);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("assignDoc!preSearchExpiredProcess.do") >= 0) {
                                doGoToMenu("assignDoc!monitorLeader.do");
                                clickMenuItem(menuMonitorLeader);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("assignDoc!preSearchSendReceiveProcess.do") >= 0) {
                                doGoToMenu("assignDoc!monitorLeader.do");
                                clickMenuItem(menuMonitorLeader);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("assignDoc!preTranferReportDocument.do") >= 0) {
                                doGoToMenu("assignDoc!monitorLeader.do");
                                clickMenuItem(menuMonitorLeader);
                            }
                            //van ban di
                            else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voPublishDocument!prepareEntryBook.do") >= 0) {
                                doGoToMenu("voPublishDocument!prepareEntryBook.do");
                                //clickMenuItem(menuMonitorLeader);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voPublishDocument!preSearchDocRequestPublish.do") >= 0) {
                                doGoToMenu("voPublishDocument!preSearchDocRequestPublish.do");
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voPublishDocument!prepareSearchDraftDocToDept.do") >= 0) {
                                doGoToMenu("voPublishDocument!prepareSearchDraftDocToDept.do");
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voPublishDocument!prepareSearch.do") >= 0) {
                                doGoToMenu("voPublishDocument!prepareSearch.do");
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voPublishDocument!prepareSearchDraft.do") >= 0) {
                                doGoToMenu("voPublishDocument!prepareSearchDraft.do");
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voPublishDocument!preDraftApproved.do") >= 0) {
                                doGoToMenu("voPublishDocument!preDraftApproved.do");
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voPublishDocument!prepareSearchDrafResposeDocReceiveForDept.do") >= 0) {
                                doGoToMenu("voPublishDocument!prepareSearchDrafResposeDocReceiveForDept.do");
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voPublishDocument!prepareSearchNotifyDoc.do") >= 0) {
                                doGoToMenu("voPublishDocument!prepareSearchNotifyDoc.do");
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voPublishDocument!preparePrintBookReport.do") >= 0) {
                                doGoToMenu("voPublishDocument!prepareSearch.do");
                                clickMenuItem(menuPublishDoc);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voPublishDocument!prepareGeneralReport.do") >= 0) {
                                doGoToMenu("voPublishDocument!prepareSearch.do");
                                clickMenuItem(menuPublishDoc);
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voPublishDocument!prepareDocumentTypeReport.do") >= 0) {
                                doGoToMenu("voPublishDocument!prepareSearch.do");
                                clickMenuItem(menuPublishDoc);
                            }
                            // van ban dieu hanh
                            else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voDocumentDirection!search.do") >= 0) {
                                doGoToMenu("voDocumentDirection!search.do");
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voDocumentDirection!isPublic.do") >= 0) {
                                doGoToMenu("voDocumentDirection!isPublic.do");
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voDocumentDirection!isPublished.do") >= 0) {
                                doGoToMenu("voDocumentDirection!isPublished.do");
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voDocumentDirection!publishToDirection.do") >= 0) {
                                doGoToMenu("voDocumentDirection!publishToDirection.do");
                            } else if (actionFunction.toString() != "" && actionFunction.toString().indexOf("voDocumentDirection!preInsert.do") >= 0) {
                                doGoToMenu("voDocumentDirection!preInsert.do");
                            }
                            searchFun();
                        }
                    } catch (en) {
                    }
                    return false;
                }

                selectYear = function (year) {
                    try {
                        document.getElementById("workingTime" + workingTime).setAttribute("class", "");
                        workingTime = "9";
                        var obj = document.getElementById("workingTime" + workingTime);
                        obj.setAttribute("class", "selected");
                    } catch (en) {

                    }
                    var yearInt = parseInt(year);
                    var fromDate = new Date(yearInt, 0, 1, 0, 0, 0, 0);
                    var toDate = new Date(yearInt, 11, 31, 0, 0, 0, 0);

                    document.getElementById("searchMainForm.fromDate").value = dateToString(fromDate);
                    document.getElementById("searchMainForm.toDate").value = dateToString(toDate);

                    searchText();
                }

                changeWorkingYear = function (obj) {
                    var year = obj.value;
                    selectYear(year);
                }

                makeSelectTime = function () {
                    var currentDate = new Date();
                    var year = currentDate.getFullYear();
                    var i = 0;

                    var select = document.getElementById("searchMainForm.selectedYear");
                    for (i = year; i >= 2013; i--) {
                        var option = document.createElement("option");
                        option.setAttribute("value", i);
                        option.innerHTML = "Năm " + i;
                        /*
                         var option = "<option value="+i+">Năm "+i+"</option>";
                         innerHTML+= option;
                         */
                        select.appendChild(option);
                    }

                }

                //makeSelectTime();
                //shortcut Add New Item
                shortcut.add("Alt+N", function () {
                    try {
                        var addFunction = window["createNewItem"];
                        if (addFunction == null || addFunction.toString() == "undefined") {
                        } else {
                            addFunction();
                        }
                    } catch (en) {
                    }
                    return false;
                });

                // shortcut delete item
                shortcut.add("Alt+D", function () {
                    try {
                        var deleteFunction = window["deleteItem"];
                        if (deleteFunction == null || deleteFunction.toString() == "undefined") {
                        } else {
                            deleteFunction();
                        }
                    } catch (en) {
                    }
                    return false;
                });
                // shortcut upload
                shortcut.add("Alt+U", function () {
                    try {
                        var upload = window["choseUploadFile"];
                        if (upload == null || upload.toString() == "undefined") {
                        } else {
                            // alert(upload);
                            upload();
                        }
                    } catch (en) {
                    }
                    return false;
                });

                // shortcut copy item
                shortcut.add("Alt+C", function () {
                    try {
                        var copyFun = window["copyItem"];
                        if (copyFun == null || copyFun.toString() == "undefined") {
                        } else {
                            copyFun();
                        }
                    } catch (en) {
                    }
                    return false;
                });

                // shortcut save and new item
                shortcut.add("F6", function () {
                    try {
                        var sfunction = window["saveAndNewItem"];
                        if (sfunction == null || sfunction.toString() == "undefined") {
                        } else {
                            sfunction();
                        }
                    } catch (en) {
                    }
                    return false;
                });

                // shortcut save and copy item
                shortcut.add("F9", function () {
                    try {
                        var sfunction = window["saveAndCopyItem"];
                        if (sfunction == null || sfunction.toString() == "undefined") {
                        } else {
                            sfunction();
                        }
                    } catch (en) {
                    }
                    return false;
                });

                // shortcut save item
                shortcut.add("F8", function () {
                    try {
                        var sfunction = window["saveItem"];
                        if (sfunction == null || sfunction.toString() == "undefined") {
                        } else {
                            sfunction();
                        }
                    } catch (en) {
                    }
                    return false;
                });

                // back page
                shortcut.add("Alt+Backspace", function () {
                    try {
                        var sfunction = window["backPage"];
                        if (sfunction == null || sfunction.toString() == "undefined") {
                        } else {
                            sfunction();
                        }
                    } catch (en) {
                    }
                    return false;
                });
                //
                //
                // Test browser
                //
                <% if (isNew) {%>
                <%-- document.location.href = "${contextPath}/Exit.do"; --%>
                document.location.href = "${contextPath}/share/logout.jsp";
                <%}%>

                if (navigator.appName.toString() == "Microsoft Internet Explorer") {
                    var divContent = document.getElementById("vt-content-wrapper");
                    var style = divContent.style;
                    style.width = "100%";
                    divContent.setAttribute("style", style);
                } else {
                }
                /*
                 if(navigator.appName.toString().indexOf("Microsoft") != -1){
                 alert("here");
                 document.getElementById("vt-content-wrapper").style="width:100%";
                 } else {
                 alert("not ie");
                 };*/
                backFistPageIcon.onclick = function () {
                    document.location.href = "${contextPath}/Index.do?request_locale=${fn:escapeXml(vt_locale)}";
                        }
                        homeIcon.onclick = function () {
                            //sd.operator.getBreadCumb("1.3.1"); // Test breadcump feature by LongH
                            document.location.href = "${contextPath}/HomePage.do";
                        };

                        logoutIcon.onclick = function () {
                <%-- document.location.href = "${contextPath}/Exit.do"; --%>
                            document.location.href = "${contextPath}/share/logout.jsp";
                        };

                        changePasswordIcon.onclick = function () {
                            var dialog = dijit.byId("vt-changePasswowrdDialog");
                            sd.connector.post("UserAction!loadPasswordChange.do", "vt-changePasswordDiv", null);
                            dialog.show();
                        };

                        function adjustBodyFrameSize(firstLoad) {
                            var ifrH = 0;
                            var leftMenu = document.getElementById("myMenuID");
                            var titleBar = document.getElementById("vt-header-wrapper");
                            var mainIfr = document.getElementById("vt-content-wrapper");
                            var content = document.getElementById("vt-content");
                            var footer = document.getElementById("vt-footer");

                            var pageH = (window.innerHeight) ? window.innerHeight : (document.documentElement.offsetHeight);

                            //ifrH = pageH - projectBar.offsetHeight - menuBar.offsetHeight - titleBar.offsetHeight - adjustW;
                            ifrH = pageH - titleBar.offsetHeight - footer.offsetHeight - 120;

                            if (!firstLoad) {
                                mainIfr.style.width = "100%";
                            } else {
                                //mainIfr.style.width = content.offsetWidth - 2;
                            }
                            //alert( ifrH );
                            //mainIfr.style.height = ifrH;
                            mainIfr.setAttribute("style", "min-height:" + ifrH + "px");
                            content.setAttribute("style", "min-height:" + (ifrH) + "px");
                            //if(mainIfr.offsetHeight < ifrH){
                            //mainIfr.style.height = ifrH;
                            //}
                        }

                        onItemFocus = function (item) {
                            localStorage.focusedItem = item.id;
                        }

                        setOnFocus = function () {
                            var inputs = document.getElementsByTagName("input");
                            if (inputs != null && inputs.length > 0) {
                                var i = 0;
                                for (i = 0; i < inputs.length; i++) {
                                    if (inputs[i].getAttribute("type") == "text") {
                                        inputs[i].setAttribute("onfocus", "onItemFocus(this);");
                                    }
                                }
                            }
                        }

                        adjustBodyFrameSize(true);

                        updatePageInfo(userName, actionTitle);

                        try {
                            dojo.require("vt.dialog.vtDialog");
                            var msg = new vt.dialog.innerDialog();

                        } catch (e) {
                            alert("Exception in loading vt.dialog.vtDialog\n" + e.message);
                        }
                        // changeWorkingDay(3);

            </script>
            <!--] LongH says: After-page-rendered script-->
            <div id ="result" style="display:none" >
                <h2>Kết quả trả về</h2>
                <%
                    Map params = request.getParameterMap();
                    Iterator i = params.keySet().iterator();
                    String param = "";
                    while (i.hasNext()) {
                        String key = (String) i.next();
                        String value = ((String[]) params.get(key))[0];
                        param += "<strong>" + key + "</strong>" + ": " + value + "<br>";

                    }
                %>
                <%= param%>
                <strong>Response Code : </strong> <%=request.getParameter("response_code")%> <br>
                <input id="responseCode" type="hidden" name="responseCode" value="<%=request.getParameter("response_code")%>">
                <input id="feeId" type="hidden" name="feeId" value="<%=request.getParameter("feeId")%>">
                <input id="fileId" type="hidden" name="fileId" value="<%=request.getParameter("fileId")%>">
                <input id="feeInfoId" type="hidden" name="feeInfoId" value="<%=request.getParameter("feeInfoId")%>">
                <input id="command" type="hidden" name="command" value="<%=request.getParameter("command")%>">
                <input id="merchant_trans_id" type="hidden" name="merchant_trans_id" value="<%=request.getParameter("merchant_trans_id")%>">
                <input id="merchant_code" type="hidden" name="merchant_code" value="<%=request.getParameter("merchant_code")%>"> 
                <input id="trans_id" type="hidden" name="trans_id" value="<%=request.getParameter("trans_id")%>"> 
                <input id="good_code" type="hidden" name="good_code" value="<%=request.getParameter("good_code")%>"> 
                <input id="net_cost" type="hidden" name="net_cost" value="<%=request.getParameter("net_cost")%>"> 
                <input id="ship_fee" type="hidden" name="ship_fee" value="<%=request.getParameter("ship_fee")%>"> 
                <input id="tax" type="hidden" name="tax" value="<%=request.getParameter("tax")%>"> 
                <input id="service_code" type="hidden" name="service_code" value="<%=request.getParameter("service_code")%>"> 
                <input id="currency_code" type="hidden" name="currency_code" value="<%=request.getParameter("currency_code")%>"> 
                <input id="bank_code" type="hidden" name="bank_code" value="<%=request.getParameter("bank_code")%>"> 
                <input id="desc_1" type="hidden" name="desc_1" value="<%=request.getParameter("desc_1")%>">
                <input id="desc_2" type="hidden" name="desc_2" value="<%=request.getParameter("desc_2")%>">
                <input id="desc_3" type="hidden" name="desc_3" value="<%=request.getParameter("desc_3")%>">
                <input id="desc_4" type="hidden" name="desc_4" value="<%=request.getParameter("desc_4")%>">
                <input id="desc_5" type="hidden" name="desc_5" value="<%=request.getParameter("desc_5")%>">
                <input id="secure_hash" type="hidden" name="secure_hash" value="<%=request.getParameter("secure_hash")%>"> 
                <input id="files_code" type="hidden" name="files_code" value="<%=request.getParameter("files_code")%>"> 
            </div>
            <script type="text/javascript">


                page.checkResponseStatus = function ()
                {

//                    var feeId = document.getElementById("feeId").value;
//                    var fileId = document.getElementById("fileId").value;
                    var feeInfoId = document.getElementById("feeInfoId").value;
                    var command = document.getElementById("command").value;
                    var merchant_trans_id = document.getElementById("merchant_trans_id").value;
                    var merchant_code = document.getElementById("merchant_code").value;
                    var responseCode = document.getElementById("responseCode").value;
                    var trans_id = document.getElementById("trans_id").value;
                    var good_code = document.getElementById("good_code").value;
                    var net_cost = document.getElementById("net_cost").value;
                    var ship_fee = document.getElementById("ship_fee").value;
                    var tax = document.getElementById("tax").value;
                    var service_code = document.getElementById("service_code").value;
                    var currency_code = document.getElementById("currency_code").value;
                    var bank_code = document.getElementById("bank_code").value;
                    var desc_1 = document.getElementById("desc_1").value;
                    var desc_2 = document.getElementById("desc_2").value;
                    var desc_3 = document.getElementById("desc_3").value;
                    var desc_4 = document.getElementById("desc_4").value;
                    var desc_5 = document.getElementById("desc_5").value;
                    var secure_hash = document.getElementById("secure_hash").value;
                    var files_code = document.getElementById("files_code").value;
                    var paymentInfo = "command:" + command + ";" + "merchant_trans_id:" + merchant_trans_id + ";" +
                            "merchant_code:" + merchant_code + ";" + "response_code:" + responseCode + ";" + "trans_id:" + trans_id + ";" + "good_code:" + good_code +
                            ";" + "net_cost:" + net_cost + ";" + "ship_fee:" + ship_fee + ";" + "tax:" + tax + ";" + "service_code:" + service_code + ";" +
                            "currency_code:" + currency_code + ";" + "bank_code:" + bank_code + ";" + "desc_1:" + desc_1 + ";" + "desc_2:" + desc_2 + ";" + "desc_3:" + desc_3 + ";" + "desc_4:" + desc_4 + ";" + "desc_5:" + desc_5 + ";" + "secure_hash:" + secure_hash + ";";
                    var hash = command + ";" + merchant_trans_id + ";" +
                            +merchant_code + ";" + responseCode + ";" + trans_id + ";" + good_code +
                            ";" + net_cost + ";" + ship_fee + ";" + tax + ";" + service_code + ";" +
                            currency_code + ";" + bank_code + ";" + desc_1 + ";" + desc_2 + ";" + desc_3 + ";" + desc_4 + ";" + desc_5 + ";" + secure_hash + ";";

                    if (responseCode == '00')
                    {

                        sd.connector.post("feeAction!onInsertFeePaymentInfo.do?feeInfoId=" + feeInfoId + "&paymentInfo=" + paymentInfo + "&filescode=" + files_code + "&hash=" + hash + "&" + token.getTokenParamString(), "createForm", null, null, page.afterPaySuccessSave);
                    } else if (responseCode != 'null' && responseCode != '00')
                    {
                        msg.alert("Thanh toán không thành công");
                    }

                };


                page.afterPaySuccessSave = function (data)
                {
                    var obj = dojo.fromJson(data);
                    var result = obj.items;
                    if (result[0] == 1)
                    {
                        msg.alert("Thanh toán thành công");
                    } else {
                        msg.alert("Thanh toán thất bại");
                    }
                    //resultMessage_show("resultCreateMessage", result[0], result[1], 5000);
                    //msg.alert("Thanh toán thành công");
                };

                page.checkResponseStatus();





            </script>

        </body>
    </html>
</s:i18n>