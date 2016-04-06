/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

import com.viettel.hqmc.BO.Files;

import com.viettel.hqmc.BO.MainlyTarget;
import com.viettel.hqmc.BO.ProductInFile;
import com.viettel.hqmc.BO.ProductTarget;
import com.viettel.hqmc.BO.QualityControlPlan;
import com.viettel.voffice.database.BO.VoAttachs;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vtit_havm2
 */
@XmlRootElement
public class FilesForm implements Serializable {

    private Long announcementId;
    private Long detailProductId;
    private Long testRegistrationId;
    private Long announcementReceiptPaperId;
    private Long confirmAnnouncementPaperId;
    private Long confirmImportSatistPaperId;

    private ReIssueFormForm reIssueForm;
    private AnnouncementForm announcement;
    private DetailProductForm detailProduct;
    private TestRegistrationForm testRegistration;
    private FilesEditForm filesEditForm;
    private List<QualityControlPlan> lstQualityControl;
    private List<ProductInFile> lstProductInFile;
    private AnnouncementReceiptPaperForm announcementReceiptPaperForm;
    private ConfirmAnnouncementPaperForm confirmAnnouncementPaperForm;
    private ConfirmImportSatistPaperForm confirmImportSatistPaperForm;
    private EvaluationRecordsForm evaluationRecordsForm;
    private EvaluationRecordsFormOnGrid evaluationRecordsFormOnGrid;
    private List<MainlyTarget> lstMainlyTarget;
    private List<ProductTarget> lstBioTarget;
    private List<ProductTarget> lstChemical;
    private List<ProductTarget> lstHeavyMetal;
    private List<ProductTarget> lstInfectedTarget;
    private List<VoAttachs> lstAttachs;
    private RequestCommentForm requestCommentForm;

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
    private Date signDateFrom;
    private Date signDateTo;
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
    private Long agencyId;
    private String agencyName;
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
    // Danh sach thu tu File Nhan dinh kem
    private String lstAttachLabel;
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
    private Long fee;
    private Long feeAnnouce;
    private Long version;
    private Long lastIsTemp;
    private String strVersion;
    private Long isSignPdf;
    private Long haveTemp;
    private String receiveNo;
    private String nameStaffProcess;
    private Long staffProcess;
    private Long leaderAssignId;
    private String leaderAssignName;
    private Long leaderReviewId;
    private String leaderReviewName;
    private Date receivedDateTo;
    private Long leaderEvaluateId;
    private String leaderEvaluateName;
    private Long leaderApproveId;
    private String leaderApproveName;
    private Long is30;
    private Long ProductTypeNew;
    private Long searchTypeNew;
    private Long isDownload;//141212 binhnt53
    //141213
    private Long productTypeId;
    private String productTypeName;
    private String governingBody;
    //141216u binhnt53
    private Long isCourier;
    private String recipientAddress;
    private String telephone;
    private String annoucementNo;
    private String signDateNew;
    private String displayStatus;
    private String packageMaterial;
    private String objectUse;
    private Long index;
    private Integer flagSavePaging; // DuND - Ho tro luu trang tim kiem
    private String typeDatetime;//binhnt add loai group thoi gian ngay, tuan, thang, qui nam const
    private Date searchDateTo;//binhnt add ngay xu ly tu
    private Date searchDateFrom;//binhnt add ngay xu ly den
    private Long isHaveSubLabel;
    private Long processDeptId;
    private String processDeptName;
    private String searchFullText;
    private String origin;
    private String businessTelephone;
    private String businessFax;
    private Date receiptDate;
    private String sendDateNew;
    private String businessProvince;
    private String receiptNo;
    private String signerName;
    //hieptq update 190515
    private Long fileCodeCheck;
    private Long fileTypeNameCheck;
    private Long businessProvinceCheck;
    private Long displayStatusCheck;
    private Long businessNameCheck;
    private Long businessAddressCheck;
    private Long businessLicenceCheck;
    private Long announcementNoCheck;
    private Long productTypeNameCheck;
    private Long productNameCheck;
    private Long nationNameCheck;
    private Long manufactureNameCheck;
    private Long signerNameCheck;
    private Long nameStaffProcessCheck;
    //SDBS
    private Long filesSourceID;   // SDBS sau cong bo. Ho so goc
    private String fileSourceCode;      //Ma ho so goc 
    private String contentsEdit;    //Noi dung SDBS
    private String contentsEditATTP;    //Noi dung SDBS. Dung de xuat cong van
    private String noteEdit;       // Ghi chu SDBS
    private String titleEdit;       //Tieu de sua doi
    private String titleEditATTP;       //Tieu de sua doi. Dung đe xuat cong van
    //End SDBS
    
