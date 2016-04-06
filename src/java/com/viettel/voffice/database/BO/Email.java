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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dungnt78
 */
@Entity
@Table(name = "EMAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e"),
    @NamedQuery(name = "Email.findByEmailId", query = "SELECT e FROM Email e WHERE e.emailId = :emailId"),
    @NamedQuery(name = "Email.findByUserId", query = "SELECT e FROM Email e WHERE e.userId = :userId"),
    @NamedQuery(name = "Email.findBySender", query = "SELECT e FROM Email e WHERE e.sender = :sender"),
    @NamedQuery(name = "Email.findByReplyTo", query = "SELECT e FROM Email e WHERE e.replyTo = :replyTo"),
    @NamedQuery(name = "Email.findBySentDate", query = "SELECT e FROM Email e WHERE e.sentDate = :sentDate"),
    @NamedQuery(name = "Email.findByReceiveDate", query = "SELECT e FROM Email e WHERE e.receiveDate = :receiveDate"),
    @NamedQuery(name = "Email.findByFlag", query = "SELECT e FROM Email e WHERE e.flag = :flag"),
    @NamedQuery(name = "Email.findByFolder", query = "SELECT e FROM Email e WHERE e.folder = :folder"),
    @NamedQuery(name = "Email.findByAttachmentId", query = "SELECT e FROM Email e WHERE e.attachmentId = :attachmentId"),
    @NamedQuery(name = "Email.findByEmailUid", query = "SELECT e FROM Email e WHERE e.emailUid = :emailUid"),
    @NamedQuery(name = "Email.findBySubject", query = "SELECT e FROM Email e WHERE e.subject = :subject"),
    @NamedQuery(name = "Email.findByEmailAdress", query = "SELECT e FROM Email e WHERE e.emailAdress = :emailAdress")})
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "EMAIL_SEQ", sequenceName = "EMAIL_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMAIL_SEQ")
    @Basic(optional = false)
    @Column(name = "EMAIL_ID")
    private Long emailId;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "SENDER")
    private String sender;
    @Lob
    @Column(name = "RECEIVER")
    private String receiver;
    @Column(name = "REPLY_TO")
    private String replyTo;
    @Column(name = "SENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentDate;
    @Column(name = "RECEIVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveDate;
    @Column(name = "FLAG")
    private String flag;
    @Column(name = "FOLDER")
    private String folder;
    @Column(name = "ATTACHMENT_ID")
    private String attachmentId;
    @Column(name = "EMAIL_UID")
    private String emailUid;
    @Column(name = "SUBJECT")
    private String subject;
    @Lob
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "EMAIL_ADRESS")
    private String emailAdress;
    @Lob
    @Column(name = "RECIPIENTS")
    private String recipients;
    @Lob
    @Column(name = "BCC")
    private String bcc;

    public Email() {
    }

    public Email(Long emailId) {
        this.emailId = emailId;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getEmailUid() {
        return emailUid;
    }

    public void setEmailUid(String emailUid) {
        this.emailUid = emailUid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailId != null ? emailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        if ((this.emailId == null && other.emailId != null) || (this.emailId != null && !this.emailId.equals(other.emailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.voffice.database.BO.Email[ emailId=" + emailId + " ]";
    }
    
}
