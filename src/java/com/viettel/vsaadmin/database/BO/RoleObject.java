/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.vsaadmin.database.BO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "ROLE_OBJECT")
@NamedQueries({@NamedQuery(name = "RoleObject.findAll", query = "SELECT r FROM RoleObject r"), @NamedQuery(name = "RoleObject.findByObjectId", query = "SELECT r FROM RoleObject r WHERE r.roleObjectPK.objectId = :objectId"), @NamedQuery(name = "RoleObject.findByRoleId", query = "SELECT r FROM RoleObject r WHERE r.roleObjectPK.roleId = :roleId"), @NamedQuery(name = "RoleObject.findByDescription", query = "SELECT r FROM RoleObject r WHERE r.description = :description"), @NamedQuery(name = "RoleObject.findByIsActive", query = "SELECT r FROM RoleObject r WHERE r.isActive = :isActive")})
public class RoleObject implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RoleObjectPK roleObjectPK;
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "OBJECT_ID", insertable=false, updatable=false )
    private Long objectId;
    @JoinColumn(name = "OBJECT_ID", referencedColumnName = "OBJECT_ID", insertable=false, updatable=false)
    @ManyToOne(optional = false)
    private Objects object;
    @Column(name = "ROLE_ID",  insertable=false, updatable=false)
    private Long roleId;
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID", insertable=false, updatable=false)
    @ManyToOne(optional = false)
    private Roles role;

    public RoleObject() {
    }

    public RoleObject(RoleObjectPK roleObjectPK) {
        this.roleObjectPK = roleObjectPK;
    }

    public RoleObject(RoleObjectPK roleObjectPK, Long isActive) {
        this.roleObjectPK = roleObjectPK;
        this.isActive = isActive;
    }

    public RoleObject(long objectId, long roleId) {
        this.roleObjectPK = new RoleObjectPK(objectId, roleId);
    }

    public RoleObjectPK getRoleObjectPK() {
        return roleObjectPK;
    }

    public void setRoleObjectPK(RoleObjectPK roleObjectPK) {
        this.roleObjectPK = roleObjectPK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Objects getObject() {
        return object;
    }

    public void setObject(Objects object) {
        this.object = object;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleObjectPK != null ? roleObjectPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleObject)) {
            return false;
        }
        RoleObject other = (RoleObject) object;
        if ((this.roleObjectPK == null && other.roleObjectPK != null) || (this.roleObjectPK != null && !this.roleObjectPK.equals(other.roleObjectPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.vsaadmin.database.BO.RoleObject[roleObjectPK=" + roleObjectPK + "]";
    }

}
