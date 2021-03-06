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
@Table(name = "V_REPORT_LS_PROCESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VReportLsProcess.findAll", query = "SELECT v FROM VReportLsProcess v"),
    @NamedQuery(name = "VReportLsProcess.findByDeptId", query = "SELECT v FROM VReportLsProcess v WHERE v.deptId = :deptId"),
    @NamedQuery(name = "VReportLsProcess.findByStaffId", query = "SELECT v FROM VReportLsProcess v WHERE v.staffId = :staffId"),
    @NamedQuery(name = "VReportLsProcess.findByStaffName", query = "SELECT v FROM VReportLsProcess v WHERE v.staffName = :staffName"),
    @NamedQuery(name = "VReportLsProcess.findByTotalProcess", query = "SELECT v FROM VReportLsProcess v WHERE v.totalProcess = :totalProcess")})
public class VReportLsProcess implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "DEPT_ID")
    private BigInteger deptId;
    @Column(name = "STAFF_ID")
    @Id
    private BigInteger staffId;
    @Column(name = "STAFF_NAME")
    private String staffName;
    @Column(name = "TOTAL_PROCESS")
    private BigInteger totalProcess;

    public VReportLsProcess() {
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

    public BigInteger getTotalProcess() {
        return totalProcess;
    }

    public void setTotalProcess(BigInteger totalProcess) {
        this.totalProcess = totalProcess;
    }
    
}
