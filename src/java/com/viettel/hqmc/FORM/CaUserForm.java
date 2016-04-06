/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.CaUser;

/**
 *
 * @author BINHNT53
 */
public class CaUserForm {

    private Long caUserId;
    private String caSerial;
    private String userName;
    private String contentSigned;

    public String getContentSigned() {
        return contentSigned;
    }

    public void setContentSigned(String contentSigned) {
        this.contentSigned = contentSigned;
    }

    public CaUserForm() {
    }

    public CaUserForm(Long caUserId, String caSerial, String userName, String contentSigned) {
        this.caUserId = caUserId;
        this.caSerial = caSerial;
        this.userName = userName;
        this.contentSigned = contentSigned;
    }

    public CaUserForm(CaUser entity) {
        caSerial = entity.getCaSerial();
        caUserId = entity.getCaUserId();
        userName = entity.getUserName();
    }

    public Long getCaUserId() {
        return caUserId;
    }

    public void setCaUserId(Long caUserId) {
        this.caUserId = caUserId;
    }

    public String getCaSerial() {
        return caSerial;
    }

    public void setCaSerial(String caSerial) {
        this.caSerial = caSerial;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
