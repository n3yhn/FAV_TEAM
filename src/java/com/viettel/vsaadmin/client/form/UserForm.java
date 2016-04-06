/* 
 * Copyright (C) 2010 Viettel Telecom. All rights reserved. 
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.vsaadmin.client.form;

import com.viettel.database.BO.BasicBO;
import com.viettel.vsaadmin.database.BO.Users;
import java.util.Date;

/** 
 * @author 
 * @since Mon Jan 24 11:56:28 ICT 2011
 * @version 1.0 
 */
public class UserForm extends BasicBO {

    private Long deptId;
    private String lastLoginFailure;
    private String deptName;
    private String staffCode;
    private Long status;
    private String lastLogin;
    private Date issueDateIdent;
    private String lastBlockDate;
    private String email;
    private String password;
    private String fax;
    private String aliasName;
    private String fullName;
    private Long profileId;
    private Long userId;
    private String description;
    private String deptLevel;
    private String ip;
    private Long passwordchanged;
    private Long posId;
    private String lastChangePassword;
    private String cellphone;
    private String lastResetPassword;
    private String telephone;
    private Long userTypeId;
    private Long managerId;
    private String issuePlacePassport;
    private Long locationId;
    private String identityCard;
    private String passportNumber;
    private Date dateOfBirth;
    private Long gender;
    private String userName;
    private Date issueDatePassport;
    private Date createDate;
    private Long userRight;
    private Long loginFailureCount;
    private String birthPlace;
    private String issuePlaceIdent;
    private Long userType;

    public UserForm() {
    }

    public UserForm(Users entities) {
        if (entities == null) {
            return;
        }
        deptId = entities.getDeptId();
//        lastLoginFailure = entities.getLastLoginFailure();
        deptName = entities.getDeptName();
        staffCode = entities.getStaffCode();
        status = entities.getStatus();
//        lastLogin = entities.getLastLogin();
        issueDateIdent = entities.getIssueDateIdent();
//        lastBlockDate = entities.getLastBlockDate();
        email = entities.getEmail();
        password = entities.getPassword();
        fax = entities.getFax();
        aliasName = entities.getAliasName();
        fullName = entities.getFullName();
        profileId = entities.getProfileId();
        userId = entities.getUserId();
        description = entities.getDescription();
        deptLevel = entities.getDeptLevel();
        ip = entities.getIp();
//        passwordchanged = entities.getPasswordchanged();
        posId = entities.getPosId();
//        lastChangePassword = entities.getLastChangePassword();
        cellphone = entities.getCellphone();
//        lastResetPassword = entities.getLastResetPassword();
        telephone = entities.getTelephone();
        userTypeId = entities.getUserTypeId();
        managerId = entities.getManagerId();
        issuePlacePassport = entities.getIssuePlacePassport();
        locationId = entities.getLocationId();
        identityCard = entities.getIdentityCard();
        passportNumber = entities.getPassportNumber();
        dateOfBirth = entities.getDateOfBirth();
        gender = entities.getGender();
        userName = entities.getUserName();
        issueDatePassport = entities.getIssueDatePassport();
        createDate = entities.getCreateDate();
        userRight = entities.getUserRight();
        loginFailureCount = entities.getLoginFailureCount();
        birthPlace = entities.getBirthPlace();
        issuePlaceIdent = entities.getIssuePlaceIdent();
    }

    public Users convertToEntity() {
        Users entities = new Users();
        entities.setDeptId(deptId);
//        entities.setLastLoginFailure(lastLoginFailure);
        entities.setDeptName(deptName);
        entities.setStaffCode(staffCode);
        entities.setStatus(status);
//        entities.setLastLogin(lastLogin);
        entities.setIssueDateIdent(issueDateIdent);
//        entities.setLastBlockDate(lastBlockDate);
        entities.setEmail(email);
        entities.setPassword(password);
        entities.setFax(fax);
        entities.setAliasName(aliasName);
        entities.setFullName(fullName);
        entities.setProfileId(profileId);
        entities.setUserId(userId);
        entities.setDescription(description);
        entities.setDeptLevel(deptLevel);
        entities.setIp(ip);
//        entities.setPasswordchanged(passwordchanged);
        entities.setPosId(posId);
//        entities.setLastChangePassword(lastChangePassword);
        entities.setCellphone(cellphone);
//        entities.setLastResetPassword(lastResetPassword);
        entities.setTelephone(telephone);
        entities.setUserTypeId(userTypeId);
        entities.setManagerId(managerId);
        entities.setIssuePlacePassport(issuePlacePassport);
        entities.setLocationId(locationId);
        entities.setIdentityCard(identityCard);
        entities.setPassportNumber(passportNumber);
        entities.setDateOfBirth(dateOfBirth);
        entities.setGender(gender);
        entities.setUserName(userName);
        entities.setIssueDatePassport(issueDatePassport);
        entities.setCreateDate(createDate);
        entities.setUserRight(userRight);
        entities.setLoginFailureCount(loginFailureCount);
        entities.setBirthPlace(birthPlace);
        entities.setIssuePlaceIdent(issuePlaceIdent);
        return entities;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getLastLoginFailure() {
        return lastLoginFailure;
    }

    public void setLastLoginFailure(String lastLoginFailure) {
        this.lastLoginFailure = lastLoginFailure;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getIssueDateIdent() {
        return issueDateIdent;
    }

    public void setIssueDateIdent(Date issueDateIdent) {
        this.issueDateIdent = issueDateIdent;
    }

    public String getLastBlockDate() {
        return lastBlockDate;
    }

    public void setLastBlockDate(String lastBlockDate) {
        this.lastBlockDate = lastBlockDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getPasswordchanged() {
        return passwordchanged;
    }

    public void setPasswordchanged(Long passwordchanged) {
        this.passwordchanged = passwordchanged;
    }

    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    public String getLastChangePassword() {
        return lastChangePassword;
    }

    public void setLastChangePassword(String lastChangePassword) {
        this.lastChangePassword = lastChangePassword;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getLastResetPassword() {
        return lastResetPassword;
    }

    public void setLastResetPassword(String lastResetPassword) {
        this.lastResetPassword = lastResetPassword;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getIssuePlacePassport() {
        return issuePlacePassport;
    }

    public void setIssuePlacePassport(String issuePlacePassport) {
        this.issuePlacePassport = issuePlacePassport;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getIssueDatePassport() {
        return issueDatePassport;
    }

    public void setIssueDatePassport(Date issueDatePassport) {
        this.issueDatePassport = issueDatePassport;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUserRight() {
        return userRight;
    }

    public void setUserRight(Long userRight) {
        this.userRight = userRight;
    }

    public Long getLoginFailureCount() {
        return loginFailureCount;
    }

    public void setLoginFailureCount(Long loginFailureCount) {
        this.loginFailureCount = loginFailureCount;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getIssuePlaceIdent() {
        return issuePlaceIdent;
    }

    public void setIssuePlaceIdent(String issuePlaceIdent) {
        this.issuePlaceIdent = issuePlaceIdent;
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }
    
    
}
