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
@Table(name = "CONFIRM_IMPORT_SATIST_PAPER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfirmImportSatistPaper.findAll", query = "SELECT c FROM ConfirmImportSatistPaper c"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByConfirmImportSatistPaperId", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.confirmImportSatistPaperId = :confirmImportSatistPaperId"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByTestAgencyName", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.testAgencyName = :testAgencyName"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByTestAgencyId", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.testAgencyId = :testAgencyId"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByTestAgencyAdd", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.testAgencyAdd = :testAgencyAdd"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByTestAgencyTel", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.testAgencyTel = :testAgencyTel"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByTestAgencyFax", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.testAgencyFax = :testAgencyFax"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByConclusion", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.conclusion = :conclusion"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByExportBusinessName", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.exportBusinessName = :exportBusinessName"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByExportBusinessAdd", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.exportBusinessAdd = :exportBusinessAdd"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByExportBusinessMail", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.exportBusinessMail = :exportBusinessMail"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByExportBusinessTel", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.exportBusinessTel = :exportBusinessTel"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByExportBusinessFax", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.exportBusinessFax = :exportBusinessFax"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByExportContractCode", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.exportContractCode = :exportContractCode"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByExportContractDate", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.exportContractDate = :exportContractDate"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByExportLadingCode", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.exportLadingCode = :exportLadingCode"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByExportLadingDate", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.exportLadingDate = :exportLadingDate"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByExportPort", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.exportPort = :exportPort"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByImportBusinessName", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.importBusinessName = :importBusinessName"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByImportBusinessAddress", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.importBusinessAddress = :importBusinessAddress"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByImportBusinessEmail", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.importBusinessEmail = :importBusinessEmail"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByImportBusinessTel", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.importBusinessTel = :importBusinessTel"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByImportBusinessFax", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.importBusinessFax = :importBusinessFax"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByImportPort", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.importPort = :importPort"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByImportDate", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.importDate = :importDate"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByProductName", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.productName = :productName"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByProductDescription", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.productDescription = :productDescription"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByProductCode", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.productCode = :productCode"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByProductOrigin", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.productOrigin = :productOrigin"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByProductAmount", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.productAmount = :productAmount"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByProductWeight", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.productWeight = :productWeight"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByProductValue", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.productValue = :productValue"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByGatheringAdd", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.gatheringAdd = :gatheringAdd"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByTestDate", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.testDate = :testDate"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByTestAdd", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.testAdd = :testAdd"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByBusinessRepresent", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.businessRepresent = :businessRepresent"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByBusinessSigndate", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.businessSigndate = :businessSigndate"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByBusinessSignAdd", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.businessSignAdd = :businessSignAdd"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByAgencyRepresent", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.agencyRepresent = :agencyRepresent"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByAgencySignAdd", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.agencySignAdd = :agencySignAdd"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByAgencySigndate", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.agencySigndate = :agencySigndate"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByStandardTargetNo", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.standardTargetNo = :standardTargetNo"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByStandardTargetDate", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.standardTargetDate = :standardTargetDate"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByReleaseDocumentNo", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.releaseDocumentNo = :releaseDocumentNo"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByReleaseDocumentDate", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.releaseDocumentDate = :releaseDocumentDate"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByIsActive", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.isActive = :isActive"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByImportContractCode", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.importContractCode = :importContractCode"),
    @NamedQuery(name = "ConfirmImportSatistPaper.findByImportContractDate", query = "SELECT c FROM ConfirmImportSatistPaper c WHERE c.importContractDate = :importContractDate")})
