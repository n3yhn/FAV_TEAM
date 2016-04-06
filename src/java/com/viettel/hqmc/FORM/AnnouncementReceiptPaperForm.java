/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.common.util.DateTimeUtils;
import com.viettel.hqmc.BO.AnnouncementReceiptPaper;
import java.util.Date;

/**
 *
 * @author vtit_binhnt53
 */
public class AnnouncementReceiptPaperForm {

    private Long announcementReceiptPaperId;
    private String receiptNo;
    private Date receiptDate;
    private String receiptDeptName;
    private String signerName;
    private Date signDate;
    private String businessName;
    private Long businessId;
    private String telephone;
    private String fax;
    private String email;
    private String productName;
    private String manufactureName;
    private String manufactureAdd;
    private String nationName;
    private String matchingTarget;
    private Date effectiveDate;
    private Integer isActive;
    private Integer status;
    private Long fileId;

    public AnnouncementReceiptPaperForm() {
    }

    public AnnouncementReceiptPaperForm(AnnouncementReceiptPaper bo) {
        this.announcementReceiptPaperId = bo.getAnnouncementReceiptPaperId();
        this.receiptNo = bo.getReceiptNo();
        this.receiptDate = bo.getReceiptDate();
        this.receiptDeptName = bo.getReceiptDeptName();
        this.signerName = bo.getSignerName();
        this.signDate = bo.getSignDate();
        this.businessName = bo.getBusinessName();
        this.businessId = bo.getBusinessId();
        this.telephone = bo.getTelephone();
        this.fax = bo.getFax();
        this.email = bo.getEmail();
        this.productName = bo.getProductName();
        this.manufactureName = bo.getManufactureName();
        this.manufactureAdd = bo.getManufactureAdd();
        this.nationName = bo.getNationName();
        this.matchingTarget = bo.getMatchingTarget();
        this.effectiveDate = bo.getEffectiveDate();
        this.isActive = bo.getIsActive();
        this.status = bo.getStatus();
    }

    public AnnouncementReceiptPaperForm(Long announcementReceiptPaperId, String receiptNo, Date receiptDate, String receiptDeptName, String signerName, Date signDate, String businessName, Long businessId, String telephone, String fax, String email, String productName, String manufactureName, String manufactureAdd, String nationName, String matchingTarget, Date effectiveDate, Integer isActive, Integer status, Long fileId) {
        this.announcementReceiptPaperId = announcementReceiptPaperId;
        this.receiptNo = receiptNo;
        this.receiptDate = receiptDate;
        this.receiptDeptName = receiptDeptName;
        this.signerName = signerName;
        this.signDate = signDate;
        this.businessName = businessName;
        this.businessId = businessId;
        this.telephone = telephone;
        this.fax = fax;
        this.email = email;
        this.productName = productName;
        this.manufactureName = manufactureName;
        this.manufactureAdd = manufactureAdd;
        this.nationName = nationName;
        this.matchingTarget = matchingTarget;
        this.effectiveDate = effectiveDate;
        this.isActive = isActive;
        this.status = status;
        this.fileId = fileId;
    }

    public AnnouncementReceiptPaper convertToEntity() {
        AnnouncementReceiptPaper item = new AnnouncementReceiptPaper();
        item.setAnnouncementReceiptPaperId(announcementReceiptPaperId);
        item.setBusinessId(businessId);
        item.setBusinessName(businessName);
        item.setEffectiveDate(effectiveDate);
        item.setEmail(email);
        item.setFax(fax);
        item.setIsActive(isActive);
        item.setManufactureAdd(manufactureAdd);
        item.setManufactureName(manufactureName);
        item.setMatchingTarget(matchingTarget);
        item.setNationName(nationName);
        item.setProductName(productName);
        item.setReceiptDate(receiptDate);
        item.setReceiptDeptName(receiptDeptName);
        item.setReceiptNo(receiptNo);
        item.setSignDate(signDate);
        item.setSignerName(signerName);
        item.setTelephone(telephone);
        item.setStatus(status);
        return item;
    }

    public Long getAnnouncementReceiptPaperId() {
        return announcementReceiptPaperId;
    }

    public void setAnnouncementReceiptPaperId(Long announcementReceiptPaperId) {
        this.announcementReceiptPaperId = announcementReceiptPaperId;
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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufactureName() {
        return manufactureName;
    }

    public void setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
    }

    public String getManufactureAdd() {
        return manufactureAdd;
    }

    public void setManufactureAdd(String manufactureAdd) {
        this.manufactureAdd = manufactureAdd;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getMatchingTarget() {
        return matchingTarget;
    }

    public void setMatchingTarget(String matchingTarget) {
        this.matchingTarget = matchingTarget;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getSignDateStr() {
        if (signDate == null) {
            return "";
        }
        return DateTimeUtils.convertDateToString(signDate, "dd/MM/yyyy");
    }
   
    public String getReceiptDateStr() {
        if (receiptDate == null) {
            return "";
        }
        return DateTimeUtils.convertDateToString(receiptDate, "dd/MM/yyyy");
    }
    public String getEffectiveDateStr() {
        if (effectiveDate == null) {
            return "";
        }
        return DateTimeUtils.convertDateToString(effectiveDate, "dd/MM/yyyy");
    }
}
