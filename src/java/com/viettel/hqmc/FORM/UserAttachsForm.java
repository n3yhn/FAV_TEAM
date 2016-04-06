/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.UserAttachs;
import java.util.Date;

/**
 *
 * @author binhnt53
 */
public class UserAttachsForm {

    private Long userAttachsId;
    private String attachName;
    private String description;
    private Date effectiveDate;
    private Date effectiveDateFrom;
    private Date effectiveDateTo;
    private Date expireDate;
    private Date expireDateFrom;
    private Date expireDateTo;
    private Date publishDate;
    private Date publishDateFrom;
    private Date publishDateTo;
    private Long isActive;
    private Long createdBy;
    private Date createDate;
    private Long modifiedBy;
    private Date modifyDate;
    private String attachPath;
    private String objectType;
    private Long status;
    private String uploadId;
    private String fileName;

    public UserAttachsForm() {
    }

    public UserAttachsForm(UserAttachs bo) {
        this.userAttachsId = bo.getUserAttachsId();
        this.attachName = bo.getAttachName();
        this.description = bo.getDescription();
        this.effectiveDate = bo.getEffectiveDate();
        this.expireDate = bo.getExpireDate();
        this.publishDate = bo.getPublishDate();
        this.isActive = bo.getIsActive();
        this.createdBy = bo.getCreatedBy();
        this.createDate = bo.getCreateDate();
        this.modifiedBy = bo.getModifiedBy();
        this.modifyDate = bo.getModifyDate();
        this.attachPath = bo.getAttachPath();
        this.objectType = bo.getObjectType();
        this.status = bo.getStatus();
//        this.uploadId = bo.getUploadId();
        this.fileName = bo.getFileName();
    }

    public UserAttachsForm(Long userAttachsId, String attachName, String description, Date effectiveDate, Date effectiveDateFrom, Date effectiveDateTo, Date expireDate, Date expireDateFrom, Date expireDateTo, Date publishDate, Date publishDateFrom, Date publishDateTo, Long isActive, Long createdBy, Date createDate, Long modifiedBy, Date modifyDate, String attachPath, String objectType, Long status, String uploadId, String fileName) {
        this.userAttachsId = userAttachsId;
        this.attachName = attachName;
        this.description = description;
        this.effectiveDate = effectiveDate;
        this.effectiveDateFrom = effectiveDateFrom;
        this.effectiveDateTo = effectiveDateTo;
        this.expireDate = expireDate;
        this.expireDateFrom = expireDateFrom;
        this.expireDateTo = expireDateTo;
        this.publishDate = publishDate;
        this.publishDateFrom = publishDateFrom;
        this.publishDateTo = publishDateTo;
        this.isActive = isActive;
        this.createdBy = createdBy;
        this.createDate = createDate;
        this.modifiedBy = modifiedBy;
        this.modifyDate = modifyDate;
        this.attachPath = attachPath;
        this.objectType = objectType;
        this.status = status;
        this.uploadId = uploadId;
        this.fileName = fileName;
    }

    public UserAttachsForm(Long userAttachsId, String attachName, String description, Date effectiveDate, Date expireDate, Date publishDate, Long isActive, Long createdBy, Date createDate, Long modifiedBy, Date modifyDate, String attachPath, String objectType, Long status, String fileName) {
        this.userAttachsId = userAttachsId;
        this.attachName = attachName;
        this.description = description;
        this.effectiveDate = effectiveDate;
        this.expireDate = expireDate;
        this.publishDate = publishDate;
        this.isActive = isActive;
        this.createdBy = createdBy;
        this.createDate = createDate;
        this.modifiedBy = modifiedBy;
        this.modifyDate = modifyDate;
        this.attachPath = attachPath;
        this.objectType = objectType;
        this.status = status;
        this.fileName = fileName;
    }

    public UserAttachs convertToEntity() {
        UserAttachs bo = new UserAttachs();
        if (userAttachsId != null) {
            bo.setUserAttachsId(userAttachsId);
        }
        bo.setAttachName(attachName);
        bo.setAttachPath(attachPath);
        bo.setCreateDate(createDate);
        bo.setCreatedBy(createdBy);
        bo.setDescription(description);
        bo.setEffectiveDate(effectiveDate);
        bo.setExpireDate(expireDate);
        bo.setIsActive(isActive);
        bo.setModifiedBy(modifiedBy);
        bo.setModifyDate(modifyDate);
        bo.setObjectType(objectType);
        bo.setPublishDate(publishDate);
        bo.setStatus(status);
        bo.setUserAttachsId(userAttachsId);
        bo.setFileName(fileName);
        return bo;
    }

    public Long getUserAttachsId() {
        return userAttachsId;
    }

    public void setUserAttachsId(Long userAttachsId) {
        this.userAttachsId = userAttachsId;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
