/*
 * To change this template, choose Tools | Templates
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
 * @author vtit_binhnt53
 */
@Entity
@Table(name = "CONFIRM_ANNOUNCEMENT_PAPER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfirmAnnouncementPaper.findAll", query = "SELECT c FROM ConfirmAnnouncementPaper c"),
    @NamedQuery(name = "ConfirmAnnouncementPaper.findByConfirmAnnouncementPaperId", query = "SELECT c FROM ConfirmAnnouncementPaper c WHERE c.confirmAnnouncementPaperId = :confirmAnnouncementPaperId"),
    @NamedQuery(name = "ConfirmAnnouncementPaper.findByConfirmationNo", query = "SELECT c FROM ConfirmAnnouncementPaper c WHERE c.confirmationNo = :confirmationNo"),
    @NamedQuery(name = "ConfirmAnnouncementPaper.findByDateOfIssue", query = "SELECT c FROM ConfirmAnnouncementPaper c WHERE c.dateOfIssue = :dateOfIssue"),
    @NamedQuery(name = "ConfirmAnnouncementPaper.findByIssueAgencyName", query = "SELECT c FROM ConfirmAnnouncementPaper c WHERE c.issueAgencyName = :issueAgencyName"),
    @NamedQuery(name = "ConfirmAnnouncementPaper.findByIssueAgencyId", query = "SELECT c FROM ConfirmAnnouncementPaper c WHERE c.issueAgencyId = :issueAgencyId"),
    @NamedQuery(name = "ConfirmAnnouncementPaper.findByIsActive", query = "SELECT c FROM ConfirmAnnouncementPaper c WHERE c.isActive = :isActive")})
public class ConfirmAnnouncementPaper implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "CONFIRM_ANNOUNCEMENT_PAPER_SEQ", sequenceName = "CONFIRM_ANNOUNCEMENT_PAPER_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONFIRM_ANNOUNCEMENT_PAPER_SEQ")
    @Basic(optional = false)
    @Column(name = "CONFIRM_ANNOUNCEMENT_PAPER_ID")
    private Long confirmAnnouncementPaperId;
    @Column(name = "CONFIRMATION_NO")
    private String confirmationNo;
    @Column(name = "DATE_OF_ISSUE")
    @Temporal(TemporalType.DATE)
    private Date dateOfIssue;
    @Column(name = "ISSUE_AGENCY_NAME")
    private String issueAgencyName;
    @Column(name = "ISSUE_AGENCY_ID")
    private Long issueAgencyId;
    @Column(name = "IS_ACTIVE")
    private Long isActive;

    public ConfirmAnnouncementPaper() {
    }

    public ConfirmAnnouncementPaper(Long confirmAnnouncementPaperId) {
        this.confirmAnnouncementPaperId = confirmAnnouncementPaperId;
    }

    public Long getConfirmAnnouncementPaperId() {
        return confirmAnnouncementPaperId;
    }

    public void setConfirmAnnouncementPaperId(Long confirmAnnouncementPaperId) {
        this.confirmAnnouncementPaperId = confirmAnnouncementPaperId;
    }

    public String getConfirmationNo() {
        return confirmationNo;
    }

    public void setConfirmationNo(String confirmationNo) {
        this.confirmationNo = StringUtils.removeEventHandlerJS(confirmationNo);
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getIssueAgencyName() {
        return issueAgencyName;
    }

    public void setIssueAgencyName(String issueAgencyName) {
        this.issueAgencyName = StringUtils.removeEventHandlerJS(issueAgencyName);
    }

    public Long getIssueAgencyId() {
        return issueAgencyId;
    }

    public void setIssueAgencyId(Long issueAgencyId) {
        this.issueAgencyId = issueAgencyId;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (confirmAnnouncementPaperId != null ? confirmAnnouncementPaperId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfirmAnnouncementPaper)) {
            return false;
        }
        ConfirmAnnouncementPaper other = (ConfirmAnnouncementPaper) object;
        if ((this.confirmAnnouncementPaperId == null && other.confirmAnnouncementPaperId != null) || (this.confirmAnnouncementPaperId != null && !this.confirmAnnouncementPaperId.equals(other.confirmAnnouncementPaperId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.ConfirmAnnouncementPaper[ confirmAnnouncementPaperId=" + confirmAnnouncementPaperId + " ]";
    }
    
}
