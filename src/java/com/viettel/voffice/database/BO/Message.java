/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vtit_binhnt53
 */
@Entity
@Table(name = "MESSAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findByMessageId", query = "SELECT m FROM Message m WHERE m.messageId = :messageId"),
    @NamedQuery(name = "Message.findByUserId", query = "SELECT m FROM Message m WHERE m.userId = :userId"),
    @NamedQuery(name = "Message.findBySenderId", query = "SELECT m FROM Message m WHERE m.senderId = :senderId"),
    @NamedQuery(name = "Message.findByContent", query = "SELECT m FROM Message m WHERE m.content = :content"),
    @NamedQuery(name = "Message.findBySentTime", query = "SELECT m FROM Message m WHERE m.sentTime = :sentTime"),
    @NamedQuery(name = "Message.findByIsSent", query = "SELECT m FROM Message m WHERE m.isSent = :isSent"),
    @NamedQuery(name = "Message.findByErrorMsg", query = "SELECT m FROM Message m WHERE m.errorMsg = :errorMsg"),
    @NamedQuery(name = "Message.findBySentTimeReq", query = "SELECT m FROM Message m WHERE m.sentTimeReq = :sentTimeReq"),
    @NamedQuery(name = "Message.findByMsgType", query = "SELECT m FROM Message m WHERE m.msgType = :msgType"),
    @NamedQuery(name = "Message.findByParentId", query = "SELECT m FROM Message m WHERE m.parentId = :parentId"),
    @NamedQuery(name = "Message.findByPhoneNumber", query = "SELECT m FROM Message m WHERE m.phoneNumber = :phoneNumber")})
public class Message implements Serializable {
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
    @Column(name = "SEND_COUNT")
    private Long sendCount;
    public Message() {
    }

    public Message(Long messageId) {
        this.messageId = messageId;
    }

    public Message(Long messageId, Long senderId) {
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
        this.content = content;
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
        this.errorMsg = errorMsg;
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
        this.phoneNumber = phoneNumber;
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
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.voffice.database.BO.Message[ messageId=" + messageId + " ]";
    }
    
}
