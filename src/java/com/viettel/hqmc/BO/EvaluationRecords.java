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
 * @author binhnt53
 */
@Entity
@Table(name = "EVALUATION_RECORDS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvaluationRecords.findAll", query = "SELECT e FROM EvaluationRecords e"),
    @NamedQuery(name = "EvaluationRecords.findByEvaluationRecordsId", query = "SELECT e FROM EvaluationRecords e WHERE e.evaluationRecordsId = :evaluationRecordsId"),
    @NamedQuery(name = "EvaluationRecords.findByCreateDate", query = "SELECT e FROM EvaluationRecords e WHERE e.createDate = :createDate"),
    @NamedQuery(name = "EvaluationRecords.findBySendDate", query = "SELECT e FROM EvaluationRecords e WHERE e.sendDate = :sendDate"),
    @NamedQuery(name = "EvaluationRecords.findByBusinessName", query = "SELECT e FROM EvaluationRecords e WHERE e.businessName = :businessName"),
    @NamedQuery(name = "EvaluationRecords.findByBusinessAddress", query = "SELECT e FROM EvaluationRecords e WHERE e.businessAddress = :businessAddress"),
    @NamedQuery(name = "EvaluationRecords.findByProductName", query = "SELECT e FROM EvaluationRecords e WHERE e.productName = :productName"),
    @NamedQuery(name = "EvaluationRecords.findByLegal", query = "SELECT e FROM EvaluationRecords e WHERE e.legal = :legal"),
    @NamedQuery(name = "EvaluationRecords.findByLegalContent", query = "SELECT e FROM EvaluationRecords e WHERE e.legalContent = :legalContent"),
    @NamedQuery(name = "EvaluationRecords.findByFoodSafetyQuality", query = "SELECT e FROM EvaluationRecords e WHERE e.foodSafetyQuality = :foodSafetyQuality"),
    @NamedQuery(name = "EvaluationRecords.findByFoodSafetyQualityContent", query = "SELECT e FROM EvaluationRecords e WHERE e.foodSafetyQualityContent = :foodSafetyQualityContent"),
    @NamedQuery(name = "EvaluationRecords.findByEffectUtility", query = "SELECT e FROM EvaluationRecords e WHERE e.effectUtility = :effectUtility"),
    @NamedQuery(name = "EvaluationRecords.findByEffectUtilityContent", query = "SELECT e FROM EvaluationRecords e WHERE e.effectUtilityContent = :effectUtilityContent"),
    @NamedQuery(name = "EvaluationRecords.findByFirstStaffId", query = "SELECT e FROM EvaluationRecords e WHERE e.firstStaffId = :firstStaffId"),
    @NamedQuery(name = "EvaluationRecords.findByFirstStaffName", query = "SELECT e FROM EvaluationRecords e WHERE e.firstStaffName = :firstStaffName"),
    @NamedQuery(name = "EvaluationRecords.findBySecondStaffId", query = "SELECT e FROM EvaluationRecords e WHERE e.secondStaffId = :secondStaffId"),
    @NamedQuery(name = "EvaluationRecords.findBySecondStaffName", query = "SELECT e FROM EvaluationRecords e WHERE e.secondStaffName = :secondStaffName"),
    @NamedQuery(name = "EvaluationRecords.findByThirdStaffId", query = "SELECT e FROM EvaluationRecords e WHERE e.thirdStaffId = :thirdStaffId"),
    @NamedQuery(name = "EvaluationRecords.findByThirdStaffName", query = "SELECT e FROM EvaluationRecords e WHERE e.thirdStaffName = :thirdStaffName"),
    @NamedQuery(name = "EvaluationRecords.findByLeederStaffId", query = "SELECT e FROM EvaluationRecords e WHERE e.leederStaffId = :leederStaffId"),
    @NamedQuery(name = "EvaluationRecords.findByLeederStaffName", query = "SELECT e FROM EvaluationRecords e WHERE e.leederStaffName = :leederStaffName")})
public class EvaluationRecords implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "EVALUATION_RECORDS_SEQ", sequenceName = "EVALUATION_RECORDS_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVALUATION_RECORDS_SEQ")
    @Basic(optional = false)
    @Column(name = "EVALUATION_RECORDS_ID")
    private Long evaluationRecordsId;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "SEND_DATE")
    @Temporal(TemporalType.DATE)
    private Date sendDate;
    @Column(name = "BUSINESS_NAME")
    private String businessName;
    @Column(name = "BUSINESS_ADDRESS")
    private String businessAddress;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "LEGAL")
    private Long legal;
    @Column(name = "LEGAL_CONTENT")
    private String legalContent;
    @Column(name = "FOOD_SAFETY_QUALITY")
    private Long foodSafetyQuality;
    @Column(name = "FOOD_SAFETY_QUALITY_CONTENT")
    private String foodSafetyQualityContent;
    @Column(name = "EFFECT_UTILITY")
    private Long effectUtility;
    @Column(name = "EFFECT_UTILITY_CONTENT")
    private String effectUtilityContent;
    @Column(name = "FIRST_STAFF_ID")
    private Long firstStaffId;
    @Column(name = "FIRST_STAFF_NAME")
    private String firstStaffName;
    @Column(name = "SECOND_STAFF_ID")
    private Long secondStaffId;
    @Column(name = "SECOND_STAFF_NAME")
    private String secondStaffName;
    @Column(name = "THIRD_STAFF_ID")
    private Long thirdStaffId;
    @Column(name = "THIRD_STAFF_NAME")
    private String thirdStaffName;
    @Column(name = "LEEDER_STAFF_ID")
    private Long leederStaffId;
    @Column(name = "LEEDER_STAFF_NAME")
    private String leederStaffName;
    @Column(name = "FILE_ID")
    private Long fileId;
    @Column(name = "LEGAL_L")
    private Long legalL;
    @Column(name = "LEGAL_CONTENT_L")
    private String legalContentL;
    @Column(name = "FOOD_SAFETY_QUALITY_L")
    private Long foodSafetyQualityL;
    @Column(name = "FOOD_SAFETY_QUALITY_CONTENT_L")
    private String foodSafetyQualityContentL;
    @Column(name = "EFFECT_UTILITY_L")
    private Long effectUtilityL;
    @Column(name = "EFFECT_UTILITY_CONTENT_L")
    private String effectUtilityContentL;
    @Column(name = "FILES_STATUS")
    private Long filesStatus;
    @Column(name = "MAIN_CONTENT")
    private String mainContent;
    @Column(name = "FILES_STATUS_L")
    private Long filesStatusL;
    @Column(name = "MAIN_CONTENT_L")
    private String mainContentL;

    public EvaluationRecords() {
    }

    public EvaluationRecords(Long evaluationRecordsId) {
        this.evaluationRecordsId = evaluationRecordsId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluationRecordsId != null ? evaluationRecordsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluationRecords)) {
            return false;
        }
        EvaluationRecords other = (EvaluationRecords) object;
        if ((this.evaluationRecordsId == null && other.evaluationRecordsId != null) || (this.evaluationRecordsId != null && !this.evaluationRecordsId.equals(other.evaluationRecordsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.EvaluationRecords[ evaluationRecordsId=" + evaluationRecordsId + " ]";
    }
}
