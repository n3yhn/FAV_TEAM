
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
    #contentMail{
        border: 1px solid graytext;

    }
</style>
<%-- ckeditor --%>
<script type="text/javascript" src="share/ckeditor/js/ckeditor.js"></script>
<script type="text/javascript" src="share/ckeditor/js/sample.js"></script>
<!--<link rel="stylesheet" href="share/ckeditor/css/sample.css" type="text/css">-->

<div class="buttonDiv">
    <sd:Button id="" key="" onclick="page.back();">
        <img src="share/images/icons/back.png" height="14" width="14" alt="Trở lại"/>
        <span style="font-size:12px">Trở lại</span>
    </sd:Button>
    <sd:Button id="" key="" onclick="page.sendEmail();">
        <img src="share/images/icons/mail_send.png" height="14" width="14" alt="Soạn thư"/>
        <span style="font-size:12px">Gửi thư</span>
    </sd:Button>
</div>

<form id="sendForm" name="sendForm">
    <sd:FieldSet key="usersForm.info">
        <table width="100%">
            <tr>
                <th style="width:10%;text-align: left; font-weight: bold"></th>
                <th width="80%"></th>
            </tr>
            <tr>
                <td style="width:10%;text-align: left; font-weight: bold">
                    <sd:Label key="sendMailForm.to"/>
                </td>
                <td  width="80%" >
                    <sd:TextBox id="sendForm.receiver" name="sendForm.receiver" key="" cssStyle="width:100%"   />
                </td>

            </tr>
            <tr>
                <td style="width:10%;text-align: left; font-weight: bold">
                    <sd:Label key="sendMailForm.cc"/>
                </td>
                <td  width="80%">
                    <sd:TextBox id="sendForm.recipients" name="sendForm.recipients" key=""  cssStyle="width:100%" />
                </td>
            </tr>
            <tr>
                <td style="width: 10%;text-align: left;font-weight: bold">
                    <sd:Label key="sendMailForm.bb"/>
                </td>
                <td width="80%">
                    <sd:TextBox id="sendForm.bcc" name="sendForm.bcc" key="" cssStyle="width:100%" />
                </td>
            </tr>
            <tr>
                <td style="width:10%;text-align: left; font-weight: bold">
                    <sd:Label key="sendMailForm.subject"/>
                </td>
                <td  width="80%">
                    <sd:TextBox id="sendForm.subject" name="sendForm.subject" key="" cssStyle="width:100%" />
                </td>
            </tr>
            <tr>
            <tr>
                <td style="width: 20%;text-align: left;font-weight: bold">
                    <sd:Label key="Đính kèm :"/>
                </td>                <td>
                    <table style="max-width: 100%">
                        <Tr>
                            <td>
                                <sd:TextBox trim="true" cssStyle="width:100%;display:none;" id="documentReceiveAddEditForm.attachId" name="documentReceiveAddEditForm.attachId" key="" />
                                <sx:upload extension="*.pdf,*.doc,*.docx,*.zip,*.rar, *.docx, *.xls, *.xlsx"
                                           auto="true" id="sendForm.attachFile"
                                           action="uploadiframe!uploadFile.do?id=sendForm.attachFile" maxSize="5">
                                </sx:upload>
                                <sd:TextBox trim="true" cssStyle="width:100%;display:none;" id="sendForm.attachmentId" name="sendForm.attachmentId" key=""/>
                            </td>
                        </Tr>
                    </table>
                </td>
                <td></td>
            </tr>
            </tr>
            <tr>
                <td colspan="2">
                    <sd:Textarea key=""
                                 id="sendForm.content" name="sendForm.content" 
                                 maxlength="4000" cssStyle="width:100%"  
                                 rows="20"></sd:Textarea>
                </td>
            </tr>
        </table>
    </sd:FieldSet>
