/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author DiuLTT
 */
public class ProcessCommentForm implements Serializable {

    private Long processCommentId;
    private Long processId;
    private String commentText;
    private Long commentType;
    private Long userId;
    private String userName;
    private Long groupId;
    private String groupName;
    private Long createdBy;
    private Date createdDate;
    private Long modifiedBy;
    private Date modifiedDate;
    private Long status;
    private Long isActive;
    //
    // addition atribute
    //
    private Long objectId;
    private Long objectType;
    
    private String nextPerson;
    private String nextDept;
    private Long nextPersonId;
    private Long nextDeptId;
    private String attachIds;
    private String attachFileLinks;
    private Long editable;
    private Long version;
    private String legal;
    private String foodSafetyQuality;
    private String effectUtility;
    
    public ProcessCommentForm() {
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Long getCommentType() {
        return commentType;
    }

    public void setCommentType(Long commentType) {
        this.commentType = commentType;
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

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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


    public String getNextDept() {
        return nextDept;
    }

    public void setNextDept(String nextDept) {
        this.nextDept = nextDept;
    }

    public String getNextPerson() {
        return nextPerson;
    }

    public void setNextPerson(String nextPerson) {
        this.nextPerson = nextPerson;
    }

    public Long getNextDeptId() {
        return nextDeptId;
    }

    public void setNextDeptId(Long nextDeptId) {
        this.nextDeptId = nextDeptId;
    }

    public Long getNextPersonId() {
        return nextPersonId;
    }

    public void setNextPersonId(Long nextPersonId) {
        this.nextPersonId = nextPersonId;
    }

    public String getAttachIds() {
        return attachIds;
    }

    public void setAttachIds(String attachIds) {
        this.attachIds = attachIds;
    }

    public String getAttachFileLinks() {
        return attachFileLinks;
    }

    public void setAttachFileLinks(String attachFileLinks) {
        this.attachFileLinks = attachFileLinks;
    }

    public Long getEditable() {
        return editable;
    }

    public void setEditable(Long editable) {
        this.editable = editable;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    public String getFoodSafetyQuality() {
        return foodSafetyQuality;
    }

    public void setFoodSafetyQuality(String foodSafetyQuality) {
        this.foodSafetyQuality = foodSafetyQuality;
    }

    public String getEffectUtility() {
        return effectUtility;
    }

    public void setEffectUtility(String effectUtility) {
        this.effectUtility = effectUtility;
    }
    
}
