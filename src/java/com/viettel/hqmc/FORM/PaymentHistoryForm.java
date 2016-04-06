/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.PaymentHistory;
import java.util.Date;

/**
 *
 * @author BINHNT53
 */
public class PaymentHistoryForm {

    private Long paymentHistoryId;
    private Date paymentDate;
    private Long income;
    private String paymentNo;
    private Long fileId;
    private String taxCode;
    private Date createDate;
    private Date modifyDate;
    private Long status;
    private String description;
    private String fileCode;

    public PaymentHistoryForm() {
    }

    public PaymentHistoryForm(PaymentHistory bo) {
        this.paymentHistoryId = bo.getPaymentHistoryId();
        this.paymentDate = bo.getPaymentDate();
        this.income = bo.getIncome();
        this.paymentNo = bo.getPaymentNo();
        this.fileId = bo.getFileId();
        this.taxCode = bo.getTaxCode();
        this.createDate = bo.getCreateDate();
        this.modifyDate = bo.getModifyDate();
        this.status = bo.getStatus();
        this.description = bo.getDescription();
        this.fileCode = bo.getFileCode();
    }

    public PaymentHistoryForm(Long paymentHistoryId, Date paymentDate, Long income, String paymentNo, Long fileId, String taxCode, Date createDate, Date modifyDate, Long status, String description) {
        this.paymentHistoryId = paymentHistoryId;
        this.paymentDate = paymentDate;
        this.income = income;
        this.paymentNo = paymentNo;
        this.fileId = fileId;
        this.taxCode = taxCode;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.status = status;
        this.description = description;
    }

    public PaymentHistory convertToEntity() {
        PaymentHistory bo = new PaymentHistory();
        bo.setCreateDate(createDate);
        bo.setDescription(description);
        bo.setFileId(fileId);
        bo.setIncome(income);
        bo.setModifyDate(modifyDate);
        bo.setPaymentDate(paymentDate);
        bo.setPaymentHistoryId(paymentHistoryId);
        bo.setPaymentNo(paymentNo);
        bo.setStatus(status);
        bo.setTaxCode(taxCode);
        bo.setFileCode(fileCode);
        return bo;
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

}
