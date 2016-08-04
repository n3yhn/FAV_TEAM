/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.CaUser;
import java.util.Date;

/**
 *
 * @author BINHNT53
 */
public class CaUserForm {

    private Long caUserId;
    private String caSerial;
    private String userName;
    private String contentSigned;
    private Date createdAt;
    private Date updatedAt;
    private String name;
    private String position;
    private String command;
    private String stamper;
    private String signature;
    private Long uploadId;

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

    public CaUserForm(Long caUserId, String caSerial, String userName, String contentSigned, Date createdAt, Date updatedAt, String name, String position, String command, String stamper, String signature) {
        this.caUserId = caUserId;
        this.caSerial = caSerial;
        this.userName = userName;
        this.contentSigned = contentSigned;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.position = position;
        this.command = command;
        this.stamper = stamper;
        this.signature = signature;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getStamper() {
        return stamper;
    }

    public void setStamper(String stamper) {
        this.stamper = stamper;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getUploadId() {
        return uploadId;
    }

    public void setUploadId(Long uploadId) {
        this.uploadId = uploadId;
    }
//add up uploadId
    public CaUserForm(Long caUserId, String caSerial, String userName, String contentSigned, Date createdAt, Date updatedAt, String name, String position, String command, String stamper, String signature, Long uploadId) {
        this.caUserId = caUserId;
        this.caSerial = caSerial;
        this.userName = userName;
        this.contentSigned = contentSigned;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.position = position;
        this.command = command;
        this.stamper = stamper;
        this.signature = signature;
        this.uploadId = uploadId;
    }
}
