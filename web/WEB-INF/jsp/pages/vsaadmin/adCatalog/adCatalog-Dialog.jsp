<%--
    Document   : adCatalog-Dialog
    Created on : Jan 4, 2010, 9:04:13 AM
    Author     : duongtb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--[select user--%>
<div style="display:inline-block;width:100%;">
    <s:if test="%{#request.action=='add'}">
        <div style="float:left;width:15%;display:inline-block;">
            <span id="step1" style="color:#0B243B;font-weight:bold;cursor:pointer;">
                1.<sd:Property>js.alertSelectUser</sd:Property>
            </span><br />
            <span id="step2">2.<sd:Property>js.alertSelectRole</sd:Property></span>
            <script type="text/javascript">
                try{
                    document.getElementById("core").style.width="85%";
                }catch(err){
                    alert(err.message);
                }
            </script>
        </div>
    </s:if>
    <div id="core" style="float:right;width:100%;">
        <div id="panel1" style="display:block;">
            <form id="adFormOnDialog" name="adForm">
                <table width="100%;">
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
                            <sd:TextBox id="adFormOnDialog.userName" name="adForm.userName" key="" cssStyle="width:80%;"/>
                        </td>
                        <td>
                            <sd:Label key="adForm.fullName"/>
                        </td>
                        <td>
                            <sd:TextBox id="adFormOnDialog.fullName" name="adForm.fullName" key="" cssStyle="width:80%;"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <sd:Label key="adForm.telephone"/>
                        </td>
                        <td>
                            <sd:TextBox id="adFormOnDialog.telephone" name="adForm.cellphone" key="" cssStyle="width:80%;"/>
                        </td>
                        <td>
                            <sd:Label key="adForm.email"/>
                        </td>
                        <td>
                            <sd:TextBox id="adFormOnDialog.email" name="adForm.email" key="" cssStyle="width:80%;"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <sd:Label key="usersForm.department"/>
                        </td>
                        <td>
                            <sd:TreePicker id="deptTree" getChildrenNodeUrl="UserAction!getChildrenDataByNode.do" getTopNodeUrl="UserAction!getData.do" key="" rootLabel="root" cssStyle="width:80%" popupStyle="ajax"/>
                            <div id="di" style="text-align:left;margin-left:30%;"><a style="text-decoration:none;" href="#" onclick="page.clearTreeOnDialog()">Bỏ chọn</a></div>
                            <div style="display:none;"><sd:TextBox id="adFormOnDialog.deptId" name="adForm.deptId" key="adForm.deptId"/></div>
                        </td>
                        <td>
                            <sd:Label key="adForm.status"/>
                        </td>
                        <td>
                            <s:if test="%{#request.action!='grant' && #request.action!='entrust'}">
                                <s:if test="%{#request.action=='add'}">
                                    <sd:SelectBox readonly="true" key="" name="adForm.status" id="adFormOnDialog.status" cssStyle="width:80%;">
                                <option value="1"><sd:Property>slt.active</sd:Property></option>
                                <%--<option value="0"><s:property value="getText('slt.deactive')" /></option>--%>
                            </sd:SelectBox>
                        </s:if>
                        <s:else>
                            <sd:SelectBox key="" name="adForm.status" id="adFormOnDialog.status" cssStyle="width:80%;">
                                <option value="10"><sd:Property>slt.all</sd:Property></option>
                                <option value="1"><sd:Property>slt.active</sd:Property></option>
                                <option value="0"><sd:Property>slt.deactive</sd:Property></option>
                            </sd:SelectBox>
                        </s:else>
                    </s:if>
                    </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td style="display:none;">
                            <sd:TextBox id="adFormOnDialog.userId" name="adForm.userId" key="adForm.userId" />
                        </td>
                    </tr>
                </table>

                <div style="text-align:center;">
                    <sd:Button id="adFormOnDialog.btnUpdate" key="" onclick="page.searchOnDialog('${fn:escapeXml(action)}')">
                        <img src="${contextPath}/share/images/icons/a1.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
                    </sd:Button>
                    <s:if test="%{#request.action=='grant'}">
                        <sd:Button id="adFormOnDialog.btnInsert" key="" onclick="page.onGrant()">
                            <img src="${contextPath}/share/images/icons/save.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnSave</sd:Property></span>
                        </sd:Button>
                    </s:if>
                    <s:if test="%{#request.action=='get'}">
                        <sd:Button id="adFormOnDialog.btnInsert" key="" onclick="page.onGet()">
                            <img src="${contextPath}/share/images/icons/save.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnSave</sd:Property></span>
                        </sd:Button>
                    </s:if>
                    <s:if test="%{#request.action=='entrust'}">
                        <sd:Button id="adFormOnDialog.btnInsert" key="" onclick="page.onEntrust()">
                            <img src="${contextPath}/share/images/icons/save.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnSave</sd:Property></span>
                        </sd:Button>
                    </s:if>
                </div>
            </form>
            <%--<form id="frm">--%>
            <div id="gridUserDiv" style="width:100%;height:300px">
                <s:if test="%{#request.action=='get'}">
                    <%
                    request.setAttribute("init", "/vsaadminv3/AdAction!onListUsrToReceive.do");
                    %>
                </s:if>
                <s:elseif test="%{#request.action=='entrust'}">
                    <%
                   request.setAttribute("init", "/vsaadminv3/AdAction!onListAdToEntrust.do");
                    %>
                </s:elseif>
                <s:elseif test="%{#request.action=='add'}">
                    <%
                   request.setAttribute("init", "/vsaadminv3/AdAction!onInitWhenAddingAd.do");
                    %>
                </s:elseif>
                <s:elseif test="%{#request.action=='grant'}">
                    <%
                   request.setAttribute("init", "/vsaadminv3/AdAction!onInitWhenGranting.do");
                    %>
                </s:elseif>
                <s:else>
                    <%
                    request.setAttribute("init", "/vsaadminv3/AdAction!onInit.do");
                    %>
                </s:else>
                <sd:DataGrid clientSort="true" getDataUrl="${init}" id="gridUserId" style="width:100%; height:100%;" container="gridUserDiv" rowSelector="20px">
                    <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="50px" />
                    <s:if test="%{ #request.action!='grant'&& #request.action!='entrust' }">
                        <sd:ColumnDataGrid editable="true" key="btnSelect" field="isCheck" type="checkbox" width="40px" styles="text-align:center;" />
                    </s:if>
                    <sd:ColumnDataGrid editable="false" key="adForm.userName" field="userName" width="100px" formatter="page.viewOnDlg" get="page.getRow"/>
                    <sd:ColumnDataGrid editable="false" key="adForm.fullName" field="fullName" width="100px"/>
                    <sd:ColumnDataGrid editable="false" key="adForm.telephone" field="cellphone" width="100px"/>
                    <sd:ColumnDataGrid editable="false" key="adForm.email" field="email" width="100px"/>
                    <sd:ColumnDataGrid editable="false" key="adForm.gender" field="gender" width="100px" formatter="page.convertGenderToStr"/>
                    <s:if test="%{#request.action!='grant' && #request.action!='entrust'}">
                        <sd:ColumnDataGrid editable="false" key="adForm.status" field="status" width="100px" formatter="page.convertStatusToStr"/>
                    </s:if>
                    <%--<s:if test="%{#request.action=='get'}">
                        <sd:ColumnDataGrid editable="false" key="manager" field="managerName" width="100px"/>
                    </s:if>--%>
                    <sd:ColumnDataGrid editable="false" key="adForm.identityCard" field="identityCard" width="100px"/>
                    <sd:ColumnDataGrid editable="false" key="adForm.passportNumber" field="passportNumber" width="100px"/>
                    <sd:ColumnDataGrid editable="false" key="adForm.dateOfBirth" field="dateOfBirth" width="100px" formatter="page.convertDateToString"/>
                    <sd:ColumnDataGrid editable="false" key="adForm.description" field="description" width="100px"/>
                    <sd:ColumnDataGrid editable="false" key="adForm.userId" field="userId" styles="display:none;"/>
                </sd:DataGrid>
            </div>
            <s:if test="%{#request.action!='grant' && #request.action!='entrust'}">
                <div style="padding-top:20px">
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('gridUserId');">Select all</span>
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('gridUserId');">Unselect all</span>
                </div>
            </s:if>
            <%--</form>--%>
        </div>
        <%--]select user--%>
        <%--[select role--%>
        <div id="panel2" style="display:none;"></div>
        <%--]select role--%>

        <form id="wizard" action="">
            <div style="text-align:center;margin-top:5px;">
                <s:if test="%{#request.action=='add'}">
                    <sd:Button id="adFormOnDialog.btnback" key="" onclick="page.onBack()" disabled="true">
                        <img src="${contextPath}/share/images/icons/back.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnback</sd:Property></span>
                    </sd:Button>
                    <sd:Button id="adFormOnDialog.btnInsert" key="" onclick="page.onInsert()" disabled="true">
                        <img src="${contextPath}/share/images/icons/save.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnSave</sd:Property></span>
                    </sd:Button>
                    <sd:Button id="adFormOnDialog.btnContinue" key="" onclick="page.onContinue()">
                        <img src="${contextPath}/share/images/icons/next.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnContinue</sd:Property></span>
                    </sd:Button>
                </s:if>
            </div>
        </form>                
    </div>
</div>
<script type="text/javascript">
    try{
        dijit.byId("adFormOnDialog.userName").focus();
    }catch(err){

    }
    dijit.byId("deptTree").onPickData = function(item){
        try{
            if(item.id){
                sd._("adFormOnDialog.deptId").setValue(item.id);
            }else {
                sd._("adFormOnDialog.deptId").setValue("");
                dijit.byId("deptTree").setValue("");
            }
        }catch(err){
            alert(err.message);
        }
    }
    try{
        sd.widget.__setReadOnly("deptTree",true);
    }catch(e){

    }
    try{
        page.enterOnDialog("adFormOnDialog", '${fn:escapeXml(action)}');
    }catch(e){

    }
</script>