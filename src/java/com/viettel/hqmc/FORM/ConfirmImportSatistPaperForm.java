/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.ConfirmImportSatistPaper;
import java.util.Date;

/**
 *
 * @author vtit_binhnt53
 */
public class ConfirmImportSatistPaperForm {

    private Long confirmImportSatistPaperId;
    private String testAgencyName;
    private Long testAgencyId;
    private String testAgencyAdd;
    private String testAgencyTel;
    private String testAgencyFax;
    private String conclusion;
    private String exportBusinessName;
    private String exportBusinessAdd;
    private String exportBusinessMail;
    private String exportBusinessTel;
    private String exportBusinessFax;
    private String exportContractCode;
    private Date exportContractDate;
    private String exportLadingCode;
    private Date exportLadingDate;
    private String exportPort;
    private String importBusinessName;
    private String importBusinessAddress;
    private String importBusinessEmail;
    private String importBusinessTel;
    private String importBusinessFax;
    private String importPort;
    private Date importDate;
    private String productName;
    private String productDescription;
    private String productCode;
    private String productOrigin;
    private String productAmount;
    private String productWeight;
    private String productValue;
    private String gatheringAdd;
    private Date testDate;
    private String testAdd;
    private String businessRepresent;
    private Date businessSigndate;
    private String businessSignAdd;
    private String agencyRepresent;
    private String agencySignAdd;
    private Date agencySigndate;
    private String standardTargetNo;
    private Date standardTargetDate;
    private String releaseDocumentNo;
    private Date releaseDocumentDate;
    private Long isActive;
    private String importContractCode;
    private Date importContractDate;
    private Long fileId;

    public ConfirmImportSatistPaperForm() {
    }

    public ConfirmImportSatistPaperForm(ConfirmImportSatistPaper bo) {
        this.confirmImportSatistPaperId = bo.getConfirmImportSatistPaperId();
        this.testAgencyName = bo.getTestAgencyName();
        this.testAgencyId = bo.getTestAgencyId();
        this.testAgencyAdd = bo.getTestAgencyAdd();
        this.testAgencyTel = bo.getTestAgencyTel();
        this.testAgencyFax = bo.getTestAgencyFax();
        this.conclusion = bo.getConclusion();
        this.exportBusinessName = bo.getExportBusinessName();
        this.exportBusinessAdd = bo.getExportBusinessAdd();
        this.exportBusinessMail = bo.getExportBusinessMail();
        this.exportBusinessTel = bo.getExportBusinessTel();
        this.exportBusinessFax = bo.getExportBusinessFax();
        this.exportContractCode = bo.getExportContractCode();
        this.exportContractDate = bo.getExportContractDate();
        this.exportLadingCode = bo.getExportLadingCode();
        this.exportLadingDate = bo.getExportLadingDate();
        this.exportPort = bo.getExportPort();
        this.importBusinessName = bo.getImportBusinessName();
        this.importBusinessAddress = bo.getImportBusinessAddress();
        this.importBusinessEmail = bo.getImportBusinessEmail();
        this.importBusinessTel = bo.getImportBusinessTel();
        this.importBusinessFax = bo.getImportBusinessFax();
        this.importPort = bo.getImportPort();
        this.importDate = bo.getImportDate();
        this.productName = bo.getProductName();
        this.productDescription = bo.getProductDescription();
        this.productCode = bo.getProductCode();
        this.productOrigin = bo.getProductOrigin();
        this.productAmount = bo.getProductAmount();
        this.productWeight = bo.getProductWeight();
        this.productValue = bo.getProductValue();
        this.gatheringAdd = bo.getGatheringAdd();
        this.testDate = bo.getTestDate();
        this.testAdd = bo.getTestAdd();
        this.businessRepresent = bo.getBusinessRepresent();
        this.businessSigndate = bo.getBusinessSigndate();
        this.businessSignAdd = bo.getBusinessSignAdd();
        this.agencyRepresent = bo.getAgencyRepresent();
        this.agencySignAdd = bo.getAgencySignAdd();
        this.agencySigndate = bo.getAgencySigndate();
        this.standardTargetNo = bo.getStandardTargetNo();
        this.standardTargetDate = bo.getStandardTargetDate();
        this.releaseDocumentNo = bo.getReleaseDocumentNo();
        this.releaseDocumentDate = bo.getReleaseDocumentDate();
        this.isActive = bo.getIsActive();
        this.importContractCode = bo.getImportContractCode();
        this.importContractDate = bo.getImportContractDate();
    }

