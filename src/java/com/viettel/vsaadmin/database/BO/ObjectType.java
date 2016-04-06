/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.vsaadmin.database.BO;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gpdn_havm2
 */
@Entity
@Table(name = "OBJECT_TYPE")
@NamedQueries({@NamedQuery(name = "ObjectType.findAll", query = "SELECT o FROM ObjectType o"), @NamedQuery(name = "ObjectType.findByObjectTypeId", query = "SELECT o FROM ObjectType o WHERE o.objectTypeId = :objectTypeId"), @NamedQuery(name = "ObjectType.findByObjectTypeName", query = "SELECT o FROM ObjectType o WHERE o.objectTypeName = :objectTypeName"), @NamedQuery(name = "ObjectType.findByStatus", query = "SELECT o FROM ObjectType o WHERE o.status = :status")})
public class ObjectType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "OBJECT_TYPE_ID")
    private Long objectTypeId;
    @Basic(optional = false)
    @Column(name = "OBJECT_TYPE_NAME")
    private String objectTypeName;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private Long status;

    public ObjectType() {
    }

    public ObjectType(Long objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public ObjectType(Long objectTypeId, String objectTypeName, Long status) {
        this.objectTypeId = objectTypeId;
        this.objectTypeName = objectTypeName;
        this.status = status;
    }

    public Long getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(Long objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public String getObjectTypeName() {
        return objectTypeName;
    }

    public void setObjectTypeName(String objectTypeName) {
        this.objectTypeName = objectTypeName;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (objectTypeId != null ? objectTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjectType)) {
            return false;
        }
        ObjectType other = (ObjectType) object;
        if ((this.objectTypeId == null && other.objectTypeId != null) || (this.objectTypeId != null && !this.objectTypeId.equals(other.objectTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.vsaadmin.database.BO.ObjectType[objectTypeId=" + objectTypeId + "]";
    }

}
