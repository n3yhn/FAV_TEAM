<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<div id="token">
    <s:token id="tokenId"/>
</div>


<script type="text/javascript">
    page.checkSelected = function () {
    };
    page.reset = function () {
        dijit.byId("searchForm.fileCode").setValue("");
        dijit.byId("searchForm.fileType").setValue("-1");
        dijit.byId("searchForm.sendDateFrom").setValue("");
        dijit.byId("searchForm.sendDateTo").setValue("");
        dijit.byId("searchForm.repStatus").setValue("-1");
        dijit.byId("searchForm.repName").setValue("-1");
    };
    page.getRow = function (inRow) {
        return inRow;
    };

    page.getIndex = function (index) {
        return index + 1;
    };

    page.getNo = function (index) {
        return dijit.byId("filesGrid").currentRow + index + 1;
    };
    page.formatEdit = function (inData) {
        var item = dijit.byId("filesGrid").getItem(inData - 1);
        var url = "";
        if (item != null) {
            var url = "<div style='text-align:center;cursor:pointer;'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile(" + item.fileId + ");' />";
        }
        url += "</div>";
        return url;
    };
    page.formatRepository = function (inData) {
        var row = inData;
        var item = dijit.byId("filesGrid").getItem(row);
        var url = "";
        if (item != null) {
            if (item.repositoriesId != null && item.repositoriesId > 0)
            {
                url = '<div onClick="page.showRepositoryDlg(' + "'" + item.repositoriesId + "'" + ');" style="text-align:center;cursor:pointer;"><img src="${contextPath}/share/images/record_view.png" width="17px" height="17px" title="Xem thông tin lưu kho"/></div>';
            }
            else {
                url = '<div onClick="page.showRepositorySelect(' + "'" + item.fileId + "'" + ');" style="text-align:center;cursor:pointer;"><img src="${contextPath}/share/images/record_view.png" width="17px" height="17px" title="Lưu kho"/></div>';
            }
        }
        return url;
    };
	
	page.formatStatus = function (inData) {
        var row = inData;
        var item = dijit.byId("filesGrid").getItem(row);
        var url = "";
        if (item != null) {
            if (item.repositoriesId != null && item.repositoriesId > 0)
            {
                url = '<div>Đã lưu trữ</div>';
            }
            else {
                url = '<div>Chưa lưu trữ</div>';
            }
        }
        return url;
    };

    page.formatRepName = function (inData) {

        var row = inData;
        var item = dijit.byId("filesGrid").getItem(row);
        var url = "";
        if (item != null) {
            if (item.repositoriesId != null && item.repositoriesId == 1)
            {
                url = '<div> Phòng dữ liệu tập trung </div>';
            }
            else {
                if (item.repositoriesId != null && item.repositoriesId == 2)
                {
                    url = '<div> Phòng dữ liệu chuyên ngành ATTP </div>';
                }
                else {
                    url = '<div> Chưa Lưu Trữ  </div>';
                }
            }
        }
        return url;
    };


// enter key
    page.searchDefault = function (evt) {
        var dk = dojo.keys;
        switch (evt.keyCode) {
            case dk.ENTER:
                page.search();
                break;
        }
    }

    dojo.connect(dojo.byId("searchForm"), "onkeypress", page.searchDefault);


</script>



