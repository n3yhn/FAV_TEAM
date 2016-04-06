/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.vsaadmin.database.BO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author gpdn_havm2
 */
@Embeddable
public class RoleUserPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ROLE_ID")
    private long roleId;
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private long userId;

    public RoleUserPK(long roleId, long userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) roleId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleUserPK)) {
            return false;
        }
        RoleUserPK other = (RoleUserPK) object;
        if (this.roleId != other.roleId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.vsaadmin.database.BO.RoleUserPK[roleId=" + roleId + ", userId=" + userId + "]";
    }

}
