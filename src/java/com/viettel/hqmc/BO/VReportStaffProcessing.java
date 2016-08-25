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
@Table(name = "V_REPORT_STAFF_PROCESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VReportStaffProcessing.findAll", query = "SELECT v FROM VReportStaffProcessing v"),
    @NamedQuery(name = "VReportStaffProcessing.findByDeptId", query = "SELECT v FROM VReportStaffProcessing v WHERE v.deptId = :deptId"),
    @NamedQuery(name = "VReportStaffProcessing.findByStaffId", query = "SELECT v FROM VReportStaffProcessing v WHERE v.staffId = :staffId"),
    @NamedQuery(name = "VReportStaffProcessing.findByStaffName", query = "SELECT v FROM VReportStaffProcessing v WHERE v.staffName = :staffName"),
    @NamedQuery(name = "VReportStaffProcessing.findByTotalProcessing", query = "SELECT v FROM VReportStaffProcessing v WHERE v.totalProcessing = :totalProcessing")})
public class VReportStaffProcessing implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "DEPT_ID")
    private BigInteger deptId;
    @Column(name = "STAFF_ID")
    @Id
    private Long staffId;
    @Column(name = "STAFF_NAME")
    private String staffName;
    @Column(name = "TOTAL_PROCESSING")
    private BigInteger totalProcessing;

    public VReportStaffProcessing() {
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

    public BigInteger getTotalProcessing() {
        return totalProcessing;
    }

    public void setTotalProcessing(BigInteger totalProcessing) {
        this.totalProcessing = totalProcessing;
    }
    
}
