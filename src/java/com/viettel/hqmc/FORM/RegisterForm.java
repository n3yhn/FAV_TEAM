/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.Business;
import com.viettel.hqmc.BO.Register;
import com.viettel.vsaadmin.database.BO.RoleUser;
import com.viettel.vsaadmin.database.BO.RoleUserPK;
import com.viettel.vsaadmin.database.BO.Users;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vtit_binhnt53
 */
public class RegisterForm implements Serializable {

    private Long registerId;
    private String manageEmail;
    private String userTelephone;
    private String userMobile;
    private Long businessTypeId;
    private String businessTypeName;
    private String businessNameVi;
    private String businessNameEng;
    private String businessNameAlias;
    private String businessTaxCode;
    private String businessLicense;
    private String businessAdd;
    private String businessProvince;
    private String businessTelephone;
    private String businessFax;
    private String businessWebsite;
    private String businessLawRep;
    private String description;
    private String businessEstablishYear;
    private String userFullName;
    private String managePassword;
    private String userEmail;
    private String manageRePassword;
    private Integer status;
    private String reason;
    private Long businessProvinceId;
    private Long posId;
    private String posName;
    private String newPosName;
    private Date createDate;
    private String governingBody;
    private Long modifiedBy;
    private Date modifyDate;

    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getNewPosName() {
        return newPosName;
    }

    public void setNewPosName(String newPosName) {
        this.newPosName = newPosName;
    }

    public RegisterForm() {
    }

    public RegisterForm(Register r) {
        if (r == null) {
            return;
        }
        this.registerId = r.getRegisterId();
        this.manageEmail = r.getManageEmail();
        this.userTelephone = r.getUserTelephone();
        this.userMobile = r.getUserMobile();
        this.businessTypeId = r.getBusinessTypeId();
        this.businessTypeName = r.getBusinessTypeName();
        this.businessNameVi = r.getBusinessNameVi();
        this.businessNameEng = r.getBusinessNameEng();
        this.businessNameAlias = r.getBusinessNameAlias();
        this.businessTaxCode = r.getBusinessTaxCode();
        this.businessLicense = r.getBusinessLicense();
        this.businessAdd = r.getBusinessAdd();
        this.businessProvince = r.getBusinessProvince();
        this.businessTelephone = r.getBusinessTelephone();
        this.businessFax = r.getBusinessFax();
        this.businessWebsite = r.getBusinessWebsite();
        this.businessLawRep = r.getBusinessLawRep();
        this.description = r.getDescription();
        this.businessEstablishYear = r.getBusinessEstablishYear();
        this.userFullName = r.getUserFullName();
        this.managePassword = r.getManagePassword();
        this.userEmail = r.getManageEmail();
        this.status = r.getStatus();
        this.reason = r.getReason();
        this.businessProvinceId = r.getBusinessProvinceId();
        this.posId = r.getPosId();
        this.posName = r.getPosName();
        //u141213 binhnt53
        this.createDate = r.getCreateDate();
        this.governingBody = r.getGoverningBody();
        this.modifiedBy = r.getModifiedBy();
        this.modifyDate = r.getModifyDate();
    }

    public Register convertToEntity() {
        Register item = new Register();
        if (this.registerId != null) {
            item.setRegisterId(this.registerId);
        }
        item.setManageEmail(this.manageEmail);
        item.setUserTelephone(this.userTelephone);
        item.setUserMobile(this.userMobile);
        item.setBusinessTypeId(this.businessTypeId);
        item.setBusinessTypeName(this.businessTypeName);
        item.setBusinessNameVi(this.businessNameVi);
        item.setBusinessNameEng(this.businessNameEng);
        item.setBusinessNameAlias(this.businessNameAlias);
        item.setBusinessTaxCode(this.businessTaxCode);
        item.setBusinessLicense(this.businessLicense);
        item.setBusinessAdd(this.businessAdd);
        item.setBusinessProvince(this.businessProvince);
        item.setBusinessTelephone(this.businessTelephone);
        item.setBusinessFax(this.businessFax);
        item.setBusinessWebsite(this.businessWebsite);
        item.setBusinessLawRep(this.businessLawRep);
        item.setDescription(this.description);
        item.setBusinessEstablishYear(this.businessEstablishYear);
        item.setUserFullName(this.userFullName);
        item.setManagePassword(this.managePassword);
        item.setUserEmail(this.userEmail);
        item.setStatus(this.status);
        item.setReason(this.reason);
        item.setBusinessProvinceId(this.businessProvinceId);
        item.setPosId(this.posId);
        item.setPosName(this.posName);
        //u141213 binhnt53
        item.setCreateDate(this.createDate);
        item.setGoverningBody(this.governingBody);
        item.setModifiedBy(this.modifiedBy);
        item.setModifyDate(this.modifyDate);
        return item;
    }

