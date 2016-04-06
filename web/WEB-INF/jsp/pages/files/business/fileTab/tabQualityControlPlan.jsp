<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<script type="text/javascript">
    
    page.convertDateToString = function(dgDate){
        var year = dgDate.getYear()+1900;
        var month = dgDate.getMonth()+1;
        var date = dgDate.getDate();
        if(month < 10){
            month = "0"+ month ;
        }
        if(date < 10){
            date = "0"+date;
        }
        var str = date + "/"+month+"/"+year;
        return str;
    }

    createNewRowOfQualityControl = function(qualityControlPlanId,productProcessDetail,controlTarget,technicalRegulation, patternFrequence,testDevice,testMethod,noteForm,note,tableId){
        if(qualityControlPlanId == null){
            qualityControlPlanId = "";
        }
        if(productProcessDetail == null){
            productProcessDetail = "";
        }
        if(controlTarget == null){
            controlTarget = "";
        }
        if(technicalRegulation == null){
            technicalRegulation = "";
        }
        if(patternFrequence == null){
            patternFrequence = "";
        }
        if(testDevice == null){
            testDevice = "";
        }
        if(testMethod == null){
            testMethod = "";
        }
        if(noteForm == null){
            noteForm = "";
        }
        if(note == null){
            note = "";
        }       
        
        var size = getRowLengthOfTable(tableId)-2;
        var preName = "createForm.lstQualityControl["+size+"].";
        var row = document.createElement("tr");
        var tdIndex= document.createElement("td");
        tdIndex.setAttribute("align","center");
        tdIndex.innerHTML = size+1;
        var tdProductProcessDetail = document.createElement("td");
        {
            var inpProductProcessDetail = document.createElement("input");
            inpProductProcessDetail.setAttribute("type","text");
            inpProductProcessDetail.setAttribute("name", preName +"productProcessDetail");
            inpProductProcessDetail.setAttribute("id", preName + "productProcessDetail");
            inpProductProcessDetail.setAttribute("style", "width:100%");
            inpProductProcessDetail.setAttribute("value",productProcessDetail);
            inpProductProcessDetail.setAttribute("maxlength","250");
            tdProductProcessDetail.appendChild(inpProductProcessDetail);
                
            var inpProductProcessDetailId = document.createElement("input");
            inpProductProcessDetailId.setAttribute("type","hidden");
            inpProductProcessDetailId.setAttribute("name", preName +"qualityControlPlanId");
            inpProductProcessDetailId.setAttribute("id", preName + "qualityControlPlanId");
            inpProductProcessDetailId.setAttribute("value",qualityControlPlanId);
            tdProductProcessDetail.appendChild(inpProductProcessDetailId);
        }
        var tdControlTarget = document.createElement("td");
        {
            var inpControlTarget = document.createElement("input");
            inpControlTarget.setAttribute("type","text");
            inpControlTarget.setAttribute("name", preName +"controlTarget");
            inpControlTarget.setAttribute("id", preName + "controlTarget");
            inpControlTarget.setAttribute("value",controlTarget);
            inpControlTarget.setAttribute("style", "width:100%");
            inpControlTarget.setAttribute("maxlength","250");
            tdControlTarget.appendChild(inpControlTarget);
        } 
        
        var tdTechnicalRegulation = document.createElement("td");
        {
            var inpTechnicalRegulation = document.createElement("input");
            inpTechnicalRegulation.setAttribute("type","text");
            inpTechnicalRegulation.setAttribute("name", preName +"technicalRegulation");
            inpTechnicalRegulation.setAttribute("id", preName + "technicalRegulation");
            inpTechnicalRegulation.setAttribute("value",technicalRegulation);
            inpTechnicalRegulation.setAttribute("style", "width:100%");
            inpTechnicalRegulation.setAttribute("maxlength","250");
            tdTechnicalRegulation.appendChild(inpTechnicalRegulation);
        } 
            
        var tdPatternFrequence = document.createElement("td");
        {
            var inpPatternFrequence = document.createElement("input");
            inpPatternFrequence.setAttribute("type","text");
            inpPatternFrequence.setAttribute("name", preName +"patternFrequence");
            inpPatternFrequence.setAttribute("id", preName + "patternFrequence");
            inpPatternFrequence.setAttribute("value",patternFrequence);
            inpPatternFrequence.setAttribute("style", "width:100%");
            inpPatternFrequence.setAttribute("maxlength","250");
            tdPatternFrequence.appendChild(inpPatternFrequence);
        } 


        var tdTestDevice = document.createElement("td");
        {
            var inpTestDevice = document.createElement("input");
            inpTestDevice.setAttribute("type","text");
            inpTestDevice.setAttribute("name", preName +"testDevice");
            inpTestDevice.setAttribute("id", preName + "testDevice");
            inpTestDevice.setAttribute("value",testDevice);
            inpTestDevice.setAttribute("style", "width:100%");
            inpTestDevice.setAttribute("maxlength","250");
            tdTestDevice.appendChild(inpTestDevice);
        }
        
        var tdTestMethod = document.createElement("td");
        {
            var inpTestMethod = document.createElement("input");
            inpTestMethod.setAttribute("type","text");
            inpTestMethod.setAttribute("name", preName +"testMethod");
            inpTestMethod.setAttribute("id", preName + "testMethod");
            inpTestMethod.setAttribute("value",testMethod);
            inpTestMethod.setAttribute("style", "width:100%");
            inpTestMethod.setAttribute("maxlength","250");
            tdTestMethod.appendChild(inpTestMethod);
        }
        
        var tdNoteForm = document.createElement("td");
        {
            var inpNoteForm = document.createElement("input");
            inpNoteForm.setAttribute("type","text");
            inpNoteForm.setAttribute("name", preName +"noteForm");
            inpNoteForm.setAttribute("id", preName + "noteForm");
            inpNoteForm.setAttribute("value",noteForm);
            inpNoteForm.setAttribute("style", "width:100%");
            inpNoteForm.setAttribute("maxlength","250");
            tdNoteForm.appendChild(inpNoteForm);
        }
        
        var tdNote = document.createElement("td");
        {
            var inpNote = document.createElement("input");
            inpNote.setAttribute("type","text");
            inpNote.setAttribute("name", preName +"note");
            inpNote.setAttribute("id", preName + "note");
            inpNote.setAttribute("value",note);
            inpNote.setAttribute("style", "width:100%");
            inpNote.setAttribute("maxlength","250");
            tdNote.appendChild(inpNote);
        }  
        
        var tdDelete = document.createElement("td");
        {
            var img = document.createElement("img");
            img.src='share/images/icons/13.png';
            img.setAttribute("width", '20px');
            img.setAttribute("height",'20px');
            img.setAttribute("title",'Xóa tài liệu');
            img.setAttribute("onclick","page.deleteQualityControlPlan(this.parentNode.parentNode);");
            tdDelete.appendChild(img);
        }            
        
        row.appendChild(tdIndex);
        row.appendChild(tdProductProcessDetail);
        row.appendChild(tdControlTarget);
        row.appendChild(tdTechnicalRegulation);
        row.appendChild(tdPatternFrequence);
        row.appendChild(tdTestDevice);
        row.appendChild(tdTestMethod);
        row.appendChild(tdNoteForm);
        row.appendChild(tdNote);
        
        row.appendChild(tdDelete);
        document.getElementById(tableId).appendChild(row);
    }; 
    
