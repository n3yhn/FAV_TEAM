<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<script>
    dijit.byId("unitTreeFormOnDialogUser.userName").focus();

    page.searchDefault = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                page.onSearchUser();
                break;
        }
    }

    dojo.connect(dojo.byId("filterFormDiv"),"onkeypress",page.searchDefault);
</script>

<sd:TitlePane key="usersForm.search" id="tltpn10">
    <div id="filterFormDiv">
        <form id="unitTreeFormOnDialogUser" name="unitTreeForm">
            <s:hidden name="unitTreeForm.parentId" id="unitTreeFormOnDialogUser.parentId" value="0"/>
            <table  width="100%">
                <tr>
                    <td>
                        <sd:TextBox id="unitTreeFormOnDialogUser.userName" name="unitTreeForm.userName" key="usersForm.userName" cssStyle="width:80%" trim="true"/>
                    </td>

                    <td>
                        <sd:TextBox id="unitTreeFormOnDialogUser.fullName" name="unitTreeForm.fullName" key="usersForm.fullName" cssStyle="width:80%" trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <sd:TextBox id="unitTreeFormOnDialogUser.cellphone" name="unitTreeForm.cellphone" key="usersForm.cellphone" cssStyle="width:80%" trim="true"/>
                    </td>

                    <td>
                        <sd:TextBox id="unitTreeFormOnDialogUser.email" name="unitTreeForm.email" key="usersForm.email" cssStyle="width:80%" trim="true"/>
                    </td>
                </tr>
            </table>

        </form>
    </div>
    <div align="center" style="padding-top:10px;">
        <sd:Button id="unitTreeFormOnDialogUser.btnSearch" key="" onclick="page.onSearchUser()" >
            <img src="share/images/icons/3.png" height="20" width="20">
            <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
        </sd:Button>
    </div>
</sd:TitlePane>

<br/>
<sd:TitlePane key="usersForm.userTitle" id="lstUserTitlePane">
    <form id="listUserForm">
        <div id="listUserGridDiv" style="width:100%;height:320px">
            <sd:DataGrid clientSort="true" getDataUrl="/vsaadminv3/UnitTreeAction!onInitUser.do" id="listUserGridId" style="width: 100%; height: 100%;" container="listUserGridDiv" rowSelector="20px">
                <sd:ColumnDataGrid editable="true"  key="usersForm.checkboxCol" field="isCheck" width="10%" type="checkbox" />
                <sd:ColumnDataGrid editable="false" key="usersForm.userName" field="userName" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.fullName" field="fullName" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.staffCode" field="staffCode" width="20%"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.description" field="description" width="30%"/>
            </sd:DataGrid>
        </div>
    </form>

    <table width="100%" style="padding-top:20px">
        <tr>
            <td width="30%">
                <div style="padding-top:5px" align="left">
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('listUserGridId');">Select all</span>
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('listUserGridId');">Unselect all</span>
                </div>
            </td>
            <td width="40%">
                <div align="center" style="padding-top:10px;">
                    <sd:Button id="usersForm.btnInsert" key="" onclick="page.onSelect()" >
                        <img src="share/images/icons/a7.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>global.checkboxCol</sd:Property></span>
                    </sd:Button>
                </div>
            </td>
            <td width="30%">
            </td>
        </tr>
    </table>
</sd:TitlePane>