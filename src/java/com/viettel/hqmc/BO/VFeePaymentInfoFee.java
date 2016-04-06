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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GPCP_BINHNT53
 */
@Entity
@Table(name = "V_FEE_PAYMENT_INFO_FEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VFeePaymentInfoFee.findAll", query = "SELECT v FROM VFeePaymentInfoFee v"),
    @NamedQuery(name = "VFeePaymentInfoFee.findByFeeId", query = "SELECT v FROM VFeePaymentInfoFee v WHERE v.feeId = :feeId"),
    @NamedQuery(name = "VFeePaymentInfoFee.findByFeeName", query = "SELECT v FROM VFeePaymentInfoFee v WHERE v.feeName = :feeName"),
    @NamedQuery(name = "VFeePaymentInfoFee.findByDescription", query = "SELECT v FROM VFeePaymentInfoFee v WHERE v.description = :description"),
    @NamedQuery(name = "VFeePaymentInfoFee.findByCost", query = "SELECT v FROM VFeePaymentInfoFee v WHERE v.cost = :cost"),
    @NamedQuery(name = "VFeePaymentInfoFee.findByFeeType", query = "SELECT v FROM VFeePaymentInfoFee v WHERE v.feeType = :feeType"),
    @NamedQuery(name = "VFeePaymentInfoFee.findByStatus", query = "SELECT v FROM VFeePaymentInfoFee v WHERE v.status = :status"),
    @NamedQuery(name = "VFeePaymentInfoFee.findByFeePaymentTypeId", query = "SELECT v FROM VFeePaymentInfoFee v WHERE v.feePaymentTypeId = :feePaymentTypeId"),
    @NamedQuery(name = "VFeePaymentInfoFee.findByPrice", query = "SELECT v FROM VFeePaymentInfoFee v WHERE v.price = :price"),
    @NamedQuery(name = "VFeePaymentInfoFee.findByPaymentPerson", query = "SELECT v FROM VFeePaymentInfoFee v WHERE v.paymentPerson = :paymentPerson"),
    @NamedQuery(name = "VFeePaymentInfoFee.findByPaymentDate", query = "SELECT v FROM VFeePaymentInfoFee v WHERE v.paymentDate = :paymentDate"),
    @NamedQuery(name = "VFeePaymentInfoFee.findByPaymentInfo", query = "SELECT v FROM VFeePaymentInfoFee v WHERE v.paymentInfo = :paymentInfo"),
    @NamedQuery(name = "VFeePaymentInfoFee.findByBillPath", query = "SELECT v FROM VFeePaymentInfoFee v WHERE v.billPath = :billPath"),
    @NamedQuery(name = "VFeePaymentInfoFee.findByPaymentInfoId", query = "SELECT v FROM VFeePaymentInfoFee v WHERE v.paymentInfoId = :paymentInfoId")})
public class VFeePaymentInfoFee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "FEE_ID")
    @Id
    private long feeId;
    @Column(name = "FEE_NAME")
    private String feeName;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "COST")
    private Long cost;
    @Column(name = "FEE_TYPE")
    private Long feeType;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "FEE_PAYMENT_TYPE_ID")
    private Long feePaymentTypeId;
    @Column(name = "PRICE")
    private Long price;
    @Column(name = "PAYMENT_PERSON")
    private String paymentPerson;
    @Column(name = "PAYMENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @Column(name = "PAYMENT_INFO")
    private String paymentInfo;
    @Column(name = "BILL_PATH")
    private String billPath;
    @Basic(optional = false)
    @Column(name = "PAYMENT_INFO_ID")
    private long paymentInfoId;
    @Column(name = "FILE_ID")
    private Long fileId;

    public VFeePaymentInfoFee() {
    }

    public long getFeeId() {
        return feeId;
    }

    public void setFeeId(long feeId) {
        this.feeId = feeId;
    }

    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getFeeType() {
        return feeType;
    }

    public void setFeeType(Long feeType) {
        this.feeType = feeType;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getFeePaymentTypeId() {
        return feePaymentTypeId;
    }

    public void setFeePaymentTypeId(Long feePaymentTypeId) {
        this.feePaymentTypeId = feePaymentTypeId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPaymentPerson() {
        return paymentPerson;
    }

    public void setPaymentPerson(String paymentPerson) {
        this.paymentPerson = paymentPerson;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getBillPath() {
        return billPath;
    }

    public void setBillPath(String billPath) {
        this.billPath = billPath;
    }

    public long getPaymentInfoId() {
        return paymentInfoId;
    }

    public void setPaymentInfoId(long paymentInfoId) {
        this.paymentInfoId = paymentInfoId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

}
