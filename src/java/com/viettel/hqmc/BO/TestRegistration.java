/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.DAOHE.TestRegistrationDAOHE;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 *
 * @author vtit_havm2
 */
@Entity
@Table(name = "TEST_REGISTRATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestRegistration.findAll", query = "SELECT t FROM TestRegistration t"),
    @NamedQuery(name = "TestRegistration.findByTestRegistrationId", query = "SELECT t FROM TestRegistration t WHERE t.testRegistrationId = :testRegistrationId"),
    @NamedQuery(name = "TestRegistration.findByExportBusinessName", query = "SELECT t FROM TestRegistration t WHERE t.exportBusinessName = :exportBusinessName"),
    @NamedQuery(name = "TestRegistration.findByExportBusinessAdd", query = "SELECT t FROM TestRegistration t WHERE t.exportBusinessAdd = :exportBusinessAdd"),
    @NamedQuery(name = "TestRegistration.findByExportBusinessMail", query = "SELECT t FROM TestRegistration t WHERE t.exportBusinessMail = :exportBusinessMail"),
    @NamedQuery(name = "TestRegistration.findByExportBusinessTel", query = "SELECT t FROM TestRegistration t WHERE t.exportBusinessTel = :exportBusinessTel"),
    @NamedQuery(name = "TestRegistration.findByExportBusinessFax", query = "SELECT t FROM TestRegistration t WHERE t.exportBusinessFax = :exportBusinessFax"),
    @NamedQuery(name = "TestRegistration.findByExportContractCode", query = "SELECT t FROM TestRegistration t WHERE t.exportContractCode = :exportContractCode"),
    @NamedQuery(name = "TestRegistration.findByExportContractDate", query = "SELECT t FROM TestRegistration t WHERE t.exportContractDate = :exportContractDate"),
    @NamedQuery(name = "TestRegistration.findByExportLadingCode", query = "SELECT t FROM TestRegistration t WHERE t.exportLadingCode = :exportLadingCode"),
    @NamedQuery(name = "TestRegistration.findByExportLadingDate", query = "SELECT t FROM TestRegistration t WHERE t.exportLadingDate = :exportLadingDate"),
    @NamedQuery(name = "TestRegistration.findByExportPort", query = "SELECT t FROM TestRegistration t WHERE t.exportPort = :exportPort"),
    @NamedQuery(name = "TestRegistration.findByImportBusinessName", query = "SELECT t FROM TestRegistration t WHERE t.importBusinessName = :importBusinessName"),
    @NamedQuery(name = "TestRegistration.findByImportBusinessAddress", query = "SELECT t FROM TestRegistration t WHERE t.importBusinessAddress = :importBusinessAddress"),
    @NamedQuery(name = "TestRegistration.findByImportBusinessEmail", query = "SELECT t FROM TestRegistration t WHERE t.importBusinessEmail = :importBusinessEmail"),
    @NamedQuery(name = "TestRegistration.findByImportBusinessTel", query = "SELECT t FROM TestRegistration t WHERE t.importBusinessTel = :importBusinessTel"),
    @NamedQuery(name = "TestRegistration.findByImportBusinessFax", query = "SELECT t FROM TestRegistration t WHERE t.importBusinessFax = :importBusinessFax"),
    @NamedQuery(name = "TestRegistration.findByImportPort", query = "SELECT t FROM TestRegistration t WHERE t.importPort = :importPort"),
    @NamedQuery(name = "TestRegistration.findByImportDate", query = "SELECT t FROM TestRegistration t WHERE t.importDate = :importDate"),
    @NamedQuery(name = "TestRegistration.findByProductName", query = "SELECT t FROM TestRegistration t WHERE t.productName = :productName"),
    @NamedQuery(name = "TestRegistration.findByProductDescription", query = "SELECT t FROM TestRegistration t WHERE t.productDescription = :productDescription"),
    @NamedQuery(name = "TestRegistration.findByProductCode", query = "SELECT t FROM TestRegistration t WHERE t.productCode = :productCode"),
    @NamedQuery(name = "TestRegistration.findByProductOrigin", query = "SELECT t FROM TestRegistration t WHERE t.productOrigin = :productOrigin"),
    @NamedQuery(name = "TestRegistration.findByProductAmount", query = "SELECT t FROM TestRegistration t WHERE t.productAmount = :productAmount"),
    @NamedQuery(name = "TestRegistration.findByProductWeight", query = "SELECT t FROM TestRegistration t WHERE t.productWeight = :productWeight"),
    @NamedQuery(name = "TestRegistration.findByProductValue", query = "SELECT t FROM TestRegistration t WHERE t.productValue = :productValue"),
    @NamedQuery(name = "TestRegistration.findByGatheringAdd", query = "SELECT t FROM TestRegistration t WHERE t.gatheringAdd = :gatheringAdd"),
    @NamedQuery(name = "TestRegistration.findByTestDate", query = "SELECT t FROM TestRegistration t WHERE t.testDate = :testDate"),
    @NamedQuery(name = "TestRegistration.findByTestAdd", query = "SELECT t FROM TestRegistration t WHERE t.testAdd = :testAdd"),
    @NamedQuery(name = "TestRegistration.findByBusinessRepresent", query = "SELECT t FROM TestRegistration t WHERE t.businessRepresent = :businessRepresent"),
    @NamedQuery(name = "TestRegistration.findByBusinessSigndate", query = "SELECT t FROM TestRegistration t WHERE t.businessSigndate = :businessSigndate"),
    @NamedQuery(name = "TestRegistration.findByBusinessSignAdd", query = "SELECT t FROM TestRegistration t WHERE t.businessSignAdd = :businessSignAdd"),
    @NamedQuery(name = "TestRegistration.findByAgencyRepresent", query = "SELECT t FROM TestRegistration t WHERE t.agencyRepresent = :agencyRepresent"),
    @NamedQuery(name = "TestRegistration.findByAgencySignAdd", query = "SELECT t FROM TestRegistration t WHERE t.agencySignAdd = :agencySignAdd"),
    @NamedQuery(name = "TestRegistration.findByAgencySigndate", query = "SELECT t FROM TestRegistration t WHERE t.agencySigndate = :agencySigndate"),
    @NamedQuery(name = "TestRegistration.findByStandardTargetNo", query = "SELECT t FROM TestRegistration t WHERE t.standardTargetNo = :standardTargetNo"),
    @NamedQuery(name = "TestRegistration.findByStandardTargetDate", query = "SELECT t FROM TestRegistration t WHERE t.standardTargetDate = :standardTargetDate"),
    @NamedQuery(name = "TestRegistration.findByReleaseDocumentNo", query = "SELECT t FROM TestRegistration t WHERE t.releaseDocumentNo = :releaseDocumentNo"),
    @NamedQuery(name = "TestRegistration.findByReleaseDocumentDate", query = "SELECT t FROM TestRegistration t WHERE t.releaseDocumentDate = :releaseDocumentDate"),
    @NamedQuery(name = "TestRegistration.findByIsActive", query = "SELECT t FROM TestRegistration t WHERE t.isActive = :isActive")})
