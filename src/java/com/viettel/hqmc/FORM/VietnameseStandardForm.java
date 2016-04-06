/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.StandardProduct;
import com.viettel.hqmc.BO.VietnameseStandard;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vtit_binhnt53
 */
public class VietnameseStandardForm implements Serializable {

    private Long vietnameseStandardId;
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
    private String publishAgencyId;
    private StandardProduct standardProduct;
    private Date publishDateFrom, publishDateTo;
    private Date effectiveDateFrom, effectiveDateTo;
    private Date expireDateFrom, expireDateTo;
    private String uploadId;
    /*
     * 
     */
    public VietnameseStandardForm() {
    }

    public VietnameseStandardForm(VietnameseStandard entity) {
        if (entity == null) {
            return;
        } else {
            this.applicationObject = entity.getApplicationObject();
            this.effectiveDate = entity.getEffectiveDate();
            this.englishName = entity.getEnglishName();
            this.expireDate = entity.getExpireDate();
            this.isActive = entity.getIsActive();
            this.publishAgencyId = entity.getPublishAgencyId();
            this.publishAgencyName = entity.getPublishAgencyName();
            this.publishDate = entity.getPublishDate();
            this.scope = entity.getScope();
            this.standardCode = entity.getStandardCode();
            this.standardProduct = entity.getStandardProduct();
            this.standardType = entity.getStandardType();
            this.summary = entity.getSummary();
            this.vietnameseName = entity.getVietnameseName();
            this.vietnameseStandardId = entity.getVietnameseStandardId();
        }

    }

    public VietnameseStandard convertToEntity() {
        VietnameseStandard item = new VietnameseStandard();

        item.setApplicationObject(this.applicationObject);
        item.setEffectiveDate(this.effectiveDate);
        item.setEnglishName(this.englishName);
        item.setExpireDate(this.expireDate);
        item.setIsActive(this.isActive);
        item.setPublishAgencyId(this.publishAgencyId);
        item.setPublishAgencyName(this.publishAgencyName);
        item.setPublishDate(this.publishDate);
        item.setScope(this.scope);
        item.setStandardCode(this.standardCode);
        item.setStandardProduct(this.standardProduct);
        item.setStandardType(this.standardType);
        item.setSummary(this.summary);
        item.setVietnameseName(this.vietnameseName);
        if (this.vietnameseStandardId != null) {
            item.setVietnameseStandardId(this.vietnameseStandardId);
        }
        return item;
    }

    public Long getVietnameseStandardId() {
        return vietnameseStandardId;
    }

    public void setVietnameseStandardId(Long vietnameseStandardId) {
        this.vietnameseStandardId = vietnameseStandardId;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getVietnameseName() {
        return vietnameseName;
    }

    public void setVietnameseName(String vietnameseName) {
        this.vietnameseName = vietnameseName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getApplicationObject() {
        return applicationObject;
    }

    public void setApplicationObject(String applicationObject) {
        this.applicationObject = applicationObject;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Long getStandardType() {
        return standardType;
    }

    public void setStandardType(Long standardType) {
        this.standardType = standardType;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public String getPublishAgencyName() {
        return publishAgencyName;
    }

    public void setPublishAgencyName(String publishAgencyName) {
        this.publishAgencyName = publishAgencyName;
    }

    public String getPublishAgencyId() {
        return publishAgencyId;
    }

    public void setPublishAgencyId(String publishAgencyId) {
        this.publishAgencyId = publishAgencyId;
    }

    public StandardProduct getStandardProduct() {
        return standardProduct;
    }

    public void setStandardProduct(StandardProduct standardProduct) {
        this.standardProduct = standardProduct;
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
    
}
