/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.Constants;
import com.viettel.common.util.DateTimeUtils;
import com.viettel.common.util.LogUtil;
import com.viettel.hqmc.BO.Announcement;
import com.viettel.hqmc.BO.AnnouncementReceiptPaper;
import com.viettel.hqmc.BO.CountNo;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.BO.Procedure;
import com.viettel.hqmc.BO.RequestComment;
import static com.viettel.hqmc.DAOHE.FilesDAOHE.getFileStatusName;
import com.viettel.hqmc.FORM.AnnouncementReceiptPaperForm;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import com.viettel.voffice.database.DAOHibernate.ProcessDAOHE;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;

/**
 *
 * @version 1.0
 * @author havm2,binhnt53
 */
public class FilesExpandDAOHE extends GenericDAOHibernate<Files, Long> {

    //private static final Logger log = Logger.getLogger(FilesDAOHE.class);
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FilesExpandDAOHE.class);
    FilesDAOHE fdaohe = new FilesDAOHE();

    /**
     *
     */
    public FilesExpandDAOHE() {
        super(Files.class);
    }

    /**
     * Get chi tiết của hồ sơc
     *
     * @param fileId
     * @return
     */
    public boolean onCreatePaperForAA(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        try {
            Date dateNow = getSysdate();
            Files file = findById(form.getFileId());
            file.setModifyDate(dateNow);
            if (file != null) {
                if (form.getStatus().equals(Constants.FILE_STATUS.APPROVED)) {
                    file.setApproveDate(dateNow);
                    if (form.getContentsEditATTP() != null && !"".equals(form.getContentsEditATTP())) {
                        file.setContentsEditATTP(form.getContentsEditATTP());
                    }
                    //Hiepvv 1403 Lay y kien cua LDC in len noi dung cong van
//                    if (form.getLeaderRequest() != null && !form.getLeaderRequest().isEmpty()) {
//                        file.setContentsEditATTP(form.getLeaderRequest());
//                    }
                    ProcedureDAOHE pcdaohe = new ProcedureDAOHE();
                    Procedure procedure = pcdaohe.findById(file.getFileType());
                    if (procedure != null && procedure.getProcedureId() > 0) {
                        if (file.getAnnouncementId() != null) {
                            if (file.getAnnouncementReceiptPaperId() == null) {
                                AnnouncementReceiptPaperForm arpForm = new AnnouncementReceiptPaperForm();
                                AnnouncementDAOHE announcementHE = new AnnouncementDAOHE();
                                Announcement announcement = announcementHE.findById(file.getAnnouncementId());
                                
                                arpForm.setBusinessName(announcement.getBusinessName());
                                arpForm.setProductName(announcement.getProductName());
                                arpForm.setManufactureName(announcement.getManufactureName());
                                arpForm.setEmail(announcement.getBusinessEmail());
                                arpForm.setFax(announcement.getBusinessFax());
                                arpForm.setTelephone(announcement.getBusinessTelephone());
                                arpForm.setNationName(announcement.getNationName());
                                String strReceiptNo = fdaohe.getNewReceiptNo(file.getAgencyId(), file.getFileType());
                                arpForm.setReceiptNo(strReceiptNo);
                                if (file.getEffectiveDate() != null) {
                                    if (file.getEffectiveDate() == 3) {//lay ngay ki + 3 nam
                                        arpForm.setEffectiveDate(DateUtils.addYears(dateNow, 3));
                                    } else if (file.getEffectiveDate() == 5) {//lay ngay ki + 5 nam
                                        arpForm.setEffectiveDate(DateUtils.addYears(dateNow, 5));
                                    } else {
                                        arpForm.setEffectiveDate(DateUtils.addYears(dateNow, 3));
                                    }
                                }
                                arpForm.setReceiptDate(dateNow);
                                arpForm.setReceiptDeptName(deptName);//ten co quan tiep nhan cong bo
                                arpForm.setMatchingTarget(announcement.getMatchingTarget());//so hieu qui chuan ki thuat
                                arpForm.setSignDate(dateNow);//ngay ki
                                arpForm.setSignerName(userName);//nguoi ki
                                //tao giay tiep nhan
                                try {
                                    AnnouncementReceiptPaperDAOHE cthe = new AnnouncementReceiptPaperDAOHE();
                                    if (cthe.isDuplicate(arpForm) == true) {
                                        return false;
                                    } else {
                                        Long ObjId = arpForm.getAnnouncementReceiptPaperId();
                                        AnnouncementReceiptPaper bo = arpForm.convertToEntity();
                                        if (ObjId == null) {
                                            getSession().save(bo);
                                            file.setAnnouncementReceiptPaperId(bo.getAnnouncementReceiptPaperId());
                                            getSession().update(file);
                                        } else {
                                            getSession().update(bo);
                                            file.setAnnouncementReceiptPaperId(bo.getAnnouncementReceiptPaperId());
                                            getSession().update(file);
                                        }
                                    }
                                    getSession().beginTransaction().commit();
                                    return true;
                                } catch (Exception ex) {
                                    LogUtil.addLog(ex);//binhnt sonar a160901
                                    return false;
                                }
                            } else {//co so roi thuc hien update khong tao moi - binhnt53 14.11.12
                                AnnouncementReceiptPaperDAOHE arpdaohe = new AnnouncementReceiptPaperDAOHE();
                                AnnouncementReceiptPaper arpbo = arpdaohe.findById(file.getAnnouncementReceiptPaperId());
                                if (arpbo != null) {
                                    AnnouncementDAOHE announcementHE = new AnnouncementDAOHE();
                                    Announcement announcement = announcementHE.findById(file.getAnnouncementId());
                                    if (announcement != null) {
                                        arpbo.setBusinessName(announcement.getBusinessName());
                                        arpbo.setProductName(announcement.getProductName());
                                        arpbo.setManufactureName(announcement.getManufactureName());
                                        arpbo.setEmail(announcement.getBusinessEmail());
                                        arpbo.setFax(announcement.getBusinessFax());
                                        arpbo.setTelephone(announcement.getBusinessTelephone());
                                        arpbo.setNationName(announcement.getNationName());
                                        if (file.getEffectiveDate() != null) {
                                            if (file.getEffectiveDate() == 3) {//lay ngay ki + 3 nam
                                                arpbo.setEffectiveDate(DateUtils.addYears(dateNow, 3));
                                            } else if (file.getEffectiveDate() == 5) {//lay ngay ki + 5 nam
                                                arpbo.setEffectiveDate(DateUtils.addYears(dateNow, 5));
                                            } else {
                                                arpbo.setEffectiveDate(DateUtils.addYears(dateNow, 3));
                                            }
                                        }
                                        if (arpbo.getReceiptDate() != null) {
                                            arpbo.setReceiptDate(arpbo.getReceiptDate());
                                        } else {
                                            arpbo.setReceiptDate(dateNow);
                                        }
                                        arpbo.setReceiptDeptName(deptName);//ten co quan tiep nhan cong bo
                                        arpbo.setMatchingTarget(announcement.getMatchingTarget());//so hieu qui chuan ki thuat
                                        arpbo.setSignDate(dateNow);//ngay ki
                                        arpbo.setSignerName(userName);//nguoi ki
                                        getSession().update(arpbo);
                                        getSession().update(file);
                                        getSession().beginTransaction().commit();
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            }
                        } else {
                            return false;
                        }
                        //!tao giay tiep nhan
                    }
                    
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return false;
        }
        
        return true;
    }
    
    public boolean onApproveByLDP4AA(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else {
                Long processStatus = file.getStatus();
                /*
                 REVIEWED = 5L;//Đã xem xét kết quả
                 REVIEW_TO_ADD = 26L;//đã xem xét nội dung cv sđbs
                 REVIEW_TO_BOSS = 29L;//Hồ sơ trình cục trưởng xem xét
                 APPROVE_TO_LEADER = 30L;//Hồ sơ cục trưởng đã quyết định
                 APPROVE_TO_ADD phê duyệt công văn sửa đổi bổ sung.
                 EVALUATE_TO_ADD //Da soan du thao sua doi bo sung
                 */
                if (processStatus != null//141225 BINHNT update phan quyen ho so tham dinh
                        && (processStatus.equals(Constants.FILE_STATUS.REVIEWED)//Đã xem xét kết quả
                        || processStatus.equals(Constants.FILE_STATUS.REVIEW_TO_ADD)//Đã xem xét nội dung cv sđbs
                        || processStatus.equals(Constants.FILE_STATUS.APPROVE_TO_ADD)//Đã phê duyệt nội dung thông báo VT
                        || processStatus.equals(Constants.FILE_STATUS.REVIEW_TO_BOSS)//Hồ sơ trình cục trưởng xem xét
                        || processStatus.equals(Constants.FILE_STATUS.APPROVE_TO_LEADER)//Hồ sơ cục trưởng đã quyết định
                        || processStatus.equals(Constants.FILE_STATUS.EVALUATE_TO_ADD) //Đã soạn dự thảo sửa đổi bổ sung
                        || processStatus.equals(Constants.FILE_STATUS.FEDBACK_TO_ADD) //Đã trả lại bổ sung hồ sơ
                        || processStatus.equals(Constants.FILE_STATUS.EVALUATED) //Đã trả lại bổ sung hồ sơ
                        )) {
                    file.setStatus(form.getStatus());
                    file.setDisplayStatus(getFileStatusName(form.getStatus()));
                    String dateTime = DateTimeUtils.convertDateToString(dateNow, "dd/MM/yyyy HH:mm");
                    if (!form.getStatus().equals(Constants.FILE_STATUS.APPROVE_TO_ADD)) {
                        file.setLeaderRequest(userName + " " + dateTime + ":\n" + form.getLeaderRequest());
                        file.setDisplayRequest(form.getLeaderRequest());
                    } else {
                        file.setLeaderRequest(userName + " " + dateTime + ":\n" + "Đã phê duyệt nội dung công văn yêu cầu SĐBS");
                        file.setDisplayRequest("Đã phê duyệt nội dung công văn yêu cầu SĐBS");
                    }
                    file.setModifyDate(dateNow);
                    file.setLeaderStaffSignId(userId);
                    file.setLeaderStaffSignName(userName);
                    // Cap nhat process
                    ProcessDAOHE pdhe = new ProcessDAOHE();
                    com.viettel.voffice.database.BO.Process oldP = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, processStatus, Constants.FILE_STATUS.NEW_CREATE);
                    if (oldP != null) {
                        oldP.setStatus(form.getStatus());
                        oldP.setLastestComment(form.getLeaderRequest());
                        getSession().update(oldP);
                    }
                    //tạo process mới với xử lý hiển tại
                    com.viettel.voffice.database.BO.Process newP = new com.viettel.voffice.database.BO.Process();
                    newP.setObjectId(form.getFileId());
                    newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                    newP.setSendDate(dateNow);
                    newP.setSendGroup(deptName);
                    newP.setSendGroupId(deptId);
                    newP.setSendUserId(userId);
                    newP.setSendUser(userName);
                    newP.setProcessStatus(form.getStatus()); // De xu ly
                    newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                    newP.setStatus(0l); // Moi den chua xu ly
                    newP.setIsActive(1l);
                    newP.setReceiveDate(dateNow);
                    if (form.getStatus().equals(Constants.FILE_STATUS.APPROVED)) {//trả về cho văn thư thông báo đói chiếu
                        newP.setReceiveGroup(file.getAgencyName());
                        newP.setReceiveGroupId(file.getAgencyId());
//cap nhat noi dung thong bao - tao noi dung thong bao
                        RequestComment rcbo = new RequestComment();
                        rcbo.setContent(form.getLeaderStaffRequest());
                        file.setComparisonContent(form.getLeaderStaffRequest());
                        rcbo.setCreateBy(userId);
                        rcbo.setCreateDate(dateNow);
                        rcbo.setUserId(userId);
                        rcbo.setUserName(userName);
                        rcbo.setStatus(1L);
                        rcbo.setIsActive(1L);
                        rcbo.setGroupId(deptId);
                        rcbo.setGroupName(deptName);
                        rcbo.setObjectId(form.getFileId());
                        rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.TBDC);
                        rcbo.setIsLastChange(Constants.ACTIVE_STATUS.ACTIVE);
//150119 binhnt53 update bo sung last change
                        RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
                        RequestComment lastRQBo = rqdaohe.findLastRequestComment(file.getFileId(), Constants.ACTIVE_STATUS.ACTIVE);
                        if (lastRQBo != null) {
                            rcbo.setLastContent(lastRQBo.getContent());
                            lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                            getSession().update(lastRQBo);
                        }
                        getSession().save(rcbo);
//!binhnt53 update bo sung last change
                        ProcedureDAOHE pdheNew = new ProcedureDAOHE();
                        Procedure pro = pdheNew.findById(file.getFileType());
                        //Hiepvv 0803 Khong xet lai phi cho SDBS sau cong bo
                        if (!"01".equals(pro.getCode())
                                && !"02".equals(pro.getCode())
                                && !"announcementFile05".equals(pro.getDescription())) {
                            file.setIsFee(0l);
                        }
                    } else {
                        if (form.getStatus().equals(Constants.FILE_STATUS.REVIEWED_TO_ADD)) {//da xem xet yc sdbs
                            //lay process tham dinh ho so tra ve cho chuyen vien tham dinh chinh
                            //gui den chuyen vien xu ly
                            if (oldP != null) {
                                newP.setReceiveGroup(oldP.getSendGroup());
                                newP.setReceiveGroupId(oldP.getSendGroupId());
                            }
                            newP.setReceiveUser(file.getNameStaffProcess());
                            newP.setReceiveUserId(file.getStaffProcess());

//cap nhat noi dung thong bao - tao noi dung thong bao
                            RequestComment rcbo = new RequestComment();
                            rcbo.setContent(file.getLeaderRequest());
                            rcbo.setCreateBy(userId);
                            rcbo.setCreateDate(dateNow);
                            rcbo.setUserId(userId);
                            rcbo.setUserName(userName);
                            rcbo.setStatus(1L);
                            rcbo.setIsActive(1L);
                            rcbo.setGroupId(deptId);
                            rcbo.setGroupName(deptName);
                            rcbo.setObjectId(form.getFileId());
                            rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.TBSDBS);
                            rcbo.setIsLastChange(Constants.ACTIVE_STATUS.ACTIVE);
                            
                            RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
                            RequestComment lastRQBo = rqdaohe.findLastRequestComment(file.getFileId(), Constants.ACTIVE_STATUS.ACTIVE);
                            if (lastRQBo != null) {
                                rcbo.setLastContent(lastRQBo.getContent());
                                lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                                getSession().update(lastRQBo);
                            }
                            getSession().save(rcbo);
//cap nhat noi dung thong bao - tao noi dung thong bao
                        }
                        if (form.getStatus().equals(Constants.FILE_STATUS.APPROVE_TO_ADD)) {//ldc phe duyet noi dung chuyen cho vt gui cong van sdbs
                            if (deptId.equals(file.getAgencyId())) {
                                newP.setReceiveGroup(deptName);
                                newP.setReceiveGroupId(deptId);
                            } else {
                                newP.setReceiveGroup(file.getAgencyName());
                                newP.setReceiveGroupId(file.getAgencyId());
                            }
//cap nhat noi dung thong bao - tao noi dung thong bao
                            RequestComment rcbo = new RequestComment();
                            if (file.getLeaderRequest() != null) {
                                rcbo.setContent(form.getLeaderRequest());
                            } else {
                                rcbo.setContent("Lãnh đạo phòng chưa có nội dung.");
                            }
                            rcbo.setCreateBy(userId);
                            rcbo.setCreateDate(dateNow);
                            rcbo.setUserId(userId);
                            rcbo.setUserName(userName);
                            rcbo.setStatus(1L);
                            rcbo.setIsActive(1L);
                            rcbo.setGroupId(deptId);
                            rcbo.setGroupName(deptName);
                            rcbo.setIsLastChange(Constants.ACTIVE_STATUS.ACTIVE);
                            rcbo.setObjectId(form.getFileId());
                            rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.TBSDBS);
//u150119 binhnt53
                            RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
                            RequestComment lastRQBo = rqdaohe.findLastRequestComment(file.getFileId(), Constants.ACTIVE_STATUS.ACTIVE);
                            if (lastRQBo != null) {
                                rcbo.setLastContent(lastRQBo.getContent());
                                lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                                getSession().update(lastRQBo);
                            }
                            getSession().save(rcbo);
//!cap nhat noi dung thong bao - tao noi dung thong bao
                            //tang so ho so di
                            CountNoDAOHE cndaohe = new CountNoDAOHE();
                            CountNo cnbo = cndaohe.returnCountNoByDeptId(deptId);
                            if (cnbo != null && cnbo.getSendNo() > 0L) {
                                Long nCount = cnbo.getSendNo();
                                cnbo.setSendNo(nCount + 1);
                                getSession().update(cnbo);
                            } else if (file != null && file.getAgencyId() > 0L) {
                                cnbo = cndaohe.returnCountNoByDeptId(file.getAgencyId());
                                Long nCount = cnbo.getSendNo();
                                cnbo.setSendNo(nCount + 1);
                                getSession().update(cnbo);
                            }
                        }
                        if (form.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_REVIEW)) {//160406
                            //lanh dao cuc tu choi phe duyet cong van thong bao sdbs chuyen cho lanh dao phong xem xet lai
                            if (oldP != null) {
                                newP.setReceiveGroup(oldP.getSendGroup());
                                newP.setReceiveGroupId(oldP.getSendGroupId());
                                newP.setReceiveUser(oldP.getSendUser());
                                newP.setReceiveUserId(oldP.getSendUserId());
                            }
                            //cap nhat noi dung thong bao - tao noi dung thong bao
                            RequestComment rcbo = new RequestComment();
                            if (file.getLeaderRequest() != null) {
                                rcbo.setContent(form.getLeaderRequest());
                            } else {
                                rcbo.setContent("Lãnh đạo phòng chưa có nội dung.");
                            }
                            rcbo.setCreateBy(userId);
                            rcbo.setCreateDate(dateNow);
                            rcbo.setUserId(userId);
                            rcbo.setUserName(userName);
                            rcbo.setStatus(1L);
                            rcbo.setIsActive(1L);
                            rcbo.setGroupId(deptId);
                            rcbo.setGroupName(deptName);
                            rcbo.setIsLastChange(Constants.ACTIVE_STATUS.ACTIVE);
                            rcbo.setObjectId(form.getFileId());
                            rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.TBSDBS);
                            
                            RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
                            RequestComment lastRQBo = rqdaohe.findLastRequestComment(file.getFileId(), Constants.ACTIVE_STATUS.ACTIVE);
                            if (lastRQBo != null) {
                                rcbo.setLastContent(lastRQBo.getContent());
                                lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                                getSession().update(lastRQBo);
                            }
                            getSession().save(rcbo);
//!cap nhat noi dung thong bao - tao noi dung thong bao
                        }
                        if (form.getStatus().equals(Constants.FILE_STATUS.REVIEW_TO_BOSS)) {//- luong pho cuc truong gui trinh len lanh dao cuc
                            //- tim cuc truong de gui
                            UsersDAOHE udaohe = new UsersDAOHE();
                            Users ubo = udaohe.findUserByPosition(deptId, Constants.POSITION.LEADER_CT);
                            if (ubo != null) {
                                newP.setReceiveUser(ubo.getFullName());
                                newP.setReceiveUserId(ubo.getUserId());
                            }
                            newP.setReceiveGroup(deptName);
                            newP.setReceiveGroupId(deptId);
//cap nhat noi dung thong bao - tao noi dung thong bao
                            RequestComment rcbo = new RequestComment();
                            if (file.getLeaderRequest() != null) {
                                rcbo.setContent(form.getLeaderRequest());
                            } else {
                                rcbo.setContent("Lãnh đạo phòng chưa có nội dung.");
                            }
                            rcbo.setCreateBy(userId);
                            rcbo.setCreateDate(dateNow);
                            rcbo.setUserId(userId);
                            rcbo.setUserName(userName);
                            rcbo.setStatus(1L);
                            rcbo.setIsActive(1L);
                            rcbo.setGroupId(deptId);
                            rcbo.setGroupName(deptName);
                            rcbo.setIsLastChange(Constants.ACTIVE_STATUS.ACTIVE);
                            rcbo.setObjectId(form.getFileId());
                            rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.TCT);//trinh cuc truong

                            RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
                            RequestComment lastRQBo = rqdaohe.findLastRequestComment(file.getFileId(), Constants.ACTIVE_STATUS.ACTIVE);
                            if (lastRQBo != null) {
                                rcbo.setLastContent(lastRQBo.getContent());
                                lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                                getSession().update(lastRQBo);
                            }
                            getSession().save(rcbo);
//!cap nhat noi dung thong bao - tao noi dung thong bao
                        }
                    }
                    getSession().save(newP);
                    update(file);
                    if (form.getStatus().equals(Constants.FILE_STATUS.APPROVED)) {
                        ProcedureDAOHE pcdaohe = new ProcedureDAOHE();
                        Procedure procedure = pcdaohe.findById(file.getFileType());
                        if (procedure != null && procedure.getProcedureId() > 0) {
                            if (!procedure.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01) && !procedure.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03)) {
                                //sms
                                MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                                String msg = "Ho so ma: " + file.getFileCode()
                                        + " cua doanh nghiep: " + file.getBusinessName()
                                        + " dang trong trang thai: da phe duyet, doanh nghiep luu y dong le phi cap so de duoc cap ban cong bo";
                                msdhe.saveMessageSMS(userId, file.getUserCreateId(), msg);
                                //email
                                MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                                String msge = "Hồ sơ mã: " + file.getFileCode()
                                        + " của doanh nghiệp: " + file.getBusinessName()
                                        + " đang trong trạng thái: đã phê duyệt, doanh nghiệp lưu ý đóng lệ phí cấp số để được cấp bản công bố";
                                msedhe.saveMessageEmail(userId, file.getUserCreateId(), msge);
                            }
                        }
                    }
                } else {
                    return false;
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            bReturn = false;
        }
        return bReturn;
    }
    
    public boolean onCreatePaperByLeaderForAA(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        try {
            Date dateNow = getSysdate();
            Files file = findById(form.getFileId());
            file.setModifyDate(dateNow);
            if (file != null) {
                if (form.getStatus().equals(Constants.FILE_STATUS.APPROVED)) {
                    file.setApproveDate(dateNow);
                    if (form.getTitleEditATTP() != null && !form.getTitleEditATTP().isEmpty()) {
                        file.setTitleEditATTP(form.getTitleEditATTP());
                    }
                    if (form.getContentsEditATTP() != null && !form.getContentsEditATTP().isEmpty()) {
                        file.setContentsEditATTP(form.getContentsEditATTP());
                    }
                    ProcedureDAOHE pcdaohe = new ProcedureDAOHE();
                    Procedure procedure = pcdaohe.findById(file.getFileType());
                    if (procedure != null && procedure.getProcedureId() > 0) {
                        if (!procedure.getDescription().equals(Constants.FILE_DESCRIPTION.CONFIRM_SATISFACTORY)) {//tao giay tiep nhan cong bo
                            if (file.getAnnouncementId() != null) {
                                if (file.getAnnouncementReceiptPaperId() == null) {
                                    AnnouncementReceiptPaperForm arpForm = new AnnouncementReceiptPaperForm();
                                    AnnouncementDAOHE announcementHE = new AnnouncementDAOHE();
                                    Announcement announcement = announcementHE.findById(file.getAnnouncementId());
                                    
                                    arpForm.setBusinessName(announcement.getBusinessName());
                                    arpForm.setProductName(announcement.getProductName());
                                    arpForm.setManufactureName(announcement.getManufactureName());
                                    arpForm.setEmail(announcement.getBusinessEmail());
                                    arpForm.setFax(announcement.getBusinessFax());
                                    arpForm.setTelephone(announcement.getBusinessTelephone());
                                    arpForm.setNationName(announcement.getNationName());
                                    String strReceiptNo = fdaohe.getNewReceiptNo(file.getAgencyId(), file.getFileType());
                                    arpForm.setReceiptNo(strReceiptNo);
                                    if (file.getEffectiveDate() != null) {
                                        if (file.getEffectiveDate() == 3) {//lay ngay ki + 3 nam
                                            arpForm.setEffectiveDate(DateUtils.addYears(dateNow, 3));
                                        } else if (file.getEffectiveDate() == 5) {//lay ngay ki + 5 nam
                                            arpForm.setEffectiveDate(DateUtils.addYears(dateNow, 5));
                                        } else {
                                            arpForm.setEffectiveDate(DateUtils.addYears(dateNow, 3));
                                        }
                                    }
                                    arpForm.setReceiptDate(dateNow);
                                    arpForm.setReceiptDeptName(deptName);//ten co quan tiep nhan cong bo
                                    arpForm.setMatchingTarget(announcement.getMatchingTarget());//so hieu qui chuan ki thuat
                                    arpForm.setSignDate(dateNow);//ngay ki
                                    arpForm.setSignerName(userName);//nguoi ki
                                    //tao giay tiep nhan
                                    try {
                                        AnnouncementReceiptPaperDAOHE cthe = new AnnouncementReceiptPaperDAOHE();
                                        if (cthe.isDuplicate(arpForm) == true) {
                                            return false;
                                        } else {
                                            Long ObjId = arpForm.getAnnouncementReceiptPaperId();
                                            AnnouncementReceiptPaper bo = arpForm.convertToEntity();
                                            if (ObjId == null) {
                                                getSession().save(bo);
                                                file.setAnnouncementReceiptPaperId(bo.getAnnouncementReceiptPaperId());
                                                getSession().update(file);
                                            } else {
                                                getSession().update(bo);
                                                file.setAnnouncementReceiptPaperId(bo.getAnnouncementReceiptPaperId());
                                                getSession().update(file);
                                            }
                                        }
                                        getSession().beginTransaction().commit();
                                        return true;
                                    } catch (Exception ex) {
                                        LogUtil.addLog(ex);//binhnt sonar a160901
                                        return false;
                                    }
                                } else {//co so roi thuc hien update khong tao moi - binhnt53 14.11.12
                                    AnnouncementReceiptPaperDAOHE arpdaohe = new AnnouncementReceiptPaperDAOHE();
                                    AnnouncementReceiptPaper arpbo = arpdaohe.findById(file.getAnnouncementReceiptPaperId());
                                    if (arpbo != null) {
                                        AnnouncementDAOHE announcementHE = new AnnouncementDAOHE();
                                        Announcement announcement = announcementHE.findById(file.getAnnouncementId());
                                        if (announcement != null) {
                                            arpbo.setBusinessName(announcement.getBusinessName());
                                            arpbo.setProductName(announcement.getProductName());
                                            arpbo.setManufactureName(announcement.getManufactureName());
                                            arpbo.setEmail(announcement.getBusinessEmail());
                                            arpbo.setFax(announcement.getBusinessFax());
                                            arpbo.setTelephone(announcement.getBusinessTelephone());
                                            arpbo.setNationName(announcement.getNationName());
                                            if (file.getEffectiveDate() != null) {
                                                if (file.getEffectiveDate() == 3) {//lay ngay ki + 3 nam
                                                    arpbo.setEffectiveDate(DateUtils.addYears(dateNow, 3));
                                                } else if (file.getEffectiveDate() == 5) {//lay ngay ki + 5 nam
                                                    arpbo.setEffectiveDate(DateUtils.addYears(dateNow, 5));
                                                } else {
                                                    arpbo.setEffectiveDate(DateUtils.addYears(dateNow, 3));
                                                }
                                            }
                                            if (arpbo.getReceiptDate() != null) {
                                                arpbo.setReceiptDate(arpbo.getReceiptDate());
                                            } else {
                                                arpbo.setReceiptDate(dateNow);
                                            }
                                            arpbo.setReceiptDeptName(deptName);//ten co quan tiep nhan cong bo
                                            arpbo.setMatchingTarget(announcement.getMatchingTarget());//so hieu qui chuan ki thuat
                                            arpbo.setSignDate(dateNow);//ngay ki
                                            arpbo.setSignerName(userName);//nguoi ki
                                            getSession().update(arpbo);
                                            getSession().update(file);
                                            getSession().beginTransaction().commit();
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                            } else {
                                return false;
                            }
                        }
                    }
                    
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return false;
        }
        return true;
    }
}