    public Register convertToEntityHomePage() {
        Register item = new Register();
        if (this.registerId != null) {
            item.setRegisterId(this.registerId);
        }
        item.setManageEmail(this.manageEmail);
        item.setUserTelephone(this.userTelephone);
        item.setUserMobile(this.userTelephone);
        item.setBusinessTypeId(this.businessTypeId);
        item.setBusinessTypeName(this.businessTypeName);
        item.setBusinessNameVi(this.businessNameVi);
        item.setBusinessNameEng(this.businessNameEng);
        item.setBusinessNameAlias(this.businessNameAlias);
        item.setBusinessTaxCode(this.businessTaxCode);
        item.setBusinessLicense(this.businessLicense);
        item.setBusinessAdd(this.businessAdd);
        item.setBusinessProvince(this.businessProvince);
        item.setBusinessTelephone(this.businessTelephone);
        item.setBusinessFax(this.businessFax);
        item.setBusinessWebsite(this.businessWebsite);
        item.setBusinessLawRep(this.userFullName);
        item.setDescription(this.description);
        item.setBusinessEstablishYear(this.businessEstablishYear);
        item.setUserFullName(this.userFullName);
        try {//BINHNT53 U141230
            if (this.businessTaxCode != null && this.businessTaxCode.length() >= 7) { // Print function
                item.setManagePassword("Attp@" + this.businessTaxCode.substring(3, 7));
            } else {
                item.setManagePassword("Attp@123");
            }
        } catch (IndexOutOfBoundsException e) {
            item.setManagePassword("Attp@123");
        }//!BINHNT53 U141230
        item.setUserEmail(this.manageEmail);
        item.setStatus(0);
        item.setReason(this.reason);
        item.setBusinessProvinceId(this.businessProvinceId);
        item.setPosId(this.posId);
        item.setPosName(this.posName);
        //u141213 binhnt53
//        item.setCreateDate(this.createDate);
        item.setGoverningBody(this.governingBody);
//        item.setModifiedBy(this.modifiedBy);
//        item.setModifyDate(this.modifyDate);
        return item;
    }

    public Business convertToBusiness() {
        Business item = new Business();
        item.setUserTelephone(this.userTelephone);
        item.setUserMobile(this.userMobile);
        item.setBusinessTypeName(this.businessTypeName);
        item.setBusinessTypeId(this.businessTypeId);
        item.setBusinessName(this.businessNameVi);
        item.setBusinessNameEng(this.businessNameEng);
        item.setBusinessNameAlias(this.businessNameAlias);
        item.setBusinessTaxCode(this.businessTaxCode);
        item.setBusinessLicense(this.businessLicense);
        item.setBusinessAddress(this.businessAdd);
        item.setBusinessProvince(this.businessProvince);
        item.setBusinessProvinceId(this.businessProvinceId);
        item.setBusinessTelephone(this.businessTelephone);
        item.setBusinessFax(this.businessFax);
        item.setBusinessWebsite(this.businessWebsite);
        item.setBusinessLawRep(this.businessLawRep);
        item.setDescription(this.description);
        item.setBusinessEstablishYear(this.businessEstablishYear);
        item.setUserEmail(this.userEmail);
        item.setUserName(this.businessTaxCode);
        item.setUserFullname(this.userFullName);
        item.setIsActive(1L);
        //u141213 binhnt53
        item.setGoverningBody(this.governingBody);

        return item;
    }

