/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.common.util.DateTimeUtils;
import com.viettel.hqmc.BO.ReIssueForm;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vtit_havm2
 */
public class ReIssueFormForm implements Serializable {

    private Long reIssueFormId;
    private String identificationNumber;
    private String address;
    private String telephone;
    private String fax;
    private String email;
    private String formNumber;
    private Date issueDate;
    private String issueAgency;
    private Date signDate;
    private String documentNo;
    private Date documentDate;
    private String businessName;
    private String nearestFileNo;
    private Long reIssueAgencyId;
    private String reIssueAgency;
    private Date reIssueDate;
    private Long isActive;
    private String reIssueDateStr;
    private String documentDateStr;
    private String signDateStr;
    private String issueDateStr;

    public ReIssueFormForm() {
    }

    public ReIssueFormForm(ReIssueForm entity) {
        if (entity == null) {
            return;
        }
        reIssueFormId = entity.getReIssueFormId();
        identificationNumber = entity.getIdentificationNumber();
        address = entity.getAddress();
        telephone = entity.getTelephone();
        fax = entity.getFax();
        email = entity.getEmail();
        formNumber = entity.getFormNumber();
        issueDate = entity.getIssueDate();
        issueAgency = entity.getIssueAgency();
        signDate = entity.getSignDate();
        documentNo = entity.getDocumentNo();
        documentDate = entity.getDocumentDate();
        businessName = entity.getBusinessName();
        nearestFileNo = entity.getNearestFileNo();
        reIssueAgencyId = entity.getReIssueAgencyId();
        reIssueAgency = entity.getReIssueAgency();
        reIssueDate = entity.getReIssueDate();
        if (reIssueDate != null) {
            reIssueDateStr = DateTimeUtils.convertDateToString(reIssueDate, "dd/MM/yyyy");
        }
        if (documentDate != null) {
            documentDateStr = DateTimeUtils.convertDateToString(documentDate, "dd/MM/yyyy");
        }
        if (signDate != null) {
            signDateStr = DateTimeUtils.convertDateToString(signDate, "dd/MM/yyyy");
        }
        if (issueDate != null) {
            issueDateStr = DateTimeUtils.convertDateToString(issueDate, "dd/MM/yyyy");
        }
        isActive = entity.getIsActive();
    }

    public ReIssueForm convertToEntity() {
        ReIssueForm entity = new ReIssueForm();
        entity.setReIssueFormId(reIssueFormId);
        entity.setIdentificationNumber(identificationNumber);
        entity.setAddress(address);
        entity.setTelephone(telephone);
        entity.setFax(fax);
        entity.setEmail(email);
        entity.setFormNumber(formNumber);
        entity.setIssueDate(issueDate);
        entity.setIssueAgency(issueAgency);
        entity.setSignDate(signDate);
        entity.setDocumentNo(documentNo);
        entity.setDocumentDate(documentDate);
        entity.setBusinessName(businessName);
        entity.setNearestFileNo(nearestFileNo);
        entity.setReIssueAgencyId(reIssueAgencyId);
        entity.setReIssueAgency(reIssueAgency);
        entity.setReIssueDate(reIssueDate);
        if (isActive == null) {
            isActive = 1l;
        }
        entity.setIsActive(isActive);
        return entity;
    }

    public Long getReIssueFormId() {
        return reIssueFormId;
    }

    public void setReIssueFormId(Long reIssueFormId) {
        this.reIssueFormId = reIssueFormId;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueDateStr() {
        if (issueDate == null) {
            return "";
        }
        return DateTimeUtils.convertDateToString(issueDate, "dd/MM/yyyy");
    }

    public String getIssueAgency() {
        return issueAgency;
    }

    public void setIssueAgency(String issueAgency) {
        this.issueAgency = issueAgency;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getSignDateStr() {
        if (signDate == null) {
            return "";
        }
        return DateTimeUtils.convertDateToString(signDate, "dd/MM/yyyy");
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public String getDocumentDateStr() {
        if (documentDate == null) {
            return "";
        }
        return DateTimeUtils.convertDateToString(documentDate, "dd/MM/yyyy");
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getNearestFileNo() {
        return nearestFileNo;
    }

    public void setNearestFileNo(String nearestFileNo) {
        this.nearestFileNo = nearestFileNo;
    }

    public Long getReIssueAgencyId() {
        return reIssueAgencyId;
    }

    public void setReIssueAgencyId(Long reIssueAgencyId) {
        this.reIssueAgencyId = reIssueAgencyId;
    }

    public String getReIssueAgency() {
        return reIssueAgency;
    }

    public void setReIssueAgency(String reIssueAgency) {
        this.reIssueAgency = reIssueAgency;
    }

    public Date getReIssueDate() {
        return reIssueDate;
    }

    public String getReIssueDateStr() {
        if (reIssueDate == null) {
            return "";
        }
        return DateTimeUtils.convertDateToString(reIssueDate, "dd/MM/yyyy");
    }

    public void setReIssueDate(Date reIssueDate) {
        this.reIssueDate = reIssueDate;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public void setReIssueDateStr(String reIssueDateStr) {
        this.reIssueDateStr = reIssueDateStr;
    }

    public void setDocumentDateStr(String documentDateStr) {
        this.documentDateStr = documentDateStr;
    }

    public void setSignDateStr(String signDateStr) {
        this.signDateStr = signDateStr;
    }

    public void setIssueDateStr(String issueDateStr) {
        this.issueDateStr = issueDateStr;
    }

}