</form>
<script>
    page.back = function(){
        if(currentpage == "inbox" )
        {
            document.getElementById("inboxpage").style.display="";    
            document.getElementById("sendMail").style.display="none";
            dijit.byId("inbox").attr("open", true);
        }else {
            document.getElementById("outboxpage").style.display="";  
            document.getElementById("sendMail").style.display="none";
            dijit.byId("outpage").attr("open", true);
        }
        document.getElementById("sendForm").reset();
        clearAttFile("sendForm.attachFile");
    }
    
    page.sendEmail = function(){
        if(page.validateForm()){
            if(currentpage == "inbox" )
            {
                document.getElementById("inboxpage").style.display="";    
                document.getElementById("sendMail").style.display="none";
                dijit.byId("inbox").attr("open", true);
            }else {
                document.getElementById("outboxpage").style.display="";  
                document.getElementById("sendMail").style.display="none";
                dijit.byId("outpage").attr("open", true); 
            }
            //            document.getElementById("inboxpage").style.display="";
            //            document.getElementById("sendMail").style.display="none";
            var attIDs = getListAttachId("sendForm.attachFile");
            dijit.byId("sendForm.attachmentId").setValue(attIDs);
            var st = CKEDITOR.instances["sendForm.content"];
            sd._("sendForm.content").setValue(st.getData());
            sd.connector.post("voEmail!sendEmail.do"+token.getTokenParamString(),null,"sendForm", null, page.reloadGrid); 
            document.getElementById("sendForm").reset();
            clearAttFile("sendForm.attachFile");
        }
    }
    page.validateForm = function(){
        var sendMail = dijit.byId("sendForm.receiver");
        var receiver = dijit.byId("sendForm.receiver").getValue().split(';');
        var recipients = dijit.byId("sendForm.recipients").getValue().split(';');
        var bRecipients = dijit.byId("sendForm.bcc").getValue().split(';');
        var pattern = "/^(((([\w\-\.]+)@((([\w\-]+\.)+)([a-zA-Z]{2,4}))))|((([\w\-\.]+)@((([\w\-]+\.)+)([a-zA-Z]{2,4})));\s)|((([\w\-\.\s\'\á\à\ạ\ả\ã\â\ấ\ầ\ẫ\ẩ\ậ\ă\ắ\ằ\ẵ\ẳ\ặ\ê\ế\ề\ệ\ễ\ể\o\ó\ò\ọ\ỏ\õ\ô\ố\ồ\ộ\ổ\ỗ\ơ\ớ\ờ\ợ\ở\ỡ\í\ì\ị\ỉ\ĩ\đ\ú\ù\ủ\ụ\ũ\ư\ứ\ừ\ự\ử\ữ]+)[<]([\w\-\.]+)@((([\w\-]+\.)+)([a-zA-Z]{2,4})))>)|((([\w\-\.\s\'\á\à\ạ\ả\ã\â\ấ\ầ\ẫ\ẩ\ậ\ă\ắ\ằ\ẵ\ẳ\ặ\ê\ế\ề\ệ\ễ\ể\o\ó\ò\ọ\ỏ\õ\ô\ố\ồ\ộ\ổ\ỗ\ơ\ớ\ờ\ợ\ở\ỡ\í\ì\ị\ỉ\ĩ\đ\ú\ù\ủ\ụ\ũ\ư\ứ\ừ\ự\ử\ữ]+)[<]([\w\-\.]+)@((([\w\-]+\.)+)([a-zA-Z]{2,4})))>;\s))*$/";  
        if (sendMail.getValue() == "" ) {
            sendMail.focus();
            resultMessage_show("validationMessage", "2", "Không được để trống người nhận", 300);
            return false;
        }
        for(var i=0;i<receiver.length;i++){
            if(!(pattern.test(receiver[i].trim()))) {
                sendMail.focus();
                resultMessage_show("validationMessage", "2", "Email không đúng định dạng !", 500);
                return false;
            }
        }
        for(var i=0;i<recipients.length;i++){
            if(!(pattern.test(recipients[i].trim()))) {
                sendMail.focus();
                resultMessage_show("validationMessage", "2", "Email không đúng định dạng !", 500);
                return false;
            }
        }
        for(var i=0;i<bRecipients.length;i++){
            if(!(pattern.test(bRecipients[i].trim()))) {
                sendMail.focus();
                resultMessage_show("validationMessage", "2", "Email không đúng định dạng !", 500);
                return false;
            }
        }   
        return true;
    }
</script>

