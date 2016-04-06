/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.crypto;

/**
 *
 * @author HaVM2
 */
public class Crypto {

    static final String HEXES = "0123456789ABCDEF";
    
    public static String byteToHex(byte[] raw) {
        if (raw == null) {
            return null;
        }
        StringBuffer hex = new StringBuffer(2* raw.length);
        for (int i=0;i<raw.length;i++) {
            byte b = raw[i];
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    public static byte[] hexToByte(String hexString) {
        int len = hexString.length();
        byte[] ba = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            ba[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return ba;
    }
}
