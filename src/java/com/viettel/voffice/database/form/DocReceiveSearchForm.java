/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.form;
import java.util.Date;
/**
 *
 * @author Viettel
 */
public class DocReceiveSearchForm {
    
    private String documentCode; //So, ky hieu goc
    private String publisherName; //Tên co quan gui
    private Date documentReceiveDateFrom; //Ngay nhan From
    private Date documentReceiveDateTo; //Ngay nhan To
    private Date documentSendDateFrom; //Ngay ban hanh From
    private Date documentSendDateTo; //Ngay ban hanh To
    private Long documentTypeId; //Loai van ban
    private Long mainOfficeProcessId; //Ma don vi xu ly
    private Long mainProcessorId; //Mã người xử lý
    private String signer; //Nguoi ky
    private String documentAbstract;  //Trich yeu
    private Long deptReceiveId; // Phong ban nhan
    private Long leaderId;   // lanh dao nhan

    public Long getMainProcessorId() {
        return mainProcessorId;
    }

    public void setMainProcessorId(Long mainProcessorId) {
        this.mainProcessorId = mainProcessorId;
    }
   
    
    public String getDocumentAbstract() {
        return documentAbstract;
    }

    public void setDocumentAbstract(String documentAbstract) {
        this.documentAbstract = documentAbstract;
    }

    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public Date getDocumentReceiveDateFrom() {
        return documentReceiveDateFrom;
    }

    public void setDocumentReceiveDateFrom(Date documentReceiveDateFrom) {
        this.documentReceiveDateFrom = documentReceiveDateFrom;
    }

    public Date getDocumentReceiveDateTo() {
        return documentReceiveDateTo;
    }

    public void setDocumentReceiveDateTo(Date documentReceiveDateTo) {
        this.documentReceiveDateTo = documentReceiveDateTo;
    }

    public Date getDocumentSendDateFrom() {
        return documentSendDateFrom;
    }

    public void setDocumentSendDateFrom(Date documentSendDateFrom) {
        this.documentSendDateFrom = documentSendDateFrom;
    }

    public Date getDocumentSendDateTo() {
        return documentSendDateTo;
    }

    public void setDocumentSendDateTo(Date documentSendDateTo) {
        this.documentSendDateTo = documentSendDateTo;
    }

    public Long getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public Long getMainOfficeProcessId() {
        return mainOfficeProcessId;
    }

    public void setMainOfficeProcessId(Long mainOfficeProcessId) {
        this.mainOfficeProcessId = mainOfficeProcessId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public Long getDeptReceiveId() {
        return deptReceiveId;
    }

    public void setDeptReceiveId(Long deptReceiveId) {
        this.deptReceiveId = deptReceiveId;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }
}
