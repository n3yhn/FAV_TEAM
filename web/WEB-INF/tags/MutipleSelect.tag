<%-- 
    Document   : ButtonAdd
    Created on : Dec 7, 2011, 3:32:13 PM
    Author     : gpdn_huannn
--%>

<%@tag description="Kiendv" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="id"%>
<%@attribute name="name"%>
<%@attribute name="disabled"%>
<%@attribute name="data" %>
<%@attribute name="onBlur" %>
<%@attribute name="actionWhenNoItem" %>
<%@attribute name="allowCode" %> <%-- co insert search2 vao list ko?--%>

<script>
    // BTP
    var allowInsert = 0;
    // Cuc ATVSTP
    //var allowInsert = 1;
    var dataObj = eval('${data}');
    var allowCode = '${allowCode}';
    var searchData = new Array();
    for (var i = 0; i < dataObj.length; i++) {
        searchData.push({name: dataObj[i].search1, id: dataObj[i].id, showName: dataObj[i].search1});
    }
    if (allowCode.toString() != "false") {
        for (var i = 0; i < dataObj.length; i++) {
            if (dataObj[i].search2 != '') {
                searchData.push({name: dataObj[i].search2, id: dataObj[i].id, showName: dataObj[i].search1});
            }
        }
    }

    var multiData = {items: searchData};

    dojo.require("dojo.data.ItemFileReadStore");
    dojo.require("dijit.form.ComboBox");
    dojo.require("dijit.form.FilteringSelect");
    //    dojo.addOnLoad(function(){     
    /*
     *NEW DATA
     */
    new dijit.form.ComboBox({
        id: "selecter-input${id}",
        store: new dojo.data.ItemFileReadStore({data: multiData}),
        autoComplete: true,
        //            query: {state: /.*/},
        style: "width: 100%;",
        required: true,
        searchAttr: "name",
        onKeyPress: function(event) {
            var key = (event.which) ? event.which : event.keyCode;
            if (key == 13 || key == 9) {
                var name = document.getElementById("selecter-input${id}").value.trim();
                //var abbr = name.match(/[A-Z]/g).join('');
                var officeName = String(name).replace(/"/g, '&quot;').replace(/'/g, '&#39;').replace(/([\\\*\/])/g, "");
                var officeCode = "";
                var exitId = "-1";
                /*
                 var exitName = "Không tồn tại";
                 var test = "";
                 var data = eval('${data}');
                 
                 for(var j=0;j<data.length; j++) {   
                 if(name.toUpperCase() == data[j].search1.toString().toUpperCase() || name.toUpperCase() == data[j].search2.toString().toUpperCase()) {                
                 exitName = data[j].search1;
                 exitId=data[i].id;
                 break;
                 }
                 }
                 */
                var func = '${actionWhenNoItem}';
                if (allowInsert.toString() == "0") {
                    func = '';
                }
                if (exitId.toString() == "-1" && !func == '' && officeName != '') {
                    if (func == null || func.toString() == "" || allowInsert.toString() == "0") {
                        return;
                    }
                    var contentToPost = {officeName: officeName, code: officeCode};
                    var url = func + "?" + token.getTokenParamString();
                    sd.connector.post(url, null, null, contentToPost, function(data) {
                        var obj = dojo.fromJson(data);
                        var result = obj.items;
                        var id = result[0];
                        if (id.toString() != "" && id.toString() != "-1") {
                            var hiddenValue = ";" + document.getElementById("${id}").value + ";";
                            if (hiddenValue.indexOf(";" + id + ";") < 0) {
                                var childSizes = dojo.query(".x-superboxselect-item", dojo.byId("list-selected-item${id}")).length;

                                var newItem = '<li class="x-superboxselect-item" id="selected-item${id}-' + childSizes + '" >'
                                        + officeName
                                        + '<a class="x-superboxselect-item-close" tabindex="0" href="javascript:xSuperboxselectOnremove(\'${id}\',\'' + childSizes + '\',\'' + id + '\')">'
                                        + '</a>'
                                        + '</li>';
                                var oldValue = dijit.byId('${id}').attr('value');
                                if ('${maxItem}' == '1' && oldValue != '') {
                                    return;
                                }
                                if (oldValue.toString() == "") {
                                    dijit.byId('${id}').attr('value', id);
                                } else {
                                    dijit.byId('${id}').attr('value', oldValue.toString() + ";" + id);
                                }
                                var oldInner = document.getElementById("list-selected-item${id}").innerHTML;
                                document.getElementById("list-selected-item${id}").innerHTML = oldInner + newItem;
                            }
                            document.getElementById("selecter-input${id}").value = "";
                        }
                    });
                }
            }
        },
        onChange: function(city) {
            if (this.item) {
                var hiddenValue = ";" + document.getElementById("${id}").value + ";";
                if (hiddenValue.indexOf(";" + this.item.id + ";") < 0) {
                    var childSizes = dojo.query(".x-superboxselect-item", dojo.byId("list-selected-item${id}")).length;

                    var newItem = '<li class="x-superboxselect-item" id="selected-item${id}-' + childSizes + '" >'
                            + this.item.showName
                            + '<a class="x-superboxselect-item-close" tabindex="0" href="javascript:xSuperboxselectOnremove(\'${id}\',\'' + childSizes + '\',\'' + this.item.id + '\')">'
                            + '</a>'
                            + '</li>';
                    var oldValue = dijit.byId('${id}').attr('value');
                    if (oldValue.toString() == "") {
                        dijit.byId('${id}').attr('value', this.item.id);
                    } else {
                        dijit.byId('${id}').attr('value', oldValue.toString() + ";" + this.item.id);
                    }

                    var oldInner = document.getElementById("list-selected-item${id}").innerHTML;
                    document.getElementById("list-selected-item${id}").innerHTML = oldInner + newItem;
                }
//                document.getElementById("selecter-input${id}").value="";
                dijit.byId('selecter-input${id}').attr("value", "0ccc");
                dijit.byId('selecter-input${id}').attr("value", "");
            }
        },
        onBlur: function() {
            document.getElementById("selecter-input-div${id}").style.display = 'none';
            var onBlur = '${onBlur}';
            if (onBlur != null && onBlur.toString() != "") {
                eval('${onBlur}');
            }
        }
    }, "selecter-input${id}");
    //    });

</script>

<sd:TextBox id="${id}" name="${name}" key="" cssStyle="display:none;" />
<sd:TextBox id="selecter_disable${id}" name="" value="${disabled}" key="" cssStyle="display:none;" />
<div tabindex="0" id="x-form-wrap-${id}" class="x-form-field-wrap" style="width: 100%;margin: 0;padding: 0;margin-left: 1px;word-wrap: break-word;"     
     onfocus="javascript:xSuperboxselectOnfocus('${id}')" >
    <div style="width: 98%;" class="x-form-text x-superboxselect">
        <ul style="margin: 0;padding: 0;" id="list-selected-item${id}">

        </ul>    
    </div>
    <div class="x-superboxselect-btns">
        <a class="x-superboxselect-item-close" tabindex="0" href="javascript:xSuperboxselectOnclear('${id}')"></a>      
    </div>
    <div style="clear: both;"></div>    
    <div style="margin-right: 5px;display: none;" id="selecter-input-div${id}"><input id="selecter-input${id}" /></div>
</div>

<script>
    /*
     *LOAD DATA
     */
    var getValue = dijit.byId('${id}').attr('value').toString();
    if (getValue.length > 0) {
        var arrayValues = getValue.split(";");
        for (var i = 0; i < arrayValues.length; i++) {
            //var showName = "Không tồn tại";
            var showName = "";
            for (var j = 0; j < dataObj.length; j++) {
                if (arrayValues[i].toString() == dataObj[j].id.toString()) {
                    showName = dataObj[j].search1;
                    break;
                }
            }
            if (showName != "")
            {
                var newItem = '<li class="x-superboxselect-item" id="selected-item${id}-' + i + '" >'
                        + showName
                        + '<a class="x-superboxselect-item-close" tabindex="0" href="javascript:xSuperboxselectOnremove(\'${id}\',\'' + i + '\',\'' + arrayValues[i] + '\')">'
                        + '</a>'
                        + '</li>';

                var oldInner = document.getElementById("list-selected-item${id}").innerHTML;
                document.getElementById("list-selected-item${id}").innerHTML = oldInner + newItem;
            }
        }
    }
    xSuperboxselectOnfocus = function(id) {
        var disable = dijit.byId('selecter_disable' + id).attr('value').toString();
        if (disable.toString() == 'true')
            return;
        document.getElementById('selecter-input-div' + id).style.display = 'block';
        document.getElementById('selecter-input' + id).focus();
    }
    xSuperboxselectOnclear = function(id) {
        var disable = dijit.byId('selecter_disable' + id).attr('value').toString();
        if (disable.toString() == 'true')
            return;
        document.getElementById('list-selected-item' + id).innerHTML = '';
        document.getElementById(id).value = "";
    }
    xSuperboxselectOnremove = function(id, index, remove) {
        var disable = dijit.byId('selecter_disable' + id).attr('value').toString();
        if (disable.toString() == 'true')
            return;
        dojo.destroy(dojo.byId('selected-item' + id + '-' + index));
        var oldInner = ";" + document.getElementById(id).value;
        document.getElementById(id).value = oldInner.replace(";" + remove, "").substring(1);
    }
    // set lai width
    document.getElementById("x-form-wrap-${id}").style.width = document.getElementById("x-form-wrap-${id}").offsetWidth - 6;
</script>