<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<script>
    var clientAction = "none";//"search","insert","update","delete","none";

    //********************************SEARCH************************************
    page.onSearch = function (){
        dijit.byId('gridId').vtReload("PositionAction!onSearch.do","positionForm") ;
    }
    //********************************INSERT************************************
    page.preInsert = function (){
        clientAction = "insert";
            page.clearFormOnDialog();
            var dialog = dijit.byId("dialogId");
            dialog.titleNode.innerHTML = "Thêm mới";
            page.changeDialogButtonStyle(/*boolean isOnInsert*/true);
            page.changeDialogInputStyle();
            dialog.show();
            try{
                dijit.byId("positionFormOnDialog.code").focus();
                page.onCustomEnter("positionFormOnDialog", page.onInsert);
            }catch(err){

            }
        }
        page.onInsert = function (){
            if(page.inValidate()){
                dijit.byId('gridId').vtReload("PositionAction!onInsert.do","positionFormOnDialog",null, page.afterCallback) ;
            }
        }

        //********************************UPDATE************************************
        page.preUpdate = function (){
            clientAction = "update";
            page.changeDialogInputStyle();
            page.clearFormOnDialog();
            var dialog = dijit.byId("dialogId");
            dialog.titleNode.innerHTML = "Sửa thông tin";
            page.changeDialogButtonStyle(false);

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
                           sd._("positionFormOnDialog.code").setValue(temp["code"]);
                           sd._("positionFormOnDialog.posId").setValue(temp["posId"]);
                           sd._("positionFormOnDialog.posName").setValue(temp["posName"]);
                           document.getElementById("positionFormOnDialog.description").value=temp["description"];
                           sd._("positionFormOnDialog.status").setValue(temp["status"]);
                           dialog.show();
                           try{
                               dijit.byId("positionFormOnDialog.code").focus();
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
           page.onUpdate = function (){
               if(page.inValidate()){
                   dijit.byId('gridId').vtReload("PositionAction!onUpdate.do","positionFormOnDialog",null,page.afterCallback) ;
               }
           }
           //********************************DELETE************************************
           page.onDelete = function (){
                page.apply=function(){
                    var content = dijit.byId("gridId").vtGetCheckedDataForPost("gridPositionForm", "isCheck");
                    dijit.byId('gridId').vtReload("PositionAction!onDelete.do",null,content,page.afterCallback) ;
                }
                if(page.isChecked("gridId")){
                    if (page.isEnableSelect("gridId")){
                        page.alert("Thông báo", "<sd:Property>global.deleteWarning1</sd:Property>", "success");
                        return false;
                    }
                    try{
                        page.confirmAsJS("<sd:Property>js.alertDelete</sd:Property>",page.apply);
                    }catch(err){
                        if (confirm("<sd:Property>js.alertDelete</sd:Property>")){
                            var content = dijit.byId("gridId").vtGetCheckedDataForPost("gridPositionForm", "isCheck");
                            dijit.byId('gridId').vtReload("PositionAction!onDelete.do",null,content,page.afterCallback) ;
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
            }

            //**************************************************************************
            page.changeDialogInputStyle = function(){
                if (clientAction=="update"){
                    sd.widget.__setReadOnly("positionFormOnDialog.code",true);
                }
                if (clientAction=="insert"){
                    sd.widget.__setReadOnly("positionFormOnDialog.code",false);
                }
            }

            //isOnInsert = true (on Insert)
            page.changeDialogButtonStyle = function (/*boolean isOnInsert*/ isOnInsert){
                dijit.byId( "positionFormOnDialog.btnInsert" ).domNode.style.display = isOnInsert?"":"none";
                dijit.byId( "positionFormOnDialog.btnUpdate" ).domNode.style.display = isOnInsert?"none":"";
            }

            page.clearFormOnDialog = function(/*String*/){
                sd._("positionFormOnDialog.code").setValue("");
                sd._("positionFormOnDialog.description").setValue("");
                sd._("positionFormOnDialog.posName").setValue("");
                sd._("positionFormOnDialog.posId").setValue("");
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
        page.convertStatusToStr=function(cellValue){
            switch(cellValue){
                case 1:
                    return 'Hoạt động';
                case 0:
                    return 'Bị khóa';
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
                        txtContent = "<div><sd:Property>js.result</sd:Property>:</div>";
                        txtContent += "<div style='text-align:left;overflow:auto;'>" + data.customInfo[1] + "</div>";
                        document.getElementById("mess").innerHTML = txtContent;
                        page.showMessageDialog("msgs");
                    }catch(err){
                        alert(data.customInfo[1]);
                    }
                    break;
                default:
                    try{
                        page.alert('message','no error','success');
                    }catch(err){
                        alert("no error");
                    }
                    break;
                }
            }
            page.inValidate=function(){
                var posCode = null;
                var status = null;
                var posName = null;
                var check=true;
                try{
                    posCode = dijit.byId('positionFormOnDialog.code').getValue();
                    status = dijit.byId('positionFormOnDialog.status').getValue();
                    posName = dijit.byId('positionFormOnDialog.posName').getValue();
                }catch(err){
                    alert(err.toString());
                }
                if(posCode==null||posCode==""){
                    try{
                        page.alert('message',"<sd:Property>js.alertPosCode</sd:Property>",'warning');
                    }catch(err){
                        alert("<sd:Property>js.alertPosCode</sd:Property>");
                    };
                    dijit.byId('positionFormOnDialog.code').focus();
                    check=false;
                }
                else if(posName==null||posName==""){
                    try{
                        page.alert('message',"<sd:Property>js.alertPosName</sd:Property>",'warning');
                    }catch(err){
                        alert("<sd:Property>js.alertPosName</sd:Property>");
                    };
                    dijit.byId('positionFormOnDialog.posName').focus();
                    check=false;
                }
                else if(status==null||status==""){
                    try{
                        page.alert('message',"<sd:Property>js.alertStatus</sd:Property>",'warning');
                    }catch(err){
                        alert("<sd:Property>js.alertStatus</sd:Property>");
                    };
                    dijit.byId('positionFormOnDialog.status').focus();
                    check=false;
                }
                return check;
            }
            page.urlEditPosition = function(inRow){
                try{
                    var row = dijit.byId("gridId").getItem(inRow);
                    if(row != null){
                        var url = "<div style='text-align:center;'><img src='${contextPath}/share/images/icons/edit.png' width='20px' height='20px' title='Cập nhật thông tin' onClick='page.editPosition(" + inRow + ")' /></div>";
                        return url;
                    }
                }catch(err){
                    alert(err.message);
                }
            }
            page.editPosition = function(inRow){
                clientAction = "update";
                page.changeDialogInputStyle();
                page.clearFormOnDialog();
                var dialog = dijit.byId("dialogId");
                dialog.titleNode.innerHTML = "Sửa thông tin";
                page.changeDialogButtonStyle(false);
                try{
                    var row = dijit.byId("gridId").getItem(inRow);
                    sd._("positionFormOnDialog.code").setValue(row.code);
                    sd._("positionFormOnDialog.posId").setValue(row.posId);
                    sd._("positionFormOnDialog.posName").setValue(row.posName);
                    document.getElementById("positionFormOnDialog.description").value=row.description;
                    sd._("positionFormOnDialog.status").setValue(row.status);
                    try{
                        dijit.byId("positionFormOnDialog.code").focus();
                        page.onCustomEnter("positionFormOnDialog", page.onUpdate);
                    }catch(err){

                    }
                    dialog.show();
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