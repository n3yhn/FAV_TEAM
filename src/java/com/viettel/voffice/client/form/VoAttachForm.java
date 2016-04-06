/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author DiuLTT
 */
public class VoAttachForm implements Serializable {

    private Long attachId;
    private Long objectId;
    private Long objectType;
    private String attachName;
    private String attachPath;
    private Long isActive;
    private Long isOldData;
    private String attachDes;
    private Long userCreateId;
    private Date createDate;
    private String attachLinks;
    private String userName;
    private Long deptId;
    private String deptName;
    private String versions;
    private Long type;
    private String attachIds;
    private Long attachIdShs;
    private Long isTemp;
    private Long originalId;

    public String getAttachDes() {
        return attachDes;
    }

    public void setAttachDes(String attachDes) {
        this.attachDes = attachDes;
    }

    public Long getAttachId() {
        return attachId;
    }

    public void setAttachId(Long attachId) {
        this.attachId = attachId;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getIsOldData() {
        return isOldData;
    }

    public void setIsOldData(Long isOldData) {
        this.isOldData = isOldData;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getObjectType() {
        return objectType;
    }

    public void setObjectType(Long objectType) {
        this.objectType = objectType;
    }

    public Long getUserCreateId() {
        return userCreateId;
    }

    public void setUserCreateId(Long userCreateId) {
        this.userCreateId = userCreateId;
    }

    public String getAttachLinks() {
        return attachLinks;
    }

    public void setAttachLinks(String attachLinks) {
        this.attachLinks = attachLinks;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAttachIds() {
        return attachIds;
    }

    public void setAttachIds(String attachIds) {
        this.attachIds = attachIds;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }

    public Long getAttachIdShs() {
        return attachIdShs;
    }

    public void setAttachIdShs(Long attachIdShs) {
        this.attachIdShs = attachIdShs;
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

}
