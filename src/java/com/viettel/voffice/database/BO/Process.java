/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.BO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "PROCESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Process.findAll", query = "SELECT p FROM Process p"),
    @NamedQuery(name = "Process.findByProcessId", query = "SELECT p FROM Process p WHERE p.processId = :processId"),
    @NamedQuery(name = "Process.findByProcessStatus", query = "SELECT p FROM Process p WHERE p.processStatus = :processStatus"),
    @NamedQuery(name = "Process.findByObjectId", query = "SELECT p FROM Process p WHERE p.objectId = :objectId"),
    @NamedQuery(name = "Process.findByObjectType", query = "SELECT p FROM Process p WHERE p.objectType = :objectType"),
    @NamedQuery(name = "Process.findBySendUserId", query = "SELECT p FROM Process p WHERE p.sendUserId = :sendUserId"),
    @NamedQuery(name = "Process.findBySendUser", query = "SELECT p FROM Process p WHERE p.sendUser = :sendUser"),
    @NamedQuery(name = "Process.findBySendGroupId", query = "SELECT p FROM Process p WHERE p.sendGroupId = :sendGroupId"),
    @NamedQuery(name = "Process.findBySendGroup", query = "SELECT p FROM Process p WHERE p.sendGroup = :sendGroup"),
    @NamedQuery(name = "Process.findBySendDate", query = "SELECT p FROM Process p WHERE p.sendDate = :sendDate"),
    @NamedQuery(name = "Process.findByReceiveUserId", query = "SELECT p FROM Process p WHERE p.receiveUserId = :receiveUserId"),
    @NamedQuery(name = "Process.findByReceiveUser", query = "SELECT p FROM Process p WHERE p.receiveUser = :receiveUser"),
    @NamedQuery(name = "Process.findByReceiveGroupId", query = "SELECT p FROM Process p WHERE p.receiveGroupId = :receiveGroupId"),
    @NamedQuery(name = "Process.findByReceiveGroup", query = "SELECT p FROM Process p WHERE p.receiveGroup = :receiveGroup"),
    @NamedQuery(name = "Process.findByReceiveDate", query = "SELECT p FROM Process p WHERE p.receiveDate = :receiveDate"),
    @NamedQuery(name = "Process.findByProcessType", query = "SELECT p FROM Process p WHERE p.processType = :processType"),
    @NamedQuery(name = "Process.findByOrderProcess", query = "SELECT p FROM Process p WHERE p.orderProcess = :orderProcess"),
    @NamedQuery(name = "Process.findByStatus", query = "SELECT p FROM Process p WHERE p.status = :status"),
    @NamedQuery(name = "Process.findByDeadline", query = "SELECT p FROM Process p WHERE p.deadline = :deadline"),
    @NamedQuery(name = "Process.findByDeadlineNumber", query = "SELECT p FROM Process p WHERE p.deadlineNumber = :deadlineNumber"),
    @NamedQuery(name = "Process.findByIsNotifyByEmail", query = "SELECT p FROM Process p WHERE p.isNotifyByEmail = :isNotifyByEmail"),
    @NamedQuery(name = "Process.findByIsNotifyByMessage", query = "SELECT p FROM Process p WHERE p.isNotifyByMessage = :isNotifyByMessage"),
    @NamedQuery(name = "Process.findByMoreInfo", query = "SELECT p FROM Process p WHERE p.moreInfo = :moreInfo"),
    @NamedQuery(name = "Process.findByIsActive", query = "SELECT p FROM Process p WHERE p.isActive = :isActive"),
    @NamedQuery(name = "Process.findByReceiveUserType", query = "SELECT p FROM Process p WHERE p.receiveUserType = :receiveUserType"),
    @NamedQuery(name = "Process.findByLastestComment", query = "SELECT p FROM Process p WHERE p.lastestComment = :lastestComment")})
public class Process implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "PROCESS_SEQ", sequenceName = "PROCESS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROCESS_SEQ")
    @Id
    @Basic(optional = false)
    @Column(name = "PROCESS_ID")
    private Long processId;
    @Column(name = "PROCESS_STATUS")
    private Long processStatus;
    @Column(name = "OBJECT_ID")
    private Long objectId;
    @Column(name = "OBJECT_TYPE")
    private Long objectType;
    @Column(name = "SEND_USER_ID")
    private Long sendUserId;
    @Column(name = "SEND_USER")
    private String sendUser;
    @Column(name = "SEND_GROUP_ID")
    private Long sendGroupId;
    @Column(name = "SEND_GROUP")
    private String sendGroup;
    @Column(name = "SEND_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;
    @Column(name = "RECEIVE_USER_ID")
    private Long receiveUserId;
    @Column(name = "RECEIVE_USER")
    private String receiveUser;
    @Column(name = "RECEIVE_GROUP_ID")
    private Long receiveGroupId;
    @Column(name = "RECEIVE_GROUP")
    private String receiveGroup;
    @Column(name = "RECEIVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveDate;
    @Column(name = "PROCESS_TYPE")
    private Long processType;
    @Column(name = "ORDER_PROCESS")
    private Long orderProcess;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "DEADLINE")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    @Column(name = "DEADLINE_NUMBER")
    private Long deadlineNumber;
    @Column(name = "IS_NOTIFY_BY_EMAIL")
    private Long isNotifyByEmail;
    @Column(name = "IS_NOTIFY_BY_MESSAGE")
    private Long isNotifyByMessage;
    @Column(name = "MORE_INFO")
    private String moreInfo;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "RECEIVE_USER_TYPE")
    private Long receiveUserType;
    @Column(name = "LASTEST_COMMENT")
    private String lastestComment;
    @Transient
    Long isFee;

    public Process() {
    }

    public Process(Long processId) {
        this.processId = processId;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
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

    public Long getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Long processStatus) {
        this.processStatus = processStatus;
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

    public Long getReceiveUserType() {
        return receiveUserType;
    }

    public void setReceiveUserType(Long receiveUserType) {
        this.receiveUserType = receiveUserType;
    }

    public String getLastestComment() {
        return lastestComment;
    }

    public void setLastestComment(String lastestComment) {
        this.lastestComment = lastestComment;
    }

    public Long getIsFee() {
        return isFee;
    }

    public void setIsFee(Long isFee) {
        this.isFee = isFee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (processId != null ? processId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Process)) {
            return false;
        }
        Process other = (Process) object;
        if ((this.processId == null && other.processId != null) || (this.processId != null && !this.processId.equals(other.processId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.voffice.database.BO.Process[ processId=" + processId + " ]";
    }
}