    public FilesForm() {
    }

    public FilesForm(Long fileId, String strVersion) {
        this.fileId = fileId;
        this.strVersion = strVersion;
    }

    public FilesForm(Files entity) {
        if (entity == null) {
            return;
        }
        fileId = entity.getFileId();
        fileType = entity.getFileType();
        fileTypeName = entity.getFileTypeName();
        fileCode = entity.getFileCode();
        createDate = entity.getCreateDate();
        modifyDate = entity.getModifyDate();
        deadline = entity.getDeadline();
        status = entity.getStatus();
        staffRequest = entity.getStaffRequest();
        leaderStaffRequest = entity.getLeaderStaffRequest();
        leaderRequest = entity.getLeaderRequest();
        displayRequest = entity.getDisplayRequest();
        reIssueFormId = entity.getReIssueFormId();
        announcementId = entity.getAnnouncementId();
        detailProductId = entity.getDetailProductId();
        testRegistrationId = entity.getTestRegistrationId();
        businessName = entity.getBusinessName();
        userCreateId = entity.getUserCreateId();
        userCreateName = entity.getUserCreateName();
        nodeId = entity.getNodeId();
        flowId = entity.getFlowId();
        previousVersion = entity.getPreviousVersion();
        deptId = entity.getDeptId();
        deptName = entity.getDeptName();
        approveDate = entity.getApproveDate();
        evaluateAddDate = entity.getEvaluateAddDate();
        evaluateOutAddDate = entity.getEvaluateOutAddDate();
        signingDate = entity.getSigningDate();
        staffSigningId = entity.getStaffSigningId();
        staffSigningName = entity.getStaffSigningName();
        signingContent = entity.getSignContent();
        signDate = entity.getSignDate();
        leaderStaffSignId = entity.getLeaderStaffSignId();
        signContent = entity.getSignContent();
        leaderStaffSignName = entity.getLeaderStaffSignName();

        announcementReceiptPaperId = entity.getAnnouncementReceiptPaperId();
        confirmAnnouncementPaperId = entity.getConfirmAnnouncementPaperId();
        confirmImportSatistPaperId = entity.getConfirmImportSatistPaperId();
        isActive = entity.getIsActive();
        effectiveDate = entity.getEffectiveDate();
        isFee = entity.getIsFee();
        signCa = entity.getSignCa();
        comparisonContent = entity.getComparisonContent();
        isComparison = entity.getIsComparison();
        contentSigned = entity.getContentSigned();
        userSigned = entity.getUserSigned();
        feeFile = entity.getFeeFile();
        clericalRequest = entity.getClericalRequest();
        isTemp = entity.getIsTemp();
        originalId = entity.getOriginalId();
        qrCode = entity.getQrCode();
        isTypeChange = entity.getIsTypeChange();
        lastType = entity.getLastType();
        deadlineAddition = entity.getDeadlineAddition();
        deadlineApprove = entity.getDeadlineApprove();
        deadlineComment = entity.getDeadlineComment();
        deadlineReceived = entity.getDeadlineReceived();
        version = entity.getVersion();
        lastIsTemp = entity.getLastIsTemp();
        isSignPdf = entity.getIsSignPdf();
        if (entity.getVersion() != null) {
            strVersion = "Lần sửa đổi " + version;
        } else {
            strVersion = "Không có lần sửa đổi";
        }
        haveTemp = entity.getHaveTemp();
        //
        agencyId = entity.getAgencyId();
        agencyName = entity.getAgencyName();
        receiveNo = entity.getReceiveNo();
        leaderAssignId = entity.getLeaderAssignId();
        leaderAssignName = entity.getLeaderAssignName();
        nameStaffProcess = entity.getNameStaffProcess();
        staffProcess = entity.getStaffProcess();
        leaderReviewId = entity.getLeaderReviewId();
        leaderReviewName = entity.getLeaderReviewName();
        leaderEvaluateId = entity.getLeaderEvaluateId();
        leaderEvaluateName = entity.getLeaderEvaluateName();
        leaderApproveId = entity.getLeaderApproveId();
        leaderApproveName = entity.getLeaderApproveName();
        is30 = entity.getIs30();
        isDownload = entity.getIsDownload();//n141212
        //n141213
        productTypeId = entity.getProductTypeId();
        productTypeName = entity.getProductTypeName();
        governingBody = entity.getGoverningBody();
        //141216u binhnt53
        isCourier = entity.getIsCourier();
        recipientAddress = entity.getRecipientAddress();
        //150120u binhnt53
        displayStatus = entity.getDisplayStatus();
        isHaveSubLabel = entity.getIsHaveSubLabel();
        //hiepvv SDBS sau cong bo
        filesSourceID = entity.getFilesSourceID();
        fileSourceCode = entity.getFileSourceCode();
        contentsEdit = entity.getContentsEdit();
        contentsEditATTP = entity.getContentsEditATTP();
        noteEdit = entity.getNoteEdit();
        titleEdit = entity.getTitleEdit();
        titleEditATTP = entity.getTitleEditATTP();
    }

