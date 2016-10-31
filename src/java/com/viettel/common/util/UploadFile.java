/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.common.util;

import com.viettel.dojoTag.DojoJSON;
import static com.viettel.voffice.database.DAO.UploadIframeDAO.getSafeFileName;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

/**
 *
 * @author Ebaymark
 */
public class UploadFile {

    private static final String separatorFile = String.valueOf(File.separatorChar);

//    public static void main(String argv[]) {
//        UploadFile.uploadFile("", "", new File("C:/Ghost.zip"), null);
//    }
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UploadFile.class);

    /**
     *
     * @param directory
     * @param fileName
     * @param srcFile
     * @param request
     * @return
     */
    public static String uploadFile(String directory, String fileName, File srcFile, HttpServletRequest request) {
        Calendar cal = Calendar.getInstance();
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String dir = rb.getString("directory");
        String subDir = separatorFile + String.valueOf(cal.getTime().getYear() + 1900)
                + separatorFile + String.valueOf(cal.getTime().getMonth() + 1)
                + separatorFile + String.valueOf(cal.getTime().getDate());
        String subFolder = dir + subDir;
        //linhdx sua
        File folderExisting = new File(dir);
        if (!folderExisting.isDirectory()) {
            folderExisting.mkdir();
        }
        if (folderExisting.isDirectory()) {
            //tao folder theo ngay thang
            File temp = new File(subFolder);
            if (!temp.isDirectory()) {
                temp.mkdirs();
            }
        }
        Long time = new Long(cal.getTimeInMillis());
        String fName = "ATTP_" + time.toString() + "_" + fileName;
//        String dir = "/share/" + directory + separatorFile;
//        String pathDir = request.getRealPath(dir);
        File destFile = new File(subFolder + separatorFile + fName);
        copy(srcFile, destFile);
        srcFile.deleteOnExit();
        File temp = new File(subFolder);
        if (temp.isDirectory()) {
            return subDir + separatorFile + fName;
        } else {
            return "";
        }
    }

    //hieptq update 111214
    /**
     *
     * @param directory
     * @param fileName
     * @param srcFile
     * @param request
     * @return
     */
    public static String uploadFilePdf(String directory, String fileName, File srcFile, HttpServletRequest request) {
        Calendar cal = Calendar.getInstance();
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String dir
                = rb.getString("PERMIT_upload")
                + String.valueOf(cal.getTime().getYear() + 1900)
                + separatorFile + String.valueOf(cal.getTime().getMonth() + 1)
                + separatorFile + String.valueOf(cal.getTime().getDate())
                + separatorFile;
        File folderExisting = new File(getSafeFileName(dir));
        if (!folderExisting.isDirectory()) {
            folderExisting.mkdir();
        }
        if (folderExisting.isDirectory()) {
            //tao folder theo ngay thang
            File temp = new File(dir);
            if (!temp.isDirectory()) {
                temp.mkdirs();
            }
        }
        // Long time = new Long(cal.getTimeInMillis());
        String fName = fileName;
//        String dir = "/share/" + directory + separatorFile;
//        String pathDir = request.getRealPath(dir);
        File destFile = new File(dir + fName);
        copy(srcFile, destFile);
        srcFile.deleteOnExit();
        File temp = new File(dir);
        if (temp.isDirectory()) {
            return dir + fName;
        } else {
            return "";
        }
    }

    //hietq update 121214 upload file VT
    /**
     *
     * @param directory
     * @param fileName
     * @param srcFile
     * @param request
     * @return
     */
    public static String uploadFilePdfVT(String directory, String fileName, File srcFile, HttpServletRequest request) {
        Calendar cal = Calendar.getInstance();
        ResourceBundle rb = ResourceBundle.getBundle("config");
        //160629 binhnt update
        String dir
                = rb.getString("PERMIT_upload")
                + String.valueOf(cal.getTime().getYear() + 1900)
                + separatorFile + String.valueOf(cal.getTime().getMonth() + 1)
                + separatorFile + String.valueOf(cal.getTime().getDate())
                + separatorFile;
        File folderExisting = new File(getSafeFileName(dir));
        if (!folderExisting.isDirectory()) {
            folderExisting.mkdir();
        }
        if (folderExisting.isDirectory()) {
            //tao folder theo ngay thang
            File temp = new File(dir);
            if (!temp.isDirectory()) {
                temp.mkdirs();
            }
        }
        String fName = fileName + "_VT";
        File destFile = new File(dir + fName);
        copy(srcFile, destFile);
        srcFile.deleteOnExit();
        File temp = new File(dir);
        if (temp.isDirectory()) {
            return dir + fName;
        } else {
            return "";
        }
    }

    //upload excel
    /**
     *
     * @param directory
     * @param fileName
     * @param srcFile
     * @param request
     * @return
     */
    public static String uploadFileExcel(String directory, String fileName, File srcFile, HttpServletRequest request) {
        Calendar cal = Calendar.getInstance();
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String dir = rb.getString("directoryExcel");
        String subDir = separatorFile + String.valueOf(cal.getTime().getYear() + 1900)
                + separatorFile + String.valueOf(cal.getTime().getMonth() + 1)
                + separatorFile + String.valueOf(cal.getTime().getDate());
        String subFolder = dir + subDir;
        File folderExisting = new File(getSafeFileName(dir));
        if (!folderExisting.isDirectory()) {
            folderExisting.mkdir();
        }
        if (folderExisting.isDirectory()) {
            //tao folder theo ngay thang
            File temp = new File(subFolder);
            if (!temp.isDirectory()) {
                temp.mkdirs();
            }
        }
        Long time = new Long(cal.getTimeInMillis());
        String fName = "ATTP_" + time.toString() + "_" + fileName;
//        String dir = "/share/" + directory + separatorFile;
//        String pathDir = request.getRealPath(dir);
        File destFile = new File(subFolder + separatorFile + fName);
        copy(srcFile, destFile);
        srcFile.deleteOnExit();
        File temp = new File(subFolder);
        if (temp.isDirectory()) {
            return subDir + separatorFile + fName;
        } else {
            return "";
        }
    }

    /**
     *
     * @param directory
     * @param fileName
     * @param srcFile
     * @param request
     * @param userName
     * @return
     */
    public static String uploadFileUserAttach(String directory, String fileName, File srcFile, HttpServletRequest request, String userName) {
        Calendar cal = Calendar.getInstance();
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String dir = rb.getString("directory");
        String subDir = separatorFile + userName;
        String subFolder = dir + subDir;
        File folderExisting = new File(getSafeFileName(dir));
        if (!folderExisting.isDirectory()) {
            folderExisting.mkdir();
        }
        if (folderExisting.isDirectory()) {
            //tao folder theo ngay thang
            File temp = new File(subFolder);
            if (!temp.isDirectory()) {
                temp.mkdirs();
            }
        }
        Long time = new Long(cal.getTimeInMillis());
        String fName = time.toString() + "_" + fileName;
//        String dir = "/share/" + directory + separatorFile;
//        String pathDir = request.getRealPath(dir);
        File destFile = new File(subFolder + separatorFile + fName);
        copy(srcFile, destFile);
        srcFile.deleteOnExit();
        File temp = new File(subFolder);
        if (temp.isDirectory()) {
            return subDir + separatorFile + fName;
        } else {
            return "";
        }
    }

    /**
     *
     * @param directory
     * @param fileName
     * @param request
     * @return
     */
    public static DojoJSON uploadFile(String directory, String fileName, HttpServletRequest request) {
        DojoJSON jsonDataGrid = new DojoJSON();
        try {
            List<String> lst = new ArrayList();
            Calendar cal = Calendar.getInstance();
            Long time = new Long(cal.getTimeInMillis());
            String dir = separatorFile + "share" + separatorFile + directory + separatorFile;
            String pathDir = request.getRealPath(dir);
            MultiPartRequestWrapper multi = (MultiPartRequestWrapper) request;
            Enumeration files = multi.getFileParameterNames();
            //int count = 0;
            while (files.hasMoreElements()) {
                //count++;
                String name = (String) files.nextElement();
                File file = multi.getFiles(name)[0];
                String fName = "Voffice_" + time.toString() + "_" + fileName;
                File dest = new File(pathDir + separatorFile + fName);
                copy(file, dest);
                lst.add(fName);
            }
            jsonDataGrid.setItems(lst);
        } catch (Exception e) {
            LogUtil.addLog(e);//binhnt sonar a160901
        }
        return jsonDataGrid;
    }

    /**
     *
     * @param request
     * @param fileSrc
     * @return
     */
    public static boolean moveFile(HttpServletRequest request, String fileSrc) {
        try {
            String dir = separatorFile + "share" + separatorFile;
            String pathDir = request.getRealPath(dir);

            File src = new File(pathDir + separatorFile + "temp" + separatorFile + fileSrc);

            File dest = new File(pathDir + separatorFile + "upload" + separatorFile + fileSrc);
            copy(src, dest);
            return FileUtils.delete(src);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return false;
        }
    }

    /**
     *
     * @param src
     * @param dest
     */
    public static synchronized void copy(File src, File dest) {
        try {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            LogUtil.addLog(e);//binhnt sonar a160901
        } 
         
    }

    /**
     *
     * @param request
     * @param arrFileName
     * @return
     */
    public static boolean moveFile(HttpServletRequest request, String[] arrFileName) {
        boolean flg = false;
        String dir = separatorFile + "share" + separatorFile;
        String pathDir = request.getRealPath(dir);
        for (int i = 0; i < arrFileName.length; i++) {
            File src = new File(pathDir + separatorFile + "temp" + separatorFile + arrFileName[i]);
            if (!"".equals(arrFileName[i]) && src.exists()) {
                flg = moveFile(request, arrFileName[i]);
            }
        }
        return flg;
    }

    /**
     *
     * @param fileCode
     * @param request
     * @return
     */
    public static byte[] getCustomImageInBytes(String fileCode, HttpServletRequest request) {
        byte[] imageInByte = null;
        BufferedImage originalImage;
        try {
            originalImage = ImageIO.read(getImageFile(fileCode, request));
            // convert BufferedImage to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "png", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
        } catch (IOException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return imageInByte;
    }

    private static File getImageFile(String fileCode, HttpServletRequest request) {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String filePath = rb.getString("directoryQR");
        File file = new File(filePath + separatorFile + fileCode + "_QRCode.PNG");
        return file;
    }

    /**
     *
     * @return
     */
    public static String getCustomContentType() {
        return "image/jpeg";
    }

    /**
     *
     * @return
     */
    public static String getCustomContentDisposition() {
        return "anyname.jpg";
    }
}
