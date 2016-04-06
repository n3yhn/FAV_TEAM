<%-- 
    Document   : registerApprove
    Created on : Jun 17, 2013, 1:55:45 PM
    Author     : vtit_binhnt53
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../../util/util_js.jsp"/>
<jsp:include page="../../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="token">
    <s:token id="tokenId"/>
</div>
<%@include file="registerApprove.jsp" %>
<div id ="searchDiv" style="display:none">
    <sd:TitlePane key="search.searchCondition" id="registerTitle">
        <form id="searchForm" name="searchForm">
            <table width="100%;" cellpadding="0px" cellspacing="5px">
                <tr>
                    <td width="15%"></td>
                    <td width="35%"></td>
                    <td width="15%"></td>
                    <td width="35%"></td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Doanh nghiệp:"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.businessNameVi"
                                    key=""
                                    name="searchForm.businessNameVi" maxlength="255"/>
                    </td>
                    <td align="right">
                        <sd:Label key="MST:"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.businessTaxCode"
                                    key=""
                                    name="searchForm.businessTaxCode" maxlength="30"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Người đăng ký:"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.userFullName"
                                    key=""
                                    name="searchForm.userFullName" maxlength="255"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Email"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.manageEmail"
                                    key=""
                                    name="searchForm.manageEmail" maxlength="100"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Trạng thái:"/>
                    </td>
                    <td>
                        <sd:SelectBox id="searchForm.status" name="searchForm.status" key="" >
                            <sd:Option value='-1'>-- Chọn --</sd:Option>
                            <sd:Option value='0' selected="true">Chưa phê duyệt</sd:Option>
                            <sd:Option value='1'>Đã phê duyệt</sd:Option>
                            <sd:Option value='2'>Đã từ chối</sd:Option>

                        </sd:SelectBox>
                    </td>
                    <td align="right">
                    </td>
                    <td>

                    </td>
                </tr>
                <tr style="text-align: center">
                    <td colspan="4">
                        <sx:ButtonSearch onclick="page.search();" id="btnSearch" />
                        <sd:Button key="" onclick="page.reset();" > 
                            <img src="${contextPath}/share/images/icons/reset.png" height="14" width="18">
                            <span style="font-size:12px">Hủy<%--<sd:Property>btnCancel</sd:Property> --%></span>
                        </sd:Button>
                    </td>
                </tr>

            </table>
        </form>
    </sd:TitlePane>
