/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.BO;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vtit_binhnt53
 */
@Entity
@Table(name = "CATEGORY_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoryType.findAll", query = "SELECT c FROM CategoryType c"),
    @NamedQuery(name = "CategoryType.findByCategoryTypeId", query = "SELECT c FROM CategoryType c WHERE c.categoryTypeId = :categoryTypeId"),
    @NamedQuery(name = "CategoryType.findByCategoryType", query = "SELECT c FROM CategoryType c WHERE c.categoryType = :categoryType"),
    @NamedQuery(name = "CategoryType.findByCategoryName", query = "SELECT c FROM CategoryType c WHERE c.categoryName = :categoryName"),
    @NamedQuery(name = "CategoryType.findByDesciption", query = "SELECT c FROM CategoryType c WHERE c.desciption = :desciption"),
    @NamedQuery(name = "CategoryType.findByIsActive", query = "SELECT c FROM CategoryType c WHERE c.isActive = :isActive")})
public class CategoryType implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "CATEGORY_TYPE_SEQ", sequenceName = "CATEGORY_TYPE_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_TYPE_SEQ")
    @Basic(optional = false)
    @Column(name = "CATEGORY_TYPE_ID")
    private Long categoryTypeId;
    @Column(name = "CATEGORY_TYPE")
    private String categoryType;
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    @Column(name = "DESCIPTION")
    private String desciption;
    @Column(name = "IS_ACTIVE")
    private Long isActive;

    public CategoryType() {
    }

    public CategoryType(Long categoryTypeId, String categoryName) {
        this.categoryTypeId = categoryTypeId;
        this.categoryName = categoryName;
    }

    public CategoryType(String categoryType, String categoryName) {
        this.categoryType = categoryType;
        this.categoryName = categoryName;
    }

    public CategoryType(Long categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }

    public Long getCategoryTypeId() {
        return categoryTypeId;
    }

    public void setCategoryTypeId(Long categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryTypeId != null ? categoryTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoryType)) {
            return false;
        }
        CategoryType other = (CategoryType) object;
        if ((this.categoryTypeId == null && other.categoryTypeId != null) || (this.categoryTypeId != null && !this.categoryTypeId.equals(other.categoryTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.CategoryType[ categoryTypeId=" + categoryTypeId + " ]";
    }
}
