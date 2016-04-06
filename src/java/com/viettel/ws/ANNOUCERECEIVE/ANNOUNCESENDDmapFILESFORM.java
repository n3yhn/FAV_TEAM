/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.ANNOUCERECEIVE;

import com.viettel.hqmc.BO.MainlyTarget;
import com.viettel.hqmc.BO.ProductInFile;
import com.viettel.hqmc.BO.ProductTarget;
import com.viettel.hqmc.BO.QualityControlPlan;
import com.viettel.hqmc.FORM.AnnouncementForm;
import com.viettel.hqmc.FORM.AnnouncementReceiptPaperForm;
import com.viettel.hqmc.FORM.ConfirmAnnouncementPaperForm;
import com.viettel.hqmc.FORM.ConfirmImportSatistPaperForm;
import com.viettel.hqmc.FORM.DetailProductForm;
import com.viettel.hqmc.FORM.EvaluationRecordsForm;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.hqmc.FORM.ReIssueFormForm;
import com.viettel.hqmc.FORM.TestRegistrationForm;
import com.viettel.voffice.database.BO.VoAttachs;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GPCP_BINHNT53
 */
public class ANNOUNCESENDDmapFILESFORM {

    private String authenticate;
    private String id;
    private String components;
    private String timeinuse;
    private String objectusage;
    private String useage;
    private String guideline;
    private String packatematerial;
    private String productionprocess;
    private String productcompare;
    private String nswfilecode;
    private String isdeleted;
    private String filetypecode;
    private String statuscode;
    private Long deptcode;
    private String bussinesscode;
    private String businessname;
    private String businessaddress;
    private String businessphone;
    private String businessfax;
    private String businessemail;
    private String hscode;
    private String producttypecode;
    private String productname;
    private String productno;
    private String manufacturename;
    private String manufacturephone;
    private String manufactureaddress;
    private String manufactureemail;
    private String manufacturefax;
    private String regionfrom;
    private String announcenumber;
    private String announcedate;
    private String announcesignname;
    private String announcemethod;
    private String senddate;
    private String productsmell;
    private String productstatus;
    private String productcolor;
    private String productotherstate;
    private String announcesigndate;
    private String createdate;
    private String createdby;
    private String modifydate;
    private String modifiedby;
    private Long fileId;
    private Long fileType;
    private String fileTypeName;
    private String fileCode;
    private Date createDate;
    private Date modifyDate;
    private Long status;
    private String staffRequest;
    private String leaderStaffRequest;
    private String leaderRequest;
    private String displayRequest;
    private Long reIssueFormId;
    private ReIssueFormForm reIssueForm;
    private Long announcementId;
    private AnnouncementForm announcement;
    private Long detailProductId;
    private DetailProductForm detailProduct;
    private Long testRegistrationId;
    private TestRegistrationForm testRegistration;
    private String businessName;
    private Long userCreateId;
    private String userCreateName;
    private Long nodeId;
    private Long flowId;
    private Long previousVersion;
    private Long isActive;
    private Date sendDate;
    private Date evaluateAddDate;
    private Date evaluateOutAddDate;
    private Date sendDateFrom;
    private Date sendDateTo;
    private Date repDateFrom;
    private Date repDateTo;
    private Date approveDateFrom;
    private Date approveDateTo;
    private Date approveDate;
    private Date signedDate;
    private Date deadline;
    private Long deptId;
    private String deptName;
    private String announcementNo;
    private String businessLicence;
    private String businessAddress;
    private String receiveUser;
    private String productName;
    private String manufactureName;
    private String manufactureAddress;
    private String matchingTarget;
    private String nationName;
    private Long announcementReceiptPaperId;
    private Long confirmAnnouncementPaperId;
    private Long confirmImportSatistPaperId;
    private Date signingDate;
    private Long staffSigningId;
    private String staffSigningName;
    private String signingContent;
    private Date signDate;
    private Long leaderStaffSignId;
    private String signContent;
    private String leaderStaffSignName;
    private Long effectiveDate;
    private Long isFee;
    private Long viewType;
    private String signCa;
    private String contentXml;
    private String contentSigned;
    private String userSigned;
    private String comparisonContent;
    private Long isComparison;
    private Long feeFile;
    private Long isTemp;
    private Long originalId;
    private Long searchType;
    private String clericalRequest;
    private Long repositoriesId;
    private Long ProductType;
    private Long businessProvinceId;
    private String leaderStaff;
    private String Staff;
    private Long repCreator;
    private Long repName;
    private Long repStatus;
    private byte[] qrCode;
    private Long isTypeChange;
    private Long lastType;
    private Date receivedDate;
    private String path; // set file path attach
    private Date deadlineReceived;
    private Date deadlineComment;
    private Date deadlineAddition;
    private Date deadlineApprove;
    private String statusExcel;
    private Long countUA;
    private MATCHINGTARGETLISTType matchingtargetlist;
    private MAINLYTARGETSLISTType mainlytargetslist;
    private BIOTARGETLISTType biotargetlist;
    private HEAVYMETALLISTType heavymetallist;
    private UNEXCHEMICALLISTType unexchemicallist;
    private IMPACTTARGETSLISTType impacttargetslist;
    private QUALITYCONTROLPLANType qualitycontrolplan;
    private ATTACHMENTSType attachments;
    private SignatureType signature;
    private List<MainlyTarget> lstMainlyTarget;
    private List<ProductTarget> lstBioTarget;
    private List<ProductTarget> lstChemical;
    private List<ProductTarget> lstHeavyMetal;
    private List<VoAttachs> lstAttachs;
    private List<QualityControlPlan> lstQualityControl;
    private List<ProductInFile> lstProductInFile;
    private AnnouncementReceiptPaperForm announcementReceiptPaperForm;
    private ConfirmAnnouncementPaperForm confirmAnnouncementPaperForm;
    private ConfirmImportSatistPaperForm confirmImportSatistPaperForm;
    private EvaluationRecordsForm evaluationRecordsForm;

