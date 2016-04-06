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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gpdn_havm2
 */
@Entity
@Table(name = "OBJECTS")
@NamedQueries({@NamedQuery(name = "Objects.findAll", query = "SELECT o FROM Objects o"), @NamedQuery(name = "Objects.findByObjectId", query = "SELECT o FROM Objects o WHERE o.objectId = :objectId"), @NamedQuery(name = "Objects.findByStatus", query = "SELECT o FROM Objects o WHERE o.status = :status"), @NamedQuery(name = "Objects.findByOrd", query = "SELECT o FROM Objects o WHERE o.ord = :ord"), @NamedQuery(name = "Objects.findByObjectUrl", query = "SELECT o FROM Objects o WHERE o.objectUrl = :objectUrl"), @NamedQuery(name = "Objects.findByObjectName", query = "SELECT o FROM Objects o WHERE o.objectName = :objectName"), @NamedQuery(name = "Objects.findByDescription", query = "SELECT o FROM Objects o WHERE o.description = :description"), @NamedQuery(name = "Objects.findByObjectCode", query = "SELECT o FROM Objects o WHERE o.objectCode = :objectCode"), @NamedQuery(name = "Objects.findByObjectLevel", query = "SELECT o FROM Objects o WHERE o.objectLevel = :objectLevel")})
public class Objects implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "OBJECT_ID")
    private Long objectId;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "ORD")
    private Long ord;
    @Column(name = "OBJECT_URL")
    private String objectUrl;
    @Basic(optional = false)
    @Column(name = "OBJECT_NAME")
    private String objectName;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "OBJECT_CODE")
    private String objectCode;
    @Column(name = "OBJECT_LEVEL")
    private String objectLevel;
    @Column(name = "APP_ID")
    private Long appId;
    @JoinColumn(name = "APP_ID", referencedColumnName = "APP_ID", insertable=false, updatable=false)
    @ManyToOne(optional = false)
    private Applications app;
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Column(name = "OBJECT_TYPE_ID")
    private Long objectTypeId;

    public Objects() {
    }

    public Objects(Long objectId) {
        this.objectId = objectId;
    }

    public Objects(Long objectId, Long status, String objectName) {
        this.objectId = objectId;
        this.status = status;
        this.objectName = objectName;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getOrd() {
        return ord;
    }

    public void setOrd(Long ord) {
        this.ord = ord;
    }

    public String getObjectUrl() {
        return objectUrl;
    }

    public void setObjectUrl(String objectUrl) {
        this.objectUrl = objectUrl;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getObjectLevel() {
        return objectLevel;
    }

    public void setObjectLevel(String objectLevel) {
        this.objectLevel = objectLevel;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Applications getApp() {
        return app;
    }

    public void setApp(Applications app) {
        this.app = app;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(Long objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (objectId != null ? objectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objects)) {
            return false;
        }
        Objects other = (Objects) object;
        if ((this.objectId == null && other.objectId != null) || (this.objectId != null && !this.objectId.equals(other.objectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.vsaadmin.database.BO.Objects[objectId=" + objectId + "]";
    }

}
