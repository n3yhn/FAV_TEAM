<%@page import="java.util.ResourceBundle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<div id ="approveDiv" style="display:none">
    <sx:ResultMessage id="resultApproveMessage" />
    <form id="approveForm" name="approveForm">        
        <div class="buttonDiv">
            <sx:ButtonBack onclick="page.backView();"/>
            <sd:Button id="btnApprove" key="" onclick="page.approve();" >
                <img src="${contextPath}/share/images/icons/6.png" height="14" width="18">
                <span style="font-size:12px">Phê duyệt</span>
            </sd:Button>
            <sd:Button id="btnNotApprove" key="" onclick="page.showNotApprove();">
                <img src="${contextPath}/share/images/icons/13.png" height="14" width="18">
                <span style="font-size:12px">Từ chối</span>
            </sd:Button>
        </div>        
        <sd:TitlePane key="Thông tin chi tiết tài khoản" id="registerViewPanel">
            <sd:FieldSet key="Thông tin tài khoản" id="tab.userRep">
                <table width="100%" class="viewTable">                    
                    <tr>
                        <td width="25%"><sd:Label key="Email:" required="true"/></td>
                        <td width="75%">                        
                            <sd:TextBox id="approveForm.registerId" name="approveForm.registerId" cssStyle="display:none" key=""/>

                            <sd:TextBox id="approveForm.businessTypeId" name="approveForm.businessTypeId" cssStyle="display:none" key=""/>
                            <sd:TextBox id="approveForm.status" name="approveForm.status" cssStyle="display:none" key=""/>
                            <sd:TextBox id="approveForm.reason" name="approveForm.reason" cssStyle="display:none" key=""/>
                            <sd:TextBox id="approveForm.posId" name="approveForm.posId" cssStyle="display:none" key=""/>
                            <div id="approveForm.vmanageEmail"></div>
                        </td>
                        <td width="30%"><sd:Label key="UserName:" required="true"/></td>
                        <td width="50%">
                            <div id="approveForm.vbusinessTaxCode"></div>
                        </td>
                        <td width="30%"><sd:Label key="Password:" required="true"/></td>
                        <td width="85%">
                            <div id="approveForm.vmanagePassword"></div>
                        </td>

                        <td width="15%"><sd:Label key="Lí do:" required="true"/></td>
                        <td width="85%">
                            <div id="approveForm.vreason"></div>
                        </td>

                    </tr>
                </table>
            </sd:FieldSet>

            <sd:FieldSet key="Người đại diện" id="tab.business">
                <table width="100%" class="viewTable">
                    <tr>
                        <td width="15%"><sd:Label 
                                key="Họ và tên:" 
                                required="true"/></td>
                        <td width="35%">
                            <div id="approveForm.vuserFullName"></div>
                        </td>

                        <td width="15%">
                            <sd:Label key="Điện thoại:"/>
                        </td>
                        <td width="35%">
                            <div id="approveForm.vuserTelephone"></div>
                        </td>
                    </tr>
                    <tr>
                        <td><sd:Label 
                                key="Số di động:"/>
                        </td>
                        <td>
                            <div id="approveForm.vuserMobile"></div>
                        </td>
                        <td><sd:Label key="Email:"/></td>
                        <td>
                            <div id="approveForm.vuserEmail"></div>
                        </td>
                    </tr>
                </table>
            </sd:FieldSet>
            <sd:FieldSet key="Thông tin doanh nghiệp:" id="tab.business">

                <table width="100%" class="viewTable">
                    <tr>
                        <td width="15%" ><sd:Label key="Tên tiếng việt" required="true"/></td>
                        <td width="35%">
                            <div id="approveForm.vbusinessNameVi"></div>
                        </td>
                        <td width="15%">
                            <sd:Label key="Tên tiếng Anh:"/>
                        </td>
                        <td width="35%">
                            <div id="approveForm.vbusinessNameEng"></div>
                        </td>
                    </tr>
                    <tr>
                        <td><sd:Label key="Tên viết tắt:"/></td>
                        <td>
                            <div id="approveForm.vbusinessNameAlias"></div>
                        </td>
                        <td><sd:Label key="Loại hình:"/></td>
                        <td>
                            <div id="approveForm.vbusinessTypeName"></div>
                        </td>
                    </tr>
                    <tr>
<!--                        <td><sd:Label key="Mã số thuế:" required="true"/></td>
                        <td>
                            <div id="approveForm.vbusinessTaxCode"></div>
                        </td>-->
                        <td><sd:Label key="Số ĐKKD:" required="true"/></td>
                        <td>
                            <div id="approveForm.vbusinessLicense"></div>
                        </td>
                        <td><sd:Label key="Cơ quan chủ quản:" required="true"/></td>
                        <td>
                            <div id="approveForm.vgoverningBody"></div>
                        </td>
                    </tr>
                    <tr>
                        <td><sd:Label key="Địa chỉ:"/></td>
                        <td>
                            <div id="approveForm.vbusinessAdd"></div>
                        </td>
                        <td><sd:Label key="Tỉnh:"/></td>
                        <td>
                            <div id="approveForm.vbusinessProvince"></div>
                        </td>
                    </tr>
                    <tr>
                        <td><sd:Label key="Điện thoại:"/></td>
                        <td>
                            <div id="approveForm.vbusinessTelephone"></div>
                        </td>
                        <td><sd:Label key="Fax:"/></td>
                        <td>
                            <div id="approveForm.vbusinessFax"></div>
                        </td>
                    </tr>
                    <tr>
                        <td><sd:Label key="Website:"/></td>
                        <td>
                            <div id="approveForm.vbusinessWebsite"></div>
                        </td>
                        <td><sd:Label key="Năm thành lập:"/></td>
                        <td>
                            <div id="approveForm.vbusinessEstablishYear"></div>
                        </td>
                    </tr>
                    <tr>
                        <td><sd:Label key="Đại diện theo pháp luật:"/></td>
                        <td>
                            <div id="approveForm.vbusinessLawRep"></div>
                        </td>
                        <td><sd:Label key="Mô tả:"/></td>
                        <td>
                            <div id="approveForm.vdescription"></div>
                        </td>
                    </tr>
                </table>
            </sd:FieldSet>
        </sd:TitlePane>
    </form>
</div>