/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.FORM;

import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.BO.MainlyTarget;
import com.viettel.hqmc.BO.ProductTarget;
import com.viettel.hqmc.BO.QualityControlPlan;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.voffice.database.BO.VoAttachs;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement
public class FILE_ITEMS {

    private String ID;
    private String FILE_ATTP_CODE;
    private String TYPE;
    private Long STATUS;
    private Long DEPT_ID;
    private Long PROVINCE_ID;
    private String BUSSINESS_ID;
    private String BUSSINESS_NAME;
    private String BUSSINESS_PHONE;
    private String BUSSINESS_FAX;
    private String BUSSINESS_EMAIL;
    private String BUSSINESS_ADDRESS;
    private String HS_CODE;
    private Long PRODUCT_TYPE;
    private Long PRODUCT_ID;
    private String MANUFACTURE_NAME;
    private String MANUFACTURE_PHONE;
    private String MANUFACTURE_ADDRESS;
    private String MANUFACTURE_EMAIL;
    private String MANUFACTURE_FAX;
    private String NATION_CODE;
    private String ANNOUNCE_NUMBER;
    private Date ANNOUNCE_DATE;
    private Date ANNOUNCE_SIGN_DATE;
    private String ANNOUNCE_SIGN_NAME;
    private List<TARGET> MATCHING_TARGET;
    private String ANNOUNCE_METHOD;
    private Date SEND_DATE;
    private Long USER_CREATE_ID;
    private PRODUCT_DETAILS PRODUCT_DETAILS;
    private List<TARGET> PRODUCT_MAINLY_TARGETS;
    private List<TARGET> PRODUCT_BIO_TARGET;
    private List<TARGET> PRODUCT_HEAVY_METAL;
    private List<TARGET> PRODUCT_UNEX_CHEMICAL;
    private List<TARGET> PRODUCT_PHYSIC_CHEMICAL;

    private List<QUALITY_CONTROL_PLAN> QUALITY;
    private String QUALITY_CONTROLLER;
    private List<ATTACH> ATTACHMENTS;

    public FILE_ITEMS() {
    }

