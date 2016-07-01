/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.ANNOUCERECEIVE;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Administrator
 */
public class ANNOUNCESENDDto {

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "ANNOUNCESENDDto", propOrder = {
        "authenticate",
        "id",
        "fileType",
        "isFee",
        "components",
        "timeinuse",
        "objectusage",
        "useage",
        "guideline",
        "packatematerial",
        "productionprocess",
        "productcompare",
        "nswfilecode",
        "isdeleted",
        "filetypecode",
        "statuscode",
        "deptcode",
        "bussinesscode",
        "businessname",
        "businessaddress",
        "businessphone",
        "businessfax",
        "businessemail",
        "hscode",
        "producttypecode",
        "productname",
        "productno",
        "manufacturename",
        "manufacturephone",
        "manufactureaddress",
        "manufactureemail",
        "manufacturefax",
        "regionfrom",
        "announcenumber",
        "announcedate",
        "announcesignname",
        "announcemethod",
        "senddate",
        "productsmell",
        "productstatus",
        "productcolor",
        "productdetailtypecode",
        "productotherstate",
        "origin",
        "announcesigndate",
        "createdate",
        "createdby",
        "modifydate",
        "modifiedby",
        "matchingtargetlist",
        "matchingtarget",
        "mainlytargetslist",
        "biotargetlist",
        "heavymetallist",
        "unexchemicallist",
        "impacttargetslist",
        "qualitycontrolplan",
        "attachments",
        "signature"
    })
    public class ANNOUNCESENDDtoType {
        @XmlElement(name = "AUTHENTICATE")
        public String authenticate;
        @XmlElement(name = "ID")
        public String id;
        @XmlElement(name = "FILE_TYPE")
        public String fileType;
        @XmlElement(name = "IS_FEE")
        public String isFee;
        @XmlElement(name = "COMPONENTS")
        public String components;
        @XmlElement(name = "TIME_IN_USE")
        public String timeinuse;
        @XmlElement(name = "OBJECT_USAGE")
        public String objectusage;
        @XmlElement(name = "USEAGE")
        public String useage;
        @XmlElement(name = "GUIDELINE")
        public String guideline;
        @XmlElement(name = "PACKATE_MATERIAL")
        public String packatematerial;
        @XmlElement(name = "PRODUCTION_PROCESS")
        public String productionprocess;
        @XmlElement(name = "PRODUCT_COMPARE")
        public String productcompare;
        @XmlElement(name = "NSW_FILE_CODE")
        public String nswfilecode;
        @XmlElement(name = "IS_DELETED1")
        public String isdeleted;
        @XmlElement(name = "FILE_TYPE_CODE")
        public String filetypecode;
        @XmlElement(name = "STATUS_CODE")
        public String statuscode;
        @XmlElement(name = "DEPT_CODE")
        public String deptcode;
        @XmlElement(name = "BUSSINESS_CODE")
        public String bussinesscode;
        @XmlElement(name = "BUSINESS_NAME")
        public String businessname;
        @XmlElement(name = "BUSINESS_ADDRESS")
        public String businessaddress;
        @XmlElement(name = "BUSINESS_PHONE")
        public String businessphone;
        @XmlElement(name = "BUSINESS_FAX")
        public String businessfax;
        @XmlElement(name = "BUSINESS_EMAIL")
        public String businessemail;
        @XmlElement(name = "HS_CODE")
        public String hscode;
        @XmlElement(name = "PRODUCT_TYPE_CODE")
        public String producttypecode;
        @XmlElement(name = "PRODUCT_NAME")
        public String productname;
        @XmlElement(name = "PRODUCT_NO")
        public String productno;
        @XmlElement(name = "MANUFACTURE_NAME")
        public String manufacturename;
        @XmlElement(name = "MANUFACTURE_PHONE")
        public String manufacturephone;
        @XmlElement(name = "MANUFACTURE_ADDRESS")
        public String manufactureaddress;
        @XmlElement(name = "MANUFACTURE_EMAIL")
        public String manufactureemail;
        @XmlElement(name = "MANUFACTURE_FAX")
        public String manufacturefax;
        @XmlElement(name = "REGION_FROM")
        public String regionfrom;
        @XmlElement(name = "ANNOUNCE_NUMBER")
        public String announcenumber;
        @XmlElement(name = "ANNOUNCE_DATE")
        @XmlSchemaType(name = "dateTime")
        public String announcedate;
        @XmlElement(name = "ANNOUNCE_SIGN_NAME")
        public String announcesignname;
        @XmlElement(name = "ANNOUNCE_METHOD")
        public String announcemethod;
        @XmlElement(name = "SEND_DATE")
        @XmlSchemaType(name = "dateTime")
        public String senddate;
        @XmlElement(name = "PRODUCT_SMELL")
        public String productsmell;
        @XmlElement(name = "PRODUCT_STATUS")
        public String productstatus;
        @XmlElement(name = "PRODUCT_COLOR")
        public String productcolor;
        @XmlElement(name = "PRODUCT_DETAIL_TYPE_CODE")
        public String productdetailtypecode;
        @XmlElement(name = "PRODUCT_OTHER_STATE")
        public String productotherstate;
        @XmlElement(name = "ORIGIN")
        public String origin;
        @XmlElement(name = "ANNOUNCE_SIGN_DATE")
        @XmlSchemaType(name = "dateTime")
        public String announcesigndate;
        @XmlElement(name = "CREATE_DATE")
        @XmlSchemaType(name = "dateTime")
        public String createdate;
        @XmlElement(name = "CREATED_BY")
        public String createdby;
        @XmlElement(name = "MODIFY_DATE")
        @XmlSchemaType(name = "dateTime")
        public String modifydate;
        @XmlElement(name = "MODIFIED_BY")
        public String modifiedby;
        @XmlElement(name = "MATCHING_TARGET_LIST")
        public MATCHINGTARGETLISTType matchingtargetlist;
        @XmlElement(name = "MATCHING_TARGET")
        public String matchingtarget;
        @XmlElement(name = "MAINLY_TARGETS_LIST")
        public MAINLYTARGETSLISTType mainlytargetslist;
        @XmlElement(name = "BIO_TARGET_LIST")
        public BIOTARGETLISTType biotargetlist;
        @XmlElement(name = "HEAVY_METAL_LIST")
        public HEAVYMETALLISTType heavymetallist;
        @XmlElement(name = "UNEX_CHEMICAL_LIST")
        public UNEXCHEMICALLISTType unexchemicallist;
        @XmlElement(name = "IMPACT_TARGETS_LIST")
        public IMPACTTARGETSLISTType impacttargetslist;
        @XmlElement(name = "QUALITY_CONTROL_PLAN")
        public QUALITYCONTROLPLANType qualitycontrolplan;
        @XmlElement(name = "ATTACHMENTS")
        public ATTACHMENTSType attachments;
        @XmlElement(name = "Signature")
        public SignatureType signature;
    }
}
