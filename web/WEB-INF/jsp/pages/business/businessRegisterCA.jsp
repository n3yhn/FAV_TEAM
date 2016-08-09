<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../util/util_js.jsp"/>
<jsp:include page="../common/commonJavascript.jsp"/>
<jsp:include page="register_CA_js.jsp" />
<object id="plugin0" type="application/x-viettelcasigner" width="3" height="10">
</object>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<script>
    page.getNo = function (index) {
        return dijit.byId("caUserGrid").currentRow + index + 1;
    };
    page.getIndex = function (index) {
        return index + 1;
    };
    page.formatEdit = function (inData) {
        var row = inData - 1;
        var item = dijit.byId("caUserGrid").getItem(row);
        var url = "";
        if (item != null) {
            var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                        title='<sd:Property>category.edit</sd:Property>' \n\
                        onClick='page.showEditPopup(" + row + ");' /></div>";
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
                            <span style="font-size:12px">Hủy</span>
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
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" 
                                           headerCheckbox="true" headerStyles="text-align:center;" 
                                           type="checkbox" width="5%" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatEdit" get="page.getIndex"
                                           width="5%"  headerStyles="text-align:center;"  />
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
                <sd:Button key="" onclick="page.insertFP();">
                    <img src="share/images/icons/save.png" height="14" width="18" alt="Ghi lại">
                    <span style="font-size:12px">Đăng ký CA bằng Plugin</span>
                </sd:Button>
                <sx:ButtonDelete onclick="page.deleteItem()" />
                <sx:ButtonSearch id="btnShowSearchPanel" onclick="page.showSearchPanel();" />
            </td>
        </tr>
    </table>
</sd:TitlePane>
<sd:Dialog  id="businessAddEditCaUserDlg" height="auto" width="500px"
            key="" showFullscreenButton="true"
            >
    <jsp:include page="businessAddEditCaUserDlg.jsp"></jsp:include>
</sd:Dialog>
<jsp:include page="../../pages/files/lookup/pluginJSRegister.jsp" flush="false"></jsp:include>

    <script type="text/javascript">
        var grid = dijit.byId("caUserGrid");
        var dlg = dijit.byId("businessAddEditCaUserDlg");


        backPage = function () {
            var caUserGrid_VTGrid = dijit.byId('caUserGrid_VTGrid');            
            if (caUserGrid_VTGrid) {
                caUserGrid_VTGrid.destroyRecursive(true);
            }
            var categoryTitle = dijit.byId('categoryTitle');         
            if (categoryTitle) {
                categoryTitle.destroyRecursive(true);
            }
            var caUserList = dijit.byId('caUserList');
            if (caUserList) {
                caUserList.destroyRecursive(true);
            }
            var businessAddEditCaUserDlg = dijit.byId('businessAddEditCaUserDlg');
            if (businessAddEditCaUserDlg) {
                businessAddEditCaUserDlg.destroyRecursive(true);
            }
//          sd.connector.post("caUserAction!caUserAction.do?", "createDiv", null, null, null);  
//           doGoToMenu("caUserAction!toBusinessRegisterCA.do?");
//sd.connector.post("caUserAction!toBusinessRegisterCA.do?", "createDiv", null, null, null);
        };

        page.showEditPopup = function (row) {
            var item = grid.getItem(row);
            page.setItem(item);
            dlg.show();
        };

        page.setItem = function (item) {
            dijit.byId("createForm.caUserId").setValue(item.caUserId);
            dijit.byId("createForm.caSerial").setValue(item.caSerial);
            dijit.byId("createForm.userName").setValue(item.userName);
            dijit.byId("createForm.name").setValue(item.name);
            dijit.byId("createForm.position").setValue(item.position);
            //        dijit.byId("createForm.command").setValue(item.command);        
            clearAttFile("createForm.upload");
            getAttacthFile(item.technicalStandardId, 23, "createForm.upload");
        };

        var check;
        var workingCaUserId;
        page.search = function () {
            dijit.byId("caUserGrid").vtReload('caUserAction!onSearch.do?', "searchForm");
        };

        page.showSearchPanel = function () {
            var panel = document.getElementById("searchDiv");
            panel.setAttribute("style", "display:;");
            dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
        };
        page.reset = function () {
            dijit.byId("searchForm.caSerial").setValue("");
        };

        page.search();

        page.insertFP = function () {
            var item = updateCertOfFile();
            cert = encodeBase64(item.certChain);
            sd.connector.post("filesAction!actionSignCARegisterCA.do?cert=" + cert, null, null, null, page.afterCheckCARegister);
        };

        page.afterCheckCARegister = function (data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            if (result[0] == "1") {
                alert(result[1]);
            } else {
                alert("Ký số không thành công ! " + result[1]);
            }
            page.search();
        };
        page.afterSignPluginC = function ()
        {
            sd.connector.post("filesAction!onReturnFiles.do?signFileId=" + signFileId + "&" + token.getTokenParamString(), null, "feedbackGiveBackForm", null, page.afterAproveVT);
        };
        page.afterAproveVT = function (data) {
            var obj = dojo.fromJson(data);
            alert(result[1]);
            if (result[0] == "1") {
                document.getElementById("divSignProcess").style.display = "none";
                onCloseFeedbackGiveBackForm();
                page.search();
            }

        };

        page.deleteItem = function () {
            if (!dijit.byId("caUserGrid").vtIsChecked()) {
                msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
                        } else {
                            msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
                                    }
                                };

                                page.deleteItemExecute = function () {
                                    var content = dijit.byId("caUserGrid").vtGetCheckedDataForPost("lstItemOnGrid");
                                    sd.connector.post("caUserAction!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
                                };

                                page.returnMessageDelete = function (data) {
                                    var obj = dojo.fromJson(data);
                                    var result = obj.items;
                                    resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
                                    page.search();
                                };

                                backPage();
</script>
