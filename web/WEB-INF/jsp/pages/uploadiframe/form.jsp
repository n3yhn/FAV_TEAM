<%-- 
    Document   : form
    Created on : Mar 8, 2011, 8:34:41 AM
    Author     : cn_longh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


<sd:DatePicker id="dtep" key="Keke" name="dtep" />
<form name="uploadIframetest" id="uploadIframetest" action="uploadiframe!executeUploadIframe.do" enctype="multipart/form-data" method="post">

    <input name="uploadIframe.myText" id="uploadIframe.myText" type="text" />

    <input name="uploadIframe.myFile" id="uploadIframe.myFile" type="file" />
    <!--    <input name="myFile2" id="myFile2" type="file" />-->
    <input type="button" value="upload" onclick="page.doSubmit();" />
    <input type="button" value="ajax" onclick="page.doSubmitAjax();" />
</form>
<iframe name="myCallbackTargetIframe" style="display:block;" width="500" height="200"></iframe>

<script>
    page.doSubmitAjax = function() {
        doGetJSON(
        /*{String url, String areaId, String formId, Object param, Function onSuccess, Function onFail, Boolean sync}*/
            {
                /*url : "uploadiframe!executeUploadIframe.do",*/
                url : "uploadiframe!executeUploadIframe.do",
                formId : "uploadIframetest"
            }
        );
    };

    page.doSubmit = function() {
        var frm = dojo.byId("uploadIframetest");
        frm.target = "myCallbackTargetIframe";
        frm.submit();
    };

    page.callback = function(msg) {
        alert("Uploadiframe callback: " + msg);
    };
</script>
