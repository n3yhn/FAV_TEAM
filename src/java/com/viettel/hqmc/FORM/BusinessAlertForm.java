/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class BusinessAlertForm {

    private Long businessAlertId;
    private Long businessId;
    private Long isActive;
    private Date createdDate;
    private Long createdById;
    private String createdByName;
    private Long seen;
    private String content;

    public BusinessAlertForm(Long businessAlertId, Long businessId, Long isActive, Date createdDate, Long createdById, String createdByName, Long seen, String content) {
        this.businessAlertId = businessAlertId;
        this.businessId = businessId;
        this.isActive = isActive;
        this.createdDate = createdDate;
        this.createdById = createdById;
        this.createdByName = createdByName;
        this.seen = seen;
        this.content = content;
    }

    public BusinessAlertForm() {
    }

    public Long getBusinessAlertId() {
        return businessAlertId;
    }

    public void setBusinessAlertId(Long businessAlertId) {
        this.businessAlertId = businessAlertId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public Long getSeen() {
        return seen;
    }

    public void setSeen(Long seen) {
        this.seen = seen;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