</div>        
<div id="viewDiv" style="width:100%; overflow:auto">
    <sd:TitlePane key="Danh sách tài khoản phê duyệt"
                  id="registerList" >
        <sx:ResultMessage id="resultDeleteMessage" />
        <table width="100%">
            <tr>
                <td>
                    <script>
                        page.getNo = function(index) {
                            return dijit.byId("Grid").currentRow + index + 1;
                        };

                        page.getIndex = function(index) {
                            return index + 1;
                        };

                        page.formatEdit = function(inData) {
                            var row = inData - 1;
                            var item = dijit.byId("Grid").getItem(row);
                            var url = "";
                            if (item != null) {
                                var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/record_view.png' width='17px' height='17px' \n\
                            title='<sd:Property>category.edit</sd:Property>' \n\
                            onClick='page.showApproveDiv(" + row + ");' /></div>";
                            }
                            return url;
                        };
                        page.formatStatus = function(inData) {
                            var item = dijit.byId("Grid").getItem(inData - 1);
                            var url = "";
                            if (item != null) {
                                var status = parseInt(item.status);
                                switch (status) {
                                    case 0:
                                        url = "Chưa phê duyệt";
                                        break;
                                    case 1:
                                        url = "Đã phê duyệt";
                                        break;
                                    case 2:
                                        url = "Đã từ chối phê duyệt";
                                        break;
                                    default:
                                        url = "Chưa phê duyệt";
                                }
                            }

                            return url;
                        };

                        // enter key
                        page.searchDefault = function(evt) {
                            var dk = dojo.keys;
                            switch (evt.keyCode) {
                                case dk.ENTER:
                                    page.search();
                                    break;
                            }
                        }

                        dojo.connect(dojo.byId("searchForm"), "onkeypress", page.searchDefault);
                        </script>

                    <sd:DataGrid id="Grid"
                                 getDataUrl=""
                                 rowSelector="0%"
                                 style="width:auto;height:auto"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="STT" get="page.getNo" width="10%"  styles="text-align:center;" />                       

                        <sd:ColumnDataGrid  key="Doanh nghiệp" field="businessNameVi"
                                            width="25%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Mã số thuế" field="businessTaxCode"
                                            width="20%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Số đăng ký" field="businessLicense"
                                            width="20%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Email" field="manageEmail"
                                            width="20%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Người đăng ký" field="userFullName"
                                            width="20%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Ngày đăng ký" field="createDate" format="dd/MM/yyyy" type="date"
                                                width="8%"  headerStyles="text-align:center;" cellStyles="text-align:center;" /> 
                        <sd:ColumnDataGrid  key="Trạng thái" get="page.getIndex" formatter="page.formatStatus" cellStyles="text-align:center;"
                                            width="10%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid key="Xem" formatter="page.formatEdit" get="page.getIndex"
                                           width="10%"  headerStyles="text-align:center;"  />
                    </sd:DataGrid>

                </td>
            </tr>
            <tr>
                <td>
                    <sx:ButtonSearch id="btnShowSearchPanel" onclick="page.showSearchPanel();" />
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>
<sd:Dialog  id="dlgReasonApprove" height="auto" width="400px"
            key="Lí do" showFullscreenButton="false"
            >
    <jsp:include page="reasonApproveDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>