    public ConfirmImportSatistPaperForm(Long confirmImportSatistPaperId, String testAgencyName, Long testAgencyId, String testAgencyAdd, String testAgencyTel, String testAgencyFax, String conclusion, String exportBusinessName, String exportBusinessAdd, String exportBusinessMail, String exportBusinessTel, String exportBusinessFax, String exportContractCode, Date exportContractDate, String exportLadingCode, Date exportLadingDate, String exportPort, String importBusinessName, String importBusinessAddress, String importBusinessEmail, String importBusinessTel, String importBusinessFax, String importPort, Date importDate, String productName, String productDescription, String productCode, String productOrigin, String productAmount, String productWeight, String productValue, String gatheringAdd, Date testDate, String testAdd, String businessRepresent, Date businessSigndate, String businessSignAdd, String agencyRepresent, String agencySignAdd, Date agencySigndate, String standardTargetNo, Date standardTargetDate, String releaseDocumentNo, Date releaseDocumentDate, Long isActive, String importContractCode, Date importContractDate, Long fileId) {
        this.confirmImportSatistPaperId = confirmImportSatistPaperId;
        this.testAgencyName = testAgencyName;
        this.testAgencyId = testAgencyId;
        this.testAgencyAdd = testAgencyAdd;
        this.testAgencyTel = testAgencyTel;
        this.testAgencyFax = testAgencyFax;
        this.conclusion = conclusion;
        this.exportBusinessName = exportBusinessName;
        this.exportBusinessAdd = exportBusinessAdd;
        this.exportBusinessMail = exportBusinessMail;
        this.exportBusinessTel = exportBusinessTel;
        this.exportBusinessFax = exportBusinessFax;
        this.exportContractCode = exportContractCode;
        this.exportContractDate = exportContractDate;
        this.exportLadingCode = exportLadingCode;
        this.exportLadingDate = exportLadingDate;
        this.exportPort = exportPort;
        this.importBusinessName = importBusinessName;
        this.importBusinessAddress = importBusinessAddress;
        this.importBusinessEmail = importBusinessEmail;
        this.importBusinessTel = importBusinessTel;
        this.importBusinessFax = importBusinessFax;
        this.importPort = importPort;
        this.importDate = importDate;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCode = productCode;
        this.productOrigin = productOrigin;
        this.productAmount = productAmount;
        this.productWeight = productWeight;
        this.productValue = productValue;
        this.gatheringAdd = gatheringAdd;
        this.testDate = testDate;
        this.testAdd = testAdd;
        this.businessRepresent = businessRepresent;
        this.businessSigndate = businessSigndate;
        this.businessSignAdd = businessSignAdd;
        this.agencyRepresent = agencyRepresent;
        this.agencySignAdd = agencySignAdd;
        this.agencySigndate = agencySigndate;
        this.standardTargetNo = standardTargetNo;
        this.standardTargetDate = standardTargetDate;
        this.releaseDocumentNo = releaseDocumentNo;
        this.releaseDocumentDate = releaseDocumentDate;
        this.isActive = isActive;
        this.importContractCode = importContractCode;
        this.importContractDate = importContractDate;
        this.fileId = fileId;
    }

