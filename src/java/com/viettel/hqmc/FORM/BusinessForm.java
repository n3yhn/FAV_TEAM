/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.Business;
import java.io.Serializable;

/**
 *
 * @author vtit_binhnt53
 */
public class BusinessForm implements Serializable {

    private Long businessId;
    private String businessTaxCode;
    private String businessName;
    private String businessLicense;
    private String businessAddress;
    private String businessProvince;
    private String userEmail;
    private String userName;
    private String userFullname;
    private String userTelephone;
    private String userMobile;
    private Long businessTypeId;
    private String businessTypeName;
    private String businessNameEng;
    private String businessNameAlias;
    private String businessTelephone;
    private String businessFax;
    private String businessWebsite;
    private String businessLawRep;
    private String description;
    private String businessEstablishYear;
    private Long isActive;
    private Long businessProvinceId;
    private String governingBody;//141214u binhnt53
    public BusinessForm(){
        
    }
    
    public BusinessForm(Business entity) {
        if (entity == null) {
            return;
        }
        businessId = entity.getBusinessId();
        businessTaxCode = entity.getBusinessTaxCode();
        businessName = entity.getBusinessName();
        businessLicense = entity.getBusinessLicense();
        businessAddress = entity.getBusinessAddress();
        businessProvince = entity.getBusinessProvince();
        userEmail = entity.getUserEmail();
        userName = entity.getUserName();
        userFullname = entity.getUserFullname();
        userTelephone = entity.getUserTelephone();
        userMobile = entity.getUserMobile();
        businessTypeId = entity.getBusinessTypeId();
        businessTypeName = entity.getBusinessTypeName();
        businessNameEng = entity.getBusinessNameEng();
        businessNameAlias = entity.getBusinessNameAlias();
        businessTelephone = entity.getBusinessTelephone();
        businessFax = entity.getBusinessFax();
        businessWebsite = entity.getBusinessWebsite();
        businessLawRep = entity.getBusinessLawRep();
        description = entity.getDescription();
        businessEstablishYear = entity.getBusinessEstablishYear();
        isActive = entity.getIsActive();
        businessProvinceId = entity.getBusinessProvinceId();
        governingBody = entity.getGoverningBody();//141214u binhnt53
    }

    public Business convertToEntity() {
        Business entity = new Business();
        entity.setBusinessId(businessId);
        entity.setBusinessTaxCode(businessTaxCode);
        entity.setBusinessName(businessName);
        entity.setBusinessLicense(businessLicense);
        entity.setBusinessAddress(businessAddress);
        entity.setBusinessProvince(businessProvince);
        entity.setUserEmail(userEmail);
        entity.setUserName(userName);
        entity.setUserFullname(userFullname);
        entity.setUserTelephone(userTelephone);
        entity.setUserMobile(userMobile);
        entity.setBusinessTypeId(businessTypeId);
        entity.setBusinessTypeName(businessTypeName);
        entity.setBusinessNameEng(businessNameEng);
        entity.setBusinessNameAlias(businessNameAlias);
        entity.setBusinessTelephone(businessTelephone);
        entity.setBusinessFax(businessFax);
        entity.setBusinessWebsite(businessWebsite);
        entity.setBusinessLawRep(businessLawRep);
        entity.setDescription(description);
        entity.setBusinessEstablishYear(businessEstablishYear);
        entity.setIsActive(isActive);
        entity.setBusinessProvinceId(businessProvinceId);
        entity.setGoverningBody(governingBody);//141214u binhnt53
        return entity;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getBusinessTaxCode() {
        return businessTaxCode;
    }

    public void setBusinessTaxCode(String businessTaxCode) {
        this.businessTaxCode = businessTaxCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessProvince() {
        return businessProvince;
    }

    public void setBusinessProvince(String businessProvince) {
        this.businessProvince = businessProvince;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
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

    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
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

    public String getBusinessEstablishYear() {
        return businessEstablishYear;
    }

    public void setBusinessEstablishYear(String businessEstablishYear) {
        this.businessEstablishYear = businessEstablishYear;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getBusinessProvinceId() {
        return businessProvinceId;
    }

    public void setBusinessProvinceId(Long businessProvinceId) {
        this.businessProvinceId = businessProvinceId;
    }
    //141214u binhnt53

    public String getGoverningBody() {
        return governingBody;
    }

    public void setGoverningBody(String governingBody) {
        this.governingBody = governingBody;
    }
    
}
