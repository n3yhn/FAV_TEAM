/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.EvaluationRecords;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author binhnt53
 */
public class EvaluationRecordsForm implements Serializable {

    private Long evaluationRecordsId;
    private Date createDate;
    private Date sendDate;
    private String businessName;
    private String businessAddress;
    private String productName;
    private Long legal;
    private String legalContent;
    private Long foodSafetyQuality;
    private String foodSafetyQualityContent;
    private Long effectUtility;
    private String effectUtilityContent;
    private Long firstStaffId;
    private String firstStaffName;
    private Long secondStaffId;
    private String secondStaffName;
    private Long thirdStaffId;
    private String thirdStaffName;
    private Long leederStaffId;
    private String leederStaffName;
    private Long fileId;
    private Long legalL;
    private String legalContentL;
    private Long foodSafetyQualityL;
    private String foodSafetyQualityContentL;
    private Long effectUtilityL;
    private String effectUtilityContentL;
    private Long filesStatus;
    private String mainContent;
    private Long filesStatusL;
    private String mainContentL;

    public EvaluationRecordsForm() {
    }

    public EvaluationRecordsForm(EvaluationRecords entity) {
        this.evaluationRecordsId = entity.getEvaluationRecordsId();
        this.createDate = entity.getCreateDate();
        this.sendDate = entity.getSendDate();
        this.businessName = entity.getBusinessName();
        this.businessAddress = entity.getBusinessAddress();
        this.productName = entity.getProductName();
        this.legal = entity.getLegal();
        this.legalContent = entity.getLegalContent();
        this.foodSafetyQuality = entity.getFoodSafetyQuality();
        this.foodSafetyQualityContent = entity.getFoodSafetyQualityContent();
        this.effectUtility = entity.getEffectUtility();
        this.effectUtilityContent = entity.getEffectUtilityContent();
        this.firstStaffId = entity.getFirstStaffId();
        this.firstStaffName = entity.getFirstStaffName();
        this.secondStaffId = entity.getSecondStaffId();
        this.secondStaffName = entity.getSecondStaffName();
        this.thirdStaffId = entity.getThirdStaffId();
        this.thirdStaffName = entity.getThirdStaffName();
        this.leederStaffId = entity.getLeederStaffId();
        this.leederStaffName = entity.getLeederStaffName();
        this.fileId = entity.getFileId();
        this.legalL = entity.getLegalL();
        this.legalContentL = entity.getLegalContentL();
        this.foodSafetyQualityL = entity.getFoodSafetyQualityL();
        this.foodSafetyQualityContentL = entity.getFoodSafetyQualityContentL();
        this.effectUtilityL = entity.getEffectUtilityL();
        this.effectUtilityContentL = entity.getEffectUtilityContentL();
        this.filesStatus = entity.getFilesStatus();
        this.filesStatusL = entity.getFilesStatusL();
        this.mainContent = entity.getMainContent();
        this.mainContentL = entity.getMainContentL();
    }

    public EvaluationRecords convertToEntity() {
        EvaluationRecords entity = new EvaluationRecords();
        entity.setEvaluationRecordsId(this.evaluationRecordsId);
        entity.setCreateDate(this.createDate);
        entity.setSendDate(this.sendDate);
        entity.setBusinessName(this.businessName);
        entity.setBusinessAddress(this.businessAddress);
        entity.setProductName(this.productName);
        entity.setLegal(this.legal);
        entity.setLegalContent(this.legalContent);
        entity.setFoodSafetyQuality(this.foodSafetyQuality);
        entity.setFoodSafetyQualityContent(this.foodSafetyQualityContent);
        entity.setEffectUtility(this.effectUtility);
        entity.setEffectUtilityContent(this.effectUtilityContent);
        entity.setFirstStaffId(this.firstStaffId);
        entity.setFirstStaffName(this.firstStaffName);
        entity.setSecondStaffId(this.secondStaffId);
        entity.setSecondStaffName(this.secondStaffName);
        entity.setThirdStaffId(this.thirdStaffId);
        entity.setThirdStaffName(this.thirdStaffName);
        entity.setLeederStaffId(this.leederStaffId);
        entity.setLeederStaffName(this.leederStaffName);
        entity.setFileId(this.fileId);
        entity.setLegalL(this.legalL);
        entity.setLegalContentL(this.legalContentL);
        entity.setFoodSafetyQualityL(this.foodSafetyQualityL);
        entity.setFoodSafetyQualityContentL(this.foodSafetyQualityContentL);
        entity.setEffectUtilityL(this.effectUtilityL);
        entity.setEffectUtilityContentL(this.effectUtilityContentL);
        entity.setMainContent(this.mainContent);
        entity.setMainContentL(this.mainContentL);
        entity.setFilesStatus(this.filesStatus);
        entity.setFilesStatusL(this.filesStatusL);
        return entity;
    }

