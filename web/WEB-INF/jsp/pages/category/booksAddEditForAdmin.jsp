<%-- 
    Document   : bookAddEdit
    Created on : May 10, 2012, 9:47:53 AM
    Author     : sytv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<jsp:include page="../util/util_js.jsp"/>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sx:ResultMessage id="addEditResultMessage"/>
<sd:TitlePane key="dialog.titleAddEdit" id="booksAddEdit">
    <form id="bookFormAddEdit">
        <sd:TextBox cssStyle="display:none"
                    id="bookFormAddEdit.bookId"
                    key=""
                    name="bookFormAddEdit.bookId"
                    value="">
        </sd:TextBox>

        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <td width="20%"></td>
                <td width="30%"></td>
                <td width="20%"></td>
                <td width="30%"></td>
            </tr>
            <tr>
                <td align="right">
                    <sd:Label key="book.bookName"/><span style="color: red">*</span>
                </td>
                <td>
                    <sd:TextBox cssStyle="width:100% !important;"
                                id="bookFormAddEdit.bookName"
                                key=""
                                name="bookFormAddEdit.bookName"
                                maxlength="500"/>
                </td>
                <td align="right">
                    <sd:Label key="book.bookType"/><span style="color: red">*</span>
                </td>
                <td>
                    <sd:SelectBox cssStyle="width:100% !important;"
                                  id="bookFormAddEdit.bookType"
                                  key=""
                                  name="bookFormAddEdit.bookType"
                                  onchange="page.changeBookType(this);">
                        <sd:Option value="-1">-- Chọn --</sd:Option>
                        <sd:Option value="1"> Văn bản đến </sd:Option>
                        <sd:Option value="2">Văn bản đi</sd:Option>
                        <sd:Option value="3">Phiếu trình</sd:Option>
                        <sd:Option value="4">Tiếp nhận hồ sơ</sd:Option> <%-- Voffice BTP thì ẩn đi, voffice ATVSTP thì cho hiện option này ra--%>
                    </sd:SelectBox>
                </td>
            </tr>
            <tr id="trRecordsType" style="display: none">
                <td align="right">
                    <sd:Label key="book.recordsType"/><span style="color: red">*</span>
                </td>
                <td>
                    <sd:SelectBox cssStyle="width:100% !important;"
                                  id="bookFormAddEdit.recordsType"
                                  key=""
                                  name="bookFormAddEdit.recordsType">
                        <sd:Option value="1"> HS quảng cáo </sd:Option>
                        <sd:Option value="2">HS công bố</sd:Option>
                        <sd:Option value="3">HS cấp GCN ĐĐK</sd:Option>
                    </sd:SelectBox>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <sd:Label key="book.prefix"/><span style="color: red">*</span>
                </td>
                <td>
                    <sd:TextBox cssStyle="width:100% !important;"
                                id="bookFormAddEdit.prefix"
                                key=""
                                name="bookFormAddEdit.prefix"
                                maxlength="15"/>
                </td>
                <td align="right">
                    <sd:Label key="book.currentNumber"/><span style="color: red">*</span>
                </td>
                <td>
                    <sd:TextBox cssStyle="width:100% !important;"
                                id="bookFormAddEdit.currentNumber"
                                key="" mask="0123456789"
                                name="bookFormAddEdit.currentNumber" maxlength="10"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <sd:Label key="book.office"/><span style="color: red">*</span>
                </td>
                <td>
                    <sd:TreePicker id="deptTreeFrom" getChildrenNodeUrl="departmentAction!getDeptChildrenDataByNode.do"
                                   getTopNodeUrl="departmentAction!getMyDeptRootTree.do"  key="" rootLabel="root" cssStyle="width:100%" />
                    <sd:TextBox cssStyle="width:100% !important; display:none" id="bookFormAddEdit.officeId"  key=""  name="bookFormAddEdit.officeId" />
                    <sd:TextBox cssStyle="width:100% !important; display:none" id="bookFormAddEdit.officeName" key="" name="bookFormAddEdit.officeName" />
                </td>
                <!--                <td colspan="3">
                <sd:TextBox cssStyle="width:100% !important;"
                            id="bookFormAddEdit.officeName"
                            key=""
                            name="bookFormAddEdit.officeName" 
                            />
            </td>-->

                <%--
                <td>
                <sd:Button cssStyle="margin: 0 0.2em!important;"
                           id="officeSelectAddEdit" key="" onclick="page.officeSelectAddEdit();">
                    <img src="share/images/icons/comment.png" height="10" width="10">
                </sd:Button>    

                    <sd:Button cssStyle="margin: 0 0.2em!important;"
                               id="officeRemoveAddEdit" key="" onclick="page.officeRemoveAddEdit();">
                        <img src="share/images/icons/13.png" height="10" width="10">
                    </sd:Button>
                    </td>
                --%>

            </tr>
            <tr style="display: none">
                <td align="right">
                    <sd:Label key="book.isDefault"/><span style="color: red">*</span>
                </td>
                <td>
                    <sd:SelectBox cssStyle="width:100% !important;"
                                  id="bookFormAddEdit.isDefault"
                                  key=""
                                  name="bookFormAddEdit.isDefault">
                        <sd:Option value="-1">-- Chọn --</sd:Option>
                        <sd:Option value="0" > Sổ thường </sd:Option>
                        <sd:Option value="1" selected="true">Sổ mặc định</sd:Option>
                    </sd:SelectBox>
                </td>
                <td align="right">
                    <sd:Label key="book.isPublic"/><span style="color: red">*</span>
                </td>
                <td>
                    <sd:SelectBox cssStyle="width:100% !important;"
                                  id="bookFormAddEdit.isPublic"
                                  key=""
                                  name="bookFormAddEdit.isPublic">
                        <sd:Option value="-1">-- Chọn --</sd:Option>
                        <sd:Option value="0">Sổ cá nhân </sd:Option>
                        <sd:Option value="1">Sổ chung</sd:Option>
                    </sd:SelectBox>
                </td>
            </tr>
            <tr style="display: none">
                <td align="right">
                    <sd:Label key="book.followBy"/><span>&nbsp;</span>
                </td>
                <td>
                    <sd:SelectBox cssStyle="width:100% !important;"
                                  id="bookFormAddEdit.followBy"
                                  key=""
                                  name="bookFormAddEdit.followBy">
                        <sd:Option value="-1">-- Chọn --</sd:Option>
                        <sd:Option value="0">Không </sd:Option>
                        <sd:Option value="2">Đơn vị</sd:Option>
                        <sd:Option value="3">Loại văn bản</sd:Option>
                    </sd:SelectBox>
                </td>
                <td align="right">
                    <sd:Label key="book.orderNumber"/><span>&nbsp;</span>
                </td>
                <td>
                    <sd:TextBox cssStyle="width:100% !important;"
                                id="bookFormAddEdit.orderNumber"
                                key="" mask="digit"
                                name="bookFormAddEdit.orderNumber" maxlength="3"/>
                </td>
            </tr>
            <tr style="text-align: center">
                <td colspan="4">
                    <sx:ButtonSave onclick="page.save()" />
                    <sx:ButtonClose onclick="page.close()" />
                </td>
            </tr>

        </table>
    </form>
