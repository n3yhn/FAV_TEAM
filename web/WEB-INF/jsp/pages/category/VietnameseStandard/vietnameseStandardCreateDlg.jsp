<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<sx:ResultMessage id="resultMessage" />
<form id="createForm" name="createForm">
    <table width="100%;" cellpadding="0px" cellspacing="5px">
        <tr>
            <td width="25%"></td>
            <td width="25%"></td>
            <td width="25%"></td>
            <td width="25%"></td>
        </tr>
        <tr>
            <td>
                <sd:TextBox id="createForm.vietnameseStandardId" name="createForm.vietnameseStandardId" cssStyle="display:none" key=""/>                
                <sx:Label key="Số hiệu tiêu chuẩn:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.standardCode"
                            name="createForm.standardCode"
                            key=""
                            maxlength="20"
                            />
            </td>
            <td>
                <sx:Label key="Loại tiêu chuẩn:" require="true"/>
            </td>
            <td>
                <sd:SelectBox id="createForm.standardType" name="createForm.standardType" key="" data="lstStandardType" valueField="categoryId" labelField="name" />
            </td>

        </tr>
        <tr>
            <td>
                <sx:Label key="Tên tiếng Việt tiêu chuẩn:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.vietnameseName"
                            name="createForm.vietnameseName"
                            key=""
                            maxlength="100"
                            />
            </td>
            <td>
                <sx:Label key="Tên tiếng Anh tiêu chuẩn:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.englishName"
                            name="createForm.englishName"
                            key=""
                            maxlength="100"
                            />
            </td>
        </tr>
        <tr>
            <td>
                <sx:Label key="Nội dung tóm tắt:"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.summary"
                            name="createForm.summary"
                            key=""
                            maxlength="200"
                            />
            </td>
            <td>
                <sx:Label key="Đối tượng áp dụng:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%" trim="true"
                            id="createForm.applicationObject"
                            name="createForm.applicationObject"
                            key=""
                            maxlength="200"
                            />
            </td>
        </tr>
        <tr>
            <td>
                <sx:Label key="Đơn vị ban hành:" require="true"/>
            </td> 
            <td>
                <sd:TreePicker id="publishAgencyName" getChildrenNodeUrl="departmentAction!getDeptChildrenDataByNode.do"
                               getTopNodeUrl="departmentAction!getDeptData.do"  key="" rootLabel="root" cssStyle="width:100%" />
                <sd:TextBox cssStyle="display:none;" id="createForm.publishAgencyId" name="createForm.publishAgencyId" key=""/>
                <sd:TextBox cssStyle="display:none;" id="createForm.publishAgencyName" name="createForm.publishAgencyName" key=""/>                
            </td>
            <td>
                <sx:Label key="Ngày ban hành:" require="true"/>
            </td>
            <td>
                <sx:DatePicker cssStyle="width:100%"
                               id="createForm.publishDate"
                               key=""
                               name="createForm.publishDate" format="dd/MM/yyyy"/>
            </td>

        </tr>
        <tr>
            <td>
                <sx:Label key="Ngày hiệu lực ban hành:" require="true"/>
            </td>
            <td>
                <sx:DatePicker cssStyle="width:100%"
                               id="createForm.effectiveDate"
                               key=""
                               name="createForm.effectiveDate" format="dd/MM/yyyy"/>
            </td>
            <td>
                <sx:Label key="Ngày hết hạn:"/>
            </td>
            <td>
                <sx:DatePicker cssStyle="width:100%"
                               id="createForm.expireDate"
                               key=""
                               name="createForm.expireDate" format="dd/MM/yyyy"/>
            </td>

        </tr>
        <tr>
            <td>
                <sx:Label key="Phạm vi áp dụng:" require="true"/>
            </td>
            <td>
                <sd:TextBox cssStyle="width:100%"
                            id="createForm.scope"
                            name="createForm.scope"
                            key=""
                            maxlength="20"
                            />
            </td>
            <td>
                <sx:Label key="File đính kèm:" require="true"/>
            </td>
            <td>
                <sx:upload action="uploadiframe!uploadFile.do?id=createForm.upload" extension="*.pdf,*.doc,*.docx,*.zip,*.rar, *.docx, *.xls, *.xlsx" id="createForm.upload" maxSize="20"/>
                <sd:TextBox id="createForm.uploadId" name="createForm.uploadId" cssStyle="display:none" key=""/>
            </td>

        </tr>
        <tr>
            <td>
                <sx:Label key="Trạng thái công bố" require="true"/>
            </td>
            <td>
                <sd:SelectBox id="createForm.isActive" name="createForm.isActive" key="" >
                    <sd:Option value='-1'>-- Chọn --</sd:Option>
                    <sd:Option value='0' selected="true">Chưa công bố</sd:Option>
                    <sd:Option value='1'>Đã công bố</sd:Option>

                </sd:SelectBox>
            </td>
        </tr>
    </table>
    <div style="text-align: center">
        <sx:ButtonSave onclick="page.save();"></sx:ButtonSave>  
        <sx:ButtonClose onclick="page.close();"></sx:ButtonClose>  
    </div>
