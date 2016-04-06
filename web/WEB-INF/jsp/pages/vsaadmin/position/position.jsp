<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<jsp:include page="/WEB-INF/jsp/pages/vsaadmin/config/alertDialog.jsp"/>
<%@include file="/WEB-INF/jsp/pages/common/commonJavascript.jsp" %>
<% request.setAttribute("contextPath", request.getContextPath());%>
<sd:TitlePane key="panel.categoryPosition" id="tltpn2" >
    <script>
        $('#parameterForm').keypress(function(event) {
            var dk = dojo.keys;
            switch(event.which){
                case dk.ENTER:
                    page.onSearch();
                    break;
            }
        });
          setFirstFocus('parameterForm');
        page.getNo = function(inRow){
            return inRow + 1;
        }
        page.getRow = function(inRow){
            return inRow;
        }

        page.edit = function(inDatum){
            var item = dijit.byId("gridTrain").getItem(inDatum);

            if(item != null){
                var url = "<div><img src='share/images/icons/edit.png' title='Sửa' onClick='page.preUpdate(" + inDatum + ")' height='14px' width='14px' /></div>";
                return url;
            }
        }
    </script>
    <sd:Dialog id="categoryDialog" key="dialog.title"  width="800px" height="auto" >
        <div id="categoryDialogDiv" style="overflow: auto; height: 100%; width: 100%;">
            <jsp:include page="position-Dialog.jsp"/>
        </div>
    </sd:Dialog>
    <form id="parameterForm" name="parameterForm">
        <table style="width: 100%;" >
            <tr>
                <td class="tdOnForm"><sd:TextBox id="parameterForm.name" name="parameterForm.name"
                            key="position.Name"  cssStyle="width:90%"   trim="true" />
                </td>
                <td  class="tdOnForm"><sd:TextBox id="parameterForm.code" name="parameterForm.code"
                            key="position.Code"  cssStyle="width:90%"   trim="true" /></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center">
                    <sd:Button id="parameterForm.btnSearch" key="btnSearch" onclick="page.onSearch()"/>
                      <sd:Button key="btn.newItem" onclick="page.preInsert();" >
                    <%--img src="${contextPath}/share/img/Add.png" height="18" width="18">
                    <span style="font-size:12px"><sd:Property>btn.newItem</sd:Property> </span--%>
                </sd:Button>
                </td>
            </tr>
        </table>
    </form>
    <table style="width: 100%;" >
        <tr>
            <td>
                <div id="tblSuccessMessage" style="display: none; margin-bottom: 3px; padding: 2px; border-width: 1px;border-color: gray; border-style: solid;background-color: #90EE90">
                    <label style="color: green;font-size: 13px;" id="lblSuccessMessage">
                    </label>
                </div>
                <div id="tblErrorMessage" style="display: none;margin-bottom: 3px; padding: 2px; border-width: 1px; border-color: red;border-style: solid; background-color: #FDE5DD">
                    <label style="color: red;font-size: 13px;" id="lblErrorMessage">
                    </label>
                </div>
                <sd:DataGrid  id="gridTrain" getDataUrl="positionAction!onInit.do"
                              rowSelector="0px" style="width:100%;height:300px;" rowsPerPage="10"    >
                    <sd:ColumnDataGrid key="customer.No" get="page.getNo" width="30px" headerStyles="text-align:center;"  styles="text-align:center;" />
                    <sd:ColumnDataGrid   key="column.checkbox" headerCheckbox="true" headerStyles="text-align:center;" type="checkbox" width="30px" cellStyles="text-align:center;"/>
                    <sd:ColumnDataGrid editable="false"  key="usersForm.edit" get="page.getRow" formatter="page.edit" width="3%" />
                    <sd:ColumnDataGrid editable="false" key="gridHeader.Name" field="name"
                                       width="20%" headerStyles="text-align:center;" />
                    <sd:ColumnDataGrid editable="false" key="gridHeader.Code"   field="code"
                                       width="30%" headerStyles="text-align:center;" />
                    <sd:ColumnDataGrid editable="false" key="gridHeader.Description" field="description"
                                       width="45%" headerStyles="text-align:center;"/>

                </sd:DataGrid>
            </td>
        </tr>
        <tr>
            <td>
                
                <sd:Button key="" onclick="page.deleteItem();" >
                    <img src="${contextPath}/share/img/delete.png" height="14" width="18">
                    <span style="font-size:12px"><sd:Property>btn.deleteItem</sd:Property> </span>
                </sd:Button>
                <%--sd:Button key="" onclick="page.submitModifiedData();" >
                    <img src="${contextPath}/share/img/save.png" height="14" width="18">
                    <span style="font-size:12px"><sd:Property>btn.apply</sd:Property> </span>
                </sd:Button>
                <sd:Button key="" onclick="page.cancel();" >
                    <img src="${contextPath}/share/img/back.png" height="14" width="18">
                    <span style="font-size:12px"><sd:Property>btn.cancel</sd:Property> </span>
                </sd:Button--%>
            </td>
        </tr>
        <tr>
            <td>
                <sd:TextBox id="type.code12" key="type.name" cssStyle="display:none;" name="parameterForm.typeCode" value="B01"/>
            </td>
        </tr>
    </table>


</sd:TitlePane>

