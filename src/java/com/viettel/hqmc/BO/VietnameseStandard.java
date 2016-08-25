/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "VIETNAMESE_STANDARD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VietnameseStandard.findAll", query = "SELECT v FROM VietnameseStandard v"),
    @NamedQuery(name = "VietnameseStandard.findByVietnameseStandardId", query = "SELECT v FROM VietnameseStandard v WHERE v.vietnameseStandardId = :vietnameseStandardId"),
    @NamedQuery(name = "VietnameseStandard.findByStandardCode", query = "SELECT v FROM VietnameseStandard v WHERE v.standardCode = :standardCode"),
    @NamedQuery(name = "VietnameseStandard.findByEnglishName", query = "SELECT v FROM VietnameseStandard v WHERE v.englishName = :englishName"),
    @NamedQuery(name = "VietnameseStandard.findByVietnameseName", query = "SELECT v FROM VietnameseStandard v WHERE v.vietnameseName = :vietnameseName"),
    @NamedQuery(name = "VietnameseStandard.findBySummary", query = "SELECT v FROM VietnameseStandard v WHERE v.summary = :summary"),
    @NamedQuery(name = "VietnameseStandard.findByApplicationObject", query = "SELECT v FROM VietnameseStandard v WHERE v.applicationObject = :applicationObject"),
    @NamedQuery(name = "VietnameseStandard.findByPublishDate", query = "SELECT v FROM VietnameseStandard v WHERE v.publishDate = :publishDate"),
    @NamedQuery(name = "VietnameseStandard.findByEffectiveDate", query = "SELECT v FROM VietnameseStandard v WHERE v.effectiveDate = :effectiveDate"),
    @NamedQuery(name = "VietnameseStandard.findByExpireDate", query = "SELECT v FROM VietnameseStandard v WHERE v.expireDate = :expireDate"),
    @NamedQuery(name = "VietnameseStandard.findByScope", query = "SELECT v FROM VietnameseStandard v WHERE v.scope = :scope"),
    @NamedQuery(name = "VietnameseStandard.findByStandardType", query = "SELECT v FROM VietnameseStandard v WHERE v.standardType = :standardType"),
    @NamedQuery(name = "VietnameseStandard.findByIsActive", query = "SELECT v FROM VietnameseStandard v WHERE v.isActive = :isActive"),
    @NamedQuery(name = "VietnameseStandard.findByPublishAgencyName", query = "SELECT v FROM VietnameseStandard v WHERE v.publishAgencyName = :publishAgencyName"),
    @NamedQuery(name = "VietnameseStandard.findByPublishAgencyId", query = "SELECT v FROM VietnameseStandard v WHERE v.publishAgencyId = :publishAgencyId")})
public class VietnameseStandard implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "VIETNAMESE_STANDARD_SEQ", sequenceName = "VIETNAMESE_STANDARD_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIETNAMESE_STANDARD_SEQ")
    @Basic(optional = false)
    @Column(name = "VIETNAMESE_STANDARD_ID")
    private Long vietnameseStandardId;
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
    @Basic(optional = false)
    @Column(name = "PUBLISH_AGENCY_ID")
    private String publishAgencyId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "vietnameseStandard")
    private StandardProduct standardProduct;

    public VietnameseStandard() {
    }

    public VietnameseStandard(Long vietnameseStandardId) {
        this.vietnameseStandardId = vietnameseStandardId;
    }

    public VietnameseStandard(Long vietnameseStandardId, String publishAgencyId) {
        this.vietnameseStandardId = vietnameseStandardId;
        this.publishAgencyId = publishAgencyId;
    }

    public Long getVietnameseStandardId() {
        return vietnameseStandardId;
    }

    public void setVietnameseStandardId(Long vietnameseStandardId) {
        this.vietnameseStandardId = vietnameseStandardId;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = StringUtils.removeEventHandlerJS(standardCode);
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = StringUtils.removeEventHandlerJS(englishName);
    }

    public String getVietnameseName() {
        return vietnameseName;
    }

    public void setVietnameseName(String vietnameseName) {
        this.vietnameseName = StringUtils.removeEventHandlerJS(vietnameseName);
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = StringUtils.removeEventHandlerJS(summary);
    }

    public String getApplicationObject() {
        return applicationObject;
    }

    public void setApplicationObject(String applicationObject) {
        this.applicationObject = StringUtils.removeEventHandlerJS(applicationObject);
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
        this.scope = StringUtils.removeEventHandlerJS(scope);
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
        this.publishAgencyName = StringUtils.removeEventHandlerJS(publishAgencyName);
    }

    public String getPublishAgencyId() {
        return publishAgencyId;
    }

    public void setPublishAgencyId(String publishAgencyId) {
        this.publishAgencyId = StringUtils.removeEventHandlerJS(publishAgencyId);
    }

    public StandardProduct getStandardProduct() {
        return standardProduct;
    }

    public void setStandardProduct(StandardProduct standardProduct) {
        this.standardProduct = standardProduct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vietnameseStandardId != null ? vietnameseStandardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VietnameseStandard)) {
            return false;
        }
        VietnameseStandard other = (VietnameseStandard) object;
        if ((this.vietnameseStandardId == null && other.vietnameseStandardId != null) || (this.vietnameseStandardId != null && !this.vietnameseStandardId.equals(other.vietnameseStandardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.VietnameseStandard[ vietnameseStandardId=" + vietnameseStandardId + " ]";
    }
    
}
