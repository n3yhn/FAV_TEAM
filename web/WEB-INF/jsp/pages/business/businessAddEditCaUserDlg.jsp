<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sx:ResultMessage id="resultMessage" />
<form id="createForm" name="createForm">
    <table width="100%" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="25%"></td>
            <td width="25%"></td>
            <td width="25%"></td>
            <td width="25%"></td>
        </tr>
        <tr>
            <td>
                <sd:TextBox id="createForm.caUserId" name="createForm.caUserId" cssStyle="display:none" key=""/>
                <sx:Label key="Serial:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.caSerial"
                            name="createForm.caSerial"
                            key=""
                            maxlength="500"
                            readonly="true"
                            />
            </td>
            <td>
                <sx:Label key="Tên tài khoản:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.userName"
                            name="createForm.userName"
                            key=""
                            maxlength="500"
                            readonly="true"
                            />
            </td>
        </tr>
        <tr>
            <td>
                <sx:Label key="Tên người ký:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.name"
                            name="createForm.name"
                            key=""
                            maxlength="500"
                            />
            </td>
            <td>
                <sx:Label key="Chức danh:"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.position"
                            name="createForm.position"
                            key=""
                            maxlength="500"
                            />
            </td>
        </tr>
        <tr>
            <td>
                <sx:Label key="Ảnh chữ ký đính kèm:" require="true"/>
            </td>
            <td>
                <sx:upload 
                    action="uploadiframe!uploadFile.do?id=createForm.upload" 
                    extension="*.png" id="createForm.upload" maxSize="20"/>
                <sd:TextBox 
                    id="createForm.uploadId" 
                    name="createForm.uploadId" 
                    cssStyle="display:none" key=""/>
            </td>
            <td>

            </td>
            <td>

            </td>
        </tr>
    </table>
    <div style="text-align: center">
        <sx:ButtonSave onclick="page.save();"></sx:ButtonSave>  
        <sx:ButtonClose onclick="page.close();"></sx:ButtonClose>  
    </div>
</form>

<script>
    page.save = function () {//add 29.7
        var attachIds = getListAttachId("createForm.upload");
        if (attachIds == null || attachIds.trim().length == 0) {
            alert("Bạn chưa đính kèm file văn bản");
        } else {
            dijit.byId("createForm.uploadId").setValue(attachIds);
            sd.connector.post("caUserAction!updateCAUser.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSave);
        }
    };

    page.afterSave = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {
            page.close();
            page.search();
        }
    };

    page.close = function () {
        dlg.hide();
    };


</script>