/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.hqmc.BO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
 * @author Hai
 */
@Entity
@Table(name = "REPOSITORIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Repositories.findAll", query = "SELECT r FROM Repositories r"),
    @NamedQuery(name = "Repositories.findByRepId", query = "SELECT r FROM Repositories r WHERE r.repId = :repId"),
    @NamedQuery(name = "Repositories.findByRepName", query = "SELECT r FROM Repositories r WHERE r.repName = :repName"),
    @NamedQuery(name = "Repositories.findByRepCreator", query = "SELECT r FROM Repositories r WHERE r.repCreator = :repCreator"),
    @NamedQuery(name = "Repositories.findByRepAddress", query = "SELECT r FROM Repositories r WHERE r.repAddress = :repAddress"),
    @NamedQuery(name = "Repositories.findByIsActive", query = "SELECT r FROM Repositories r WHERE r.isActive = :isActive")})
public class Repositories implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "REPOSITORIES_SEQ", sequenceName = "REPOSITORIES_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPOSITORIES_SEQ")
    @Basic(optional = false)
    @Column(name = "REP_ID")
    private Long repId;
    @Basic(optional = false)
    @Column(name = "REP_NAME")
    private String repName;
    @Column(name = "REP_CREATOR")
    private Long repCreator;
    @Column(name = "REP_ADDRESS")
    private String repAddress;
    @Column(name = "IS_ACTIVE")
    private Long isActive;

    public Repositories() {
    }

    public Repositories(Long repId) {
        this.repId = repId;
    }

    public Repositories(Long repId, String repName) {
        this.repId = repId;
        this.repName = repName;
    }

    public Long getRepId() {
        return repId;
    }

    public void setRepId(Long repId) {
        this.repId = repId;
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public Long getRepCreator() {
        return repCreator;
    }

    public void setRepCreator(Long repCreator) {
        this.repCreator = repCreator;
    }

    public String getRepAddress() {
        return repAddress;
    }

    public void setRepAddress(String repAddress) {
        this.repAddress = repAddress;
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
        hash += (repId != null ? repId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Repositories)) {
            return false;
        }
        Repositories other = (Repositories) object;
        if ((this.repId == null && other.repId != null) || (this.repId != null && !this.repId.equals(other.repId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.Repositories[ repId=" + repId + " ]";
    }
    
}
