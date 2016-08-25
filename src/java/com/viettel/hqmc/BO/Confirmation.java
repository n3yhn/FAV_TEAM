/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 *
 * @author vtit_havm2
 */
@Entity
@Table(name = "CONFIRMATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Confirmation.findAll", query = "SELECT c FROM Confirmation c"),
    @NamedQuery(name = "Confirmation.findByConfirmationId", query = "SELECT c FROM Confirmation c WHERE c.confirmationId = :confirmationId"),
    @NamedQuery(name = "Confirmation.findByFileId", query = "SELECT c FROM Confirmation c WHERE c.fileId = :fileId"),
    @NamedQuery(name = "Confirmation.findByConfirmationNo", query = "SELECT c FROM Confirmation c WHERE c.confirmationNo = :confirmationNo"),
    @NamedQuery(name = "Confirmation.findByDateOfIssue", query = "SELECT c FROM Confirmation c WHERE c.dateOfIssue = :dateOfIssue"),
    @NamedQuery(name = "Confirmation.findByIssueAgencyName", query = "SELECT c FROM Confirmation c WHERE c.issueAgencyName = :issueAgencyName"),
    @NamedQuery(name = "Confirmation.findByIssueAgencyId", query = "SELECT c FROM Confirmation c WHERE c.issueAgencyId = :issueAgencyId"),
    @NamedQuery(name = "Confirmation.findByIsActive", query = "SELECT c FROM Confirmation c WHERE c.isActive = :isActive")})
public class Confirmation implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "CONFIRMATION_SEQ", sequenceName = "CONFIRMATION_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONFIRMATION_SEQ")
    @Basic(optional = false)
    @Column(name = "CONFIRMATION_ID")
    private Long confirmationId;
    @Basic(optional = false)
    @Column(name = "FILE_ID")
    private BigInteger fileId;
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

    public Confirmation() {
    }

    public Confirmation(Long confirmationId) {
        this.confirmationId = confirmationId;
    }

    public Confirmation(Long confirmationId, BigInteger fileId) {
        this.confirmationId = confirmationId;
        this.fileId = fileId;
    }

    public Long getConfirmationId() {
        return confirmationId;
    }

    public void setConfirmationId(Long confirmationId) {
        this.confirmationId = confirmationId;
    }

    public BigInteger getFileId() {
        return fileId;
    }

    public void setFileId(BigInteger fileId) {
        this.fileId = fileId;
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
        hash += (confirmationId != null ? confirmationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Confirmation)) {
            return false;
        }
        Confirmation other = (Confirmation) object;
        if ((this.confirmationId == null && other.confirmationId != null) || (this.confirmationId != null && !this.confirmationId.equals(other.confirmationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.Confirmation[ confirmationId=" + confirmationId + " ]";
    }
    
}
