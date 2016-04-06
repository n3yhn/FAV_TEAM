/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.voffice.database.BO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GPCP_BINHNT53
 */
@Entity
@Table(name = "PROCESS_COMMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcessComment.findAll", query = "SELECT p FROM ProcessComment p"),
    @NamedQuery(name = "ProcessComment.findByProcessCommentId", query = "SELECT p FROM ProcessComment p WHERE p.processCommentId = :processCommentId"),
    @NamedQuery(name = "ProcessComment.findByProcessId", query = "SELECT p FROM ProcessComment p WHERE p.processId = :processId"),
    @NamedQuery(name = "ProcessComment.findByCommentType", query = "SELECT p FROM ProcessComment p WHERE p.commentType = :commentType"),
    @NamedQuery(name = "ProcessComment.findByUserId", query = "SELECT p FROM ProcessComment p WHERE p.userId = :userId"),
    @NamedQuery(name = "ProcessComment.findByUserName", query = "SELECT p FROM ProcessComment p WHERE p.userName = :userName"),
    @NamedQuery(name = "ProcessComment.findByGroupId", query = "SELECT p FROM ProcessComment p WHERE p.groupId = :groupId"),
    @NamedQuery(name = "ProcessComment.findByGroupName", query = "SELECT p FROM ProcessComment p WHERE p.groupName = :groupName"),
    @NamedQuery(name = "ProcessComment.findByCreatedBy", query = "SELECT p FROM ProcessComment p WHERE p.createdBy = :createdBy"),
    @NamedQuery(name = "ProcessComment.findByCreatedDate", query = "SELECT p FROM ProcessComment p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "ProcessComment.findByModifiedBy", query = "SELECT p FROM ProcessComment p WHERE p.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "ProcessComment.findByModifiedDate", query = "SELECT p FROM ProcessComment p WHERE p.modifiedDate = :modifiedDate"),
    @NamedQuery(name = "ProcessComment.findByStatus", query = "SELECT p FROM ProcessComment p WHERE p.status = :status"),
    @NamedQuery(name = "ProcessComment.findByIsActive", query = "SELECT p FROM ProcessComment p WHERE p.isActive = :isActive"),
    @NamedQuery(name = "ProcessComment.findByObjectId", query = "SELECT p FROM ProcessComment p WHERE p.objectId = :objectId"),
    @NamedQuery(name = "ProcessComment.findByObjectType", query = "SELECT p FROM ProcessComment p WHERE p.objectType = :objectType"),
    @NamedQuery(name = "ProcessComment.findByVersion", query = "SELECT p FROM ProcessComment p WHERE p.version = :version"),
    @NamedQuery(name = "ProcessComment.findByLastIsTemp", query = "SELECT p FROM ProcessComment p WHERE p.lastIsTemp = :lastIsTemp")})
public class ProcessComment implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "PROCESS_COMMENT_SEQ", sequenceName = "PROCESS_COMMENT_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROCESS_COMMENT_SEQ")
    @Basic(optional = false)
    @Column(name = "PROCESS_COMMENT_ID")
    private Long processCommentId;
    @Column(name = "PROCESS_ID")
    private Long processId;
    @Column(name = "COMMENT_TYPE")
    private Long commentType;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "GROUP_ID")
    private Long groupId;
    @Column(name = "GROUP_NAME")
    private String groupName;
    @Column(name = "CREATED_BY")
    private Long createdBy;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "OBJECT_ID")
    private Long objectId;
    @Column(name = "OBJECT_TYPE")
    private Long objectType;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "LAST_IS_TEMP")
    private Long lastIsTemp;

    public ProcessComment() {
    }

    public ProcessComment(Long processCommentId) {
        this.processCommentId = processCommentId;
    }

    public Long getProcessCommentId() {
        return processCommentId;
    }

    public void setProcessCommentId(Long processCommentId) {
        this.processCommentId = processCommentId;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Long getCommentType() {
        return commentType;
    }

    public void setCommentType(Long commentType) {
        this.commentType = commentType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getObjectType() {
        return objectType;
    }

    public void setObjectType(Long objectType) {
        this.objectType = objectType;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (processCommentId != null ? processCommentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcessComment)) {
            return false;
        }
        ProcessComment other = (ProcessComment) object;
        if ((this.processCommentId == null && other.processCommentId != null) || (this.processCommentId != null && !this.processCommentId.equals(other.processCommentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.voffice.database.BO.ProcessComment[ processCommentId=" + processCommentId + " ]";
    }
    
}
