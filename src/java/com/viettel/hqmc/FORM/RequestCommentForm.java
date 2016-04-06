/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import java.util.Date;

/**
 *
 * @author GPCP_BINHNT53
 */
public class RequestCommentForm {

    private Long requestComment;
    private Long createBy;
    private Date createDate;
    private String requestType;
    private Long requestTypeId;
    private String content;
    private Long isActive;
    private Long isLastChange;
    private Long version;
    private Long userId;
    private String userName;
    private Long groupId;
    private String groupName;
    private Long status;
    private Long objectId;
    private String objectType;
    private String lastContent;

    public RequestCommentForm(Long requestComment, Long createBy, Date createDate, String requestType, Long requestTypeId, String content, Long isActive, Long isLastChange, Long version, Long userId, String userName, Long groupId, String groupName, Long status, Long objectId, String objectType, String lastContent) {
        this.requestComment = requestComment;
        this.createBy = createBy;
        this.createDate = createDate;
        this.requestType = requestType;
        this.requestTypeId = requestTypeId;
        this.content = content;
        this.isActive = isActive;
        this.isLastChange = isLastChange;
        this.version = version;
        this.userId = userId;
        this.userName = userName;
        this.groupId = groupId;
        this.groupName = groupName;
        this.status = status;
        this.objectId = objectId;
        this.objectType = objectType;
        this.lastContent = lastContent;
    }

    public RequestCommentForm() {
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

    public String getLastContent() {
        return lastContent;
    }

    public void setLastContent(String lastContent) {
        this.lastContent = lastContent;
    }

}
