/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.DAOHE.ReIssueFormDAOHE;
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
@Table(name = "RE_ISSUE_FORM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReIssueForm.findAll", query = "SELECT r FROM ReIssueForm r"),
    @NamedQuery(name = "ReIssueForm.findByReIssueFormId", query = "SELECT r FROM ReIssueForm r WHERE r.reIssueFormId = :reIssueFormId"),
    @NamedQuery(name = "ReIssueForm.findByIdentificationNumber", query = "SELECT r FROM ReIssueForm r WHERE r.identificationNumber = :identificationNumber"),
    @NamedQuery(name = "ReIssueForm.findByAddress", query = "SELECT r FROM ReIssueForm r WHERE r.address = :address"),
    @NamedQuery(name = "ReIssueForm.findByTelephone", query = "SELECT r FROM ReIssueForm r WHERE r.telephone = :telephone"),
    @NamedQuery(name = "ReIssueForm.findByFax", query = "SELECT r FROM ReIssueForm r WHERE r.fax = :fax"),
    @NamedQuery(name = "ReIssueForm.findByEmail", query = "SELECT r FROM ReIssueForm r WHERE r.email = :email"),
    @NamedQuery(name = "ReIssueForm.findByFormNumber", query = "SELECT r FROM ReIssueForm r WHERE r.formNumber = :formNumber"),
    @NamedQuery(name = "ReIssueForm.findByIssueDate", query = "SELECT r FROM ReIssueForm r WHERE r.issueDate = :issueDate"),
    @NamedQuery(name = "ReIssueForm.findByIssueAgency", query = "SELECT r FROM ReIssueForm r WHERE r.issueAgency = :issueAgency"),
    @NamedQuery(name = "ReIssueForm.findBySignDate", query = "SELECT r FROM ReIssueForm r WHERE r.signDate = :signDate"),
    @NamedQuery(name = "ReIssueForm.findByIsActive", query = "SELECT r FROM ReIssueForm r WHERE r.isActive = :isActive")})
public class ReIssueForm implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "RE_ISSUE_FORM_SEQ", sequenceName = "RE_ISSUE_FORM_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RE_ISSUE_FORM_SEQ")
    @Basic(optional = false)
    @Column(name = "RE_ISSUE_FORM_ID")
    private Long reIssueFormId;
    @Column(name = "IDENTIFICATION_NUMBER")
    private String identificationNumber;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "TELEPHONE")
    private String telephone;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FORM_NUMBER")
    private String formNumber;
    @Column(name = "ISSUE_DATE")
    @Temporal(TemporalType.DATE)
    private Date issueDate;
    @Column(name = "ISSUE_AGENCY")
    private String issueAgency;
    @Column(name = "SIGN_DATE")
    @Temporal(TemporalType.DATE)
    private Date signDate;
    @Column(name = "DOCUMENT_NO")
    private String documentNo;
    @Column(name = "DOCUMENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date documentDate;
    @Column(name = "BUSINESS_NAME")
    private String businessName;
    @Column(name = "NEAREST_FILE_NO")
    private String nearestFileNo;
    @Column(name = "RE_ISSUE_AGENCY_ID")
    private Long reIssueAgencyId;
    @Column(name = "RE_ISSUE_AGENCY")
    private String reIssueAgency;
    @Column(name = "RE_ISSUE_DATE")
    @Temporal(TemporalType.DATE)
    private Date reIssueDate;
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

    public ReIssueForm() {
    }

    public ReIssueForm(Long reIssueFormId) {
        this.reIssueFormId = reIssueFormId;
    }

    public ReIssueForm(Long reIssueFormId, Date signDate) {
        this.reIssueFormId = reIssueFormId;
        this.signDate = signDate;
    }

    public ReIssueForm cloneEntity(ReIssueForm original) {
        ReIssueForm entity = new ReIssueForm();

        entity.setReIssueFormId(original.getReIssueFormId());
        entity.setIdentificationNumber(original.getIdentificationNumber());
        entity.setAddress(original.getAddress());
        entity.setTelephone(original.getTelephone());
        entity.setFax(original.getFax());
        entity.setEmail(original.getEmail());
        entity.setFormNumber(original.getFormNumber());
        entity.setIssueDate(original.getIssueDate());
        entity.setIssueAgency(original.getIssueAgency());
        entity.setSignDate(original.getSignDate());
        entity.setDocumentNo(original.getDocumentNo());
        entity.setDocumentDate(original.getDocumentDate());
        entity.setBusinessName(original.getBusinessName());
        entity.setNearestFileNo(original.getNearestFileNo());
        entity.setReIssueAgencyId(original.getReIssueAgencyId());
        entity.setReIssueAgency(original.getReIssueAgency());
        entity.setReIssueDate(original.getReIssueDate());
        entity.setIsActive(original.getIsActive());
        entity.setIsTemp(original.getIsTemp());
        entity.setOriginalId(original.getOriginalId());

        entity.setLastIsTemp(1L);
        ReIssueFormDAOHE anndaohe = new ReIssueFormDAOHE();
        entity.setVersion(anndaohe.getCountVersion(original.getReIssueFormId()));
        return entity;
    }

    public ReIssueForm cloneEntity() {
        return cloneEntity(this);
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
        this.identificationNumber = StringUtils.removeEventHandlerJS(identificationNumber);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = StringUtils.removeEventHandlerJS(address);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = StringUtils.removeEventHandlerJS(telephone);
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = StringUtils.removeEventHandlerJS(fax);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = StringUtils.removeEventHandlerJS(email);
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = StringUtils.removeEventHandlerJS(formNumber);
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueAgency() {
        return issueAgency;
    }

    public void setIssueAgency(String issueAgency) {
        this.issueAgency = StringUtils.removeEventHandlerJS(issueAgency);
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = StringUtils.removeEventHandlerJS(documentNo);
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = StringUtils.removeEventHandlerJS(businessName);
    }

    public String getNearestFileNo() {
        return nearestFileNo;
    }

    public void setNearestFileNo(String nearestFileNo) {
        this.nearestFileNo = StringUtils.removeEventHandlerJS(nearestFileNo);
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
        this.reIssueAgency = StringUtils.removeEventHandlerJS(reIssueAgency);
    }

    public Date getReIssueDate() {
        return reIssueDate;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reIssueFormId != null ? reIssueFormId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReIssueForm)) {
            return false;
        }
        ReIssueForm other = (ReIssueForm) object;
        if ((this.reIssueFormId == null && other.reIssueFormId != null) || (this.reIssueFormId != null && !this.reIssueFormId.equals(other.reIssueFormId))) {
            return false;
        }
        return true;
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

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.ReIssueForm[ reIssueFormId=" + reIssueFormId + " ]";
    }
}
