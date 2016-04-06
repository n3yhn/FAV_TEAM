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
    page.checkSelected = function() {
    };

    page.getIndex = function(index) {
        return index + 1;
    };

    page.reset = function() {
        dijit.byId("searchForm.fileCode").setValue("");
        dijit.byId("searchForm.fileType").setValue("-1");
        dijit.byId("searchForm.sendDateFrom").setValue("");
        dijit.byId("searchForm.sendDateTo").setValue("");
        dijit.byId("searchForm.repStatus").setValue("-1");
        dijit.byId("searchForm.repName").setValue("-1");
    };
    page.getRow = function(inRow) {
        return inRow;
    };
    page.getNo = function(index) {
        return dijit.byId("filesGrid").currentRow + index + 1;
    };
    page.formatEdit = function(inData) {
        var row = inData - 1;
        var item = dijit.byId("filesGrid").getItem(row);
        var url = "";
        if (item != null) {
            var url = "<div style='text-align:center;cursor:pointer;'><img src='share/images/edit.png' width='17px' height='17px' title='Sửa kho lưu trữ' onClick='page.editRep(" + row + ");' /><img src='share/images/icons/deleteStand.png' width='17px' height='17px' title='Xóa kho lưu trữ' onClick='page.deleteRep(" + item.repId + ");' /></div>";
        }
        return url;

    };
    page.formatRepository = function(inData) {
        var row = inData;
        var item = dijit.byId("filesGrid").getItem(row);
        var url = "";
        if (item != null) {
            if (item.repositoriesId != null && item.repositoriesId > 0)
            {
                url = '<div onClick="page.showRepositoryDlg(' + "'" + item.repositoriesId + "'" + ');" style="text-align:left;cursor:pointer;"><img src="${contextPath}/share/images/edit.png" width="17px" height="17px" \n\
                                        title="Xem kho lưu trữ" \n\
                                         /> Đã lưu trữ</div>';
            }
            else {
                url = '<div  onClick="page.showRepositorySelect(' + "'" + item.fileId + "'" + ');" style="text-align:left;cursor:pointer;"><img src="${contextPath}/share/images/edit.png" width="17px" height="17px" \n\
                                        title="Chọn kho lưu trữ" \n\
                                        /> Chưa lưu trữ</div>';
            }



        }
        return url;
    };

    page.formatRepName = function(inData) {

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
                        <sd:Label key="Kho lưu trữ"/>
                    </td>
                    <td>
                        <sd:SelectBox onchange="page.checkSelected()"  cssStyle="width:99%"
                                      id="repForm.repName"
                                      key="" data="lstRepository" valueField="repId" labelField="repName"
                                      name="repForm.repName" >
                        </sd:SelectBox>
                    </td>
                    <td align="right">
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

    <sd:TitlePane key="Danh sách Kho lưu trữ"
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
                            <sd:ColumnDataGrid editable="true" key="Xóa" headerStyles="text-align:center;" width="3%" cellStyles="text-align:center;"
                                               formatter="page.formatEdit" get="page.getIndex"/>                        
                            <sd:ColumnDataGrid  key="Mã kho" field="repId" width="7%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên kho" field="repName"
                                                width="25%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Vị trí lưu trữ" field="repAddress"
                                                width="25%"  headerStyles="text-align:center;" />   


                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:Button key="" onclick="page.insert();">
                        <img src="share/images/icons/save.png" height="14" width="18" alt="Ghi lại">
                        <span style="font-size:12px">Thêm mới kho lưu trữ</span>
                    </sd:Button>            
                </td>
            </tr>
        </table>
    </sd:TitlePane>



</div>






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
<sd:Dialog  id="RepositoryDlg" height="auto" width="500px"
            key="Thêm kho lưu trữ" showFullscreenButton="false"
            >
    <jsp:include page="addrepositoriesDlg.jsp" flush="false"></jsp:include>
</sd:Dialog>


<div id="createDiv"></div>


<script language="javascript">
    var currentUserId = '${fn:escapeXml(userId)}';
    page.search = function() {
        //alert(currentUserId);
        dijit.byId("filesGrid").vtReload("filesAction!onSearchLookupRepositories.do?searchForm.repCreator=" + currentUserId + "&searchForm.searchType=2", "searchForm");
    };
    page.showRepositorySelect = function(fileId)
    {
        var item = document.getElementById("createForm.fileId");
        item.value = fileId;
        dijit.byId("RepositorySelectDlg").show();
    };

    page.Save = function()
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

    page.insert = function() {
        check = true;
        dijit.byId("RepositoryDlg").show();
    };
    
    page.showRepositoryDlg = function(repId) {
        sd.connector.post("filesAction!getRepositoryRecordsDetails.do?createForm.repositoriesId=" + repId, null, null, null, afterLoadViewRepository);
    };
     
    afterUpdateRepository = function() {
        alert("Cập nhật kho lưu trữ thành công !")
        dijit.byId("RepositorySelectDlg").hide();
        page.search();
    };

    afterLoadViewRepository = function(data) {
        var returnData = dojo.fromJson(data);
        document.getElementById("repository.repStatus").innerHTML = escapeHtml_(returnStatusRepository(returnData.items[0].repId));
        document.getElementById("repository.repName").innerHTML = escapeHtml_(returnData.items[0].repName);
        document.getElementById("repository.repAddress").innerHTML = escapeHtml_(returnData.items[0].repAddress);
        dijit.byId("RepositoryDlg").show();
    };
    page.Close = function()
    {
        dijit.byId("RepositorySelectDlg").hide();
    };

    returnStatusRepository = function(status) {
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
    
    page.deleteRep = function(repId) {
        msg.confirm("Bạn có chắc muốn xóa ?", "Xóa kho lưu trữ", function() {
            sd.connector.post("filesAction!onDeleteRepositories.do?repoForm.repId=" + repId, null, null, null, page.afterDelete);
        });
    };

    page.editRep = function(row) {
        check = true;
        var item = dijit.byId("filesGrid").getItem(row);
        page.setItem(item);
        dijit.byId("RepositoryDlg").show();
    };
    
    page.setItem = function(item) {
        dijit.byId("repoForm.repId").setValue(item.repId);
        dijit.byId("repoForm.repName").setValue(item.repName);
        dijit.byId("repoForm.repAddress").setValue(item.repAddress);
        dijit.byId("repoForm.isActive").setValue(item.isActive);
    };
    
    page.afterDelete = function(data) {

        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "1") {

            page.search();
        } else {
            alert("Không xóa được kho đã lưu trữ hồ sơ !!!")
            page.search();
        }
    };
    page.search();
</script>