    public FILE_ITEMS(String ID, String FILE_ATTP_CODE, String TYPE, Long STATUS, Long DEPT_ID, Long PROVINCE_ID, String BUSSINESS_ID, String BUSSINESS_NAME, String BUSSINESS_PHONE, String BUSSINESS_FAX, String BUSSINESS_EMAIL, String BUSSINESS_ADDRESS, String HS_CODE, Long PRODUCT_TYPE, Long PRODUCT_ID, String MANUFACTURE_NAME, String MANUFACTURE_PHONE, String MANUFACTURE_ADDRESS, String MANUFACTURE_EMAIL, String MANUFACTURE_FAX, String NATION_CODE, String ANNOUNCE_NUMBER, Date ANNOUNCE_DATE, Date ANNOUNCE_SIGN_DATE, String ANNOUNCE_SIGN_NAME, List<TARGET> MATCHING_TARGET, String ANNOUNCE_METHOD, Date SEND_DATE, Long USER_CREATE_ID, PRODUCT_DETAILS PRODUCT_DETAILS, List<TARGET> PRODUCT_MAINLY_TARGETS, List<TARGET> PRODUCT_BIO_TARGET, List<TARGET> PRODUCT_HEAVY_METAL, List<TARGET> PRODUCT_UNEX_CHEMICAL, List<TARGET> PRODUCT_PHYSIC_CHEMICAL, List<QUALITY_CONTROL_PLAN> QUALITY, String QUALITY_CONTROLLER, List<ATTACH> ATTACHMENTS) {
        this.ID = ID;
        this.FILE_ATTP_CODE = FILE_ATTP_CODE;
        this.TYPE = TYPE;
        this.STATUS = STATUS;
        this.DEPT_ID = DEPT_ID;
        this.PROVINCE_ID = PROVINCE_ID;
        this.BUSSINESS_ID = BUSSINESS_ID;
        this.BUSSINESS_NAME = BUSSINESS_NAME;
        this.BUSSINESS_PHONE = BUSSINESS_PHONE;
        this.BUSSINESS_FAX = BUSSINESS_FAX;
        this.BUSSINESS_EMAIL = BUSSINESS_EMAIL;
        this.BUSSINESS_ADDRESS = BUSSINESS_ADDRESS;
        this.HS_CODE = HS_CODE;
        this.PRODUCT_TYPE = PRODUCT_TYPE;
        this.PRODUCT_ID = PRODUCT_ID;
        this.MANUFACTURE_NAME = MANUFACTURE_NAME;
        this.MANUFACTURE_PHONE = MANUFACTURE_PHONE;
        this.MANUFACTURE_ADDRESS = MANUFACTURE_ADDRESS;
        this.MANUFACTURE_EMAIL = MANUFACTURE_EMAIL;
        this.MANUFACTURE_FAX = MANUFACTURE_FAX;
        this.NATION_CODE = NATION_CODE;
        this.ANNOUNCE_NUMBER = ANNOUNCE_NUMBER;
        this.ANNOUNCE_DATE = ANNOUNCE_DATE;
        this.ANNOUNCE_SIGN_DATE = ANNOUNCE_SIGN_DATE;
        this.ANNOUNCE_SIGN_NAME = ANNOUNCE_SIGN_NAME;
        this.MATCHING_TARGET = MATCHING_TARGET;
        this.ANNOUNCE_METHOD = ANNOUNCE_METHOD;
        this.SEND_DATE = SEND_DATE;
        this.USER_CREATE_ID = USER_CREATE_ID;
        this.PRODUCT_DETAILS = PRODUCT_DETAILS;
        this.PRODUCT_MAINLY_TARGETS = PRODUCT_MAINLY_TARGETS;
        this.PRODUCT_BIO_TARGET = PRODUCT_BIO_TARGET;
        this.PRODUCT_HEAVY_METAL = PRODUCT_HEAVY_METAL;
        this.PRODUCT_UNEX_CHEMICAL = PRODUCT_UNEX_CHEMICAL;
        this.PRODUCT_PHYSIC_CHEMICAL = PRODUCT_PHYSIC_CHEMICAL;
        this.QUALITY = QUALITY;
        this.QUALITY_CONTROLLER = QUALITY_CONTROLLER;
        this.ATTACHMENTS = ATTACHMENTS;
    }

