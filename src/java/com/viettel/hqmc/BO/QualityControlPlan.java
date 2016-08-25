/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import com.viettel.common.util.StringUtils;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 *
 * @author vtit_havm2
 */
@Entity
@Table(name = "QUALITY_CONTROL_PLAN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QualityControlPlan.findAll", query = "SELECT q FROM QualityControlPlan q"),
    @NamedQuery(name = "QualityControlPlan.findByQualityControlPlanId", query = "SELECT q FROM QualityControlPlan q WHERE q.qualityControlPlanId = :qualityControlPlanId"),
    @NamedQuery(name = "QualityControlPlan.findByFileId", query = "SELECT q FROM QualityControlPlan q WHERE q.fileId = :fileId"),
    @NamedQuery(name = "QualityControlPlan.findByProductProcessDetail", query = "SELECT q FROM QualityControlPlan q WHERE q.productProcessDetail = :productProcessDetail"),
    @NamedQuery(name = "QualityControlPlan.findByControlTarget", query = "SELECT q FROM QualityControlPlan q WHERE q.controlTarget = :controlTarget"),
    @NamedQuery(name = "QualityControlPlan.findByTechnicalRegulation", query = "SELECT q FROM QualityControlPlan q WHERE q.technicalRegulation = :technicalRegulation"),
    @NamedQuery(name = "QualityControlPlan.findByPatternFrequence", query = "SELECT q FROM QualityControlPlan q WHERE q.patternFrequence = :patternFrequence"),
    @NamedQuery(name = "QualityControlPlan.findByTestDevice", query = "SELECT q FROM QualityControlPlan q WHERE q.testDevice = :testDevice"),
    @NamedQuery(name = "QualityControlPlan.findByTestMethod", query = "SELECT q FROM QualityControlPlan q WHERE q.testMethod = :testMethod"),
    @NamedQuery(name = "QualityControlPlan.findByNoteForm", query = "SELECT q FROM QualityControlPlan q WHERE q.noteForm = :noteForm"),
    @NamedQuery(name = "QualityControlPlan.findByNote", query = "SELECT q FROM QualityControlPlan q WHERE q.note = :note"),
    @NamedQuery(name = "QualityControlPlan.findByIsActive", query = "SELECT q FROM QualityControlPlan q WHERE q.isActive = :isActive")})
public class QualityControlPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "QUALITY_CONTROL_PLAN_SEQ", sequenceName = "QUALITY_CONTROL_PLAN_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUALITY_CONTROL_PLAN_SEQ")
    @Basic(optional = false)
    @Column(name = "QUALITY_CONTROL_PLAN_ID")
    private Long qualityControlPlanId;
    @Basic(optional = false)
    @Column(name = "FILE_ID")
    private Long fileId;
    @Column(name = "PRODUCT_PROCESS_DETAIL")
    private String productProcessDetail;
    @Column(name = "CONTROL_TARGET")
    private String controlTarget;
    @Column(name = "TECHNICAL_REGULATION")
    private String technicalRegulation;
    @Column(name = "PATTERN_FREQUENCE")
    private String patternFrequence;
    @Column(name = "TEST_DEVICE")
    private String testDevice;
    @Column(name = "TEST_METHOD")
    private String testMethod;
    @Column(name = "NOTE_FORM")
    private String noteForm;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "IS_TEMP")
    private Long isTemp;
    @Column(name = "ORIGINAL_ID")
    private Long originalId;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "LAST_IS_TEMP")
    private Long lastIsTemp;

    public QualityControlPlan() {
    }

    public QualityControlPlan(Long qualityControlPlanId) {
        this.qualityControlPlanId = qualityControlPlanId;
    }

    public QualityControlPlan(Long qualityControlPlanId, Long fileId) {
        this.qualityControlPlanId = qualityControlPlanId;
        this.fileId = fileId;
    }

    public QualityControlPlan cloneEntity(QualityControlPlan original) {
        QualityControlPlan entity = new QualityControlPlan();

        //entity.setQualityControlPlanId(original.getQualityControlPlanId());
        entity.setFileId(original.getFileId());
        entity.setProductProcessDetail(original.getProductProcessDetail());
        entity.setControlTarget(original.getControlTarget());
        entity.setTechnicalRegulation(original.getTechnicalRegulation());
        entity.setPatternFrequence(original.getPatternFrequence());
        entity.setTestDevice(original.getTestDevice());
        entity.setTestMethod(original.getTestMethod());
        entity.setNoteForm(original.getNoteForm());
        entity.setNote(original.getNote());
        entity.setIsActive(original.getIsActive());
        entity.setIsTemp(original.getIsTemp());
        entity.setOriginalId(original.getOriginalId());

        return entity;
    }

    public QualityControlPlan cloneEntity() {
        return cloneEntity(this);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qualityControlPlanId != null ? qualityControlPlanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QualityControlPlan)) {
            return false;
        }
        QualityControlPlan other = (QualityControlPlan) object;
        if ((this.qualityControlPlanId == null && other.qualityControlPlanId != null) || (this.qualityControlPlanId != null && !this.qualityControlPlanId.equals(other.qualityControlPlanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.QualityControlPlan[ qualityControlPlanId=" + qualityControlPlanId + " ]";
    }

    public Long getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(Long isTemp) {
        this.isTemp = isTemp;
    }

    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Long originalId) {
        this.originalId = originalId;
    }

    public Long getQualityControlPlanId() {
        return qualityControlPlanId;
    }

    public void setQualityControlPlanId(Long qualityControlPlanId) {
        this.qualityControlPlanId = qualityControlPlanId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getProductProcessDetail() {
        return productProcessDetail;
    }

    public void setProductProcessDetail(String productProcessDetail) {
        this.productProcessDetail = StringUtils.removeEventHandlerJS(productProcessDetail);
    }

    public String getControlTarget() {
        return controlTarget;
    }

    public void setControlTarget(String controlTarget) {
        this.controlTarget = StringUtils.removeEventHandlerJS(controlTarget);
    }

    public String getTechnicalRegulation() {
        return technicalRegulation;
    }

    public void setTechnicalRegulation(String technicalRegulation) {
        this.technicalRegulation = StringUtils.removeEventHandlerJS(technicalRegulation);
    }

    public String getPatternFrequence() {
        return patternFrequence;
    }

    public void setPatternFrequence(String patternFrequence) {
        this.patternFrequence = StringUtils.removeEventHandlerJS(patternFrequence);
    }

    public String getTestDevice() {
        return testDevice;
    }

    public void setTestDevice(String testDevice) {
        this.testDevice = StringUtils.removeEventHandlerJS(testDevice);
    }

    public String getTestMethod() {
        return testMethod;
    }

    public void setTestMethod(String testMethod) {
        this.testMethod = StringUtils.removeEventHandlerJS(testMethod);
    }

    public String getNoteForm() {
        return noteForm;
    }

    public void setNoteForm(String noteForm) {
        this.noteForm = StringUtils.removeEventHandlerJS(noteForm);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = StringUtils.removeEventHandlerJS(note);
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getLastIsTemp() {
        return lastIsTemp;
    }

    public void setLastIsTemp(Long lastIsTemp) {
        this.lastIsTemp = lastIsTemp;
    }

}
