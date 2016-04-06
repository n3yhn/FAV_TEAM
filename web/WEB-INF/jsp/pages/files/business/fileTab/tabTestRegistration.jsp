<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
    <table class="editTable">
        <tr>
            <td width="25%"><sd:Label required="true">Tên cơ quan kiểm tra</sd:Label><font style="color:red">*</font></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.testAgency" name="createForm.testRegistration.testAgency" maxlength="50" cssStyle="width:99%" trim="true" />
                <sd:TextBox key="" id="createForm.testRegistration.testRegistrationId" name="createForm.testRegistration.testRegistrationId" cssStyle="display:none"/>
            </td>
            <td colspan="2"/>
        </tr>
    </table>


<sd:FieldSet key="Thương nhân xuất khẩu">
    <table class="editTable">
        <tr>
            <td width="25%"><sd:Label required="true">Thương nhân</sd:Label><font style="color:red">*</font></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.exportBusinessName" name="createForm.testRegistration.exportBusinessName" maxlength="250" cssStyle="width:99%" trim="true" />
            </td>
            <td colspan="2">
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label required="true">Địa chỉ</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.exportBusinessAdd" name="createForm.testRegistration.exportBusinessAdd" maxlength="250" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%" ><sd:Label required="true">Email</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.exportBusinessMail" name="createForm.testRegistration.exportBusinessMail" maxlength="50" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Điện thoại</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.exportBusinessTel" name="createForm.testRegistration.exportBusinessTel" maxlength="15" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Fax</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.exportBusinessFax" name="createForm.testRegistration.exportBusinessFax" maxlength="15" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Số hợp đồng</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.exportContractCode" name="createForm.testRegistration.exportContractCode" maxlength="250" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Ngày hợp đồng</sd:Label></td>
                <td width="25%">
                <sx:DatePicker key="" id="createForm.testRegistration.exportContractDate" name="createForm.testRegistration.exportContractDate" format="dd/MM/yyyy" cssStyle="width:99%"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Số vận đơn</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.exportLadingCode" name="createForm.testRegistration.exportLadingCode" maxlength="250" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Ngày vận đơn</sd:Label></td>
                <td width="25%">
                <sx:DatePicker key="" id="createForm.testRegistration.exportLadingDate" name="createForm.testRegistration.exportLadingDate" format="dd/MM/yyyy" cssStyle="width:99%"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Bến đi</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.exportPort" name="createForm.testRegistration.exportPort" maxlength="250" cssStyle="width:99%" trim="true"/>
            </td>
            <td colspan="2"></td>
        </tr>
    </table>
</sd:FieldSet>
<br/>
<sd:FieldSet key="Thương nhân nhập khẩu">
    <table class="editTable">
        <tr>
            <td width="25%"><sd:Label required="true">Thương nhân</sd:Label><font style="color:red">*</font></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.importBusinessName" name="createForm.testRegistration.importBusinessName" maxlength="250" cssStyle="width:99%"  trim="true"/>
            </td>
            <td colspan="2">
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label required="true">Địa chỉ</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.importBusinessAddress" name="createForm.testRegistration.importBusinessAddress" maxlength="250" cssStyle="width:99%" trim="true" />
            </td>
            <td width="25%" ><sd:Label required="true">Email</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.importBusinessEmail" name="createForm.testRegistration.importBusinessEmail" maxlength="50" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Điện thoại</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.importBusinessTel" name="createForm.testRegistration.importBusinessTel" maxlength="15" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Fax</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.importBusinessFax" name="createForm.testRegistration.importBusinessFax" maxlength="15" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Bến đến</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.importPort" name="createForm.testRegistration.importPort" maxlength="250" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Thời gian nhập khẩu dự kiến</sd:Label></td>
                <td width="25%">
                <sx:DatePicker key="" id="createForm.testRegistration.importDate" name="createForm.testRegistration.importDate" format="dd/MM/yyyy" cssStyle="width:99%"/>
            </td>
        </tr>
    </table>
</sd:FieldSet>
<br/>

