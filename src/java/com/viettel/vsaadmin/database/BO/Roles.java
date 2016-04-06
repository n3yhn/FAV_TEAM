/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.vsaadmin.database.BO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author gpdn_havm2
 */
@Entity
@Table(name = "ROLES")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"), @NamedQuery(name = "Roles.findByRoleId", query = "SELECT r FROM Roles r WHERE r.roleId = :roleId"), @NamedQuery(name = "Roles.findByStatus", query = "SELECT r FROM Roles r WHERE r.status = :status"), @NamedQuery(name = "Roles.findByRoleName", query = "SELECT r FROM Roles r WHERE r.roleName = :roleName"), @NamedQuery(name = "Roles.findByDescription", query = "SELECT r FROM Roles r WHERE r.description = :description"), @NamedQuery(name = "Roles.findByRoleCode", query = "SELECT r FROM Roles r WHERE r.roleCode = :roleCode")})
public class Roles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
    parameters = {
        @Parameter(name = "sequence", value = "ROLES_SEQ")
    })
    @Column(name = "ROLE_ID")
    private Long roleId;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "ROLE_NAME")
    private String roleName;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ROLE_CODE")
    private String roleCode;

    public Roles() {
    }

    public Roles(Long roleId) {
        this.roleId = roleId;
    }

    public Roles(Long roleId, Long status, String roleName, String roleCode) {
        this.roleId = roleId;
        this.status = status;
        this.roleName = roleName;
        this.roleCode = roleCode;
    }

    public Roles(Long roleId, String roleName, String roleCode, String description, Long status) {
        this.roleId = roleId;
        this.status = status;
        this.roleName = roleName;
        this.roleCode = roleCode;
        this.description= description;
        this.status = status;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.vsaadmin.database.BO.Roles[roleId=" + roleId + "]";
    }

}