public class ConfirmImportSatistPaper implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "CONFIRM_IMPORT_SATIST_SEQ", sequenceName = "CONFIRM_IMPORT_SATIST_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONFIRM_IMPORT_SATIST_SEQ")
    @Basic(optional = false)
    @Column(name = "CONFIRM_IMPORT_SATIST_PAPER_ID")
    private Long confirmImportSatistPaperId;
    @Column(name = "TEST_AGENCY_NAME")
    private String testAgencyName;
    @Column(name = "TEST_AGENCY_ID")
    private Long testAgencyId;
    @Column(name = "TEST_AGENCY_ADD")
    private String testAgencyAdd;
    @Column(name = "TEST_AGENCY_TEL")
    private String testAgencyTel;
    @Column(name = "TEST_AGENCY_FAX")
    private String testAgencyFax;
    @Column(name = "CONCLUSION")
    private String conclusion;
    @Column(name = "EXPORT_BUSINESS_NAME")
    private String exportBusinessName;
    @Column(name = "EXPORT_BUSINESS_ADD")
    private String exportBusinessAdd;
    @Column(name = "EXPORT_BUSINESS_MAIL")
    private String exportBusinessMail;
    @Column(name = "EXPORT_BUSINESS_TEL")
    private String exportBusinessTel;
    @Column(name = "EXPORT_BUSINESS_FAX")
    private String exportBusinessFax;
    @Column(name = "EXPORT_CONTRACT_CODE")
    private String exportContractCode;
    @Column(name = "EXPORT_CONTRACT_DATE")
    @Temporal(TemporalType.DATE)
    private Date exportContractDate;
    @Column(name = "EXPORT_LADING_CODE")
    private String exportLadingCode;
    @Column(name = "EXPORT_LADING_DATE")
    @Temporal(TemporalType.DATE)
    private Date exportLadingDate;
    @Column(name = "EXPORT_PORT")
    private String exportPort;
    @Column(name = "IMPORT_BUSINESS_NAME")
    private String importBusinessName;
    @Column(name = "IMPORT_BUSINESS_ADDRESS")
    private String importBusinessAddress;
    @Column(name = "IMPORT_BUSINESS_EMAIL")
    private String importBusinessEmail;
    @Column(name = "IMPORT_BUSINESS_TEL")
    private String importBusinessTel;
    @Column(name = "IMPORT_BUSINESS_FAX")
    private String importBusinessFax;
    @Column(name = "IMPORT_PORT")
    private String importPort;
    @Column(name = "IMPORT_DATE")
    @Temporal(TemporalType.DATE)
    private Date importDate;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "PRODUCT_DESCRIPTION")
    private String productDescription;
    @Column(name = "PRODUCT_CODE")
    private String productCode;
    @Column(name = "PRODUCT_ORIGIN")
    private String productOrigin;
    @Column(name = "PRODUCT_AMOUNT")
    private String productAmount;
    @Column(name = "PRODUCT_WEIGHT")
    private String productWeight;
    @Column(name = "PRODUCT_VALUE")
    private String productValue;
    @Column(name = "GATHERING_ADD")
    private String gatheringAdd;
    @Column(name = "TEST_DATE")
    @Temporal(TemporalType.DATE)
    private Date testDate;
    @Column(name = "TEST_ADD")
    private String testAdd;
    @Column(name = "BUSINESS_REPRESENT")
    private String businessRepresent;
    @Column(name = "BUSINESS_SIGNDATE")
    @Temporal(TemporalType.DATE)
    private Date businessSigndate;
    @Column(name = "BUSINESS_SIGN_ADD")
    private String businessSignAdd;
    @Column(name = "AGENCY_REPRESENT")
    private String agencyRepresent;
    @Column(name = "AGENCY_SIGN_ADD")
    private String agencySignAdd;
    @Column(name = "AGENCY_SIGNDATE")
    @Temporal(TemporalType.DATE)
    private Date agencySigndate;
    @Column(name = "STANDARD_TARGET_NO")
    private String standardTargetNo;
    @Column(name = "STANDARD_TARGET_DATE")
    @Temporal(TemporalType.DATE)
    private Date standardTargetDate;
    @Column(name = "RELEASE_DOCUMENT_NO")
    private String releaseDocumentNo;
    @Column(name = "RELEASE_DOCUMENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date releaseDocumentDate;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "IMPORT_CONTRACT_CODE")
    private String importContractCode;
    @Column(name = "IMPORT_CONTRACT_DATE")
    @Temporal(TemporalType.DATE)
    private Date importContractDate;

    public ConfirmImportSatistPaper() {
    }

    public ConfirmImportSatistPaper(Long confirmImportSatistPaperId) {
        this.confirmImportSatistPaperId = confirmImportSatistPaperId;
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
        this.testAgencyName = StringUtils.removeEventHandlerJS(testAgencyName);
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
        this.testAgencyAdd = StringUtils.removeEventHandlerJS(testAgencyAdd);
    }

    public String getTestAgencyTel() {
        return testAgencyTel;
    }

    public void setTestAgencyTel(String testAgencyTel) {
        this.testAgencyTel = StringUtils.removeEventHandlerJS(testAgencyTel);
    }

    public String getTestAgencyFax() {
        return testAgencyFax;
    }

    public void setTestAgencyFax(String testAgencyFax) {
        this.testAgencyFax = StringUtils.removeEventHandlerJS(testAgencyFax);
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
        this.exportBusinessName = StringUtils.removeEventHandlerJS(exportBusinessName);
    }

    public String getExportBusinessAdd() {
        return exportBusinessAdd;
    }

    public void setExportBusinessAdd(String exportBusinessAdd) {
        this.exportBusinessAdd = StringUtils.removeEventHandlerJS(exportBusinessAdd);
    }

    public String getExportBusinessMail() {
        return exportBusinessMail;
    }

    public void setExportBusinessMail(String exportBusinessMail) {
        this.exportBusinessMail = StringUtils.removeEventHandlerJS(exportBusinessMail);
    }

    public String getExportBusinessTel() {
        return exportBusinessTel;
    }

    public void setExportBusinessTel(String exportBusinessTel) {
        this.exportBusinessTel = StringUtils.removeEventHandlerJS(exportBusinessTel);
    }

    public String getExportBusinessFax() {
        return exportBusinessFax;
    }

    public void setExportBusinessFax(String exportBusinessFax) {
        this.exportBusinessFax = StringUtils.removeEventHandlerJS(exportBusinessFax);
    }

    public String getExportContractCode() {
        return exportContractCode;
    }

    public void setExportContractCode(String exportContractCode) {
        this.exportContractCode = StringUtils.removeEventHandlerJS(exportContractCode);
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
        this.exportLadingCode = StringUtils.removeEventHandlerJS(exportLadingCode);
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
        this.exportPort = StringUtils.removeEventHandlerJS(exportPort);
    }

    public String getImportBusinessName() {
        return importBusinessName;
    }

    public void setImportBusinessName(String importBusinessName) {
        this.importBusinessName = StringUtils.removeEventHandlerJS(importBusinessName);
    }

    public String getImportBusinessAddress() {
        return importBusinessAddress;
    }

    public void setImportBusinessAddress(String importBusinessAddress) {
        this.importBusinessAddress = StringUtils.removeEventHandlerJS(importBusinessAddress);
    }

    public String getImportBusinessEmail() {
        return importBusinessEmail;
    }

    public void setImportBusinessEmail(String importBusinessEmail) {
        this.importBusinessEmail = StringUtils.removeEventHandlerJS(importBusinessEmail);
    }

    public String getImportBusinessTel() {
        return importBusinessTel;
    }

    public void setImportBusinessTel(String importBusinessTel) {
        this.importBusinessTel = StringUtils.removeEventHandlerJS(importBusinessTel);
    }

    public String getImportBusinessFax() {
        return importBusinessFax;
    }

    public void setImportBusinessFax(String importBusinessFax) {
        this.importBusinessFax = StringUtils.removeEventHandlerJS(importBusinessFax);
    }

    public String getImportPort() {
        return importPort;
    }

    public void setImportPort(String importPort) {
        this.importPort = StringUtils.removeEventHandlerJS(importPort);
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
        this.productName = StringUtils.removeEventHandlerJS(productName);
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = StringUtils.removeEventHandlerJS(productDescription);
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = StringUtils.removeEventHandlerJS(productCode);
    }

    public String getProductOrigin() {
        return productOrigin;
    }

    public void setProductOrigin(String productOrigin) {
        this.productOrigin = StringUtils.removeEventHandlerJS(productOrigin);
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = StringUtils.removeEventHandlerJS(productAmount);
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = StringUtils.removeEventHandlerJS(productWeight);
    }

    public String getProductValue() {
        return productValue;
    }

    public void setProductValue(String productValue) {
        this.productValue = StringUtils.removeEventHandlerJS(productValue);
    }

    public String getGatheringAdd() {
        return gatheringAdd;
    }

    public void setGatheringAdd(String gatheringAdd) {
        this.gatheringAdd = StringUtils.removeEventHandlerJS(gatheringAdd);
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
        this.testAdd = StringUtils.removeEventHandlerJS(testAdd);
    }

    public String getBusinessRepresent() {
        return businessRepresent;
    }

    public void setBusinessRepresent(String businessRepresent) {
        this.businessRepresent = StringUtils.removeEventHandlerJS(businessRepresent);
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
        this.businessSignAdd = StringUtils.removeEventHandlerJS(businessSignAdd);
    }

    public String getAgencyRepresent() {
        return agencyRepresent;
    }

    public void setAgencyRepresent(String agencyRepresent) {
        this.agencyRepresent = StringUtils.removeEventHandlerJS(agencyRepresent);
    }

    public String getAgencySignAdd() {
        return agencySignAdd;
    }

    public void setAgencySignAdd(String agencySignAdd) {
        this.agencySignAdd = StringUtils.removeEventHandlerJS(agencySignAdd);
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
        this.standardTargetNo = StringUtils.removeEventHandlerJS(standardTargetNo);
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
        this.releaseDocumentNo = StringUtils.removeEventHandlerJS(releaseDocumentNo);
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
        this.importContractCode = StringUtils.removeEventHandlerJS(importContractCode);
    }

    public Date getImportContractDate() {
        return importContractDate;
    }

    public void setImportContractDate(Date importContractDate) {
        this.importContractDate = importContractDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (confirmImportSatistPaperId != null ? confirmImportSatistPaperId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfirmImportSatistPaper)) {
            return false;
        }
        ConfirmImportSatistPaper other = (ConfirmImportSatistPaper) object;
        if ((this.confirmImportSatistPaperId == null && other.confirmImportSatistPaperId != null) || (this.confirmImportSatistPaperId != null && !this.confirmImportSatistPaperId.equals(other.confirmImportSatistPaperId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.ConfirmImportSatistPaper[ confirmImportSatistPaperId=" + confirmImportSatistPaperId + " ]";
    }
    
}
