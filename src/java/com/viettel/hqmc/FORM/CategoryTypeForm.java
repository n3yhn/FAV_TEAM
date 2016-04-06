/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.CategoryType;
import java.io.Serializable;

/**
 *
 * @author vtit_binhnt53
 */
public class CategoryTypeForm implements Serializable {

    private Long categoryTypeId;
    private String categoryType;
    private String categoryName;
    private String desciption;
    private Long isActive;

    public CategoryTypeForm(){
        
    }
    
    public CategoryTypeForm(CategoryType entity) {
        if (entity == null) {
            return;
        } else {
            this.categoryName = entity.getCategoryName();
            this.categoryType = entity.getCategoryType();
            this.categoryTypeId = entity.getCategoryTypeId();
            this.desciption = entity.getDesciption();
            this.isActive = entity.getIsActive();
        }

    }

    public CategoryType convertToEntity() {
        CategoryType item = new CategoryType();
        item.setCategoryName(this.categoryName);
        item.setCategoryType(this.categoryType);
        item.setCategoryTypeId(this.categoryTypeId);
        item.setDesciption(this.desciption);
        item.setIsActive(this.isActive);

        return item;
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
}
