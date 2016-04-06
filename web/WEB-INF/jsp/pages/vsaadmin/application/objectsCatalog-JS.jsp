<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<script type="text/javascript">

    var clientAction = "none";//"search","insert","update","delete","none";

    page.selectAll = function(gridId){
        var grid = document.getElementById(gridId);
        var lstItem = grid.getElementsByTagName("input");
        if(lstItem == null || lstItem.length ==0)
            return;
        for(var i=0;i<lstItem.length;i++){
            if(lstItem[i].getAttribute("type") == "checkbox"){
                lstItem[i].checked = true;
            }
        }
    }
    
    page.unSelectAll = function(gridId){
        var grid = document.getElementById(gridId);
        var lstItem = grid.getElementsByTagName("input");
        if(lstItem == null || lstItem.length ==0)
            return;
        for(var i=0;i<lstItem.length;i++){
            if(lstItem[i].getAttribute("type") == "checkbox"){
                lstItem[i].checked = false;
            }
        }
    }
    //********************************SEARCH************************************
    page.onSearchObject = function (){
        dijit.byId('gridObjectId').vtReload("ObjectsAction!onSearch.do","objectsForm") ;
    }
    //********************************INSERT************************************
    page.preInsertObject = function (){
        clientAction = "insert";
        page.changeObjectDialogInputStyle();
        page.clearObjectFormOnDialog();
        var dialog = dijit.byId("dialogObjectId");
        dialog.titleNode.innerHTML = "<sd:Property>btnInsert</sd:Property>";
        page.changeObjectDialogButtonStyle(/*boolean isOnInsert*/true);
        dialog.show();
        try{
            dijit.byId("objectsFormOnDialog.objectName").focus();
            page.onCustomEnter("objectsFormOnDialog", page.onInsertObject);
            
        }catch(err){
        }
    }
    
    page.onInsertObject = function (){
        if(page.validatedInput()){
            var appId = document.getElementById("appId").value;
            sd._('objectsFormOnDialog.appId').setValue(appId);

            var callback= function(data, bSuccess){
                page.afterCallback(data, bSuccess);
                page.reloadListObj();
            }
            dijit.byId('gridObjectId').vtReload("object!onApply.do?"+token.getTokenParamString(),"objectsForm",null,callback) ;
        }
    }
    //********************************UPDATE************************************
    page.preUpdateObject = function (){
        clientAction = "update";
        page.changeObjectDialogInputStyle();
        page.clearObjectFormOnDialog();
        var dialog = dijit.byId("dialogObjectId");
        dialog.titleNode.innerHTML = "<sd:Property>global.edit</sd:Property>";
        page.changeObjectDialogButtonStyle(false);
        try{
            var count = 0;
            var grid = dijit.byId("gridObjectId");
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
                    alert("come preUpdateObject");
                    sd._("objectsFormOnDialog.appId").setValue(temp["appId"]);
                    sd._("objectsFormOnDialog.objectId").setValue(temp["objectId"]);
                    sd._("objectsFormOnDialog.objectCode").setValue(temp["objectCode"]);
                    sd._("objectsFormOnDialog.objectName").setValue(temp["objectName"]);
                    sd._("objectsFormOnDialog.objectType").setValue(temp["objectType"]);
                    sd._("objectsFormOnDialog.objectUrl").setValue(temp["objectUrl"]);
                    sd._("objectsFormOnDialog.ord").setValue(temp["ord"]);
                    sd._("objectsFormOnDialog.description").setValue=temp["description"];
                    sd._("objectsFormOnDialog.status").setValue(temp["status"]);
                    dialog.show();
                     
                }catch(e){
                    alert(e.message);
                }
            }
            else if(count>1){

                try{
                    page.alert('message',"<sd:Property>js.alertUniqueRecord</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertUniqueRecord</sd:Property>");
                };
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

    page.reloadListObj = function(){
        //var areaId = "objectArea";

        try{
            var appId = document.getElementById("appId").value;
            if(appId != null){
                var parentId = sd._("objectsFormOnDialog.parentId").getValue();
                if(parentId == "" || parentId == null){
                    parentId = "0";
                }
                dijit.byId("gridObjectId").vtReload("object!onInit.do?parentId="+parentId+"&appId="+appId, null, null, null);
                //sd.connector.post('object.do?appId='+ appId ,areaId,null,page.showArea("objectArea"));
            }
        }catch(err){

        }
    };

    page.onUpdateObject = function (){
        if(page.validatedInput()){
            var callback= function(data, bSuccess){
                page.afterCallback(data, bSuccess);
                page.reloadListObj();
            }
            dijit.byId('gridObjectId').vtReload("object!onApply.do?"+token.getTokenParamString(),"objectsForm",null,callback) ;
        }
    };
    //********************************DELETE************************************
    page.getCheckedItem = function(id){
        var objIds = "";
        var grid = dijit.byId(id);
        for(var j = 0; j < grid._by_idx.length;j++){
            var item = grid.getItem(j);
            if(item != undefined && item.checked == true){
                if(objIds.length == 0){
                    objIds = item.objectId;
                } else {
                    objIds = objIds+','+item.objectId;
                }
            }
        }
        return objIds;
    };

    page.apply = function(){
        var objIds = page.getCheckedItem("gridObjectId");
        //var content = dijit.byId("gridObjectId").vtGetCheckedDataForPost("gridObjectForm","isCheck");
        //sd.connector.post("object!reloadTree.do","objTreeDiv",null);
        var callback= function(data, bSuccess){
            alert("Xóa chức năng "+data.customInfo[1]);
            page.reloadListObj();
        }
        dijit.byId('gridObjectId').vtReload("object!onDelete.do?ids="+objIds+"&"+token.getTokenParamString(),null,null,callback);
    };

    page.onDeleteObject = function (){
        if(page.isCheckedObj()){
            try{
                page.confirmAsJS("Bạn có chắc chắn xóa chức năng không ?",page.apply);
            }catch(err){
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
    };


    lockObject = function(data, bSuccess){
        if(data.customInfo[1].toString()=="success"){
            alert("Khóa thành công");
        } else{
            alert("Khóa không thành công");
        }
        page.reloadListObj();
    };

    unlockObject = function(data, bSuccess){
        if(data.customInfo[1].toString()=="success"){
            alert("Mở khóa thành công");
        } else{
            alert("Mở khóa không thành công");
        }
        page.reloadListObj();
    };

    page.unlock = function(){
        if(page.isCheckedObj()){
            var continueFun = function (){
                var objIds = page.getCheckedItem("gridObjectId");
                dijit.byId('gridObjectId').vtReload("object!onUnlock.do?ids="+objIds+"&"+token.getTokenParamString(),null,null,unlockObject);
            }

            try{
                page.confirmAsJS("<sd:Property>js.alertUnlock</sd:Property>",continueFun);
            }catch(err){
            }
        }
        else{
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
            };
        }
        return false;
    };

    page.lock = function(){
        if(page.isCheckedObj()){
            var continueFun = function (){
                var objIds = page.getCheckedItem("gridObjectId");
                dijit.byId('gridObjectId').vtReload("object!onLock.do?ids="+objIds+"&"+token.getTokenParamString(),null,null,lockObject);
            }
            try{
                page.confirmAsJS("<sd:Property>js.alertLock</sd:Property>",continueFun);
            }catch(err){
            }
        }
        else{
            try{
                page.alert('message',"<sd:Property>js.alertRecord</sd:Property>",'warning');
            }catch(err){
            };
        }
        return false;
    };

    //**************************************************************************
    page.changeObjectDialogInputStyle = function(){
        if ((clientAction=="update") || (clientAction=="insert")){
            sd.widget.__setReadOnly("objectsFormOnDialog.objectId",true);
        }
    };

    //isOnInsert = true (on Insert)
    page.changeObjectDialogButtonStyle = function (/*boolean isOnInsert*/ isOnInsert){
        dijit.byId( "objectsFormOnDialog.btnInsert" ).domNode.style.display = isOnInsert?"":"none";

        if (isOnInsert){
            sd.widget.__setReadOnly("objectsFormOnDialog.status",false);
            sd.widget.__setReadOnly("objectsFormOnDialog.objectCode",false);
        }
        else{
            sd.widget.__setReadOnly("objectsFormOnDialog.status",true);
    <%--sd.widget.__setReadOnly("objectsFormOnDialog.objectCode",true);--%>
            }

            dijit.byId( "objectsFormOnDialog.btnUpdate" ).domNode.style.display = isOnInsert?"none":"";
        }

        page.clearObjectFormOnDialog = function(/*String*/){
            sd._("objectsFormOnDialog.description").setValue("");
            sd._("objectsFormOnDialog.objectId").setValue("");
            sd._("objectsFormOnDialog.objectName").setValue("");
            sd._("objectsFormOnDialog.objectType").setValue("");
            sd._("objectsFormOnDialog.objectUrl").setValue("");
            sd._("objectsFormOnDialog.ord").setValue("");
            sd._("objectsFormOnDialog.objectCode").setValue("");
            try{
            }catch(err){

            }
        };

        page.convertStringToDate = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dgDate){
            try{
                var temp = dgDate.split("-");
                return new Date(temp[1]+"/"+temp[2].split("T")[0]+"/"+temp[0]);
            }catch(e){
                alert("function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss");
                return undefined;
            }
        };
        page.convertStatusToStr=function(cellValue){
            switch(cellValue){
                case 1:
                    return 'Hoạt động';
                case 0:
                    return 'Bị khóa';
            }
        };
        page.convertTypeToStr=function(cellValue){
            switch(cellValue){
                case 0:
                    return 'Module';
                case 1:
                    return 'Component';
            }
        };
        page.setTitle=function(){
            try{
                var title = dijit.byId('parentObjTreeOnDialog').getValue();
                if(title!=null && title!=""){
                    try{
                        document.getElementById("spnTitle").innerHTML = title;
                    }catch(err){

                    }
                }
                else{
                    try{
                        document.getElementById("spnTitle").innerHTML = " cấp 1";
                        document.getElementById("js").innerHTML = "";
                    }catch(err){

                    }
                }
            }catch(err){
                
            }

        };
        page.validatedInput=function(){
            var objectName = null;
            var status = null;
            var objectType = null;
            var check=true;
            var order = "";
            var parentId ="";
            try{
                objectName = dijit.byId('objectsFormOnDialog.objectName').getValue();
                status = dijit.byId('objectsFormOnDialog.status').getValue();
                objectType = dijit.byId('objectsFormOnDialog.objectType').getValue();
                order = dijit.byId('objectsFormOnDialog.ord').getValue();
                parentId = dijit.byId('objectsFormOnDialog.parentId').getValue();
            }catch(err){
                alert(err.toString());
            }
            if(objectName==null||objectName==""){

                try{
                    page.alert('message',"<sd:Property>js.alertObjectName</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertObjectName</sd:Property>");
                };
                dijit.byId('objectsFormOnDialog.objectName').focus();
                check=false;
            }
            else if(objectType==null||objectType==""){

                try{
                    page.alert('message',"<sd:Property>js.alertType</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertType</sd:Property>");
                };
                dijit.byId('objectsFormOnDialog.objectType').focus();
                check=false;
            }
            else if(status==null||status==""){

                try{
                    page.alert('message',"<sd:Property>js.alertStatus</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertStatus</sd:Property>");
                };
                dijit.byId('objectsFormOnDialog.status').focus();
                check=false;
            }
            else if(!page.isInteger(order)){

                try{
                    page.alert('message',"<sd:Property>js.alertNumber</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertNumber</sd:Property>");
                };
                dijit.byId('objectsFormOnDialog.ord').focus();
                check=false;
            }
            if(objectType=="1" && parentId ==""){
                try{
                    page.alert('message',"<sd:Property>js.alertParent</sd:Property>",'warning');
                }catch(err){
                    alert("<sd:Property>js.alertParent</sd:Property>");
                };
                check = false;
            }
            return check;
        };
        page.isCheckedObj = function(){
            var bReturn = false;
            var gridId = "gridObjectId";
            var grid = document.getElementById(gridId);
            var lstItem = grid.getElementsByTagName("input");
            if(lstItem == null || lstItem.length ==0)
                return false;
            for(var i=0;i<lstItem.length;i++){
                if(lstItem[i].getAttribute("type") == "checkbox"){
                    if(lstItem[i].checked == true){
                        bReturn = true;
                        break;
                    }
                }
            }
            return bReturn;

        };
        
        page.convertParentId = function(inRow){
        };
        page.urlEdit = function(inRow){
            try{
                var row = dijit.byId("gridObjectId").getItem(inRow);
                if(row != null){
                    var url = "<div style='text-align:center;'><img src='share/images/icons/edit.png' \n\
                width='20px' height='20px' title='Cập nhật thông tin' \n\
                onClick='page.editObject(" + inRow + ")' /></div>";
                    return url;
                }
            }catch(err){
                alert(err.message);
            }
        };
        page.editObject = function(inRow){
            clientAction = "update";
            page.changeObjectDialogInputStyle();
            page.clearObjectFormOnDialog();
            var dialog = dijit.byId("dialogObjectId");
            dialog.titleNode.innerHTML = "<sd:Property>global.edit</sd:Property>";
            page.changeObjectDialogButtonStyle(false);
            try{
                var row = dijit.byId("gridObjectId").getItem(inRow);
                sd._("objectsFormOnDialog.appId").setValue(row.appId);
                sd._("objectsFormOnDialog.objectId").setValue(row.objectId);
                sd._("objectsFormOnDialog.objectName").setValue(row.objectName);
                sd._("objectsFormOnDialog.objectCode").setValue(row.objectCode);
                sd._("objectsFormOnDialog.objectType").setValue(row.objectTypeId);
                sd._("objectsFormOnDialog.objectUrl").setValue(row.objectUrl);
                sd._("objectsFormOnDialog.ord").setValue(row.ord);
                document.getElementById("objectsFormOnDialog.description").value = row.description;
                sd._("objectsFormOnDialog.status").setValue(row.status);

                dialog.show();
                try{
                    dijit.byId("objectsFormOnDialog.objectName").focus();
                }catch(err){

                }
            }catch(e){
                alert(e.message);
            }
        };
        page.isInteger = function(s){
            var i;
            if(s!=null){
                for (i = 0; i < s.length; i++)
                {
                    // Check that current character is number.
                    var c = s.charAt(i);
                    if (((c < "0") || (c > "9"))) return false;
                }
            }
            // All characters are numbers.
            return true;
        };
        page.getParentObject = function(parentId){
            var parentName ="";
            var grid = dijit.byId('gridObjectId');
            for (var idx in grid._by_idx){
                try{
                    var item = grid._by_idx[idx].item;
                    if(item["objectId"].toString()==parentId.toString()){
                        parentName = item["objectName"];
                        break;
                    }
                }catch(err){
                    alert(err.message);
                }

            }
            return parentName;
        };

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
        };
</script>