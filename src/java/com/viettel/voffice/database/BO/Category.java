/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.BO;

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
 * @author vtit_binhnt53
 */
@Entity
@Table(name = "CATEGORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findByCategoryId", query = "SELECT c FROM Category c WHERE c.categoryId = :categoryId"),
    @NamedQuery(name = "Category.findByType", query = "SELECT c FROM Category c WHERE c.type = :type"),
    @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name"),
    @NamedQuery(name = "Category.findByCode", query = "SELECT c FROM Category c WHERE c.code = :code"),
    @NamedQuery(name = "Category.findByIsActive", query = "SELECT c FROM Category c WHERE c.isActive = :isActive"),
    @NamedQuery(name = "Category.findByCreatedBy", query = "SELECT c FROM Category c WHERE c.createdBy = :createdBy"),
    @NamedQuery(name = "Category.findByCreateDate", query = "SELECT c FROM Category c WHERE c.createDate = :createDate"),
    @NamedQuery(name = "Category.findByModifiedBy", query = "SELECT c FROM Category c WHERE c.modifiedBy = :modifiedBy"),
    @NamedQuery(name = "Category.findByModifyDate", query = "SELECT c FROM Category c WHERE c.modifyDate = :modifyDate"),
    @NamedQuery(name = "Category.findByDescription", query = "SELECT c FROM Category c WHERE c.description = :description")})
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "CATEGORY_SEQ", sequenceName = "CATEGORY_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ")
    @Basic(optional = false)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "CODE")
    private String code;
    @Column(name = "IS_ACTIVE")
    private String isActive;
    @Column(name = "CREATED_BY")
    private Long createdBy;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;
    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;
    @Column(name = "DESCRIPTION")
    private String description;

    public Category() {
    }

    public Category(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Category(Long categoryId, String type, String name, String code) {
        this.categoryId = categoryId;
        this.type = type;
        this.name = name;
        this.code = code;
    }
    public Category(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Category(Long categoryId, String type, String name, String code, String description) {
        this.categoryId = categoryId;
        this.type = type;
        this.name = name;
        this.code = code;
        this.description = description;
    }
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryId != null ? categoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.voffice.database.BO.Category[ categoryId=" + categoryId + " ]";
    }
    
}
