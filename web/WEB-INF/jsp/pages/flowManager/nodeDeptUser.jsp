<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../../common/commonJavascript.jsp" />
<style type="text/css">
    #processGridId_VTGrid div.gridHeader {
        display: none;
    }
</style>
<script>

    var dialogAssign="0";
    <c:if test="${not empty dialogAssign}">
        dialogAssign=${fn:escapeXml(dialogAssign)};
    </c:if>	

        page.onSearchUserByDept = function (deptId){
            dijit.byId('userGridId').vtReload("departmentAction!getUserOfDept.do?deptId="+deptId) ;
        }
    
        page.getIndex=function(inRow){
            return (inRow+1);
        }

        page.processTypeFormat = function(inRow){
            var item = dijit.byId("processGridId").getItem(inRow);
            if(item == null){
                return "";
            }
            var processType = item.processType;
            var str = "<label onclick='setMainProcess("+inRow+")'>Phối hợp </label> <img src='share/images/icons/reset.png' width='16' height='16' title='Đổi sang là xử lý chính' onclick='setMainProcess("+inRow+")'>";
            if(processType == "1"){
                str = "<label onclick='setMainProcess("+inRow+")'>Xử lý chính</label>  <img src='share/images/icons/reset.png' width='16' height='16' title='Đổi sang là đồng xử lý' onclick='setMainProcess("+inRow+")'>";
            }
            return str;
        }

        page.statusFormat = function(inRow){
            var item = dijit.byId("processGridId").getItem(inRow);
            if(item == null){
                return "";
            }
            var str = "Mới đến";
            if(item.status!=null) {
                var status = item.status.toString();
                switch(status){
                    case "0":
                        str = "Mới đến";
                        break;
                    case "1":
                        str = "Đã lưu sổ đơn vị";
                        break;
                    case "2":
                        str = "Đã chuyển xin ý kiến lãnh đạo";
                        break;
                    case "3":
                        str = "Đã phân công";
                        break;
                    case "4":
                        str = "Hoàn thành";
                        break;
                    case "5":
                        str = "Trả lại";
                        break;
                    default:;
                }
            }
            return str;
        }
    
        page.formatAddUserToDept= function(inDatum){
            var item = dijit.byId("deptGridId").getItem(inDatum);
            if(item != null){
                var url = "<div><img src='share/images/icons/a7.png'  title='Bổ sung người dùng' onClick='page.preAddUserToDept(" + inDatum + ")' height='20' width='20' /></div>";
                return url;
            }
        }
        // enter key
        page.searchDefault = function(evt){
            var dk = dojo.keys;
            switch(evt.keyCode){
                case dk.ENTER:
                    page.onSearchUserByDept();
                    break;
            }
        }

        dojo.connect(dojo.byId("deptSearchDiv"),"onkeypress",page.searchDefault);


