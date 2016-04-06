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
@Table(name = "EVENT_LOG_LOGIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EventLogLogin.findAll", query = "SELECT e FROM EventLogLogin e"),
    @NamedQuery(name = "EventLogLogin.findByEventId", query = "SELECT e FROM EventLogLogin e WHERE e.eventId = :eventId"),
    @NamedQuery(name = "EventLogLogin.findByEventDate", query = "SELECT e FROM EventLogLogin e WHERE e.eventDate = :eventDate"),
    @NamedQuery(name = "EventLogLogin.findByUserName", query = "SELECT e FROM EventLogLogin e WHERE e.userName = :userName"),
    @NamedQuery(name = "EventLogLogin.findByAction", query = "SELECT e FROM EventLogLogin e WHERE e.action = :action"),
    @NamedQuery(name = "EventLogLogin.findByIp", query = "SELECT e FROM EventLogLogin e WHERE e.ip = :ip"),
    @NamedQuery(name = "EventLogLogin.findByWan", query = "SELECT e FROM EventLogLogin e WHERE e.wan = :wan"),
    @NamedQuery(name = "EventLogLogin.findByMac", query = "SELECT e FROM EventLogLogin e WHERE e.mac = :mac")})
public class EventLogLogin implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "EVENT_LOG_LOGIN_SEQ", sequenceName = "EVENT_LOG_LOGIN_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_LOG_LOGIN_SEQ")
    @Basic(optional = false)
    @Column(name = "EVENT_ID")
    private Long eventId;
    @Column(name = "EVENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "ACTION")
    private String action;
    @Column(name = "IP")
    private String ip;
    @Column(name = "WAN")
    private String wan;
    @Column(name = "MAC")
    private String mac;

    public EventLogLogin() {
    }

    public EventLogLogin(Long eventId) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventId != null ? eventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventLogLogin)) {
            return false;
        }
        EventLogLogin other = (EventLogLogin) object;
        if ((this.eventId == null && other.eventId != null) || (this.eventId != null && !this.eventId.equals(other.eventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.vsaadmin.database.BO.EventLogLogin[ eventId=" + eventId + " ]";
    }
    
}
