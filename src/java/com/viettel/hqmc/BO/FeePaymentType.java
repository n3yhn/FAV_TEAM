/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "FEE_PAYMENT_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeePaymentType.findAll", query = "SELECT f FROM FeePaymentType f"),
    @NamedQuery(name = "FeePaymentType.findByFeePaymentTypeId", query = "SELECT f FROM FeePaymentType f WHERE f.feePaymentTypeId = :feePaymentTypeId"),
    @NamedQuery(name = "FeePaymentType.findByFeePaymentTypeName", query = "SELECT f FROM FeePaymentType f WHERE f.feePaymentTypeName = :feePaymentTypeName"),
    @NamedQuery(name = "FeePaymentType.findByIsActive", query = "SELECT f FROM FeePaymentType f WHERE f.isActive = :isActive"),
    @NamedQuery(name = "FeePaymentType.findByDescription", query = "SELECT f FROM FeePaymentType f WHERE f.description = :description")})
public class FeePaymentType implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "FEE_PAYMENT_TYPE_SEQ", sequenceName = "FEE_PAYMENT_TYPE_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FEE_PAYMENT_TYPE_SEQ")
    @Basic(optional = false)
    @Column(name = "FEE_PAYMENT_TYPE_ID")
    private Short feePaymentTypeId;
    @Column(name = "FEE_PAYMENT_TYPE_NAME")
    private String feePaymentTypeName;
    @Column(name = "IS_ACTIVE")
    private Short isActive;
    @Column(name = "DESCRIPTION")
    private String description;

    public FeePaymentType() {
    }

    public FeePaymentType(Short feePaymentTypeId) {
        this.feePaymentTypeId = feePaymentTypeId;
    }

    public Short getFeePaymentTypeId() {
        return feePaymentTypeId;
    }

    public void setFeePaymentTypeId(Short feePaymentTypeId) {
        this.feePaymentTypeId = feePaymentTypeId;
    }

    public String getFeePaymentTypeName() {
        return feePaymentTypeName;
    }

    public void setFeePaymentTypeName(String feePaymentTypeName) {
        this.feePaymentTypeName = feePaymentTypeName;
    }

    public Short getIsActive() {
        return isActive;
    }

    public void setIsActive(Short isActive) {
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feePaymentTypeId != null ? feePaymentTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeePaymentType)) {
            return false;
        }
        FeePaymentType other = (FeePaymentType) object;
        if ((this.feePaymentTypeId == null && other.feePaymentTypeId != null) || (this.feePaymentTypeId != null && !this.feePaymentTypeId.equals(other.feePaymentTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.FeePaymentType[ feePaymentTypeId=" + feePaymentTypeId + " ]";
    }

}
