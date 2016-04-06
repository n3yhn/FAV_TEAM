<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    request.setAttribute("contextPath", request.getContextPath());
%>
<style>
    .editable p {
        margin-top: 0px;
    }
</style>
<script type="text/javascript">
    var lstUnit = [];
    var ob;
    <c:forEach items="${lstUnit}" var="item">
    ob = {};
    ob.categoryId = ${fn:escapeXml(item.categoryId)};
    ob.name = "${fn:escapeXml(item.name)}";
    lstUnit.push(ob);
    </c:forEach>

    tinymce.remove();

    loadEditor = function (target) {
        if (!target)
            target = "div.editable";
        tinymce.init({
            //selector: "textarea",
            selector: target,
            plugins: [
                "advlist autolink lists link image charmap print preview anchor",
                //"searchreplace visualblocks code fullscreen contextmenu",
                "insertdatetime media table paste"
            ],
            menubar: false,
            inline: true,
            toolbar: "subscript | superscript | charmap",
            statusbar: false,
            init_instance_callback: "afterInit"

        });
    }

    afterInit = function () {
        console.log('init tinymce succesfull');
    }


    createSelectElement = function (id, name, value) {
        var select = document.createElement("select");
        select.setAttribute("id", id);
        select.setAttribute("name", name);
        select.setAttribute("style", "width:100%");
        if (value != null) {
            select.setAttribute("value", value);
        }

        for (var i = 0; i < lstUnit.length; i++) {
            var option = document.createElement("option");
            var unit = lstUnit[i];
            option.setAttribute("value", unit.categoryId);
            option.innerHTML = unit.name;
            if (value == unit.categoryId) {
                option.setAttribute("selected", "true");
            }
            select.appendChild(option);
        }
        return select;
    };

    getSelectElementName = function (value) {
        var select = "";
        for (var i = 0; i < lstUnit.length; i++) {
            var unit = lstUnit[i];
            if (unit.categoryId.toString() == value.toString()) {
                select = unit.name;
                break;
            }
        }
        return select;
    };


    createNewMainlyTargetRow = function (mainlyTargetId, targetName, unitId, publishLevel, meetLevel) {

        if (mainlyTargetId == null) {
            mainlyTargetId = "";
        }
        if (targetName == null) {
            targetName = "";
        }
        if (publishLevel == null) {
            publishLevel = "";
        }
        if (meetLevel == null) {
            meetLevel = "";
        }
        //            targetName = escapeHtml_(targetName);
        //            publishLevel = escapeHtml_(publishLevel);
        //            meetLevel = escapeHtml_(meetLevel);

        var size = getRowLengthOfTable("mainlyTargetTbl") - 2;
        var preName = "createForm.lstMainlyTarget[" + size + "].";
        var divPreName = "createForm.divLstMainlyTarget[" + size + "].";
        var preNameToReload = '#createForm\\.divLstMainlyTarget\\[' + size + '\\]\\.';
        var row = document.createElement("tr");
        var tdIndex = document.createElement("td");
        tdIndex.setAttribute("align", "center");
        tdIndex.innerHTML = size + 1;
        var tdTargetName = document.createElement("td");
        {
            var divTargetName = document.createElement("div");
            //inpTargetName.setAttribute("type", "text");
            divTargetName.setAttribute("name", divPreName + "targetName");
            divTargetName.setAttribute("id", divPreName + "targetName");
            divTargetName.innerHTML = targetName;
            console.log(targetName);
            divTargetName.setAttribute("class", "editable");
            divTargetName.setAttribute("style", "width:100%;border:1px solid gray;height:20px;overflow:hidden");
            //inpTargetName.setAttribute("maxlength", "250");
            //210214 bintnt53
            //inpTargetName.setAttribute("onfocus", "onItemFocus(this);");
            //!binhnt53
            tdTargetName.appendChild(divTargetName);

            var inpTargetName = document.createElement("input");
            inpTargetName.setAttribute("type", "hidden");
            inpTargetName.setAttribute("name", preName + "targetName");
            inpTargetName.setAttribute("id", preName + "targetName");
            inpTargetName.setAttribute("value", targetName);
            inpTargetName.setAttribute("maxlength", "250");
            tdTargetName.appendChild(inpTargetName);

            var inpTargetId = document.createElement("input");
            inpTargetId.setAttribute("type", "hidden");
            inpTargetId.setAttribute("name", preName + "mainlyTargetId");
            inpTargetId.setAttribute("id", preName + "mainlyTargetId");
            inpTargetId.setAttribute("value", mainlyTargetId);
            tdTargetName.appendChild(inpTargetId);
        }
        var tdUnit = document.createElement("td");
        {
            var selectUnit = createSelectElement(preName + "unitId", preName + "unitId", unitId);
            tdUnit.appendChild(selectUnit);
            var inpUnitName = document.createElement("input");
            inpUnitName.setAttribute("type", "hidden");
            inpUnitName.setAttribute("name", preName + "unitName");
            inpUnitName.setAttribute("id", preName + "unitName");
            tdUnit.appendChild(inpUnitName);
        }
        var tdPublishLevel = document.createElement("td");
        {
            var divPublishLevel = document.createElement("div");
            divPublishLevel.setAttribute("name", divPreName + "publishLevel");
            divPublishLevel.setAttribute("id", divPreName + "publishLevel");
            divPublishLevel.innerHTML = publishLevel;
            divPublishLevel.setAttribute("class", "editable");
            divPublishLevel.setAttribute("style", "width:100%;overflow:hidden;border:1px solid gray;height:20px");
            tdPublishLevel.appendChild(divPublishLevel);

            var inpPublishLevel = document.createElement("input");
            inpPublishLevel.setAttribute("type", "hidden");
            inpPublishLevel.setAttribute("name", preName + "publishLevel");
            inpPublishLevel.setAttribute("id", preName + "publishLevel");
            inpPublishLevel.setAttribute("value", publishLevel);
            inpPublishLevel.setAttribute("style", "width:100%");
            inpPublishLevel.setAttribute("maxlength", "250");
            //210214 bintnt53
            inpPublishLevel.setAttribute("onfocus", "onItemFocus(this);");
            //!binhnt53
            tdPublishLevel.appendChild(inpPublishLevel);
        }

        var tdMeetLevel = document.createElement("td");
        {
            var divMeetLevel = document.createElement("div");
            divMeetLevel.setAttribute("name", divPreName + "meetLevel");
            divMeetLevel.setAttribute("id", divPreName + "meetLevel");
            divMeetLevel.innerHTML = meetLevel;
            divMeetLevel.setAttribute("class", "editable");
            divMeetLevel.setAttribute("style", "width:100%;overflow:hidden;border:1px solid gray;height:20px");
            tdMeetLevel.appendChild(divMeetLevel);

            var inpMeetLevel = document.createElement("input");
            inpMeetLevel.setAttribute("type", "hidden");
            inpMeetLevel.setAttribute("name", preName + "meetLevel");
            inpMeetLevel.setAttribute("id", preName + "meetLevel");
            inpMeetLevel.setAttribute("value", meetLevel);
            inpMeetLevel.setAttribute("style", "width:100%");
            inpMeetLevel.setAttribute("maxlength", "2000");
            inpMeetLevel.setAttribute("onfocus", "onItemFocus(this);");
            //!binhnt53
            tdMeetLevel.appendChild(inpMeetLevel);
        }

        var tdDelete = document.createElement("td");
        {
            var img = document.createElement("img");
            img.src = 'share/images/icons/13.png';
            img.setAttribute("width", '20px');
            img.setAttribute("height", '20px');
            img.setAttribute("title", 'Xóa chỉ tiêu');
            img.setAttribute("onclick", "page.deleteMainlyTarget(this.parentNode.parentNode);");
            tdDelete.appendChild(img);
        }

        row.appendChild(tdIndex);
        row.appendChild(tdTargetName);
        row.appendChild(tdUnit);
        row.appendChild(tdPublishLevel);
        row.appendChild(tdMeetLevel);

        row.appendChild(tdDelete);
        document.getElementById("mainlyTargetTbl").appendChild(row);
        document.getElementById(preName + "meetLevel").defaultValue = meetLevel;
        loadEditor(preNameToReload + 'targetName');
        loadEditor(preNameToReload + 'publishLevel');
        loadEditor(preNameToReload + 'meetLevel');

    };

    createNewRowOfTarget = function (productTargetId, targetName, unitId, maxLevel, targetType, tableId) {
        if (productTargetId == null) {
            productTargetId = "";
        }
        if (targetName == null) {
            targetName = "";
        }
        if (maxLevel == null) {
            maxLevel = "";
        }
        if (targetType == null) {
            targetType = "";
        }

        //            targetName = escapeHtml_(targetName);
        //            maxLevel = escapeHtml_(maxLevel);
        //            targetType = escapeHtml_(targetType);

        tableId = "biologicTargetTbl";
        if (targetType == 3) {
            tableId = "chemicalTargetTbl";
        } else if (targetType == 2) {
            tableId = "heavyMetalTargetTbl";
        }
        var size = getRowLengthOfTable(tableId) - 2;
        var preName = "createForm.lstBioTarget[" + size + "].";
        var preDivName = "createForm.lstDivBioTarget[" + size + "].";
        var preDivLoadName = "#createForm\\.lstDivBioTarget\\[" + size + "\\]\\.";
        if (targetType == 3) {
            preName = "createForm.lstChemical[" + size + "].";
            preDivName = "createForm.lstDivChemical[" + size + "].";
            preDivLoadName = "#createForm\\.lstDivChemical\\[" + size + "\\]\\.";
            tableId = "chemicalTargetTbl";
        } else if (targetType == 2) {
            preName = "createForm.lstHeavyMetal[" + size + "].";
            preDivName = "createForm.lstDivHeavyMetal[" + size + "].";
            preDivLoadName = "#createForm\\.lstDivHeavyMetal\\[" + size + "\\]\\.";
            tableId = "heavyMetalTargetTbl";
        }

        var row = document.createElement("tr");
        var tdIndex = document.createElement("td");
        tdIndex.setAttribute("align", "center");
        tdIndex.innerHTML = size + 1;
        var tdTargetName = document.createElement("td");
        {
            var divTargetName = document.createElement("div");
            divTargetName.setAttribute("name", preDivName + "targetName");
            divTargetName.setAttribute("id", preDivName + "targetName");
            divTargetName.setAttribute("class", "editable");
            divTargetName.setAttribute("style", "width:100%;overflow:hidden;border:1px solid gray;height:20px");
            divTargetName.innerHTML = targetName;
            tdTargetName.appendChild(divTargetName);

            var inpTargetName = document.createElement("input");
            inpTargetName.setAttribute("type", "hidden");
            inpTargetName.setAttribute("name", preName + "targetName");
            inpTargetName.setAttribute("id", preName + "targetName");
            inpTargetName.setAttribute("style", "width:100%");
            inpTargetName.setAttribute("value", targetName);
            inpTargetName.setAttribute("maxlength", "250");
            //210214 bintnt53
            inpTargetName.setAttribute("onfocus", "onItemFocus(this);");
            //!binhnt53
            tdTargetName.appendChild(inpTargetName);

            var inpTargetId = document.createElement("input");
            inpTargetId.setAttribute("type", "hidden");
            inpTargetId.setAttribute("name", preName + "productTargetId");
            inpTargetId.setAttribute("id", preName + "productTargetId");
            inpTargetId.setAttribute("value", productTargetId);
            tdTargetName.appendChild(inpTargetId);

            var inpTargetType = document.createElement("input");
            inpTargetType.setAttribute("type", "hidden");
            inpTargetType.setAttribute("name", preName + "targetType");
            inpTargetType.setAttribute("id", preName + "targetType");
            inpTargetType.setAttribute("value", targetType);
            tdTargetName.appendChild(inpTargetType);
        }
        var tdUnit = document.createElement("td");
        {
            var selectUnit = createSelectElement(preName + "unitId", preName + "unitId", unitId);
            tdUnit.appendChild(selectUnit);

            var inpUnitName = document.createElement("input");
            inpUnitName.setAttribute("type", "hidden");
            inpUnitName.setAttribute("name", preName + "unitName");
            inpUnitName.setAttribute("id", preName + "unitName");
            tdUnit.appendChild(inpUnitName);
        }

        var tdMeetLevel = document.createElement("td");
        var divMaxlevel;
        {
            divMaxlevel = document.createElement("div");
            divMaxlevel.setAttribute("name", preDivName + "maxLevel");
            divMaxlevel.setAttribute("id", preDivName + "maxLevel");
            divMaxlevel.setAttribute("class", "editable");
            divMaxlevel.setAttribute("style", "width:100%;overflow:hidden;border:1px solid gray;height:20px");
            divMaxlevel.innerHTML = maxLevel;
            tdMeetLevel.appendChild(divMaxlevel);

            var inpMeetLevel = document.createElement("input");
            inpMeetLevel.setAttribute("type", "hidden");
            inpMeetLevel.setAttribute("name", preName + "maxLevel");
            inpMeetLevel.setAttribute("id", preName + "maxLevel");
            inpMeetLevel.setAttribute("value", maxLevel);
            inpMeetLevel.setAttribute("style", "width:100%");
            inpMeetLevel.setAttribute("maxlength", "250");
            //210214 bintnt53
            inpMeetLevel.setAttribute("onfocus", "onItemFocus(this);");
            //!binhnt53
            tdMeetLevel.appendChild(inpMeetLevel);
        }

        var tdDelete = document.createElement("td");
        {
            var img = document.createElement("img");
            img.src = 'share/images/icons/13.png';
            img.setAttribute("width", '20px');
            img.setAttribute("height", '20px');
            img.setAttribute("title", 'Xóa chỉ tiêu');
            img.setAttribute("onclick", "page.deleteProductTarget(this.parentNode.parentNode,'" + targetType + "','" + tableId + "')");
            tdDelete.appendChild(img);
        }
        row.appendChild(tdIndex);
        row.appendChild(tdTargetName);
        row.appendChild(tdUnit);
        row.appendChild(tdMeetLevel);
        row.appendChild(tdDelete);

        document.getElementById(tableId).appendChild(row);
        loadEditor(preDivLoadName + 'targetName');
        loadEditor(preDivLoadName + 'maxLevel');

    };

    createNewTarget = function (tableId, targetType) {
        createNewRowOfTarget('', '', null, '', targetType, tableId);
    };


