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
<jsp:include page="../../util/util_js.jsp"/>
<jsp:include page="../../common/commonJavascript.jsp"/>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<div id="token">
    <s:token id="tokenId"/>
</div>
<form id="createForm" name="createForm">
    <sx:ResultMessage id="resultMessage" />
    <div class="buttonDiv">
        <sx:ButtonSave onclick="page.save();"></sx:ButtonSave>  
        <sx:ButtonClose onclick="page.close();"></sx:ButtonClose> 
        </div>

    <sd:TitlePane key="Nhập thông tin giấy tiếp nhận hồ sơ" id="TitleTitle">

        <sd:FieldSet key="Thông tin" id="tab.userRep">
            <table width="100%" class="editTable">
                <tr>
                    <td width="15%">
                        <sd:Label required="true">Số giấy tiếp nhận:</sd:Label><font style="color:red">*</font></td>
                        <td width="35%">
                        <sd:TextBox 
                            id="createForm.receiptNo" 
                            name="createForm.receiptNo" 
                            key="" 
                            maxlength="50" 
                            cssStyle="width:99%"
                            trim="true" htmlAttributes="title='- Đối với Giấy tiếp nhận do Cục ATTP cấp, Số giấy tiếp nhận được nhập theo định dạng: (số thứ tự)/(năm cấp)/ATTP-TNCB
