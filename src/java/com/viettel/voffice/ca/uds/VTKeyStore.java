/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.viettel.voffice.ca.uds;

import java.security.KeyStore;
import java.io.FileInputStream;
import java.security.cert.Certificate;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author : HungND8@viettel.com.vn
 * @Version: 1.0
 * @Since  : version 1.0
 * @Date   : Feb 9, 2011, 4:52:29 PM
 */
public class VTKeyStore {

    Logger logger = Logger.getLogger(VTKeyStore.class);
    /** Key store*/
    private KeyStore keyStore;
    /** private key */
    private PrivateKey privateKey;
    /** chain key*/
    private Certificate[] certificateChain;
    Locale locale = new Locale("en", "US");
    ResourceBundle bundle = ResourceBundle.getBundle("cas", locale);
    private static final String VIETTEL_CA = "Viettel-CA";
    private static final String BKAV_CA = "BkavCA";
    private static final String VNPT_CA = "VNPT Certification Authority";
    //NACENCOM
    private static final String CA2 = "CA2";
    //FPT
    private static final String FPT_CA = "FPT Certification Authority";

    public VTKeyStore() {
    }

    public VTKeyStore(Certificate cer1, Certificate cert2) {
        certificateChain = getOtherVanCertChain(cer1, cert2);

    }

    /**
     * Ham khoi tao Key Store tu 1 file java key store
     * @param keyStoreFile
     */
    public VTKeyStore(String keyStoreFile, String pass) throws Exception {
        //keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore = KeyStore.getInstance("jks");
        keyStore.load(new FileInputStream(keyStoreFile), pass.toCharArray());
        getCertificateChain(keyStore, pass);
    }
//tao certchain bao gom root và viettel

    public void createViettelCertchain() throws Exception {
        //keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        CertificateFactory cf = CertificateFactory.getInstance("X509");

        Certificate cert1, cert2;
        cert2 = cf.generateCertificate(new FileInputStream(bundle.getString("micCertFile")));
        cert1 = cf.generateCertificate(new FileInputStream(bundle.getString("rootCACertFiel")));

        certificateChain = getOtherVanCertChain(cert1, cert2);
//        getCertificateChain(keyStore, pass);
    }

    public VTKeyStore(String keyStoreFile1, String pass1, String keyStoreFile2, String pass2) throws Exception {
        certificateChain = getOtherVanCertChain(getOtherVANCertificate(keyStoreFile1, pass1), getOtherVANCertificate(keyStoreFile2, pass2));
    }