</script>

<table class="editTable">
    <tr style="background: #f0efef">
        <td colspan="4" style="color: #15428b;font-weight: bold;padding-left: 10px; height: 25px">
            Thông tin chung
            <sd:TextBox key="" 
                        id="createForm.detailProduct.detailProductId" 
                        name="createForm.detailProduct.detailProductId"
                        cssStyle="display:none"/>
        </td>
    </tr>
    <tr>
        <td width="25%"><sd:Label>Cơ quan chủ quản</sd:Label></td>
            <td width="25%">
                <div id="createForm.detailProduct.agencyName" style="width:98%">${fn:escapeXml(agencyName)}</div>
        </td>
        <td width="25%"><sd:Label>Số bản công bố</sd:Label></td>
            <td width="25%">
                <div  id="createForm.detailProduct.announcementNo" style="width:98%"></div>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Tên tổ chức cá nhân</sd:Label></td>
            <td width="25%">
                <div id="createForm.detailProduct.businessName" style="width:98%"></div>
            </td>
            <td width="25%"><sd:Label>Tên sản phẩm</sd:Label></td>
            <td width="25%">
                <div id="createForm.detailProduct.productName" style="width:98%"></div>
            </td>
        </tr>
        <tr>
            <td width="25%"><sd:Label>Nhóm sản phẩm</sd:Label></td>
            <td width="25%">
            <sd:SelectBox key="" id="createForm.detailProduct.productType" name="createForm.detailProduct.productType" cssStyle="width:98%"
                          data="lstProductType" valueField="categoryId" labelField="name" value="${fn:escapeXml(createForm.detailProduct.productType)}" onchange="page.showFee()"/>
            <sd:TextBox key="" id="createForm.detailProduct.productTypeName" name="createForm.detailProduct.productTypeName" cssStyle="display:none" trim="true" maxlength="255"/>
        </td>
        <td width="25%" style="display: "><sd:Label>Số</sd:Label></td>
            <td width="25%" style="display: ">
            <sd:TextBox key="" 
                        id="createForm.detailProduct.productNo" 
                        name="createForm.detailProduct.productNo" 
                        maxlength="255" 
                        cssStyle="width:98%"
                        trim="true"/>
        </td>
    </tr>
    <tr style="background: #f0efef">
        <td colspan="4" style="color: #15428b;font-weight: bold;padding-left: 10px; height: 25px">
            Các chỉ tiêu cảm quan
        </td>
    </tr>       
    <tr>
        <td width="25%"><sd:Label>Hương, Vị, Độ nặng, Độ cháy, Màu sắc sợi</sd:Label></td>
            <td width="75%" colspan="3">
            <%--
            <sd:TextBox key="" 
                        id="createForm.detailProduct.productOtherStatus" 
                        name="createForm.detailProduct.productOtherStatus" 
                        maxlength="255" 
                        cssStyle="width:98%"
                        trim="true"/>
            --%>
            <sd:Textarea id="createForm.detailProduct.productOtherStatus" 
                         name="createForm.detailProduct.productOtherStatus"
                         key="" 
                         maxlength="1000" 
                         cssStyle="width:98%" 
                         rows="3" trim="true"/>
        </td>
    </tr>         
