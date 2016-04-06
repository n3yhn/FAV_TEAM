/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.hqmc.BO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GPCP_BINHNT53
 */
@Entity
@Table(name = "MESSAGE_SMS_SENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessageSmsSent.findAll", query = "SELECT m FROM MessageSmsSent m"),
    @NamedQuery(name = "MessageSmsSent.findByMessageId", query = "SELECT m FROM MessageSmsSent m WHERE m.messageId = :messageId"),
    @NamedQuery(name = "MessageSmsSent.findByUserId", query = "SELECT m FROM MessageSmsSent m WHERE m.userId = :userId"),
    @NamedQuery(name = "MessageSmsSent.findBySenderId", query = "SELECT m FROM MessageSmsSent m WHERE m.senderId = :senderId"),
    @NamedQuery(name = "MessageSmsSent.findByContent", query = "SELECT m FROM MessageSmsSent m WHERE m.content = :content"),
    @NamedQuery(name = "MessageSmsSent.findBySentTime", query = "SELECT m FROM MessageSmsSent m WHERE m.sentTime = :sentTime"),
    @NamedQuery(name = "MessageSmsSent.findByMsgType", query = "SELECT m FROM MessageSmsSent m WHERE m.msgType = :msgType"),
    @NamedQuery(name = "MessageSmsSent.findByParentId", query = "SELECT m FROM MessageSmsSent m WHERE m.parentId = :parentId"),
    @NamedQuery(name = "MessageSmsSent.findByPhoneNumber", query = "SELECT m FROM MessageSmsSent m WHERE m.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "MessageSmsSent.findByUserName", query = "SELECT m FROM MessageSmsSent m WHERE m.userName = :userName"),
    @NamedQuery(name = "MessageSmsSent.findByDeptName", query = "SELECT m FROM MessageSmsSent m WHERE m.deptName = :deptName")})
public class MessageSmsSent implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "MESSAGE_ID")
    private BigDecimal messageId;
    @Column(name = "USER_ID")
    private BigInteger userId;
    @Basic(optional = false)
    @Column(name = "SENDER_ID")
    private BigInteger senderId;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "SENT_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentTime;
    @Column(name = "MSG_TYPE")
    private BigInteger msgType;
    @Column(name = "PARENT_ID")
    private BigInteger parentId;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "DEPT_NAME")
    private String deptName;

    public MessageSmsSent() {
    }

    public MessageSmsSent(BigDecimal messageId) {
        this.messageId = messageId;
    }

    public MessageSmsSent(BigDecimal messageId, BigInteger senderId) {
        this.messageId = messageId;
        this.senderId = senderId;
    }

    public BigDecimal getMessageId() {
        return messageId;
    }

    public void setMessageId(BigDecimal messageId) {
        this.messageId = messageId;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getSenderId() {
        return senderId;
    }

    public void setSenderId(BigInteger senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public BigInteger getMsgType() {
        return msgType;
    }

    public void setMsgType(BigInteger msgType) {
        this.msgType = msgType;
    }

    public BigInteger getParentId() {
        return parentId;
    }

    public void setParentId(BigInteger parentId) {
        this.parentId = parentId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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
        if (!(object instanceof MessageSmsSent)) {
            return false;
        }
        MessageSmsSent other = (MessageSmsSent) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.MessageSmsSent[ messageId=" + messageId + " ]";
    }
    
}
