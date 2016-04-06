/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author havm2
 */
public class ProcessForm implements Serializable {
    private Long processId;
    private Long processStatusId;
    private Long processStatus;
    private Long objectId;
    private Long objectType;
    private Long sendUserId;
    private String sendUser;
    private Long sendGroupId;
    private String sendGroup;
    private Date sendDate;
    private Long receiveUserId;
    private String receiveUser;
    private Long receiveGroupId;
    private String receiveGroup;
    private Date receiveDate;
    private Long processType;
    private Long orderProcess;
    private Long status;
    private Date deadline;
    private Long deadlineNumber;
    private Long isNotifyByEmail;
    private Long isNotifyByMessage;
    private String moreInfo;
    private Long isActive;

    private int stt;
    private Date sendDateFrom;
    private Date sendDateTo;
    private String documentCode;

    public ProcessForm() {
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public Date getSendDateFrom() {
        return sendDateFrom;
    }

    public void setSendDateFrom(Date sendDateFrom) {
        this.sendDateFrom = sendDateFrom;
    }

    public Date getSendDateTo() {
        return sendDateTo;
    }

    public void setSendDateTo(Date sendDateTo) {
        this.sendDateTo = sendDateTo;
    }

    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }


    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Long getProcessStatusId() {
        return processStatusId;
    }

    public void setProcessStatusId(Long processStatusId) {
        this.processStatusId = processStatusId;
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

    public Long getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Long sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public Long getSendGroupId() {
        return sendGroupId;
    }

    public void setSendGroupId(Long sendGroupId) {
        this.sendGroupId = sendGroupId;
    }

    public String getSendGroup() {
        return sendGroup;
    }

    public void setSendGroup(String sendGroup) {
        this.sendGroup = sendGroup;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Long getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(Long receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public Long getReceiveGroupId() {
        return receiveGroupId;
    }

    public void setReceiveGroupId(Long receiveGroupId) {
        this.receiveGroupId = receiveGroupId;
    }

    public String getReceiveGroup() {
        return receiveGroup;
    }

    public void setReceiveGroup(String receiveGroup) {
        this.receiveGroup = receiveGroup;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Long getProcessType() {
        return processType;
    }

    public void setProcessType(Long processType) {
        this.processType = processType;
    }

    public Long getOrderProcess() {
        return orderProcess;
    }

    public void setOrderProcess(Long orderProcess) {
        this.orderProcess = orderProcess;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Long getDeadlineNumber() {
        return deadlineNumber;
    }

    public void setDeadlineNumber(Long deadlineNumber) {
        this.deadlineNumber = deadlineNumber;
    }

    public Long getIsNotifyByEmail() {
        return isNotifyByEmail;
    }

    public void setIsNotifyByEmail(Long isNotifyByEmail) {
        this.isNotifyByEmail = isNotifyByEmail;
    }

    public Long getIsNotifyByMessage() {
        return isNotifyByMessage;
    }

    public void setIsNotifyByMessage(Long isNotifyByMessage) {
        this.isNotifyByMessage = isNotifyByMessage;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Long processStatus) {
        this.processStatus = processStatus;
    }
    
}