</table>
<br/>
<table id="mainlyTargetTbl" class="lstTable">
    <tr style="background: #f0efef">
        <td colspan="6" style="color: #15428b;font-weight: bold;height: 25px">
            Các chỉ tiêu chất lượng chủ yếu
        </td>
    </tr>
    <tr class="headerRow">
        <th width="5%">STT</th>
        <th width="25%">Tên chỉ tiêu<font style="color:red">*</font></th>
        <th width="20%">Đơn vị</th>
        <th width="25%">Mức công bố<font style="color:red">*</font></th>
        <th width="20%">Mức đáp ứng/Khẩu phần ăn</th>
        <th width="5%">Xóa</th>
    </tr>
</table>
<div>
    <sx:ButtonAddCategory onclick="createNewMainlyTargetRow('','',null,'','');"/>
</div>

<table id="biologicTargetTbl" class="lstTable">
    <tr style="background: #f0efef">
        <td colspan="5" style="color: #15428b;font-weight: bold;height: 25px">
            Chỉ tiêu vi sinh vật
        </td>
    </tr>
    <tr class="headerRow">
        <th width="5%">STT</th>
        <th width="25%">Tên chỉ tiêu<font style="color:red">*</font></th>
        <th width="20%">Đơn vị</th>
        <th width="45%">Mức Tối đa<font style="color:red">*</font></th>
        <th width="5%">Xóa</th>
    </tr>
</table>
<div>
    <sx:ButtonAddCategory onclick="createNewTarget('biologicTargetTbl',1);"/>
</div>

<table id="heavyMetalTargetTbl" class="lstTable">
    <tr style="background: #f0efef">
        <td colspan="5" style="color: #15428b;font-weight: bold;height: 25px">
            Hàm lượng kim loại nặng
        </td>
    </tr>
    <tr class="headerRow">
        <th width="5%">STT</th>
        <th width="25%">Tên chỉ tiêu<font style="color:red">*</font></th>
        <th width="20%">Đơn vị</th>
        <th width="45%">Mức Tối đa<font style="color:red">*</font></th>
        <th width="5%">Xóa</th>
    </tr>
</table>
<div>
    <sx:ButtonAddCategory onclick="createNewTarget('heavyMetalTargetTbl',2);"/>
</div>
<table id="chemicalTargetTbl" class="lstTable">
    <tr style="background: #f0efef">
        <td colspan="5" style="color: #15428b;font-weight: bold;height: 25px">
            Hàm lượng hóa chất không mong muốn
        </td>
    </tr>
    <tr class="headerRow">
        <th width="5%">STT</th>
        <th width="25%">Tên chỉ tiêu<font style="color:red">*</font></th>
        <th width="20%">Đơn vị</th>
        <th width="45%">Mức Tối đa<font style="color:red">*</font></th>
        <th width="5%">Xóa</th>
    </tr>
