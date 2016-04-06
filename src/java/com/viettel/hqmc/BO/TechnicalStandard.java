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
@Table(name = "TECHNICAL_STANDARD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TechnicalStandard.findAll", query = "SELECT t FROM TechnicalStandard t"),
    @NamedQuery(name = "TechnicalStandard.findByTechnicalStandardId", query = "SELECT t FROM TechnicalStandard t WHERE t.technicalStandardId = :technicalStandardId"),
    @NamedQuery(name = "TechnicalStandard.findByStandardCode", query = "SELECT t FROM TechnicalStandard t WHERE t.standardCode = :standardCode"),
    @NamedQuery(name = "TechnicalStandard.findByEnglishName", query = "SELECT t FROM TechnicalStandard t WHERE t.englishName = :englishName"),
    @NamedQuery(name = "TechnicalStandard.findByVietnameseName", query = "SELECT t FROM TechnicalStandard t WHERE t.vietnameseName = :vietnameseName"),
    @NamedQuery(name = "TechnicalStandard.findBySummary", query = "SELECT t FROM TechnicalStandard t WHERE t.summary = :summary"),
    @NamedQuery(name = "TechnicalStandard.findByApplicationObject", query = "SELECT t FROM TechnicalStandard t WHERE t.applicationObject = :applicationObject"),
    @NamedQuery(name = "TechnicalStandard.findByPublishDate", query = "SELECT t FROM TechnicalStandard t WHERE t.publishDate = :publishDate"),
    @NamedQuery(name = "TechnicalStandard.findByEffectiveDate", query = "SELECT t FROM TechnicalStandard t WHERE t.effectiveDate = :effectiveDate"),
    @NamedQuery(name = "TechnicalStandard.findByExpireDate", query = "SELECT t FROM TechnicalStandard t WHERE t.expireDate = :expireDate"),
    @NamedQuery(name = "TechnicalStandard.findByScope", query = "SELECT t FROM TechnicalStandard t WHERE t.scope = :scope"),
    @NamedQuery(name = "TechnicalStandard.findByStandardType", query = "SELECT t FROM TechnicalStandard t WHERE t.standardType = :standardType"),
    @NamedQuery(name = "TechnicalStandard.findByIsActive", query = "SELECT t FROM TechnicalStandard t WHERE t.isActive = :isActive"),
    @NamedQuery(name = "TechnicalStandard.findByPublishAgencyName", query = "SELECT t FROM TechnicalStandard t WHERE t.publishAgencyName = :publishAgencyName"),
    @NamedQuery(name = "TechnicalStandard.findByStatus", query = "SELECT t FROM TechnicalStandard t WHERE t.status = :status")})
public class TechnicalStandard implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "TECHNICAL_STANDARD_SEQ", sequenceName = "TECHNICAL_STANDARD_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TECHNICAL_STANDARD_SEQ")
    @Basic(optional = false)
    @Column(name = "TECHNICAL_STANDARD_ID")
    private Long technicalStandardId;
    @Column(name = "STANDARD_CODE")
    private String standardCode;
    @Column(name = "ENGLISH_NAME")
    private String englishName;
    @Column(name = "VIETNAMESE_NAME")
    private String vietnameseName;
    @Column(name = "SUMMARY")
    private String summary;
    @Column(name = "APPLICATION_OBJECT")
    private String applicationObject;
    @Column(name = "PUBLISH_DATE")
    @Temporal(TemporalType.DATE)
    private Date publishDate;
    @Column(name = "EFFECTIVE_DATE")
    @Temporal(TemporalType.DATE)
    private Date effectiveDate;
    @Column(name = "EXPIRE_DATE")
    @Temporal(TemporalType.DATE)
    private Date expireDate;
    @Column(name = "SCOPE")
    private String scope;
    @Column(name = "STANDARD_TYPE")
    private Long standardType;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "PUBLISH_AGENCY_NAME")
    private String publishAgencyName;
    @Column(name = "STATUS")
    private Long status;

    public TechnicalStandard() {
    }

    public TechnicalStandard(Long technicalStandardId) {
        this.technicalStandardId = technicalStandardId;
    }

    public Long getTechnicalStandardId() {
        return technicalStandardId;
    }

    public void setTechnicalStandardId(Long technicalStandardId) {
        this.technicalStandardId = technicalStandardId;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getVietnameseName() {
        return vietnameseName;
    }

    public void setVietnameseName(String vietnameseName) {
        this.vietnameseName = vietnameseName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getApplicationObject() {
        return applicationObject;
    }

    public void setApplicationObject(String applicationObject) {
        this.applicationObject = applicationObject;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Long getStandardType() {
        return standardType;
    }

    public void setStandardType(Long standardType) {
        this.standardType = standardType;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public String getPublishAgencyName() {
        return publishAgencyName;
    }

    public void setPublishAgencyName(String publishAgencyName) {
        this.publishAgencyName = publishAgencyName;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (technicalStandardId != null ? technicalStandardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TechnicalStandard)) {
            return false;
        }
        TechnicalStandard other = (TechnicalStandard) object;
        if ((this.technicalStandardId == null && other.technicalStandardId != null) || (this.technicalStandardId != null && !this.technicalStandardId.equals(other.technicalStandardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.TechnicalStandard[ technicalStandardId=" + technicalStandardId + " ]";
    }
    
}
