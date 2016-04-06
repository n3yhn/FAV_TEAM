/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class ServiceToken {
    String userName;
    Date validateTime;
    String tokenString;

    public ServiceToken(String userName, String tokenString) {
        this.userName = userName;
        this.tokenString = tokenString;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, 30);
        validateTime = cal.getTime();
    }

    public boolean validToken() {
        Date currentDate = new Date();
        if (validateTime.compareTo(currentDate) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public Date getValidateTime() {
        return validateTime;
    }

    public void setValidateTime(Date validateTime) {
        this.validateTime = validateTime;
    }

    public String getTokenString() {
        return tokenString;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }
}
