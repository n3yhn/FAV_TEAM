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
<form id="provideForm" name="provideForm">
    <sx:ResultMessage id="resultMessage" />
    <div class="buttonDiv">
        <sx:ButtonBack onclick="backPage();"/>
        <sx:ButtonSave onclick="page.save();"></sx:ButtonSave>              
    </div>
    <sd:TitlePane key="Thông tin chi tiết" id="registerViewPanel">
        <sd:FieldSet key="Cơ quan kiểm tra">
            <table class="editTable">
                <tr>
                    <td width="25%"><sd:Label required="true">Tên cơ quan kiểm tra</sd:Label><font style="color:red">*</font></td>
                        <td width="25%">
                        <sd:TextBox 
                            id="provideForm.fileId"
                            name="provideForm.fileId" 
                            cssStyle="display:none" 
                            key=""
                            trim="true"/>
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.testAgencyName" 
                                    name="provideForm.confirmImportSatistPaperForm.testAgencyName" 
                                    maxlength="50" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.confirmImportSatistPaperId" 
                                    name="provideForm.confirmImportSatistPaperForm.confirmImportSatistPaperId" 
                                    cssStyle="display:none" 
                                    trim="true"/>
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.testAgencyId" 
                                    name="provideForm.confirmImportSatistPaperForm.testAgencyId" 
                                    cssStyle="display:none" 
                                    trim="true"/>
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.isActive" 
                                    name="provideForm.confirmImportSatistPaperForm.isActive" 
                                    cssStyle="display:none" 
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Địa chỉ"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.testAgencyAdd" 
                                    name="provideForm.confirmImportSatistPaperForm.testAgencyAdd" 
                                    maxlength="250" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số fax"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.testAgencyFax" 
                                    name="provideForm.confirmImportSatistPaperForm.testAgencyFax" 
                                    maxlength="20" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Số điện thoại"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.testAgencyTel" 
                                    name="provideForm.confirmImportSatistPaperForm.testAgencyTel" 
                                    maxlength="20" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                </tr>
            </table>
        </sd:FieldSet>

        <sd:FieldSet key="Thương nhân xuất khẩu">
            <table class="editTable">
                <tr>
                    <td width="25%"><sx:Label key="Thương nhân" require="true"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.exportBusinessName" 
                                    name="provideForm.confirmImportSatistPaperForm.exportBusinessName" 
                                    maxlength="250" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                    <td colspan="2">
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Địa chỉ"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.exportBusinessAdd" 
                                    name="provideForm.confirmImportSatistPaperForm.exportBusinessAdd" 
                                    maxlength="250" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                    <td width="25%" ><sx:Label key="Email"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.exportBusinessMail" 
                                    name="provideForm.confirmImportSatistPaperForm.exportBusinessMail" 
                                    maxlength="50" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Điện thoại"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.exportBusinessTel" 
                                    name="provideForm.confirmImportSatistPaperForm.exportBusinessTel" 
                                    maxlength="20" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Fax"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.exportBusinessFax" 
                                    name="provideForm.confirmImportSatistPaperForm.exportBusinessFax" 
                                    maxlength="20" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số hợp đồng"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.exportContractCode" 
                                    name="provideForm.confirmImportSatistPaperForm.exportContractCode" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Ngày hợp đồng"/></td>
                    <td width="25%">
                        <sx:DatePicker key="" 
                                       id="provideForm.confirmImportSatistPaperForm.exportContractDate" 
                                       name="provideForm.confirmImportSatistPaperForm.exportContractDate" 
                                       format="dd/MM/yyyy" 
                                       cssStyle="width:99%"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số vận đơn"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.exportLadingCode" 
                                    name="provideForm.confirmImportSatistPaperForm.exportLadingCode" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Ngày vận đơn"/></td>
                    <td width="25%">
                        <sx:DatePicker 
                            key="" 
                            id="provideForm.confirmImportSatistPaperForm.exportLadingDate" 
                            name="provideForm.confirmImportSatistPaperForm.exportLadingDate" 
                            format="dd/MM/yyyy" 
                            cssStyle="width:99%"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Bến đi"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.exportPort" 
                                    name="provideForm.confirmImportSatistPaperForm.exportPort" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
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
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.importBusinessName" 
                                    name="provideForm.confirmImportSatistPaperForm.importBusinessName" 
                                    maxlength="250" 
                                    cssStyle="width:99%" 
                                    required="true"
                                    trim="true"/>
                    </td>
                    <td colspan="2">
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Địa chỉ"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.importBusinessAddress" 
                                    name="provideForm.confirmImportSatistPaperForm.importBusinessAddress" 
                                    maxlength="250" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                    <td width="25%" ><sx:Label key="Email"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.importBusinessEmail" 
                                    name="provideForm.confirmImportSatistPaperForm.importBusinessEmail" 
                                    maxlength="50" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Điện thoại"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.importBusinessTel" 
                                    name="provideForm.confirmImportSatistPaperForm.importBusinessTel" 
                                    maxlength="20" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Fax"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.importBusinessFax" 
                                    name="provideForm.confirmImportSatistPaperForm.importBusinessFax" 
                                    maxlength="20" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Bến đến"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.importPort" 
                                    name="provideForm.confirmImportSatistPaperForm.importPort" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Thời gian nhập khẩu dự kiến"/></td>
                    <td width="25%">
                        <sx:DatePicker key="" 
                                       id="provideForm.confirmImportSatistPaperForm.importDate" 
                                       name="provideForm.confirmImportSatistPaperForm.importDate" 
                                       format="dd/MM/yyyy" 
                                       cssStyle="width:99%"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số vận đơn"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.importContractCode" 
                                    name="provideForm.confirmImportSatistPaperForm.importContractCode" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Ngày vận đơn"/></td>
                    <td width="25%">
                        <sx:DatePicker key="" 
                                       id="provideForm.confirmImportSatistPaperForm.importContractDate" 
                                       name="provideForm.confirmImportSatistPaperForm.importContractDate" 
                                       format="dd/MM/yyyy" 
                                       cssStyle="width:99%"/>
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
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.productName" 
                                    name="provideForm.confirmImportSatistPaperForm.productName" 
                                    maxlength="250" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Mô tả hàng hóa"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.productDescription" 
                                    name="provideForm.confirmImportSatistPaperForm.productDescription" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Ký hiệu mã" require="true"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.productCode" 
                                    name="provideForm.confirmImportSatistPaperForm.productCode" 
                                    maxlength="50" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Xuất xứ"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.productOrigin" 
                                    name="provideForm.confirmImportSatistPaperForm.productOrigin" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số lượng"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.productAmount" 
                                    name="provideForm.confirmImportSatistPaperForm.productAmount" 
                                    maxlength="50" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Khối lượng"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.productWeight" 
                                    name="provideForm.confirmImportSatistPaperForm.productWeight" 
                                    maxlength="50" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Giá trị hàng hóa"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.productValue" 
                                    name="provideForm.confirmImportSatistPaperForm.productValue" 
                                    maxlength="50" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Địa điểm tập kết"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.gatheringAdd" 
                                    name="provideForm.confirmImportSatistPaperForm.gatheringAdd" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Thời gian kiểm tra"/></td>
                    <td width="25%">
                        <sx:DatePicker key="" id="provideForm.confirmImportSatistPaperForm.testDate" name="provideForm.confirmImportSatistPaperForm.testDate" cssStyle="width:99%"/>
                    </td>
                    <td width="25%"><sx:Label key="Địa điểm kiểm tra"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.testAdd" 
                                    name="provideForm.confirmImportSatistPaperForm.testAdd" 
                                    maxlength="200" 
                                    cssStyle="width:99%"
                                    trim="true"/>
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
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.businessRepresent" 
                                    name="provideForm.confirmImportSatistPaperForm.businessRepresent" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Kết luận"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.conclusion" 
                                    name="provideForm.confirmImportSatistPaperForm.conclusion" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Địa điểm"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.businessSignAdd" 
                                    name="provideForm.confirmImportSatistPaperForm.businessSignAdd" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Ngày ký"/></td>
                    <td width="25%">
                        <sx:DatePicker key="" 
                                       id="provideForm.confirmImportSatistPaperForm.businessSigndate" 
                                       name="provideForm.confirmImportSatistPaperForm.businessSigndate" 
                                       format="dd/MM/yyyy" 
                                       cssStyle="width:99%"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Đại diện của cơ quan kiểm tra"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.agencyRepresent" 
                                    name="provideForm.confirmImportSatistPaperForm.agencyRepresent" 
                                    maxlength="200" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Địa điểm"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.agencySignAdd" 
                                    name="provideForm.confirmImportSatistPaperForm.agencySignAdd" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Ngày ký"/></td>
                    <td width="25%">
                        <sx:DatePicker key="" id="provideForm.confirmImportSatistPaperForm.agencySigndate" name="provideForm.confirmImportSatistPaperForm.agencySigndate" format="dd/MM/yyyy" cssStyle="width:99%"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số tiêu chuẩn, qui chuẩn"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.standardTargetNo" 
                                    name="provideForm.confirmImportSatistPaperForm.standardTargetNo" 
                                    maxlength="200" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Ngày cấp"/></td>
                    <td width="25%">
                        <sx:DatePicker key="" 
                                       id="provideForm.confirmImportSatistPaperForm.standardTargetDate" 
                                       name="provideForm.confirmImportSatistPaperForm.standardTargetDate" 
                                       format="dd/MM/yyyy" 
                                       cssStyle="width:99%"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số công văn giải tỏa lô hàng"/></td>
                    <td width="25%">
                        <sd:TextBox key="" 
                                    id="provideForm.confirmImportSatistPaperForm.releaseDocumentNo" 
                                    name="provideForm.confirmImportSatistPaperForm.releaseDocumentNo" 
                                    maxlength="200" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Ngày cấp"/></td>
                    <td width="25%">
                        <sx:DatePicker key="" 
                                       id="provideForm.confirmImportSatistPaperForm.releaseDocumentDate" 
                                       name="provideForm.confirmImportSatistPaperForm.releaseDocumentDate" 
                                       format="dd/MM/yyyy" 
                                       cssStyle="width:99%"/>
                    </td>
                </tr>
            </table>

        </sd:FieldSet>
    </sd:TitlePane>
    <br/> 
