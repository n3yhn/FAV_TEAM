<%-- 
    Document   : importUsers
    Created on : Aug 26, 2013, 8:39:39 AM
    Author     : binhnt53
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags/" %>

<form id="staffImportForm" name="staffImportForm">
    <!-- Profile user table -->
    <table id="staffImportTable" style="border-width:thin; border-style:solid; border-color:#B5BACE; border-collapse:collapse;width:100%">
        <tr>
            <td align="center" width="50%" style="border-width:thin;  border-style:solid; border-color:#B5BACE;">
                <div align="center">
                    Click vào đây để tải về file mẫu: <a href="${contextPath}/share/BM_KhaiBao_2007_4Star.xlsx" target="self" id="linkFile">Excel 2007</a> | <a href="${contextPath}/share/BM_KhaiBao_2010_4Star.xlsx" target="self" id="linkFile">Excel 2010</a>
                </div>
                <br/>
                <tags:uploadImp action="uploadiframe!uploadFileImp.do?id=attachImport" extension="*.xlsx" id="attachImport" maxSize="20" auto="true"/>
                <br/>
                <div align="center" style="color: red">
                    Khi khai báo biểu mẫu Excel Doanh nghiệp chú ý 1 số nội dung sau: Một, sử dụng đúng phiên bản Excel đang sử dụng. Hai, Các thông tin hệ thống để chọn Doanh nghiệp không thể tự đánh thêm thông tin vào. Ba, Tên biểu mẫu Excel để tiếng việt không dấu. Bốn, các thông tin chỉ cho phép chọn nếu chưa tồn tại thông tin mong muốn có thể chọn tạm 1 thông tin có sẵn rồi cập nhật hồ sơ trên hệ thống sau đó cập nhật lại thông tin chưa chính xác. Năm, Doanh nghiệp đặc biệt chú ý không thực hiện sao chép đè vào ô Excel của biểu mẫu từ ô khác hay ô của tài liệu Excel khác, chỉ nên sao chép đoạn văn bản vào ko sao chép cả ô có thể làm hỏng biểu mẫu của hệ thống dẫn tới không tải được hồ sơ
                </div>
                <sd:TextBox id="attachId" name="attachId" cssStyle="display:none" key=""/>
                <sd:TextBox key="" id="createForm.path" name="createForm.path" cssStyle="display:none" />
                <sd:TextBox key="" id="createForm.statusExcel" name="createForm.statusExcel" cssStyle="display:none" />
            </td>
        </tr>
    </table> <!-- End of profile user table -->
    <br/>

    <div id="buttonImport1" style="text-align:center;">
        <sd:Button id="staffImportForm.btnCreate" key="" onclick="page.import()">
            <img src="share/images/icons/6.png" height="20" width="20" alt="Nhập thông tin">
            <span style="font-size:12px"><sd:Property>btn.import</sd:Property></span>
        </sd:Button>
    </div>
</form>