<div id="searchDiv" >
    <sd:TitlePane key="search.searchCondition" id="categoryTitle">
        <form id="searchForm" name="searchForm">
            <s:hidden id="deptId" name="searchForm.deptId"/>
            <table width="100%;" cellpadding="0px" cellspacing="5px">
                <tr>
                    <td width="20%"></td>
                    <td width="30%"></td>
                    <td width="20%"></td>
                    <td width="30%"></td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Mã hồ sơ/ Số ISO"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:99%"
                                    id="searchForm.fileCode"
                                    key=""
                                    name="searchForm.fileCode" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Tên loại hồ sơ"/>
                    </td>
                    <td>
                        <sd:SelectBox cssStyle="width:100%"
                                      id="searchForm.fileType"
                                      key="" data="lstFileType" valueField="procedureId" labelField="name"
                                      name="searchForm.fileType" >
                        </sd:SelectBox>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Lưu trữ từ ngày"/>
                    </td>
                    <td>
                        <sd:DatePicker cssStyle="width:39%"
                                       id="searchForm.sendDateFrom"
                                       key="" format="dd/MM/yyyy"
                                       name="searchForm.repDateFrom"/>
                        <sd:Label key="Đến ngày "/>
                        <sd:DatePicker cssStyle="width:39%; float: right;" 
                                       id="searchForm.sendDateTo"
                                       key="" format="dd/MM/yyyy"
                                       name="searchForm.repDateTo"/>
                    </td>
                    <td align="right"><sd:Label key="Trạng thái"/></td>
                    <td>
                        <sd:SelectBox  key="" id="searchForm.repStatus" name="searchForm.repStatus" cssStyle="width:100%">
                            <sd:Option value="-1" selected='true' >-- Chọn --</sd:Option>
                            <sd:Option value="1"> Đã lưu trữ</sd:Option>
                            <sd:Option value="2"> Chưa lưu trữ</sd:Option>
                        </sd:SelectBox>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Kho lưu trữ"/>
                    </td>
                    <td>
                        <sd:SelectBox onchange="page.checkSelected()"  cssStyle="width:99%"
                                      id="searchForm.repName"
                                      key="" data="lstRepository" valueField="repId" labelField="repName"
                                      name="searchForm.repName" >
                        </sd:SelectBox>
                    </td>
                    <td align="right">

                    </td>
                    <td>

                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Tên doanh nghiệp"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.businessName"
                                    key=""
                                    name="searchForm.businessName" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Tên sản phẩm"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.productName"
                                    key=""
                                    name="searchForm.productName" maxlength="250"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <sd:Label key="Địa chỉ"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.businessAddress"
                                    key=""
                                    name="searchForm.businessAddress" maxlength="250"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Người thẩm xét"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
                                    id="searchForm.Staff"
                                    key=""
                                    name="searchForm.Staff" maxlength="250"/>
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

    <sd:TitlePane key="Danh sách hồ sơ"
                  id="businessList" >
        <table width="100%">
            <tr>
                <td>
                    <div id="fileGridDiv" style="width:100%;">
                        <sd:DataGrid id="filesGrid"
                                     getDataUrl=""
                                     rowSelector="0%"
                                     style="width:auto;height:auto"
                                     rowsPerPage="20"
                                     serverPaging="true"
                                     clientSort="false">
                            <sd:ColumnDataGrid key="category.No" get="page.getNo" width="3%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="Xem" headerStyles="text-align:center;" width="3%" cellStyles="text-align:center;"
                                               formatter="page.formatEdit" get="page.getIndex"/>    
                            <sd:ColumnDataGrid  key="Lưu kho" formatter="page.formatRepository" get="page.getRow"
                                                width="3%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>											   
                            <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode" width="7%" headerStyles="text-align:center;" cellStyles="text-align:center;"/>
                            <sd:ColumnDataGrid  key="Loại hồ sơ" field="fileTypeName"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên tổ chức, cá nhân" field="businessName"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên sản phẩm" field="productName"  cellStyles="text-align:left;"
                                                width="15%"  headerStyles="text-align:center;" />  
                            <sd:ColumnDataGrid  key="Cán bộ xử lý chính" field="nameStaffProcess"  cellStyles="text-align:center;"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày lưu trữ" field="repDate" format="dd/MM/yyyy" type="date"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />                                                        
                            <sd:ColumnDataGrid  key="Trạng thái lưu trữ" formatter="page.formatStatus" get="page.getRow"
                                                width="5%"  headerStyles="text-align:center;" cellStyles="text-align:center;"/>							

                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>

<sd:Dialog  id="RepositoryDlg" height="auto" width="500px"
            key="Lưu trữ hồ sơ" showFullscreenButton="false"
            >
    <jsp:include page="../../files/repository/repositoryDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>

