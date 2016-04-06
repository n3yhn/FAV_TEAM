<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div style="display:inline-block;">
    <div style="cursor:pointer;padding:3px 0px 0px 0px;float:left;border:1px solid;border-bottom:0px;border-color:#A9D0F5;-moz-border-radius-topleft:6px;
         -moz-border-radius-topright:6px;-webkit-border-top-right-radius:6px;-webkit-border-top-left-radius:6px;
         padding-left:2px;padding-right:2px;padding-bottom:2px;">
        <span style="padding-left:5px;padding-right:5px;
              margin:0px 0px 0px 0px;color:#15428B;
              font-family:Tahoma,helvetica,arial,'Times New Roman';
              font-style:normal;
              font-weight:lighter;">
            Danh sách người dùng quản lý bởi admin lĩnh vực <span style="color:red;">${fn:escapeXml(par)}</span></span>
    </div>
</div>
<br/>
<br/>
<div id="userSearchFormDiv" align="center">
    <form id="usersForm" name="usersForm">
        <table  width="80%">
            <tr>
                <td class="tdOnForm">
                    <sd:TextBox id="usersForm.userName" name="usersForm.userName" key="usersForm.userName" cssStyle="width:80%" trim="true"/>
                </td>

                <td class="tdOnForm">
                    <sd:TextBox id="usersForm.fullName" name="usersForm.fullName" key="usersForm.fullName" cssStyle="width:80%" trim="true"/>
                </td >
            </tr>
        </table>

    </form>
</div>

<div align="center" style="padding-top:10px;">
    <sd:Button id="usersForm.btnSearch1" key="" onclick="page.onSearchUser()" >
        <img src="share/images/icons/search.png" height="20" width="20">
        <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
    </sd:Button>
</div>
<script>
    page.onSearchUser=function(){
        dijit.byId('gridUsrOfAdId').vtReload("AdAction!onListUsrOfAd.do","usersForm");
    }
</script>
<div id="gridUsrOfAdDiv" style="width:100%;height:300px">
    <sd:DataGrid clientSort="true" getDataUrl="/vsaadminv3/AdAction!onListUsrOfAd.do" id="gridUsrOfAdId" style="width:100%; height:100%;" container="gridUsrOfAdDiv" rowSelector="20px">
        <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="20px" />
        <sd:ColumnDataGrid editable="true" key="title.select" field="isCheck" type="checkbox" width="40px" styles="text-align:center;" />
        <sd:ColumnDataGrid editable="false" key="adForm.userName" field="userName" width="100px" get="page.getRow" formatter="page.viewUsr"/>
        <sd:ColumnDataGrid editable="false" key="adForm.fullName" field="fullName" width="200px"/>
        <sd:ColumnDataGrid editable="false" key="adForm.telephone" field="telephone" width="100px"/>
        <sd:ColumnDataGrid editable="false" key="adForm.email" field="email" width="100px"/>
        <sd:ColumnDataGrid editable="false" key="adForm.gender" field="gender" width="100px" formatter="page.convertGenderToStr"/>
        <sd:ColumnDataGrid editable="false" key="adForm.status" field="status" width="100px" formatter="page.convertStatusToStr"/>
        <sd:ColumnDataGrid editable="false" key="adForm.identityCard" field="identityCard" width="100px"/>
        <sd:ColumnDataGrid editable="false" key="adForm.passportNumber" field="passportNumber" width="100px"/>
        <sd:ColumnDataGrid editable="false" key="adForm.dateOfBirth" field="dateOfBirth" formatter="page.convertDateToString" width="100px"/>
        <sd:ColumnDataGrid editable="false" key="adForm.description" field="description" width="100px"/>
        <sd:ColumnDataGrid editable="false" key="adForm.userId" field="userId" styles="display:none;"/>
    </sd:DataGrid>
</div>
<table  style="padding-top:20px">
    <tr>
        <td>
            <div>
                <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('gridUsrOfAdId');">Select all</span>
                <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('gridUsrOfAdId');">Unselect all</span>
            </div>
        </td>
        <td>
            <div style="text-align:right;">
                <sd:Button id="usrOfAdForm.btnInsert" key="" onclick="page.preGrant('get')">
                    <img src="${contextPath}/share/images/icons/6.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                </sd:Button>

                <%--<sd:Button id="usrOfAdForm.btnEntrust" key="" onclick="page.preGrant('entrust')">
                    <img src="${contextPath}/share/images/icons/entrust.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnEntrust</sd:Property></span>
                </sd:Button>--%>

                <sd:Button id="rolesForm.btnDelete" key="" onclick="page.onDeleteUserFromManager()" >
                    <img src="share/images/icons/a4.png" height="20" width="20">
                    <span style="font-size:12px"><sd:Property>btnDelete</sd:Property></span>
                </sd:Button>
            </div>
        </td>
    </tr>
</table>

<br />