    public String getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(String authenticate) {
        this.authenticate = authenticate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getTimeinuse() {
        return timeinuse;
    }

    public void setTimeinuse(String timeinuse) {
        this.timeinuse = timeinuse;
    }

    public String getObjectusage() {
        return objectusage;
    }

    public void setObjectusage(String objectusage) {
        this.objectusage = objectusage;
    }

    public String getUseage() {
        return useage;
    }

    public void setUseage(String useage) {
        this.useage = useage;
    }

    public String getGuideline() {
        return guideline;
    }

    public void setGuideline(String guideline) {
        this.guideline = guideline;
    }

    public String getPackatematerial() {
        return packatematerial;
    }

    public void setPackatematerial(String packatematerial) {
        this.packatematerial = packatematerial;
    }

    public String getProductionprocess() {
        return productionprocess;
    }

    public void setProductionprocess(String productionprocess) {
        this.productionprocess = productionprocess;
    }

    public String getProductcompare() {
        return productcompare;
    }

    public void setProductcompare(String productcompare) {
        this.productcompare = productcompare;
    }

    public String getNswfilecode() {
        return nswfilecode;
    }

    public void setNswfilecode(String nswfilecode) {
        this.nswfilecode = nswfilecode;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getFiletypecode() {
        return filetypecode;
    }

    public void setFiletypecode(String filetypecode) {
        this.filetypecode = filetypecode;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public Long getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(Long deptcode) {
        this.deptcode = deptcode;
    }

    public String getBussinesscode() {
        return bussinesscode;
    }

    public void setBussinesscode(String bussinesscode) {
        this.bussinesscode = bussinesscode;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public String getBusinessaddress() {
        return businessaddress;
    }

    public void setBusinessaddress(String businessaddress) {
        this.businessaddress = businessaddress;
    }

    public String getBusinessphone() {
        return businessphone;
    }

    public void setBusinessphone(String businessphone) {
        this.businessphone = businessphone;
    }

    public String getBusinessfax() {
        return businessfax;
    }

    public void setBusinessfax(String businessfax) {
        this.businessfax = businessfax;
    }

    public String getBusinessemail() {
        return businessemail;
    }

    public void setBusinessemail(String businessemail) {
        this.businessemail = businessemail;
    }

    public String getHscode() {
        return hscode;
    }

    public void setHscode(String hscode) {
        this.hscode = hscode;
    }

    public String getProducttypecode() {
        return producttypecode;
    }

    public void setProducttypecode(String producttypecode) {
        this.producttypecode = producttypecode;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductno() {
        return productno;
    }

    public void setProductno(String productno) {
        this.productno = productno;
    }

    public String getManufacturename() {
        return manufacturename;
    }

    public void setManufacturename(String manufacturename) {
        this.manufacturename = manufacturename;
    }

    public String getManufacturephone() {
        return manufacturephone;
    }

    public void setManufacturephone(String manufacturephone) {
        this.manufacturephone = manufacturephone;
    }

    public String getManufactureaddress() {
        return manufactureaddress;
    }

    public void setManufactureaddress(String manufactureaddress) {
        this.manufactureaddress = manufactureaddress;
    }

    public String getManufactureemail() {
        return manufactureemail;
    }

    public void setManufactureemail(String manufactureemail) {
        this.manufactureemail = manufactureemail;
    }

    public String getManufacturefax() {
        return manufacturefax;
    }

    public void setManufacturefax(String manufacturefax) {
        this.manufacturefax = manufacturefax;
    }

    public String getRegionfrom() {
        return regionfrom;
    }

    public void setRegionfrom(String regionfrom) {
        this.regionfrom = regionfrom;
    }

    public String getAnnouncenumber() {
        return announcenumber;
    }

    public void setAnnouncenumber(String announcenumber) {
        this.announcenumber = announcenumber;
    }

    public String getAnnouncedate() {
        return announcedate;
    }

    public void setAnnouncedate(String announcedate) {
        this.announcedate = announcedate;
    }

    public String getAnnouncesignname() {
        return announcesignname;
    }

    public void setAnnouncesignname(String announcesignname) {
        this.announcesignname = announcesignname;
    }

    public String getAnnouncemethod() {
        return announcemethod;
    }

    public void setAnnouncemethod(String announcemethod) {
        this.announcemethod = announcemethod;
    }

    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }

    public String getProductsmell() {
        return productsmell;
    }

    public void setProductsmell(String productsmell) {
        this.productsmell = productsmell;
    }

    public String getProductstatus() {
        return productstatus;
    }

    public void setProductstatus(String productstatus) {
        this.productstatus = productstatus;
    }

    public String getProductcolor() {
        return productcolor;
    }

    public void setProductcolor(String productcolor) {
        this.productcolor = productcolor;
    }

    public String getProductotherstate() {
        return productotherstate;
    }

    public void setProductotherstate(String productotherstate) {
        this.productotherstate = productotherstate;
    }

    public String getAnnouncesigndate() {
        return announcesigndate;
    }

    public void setAnnouncesigndate(String announcesigndate) {
        this.announcesigndate = announcesigndate;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getModifydate() {
        return modifydate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getFileType() {
        return fileType;
    }

    public void setFileType(Long fileType) {
        this.fileType = fileType;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getStaffRequest() {
        return staffRequest;
    }

    public void setStaffRequest(String staffRequest) {
        this.staffRequest = staffRequest;
    }

    public String getLeaderStaffRequest() {
        return leaderStaffRequest;
    }

    public void setLeaderStaffRequest(String leaderStaffRequest) {
        this.leaderStaffRequest = leaderStaffRequest;
    }

    public String getLeaderRequest() {
        return leaderRequest;
    }

    public void setLeaderRequest(String leaderRequest) {
        this.leaderRequest = leaderRequest;
    }

    public String getDisplayRequest() {
        return displayRequest;
    }

    public void setDisplayRequest(String displayRequest) {
        this.displayRequest = displayRequest;
    }

    public Long getReIssueFormId() {
        return reIssueFormId;
    }

    public void setReIssueFormId(Long reIssueFormId) {
        this.reIssueFormId = reIssueFormId;
    }

    public ReIssueFormForm getReIssueForm() {
        return reIssueForm;
    }

    public void setReIssueForm(ReIssueFormForm reIssueForm) {
        this.reIssueForm = reIssueForm;
    }

    public Long getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Long announcementId) {
        this.announcementId = announcementId;
    }

    public AnnouncementForm getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(AnnouncementForm announcement) {
        this.announcement = announcement;
    }

    public Long getDetailProductId() {
        return detailProductId;
    }

    public void setDetailProductId(Long detailProductId) {
        this.detailProductId = detailProductId;
    }

    public DetailProductForm getDetailProduct() {
        return detailProduct;
    }

    public void setDetailProduct(DetailProductForm detailProduct) {
        this.detailProduct = detailProduct;
    }

    public Long getTestRegistrationId() {
        return testRegistrationId;
    }

    public void setTestRegistrationId(Long testRegistrationId) {
        this.testRegistrationId = testRegistrationId;
    }

    public TestRegistrationForm getTestRegistration() {
        return testRegistration;
    }

    public void setTestRegistration(TestRegistrationForm testRegistration) {
        this.testRegistration = testRegistration;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Long getUserCreateId() {
        return userCreateId;
    }

    public void setUserCreateId(Long userCreateId) {
        this.userCreateId = userCreateId;
    }

    public String getUserCreateName() {
        return userCreateName;
    }

    public void setUserCreateName(String userCreateName) {
        this.userCreateName = userCreateName;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Long getPreviousVersion() {
        return previousVersion;
    }

    public void setPreviousVersion(Long previousVersion) {
        this.previousVersion = previousVersion;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getEvaluateAddDate() {
        return evaluateAddDate;
    }

    public void setEvaluateAddDate(Date evaluateAddDate) {
        this.evaluateAddDate = evaluateAddDate;
    }

    public Date getEvaluateOutAddDate() {
        return evaluateOutAddDate;
    }

    public void setEvaluateOutAddDate(Date evaluateOutAddDate) {
        this.evaluateOutAddDate = evaluateOutAddDate;
    }

    public Date getSendDateFrom() {
        return sendDateFrom;
    }

    public void setSendDateFrom(Date sendDateFrom) {
        this.sendDateFrom = sendDateFrom;
    }

    public Date getSendDateTo() {
        return sendDateTo;
    }

    public void setSendDateTo(Date sendDateTo) {
        this.sendDateTo = sendDateTo;
    }

    public Date getRepDateFrom() {
        return repDateFrom;
    }

    public void setRepDateFrom(Date repDateFrom) {
        this.repDateFrom = repDateFrom;
    }

    public Date getRepDateTo() {
        return repDateTo;
    }

    public void setRepDateTo(Date repDateTo) {
        this.repDateTo = repDateTo;
    }

    public Date getApproveDateFrom() {
        return approveDateFrom;
    }

    public void setApproveDateFrom(Date approveDateFrom) {
        this.approveDateFrom = approveDateFrom;
    }

    public Date getApproveDateTo() {
        return approveDateTo;
    }

    public void setApproveDateTo(Date approveDateTo) {
        this.approveDateTo = approveDateTo;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Date getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(Date signedDate) {
        this.signedDate = signedDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getAnnouncementNo() {
        return announcementNo;
    }

    public void setAnnouncementNo(String announcementNo) {
        this.announcementNo = announcementNo;
    }

    public String getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufactureName() {
        return manufactureName;
    }

    public void setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
    }

    public String getManufactureAddress() {
        return manufactureAddress;
    }

    public void setManufactureAddress(String manufactureAddress) {
        this.manufactureAddress = manufactureAddress;
    }

    public String getMatchingTarget() {
        return matchingTarget;
    }

    public void setMatchingTarget(String matchingTarget) {
        this.matchingTarget = matchingTarget;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public Long getAnnouncementReceiptPaperId() {
        return announcementReceiptPaperId;
    }

    public void setAnnouncementReceiptPaperId(Long announcementReceiptPaperId) {
        this.announcementReceiptPaperId = announcementReceiptPaperId;
    }

    public Long getConfirmAnnouncementPaperId() {
        return confirmAnnouncementPaperId;
    }

    public void setConfirmAnnouncementPaperId(Long confirmAnnouncementPaperId) {
        this.confirmAnnouncementPaperId = confirmAnnouncementPaperId;
    }

    public Long getConfirmImportSatistPaperId() {
        return confirmImportSatistPaperId;
    }

    public void setConfirmImportSatistPaperId(Long confirmImportSatistPaperId) {
        this.confirmImportSatistPaperId = confirmImportSatistPaperId;
    }

    public Date getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(Date signingDate) {
        this.signingDate = signingDate;
    }

    public Long getStaffSigningId() {
        return staffSigningId;
    }

    public void setStaffSigningId(Long staffSigningId) {
        this.staffSigningId = staffSigningId;
    }

    public String getStaffSigningName() {
        return staffSigningName;
    }

    public void setStaffSigningName(String staffSigningName) {
        this.staffSigningName = staffSigningName;
    }

    public String getSigningContent() {
        return signingContent;
    }

    public void setSigningContent(String signingContent) {
        this.signingContent = signingContent;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Long getLeaderStaffSignId() {
        return leaderStaffSignId;
    }

    public void setLeaderStaffSignId(Long leaderStaffSignId) {
        this.leaderStaffSignId = leaderStaffSignId;
    }

    public String getSignContent() {
        return signContent;
    }

    public void setSignContent(String signContent) {
        this.signContent = signContent;
    }

    public String getLeaderStaffSignName() {
        return leaderStaffSignName;
    }

    public void setLeaderStaffSignName(String leaderStaffSignName) {
        this.leaderStaffSignName = leaderStaffSignName;
    }

    public Long getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Long effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Long getIsFee() {
        return isFee;
    }

    public void setIsFee(Long isFee) {
        this.isFee = isFee;
    }

    public Long getViewType() {
        return viewType;
    }

    public void setViewType(Long viewType) {
        this.viewType = viewType;
    }

    public String getSignCa() {
        return signCa;
    }

    public void setSignCa(String signCa) {
        this.signCa = signCa;
    }

    public String getContentXml() {
        return contentXml;
    }

    public void setContentXml(String contentXml) {
        this.contentXml = contentXml;
    }

    public String getContentSigned() {
        return contentSigned;
    }

    public void setContentSigned(String contentSigned) {
        this.contentSigned = contentSigned;
    }

    public String getUserSigned() {
        return userSigned;
    }

    public void setUserSigned(String userSigned) {
        this.userSigned = userSigned;
    }

    public String getComparisonContent() {
        return comparisonContent;
    }

    public void setComparisonContent(String comparisonContent) {
        this.comparisonContent = comparisonContent;
    }

    public Long getIsComparison() {
        return isComparison;
    }

    public void setIsComparison(Long isComparison) {
        this.isComparison = isComparison;
    }

    public Long getFeeFile() {
        return feeFile;
    }

    public void setFeeFile(Long feeFile) {
        this.feeFile = feeFile;
    }

    public Long getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(Long isTemp) {
        this.isTemp = isTemp;
    }

    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Long originalId) {
        this.originalId = originalId;
    }

    public Long getSearchType() {
        return searchType;
    }

    public void setSearchType(Long searchType) {
        this.searchType = searchType;
    }

    public String getClericalRequest() {
        return clericalRequest;
    }

    public void setClericalRequest(String clericalRequest) {
        this.clericalRequest = clericalRequest;
    }

    public Long getRepositoriesId() {
        return repositoriesId;
    }

    public void setRepositoriesId(Long repositoriesId) {
        this.repositoriesId = repositoriesId;
    }

    public Long getProductType() {
        return ProductType;
    }

    public void setProductType(Long ProductType) {
        this.ProductType = ProductType;
    }

    public Long getBusinessProvinceId() {
        return businessProvinceId;
    }

    public void setBusinessProvinceId(Long businessProvinceId) {
        this.businessProvinceId = businessProvinceId;
    }

    public String getLeaderStaff() {
        return leaderStaff;
    }

    public void setLeaderStaff(String leaderStaff) {
        this.leaderStaff = leaderStaff;
    }

    public String getStaff() {
        return Staff;
    }

    public void setStaff(String Staff) {
        this.Staff = Staff;
    }

    public Long getRepCreator() {
        return repCreator;
    }

    public void setRepCreator(Long repCreator) {
        this.repCreator = repCreator;
    }

    public Long getRepName() {
        return repName;
    }

    public void setRepName(Long repName) {
        this.repName = repName;
    }

    public Long getRepStatus() {
        return repStatus;
    }

    public void setRepStatus(Long repStatus) {
        this.repStatus = repStatus;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }

    public Long getIsTypeChange() {
        return isTypeChange;
    }

    public void setIsTypeChange(Long isTypeChange) {
        this.isTypeChange = isTypeChange;
    }

    public Long getLastType() {
        return lastType;
    }

    public void setLastType(Long lastType) {
        this.lastType = lastType;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDeadlineReceived() {
        return deadlineReceived;
    }

    public void setDeadlineReceived(Date deadlineReceived) {
        this.deadlineReceived = deadlineReceived;
    }

    public Date getDeadlineComment() {
        return deadlineComment;
    }

    public void setDeadlineComment(Date deadlineComment) {
        this.deadlineComment = deadlineComment;
    }

    public Date getDeadlineAddition() {
        return deadlineAddition;
    }

    public void setDeadlineAddition(Date deadlineAddition) {
        this.deadlineAddition = deadlineAddition;
    }

    public Date getDeadlineApprove() {
        return deadlineApprove;
    }

    public void setDeadlineApprove(Date deadlineApprove) {
        this.deadlineApprove = deadlineApprove;
    }

    public String getStatusExcel() {
        return statusExcel;
    }

    public void setStatusExcel(String statusExcel) {
        this.statusExcel = statusExcel;
    }

    public Long getCountUA() {
        return countUA;
    }

    public void setCountUA(Long countUA) {
        this.countUA = countUA;
    }

    public MATCHINGTARGETLISTType getMatchingtargetlist() {
        return matchingtargetlist;
    }

    public void setMatchingtargetlist(MATCHINGTARGETLISTType matchingtargetlist) {
        this.matchingtargetlist = matchingtargetlist;
    }

    public MAINLYTARGETSLISTType getMainlytargetslist() {
        return mainlytargetslist;
    }

    public void setMainlytargetslist(MAINLYTARGETSLISTType mainlytargetslist) {
        this.mainlytargetslist = mainlytargetslist;
    }

    public BIOTARGETLISTType getBiotargetlist() {
        return biotargetlist;
    }

    public void setBiotargetlist(BIOTARGETLISTType biotargetlist) {
        this.biotargetlist = biotargetlist;
    }

    public HEAVYMETALLISTType getHeavymetallist() {
        return heavymetallist;
    }

    public void setHeavymetallist(HEAVYMETALLISTType heavymetallist) {
        this.heavymetallist = heavymetallist;
    }

    public UNEXCHEMICALLISTType getUnexchemicallist() {
        return unexchemicallist;
    }

    public void setUnexchemicallist(UNEXCHEMICALLISTType unexchemicallist) {
        this.unexchemicallist = unexchemicallist;
    }

    public IMPACTTARGETSLISTType getImpacttargetslist() {
        return impacttargetslist;
    }

    public void setImpacttargetslist(IMPACTTARGETSLISTType impacttargetslist) {
        this.impacttargetslist = impacttargetslist;
    }

    public QUALITYCONTROLPLANType getQualitycontrolplan() {
        return qualitycontrolplan;
    }

    public void setQualitycontrolplan(QUALITYCONTROLPLANType qualitycontrolplan) {
        this.qualitycontrolplan = qualitycontrolplan;
    }

    public ATTACHMENTSType getAttachments() {
        return attachments;
    }

    public void setAttachments(ATTACHMENTSType attachments) {
        this.attachments = attachments;
    }

    public SignatureType getSignature() {
        return signature;
    }

    public void setSignature(SignatureType signature) {
        this.signature = signature;
    }

    public List<MainlyTarget> getLstMainlyTarget() {
        return lstMainlyTarget;
    }

    public void setLstMainlyTarget(List<MainlyTarget> lstMainlyTarget) {
        this.lstMainlyTarget = lstMainlyTarget;
    }

    public List<ProductTarget> getLstBioTarget() {
        return lstBioTarget;
    }

    public void setLstBioTarget(List<ProductTarget> lstBioTarget) {
        this.lstBioTarget = lstBioTarget;
    }

    public List<ProductTarget> getLstChemical() {
        return lstChemical;
    }

    public void setLstChemical(List<ProductTarget> lstChemical) {
        this.lstChemical = lstChemical;
    }

    public List<ProductTarget> getLstHeavyMetal() {
        return lstHeavyMetal;
    }

    public void setLstHeavyMetal(List<ProductTarget> lstHeavyMetal) {
        this.lstHeavyMetal = lstHeavyMetal;
    }

    public List<VoAttachs> getLstAttachs() {
        return lstAttachs;
    }

    public void setLstAttachs(List<VoAttachs> lstAttachs) {
        this.lstAttachs = lstAttachs;
    }

    public List<QualityControlPlan> getLstQualityControl() {
        return lstQualityControl;
    }

    public void setLstQualityControl(List<QualityControlPlan> lstQualityControl) {
        this.lstQualityControl = lstQualityControl;
    }

    public List<ProductInFile> getLstProductInFile() {
        return lstProductInFile;
    }

    public void setLstProductInFile(List<ProductInFile> lstProductInFile) {
        this.lstProductInFile = lstProductInFile;
    }

    public AnnouncementReceiptPaperForm getAnnouncementReceiptPaperForm() {
        return announcementReceiptPaperForm;
    }

    public void setAnnouncementReceiptPaperForm(AnnouncementReceiptPaperForm announcementReceiptPaperForm) {
        this.announcementReceiptPaperForm = announcementReceiptPaperForm;
    }

    public ConfirmAnnouncementPaperForm getConfirmAnnouncementPaperForm() {
        return confirmAnnouncementPaperForm;
    }

    public void setConfirmAnnouncementPaperForm(ConfirmAnnouncementPaperForm confirmAnnouncementPaperForm) {
        this.confirmAnnouncementPaperForm = confirmAnnouncementPaperForm;
    }

    public ConfirmImportSatistPaperForm getConfirmImportSatistPaperForm() {
        return confirmImportSatistPaperForm;
    }

    public void setConfirmImportSatistPaperForm(ConfirmImportSatistPaperForm confirmImportSatistPaperForm) {
        this.confirmImportSatistPaperForm = confirmImportSatistPaperForm;
    }

    public EvaluationRecordsForm getEvaluationRecordsForm() {
        return evaluationRecordsForm;
    }

    public void setEvaluationRecordsForm(EvaluationRecordsForm evaluationRecordsForm) {
        this.evaluationRecordsForm = evaluationRecordsForm;
    }

    public FilesForm toFilesForm() {
        FilesForm form = new FilesForm();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        form.setAnnouncement(new AnnouncementForm());
        //form.setFileCode();
        try {
            form.setSendDate(formatter.parse(senddate));
        } catch (ParseException ex) {
            Logger.getLogger(ANNOUNCESENDDmapFILESFORM.class.getName()).log(Level.SEVERE, null, ex);
        }
        //  bat buoc 
        form.getAnnouncement().setBusinessName(businessname);
        form.setDeptId(deptcode);
        form.setUserCreateId(8171804l);
        form.getAnnouncement().setBusinessAddress(businessaddress);
        form.getAnnouncement().setManufactureName(manufacturename);
        form.getAnnouncement().setManufactureAddress(manufactureaddress);
        form.getAnnouncement().setNationName(regionfrom);
        form.getAnnouncement().setProductName(productname);
        form.getAnnouncement().setMatchingTarget("Quy chuẩn kỹ thuật quốc gia về chất lượng nước sinh hoạt");
//        form.setFileId(id);
        form.setFileTypeName("Cấp giấy tiếp nhận bản công bố hợp quy(bên thứ nhất)");
        //convert tim kiem trong db lay id file type
//        form.setFileType(TYPE);
        form.setFileType(Long.parseLong(filetypecode));
        //lay status trong 
        form.setStatus(Long.parseLong(statuscode));
//        form.getAnnouncement().setAnnouncementId(21212132L);
        form.getAnnouncement().setBusinessEmail(businessemail);
        form.getAnnouncement().setBusinessFax(businessfax);
        form.getAnnouncement().setBusinessTelephone(businessphone);
        form.getAnnouncement().setManufactureEmail(manufactureemail);
        form.getAnnouncement().setManufactureFax(manufacturefax);
        form.getAnnouncement().setManufactureTel(manufacturephone);
        form.getAnnouncement().setAnnouncementNo(announcenumber);
        form.getAnnouncement().setAssessmentMethod(announcemethod);
        try {
            form.getAnnouncement().setPublishDate(formatter.parse(announcedate));
        } catch (ParseException ex) {
            Logger.getLogger(ANNOUNCESENDDmapFILESFORM.class.getName()).log(Level.SEVERE, null, ex);
        }
        form.getAnnouncement().setSigner(announcesignname);

        form.setDetailProduct(new DetailProductForm());
        form.getDetailProduct().setProductNo(productno);
        form.getDetailProduct().setProductType(Long.parseLong(producttypecode));

        form.getDetailProduct().setProductSmell(productsmell);
        form.getDetailProduct().setProductStatus(productstatus);
        form.getDetailProduct().setProductColor(productcolor);
        form.getDetailProduct().setProductOtherStatus(productotherstate);
        form.getDetailProduct().setComponents(components);
        form.getDetailProduct().setTimeInUse(timeinuse);
        form.getDetailProduct().setObjectUse(objectusage);
        form.getDetailProduct().setUseage(useage);
        form.getDetailProduct().setGuideline(guideline);
        //form.getDetailProduct().setPreservation();
        form.getDetailProduct().setPackateMaterial(packatematerial);
        form.getDetailProduct().setProductionProcess(productionprocess);
        form.getDetailProduct().setCounterfeitDistinctive(productcompare);
        form.getDetailProduct().setProductTypeName(productname);
        //thieu product name
        //form.setProductName();
        //hien tai he thong dang de la string k phai danh muc
//        form.setMatchingTarget(MATCHING_TARGET);
        //hien tai he thong dang quan ly theo ten khong theo danh muc
        // mainly target
        List<MainlyTarget> lstMainlyTarget = new ArrayList<MainlyTarget>();
        for (MAINLYTARGETSDtoType bo : mainlytargetslist.mainlytargetsDto) {
            MainlyTarget temp = new MainlyTarget();
            temp.setMeetLevel(bo.getMEETLEVELDES());
            temp.setTargetName(bo.getTARGETNAME());

            temp.setPublishLevel(bo.getPUBLISHLEVEL());
            temp.setUnitId(bo.getUNITCODE());
            temp.setUnitName("CFU/g");
            lstMainlyTarget.add(temp);
        }
        form.setLstMainlyTarget(lstMainlyTarget);

        //productbiotarget
        List<ProductTarget> lstBioTarget = new ArrayList<ProductTarget>();
        for (BIOTARGETDtoType bo : biotargetlist.biotargetDto) {
            ProductTarget temp = new ProductTarget();
            temp.setMaxLevel(bo.getMAXLEVEL());
            temp.setTargetName(bo.getTARGETNAME());
            temp.setUnitId(bo.getUNITCODE());
            temp.setUnitName("CFU/g");
            temp.setTargetType(1L);
            lstBioTarget.add(temp);
        }
        form.setLstBioTarget(lstBioTarget);

        // product_physical_chemical
        List<ProductTarget> lstChemical = new ArrayList<ProductTarget>();
        for (CHEMTARGETDtoType bo : unexchemicallist.chemtargetDto) {
            ProductTarget temp = new ProductTarget();
            temp.setMaxLevel(bo.getMAXLEVEL());
            temp.setTargetName(bo.getTARGETNAME());
            temp.setUnitId(bo.getUNITCODE());
            temp.setUnitName("CFU/g");
            temp.setTargetType(3l);
            lstChemical.add(temp);
        }
        form.setLstChemical(lstChemical);
        //form.setMatchingTarget(null);

        //heavy metal
        List<ProductTarget> lstHeavyMetal = new ArrayList<ProductTarget>();
        for (HEAVYTARGETDtoType bo : heavymetallist.heavytargetDto) {
            ProductTarget temp = new ProductTarget();
            temp.setMaxLevel(bo.getMAXLEVEL());
            temp.setTargetName(bo.getTARGETNAME());
            temp.setUnitId(bo.getUNITCODE());
            temp.setUnitName("CFU/g");
            temp.setTargetType(2L);
            lstHeavyMetal.add(temp);
        }
        form.setLstHeavyMetal(lstHeavyMetal);

        // attach - pending
//        List<VoAttachs> lstAttachs = new ArrayList<VoAttachs>();
//        for (ATTACHType bo : attachments.attach) {
//            VoAttachs temp = new VoAttachs();
//            temp.setAttachDes(bo.getATTACHDES());
//            temp.setAttachName(bo.getATTACHNAME());
//            lstAttachs.add(temp);
//        }
//        form.setLstAttachs(null);
        //Quality control plan
        List<QualityControlPlan> lstQualityControl = new ArrayList<QualityControlPlan>();
        for (CONTROLPLANDtoType bo : qualitycontrolplan.controlplanDto) {
            QualityControlPlan temp = new QualityControlPlan();
            temp.setControlTarget(bo.getCONTROLTARGET());
            temp.setNoteForm(bo.getNOTEFORM());
            temp.setPatternFrequence(bo.getPATTERNFREQUENCE());
            temp.setProductProcessDetail(bo.getPRODUCTPROCESSDETAIL());
            temp.setTechnicalRegulation(bo.getTECHNICALREGULATION());
            temp.setTestDevice(bo.getTESTDEVICE());
            temp.setTestMethod(bo.getTESTMETHOD());
            lstQualityControl.add(temp);
        }
        form.setLstQualityControl(lstQualityControl);
        return form;
    }
}
