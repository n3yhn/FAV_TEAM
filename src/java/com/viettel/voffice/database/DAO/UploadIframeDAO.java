/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAO;

import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.dojoTag.DojoJSON;
import com.viettel.voffice.client.form.UploadIframeForm;
import com.viettel.common.util.UploadFile;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.BO.UserAttachs;
import com.viettel.hqmc.DAOHE.FilesDAOHE;
import com.viettel.hqmc.DAOHE.UserAttachsDAOHE;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAOHibernate.VoAttachsDAOHE;
import com.viettel.vsaadmin.database.BO.Roles;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

/**
 *
 * @author cn_longh
 */
public class UploadIframeDAO extends BaseDAO {

    //[ Bean properties & methods
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UploadIframeDAO.class);
    private UploadIframeForm uploadIframe = new UploadIframeForm();
    private File myFile2;
    private DojoJSON jsonFile = new DojoJSON();
    private FileInputStream inputStream;
    private FilesForm filesForm = new FilesForm();
    private static BaseDAO baseDAO = new BaseDAO();

    public DojoJSON getJsonFile() {
        return jsonFile;
    }

    public void setJsonFile(DojoJSON jsonFile) {
        this.jsonFile = jsonFile;
    }

    public File getMyFile2() {

        return myFile2;
    }

    public void setMyFile2(File myFile2) {
        this.myFile2 = myFile2;
    }

    public UploadIframeForm getUploadIframe() {
        return uploadIframe;
    }

    public void setUploadIframe(UploadIframeForm uploadIframe) {
        this.uploadIframe = uploadIframe;
    }
    //] Bean properties & methods

    //[ Logic methods
    public String showUploadIframeScreen() {
        String result = "mainUploadIframeScreen";
        getRequest().setAttribute("imagePath", "noimage.PNG");
        return result;
    }

    public String executeUploadIframe() throws FileNotFoundException, IOException {
        HttpServletRequest request = getRequest();
//        String strType = request.getParameter("type");
        MultiPartRequestWrapper multi = (MultiPartRequestWrapper) request;
        Enumeration files = multi.getFileParameterNames();
        String fieldName;
        String fileName = "";
        while (files.hasMoreElements()) {
            fieldName = (String) files.nextElement();
//            File file = multi.getFiles(fieldName)[0];
            fileName = multi.getFileNames(fieldName)[0];
        }
        getRequest().setAttribute("fileName", fileName);
        return "uploadIframeCallbackTarget";
    }

    public static String getSafeFileName(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != '/' && c != '\\' && c != 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String uploadFile() {
        try {
            HttpServletRequest request = getRequest();
            String strId = request.getParameter("id");
            MultiPartRequestWrapper multi = (MultiPartRequestWrapper) request;
            Enumeration files = multi.getFileParameterNames();
            String fieldName;
            String fileName;
            File file;
            while (files.hasMoreElements()) {
                fieldName = (String) files.nextElement();
                file = multi.getFiles(fieldName)[0];
                fileName = multi.getFileNames(fieldName)[0];
                fileName = getSafeFileName(fileName);
                fileName = toNoneUnicode(fileName);
                char dot = fileName.charAt(0);
                if (dot == '.') {
                    return "";
                }
                if (checkFileExtension(fileName)) {
//                if (true) {
                    String filePath = UploadFile.uploadFile("temp", fileName, file, getRequest());
                    if(!"".equals(filePath)){
                        VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
                    VoAttachs bo = new VoAttachs();
                    bo.setAttachName(fileName);
                    bo.setAttachPath(filePath);
                    bo.setIsActive(1L);
                    bo.setUserCreateId(this.getUserId());
                    bo.setCreateDate(daoHe.getSysdate());

                    Long id = daoHe.create(bo);
                    getRequest().setAttribute("attachId", id);
                    getRequest().setAttribute("fileName", fileName);
                    getRequest().setAttribute("id", strId);

                    String idSession = (String) getRequest().getSession().getAttribute("idSession");
                    if (idSession == null) {
                        idSession = "";
                    }
                    idSession += id.toString() + ";";
                    getRequest().getSession().setAttribute("idSession", idSession);
                    } else{
                        return "uploadResultError";
                    }
                    

                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "uploadResultPage";
    }

    //hieptq update 111214
    public String uploadFilePdf() {
        try {
            HttpServletRequest request = getRequest();
            String strId = request.getParameter("id");
            MultiPartRequestWrapper multi = (MultiPartRequestWrapper) request;
            Enumeration files = multi.getFileParameterNames();
            String fieldName;
            String fileName;
            File file;
            while (files.hasMoreElements()) {
                fieldName = (String) files.nextElement();
                file = multi.getFiles(fieldName)[0];
                fileName = multi.getFileNames(fieldName)[0];
                fileName = getSafeFileName(fileName);
                fileName = toNoneUnicode(fileName);
                char dot = fileName.charAt(0);
                if (dot == '.') {
                    return "";
                }
                if (checkFileExtension(fileName)) {
//                if (true) {
                    String filePath = UploadFile.uploadFilePdf("temp", fileName, file, getRequest());
                    VoAttachs bo = new VoAttachs();
                    bo.setAttachName(fileName);
                    bo.setAttachPath(filePath);
                    bo.setIsActive(1L);
                    VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
                    bo.setUserCreateId(this.getUserId());
                    bo.setCreateDate(daoHe.getSysdate());
                    Long id = daoHe.create(bo);
                    getRequest().setAttribute("attachId", id);
                    getRequest().setAttribute("fileName", fileName);
                    getRequest().setAttribute("id", strId);

                    String idSession = (String) getRequest().getSession().getAttribute("idSession");
                    if (idSession == null) {
                        idSession = "";
                    }
                    idSession += id.toString() + ";";
                    getRequest().getSession().setAttribute("idSession", idSession);

                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            LOG.error("Error upload", ex);
        }
        return "uploadResultPage";
    }

    // upload pdf hieptq vt 121214
    public String uploadFilePdfVT() {
        try {
            HttpServletRequest request = getRequest();
            String strId = request.getParameter("id");
            MultiPartRequestWrapper multi = (MultiPartRequestWrapper) request;
            Enumeration files = multi.getFileParameterNames();
            String fieldName;
            String fileName;
            File file;
            while (files.hasMoreElements()) {
                fieldName = (String) files.nextElement();
                file = multi.getFiles(fieldName)[0];
                fileName = multi.getFileNames(fieldName)[0];
                fileName = getSafeFileName(fileName);
                fileName = toNoneUnicode(fileName);
                char dot = fileName.charAt(0);
                if (dot == '.') {
                    return "";
                }
                if (checkFileExtension(fileName)) {
//                if (true) {
                    String filePath = UploadFile.uploadFilePdfVT("temp", fileName, file, getRequest());
                    VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
                    VoAttachs bo = new VoAttachs();
                    bo.setAttachName(fileName);
                    bo.setAttachPath(filePath);
                    bo.setIsActive(1L);
                    bo.setUserCreateId(this.getUserId());
                    bo.setCreateDate(getSysdate());

                    Long id = daoHe.create(bo);
                    getRequest().setAttribute("attachId", id);
                    getRequest().setAttribute("fileName", fileName);
                    getRequest().setAttribute("id", strId);

                    String idSession = (String) getRequest().getSession().getAttribute("idSession");
                    if (idSession == null) {
                        idSession = "";
                    }
                    idSession += id.toString() + ";";
                    getRequest().getSession().setAttribute("idSession", idSession);

                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "uploadResultPage";
    }

    //upload file excel
    public String uploadFileImp() {
        try {
            HttpServletRequest request = getRequest();
            String strId = request.getParameter("id");
            MultiPartRequestWrapper multi = (MultiPartRequestWrapper) request;
            Enumeration files = multi.getFileParameterNames();
            String fieldName;
            String fileName;
            File file;
            while (files.hasMoreElements()) {
                fieldName = (String) files.nextElement();
                file = multi.getFiles(fieldName)[0];
                fileName = multi.getFileNames(fieldName)[0];
                fileName = getSafeFileName(fileName);
                fileName = toNoneUnicode(fileName);
                if (checkFileExtension(fileName)) {
//                if (true) {
                    VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
                    String filePath = UploadFile.uploadFileExcel("temp", fileName, file, getRequest());
                    VoAttachs bo = new VoAttachs();
                    bo.setAttachName(fileName);
                    bo.setAttachPath(filePath);
                    bo.setIsActive(1L);
                    bo.setUserCreateId(this.getUserId());
                    bo.setCreateDate(daoHe.getSysdate());

                    Long id = daoHe.create(bo);
                    getRequest().setAttribute("attachId", id);
                    getRequest().setAttribute("fileName", fileName);
                    getRequest().setAttribute("id", strId);

                    String idSession = (String) getRequest().getSession().getAttribute("idSession");
                    if (idSession == null) {
                        idSession = "";
                    }
                    idSession += id.toString() + ";";
                    getRequest().getSession().setAttribute("idSession", idSession);

                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "uploadResultPageImp";
    }

    public String uploadFileSh() {
        try {
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            HttpServletRequest request = getRequest();
            MultiPartRequestWrapper multi = (MultiPartRequestWrapper) request;
            Enumeration files = multi.getFileParameterNames();
            String strId = request.getParameter("id");
            String fieldName;
            String fileName;
            File file;
            while (files.hasMoreElements()) {
                fieldName = (String) files.nextElement();
                file = multi.getFiles(fieldName)[0];
                fileName = multi.getFileNames(fieldName)[0];
                fileName = getSafeFileName(fileName);
                fileName = toNoneUnicode(fileName);
                if (checkFileExtension(fileName)) {
//                if (true) {
                    String filePath = UploadFile.uploadFile("temp", fileName, file, getRequest());
                    VoAttachs bo = new VoAttachs();
                    bo.setAttachName(fileName);
                    bo.setAttachPath(filePath);
                    bo.setIsActive(1L);
                    bo.setUserCreateId(this.getUserId());
                    bo.setCreateDate(daoHe.getSysdate());

                    Long id = daoHe.create(bo);
                    getRequest().setAttribute("attachId", id);
                    getRequest().setAttribute("fileName", fileName);
                    getRequest().setAttribute("id", strId);
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "uploadResultPageSh";
    }

    public String openFile() {
        String linkFile = "";
        boolean noError = false;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String dir = rb.getString("directory");
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            Long id = Long.parseLong(getRequest().getParameter("attachId"));

            String idSession = (String) getRequest().getSession().getAttribute("idSession");

            if (idSession != null) {
                String[] ids = idSession.split(";");
                for (int i = 0; i < ids.length; i++) {
                    if (ids[i].equals(String.valueOf(id))) {
                        try {
                            VoAttachs bo = daoHe.findById(id, false);
                            linkFile = bo.getAttachPath();
                            linkFile = dir + linkFile;
                            File file = new File(linkFile);
                            inputStream = new FileInputStream(file);
                            HttpServletResponse response = getResponse();
                            response.setHeader("Cache-Control", "no-cache");
                            response.setHeader("Content-Disposition", "attachment; filename=\"" + bo.getAttachName() + "\"");
                            response.flushBuffer();
                            noError = true;
                            break;
                        } catch (Exception ex) {
                            LogUtil.addLog(ex);//binhnt sonar a160901
                        }
                    }
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (noError) {
            return "download";
        } else {
            return "errorNoFile";
        }
    }

    public String openFileForPortal() {
        String linkFile = "";
        boolean noError = false;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String dir = rb.getString("directory");
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            Long id = Long.parseLong(getRequest().getParameter("attachId"));
            VoAttachs bo = daoHe.findById(id, false);
            linkFile = bo.getAttachPath();
            linkFile = getSafeFileName(dir + linkFile);
            File file = new File(linkFile);
            inputStream = new FileInputStream(file);
            HttpServletResponse response = getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + bo.getAttachName() + "\"");
            response.flushBuffer();
            noError = true;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (noError) {
            return "download";
        } else {
            return "errorNoFile";
        }
    }

    /*
     * DiuLTT - mở file version cũ gồm cả file có isActive = 0;
     */
 /*  public String openOldFile() {
     String linkFile = "";
     try {

     ResourceBundle rb = ResourceBundle.getBundle("config");
     String dir = rb.getString("directory");
     VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
     Long id = Long.parseLong(getRequest().getParameter("attachId"));
     //get attach by Id (ke ca attach da bi xoa (isActive = 0)
     VoAttachs bo = daoHe.getAttById(id);
     linkFile = bo.getAttachPath();
     linkFile = dir + linkFile;

     File file = new File(linkFile);
     inputStream = new FileInputStream(file);
     HttpServletResponse response = getResponse();
     response.setHeader("Cache-Control", "no-cache");
     response.setHeader("Content-Disposition", "attachment; filename=\"" + bo.getAttachName() + "\"");
     response.flushBuffer();
     } catch (Exception ex) {
     log.error(ex.getMessage());
     }
     return "download";
     }*/
    public void removeFile() {
        try {
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            Long id = Long.parseLong(getRequest().getParameter("attachId"));
            VoAttachs bo = daoHe.findById(id, false);
            bo.setIsActive(0L);
            daoHe.update(bo);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
    }

    private boolean checkFileExtension(String fileName) {
        String[] allows = {"zip", "rar", "doc", "pdf", "jpg", "png", "gif", "jpeg", "bmp", "docx", "xls", "xlsx", "odt"};
        String ext = "";
        try {
            int mid = fileName.lastIndexOf(".");
            ext = fileName.substring(mid + 1, fileName.length()).toLowerCase();
            if (Arrays.asList(allows).contains(ext)) {
                return true;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return false;
    }

    public String getAttachFile() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        try {
            List ids = new ArrayList();
            List names = new ArrayList();
            List idSHs = new ArrayList();
            Long objectId = Long.parseLong(getRequest().getParameter("objectId"));
            Long objectType = Long.parseLong(getRequest().getParameter("objectType"));
            String id = String.valueOf(getRequest().getParameter("id"));

            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            List<VoAttachs> lst = daoHe.getAttachsByObject(objectId, objectType);
            String idSession = (String) getRequest().getSession().getAttribute("idSession");
            if (idSession == null) {
                idSession = "";
            }
            for (VoAttachs bo : lst) {
                ids.add(bo.getAttachId());
                names.add(bo.getAttachName());
                idSession += bo.getAttachId().toString();
                idSession += ";";
            }
            getRequest().getSession().setAttribute("idSession", idSession);
            resultMessage.add(ids);
            resultMessage.add(names);
            resultMessage.add(idSHs);
            customInfo.add(id);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        jsonFile.setItems(resultMessage);
        jsonFile.setCustomInfo(customInfo);
        return "jsonFile";
    }

    public String getAttachFileForPortal() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        try {
            List ids = new ArrayList();
            List names = new ArrayList();
            List idSHs = new ArrayList();
            Long objectId = Long.parseLong(getRequest().getParameter("objectId"));
            Long objectType = Long.parseLong(getRequest().getParameter("objectType"));
            String id = String.valueOf(getRequest().getParameter("id"));

            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            List<VoAttachs> lst = daoHe.getAttachsByObject(objectId, objectType);
            for (VoAttachs bo : lst) {
                ids.add(bo.getAttachId());
                names.add(bo.getAttachName());
            }
            resultMessage.add(ids);
            resultMessage.add(names);
            resultMessage.add(idSHs);
            customInfo.add(id);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        jsonFile.setItems(resultMessage);
        jsonFile.setCustomInfo(customInfo);
        return "jsonFile";
    }

    public String getAttachFileSh() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        try {
            List ids = new ArrayList();
            List names = new ArrayList();
            List idSHs = new ArrayList();
            String attachIds = getRequest().getParameter("attachIds");
            String[] attLst = attachIds.split(";");
            String id = String.valueOf(getRequest().getParameter("id"));

            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            String idSession = (String) getRequest().getSession().getAttribute("idSession");
            if (idSession == null) {
                idSession = "";
            }
            for (int ind = 0; ind < attLst.length; ind++) {
                if (attLst[ind] != null && !"".equals(attLst[ind])) {
                    VoAttachs bo = daoHe.getAttById(Long.valueOf(attLst[ind]));
                    if (bo != null) {
                        ids.add(bo.getAttachId());
                        names.add(bo.getAttachName());
                        idSession += bo.getAttachId().toString();
                        idSession += ";";
                    }
                }
            }
            getRequest().getSession().setAttribute("idSession", idSession);
            resultMessage.add(ids);
            resultMessage.add(names);
            resultMessage.add(idSHs);
            customInfo.add(id);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        jsonFile.setItems(resultMessage);
        jsonFile.setCustomInfo(customInfo);
        return "jsonFile";
    }

    public String getFile() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        try {
            List ids = new ArrayList();
            List names = new ArrayList();
            Long objectId = Long.parseLong(getRequest().getParameter("objectId"));
            Long objectType = Long.parseLong(getRequest().getParameter("objectType"));
            String id = String.valueOf(getRequest().getParameter("id"));

            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            List<VoAttachs> lst = daoHe.getRelationAttachs(objectId, objectType);
            String idSession = (String) getRequest().getSession().getAttribute("idSession");
            if (idSession == null) {
                idSession = "";
            }
            for (VoAttachs bo : lst) {
                ids.add(bo.getAttachId());
                names.add(bo.getAttachName());
                idSession += bo.getAttachId().toString();
                idSession += ";";
            }
            getRequest().getSession().setAttribute("idSession", idSession);
            resultMessage.add(ids);
            resultMessage.add(names);
            customInfo.add(id);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        jsonFile.setItems(resultMessage);
        jsonFile.setCustomInfo(customInfo);
        return "jsonFile";
    }
    private static char[] SPECIAL_CHARACTERS = {' ', '!', '"', '#', '$', '%',
        '*', '+', ',', ':', '<', '=', '>', '?', '@', '[', '\\', ']', '^',
        '`', '|', '~', 'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò',
        'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê',
        'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă', 'Đ', 'đ',
        'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ',
        'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ', 'Ắ', 'ắ', 'Ằ', 'ằ',
        'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế',
        'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị',
        'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ', 'ổ', 'Ỗ', 'ỗ', 'Ộ',
        'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ',
        'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự',
        'Ý', 'Ỷ', 'Ỵ', 'Ỳ', 'ý', 'ỷ', 'ỵ', 'ỳ',};
    private static char[] REPLACEMENTS = {'-', '\0', '\0', '\0', '\0', '\0',
        '\0', '_', '\0', '_', '\0', '\0', '\0', '\0', '\0', '\0', '_',
        '\0', '\0', '\0', '\0', '\0', 'A', 'A', 'A', 'A', 'E', 'E', 'E',
        'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a',
        'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A',
        'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a',
        'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
        'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e',
        'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I',
        'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
        'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
        'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
        'U', 'u',
        'Y', 'Y', 'Y', 'Y', 'y', 'y', 'y', 'y',};

    public static String toNoneUnicode(String s) {
        s = baseDAO.convertVietNameseToNoSign(s);
        int maxLength = Math.min(s.length(), 236);
        char[] buffer = new char[maxLength];
        int n = 0;
        for (int i = 0; i < maxLength; i++) {
            char ch = s.charAt(i);
            buffer[n] = removeAccent(ch);
            // skip not printable characters
            if (buffer[n] > 31) {
                n++;
            }
        }
        // skip trailing slashes
        while (n > 0 && buffer[n - 1] == '/') {
            n--;
        }
        return String.valueOf(buffer, 0, n);
    }

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SPECIAL_CHARACTERS, ch);
        if (index >= 0) {
            ch = REPLACEMENTS[index];
        }
        return ch;
    }

    public static String removeAccent(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }

    public FileInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(FileInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void getBarcodeImg() {
//        try {
        Long fileId = filesForm.getFileId();
        HttpServletRequest request = getRequest();
//        HttpServletResponse response = getResponse();
        FilesDAOHE filesdaohe = new FilesDAOHE();
        Files filesbo = filesdaohe.findById(fileId);
//            response.setContentType(UploadFile.getCustomContentType());
//            response.getOutputStream().write(UploadFile.getCustomImageInBytes(filesbo.getFileCode(), request));
//            response.getOutputStream().flush();

        StringBuilder sb = new StringBuilder();
        sb.append("data:image/png;base64,");
        sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(UploadFile.getCustomImageInBytes(filesbo.getFileCode(), request), false)));
        getRequest().setAttribute("imgsrc", sb.toString());
//        } catch (IOException ex) {
//            Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public FilesForm getFilesForm() {
        return filesForm;
    }

    public void setFilesForm(FilesForm filesForm) {
        this.filesForm = filesForm;
    }
//140611 binhnt53

    public String getAttachFileUserAttachs() {
        List resultMessage = new ArrayList();
        List customInfo = new ArrayList();
        try {
            List ids = new ArrayList();
            List names = new ArrayList();
            List idSHs = new ArrayList();
            Long objectId = Long.parseLong(getRequest().getParameter("objectId"));
            String id = String.valueOf(getRequest().getParameter("id"));

            UserAttachsDAOHE daoHe = new UserAttachsDAOHE();
            List<UserAttachs> lst = daoHe.getAttachsByObject(objectId);
            String idSession = (String) getRequest().getSession().getAttribute("idSession");
            if (idSession == null) {
                idSession = "";
            }
            for (UserAttachs bo : lst) {
                ids.add(bo.getUserAttachsId());
                names.add(bo.getFileName());
                idSession += bo.getUserAttachsId().toString();
                idSession += ";";
            }
            getRequest().getSession().setAttribute("idSession", idSession);
            resultMessage.add(ids);
            resultMessage.add(names);
            resultMessage.add(idSHs);
            customInfo.add(id);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        jsonFile.setItems(resultMessage);
        jsonFile.setCustomInfo(customInfo);
        return "jsonFile";
    }

    public String openFileUserAttach() {
        String linkFile = "";
        boolean noError = false;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String dir = rb.getString("directory");
            UserAttachsDAOHE daoHe = new UserAttachsDAOHE();
            Long id = Long.parseLong(getRequest().getParameter("attachId"));

            String idSession = (String) getRequest().getSession().getAttribute("idSession");

            if (idSession != null) {
                String[] ids = idSession.split(";");
                for (int i = 0; i < ids.length; i++) {
                    if (ids[i].equals(String.valueOf(id))) {
                        try {
                            UserAttachs bo = daoHe.findById(id, false);
                            linkFile = bo.getAttachPath();
                            linkFile = dir + linkFile;
                            File file = new File(linkFile);
                            inputStream = new FileInputStream(file);
                            HttpServletResponse response = getResponse();
                            response.setHeader("Cache-Control", "no-cache");
                            response.setHeader("Content-Disposition", "attachment; filename=\"" + bo.getAttachName() + "\"");
                            response.flushBuffer();
                            noError = true;
                            break;
                        } catch (Exception ex) {
                            LogUtil.addLog(ex);//binhnt sonar a160901
                        }
                    }
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (noError) {
            return "download";
        } else {
            return "errorNoFile";
        }
    }
    //!binhnt53

    public String openFileUserAttachPdf() {
        String linkFile = "";
        boolean noError = false;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String dir = rb.getString("directory");
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            Long id = Long.parseLong(getRequest().getParameter("fileId"));
            try {
                VoAttachs bo = daoHe.getLstVoAttachByFilesId(id, "PDHS");
                //Hiepvv 1403 Download SDBS sau cong bo
                if (!bo.getAttachName().startsWith("Bancongbo_VT") && !bo.getAttachName().startsWith("Bancongbo_LD")
                        && !bo.getAttachName().startsWith("CongvanSDBSsaucongbo_VT")) {
                    return "errorNoFile";
                }
                linkFile = bo.getAttachPath();
                linkFile = dir + linkFile;
                File file = new File(linkFile);
                inputStream = new FileInputStream(file);
                HttpServletResponse response = getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + bo.getAttachName() + "\"");
                response.flushBuffer();
                noError = true;
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (noError) {
            return "download";
        } else {
            return "errorNoFile";
        }
    }

    /**
     * open file lanh dao cuc da ki phe duyet binhnt53
     *
     * @return
     */
    public String openFileLDCSignApprove() {
        String linkFile = "";
        boolean noError = false;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String dir = rb.getString("directory");
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            Long id = Long.parseLong(getRequest().getParameter("fileId"));
            try {
                VoAttachs bo = daoHe.getLstVoAttachByFilesId(id, "PDHS");
                if (!bo.getAttachName().startsWith("Bancongbo_LD")) {
                    return "errorNoFile";
                }
                linkFile = bo.getAttachPath();
                linkFile = dir + linkFile;
                File file = new File(linkFile);
                inputStream = new FileInputStream(file);
                HttpServletResponse response = getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + bo.getAttachName() + "\"");
                response.flushBuffer();
                noError = true;
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (noError) {
            return "download";
        } else {
            return "errorNoFile";
        }
    }

    public String openFileLDCSignApproveForAA() {
        String linkFile = "";
        boolean noError = false;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String dir = rb.getString("directory");
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            Long id = Long.parseLong(getRequest().getParameter("fileId"));
            try {
                VoAttachs bo = daoHe.getLstVoAttachByFilesIdForAA(id, "PDHS");
                if (!bo.getAttachName().startsWith("CongvanSDBSsaucongbo_LD")) {
                    return "errorNoFile";
                }
                linkFile = bo.getAttachPath();
                linkFile = dir + linkFile;
                File file = new File(linkFile);
                inputStream = new FileInputStream(file);
                HttpServletResponse response = getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + bo.getAttachName() + "\"");
                response.flushBuffer();
                noError = true;
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (noError) {
            return "download";
        } else {
            return "errorNoFile";
        }
    }

    public String openFileSignPublic() {
        String linkFile = "";
        boolean noError = false;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String dir = rb.getString("directory");
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            Long id = Long.parseLong(getRequest().getParameter("fileId"));
            try {
                VoAttachs bo = daoHe.getLstVoAttachByFilesId(id, "PDHS_PUBLIC");
                if (!bo.getAttachName().startsWith("Bancongbo_VT")) {
                    return "errorNoFile";
                }
                linkFile = bo.getAttachPath();
                linkFile = dir + linkFile;
                File file = new File(linkFile);
                inputStream = new FileInputStream(file);
                HttpServletResponse response = getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + bo.getAttachName() + "\"");
                response.flushBuffer();
                noError = true;
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (noError) {
            return "download";
        } else {
            return "errorNoFile";
        }
    }

    public String openFileUserAttachPdfCVBS() {
        String linkFile = "";
        boolean noError = false;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String dir = rb.getString("directory");
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            Long id = Long.parseLong(getRequest().getParameter("fileIdf"));
            try {
                VoAttachs bo = daoHe.getLstVoAttachByFilesId(id, "CVBS");

                linkFile = bo.getAttachPath();
                linkFile = dir + linkFile;
                File file = new File(linkFile);
                inputStream = new FileInputStream(file);
                HttpServletResponse response = getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + bo.getAttachName() + "\"");
                response.flushBuffer();
                noError = true;
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (noError) {
            return "download";
        } else {
            return "errorNoFile";
        }
    }

    public String openFileUserAttachPdfCVBSold() {//141215u binhnt53
        String linkFile = "";
        boolean noError = false;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String dir = rb.getString("directory");
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            Long id = Long.parseLong(getRequest().getParameter("attachId"));
            try {
                VoAttachs bo = daoHe.findById(id, false);
                if (bo != null && bo.getObjectType() == 71L) {
                    linkFile = bo.getAttachPath();
                    linkFile = dir + linkFile;
                    File file = new File(linkFile);
                    inputStream = new FileInputStream(file);
                    HttpServletResponse response = getResponse();
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + bo.getAttachName() + "\"");
                    response.flushBuffer();
                    noError = true;
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (noError) {
            return "download";
        } else {
            return "errorNoFile";
        }
    }

    public String openFileIsChanged() {//141215u binhnt53
        String linkFile = "";
        boolean noError = false;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String dir = rb.getString("directory");
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            Long id = Long.parseLong(getRequest().getParameter("attachId"));
            try {
                VoAttachs bo = daoHe.findById(id, false);
                if (bo != null && bo.getIsActive() == -1L) {
                    linkFile = bo.getAttachPath();
                    linkFile = dir + linkFile;
                    File file = new File(linkFile);
                    inputStream = new FileInputStream(file);
                    HttpServletResponse response = getResponse();
                    response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + bo.getAttachName() + "\"");
                    response.flushBuffer();
                    noError = true;
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (noError) {
            return "download";
        } else {
            return "errorNoFile";
        }
    }

    public String openFileUserAttachPdfSign() {
        List resultMessage = new ArrayList();
        String fullPath = "";
        String fullName = "";
        String fileName0 = "";
        String filePath0 = "";
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            Long fileId = Long.parseLong(getRequest().getParameter("fileId"));
            String signType = getRequest().getParameter("signType");
            String pathSaveFile = rb.getString("file_sign_link");
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            List<VoAttachs> lstBo = daoHe.getLstVoAttachSignByFilesId(fileId, signType);
            String dir = rb.getString("directory");

            if (lstBo == null || lstBo.isEmpty()) {
                Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, "Quá trình xử lý file ký Văn thư không thành công: File ký không tồn tại");
                resultMessage.add("error");
                jsonFile.setItems(resultMessage);
                return "jsonFile";
            }

            // Get user info
            Long userId = getUserId();
            List<Roles> lstRole = getListRolesByUser();
            String code = "";
            if (lstRole != null && lstRole.size() > 0) {
                for (Integer i = 0; i < lstRole.size(); i++) {
                    code = lstRole.get(i).getRoleCode();
                    if (!"".equals(code) && ("voffice_vtb".equals(code)
                            || code.equals(Constants.ROLES.LEAD_MONITOR_ROLE)
                            || code.equals(Constants.ROLES.DIRECTOR_ROLE))) {
                        if ("voffice_vtb".equals(code)) {
                            code = "VT";
                        } else if (code.equals(Constants.ROLES.LEAD_MONITOR_ROLE)
                                || code.equals(Constants.ROLES.DIRECTOR_ROLE)) {
                            code = "LD";
                        }
                        break;
                    } else {
                        code = "";
                    }
                }
            } else {
                Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, "Quá trình xử lý file ký Văn thư không thành công: Không xác định được vai trò User đăng nhập");
                resultMessage.add("error");
                jsonFile.setItems(resultMessage);
                return "jsonFile";
            }

            if ("".equals(code)) {
                Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, "Quá trình xử lý file ký Văn thư không thành công: Không xác định được vai trò User đăng nhập");
                resultMessage.add("error");
                jsonFile.setItems(resultMessage);
                return "jsonFile";
            }
            Boolean flagSave = false;
            Date dateId = new Date();
            Long time = dateId.getTime();
            for (int i = 0; i < lstBo.size(); i++) {
                flagSave = false;
                if (!lstBo.get(i).getAttachName().contains("LD")) {
                    continue;
                }
                String fileName;
                if (lstBo.get(i).getObjectType().equals(40L)) {
                    fileName = pathSaveFile + code + "_" + userId.toString() + "_" + fileId.toString() + "_" + time + "_" + signType + "_1.pdf";
                    filePath0 = pathSaveFile + code + "_" + userId.toString() + "_" + fileId.toString() + "_" + time + "_" + signType + "_2.pdf";
                    fileName0 = code + "_" + userId.toString() + "_" + fileId.toString() + "_" + time + "_" + signType + "_2.pdf";
                } else if (lstBo.get(i).getObjectType().equals(41L)) {
                    fileName = pathSaveFile + code + "_" + userId.toString() + "_" + fileId.toString() + "_" + time + "_" + signType + "_2.pdf";
                } else {
                    fileName = pathSaveFile + code + "_" + userId.toString() + "_" + fileId.toString() + "_" + time + "_" + signType + ".pdf";
                }
                File fileRead = new File(dir + lstBo.get(i).getAttachPath());
                byte[] pdfByte = read(fileRead);
                // Check file exist
                Path path = Paths.get(fileName);
                File file = new File(fileName);
                if (java.nio.file.Files.exists(path)) {
                    file.delete();
                }
                try (FileOutputStream fop = new FileOutputStream(file)) {
                    fop.write(pdfByte);
                    fop.flush();
                }
                fullPath = file.getPath();
                fullName = file.getName();
                flagSave = true;
                if (!lstBo.get(i).getObjectType().equals(40L) && !lstBo.get(i).getObjectType().equals(41L)) {
                    // Read and save only file
                    break;
                }
            }
            if (!flagSave) {
                Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, "Quá trình xử lý file ký Văn thư không thành công: Lưu file ký không thành công");
                resultMessage.add("error");
                jsonFile.setItems(resultMessage);
                return "jsonFile";
            }
            resultMessage.add("success");
            resultMessage.add(fullPath + ";" + fullName + ";" + filePath0 + ";" + fileName0);
        } catch (NumberFormatException | IOException ex) {
//            Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, ex);
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("error");
        }
        jsonFile.setItems(resultMessage);
        return "jsonFile";
    }

    public String openFileUserAttachPdfSignForAA() {
        List resultMessage = new ArrayList();
        String fullPath = "";
        String fullName = "";
        String fileName0 = "";
        String filePath0 = "";
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            Long fileId = Long.parseLong(getRequest().getParameter("fileId"));
            String signType = getRequest().getParameter("signType");
            String pathSaveFile = rb.getString("file_sign_link");
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            List<VoAttachs> lstBo = daoHe.getLstVoAttachSignByFilesId(fileId, signType);
            String dir = rb.getString("directory");

            if (lstBo == null || lstBo.isEmpty()) {
                Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, "Quá trình xử lý file ký Văn thư không thành công: File ký không tồn tại");
                resultMessage.add("error");
                jsonFile.setItems(resultMessage);
                return "jsonFile";
            }

            // Get user info
            Long userId = getUserId();
            List<Roles> lstRole = getListRolesByUser();
            String code = "";
            if (lstRole != null && lstRole.size() > 0) {
                for (Integer i = 0; i < lstRole.size(); i++) {
                    code = lstRole.get(i).getRoleCode();
                    if (!"".equals(code) && ("voffice_vtb".equals(code)
                            || code.equals(Constants.ROLES.LEAD_MONITOR_ROLE)
                            || code.equals(Constants.ROLES.DIRECTOR_ROLE))) {
                        if ("voffice_vtb".equals(code)) {
                            code = "VT";
                        } else if (code.equals(Constants.ROLES.LEAD_MONITOR_ROLE)
                                || code.equals(Constants.ROLES.DIRECTOR_ROLE)) {
                            code = "LD";
                        }
                        break;
                    } else {
                        code = "";
                    }
                }
            } else {
                Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, "Quá trình xử lý file ký Văn thư không thành công: Không xác định được vai trò User đăng nhập");
                resultMessage.add("error");
                jsonFile.setItems(resultMessage);
                return "jsonFile";
            }

            if ("".equals(code)) {
                Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, "Quá trình xử lý file ký Văn thư không thành công: Không xác định được vai trò User đăng nhập");
                resultMessage.add("error");
                jsonFile.setItems(resultMessage);
                return "jsonFile";
            }
            Boolean flagSave = false;
            Date dateId = new Date();
            Long time = dateId.getTime();
            for (int i = 0; i < lstBo.size(); i++) {
                flagSave = false;
                if (!lstBo.get(i).getAttachName().contains("LD")) {
                    continue;
                }
                String fileName;
                if (lstBo.get(i).getObjectType().equals(40L)) {
                    fileName = pathSaveFile + code + "_" + userId.toString() + "_" + fileId.toString() + "_" + time + "_" + signType + "_1.pdf";
                    filePath0 = pathSaveFile + code + "_" + userId.toString() + "_" + fileId.toString() + "_" + time + "_" + signType + "_2.pdf";
                    fileName0 = code + "_" + userId.toString() + "_" + fileId.toString() + "_" + time + "_" + signType + "_2.pdf";
                } else if (lstBo.get(i).getObjectType().equals(41L)) {
                    fileName = pathSaveFile + code + "_" + userId.toString() + "_" + fileId.toString() + "_" + time + "_" + signType + "_2.pdf";
                } else {
                    fileName = pathSaveFile + code + "_" + userId.toString() + "_" + fileId.toString() + "_" + time + "_" + signType + ".pdf";
                }
                File fileRead = new File(dir + lstBo.get(i).getAttachPath());
                if (fileRead.getPath().contains("PDHS_2")) {
                    continue;
                }
                byte[] pdfByte = read(fileRead);
                // Check file exist
                Path path = Paths.get(fileName);
                File file = new File(fileName);
                if (java.nio.file.Files.exists(path)) {
                    file.delete();
                }
                try (FileOutputStream fop = new FileOutputStream(file)) {
                    fop.write(pdfByte);
                    fop.flush();
                }
                fullPath = file.getPath();
                fullName = file.getName();
                flagSave = true;
                if (!lstBo.get(i).getObjectType().equals(40L) && !lstBo.get(i).getObjectType().equals(41L)) {
                    // Read and save only file
                    break;
                }
            }
            if (!flagSave) {
                Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, "Quá trình xử lý file ký Văn thư không thành công: Lưu file ký không thành công");
                resultMessage.add("error");
                jsonFile.setItems(resultMessage);
                return "jsonFile";
            }
            resultMessage.add("success");
            resultMessage.add(fullPath + ";" + fullName + ";" + filePath0 + ";" + fileName0);
        } catch (NumberFormatException | IOException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, ex);
            resultMessage.add("error");
        }
        jsonFile.setItems(resultMessage);
        return "jsonFile";
    }

    public String openFileUserUpload() {
        String linkFile = "";
        boolean noError = false;
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String dir = rb.getString("directory");
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            Long id = Long.parseLong(getRequest().getParameter("fileId"));
            try {
                VoAttachs bo = daoHe.getLstVoAttachUploadByFilesId(id);
                linkFile = bo.getAttachPath();
                linkFile = dir + linkFile;
                File file = new File(linkFile);
                inputStream = new FileInputStream(file);
                HttpServletResponse response = getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + bo.getAttachName() + "\"");
                response.flushBuffer();
                noError = true;
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (noError) {
            return "download";
        } else {
            return "errorNoFile";
        }
    }

    public byte[] read(File file) throws IOException {
        ByteArrayOutputStream ous = null;
        InputStream ios = null;
        try {
            byte[] buffer = new byte[4096];
            ous = new ByteArrayOutputStream();
            ios = new FileInputStream(file);
            int read = 0;
            while ((read = ios.read(buffer)) != -1) {
                ous.write(buffer, 0, read);
            }
        } finally {
            try {
                if (ous != null) {
                    ous.close();
                }
            } catch (IOException ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, e);
            }

            try {
                if (ios != null) {
                    ios.close();
                }
            } catch (IOException ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
//                Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return ous.toByteArray();
    }

    public String openFileUserAttachPdfSignAA() {
        List resultMessage = new ArrayList();
        String fullPath = "";
        String fullName = "";
        String fileName0 = "";
        String filePath0 = "";
        try {
            ResourceBundle rb = ResourceBundle.getBundle("config");
            Long fileId = Long.parseLong(getRequest().getParameter("fileId"));
            String signType = getRequest().getParameter("signType");
            String pathSaveFile = rb.getString("file_sign_link");
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            List<VoAttachs> lstBo = daoHe.getLstVoAttachSignByFilesId(fileId, signType);
            String dir = rb.getString("directory");

            if (lstBo == null || lstBo.isEmpty()) {
                Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, "Quá trình xử lý file ký Văn thư không thành công: File ký không tồn tại");
                resultMessage.add("error");
                jsonFile.setItems(resultMessage);
                return "jsonFile";
            }

            // Get user info
            Long userId = getUserId();
            List<Roles> lstRole = getListRolesByUser();
            String code = "";
            if (lstRole != null && lstRole.size() > 0) {
                for (Integer i = 0; i < lstRole.size(); i++) {
                    code = lstRole.get(i).getRoleCode();
                    if (!"".equals(code) && ("voffice_vtb".equals(code)
                            || code.equals(Constants.ROLES.LEAD_MONITOR_ROLE)
                            || code.equals(Constants.ROLES.DIRECTOR_ROLE))) {
                        if ("voffice_vtb".equals(code)) {
                            code = "VT";
                        } else if (code.equals(Constants.ROLES.LEAD_MONITOR_ROLE)
                                || code.equals(Constants.ROLES.DIRECTOR_ROLE)) {
                            code = "LD";
                        }
                        break;
                    } else {
                        code = "";
                    }
                }
            } else {
                Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, "Quá trình xử lý file ký Văn thư không thành công: Không xác định được vai trò User đăng nhập");
                resultMessage.add("error");
                jsonFile.setItems(resultMessage);
                return "jsonFile";
            }

            if ("".equals(code)) {
                Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, "Quá trình xử lý file ký Văn thư không thành công: Không xác định được vai trò User đăng nhập");
                resultMessage.add("error");
                jsonFile.setItems(resultMessage);
                return "jsonFile";
            }
            Boolean flagSave = false;
            Date dateId = new Date();
            Long time = dateId.getTime();
            for (int i = 0; i < lstBo.size(); i++) {
                flagSave = false;
                if (!lstBo.get(i).getAttachName().contains("LD")) {
                    continue;
                }
                String fileName;
                if (lstBo.get(i).getObjectType().equals(40L)) {
                    fileName = pathSaveFile + code + "_" + userId.toString() + "_" + fileId.toString() + "_" + time + "_" + signType + "_1.pdf";
                    filePath0 = pathSaveFile + code + "_" + userId.toString() + "_" + fileId.toString() + "_" + time + "_" + signType + "_2.pdf";
                    fileName0 = code + "_" + userId.toString() + "_" + fileId.toString() + "_" + time + "_" + signType + "_2.pdf";
                } else if (lstBo.get(i).getObjectType().equals(41L)) {
                    fileName = pathSaveFile + code + "_" + userId.toString() + "_" + fileId.toString() + "_" + time + "_" + signType + "_2.pdf";
                } else {
                    fileName = pathSaveFile + code + "_" + userId.toString() + "_" + fileId.toString() + "_" + time + "_" + signType + ".pdf";
                }
                File fileRead = new File(dir + lstBo.get(i).getAttachPath());
                byte[] pdfByte = read(fileRead);
                // Check file exist
                Path path = Paths.get(fileName);
                File file = new File(fileName);
                if (java.nio.file.Files.exists(path)) {
                    file.delete();
                }
                try (FileOutputStream fop = new FileOutputStream(file)) {
                    fop.write(pdfByte);
                    fop.flush();
                }
                fullPath = file.getPath();
                fullName = file.getName();
                flagSave = true;
                if (!lstBo.get(i).getObjectType().equals(40L) && !lstBo.get(i).getObjectType().equals(41L)) {
                    // Read and save only file
                    break;
                }
            }
            if (!flagSave) {
                Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, "Quá trình xử lý file ký Văn thư không thành công: Lưu file ký không thành công");
                resultMessage.add("error");
                jsonFile.setItems(resultMessage);
                return "jsonFile";
            }
            resultMessage.add("success");
            resultMessage.add(fullPath + ";" + fullName + ";" + filePath0 + ";" + fileName0);
        } catch (NumberFormatException | IOException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            Logger.getLogger(UploadIframeDAO.class.getName()).log(Level.SEVERE, null, ex);
            resultMessage.add("error");
        }
        jsonFile.setItems(resultMessage);
        return "jsonFile";
    }

    public String openTempFileRegisterCA() {
        return "jsonFile";
    }
}
