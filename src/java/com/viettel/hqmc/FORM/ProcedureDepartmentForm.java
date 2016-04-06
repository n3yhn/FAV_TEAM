/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.ProcedureDepartment;
import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class ProcedureDepartmentForm implements Serializable {

    private Long procedureDepartmentId;
    private Long procedureId;
    private Long deptId;
    private String deptName;

    public ProcedureDepartmentForm() {
    }

    public ProcedureDepartmentForm(ProcedureDepartment entity) {
        if (entity == null) {
            return;
        }
        this.procedureDepartmentId = entity.getProcedureDepartmentId();
        this.procedureId = entity.getProcedureId();
        this.deptId = entity.getDeptId();
        this.deptName = entity.getDeptName();
    }

    public ProcedureDepartment convertToEntity() {
        ProcedureDepartment entity = new ProcedureDepartment();
        entity.setProcedureId(procedureId);
        entity.setDeptId(deptId);
        entity.setDeptName(deptName);
        entity.setProcedureDepartmentId(procedureDepartmentId);
        return entity;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public Long getProcedureDepartmentId() {
        return procedureDepartmentId;
    }

    public void setProcedureDepartmentId(Long procedureDepartmentId) {
        this.procedureDepartmentId = procedureDepartmentId;
    }
    
}