    public ConfirmImportSatistPaper convertToEntity() {
        ConfirmImportSatistPaper bo = new ConfirmImportSatistPaper();
        bo.setConfirmImportSatistPaperId(this.confirmImportSatistPaperId);
        bo.setTestAgencyName(this.testAgencyName);
        bo.setTestAgencyId(this.testAgencyId);
        bo.setTestAgencyAdd(this.testAgencyAdd);
        bo.setTestAgencyTel(this.testAgencyTel);
        bo.setTestAgencyFax(this.testAgencyFax);
        bo.setConclusion(this.conclusion);
        bo.setExportBusinessName(this.exportBusinessName);
        bo.setExportBusinessAdd(this.exportBusinessAdd);
        bo.setExportBusinessMail(this.exportBusinessMail);
        bo.setExportBusinessTel(this.exportBusinessTel);
        bo.setExportBusinessFax(this.exportBusinessFax);
        bo.setExportContractCode(this.exportContractCode);
        bo.setExportContractDate(this.exportContractDate);
        bo.setExportLadingCode(this.exportLadingCode);
        bo.setExportLadingDate(this.exportLadingDate);
        bo.setExportPort(this.exportPort);
        bo.setImportBusinessName(this.importBusinessName);
        bo.setImportBusinessAddress(this.importBusinessAddress);
        bo.setImportBusinessEmail(this.importBusinessEmail);
        bo.setImportBusinessTel(this.importBusinessTel);
        bo.setImportBusinessFax(this.importBusinessFax);
        bo.setImportPort(this.importPort);
        bo.setImportDate(this.importDate);
        bo.setProductName(this.productName);
        bo.setProductDescription(this.productDescription);
        bo.setProductCode(this.productCode);
        bo.setProductOrigin(this.productOrigin);
        bo.setProductAmount(this.productAmount);
        bo.setProductWeight(this.productWeight);
        bo.setProductValue(this.productValue);
        bo.setGatheringAdd(this.gatheringAdd);
        bo.setTestDate(this.testDate);
        bo.setTestAdd(this.testAdd);
        bo.setBusinessRepresent(this.businessRepresent);
        bo.setBusinessSigndate(this.businessSigndate);
        bo.setBusinessSignAdd(this.businessSignAdd);
        bo.setAgencyRepresent(this.agencyRepresent);
        bo.setAgencySignAdd(this.agencySignAdd);
        bo.setAgencySigndate(this.agencySigndate);
        bo.setStandardTargetNo(this.standardTargetNo);
        bo.setStandardTargetDate(this.standardTargetDate);
        bo.setReleaseDocumentNo(this.releaseDocumentNo);
        bo.setReleaseDocumentDate(this.releaseDocumentDate);
        bo.setIsActive(this.isActive);
        bo.setImportContractCode(importContractCode);
        bo.setImportContractDate(importContractDate);

        return bo;
    }

    public Long getConfirmImportSatistPaperId() {
        return confirmImportSatistPaperId;
    }

    public void setConfirmImportSatistPaperId(Long confirmImportSatistPaperId) {
        this.confirmImportSatistPaperId = confirmImportSatistPaperId;
    }

    public String getTestAgencyName() {
        return testAgencyName;
    }

    public void setTestAgencyName(String testAgencyName) {
        this.testAgencyName = testAgencyName;
    }

    public Long getTestAgencyId() {
        return testAgencyId;
    }

    public void setTestAgencyId(Long testAgencyId) {
        this.testAgencyId = testAgencyId;
    }

    public String getTestAgencyAdd() {
        return testAgencyAdd;
    }

    public void setTestAgencyAdd(String testAgencyAdd) {
        this.testAgencyAdd = testAgencyAdd;
    }

    public String getTestAgencyTel() {
        return testAgencyTel;
    }

    public void setTestAgencyTel(String testAgencyTel) {
        this.testAgencyTel = testAgencyTel;
    }

    public String getTestAgencyFax() {
        return testAgencyFax;
    }