</sd:TitlePane>
<script>
    var bookId=dijit.byId("bookFormAddEdit.bookId");
    var bookName=dijit.byId("bookFormAddEdit.bookName");
    var bookType=dijit.byId("bookFormAddEdit.bookType");
    var prefix=dijit.byId("bookFormAddEdit.prefix");
    var currentNumber=dijit.byId("bookFormAddEdit.currentNumber");
    var officeName=dijit.byId("bookFormAddEdit.officeName");
//    var isDefault=dijit.byId("bookFormAddEdit.isDefault");
//    var isPublic=dijit.byId("bookFormAddEdit.isPublic");
//    var followBy=dijit.byId("bookFormAddEdit.followBy");
//    var orderNumber=dijit.byId("bookFormAddEdit.orderNumber");
    
    dijit.byId("deptTreeFrom").onPickData = function(item){
        try{
            if(item.id){
                sd._("bookFormAddEdit.officeId").setValue(item.id);
                sd._("bookFormAddEdit.officeName").setValue(item.name);
            } else {
                sd._("bookFormAddEdit.officeId").setValue("");
                sd._("bookFormAddEdit.officeName").setValue("");
                sd._("deptTreeFrom").setValue("");
            }
        }catch(err){
            alert(err.message);
        }
    }
    
    page.changeBookType = function(){
        if(bookType.getValue().toString() == "4"){//ho so dang ky
            document.getElementById("trRecordsType").style.display = "";
        } else {
            document.getElementById("trRecordsType").style.display = "none";
        }
    }
    
    page.save = function() {
        
        if (bookName.getValue().toString() == "") {
            bookName.focus();
            msg.alert("Tên sổ không được để trống",'<sd:Property>confirm.title</sd:Property>');
            return false;
        }
        if (bookType.getValue().toString() == "-1") {
            msg.alert("Loại sổ không được để trống",'<sd:Property>confirm.title</sd:Property>');
            return false;
        }
        if(bookType.getValue().toString() == "4"){//ho so dang ky
            var recordsType = dijit.byId("bookFormAddEdit.recordsType").getValue();
            if(recordsType.toString() == "-1"){
                msg.alert("Loại hồ sơ không được để trống",'<sd:Property>confirm.title</sd:Property>');
                return false;
            }
        }
        if (prefix.getValue().toString() == "") {
            prefix.focus();
            msg.alert("Tiền tố không được để trống",'<sd:Property>confirm.title</sd:Property>');
            return false;
        }
        if (currentNumber.getValue().toString() == "") {
            currentNumber.focus();
            msg.alert("Số hiện tại không được để trống",'<sd:Property>confirm.title</sd:Property>');
            return false;
        } 
        if (officeName.getValue().toString() == "") {
            msg.alert("Đơn vị không được để trống",'<sd:Property>confirm.title</sd:Property>');
            return false;
        }
//        if (isDefault.getValue().toString() == "-1") {
//            msg.alert("Sổ mặc định không được để trống",'<sd:Property>confirm.title</sd:Property>');
//            return false;
//        }
//        if (isPublic.getValue().toString() == "-1") {
//            msg.alert("Sổ công bố không được để trống",'<sd:Property>confirm.title</sd:Property>');
//            return false;
//        }
        sd.connector.post("voBooks!checkBook.do", null, "bookFormAddEdit", null, page.afterCheckCategory);
        
    }

    page.afterCheckCategory = function(data) {
        var obj = dojo.fromJson(data);
        var result = null;
        result=obj.items;
        //var customInfo = obj.customInfo;

        var result0 = result[0];
        if (result0 == "3") {
            //showMessage(result[0], result[1]);
            showResultMessage("addEditResultMessage", result[0], result[1]);
        } else {
            sd.connector.post("voBooks!insertBook.do?" + token.getTokenParamString(), null, "bookFormAddEdit", null, page.afterInsertCategory);
        } 
    }

    page.afterInsertCategory = function(data) {
        var obj = dojo.fromJson(data);
        var result=null;
        result = obj.items;
        var dept = '${fn:escapeXml(dept)}';
        grid.vtReload('voBooks!search.do?dept='+dept , "bookForm",null,null);
        
        if (insertDialog) {
            resultMessage_show("addEditResultMessage", result[0], result[1], 5000);
            dijit.byId("bookFormAddEdit.bookId").setValue("");
            dijit.byId("bookFormAddEdit.bookName").setValue("");
            dijit.byId("bookFormAddEdit.bookType").setValue("-1");
            dijit.byId("bookFormAddEdit.prefix").setValue("");
            dijit.byId("bookFormAddEdit.currentNumber").setValue("");
//            dijit.byId("bookFormAddEdit.isDefault").setValue("-1");
//            dijit.byId("bookFormAddEdit.isPublic").setValue("-1");
//            dijit.byId("bookFormAddEdit.followBy").setValue("-1");
//            dijit.byId("bookFormAddEdit.orderNumber").setValue("");
            dijit.byId("bookFormAddEdit.officeName").setValue("");
            dijit.byId("bookFormAddEdit.officeId").setValue("");
            sd._("deptTreeFrom").setValue("");
        } else {
            page.close();
            resultMessage_show("deletedResultMessage", result[0], result[1], 5000);
        }        
    }

    page.close = function() {
        dialog.hide();
        dijit.byId("bookFormAddEdit.bookId").setValue("");
        dijit.byId("bookFormAddEdit.bookName").setValue("");
        dijit.byId("bookFormAddEdit.bookType").setValue("-1");
        dijit.byId("bookFormAddEdit.prefix").setValue("");
        dijit.byId("bookFormAddEdit.currentNumber").setValue("");
        dijit.byId("bookFormAddEdit.officeName").setValue("");
        dijit.byId("bookFormAddEdit.officeId").setValue("");
        sd._("deptTreeFrom").setValue("");
//        dijit.byId("bookFormAddEdit.isDefault").setValue("-1");
//        dijit.byId("bookFormAddEdit.isPublic").setValue("-1");
//        dijit.byId("bookFormAddEdit.followBy").setValue("-1");
//        dijit.byId("bookFormAddEdit.orderNumber").setValue("");
 
    }
</script>