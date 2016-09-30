/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAO;

import com.google.gson.JsonSyntaxException;
import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.common.util.ResourceBundleUtil;
import com.viettel.hqmc.BO.AnnouncementReceiptPaper;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.BO.Procedure;
import static com.viettel.hqmc.DAO.FilesDAO.checkRevocationStatus;
import com.viettel.hqmc.DAOHE.AnnouncementReceiptPaperDAOHE;
import com.viettel.hqmc.DAOHE.CaUserDAOHE;
import com.viettel.hqmc.DAOHE.FilesDAOHE;
import com.viettel.hqmc.DAOHE.FilesExpandDAOHE;
import com.viettel.hqmc.DAOHE.ProcedureDAOHE;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.signature.pdf.PDFServerClientSignature;
import com.viettel.signature.pdf.SearchTextLocations;
import com.viettel.signature.plugin.SignPdfFile;
import com.viettel.signature.utils.CertUtils;
import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAO.BaseDAO;
import static com.viettel.voffice.database.DAO.UploadIframeDAO.getSafeFileName;
import com.viettel.voffice.database.DAOHibernate.EventLogDAOHE;
import com.viettel.voffice.database.DAOHibernate.VoAttachsDAOHE;
import com.viettel.ws.FilesWS;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;
import sun.security.provider.certpath.OCSP;

/**
 *
 * @author Administrator
 */
