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
        try{
            dijit.byId('gridId').vtReload("application!onSearch.do","applicationsForm",null) ;
            try{
                document.getElementById("objectArea").style.visibility = "hidden";
            }catch(err){
                
            }
        }catch(err){
            alert(err.message);
        }
    }
    //********************************INSERT************************************
    page.preInsert = function (){
        clientAction = "insert";
        page.changeDialogInputStyle();
        page.clearFormOnDialog();
        var dialog = dijit.byId("dialogId");
        dialog.titleNode.innerHTML = "<sd:Property>btnAdd</sd:Property>";
        //page.changeDialogButtonStyle(/*boolean isOnInsert*/true);
        dialog.show();
        try{
            dijit.byId("applicationsFormOnDialog.appCode").focus();
            page.onCustomEnter("applicationsFormOnDialog", page.onInsert);
        }catch(err){

        }
    }
    page.onInsert = function (){
        if(page.inValidate()){
            //do submit
            dijit.byId('gridId').vtReload("application!onInsert.do","applicationsFormOnDialog",null,page.afterCallback) ;
        }
    }
    page.submitModifiedData=function(){
        if(page.inValidate()){
            //do submit
            dijit.byId('gridId').vtReload("application!onApply.do?"+token.getTokenParamString(),"applicationsFormOnDialog",null,page.showMessage) ;
            page.close();
        }
    }
    page.onListObj = function(inRow){
        var areaId = "objectArea";
        var objectDialogDiv = "dialogObjectDiv";
        try{
            page.clearArea("importArea");
        }catch(err){

        }
        try{
            var row = dijit.byId("gridId").getItem(inRow);
            document.getElementById("appId").value = row.appId;
            if(row != null){
                var dialog = dijit.byId("applicationsModuleId");
                dialog.titleNode.innerHTML = "Chức năng";
                sd.connector.post('object.do?appId='+ row.appId + "&appCode=" + row.appCode +"&appName=" + row.appName ,areaId,null,page.showArea("objectArea"));
                sd.connector.post('object!refreshObjectDialogPage.do?appId='+ row.appId + "&appCode=" + row.appCode +"&appName=" + row.appName ,objectDialogDiv,null,null);

                dialog.show();
            }
        }catch(err){

        }
    }

    page.onListObject = function(){
        var areaId = "objectArea";
        try{
            page.clearArea("importArea");
                
        }catch(err){

        }
        try{
            var count = 0;
            var grid = dijit.byId("gridId");
            var temp;
            for (var idx in grid._by_idx){
                var item = grid._by_idx[idx].item;
                if (item["isCheck"]=="true"){
                    count++;
                    temp = item;
                    if(count>=2){
                        break;
                    }
                }
            }
            if(count==1){
                try{
                    sd.connector.post('object.do?appId='+ temp["appId"] + "&appCode=" + temp["appCode"] +"&appName=" + temp["appName"] ,areaId,null,page.showArea("objectArea"));
                }catch(e){
                    alert(e.message);
                }
            }
            else if(count>1){

                try{
                    page.alert('message',"<sd:Property>js.alertUniqueRecord</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertUniqueRecord</sd:Property>");
                }
            }
            else{
                try{
                    page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertRecord</sd:Property>");
                }
            }
        }catch(e){
            alert(e.message);
        }
        return false;
                
    }

    //********************************UPDATE************************************
    page.preUpdate = function (){
        clientAction = "update";
        page.changeDialogInputStyle();
        page.clearFormOnDialog();
        var dialog = dijit.byId("dialogId");
        dialog.titleNode.innerHTML = "<sd:Property>js.titleUpdate</sd:Property>";
        //page.changeDialogButtonStyle(false);
        try{
            var count = 0;
            var grid = dijit.byId("gridId");
            var temp;
            for (var idx in grid._by_idx){
                var item = grid._by_idx[idx].item;
                if (item["isCheck"]=="true"){
                    count++;
                    temp = item;
                    if(count>=2){
                        break;
                    }
                }
            }
            if(count==1){
                try{
                    sd._("applicationsFormOnDialog.appCode").setValue(temp["appCode"]);
                    sd._("applicationsFormOnDialog.appId").setValue(temp["appId"]);
                    sd._("applicationsFormOnDialog.appName").setValue(temp["appName"]);
                    document.getElementById("applicationsFormOnDialog.description").value=temp["description"];
                    sd._("applicationsFormOnDialog.status").setValue(temp["status"]);
                    dialog.show();
                       
                    try{
                        dijit.byId("applicationsFormOnDialog.appCode").focus();
                    }catch(err){

                    }
                }catch(e){
                    alert(e.message);
                }
            }
            else if(count>1){

                try{
                    page.alert('message',"<sd:Property>js.alertUniqueRecord</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertUniqueRecord</sd:Property>");
                }
            }
            else{
                try{
                    page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertRecord</sd:Property>");
                };
            }
        }catch(e){
            alert(e.message);
        }
        return false;
    }

    page.close = function(){
        dijit.byId("dialogId").hide();
    }
    page.onUpdate = function (){
        if(page.inValidate()){
            dijit.byId('gridId').vtReload("application!onUpdate.do","applicationsFormOnDialog",null,page.afterCallback) ;
        }
    }
    page.preImport=function(){
        try{
            page.showArea("importArea");
            page.clearArea("objectArea");
                
        }catch(err){

        }
    }
    page.onImport = function(){
        if(page.validateImport("client")){
            try{
                document.getElementById("frmImport").target = 'uploadFrame';
                document.getElementById("frmImport").submit();
            }catch(err){
                alert(err.message);
            }

        }
        else{
            alert("File phải có dạng .xml hoặc .xls");
        }
    }
    page.onExport = function(){
        if(page.isChecked("gridId")){
            try{
                page.clearArea("importArea");
                var content = dijit.byId("gridId").vtGetCheckedDataForPost("gridApplicationForm","isCheck");
                sd.connector.post("application!onExport.do","objectArea",null,content);
                page.showArea("objectArea");
            }catch(err){
                alert(err.message);
            }
        }
        else{
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            };
        }
    }
    //********************************DELETE************************************
    page.call=function(){
        var content = dijit.byId("gridId").vtGetCheckedDataForPost("gridApplicationForm","isCheck");
        dijit.byId('gridId').vtReload("application!onDelete.do?"+token.getTokenParamString(),null,content,page.afterCallback) ;
    }

    page.onExcel=function(){
        if(page.isChecked("gridId")){
            var content = dijit.byId("gridId").vtGetCheckedDataForPost("gridApplicationForm","isCheck");
            window.location = "application!onExcel.do?appId=" + content['gridApplicationForm.appId'];
        }
        else{
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            };
        }
    }
    page.showMessage = function(data, mess){
        var status="";
        try{
            var obj = JSON.parse(data);
            status = obj.customInfo;
        }catch(e){
            status=data.customInfo;
        }
        
        if(status.toString().indexOf('success') >= 0){
            document.getElementById("lblSuccessMessage").innerHTML='<sd:Property>alert.updatesucess</sd:Property>';
            document.getElementById("tblSuccessMessage").style.display='';
            window.setTimeout( "hideMessage()", 5000 );
        }else if(status.toString().indexOf('error') >= 0){
            document.getElementById("tblErrorMessage").style.display='';
            window.setTimeout( "hideMessage()", 5000 );
        }else if(status.toString().indexOf('deleteSucc') >= 0){
            document.getElementById("lblSuccessMessage").innerHTML="Xóa ứng dụng thành công";
            document.getElementById("tblSuccessMessage").style.display='';
            window.setTimeout( "hideMessage()", 5000 );
        }else if(status.toString().indexOf('locksucc') >= 0){
            document.getElementById("lblSuccessMessage").innerHTML="Khóa ứng dụng thành công";
            document.getElementById("tblSuccessMessage").style.display='';
            window.setTimeout( "hideMessage()", 5000 );
        }else if(status.toString().indexOf('unLocksucc') >= 0){
            document.getElementById("lblSuccessMessage").innerHTML="Mở khóa ứng dụng thành công";
            document.getElementById("tblSuccessMessage").style.display='';
            window.setTimeout( "hideMessage()", 5000 );
        }
    }
    page.onDelete = function (){
        var appGrid = dijit.byId('gridId');
        if (!appGrid.vtIsChecked()){
            alert('<sd:Property>alert.select</sd:Property>');
            return false;
        }else{
            if (confirm("<sd:Property>confirm.delete</sd:Property>")){
                var content = dijit.byId("gridId").vtGetCheckedDataForPost("applicationFormOnGrid");
                dijit.byId('gridId').vtReload("application!onDelete.do?"+token.getTokenParamString(),null,content,page.showMessage) ;
            }
            return true;
        }
        if(page.isChecked("gridId")){
            if (page.isEnableSelect("gridId")){
                page.alert("Thông báo", "<sd:Property>global.deleteWarning1</sd:Property>", "success");
                return false;
            }
        
            try{
                page.onExcel();
                page.confirmAsJS("<sd:Property>js.alertDelete</sd:Property>",page.call);
            }catch(err){
                if (confirm("<sd:Property>js.alertDelete</sd:Property>")){
                    var content = dijit.byId("gridId").vtGetCheckedDataForPost("applicationFormOnGrid");
                    dijit.byId('gridId').vtReload("application!onDelete.do?"+token.getTokenParamString(),null,content,page.afterCallback) ;
                }
            }
        }
        else{
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
                alert("<sd:Property>js.alertRecord</sd:Property>");
            };
        }
        return false;
    }

    //**************************************************************************
    page.changeDialogInputStyle = function(){
        if (clientAction=="update"){
    <%--sd.widget.__setReadOnly("applicationsFormOnDialog.appId",true);--%>
                sd.widget.__setReadOnly("applicationsFormOnDialog.appCode",true);
            }
            else if (clientAction=="insert"){
                sd.widget.__setReadOnly("applicationsFormOnDialog.appCode",false);
            }
        }

        //isOnInsert = true (on Insert)
        page.changeDialogButtonStyle = function (/*boolean isOnInsert*/ isOnInsert){
            dijit.byId( "applicationsFormOnDialog.btnInsert" ).domNode.style.display = isOnInsert?"":"none";
            if (isOnInsert){
                sd.widget.__setReadOnly("applicationsFormOnDialog.status",false);
            }
            else{
                sd.widget.__setReadOnly("applicationsFormOnDialog.status",true);
            }

            dijit.byId( "applicationsFormOnDialog.btnUpdate" ).domNode.style.display = isOnInsert?"none":"";
        }

        page.clearFormOnDialog = function(/*String*/){
            sd._("applicationsFormOnDialog.appCode").setValue("");
            sd._("applicationsFormOnDialog.appId").setValue("");
            sd._("applicationsFormOnDialog.appName").setValue("");
            sd._("applicationsFormOnDialog.description").setValue("");
    <%--sd._("applicationsFormOnDialog.status").setValue("");--%>
            try{
                document.getElementById("messageArea").innerHTML="";
            }catch(err){
                //do nothing
            }
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
        page.validateCheckOne=function(formId,chk_radioTagName){
            var isChoice = false;
            var motherTab = dijit.byId(formId);
            var inputs= motherTab.getElementsByTagName('input');
            for(var i=0; i < inputs.length; i++){
                if(inputs[i].name.indexOf(chk_radioTagName)!=-1){
                    if (inputs[i].checked == true)
                    {
                        isChoice = true;
                        break;
                    }
                }
            }

            if(!isChoice){
                alert("!");
                return false;
            }
            return confirm("<sd:Property>js.alertDelete</sd:Property>");

        }
        page.checkAll=function(obj ,formId ,chk_radioTagName){
            var state=obj.checked;
            var merTable=dijit.byId(formId);
            var inputs = merTable.getElementsByTagName('input');
            var i=0;
            for(i=0;i<inputs.length;i++){
                if(inputs[i].name.indexOf(chk_radioTagName) >-1 ){
                    //chuyển trạng thái checkbox
                    inputs[i].checked=state;
                }
            }
        }
        page.getSelectedValue = function(selectTagId){
            var value;
            var index=0;
            if(dijit.byId(selectTagId)){
                index = dijit.byId(selectTagId).selected;
            }
            value = dijit.byId(selectTagId).options[index].value;
            return value;
        }
        page.clearArea = function(id){
            try{
                document.getElementById(id).style.display="none";
            }catch(err){
            
            }
        }
        page.showArea = function(id){
            dijit.byId('applicationsModuleId').show();
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
        page.convertStatusToStr=function(cellValue){
            switch(cellValue){
                case 1:
                    return 'Hoạt động';
                case 0:
                    return 'Bị khóa';
            }
        }
        page.inValidate=function(){
            var appCode = null;
            var status = null;
            var appName = null;
            var check=true;
            try{
                appCode = dijit.byId('applicationsFormOnDialog.appCode').getValue();
                status = dijit.byId('applicationsFormOnDialog.status').getValue();
                appName = dijit.byId('applicationsFormOnDialog.appName').getValue();
            }catch(err){
                alert(err.toString());
            }
            if(appCode==null||appCode==""){

                try{
                    page.alert('message',"<sd:Property>js.alertCode</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertCode</sd:Property>");
                };
                dijit.byId('applicationsFormOnDialog.appCode').focus();
                check=false;
            }
            else if(appName==null||appName==""){

                try{
                    page.alert('message',"<sd:Property>js.alertName</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertName</sd:Property>");
                };
                dijit.byId('applicationsFormOnDialog.appName').focus();
                check=false;
            }
            else if(status==null||status==""){

                try{
                    page.alert('message',"<sd:Property>js.alertStatus</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertStatus</sd:Property>");
                };
                dijit.byId('applicationsFormOnDialog.status').focus();
                check=false;
            }
            return check;
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
    <%--   for (var idx in grid.store._arrayOfAllItems){
           var item = grid.store._arrayOfAllItems[idx];
           if (item["isCheck"][0] == "true" || item["isCheck"][0] ){
               bReturn = true;
               break;
           }
       }--%>
               return bReturn;
           }
           page.getStrAppName = function(){
               try{
                   var grid = dijit.byId("gridId");
                   var temp="";
                   var count =0;
                   for (var idx in grid._by_idx){
                       var item = grid._by_idx[idx].item;
                       if (item["isCheck"]=="true"){
                           count++;
                           if(count>8){
                               break;
                           }
                           temp += item["appName"] + ",";
                       }
                   }
                   var index = temp.lastIndexOf(',');
                   if(index>0){
                       temp = temp.substring(0,index);
                   }
               }catch(err){
                   alert(err.message);
               }
               return temp;
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
                           if (data.customInfo[1].length > 0){
                               txtContent = "<div><sd:Property>result</sd:Property>:</div>";
                               txtContent += "<div style='text-align:left;overflow:auto;'>" + data.customInfo[1] + "</div>";
                               document.getElementById("message").innerHTML = txtContent;
                               page.showMessageDialog("msg");
                           }
                           else{
                               page.alert('message',"<sd:Property>msg.deleteOk</sd:Property>",'error');
                           }
                       }catch(err){
                           alert(data.customInfo[1]);
                       }
                       break;
                   case "locking":
                       try{
                           page.alert('message',"<sd:Property>js.alertLocking</sd:Property>",'error');
                       }catch(err){
                           alert("<sd:Property>js.alertLocking</sd:Property>");
                       }
                       break;
                   case "insertFunction":
                       try{
                           page.alert('message',"<sd:Property>js.alertInsert</sd:Property>",'success');
                           page.clearObjectFormOnDialog();
                       }catch(err){
                           alert("<sd:Property>js.alertInsert</sd:Property>");
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
               page.urlEditApp = function(inRow){
                   try{
                       var row = dijit.byId("gridId").getItem(inRow);
                       if(row != null){
                           var url = "<div style='text-align:center;'>" +
                               "<img src='share/img/edit.png' width='17px'" +
                               "height='17px' title='Cập nhật thông tin'" +
                               " onClick='page.editApp(" + inRow + ")' /></div>";
                           return url;
                       }
                   }catch(err){
                       alert(err.message);
                   }
               }
               page.urlListObj = function(inRow){
                   try{
                       var row = dijit.byId("gridId").getItem(inRow);
                       if(row != null){
                           var url = "<div style='text-align:center;'>";
                           url+="<img src='share/images/icons/a3.png' width='17px' \n\
                                     height='17px' title='<sd:Property>applicationsForm.moduleList</sd:Property>'\n\
                onClick='page.onListObj(" + inRow + ")' /></div>";
                           return url;
                       }
                   }catch(err){
                       alert(err.message);
                   }
               }
               page.editApp = function(inRow){
                   clientAction = "update";
                   page.changeDialogInputStyle();
                   page.clearFormOnDialog();
                   var dialog = dijit.byId("dialogId");
                   dialog.titleNode.innerHTML = "Sửa"
                   //page.changeDialogButtonStyle(false);
                   try{
                       var row = dijit.byId("gridId").getItem(inRow);
                       sd._("applicationsFormOnDialog.appCode").setValue(row.appCode);
                       sd._("applicationsFormOnDialog.appId").setValue(row.appId);
                       sd._("applicationsFormOnDialog.appName").setValue(row.appName);
                       document.getElementById("applicationsFormOnDialog.description").value=row.description;
                       sd._("applicationsFormOnDialog.status").setValue(row.status);
                       dialog.show();
                       try{
                           dijit.byId("applicationsFormOnDialog.appCode").focus();
                           //page.onCustomEnter("applicationsFormOnDialog", page.onUpdate);
                       }catch(err){

                       }
                   }catch(e){
                       alert(e.message);
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
               page.unSelectAll = function(gridId){
                   var grid = dijit.byId(gridId);
                   for (var idx in grid._by_idx){
                       var item = grid._by_idx[idx].item;
                       item["isCheck"]="";
                   }
                   grid.render();
               }
               page.validateImport = function(id){
                   var check = true;
                   try{
                       var filePath = document.getElementById(id).value;
                       var ext = filePath.substr(filePath.toString().lastIndexOf('.')+1, filePath.length-filePath.toString().lastIndexOf('.'));
                       //alert(ext.toString().toLowerCase());
                       //if (page.trim(ext.toString().toLowerCase())!="xls"&& page.trim(ext.toString().toLowerCase())!="xml") {
                       if (page.trim(ext.toString().toLowerCase())!="xml" && page.trim(ext.toString().toLowerCase())!="xls") {
                           check = false ;
                       }
                   }catch(err){
                       alert(err.message);
                   }
                   return check;
               }
   
               page.checkNumber = function(keyCode)
               {
                   var pattern =/^[0-9]*$/;
                   var str = String.fromCharCode(keyCode);
                   return pattern.test(str);
               }
               //trim in js
               page.trim = function(str)
               {
                   if(typeof str != 'string')
                       return null;
                   return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
               }
               //trim in js
               page.checkSpace = function(str)
               {
                   var re = /[\s]+/;
                   return re.test(str);
               }
               afterImport = function(){
                   dijit.byId("gridUploadResultId").vtReload("application!onLoadResult.do", null);
               }
               page.getIndex = function(inRowIndex){
                   return inRowIndex + 1;
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
               page.unlockApp = function(){
                   var content = dijit.byId("gridId").vtGetCheckedDataForPost("applicationsFormOnDialog");
                   dijit.byId('gridId').vtReload("application!onUnlock.do?"+token.getTokenParamString(),"applicationsForm",content,page.afterCallback) ;
               }
               page.onEnableApp = function (){
                   var appGrid = dijit.byId('gridId');
                   if (!appGrid.vtIsChecked()){
                       alert('<sd:Property>alert.select</sd:Property>');
                       return false;
                   }else{
                       if (confirm("<sd:Property>confirm.enable</sd:Property>")){
                           var content = dijit.byId("gridId").vtGetCheckedDataForPost("applicationFormOnGrid");
                           dijit.byId('gridId').vtReload("application!onUnlock.do?"+token.getTokenParamString(),"applicationsForm",content,page.showMessage) ;
                       }
                       return true;
                   }
                   if(page.isChecked("gridId")){
                       try{
                           page.confirmAsJS("<sd:Property>js.alertEnable</sd:Property>", page.unlockApp);
                       }catch(err){
                           if (confirm("<sd:Property>js.alertEnable</sd:Property>")){
                               var content = dijit.byId("gridId").vtGetCheckedDataForPost("gridApplicationForm", "isCheck");
                               dijit.byId('gridId').vtReload("application!onUnlock.do?"+token.getTokenParamString(),null,content,page.afterCallback) ;
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
               page.lockApp = function(){
                   var content = dijit.byId("gridId").vtGetCheckedDataForPost("applicationsFormOnDialog");
                   dijit.byId('gridId').vtReload("application!onLock.do?"+token.getTokenParamString(),"applicationsForm",content,page.afterCallback) ;
               }
               page.onDisableApp = function (){
                   var appGrid = dijit.byId('gridId');
                   if (!appGrid.vtIsChecked()){
                       alert('<sd:Property>alert.select</sd:Property>');
                       return false;
                   }else{
                       if (confirm("<sd:Property>confirm.disable</sd:Property>")){
                           var content = dijit.byId("gridId").vtGetCheckedDataForPost("applicationFormOnGrid");
                           dijit.byId('gridId').vtReload("application!onLock.do?"+token.getTokenParamString(),"applicationsForm",content,page.showMessage) ;
                       }
                       return true;
                   }
                   if(page.isChecked("gridId")){
                       var strComment = prompt("Hãy nhập lý do bạn muốn khóa ứng dụng. Thông báo sẽ được hiển thị khi người dùng đăng nhập hệ thống", "Tạm ngừng hệt thống để phục vụ nâng cấp");
                       if (strComment.length > 0){
                           sd.$("applicationsForm.lockDescription").value = strComment;
                           try{
                               page.confirmAsJS("<sd:Property>js.alertDisable</sd:Property>", page.lockApp);
                           }catch(err){
                               if (confirm("<sd:Property>js.alertDisable</sd:Property>")){
                                   var content = dijit.byId("gridId").vtGetCheckedDataForPost("gridApplicationForm", "isCheck");
                                   dijit.byId('gridId').vtReload("application!onLock.do?"+token.getTokenParamString(),"applicationsForm",content,page.afterCallback) ;
                               }
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

               page.isEnableSelect = function(gridId){
                   var bReturn = false;
                   var grid = dijit.byId(gridId);
                   for (var idx in grid.store._arrayOfAllItems){
                       var item = grid.store._arrayOfAllItems[idx];
                       if ((item["isCheck"][0] == "true" || item["isCheck"][0]) && item["status"][0] == "1"){
                           bReturn = true;
                           break;
                       }
                   }
                   return bReturn;
               }
</script>