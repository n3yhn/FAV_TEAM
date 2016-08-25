/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gpcp_dund1
 */
@Entity
@Table(name = "V_REPORT_STAFF_PROCESSED")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VReportStaffProcessed.findAll", query = "SELECT v FROM VReportStaffProcessed v"),
    @NamedQuery(name = "VReportStaffProcessed.findByDeptId", query = "SELECT v FROM VReportStaffProcessed v WHERE v.deptId = :deptId"),
    @NamedQuery(name = "VReportStaffProcessed.findByStaffId", query = "SELECT v FROM VReportStaffProcessed v WHERE v.staffId = :staffId"),
    @NamedQuery(name = "VReportStaffProcessed.findByStaffName", query = "SELECT v FROM VReportStaffProcessed v WHERE v.staffName = :staffName"),
    @NamedQuery(name = "VReportStaffProcessed.findByTotalProcessed", query = "SELECT v FROM VReportStaffProcessed v WHERE v.totalProcessed = :totalProcessed")})
public class VReportStaffProcessed implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "DEPT_ID")
    private BigInteger deptId;
    @Column(name = "STAFF_ID")
    @Id
    private Long staffId;
    @Column(name = "STAFF_NAME")
    private String staffName;
    @Column(name = "TOTAL_PROCESSED")
    private BigInteger totalProcessed;

    public VReportStaffProcessed() {
    }

    public BigInteger getDeptId() {
        return deptId;
    }

    public void setDeptId(BigInteger deptId) {
        this.deptId = deptId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = StringUtils.removeEventHandlerJS(staffName);
    }

    public BigInteger getTotalProcessed() {
        return totalProcessed;
    }

    public void setTotalProcessed(BigInteger totalProcessed) {
        this.totalProcessed = totalProcessed;
    }
    
}
