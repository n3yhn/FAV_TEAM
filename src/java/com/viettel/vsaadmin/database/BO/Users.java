/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.vsaadmin.database.BO;

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

/**
 *
 * @author sytv
 */
@Entity
@Table(name = "USERS")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByUserRight", query = "SELECT u FROM Users u WHERE u.userRight = :userRight"),
    @NamedQuery(name = "Users.findByUserName", query = "SELECT u FROM Users u WHERE u.userName = :userName"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByStatus", query = "SELECT u FROM Users u WHERE u.status = :status"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByCellphone", query = "SELECT u FROM Users u WHERE u.cellphone = :cellphone"),
    @NamedQuery(name = "Users.findByTelephone", query = "SELECT u FROM Users u WHERE u.telephone = :telephone"),
    @NamedQuery(name = "Users.findByFax", query = "SELECT u FROM Users u WHERE u.fax = :fax"),
    @NamedQuery(name = "Users.findByGender", query = "SELECT u FROM Users u WHERE u.gender = :gender"),
    @NamedQuery(name = "Users.findByDateOfBirth", query = "SELECT u FROM Users u WHERE u.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Users.findByLastChangePassword", query = "SELECT u FROM Users u WHERE u.lastChangePassword = :lastChangePassword"),
    @NamedQuery(name = "Users.findByLastBlockDate", query = "SELECT u FROM Users u WHERE u.lastBlockDate = :lastBlockDate"),
    @NamedQuery(name = "Users.findByLoginFailureCount", query = "SELECT u FROM Users u WHERE u.loginFailureCount = :loginFailureCount"),
    @NamedQuery(name = "Users.findByLastLoginFailure", query = "SELECT u FROM Users u WHERE u.lastLoginFailure = :lastLoginFailure"),
    @NamedQuery(name = "Users.findByAliasName", query = "SELECT u FROM Users u WHERE u.aliasName = :aliasName"),
    @NamedQuery(name = "Users.findByBirthPlace", query = "SELECT u FROM Users u WHERE u.birthPlace = :birthPlace"),
    @NamedQuery(name = "Users.findByIdentityCard", query = "SELECT u FROM Users u WHERE u.identityCard = :identityCard"),
    @NamedQuery(name = "Users.findByIssuePlaceIdent", query = "SELECT u FROM Users u WHERE u.issuePlaceIdent = :issuePlaceIdent"),
    @NamedQuery(name = "Users.findByIssueDateIdent", query = "SELECT u FROM Users u WHERE u.issueDateIdent = :issueDateIdent"),
    @NamedQuery(name = "Users.findByPassportNumber", query = "SELECT u FROM Users u WHERE u.passportNumber = :passportNumber"),
    @NamedQuery(name = "Users.findByIssuePlacePassport", query = "SELECT u FROM Users u WHERE u.issuePlacePassport = :issuePlacePassport"),
    @NamedQuery(name = "Users.findByIssueDatePassport", query = "SELECT u FROM Users u WHERE u.issueDatePassport = :issueDatePassport"),
    @NamedQuery(name = "Users.findByFullName", query = "SELECT u FROM Users u WHERE u.fullName = :fullName"),
    @NamedQuery(name = "Users.findByUserTypeId", query = "SELECT u FROM Users u WHERE u.userTypeId = :userTypeId"),
    @NamedQuery(name = "Users.findByCreateDate", query = "SELECT u FROM Users u WHERE u.createDate = :createDate"),
    @NamedQuery(name = "Users.findByDescription", query = "SELECT u FROM Users u WHERE u.description = :description"),
    @NamedQuery(name = "Users.findByStaffCode", query = "SELECT u FROM Users u WHERE u.staffCode = :staffCode"),
    @NamedQuery(name = "Users.findByManagerId", query = "SELECT u FROM Users u WHERE u.managerId = :managerId"),
    @NamedQuery(name = "Users.findByLocationId", query = "SELECT u FROM Users u WHERE u.locationId = :locationId"),
    @NamedQuery(name = "Users.findByPasswordchanged", query = "SELECT u FROM Users u WHERE u.passwordchanged = :passwordchanged"),
    @NamedQuery(name = "Users.findByLastLogin", query = "SELECT u FROM Users u WHERE u.lastLogin = :lastLogin"),
    @NamedQuery(name = "Users.findByProfileId", query = "SELECT u FROM Users u WHERE u.profileId = :profileId"),
    @NamedQuery(name = "Users.findByLastResetPassword", query = "SELECT u FROM Users u WHERE u.lastResetPassword = :lastResetPassword"),
    @NamedQuery(name = "Users.findByIp", query = "SELECT u FROM Users u WHERE u.ip = :ip"),
    @NamedQuery(name = "Users.findByDeptId", query = "SELECT u FROM Users u WHERE u.deptId = :deptId"),
    @NamedQuery(name = "Users.findByDeptLevel", query = "SELECT u FROM Users u WHERE u.deptLevel = :deptLevel"),
    @NamedQuery(name = "Users.findByPosId", query = "SELECT u FROM Users u WHERE u.posId = :posId"),
    @NamedQuery(name = "Users.findByDeptName", query = "SELECT u FROM Users u WHERE u.deptName = :deptName"),
    @NamedQuery(name = "Users.findByDeptRepresentId", query = "SELECT u FROM Users u WHERE u.deptRepresentId = :deptRepresentId"),
    @NamedQuery(name = "Users.findByDeptRepresentName", query = "SELECT u FROM Users u WHERE u.deptRepresentName = :deptRepresentName")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "USER_ID_SEQ", sequenceName = "USER_ID_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Long userId;
    @Basic(optional = false)
    @Column(name = "USER_NAME")
    private String userName;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CELLPHONE")
    private String cellphone;
    @Column(name = "TELEPHONE")
    private String telephone;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "LAST_CHANGE_PASSWORD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChangePassword;
    @Column(name = "LAST_BLOCK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastBlockDate;
    @Column(name = "LAST_LOGIN_FAILURE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginFailure;
    @Column(name = "ALIAS_NAME")
    private String aliasName;
    @Column(name = "BIRTH_PLACE")
    private String birthPlace;
    @Column(name = "IDENTITY_CARD")
    private String identityCard;
    @Column(name = "ISSUE_PLACE_IDENT")
    private String issuePlaceIdent;
    @Column(name = "ISSUE_DATE_IDENT")
    @Temporal(TemporalType.DATE)
    private Date issueDateIdent;
    @Column(name = "PASSPORT_NUMBER")
    private String passportNumber;
    @Column(name = "ISSUE_PLACE_PASSPORT")
    private String issuePlacePassport;
    @Column(name = "ISSUE_DATE_PASSPORT")
    @Temporal(TemporalType.DATE)
    private Date issueDatePassport;
    @Basic(optional = false)
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "USER_TYPE_ID")
    private Long userTypeId;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "STAFF_CODE")
    private String staffCode;
    @Column(name = "MANAGER_ID")
    private Long managerId;
    @Column(name = "LOCATION_ID")
    private Long locationId;
    @Column(name = "LAST_LOGIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "PROFILE_ID")
    private Long profileId;
    @Column(name = "LAST_RESET_PASSWORD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastResetPassword;
    @Column(name = "IP")
    private String ip;
    @Column(name = "DEPT_LEVEL")
    private String deptLevel;
    @Column(name = "POS_ID")
    private Long posId;
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "DEPT_REPRESENT_NAME")
    private String deptRepresentName;
    @Column(name = "BUSINESS_ID")
    private Long businessId;
    @Column(name = "BUSINESS_NAME")
    private String businessName;
    @Column(name = "USER_RIGHT")
    private Long userRight;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "GENDER")
    private Long gender;
    @Column(name = "LOGIN_FAILURE_COUNT")
    private Long loginFailureCount;
    @Basic(optional = false)
    @Column(name = "PASSWORDCHANGED")
    private Long passwordchanged;
    @Column(name = "DEPT_ID")
    private Long deptId;
    @Column(name = "CHECK_VALID_TIME")
    private Long checkValidTime;
    @Column(name = "VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;
    @Column(name = "VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTo;
    @Column(name = "START_TIME_TO_CHANGE_PASSWORD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTimeToChangePassword;
    @Column(name = "IP_LAN")
    private String ipLan;
    @Column(name = "LAST_UNLOCK")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUnlock;
    @Column(name = "CHECK_IP")
    private Long checkIp;
    @Column(name = "CHECK_IP_LAN")
    private Long checkIpLan;
    @Column(name = "LAST_LOCK")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLock;
    @Column(name = "MINISTRY_JUSTICE_ID")
    private Long ministryJusticeId;
    @Column(name = "DEPT_REPRESENT_ID")
    private Long deptRepresentId;
    @Column(name = "FORGOT_PASSWORD_EXPIRED")
    @Temporal(TemporalType.DATE)
    private Date forgotPasswordExpired;
    @Column(name = "FORGOT_PASSWORD")
    private String forgotPassword;

    public Users() {
    }

    public Users(Long userId) {
        this.userId = userId;
    }

    public Users(Long userId, String fullName) {
        this.userId = userId;
        this.fullName = fullName;
    }

    public Users(Long userId, String userName, String password, Long status, String fullName, Long passwordchanged) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.fullName = fullName;
        this.passwordchanged = passwordchanged;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserRight() {
        return userRight;
    }

    public void setUserRight(Long userRight) {
        this.userRight = userRight;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getLastChangePassword() {
        return lastChangePassword;
    }

    public void setLastChangePassword(Date lastChangePassword) {
        this.lastChangePassword = lastChangePassword;
    }

    public Date getLastBlockDate() {
        return lastBlockDate;
    }

    public void setLastBlockDate(Date lastBlockDate) {
        this.lastBlockDate = lastBlockDate;
    }

    public Long getLoginFailureCount() {
        return loginFailureCount;
    }

    public void setLoginFailureCount(Long loginFailureCount) {
        this.loginFailureCount = loginFailureCount;
    }

    public Date getLastLoginFailure() {
        return lastLoginFailure;
    }

    public void setLastLoginFailure(Date lastLoginFailure) {
        this.lastLoginFailure = lastLoginFailure;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getIssuePlaceIdent() {
        return issuePlaceIdent;
    }

    public void setIssuePlaceIdent(String issuePlaceIdent) {
        this.issuePlaceIdent = issuePlaceIdent;
    }

    public Date getIssueDateIdent() {
        return issueDateIdent;
    }

    public void setIssueDateIdent(Date issueDateIdent) {
        this.issueDateIdent = issueDateIdent;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getIssuePlacePassport() {
        return issuePlacePassport;
    }

    public void setIssuePlacePassport(String issuePlacePassport) {
        this.issuePlacePassport = issuePlacePassport;
    }

    public Date getIssueDatePassport() {
        return issueDatePassport;
    }

    public void setIssueDatePassport(Date issueDatePassport) {
        this.issueDatePassport = issueDatePassport;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Date getLastResetPassword() {
        return lastResetPassword;
    }

    public void setLastResetPassword(Date lastResetPassword) {
        this.lastResetPassword = lastResetPassword;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getDeptRepresentId() {
        return deptRepresentId;
    }

    public void setDeptRepresentId(Long deptRepresentId) {
        this.deptRepresentId = deptRepresentId;
    }

    public String getDeptRepresentName() {
        return deptRepresentName;
    }

    public void setDeptRepresentName(String deptRepresentName) {
        this.deptRepresentName = deptRepresentName;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.vsaadmin.database.BO.Users[userId=" + userId + "]";
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public Long getPasswordchanged() {
        return passwordchanged;
    }

    public void setPasswordchanged(Long passwordchanged) {
        this.passwordchanged = passwordchanged;
    }

    public Long getCheckValidTime() {
        return checkValidTime;
    }

    public void setCheckValidTime(Long checkValidTime) {
        this.checkValidTime = checkValidTime;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public Date getStartTimeToChangePassword() {
        return startTimeToChangePassword;
    }

    public void setStartTimeToChangePassword(Date startTimeToChangePassword) {
        this.startTimeToChangePassword = startTimeToChangePassword;
    }

    public String getIpLan() {
        return ipLan;
    }

    public void setIpLan(String ipLan) {
        this.ipLan = ipLan;
    }

    public Date getLastUnlock() {
        return lastUnlock;
    }

    public void setLastUnlock(Date lastUnlock) {
        this.lastUnlock = lastUnlock;
    }

    public Long getCheckIp() {
        return checkIp;
    }

    public void setCheckIp(Long checkIp) {
        this.checkIp = checkIp;
    }

    public Long getCheckIpLan() {
        return checkIpLan;
    }

    public void setCheckIpLan(Long checkIpLan) {
        this.checkIpLan = checkIpLan;
    }

    public Date getLastLock() {
        return lastLock;
    }

    public void setLastLock(Date lastLock) {
        this.lastLock = lastLock;
    }

    public Long getMinistryJusticeId() {
        return ministryJusticeId;
    }

    public void setMinistryJusticeId(Long ministryJusticeId) {
        this.ministryJusticeId = ministryJusticeId;
    }

    public Date getForgotPasswordExpired() {
        return forgotPasswordExpired;
    }

    public void setForgotPasswordExpired(Date forgotPasswordExpired) {
        this.forgotPasswordExpired = forgotPasswordExpired;
    }

    public String getForgotPassword() {
        return forgotPassword;
    }

    public void setForgotPassword(String forgotPassword) {
        this.forgotPassword = forgotPassword;
    }
}