public class FilesExpandDAO extends BaseDAO {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FilesExpandDAO.class);
    private static final String separatorFile = String.valueOf(File.separatorChar);
    private final String announcementFile05 = "announcementFile05";//Hiepvv
    private FilesForm createForm;

    private List lstProductType;
    private List lstUnit;
    private List lstStandard;
    private List lstUserAttach;

    public String getCommentReviewSignForAA() {
        getGridInfo();
        List customInfo = new ArrayList();
        String staffContent = "done";
        customInfo.add(staffContent);
        jsonDataGrid.setCustomInfo(customInfo);
        return GRID_DATA;
    }

    public String exportDataSignPluginForAA() {
        ExportFileDAO exp = new ExportFileDAO();
        String path = exp.onExportPaperSignPlugin();
        List resultMessage = new ArrayList();
        if (path.trim().length() > 0 && !"false".equals(path)) {
            resultMessage.add("1");
            resultMessage.add("Xuất công văn SĐBS thành công");
            resultMessage.add(path);
        } else {
            resultMessage.add("3");
            resultMessage.add("Lỗi trong quá trình xuất công văn SĐBS");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public String actionSignCA() throws IOException {
        boolean result = true;
        String base64Hash = "";
        String base64Hash0 = "";
        String certSerial = "";
        String fileId = "";
        String outPutFileFinal = "";
        String outPutFileFinal2 = "";
        String fileName = "";
        String fileToSign = "";
        String errorCode = "";
        SignPdfFile pdfSig = new SignPdfFile();
        try {
            fileId = getRequest().getParameter("fileId");
            String rootCert = null, base64Certificate = null, certChain;
            Base64 decoder = new Base64();
            certChain = new String(decoder.decode(getRequest().getParameter("cert").replace("_", "+").getBytes()), "UTF-8");
            String sToFind = getRequest().getParameter("signType");
            String path = getRequest().getParameter("path");
            String[] pathArr = path.split(";");
            fileToSign = pathArr[0];
            fileName = pathArr[1];
            String[] chain;
            try {
                chain = certChain.split(",");
                rootCert = chain[1];
                base64Certificate = chain[0];
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_001";
                result = false;
            }
            // hieptq update 150615
            if (base64Certificate == null) {
                errorCode = "SI_002";
                result = false;
            }
            X509Certificate x509Cert = null;
            X509Certificate x509CertChain = null;
            try {
                x509Cert = CertUtils.getX509Cert(base64Certificate);
                x509CertChain = CertUtils.getX509Cert(rootCert);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_003";
                result = false;
            }

            PDFServerClientSignature pdfSCS = new PDFServerClientSignature();
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String TSA_LINK = rb.getString("tsaUrl");
            pdfSCS.setTSA_LINK(TSA_LINK);
            String checkOcspStr = rb.getString("checkOCSP");
            Long checkOCSP = Long.parseLong(checkOcspStr);
            try {
                certSerial = x509Cert.getSerialNumber().toString(16);
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_004";
                result = false;
            }
            // hieptq update 160615 - check serial        
            String filePath = rb.getString("sign_temp_plugin");
            File f = new File(filePath);
            if (!f.exists()) {
                f.mkdirs();
            }
            outPutFileFinal = filePath + fileName;
            CaUserDAOHE ca = new CaUserDAOHE();
            boolean checkCaUser = true;
            if (!ca.checkCaSerial("SerialNumber:[" + certSerial + "]")) {
                errorCode = "SI_005";
                result = false;
            }
            try {
                if (checkOCSP == 1l) {
                    OCSP.RevocationStatus.CertStatus status = checkRevocationStatus((X509Certificate) x509Cert, (X509Certificate) x509CertChain);
                    if (status != OCSP.RevocationStatus.CertStatus.GOOD) {
                        errorCode = "SI_006";
                        result = false;
                    }
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_007";
                result = false;
            }
            if (checkCaUser) {
                String folderPath = ResourceBundleUtil.getString("sign_image");
                String linkImageSign = folderPath + getUserId() + ".png";
                String linkImageStamp = folderPath + "attpStamp.png";
                if ((linkImageSign == null && "".equals(linkImageSign))
                        || (linkImageStamp == null && "".equals(linkImageStamp))) {
                    errorCode = "SI_008";
                    result = false;
                }
                try {
                    if ("PDHS".equals(sToFind)) {
                        // ky lanh dao
                        if (fileToSign == null && "".equals(fileToSign)) {
                            errorCode = "SI_009";
                            result = false;
                        }
                        sToFind = "<SI>";
                        SearchTextLocations ptl = new SearchTextLocations();
                        List local = ptl.searchLocation(sToFind, fileToSign,
                                SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
                        String location = "0;0;0";
                        int pageNumber, lx, ly;
                        if (local != null && local.size() > 0) {
                            location = local.get(0).toString();
                        }
                        String[] parts = location.split(";");
                        pageNumber = Integer.parseInt(parts[0]);
                        lx = (int) Float.parseFloat(parts[1]);
                        ly = (int) Float.parseFloat(parts[2]);
                        ly = convertLocation(ly);
                        base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
                                new Certificate[]{x509Cert}, pageNumber, linkImageSign, lx + 70, ly + 130, 120, 70, "LD");
                    }
                    if ("PDHS_VT".equals(sToFind)) {
                        // ky van thu
                        if (fileToSign == null && "".equals(fileToSign)) {
                            errorCode = "SI_010";
                            result = false;
                        }
                        String sToFindtemp = "<SI>";
                        SearchTextLocations ptl = new SearchTextLocations();
                        List local = ptl.searchLocation(sToFindtemp, fileToSign,
                                SearchTextLocations.SEARCH_TOPDOWN, SearchTextLocations.FIND_ONE);
                        String location = "0;0;0";
                        int pageNumber, lx, ly;
                        if (local != null && local.size() > 0) {
                            location = local.get(0).toString();
                        }
                        String[] parts = location.split(";");
                        pageNumber = Integer.parseInt(parts[0]);
                        lx = (int) Float.parseFloat(parts[1]);
                        ly = (int) Float.parseFloat(parts[2]);
                        ly = convertLocation(ly);
                        base64Hash = pdfSig.createHash(fileToSign, outPutFileFinal,
                                new Certificate[]{x509Cert}, pageNumber, linkImageStamp, lx + 23, ly + 115, 90, 90, "VT");
                    }
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
                    System.out.println("ERROR SI_012|" + ex.getMessage());
                    errorCode = "SI_012";
                    result = false;
                }

            } else {
                errorCode = "SI_013";
                result = false;
            }
        } catch (JsonSyntaxException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            errorCode = "SI_014";
            result = false;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            errorCode = "SI_015";
            result = false;
        } finally {

        }
        List resultMessage = new ArrayList();
        if (result) {
            HttpServletRequest req = getRequest();
            HttpSession session = req.getSession();
            session.setAttribute("PDFSignature", pdfSig);
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
            resultMessage.add(base64Hash);
            resultMessage.add(certSerial);
            resultMessage.add(fileId);
            resultMessage.add(outPutFileFinal);
            resultMessage.add(fileName);
            resultMessage.add(base64Hash0);
            resultMessage.add(outPutFileFinal2);
        } else {
            resultMessage.add("0");
            resultMessage.add("Lưu dữ liệu không thành công " + errorCode);
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public int convertLocation(int y) {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        String pageHeightStr = rb.getString("pageHeight");
        String imageSignatureHeightStr = rb.getString("imageSignatureHeight");
        int pageHeight = Integer.parseInt(pageHeightStr);
        int imageSignatureHeight = Integer.parseInt(imageSignatureHeightStr);
        int padding = 10;
        if ((pageHeight - y) < 0) {
            y = 0;
        } else {
            y = pageHeight - y;
        }
        if (y > pageHeight - imageSignatureHeight) {
            y = pageHeight - imageSignatureHeight - padding;
        }
        return y;
    }

    public String onSignPlugin() {
        boolean result = true;
        String errorCode = "";
        Calendar cal = Calendar.getInstance();
        try {
            String signType = getRequest().getParameter("signType");
            String fileName = getRequest().getParameter("fileName");
            String fileName0 = getRequest().getParameter("fileName0");
            //hieptq update vi tri luu file
            ResourceBundle rb = ResourceBundle.getBundle("config");
            String uploadPath = rb.getString("PERMIT_path");//160629 binhnt update duong dan luu file cong bo
            String subDir = String.valueOf(cal.getTime().getYear() + 1900)
                    + separatorFile + String.valueOf(cal.getTime().getMonth() + 1)
                    + separatorFile + String.valueOf(cal.getTime().getDate())
                    + separatorFile;//ex: 2016\6\29\
            String strPath = rb.getString("PERMIT_upload") + subDir;
            String copyPath = rb.getString("file_sign_link");

            String paperOnly = "";

            File folderExisting = new File(getSafeFileName(strPath));
            if (!folderExisting.isDirectory()) {
                folderExisting.mkdir();
            }
            if (folderExisting.isDirectory()) {
                //tao folder theo ngay thang
                File temp = new File(strPath);
                if (!temp.isDirectory()) {
                    temp.mkdirs();
                }
            }

            String[] parts = fileName.split("_");
            if (parts.length != 4 && parts.length != 5 && parts.length != 6) {
                errorCode = "SI_015";
                result = false;
            }
            if (parts.length == 5 && "LD".equals(parts[0])) {
                paperOnly = parts[0] + "_" + parts[1] + "_" + parts[2] + "_" + parts[3] + "_" + "2" + ".pdf";
            }
            if (parts.length == 6 && "VT".equals(parts[0])) {
                paperOnly = parts[0] + "_" + parts[1] + "_" + parts[2] + "_" + parts[3] + "_" + parts[4] + "_" + "2" + ".pdf";
            }
            //hieptq update 106015
            String outputFile = strPath + fileName;
            String outputFileOriginal = strPath + fileName0;
            String signature;
            String signatureOriginal = null;
            Base64 decoder = new Base64();
            signature = new String(decoder.decode(getRequest().getParameter("signData").replace("_", "+").getBytes()), "UTF-8");

            SignPdfFile pdfSig = new SignPdfFile();
            SignPdfFile pdfSig0 = new SignPdfFile();
            String checkTsaStr = rb.getString("checkTSA");
            Long checkTSA = Long.parseLong(checkTsaStr);
            pdfSig = (SignPdfFile) getRequest().getSession().getAttribute("PDFSignature");
            //Hiepvv hoso SDBS sau cong bo khong can cai nay
            if (fileName0 != null && fileName0.length() > 0 && ("PDHS".equals(signType) || "PDHS_VT".equals(signType))) {
                signatureOriginal = new String(decoder.decode(getRequest().getParameter("signDataOriginal").replace("_", "+").getBytes()), "UTF-8");
                pdfSig0 = (SignPdfFile) getRequest().getSession().getAttribute("PDFSignature2");
            }
            //hieptq update 110615
            String fileSignOutLink = getRequest().getParameter("outPutPath");
            String fileSignOutLink2 = getRequest().getParameter("outPutPath2");
            try {
                if (checkTSA == 1l) {
                    pdfSig.insertSignatureFinal(signature, fileSignOutLink, outputFile, true);
                    if ("PDHS".equals(signType) || "PDHS_VT".equals(signType)) {
                        pdfSig0.insertSignatureFinal(signatureOriginal, fileSignOutLink2, outputFileOriginal, true);
                    }
                } else {
                    pdfSig.insertSignatureFinal(signature, fileSignOutLink, outputFile, false);
                    //Hiepvv hoso SDBS sau cong bo khong can cai nay
                    if (fileSignOutLink2 != null && fileSignOutLink2.length() > 0 && ("PDHS".equals(signType) || "PDHS_VT".equals(signType))) {
                        pdfSig0.insertSignatureFinal(signatureOriginal, fileSignOutLink2, outputFileOriginal, false);
                    }
                }
            } catch (IOException ex) {
                errorCode = "SI_016";
                System.out.println("IOException " + ex.toString());
                LogUtil.addLog(ex);//binhnt sonar a160901
                result = false;
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_017";
                System.out.println("Exception " + ex.getMessage());
                result = false;
            } finally {
                try {
                    if (deleteFile(fileSignOutLink)) {
                        System.out.println("Deleted file: " + fileSignOutLink);
                    } else {
                        errorCode = "SI_018";
                        result = false;
                    }
                    //Hiepvv SDBS sau cong bo khong co file 2
                    if (("PDHS".equals(signType) || "PDHS_VT".equals(signType))
                            && fileSignOutLink2 != null && fileSignOutLink2.length() > 0) {
                        if (deleteFile(copyPath + paperOnly)) {
                            System.out.println("Deleted file: " + copyPath + paperOnly);
                        } else {
                            errorCode = "SI_020";
                            result = false;
                        }
                        if (deleteFile(fileSignOutLink2)) {
                            System.out.println("Deleted file: " + fileSignOutLink2);
                        } else {
                            errorCode = "SI_019";
                            result = false;
                        }
                    }
                    if (deleteFile(copyPath + fileName)) {
                        System.out.println("Deleted file: " + copyPath + fileName);
                    } else {
                        errorCode = "SI_021";
                        result = false;
                    }
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
                    System.out.println("Delete file fail ! " + ex.toString());
                }
            }
            System.out.println("Signed file: " + outputFile);
            try {
                if (updateSignPlugin(fileName, subDir, uploadPath) == false) {
                    errorCode = "SI_022";
                    result = false;
                }
                if (("PDHS".equals(signType) || "PDHS_VT".equals(signType))
                        //Hiepvv
                        && fileSignOutLink2 != null && fileSignOutLink2.length() > 0) {
                    if (updateSignPlugin(paperOnly, subDir, uploadPath) == false) {
                        errorCode = "SI_023";
                        result = false;
                    }
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                errorCode = "SI_024";
                result = false;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            errorCode = "SI_025";
            result = false;

        }
        List resultMessage = new ArrayList();
        if (result) {
            resultMessage.add("1");
        } else {
            resultMessage.add("0");
            resultMessage.add(errorCode);
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public Boolean updateSignPlugin(String fileName, String destination, String uploadPath) {
        boolean result = true;
        try {
            // Save info to DB
            String[] parts = fileName.split("_");
            if (parts.length != 4
                    && parts.length != 5
                    && parts.length != 6) {
                result = false;
            }
            String signType = "";
            Integer indexFile = 0;
            if (parts.length == 4) {
                signType = parts[3].substring(0, parts[3].indexOf(".pdf"));
            } else if (parts.length == 6) {
                if (parts[0].equals("VT")) {
                    signType = parts[4];
                    indexFile = Integer.parseInt(parts[5].substring(0, parts[5].indexOf(".pdf")));
                } else {
                    signType = parts[3];
                    indexFile = Integer.parseInt(parts[4].substring(0, parts[4].indexOf(".pdf")));
                }
            } else if (parts.length == 5) {
                if (parts[0].equals("VT")) {
                    signType = parts[4].substring(0, parts[4].indexOf(".pdf"));
                } else {
                    signType = parts[3];
                    indexFile = Integer.parseInt(parts[4].substring(0, parts[4].indexOf(".pdf")));
                }
            }
            String fileId = parts[2];
            if ("".equals(fileId) || "".equals(signType)) {
                Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: Tên File không đúng định dạng");
                result = false;
            }

            VoAttachsDAOHE vdhe = new VoAttachsDAOHE();
            if (parts[0].equals("LD")) {
                VoAttachs voUpload = new VoAttachs();
                voUpload.setObjectId(Long.parseLong(fileId));
                voUpload.setIsActive(1l);
                voUpload.setCreateDate(vdhe.getSysdate());
                if (signType.equals("PDHS")) {
                    if (indexFile == 0 || indexFile == 1) {
                        voUpload.setObjectType(40L);
                    } else if (indexFile == 2) {
                        voUpload.setObjectType(41L);
                    } else {
                        Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: PDHS không xác định được thứ tự File ký");
                        result = false;
                    }
                    //Hiepvv 0703 dat ten file
                    FilesDAOHE fdhe = new FilesDAOHE();
                    Files fName = fdhe.findById(Long.parseLong(fileId));
                    if (fName.getFilesSourceID() != null && fName.getFilesSourceID() > 0) {
                        voUpload.setAttachName("CongvanSDBSsaucongbo_" + fileName);
                        voUpload.setObjectType(40L);

                    } else {
                        voUpload.setAttachName("Bancongbo_" + fileName);
                    }
                } else if (signType.equals("CVBS")) {
                    voUpload.setObjectType(71L);
                    voUpload.setAttachName("CongvanSdbs_" + fileName);
                } else {
                    Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: Tên File không đúng định dạng");
                    result = false;
                }
                voUpload.setAttachPath(uploadPath + destination + fileName);
                vdhe.saveDbNotCommit(voUpload);
            } else if (parts[0].equals("VT")) {
                List<VoAttachs> voa;
                if (signType.equals("PDHS")) {
                    if (indexFile == 0 || indexFile == 1) {
                        voa = vdhe.getAttachsByObject(Long.parseLong(fileId), 40L);
                        if (voa != null && voa.size() > 0) {
                            for (int i = 0; i < voa.size(); i++) {
                                voa.get(i).setIsActive(0L);
                                vdhe.updateDbNotCommit(voa.get(i));
                            }
                        }
                    } else if (indexFile == 2) {
                        voa = vdhe.getAttachsByObject(Long.parseLong(fileId), 41L);
                        if (voa != null && voa.size() > 0) {
                            for (int i = 0; i < voa.size(); i++) {
                                voa.get(i).setIsActive(0L);
                                vdhe.updateDbNotCommit(voa.get(i));
                            }
                        }
                    }
                }

                VoAttachs voUpload = new VoAttachs();
                voUpload.setObjectId(Long.parseLong(fileId));
                voUpload.setIsActive(1l);
                voUpload.setCreateDate(vdhe.getSysdate());
                if (signType.equals("PDHS")) {
                    if (indexFile == 0 || indexFile == 1) {
                        voUpload.setObjectType(40L);
                    } else if (indexFile == 2) {
                        voUpload.setObjectType(41L);
                    } else {
                        Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: PDHS không xác định được thứ tự File ký");
                        result = false;
                    }
                    //Hiepvv 0703 dat ten file
                    FilesDAOHE fdhe = new FilesDAOHE();
                    Files fName = fdhe.findById(Long.parseLong(fileId));
                    if (fName.getFilesSourceID() != null && fName.getFilesSourceID() > 0) {
                        voUpload.setAttachName("CongvanSDBSsaucongbo_" + fileName);
                        voUpload.setObjectType(40L);
                    } else {
                        voUpload.setAttachName("Bancongbo_" + fileName);
                    }
                } else if (signType.equals("CVBS")) {
                    voUpload.setObjectType(71L);
                    voUpload.setAttachName("CongvanSdbs_" + fileName);
                } else {
                    Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: Tên File không đúng định dạng");
                    result = false;
                }
                voUpload.setAttachPath(uploadPath + destination + fileName);
                vdhe.saveDbNotCommit(voUpload);
            }
            // Update status to File
            if (signType.equals("PDHS")) {
                FilesDAOHE fdhe = new FilesDAOHE();
                Files file = fdhe.findById(Long.parseLong(fileId));
                file.setIsDownload(1L);
                if (parts[0].equals("VT")) {
                    file.setIsSignPdf(2l);
                    fdhe.saveDbNoCommit(file);
                } else if (parts[0].equals("LD")) {
                    file.setIsSignPdf(1l);
                    fdhe.saveDbNoCommit(file);
                } else {
                    Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: Tên File không đúng định dạng");
                    result = false;
                }
            }
            vdhe.commitDb();
            //Hiepvv_Home copy file SĐBS sau công bố sang file gốc
            if (signType.equals("PDHS") && parts[0].equals("VT")) {
                if (fileId != null && fileId.length() > 0) {
                    Long fId = Long.parseLong(fileId);
                    result = copyFileChangeAfterAnnouncedToFileSource(fId);
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        return result;
    }

    public boolean copyFileChangeAfterAnnouncedToFileSource(Long fileId) {
        VoAttachsDAOHE vDAO = new VoAttachsDAOHE();
        ProcedureDAOHE pdhe = new ProcedureDAOHE();
        Files f = new Files();
        FilesDAOHE fDAO = new FilesDAOHE();
        try {
            if (fileId != null && fileId > 0L) {

                f = fDAO.findById(fileId);
                Procedure pro = pdhe.findById(f.getFileType());
                String typePro = "";
                if (pro != null) {
                    typePro = pro.getDescription();
                }
                //Nếu là SĐBS sau công bố
                if (typePro != null && typePro.equalsIgnoreCase(announcementFile05)) {
                    boolean isCheck = false;

                    VoAttachs vAtt = new VoAttachs();
                    VoAttachs voUpload;
                    List<VoAttachs> lstVoAtt = vDAO.getLstVoAttachByObjectId(fileId);
                    if (lstVoAtt != null && lstVoAtt.size() > 0) {
                        //Check văn thư đóng dấu trả doanh nghiệp
                        for (int i = 0; i < lstVoAtt.size(); i++) {
                            vAtt = lstVoAtt.get(i);
                            if (vAtt.getObjectType() == 41L || vAtt.getObjectType() == 40L) {
                                isCheck = true;
                                break;
                            }
                        }
                        //Nếu văn thư đã trả công bố và chưa copy files
                        if (isCheck == true && (f.getIsCopy() == null || f.getIsCopy() != 1L)) {
                            for (int i = 0; i < lstVoAtt.size(); i++) {
                                vAtt = lstVoAtt.get(i);
                                //Hiepvv chi insert cong van sdbs sau cong bo ve ho so goc
                                if (vAtt.getObjectType() == 41L || vAtt.getObjectType() == 40L) {
                                    voUpload = new VoAttachs();
                                    //Copy
                                    voUpload.setObjectId(f.getFilesSourceID());
                                    voUpload.setIsActive(1l);
                                    voUpload.setCreateDate(vAtt.getCreateDate());
                                    voUpload.setAttachName(vAtt.getAttachName());
                                    voUpload.setAttachPath(vAtt.getAttachPath());
                                    voUpload.setAttachDes("Công văn sửa đổi bổ sung sau công bố");
                                    voUpload.setCategoryName("Công văn sửa đổi bổ sung sau công bố");
                                    voUpload.setObjectType(vAtt.getObjectType());
                                    if (vAtt.getCategoryId() != null) {
                                        voUpload.setCategoryId(vAtt.getCategoryId());
                                    }
                                    if (vAtt.getDeptId() != null) {
                                        voUpload.setDeptId(vAtt.getDeptId());
                                    }
                                    if (vAtt.getIsTemp() != null) {
                                        voUpload.setIsTemp(vAtt.getIsTemp());
                                    }
                                    if (vAtt.getOriginalId() != null) {
                                        voUpload.setOriginalId(vAtt.getOriginalId());
                                    }
                                    if (vAtt.getUserCreateId() != null) {
                                        voUpload.setUserCreateId(vAtt.getUserCreateId());
                                    }

                                    vDAO.saveDbNotCommit(voUpload);
                                }
                            }
                            vDAO.commitDb();
                            //Update files đã chuyển các file cần thiết qua hồ sơ gốc
                            f.setIsCopy(1L);
                            getSession().update(f);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, exc);
            return false;
        }
        return true;
    }

    public boolean deleteFile(String filePath) {
        boolean result = true;
        try {
            if (filePath != null && filePath.trim().length() > 0) {
                File file = new File(filePath);
                if (!file.delete()) {
                    result = false;
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            result = false;
        }
        return result;
    }

    public String onCreatePaperForAA() {
        FilesExpandDAOHE fdhe = new FilesExpandDAOHE();
        FilesDAO fdao = new FilesDAO();
        boolean bReturn = fdhe.onCreatePaperForAA(createForm, getDepartmentId(),
                getDepartment().getDeptName(), getUserId(), getUserName());
        List resultMessage = new ArrayList();
        if (bReturn) {
            resultMessage.add("1");
            resultMessage.add("Lưu dữ liệu thành công");
            if (createForm.getStatus().equals(Constants.FILE_STATUS.APPROVED)) {
                fdao.getBarcode(createForm);
            }
        } else {
            resultMessage.add("3");
            resultMessage.add("Lưu dữ liệu không thành công");
        }
        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }

    public String onCreatePaperByLeaderForAA() {
        try {
            Base64 decoder = new Base64();
            String title = new String(decoder.decode(getRequest().getParameter("title").replace("_", "+").getBytes()), "UTF-8");
            String content = new String(decoder.decode(getRequest().getParameter("content").replace("_", "+").getBytes()), "UTF-8");
            FilesExpandDAOHE fdhe = new FilesExpandDAOHE();
            FilesDAO fdao = new FilesDAO();
            createForm.setTitleEditATTP(title);
            createForm.setContentsEditATTP(content);
            boolean bReturn = fdhe.onCreatePaperForAA(createForm, getDepartmentId(),
                    getDepartment().getDeptName(), getUserId(), getUserName());
            List resultMessage = new ArrayList();
            if (bReturn) {
                resultMessage.add("1");
                resultMessage.add("Lưu dữ liệu thành công");
                if (createForm.getStatus().equals(Constants.FILE_STATUS.APPROVED)) {
                    fdao.getBarcode(createForm);
                }
            } else {
                resultMessage.add("3");
                resultMessage.add("Lưu dữ liệu không thành công");
            }
            jsonDataGrid.setItems(resultMessage);
            return GRID_DATA;
        } catch (UnsupportedEncodingException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            Logger.getLogger(FilesExpandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return GRID_DATA;
    }

    public FilesForm getCreateForm() {
        return createForm;
    }

    public void setCreateForm(FilesForm createForm) {
        this.createForm = createForm;
    }

    public String onApproveByLDP4AA() {
        FilesDAO fdao = new FilesDAO();
        FilesDAOHE fdhe = new FilesDAOHE();
        FilesExpandDAOHE fedaohe = new FilesExpandDAOHE();
        List resultMessage = new ArrayList();
        try {
            boolean check = fdhe.validateRoleUser(createForm.getFileId(), createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
            if (check) {
                boolean bReturn = fedaohe.onApproveByLDP4AA(createForm, getDepartmentId(), getDepartment().getDeptName(), getUserId(), getUserName());
                if (bReturn) {
                    resultMessage.add("1");
                    resultMessage.add("Lưu dữ liệu thành công");
                    if (createForm.getStatus().equals(Constants.FILE_STATUS.APPROVED)) {
                        fdao.getBarcode(createForm);
                    }
                    Files file = fdhe.findById(createForm.getFileId());
                    // Save status
                    fdhe.saveStatusFiles(file, "Hồ sơ mã: " + file.getFileCode() + " Đã được phê duyệt");
                    // Save Sign date
                    AnnouncementReceiptPaperDAOHE arpDhe = new AnnouncementReceiptPaperDAOHE();
                    AnnouncementReceiptPaper arp = arpDhe.findById(file.getAnnouncementReceiptPaperId());
                    if (arp != null) {
                        VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
                        arp.setSignDate(daoHe.getSysdate());
                        getSession().update(arp);
                    } else {
                        resultMessage.add("3");
                        resultMessage.add("Phê duyệt không thành công");
                        jsonDataGrid.setItems(resultMessage);
                        return GRID_DATA;
                    }
                } else {
                    resultMessage.add("3");
                    resultMessage.add("Phê duyệt không thành công");
                    jsonDataGrid.setItems(resultMessage);
                    return GRID_DATA;
                }
            } else {
                resultMessage.add("3");
                resultMessage.add("Phê duyệt không thành công - Lỗi phân quyền người dùng");
            }

            EventLogDAOHE edhe = new EventLogDAOHE();
            edhe.insertEventLog("Phê duyệt hồ sơ", "hồ sơ có id=" + createForm.getFileId(), getRequest());
            getSession().getTransaction().commit();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            resultMessage.add("3");
            resultMessage.add("Phê duyệt không thành công");
//            log.error(ex.getMessage());
        }

        jsonDataGrid.setItems(resultMessage);
        return GRID_DATA;
    }
    /*
    public String toCreateFile4AAPage() {
        Long fileId = getRequest().getParameter("fileId") == null ? 0L : Long.parseLong(getRequest().getParameter("fileId"));
        Long fileType = getRequest().getParameter("fileType") == null ? 0L : Long.parseLong(getRequest().getParameter("fileType"));
        Long checkEdit = getRequest().getParameter("checkEdit") == null ? 0L : Long.parseLong(getRequest().getParameter("checkEdit"));
        Long typeFee = getRequest().getParameter("typeFee") == null ? 0L : Long.parseLong(getRequest().getParameter("typeFee"));
//        //hiepvv check edit after announced
        Long isEdits = getRequest().getParameter("isEdit") == null ? 0L : Long.parseLong(getRequest().getParameter("isEdit"));
        if (fileType > 0L) {
            createForm = new FilesForm();
            createForm.setFileType(fileType);
        }
        Users user = new Users();
        UsersDAOHE udhe = new UsersDAOHE();
        user = udhe.findById(getUserId());

        Business bus = new Business();
        BusinessDAOHE bdhe = new BusinessDAOHE();
        bus = bdhe.findById(user.getBusinessId());
        //San pham ho so goc
        String proName = "";
        String manuName = "";
        String manuTel = "";
        String manuAdd = "";
        String manuEmail = "";
        String manuFax = "";
        String matchingTaget = "";
        String nameStaffProcess = "";
        Long staffProcessId = 0L;
        Date publishDate = null;
        String strReturn = ERROR_PERMISSION;
        if (createForm.getFileId() != null && createForm.getFileId() > 0l) {
            FilesDAOHE fdhe = new FilesDAOHE();
            createForm = fdhe.getFilesDetail(createForm.getFileId());
            if (isEdits > 0L) {
                String fileSourceCode = createForm.getFileCode();
                Long fileSource = createForm.getFileId();
                staffProcessId = createForm.getStaffProcess();
                nameStaffProcess = createForm.getNameStaffProcess();
                //Thong tin ho so cu
                proName = createForm.getAnnouncement().getProductName();
                manuName = createForm.getAnnouncement().getManufactureName();
                manuAdd = createForm.getAnnouncement().getManufactureAddress();
                manuEmail = createForm.getAnnouncement().getManufactureName();
                manuTel = createForm.getAnnouncement().getManufactureTel();
                manuFax = createForm.getAnnouncement().getManufactureFax();
                matchingTaget = createForm.getAnnouncement().getMatchingTarget();
                publishDate = createForm.getAnnouncement().getPublishDate();
                //end
                ProcedureDAOHE pdaohe = new ProcedureDAOHE();
                Procedure p = new Procedure();
                try {
                    p = pdaohe.getProcedureByDescription(announcementFile05);
                } catch (Exception ex) {
                    p = null;
                    Logger.getLogger(FilesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                createForm = new FilesForm();
                if (p != null) {
                    createForm.setFileType(p.getProcedureId());
                }
                createForm.setFilesSourceID(fileSource);
                createForm.setFileSourceCode(fileSourceCode);
                createForm.setStaffProcess(staffProcessId);//them nguoi tham dinh ho so truoc day
                createForm.setNameStaffProcess(nameStaffProcess);//them nguoi tham dinh ho so truoc day
            }
        }

        if (createForm.getFileType() != null && createForm.getFileType() > 0l) {
            ProcedureDAOHE pdhe = new ProcedureDAOHE();
            CategoryDAOHE cdhe = new CategoryDAOHE();
            TechnicalStandardDAOHE tdhe = new TechnicalStandardDAOHE();
            FilesDAOHE fdhe = new FilesDAOHE();
            if (!fileType.equals(0L)) {
                createForm.setFileType(fileType);
            }
            Procedure tthc = pdhe.findById(createForm.getFileType());

            if (tthc != null) {

                if (checkEdit == 1) {
                    typeFee = cdhe.findTypeFee(tthc.getProcedureId());
                    lstProductType = cdhe.findAllCategoryByFee("SP", typeFee);
                } else {
                    lstProductType = cdhe.findAllCategoryByFee("SP", typeFee);
                }

                if (((Category) lstProductType.get(0)).getCategoryId() != -1L) {
                    lstProductType.add(0, new Category(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                }
                lstUnit = cdhe.findAllCategory("DVI");
                lstUnit.add(0, new Category(Constants.COMBOBOX_HEADER_VALUE, Constants.COMBOBOX_HEADER_TEXT_SELECT));
                lstStandard = tdhe.findAllStandard();
                String lstDepts = convertToJSONData(lstStandard, "vietnameseName", "vietnameseName");
                getRequest().setAttribute("lstStandard", lstDepts);

                UserAttachsDAOHE uahe = new UserAttachsDAOHE();
                lstUserAttach = uahe.findAllUserAttach(getUserId());
                String lstUserAttachs = convertToJSONData(lstUserAttach, "attachName", "attachName");
                getRequest().setAttribute("lstUserAttach", lstUserAttachs);
                if (lstUserAttachs.trim().length() > 0) {
                    createForm.setCountUA(1L);
                } else {
                    createForm.setCountUA(0L);
                }

                getRequest().setAttribute("lstProductType", lstProductType);
                getRequest().setAttribute("lstUnit", lstUnit);
                String fileLst = tthc.getFileList();
                getRequest().setAttribute("fileList", com.viettel.common.util.StringUtils.removeHTML(fileLst));
                getRequest().setAttribute("agencyName", getDepartmentName());
                getRequest().setAttribute("fileNameFull", tthc.getName());
                getRequest().setAttribute("checkEdit", checkEdit);
                strReturn = tthc.getDescription();
                if (createForm.getAnnouncement() != null) {
                    if (createForm.getAnnouncement().getAnnouncementNo() != null
                            && createForm.getAnnouncement().getAnnouncementNo().length() > 0l
                            && isEdits != 1L) {
                        CategoryDAOHE ctdhe = new CategoryDAOHE();
                        Category cate = ctdhe.findCategoryByTypeAndCode("SP", "TPCN");
                        Category cateTL = ctdhe.findCategoryByTypeAndCode("SP", "TL");
                        List<Category> cate1 = ctdhe.findCategoryByTypeAndCodeNew("SP", "DBT");
                        String dbtId = "";
                        for (int i = 0; i < cate1.size(); i++) {
                            dbtId += cate1.get(i).getCategoryId().toString() + ";";
                        }

                        Long tpcnId = cate.getCategoryId();
                        Long tlId = cateTL.getCategoryId();
                        FeeDAOHE fdhe1 = new FeeDAOHE();
                        Fee findfee1 = fdhe1.findFeeByCode("TPDB");
                        Long priceTPDB = findfee1.getPrice();
                        Fee findfee2 = fdhe1.findFeeByCode("TPCN");
                        Long priceTPCN = findfee2.getPrice();
                        Fee findfee3 = fdhe1.findFeeByCode("TPK");
                        Long priceETC = findfee3.getPrice();
                        getRequest().setAttribute("dbtId", dbtId);
                        getRequest().setAttribute("tpcnId", tlId);
                        getRequest().setAttribute("tlId", tpcnId);
                        getRequest().setAttribute("tpcnId", tpcnId);
                        getRequest().setAttribute("priceTPCN", priceTPCN);
                        getRequest().setAttribute("priceTPDB", priceTPDB);
                        getRequest().setAttribute("priceETC", priceETC);
                        return strReturn;
                    }
                }
                if (strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.REC_CONFIRM_NORMAL_IMP)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_IMP)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_VN)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.RE_CONFIRM_NORMAL_VN)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05)
                        || strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR)) {
                    String announcementNoStr = fdhe.getReceiptNoNew(getUserId(), getUserLogin(), createForm.getFileType());
                    createForm.setAnnouncement(new AnnouncementForm());
                    createForm.getAnnouncement().setAnnouncementNo(announcementNoStr);
                    // thong tin doanh nghiep
                    createForm.getAnnouncement().setBusinessAddress(bus.getBusinessAddress());
                    createForm.getAnnouncement().setBusinessFax(bus.getBusinessFax());
                    createForm.getAnnouncement().setBusinessName(bus.getBusinessName());
                    createForm.getAnnouncement().setBusinessTelephone(bus.getBusinessTelephone());
                    createForm.getAnnouncement().setBusinessEmail(bus.getUserEmail());
                    createForm.getAnnouncement().setBusinessLicence(bus.getBusinessLicense());
                    // ho so cap lai 7-11
                    createForm.setReIssueForm(new ReIssueFormForm());
                    createForm.getReIssueForm().setBusinessName(bus.getBusinessName());
                    createForm.getReIssueForm().setIdentificationNumber(bus.getBusinessLicense());
                    createForm.getReIssueForm().setAddress(bus.getBusinessAddress());
                    createForm.getReIssueForm().setEmail(bus.getUserEmail());
                    createForm.getReIssueForm().setTelephone(bus.getBusinessTelephone());
                    createForm.getReIssueForm().setFax(bus.getBusinessFax());
//                    //San pham ho so goc
                    if (strReturn.equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05)) {
                        createForm.getAnnouncement().setProductName(proName);
                        createForm.getAnnouncement().setManufactureAddress(manuAdd);
                        createForm.getAnnouncement().setManufactureName(manuName);
                        createForm.getAnnouncement().setManufactureTel(manuTel);
                        createForm.getAnnouncement().setManufactureEmail(manuEmail);
                        createForm.getAnnouncement().setManufactureFax(manuFax);
                        createForm.getAnnouncement().setMatchingTarget(matchingTaget);
                        createForm.getAnnouncement().setPublishDate(publishDate);
                        createForm.setIsFee(1L);
                    }
                }
            }
            CategoryDAOHE ctdhe = new CategoryDAOHE();
            Category cate = ctdhe.findCategoryByTypeAndCode("SP", "TPCN");
            Category cateTL = ctdhe.findCategoryByTypeAndCode("SP", "TL");
            List<Category> cate1 = ctdhe.findCategoryByTypeAndCodeNew("SP", "DBT");
            String dbtId = "";
            for (int i = 0; i < cate1.size(); i++) {
                dbtId += cate1.get(i).getCategoryId().toString() + ";";
            }
            Long tpcnId = cate.getCategoryId();
            Long tlId = cateTL.getCategoryId();
            FeeDAOHE fdhe1 = new FeeDAOHE();
            Fee findfee1 = fdhe1.findFeeByCode("TPDB");
            Long priceTPDB = findfee1.getPrice();
            Fee findfee2 = fdhe1.findFeeByCode("TPCN");
            Long priceTPCN = findfee2.getPrice();
            Fee findfee3 = fdhe1.findFeeByCode("TPK");
            Long priceETC = findfee3.getPrice();
            getRequest().setAttribute("dbtId", dbtId);
            getRequest().setAttribute("tpcnId", tpcnId);
            getRequest().setAttribute("tlId", tlId);
            getRequest().setAttribute("priceTPCN", priceTPCN);
            getRequest().setAttribute("priceTPDB", priceTPDB);
            getRequest().setAttribute("priceETC", priceETC);
        }
        return strReturn + "Paper";
    }
     */
}
