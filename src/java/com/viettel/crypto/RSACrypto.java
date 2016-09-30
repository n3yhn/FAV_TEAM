/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.crypto;

import com.viettel.common.util.LogUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author HaVM2
 */
public class RSACrypto extends Crypto {

    public static void SaveKeyPair(String path, KeyPair keyPair) throws IOException {
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Store Public Key.
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
                publicKey.getEncoded());
        ObjectOutputStream fos = new ObjectOutputStream(new FileOutputStream(path));
        fos.writeObject(x509EncodedKeySpec.getEncoded());

        // Store Private Key.
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                privateKey.getEncoded());
        fos.writeObject(pkcs8EncodedKeySpec.getEncoded());
        fos.close();
    }

    public static String PublicKeyToString(PublicKey key) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key.getEncoded());
        String str = byteToHex(x509EncodedKeySpec.getEncoded());
        return str;
    }

    public static PublicKey StringToPublicKey(String strKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] code = hexToByte(strKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(code);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        return publicKey;
    }

    public static String PrivateKeyToString(PrivateKey key) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key.getEncoded());
        String str = byteToHex(pkcs8EncodedKeySpec.getEncoded());
        return str;
    }

    public static PrivateKey StringToPrivateKey(String strKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] code = hexToByte(strKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(code);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        return privateKey;
    }

    public static PublicKey LoadPublicKey(String path, String algorithm)
            throws IOException, NoSuchAlgorithmException,
            InvalidKeySpecException, ClassNotFoundException {
        // Read Public Key.
        ObjectInputStream fis = new ObjectInputStream(new FileInputStream(path));

        byte[] encodedPublicKey = (byte[]) fis.readObject();
        fis.close();

        // Generate KeyPair.
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
                encodedPublicKey);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        return publicKey;
    }

    public static PrivateKey LoadPrivateKey(String path, String algorithm)
            throws IOException, NoSuchAlgorithmException,
            InvalidKeySpecException, ClassNotFoundException {
        // Read Public Key.
        ObjectInputStream fis = new ObjectInputStream(new FileInputStream(path));

//        byte[] encodedPublicKey = (byte[]) fis.readObject();
        byte[] encodedPrivateKey = (byte[]) fis.readObject();
        fis.close();

        // Generate KeyPair.
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
                encodedPrivateKey);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        return privateKey;
    }

    public static KeyPair LoadKeyPair(String path, String algorithm)
            throws IOException, NoSuchAlgorithmException,
            InvalidKeySpecException, ClassNotFoundException {
        // Read Public Key.
        ObjectInputStream fis = new ObjectInputStream(new FileInputStream(path));

        byte[] encodedPublicKey = (byte[]) fis.readObject();
        byte[] encodedPrivateKey;
        encodedPrivateKey = (byte[]) fis.readObject();
        fis.close();

        // Generate KeyPair.
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
                encodedPublicKey);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
                encodedPrivateKey);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        return new KeyPair(publicKey, privateKey);
    }

    public static String CertificateToString(String path)
            throws IOException, NoSuchAlgorithmException,
            InvalidKeySpecException, ClassNotFoundException {
        // Read Public Key.
        String str = "";
        try {
            File f = new File(path);
            InputStream fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            str = byteToHex(data);
            fis.close();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        //str = byteToHex(data);
        return str;
    }

    public static PublicKey PublicKeyFromCertFile(String path)
            throws IOException, NoSuchAlgorithmException,
            InvalidKeySpecException, ClassNotFoundException {
        // Read Public Key.
        PublicKey key = null;
        try {
            InputStream fis = new FileInputStream(path);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(fis);
            key = cert.getPublicKey();
            fis.close();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901

        }
        //str = byteToHex(data);
        return key;
    }

    public static PublicKey PublicKeyFromCertString(String certString)
            throws IOException, NoSuchAlgorithmException,
            InvalidKeySpecException, ClassNotFoundException {
        // Read Public Key.
        PublicKey key = null;
        try {
            byte[] data = hexToByte(certString);
            InputStream fis = new ByteArrayInputStream(data);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(fis);
            key = cert.getPublicKey();
            fis.close();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        //str = byteToHex(data);
        return key;
    }
}