public class TestRegistration implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "TEST_REGISTRATION_SEQ", sequenceName = "TEST_REGISTRATION_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEST_REGISTRATION_SEQ")
    @Basic(optional = false)
    @Column(name = "TEST_REGISTRATION_ID")
    private Long testRegistrationId;
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
    @Column(name = "TEST_AGENCY")
    private String testAgency;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "IS_TEMP")
    private Long isTemp;
    @Column(name = "ORIGINAL_ID")
    private Long originalId;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "LAST_IS_TEMP")
    private Long lastIsTemp;

    public TestRegistration() {
    }

    public TestRegistration(Long testRegistrationId) {
        this.testRegistrationId = testRegistrationId;
    }

    public TestRegistration cloneEntity(TestRegistration original) {
        TestRegistration entity = new TestRegistration();

        //entity.setTestRegistrationId(original.getTestRegistrationId());
        entity.setExportBusinessName(original.getExportBusinessName());
        entity.setExportBusinessAdd(original.getExportBusinessAdd());
        entity.setExportBusinessMail(original.getExportBusinessMail());
        entity.setExportBusinessTel(original.getExportBusinessTel());
        entity.setExportBusinessFax(original.getExportBusinessFax());
        entity.setExportContractCode(original.getExportContractCode());
        entity.setExportContractDate(original.getExportContractDate());
        entity.setExportLadingCode(original.getExportLadingCode());
        entity.setExportLadingDate(original.getExportLadingDate());
        entity.setExportPort(original.getExportPort());
        entity.setImportBusinessName(original.getImportBusinessName());
        entity.setImportBusinessAddress(original.getImportBusinessAddress());
        entity.setImportBusinessEmail(original.getImportBusinessEmail());
        entity.setImportBusinessTel(original.getImportBusinessTel());
        entity.setImportBusinessFax(original.getImportBusinessFax());
        entity.setImportPort(original.getImportPort());
        entity.setImportDate(original.getImportDate());
        entity.setProductName(original.getProductName());
        entity.setProductDescription(original.getProductDescription());
        entity.setProductCode(original.getProductCode());
        entity.setProductOrigin(original.getProductOrigin());
        entity.setProductAmount(original.getProductAmount());
        entity.setProductWeight(original.getProductWeight());
        entity.setProductValue(original.getProductValue());
        entity.setGatheringAdd(original.getGatheringAdd());
        entity.setTestDate(original.getTestDate());
        entity.setTestAdd(original.getTestAdd());
        entity.setBusinessRepresent(original.getBusinessRepresent());
        entity.setBusinessSigndate(original.getBusinessSigndate());
        entity.setBusinessSignAdd(original.getBusinessSignAdd());
        entity.setAgencyRepresent(original.getAgencyRepresent());
        entity.setAgencySignAdd(original.getAgencySignAdd());
        entity.setAgencySigndate(original.getAgencySigndate());
        entity.setStandardTargetNo(original.getStandardTargetNo());
        entity.setStandardTargetDate(original.getStandardTargetDate());
        entity.setReleaseDocumentNo(original.getReleaseDocumentNo());
        entity.setReleaseDocumentDate(original.getReleaseDocumentDate());
        entity.setTestAgency(original.getTestAgency());
        entity.setIsActive(original.getIsActive());
        entity.setIsTemp(1L);
        entity.setOriginalId(original.getTestRegistrationId());

        entity.setLastIsTemp(1L);
        TestRegistrationDAOHE anndaohe = new TestRegistrationDAOHE();
        entity.setVersion(anndaohe.getCountVersion(original.getTestRegistrationId()));
        return entity;
    }

    public TestRegistration cloneEntity() {
        //entity.setTestRegistrationId(original.getTestRegistrationId());
        return cloneEntity(this);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (testRegistrationId != null ? testRegistrationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestRegistration)) {
            return false;
        }
        TestRegistration other = (TestRegistration) object;
        if ((this.testRegistrationId == null && other.testRegistrationId != null) || (this.testRegistrationId != null && !this.testRegistrationId.equals(other.testRegistrationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.TestRegistration[ testRegistrationId=" + testRegistrationId + " ]";
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

    public String getTestAgency() {
        return testAgency;
    }

    public void setTestAgency(String testAgency) {
        this.testAgency = StringUtils.removeEventHandlerJS(testAgency);
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(Long isTemp) {
        this.isTemp = isTemp;
    }

    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Long originalId) {
        this.originalId = originalId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getLastIsTemp() {
        return lastIsTemp;
    }

    public void setLastIsTemp(Long lastIsTemp) {
        this.lastIsTemp = lastIsTemp;
    }

}
