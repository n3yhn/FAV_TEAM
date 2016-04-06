/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.Fee;
import java.util.Date;

/**
 *
 * @author hieptq
 */
public class FeeForm {

    private Long feeId;
    private String feeName;
    private Date createDate;
    private String description;
    private Long isActive;
    private Long createUserId;
    private Date updateDate;
    private Long price;
    private Long updateUserId;
    private String procedureId;
    private String procedureName;
    private Long feeType;
    private Long fileId;
    private Long feePaymentTypeId;
    private Long status;
    private Integer flagSavePaging;
    
    public FeeForm(Long feeId, String feeName, Date createDate, String description, Long isActive, Long createUserId, Date updateDate, Long price, Long updateUserId) {
        this.feeId = feeId;
        this.feeName = feeName;
        this.createDate = createDate;
        this.description = description;
        this.isActive = isActive;
        this.createUserId = createUserId;
        this.updateDate = updateDate;
        this.price = price;
        this.updateUserId = updateUserId;
        this.procedureId = procedureId;
        this.feeType = feeType;

    }

    public Fee formToBo() {
        Fee bo = new Fee();
        if (feeId != null) {
            bo.setFeeId(feeId);
        }
        bo.setCreateDate(createDate);
        bo.setCreateUserId(createUserId);
        bo.setDescription(description);
        bo.setIsActive(isActive);
        bo.setFeeName(feeName);
        bo.setPrice(price);
        bo.setUpdateDate(updateDate);
        bo.setUpdateUserId(updateUserId);
        bo.setFeeType(feeType);
        return bo;
    }

    public FeeForm(Fee bo) {
        FeeForm form = new FeeForm();
        if (bo.getFeeId() != null) {
            form.setFeeId(feeId);
        }
        form.setCreateDate(bo.getCreateDate());
        form.setCreateUserId(bo.getCreateUserId());
        form.setDescription(bo.getDescription());
        form.setIsActive(bo.getIsActive());
        form.setFeeName(bo.getFeeName());
        form.setPrice(bo.getPrice());
        form.setUpdateDate(bo.getUpdateDate());
        form.setUpdateUserId(bo.getUpdateUserId());
        form.setFeeType(bo.getFeeType());
    }

    public FeeForm() {
    }

    public Long getFeeId() {
        return feeId;
    }

    public void setFeeId(Long feeId) {
        this.feeId = feeId;
    }

    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description.trim();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public Long getFeeType() {
        return feeType;
    }

    public void setFeeType(Long feeType) {
        this.feeType = feeType;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getFeePaymentTypeId() {
        return feePaymentTypeId;
    }

    public void setFeePaymentTypeId(Long feePaymentTypeId) {
        this.feePaymentTypeId = feePaymentTypeId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Integer getFlagSavePaging() {
        return flagSavePaging;
    }

    public void setFlagSavePaging(Integer flagSavePaging) {
        this.flagSavePaging = flagSavePaging;
    }
    
}
