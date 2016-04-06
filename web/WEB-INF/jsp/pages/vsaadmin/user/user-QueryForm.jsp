<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<script>

    dijit.byId("usersForm.userName").focus();
    page.getRow = function(inRow) {
        return inRow;
    }

    page.editUser = function(inDatum) {
        var item = dijit.byId("listUserGridId").getItem(inDatum);

        if (item != null) {
            var url = "<div><img style='cursor: pointer; cursor: hand;' src='share/images/icons/edit.png' title='Cập nhật thông tin' onClick='page.clickIconUpdate(" + inDatum + ")' height='20' width='20' /></div>";
            return url;
        }
    }

    page.view = function(inRow) {
        var item = dijit.byId("listUserGridId").getItem(inRow);

        if (item != null) {
            var url = "<a href='#' onClick='page.clickIconView(" + inRow + ")'>" + escapeHtml_(item.userName) + "</a>";
            return url;
        }
    }

    page.resetPass = function(inRow) {
        var item = dijit.byId("listUserGridId").getItem(inRow);

        if (item != null) {
            var url = "<div><img style='cursor: pointer; cursor: hand;' src='share/images/icons/reset.png' title='Sửa mật khẩu' onClick='page.clickResetPasswordImg(" + inRow + ")' height='20' width='20' /></div>";
            return url;
        }
    }

    page.appRole = function(inRow) {
        var item = dijit.byId("listUserGridId").getItem(inRow);

        if (item != null) {
            var url = "<div><img style='cursor: pointer; cursor: hand;' src='share/images/icons/role.png' title='Vai trò và ứng dụng' onClick='page.clickViewRole(" + inRow + ")' height='20' width='20' /></div>";
            return url;
        }
    }

    page.showDepts = function(inRow) {
        var item = dijit.byId("listUserGridId").getItem(inRow);

        if (item != null) {
            var url = "<div><img src='share/images/icons/dept.png' title='Show Departments' onClick='page.showDeptsOfUser(" + inRow + ")' height='20' width='20' /></div>";
            return url;
        }
    }

    // enter key
    page.searchDefault = function(evt) {
        var dk = dojo.keys;
        switch (evt.keyCode) {
            case dk.ENTER:
                page.onSearch();
                break;
        }
    }

    dojo.connect(dojo.byId("userSearchFormDiv"), "onkeypress", page.searchDefault);

    // sd.widget.__setReadOnly("deptTree",true);

</script>

