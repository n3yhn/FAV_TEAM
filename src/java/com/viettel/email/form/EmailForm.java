/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.email.form;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dungnt78
 */
public class EmailForm {

    private Long emailId;
    private Long userId;
    private String sender;
    private String receiver;
    private String replyTo;
    private Date sentDate;
    private Date receiveDate;
    private String flag;
    private String folder;
    private String attachmentId;
    private String emailUid;
    private String subject;
    private String content;
    private String emailAdress;
    private String recipients;
    private String bcc;
    private String[] attachmentIDArray;
    private List<String> attachmentNameArray;
    private Date fromDate;
    private Date toDate;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    
    public String getAttachIDString() {
        return attachIDString;
    }

    public void setAttachIDString(String attachIDString) {
        this.attachIDString = attachIDString;
    }
    //String of attachs id: 5123; 1234; 
    private String attachIDString;

    public String[] getAttachmentIDArray() {
        return attachmentIDArray;
    }

    public void setAttachmentIDArray(String[] attachmentIDArray) {
        this.attachmentIDArray = attachmentIDArray;
    }

    public List<String> getAttachmentNameArray() {
        return attachmentNameArray;
    }

    public void setAttachmentNameArray(List<String> attachmentNameArray) {
        this.attachmentNameArray = attachmentNameArray;
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

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }
}
