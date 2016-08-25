/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
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
@Table(name = "V_REPORT_L_PROCESSED")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VReportLProcessed.findAll", query = "SELECT v FROM VReportLProcessed v"),
    @NamedQuery(name = "VReportLProcessed.findByDeptId", query = "SELECT v FROM VReportLProcessed v WHERE v.deptId = :deptId"),
    @NamedQuery(name = "VReportLProcessed.findByLeaderId", query = "SELECT v FROM VReportLProcessed v WHERE v.leaderId = :leaderId"),
    @NamedQuery(name = "VReportLProcessed.findByLeaderName", query = "SELECT v FROM VReportLProcessed v WHERE v.leaderName = :leaderName"),
    @NamedQuery(name = "VReportLProcessed.findByTotalProcessed", query = "SELECT v FROM VReportLProcessed v WHERE v.totalProcessed = :totalProcessed")})
public class VReportLProcessed implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "DEPT_ID")
    private BigInteger deptId;
    @Column(name = "LEADER_ID")
    @Id
    private BigInteger leaderId;
    @Basic(optional = false)
    @Column(name = "LEADER_NAME")
    private String leaderName;
    @Column(name = "TOTAL_PROCESSED")
    private BigInteger totalProcessed;

    public VReportLProcessed() {
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
        this.leaderName = StringUtils.removeEventHandlerJS(leaderName);
    }

    public BigInteger getTotalProcessed() {
        return totalProcessed;
    }

    public void setTotalProcessed(BigInteger totalProcessed) {
        this.totalProcessed = totalProcessed;
    }
    
}
