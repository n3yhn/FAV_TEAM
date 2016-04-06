/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

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
@Table(name = "MAINLY_TARGET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MainlyTarget.findAll", query = "SELECT m FROM MainlyTarget m"),
    @NamedQuery(name = "MainlyTarget.findByMainlyTargetId", query = "SELECT m FROM MainlyTarget m WHERE m.mainlyTargetId = :mainlyTargetId"),
    @NamedQuery(name = "MainlyTarget.findByFileId", query = "SELECT m FROM MainlyTarget m WHERE m.fileId = :fileId"),
    @NamedQuery(name = "MainlyTarget.findByTargetName", query = "SELECT m FROM MainlyTarget m WHERE m.targetName = :targetName"),
    @NamedQuery(name = "MainlyTarget.findByUnitName", query = "SELECT m FROM MainlyTarget m WHERE m.unitName = :unitName"),
    @NamedQuery(name = "MainlyTarget.findByPublishLevel", query = "SELECT m FROM MainlyTarget m WHERE m.publishLevel = :publishLevel"),
    @NamedQuery(name = "MainlyTarget.findByMeetLevel", query = "SELECT m FROM MainlyTarget m WHERE m.meetLevel = :meetLevel")})
public class MainlyTarget implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "MAINLY_TARGET_SEQ", sequenceName = "MAINLY_TARGET_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MAINLY_TARGET_SEQ")
    @Basic(optional = false)
    @Column(name = "MAINLY_TARGET_ID")
    private Long mainlyTargetId;
    @Basic(optional = false)
    @Column(name = "FILE_ID")
    private Long fileId;
    @Column(name = "TARGET_NAME")
    private String targetName;
    @Column(name = "UNIT_NAME")
    private String unitName;
    @Column(name = "UNIT_ID")
    private String unitId;
    @Column(name = "PUBLISH_LEVEL")
    private String publishLevel;
    @Column(name = "MEET_LEVEL")
    private String meetLevel;
    @Column(name = "IS_TEMP")
    private Long isTemp;
    @Column(name = "ORIGINAL_ID")
    private Long originalId;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "LAST_IS_TEMP")
    private Long lastIsTemp;

    public MainlyTarget() {
    }

    public MainlyTarget(Long mainlyTargetId) {
        this.mainlyTargetId = mainlyTargetId;
    }

    public MainlyTarget(Long mainlyTargetId, Long fileId) {
        this.mainlyTargetId = mainlyTargetId;
        this.fileId = fileId;
    }

    public MainlyTarget cloneEntity(MainlyTarget original) {
        MainlyTarget entity = new MainlyTarget();

        //entity.setMainlyTargetId(original.getMainlyTargetId());
        //entity.setFileId(original.getFileId());
        entity.setTargetName(original.getTargetName());
        entity.setUnitName(original.getUnitName());
        entity.setUnitId(original.getUnitId());
        entity.setPublishLevel(original.getPublishLevel());
        entity.setMeetLevel(original.getMeetLevel());
        entity.setIsTemp(0L);
        entity.setOriginalId(original.getMainlyTargetId());

        return entity;
    }
    
      public MainlyTarget cloneEntity() {
        return cloneEntity(this);
    }
  

    public boolean compareEntity(MainlyTarget original) {
        if (!this.getTargetName().equals(original.getTargetName())) {
            return false;
        }
        if (!this.getUnitName().equals(original.getUnitName())) {
            return false;
        }
        if (!this.getUnitId().equals(original.getUnitId())) {
            return false;
        }
        if (!this.getPublishLevel().equals(original.getPublishLevel())) {
            return false;
        }
        if (!this.getMeetLevel().equals(original.getMeetLevel())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mainlyTargetId != null ? mainlyTargetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MainlyTarget)) {
            return false;
        }
        MainlyTarget other = (MainlyTarget) object;
        if ((this.mainlyTargetId == null && other.mainlyTargetId != null) || (this.mainlyTargetId != null && !this.mainlyTargetId.equals(other.mainlyTargetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.MainlyTarget[ mainlyTargetId=" + mainlyTargetId + " ]";
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

    public Long getMainlyTargetId() {
        return mainlyTargetId;
    }

    public void setMainlyTargetId(Long mainlyTargetId) {
        this.mainlyTargetId = mainlyTargetId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getPublishLevel() {
        return publishLevel;
    }

    public void setPublishLevel(String publishLevel) {
        this.publishLevel = publishLevel;
    }

    public String getMeetLevel() {
        return meetLevel;
    }

    public void setMeetLevel(String meetLevel) {
        this.meetLevel = meetLevel;
    }

}
