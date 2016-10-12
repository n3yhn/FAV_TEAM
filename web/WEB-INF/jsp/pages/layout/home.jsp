<%-- 
    Document   : home
    Created on : Jun 29, 2012, 10:06:31 AM
    Author     : HanPT1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link class="lfr-css-file" href="share/homeLayout/style.css" rel="stylesheet" type="text/css">

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<script type="text/javascript">
    document.getElementById('hsCtd').style.display = "none";
    document.getElementById('hsDxl').style.display = "none";
    document.getElementById('hsSdbs').style.display = "none";
    document.getElementById('hsTl').style.display = "none";
    document.getElementById('hsDc').style.display = "none";
    document.getElementById('hsDaxl').style.display = "none";
    document.getElementById('hsvldp').style.display = "none";
    document.getElementById('hsvtcv').style.display = "none";
</script>

<div id="boxContent1" style="display: none">
    <c:forEach var="item" items="${lstItem}" varStatus="status">
        <c:if test="${item.group == 1}"> 
            <li>
                <table style="width: 100%;margin-top: 5px;margin-bottom: 5px;">
                    <tr>
                        <td style="width: 80%">
                            <a onclick="${item.url}">${fn:escapeXml(item.tile)} </a>
                        </td>
                        <td style="width: 20%;">
                            <label style="color: red;margin-right: 5px;float: right">${item.count}</label>
                        </td>
                    </tr>
                </table>
            </li>
        </c:if>         
    </c:forEach>  
</div>
<div id="boxContent2" style="display: none">
    <c:forEach var="item" items="${lstItem}" varStatus="status">
        <c:if test="${item.group == 2}"> 
            <li>
                <table style="width: 100%;margin-top: 5px;margin-bottom: 5px;">
                    <tr>
                        <td style="width: 80%">
                            <a onclick="${item.url}">${fn:escapeXml(item.tile)} </a>
                        </td>
                        <td style="width: 20%">
                            <label style="color: red;margin-right: 5px;float: right">${item.count}</label>
                        </td>
                    </tr>
                </table>
            </li>
        </c:if>         
    </c:forEach>
</div>
<div id="boxContent3" style="display: none">
    <c:forEach var="item" items="${lstItem}" varStatus="status">
        <c:if test="${item.group == 3}"> 
            <li>
                <table style="width: 100%;margin-top: 5px;margin-bottom: 5px;">
                    <tr>
                        <td style="width: 80%">
                            <a onclick="${item.url}">${fn:escapeXml(item.tile)} </a>
                        </td>
                        <td style="width: 20%">
                            <label style="color: red;margin-right: 5px;float: right">${item.count}</label>
                        </td>
                    </tr>
                </table>
            </li>
        </c:if>         
    </c:forEach>
</div>
<div id="boxContent4" style="display: none">
    <c:forEach var="item" items="${lstItem}" varStatus="status">
        <c:if test="${item.group == 4}"> 
            <li>
                <table style="width: 100%;margin-top: 5px;margin-bottom: 5px;">
                    <tr>
                        <td style="width: 80%">
                            <a onclick="${item.url}">${fn:escapeXml(item.tile)} </a>
                        </td>
                        <td style="width: 20%">
                            <label style="color: red;margin-right: 5px;float: right">${item.count}</label>
                        </td>
                    </tr>
                </table>
            </li>
        </c:if>         
    </c:forEach>
</div>
<div id="boxContent5" style="display: none">
    <c:forEach var="item" items="${lstItem}" varStatus="status">
        <c:if test="${item.group == 5}"> 
            <li>
                <table style="width: 100%;margin-top: 5px;margin-bottom: 5px;">
                    <tr>
                        <td style="width: 80%">
                            <a onclick="${item.url}">${fn:escapeXml(item.tile)} </a>
                        </td>
                        <td style="width: 20%">
                            <label style="color: red;margin-right: 5px;float: right">${item.count}</label>
                        </td>
                    </tr>
                </table>
            </li>
        </c:if>         
    </c:forEach>
</div>

<div id="boxContent6" style="display: none">
    <c:forEach var="item" items="${lstItem}" varStatus="status">
        <c:if test="${item.group == 6}"> 
            <li>
                <table style="width: 100%;margin-top: 5px;margin-bottom: 5px;">
                    <tr>
                        <td style="width: 80%">
                            <a onclick="${item.url}">${fn:escapeXml(item.tile)} </a>
                        </td>
                        <td style="width: 20%">
                            <label style="color: red;margin-right: 5px;float: right">${item.count}</label>
                        </td>
                    </tr>
                </table>
            </li>
        </c:if>         
    </c:forEach>
</div>
<div id="boxContent7" style="display: none">
    <c:forEach var="item" items="${lstItem}" varStatus="status">
        <c:if test="${item.group == 7}"> 
            <li>
                <table style="width: 100%;margin-top: 5px;margin-bottom: 5px;">
                    <tr>
                        <td style="width: 80%">
                            <a onclick="${item.url}">${fn:escapeXml(item.tile)} </a>
                        </td>
                        <td style="width: 20%">
                            <label style="color: red;margin-right: 5px;float: right">${item.count}</label>
                        </td>
                    </tr>
                </table>
            </li>
        </c:if>         
    </c:forEach>
</div>
<div id="boxContent8" style="display: none">
    <c:forEach var="item" items="${lstItem}" varStatus="status">
        <c:if test="${item.group == 8}"> 
            <li>
                <table style="width: 100%;margin-top: 5px;margin-bottom: 5px;">
                    <tr>
                        <td style="width: 80%">
                            <a onclick="${item.url}">${fn:escapeXml(item.tile)} </a>
                        </td>
                        <td style="width: 20%">
                            <label style="color: red;margin-right: 5px;float: right">${item.count}</label>
                        </td>
                    </tr>
                </table>
            </li>
        </c:if>         
    </c:forEach>