<sd:FieldSet key="Hàng hóa">
    <table class="editTable">
        <tr>
            <td width="25%"><sd:Label required="true">Mô tả hàng hóa</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.productDescription" name="createForm.testRegistration.productDescription" maxlength="250" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Tên hàng hóa</sd:Label><font style="color:red">*</font></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.productName" name="createForm.testRegistration.productName" maxlength="250" cssStyle="width:99%"  trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Ký hiệu mã</sd:Label><font style="color:red">*</font></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.productCode" name="createForm.testRegistration.productCode" maxlength="50" cssStyle="width:99%" trim="true" />
            </td>
            <td width="25%"><sd:Label>Xuất xứ</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.productOrigin" name="createForm.testRegistration.productOrigin" maxlength="250" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Số lượng</sd:Label></td>
                <td width="25%">
                    <sd:TextBox key="" id="createForm.testRegistration.productAmount" name="createForm.testRegistration.productAmount" maxlength="50" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Khối lượng</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.productWeight" name="createForm.testRegistration.productWeight" maxlength="50" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Giá trị hàng hóa</sd:Label></td>
                <td width="25%">
                    <sd:TextBox key="" id="createForm.testRegistration.productValue" name="createForm.testRegistration.productValue" maxlength="50" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Địa điểm tập kết</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.gatheringAdd" name="createForm.testRegistration.gatheringAdd" maxlength="250" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Thời gian kiểm tra</sd:Label></td>
                <td width="25%">
                <sx:DatePicker key="" id="createForm.testRegistration.testDate" name="createForm.testRegistration.testDate" cssStyle="width:99%"/>
            </td>
            <td width="25%"><sd:Label>Địa điểm kiểm tra</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.testAdd" name="createForm.testRegistration.testAdd" maxlength="200" cssStyle="width:99%" trim="true"/>
            </td>
        </tr>
    </table>

</sd:FieldSet> 
<br/>
<sd:FieldSet key="Đại diện xác nhận">
    <table class="editTable">
        <tr>
            <td width="25%"><sd:Label required="true">Đại diện thương nhân nhập khẩu</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.businessRepresent" name="createForm.testRegistration.businessRepresent" maxlength="250" cssStyle="width:99%" trim="true"/>
            </td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Địa điểm</sd:Label></td>
                <td width="25%">
                    <sd:TextBox key="" id="createForm.testRegistration.businessSignAdd" name="createForm.testRegistration.businessSignAdd" maxlength="250" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Ngày ký</sd:Label></td>
                <td width="25%">
                <sx:DatePicker key="" id="createForm.testRegistration.businessSigndate" name="createForm.testRegistration.businessSigndate" format="dd/MM/yyyy" cssStyle="width:99%"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Đại diện của cơ quan kiểm tra</sd:Label></td>
                <td width="25%">
                    <sd:TextBox key="" id="createForm.testRegistration.agencyRepresent" name="createForm.testRegistration.agencyRepresent" maxlength="200" cssStyle="width:99%" trim="true"/>
            </td>
            <td colspan="2"></td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Địa điểm</sd:Label></td>
                <td width="25%">
                    <sd:TextBox key="" id="createForm.testRegistration.agencySignAdd" name="createForm.testRegistration.agencySignAdd" maxlength="250" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Ngày ký</sd:Label></td>
                <td width="25%">
                <sx:DatePicker key="" id="createForm.testRegistration.agencySigndate" name="createForm.testRegistration.agencySigndate" format="dd/MM/yyyy" cssStyle="width:99%"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Số tiêu chuẩn, quy chuẩn</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.standardTargetNo" name="createForm.testRegistration.standardTargetNo" maxlength="200" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Ngày cấp</sd:Label></td>
                <td width="25%">
                <sx:DatePicker key="" id="createForm.testRegistration.standardTargetDate" name="createForm.testRegistration.standardTargetDate" format="dd/MM/yyyy" cssStyle="width:99%"/>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Số công văn giải tỏa lô hàng</sd:Label></td>
                <td width="25%">
                <sd:TextBox key="" id="createForm.testRegistration.releaseDocumentNo" name="createForm.testRegistration.releaseDocumentNo" maxlength="200" cssStyle="width:99%" trim="true"/>
            </td>
            <td width="25%"><sd:Label>Ngày cấp</sd:Label></td>
                <td width="25%">
                <sx:DatePicker key="" id="createForm.testRegistration.releaseDocumentDate" name="createForm.testRegistration.releaseDocumentDate" format="dd/MM/yyyy" cssStyle="width:99%"/>
            </td>
        </tr>
    </table>

