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
@Table(name = "FEE_ADD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeeAdd.findAll", query = "SELECT f FROM FeeAdd f"),
    @NamedQuery(name = "FeeAdd.findByFeeAddId", query = "SELECT f FROM FeeAdd f WHERE f.feeAddId = :feeAddId"),
    @NamedQuery(name = "FeeAdd.findByFeeAddName", query = "SELECT f FROM FeeAdd f WHERE f.feeAddName = :feeAddName"),
    @NamedQuery(name = "FeeAdd.findByFeeAddPrice", query = "SELECT f FROM FeeAdd f WHERE f.feeAddPrice = :feeAddPrice"),
    @NamedQuery(name = "FeeAdd.findByIsActive", query = "SELECT f FROM FeeAdd f WHERE f.isActive = :isActive"),
    @NamedQuery(name = "FeeAdd.findByDescription", query = "SELECT f FROM FeeAdd f WHERE f.description = :description"),
    @NamedQuery(name = "FeeAdd.findByCreateUserId", query = "SELECT f FROM FeeAdd f WHERE f.createUserId = :createUserId"),
    @NamedQuery(name = "FeeAdd.findByUpdateUserId", query = "SELECT f FROM FeeAdd f WHERE f.updateUserId = :updateUserId"),
    @NamedQuery(name = "FeeAdd.findByCreateDate", query = "SELECT f FROM FeeAdd f WHERE f.createDate = :createDate"),
    @NamedQuery(name = "FeeAdd.findByUpdateDate", query = "SELECT f FROM FeeAdd f WHERE f.updateDate = :updateDate")})
public class FeeAdd implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "FEE_ADD_SEQ", sequenceName = "FEE_ADD_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FEE_ADD_SEQ")
    @Basic(optional = false)
    @Column(name = "FEE_ADD_ID")
    private Long feeAddId;
    @Column(name = "FEE_ADD_NAME")
    private String feeAddName;
    @Column(name = "FEE_ADD_PRICE")
    private Long feeAddPrice;
    @Column(name = "IS_ACTIVE")
    private Short isActive;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATE_USER_ID")
    private Long createUserId;
    @Column(name = "UPDATE_USER_ID")
    private Long updateUserId;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    public FeeAdd() {
    }

    public FeeAdd(Long feeAddId) {
        this.feeAddId = feeAddId;
    }

    public Long getFeeAddId() {
        return feeAddId;
    }

    public void setFeeAddId(Long feeAddId) {
        this.feeAddId = feeAddId;
    }

    public String getFeeAddName() {
        return feeAddName;
    }

    public void setFeeAddName(String feeAddName) {
        this.feeAddName = feeAddName;
    }

    public Long getFeeAddPrice() {
        return feeAddPrice;
    }

    public void setFeeAddPrice(Long feeAddPrice) {
        this.feeAddPrice = feeAddPrice;
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

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feeAddId != null ? feeAddId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeeAdd)) {
            return false;
        }
        FeeAdd other = (FeeAdd) object;
        if ((this.feeAddId == null && other.feeAddId != null) || (this.feeAddId != null && !this.feeAddId.equals(other.feeAddId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.FeeAdd[ feeAddId=" + feeAddId + " ]";
    }

}
