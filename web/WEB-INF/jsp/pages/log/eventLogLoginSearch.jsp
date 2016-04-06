<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../util/util_js.jsp"/>
<jsp:include page="../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="token">
    <s:token id="tokenId"/>
</div>
<div id ="searchEventLogLoginDiv">

    <sd:TitlePane key="search.searchCondition" id="eventLogLoginTitle">
        <form id="searchEventLogLoginForm" name="searchEventLogLoginForm">
            <table width="100%;" cellpadding="0px" cellspacing="5px">
                <tr>
                    <td width="20%"></td>
                    <td width="30%"></td>
                    <td width="20%"></td>
                    <td width="30%"></td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Từ ngày:"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchEventLogLoginForm.eventDateFrom"
                                       key=""
                                       name="searchEventLogLoginForm.eventDateFrom" format="dd/MM/yyyy"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày:"/>
                    </td>
                    <td>
                        <sx:DatePicker cssStyle="width:100%"
                                       id="searchEventLogLoginForm.eventDateTo"
                                       key=""
                                       name="searchEventLogLoginForm.eventDateTo" format="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Người dùng:"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchEventLogLoginForm.userName"
                                    key=""
                                    name="searchEventLogLoginForm.userName" maxlength="20"/>
                    </td>
                    <td align="right">
                    </td>
                    <td>

                    </td>
                </tr>
                <tr style="text-align: center">
                    <td colspan="4">
                        <sx:ButtonSearch onclick="page.searchEventLogLogin();" />
                        <sd:Button key="" onclick="page.resetEventLogLogin();" > 
                            <img src="${contextPath}/share/images/icons/reset.png" height="14" width="18">
                            <span style="font-size:12px">Hủy<%--<sd:Property>btnCancel</sd:Property> --%></span>
                        </sd:Button>
                    </td>
                </tr>
            </table>
        </form>
    </sd:TitlePane>


    <sd:TitlePane key="Báo cáo đăng nhập"
                  id="reportLoginList" >
        <sx:ResultMessage id="resultDeleteMessage" />
        <table width="100%">
            <tr>
                <td>
                    <script>
                        page.getNo = function(index) {
                            return dijit.byId("reportLoginGrid").currentRow + index + 1;
                        }
                    </script>
                    <div id="" style="width:100%;">
                        <sd:DataGrid id="reportLoginGrid"
                                     getDataUrl=""
                                     rowSelector="0%"
                                     style="width:auto;height:auto"
                                     rowsPerPage="20"
                                     serverPaging="true"
                                     clientSort="false">
                            <sd:ColumnDataGrid key="STT" get="page.getNo" width="5%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Người dùng" field="userName"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày giờ đăng nhập" field="eventDate"
                                                width="15%"  headerStyles="text-align:center;" 
                                                type="date" format="dd/MM/yyyy hh:mm:s aaa" />
                            <sd:ColumnDataGrid  key="IP đăng nhập" field="ip"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="WAN" field="wan"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="MAC" field="mac"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Trạng thái đăng nhập" field="action"
                                                width="20%"  headerStyles="text-align:center;" />
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>
<script type="text/javascript">
    var grid = dijit.byId("reportLoginGrid");
    page.searchEventLogLogin = function() {
        grid.vtReload('eventLogLoginAction!onSearch.do', "searchEventLogLoginForm", null, null);
    }
    page.resetEventLogLogin = function() {
        dijit.byId("searchEventLogLoginForm.eventDateFrom").setValue("");
        dijit.byId("searchEventLogLoginForm.eventDateTo").setValue("");
        dijit.byId("searchEventLogLoginForm.userName").setValue("");
    }
    page.searchEventLogLogin();    
</script>