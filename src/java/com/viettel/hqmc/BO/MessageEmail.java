/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vtit_binhnt53
 */
@Entity
@Table(name = "MESSAGE_EMAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessageEmail.findAll", query = "SELECT m FROM MessageEmail m"),
    @NamedQuery(name = "MessageEmail.findByMessageId", query = "SELECT m FROM MessageEmail m WHERE m.messageId = :messageId"),
    @NamedQuery(name = "MessageEmail.findBySendTime", query = "SELECT m FROM MessageEmail m WHERE m.sendTime = :sendTime"),
    @NamedQuery(name = "MessageEmail.findByIsSent", query = "SELECT m FROM MessageEmail m WHERE m.isSent = :isSent"),
    @NamedQuery(name = "MessageEmail.findByErrorMsg", query = "SELECT m FROM MessageEmail m WHERE m.errorMsg = :errorMsg"),
    @NamedQuery(name = "MessageEmail.findBySentTimeReq", query = "SELECT m FROM MessageEmail m WHERE m.sentTimeReq = :sentTimeReq"),
    @NamedQuery(name = "MessageEmail.findByMsgType", query = "SELECT m FROM MessageEmail m WHERE m.msgType = :msgType"),
    @NamedQuery(name = "MessageEmail.findByReceiveEmail", query = "SELECT m FROM MessageEmail m WHERE m.receiveEmail = :receiveEmail"),
    @NamedQuery(name = "MessageEmail.findBySenderId", query = "SELECT m FROM MessageEmail m WHERE m.senderId = :senderId")})
public class MessageEmail implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "MESSAGE_EMAIL_SEQ", sequenceName = "MESSAGE_EMAIL_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_EMAIL_SEQ")
    @Basic(optional = false)
    @Column(name = "MESSAGE_ID")
    private Long messageId;
    @Column(name = "SEND_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendTime;
    @Column(name = "IS_SENT")
    private Long isSent;
    @Column(name = "ERROR_MSG")
    private String errorMsg;
    @Column(name = "SENT_TIME_REQ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentTimeReq;
    @Column(name = "MSG_TYPE")
    private Long msgType;
    @Column(name = "RECEIVE_EMAIL")
    private String receiveEmail;
    @Basic(optional = false)
    @Column(name = "SENDER_ID")
    private long senderId;
    @Lob
    @Column(name = "CONTENT")
    private String content;
    @OneToMany(mappedBy = "replyMessageId")
    private Collection<ReceiveEmail> receiveEmailCollection;
    @Column(name = "SEND_COUNT")
    private Long sendCount;

    public MessageEmail() {
    }

    public MessageEmail(Long messageId) {
        this.messageId = messageId;
    }

    public MessageEmail(Long messageId, long senderId) {
        this.messageId = messageId;
        this.senderId = senderId;
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

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @XmlTransient
    public Collection<ReceiveEmail> getReceiveEmailCollection() {
        return receiveEmailCollection;
    }

    public void setReceiveEmailCollection(Collection<ReceiveEmail> receiveEmailCollection) {
        this.receiveEmailCollection = receiveEmailCollection;
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
        if (!(object instanceof MessageEmail)) {
            return false;
        }
        MessageEmail other = (MessageEmail) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.MessageEmail[ messageId=" + messageId + " ]";
    }

}
