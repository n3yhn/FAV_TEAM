/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import java.util.Date;

/**
 *
 * @author binhnt53
 */
public class FeePaymentFileForm {

    private Long feeId;
    private String feeName;
    private String description;
    private Long isActive;
    private Long cost;
    private Long status;
    private Long feePaymentType;
    private String procedureId;
    private String procedureName;
    private Long feeType;
    private Long fileId;
    private Long price;
    private String paymentPerson;
    private String billPath;
    private String paymentDate;
    private String paymentInfo;
    private String fileCode;
    private String productName;
    private Long paymentInfoId;
    private String businessName;
    private String paymentCode;
    private String paymentConfirm;
    private String billCode;
    private Date dateFrom;
    private Date dateTo;
    private String dateConfirm;
    private Date dateConfirmSearchFrom;
    private Date dateConfirmSearchTo;
    private String commentReject;
    private String businessAddress;
    private Long productType;
    private Long searchType;
    private Long countTM;
    private Long countCK;
    private Long countOL;
    private Long totalTM;
    private Long totalCK;
    private Long totalOL;
    private String filesCode;
    private Long index;

    public FeePaymentFileForm() {
    }

    public FeePaymentFileForm(Long feeId, String feeName, String description, Long isActive, Long cost, Long status, Long feePaymentType, String procedureId, String procedureName, Long feeType, Long fileId, Long price, String paymentPerson, String billPath, String paymentDate, String paymentInfo, String fileCode, String productName, Long paymentInfoId, String paymentCode, String paymentConfirm, String billCode) {
        this.feeId = feeId;
        this.feeName = feeName;
        this.description = description;
        this.isActive = isActive;
        this.cost = cost;
        this.status = status;
        this.feePaymentType = feePaymentType;
        this.procedureId = procedureId;
        this.procedureName = procedureName;
        this.feeType = feeType;
        this.fileId = fileId;
        this.price = price;
        this.paymentPerson = paymentPerson;
        this.billPath = billPath;
        this.paymentDate = paymentDate;
        this.paymentInfo = paymentInfo;
        this.fileCode = fileCode;
        this.productName = productName;
        this.paymentInfoId = paymentInfoId;
        this.paymentCode = paymentCode;
        this.paymentConfirm = paymentConfirm;
        this.billCode = billCode;
        this.productType = productType;
        this.filesCode = filesCode;
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

    public String getDescription() {
        return description;
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

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getFeePaymentType() {
        return feePaymentType;
    }

    public void setFeePaymentType(Long feePaymentType) {
        this.feePaymentType = feePaymentType;
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

    public String getBillPath() {
        return billPath;
    }

    public void setBillPath(String billPath) {
        this.billPath = billPath;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getPaymentInfoId() {
        return paymentInfoId;
    }

    public void setPaymentInfoId(Long paymentInfoId) {
        this.paymentInfoId = paymentInfoId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
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

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getDateConfirm() {
        return dateConfirm;
    }

    public void setDateConfirm(String dateConfirm) {
        this.dateConfirm = dateConfirm;
    }

    public Date getDateConfirmSearchFrom() {
        return dateConfirmSearchFrom;
    }

    public void setDateConfirmSearchFrom(Date dateConfirmSearchFrom) {
        this.dateConfirmSearchFrom = dateConfirmSearchFrom;
    }

    public Date getDateConfirmSearchTo() {
        return dateConfirmSearchTo;
    }

    public void setDateConfirmSearchTo(Date dateConfirmSearchTo) {
        this.dateConfirmSearchTo = dateConfirmSearchTo;
    }

    public String getCommentReject() {
        return commentReject;
    }

    public void setCommentReject(String commentReject) {
        this.commentReject = commentReject;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    // hieptq them nhom san pham 17.11.14
    public Long getProductType() {
        return productType;
    }

    public void setProductType(Long productType) {
        this.productType = productType;
    }

    public Long getSearchType() {
        return searchType;
    }

    public void setSearchType(Long searchType) {
        this.searchType = searchType;
    }

    public Long getCountTM() {
        return countTM;
    }

    public void setCountTM(Long countTM) {
        this.countTM = countTM;
    }

    public Long getCountCK() {
        return countCK;
    }

    public void setCountCK(Long countCK) {
        this.countCK = countCK;
    }

    public Long getCountOL() {
        return countOL;
    }

    public void setCountOL(Long countOL) {
        this.countOL = countOL;
    }

    public Long getTotalTM() {
        return totalTM;
    }

    public void setTotalTM(Long totalTM) {
        this.totalTM = totalTM;
    }

    public Long getTotalCK() {
        return totalCK;
    }

    public void setTotalCK(Long totalCK) {
        this.totalCK = totalCK;
    }

    public Long getTotalOL() {
        return totalOL;
    }

    public void setTotalOL(Long totalOL) {
        this.totalOL = totalOL;
    }

    public String getFilesCode() {
        return filesCode;
    }

    public void setFilesCode(String filesCode) {
        this.filesCode = filesCode;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

}
