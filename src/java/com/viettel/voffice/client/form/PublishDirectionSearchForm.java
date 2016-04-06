/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

import java.util.Date;

/**
 *
 * @author sytv
 */
public class PublishDirectionSearchForm {

    private String documentCode;
    private String officeId;
    private Long documentTypeId;
    private Date toDate;
    private Date fromDate;
    private Date publishDateFrom;
    private Date publishDateTo;
    private String signer;
    private String documentAbstract;
    private Long areaId;
    private String officeName;
    private Long status;
    private String advanceSearch;
    private Long publishOfficeId;
    private String statusPortal;
    
    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
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

    public Long getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public Date getToDate() {
        return toDate;
    }

    public Date getPublishDateFrom() {
        return publishDateFrom;
    }

    public void setPublishDateFrom(Date publishDateFrom) {
        this.publishDateFrom = publishDateFrom;
    }

    public Date getPublishDateTo() {
        return publishDateTo;
    }

    public void setPublishDateTo(Date publishDateTo) {
        this.publishDateTo = publishDateTo;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getAdvanceSearch() {
        return advanceSearch;
    }

    public void setAdvanceSearch(String advanceSearch) {
        this.advanceSearch = advanceSearch;
    }

    public Long getPublishOfficeId() {
        return publishOfficeId;
    }

    public void setPublishOfficeId(Long publishOfficeId) {
        this.publishOfficeId = publishOfficeId;
    }

    public String getStatusPortal() {
        return statusPortal;
    }

    public void setStatusPortal(String statusPortal) {
        this.statusPortal = statusPortal;
    }
    
}
