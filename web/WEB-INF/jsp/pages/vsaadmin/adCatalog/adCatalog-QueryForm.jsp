<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<style type="text/css">
    #ula a:visited {
        display:block;
        /*
        width:9em;
          border:1px solid #808;*/
        font-family:arial, verdana, sans-serif;
        font-size:12px;
        text-align:left;
        text-decoration:none;
    }
    #ula a:active {
    }
    #ula a:link{
        color:#5858FA;
        font-size:12px;
    }
    #di a:link{
        color:#1C1C1C;
        font-size:12px;
    }
    #di a:hover {
        color: red;
    }
    #ula a:hover {
        color: #FF9900;
    }
    #ulli ul {
        list-style: none;
        margin-left: 0;
        padding-left: 1em;
        text-indent: -1em;
    }
    .continue{
        color:#0B243B;
        font-weight:bold;
        cursor:pointer;
    }
    .step{
        cursor:pointer;
        font-weight:normal;
    }
</style>
<sd:TitlePane key="adForm.title" id="tltpn">
    <form id="adForm" name="adForm" action="/AdAction.do">
        <table width="100%">
            <tr>
                <th width="15%"></th>
                <th width="35%"></th>
                <th width="15%"></th>
                <th width="35%"></th>
            </tr>
            <tr>
                <td>
                    <sd:Label key="adForm.userName"/>
                </td>
                <td>
                    <sd:TextBox id="adForm.userName" name="adForm.userName" key="" cssStyle="width:80%"/>
                </td>
                <td>
                    <sd:Label key="adForm.fullName"/>
                </td>
                <td>
                    <sd:TextBox id="adForm.fullName" name="adForm.fullName" key="" cssStyle="width:80%"/>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:Label key="adForm.telephone"/>
                </td>
                <td>
                    <sd:TextBox id="adForm.telephone" name="adForm.telephone" key="" cssStyle="width:80%"/>
                </td>
                <td>
                    <sd:Label key="adForm.email"/>
                </td>
                <td>
                    <sd:TextBox id="adForm.email" name="adForm.email" key="" cssStyle="width:80%"/>

                </td>
            </tr>
            <tr>
                <td>
                    <sd:Label key="usersForm.department"/>
                </td>
                <td>
                    <sd:TreePicker id="deptTreeBelow" getChildrenNodeUrl="UserAction!getChildrenDataByNode.do" getTopNodeUrl="UserAction!getData.do" key="" rootLabel="root" cssStyle="width:80%" popupStyle="ajax"/>
                    <div id="di" style="text-align:left;margin-left:30%;"><a style="text-decoration:none;" href="#" onclick="page.clearTree()">Bỏ chọn</a></div>
                    <div style="display:none;"><sd:TextBox id="adForm.deptId" name="adForm.deptId" key="adForm.deptId"/></div>
                </td>
                <td>
                    <sd:Label key="adForm.status"/>
                </td>
                <td>
                    <sd:SelectBox key="" name="adForm.status" id="adForm.status" cssStyle="width:80%;">
                        <option value="10"><sd:Property>slt.all</sd:Property></option>
                        <option value="2"><sd:Property>slt.active</sd:Property></option>
                        <option value="-2"><sd:Property>slt.deactive</sd:Property></option>
                    </sd:SelectBox>
                </td>
            </tr>
            <tr>
                <td></td>
                <td style="display:none;">
                    <sd:TextBox id="adForm.userId" name="adForm.userId" key="adForm.userId" cssStyle="width:80%"/>
                </td>
                <td></td>
                <td></td>
            </tr>
        </table>
    </form>
    <div style="text-align:center;">
        <sd:Button id="adForm.btnSearch" key="" onclick="page.onSearch()">
            <img src="${contextPath}/share/images/icons/a1.png" height="20" width="20">
            <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
        </sd:Button>
    </div>

    <div id="gridDiv" style="width:100%;height:300px">
        <sd:DataGrid clientSort="true" getDataUrl="/vsaadminv3/AdAction!onInit.do" id="gridId" style="width:100%; height:100%;" container="gridDiv" rowSelector="20px">
            <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="50px" />
            <sd:ColumnDataGrid editable="true" key="title.select" field="isCheck" type="checkbox" width="40px" styles="text-align:center;" />
            <sd:ColumnDataGrid editable="false" key="adForm.userName" field="userName" width="100px" formatter="page.view" get="page.getRow"/>
            <sd:ColumnDataGrid editable="false" key="adForm.fullName" field="fullName" width="100px"/>
            <sd:ColumnDataGrid editable="false" key="adForm.telephone" field="telephone" width="100px"/>
            <sd:ColumnDataGrid editable="false" key="adForm.email" field="email" width="100px"/>
            <sd:ColumnDataGrid editable="false" key="adForm.gender" field="gender" width="100px" formatter="page.convertGenderToStr"/>
            <sd:ColumnDataGrid editable="false" key="adForm.status" field="userRight" width="100px" formatter="page.convertAdminStatusToStr"/>
            <sd:ColumnDataGrid editable="false" key="adForm.identityCard" field="identityCard" width="100px"/>
            <sd:ColumnDataGrid editable="false" key="adForm.passportNumber" field="passportNumber" width="100px"/>
            <sd:ColumnDataGrid editable="false" key="adForm.dateOfBirth" field="dateOfBirth" formatter="page.convertDateToString" width="100px"/>
            <sd:ColumnDataGrid editable="false" key="adForm.description" field="description" width="100px"/>
            <sd:ColumnDataGrid editable="false" key="adForm.userId" field="userId" styles="display:none;"/>
        </sd:DataGrid>
    </div>


    <table width="100%" style="padding-top:20px">
        <tr>
            <td>
                <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('gridId');">Select all</span>
                <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('gridId');">Unselect all</span>
            </td>
            <td>
                <div style="text-align:right;margin:5px 5px 0px 0px;float:right;/*width:50%;*/margin-bottom:5px;padding-bottom:5px;">
                    <div style="float:right;">
                        <sd:Button id="adForm.btnInsert" key="" onclick="page.preInsert()">
                            <img src="${contextPath}/share/images/icons/addUser.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                        </sd:Button>
                        <sd:Button id="adForm.btnViewRole" key="" onclick="page.onListRole()">
                            <img src="${contextPath}/share/images/icons/a3.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnViewRole</sd:Property></span>
                        </sd:Button>
                        <sd:Button id="adForm.btnViewUser" key="" onclick="page.onListUsrOfAd()">
                            <img src="${contextPath}/share/images/icons/a3.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnViewUser</sd:Property></span>
                        </sd:Button>
                        <sd:Button id="userReport.excelRole" key="" onclick="page.onExcelRole()" >
                            <img src="share/images/icons/excel.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnExportRole</sd:Property></span>
                        </sd:Button>
                        <sd:Button id="userReport.excelUser" key="" onclick="page.onExcelUser()" >
                            <img src="share/images/icons/excel.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnExportUser</sd:Property></span>
                        </sd:Button>
                        <sd:Button id="adForm.btnDelete" key="" onclick="page.onDelete()">
                            <img src="${contextPath}/share/images/icons/13.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnDelete</sd:Property></span>
                        </sd:Button>
                        <sd:Button id="adForm.btnEnable" key="" onclick="page.onEnable()">
                            <img src="${contextPath}/share/images/icons/unlock.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnEnable</sd:Property></span>
                        </sd:Button>
                        <sd:Button id="adForm.btnDisable" key="" onclick="page.onDisable()">
                            <img src="${contextPath}/share/images/icons/lock.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnDisable</sd:Property></span>
                        </sd:Button>
                        <sd:Button id="adForm.btnGrant" key="" onclick="page.preGrantAd()">
                            <img src="${contextPath}/share/images/icons/dept.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnGrant</sd:Property></span>
                        </sd:Button>
                    </div>
            </td>
        </tr>
    </table>



    <%--<div style="float:right;" onmouseover="page.showArea('ulli')" onmouseout="page.clearArea('ulli')">
        <div>
            <sd:Button id="adForm.btnUpdate" key="" onclick="page.showArea('ulli')">
                <img src="${contextPath}/share/images/icons/a3.png" height="20" width="20">
                <span style="font-size:12px;"><s:property value="getText('btnUpdate')" /></span>
                <img src="${contextPath}/share/images/icons/combo.gif" width="7px" height="4px" />
            </sd:Button>
        </div>
        <div id="ulli" style="width:auto;display:none;z-index:5;margin-top:-3px;margin-left:2px;
             position:absolute;
             -moz-opacity:1;
             -ms-filter:'progid:DXImageTransform.Microsoft.Alpha(Opacity=100)';
             filter: alpha(opacity=100);
             opacity:1;
             border:1px solid;
             background-color:#FBF5EF;">
            <ul id="ula" style="list-style:none;
                background-repeat: no-repeat;
                background-position:left;
                text-decoration: none;
                text-align:left;">
                <li><a href="#" style="text-decoration:none; padding-right:5px;" onclick="page.onListRole()">1. Danh sách vai trò đang quản lý</a></li>
                <li><a href="#" style="text-decoration:none; padding-right:5px;" onclick="page.onListUsrOfAd()">2. Danh sách người dùng đang quản lý</a></li>
            </ul>
        </div>
    </div>--%>
</div>
<br />
<br />
<div id="roleArea"><div style="visibility:hidden;"></div></div>
</sd:TitlePane>
<br />
<script type="text/javascript">
    dijit.byId("deptTreeBelow").onPickData = function(item){
        try{
            if(item.id){
                sd._("adForm.deptId").setValue(item.id);
            }else {
                sd._("adForm.deptId").setValue("");
                dijit.byId("deptTreeBelow").setValue("");
            }
        }catch(err){
            alert(err.message);
        }
    };
    try{
        dijit.byId("adForm.userName").focus();
        sd.widget.__setReadOnly("deptTreeBelow",true);
        page.onEnter("adForm", page.onSearch);
    }catch(err){

    }
</script>