</sd:FieldSet> 

<script type="text/javascript">
    page.validateTestRegistration = function(){
        var tabs = dijit.byId("files_tab");
        var panel = dijit.byId("tab.testRegistration");
        tabs.selectChild(panel);
        
        if(!dijit.byId("createForm.testRegistration.testAgency").getValue()){
            alert("[Tên cơ quan kiểm tra] chưa nhập");
            dijit.byId("createForm.testRegistration.testAgency").focus();
            return false;       
        }
        
        if(!dijit.byId("createForm.testRegistration.exportBusinessName").getValue()){
            alert("[Thương nhân xuất khẩu] chưa nhập");
            dijit.byId("createForm.testRegistration.exportBusinessName").focus();
            return false;       
        }
        
        if(dijit.byId("createForm.testRegistration.exportBusinessMail").getValue()){
            var content = dijit.byId("createForm.testRegistration.exportBusinessMail").getValue();
            if(!sd.validator.isEmail(content)){
                alert("[Thương nhân xuất khẩu : Email] không đúng định dạng");
                dijit.byId("createForm.testRegistration.exportBusinessMail").focus();
                return false;       
            }
        } 
        
        if(dijit.byId("createForm.testRegistration.exportBusinessTel").getValue()){
            var content = dijit.byId("createForm.testRegistration.exportBusinessTel").getValue();
            if(!validatePhone(content)){
                alert("[Thương nhân xuất khẩu : điện thoại] không đúng định dạng");
                dijit.byId("createForm.testRegistration.exportBusinessTel").focus();
                return false;       
            }
        } 
        if(dijit.byId("createForm.testRegistration.exportBusinessFax").getValue()){
            var content = dijit.byId("createForm.testRegistration.exportBusinessFax").getValue();
            if(!validatePhone(content)){
                alert("[Thương nhân xuất khẩu : Fax] không đúng định dạng");
                dijit.byId("createForm.testRegistration.exportBusinessFax").focus();
                return false;       
            }
        } 
        
        if(!dijit.byId("createForm.testRegistration.importBusinessName").getValue()){
            alert("[Thương nhân nhập khẩu] chưa nhập");
            dijit.byId("createForm.testRegistration.importBusinessName").focus();
            return false;       
        }
        
        if(dijit.byId("createForm.testRegistration.importBusinessEmail").getValue()){
            var content = dijit.byId("createForm.testRegistration.importBusinessEmail").getValue();
            if(!sd.validator.isEmail(content)){
                alert("[Thương nhân nhập khẩu : Email] không đúng định dạng");
                dijit.byId("createForm.testRegistration.importBusinessEmail").focus();
                return false;       
            }
        } 
        
        if(dijit.byId("createForm.testRegistration.importBusinessTel").getValue()){
            var content = dijit.byId("createForm.testRegistration.importBusinessTel").getValue();
            if(!validatePhone(content)){
                alert("[Thương nhân nhập khẩu : điện thoại] không đúng định dạng");
                dijit.byId("createForm.testRegistration.importBusinessTel").focus();
                return false;       
            }
        } 
        if(dijit.byId("createForm.testRegistration.importBusinessFax").getValue()){
            var content = dijit.byId("createForm.testRegistration.importBusinessFax").getValue();
            if(!validatePhone(content)){
                alert("[Thương nhân nhập khẩu : Fax] không đúng định dạng");
                dijit.byId("createForm.testRegistration.importBusinessFax").focus();
                return false;       
            }
        } 
        
        if(!dijit.byId("createForm.testRegistration.productName").getValue()){
            alert("[Tên hàng hóa] chưa nhập");
            dijit.byId("createForm.testRegistration.productName").focus();
            return false;       
        }
        if(!dijit.byId("createForm.testRegistration.productCode").getValue()){
            alert("[Mã hàng hóa] chưa nhập");
            dijit.byId("createForm.testRegistration.productCode").focus();
            return false;       
        } 
        return true;       
    }
</script>