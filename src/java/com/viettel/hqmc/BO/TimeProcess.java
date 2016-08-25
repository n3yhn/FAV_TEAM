/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author binhnt53
 */
@Entity
@Table(name = "TIME_PROCESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TimeProcess.findAll", query = "SELECT t FROM TimeProcess t"),
    @NamedQuery(name = "TimeProcess.findByTimeProcessId", query = "SELECT t FROM TimeProcess t WHERE t.timeProcessId = :timeProcessId"),
    @NamedQuery(name = "TimeProcess.findByName", query = "SELECT t FROM TimeProcess t WHERE t.name = :name"),
    @NamedQuery(name = "TimeProcess.findByTimeProcessDate", query = "SELECT t FROM TimeProcess t WHERE t.timeProcessDate = :timeProcessDate"),
    @NamedQuery(name = "TimeProcess.findByDescription", query = "SELECT t FROM TimeProcess t WHERE t.description = :description"),
    @NamedQuery(name = "TimeProcess.findByIsDayOff", query = "SELECT t FROM TimeProcess t WHERE t.isDayOff = :isDayOff"),
    @NamedQuery(name = "TimeProcess.findByIsActive", query = "SELECT t FROM TimeProcess t WHERE t.isActive = :isActive"),
    @NamedQuery(name = "TimeProcess.findByCreatedBy", query = "SELECT t FROM TimeProcess t WHERE t.createdBy = :createdBy"),
    @NamedQuery(name = "TimeProcess.findByCreateDate", query = "SELECT t FROM TimeProcess t WHERE t.createDate = :createDate"),
    @NamedQuery(name = "TimeProcess.findByModifiedBy", query = "SELECT t FROM TimeProcess t WHERE t.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "TimeProcess.findByModifyDate", query = "SELECT t FROM TimeProcess t WHERE t.modifyDate = :modifyDate")})
public class TimeProcess implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "TIME_PROCESS_SEQ", sequenceName = "TIME_PROCESS_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIME_PROCESS_SEQ")
    @Basic(optional = false)
    @Column(name = "TIME_PROCESS_ID")
    private Long timeProcessId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TIME_PROCESS_DATE")
    @Temporal(TemporalType.DATE)
    private Date timeProcessDate;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IS_DAY_OFF")
    private Long isDayOff;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "CREATED_BY")
    private Long createdBy;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;
    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;

    public TimeProcess() {
    }

    public TimeProcess(Long timeProcessId) {
        this.timeProcessId = timeProcessId;
    }

    public Long getTimeProcessId() {
        return timeProcessId;
    }

    public void setTimeProcessId(Long timeProcessId) {
        this.timeProcessId = timeProcessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.removeEventHandlerJS(name);
    }

    public Date getTimeProcessDate() {
        return timeProcessDate;
    }

    public void setTimeProcessDate(Date timeProcessDate) {
        this.timeProcessDate = timeProcessDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = StringUtils.removeEventHandlerJS(description);
    }

    public Long getIsDayOff() {
        return isDayOff;
    }

    public void setIsDayOff(Long isDayOff) {
        this.isDayOff = isDayOff;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (timeProcessId != null ? timeProcessId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TimeProcess)) {
            return false;
        }
        TimeProcess other = (TimeProcess) object;
        if ((this.timeProcessId == null && other.timeProcessId != null) || (this.timeProcessId != null && !this.timeProcessId.equals(other.timeProcessId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.TimeProcess[ timeProcessId=" + timeProcessId + " ]";
    }    
}
