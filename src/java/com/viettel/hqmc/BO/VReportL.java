/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.hqmc.BO;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GPCP_BINHNT53
 */
@Entity
@Table(name = "V_REPORT_L")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VReportL.findAll", query = "SELECT v FROM VReportL v"),
    @NamedQuery(name = "VReportL.findByDeptId", query = "SELECT v FROM VReportL v WHERE v.deptId = :deptId"),
    @NamedQuery(name = "VReportL.findByLeaderId", query = "SELECT v FROM VReportL v WHERE v.leaderId = :leaderId"),
    @NamedQuery(name = "VReportL.findByLeaderName", query = "SELECT v FROM VReportL v WHERE v.leaderName = :leaderName"),
    @NamedQuery(name = "VReportL.findByTotalProcess", query = "SELECT v FROM VReportL v WHERE v.totalProcess = :totalProcess"),
    @NamedQuery(name = "VReportL.findByTotalProcessed", query = "SELECT v FROM VReportL v WHERE v.totalProcessed = :totalProcessed"),
    @NamedQuery(name = "VReportL.findByTotalProcessing", query = "SELECT v FROM VReportL v WHERE v.totalProcessing = :totalProcessing")})
public class VReportL implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "DEPT_ID")
    private BigInteger deptId;
    @Column(name = "LEADER_ID")
    @Id
    private BigInteger leaderId;
    @Basic(optional = false)
    @Column(name = "LEADER_NAME")
    private String leaderName;
    @Column(name = "TOTAL_PROCESS")
    private BigInteger totalProcess;
    @Column(name = "TOTAL_PROCESSED")
    private BigInteger totalProcessed;
    @Column(name = "TOTAL_PROCESSING")
    private BigInteger totalProcessing;

    public VReportL() {
    }

    public BigInteger getDeptId() {
        return deptId;
    }

    public void setDeptId(BigInteger deptId) {
        this.deptId = deptId;
    }

    public BigInteger getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(BigInteger leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
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
