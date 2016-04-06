/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.common.util.DateTimeUtils;
import com.viettel.hqmc.BO.Announcement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vtit_havm2
 */
public class AnnouncementForm implements Serializable {

    private Long announcementId;
    private String announcementNo;
    private String businessName;
    private String businessLicence;
    private String businessAddress;
    private String businessTelephone;
    private String businessFax;
    private String businessEmail;
    private String productName;
    private String manufactureName;
    private String manufactureAddress;
    private String manufactureTel;
    private String manufactureFax;
    private String manufactureEmail;
    private String nationName;
    private String matchingTarget;
    private String assessmentMethod;
    private Date publishDate;
    private String signer;
    private Long isActive;
    private String signDateStr;
    private String nationCompanyName;
    private String nationCompanyAddress;
    private Long version;
    private Long lastIsTemp;

    public AnnouncementForm() {
    }

    public AnnouncementForm(Announcement entity) {
        if (entity == null) {
            return;
        }
        announcementId = entity.getAnnouncementId();
        announcementNo = entity.getAnnouncementNo();
        businessName = entity.getBusinessName();
        businessLicence = entity.getBusinessLicence();
        businessAddress = entity.getBusinessAddress();
        businessTelephone = entity.getBusinessTelephone();
        businessFax = entity.getBusinessFax();
        businessEmail = entity.getBusinessEmail();
        productName = entity.getProductName();
        manufactureName = entity.getManufactureName();
        manufactureAddress = entity.getManufactureAddress();
        manufactureTel = entity.getManufactureTel();
        manufactureFax = entity.getManufactureFax();
        manufactureEmail = entity.getManufactureEmail();
        nationName = entity.getNationName();
        matchingTarget = entity.getMatchingTarget();
        assessmentMethod = entity.getAssessmentMethod();
        publishDate = entity.getPublishDate();
        signer = entity.getSigner();
        isActive = entity.getIsActive();
        nationCompanyAddress = entity.getNationCompanyAddress();
        nationCompanyName = entity.getNationCompanyName();
        version = entity.getVersion();
        lastIsTemp = entity.getLastIsTemp();
    }

    public Announcement convertToEntity() {
        Announcement entity = new Announcement();
        if (announcementId != null) {
            entity.setAnnouncementId(announcementId);
        }
        if (announcementNo != null) {
            entity.setAnnouncementNo(announcementNo.trim());
        }
        if (businessName != null) {
            entity.setBusinessName(businessName.trim());
        }
        if (businessLicence != null) {
            entity.setBusinessLicence(businessLicence.trim());
        }
        if (businessAddress != null) {
            entity.setBusinessAddress(businessAddress.trim());
        }
        if (businessTelephone != null) {
            entity.setBusinessTelephone(businessTelephone.trim());
        }
        if (businessFax != null) {
            entity.setBusinessFax(businessFax.trim());
        }
        if (businessEmail != null) {
            entity.setBusinessEmail(businessEmail.trim());
        }
        if (productName != null) {
            entity.setProductName(productName.trim());
        }
        if (manufactureName != null) {
            entity.setManufactureName(manufactureName.trim());
        }
        if (manufactureAddress != null) {
            entity.setManufactureAddress(manufactureAddress.trim());
        }
        if (manufactureTel != null) {
            entity.setManufactureTel(manufactureTel.trim());
        }
        if (manufactureFax != null) {
            entity.setManufactureFax(manufactureFax.trim());
        }
        if (manufactureEmail != null) {
            entity.setManufactureEmail(manufactureEmail.trim());
        }
        if (nationName != null) {
            entity.setNationName(nationName.trim());
        }
        if (matchingTarget != null) {
            entity.setMatchingTarget(matchingTarget.trim());
        }
        if (assessmentMethod != null) {
            entity.setAssessmentMethod(assessmentMethod.trim());
        }
        entity.setPublishDate(publishDate);
        if (signer != null) {
            entity.setSigner(signer.trim());
        }
        if (isActive == null) {
            isActive = 1l;
        }
        entity.setIsActive(isActive);
        entity.setNationCompanyAddress(nationCompanyAddress);
        entity.setNationCompanyName(nationCompanyName);
        return entity;
    }

    public Long getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Long announcementId) {
        this.announcementId = announcementId;
    }

    public String getAnnouncementNo() {
        return announcementNo;
    }

    public void setAnnouncementNo(String announcementNo) {
        this.announcementNo = announcementNo;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
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

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufactureName() {
        return manufactureName;
    }

    public void setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
    }

    public String getManufactureAddress() {
        return manufactureAddress;
    }

    public void setManufactureAddress(String manufactureAddress) {
        this.manufactureAddress = manufactureAddress;
    }

    public String getManufactureTel() {
        return manufactureTel;
    }

    public void setManufactureTel(String manufactureTel) {
        this.manufactureTel = manufactureTel;
    }

    public String getManufactureFax() {
        return manufactureFax;
    }

    public void setManufactureFax(String manufactureFax) {
        this.manufactureFax = manufactureFax;
    }

    public String getManufactureEmail() {
        return manufactureEmail;
    }

    public void setManufactureEmail(String manufactureEmail) {
        this.manufactureEmail = manufactureEmail;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getMatchingTarget() {
        return matchingTarget;
    }

    public void setMatchingTarget(String matchingTarget) {
        this.matchingTarget = matchingTarget;
    }

    public String getAssessmentMethod() {
        return assessmentMethod;
    }

    public void setAssessmentMethod(String assessmentMethod) {
        this.assessmentMethod = assessmentMethod;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishDateStr() {
        if (publishDate == null) {
            return "";
        }
        return DateTimeUtils.convertDateToString(publishDate, "dd/MM/yyyy");
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    //binhnt 53fomat signdate
    public String getSignDateStr() {
        return signDateStr;
    }

    public void setSignDateStr(String signDateStr) {
        this.signDateStr = signDateStr;
    }

    //!binhnt53
    // 30.06.14 hieptq 
    public String getNationCompanyName() {
        return nationCompanyName;
    }

    public void setNationCompanyName(String nationCompanyName) {
        this.nationCompanyName = nationCompanyName;
    }

    public String getNationCompanyAddress() {
        return nationCompanyAddress;
    }

    public void setNationCompanyAddress(String nationCompanyAddress) {
        this.nationCompanyAddress = nationCompanyAddress;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getLastIsTemp() {
        return lastIsTemp;
    }

    public void setLastIsTemp(Long lastIsTemp) {
        this.lastIsTemp = lastIsTemp;
    }
}
