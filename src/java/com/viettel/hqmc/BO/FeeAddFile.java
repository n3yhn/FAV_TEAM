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
@Table(name = "FEE_ADD_FILE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeeAddFile.findAll", query = "SELECT f FROM FeeAddFile f"),
    @NamedQuery(name = "FeeAddFile.findByFileId", query = "SELECT f FROM FeeAddFile f WHERE f.fileId = :fileId"),
    @NamedQuery(name = "FeeAddFile.findByFeeAddId", query = "SELECT f FROM FeeAddFile f WHERE f.feeAddId = :feeAddId"),
    @NamedQuery(name = "FeeAddFile.findByIsActive", query = "SELECT f FROM FeeAddFile f WHERE f.isActive = :isActive"),
    @NamedQuery(name = "FeeAddFile.findByFeeAddFileId", query = "SELECT f FROM FeeAddFile f WHERE f.feeAddFileId = :feeAddFileId")})
public class FeeAddFile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "FILE_ID")
    private Long fileId;
    @Column(name = "FEE_ADD_ID")
    private Long feeAddId;
    @Column(name = "IS_ACTIVE")
    private Short isActive;
    @SequenceGenerator(name = "FEE_ADD_FILE_SEQ", sequenceName = "FEE_ADD_FILE_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FEE_ADD_FILE_SEQ")
    @Basic(optional = false)
    @Column(name = "FEE_ADD_FILE_ID")
    private Long feeAddFileId;

    public FeeAddFile() {
    }

    public FeeAddFile(Long feeAddFileId) {
        this.feeAddFileId = feeAddFileId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getFeeAddId() {
        return feeAddId;
    }

    public void setFeeAddId(Long feeAddId) {
        this.feeAddId = feeAddId;
    }

    public Short getIsActive() {
        return isActive;
    }

    public void setIsActive(Short isActive) {
        this.isActive = isActive;
    }

    public Long getFeeAddFileId() {
        return feeAddFileId;
    }

    public void setFeeAddFileId(Long feeAddFileId) {
        this.feeAddFileId = feeAddFileId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feeAddFileId != null ? feeAddFileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeeAddFile)) {
            return false;
        }
        FeeAddFile other = (FeeAddFile) object;
        if ((this.feeAddFileId == null && other.feeAddFileId != null) || (this.feeAddFileId != null && !this.feeAddFileId.equals(other.feeAddFileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.FeeAddFile[ feeAddFileId=" + feeAddFileId + " ]";
    }
    
}
