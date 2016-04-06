<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<script>
    dijit.byId("rolesForm.codeSearch").focus();

    page.getArrayNo = function(inRow) {
        return inRow;
    }

    page.getNo = function(inRow) {
        return inRow + 1;
    }
    
    page.editRole = function(inRow){
        var item = dijit.byId("gridId").getItem(inRow);
        
        if(item != null) {
            var url = "<div><img style='cursor: pointer; cursor: hand;' src='share/images/icons/edit.png' title='Cập nhật thông tin' \n\
            onClick='page.preUpdate(" + inRow + ")' height='20' width='20' /></div>";
            return url;
        }
    }

    page.viewFunctionFormat = function(inRow){
        var item = dijit.byId("gridId").getItem(inRow);

        if(item != null) {
            var url = "<div><img style='cursor: pointer; cursor: hand;' src='share/images/icons/icon_checklist.png' title='Danh sách chức năng được sử dụng' \n\
            onClick='page.viewFunction(" + inRow + ")' height='20' width='20' /></div>";
            return url;
        }
    }

    page.convertStatus = function(intStatus) {
        switch(intStatus) {
            case 1:
                return "<sd:Property>global.active</sd:Property>";
                case 0:
                    return "<sd:Property>global.deactive</sd:Property>";
                    case "1":
                        return "<sd:Property>global.active</sd:Property>";
                        case "0":
                            return "<sd:Property>global.deactive</sd:Property>";
                            default :
                                return "...";
                            }
                        }
                        
                        page.convertObjectsType  = function(intStatus) {
                            switch(intStatus) {
                                case 0:
                                    return "<sd:Property>global.m</sd:Property>";
                                    case 1:
                                        return "<sd:Property>global.c</sd:Property>";
                                    }
                                }
</script>
                                        
<sd:TitlePane key="rolesForm.title" id="tltpn">
    <form id="rolesForm" name="rolesForm" action="onSubmitData();" onkeydown="return onSubmitData(event);">
        <table width="100%" cellpadding="2px" cellspacing="3px">
            <tr>
                <td width="50%">
                    <sd:TextBox id="rolesForm.codeSearch" name="rolesForm.codeSearch" key="rolesForm.code" cssStyle="width:80%" trim="true"/>
                </td>
                <td width="50%">
                    <sd:TextBox id="rolesForm.nameSearch" name="rolesForm.nameSearch" key="rolesForm.roleName" cssStyle="width:80%" trim="true"/>
                </td>
            </tr>
            <tr>
                <td width="50%">
                    <sd:TextBox id="rolesForm.descriptionSearch" name="rolesForm.descriptionSearch" key="rolesForm.description" cssStyle="width:80%" trim="true"/>
                </td>
                <td width="50%">
                    <sd:SelectBox id="rolesForm.statusSearch" name="rolesForm.statusSearch" key="rolesForm.status" cssStyle="width:80%">
                        <sd:Option value="-1">-- Chọn --</sd:Option>
                        <sd:Option value="1">Hoạt động</sd:Option>
                        <sd:Option value="0">Bị khóa</sd:Option>
                    </sd:SelectBox>
                </td>
            </tr>
            <tr style="text-align: center">
                <td colspan="2">
                    <sd:Button id="rolesForm.btnSearch" key="" onclick="page.onSearch()" >
                        <img src="share/images/icons/3.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
                    </sd:Button>
                </td>
            </tr>
            <tr style="text-align: center">
                <td colspan="2">
                    <sd:DataGrid id="gridId"
                                 getDataUrl="RolesAction!onInit.do"
                                 rowSelector="0%"
                                 style="width:100%; height:100%;"
                                 rowsPerPage="10"
                                 clientSort="true">
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" cellStyles="text-align:center;" key="customer.No" get="page.getNo" width="5%"/>
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" cellStyles="text-align:center;" editable="true" key="column.checkbox" headerCheckbox="true"
                                           type="checkbox" width="6%"/>
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="rolesForm.code" field="roleCode" width="20%"/>
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="rolesForm.roleName" field="roleName" width="25%"/>
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="rolesForm.description" field="description" width="25%"/>
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" editable="false" key="rolesForm.status" field="status" formatter="page.convertStatus" width="10%"/>
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" cellStyles="text-align:center;"
                                           editable="false"  key="global.edit" formatter="page.editRole" width="10%"
                                           get="page.getArrayNo" />
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" cellStyles="text-align:center;"
                                           editable="false"  key="applicationsForm.module" formatter="page.viewFunctionFormat" width="10%"
                                           get="page.getArrayNo" />
                        <sd:ColumnDataGrid headerStyles="text-align:center;font-weight: bold" cellStyles="text-align:center;"
                                           editable="false" key="rolesForm.status" field="roleId" width="0%" styles="display:none;"/>
                    </sd:DataGrid>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:Button id="rolesForm.btnInsert" key="" onclick="page.preInsert()" >
                        <img src="share/images/icons/a7.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                    </sd:Button>
                    <sd:Button id="rolesForm.btnLock" key="" onclick="page.onLock()" >
                        <img src="share/images/icons/lock.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnDisable</sd:Property></span>
                    </sd:Button>
                    <sd:Button id="rolesForm.btnUnLock" key="" onclick="page.onUnLock()" >
                        <img src="share/images/icons/unlock.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnEnable</sd:Property></span>
                    </sd:Button>
                    <sd:Button id="rolesForm.btnDelete" key="" onclick="page.onDelete()" >
                        <img src="share/images/icons/a4.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnDelete</sd:Property></span>
                    </sd:Button>
                </td>
            </tr>
        </table>
                <script type="javascript/text">
                        onSubmitData = function(e){
                            var keynum;
                            var keychar;
                            var numcheck;

                            if(window.event) // IE
                            {
                                keynum = e.keyCode;
                            }
                            else if(e.which) // Netscape/Firefox/Opera
                            {
                                keynum = e.which;
                            }

                            if(keynum ==13){
                                page.onSearch();
                            } else if(keynum == 116){
                                keynum = 13;
                            }
                            
                            return keynum;
                        }
                </script>
    </form>
</sd:TitlePane>
<script>

//
//                                page.searchDefaultRole = function(evt){
//                                    var dk = dojo.keys;
//                                    switch(evt.keyCode){
//                                        case dk.ENTER:
//                                            page.onSearch();
//                                            break;
//                                    }
//                                }
//
//                                page.searchDefaultFunction = function(evt){
//                                    var dk = dojo.keys;
//                                    switch(evt.keyCode){
//                                        case dk.ENTER:
//                                            page.onSearchFunction();
//                                            break;
//                                    }
//                                }
//
//                                dojo.connect(dojo.byId("roleFormDiv"),"onkeypress",page.searchDefaultRole);
//                                dojo.connect(dojo.byId("functionFormDiv"),"onkeypress",page.searchDefaultFunction);
        
</script>
