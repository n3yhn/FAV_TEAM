<%-- 
    Document   : confirmImportSatistPaperSearch
    Created on : Jul 1, 2013, 2:52:39 PM
    Author     : vtit_binhnt53
    Description: màn hình danh sách thông tin giấy tiếp nhận bản công bố hợp quy
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
<div id="mainDiv">
    <div id ="searchDiv" style="display:none">
        <sd:TitlePane key="search.searchCondition" id="receiptNoTitle">
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
                            <sd:Label key="Tên hàng hóa:"/>
                        </td>
                        <td>
                            <sd:TextBox cssStyle="width:100%"
                                        id="searchForm.productName"
                                        key=""
                                        name="searchForm.productName" 
                                        maxlength="250"
                                        trim="true"/>
                        </td>
                        <td align="right">
                            <sd:Label key="Ký hiệu mã:"/>
                        </td>
                        <td>
                            <sd:TextBox cssStyle="width:100%"
                                        id="searchForm.productCode"
                                        key=""
                                        name="searchForm.productCode" 
                                        maxlength="50"
                                        trim="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            <sd:Label key="Thương nhân nhập khẩu:"/>
                        </td>
                        <td>
                            <sd:TextBox cssStyle="width:100%"
                                        id="searchForm.importBusinessName"
                                        key=""
                                        name="searchForm.importBusinessName" 
                                        maxlength="250"
                                        trim="true"/>
                        </td>
                        <td align="right">
                            <sd:Label key="Thương nhân xuất khẩu:"/>
                        </td>
                        <td>
                            <sd:TextBox cssStyle="width:100%"
                                        id="searchForm.exportBusinessName"
                                        key=""
                                        name="searchForm.exportBusinessName" 
                                        maxlength="250"
                                        trim="true"/>
                        </td>
                    </tr>
                    <tr style="text-align: center">
                        <td colspan="4">
                            <sx:ButtonSearch onclick="page.search();" />
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

    <sd:TitlePane key="Nhập thông tin giấy xác nhận đạt yêu cầu nhập khẩu của cơ quan kiểm tra Nhà nước về chất lượng thực phẩm nhập khẩu"
                  id="List" >
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
                            if (item !== null) {
                                var url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                            title='<sd:Property>category.edit</sd:Property>' \n\
                            onClick='page.showEditPopup(" + row + ");' /></div>";
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
                        <div id="" style="width:100%;">
                        <sd:DataGrid id="Grid"
                                     getDataUrl=""
                                     rowSelector="0%"
                                     style="width:auto;height:auto"
                                     rowsPerPage="20"
                                     serverPaging="true"
                                     clientSort="false">
                            <sd:ColumnDataGrid key="STT" get="page.getNo" width="3%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatEdit" get="page.getIndex"
                                               width="5%"  headerStyles="text-align:center;"  />
                            <sd:ColumnDataGrid  key="Mã sản phẩm" field="productCode"
                                                width="20%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName"
                                                width="50%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Thương nhân nhập khẩu" field="importBusinessName"
                                                width="50%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Thương nhân xuất khẩu" field="exportBusinessName"
                                                width="50%"  headerStyles="text-align:center;" />
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <sx:ButtonAddCategory onclick="page.insert();"/>
                    <sx:ButtonDelete onclick="page.deleteItem()" />
                    <sx:ButtonSearch id="btnShowSearchPanel" onclick="page.showSearchPanel();" />
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>
<div id="createDiv" style="display: none">    
    <jsp:include page="confirmImportSatistPaperCreateDlg.jsp" flush="false"/>
</div>
<script>
    //
    var grid = dijit.byId("Grid");
    //show edit popup
    page.showEditPopup = function(row) {
        var item = dijit.byId("Grid").getItem(row);
        page.setItem(item);
        document.getElementById("mainDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";

    };
    //set data 
    page.setItem = function(item) {
        //code cont
        dijit.byId("createForm.confirmImportSatistPaperId").setValue(item.confirmImportSatistPaperId);
        dijit.byId("createForm.testAgencyName").setValue(item.testAgencyName);
        dijit.byId("createForm.testAgencyId").setValue(item.testAgencyId);
        dijit.byId("createForm.testAgencyAdd").setValue(item.testAgencyAdd);
        dijit.byId("createForm.testAgencyTel").setValue(item.testAgencyTel);
        dijit.byId("createForm.testAgencyFax").setValue(item.testAgencyFax);
        dijit.byId("createForm.conclusion").setValue(item.conclusion);
        dijit.byId("createForm.exportBusinessName").setValue(item.exportBusinessName);
        dijit.byId("createForm.exportBusinessAdd").setValue(item.exportBusinessAdd);
        dijit.byId("createForm.exportBusinessMail").setValue(item.exportBusinessMail);
        dijit.byId("createForm.exportBusinessTel").setValue(item.exportBusinessTel);
        dijit.byId("createForm.exportBusinessFax").setValue(item.exportBusinessFax);
        dijit.byId("createForm.exportContractCode").setValue(item.exportContractCode);
        if (item.exportContractDate != "") {
            dijit.byId("createForm.exportContractDate").setValue(page.convertStringToDate(item.exportContractDate));
        }
        dijit.byId("createForm.exportLadingCode").setValue(item.exportLadingCode);
        if (item.exportLadingDate != "") {
            dijit.byId("createForm.exportLadingDate").setValue(page.convertStringToDate(item.exportLadingDate));
        }
        dijit.byId("createForm.exportPort").setValue(item.exportPort);
        dijit.byId("createForm.importBusinessName").setValue(item.importBusinessName);
        dijit.byId("createForm.importBusinessAddress").setValue(item.importBusinessAddress);
        dijit.byId("createForm.importBusinessEmail").setValue(item.importBusinessEmail);
        dijit.byId("createForm.importBusinessTel").setValue(item.importBusinessTel);
        dijit.byId("createForm.importBusinessFax").setValue(item.importBusinessFax);
        dijit.byId("createForm.importPort").setValue(item.importPort);
        if (item.importDate != "") {
            dijit.byId("createForm.importDate").setValue(page.convertStringToDate(item.importDate));
        }
        dijit.byId("createForm.productName").setValue(item.productName);
        dijit.byId("createForm.productDescription").setValue(item.productDescription);
        dijit.byId("createForm.productCode").setValue(item.productCode);
        dijit.byId("createForm.productOrigin").setValue(item.productOrigin);
        dijit.byId("createForm.productAmount").setValue(item.productAmount);
        dijit.byId("createForm.productWeight").setValue(item.productWeight);
        dijit.byId("createForm.productValue").setValue(item.productValue);
        dijit.byId("createForm.gatheringAdd").setValue(item.gatheringAdd);
        if (item.testDate != "") {
            dijit.byId("createForm.testDate").setValue(page.convertStringToDate(item.testDate));
        }
        dijit.byId("createForm.testAdd").setValue(item.testAdd);
        dijit.byId("createForm.businessRepresent").setValue(item.businessRepresent);
        if (item.businessSigndate != "") {
            dijit.byId("createForm.businessSigndate").setValue(page.convertStringToDate(item.businessSigndate));
        }
        dijit.byId("createForm.businessSignAdd").setValue(item.businessSignAdd);
        dijit.byId("createForm.agencyRepresent").setValue(item.agencyRepresent);
        dijit.byId("createForm.agencySignAdd").setValue(item.agencySignAdd);
        if (item.agencySigndate != "") {
            dijit.byId("createForm.agencySigndate").setValue(page.convertStringToDate(item.agencySigndate));
        }
        dijit.byId("createForm.standardTargetNo").setValue(item.standardTargetNo);
        if (item.standardTargetDate != "") {
            dijit.byId("createForm.standardTargetDate").setValue(page.convertStringToDate(item.standardTargetDate));
        }
        dijit.byId("createForm.releaseDocumentNo").setValue(item.releaseDocumentNo);
        if (item.releaseDocumentDate != "") {
            dijit.byId("createForm.releaseDocumentDate").setValue(page.convertStringToDate(item.releaseDocumentDate));
        }
        dijit.byId("createForm.isActive").setValue(1);
        dijit.byId("createForm.importContractCode").setValue(item.importContractCode);
        if (item.importContractDate != "") {
            dijit.byId("createForm.importContractDate").setValue(page.convertStringToDate(item.importContractDate));
        }
    };
    //action search
    page.search = function() {
        grid.vtReload('confirmImportSatistPaperAction!onSearch.do?', "searchForm", null, null);
    };
    //action reset field for search form
    page.reset = function() {
        dijit.byId("searchForm.productCode").setValue("");
        dijit.byId("searchForm.productName").setValue("");
        dijit.byId("searchForm.exportBusinessName").setValue("");
        dijit.byId("searchForm.importBusinessName").setValue("");
        page.search();
    };
    //action insert show dialog
    page.insert = function() {
        page.clearInsertForm();
        document.getElementById("mainDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
    };
    //action clear before insert
    page.clearInsertForm = function() {
        //code cont
        dijit.byId("createForm.confirmImportSatistPaperId").setValue("");
        dijit.byId("createForm.testAgencyName").setValue("");
        dijit.byId("createForm.testAgencyId").setValue("");
        dijit.byId("createForm.testAgencyAdd").setValue("");
        dijit.byId("createForm.testAgencyTel").setValue("");
        dijit.byId("createForm.testAgencyFax").setValue("");
        dijit.byId("createForm.conclusion").setValue("");
        dijit.byId("createForm.exportBusinessName").setValue("");
        dijit.byId("createForm.exportBusinessAdd").setValue("");
        dijit.byId("createForm.exportBusinessMail").setValue("");
        dijit.byId("createForm.exportBusinessTel").setValue("");
        dijit.byId("createForm.exportBusinessFax").setValue("");
        dijit.byId("createForm.exportContractCode").setValue("");
        dijit.byId("createForm.exportContractDate").setValue("");
        dijit.byId("createForm.exportLadingCode").setValue("");
        dijit.byId("createForm.exportLadingDate").setValue("");
        dijit.byId("createForm.exportPort").setValue("");
        dijit.byId("createForm.importBusinessName").setValue("");
        dijit.byId("createForm.importBusinessAddress").setValue("");
        dijit.byId("createForm.importBusinessEmail").setValue("");
        dijit.byId("createForm.importBusinessTel").setValue("");
        dijit.byId("createForm.importBusinessFax").setValue("");
        dijit.byId("createForm.importPort").setValue("");
        dijit.byId("createForm.importDate").setValue("");
        dijit.byId("createForm.productName").setValue("");
        dijit.byId("createForm.productDescription").setValue("");
        dijit.byId("createForm.productCode").setValue("");
        dijit.byId("createForm.productOrigin").setValue("");
        dijit.byId("createForm.productAmount").setValue("");
        dijit.byId("createForm.productWeight").setValue("");
        dijit.byId("createForm.productValue").setValue("");
        dijit.byId("createForm.gatheringAdd").setValue("");
        dijit.byId("createForm.testDate").setValue("");
        dijit.byId("createForm.testAdd").setValue("");
        dijit.byId("createForm.businessRepresent").setValue("");
        dijit.byId("createForm.businessSigndate").setValue("");
        dijit.byId("createForm.businessSignAdd").setValue("");
        dijit.byId("createForm.agencyRepresent").setValue("");
        dijit.byId("createForm.agencySignAdd").setValue("");
        dijit.byId("createForm.agencySigndate").setValue("");
        dijit.byId("createForm.standardTargetNo").setValue("");
        dijit.byId("createForm.standardTargetDate").setValue("");
        dijit.byId("createForm.releaseDocumentNo").setValue("");
        dijit.byId("createForm.releaseDocumentDate").setValue("");
        dijit.byId("createForm.isActive").setValue(1);
        dijit.byId("createForm.importContractCode").setValue("");
        dijit.byId("createForm.importContractDate").setValue("");
    };
    page.deleteItem = function() {
        if (!dijit.byId("Grid").vtIsChecked()) {
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
                    }
                    else {
                        msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
                                }
                            };

                            page.deleteItemExecute = function() {
                                var content = grid.vtGetCheckedDataForPost("lstItemOnGrid");
                                sd.connector.post("confirmImportSatistPaperAction!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
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
                            page.convertStringToDate = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dgDate) {
                                try {
                                    var dateStr = page.getString(dgDate);
                                    var temp = dateStr.split("-");
                                    return new Date(temp[1] + "/" + temp[2].split("T")[0] + "/" + temp[0]);
                                } catch (e) {
//            page.alert("Thông báo", "function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss", "warning");
                                    return undefined;
                                }
                            };
                            page.search();
</script>
