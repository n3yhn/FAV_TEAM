<%-- 
    Document   : searchStaff
    Created on : Apr 1, 2011, 2:56:16 PM
    Author     : gpdn_havm2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags/" %>

<%@include file="../common/commonJavascript.jsp" %>
<script>

    page.getRow = function(inRow) {
        return dijit.byId("gridId").currentRow + inRow + 1;
    }

    page.updateStaffFormat = function(inrow) {
        var item = dijit.byId("gridId").getItem(inrow);

        if (item != null) {
            var url = "<div><img src='share/images/icons/edit.png' title='Cập nhật thông tin' onClick='page.gotoEdit(" + inrow + ")' height='20' width='20' /></div>";
            return url;
        }
    }

</script>
<div id ="searchAllDiv">
    <sd:TitlePane key="staff.title" id="tltpnStaff">
        <form id="searchLogForm" name="searchLogForm">
            <table width="100%">
                <tr>
                    <td width="20%"></td>
                    <td width="30%"></td>
                    <td width="20%"></td>
                    <td width="30%"></td>
                </tr>
                <tr>
                    <td align="right"><sd:Label key="Người xử lý:"/></td>
                    <td>
                        <sd:TextBox maxlength="50" id="searchLogForm.userName" name="searchLogForm.userName" key="" cssStyle="width:80%"/>

                    </td>
                    <td align="right"><sd:Label key="Chức năng:"/></td>
                    <td>
                        <sd:TextBox maxlength="50" id="searchLogForm.action" name="searchLogForm.action" key="" cssStyle="width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td align="right"><sd:Label key="Tên vai trò:"/></td>
                    <td>                        
                        <sd:TextBox maxlength="50" id="searchLogForm.objectName" name="searchLogForm.objectName" key="" cssStyle="width:80%"/>                        
                    </td>
                    <td align="right"><sd:Label key="Tên chức năng:"/></td>
                    <td>     
                        <sd:TextBox maxlength="50" id="searchLogForm.appName" name="searchLogForm.appName" key="" cssStyle="width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td align="right"><sd:Label key="Tên phòng ban:"/></td>
                    <td>                      
                        <sd:TreePicker id="deptNamePicker" getChildrenNodeUrl="departmentAction!getDeptChildrenDataByNode.do"
                               getTopNodeUrl="departmentAction!getDeptData.do"  key="" rootLabel="root" cssStyle="width:80%" />
                <sd:TextBox cssStyle="display:none;" id="searchLogForm.deptName" name="searchLogForm.deptName" key=""/> 
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td align="right"><sd:Label key="Ngày xử lý         Từ ngày:"/></td>
                    <td>
                        <sd:DatePicker id="searchLogForm.eventDateFrom" format="dd/MM/yyyy"  name="searchLogForm.eventDateFrom" key="" cssStyle="width:80%"/>
                    </td>
                    <td align="right"><sd:Label key="Đến ngày:"/></td>
                    <td>
                        <sd:DatePicker id="searchLogForm.eventDateTo" format="dd/MM/yyyy"  name="searchLogForm.eventDateTo" key="" cssStyle="width:80%"/>
                    </td>
                </tr>
            </table>
            <div style="text-align:center;">
                <sd:Button id="staffForm.btnSearch" key="" onclick="page.onSearch()">
                    <img src="share/images/icons/a1.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
                </sd:Button>
            </div>
        </form>
    </sd:TitlePane>

    <sd:TitlePane key="staffForm.searchList" id="tltpnStaffSearchList">
        <div id="gridDiv" style="width:100%;">
            <sd:DataGrid clientSort="true" getDataUrl="" id="gridId" rowsPerPage="10" style="width: 100%; height: 100%;" container="gridDiv" rowSelector="0px">
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" cellStyles="text-align:center;" editable="false" key="customer.No" get="page.getRow" styles="text-align:center;" width="5%;" />
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="applicationsForm.module" field="action" width="20%" />
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="usersFormOnDialog.userName" field="userName" width="10%" />
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="usersFormOnDialog.fullName" field="actor" width="15%" />
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="report.ip" field="ip" width="20%" />
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" cellStyles="text-align:center;" editable="false" key="report.eventDate" field="eventDate" type="date" format="dd/MM/yyyy hh:hm:s aaa" width="15%" />
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="positionAddEditForm.description" field="description" width="15%" />
            </sd:DataGrid>
        </div>

    </sd:TitlePane>
</div>
<script type="text/javascript" src="debug.js"></script>
<script type="text/javascript">

    page.onSearch = function() {
        dijit.byId("searchLogForm.deptName").setValue(dijit.byId("searchLogForm.deptName").getValue(deptNamePicker));
        dijit.byId("gridId").vtReload("home!onSearchLog.do", "searchLogForm", null, null);
    }
    dijit.byId("deptNamePicker").onPickData = function(item) {
        try {
            if (item.id) {
                dijit.byId("searchLogForm.deptName").setValue(dijit.byId("deptNamePicker").getValue());
            } else {
                sd._("searchLogForm.deptName").setValue("");
            }
        } catch (err) {
            alert(err.message);
        }
    }
     page.onSearch();
</script>


