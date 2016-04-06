<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib  prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../../util/util_js.jsp"/>
<jsp:include page="../../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="token">
    <s:token id="tokenId"/>
</div>
<div id ="searchDiv" style="display:none">

    <sd:TitlePane key="search.searchCondition" id="FeeTitle">
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
                        <sd:Label key="Tên phí"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%" trim="true"
                                    id="searchForm.feeName"
                                    name="searchForm.feeName"
                                    key=""
                                    maxlength="250"
                                    />
                    </td>



                    <td align="right">
                        <sd:Label key="Trạng thái"/>
                    </td>
                    <td>
                        <sd:SelectBox id="searchForm.isActive" name="searchForm.isActive" key=""  cssStyle="width:100%" >
                            <sd:Option value='-1'>-- Chọn --</sd:Option>
                            <sd:Option value='1' selected="true">Hoạt động</sd:Option>
                            <sd:Option value='0'>Không hoạt động</sd:Option>

                        </sd:SelectBox>
                    </td>
                </tr>


                <tr style="text-align: center">
                    <td colspan="4">
                        <sx:ButtonSearch onclick="page.search();" />
                        <sd:Button key="" onclick="page.reset();" > 
                            <img src="${contextPath}/share/images/icons/reset.png" height="14" width="18">
                            <span style="font-size:12px">Hủy<%--<sd:Property>btnCancel</sd:Property> --%></span>
                        </sd:Button>
                        <sd:TextBox cssStyle="display:none" trim="true"
                                    id="searchForm.flagSavePaging"
                                    name="searchForm.flagSavePaging"
                                    key=""
                                    value="1"
                                    />
                    </td>
                </tr>

            </table>
        </form>
    </sd:TitlePane>
</div>        

