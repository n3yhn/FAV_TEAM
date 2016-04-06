/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.FeePaymentInfo;
import java.util.Date;

/**
 *
 * @author GPCP_BINHNT53
 */
public class FeePaymentInfoForm {
    
    private Long paymentInfoId;
    private Long status;
    private String paymentPerson;
    private Date paymentDate;
    private Long feeId;
    private Long feePaymentTypeId;
    private String paymentInfo;
    private Long cost;
    private String billPath;
    private Date createDate;
    private Date updateDate;
    private Long fileId;
    private Long isActive;
    private String paymentCode;
    private String paymentConfirm;
    private String billCode;
    
    public FeePaymentInfoForm() {
    }
    
    public FeePaymentInfoForm(Long paymentInfoId, Long status, String paymentPerson, Date paymentDate, Long feeId, Long feePaymentTypeId, String paymentInfo, Long cost, String billPath, Date createDate, Date updateDate, Long fileId, Long isActive, String paymentCode, String paymentConfirm, String billCode) {
        this.paymentInfoId = paymentInfoId;
        this.status = status;
        this.paymentPerson = paymentPerson;
        this.paymentDate = paymentDate;
        this.feeId = feeId;
        this.feePaymentTypeId = feePaymentTypeId;
        this.paymentInfo = paymentInfo;
        this.cost = cost;
        this.billPath = billPath;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.fileId = fileId;
        this.isActive = isActive;
        this.paymentCode = paymentCode;
        this.paymentConfirm = paymentConfirm;
        this.billCode = billCode;
    }
    
    public FeePaymentInfoForm(FeePaymentInfo bo) {
        this.paymentInfoId = bo.getPaymentInfoId();
        this.status = bo.getStatus();
        this.paymentPerson = bo.getPaymentPerson();
        this.paymentDate = bo.getPaymentDate();
        this.feeId = bo.getFeeId();
        this.feePaymentTypeId = bo.getFeePaymentTypeId();
        this.paymentInfo = bo.getPaymentInfo();
        this.cost = bo.getCost();
        this.billPath = bo.getBillPath();
        this.createDate = bo.getCreateDate();
        this.updateDate = bo.getUpdateDate();
        this.fileId = bo.getFileId();
        this.isActive = bo.getIsActive();
        this.paymentCode = bo.getPaymentCode();
        this.paymentConfirm= bo.getPaymentConfirm();
        this.billCode = bo.getBillCode();
    }
    
    public FeePaymentInfo formToBo() {
        FeePaymentInfo bo = new FeePaymentInfo();
        bo.setPaymentInfoId(this.paymentInfoId);
        bo.setStatus(this.status);
        bo.setPaymentPerson(this.paymentPerson);
        bo.setPaymentDate(this.paymentDate);
        bo.setFeeId(this.feeId);
        bo.setFeePaymentTypeId(this.feePaymentTypeId);
        bo.setPaymentInfo(this.paymentInfo);
        bo.setCost(this.cost);
        bo.setBillPath(this.billPath);
        bo.setCreateDate(this.createDate);
        bo.setUpdateDate(this.updateDate);
        bo.setFileId(this.fileId);
        bo.setIsActive(this.isActive);
        bo.setPaymentCode(this.paymentCode);
        bo.setBillCode(this.billCode);
        bo.setPaymentConfirm(this.paymentConfirm);
        return bo;
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
        this.paymentPerson = paymentPerson;
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
        this.paymentInfo = paymentInfo;
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
        this.billPath = billPath;
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
        this.paymentCode = paymentCode;
    }

    public String getPaymentConfirm() {
        return paymentConfirm;
    }

    public void setPaymentConfirm(String paymentConfirm) {
        this.paymentConfirm = paymentConfirm;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }
    
}