</script>
<table width="100%" style="table-layout:fixed;">
    <tr style="display: none">
        <td>
            <input type="hidden" id="objectId"/>
            <input type="hidden" id="objectType" value="1"/>
            <input type="hidden" id="receiveGroupId"/>
            <input type="hidden" id="receiveGroup"/>
            <input type="hidden" id="receiveUserId"/>
        </td>
    </tr>
    <tr>
        <td width="30%" valign="top" style="overflow: auto;">
            <sd:TitlePane key="department.treeTitle" id="deptTreePane">
                <div id="treeDiv" style="overflow: auto;">
                    <sd:AjaxTree 
                        id="deptTreeId" 
                        getChildrenUrl="departmentAction!getCheckedChildrenDataByNode.do" 
                        getTopLevelUrl="departmentAction!getMyDeptRootTree.do" 
                        rootLabel="usersForm.department"
                        onNodeChecked="page.onNodeChecked"
                        onNodeUnchecked="page.onNodeUnchecked"
                        onClick="page.onNodeClick"/>
                </div>
            </sd:TitlePane>
        </td>

        <td width="70%" valign="top">
            <sd:TitlePane key="documentReceive.staffList" id="staffPanel">
                <div id="userGridDiv" style="width:100%; height: 150px">
                    <sd:DataGrid clientSort="false" 
                                 id="userGridId"
                                 style="width:auto;height:auto"
                                 getDataUrl="" 
                                 container="userGridDiv" 
                                 serverPaging="false"
                                 rowSelector="0px" 
                                 rowsPerPage="20">
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial " cellStyles="text-align:center;" editable="false" key="index" get="page.getIndex" width="5%" />
                        <sd:ColumnDataGrid headerStyles="text-align:center;"  cellStyles="text-align:center;"  key="column.checkbox" headerCheckbox="true"  type="checkbox" width="5%"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="usersForm.userName" field="userName" width="30%"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="usersForm.fullName" field="fullName" width="30%"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="usersForm.cellphone" field="cellphone" width="15%"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="usersForm.staffCode" field="staffCode" width="15%"/>
                    </sd:DataGrid>
                </div>
                <table width="100%">
                    <tr>
                        <td align="left">
                            <sd:Button id="" key="" onclick="page.listLeader()">
                                <img src="share/images/icons/role.png" height="20" width="20"/>
                                <span style="font-size: 12px"><sd:Property>documentReceive.listLeader</sd:Property> </span>
                            </sd:Button>
                            <sd:Button id="" key="" onclick="page.listStaff()">
                                <img src="share/images/icons/comment.png" height="20" width="20"/>
                                <span style="font-size: 12px"><sd:Property>documentReceive.listStaff</sd:Property> </span>
                            </sd:Button>
                            <sd:Button id="departmentForm.btnInsert" key="" onclick="page.InsertStaff()">
                                <img src="share/images/icons/a7.png" height="20" width="20">
                                <span style="font-size:12px"><sd:Property>documentReceive.add</sd:Property></span>
                            </sd:Button>
                        </td>
                    </tr>
                </table>
            </sd:TitlePane>
            <sd:TitlePane key="documentReceive.processList" id="processTitle">
                <div id="tblSuccessMessage" style="display: none; margin-bottom: 3px; padding: 2px; border-width: 1px;border-color: gray; border-style: solid;background-color: #90EE90">
                    <label style="color: green;font-size: 13px;" id="lblSuccessMessage">
                    </label>
                </div>
                <div id="tblErrorMessage" style="display: none;margin-bottom: 3px; padding: 2px; border-width: 1px; border-color: red;border-style: solid; background-color: #FDE5DD">
                    <label style="color: red;font-size: 13px;" id="lblErrorMessage">
                    </label>
                </div>
                <div id="processGridDiv" style="width:100%; height: 150px;">
                    <sd:DataGrid clientSort="false" 
                                 id="processGridId"
                                 style="width:auto;height:auto"
                                 getDataUrl="" 
                                 container="processGridDiv" 
                                 serverPaging="false"
                                 rowSelector="0px" 
                                 rowsPerPage="10">
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial " cellStyles="text-align:center;" editable="false" key="index" get="page.getIndex" width="5%" />
                        <sd:ColumnDataGrid headerStyles="text-align:center;"  cellStyles="text-align:center;"  key="column.checkbox" headerCheckbox="true"  type="checkbox" width="5%"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="documentReceive.receiveGroup" field="receiveGroup" width="25%"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="documentReceive.receiveUser" field="receiveUser" width="25%"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="documentReceive.status" get="page.getRow" formatter="page.statusFormat" width="10%"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="documentReceive.processType" get="page.getRow" formatter="page.processTypeFormat" width="15%"/>
                        <sd:ColumnDataGrid editable="true" headerStyles="text-align:center;font-weight: bold;" type="date" format="dd/MM/yyyy" key="documentReceive.deadline" field="deadline" width="15%"/>
                    </sd:DataGrid>
                </div>

                <table>
                    <tr>
                        <td align="center">
                            <div align="right">
                                <sd:Button id="btnDelete" key="" onclick="page.deleteProcess()">
                                    <img src="share/images/icons/13.png" height="20" width="20"/>
                                    <span style="font-size:12px"><sd:Property>btnDelete</sd:Property></span>
                                </sd:Button>
                            </div>
                        </td>
                    </tr>
                </table>
            </sd:TitlePane>
            <sd:TitlePane id="processContentTitle" key="documentReceive.processContent">
                <div id="processContentDiv" style="width:100%;">
                    <div>
                        <form id="processContentForm" name="processContentForm">
                            <table style="width: 100%;margin: 0;padding: 0">
                                <tr>
                                    <td style="width: 20%;padding-right: 10px;" align="right">
                                        <sd:Label key="documentReceive.deadline" cssStyle="font-weight:bold;"/>
                                    </td>
                                    <td style="width: 80%;">
                                        <sd:DatePicker id="processContentForm.deadline" name="processContentForm.deadline" key=""
                                                       format="dd/MM/yyyy" cssStyle="width:50%" onchange="page.setDeadline()"/>
                                    </td>
                                </tr>
                                <tr style="padding-top: 5px;">
                                    <td style="width: 20%;padding-right: 10px;" align="right">
                                        <sd:Label key="documentReceive.processContent" cssStyle="font-weight:bold;"/>
                                    </td>
                                    <td style="width: 80%;">
                                        <sd:Textarea id="processContentForm.processContent" name="processContentForm.processContent" key=""
                                                     cssStyle="width:99%" trim="true" 
                                                     maxlength="4000" />
                                    </td>
                                </tr>

                                <tr>
                                    <td></td>
                                    <td>
                                        <sd:Button id="btnUpdate" key="" onclick="page.updateDealine()">
                                            <img src='share/images/icons/edit.png' title='Cập nhật thông tin' height='20' width='20' />
                                            <span style="font-size:12px"><sd:Property>btnCommit</sd:Property></span>
                                        </sd:Button>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </sd:TitlePane>
        </td>


    </tr>
