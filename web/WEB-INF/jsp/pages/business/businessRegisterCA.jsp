<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../util/util_js.jsp"/>
<jsp:include page="../common/commonJavascript.jsp"/>
<jsp:include page="register_CA_js.jsp" />
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<script>
    page.getNo = function(index) {
        return dijit.byId("caUserGrid").currentRow + index + 1;
    };
    page.getIndex = function(index) {
        return index + 1;
    };
    page.formatEdit = function(inData) {
        var row = inData - 1;
        var item = dijit.byId("caUserGrid").getItem(row);
        var url = "";
        if (item != null) {
            var url = "<div style='text-align:center;cursor:pointer;'><img src='share/images/icons/deleteStand.png' width='17px' height='17px' title='Tạo bản sao hồ sơ' onClick='page.deleteCA(" + item.caUserId + ");' /></div>";
        }
        return url;
        
    };

</script>

<div id="token">
    <s:token id="tokenId"/>
</div>

<div id ="searchDiv" style="display:none">

    <sd:TitlePane key="search.searchCondition" id="categoryTitle">
        <form id="searchForm" name="searchForm">
            <table width="100%;" cellpadding="0px" cellspacing="5px">
                <tr>
                    <td width="20%"></td>
                    <td width="30%"></td>
                    <td width="20%"></td>
                    <td width="30%"></td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Mã serial"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.caSerial"
                                    key=""
                                    name="searchForm.caSerial" maxlength="250"/>
                    </td>
                    <td align="right">
                    </td>
                    <td>
                    </td>
                </tr>                
                <tr style="text-align: center">
                    <td colspan="4">
                        <sx:ButtonSearch onclick="page.search();" />
                        <sd:Button key="" onclick="page.reset();" > 
                            <img src="share/images/icons/reset.png" height="14" width="18"/>
                            <span style="font-size:12px">Hủy<%--<sd:Property>btnCancel</sd:Property> --%></span>
                        </sd:Button>
                    </td>
                </tr>

            </table>
        </form>
    </sd:TitlePane>
</div>        

<sd:TitlePane key="Danh mục CA token"
              id="caUserList" >
    <sx:ResultMessage id="resultDeleteMessage" />
    <table width="100%">
        <tr>
            <td>
                <div style="width:100%;">
                    <sd:DataGrid id="caUserGrid"
                                 getDataUrl=""
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="category.No" get="page.getNo" width="5%"  styles="text-align:center;" />
                        <sd:ColumnDataGrid editable="true" key="Xóa" headerStyles="text-align:center;" width="30%" cellStyles="text-align:center;"
                                           formatter="page.formatEdit" get="page.getIndex"/>   
                        <sd:ColumnDataGrid  key="Số serial" field="caSerial"
                                            width="80%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="User" field="userName"
                                            width="80%"  headerStyles="text-align:center;" />
                    </sd:DataGrid>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <sd:Button key="" onclick="page.insert();">
                    <img src="share/images/icons/save.png" height="14" width="18" alt="Ghi lại">
                    <span style="font-size:12px">Đăng ký CA</span>
                </sd:Button>
<!--                <sd:Button key="" onclick="page.onDeleteCa();" >
                    <img src="share/images/icons/deleteStand.png" height="14" width="18" alt="Hủy">
                    <span style="font-size:12px">Hủy CA Token</span>
                </sd:Button>       -->
                <sx:ButtonSearch id="btnShowSearchPanel" onclick="page.showSearchPanel();" />
            </td>
        </tr>
    </table>
</sd:TitlePane>
<sd:Dialog  id="dlgReturn" height="auto" width="500px"
            key="" showFullscreenButton="true"
            >
    <jsp:include page="dlgRegisterCA.jsp"></jsp:include>
</sd:Dialog>
<script type="text/javascript">
    var check;
    var workingCaUserId;
    page.search = function() {
        dijit.byId("caUserGrid").vtReload('caUserAction!onSearch.do?', "searchForm");
    };

    page.insert = function() {
        check = true;
        dijit.byId("btnOK").domNode.style.display = "";
        dijit.byId("dlgReturn").vtSetTitle("Ký duyệt CA");
        dijit.byId("dlgReturn").show();
    };
    page.returnMessageDelete = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
        page.search();
    };
    page.showSearchPanel = function() {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };
    page.reset = function() {
        dijit.byId("searchForm.caSerial").setValue("");
    };
    page.deleteCA = function(caUserId) {
        check = false;
        workingCaUserId = caUserId;
        dijit.byId("btnOK").domNode.style.display = "";
        dijit.byId("dlgReturn").vtSetTitle("Xóa CA Token");
        dijit.byId("dlgReturn").show();
    };

    page.search();
</script>
