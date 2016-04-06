package com.viettel.vsaadmin.client.form;

import com.viettel.hqmc.FORM.BusinessForm;
import java.util.Date;

/*     */
/*     */ public class UsersForm /*     */ {
    /*     */ private String aliasName;
    /*     */ private String birthPlace;
    /*     */ private String cellphone;
    /*     */ private String createDate;
    /*     */ private String dateOfBirth;
    /*     */ private String description;
    /*     */ private String email;
    /*     */ private String fax;
    /*     */ private String fullName;
    /*     */ private String gender;
    /*     */ private String identityCard;
    /*     */ private String issueDateIdent;
    /*     */ private String issueDatePassport;
    /*     */ private String issuePlaceIdent;
    /*     */ private String issuePlacePassport;
    /*     */ private String lastBlockDate;
    /*     */ private Date lastChangePassword;
    /*     */ private String locationId;
    /*     */ private String loginFailureCount;
    /*     */ private String lastLoginFailure;
    /*     */ private String managerId;
    /*     */ private String passportNumber;
    /*     */ private String password;
    /*     */ private String staffCode;
    /*     */ private String status;
    /*     */ private String telephone;
    /*     */ private Long userTypeId;
    /*     */ private String userName;
    /*     */ private String userId;
    /*     */ private String userRight;
    /*     */ private Long deptId;
    /*     */ private Long deptRepresentId;
    /*     */ private String isCheck;
    /*     */ private String roleCode;
    /*     */ private String profileId;
    /*     */ private String ip;
    /*     */ private Long posId;
    /*     */ private String deptName;
    /*     */ private String deptCode;
    /*     */ private String posCode;
    /*     */ private String ignoreCheckIp;
    /*     */ private String deptLevel;
    private Long staffId;
    private String staffName;
    private String oldPassword;
    private String emailPassword;
    private String deptIdStr;
    private String deptRepresentIdStr;
    private String deptRepresentName;
    private String posIdStr;
    private Date dateOfBirthDate;
    private Date issueDateIdentDate;
    private Date lastLogin;
    private String deptParentName;
    private Date lastResetPassword;
    private String businessName;
    private Long businessId;
    private BusinessForm businessForm;
    private Long userType;
    public String getDeptParentName() {
        return deptParentName;
    }

    public void setDeptParentName(String deptParentName) {
        this.deptParentName = deptParentName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /*     */
    /*     */ public String getIgnoreCheckIp() /*     */ {
        /*  48 */ return this.ignoreCheckIp;
        /*     */    }
    /*     */
    /*     */ public void setIgnoreCheckIp(String ignoreCheckIp) {
        /*  52 */ this.ignoreCheckIp = ignoreCheckIp;
        /*     */    }
    /*     */
    /*     */ public String getDeptCode() {
        /*  56 */ return this.deptCode;
        /*     */    }
    /*     */
    /*     */ public void setDeptCode(String deptCode) {
        /*  60 */ this.deptCode = deptCode;
        /*     */    }
    /*     */
    /*     */ public String getPosCode() {
        /*  64 */ return this.posCode;
        /*     */    }
    /*     */
    /*     */ public void setPosCode(String posCode) {
        /*  68 */ this.posCode = posCode;
        /*     */    }
    /*     */
    /*     */ public String getDeptName() {
        /*  72 */ return this.deptName;
        /*     */    }
    /*     */
    /*     */ public void setDeptName(String deptName) {
        /*  76 */ this.deptName = deptName;
        /*     */    }
    /*     */
    /*     */ public Long getPosId() {
        /*  80 */ return this.posId;
        /*     */    }
    /*     */
    /*     */ public String getDeptLevel() /*     */ {
        /*  85 */ return this.deptLevel;
        /*     */    }
    /*     */
    /*     */ public void setDeptLevel(String deptLevel) {
        /*  89 */ this.deptLevel = deptLevel;
        /*     */    }
    /*     */
    /*     */ public void setPosId(Long posId) {
        /*  93 */ this.posId = posId;
        /*     */    }
    /*     */
    /*     */ public String getIp() {
        /*  97 */ return this.ip;
        /*     */    }
    /*     */
    /*     */ public void setIp(String ip) {
        /* 101 */ this.ip = ip;
        /*     */    }
    /*     */
    /*     */ public UsersForm() {
        /* 105 */ this.aliasName = "";
        /* 106 */ this.birthPlace = "";
        /* 107 */ this.cellphone = "";
        /* 108 */ this.createDate = "";
        /* 109 */ this.dateOfBirth = "";
        /* 110 */ this.description = "";
        /* 111 */ this.email = "";
        /* 112 */ this.fax = "";
        /* 113 */ this.fullName = "";
        /* 114 */ this.gender = "";
        /* 115 */ this.identityCard = "";
        /* 116 */ this.issueDateIdent = "";
        /* 117 */ this.issueDatePassport = "";
        /* 118 */ this.issuePlaceIdent = "";
        /* 119 */ this.issuePlacePassport = "";
        /* 120 */ this.lastBlockDate = "";
        /* 122 */ this.locationId = "";
        /* 123 */ this.loginFailureCount = "";
        /* 124 */ this.lastLoginFailure = "";
        /* 125 */ this.managerId = "";
        /* 126 */ this.passportNumber = "";
        /* 127 */ this.password = "";
        /* 128 */ this.staffCode = "";
        /* 129 */ this.status = "";
        /* 130 */ this.telephone = "";
        /* 131 */ this.userTypeId = 0L;
        /* 132 */ this.userName = "";
        /* 133 */ this.userId = "";
        /* 134 */ this.userRight = "";
        /* 135 */ this.deptId = null;
        /* 136 */ this.isCheck = "";
        /* 137 */ this.roleCode = "";
        /* 138 */ this.profileId = "";
        /* 139 */ this.ip = "";
        this.oldPassword = "";
        /*     */    }
    /*     */
    /*     */ public String getProfileId() {
        /* 143 */ return this.profileId;
        /*     */    }
    /*     */
    /*     */ public void setProfileId(String profileId) {
        /* 147 */ this.profileId = profileId;
        /*     */    }
    /*     */
    /*     */ public String getRoleCode() {
        /* 151 */ return this.roleCode;
        /*     */    }
    /*     */
    /*     */ public void setRoleCode(String roleCode) {
        /* 155 */ this.roleCode = roleCode;
        /*     */    }
    /*     */
    /*     */ public String getIsCheck() {
        /* 159 */ return this.isCheck;
        /*     */    }
    /*     */
    /*     */ public void setIsCheck(String isCheck) {
        /* 163 */ this.isCheck = isCheck;
        /*     */    }
    /*     */
    /*     */ public Long getDeptId() {
        /* 167 */ return this.deptId;
        /*     */    }
    /*     */
    /*     */ public void setDeptId(Long deptId) {
        /* 171 */ this.deptId = deptId;
        /*     */    }
    /*     */
    /*     */ public String getAliasName() /*     */ {
        /* 176 */ return this.aliasName;
        /*     */    }
    /*     */
    /*     */ public void setAliasName(String aliasName) {
        /* 180 */ this.aliasName = aliasName;
        /*     */    }
    /*     */
    /*     */ public String getBirthPlace() {
        /* 184 */ return this.birthPlace;
        /*     */    }
    /*     */
    /*     */ public void setBirthPlace(String birthPlace) {
        /* 188 */ this.birthPlace = birthPlace;
        /*     */    }
    /*     */
    /*     */ public String getCellphone() {
        /* 192 */ return this.cellphone;
        /*     */    }
    /*     */
    /*     */ public void setCellphone(String cellphone) {
        /* 196 */ this.cellphone = cellphone;
        /*     */    }
    /*     */
    /*     */ public String getCreateDate() {
        /* 200 */ return this.createDate;
        /*     */    }
    /*     */
    /*     */ public void setCreateDate(String createDate) {
        /* 204 */ this.createDate = createDate;
        /*     */    }
    /*     */
    /*     */ public String getDateOfBirth() {
        /* 208 */ return this.dateOfBirth;
        /*     */    }
    /*     */
    /*     */ public void setDateOfBirth(String dateOfBirth) {
        /* 212 */ this.dateOfBirth = dateOfBirth;
        /*     */    }
    /*     */
    /*     */ public String getDescription() {
        /* 216 */ return this.description;
        /*     */    }
    /*     */
    /*     */ public void setDescription(String description) {
        /* 220 */ this.description = description;
        /*     */    }
    /*     */
    /*     */ public String getEmail() {
        /* 224 */ return this.email;
        /*     */    }
    /*     */
    /*     */ public void setEmail(String email) {
        /* 228 */ this.email = email;
        /*     */    }
    /*     */
    /*     */ public String getFax() {
        /* 232 */ return this.fax;
        /*     */    }
    /*     */
    /*     */ public void setFax(String fax) {
        /* 236 */ this.fax = fax;
        /*     */    }
    /*     */
    /*     */ public String getFullName() {
        /* 240 */ return this.fullName;
        /*     */    }
    /*     */
    /*     */ public void setFullName(String fullName) {
        /* 244 */ this.fullName = fullName;
        /*     */    }
    /*     */
    /*     */ public String getGender() {
        /* 248 */ return this.gender;
        /*     */    }
    /*     */
    /*     */ public void setGender(String gender) {
        /* 252 */ this.gender = gender;
        /*     */    }
    /*     */
    /*     */ public String getIdentityCard() {
        /* 256 */ return this.identityCard;
        /*     */    }
    /*     */
    /*     */ public void setIdentityCard(String identityCard) {
        /* 260 */ this.identityCard = identityCard;
        /*     */    }
    /*     */
    /*     */ public String getIssueDateIdent() {
        /* 264 */ return this.issueDateIdent;
        /*     */    }
    /*     */
    /*     */ public void setIssueDateIdent(String issueDateIdent) {
        /* 268 */ this.issueDateIdent = issueDateIdent;
        /*     */    }
    /*     */
    /*     */ public String getIssueDatePassport() {
        /* 272 */ return this.issueDatePassport;
        /*     */    }
    /*     */
    /*     */ public void setIssueDatePassport(String issueDatePassport) {
        /* 276 */ this.issueDatePassport = issueDatePassport;
        /*     */    }
    /*     */
    /*     */ public String getIssuePlaceIdent() {
        /* 280 */ return this.issuePlaceIdent;
        /*     */    }
    /*     */
    /*     */ public void setIssuePlaceIdent(String issuePlaceIdent) {
        /* 284 */ this.issuePlaceIdent = issuePlaceIdent;
        /*     */    }
    /*     */
    /*     */ public String getIssuePlacePassport() {
        /* 288 */ return this.issuePlacePassport;
        /*     */    }
    /*     */
    /*     */ public void setIssuePlacePassport(String issuePlacePassport) {
        /* 292 */ this.issuePlacePassport = issuePlacePassport;
        /*     */    }
    /*     */
    /*     */ public String getLastBlockDate() {
        /* 296 */ return this.lastBlockDate;
        /*     */    }
    /*     */
    /*     */ public void setLastBlockDate(String lastBlockDate) {
        /* 300 */ this.lastBlockDate = lastBlockDate;
        /*     */    }

    /*     */ public String getLocationId() {
        /* 312 */ return this.locationId;
        /*     */    }
    /*     */
    /*     */ public void setLocationId(String locationId) {
        /* 316 */ this.locationId = locationId;
        /*     */    }
    /*     */
    /*     */ public String getLoginFailureCount() {
        /* 320 */ return this.loginFailureCount;
        /*     */    }
    /*     */
    /*     */ public void setLoginFailureCount(String loginFailureCount) {
        /* 324 */ this.loginFailureCount = loginFailureCount;
        /*     */    }
    /*     */
    /*     */ public String getManagerId() {
        /* 328 */ return this.managerId;
        /*     */    }
    /*     */
    /*     */ public void setManagerId(String managerId) {
        /* 332 */ this.managerId = managerId;
        /*     */    }
    /*     */
    /*     */ public String getPassportNumber() {
        /* 336 */ return this.passportNumber;
        /*     */    }
    /*     */
    /*     */ public void setPassportNumber(String passportNumber) {
        /* 340 */ this.passportNumber = passportNumber;
        /*     */    }
    /*     */
    /*     */ public String getPassword() {
        /* 344 */ return this.password;
        /*     */    }
    /*     */
    /*     */ public void setPassword(String password) {
        /* 348 */ this.password = password;
        /*     */    }
    /*     */
    /*     */ public String getStaffCode() {
        /* 352 */ return this.staffCode;
        /*     */    }
    /*     */
    /*     */ public void setStaffCode(String staffCode) {
        /* 356 */ this.staffCode = staffCode;
        /*     */    }
    /*     */
    /*     */ public String getStatus() {
        /* 360 */ return this.status;
        /*     */    }
    /*     */
    /*     */ public void setStatus(String status) {
        /* 364 */ this.status = status;
        /*     */    }
    /*     */
    /*     */ public String getTelephone() {
        /* 368 */ return this.telephone;
        /*     */    }
    /*     */
    /*     */ public void setTelephone(String telephone) {
        /* 372 */ this.telephone = telephone;
        /*     */    }
    /*     */
    /*     */ public String getUserId() {
        /* 376 */ return this.userId;
        /*     */    }
    /*     */
    /*     */ public void setUserId(String userId) {
        /* 380 */ this.userId = userId;
        /*     */    }
    /*     */
    /*     */ public String getUserName() {
        /* 384 */ return this.userName;
        /*     */    }
    /*     */
    /*     */ public void setUserName(String userName) {
        /* 388 */ this.userName = userName;
        /*     */    }
    /*     */
    /*     */ public String getLastLoginFailure() {
        /* 392 */ return this.lastLoginFailure;
        /*     */    }
    /*     */
    /*     */ public void setLastLoginFailure(String lastLoginFailure) {
        /* 396 */ this.lastLoginFailure = lastLoginFailure;
        /*     */    }
    /*     */
    /*     */ public String getUserRight() {
        /* 400 */ return this.userRight;
        /*     */    }
    /*     */
    /*     */ public void setUserRight(String userRight) {
        /* 404 */ this.userRight = userRight;
        /*     */    }
    /*     */
    /*     */ public Long getUserTypeId() {
        /* 408 */ return this.userTypeId;
        /*     */    }
    /*     */
    /*     */ public void setUserTypeId(Long userTypeId) {
        /* 412 */ this.userTypeId = userTypeId;
        /*     */    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    /*     */
    /*     */ public Long getDeptRepresentId() {
        /*     */ return deptRepresentId;
        /*     */    }
    /*     */
    /*     */ public void setDeptRepresentId(Long deptRepresentId) {
        /*     */ this.deptRepresentId = deptRepresentId;
        /*     */    }


    /*     */
    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getDeptIdStr() {
        return deptIdStr;
    }

    public void setDeptIdStr(String deptIdStr) {
        this.deptIdStr = deptIdStr;
    }

    public String getDeptRepresentIdStr() {
        return deptRepresentIdStr;
    }

    public void setDeptRepresentIdStr(String deptRepresentIdStr) {
        this.deptRepresentIdStr = deptRepresentIdStr;
    }

    public String getPosIdStr() {
        return posIdStr;
    }

    public void setPosIdStr(String posIdStr) {
        this.posIdStr = posIdStr;
    }

    public String getDeptRepresentName() {
        return deptRepresentName;
    }

    public void setDeptRepresentName(String deptRepresentName) {
        this.deptRepresentName = deptRepresentName;
    }

    public Date getDateOfBirthDate() {
        return dateOfBirthDate;
    }

    public void setDateOfBirthDate(Date dateOfBirthDate) {
        this.dateOfBirthDate = dateOfBirthDate;
    }

    public Date getIssueDateIdentDate() {
        return issueDateIdentDate;
    }

    public void setIssueDateIdentDate(Date issueDateIdentDate) {
        this.issueDateIdentDate = issueDateIdentDate;
    }

    public Date getLastChangePassword() {
        return lastChangePassword;
    }

    public void setLastChangePassword(Date lastChangePassword) {
        this.lastChangePassword = lastChangePassword;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getLastResetPassword() {
        return lastResetPassword;
    }

    public void setLastResetPassword(Date lastResetPassword) {
        this.lastResetPassword = lastResetPassword;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public BusinessForm getBusinessForm() {
        return businessForm;
    }

    public void setBusinessForm(BusinessForm businessForm) {
        this.businessForm = businessForm;
    }

      public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }
    
}

/* Location:           C:\Program Files\Apache Software Foundation\TomcatVSA\webapps\vsaadminv3\WEB-INF\classes\
 * Qualified Name:     com.viettel.vsaadmin.client.form.UsersForm
 * JD-Core Version:    0.6.0
 */
