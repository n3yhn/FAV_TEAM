/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author Administrator
 */
@Entity
@Table(name = "FEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fee.findAll", query = "SELECT f FROM Fee f"),
    @NamedQuery(name = "Fee.findByFeeId", query = "SELECT f FROM Fee f WHERE f.feeId = :feeId"),
    @NamedQuery(name = "Fee.findByFeeName", query = "SELECT f FROM Fee f WHERE f.feeName = :feeName"),
    @NamedQuery(name = "Fee.findByPrice", query = "SELECT f FROM Fee f WHERE f.price = :price"),
    @NamedQuery(name = "Fee.findByIsActive", query = "SELECT f FROM Fee f WHERE f.isActive = :isActive"),
    @NamedQuery(name = "Fee.findByDescription", query = "SELECT f FROM Fee f WHERE f.description = :description"),
    @NamedQuery(name = "Fee.findByCreateDate", query = "SELECT f FROM Fee f WHERE f.createDate = :createDate"),
    @NamedQuery(name = "Fee.findByCreateUserId", query = "SELECT f FROM Fee f WHERE f.createUserId = :createUserId"),
    @NamedQuery(name = "Fee.findByUpdateDate", query = "SELECT f FROM Fee f WHERE f.updateDate = :updateDate"),
    @NamedQuery(name = "Fee.findByUpdateUserId", query = "SELECT f FROM Fee f WHERE f.updateUserId = :updateUserId")})
public class Fee implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "FEE_SEQ", sequenceName = "FEE_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FEE_SEQ")
    @Basic(optional = false)
    @Column(name = "FEE_ID")
    private Long feeId;
    @Column(name = "FEE_NAME")
    private String feeName;
    @Column(name = "PRICE")
    private Long price;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "CREATE_USER_ID")
    private Long createUserId;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @Column(name = "UPDATE_USER_ID")
    private Long updateUserId;
    @Column(name = "PROCEDURE_ID")
    private String procedureId;
    @Column(name = "PROCEDURE_NAME")
    private String procedureName;
    @Column(name = "FEE_TYPE")
    private Long feeType;
    @Column(name = "FEE_CODE")
    private String feeCode;

    public Fee() {
    }

    public Fee(Long feeId) {
        this.feeId = feeId;
    }

    public Long getFeeId() {
        return feeId;
    }

    public void setFeeId(Long feeId) {
        this.feeId = feeId;
    }

    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public Long getFeeType() {
        return feeType;
    }

    public void setFeeType(Long feeType) {
        this.feeType = feeType;
    }

    public String getFeeCode() {
        return feeCode;
    }

    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feeId != null ? feeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fee)) {
            return false;
        }
        Fee other = (Fee) object;
        if ((this.feeId == null && other.feeId != null) || (this.feeId != null && !this.feeId.equals(other.feeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.Fee[ feeId=" + feeId + " ]";
    }

}
