/*
 *  Author: Kiendv
 *
 **/

function resultMessage_show (id , success, message, timeout) {
    var divResult = dojo.byId(id);
    timeout = 10000;
    dojo.attr(divResult, {
        style:{
            display:"block"
        },
        innerHTML: message
    });

    if(success == "1") {
        dojo.addClass(divResult, "lltp_resultMessageSuccess");
    } else {
        dojo.addClass(divResult, "lltp_resultMessageError");
    }

    setTimeout(
        resultMessage_hide = function () {
            if(success == "1") {
                dojo.removeClass(divResult, "lltp_resultMessageSuccess");
            } else {
                dojo.removeClass(divResult, "lltp_resultMessageError");
            }
            dojo.attr(divResult, {
                style:{
                    display:"none"
                },
                innerHTML: ""
            });
        },
        timeout
        );
    return true;
}
function addNewRow(id){
    var grid = dijit.byId(id);
    grid.edit.apply();
    var newItem = {
    }

    grid.store.newItem(newItem);
    grid.scrollToRow(grid._by_idx.length);
}

function xSuperboxselectOnfocus(id) {
    var disable = dijit.byId('selecter_disable' + id).attr('value').toString();
    if(disable.toString()=='true') return;
    document.getElementById('selecter-input-div' + id).style.display='block';
    document.getElementById('selecter-input' + id).focus();
}

function xSuperboxselectOnclear(id) {
    var disable = dijit.byId('selecter_disable' + id).attr('value').toString();
    if(disable.toString()=='true') return;
    document.getElementById('list-selected-item'+id).innerHTML='';
    document.getElementById(id).value = ""; 
}

function xSuperboxselectOnremove(id, index, remove) {
    var disable = dijit.byId('selecter_disable' + id).attr('value').toString();
    if(disable.toString()=='true') return;
    dojo.destroy(dojo.byId('selected-item' + id + '-'+index));
    var oldInner = ";" + document.getElementById(id).value;
    document.getElementById(id).value = oldInner.replace(";" + remove, "").substring(1);
    
}

function xSuperboxselectReload(id, dataObj) {
    var getValue = dijit.byId(id).attr('value').toString();   
    if(getValue.length>0) {
        var arrayValues = getValue.split(";"); 
        for(var i=0; i<arrayValues.length; i++) {        
            var showName = "Không tồn tại";
            for(var j=0;j<dataObj.length; j++) {             
                if(arrayValues[i].toString() == dataObj[j].id.toString()) {                
                    showName = dataObj[j].search1;
                    break;
                }
            }    
            var newItem = '<li class="x-superboxselect-item" id="selected-item'+id+'-'+ i + '" >'
            + showName
            + '<a class="x-superboxselect-item-close" tabindex="0" href="javascript:xSuperboxselectOnremove(\'' + id + '\',\''+i+'\',\''+arrayValues[i]+'\')">'
            + '</a>'
            + '</li>';
            
            var oldInner = document.getElementById("list-selected-item" + id).innerHTML;
            document.getElementById("list-selected-item" + id).innerHTML = oldInner + newItem;
        }   
    }
}

function xSuperboxSetDisable(id, bool) {
    if(bool == true) {
        dijit.byId('selecter_disable' + id).attr('value', 'true');
    } else {
        dijit.byId('selecter_disable' + id).attr('value', 'false');
    }
}