</form>

<script>

    page.validate = function() {
        var standardCode = dijit.byId("createForm.standardCode").getValue();
        if (standardCode == null || standardCode.trim().length == 0) {
            alert("Bạn chưa nhập mã tiêu chuẩn");
            dijit.byId("createForm.standardCode").focus();
            return false;
        }
        var vietnameseName = dijit.byId("createForm.vietnameseName").getValue();
        if (vietnameseName == null || vietnameseName.trim().length == 0) {
            alert("Bạn chưa nhập tên tiếng Việt tiêu chuẩn");
            dijit.byId("createForm.vietnameseName").focus();
            return false;
        }
        var englishName = dijit.byId("createForm.englishName").getValue();
        if (englishName == null || englishName.trim().length == 0) {
            alert("Bạn chưa nhập tên Tiếng Anh tiêu chuẩn");
            dijit.byId("createForm.englishName").focus();
            return false;
        }
        var applicationObject = dijit.byId("createForm.applicationObject").getValue();
        if (applicationObject == null || applicationObject.trim().length == 0) {
            alert("Bạn chưa nhập đối tượng áp dụng");
            dijit.byId("createForm.applicationObject").focus();
            return false;
        }
        var publishAgencyName = dijit.byId("publishAgencyName").getValue();
        if (publishAgencyName == null || publishAgencyName.trim().length == 0) {
            alert("Bạn chưa nhập đơn vị ban hành");
            dijit.byId("publishAgencyName").focus();
            return false;
        }

        var publishDate = dijit.byId("createForm.publishDate").getValue();
        if (publishDate == null) {
            alert("Bạn chưa nhập ngày ban hành");
            dijit.byId("createForm.publishDate").focus();
            return false;
        }
        var effectiveDate = dijit.byId("createForm.effectiveDate").getValue();
        if (effectiveDate == null) {
            alert("Bạn chưa nhập ngày hiệu lực thi hành");
            dijit.byId("createForm.effectiveDate").focus();
            return false;
        }


        var expireDate = dijit.byId("createForm.expireDate").getValue();
        if (expireDate == null) {
            alert("Bạn chưa nhập ngày hết hiệu lực");
            dijit.byId("createForm.expireDate").focus();
            return false;
        }

        if (publishDate > effectiveDate) {
            alert("Ngày ban hành lớn hơn Ngày hiệu lực.");
            dijit.byId("createForm.publishDate").focus();
            return false;
        }
        if (publishDate > expireDate) {
            alert("Ngày ban hành lớn hơn Hết hạn.");
            dijit.byId("createForm.publishDate").focus();
            return false;
        }
        if (effectiveDate > expireDate) {
            alert("Ngày hiệu lực lớn hơn Hết hạn.");
            dijit.byId("createForm.effectiveDate").focus();
            return false;
        }
        var scope = dijit.byId("createForm.scope").getValue();
        if (scope == null || scope.trim().length == 0) {
            alert("Bạn chưa nhập phạm vi tiêu chuẩn");
            dijit.byId("createForm.scope").focus();
            return false;
        }
        var pubAgency = dijit.byId("createForm.publishAgencyId").getValue();
        if (pubAgency == null || pubAgency.trim().length == 0 || pubAgency  == 0) {
            alert("Bạn chưa chọn đơn vị ban hành");
            dijit.byId("publishAgencyName").focus();
            return false;
        }
        var isActive = dijit.byId("createForm.isActive").getValue();
        if (isActive < 0) {
            alert("Trạng thái chưa chọn ");
            dijit.byId("createForm.isActive").focus();
            return false;
        }
        var attachIds = getListAttachId("createForm.upload");
        if(attachIds == null || attachIds.trim().length == 0){
            alert("Bạn chưa đính kèm file văn bản");
            return false;
        }else{
            dijit.byId("createForm.uploadId").setValue(attachIds);
        }
        return true;
    };
    page.save = function() {
        if (page.validate()) {
            sd.connector.post("vietnameseStandardAction!onInsert.do?" + token.getTokenParamString(), null, "createForm", null, page.afterSave);
        }
    };
    dijit.byId("publishAgencyName").onPickData = function(item) {
        try {
            if (item.id) {
                sd._("createForm.publishAgencyId").setValue(item.id);
                dijit.byId("createForm.publishAgencyName").setValue(dijit.byId("publishAgencyName").getValue());
            } else {
                sd._("createForm.publishAgencyId").setValue("");
                sd._("publishAgency").setValue("");
            }
        } catch (err) {
            alert(err.message);
        }
    };

    page.afterSave = function(data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        resultMessage_show("resultMessage", result[0], result[1], 5000);
        var result0 = result[0];
        if (result0 == "3") {
        } else {
            var Id = dijit.byId("createForm.vietnameseStandardId").getValue();
            if (Id == null || Id == "") {
                page.clearInsertForm();
            } else {
                page.close();
            }
            page.search();
            02
        }
    };

    page.close = function() {
        dlg.hide();
    };
</script>