    public void setTestAgencyFax(String testAgencyFax) {
        this.testAgencyFax = testAgencyFax;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getExportBusinessName() {
        return exportBusinessName;
    }

    public void setExportBusinessName(String exportBusinessName) {
        this.exportBusinessName = exportBusinessName;
    }

    public String getExportBusinessAdd() {
        return exportBusinessAdd;
    }

    public void setExportBusinessAdd(String exportBusinessAdd) {
        this.exportBusinessAdd = exportBusinessAdd;
    }

    public String getExportBusinessMail() {
        return exportBusinessMail;
    }

    public void setExportBusinessMail(String exportBusinessMail) {
        this.exportBusinessMail = exportBusinessMail;
    }

    public String getExportBusinessTel() {
        return exportBusinessTel;
    }

    public void setExportBusinessTel(String exportBusinessTel) {
        this.exportBusinessTel = exportBusinessTel;
    }

    public String getExportBusinessFax() {
        return exportBusinessFax;
    }

    public void setExportBusinessFax(String exportBusinessFax) {
        this.exportBusinessFax = exportBusinessFax;
    }

    public String getExportContractCode() {
        return exportContractCode;
    }

    public void setExportContractCode(String exportContractCode) {
        this.exportContractCode = exportContractCode;
    }

    public Date getExportContractDate() {
        return exportContractDate;
    }

    public void setExportContractDate(Date exportContractDate) {
        this.exportContractDate = exportContractDate;
    }

    public String getExportLadingCode() {
        return exportLadingCode;
    }

    public void setExportLadingCode(String exportLadingCode) {
        this.exportLadingCode = exportLadingCode;
    }

    public Date getExportLadingDate() {
        return exportLadingDate;
    }

    public void setExportLadingDate(Date exportLadingDate) {
        this.exportLadingDate = exportLadingDate;
    }

    public String getExportPort() {
        return exportPort;
    }

    public void setExportPort(String exportPort) {
        this.exportPort = exportPort;
    }

    public String getImportBusinessName() {
        return importBusinessName;
    }

    public void setImportBusinessName(String importBusinessName) {
        this.importBusinessName = importBusinessName;
    }

    public String getImportBusinessAddress() {
        return importBusinessAddress;
    }

    public void setImportBusinessAddress(String importBusinessAddress) {
        this.importBusinessAddress = importBusinessAddress;
    }

    public String getImportBusinessEmail() {
        return importBusinessEmail;
    }

    public void setImportBusinessEmail(String importBusinessEmail) {
        this.importBusinessEmail = importBusinessEmail;
    }

    public String getImportBusinessTel() {
        return importBusinessTel;
    }

    public void setImportBusinessTel(String importBusinessTel) {
        this.importBusinessTel = importBusinessTel;
    }

    public String getImportBusinessFax() {
        return importBusinessFax;
    }

    public void setImportBusinessFax(String importBusinessFax) {
        this.importBusinessFax = importBusinessFax;
    }

    public String getImportPort() {
        return importPort;
    }

    public void setImportPort(String importPort) {
        this.importPort = importPort;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductOrigin() {
        return productOrigin;
    }

    public void setProductOrigin(String productOrigin) {
        this.productOrigin = productOrigin;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductValue() {
        return productValue;
    }

    public void setProductValue(String productValue) {
        this.productValue = productValue;
    }

    public String getGatheringAdd() {
        return gatheringAdd;
    }

    public void setGatheringAdd(String gatheringAdd) {
        this.gatheringAdd = gatheringAdd;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public String getTestAdd() {
        return testAdd;
    }

    public void setTestAdd(String testAdd) {
        this.testAdd = testAdd;
    }

    public String getBusinessRepresent() {
        return businessRepresent;
    }

    public void setBusinessRepresent(String businessRepresent) {
        this.businessRepresent = businessRepresent;
    }

    public Date getBusinessSigndate() {
        return businessSigndate;
    }

    public void setBusinessSigndate(Date businessSigndate) {
        this.businessSigndate = businessSigndate;
    }

    public String getBusinessSignAdd() {
        return businessSignAdd;
    }

    public void setBusinessSignAdd(String businessSignAdd) {
        this.businessSignAdd = businessSignAdd;
    }

    public String getAgencyRepresent() {
        return agencyRepresent;
    }

    public void setAgencyRepresent(String agencyRepresent) {
        this.agencyRepresent = agencyRepresent;
    }

    public String getAgencySignAdd() {
        return agencySignAdd;
    }

    public void setAgencySignAdd(String agencySignAdd) {
        this.agencySignAdd = agencySignAdd;
    }

    public Date getAgencySigndate() {
        return agencySigndate;
    }

    public void setAgencySigndate(Date agencySigndate) {
        this.agencySigndate = agencySigndate;
    }

    public String getStandardTargetNo() {
        return standardTargetNo;
    }

    public void setStandardTargetNo(String standardTargetNo) {
        this.standardTargetNo = standardTargetNo;
    }

    public Date getStandardTargetDate() {
        return standardTargetDate;
    }

    public void setStandardTargetDate(Date standardTargetDate) {
        this.standardTargetDate = standardTargetDate;
    }

    public String getReleaseDocumentNo() {
        return releaseDocumentNo;
    }

    public void setReleaseDocumentNo(String releaseDocumentNo) {
        this.releaseDocumentNo = releaseDocumentNo;
    }

    public Date getReleaseDocumentDate() {
        return releaseDocumentDate;
    }

    public void setReleaseDocumentDate(Date releaseDocumentDate) {
        this.releaseDocumentDate = releaseDocumentDate;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public String getImportContractCode() {
        return importContractCode;
    }

    public void setImportContractCode(String importContractCode) {
        this.importContractCode = importContractCode;
    }

    public Date getImportContractDate() {
        return importContractDate;
    }

    public void setImportContractDate(Date importContractDate) {
        this.importContractDate = importContractDate;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
}
