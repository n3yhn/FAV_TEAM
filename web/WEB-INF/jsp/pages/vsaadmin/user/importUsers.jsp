<%-- 
    Document   : importUsers
    Created on : Aug 26, 2013, 8:39:39 AM
    Author     : binhnt53
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<form id="staffImportForm" name="staffImportForm">
    <!-- Profile user table -->
    <table id="staffImportTable" style="border-width:thin; border-style:solid; border-color:#B5BACE; border-collapse:collapse;width:100%">
        <tr>
            <td width="50%" style="border-width:thin;  border-style:solid; border-color:#B5BACE;">
                <div align="left">
                    <a href="${contextPath}/share/Template_create.xls" target="self" id="linkFile">Click vào đây để tải về file mẫu</a>
                </div>
                <sx:upload action="uploadiframe!uploadFile.do?id=attachImport" extension="*.xls" id="attachImport" maxSize="20" auto="true"/>
                <sd:TextBox id="attachId" name="attachId" cssStyle="display:none" key=""/>
            </td>
        </tr>
    </table> <!-- End of profile user table -->
    <br/>

    <div style="text-align:center;">
        <sd:Button id="staffImportForm.btnCreate" key="" onclick="page.onImport()">
            <img src="share/images/icons/6.png" height="20" width="20" alt="Thêm mới">
            <span style="font-size:12px"><sd:Property>btn.import</sd:Property></span>
        </sd:Button>
        <sd:Button id="staffImportForm.btnClose" key="" onclick="page.onCloseImportForm()">
            <img src="share/images/icons/13.png" height="20" width="20" alt="Đóng">
            <span style="font-size:12px"><sd:Property>btn.close</sd:Property></span>
        </sd:Button>
    </div>
</form>

<script>

    page.onCloseImportForm = function() {
        clearAttFile("attachImport");
        var dialog = dijit.byId("importUser");
        dialog.hide();
        page.onSearch();
        //dijit.byId("gridId").vtReload("staffAction!onSearchByAcedemy.do", "staffForm", null, null);
    };

    page.onImport = function() {
        var afterCallback = function(data) {
//            console.log(data);
            var obj = JSON.parse(data);
            var status = obj.customInfo;
            if (status.toString().indexOf('success') >= 0) {
                alert("Tất cả dữ liệu nhân viên nhập thành công");
            } else {
                alert("Nhập dữ liệu nhân viên thất bại. Các tài khoản các dòng " + status.toString() + " không thể nhập. Vui lòng kiểm tra lại");
            }
            page.onCloseImportForm();
        };
//        var attachId = document.getElementById("attachId").value;
        var attachId = getListAttachId("attachImport");
        if (attachId == null || attachId <= 0) {
            msg.alert("Chưa chọn file để import");
        } else {
            sd.connector.post("UserAction!importStaffFromExcel.do?attachId=" + attachId + "&" + token.getTokenParamString(), null, null, null, afterCallback);
        }
    };
</script>