    public Files convertToEntity() {
        Files entity = new Files();
        entity.setFileId(fileId);
        entity.setFileType(fileType);
        entity.setFileTypeName(fileTypeName);
        entity.setFileCode(fileCode);
        entity.setCreateDate(createDate);
        entity.setModifyDate(modifyDate);
        entity.setDeadline(deadline);
        entity.setStatus(status);
        entity.setStaffRequest(staffRequest);
        entity.setLeaderStaffRequest(leaderStaffRequest);
        entity.setLeaderRequest(leaderRequest);
        entity.setDisplayRequest(displayRequest);
        entity.setReIssueFormId(reIssueFormId);
        entity.setAnnouncementId(announcementId);
        entity.setDetailProductId(detailProductId);
        entity.setTestRegistrationId(testRegistrationId);
        entity.setBusinessName(businessName);
        entity.setUserCreateId(userCreateId);
        entity.setUserCreateName(userCreateName);
        entity.setNodeId(nodeId);
        entity.setFlowId(flowId);
        entity.setPreviousVersion(previousVersion);
        entity.setDeptId(deptId);
        entity.setDeptName(deptName);
        entity.setApproveDate(approveDate);
        entity.setEvaluateAddDate(evaluateAddDate);
        entity.setSigningDate(signingDate);
        entity.setStaffSigningId(staffSigningId);
        entity.setStaffSigningName(staffSigningName);
        entity.setSigningContent(signingContent);
        entity.setSignDate(signDate);
        entity.setLeaderStaffSignId(leaderStaffSignId);
        entity.setSignContent(signContent);
        entity.setLeaderStaffSignName(leaderStaffSignName);
        entity.setEffectiveDate(effectiveDate);
        if (announcement != null) {
            entity.setBusinessName(announcement.getBusinessName());
            entity.setBusinessLicence(announcement.getBusinessLicence());
            entity.setAnnouncementNo(announcement.getAnnouncementNo());
            entity.setBusinessAddress(announcement.getBusinessAddress());
            entity.setProductName(announcement.getProductName());
            entity.setNationName(announcement.getNationName());
            entity.setManufactureName(announcement.getManufactureName());
            entity.setManufactureAddress(announcement.getManufactureAddress());
            entity.setMatchingTarget(announcement.getMatchingTarget());
        }
        if (detailProduct != null) {
            entity.setProductTypeId(detailProduct.getProductType());
            entity.setProductTypeName(detailProduct.getProductTypeName());
        }

        entity.setAnnouncementReceiptPaperId(announcementReceiptPaperId);
        entity.setConfirmAnnouncementPaperId(confirmAnnouncementPaperId);
        entity.setConfirmImportSatistPaperId(confirmImportSatistPaperId);

        if (isActive == null) {
            isActive = 1l;
        }
        entity.setIsActive(isActive);
        entity.setIsFee(isFee);
        entity.setSignCa(signCa);
        entity.setComparisonContent(comparisonContent);
        entity.setIsComparison(isComparison);
        entity.setFeeFile(feeFile);
        entity.setClericalRequest(clericalRequest);
        entity.setIsTemp(isTemp);
        entity.setOriginalId(originalId);
        entity.setDeadlineAddition(deadlineAddition);
        entity.setDeadlineApprove(deadlineApprove);
        entity.setDeadlineReceived(deadlineReceived);
        entity.setDeadlineComment(deadlineComment);
        entity.setIsSignPdf(isSignPdf);
        entity.setHaveTemp(haveTemp);
        //
        entity.setAgencyId(agencyId);
        entity.setAgencyName(agencyName);
        //
        entity.setReceiveNo(receiveNo);
        entity.setLeaderAssignId(leaderAssignId);
        entity.setLeaderAssignName(leaderAssignName);
        entity.setNameStaffProcess(nameStaffProcess);
        entity.setStaffProcess(staffProcess);
        entity.setLeaderReviewId(leaderReviewId);
        entity.setLeaderReviewName(leaderReviewName);
        entity.setLeaderEvaluateId(leaderEvaluateId);
        entity.setLeaderEvaluateName(leaderEvaluateName);
        entity.setLeaderApproveId(leaderApproveId);
        entity.setLeaderApproveName(leaderApproveName);
        entity.setIs30(is30);
        entity.setIsDownload(isDownload);//n141212
        //n141213
        entity.setGoverningBody(governingBody);
        //141216u binhnt53
        entity.setIsCourier(isCourier);
        entity.setRecipientAddress(recipientAddress);
        entity.setVersion(version);
        entity.setIsHaveSubLabel(isHaveSubLabel);
        //hiepvv edit after announced
        if(filesSourceID!=null){
            entity.setFilesSourceID(filesSourceID);
            entity.setFileSourceCode(fileSourceCode);
            entity.setContentsEdit(contentsEdit);
            entity.setContentsEditATTP(contentsEditATTP);
            entity.setNoteEdit(noteEdit);
            entity.setTitleEdit(titleEdit);
            entity.setTitleEditATTP(titleEditATTP);
//            entity.setUserSigned("fileUploaded");
            entity.setIsFee(1L);
//            entity.setIsSignPdf(1L);
        }
        
        return entity;
    }

