<%-- 
    Document   : outsiteOfficeAddEdit
    Created on : Jun 16, 2012, 9:57:12 AM
    Author     : HanPT1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<jsp:include page="../util/util_js.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sd:TitlePane key="" id="">
    <sx:ResultMessage id="positionAddEditMessage"/>
    <form id="outsiteOfficeAddEditForm" name="outsiteOfficeAddEditForm">

        <sd:TextBox cssStyle="width:100%;display:none"
                    id="outsiteOfficeAddEditForm.officeId"
                    key=""
                    name="outsiteOfficeAddEditForm.officeId"
                    >
        </sd:TextBox> 

        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <td width="20%"></td>
                <td width="30%"></td>
                <td width="20%"></td>
                <td width="30%"></td>
            </tr>

            <tr>
                <td align="right">
                    <sd:Label key="outsiteOfficeAddEditForm.officeName" cssStyle="100%"/><span style="color:red">*</span>
                </td>
                <td>
                    <sd:TextBox id="outsiteOfficeAddEditForm.officeName" name="outsiteOfficeAddEditForm.officeName"
                                key="" maxlength="250" cssStyle="width:100%">
                    </sd:TextBox>
                </td>
                
                <td align="right">
                    <sd:Label key="outsiteOfficeAddEditForm.code" cssStyle="100%"/><span style="color:red">*</span>
                </td>
                <td>
                    <sd:TextBox id="outsiteOfficeAddEditForm.code" name="outsiteOfficeAddEditForm.code"
                                key="" maxlength="20" cssStyle="width:100%">
                    </sd:TextBox>
                </td>                        
            </tr>

            <tr>
                <td align="right">
                    <sd:Label key="outsiteOfficeAddEditForm.officeLevel" cssStyle="100%"/>
                </td>
                <td>
                    <sd:SelectBox id="outsiteOfficeAddEditForm.officeLevelId" 
                                  key="" 
                                  name="outsiteOfficeAddEditForm.officeLevelId" 
                                  data="officeLevelList" 
                                  labelField="officeLevelName" valueField="officeLevelId"
                                  cssStyle="width:100%"
                                  />                    
                </td>

                <td align="right">
                    <sd:Label key="outsiteOfficeAddEditForm.leader" cssStyle="100%"/>
                </td>
                <td>
                    <sd:TextBox id="outsiteOfficeAddEditForm.leader" name="outsiteOfficeAddEditForm.leader"
                                key="" maxlength="100" cssStyle="width:100%">
                    </sd:TextBox>
                </td>  
            </tr>  

            <tr>
                <td align="right">
                    <sd:Label key="outsiteOfficeAddEditForm.address" cssStyle="100%"/>
                </td>
                <td>
                    <sd:TextBox id="outsiteOfficeAddEditForm.address" name="outsiteOfficeAddEditForm.address"
                                key="" maxlength="250" cssStyle="width:100%">
                    </sd:TextBox>
                </td>      

                <td align="right">
                    <sd:Label key="outsiteOfficeAddEditForm.telephone" cssStyle="100%"/>
                </td>
                <td>
                    <sd:TextBox id="outsiteOfficeAddEditForm.telephone" name="outsiteOfficeAddEditForm.telephone"
                                key="" maxlength="11" cssStyle="width:100%" mask="digit">
                    </sd:TextBox>
                </td>      
            </tr>

            <tr>
                <td align="right">
                    <sd:Label key="outsiteOfficeAddEditForm.email" cssStyle="100%"/>
                </td>
                <td>
                    <sd:TextBox id="outsiteOfficeAddEditForm.email" name="outsiteOfficeAddEditForm.email"
                                key="" maxlength="50" cssStyle="width:100%">
                    </sd:TextBox>     
                </td>          

                <td align="right">
                    <sd:Label key="outsiteOfficeAddEditForm.officeComment" cssStyle="100%"/>
                </td>
                <td>
                    <sd:TextBox id="outsiteOfficeAddEditForm.officeComment" name="outsiteOfficeAddEditForm.officeComment"
                                key="" maxlength="250" cssStyle="width:100%">
                    </sd:TextBox>
                </td>  
            </tr>

            <tr>
                <td align="right">
                    <sd:Label key="outsiteOfficeAddEditForm.receiveDocAddress" cssStyle="100%"/>
                </td>
                <td colspan="3">
                    <sd:Textarea id="outsiteOfficeAddEditForm.receiveDocAddress" name="outsiteOfficeAddEditForm.receiveDocAddress"
                                 key="" maxlength="250" cssStyle="width:100%" rows="2">
                    </sd:Textarea>
                </td>         
            </tr>

            <tr style="text-align: center">
                <td colspan="4">
                    <sx:ButtonSave onclick="page.save();"></sx:ButtonSave>  
                    <sx:ButtonClose onclick="page.close();"></sx:ButtonClose>  
                </td>
            </tr>

        </table>
    </form>
</sd:TitlePane>

<script>

    page.save = function() {
        var code = dijit.byId("outsiteOfficeAddEditForm.code");
        var officeName = dijit.byId("outsiteOfficeAddEditForm.officeName");  

        if (code.getValue() == "" ) {
            code.focus();
            msg.alert("Hãy nhập mã đơn vị",'<sd:Property>confirm.title</sd:Property>');
            return false;
        }
        
        if (officeName.getValue() == "" ) {
            officeName.focus();
            msg.alert("Hãy nhập tên đơn vị",'<sd:Property>confirm.title</sd:Property>');
            return false;
        }
        sd.connector.post("voOutsiteOffice!onInsert.do?"+ token.getTokenParamString(), null, "outsiteOfficeAddEditForm", null, page.afterInsert);   
    }    
     
    page.afterInsert = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        grid.vtReload('voOutsiteOffice!onSearch.do?', "outsiteOfficeSearchForm",null,null); 

        if (insertDialog) {
            resultMessage_show("positionAddEditMessage", result[0], result[1], 5000);
            dijit.byId("outsiteOfficeAddEditForm.officeId").setValue("");
            dijit.byId("outsiteOfficeAddEditForm.code").setValue("");
            dijit.byId("outsiteOfficeAddEditForm.officeName").setValue("");
            dijit.byId("outsiteOfficeAddEditForm.officeLevelId").setValue("");
            dijit.byId("outsiteOfficeAddEditForm.email").setValue("");
            dijit.byId("outsiteOfficeAddEditForm.address").setValue("");
            dijit.byId("outsiteOfficeAddEditForm.receiveDocAddress").setValue("");
            dijit.byId("outsiteOfficeAddEditForm.leader").setValue("");
            dijit.byId("outsiteOfficeAddEditForm.officeComment").setValue("");
            dijit.byId("outsiteOfficeAddEditForm.telephone").setValue("");
        } else {
            page.close();
            resultMessage_show("positionDeleteMessage", result[0], result[1], 5000);
        }

    }     

    page.close = function() {
        dijit.byId("outsiteOfficeAddEditForm.officeId").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.code").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.officeName").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.officeLevelId").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.email").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.address").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.receiveDocAddress").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.leader").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.officeComment").setValue("");
        dijit.byId("outsiteOfficeAddEditForm.telephone").setValue("");
        dlgAddEditOutsiteOffice.hide();            
    }  
   
</script>
