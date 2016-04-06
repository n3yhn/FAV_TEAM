/*    */ package com.viettel.vsaadmin.common.util;
/*    */
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import sun.misc.BASE64Encoder;
/*    */
/*    */ public final class PasswordService /*    */ {
    /*    */ private static PasswordService instance;
    /*    */
    /*    */ public synchronized String encrypt(String plaintext)
            /*    */ throws Exception /*    */ {
        /* 25 */ MessageDigest md = null;
        /*    */ try {
            /* 27 */ md = MessageDigest.getInstance("SHA-1");
            /*    */        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            /*    */        }
        /*    */ try {
            /* 32 */ md.update(plaintext.getBytes("UTF-8"));
            /*    */        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            /*    */        }
        /*    */
        /* 37 */ byte[] raw = md.digest();
        /* 38 */ String hash = new BASE64Encoder().encode(raw);
        /* 39 */ return hash;
        /*    */    }
    /*    */
    /*    */ public static synchronized PasswordService getInstance() /*    */ {
        /* 44 */ if (instance == null) {
            /* 45 */ instance = new PasswordService();
            /*    */        }
        /* 47 */ return instance;
        /*    */    }
    /*    */ }

/* Location:           C:\Program Files\Apache Software Foundation\TomcatVSA\webapps\vsaadminv3\WEB-INF\classes\
 * Qualified Name:     com.viettel.vsaadmin.common.util.PasswordService
 * JD-Core Version:    0.6.0
 */