    public Files updateToEntity(Files entity) {
        if (entity == null) {
            return entity;
        }
        if (announcement != null) {
            entity.setBusinessName(announcement.getBusinessName());
            entity.setDeptName(announcement.getBusinessName());//binhnt53 141121
            entity.setBusinessLicence(announcement.getBusinessLicence());
            entity.setAnnouncementNo(announcement.getAnnouncementNo());
            entity.setBusinessAddress(announcement.getBusinessAddress());
            entity.setProductName(announcement.getProductName());
            entity.setNationName(announcement.getNationName());
            entity.setManufactureName(announcement.getManufactureName());
            entity.setManufactureAddress(announcement.getManufactureAddress());
            entity.setMatchingTarget(announcement.getMatchingTarget());
        }
        if (detailProduct != null) {//150204 binhnt add
            entity.setProductTypeId(detailProduct.getProductType());
            entity.setProductTypeName(detailProduct.getProductTypeName());
        }//!150204 binhnt add
        entity.setModifyDate(modifyDate);
        if (this.getFileType() != null && this.getFileType() > 0L) {
            entity.setFileType(this.getFileType());
            entity.setFileTypeName(this.getFileTypeName());
        }
        if (this.getIsHaveSubLabel() != null) {
            entity.setIsHaveSubLabel(this.getIsHaveSubLabel());
        }
        //Hiepvv Sua doi bo sung sau cong bo
        if(filesSourceID!=null){
            entity.setFilesSourceID(filesSourceID);
            entity.setFileSourceCode(fileSourceCode);
            entity.setContentsEdit(contentsEdit);
            entity.setContentsEditATTP(contentsEditATTP);
            entity.setNoteEdit(noteEdit);
            entity.setTitleEdit(titleEdit);
            entity.setTitleEditATTP(titleEditATTP);
//            entity.setUserSigned("fileUploaded");
            entity.setIsFee(1L);
//            entity.setIsSignPdf(1L);
        }
        return entity;
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

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
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

    public FilesEditForm getFilesEditForm() {
        return filesEditForm;
    }

    public void setFilesEditForm(FilesEditForm filesEditForm) {
        this.filesEditForm = filesEditForm;
    }

    public Long getFilesSourceID() {
        return filesSourceID;
    }

    public void setFilesSourceID(Long filesSourceID) {
        this.filesSourceID = filesSourceID;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

    public Date getEvaluateAddDate() {
        return evaluateAddDate;
    }

    public void setEvaluateAddDate(Date evaluateAddDate) {
        this.evaluateAddDate = evaluateAddDate;
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

    public Long getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Long announcementId) {
        this.announcementId = announcementId;
    }

    public Long getDetailProductId() {
        return detailProductId;
    }

    public void setDetailProductId(Long detailProductId) {
        this.detailProductId = detailProductId;
    }

    public Long getTestRegistrationId() {
        return testRegistrationId;
    }

    public void setTestRegistrationId(Long testRegistrationId) {
        this.testRegistrationId = testRegistrationId;
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

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public AnnouncementForm getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(AnnouncementForm announcement) {
        this.announcement = announcement;
    }

    public DetailProductForm getDetailProduct() {
        return detailProduct;
    }

    public void setDetailProduct(DetailProductForm detailProduct) {
        this.detailProduct = detailProduct;
    }

    public ReIssueFormForm getReIssueForm() {
        return reIssueForm;
    }

    public void setReIssueForm(ReIssueFormForm reIssueForm) {
        this.reIssueForm = reIssueForm;
    }

    public TestRegistrationForm getTestRegistration() {
        return testRegistration;
    }

    public void setTestRegistration(TestRegistrationForm testRegistration) {
        this.testRegistration = testRegistration;
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

    public List<ProductTarget> getLstInfectedTarget() {
        return lstInfectedTarget;
    }

    public void setLstInfectedTarget(List<ProductTarget> lstInfectedTarget) {
        this.lstInfectedTarget = lstInfectedTarget;
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

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
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

    public Long getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Long effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public EvaluationRecordsForm getEvaluationRecordsForm() {
        return evaluationRecordsForm;
    }

    public void setEvaluationRecordsForm(EvaluationRecordsForm evaluationRecordsForm) {
        this.evaluationRecordsForm = evaluationRecordsForm;
    }

    public Long getViewType() {
        return viewType;
    }

    public void setViewType(Long viewType) {
        this.viewType = viewType;
    }

    public Long getIsFee() {
        return isFee;
    }

    public void setIsFee(Long isFee) {
        this.isFee = isFee;
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

    public String getContentXml() {
        return contentXml;
    }

    public void setContentXml(String contentXml) {
        this.contentXml = contentXml;
    }

    public String getSignCa() {
        return signCa;
    }

    public void setSignCa(String signCa) {
        this.signCa = signCa;
    }

    public Long getFeeFile() {
        return feeFile;
    }

    public void setFeeFile(Long feeFile) {
        this.feeFile = feeFile;
    }

    public Long getSearchType() {
        return searchType;
    }

    public void setSearchType(Long searchType) {
        this.searchType = searchType;
    }

    public Date getEvaluateOutAddDate() {
        return evaluateOutAddDate;
    }

    public void setEvaluateOutAddDate(Date evaluateOutAddDate) {
        this.evaluateOutAddDate = evaluateOutAddDate;
    }

    public Date getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(Date signedDate) {
        this.signedDate = signedDate;
    }

    public String getClericalRequest() {
        return clericalRequest;
    }

    public void setClericalRequest(String clericalRequest) {
        this.clericalRequest = clericalRequest;
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

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
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

    // le phi ho so - hieptq
    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getFeeAnnouce() {
        return feeAnnouce;
    }

    public void setFeeAnnouce(Long feeAnnouce) {
        this.feeAnnouce = feeAnnouce;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getLastIsTemp() {
        return lastIsTemp;
    }

    public void setLastIsTemp(Long lastIsTemp) {
        this.lastIsTemp = lastIsTemp;
    }

    public String getStrVersion() {
        return strVersion;
    }

    public void setStrVersion(String strVersion) {
        this.strVersion = strVersion;
    }

    public Long getHaveTemp() {
        return haveTemp;
    }

    public void setHaveTemp(Long haveTemp) {
        this.haveTemp = haveTemp;
    }

    public Long getIsSignPdf() {
        return isSignPdf;
    }

    public void setIsSignPdf(Long isSignPdf) {
        this.isSignPdf = isSignPdf;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public RequestCommentForm getRequestCommentForm() {
        return requestCommentForm;
    }

    public void setRequestCommentForm(RequestCommentForm requestCommentForm) {
        this.requestCommentForm = requestCommentForm;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getNameStaffProcess() {
        return nameStaffProcess;
    }

    public void setNameStaffProcess(String nameStaffProcess) {
        this.nameStaffProcess = nameStaffProcess;
    }

    public Long getLeaderAssignId() {
        return leaderAssignId;
    }

    public void setLeaderAssignId(Long leaderAssignId) {
        this.leaderAssignId = leaderAssignId;
    }

    public String getLeaderAssignName() {
        return leaderAssignName;
    }

    public void setLeaderAssignName(String leaderAssignName) {
        this.leaderAssignName = leaderAssignName;
    }

    public Long getStaffProcess() {
        return staffProcess;
    }

    public void setStaffProcess(Long staffProcess) {
        this.staffProcess = staffProcess;
    }

    public Long getLeaderReviewId() {
        return leaderReviewId;
    }

    public void setLeaderReviewId(Long leaderReviewId) {
        this.leaderReviewId = leaderReviewId;
    }

    public String getLeaderReviewName() {
        return leaderReviewName;
    }

    public void setLeaderReviewName(String leaderReviewName) {
        this.leaderReviewName = leaderReviewName;
    }

    public Date getReceivedDateTo() {
        return receivedDateTo;
    }

    public void setReceivedDateTo(Date receivedDateTo) {
        this.receivedDateTo = receivedDateTo;
    }

    public Long getLeaderEvaluateId() {
        return leaderEvaluateId;
    }

    public void setLeaderEvaluateId(Long leaderEvaluateId) {
        this.leaderEvaluateId = leaderEvaluateId;
    }

    public String getLeaderEvaluateName() {
        return leaderEvaluateName;
    }

    public void setLeaderEvaluateName(String leaderEvaluateName) {
        this.leaderEvaluateName = leaderEvaluateName;
    }

    public Long getLeaderApproveId() {
        return leaderApproveId;
    }

    public void setLeaderApproveId(Long leaderApproveId) {
        this.leaderApproveId = leaderApproveId;
    }

    public String getLeaderApproveName() {
        return leaderApproveName;
    }

    public void setLeaderApproveName(String leaderApproveName) {
        this.leaderApproveName = leaderApproveName;
    }

    public String getLstAttachLabel() {
        return lstAttachLabel;
    }

    public void setLstAttachLabel(String lstAttachLabel) {
        this.lstAttachLabel = lstAttachLabel;
    }

    public EvaluationRecordsFormOnGrid getEvaluationRecordsFormOnGrid() {
        return evaluationRecordsFormOnGrid;
    }

    public void setEvaluationRecordsFormOnGrid(EvaluationRecordsFormOnGrid evaluationRecordsFormOnGrid) {
        this.evaluationRecordsFormOnGrid = evaluationRecordsFormOnGrid;
    }

    public Long getIs30() {
        return is30;
    }

    public void setIs30(Long is30) {
        this.is30 = is30;
    }

    public Long getProductTypeNew() {
        return ProductTypeNew;
    }

    public void setProductTypeNew(Long ProductTypeNew) {
        this.ProductTypeNew = ProductTypeNew;
    }

    public Long getSearchTypeNew() {
        return searchTypeNew;
    }

    public void setSearchTypeNew(Long searchTypeNew) {
        this.searchTypeNew = searchTypeNew;
    }
    //141212 binhnt53

    public Long getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(Long isDownload) {
        this.isDownload = isDownload;
    }
    //n141213

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getGoverningBody() {
        return governingBody;
    }

    public void setGoverningBody(String governingBody) {
        this.governingBody = governingBody;
    }
//141216u binhnt53

    public Long getIsCourier() {
        return isCourier;
    }

    public void setIsCourier(Long isCourier) {
        this.isCourier = isCourier;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAnnoucementNo() {
        return annoucementNo;
    }

    public void setAnnoucementNo(String annoucementNo) {
        this.annoucementNo = annoucementNo;
    }

    public String getSignDateNew() {
        return signDateNew;
    }

    public void setSignDateNew(String signDateNew) {
        this.signDateNew = signDateNew;
    }

    public String getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(String displayStatus) {
        this.displayStatus = displayStatus;
    }

    public String getPackageMaterial() {
        return packageMaterial;
    }

    public void setPackageMaterial(String packageMaterial) {
        this.packageMaterial = packageMaterial;
    }

    public String getObjectUse() {
        return objectUse;
    }

    public void setObjectUse(String objectUse) {
        this.objectUse = objectUse;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public Integer getFlagSavePaging() {
        return flagSavePaging;
    }

    public void setFlagSavePaging(Integer flagSavePaging) {
        this.flagSavePaging = flagSavePaging;
    }

    public Date getSignDateFrom() {
        return signDateFrom;
    }

    public void setSignDateFrom(Date signDateFrom) {
        this.signDateFrom = signDateFrom;
    }

    public Date getSignDateTo() {
        return signDateTo;
    }

    public void setSignDateTo(Date signDateTo) {
        this.signDateTo = signDateTo;
    }

    public Date getSearchDateTo() {
        return searchDateTo;
    }

    public void setSearchDateTo(Date searchDateTo) {
        this.searchDateTo = searchDateTo;
    }

    public Date getSearchDateFrom() {
        return searchDateFrom;
    }

    public void setSearchDateFrom(Date searchDateFrom) {
        this.searchDateFrom = searchDateFrom;
    }

    public String getTypeDatetime() {
        return typeDatetime;
    }

    public void setTypeDatetime(String typeDatetime) {
        this.typeDatetime = typeDatetime;
    }

    public Long getIsHaveSubLabel() {
        return isHaveSubLabel;
    }

    public void setIsHaveSubLabel(Long isHaveSubLabel) {
        this.isHaveSubLabel = isHaveSubLabel;
    }

    public Long getProcessDeptId() {
        return processDeptId;
    }

    public void setProcessDeptId(Long processDeptId) {
        this.processDeptId = processDeptId;
    }

    public String getProcessDeptName() {
        return processDeptName;
    }

    public void setProcessDeptName(String processDeptName) {
        this.processDeptName = processDeptName;
    }

    public String getSearchFullText() {
        return searchFullText;
    }

    public void setSearchFullText(String searchFullText) {
        this.searchFullText = searchFullText;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getBusinessTelephone() {
        return businessTelephone;
    }

    public void setBusinessTelephone(String businessTelephone) {
        this.businessTelephone = businessTelephone;
    }

    public String getBusinessFax() {
        return businessFax;
    }

    public void setBusinessFax(String businessFax) {
        this.businessFax = businessFax;
    }
    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getSendDateNew() {
        return sendDateNew;
    }

    public void setSendDateNew(String sendDateNew) {
        this.sendDateNew = sendDateNew;
    }

    public String getBusinessProvince() {
        return businessProvince;
    }

    public void setBusinessProvince(String businessProvince) {
        this.businessProvince = businessProvince;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getSignerName() {
        return signerName;
    }

    public void setSignerName(String signerName) {
        this.signerName = signerName;
    }

    public Long getFileCodeCheck() {
        return fileCodeCheck;
    }

    public void setFileCodeCheck(Long fileCodeCheck) {
        this.fileCodeCheck = fileCodeCheck;
    }

    public Long getFileTypeNameCheck() {
        return fileTypeNameCheck;
    }

    public void setFileTypeNameCheck(Long fileTypeNameCheck) {
        this.fileTypeNameCheck = fileTypeNameCheck;
    }

    public Long getBusinessProvinceCheck() {
        return businessProvinceCheck;
    }

    public void setBusinessProvinceCheck(Long businessProvinceCheck) {
        this.businessProvinceCheck = businessProvinceCheck;
    }

    public Long getDisplayStatusCheck() {
        return displayStatusCheck;
    }

    public void setDisplayStatusCheck(Long displayStatusCheck) {
        this.displayStatusCheck = displayStatusCheck;
    }

    public Long getBusinessNameCheck() {
        return businessNameCheck;
    }

    public void setBusinessNameCheck(Long businessNameCheck) {
        this.businessNameCheck = businessNameCheck;
    }

    public Long getBusinessAddressCheck() {
        return businessAddressCheck;
    }

    public void setBusinessAddressCheck(Long businessAddressCheck) {
        this.businessAddressCheck = businessAddressCheck;
    }

    public Long getBusinessLicenceCheck() {
        return businessLicenceCheck;
    }

    public void setBusinessLicenceCheck(Long businessLicenceCheck) {
        this.businessLicenceCheck = businessLicenceCheck;
    }

    public Long getAnnouncementNoCheck() {
        return announcementNoCheck;
    }

    public void setAnnouncementNoCheck(Long announcementNoCheck) {
        this.announcementNoCheck = announcementNoCheck;
    }

    public Long getProductTypeNameCheck() {
        return productTypeNameCheck;
    }

    public void setProductTypeNameCheck(Long productTypeNameCheck) {
        this.productTypeNameCheck = productTypeNameCheck;
    }

    public Long getProductNameCheck() {
        return productNameCheck;
    }

    public void setProductNameCheck(Long productNameCheck) {
        this.productNameCheck = productNameCheck;
    }

    public Long getNationNameCheck() {
        return nationNameCheck;
    }

    public void setNationNameCheck(Long nationNameCheck) {
        this.nationNameCheck = nationNameCheck;
    }

    public Long getManufactureNameCheck() {
        return manufactureNameCheck;
    }

    public void setManufactureNameCheck(Long manufactureNameCheck) {
        this.manufactureNameCheck = manufactureNameCheck;
    }

    public Long getSignerNameCheck() {
        return signerNameCheck;
    }

    public void setSignerNameCheck(Long signerNameCheck) {
        this.signerNameCheck = signerNameCheck;
    }

    public Long getNameStaffProcessCheck() {
        return nameStaffProcessCheck;
    }

    public void setNameStaffProcessCheck(Long nameStaffProcessCheck) {
        this.nameStaffProcessCheck = nameStaffProcessCheck;
    }
    
    public String getFileSourceCode() {
        return fileSourceCode;
    }

    public void setFileSourceCode(String fileSourceCode) {
        this.fileSourceCode = fileSourceCode;
    }

    public String getContentsEdit() {
        return contentsEdit;
    }

    public void setContentsEdit(String contentsEdit) {
        this.contentsEdit = contentsEdit;
    }

    public String getNoteEdit() {
        return noteEdit;
    }

    public void setNoteEdit(String noteEdit) {
        this.noteEdit = noteEdit;
    }

    public String getTitleEdit() {
        return titleEdit;
    }

    public void setTitleEdit(String titleEdit) {
        this.titleEdit = titleEdit;
    }

    public String getContentsEditATTP() {
        return contentsEditATTP;
    }

    public void setContentsEditATTP(String contentsEditATTP) {
        this.contentsEditATTP = contentsEditATTP;
    }

    public String getTitleEditATTP() {
        return titleEditATTP;
    }

    public void setTitleEditATTP(String titleEditATTP) {
        this.titleEditATTP = titleEditATTP;
    }
    
    
}
