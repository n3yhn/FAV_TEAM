<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../common/commonJavascript.jsp" />
<script type="text/javascript">

    var dialogAssign = "0";

    page.onSearchUserByDept = function(deptId) {
        dijit.byId('userGridId').vtReload("departmentAction!getUserOfDept.do?deptId=" + deptId);
    }

    page.getIndex = function(inRow) {
        return dijit.byId("userGridId").currentRow + (inRow + 1);
    }

    page.processTypeFormat = function(inRow) {
        var item = dijit.byId("processGridId").getItem(inRow);
        if (item == null) {
            return "";
        }
        var processType = item.processType;
        var str = "<label onclick='setMainProcess(" + inRow + ")'>Phối hợp </label> <img src='share/images/icons/reset.png' width='16' height='16' title='Đổi sang là xử lý chính' onclick='setMainProcess(" + inRow + ")'>";
        if (processType == "1") {
            str = "<label onclick='setMainProcess(" + inRow + ")'>Xử lý chính</label>  <img src='share/images/icons/reset.png' width='16' height='16' title='Đổi sang là đồng xử lý' onclick='setMainProcess(" + inRow + ")'>";
        }
        return str;
    }

    // enter key
    page.searchDefault = function(evt) {
        var dk = dojo.keys;
        switch (evt.keyCode) {
            case dk.ENTER:
                page.onSearchUserByDept();
                break;
        }
    }


</script>
<table width="100%" style="table-layout:fixed;">
    <tr style="display: none">
        <td>
            <input type="hidden" id="receiveGroupId"/>
            <input type="hidden" id="receiveGroup"/>
            <input type="hidden" id="receiveUserId"/>
        </td>
    </tr>
    <tr>
        <td width="35%" valign="top" style="overflow: auto;">
            <sd:TitlePane key="department.treeTitle" id="deptTreePane">
                <div id="treeDiv" style="overflow: auto; height: 400px" >
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

        <td width="65%" valign="top">
            <sd:TitlePane key="documentReceive.staffList" id="staffPanel">
                <div id="userGridDiv" style="width:100%;">
                    <sd:DataGrid clientSort="false" 
                                 id="userGridId"
                                 style="width:auto;height:auto"
                                 getDataUrl="" 
                                 container="userGridDiv" 
                                 serverPaging="false"
                                 rowSelector="0px" 
                                 rowsPerPage="10">
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
                <div id="processGridDiv" style="width:100%;">
                    <sd:DataGrid clientSort="false" 
                                 id="processGridId"
                                 style="width:auto;height:auto"
                                 getDataUrl="" 
                                 container="processGridDiv" 
                                 serverPaging="false"
                                 rowSelector="0px" 
                                 rowsPerPage="100"
                                 >
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial " cellStyles="text-align:center;" editable="false" key="index" get="page.getIndex" width="5%" />
                        <sd:ColumnDataGrid headerStyles="text-align:center;"  cellStyles="text-align:center;"  key="column.checkbox" headerCheckbox="true"  type="checkbox" width="5%"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="documentReceive.receiveGroup" field="deptName" width="25%"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="documentReceive.receiveUser" field="userName" width="25%"/>
                        <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="documentReceive.processType" get="page.getRow" formatter="page.processTypeFormat" width="15%"/>
                    </sd:DataGrid>
                </div>

                <table>
                    <tr>
                        <td align="center">
                            <div align="right">
                                <sx:ButtonSave onclick="page.saveNodeDeptUser();"/>
                                <sd:Button id="btnDelete" key="" onclick="page.deleteProcess()">
                                    <img src="share/images/icons/13.png" height="20" width="20"/>
                                    <span style="font-size:12px"><sd:Property>btnDelete</sd:Property></span>
                                </sd:Button>
                            </div>
                        </td>
                    </tr>
                </table>
            </sd:TitlePane>
        </td>


    </tr>
