/*
 * To change this template, choose Tools | Templates
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
 * @author vtit_havm2
 */
@Entity
@Table(name = "ANNOUNCEMENT_RECEIPT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnnouncementReceipt.findAll", query = "SELECT a FROM AnnouncementReceipt a"),
    @NamedQuery(name = "AnnouncementReceipt.findByReceiptId", query = "SELECT a FROM AnnouncementReceipt a WHERE a.receiptId = :receiptId"),
    @NamedQuery(name = "AnnouncementReceipt.findByReceiptNo", query = "SELECT a FROM AnnouncementReceipt a WHERE a.receiptNo = :receiptNo"),
    @NamedQuery(name = "AnnouncementReceipt.findByReceiptDate", query = "SELECT a FROM AnnouncementReceipt a WHERE a.receiptDate = :receiptDate"),
    @NamedQuery(name = "AnnouncementReceipt.findByReceiptDeptName", query = "SELECT a FROM AnnouncementReceipt a WHERE a.receiptDeptName = :receiptDeptName"),
    @NamedQuery(name = "AnnouncementReceipt.findBySignerName", query = "SELECT a FROM AnnouncementReceipt a WHERE a.signerName = :signerName"),
    @NamedQuery(name = "AnnouncementReceipt.findBySignDate", query = "SELECT a FROM AnnouncementReceipt a WHERE a.signDate = :signDate"),
    @NamedQuery(name = "AnnouncementReceipt.findByIsActive", query = "SELECT a FROM AnnouncementReceipt a WHERE a.isActive = :isActive")})
public class AnnouncementReceipt implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "ANNOUNCEMENT_RECEIPT_SEQ", sequenceName = "ANNOUNCEMENT_RECEIPT_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANNOUNCEMENT_RECEIPT_SEQ")
    @Basic(optional = false)
    @Column(name = "RECEIPT_ID")
    private Long receiptId;
    @Column(name = "RECEIPT_NO")
    private String receiptNo;
    @Column(name = "RECEIPT_DATE")
    @Temporal(TemporalType.DATE)
    private Date receiptDate;
    @Column(name = "RECEIPT_DEPT_NAME")
    private String receiptDeptName;
    @Column(name = "SIGNER_NAME")
    private String signerName;
    @Column(name = "SIGN_DATE")
    @Temporal(TemporalType.DATE)
    private Date signDate;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "ANNOUNCEMENT_ID")
    private Long announcementId;

    public AnnouncementReceipt() {
    }

    public AnnouncementReceipt(Long receiptId) {
        this.receiptId = receiptId;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getReceiptDeptName() {
        return receiptDeptName;
    }

    public void setReceiptDeptName(String receiptDeptName) {
        this.receiptDeptName = receiptDeptName;
    }

    public String getSignerName() {
        return signerName;
    }

    public void setSignerName(String signerName) {
        this.signerName = signerName;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Long announcementId) {
        this.announcementId = announcementId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (receiptId != null ? receiptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnnouncementReceipt)) {
            return false;
        }
        AnnouncementReceipt other = (AnnouncementReceipt) object;
        if ((this.receiptId == null && other.receiptId != null) || (this.receiptId != null && !this.receiptId.equals(other.receiptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.AnnouncementReceipt[ receiptId=" + receiptId + " ]";
    }
}
