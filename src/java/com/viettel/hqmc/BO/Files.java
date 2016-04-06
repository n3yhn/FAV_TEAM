package com.viettel.hqmc.BO;

import com.viettel.hqmc.DAOHE.FilesDAOHE;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vtit_havm2, binhnt53 update
 */
@Entity
@Table(name = "FILES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Files.findAll", query = "SELECT f FROM Files f"),
    @NamedQuery(name = "Files.findByFileId", query = "SELECT f FROM Files f WHERE f.fileId = :fileId"),
    @NamedQuery(name = "Files.findByFileType", query = "SELECT f FROM Files f WHERE f.fileType = :fileType"),
    @NamedQuery(name = "Files.findByFileTypeName", query = "SELECT f FROM Files f WHERE f.fileTypeName = :fileTypeName"),
    @NamedQuery(name = "Files.findByFileCode", query = "SELECT f FROM Files f WHERE f.fileCode = :fileCode"),
    @NamedQuery(name = "Files.findByCreateDate", query = "SELECT f FROM Files f WHERE f.createDate = :createDate"),
    @NamedQuery(name = "Files.findByModifyDate", query = "SELECT f FROM Files f WHERE f.modifyDate = :modifyDate"),
    @NamedQuery(name = "Files.findBySendDate", query = "SELECT f FROM Files f WHERE f.sendDate = :sendDate"),
    @NamedQuery(name = "Files.findByStatus", query = "SELECT f FROM Files f WHERE f.status = :status"),
    @NamedQuery(name = "Files.findByStaffRequest", query = "SELECT f FROM Files f WHERE f.staffRequest = :staffRequest"),
    @NamedQuery(name = "Files.findByLeaderStaffRequest", query = "SELECT f FROM Files f WHERE f.leaderStaffRequest = :leaderStaffRequest"),
    @NamedQuery(name = "Files.findByLeaderRequest", query = "SELECT f FROM Files f WHERE f.leaderRequest = :leaderRequest"),
    @NamedQuery(name = "Files.findByDisplayRequest", query = "SELECT f FROM Files f WHERE f.displayRequest = :displayRequest"),
    @NamedQuery(name = "Files.findByReIssueFormId", query = "SELECT f FROM Files f WHERE f.reIssueFormId = :reIssueFormId"),
    @NamedQuery(name = "Files.findByAnnouncementId", query = "SELECT f FROM Files f WHERE f.announcementId = :announcementId"),
    @NamedQuery(name = "Files.findByDetailProductId", query = "SELECT f FROM Files f WHERE f.detailProductId = :detailProductId"),
    @NamedQuery(name = "Files.findByTestRegistrationId", query = "SELECT f FROM Files f WHERE f.testRegistrationId = :testRegistrationId"),
    @NamedQuery(name = "Files.findByBusinessName", query = "SELECT f FROM Files f WHERE f.businessName = :businessName"),
    @NamedQuery(name = "Files.findByUserCreateId", query = "SELECT f FROM Files f WHERE f.userCreateId = :userCreateId"),
    @NamedQuery(name = "Files.findByUserCreateName", query = "SELECT f FROM Files f WHERE f.userCreateName = :userCreateName"),
    @NamedQuery(name = "Files.findByNodeId", query = "SELECT f FROM Files f WHERE f.nodeId = :nodeId"),
    @NamedQuery(name = "Files.findByFlowId", query = "SELECT f FROM Files f WHERE f.flowId = :flowId"),
    @NamedQuery(name = "Files.findByPreviousVersion", query = "SELECT f FROM Files f WHERE f.previousVersion = :previousVersion"),
    @NamedQuery(name = "Files.findByIsActive", query = "SELECT f FROM Files f WHERE f.isActive = :isActive")})
