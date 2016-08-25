/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author binhnt53
 */
@Entity
@Table(name = "USER_ATTACHS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAttachs.findAll", query = "SELECT u FROM UserAttachs u"),
    @NamedQuery(name = "UserAttachs.findByUserAttachsId", query = "SELECT u FROM UserAttachs u WHERE u.userAttachsId = :userAttachsId"),
    @NamedQuery(name = "UserAttachs.findByAttachName", query = "SELECT u FROM UserAttachs u WHERE u.attachName = :attachName"),
    @NamedQuery(name = "UserAttachs.findByDescription", query = "SELECT u FROM UserAttachs u WHERE u.description = :description"),
    @NamedQuery(name = "UserAttachs.findByEffectiveDate", query = "SELECT u FROM UserAttachs u WHERE u.effectiveDate = :effectiveDate"),
    @NamedQuery(name = "UserAttachs.findByExpireDate", query = "SELECT u FROM UserAttachs u WHERE u.expireDate = :expireDate"),
    @NamedQuery(name = "UserAttachs.findByPublishDate", query = "SELECT u FROM UserAttachs u WHERE u.publishDate = :publishDate"),
    @NamedQuery(name = "UserAttachs.findByIsActive", query = "SELECT u FROM UserAttachs u WHERE u.isActive = :isActive"),
    @NamedQuery(name = "UserAttachs.findByCreatedBy", query = "SELECT u FROM UserAttachs u WHERE u.createdBy = :createdBy"),
    @NamedQuery(name = "UserAttachs.findByCreateDate", query = "SELECT u FROM UserAttachs u WHERE u.createDate = :createDate"),
    @NamedQuery(name = "UserAttachs.findByModifiedBy", query = "SELECT u FROM UserAttachs u WHERE u.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "UserAttachs.findByModifyDate", query = "SELECT u FROM UserAttachs u WHERE u.modifyDate = :modifyDate"),
    @NamedQuery(name = "UserAttachs.findByAttachPath", query = "SELECT u FROM UserAttachs u WHERE u.attachPath = :attachPath"),
    @NamedQuery(name = "UserAttachs.findByObjectType", query = "SELECT u FROM UserAttachs u WHERE u.objectType = :objectType")})
public class UserAttachs implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "USER_ATTACHS_SEQ", sequenceName = "USER_ATTACHS_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ATTACHS_SEQ")
    @Basic(optional = false)
    @Column(name = "USER_ATTACHS_ID")
    private Long userAttachsId;
    @Column(name = "ATTACH_NAME")
    private String attachName;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "EFFECTIVE_DATE")
    @Temporal(TemporalType.DATE)
    private Date effectiveDate;
    @Column(name = "EXPIRE_DATE")
    @Temporal(TemporalType.DATE)
    private Date expireDate;
    @Column(name = "PUBLISH_DATE")
    @Temporal(TemporalType.DATE)
    private Date publishDate;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "CREATED_BY")
    private Long createdBy;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;
    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;
    @Column(name = "ATTACH_PATH")
    private String attachPath;
    @Column(name = "OBJECT_TYPE")
    private String objectType;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "FILE_NAME")
    private String fileName;

    public UserAttachs() {
    }

    public UserAttachs(Long userAttachsId) {
        this.userAttachsId = userAttachsId;
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
        this.attachName = StringUtils.removeEventHandlerJS(attachName);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = StringUtils.removeEventHandlerJS(description);
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
        this.attachPath = StringUtils.removeEventHandlerJS(attachPath);
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = StringUtils.removeEventHandlerJS(objectType);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userAttachsId != null ? userAttachsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAttachs)) {
            return false;
        }
        UserAttachs other = (UserAttachs) object;
        if ((this.userAttachsId == null && other.userAttachsId != null) || (this.userAttachsId != null && !this.userAttachsId.equals(other.userAttachsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.UserAttachs[ userAttachsId=" + userAttachsId + " ]";
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = StringUtils.removeEventHandlerJS(fileName);
    }

}
