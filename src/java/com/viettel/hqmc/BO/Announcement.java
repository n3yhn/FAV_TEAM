/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.hqmc.DAOHE.AnnouncementDAOHE;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vtit_havm2
 */
@Entity
@Table(name = "ANNOUNCEMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Announcement.findAll", query = "SELECT a FROM Announcement a"),
    @NamedQuery(name = "Announcement.findByAnnouncementId", query = "SELECT a FROM Announcement a WHERE a.announcementId = :announcementId"),
    @NamedQuery(name = "Announcement.findByAnnouncementNo", query = "SELECT a FROM Announcement a WHERE a.announcementNo = :announcementNo"),
    @NamedQuery(name = "Announcement.findByBusinessName", query = "SELECT a FROM Announcement a WHERE a.businessName = :businessName"),
    @NamedQuery(name = "Announcement.findByBusinessAddress", query = "SELECT a FROM Announcement a WHERE a.businessAddress = :businessAddress"),
    @NamedQuery(name = "Announcement.findByBusinessTelephone", query = "SELECT a FROM Announcement a WHERE a.businessTelephone = :businessTelephone"),
    @NamedQuery(name = "Announcement.findByBusinessFax", query = "SELECT a FROM Announcement a WHERE a.businessFax = :businessFax"),
    @NamedQuery(name = "Announcement.findByBusinessEmail", query = "SELECT a FROM Announcement a WHERE a.businessEmail = :businessEmail"),
    @NamedQuery(name = "Announcement.findByProductName", query = "SELECT a FROM Announcement a WHERE a.productName = :productName"),
    @NamedQuery(name = "Announcement.findByManufactureName", query = "SELECT a FROM Announcement a WHERE a.manufactureName = :manufactureName"),
    @NamedQuery(name = "Announcement.findByManufactureAddress", query = "SELECT a FROM Announcement a WHERE a.manufactureAddress = :manufactureAddress"),
    @NamedQuery(name = "Announcement.findByManufactureTel", query = "SELECT a FROM Announcement a WHERE a.manufactureTel = :manufactureTel"),
    @NamedQuery(name = "Announcement.findByManufactureFax", query = "SELECT a FROM Announcement a WHERE a.manufactureFax = :manufactureFax"),
    @NamedQuery(name = "Announcement.findByManufactureEmail", query = "SELECT a FROM Announcement a WHERE a.manufactureEmail = :manufactureEmail"),
    @NamedQuery(name = "Announcement.findByNationName", query = "SELECT a FROM Announcement a WHERE a.nationName = :nationName"),
    @NamedQuery(name = "Announcement.findByMatchingTarget", query = "SELECT a FROM Announcement a WHERE a.matchingTarget = :matchingTarget"),
    @NamedQuery(name = "Announcement.findByAssessmentMethod", query = "SELECT a FROM Announcement a WHERE a.assessmentMethod = :assessmentMethod"),
    @NamedQuery(name = "Announcement.findByPublishDate", query = "SELECT a FROM Announcement a WHERE a.publishDate = :publishDate"),
    @NamedQuery(name = "Announcement.findBySigner", query = "SELECT a FROM Announcement a WHERE a.signer = :signer"),
    @NamedQuery(name = "Announcement.findByIsActive", query = "SELECT a FROM Announcement a WHERE a.isActive = :isActive")})
