<%-- 
    Document   : TextboxList
    Created on : Apr 11, 2013, 3:32:13 PM
    Author     : cpdt_pt_sytv
--%>
<%@tag description="Textbox List" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="id" required="true"%>
<%@attribute name="valueId" required="true"%>
<%@attribute name="valueName" required="true"%>
<%@attribute name="data" required="true"%>
<%@attribute name="allowImport"%>
<%@attribute name="disabled"%>
<%@attribute name="onBlur" %>

<script>

    var dataObj = eval('${data}');
    var allowImport = '${allowImport}';
    var searchData = new Array();
    for (var i = 0; i < dataObj.length; i++) {
        searchData.push({name: dataObj[i].search1, id: dataObj[i].id, showName: dataObj[i].search1});
    }
    var multiData = {items: searchData};

    dojo.require("dojo.data.ItemFileReadStore");
    dojo.require("dijit.form.ComboBox");
    dojo.require("dijit.form.FilteringSelect");

    /*
     *NEW DATA
     */
    new dijit.form.ComboBox({
        id: "selecter-input${valueName}",
        store: new dojo.data.ItemFileReadStore({data: multiData}),
        autoComplete: true,
        //            query: {state: /.*/},
        style: "width: 100%;",
        required: true,
        searchAttr: "name",
        onKeyPress: function(event) {

            var key = (event.which) ? event.which : event.keyCode;
            if (key == 13 || key == 9) {
                var name = document.getElementById("selecter-input${valueName}").value.trim();
                var officeName = String(name).replace(/"/g, '&quot;').replace(/'/g, '&#39;').replace(/([\\\*\/])/g, "");
                var exitId = "-1";
                var exitName = officeName;
                if (!(officeName.length > 0)) {
                    return;
                }
                var data = eval('${data}');
                for (var j = 0; j < data.length; j++) {
                    if (officeName.toUpperCase() == data[j].search1.toString().toUpperCase() || officeName.toUpperCase() == data[j].search2.toString().toUpperCase()) {
                        exitName = data[j].search1;
                        exitId = data[j].id;
                        break;
                    }
                }
                /* SyTV -- Cho phep nhap hay khong?*/
                var allow='${allowImport}';
                if(exitId == "-1" && allow.toString()=="false") {
                    return;
                }
                var hiddenValue = ";" + document.getElementById("${valueId}").value + ";";
                if (hiddenValue.indexOf(";" + exitId + ";") < 0) {
                    var childSizes = dojo.query(".x-superboxselect-item", dojo.byId("list-selected-item${valueName}")).length;

                    var newItem = '<li class="x-superboxselect-item" id="selected-item${valueName}-' + childSizes + '" >'
                            + exitName
                            + '<a class="x-superboxselect-item-close" tabindex="0" href="javascript:xTextboxListOnremove(\'${valueId}\',\'${valueName}\',\'' + childSizes + '\',\'' + exitId + '\',\'' + exitName + '\')">'
                            + '</a>'
                            + '</li>';
                    var oldValue = dijit.byId('${valueName}').attr('value');
                    if (oldValue.toString() == "") {
                        dijit.byId('${valueName}').attr('value', exitName);
                    } else {
                        dijit.byId('${valueName}').attr('value', oldValue.toString() + ";" + exitName);
                    }
                    var oldId = dijit.byId('${valueId}').attr('value');
                    if (exitId.toString() != "-1") {
                        if (oldId.toString() == "") {
                            dijit.byId('${valueId}').attr('value', exitId);
                        } else {
                            dijit.byId('${valueId}').attr('value', oldId.toString() + ";" + exitId);
                        }
                    }

                    var oldInner = document.getElementById("list-selected-item${valueName}").innerHTML;
                    document.getElementById("list-selected-item${valueName}").innerHTML = oldInner + newItem;
                }
                dijit.byId('selecter-input${valueName}').attr("value", "0ccc");
                dijit.byId('selecter-input${valueName}').attr("value", "");
//                document.getElementById("selecter-input${valueName}").value = "";
            }
        },
        onChange: function(city) {
            if (this.item) {
                var hiddenValue = ";" + document.getElementById("${valueId}").value + ";";
                if (hiddenValue.indexOf(";" + this.item.id + ";") < 0) {
                    var childSizes = dojo.query(".x-superboxselect-item", dojo.byId("list-selected-item${valueName}")).length;

                    var newItem = '<li class="x-superboxselect-item" id="selected-item${valueName}-' + childSizes + '" >'
                            + this.item.showName
                            + '<a class="x-superboxselect-item-close" tabindex="0" href="javascript:xTextboxListOnremove(\'${valueId}\',\'${valueName}\',\'' + childSizes + '\',\'' + this.item.id + '\',\'' + this.item.showName + '\')">'
                            + '</a>'
                            + '</li>';
                    var oldValue = dijit.byId('${valueName}').attr('value');
                    if (oldValue.toString() == "") {
                        dijit.byId('${valueName}').attr('value', this.item.showName);
                    } else {
                        dijit.byId('${valueName}').attr('value', oldValue.toString() + ";" + this.item.showName);
                    }
                    var oldId = dijit.byId('${valueId}').attr('value');
                    if (oldId.toString() == "") {
                        dijit.byId('${valueId}').attr('value', this.item.id);
                    } else {
                        dijit.byId('${valueId}').attr('value', oldId.toString() + ";" + this.item.id);
                    }
                    var oldInner = document.getElementById("list-selected-item${valueName}").innerHTML;
                    document.getElementById("list-selected-item${valueName}").innerHTML = oldInner + newItem;
                }
                dijit.byId('selecter-input${valueName}').attr("value", "0ccc");
                dijit.byId('selecter-input${valueName}').attr("value", "");
//                document.getElementById("selecter-input${valueName}").value = "";
            }
        },
        onBlur: function() {
            document.getElementById("selecter-input-div${valueName}").style.display = 'none';
            var onBlur = '${onBlur}';
            if (onBlur != null && onBlur.toString() != "") {
                eval('${onBlur}');
            }
        }
    }, "selecter-input${valueName}");
    //    });

</script>

<sd:TextBox id="${valueName}" name="${valueName}" key="" cssStyle="display:none;" />
<sd:TextBox id="${valueId}" name = "${valueId}" key="" cssStyle="display:none;"/>
<sd:TextBox id="selecter_disable${valueName}" name="" value="${disabled}" key="" cssStyle="display:none;" />
<div tabindex="0" class="x-form-field-wrap" onfocus="javascript:xTextboxListOnfocus('${valueName}')" id="x-form-wrap-${id}">
    <div style="width: 98%;" class="x-form-text x-superboxselect">
        <ul style="margin: 0;padding: 0;" id="list-selected-item${valueName}">

        </ul>    
    </div>
    <div class="x-superboxselect-btns">
        <a class="x-superboxselect-item-close" tabindex="0" href="javascript:xTextboxListOnclear('${valueId}','${valueName}')"></a>      
    </div>
    <div style="clear: both;"></div>    
    <div style="margin-right: 5px;display: none;" id="selecter-input-div${valueName}"><input id="selecter-input${valueName}" /></div>
</div>

<script>
    /*
     *LOAD DATA
     */
    var getValue = dijit.byId('${valueName}').attr('value').toString();
    var getId = dijit.byId('${valueId}').attr('value').toString();
    if (getValue.length > 0) {
        var arrayValues = getValue.split(";");
        for (var i = 0; i < arrayValues.length; i++) {
            var showId = "-1";
            var showName = arrayValues[i].toString();
            if (getId.length > 0) {
                arrayIds = getId.split(";");
                for (var j = 0; j < dataObj.length; j++) {
                    if (showName.toUpperCase() == dataObj[j].search1.toString().toUpperCase()) {
                        showId = dataObj[j].id;
                        break;
                    }
                }
            }
            if (showName != "") {
                var newItem = '<li class="x-superboxselect-item" id="selected-item${valueName}-' + i + '" >'
                        + showName
                        + '<a class="x-superboxselect-item-close" tabindex="0" href="javascript:xTextboxListOnremove(\'${valueId}\',\'${valueName}\',\'' + i + '\',\'' + showId + '\',\'' + showName + '\')">'
                        + '</a>'
                        + '</li>';

                var oldInner = document.getElementById("list-selected-item${valueName}").innerHTML;
                document.getElementById("list-selected-item${valueName}").innerHTML = oldInner + newItem;
            }
        }
    }
    
    // set lai width
    document.getElementById("x-form-wrap-${id}").style.width=document.getElementById("x-form-wrap-${id}").offsetWidth-6;
</script>