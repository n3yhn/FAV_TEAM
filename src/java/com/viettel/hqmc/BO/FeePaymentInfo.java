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
 * @author Administrator
 */
@Entity
@Table(name = "FEE_PAYMENT_INFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeePaymentInfo.findAll", query = "SELECT f FROM FeePaymentInfo f"),
    @NamedQuery(name = "FeePaymentInfo.findByPaymentInfoId", query = "SELECT f FROM FeePaymentInfo f WHERE f.paymentInfoId = :paymentInfoId"),
    @NamedQuery(name = "FeePaymentInfo.findByStatus", query = "SELECT f FROM FeePaymentInfo f WHERE f.status = :status"),
    @NamedQuery(name = "FeePaymentInfo.findByPaymentPerson", query = "SELECT f FROM FeePaymentInfo f WHERE f.paymentPerson = :paymentPerson"),
    @NamedQuery(name = "FeePaymentInfo.findByPaymentDate", query = "SELECT f FROM FeePaymentInfo f WHERE f.paymentDate = :paymentDate"),
    @NamedQuery(name = "FeePaymentInfo.findByFeeId", query = "SELECT f FROM FeePaymentInfo f WHERE f.feeId = :feeId"),
    @NamedQuery(name = "FeePaymentInfo.findByFeePaymentTypeId", query = "SELECT f FROM FeePaymentInfo f WHERE f.feePaymentTypeId = :feePaymentTypeId"),
    @NamedQuery(name = "FeePaymentInfo.findByPaymentInfo", query = "SELECT f FROM FeePaymentInfo f WHERE f.paymentInfo = :paymentInfo"),
    @NamedQuery(name = "FeePaymentInfo.findByCost", query = "SELECT f FROM FeePaymentInfo f WHERE f.cost = :cost"),
    @NamedQuery(name = "FeePaymentInfo.findByBillPath", query = "SELECT f FROM FeePaymentInfo f WHERE f.billPath = :billPath"),
    @NamedQuery(name = "FeePaymentInfo.findByCreateDate", query = "SELECT f FROM FeePaymentInfo f WHERE f.createDate = :createDate"),
    @NamedQuery(name = "FeePaymentInfo.findByUpdateDate", query = "SELECT f FROM FeePaymentInfo f WHERE f.updateDate = :updateDate")})
public class FeePaymentInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "FEE_PAYMENT_INFO_SEQ", sequenceName = "FEE_PAYMENT_INFO_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FEE_PAYMENT_INFO_SEQ")
    @Basic(optional = false)
    @Column(name = "PAYMENT_INFO_ID")
    private Long paymentInfoId;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "PAYMENT_PERSON")
    private String paymentPerson;
    @Column(name = "PAYMENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @Column(name = "FEE_ID")
    private Long feeId;
    @Column(name = "FEE_PAYMENT_TYPE_ID")
    private Long feePaymentTypeId;
    @Column(name = "PAYMENT_INFO")
    private String paymentInfo;
    @Column(name = "COST")
    private Long cost;
    @Column(name = "BILL_PATH")
    private String billPath;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @Column(name = "FILE_ID")
    private Long fileId;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "PAYMENT_CODE")
    private String paymentCode;
    @Column(name = "PAYMENT_CONFIRM")
    private String paymentConfirm;
    @Column(name = "BILL_CODE")
    private String billCode;
    @Column(name = "DATE_CONFIRM")
    @Temporal(TemporalType.DATE)
    private Date dateConfirm;
    @Column(name = "COMMENT_REJECT")
    private String commentReject;
    @Column(name = "FILES_CODE")
    private String filesCode;
    @Column(name = "COST_CHECK")
    private Long costCheck;
    @Column(name = "FEE_ID_OLD")
    private Long feeIdOld;

    public FeePaymentInfo() {
    }

    public FeePaymentInfo(Long paymentInfoId) {
        this.paymentInfoId = paymentInfoId;
    }

    public Long getPaymentInfoId() {
        return paymentInfoId;
    }

    public void setPaymentInfoId(Long paymentInfoId) {
        this.paymentInfoId = paymentInfoId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getPaymentPerson() {
        return paymentPerson;
    }

    public void setPaymentPerson(String paymentPerson) {
        this.paymentPerson = StringUtils.removeEventHandlerJS(paymentPerson);
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getFeeId() {
        return feeId;
    }

    public void setFeeId(Long feeId) {
        this.feeId = feeId;
    }

    public Long getFeePaymentTypeId() {
        return feePaymentTypeId;
    }

    public void setFeePaymentTypeId(Long feePaymentTypeId) {
        this.feePaymentTypeId = feePaymentTypeId;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = StringUtils.removeEventHandlerJS(paymentInfo);
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getBillPath() {
        return billPath;
    }

    public void setBillPath(String billPath) {
        this.billPath = StringUtils.removeEventHandlerJS(billPath);
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = StringUtils.removeEventHandlerJS(paymentCode);
    }

    public String getPaymentConfirm() {
        return paymentConfirm;
    }

    public void setPaymentConfirm(String paymentConfirm) {
        this.paymentConfirm = StringUtils.removeEventHandlerJS(paymentConfirm);
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = StringUtils.removeEventHandlerJS(billCode);
    }

    public Date getDateConfirm() {
        return dateConfirm;
    }

    public void setDateConfirm(Date dateConfirm) {
        this.dateConfirm = dateConfirm;
    }

    public String getCommentReject() {
        return commentReject;
    }

    public void setCommentReject(String commentReject) {
        this.commentReject = StringUtils.removeEventHandlerJS(commentReject);
    }

    public String getFilesCode() {
        return filesCode;
    }

    public void setFilesCode(String filesCode) {
        this.filesCode = StringUtils.removeEventHandlerJS(filesCode);
    }

    public Long getCostCheck() {
        return costCheck;
    }

    public void setCostCheck(Long costCheck) {
        this.costCheck = costCheck;
    }

    public Long getFeeIdOld() {
        return feeIdOld;
    }

    public void setFeeIdOld(Long feeIdOld) {
        this.feeIdOld = feeIdOld;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentInfoId != null ? paymentInfoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeePaymentInfo)) {
            return false;
        }
        FeePaymentInfo other = (FeePaymentInfo) object;
        if ((this.paymentInfoId == null && other.paymentInfoId != null) || (this.paymentInfoId != null && !this.paymentInfoId.equals(other.paymentInfoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.FeePaymentInfo[ paymentInfoId=" + paymentInfoId + " ]";
    }

}