</table>
<div>
    <tr>
        <td><sd:Label><strong>Thông tin bổ sung</strong></sd:Label></td>
        </tr>
        <tr>
            <td>
            <%--<textarea class="ckeditor" cols="80" id="editorChemicalTargetUnwanted" rows="3" tabindex="1"></textarea>--%>
            <sd:Textarea id="createForm.detailProduct.chemicalTargetUnwanted" 
                         name="createForm.detailProduct.chemicalTargetUnwanted"
                         key="" 
                         maxlength="500" 
                         cssStyle="width:98%" 
                         rows="2" trim="true"/>
        </td>
    </tr>
    <sx:ButtonAddCategory onclick="createNewTarget('chemicalTargetTbl',3);"/>
</div>
<table class="editTable">
    <tr>
        <td><sd:Label><strong>Các chi tiêu khác</strong></sd:Label></td>
        </tr>
        <tr>
            <td>
            <sd:Textarea id="createForm.detailProduct.otherTarget" 
                         name="createForm.detailProduct.otherTarget"
                         key="" 
                         maxlength="2000" 
                         cssStyle="width:98%" 
                         rows="2" trim="true"/>
        </td>
    </tr>
    <tr>
        <td><sd:Label><strong>Thành phần cấu tạo<font style="color:red">*</font></strong></sd:Label></td>
        </tr>
        <tr>
            <td>
            <sd:Textarea id="createForm.detailProduct.components" 
                         name="createForm.detailProduct.components"
                         key="" 
                         maxlength="2000" 
                         cssStyle="width:98%" 
                         rows="2" trim="true"/>
        </td>
    </tr>
    <tr>
        <td><sd:Label><strong>Thời hạn sử dụng <font style="color:red">*</font></strong></sd:Label></td>
        </tr>
        <tr>
            <td>
            <sd:Textarea id="createForm.detailProduct.timeInUse" 
                         name="createForm.detailProduct.timeInUse"
                         key="" 
                         maxlength="500" 
                         cssStyle="width:98%" 
                         rows="2" trim="true"/>
        </td>
    </tr>
    <tr>
        <td><sd:Label><strong>Công dụng</strong></sd:Label></td>
        </tr>
        <tr>
            <td>
            <sd:Textarea id="createForm.detailProduct.useage" 
                         name="createForm.detailProduct.useage"
                         key="" 
                         maxlength="2000" 
                         cssStyle="width:98%" 
                         rows="2" trim="true"/>
        </td>
    </tr>
    <tr>
        <td><sd:Label><strong>Đối tượng sử dụng</strong></sd:Label></td>
        </tr>
        <tr>
            <td>
            <sd:Textarea id="createForm.detailProduct.objectUse" 
                         name="createForm.detailProduct.objectUse"
                         key="" 
                         maxlength="2000" 
                         cssStyle="width:98%" 
                         rows="2" trim="true"/>
        </td>
    </tr>
    <tr>
        <td><sd:Label><strong>Hướng dẫn sử dụng và bảo quản <font style="color:red">*</font></strong></sd:Label></td>
        </tr>
        <tr>
            <td>
            <sd:Textarea id="createForm.detailProduct.guideline" 
                         name="createForm.detailProduct.guideline"
                         key="" 
                         maxlength="2000" 
                         cssStyle="width:98%" 
                         rows="2" trim="true"/>
        </td>
    </tr>
    <tr>
        <td><sd:Label><strong>Chất liệu bao bì và quy cách đóng gói <font style="color:red">*</font></strong></sd:Label></td>
        </tr>
        <tr>
            <td>
            <sd:Textarea key="" 
                         id="createForm.detailProduct.packateMaterial" 
                         name="createForm.detailProduct.packateMaterial" 
                         maxlength="2000" 
                         cssStyle="width:98%"
                         trim="true" rows="2"/>
        </td>
    </tr>
    <tr>
        <td><sd:Label><strong>Quy trình sản xuất</strong></sd:Label></td>
        </tr>
        <tr>
            <td>
            <sd:Textarea key="" 
                         id="createForm.detailProduct.productionProcess" 
                         name="createForm.detailProduct.productionProcess" 
                         maxlength="500" 
                         cssStyle="width:98%"
                         trim="true" rows="2"/>
        </td>
    </tr>
    <tr>
        <td><sd:Label><strong>Các biện pháp phân biệt thật, giả (nếu có)</strong></sd:Label></td>
        </tr>
        <tr>
            <td>
            <sd:Textarea key="" 
                         id="createForm.detailProduct.counterfeitDistinctive" 
                         name="createForm.detailProduct.counterfeitDistinctive" 
                         maxlength="500" 
                         cssStyle="width:98%"
                         trim="true" rows="2"/>
        </td>
    </tr>
    <tr>
        <td><sd:Label><strong>Xuất xứ và thương nhân chịu trách nhiệm về chất lượng hàng hóa <font style="color:red">*</font></strong></sd:Label></td>
        </tr>
        <tr>
            <td>
            <sd:Textarea key="" 
                         id="createForm.detailProduct.origin" 
                         name="createForm.detailProduct.origin" 
                         maxlength="2000" 
                         cssStyle="width:98%"
                         trim="true" rows="2"/>
        </td>
    </tr>
    <tr>
        <td><sd:Label><strong>Ngày ký</strong></sd:Label></td>
        </tr>
        <tr>
            <td>
            <sx:DatePicker key="" cssStyle="width:25%"
                           id="createForm.detailProduct.signDate" 
                           name="createForm.detailProduct.signDate" 
                           format="dd/MM/yyyy"/>
        </td>
    </tr>
    <tr>
        <td><sd:Label><strong>Người ký</strong></sd:Label></td>
        </tr>
        <tr>
            <td>
            <sd:TextBox key="" 
                        id="createForm.detailProduct.signer" 
                        name="createForm.detailProduct.signer" 
                        maxlength="255" 
                        cssStyle="width:98%"
                        trim="true"/>
        </td>
    </tr>
</table>

