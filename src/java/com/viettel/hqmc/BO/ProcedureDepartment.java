/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
import java.io.Serializable;
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
 * @author GPCP_BINHNT53
 */
@Entity
@Table(name = "PROCEDURE_DEPARTMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcedureDepartment.findAll", query = "SELECT p FROM ProcedureDepartment p"),
    @NamedQuery(name = "ProcedureDepartment.findByProcedureId", query = "SELECT p FROM ProcedureDepartment p WHERE p.procedureId = :procedureId"),
    @NamedQuery(name = "ProcedureDepartment.findByDeptId", query = "SELECT p FROM ProcedureDepartment p WHERE p.deptId = :deptId"),
    @NamedQuery(name = "ProcedureDepartment.findByDeptName", query = "SELECT p FROM ProcedureDepartment p WHERE p.deptName = :deptName"),
    @NamedQuery(name = "ProcedureDepartment.findByProcessDeptId", query = "SELECT p FROM ProcedureDepartment p WHERE p.processDeptId = :processDeptId"),
    @NamedQuery(name = "ProcedureDepartment.findByProcessDeptName", query = "SELECT p FROM ProcedureDepartment p WHERE p.processDeptName = :processDeptName"),
    @NamedQuery(name = "ProcedureDepartment.findByProcedureDepartmentId", query = "SELECT p FROM ProcedureDepartment p WHERE p.procedureDepartmentId = :procedureDepartmentId")})
public class ProcedureDepartment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "PROCEDURE_ID")
    private Long procedureId;
    @Column(name = "DEPT_ID")
    private Long deptId;
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "PROCESS_DEPT_ID")
    private Long processDeptId;
    @Column(name = "PROCESS_DEPT_NAME")
    private String processDeptName;
    @SequenceGenerator(name = "PROCEDURE_DEPARTMENT_SEQ", sequenceName = "PROCEDURE_DEPARTMENT_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROCEDURE_DEPARTMENT_SEQ")
    @Basic(optional = false)
    @Column(name = "PROCEDURE_DEPARTMENT_ID")
    private Long procedureDepartmentId;

    public ProcedureDepartment() {
    }

    public ProcedureDepartment(Long procedureDepartmentId) {
        this.procedureDepartmentId = procedureDepartmentId;
    }

    public ProcedureDepartment(Long processDeptId, String processDeptName) {
        this.processDeptId = processDeptId;
        this.processDeptName = processDeptName;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long procedureId) {
        this.procedureId = procedureId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = StringUtils.removeEventHandlerJS(deptName);
    }

    public Long getProcessDeptId() {
        return processDeptId;
    }

    public void setProcessDeptId(Long processDeptId) {
        this.processDeptId = processDeptId;
    }

    public String getProcessDeptName() {
        return processDeptName;
    }

    public void setProcessDeptName(String processDeptName) {
        this.processDeptName = StringUtils.removeEventHandlerJS(processDeptName);
    }

    public Long getProcedureDepartmentId() {
        return procedureDepartmentId;
    }

    public void setProcedureDepartmentId(Long procedureDepartmentId) {
        this.procedureDepartmentId = procedureDepartmentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (procedureDepartmentId != null ? procedureDepartmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcedureDepartment)) {
            return false;
        }
        ProcedureDepartment other = (ProcedureDepartment) object;
        if ((this.procedureDepartmentId == null && other.procedureDepartmentId != null) || (this.procedureDepartmentId != null && !this.procedureDepartmentId.equals(other.procedureDepartmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.ProcedureDepartment[ procedureDepartmentId=" + procedureDepartmentId + " ]";
    }

}
