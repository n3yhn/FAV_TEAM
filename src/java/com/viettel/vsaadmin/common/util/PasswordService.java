package com.viettel.vsaadmin.common.util;

import com.viettel.common.util.LogUtil;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

public final class PasswordService {

    private static PasswordService instance;

    public synchronized String encrypt(String plaintext)
            throws Exception {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        try {
            md.update(plaintext.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }

        byte[] raw = md.digest();
        String hash = new BASE64Encoder().encode(raw);
        return hash;
    }

    public static synchronized PasswordService getInstance() {
        if (instance == null) {
            instance = new PasswordService();
        }
        return instance;
    }
}

/* Location:           C:\Program Files\Apache Software Foundation\TomcatVSA\webapps\vsaadminv3\WEB-INF\classes\
 * Qualified Name:     com.viettel.vsaadmin.common.util.PasswordService
 * JD-Core Version:    0.6.0
 */
