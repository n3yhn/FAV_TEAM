/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.validateData;

import com.viettel.ws.ANNOUCERECEIVE.*;
import com.viettel.ws.BO.*;
import com.viettel.ws.ErrorType;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Administrator
 */
public class Helper {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Helper.class);
    public Helper() {

    }

    private File xmlErrType() {
        URL path = Helper.class.getProtectionDomain().getCodeSource().getLocation();
        String stringPath = path.toString();
        stringPath = stringPath.replaceAll("%20", " ");
        stringPath = stringPath.substring(6);
        stringPath = stringPath.replaceAll("/com/viettel/ws/validateData/Helper.class", "/Validate.xml");
        File rs = new File(stringPath);
        return rs;
    }

    public Boolean Validate_Type(String value, String type) {
        switch (type) {
            case "Int":
                Pattern pi = Pattern.compile("^\\d+$");
                Matcher mi = pi.matcher(value);
                return mi.matches();
            case "Tax":
                return value.length() == 10 || value.length() == 13;
            case "Phone":
//                String PHONE_PATTERN = "^(\\+84)|(84)|0+[1-9]{1}+[0-9]{8,9}$";
//                Pattern pp = Pattern.compile(PHONE_PATTERN);
//                Matcher mp = pp.matcher(value);
//                return mp.matches();
            case "DateTime":
                String v = value.trim().substring(0, 10);

                String DATE_PATTERN = "^\\d{4}-((0[1-9])|(1[012]))-((0[1-9]|[12]\\d)|3[01])$";
                //"^((((0[1-9]|[12]\\d|3[01])/(0[13578]|1[02])/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)/(0[13456789]|1[012])/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])/02/((19|[2-9]\\d)\\d{2}))|(29/02/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$)|((((0[1-9]|[12]\\d|3[01])-(0[13578]|1[02])-((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)-(0[13456789]|1[012])-((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])-02-((19|[2-9]\\d)\\d{2}))|(29-02-((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$)";
                Pattern pd = Pattern.compile(DATE_PATTERN);
                Matcher md = pd.matcher(v);
                return md.matches();
            case "Email":
                String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                Pattern pe = Pattern.compile(EMAIL_PATTERN);
                Matcher me = pe.matcher(value);
                return me.matches();
            case "FileName":
                int k = value.lastIndexOf(".");
                int m = 0;
                if (k > 0) {
                    m = value.substring(k, value.length() - 1).length();
                }
                return m == 3 || m == 4;
            case "Number":
                String NUMBER_PATTERN = "^([0-9]{1,6}[.]{1}[0-9]{3})|([0-9]{1,9})$";
                Pattern pn = Pattern.compile(NUMBER_PATTERN);
                Matcher mn = pn.matcher(value);
            case "Bool":
                Boolean rs = "1".equals(value) || "0".equals(value);
                return rs;
            default:
                return true;
        }
    }

    public static boolean canBePhoneNumber(String phonenumber) {
        if (phonenumber == null || "".equals(phonenumber.trim())) {
            return false;
        }
        Pattern p = Pattern.compile("^(09|01|849|841|\\+849|\\+841)[0-9]+$");
        Matcher m = p.matcher(phonenumber);

        boolean matchFound = m.matches();
        return matchFound;
    }

    public static boolean validateMobileNumber(String mobileNumber) {
        mobileNumber = mobileNumber.trim();
        final String prefix849 = "849";
        final String prefix849plus = "+849";
        final String prefix841 = "841";
        final String prefix841plus = "+841";
        final String prefix09 = "09";
        final String prefix01 = "01";
        boolean rs = false;
        if (mobileNumber == null || "".equals(mobileNumber.trim())
                && canBePhoneNumber(mobileNumber)) {
            int length = mobileNumber.length();
            if ((length == 10 && mobileNumber.startsWith(prefix09))
                    || (length == 11 && (mobileNumber.startsWith(prefix01) || mobileNumber.startsWith(prefix849)))
                    || (length == 12 && (mobileNumber.startsWith(prefix841) || mobileNumber.startsWith(prefix849plus)))
                    || (length == 13 && mobileNumber.startsWith(prefix841plus))) {
                rs = true;
            }
        }
        return rs;
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
//HieuLd5

    private List<ERRORDto> getErrorList(String field, String value, String parentObject, File xmlErrorType, int loop) throws ParserConfigurationException, SAXException, IOException {
        try {
            List<ErrorType> errTypes = new ArrayList<>();
            List<ERRORDto> rs = new ArrayList<>();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(xmlErrorType);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(field);
            if (nList != null && nList.getLength() > 0) {
                String Loop = Integer.toString(loop);
                StringBuilder buf = new StringBuilder(Loop);
                while (buf.length() < 8) {
                    buf.insert(0, '0');
                }
                Loop = buf.toString();
                Helper hp = new Helper();
                for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);
                    if (nNode.getParentNode().getNodeName().equals(parentObject)) {
                        Element e = (Element) nNode;
                        String[] errTypeIds = e.getAttribute("Id").split(";");
                        String name = e.getAttribute("Name");
                        String type = e.getAttribute("Type");
                        int length = "".equals(e.getAttribute("Length")) ? 0 : Integer.parseInt(e.getAttribute("Length"));
                        String table = e.getAttribute("Table");
                        if (errTypeIds != null && errTypeIds.length > 0) {
                            for (String id : errTypeIds) {
                                errTypes.add(new ErrorType(Integer.parseInt(id), name, length, type, table));
                            }
                        }
                    }
                    break;
                }
                if (errTypes != null && errTypes.size() > 0) {
                    Boolean err = false;
                    for (ErrorType errType : errTypes) {
                        if (!err) {
                            switch (errType.Id) {
                                case 2:
                                    if (value == null || value.trim().length() < 1) {
                                        rs.add(new ERRORDto("S001-" + field + "-" + Loop, errType.Name + " Thiếu thông tin (Null)"));
                                        err = true;
                                    }
                                    break;
                                case 3:
                                    if (value != null && value.trim().length() > errType.Length) {
                                        rs.add(new ERRORDto("S002-" + field + "-" + Loop, errType.Name + " Độ dài vượt quá " + " - " + errType.Length));
                                        err = true;
                                    }
                                    break;
                                case 4:
                                    if (!hp.Validate_Type(value, errType.Type)) {
                                        rs.add(new ERRORDto("S002-" + field + "-" + Loop, errType.Name + " Sai kiểu dữ liệu: " + errType.Type));
                                        err = true;
                                    }
                                    break;
                                case 5:

                                    if (errType.Table != null && !"".equals(errType.Table.trim()) && !value.isEmpty()) {
                                        String[] T = errType.Table.split(";");
                                        if (T.length > 1) {
//                                            String hql = "Select " + T[1] + " from " + T[0] + " where " + T[1] + " = '"+value+"'";
                                            String hql = "Select " + T[1] + " from " + T[0] + " where " + T[1] + " = ?";
                                            if (T.length == 3) {
                                                hql += " and " + T[2];
                                            }
                                            Element econn = (Element) doc.getElementsByTagName("CONNECTION").item(0);
                                            Class.forName("oracle.jdbc.driver.OracleDriver");
                                            Connection conn = DriverManager.getConnection(econn.getAttribute("Url"), econn.getAttribute("UserName"), econn.getAttribute("Password"));
                                            PreparedStatement preparedStatement = conn.prepareStatement(hql);
                                            preparedStatement.setString(1, value);
                                            ResultSet emps = preparedStatement.executeQuery();
                                            if (!emps.next()) {
                                                Boolean k = false;
                                                switch (field) {
//                                                    case "deptcode":
//                                                        k = PROCESSDEPT_Insert(processdeptGetByCode(value), conn);
//                                                        break;
//                                                    case "filetypecode":
//                                                        k = FILETYPE_Insert(filetypeGetByCode(value), conn);
//                                                        break;
//                                                    case "statuscode":
//                                                        k = STATUS_Insert(statusGetByCode(new Integer(value)), conn);
//                                                        break;
//                                                    case "producttypecode":
//                                                        k = PRODUCTTYPE_Insert(producttypeGetByCode(value), conn);
//                                                        break;
//                                                    case "targetcode":
//                                                        k = TECHNICALSTANDARD_Insert(technicalstandardGetByCode(value), false, conn);
//                                                        break;
//                                                    case "unitcode":
//                                                        k = UNIT_Insert(unitGetByCode(value), conn);
//                                                        break;
//                                                    case "technicalregulation":
//                                                        k = TECHNICALSTANDARD_Insert(technicalstandardGetByCode(value), true, conn);
//                                                        break;
                                                    default:
                                                        break;
                                                }
                                                if (!k) {
                                                    rs.add(new ERRORDto("E001-" + field + "-" + Loop, errType.Name + " Không có trong CSDL, không thể đồng bộ từ Gateway danh mục: " + T[0] + " - Trường " + T[1] + " - Giá trị: " + value));
                                                }
                                            }
                                        }
                                    }
                                default:
                                    break;
                            }
                        }

                    }
                }
            }
            return rs;
        } catch (Exception ex) {
            return null;
        }
    }