<script>

    page.import = function() {
        document.getElementById('createFileDiv').Value = '';
        var workingAttachId = getListAttachId("attachImport");
        var fileType = dijit.byId("createForm.fileType").getValue();
        if (workingAttachId == null || workingAttachId <= 0) {
            msg.alert("Chưa chọn file để import");
        }
        else
        {
            sd.connector.post("filesAction!importFileFromExcel.do?attachId=" + workingAttachId + "&createForm.fileType=" + fileType, "createFileDiv", null, null, page.afterLoadExcel);
        }

    };

    page.afterLoadExcel = function(data)
    {
        page.loadInitMainlyTargets();
        page.loadInitProductTargets();
        page.loadInitQualityControl();
    };

    page.afterLoad = function(data) {
        msg.alert(dijit.byId("createForm.statusExcel").getValue());
    };


    page.afterLoadMainlyTarget = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result1 = obj.customInfo[0];
        var statusExcel = dijit.byId("createForm.statusExcel").getValue();
        if (result != null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                createNewMainlyTargetRow('', item.targetName, item.unitId, item.publishLevel, item.meetLevel);
            }
        }
        var statusExcelNew = statusExcel + ", " + result1;
        document.getElementById('createForm.statusExcel').value = statusExcelNew;
    };

    page.loadInitMainlyTargets = function() {
        var path = dijit.byId("createForm.path").getValue();

        sd.connector.post("filesAction!loadMainlyTargetExcel.do?path=" + path, null, null, null, page.afterLoadMainlyTarget);
    };

    page.afterProductTarget = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result1 = obj.customInfo[0];
        var statusExcel = dijit.byId("createForm.statusExcel").getValue();
        if (result != null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                createNewRowOfTarget(item.productTargetId, item.targetName, item.unitId, item.maxLevel, item.targetType, "")
            }
        }
        var statusExcelNew = statusExcel + ", " + result1;
        document.getElementById('createForm.statusExcel').value = statusExcelNew;
    };

    page.loadInitProductTargets = function() {
        var path = dijit.byId("createForm.path").getValue();
        sd.connector.post("filesAction!loadProductTargetExcel.do?path=" + path, null, null, null, page.afterProductTarget);

    };


    page.afterLoadQualityControl = function(data) {

        var obj = dojo.fromJson(data);
        var result = obj.items;
        var result1 = obj.customInfo[0];
        var statusExcel = dijit.byId("createForm.statusExcel").getValue();
        if (result != null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                createNewRowOfQualityControl(item.qualityControlPlanId, item.productProcessDetail, item.controlTarget, item.technicalRegulation, item.patternFrequence, item.testDevice, item.testMethod, item.noteForm, item.note, 'qualityControlTbl');
            }
        }
        var statusExcelNew = statusExcel + ", " + result1;
        document.getElementById('createForm.statusExcel').value = statusExcelNew;
        page.afterLoad();
    };

    page.loadInitQualityControl = function() {
        var path = dijit.byId("createForm.path").getValue();
        sd.connector.post("filesAction!loadQualityControlsExcel.do?path=" + path, null, null, null, page.afterLoadQualityControl);
    };
    
        page.checkExcel1 = function() {
        var check = '${checkEdit}';
        if (check == 1)
        {
            document.getElementById("buttonImport1").style.display = "none";
        }
        else
        {
             document.getElementById("buttonImport1").style.display = "";
        }
    };
    page.checkExcel1();

//    uploadNewRow = function(name, attachId, id) {
//        /* Validate */
//        /*
//        var isValid = false;
//        var file = document.getElementById(uploadId);
//        var files = file.files;
//        if (!files) {//for IE
//            if (file.size > 0) {
//                var fileName = file.value;
//                if (!page.isValidExt(fileName))
//                    return false;
//            }
//        } else {
//            if (document.getElementById(uploadId).files.length > 0) {
//                var fileName = document.getElementById(uploadId).files[0].name;
//                //alert(fileName);
//                if (!page.isValidExt(fileName))
//                    return false;
//                var size = document.getElementById(uploadId).files[0].size;
//                if (!page.isValidSize(size))
//                    return false;
//            }
//        }
//        document.getElementById(loadingId).style.display = '';
//        */
//        /* End validate */
//    
//        if(id == "upload.xsl"){
//            clearAttFile(id);
//        }
//        var tbId = 'browse' + id + 'tb';
//        var table = document.getElementById(tbId);
//        var tds = table.getElementsByTagName("tr");
//        var tr = document.createElement("tr");
//        var ranDomId = attachId;
//        tr.setAttribute("id", ranDomId);
//        var tdName = document.createElement("td");
//        var source = "<a href='uploadiframe!openFile.do?attachId=" + attachId+"' >"+name+"</a>";
//        if(dis != true){
//            source += "<img src='${contextPath}/share/images/delete.png' width='12' height='12' onclick='removeThis(\""+ranDomId+"\"" + "," + "\"" + tbId + "\")'/>" ;
//        }
//        tdName.innerHTML = source;
//        tr.appendChild(tdName);
//        table.appendChild(tr);
//    };


</script>