</div>

<script type="text/javascript">
    if (document.getElementById('boxContent1').innerHTML.trim() != "")
    {
        document.getElementById('divBox1').innerHTML = "<ul class=\"link-list\">" + document.getElementById('boxContent1').innerHTML.trim() + "</ul>";
        document.getElementById('hsCtd').style.display = "";
    }
    if (document.getElementById('boxContent2').innerHTML.trim() != "")
    {
        document.getElementById('divBox2').innerHTML = "<ul class=\"link-list\">" + document.getElementById('boxContent2').innerHTML.trim() + "</ul>";
        document.getElementById('hsDxl').style.display = "";
    }
    if (document.getElementById('boxContent3').innerHTML.trim() != "")
    {
        document.getElementById('divBox3').innerHTML = "<ul class=\"link-list\">" + document.getElementById('boxContent3').innerHTML.trim() + "</ul>";
        document.getElementById('hsSdbs').style.display = "";
    }
    if (document.getElementById('boxContent4').innerHTML.trim() != "")
    {
        document.getElementById('divBox4').innerHTML = "<ul class=\"link-list\">" + document.getElementById('boxContent4').innerHTML.trim() + "</ul>";
        document.getElementById('hsTl').style.display = "";
    }
    if (document.getElementById('boxContent5').innerHTML.trim() != "")
    {
        document.getElementById('divBox5').innerHTML = "<ul class=\"link-list\">" + document.getElementById('boxContent5').innerHTML.trim() + "</ul>";
        document.getElementById('hsDc').style.display = "";
    }
    if (document.getElementById('boxContent6').innerHTML.trim() != "")
    {
        document.getElementById('divBox6').innerHTML = "<ul class=\"link-list\">" + document.getElementById('boxContent6').innerHTML.trim() + "</ul>";
        document.getElementById('hsDaxl').style.display = "";
    }
    if (document.getElementById('boxContent7').innerHTML.trim() != "")
    {
        document.getElementById('divBox7').innerHTML = "<ul class=\"link-list\">" + document.getElementById('boxContent7').innerHTML.trim() + "</ul>";
        document.getElementById('hsvtcv').style.display = "";
    }
    if (document.getElementById('boxContent8').innerHTML.trim() != "")
    {
        document.getElementById('divBox8').innerHTML = "<ul class=\"link-list\">" + document.getElementById('boxContent8').innerHTML.trim() + "</ul>";
        document.getElementById('hsvldp').style.display = "";
    }
</script>

<div id="main-boxes" style="margin-left: 100px;margin-top: 10px">

    <!-- 1: Hồ sơ chờ thẩm định -->
    <!-- 2: Hồ sơ đang xử lý -->
    <!-- 3: Hồ sơ SĐBS -->
    <!-- 4: Hồ sơ bị trả lại -->
    <!-- 5: Hồ sơ đối chiếu -->
    <!-- 6: Hồ sơ đã xử lý -->


    <!-- Box -->

    <div class="box" id="hsCtd">
        <span class="left-arrow">&nbsp;</span>
        <h3>Thông tin chung</h3>
        <div class="box-content" id="divBox1">
        </div>
    </div>
    <div class="box" id="hsDxl">
        <span class="left-arrow">&nbsp;</span>
        <h3>Hs chờ LĐC xử lý</h3>
        <div class="box-content" id="divBox2">
        </div>
    </div>
    <div class="box" id="hsSdbs">
        <span class="left-arrow">&nbsp;</span>
        <h3>Hs chờ LĐP xử lý</h3>
        <div class="box-content" id="divBox3">
        </div>
    </div>
    <div class="box" id="hsvldp">
        <span class="left-arrow">&nbsp;</span>
        <h3>Hs SĐBS chờ LĐP xử lý</h3>
        <div class="box-content" id="divBox8">
        </div>
    </div>
    <div class="box" id="hsvtcv">
        <span class="left-arrow">&nbsp;</span>
        <h3>Hs chờ CV xử lý</h3>
        <div class="box-content" id="divBox7">
        </div>
    </div>
    <div class="box" id="hsTl">
        <span class="left-arrow">&nbsp;</span>
        <h3>Hồ sơ bị trả lại</h3>
        <div class="box-content" id="divBox4">
        </div>
    </div>
    <div class="box" id="hsDc">
        <span class="left-arrow">&nbsp;</span>
        <h3>Hồ sơ đối chiếu</h3>
        <div class="box-content" id="divBox5">
        </div>
    </div>
    <div class="box" id="hsDaxl">
        <span class="left-arrow">&nbsp;</span>
        <h3>Hồ sơ đã xử lý</h3>
        <div class="box-content" id="divBox6">
        </div>
    </div>    

    <%--
    <c:forEach var="item" items="${lstItem}" varStatus="status">
            <div onclick="${item.url}" style="width:200px; height: 120px; display: inline-table; background-color: ${item.color};padding:5px;margin: 5px; text-align: center; cursor: pointer ">
                <img src='${item.iconUrl}' width='69' height='69' style="text-align: center"/> 
                <div style="font-weight: bold; color: #000099;text-align: center">
                    ${fn:escapeXml(item.tile)} :
                    <font style="color:red" >
                    ${item.count}
                    </font>
                </div>
            </div>
    </c:forEach>
    </div>

    --%>