    public FilesForm wsToForm() {
        FilesForm form = new FilesForm();
        form.setFileCode(FILE_ATTP_CODE);
        form.setSendDate(SEND_DATE);
        form.setBusinessName(BUSSINESS_NAME);
        form.setDeptId(DEPT_ID);
        form.setBusinessAddress(BUSSINESS_ADDRESS);
        form.setManufactureName(MANUFACTURE_NAME);
        form.setManufactureAddress(MANUFACTURE_ADDRESS);
        form.setNationName(NATION_CODE);
        form.setFileId(Long.parseLong(ID));
        //convert tim kiem trong db lay id file type
//        form.setFileType(TYPE);
        form.setFileCode(FILE_ATTP_CODE);
        //lay status trong 
        form.setStatus(STATUS);
        form.setBusinessName(BUSSINESS_NAME);
        form.setUserCreateId(USER_CREATE_ID);
        form.setSendDate(SEND_DATE);
        form.setDeptId(DEPT_ID);
        form.setAnnouncementNo(ANNOUNCE_NUMBER);
        form.setBusinessAddress(BUSSINESS_ADDRESS);
        //thieu product name
        //form.setProductName();
        form.setManufactureName(MANUFACTURE_NAME);
        form.setManufactureAddress(MANUFACTURE_ADDRESS);
        //hien tai he thong dang de la string k phai danh muc
//        form.setMatchingTarget(MATCHING_TARGET);
        //hien tai he thong dang quan ly theo ten khong theo danh muc
        form.setNationName(NATION_CODE);

        List<MainlyTarget> lstMainlyTarget = new ArrayList<MainlyTarget>();
        for (TARGET bo : MATCHING_TARGET) {
            MainlyTarget temp = new MainlyTarget();
            temp.setMeetLevel(bo.getMAX_LEVEL());
            temp.setTargetName(bo.getTARGET_NAME());
            temp.setUnitId(bo.getUNIT_ID());
            lstMainlyTarget.add(temp);
        }
        form.setLstMainlyTarget(lstMainlyTarget);

        List<ProductTarget> lstBioTarget = new ArrayList<ProductTarget>();
        for (TARGET bo : PRODUCT_BIO_TARGET) {
            ProductTarget temp = new ProductTarget();
            temp.setMaxLevel(bo.getMAX_LEVEL());
            temp.setTargetName(bo.getTARGET_NAME());
            temp.setUnitId(bo.getUNIT_ID());
            lstBioTarget.add(temp);
        }
        form.setLstBioTarget(lstBioTarget);
        List<ProductTarget> lstChemical = new ArrayList<ProductTarget>();
        for (TARGET bo : PRODUCT_PHYSIC_CHEMICAL) {
            ProductTarget temp = new ProductTarget();
            temp.setMaxLevel(bo.getMAX_LEVEL());
            temp.setTargetName(bo.getTARGET_NAME());
            temp.setUnitId(bo.getUNIT_ID());
            lstChemical.add(temp);
        }
        form.setLstChemical(lstChemical);
        List<ProductTarget> lstHeavyMetal = new ArrayList<ProductTarget>();
        for (TARGET bo : PRODUCT_PHYSIC_CHEMICAL) {
            ProductTarget temp = new ProductTarget();
            temp.setMaxLevel(bo.getMAX_LEVEL());
            temp.setTargetName(bo.getTARGET_NAME());
            temp.setUnitId(bo.getUNIT_ID());
            lstHeavyMetal.add(temp);
        }
        form.setLstHeavyMetal(lstHeavyMetal);
        List<VoAttachs> lstAttachs = new ArrayList<VoAttachs>();
        for (ATTACH bo : ATTACHMENTS) {
            VoAttachs temp = new VoAttachs();
//            temp.get(bo.getATTACH_DATA());
            temp.setAttachDes(bo.getATTACH_DES());
            temp.setAttachName(bo.getATTACH_NAME());
            lstAttachs.add(temp);
        }
        form.setLstAttachs(lstAttachs);
        List<QualityControlPlan> lstQualityControl = new ArrayList<QualityControlPlan>();
        for (QUALITY_CONTROL_PLAN bo : QUALITY) {
            QualityControlPlan temp = new QualityControlPlan();
            temp.setControlTarget(bo.getCONTROL_TARGET());
            temp.setNoteForm(bo.getNOTE_FORM());
            temp.setPatternFrequence(bo.getPATTERN_FREQUENCE());
            temp.setProductProcessDetail(bo.getPRODUCT_PROCESS_DETAIL());
            temp.setTechnicalRegulation(bo.getTECHNICAL_REGULATION());
            temp.setTestDevice(bo.getTEST_DEVICE());
            temp.setTestMethod(bo.getTEST_METHOD());
            lstQualityControl.add(temp);
        }
        form.setLstQualityControl(lstQualityControl);
        form.setProductType(PRODUCT_TYPE);
        return form;
    }

