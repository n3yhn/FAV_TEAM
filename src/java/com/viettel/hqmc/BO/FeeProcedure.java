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
@Table(name = "FEE_PROCEDURE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeeProcedure.findAll", query = "SELECT f FROM FeeProcedure f"),
    @NamedQuery(name = "FeeProcedure.findByProcedureId", query = "SELECT f FROM FeeProcedure f WHERE f.procedureId = :procedureId"),
    @NamedQuery(name = "FeeProcedure.findByFeeId", query = "SELECT f FROM FeeProcedure f WHERE f.feeId = :feeId"),
    @NamedQuery(name = "FeeProcedure.findByIsActive", query = "SELECT f FROM FeeProcedure f WHERE f.isActive = :isActive"),
    @NamedQuery(name = "FeeProcedure.findByFeeProcedureId", query = "SELECT f FROM FeeProcedure f WHERE f.feeProcedureId = :feeProcedureId")})
public class FeeProcedure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "PROCEDURE_ID")
    private Long procedureId;
    @Column(name = "FEE_ID")
    private Long feeId;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @SequenceGenerator(name = "FEE_PROCEDURE_SEQ", sequenceName = "FEE_PROCEDURE_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FEE_PROCEDURE_SEQ")
    @Basic(optional = false)
    @Column(name = "FEE_PROCEDURE_ID")
    private Long feeProcedureId;

    public FeeProcedure() {
    }

    public FeeProcedure(Long feeProcedureId) {
        this.feeProcedureId = feeProcedureId;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long procedureId) {
        this.procedureId = procedureId;
    }

    public Long getFeeId() {
        return feeId;
    }

    public void setFeeId(Long feeId) {
        this.feeId = feeId;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getFeeProcedureId() {
        return feeProcedureId;
    }

    public void setFeeProcedureId(Long feeProcedureId) {
        this.feeProcedureId = feeProcedureId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feeProcedureId != null ? feeProcedureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeeProcedure)) {
            return false;
        }
        FeeProcedure other = (FeeProcedure) object;
        if ((this.feeProcedureId == null && other.feeProcedureId != null) || (this.feeProcedureId != null && !this.feeProcedureId.equals(other.feeProcedureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.FeeProcedure[ feeProcedureId=" + feeProcedureId + " ]";
    }

}
