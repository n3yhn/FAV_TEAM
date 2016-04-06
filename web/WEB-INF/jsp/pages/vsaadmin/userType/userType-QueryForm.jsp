<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<script>

    page.getRow = function(inRow){
        return inRow;
    }

    page.edit = function(inDatum){

        var item = dijit.byId("userTypeGridId").getItem(inDatum);

        if(item != null){
            var url = "<div><img src='${contextPath}/share/images/icons/edit.png' title='Cập nhật thông tin' onClick='page.preUpdate(" + inDatum + ")' height='20' width='20' /></div>";
            return url;
        }
    }

    // enter key
    page.searchDefault = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                page.onSearch();
                break;
        }
    }

    dojo.connect(dojo.byId("userTypeSearchFormDiv"),"onkeypress",page.searchDefault);

</script>

<sd:TitlePane key="menu.user.userType" id="tltpn">

    <sd:FieldSet key="userTypeForm.search">
        <tr>
            <td>
                <div id="userTypeSearchFormDiv">
                    <form id="userTypeForm" name="userTypeForm">
                        <table width="90%">
                            <tr>
                                <td>
                                    <sd:TextBox id="userTypeForm.typeName" name="userTypeForm.typeName" key="userTypeForm.typeName" cssStyle="width:100%" labelWidth="20%" inputWidth="60%" trim="true"/>
                                </td>
                                <td>
                                    <sd:TextBox id="userTypeForm.description" name="userTypeForm.description" key="userTypeForm.description" cssStyle="width:100%" labelWidth="20%" inputWidth="80%" trim="true"/>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div align="center" style="padding-top:10px;">
                    <sd:Button id="userTypeForm.btnSearch" key="" onclick="page.onSearch()">
                        <img src="share/images/icons/search.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
                    </sd:Button>
                </div>
            </td>
        </tr>
    </sd:FieldSet>
    <%--</sd:TitlePane>

<br/>
<sd:TitlePane key="userTypeForm.userTypeTitle" id="listUserTypeTlPn">--%>
    <sd:FieldSet key="userTypeForm.userTypeTitle">
        <tr>
            <td>
                <form id="userTypeGridForm">
                    <div id="userTypeGridDiv" style="width:100%;height:200px">
                        <sd:DataGrid clientSort="true" getDataUrl="UserTypeAction!onInit.do" id="userTypeGridId" style="width: 100%; height: 100%;" container="userTypeGridDiv" rowSelector="20px">
                            <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="20px" />
                            <sd:ColumnDataGrid editable="false"  key="usersForm.checkboxCol" field="isCheck" width="30px" type="checkbox" />
                            <sd:ColumnDataGrid editable="false" key="userTypeForm.typeName" field="typeName" width="40%"/>
                            <sd:ColumnDataGrid editable="false" key="userTypeForm.description" field="description" width="40%"/>
                            <sd:ColumnDataGrid editable="false"  key="usersForm.edit" get="page.getRow" formatter="page.edit" width="50px" />
                        </sd:DataGrid>
                    </div>
                </form>
                <table style="padding-top:20px">
                    <tr>
                        <td>
                            <div>
                                <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('userTypeGridId');">Select all</span>
                                <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('userTypeGridId');">Unselect all</span>
                            </div>
                        </td>
                        <td>
                            <div align="right" >
                                <sd:Button id="userTypeForm.btnInsert" key="" onclick="page.preInsert()">
                                    <img src="share/images/icons/a7.png" height="20" width="20">
                                    <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                                </sd:Button>

                                <%--                    <sd:Button id="userTypeForm.btnUpdate" key="" onclick="page.preUpdate()">
                                                        <img src="share/images/icons/edit.png" height="20" width="20">
                                                        <span style="font-size:12px"><sd:Property>btnUpdate</sd:Property></span>
                                                    </sd:Button>--%>

                                <sd:Button id="userTypeForm.btnDelete" key="" onclick="page.onDelete()">
                                    <img src="share/images/icons/13.png" height="20" width="20">
                                    <span style="font-size:12px"><sd:Property>btnDelete</sd:Property></span>
                                </sd:Button>
                            </div>
                        </td>
                    </tr>
                </table>



            </td>
        </tr>
    </sd:FieldSet>

</sd:TitlePane>

<script>
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

    dijit.byId("userTypeForm.typeName").focus();
</script>