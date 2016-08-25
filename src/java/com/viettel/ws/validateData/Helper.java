/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.validateData;

import com.viettel.common.util.Constants;
import com.viettel.common.util.ResourceBundleUtil;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.DAOHE.FilesDAOHE;
import com.viettel.hqmc.DAOHE.XmlMessageDAOHE;
import com.viettel.ws.BO.*;
import com.viettel.ws.FORM.ERROR;
import com.viettel.ws.FORM.RESULT;
import com.viettel.ws.ServiceSessionManager;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebParam;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Administrator
 */
public class Helper {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Helper.class);
    private final SimpleDateFormat formatterYear = new SimpleDateFormat("yyyy");
    private final SimpleDateFormat formatterDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public Helper() {

    }

//    private File xmlErrType() {
//        URL path = Helper.class.getProtectionDomain().getCodeSource().getLocation();
//        String stringPath = path.toString();
//        stringPath = stringPath.replaceAll("%20", " ");
//        stringPath = stringPath.substring(6);
//        stringPath = stringPath.replaceAll("/com/viettel/ws/validateData/Helper.class", "/Validate.xml");
//        File rs = new File(stringPath);
//        return rs;
//    }
//    public Boolean Validate_Type(String value, String type) {
//        switch (type) {
//            case "Int":
//                Pattern pi = Pattern.compile("^\\d+$");
//                Matcher mi = pi.matcher(value);
//                return mi.matches();
//            case "Tax":
//                return value.length() == 10 || value.length() == 13;
//            case "Phone":
////                String PHONE_PATTERN = "^(\\+84)|(84)|0+[1-9]{1}+[0-9]{8,9}$";
////                Pattern pp = Pattern.compile(PHONE_PATTERN);
////                Matcher mp = pp.matcher(value);
////                return mp.matches();
//            case "DateTime":
//                String v = value.trim().substring(0, 10);
//
//                String DATE_PATTERN = "^\\d{4}-((0[1-9])|(1[012]))-((0[1-9]|[12]\\d)|3[01])$";
//                //"^((((0[1-9]|[12]\\d|3[01])/(0[13578]|1[02])/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)/(0[13456789]|1[012])/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])/02/((19|[2-9]\\d)\\d{2}))|(29/02/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$)|((((0[1-9]|[12]\\d|3[01])-(0[13578]|1[02])-((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)-(0[13456789]|1[012])-((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])-02-((19|[2-9]\\d)\\d{2}))|(29-02-((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$)";
//                Pattern pd = Pattern.compile(DATE_PATTERN);
//                Matcher md = pd.matcher(v);
//                return md.matches();
//            case "Email":
//                String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//                Pattern pe = Pattern.compile(EMAIL_PATTERN);
//                Matcher me = pe.matcher(value);
//                return me.matches();
//            case "FileName":
//                int k = value.lastIndexOf(".");
//                int m = 0;
//                if (k > 0) {
//                    m = value.substring(k, value.length() - 1).length();
//                }
//                return m == 3 || m == 4;
//            case "Number":
//                String NUMBER_PATTERN = "^([0-9]{1,6}[.]{1}[0-9]{3})|([0-9]{1,9})$";
//                Pattern pn = Pattern.compile(NUMBER_PATTERN);
//                Matcher mn = pn.matcher(value);
//            case "Bool":
//                Boolean rs = "1".equals(value) || "0".equals(value);
//                return rs;
//            default:
//                return true;
//        }
//    }
//    public static boolean canBePhoneNumber(String phonenumber) {
//        if (phonenumber == null || "".equals(phonenumber.trim())) {
//            return false;
//        }
//        Pattern p = Pattern.compile("^(09|01|849|841|\\+849|\\+841)[0-9]+$");
//        Matcher m = p.matcher(phonenumber);
//
//        boolean matchFound = m.matches();
//        return matchFound;
//    }
//    public static boolean validateMobileNumber(String mobileNumber) {
//        mobileNumber = mobileNumber.trim();
//        final String prefix849 = "849";
//        final String prefix849plus = "+849";
//        final String prefix841 = "841";
//        final String prefix841plus = "+841";
//        final String prefix09 = "09";
//        final String prefix01 = "01";
//        boolean rs = false;
//        if (mobileNumber == null || "".equals(mobileNumber.trim())
//                && canBePhoneNumber(mobileNumber)) {
//            int length = mobileNumber.length();
//            if ((length == 10 && mobileNumber.startsWith(prefix09))
//                    || (length == 11 && (mobileNumber.startsWith(prefix01) || mobileNumber.startsWith(prefix849)))
//                    || (length == 12 && (mobileNumber.startsWith(prefix841) || mobileNumber.startsWith(prefix849plus)))
//                    || (length == 13 && mobileNumber.startsWith(prefix841plus))) {
//                rs = true;
//            }
//        }
//        return rs;
//    }
//    public static ANNOUNCESENDDtoType XmlToObjectFile(String xml) {
//        try {
//            JAXBContext jc = JAXBContext.newInstance(ANNOUNCESENDDtoType.class);
//            Unmarshaller unmarshaller = jc.createUnmarshaller();
//            StreamSource streamSource = new StreamSource(new StringReader(xml));
//            JAXBElement<ANNOUNCESENDDtoType> je = unmarshaller.unmarshal(streamSource,
//                    ANNOUNCESENDDtoType.class);
//            return (ANNOUNCESENDDtoType) je.getValue();
//        } catch (JAXBException e) {
//            log.error(e);
//            return null;
//        }
//    }
//HieuLd5
//    private List<ERRORDto> getErrorList(String field, String value, String parentObject, File xmlErrorType, int loop) {
//        try {
//            List<ErrorType> errTypes = new ArrayList<>();
//            List<ERRORDto> rs = new ArrayList<>();
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            dbFactory.setFeature(FEATURE_GENERAL_ENTITIES, false);
//            dbFactory.setFeature(FEATURE_PARAMETER_ENTITIES, false);
//            dbFactory.setXIncludeAware(false);
//            dbFactory.setExpandEntityReferences(false);
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = (Document) dBuilder.parse(xmlErrorType);
//            doc.getDocumentElement().normalize();
//            NodeList nList = doc.getElementsByTagName(field);
//            if (nList != null && nList.getLength() > 0) {
//                String Loop = Integer.toString(loop);
//                StringBuilder buf = new StringBuilder(Loop);
//                while (buf.length() < 8) {
//                    buf.insert(0, '0');
//                }
//                Loop = buf.toString();
//                Helper hp = new Helper();
//                for (int temp = 0; temp < nList.getLength(); temp++) {
//
//                    Node nNode = nList.item(temp);
//                    if (nNode.getParentNode().getNodeName().equals(parentObject)) {
//                        Element e = (Element) nNode;
//                        String[] errTypeIds = e.getAttribute("Id").split(";");
//                        String name = e.getAttribute("Name");
//                        String type = e.getAttribute("Type");
//                        int length = "".equals(e.getAttribute("Length")) ? 0 : Integer.parseInt(e.getAttribute("Length"));
//                        String table = e.getAttribute("Table");
//                        if (errTypeIds != null && errTypeIds.length > 0) {
//                            for (String id : errTypeIds) {
//                                errTypes.add(new ErrorType(Integer.parseInt(id), name, length, type, table));
//                            }
//                        }
//                    }
//                    break;
//                }
//                if (errTypes != null && errTypes.size() > 0) {
//                    Boolean err = false;
//                    for (ErrorType errType : errTypes) {
//                        if (!err) {
//                            switch (errType.Id) {
//                                case 2:
//                                    if (value == null || value.trim().length() < 1) {
//                                        rs.add(new ERRORDto("S001-" + field + "-" + Loop, errType.Name + " Thiếu thông tin (Null)"));
//                                        err = true;
//                                    }
//                                    break;
//                                case 3:
//                                    if (value != null && value.trim().length() > errType.Length) {
//                                        rs.add(new ERRORDto("S002-" + field + "-" + Loop, errType.Name + " Độ dài vượt quá " + " - " + errType.Length));
//                                        err = true;
//                                    }
//                                    break;
//                                case 4:
//                                    if (!hp.Validate_Type(value, errType.Type)) {
//                                        rs.add(new ERRORDto("S002-" + field + "-" + Loop, errType.Name + " Sai kiểu dữ liệu: " + errType.Type));
//                                        err = true;
//                                    }
//                                    break;
//                                case 5:
//
//                                    if (errType.Table != null && !"".equals(errType.Table.trim()) && !value.isEmpty()) {
//                                        String[] T = errType.Table.split(";");
//                                        if (T.length > 1) {
////                                            String hql = "Select " + T[1] + " from " + T[0] + " where " + T[1] + " = '"+value+"'";
//                                            String hql = "Select " + T[1] + " from " + T[0] + " where " + T[1] + " = ?";
//                                            if (T.length == 3) {
//                                                hql += " and " + T[2];
//                                            }
//                                            Element econn = (Element) doc.getElementsByTagName("CONNECTION").item(0);
//                                            Class.forName("oracle.jdbc.driver.OracleDriver");
//                                            Connection conn = DriverManager.getConnection(econn.getAttribute("Url"), econn.getAttribute("UserName"), econn.getAttribute("Password"));
//                                            PreparedStatement preparedStatement = conn.prepareStatement(hql);
//                                            preparedStatement.setString(1, value);
//                                            ResultSet emps = preparedStatement.executeQuery();
//                                            if (!emps.next()) {
//                                                Boolean k = false;
//                                                switch (field) {
////                                                    case "deptcode":
////                                                        k = PROCESSDEPT_Insert(processdeptGetByCode(value), conn);
////                                                        break;
////                                                    case "filetypecode":
////                                                        k = FILETYPE_Insert(filetypeGetByCode(value), conn);
////                                                        break;
////                                                    case "statuscode":
////                                                        k = STATUS_Insert(statusGetByCode(new Integer(value)), conn);
////                                                        break;
////                                                    case "producttypecode":
////                                                        k = PRODUCTTYPE_Insert(producttypeGetByCode(value), conn);
////                                                        break;
////                                                    case "targetcode":
////                                                        k = TECHNICALSTANDARD_Insert(technicalstandardGetByCode(value), false, conn);
////                                                        break;
////                                                    case "unitcode":
////                                                        k = UNIT_Insert(unitGetByCode(value), conn);
////                                                        break;
////                                                    case "technicalregulation":
////                                                        k = TECHNICALSTANDARD_Insert(technicalstandardGetByCode(value), true, conn);
////                                                        break;
//                                                    default:
//                                                        break;
//                                                }
//                                                if (!k) {
//                                                    rs.add(new ERRORDto("E001-" + field + "-" + Loop, errType.Name + " Không có trong CSDL, không thể đồng bộ từ Gateway danh mục: " + T[0] + " - Trường " + T[1] + " - Giá trị: " + value));
//                                                }
//                                            }
//                                        }
//                                    }
//                                default:
//                                    break;
//                            }
//                        }
//
//                    }
//                }
//            }
//            return rs;
//        } catch (Exception ex) {
//            return null;
//        }
//    }
//Hieuld5
//    public List<ERRORDto> validateObj(Object input, int loop, File xmlErrType) {//hàm thực hiện validate obj đối với obj đầu vào obj.
//        List<ERRORDto> eRRORLIST = new ArrayList<>();
//        try {
//            String parentObject = input.getClass().getName();
//            parentObject = parentObject.substring(parentObject.lastIndexOf(".")).replace(".", "");
//            for (Field fieldInput : input.getClass().getDeclaredFields()) {
//                String field = fieldInput.getName().toLowerCase();
//                String value = fieldInput.get(input) == null ? null : fieldInput.get(input).toString();
//                List<ERRORDto> err = getErrorList(field, value, parentObject, xmlErrType, loop);
//                if (err != null && err.size() > 0) {
//                    eRRORLIST.addAll(err);
//                }
//            }
//        } catch (IllegalArgumentException | IllegalAccessException ex) {
//        }
//        //list all require here
//        return eRRORLIST;
//
//    }
//    public List<ERRORDto> validateANNOUNCESENDDto(ANNOUNCESENDDtoType file) {
//        File xmlErrType = xmlErrType();
//        List<ERRORDto> errList = new ArrayList<>();
//        if (file != null) {
//            errList.addAll(validateObj(file, 0, xmlErrType));
//            MATCHINGTARGETLISTType matching = file.getMATCHINGTARGETLIST();
//            if (matching != null) {
//                List<MATCHINGTARGETDtoType> matchingtargetDto = matching.getMATCHINGTARGETDto();
//                if (matchingtargetDto != null && matchingtargetDto.size() > 0) {
//                    for (int m = 0; m < matchingtargetDto.size(); m++) {
//                        errList.addAll(validateObj(matchingtargetDto.get(m), m, xmlErrType));
//                    }
//                }
//            }
//            MAINLYTARGETSLISTType mainly = file.getMAINLYTARGETSLIST();
//            if (mainly != null) {
//                List<MAINLYTARGETSDtoType> mainlytargetsDto = mainly.getMAINLYTARGETSDto();
//                if (mainlytargetsDto != null && mainlytargetsDto.size() > 0) {
//                    for (int m = 0; m < mainlytargetsDto.size(); m++) {
//                        errList.addAll(validateObj(mainlytargetsDto.get(m), m, xmlErrType));
//                    }
//                }
//            }
//            BIOTARGETLISTType bio = file.getBIOTARGETLIST();
//            if (bio != null) {
//                List<BIOTARGETDtoType> biotargetDto = bio.getBIOTARGETDto();
//                if (biotargetDto != null && biotargetDto.size() > 0) {
//                    for (int m = 0; m < biotargetDto.size(); m++) {
//                        errList.addAll(validateObj(biotargetDto.get(m), m, xmlErrType));
//                    }
//                }
//            }
//            HEAVYMETALLISTType heavy = file.getHEAVYMETALLIST();
//            if (heavy != null) {
//                List<HEAVYTARGETDtoType> heavytargetDto = heavy.getHEAVYTARGETDto();
//                if (heavytargetDto != null && heavytargetDto.size() > 0) {
//                    for (int m = 0; m < heavytargetDto.size(); m++) {
//                        errList.addAll(validateObj(heavytargetDto.get(m), m, xmlErrType));
//                    }
//                }
//            }
//            UNEXCHEMICALLISTType chemlist = file.getUNEXCHEMICALLIST();
//            if (chemlist != null) {
//                List<CHEMTARGETDtoType> chemtargetDto = chemlist.getCHEMTARGETDto();
//                if (chemtargetDto != null && chemtargetDto.size() > 0) {
//                    for (int m = 0; m < chemtargetDto.size(); m++) {
//                        errList.addAll(validateObj(chemtargetDto.get(m), m, xmlErrType));
//                    }
//                }
//            }
//            IMPACTTARGETSLISTType impact = file.getIMPACTTARGETSLIST();
//            if (impact != null) {
//                List<IMPACTTARGETDtoType> impacttargetDto = impact.getIMPACTTARGETDto();
//                if (impacttargetDto != null && impacttargetDto.size() > 0) {
//                    for (int m = 0; m < impacttargetDto.size(); m++) {
//                        errList.addAll(validateObj(impacttargetDto.get(m), m, xmlErrType));
//                    }
//                }
//            }
//            QUALITYCONTROLPLANType quality = file.getQUALITYCONTROLPLAN();
//            if (quality != null) {
//                List<CONTROLPLANDtoType> controlplanDto = quality.getCONTROLPLANDto();
//                if (controlplanDto != null && controlplanDto.size() > 0) {
//                    for (int m = 0; m < controlplanDto.size(); m++) {
//                        errList.addAll(validateObj(controlplanDto.get(m), m, xmlErrType));
//                    }
//                }
//            }
//            ATTACHMENTSType attach = file.getATTACHMENTS();
//            if (attach != null) {
//                List<ATTACHMENTSDtoType> attachmentsDto = (List<ATTACHMENTSDtoType>) attach.getATTACHMENTSDto();
//                if (attachmentsDto != null && attachmentsDto.size() > 0) {
//                    for (int m = 0; m < attachmentsDto.size(); m++) {
//                        errList.addAll(validateObj(attachmentsDto.get(m), m, xmlErrType));
//                    }
//                }
//            } else {
//                errList.add(new ERRORDto("A002-ATTACHMENTST-000", "Không có tệp đính kèm"));
//            }
//        }
//        return errList;
//    }
//    public String senddresponsedToToXml(Object obj) {
//        String result = "";
//        java.io.StringWriter sw = new StringWriter();
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(SENDRESPONSEDto.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//// output pretty printed
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            jaxbMarshaller.marshal(obj, sw);
//            result = sw.toString();
//        } catch (JAXBException ex) {
//            result = "Lỗi";
//        }
//        return result;
//    }
//    public String getMATCHINGTARGET_NAME(List<MATCHINGTARGETDtoType> list) {
//        try {
//            String rs = "";
//            String hql = "Select VIETNAMESE_NAME from TECHNICAL_STANDARD where STANDARD_CODE = ?";
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            dbFactory.setFeature(FEATURE_GENERAL_ENTITIES, false);
//            dbFactory.setFeature(FEATURE_PARAMETER_ENTITIES, false);
//            dbFactory.setXIncludeAware(false);
//            dbFactory.setExpandEntityReferences(false);
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = (Document) dBuilder.parse(xmlErrType());
//            Element econn = (Element) doc.getElementsByTagName("CONNECTION").item(0);
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection conn = DriverManager.getConnection(econn.getAttribute("Url"), econn.getAttribute("UserName"), econn.getAttribute("Password"));
//
//            for (MATCHINGTARGETDtoType mATCHINGTARGETDtoType : list) {
//                PreparedStatement preparedStatement = conn.prepareStatement(hql);
//                preparedStatement.setString(1, mATCHINGTARGETDtoType.getTARGETCODE());
//                ResultSet emps = preparedStatement.executeQuery();
//                if (emps.next()) {
//                    rs += ";" + emps.getString("VIETNAMESE_NAME");
//                }
//            }
//            rs = rs.trim().substring(1);
//            return rs;
//        } catch (ParserConfigurationException ex) {
//            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SAXException ex) {
//            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return "";
//    }
//    public String getIMPACTTARGET_NAME(List<IMPACTTARGETDtoType> list) {
//        String rs = "Chỉ tiêu mức thôi nhiễm:\n";
//        for (IMPACTTARGETDtoType iMPACTTARGETDtoType : list) {
//            rs += "\t" + iMPACTTARGETDtoType.getTARGETNAME() + " Mức công bố: " + iMPACTTARGETDtoType.getPUBLISHLEVEL() + iMPACTTARGETDtoType.getUNITCODE();
//        }
//        return rs;
//    }
//
    public String ObjectToXml(Object obj) {

        String result = "";
        java.io.StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(obj, sw);
            result = sw.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result.trim();
    }

//    public String sendARP(Long fileId) {
//        try {
//            // khai bao
//            FilesDAOHE fdhe = new FilesDAOHE();
//            Files files = fdhe.findById(fileId);
//            Envelope evl = makeEnvelopeSend("111", "222",
//                    files.getFileCode(), files.getFileCode(), files.getFileId().toString());
//            Body bd = new Body();
//            Content ct = new Content();
//
//            AnnouncementReceiptPaper cosPermit = new AnnouncementReceiptPaper();
//            AnnouncementReceiptPaperDAOHE arpdaohe = new AnnouncementReceiptPaperDAOHE();
//            cosPermit = arpdaohe.findById(files.getAnnouncementReceiptPaperId());
//
//            // set trong body
//            AcceptRequestProductNotification acceptRequestProductNotification = new AcceptRequestProductNotification();
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            acceptRequestProductNotification.setNSWFileCode(files.getFileCode());
//            if (cosPermit != null) {
//                Date day = cosPermit.getEffectiveDate();
//                String receiveDate = df.format(day);
//                acceptRequestProductNotification.setProductNotificationNo(cosPermit.getReceiptNo());
//                acceptRequestProductNotification.setProductNotificationDate(receiveDate);
//            }
//
////            ct.setAcceptRequestProductNotification(acceptRequestProductNotification);
//            Attachment att = new Attachment();
//            VoAttachsDAOHE attdhe = new VoAttachsDAOHE();
//            VoAttachs lstAttach = attdhe.getLstVoAttachByFilesId(fileId, "PDHS_PUBLIC");
//            String filePath = lstAttach.getAttachPath();
//            ResourceBundle rb = ResourceBundle.getBundle("config");
//            String signPdf = rb.getString("directory");
//            String filePathFinal = signPdf + filePath;
//            File fileSign = new File(filePathFinal);
//            att.setAttachmentName(lstAttach.getAttachName());
//            att.setBase64FileContent(Base64.encode(read(fileSign)));
//            List<Attachment> listatt = new ArrayList<Attachment>();
//            listatt.add(att);
////            ct.setAttachment(att);
//
//            bd.setContent(ct);
//            evl.setBody(bd);
//            return sendMs(evl);
//        } catch (Exception ex) {
//            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
//
//            return "";
//        }
//    }
    public Envelope makeEnvelopeSend(String type, String function, String ref, String pref, String msId) {
        Envelope ev = new Envelope();
        Header hd = new Header();

        hd.setReference(new Reference("1.0", msId));
        hd.setFrom(new From("Cục an toàn thực phẩm - Bộ Y tế", "ATTP"));
        hd.setTo(new To("Hệ thống NSW", "NSW"));
        Date d = new Date();
        hd.setSubject(new Subject(type, function, ref, pref, formatterYear.format(d), formatterDateTime.format(d)));
        ev.setHeader(hd);
        return ev;
    }

//    public byte[] read(File file) {
//        // if (file.length() > MAX_FILE_SIZE) {
//        // throw new FileTooBigException(file);
//        // }
//        ByteArrayOutputStream ous = null;
//        InputStream ios = null;
//        try {
//            byte[] buffer = new byte[4096];
//            ous = new ByteArrayOutputStream();
//            ios = new FileInputStream(file);
//            int read = 0;
//            while ((read = ios.read(buffer)) != -1) {
//                ous.write(buffer, 0, read);
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (ous != null) {
//                    ous.close();
//                }
//            } catch (IOException e) {
////                LogUtils.addLog(e);
//            }
//            if (ios != null) {
//                try {
//                    ios.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//        return ous.toByteArray();
//    }
    private void writeXmlToFile(Envelope envelop) {
        try {
            String evlrs = ObjectToXml(envelop);
            System.out.println(evlrs);
            if ("true".equals(ResourceBundleUtil.getString("export_service_message_to_file", "config"))) {
                String type = envelop.getHeader().getSubject().getType();
                String function = envelop.getHeader().getSubject().getFunction();
                String reference = envelop.getHeader().getSubject().getReference();
                String fileName = type + "_" + function + "_" + reference;
                writeXmlToFile(evlrs, "sendMs", fileName);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeXmlToFile(String evlrs, String sendOrReceive, String typeFunctionReference) {
        try {
            XmlMessage xmlMessage = new XmlMessage();
            xmlMessage.setCreateDate(new Date());
            xmlMessage.setMessage(sendOrReceive + evlrs);
            XmlMessageDAOHE xmlDAOHE = new XmlMessageDAOHE();
            xmlDAOHE.create(xmlMessage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Envelope xmlToEnvelope(String xml) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Envelope.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            return (Envelope) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            log.error(e);
            return null;
        }
    }

//    public String sendMs(Envelope envelop) {
//        try {
//            if ("false".equals(ResourceBundleUtil.getString("send_service", "config"))) {
//                return "";
//            }
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return ObjectToXml(envelop);
//    }
    //Nhận ms từ NSW
    public String receiveMs(String evl) {
        Body bd = new Body();
        Content ct = new Content();
        List<com.viettel.ws.BO.Error> errList = new ArrayList<>();
        com.viettel.ws.BO.Error err = null;
        String type = null;
        String function = null;
        String ref = null;
        String pref = null;
        String msId = null;
        Files f = null;
        Boolean isMultiPayment = false;
        Boolean noUpdateProcess = false;
        FilesDAOHE fDAOHE = new FilesDAOHE();
//        ProcessDAOHE pdhe = new ProcessDAOHE();
        try {
            Envelope envelop = xmlToEnvelope(evl);
            if (envelop != null) {
                type = envelop.getHeader().getSubject().getType();
                ref = envelop.getHeader().getSubject().getReference();
                pref = envelop.getHeader().getSubject().getPreReference();
                msId = envelop.getHeader().getReference().getMessageId();
                function = envelop.getHeader().getSubject().getFunction();

                //Luu log DB
                if ("true".equals(ResourceBundleUtil.getString("export_service_message_to_file", "config"))) {
                    writeXmlToFile(evl, "receiveMS", envelop.getHeader().getSubject().getType() + "_" + envelop.getHeader().getSubject().getFunction()
                            + "_" + envelop.getHeader().getSubject().getReference());
                }
                String docType = Constants.CATEGORY_TYPE.CBSP_SERVICE;
                f = fDAOHE.findById(Long.parseLong(ref));
                if (err != null) {
                    errList.add(err);
                    function = Constants.NSW_FUNCTION(100L);
                } else {
                    err = new com.viettel.ws.BO.Error();
                    switch (function) {
                        default:
                            switch (function) {
                                case "310":
                                    errList = reiceiveMs_310(envelop);
                                    docType = Constants.CATEGORY_TYPE.CBSP_OBJECT;
                                    noUpdateProcess = true;
                                    break;
                                case "320":
                                    errList = reiceiveMs_320(envelop);
                                    docType = Constants.CATEGORY_TYPE.CBSP_OBJECT;
                                    noUpdateProcess = true;
                                    break;

                            }
                            if (errList.size() > 0) {
                                function = Constants.NSW_FUNCTION(100L);
                            } else if (noUpdateProcess) {
                                function = Constants.NSW_FUNCTION(199L);
                                ct.setReceiveDate(formatterDateTime.format(new Date()));

                            }
                            break;
                    }
                }
            } else {
                err = new com.viettel.ws.BO.Error();
                err.setErrorCode("L000-0000-0000");
                err.setErrorName("Lỗi convert thông điệp ra object");
                err.setSolution("Kiểm tra lại chỉ tiêu thông tin");
                errList.add(err);
                Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, err);
                function = Constants.NSW_FUNCTION(100L);
            }
        } catch (Exception ex) {
            err = new com.viettel.ws.BO.Error();
            err.setErrorCode("L000-0000-0000");
            err.setErrorName("Lỗi nhận dữ liệu");
            err.setSolution("Kiểm tra lại kết nối giữa 2 hệ thống");
            errList.add(err);
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            // ct.setErrorList(errList);
            function = Constants.NSW_FUNCTION(100L);
        }
        Envelope ev = makeEnvelopeSend(type, function, ref, pref, msId);
        bd.setContent(ct);
        ev.setBody(bd);
        if (Constants.NSW_TYPE(100L).equals(function)) {
            fDAOHE.rollBack();
        }
        return ObjectToXml(ev);
    }

    public String envelopeToXml(Envelope obj) {
        String result = "";
        java.io.StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(obj, sw);
            result = sw.toString();
            System.out.println(result);
        } catch (JAXBException e) {
            log.error(e);
        }
        return result;
    }

    //330-Thông báo sửa đổi bổ sung hồ sơ
    public String sendMs_330(Long fileId, String strContent) {
        ERROR err;
        RESULT lstError = new RESULT();

        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        Content content = new Content();
        CBREQUEST_CHANGE_330 cBREQUEST_CHANGE_330 = new CBREQUEST_CHANGE_330();
        cBREQUEST_CHANGE_330.setName("Hố sơ thông báo sửa đổi bổ sung hồ sơ");
        cBREQUEST_CHANGE_330.setNswFileCode("16.05.1.202131");
        cBREQUEST_CHANGE_330.setReason(" yêu cầu sửa đổi nội dung");

//        if (!ServiceSessionManager.validToken(tokenString)) {
//            err = new ERROR();
//            err.setERROR_CODE("Validate");
//            lstError.getLstERROR().add(err);
//        }
        content.setCbRequestChange330(cBREQUEST_CHANGE_330);

        body.setContent(content);
        envelope.setBody(body);
        envelope.setHeader(header);

        return envelopeToXml(envelope);
    }
///3	310	Yêu cầu SDBS hồ sơ--NSW--> BYT

    public List<com.viettel.ws.BO.Error> reiceiveMs_310(Envelope envelope) {
        List<com.viettel.ws.BO.Error> errList = new ArrayList<>();
        com.viettel.ws.BO.Error err = new com.viettel.ws.BO.Error();
        try {
            DNREQUEST_CHANGE_310 DnReQuestChange310 = envelope.getBody().getContent().getDnRequestChange310();
            String nswFileCode = DnReQuestChange310.getNswFileCode();
            FilesDAOHE fileDAOHE = new FilesDAOHE();
            Files filesBo = fileDAOHE.findByNswCode(nswFileCode);
        } catch (Exception ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            err.setErrorCode("U000-0000-0001");
            err.setErrorName("Lỗi gửi yêu cầu bổ sung");
            err.setSolution("Kiểm tra chỉ tiêu thông tin và hàm validate");
            errList.add(err);
        }
        return errList;
    }

    public List<com.viettel.ws.BO.Error> reiceiveMs_320(Envelope envelope) {
        List<com.viettel.ws.BO.Error> errList = new ArrayList<>();
        com.viettel.ws.BO.Error err = new com.viettel.ws.BO.Error();
        try {
            DNREQUEST_DELETE_320 DnReQuestDelete320 = envelope.getBody().getContent().getDnRequestDelete320();
            String nswFileCode = DnReQuestDelete320.getNswFileCode();
            FilesDAOHE fileDAOHE = new FilesDAOHE();
            Files filesBo = fileDAOHE.findByNswCode(nswFileCode);
        } catch (Exception ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            err.setErrorCode("U000-0000-0001");
            err.setErrorName("Lỗi gửi yêu cầu bổ sung");
            err.setSolution("Kiểm tra chỉ tiêu thông tin và hàm validate");
            errList.add(err);
        }
        return errList;
    }

    //370	Gửi kết quả kiểm tra hệ thống
    public String sendMs_370(@WebParam(name = "tokenString") String tokenString, @WebParam(name = "PERMIT_370") String strPERMIT_370) {
        ERROR err;
        RESULT lstError = new RESULT();
        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        Content content = new Content();

        PERMIT_370 pERMIT_370 = new PERMIT_370();

        if (!ServiceSessionManager.validToken(tokenString)) {
            err = new ERROR();
            err.setERROR_CODE("Validate");
            lstError.getLstERROR().add(err);
        }
        content.setPermit370(pERMIT_370);

        body.setContent(content);
        envelope.setBody(body);
        envelope.setHeader(header);

        return envelopeToXml(envelope);
    }
    //340-Thông báo lệ phí
    /*
    public String sendMs_340(@WebParam(name = "tokenString") String tokenString, @WebParam(name = "annNo") String annNo) {
        ERROR err;
        RESULT lstError = new RESULT();
        Envelope envelope = new Envelope();
        Header header = new Header();
        Body body = new Body();
        Content content = new Content();
        
        FEE_NOTICE_340 fEE_NOTICE_340 = new FEE_NOTICE_340();
        
        if (!ServiceSessionManager.validToken(tokenString)) {
            err = new ERROR();
            err.setERROR_CODE("Validate");
            lstError.getLstERROR().add(err);
        }                
        content.setFeeNotice340(fEE_NOTICE_340);
        
        body.setContent(content);        
        envelope.setBody(body);
        envelope.setHeader(header);
        
        return envelopeToXml(envelope);
    }
     */
//    
//    public List<ErrorWs> reiceiveMs_350(Envelope envelope) throws Exception {
//        List<ErrorWs> errList = new ArrayList<>();
//        ErrorWs err = new ErrorWs();
//        try {
//            DNREQUEST_CHANGE_310 dNREQUEST_CHANGE_310 = envelope.getBody().getContent().getDnRequestChange310();
//        } catch (Exception ex) {
//            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
//            err.setErrorCode("U000-0000-0001");
//            err.setErrorName("Lỗi số lượng hàng hóa thông quan");
//            err.setSolution("Kiểm tra chỉ tiêu thông tin và hàm validate");
//            errList.add(err);
//        }
//        return errList;
//    }
    //310-Yêu cầu SDBS hồ sơ
    /*
    public List<ErrorWs> reiceiveMs_310(Envelope envelope) throws Exception {
        List<ErrorWs> errList = new ArrayList<>();
        ErrorWs err = new ErrorWs();
        try {
            DNREQUEST_CHANGE_310 dNREQUEST_CHANGE_310 = envelope.getBody().getContent().getDnRequestChange310();
            if (dNREQUEST_CHANGE_310 != null) {
                XmlWs bo = new XmlWs();
                XmlWsDAOHE xmldaohe = new XmlWsDAOHE();
                bo.setReason(dNREQUEST_CHANGE_310.getReason());
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                bo.setCreateDate((Date) formatter.parse(dNREQUEST_CHANGE_310.getCreateDate()));
                bo.setNswFileCode(dNREQUEST_CHANGE_310.getNswFileCode());
                xmldaohe.create(bo);
            }
        } catch (Exception ex) {
            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
            err.setErrorCode("U000-0000-0001");
            err.setErrorName("Lỗi lưu dữ liệu không thanh công");
            err.setSolution("Kiểm tra chỉ tiêu thông tin và hàm validate");
            errList.add(err);
        }
        return errList;
    }
     */
    //320-Yêu cầu hủy hồ sơ
    /*
    public List<ErrorWs> reiceiveMs_320(Envelope envelope) throws Exception {
        List<ErrorWs> errList = new ArrayList<>();
        ErrorWs err = new ErrorWs();
        try {
            DNREQUEST_DELETE_320 dnRequestDelete = envelope.getBody().getContent().getDnRequestDelete320();
            if (dnRequestDelete != null) {
                XmlWs bo = new XmlWs();
                XmlWsDAOHE xmldaohe = new XmlWsDAOHE();
                bo.setReason(dnRequestDelete.getReason());
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                bo.setCreateDate((Date) formatter.parse(dnRequestDelete.getCreateDate()));
                bo.setNswFileCode(dnRequestDelete.getNswFileCode());
                xmldaohe.create(bo);
            }
        } catch (Exception ex) {
            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
            err.setErrorCode("U000-0000-0001");
            err.setErrorName("Lỗi số lượng hàng hóa thông quan");
            err.setSolution("Kiểm tra chỉ tiêu thông tin và hàm validate");
            errList.add(err);
        }
        return errList;
    }
    
     */
//    
//    public List<ErrorWs> reiceiveMs_380(Envelope envelope) throws Exception {
//        List<ErrorWs> errList = new ArrayList<>();
//        ErrorWs err = new ErrorWs();
//        try {
//            DNREQUEST_CHANGE_310 dNREQUEST_CHANGE_310 = envelope.getBody().getContent().getDnRequestChange310();
//        } catch (Exception ex) {
//            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
//            err.setErrorCode("U000-0000-0001");
//            err.setErrorName("Lỗi số lượng hàng hóa thông quan");
//            err.setSolution("Kiểm tra chỉ tiêu thông tin và hàm validate");
//            errList.add(err);
//        }
//        return errList;
//    }
//    
//    public List<ErrorWs> reiceiveMs_390(Envelope envelope) throws Exception {
//        List<ErrorWs> errList = new ArrayList<>();
//        ErrorWs err = new ErrorWs();
//        try {
//            DNREQUEST_CHANGE_310 dNREQUEST_CHANGE_310 = envelope.getBody().getContent().getDnRequestChange310();
//        } catch (Exception ex) {
//            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
//            err.setErrorCode("U000-0000-0001");
//            err.setErrorName("Lỗi số lượng hàng hóa thông quan");
//            err.setSolution("Kiểm tra chỉ tiêu thông tin và hàm validate");
//            errList.add(err);
//        }
//        return errList;
//    }
//    
    //    public List<ErrorWs> reiceiveMs_360(Envelope envelope) {
//        List<ErrorWs> errList = new ArrayList<>();
//        ErrorWs err = new ErrorWs();
//        try {
////            DNREQUEST_CHANGE_310 dNREQUEST_CHANGE_310 = envelope.getBody().getContent().getDnRequestChange310();
//        } catch (Exception ex) {
//            Logger.getLogger(FilesWS.class.getName()).log(Level.SEVERE, null, ex);
//            err.setErrorCode("U000-0000-0001");
//            err.setErrorName("Lỗi số lượng hàng hóa thông quan");
//            err.setSolution("Kiểm tra chỉ tiêu thông tin và hàm validate");
//            errList.add(err);
//        }
//        return errList;
//    }
}