<sd:Dialog  id="RepositorySelectDlg" height="auto" width="500px"
            key="Lưu trữ hồ sơ" showFullscreenButton="false"
            >
    <form id="receivedForm" name="receivedForm">
        <sx:ResultMessage id="resultMessage" />
        <table width="100%" class="viewTable" cellpadding="0px" cellspacing="5px">
            <tr>
                <td width="30%" style="text-align: right"><sd:Label key="Chọn kho lưu trữ:" required="true"/></td>
            <input type="hidden" id="createForm.fileId" name="createForm.fileId" />
            <td width="70%">
                <sd:SelectBox  cssStyle="width:100%"
                               id="createForm.repository"
                               key="" data="lstRepository" valueField="repId" labelField="repName"
                               name="createForm.repository" >
                </sd:SelectBox>
            </td>
            </tr>
            <tr>
                <td width="25%"></td> 
                <td width="75%"></td>
            </tr>


            <tr>
                <td colspan="2" style="text-align: center">
                    <sx:ButtonSave  onclick="page.Save();"/>
                    <sx:ButtonClose onclick="page.Close();"/>  
                </td>
            </tr>
        </table>
    </form>
</sd:Dialog>



<div id="createDiv"></div>


<script language="javascript">
    var currentUserId = '${fn:escapeXml(userId)}';
    backPage = function () {
        doGoToMenu(g_latestClickedMenu);
    };
    page.search = function () {
        dijit.byId("filesGrid").vtReload("filesAction!onSearchRepository.do?searchForm.repCreator=" + currentUserId + "&searchForm.searchType=110", "searchForm");
    };
    page.showViewFile = function (fileId) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId=" + fileId + "&createForm.viewType=1", "createDiv", null, null, afterLoadForm);
    };
    afterLoadForm = function (data) {
        document.getElementById("searchDiv").style.display = "none";
        document.getElementById("createDiv").style.display = "";
    };
    page.showRepositorySelect = function (fileId)
    {
        var item = document.getElementById("createForm.fileId");
        item.value = fileId;
        dijit.byId("RepositorySelectDlg").show();
    };

    page.Save = function ()
    {
        var rep_Id = dijit.byId("createForm.repository").attr("value");
        var fileId = document.getElementById("createForm.fileId").value;
        if (rep_Id > 0) {
            sd.connector.post("filesAction!onUpdateRepository.do?createForm.repositoriesId=" + rep_Id + "&createForm.fileId=" + fileId, null, "createForm", null, afterUpdateRepository);
        }
        else {
            alert("Bạn phải chọn kho lưu trữ trước khi Lưu");
        }
    };

    page.showRepositoryDlg = function (repId) {
        sd.connector.post("filesAction!getRepositoryRecordsDetails.do?createForm.repositoriesId=" + repId, null, null, null, afterLoadViewRepository);
    };
    afterUpdateRepository = function () {
        alert("Cập nhật kho lưu trữ thành công !")
        dijit.byId("RepositorySelectDlg").hide();
        page.search();
    };

    afterLoadViewRepository = function (data) {
        var returnData = dojo.fromJson(data);
        document.getElementById("repository.repStatus").innerHTML = escapeHtml_(returnStatusRepository(returnData.items[0].repId));
        document.getElementById("repository.repName").innerHTML = escapeHtml_(returnData.items[0].repName);
        document.getElementById("repository.repAddress").innerHTML = escapeHtml_(returnData.items[0].repAddress);
        dijit.byId("RepositoryDlg").show();
    };
    page.Close = function ()
    {
        dijit.byId("RepositorySelectDlg").hide();
    };

    returnStatusRepository = function (status) {
        var strStatus = "";
        if (status != null)
        {
            strStatus = "Đã lưu trữ hồ sơ";
        }
        else {
            strStatus = "Chưa lưu trữ";
        }
        return strStatus;
    };
    page.search();
</script>