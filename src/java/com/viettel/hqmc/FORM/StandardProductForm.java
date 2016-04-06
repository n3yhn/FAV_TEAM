/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.StandardProduct;
import com.viettel.hqmc.BO.VietnameseStandard;
import java.io.Serializable;

/**
 *
 * @author vtit_binhnt53
 */
public class StandardProductForm implements Serializable {
    private Long vietnameseStandardId;
    private String categoryName;
    private VietnameseStandard vietnameseStandard;
    
    public StandardProductForm(StandardProduct entity) {
        if (entity == null) {
            return;
        } else {
            this.categoryName = entity.getCategoryName();
            this.vietnameseStandard = entity.getVietnameseStandard();
            this.vietnameseStandardId = entity.getVietnameseStandardId();
        }
        
    }

    public StandardProduct convertToEntity() {
        StandardProduct item = new StandardProduct();
        item.setCategoryName(this.categoryName);
        item.setVietnameseStandard(this.vietnameseStandard);
        item.setVietnameseStandardId(this.vietnameseStandardId);

        return item;
    }
    
    public Long getVietnameseStandardId() {
        return vietnameseStandardId;
    }

    public void setVietnameseStandardId(Long vietnameseStandardId) {
        this.vietnameseStandardId = vietnameseStandardId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public VietnameseStandard getVietnameseStandard() {
        return vietnameseStandard;
    }

    public void setVietnameseStandard(VietnameseStandard vietnameseStandard) {
        this.vietnameseStandard = vietnameseStandard;
    }
    
    
}