<script>
    var grid = dijit.byId("Grid");
    var isInsertDialog = false;

    page.showApproveDiv = function(row) {
        var item = dijit.byId("Grid").getItem(row);
        page.setItem(item);
        page.showApprovePanel();
    };

    page.setItem = function(item) {
        dijit.byId("approveForm.registerId").setValue(item.registerId);
        dijit.byId("approveForm.businessTypeId").setValue(item.businessTypeId);
//        dijit.byId("approveForm.managePassword").setValue(item.managePassword);
        dijit.byId("approveForm.posId").setValue(item.posId);
        document.getElementById("approveForm.vbusinessTypeName").innerHTML = escapeHtml_(item.businessTypeName);
        document.getElementById("approveForm.vmanageEmail").innerHTML = escapeHtml_(item.manageEmail);
        document.getElementById("approveForm.vuserTelephone").innerHTML = escapeHtml_(item.userTelephone);
        document.getElementById("approveForm.vuserMobile").innerHTML = escapeHtml_(item.userMobile);
        document.getElementById("approveForm.vbusinessNameVi").innerHTML = escapeHtml_(item.businessNameVi);
        document.getElementById("approveForm.vbusinessNameEng").innerHTML = escapeHtml_(item.businessNameEng);
        document.getElementById("approveForm.vbusinessNameAlias").innerHTML = escapeHtml_(item.businessNameAlias);
        document.getElementById("approveForm.vbusinessTaxCode").innerHTML = escapeHtml_(item.businessTaxCode);
        document.getElementById("approveForm.vbusinessLicense").innerHTML = escapeHtml_(item.businessLicense);
        document.getElementById("approveForm.vgoverningBody").innerHTML = escapeHtml_(item.governingBody);//u141213 binhnt53
        document.getElementById("approveForm.vbusinessAdd").innerHTML = escapeHtml_(item.businessAdd);
        document.getElementById("approveForm.vbusinessProvince").innerHTML = escapeHtml_(item.businessProvince);
        document.getElementById("approveForm.vbusinessTelephone").innerHTML = escapeHtml_(item.businessTelephone);
        document.getElementById("approveForm.vbusinessFax").innerHTML = escapeHtml_(item.businessFax);
        document.getElementById("approveForm.vbusinessWebsite").innerHTML = escapeHtml_(item.businessWebsite);
        document.getElementById("approveForm.vbusinessLawRep").innerHTML = escapeHtml_(item.businessLawRep);
        document.getElementById("approveForm.vdescription").innerHTML = escapeHtml_(item.description);
        document.getElementById("approveForm.vbusinessEstablishYear").innerHTML = escapeHtml_(item.businessEstablishYear);
        document.getElementById("approveForm.vuserFullName").innerHTML = escapeHtml_(item.userFullName);
        document.getElementById("approveForm.vuserEmail").innerHTML = escapeHtml_(item.userEmail);
        document.getElementById("approveForm.vmanagePassword").innerHTML = escapeHtml_(item.managePassword);
        document.getElementById("approveForm.vreason").innerHTML = escapeHtml_(item.reason);
        if (item.status == 0) {
            dijit.byId("btnApprove").domNode.style.display = "";
            dijit.byId("btnNotApprove").domNode.style.display = "";
        } else {
            dijit.byId("btnApprove").domNode.style.display = "none";
            dijit.byId("btnNotApprove").domNode.style.display = "none";            
        }
    };

    page.search = function() {
        grid.vtReload('registerSearchAction!onSearch.do?', "searchForm", null, null);
    };
    page.reset = function() {
        dijit.byId("searchForm.businessNameVi").setValue("");
        dijit.byId("searchForm.businessTaxCode").setValue("");
        dijit.byId("searchForm.userFullName").setValue("");
        dijit.byId("searchForm.manageEmail").setValue("");
        dijit.byId("searchForm.status").setValue(-1);
        grid.vtReload('registerSearchAction!onSearch.do?', "searchForm", null, null);
    };

    page.showSearchPanel = function() {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };

    page.showApprovePanel = function() {
        var panel = document.getElementById("approveDiv");
        panel.setAttribute("style", "display:;");

        var spanel = document.getElementById("searchDiv");
        spanel.setAttribute("style", "display:none;");

        var vpanel = document.getElementById("viewDiv");
        vpanel.setAttribute("style", "display:none;");
    };

    page.backView = function() {
        var panel = document.getElementById("approveDiv");
        panel.setAttribute("style", "display:none;");

        var spanel = document.getElementById("searchDiv");
        spanel.setAttribute("style", "display:none;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:block");
        var vpanel = document.getElementById("viewDiv");
        vpanel.setAttribute("style", "display:;");


    };

    page.approveExecute = function() {
        dijit.byId("approveForm.status").setValue(1);
        dijit.byId("approveForm.reason").setValue("Đã phê duyệt");
        sd.connector.post("registerSearchAction!onApprove.do?" + token.getTokenParamString(), null, "approveForm", null, page.afterSave);
    };
    page.approve = function() {
        msg.confirm('<sd:Property>Bạn có chắc chắn phê duyệt tài khoản này</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.approveExecute);
            };
            page.notApprove = function() {
                if (page.validateReason()) {
                    dijit.byId("approveForm.status").setValue(2);
                    dijit.byId("approveForm.reason").setValue(dijit.byId("reasonApprove.reason").getValue());
                    sd.connector.post("registerSearchAction!onApprove.do?" + token.getTokenParamString(), null, "approveForm", null, page.afterSave);
                }
            };

            page.afterSave = function(data) {
                var obj = dojo.fromJson(data);
                var result = obj.items;
                resultMessage_show("resultApproveMessage", result[0], result[1], 5000);
                page.hideNotApprove();
                page.search();
                page.backView();
            };
            page.search();
            //
            page.validateReason = function() {
                var reason = dijit.byId("reasonApprove.reason").getValue();
                if (reason == null || reason.trim().length == 0) {
                    alert("Bạn chưa nhập lí do");
                    dijit.byId("reasonApprove.reason").focus();
                    return false;
                }
                return true;
            };
            page.showNotApprove = function() {
                dijit.byId("reasonApprove.reason").setValue("");
                dijit.byId("dlgReasonApprove").show();
            };
            page.hideNotApprove = function() {
                dijit.byId("dlgReasonApprove").hide();
            };
</script>

