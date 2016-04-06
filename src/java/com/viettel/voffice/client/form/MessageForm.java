/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

import java.util.Date;
import java.util.List;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Owner
 */
public class MessageForm {

    private long msgNum;

    private long emailID;

    public long getEmailID() {
        return emailID;
    }

    public void setEmailID(long emailID) {
        this.emailID = emailID;
    }
    private String subject;
    private String personNameFrom;
    private String addressFrom;
    private Date receiveDate;
    private Date sendDate;
    private String content;
    private String readFlag;
    private boolean haveAttachment;
    private String allRecipients;
    private String filenameList;
    private String to;
    private String cc;
    private String bb;
    private String[] attachID;
    private String attachIDString;

    public String getAttachIDString() {
        return attachIDString;
    }

    public void setAttachIDString(String attachIDString) {
        this.attachIDString = attachIDString;
    }
    private List<String> attachFileName;

    public List<String> getAttachFileName() {
        return attachFileName;
    }

    public void setAttachFileName(List<String> attachFileName) {
        this.attachFileName = attachFileName;
    }
    public String[] getAttachID() {
        return attachID;
    }

    public void setAttachID(String[] attachID) {
        this.attachID = attachID;
    }

    public String getFilenameList() {
        return filenameList;
    }

    public void setFilenameList(String filenameList) {
        this.filenameList = filenameList;
    }


    
    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public String getAllRecipients() {
        return allRecipients;
    }

    public void setAllRecipients(String add) {

        this.allRecipients = add;
    }

    public void setHaveAttachment(boolean haveAttachment) {
        this.haveAttachment = haveAttachment;
    }

    public String getPersonNameFrom() {
        return personNameFrom;
    }

    public void setPersonNameFrom(String personNameFrom) {
        this.personNameFrom = personNameFrom;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(long msgNum) {
        this.msgNum = msgNum;
    }



   

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
}
