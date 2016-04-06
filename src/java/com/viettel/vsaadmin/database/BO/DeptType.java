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
import javax.persistence.Table;

/**
 *
 * @author gpdn_havm2
 */
@Entity
@Table(name = "DEPT_TYPE")
@NamedQueries({@NamedQuery(name = "DeptType.findAll", query = "SELECT d FROM DeptType d"), @NamedQuery(name = "DeptType.findByDeptTypeId", query = "SELECT d FROM DeptType d WHERE d.deptTypeId = :deptTypeId"), @NamedQuery(name = "DeptType.findByTypeName", query = "SELECT d FROM DeptType d WHERE d.typeName = :typeName"), @NamedQuery(name = "DeptType.findByDescription", query = "SELECT d FROM DeptType d WHERE d.description = :description")})
public class DeptType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DEPT_TYPE_ID")
    private Long deptTypeId;
    @Basic(optional = false)
    @Column(name = "TYPE_NAME")
    private String typeName;
    @Column(name = "DESCRIPTION")
    private String description;

    public DeptType() {
    }

    public DeptType(Long deptTypeId) {
        this.deptTypeId = deptTypeId;
    }

    public DeptType(Long deptTypeId, String typeName) {
        this.deptTypeId = deptTypeId;
        this.typeName = typeName;
    }

    public Long getDeptTypeId() {
        return deptTypeId;
    }

    public void setDeptTypeId(Long deptTypeId) {
        this.deptTypeId = deptTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptTypeId != null ? deptTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeptType)) {
            return false;
        }
        DeptType other = (DeptType) object;
        if ((this.deptTypeId == null && other.deptTypeId != null) || (this.deptTypeId != null && !this.deptTypeId.equals(other.deptTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hvct.database.BO.DeptType[deptTypeId=" + deptTypeId + "]";
    }
}
