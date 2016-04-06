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
@Table(name = "PRODUCT_TARGET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductTarget.findAll", query = "SELECT p FROM ProductTarget p"),
    @NamedQuery(name = "ProductTarget.findByProductTargetId", query = "SELECT p FROM ProductTarget p WHERE p.productTargetId = :productTargetId"),
    @NamedQuery(name = "ProductTarget.findByTargetName", query = "SELECT p FROM ProductTarget p WHERE p.targetName = :targetName"),
    @NamedQuery(name = "ProductTarget.findByTargetType", query = "SELECT p FROM ProductTarget p WHERE p.targetType = :targetType"),
    @NamedQuery(name = "ProductTarget.findByUnitName", query = "SELECT p FROM ProductTarget p WHERE p.unitName = :unitName"),
    @NamedQuery(name = "ProductTarget.findByMaxLevel", query = "SELECT p FROM ProductTarget p WHERE p.maxLevel = :maxLevel"),
    @NamedQuery(name = "ProductTarget.findByFileId", query = "SELECT p FROM ProductTarget p WHERE p.fileId = :fileId")})
public class ProductTarget implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "MAINLY_TARGET_SEQ", sequenceName = "MAINLY_TARGET_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MAINLY_TARGET_SEQ")
    @Basic(optional = false)
    @Column(name = "PRODUCT_TARGET_ID")
    private Long productTargetId;
    @Column(name = "TARGET_NAME")
    private String targetName;
    @Column(name = "TARGET_TYPE")
    private Long targetType;
    @Column(name = "UNIT_NAME")
    private String unitName;
    @Column(name = "UNIT_ID")
    private String unitId;
    @Column(name = "MAX_LEVEL")
    private String maxLevel;
    @Basic(optional = false)
    @Column(name = "FILE_ID")
    private Long fileId;
    @Column(name = "IS_TEMP")
    private Long isTemp;
    @Column(name = "ORIGINAL_ID")
    private Long originalId;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "LAST_IS_TEMP")
    private Long lastIsTemp;

    public ProductTarget() {
    }

    public ProductTarget(Long productTargetId) {
        this.productTargetId = productTargetId;
    }

    public ProductTarget(Long productTargetId, Long fileId) {
        this.productTargetId = productTargetId;
        this.fileId = fileId;
    }

    public ProductTarget cloneEntity(ProductTarget original) {
        ProductTarget entity = new ProductTarget();
        //entity.setProductTargetId(original.getProductTargetId());
        entity.setTargetName(original.getTargetName());
        entity.setTargetType(original.getTargetType());
        entity.setUnitName(original.getUnitName());
        entity.setUnitId(original.getUnitId());
        entity.setMaxLevel(original.getMaxLevel());
        entity.setFileId(original.getFileId());
        entity.setIsTemp(0L);
        entity.setOriginalId(original.getProductTargetId());
        return entity;
    }
    
    public ProductTarget cloneEntity() {
        return cloneEntity(this);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productTargetId != null ? productTargetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductTarget)) {
            return false;
        }
        ProductTarget other = (ProductTarget) object;
        if ((this.productTargetId == null && other.productTargetId != null) || (this.productTargetId != null && !this.productTargetId.equals(other.productTargetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.ProductTarget[ productTargetId=" + productTargetId + " ]";
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

    public Long getProductTargetId() {
        return productTargetId;
    }

    public void setProductTargetId(Long productTargetId) {
        this.productTargetId = productTargetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Long getTargetType() {
        return targetType;
    }

    public void setTargetType(Long targetType) {
        this.targetType = targetType;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(String maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
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
