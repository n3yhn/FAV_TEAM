/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws;

import com.viettel.common.util.DateTimeUtils;
import com.viettel.hqmc.BO.AnnouncementReceiptPaper;
import com.viettel.hqmc.BO.Business;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.BO.Procedure;
import com.viettel.hqmc.DAOHE.AnnouncementReceiptPaperDAOHE;
import com.viettel.hqmc.DAOHE.BusinessDAOHE;
import com.viettel.hqmc.DAOHE.FilesDAOHE;
import com.viettel.hqmc.DAOHE.FilesNoClobDAOHE;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.hqmc.FORM.ReIssueFormForm;
import com.viettel.hqmc.FORM.TestRegistrationForm;
import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.VoAttachsDAOHE;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import com.viettel.ws.ANNOUCERECEIVE.ANNOUNCESENDDmapFILESFORM;
import com.viettel.ws.ANNOUCERECEIVE.ANNOUNCESENDDtoType;
import com.viettel.ws.BO.ANNOUNCERESULTDto;
import com.viettel.ws.BO.ERRORDto;
import com.viettel.ws.BO.ERRORLIST;
import com.viettel.ws.BO.FILERESULTSDto;
import com.viettel.ws.BO.RESULTPAGER;
import com.viettel.ws.BO.SENDRESPONSEDto;
import com.viettel.ws.FORM.ANNOUCE_RECEIVE;
import com.viettel.ws.FORM.ANNOUNCE_DETAILS;
import com.viettel.ws.FORM.ASSIGNMENT_QUERY;
import com.viettel.ws.FORM.COMPLAINTS;
import com.viettel.ws.FORM.ERROR;
import com.viettel.ws.FORM.RESULT;
import com.viettel.ws.FORM.RE_ANNOUNCE;
import com.viettel.ws.PdfSign.PdfSign;
import com.viettel.ws.validateData.Helper;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author vtit_havm2
 */
