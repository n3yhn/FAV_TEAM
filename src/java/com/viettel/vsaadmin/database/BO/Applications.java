/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.vsaadmin.database.BO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gpdn_havm2
 */
@Entity
@Table(name = "APPLICATIONS")
@NamedQueries({
    @NamedQuery(name = "Applications.findAll", query = "SELECT a FROM Applications a"),
    @NamedQuery(name = "Applications.findByAppId", query = "SELECT a FROM Applications a WHERE a.appId = :appId"),
    @NamedQuery(name = "Applications.findByStatus", query = "SELECT a FROM Applications a WHERE a.status = :status"),
    @NamedQuery(name = "Applications.findByAppCode", query = "SELECT a FROM Applications a WHERE a.appCode = :appCode"),
    @NamedQuery(name = "Applications.findByAppName", query = "SELECT a FROM Applications a WHERE a.appName = :appName"),
    @NamedQuery(name = "Applications.findByDescription", query = "SELECT a FROM Applications a WHERE a.description = :description"),
    @NamedQuery(name = "Applications.findByLockDescription", query = "SELECT a FROM Applications a WHERE a.lockDescription = :lockDescription")})
public class Applications implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "APP_ID")
    private Long appId;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private Long status;
    @Basic(optional = false)
    @Column(name = "APP_CODE")
    private String appCode;
    @Basic(optional = false)
    @Column(name = "APP_NAME")
    private String appName;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "LOCK_DESCRIPTION")
    private String lockDescription;

    public Applications() {
    }

    public Applications(Long appId) {
        this.appId = appId;
    }

    public Applications(Long appId, Long status, String appCode, String appName) {
        this.appId = appId;
        this.status = status;
        this.appCode = appCode;
        this.appName = appName;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLockDescription() {
        return lockDescription;
    }

    public void setLockDescription(String lockDescription) {
        this.lockDescription = lockDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appId != null ? appId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Applications)) {
            return false;
        }
        Applications other = (Applications) object;
        if ((this.appId == null && other.appId != null) || (this.appId != null && !this.appId.equals(other.appId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.vsaadmin.database.BO.Applications[appId=" + appId + "]";
    }
}
