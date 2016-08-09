/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.common.util;

import com.sun.xml.ws.security.opt.crypto.dsig.keyinfo.X509IssuerSerial;
import static com.viettel.common.util.Constants.TEMP.FEATURE_GENERAL_ENTITIES;
import static com.viettel.common.util.Constants.TEMP.FEATURE_PARAMETER_ENTITIES;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.BO.MainlyTarget;
import com.viettel.hqmc.BO.ProductTarget;
import com.viettel.hqmc.BO.QualityControlPlan;
import com.viettel.hqmc.DAOHE.FilesDAOHE;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.voffice.ca.uds.X509KeySelector;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.cert.X509CRL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;
import sun.security.x509.X509CertImpl;

/**
 *
 * @author gpdn_huannn update binhnt53
 */
public class CommonUtils {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CommonUtils.class);
    public static String[] attrPublishDocToSignedArr = {"fileId", "fileType", "fileTypeName", "fileCode", "announcementId", "detailProductId", "testRegistrationId", "reIssueFormId", "businessName", "userCreateId", "userCreateName", "deptId", "deptName", "announcementNo", "businessLicence", "businessAddress", "productName", "manufactureName", "manufactureAddress", "matchingTarget", "nationName"};
    public static String[] attrLstDetailsArr = {"announcementId", "detailProductId", "testRegistrationId", "reIssueFormId"};
    public static String[] attrAnnounmenceArr = {"announcementId", "announcementNo", "businessName", "businessLicence", "businessAddress", "businessTelephone", "businessFax", "businessEmail", "productName", "manufactureName", "manufactureAddress", "manufactureTel", "manufactureFax", "manufactureEmail", "nationName", "matchingTarget", "assessmentMethod"};
    public static String[] attrDetailsProductArr = {"detailProductId", "productType", "productTypeName", "productSmell", "productStatus", "productColor", "productOtherStatus", "components", "useage", "timeInUse", "objectUse", "guideline", "preservation", "packateMaterial", "packageRecipe", "productionProcess", "counterfeitDistinctive", "signDate", "signer", "origin", "productNo", "otherTarget"};
    public static String[] attrTestRegistrationArr = {"testRegistrationId", "exportBusinessName", "exportBusinessAdd", "exportBusinessMail", "exportBusinessTel", "exportBusinessFax", "exportContractCode", "exportContractDate", "exportLadingCode", "exportLadingDate", "exportPort", "importBusinessName", "importBusinessAddress", "importBusinessEmail", "importBusinessTel", "importBusinessFax", "importPort", "importDate", "productName", "productDescription", "productCode", "productOrigin", "productAmount", "productWeight", "productValue", "gatheringAdd", "testDate", "testAdd", "businessRepresent", "businessSigndate", "businessSignAdd", "agencyRepresent", "agencySignAdd", "agencySigndate", "standardTargetNo", "standardTargetDate", "releaseDocumentNo", "releaseDocumentDate", "testAgency", "isActive"};
    public static String[] attrAttachFileNotSignedArr = {"serialVersionUID", "attachId"};
    public static String[] attrDestroyRecordNotSignedArr = {"serialVersionUID", "destroyRecordId", "contentSigned", "userSigned", "status"};
    public static String[] attrMainlytarget = {"targetName", "unitName", "unitId", "publishLevel"};
    public static String[] attrProductTarget = {"targetName", "targetType", "unitName", "unitId", "maxLevel"};
    public static String[] attrQuanlityControl = {"productProcessDetail", "controlTarget", "technicalRegulation", "patternFrequence", "testDevice", "testMethod", "noteForm", "note"};

    public static String getWSPassword() {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        return rb.getString("WSPassword");
    }

    public static Document buildXmlTemplate(String root) {
        Document doc = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            docFactory.setFeature(FEATURE_GENERAL_ENTITIES, false);
            docFactory.setFeature(FEATURE_PARAMETER_ENTITIES, false);
            docFactory.setXIncludeAware(false);
            docFactory.setExpandEntityReferences(false);
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(root);
            doc.appendChild(rootElement);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return doc;
    }

    public static Document buildAllPublishDocument(Long filesId) throws Exception {
        FilesDAOHE fhe = new FilesDAOHE();
        Files fileBo = fhe.getById("fileId", filesId);
        FilesForm fileForm = fhe.returnFilesForm(fileBo);
        Document document = CommonUtils.buildXmlTemplate("ROOT");
        Element root = document.getDocumentElement();
        Element dataElement = document.createElement("DATA");
        root.appendChild(dataElement);
        List<FilesForm> lstJud = new ArrayList<FilesForm>();
        lstJud.add(fileForm);
        document = CommonUtils.buildXmlPublishDocument(document, dataElement, lstJud);
        return document;
    }

    public static Document buildXmlPublishDocument(Document document, Element dataElement, Collection<FilesForm> voPublishCollection) throws Exception {
        for (FilesForm publicBo : voPublishCollection) {
            Element element = document.createElement("PUBLIC_ELEMENT");//LLTPUtil.ROOT_JUDGMENT
            Class cls = publicBo.getClass();
            Field[] fieldArr = cls.getDeclaredFields();

            String fieldName;
            String getMethodName = "";
            Method getMethod;
            String value;
            for (int i = 0; i < fieldArr.length; i++) {
                if (!isInArrToSign(fieldArr[i].getName(), attrPublishDocToSignedArr)) {
                    continue;
                }

                if (fieldArr[i].getType().equals(Date.class)) {
                    try {
                        fieldName = fieldArr[i].getName();
                        getMethodName = "get" + UpcaseFirst(fieldName);
                        getMethod = cls.getMethod(getMethodName);
                        value = dateToString((Date) getMethod.invoke(publicBo));
                        Element elmt = document.createElement(fieldName);
                        elmt.appendChild(document.createTextNode(com.viettel.common.util.StringUtils.escapeXML(value)));
                        element.appendChild(elmt);
                    } catch (Exception ex) {
                        System.out.print("\nno method or invoke: " + cls.getName() + ":" + getMethodName);
                    }
                } else {
                    try {
                        if (!isInArrToSign(fieldArr[i].getName(), attrLstDetailsArr)) {
                            fieldName = fieldArr[i].getName();
                            getMethodName = "get" + UpcaseFirst(fieldName);
                            getMethod = cls.getMethod(getMethodName);
                            if (getMethod.invoke(publicBo) == null) {
                                value = "";
                            } else {
                                value = String.valueOf(getMethod.invoke(publicBo));
                            }
                            Element elmt = document.createElement(fieldName);
                            elmt.appendChild(document.createTextNode(value));
                            element.appendChild(elmt);
                        } else {
                            //check announcement
                            if (fieldArr[i].getName().trim().equals("announcementId")) {
                                Class cls1 = publicBo.getAnnouncement().getClass();
                                Field[] fieldArr1 = cls1.getDeclaredFields();
                                String fieldName1;
                                String getMethodName1 = "";
                                Method getMethod1;
                                String value1;
                                for (int j = 0; j < fieldArr1.length; j++) {
                                    if (!isInArrToSign(fieldArr1[j].getName(), attrAnnounmenceArr)) {
                                        continue;
                                    }

                                    if (fieldArr1[j].getType().equals(Date.class)) {
                                        try {
                                            fieldName1 = fieldArr1[i].getName();
                                            getMethodName1 = "get" + UpcaseFirst(fieldName1);
                                            getMethod1 = cls.getMethod(getMethodName1);
                                            value1 = dateToString((Date) getMethod1.invoke(publicBo.getAnnouncement()));
                                            Element elmt1 = document.createElement(fieldName1);
                                            elmt1.appendChild(document.createTextNode(com.viettel.common.util.StringUtils.escapeXML(value1)));
                                            element.appendChild(elmt1);
                                        } catch (Exception ex) {
                                            System.out.print("\nno method or invoke: " + cls.getName() + ":" + getMethodName);
                                        }
                                    } else {
                                        try {
                                            fieldName1 = fieldArr1[j].getName();
                                            getMethodName1 = "get" + UpcaseFirst(fieldName1);
                                            getMethod1 = cls1.getMethod(getMethodName1);
                                            if (getMethod1.invoke(publicBo.getAnnouncement()) == null) {
                                                value1 = "";
                                            } else {
                                                value1 = String.valueOf(getMethod1.invoke(publicBo.getAnnouncement()));
                                            }
                                            Element elmt1 = document.createElement(fieldName1);
                                            elmt1.appendChild(document.createTextNode(com.viettel.common.util.StringUtils.escapeXML(value1)));
                                            element.appendChild(elmt1);
                                        } catch (Exception ex) {
                                            System.out.print("\nno method or invoke: " + cls1.getName() + ":" + getMethodName1);
                                        }
                                    }
                                }
                            }
                            //check detailProduct
                            if (fieldArr[i].getName().trim().equals("detailProductId")) {
                                Class cls1 = publicBo.getDetailProduct().getClass();
                                Field[] fieldArr1 = cls1.getDeclaredFields();
                                String fieldName1;
                                String getMethodName1 = "";
                                Method getMethod1;
                                String value1;
                                for (int j = 0; j < fieldArr1.length; j++) {
                                    if (!isInArrToSign(fieldArr1[j].getName(), attrDetailsProductArr)) {
                                        continue;
                                    }

                                    if (fieldArr1[j].getType().equals(Date.class)) {
                                        try {
                                            fieldName1 = fieldArr1[i].getName();
                                            getMethodName1 = "get" + UpcaseFirst(fieldName1);
                                            getMethod1 = cls1.getMethod(getMethodName1);
                                            value1 = dateToString((Date) getMethod1.invoke(publicBo.getDetailProduct()));
                                            Element elmt1 = document.createElement(fieldName1);
                                            elmt1.appendChild(document.createTextNode(com.viettel.common.util.StringUtils.escapeXML(value1)));
                                            element.appendChild(elmt1);
                                        } catch (Exception ex) {
                                            System.out.print("\nno method or invoke: " + cls1.getName() + ":" + getMethodName1);
                                        }
                                    } else {
                                        try {
                                            fieldName1 = fieldArr1[j].getName();
                                            getMethodName1 = "get" + UpcaseFirst(fieldName1);
                                            getMethod1 = cls1.getMethod(getMethodName1);
                                            if (getMethod1.invoke(publicBo.getDetailProduct()) == null) {
                                                value1 = "";
                                            } else {
                                                value1 = String.valueOf(getMethod1.invoke(publicBo.getDetailProduct()));
                                            }
                                            Element elmt1 = document.createElement(fieldName1);
                                            elmt1.appendChild(document.createTextNode(com.viettel.common.util.StringUtils.escapeXML(value1)));
                                            element.appendChild(elmt1);
                                        } catch (Exception ex) {
                                            System.out.print("\nno method or invoke: " + cls1.getName() + ":" + getMethodName1);
                                        }
                                    }
                                }
                            }//
                            //check testRegistrationId
                            if (fieldArr[i].getName().trim().equals("testRegistrationId")) {
                                Class cls1 = publicBo.getTestRegistration().getClass();
                                Field[] fieldArr1 = cls1.getDeclaredFields();
                                String fieldName1;
                                String getMethodName1 = "";
                                Method getMethod1;
                                String value1;
                                for (int j = 0; j < fieldArr1.length; j++) {
                                    if (!isInArrToSign(fieldArr1[j].getName(), attrTestRegistrationArr)) {
                                        continue;
                                    }

                                    if (fieldArr1[j].getType().equals(Date.class)) {
                                        try {
                                            fieldName1 = fieldArr1[i].getName();
                                            getMethodName1 = "get" + UpcaseFirst(fieldName1);
                                            getMethod1 = cls.getMethod(getMethodName1);
                                            value1 = dateToString((Date) getMethod1.invoke(publicBo.getTestRegistration()));
                                            Element elmt1 = document.createElement(fieldName1);
                                            elmt1.appendChild(document.createTextNode(com.viettel.common.util.StringUtils.escapeXML(value1)));
                                            element.appendChild(elmt1);
                                        } catch (Exception ex) {
                                            System.out.print("\nno method or invoke: " + cls.getName() + ":" + getMethodName);
                                        }
                                    } else {
                                        try {
                                            fieldName1 = fieldArr1[j].getName();
                                            getMethodName1 = "get" + UpcaseFirst(fieldName1);
                                            getMethod1 = cls1.getMethod(getMethodName1);
                                            if (getMethod1.invoke(publicBo.getTestRegistration()) == null) {
                                                value1 = "";
                                            } else {
                                                value1 = String.valueOf(getMethod1.invoke(publicBo.getTestRegistration()));
                                            }
                                            Element elmt1 = document.createElement(fieldName1);
                                            elmt1.appendChild(document.createTextNode(com.viettel.common.util.StringUtils.escapeXML(value1)));
                                            element.appendChild(elmt1);
                                        } catch (Exception ex) {
                                            System.out.print("\nno method or invoke: " + cls1.getName() + ":" + getMethodName1);
                                        }
                                    }
                                }
                            }//
                        }
                    } catch (Exception ex) {
                        System.out.print("\nno method or invoke: " + cls.getName() + ":" + getMethodName);
                    }
                }
            }
            FilesDAOHE fhde = new FilesDAOHE();
            List lstMainlyTarget = fhde.getMainlyTargetOfFile(publicBo.getFileId());
            if (lstMainlyTarget != null && lstMainlyTarget.size() > 0) {
                for (int j = 0; j < lstMainlyTarget.size(); j++) {
                    MainlyTarget objTarget = (MainlyTarget) lstMainlyTarget.get(j);
                    Class clsProducttarget = objTarget.getClass();
                    Field[] fieldArrMainlytarget = clsProducttarget.getDeclaredFields();
                    String fieldNameMainlytarget;
                    String getMethodNameTarget = "";
                    Method getMethodTarget;
                    String valueTarget;
                    for (int i = 0; i < fieldArrMainlytarget.length; i++) {
                        if (!isInArrToSign(fieldArrMainlytarget[i].getName(), attrMainlytarget)) {
                            continue;
                        }
                        if (fieldArrMainlytarget[i].getType().equals(Date.class)) {
                            try {
                                fieldNameMainlytarget = fieldArrMainlytarget[i].getName();
                                getMethodNameTarget = "get" + UpcaseFirst(fieldNameMainlytarget);
                                getMethodTarget = clsProducttarget.getMethod(getMethodNameTarget);
                                valueTarget = dateToString((Date) getMethodTarget.invoke(objTarget));
                                Element elmt = document.createElement(fieldNameMainlytarget);
                                elmt.appendChild(document.createTextNode(com.viettel.common.util.StringUtils.escapeXML(valueTarget)));
                                element.appendChild(elmt);
                            } catch (Exception ex) {
                                System.out.print("\nno method or invoke: " + clsProducttarget.getName() + ":" + getMethodNameTarget);
                            }
                        } else {
                            try {
                                fieldNameMainlytarget = fieldArrMainlytarget[i].getName();
                                getMethodNameTarget = "get" + UpcaseFirst(fieldNameMainlytarget);
                                getMethodTarget = clsProducttarget.getMethod(getMethodNameTarget);
                                if (getMethodTarget.invoke(objTarget) == null) {
                                    valueTarget = "";
                                } else {
                                    valueTarget = String.valueOf(getMethodTarget.invoke(objTarget));
                                }
                                Element elmt = document.createElement(fieldNameMainlytarget);
                                elmt.appendChild(document.createTextNode(com.viettel.common.util.StringUtils.escapeXML(valueTarget)));
                                element.appendChild(elmt);
                            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException | DOMException ex) {
                                System.out.print("\nno method or invoke: " + clsProducttarget.getName() + ":" + getMethodNameTarget);
                            }
                        }
                        //System.out.println(fieldNameMainlytarget.toString() + ":" + getMethodNameTarget + ":" + valueTarget);
                    }
                }
            }
            List lstProductTarget = fhde.getProductTargetOfFile(publicBo.getFileId());
            if (lstProductTarget != null && lstProductTarget.size() > 0) {
                for (int j = 0; j < lstProductTarget.size(); j++) {
                    ProductTarget objTarget = (ProductTarget) lstProductTarget.get(j);
                    Class clsProducttarget = objTarget.getClass();
                    Field[] fieldArrMainlytarget = clsProducttarget.getDeclaredFields();
                    String fieldNameMainlytarget;
                    String getMethodNameTarget = "";
                    Method getMethodTarget;
                    String valueTarget;
                    for (int i = 0; i < fieldArrMainlytarget.length; i++) {
                        if (!isInArrToSign(fieldArrMainlytarget[i].getName(), attrProductTarget)) {
                            continue;
                        }
                        if (fieldArrMainlytarget[i].getType().equals(Date.class)) {
                            try {
                                fieldNameMainlytarget = fieldArrMainlytarget[i].getName();
                                getMethodNameTarget = "get" + UpcaseFirst(fieldNameMainlytarget);
                                getMethodTarget = clsProducttarget.getMethod(getMethodNameTarget);
                                valueTarget = dateToString((Date) getMethodTarget.invoke(objTarget));
                                Element elmt = document.createElement(fieldNameMainlytarget);
                                elmt.appendChild(document.createTextNode(com.viettel.common.util.StringUtils.escapeXML(valueTarget)));
                                element.appendChild(elmt);
                            } catch (Exception ex) {
                                System.out.print("\nno method or invoke: " + clsProducttarget.getName() + ":" + getMethodNameTarget);
                            }
                        } else {
                            try {
                                fieldNameMainlytarget = fieldArrMainlytarget[i].getName();
                                getMethodNameTarget = "get" + UpcaseFirst(fieldNameMainlytarget);
                                getMethodTarget = clsProducttarget.getMethod(getMethodNameTarget);
                                if (getMethodTarget.invoke(objTarget) == null) {
                                    valueTarget = "";
                                } else {
                                    valueTarget = String.valueOf(getMethodTarget.invoke(objTarget));
                                }
                                Element elmt = document.createElement(fieldNameMainlytarget);
                                elmt.appendChild(document.createTextNode(com.viettel.common.util.StringUtils.escapeXML(valueTarget)));
                                element.appendChild(elmt);
                            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException | DOMException ex) {
                                System.out.print("\nno method or invoke: " + clsProducttarget.getName() + ":" + getMethodNameTarget);
                            }
                        }
                    }
                }
            }
            List lstQualityControlPanel = fhde.getQualityControlOfFile(publicBo.getFileId());
            if (lstQualityControlPanel != null && lstQualityControlPanel.size() > 0) {
                for (int j = 0; j < lstQualityControlPanel.size(); j++) {
                    QualityControlPlan objTarget = (QualityControlPlan) lstQualityControlPanel.get(j);
                    Class clsProducttarget = objTarget.getClass();
                    Field[] fieldArrMainlytarget = clsProducttarget.getDeclaredFields();
                    String fieldNameMainlytarget;
                    String getMethodNameTarget = "";
                    Method getMethodTarget;
                    String valueTarget;
                    for (int i = 0; i < fieldArrMainlytarget.length; i++) {
                        if (!isInArrToSign(fieldArrMainlytarget[i].getName(), attrQuanlityControl)) {
                            continue;
                        }
                        if (fieldArrMainlytarget[i].getType().equals(Date.class)) {
                            try {
                                fieldNameMainlytarget = fieldArrMainlytarget[i].getName();
                                getMethodNameTarget = "get" + UpcaseFirst(fieldNameMainlytarget);
                                getMethodTarget = clsProducttarget.getMethod(getMethodNameTarget);
                                valueTarget = dateToString((Date) getMethodTarget.invoke(objTarget));
                                Element elmt = document.createElement(fieldNameMainlytarget);
                                elmt.appendChild(document.createTextNode(com.viettel.common.util.StringUtils.escapeXML(valueTarget)));
                                element.appendChild(elmt);
                            } catch (Exception ex) {
                                System.out.print("\nno method or invoke: " + clsProducttarget.getName() + ":" + getMethodNameTarget);
                            }
                        } else {
                            try {
                                fieldNameMainlytarget = fieldArrMainlytarget[i].getName();
                                getMethodNameTarget = "get" + UpcaseFirst(fieldNameMainlytarget);
                                getMethodTarget = clsProducttarget.getMethod(getMethodNameTarget);
                                if (getMethodTarget.invoke(objTarget) == null) {
                                    valueTarget = "";
                                } else {
                                    valueTarget = String.valueOf(getMethodTarget.invoke(objTarget));
                                }
                                Element elmt = document.createElement(fieldNameMainlytarget);
                                elmt.appendChild(document.createTextNode(com.viettel.common.util.StringUtils.escapeXML(valueTarget)));
                                element.appendChild(elmt);
                            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException | DOMException ex) {
                                System.out.print("\nno method or invoke: " + clsProducttarget.getName() + ":" + getMethodNameTarget);
                            }
                        }
                    }
                }
            }
            dataElement.appendChild(element);
        }
        return document;
    }

    public static String createChecksum(FileInputStream file) throws
            Exception {
        try {
            byte[] buffer = new byte[1024];
            MessageDigest complete = MessageDigest.getInstance("SHA-1");
            int numRead;
            do {
                numRead = file.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
            } while (numRead != -1);
            byte[] hashBytes = complete.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < hashBytes.length; i++) {
                sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } finally {
            file.close();
        }
    }

    public static KeyInfo validateContentSigned(String content) throws Exception {
        DocumentBuilderFactory dbf
                = DocumentBuilderFactory.newInstance();
        dbf.setFeature(FEATURE_GENERAL_ENTITIES, false);
        dbf.setFeature(FEATURE_PARAMETER_ENTITIES, false);
        dbf.setXIncludeAware(false);
        dbf.setExpandEntityReferences(false);
        DOMValidateContext valContext = null;
        KeyInfo keyInfo = null;
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(content.getBytes("UTF-8")));
        NodeList nl
                = doc.getElementsByTagNameNS(XMLSignature.XMLNS,
                        "Signature");
        if (nl.getLength() == 0) {
            throw new Exception("Cannot find Signature element");
        }

        String providerName = System.getProperty(
                "jsr105Provider",
                "org.jcp.xml.dsig.internal.dom.XMLDSigRI");
        XMLSignatureFactory fac
                = XMLSignatureFactory.getInstance("DOM",
                        (Provider) Class.forName(providerName).newInstance());
        if (nl.getLength() == 1) {
            valContext = new DOMValidateContext(new X509KeySelector(), nl.item(0));
        } else {
            valContext = new DOMValidateContext(new X509KeySelector(), nl.item(1));
        }

        XMLSignature signature
                = fac.unmarshalXMLSignature(valContext);

        boolean coreValidity = signature.validate(valContext);

        if (coreValidity == false) {
            System.err.println("Signature failed");
        } else {
            keyInfo = signature.getKeyInfo();
            System.out.println("Signature passed");
        }
        return keyInfo;
    }

    public static String getUserSigned(KeyInfo keyInfo) throws Exception {
        String userSigned = "", serial = "";
        Iterator iter = keyInfo.getContent().iterator();
        X509CertImpl certImpl = null;
        while (iter.hasNext()) {
            XMLStructure kiType = (XMLStructure) iter.next();
            if (kiType instanceof X509Data) {
                X509Data xd = (X509Data) kiType;
                Object[] entries = xd.getContent().toArray();
                X509CRL crl = null;
                for (int i = 0; i < entries.length; i++) {
                    if (entries[i] instanceof X509CRL) {
                        crl = (X509CRL) entries[i];
                    }
                    if (entries[i] instanceof X509CertImpl) {
                        certImpl = (X509CertImpl) entries[i];
                    }
                    if (entries[i] instanceof String) {
                        userSigned += (String) entries[i] + "\n";
                    }
                }
            }
        }
        return userSigned;
    }

    public static String getSerial(KeyInfo keyInfo) throws Exception {
        String serial = "";
        Iterator iter = keyInfo.getContent().iterator();
        X509CertImpl certImpl = null;
        while (iter.hasNext()) {
            XMLStructure kiType = (XMLStructure) iter.next();
            if (kiType instanceof X509Data) {
                X509Data xd = (X509Data) kiType;
                Object[] entries = xd.getContent().toArray();
                for (int i = 0; i < entries.length; i++) {
                    if (entries[i] instanceof X509CertImpl) {
                        certImpl = (X509CertImpl) entries[i];
                        serial = certImpl.getSerialNumberObject().toString().replaceAll(" ", "");
                    }
                }
            }
        }
        return serial;
    }

    public static String UpcaseFirst(String str) {
        String first = str.substring(0, 1);
        String concat = str.substring(1);
        return first.toUpperCase() + concat;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        if (date == null) {
            return "";
        }
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static String convertDocument2String(Document document) {
        try {
            TransformerFactory transfac = TransformerFactory.newInstance();
            Transformer trans = transfac.newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(document);
            trans.transform(source, result);
            String xmlString = sw.toString().replaceAll("\n", "");
            xmlString = xmlString.replaceAll("\r", "");
            xmlString = xmlString.replaceAll("\t", "");
            xmlString = xmlString.replaceAll("&", "@#26");

//            System.out.println("\n\n-----------------------------------------------------");
//            System.out.println(xmlString);
            return xmlString;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    public static boolean isInArr(String attr, String[] arr) {
        boolean isSigned = false;
        for (int i = 0; i < arr.length; i++) {
            if (attr.equals(arr[i])) {
                isSigned = true;
                break;
            }
        }
        return isSigned;
    }

    public static boolean isInArrToSign(String attr, String[] arr) {
        boolean isSigned = false;
        attr = attr.replaceAll(" ", "");
        for (int i = 0; i < arr.length; i++) {
            if (attr.equals(arr[i])) {
                isSigned = true;
                break;
            }
        }
        return isSigned;
    }

    public static String getVoRecordWS_URL(String wsType) {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        //check LTDV hay LTCQ
        String url = "";
        if ("LTCQ".equals(wsType)) {
            url = rb.getString("XWS_URL_DV");
        } else if ("LTDV".equals(wsType)) {
            url = rb.getString("XWS_URL_CQ");
        } else {
            url = rb.getString("XWS_URL_CQ");
        }
        return (url + "/VoRecordWS?wsdl");
    }

    public static boolean checkSecurityPublishCA(Long filesId, String contentSigned) {
        boolean isValid = true;
        try {
            Document document = CommonUtils.buildAllPublishDocument(filesId);
            String contentXml = CommonUtils.convertDocument2String(document);
            if (contentXml != null && contentXml.length() > 7) { // 7 = '</ROOT>'
                if (!contentSigned.contains(contentXml.substring(0, contentXml.length() - 7))) {
                    isValid = false;
                }
            } else {
                isValid = false;
            }

        } catch (Exception ex) {
            isValid = false;
            log.error(ex.getMessage());
        }
        return isValid;
    }

    public static String getSerialNumber(KeyInfo keyInfo) throws Exception {
        String userSigned = "";
        String serial;
        Iterator iter = keyInfo.getContent().iterator();
        X509CertImpl certImpl = null;
        while (iter.hasNext()) {
            XMLStructure kiType = (XMLStructure) iter.next();
            if (kiType instanceof X509Data) {
                X509Data xd = (X509Data) kiType;
                Object[] entries = xd.getContent().toArray();
                X509CRL crl = null;
                for (int i = 0; i < entries.length; i++) {
                    if (entries[i] instanceof X509CRL) {
                        crl = (X509CRL) entries[i];
                    }
                    if (entries[i] instanceof X509CertImpl) {
                        certImpl = (X509CertImpl) entries[i];
                    }
                    if (entries[i] instanceof String) {
                        userSigned += (String) entries[i] + "\n";
                    }
                    if (entries[i] instanceof X509IssuerSerial) {
                        serial = entries[i].toString();
                    }
                }
            }
        }
        return userSigned;
    }
}
