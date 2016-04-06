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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vtit_binhnt53
 */
@Entity
@Table(name = "EVENT_LOG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EventLogBO.findAll", query = "SELECT e FROM EventLogBO e"),
    @NamedQuery(name = "EventLogBO.findByEventId", query = "SELECT e FROM EventLogBO e WHERE e.eventId = :eventId"),
    @NamedQuery(name = "EventLogBO.findByEventDate", query = "SELECT e FROM EventLogBO e WHERE e.eventDate = :eventDate"),
    @NamedQuery(name = "EventLogBO.findByRoleName", query = "SELECT e FROM EventLogBO e WHERE e.roleName = :roleName"),
    @NamedQuery(name = "EventLogBO.findByRoleCode", query = "SELECT e FROM EventLogBO e WHERE e.roleCode = :roleCode"),
    @NamedQuery(name = "EventLogBO.findByAppCode", query = "SELECT e FROM EventLogBO e WHERE e.appCode = :appCode"),
    @NamedQuery(name = "EventLogBO.findByAppName", query = "SELECT e FROM EventLogBO e WHERE e.appName = :appName"),
    @NamedQuery(name = "EventLogBO.findByActor", query = "SELECT e FROM EventLogBO e WHERE e.actor = :actor"),
    @NamedQuery(name = "EventLogBO.findByUserName", query = "SELECT e FROM EventLogBO e WHERE e.userName = :userName"),
    @NamedQuery(name = "EventLogBO.findByAction", query = "SELECT e FROM EventLogBO e WHERE e.action = :action"),
    @NamedQuery(name = "EventLogBO.findByObjectName", query = "SELECT e FROM EventLogBO e WHERE e.objectName = :objectName"),
    @NamedQuery(name = "EventLogBO.findByDescription", query = "SELECT e FROM EventLogBO e WHERE e.description = :description"),
    @NamedQuery(name = "EventLogBO.findByIp", query = "SELECT e FROM EventLogBO e WHERE e.ip = :ip"),
    @NamedQuery(name = "EventLogBO.findByWan", query = "SELECT e FROM EventLogBO e WHERE e.wan = :wan"),
    @NamedQuery(name = "EventLogBO.findByMac", query = "SELECT e FROM EventLogBO e WHERE e.mac = :mac"),
    @NamedQuery(name = "EventLogBO.findByObjectCode", query = "SELECT e FROM EventLogBO e WHERE e.objectCode = :objectCode"),
    @NamedQuery(name = "EventLogBO.findByDeptId", query = "SELECT e FROM EventLogBO e WHERE e.deptId = :deptId"),
    @NamedQuery(name = "EventLogBO.findByAppId", query = "SELECT e FROM EventLogBO e WHERE e.appId = :appId"),
    @NamedQuery(name = "EventLogBO.findByDeptName", query = "SELECT e FROM EventLogBO e WHERE e.deptName = :deptName"),
    @NamedQuery(name = "EventLogBO.findByDeptCode", query = "SELECT e FROM EventLogBO e WHERE e.deptCode = :deptCode"),
    @NamedQuery(name = "EventLogBO.findByObjectId", query = "SELECT e FROM EventLogBO e WHERE e.objectId = :objectId"),
    @NamedQuery(name = "EventLogBO.findByUserId", query = "SELECT e FROM EventLogBO e WHERE e.userId = :userId"),
    @NamedQuery(name = "EventLogBO.findByRoleId", query = "SELECT e FROM EventLogBO e WHERE e.roleId = :roleId")})
public class EventLogBO implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "EVENT_LOG_SEQ", sequenceName = "EVENT_LOG_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_LOG_SEQ")
    @Basic(optional = false)
    @Column(name = "EVENT_ID")
    private Long eventId;
    @Column(name = "EVENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    @Column(name = "ROLE_NAME")
    private String roleName;
    @Column(name = "ROLE_CODE")
    private String roleCode;
    @Column(name = "APP_CODE")
    private String appCode;
    @Column(name = "APP_NAME")
    private String appName;
    @Column(name = "ACTOR")
    private String actor;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "ACTION")
    private String action;
    @Column(name = "OBJECT_NAME")
    private String objectName;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IP")
    private String ip;
    @Column(name = "WAN")
    private String wan;
    @Column(name = "MAC")
    private String mac;
    @Column(name = "OBJECT_CODE")
    private String objectCode;
    @Column(name = "DEPT_ID")
    private Long deptId;
    @Column(name = "APP_ID")
    private Long appId;
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "DEPT_CODE")
    private String deptCode;
    @Column(name = "OBJECT_ID")
    private Long objectId;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "ROLE_ID")
    private Long roleId;

    public EventLogBO() {
    }

    public EventLogBO(Long eventId) {
        this.eventId = eventId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getWan() {
        return wan;
    }

    public void setWan(String wan) {
        this.wan = wan;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventId != null ? eventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventLogBO)) {
            return false;
        }
        EventLogBO other = (EventLogBO) object;
        if ((this.eventId == null && other.eventId != null) || (this.eventId != null && !this.eventId.equals(other.eventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.vsaadmin.database.BO.EventLogBO[ eventId=" + eventId + " ]";
    }
    
}
