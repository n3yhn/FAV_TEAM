/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.crypto;

import com.viettel.common.util.LogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author HaVM2
 */
public class AESCrypto extends Crypto {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AESCrypto.class);
    static final byte[] iv = new byte[]{
        0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f
    };
    static final byte[] v = new byte[]{
        0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
        0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17
    };
    static final byte[] vi = new byte[]{
        0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
        0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1a, 0x1b, 0x1c, 0x1d, 0x1e, 0x1f
    };
    SecretKey key = null;
    int length = 128;
    Cipher ecipher;
    Cipher dcipher;
    // Buffer used to transport the bytes from one stream to another
    byte[] buf = new byte[1024];

    public AESCrypto(int length) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            this.length = length;
            kgen.init(length);
            this.setupCrypto(kgen.generateKey());
        } catch (Exception e) {
            LogUtil.addLog(e);//binhnt sonar a160901
        }
    }

    public AESCrypto(SecretKey key) {
        this.setupCrypto(key);
    }

    public AESCrypto(byte[] keys) {
        SecretKeySpec skey = new SecretKeySpec(keys, "AES");
        this.setupCrypto(skey);
    }

    public AESCrypto(String key) {
        if (key.length() < 32) {
            for (int i = key.length(); i < 32; i++) {
                key = key + " ";
            }
        } else if (key.length() > 32) {
            key = key.substring(0, 32);
        }
        SecretKeySpec skey = new SecretKeySpec(hexToByte(key), "AES");
        this.setupCrypto(skey);
    }

    public String encryptKey(PublicKey pubKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return byteToHex(cipher.doFinal(this.key.getEncoded()));
    }

    public SecretKey decryptKey(String code, PrivateKey priKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        byte[] encode = cipher.doFinal(hexToByte(code));
        SecretKeySpec skey = new SecretKeySpec(encode, "AES");
        return skey;
    }

    public void encrypt(String inputFile, String outputFile) throws Exception {
        File input = new File(inputFile);
        if (!input.exists()) {
            throw new Exception("Không tồn tại file input");
        }
        if (key == null) {
            throw new Exception("Key mã hóa chưa được khởi tạo");
        }
        InputStream in = new FileInputStream(inputFile);
        OutputStream out = new FileOutputStream(outputFile);
        encrypt(in, out);
    }

    public void decrypt(String inputFile, String outputFile) throws Exception {
        File input = new File(inputFile);
        if (!input.exists()) {
            throw new Exception("Không tồn tại file input");
        }
        if (key == null) {
            throw new Exception("Key mã hóa chưa được khởi tạo");
        }
        InputStream in = new FileInputStream(inputFile);
        OutputStream out = new FileOutputStream(outputFile);
        decrypt(in, out);
    }

    //
    // Ma hoa file dinh kem encrypt key da ma hoa
    //
    public void encrypt(String inputFile, String encryptedKey, String outputFile) throws Exception {
        //
        // Ma hoa da
        //
        String tmpFile = outputFile + "tmp";
        encrypt(inputFile, tmpFile);
        //
        // Dua encryptedKey vao
        //
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFile));
        oos.writeObject(encryptedKey);

        InputStream is = new FileInputStream(tmpFile);
        int numRead;
        while ((numRead = is.read(buf)) >= 0) {
            oos.write(buf, 0, numRead);
        }
        oos.close();
        is.close();
        try {
            File f = new File(tmpFile);
            f.delete();
        } catch (Exception e) {
            LogUtil.addLog(e);//binhnt sonar a160901
        }
    }

    //
    // Giai ma file kem key co private key
    //
    public void decrypt(String inputFile, PrivateKey pk, String outputFile) {
        try {
            //
            // Doc encrypted key trong file
            //
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputFile));
            String encryptedKey = (String) ois.readObject();
            //
            // Giai ma key
            //
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, pk);
            byte[] encode = cipher.doFinal(hexToByte(encryptedKey));
            SecretKeySpec skey = new SecretKeySpec(encode, "AES");
            //
            // Ghi du lieu trong file ra file tam
            //
            int numRead;
            String tmpFile = outputFile + "tmp";
            FileOutputStream fos = new FileOutputStream(tmpFile);
            while ((numRead = ois.read(buf)) >= 0) {
                fos.write(buf, 0, numRead);
            }
            fos.close();
            ois.close();
            //
            // Giai ma file
            //
            this.setupCrypto(skey);
            decrypt(tmpFile, outputFile);

            File f = new File(tmpFile);
            f.delete();
        } catch (Exception e) {
            LogUtil.addLog(e);//binhnt sonar a160901
        }
    }

    /**
     * Input a string that will be md5 hashed to create the key.
     *
     * @return void, cipher initialized
     */
    private void setupCrypto(SecretKey key) {
        // Create an 8-byte initialization vector
        this.key = key;

        AlgorithmParameterSpec paramSpec;
        paramSpec = new IvParameterSpec(iv);
//        if (length == 128) {
//            paramSpec = new IvParameterSpec(iv);
//        } else if (length == 192) {
//            paramSpec = new IvParameterSpec(v);
//        } else if (length == 256) {
//            paramSpec = new IvParameterSpec(vi);
//        }
        try {
            ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // CBC requires an initialization vector
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (Exception e) {
            LogUtil.addLog(e);//binhnt sonar a160901
        }
    }

    public void encrypt(InputStream in, OutputStream out) {
        try {
            // Bytes written to out will be encrypted
            out = new CipherOutputStream(out, ecipher);

            // Read in the cleartext bytes and write to out to encrypt
            int numRead;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (java.io.IOException e) {
            LogUtil.addLog(e);//binhnt sonar a160901
        }
    }

    /**
     * Input is a string to encrypt.
     *
     * @return a Hex string of the byte array
     */
    public String encrypt(String plaintext) {
        try {
            byte[] ciphertext = ecipher.doFinal(plaintext.getBytes("UTF-8"));
            return byteToHex(ciphertext);
        } catch (Exception e) {
            LogUtil.addLog(e);//binhnt sonar a160901
            return null;
        }
    }

    public void decrypt(InputStream in, OutputStream out) {
        try {
            // Bytes read from in will be decrypted
            in = new CipherInputStream(in, dcipher);

            // Read in the decrypted bytes and write the cleartext to out
            int numRead;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (java.io.IOException e) {
            LogUtil.addLog(e);//binhnt sonar a160901
        }
    }

    /**
     * Input encrypted String represented in HEX
     *
     * @return a string decrypted in plain text
     */
    public String decrypt(String hexCipherText) {
        try {
            String plaintext = new String(dcipher.doFinal(this.hexToByte(hexCipherText)), "UTF-8");
            return plaintext;
        } catch (Exception e) {
            LogUtil.addLog(e);//binhnt sonar a160901
            return null;
        }
    }

    public String decrypt(byte[] ciphertext) {
        try {
            String plaintext = new String(dcipher.doFinal(ciphertext), "UTF-8");
            return plaintext;
        } catch (Exception e) {
            LogUtil.addLog(e);//binhnt sonar a160901
            return null;
        }
    }

    private static byte[] getMD5(String input) {
        try {
            byte[] bytesOfMessage = input.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(bytesOfMessage);
        } catch (Exception e) {
            LogUtil.addLog(e);//binhnt sonar a160901
            return null;
        }
    }

    public SecretKey getKey() {
        return key;
    }

    public void setKey(SecretKey key) {
        this.key = key;
    }
}
