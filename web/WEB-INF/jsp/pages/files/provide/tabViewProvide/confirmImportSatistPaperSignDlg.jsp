<%-- 
    Document   : confirmImportSatistPaperCreateDlg - 
'Nhập thông tin giấy xác nhận đạt yêu cầu 
nhập khẩu của cơ quan kiểm tra Nhà nước
về chất lượng thực phẩm nhập khẩu'
    Created on : 
    Author     : vtit_binhnt53
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../../../util/util_js.jsp"/>
<jsp:include page="../../../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<sx:ResultMessage id="resultMessage" />
<form id="signForm" name="signForm">
    <sx:ResultMessage id="resultMessage" />
    <div class="buttonDiv">
        <sx:ButtonBack onclick="backPage();"/>
        <sd:Button id="btnSign" key="" onclick="page.onSign();" >
            <img src="${contextPath}/share/images/icons/6.png" height="14" width="18">
            <span style="font-size:12px">Ký duyệt</span>
        </sd:Button>
        <sd:Button id="btnReject" key="" onclick="page.reject();">
            <img src="${contextPath}/share/images/icons/13.png" height="14" width="18">
            <span style="font-size:12px">Từ chối ký duyệt</span>
        </sd:Button>
    </div>
    <sd:TitlePane key="Thông tin chi tiết" id="registerViewPanel">
        <sd:FieldSet key="Cơ quan kiểm tra">
            <table class="editTable">
                <tr>
                    <td width="25%"><sd:Label required="true">Tên cơ quan kiểm tra</sd:Label><font style="color:red">*</font></td>
                        <td width="25%">
                            <div id="signForm.confirmImportSatistPaperForm.testAgencyName"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.testAgencyName)} </div>
                        <sd:TextBox 
                            id="signForm.fileId"
                            name="signForm.fileId" 
                            cssStyle="display:none" 
                            key=""
                            trim="true"/>
                        <sd:TextBox key="" 
                                    id="signForm.confirmImportSatistPaperForm.confirmImportSatistPaperId" 
                                    name="signForm.confirmImportSatistPaperForm.confirmImportSatistPaperId" 
                                    cssStyle="display:none" 
                                    trim="true"/>
                        <sd:TextBox key="" 
                                    id="signForm.confirmImportSatistPaperForm.testAgencyId" 
                                    name="signForm.confirmImportSatistPaperForm.testAgencyId" 
                                    cssStyle="display:none" 
                                    trim="true"/>
                        <sd:TextBox key="" 
                                    id="signForm.confirmImportSatistPaperForm.isActive" 
                                    name="signForm.confirmImportSatistPaperForm.isActive" 
                                    cssStyle="display:none" 
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Địa chỉ"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.testAgencyAdd"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.testAgencyAdd)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số fax"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.testAgencyFax"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.testAgencyFax)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Số điện thoại"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.testAgencyTel"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.testAgencyTel)} </div>
                    </td>
                </tr>
            </table>
        </sd:FieldSet>

        <sd:FieldSet key="Thương nhân xuất khẩu">
            <table class="editTable">
                <tr>
                    <td width="25%"><sx:Label key="Thương nhân" require="true"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.exportBusinessName"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.exportBusinessName)} </div>
                    </td>
                    <td colspan="2">
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Địa chỉ"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.exportBusinessAdd"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.exportBusinessAdd)} </div>
                    </td>
                    <td width="25%" ><sx:Label key="Email"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.exportBusinessMail"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.exportBusinessMail)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Điện thoại"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.exportBusinessTel"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.exportBusinessTel)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Fax"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.exportBusinessFax"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.exportBusinessFax)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số hợp đồng"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.exportContractCode"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.exportContractCode)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Ngày hợp đồng"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.exportContractDate"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.exportContractDate)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số vận đơn"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.exportLadingCode"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.exportLadingCode)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Ngày vận đơn"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.exportLadingDate"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.exportLadingDate)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Bến đi"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.exportPort"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.exportPort)} </div>
                    </td>
                    <td colspan="2"></td>
                </tr>
            </table>
        </sd:FieldSet>
        <br/>
        <sd:FieldSet key="Thương nhân nhập khẩu">
            <table class="editTable">
                <tr>
                    <td width="25%"><sx:Label key="Thương nhân" require="true"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.importBusinessName"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.importBusinessName)} </div>
                    </td>
                    <td colspan="2">
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Địa chỉ"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.importBusinessAddress"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.importBusinessAddress)} </div>
                    </td>
                    <td width="25%" ><sx:Label key="Email"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.importBusinessEmail"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.importBusinessEmail)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Điện thoại"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.importBusinessTel"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.importBusinessTel)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Fax"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.importBusinessFax"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.importBusinessFax)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Bến đến"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.importPort"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.importPort)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Thời gian nhập khẩu dự kiến"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.importDate"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.importDate)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số vận đơn"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.importContractCode"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.importContractCode)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Ngày vận đơn"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.importContractDate"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.importContractDate)} </div>
                    </td>
                </tr>
            </table>
        </sd:FieldSet>
        <br/>

        <sd:FieldSet key="Hàng hóa">
            <table class="editTable">
                <tr>                    
                    <td width="25%"><sx:Label key="Tên hàng hóa" require="true"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.productName"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.productName)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Mô tả hàng hóa"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.productDescription"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.productDescription)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Ký hiệu mã" require="true"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.productCode"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.productCode)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Xuất xứ"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.productOrigin"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.productOrigin)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số lượng"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.productAmount"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.productAmount)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Khối lượng"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.productWeight"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.productWeight)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Giá trị hàng hóa"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.productValue"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.productValue)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Địa điểm tập kết"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.gatheringAdd"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.gatheringAdd)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Thời gian kiểm tra"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.testDate"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.testDate)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Địa điểm kiểm tra"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.testAdd"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.testAdd)} </div>
                    </td>
                </tr>
            </table>

        </sd:FieldSet> 
        <br/>
        <sd:FieldSet key="Đại diện xác nhận">
            <table class="editTable">
                <tr>
                    <td width="25%"><sx:Label key="Đại diện thương nhân nhập khẩu"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.businessRepresent"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.businessRepresent)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Kết luận"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.conclusion"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.conclusion)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Địa điểm"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.businessSignAdd"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.businessSignAdd)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Ngày kí"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.businessSigndate"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.businessSigndate)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Đại diện của cơ quan kiểm tra"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.agencyRepresent"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.agencyRepresent)} </div>
                    </td>
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Địa điểm"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.agencySignAdd"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.agencySignAdd)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Ngày kí"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.agencySigndate"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.agencySigndate)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số tiêu chuẩn, qui chuẩn"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.standardTargetNo"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.standardTargetNo)} </div>
                    </td>
                    <td width="25%"><sx:Label key="Ngày cấp"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.standardTargetDate"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.standardTargetDate)} </div>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số công văn giải tỏa lô hàng"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.releaseDocumentNo"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.releaseDocumentNo)} </div>

                    </td>
                    <td width="25%"><sx:Label key="Ngày cấp"/></td>
                    <td width="25%">
                        <div id="signForm.confirmImportSatistPaperForm.releaseDocumentDate"> ${fn:escapeXml(signForm.confirmImportSatistPaperForm.releaseDocumentDate)} </div>
                    </td>
                </tr>
            </table>

        </sd:FieldSet>
    </sd:TitlePane>
    <br/> 
</form>
<sd:Dialog  id="dlgSign" height="auto" width="400px"
            key="Lí do" showFullscreenButton="false"
            >
    <jsp:include page="../signDialog.jsp" flush="false"></jsp:include>
</sd:Dialog>
<script type="text/javascript">
    page.onSign = function() {
        dijit.byId("signedForm.fileId").setValue(dijit.byId("signForm.fileId").getValue());
        //dijit.byId("signingForm.signContent").setValue("");
        dijit.byId("dlgSign").show();
    };
</script>