@WebService(serviceName = "FilesWS")
public class FilesWS extends BaseWS {
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FilesWS.class);

    // service tiep nhan ban cong bo hop quy/phu hop - hieptq 07052014
    public String sendAnnouncement(@WebParam(name = "tokenString") String tokenString, @WebParam(name = "xml") String xml) {
        SENDRESPONSEDto sENDRESPONSEDto = new SENDRESPONSEDto();
        ERRORLIST eRRORLIST = new ERRORLIST();
        FilesForm createForm = null;
        if (!tokenString.isEmpty() && !ServiceSessionManager.validToken(tokenString)) {
            //validate authemcation
            ERRORDto eRRORDto = new ERRORDto();
            eRRORDto.setERRORCODE("A0001-ANNOUNCESEND-0000");
            eRRORDto.setERRORID("");
            eRRORDto.setERRORNAME("Lỗi token");
            eRRORLIST.getERRORDto().add(eRRORDto);
        }
        // check convert
        try {
            createForm = null;
            ANNOUNCESENDDtoType file = XmlToObjectFile(xml);
            Helper vali = new Helper();
            ANNOUNCESENDDmapFILESFORM a = new ANNOUNCESENDDmapFILESFORM();
            eRRORLIST.getERRORDto().addAll(vali.validateANNOUNCESENDDto(file));
//            FILEITEMSType fileitem;
//            FILELISTType filelist;
//            filelist = file.getFILELIST();
//            fileitem = filelist.getFILEITEMS();
            if (eRRORLIST == null || eRRORLIST.getERRORDto() == null || eRRORLIST.getERRORDto().size() == 0) {
                createForm = file.wsToForm();
                Boolean save = sendFiles(tokenString, createForm);
                if (!save) {
                    ERRORDto eRRORDto = new ERRORDto();
                    eRRORDto.setERRORCODE("L001-ANNOUNCESEND-0000");
                    eRRORDto.setERRORID("");
                    eRRORDto.setERRORNAME("Lỗi lưu dữ liệu");
                    eRRORLIST.getERRORDto().add(eRRORDto);
                } else {
                    ERRORDto eRRORDto = new ERRORDto();
                    eRRORDto.setERRORCODE("00000-0000-0000");
                    eRRORDto.setERRORID("");
                    eRRORDto.setERRORNAME("Nhận hồ sơ thành công");
                    eRRORLIST.getERRORDto().add(eRRORDto);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
            ERRORDto eRRORDto = new ERRORDto();
            eRRORDto.setERRORCODE("A001-ANNOUNCESEND-0000");
            eRRORDto.setERRORID("");
            eRRORDto.setERRORNAME("Lỗi validate hồ sơ");
            eRRORLIST.getERRORDto().add(eRRORDto);
        }
        sENDRESPONSEDto.setERRORLIST(eRRORLIST);
        return senddresponsedToToXml(sENDRESPONSEDto);
    }
    
    public boolean sendFiles(@WebParam(name = "tokenString") String tokenString, @WebParam(name = "searchForm") FilesForm createForm) {
        if (!tokenString.isEmpty() && !ServiceSessionManager.validToken(tokenString)) {
            return false;
        }
        boolean bReturn = true;
        try {
            FilesDAOHE fdhe = new FilesDAOHE();
            fdhe.saveFiles(createForm);
        } catch (Exception en) {
            bReturn = false;
            log.error(en.getMessage());
            //System.out.println(en.getMessage());
        }
        return bReturn;
    }

    // service tra ket qua xu ly ho so PHHQ
    public String searchFiles(@WebParam(name = "tokenString") String tokenString, @WebParam(name = "name") FilesForm form, @WebParam(name = "start") int start, @WebParam(name = "count") int count) {
        GridResult gr = null;
        String xml = "";
        if (!ServiceSessionManager.validToken(tokenString)) {
            return "ERR";
        }
        try {
            FilesNoClobDAOHE fdhe = new FilesNoClobDAOHE();
            gr = fdhe.searchBusinessFiles(form, start, count, null, null);
            xml = toXML(gr);
        } catch (Exception en) {
            log.error(en.getMessage());
            // System.out.println(en.getMessage());
        }
        return xml;
    }
    
    public String login(@WebParam(name = "userName") String userName, @WebParam(name = "password") String password) {
        UsersDAOHE udhe = new UsersDAOHE();
        boolean bReturn = udhe.checkUserPass(userName, password);
        String strReturn = "ERR";
        if (bReturn) {
            strReturn = ServiceSessionManager.generateToken(userName);
        }
        return strReturn;
    }
    
    public boolean logout(@WebParam(name = "tokenString") String tokenString) {
        return ServiceSessionManager.removeToken(tokenString);
    }
    
    private String toXML(GridResult gr) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element root = doc.createElement("lstFiles");
            doc.appendChild(root);
            root.setAttribute("count", gr.getnCount().toString());
            
            if (gr.getLstResult() != null && gr.getLstResult().size() > 0) {
                for (int i = 0; i < gr.getLstResult().size(); i++) {
                    Files form = (Files) gr.getLstResult().get(i);
                    Element file = doc.createElement("file");
                    root.appendChild(file);
                    
                    file.setAttribute("fileId", form.getFileId().toString());
                    file.setAttribute("fileType", form.getFileType().toString());
                    file.setAttribute("fileTypeName", form.getFileTypeName());
                    file.setAttribute("fileCode", form.getFileCode());
                    if (form.getCreateDate() != null) {
                        file.setAttribute("createDate", form.getCreateDate().toString());
                    } else {
                        file.setAttribute("createDate", "");
                    }
                    if (form.getModifyDate() != null) {
                        file.setAttribute("modifyDate", form.getModifyDate().toString());
                    } else {
                        file.setAttribute("modifyDate", "");
                    }
                    if (form.getSendDate() != null) {
                        file.setAttribute("sendDate", form.getSendDate().toString());
                    } else {
                        file.setAttribute("sendDate", "");
                    }
                    if (form.getDeadline() != null) {
                        file.setAttribute("deadline", form.getDeadline().toString());
                    } else {
                        file.setAttribute("deadline", "");
                    }
                    if (form.getStatus() != null) {
                        file.setAttribute("status", form.getStatus().toString());
                    } else {
                        file.setAttribute("status", "");
                    }
                    file.setAttribute("staffRequest", form.getStaffRequest());
                    file.setAttribute("leaderStaffRequest", form.getLeaderStaffRequest());
                    file.setAttribute("leaderRequest", form.getLeaderRequest());
                    file.setAttribute("displayRequest", form.getDisplayRequest());
                    if (form.getReIssueFormId() != null) {
                        file.setAttribute("reIssueFormId", form.getReIssueFormId().toString());
                    } else {
                        file.setAttribute("reIssueFormId", "");
                    }
                    
                    if (form.getAnnouncementId() != null) {
                        file.setAttribute("announcementId", form.getAnnouncementId().toString());
                    } else {
                        file.setAttribute("announcementId", "");
                    }
                    
                    if (form.getDetailProductId() != null) {
                        file.setAttribute("detailProductId", form.getDetailProductId().toString());
                    } else {
                        file.setAttribute("detailProductId", "");
                    }
                    
                    if (form.getTestRegistrationId() != null) {
                        file.setAttribute("testRegistrationId", form.getTestRegistrationId().toString());
                    } else {
                        file.setAttribute("testRegistrationId", "");
                    }
                    file.setAttribute("businessName", form.getBusinessName());
                    
                    if (form.getUserCreateId() != null) {
                        file.setAttribute("userCreateId", form.getUserCreateId().toString());
                    } else {
                        file.setAttribute("userCreateId", "");
                    }
                    file.setAttribute("userCreateName", form.getUserCreateName());
                    
                    if (form.getNodeId() != null) {
                        file.setAttribute("nodeId", form.getNodeId().toString());
                    } else {
                        file.setAttribute("nodeId", "");
                    }
                    
                    if (form.getFlowId() != null) {
                        file.setAttribute("flowId", form.getFlowId().toString());
                    } else {
                        file.setAttribute("flowId", "");
                    }
                    
                    if (form.getPreviousVersion() != null) {
                        file.setAttribute("previousVersion", form.getPreviousVersion().toString());
                    } else {
                        file.setAttribute("previousVersion", "");
                    }
                    
                    if (form.getDeptId() != null) {
                        file.setAttribute("deptId", form.getDeptId().toString());
                    } else {
                        file.setAttribute("deptId", "");
                    }
                    file.setAttribute("deptName", form.getDeptName());
                    file.setAttribute("announcementNo", form.getAnnouncementNo());
                    file.setAttribute("businessLicence", form.getBusinessLicence());
                    file.setAttribute("businessAddress", form.getBusinessAddress());
                    file.setAttribute("productName", form.getProductName());
                    file.setAttribute("manufactureName", form.getManufactureName());
                    file.setAttribute("manufactureAddress", form.getManufactureAddress());
                    file.setAttribute("matchingTarget", form.getMatchingTarget());
                    file.setAttribute("nationName", form.getNationName());
                    file.setAttribute("announcementReceiptPaperId", form.getAnnouncementReceiptPaperId() + "");
                    file.setAttribute("confirmAnnouncementPaperId", form.getConfirmAnnouncementPaperId() + "");
                    file.setAttribute("confirmImportSatistPaperId", form.getConfirmImportSatistPaperId() + "");
                    if (form.getAgencyId() != null) {
                        file.setAttribute("agencyId", form.getAgencyId().toString());
                    } else {
                        file.setAttribute("agencyId", "");
                    }
                    file.setAttribute("agencyName", form.getAgencyName());
                    
                }
                
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            
            transformer.transform(source, result);
            writer.flush();
            return writer.toString();
            
        } catch (Exception en) {
            log.error(en.getMessage());
            return "";
        }
        
    }
    
    public static ANNOUNCESENDDtoType XmlToObjectFile(String xml) {
        try {
            JAXBContext jc = JAXBContext.newInstance(ANNOUNCESENDDtoType.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(xml));
            JAXBElement<ANNOUNCESENDDtoType> je = unmarshaller.unmarshal(streamSource,
                    ANNOUNCESENDDtoType.class);
            return (ANNOUNCESENDDtoType) je.getValue();
        } catch (JAXBException e) {
            log.error(e);
            return null;
        }
    }

//binhnt53 140602 3.1.2.9	Services tiếp nhận hồ sơ xin cấp lại công bố hợp quy/phù hợp
    public boolean receivedReAnnounce(@WebParam(name = "tokenString") String tokenString, @WebParam(name = "createForm") String xml) {
        FilesForm createForm = new FilesForm();
        RE_ANNOUNCE reAnnounce = new RE_ANNOUNCE();
        ERROR err;
        RESULT lstError = new RESULT();
        if (!ServiceSessionManager.validToken(tokenString)) {
            err = new ERROR();
            
            lstError.getLstERROR().add(err);
            return false;
        }
        try {
            reAnnounce = xmlToRE_ANNOUNCE(xml);//try
        } catch (Exception ex) {
            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
            err = new ERROR();
            
            lstError.getLstERROR().add(err);
        }
        //validate data
        if (reAnnounce.getANNOUNCE_NUMBER().length() > 50) {
            err = new ERROR();
            
            lstError.getLstERROR().add(err);
        }
        if (reAnnounce.getBUSSINESS_CODE().length() > 50) {
            err = new ERROR();
            
            lstError.getLstERROR().add(err);
        }
        if (reAnnounce.getDOCUMENT_NUMBER().length() > 50) {
            err = new ERROR();
            
            lstError.getLstERROR().add(err);
        }
        if (reAnnounce.getFILE_ATTP_CODE().length() > 50) {
            err = new ERROR();
            
            lstError.getLstERROR().add(err);
        }
        if (reAnnounce.getFILE_CODE().length() > 50) {
            err = new ERROR();
            
            lstError.getLstERROR().add(err);
        }
        if (reAnnounce.getTYPE().length() > 50) {
            err = new ERROR();
            
            lstError.getLstERROR().add(err);
        }
        //!//validate data
        createForm.setFileCode(reAnnounce.getFILE_ATTP_CODE());
        Procedure procedurebo = getFileType(reAnnounce.getTYPE());
        if (procedurebo != null) {
            createForm.setFileType(procedurebo.getProcedureId());
            createForm.setFileTypeName(procedurebo.getName());
        }
        ReIssueFormForm reIssueFormForm = new ReIssueFormForm();
        reIssueFormForm = reAnnounce.toReIssueFormForm();
        createForm.setReIssueForm(reIssueFormForm);
        createForm.setStatus(com.viettel.common.util.Constants.FILE_STATUS.NEW);
        boolean bReturn = true;
        try {
            FilesDAOHE fdhe = new FilesDAOHE();
            fdhe.saveFiles(createForm);
        } catch (Exception ex) {
            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
            err = new ERROR();
//            err.setERROR_ID(0L);
//            err.setERROR_CODE("none");
//            err.setERROR_NAME("none");
            lstError.getLstERROR().add(err);
            bReturn = false;
        }
        return bReturn;
    }

    //binhnt53 140602 3.1.2.5	Services cung cấp thông tin số công bố hợp quy/phù hợp	
    public String sendAssignmentQuery(@WebParam(name = "tokenString") String tokenString, @WebParam(name = "createForm") String xml) {
        ERROR err;
        RESULT lstError = new RESULT();
        ASSIGNMENT_QUERY reAnnounce = xmlToASSIGNMENT_QUERY(xml);
        AnnouncementReceiptPaper annrpbo = null;
        AnnouncementReceiptPaperDAOHE annrpdaohe = new AnnouncementReceiptPaperDAOHE();
        //validate
        if (reAnnounce.getANNOUNCE_NUMBER().length() > 50) {
            err = new ERROR();
            lstError.getLstERROR().add(err);
        }
        if (reAnnounce.getBUSSINESS_A().length() > 50) {
            err = new ERROR();
            lstError.getLstERROR().add(err);
        }
        if (reAnnounce.getBUSSINESS_B().length() > 50) {
            err = new ERROR();
            lstError.getLstERROR().add(err);
        }
        //!validate
        if (reAnnounce != null) {
            annrpbo = annrpdaohe.getAnnouncementReceiptPaperToWs(reAnnounce.getBUSSINESS_A(), reAnnounce.getANNOUNCE_NUMBER());
        }
        ANNOUNCERESULTDto annouceResult = new ANNOUNCERESULTDto();
        annouceResult.setCOMMENTS("");
        annouceResult.setNSWFILECODE("");
        if (annrpbo != null) {
            RESULTPAGER resultpaper = new RESULTPAGER();
            Files filesbo = new Files();
            FilesDAOHE fdaohe = new FilesDAOHE();
            filesbo = fdaohe.getFilesByAnnrpId(annrpbo.getAnnouncementReceiptPaperId());
            if (filesbo != null) {
                resultpaper.setBUSINESSADDRESS(filesbo.getBusinessAddress());
                annouceResult.setDEPTCODE(getDepartment(filesbo.getDeptId()).getDeptCode());
                annouceResult.setSIGNDATE(DateTimeUtils.convertDateToString(filesbo.getSignDate(), "dd/MM/yyyy"));
                annouceResult.setSTATUSCODE(filesbo.getStatus().toString());
            }
            resultpaper.setBUSINESSEMAIL(annrpbo.getEmail());
            resultpaper.setBUSINESSFAX(annrpbo.getFax());
            resultpaper.setBUSINESSNAME(annrpbo.getBusinessName());
            resultpaper.setBUSINESSPHONE(annrpbo.getTelephone());
            Business business = new Business();
            BusinessDAOHE businessdaohe = new BusinessDAOHE();
            business = businessdaohe.findById(annrpbo.getBusinessId());
            resultpaper.setBUSSINESSCODE(business.getBusinessTaxCode());
            resultpaper.setEFFECTIVEDATE(DateTimeUtils.convertDateToString(annrpbo.getEffectiveDate(), "dd/MM/yyyy"));
            resultpaper.setMANUFACTUREADD(annrpbo.getManufactureAdd());
            resultpaper.setMANUFACTURENAME(annrpbo.getManufactureName());
            resultpaper.setNATIONCODE(getNationCode(annrpbo.getNationName()));
            resultpaper.setPRODUCTNAME(annrpbo.getProductName());
            resultpaper.setRECEIPTDATE(DateTimeUtils.convertDateToString(annrpbo.getReceiptDate(), "dd/MM/yyyy"));
            resultpaper.setRECEIPTDEPTNAME(annrpbo.getReceiptDeptName());
            resultpaper.setRECEIPTNO(annrpbo.getReceiptNo());
            annouceResult.setSIGNERNAME(annrpbo.getSignerName());
            if (resultpaper != null) {
                annouceResult.setRESULTPAGER(resultpaper);
            }
        }
        return assignmentQueryToXml(annouceResult);
    }

    //binhnt53 140602 3.1.2.16	Services cung cấp danh sách số xác nhận công bố hợp quy/phù hợp của doanh nghiệp
    public String sendLstAssignmentQuery(@WebParam(name = "tokenString") String tokenString, @WebParam(name = "annNo") String annNo) {
        ERROR err;
        RESULT lstError = new RESULT();
        if (!ServiceSessionManager.validToken(tokenString)) {
            err = new ERROR();
            //bong truong thong tin loi
            err.setERROR_CODE("Validate");
            lstError.getLstERROR().add(err);
        }
        ANNOUCE_RECEIVE annouceReceive = new ANNOUCE_RECEIVE();
        AnnouncementReceiptPaper arpbo = null;
        AnnouncementReceiptPaperDAOHE arpdaohe = new AnnouncementReceiptPaperDAOHE();
        arpbo = arpdaohe.getARPToWsRAQ(annNo);
        if (arpbo != null) {
            Files filesbo = new Files();
            FilesDAOHE fdaohe = new FilesDAOHE();
            filesbo = fdaohe.getFilesByAnnrpId(arpbo.getAnnouncementReceiptPaperId());
            ANNOUNCE_DETAILS ad = new ANNOUNCE_DETAILS();
            if (filesbo != null) {
                ad.setCONFIRM_DEPT_ID(filesbo.getAgencyId());
                ad.setCONFIRM_NO(arpbo.getReceiptNo());
                ad.setCONFIRM_DATE(arpbo.getReceiptDate());
                ad.setCONFIRMER_ID(filesbo.getLeaderStaffSignId());
                ad.setCONFIRM_START_DATE(arpbo.getEffectiveDate());
                ad.setCONFIRM_END_DATE(arpbo.getEffectiveDate());
            }
            annouceReceive.setaNNOUNCE_DETAILS(ad);
        }
        return annouceReceiveToXml(annouceReceive);
    }

    //binhnt53 140602 3.1.2.13	Services Gửi thông tin giấy xác nhận công bố hợp quy/phù hợp	
    //chua hoan thanh
    public String sendInfoARP(@WebParam(name = "tokenString") String tokenString, @WebParam(name = "annNo") String xml) {
//        ERROR err;
//        RESULT lstError = new RESULT();
//        if (!ServiceSessionManager.validToken(tokenString)) {
//            ERROR error = new ERROR();
//            //bong truong thong tin loi
//            error.setERROR_CODE("Validate");
//        }
//        ASSIGNMENT_QUERY reAnnounce = xmlToASSIGNMENT_QUERY(xml);
        AnnouncementReceiptPaperDAOHE annrpdaohe = new AnnouncementReceiptPaperDAOHE();
        AnnouncementReceiptPaper annrpbo = null;
        annrpbo = annrpdaohe.getAnnouncementReceiptPaperToWs(xml);
//        //validate
//        if (reAnnounce.getANNOUNCE_NUMBER().length() > 50) {
//            err = new ERROR();
//
//            lstError.getLstERROR().add(err);
//        }
//        if (reAnnounce.getBUSSINESS_A().length() > 50) {
//            err = new ERROR();
//
//            lstError.getLstERROR().add(err);
//        }
//        if (reAnnounce.getBUSSINESS_B().length() > 50) {
//            err = new ERROR();
//
//            lstError.getLstERROR().add(err);
//        }
//        //!validate
//        if (reAnnounce != null) {
//            annrpbo = annrpdaohe.getAnnouncementReceiptPaperToWs(reAnnounce.getBUSSINESS_A(), reAnnounce.getANNOUNCE_NUMBER());
//        }
//        ANNOUNCERESULTDto annouceResult = new ANNOUNCERESULTDto();
//        if (annrpbo != null) {
//            Files filesbo = new Files();
//            FilesDAOHE fdaohe = new FilesDAOHE();
//            filesbo = fdaohe.getFilesByAnnrpId(annrpbo.getAnnouncementReceiptPaperId());
//            if (filesbo != null) {
//                annouceResult.setCOMMENTS(null);
//                annouceResult.setDEPTCODE(filesbo.getDeptId().toString());
//                annouceResult.setSIGNDATE(filesbo.getSignDate().toString());
//                annouceResult.setSIGNERNAME(filesbo.getStaffSigningName().toString());
//                RESULTPAGER rESULTPAGER = new RESULTPAGER();
//                rESULTPAGER.setBUSINESSADDRESS(filesbo.getBusinessAddress());
//                annouceResult.setRESULTPAGER(null);
//            }
//        }
        if (annrpbo != null) {
            return ARPToXml(annrpbo);
        }
        return "";
    }

    //binhnt53 3.1.2.11	Services tiếp nhận kết quả xử lý đơn xác nhận đạt yêu cầu nhập khẩu
    public String receivedTestRegistration(@WebParam(name = "tokenString") String tokenString, @WebParam(name = "annNo") String xml) {
        FilesForm createForm = new FilesForm();
        FILERESULTSDto fILERESULTSDto = new FILERESULTSDto();
        if (!ServiceSessionManager.validToken(tokenString)) {
            return "";
        }
        try {
            fILERESULTSDto = xmlToFILERESULTSDto(xml);//try
        } catch (Exception ex) {
            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        //!//validate data
        //createForm.setFileCode(testRegistrationType.getFILE_ATTP_CODE());
        Procedure procedurebo = getFileType("confirmSatisfactory");
        if (procedurebo != null) {
            createForm.setFileType(procedurebo.getProcedureId());
            createForm.setFileTypeName(procedurebo.getName());
        }
        TestRegistrationForm testRegistrationForm = new TestRegistrationForm();
        createForm.setTestRegistration(testRegistrationForm);
        createForm.setStatus(com.viettel.common.util.Constants.FILE_STATUS.NEW);
        try {
            FilesDAOHE fdhe = new FilesDAOHE();
            fdhe.saveFiles(createForm);
        } catch (Exception ex) {
            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    // Convert xml to object Complanints
    public static COMPLAINTS XmlToObjectComplaints(String xml) {
        try {
            JAXBContext jc = JAXBContext.newInstance(COMPLAINTS.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(xml));
            JAXBElement<COMPLAINTS> je = unmarshaller.unmarshal(streamSource,
                    COMPLAINTS.class);

            return (COMPLAINTS) je.getValue();

        } catch (JAXBException e) {
            log.error(e);
            return null;
        }
    }

    /**
     * Web service operation
     * @param userId
     * @param fileId
     * @param signType
     * @param indexFile
     * @return
     */
    public String deletePdf(String userId, String fileId, String signType, Integer indexFile) {
        try {
            if(!deleteFileSign(userId, fileId, signType, indexFile)){
                return "error";
            }
        } catch (Exception ex) {
            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }

        return "done";
    }

    /**
     * Web service operation
     */
    /*
     public byte[] downloadPdfByte() {
     ResourceBundle rb1 = ResourceBundle.getBundle("config");
     String PATH1 = rb1.getString("sign_download");
     File folder = new File(PATH1);
     File[] listOfFiles = folder.listFiles();
     byte[] pdf = null;
     for (File file : listOfFiles) {
     if (file.isFile()) {
     try {
     pdf = read(file);
     } catch (IOException ex) {
     Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
     }
     }
     }

     if ("error".equals(deletePdf())) {
     return null;
     }
     return pdf;
     }
     public String getPdfName() {
     ResourceBundle rb1 = ResourceBundle.getBundle("config");
     String pathName = rb1.getString("sign_download");
     File folder = new File(pathName);
     File[] listOfFiles = null;
     long startTime = System.currentTimeMillis(); //fetch starting time   
     String timeOut = rb1.getString("time_out_cks");
     while ((System.currentTimeMillis() - startTime) < Long.parseLong(timeOut))  {
     listOfFiles = folder.listFiles();
     if (listOfFiles.length != 0) {
     break;
     }
     }
     String name = "";
     for (File file : listOfFiles) {
     if (file.isFile()) {
     name = file.getName();
     }
     }


     return name;
     }
     */
    /**
     * getObjectSign
     *
     * @param userId
     * @param fileId
     * @param signType
     * @param haveDllLogin
     * @return
     */
    public List<PdfSign> getObjectSign(String userId, String fileId, String signType, Boolean haveDllLogin) {
        try {
            List<PdfSign> lstFile = new ArrayList<>();
            PdfSign object;
            ResourceBundle rb1 = ResourceBundle.getBundle("config");
            String pdfFilePath = rb1.getString("sign_download");
            String imageSignPath = rb1.getString("sign_image");
            File folder = new File(pdfFilePath);
            File[] listOfFiles = null;
            long startTime = System.currentTimeMillis(); //fetch starting time   
            Long timeOut = Long.parseLong(rb1.getString("time_out_cks"));
            Boolean checkExist = false;
            String roleSign = "";
            String name = "";
            Integer indexFile = 0;
            byte[] pdf = null;
            Date sysDate = null;
            VoAttachsDAOHE daoHe = new VoAttachsDAOHE();
            try {
                sysDate = daoHe.getSysdate();
            } catch (Exception ex) {
                Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }

            while ((System.currentTimeMillis() - startTime) < timeOut) {
                listOfFiles = folder.listFiles();
                if (listOfFiles.length != 0) {
                    for (File file : listOfFiles) {
                        if (file.isFile()) {
                            object = new PdfSign();
                            indexFile = 0;
                            try {
                                // Check file name
                                String[] splitName = file.getName().split("_");
                                if (splitName.length != 4 && splitName.length != 5) {
                                    continue;
                                }
                                // Compare User Id
                                if (!splitName[1].equals(userId)) {
                                    continue;
                                }
                                // Compare User Id
                                if (!splitName[2].equals(fileId)) {
                                    continue;
                                }
                                // Check sign Type
                                if (!splitName[3].startsWith(signType)) {
                                    continue;
                                }

                                checkExist = true;

                                if (splitName.length == 5) {
                                    String splitTypeStr = splitName[4].substring(0, splitName[4].indexOf(".pdf"));
                                    indexFile = Integer.parseInt(splitTypeStr);
                                }
                                roleSign = splitName[0];
                                name = file.getName();
                                pdf = read(file);

                                if (!checkExist || "".equals(name)) {
                                    Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "FileWS: Có lỗi xảy ra trong quá trình tìm kiếm xử lý File trên Applet: File không tồn tại");
                                    return null;
                                }

                                object.setPdfName(name);
                                object.setPdfFile(compress(pdf));
                                object.setIndexFile(indexFile);
                                object.setServerDate(sysDate);
                                // Delete files
                                if ("error".equals(deletePdf(userId, fileId, signType, indexFile))) {
                                    Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "FileWS: Có lỗi xảy ra trong quá trình tìm kiếm xử lý File trên Applet: Xóa file tạm trên Server không thành công");
                                    return null;
                                }

                                //server linux
                                byte[] imageStamp = null;
                                byte[] imageSign = null;
                                byte[] dllLoginBcy = null;
                                try {
                                    if (roleSign.equals("VT")) {
                                        imageStamp = read(new File(imageSignPath + "attpStamp.png"));
                                    } else if (roleSign.equals("LD")) {
                                        imageSign = read(new File(imageSignPath + userId + ".png"));
                                    }
                                    // Get dll login
                                    if (!haveDllLogin) {
                                        dllLoginBcy = read(new File(imageSignPath + "MEtoken.dll"));
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                object.setImageSign(compress(imageSign));
                                object.setImageStamp(compress(imageStamp));
                                if (!haveDllLogin && lstFile.isEmpty()) {
                                    // Set only One time
                                    object.setDllLoginBcy(compress(dllLoginBcy));
                                }
                                lstFile.add(object);

                            } catch (IOException ex) {
                                Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "FileWS: Có lỗi xảy ra trong quá trình tìm kiếm xử lý File trên Applet: " + ex.getMessage());
                            }
                        }
                    }
                    if (checkExist) {
                        break;
                    }
                } else {
                    Thread.sleep(100);
                }
            }

            return lstFile;
        } catch (InterruptedException | NumberFormatException ex) {
            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Boolean upload(String fileName, byte[] imageBytes) {
        try {
            // Upload file
            ResourceBundle rb1 = ResourceBundle.getBundle("config");
            String PATH1 = rb1.getString("sign_upload");
            String uploadPath = rb1.getString("upload_path");

            // Save info to DB
            String[] parts = fileName.split("_");
            if (parts.length != 4 && parts.length != 5) {
                return false;
            }
            String signType = "";
            Integer indexFile = 0;
            if (parts.length == 4) {
                signType = parts[3].substring(0, parts[3].indexOf(".pdf"));
            } else if (parts.length == 5) {
                signType = parts[3];
                indexFile = Integer.parseInt(parts[4].substring(0, parts[4].indexOf(".pdf")));
            }

            String fileId = parts[2];
            if ("".equals(fileId) || "".equals(signType)) {
                Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: Tên File không đúng định dạng");
                return false;
            }

            VoAttachsDAOHE vdhe = new VoAttachsDAOHE();
            if (parts[0].equals("LD")) {
                String filePath = PATH1 + fileName;
                FileOutputStream fos = new FileOutputStream(filePath);
                BufferedOutputStream outputStream = new BufferedOutputStream(fos);
                outputStream.write(decompress(imageBytes));
                outputStream.close();

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
                        return false;
                    }

                    voUpload.setAttachName("Bancongbo_" + fileName);
                } else if (signType.equals("CVBS")) {
                    voUpload.setObjectType(71L);
                    voUpload.setAttachName("CongvanSdbs_" + fileName);
                } else {
                    Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: Tên File không đúng định dạng");
                    return false;
                }
                voUpload.setAttachPath(uploadPath + fileName);
                vdhe.saveDbNotCommit(voUpload);
            } else if (parts[0].equals("VT")) {
                Date newDate = new Date();
                if (parts.length == 4) {
                    fileName = parts[0] + "_" + parts[1] + "_" + parts[2] + "_" + newDate.getTime() + "_" + parts[3];
                } else if (parts.length == 5) {
                    fileName = parts[0] + "_" + parts[1] + "_" + parts[2] + "_" + newDate.getTime() + "_" + parts[3] + "_" + parts[4];
                }

                String filePath = PATH1 + fileName;
                FileOutputStream fos = new FileOutputStream(filePath);
                BufferedOutputStream outputStream = new BufferedOutputStream(fos);
                outputStream.write(decompress(imageBytes));
                outputStream.close();

                // Delete old file BCB
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
                        return false;
                    }
                    voUpload.setAttachName("Bancongbo_" + fileName);
                } else if (signType.equals("CVBS")) {
                    voUpload.setObjectType(71L);
                    voUpload.setAttachName("CongvanSdbs_" + fileName);
                } else {
                    Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, "Lỗi trong quá trình upload file ký lên server: Tên File không đúng định dạng");
                    return false;
                }

                voUpload.setAttachPath(uploadPath + fileName);
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
                    return false;
                }
            }
            vdhe.commitDb();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public byte[] compress(final byte[] data) {
        if (data == null || data.length == 0) {
            return new byte[0];
        }
        try (final ByteArrayOutputStream out = new ByteArrayOutputStream(data.length)) {
            final Deflater deflater = new Deflater();
            deflater.setInput(data);

            deflater.finish();
            final byte[] buffer = new byte[1024];
            while (!deflater.finished()) {
                out.write(buffer, 0, deflater.deflate(buffer));
            }

            return out.toByteArray();
        } catch (final IOException e) {
            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return data;
        }
    }

    public byte[] decompress(final byte[] data) {
        if (data == null || data.length == 0) {
            return new byte[0];
        }

        final Inflater inflater = new Inflater();
        inflater.setInput(data);

        try (final ByteArrayOutputStream out = new ByteArrayOutputStream(data.length)) {
            final byte[] buffer = new byte[1024];
            while (!inflater.finished()) {
                out.write(buffer, 0, inflater.inflate(buffer));
            }

            return out.toByteArray();
        } catch (final IOException | DataFormatException e) {
            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, e.getMessage());
            return data;
        }
    }
}
