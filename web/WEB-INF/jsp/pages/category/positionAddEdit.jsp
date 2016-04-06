<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<jsp:include page="../util/util_js.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sx:ResultMessage id="positionAddEditMessage"/>
<form id="positionAddEditForm" name="positionAddEditForm">

    <sd:TextBox cssStyle="width:100%;display:none"
                id="positionAddEditForm.posId"
                key=""
                name="positionAddEditForm.posId" trim="true"/>

    <table width="100%" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="30%"></td>
            <td width="70%"></td>
        </tr>

        <tr>
            <td align="right">
                <sd:Label key="positionAddEditForm.posCode" cssStyle="100%"/><font color="red">*</font>
            </td>
            <td>
                <sd:TextBox id="positionAddEditForm.posCode" name="positionAddEditForm.posCode"
                            key="" maxlength="20" cssStyle="width:100%" trim="true"/>
            </td>
        </tr>
        <tr>

            <td align="right">
                <sd:Label key="positionAddEditForm.posName" cssStyle="100%"/><font color="red">*</font>
            </td>
            <td>
                <sd:TextBox id="positionAddEditForm.posName" name="positionAddEditForm.posName"
                            key="" maxlength="180" cssStyle="width:100%" trim="true"/>
            </td>  
        </tr>

        <tr>
            <td align="right">
                <sd:Label key="positionAddEditForm.description" cssStyle="100%"/>
            </td>
            <td>
                <sd:Textarea id="positionAddEditForm.description" name="positionAddEditForm.description"
                             key="" maxlength="1500" cssStyle="width:100%" trim="true"/>
            </td> 
        </tr>            

        <tr style="text-align: center">
            <td colspan="2">
                <sx:ButtonSave onclick="page.save();"></sx:ButtonSave>  
                <sx:ButtonClose onclick="page.close();"></sx:ButtonClose>  
                </td>
            </tr>

        </table>
    </form>

    <script>

        page.save = function() {
            var posCode = dijit.byId("positionAddEditForm.posCode");
            var posName = dijit.byId("positionAddEditForm.posName");  

            if (posCode.getValue() == "" ) {
                posCode.focus();
                msg.alert("Hãy nhập mã chức danh",'<sd:Property>confirm.title</sd:Property>');
                return false;
            }
        
            if (posName.getValue() == "" ) {
                posName.focus();
                msg.alert("Hãy nhập tên chức danh",'<sd:Property>confirm.title</sd:Property>');
                return false;
            }
            sd.connector.post("position!onInsert.do?"+ token.getTokenParamString(), null, "positionAddEditForm", null, page.afterInsert);   
        }    
     
        page.afterInsert = function(data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            grid.vtReload('position!onSearch.do?', "positionSearchForm",null,null); 

            if (insertDialog) {
                resultMessage_show("positionAddEditMessage", result[0], result[1], 5000);
                dijit.byId("positionAddEditForm.posCode").focus();
//                dijit.byId("positionAddEditForm.posCode").setValue("");
//                dijit.byId("positionAddEditForm.posName").setValue("");
//                dijit.byId("positionAddEditForm.description").setValue("");
            } else {
                page.close();
                resultMessage_show("positionDeleteMessage", result[0], result[1], 5000);
            }

        }     

        page.close = function() {
            dijit.byId("positionAddEditForm.posCode").setValue("");
            dijit.byId("positionAddEditForm.posName").setValue("");
            dijit.byId("positionAddEditForm.description").setValue("");
            dlgAddEditPosition.hide();            
        }  
   
</script>
