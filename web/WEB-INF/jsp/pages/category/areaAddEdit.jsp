<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<jsp:include page="../util/util_js.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sx:ResultMessage id="positionAddEditMessage"/>
<form id="areaAddEditForm" name="areaAddEditForm">

    <sd:TextBox cssStyle="width:100%;display:none"
                id="areaAddEditForm.areaId"
                key=""
                name="areaAddEditForm.areaId"
                >
    </sd:TextBox> 

    <table width="100%" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="20%"></td>
            <td width="30%"></td>
            <td width="20%"></td>
            <td width="30%"></td>
        </tr>

        <tr>
            <td align="right">
                <sd:Label key="Mã lĩnh vực" cssStyle="100%"/><font color="red">*</font>
            </td>
            <td>
                <sd:TextBox id="areaAddEditForm.abbreviate" name="areaAddEditForm.abbreviate"
                            key="" maxlength="30" cssStyle="width:100%">
                </sd:TextBox>
            </td>
            <td align="right">
                <sd:Label key="Tên lĩnh vực" cssStyle="100%"/><font color="red">*</font>
            </td>
            <td>
                <sd:TextBox id="areaAddEditForm.name" name="areaAddEditForm.name"
                            key="" maxlength="150" cssStyle="width:100%">
                </sd:TextBox>
            </td> 
        </tr>
        <tr>
            <td align="right">
                <sd:Label key="Thứ tự sắp xếp" cssStyle="100%"/>
            </td>
            <td>
                <sd:TextBox id="areaAddEditForm.orderNumber" name="areaAddEditForm.orderNumber"
                            key="" maxlength="5" cssStyle="width:100%" mask="0123456789">
                </sd:TextBox>
            </td> 
            <td align="right">
                <sd:Label key="Lĩnh vực cha" cssStyle="100%"/>
            </td>
            <td>
                <sd:SelectBox  cssStyle="width:100%!important;" 
                               id="areaAddEditForm.parentId" 
                               name="areaAddEditForm.parentId" 
                               key=""
                               data="lstArea"
                               labelField="name" valueField="areaId">
                </sd:SelectBox > 
            </td> 
        </tr>

        <tr>
            <td align="right">
                <sd:Label key="positionAddEditForm.description" cssStyle="100%"/>
            </td>
            <td colspan="3">
                <sd:TextBox id="areaAddEditForm.description" name="areaAddEditForm.description"
                             key="" maxlength="1500" cssStyle="width:100%">
                </sd:TextBox>                    
            </td> 
            <%--
            <td align="right">
                <sd:Label key="Hiển thị" cssStyle="100%"/>
            </td>
            <td>
                <sd:CheckBox id="areaAddEditForm.isDisplay" 
                             name="areaAddEditForm.isDisplay"
                             key="" cssStyle="" value="1"/>
            </td>
            --%>
        </tr>            

        <tr style="text-align: center">
            <td colspan="4">
            <sx:ButtonSave onclick="page.save();"></sx:ButtonSave>  
            <sx:ButtonClose onclick="page.close();"></sx:ButtonClose>  
            </td>
        </tr>
    </table>
    </form>

    <script>

        page.save = function() {
            var abbreviate = dijit.byId("areaAddEditForm.abbreviate");
            var name = dijit.byId("areaAddEditForm.name");

            if (abbreviate.getValue() == "") {
                abbreviate.focus();
                msg.alert("Hãy nhập mã lĩnh vực", '<sd:Property>confirm.title</sd:Property>');
                return false;
            }

            if (name.getValue() == "") {
                name.focus();
                msg.alert("Hãy nhập tên lĩnh vực", '<sd:Property>confirm.title</sd:Property>');
                return false;
            }
            sd.connector.post("voArea!onInsert.do?" + token.getTokenParamString(), null, "areaAddEditForm", null, page.afterInsert);
        }

        page.afterInsert = function(data) {
            var obj = dojo.fromJson(data);
            var result = obj.items;
            grid.vtReload('voArea!onSearch.do?', "areaSearchForm", null, null);

            if (insertDialog) {               
                resultMessage_show("positionAddEditMessage", result[0], result[1], 5000);
                dijit.byId("areaAddEditForm.areaId").setValue("");
                dijit.byId("areaAddEditForm.abbreviate").setValue("");
                dijit.byId("areaAddEditForm.name").setValue("");
                dijit.byId("areaAddEditForm.description").setValue("");
                dijit.byId("areaAddEditForm.orderNumber").setValue("");
                dijit.byId("areaAddEditForm.parentId").setSelectedIndex(0);
                //dijit.byId("areaAddEditForm.isDisplay").attr("checked", "");
            } else {
                page.close();
                resultMessage_show("positionDeleteMessage", result[0], result[1], 5000);
            }

        }

        page.close = function() {
            dijit.byId("areaAddEditForm.areaId").setValue("");
            dijit.byId("areaAddEditForm.abbreviate").setValue("");
            dijit.byId("areaAddEditForm.name").setValue("");
            dijit.byId("areaAddEditForm.description").setValue("");
            dijit.byId("areaAddEditForm.orderNumber").setValue("");
            dijit.byId("areaAddEditForm.parentId").setSelectedIndex(0);
            //dijit.byId("areaAddEditForm.isDisplay").attr("checked", "");
            dlgAddEditArea.hide();
        }

</script>
