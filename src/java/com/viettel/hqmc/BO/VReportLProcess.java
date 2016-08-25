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
@Table(name = "V_REPORT_L_PROCESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VReportLProcess.findAll", query = "SELECT v FROM VReportLProcess v"),
    @NamedQuery(name = "VReportLProcess.findByDeptId", query = "SELECT v FROM VReportLProcess v WHERE v.deptId = :deptId"),
    @NamedQuery(name = "VReportLProcess.findByLeaderId", query = "SELECT v FROM VReportLProcess v WHERE v.leaderId = :leaderId"),
    @NamedQuery(name = "VReportLProcess.findByLeaderName", query = "SELECT v FROM VReportLProcess v WHERE v.leaderName = :leaderName"),
    @NamedQuery(name = "VReportLProcess.findByTotalProcess", query = "SELECT v FROM VReportLProcess v WHERE v.totalProcess = :totalProcess")})
public class VReportLProcess implements Serializable {
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

    public VReportLProcess() {
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

    public BigInteger getTotalProcess() {
        return totalProcess;
    }

    public void setTotalProcess(BigInteger totalProcess) {
        this.totalProcess = totalProcess;
    }
    
}
