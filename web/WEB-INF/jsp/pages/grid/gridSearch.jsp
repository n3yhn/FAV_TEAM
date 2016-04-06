<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<sd:TitlePane  id = "" key="Điều kiện tìm kiếm" >
    <form id="form1" >
        <table width="100%" cellspacing="0" cellpadding="0">
            <tr>
                <td width="50%">
                    <sd:TextBox id="form.username" name="form1.username" key="gridtest.username" inputWidth="150px"/>
                </td>
                <td width="50%">
                    <sd:TextBox id="form.fullname" labelWidth="125px;" name="form1.fullname" key="gridtest.fullname" />
                </td>
            </tr>
            <tr>
                <td align="center" colspan="3">
                    <br/>
                    <sd:Button id="" key="Tìm kiếm" onclick="page.onSearch();" />
                </td>
            </tr>
        </table>
    </form>

</sd:TitlePane>

<br/>
<script type="text/javascript">
    page.getNo = function (index) {
        return index;
    }
    page.getSTT = function (index) {
        var grid = dijit.byId("gridTestDB");
        return grid.currentRow + index + 1;
    }
    page.setDisable=function(index){
        if (index % 2==1){
            return true;
        }
        return false;
    }
</script>

<sd:TitlePane id="gridTP" key="Danh sách sinh viên">
    <sd:DataGrid id="gridTestDB" style="width:100%;height:100%;" rowsPerPage="10" rowSelector="0px" getDataUrl="">
        <sd:ColumnDataGrid key="gridtest.STT" get="page.getNo" formatter="page.getSTT" width="100px" />
        <sd:ColumnDataGrid editable="true" key="" type="checkbox" width="100px" headerCheckbox="true" setDisabled="page.setDisable"/>
        <sd:ColumnDataGrid key="gridtest.id" field="stid" width="1000px" editable="false" />
        <sd:ColumnDataGrid editable="true" key="gridtest.username" field="stname" width="1000px" />
        <sd:ColumnDataGrid editable="true" key="gridtest.fullname" field="code" width="1000px" />
    </sd:DataGrid>

    <br/>
    <sd:Button id="" key="Thêm mới" onclick="page.openNew();" />
    <sd:Button id="" key="Cập nhật" onclick="page.postModifiedData();" />
    <sd:Button id="" key="Xóa" onclick="page.deleteItem();" />
</sd:TitlePane>

<sd:Dialog id="dlNew" key="Thêm mới sinh viên" width="400px">
    <table width="100%">
        <tr>
            <td >
                <sd:TextBox id="form1.id" name="form1.id" key="Id" />
            </td>
        </tr>
        <tr>
            <td width="50%">
                <sd:TextBox id="form1.username" name="form1.username" key="Username" />
            </td>
        </tr>
        <tr>
            <td>
                <sd:TextBox id="form1.fullname" name="form1.fullname" key="Fullname" />
            </td>
        </tr>
        <tr>
            <td align="center" colspan="2">
                <br/>
                <sd:Button id="" key="Lưu" onclick="page.addItem();" />
            </td>
        </tr>
    </table>
</sd:Dialog>

<script type="text/javascript">
    //sd._('form.username').attr('value',"<td>~!@#$%^\"&'|</td>");
    page.popupWindow= function () {
        window.open("grid!popup.do", "strWindowName",'width=400,height=300');
    }
    page.onSearch = function () {
        var grid = dijit.byId("gridTestDB");
        grid.vtReload("grid!searchData.do","form1");
    }
    
    page.openNew=function(){
        dijit.byId("dlNew").show();
    }
    
    openNew=function(){
        dijit.byId("dlNew").show();
    }
      
      
    page.addItem = function(){
        var grid = dijit.byId('gridTestDB');
        var param=token.getTokenParam();
        param['id']=sd._('form1.id').attr('value');
        param['username']=sd._('form1.username').attr('value');
        param['fullname']=sd._('form1.fullname').attr('value');
        //        var newItem = {
        //            'id' : sd._('form1.id').attr('value'),
        //            'username' : sd._('form1.username').attr('value'),
        //            'fullname' : sd._('form1.fullname').attr('value')
        //        };
        //Kiem tra du lieu hop le de luu DB
        if (!page.validateData()){
            alert("Sai du lieu o truong nao!!");
            return;
        }
        doUpdateArea({
            url : "grid!insertToDB.do",
            param : param,
            onSuccess : function(data) {
                grid.vtReload("grid!searchData.do");
            }
        });
    }
    
    page.deleteItem = function () {
        var grid =  dijit.byId('gridTestDB');
        if(!grid.vtIsChecked()){ 
            alert("Bạn chưa chọn để xóa!");
        } else {
            var content = grid.vtGetCheckedDataForPost('studentLst');
            var size="&studentLstSize="+grid.vtGetCheckedItems().length;
            doUpdateArea({
                url : "grid!deleteFromDB.do?"+token.getTokenParamString()+size,
                param: content,
                onSuccess : function() {
                    grid.vtReload("grid!searchData.do");
                }
            });
        }
    }

    page.postModifiedData = function () {
        var grid = dijit.byId('gridTestDB');
        
        //Kiem tra du lieu hop le de luu DB
        if (!page.validateData()){
            alert("Sai du lieu o truong nao!!");
            return;
        }
       
        var content = grid.vtGetModifiedDataForPost('studentLst');
        doUpdateArea({
            url : "grid!saveToDB.do?"+token.getTokenParamString(),
            param: content,
            onSuccess : function() {
                grid.vtReload("grid!searchData.do");
            }
        });
    }
    
    // Kiêm tra dũ lieu nhap vao theo yeu cau nghiep vu
    page.validateData=function(){
        return true;
    }
</script>