<sd:TitlePane key="Danh sách biểu phí hồ sơ"
              id="FeeList" >
    <sx:ResultMessage id="resultDeleteMessage" />
    <table width="100%">
        <tr>
            <td>
                <script>
                    page.getNo = function(index) {
                        return dijit.byId("FeeGrid").currentRow + index + 1;
                    };
                    page.getIndex = function(index) {
                        return index + 1;
                    };
                    page.formatEdit = function(inData) {
                        var row = inData - 1;
                        var item = dijit.byId("FeeGrid").getItem(row);
                        var url = "";

                        if (item != null) {

                            url = "<div style='text-align:center;cursor:pointer;'><img src='${contextPath}/share/images/edit.png' width='17px' height='17px' \n\
                        title='<sd:Property>category.edit</sd:Property>' \n\
                        onClick='page.showEditPopup(" + row + ");' /></div>";

                        }
                        return url;
                    };
                    page.formatStatus = function(inData) {
                        var item = dijit.byId("FeeGrid").getItem(inData - 1);
                        var url = "";

                        return url;
                    };
                    page.formatIsActive = function(inData) {
                        var item = dijit.byId("FeeGrid").getItem(inData - 1);
                        var url = "";
                        if (item != null) {
                            var isDayOff = parseInt(item.isActive);
                            switch (isDayOff) {
                                case 0:
                                    url = "Không hoạt động";
                                    break;
                                case 1:
                                    url = "Hoạt động";
                                    break;
                                default:
                                    url = "Không hoạt động";
                            }
                        }
                        return url;
                    };
                    page.formatfeeType = function(inData) {
                        var item = dijit.byId("FeeGrid").getItem(inData - 1);
                        var url = "";
                        if (item != null) {
                            var isDayOff = parseInt(item.feeType);
                            switch (isDayOff) {
                                case 1:
                                    url = "Lệ phí";
                                    break;
                                case 2:
                                    url = "Nộp phí";
                                    break;
                                default:
                                    url = "Không rõ";
                            }
                        }
                        return url;
                    };
                    page.disabledCheckbox = function(inData) {
                        var item = dijit.byId("FeeGrid").getItem(inData);
                        var check = false;
                        return check;
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
                    <sd:DataGrid id="FeeGrid"
                                 getDataUrl=""
                                 rowSelector="0%"
                                 style="width:auto;height:auto;"
                                 rowsPerPage="20"
                                 serverPaging="true"
                                 clientSort="false">
                        <sd:ColumnDataGrid key="STT" get="page.getNo" width="2%" styles="text-align:center;"/>
                        <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="1%" cellStyles="text-align:center;"  setDisabled="page.disabledCheckbox"/>
                        <sd:ColumnDataGrid key="btnUpdate" formatter="page.formatEdit" get="page.getIndex"
                                           width="1%"  headerStyles="text-align:center;"  />
                        <sd:ColumnDataGrid  key="Tên phí" field="feeName"
                                            width="5%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Mô tả" field="description" 
                                            width="5%"  headerStyles="text-align:center;" styles="white-space:pre;"/>
                        <sd:ColumnDataGrid  key="Giá tiền" field="price"
                                            width="5%"  headerStyles="text-align:center;" styles="text-align:right;"/>

                        <sd:ColumnDataGrid  key="Ngày tạo" field="createDate" format="dd/MM/yyyy" type="date"
                                            width="3%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Ngày cập nhật" field="updateDate" format="dd/MM/yyyy" type="date"
                                            width="3%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Loại phí" get="page.getIndex" formatter="page.formatfeeType" cellStyles="text-align:center;"
                                            width="3%"  headerStyles="text-align:center;" />
                        <sd:ColumnDataGrid  key="Trạng Thái" get="page.getIndex" formatter="page.formatIsActive" cellStyles="text-align:center;"
                                            width="3%"  headerStyles="text-align:center;" />
                    </sd:DataGrid>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <sx:ButtonAddCategory onclick="page.insert();"/>
                <sx:ButtonDelete onclick="page.deleteItem()" />
                <sx:ButtonSearch id="btnShowSearchPanel" onclick="page.showSearchPanel();" />

                <sd:TitlePane key="Reset phí nhóm thuốc lá"
                              id="reset" >
                    <sd:TextBox cssStyle="width:10%" trim="true"
                                id="searchForm.fileCode"
                                name="searchForm.fileCode"
                                key=""
                                maxlength="300"
                                />
                    <sx:ButtonAccept onclick=" page.setFeeTlNew();"/>
                </sd:TitlePane>

                <sd:TitlePane key="Chuyển loại hồ sơ"
                              id="changeType" >
                    <sd:TextBox cssStyle="width:10%" trim="true"
                                id="searchForm.fileCodeNew"
                                name="searchForm.fileCodeNew"
                                key=""
                                maxlength="300"
                                />

                    <sd:SelectBox
                        id="searchForm.Type"
                        key="" data="lstFileType" valueField="procedureId" labelField="name"
                        name="searchForm.Type" 
                        cssStyle="display:;width:70%;">
                    </sd:SelectBox> 

                    <sx:ButtonAccept onclick=" page.changeFilesType();"/>
                </sd:TitlePane>

            </td>
        </tr>
    </table>
</sd:TitlePane>

<sd:Dialog  id="dlgFee" height="auto" width="1000px"
            key="dialog.titleAddEdit" showFullscreenButton="false"
            >
    <jsp:include page="feeCreateDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>

<script>
    var insertDialog = true;
    var grid = dijit.byId("FeeGrid");
    var dlgFee = dijit.byId("dlgFee");

    page.setItem = function(item) {

        insertDialog = false;
        dijit.byId("createForm.feeName").setValue(item.feeName);
        dijit.byId("createForm.price").setValue(item.price);
        dijit.byId("createForm.feeId").setValue(item.feeId);
        dijit.byId("createForm.isActive").setValue(item.isActive);
        dijit.byId("createForm.feeType").setValue(item.feeType);
        // dijit.byId("createForm.description").setValue(item.description);
        document.getElementById("createForm.description").innerHTML = escapeHtml_(item.description);
        var spIds = item.procedureId.toString().split(";");
        var spNames = item.procedureName.toString().split(";");
        if (spIds.length == spNames.length)
        {
            var returnData = "";
            for (var i = 0; i < spIds.length; i++)
            {
                var newItem = '<li title="' + spNames[i] + '" class="x-superboxselect-item" id="selected-itemcreateForm.procedureId-' + i + '" >'
                        + spNames[i]
                        + '<a class="x-superboxselect-item-close" tabindex="0" href="javascript:xSuperboxselectOnremove(\'createForm.procedureId\',\'' + i + '\',\'' + spIds[i] + '\')">'
                        + '</a>'
                        + '</li>';
                returnData += newItem;
            }
            document.getElementById("list-selected-itemcreateForm.procedureId").innerHTML = returnData;
            dijit.byId('createForm.procedureId').attr('value', item.procedureId);
        }
    };
    page.showEditPopup = function(row) {
        var item = dijit.byId("FeeGrid").getItem(row);
        page.setItem(item);
        dlgFee.show();
    };


    page.search = function() {
        grid.vtReload("feeAction!onSearch.do?", "searchForm", null, page.afterSearch);
    };

    page.afterSearch = function() {
        dijit.byId("searchForm.flagSavePaging").setValue("0");
    }

    page.reset = function() {
        dijit.byId("searchForm.feeName").setValue("");
        dijit.byId("searchForm.isActive").setValue(-1);
        page.search();
    };
    page.insert = function() {
        page.clearInsertForm();
        dlgFee.show();
    };
    page.clearInsertForm = function() {

        insertDialog = true;
        dijit.byId("createForm.feeName").setValue("");
        document.getElementById("createForm.description").innerHTML = "";
        // dijit.byId("createForm.description").setValue("");
        dijit.byId("createForm.feeId").setValue("");
        dijit.byId("createForm.feeType").setValue(-1);
        dijit.byId("createForm.isActive").setValue(-1);
        dijit.byId("createForm.price").setValue("");
        dijit.byId("createForm.procedureId").attr("disabled", false);
        document.getElementById("list-selected-itemcreateForm.procedureId").innerHTML = "";

    };

    page.deleteItemExecute = function() {
        var content = grid.vtGetCheckedDataForPost("lstItemOnGrid");
        sd.connector.post("feeAction!onDelete.do?" + token.getTokenParamString(), null, null, content, page.returnMessageDelete);
    };

    page.returnMessageDelete = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
        page.search(1);
    };

    page.showSearchPanel = function() {
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style", "display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    };

    page.search();
    page.deleteItem = function() {
        if (!dijit.byId("FeeGrid").vtIsChecked()) {
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
                    } else {
                        msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
                                }
                            };

                            //hieptq update 210515
                            page.setFeeTl = function() {
                                var fileCode = dijit.byId("searchForm.fileCode").getValue();
                                if (fileCode == null || fileCode.trim().length == 0)
                                {
                                    alert("Bạn phải nhập mã hồ sơ muốn reset phí !")
                                } else
                                {
                                    sd.connector.post("feeAction!onResetFeeTl.do?" + token.getTokenParamString() + "&fileCode=" + fileCode, null, null, null, page.returnMessageReset);
                                }
                            }

                            page.setFeeTlNew = function() {
                                msg.confirm('Bạn có chắc chắn hồ sơ này thuộc nhóm thuốc lá ?', '<sd:Property>Cảnh báo</sd:Property>', page.setFeeTl);
                                    }

                                    page.returnMessageReset = function(data) {
                                        var obj = dojo.fromJson(data);
                                        var result = obj.items;
                                        alert(result[1]);
                                    };

                                    //hieptq update doi nhom san pham
                                    page.changeFilesType = function() {
                                        msg.confirm('Bạn có chắc chắn chuyển hồ sơ này 1 sang loại hồ sơ đã chọn ?', '<sd:Property>Cảnh báo</sd:Property>', page.changeFileTypeNew);
                                            }

                                            page.changeFileTypeNew = function() {
                                                var fileCode = dijit.byId("searchForm.fileCodeNew").getValue();
                                                var fileType = dijit.byId("searchForm.Type").getValue();
                                                if (fileCode == null || fileCode.trim().length == 0)
                                                {
                                                    alert("Bạn phải nhập mã hồ sơ muốn chuyển loại !")
                                                } else
                                                {
                                                    if (fileType == -1)
                                                    {
                                                        alert("Bạn phải chọn loại hồ sơ muốn chuyển !")
                                                    } else {
                                                        sd.connector.post("feeAction!onChangeFileType.do?" + token.getTokenParamString() + "&fileCode=" + fileCode + "&fileType=" + fileType, null, null, null, page.returnMessageReset);
                                                    }
                                                }
                                            }

</script>
