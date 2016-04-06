/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.hqmc.FORM;

/**
 *
 * @author Hai
 */
public class RepositoriesForm {
    private Long repId;
    private String repName;
    private Long repCreator;
    private String repAddress;
    private Long isActive;

    public RepositoriesForm() {
    }

    public Long getRepId() {
        return repId;
    }

    public void setRepId(Long repId) {
        this.repId = repId;
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public Long getRepCreator() {
        return repCreator;
    }

    public void setRepCreator(Long repCreator) {
        this.repCreator = repCreator;
    }

    public String getRepAddress() {
        return repAddress;
    }

    public void setRepAddress(String repAddress) {
        this.repAddress = repAddress;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }
    
}
