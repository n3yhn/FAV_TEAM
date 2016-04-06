<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<sd:TitlePane key="positionForm.title" id="tltpn">
    <form id="positionForm" name="positionForm">
        <table width="100%">
            <tr>
                <td>
                    <sd:TextBox id="positionForm.code" name="positionForm.code" key="positionForm.code" cssStyle="width:80%" trim="true"/>
                </td>

                <td>
                    <sd:TextBox id="positionForm.posName" name="positionForm.posName" key="positionForm.posName" cssStyle="width:80%" trim="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    <sd:SelectBox key="positionForm.status" name="positionForm.status" id="positionForm.status" cssStyle="width:80%;">
                <option value="10"><sd:Property>slt.all</sd:Property></option>
                <option value="1"><sd:Property>slt.active</sd:Property></option>
                <option value="0"><sd:Property>slt.deactive</sd:Property></option>
            </sd:SelectBox>
            </td>
            <td>
                <sd:TextBox id="positionForm.description" name="positionForm.description" key="positionForm.description" cssStyle="width:80%" trim="true"/>
            </td>
            </tr>
        </table>
        <div style="text-align:center;">
            <sd:Button id="positionForm.btnSearch" key="" onclick="page.onSearch()">
                <img src="${contextPath}/share/images/icons/a1.png" height="20" width="20">
                <span style="font-size:12px"><sd:Property>btnSearch</sd:Property></span>
            </sd:Button>
        </div>
    </form>
    <br/>
    <form id="form1">
        <div id="gridDiv" style="width:100%;height:400px">

            <sd:DataGrid clientSort="true" 
                         rowsPerPage="20"
                         getDataUrl="/vsaadminv3/PositionAction!onInit.do" 
                         id="gridId" 
                         style="width: 100%; height: 100%;" 
                         container="gridDiv" 
                         rowSelector="20px">
                <sd:ColumnDataGrid editable="false" key="index" get="page.getIndex" width="20px" />
                <sd:ColumnDataGrid editable="true" key="title.select" field="isCheck" width="40px" styles="text-align:center;" type="checkbox"/>
                <sd:ColumnDataGrid editable="false" key="positionForm.code" field="code" width="100px"/>
                <sd:ColumnDataGrid editable="false" key="positionForm.posName" field="posName" width="300px"/>
                <sd:ColumnDataGrid editable="false" key="positionForm.description" field="description" width="300px"/>
                <sd:ColumnDataGrid editable="false" key="positionForm.status" field="status" width="100px" formatter="page.convertStatusToStr"/>
                <sd:ColumnDataGrid editable="false" key="usersForm.edit" field="" width="100px" get="page.urlEditPosition" styles="text-align:center;"/>
                <sd:ColumnDataGrid editable="false" key="positionForm.posId" field="posId" styles="display:none;"/>
            </sd:DataGrid>
        </div>
    </form>
    <table style="padding-top:20px">
        <tr>
            <td>
                <div >
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.selectAll('gridId');">Select all</span>
                    <span style="color:blue; text-decoration: underline; cursor: pointer" onclick="page.unSelectAll('gridId');">Unselect all</span>
                </div>
            </td>
            <td>
                <div style="text-align:right;margin-top:5px;margin-right:5px;">
                    <sd:Button id="positionForm.btnInsert" key="" onclick="page.preInsert()">
                        <img src="${contextPath}/share/images/icons/6.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnInsert</sd:Property></span>
                    </sd:Button>
                    <sd:Button id="positionForm.btnDelete" key="" onclick="page.onDelete()">
                        <img src="${contextPath}/share/images/icons/13.png" height="20" width="20">
                        <span style="font-size:12px"><sd:Property>btnDelete</sd:Property></span>
                    </sd:Button>
                </div>
            </td>
        </tr>
    </table>


</sd:TitlePane>
<script type="text/javascript">
    try{
        dijit.byId("positionForm.code").focus();
        page.onEnter("positionForm", page.onSearch);
    }catch(err){

    }
</script>