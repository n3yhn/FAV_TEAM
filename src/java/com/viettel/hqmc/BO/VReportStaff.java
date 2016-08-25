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
@Table(name = "V_REPORT_STAFF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VReportStaff.findAll", query = "SELECT v FROM VReportStaff v"),
    @NamedQuery(name = "VReportStaff.findByDeptId", query = "SELECT v FROM VReportStaff v WHERE v.deptId = :deptId"),
    @NamedQuery(name = "VReportStaff.findByStaffId", query = "SELECT v FROM VReportStaff v WHERE v.staffId = :staffId"),
    @NamedQuery(name = "VReportStaff.findByStaffName", query = "SELECT v FROM VReportStaff v WHERE v.staffName = :staffName"),
    @NamedQuery(name = "VReportStaff.findByTotalProcess", query = "SELECT v FROM VReportStaff v WHERE v.totalProcess = :totalProcess"),
    @NamedQuery(name = "VReportStaff.findByTotalProcessed", query = "SELECT v FROM VReportStaff v WHERE v.totalProcessed = :totalProcessed"),
    @NamedQuery(name = "VReportStaff.findByTotalProcessing", query = "SELECT v FROM VReportStaff v WHERE v.totalProcessing = :totalProcessing")})
public class VReportStaff implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "DEPT_ID")
    private BigInteger deptId;
    @Column(name = "STAFF_ID")
    @Id
    private Long staffId;
    @Column(name = "STAFF_NAME")
    private String staffName;
    @Column(name = "TOTAL_PROCESS")
    private BigInteger totalProcess;
    @Column(name = "TOTAL_PROCESSED")
    private BigInteger totalProcessed;
    @Column(name = "TOTAL_PROCESSING")
    private BigInteger totalProcessing;

    public VReportStaff() {
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

    public BigInteger getTotalProcess() {
        return totalProcess;
    }

    public void setTotalProcess(BigInteger totalProcess) {
        this.totalProcess = totalProcess;
    }

    public BigInteger getTotalProcessed() {
        return totalProcessed;
    }

    public void setTotalProcessed(BigInteger totalProcessed) {
        this.totalProcessed = totalProcessed;
    }

    public BigInteger getTotalProcessing() {
        return totalProcessing;
    }

    public void setTotalProcessing(BigInteger totalProcessing) {
        this.totalProcessing = totalProcessing;
    }
    
}
