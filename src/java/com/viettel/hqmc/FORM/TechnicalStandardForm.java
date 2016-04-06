/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import java.util.Date;
import com.viettel.hqmc.BO.TechnicalStandard;
import java.io.Serializable;

/**
 *
 * @author vtit_binhnt53
 */
public class TechnicalStandardForm implements Serializable {
    //properties

    private Long technicalStandardId;
    private String standardCode;
    private String englishName;
    private String vietnameseName;
    private String summary;
    private String applicationObject;
    private Date publishDate;
    private Date effectiveDate;
    private Date expireDate;
    private String scope;
    private Long standardType;
    private Long isActive;
    private String publishAgencyName;
    private Date publishDateFrom, publishDateTo;
    private Date effectiveDateFrom, effectiveDateTo;
    private Date expireDateFrom, expireDateTo;
    private String uploadId;
    private Long status;
    public TechnicalStandardForm() {
    }
       
    public TechnicalStandardForm(TechnicalStandard entity) {
        if (entity == null) {
            return;
        } else {
            this.applicationObject = entity.getApplicationObject();
        }
        this.effectiveDate = entity.getEffectiveDate();
        this.englishName = entity.getEnglishName();
        this.expireDate = entity.getExpireDate();
        this.isActive = entity.getIsActive();
        this.publishAgencyName = entity.getPublishAgencyName();
        this.publishDate = entity.getPublishDate();
        this.scope = entity.getScope();
        this.standardCode = entity.getStandardCode();
        this.standardType = entity.getStandardType();
        this.summary = entity.getSummary();
        this.technicalStandardId = entity.getTechnicalStandardId();
        this.vietnameseName = entity.getVietnameseName();
        this.status = entity.getStatus();
    }

    public TechnicalStandard convertToEntity() {
        TechnicalStandard item = new TechnicalStandard();
        if (this.technicalStandardId != null) {
            item.setTechnicalStandardId(this.technicalStandardId);
        }
        item.setApplicationObject(this.applicationObject);
        item.setEffectiveDate(this.effectiveDate);
        item.setEnglishName(this.englishName);
        item.setExpireDate(this.expireDate);
        item.setIsActive(this.isActive);
        item.setPublishAgencyName(this.publishAgencyName);
        item.setPublishDate(this.publishDate);
        item.setScope(this.scope);
        item.setStandardCode(this.standardCode);
        item.setStandardType(this.standardType);
        item.setSummary(this.summary);
        item.setVietnameseName(this.vietnameseName);
        item.setStatus(this.status);
        return item;
    }

    /**
     * @return the technicalStandardId
     */
    public Long getTechnicalStandardId() {
        return technicalStandardId;
    }

    /**
     * @param technicalStandardId the technicalStandardId to set
     */
    public void setTechnicalStandardId(Long technicalStandardId) {
        this.technicalStandardId = technicalStandardId;
    }

    /**
     * @return the standardCode
     */
    public String getStandardCode() {
        return standardCode;
    }

    /**
     * @param standardCode the standardCode to set
     */
    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    /**
     * @return the englishName
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * @param englishName the englishName to set
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    /**
     * @return the vietnameseName
     */
    public String getVietnameseName() {
        return vietnameseName;
    }

    /**
     * @param vietnameseName the vietnameseName to set
     */
    public void setVietnameseName(String vietnameseName) {
        this.vietnameseName = vietnameseName;
    }

    /**
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the applicationObject
     */
    public String getApplicationObject() {
        return applicationObject;
    }

    /**
     * @param applicationObject the applicationObject to set
     */
    public void setApplicationObject(String applicationObject) {
        this.applicationObject = applicationObject;
    }

    /**
     * @return the publishDate
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * @param publishDate the publishDate to set
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * @return the effectiveDate
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * @param effectiveDate the effectiveDate to set
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * @return the expireDate
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * @param expireDate the expireDate to set
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * @return the scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * @return the standardType
     */
    public Long getStandardType() {
        return standardType;
    }

    /**
     * @param standardType the standardType to set
     */
    public void setStandardType(Long standardType) {
        this.standardType = standardType;
    }

    /**
     * @return the isActive
     */
    public Long getIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the publishAgencyName
     */
    public String getPublishAgencyName() {
        return publishAgencyName;
    }

    /**
     * @param publishAgencyName the publishAgencyName to set
     */
    public void setPublishAgencyName(String publishAgencyName) {
        this.publishAgencyName = publishAgencyName;
    }

    public Date getPublishDateFrom() {
        return publishDateFrom;
    }

    public void setPublishDateFrom(Date publishDateFrom) {
        this.publishDateFrom = publishDateFrom;
    }

    public Date getPublishDateTo() {
        return publishDateTo;
    }

    public void setPublishDateTo(Date publishDateTo) {
        this.publishDateTo = publishDateTo;
    }

    public Date getEffectiveDateFrom() {
        return effectiveDateFrom;
    }

    public void setEffectiveDateFrom(Date effectiveDateFrom) {
        this.effectiveDateFrom = effectiveDateFrom;
    }

    public Date getEffectiveDateTo() {
        return effectiveDateTo;
    }

    public void setEffectiveDateTo(Date effectiveDateTo) {
        this.effectiveDateTo = effectiveDateTo;
    }

    public Date getExpireDateFrom() {
        return expireDateFrom;
    }

    public void setExpireDateFrom(Date expireDateFrom) {
        this.expireDateFrom = expireDateFrom;
    }

    public Date getExpireDateTo() {
        return expireDateTo;
    }

    public void setExpireDateTo(Date expireDateTo) {
        this.expireDateTo = expireDateTo;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
    
}
