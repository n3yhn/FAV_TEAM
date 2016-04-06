/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Administrator
 */
public class ServiceSessionManager {

    private static Map<String, ServiceToken> lstToken;

    public static String generateToken(String userName) {
        UUID uid = java.util.UUID.randomUUID();
        ServiceToken sv = new ServiceToken(userName, uid.toString());
        if (lstToken == null) {
            lstToken = new HashMap<>();
        }
        lstToken.put(sv.getTokenString(), sv);
        return sv.getTokenString();
    }

    public static boolean validToken(String tokenString) {
        if (lstToken == null) {
            return false;
        }
        ServiceToken svr = lstToken.get(tokenString);
        if (svr == null) {
            return false;
        }
        if (svr.getTokenString().equals(tokenString)) {
            return svr.validToken();
        }
        return false;
    }

    public static boolean removeToken(String tokenString) {
        if (lstToken == null) {
            return false;
        }
        ServiceToken svr = lstToken.remove(tokenString);
        if (svr == null) {
            return false;
        } else {
            return true;
        }
    }
}
