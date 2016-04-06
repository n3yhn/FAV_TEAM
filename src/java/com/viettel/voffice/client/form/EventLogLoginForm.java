/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.client.form;

import java.util.Date;

/**
 *
 * @author HaVM2
 */
public class EventLogLoginForm {
    private Long eventId;
    private Date eventDate;
    private String userName;
    private String action;
    private String ip;
    private String wan;
    private String mac;
    private Date eventDateTo;
    private Date eventDateFrom;

    public EventLogLoginForm() {
    }

    public EventLogLoginForm(Long eventId, Date eventDate, String userName, String action, String ip, String wan, String mac) {
        this.eventId = eventId;
        this.eventDate = eventDate;
        this.userName = userName;
        this.action = action;
        this.ip = ip;
        this.wan = wan;
        this.mac = mac;
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

    public Date getEventDateTo() {
        return eventDateTo;
    }

    public void setEventDateTo(Date eventDateTo) {
        this.eventDateTo = eventDateTo;
    }

    public Date getEventDateFrom() {
        return eventDateFrom;
    }

    public void setEventDateFrom(Date eventDateFrom) {
        this.eventDateFrom = eventDateFrom;
    }
    
}
