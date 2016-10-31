/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "BUSINESS_ALERT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BusinessAlert.findAll", query = "SELECT b FROM BusinessAlert b"),
    @NamedQuery(name = "BusinessAlert.findByBusinessAlertId", query = "SELECT b FROM BusinessAlert b WHERE b.businessAlertId = :businessAlertId"),
    @NamedQuery(name = "BusinessAlert.findByBusinessId", query = "SELECT b FROM BusinessAlert b WHERE b.businessId = :businessId"),
    @NamedQuery(name = "BusinessAlert.findByIsActive", query = "SELECT b FROM BusinessAlert b WHERE b.isActive = :isActive"),
    @NamedQuery(name = "BusinessAlert.findByCreatedDate", query = "SELECT b FROM BusinessAlert b WHERE b.createdDate = :createdDate"),
    @NamedQuery(name = "BusinessAlert.findByCreatedById", query = "SELECT b FROM BusinessAlert b WHERE b.createdById = :createdById"),
    @NamedQuery(name = "BusinessAlert.findByCreatedByName", query = "SELECT b FROM BusinessAlert b WHERE b.createdByName = :createdByName"),
    @NamedQuery(name = "BusinessAlert.findBySeen", query = "SELECT b FROM BusinessAlert b WHERE b.seen = :seen")})
public class BusinessAlert implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "BUSINESS_ALERT_SEQ", sequenceName = "BUSINESS_ALERT_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUSINESS_ALERT_SEQ")
    @Basic(optional = false)
    @Column(name = "BUSINESS_ALERT_ID")
    private Long businessAlertId;
    @Column(name = "BUSINESS_ID")
    private Long businessId;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "CREATED_BY_ID")
    private Long createdById;
    @Column(name = "CREATED_BY_NAME")
    private String createdByName;
    @Column(name = "SEEN")
    private Long seen;
    //PARENT_ID
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Lob
    @Column(name = "CONTENT")
    private String content;

    public BusinessAlert() {
    }

    public BusinessAlert(Long businessAlertId) {
        this.businessAlertId = businessAlertId;
    }

    public Long getBusinessAlertId() {
        return businessAlertId;
    }

    public void setBusinessAlertId(Long businessAlertId) {
        this.businessAlertId = businessAlertId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = StringUtils.removeEventHandlerJS(createdByName);
    }

    public Long getSeen() {
        return seen;
    }

    public void setSeen(Long seen) {
        this.seen = seen;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = StringUtils.removeEventHandlerJS(content);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (businessAlertId != null ? businessAlertId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BusinessAlert)) {
            return false;
        }
        BusinessAlert other = (BusinessAlert) object;
        if ((this.businessAlertId == null && other.businessAlertId != null) || (this.businessAlertId != null && !this.businessAlertId.equals(other.businessAlertId))) {
            return false;
        }
        return true;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.BusinessAlert[ businessAlertId=" + businessAlertId + " ]";
    }
    
}
