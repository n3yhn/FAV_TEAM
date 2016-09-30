/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.ca.uds.util;

import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.viettel.common.util.LogUtil;

/**
 *
 * @author : HungND8@viettel.com.vn
 * @Version: 1.0
 * @Since  : version 1.0
 * @Date   : Feb 9, 2011, 4:52:29 PM
 */
public class DataStreamUtils {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DataStreamUtils.class);
        /** Kieu file pdf*/
    public static final String PDF_FILE = "pdf";

    /** Kieu file excel*/
    public static final String EXL_FILE = "xlsx";
    /**
     * Doc du lieu tu file vao byte[]
     * @param filePath
     * @return byte[] du lieu sau khi doc tu file
     * @throws Exception
     */
    public static byte[] inputFileToBye(String filePath) throws Exception {
        File file = new File(filePath);

        byte[] b = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);
        } catch (FileNotFoundException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return b;
    }

    /**
     * Chuyen du lieu tu InputStream thanh byte[]
     * @param in luong du lieu de chuyen doi
     * @return byte[] sau khi chuyen
     * @throws IOException
     */
    public static byte[] inputStreamToBytes(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }

        in.close();
        out.close();
        return out.toByteArray();
    }

    /**
     * Ghi du lieu dang byte[] ra file
     * @param data du lieu de ghi
     * @param file duong dan file de ghi ra
     */
    public static void outputByteToFile(byte[] data, String file) throws IOException {
        OutputStream out = new FileOutputStream(file);
        out.write(data);
        out.close();
    }

    /**
     * Check su ton tai cua file
     * @param filePath duong dan file
     * @return TRUE if ton tai, FALSE neu ko ton tai
     */
    public static boolean isExistFile(String filePath) {
        if (null == filePath) {
            return false;
        }

        File file = new File(filePath);
        return file.isFile();
    }

    /**
     * Lay ve phan mo rong file
     * @param fileName
     * @return
     */
    public static String getFileType(String fileName) {

        String ext = null;
        if (fileName != null) {
            String lFileName = fileName.toLowerCase();

            ext = (lFileName.lastIndexOf(".")==-1)?
                null:lFileName.substring(lFileName.lastIndexOf(".")+1,lFileName.length());
        }
        return ext;
    }
}
