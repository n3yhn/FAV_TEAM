<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<sd:TitlePane key="unitTreeForm.title" id="tltpn">
    <table width="100%">
        <tr>
            <td width="30%" valign="top">
                <div align="center">

                    <sd:Button id="locationForm.btnInsert" key="" onclick="page.preInsert()" >
                        <img src="share/images/icons/a7.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                    </sd:Button>
                    <sd:Button id="locationForm.btnUpdate" key="" onclick="page.preUpdate()" >
                        <img src="share/images/icons/a3.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnUpdate</sd:Property></span>
                    </sd:Button>
                    <sd:Button id="locationForm.btnDelete" key="" onclick="page.onDelete()" >
                        <img src="share/images/icons/a4.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnDelete</sd:Property></span>
                    </sd:Button>
                </div>
                <br/>
                <table class="treeContainer1">
                    <tr>
                        <td class="padding-10">
                            <div class="treeDiv1">
                                <sd:AjaxTree id="treeId" rootLabel="unitTreeSelection"
                                             getChildrenUrl="/vsaadminv3/UnitTreeAction!getChildrenDataByNode.do"
                                             getTopLevelUrl="/vsaadminv3/UnitTreeAction!getData.do"
                                             onClick="page.onTreeClick" />
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
            <td width="70%" valign="top">
                <sd:TitlePane key="global.detail" id="tltpn1">
                    <form id="unitTreeForm" name="unitTreeForm">
                        <table width="100%">
                            <tr>
                                <td>
                                    <sd:TextBox id="unitTreeForm.deptCode" name="unitTreeForm.deptCode" key="unitTreeForm.dept.code" cssStyle="width:80%" trim="true" readonly="true"/>
                                </td>
                                <td>
                                    <sd:TextBox id="unitTreeForm.deptName" name="unitTreeForm.deptName" key="unitTreeForm.dept.name" cssStyle="width:80%" trim="true" readonly="true"/>
                                </td>
                                <td>
                                    <sd:TextBox id="unitTreeForm.deptPhone" name="unitTreeForm.deptPhone" key="unitTreeForm.dept.phone" cssStyle="width:80%" trim="true" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <sd:TextBox id="unitTreeForm.deptEmail" name="unitTreeForm.deptEmail" key="unitTreeForm.dept.email" cssStyle="width:80%" trim="true" readonly="true"/>
                                </td>
                                <td>
                                    <sd:TextBox id="unitTreeForm.deptAddress" name="unitTreeForm.deptAddress" key="unitTreeForm.dept.address" cssStyle="width:80%" trim="true" readonly="true"/>
                                </td>
                                <td>
                                    <sd:TextBox id="unitTreeForm.deptDescription" name="unitTreeForm.deptDescription" key="unitTreeForm.dept.description" cssStyle="width:80%" trim="true" readonly="true"/>
                                </td>
                            </tr>
                        </table>
                        <s:hidden name="unitTreeForm.parentId" id="unitTreeForm.parentId" value="0"/>
                    </form>
                    <br/>
                    <div align="right">                      
                        <sd:Button id="locationForm.btnAdDept" key="" onclick="page.onAddDept()" >
                            <img src="share/images/icons/a7.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>unitTreeForm.adDept</sd:Property></span>
                        </sd:Button>

                        <sd:Button id="locationForm.btnReDept" key="" onclick="page.onReDept()" >
                            <img src="share/images/icons/a4.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>unitTreeForm.reDept</sd:Property></span>
                        </sd:Button>

                    </div>
                    <br/>
                    <sd:TitlePane key="usersForm.userTitle" id="tltpn3">
                        <form id="form1">
                            <div id="gridDiv" style="width:100%;height:400px">

                                <sd:DataGrid clientSort="true" getDataUrl="/vsaadminv3/UnitTreeAction!onInit.do?" id="gridId" style="width: 100%; height: 100%;" container="gridDiv" rowSelector="20px">
                                    <sd:ColumnDataGrid editable="false" key="usersForm.checkboxCol" field="isCheck" width="10%" type="checkbox"/>
                                    <sd:ColumnDataGrid editable="false" key="usersForm.userName" field="userName" width="20%"/>
                                    <sd:ColumnDataGrid editable="false" key="usersForm.fullName" field="fullName" width="20%"/>
                                    <sd:ColumnDataGrid editable="false" key="usersForm.email" field="email" width="20%"/>
                                    <sd:ColumnDataGrid editable="false" key="usersForm.cellphone" field="cellphone" width="15%"/>
                                    <sd:ColumnDataGrid editable="false" key="usersForm.staffCode" field="staffCode" width="15%"/>
                                </sd:DataGrid>
                            </div>
                            <table width="100%" style="padding-top:20px">
                                <tr>
                                    <td>
                                        <div style="padding-top:5px" align="left">
                                            <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('gridId');">Select all</span>
                                            <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('gridId');">Unselect all</span>
                                        </div>
                                    </td>
                                    <td>
                                        <div align="right">
                                            <sd:Button id="locationForm.btnAdUser" key="" onclick="page.onAddUser()" >
                                                <img src="share/images/icons/a7.png" height="20" width="20">
                                                <span style="font-size:12px"><sd:Property>unitTreeForm.adUser</sd:Property></span>
                                            </sd:Button>

                                            <sd:Button id="locationForm.btnReUser" key="" onclick="page.onReUser()" >
                                                <img src="share/images/icons/a4.png" height="20" width="20">
                                                <span style="font-size:12px"><sd:Property>unitTreeForm.reUser</sd:Property></span>
                                            </sd:Button>
                                        </div>
                                    </td>
                                </tr>
                            </table>

                        </form>
                    </sd:TitlePane>
                </sd:TitlePane>
            </td>
        </tr>
    </table>
</sd:TitlePane>