</table>
<script type="text/javascript">

    page.afterUpdateNodeToUpdateDeptUser = function(data) {
        var returnData = dojo.fromJson(data);
        var result = returnData.items;
        document.getElementById("viewNodeForm.nodeId").value = result[0];
        graph.updateFromForm();

        page.updateDeptUserOfNode();
    }

    page.saveNodeDeptUser = function() {
        var grid = dijit.byId("processGridId");
        var length = grid._by_idx.length.toString();
        if (length == "0") {
            msg.alert("Chưa chọn đơn vi, chuyên viên");
            return;
        }        
        var i = 0;
        var countMainProcess = 0;
        for (i = 0; i < length; i++) {
            var itemi = grid.getItem(i);
            if ("1" == itemi.processType.toString()) {
                countMainProcess++;
                break;
            }
        }
        if (countMainProcess == 0) {
            msg.alert("Bạn chưa chọn đơn vị, cá nhân xử lý chính", "Cảnh báo");
            return;
        }
        var nodeId = document.getElementById("viewNodeForm.nodeId").value;
        if (nodeId) {
            page.updateDeptUserOfNode();
        } else {
            sd.connector.post("flow!updateNode.do?" + token.getTokenParamString(), null, "viewNodeForm", null, page.afterUpdateNodeToUpdateDeptUser);
        }
    }

    page.updateDeptUserOfNode = function() {
        var nodeId = document.getElementById("viewNodeForm.nodeId").value;
        var content = dijit.byId("processGridId").vtGetTotalDataForPost("lstNodeDeptUser");
        sd.connector.post("flow!updateNodeDeptUser.do?nodeId=" + nodeId + "&" + token.getTokenParamString(), null, null, content, afterUpdateDB);
    }

    afterUpdateDB = function(data) {
        var obj = dojo.fromJson(data);
        var message = obj.customInfo;
        if (message != null && message.trim().length > 0) {
            msg.alert(message);
        }
    }

    setMainProcess = function(row) {
        var grid = dijit.byId("processGridId");
        var item = grid.getItem(row);
        if (item.processType == "1") {
            item.processType = "0"
        } else {
            var length = grid._by_idx.length.toString();
            var i = 0;
            var countMainProcess = 0;
            for (i = 0; i < length; i++) {
                var itemi = grid.getItem(i);
                if ("1" == itemi.processType.toString()) {
                    countMainProcess++;
                    break;
                }
            }
            if (countMainProcess == 0) {
                item.processType = "1";
            } else {
                msg.alert("Bạn đã chọn đơn vị, cá nhân xử lý chính", "Cảnh báo");
            }
        }
        grid.doApplyCellEdit(item.processType, row, "processType");
        grid.scrollToRow(grid._by_idx.length);
        grid.renderNoReload();
        hideGridHeader();

    }

    page.afterInit = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        var processContent = "";
        if (result[1] != "") {
            processContent = result[1];

        }
        document.getElementById("processContentForm.deadline").value = escapeHtml_(result[0]);
        document.getElementById("processContentForm.processContent").value = escapeHtml_(processContent);
    }

    page.InsertStaff = function() {
        var userGrid = dijit.byId("userGridId");
        var items = userGrid.vtGetCheckedItems();
        if (items.length == 0) {
            msg.alert("Bạn chưa chọn đối tượng để tham gia", "Cảnh báo");
            return;
        }
        var i = 0;
        var nodeId = document.getElementById("viewNodeForm.nodeId").value;
        var nodeName = document.getElementById("viewNodeForm.nodeName").value;
        var receiveGroupId = document.getElementById("receiveGroupId").value;
        var receiveGroup = document.getElementById("receiveGroup").value;
        for (i = 0; i < items.length; i++) {
            var item = items[i];
            if (!page.checkUserExist(item.userId)) {
                page.insertNewProcessItem(nodeId, nodeName, receiveGroupId, receiveGroup, item.userId, item.fullName);
            }
        }
        hideGridHeader();
    }

    page.checkMainProcessExist = function() {
        var i = 0;
        var grid = dijit.byId("processGridId");
        var length = grid._by_idx.length;
        for (i = 0; i < length; i++) {
            var item = grid.getItem(i);
            if (item.processType && "1" == item.processType.toString()) {
                return true;
            }
        }
        return false;
    }

    page.insertNewProcessItem = function(nodeId, nodeName, deptId, deptName, userId, userName) {
        var grid = dijit.byId("processGridId");
        var item = {
            nodeDeptUserId: null,
            nodeId: nodeId,
            nodeName: nodeName,
            deptId: deptId,
            deptName: deptName,
            userId: userId,
            userName: userName,
            processType: 0
        };

        if (!page.checkMainProcessExist()) {
            item.processType = 1;
        } else {
            item.processType = 0;
        }

        grid.store.newItem(item);
        grid.store.save();
        //grid.vtRefresh();
        //alert("end insert");

    }

    page.deleteProcess = function() {
        var processGrid = dijit.byId("processGridId");
        var items = processGrid.vtGetCheckedItems();

        if (items == null || items.length == 0) {
            msg.alert("Bạn chưa chọn đối tượng để xóa", "Cảnh báo");
            return;
        }

        msg.confirm('<sd:Property>confirm.delete</sd:Property>', "<sd:Property>confirm.title</sd:Property>", function() {
                    var grid = dijit.byId("processGridId");
                    var i = 0;
                    for (i = 0; i < items.length; i++) {
                        var item = items[i];
                        grid.store.deleteItem(item);
                    }
                    grid.scrollToRow(processGrid._by_idx.length);
                    hideGridHeader();
                });

            }

            page.onNodeClick = function(item, node, event) {
                var nodeId = document.getElementById("viewNodeForm.nodeId").value;
                document.getElementById("receiveGroupId").value = item.id;
                document.getElementById("receiveGroup").value = item.name;
                dijit.byId('userGridId').vtReload("departmentAction!getStaffOfDept.do?deptId=" + item.id + "&nodeId=" + nodeId);
            }


            page.onNodeChecked = function(item, node) {
                //
                // Luu cac item xu ly vao cac bien
                //
                var exist = page.checkDeptExist(item.id);
                if (!exist) {
                    var nodeId = document.getElementById("viewNodeForm.nodeId").value;
                    var nodeName = document.getElementById("viewNodeForm.nodeName").value;
                    var deptId = item.id;
                    var deptName = item.name;
                    page.insertNewProcessItem(nodeId, nodeName, deptId, deptName, null, null);
                    //page.addNewProcessItem();
                }
                hideGridHeader();
                return true;
            }

            page.onNodeUnchecked = function(item, node) {
                var grid = dijit.byId("processGridId");
                // check xem da ton tai tren grid chua
                var i = 0;
                for (i = 0; i < grid._by_idx.length; i++) {
                    var processItem = grid.getItem(i);
                    if (processItem.deptId.toString() == item.id.toString()) {
                        grid.store.deleteItem(processItem);
                    }
                }
                grid.scrollToRow(grid._by_idx.length);
                hideGridHeader();
                return true;
            }

            page.listLeader = function() {
                var id = document.getElementById("receiveGroupId").value;
                var objectId = document.getElementById("objectId").value;
                var objectType = document.getElementById("objectType").value;
                // var name=document.getElementById("receiveGroup").value;
                if (id == "") {
                    msg.alert("Bạn cần phải chọn đơn vị của lãnh đạo", "Cảnh báo");
                    return;
                }
                dijit.byId('userGridId').vtReload("departmentAction!getStaffOfDept.do?pos=LEADER&deptId=" + id + "&objectId=" + objectId + "&objectType=" + objectType);
            }

            page.listStaff = function() {
                var id = document.getElementById("receiveGroupId").value;
                var objectId = document.getElementById("objectId").value;
                var objectType = document.getElementById("objectType").value;
                // var name=document.getElementById("receiveGroup").value;
                if (id == "") {
                    msg.alert("Bạn cần phải chọn đơn vị của nhân viên", "Cảnh báo");
                    return;
                }
                dijit.byId('userGridId').vtReload("departmentAction!getStaffOfDept.do?pos=STAFF&deptId=" + id + "&objectId=" + objectId + "&objectType=" + objectType);
            }

            page.checkDeptExist = function(receiverGroupId) {
                var grid = dijit.byId("processGridId");
                // check xem da ton tai tren grid chua
                var exist = false;
                var i = 0;
                for (i = 0; i < grid._by_idx.length; i++) {
                    var processItem = grid.getItem(i);
                    if (processItem.deptId.toString() == receiverGroupId.toString()) {
                        exist = true;
                        break;
                    }
                }
                return exist;
            }

            page.getProcessItemByGroup = function(receiverGroupId) {
                var grid = dijit.byId("processGridId");
                var result = -1;
                var i = 0;
                for (i = 0; i < grid._by_idx.length; i++) {
                    var processItem = grid.getItem(i);
                    if (processItem.deptId == receiverGroupId && (processItem.userId == null || processItem.userId.toString() == "")) {
                        result = i;
                        break;
                    }
                }
                return result;
            }

            page.checkUserExist = function(userId) {
                var grid = dijit.byId("processGridId");
                // check xem da ton tai tren grid chua
                var exist = false;
                var i = 0;
                for (i = 0; i < grid._by_idx.length; i++) {
                    var processItem = grid.getItem(i);
                    //alert(userId +"-"+processItem.receiveUserId);
                    if (processItem.userId && processItem.userId.toString() == userId.toString()) {
                        exist = true;
                        break;
                    }
                }
                return exist;
            }

            page.afterUpdateAssign = function(data) {
                var obj = dojo.fromJson(data);
                var message = obj.customInfo;
                if (message != null && message.trim().length > 0) {
                    alert(message);
                }
                msg.info("Cập nhật thành công", "Thông báo");
            }

            page.updateAssign = function() {
                dijit.byId("processGridId").edit.apply();
                var objectId = document.getElementById("objectId").value;
                var objectType = document.getElementById("objectType").value;
                var length = dijit.byId("processGridId")._by_idx.length.toString();
                var content = dijit.byId("processGridId").vtGetTotalDataForPost("lstProcess");
                var date = new Date();
                var i = 0;
                var countMainProcess = 0;
                for (i = 0; i < length; i++) {
                    var item = dijit.byId("processGridId").getItem(i);
                    if ("1" == item.processType.toString()) {
                        countMainProcess++;
                    }
                    if (item.deadline != null && item.deadline.toString() != "") {
                        var deadline = new Date();
                        deadline.setISO8601(item.deadline.toString());

                        //var compare = dojo.date.compare(date,item.deadline);
                        //alert(compare);
                        if (date > deadline) {
                            msg.alert("Hạn xử lý phải >= ngày hôm nay", "Cảnh báo");
                            return false;
                        } else {
                        }
                    }
                    //linhdx Kiem tra don vi xu ly chinh

                    if (item.processType.toString() == "1") {
                        mainProcess = 1;
                    }
                }
                if (mainProcess) {
                    if (countMainProcess == 0) {
                        msg.alert("Chưa chọn cá nhân, đơn vị xử lý chính", "Cảnh báo");
                        return false;
                    } else if (countMainProcess > 1) {
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
                msg.confirm('<sd:Property>confirm.update</sd:Property>', "<sd:Property>confirm.title</sd:Property>", function() {
                            if (length == "0") {
                                sd.connector.post("assignDoc!updateDealine.do?objectId=" + objectId + "&objectType=" + objectType + "&" + token.getTokenParamString(), null, "processContentForm", null, page.afterUpdateAssign);
                            } else {
                                sd.connector.post("assignDoc!updateDealine.do?" + token.getTokenParamString(), null, "processContentForm", content, page.afterUpdateAssign);
                            }
                        });
                    }


                    hideGridHeader = function() {
                        var grid = document.getElementById("processGridId_VTGrid");
                        var divs = grid.getElementsByTagName("div");
                        if (divs != null && divs.length > 0) {
                            var header = divs[0];
                            header.style.display = "none";
                            //alert(header.innerHTML);
                            //alert("hide");
                        }
                    }

                    //hideGridHeader();

</script>