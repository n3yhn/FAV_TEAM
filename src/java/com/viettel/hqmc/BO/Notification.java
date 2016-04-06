/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.hqmc.BO;

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
 * @author gpcp_dund1
 */
@Entity
@Table(name = "NOTIFICATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
    @NamedQuery(name = "Notification.findByNoticeId", query = "SELECT n FROM Notification n WHERE n.noticeId = :noticeId"),
    @NamedQuery(name = "Notification.findByContent", query = "SELECT n FROM Notification n WHERE n.content = :content"),
    @NamedQuery(name = "Notification.findByCreateDate", query = "SELECT n FROM Notification n WHERE n.createDate = :createDate"),
    @NamedQuery(name = "Notification.findByModifiedBy", query = "SELECT n FROM Notification n WHERE n.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "Notification.findByModifiedDate", query = "SELECT n FROM Notification n WHERE n.modifiedDate = :modifiedDate"),
    @NamedQuery(name = "Notification.findByType", query = "SELECT n FROM Notification n WHERE n.type = :type"),
    @NamedQuery(name = "Notification.findByIsActive", query = "SELECT n FROM Notification n WHERE n.isActive = :isActive"),
    @NamedQuery(name = "Notification.findBySort", query = "SELECT n FROM Notification n WHERE n.sort = :sort")})
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "NOTIFICATION_SEQ", sequenceName = "NOTIFICATION_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTIFICATION_SEQ")
    @Basic(optional = false)
    @Column(name = "NOTICE_ID")
    private Long noticeId;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;
    @Column(name = "TYPE")
    private Long type;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "SORT")
    private Long sort;

    public Notification() {
    }

    public Notification(Long noticeId) {
        this.noticeId = noticeId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noticeId != null ? noticeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.noticeId == null && other.noticeId != null) || (this.noticeId != null && !this.noticeId.equals(other.noticeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.Notification[ noticeId=" + noticeId + " ]";
    }
    
}