//Hieuld5

    public List<ERRORDto> validateObj(Object input, int loop, File xmlErrType) throws ParserConfigurationException, SAXException, IOException {//hàm thực hiện validate obj đối với obj đầu vào obj.
        List<ERRORDto> eRRORLIST = new ArrayList<>();
        try {
            String parentObject = input.getClass().getName();
            parentObject = parentObject.substring(parentObject.lastIndexOf(".")).replace(".", "");
            for (Field fieldInput : input.getClass().getDeclaredFields()) {
                String field = fieldInput.getName().toLowerCase();
                String value = fieldInput.get(input) == null ? null : fieldInput.get(input).toString();
                List<ERRORDto> err = getErrorList(field, value, parentObject, xmlErrType, loop);
                if (err != null && err.size() > 0) {
                    eRRORLIST.addAll(err);
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
        }
        //list all require here
        return eRRORLIST;

    }

    public List<ERRORDto> validateANNOUNCESENDDto(ANNOUNCESENDDtoType file) throws ParserConfigurationException, SAXException, IOException {
        File xmlErrType = xmlErrType();
        List<ERRORDto> errList = new ArrayList<>();
        if (file != null) {
            errList.addAll(validateObj(file, 0, xmlErrType));
            MATCHINGTARGETLISTType matching = file.getMATCHINGTARGETLIST();
            if (matching != null) {
                List<MATCHINGTARGETDtoType> matchingtargetDto = matching.getMATCHINGTARGETDto();
                if (matchingtargetDto != null && matchingtargetDto.size() > 0) {
                    for (int m = 0; m < matchingtargetDto.size(); m++) {
                        errList.addAll(validateObj(matchingtargetDto.get(m), m, xmlErrType));
                    }
                }
            }
            MAINLYTARGETSLISTType mainly = file.getMAINLYTARGETSLIST();
            if (mainly != null) {
                List<MAINLYTARGETSDtoType> mainlytargetsDto = mainly.getMAINLYTARGETSDto();
                if (mainlytargetsDto != null && mainlytargetsDto.size() > 0) {
                    for (int m = 0; m < mainlytargetsDto.size(); m++) {
                        errList.addAll(validateObj(mainlytargetsDto.get(m), m, xmlErrType));
                    }
                }
            }
            BIOTARGETLISTType bio = file.getBIOTARGETLIST();
            if (bio != null) {
                List<BIOTARGETDtoType> biotargetDto = bio.getBIOTARGETDto();
                if (biotargetDto != null && biotargetDto.size() > 0) {
                    for (int m = 0; m < biotargetDto.size(); m++) {
                        errList.addAll(validateObj(biotargetDto.get(m), m, xmlErrType));
                    }
                }
            }
            HEAVYMETALLISTType heavy = file.getHEAVYMETALLIST();
            if (heavy != null) {
                List<HEAVYTARGETDtoType> heavytargetDto = heavy.getHEAVYTARGETDto();
                if (heavytargetDto != null && heavytargetDto.size() > 0) {
                    for (int m = 0; m < heavytargetDto.size(); m++) {
                        errList.addAll(validateObj(heavytargetDto.get(m), m, xmlErrType));
                    }
                }
            }
            UNEXCHEMICALLISTType chemlist = file.getUNEXCHEMICALLIST();
            if (chemlist != null) {
                List<CHEMTARGETDtoType> chemtargetDto = chemlist.getCHEMTARGETDto();
                if (chemtargetDto != null && chemtargetDto.size() > 0) {
                    for (int m = 0; m < chemtargetDto.size(); m++) {
                        errList.addAll(validateObj(chemtargetDto.get(m), m, xmlErrType));
                    }
                }
            }
            IMPACTTARGETSLISTType impact = file.getIMPACTTARGETSLIST();
            if (impact != null) {
                List<IMPACTTARGETDtoType> impacttargetDto = impact.getIMPACTTARGETDto();
                if (impacttargetDto != null && impacttargetDto.size() > 0) {
                    for (int m = 0; m < impacttargetDto.size(); m++) {
                        errList.addAll(validateObj(impacttargetDto.get(m), m, xmlErrType));
                    }
                }
            }
            QUALITYCONTROLPLANType quality = file.getQUALITYCONTROLPLAN();
            if (quality != null) {
                List<CONTROLPLANDtoType> controlplanDto = quality.getCONTROLPLANDto();
                if (controlplanDto != null && controlplanDto.size() > 0) {
                    for (int m = 0; m < controlplanDto.size(); m++) {
                        errList.addAll(validateObj(controlplanDto.get(m), m, xmlErrType));
                    }
                }
            }
            ATTACHMENTSType attach = file.getATTACHMENTS();
            if (attach != null) {
                List<ATTACHMENTSDtoType> attachmentsDto = (List<ATTACHMENTSDtoType>) attach.getATTACHMENTSDto();
                if (attachmentsDto != null && attachmentsDto.size() > 0) {
                    for (int m = 0; m < attachmentsDto.size(); m++) {
                        errList.addAll(validateObj(attachmentsDto.get(m), m, xmlErrType));
                    }
                }
            } else {
                errList.add(new ERRORDto("A002-ATTACHMENTST-000", "Không có tệp đính kèm"));
            }
        }
        return errList;
    }

//    private static UNITDto unitGetByCode(java.lang.String code) {
//        com.viettel.ws.client.NSWGatewaySyncService service = new com.viettel.ws.client.NSWGatewaySyncService();
//        com.viettel.ws.client.INSWGatewaySyncService port = service.getBasicHttpBindingINSWGatewaySyncService();
//        return port.unitGetByCode(code);
//    }

//    private static STATUSDto statusGetByCode(java.lang.Integer code) {
//        com.viettel.ws.client.NSWGatewaySyncService service = new com.viettel.ws.client.NSWGatewaySyncService();
//        com.viettel.ws.client.INSWGatewaySyncService port = service.getBasicHttpBindingINSWGatewaySyncService();
//        return port.statusGetByCode(code);
//    }

//    private static PRODUCTTYPEDto producttypeGetByCode(java.lang.String code) {
//        com.viettel.ws.client.NSWGatewaySyncService service = new com.viettel.ws.client.NSWGatewaySyncService();
//        com.viettel.ws.client.INSWGatewaySyncService port = service.getBasicHttpBindingINSWGatewaySyncService();
//        return port.producttypeGetByCode(code);
//    }

//    private static PROCESSDEPTDto processdeptGetByCode(java.lang.String code) {
//        com.viettel.ws.client.NSWGatewaySyncService service = new com.viettel.ws.client.NSWGatewaySyncService();
//        com.viettel.ws.client.INSWGatewaySyncService port = service.getBasicHttpBindingINSWGatewaySyncService();
//        return port.processdeptGetByCode(code);
//    }

//    private static TECHNICALSTANDARDDto technicalstandardGetByCode(java.lang.String code) {
//        com.viettel.ws.client.NSWGatewaySyncService service = new com.viettel.ws.client.NSWGatewaySyncService();
//        com.viettel.ws.client.INSWGatewaySyncService port = service.getBasicHttpBindingINSWGatewaySyncService();
//        return port.technicalstandardGetByCode(code);
//    }

//    private static FILETYPEDto filetypeGetByCode(java.lang.String code) {
//        com.viettel.ws.client.NSWGatewaySyncService service = new com.viettel.ws.client.NSWGatewaySyncService();
//        com.viettel.ws.client.INSWGatewaySyncService port = service.getBasicHttpBindingINSWGatewaySyncService();
//        return port.filetypeGetByCode(code);
//    }

//    private Boolean UNIT_Insert(UNITDto obj, Connection conn) {
//        Boolean rs = false;
//        try {
//            if (obj == null) {
//                return false;
//            }
//            String hql = "INSERT INTO CATEGORY ( CATEGORY_ID, TYPE, NAME, CODE, IS_ACTIVE, DESCRIPTION) VALUES ( CATEGORY_SEQ.nextval, ?, ?, ?, 1, ?)";
//            PreparedStatement preparedStatement = conn.prepareStatement(hql);
//            preparedStatement.setString(1, "DVI");
//            preparedStatement.setString(2, obj.getNAME() == null ? null : obj.getNAME().getValue());
//            preparedStatement.setString(3, obj.getUNITCODE() == null ? null : obj.getUNITCODE().getValue());
//            preparedStatement.setString(4, obj.getDESCRIPTION() == null ? null : obj.getDESCRIPTION().getValue());
//            preparedStatement.executeUpdate();
//            rs = true;
//        } catch (Exception ex) {
//            rs = false;
//        }
//        return rs;
//    }

//    private Boolean STATUS_Insert(STATUSDto obj, Connection conn) {
//        Boolean rs = false;
//        try {
//            if (obj == null) {
//                return false;
//            }
//            String hql = "INSERT INTO CATEGORY ( CATEGORY_ID, TYPE, NAME, CODE, IS_ACTIVE, DESCRIPTION) VALUES ( CATEGORY_SEQ.nextval, ?, ?, ?, 1, ?)";
//            PreparedStatement preparedStatement = conn.prepareStatement(hql);
//            preparedStatement.setString(1, "STATUS");
//            preparedStatement.setString(2, obj.getSTATUSNAME() == null ? null : obj.getSTATUSNAME().getValue());
//            preparedStatement.setString(3, obj.getSTATUSCODE().getValue() == null ? null : obj.getSTATUSCODE().getValue().toString());
//            preparedStatement.setString(4, obj.getDESCRIPTIONS() == null ? null : obj.getDESCRIPTIONS().getValue());
//            preparedStatement.executeUpdate();
//            rs = true;
//        } catch (Exception ex) {
//            rs = false;
//        }
//        return rs;
//    }

//    private Boolean PRODUCTTYPE_Insert(PRODUCTTYPEDto obj, Connection conn) {
//        Boolean rs = false;
//        try {
//            if (obj == null) {
//                return false;
//            }
//            String hql = "INSERT INTO CATEGORY ( CATEGORY_ID, TYPE, NAME, CODE ,IS_ACTIVE, DESCRIPTION) VALUES ( CATEGORY_SEQ.nextval, ?, ?, ?, 1, ?)";
//            PreparedStatement preparedStatement = conn.prepareStatement(hql);
//            preparedStatement.setString(1, "SP");
//            preparedStatement.setString(2, obj.getPRODUCTNAME() == null ? null : obj.getPRODUCTNAME().getValue());
//            preparedStatement.setString(3, obj.getPRODUCTTYPECODE() == null ? null : obj.getPRODUCTTYPECODE().getValue());
//            preparedStatement.setString(4, obj.getDESCRIPTION() == null ? null : obj.getDESCRIPTION().getValue());
//            preparedStatement.executeUpdate();
//            rs = true;
//        } catch (Exception ex) {
//            rs = false;
//        }
//        return rs;
//    }

//    private Boolean PROCESSDEPT_Insert(PROCESSDEPTDto obj, Connection conn) {
//        Boolean rs = false;
//        String hql = "";
//        try {
//            if (obj == null) {
//                return false;
//            }
//            if (obj.getDEPTTYPEID() == null) {
//                return false;
//            } else {
//                hql = "Select DEPT_TYPE_ID from DEPT_TYPE where DEPT_TYPE_ID = ?";
//                PreparedStatement preparedStatement1 = conn.prepareStatement(hql);
//                preparedStatement1.setInt(1, obj.getDEPTTYPEID());
//                ResultSet emps = preparedStatement1.executeQuery();
//                if (!emps.next()) {
//                    return false;
//                } else {
//                    hql = "INSERT INTO DEPARTMENT ( DEPT_ID, TELEPHONE, STATUS, DEPT_NAME, ADDRESS, DESCRIPTION, DEPT_CODE, EMAIL, CONTACT_NAME, CONTACT_TITLE, FAX, TEL, DEPT_TYPE_ID, DEPT_LEVEL, IP, URL, TAX_CODE, INDEPENDENT, PARENT_CODE, PARENT_NAME, PROVINCE_NAME)"
//                            + " VALUES ( DEPARTMENT_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//                    PreparedStatement preparedStatement = conn.prepareStatement(hql);
//                    preparedStatement.setString(1, obj.getTELEPHONE() == null ? null : obj.getTELEPHONE().getValue());
//                    preparedStatement.setInt(2, 1);
//                    preparedStatement.setString(3, obj.getDEPTNAME() == null ? null : obj.getDEPTNAME().getValue());
//                    preparedStatement.setString(4, obj.getADDRESS() == null ? null : obj.getADDRESS().getValue());
//                    preparedStatement.setString(5, obj.getDESCRIPTION() == null ? null : obj.getDESCRIPTION().getValue());
//                    preparedStatement.setString(6, obj.getDEPTCODE() == null ? null : obj.getDEPTCODE().getValue());
//                    preparedStatement.setString(7, obj.getEMAIL() == null ? null : obj.getEMAIL().getValue());
//                    preparedStatement.setString(8, obj.getCONTACTNAME() == null ? null : obj.getCONTACTNAME().getValue());
//                    preparedStatement.setString(9, obj.getCONTACTTITLE() == null ? null : obj.getCONTACTTITLE().getValue());
//                    preparedStatement.setString(10, obj.getFAX() == null ? null : obj.getFAX().getValue());
//                    preparedStatement.setString(11, obj.getTEL() == null ? null : obj.getTEL().getValue());
//                    preparedStatement.setString(12, obj.getDEPTTYPEID() == null ? null : obj.getDEPTTYPEID().toString());
//                    preparedStatement.setString(13, obj.getDEPTLEVEL() == null ? null : obj.getDEPTLEVEL().getValue());
//                    preparedStatement.setString(14, obj.getIP() == null ? null : obj.getIP().getValue());
//                    preparedStatement.setString(15, obj.getURL() == null ? null : obj.getURL().getValue());
//                    preparedStatement.setString(16, obj.getTAXCODE() == null ? null : obj.getTAXCODE().getValue());
//                    preparedStatement.setString(17, obj.getINDEPENDENT().getValue() == null ? null : obj.getINDEPENDENT().getValue().toString());
//                    preparedStatement.setString(18, obj.getPARENTCODE() == null ? null : obj.getPARENTCODE().getValue());
//                    preparedStatement.setString(19, obj.getPARENTNAME() == null ? null : obj.getPARENTNAME().getValue());
//                    preparedStatement.setString(20, obj.getPROVINCENAME() == null ? null : obj.getPROVINCENAME().getValue());
//                    preparedStatement.executeUpdate();
//                    rs = true;
//                }
//            }
//        } catch (Exception ex) {
//            rs = false;
//        }
//        return rs;
//    }

//    private Boolean FILETYPE_Insert(FILETYPEDto obj, Connection conn) {
//
//        Boolean rs = false;
//        try {
//            if (obj == null) {
//                return false;
//            }
//            String hql = "INSERT INTO PROCEDURE ( PROCEDURE_ID, NAME, CODE, IS_ACTIVE) VALUES ( PROCEDURE_SEQ.nextval, ?, ?, 1)";
//            PreparedStatement preparedStatement = conn.prepareStatement(hql);
//            preparedStatement.setString(1, obj.getFILETYPENAME() == null ? null : obj.getFILETYPENAME().getValue());
//            preparedStatement.setString(2, obj.getFILETYPECODE() == null ? null : obj.getFILETYPECODE().getValue());
//            preparedStatement.executeUpdate();
//            rs = true;
//        } catch (Exception ex) {
//            rs = false;
//        }
//        return rs;
//    }

//    private Boolean TECHNICALSTANDARD_Insert(TECHNICALSTANDARDDto obj, Boolean vn, Connection conn) {
//        Boolean rs = false;
//        try {
//            if (obj == null) {
//                return false;
//            }
//            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
//            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
//            if (vn) {
//                String hql = "INSERT INTO VIETNAMESE_STANDARD ( VIETNAMESE_STANDARD_ID, STANDARD_CODE, ENGLISH_NAME, VIETNAMESE_NAME, SUMMARY, APPLICATION_OBJECT, PUBLISH_DATE, EFFECTIVE_DATE, EXPIRE_DATE, SCOPE, STANDARD_TYPE, IS_ACTIVE, PUBLISH_AGENCY_NAME)"
//                        + "  VALUES ( VIETNAMESE_STANDARD_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, ?)";
//                PreparedStatement preparedStatement = conn.prepareStatement(hql);
//                preparedStatement.setString(1, obj.getSTANDARDCODE() == null ? null : obj.getSTANDARDCODE().getValue());
//                preparedStatement.setString(2, obj.getENGLISHNAME() == null ? null : obj.getENGLISHNAME().getValue());
//                preparedStatement.setString(3, obj.getVIETNAMESENAME() == null ? null : obj.getVIETNAMESENAME().getValue());
//                preparedStatement.setString(4, obj.getSUMMARY() == null ? null : obj.getSUMMARY().getValue());
//                preparedStatement.setString(5, obj.getAPPLICATIONOBJECT() == null ? null : obj.getAPPLICATIONOBJECT().getValue());
//                preparedStatement.setString(6, obj.getPUBLISHDATE() == null|| obj.getPUBLISHDATE().getValue()==null? null : dateFormat.format(dateFormat2.parse(obj.getPUBLISHDATE().getValue().toString().substring(0, 10))));
//                preparedStatement.setString(7, obj.getEFFECTIVEDATE() == null|| obj.getEFFECTIVEDATE().getValue()==null ? null : dateFormat.format(dateFormat2.parse(obj.getEFFECTIVEDATE().getValue().toString().substring(0, 10))));
//                preparedStatement.setString(8, obj.getEXPIREDATE() ==null|| obj.getEXPIREDATE().getValue() == null ? null : dateFormat.format(dateFormat2.parse(obj.getEXPIREDATE().getValue().toString().substring(0, 10))));
//                preparedStatement.setString(9, obj.getSCOPE() == null ? null : obj.getSCOPE().toString());
//                preparedStatement.setString(10, obj.getSTANDARDTYPE().getValue() == null ? null : obj.getSTANDARDTYPE().toString());
//                preparedStatement.setString(11, obj.getPUBLISHAGENCYNAME() == null ? null : obj.getPUBLISHAGENCYNAME().getValue());
//                preparedStatement.executeUpdate();
//                rs = true;
//            } else {
//                String hql = "INSERT INTO TECHNICAL_STANDARD ( TECHNICAL_STANDARD_ID, STANDARD_CODE, ENGLISH_NAME, VIETNAMESE_NAME, SUMMARY, APPLICATION_OBJECT, PUBLISH_DATE, EFFECTIVE_DATE, EXPIRE_DATE, SCOPE, STANDARD_TYPE, IS_ACTIVE, PUBLISH_AGENCY_NAME, STATUS)"
//                        + "  VALUES ( TECHNICAL_STANDARD_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, ?, 1)";
//                PreparedStatement preparedStatement = conn.prepareStatement(hql);
//                preparedStatement.setString(1, obj.getSTANDARDCODE() == null ? null : obj.getSTANDARDCODE().getValue());
//                preparedStatement.setString(2, obj.getENGLISHNAME() == null ? null : obj.getENGLISHNAME().getValue());
//                preparedStatement.setString(3, obj.getVIETNAMESENAME() == null ? null : obj.getVIETNAMESENAME().getValue());
//                preparedStatement.setString(4, obj.getSUMMARY() == null ? null : obj.getSUMMARY().getValue());
//                preparedStatement.setString(5, obj.getAPPLICATIONOBJECT() == null ? null : obj.getAPPLICATIONOBJECT().getValue());
//                preparedStatement.setString(6, obj.getPUBLISHDATE() == null|| obj.getPUBLISHDATE().getValue()==null? null : dateFormat.format(dateFormat2.parse(obj.getPUBLISHDATE().getValue().toString().substring(0, 10))));
//                preparedStatement.setString(7, obj.getEFFECTIVEDATE() == null|| obj.getEFFECTIVEDATE().getValue()==null ? null : dateFormat.format(dateFormat2.parse(obj.getEFFECTIVEDATE().getValue().toString().substring(0, 10))));
//                preparedStatement.setString(8, obj.getEXPIREDATE() ==null|| obj.getEXPIREDATE().getValue() == null ? null : dateFormat.format(dateFormat2.parse(obj.getEXPIREDATE().getValue().toString().substring(0, 10))));
//                preparedStatement.setString(9, obj.getSCOPE() == null ? null : obj.getSCOPE().getValue());
//                preparedStatement.setString(10, obj.getSTANDARDTYPE().getValue() == null ? null : obj.getSTANDARDTYPE().getValue().toString());
//                preparedStatement.setString(11, obj.getPUBLISHAGENCYNAME() == null ? null : obj.getPUBLISHAGENCYNAME().getValue());
//                preparedStatement.executeUpdate();
//                rs = true;
//            }
//
//        } catch (Exception ex) {
//            rs = false;
//        }
//        return rs;
//    }

    public String senddresponsedToToXml(Object obj) {
        String result = "";
        java.io.StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SENDRESPONSEDto.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
// output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(obj, sw);
            result = sw.toString();
        } catch (JAXBException ex) {
            result = "Lỗi";
        }
        return result;
    }

    public String getMATCHINGTARGET_NAME(List<MATCHINGTARGETDtoType> list) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, SQLException {
        String rs = "";
        String hql = "Select VIETNAMESE_NAME from TECHNICAL_STANDARD where STANDARD_CODE = ?";
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = (Document) dBuilder.parse(xmlErrType());
        Element econn = (Element) doc.getElementsByTagName("CONNECTION").item(0);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection(econn.getAttribute("Url"), econn.getAttribute("UserName"), econn.getAttribute("Password"));

        for (MATCHINGTARGETDtoType mATCHINGTARGETDtoType : list) {
            PreparedStatement preparedStatement = conn.prepareStatement(hql);
            preparedStatement.setString(1, mATCHINGTARGETDtoType.getTARGETCODE());
            ResultSet emps = preparedStatement.executeQuery();
            if(emps.next())
            {
            rs += ";" + emps.getString("VIETNAMESE_NAME");
            }
        }
        rs = rs.trim().substring(1);
        return rs;
    }

    public String getIMPACTTARGET_NAME(List<IMPACTTARGETDtoType> list) {
        String rs = "Chỉ tiêu mức thôi nhiễm:\n";
        for (IMPACTTARGETDtoType iMPACTTARGETDtoType : list) {
            rs += "\t" + iMPACTTARGETDtoType.getTARGETNAME() + " Mức công bố: " + iMPACTTARGETDtoType.getPUBLISHLEVEL() + iMPACTTARGETDtoType.getUNITCODE();
        }
        return rs;
    }

//    public String sendAnnounceResule(String annNo) throws JAXBException {
//        AnnouncementReceiptPaper annrpbo = null;
//        BaseWS bw = new BaseWS();
//        AnnouncementReceiptPaperDAOHE annrpdaohe = new AnnouncementReceiptPaperDAOHE();
//        annrpbo = annrpdaohe.getAnnouncementReceiptPaperToWs(annNo);
//        ANNOUNCERESULTDto annouceResult = new ANNOUNCERESULTDto();
//        annouceResult.setCOMMENTS("");
//        annouceResult.setNSWFILECODE("");
//        if (annrpbo != null) {
//            RESULTPAGER resultpaper = new RESULTPAGER();
//            Files filesbo = new Files();
//            FilesDAOHE fdaohe = new FilesDAOHE();
//            filesbo = fdaohe.getFilesByAnnrpId(annrpbo.getAnnouncementReceiptPaperId());
//            if (filesbo != null) {
//                resultpaper.setBUSINESSADDRESS(filesbo.getBusinessAddress());
//                annouceResult.setDEPTCODE(bw.getDepartment(filesbo.getDeptId()).getDeptCode());
//                annouceResult.setSIGNDATE(DateTimeUtils.convertDateToString(filesbo.getSignDate(), "dd/MM/yyyy"));
//                annouceResult.setSTATUSCODE(filesbo.getStatus().toString());
//            }
//            resultpaper.setBUSINESSEMAIL(annrpbo.getEmail());
//            resultpaper.setBUSINESSFAX(annrpbo.getFax());
//            resultpaper.setBUSINESSNAME(annrpbo.getBusinessName());
//            resultpaper.setBUSINESSPHONE(annrpbo.getTelephone());
//            Business business = new Business();
//            BusinessDAOHE businessdaohe = new BusinessDAOHE();
//            business = businessdaohe.findById(annrpbo.getBusinessId());
//            resultpaper.setBUSSINESSCODE(business.getBusinessTaxCode());
//            resultpaper.setEFFECTIVEDATE(DateTimeUtils.convertDateToString(annrpbo.getEffectiveDate(), "dd/MM/yyyy"));
//            resultpaper.setMANUFACTUREADD(annrpbo.getManufactureAdd());
//            resultpaper.setMANUFACTURENAME(annrpbo.getManufactureName());
//            resultpaper.setNATIONCODE(bw.getNationCode(annrpbo.getNationName()));
//            resultpaper.setPRODUCTNAME(annrpbo.getProductName());
//            resultpaper.setRECEIPTDATE(DateTimeUtils.convertDateToString(annrpbo.getReceiptDate(), "dd/MM/yyyy"));
//            resultpaper.setRECEIPTDEPTNAME(annrpbo.getReceiptDeptName());
//            resultpaper.setRECEIPTNO(annrpbo.getReceiptNo());
//            annouceResult.setSIGNERNAME(annrpbo.getSignerName());
//            if (resultpaper != null) {
//                annouceResult.setRESULTPAGER(resultpaper);
//            }
//        }
//
//        return senddresponsedToToXml(annouceResult);
//    }
}
