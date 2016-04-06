/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.voffice.database.BO.Message;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "RECEIVE_SMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReceiveSms.findAll", query = "SELECT r FROM ReceiveSms r"),
    @NamedQuery(name = "ReceiveSms.findByMessageId", query = "SELECT r FROM ReceiveSms r WHERE r.messageId = :messageId"),
    @NamedQuery(name = "ReceiveSms.findByContent", query = "SELECT r FROM ReceiveSms r WHERE r.content = :content"),
    @NamedQuery(name = "ReceiveSms.findByPhoneNumber", query = "SELECT r FROM ReceiveSms r WHERE r.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "ReceiveSms.findByReceiveTime", query = "SELECT r FROM ReceiveSms r WHERE r.receiveTime = :receiveTime"),
    @NamedQuery(name = "ReceiveSms.findByMsgType", query = "SELECT r FROM ReceiveSms r WHERE r.msgType = :msgType"),
    @NamedQuery(name = "ReceiveSms.findByErrorMsg", query = "SELECT r FROM ReceiveSms r WHERE r.errorMsg = :errorMsg"),
    @NamedQuery(name = "ReceiveSms.findByIsProcess", query = "SELECT r FROM ReceiveSms r WHERE r.isProcess = :isProcess")})
public class ReceiveSms implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "RECEIVE_SMS_SEQ", sequenceName = "RECEIVE_SMS_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECEIVE_SMS_SEQ")
    @Basic(optional = false)
    @Column(name = "MESSAGE_ID")
    private Long messageId;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "RECEIVE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveTime;
    @Column(name = "MSG_TYPE")
    private Integer msgType;
    @Column(name = "ERROR_MSG")
    private String errorMsg;
    @Column(name = "IS_PROCESS")
    private Integer isProcess;
    @JoinColumn(name = "REPLY_MESSAGE_ID", referencedColumnName = "MESSAGE_ID")
    @ManyToOne(optional = false)
    private Message replyMessageId;

    public ReceiveSms() {
    }

    public ReceiveSms(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getIsProcess() {
        return isProcess;
    }

    public void setIsProcess(Integer isProcess) {
        this.isProcess = isProcess;
    }

    public Message getReplyMessageId() {
        return replyMessageId;
    }

    public void setReplyMessageId(Message replyMessageId) {
        this.replyMessageId = replyMessageId;
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
        if (!(object instanceof ReceiveSms)) {
            return false;
        }
        ReceiveSms other = (ReceiveSms) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.ReceiveSms[ messageId=" + messageId + " ]";
    }
    
}
