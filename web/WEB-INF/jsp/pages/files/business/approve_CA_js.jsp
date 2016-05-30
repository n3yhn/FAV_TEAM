<%@page import="com.viettel.common.util.StringUtils"%>
<%@page import="java.util.ResourceBundle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    page.preApproveCA = function() {
        var fileId = workingFileId;
        var xmlContent = check;
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
                var publishDocumentId = workingFileId;
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
                            if (documentId == publishDocumentId)
                            {
                                content = content.replace("[" + documentId + "]", "");
                                if (content != null) {
                                    document.getElementById("signCAForm.contentSigned").value = content;
                                    document.getElementById("signCAForm.fileId").value = workingFileId;
                                    sd.connector.post("filesAction!validateCA.do?" + token.getTokenParamString(), null, "signCAForm", null, function(data) {
                                        var obj = dojo.fromJson(data);
                                        var result = obj.items;
                                        if (result[0] == "1") {
                                            sd.connector.post("filesAction!onSelectFlow.do?" + token.getTokenParamString() + "&createForm.deptId=" + item.deptId + "&createForm.fileId=" + workingFileId, null, null, null, page.afterSend);
                                            msg.alert(result[1], "Thông báo");
                                            dijit.byId("selectDeptDlg").hide();
                                            page.search();
                                        } else {
                                            msg.alert(result[1], "Thông báo");
                                            page.search();
                                        }
                                    });
                                } else {
                                    alert("Hồ sơ đã được ký 1 lần");
                                    page.search();
                                }

                                break;
                            }
                        }
                    }
                }
                dijit.byId("dlgReturn1").hide();
                dijit.byId("selectDeptDlg").hide();
                page.search();
            }
        }
        catch (ex)
        {
            alert(ex);
        }
    };
</script>