<script type="text/javascript">
    //loadEditor("havm");
    var flagCkb = 1;
    page.deleteRowOfTable = function (rowElement) {
        var tbody = rowElement.parentNode;
        tbody.removeChild(rowElement);
    };

    page.renameElementOfMainlyTarget = function () {
        var preName = "createForm.lstMainlyTarget[";
        var preDiv = "createForm.divLstMainlyTarget[";
        var table = document.getElementById("mainlyTargetTbl");
        var rows = table.getElementsByTagName("tr");
        var i = 0;
        for (i = 2; i < rows.length; i++) {
            var tds = rows[i].getElementsByTagName("td");
            tds[0].innerHTML = i - 1;
            var inputs = rows[i].getElementsByTagName("input");
            inputs[0].setAttribute("name", preDiv + (i - 2) + "].targetName");
            inputs[0].setAttribute("id", preDiv + (i - 2) + "].targetName");

            inputs[1].setAttribute("name", preName + (i - 2) + "].targetName");
            inputs[1].setAttribute("id", preName + (i - 2) + "].targetName");
            inputs[2].setAttribute("name", preName + (i - 2) + "].mainlyTargetId");
            inputs[2].setAttribute("id", preName + (i - 2) + "].mainlyTargetId");

            inputs[3].setAttribute("name", preName + (i - 2) + "].unitName");
            inputs[3].setAttribute("id", preName + (i - 2) + "].unitName");

            inputs[4].setAttribute("name", preDiv + (i - 2) + "].publishLevel");
            inputs[4].setAttribute("id", preDiv + (i - 2) + "].publishLevel");

            inputs[5].setAttribute("name", preName + (i - 2) + "].publishLevel");
            inputs[5].setAttribute("id", preName + (i - 2) + "].publishLevel");

            inputs[6].setAttribute("name", preDiv + (i - 2) + "].meetLevel");
            inputs[6].setAttribute("id", preDiv + (i - 2) + "].meetLevel");

            inputs[7].setAttribute("name", preName + (i - 2) + "].meetLevel");
            inputs[7].setAttribute("id", preName + (i - 2) + "].meetLevel");

            var select = rows[i].getElementsByTagName("select");
            select[0].setAttribute("name", preName + (i - 2) + "].unitId");
            select[0].setAttribute("id", preName + (i - 2) + "].unitId");

            var divs = rows[i].getElementsByTagName("div");
            divs[0].setAttribute("name", preDiv + (i - 2) + "].targetName");
            divs[0].setAttribute("id", preDiv + (i - 2) + "].targetName");
            divs[1].setAttribute("name", preDiv + (i - 2) + "].publishLevel");
            divs[1].setAttribute("id", preDiv + (i - 2) + "].publishLevel");
            divs[2].setAttribute("name", preDiv + (i - 2) + "].meetLevel");
            divs[2].setAttribute("id", preDiv + (i - 2) + "].meetLevel");
        }
    };

    page.convertHtmlToOldStyle = function (source) {
        var content = source.innerHTML;
        console.log(content);
        var backup = content;
        if (content == null)
            return null;
        content = content.replace(/<sup>/g, "^(");
        content = content.replace(/<\/sup>/g, "^)");
        content = content.replace(/<sub>/g, "_(");
        content = content.replace(/<\/sub>/g, "_)");
        source.innerHTML = content;
        if (source.textContent) {
            //
            // firefox
            //
            content = source.textContent;
        } else {
            //
            // ie & chrome
            //
            content = source.innerText;
        }
        source.innerHTML = backup;
        if (content == undefined) {
            console.log("content is null");
            content = "";
        }
        return content;
    };

    page.updateListDataBeforeSubmit = function () {
        page.updateUnitName();

        var table = document.getElementById("mainlyTargetTbl");
        var rows = table.getElementsByTagName("tr");
        var i = 0;
        for (i = 2; i < rows.length; i++) {
            var inputs = rows[i].getElementsByTagName("input");
            var ps = rows[i].getElementsByTagName("p");
            inputs[1].value = page.convertHtmlToOldStyle(ps[0]);
            inputs[5].value = page.convertHtmlToOldStyle(ps[1]);
            inputs[7].value = page.convertHtmlToOldStyle(ps[2]);
        }

        table = document.getElementById("biologicTargetTbl");
        rows = table.getElementsByTagName("tr");
        i = 0;
        for (i = 2; i < rows.length; i++) {
            var inputs = rows[i].getElementsByTagName("input");
            var ps = rows[i].getElementsByTagName("p");
            inputs[1].value = page.convertHtmlToOldStyle(ps[0]);
            inputs[6].value = page.convertHtmlToOldStyle(ps[1]);
        }

        table = document.getElementById("heavyMetalTargetTbl");
        rows = table.getElementsByTagName("tr");
        i = 0;
        for (i = 2; i < rows.length; i++) {
            var inputs = rows[i].getElementsByTagName("input");
            var ps = rows[i].getElementsByTagName("p");
            inputs[1].value = page.convertHtmlToOldStyle(ps[0]);
            inputs[6].value = page.convertHtmlToOldStyle(ps[1]);
        }

        table = document.getElementById("chemicalTargetTbl");
        rows = table.getElementsByTagName("tr");
        i = 0;
        for (i = 2; i < rows.length; i++) {
            var inputs = rows[i].getElementsByTagName("input");
            var ps = rows[i].getElementsByTagName("p");
            inputs[1].value = page.convertHtmlToOldStyle(ps[0]);
            inputs[6].value = page.convertHtmlToOldStyle(ps[1]);
        }
//        if (document.getElementById("ckIsHaveSubLabel").checked) {
//            dijit.byId("createForm.isHaveSubLabel").setValue(1);
//        } else {
//            dijit.byId("createForm.isHaveSubLabel").setValue(0);
//        }
    };

    page.validateMainlyTargetData = function () {
        var table = document.getElementById("mainlyTargetTbl");
        var rows = table.getElementsByTagName("tr");
        var i = 0;
        for (i = 2; i < rows.length; i++) {
            var inputs = rows[i].getElementsByTagName("input");
            var content = inputs[1].value.trim();
            inputs[1].value = content;
            if (content == null || content == "") {
                alert("[Các chỉ tiêu chất lượng chủ yếu][Tên chỉ tiêu dòng " + (i - 1) + "] chưa nhập");
                inputs[1].focus();
                return false;
            } else {
                if (content.length > 250) {
                    alert("[Các chỉ tiêu chất lượng chủ yếu][Tên chỉ tiêu dòng " + (i - 1) + "] dài hơn 250 ký tự");
                    inputs[1].focus();
                    return false;
                }
            }

            var select = rows[i].getElementsByTagName("select")[0];
            content = getSelectElementName(select.value);
            inputs[3].value = content;
            //validate don vi - hieptq
            if (content == "-- Chọn --") {
                alert("Đơn vị dòng " + (i - 1) + " chưa chọn");
                select.focus();
                return false;
            }

            content = inputs[5].value.trim();
            inputs[5].value = content;
            if (content == null || content == "") {
                alert("[Các chỉ tiêu chất lượng chủ yếu][Mức công bố dòng " + (i - 1) + "] chưa nhập");
                inputs[5].focus();
                return false;
            } else {
                if (content.length > 250) {
                    alert("[Các chỉ tiêu chất lượng chủ yếu][Mức  công bố dòng " + (i - 1) + "] dài hơn 250 ký tự");
                    inputs[5].focus();
                    return false;
                }
            }

//            content = inputs[3].value.trim();
//            inputs[3].value = content;
//            if (content == null || content == "") {
//                alert("[Các chỉ tiêu chất lượng chủ yếu][Mức  đáp ứng dòng " + (i - 1) + "] chưa nhập");
//                inputs[3].focus();
//                return false;
//            } else {
//                if (content.length > 50) {
//                    alert("[Các chỉ tiêu chất lượng chủ yếu][Mức  đáp ứng dòng " + (i - 1) + "] dài hơn 50 ký tự");
//                    inputs[3].focus();
//                    return false;
//                }
//            }
        }
        return true;
    };
    page.validateAnotherProductTargetData = function ()
    {
        var components = dijit.byId("createForm.detailProduct.components").getValue();
        var timeInUse = dijit.byId("createForm.detailProduct.timeInUse").getValue();
        var guideline = dijit.byId("createForm.detailProduct.guideline").getValue();
        var packateMaterial = dijit.byId("createForm.detailProduct.packateMaterial").getValue();
        var origin = dijit.byId("createForm.detailProduct.origin").getValue();
        if (components.trim().length == 0)
        {
            alert("Chưa nhập thành phần cấu tạo");
            return false;
        }
        if (timeInUse.trim().length == 0)
        {
            alert("Chưa nhập thời gian sử dụng");
            return false;
        }
        if (guideline.trim().length == 0)
        {
            alert("Chưa nhập hướng dẫn sử dụng");
            return false;
        }
        if (packateMaterial.trim().length == 0)
        {
            alert("Chưa nhập chất liệu bao bì và quy cách đóng gói ");
            return false;
        }
        if (origin.trim().length == 0)
        {
            alert("Chưa nhập xuất xứ và thương nhân chịu trách nhiệm về chất lượng hàng hóa");
            return false;
        }
        return true;
    };


    page.validateProductTargetData = function (tableId) {
        var table = document.getElementById(tableId);
        var rows = table.getElementsByTagName("tr");
        var i = 0;
        var strAlert = "";
        switch (tableId) {
            case "biologicTargetTbl":
                strAlert = "Chỉ tiêu vi sinh vật";
                break;
            case "heavyMetalTargetTbl":
                strAlert = "Hàm lượng kim loại nặng";
                break;
            case "chemicalTargetTbl":
                strAlert = "Hàm lượng hóa chất không mong muốn";
                break;
        }
        for (i = 2; i < rows.length; i++) {
            var inputs = rows[i].getElementsByTagName("input");
            var content = inputs[1].value.trim();
            inputs[1].value = content;
            if (content == null || content == "") {
                alert("[" + strAlert + "][Tên chỉ tiêu dòng " + (i - 1) + "] chưa nhập");
                inputs[1].focus();
                return false;
            } else if (content.length > 250) {
                alert("[" + strAlert + "][Mức  chỉ tiêu dòng " + (i - 1) + "] dài hơn 250 ký tự");
                inputs[1].focus();
                return false;
            }

            var select = rows[i].getElementsByTagName("select");
            content = getSelectElementName(select[0].value);
            inputs[4].value = content;
            // validate don vi - hieptq
            if (content == "-- Chọn --") {
                alert("Đơn vị dòng " + (i - 1) + " chưa chọn");
                select[0].focus();
                return false;
            }
            content = inputs[6].value.trim();
            inputs[6].value = content;
            if (content == null || content == "") {
                alert("[" + strAlert + "][Mức  tối đa dòng " + (i - 1) + "] chưa nhập");
                inputs[6].focus();
                return false;
            } else if (content.length > 250) {
                alert("[" + strAlert + "][Mức  tối đa dòng " + (i - 1) + "] dài hơn 250 ký tự");
                inputs[6].focus();
                return false;
            }
        }
        return true;
    };

    page.updateUnitNameOfMainlyTarget = function () {
        var table = document.getElementById("mainlyTargetTbl");
        var rows = table.getElementsByTagName("tr");
        var i = 0;
        for (i = 2; i < rows.length; i++) {
            var inputs = rows[i].getElementsByTagName("input");
            var select = rows[i].getElementsByTagName("select")[0];
            var content = getSelectElementName(select.value);
            inputs[3].value = content;
        }

    };

    page.updateUnitNameOfProductTarget = function (tableId) {
        var table = document.getElementById(tableId);
        var rows = table.getElementsByTagName("tr");
        var i = 0;
        for (i = 2; i < rows.length; i++) {
            var inputs = rows[i].getElementsByTagName("input");
            var select = rows[i].getElementsByTagName("select");
            var content = getSelectElementName(select[0].value);
            inputs[4].value = content;
        }
        return true;
    };

    page.updateUnitName = function () {
        page.updateUnitNameOfMainlyTarget();
        page.updateUnitNameOfProductTarget("biologicTargetTbl");
        page.updateUnitNameOfProductTarget("heavyMetalTargetTbl");
        page.updateUnitNameOfProductTarget("chemicalTargetTbl");
    };

    page.renameElementOfProductTarget = function (targetType, tableId) {
        var preName = "createForm.lstBioTarget[";
        var preDiv = "createForm.lstDivBioTarget[";
        if (targetType == 2) {
            preName = "createForm.lstHeavyMetal[";
            preDiv = "createForm.lstDivHeavyMetal[";
        } else if (targetType == 3) {
            preName = "createForm.lstChemical[";
            preDiv = "createForm.lstDivChemical[";
        }
        var table = document.getElementById(tableId);
        var rows = table.getElementsByTagName("tr");
        var i = 0;
        for (i = 2; i < rows.length; i++) {
            var tds = rows[i].getElementsByTagName("td");
            tds[0].innerHTML = i - 1;
            var inputs = rows[i].getElementsByTagName("input");
            inputs[0].setAttribute("name", preDiv + (i - 2) + "].targetName");
            inputs[0].setAttribute("id", preDiv + (i - 2) + "].targetName");

            inputs[1].setAttribute("name", preName + (i - 2) + "].targetName");
            inputs[1].setAttribute("id", preName + (i - 2) + "].targetName");
            inputs[2].setAttribute("name", preName + (i - 2) + "].productTargetId");
            inputs[2].setAttribute("id", preName + (i - 2) + "].productTargetId");
            inputs[3].setAttribute("name", preName + (i - 2) + "].targetType");
            inputs[3].setAttribute("id", preName + (i - 2) + "].targetType");

            inputs[4].setAttribute("name", preName + (i - 2) + "].unitName");
            inputs[4].setAttribute("id", preName + (i - 2) + "].unitName");

            inputs[5].setAttribute("name", preDiv + (i - 2) + "].maxLevel");
            inputs[5].setAttribute("id", preDiv + (i - 2) + "].maxLevel");

            inputs[6].setAttribute("name", preName + (i - 2) + "].maxLevel");
            inputs[6].setAttribute("id", preName + (i - 2) + "].maxLevel");

            var select = rows[i].getElementsByTagName("select");
            select[0].setAttribute("name", preName + (i - 2) + "].unitId");
            select[0].setAttribute("id", preName + (i - 2) + "].unitId");

            var divs = rows[i].getElementsByTagName("div");
            divs[0].setAttribute("name", preDiv + (i - 2) + "].targetName");
            divs[0].setAttribute("id", preDiv + (i - 2) + "].targetName");
            divs[1].setAttribute("name", preDiv + (i - 2) + "].maxLevel");
            divs[1].setAttribute("id", preDiv + (i - 2) + "].maxLevel");
        }
    };

    page.reloadTinyMCE = function () {
        tinymce.remove();
        var preDiv = "#createForm\\.divLstMainlyTarget\\[";
        var i = 0;
        var table = document.getElementById("mainlyTargetTbl");
        var rows = table.getElementsByTagName("tr");
        for (i = 2; i < rows.length; i++) {
            loadEditor(preDiv + (i - 2) + "\\]\\.targetName");
            loadEditor(preDiv + (i - 2) + "\\]\\.publishLevel");
            loadEditor(preDiv + (i - 2) + "\\]\\.meetLevel");
        }
        preDiv = "#createForm\\.lstDivBioTarget\\[";
        table = document.getElementById("biologicTargetTbl");
        rows = table.getElementsByTagName("tr");
        for (i = 2; i < rows.length; i++) {
            loadEditor(preDiv + (i - 2) + "\\]\\.targetName");
            loadEditor(preDiv + (i - 2) + "\\]\\.maxLevel");
        }
        preDiv = "#createForm\\.lstDivHeavyMetal\\[";
        table = document.getElementById("heavyMetalTargetTbl");
        rows = table.getElementsByTagName("tr");
        for (i = 2; i < rows.length; i++) {
            loadEditor(preDiv + (i - 2) + "\\]\\.targetName");
            loadEditor(preDiv + (i - 2) + "\\]\\.maxLevel");
        }
        preDiv = "#createForm\\.lstDivChemical\\[";
        table = document.getElementById("chemicalTargetTbl");
        rows = table.getElementsByTagName("tr");
        for (i = 2; i < rows.length; i++) {
            loadEditor(preDiv + (i - 2) + "\\]\\.targetName");
            loadEditor(preDiv + (i - 2) + "\\]\\.maxLevel");
        }
    };

    page.deleteMainlyTarget = function (rowElement) {
        msg.confirm("Bạn có chắc muốn xóa tiêu chí không ?", "Xóa tiêu chí", function () {
            page.deleteRowOfTable(rowElement);
            page.renameElementOfMainlyTarget();
            page.reloadTinyMCE();
        });
    };

    page.deleteProductTarget = function (rowElement, targetType, tableId) {
        msg.confirm("Bạn có chắc muốn xóa tiêu chí không ?", "Xóa tiêu chí", function () {
            page.deleteRowOfTable(rowElement);
            page.renameElementOfProductTarget(targetType, tableId);
            page.reloadTinyMCE();
        });

    };

    dojo.connect(dijit.byId('files_tab_tablist_tab.productDetail'), "onClick", loadFormFromAnnounTab = function () {
        //document.getElementById("createForm.detailProduct.assessmentMethod").innerHTML = dijit.byId("createForm.announcement.businessAddress").getValue();
        document.getElementById("createForm.detailProduct.businessName").innerHTML = escapeHtml_(dijit.byId("createForm.announcement.businessName").getValue());
        document.getElementById("createForm.detailProduct.productName").innerHTML = escapeHtml_(dijit.byId("createForm.announcement.productName").getValue());
        document.getElementById("createForm.detailProduct.announcementNo").innerHTML = escapeHtml_(dijit.byId("createForm.announcement.announcementNo").getValue());
    });

    page.validateDetailProduct = function () {
        if (dijit.byId("createForm.detailProduct.productType").getValue() == -1) {
            alert("[Nhóm sản phẩm] chưa nhập");
            dijit.byId("createForm.detailProduct.productType").focus();
            return false;
        }
        var productTypeName = dijit.byId("createForm.detailProduct.productType").attr("displayedValue");
        dijit.byId("createForm.detailProduct.productTypeName").setValue(productTypeName);

        var tabs = dijit.byId("files_tab");
        var panel = dijit.byId("tab.productDetail");
        tabs.selectChild(panel);

        page.updateListDataBeforeSubmit();

        if (!page.validateMainlyTargetData()) {
            return false;
        }

        if (!page.validateProductTargetData("biologicTargetTbl")) {
            return false;
        }

        if (!page.validateProductTargetData("heavyMetalTargetTbl")) {
            return false;
        }

        if (!page.validateProductTargetData("chemicalTargetTbl")) {
            return false;
        }
        if (!page.validateAnotherProductTargetData())
        {
            return false;
        }
//        if (document.getElementById("ckIsHaveSubLabel").checked) {
//            dijit.byId("createForm.isHaveSubLabel").setValue(1);
//        } else {
//            dijit.byId("createForm.isHaveSubLabel").setValue(0);
//        }
        return true;

    };
    page.afterLoadMainlyTarget = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result != null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                createNewMainlyTargetRow(item.mainlyTargetId, item.targetName, item.unitId, item.publishLevel, item.meetLevel);
            }
        }
        replaceExponentByShift6("mainlyTargetTbl");
        replaceSubscript("mainlyTargetTbl");
    };

    page.loadInitMainlyTargets = function () {
        var fileId = dijit.byId("createForm.fileId").getValue();
        if (fileId == null || fileId == "") {
            //createNewMainlyTargetRow('', '', null, '', '');
        } else {
            sd.connector.post("filesAction!loadMainlyTarget.do?createForm.fileId=" + fileId, null, null, null, page.afterLoadMainlyTarget);
        }
    };

    page.afterProductTarget = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result != null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                createNewRowOfTarget(item.productTargetId, item.targetName, item.unitId, item.maxLevel, item.targetType, "")
            }

        }
        replaceExponentByShift6("chemicalTargetTbl");
        replaceExponentByShift6("biologicTargetTbl");
        replaceExponentByShift6("heavyMetalTargetTbl");
        replaceSubscript("chemicalTargetTbl");
        replaceSubscript("biologicTargetTbl");
        replaceSubscript("heavyMetalTargetTbl");

    };

    page.loadInitProductTargets = function () {
        var fileId = dijit.byId("createForm.fileId").getValue();
        if (fileId == null || fileId == "") {
            //createNewTarget('biologicTargetTbl', 1);
            //createNewTarget('heavyMetalTargetTbl', 2);
            //createNewTarget('chemicalTargetTbl', 3);
        } else {
            sd.connector.post("filesAction!loadProductTarget.do?createForm.fileId=" + fileId, null, null, null, page.afterProductTarget);
        }
    };

    page.afterLoadMainlyTargetCopy = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result != null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                createNewMainlyTargetRow("", item.targetName, item.unitId, item.publishLevel, item.meetLevel);
            }
        }
        replaceExponentByShift6("mainlyTargetTbl");
        replaceSubscript("mainlyTargetTbl");
    };

    page.loadInitMainlyTargetsCopy = function () {
        var fileId = dijit.byId("createForm.fileId").getValue();
        if (fileId == null || fileId == "") {
            createNewMainlyTargetRow('', '', null, '', '');
        } else {
            sd.connector.post("filesAction!loadMainlyTarget.do?createForm.fileId=" + fileId, null, null, null, page.afterLoadMainlyTargetCopy);
        }
    };

    page.afterProductTargetCopy = function (data) {
        var obj = dojo.fromJson(data);
        var result = obj.items;
        if (result != null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                var item = result[i];
                createNewRowOfTarget("", item.targetName, item.unitId, item.maxLevel, item.targetType, "")
            }
        }
        replaceExponentByShift6("chemicalTargetTbl");
        replaceExponentByShift6("biologicTargetTbl");
        replaceExponentByShift6("heavyMetalTargetTbl");
        replaceSubscript("chemicalTargetTbl");
        replaceSubscript("biologicTargetTbl");
        replaceSubscript("heavyMetalTargetTbl");
    };

    page.loadInitProductTargetsCopy = function () {
        var fileId = dijit.byId("createForm.fileId").getValue();
        if (fileId == null || fileId == "") {
            createNewTarget('biologicTargetTbl', 1);
            createNewTarget('heavyMetalTargetTbl', 2);
            createNewTarget('chemicalTargetTbl', 3);
        } else {
            sd.connector.post("filesAction!loadProductTarget.do?createForm.fileId=" + fileId, null, null, null, page.afterProductTargetCopy);
        }
    };
    page.showFee = function ()
    {
        var productTypeName = dijit.byId("createForm.detailProduct.productType").attr("displayedValue");
        dijit.byId("createForm.detailProduct.productTypeName").setValue(productTypeName);

        var check = dijit.byId("createForm.detailProduct.productType").getValue();
        var dbtId = "${fn:escapeXml(dbtId)}";
        var checkDBT = dbtId.split(";");
        var tpcnId = "${fn:escapeXml(tpcnId)}";
        var priceDBT = "${fn:escapeXml(priceTPDB)}";
        var priceTPCN = "${fn:escapeXml(priceTPCN)}";
        var priceETC = "${fn:escapeXml(priceETC)}";
        var tlId = "${tlId}";
        var dem = 0;
        for (var i = 0; i < checkDBT.length; i++)
        {
            if (check == checkDBT[i])
            {
                dem = 1;
                break;
            }
        }
        if (dem == 1)
        {
            alert("Phí thẩm định nhóm sản phẩm này có giá: " + priceDBT + " VNĐ, Chú ý chọn đúng nhóm SP vì có liên quan tới phí");
        } else {
            if (check == tpcnId)
            {
                alert("Phí thẩm định nhóm sản phẩm này có giá: " + priceTPCN + " VNĐ, Chú ý chọn đúng nhóm SP vì có liên quan tới phí");
            } else {
                if (check == -1)
                {

                } else {
                    if (check == tlId)
                    {
                        alert("Phí thẩm định nhóm sản phẩm này có giá: " + 0 + " VNĐ, Chú ý: Nhóm thuốc lá không phải đóng phí");
                    } else {
                        if (check != dbtId && check != tpcnId && check != -1) {
                            alert("Phí thẩm định nhóm sản phẩm này có giá: " + priceETC + " VNĐ, Chú ý chọn đúng nhóm SP vì có liên quan tới phí");
                        }
                    }
                }
            }
        }
    };
    var isCopy = false;
    try {
        isCopy = "${fn:escapeXml(isCopy)}";
    } catch (en) {
    }
    if (isCopy == "true") {
        page.loadInitMainlyTargetsCopy();
        page.loadInitProductTargetsCopy();
    } else {
        page.loadInitMainlyTargets();
        page.loadInitProductTargets();
    }

    page.hideCkeditorTabs = function (index)
    {
        document.getElementById("cke_" + index + "14").style.display = "none";
        document.getElementById("cke_" + index + "21").style.display = "none";
        document.getElementById("cke_" + index + "29").style.display = "none";
        document.getElementById("cke_" + index + "34").style.display = "none";
        document.getElementById("cke_" + index + "52").style.display = "none";
        document.getElementById("cke_" + index + "66").style.display = "none";
        document.getElementById("cke_" + index + "79").style.display = "none";
        document.getElementById("cke_" + index + "80").style.display = "none";
        document.getElementById("cke_" + index + "83").style.display = "none";
        document.getElementById("cke_" + index + "86").style.display = "none";

        document.getElementById("cke_" + index + "45").style.display = "none";
        document.getElementById("cke_" + index + "46").style.display = "none";
        document.getElementById("cke_" + index + "47").style.display = "none";
        document.getElementById("cke_" + index + "48").style.display = "none";
        document.getElementById("cke_" + index + "51").style.display = "none";

        document.getElementById("cke_" + index + "71").style.display = "none";
        document.getElementById("cke_" + index + "72").style.display = "none";
        document.getElementById("cke_" + index + "73").style.display = "none";
        document.getElementById("cke_" + index + "74").style.display = "none";
        document.getElementById("cke_" + index + "75").style.display = "none";
        document.getElementById("cke_" + index + "77").style.display = "none";
        document.getElementById("cke_" + index + "78").style.display = "none";
    };

    page.afterLoadProductDetailTH = function () {
//        var isHaveSubLabel = dijit.byId('createForm.isHaveSubLabel').getValue();
//        if (isHaveSubLabel == '1') {
//            dijit.byId("ckIsHaveSubLabel").attr("checked", "on");
//        } else {
//            dijit.byId("ckIsHaveSubLabel").attr("checked", "");
//        }
    };
    page.afterLoadProductDetailTH();
//    page.onchangeCkbIHSL = function() {
//        if (document.getElementById("ckIsHaveSubLabel").checked && flagCkb == 0) {
//            msg.alert("Chú ý: Việc tích tự động đính kèm nhãn, hồ sơ không cần phải tải file đính kèm nhãn lên, nếu không tích tự động đính kèm nhãn doanh nghiệp chủ động đính kèm nhãn phụ.", "Cảnh báo");
//        }
//        flagCkb = 0;
//    };
</script>
