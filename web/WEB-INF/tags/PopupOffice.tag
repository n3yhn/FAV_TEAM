<%-- 
    Document   : PopupOffice
    Created on : June 1, 2012
    Author     : SyTV
--%>

<%@tag description="Popup Choose Department" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="id" %> 
<%@attribute name="keyId" required="true"%>
<%@attribute name="keyName" required="true"%>
<%@attribute name="readonlyArea"%>
<%@attribute name="disableImage"%>
<table width="100%" border="0"  cellspacing="0" cellpadding="0">
    <tr>
        <td style="width:100%">
            <sd:TextBox id="${keyId}" name="${keyId}" key="" cssStyle="display:none;" trim="true"/>
            <sd:TextBox id="${keyName}" name="${keyName}" key="" readonly="${readonlyArea}" trim="true" cssStyle="width:100%"/>
        </td>
        <td style="text-align: right;width: 20px;">
            <img width="16" height="16" title="Chọn" 
                 alt="Chọn" src="share/images/icons/comment.png" 
                 onclick="page.officeOutsiteSelect();"
                 style="cursor: pointer; cursor: hand; margin: 1px 4px;">
        </td>
        <td style="text-align: right;width: 20px;">
            <img width="16" height="16" title="Bỏ chọn" 
                 alt="Bỏ chọn" src="share/img/delete.png" 
                 onclick="page.officeOutsiteRemove();"
                 style="cursor: pointer; cursor: hand; margin: 1px 2px;">
        </td>   
    </tr>
</table>
<sd:Dialog id="${id}DialogOffice" key="office.titleDlgOffice" height="auto" width="600">
    <div id="${id}UpdateOfficeDiv">     
    </div>
</sd:Dialog>
<script type="text/javascript">
   
    var updateDiv="${id}UpdateOfficeDiv";
  
    
    var id="${keyId}";
    var name="${keyName}";
    alert(id);
    page.officeOutsiteSelect = function() {
        dijit.byId("${id}DialogOffice").show();
        sd.connector.post("voOutsiteOffice.do", updateDiv, null, null);    
    }
    
    page.officeOutsiteRemove =function() {
        document.getElementById("${keyId}").value = "";
        document.getElementById("${keyName}").value = "";            
    }
    page.Close = function() {
        dijit.byId("${id}DialogOffice").hide();
    }
    page.Select = function() {
        var officeId = dijit.byId("outsiteOffice").attr("value");
        var selected = document.getElementById("outsiteOffice").selectedIndex;
        var officeName=document.getElementById("outsiteOffice").options[selected].text;
        document.getElementById(id).value = officeId;
        document.getElementById(name).value = officeName;
        dijit.byId("${id}DialogOffice").hide();
    }
</script>

