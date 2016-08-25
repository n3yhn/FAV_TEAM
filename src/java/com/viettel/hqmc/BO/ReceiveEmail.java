/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "RECEIVE_EMAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReceiveEmail.findAll", query = "SELECT r FROM ReceiveEmail r"),
    @NamedQuery(name = "ReceiveEmail.findByMessageId", query = "SELECT r FROM ReceiveEmail r WHERE r.messageId = :messageId"),
    @NamedQuery(name = "ReceiveEmail.findByEmail", query = "SELECT r FROM ReceiveEmail r WHERE r.email = :email"),
    @NamedQuery(name = "ReceiveEmail.findByReceiveTime", query = "SELECT r FROM ReceiveEmail r WHERE r.receiveTime = :receiveTime"),
    @NamedQuery(name = "ReceiveEmail.findByMsgType", query = "SELECT r FROM ReceiveEmail r WHERE r.msgType = :msgType"),
    @NamedQuery(name = "ReceiveEmail.findByErrorMsg", query = "SELECT r FROM ReceiveEmail r WHERE r.errorMsg = :errorMsg"),
    @NamedQuery(name = "ReceiveEmail.findByIsProcess", query = "SELECT r FROM ReceiveEmail r WHERE r.isProcess = :isProcess")})
public class ReceiveEmail implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "RECEIVE_EMAIL_SEQ", sequenceName = "RECEIVE_EMAIL_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECEIVE_EMAIL_SEQ")
    @Basic(optional = false)
    @Column(name = "MESSAGE_ID")
    private Long messageId;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "RECEIVE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveTime;
    @Column(name = "MSG_TYPE")
    private Integer msgType;
    @Column(name = "ERROR_MSG")
    private String errorMsg;
    @Column(name = "IS_PROCESS")
    private Integer isProcess;
    @Lob
    @Column(name = "CONTENT")
    private String content;
    @JoinColumn(name = "REPLY_MESSAGE_ID", referencedColumnName = "MESSAGE_ID")
    @ManyToOne
    private MessageEmail replyMessageId;

    public ReceiveEmail() {
    }

    public ReceiveEmail(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = StringUtils.removeEventHandlerJS(email);
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
        this.errorMsg = StringUtils.removeEventHandlerJS(errorMsg);
    }

    public Integer getIsProcess() {
        return isProcess;
    }

    public void setIsProcess(Integer isProcess) {
        this.isProcess = isProcess;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = StringUtils.removeEventHandlerJS(content);
    }

    public MessageEmail getReplyMessageId() {
        return replyMessageId;
    }

    public void setReplyMessageId(MessageEmail replyMessageId) {
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
        if (!(object instanceof ReceiveEmail)) {
            return false;
        }
        ReceiveEmail other = (ReceiveEmail) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.ReceiveEmail[ messageId=" + messageId + " ]";
    }

}
