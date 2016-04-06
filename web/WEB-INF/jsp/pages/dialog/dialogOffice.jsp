<%-- 
    Document   : dialogOffice
    Created on : May 10, 2012, 3:50:02 PM
    Author     : sytv
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sd:TitlePane key="dialogOffice.officeLevel" id=""> 
    <table style="width: 100%;">
        <tr>
            <td style="width: 40%;text-align: center;"></td>
            <td style="width: 60%;text-align: center;"></td>
        </tr>
        <tr>
            <td>
                <sd:Label key="Cấp cơ quan" cssStyle="font-weight:bold;"/>
            </td>
            <td>
                <sd:Label key="Danh sách cơ quan theo cấp" cssStyle="font-weight:bold;"/>
            </td>
        </tr>
        <tr>
            <td>
                <div id="officeLevel" style="width:100%; overflow:auto">
                    <sd:MultiSelect id="officeLevelId" 
                                    key="" 
                                    name="officeLevelId" 
                                    data="officeLevelList" 
                                    labelField="officeLevelName" valueField="officeLevelId"
                                    cssStyle="height: 300px;width:98% !important;"
                                    onchange="page.dlgOfficeLoadOffice(this)">
                    </sd:MultiSelect>
                </div>
            </td>
            <td>
                <div id="dialogOffice.outsiteOffice">
                    <jsp:include page="outsiteOffice.jsp" flush="false"></jsp:include>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <sd:Button key="" onclick="page.dlgOfficeChooseOffice();" > 
            <img src="${contextPath}/share/images/icons/approve.png" height="14" width="14">
            <span style="font-size:12px">Đồng ý</span>
        </sd:Button>
                <sx:ButtonClose onclick="page.closeDlgOffice()" />
            </td>
        </tr>
    </table>
</sd:TitlePane>

<script>

    page.dlgOfficeLoadOffice = function(obj) {
        var officeLevelId =  dijit.byId(obj).attr("value");  
        sd.connector.post("voOutsiteOffice!listOutsiteOffice.do?officeLevelId="+ officeLevelId, "dialogOffice.outsiteOffice",null,null);            
    }
 
</script>
