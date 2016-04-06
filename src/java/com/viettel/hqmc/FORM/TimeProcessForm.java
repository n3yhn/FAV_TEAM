/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.TimeProcess;
import java.util.Date;

/**
 *
 * @author binhnt53
 */
public class TimeProcessForm {

    private Long timeProcessId;
    private String name;
    private Date timeProcessDate;
    private String description;
    private Long isDayOff;
    private Long isActive;
    private Long createdBy;
    private Date createDate;
    private Long modifiedBy;
    private Date modifyDate;
    private Date timeProcessDateFrom;
    private Date timeProcessDateTo;

    //method====================================================================
    public TimeProcess formToBo() {
        TimeProcess bo = new TimeProcess();
        if (timeProcessId != null) {
            bo.setTimeProcessId(timeProcessId);
        }
        bo.setCreateDate(createDate);
        bo.setCreatedBy(createdBy);
        bo.setDescription(description);
        bo.setIsActive(isActive);
        bo.setIsDayOff(isDayOff);
        bo.setModifiedBy(modifiedBy);
        bo.setModifyDate(modifyDate);
        bo.setName(name);
        bo.setTimeProcessDate(timeProcessDate);
        return bo;
    }

    //constructor ==============================================================
    public TimeProcessForm(Long timeProcessId, String name, Date timeProcessDate, String description, Long isDayOff, Long isActive, Long createdBy, Date createDate, Long modifiedBy, Date modifyDate) {
        this.timeProcessId = timeProcessId;
        this.name = name;
        this.timeProcessDate = timeProcessDate;
        this.description = description;
        this.isDayOff = isDayOff;
        this.isActive = isActive;
        this.createdBy = createdBy;
        this.createDate = createDate;
        this.modifiedBy = modifiedBy;
        this.modifyDate = modifyDate;
    }

    public TimeProcessForm(TimeProcess bo) {
        TimeProcessForm form = new TimeProcessForm();
        if (bo.getTimeProcessId() != null) {
            form.setTimeProcessId(timeProcessId);
        }
        form.setCreateDate(bo.getCreateDate());
        form.setCreatedBy(bo.getCreatedBy());
        form.setDescription(bo.getDescription());
        form.setIsActive(bo.getIsActive());
        form.setIsDayOff(bo.getIsDayOff());
        form.setModifiedBy(bo.getModifiedBy());
        form.setModifyDate(bo.getModifyDate());
        form.setName(bo.getName());
        form.setTimeProcessDate(bo.getTimeProcessDate());
    }

    public TimeProcessForm() {
    }

    //setter getter ============================================================
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
        this.name = name;
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
        this.description = description;
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

    public Date getTimeProcessDateFrom() {
        return timeProcessDateFrom;
    }

    public void setTimeProcessDateFrom(Date timeProcessDateFrom) {
        this.timeProcessDateFrom = timeProcessDateFrom;
    }

    public Date getTimeProcessDateTo() {
        return timeProcessDateTo;
    }

    public void setTimeProcessDateTo(Date timeProcessDateTo) {
        this.timeProcessDateTo = timeProcessDateTo;
    }

}
