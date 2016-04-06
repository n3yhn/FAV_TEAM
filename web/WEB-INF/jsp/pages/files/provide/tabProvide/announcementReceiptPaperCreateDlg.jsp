<%-- 
    Document   : AnnouncementReceiptPaperCreateDlg - 
Chức năng nhập thông tin giấy tiếp nhận bản công bố hợp quy
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
<div id="token">
    <s:token id="tokenId"/>
</div>
<form id="provideForm" name="provideForm">
    <sx:ResultMessage id="resultMessage" />
    <div class="buttonDiv">
        <sx:ButtonBack onclick="backPage();"/>
        <sx:ButtonSave onclick="page.save();"></sx:ButtonSave>              
        </div>
    <sd:TitlePane key="Nhập thông tin giấy tiếp nhận hồ sơ" id="TitleTitle">
        <sd:FieldSet key="Thông tin" id="tab.userRep">
            <table width="100%" class="editTable">
                <tr>
                    <td width="15%">
                        <sd:Label required="true">Số giấy tiếp nhận:</sd:Label><font style="color:red">*</font></td>
                        <td width="35%">
                        <sd:TextBox 
                            id="provideForm.announcementReceiptPaperForm.receiptNo" 
                            name="provideForm.announcementReceiptPaperForm.receiptNo" 
                            key="" 
                            maxlength="50" 
                            required="true" 
                            cssStyle="width:99%"
                            trim="true" htmlAttributes="title='- Đối với Giấy tiếp nhận do Cục ATTP cấp, Số giấy tiếp nhận được nhập theo định dạng: (số thứ tự)/(năm cấp)/ATTP-TNCB
