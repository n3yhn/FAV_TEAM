/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

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
@Table(name = "ANNOUNCEMENT_RECEIPT_PAPER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnnouncementReceiptPaper.findAll", query = "SELECT a FROM AnnouncementReceiptPaper a"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByAnnouncementReceiptPaperId", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.announcementReceiptPaperId = :announcementReceiptPaperId"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByReceiptNo", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.receiptNo = :receiptNo"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByReceiptDate", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.receiptDate = :receiptDate"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByReceiptDeptName", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.receiptDeptName = :receiptDeptName"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findBySignerName", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.signerName = :signerName"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findBySignDate", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.signDate = :signDate"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByBusinessName", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.businessName = :businessName"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByBusinessId", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.businessId = :businessId"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByTelephone", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.telephone = :telephone"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByFax", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.fax = :fax"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByEmail", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.email = :email"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByProductName", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.productName = :productName"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByManufactureName", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.manufactureName = :manufactureName"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByManufactureAdd", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.manufactureAdd = :manufactureAdd"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByNationName", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.nationName = :nationName"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByMatchingTarget", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.matchingTarget = :matchingTarget"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByEffectiveDate", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.effectiveDate = :effectiveDate"),
    @NamedQuery(name = "AnnouncementReceiptPaper.findByIsActive", query = "SELECT a FROM AnnouncementReceiptPaper a WHERE a.isActive = :isActive")})
public class AnnouncementReceiptPaper implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "ANNOUNCEMENT_RECEIPT_PAPER_SEQ", sequenceName = "ANNOUNCEMENT_RECEIPT_PAPER_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANNOUNCEMENT_RECEIPT_PAPER_SEQ")
    @Basic(optional = false)
    @Column(name = "ANNOUNCEMENT_RECEIPT_PAPER_ID")
    private Long announcementReceiptPaperId;
    @Column(name = "RECEIPT_NO")
    private String receiptNo;
    @Column(name = "RECEIPT_DATE")
    @Temporal(TemporalType.DATE)
    private Date receiptDate;
    @Column(name = "RECEIPT_DEPT_NAME")
    private String receiptDeptName;
    @Column(name = "SIGNER_NAME")
    private String signerName;
    @Column(name = "SIGN_DATE")
    @Temporal(TemporalType.DATE)
    private Date signDate;
    @Column(name = "BUSINESS_NAME")
    private String businessName;
    @Column(name = "BUSINESS_ID")
    private Long businessId;
    @Column(name = "TELEPHONE")
    private String telephone;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "MANUFACTURE_NAME")
    private String manufactureName;
    @Column(name = "MANUFACTURE_ADD")
    private String manufactureAdd;
    @Column(name = "NATION_NAME")
    private String nationName;
    @Column(name = "MATCHING_TARGET")
    private String matchingTarget;
    @Column(name = "EFFECTIVE_DATE")
    @Temporal(TemporalType.DATE)
    private Date effectiveDate;
    @Column(name = "IS_ACTIVE")
    private Integer isActive;
    @Column(name = "STATUS")
    private Integer status;
    public AnnouncementReceiptPaper() {
    }

    public AnnouncementReceiptPaper(Long announcementReceiptPaperId) {
        this.announcementReceiptPaperId = announcementReceiptPaperId;
    }

    public Long getAnnouncementReceiptPaperId() {
        return announcementReceiptPaperId;
    }

    public void setAnnouncementReceiptPaperId(Long announcementReceiptPaperId) {
        this.announcementReceiptPaperId = announcementReceiptPaperId;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getReceiptDeptName() {
        return receiptDeptName;
    }

    public void setReceiptDeptName(String receiptDeptName) {
        this.receiptDeptName = receiptDeptName;
    }

    public String getSignerName() {
        return signerName;
    }

    public void setSignerName(String signerName) {
        this.signerName = signerName;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufactureName() {
        return manufactureName;
    }

    public void setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
    }

    public String getManufactureAdd() {
        return manufactureAdd;
    }

    public void setManufactureAdd(String manufactureAdd) {
        this.manufactureAdd = manufactureAdd;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getMatchingTarget() {
        return matchingTarget;
    }

    public void setMatchingTarget(String matchingTarget) {
        this.matchingTarget = matchingTarget;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (announcementReceiptPaperId != null ? announcementReceiptPaperId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnnouncementReceiptPaper)) {
            return false;
        }
        AnnouncementReceiptPaper other = (AnnouncementReceiptPaper) object;
        if ((this.announcementReceiptPaperId == null && other.announcementReceiptPaperId != null) || (this.announcementReceiptPaperId != null && !this.announcementReceiptPaperId.equals(other.announcementReceiptPaperId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.AnnouncementReceiptPaper[ announcementReceiptPaperId=" + announcementReceiptPaperId + " ]";
    }
    
}
