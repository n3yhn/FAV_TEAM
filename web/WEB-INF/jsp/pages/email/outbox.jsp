<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils" %> 
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../util/util_js.jsp"/>
<jsp:include page="../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>

<div id="token">
    <s:token id="tokenId"/>
</div> 
<sx:ResultMessage id="validationMessage"/>
<script type="text/javascript" charset="utf-8">
    page.getNo = function(index) {
        return dijit.byId("filesGrid").currentRow + index + 1;
    }
    var currentpage = "outbox";
    page.getIndex = function(index) {
        return index + 1;
    }

    page.emailTo =function(dataGrid) {
        var item= dijit.byId("emailGrid").getItem(dataGrid-1);
        var url="";
        if (item!=null) {
            var msgNum = dataGrid-1;
            var to =item.receiver;
            var isRead = item.flag;
            if(isRead=="read"){
                if (to != null){
                    url = "<span onclick='page.readMail("+ msgNum +","+false+");' style='cursor:hand;cursor:pointer;'>" + to + "</span>";
                }
            }else{
                if (to != null){
                    url = "<span onclick='page.readMail("+ msgNum +","+true+");' style='cursor:hand;cursor:pointer;font-weight: bold;'>" + to + "</span>";
                }
            }

        }
        return url;
    }  

    page.emailSubject =function(dataGrid) {
        var item= dijit.byId("emailGrid").getItem(dataGrid-1);
        var url="";
        if (item!=null) {
            var msgNum = dataGrid-1;
            var subject=item.subject;
            var isRead = item.flag;
            if(isRead == "read"){
                if (subject != null){
                    url = "<span onclick='page.readMail("+ msgNum +","+false+");' style='cursor:hand;cursor:pointer;' >" + subject + "</span>";
                }
            } else {
                if (subject != null){
                    url = "<span onclick='page.readMail("+ msgNum +","+true+");' style='cursor:hand;cursor:pointer;font-weight: bold;'>" + subject + "</span>";
                }
            }
        }
        return url;
    }  
    
    page.haveAttachment = function(dataGrid){
        var item = dijit.byId("emailGrid").getItem(dataGrid-1);
        var url ="";
        if(item!=null){
            var attach = item.attachmentId;
            if(attach!=""){
                url ='<img src="share/images/icons/attach.png" height="20" width="20"/>'
            }
            
        }
        return url;
    }
    page.compose = function(){
        document.getElementById("outboxpage").style.display="none";
        document.getElementById("sendMail").style.display="";
        page.createEditor();
        
    }
    function decodeHtml(str) {

        return  str
        .replace("&lt;",'<')
        .replace("&gt;", '>')
        .replace("'&lt;", "'<")
        .replace("&gt;; ", ">; ")
        .replace("'&lt;", "'<")
        .replace("&gt;; ", ">; ");  
    }
    String.prototype.replaceAll = function(strTarget,strSubString){
        var strText = this;
        var intIndexOfMatch = strText.indexOf( strTarget );        
        while (intIndexOfMatch != -1){
            strText = strText.replace( strTarget, strSubString )
            intIndexOfMatch = strText.indexOf( strTarget );
        }
        return( strText );
    }
    function decodeHtml1(str) {

        return  str
        .replaceAll("&lt;",'<')
        .replaceAll("&gt;", '>')
        .replaceAll("'&lt;", "'<")
        .replaceAll("&gt;; ", ">;")
        .replaceAll("'&lt;", "'<")
        .replaceAll("&gt;; ", ">;");  
    }
    
    page.convertDateToString = function(dateStr){
        try{

            if(dateStr!=null){
                if(dateStr.toString().length>0)
                {
                    var dgDate  =dateStr.toString().substring(0,10);
                    var dgHour = dateStr.toString().substring(11, 20);
                    var arrDate=dgDate.split("-");
                    return dgHour+ " " + arrDate[2]+"/"+arrDate[1]+"/"+arrDate[0];
                }
                else return "";
            }
            else return "";
        }catch(e){
            return undefined;
        }
    }
    page.emailSentDate =function(dataGrid) {
        var item= dijit.byId("emailGrid").getItem(dataGrid-1);
        var url="";
        if (item!=null) {
            var msgNum = dataGrid-1;
            var sentDate=item.sentDate;
            var isRead = item.flag;
            if(isRead == "read"){
                if (sentDate != null){
                    url = "<span onclick='page.readMail("+ msgNum +","+false+");' style='cursor:hand;cursor:pointer;' >" + page.convertDateToString(sentDate) + "</span>";
                }
            } else {
                if (sentDate != null){
                    url = "<span onclick='page.readMail("+ msgNum +","+true+");' style='cursor:hand;cursor:pointer;font-weight: bold;'>" + page.convertDateToString(sentDate) + "</span>";
                }
            }
        }
        return url;
    }  
    page.forwardMail = function(){      
        sd._("sendForm.subject").setValue(decodeHtml1(document.getElementById("emailHeader.subject").innerHTML));
        var cont = document.getElementById("emailContent").innerHTML;
        sd._("sendForm.content").setValue(document.getElementById("emailContent").innerHTML);
        page.createEditor();
        var attachment = document.getElementsByClassName("attachment");
        for (var i = 0 ; i<attachment.length; i++){
            var id = attachment[i].getAttribute("href").replace("uploadiframe!openFile.do?attachId=","");
            var name = attachment[i].innerText;
            uploadNewRowReadonly(name, id, "sendForm.attachFile");
        } 
        document.getElementById("outboxpage").style.display="none";
        document.getElementById("sendMail").style.display="";
        
    }
    page.replyMail = function(){     
        sd._("sendForm.receiver").setValue(decodeHtml(dojo.attr(dojo.byId("emailHeader.textfrom"),"value")));
        sd._("sendForm.subject").setValue("RE: " + decodeHtml1(document.getElementById("emailHeader.subject").innerHTML));
        sd._("sendForm.content").setValue(document.getElementById("emailContent").innerHTML);
        document.getElementById("outboxpage").style.display="none";
        document.getElementById("sendMail").style.display="";
        page.createEditor();
    }
    page.replyToAll = function(){  
        var st = decodeHtml(dojo.attr(dojo.byId("emailHeader.textfrom"),"value") +"; "+ dojo.attr(dojo.byId("emailHeader.textcc"),"value"));
        sd._("sendForm.receiver").setValue(st);
        sd._("sendForm.subject").setValue("RE:" + decodeHtml1(document.getElementById("emailHeader.subject").innerHTML));
        sd._("sendForm.content").setValue(document.getElementById("emailContent").innerHTML);
        document.getElementById("outboxpage").style.display="none";
        document.getElementById("sendMail").style.display="";
        page.createEditor();
    }
    page.createEditor = function(){
        var editor = CKEDITOR.instances["sendForm.content"];
        if (editor) { 
            editor.destroy(true); 
        }
        CKEDITOR.replace( "sendForm.content",  {
            toolbar :
                [
                { name: 'document', items : [ 'Preview','Print' ] },
                { name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
                { name: 'editing', items : [ 'Find','Replace','-','SelectAll','-','Scayt' ] },
                { name: 'insert', items : [ 'Table','HorizontalRule','Smiley','SpecialChar','PageBreak'] },
                '/',
                { name: 'styles', items : [ 'Styles','Format','Font','FontSize'  ] },
                { name: 'basicstyles', items : [ 'Bold','Italic','Strike','-','RemoveFormat' ] },
                { name: 'colors', items : [ 'TextColor','BGColor' ] },
                { name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock' ] },
                { name: 'links', items : [ 'Link','Unlink','Anchor' ] },
                { name: 'tools', items : [ 'Maximize','-','About' ] }
            ]
        });
    }
</script>

<div style="display: none">
    <sd:TitlePane key="emailformsearch.search" id="emailformsearchTitle">
        <!--      <sx:ResultMessage id="emailformsearchMessage"/>-->
        <form id="emailformsearch" name ="emailformsearch">
            <table width="100%;" cellpadding="0px" cellspacing="5px">
                <tr>
                    <td width="20%"></td>
                    <td width="30%"></td>
                    <td width="20%"></td>
                    <td width="30%"></td>
                </tr>
                <tr>
                    <td align="right"> 
                        <sd:Label key="emailformsearch.fromDate"/>
                    </td>
                    <td align="left" colspan="1">
                        <sx:DatePicker id="emailformsearch.fromDate" 
                                       key="" 
                                       name="emailformsearch.fromDate"
                                       format="dd/MM/yyyy">
                        </sx:DatePicker>                 

                    </td>
                    <td align="right">
                        <sd:Label key="emailformsearch.toDate"/>
                    </td>
                    <td align="left" colspan="1">
                        <sx:DatePicker id="emailformsearch.toDate" 
                                       key="" 
                                       name="emailformsearch.toDate"
                                       format="dd/MM/yyyy">
                        </sx:DatePicker>                 
                    </td>
                </tr>

                <tr id="hiddenSearch1" style="display: none">
                    <td align="right">
                        <sd:Label key="emailformsearch.subject"/>
                    </td>
                    <td colspan="2">
                        <sd:Textarea trim="true" cssStyle="width:100%" id="emailformsearch.subject" name="emailformsearch.subject" key="" maxlength="200" rows="2"/>
                    </td>
                </tr>
                <tr id="hiddenSearch2" style="display: none">
                    <td align="right">
                        <sd:Label key="emailformsearch.content"/>
                    </td>
                    <td colspan="2">
                        <sd:Textarea trim="true" cssStyle="width:100%" id="emailformsearch.content" name="emailformsearch.content" key="" maxlength="200" rows="2"/>
                    </td>
                </tr>
                <tr id="hiddenSearch3" style="display: none">
                    <td align="right">
                        <sd:Label key="emailformsearch.folder"/>
                    </td>
                    <td colspan="2">
                        <sd:Textarea trim="true" cssStyle="width:100%" id="emailformsearch.folder" name="emailformsearch.folder" key="" maxlength="200" rows="2"/>
                    </td>
                </tr>
                <tr id="hiddenSearch4" style="display: none">
                    <td align="right">
                        <sd:Label key="emailformsearch.emailAdress"/>
                    </td>
                    <td colspan="2">
                        <sd:Textarea trim="true" cssStyle="width:100%" id="emailformsearch.emailAdress" name="emailformsearch.emailAdress" key="" maxlength="200" rows="2"/>
                    </td>
                </tr>
            </table>
        </form>
    </sd:TitlePane>
</div>

<div id="sendMail" style="width: 100%;display: none">
    <jsp:include page="/WEB-INF/jsp/pages/email/sendMail.jsp"/>
</div>

<div id ="outboxpage" style="width: 100%;display: " >
    <div id="button" style="width: 100%;display: " class="buttonDiv">
        <table border="0" cellpadding="0" cellspacing="0">
            <tbody>
                <tr>
                    <td>
                        <sd:Button  id="btnCompose" key="" onclick="page.compose();">
                            <img src="share/images/icons/compose_email.png" height="14" width="14" alt="Soạn thư">
                            <span style="font-size:12px">Soạn thư</span>
                        </sd:Button>
                    </td>
                    <td >
                        <sd:Button  id="btnDelete" key="" onclick=" page.deleteEmail();">
                            <img src="share/images/icons/delete_email.png" height="14" width="14" alt="Xóa thư">
                            <span style="font-size:12px">Xóa thư</span>
                        </sd:Button>   
                    </td>
                    <td nowrap="">
                        <sd:Button cssStyle="display:none" id="btnReply" key="" onclick="page.replyMail();">
                            <img src="share/images/icons/reply_email.png" height="14" width="14" alt="Trả lời">
                            <span style="font-size:12px">Trả lời</span>
                        </sd:Button>       
                    </td>
                    <td nowrap="">
                        <sd:Button cssStyle="display:none" id="btnReplyAll" key="" onclick="page.replyToAll();">
                            <img src="share/images/icons/replyall_email.png" height="14" width="14" alt="Trả lời tất cả">
                            <span style="font-size:12px">Trả lời tất cả</span>
                        </sd:Button>       
                    </td>
                    <td nowrap="">
                        <sd:Button cssStyle="display:none" id="btnFoward" key="" onclick="page.forwardMail();">
                            <img src="share/images/icons/foward_email.png" height="14" width="14" alt="Chuyển tiếp">
                            <span style="font-size:12px">Chuyển tiếp</span>
                        </sd:Button>       
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <sd:TitlePane id="outpage" key="file.outpage" >
        <sd:DataGrid id="emailGrid"
                     getDataUrl="" 
                     rowSelector="0"
                     style="width:auto;height:auto"
                     rowsPerPage="15" 
                     serverPaging="true"  
                     clientSort="true"       
                     >
            <sd:ColumnDataGrid editable="false" key="column.checkbox" headerCheckbox="true" width="5%" 
                               headerStyles="text-align:center;font-weight:bold" type="checkbox" cellStyles="text-align:center;" setDisabled="page.onDisable"/>
            <sd:ColumnDataGrid editable="false" key="emailGrid.to" get="page.getIndex" formatter="page.emailTo" width="15%" alwaysEditing="true"
                               headerStyles="text-align:center"/>

            <sd:ColumnDataGrid editable="false" key="emailGrid.subject" get="page.getIndex" formatter="page.emailSubject"  width="65%" alwaysEditing="false" wrapping="true"
                               headerStyles="text-align:center"/>

            <sd:ColumnDataGrid editable="false" key=" " formatter="page.haveAttachment" width="3%" get="page.getIndex"
                               headerStyles="background: url(share/images/icons/attach.png) no-repeat center;background-size: 20px;text-align:center;font-weight:italic"  cellStyles="text-align:center" />

            <sd:ColumnDataGrid editable="false" key="emailGrid.timeoutbox"   get="page.getIndex" formatter="page.emailSentDate"  width="12%" 
                               headerStyles="text-align:center" />
        </sd:DataGrid>
        <div style="display: none">
            <sd:DataGrid 
                id="deleteItemsGrid"
                getDataUrl="" 
                rowSelector="0"
                style="width:auto;height:auto"
                rowsPerPage="10" 
                serverPaging="true"  
                clientSort="true"

                >
                <sd:ColumnDataGrid editable="false" headerStyles="text-align:center;font-weight: bold;" key="deleteGrid.emailId" field="emailId" width="25%"/>
            </sd:DataGrid>
        </div>
    </sd:TitlePane>
    <div id="mailContent" style="display: none; max-height: 800px; max-width: 1060px;">
        <form  id="emailForm" name="emailForm" style="display: none"> 
            <sd:TextBox cssStyle="width:100%"
                        id="emailForm.emailId"
                        key=""
                        name="emailForm.emailId"
                        >
            </sd:TextBox>
            <sd:TextBox cssStyle="width:100%"
                        id="emailForm.attachIDString"
                        key=""
                        name="emailForm.attachIDString"
                        >
            </sd:TextBox>
            <sd:TextBox cssStyle="width:100%"
                        id="emailForm.folder"
                        key=""
                        name="emailForm.folder"
                        >
            </sd:TextBox>
        </form>

        <sd:TitlePane id="contentMailGrid" key="file.contentOutbox" >
            <div >
                <form id="emailHeader">
                    <table style="width: 100%; border-bottom-color: black; border-bottom-style: double;">
                        <tr>
                            <td style="text-align: left; width: 20%">
                                Chủ đề  :
                            </td>
                            <td style="text-align: left; width: 80%"> 
                                <label style="blue-label" id="emailHeader.subject"/>
                            </td>
                            <td style="text-align: left;width: 80%">
                                <input type="hidden" name="emailHeader.textsubject" id="emailHeader.textsubject" />
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: left">
                                Đã gửi : 
                            </td>
                            <td style="text-align: left">
                                <label style="blue-label" id="emailHeader.receivedDate"/>
                            </td>

                        </tr>
                        <tr>
                            <td style="text-align: left">
                                Đến :  
                            </td>
                            <td style="text-align: left">
                                <label style="blue-label" id="emailHeader.from"/>
                            </td>
                            <td>
                                <input type="hidden" name="emailHeader.textfrom" id="emailHeader.textfrom" />
                            </td>
                        </tr>
                        <tr>
                            <td style="overflow-x: hidden; max-width: 100%; text-align: left">
                                CC:  
                            </td>
                            <td style="overflow-x: hidden; max-width: 100%; text-align: left">
                                <label style="blue-label" id="emailHeader.cc"/>
                            </td>
                            <td>
                                <input type="hidden" name="emailHeader.textcc" id="emailHeader.textcc" />
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: left">
                                Đính kèm :
                            </td>
                            <td style="text-align: left">
                                <label style="blue-label" id="emailHeader.attach"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div style="max-height: 400;max-width: 1060;overflow-x: auto;overflow-y: auto">
                <p id="emailContent">

                </p>
            </div>
        </sd:TitlePane >

    </div>


    <%
        request.setAttribute("contextPath", request.getContextPath());
    %>

    <sx:ResultMessage id="resultAction"/>
    <script type="text/javascript">
        var readYN = 0;
        page.reloadGrid = function(){
            onSearch();
        }
        page.readMail = function(index, read) {
            var item = dijit.byId("emailGrid").getItem(index);
            item.flag = "read";
            dijit.byId("emailGrid").store.save(item);
            dijit.byId("emailGrid").renderNoReload();
            sd._("emailForm.emailId").setValue(item.emailId);
            sd._("emailForm.folder").setValue(item.folder);
            dijit.byId("outpage").attr("open", false);
            document.getElementById("emailContent").innerHTML = (item.content);
            document.getElementById("emailHeader.subject").innerHTML = item.subject;
            document.getElementById("emailHeader.receivedDate").innerHTML = page.convertDateToString(item.sentDate);
            document.getElementById("emailHeader.from").innerHTML = (item.sender);
            document.getElementById("emailHeader.textcc").value = item.recipients;
            document.getElementById("emailHeader.textsubject").value = item.subject; 
            document.getElementById("emailHeader.textfrom").value = item.sender;
            if(!!item.recipients && item.recipients.length != 0 && !!item.recipients[0]){
                document.getElementById("emailHeader.cc").innerHTML =  (item.recipients);
            }
            document.getElementById("mailContent").style.display="";
            dijit.byId("btnReply").domNode.style.display = "";
            dijit.byId("btnReplyAll").domNode.style.display = "";
            dijit.byId("btnFoward").domNode.style.display = "";
         
            var link ="";
            var st = "";
            for (var i = 0 ; i< item.attachmentIDArray.length;i++){
                if (item.attachmentIDArray[i]!=null){  
                
                    link  += "<a class = 'attachment' href='uploadiframe!openFile.do?attachId="+item.attachmentIDArray[i]+"'>"+item.attachmentNameArray[i]+"</a>; ";
                    st += item.attachmentIDArray[i]+"; ";
                }
            }
            sd._("emailForm.attachIDString").setValue(st);
            document.getElementById("emailHeader.attach").innerHTML = (link);
            if(read)
            {
                readYN = 1;
                sd.connector.post("voEmail!readMail.do?"+token.getTokenParamString(), null, "emailForm", null, null);
            }
        }
    
        page.deleteEmail = function(){
            var grid = dijit.byId("emailGrid");
            var items = grid.vtGetCheckedItems();
            var from = document.getElementById("emailHeader.from").innerHTML.trim();
            switch(page.validateDelete(items,from)){
                case "checked":
                    page.deleteReadEmail();
                    break;
                case "read":
                    page.deleteSelectedEmail();
                    break;
                default:;
            }
        }
        page.deleteReadEmail = function(){
        
            var grid = dijit.byId("emailGrid");
            var content = grid.vtGetCheckedDataForPost("listMessForm");
            sd.connector.post("voEmail!onDelete.do?"+token.getTokenParamString(), null, null, content, page.afterDeleteEmail);
        }
        page.deleteSelectedEmail = function(){
            sd.connector.post("voEmail!deleteSelected.do?"+token.getTokenParamString(), null, "emailForm", null, page.afterDeleteEmail);
            document.getElementById("emailHeader.from").innerHTML ="";
            dijit.byId("btnReply").domNode.style.display = "none";
            dijit.byId("btnReplyAll").domNode.style.display = "none";
            dijit.byId("btnFoward").domNode.style.display = "none";
        }
        page.afterDeleteEmail = function(){
            page.reloadGrid();
            document.getElementById("mailContent").style.display="none";
            dijit.byId("outpage").attr("open", true);
        }
        var grid = dijit.byId("emailGrid");
        onSearch = function(){
            var fromDate = document.getElementById("searchForm.fromDate").value;
            var toDate = document.getElementById("searchForm.toDate").value;
            var text = document.getElementById("searchForm.text").value;
            if(fromDate!=null && fromDate.toString() != "") {
                var yyyy = fromDate.substring(6,10);            
                var mm = fromDate.substring(3,5);
                var dd = fromDate.substring(0,2);             
                dijit.byId("emailformsearch.fromDate").attr("value",new Date(yyyy,mm - 1 ,dd));
            } else {
                dijit.byId("emailformsearch.fromDate").attr("value","");
            }
            if(toDate!=null && toDate.toString() != "") {
                var yyyy = toDate.substring(6,10);            
                var mm = toDate.substring(3,5);
                var dd = toDate.substring(0,2);             
                dijit.byId("emailformsearch.toDate").attr("value",new Date(yyyy,mm - 1 ,dd));
            } else {
                dijit.byId("emailformsearch.toDate").attr("value","");
            }
            var text = dojo.attr(dojo.byId("searchForm.text"),"value");
            dijit.byId("emailformsearch.subject").attr("value",text);
            dijit.byId("emailformsearch.folder").attr("value","outbox");
            dijit.byId("emailformsearch.content").attr("value",text);
            dijit.byId("emailformsearch.emailAdress").attr("value",text);
            document.getElementById("mailContent").style.display="none";
            page.search();

        }
        page.search = function() {
            if(readYN == 0){
                if( page.validateFormSearch()){
                    grid.vtReload('voEmail!onSearch.do?folder=outbox&'+token.getTokenParamString(), "emailformsearch",null,null);
                    dijit.byId("outpage").attr("open", true);
                    dijit.byId("btnReply").domNode.style.display = "none";
                    dijit.byId("btnReplyAll").domNode.style.display = "none";
                    dijit.byId("btnFoward").domNode.style.display = "none";
                }
            }else{
                if( page.validateFormSearch()){
                    grid.vtReload('voEmail!onSearch.do?folder=outbox&'+token.getTokenParamString(), "emailformsearch",null,null);
                }
                dijit.byId("outpage").attr("open", false);
                document.getElementById("mailContent").style.display="";
                readYN =0;
            }
        }
        page.validateDelete = function(item,from){
            if(item !=""){
                return "checked";
            }else if(from  != ""){
                return "read";
            }else{
                var btnDelete = dijit.byId("btnDelete");
                btnDelete.focus();
                resultMessage_show("validationMessage", "2", "Chọn thư cần xóa trước khi xóa !!!", 300);
            }
        }
        page.validateFormSearch= function(){        
            var fromDate = dijit.byId("emailformsearch.fromDate");
            var toDate = dijit.byId("emailformsearch.toDate");
            if(fromDate.getValue() != null && toDate.getValue() != null){
                if(fromDate.getValue() > toDate.getValue()){
                    fromDate.focus();
                    //msg.alert("Ngày ban hành Từ phải nhỏ hơn ngày ban hành Đến","<sd:Property>confirm.title</sd:Property>");
                    resultMessage_show("emailformsearchMessage", "2", "Ngày ban hành Từ phải nhỏ hơn ngày ban hành Đến", 2000);
                    return false;
                }
            }
            return true;
        }
        insertDeleteItem = function(emailId){
            var grid = dijit.byId("deleteItemsGrid");
            var item={
                emailId       :emailId,  
                userId:null,
                sender:null,
                receiver:null,
                replyTo:null,
                sentDate:null,
                receiveDate:null,
                flag:null,
                folder:null,
                attachmentId:null,
                emailUid:null,
                subject:null,
                content:null,
                emailAdress:null,
                recipients:null,
                bcc:null,
                attachmentIDArray:null,
                attachmentNameArray:null
            };  
            grid.store.newItem(item);
            grid.store.save();
            grid.renderNoReload();
            return item;
        }       
        onSearch();

    </script>
