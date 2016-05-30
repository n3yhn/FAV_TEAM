<%@page import="com.viettel.common.util.StringUtils"%>
<%@page import="java.util.ResourceBundle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    page.preApproveCA = function() {
        //alert(check);
        var fileId = "374909";
        var xmlContent = "<ROOT><DATA><PUBLIC_ELEMENT><ID>374909</ID><INFO>REGISTER CREATE CA</INFO></PUBLIC_ELEMENT></DATA></ROOT>";
        var contentToSign = "[" + fileId + "]" + xmlContent + "&!$%";
        contentToSign = contentToSign.replace(/"/g, "@@@");
        document.getElementById('myapplet').innerHTML = buildApplet(contentToSign);
    };

    showDialogS = function() {
        var applet = document.signApplet;
        if (applet.isActive) {
            try {
                applet.showSignal();
            } catch (err) {
                alert(err.toString());
            }
        }
        return false;
    };

    buildApplet = function(content) {
    <%
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String calink = rb.getString("CA_LINK");
    %>
        var applet = "<applet WIDTH=\"300px\" HEIGHT=\"300px\" code=\"com.viettel.QLLLTP.ca.applet.DataSignApplet\" name=\"signApplet\" id=\"signApplet\" >\n"
                + "        <param value=\"AppletSignCA_160527.jar,\n"
                + "               \"\n"
                + "               name=\"archive\">\n"
                + "        <param value=\"yes\" name=\"mayscript\">\n"
                + "        <param value=\"true\" name=\"scriptable\">\n"
                + "        <param value=\"jsapplet\" name=\"name\">\n"
                + "        <param name=\"content\" value=\"" + content + "\">\n"
                + "        <PARAM name=\"type\" value=\"INIT\">\n"
                + "        <PARAM name=\"userId\" value=\"123\">\n"
                + "        <PARAM name=\"sign.server\" value=\"ca/SigningServlet\">\n"
                + "    </applet\n"
                + "</div>    \n";
        return applet;
    };

    page.submitSignForm = function(data)
    {
        try {
            if (data != null)
            {
                var publishDocumentId = dijit.byId("createForm.fileId").getValue();
                var contentArr = data.split("&!$%");
                var content;
                var documentId;
                var firstIdx;
                var lastIdx;
                if (contentArr != null && publishDocumentId != null)
                {
                    for (var i = 0; i < contentArr.length; i++)
                    {
                        content = contentArr[i];
                        firstIdx = content.indexOf("[");
                        lastIdx = content.indexOf("]");

                        if (firstIdx >= 0 && lastIdx >= 0)
                        {
                            documentId = content.substring(firstIdx + 1, lastIdx);
                            if (check == true) {
                                if (documentId == publishDocumentId)
                                {
                                    content = content.replace("[" + documentId + "]", "");
                                    if (content != null) {
                                        document.getElementById("createForm.contentSigned").value = content;
                                        sd.connector.post("caUserAction!registerCA.do?" + token.getTokenParamString(), null, "createForm", null, function(data) {
                                            var obj = dojo.fromJson(data);
                                            var result = obj.items;
                                            if (result[0] == "1") {
                                                msg.alert(result[1], "Thông báo");
                                            } else {
                                                msg.alert(result[1], "Thông báo");
                                            }
                                        });
                                    } else {
                                        alert("Hồ sơ đã được ký 1 lần");
                                    }
                                    break;
                                }
                            }
                            if (check == false)
                            {
                                if (documentId == publishDocumentId)
                                {
                                    content = content.replace("[" + documentId + "]", "");
                                    if (content != null) {
                                        document.getElementById("createForm.contentSigned").value = content;
                                        sd.connector.post("caUserAction!deleteCA.do?" + token.getTokenParamString() + "&createForm.caUserId=" + workingCaUserId, null, "createForm", null, function(data) {
                                            var obj = dojo.fromJson(data);
                                            var result = obj.items;
                                            if (result[0] == "1") {
                                                msg.alert(result[1], "Thông báo");
                                                page.search();
                                                dijit.byId("dlgReturn").hide();
                                            } else {
                                                msg.alert(result[1], "Thông báo");
                                                page.search();
                                                dijit.byId("dlgReturn").hide();
                                            }
                                        });
                                    } else {
                                        alert("Hồ sơ đã được ký 1 lần");
                                    }
                                    break;
                                }
                            }
                        }

                    }
                }
                dijit.byId("dlgReturn").hide();
                page.search();
            }
        }
        catch (ex)
        {
            alert(ex);
        }
    };
</script>