<script>



    page.deleteItem = function(){
        if (!dijit.byId("gridTrain").vtIsChecked()){
            msg.alert('<sd:Property>alert.select</sd:Property>');
        }
        else{
            msg.confirm('<sd:Property>confirm.delete</sd:Property>',"<sd:Property>confirm.title</sd:Property>",page.deleteTrain)
        }
    };
    page.onSearch = function (){
        //  var typeCode=sd._("type.code").value;
        dijit.byId('gridTrain').vtReload("positionAction!onSearch.do","parameterForm") ;
    };
    page.deleteTrain=function(){
        var content = dijit.byId("gridTrain").vtGetCheckedDataForPost("parameterFormOnGrid");
        sd.connector.post("positionAction!gridDeleteData.do?"+ token.getTokenParamString(),"bodyContent",null,content,page.returnMessageDelete);
    };
    page.returnMessageDelete = function(data){
        var obj = JSON.parse(data);
        var status = obj.customInfo;
        var lst=obj.items;
        document.getElementById("lblSuccessMessage").innerHTML=lst[0];
        document.getElementById("lblErrorMessage").innerHTML=lst[1];
        if(status.toString().indexOf('1') >= 0){
            document.getElementById("tblSuccessMessage").style.display='';
        }else if(status.toString().indexOf('2') >= 0){
            document.getElementById("tblErrorMessage").style.display='';
        }
        else if(status.toString().indexOf('3') >= 0){
            document.getElementById("tblErrorMessage").style.display='';
            document.getElementById("tblSuccessMessage").style.display='';
        }
        var items = dijit.byId("gridTrain").vtGetCheckedItems();
        var lengthItems = items.length;
        for(var i = 0; i< lengthItems;i++){
            var itemDel = items[i];
            var notDel=false;
            if(lst.length>2){
                for(var j = 2;j<lst.length;j++){
                    if(itemDel.posId!=null&&lst[j]==itemDel.posId.toString()){
                        notDel=true;
                    }
                }
            }
            if(!notDel){
                dijit.byId("gridTrain").store.deleteItem(itemDel);
            }
        }
        window.setTimeout( "hideMessage()", 5000 );
    };

    page.returnMessage = function(data){
        var typeID=dijit.byId("type.code").value;
        dijit.byId("gridTrain").vtReload("positionAction!onInit.do?TypeID="+typeID);
        var obj = JSON.parse(data);
        var status = obj.customInfo;
        var lst=obj.items;
        document.getElementById("lblSuccessMessage").innerHTML=lst[0];
        document.getElementById("lblErrorMessage").innerHTML=lst[1];
        if(status.toString().indexOf('1') >= 0){
            document.getElementById("tblSuccessMessage").style.display='';
        }else if(status.toString().indexOf('2') >= 0){
            document.getElementById("tblErrorMessage").style.display='';
        }
        else if(status.toString().indexOf('3') >= 0){
            document.getElementById("tblErrorMessage").style.display='';
            document.getElementById("tblSuccessMessage").style.display='';
        }
        window.setTimeout( "hideMessage()", 5000 );

    };
    hideMessage = function(){
        document.getElementById("tblSuccessMessage").style.display = 'none';
        document.getElementById("tblErrorMessage").style.display = 'none';
    };

    page.preInsert = function (){
        page.clearFormOnDialog();
        var dialog = dijit.byId("categoryDialog");
        //      dialog.titleNode.innerHTML = "Thêm mới học viên";
        page.changeDialogButtonStyle(/*boolean isOnInsert*/true);
        dijit.byId("parameterCreateForm.name").focus();
        dialog.show();
    };
    page.prepareDataForUpdate=function(inRow){

        var  row= dijit.byId("gridTrain").getItem(inRow);
        sd._("parameterCreateForm.parameterId").setValue( row.posId);
        console.log( sd._("parameterCreateForm.rowNo"));
        sd._("parameterCreateForm.rowNo").setValue(inRow);
        sd._("parameterCreateForm.name").setValue( row.name);
        sd._("parameterCreateForm.code").setValue( row.code);
        sd._("parameterCreateForm.description").setValue( row.description);
    };
    page.preUpdate = function (inRow){
        page.clearFormOnDialog();
        var dialog = dijit.byId("categoryDialog");
        page.prepareDataForUpdate(inRow);
        page.changeDialogButtonStyle(/*boolean isOnInsert*/false);
        dijit.byId("parameterCreateForm.name").focus();
        dialog.show();
    };
    page.afterUpdate = function (){
        var grid=dijit.byId('gridTrain');
        var rowNo = dijit.byId('parameterCreateForm.rowNo').value ;
        try{
         grid.doApplyCellEdit(dijit.byId("parameterCreateForm.name").getValue(),rowNo,"name");
            grid.doApplyCellEdit(dijit.byId("parameterCreateForm.code").getValue(),rowNo,"code");
            grid.doApplyCellEdit(dijit.byId("parameterCreateForm.description").getValue(),rowNo,"description");
            grid.scrollToRow(rowNo);

        }catch(e){
            alert(e.message);
        }
    };
    //isOnInsert = true (on Insert)
    page.changeDialogButtonStyle = function (/*boolean isOnInsert*/ isOnInsert){
        dijit.byId( "parameterCreateForm.btnInsert" ).domNode.style.display = isOnInsert?"":"none";
        dijit.byId( "parameterCreateForm.btnUpdate" ).domNode.style.display = isOnInsert?"none":"";
          dijit.byId( "categoryDialog" ).titleNode.innerHTML = isOnInsert?"Thêm mới ":"Cập nhật thông tin";
    };
    page.clearFormOnDialog = function(/*String*/){
        sd._("parameterCreateForm.parameterId").setValue("");
        sd._("parameterCreateForm.name").setValue( "");
        sd._("parameterCreateForm.code").setValue( "");
        sd._("parameterCreateForm.description").setValue( "");
    };
    dijit.byId("gridTrain").onCellFocus = function(cell, rowIndex){
        if (cell.index >=2){
            dijit.byId("gridTrain").edit.setEditCell(cell,rowIndex);
        }
    };






</script>



