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
@Table(name = "FEE_FILE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeeFile.findAll", query = "SELECT f FROM FeeFile f"),
    @NamedQuery(name = "FeeFile.findByFeeFileId", query = "SELECT f FROM FeeFile f WHERE f.feeFileId = :feeFileId"),
    @NamedQuery(name = "FeeFile.findByFileId", query = "SELECT f FROM FeeFile f WHERE f.fileId = :fileId"),
    @NamedQuery(name = "FeeFile.findByFeeId", query = "SELECT f FROM FeeFile f WHERE f.feeId = :feeId"),
    @NamedQuery(name = "FeeFile.findByIsActive", query = "SELECT f FROM FeeFile f WHERE f.isActive = :isActive")})
public class FeeFile implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "FEE_FILE_SEQ", sequenceName = "FEE_FILE_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FEE_FILE_SEQ")
    @Basic(optional = false)
    @Column(name = "FEE_FILE_ID")
    private Long feeFileId;
    @Column(name = "FILE_ID")
    private Long fileId;
    @Column(name = "FEE_ID")
    private Long feeId;
    @Column(name = "IS_ACTIVE")
    private Long isActive;

    public FeeFile() {
    }

    public FeeFile(Long feeFileId) {
        this.feeFileId = feeFileId;
    }

    public Long getFeeFileId() {
        return feeFileId;
    }

    public void setFeeFileId(Long feeFileId) {
        this.feeFileId = feeFileId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feeFileId != null ? feeFileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeeFile)) {
            return false;
        }
        FeeFile other = (FeeFile) object;
        if ((this.feeFileId == null && other.feeFileId != null) || (this.feeFileId != null && !this.feeFileId.equals(other.feeFileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.FeeFile[ feeFileId=" + feeFileId + " ]";
    }
    
}
