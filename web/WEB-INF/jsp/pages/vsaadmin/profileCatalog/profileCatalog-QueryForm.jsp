<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%--<sd:TitlePane key="profileForm.formTitle" id="tltpn1">
    <form id="profileForm" name="profileForm" >
        <table width="100%">
            <tr>
                <td>
                    <sd:TextBox id="profileForm.allowIp" name="profileForm.allowIp" key="profileForm.allowIp" cssStyle="width:80%"/>
                </td>

                <td>
                    <sd:TextBox id="profileForm.allowLoginTimeEnd" name="profileForm.allowLoginTimeEnd" key="profileForm.allowLoginTimeEnd" cssStyle="width:80%"/>
                </td>

            </tr>
            <tr>
                <td>
                    <sd:TextBox id="profileForm.allowLoginTimeStart" name="profileForm.allowLoginTimeStart" key="profileForm.allowLoginTimeStart" cssStyle="width:80%"/>
                </td>

                <td>
                    <sd:TextBox id="profileForm.allowMultiIpLogin" name="profileForm.allowMultiIpLogin" key="profileForm.allowMultiIpLogin" cssStyle="width:80%"/>
                </td>

            </tr>
            <tr>
                <td>
                    <sd:TextBox id="profileForm.id" name="profileForm.id" key="profileForm.id" cssStyle="width:80%"/>
                </td>

                <td>
                    <sd:TextBox id="profileForm.loginFailAllow" name="profileForm.loginFailAllow" key="profileForm.loginFailAllow" cssStyle="width:80%"/>
                </td>

            </tr>
            <tr>
                <td>
                    <sd:TextBox id="profileForm.maxTmpLockAday" name="profileForm.maxTmpLockAday" key="profileForm.maxTmpLockAday" cssStyle="width:80%"/>
                </td>

                <td>
                    <sd:TextBox id="profileForm.name" name="profileForm.name" key="profileForm.name" cssStyle="width:80%"/>
                </td>

            </tr>
            <tr>
                <td>
                    <sd:TextBox id="profileForm.passwordValidTime" name="profileForm.passwordValidTime" key="profileForm.passwordValidTime" cssStyle="width:80%"/>
                </td>

                <td>
                    <sd:TextBox id="profileForm.temporaryLockTime" name="profileForm.temporaryLockTime" key="profileForm.temporaryLockTime" cssStyle="width:80%"/>
                </td>

            </tr>
            <tr>
                <td>
                    <sd:TextBox id="profileForm.userValidTime" name="profileForm.userValidTime" key="profileForm.userValidTime" cssStyle="width:80%"/>
                </td>
                <td>&nbsp;</td>

            </tr>
        </table>
    </form>
</sd:TitlePane>

<br/>--%>
<script>
    page.editProfile = function(inRow){
        var item = dijit.byId("gridId").getItem(inRow);

        if(item != null){
            var url = "<div><img src='${contextPath}/share/images/icons/edit.png' title='Cập nhật thông tin' onClick='page.preUpdate(" + inRow + ")' height='20' width='20' /></div>";
            return url;
        }
    }

    page.getIndex = function(inRowIndex){
        return inRowIndex+1;
    }
     page.convertAllow = function(intStatus){
        switch(intStatus){
            case 1:
                return "Cho phép";
                case 0:
                    return "Không cho phép";
                    case "1":
                        return "Cho phép";
                        case "0":
                            return "Không cho phép";
                            default :
                                return "...";
                            }
                        }
     page.convertAsign = function(intStatus){
        switch(intStatus){
            case 1:
                return "Thiết lập";
                case 0:
                    return "Không thiết lập";
                    case "1":
                        return "Thiết lập";
                        case "0":
                            return "Không thiết lập";
                            default :
                                return "...";
                            }
                        }
</script>
<sd:TitlePane key="profileForm.gridTitle" id="tltpn2">
    <div align="center">
        <sd:Button id="profileForm.btnSearch" key="" onclick="page.onSearch()" >
            <img src="share/images/icons/3.png" height="20" width="20">
            <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
        </sd:Button>
        <sd:Button id="profileForm.btnInsert" key="" onclick="page.preInsert()" >
            <img src="share/images/icons/a7.png" height="20" width="20">
            <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
        </sd:Button>
        <sd:Button id="profileForm.btnDelete" key="" onclick="page.onDelete()" >
            <img src="share/images/icons/a4.png" height="20" width="20">
            <span style="font-size:12px"><sd:Property>btnDelete</sd:Property></span>
        </sd:Button>
    </div>
    <form id="profileFormOnGrid">
        <div id="gridDiv" style="width:100%;height:400px">
            <sd:DataGrid clientSort="true" escapeHTMLInData="false" getDataUrl="/vsaadminv3/ProfileAction!onInit.do" id="gridId" style="width: 100%; height: 95%;" container="gridDiv" rowSelector="20px">
                <sd:ColumnDataGrid editable="false" key="page.getIndex" get="page.getIndex" width="30px" headerStyles="text-align:center;"/>
                <sd:ColumnDataGrid editable="true"  key="global.checkboxCol" field="checkbox" type="checkbox" width="50px"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.edit" get="page.editProfile" width="50px" />
                <sd:ColumnDataGrid editable="false" key="profileForm.name" field="name" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="profileForm.allowIp" field="allowIp" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="profileForm.allowLoginTimeStart" field="allowLoginTimeStart" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="profileForm.allowLoginTimeEnd" field="allowLoginTimeEnd" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="profileForm.allowMultiIpLogin" field="allowMultiIpLogin" formatter="page.convertAllow" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="profileForm.loginFailAllow" field="loginFailAllow" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="profileForm.maxTmpLockAday" field="maxTmpLockAday" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="profileForm.temporaryLockTime" field="temporaryLockTime" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="profileForm.passwordValidTime" field="passwordValidTime" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="profileForm.userValidTime" field="userValidTime" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="profileForm.needChangePassword" field="needChangePassword" formatter="page.convertAsign" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="profileForm.timeToChangePassword" field="timeToChangePassword" width="100px"/>
            </sd:DataGrid>
        </div>
    </form>
</sd:TitlePane>