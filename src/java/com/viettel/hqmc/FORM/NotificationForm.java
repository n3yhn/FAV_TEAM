/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.Notification;
import java.util.Date;

/**
 *
 * @author hieptq
 */
public class NotificationForm {

    private Long noticeId;
    private String content;
    private Date createDate;
    private String modifiedBy;
    private Date modifiedDate;
    private Long type;
    private Long isActive;
    private Long sort;

    public NotificationForm() {
    }

    public NotificationForm(Long noticeId, String content, Date createDate, String modifiedBy, Date modifiedDate, Long type, Long isActive, Long sort) {
        this.noticeId = noticeId;
        this.content = content;
        this.createDate = createDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.type = type;
        this.isActive = isActive;
        this.sort = sort;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }
}