    public Users convertToUsers(Business bus) {
        Users item = new Users();
        item.setEmail(this.manageEmail);
        item.setTelephone(this.userTelephone);
        item.setCellphone(this.userMobile);
        item.setFax(this.businessFax);
        item.setDescription(this.description);
        item.setFullName(this.userFullName);
        item.setPassword(this.managePassword);
        item.setPasswordchanged(0L);
        item.setEmail(this.userEmail);
        item.setBusinessId(bus.getBusinessId());
        item.setBusinessName(bus.getBusinessName());
        item.setStatus(1L);
        item.setUserName(this.businessTaxCode);
        item.setPosId(this.posId);
        //item.setPosName(this.posName);
        return item;
    }

    public RoleUser convertToRoleUsers() {
        RoleUser item = new RoleUser();
        item.setIsActive(1L);
        item.setIsAdmin(0L);
        item.setUserId(this.registerId);
        item.setRoleUserPK(new RoleUserPK(322, this.registerId));
        return item;
    }

    public String getBusinessAdd() {
        return businessAdd;
    }

    public void setBusinessAdd(String businessAdd) {
        this.businessAdd = businessAdd;
    }

    public String getBusinessEstablishYear() {
        return businessEstablishYear;
    }

    public void setBusinessEstablishYear(String businessEstablishYear) {
        this.businessEstablishYear = businessEstablishYear;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public String getManageEmail() {
        return manageEmail;
    }

    public void setManageEmail(String manageEmail) {
        this.manageEmail = manageEmail;
    }

    public String getUseTelephone() {
        return userTelephone;
    }

    public void setUseTelephone(String useTelephone) {
        this.userTelephone = useTelephone;
    }

    public String getUseMobile() {
        return userMobile;
    }

    public void setUseMobile(String useMobile) {
        this.userMobile = useMobile;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public String getBusinessNameVi() {
        return businessNameVi;
    }

    public void setBusinessNameVi(String businessNameVi) {
        this.businessNameVi = businessNameVi;
    }

    public String getBusinessNameEng() {
        return businessNameEng;
    }

    public void setBusinessNameEng(String businessNameEng) {
        this.businessNameEng = businessNameEng;
    }

    public String getBusinessNameAlias() {
        return businessNameAlias;
    }

    public void setBusinessNameAlias(String businessNameAlias) {
        this.businessNameAlias = businessNameAlias;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessProvince() {
        return businessProvince;
    }

    public void setBusinessProvince(String businessProvince) {
        this.businessProvince = businessProvince;
    }

    public String getBusinessTelephone() {
        return businessTelephone;
    }

    public void setBusinessTelephone(String businessTelephone) {
        this.businessTelephone = businessTelephone;
    }

    public String getBusinessFax() {
        return businessFax;
    }

    public void setBusinessFax(String businessFax) {
        this.businessFax = businessFax;
    }

    public String getBusinessWebsite() {
        return businessWebsite;
    }

    public void setBusinessWebsite(String businessWebsite) {
        this.businessWebsite = businessWebsite;
    }

    public String getBusinessLawRep() {
        return businessLawRep;
    }

    public void setBusinessLawRep(String businessLawRep) {
        this.businessLawRep = businessLawRep;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getManagePassword() {
        return managePassword;
    }

    public void setManagePassword(String managePassword) {
        this.managePassword = managePassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getManageRePassword() {
        return manageRePassword;
    }

    public void setManageRePassword(String manageRePassword) {
        this.manageRePassword = manageRePassword;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getBusinessProvinceId() {
        return businessProvinceId;
    }

    public void setBusinessProvinceId(Long businessProvinceId) {
        this.businessProvinceId = businessProvinceId;
    }

    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getBusinessTaxCode() {
        return businessTaxCode;
    }

    public void setBusinessTaxCode(String businessTaxCode) {
        this.businessTaxCode = businessTaxCode;
    }
//u141213 binhnt53

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getGoverningBody() {
        return governingBody;
    }

    public void setGoverningBody(String governingBody) {
        this.governingBody = governingBody;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

}
