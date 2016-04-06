<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--
<jsp:include page="../util/util_js.jsp"/>
<jsp:include page="../common/commonJavascript.jsp"/>
--%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<style type="text/css">
    .box:hover{
        color: #ffff00;
        cursor: pointer;
    }
</style>
<script>
    page.getNo = function(index) {
        return dijit.byId("filesGrid").currentRow + index + 1;
    };

    page.getRow = function(inRow){
        return inRow;
    };
                  
    page.getIndex = function(index) {
        return index + 1;
    };
                  
    page.formatView = function (inData) {
        var item= dijit.byId("filesGrid").getItem(inData -1);
        var url="";
        if (item!=null) {
            var url = "<div style='text-align:center;cursor:pointer;'><img src='share/images/icons/view.png' width='17px' height='17px' title='Xem hồ sơ' onClick='page.showViewFile("+item.fileId+");' /></div>";
        }
        return url;
    };
    
    page.formatEdit = function (inData) {
        var url = "<div class='box' onclick='page.viewFile("+inData+");' />"+inData+"</div>";
        
        return url;
    };

    page.formatStatus=function(inData){
        return 'Mới nộp';
    };
    page.formatDate=function(date){
        if(date!=null) return;
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

<div id="token">
    <s:token id="tokenId"/>
</div>
<div id="searchDiv">

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
                        <sd:Label key="Mã hồ sơ"/>
                    </td>
                    <td>
                        <sd:TextBox cssStyle="width:100%"
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
                        <sd:Label key="Gửi Từ ngày"/>
                    </td>
                    <td>
                        <sd:DatePicker cssStyle="width:100%"
                                       id="searchForm.sendDateFrom"
                                       key="" format="dd/MM/yyyy"
                                       name="searchForm.sendDateFrom"/>
                    </td>
                    <td align="right">
                        <sd:Label key="Đến ngày"/>
                    </td>
                    <td>
                        <sd:DatePicker cssStyle="width:100%"
                                       id="searchForm.sendDateTo"
                                       key="" format="dd/MM/yyyy"
                                       name="searchForm.sendDateTo"/>
                    </td>
                </tr>
                <tr style="text-align: center">
                    <td colspan="4">
                        <sx:ButtonSearch onclick="page.search();" />
                        <sd:Button key="" onclick="page.reset();" > 
                            <img src="share/images/icons/reset.png" height="14" width="18">
                            <span style="font-size:12px">Hủy<%--<sd:Property>btnCancel</sd:Property> --%></span>
                        </sd:Button>
                    </td>
                </tr>

            </table>
        </form>
    </sd:TitlePane>

    <sd:TitlePane key="Danh sách hồ sơ"
                  id="businessList" >
        <sx:ResultMessage id="resultDeleteMessage" />
        <table width="100%">
            <tr>
                <td>
                    <div style="width:100%;">
                        <sd:DataGrid id="filesGrid"
                                     getDataUrl=""
                                     rowSelector="0%"
                                     style="width:auto;height:auto"
                                     rowsPerPage="20"
                                     serverPaging="true"
                                     clientSort="false"
                                     >
                            <sd:ColumnDataGrid key="category.No" get="page.getNo" width="5%"  styles="text-align:center;" />
                            <sd:ColumnDataGrid editable="true" key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="5%" cellStyles="text-align:center;" />                        
                            <sd:ColumnDataGrid editable="true" key="Xem" headerStyles="text-align:center;" width="5%" cellStyles="text-align:center;"
                                               formatter="page.formatView" get="page.getIndex"/>                        
                            <sd:ColumnDataGrid  key="Mã hồ sơ" field="fileCode" width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Loại hồ sơ" field="fileTypeName"
                                                width="15%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Trạng thái hồ sơ" field="displayStatus" cellStyles="text-align:center;"
                                                width="10%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Tên tổ chức, cá nhân" field="businessName"
                                                width="25%"  headerStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày nộp" field="sendDate" format="dd/MM/yyyy" type="date"
                                                width="15%"  headerStyles="text-align:center;" cellStyles="text-align:center;" />
                            <sd:ColumnDataGrid  key="Ngày nộp" field="fileId" cellStyles="display:none;"
                                                width="0%"  headerStyles="text-align:center;display:none" />
                        </sd:DataGrid>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:Button key="" onclick="page.propose();" > 
                        <img src="share/images/icons/9.png" height="14" width="18">
                        <span style="font-size:12px">Đề xuất</span>
                    </sd:Button>
                </td>
            </tr>
        </table>
    </sd:TitlePane>
</div>

<div id="viewDiv"></div>
<script type="text/javascript">
    
    backPage = function(){
        //document.getElementById("selectFileDiv").style.display="";
        //document.getElementById("createFileDiv").style.display="none";
        //doGoToMenu(g_latestClickedMenu);
        document.getElementById("searchDiv").style.display="";
        document.getElementById("viewDiv").style.display="none";
    } 
    
    afterLoadForm = function (data){
        document.getElementById("searchDiv").style.display="none";
        document.getElementById("createDiv").style.display="";
    }

    page.showViewFile = function(fileId){
        document.getElementById("searchDiv").style.display="none";
        document.getElementById("viewDiv").style.display="";
        sd.connector.post("filesAction!loadFileView.do?createForm.fileId="+fileId,"viewDiv",null,null,afterLoadForm);
    }

    page.search = function() {
        dijit.byId("filesGrid").vtReload('filesAction!onProposeEvaluation.do?', "searchForm");
    }

    page.reset=function(){
        dijit.byId('searchForm.fileCode').attr('value','');
        dijit.byId('searchForm.fileType').attr('value','-1');
        dijit.byId('searchForm.sendDateFrom').attr('value',null);
        dijit.byId('searchForm.sendDateTo').attr('value',null);    
    }
    
    page.propose=function(){
        if (!dijit.byId("filesGrid").vtIsChecked()){
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
        }
        else{
            msg.confirm('<sd:Property>confirm.propose</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.proposeItem);
        }
    }
    
    page.setItem = function(item){
        dijit.byId("createForm.businessId").setValue(item.businessId);
        dijit.byId("createForm.userFullname").setValue(item.userFullname);
        dijit.byId("createForm.userTelephone").setValue(item.userTelephone);
        dijit.byId("createForm.userMobile").setValue(item.userMobile);
        dijit.byId("createForm.userEmail").setValue(item.userEmail);
        dijit.byId("createForm.isActive").setValue(item.isActive);
        dijit.byId("createForm.businessTypeName").setValue(item.businessTypeName);
        dijit.byId("createForm.businessName").setValue(item.businessName);
        dijit.byId("createForm.businessNameEng").setValue(item.businessNameEng);
        dijit.byId("createForm.businessNameAlias").setValue(item.businessNameAlias);
        dijit.byId("createForm.businessTaxCode").setValue(item.businessTaxCode);
        dijit.byId("createForm.businessLicense").setValue(item.businessLicense);
        dijit.byId("createForm.businessAddress").setValue(item.businessAddress);
        dijit.byId("createForm.businessProvince").setValue(item.businessProvince);
        dijit.byId("createForm.businessTelephone").setValue(item.businessTelephone);
        dijit.byId("createForm.businessFax").setValue(item.businessFax);
        dijit.byId("createForm.businessWebsite").setValue(item.businessWebsite);
        dijit.byId("createForm.businessEstablishYear").setValue(item.businessEstablishYear);
        dijit.byId("createForm.businessLawRep").setValue(item.businessLawRep);
        dijit.byId("createForm.description").setValue(item.description);
    }
    
    page.clearInsertForm = function(){
        dijit.byId("createForm.businessId").setValue("");
        dijit.byId("createForm.userFullname").setValue("");
        dijit.byId("createForm.userTelephone").setValue("");
        dijit.byId("createForm.userMobile").setValue("");
        dijit.byId("createForm.userEmail").setValue("");
        dijit.byId("createForm.isActive").setValue(1);
        dijit.byId("createForm.businessTypeName").setValue("");
        dijit.byId("createForm.businessName").setValue("");
        dijit.byId("createForm.businessNameEng").setValue("");
        dijit.byId("createForm.businessNameAlias").setValue("");
        dijit.byId("createForm.businessTaxCode").setValue("");
        dijit.byId("createForm.businessLicense").setValue("");
        dijit.byId("createForm.businessAddress").setValue("");
        dijit.byId("createForm.businessProvince").setValue(0);
        dijit.byId("createForm.businessTelephone").setValue("");
        dijit.byId("createForm.businessFax").setValue("");
        dijit.byId("createForm.businessWebsite").setValue("");
        dijit.byId("createForm.businessEstablishYear").setValue("");
        dijit.byId("createForm.businessLawRep").setValue("");
        dijit.byId("createForm.description").setValue("");
    }

    page.insert = function() {
        page.clearInsertForm();
        dijit.byId("createDlg").show();
    }

    page.showEditPopup = function(row) {
        var item = dijit.byId("filesGrid").getItem(row);
        page.setItem(item);
        dijit.byId("createDlg").show();
    }

    page.deleteItem = function(){
        if (!dijit.byId("filesGrid").vtIsChecked()){
            msg.alert('<sd:Property>alert.select</sd:Property>', '<sd:Property>confirm.title</sd:Property>');
        }
        else{
            msg.confirm('<sd:Property>confirm.delete</sd:Property>', '<sd:Property>confirm.title1</sd:Property>', page.deleteItemExecute);
        }
    }

    page.deleteItemExecute=function(){
        var content = dijit.byId("filesGrid").vtGetCheckedDataForPost("lstItemOnGrid");
        sd.connector.post("businessAction!onDelete.do?"+token.getTokenParamString(), null, null, content, page.returnMessageDelete);
    }

    page.proposeItem=function(){
        var content = dijit.byId("filesGrid").vtGetCheckedDataForPost("lstItemOnGrid");
        sd.connector.post("filesAction!onPropose.do?"+token.getTokenParamString(), null, null, content, page.returnMessageDelete);
    }
    page.returnMessageDelete = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultDeleteMessage", result[0], result[1], 5000);
        page.search();
    }
    
    page.showSearchPanel = function(){
        var panel = document.getElementById("searchDiv");
        panel.setAttribute("style","display:;");
        dijit.byId("btnShowSearchPanel").setAttribute("style", "display:none;");
    } 
    
    page.search();
    
    //dijit.byId("categoryTitle").attr("open", false);

</script>
