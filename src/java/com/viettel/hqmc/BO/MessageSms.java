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
 * @author GPCP_BINHNT53
 */
@Entity
@Table(name = "MESSAGE_SMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessageSms.findAll", query = "SELECT m FROM MessageSms m"),
    @NamedQuery(name = "MessageSms.findByMessageId", query = "SELECT m FROM MessageSms m WHERE m.messageId = :messageId"),
    @NamedQuery(name = "MessageSms.findByUserId", query = "SELECT m FROM MessageSms m WHERE m.userId = :userId"),
    @NamedQuery(name = "MessageSms.findBySenderId", query = "SELECT m FROM MessageSms m WHERE m.senderId = :senderId"),
    @NamedQuery(name = "MessageSms.findByContent", query = "SELECT m FROM MessageSms m WHERE m.content = :content"),
    @NamedQuery(name = "MessageSms.findBySentTime", query = "SELECT m FROM MessageSms m WHERE m.sentTime = :sentTime"),
    @NamedQuery(name = "MessageSms.findByIsSent", query = "SELECT m FROM MessageSms m WHERE m.isSent = :isSent"),
    @NamedQuery(name = "MessageSms.findByErrorMsg", query = "SELECT m FROM MessageSms m WHERE m.errorMsg = :errorMsg"),
    @NamedQuery(name = "MessageSms.findBySentTimeReq", query = "SELECT m FROM MessageSms m WHERE m.sentTimeReq = :sentTimeReq"),
    @NamedQuery(name = "MessageSms.findByMsgType", query = "SELECT m FROM MessageSms m WHERE m.msgType = :msgType"),
    @NamedQuery(name = "MessageSms.findByParentId", query = "SELECT m FROM MessageSms m WHERE m.parentId = :parentId"),
    @NamedQuery(name = "MessageSms.findByPhoneNumber", query = "SELECT m FROM MessageSms m WHERE m.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "MessageSms.findByBroadcastId", query = "SELECT m FROM MessageSms m WHERE m.broadcastId = :broadcastId"),
    @NamedQuery(name = "MessageSms.findByUserName", query = "SELECT m FROM MessageSms m WHERE m.userName = :userName"),
    @NamedQuery(name = "MessageSms.findByDeptName", query = "SELECT m FROM MessageSms m WHERE m.deptName = :deptName"),
    @NamedQuery(name = "MessageSms.findBySendCount", query = "SELECT m FROM MessageSms m WHERE m.sendCount = :sendCount")})
public class MessageSms implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "MESSAGE_SEQ", sequenceName = "MESSAGE_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_SEQ")
    @Basic(optional = false)
    @Column(name = "MESSAGE_ID")
    private Long messageId;
    @Column(name = "USER_ID")
    private Long userId;
    @Basic(optional = false)
    @Column(name = "SENDER_ID")
    private Long senderId;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "SENT_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentTime;
    @Column(name = "IS_SENT")
    private Long isSent;
    @Column(name = "ERROR_MSG")
    private String errorMsg;
    @Column(name = "SENT_TIME_REQ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentTimeReq;
    @Column(name = "MSG_TYPE")
    private Long msgType;
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "BROADCAST_ID")
    private Long broadcastId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "SEND_COUNT")
    private Long sendCount;

    public MessageSms() {
    }

    public MessageSms(Long messageId) {
        this.messageId = messageId;
    }

    public MessageSms(Long messageId, Long senderId) {
        this.messageId = messageId;
        this.senderId = senderId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = StringUtils.removeEventHandlerJS(content);
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public Long getIsSent() {
        return isSent;
    }

    public void setIsSent(Long isSent) {
        this.isSent = isSent;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = StringUtils.removeEventHandlerJS(errorMsg);
    }

    public Date getSentTimeReq() {
        return sentTimeReq;
    }

    public void setSentTimeReq(Date sentTimeReq) {
        this.sentTimeReq = sentTimeReq;
    }

    public Long getMsgType() {
        return msgType;
    }

    public void setMsgType(Long msgType) {
        this.msgType = msgType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = StringUtils.removeEventHandlerJS(phoneNumber);
    }

    public Long getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(Long broadcastId) {
        this.broadcastId = broadcastId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = StringUtils.removeEventHandlerJS(userName);
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = StringUtils.removeEventHandlerJS(deptName);
    }

    public Long getSendCount() {
        return sendCount;
    }

    public void setSendCount(Long sendCount) {
        this.sendCount = sendCount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageSms)) {
            return false;
        }
        MessageSms other = (MessageSms) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.MessageSms[ messageId=" + messageId + " ]";
    }

}
