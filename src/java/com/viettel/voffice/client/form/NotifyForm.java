/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

import java.util.Date;

/**
 *
 * @author DiuLTT
 */
public class NotifyForm {
    private Long notifyId;
    private Long notifyType;
    private Long objectId;
    private Long objectType;
    private Long notifyOfficeId;
    private String notifyOfficeIdStr;
    private String notifyOfficeName;
    private Long notifierId;
    private String notifierName;
    private String notifyContent;
    private Date notifyDate;
    private Long modifiedBy;
    private Date modifiedDate;
    private Long isActive;
    private Long notifyStatus;
    private Date fromDate;
    private Date toDate;
    private String notifyStatusStr;

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

    public Long getNotifierId() {
        return notifierId;
    }

    public void setNotifierId(Long notifierId) {
        this.notifierId = notifierId;
    }

    public String getNotifierName() {
        return notifierName;
    }

    public void setNotifierName(String notifierName) {
        this.notifierName = notifierName;
    }

    public String getNotifyContent() {
        return notifyContent;
    }

    public void setNotifyContent(String notifyContent) {
        this.notifyContent = notifyContent;
    }

    public Date getNotifyDate() {
        return notifyDate;
    }

    public void setNotifyDate(Date notifyDate) {
        this.notifyDate = notifyDate;
    }

    public Long getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
    }

    public Long getNotifyOfficeId() {
        return notifyOfficeId;
    }

    public void setNotifyOfficeId(Long notifyOfficeId) {
        this.notifyOfficeId = notifyOfficeId;
    }

    public String getNotifyOfficeName() {
        return notifyOfficeName;
    }

    public void setNotifyOfficeName(String notifyOfficeName) {
        this.notifyOfficeName = notifyOfficeName;
    }

    public Long getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(Long notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    public Long getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(Long notifyType) {
        this.notifyType = notifyType;
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

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getNotifyStatusStr() {
        return notifyStatusStr;
    }

    public void setNotifyStatusStr(String notifyStatusStr) {
        this.notifyStatusStr = notifyStatusStr;
    }

    public String getNotifyOfficeIdStr() {
        return notifyOfficeIdStr;
    }

    public void setNotifyOfficeIdStr(String notifyOfficeIdStr) {
        this.notifyOfficeIdStr = notifyOfficeIdStr;
    }
}
