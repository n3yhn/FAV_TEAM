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
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "MESSAGE_EMAIL_SENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessageEmailSent.findAll", query = "SELECT m FROM MessageEmailSent m"),
    @NamedQuery(name = "MessageEmailSent.findByMessageId", query = "SELECT m FROM MessageEmailSent m WHERE m.messageId = :messageId"),
    @NamedQuery(name = "MessageEmailSent.findBySendTime", query = "SELECT m FROM MessageEmailSent m WHERE m.sendTime = :sendTime"),
    @NamedQuery(name = "MessageEmailSent.findByMsgType", query = "SELECT m FROM MessageEmailSent m WHERE m.msgType = :msgType"),
    @NamedQuery(name = "MessageEmailSent.findByReceiveEmail", query = "SELECT m FROM MessageEmailSent m WHERE m.receiveEmail = :receiveEmail"),
    @NamedQuery(name = "MessageEmailSent.findBySenderId", query = "SELECT m FROM MessageEmailSent m WHERE m.senderId = :senderId")})
public class MessageEmailSent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MESSAGE_ID")
    private Long messageId;
    @Column(name = "SEND_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendTime;
    @Column(name = "MSG_TYPE")
    private Short msgType;
    @Column(name = "RECEIVE_EMAIL")
    private String receiveEmail;
    @Column(name = "SENDER_ID")
    private Long senderId;
    @Lob
    @Column(name = "CONTENT")
    private String content;

    public MessageEmailSent() {
    }

    public MessageEmailSent(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Short getMsgType() {
        return msgType;
    }

    public void setMsgType(Short msgType) {
        this.msgType = msgType;
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageEmailSent)) {
            return false;
        }
        MessageEmailSent other = (MessageEmailSent) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.MessageEmailSent[ messageId=" + messageId + " ]";
    }
    
}
