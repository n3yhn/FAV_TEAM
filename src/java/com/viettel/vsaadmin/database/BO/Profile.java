/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.viettel.vsaadmin.database.BO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gpdn_trungnq7
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "PROFILE")
@NamedQueries({
    @NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p"),
    @NamedQuery(name = "Profile.findById", query = "SELECT p FROM Profile p WHERE p.id = :id"),
    @NamedQuery(name = "Profile.findByLoginFailAllow", query = "SELECT p FROM Profile p WHERE p.loginFailAllow = :loginFailAllow"),
    @NamedQuery(name = "Profile.findByTemporaryLockTime", query = "SELECT p FROM Profile p WHERE p.temporaryLockTime = :temporaryLockTime"),
    @NamedQuery(name = "Profile.findByMaxTmpLockAday", query = "SELECT p FROM Profile p WHERE p.maxTmpLockAday = :maxTmpLockAday"),
    @NamedQuery(name = "Profile.findByPasswordValidTime", query = "SELECT p FROM Profile p WHERE p.passwordValidTime = :passwordValidTime"),
    @NamedQuery(name = "Profile.findByUserValidTime", query = "SELECT p FROM Profile p WHERE p.userValidTime = :userValidTime"),
    @NamedQuery(name = "Profile.findByAllowMultiIpLogin", query = "SELECT p FROM Profile p WHERE p.allowMultiIpLogin = :allowMultiIpLogin"),
    @NamedQuery(name = "Profile.findByAllowIp", query = "SELECT p FROM Profile p WHERE p.allowIp = :allowIp"),
    @NamedQuery(name = "Profile.findByAllowLoginTimeStart", query = "SELECT p FROM Profile p WHERE p.allowLoginTimeStart = :allowLoginTimeStart"),
    @NamedQuery(name = "Profile.findByAllowLoginTimeEnd", query = "SELECT p FROM Profile p WHERE p.allowLoginTimeEnd = :allowLoginTimeEnd"),
    @NamedQuery(name = "Profile.findByName", query = "SELECT p FROM Profile p WHERE p.name = :name"),
    @NamedQuery(name = "Profile.findByNeedChangePassword", query = "SELECT p FROM Profile p WHERE p.needChangePassword = :needChangePassword"),
    @NamedQuery(name = "Profile.findByTimeToChangePassword", query = "SELECT p FROM Profile p WHERE p.timeToChangePassword = :timeToChangePassword")})
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "LOGIN_FAIL_ALLOW")
    private Long loginFailAllow;
    @Column(name = "TEMPORARY_LOCK_TIME")
    private Long temporaryLockTime;
    @Column(name = "MAX_TMP_LOCK_ADAY")
    private Long maxTmpLockAday;
    @Column(name = "PASSWORD_VALID_TIME")
    private Long passwordValidTime;
    @Column(name = "USER_VALID_TIME")
    private Long userValidTime;
    @Column(name = "ALLOW_MULTI_IP_LOGIN")
    private Long allowMultiIpLogin;
    @Column(name = "ALLOW_IP")
    private String allowIp;
    @Column(name = "ALLOW_LOGIN_TIME_START")
    private Long allowLoginTimeStart;
    @Column(name = "ALLOW_LOGIN_TIME_END")
    private Long allowLoginTimeEnd;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NEED_CHANGE_PASSWORD")
    private Long needChangePassword;
    @Column(name = "TIME_TO_CHANGE_PASSWORD")
    private Long timeToChangePassword;

    public Profile() {
    }

    public Profile(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoginFailAllow() {
        return loginFailAllow;
    }

    public void setLoginFailAllow(Long loginFailAllow) {
        this.loginFailAllow = loginFailAllow;
    }

    public Long getTemporaryLockTime() {
        return temporaryLockTime;
    }

    public void setTemporaryLockTime(Long temporaryLockTime) {
        this.temporaryLockTime = temporaryLockTime;
    }

    public Long getMaxTmpLockAday() {
        return maxTmpLockAday;
    }

    public void setMaxTmpLockAday(Long maxTmpLockAday) {
        this.maxTmpLockAday = maxTmpLockAday;
    }

    public Long getPasswordValidTime() {
        return passwordValidTime;
    }

    public void setPasswordValidTime(Long passwordValidTime) {
        this.passwordValidTime = passwordValidTime;
    }

    public Long getUserValidTime() {
        return userValidTime;
    }

    public void setUserValidTime(Long userValidTime) {
        this.userValidTime = userValidTime;
    }

    public Long getAllowMultiIpLogin() {
        return allowMultiIpLogin;
    }

    public void setAllowMultiIpLogin(Long allowMultiIpLogin) {
        this.allowMultiIpLogin = allowMultiIpLogin;
    }

    public String getAllowIp() {
        return allowIp;
    }

    public void setAllowIp(String allowIp) {
        this.allowIp = allowIp;
    }

    public Long getAllowLoginTimeStart() {
        return allowLoginTimeStart;
    }

    public void setAllowLoginTimeStart(Long allowLoginTimeStart) {
        this.allowLoginTimeStart = allowLoginTimeStart;
    }

    public Long getAllowLoginTimeEnd() {
        return allowLoginTimeEnd;
    }

    public void setAllowLoginTimeEnd(Long allowLoginTimeEnd) {
        this.allowLoginTimeEnd = allowLoginTimeEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNeedChangePassword() {
        return needChangePassword;
    }

    public void setNeedChangePassword(Long needChangePassword) {
        this.needChangePassword = needChangePassword;
    }

    public Long getTimeToChangePassword() {
        return timeToChangePassword;
    }

    public void setTimeToChangePassword(Long timeToChangePassword) {
        this.timeToChangePassword = timeToChangePassword;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.vsaadmin.database.BO.Profile[id=" + id + "]";
    }

}
