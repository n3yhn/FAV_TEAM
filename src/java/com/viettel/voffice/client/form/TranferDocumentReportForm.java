/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

import java.util.Date;

/**
 *
 * @author SyTV
 */
public class TranferDocumentReportForm {

   private Long documentReceiveId; 
   private int stt;
   private Date receiveDate;
   private String receiveDateStr;
   private String documentCode;
   private Date documentDate;
   private String documentDateStr;
   private String publisherName;
   private String documentAbstract;
   private String note;
   private int reception;
   private String receptionStr;
   
   private Long processOfficeId;
   private String processOffice;

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public Long getDocumentReceiveId() {
        return documentReceiveId;
    }

    public void setDocumentReceiveId(Long documentReceiveId) {
        this.documentReceiveId = documentReceiveId;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceiveDateStr() {
        return receiveDateStr;
    }

    public void setReceiveDateStr(String receiveDateStr) {
        this.receiveDateStr = receiveDateStr;
    }

    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentDateStr() {
        return documentDateStr;
    }

    public void setDocumentDateStr(String documentDateStr) {
        this.documentDateStr = documentDateStr;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getDocumentAbstract() {
        return documentAbstract;
    }

    public void setDocumentAbstract(String documentAbstract) {
        this.documentAbstract = documentAbstract;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getReception() {
        return reception;
    }

    public void setReception(int reception) {
        this.reception = reception;
    }

    public String getReceptionStr() {
        return receptionStr;
    }

    public void setReceptionStr(String receptionStr) {
        this.receptionStr = receptionStr;
    }


    public Long getProcessOfficeId() {
        return processOfficeId;
    }

    public void setProcessOfficeId(Long processOfficeId) {
        this.processOfficeId = processOfficeId;
    }

    public String getProcessOffice() {
        return processOffice;
    }

    public void setProcessOffice(String processOffice) {
        this.processOffice = processOffice;
    }
   
   
}
