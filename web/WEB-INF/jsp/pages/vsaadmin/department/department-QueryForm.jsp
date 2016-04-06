<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<script>

    page.getRow = function(inRow){
        return inRow;
    }

    page.edit = function(inDatum){
        //      alert(inDatum);

        var item = dijit.byId("deptGridId").getItem(inDatum);

        if(item != null){
            var url = "<div><a href='#'><img src='share/images/icons/edit.png' title='Sửa' onClick='page.preUpdate(" + inDatum + ")' height='20' width='20' /></a></div>";
            return url;
        }
    }

    page.formatListUserOfDept= function(inDatum){
        //      alert(inDatum);

        var item = dijit.byId("deptGridId").getItem(inDatum);

        if(item != null){
            var url = "<div><a href='#'><img src='share/images/icons/listUser.png' title='Danh sách người dùng' onClick='page.listUserOfDept(" + inDatum + ")' height='20' width='20' /></a></div>";
            return url;
        }
    }
    page.formatAddUserToDept= function(inDatum){
        var item = dijit.byId("deptGridId").getItem(inDatum);
        if(item != null){
            var url = "<div><a href='#'><img src='share/images/icons/a7.png' title='Bổ sung người dùng' onClick='page.preAddUserToDept(" + inDatum + ")' height='20' width='20' /></a></div>";
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

    dojo.connect(dojo.byId("deptSearchDiv"),"onkeypress",page.searchDefault);


</script>

<table style="width:100%;padding: 0px; margin: 0px; table-layout: fixed">
    <tr>
        <td width="25%" valign="top">
            <sd:TitlePane key="department.treeTitle" id="deptTreePane" >
                <div id="treeDiv" style="overflow: auto; max-width: 300px">
                    <jsp:include page="tree.jsp"/>
                </div>
            </sd:TitlePane>
        </td>

        <td width="75%" valign="top">
            <sd:TitlePane key="departmentForm.title" id="deptPane">
                <fieldset>
                    <div id="deptSearchDiv">
                        <form id="departmentForm" name="departmentForm">
                            <div style="display:none">
                                <table width="100%">
                                    <tr>
                                        <td class="tdOnForm">
                                            <sd:TextBox id="departmentForm.deptName" name="departmentForm.deptName" key="departmentForm.deptName" cssStyle="width:80%" trim="true"/>

                                        </td>

                                        <td class="tdOnForm">
                                            <sd:TextBox id="departmentForm.deptCode" name="departmentForm.deptCode" key="departmentForm.deptCode" cssStyle="width:80%" trim="true"/>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td class="tdOnForm">
                                            <sd:TextBox id="departmentForm.address" name="departmentForm.address" key="departmentForm.address" cssStyle="width:80%" trim="true"/>
                                        </td>

                                        <td class="tdOnForm">

                                            <sd:SelectBox id="departmentForm.status" key="departmentForm.status" name="departmentForm.status" cssStyle="width:80%">
                                                <sd:Option value="ALL">Tất cả</sd:Option>
                                                <sd:Option value="1">Hoạt động</sd:Option>
                                                <sd:Option value="0">Bị khóa</sd:Option>
                                                <!--                                            <option value="ALL">Tất cả</option>
                                                                                            <option value="1">Hoạt động</option>
                                                                                            <option value="0">Bị khóa</option>-->
                                            </sd:SelectBox>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td class="tdOnForm">
                                            <sd:TextBox id="departmentForm.telephone" name="departmentForm.telephone" key="departmentForm.telephone" cssStyle="width:80%" trim="true"/>
                                        </td>

                                        <td class="tdOnForm">
                                            <sd:SelectBox id="departmentForm.type" key="departmentForm.type" name="departmentForm.deptTypeId" cssStyle="width:80%" data="deptTypeList" labelField="typeName" valueField="deptTypeId"/>
                                            <%--<div style="display:none">--%>
                                            <sd:TextBox id="departmentForm.deptId" name="departmentForm.deptId" key=""/>
                                            <sd:TextBox id="departmentForm.deptParentName" name="departmentForm.deptParentName" key=""/>
                                            <%--</div>--%>
                                        </td>

                                    </tr>
                                </table>
                            </div>
                        </form>
                    </div>
                    <%--<div align="center" style="padding-top:10px;">
                        <sd:Button id="departmentForm.btnSearch" key="" onclick="page.onSearch()">
                            <img src="share/images/icons/search.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
                        </sd:Button>
                    </div>--%>
                </fieldset>
                <br/>
                <form id="deptGridForm" >
                    <div id="tblSuccessMessage" style="display: none; margin-bottom: 3px; padding: 2px; border-width: 1px;border-color: gray; border-style: solid;background-color: #90EE90">
                        <label style="color: green;font-size: 15px;font-family: Time News Roman;border-color: gray; font-weight: bold" id="lblSuccessMessage">
                            <sd:Property>alert.updatesucess</sd:Property>
                        </label>
                    </div>
                    <div id="tblErrorMessage" style="display: none;margin-bottom: 3px; padding: 2px; border-bottom-width: 2px; border-left-style: solid;background-color: #FDE5DD">
                        <label style="color: red;font-size: 15px;font-family: Time News Roman; font-weight: bold" id="lblErrorMessage">
                            <sd:Property>alert.updaterror</sd:Property>
                        </label>
                    </div>
                    <div id="DeptGridDiv" style="width:100%;">
                        <sd:DataGrid clientSort="true" 
                                     getDataUrl="departmentAction!onInitChild.do" 
                                     id="deptGridId"
                                     style="width: 100%; height: 100%;" 
                                     container="DeptGridDiv" 
                                     rowSelector="0px" 
                                     rowsPerPage="10">
                            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial " cellStyles="text-align:center;" editable="false" key="index" get="page.getIndex" width="5%" />
                            <sd:ColumnDataGrid headerStyles="text-align:center;"  cellStyles="text-align:center;"  key="column.checkbox" headerCheckbox="true"  type="checkbox" width="5%"/>
                            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" editable="false" key="departmentForm.deptName" field="deptName" width="20%"/>
                            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" editable="false" key="departmentForm.deptCode" field="deptCode" width="10%"/>
                            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" editable="false" key="departmentForm.status" field="status" width="10%" formatter="page.convertDeptStatus"/>
                            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" editable="false" key="departmentForm.telephone" field="telephone" width="10%"/>
                            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" cellStyles="text-align:center;" editable="false"  key="usersForm.edit" get="page.getRow" formatter="page.edit" width="5%" />
                            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" cellStyles="text-align:center;" editable="false"  key="btnListUserOfDept" get="page.getRow" formatter="page.formatListUserOfDept" width="10%" />
                            <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold;font-size:10px;font-family:Tahoma,helvetica,arial" cellStyles="text-align:center;" editable="false"  key="btnAddUserOfDept" get="page.getRow" formatter="page.formatAddUserToDept" width="15%" />

                        </sd:DataGrid>
                    </div>
                </form>

                <table>
                    <tr>
                        <td>
                            <!--                                <div>
                                                                <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('deptGridId');">Select all</span>
                                                                <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('deptGridId');">UnSelect all</span>
                                                            </div>-->
                        </td>
                        <td>
                            <div align="right">
                                <sd:Button id="departmentForm.btnInsert" key="" onclick="page.preInsert()">
                                    <img src="share/images/icons/a7.png" height="20" width="20">
                                    <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                                </sd:Button>

                                <sd:Button id="departmentForm.btnDelete" key="" onclick="page.onDelete()">
                                    <img src="share/images/icons/13.png" height="20" width="20">
                                    <span style="font-size:12px"><sd:Property>btnDelete</sd:Property></span>
                                </sd:Button>


                            </div>
                        </td>
                    </tr>
                </table>
            </sd:TitlePane>

            <jsp:include page="listUserOfDept.jsp"/>
        </td>


    </tr>
</table>

<script>
    page.onNodeClick = function(item, node, event){
        console.log("item:"+item);
        if(item.id == undefined){
            sd._("departmentForm.deptId").setValue(null);
        }else{
            sd._("departmentForm.deptId").setValue(item.id);
            sd._("departmentForm.deptParentName").setValue(item.name);
        }
        page.onSearch();

        sd._("departmentFormOnDialog.deptId").setValue(item.id);
        sd._("addUserToDeptForm.deptId").setValue(item.id);
        sd._("userOfDeptForm.deptId").setValue(item.id);
        dijit.byId("userDeptGridId").vtReload("departmentAction!onInitUserList.do", "departmentFormOnDialog", null, null);

    };

    
    //    page.selectAll = function(gridId){
    //
    //        var grid = dijit.byId(gridId);
    //
    //        alert(grid._by_idx);
    //        alert(grid._by_idx.length);
    //        for (var idx in grid._by_idx){
    //            alert("3"+idx);
    //            var item = grid._by_idx[idx].item;
    //            item["isCheck"]="true";
    //        }
    //        alert("4");
    //        grid.render();
    //
    //    }
    //    page.unSelectAll = function(gridId){
    //        var grid = dijit.byId(gridId);
    //        for (var idx in grid._by_idx){
    //            var item = grid._by_idx[idx].item;
    //            item["isCheck"]="";
    //        }
    //        grid.render();
    //    }
    
    page.onSearch();
    dijit.byId("departmentForm.deptName").focus();
</script>