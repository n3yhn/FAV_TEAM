<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<div id="token">
    <s:token id="tokenId"/>
</div>
<div id="myapplet" style="visibility: hidden;height: 0px !important;" > 
</div>
<sx:ResultMessage id="ReturnResultMessage"/>
<sd:TitlePane key="Thông tin" id="titlePaneApprove" >
    <div id="processCommentFormDiv">
        <form id="createForm" name="createForm">
            <table width="100%;" cellpadding="0px" cellspacing="5px">
                <tr>
                    <td colspan="2" style="display:">
                        Vui lòng kết nối USB Token vào máy tính, thực hiện kí kiểm tra                        
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="display: none">
                        <sd:TextBox cssStyle="display:none;" id="isApproveCA" name="isApproveCA" key=""/>
                        <sd:TextBox cssStyle="display:none;" id="actionFormDlg" name="actionFormDlg" key=""/>      
                        <sd:TextBox key="" id="createForm.contentSigned" name="createForm.contentSigned" cssStyle="display:none"/>
                        <sd:TextBox key="" id="createForm.fileId" name="createForm.fileId" cssStyle="display:none" value="374909"/>
                    </td>
                </tr></table>
        </form>
    </div>
</sd:TitlePane>
<div>
    <table  width="100%;" cellpadding="0px" cellspacing="5px">
        <tr>
            <td colspan="4" align ="center">

                <sd:Button id="btnOK" key="" onclick="page.sendPublish();" cssStyle="display: none">
                    <img src="share/images/active.png" height="14" width="14" alt="Gửi">
                    <span style="font-size:12px">Kết Nối</span>
                </sd:Button>
                <sd:Button id="closeDlg" key="" onclick="page.closeFormReturnOrApproveDlg();">
                    <img src="share/images/icons/deleteStand.png" height="14" width="14" alt="Đóng">
                    <span style="font-size:12px">Đóng</span>
                </sd:Button>
            </td>
        </tr>
    </table>

</div>
<script>
    page.closeFormReturnOrApproveDlg = function() {
        dijit.byId("dlgReturn").hide();
    };

    //  approve
    page.sendPublish = function() {
        page.preApproveCA();
    };
</script>
