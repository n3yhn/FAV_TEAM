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
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;


/**
 *
 * @author HaVM2
 */
@Entity
@Table(name = "ROLE_USER_DEPT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleUserDept.findAll", query = "SELECT r FROM RoleUserDept r"),
    @NamedQuery(name = "RoleUserDept.findByRoleUserDeptId", query = "SELECT r FROM RoleUserDept r WHERE r.roleUserDeptId = :roleUserDeptId"),
    @NamedQuery(name = "RoleUserDept.findByDeptId", query = "SELECT r FROM RoleUserDept r WHERE r.deptId = :deptId"),
    @NamedQuery(name = "RoleUserDept.findByIsActive", query = "SELECT r FROM RoleUserDept r WHERE r.isActive = :isActive"),
    @NamedQuery(name = "RoleUserDept.findByRoleId", query = "SELECT r FROM RoleUserDept r WHERE r.roleId = :roleId"),
    @NamedQuery(name = "RoleUserDept.findByUserId", query = "SELECT r FROM RoleUserDept r WHERE r.userId = :userId"),
    @NamedQuery(name = "RoleUserDept.findByDeptName", query = "SELECT r FROM RoleUserDept r WHERE r.deptName = :deptName"),
    @NamedQuery(name = "RoleUserDept.findByUserName", query = "SELECT r FROM RoleUserDept r WHERE r.userName = :userName"),
    @NamedQuery(name = "RoleUserDept.findByRoleName", query = "SELECT r FROM RoleUserDept r WHERE r.roleName = :roleName")})
public class RoleUserDept implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "ROLE_USER_DEPT_SEQ", sequenceName = "ROLE_USER_DEPT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_USER_DEPT_SEQ")
    @Id
    @Basic(optional = false)
    @Column(name = "ROLE_USER_DEPT_ID")
    private Long roleUserDeptId;
    @Column(name = "DEPT_ID")
    private Long deptId;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "ROLE_ID")
    private Long roleId;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "ROLE_NAME")
    private String roleName;

    public RoleUserDept() {
    }

    public RoleUserDept(Long roleUserDeptId) {
        this.roleUserDeptId = roleUserDeptId;
    }

    public Long getRoleUserDeptId() {
        return roleUserDeptId;
    }

    public void setRoleUserDeptId(Long roleUserDeptId) {
        this.roleUserDeptId = roleUserDeptId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleUserDeptId != null ? roleUserDeptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleUserDept)) {
            return false;
        }
        RoleUserDept other = (RoleUserDept) object;
        if ((this.roleUserDeptId == null && other.roleUserDeptId != null) || (this.roleUserDeptId != null && !this.roleUserDeptId.equals(other.roleUserDeptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.vsaadmin.database.BO.RoleUserDept[ roleUserDeptId=" + roleUserDeptId + " ]";
    }
    
}