</script>
<table class="viewTable">
    <tr style="background: #f0efef">
        <td colspan="4" style="color: #15428b;font-weight: bold;padding-left: 10px; height: 25px">
            Chỉ áp dụng đối với sản phẩm của cơ sở sản xuất trong nước có chứng chỉ hệ thống quản lý chất lượng tiên tiến: GMP, HACCP, ISO 22000 hoặc tương đương        
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label>Tên tổ chức, cá nhân</sd:Label></td>
            <td width="25%">
                <div id="createForm.qualityControlPlan.businessName" style="width:98%"/>
            </td>
            <td width="25%"><sd:Label>Địa chỉ</sd:Label></td>
            <td width="25%">
                <div id="createForm.qualityControlPlan.address" style="width:98%"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Tên sản phẩm</sd:Label></td>
            <td width="25%">
                <div id="createForm.qualityControlPlan.productName" style="width:98%"/>
            </td>
            <td width="25%" colspan="2"/>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Ngày ký</sd:Label></td>
            <td width="25%">
                <div id="createForm.qualityControlPlan.signDate" style="width:98%"/>
            </td>
            <td width="25%"><sd:Label>Người ký</sd:Label></td>
            <td width="25%">
                <div id="createForm.qualityControlPlan.signer" style="width:98%"/>
            </td>
        </tr>
    </table>
    <table id="qualityControlTbl" class="lstTable">
        <tr style="background: #f0efef">
            <td colspan="10" style="color: #15428b;font-weight: bold;padding-left: 10px; height: 25px">
                Kế hoạch kiểm soát chất lượng
            </td>
        </tr>
        <tr class="headerRow">
            <th width="5%">STT</th>
            <th width="15%">Các quá trình SX<font style="color:red">*</font></th>
            <th width="10%">Các chỉ tiêu kiểm soát</th>
            <th width="10%">Quy định kỹ thuật</th>
            <th width="10%">Tần xuất lấy mẫu/cỡ mẫu</th>
            <th width="10%">Thiết bị thử nghiệm/ kiểm tra</th>
            <th width="10%">Phương pháp thử/ kiểm tra</th>
            <th width="10%">Biểu ghi chép</th>
            <th width="15%">Ghi chú</th>
            <th width="5%">Xóa</th>
        </tr>
    </table>
    <div>
    <sx:ButtonAddCategory onclick="createNewRowOfQualityControl('','','','','','','','','','qualityControlTbl');"/>
