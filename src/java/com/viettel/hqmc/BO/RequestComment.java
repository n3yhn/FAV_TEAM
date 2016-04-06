/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

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
 * @author GPCP_BINHNT53
 */
@Entity
@Table(name = "REQUEST_COMMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RequestComment.findAll", query = "SELECT r FROM RequestComment r"),
    @NamedQuery(name = "RequestComment.findByRequestComment", query = "SELECT r FROM RequestComment r WHERE r.requestComment = :requestComment"),
    @NamedQuery(name = "RequestComment.findByCreateBy", query = "SELECT r FROM RequestComment r WHERE r.createBy = :createBy"),
    @NamedQuery(name = "RequestComment.findByCreateDate", query = "SELECT r FROM RequestComment r WHERE r.createDate = :createDate"),
    @NamedQuery(name = "RequestComment.findByRequestType", query = "SELECT r FROM RequestComment r WHERE r.requestType = :requestType"),
    @NamedQuery(name = "RequestComment.findByRequestTypeId", query = "SELECT r FROM RequestComment r WHERE r.requestTypeId = :requestTypeId"),
    @NamedQuery(name = "RequestComment.findByContent", query = "SELECT r FROM RequestComment r WHERE r.content = :content"),
    @NamedQuery(name = "RequestComment.findByIsActive", query = "SELECT r FROM RequestComment r WHERE r.isActive = :isActive"),
    @NamedQuery(name = "RequestComment.findByIsLastChange", query = "SELECT r FROM RequestComment r WHERE r.isLastChange = :isLastChange"),
    @NamedQuery(name = "RequestComment.findByVersion", query = "SELECT r FROM RequestComment r WHERE r.version = :version"),
    @NamedQuery(name = "RequestComment.findByUserId", query = "SELECT r FROM RequestComment r WHERE r.userId = :userId"),
    @NamedQuery(name = "RequestComment.findByUserName", query = "SELECT r FROM RequestComment r WHERE r.userName = :userName"),
    @NamedQuery(name = "RequestComment.findByGroupId", query = "SELECT r FROM RequestComment r WHERE r.groupId = :groupId"),
    @NamedQuery(name = "RequestComment.findByGroupName", query = "SELECT r FROM RequestComment r WHERE r.groupName = :groupName"),
    @NamedQuery(name = "RequestComment.findByStatus", query = "SELECT r FROM RequestComment r WHERE r.status = :status"),
    @NamedQuery(name = "RequestComment.findByObjectId", query = "SELECT r FROM RequestComment r WHERE r.objectId = :objectId"),
    @NamedQuery(name = "RequestComment.findByObjectType", query = "SELECT r FROM RequestComment r WHERE r.objectType = :objectType")})
public class RequestComment implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "REQUEST_COMMENT_SEQ", sequenceName = "REQUEST_COMMENT_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REQUEST_COMMENT_SEQ")
    @Basic(optional = false)
    @Column(name = "REQUEST_COMMENT")
    private Long requestComment;
    @Column(name = "CREATE_BY")
    private Long createBy;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "REQUEST_TYPE")
    private String requestType;
    @Column(name = "REQUEST_TYPE_ID")
    private Long requestTypeId;
    @Column(name = "CONTENT")
    private String content;
    @Basic(optional = false)
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "IS_LAST_CHANGE")
    private Long isLastChange;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "GROUP_ID")
    private Long groupId;
    @Column(name = "GROUP_NAME")
    private String groupName;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "OBJECT_ID")
    private Long objectId;
    @Column(name = "OBJECT_TYPE")
    private String objectType;
    @Column(name = "LAST_CONTENT")
    private String lastContent;

    public RequestComment() {
    }

    public RequestComment(Long requestComment) {
        this.requestComment = requestComment;
    }

    public RequestComment(Long requestComment, Long isActive) {
        this.requestComment = requestComment;
        this.isActive = isActive;
    }

    public Long getRequestComment() {
        return requestComment;
    }

    public void setRequestComment(Long requestComment) {
        this.requestComment = requestComment;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Long getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Long requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getIsLastChange() {
        return isLastChange;
    }

    public void setIsLastChange(Long isLastChange) {
        this.isLastChange = isLastChange;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestComment != null ? requestComment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequestComment)) {
            return false;
        }
        RequestComment other = (RequestComment) object;
        if ((this.requestComment == null && other.requestComment != null) || (this.requestComment != null && !this.requestComment.equals(other.requestComment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.RequestComment[ requestComment=" + requestComment + " ]";
    }

    public String getLastContent() {
        return lastContent;
    }

    public void setLastContent(String lastContent) {
        this.lastContent = lastContent;
    }

}