- Đối với Giấy tiếp nhận do Chi cục ATVSTP tỉnh/thành phố cấp, nhập theo định dạng: (số thứ tự)/(năm cấp)/YT+tên viết tắt tỉnh/thành phố-TNCB'"/>
                        <sd:TextBox 
                            id="provideForm.announcementReceiptPaperForm.announcementReceiptPaperId" 
                            name="provideForm.announcementReceiptPaperForm.announcementReceiptPaperId" 
                            cssStyle="display:none" 
                            key=""
                            trim="true"/>
                        <sd:TextBox 
                            id="provideForm.fileId"
                            name="provideForm.fileId" 
                            cssStyle="display:none" 
                            key=""
                            trim="true"/>
                        <sd:TextBox 
                            id="provideForm.announcementReceiptPaperForm.isActive" 
                            name="provideForm.announcementReceiptPaperForm.isActive" 
                            cssStyle="display:none" 
                            key=""
                            trim="true"/>
                    </td>
                    <td width="15%"><sd:Label required="true">Ngày ban hành:</sd:Label><font style="color:red">*</font></td>
                        <td width="35%">
                        <sx:DatePicker cssStyle="width:99%"
                                       id="provideForm.announcementReceiptPaperForm.receiptDate"
                                       key=""
                                       name="provideForm.announcementReceiptPaperForm.receiptDate" format="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label required="true">Tên cơ quan tiếp nhận công bố:</sd:Label><font style="color:red">*</font></td>
                        <td>
                        <sd:TextBox 
                            id="provideForm.announcementReceiptPaperForm.receiptDeptName" 
                            name="provideForm.announcementReceiptPaperForm.receiptDeptName" 
                            key="" 
                            maxlength="250" 
                            required="true" 
                            cssStyle="width:99%"
                            trim="true"/>
                    </td>
                    <td><sd:Label required="true">Tên tổ chức, cá nhân:</sd:Label><font style="color:red">*</font></td>
                        <td>
                        <sd:TextBox 
                            id="provideForm.announcementReceiptPaperForm.businessName" 
                            name="provideForm.announcementReceiptPaperForm.businessName" 
                            key="" 
                            maxlength="250" 
                            required="true" 
                            cssStyle="width:99%"
                            trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label required="true">Điện thoại:</sd:Label><font style="color:red">*</font></td>
                        <td>
                        <sd:TextBox 
                            id="provideForm.announcementReceiptPaperForm.telephone" 
                            name="provideForm.announcementReceiptPaperForm.telephone" 
                            key="" 
                            maxlength="20" 
                            required="true" 
                            cssStyle="width:99%"
                            trim="true"/>
                    </td>
                    <td><sd:Label required="true">Fax:</sd:Label><font style="color:red">*</font></td>
                        <td>
                        <sd:TextBox 
                            id="provideForm.announcementReceiptPaperForm.fax" 
                            name="provideForm.announcementReceiptPaperForm.fax" 
                            key="" 
                            maxlength="20" 
                            required="true" 
                            cssStyle="width:99%"
                            trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label required="true">Email:</sd:Label><font style="color:red">*</font></td>
                        <td>
                        <sd:TextBox 
                            id="provideForm.announcementReceiptPaperForm.email" 
                            name="provideForm.announcementReceiptPaperForm.email" 
                            key="" 
                            maxlength="100" 
                            required="true" 
                            cssStyle="width:99%"
                            trim="true"/>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td><sd:Label required="true">Tên sản phẩm:</sd:Label><font style="color:red">*</font></td>
                        <td>
                        <sd:TextBox 
                            id="provideForm.announcementReceiptPaperForm.productName" 
                            name="provideForm.announcementReceiptPaperForm.productName" 
                            key="" 
                            maxlength="250" 
                            required="true" 
                            cssStyle="width:99%"
                            trim="true"/>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td><sd:Label required="true">Tên cơ sở sản xuất:</sd:Label><font style="color:red">*</font></td>
                        <td>
                        <sd:TextBox 
                            id="provideForm.announcementReceiptPaperForm.manufactureName" 
                            name="provideForm.announcementReceiptPaperForm.manufactureName" 
                            key="" 
                            maxlength="250" 
                            required="true" 
                            cssStyle="width:99%"
                            trim="true"/>
                    </td>
                    <td><sd:Label required="true">Nước xuất xứ:</sd:Label><font style="color:red">*</font></td>
                        <td>
                        <sd:SelectBox id="provideForm.announcementReceiptPaperForm.nationName" name="provideForm.announcementReceiptPaperForm.nationName" key="" data="lstNation" valueField="name" labelField="name" />
                    </td>
                </tr>
            </table>
        </sd:FieldSet>

        <sd:FieldSet key="Xác thực" id="tab.business">
            <table width="100%" class="editTable">
                <tr>
                    <td width="15%"><sd:Label required="true">Số hiệu qui chuẩn kĩ thuật:</sd:Label><font style="color:red">*</font></td>
                        <td width="35%">
                        <sd:TextBox 
                            id="provideForm.announcementReceiptPaperForm.matchingTarget" 
                            name="provideForm.announcementReceiptPaperForm.matchingTarget" 
                            key="" 
                            maxlength="50" 
                            required="true" 
                            cssStyle="width:99%"
                            trim="true"/>
                    </td>
                    <td width="15%"><sd:Label required="true">Thời gian hiệu lực của giấy tiếp nhận:</sd:Label><font style="color:red">*</font></td>
                        <td width="35%">
                        <sx:DatePicker cssStyle="width:99%"
                                       id="provideForm.announcementReceiptPaperForm.effectiveDate"
                                       key=""
                                       name="provideForm.announcementReceiptPaperForm.effectiveDate" format="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <sd:Label required="true">Ngày ký:</sd:Label><font style="color:red">*</font></td>
                        <td>
                        <sx:DatePicker cssStyle="width:99%"
                                       id="provideForm.announcementReceiptPaperForm.signDate"
                                       key=""
                                       name="provideForm.announcementReceiptPaperForm.signDate" format="dd/MM/yyyy"/>
                    </td>
                    <td><sd:Label required="true">Người ký:</sd:Label><font style="color:red">*</font></td>
                        <td>
                        <sd:TextBox 
                            id="provideForm.announcementReceiptPaperForm.signerName" 
                            name="provideForm.announcementReceiptPaperForm.signerName" 
                            key="" 
                            maxlength="150" 
                            required="true" 
                            cssStyle="width:99%"
                            trim="true"/>
                    </td>
                </tr>
            </table>
        </sd:FieldSet>       
    </form>
