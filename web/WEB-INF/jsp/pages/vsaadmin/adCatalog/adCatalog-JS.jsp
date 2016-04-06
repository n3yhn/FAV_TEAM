<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<script type="text/javascript">
    var clientAction = "none";//"search","insert","update","delete","none";

    //********************************SEARCH************************************
    page.getRow = function(inRow){
        return inRow;
    }
    page.onSearch = function (){
        dijit.byId('gridId').vtReload("AdAction!onSearch.do","adForm") ;
    }
    //********************************INSERT************************************
    page.preInsert = function (){
        try{
            vt.connector.post("AdAction!prepareInsert.do?action=add","dialogDiv",null,null,page.showDialog('add'));
        }catch(err){
            alert(err.message);
        }
    }

    page.onContinue=function(){
        if(page.isChecked("gridUserId")){
            try{
                var elm = document.getElementById("panel1");
                if(elm.style.display=="block"){
                    elm.style.display="none";
                }
                document.getElementById("panel2").style.display="block";
                if(document.getElementById("panel2").innerHTML==""){
                    vt.connector.post("AdAction!prepareSelectRole.do?action=selectRole","panel2",null);
                }
                dijit.byId("adFormOnDialog.btnback").attr("disabled", false);
                dijit.byId("adFormOnDialog.btnContinue").attr("disabled", true);
                dijit.byId("adFormOnDialog.btnInsert").attr("disabled", false);
                try{
                    document.getElementById("step2").className="continue";
                }catch(err){

                }
            }catch(err){
                alert(err.message);
            }
        }
        else{
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            }
        }
        return false;
    }
    page.onBack=function(){
        try{
            var elm = document.getElementById("panel1");
            if(elm.style.display=="none"){
                elm.style.display="block";
            }
            var elm = document.getElementById("panel2");
            if(elm.style.display=="block"){
                elm.style.display="none";
            }
            dijit.byId("adFormOnDialog.btnContinue").attr("disabled", false);
            dijit.byId("adFormOnDialog.btnInsert").attr("disabled", true);
            dijit.byId("adFormOnDialog.btnback").attr("disabled", true);
            document.getElementById('spnsp').innerHTML = "<sd:Property>js.alertSelectUser</sd:Property>";
            try{
                document.getElementById("step2").className="step";
            }catch(err){
                alert(err.message);
            }
        }catch(err){
            //alert(err.message);
        }
    }
    page.onInsert = function (){
        if(page.isChecked("gridfrmRoleId")){
            var content1 = dijit.byId("gridfrmRoleId").vtGetCheckedDataForPost("roleGridForm", "isCheck");
            var content2 = dijit.byId("gridUserId").vtGetCheckedDataForPost("userGridForm", "isCheck");
            var param = {};
            for (var prop in content1){
                param[prop] = content1[prop];
            }
            for (var prop in content2){
                param[prop] = content2[prop];
            }
            dijit.byId('gridId').vtReload("AdAction!onInsert.do",null,param) ;
        }
        else{
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            }
        }
    }
    page.showDialog = function(action){
        try{
            var dialog = dijit.byId("dialogId");
            if(action=='grant'){
                dialog.titleNode.innerHTML = "<sd:Property>js.alertGrant</sd:Property>";
            }
            else if(action=='add'){
                dialog.titleNode.innerHTML = "<sd:Property>js.alertAdd</sd:Property>";
            }
            else if(action=='get'){
                dialog.titleNode.innerHTML = "<sd:Property>js.alertGet</sd:Property>";
            }
            else if(action == 'entrust'){
                dialog.titleNode.innerHTML = "<sd:Property>js.alertEntrust</sd:Property>";
            }
            //page.changeDialogButtonStyle(/*boolean isOnInsert*/true);
            dialog.show();
        }catch(err){
            alert(err.message);
        }
    }
    page.toDialog = function(id){
        try{
            var dialog = dijit.byId(id);
            dialog.titleNode.innerHTML = "<sd:Property>usersForm.addInfo</sd:Property>";
            dialog.show();
        }catch(err){
            alert(err.message);
        }
    }
    //********************************UPDATE************************************
    page.preUpdate = function (){
        clientAction = "update";
        page.changeDialogInputStyle();
        page.clearFormOnDialog();
        var dialog = dijit.byId("dialogId");
        dialog.titleNode.innerHTML = "<sd:Property>js.alertUpdate</sd:Property>";
        /*page.changeDialogButtonStyle(false);*/
        var row= dijit.byId("gridId").selection.getSelected()[0];
        if (row == undefined) {
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            }
            return false;
        };
        sd._("adFormOnDialog.email").setValue(row.email);
        sd._("adFormOnDialog.fullName").setValue(row.fullName);
        sd._("adFormOnDialog.status").setValue(row.status);
        sd._("adFormOnDialog.telephone").setValue(row.telephone);
        sd._("adFormOnDialog.userId").setValue(row.userId);
        sd._("adFormOnDialog.userName").setValue(row.userName);
                                                                        
        dialog.show();
    }
    page.searchOnDialog = function(action){
        //alert(action);
        var param = {};
        if(action=="grant" || action=="entrust" ||action=="get"){
            param = dijit.byId("gridId").vtGetCheckedDataForPost("usrGridForm", "isCheck");
        }
        dijit.byId('gridUserId').vtReload("AdAction!onSearch.do?param=" + action,"adFormOnDialog",param) ;
    }
    page.preGrantAd = function(){
        if(page.isChecked("gridId")){
            var content = dijit.byId("gridId").vtGetCheckedDataForPost("userGridForm", "isCheck");
            sd.connector.post("AdAction!prepareInsert.do?action=grant","dialogDiv",null,content,page.showDialog('grant'));
        }
        else{
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            }
        }
    }
    page.preGrant = function(action){
        if(page.isChecked("gridId")){
            if(action=="entrust"){
                if(page.isChecked("gridUsrOfAdId")){
                    var content = dijit.byId("gridId").vtGetCheckedDataForPost("userGridForm", "isCheck");
                    sd.connector.post("AdAction!prepareInsert.do?action=entrust","dialogDiv",null,content,page.showDialog('entrust'));
                }
                else{
                    try{
                        page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
                    }catch(err){
                        alert("<sd:Property>js.alertRecord</sd:Property>");
                    }
                }
            }
            else if(action=="get"){
                var content = dijit.byId("gridId").vtGetCheckedDataForPost("userGridForm", "isCheck");
                sd.connector.post("AdAction!prepareInsert.do?action=get","dialogDiv",null,content,page.showDialog('get'));
            }
        }
        else{
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            }
        }
    }

    page.onGrant = function (){
        if(dijit.byId("gridUserId").selection.getSelectedCount() >1){
            try{
                page.alert('message',"<sd:Property>js.uniqueRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.uniqueRecord</sd:Property>");
            }
            return false;
        }
        var row= dijit.byId("gridUserId").selection.getSelected()[0];
        if (row == undefined) {
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            }
            return false;
        };
        if(page.isChecked("gridId")){
            var content = dijit.byId("gridId").vtGetCheckedDataForPost("usrGridForm", "isCheck");
            dijit.byId('gridId').vtReload("AdAction!onGrant.do?userId=" + row.userId,null,content,page.afterCallback) ;
        }
        else{
            alert("Chưa chọn admin lĩnh vực cần ủy quyền");
        }
    }
    page.onEntrust = function (){
        if(dijit.byId("gridUserId").selection.getSelectedCount() >1){
            try{
                page.alert('message',"<sd:Property>js.uniqueRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.uniqueRecord</sd:Property>");
            }
            return false;
        }
        var row= dijit.byId("gridUserId").selection.getSelected()[0];
        if (row == undefined) {
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            }
            return false;
        };
        if(page.isChecked("gridUsrOfAdId")){
            var content1 = dijit.byId("gridUsrOfAdId").vtGetCheckedDataForPost("userGridForm", "isCheck");
            var content2 = dijit.byId("gridId").vtGetCheckedDataForPost("usrGridForm", "isCheck");
            var param = {};
            for (var prop in content1){
                param[prop] = content1[prop];
            }
            for (var prop in content2){
                param[prop] = content2[prop];
            }
            dijit.byId('gridUsrOfAdId').vtReload("AdAction!onEntrust.do?adId=" +row.userId ,null,param,page.afterCallback) ;
        }
        else{
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            }
        }
    }
    /* for get user to manage*/
    page.callGet = function(){
        var content1 = dijit.byId("gridId").vtGetCheckedDataForPost("usrGridForm", "isCheck");
        var content2 = dijit.byId("gridUserId").vtGetCheckedDataForPost("userGridForm", "isCheck");
        var param = {};
        for (var prop in content1){
            param[prop] = content1[prop];
        }
        for (var prop in content2){
            param[prop] = content2[prop];
        }
        //dijit.byId('gridUsrOfAdId').vtReload("AdAction!onGet.do","usersForm",param,page.afterCallback) ;
        dijit.byId('gridUsrOfAdId').vtReload("AdAction!onGet.do","usersForm",param,page.hiddenDialog('dialogId')) ;

    }
    page.hiddenDialog = function(id){
        dijit.byId(id).hide();
    }
    page.onGet = function (){
        if(page.isChecked("gridUserId")){
            try{
                txtContent = "<sd:Property>js.alertSureGet</sd:Property>"
                try{
                    var item = page.getItemByChecked("gridId");
                    txtContent+="<span style='color:red'>&nbsp;[" + item["userName"] + "]&nbsp;</span>";
                }catch(err){

                }
                page.confirmAsJS("<sd:Property>js.manage</sd:Property>", page.callGet);
            }catch(err){
                if (confirm("<sd:Property>js.manage</sd:Property>")){
                    page.callGet();
                }
            }
        }
        else{
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            }
        }
    }
    page.onListRole = function(){
        if(page.isChecked("gridId")){
            var content = dijit.byId("gridId").vtGetCheckedDataForPost("userGridForm", "isCheck");
            sd.connector.post("AdRoleAction.do","roleArea",null,content);
        }
        else{
            try{"<sd:Property>js.alertRecord</sd:Property>"
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            }
        }
    }
    page.onListUsrOfAd = function(){
        if(page.isCheckedUnique("gridId")){
            if(page.isChecked("gridId")){
                var content = dijit.byId("gridId").vtGetCheckedDataForPost("userGridForm", "isCheck");
                sd.connector.post("AdAction!prepareListUsrOfAd.do","roleArea",null,content);
            }
            else{
                try{
                    page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertRecord</sd:Property>");
                }
            }
        }
        else{
            try{
                page.alert('message',"<sd:Property>js.uniqueRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.uniqueRecord</sd:Property>");
            }
        }
    }
    page.callDel = function(){
        var content = dijit.byId("gridId").vtGetCheckedDataForPost("usrGridForm", "isCheck");
        dijit.byId('gridId').vtReload("AdAction!onDelete.do",null,content,page.afterCallback) ;
    }
    page.onDelete = function (){
        if(page.isChecked("gridId")){
            if (page.isEnableSelect("gridId")){
                page.alert("Thông báo", "<sd:Property>global.deleteWarning1</sd:Property>", "success");
                return false;
            }
            try{
                page.confirmAsJS("<sd:Property>js.alertDelete</sd:Property>", page.callDel);
            }catch(err){
                if (confirm("<sd:Property>js.alertDelete</sd:Property>")){
                    var content = dijit.byId("gridId").vtGetCheckedDataForPost("usrGridForm", "isCheck");
                    dijit.byId('gridId').vtReload("AdAction!onDelete.do",null,content,page.afterCallback) ;
                }
            }
        }
        else{
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            }
        }
    }
    page.unlockAd = function(){
        var content = dijit.byId("gridId").vtGetCheckedDataForPost("userGridForm", "isCheck");
        dijit.byId('gridId').vtReload("AdAction!onUnlock.do",null,content,page.afterCallback) ;
    }
    page.onEnable = function (){
        if(page.isChecked("gridId")){
            try{
                page.confirmAsJS("<sd:Property>js.alertEnable</sd:Property>", page.unlockAd);
            }catch(err){
                if (confirm("<sd:Property>js.alertEnable</sd:Property>")){
                    var content = dijit.byId("gridId").vtGetCheckedDataForPost("userGridForm", "isCheck");
                    dijit.byId('gridId').vtReload("AdAction!onUnlock.do",null,content,page.afterCallback) ;
                }
            }
        }
        else{
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            }
        }
    }
    page.lockAd = function(){
        var content = dijit.byId("gridId").vtGetCheckedDataForPost("userGridForm", "isCheck");
        dijit.byId('gridId').vtReload("AdAction!onLock.do",null,content,page.afterCallback) ;
    }
    page.onDisable = function (){
        if(page.isChecked("gridId")){
            try{
                page.confirmAsJS("<sd:Property>js.alertDisable</sd:Property>", page.lockAd);
            }catch(err){
                if (confirm("<sd:Property>js.alertDisable</sd:Property>")){
                    var content = dijit.byId("gridId").vtGetCheckedDataForPost("userGridForm", "isCheck");
                    dijit.byId('gridId').vtReload("AdAction!onLock.do",null,content,page.afterCallback) ;
                }
            }
        }
        else{
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            }
        }
    }


    //**************************************************************************
    page.changeDialogInputStyle = function(){
        if ((clientAction=="update") || (clientAction=="insert")){
            sd.widget.__setReadOnly("adFormOnDialog.userId",true);
        }
    }

    //isOnInsert = true (on Insert)
    page.changeDialogButtonStyle = function (/*boolean isOnInsert*/ isOnInsert){
        dijit.byId( "adFormOnDialog.btnInsert" ).domNode.style.display = isOnInsert?"":"none";
        dijit.byId( "adFormOnDialog.btnUpdate" ).domNode.style.display = isOnInsert?"none":"";
    }

    page.clearFormOnDialog = function(/*String*/){
    <%--  sd._("adFormOnDialog.birthPlace").setValue("");
      sd._("adFormOnDialog.cellphone").setValue("");
      sd._("adFormOnDialog.createDate").setValue("");
      sd._("adFormOnDialog.dateOfBirth").setValue("");
      sd._("adFormOnDialog.description").setValue("");--%>
              sd._("adFormOnDialog.email").setValue("");
    <%--  sd._("adFormOnDialog.fax").setValue("");--%>
            sd._("adFormOnDialog.fullName").setValue("");
    <%--  sd._("adFormOnDialog.identityCard").setValue("");
      sd._("adFormOnDialog.nationality").setValue("");
      sd._("adFormOnDialog.passportNumber").setValue("");
      sd._("adFormOnDialog.provinceCode").setValue("");--%>
              sd._("adFormOnDialog.status").setValue("");
              sd._("adFormOnDialog.telephone").setValue("");
    <%--sd._("adFormOnDialog.userId").setValue("");--%>
            sd._("adFormOnDialog.userName").setValue("");
        }

        page.convertStringToDate = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dgDate){
            try{
                var temp = dgDate.split("-");
                return new Date(temp[1]+"/"+temp[2].split("T")[0]+"/"+temp[0]);
            }catch(e){
                alert("function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss");
                return undefined;
            }
        }
        page.convertAdminStatusToStr=function(inDatum){
            switch(inDatum){
                case 2:
                    return 'Hoạt động';
                case -2:
                    return 'Bị khóa';
            }
        }
        page.convertStatusToStr=function(inDatum){
            switch(inDatum){
                case 1:
                    return 'Hoạt động';
                case 0:
                    return 'Bị khóa';
            }
        }
        page.convertGenderToStr=function(inDatum){
            switch(inDatum){
                case 1:
                    return 'Nam';
                case 0:
                    return 'Nữ';
            }
        }
        page.isChecked = function(gridId){
            var bReturn = false;
            var grid = dijit.byId(gridId);
            for (var idx in grid._by_idx){
                var item = grid._by_idx[idx].item;
                if (item["isCheck"]=="true"){
                    bReturn = true;
                    break;
                }
            }
            return bReturn;
        }
        page.isCheckedUnique = function(gridId){
            var bReturn = true;
            var count = 0;
            var grid = dijit.byId(gridId);
            for (var idx in grid._by_idx){
                var item = grid._by_idx[idx].item;
                if (item["isCheck"]=="true"){
                    count++;
                    if(count>=2){
                        bReturn = false;
                        break;
                    }
                }
            }
            return bReturn;
        }
        page.afterCallback = function(data, bSuccess){
            var txtContent = "";
            switch (data.customInfo[0]){
                case "update":
                    try{
                        page.alert('message',"<sd:Property>js.alertUpdate</sd:Property>",'success');
                    }catch(err){
                        alert("<sd:Property>js.alertUpdate</sd:Property>");
                    }
                    break;
                case "insert":
                    try{
                        page.alert('message',"<sd:Property>js.alertInsert</sd:Property>",'success');
                    }catch(err){
                        alert("<sd:Property>js.alertInsert</sd:Property>");
                    }
                    break;
                case "exist":
                    try{
                        page.alert('message',"<sd:Property>js.alertExist</sd:Property>",'error');
                    }catch(err){
                        alert("<sd:Property>js.alertExist</sd:Property>");
                    }
                    break;
                case "err":
                    try{
                        page.alert('message',"<sd:Property>js.alertError</sd:Property>",'error');
                    }catch(err){
                        alert(data.customInfo[1]);
                    }
                    break;
                case "delete":
                    try{
                        txtContent = "<div><sd:Property>result</sd:Property>:</div>";
                        txtContent += "<div style='text-align:left;height:100px;overflow:scroll;'>" + data.customInfo[1] + "</div>";
                        document.getElementById("adMsg").innerHTML = txtContent;
                        page.showMessageDialog("adDialogMsg");
                    }catch(err){
                        alert(data.customInfo[1]);
                    }
                    break;
                default:
                    try{
                        page.alert('message',"<sd:Property>js.alertNoError</sd:Property>",'error');
                    }catch(err){
                        alert("<sd:Property>js.alertNoError</sd:Property>");
                    }
                    break;
                }
            }
            page.selectAll = function(gridId){
                var grid = dijit.byId(gridId);
                for (var idx in grid._by_idx){
                    var item = grid._by_idx[idx].item;
                    //item["isCheck"]="checked";
                    item["isCheck"]="true";
                }
                grid.render();

            }
            page.getItemByChecked = function(gridId){
                var grid = dijit.byId(gridId);
                for (var idx in grid._by_idx){
                    var item = grid._by_idx[idx].item;
                    if(item["isCheck"]=="true")
                        return item;
                }
            }
            page.unSelectAll = function(gridId){
                var grid = dijit.byId(gridId);
                for (var idx in grid._by_idx){
                    var item = grid._by_idx[idx].item;
                    item["isCheck"]="";
                }
                grid.render();
            }
            page.showArea = function(id){
                try{
                    if(document.getElementById(id).style.display=="none"){
                        document.getElementById(id).style.display = "block";
                    }
                    if(document.getElementById(id).style.visibility=="hidden"){
                        document.getElementById(id).style.visibility = "visible";
                    }
                }catch(err){
                    //alert(err.message);
                }
            }
            page.clearArea = function(id){
                try{
                    document.getElementById(id).style.display="none";
                }catch(err){
             
                }
            }
            page.clearForm=function(){
                try{
                    sd._("adForm.email").setValue("");
                    sd._("adForm.fullName").setValue("");
                    sd._("adForm.status").setValue("");
                    sd._("adForm.telephone").setValue("");
                    sd._("adForm.userName").setValue("");
                    sd._("adForm.userName").setValue("");
                    sd._("adForm.deptId").setValue("");
                    sd._("deptTreeBelow").setValue("");
                }catch(err){

                }
            }
            page.onSearchRole = function (){
                dijit.byId('gridfrmRoleId').vtReload("AdRoleAction!onSearchRoleWhenAddingAd.do","adFormRoleOnDialog") ;
            }
            page.getIndex = function(inRowIndex){
                return inRowIndex + 1;
            }
            page.enterOnDialog = function(id,action){
                try{
                    for(var i in connections){
                        dojo.disconnect(connections[i]);
                    }
                    var el = dojo.connect(dojo.byId(id), 'onkeypress', function (evt) {
                        key = evt.keyCode;
                        if(key == dojo.keys.ENTER) {
                            try{
                                page.searchOnDialog(action);
                            }catch(e){
                                alert(e.message);
                            }
                        }
                    });
                    connections.push(el);
                }catch(err){
                    alert(err.message);
                }
            }
            page.showMessageDialog = function(id){
                try{
                    var dialog = dijit.byId(id);
                    dialog.titleNode.innerHTML = "<sd:Property>message</sd:Property>";
                    dialog.show();
                }catch(err){
                    alert(err.message);
                }
            }
            page.clearTree = function(){
                try{
                    sd._("adForm.deptId").setValue("");
                    sd._("deptTreeBelow").setValue("");
                }catch(err){
            
                }
            }
            page.clearTreeOnDialog = function(){
                try{
                    sd._("adFormOnDialog.deptId").setValue("");
                    sd._("deptTree").setValue("");
                }catch(err){

                }
            }
            page.convertDateToString = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dgDate){
                try{
                    var temp = dgDate.split("-");
                    return temp[2].split("T")[0]+"/"+temp[1]+"/"+temp[0];
                }catch(e){
                    //alert("function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss");
                    return undefined;
                }
            }
            page.view = function(inRow){
                var item = dijit.byId("gridId").getItem(inRow);
                if(item != null){
                    var url = "<a href='#' style='text-decoration:none;' title='Thông tin thêm' onClick='page.clickIconView(" + inRow + ")'>" + item.userName +"</a>";
                    return url;
                }
            }
            page.viewOnDlg = function(inRow){
                var item = dijit.byId("gridUserId").getItem(inRow);
                if(item != null){
                    var url = "<a href='#' style='text-decoration:none;' title='Thông tin thêm' onClick='page.clickIconViewOnDlg(" + inRow + ")'>" + item.userName +"</a>";
                    return url;
                }
            }
            page.getRow = function(inRow){
                return inRow;
            }
            page.clickIconView = function(row){
                var item = dijit.byId("gridId").getItem(row);
                if(item!=null){
                    sd.connector.post("AdAction!onUserDetail.do?userId=" + item.userId,"dlgDiv",null,null,page.toDialog("dlgId"));
                }
            }
            page.clickIconViewOnDlg = function(row){
                var item = dijit.byId("gridUserId").getItem(row);
                if(item!=null){
                    sd.connector.post("AdAction!onUserDetail.do?userId=" + item.userId,"dlgDiv",null,null,page.toDialog("dlgId"));
                }
            }
            page.viewUsr = function(inRow){
                var item = dijit.byId("gridUsrOfAdId").getItem(inRow);
                if(item != null){
                    var url = "<a href='#' style='text-decoration:none;' title='Thông tin thêm' onClick='page.clickIconViewUsr(" + inRow + ")'>" + item.userName +"</a>";
                    return url;
                }
            }
            page.clickIconViewUsr = function(row){
                var item = dijit.byId("gridUsrOfAdId").getItem(row);
                if(item!=null){
                    sd.connector.post("AdAction!onUserDetail.do?userId=" + item.userId,"dlgDiv",null,null,page.toDialog("dlgId"));
                }
            }
            page.urlInfoDlg = function(inRow){
                try{
                    var row = dijit.byId("gridfrmRoleId").getItem(inRow);
                    if(row != null){
                        var url = "<div style='text-align:center;'><img src='${contextPath}/share/images/icons/bookmark.png' width='20px' height='20px' title='Xem chi tiết' onClick='page.infoRoleDlg(" + inRow + ")' /></div>";
                        return url;
                    }
                }catch(err){
                    alert(err.message);
                }
            }

            page.onExcelUser=function(){
                if(page.isChecked("gridId")){

                    var content = dijit.byId("gridId").vtGetCheckedDataForPost("usrGridForm", "isCheck");
                    window.location = "AdAction!onExcelUser.do?userId=" + content['usrGridForm.userId'];
                }
                else{
                    try{
                        page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
                    }catch(err){
                        alert("<sd:Property>js.alertRecord</sd:Property>");
                    };
                }
            }

            page.onExcelRole=function(){
                if(page.isChecked("gridId")){

                    var content = dijit.byId("gridId").vtGetCheckedDataForPost("usrGridForm", "isCheck");
                    window.location = "AdAction!onExcelRole.do?userId=" + content['usrGridForm.userId'];
                }
                else{
                    try{
                        page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
                    }catch(err){
                        alert("<sd:Property>js.alertRecord</sd:Property>");
                    };
                }
            }


            page.callDeleteUserFromManager = function(){
                var content1 = dijit.byId("gridUsrOfAdId").vtGetCheckedDataForPost("userGridForm", "isCheck");
                var content2 = dijit.byId("gridId").vtGetCheckedDataForPost("usrGridForm", "isCheck");
                var param = {};
                for (var prop in content1){
                    param[prop] = content1[prop];
                }
                for (var prop in content2){
                    param[prop] = content2[prop];
                }
                dijit.byId("gridUsrOfAdId").vtReload("AdAction!onDeleteUserFromManager.do","usersForm",param) ;
            }

            page.onDeleteUserFromManager = function(){
                if(page.isChecked("gridUsrOfAdId")){
                    try{
                        page.confirmAsJS("<sd:Property>js.manage</sd:Property>", page.callDeleteUserFromManager);
                    }catch(err){
                        if (confirm("<sd:Property>js.manage</sd:Property>")){
                            page.callDeleteUserFromManager();
                        }
                    }

                        
                }
            }

            page.isEnableSelect = function(gridId){
                var bReturn = false;
                var grid = dijit.byId(gridId);
                for (var idx in grid.store._arrayOfAllItems){
                    var item = grid.store._arrayOfAllItems[idx];
                    if ((item["isCheck"][0] == "true" || item["isCheck"][0]) && item["userRight"][0] == "2"){
                        bReturn = true;
                        break;
                    }
                }
                return bReturn;
            }
</script>