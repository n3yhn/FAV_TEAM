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
<jsp:include page="../../util/util_js.jsp"/>
<jsp:include page="../../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<sx:ResultMessage id="resultMessage" />
<form id="createForm" name="createForm">
    <div class="buttonDiv">
        <sx:ButtonSave onclick="page.save();"></sx:ButtonSave>  
        <sx:ButtonClose onclick="page.close();"></sx:ButtonClose> 
        </div>
    <sd:TitlePane key="Thông tin chi tiết" id="registerViewPanel">
        <sd:FieldSet key="Cơ quan kiểm tra">
        <table class="editTable">
            <tr>
                <td width="25%"><sd:Label required="true">Tên cơ quan kiểm tra</sd:Label><font style="color:red">*</font></td>
                    <td width="25%">
                    <sd:TextBox key="" 
                                id="createForm.testAgencyName" 
                                name="createForm.testAgencyName" 
                                maxlength="50" 
                                cssStyle="width:99%" 
                                trim="true"/>
                    <sd:TextBox key="" 
                                id="createForm.confirmImportSatistPaperId" 
                                name="createForm.confirmImportSatistPaperId" 
                                cssStyle="display:none" 
                                trim="true"/>
                    <sd:TextBox key="" 
                                id="createForm.testAgencyId" 
                                name="createForm.testAgencyId" 
                                cssStyle="display:none" 
                                trim="true"/>
                    <sd:TextBox key="" 
                                id="createForm.isActive" 
                                name="createForm.isActive" 
                                cssStyle="display:none" 
                                trim="true"/>
                </td>
                <td width="25%"><sx:Label key="Địa chỉ"/></td>
                    <td width="25%">
                    <sd:TextBox key="" 
                                id="createForm.testAgencyAdd" 
                                name="createForm.testAgencyAdd" 
                                maxlength="250" 
                                cssStyle="width:99%" 
                                trim="true"/>
                </td>
            </tr>
            <tr>
                <td width="25%"><sx:Label key="Số fax"/></td>
                    <td width="25%">
                    <sd:TextBox key="" 
                                id="createForm.testAgencyFax" 
                                name="createForm.testAgencyFax" 
                                maxlength="20" 
                                cssStyle="width:99%" 
                                trim="true"/>
                </td>
                <td width="25%"><sx:Label key="Số điện thoại"/></td>
                    <td width="25%">
                    <sd:TextBox key="" 
                                id="createForm.testAgencyTel" 
                                name="createForm.testAgencyTel" 
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
                                    id="createForm.exportBusinessName" 
                                    name="createForm.exportBusinessName" 
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
                                    id="createForm.exportBusinessAdd" 
                                    name="createForm.exportBusinessAdd" 
                                    maxlength="250" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                    <td width="25%" ><sx:Label key="Email"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.exportBusinessMail" 
                                    name="createForm.exportBusinessMail" 
                                    maxlength="50" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Điện thoại"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.exportBusinessTel" 
                                    name="createForm.exportBusinessTel" 
                                    maxlength="20" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Fax"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.exportBusinessFax" 
                                    name="createForm.exportBusinessFax" 
                                    maxlength="20" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số hợp đồng"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.exportContractCode" 
                                    name="createForm.exportContractCode" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Ngày hợp đồng"/></td>
                        <td width="25%">
                        <sx:DatePicker key="" 
                                       id="createForm.exportContractDate" 
                                       name="createForm.exportContractDate" 
                                       format="dd/MM/yyyy" 
                                       cssStyle="width:99%"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số vận đơn"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.exportLadingCode" 
                                    name="createForm.exportLadingCode" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Ngày vận đơn"/></td>
                        <td width="25%">
                        <sx:DatePicker 
                            key="" 
                            id="createForm.exportLadingDate" 
                            name="createForm.exportLadingDate" 
                            format="dd/MM/yyyy" 
                            cssStyle="width:99%"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Bến đi"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.exportPort" 
                                    name="createForm.exportPort" 
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
                                    id="createForm.importBusinessName" 
                                    name="createForm.importBusinessName" 
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
                                    id="createForm.importBusinessAddress" 
                                    name="createForm.importBusinessAddress" 
                                    maxlength="250" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                    <td width="25%" ><sx:Label key="Email"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.importBusinessEmail" 
                                    name="createForm.importBusinessEmail" 
                                    maxlength="50" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Điện thoại"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.importBusinessTel" 
                                    name="createForm.importBusinessTel" 
                                    maxlength="20" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Fax"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.importBusinessFax" 
                                    name="createForm.importBusinessFax" 
                                    maxlength="20" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Bến đến"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.importPort" 
                                    name="createForm.importPort" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Thời gian nhập khẩu dự kiến"/></td>
                        <td width="25%">
                        <sx:DatePicker key="" 
                                       id="createForm.importDate" 
                                       name="createForm.importDate" 
                                       format="dd/MM/yyyy" 
                                       cssStyle="width:99%"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số vận đơn"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.importContractCode" 
                                    name="createForm.importContractCode" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Ngày vận đơn"/></td>
                        <td width="25%">
                        <sx:DatePicker key="" 
                                       id="createForm.importContractDate" 
                                       name="createForm.importContractDate" 
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
                                    id="createForm.productName" 
                                    name="createForm.productName" 
                                    maxlength="250" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Mô tả hàng hóa"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.productDescription" 
                                    name="createForm.productDescription" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Ký hiệu mã" require="true"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.productCode" 
                                    name="createForm.productCode" 
                                    maxlength="50" 
                                    cssStyle="width:99%" 
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Xuất xứ"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.productOrigin" 
                                    name="createForm.productOrigin" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số lượng"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.productAmount" 
                                    name="createForm.productAmount" 
                                    maxlength="50" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Khối lượng"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.productWeight" 
                                    name="createForm.productWeight" 
                                    maxlength="50" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Giá trị hàng hóa"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.productValue" 
                                    name="createForm.productValue" 
                                    maxlength="50" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Địa điểm tập kết"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.gatheringAdd" 
                                    name="createForm.gatheringAdd" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Thời gian kiểm tra"/></td>
                        <td width="25%">
                        <sx:DatePicker key="" id="createForm.testDate" name="createForm.testDate" cssStyle="width:99%"/>
                    </td>
                    <td width="25%"><sx:Label key="Địa điểm kiểm tra"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.testAdd" 
                                    name="createForm.testAdd" 
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
                                    id="createForm.businessRepresent" 
                                    name="createForm.businessRepresent" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Kết luận"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.conclusion" 
                                    name="createForm.conclusion" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Địa điểm"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.businessSignAdd" 
                                    name="createForm.businessSignAdd" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Ngày ký"/></td>
                        <td width="25%">
                        <sx:DatePicker key="" 
                                       id="createForm.businessSigndate" 
                                       name="createForm.businessSigndate" 
                                       format="dd/MM/yyyy" 
                                       cssStyle="width:99%"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Đại diện của cơ quan kiểm tra"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.agencyRepresent" 
                                    name="createForm.agencyRepresent" 
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
                                    id="createForm.agencySignAdd" 
                                    name="createForm.agencySignAdd" 
                                    maxlength="250" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Ngày ký"/></td>
                        <td width="25%">
                        <sx:DatePicker key="" id="createForm.agencySigndate" name="createForm.agencySigndate" format="dd/MM/yyyy" cssStyle="width:99%"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số tiêu chuẩn, qui chuẩn"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.standardTargetNo" 
                                    name="createForm.standardTargetNo" 
                                    maxlength="200" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Ngày cấp"/></td>
                        <td width="25%">
                        <sx:DatePicker key="" 
                                       id="createForm.standardTargetDate" 
                                       name="createForm.standardTargetDate" 
                                       format="dd/MM/yyyy" 
                                       cssStyle="width:99%"/>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><sx:Label key="Số công văn giải tỏa lô hàng"/></td>
                        <td width="25%">
                        <sd:TextBox key="" 
                                    id="createForm.releaseDocumentNo" 
                                    name="createForm.releaseDocumentNo" 
                                    maxlength="200" 
                                    cssStyle="width:99%"
                                    trim="true"/>
                    </td>
                    <td width="25%"><sx:Label key="Ngày cấp"/></td>
                        <td width="25%">
                        <sx:DatePicker key="" 
                                       id="createForm.releaseDocumentDate" 
                                       name="createForm.releaseDocumentDate" 
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
        if (!dijit.byId("createForm.exportBusinessName").getValue()) {
            alert("[Thương nhân xuất khẩu] chưa nhập");
            dijit.byId("createForm.exportBusinessName").focus();
            return false;
        }

        if (dijit.byId("createForm.exportBusinessMail").getValue()) {
            var content = dijit.byId("createForm.exportBusinessMail").getValue();
            if (!sd.validator.isEmail(content)) {
                alert("[Thương nhân xuất khẩu : Email] không đúng định dạng");
                dijit.byId("createForm.exportBusinessMail").focus();
                return false;
            }
        }

        if (dijit.byId("createForm.exportBusinessTel").getValue()) {
            var content = dijit.byId("createForm.exportBusinessTel").getValue();
            if (!validatePhone(content)) {
                alert("[Thương nhân xuất khẩu : điện thoại] không đúng định dạng");
                dijit.byId("createForm.exportBusinessTel").focus();
                return false;
            }
        }
        if (dijit.byId("createForm.exportBusinessFax").getValue()) {
            var content = dijit.byId("createForm.exportBusinessFax").getValue();
            if (!validatePhone(content)) {
                alert("[Thương nhân xuất khẩu : Fax] không đúng định dạng");
                dijit.byId("createForm.exportBusinessFax").focus();
                return false;
            }
        }

        if (!dijit.byId("createForm.importBusinessName").getValue()) {
            alert("[Thương nhân nhập khẩu] chưa nhập");
            dijit.byId("createForm.importBusinessName").focus();
            return false;
        }

        if (dijit.byId("createForm.importBusinessEmail").getValue()) {
            var content = dijit.byId("createForm.importBusinessEmail").getValue();
            if (!sd.validator.isEmail(content)) {
                alert("[Thương nhân nhập khẩu : Email] không đúng định dạng");
                dijit.byId("createForm.importBusinessEmail").focus();
                return false;
            }
        }

        if (dijit.byId("createForm.importBusinessTel").getValue()) {
            var content = dijit.byId("createForm.importBusinessTel").getValue();
            if (!validatePhone(content)) {
                alert("[Thương nhân nhập khẩu : điện thoại] không đúng định dạng");
                dijit.byId("createForm.importBusinessTel").focus();
                return false;
            }
        }
        if (dijit.byId("createForm.importBusinessFax").getValue()) {
            var content = dijit.byId("createForm.importBusinessFax").getValue();
            if (!validatePhone(content)) {
                alert("[Thương nhân nhập khẩu : Fax] không đúng định dạng");
                dijit.byId("createForm.importBusinessFax").focus();
                return false;
            }
        }

        if (!dijit.byId("createForm.productName").getValue()) {
            alert("[Tên hàng hóa] chưa nhập");
            dijit.byId("createForm.productName").focus();
            return false;
        }
        if (!dijit.byId("createForm.productCode").getValue()) {
            alert("[Mã hàng hóa] chưa nhập");
            dijit.byId("createForm.productCode").focus();
            return false;
        }
        return true;
    }
    //action save into db
    page.save = function() {
        if (page.validate()) {
            sd.connector.post("confirmImportSatistPaperAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSave);
        }
    };
    //action after insert into db
    page.afterSave = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
            dijit.byId("createForm.productName").focus();
        } else {
            var Id = dijit.byId("createForm.confirmImportSatistPaperId").getValue();
            if (Id == null || Id == "") {
                page.clearInsertForm();
            } else {
                page.close();
            }
            page.search();
        }
    };
    //action hide form
    page.close = function() {
        document.getElementById("mainDiv").style.display = "";
        document.getElementById("createDiv").style.display = "none";
        page.search();
    };
</script>