</sd:TitlePane>     
<script>
    page.validate = function() {
        var today = new Date();
        var receiptNo = dijit.byId("provideForm.announcementReceiptPaperForm.receiptNo").getValue();
        if (receiptNo != null && receiptNo.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Số tiếp nhận]");
            dijit.byId("provideForm.announcementReceiptPaperForm.receiptNo").focus();
            return false;
        }
        var receiptDate = dijit.byId("provideForm.announcementReceiptPaperForm.receiptDate").getValue();
        if (receiptDate != null) {
            //add code if u need
            if (receiptDate < today) {
                alert("[Ngày ban hành] phải lớn hơn ngày hiện tại");
                dijit.byId("provideForm.announcementReceiptPaperForm.receiptDate").focus();
                return false;
            }
        } else {
            alert("Vui lòng nhập [Ngày ban hành]");
            dijit.byId("provideForm.announcementReceiptPaperForm.receiptDate").focus();
            return false;
        }
        var receiptDeptName = dijit.byId("provideForm.announcementReceiptPaperForm.receiptDeptName").getValue();
        if (receiptDeptName != null && receiptDeptName.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Tên cơ quan tiếp nhận công bố]");
            dijit.byId("provideForm.announcementReceiptPaperForm.receiptDeptName").focus();
            return false;
        }
        var businessName = dijit.byId("provideForm.announcementReceiptPaperForm.businessName").getValue();
        if (businessName != null && businessName.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Tên tổ chức cá nhân]");
            dijit.byId("provideForm.announcementReceiptPaperForm.businessName").focus();
            return false;
        }
        var telephone = dijit.byId("provideForm.announcementReceiptPaperForm.telephone").getValue();
        if (telephone != null && telephone.trim().length > 0 && validatePhone(telephone) == true) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Điện thoại] đúng định dạng");
            dijit.byId("provideForm.announcementReceiptPaperForm.telephone").focus();
            return false;
        }
        var fax = dijit.byId("provideForm.announcementReceiptPaperForm.fax").getValue();
        if (fax != null && fax.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Fax]");
            dijit.byId("provideForm.announcementReceiptPaperForm.fax").focus();
            return false;
        }
        var email = dijit.byId("provideForm.announcementReceiptPaperForm.email").getValue();
        if (email != null && email.trim().length > 0 && validateEmail(email) == true) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Email] đúng định dạng");
            dijit.byId("provideForm.announcementReceiptPaperForm.email").focus();
            return false;
        }
        var productName = dijit.byId("provideForm.announcementReceiptPaperForm.productName").getValue();
        if (productName != null && productName.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Tên sản phẩm]");
            dijit.byId("provideForm.announcementReceiptPaperForm.productName").focus();
            return false;
        }
        var manufactureName = dijit.byId("provideForm.announcementReceiptPaperForm.manufactureName").getValue();
        if (manufactureName != null && manufactureName.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Tên cơ sở sản xuất]");
            dijit.byId("provideForm.announcementReceiptPaperForm.manufactureName").focus();
            return false;
        }

        var nationName = dijit.byId("provideForm.announcementReceiptPaperForm.nationName").getValue();
        if (nationName != null && nationName != "-- Chọn --") {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Nước xuất xứ]");
            dijit.byId("provideForm.announcementReceiptPaperForm.nationName").focus();
            return false;
        }
        var matchingTarget = dijit.byId("provideForm.announcementReceiptPaperForm.matchingTarget").getValue();
        if (matchingTarget != null && matchingTarget.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Số hiệu qui chuẩn kĩ thuật]");
            dijit.byId("provideForm.announcementReceiptPaperForm.matchingTarget").focus();
            return false;
        }
        var effectiveDate = dijit.byId("provideForm.announcementReceiptPaperForm.effectiveDate").getValue();
        if (effectiveDate != null) {
            //add code if u need
            if (effectiveDate < today) {
                alert("[Thời gian hiệu lực của giấy tiếp nhận] phải lớn hơn ngày hiện tại");
                dijit.byId("provideForm.announcementReceiptPaperForm.effectiveDate").focus();
                return false;
            }
        } else {
            alert("Vui lòng nhập [Thời gian hiệu lực của giấy tiếp nhận]");
            dijit.byId("provideForm.announcementReceiptPaperForm.effectiveDate").focus();
            return false;
        }
        var signDate = dijit.byId("provideForm.announcementReceiptPaperForm.signDate").getValue();
        if (signDate != null) {
            //add code if u need            
            if (signDate < today) {
                alert("[Ngày ký] phải lớn hơn ngày hiện tại");
                dijit.byId("provideForm.announcementReceiptPaperForm.signDate").focus();
                return false;
            }
        } else {
            alert("Vui lòng nhập [Ngày ký]");
            dijit.byId("provideForm.announcementReceiptPaperForm.signDate").focus();
            return false;
        }
        var signerName = dijit.byId("provideForm.announcementReceiptPaperForm.signerName").getValue();
        if (signerName != null && signerName.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Người ký]");
            dijit.byId("provideForm.announcementReceiptPaperForm.signerName").focus();
            return false;
        }
        return true;
    };
    //action save into db
    page.save = function() {
        if (page.validate()) {
            sd.connector.post("announcementReceiptPaperAction!onProvide.do?" + token.getTokenParamString(), null, "provideForm", null, page.afterSave);
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