    public Long getEvaluationRecordsId() {
        return evaluationRecordsId;
    }

    public void setEvaluationRecordsId(Long evaluationRecordsId) {
        this.evaluationRecordsId = evaluationRecordsId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getLegal() {
        return legal;
    }

    public void setLegal(Long legal) {
        this.legal = legal;
    }

    public String getLegalContent() {
        return legalContent;
    }

    public void setLegalContent(String legalContent) {
        this.legalContent = legalContent;
    }

    public Long getFoodSafetyQuality() {
        return foodSafetyQuality;
    }

    public void setFoodSafetyQuality(Long foodSafetyQuality) {
        this.foodSafetyQuality = foodSafetyQuality;
    }

    public String getFoodSafetyQualityContent() {
        return foodSafetyQualityContent;
    }

    public void setFoodSafetyQualityContent(String foodSafetyQualityContent) {
        this.foodSafetyQualityContent = foodSafetyQualityContent;
    }

    public Long getEffectUtility() {
        return effectUtility;
    }

    public void setEffectUtility(Long effectUtility) {
        this.effectUtility = effectUtility;
    }

    public String getEffectUtilityContent() {
        return effectUtilityContent;
    }

    public void setEffectUtilityContent(String effectUtilityContent) {
        this.effectUtilityContent = effectUtilityContent;
    }

    public Long getFirstStaffId() {
        return firstStaffId;
    }

    public void setFirstStaffId(Long firstStaffId) {
        this.firstStaffId = firstStaffId;
    }

    public String getFirstStaffName() {
        return firstStaffName;
    }

    public void setFirstStaffName(String firstStaffName) {
        this.firstStaffName = firstStaffName;
    }

    public Long getSecondStaffId() {
        return secondStaffId;
    }

    public void setSecondStaffId(Long secondStaffId) {
        this.secondStaffId = secondStaffId;
    }

    public String getSecondStaffName() {
        return secondStaffName;
    }

    public void setSecondStaffName(String secondStaffName) {
        this.secondStaffName = secondStaffName;
    }

    public Long getThirdStaffId() {
        return thirdStaffId;
    }

    public void setThirdStaffId(Long thirdStaffId) {
        this.thirdStaffId = thirdStaffId;
    }

    public String getThirdStaffName() {
        return thirdStaffName;
    }

    public void setThirdStaffName(String thirdStaffName) {
        this.thirdStaffName = thirdStaffName;
    }

    public Long getLeederStaffId() {
        return leederStaffId;
    }

    public void setLeederStaffId(Long leederStaffId) {
        this.leederStaffId = leederStaffId;
    }

    public String getLeederStaffName() {
        return leederStaffName;
    }

    public void setLeederStaffName(String leederStaffName) {
        this.leederStaffName = leederStaffName;
    }

    public Long getFilesId() {
        return fileId;
    }

    public void setFilesId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getLegalL() {
        return legalL;
    }

    public void setLegalL(Long legalL) {
        this.legalL = legalL;
    }

    public String getLegalContentL() {
        return legalContentL;
    }

    public void setLegalContentL(String legalContentL) {
        this.legalContentL = legalContentL;
    }

    public Long getFoodSafetyQualityL() {
        return foodSafetyQualityL;
    }

    public void setFoodSafetyQualityL(Long foodSafetyQualityL) {
        this.foodSafetyQualityL = foodSafetyQualityL;
    }

    public String getFoodSafetyQualityContentL() {
        return foodSafetyQualityContentL;
    }

    public void setFoodSafetyQualityContentL(String foodSafetyQualityContentL) {
        this.foodSafetyQualityContentL = foodSafetyQualityContentL;
    }

    public Long getEffectUtilityL() {
        return effectUtilityL;
    }

    public void setEffectUtilityL(Long effectUtilityL) {
        this.effectUtilityL = effectUtilityL;
    }

    public String getEffectUtilityContentL() {
        return effectUtilityContentL;
    }

    public void setEffectUtilityContentL(String effectUtilityContentL) {
        this.effectUtilityContentL = effectUtilityContentL;
    }

    public Long getFilesStatus() {
        return filesStatus;
    }

    public void setFilesStatus(Long filesStatus) {
        this.filesStatus = filesStatus;
    }

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    public Long getFilesStatusL() {
        return filesStatusL;
    }

    public void setFilesStatusL(Long filesStatusL) {
        this.filesStatusL = filesStatusL;
    }

    public String getMainContentL() {
        return mainContentL;
    }

    public void setMainContentL(String mainContentL) {
        this.mainContentL = mainContentL;
    }

}
