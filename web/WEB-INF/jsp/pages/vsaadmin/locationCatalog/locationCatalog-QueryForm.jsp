<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<link rel="stylesheet" href="${contextPath}/share/css/formStyle.css" charset="UTF-8" type="text/css" />

<script>
    dijit.byId("locationForm.codeSearch").focus();

    page.searchDefault = function(evt){
        var dk = dojo.keys;
        switch(evt.keyCode){
            case dk.ENTER:
                page.onSearch();
                break;
        }
    }

    page.editLocation = function(inRow){
        var item = dijit.byId("gridId").getItem(inRow);

        if(item != null){
            var url = "<div><img src='${contextPath}/share/images/icons/edit.png' title='Cập nhật thông tin' onClick='page.preUpdate(" + inRow + ")' height='20' width='20' /></div>";
            return url;
        }
    }

    dojo.connect(dojo.byId("filterFormDiv"),"onkeypress",page.searchDefault);
</script>

<sd:TitlePane key="LocationAction.page" id="tltpn0">
    <table width="100%">
        <tr>
            <td width="25%" valign="top">
                <table class="treeContainer1">
                    <tr>
                        <td class="padding-10">
                            <div class="treeDiv1">
                                <sd:AjaxTree id="treeId" rootLabel="locationTree"
                                             getChildrenUrl="/vsaadminv3/LocationAction!getChildrenDataByNode.do"
                                             getTopLevelUrl="/vsaadminv3/LocationAction!getData.do"
                                             onClick="page.onTreeClick" />
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
            <td width="75%"  valign="top">
                <sd:TitlePane key="locationForm.title" id="tltpn">
                    <div id="filterFormDiv">
                        <form id="locationForm" name="locationForm">
                            <table width="100%">
                                <tr>
                                    <td>

                                        <s:hidden name="locationForm.parentId" id="locationForm.parentId"/>
                                        <sd:TextBox id="locationForm.codeSearch" name="locationForm.codeSearch" key="locationForm.locationCode" cssStyle="width:80%" trim="true"/>
                                    </td>
                                    <td>
                                        <sd:TextBox id="locationForm.nameSearch" name="locationForm.nameSearch" key="locationForm.locationName" cssStyle="width:80%" trim="true"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <div align="center">
                        <sd:Button id="locationForm.btnSearch" key="" onclick="page.onSearch()" >
                            <img src="share/images/icons/3.png" height="20" width="20">
                            <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
                        </sd:Button>
                    </div>

                    <br/>

                    <form id="form1">
                        <div id="gridDiv" style="width:100%;height:300px">
                            <sd:DataGrid clientSort="true" escapeHTMLInData="false" getDataUrl="/vsaadminv3/LocationAction!onInit.do" id="gridId" style="width: 100%; height: 100%;" container="gridDiv" rowSelector="20px">
                                <sd:ColumnDataGrid editable="true"  key="global.checkboxCol" field="isCheck" width="10%" type="checkbox" />
                                <sd:ColumnDataGrid editable="false" key="locationForm.locationCode" field="locationCode" width="20%"/>
                                <sd:ColumnDataGrid editable="false" key="locationForm.locationName" field="locationName" width="30%"/>
                                <sd:ColumnDataGrid editable="false" key="positionForm.description" field="description" width="40%"/>
                                <sd:ColumnDataGrid editable="false"  key="usersForm.edit" get="page.editLocation" width="50px" />
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
                                        <sd:Button id="locationForm.btnInsert" key="" onclick="page.preInsert()" >
                                            <img src="share/images/icons/a7.png" height="20" width="20">
                                            <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                                        </sd:Button>
                                        <sd:Button id="locationForm.btnDelete" key="" onclick="page.onDelete()" >
                                            <img src="share/images/icons/a4.png" height="20" width="20">
                                            <span style="font-size:12px"><sd:Property>btnDelete</sd:Property></span>
                                        </sd:Button>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>
                </sd:TitlePane>

            </td>
        </tr>
    </table>
</sd:TitlePane>