//@NamedNativeQueries({
//    @NamedNativeQuery(	name = "callUpdateFileStatus", query = "CALL CALLUPDATEFILESTATUS(:fileId)")
//})
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "FILES_SEQ", sequenceName = "FILES_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILES_SEQ")
    @Basic(optional = false)
    @Column(name = "FILE_ID")
    private Long fileId;
    @Column(name = "FILE_TYPE")
    private Long fileType;
    @Column(name = "FILE_TYPE_NAME")
    private String fileTypeName;
    @Column(name = "FILE_CODE")
    private String fileCode;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    @Column(name = "SEND_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;
    @Column(name = "DEADLINE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "STAFF_REQUEST")
    private String staffRequest;
    @Column(name = "LEADER_STAFF_REQUEST")
    private String leaderStaffRequest;
    @Column(name = "LEADER_REQUEST")
    private String leaderRequest;
    @Column(name = "DISPLAY_REQUEST")
    private String displayRequest;
    @Column(name = "RE_ISSUE_FORM_ID")
    private Long reIssueFormId;
    @Column(name = "ANNOUNCEMENT_ID")
    private Long announcementId;
//    @Basic(optional = false)
    @Column(name = "DETAIL_PRODUCT_ID")
    private Long detailProductId;
    @Column(name = "TEST_REGISTRATION_ID")
    private Long testRegistrationId;
    @Column(name = "BUSINESS_NAME")
    private String businessName;
    @Column(name = "USER_CREATE_ID")
    private Long userCreateId;
    @Column(name = "USER_CREATE_NAME")
    private String userCreateName;
    @Column(name = "NODE_ID")
    private Long nodeId;
    @Column(name = "PREVIOUS_NODE_ID")
    private Long previousNodeId;
    @Column(name = "FLOW_ID")
    private Long flowId;
    @Column(name = "PREVIOUS_VERSION")
    private Long previousVersion;
    @Column(name = "DEPT_ID")
    private Long deptId;
    @Column(name = "DEPT_NAME")
    private String deptName;
    @Column(name = "ANNOUNCEMENT_NO")
    private String announcementNo;
    @Column(name = "BUSINESS_LICENCE")
    private String businessLicence;
    @Column(name = "BUSINESS_ADDRESS")
    private String businessAddress;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "MANUFACTURE_NAME")
    private String manufactureName;
    @Column(name = "MANUFACTURE_ADDRESS")
    private String manufactureAddress;
    @Column(name = "MATCHING_TARGET")
    private String matchingTarget;
    @Column(name = "NATION_NAME")
    private String nationName;
    @Column(name = "ANNOUNCEMENT_RECEIPT_PAPER_ID")
    private Long announcementReceiptPaperId;
    @Column(name = "CONFIRM_ANNOUNCEMENT_PAPER_ID")
    private Long confirmAnnouncementPaperId;
    @Column(name = "CONFIRM_IMPORT_SATIST_PAPER_ID")
    private Long confirmImportSatistPaperId;
    @Column(name = "AGENCY_ID")
    private Long agencyId;
    @Column(name = "AGENCY_NAME")
    private String agencyName;
    @Column(name = "APPROVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approveDate;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "SIGNING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date signingDate;
    @Column(name = "STAFF_SIGNING_ID")
    private Long staffSigningId;
    @Column(name = "STAFF_SIGNING_NAME")
    private String staffSigningName;
    @Column(name = "SIGNING_CONTENT")
    private String signingContent;
    @Column(name = "SIGN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date signDate;
    @Column(name = "LEADER_STAFF_SIGN_ID")
    private Long leaderStaffSignId;
    @Column(name = "SIGN_CONTENT")
    private String signContent;
    @Column(name = "LEADER_STAFF_SIGN_NAME")
    private String leaderStaffSignName;
    @Column(name = "DISPLAY_STATUS")
    private String displayStatus;
    @Column(name = "NODE_HISTORY")
    private String nodeHistory;
    @Column(name = "EFFECTIVE_DATE")
    private Long effectiveDate;
    @Column(name = "IS_FEE")
    private Long isFee;
    @Column(name = "SIGN_CA")
    private String signCa;
    @Column(name = "CONTENT_SIGNED")
    private String contentSigned;
    @Column(name = "USER_SIGNED")
    private String userSigned;
    @Column(name = "IS_COMPARISON")
    private Long isComparison;
    @Column(name = "COMPARISON_CONTENT")
    private String comparisonContent;
    @Column(name = "FEE_FILE")
    private Long feeFile;
    @Column(name = "EVALUATE_ADD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date evaluateAddDate;
    @Column(name = "EVALUATE_OUT_ADD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date evaluateOutAddDate;
    @Column(name = "CLERICAL_REQUEST")
    private String clericalRequest;
    @Column(name = "IS_TEMP")
    private Long isTemp;
    @Column(name = "ORIGINAL_ID")
    private Long originalId;
    @Column(name = "REP_ID")
    private Long repositoriesId;
    @Column(name = "QR_CODE")
    private byte[] qrCode;
    @Column(name = "IS_TYPE_CHANGE")
    private Long isTypeChange;
    @Column(name = "LAST_TYPE")
    private Long lastType;
    @Column(name = "REP_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date repDate;
    @Column(name = "RECEIVED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receivedDate;

    @Column(name = "DEADLINE_RECEIVED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadlineReceived;
    @Column(name = "DEADLINE_COMMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadlineComment;
    @Column(name = "DEADLINE_ADDITION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadlineAddition;
    @Column(name = "DEADLINE_APPROVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadlineApprove;
    @Column(name = "VERSION")
    private Long version;
    @Column(name = "LAST_IS_TEMP")
    private Long lastIsTemp;
    @Column(name = "HAVE_TEMP")
    private Long haveTemp;
    @Column(name = "IS_SIGN_PDF")
    private Long isSignPdf;
    @Column(name = "STAFF_PROCESS")
    private Long staffProcess;
    @Column(name = "RECEIVE_NO")
    private String receiveNo;
    @Column(name = "NAME_STAFF_PROCESS")
    private String nameStaffProcess;
    @Column(name = "LEADER_ASSIGN_ID")
    private Long leaderAssignId;
    @Column(name = "LEADER_ASSIGN_NAME")
    private String leaderAssignName;
    @Column(name = "LEADER_REVIEW_ID")
    private Long leaderReviewId;
    @Column(name = "LEADER_REVIEW_NAME")
    private String leaderReviewName;
    @Column(name = "LEADER_EVALUATE_ID")
    private Long leaderEvaluateId;
    @Column(name = "LEADER_EVALUATE_NAME")
    private String leaderEvaluateName;
    @Column(name = "LEADER_APPROVE_ID")
    private Long leaderApproveId;
    @Column(name = "LEADER_APPROVE_NAME")
    private String leaderApproveName;
    @Column(name = "IS_30")
    private Long is30;
    @Column(name = "IS_DOWNLOAD")
    private Long isDownload;
    @Column(name = "PRODUCT_TYPE_ID")
    private Long productTypeId;
    @Column(name = "PRODUCT_TYPE_NAME")
    private String productTypeName;
    @Column(name = "GOVERNING_BODY")
    private String governingBody;
    @Column(name = "IS_COURIER")
    private Long isCourier;
    @Column(name = "RECIPIENT_ADDRESS")
    private String recipientAddress;
    @Column(name = "IS_HAVE_SUB_LABEL")
    private Long isHaveSubLabel;
    //SDBS sau cong bo
    @Column(name = "FILES_SOURCE_ID")
    private Long filesSourceID;
    @Column(name = "CONTENTS_EDIT")
    private String contentsEdit;
    @Column(name = "CONTENTS_EDIT_ATTP")
    private String contentsEditATTP;
    @Column(name = "NOTE_EDIT")
    private String noteEdit;
    @Column(name = "FILE_SOURCE_CODE")
    private String fileSourceCode;
    @Column(name = "TITLE_EDIT")
    private String titleEdit;
    @Column(name = "TITLE_EDIT_ATTP")
    private String titleEditATTP;
    @Column(name = "IS_COPY")
    private Long isCopy;
    //end
    @Transient
    String proposeUserName;
    @Transient
    Long processType;

    public Files() {
    }

    public Files(Long fileId) {
        this.fileId = fileId;
    }

    public Files(Long fileId, Long reIssueFormId, Long announcementId, Long detailProductId, Long testRegistrationId, Long previousVersion) {
        this.fileId = fileId;
        this.reIssueFormId = reIssueFormId;
        this.announcementId = announcementId;
        this.detailProductId = detailProductId;
        this.testRegistrationId = testRegistrationId;
        this.previousVersion = previousVersion;
    }

    public Files cloneEntity(Files originalFiles) {
        Files entity = new Files();
//        entity.setFileId(originalFiles.getFileId());
        entity.setFileType(originalFiles.getFileType());
        entity.setFileTypeName(originalFiles.getFileTypeName());
        entity.setFileCode(originalFiles.getFileCode());
        entity.setCreateDate(originalFiles.getCreateDate());
        entity.setModifyDate(originalFiles.getModifyDate());
        entity.setSendDate(originalFiles.getSendDate());
        entity.setDeadline(originalFiles.getDeadline());
        entity.setStatus(originalFiles.getStatus());
        entity.setStaffRequest(originalFiles.getStaffRequest());
        entity.setLeaderStaffRequest(originalFiles.getLeaderStaffRequest());
        entity.setLeaderRequest(originalFiles.getLeaderRequest());
        entity.setDisplayRequest(originalFiles.getDisplayRequest());
        entity.setReIssueFormId(originalFiles.getReIssueFormId());
        entity.setAnnouncementId(originalFiles.getAnnouncementId());
        entity.setDetailProductId(originalFiles.getDetailProductId());
        entity.setTestRegistrationId(originalFiles.getTestRegistrationId());
        entity.setBusinessName(originalFiles.getBusinessName());
        entity.setUserCreateId(originalFiles.getUserCreateId());
        entity.setUserCreateName(originalFiles.getUserCreateName());
        entity.setNodeId(originalFiles.getNodeId());
        entity.setPreviousNodeId(originalFiles.getPreviousNodeId());
        entity.setFlowId(originalFiles.getFlowId());
        entity.setPreviousVersion(originalFiles.getPreviousVersion());
        entity.setDeptId(originalFiles.getDeptId());
        entity.setDeptName(originalFiles.getDeptName());
        entity.setAnnouncementNo(originalFiles.getAnnouncementNo());
        entity.setBusinessLicence(originalFiles.getBusinessLicence());
        entity.setBusinessAddress(originalFiles.getBusinessAddress());
        entity.setProductName(originalFiles.getProductName());
        entity.setManufactureName(originalFiles.getManufactureName());
        entity.setManufactureAddress(originalFiles.getManufactureAddress());
        entity.setMatchingTarget(originalFiles.getMatchingTarget());
        entity.setNationName(originalFiles.getNationName());
        entity.setAnnouncementReceiptPaperId(originalFiles.getAnnouncementReceiptPaperId());
        entity.setConfirmAnnouncementPaperId(originalFiles.getConfirmAnnouncementPaperId());
        entity.setConfirmImportSatistPaperId(originalFiles.getConfirmImportSatistPaperId());
        entity.setAgencyId(originalFiles.getAgencyId());
        entity.setAgencyName(originalFiles.getAgencyName());
        entity.setApproveDate(originalFiles.getApproveDate());
        entity.setIsActive(originalFiles.getIsActive());
        entity.setSigningDate(originalFiles.getSigningDate());
        entity.setStaffSigningId(originalFiles.getStaffSigningId());
        entity.setStaffSigningName(originalFiles.getStaffSigningName());
        entity.setSigningContent(originalFiles.getSigningContent());
        entity.setSignDate(originalFiles.getSignDate());
        entity.setLeaderStaffSignId(originalFiles.getLeaderStaffSignId());
        entity.setSignContent(originalFiles.getSignContent());
        entity.setLeaderStaffSignName(originalFiles.getLeaderStaffSignName());
        entity.setDisplayStatus(originalFiles.getDisplayStatus());
        entity.setNodeHistory(originalFiles.getNodeHistory());
        entity.setEffectiveDate(originalFiles.getEffectiveDate());
        entity.setIsFee(originalFiles.getIsFee());
        entity.setSignCa(originalFiles.getSignCa());

//        entity.setContentSigned(originalFiles.getContentSigned());
//        entity.setUserSigned(originalFiles.getUserSigned());
        entity.setIsComparison(originalFiles.getIsComparison());
        entity.setComparisonContent(originalFiles.getComparisonContent());
        entity.setFeeFile(originalFiles.getFeeFile());
        entity.setEvaluateAddDate(originalFiles.getEvaluateAddDate());
        entity.setEvaluateOutAddDate(originalFiles.getEvaluateOutAddDate());
        entity.setClericalRequest(originalFiles.getClericalRequest());
        entity.setIsTypeChange(originalFiles.getIsTypeChange());
        entity.setLastType(originalFiles.getLastType());
        if (originalFiles.getIsActive() == null) {
            entity.setIsActive(1l);
        } else {
            entity.setIsActive(originalFiles.getIsActive());
        }
        entity.setIsTemp(1L);
        entity.setOriginalId(originalFiles.getFileId());
        entity.setDeadlineAddition(originalFiles.getDeadlineAddition());
        entity.setDeadlineApprove(originalFiles.getDeadlineApprove());
        entity.setDeadlineComment(originalFiles.getDeadlineComment());
        entity.setDeadlineReceived(originalFiles.getDeadlineReceived());

        entity.setLastIsTemp(1L);
        entity.setIsSignPdf(originalFiles.getIsSignPdf());
        FilesDAOHE anndaohe = new FilesDAOHE();
        entity.setVersion(anndaohe.getCountVersion(originalFiles.getFileId()));
        entity.setReceiveNo(originalFiles.getReceiveNo());
        entity.setLeaderAssignId(originalFiles.getLeaderAssignId());
        entity.setLeaderAssignName(originalFiles.getLeaderAssignName());
        entity.setLeaderReviewId(originalFiles.getLeaderReviewId());
        entity.setLeaderReviewName(originalFiles.getLeaderReviewName());
        entity.setLeaderEvaluateId(originalFiles.getLeaderEvaluateId());
        entity.setLeaderEvaluateName(originalFiles.getLeaderEvaluateName());
        entity.setLeaderApproveId(originalFiles.getLeaderApproveId());
        entity.setLeaderApproveName(originalFiles.getLeaderApproveName());
        entity.setIs30(originalFiles.getIs30());
        entity.setIsDownload(originalFiles.getIsDownload());//141212
        //141213
        entity.setProductTypeId(originalFiles.getProductTypeId());
        entity.setProductTypeName(originalFiles.getProductTypeName());
        entity.setGoverningBody(originalFiles.getGoverningBody());
        //141216u binhnt53
        entity.setIsCourier(originalFiles.getIsCourier());
        entity.setRecipientAddress(originalFiles.getRecipientAddress());
        entity.setIsHaveSubLabel(originalFiles.getIsHaveSubLabel());
        //Ho so SDBS sau cong bo
        if(originalFiles.getFilesSourceID()!=null)
            entity.setFilesSourceID(originalFiles.getFilesSourceID());
        entity.setFileSourceCode(originalFiles.getFileSourceCode());
        entity.setContentsEdit(originalFiles.getContentsEdit());
        entity.setContentsEdit(originalFiles.getContentsEditATTP());
        entity.setTitleEditATTP(originalFiles.getTitleEditATTP());
        entity.setNoteEdit(originalFiles.getNoteEdit());
        entity.setTitleEdit(originalFiles.getTitleEdit());
        
        //end SDBS
        return entity;
    }

    public Files cloneEntity(){
        return cloneEntity(this);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fileId != null ? fileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Files)) {
            return false;
        }
        Files other = (Files) object;
        if ((this.fileId == null && other.fileId != null) || (this.fileId != null && !this.fileId.equals(other.fileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.hqmc.BO.Files[ fileId=" + fileId + " ]";
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

    public Date getDeadlineReceived() {
        return deadlineReceived;
    }

    public void setDeadlineReceived(Date deadlineReceived) {
        this.deadlineReceived = deadlineReceived;
    }

    public Long getFilesSourceID() {
        return filesSourceID;
    }

    public void setFilesSourceID(Long filesSourceID) {
        this.filesSourceID = filesSourceID;
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
    

    public Date getRepDate() {
        return repDate;
    }

    public void setRepDate(Date repDate) {
        this.repDate = repDate;
    }

    public Date getEvaluateOutAddDate() {
        return evaluateOutAddDate;
    }

    public void setEvaluateOutAddDate(Date evaluateOutAddDate) {
        this.evaluateOutAddDate = evaluateOutAddDate;
    }

    public Date getEvaluateAddDate() {
        return evaluateAddDate;
    }

    public void setEvaluateAddDate(Date evaluateAddDate) {
        this.evaluateAddDate = evaluateAddDate;
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

    public String getProposeUserName() {
        return proposeUserName;
    }

    public void setProposeUserName(String proposeUserName) {
        this.proposeUserName = proposeUserName;
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

    public Long getPreviousNodeId() {
        return previousNodeId;
    }

    public void setPreviousNodeId(Long previousNodeId) {
        this.previousNodeId = previousNodeId;
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

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
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

    public String getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(String displayStatus) {
        this.displayStatus = displayStatus;
    }

    public String getNodeHistory() {
        return nodeHistory;
    }

    public void setNodeHistory(String nodeHistory) {
        this.nodeHistory = nodeHistory;
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

    public Long getIsComparison() {
        return isComparison;
    }

    public void setIsComparison(Long isComparison) {
        this.isComparison = isComparison;
    }

    public String getComparisonContent() {
        return comparisonContent;
    }

    public void setComparisonContent(String comparisonContent) {
        this.comparisonContent = comparisonContent;
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

    public Long getIsSignPdf() {
        return isSignPdf;
    }

    public void setIsSignPdf(Long isSignPdf) {
        this.isSignPdf = isSignPdf;
    }

    public Long getHaveTemp() {
        return haveTemp;
    }

    public void setHaveTemp(Long haveTemp) {
        this.haveTemp = haveTemp;
    }

    public Long getProcessType() {
        return processType;
    }

    public void setProcessType(Long processType) {
        this.processType = processType;
    }

    public Long getStaffProcess() {
        return staffProcess;
    }

    public void setStaffProcess(Long staffProcess) {
        this.staffProcess = staffProcess;
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

    public Long getIs30() {
        return is30;
    }

    public void setIs30(Long is30) {
        this.is30 = is30;
    }

//141212 binhnt53
    public Long getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(Long isDownload) {
        this.isDownload = isDownload;
    }
    //141213 binhnt53

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
    //141216 binhnt53 tra ho so

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

    public Long getIsHaveSubLabel() {
        return isHaveSubLabel;
    }

    public void setIsHaveSubLabel(Long isHaveSubLabel) {
        this.isHaveSubLabel = isHaveSubLabel;
    }

    public String getFileSourceCode() {
        return fileSourceCode;
    }

    public void setFileSourceCode(String fileSourceCode) {
        this.fileSourceCode = fileSourceCode;
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

    public Long getIsCopy() {
        return isCopy;
    }

    public void setIsCopy(Long isCopy) {
        this.isCopy = isCopy;
    }

    
    
   
}