public class Announcement implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "ANNOUNCEMENT_SEQ", sequenceName = "ANNOUNCEMENT_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANNOUNCEMENT_SEQ")
    @Basic(optional = false)
    @Column(name = "ANNOUNCEMENT_ID")
    private Long announcementId;
    @Column(name = "ANNOUNCEMENT_NO")
    private String announcementNo;
    @Column(name = "BUSINESS_NAME")
    private String businessName;
    @Column(name = "BUSINESS_LICENCE")
    private String businessLicence;
    @Column(name = "BUSINESS_ADDRESS")
    private String businessAddress;
    @Column(name = "BUSINESS_TELEPHONE")
    private String businessTelephone;
    @Column(name = "BUSINESS_FAX")
    private String businessFax;
    @Column(name = "BUSINESS_EMAIL")
    private String businessEmail;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "MANUFACTURE_NAME")
    private String manufactureName;
    @Column(name = "MANUFACTURE_ADDRESS")
    private String manufactureAddress;
    @Column(name = "MANUFACTURE_TEL")
    private String manufactureTel;
    @Column(name = "MANUFACTURE_FAX")
    private String manufactureFax;
    @Column(name = "MANUFACTURE_EMAIL")
    private String manufactureEmail;
    @Column(name = "NATION_NAME")
    private String nationName;
    @Column(name = "MATCHING_TARGET")
    private String matchingTarget;
    @Column(name = "ASSESSMENT_METHOD")
    private String assessmentMethod;
    @Column(name = "PUBLISH_DATE")
    @Temporal(TemporalType.DATE)
    private Date publishDate;
    @Column(name = "SIGNER")
    private String signer;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "IS_TEMP")
    private Long isTemp;
    @Column(name = "ORIGINAL_ID")
    private Long originalId;
    @Column(name = "NATION_COMPANY_ADDRESS")
    private String nationCompanyAddress;
    @Column(name = "NATION_COMPANY_NAME")
    private String nationCompanyName;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "LAST_IS_TEMP")
    private Long lastIsTemp;

    public Announcement() {
    }

    public Announcement(Long announcementId) {
        this.announcementId = announcementId;
    }

    public Announcement cloneEntity(Announcement original) {
        Announcement entity = new Announcement();

//        if (original.getAnnouncementId() != null) {
//            entity.setAnnouncementId(original.getAnnouncementId());
//        }
        if (original.getAnnouncementNo() != null) {
            entity.setAnnouncementNo(original.getAnnouncementNo().trim());
        }
        if (original.getBusinessName() != null) {
            entity.setBusinessName(original.getBusinessName().trim());
        }
        if (original.getBusinessLicence() != null) {
            entity.setBusinessLicence(original.getBusinessLicence().trim());
        }
        if (original.getBusinessAddress() != null) {
            entity.setBusinessAddress(original.getBusinessAddress().trim());
        }
        if (original.getBusinessTelephone() != null) {
            entity.setBusinessTelephone(original.getBusinessTelephone().trim());
        }
        if (original.getBusinessFax() != null) {
            entity.setBusinessFax(original.getBusinessFax().trim());
        }
        if (original.getBusinessEmail() != null) {
            entity.setBusinessEmail(original.getBusinessEmail().trim());
        }
        entity.setProductName(original.getProductName());
        entity.setManufactureName(original.getManufactureName());
        entity.setManufactureAddress(original.getManufactureAddress());
        entity.setManufactureTel(original.getManufactureTel());
        entity.setManufactureFax(original.getManufactureFax());
        entity.setManufactureEmail(original.getManufactureEmail());
        entity.setNationName(original.getNationName());
        entity.setMatchingTarget(original.getMatchingTarget());
        if (original.getAssessmentMethod() != null) {
            entity.setAssessmentMethod(original.getAssessmentMethod());
        }
        entity.setPublishDate(original.getPublishDate());
        entity.setSigner(original.getSigner());
        if (original.getIsActive() == null) {
            entity.setIsActive(0L);
        } else {
            entity.setIsActive(1L);
        }
        entity.setIsTemp(1L);
        entity.setOriginalId(original.getAnnouncementId());

        entity.setLastIsTemp(1L);
        AnnouncementDAOHE anndaohe = new AnnouncementDAOHE();
        entity.setVersion(anndaohe.getCountVersion(original.getAnnouncementId()));
        return entity;
    }

    public Announcement cloneEntity() {
        return cloneEntity(this);
    }

    public String getNationCompanyAddress() {
        return nationCompanyAddress;
    }

    public void setNationCompanyAddress(String nationCompanyAddress) {
        this.nationCompanyAddress = nationCompanyAddress;
    }

    public String getNationCompanyName() {
        return nationCompanyName;
    }

    public void setNationCompanyName(String nationCompanyName) {
        this.nationCompanyName = nationCompanyName;
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

    public Long getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Long announcementId) {
        this.announcementId = announcementId;
    }

    public String getAnnouncementNo() {
        return announcementNo;
    }

    public void setAnnouncementNo(String announcementNo) {
        this.announcementNo = announcementNo;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessTelephone() {
        return businessTelephone;
    }

    public void setBusinessTelephone(String businessTelephone) {
        this.businessTelephone = businessTelephone;
    }

    public String getBusinessFax() {
        return businessFax;
    }

    public void setBusinessFax(String businessFax) {
        this.businessFax = businessFax;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
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

    public String getManufactureAddress() {
        return manufactureAddress;
    }

    public void setManufactureAddress(String manufactureAddress) {
        this.manufactureAddress = manufactureAddress;
    }

    public String getManufactureTel() {
        return manufactureTel;
    }

    public void setManufactureTel(String manufactureTel) {
        this.manufactureTel = manufactureTel;
    }

    public String getManufactureFax() {
        return manufactureFax;
    }

    public void setManufactureFax(String manufactureFax) {
        this.manufactureFax = manufactureFax;
    }

    public String getManufactureEmail() {
        return manufactureEmail;
    }

    public void setManufactureEmail(String manufactureEmail) {
        this.manufactureEmail = manufactureEmail;
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

    public String getAssessmentMethod() {
        return assessmentMethod;
    }

    public void setAssessmentMethod(String assessmentMethod) {
        this.assessmentMethod = assessmentMethod;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
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
        hash += (announcementId != null ? announcementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Announcement)) {
            return false;
        }
        Announcement other = (Announcement) object;
        if ((this.announcementId == null && other.announcementId != null) || (this.announcementId != null && !this.announcementId.equals(other.announcementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.Announcement[ announcementId=" + announcementId + " ]";
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
}
