/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "V_REPORT_PC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VReportPc.findAll", query = "SELECT v FROM VReportPc v"),
    @NamedQuery(name = "VReportPc.findByReceiveUserId", query = "SELECT v FROM VReportPc v WHERE v.receiveUserId = :receiveUserId"),
    @NamedQuery(name = "VReportPc.findByReceiveUser", query = "SELECT v FROM VReportPc v WHERE v.receiveUser = :receiveUser"),
    @NamedQuery(name = "VReportPc.findByReceiveGroupId", query = "SELECT v FROM VReportPc v WHERE v.receiveGroupId = :receiveGroupId"),
    @NamedQuery(name = "VReportPc.findByReceiveGroup", query = "SELECT v FROM VReportPc v WHERE v.receiveGroup = :receiveGroup"),
    @NamedQuery(name = "VReportPc.findByThuong", query = "SELECT v FROM VReportPc v WHERE v.thuong = :thuong"),
    @NamedQuery(name = "VReportPc.findByChucNang", query = "SELECT v FROM VReportPc v WHERE v.chucNang = :chucNang")})
public class VReportPc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "RECEIVE_USER_ID")
    @Id
    private Long receiveUserId;
    @Column(name = "RECEIVE_USER")
    private String receiveUser;
    @Column(name = "RECEIVE_GROUP_ID")
    private Long receiveGroupId;
    @Column(name = "RECEIVE_GROUP")
    private String receiveGroup;
    @Column(name = "THUONG")
    private Long thuong;
    @Column(name = "CHUC_NANG")
    private Long chucNang;

    public VReportPc() {
    }

    public Long getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(Long receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = StringUtils.removeEventHandlerJS(receiveUser);
    }

    public Long getReceiveGroupId() {
        return receiveGroupId;
    }

    public void setReceiveGroupId(Long receiveGroupId) {
        this.receiveGroupId = receiveGroupId;
    }

    public String getReceiveGroup() {
        return receiveGroup;
    }

    public void setReceiveGroup(String receiveGroup) {
        this.receiveGroup =StringUtils.removeEventHandlerJS( receiveGroup);
    }

    public Long getThuong() {
        return thuong;
    }

    public void setThuong(Long thuong) {
        this.thuong = thuong;
    }

    public Long getChucNang() {
        return chucNang;
    }

    public void setChucNang(Long chucNang) {
        this.chucNang = chucNang;
    }
    
}
