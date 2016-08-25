/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GPCP_BINHNT53
 */
@Entity
@Table(name = "COUNT_NO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CountNo.findAll", query = "SELECT c FROM CountNo c"),
    @NamedQuery(name = "CountNo.findByCountNoId", query = "SELECT c FROM CountNo c WHERE c.countNoId = :countNoId"),
    @NamedQuery(name = "CountNo.findByReceiveNo", query = "SELECT c FROM CountNo c WHERE c.receiveNo = :receiveNo"),
    @NamedQuery(name = "CountNo.findByAnnouncementNo", query = "SELECT c FROM CountNo c WHERE c.announcementNo = :announcementNo"),
    @NamedQuery(name = "CountNo.findByDeptId", query = "SELECT c FROM CountNo c WHERE c.deptId = :deptId"),
    @NamedQuery(name = "CountNo.findByDeptCode", query = "SELECT c FROM CountNo c WHERE c.deptCode = :deptCode"),
    @NamedQuery(name = "CountNo.findByName", query = "SELECT c FROM CountNo c WHERE c.name = :name"),
    @NamedQuery(name = "CountNo.findByIsActive", query = "SELECT c FROM CountNo c WHERE c.isActive = :isActive")})
public class CountNo implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "COUNT_NO_SEQ", sequenceName = "COUNT_NO_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COUNT_NO_SEQ")
    @Basic(optional = false)
    @Column(name = "COUNT_NO_ID")
    private Long countNoId;
    @Column(name = "RECEIVE_NO")
    private Long receiveNo;
    @Column(name = "ANNOUNCEMENT_NO")
    private Long announcementNo;
    @Column(name = "DEPT_ID")
    private Long deptId;
    @Column(name = "DEPT_CODE")
    private String deptCode;
    @Column(name = "NAME")
    private String name;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "SEND_NO")
    private Long sendNo;
    @Column(name = "KEYPAY_NO")
    private Long keypayNo;

    public CountNo() {
    }

    public CountNo(Long countNoId) {
        this.countNoId = countNoId;
    }

    public Long getCountNoId() {
        return countNoId;
    }

    public void setCountNoId(Long countNoId) {
        this.countNoId = countNoId;
    }

    public Long getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(Long receiveNo) {
        this.receiveNo = receiveNo;
    }

    public Long getAnnouncementNo() {
        return announcementNo;
    }

    public void setAnnouncementNo(Long announcementNo) {
        this.announcementNo = announcementNo;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = StringUtils.removeEventHandlerJS(deptCode);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.removeEventHandlerJS(name);
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getSendNo() {
        return sendNo;
    }

    public void setSendNo(Long sendNo) {
        this.sendNo = sendNo;
    }

    public Long getKeypayNo() {
        return keypayNo;
    }

    public void setKeypayNo(Long keypayNo) {
        this.keypayNo = keypayNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countNoId != null ? countNoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CountNo)) {
            return false;
        }
        CountNo other = (CountNo) object;
        if ((this.countNoId == null && other.countNoId != null) || (this.countNoId != null && !this.countNoId.equals(other.countNoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.DAOHE.CountNo[ countNoId=" + countNoId + " ]";
    }

}