</form>
<script type="text/javascript">
    page.validate = function() {
        if (!dijit.byId("provideForm.confirmImportSatistPaperForm.exportBusinessName").getValue()) {
            alert("[Thương nhân xuất khẩu] chưa nhập");
            dijit.byId("provideForm.confirmImportSatistPaperForm.exportBusinessName").focus();
            return false;
        }

        if (dijit.byId("provideForm.confirmImportSatistPaperForm.exportBusinessMail").getValue()) {
            var content = dijit.byId("provideForm.confirmImportSatistPaperForm.exportBusinessMail").getValue();
            if (!sd.validator.isEmail(content)) {
                alert("[Thương nhân xuất khẩu : Email] không đúng định dạng");
                dijit.byId("provideForm.confirmImportSatistPaperForm.exportBusinessMail").focus();
                return false;
            }
        }

        if (dijit.byId("provideForm.confirmImportSatistPaperForm.exportBusinessTel").getValue()) {
            var content = dijit.byId("provideForm.confirmImportSatistPaperForm.exportBusinessTel").getValue();
            if (!validatePhone(content)) {
                alert("[Thương nhân xuất khẩu : điện thoại] không đúng định dạng");
                dijit.byId("provideForm.confirmImportSatistPaperForm.exportBusinessTel").focus();
                return false;
            }
        }
        if (dijit.byId("provideForm.confirmImportSatistPaperForm.exportBusinessFax").getValue()) {
            var content = dijit.byId("provideForm.confirmImportSatistPaperForm.exportBusinessFax").getValue();
            if (!validatePhone(content)) {
                alert("[Thương nhân xuất khẩu : Fax] không đúng định dạng");
                dijit.byId("provideForm.confirmImportSatistPaperForm.exportBusinessFax").focus();
                return false;
            }
        }

        if (!dijit.byId("provideForm.confirmImportSatistPaperForm.importBusinessName").getValue()) {
            alert("[Thương nhân nhập khẩu] chưa nhập");
            dijit.byId("provideForm.confirmImportSatistPaperForm.importBusinessName").focus();
            return false;
        }

        if (dijit.byId("provideForm.confirmImportSatistPaperForm.importBusinessEmail").getValue()) {
            var content = dijit.byId("provideForm.confirmImportSatistPaperForm.importBusinessEmail").getValue();
            if (!sd.validator.isEmail(content)) {
                alert("[Thương nhân nhập khẩu : Email] không đúng định dạng");
                dijit.byId("provideForm.confirmImportSatistPaperForm.importBusinessEmail").focus();
                return false;
            }
        }

        if (dijit.byId("provideForm.confirmImportSatistPaperForm.importBusinessTel").getValue()) {
            var content = dijit.byId("provideForm.confirmImportSatistPaperForm.importBusinessTel").getValue();
            if (!validatePhone(content)) {
                alert("[Thương nhân nhập khẩu : điện thoại] không đúng định dạng");
                dijit.byId("provideForm.confirmImportSatistPaperForm.importBusinessTel").focus();
                return false;
            }
        }
        if (dijit.byId("provideForm.confirmImportSatistPaperForm.importBusinessFax").getValue()) {
            var content = dijit.byId("provideForm.confirmImportSatistPaperForm.importBusinessFax").getValue();
            if (!validatePhone(content)) {
                alert("[Thương nhân nhập khẩu : Fax] không đúng định dạng");
                dijit.byId("provideForm.confirmImportSatistPaperForm.importBusinessFax").focus();
                return false;
            }
        }

        if (!dijit.byId("provideForm.confirmImportSatistPaperForm.productName").getValue()) {
            alert("[Tên hàng hóa] chưa nhập");
            dijit.byId("provideForm.confirmImportSatistPaperForm.productName").focus();
            return false;
        }
        if (!dijit.byId("provideForm.confirmImportSatistPaperForm.productCode").getValue()) {
            alert("[Mã hàng hóa] chưa nhập");
            dijit.byId("provideForm.confirmImportSatistPaperForm.productCode").focus();
            return false;
        }
        return true;
    }
    //action save into db
    page.save = function() {
        if (page.validate()) {
            sd.connector.post("confirmImportSatistPaperAction!onProvide.do?" + token.getTokenParamString(), null, "provideForm", null, page.afterSave);
        }
    };
    //action after insert into db
    page.afterSave = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {
            var Id = dijit.byId("provideForm.confirmImportSatistPaperForm.confirmImportSatistPaperId").getValue();
            if (Id == null || Id == "") {
                page.clearInsertForm();
            } else {
                page.close();
            }
            page.search();
        }
    };
    //action after insert into db
    page.afterSave = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {
            backPage();
        }
    };
</script>