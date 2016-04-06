/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vtit_havm2
 */
public class TestRegistrationForm implements Serializable {

    private Long testRegistrationId;
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
    private String testAgency;
    private Long isActive;

    public TestRegistrationForm() {
    }

    public TestRegistrationForm(TestRegistration entity) {
        if (entity == null) {
            return;
        }
        testRegistrationId = entity.getTestRegistrationId();
        exportBusinessName = entity.getExportBusinessName();
        exportBusinessAdd = entity.getExportBusinessAdd();
        exportBusinessMail = entity.getExportBusinessMail();
        exportBusinessTel = entity.getExportBusinessTel();
        exportBusinessFax = entity.getExportBusinessFax();
        exportContractCode = entity.getExportContractCode();
        exportContractDate = entity.getExportContractDate();
        exportLadingCode = entity.getExportLadingCode();
        exportLadingDate = entity.getExportLadingDate();
        exportPort = entity.getExportPort();
        importBusinessName = entity.getImportBusinessName();
        importBusinessAddress = entity.getImportBusinessAddress();
        importBusinessEmail = entity.getImportBusinessEmail();
        importBusinessTel = entity.getImportBusinessTel();
        importBusinessFax = entity.getImportBusinessFax();
        importPort = entity.getImportPort();
        importDate = entity.getImportDate();
        productName = entity.getProductName();
        productDescription = entity.getProductDescription();
        productCode = entity.getProductCode();
        productOrigin = entity.getProductOrigin();
        productAmount = entity.getProductAmount();
        productWeight = entity.getProductWeight();
        productValue = entity.getProductValue();
        gatheringAdd = entity.getGatheringAdd();
        testDate = entity.getTestDate();
        testAdd = entity.getTestAdd();
        businessRepresent = entity.getBusinessRepresent();
        businessSigndate = entity.getBusinessSigndate();
        businessSignAdd = entity.getBusinessSignAdd();
        agencyRepresent = entity.getAgencyRepresent();
        agencySignAdd = entity.getAgencySignAdd();
        agencySigndate = entity.getAgencySigndate();
        standardTargetNo = entity.getStandardTargetNo();
        standardTargetDate = entity.getStandardTargetDate();
        releaseDocumentNo = entity.getReleaseDocumentNo();
        releaseDocumentDate = entity.getReleaseDocumentDate();
        testAgency = entity.getTestAgency();
        isActive = entity.getIsActive();
    }
    
    public TestRegistration convertToEntity(){
        TestRegistration entity = new TestRegistration();
         entity.setTestRegistrationId(testRegistrationId);
         entity.setExportBusinessName(exportBusinessName);
         entity.setExportBusinessAdd(exportBusinessAdd);
         entity.setExportBusinessMail(exportBusinessMail);
         entity.setExportBusinessTel(exportBusinessTel);
         entity.setExportBusinessFax(exportBusinessFax);
         entity.setExportContractCode(exportContractCode);
         entity.setExportContractDate(exportContractDate);
         entity.setExportLadingCode(exportLadingCode);
         entity.setExportLadingDate(exportLadingDate);
         entity.setExportPort(exportPort);
         entity.setImportBusinessName(importBusinessName);
         entity.setImportBusinessAddress(importBusinessAddress);
         entity.setImportBusinessEmail(importBusinessEmail);
         entity.setImportBusinessEmail(importBusinessTel);
         entity.setImportBusinessFax(importBusinessFax);
         entity.setImportPort(importPort);
         entity.setImportDate(importDate);
         entity.setProductName(productName);
         entity.setProductDescription(productDescription);
         entity.setProductCode(productCode);
         entity.setProductOrigin(productOrigin);
         entity.setProductAmount(productAmount);
         entity.setProductWeight(productWeight);
         entity.setProductValue(productValue);
         entity.setGatheringAdd(gatheringAdd);
         entity.setTestDate(testDate);
         entity.setTestAdd(testAdd);
         entity.setBusinessRepresent(businessRepresent);
         entity.setBusinessSigndate(businessSigndate);
         entity.setBusinessSignAdd(businessSignAdd);
         entity.setAgencyRepresent(agencyRepresent);
         entity.setAgencySignAdd(agencySignAdd);
         entity.setAgencySigndate(agencySigndate);
         entity.setStandardTargetNo(standardTargetNo);
         entity.setStandardTargetDate(standardTargetDate);
         entity.setReleaseDocumentNo(releaseDocumentNo);
         entity.setReleaseDocumentDate(releaseDocumentDate);
         entity.setTestAgency(testAgency);
         if(isActive == null){
             isActive = 1l;
         }
         entity.setIsActive(isActive);
         return entity;
    }

    public Long getTestRegistrationId() {
        return testRegistrationId;
    }

    public void setTestRegistrationId(Long testRegistrationId) {
        this.testRegistrationId = testRegistrationId;
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

    public String getTestAgency() {
        return testAgency;
    }

    public void setTestAgency(String testAgency) {
        this.testAgency = testAgency;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }
}