<sd:TitlePane key="usersForm.search" id="userTltpn">
    <div id="userSearchFormDiv">
        <form id="usersForm" name="usersForm">
            <table  width="100%" align="center">
                <tr>
                    <th width="15%"></th>
                    <th width="35%"></th>
                    <th width="15%"></th>
                    <th width="35%"></th>
                </tr>
                <tr>
                    <td>
                        <sd:Label key="usersForm.userName" />
                    </td>
                    <td>
                        <sd:TextBox id="usersForm.userName" name="usersForm.userName" key="" cssStyle="width:80%" trim="true" maxlength="50"/>
                    </td>
                    <td>
                        <sd:Label key="usersForm.fullName" />
                    </td>
                    <td>
                        <sd:TextBox id="usersForm.fullName" name="usersForm.fullName" key="" cssStyle="width:80%" trim="true" maxlength="100"/>
                    </td >

                </tr>
                <tr>
                    <td>
                        <sd:Label key="usersForm.status"/>
                    </td>
                    <td>
                        <sd:SelectBox id="usersForm.status" key="" name="usersForm.status" cssStyle="width:80%">
                            <sd:Option value="ALL" >-- Chọn --</sd:Option>
                            <sd:Option value="1">Hoạt động</sd:Option>
                            <sd:Option value="0">Bị khóa</sd:Option>
                        </sd:SelectBox>
                    </td>
                    <td>
                        <sd:Label key="usersForm.cellphone"/>
                    </td>
                    <td>
                        <sd:TextBox id="usersForm.cellphone" name="usersForm.cellphone" key="" mask="^\d*$" cssStyle="width:80%" trim="true" maxlength="15"/>
                    </td>
                </tr>
                <tr>
                    <td >
                        <sd:Label key="Đơn vị" cssStyle=""/> 
                    </td>
                    <td>
                        <sd:TreePicker id="userSearchDeptTree" getChildrenNodeUrl="departmentAction!getDeptChildrenDataByNode.do"
                                       getTopNodeUrl="departmentAction!getDeptData.do"  key="" rootLabel="root" cssStyle="width:80%;"/>

                        <sd:TextBox cssStyle="display:none;" id="usersForm.deptId" name="usersForm.deptId" key=""/>
                    </td>

                    <td>
                        <sd:Label key="Nhóm người dùng"/>
                    </td>
                    <td>
                        <sd:SelectBox id="usersForm.userType" key="" name="usersForm.userType" cssStyle="width:80%">
                            <sd:Option value="-1" >-- Chọn --</sd:Option>
                            <sd:Option value="1">Doanh Nghiệp</sd:Option>
                            <sd:Option value="0">Các đơn vị khác</sd:Option>
                        </sd:SelectBox>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div align="center" style="padding-top:10px;">
        <sd:Button id="usersForm.btnSearch" key="" onclick="page.onSearch()" >
            <img src="share/images/icons/search.png" height="20" width="20">
            <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
        </sd:Button>
        <sd:Button id="usersForm.btnInsert" key="" onclick="page.preInsert()">
            <img src="share/images/icons/addUser.png" height="20" width="20">
            <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
        </sd:Button>
        <sd:Button id="userReport.excel" key="" onclick="page.importUsers()" >
            <img src="share/images/icons/excel.png" height="20" width="20">
            <span style="font-size:12px"><sd:Property>global.excel</sd:Property></span>
        </sd:Button>
    </div>
    <br/>
    <%--<sd:TitlePane key="usersForm.userTitle" id="lstUserTitlePane">--%>
    <form id="listUserGridForm">
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
            <div id="listUserGridDiv" style="width:100%;">
            <sd:DataGrid clientSort="true" 
                         getDataUrl="UserAction!onSearch.do?" 
                         id="listUserGridId" 
                         style="width:100%; height:100%;" 
                         container="listUserGridDiv" 
                         serverPaging="true"
                         rowsPerPage="10" >
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="index" get="page.getIndex" width="4%" cellStyles="text-align:center;"/>
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="true"  key="usersForm.checkboxCol" field="isCheck" width="5%" type="checkbox" headerCheckbox="true" cellStyles="text-align:center;"/>
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="usersForm.userName" get="page.getRow" formatter="page.view" width="10%"/>
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="usersForm.fullName" field="fullName" width="15%"/>
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="usersForm.cellphone" field="cellphone" width="8%" cellStyles="text-align:right;" />
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="usersForm.deptName" field="deptName" width="20%"/>
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="usersForm.status" field="status" width="8%" formatter="page.convertUserStatus"/>
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="usersForm.description" field="description" width="10%"/>
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false"  key="usersForm.edit" get="page.getRow" formatter="page.editUser" width="4%" cellStyles="text-align:center;" />
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false"  key="usersForm.reset" get="page.getRow" formatter="page.resetPass" width="7%" cellStyles="text-align:center;" />
                <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false"  key="usersForm.Role" get="page.getRow" formatter="page.appRole" width="7%" cellStyles="text-align:center;" />
            </sd:DataGrid>
        </div>
    </form>
    <table style="width: 100%">
        <tr>
            <!--
            <td>
                <div>
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('listUserGridId');">Select all</span>
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('listUserGridId');">Unselect all</span>
                </div>
            </td>
            -->
            <td>
                <div>
                    <sd:Button id="usersForm.btnDelete" key="" onclick="page.onDelete()">
                        <img src="share/images/icons/13.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnDelete</sd:Property></span>
                    </sd:Button>

                    <sd:Button id="usersForm.btnLock" key="" onclick="page.onLock()">
                        <img src="share/images/icons/lock.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnDisable</sd:Property></span>
                    </sd:Button>

                    <sd:Button id="usersForm.btnUnLock" key="" onclick="page.onUnLock()">
                        <img src="share/images/icons/unlock.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnEnable</sd:Property></span>
                    </sd:Button>
                    <%--
                                        <sd:Button id="usersForm.btnConvert" key="" onclick="page.onConvert()">
                                            <img src="share/images/icons/next.png" height="20" width="20">
                                            <span style="font-size:12px"><sd:Property>btnConvert</sd:Property></span>
                                        </sd:Button>


                    <sd:Button id="usersForm.btnShowRoles" key="" onclick="page.showRolesOfUser()">
                        <img src="share/images/icons/role.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnShowRoles</sd:Property></span>
                    </sd:Button>
                    --%>
                    <%--        <sd:Button id="usersForm.btnShowDepts" key="" onclick="page.showDeptsOfUser()">
                                <img src="share/images/icons/dept.png" height="20" width="20">
                                <span style="font-size:12px"><sd:Property>btnShowDepts</sd:Property></span>
                            </sd:Button>--%>
                </div>
            </td>
        </tr>
    </table>


</sd:TitlePane>

<br/>
<script>
    dijit.byId("userSearchDeptTree").onPickData = function(item) {
        try {
            if (item.id) {
                sd._("usersForm.deptId").setValue(item.id);
            } else {
                sd._("usersForm.deptId").setValue("");
                sd._("userSearchDeptTree").setValue("");
            }
        } catch (err) {
            alert(err.message);
        }
    };

</script>