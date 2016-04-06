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
@Table(name = "ROLE_USER")
@NamedQueries({@NamedQuery(name = "RoleUser.findAll", query = "SELECT r FROM RoleUser r"), @NamedQuery(name = "RoleUser.findByRoleId", query = "SELECT r FROM RoleUser r WHERE r.roleUserPK.roleId = :roleId"), @NamedQuery(name = "RoleUser.findByUserId", query = "SELECT r FROM RoleUser r WHERE r.roleUserPK.userId = :userId"), @NamedQuery(name = "RoleUser.findByIsAdmin", query = "SELECT r FROM RoleUser r WHERE r.isAdmin = :isAdmin"), @NamedQuery(name = "RoleUser.findByIsActive", query = "SELECT r FROM RoleUser r WHERE r.isActive = :isActive")})
public class RoleUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RoleUserPK roleUserPK;
    @Basic(optional = false)
    @Column(name = "IS_ADMIN")
    private Long isAdmin;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "ROLE_ID", insertable=false, updatable=false)
    private Long roleId;
    @Column(name = "USER_ID",insertable=false, updatable=false)
    private Long userId;

    public RoleUser() {
    }

    public RoleUser(RoleUserPK roleUserPK) {
        this.roleUserPK = roleUserPK;
    }

    public RoleUser(RoleUserPK roleUserPK, Long isAdmin) {
        this.roleUserPK = roleUserPK;
        this.isAdmin = isAdmin;
    }

    public RoleUser(long roleId, long userId) {
        this.roleUserPK = new RoleUserPK(roleId, userId);
    }

    public RoleUserPK getRoleUserPK() {
        return roleUserPK;
    }

    public void setRoleUserPK(RoleUserPK roleUserPK) {
        this.roleUserPK = roleUserPK;
    }

    public Long getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Long isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleUserPK != null ? roleUserPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleUser)) {
            return false;
        }
        RoleUser other = (RoleUser) object;
        if ((this.roleUserPK == null && other.roleUserPK != null) || (this.roleUserPK != null && !this.roleUserPK.equals(other.roleUserPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.vsaadmin.database.BO.RoleUser[roleUserPK=" + roleUserPK + "]";
    }

}