</table>
<script type="text/javascript">
    //    mainProcess = true;
    Date.prototype.setISO8601 = function (string) {
        var regexp = "([0-9]{4})(-([0-9]{2})(-([0-9]{2})" +
            "(T([0-9]{2}):([0-9]{2})(:([0-9]{2})(\.([0-9]+))?)?" +
            "(Z|(([-+])([0-9]{2}):([0-9]{2})))?)?)?)?";
        var d = string.match(new RegExp(regexp));

        var offset = 0;
        var date = new Date(d[1], 0, 1);

        if (d[3]) { date.setMonth(d[3] - 1); }
        if (d[5]) { date.setDate(d[5]); }
        if (d[7]) { date.setHours(d[7]); }
        if (d[8]) { date.setMinutes(d[8]); }
        if (d[10]) { date.setSeconds(d[10]); }
        if (d[12]) { date.setMilliseconds(Number("0." + d[12]) * 1000); }
        if (d[14]) {
            offset = (Number(d[16]) * 60) + Number(d[17]);
            offset *= ((d[15] == '-') ? 1 : -1);
        }

        offset -= date.getTimezoneOffset();
        time = (Number(date) + (offset * 60 * 1000));
        this.setTime(Number(time));
    }
    
    afterUpdateDB = function(data) {  
        var obj = dojo.fromJson(data);        
        var message = obj.customInfo;
        if(message != null && message.trim().length > 0){
            alert(message);
        }
        //resultMessage_show("jpBanPositionGridResultMessage", result[0], result[1], 5000);
        var objectId = document.getElementById("objectId").value ;
        var objectType=document.getElementById("objectType").value;
        // sytv add 
        if(dialogAssign=="1") {
            getProcess(objectId, objectType);       // load lai luong xu ly cua van ban
            page.getComments(objectId,objectType);  
        } else {
            dijit.byId("processGridId").vtReload("assignDoc!getProcessOfDoc.do?documentId="+objectId); 
        }
        
        msg.info("Cập nhật thành công","Thông báo",function(){
            var dlg = dijit.byId("assignDocumentDlg");
            dlg.hide();        
            searchText();
            page.close();
        });
    }
    
    setMainProcess = function(row){
        var grid = dijit.byId("processGridId");
        var item = grid.getItem(row);
        if(item.processType == "1"){
            item.processType = "0"
        } else {
            if(mainProcess == false){
                msg.alert("Đơn vị chỉ phối hợp xử lý nên không được phân công xử lý chính", "Cảnh báo");
            } else {
                var length = grid._by_idx.length.toString();
                var i=0;
                var countMainProcess = 0;
                for(i=0;i<length;i++){
                    var itemi = grid.getItem(i);
                    if("1" == itemi.processType.toString()){
                        countMainProcess++;
                    }
                }
                if(countMainProcess == 0){
                    item.processType = "1";
                } else {
                    msg.alert("Bạn đã chọn đơn vị, cá nhân xử lý chính","Cảnh báo");
                }
            }
        }
        grid.doApplyCellEdit(item.processType,row,"processType");
        grid.scrollToRow(grid._by_idx.length);
        grid.renderNoReload();
        
    }
    
    page.showAssignDlg = function(documentId){
        //        var tree = dijit.byId("deptTreeId");
        //        alert("here");
        //        var rootNode = tree.vtGetRootOrFirstNode();
        //        alert("here");
        //        tree.refresh("departmentAction!getMyDeptRootTree.do");
        //        alert("here");
        var root =dijit.byId("deptTreeId")._getRootOrFirstNode();
        //linhdx
        //dijit.byId("deptTreeId")._collapseNode(root);
        document.getElementById("objectId").value = escapeHtml_(documentId);
        //document.getElementById("receiveGroupId").value = documentId;
        //dijit.byId('userGridId').vtReload("departmentAction!getMyDeptUser.do?");
        dijit.byId('userGridId').vtReload("departmentAction!getUserOfDept.do?deptId=0") ;
        dijit.byId("processGridId").vtReload("assignDoc!getProcessOfDoc.do?documentId="+documentId);
        sd.connector.post("assignDoc!initProcessContent.do?documentId="+documentId, null, null, null, page.afterInit);
    }
    page.afterInit= function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var processContent = "";
        if(result[1]!= "") {
            processContent = result[1];
            
        }
        document.getElementById("processContentForm.deadline").value = escapeHtml_(result[0]);
        document.getElementById("processContentForm.processContent").value = escapeHtml_(processContent);
    }    
    page.InsertStaff = function(){
        var userGrid = dijit.byId("userGridId");
        var items = userGrid.vtGetCheckedItems();
        var i=0;
        var objectId = document.getElementById("objectId").value ;
        var objectType = document.getElementById("objectType").value ;
        var receiveGroupId = document.getElementById("receiveGroupId").value ;
        var receiveGroup = document.getElementById("receiveGroup").value;
        for(i=0;i<items.length;i++){
            var item = items[i];
            if(i==0){
                //alert(item.userId);
                var row = page.getProcessItemByGroup(receiveGroupId);
                if(row > -1){
                    //
                    //
                    //var processRow = dijit.byId("processGridId").getItem(row);
                    dijit.byId("processGridId").doApplyCellEdit(item.userId+"",row,"receiveUserId");
                    dijit.byId("processGridId").doApplyCellEdit(item.fullName,row,"receiveUser");
                    //dijit.byId("processGridId").store.setValue(item, "receiveUserId",  item.userId+'');


                } else {
                    if(!page.checkUserExist(item.userId)){
                        page.insertNewProcessItem(objectId,objectType,item.userId,item.fullName,receiveGroupId,receiveGroup);
                    }
                }
            } else {
                if(!page.checkUserExist(item.userId)){
                    page.insertNewProcessItem(objectId,objectType,item.userId,item.fullName,receiveGroupId,receiveGroup);
                }
            }
        }
        dijit.byId("processGridId").store.save();
        if(items.length ==0){
            msg.alert("Bạn chưa chọn đối tượng để tham gia","Cảnh báo");
        }
    }
    
    page.checkMainProcessExist = function(){
        var i=0;
        var grid =  dijit.byId("processGridId");
        var length = grid._by_idx.length;
        for(i=0;i<length;i++){
            var item = grid.getItem(i);
            if("1" == item.processType.toString()){
                return true;
            }
        }
        return false;
    }
    
    page.insertNewProcessItem = function(objectId, objectType, userId, userName, receiverGroupId, receiverGroup){
        var grid = dijit.byId("processGridId");
        var item={
            processId       :null,
            processStatusId :null,
            objectId        :objectId,
            objectType      :objectType,
            sendUserId      :null,
            sendUser        :null,
            sendGroupId     :null,
            sendGroup       :null,
            sendDate        :null,
            receiveUserId   :userId,
            receiveUser     :userName,
            receiveGroupId  :receiverGroupId,
            receiveGroup    :receiverGroup,
            receiveDate     :null,
            processType     :0,
            orderProcess    :null,
            status          :0,
            deadline        :null,
            deadlineNumber  :null,
            isNotifyByEmail :0,
            isNotifyByMessage:0,
            moreInfo        :"",
            isActive        :1               
        };
        
        if(mainProcess){
            if(!page.checkMainProcessExist()){
                item.processType = 1;
            }
        }
        
        grid.store.newItem(item);
        grid.store.save();
        grid.renderNoReload();
    }
    
    page.deleteProcess = function(){
        var processGrid = dijit.byId("processGridId");
        var items = processGrid.vtGetCheckedItems();
        
        if(items == null || items.length == 0){
            msg.alert("Bạn chưa chọn đối tượng để xóa","Cảnh báo");
            return;
        }
        
        msg.confirm('<sd:Property>confirm.delete</sd:Property>',"<sd:Property>confirm.title</sd:Property>", function() {
            var i=0;
            for(i=0;i<items.length;i++){
                var item = items[i];
                processGrid.store.deleteItem(item);
            }
            processGrid.scrollToRow(processGrid._by_idx.length);
        });       

    }
    
    page.setDeadline = function() {
        var grid = dijit.byId("processGridId");
        var length = dijit.byId("processGridId")._by_idx.length.toString();
        var dateStr=document.getElementById("processContentForm.deadline").value;
        var deadline=new Date(dateStr.replace( /(\d{2})\/(\d{2})\/(\d{4})/, "$2/$1/$3")); 
        for(i=0;i<length;i++){
            var item = dijit.byId("processGridId").getItem(i);
            try{
                grid.doApplyCellEdit(deadline,grid.getItemIndex(item),"deadline");
            }catch(e){
                alert(e.message);
            }
        }
    }
    
    page.updateDealine = function(){
        
        dijit.byId("processGridId").edit.apply();
        var objectId = document.getElementById("objectId").value ;
        var objectType = document.getElementById("objectType").value ;
        var length = dijit.byId("processGridId")._by_idx.length.toString();
        var content = dijit.byId("processGridId").vtGetTotalDataForPost("lstProcess");
        var date = new Date(); 
        var i=0;
        var countMainProcess = 0;
        for(i=0;i<length;i++){
            var item = dijit.byId("processGridId").getItem(i);
            if("1" == item.processType.toString()){
                countMainProcess++;
            }
            if(item.deadline != null && item.deadline.toString() != ""){
                var deadline = new Date();
                deadline.setISO8601(item.deadline.toString());

                //var compare = dojo.date.compare(date,item.deadline);
                //alert(compare);
                if(date > deadline){
                    msg.alert("Hạn xử lý phải >= ngày hôm nay", "Cảnh báo");
                    return false;
                } else {
                }
            }
            //linhdx Kiem tra don vi xu ly chinh
            
            if(item.processType.toString() == "1"){
                mainProcess = 1;
            }
        }
        if(mainProcess){
            if(countMainProcess == 0){
                msg.alert("Chưa chọn cá nhân, đơn vị xử lý chính","Cảnh báo");
                return false;
            } else if(countMainProcess > 1){
                msg.alert("Chỉ được chọn một cá nhân hoặc đơn vị xử lý chính", "Cảnh báo");
                return false;
            }
        }
        
        
        /*
        if(mainProcess==0){
            msg.alert("Phải chọn đơn vị xử lý chính");
            return false;
        }
         */
        msg.confirm('<sd:Property>confirm.update</sd:Property>',"<sd:Property>confirm.title</sd:Property>", function() {
            if(length == "0"){
                sd.connector.post("assignDoc!updateDealine.do?objectId="+objectId+"&objectType="+objectType+"&"+token.getTokenParamString(),null,"processContentForm",null,afterUpdateDB);        
            } else {
                sd.connector.post("assignDoc!updateDealine.do?"+token.getTokenParamString(),null,"processContentForm",content,afterUpdateDB);
            }
        });
    }
    
    page.saveProcess =  function(deptId, staffId){
        document.getElementById("receiveGroupId").value = escapeHtml_(deptId) ;
        document.getElementById("receiveUserId").value = escapeHtml_(staffId) ;
    }
    
    page.addNewProcessItem = function(){
        var objectId = document.getElementById("objectId").value ;
        var receiveGroupId = document.getElementById("receiveGroupId").value ;
        var receiveUserId = document.getElementById("receiveUserId").value ;
       
        sd.connector.post("assignDoc!insertNewItem.do?"+token.getTokenParamString()+"&objectId="+objectId+"&receiveGroupId="+receiveGroupId+"&receiveUserId="+receiveUserId,null,null,null,afterUpdateDB);
    }
    
    page.onNodeClick = function(item, node, event){
        var objectId = document.getElementById("objectId").value ;
        var objectType = document.getElementById("objectType").value ;
        document.getElementById("receiveGroupId").value = item.id;
        document.getElementById("receiveGroup").value = item.name;
        dijit.byId('userGridId').vtReload("departmentAction!getStaffOfDept.do?deptId=" + item.id + "&objectId=" + objectId + "&objectType=" + objectType) ;
    }
    page.listLeader = function() {
        var id=document.getElementById("receiveGroupId").value;
        var objectId = document.getElementById("objectId").value ;
        var objectType = document.getElementById("objectType").value ;
        // var name=document.getElementById("receiveGroup").value;
        if(id == "") {
            msg.alert("Bạn cần phải chọn đơn vị của lãnh đạo","Cảnh báo");
            return ;
        }
        dijit.byId('userGridId').vtReload("departmentAction!getStaffOfDept.do?pos=LEADER&deptId=" + id + "&objectId=" + objectId + "&objectType=" + objectType);
    }
    
    page.listStaff = function() {
        var id=document.getElementById("receiveGroupId").value;
        var objectId = document.getElementById("objectId").value ;
        var objectType = document.getElementById("objectType").value ;
        // var name=document.getElementById("receiveGroup").value;
        if(id == "") {
            msg.alert("Bạn cần phải chọn đơn vị của nhân viên","Cảnh báo");
            return ;
        }
        dijit.byId('userGridId').vtReload("departmentAction!getStaffOfDept.do?pos=STAFF&deptId=" + id + "&objectId=" + objectId + "&objectType=" + objectType);
    }
    page.checkDeptExist = function(receiverGroupId){
        var grid = dijit.byId("processGridId");
        // check xem da ton tai tren grid chua
        var exist = false;
        var i=0;
        for(i=0;i<grid._by_idx.length;i++){
            var processItem = grid.getItem(i);
            if(processItem.receiveGroupId.toString() == receiverGroupId.toString()){
                exist =true;
                break;
            }            
        }  
        return exist;
    }
    
    page.getProcessItemByGroup = function(receiverGroupId){
        var grid = dijit.byId("processGridId");
        var result = -1;
        var i=0;
        for(i=0;i<grid._by_idx.length;i++){
            var processItem = grid.getItem(i);
            if(processItem.receiveGroupId == receiverGroupId && (processItem.receiveUserId == null || processItem.receiveUserId.toString() == "" )){
                result =i;
                break;
            }            
        }  
        return result;
    }

    page.checkUserExist = function(userId){
        var grid = dijit.byId("processGridId");
        // check xem da ton tai tren grid chua
        var exist = false;
        var i=0;
        for(i=0;i<grid._by_idx.length;i++){
            var processItem = grid.getItem(i);
            //alert(userId +"-"+processItem.receiveUserId);
            if(processItem.receiveUserId.toString() == userId.toString()){
                exist =true;
                break;
            }            
        }  
        return exist;
    }

    page.onNodeChecked=function(item,node) {
        //
        // Luu cac item xu ly vao cac bien
        //
        page.saveProcess(item.id,null);
        var exist = page.checkDeptExist(item.id);
        
        if(!exist){
            var objectId = document.getElementById("objectId").value ;
            var objectType = document.getElementById("objectType").value ;
            var receiveGroupId = document.getElementById("receiveGroupId").value ;
            var receiveGroup = document.getElementById("receiveGroup").value;
            page.insertNewProcessItem(objectId,objectType,"","",receiveGroupId,receiveGroup);

            //page.addNewProcessItem();
        }
    }

    page.onNodeUnchecked=function(item,node){
        var grid = dijit.byId("processGridId");
        // check xem da ton tai tren grid chua
        var i=0;
        for(i=0;i<grid._by_idx.length;i++){
            var processItem = grid.getItem(i);
            if(processItem.receiveGroupId.toString() == item.id.toString()){
                grid.store.deleteItem(processItem);
            } 
        }
        grid.scrollToRow(grid._by_idx.length);
    }
    
    page.afterUpdateAssign = function(data) {
        var obj = dojo.fromJson(data);        
        var message = obj.customInfo;
        if(message != null && message.trim().length > 0){
            alert(message);
        }
        msg.info("Cập nhật thành công","Thông báo");
    }
    
    page.updateAssign = function(){
        dijit.byId("processGridId").edit.apply();
        var objectId = document.getElementById("objectId").value ;
        var objectType = document.getElementById("objectType").value ;
        var length = dijit.byId("processGridId")._by_idx.length.toString();
        var content = dijit.byId("processGridId").vtGetTotalDataForPost("lstProcess");
        var date = new Date(); 
        var i=0;
        var countMainProcess = 0;
        for(i=0;i<length;i++){
            var item = dijit.byId("processGridId").getItem(i);
            if("1" == item.processType.toString()){
                countMainProcess++;
            }
            if(item.deadline != null && item.deadline.toString() != ""){
                var deadline = new Date();
                deadline.setISO8601(item.deadline.toString());

                //var compare = dojo.date.compare(date,item.deadline);
                //alert(compare);
                if(date > deadline){
                    msg.alert("Hạn xử lý phải >= ngày hôm nay", "Cảnh báo");
                    return false;
                } else {
                }
            }
            //linhdx Kiem tra don vi xu ly chinh
            
            if(item.processType.toString() == "1"){
                mainProcess = 1;
            }
        }
        if(mainProcess){
            if(countMainProcess == 0){
                msg.alert("Chưa chọn cá nhân, đơn vị xử lý chính","Cảnh báo");
                return false;
            } else if(countMainProcess > 1){
                msg.alert("Chỉ được chọn một cá nhân hoặc đơn vị xử lý chính","Cảnh báo");
                return false;
            }
        }
        /*
        if(mainProcess==0){
            msg.alert("Phải chọn đơn vị xử lý chính");
            return false;
        }
         */
        msg.confirm('<sd:Property>confirm.update</sd:Property>',"<sd:Property>confirm.title</sd:Property>", function() {
            if(length == "0"){
                sd.connector.post("assignDoc!updateDealine.do?objectId="+objectId+"&objectType="+objectType+"&"+token.getTokenParamString(),null,"processContentForm",null,page.afterUpdateAssign);        
            } else {
                sd.connector.post("assignDoc!updateDealine.do?"+token.getTokenParamString(),null,"processContentForm",content,page.afterUpdateAssign);
            }
        });
    }
    
    insertNewProcessItem = function(objectId, objectType, userId, userName, receiverGroupId, receiverGroup, processType){
        var grid = dijit.byId("processGridId");
        var item={
            processId       :null,
            processStatusId :null,
            objectId        :objectId,
            objectType      :objectType,
            sendUserId      :null,
            sendUser        :null,
            sendGroupId     :null,
            sendGroup       :null,
            sendDate        :null,
            receiveUserId   :userId,
            receiveUser     :userName,
            receiveGroupId  :receiverGroupId,
            receiveGroup    :receiverGroup,
            receiveDate     :null,
            processType     :processType,
            orderProcess    :null,
            status          :0,
            deadline        :null,
            deadlineNumber  :null,
            isNotifyByEmail :0,
            isNotifyByMessage:0,
            moreInfo        :"",
            isActive        :1               
        };
        
        grid.store.newItem(item);        
        grid.store.save();
        grid.renderNoReload();
    }
    insertNewProcessItemMonitor = function(objectId, objectType, userId, userName, receiverGroupId, receiverGroup, processType, receiveUserType){
        var grid = dijit.byId("processGridId");
        var item={
            processId       :null,
            processStatusId :null,
            objectId        :objectId,
            objectType      :objectType,
            sendUserId      :null,
            sendUser        :null,
            sendGroupId     :null,
            sendGroup       :null,
            sendDate        :null,
            receiveUserId   :userId,
            receiveUser     :userName,
            receiveGroupId  :receiverGroupId,
            receiveGroup    :receiverGroup,
            receiveDate     :null,
            processType     :processType,
            receiveUserType :receiveUserType,
            orderProcess    :null,
            status          :0,
            deadline        :null,
            deadlineNumber  :null,
            isNotifyByEmail :0,
            isNotifyByMessage:0,
            moreInfo        :"",
            isActive        :1               
        };
        
        grid.store.newItem(item);        
        grid.store.save();
        grid.renderNoReload();
    }
    
</script>