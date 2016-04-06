/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.form;

import com.viettel.voffice.database.BO.Category;
import java.util.Date;

/**
 *
 * @author vtit_binhnt53
 */
public class CategoryForm {
    private Long categoryId;
    private String type;
    private String name;
    private String code;
    private String isActive;
    private Long createdBy;
    private Date createDate;
    private Long modifiedBy;
    private Date modifyDate;
    private String description;

    public CategoryForm() {
    }
    public CategoryForm(Category entity) {
        if (entity == null) {
            return;
        } else {
            this.categoryId = entity.getCategoryId();
            this.code = entity.getCode();
            this.createDate = entity.getCreateDate();
            this.createdBy = entity.getCreatedBy();
            this.description = entity.getDescription();
            this.isActive = entity.getIsActive();
            this.modifiedBy = entity.getModifiedBy();
            this.modifyDate = entity.getModifyDate();
            this.name = entity.getName();
            this.type = entity.getType();
        }

    }

    public Category convertToEntity() {
        Category item = new Category();
        item.setCategoryId(this.categoryId);
        item.setCode(this.code);
        item.setCreateDate(this.createDate);
        item.setCreatedBy(this.createdBy);
        item.setDescription(this.description);
        item.setIsActive(this.isActive);
        item.setModifiedBy(this.modifiedBy);
        item.setModifyDate(this.modifyDate);
        item.setName(this.name);
        item.setType(this.type);

        return item;
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
    
}
