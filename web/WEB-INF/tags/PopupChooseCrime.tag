<%-- 
    Document   : AddButton
    Created on : Dec 7, 2011, 3:32:13 PM
    Author     : huantv
--%>

<%@tag description="Accept Button" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="keyId"%>
<%@attribute name="keyName"%>
<%@attribute name="readonlyArea"%>
<%@attribute name="disableImage"%>

<sd:TextBox id="${keyId}" name="${keyId}" key="" cssStyle="display:none;" trim="true"/>
<sd:Textarea id="${keyName}" name="${keyName}" onclick="page.showPopupCrime();" key="" readonly="${readonlyArea}" rows="2" trim="true" cssStyle="width:100%"/>
<sd:Button key="" onclick="page.showPopupCrime();" disabled="${disableImage}">
    <img src="share/images/icons/6.png" height="16" width="16" title="Thêm tội danh">                        
</sd:Button>
<sd:Button key="" onclick="page.delCrime();" disabled="${disableImage}">
    <img src="share/images/icons/13.png" height="16" width="16" title="Xóa tội danh">
</sd:Button>    
<sd:Dialog id="dlgCrime" key="jpJudment.choseCrime" height="auto" width="80%">
    <div id="updateCrimeDiv">     
    </div>
</sd:Dialog> 
<script type="text/javascript">
    
    var isChoosed = false;
    page.showPopupCrime = function(){
        
        if(!isChoosed && "${disableImage}" != "true")
        {
            dijit.byId("dlgCrime").show();
            sd.connector.post("category!prepareChooseCrime.do", "updateCrimeDiv", null, null);       
        }        
    }
    page.delCrime = function(){          
        if(document.getElementById("${keyId}") != null)
        {
            document.getElementById("${keyId}").value = "";
        }
        
        if(document.getElementById("${keyName}") != null)
        {              
            document.getElementById("${keyName}").value = "";            
        }
    }
    page.fillValue = function(idValue, nameValue)
    {
        if(nameValue != null && nameValue.length > 500)
        {
            nameValue = nameValue.substring(0, 500);
        }
        
        if(document.getElementById("${keyId}") != null)
        {
            document.getElementById("${keyId}").value += idValue;
        }
        
        if(document.getElementById("${keyName}") != null)
        {              
            document.getElementById("${keyName}").value += nameValue;            
        }
    }
    page.closeCrimeDialog = function(){
        dijit.byId("dlgCrime").hide();
        
        //        if(document.getElementById("${keyName}") != null)
        //        {
        //            isChoosed = true;
        //            document.getElementById("${keyName}").focus();
        //            isChoosed = false;               
        //        }
    }
</script>
