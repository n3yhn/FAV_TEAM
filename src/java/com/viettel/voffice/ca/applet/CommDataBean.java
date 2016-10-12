/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.voffice.ca.applet;

import java.io.Serializable;
import java.security.PublicKey;
import java.security.cert.Certificate;

/**
 * Dung de send du lieu tu applet den servelet
 * \nThong tin send gom:
 * \n - Du lieu chu ki (signature)
 * \n - Public key
 * @author chucvq@viettel.com.vn
 * @since Sep 29, 2010
 */
public class CommDataBean implements Serializable {
    public static final String TYPE_INIT="INIT";
    public static final String TYPE_SIGN_BAN_POSITION="SIGN_BAN_POSITION";    
    private String content;
    private String type;
    private int code;    
    private String message;
    private byte[] signature;    
    /** khoa cong khai.*/
    private PublicKey publicKey;
    /** Encoded public key. */
    private byte[] encodedPublicKey;
 
    private Certificate[] certs = new Certificate[1];
    /** Chain Certificate cua NNT. */
    private Certificate[] certChain = new Certificate[3];
    private long userId;
    
    public String getContent() {
        return content;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Certificate[] getCertChain() {
        return certChain;
    }

    public void setCertChain(Certificate[] certChain) {
        this.certChain = certChain;
    }

    public Certificate[] getCerts() {
        return certs;
    }

    public void setCerts(Certificate[] certs) {
        this.certs = certs;
    }

    public byte[] getEncodedPublicKey() {
        return encodedPublicKey;
    }

    public void setEncodedPublicKey(byte[] encodedPublicKey) {
        this.encodedPublicKey = encodedPublicKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }
    
}