</div>

<script type="text/javascript">
    
    page.renameElementOfQualityControlPlan = function(){
        var preName = "createForm.lstQualityControl[";
        var table = document.getElementById("qualityControlTbl");
        var rows = table.getElementsByTagName("tr");
        var i=0;
        for(i=2;i<rows.length;i++){
            var tds =rows[i].getElementsByTagName("td");
            tds[0].innerHTML = i-1;
            var inputs = rows[i].getElementsByTagName("input");
            inputs[0].setAttribute("name", preName+(i-2)+"].productProcessDetail");
            inputs[0].setAttribute("id", preName+(i-2)+"].productProcessDetail");
            inputs[1].setAttribute("name", preName+(i-2)+"].qualityControlPlanId");
            inputs[1].setAttribute("id", preName+(i-2)+"].qualityControlPlanId");
            inputs[2].setAttribute("name", preName+(i-2)+"].controlTarget");
            inputs[2].setAttribute("id", preName+(i-2)+"].controlTarget");
            inputs[3].setAttribute("name", preName+(i-2)+"].technicalRegulation");
            inputs[3].setAttribute("id", preName+(i-2)+"].technicalRegulation");
            inputs[4].setAttribute("name", preName+(i-2)+"].patternFrequence");
            inputs[4].setAttribute("id", preName+(i-2)+"].patternFrequence");
            inputs[5].setAttribute("name", preName+(i-2)+"].testDevice");
            inputs[5].setAttribute("id", preName+(i-2)+"].testDevice");
            inputs[6].setAttribute("name", preName+(i-2)+"].testMethod");
            inputs[6].setAttribute("id", preName+(i-2)+"].testMethod");
            inputs[7].setAttribute("name", preName+(i-2)+"].noteForm");
            inputs[7].setAttribute("id", preName+(i-2)+"].noteForm");
            inputs[8].setAttribute("name", preName+(i-2)+"].note");
            inputs[8].setAttribute("id", preName+(i-2)+"].note");
        }
    }
    
    page.deleteQualityControlPlan =function(rowElement){
        msg.confirm("Bạn có chắc muốn xóa không ?", "Xác nhận xóa", function(){
            rowElement.parentNode.removeChild(rowElement);
            page.renameElementOfQualityControlPlan();
        });
    }
    
    dojo.connect(dijit.byId('files_tab_tablist_tab.qualityControlPlan'), "onClick", loadFormFromAnnounTab = function() {       
        document.getElementById("createForm.qualityControlPlan.businessName").innerHTML = escapeHtml_(dijit.byId("createForm.announcement.businessName").getValue());
        document.getElementById("createForm.qualityControlPlan.address").innerHTML = escapeHtml_(dijit.byId("createForm.announcement.businessAddress").getValue());
        document.getElementById("createForm.qualityControlPlan.productName").innerHTML = escapeHtml_(dijit.byId("createForm.announcement.productName").getValue());
        var date = dijit.byId("createForm.announcement.publishDate").getValue();
        var signDate = "";
        if(date != null){
            signDate = page.convertDateToString(date);
        }
        document.getElementById("createForm.qualityControlPlan.signDate").innerHTML = escapeHtml_(signDate);
        document.getElementById("createForm.qualityControlPlan.signer").innerHTML = escapeHtml_(dijit.byId("createForm.announcement.signer").getValue());
    });
    
    page.validateQualityPlanData = function(){
        var tabs = dijit.byId("files_tab");
        var panel = dijit.byId("tab.qualityControlPlan");
        tabs.selectChild(panel);        
        var table = document.getElementById("qualityControlTbl");
        var rows = table.getElementsByTagName("tr");
        var i=0;
        for(i=2;i<rows.length;i++){
            var inputs = rows[i].getElementsByTagName("input");
            var content = inputs[0].value.trim();
            inputs[0].value = content;
            if(content == null || content == ""){
                alert("[Các quá trình xử lý dòng "+(i-1)+"] chưa nhập");
                inputs[0].focus();
                return false;
            } else if(content.length > 250 ){
                alert("[Các quá trình xử lý dòng "+(i-1)+"] dài hơn 250 ký tự");
                inputs[0].focus();
                return false;
            }
            
            content = inputs[2].value.trim();
            inputs[2].value = content;
            if(content.length > 250 ){
                alert("[Các chỉ tiêu kiểm soát dòng "+(i-1)+"] dài hơn 250 ký tự");
                inputs[1].focus();
                return false;
            }  
            
            content = inputs[3].value.trim();
            inputs[3].value = content;
            if(content.length > 250 ){
                alert("[Quy định kỹ thuật dòng "+(i-1)+"] dài hơn 250 ký tự");
                inputs[3].focus();
                return false;
            }  
            
            content = inputs[4].value.trim();
            inputs[4].value = content;
            if(content.length > 250 ){
                alert("[Tần xuất lấy mẫu / cỡ mẫu dòng "+(i-1)+"] dài hơn 250 ký tự");
                inputs[4].focus();
                return false;
            }  
            content = inputs[5].value.trim();
            inputs[5].value = content;
            if(content.length > 250 ){
                alert("[Thiết bị thử nghiệm / kiểm tra dòng "+(i-1)+"] dài hơn 250 ký tự");
                inputs[5].focus();
                return false;
            }  
            
            content = inputs[6].value.trim();
            inputs[6].value = content;
            if(content.length > 250 ){
                alert("[Phương pháp thử / kiểm tra dòng "+(i-1)+"] dài hơn 250 ký tự");
                inputs[6].focus();
                return false;
            }  

            content = inputs[7].value.trim();
            inputs[7].value = content;
            if(content.length > 250 ){
                alert("[Biểu ghi chép dòng "+(i-1)+"] dài hơn 250 ký tự");
                inputs[6].focus();
                return false;
            } 
            
            content = inputs[7].value.trim();
            inputs[7].value = content;
            if(content.length > 250 ){
                alert("[Ghi chú dòng "+(i-1)+"] dài hơn 250 ký tự");
                inputs[7].focus();
                return false;
            }  
           
        }
        return true;
    }    
    
    page.validateQualityPlan = function(){
        
        if(!page.validateQualityPlanData()){
            return false;
        }
        
        return true;
        
    }
    
    page.afterLoadQualityControl = function(data){
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if(result != null && result.length > 0){
            for(var i=0;i<result.length;i++){
                var item = result[i];
                createNewRowOfQualityControl(item.qualityControlPlanId,item.productProcessDetail,item.controlTarget,item.technicalRegulation, item.patternFrequence,item.testDevice,item.testMethod,item.noteForm,item.note,'qualityControlTbl');
            }
        }
    }

    page.loadInitQualityControl = function(){
        var fileId = dijit.byId("createForm.fileId").getValue();
        if(fileId == null || fileId == ""){
            //createNewRowOfQualityControl('','','','','','','','','','qualityControlTbl');
        } else {
            sd.connector.post("filesAction!loadQualityControls.do?createForm.fileId="+fileId,null,null,null,page.afterLoadQualityControl);
        }
    }
    page.loadInitQualityControl();
</script>