    /**
     * Khoi tao tu key store
     * @param keyStore
     * @throws Exception
     */
    public VTKeyStore(KeyStore keyStore, String pass) throws Exception {
        this.keyStore = keyStore;
        getCertificateChain(this.keyStore, pass);
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    public void setKeyStore(KeyStore keyStore) {
        this.keyStore = keyStore;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public Certificate[] getCertificateChain() {
        return certificateChain;
    }

    public void setCertificateChain(Certificate[] certificateChain) {
        this.certificateChain = certificateChain;
    }

    /**
     * Lay Cerfiticate from keyStore
     * @param aKeyStore
     * @return
     * @throws Exception
     */
    public void getCertificateChain(KeyStore aKeyStore, String pass) throws Exception {
        String alias;
        Enumeration aliasesEnum = aKeyStore.aliases();

        while (aliasesEnum.hasMoreElements()) {
            alias = (String) aliasesEnum.nextElement();
            if (aKeyStore.isKeyEntry(alias)) {
                certificateChain = aKeyStore.getCertificateChain(alias);

                if (pass != null) {
                    privateKey = (PrivateKey) aKeyStore.getKey(alias, pass.toCharArray());
                } else {
                    privateKey = (PrivateKey) aKeyStore.getKey(alias, null);
                }

                logger.info("getCertificateChain success");
                return;
            }
        }
    }

    public Certificate getOtherVANCertificate(String keyStoreFile, String pass) throws Exception {
        KeyStore aKeyStore = KeyStore.getInstance("jks");
        aKeyStore.load(new FileInputStream(keyStoreFile), pass.toCharArray());
        String alias;
        Enumeration aliasesEnum = aKeyStore.aliases();
        Certificate cert = null;
        while (aliasesEnum.hasMoreElements()) {
            alias = (String) aliasesEnum.nextElement();
            if (aKeyStore.isCertificateEntry(alias)) {
                cert = aKeyStore.getCertificate(alias);
                logger.info("getCertificateChain success");
                return cert;
            }
        }
        return cert;
    }

    public Certificate[] getOtherVanCertChain(Certificate cert1, Certificate cert2) {
        Certificate[] certChain = new Certificate[3];
        certChain[1] = cert1;
        certChain[2] = cert2;
        return certChain;
    }

    public Certificate[] createNNTCertChain(Certificate[] jksCChain, Certificate[] nntCChain) {

        if (jksCChain.length > 0 && nntCChain.length > 0) {

            jksCChain[0] = nntCChain[0];

            return jksCChain;
        } else {
            return null;
        }
    }
//public Certificate[] createVANCertChain()throws Exception {
//        String alias;
//        Certificate[] cchain = null;
//        KeyStore ks = KeyStore.getInstance("JKS");
//        String keyStoreFile = bundle.getString("vanKeyStore"); // chu ki so cua VAN Viettel
//        char[] keystorePass = bundle.getString("vanKeyStorePass")==null?null:bundle.getString("vanKeyStorePass").toCharArray();//password ma pin truy cap keystore
//        ks.load(new FileInputStream(keyStoreFile), keystorePass);
//
//        Enumeration aliasesEnum = ks.aliases();
//
//        while (aliasesEnum.hasMoreElements()) {
//            alias = (String) aliasesEnum.nextElement();
//            if (ks.isKeyEntry(alias)) {
//                cchain = ks.getCertificateChain(alias);
//            }
//        }
//        return cchain;
//    }

    public VTKeyStore getKeyStore(HttpServletRequest request,String issuer) throws Exception {
//        String keystore = null;
        VTKeyStore vTKeyStore = null;
//        String pass = null;
        CertificateFactory cf = CertificateFactory.getInstance("X509");

        Certificate cert1, cert2;
        if (issuer.equals(VIETTEL_CA)) {
//            keystore = bundle.getString("vanKeyStore");
//            pass = bundle.getString("vanKeyStorePass");
            
            
            
            cert2 = cf.generateCertificate(request.getServletContext().getResourceAsStream("/WEB-INF/ca/MIC.cer"));
            cert1 = cf.generateCertificate(request.getServletContext().getResourceAsStream("/WEB-INF/ca/viettel-ca.cer"));
//            cert2 = cf.generateCertificate(new FileInputStream(bundle.getString("micCertFile")));
//            cert1 = cf.generateCertificate(new FileInputStream(bundle.getString("rootCACertFiel")));
            //dong nay dung cho khi ko ky tren HSM
            vTKeyStore = new VTKeyStore(cert1, cert2);
        } else if (issuer.equals(BKAV_CA)) {
            cert2 = cf.generateCertificate(new FileInputStream(bundle.getString("micCertFile")));
            cert1 = cf.generateCertificate(new FileInputStream(bundle.getString("bkavCACertFile")));
            vTKeyStore = new VTKeyStore(cert1, cert2);
        } else if (issuer.equals(VNPT_CA)) {
            cert2 = cf.generateCertificate(new FileInputStream(bundle.getString("micCertFile")));
            cert1 = cf.generateCertificate(new FileInputStream(bundle.getString("vnptCACertFile")));

            vTKeyStore = new VTKeyStore(cert1, cert2);
        } else if (issuer.equals(FPT_CA)) {
//            keystore = bundle.getString("vnptKeyStore");
//            pass = bundle.getString("vnptKeyStorePass");
//            String keystore2 = bundle.getString("micKeyStore");
//            String pass2 = bundle.getString("micKeyStorePass");
//            cert2 = cf.generateCertificate(new FileInputStream(bundle.getString("micCertFile")));
//            cert1 = cf.generateCertificate(new FileInputStream(bundle.getString("vnptCACertFile")));
            cert2 = cf.generateCertificate(new FileInputStream(bundle.getString("micCertFile")));
            cert1 = cf.generateCertificate(new FileInputStream(bundle.getString("fptCACertFile")));

            vTKeyStore = new VTKeyStore(cert1, cert2);
        } else if (issuer.equals(CA2)) {
            cert2 = cf.generateCertificate(new FileInputStream(bundle.getString("micCertFile")));
            cert1 = cf.generateCertificate(new FileInputStream(bundle.getString("nacencomCACertFile")));
            vTKeyStore = new VTKeyStore(cert1, cert2);
        }

        return vTKeyStore;
    }

    /**
     *
     * @param nntCertChain
     * @return
     */
    public String getIssuerCert(Certificate[] nntCertChain) {
        String issuer = null;
        if (nntCertChain != null) {
            X509Certificate nntCert = (X509Certificate) nntCertChain[0];
            issuer = X509ExtensionUtil.getIssuerName(nntCert);
        }
        return issuer;

    }

    public Certificate[] getCertChain(VTKeyStore keyStore) throws Exception {
        if (keyStore != null) {
            return keyStore.getCertificateChain();
        } else {
            return null;
        }
    }

    /**
     *
     */
    public void getCertChainFromCert(String higher, String lower) {
    }
}