    public Files wsToBO() {
        Files bo = new Files();
//        bo.setFileId(originalFiles.getFileId());
//        bo.setFileType(TYPE);
//        bo.setFileTypeName();

//        bo.setCreateDate(SEND_DATE);
//        bo.setModifyDate(SEND_DATE);
//        bo.setDeadline();
//        bo.setStatus();//sai kieu
//        bo.setStaffRequest();
//        bo.setLeaderStaffRequest();
//        bo.setLeaderRequest();
//        bo.setDisplayRequest();
//        bo.setReIssueFormId();
//        bo.setAnnouncementId();
//        bo.setDetailProductId();
//        bo.setTestRegistrationId();
//        bo.setUserCreateId();
//        bo.setUserCreateName();
//        bo.setNodeId();
//        bo.setPreviousNodeId();
//        bo.setFlowId();
//        bo.setPreviousVersion();
//        bo.setDeptName();
//        bo.setAnnouncementNo();
//        bo.setBusinessLicence();
//        bo.setProductName();
//        bo.setMatchingTarget();
//        bo.setAnnouncementReceiptPaperId();
//        bo.setConfirmAnnouncementPaperId();
//        bo.setConfirmImportSatistPaperId();
//        bo.setAgencyId();
//        bo.setAgencyName();
//        bo.setApproveDate();
//        bo.setIsActive();
//        bo.setSigningDate();
//        bo.setStaffSigningId();
//        bo.setStaffSigningName();
//        bo.setSigningContent();
//        bo.setSignDate();
//        bo.setLeaderStaffSignId();
//        bo.setSignContent();
//        bo.setLeaderStaffSignName();
//        bo.setDisplayStatus();
//        bo.setNodeHistory();
//        bo.setEffectiveDate();
//        bo.setIsFee();
//        bo.setSignCa();
//        bo.setContentSigned(originalFiles.getContentSigned());
//        bo.setUserSigned(originalFiles.getUserSigned());
//        bo.setIsComparison();
//        bo.setComparisonContent();
//        bo.setFeeFile();
//        bo.setEvaluateAddDate();
//        bo.setEvaluateOutAddDate();
//        bo.setClericalRequest();
//        bo.setIsTypeChange();
//        bo.setLastType();
//        if (originalFiles.getIsActive() == null) {
//            bo.setIsActive(1l);
//        } else {
//            bo.setIsActive();
//        }
//        bo.setIsTemp(1L);
//        bo.setOriginalId();
        return bo;

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFILE_ATTP_CODE() {
        return FILE_ATTP_CODE;
    }

    public void setFILE_ATTP_CODE(String FILE_ATTP_CODE) {
        this.FILE_ATTP_CODE = FILE_ATTP_CODE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public Long getDEPT_ID() {
        return DEPT_ID;
    }

    public void setDEPT_ID(Long DEPT_ID) {
        this.DEPT_ID = DEPT_ID;
    }

    public Long getPROVINCE_ID() {
        return PROVINCE_ID;
    }

    public void setPROVINCE_ID(Long PROVINCE_ID) {
        this.PROVINCE_ID = PROVINCE_ID;
    }

    public String getBUSSINESS_ID() {
        return BUSSINESS_ID;
    }

    public void setBUSSINESS_ID(String BUSSINESS_ID) {
        this.BUSSINESS_ID = BUSSINESS_ID;
    }

    public String getBUSSINESS_NAME() {
        return BUSSINESS_NAME;
    }

    public void setBUSSINESS_NAME(String BUSSINESS_NAME) {
        this.BUSSINESS_NAME = BUSSINESS_NAME;
    }

    public String getBUSSINESS_PHONE() {
        return BUSSINESS_PHONE;
    }

    public void setBUSSINESS_PHONE(String BUSSINESS_PHONE) {
        this.BUSSINESS_PHONE = BUSSINESS_PHONE;
    }

    public String getBUSSINESS_FAX() {
        return BUSSINESS_FAX;
    }

    public void setBUSSINESS_FAX(String BUSSINESS_FAX) {
        this.BUSSINESS_FAX = BUSSINESS_FAX;
    }

    public String getBUSSINESS_EMAIL() {
        return BUSSINESS_EMAIL;
    }

    public void setBUSSINESS_EMAIL(String BUSSINESS_EMAIL) {
        this.BUSSINESS_EMAIL = BUSSINESS_EMAIL;
    }

    public String getBUSSINESS_ADDRESS() {
        return BUSSINESS_ADDRESS;
    }

    public void setBUSSINESS_ADDRESS(String BUSSINESS_ADDRESS) {
        this.BUSSINESS_ADDRESS = BUSSINESS_ADDRESS;
    }

    public String getHS_CODE() {
        return HS_CODE;
    }

    public void setHS_CODE(String HS_CODE) {
        this.HS_CODE = HS_CODE;
    }

    public Long getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(Long STATUS) {
        this.STATUS = STATUS;
    }

    public Long getPRODUCT_TYPE() {
        return PRODUCT_TYPE;
    }

    public void setPRODUCT_TYPE(Long PRODUCT_TYPE) {
        this.PRODUCT_TYPE = PRODUCT_TYPE;
    }

    public Long getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(Long PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public String getMANUFACTURE_NAME() {
        return MANUFACTURE_NAME;
    }

    public void setMANUFACTURE_NAME(String MANUFACTURE_NAME) {
        this.MANUFACTURE_NAME = MANUFACTURE_NAME;
    }

    public String getMANUFACTURE_PHONE() {
        return MANUFACTURE_PHONE;
    }

    public void setMANUFACTURE_PHONE(String MANUFACTURE_PHONE) {
        this.MANUFACTURE_PHONE = MANUFACTURE_PHONE;
    }

    public String getMANUFACTURE_ADDRESS() {
        return MANUFACTURE_ADDRESS;
    }

    public void setMANUFACTURE_ADDRESS(String MANUFACTURE_ADDRESS) {
        this.MANUFACTURE_ADDRESS = MANUFACTURE_ADDRESS;
    }

    public String getMANUFACTURE_EMAIL() {
        return MANUFACTURE_EMAIL;
    }

    public void setMANUFACTURE_EMAIL(String MANUFACTURE_EMAIL) {
        this.MANUFACTURE_EMAIL = MANUFACTURE_EMAIL;
    }

    public String getMANUFACTURE_FAX() {
        return MANUFACTURE_FAX;
    }

    public void setMANUFACTURE_FAX(String MANUFACTURE_FAX) {
        this.MANUFACTURE_FAX = MANUFACTURE_FAX;
    }

    public String getNATION_CODE() {
        return NATION_CODE;
    }

    public void setNATION_CODE(String NATION_CODE) {
        this.NATION_CODE = NATION_CODE;
    }

    public String getANNOUNCE_NUMBER() {
        return ANNOUNCE_NUMBER;
    }

    public void setANNOUNCE_NUMBER(String ANNOUNCE_NUMBER) {
        this.ANNOUNCE_NUMBER = ANNOUNCE_NUMBER;
    }

    public Date getANNOUNCE_DATE() {
        return ANNOUNCE_DATE;
    }

    public void setANNOUNCE_DATE(Date ANNOUNCE_DATE) {
        this.ANNOUNCE_DATE = ANNOUNCE_DATE;
    }

    public Date getANNOUNCE_SIGN_DATE() {
        return ANNOUNCE_SIGN_DATE;
    }

    public void setANNOUNCE_SIGN_DATE(Date ANNOUNCE_SIGN_DATE) {
        this.ANNOUNCE_SIGN_DATE = ANNOUNCE_SIGN_DATE;
    }

    public String getANNOUNCE_SIGN_NAME() {
        return ANNOUNCE_SIGN_NAME;
    }

    public void setANNOUNCE_SIGN_NAME(String ANNOUNCE_SIGN_NAME) {
        this.ANNOUNCE_SIGN_NAME = ANNOUNCE_SIGN_NAME;
    }

    public List<TARGET> getMATCHING_TARGET() {
        return MATCHING_TARGET;
    }

    public void setMATCHING_TARGET(List<TARGET> MATCHING_TARGET) {
        this.MATCHING_TARGET = MATCHING_TARGET;
    }

    public String getANNOUNCE_METHOD() {
        return ANNOUNCE_METHOD;
    }

    public void setANNOUNCE_METHOD(String ANNOUNCE_METHOD) {
        this.ANNOUNCE_METHOD = ANNOUNCE_METHOD;
    }

    public Date getSEND_DATE() {
        return SEND_DATE;
    }

    public void setSEND_DATE(Date SEND_DATE) {
        this.SEND_DATE = SEND_DATE;
    }

    public Long getUSER_CREATE_ID() {
        return USER_CREATE_ID;
    }

    public void setUSER_CREATE_ID(Long USER_CREATE_ID) {
        this.USER_CREATE_ID = USER_CREATE_ID;
    }

    public PRODUCT_DETAILS getPRODUCT_DETAILS() {
        return PRODUCT_DETAILS;
    }

    public void setPRODUCT_DETAILS(PRODUCT_DETAILS PRODUCT_DETAILS) {
        this.PRODUCT_DETAILS = PRODUCT_DETAILS;
    }

    public List<TARGET> getPRODUCT_MAINLY_TARGETS() {
        return PRODUCT_MAINLY_TARGETS;
    }

    public void setPRODUCT_MAINLY_TARGETS(List<TARGET> PRODUCT_MAINLY_TARGETS) {
        this.PRODUCT_MAINLY_TARGETS = PRODUCT_MAINLY_TARGETS;
    }

    public List<TARGET> getPRODUCT_BIO_TARGET() {
        return PRODUCT_BIO_TARGET;
    }

    public void setPRODUCT_BIO_TARGET(List<TARGET> PRODUCT_BIO_TARGET) {
        this.PRODUCT_BIO_TARGET = PRODUCT_BIO_TARGET;
    }

    public List<TARGET> getPRODUCT_HEAVY_METAL() {
        return PRODUCT_HEAVY_METAL;
    }

    public void setPRODUCT_HEAVY_METAL(List<TARGET> PRODUCT_HEAVY_METAL) {
        this.PRODUCT_HEAVY_METAL = PRODUCT_HEAVY_METAL;
    }

    public List<TARGET> getPRODUCT_UNEX_CHEMICAL() {
        return PRODUCT_UNEX_CHEMICAL;
    }

    public void setPRODUCT_UNEX_CHEMICAL(List<TARGET> PRODUCT_UNEX_CHEMICAL) {
        this.PRODUCT_UNEX_CHEMICAL = PRODUCT_UNEX_CHEMICAL;
    }

    public List<TARGET> getPRODUCT_PHYSIC_CHEMICAL() {
        return PRODUCT_PHYSIC_CHEMICAL;
    }

    public void setPRODUCT_PHYSIC_CHEMICAL(List<TARGET> PRODUCT_PHYSIC_CHEMICAL) {
        this.PRODUCT_PHYSIC_CHEMICAL = PRODUCT_PHYSIC_CHEMICAL;
    }

    public List<QUALITY_CONTROL_PLAN> getQUALITY() {
        return QUALITY;
    }

    public void setQUALITY(List<QUALITY_CONTROL_PLAN> QUALITY) {
        this.QUALITY = QUALITY;
    }

    public String getQUALITY_CONTROLLER() {
        return QUALITY_CONTROLLER;
    }

    public void setQUALITY_CONTROLLER(String QUALITY_CONTROLLER) {
        this.QUALITY_CONTROLLER = QUALITY_CONTROLLER;
    }

    public List<ATTACH> getATTACHMENTS() {
        return ATTACHMENTS;
    }

    public void setATTACHMENTS(List<ATTACH> ATTACHMENTS) {
        this.ATTACHMENTS = ATTACHMENTS;
    }

}