- Đối với Giấy tiếp nhận do Chi cục ATVSTP tỉnh/thành phố cấp, nhập theo định dạng: (số thứ tự)/(năm cấp)/YT+tên viết tắt tỉnh/thành phố-TNCB'"/>
                        <sd:TextBox 
                            id="createForm.announcementReceiptPaperId" 
                            name="createForm.announcementReceiptPaperId" 
                            cssStyle="display:none" 
                            key=""
                            trim="true"/>
                        <sd:TextBox 
                            id="createForm.isActive" 
                            name="createForm.isActive" 
                            cssStyle="display:none" 
                            key=""
                            trim="true"/>
                    </td>
                    <td width="15%"><sd:Label required="true">Ngày ban hành:</sd:Label><font style="color:red">*</font></td>
                        <td width="35%">
                        <sx:DatePicker cssStyle="width:99%"
                                       id="createForm.receiptDate"
                                       key=""
                                       name="createForm.receiptDate" format="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label required="true">Tên cơ quan tiếp nhận công bố:</sd:Label><font style="color:red">*</font></td>
                        <td width="35%">
                        <sd:TextBox 
                            id="createForm.receiptDeptName" 
                            name="createForm.receiptDeptName" 
                            key="" 
                            maxlength="250" 
                            cssStyle="width:99%"
                            trim="true"/>
                    </td>
                    <td><sd:Label required="true">Tên tổ chức, cá nhân:</sd:Label><font style="color:red">*</font></td>
                        <td width="35%">
                        <sd:TextBox 
                            id="createForm.businessName" 
                            name="createForm.businessName" 
                            key="" 
                            maxlength="250" 
                            cssStyle="width:99%"
                            trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label required="true">Điện thoại:</sd:Label><font style="color:red">*</font></td>
                        <td width="35%">
                        <sd:TextBox 
                            id="createForm.telephone" 
                            name="createForm.telephone" 
                            key="" 
                            maxlength="20" 
                            cssStyle="width:99%"
                            trim="true"/>
                    </td>
                    <td><sd:Label required="true">Fax:</sd:Label><font style="color:red">*</font></td>
                        <td width="35%">
                        <sd:TextBox 
                            id="createForm.fax" 
                            name="createForm.fax" 
                            key="" 
                            maxlength="20" 
                            cssStyle="width:99%"
                            trim="true"/>
                    </td>
                </tr>
                <tr>
                    <td><sd:Label required="true">Email:</sd:Label><font style="color:red">*</font></td>
                        <td>
                        <sd:TextBox 
                            id="createForm.email" 
                            name="createForm.email" 
                            key="" 
                            maxlength="100" 
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
                            id="createForm.productName" 
                            name="createForm.productName" 
                            key="" 
                            maxlength="250" 
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
                            id="createForm.manufactureName" 
                            name="createForm.manufactureName" 
                            key="" 
                            maxlength="250" 
                            cssStyle="width:99%"
                            trim="true"/>
                    </td>
                    <td><sd:Label required="true">Nước xuất xứ:</sd:Label><font style="color:red">*</font></td>
                        <td>
                        <sd:SelectBox id="createForm.nationName" name="createForm.nationName" key="" data="lstNation" valueField="name" labelField="name" />
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
                            id="createForm.matchingTarget" 
                            name="createForm.matchingTarget" 
                            key="" 
                            maxlength="50" 
                            cssStyle="width:99%"
                            trim="true"/>
                    </td>
                    <td width="15%"><sd:Label required="true">Thời gian hiệu lực của giấy tiếp nhận:</sd:Label><font style="color:red">*</font></td>
                        <td width="35%">
                        <sx:DatePicker cssStyle="width:99%"
                                       id="createForm.effectiveDate"
                                       key=""
                                       name="createForm.effectiveDate" format="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <sd:Label required="true">Ngày ký:</sd:Label><font style="color:red">*</font></td>
                        <td>
                        <sx:DatePicker cssStyle="width:99%"
                                       id="createForm.signDate"
                                       key=""
                                       name="createForm.signDate" format="dd/MM/yyyy"/>
                    </td>
                    <td><sd:Label required="true">Người ký:</sd:Label><font style="color:red">*</font></td>
                        <td>
                        <sd:TextBox 
                            id="createForm.signerName" 
                            name="createForm.signerName" 
                            key="" 
                            maxlength="150" 
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
        var receiptNo = dijit.byId("createForm.receiptNo").getValue();
        if (receiptNo != null && receiptNo.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Số tiếp nhận]");
            dijit.byId("createForm.receiptNo").focus();
            return false;
        }
        var receiptDate = dijit.byId("createForm.receiptDate").getValue();
        if (receiptDate != null) {
            //add code if u need
//            if (receiptDate < today) {
//                alert("[Ngày ban hành] nhỏ hơn ngày hiện tại");
//                dijit.byId("createForm.receiptDate").focus();
//                return false;
//            }
        } else {
            alert("Vui lòng nhập [Ngày ban hành]");
            dijit.byId("createForm.receiptDate").focus();
            return false;
        }
        var receiptDeptName = dijit.byId("createForm.receiptDeptName").getValue();
        if (receiptDeptName != null && receiptDeptName.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Tên cơ quan tiếp nhận công bố]");
            dijit.byId("createForm.receiptDeptName").focus();
            return false;
        }
        var businessName = dijit.byId("createForm.businessName").getValue();
        if (businessName != null && businessName.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Tên tổ chức cá nhân]");
            dijit.byId("createForm.businessName").focus();
            return false;
        }
        var telephone = dijit.byId("createForm.telephone").getValue();
        if (telephone != null && telephone.trim().length > 0 && validatePhone(telephone) == true) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Điện thoại] đúng định dạng");
            dijit.byId("createForm.telephone").focus();
            return false;
        }
        var fax = dijit.byId("createForm.fax").getValue();
        if (fax != null && fax.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Fax]");
            dijit.byId("createForm.fax").focus();
            return false;
        }
        var email = dijit.byId("createForm.email").getValue();
        if (email != null && email.trim().length > 0 && validateEmail(email) == true) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Email] đúng định dạng");
            dijit.byId("createForm.email").focus();
            return false;
        }
        var productName = dijit.byId("createForm.productName").getValue();
        if (productName != null && productName.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Tên sản phẩm]");
            dijit.byId("createForm.productName").focus();
            return false;
        }
        var manufactureName = dijit.byId("createForm.manufactureName").getValue();
        if (manufactureName != null && manufactureName.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Tên cơ sở sản xuất]");
            dijit.byId("createForm.manufactureName").focus();
            return false;
        }

        var nationName = dijit.byId("createForm.nationName").getValue();
        if (nationName != null && nationName != "-- Chọn --") {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Nước xuất xứ]");
            dijit.byId("createForm.nationName").focus();
            return false;
        }
        var matchingTarget = dijit.byId("createForm.matchingTarget").getValue();
        if (matchingTarget != null && matchingTarget.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Số hiệu qui chuẩn kĩ thuật]");
            dijit.byId("createForm.matchingTarget").focus();
            return false;
        }
        var effectiveDate = dijit.byId("createForm.effectiveDate").getValue();
        if (effectiveDate != null) {
//            if (effectiveDate < today) {
//                alert("[Thời gian hiệu lực của giấy tiếp nhận] nhỏ hơn ngày hiện tại");
//                dijit.byId("createForm.effectiveDate").focus();
//                return false;
//            }
        } else {
            alert("Vui lòng nhập [Thời gian hiệu lực của giấy tiếp nhận]");
            dijit.byId("createForm.effectiveDate").focus();
            return false;
        }
        var signDate = dijit.byId("createForm.signDate").getValue();
        if (signDate != null) {
            //add code if u need
//            if (signDate < today) {
//                alert("[Ngày ký] nhỏ hơn ngày hiện tại");
//                dijit.byId("createForm.signDate").focus();
//                return false;
//            }
        } else {
            alert("Vui lòng nhập [Ngày ký]");
            dijit.byId("createForm.signDate").focus();
            return false;
        }
        var signerName = dijit.byId("createForm.signerName").getValue();
        if (signerName != null && signerName.trim().length > 0) {
            //add code if u need
        } else {
            alert("Vui lòng nhập [Người ký]");
            dijit.byId("createForm.signerName").focus();
            return false;
        }
        return true;
    };
    //action save into db
    page.save = function() {
        if (page.validate()) {
            sd.connector.post("announcementReceiptPaperAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSave);
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
            var Id = dijit.byId("createForm.announcementReceiptPaperId").getValue();
            if (Id == null || Id == "") {
                page.clearInsertForm();
            } else {
                page.close();
            }
            page.search();
        }
    };

</script>
