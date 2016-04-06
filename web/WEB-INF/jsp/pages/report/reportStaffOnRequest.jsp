<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="token">
    <s:token id="tokenId"/>
</div>

<sd:TitlePane key="search.searchCondition" id="categoryTitle">
    <form id="searchForm" name="searchForm">
        <s:hidden id="deptId" name="searchForm.deptId"/>
        <sd:TextBox key="" id="searchForm.searchType" name="searchForm.searchType" cssStyle="display:none"/>
        <table width="100%;" cellpadding="0px" cellspacing="5px">
            <tr>
                <td width="20%"></td>
                <td width="30%"></td>
                <td width="20%"></td>
                <td width="30%"></td>
            </tr>
            <tr>
                <td align="right">
                    <sd:Label key="Mã hồ sơ"/>
                </td>
                <td>
                    <sd:CheckBox id="searchForm.fileCodeCheck" name="searchForm.fileCodeCheck" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox> 
                    <sd:TextBox cssStyle="width:100%"
                                id="searchForm.fileCode"
                                key=""
                                name="searchForm.fileCode" maxlength="250"/> </td>
                <td align="right">
                    <sd:Label key="Tên loại hồ sơ"/>
                </td>
                <td>
                    <sd:CheckBox id="searchForm.fileTypeNameCheck" name="searchForm.fileTypeNameCheck" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox>  
               <%--     <sd:TextBox cssStyle="width:100%"
                                id="searchForm.fileTypeName"
                                key=""
                                name="searchForm.fileTypeName" maxlength="250"/>  --%>
                  <sd:SelectBox cssStyle="width:100%"
                                      id="searchForm.fileType"
                                      key="" data="lstFileType" valueField="procedureId" labelField="name"
                                      name="searchForm.fileType" >
                        </sd:SelectBox>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <sd:Label key="Tỉnh/Thành Phố"/>
                </td>
                <td>
                    <%--                        <sd:SelectBox id="searchForm.businessProvinceId" name="searchForm.businessProvinceId" key="" data="lstProvince" valueField="categoryId" labelField="name" />
                                            <sd:TextBox id="searchForm.businessProvince" name="searchForm.businessProvince" cssStyle="display:none" key=""/> --%>
                    <sd:CheckBox id="searchForm.businessProvinceCheck" name="searchForm.businessProvinceCheck" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox>   
                     <%--  <sd:TextBox cssStyle="width:100%"
                                id="searchForm.businessProvince"
                                key=""
                                name="searchForm.businessProvince" maxlength="250"/> --%>
                       <sd:SelectBox id="searchForm.businessProvinceId" name="searchForm.businessProvinceId" key="" data="lstProvince" valueField="categoryId" labelField="name" />
                    </td>



                    <td align="right"><sd:Label key="Trạng thái"/></td>
                <td>
                    <sd:CheckBox id="searchForm.displayStatusCheck" name="searchForm.displayStatusCheck" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox>   
                        <sd:SelectBox key="" id="searchForm.status" name="searchForm.status" cssStyle="width:100%">
                            <sd:Option value="-1" selected="true">-- Chọn --</sd:Option>
                            <sd:Option value="1">Mới nộp</sd:Option>
                            <sd:Option value="2">Đã được đề xuất xử lý</sd:Option>
                            <sd:Option value="3">Đã được lãnh đạo phòng phân công xử lý</sd:Option>
                            <sd:Option value="4">Đã được chuyên viên thẩm định</sd:Option>
                            <sd:Option value="5">Đã được lãnh đạo phòng xem xét kết quả</sd:Option>
                            <sd:Option value="14">Đã được văn thư tiếp nhận hồ sơ</sd:Option>
                            <sd:Option value="15">Đã được văn thư đối chiếu hồ sơ gốc</sd:Option>
                            <sd:Option value="16">Đã được văn thư đối chiếu hồ sơ gốc, có sai lệch</sd:Option>
                            <sd:Option value="17">Đã được văn thư tiếp nhận hồ sơ SĐBS</sd:Option>
                            <sd:Option value="18">Chờ văn thư tiếp nhận hồ sơ SĐBS</sd:Option>
                            <sd:Option value="6">Lãnh đạo cục đã phê duyệt kết quả</sd:Option>
                            <sd:Option value="7">Chuyên viên KL: SĐBS</sd:Option>
                            <sd:Option value="8">Lãnh đạo phòng trả lại để thẩm định lại</sd:Option>
                            <sd:Option value="9">Lãnh đạo cục trả lại để xem xét lại</sd:Option>
                            <sd:Option value="22">Hồ sơ đã được trả bản công bố</sd:Option>
                            <sd:Option value="23">Hồ sơ đã được văn thư thông báo đối chiều hồ sơ gốc</sd:Option>
                            <sd:Option value="28">Hồ sơ đã soạn dự thảo SĐBS</sd:Option>
                            <sd:Option value="20">Hồ sơ đã gửi công văn SĐBS</sd:Option>
                        </sd:SelectBox>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                    <sd:Label key="Tên tổ chức, cá nhân"/>
                </td>
                <td>
                    <sd:CheckBox id="searchForm.businessNameCheck" name="searchForm.businessNameCheck" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox>   
                       <sd:TextBox cssStyle="width:100%"
                                id="searchForm.businessName"
                                key=""
                                name="searchForm.businessName" maxlength="250"/> 
                    </td>
                    <td align="right">
                    <sd:Label key="Địa chỉ"/>
                </td>
                <td>


                    <sd:CheckBox id="searchForm.businessAddressCheck" name="searchForm.businessAddressCheck" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox>   
                       <sd:TextBox cssStyle="width:100%"
                                id="searchForm.businessAddress"
                                key=""
                                name="searchForm.businessAddress" maxlength="250"/> 
                    </td>
                </tr>
                <tr>
                    <td align="right">
                    <sd:Label key="Số ĐKKD"/>
                </td>
                <td>                                               
                    <sd:CheckBox id="searchForm.businessLicenceCheck" name="searchForm.businessLicenceCheck" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox>    
                       <sd:TextBox cssStyle="width:100%"
                                id="searchForm.businessLicence"
                                key=""
                                name="searchForm.businessLicence" maxlength="250"/> 
                    </td>
                    <td align="right">
                    <sd:Label key="Số chứng nhận CB"/>
                </td>
                <td>
                    <sd:CheckBox id="searchForm.announcementNoCheck" name="searchForm.announcementNoCheck" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox>    
                   <sd:TextBox cssStyle="width:100%"
                                id="searchForm.announcementNo"
                                key=""
                                name="searchForm.announcementNo" maxlength="250"/> 
                    </td>
                </tr>
                <tr>
                    <td  align="right"><sd:Label>Nhóm sản phẩm</sd:Label></td>
                    <td >                       
                    <sd:CheckBox id="searchForm.productTypeNameCheck" name="searchForm.productTypeNameCheck" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox>    
                          <sd:SelectBox id="searchForm.productTypeId" name="searchForm.productTypeId" key="" data="lstProductType" valueField="categoryId" labelField="name" />
                    </td>
                    <td align="right">
                    <sd:Label key="Tên sản phẩm"/>
                </td>
                <td>

                    <sd:CheckBox id="searchForm.productNameCheck" name="searchForm.productNameCheck" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox>   
                     <sd:TextBox cssStyle="width:100%"
                                id="searchForm.productName"
                                key=""
                                name="searchForm.productName" maxlength="250"/> 

                    </td>
                </tr>
                <tr>
                    <td align="right">
                    <sd:Label key="Xuất xứ"/>
                </td>
                <td>                    
                    <sd:CheckBox id="searchForm.nationNameCheck" name="searchForm.nationNameCheck" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox>    
                     <sd:TextBox cssStyle="width:100%"
                                id="searchForm.nationName"
                                key=""
                                name="searchForm.nationName" maxlength="250"/> 
                    </td>
                    <td align="right">
                    <sd:Label key="Nhà sản xuất"/>
                </td>
                <td>
                    <sd:CheckBox id="searchForm.manufactureNameCheck" name="searchForm.manufactureNameCheck" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox>    
                      <sd:TextBox cssStyle="width:100%"
                                id="searchForm.manufactureName"
                                key=""
                                name="searchForm.manufactureName" maxlength="250"/> 
                    </td>
                </tr>
                <tr>

                    <td align="right">
                    <sd:Label key="Người ký"/>
                </td>
                <td>
                    <sd:CheckBox id="searchForm.signerNameCheck" name="searchForm.signerNameCheck" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox>    
                        <sd:TextBox cssStyle="width:100%"
                                id="searchForm.signerName"
                                key=""
                                name="searchForm.signerName" maxlength="250"/> 
                    </td>
                </tr>
            <%--               <tr>

                    <td align="right">
                    <sd:Label key="Lãnh đạo đơn vị"/>
                </td>
                <td>
                    <sd:CheckBox id="searchForm.leaderStaff" name="searchForm.leaderStaff" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox>    
                    </td>
                   

            </tr> --%>
            <tr>
                <td align="right">
                    <sd:Label key="Người thẩm xét"/>
                </td>
                <td>

                    <sd:CheckBox id="searchForm.nameStaffProcessCheck" name="searchForm.nameStaffProcessCheck" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox> 
                     <sd:TextBox cssStyle="width:100%"
                                id="searchForm.nameStaffProcess"
                                key=""
                                name="searchForm.nameStaffProcess" maxlength="250"/> 
                    </td>

                </tr>
                <tr>
                    <td align="right">
                    <sd:Label key="Theo thông tư 30"/>
                </td>
                <td>
                    <sd:CheckBox id="searchForm.is30" name="searchForm.is30" 
                                 checked="false" key="" value="1"
                                 ></sd:CheckBox>                    
                    </td>
                </tr>

                <tr>
                    <td align="right">
                    <sd:Label key="Gửi Từ ngày"/>
                </td>
                <td>
                    <sd:DatePicker cssStyle="width:35%"
                                   id="searchForm.sendDateFrom"
                                   key="" format="dd/MM/yyyy"
                                   name="searchForm.sendDateFrom"/>
                    <sd:Label key="Đến ngày "/>
                    <sd:DatePicker cssStyle="width:35%; float: right;" 
                                   id="searchForm.sendDateTo"
                                   key="" format="dd/MM/yyyy"
                                   name="searchForm.sendDateTo"/>
                </td>
                <td align="right">
                    <sd:Label key="Ngày ký"/>
                </td>
                <td>
                    <sd:DatePicker cssStyle="width:35%"
                                   id="searchForm.approveDateFrom"
                                   key="" format="dd/MM/yyyy"
                                   name="searchForm.approveDateFrom"/>
                    <sd:Label key="Đến ngày"/>
                    <sd:DatePicker cssStyle="width:35%; float: right;" 
                                   id="searchForm.approveDateTo"
                                   key="" format="dd/MM/yyyy"
                                   name="searchForm.approveDateTo"/>
                </td>


            </tr>

            <tr style="text-align: center">
                <td colspan="4">

                    <sd:Button id="totalStaffQualitySearchForm.reportButton" key="" onclick="page.report()">
                        <img src='share/images/icons/report_icon.jpg' height="20" width="20">
                        <span style="font-size:12px">Lập báo cáo</span>
                    </sd:Button>
                </td>
            </tr>
        </table>
    </form>
</sd:TitlePane>


<script>

    page.report = function() {
        var url = "report!reportStaffOnRequest.do";
        document.searchForm.action = url;
        document.searchForm.submit();
    }

</script>