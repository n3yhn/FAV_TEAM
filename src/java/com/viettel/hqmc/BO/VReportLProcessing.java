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
@Table(name = "V_REPORT_L_PROCESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VReportLProcessing.findAll", query = "SELECT v FROM VReportLProcessing v"),
    @NamedQuery(name = "VReportLProcessing.findByDeptId", query = "SELECT v FROM VReportLProcessing v WHERE v.deptId = :deptId"),
    @NamedQuery(name = "VReportLProcessing.findByLeaderId", query = "SELECT v FROM VReportLProcessing v WHERE v.leaderId = :leaderId"),
    @NamedQuery(name = "VReportLProcessing.findByLeaderName", query = "SELECT v FROM VReportLProcessing v WHERE v.leaderName = :leaderName"),
    @NamedQuery(name = "VReportLProcessing.findByTotalProcessing", query = "SELECT v FROM VReportLProcessing v WHERE v.totalProcessing = :totalProcessing")})
public class VReportLProcessing implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "DEPT_ID")
    private BigInteger deptId;
    @Column(name = "LEADER_ID")
    @Id
    private BigInteger leaderId;
    @Basic(optional = false)
    @Column(name = "LEADER_NAME")
    private String leaderName;
    @Column(name = "TOTAL_PROCESSING")
    private BigInteger totalProcessing;

    public VReportLProcessing() {
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

    public BigInteger getTotalProcessing() {
        return totalProcessing;
    }

    public void setTotalProcessing(BigInteger totalProcessing) {
        this.totalProcessing = totalProcessing;
    }
    
}
