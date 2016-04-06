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
<sx:ResultMessage id="ReturnResultMessage"/>
<%--<sd:TitlePane key="" id="titlePaneApprove1" >--%>
<div id="processCommentFormDiv">
    <form id="processCommentForm" name="processCommentForm">
        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <td colspan="2" style="display: none">
                    <sd:TextBox cssStyle="display:none;" id="isApproveCA1" name="isApproveCA1" key=""/>
                    <sd:TextBox cssStyle="display:none;" id="actionFormDlg1" name="actionFormDlg" key=""/>                        
                </td>
            </tr></table>
    </form>
</div>
<%--</sd:TitlePane>--%>
<div>
    <table  width="100%;" cellpadding="0px" cellspacing="5px">
        <tr><td colspan="4" align ="center"> <sd:Label key="Vui lòng cắm usb đã được đăng ký với tài khoản này để ký xác thực"/>  </td></tr>
        <tr><td colspan="4" align ="center"> <sd:Label key="Lưu ý: Kích hoạt Java Platfom SE 7 (Nếu sử dụng trình duyệt Firefox) và chỉnh Java Security xuống Medium"/>  </td></tr>
        <tr>


            <td colspan="4" align ="center">
                <sd:Button id="btnOK1" key="" onclick="page.checksendPublish();" cssStyle="display">
                    <img src="share/images/active.png" height="14" width="14" alt="Gửi">
                    <span style="font-size:12px">Ký duyệt</span>
                </sd:Button>
                <sd:Button id="closeDlg1" key="" onclick="page.closeFormReturnOrApproveDlg();">
                    <img src="share/images/icons/deleteStand.png" height="14" width="14" alt="Đóng">
                    <span style="font-size:12px">Đóng</span>
                </sd:Button>
            </td>
        </tr>
    </table>

</div>
        
<script>
    page.closeFormReturnOrApproveDlg = function() {
        dijit.byId("dlgReturn1").hide();
    };
    page.checksendPublish = function (){
      msg.confirm('Sau khi ký số nội dung hồ sơ sẽ không thay đổi được nữa , bạn có chắc chắn thực hiện hành động này ?', '<sd:Property>Cảnh báo</sd:Property>',  page.sendPublish);  
    };
    page.sendPublish = function() {
        page.preApproveCA();
    };
</script>
