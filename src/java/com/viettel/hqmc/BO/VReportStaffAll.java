/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GPCP_BINHNT53
 */
@Entity
@Table(name = "V_REPORT_STAFF_ALL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VReportStaffAll.findAll", query = "SELECT v FROM VReportStaffAll v"),
    @NamedQuery(name = "VReportStaffAll.findByDeptId", query = "SELECT v FROM VReportStaffAll v WHERE v.deptId = :deptId"),
    @NamedQuery(name = "VReportStaffAll.findByObjectId", query = "SELECT v FROM VReportStaffAll v WHERE v.objectId = :objectId"),
    @NamedQuery(name = "VReportStaffAll.findByFullName", query = "SELECT v FROM VReportStaffAll v WHERE v.fullName = :fullName"),
    @NamedQuery(name = "VReportStaffAll.findByStatus", query = "SELECT v FROM VReportStaffAll v WHERE v.status = :status"),
    @NamedQuery(name = "VReportStaffAll.findByProcessStatus", query = "SELECT v FROM VReportStaffAll v WHERE v.processStatus = :processStatus"),
    @NamedQuery(name = "VReportStaffAll.findBySendUser", query = "SELECT v FROM VReportStaffAll v WHERE v.sendUser = :sendUser"),
    @NamedQuery(name = "VReportStaffAll.findBySendUserId", query = "SELECT v FROM VReportStaffAll v WHERE v.sendUserId = :sendUserId"),
    @NamedQuery(name = "VReportStaffAll.findByReceiveUserId", query = "SELECT v FROM VReportStaffAll v WHERE v.receiveUserId = :receiveUserId"),
    @NamedQuery(name = "VReportStaffAll.findByReceiveUser", query = "SELECT v FROM VReportStaffAll v WHERE v.receiveUser = :receiveUser")})
public class VReportStaffAll implements Serializable {
    @Column(name = "RECEIVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveDate;
    @Column(name = "SEND_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;
    private static final long serialVersionUID = 1L;
    @Column(name = "DEPT_ID")
    private BigInteger deptId;
    @Column(name = "OBJECT_ID")
    @Id
    private BigInteger objectId;
    @Basic(optional = false)
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "STATUS")
    private Short status;
    @Column(name = "PROCESS_STATUS")
    private Long processStatus;
    @Column(name = "SEND_USER")
    private String sendUser;
    @Column(name = "SEND_USER_ID")
    private BigInteger sendUserId;
    @Column(name = "RECEIVE_USER_ID")
    private BigInteger receiveUserId;
    @Column(name = "RECEIVE_USER")
    private String receiveUser;

    public VReportStaffAll() {
    }

    public BigInteger getDeptId() {
        return deptId;
    }

    public void setDeptId(BigInteger deptId) {
        this.deptId = deptId;
    }

    public BigInteger getObjectId() {
        return objectId;
    }

    public void setObjectId(BigInteger objectId) {
        this.objectId = objectId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Long getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Long processStatus) {
        this.processStatus = processStatus;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public BigInteger getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(BigInteger sendUserId) {
        this.sendUserId = sendUserId;
    }

    public BigInteger getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(BigInteger receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
    
}
