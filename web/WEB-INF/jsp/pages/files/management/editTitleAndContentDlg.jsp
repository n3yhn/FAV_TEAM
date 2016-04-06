<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>

<%--<jsp:include page="../../util/dataFlow.jsp"/>--%>
<!--Hiepvv-->
<div id="documentProcessGridId123456" style="width:100%; overflow-y: auto;max-height: 500px">
    <form id="createForm" name="createForm">
        <table class="editTable">
            <tr style="display: none">
                <td>
                    <sd:TextBox key="" id="createForm.fileId" name="createForm.fileId"  cssStyle="width:99%;margin:auto;" trim="true"/>
                </td>
            </tr>
            <tr>
                <td width="15%" ><sd:Label required="true">Về việc</sd:Label></td>
                    <td width="90%" >
                        <!--<textarea style="width:99%; height: 99%; margin-left: 5px;" ></textarea>-->
                    <sd:TextBox key="" id="createForm.titleEditATTP" name="createForm.titleEditATTP"  cssStyle="width:99%;margin:auto;" trim="true"/>
                </td>
            </tr>
            <tr>
                <td width="15%" ><sd:Label required="true">Nội dung sửa đổi</sd:Label></td>
                    <td width="90%" >
                        <!--<textarea style="width:99%; height: 99%; margin-left: 5px;" ></textarea>-->
                    <sd:Textarea key="" id="createForm.contentsEditATTP" name="createForm.contentsEditATTP"  cssStyle="width:99%;margin:auto;" trim="true" rows="20"/>
                </td>
            </tr>
        </table>
                <br/>
                <div style="width: 100%; text-align: center;">
                    <sd:Button key="" onclick="updateTitleAndContent();" >
                        <img src="${contextPath}/share/images/icons/Answer.png" height="14" width="18">
                        <span style="font-size:12px">Lưu sửa</span>
                    </sd:Button>
                </div>
                
    </form>
</div>

<script type="text/javascript">
    page.getProcess3= function(objectId) {
        dijit.byId("documentProcessGridId123456").vtReload("filesAction!loadTitleAndContentEditAfterAnnounced.do?fileId=" + objectId, null, null, null);
    };
    updateTitleAndContent = function(){
        var fileId = dijit.byId("createForm.fileId").getValue();
        var title = dijit.byId("createForm.titleEditATTP").getValue();
        var content = dijit.byId("createForm.contentsEditATTP").getValue();
        document.location = "filesAction!onEditTitleAndContent.do?fileId=" + fileId+"&&title="+title+"&&content="+content;
    };
</script>