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
 * @author BINHNT53
 */
@Entity
@Table(name = "PAYMENT_HISTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentHistory.findAll", query = "SELECT p FROM PaymentHistory p"),
    @NamedQuery(name = "PaymentHistory.findByPaymentHistoryId", query = "SELECT p FROM PaymentHistory p WHERE p.paymentHistoryId = :paymentHistoryId"),
    @NamedQuery(name = "PaymentHistory.findByPaymentDate", query = "SELECT p FROM PaymentHistory p WHERE p.paymentDate = :paymentDate"),
    @NamedQuery(name = "PaymentHistory.findByIncome", query = "SELECT p FROM PaymentHistory p WHERE p.income = :income"),
    @NamedQuery(name = "PaymentHistory.findByPaymentNo", query = "SELECT p FROM PaymentHistory p WHERE p.paymentNo = :paymentNo"),
    @NamedQuery(name = "PaymentHistory.findByFileId", query = "SELECT p FROM PaymentHistory p WHERE p.fileId = :fileId"),
    @NamedQuery(name = "PaymentHistory.findByTaxCode", query = "SELECT p FROM PaymentHistory p WHERE p.taxCode = :taxCode"),
    @NamedQuery(name = "PaymentHistory.findByCreateDate", query = "SELECT p FROM PaymentHistory p WHERE p.createDate = :createDate"),
    @NamedQuery(name = "PaymentHistory.findByModifyDate", query = "SELECT p FROM PaymentHistory p WHERE p.modifyDate = :modifyDate"),
    @NamedQuery(name = "PaymentHistory.findByStatus", query = "SELECT p FROM PaymentHistory p WHERE p.status = :status"),
    @NamedQuery(name = "PaymentHistory.findByDescription", query = "SELECT p FROM PaymentHistory p WHERE p.description = :description")})
public class PaymentHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "PAYMENT_HISTORY_SEQ", sequenceName = "PAYMENT_HISTORY_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAYMENT_HISTORY_SEQ")
    @Basic(optional = false)
    @Column(name = "PAYMENT_HISTORY_ID")
    private Long paymentHistoryId;
    @Column(name = "PAYMENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @Column(name = "INCOME")
    private Long income;
    @Column(name = "PAYMENT_NO")
    private String paymentNo;
    @Column(name = "FILE_ID")
    private Long fileId;
    @Column(name = "TAX_CODE")
    private String taxCode;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "FILE_CODE")
    private String fileCode;

    public PaymentHistory() {
    }

    public PaymentHistory(Long paymentHistoryId) {
        this.paymentHistoryId = paymentHistoryId;
    }

    public Long getPaymentHistoryId() {
        return paymentHistoryId;
    }

    public void setPaymentHistoryId(Long paymentHistoryId) {
        this.paymentHistoryId = paymentHistoryId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentHistoryId != null ? paymentHistoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentHistory)) {
            return false;
        }
        PaymentHistory other = (PaymentHistory) object;
        if ((this.paymentHistoryId == null && other.paymentHistoryId != null) || (this.paymentHistoryId != null && !this.paymentHistoryId.equals(other.paymentHistoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.PaymentHistory[ paymentHistoryId=" + paymentHistoryId + " ]";
    }

}
