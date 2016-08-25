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
 * @author binhninh
 */
@Entity
@Table(name = "V_REPORT_LS_PROCESSED")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VReportLsProcessed.findAll", query = "SELECT v FROM VReportLsProcessed v"),
    @NamedQuery(name = "VReportLsProcessed.findByDeptId", query = "SELECT v FROM VReportLsProcessed v WHERE v.deptId = :deptId"),
    @NamedQuery(name = "VReportLsProcessed.findByStaffId", query = "SELECT v FROM VReportLsProcessed v WHERE v.staffId = :staffId"),
    @NamedQuery(name = "VReportLsProcessed.findByStaffName", query = "SELECT v FROM VReportLsProcessed v WHERE v.staffName = :staffName"),
    @NamedQuery(name = "VReportLsProcessed.findByTotalProcessed", query = "SELECT v FROM VReportLsProcessed v WHERE v.totalProcessed = :totalProcessed")})
public class VReportLsProcessed implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "DEPT_ID")
    private BigInteger deptId;
    @Column(name = "STAFF_ID")
    @Id
    private BigInteger staffId;
    @Column(name = "STAFF_NAME")
    private String staffName;
    @Column(name = "TOTAL_PROCESSED")
    private BigInteger totalProcessed;

    public VReportLsProcessed() {
    }

    public BigInteger getDeptId() {
        return deptId;
    }

    public void setDeptId(BigInteger deptId) {
        this.deptId = deptId;
    }

    public BigInteger getStaffId() {
        return staffId;
    }

    public void setStaffId(BigInteger staffId) {
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
