/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.google.gson.Gson;
import com.viettel.common.util.Constants;
import com.viettel.common.util.DateTimeUtils;
import com.viettel.common.util.StringUtils;
import com.viettel.flow.DAOHE.FlowDAOHE;
import com.viettel.hqmc.BO.Announcement;
import com.viettel.hqmc.BO.AnnouncementReceiptPaper;
import com.viettel.hqmc.BO.ConfirmImportSatistPaper;
import com.viettel.hqmc.BO.CountNo;
import com.viettel.hqmc.BO.DetailProduct;
import com.viettel.hqmc.BO.EvaluationRecords;
import com.viettel.hqmc.BO.Fee;
import com.viettel.hqmc.BO.FeePaymentInfo;
import com.viettel.hqmc.BO.FeeProcedure;
import com.viettel.hqmc.BO.FileForSearch;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.BO.MainlyTarget;
import com.viettel.hqmc.BO.Procedure;
import com.viettel.hqmc.BO.ProcedureDepartment;
import com.viettel.hqmc.BO.ProductInFile;
import com.viettel.hqmc.BO.ProductTarget;
import com.viettel.hqmc.BO.QualityControlPlan;
import com.viettel.hqmc.BO.ReIssueForm;
import com.viettel.hqmc.BO.ReceiveEmail;
import com.viettel.hqmc.BO.RequestComment;
import com.viettel.hqmc.BO.TestRegistration;
import com.viettel.hqmc.DAO.EmailSmsDAO;
import com.viettel.hqmc.FORM.AnnouncementForm;
import com.viettel.hqmc.FORM.AnnouncementReceiptPaperForm;
import com.viettel.hqmc.FORM.ConfirmImportSatistPaperForm;
import com.viettel.hqmc.FORM.DetailProductForm;
import com.viettel.hqmc.FORM.EvaluationRecordsForm;
import com.viettel.hqmc.FORM.EvaluationRecordsFormOnGrid;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.hqmc.FORM.ReIssueFormForm;
import com.viettel.hqmc.FORM.TestRegistrationForm;
import com.viettel.voffice.common.util.CommonUtils;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.BO.Process;
import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.CategoryDAOHE;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import com.viettel.voffice.database.DAOHibernate.ProcessCommentDAOHE;
import com.viettel.voffice.database.DAOHibernate.ProcessDAOHE;
import com.viettel.vsaadmin.database.BO.Department;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAOHibernate.DepartmentDAOHE;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import com.viettel.ws.FORM.ANNOUCE_HANDLING;
import com.viettel.ws.validateData.Helper;
import com.viettel.ws.validateData.Validator;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.w3c.dom.Document;

/**
 *
 * @version 1.0
 * @author havm2,binhnt53
 */
public class FilesDAOHE extends GenericDAOHibernate<Files, Long> {

    //private static final Logger log = Logger.getLogger(FilesDAOHE.class);
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FilesDAOHE.class);

    /**
     *
     */
    public FilesDAOHE() {
        super(Files.class);
    }

    /**
     * Get chi tiết của hồ sơc
     *
     * @param fileId
     * @return
     */
    public FilesForm getFilesDetail(Long fileId) {
        Files entity = findById(fileId);
        FilesForm form = null;
        try {
            if (entity != null) {
                form = new FilesForm(entity);
                if (entity.getAnnouncementId() != null) {
                    Announcement ann = (Announcement) findById(
                            Announcement.class,
                            "announcementId",
                            entity.getAnnouncementId()
                    );
                    form.setAnnouncement(new AnnouncementForm(ann));
                    form.setAnnouncementReceiptPaperForm(new AnnouncementReceiptPaperForm());
                }
                if (entity.getReIssueFormId() != null) {
                    ReIssueForm re = (ReIssueForm) findById(
                            ReIssueForm.class,
                            "reIssueFormId",
                            entity.getReIssueFormId()
                    );
                    form.setReIssueForm(new ReIssueFormForm(re));
                }
                if (entity.getDetailProductId() != null) {
                    DetailProduct ann = (DetailProduct) findById(
                            DetailProduct.class,
                            "detailProductId",
                            entity.getDetailProductId()
                    );
                    form.setDetailProduct(new DetailProductForm(ann));
                }
                if (entity.getTestRegistrationId() != null) {
                    TestRegistration ann = (TestRegistration) findById(
                            TestRegistration.class,
                            "testRegistrationId",
                            entity.getTestRegistrationId()
                    );
                    form.setTestRegistration(new TestRegistrationForm(ann));
                    form.setConfirmImportSatistPaperForm(new ConfirmImportSatistPaperForm());
                }
                //set contentXml
                try {
                    Document document = CommonUtils.buildAllPublishDocument(entity.getFileId());
                    form.setContentXml(StringUtils.escapeHtml(CommonUtils.convertDocument2String(document)));
                } catch (Exception e) {
                    log.error(e);
                }
                try {
                    if (entity.getAnnouncementReceiptPaperId() != null) {
                        AnnouncementReceiptPaper annp = (AnnouncementReceiptPaper) findById(
                                AnnouncementReceiptPaper.class,
                                "announcementReceiptPaperId",
                                entity.getAnnouncementReceiptPaperId()
                        );
                        form.setAnnouncementReceiptPaperForm(new AnnouncementReceiptPaperForm(annp));
                    }
                    if (entity.getConfirmImportSatistPaperId() != null) {
                        ConfirmImportSatistPaper arp = (ConfirmImportSatistPaper) findById(
                                AnnouncementReceiptPaper.class,
                                "confirmImportSatistPaperId",
                                entity.getAnnouncementReceiptPaperId()
                        );
                        form.setConfirmImportSatistPaperForm(new ConfirmImportSatistPaperForm(arp));
                    }

                } catch (Exception e) {
                    log.error(e);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return form;
    }

    /**
     *
     * @param fileId
     * @return
     */
    public FilesForm getFilesFullDetail(Long fileId) {
        Files entity = findById(fileId);
        FilesForm form = null;
        try {
            if (entity != null) {
                form = new FilesForm(entity);
                if (entity.getAnnouncementId() != null) {
                    Announcement ann = (Announcement) findById(
                            Announcement.class,
                            "announcementId",
                            entity.getAnnouncementId()
                    );
                    form.setAnnouncement(new AnnouncementForm(ann));
                }
                if (entity.getReIssueFormId() != null) {
                    ReIssueForm re = (ReIssueForm) findById(
                            ReIssueForm.class,
                            "reIssueFormId",
                            entity.getReIssueFormId()
                    );
                    form.setReIssueForm(new ReIssueFormForm(re));
                }
                if (entity.getDetailProductId() != null) {
                    DetailProduct ann = (DetailProduct) findById(
                            DetailProduct.class,
                            "detailProductId",
                            entity.getDetailProductId()
                    );
                    form.setDetailProduct(new DetailProductForm(ann));
                }
                if (entity.getTestRegistrationId() != null) {
                    TestRegistration ann = (TestRegistration) findById(
                            TestRegistration.class,
                            "testRegistrationId",
                            entity.getTestRegistrationId()
                    );
                    form.setTestRegistration(new TestRegistrationForm(ann));
                }

                form.setLstMainlyTarget(getMainlyTargetOfFile(fileId));
                form.setLstBioTarget(getProductTargetOfFile(fileId, 1l));
                form.setLstHeavyMetal(getProductTargetOfFile(fileId, 2l));
                form.setLstChemical(getProductTargetOfFile(fileId, 3l));
                form.setLstAttachs(getAttachsOfFile(fileId));
                form.setLstQualityControl(getQualityControlOfFile(fileId));
            }
        } catch (Exception e) {
            log.error(e);
        }
        return form;
    }

    /**
     * Get clone files binhnt53 140417
     *
     * @param fileId
     * @return
     */
    public FilesForm getCloneFilesDetail(Long fileId) {//
//        Files entity = (Files) findById(Files.class, "originalId", fileId);
        Files entity = findIsItempObj(0L, fileId, 0L, true);
        FilesForm form = null;
        try {
            if (entity != null
                    && entity.getFileId() != null
                    && entity.getFileId() > 0L) {
                form = new FilesForm(entity);
                if (entity.getAnnouncementId() != null) {
//                    Announcement objClone = (Announcement) findById(Announcement.class, "announcementId", entity.getAnnouncementId());
                    AnnouncementDAOHE objDaohe = new AnnouncementDAOHE();
                    Announcement objClone = objDaohe.findIsItempObj(
                            entity.getAnnouncementId(),
                            0L,
                            0L,
                            true
                    );
                    if (objClone != null && objClone.getAnnouncementId() != null) {
                        form.setAnnouncement(new AnnouncementForm(objClone));
                        form.setAnnouncementReceiptPaperForm(new AnnouncementReceiptPaperForm());
                    }
                }
                if (entity.getReIssueFormId() != null) {
//                    ReIssueForm objClone = (ReIssueForm) findById(ReIssueForm.class, "reIssueFormId", entity.getReIssueFormId());
                    ReIssueFormDAOHE objDaohe = new ReIssueFormDAOHE();
                    ReIssueForm objClone = objDaohe.findIsItempObj(entity.getReIssueFormId(), 0L, 0L, true);
                    if (objClone != null && objClone.getReIssueFormId() != null) {
                        form.setReIssueForm(new ReIssueFormForm(objClone));
                    }
                }
                if (entity.getDetailProductId() != null) {
//                    DetailProduct objClone = (DetailProduct) findById(DetailProduct.class, "detailProductId", entity.getDetailProductId());
                    DetailProductDAOHE objDaohe = new DetailProductDAOHE();
                    DetailProduct objClone = objDaohe.findIsItempObj(entity.getDetailProductId(), 0L, 0L, true);
                    if (objClone != null && objClone.getDetailProductId() != null) {
                        form.setDetailProduct(new DetailProductForm(objClone));
                    }
                }
                if (entity.getTestRegistrationId() != null) {
//                    TestRegistration objClone = (TestRegistration) findById(TestRegistration.class, "testRegistrationId", entity.getTestRegistrationId());
                    TestRegistrationDAOHE objDaohe = new TestRegistrationDAOHE();
                    TestRegistration objClone = objDaohe.findIsItempObj(entity.getTestRegistrationId(), 0L, 0L, true);
                    if (objClone != null && objClone.getTestRegistrationId() != 0) {
                        form.setTestRegistration(new TestRegistrationForm(objClone));
                        form.setConfirmImportSatistPaperForm(new ConfirmImportSatistPaperForm());
                    }
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return form;
    }

    /**
     * Gửi hồ sơ cho cơ quan xử lý Kế toán xác nhận hồ sơ
     *
     * @param fileId
     * @param deptId
     * @param userId
     * @param userName
     * @param businessId
     * @param businessName
     * @return
     */
    public boolean assignFileToDept(Long fileId, Long deptId, Long userId, String userName, Long businessId, String businessName) {
        boolean bReturn = true;
        try {
            Files bo = findById(fileId);
//            FlowDAOHE fdhe = new FlowDAOHE();
            DepartmentDAOHE ddhe = new DepartmentDAOHE();
            Date dateNow = getSysdate();
            Department dept = ddhe.findById(deptId);
            Long processStatus = bo.getStatus();
            bo.setAgencyId(dept.getDeptId());
            bo.setAgencyName(dept.getDeptName());
            bo.setModifyDate(dateNow);
            //
            // neu co flow
            //
/*
             Flow flow = fdhe.findByDept(deptId, bo.getFileType());
             if (flow != null) {
             //
             // Gui toi cho don vi & luong duoc chon
             //
             bo.setFlowId(flow.getFlowId());
             bo.setNodeId(null);
             bo.setNodeHistory("");

             } else {
             }
             */
            ProcedureDAOHE pdhe = new ProcedureDAOHE();
            Procedure pro = pdhe.findById(bo.getFileType());
            if (pro != null && pro.getDeadline() != null) {
                Date dt = DateTimeUtils.getAddDate(dateNow, 0, pro.getDeadline().intValue());
                bo.setDeadline(dt);
            }
            if (bo.getStatus().equals(Constants.FILE_STATUS.NEW_CREATE)
                    || bo.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT)) {
                bo.setStatus(Constants.FILE_STATUS.NEW);
                bo.setDisplayStatus(getFileStatusName(Constants.FILE_STATUS.NEW));
                if (processStatus.equals(Constants.FILE_STATUS.RECEIVED_REJECT)) {
                    ProcessDAOHE psdhe = new ProcessDAOHE();
                    Process p = psdhe.getProcessByAction(fileId, Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, processStatus, Constants.FILE_STATUS.NEW_CREATE);
                    if (p != null) {
                        p.setStatus(bo.getStatus());
                        p.setLastestComment("Doanh nghiệp gửi Sửa đổi bổ sung");
                        getSession().update(p);
                    }
                }
            }
            if (processStatus.equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)
                    || processStatus.equals(Constants.FILE_STATUS.RECEIVED_REJECT_TO_ADD)) {
                bo.setStatus(Constants.FILE_STATUS.NEW_TO_ADD);
                bo.setDisplayStatus(getFileStatusName(Constants.FILE_STATUS.NEW_TO_ADD));
                //update process
                ProcessDAOHE psdhe = new ProcessDAOHE();
                Process p = psdhe.getProcessByAction(fileId, Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, processStatus, Constants.FILE_STATUS.NEW_CREATE);
                if (p != null) {
                    p.setStatus(bo.getStatus());
                    p.setLastestComment("Doanh nghiệp gửi Sửa đổi bổ sung");
                    getSession().update(p);
                }
            }
            bo.setSendDate(dateNow);
            //140623 THIET LAP HAN TIEP NHAN HO SO
            ResourceBundle rb = ResourceBundle.getBundle("config");
            Procedure procedurebo = new Procedure();
            ProcedureDAOHE procedureDAOHE = new ProcedureDAOHE();
            procedurebo = procedureDAOHE.findById(bo.getFileType());
            int TN = 0;
            try {
                TN = Integer.parseInt(rb.getString(procedurebo.getDescription() + "_TN"));
            } catch (NumberFormatException ex) {
                log.error(ex.getMessage());
            }
            if (TN > 0) {
                bo.setDeadlineReceived(getDateWorkingTime(TN));
            }
            //Hiepvv Set defalt TitleEditATTP = TitleEdit AND ContentsEditATTP = ContentsEdit
            if (bo.getTitleEdit() != null) {
                bo.setTitleEditATTP(bo.getTitleEdit());
            }
            if (bo.getContentsEdit() != null) {
                bo.setContentsEditATTP(bo.getContentsEdit());
            }
            //140623 THIET LAP HAN TIEP NHAN HO SO
            getSession().update(bo);

            // Chay toi node dau tien
//            if (flow != null) {
//                bReturn = fdhe.moveDocumentToNextNode(businessId, businessName, userId, userName, bo.getFileId());
//            } else {
            // Xem trong file cau hinh co don vi la phong ban xu ly ko
//            String hql = "select p from ProcedureDepartment p where p.deptId = ? and p.procedureId = ? ";
//            Query query = getSession().createQuery(hql);
//            query.setParameter(0, deptId);
//            query.setParameter(1, bo.getFileType());
//            List lst = query.list();
            // gui den cho co quan truoc
            Process p = new Process();
            p.setObjectId(fileId);
            p.setObjectType(Constants.OBJECT_TYPE.FILES);
            p.setSendDate(dateNow);
            p.setSendGroup(businessName);
            p.setSendGroupId(businessId);
            p.setSendUserId(userId);
            p.setSendUser(userName);

            p.setReceiveDate(dateNow);
            //
            String deptCode = rb.getString("deptCode");
            if (deptCode == null) {
                deptCode = "ATTP";
            }
            DepartmentDAOHE dphe = new DepartmentDAOHE();
            Department dep = dphe.findByDeptCode(deptCode);
            if (dep != null) {
                p.setReceiveGroup(dep.getDeptName());
                p.setReceiveGroupId(dep.getDeptId());
            } else {
                p.setReceiveGroup(dept.getDeptName());
                p.setReceiveGroupId(dept.getDeptId());
            }
            /*
             if (lst != null && lst.size() > 0) {
             ProcedureDepartment pd = (ProcedureDepartment) lst.get(0);
             if (pd.getProcessDeptId() != null && pd.getProcessDeptId() > 0l) {
             p.setReceiveGroup(pd.getProcessDeptName());
             p.setReceiveGroupId(pd.getProcessDeptId());
             } else {
             p.setReceiveGroup(dept.getDeptName());
             p.setReceiveGroupId(dept.getDeptId());
             }
             } else {
             p.setReceiveGroup(dept.getDeptName());
             p.setReceiveGroupId(dept.getDeptId());
             }
             */
            p.setProcessType(Constants.PROCESS_TYPE.MAIN);
            //140404 binhnt53
            if (bo.getStatus().equals(Constants.FILE_STATUS.NEW)
                    || bo.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT)) {
                p.setProcessStatus(Constants.FILE_STATUS.NEW); // De xu ly
            } else {
                p.setProcessStatus(Constants.FILE_STATUS.NEW_TO_ADD); // De xu ly
            }
//                p.setProcessStatus(Constants.FILE_STATUS.NEW); // De xu ly
            //!140404 binhnt53
//                if (lst != null && lst.size() > 0) { //140405
            if (bo.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD) == true) {
                p.setStatus(Constants.FILE_STATUS.ASSIGNED);
            } else {
                p.setStatus(Constants.FILE_STATUS.NEW_CREATE);
            }
            p.setIsActive(Constants.ACTIVE_STATUS.ACTIVE);
            getSession().save(p);

//                if (lst != null && lst.size() > 0) {
//                    ProcedureDepartment pd = (ProcedureDepartment) lst.get(0);
//                    if (pd.getProcessDeptId() != null && pd.getProcessDeptId() > 0l) {
//                        Process pAuto = new Process();
//                        pAuto.setObjectId(fileId);
//                        pAuto.setObjectType(Constants.OBJECT_TYPE.FILES);
//                        pAuto.setSendDate(new Date());
//                        pAuto.setSendGroup(dept.getDeptName());
//                        pAuto.setSendGroupId(dept.getDeptId());
//
//                        pAuto.setReceiveDate(new Date());
//                        pAuto.setReceiveGroup(pd.getProcessDeptName());
//                        pAuto.setReceiveGroupId(pd.getProcessDeptId());
//
//                        //140404 binhnt53
//                        //                        pAuto.setProcessStatus(Constants.FILE_STATUS.NEW); // De xu ly
//                        if (bo.getStatus().equals(Constants.FILE_STATUS.NEW) || bo.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT)) {
//                            pAuto.setProcessStatus(Constants.FILE_STATUS.NEW); // De xu ly
//                        }
//                        if (bo.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {
//                            pAuto.setProcessStatus(Constants.FILE_STATUS.NEW_TO_ADD); // De xu ly
//                        }
//                        //!140404 binhnt53
//
//                        pAuto.setProcessType(Constants.PROCESS_TYPE.MAIN);
//                        pAuto.setStatus(0l);
//                        pAuto.setIsActive(1l);
//                        getSession().save(pAuto);
//                    }
//
//                }
//            }
        } catch (Exception en) {
            bReturn = false;
            log.error(en.getMessage());
        }
        return bReturn;
    }

    /**
     * Tìm hồ sơ để kí duyệt
     *
     * @param form
     * @param deptId
     * @param userId
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult searchFilesToSign(FilesForm form, Long deptId, Long userId, int start, int count, String sortField) {
        GridResult gr;
        try {
            String hql = " from Files f where f.isActive=1 and (f.isTemp = null or f.isTemp = 0 ) ";
            List lstParam = new ArrayList();
            if (form != null) {
                if (form.getFileCode() != null && !"".equals(form.getFileCode().trim())) {
                    hql += " AND lower(f.fileCode) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getFileCode().toLowerCase().trim()));
                }
                if (form.getAnnouncementNo() != null && !"".equals(form.getAnnouncementNo().trim())) {
                    hql += " AND lower(f.announcementNo) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getAnnouncementNo().toLowerCase().trim()));
                }
                if (form.getBusinessName() != null && !"".equals(form.getBusinessName().trim())) {
                    hql += " AND lower(f.businessName) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getBusinessName().toLowerCase().trim()));
                }
                if (form.getBusinessLicence() != null && !"".equals(form.getBusinessLicence().trim())) {
                    hql += " AND lower(f.businessLicence) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getBusinessLicence().toLowerCase().trim()));
                }
                if (form.getBusinessAddress() != null && !"".equals(form.getBusinessAddress().trim())) {
                    hql += " AND lower(f.businessAddress) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getBusinessAddress().toLowerCase().trim()));
                }
                if (form.getProductName() != null && !"".equals(form.getProductName().trim())) {
                    hql += " AND lower(f.productName) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getProductName().toLowerCase().trim()));
                }
                if (form.getNationName() != null && !"".equals(form.getNationName().trim())) {
                    hql += " AND lower(f.nationName) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getNationName().toLowerCase().trim()));
                }
                if (form.getManufactureName() != null && !"".equals(form.getManufactureName().trim())) {
                    hql += " AND lower(f.manufactureName) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getManufactureName().toLowerCase().trim()));
                }
                if (form.getManufactureAddress() != null && !"".equals(form.getManufactureAddress().trim())) {
                    hql += " AND lower(f.manufactureAddress) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getManufactureAddress().toLowerCase().trim()));
                }
                if (form.getMatchingTarget() != null && !"".equals(form.getMatchingTarget().trim())) {
                    hql += " AND lower(f.matchingTarget) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getMatchingTarget().toLowerCase().trim()));
                }

                if (form.getFileType() != null && form.getFileType() != -1) {
                    hql += " AND f.fileType = ? ";
                    lstParam.add(form.getFileType());
                }
            }

            hql += " and (f.status=? or f.status = ? or f.status = ? or f.status = ?)";
//                lstParam.add(Constants.FILE_STATUS.APPROVED);
//            lstParam.add(Constants.FILE_STATUS.SIGNING);
//            lstParam.add(Constants.FILE_STATUS.LICENSING);
//            lstParam.add(Constants.FILE_STATUS.SIGNED);
//            lstParam.add(Constants.FILE_STATUS.REJECT);
            lstParam.add(Constants.FILE_STATUS.APPROVED);
            lstParam.add(Constants.FILE_STATUS.SIGNED);
            if (deptId != null) {
                hql += " AND f.agencyId = ? ";
                lstParam.add(deptId);
            }

            Query countQuery = getSession().createQuery("select count(f) " + hql);
            Query query = getSession().createQuery("select f " + hql + " order by f.modifyDate desc ");
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }

            query.setFirstResult(start);
            query.setMaxResults(count);
            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            List lstResult = query.list();
            gr = new GridResult(total, lstResult);
            return gr;
        } catch (Exception ex) {
            log.error(ex.getMessage());;
            gr = new GridResult(0, null);
        }
        return gr;
    }

    /**
     * Tham dinh ho so - chuyen vien
     *
     * @param form
     * @param deptId ma don vi
     * @param deptName ten don vi
     * @param userId ma nguoi dung
     * @param userName ten nguoi dung
     * @return
     */
    public boolean onEvaluateOld(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());//lay thong tin chi tiet ho so
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else// Cap nhat trang thai ho so
             if ((file.getStatus() != null
                        && form.getStatus() != null)//141225 binhnt update phan quyen ho so tham dinh
                        && (file.getStatus().equals(Constants.FILE_STATUS.ASSIGNED)//da phan cong
                        || file.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE)//tra lai tham dinh lai
                        || file.getStatus().equals(Constants.FILE_STATUS.EVALUATED)//tra lai tham dinh lai
                        || file.getStatus().equals(Constants.FILE_STATUS.REVIEW_TO_ADD)//đã xem xét nội dung cv sđbs LDC
                        || file.getStatus().equals(Constants.FILE_STATUS.REVIEWED_TO_ADD)//Đã xem xét yêu cầu SĐBS LDC
                        || file.getStatus().equals(Constants.FILE_STATUS.APPROVE_TO_ADD)//đã phê duyệt nội dung thông báo VT
                        || file.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_ADD)//da tra lai bo sung ho so
                        || file.getStatus().equals(Constants.FILE_STATUS.RECEIVED_TO_ADD))) {//da tiep nhan ho so sdbs
                    Long processStatus = file.getStatus();
                    if (form.getProductType() != null
                            && form.getProductType() == 1L
                            && form.getLeaderReviewId() != null) {//gui lanh dao tham dinh
                        //150114 binhn53 add check TP k dc tham dinh ho so
                        //new binhnt update 08102015
                        UsersDAOHE udaohe = new UsersDAOHE();
                        List<String> lstLeader = new ArrayList<String>();
                        lstLeader.add(Constants.POSITION.LEADER_OF_STAFF_T);
                        lstLeader.add(Constants.POSITION.GDTT);
                        List<Users> lstUser = udaohe.findLstUserByLstPosition(deptId, lstLeader);
                        //!new binhnt update 08102015
                        //List<Users> lstUser = udaohe.findLstUserByPosition(deptId, Constants.POSITION.LEADER_OF_STAFF_T);//old 08102015 bbinhnt
                        if (lstUser != null) {
                            for (Users users : lstUser) {
                                if (users.getUserId() != null
                                        && form.getLeaderReviewId().equals(users.getUserId())) {
                                    return false;
                                }
                            }
                        }//!150114 binhn53 add check TP k dc tham dinh ho so
                        file.setLeaderEvaluateId(form.getLeaderReviewId());
                        file.setLeaderEvaluateName(form.getLeaderReviewName());
                        file.setLeaderReviewId(null);
                        file.setLeaderReviewName(null);
                        file.setLeaderApproveId(null);
                        file.setLeaderApproveName(null);
                    } else if (form.getLeaderReviewId() != null) {//gui lanh dao de xem xet
                        file.setLeaderReviewId(form.getLeaderReviewId());
                        file.setLeaderReviewName(form.getLeaderReviewName());
                        file.setLeaderEvaluateId(null);
                        file.setLeaderEvaluateName(null);
                        file.setLeaderApproveId(null);
                        file.setLeaderApproveName(null);
                    } else {
                        //new binhnt update 08102015
                        UsersDAOHE udaohe = new UsersDAOHE();
                        List<String> lstLeader = new ArrayList<String>();
                        lstLeader.add(Constants.POSITION.LEADER_OF_STAFF_T);
                        lstLeader.add(Constants.POSITION.GDTT);
                        Users ubo = null;
                        List<Users> lstLeaderOfDept = udaohe.findLstUserByLstPosition(deptId, lstLeader);
                        if (lstLeaderOfDept != null
                                && lstLeaderOfDept.size() > 0) {
                            ubo = lstLeaderOfDept.get(0);
                            file.setLeaderReviewId(ubo.getUserId());
                            file.setLeaderReviewName(ubo.getFullName());
                            file.setLeaderApproveId(null);
                            file.setLeaderApproveName(null);
                        }
                    }
                    file.setStatus(form.getStatus());
                    // neu tra lai de bo sung -> thiet lap bien have_temp = 1 de biet ma tao ra history khi sua doi
                    if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {
                        file.setHaveTemp(1l);
                    }
                    file.setDisplayStatus(getFileStatusName(form.getStatus()));
                    String dateTime = DateTimeUtils.convertDateToString(dateNow, "dd/MM/yyyy HH:mm");
                    //file.setStaffRequest(userName + " " + dateTime + ":\n" + form.getStaffRequest());
                    String prefix = userName + " " + dateTime + ":\n";
                    if (form.getStaffRequest()
                            != null && form.getStaffRequest().trim().length() > 0) {
                        file.setStaffRequest(prefix + form.getStaffRequest());
                    }
                    file.setModifyDate(dateNow);
                    if (form.getEffectiveDate() != null) {
                        file.setEffectiveDate(form.getEffectiveDate());
                    } else {
                        file.setEffectiveDate(Constants.EFFECTIVEDATE.THREE);
                    }
                    file.setIsTypeChange(form.getIsTypeChange());
                    file.setLastType(form.getLastType());
                    //Cap nhat process cu
                    ProcessDAOHE pdhe = new ProcessDAOHE();
                    Process p = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, processStatus, Constants.FILE_STATUS.NEW_CREATE);
                    if (p != null) {
                        p.setStatus(form.getStatus());
                        p.setLastestComment(form.getStaffRequest());
                        getSession().update(p);
                    }
                    //!Cap nhat process cu
                    if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED)
                            || form.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_ADD)) {
                        //tham xet dat, tham xet khong dat deu gui len cho to truong to tham xet xem xet
                        Process newP = new Process();
                        newP.setObjectId(form.getFileId());
                        newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                        newP.setProcessType(Constants.PROCESS_TYPE.MAIN);

                        newP.setSendDate(dateNow);
                        newP.setSendGroup(deptName);
                        newP.setSendGroupId(deptId);
                        newP.setSendUserId(userId);
                        newP.setSendUser(userName);

                        newP.setReceiveDate(dateNow);
                        if (form.getProductType()
                                != null && form.getProductType() == 1L
                                && file.getLeaderEvaluateId() != null) {
                            newP.setReceiveUserId(file.getLeaderEvaluateId());
                            newP.setReceiveUser(file.getLeaderEvaluateName());
                        } else if (file.getLeaderReviewId() != null) {
                            newP.setReceiveUserId(file.getLeaderReviewId());
                            newP.setReceiveUser(file.getLeaderReviewName());
                        }
                        newP.setReceiveGroup(deptName);
                        newP.setReceiveGroupId(deptId);

                        newP.setProcessStatus(form.getStatus()); // De xu ly
                        newP.setStatus(0l); // Moi den chua xu ly
                        newP.setIsActive(1l);
                        getSession().save(newP);
                    } else {
                        if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {
                            //tao thong bao yeu cau sdbs gui toi doanh nghiep
                            Process newP = new Process();
                            newP.setObjectId(form.getFileId());
                            newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                            newP.setProcessType(Constants.PROCESS_TYPE.MAIN);

                            newP.setSendDate(dateNow);
                            newP.setSendGroup(deptName);
                            newP.setSendGroupId(deptId);
                            newP.setSendUserId(userId);
                            newP.setSendUser(userName);

                            newP.setReceiveDate(dateNow);
                            ProcessDAOHE psdhe = new ProcessDAOHE();
                            Process pold = psdhe.getProcessByAction(
                                    form.getFileId(),
                                    Constants.Status.ACTIVE,
                                    Constants.OBJECT_TYPE.FILES,
                                    Constants.FILE_STATUS.NEW,
                                    Constants.FILE_STATUS.RECEIVED
                            );
                            if (pold != null) {
                                newP.setReceiveGroupId(pold.getSendGroupId());
                                newP.setReceiveGroup(pold.getSendGroup());
                                newP.setReceiveUserId(pold.getSendUserId());
                                newP.setReceiveUser(pold.getSendUser());
                            } else {
                                newP.setReceiveGroup(deptName);
                                newP.setReceiveGroupId(deptId);
                            }

                            newP.setProcessStatus(form.getStatus()); //De xu ly
                            newP.setStatus(0l); //Moi den chua xu ly
                            newP.setIsActive(1l);

                            getSession().save(newP);
                            //xóa bản ghi temp trước nếu có (lưu vào vùng lưu trữ)
                            updateSetNotLastIsTemp(file.getFileId());
                            //xóa bản ghi temp trước nếu có (lưu vào vùng lưu trữ)
                            ProcessCommentDAOHE pcdaohe = new ProcessCommentDAOHE();
                            int a = pcdaohe.updateSetNotLastIsTemp(file.getFileId());
                        }
                        if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATE_TO_ADD)) {//da soan du thao thong bao sdbs ho so                           
                            /*
                         ho so sau khi da tra lai chuyen vien de soan du thao tb sdbs
                         cv vao tao ban du thao
                         sau khi tao xong luu
                         gui noi dung cho lanh dao phong xem xet
                             */
                            Process newP = new Process();
                            newP.setObjectId(form.getFileId());
                            newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                            newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                            newP.setProcessStatus(form.getStatus()); // De xu ly
                            newP.setStatus(Constants.ACTIVE_STATUS.DEACTIVE);// Moi den chua xu ly-150120
                            newP.setIsActive(Constants.ACTIVE_STATUS.ACTIVE);//-150120

                            newP.setSendDate(dateNow);
                            newP.setSendGroup(deptName);
                            newP.setSendGroupId(deptId);
                            newP.setSendUserId(userId);
                            newP.setSendUser(userName);

                            newP.setReceiveDate(dateNow);
                            if (form.getLeaderReviewId() != null) {
                                newP.setReceiveUserId(form.getLeaderReviewId());
                                file.setLeaderReviewId(form.getLeaderReviewId());

                            }
                            if (form.getLeaderReviewName() != null) {
                                newP.setReceiveUser(form.getLeaderReviewName());
                                file.setLeaderReviewName(form.getLeaderReviewName());
                            }
                            newP.setReceiveGroup(deptName);
                            newP.setReceiveGroupId(deptId);
                            getSession().save(newP);

//cap nhat noi dung thong bao - tao noi dung thong bao
                            RequestComment rcbo = new RequestComment();
                            if (form.getStaffRequest() != null) {
                                rcbo.setContent(form.getStaffRequest());
                            } else {
                                rcbo.setContent("Chuyên viên chưa có nội dung.");
                            }
                            rcbo.setCreateBy(userId);
                            rcbo.setCreateDate(dateNow);
                            rcbo.setUserId(userId);
                            rcbo.setUserName(userName);
                            rcbo.setStatus(1L);
                            rcbo.setIsActive(1L);
                            rcbo.setGroupId(deptId);
                            rcbo.setGroupName(deptName);
                            rcbo.setObjectId(form.getFileId());
                            rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.TBSDBS);//-150120
                            rcbo.setIsLastChange(Constants.ACTIVE_STATUS.ACTIVE);//-150120
                            //!luu noi dung du thao
                            //u150119 binhnt53 update lại nội dung thông tin.
                            RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
                            RequestComment lastRQBo = rqdaohe.findLastRequestComment(file.getFileId(), Constants.ACTIVE_STATUS.ACTIVE);
                            if (lastRQBo != null) {
                                rcbo.setLastContent(lastRQBo.getContent());
                                lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                                getSession().update(lastRQBo);
                            }
                            getSession().save(rcbo);
//!u150119 binhnt53 update lại nội dung thông tin.
                        }
                        //hieptq update 230415
                        if ((form.getStatus()).equals(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE)) {
                            Process newP = new Process();
                            newP.setObjectId(form.getFileId());
                            newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                            newP.setSendDate(dateNow);
                            newP.setSendGroup(deptName);
                            newP.setSendGroupId(deptId);
                            newP.setSendUserId(userId);
                            newP.setSendUser(userName);
                            newP.setProcessStatus(p.getStatus()); // De xu ly
                            newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                            newP.setStatus(Constants.FILE_STATUS.NEW_CREATE); // Moi den chua xu ly
                            newP.setIsActive(Constants.ACTIVE_STATUS.ACTIVE);
                            newP.setReceiveDate(dateNow);
                            if (p != null) {
                                //lay process tham dinh ho so
                                ProcessDAOHE psdhe = new ProcessDAOHE();
                                Process pold = psdhe.getProcessByAction(
                                        form.getFileId(),
                                        Constants.Status.ACTIVE,
                                        Constants.OBJECT_TYPE.FILES,
                                        Constants.FILE_STATUS.EVALUATED,
                                        p.getStatus()
                                );
                                if (pold != null) {
                                    newP.setReceiveGroup(pold.getSendGroup());
                                    newP.setReceiveGroupId(pold.getSendGroupId());
                                    if (file != null
                                            && file.getStaffProcess() != null
                                            && file.getNameStaffProcess() != null) {
                                        newP.setReceiveUser(file.getNameStaffProcess());
                                        newP.setReceiveUserId(file.getStaffProcess());
                                    } else {
                                        newP.setReceiveUser(pold.getSendUser());
                                        newP.setReceiveUserId(pold.getSendUserId());
                                    }
                                } else {//141218u binhnt53 fix loi ho so lanh dao phong tra lai voi
                                    pold = psdhe.getProcessByAction(
                                            form.getFileId(),
                                            Constants.Status.ACTIVE,
                                            Constants.OBJECT_TYPE.FILES,
                                            Constants.FILE_STATUS.FEDBACK_TO_ADD,
                                            p.getStatus()
                                    );
                                    if (pold != null) {
                                        newP.setReceiveGroup(pold.getSendGroup());
                                        newP.setReceiveGroupId(pold.getSendGroupId());
                                        if (file != null
                                                && file.getStaffProcess() != null
                                                && file.getNameStaffProcess() != null) {
                                            newP.setReceiveUser(file.getNameStaffProcess());
                                            newP.setReceiveUserId(file.getStaffProcess());
                                        } else {
                                            newP.setReceiveUser(pold.getSendUser());
                                            newP.setReceiveUserId(pold.getSendUserId());
                                        }
                                    } else if (p != null) {// Gui lai cho chinh nguoi gui
                                        newP.setReceiveGroup(p.getSendGroup());
                                        newP.setReceiveGroupId(p.getSendGroupId());
                                        newP.setReceiveUser(p.getSendUser());
                                        newP.setReceiveUserId(p.getSendUserId());
                                    }
                                }
                            }
                            getSession().save(newP);
                        }
                    }
                    //insert noi dung tham dinh
                    if (form.getEvaluationRecordsForm() != null) {
                        EvaluationRecordsForm evaRecordForm = new EvaluationRecordsForm();

                        evaRecordForm.setCreateDate(dateNow);
                        evaRecordForm.setSendDate(file.getSendDate());
                        evaRecordForm.setBusinessName(file.getBusinessName());
                        evaRecordForm.setBusinessAddress(file.getBusinessAddress());
                        evaRecordForm.setProductName(file.getProductName());
                        evaRecordForm.setLegal(form.getEvaluationRecordsForm().getLegal());
                        evaRecordForm.setLegalContent(form.getEvaluationRecordsForm().getLegalContent());
                        evaRecordForm.setFoodSafetyQuality(form.getEvaluationRecordsForm().getFoodSafetyQuality());
                        evaRecordForm.setFoodSafetyQualityContent(form.getEvaluationRecordsForm().getFoodSafetyQualityContent());
                        evaRecordForm.setEffectUtility(form.getEvaluationRecordsForm().getEffectUtility());
                        evaRecordForm.setEffectUtilityContent(form.getEvaluationRecordsForm().getEffectUtilityContent());
                        evaRecordForm.setFilesStatus(file.getStatus());
                        evaRecordForm.setMainContent(file.getStaffRequest());
                        evaRecordForm.setFirstStaffId(userId);
                        evaRecordForm.setFirstStaffName(userName);
                        evaRecordForm.setSecondStaffId(userId);
                        evaRecordForm.setSecondStaffName(userName);
                        evaRecordForm.setThirdStaffId(userId);
                        evaRecordForm.setThirdStaffName(userName);
                        evaRecordForm.setLeederStaffId(userId);
                        evaRecordForm.setLeederStaffName(userName);
                        evaRecordForm.setFilesId(file.getFileId());

                        EvaluationRecords evaluationRecordsBo;
                        evaluationRecordsBo = evaRecordForm.convertToEntity();
                        getSession().save(evaluationRecordsBo);
                        boolean bInsertRC = insertRequestComment(
                                file.getFileId(),
                                form,
                                userId,
                                userName,
                                deptId,
                                deptName,
                                dateNow
                        );//binhnt53 150130
                    } else if (form.getEvaluationRecordsFormOnGrid() != null) {
                        EvaluationRecordsFormOnGrid evaRecordForm = new EvaluationRecordsFormOnGrid();

                        evaRecordForm.setCreateDate(dateNow);
                        evaRecordForm.setSendDate(file.getSendDate());
                        evaRecordForm.setBusinessName(file.getBusinessName());
                        evaRecordForm.setBusinessAddress(file.getBusinessAddress());
                        evaRecordForm.setProductName(file.getProductName());
                        evaRecordForm.setLegal(form.getEvaluationRecordsForm().getLegal());
                        evaRecordForm.setLegalContent(form.getEvaluationRecordsForm().getLegalContent());
                        evaRecordForm.setFoodSafetyQuality(form.getEvaluationRecordsForm().getFoodSafetyQuality());
                        evaRecordForm.setFoodSafetyQualityContent(form.getEvaluationRecordsForm().getFoodSafetyQualityContent());
                        evaRecordForm.setEffectUtility(form.getEvaluationRecordsForm().getEffectUtility());
                        evaRecordForm.setEffectUtilityContent(form.getEvaluationRecordsForm().getEffectUtilityContent());
                        evaRecordForm.setFilesStatus(file.getStatus());
                        evaRecordForm.setMainContent(file.getStaffRequest());
                        evaRecordForm.setFirstStaffId(userId);
                        evaRecordForm.setFirstStaffName(userName);
                        evaRecordForm.setSecondStaffId(userId);
                        evaRecordForm.setSecondStaffName(userName);
                        evaRecordForm.setThirdStaffId(userId);
                        evaRecordForm.setThirdStaffName(userName);
                        evaRecordForm.setLeederStaffId(userId);
                        evaRecordForm.setLeederStaffName(userName);
                        evaRecordForm.setFilesId(file.getFileId());

                        EvaluationRecords evaluationRecordsBo;
                        evaluationRecordsBo = evaRecordForm.convertToEntity();
                        getSession().save(evaluationRecordsBo);
                        boolean bInsertRC = insertRequestComment(
                                file.getFileId(),
                                form,
                                userId,
                                userName,
                                deptId,
                                deptName,
                                dateNow
                        );//binhnt53 150130
                    }

                    if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {//140721 binhnt
                        try {//140627 THIET LAP HAN SDBS HO SO
                            ResourceBundle rb = ResourceBundle.getBundle("config");
                            Procedure procedurebo;
                            ProcedureDAOHE procedureDAOHE = new ProcedureDAOHE();
                            procedurebo = procedureDAOHE.findById(file.getFileType());
                            int SD = 0;
                            try {
                                SD = Integer.parseInt(rb.getString(procedurebo.getDescription() + "_SD"));
                            } catch (NumberFormatException ex) {
                                log.error(ex.getMessage());
                            }
                            if (SD > 0) {
                                file.setDeadlineAddition(getDateWorkingTime(SD));
                            }
                        } catch (Exception ex) {
                            log.error(ex.getMessage());
                        }//!140627 THIET LAP HAN SDBS HO SO
                        //sms
                        /* disable send sms binhnt53 150205
                     MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                     String msg = "Ho so ma: " + file.getFileCode() + " cua doanh nghiep: " + file.getBusinessName() + " dang trong trang thai: da thong bao yeu cau sdbs";
                     msdhe.saveMessageSMS(userId, file.getUserCreateId(), msg);
                         */
                        //email
                        MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                        String msge = "Hồ sơ mã: " + file.getFileCode()
                                + " của doanh nghiệp: " + file.getBusinessName()
                                + " đang trong trạng thái: Đã thông báo yêu cầu sửa đổi bổ sung.";
                        msedhe.saveMessageEmail(userId, file.getUserCreateId(), msge);
                    }//!140721
                    update(file);
                } else {
                    log.error("Lỗi hệ thống: Phân quyền xử lý hồ sơ: " + file.getFileCode());
                    return false;
                }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    /**
     * 260214 doi chieu ho so -comparison -binhnt53
     *
     * @param form
     * @param deptId
     * @param deptName
     * @param userId
     * @param userName
     * @return
     */
    public boolean onComparison(FilesForm form, Long deptId, String deptName, Long userId, String userName) {//db140425
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else {
                Long isComparison = form.getIsComparison();
                if (isComparison.equals(1L)) {
                    file.setStatus(Constants.FILE_STATUS.COMPARED);
                    file.setDisplayStatus(getFileStatusName(Constants.FILE_STATUS.COMPARED));
                    form.setStatus(Constants.FILE_STATUS.COMPARED);
                } else {
                    file.setStatus(Constants.FILE_STATUS.COMPARED_FAIL);
                    file.setDisplayStatus(getFileStatusName(Constants.FILE_STATUS.COMPARED_FAIL));
                    form.setStatus(Constants.FILE_STATUS.COMPARED_FAIL);
                }
                file.setModifyDate(dateNow);
                file.setComparisonContent(form.getComparisonContent());
                file.setIsComparison(form.getIsComparison());
//cap nhat noi dung thong bao - tao noi dung thong bao
                RequestComment rcbo = new RequestComment();
                rcbo.setContent(form.getComparisonContent());
                rcbo.setCreateBy(userId);
                rcbo.setCreateDate(dateNow);
                rcbo.setUserId(userId);
                rcbo.setUserName(userName);
                rcbo.setStatus(1L);
                rcbo.setIsActive(1L);
                rcbo.setGroupId(deptId);
                rcbo.setGroupName(deptName);
                rcbo.setObjectId(form.getFileId());
                rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.TBDC);//-150120
                rcbo.setIsLastChange(Constants.ACTIVE_STATUS.ACTIVE);//-150120
//u150119 binhnt53
                RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
                RequestComment lastRQBo = rqdaohe.findLastRequestComment(file.getFileId(), Constants.ACTIVE_STATUS.ACTIVE);
                if (lastRQBo != null) {
                    rcbo.setLastContent(lastRQBo.getContent());
                    lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                    getSession().update(lastRQBo);
                }
                getSession().save(rcbo);
//!u150119 binhnt53
//cap nhat noi dung thong bao - tao noi dung thong bao
                //update process cu
                ProcessDAOHE pdhe = new ProcessDAOHE();
                if (isComparison.equals(1L)) {
                    Process oldP = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.ALERT_COMPARISON, Constants.FILE_STATUS.NEW_CREATE);
                    if (oldP != null) {
                        oldP.setStatus(form.getStatus());
                        if (form.getRequestCommentForm() != null) {
                            oldP.setLastestComment(form.getRequestCommentForm().getContent());
                        }
                        getSession().update(oldP);
                    } else {
                        oldP = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.COMPARED_FAIL, Constants.FILE_STATUS.NEW_CREATE);
                        if (oldP != null) {
                            oldP.setStatus(form.getStatus());
                            if (form.getRequestCommentForm() != null) {
                                oldP.setLastestComment(form.getRequestCommentForm().getContent());
                            }
                            getSession().update(oldP);
                        }
                    }
                } else {
                    Process oldP = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.COMPARED_FAIL, Constants.FILE_STATUS.NEW_CREATE);
                    if (oldP != null) {
                        oldP.setStatus(form.getStatus());
                        if (form.getRequestCommentForm() != null) {
                            oldP.setLastestComment(form.getRequestCommentForm().getContent());
                        }
                        getSession().update(oldP);
                    } else {
                        oldP = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.ALERT_COMPARISON, Constants.FILE_STATUS.NEW_CREATE);
                        if (oldP != null) {
                            oldP.setStatus(form.getStatus());
                            if (form.getRequestCommentForm() != null) {
                                oldP.setLastestComment(form.getRequestCommentForm().getContent());
                            }
                            getSession().update(oldP);
                        }
                    }
                }

                //tao process moi
                Process newP = new Process();
                newP.setObjectId(form.getFileId());
                newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                newP.setSendDate(dateNow);
                newP.setSendGroup(deptName);
                newP.setSendGroupId(deptId);
                newP.setSendUserId(userId);
                newP.setSendUser(userName);

                newP.setReceiveDate(dateNow);
                newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                newP.setProcessStatus(form.getStatus()); // De xu ly
                newP.setStatus(0L); // Moi den chua xu ly
                newP.setIsActive(1l);
                //tim lanh dao phan cong de gui
                if (isComparison.equals(1L)) {
                    newP.setReceiveGroup(deptName);
                    newP.setReceiveGroupId(deptId);
                } else {
                    //tim doanh nghiep
                    Process pReceive = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.NEW, Constants.FILE_STATUS.RECEIVED);
                    if (pReceive != null) {
                        newP.setReceiveUser(pReceive.getSendUser());
                        newP.setReceiveUserId(pReceive.getSendUserId());
                        newP.setReceiveGroup(pReceive.getSendGroup());
                        newP.setReceiveGroupId(pReceive.getSendGroupId());
                    } else {
                        newP.setReceiveGroup(deptName);
                        newP.setReceiveGroupId(deptId);
                    }
                }

                getSession().save(newP);
                getSession().update(file);
            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    //!comparison
    /**
     * Nhập kết quả xem xét
     *
     * @param form
     * @param deptId
     * @param deptName
     * @param userId
     * @param userName
     * @return
     */
    public boolean onReview(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else {
                // Cap nhat trang thai ho so
                file.setStatus(form.getStatus());
                file.setDisplayStatus(getFileStatusName(form.getStatus()));
                String dateTime = DateTimeUtils.convertDateToString(dateNow, "dd/MM/yyyy HH:mm");
                file.setLeaderStaffRequest(userName + " " + dateTime + ":\n" + form.getLeaderStaffRequest());
                file.setDisplayRequest(form.getLeaderStaffRequest());
                file.setModifyDate(dateNow);
                file.setEvaluateAddDate(dateNow);
                // Cap nhat process
                ProcessDAOHE pdhe = new ProcessDAOHE();
                Process p = pdhe.getWorkingProcess(form.getFileId(), Constants.OBJECT_TYPE.FILES, deptId, userId);
                if (p != null) {
                    p.setStatus(form.getStatus());
                    p.setLastestComment(form.getLeaderStaffRequest());
                    getSession().update(p);
                } else {
                    ProcessDAOHE psdhe = new ProcessDAOHE();
                    Process pold = psdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.FEDBACK_TO_ADD, Constants.FILE_STATUS.NEW_CREATE);
                    if (pold != null) {
                        pold.setStatus(form.getStatus());
                        pold.setLastestComment(form.getLeaderStaffRequest());
                        getSession().update(pold);
                    }
                }
                // Neu co luong thi chay theo luong
                if (file.getFlowId() != null && file.getFlowId() > 0) {
                    FlowDAOHE fdhe = new FlowDAOHE();
                    if (Constants.FILE_STATUS.REVIEWED.equals(form.getStatus())) {
                        fdhe.moveDocumentToNextNodeByAction(deptId, deptName, userId, userName, file.getFileId(), "xem xét");
                    } else {
                        //fdhe.moveDocumentToNextNodeByAction(deptId, deptName, userId, userName, file.getFileId(), "trả lại");
                        //fdhe.moveDocumentToPreviousNode(deptId, deptName, userId, userName, file.getFileId(), file.getPreviousNodeId());
                        Process newP = new Process();
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

                        if (form.getStatus().equals(Constants.FILE_STATUS.REVIEWED)) {
                            newP.setReceiveDate(dateNow);
                            newP.setReceiveGroup(file.getAgencyName());
                            newP.setReceiveGroupId(file.getAgencyId());
                        } else if (p != null) {
                            if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {//140722 - da thong bao yeu cau sdbs
                                newP.setObjectId(form.getFileId());
                                newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                                newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                                newP.setSendDate(dateNow);
                                newP.setSendGroup(deptName);
                                newP.setSendGroupId(deptId);
                                newP.setSendUserId(userId);
                                newP.setSendUser(userName);
                                //
                                newP.setReceiveDate(dateNow);
                                newP.setReceiveGroup(deptName);
                                newP.setReceiveGroupId(deptId);

                                newP.setProcessStatus(form.getStatus()); //De xu ly
                                newP.setStatus(0l); //Moi den chua xu ly
                                newP.setIsActive(1l);
                                //xóa bản ghi temp trước nếu có (lưu vào vùng lưu trữ)
                                updateSetNotLastIsTemp(file.getFileId());//
                            } else {
                                //lay process tham dinh ho so
                                ProcessDAOHE psdhe = new ProcessDAOHE();
                                Process pold = psdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.FEDBACK_TO_ADD, Constants.FILE_STATUS.NEW_CREATE);
                                if (pold != null) {
                                    newP.setReceiveDate(dateNow);
                                    newP.setReceiveGroup(pold.getSendGroup());
                                    newP.setReceiveGroupId(pold.getSendGroupId());
                                    newP.setReceiveUser(pold.getSendUser());
                                    newP.setReceiveUserId(pold.getSendUserId());
                                } else {
                                    newP.setReceiveDate(dateNow);
                                    newP.setReceiveGroup(p.getSendGroup());
                                    newP.setReceiveGroupId(p.getSendGroupId());
                                    newP.setReceiveUser(p.getSendUser());
                                    newP.setReceiveUserId(p.getSendUserId());
                                }
                            }
                        }
                        getSession().save(newP);
                    }
                } else {// Neu khong co luong thi tu xu thoi :-)
                    // Xem xet oke, gui tiep cho cho lanh dao co quan phe duyet
                    Process newP = new Process();
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
                    if (form.getStatus().equals(Constants.FILE_STATUS.REVIEWED)) {
                        // Gui toi chinh don vi quan ly de phe duyet
                        newP.setReceiveDate(dateNow);
                        newP.setReceiveGroup(file.getAgencyName());
                        newP.setReceiveGroupId(file.getAgencyId());
                    } else {
                        if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {//140722 - da thong bao yeu cau sdbs
                            newP.setReceiveDate(dateNow);
                            newP.setReceiveGroup(deptName);
                            newP.setReceiveGroupId(deptId);
                            //xóa bản ghi temp trước nếu có (lưu vào vùng lưu trữ)
                            updateSetNotLastIsTemp(file.getFileId());//
                        }
                        if (form.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_ADD)) {// Tra lai cho chuyen vien tham dinh
                            if (p != null) {
                                //lay process tham dinh ho so
                                ProcessDAOHE psdhe = new ProcessDAOHE();
                                Process pold = psdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.FEDBACK_TO_ADD, Constants.FILE_STATUS.NEW_CREATE);
                                if (pold != null) {
                                    newP.setReceiveDate(dateNow);
                                    newP.setReceiveGroup(pold.getSendGroup());
                                    newP.setReceiveGroupId(pold.getSendGroupId());
                                    newP.setReceiveUser(pold.getSendUser());
                                    newP.setReceiveUserId(pold.getSendUserId());
                                } else {
                                    // Gui lai cho chinh nguoi gui
                                    newP.setReceiveDate(dateNow);
                                    newP.setReceiveGroup(p.getSendGroup());
                                    newP.setReceiveGroupId(p.getSendGroupId());
                                    newP.setReceiveUser(p.getSendUser());
                                    newP.setReceiveUserId(p.getSendUserId());
                                }
                            }
                        }
                        if (form.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE)) {// Tra lai cho chuyen vien tham dinh
                            if (p != null) {
                                //lay process tham dinh ho so
                                ProcessDAOHE psdhe = new ProcessDAOHE();
                                Process pold = psdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.EVALUATED, Constants.FILE_STATUS.NEW_CREATE);
                                if (pold != null) {
                                    newP.setReceiveDate(dateNow);
                                    newP.setReceiveGroup(pold.getSendGroup());
                                    newP.setReceiveGroupId(pold.getSendGroupId());
                                    newP.setReceiveUser(pold.getSendUser());
                                    newP.setReceiveUserId(pold.getSendUserId());
                                } else {//141218u binhnt53 update fix loi ho so khi cv tham dinh k dat tra lai cho chuyen vien
                                    pold = psdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.FEDBACK_TO_ADD, Constants.FILE_STATUS.NEW_CREATE);
                                    if (pold != null) {
                                        newP.setReceiveDate(dateNow);
                                        newP.setReceiveGroup(pold.getSendGroup());
                                        newP.setReceiveGroupId(pold.getSendGroupId());
                                        newP.setReceiveUser(pold.getSendUser());
                                        newP.setReceiveUserId(pold.getSendUserId());
                                    } else if (p != null) {
                                        // Gui lai cho chinh nguoi gui
                                        newP.setReceiveDate(dateNow);
                                        newP.setReceiveGroup(p.getSendGroup());
                                        newP.setReceiveGroupId(p.getSendGroupId());
                                        newP.setReceiveUser(p.getSendUser());
                                        newP.setReceiveUserId(p.getSendUserId());
                                    }
                                }
                            }
                        }
                        if (form.getStatus().equals(Constants.FILE_STATUS.REVIEWED_TO_ADD)) {
                            //lay process tham dinh ho so
                            ProcessDAOHE psdhe = new ProcessDAOHE();
                            Process pold = psdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.FEDBACK_TO_ADD, Constants.FILE_STATUS.NEW_CREATE);
                            if (pold != null) {
                                newP.setReceiveDate(dateNow);
                                newP.setReceiveGroup(pold.getSendGroup());
                                newP.setReceiveGroupId(pold.getSendGroupId());
                                newP.setReceiveUser(pold.getSendUser());
                                newP.setReceiveUserId(pold.getSendUserId());
                            } else {
                                newP.setReceiveDate(dateNow);
                                newP.setReceiveGroup(p.getSendGroup());
                                newP.setReceiveGroupId(p.getSendGroupId());
                                newP.setReceiveUser(p.getSendUser());
                                newP.setReceiveUserId(p.getSendUserId());
                            }
                        }
                    }
                    getSession().save(newP);
                }
                //insert noi dung tham dinh
                if (form.getEvaluationRecordsForm() != null) {
                    EvaluationRecordsDAOHE evaluationRecordsDAOHE = new EvaluationRecordsDAOHE();
                    EvaluationRecords evaluationRecords = evaluationRecordsDAOHE.findFilesByFileId(file);
                    if (evaluationRecords != null) {
                        evaluationRecords.setSendDate(file.getSendDate());
                        evaluationRecords.setLegalL(form.getEvaluationRecordsForm().getLegalL());
                        evaluationRecords.setLegalContentL(form.getEvaluationRecordsForm().getLegalContentL());
                        evaluationRecords.setFoodSafetyQualityL(form.getEvaluationRecordsForm().getFoodSafetyQualityL());
                        evaluationRecords.setFoodSafetyQualityContentL(form.getEvaluationRecordsForm().getFoodSafetyQualityContentL());
                        evaluationRecords.setEffectUtilityL(form.getEvaluationRecordsForm().getEffectUtilityL());
                        evaluationRecords.setEffectUtilityContentL(form.getEvaluationRecordsForm().getEffectUtilityContentL());
                        evaluationRecords.setFilesStatusL(file.getStatus());
                        evaluationRecords.setMainContentL(file.getLeaderRequest());
                        getSession().update(evaluationRecords);
                    }
                }

                //140721 binhnt
                if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {
                    try {//140627 THIET LAP HAN SDBS HO SO
                        ResourceBundle rb = ResourceBundle.getBundle("config");
                        Procedure procedurebo;
                        ProcedureDAOHE procedureDAOHE = new ProcedureDAOHE();
                        procedurebo = procedureDAOHE.findById(file.getFileType());
                        int SD = 0;
                        try {
                            SD = Integer.parseInt(rb.getString(procedurebo.getDescription() + "_SD"));
                        } catch (NumberFormatException ex) {
                            log.error(ex.getMessage());
                        }
                        if (SD > 0) {
                            file.setDeadlineAddition(getDateWorkingTime(SD));
                        }
                    } catch (Exception ex) {
                        log.error(ex.getMessage());
                    }//!140627 THIET LAP HAN SDBS HO SO
                    //sms
                    MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                    String msg = "";
                    msg = "Ho so ma: " + file.getFileCode() + " cua doanh nghiep: " + file.getBusinessName() + " dang trong trang thai: da thong bao yeu cau sdbs";
                    msdhe.saveMessageSMS(userId, file.getUserCreateId(), msg);
                    //email
                    MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                    String msge = "";
                    msge = "Hồ sơ mã: " + file.getFileCode() + " của doanh nghiệp: " + file.getBusinessName() + " đang trong trạng thái: Đã thông báo yêu cầu sửa đổi bổ sung.";
                    msedhe.saveMessageEmail(userId, file.getUserCreateId(), msge);
                }//!140721
                update(file);
            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    /**
     *
     * @param form
     * @param deptId
     * @param deptName
     * @param userId
     * @param userName
     * @return
     */
    public boolean onUpdateProcessToAddition(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        Files file = findById(form.getFileId());
        if (file == null) {
            bReturn = false;
        } else {
            Long objectId = file.getFileId();
            ProcessDAOHE psdhe = new ProcessDAOHE();
            List<Process> lstPs = psdhe.getProcessOfOffice(objectId, Constants.OBJECT_TYPE.FILES);
            if (!lstPs.isEmpty()) {
                for (Process process : lstPs) {
                    if (process.getStatus().equals(Constants.FILE_STATUS.ALERT_COMPARISON) && process.getStatus().equals(Constants.FILE_STATUS.ALERT_COMPARISON)) {
                    }
                }
            }
        }
        return bReturn;
    }

    /**
     * Phê duyệt hồ sơ
     *
     * @param form
     * @param deptId
     * @param deptName
     * @param userId
     * @param userName
     * @return
     */
    public boolean onApprove(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
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
                    Process oldP = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, processStatus, Constants.FILE_STATUS.NEW_CREATE);
                    if (oldP != null) {
                        oldP.setStatus(form.getStatus());
                        oldP.setLastestComment(form.getLeaderRequest());
                        getSession().update(oldP);
                    }
                    //tạo process mới với xử lý hiển tại
                    Process newP = new Process();
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
                        newP.setReceiveGroup(deptName);
                        newP.setReceiveGroupId(deptId);
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
                        if (!pro.getCode().equals("01") && !pro.getCode().equals("02") && !pro.getDescription().equals("announcementFile05")) {
                            file.setIsFee(0l);
                        }

                        /* đổi luồng binhnt53 140914
                         // Phe duyet oke, chuyen ho so xuong cho van thu tra ho so
                         Process newP = new Process();
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
                         //lay process cua VT tiep nhan
                         Process pold = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.RECEIVED, Constants.FILE_STATUS.ASSIGNED);
                         if (pold != null) {
                         newP.setReceiveGroup(pold.getSendGroup());
                         newP.setReceiveGroupId(pold.getSendGroupId());
                         newP.setReceiveUser(pold.getSendUser());
                         newP.setReceiveUserId(pold.getSendUserId());
                         } else {
                         newP.setReceiveUser(deptName);
                         newP.setReceiveUserId(deptId);
                         }
                         RequestComment rcbo = new RequestComment();
                         rcbo.setContent(form.getLeaderRequest());
                         file.setComparisonContent(form.getLeaderRequest());
                         file.setIsFee(0L);//bay gio tinh phi giay cong bo
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
                         rcbo.setRequestType("YKPD");
                         getSession().save(rcbo);
                         getSession().save(newP);
                         */
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
                    //onCreatePaper(form, deptId, deptName, userId, userName);
                    if (form.getStatus().equals(Constants.FILE_STATUS.APPROVED)) {
                        ProcedureDAOHE pcdaohe = new ProcedureDAOHE();
                        Procedure procedure = pcdaohe.findById(file.getFileType());
                        if (procedure != null && procedure.getProcedureId() > 0) {
                            if (!procedure.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01) && !procedure.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03)) {
                                //sms
                                MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                                String msg = "Ho so ma: " + file.getFileCode() + " cua doanh nghiep: " + file.getBusinessName() + " dang trong trang thai: da phe duyet, doanh nghiep luu y dong le phi cap so de duoc cap ban cong bo";
                                msdhe.saveMessageSMS(userId, file.getUserCreateId(), msg);
                                //email
                                MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                                String msge = "";
                                msge = "Hồ sơ mã: " + file.getFileCode() + " của doanh nghiệp: " + file.getBusinessName() + " đang trong trạng thái: đã phê duyệt, doanh nghiệp lưu ý đóng lệ phí cấp số để được cấp bản công bố";
                                msedhe.saveMessageEmail(userId, file.getUserCreateId(), msge);
                            }
                        }
                    }
                } else {
                    return false;
                }
            }            
        } catch (Exception ex) {
            log.error(ex.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    /**
     * tạo bản công bố
     *
     * @param form
     * @param deptId
     * @param deptName
     * @param userId
     * @param userName
     * @return
     */
    public boolean onCreatePaper(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        try {
            Date dateNow = getSysdate();
            Files file = findById(form.getFileId());
            file.setModifyDate(dateNow);
            if (file != null) {
                if (form.getStatus().equals(Constants.FILE_STATUS.APPROVED)) {
                    file.setApproveDate(dateNow);
                    //Hiepvv 1403 Lay y kien cua LDC in len noi dung cong van
                    if (form.getLeaderRequest() != null && !form.getLeaderRequest().isEmpty()) {
                        file.setContentsEditATTP(form.getLeaderRequest());
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
                                    String strReceiptNo = getNewReceiptNo(file.getAgencyId(), file.getFileType());
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
//                                if (cthe.validateReceiptNo(arpForm.getReceiptNo())) { //validate so giay tiep nhan
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
//                                } else {
//                                    return false;
//                                }
                                        }
                                        getSession().beginTransaction().commit();
                                        return true;
                                    } catch (Exception ex) {
                                        log.error(ex.getMessage());
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
                        } else if (file.getTestRegistrationId() != null) {//Giấy xác nhận đạt yêu cầu nhập khẩu của cơ quan kiểm tra Nhà nước về chất lượng thực phẩm nhập khẩu
                            ConfirmImportSatistPaperForm cispForm = new ConfirmImportSatistPaperForm();
                            cispForm.setTestAgencyName(form.getTestRegistration().getTestAgency());
                            cispForm.setTestAdd(form.getTestRegistration().getTestAdd());
                            cispForm.setExportBusinessName(form.getTestRegistration().getExportBusinessName());
                            cispForm.setExportBusinessAdd(form.getTestRegistration().getExportBusinessAdd());
                            cispForm.setExportBusinessMail(form.getTestRegistration().getExportBusinessMail());
                            cispForm.setExportBusinessTel(form.getTestRegistration().getExportBusinessTel());
                            cispForm.setExportBusinessFax(form.getTestRegistration().getExportBusinessFax());
                            cispForm.setExportContractCode(form.getTestRegistration().getExportContractCode());
                            cispForm.setExportContractDate(form.getTestRegistration().getExportContractDate());
                            cispForm.setExportLadingCode(form.getTestRegistration().getExportLadingCode());
                            cispForm.setExportLadingDate(form.getTestRegistration().getExportLadingDate());
                            cispForm.setExportPort(form.getTestRegistration().getExportPort());
                            cispForm.setImportBusinessName(form.getTestRegistration().getImportBusinessName());
                            cispForm.setImportBusinessAddress(form.getTestRegistration().getImportBusinessAddress());
                            cispForm.setImportBusinessEmail(form.getTestRegistration().getImportBusinessEmail());
                            cispForm.setImportBusinessTel(form.getTestRegistration().getImportBusinessTel());
                            cispForm.setImportBusinessFax(form.getTestRegistration().getImportBusinessFax());
                            cispForm.setImportPort(form.getTestRegistration().getImportPort());
                            cispForm.setImportDate(form.getTestRegistration().getImportDate());
                            cispForm.setProductName(form.getTestRegistration().getProductName());
                            cispForm.setProductDescription(form.getTestRegistration().getProductDescription());
                            cispForm.setProductCode(form.getTestRegistration().getProductCode());
                            cispForm.setProductOrigin(form.getTestRegistration().getProductOrigin());
                            cispForm.setProductAmount(form.getTestRegistration().getProductAmount());
                            cispForm.setProductWeight(form.getTestRegistration().getProductWeight());
                            cispForm.setProductValue(form.getTestRegistration().getProductValue());
                            cispForm.setGatheringAdd(form.getTestRegistration().getGatheringAdd());
                            cispForm.setTestDate(form.getTestRegistration().getTestDate());
                            cispForm.setBusinessRepresent(form.getTestRegistration().getBusinessRepresent());
                            cispForm.setBusinessSignAdd(form.getTestRegistration().getBusinessSignAdd());
                            cispForm.setBusinessSigndate(form.getTestRegistration().getBusinessSigndate());
                            cispForm.setAgencyRepresent(form.getTestRegistration().getAgencyRepresent());
                            cispForm.setAgencySignAdd(form.getTestRegistration().getAgencySignAdd());
                            cispForm.setAgencySigndate(form.getTestRegistration().getAgencySigndate());
                            cispForm.setStandardTargetNo(form.getTestRegistration().getStandardTargetNo());
                            cispForm.setStandardTargetDate(form.getTestRegistration().getStandardTargetDate());
                            cispForm.setReleaseDocumentNo(form.getTestRegistration().getReleaseDocumentNo());
                            cispForm.setReleaseDocumentDate(form.getTestRegistration().getReleaseDocumentDate());
                            try {
                                ConfirmImportSatistPaperDAOHE cthe = new ConfirmImportSatistPaperDAOHE();
                                if (cthe.isDuplicate(cispForm) == true) {
                                    return false;
                                } else {
                                    Long ObjId = cispForm.getConfirmImportSatistPaperId();
                                    if (ObjId == null) {
                                        ConfirmImportSatistPaper bo = cispForm.convertToEntity();
                                        getSession().save(bo);
                                        file.setConfirmImportSatistPaperId(bo.getConfirmImportSatistPaperId());
                                        getSession().update(file);
                                    } else {
                                        ConfirmImportSatistPaper bo = cispForm.convertToEntity();
                                        getSession().update(bo);
                                        file.setConfirmImportSatistPaperId(bo.getConfirmImportSatistPaperId());
                                        getSession().update(file);
                                    }
                                    getSession().getTransaction().commit();
                                    return true;

                                }
                            } catch (Exception ex) {
                                log.error(ex.getMessage());
                                return false;
                            }
                        }
                    }

                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Cấp mã hồ sơ
     *
     * @param fileType
     * @return
     */
    public String getNewFileCode(Long fileType) {
        ProcedureDAOHE cdhe = new ProcedureDAOHE();
        Procedure type = cdhe.findById(fileType);

//        String hql = "select count(f) from Files f where f.fileType = ? and (f.isTemp = null or f.isTemp = 0 ) ";
        String hql = "select count(f) from Files f where (f.isTemp = null or f.isTemp = 0 ) ";//u150129 dund cap nhat ma ho so
//        String hql = "select count(f) from Files f where f.fileType = ? ";
        Query query = getSession().createQuery(hql);
//        query.setParameter(0, fileType);
        int nCount = (int) (long) (Long) query.uniqueResult();
        nCount += 1;
        Date currentDate = new Date();
        try {
            currentDate = getSysdate();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        String fileCode = DateTimeUtils.convertDateToString(currentDate, "yy.MM.") + type.getCode() + "." + nCount;
        return fileCode;
    }

    /*
     * Thuc hien clone 1 ho so sang ho so khac de luu phien ban, clone tat ca thong tin lien quan den ho s
     */
    /**
     *
     * @param fileId
     * @return
     */
    public FilesForm getNewCloneFiles(Long fileId) {
        FilesForm originalFiles = getFilesFullDetail(fileId);
        FilesForm cloneFiles = new FilesForm(originalFiles.convertToEntity().cloneEntity());

        if (originalFiles.getAnnouncement() != null) {
            Announcement cloneAnn = originalFiles.getAnnouncement().convertToEntity().cloneEntity();
            cloneFiles.setAnnouncement(new AnnouncementForm(cloneAnn));
        }

        if (originalFiles.getDetailProduct() != null) {
            DetailProduct cloneDetail = originalFiles.getDetailProduct().convertToEntity().cloneEntity();
            cloneFiles.setDetailProduct(new DetailProductForm(cloneDetail));
        }

        if (originalFiles.getReIssueForm() != null) {
            ReIssueForm cloneReIssue = originalFiles.getReIssueForm().convertToEntity().cloneEntity();
            cloneFiles.setReIssueForm(new ReIssueFormForm(cloneReIssue));
        }

        if (originalFiles.getTestRegistration() != null) {
            TestRegistration cloneTest = originalFiles.getTestRegistration().convertToEntity().cloneEntity();
            cloneFiles.setTestRegistration(new TestRegistrationForm(cloneTest));
        }
        List lstMainlyTarget = new ArrayList();
        if (originalFiles.getLstMainlyTarget() != null && !originalFiles.getLstMainlyTarget().isEmpty()) {
            for (MainlyTarget item : originalFiles.getLstMainlyTarget()) {
                MainlyTarget cloneItem = item.cloneEntity();
                lstMainlyTarget.add(cloneItem);
            }
        }
        cloneFiles.setLstMainlyTarget(lstMainlyTarget);

        List lstBioTarget = new ArrayList();
        if (originalFiles.getLstBioTarget() != null && !originalFiles.getLstBioTarget().isEmpty()) {
            for (ProductTarget item : originalFiles.getLstBioTarget()) {
                ProductTarget cloneItem = item.cloneEntity();
                lstBioTarget.add(cloneItem);
            }
        }
        cloneFiles.setLstBioTarget(lstBioTarget);

        List lstHeavyTarget = new ArrayList();
        if (originalFiles.getLstHeavyMetal() != null && !originalFiles.getLstHeavyMetal().isEmpty()) {
            for (ProductTarget item : originalFiles.getLstHeavyMetal()) {
                ProductTarget cloneItem = item.cloneEntity();
                lstHeavyTarget.add(cloneItem);
            }
        }
        cloneFiles.setLstHeavyMetal(lstHeavyTarget);

        List lstChemicalTarget = new ArrayList();
        if (originalFiles.getLstChemical() != null && !originalFiles.getLstChemical().isEmpty()) {
            for (ProductTarget item : originalFiles.getLstChemical()) {
                ProductTarget cloneItem = item.cloneEntity();
                lstChemicalTarget.add(cloneItem);
            }
        }
        cloneFiles.setLstChemical(lstChemicalTarget);

        String lstAttachLabel = ";";
        List lstAttachs = new ArrayList();
        if (originalFiles.getLstAttachs() != null && !originalFiles.getLstAttachs().isEmpty()) {
            int i = 0;
            for (VoAttachs item : originalFiles.getLstAttachs()) {
                VoAttachs cloneItem = item.cloneEntity();
                lstAttachs.add(cloneItem);
                if (item.getObjectType().equals(Constants.OBJECT_TYPE.FILES_LABEL)) {
                    lstAttachLabel += (i + ";");
                }
                i++;
            }
        }
        cloneFiles.setLstAttachs(lstAttachs);
        cloneFiles.setLstAttachLabel(lstAttachLabel);

        List lstQualityPlan = new ArrayList();
        if (originalFiles.getLstQualityControl() != null && !originalFiles.getLstQualityControl().isEmpty()) {
            for (QualityControlPlan item : originalFiles.getLstQualityControl()) {
                QualityControlPlan cloneItem = item.cloneEntity();
                lstQualityPlan.add(cloneItem);
            }
        }
        cloneFiles.setLstQualityControl(lstQualityPlan);

        return cloneFiles;
    }

    private boolean checkMainlyTargetIsModify(MainlyTarget target, List<MainlyTarget> lstItem) {
        if (target.getMainlyTargetId() == null || target.getMainlyTargetId() <= 0l) {
            return false;
        }

        if (lstItem == null) {
            return false;
        }
        if (lstItem.isEmpty()) {
            return false;
        }

        for (MainlyTarget item : lstItem) {
            if (target.getMainlyTargetId().equals(item.getMainlyTargetId()) || target.getMainlyTargetId().equals(item.getOriginalId())) {
                if (item.getMeetLevel() != null && target.getMeetLevel() != null) {
                    if (!item.getMeetLevel().equals(target.getMeetLevel())) {
                        return true;
                    }
                }
                if (item.getPublishLevel() != null && target.getPublishLevel() != null) {
                    if (!item.getPublishLevel().equals(target.getPublishLevel())) {
                        return true;
                    }
                }
                if (item.getTargetName() != null && target.getTargetName() != null) {
                    if (!item.getTargetName().equals(target.getTargetName())) {
                        return true;
                    }
                }
                if (item.getUnitId() != null && target.getUnitId() != null) {
                    if (!item.getUnitId().equals(target.getUnitId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkProductTargetIsModify(ProductTarget target, List<ProductTarget> lstItem) {
        if (target.getProductTargetId() == null || target.getProductTargetId() <= 0l) {
            return false;
        }

        if (lstItem == null) {
            return false;
        }
        if (lstItem.isEmpty()) {
            return false;
        }

        for (ProductTarget item : lstItem) {
            if (target.getProductTargetId().equals(item.getProductTargetId()) || target.getProductTargetId().equals(item.getOriginalId())) {
                if (item.getMaxLevel() != null && target.getMaxLevel() != null) {
                    if (!item.getMaxLevel().equals(target.getMaxLevel())) {
                        return true;
                    }
                }
                if (item.getTargetName() != null && target.getTargetName() != null) {
                    if (!item.getTargetName().equals(target.getTargetName())) {
                        return true;
                    }
                }
                if (item.getUnitId() != null && target.getUnitId() != null) {
                    if (!item.getUnitId().equals(target.getUnitId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkAttachIsModify(VoAttachs target, List<VoAttachs> lstItem) {
        if (target.getAttachId() == null || target.getAttachId() <= 0l) {
            return false;
        }

        if (lstItem == null) {
            return false;
        }
        if (lstItem.isEmpty()) {
            return false;
        }

        for (VoAttachs item : lstItem) {
            if (target.getAttachId().equals(item.getAttachId()) || target.getAttachId().equals(item.getOriginalId())) {
                if (item.getAttachDes() != null && target.getAttachDes() != null) {
                    if (!item.getAttachDes().equals(target.getAttachDes())) {
                        return true;
                    }
                }
                if (item.getCategoryName() != null && target.getCategoryName() != null) {
                    if (!item.getCategoryName().equals(target.getCategoryName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * luu danh sach chi tieu chinh
     */
    private void saveMainlytarget(List<MainlyTarget> lstItems, Long fileId) {
        List<Long> lstIdOfMainlyTargets = new ArrayList();
        if (lstItems != null && !lstItems.isEmpty()) {
            Long previousVersionId = getLastVersionIdOfFile(fileId);
            List lstMainlyTarget = null;
            if (previousVersionId != null) {
                lstMainlyTarget = getMainlyTargetOfFile(previousVersionId);
            }
            for (MainlyTarget item : lstItems) {
                if (item == null) {
                    continue;
                }
                item.setFileId(fileId);//
                if (checkMainlyTargetIsModify(item, lstMainlyTarget)) {
                    item.setIsTemp(1l);
                } else {
                    item.setIsTemp(0l);
                }
                if (item.getMainlyTargetId() != null) {
                    getSession().merge(item);
                } else {
                    getSession().save(item);
                }
                Long id = item.getMainlyTargetId();
                lstIdOfMainlyTargets.add(id);
            }
        }

        String hql = "delete from MainlyTarget m where m.fileId = :fileId ";
        if (!lstIdOfMainlyTargets.isEmpty()) {
            hql += " and m.mainlyTargetId not in (:lstIdOfMainlyTargets)";
        }
        Query query = getSession().createQuery(hql);
        query.setParameter("fileId", fileId);
        if (!lstIdOfMainlyTargets.isEmpty()) {
            query.setParameterList("lstIdOfMainlyTargets", lstIdOfMainlyTargets);
        }
        query.executeUpdate();
    }

    /**
     * luu danh sach chi tieu san pham
     */
    private void saveProductTarget(List<ProductTarget> lstItems, Long fileId, Long type) {
        List<Long> lstUpdateItems = new ArrayList();
        Long previousVersionId = getLastVersionIdOfFile(fileId);
        List lstProductTarget = null;
        if (previousVersionId != null) {
            lstProductTarget = getProductTargetOfFile(previousVersionId);
        }
        if (lstItems != null && !lstItems.isEmpty()) {
            for (ProductTarget item : lstItems) {
                if (item == null) {
                    continue;
                }
                item.setFileId(fileId);
                if (checkProductTargetIsModify(item, lstProductTarget)) {
                    item.setIsTemp(1l);
                } else {
                    item.setIsTemp(0l);
                }
                if (item.getProductTargetId() != null) {
                    getSession().merge(item);
                } else {
                    getSession().save(item);
                }
                Long id = item.getProductTargetId();
                lstUpdateItems.add(id);
            }
        }

        String hql = "delete from ProductTarget m where m.fileId = :fileId and m.targetType=:type";
        if (!lstUpdateItems.isEmpty()) {
            hql += " and m.productTargetId not in (:lstUpdateItems)";
        }
        Query query = getSession().createQuery(hql);
        query.setParameter("type", type);
        query.setParameter("fileId", fileId);
        if (!lstUpdateItems.isEmpty()) {
            query.setParameterList("lstUpdateItems", lstUpdateItems);
        }
        query.executeUpdate();
    }

    /**
     * Luu cac file attach
     */
    private void saveAttachs(List<VoAttachs> lstItems, Long fileId, String lstAttachLabel) {
        List<Long> lstIdOfAttachs = new ArrayList();
        if (lstItems != null && !lstItems.isEmpty()) {
            boolean isEdit = false;
            if (fileId != null && fileId > 0L) {
                isEdit = true;
            }
            if (lstAttachLabel != null) {
                lstAttachLabel = ";" + lstAttachLabel;
            } else {
                lstAttachLabel = "";
            }
            Long previousVersionId = getLastVersionIdOfFile(fileId);
            List lstPreviousVersionAtt = null;
            if (previousVersionId != null) {
                lstPreviousVersionAtt = getAttachsOfFile(previousVersionId);
            }
            for (VoAttachs item : lstItems) {
                Long attachId = item.getAttachId();
                if (attachId == null) {
                    continue;
                }
                lstIdOfAttachs.add(attachId);
            }

            List<VoAttachs> lstOldAttachs = null;
            if (!lstIdOfAttachs.isEmpty()) {
                String hql = "select v from VoAttachs v where v.attachId in (:lstIdOfAttachs)";
                Query query = getSession().createQuery(hql);
                query.setParameterList("lstIdOfAttachs", lstIdOfAttachs);
                lstOldAttachs = query.list();
            }
            for (int i = 0; i < lstItems.size(); i++) {
                VoAttachs item = lstItems.get(i);
                Long attachId = item.getAttachId();

                if (lstAttachLabel.indexOf(";" + i + ";") >= 0) {
                    item.setObjectType(com.viettel.common.util.Constants.OBJECT_TYPE.FILES_LABEL);
                } else {
                    item.setObjectType(com.viettel.common.util.Constants.OBJECT_TYPE.FILES);
                }
                item.setObjectId(fileId);

                if (attachId == null) {

                    item.setIsActive(1l);
                    getSession().save(item);

                } else {
                    for (VoAttachs oldItem : lstOldAttachs) {
                        if (oldItem.getAttachId().equals(attachId)) {
                            oldItem.setCategoryName(item.getCategoryName());
                            oldItem.setAttachDes(item.getAttachDes());
                            oldItem.setObjectType(item.getObjectType());
                            item = oldItem;
                            break;
                        }
                    }
                    if (checkAttachIsModify(item, lstPreviousVersionAtt)) {
                        item.setIsTemp(1l);
                    } else {
                        item.setIsTemp(0l);
                    }
                    item.setObjectId(fileId);
                    if (item.getAttachId() != null && fileId != null && fileId > 0L) {
                        getSession().merge(item);
                    } else if (!isEdit) {
                        getSession().save(item);
                    }
                }

                Long id = item.getAttachId();
                lstIdOfAttachs.add(id);
            }
        }
        if (fileId != null && fileId > 0L) {
            //la sua moi thuc hien xoa khong thi k xoa gi ca
            String hql = "delete from VoAttachs m where m.objectId = :filesId and (m.objectType= :objectType1 or m.objectType= :objectType2)  ";
            if (!lstIdOfAttachs.isEmpty()) {
                hql += " and m.attachId not in (:lstIdOfAttachs)";
            }
            Query query = getSession().createQuery(hql);
            query.setParameter("filesId", fileId);
            query.setParameter("objectType1", com.viettel.common.util.Constants.OBJECT_TYPE.FILES);
            query.setParameter("objectType2", com.viettel.common.util.Constants.OBJECT_TYPE.FILES_LABEL);
            if (!lstIdOfAttachs.isEmpty()) {
                query.setParameterList("lstIdOfAttachs", lstIdOfAttachs);
            }
            query.executeUpdate();
        }
    }

    /*
     * Luu ke hoach quan ly chat luong
     */
    private void saveQualityPlan(List<QualityControlPlan> lstItems, Long fileId) {
        List<Long> lstIdOfQualitys = new ArrayList();
        if (lstItems != null && !lstItems.isEmpty()) {
            for (QualityControlPlan item : lstItems) {
                if (item.getQualityControlPlanId() != null) {//kiem tra ban tren form khac trong db k neu khach oanh dau co sua doi va luu ban goc
//                    QualityControlPlan itemOnDB = (QualityControlPlan) findById(QualityControlPlan.class, "qualityControlPlanId", item.getQualityControlPlanId());
                    QualityControlPlanDAOHE qualityControlPlanDAOHE = new QualityControlPlanDAOHE();
                    QualityControlPlan itemOnDB = qualityControlPlanDAOHE.findIsItempObj(item.getQualityControlPlanId(), 0L, 0L, false);
                    if (itemOnDB != null) {
                        item.setIsTemp(itemOnDB.getIsTemp());
                        if (item.getControlTarget() != null && itemOnDB.getControlTarget() != null) {
                            if (!item.getControlTarget().equals(itemOnDB.getControlTarget())) {
                                item.setIsTemp(1L);
                            }
                        }
                        if (item.getNote() != null && itemOnDB.getNote() != null) {
                            if (!item.getNote().equals(itemOnDB.getNote())) {
                                item.setIsTemp(1L);
                            }
                        }
                        if (item.getNoteForm() != null && itemOnDB.getNoteForm() != null) {
                            if (!item.getNoteForm().equals(itemOnDB.getNoteForm())) {
                                item.setIsTemp(1L);
                            }
                        }
                        if (item.getPatternFrequence() != null && itemOnDB.getPatternFrequence() != null) {
                            if (!item.getPatternFrequence().equals(itemOnDB.getPatternFrequence())) {
                                item.setIsTemp(1L);
                            }
                        }
                        if (item.getProductProcessDetail() != null && itemOnDB.getProductProcessDetail() != null) {
                            if (!item.getProductProcessDetail().equals(itemOnDB.getProductProcessDetail())) {
                                item.setIsTemp(1L);
                            }
                        }
                        if (item.getTechnicalRegulation() != null && itemOnDB.getTechnicalRegulation() != null) {
                            if (!item.getTechnicalRegulation().equals(itemOnDB.getTechnicalRegulation())) {
                                item.setIsTemp(1L);
                            }
                        }
                        if (item.getTestDevice() != null && itemOnDB.getTestDevice() != null) {
                            if (!item.getTestDevice().equals(itemOnDB.getTestDevice())) {
                                item.setIsTemp(1L);
                            }
                        }
                        if (item.getTestMethod() != null && itemOnDB.getTestMethod() != null) {
                            if (!item.getTestMethod().equals(itemOnDB.getTestMethod())) {
                                item.setIsTemp(1L);
                            }
                        }
                    }
                }

                item.setFileId(fileId);

                if (item.getQualityControlPlanId() != null) {
                    getSession().merge(item);
                } else {
                    getSession().save(item);
                }

                Long id = item.getQualityControlPlanId();
                lstIdOfQualitys.add(id);
            }
        }

        String hql = "delete from QualityControlPlan m where m.fileId = :fileId ";
        if (!lstIdOfQualitys.isEmpty()) {
            hql += " and m.qualityControlPlanId not in (:lstIdOfQualitys)";
        }
        Query query = getSession().createQuery(hql);
        query.setParameter("fileId", fileId);
        if (!lstIdOfQualitys.isEmpty()) {
            query.setParameterList("lstIdOfQualitys", lstIdOfQualitys);
        }
        query.executeUpdate();
    }

    /*
     * Luu danh sach san pham
     */
    private void saveProductInFile(List<ProductInFile> lstItems, Long fileId) {
        List<Long> lstIdOfProducts = new ArrayList();
        if (lstItems != null && lstItems.size() > 0) {
            for (ProductInFile item : lstItems) {
                item.setFileId(fileId);
                if (item.getProductInFileId() != null) {
                    getSession().update(item);
                } else {
                    getSession().save(item);
                }

                Long id = item.getProductInFileId();
                lstIdOfProducts.add(id);
            }
        }

        String hql = "delete from ProductInFile m where m.fileId = :fileId ";
        if (!lstIdOfProducts.isEmpty()) {
            hql += " and m.productInFileId not in (:lstIdOfProducts)";
        }
        Query query = getSession().createQuery(hql);
        query.setParameter("fileId", fileId);
        if (!lstIdOfProducts.isEmpty()) {
            query.setParameterList("lstIdOfProducts", lstIdOfProducts);
        }
        query.executeUpdate();
    }

    /*
     * Luu fee cua ho so
     */
    private Boolean saveFee(Long fileId, Long fileType, Long productType, Procedure pro, Long productTypeIdOld) {
        if (fileId != null) {
            Date dateNow = null;
            try {
                dateNow = getSysdate();
            } catch (Exception ex) {
                log.error(ex.getMessage());
                return false;
            }

            String hql = "select fpif from FeePaymentInfo fpif where fpif.fileId =:fileId and fpif.isActive = 1";
            Query query = getSession().createQuery(hql);
            query.setParameter("fileId", fileId);
            List<FeePaymentInfo> FeePaymentInfo = query.list();
            //
            // truong hop sao chep va luu tam productType co the = null, vi the phai set = -1
            //

            if (FeePaymentInfo.isEmpty()) {
                FeeDAOHE fdhe = new FeeDAOHE();
                List<FeeProcedure> feenew = fdhe.findAllFeeByProcedureId(fileType);
                // check le phi cap so theo loai ho so
                if (feenew != null && feenew.size() > 0) {
                    FeePaymentInfo fpif = new FeePaymentInfo();
                    for (int i = 0; i < feenew.size(); i++) {
                        fpif = new FeePaymentInfo();
                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(0l);
                        fpif.setFileId(fileId);
                        fpif.setIsActive(1l);
                        fpif.setFeeId(feenew.get(i).getFeeId());
                        Fee feeTemp = (Fee) findById(Fee.class, "feeId", feenew.get(i).getFeeId());
                        fpif.setCost(feeTemp.getPrice());
                        getSession().save(fpif);
                    }
                }

                // hieptq update 280515 set phi ho so cap lai
                if (productType == null) {
                    if (pro != null && pro.getTypeFee() == 2l) {
                        FeePaymentInfo fpif = new FeePaymentInfo();
                        Fee findfee1 = fdhe.findFeeByCode("CLT");
                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(0l);
                        fpif.setFileId(fileId);
                        if (findfee1 != null) {
                            fpif.setFeeId(findfee1.getFeeId());
                            fpif.setCost(findfee1.getPrice());
                            fpif.setCostCheck(findfee1.getPrice());
                            fpif.setFeeIdOld(findfee1.getFeeId());
                        } else {
                            return false;
                        }
                        fpif.setIsActive(1l);
                        getSession().save(fpif);
                        return true;
                    } else if (pro != null && pro.getTypeFee() == 3l) {
                        FeePaymentInfo fpif = new FeePaymentInfo();
                        Fee findfee1 = fdhe.findFeeByCode("CLCN");
                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(0l);
                        fpif.setFileId(fileId);
                        if (findfee1 != null) {
                            fpif.setFeeId(findfee1.getFeeId());
                            fpif.setCost(findfee1.getPrice());
                            fpif.setCostCheck(findfee1.getPrice());
                            fpif.setFeeIdOld(findfee1.getFeeId());
                        } else {
                            return false;
                        }
                        fpif.setIsActive(1l);
                        getSession().save(fpif);
                        return true;
                    } else {
                        return false;
                    }
                }

                //check loai thuc pham (thuc pham dac biet)
                CategoryDAOHE ctdhe = new CategoryDAOHE();
                Category cate = ctdhe.findCategoryByTypeAndCode("SP", "TPCN");
                List<Category> cate1 = ctdhe.findCategoryByTypeAndCodeNew("SP", "DBT");
                int check = 0;
                for (int i = 0; i < cate1.size(); i++) {
                    if (productType.equals(cate1.get(i).getCategoryId())) {
                        check = 1;
                        break;
                    }
                }

                if (check == 1) {
                    FeePaymentInfo fpif = new FeePaymentInfo();
                    Fee findfee1 = fdhe.findFeeByCode("TPDB");
                    fpif.setCreateDate(dateNow);
                    fpif.setStatus(0l);
                    fpif.setFileId(fileId);
                    if (findfee1 != null) {
                        fpif.setFeeId(findfee1.getFeeId());
                        fpif.setCost(findfee1.getPrice());
                        fpif.setCostCheck(findfee1.getPrice());
                        fpif.setFeeIdOld(findfee1.getFeeId());
                    } else {
                        return false;
                    }
                    fpif.setIsActive(1l);
                    getSession().save(fpif);
                } else {
                    // thuc pham chuc nang
                    FeePaymentInfo fpif = new FeePaymentInfo();
                    if (productType.equals(cate.getCategoryId())) {
                        Fee findfee2 = fdhe.findFeeByCode("TPCN");

                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(0l);
                        fpif.setFileId(fileId);
                        if (findfee2 != null) {
                            fpif.setFeeId(findfee2.getFeeId());
                            fpif.setCost(findfee2.getPrice());
                            fpif.setCostCheck(findfee2.getPrice());
                            fpif.setFeeIdOld(findfee2.getFeeId());
                        } else {
                            return false;
                        }
                        fpif.setIsActive(1l);
                        getSession().save(fpif);
                    } else {
                        Fee findfee3 = fdhe.findFeeByCode("TPK");
                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(0l);
                        fpif.setFileId(fileId);
                        if (findfee3 != null) {
                            fpif.setFeeId(findfee3.getFeeId());
                            fpif.setCost(findfee3.getPrice());
                            fpif.setCostCheck(findfee3.getPrice());
                            fpif.setFeeIdOld(findfee3.getFeeId());
                        } else {
                            return false;
                        }
                        fpif.setIsActive(1l);
                        getSession().save(fpif);
                    }
                }
                // }
            }
            // sua nhom san pham va chuyen loai ho so - map lai phi
            if ((productTypeIdOld != null) && (!productTypeIdOld.equals(productType))) {
                // hieptq update 310115
                // tim lai feeId cu 
                CategoryDAOHE ctdhe = new CategoryDAOHE();
                FeeDAOHE fdhe = new FeeDAOHE();
                Category cate = ctdhe.findCategoryByTypeAndCode("SP", "TPCN");
                List<Category> cate1 = ctdhe.findCategoryByTypeAndCodeNew("SP", "DBT");
                Long feeIdOld, feeIdNew;
                int check = 0, check1 = 0;
                FeePaymentInfo fpifOld = null;
                for (int i = 0; i < cate1.size(); i++) {
                    if (productTypeIdOld.equals(cate1.get(i).getCategoryId())) {
                        check = 1;
                        break;
                    }
                }
                if (check == 1) {
                    Fee findfee1 = fdhe.findFeeByCode("TPDB");
                    feeIdOld = findfee1.getFeeId();
                } else // thuc pham chuc nang
                 if (productTypeIdOld.equals(cate.getCategoryId())) {
                        Fee findfee2 = fdhe.findFeeByCode("TPCN");
                        feeIdOld = findfee2.getFeeId();
                    } else {
                        Fee findfee3 = fdhe.findFeeByCode("TPK");
                        feeIdOld = findfee3.getFeeId();
                    }

                fpifOld = fdhe.findFeePaymentInfoFileIdFeeIdIsActive(fileId, feeIdOld, 1l);

                // lay feeid moi
                for (int i = 0; i < cate1.size(); i++) {
                    if (productType.equals(cate1.get(i).getCategoryId())) {
                        check1 = 1;
                        break;
                    }
                }
                Long costNew = 0l;
                if (check1 == 1) {
                    Fee findfee1 = fdhe.findFeeByCode("TPDB");
                    feeIdNew = findfee1.getFeeId();
                    costNew = findfee1.getPrice();
                } else // thuc pham chuc nang
                 if (productType.equals(cate.getCategoryId())) {
                        Fee findfee2 = fdhe.findFeeByCode("TPCN");
                        feeIdNew = findfee2.getFeeId();
                        costNew = findfee2.getPrice();
                    } else {
                        Fee findfee3 = fdhe.findFeeByCode("TPK");
                        feeIdNew = findfee3.getFeeId();
                        costNew = findfee3.getPrice();
                    }
                //fpifNew = fdhe.findFeePaymentInfoFileIdFeeIdIsActive(fileId, feeIdNew, 1l);
                FilesDAOHE filesdhe = new FilesDAOHE();
                Files filesnew = filesdhe.findById(fileId);
                // check gia cu va gia moi
                if (fpifOld.getCostCheck().equals(costNew) || fpifOld.getCostCheck() > costNew) {
                    if (filesnew.getStatus() == 0l) {
                        fpifOld.setCost(fpifOld.getCostCheck());
                        fpifOld.setFeeId(fpifOld.getFeeIdOld());
                        getSession().update(fpifOld);
                    } else {
                        //Long newCost = costNew - fpifOld.getCost();
                        fpifOld.setCost(fpifOld.getCostCheck());
                        fpifOld.setFeeId(fpifOld.getFeeIdOld());
                        fpifOld.setStatus(1l);
                        filesnew.setIsFee(1l);
                        getSession().update(filesnew);
                        getSession().update(fpifOld);
                    }

                    return true;
                } else if (filesnew.getStatus() == 0l) {
                    fpifOld.setCost(costNew);
                    fpifOld.setFeeId(feeIdNew);
                    getSession().update(fpifOld);
                } else {
                    //Long newCost = costNew - fpifOld.getCost();
                    fpifOld.setCost(costNew);
                    fpifOld.setFeeId(feeIdNew);
                    fpifOld.setStatus(0l);
                    //Hiepvv 0803 Khong tinh phi SDBS sau cong bo
                    ProcedureDAOHE pHE = new ProcedureDAOHE();
                    Procedure pdu = new Procedure();
                    pdu = pHE.findById(filesnew.getFileType());
                    if (pdu != null && pdu.getDescription() != null
                            && pdu.getDescription().equals("announcementFile05")) {
                        filesnew.setIsFee(1l);
                    } else {
                        filesnew.setIsFee(0l);
                    }
                    getSession().update(filesnew);
                    getSession().update(fpifOld);
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private Boolean saveFileForSearch(Long fileId) {
        try {
            FilesForm viewForm = getFilesFullDetail(fileId);
            //
            // save json to search
            //
            Gson gson = new Gson();
            String json = gson.toJson(viewForm);
            FileForSearch fs = new FileForSearch(fileId);
            fs.setContent(json);
            session.saveOrUpdate(fs);
            return true;
        } catch (Exception en) {
            return false;
        }
        //
        // end save json to search
        //
    }

    public int getUpdateCount() {
        String hql = "select count(f) from Files f"
                + " where f.isActive = 1"
                + " and f.isTemp is null"
                + " and f.fileId not in (select ffs.fileId from FileForSearch ffs)";
        Query query = session.createQuery(hql);
        Long count = (Long) query.uniqueResult();
        return count.intValue();
    }

    public Boolean updateIndex(int start, int length) {
        String hql = "select f.fileId from Files f"
                + " where f.isActive = 1"
                + " and f.isTemp is null"
                + " and f.fileId not in (select ffs.fileId from FileForSearch ffs)";
        Query query = session.createQuery(hql);
        query.setFirstResult(start);
        query.setMaxResults(length);
        List<Long> lstIds = query.list();
        for (int i = 0; i < lstIds.size(); i++) {
            Long id = lstIds.get(i);
            saveFileForSearch(id);
        }
        return true;
    }

    /**
     * Lưu hồ sơ
     *
     * @param createForm
     * @return
     */
    public Files saveFiles(FilesForm createForm) {
        Files bo = null;
        Files boRollBack = null;
        Long filesId = createForm.getFileId();
        Boolean isCreateNew = true;
        Long status = 0L;
        Long announcementId = null;
        Long detailProductId = null;
        Long reIssueFormId = null;
        Long testRegistrationId = null;
        Long productTypeIdOld = null;
        if (createForm.getStatus() != null) {
            status = createForm.getStatus();
        }
        if (filesId != null) {
            String hql = "select dt.productType from DetailProduct dt "
                    + "where "
                    + "dt.detailProductId = (select f.detailProductId from Files f where f.fileId =?)";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, filesId);
            List<Long> lstProductType = query.list();
            if (lstProductType.size() > 0) {
                productTypeIdOld = lstProductType.get(0);
            }
        }

        //
        // luu thong tin ho so
        //
        if (filesId == null) {//la them moi            
            bo = createForm.convertToEntity();
        } else {//la sua
            isCreateNew = false;
            boRollBack = findById(filesId);
            bo = findById(filesId);

            if (status.equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)
                    && bo.getHaveTemp() != null
                    && bo.getHaveTemp().equals(1l)) {
                //
                // Tao ban ghi backup
                //
                FilesForm cloneForm = getNewCloneFiles(filesId);
                cloneForm.setVersion(getCountVersion(bo.getFileId()));
                bo.setVersion(cloneForm.getVersion());//update version moi nhat cua ho so
                bo.setHaveTemp(null);
                saveFiles(cloneForm);
                //update toan bo process_comment cua lan tham dinh truoc thanh
                ProcessCommentDAOHE pcdhe = new ProcessCommentDAOHE();
                int r = pcdhe.updateVersion(filesId, cloneForm.getVersion());
            }
            bo = createForm.updateToEntity(bo);
        }

        //*Luu thong tin cac form chinh cua ho so
        if (createForm.getAnnouncement() != null) {
            Announcement ann = createForm.getAnnouncement().convertToEntity();
            ann.setIsTemp(0L);
            if (ann.getAnnouncementId() != null) {
                session.merge(ann);
            } else {
                session.save(ann);
            }
            announcementId = ann.getAnnouncementId();
        }

        if (createForm.getDetailProduct() != null) {
            DetailProduct detail = createForm.getDetailProduct().convertToEntity();
            detail.setIsTemp(0L);
            if (detail.getDetailProductId() != null) {
                session.merge(detail);
            } else {
                session.save(detail);
            }
            detailProductId = detail.getDetailProductId();
        }

        if (createForm.getReIssueForm() != null) {
            ReIssueForm reissue = createForm.getReIssueForm().convertToEntity();
            if (reissue.getReIssueFormId() != null) {
                session.merge(reissue);
            } else {
                session.save(reissue);
            }
            reIssueFormId = reissue.getReIssueFormId();
        }

        if (createForm.getTestRegistration() != null) {
            TestRegistration testReg = createForm.getTestRegistration().convertToEntity();
            if (testReg.getTestRegistrationId() != null) {
                getSession().merge(testReg);
            } else {
                getSession().save(testReg);
            }
            testRegistrationId = testReg.getTestRegistrationId();
        }
        bo.setAnnouncementId(announcementId);
        bo.setDetailProductId(detailProductId);
        bo.setReIssueFormId(reIssueFormId);
        bo.setTestRegistrationId(testRegistrationId);
        bo.setDisplayStatus(getFileStatusName(bo.getStatus()));
        if (bo.getFileId() != null) {
            //khi sua xoa toan bo chu ki CA
            bo.setStaffRequest("");
            bo.setLeaderRequest("");
            bo.setLeaderStaffRequest("");
            bo.setContentSigned("");
            bo.setUserSigned("");
            getSession().update(bo);
        } else {
            //update 15092015 binhnt cap nhat lay ma ho so
            if (createForm.getIsTemp() != null
                    && createForm.getIsTemp().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                //
                // voi ho so clone thi ko can tao moi file code
                //
            } else {
                bo.setFileCode(getNewFileCode(createForm.getFileType()));
            }
            getSession().save(bo);
        }

        filesId = bo.getFileId();

        // Luu thong tin cac danh sach chi tieu chinh
        saveMainlytarget(createForm.getLstMainlyTarget(), filesId);
        // Luu thong tin danh sach chi tieu san pham
        saveProductTarget(createForm.getLstBioTarget(), filesId, Constants.PRODUCT_TARGET_TYPE.BIO);
        saveProductTarget(createForm.getLstHeavyMetal(), filesId, Constants.PRODUCT_TARGET_TYPE.HEAVY_METAL);
        saveProductTarget(createForm.getLstChemical(), filesId, Constants.PRODUCT_TARGET_TYPE.CHEMICAL);
        // Luu thong tin danh sach tai lieu
        saveAttachs(createForm.getLstAttachs(), filesId, createForm.getLstAttachLabel());

//        if ("".equals(createForm.getLstAttachLabel())) {
//            return null;
//        }
        // Luu thong tin danh sach ke hoach quan ly chat luong san pham
        saveQualityPlan(createForm.getLstQualityControl(), filesId);

        // Luu thong tin danh sach san pham nhap khau cho khach san 4 sao        
        saveProductInFile(createForm.getLstProductInFile(), filesId);
        try {//lưu phí thẩm định hồ sơ
            ProcedureDAOHE pdheCheck = new ProcedureDAOHE();
            Procedure pro = pdheCheck.getProcedureTypeFee(createForm.getFileType());
            if (pro != null
                    && (pro.getTypeFee() == 2 || pro.getTypeFee() == 3)
                    && !pro.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR)
                    && !pro.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05)) {
                if (!saveFee(filesId, createForm.getFileType(), null, pro, productTypeIdOld)) {
                    return null;
                }
            } //Sua doi sua cong bo. Tao mot ban ghi trong FeePaymentInfo de VanThu nhin thay hoso cua loai nay
            else if (pro != null
                    && pro.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05)) {
                if (!saveFeeChangesAfterAnnounced(filesId, createForm.getFileType())) {
                    return null;
                }
            } else if (pro != null
                    && pro.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR)) {
                if (!saveFee4Star(filesId, createForm.getFileType())) {
                    return null;
                }
            } else if (pro != null
                    && (pro.getTypeFee() == 7)) {
                if (!saveFeeTL(filesId, createForm.getFileType(),
                        createForm.getDetailProduct().getProductType(), pro, productTypeIdOld)) {
                    return null;
                }
            } else if (createForm.getDetailProduct() != null
                    && createForm.getDetailProduct().getProductType() != null) {
                if (!saveFee(filesId, createForm.getFileType(),
                        createForm.getDetailProduct().getProductType(), pro, productTypeIdOld)) {
                    return null;
                }
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
            if (isCreateNew) {
                bo.setIsActive(Constants.Status.INACTIVE);
                getSession().update(bo);
            } else {
                getSession().update(boRollBack);

            }
            return null;
        }

        if (createForm.getIsTemp() == null || (createForm.getIsTemp() != null && !createForm.getIsTemp().equals(1l))) {
            saveFileForSearch(filesId);
        }

        session.flush();
        return bo;
    }

    /**
     *
     * @param form
     * @return
     */
    public String validateAnnouncementForm(AnnouncementForm form, Long fileId) {
        String error = null;
        if (form.getBusinessName() == null || form.getBusinessName().trim().isEmpty()) {
            error = "Chưa nhập tên doanh nghiệp";
            return error;
        }
        if (form.getBusinessAddress() == null || form.getBusinessAddress().trim().isEmpty()) {
            error = "Chưa nhập địa chỉ doanh nghiệp";
            return error;
        }
        if (form.getBusinessTelephone() == null || form.getBusinessTelephone().trim().isEmpty()) {
            error = "Chưa nhập số điện thoại doanh nghiệp";
            return error;
        }

        if (!Validator.validateMobileNumber(form.getBusinessTelephone())) {
            error = "Số điện thoại doanh nghiệp không đúng định dạng";
            return error;
        }

        if (form.getBusinessFax() != null) {
            if (!Validator.validateMobileNumber(form.getBusinessFax())) {
                error = "Số fax doanh nghiệp không đúng định dạng";
                return error;
            }
        }

        if (form.getBusinessEmail() != null) {
            if (!Validator.validateEmail(form.getBusinessEmail())) {
                error = "Email doanh nghiệp không đúng định dạng";
                return error;
            }
        }
        ////Hiepvv ngoai le validate
        Files file = findById(fileId);
        ProcedureDAOHE pdhe = new ProcedureDAOHE();
        Procedure tthc = pdhe.findById(file.getFileType());
        String typeFile = "";
        if (tthc != null) {
            typeFile = tthc.getDescription();
        }
        //Hiepvv Khong validate voi ks4s
        if (typeFile.equalsIgnoreCase("announcement4star")) {

        } else if (form.getProductName() == null || form.getProductName().trim().isEmpty()) {
            error = "Chưa nhập tên sản phẩm";
            return error;
        }
        //Hiepvv Khong validate voi ho so sua doi sau cong bo
        if (!(typeFile.equalsIgnoreCase("announcementFile05"))
                && (form.getManufactureName() == null
                || form.getManufactureName().trim().isEmpty())) {
            error = "Chưa nhập tên nhà sản xuất";
            return error;
        }
        //Hiepvv Khong validate voi ho so sua doi sau cong bo
        if (!(typeFile.equalsIgnoreCase("announcementFile05"))
                && (form.getManufactureAddress() == null
                || form.getManufactureAddress().trim().isEmpty())) {
            error = "Chưa nhập địa chỉ nhà sản xuất";
            return error;
        }

        if (form.getManufactureTel() != null) {
            if (!Validator.validateMobileNumber(form.getManufactureTel())) {
                error = "Số điện thoại nhà sản xuất không đúng định dạng";
                return error;
            }
        }

        if (form.getManufactureFax() != null) {
            if (!Validator.validateMobileNumber(form.getManufactureFax())) {
                error = "Số fax nhà sản xuất không đúng định dạng";
                return error;
            }
        }

        if (form.getManufactureEmail() != null) {
            if (!Validator.validateEmail(form.getManufactureEmail())) {
                error = "Email nhà sản xuất không đúng định dạng";
                return error;
            }
        }
        //Hiepvv Khong validate voi ho so sua doi sau cong bo
        if (!(typeFile.equalsIgnoreCase("announcementFile05")) && (form.getNationName() == null || form.getNationName().trim().isEmpty())) {
            error = "Chưa nhập nước xuất xứ";
            return error;
        }
        if (form.getAnnouncementNo() == null || form.getAnnouncementNo().trim().isEmpty()) {
            error = "Chưa nhập số bản công bố";
            return error;
        }

        if (!(typeFile.equalsIgnoreCase("announcementFile05")) && (form.getMatchingTarget() == null || form.getMatchingTarget().trim().isEmpty())) {
            error = "Chưa nhập phù hợp với QCKT/QĐATTP";
            return error;
        }

        return error;
    }

    /**
     *
     * @param form
     * @return
     */
    public String validateProductDetail(DetailProductForm form) {
        String error = null;
        if (form.getProductType() == null || form.getProductType() <= 0) {
            error = "Chưa nhập nhóm sản phẩm";
            return error;
        }
        if (form.getComponents() == null || form.getComponents().trim().isEmpty()) {
            error = "Chưa nhập thành phần cấu tạo";
            return error;
        }
        if (form.getTimeInUse() == null || form.getTimeInUse().trim().isEmpty()) {
            error = "Chưa nhập thời hạn sử dụng";
            return error;
        }
        if (form.getGuideline() == null || form.getGuideline().trim().isEmpty()) {
            error = "Chưa nhập hướng dẫn sử dụng và bảo quản";
            return error;
        }

        if (form.getPackateMaterial() == null || form.getPackateMaterial().trim().isEmpty()) {
            error = "Chưa nhập chất liệu bao bì và quy cách đóng gói";
            return error;
        }
        if (form.getOrigin() == null || form.getOrigin().trim().isEmpty()) {
            error = "Chưa nhập xuất xứ và thương nhân chịu trách nhiệm về chất lượng hàng hóa";
            return error;
        }

        return error;
    }

    public String validateFiles(FilesForm createForm, Long fileId) {
        String error = null;
        if (createForm.getAnnouncement() != null) {
            //
            // validate thong tin ban cong bo
            //
            error = validateAnnouncementForm(createForm.getAnnouncement(), fileId);
        }

        if (error != null) {
            return error;
        }
        Files file = findById(fileId);
        ProcedureDAOHE pdhe = new ProcedureDAOHE();
        Procedure tthc = pdhe.findById(file.getFileType());
        //Hiepvv Khong validate voi ho so sua doi sau cong bo va KS4s
        if (tthc.getDescription().equalsIgnoreCase("announcement4star") || tthc.getDescription().equalsIgnoreCase("announcementFile05")) {

        } else if (createForm.getDetailProduct() != null) {
            //
            // validate thong tin chi tiet
            //
            error = validateProductDetail(createForm.getDetailProduct());
        }

        //hieptq update check reset fee 100715
        if (createForm.getUserSigned() != null && createForm.getUserSigned().equals("reset")) {
            //
            // validate thong tin chi tiet
            //
            error = "Chuyển sang loại hồ sơ có phí cao hơn phải chọn lại nhóm sản phẩm";
        }

        if (error != null) {
            return error;
        }

        if (createForm.getReIssueForm() != null) {
            //
            // validate cap lai, do chua su dung nen tam de trong
            //
        }
//        if (createForm.getTestRegistration() != null) {
//            //
//            // validate dang ky xet nghiem
//            // ko co thong tin can validate
//            //
//        }
        //
        // validate mainly target
        //
        if (createForm.getLstMainlyTarget() != null && !createForm.getLstMainlyTarget().isEmpty()) {
            for (MainlyTarget item : createForm.getLstMainlyTarget()) {
                if (item.getTargetName() == null || item.getTargetName().trim().isEmpty()) {
                    error = "Chưa nhập tên chỉ tiêu chất lượng chính";
                    return error;
                }
                if (item.getUnitName() == null || item.getUnitName().equals("-- Chọn --")) {
                    error = "Chưa nhập đơn vị của chỉ tiêu chất lượng chính";
                    return error;
                }
                if (item.getPublishLevel() == null || item.getPublishLevel().trim().isEmpty()) {
                    error = "Chưa nhập mức công bố của chỉ tiêu chất lượng chính";
                    return error;
                }

            }
        }
        //
        // validate product target
        //
        if (createForm.getLstBioTarget() != null && !createForm.getLstBioTarget().isEmpty()) {
            for (ProductTarget item : createForm.getLstBioTarget()) {
                if (item.getTargetName() == null || item.getTargetName().trim().isEmpty()) {
                    error = "Chưa nhập tên chỉ tiêu vi sinh vật";
                    return error;
                }
                if (item.getUnitName() == null || item.getUnitName().equals("-- Chọn --")) {
                    error = "Chưa nhập đơn vị của chỉ tiêu vi sinh vật";
                    return error;
                }
                if (item.getMaxLevel() == null || item.getMaxLevel().trim().isEmpty()) {
                    error = "Chưa nhập mức tối đa của chỉ tiêu vi sinh vật";
                    return error;
                }

            }
        }
        if (createForm.getLstHeavyMetal() != null && !createForm.getLstHeavyMetal().isEmpty()) {
            for (ProductTarget item : createForm.getLstHeavyMetal()) {
                if (item.getTargetName() == null || item.getTargetName().trim().isEmpty()) {
                    error = "Chưa nhập tên chỉ tiêu hàm lượng kim loại nặng";
                    return error;
                }
                if (item.getUnitName() == null || item.getUnitName().equals("-- Chọn --")) {
                    error = "Chưa nhập đơn vị của chỉ tiêu hàm lượng kim loại nặng";
                    return error;
                }
                if (item.getMaxLevel() == null || item.getMaxLevel().trim().isEmpty()) {
                    error = "Chưa nhập mức tối đa của chỉ tiêu hàm lượng kim loại nặng";
                    return error;
                }

            }
        }
        if (createForm.getLstChemical() != null && !createForm.getLstChemical().isEmpty()) {
            for (ProductTarget item : createForm.getLstChemical()) {
                if (item.getTargetName() == null || item.getTargetName().trim().isEmpty()) {
                    error = "Chưa nhập tên chỉ tiêu hàm lượng hóa chất không mong muốn";
                    return error;
                }
                if (item.getUnitName() == null || item.getUnitName().equals("-- Chọn --")) {
                    error = "Chưa nhập đơn vị của chỉ tiêu chất hàm lượng hóa chất không mong muốn";
                    return error;
                }
                if (item.getMaxLevel() == null || item.getMaxLevel().trim().isEmpty()) {
                    error = "Chưa nhập mức tối đa của chỉ tiêu hàm lượng hóa chất không mong muốn";
                    return error;
                }

            }
        }
        //
        // validate attachs
        //

        Procedure pd = pdhe.findById(createForm.getFileType());
        if (pd == null) {
            error = "Không xác định được loại hồ sơ";
            return error;
        }

        int count = StringUtils.countMatches(pd.getFileList(), "(*)");
        if (count > 0) {
            if (createForm.getLstAttachs() == null) {
                error = "Chưa nhập đầy đủ file đính kèm";
                return error;
            }

            if (count > createForm.getLstAttachs().size()) {
                error = "Chưa nhập đầy đủ file đính kèm";
                return error;
            }
        }
        count = 0;// dem so file la nhan
        if (createForm.getLstAttachs() != null && !createForm.getLstAttachs().isEmpty()) {
            for (VoAttachs item : createForm.getLstAttachs()) {
                if (item.getAttachName() == null || item.getAttachName().trim().isEmpty()) {
                    error = "Chưa nhập tên file đính kèm";
                    return error;
                }
                if (item.getCategoryName() == null || item.getCategoryName().trim().isEmpty()) {
                    error = "Chưa nhập tên tài liệu";
                    return error;
                }
                if (item.getAttachPath() == null || item.getAttachPath().trim().isEmpty()) {
                    error = "Chưa nhập file đính kèm";
                    return error;
                }
                if (item.getObjectType().equals(Constants.OBJECT_TYPE.FILES_LABEL)) {
                    count++;
                }
            }
        }
        //Hiepvv Khong validate voi ho so sua doi sau cong bo
        if (count == 0 && !(pd.getDescription().equalsIgnoreCase("announcementFile05"))) {
            error = "Chưa chọn nhãn cho sản phẩm";
            return error;
        } else//150709 binhnt53 add check max nhan duoc chon
         if (count > 3) {
                error = "Vượt quá số lượng tệp được chọn đính kèm cùng bản công bố(Tối đa 3 tệp)";
                return error;
            }//!150709        //        // validate ke hoach kiem soat chat luong        //
        if (createForm.getLstQualityControl() != null && !createForm.getLstQualityControl().isEmpty()) {
            for (QualityControlPlan item : createForm.getLstQualityControl()) {
                if (item.getProductProcessDetail() == null || item.getProductProcessDetail().trim().isEmpty()) {
                    error = "Chưa nhập các quá trình sản xuất";
                    return error;
                }
            }
        }

        return error;
    }

    public String validateFiles(Long fileId) {
        String error;
        FilesForm form = getFilesFullDetail(fileId);
        if (form == null) {
            error = "Không tồn tại hồ sơ";
        } else {
            error = validateFiles(form, fileId);
        }
        return error;
    }

    /**
     *
     * @param userId
     * @param businessId
     * @param fileId
     * @return 0 : xóa thành công 1 : Không có quyền xóa 2 : đã gửi đi k xóa
     * được
     */
    public int deleteFile(Long userId, Long businessId, Long fileId) {
        int iReturn = 0;
        Files file = findById(fileId);
        if (file.getDeptId() != null && businessId != null && file.getDeptId().equals(businessId)) {
            if (Constants.FILE_STATUS.NEW_CREATE.equals(file.getStatus()) || Constants.FILE_STATUS.FEDBACK_TO_ADD.equals(file.getStatus()) || Constants.FILE_STATUS.RECEIVED_REJECT.equals(file.getStatus())) {
                //hieptq update neu ho so bi ke toan tu choi xoa di is_active = 2
                if (Constants.FILE_STATUS.RECEIVED_REJECT.equals(file.getStatus())) {
                    file.setIsActive(2l);
                } else {
                    file.setIsActive(0l);
                }
                getSession().update(file);
            } else {
                iReturn = 2;
            }
        } else {
            iReturn = 1;
        }
        return iReturn;
    }

    /**
     * lấy danh sách đơn vị gửi hồ sơ đến
     *
     * @param fileId
     * @param provinceId
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult getAgencyToSendFile(Long fileId, Long provinceId, int start, int count, String sortField) {
        Files file = findById(fileId);
        if (file == null) {
            return null;
        }
        Long fileType = file.getFileType();
        List lstParam = new ArrayList();
        GridResult gr;
        //
        // Tim cac don vi dap ung vua thuoc tinh lai vua co luong xu ly
        //
        String hql = " from Flow f where f.isActive = 1 ";
        hql += " and f.flowType=?";
        lstParam.add(fileType);

        if (provinceId != null) {
            hql += " and f.deptId in (select d from Department d where d.provinceId = ?) ";
            lstParam.add(provinceId);
        }

        Query countQuery = getSession().createQuery("select count(f) " + hql);
        Query query = getSession().createQuery("select f " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            countQuery.setParameter(i, lstParam.get(i));
        }

        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        if (total > 0) {
            List lstResult = query.list();
            gr = new GridResult(total, lstResult);
        } else {
            //
            // Neu tim theo tinh ko co don vi nao dap ung thi chi tim cac don vi co luong thoi xu ly loai ho so thoi
            //
            lstParam.clear();
            hql = " from Flow f where f.isActive = 1 ";
            hql += " and f.flowType=?";
            lstParam.add(fileType);

            countQuery = getSession().createQuery("select count(f) " + hql);
            query = getSession().createQuery("select f " + hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }

            query.setFirstResult(start);
            query.setMaxResults(count);
            List lstResult = query.list();
            gr = new GridResult(total, lstResult);
        }
        return gr;

    }

    /**
     * lấy các danh sách của hồ sơ
     *
     * @param fileId
     * @return
     */
    public List getMainlyTargetOfFile(Long fileId) {
        String hql = "select m from MainlyTarget m where m.fileId = ? order by m.mainlyTargetId";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, fileId);
        List lst = query.list();
        return lst;
    }

    /**
     * chỉ tiêu sản phẩm hồ sơ
     *
     * @param fileId
     * @return
     */
    public List getProductTargetOfFile(Long fileId) {
        String hql = "select m from ProductTarget m where m.fileId = ? order by m.productTargetId ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, fileId);
        List lst = query.list();
        return lst;
    }

    /**
     * thông tin chỉ tiêu sản phẩm
     *
     * @param fileId
     * @param type
     * @return
     */
    public List getProductTargetOfFile(Long fileId, Long type) {
        String hql = "select m from ProductTarget m where m.fileId = ? and m.targetType = ? order by m.productTargetId ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, fileId);
        query.setParameter(1, type);
        List lst = query.list();
        return lst;
    }

    /**
     * lấy file att hồ sơ
     *
     * @param fileId
     * @return
     */
    public List getAttachsOfFile(Long fileId) {
        String hql = "select m from VoAttachs m where m.isActive = 1 and m.objectId = ? and (m.objectType=? or m.objectType=?) order by m.attachId ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, fileId);
        query.setParameter(1, com.viettel.common.util.Constants.OBJECT_TYPE.FILES);
        query.setParameter(2, com.viettel.common.util.Constants.OBJECT_TYPE.FILES_LABEL);
        List lst = query.list();
        return lst;
    }

    /**
     * Hiepvv 11/03 lấy file cong van sdbs sau cong bo
     *
     * @param fileId
     * @return
     */
    public List getAttachsOfFileSDBS(Long fileId) {
        String hql = "select m from VoAttachs m where m.isActive = 1 and m.objectId = ? and (m.objectType=? or m.objectType=?) and m.attachName like 'CongvanSDBSsaucongbo%' order by m.attachId ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, fileId);
        query.setParameter(1, 40L);
        query.setParameter(2, 41L);
        List lst = query.list();
        return lst;
    }

    /**
     * quản lý chất lượng
     *
     * @param fileId
     * @return
     */
    public List getQualityControlOfFile(Long fileId) {
        String hql = "select m from QualityControlPlan m where m.fileId = ? order by m.qualityControlPlanId  ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, fileId);
        List lst = query.list();
        return lst;
    }

    /**
     * get danh sách sản phẩm
     *
     * @param fileId
     * @return
     */
    public List getProductOfFile(Long fileId) {
        String hql = "select m from ProductInFile m where m.fileId = ? order by m.productInFileId ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, fileId);
        List lst = query.list();
        return lst;
    }

    /**
     * tra ve true neu co ban ghi bi trung, tra ve false neu ko bi trung
     *
     * @param form
     * @return
     */
    public boolean isDuplicate(FilesForm form) {
        if (form == null) {
            return false;
        }
//        List lstParam = new ArrayList();
//        String hql = "select count(b) from Business b where b.isActive = 1 ";
//        if (form.getBusinessId() != null && form.getBusinessId() > 0l) {
//            hql += " and b.businessId <> ? ";
//            lstParam.add(form.getBusinessId());
//        }
//
//        if (form.getBusinessTaxCode() != null && form.getBusinessTaxCode().trim().length() > 0) {
//            hql += " and lower(b.businessTaxCode) = ?";
//            lstParam.add(form.getBusinessTaxCode().toLowerCase());
//        }
//        if (form.getBusinessLicense() != null && form.getBusinessLicense().trim().length() > 0) {
//            hql += " and lower(b.businessLicense) = ?";
//            lstParam.add(form.getBusinessLicense().toLowerCase());
//        }
//
//        Query query = getSession().createQuery(hql);
//        for (int i = 0; i < lstParam.size(); i++) {
//            query.setParameter(i, lstParam.get(i));
//        }
//
//        Long count = Long.parseLong(query.uniqueResult().toString());
//        boolean bReturn = false;
//        if (count >= 1l) {
//            bReturn = true;
//        }
        boolean bReturn = false;
        return bReturn;
    }

    /**
     * Tìm tất cả Xử lý by FilesId
     *
     * @param fileId
     * @param deptId
     * @param userId
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findAllProcessByFileId(Long fileId, Long deptId, Long userId, int start, int count, String sortField) {
        String hql = " from Process p "
                //                + "where p.isActive=1 and p.objectId=? and p.objectType=? and (( (p.sendUserId =? or p.sendGroupId =? ) and (p.processType=? or p.processType=?)) or p.processType=? ) ";//bỏ để xuất thẩm định
                + "where p.isActive=1 and p.objectId=? and p.objectType=? and (( (p.sendUserId =? or p.sendGroupId =? ) and (p.processType=? or p.processType=?))) ";
        List lstParam = new ArrayList();
        lstParam.add(fileId);
        lstParam.add(Constants.OBJECT_TYPE.FILES);
        lstParam.add(userId);
        lstParam.add(deptId);
        lstParam.add(Constants.PROCESS_TYPE.COOPERATE);
        lstParam.add(Constants.PROCESS_TYPE.MAIN);
        //lstParam.add(Constants.PROCESS_TYPE.PROPOSE);

        Query countQuery = getSession().createQuery("select count(p) " + hql);
        Query query = getSession().createQuery("select p " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            countQuery.setParameter(i, lstParam.get(i));
        }

        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List<Files> lstResult = query.list();
        GridResult gr = new GridResult(total, lstResult);
        return gr;
    }

    /**
     * tìm luồng xử lý hồ sơ fileId
     *
     * @param fileId
     * @param deptId
     * @param userId
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findAllCoProcessByFileId(Long fileId, Long deptId, Long userId, int start, int count, String sortField) {
        String hql = " from Process p "
                //                + "where p.isActive=1 and p.objectId=? and p.objectType=? and (( (p.sendUserId =? or p.sendGroupId =? ) and (p.processType=? or p.processType=?)) or p.processType=? ) ";//bỏ để xuất thẩm định
                + "where p.isActive=1 and p.objectId=? and p.objectType=? and (( (p.sendUserId =? or p.sendGroupId =? ) and p.processType=?)) ";
        List lstParam = new ArrayList();
        lstParam.add(fileId);
        lstParam.add(Constants.OBJECT_TYPE.FILES);
        lstParam.add(userId);
        lstParam.add(deptId);
        lstParam.add(Constants.PROCESS_TYPE.COOPERATE);
//        lstParam.add(Constants.PROCESS_TYPE.MAIN);
        //lstParam.add(Constants.PROCESS_TYPE.PROPOSE);

        Query countQuery = getSession().createQuery("select count(p) " + hql);
        Query query = getSession().createQuery("select p " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            countQuery.setParameter(i, lstParam.get(i));
        }

        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List<Files> lstResult = query.list();
        GridResult gr = new GridResult(total, lstResult);
        return gr;
    }

    /**
     * tim hồ sơ theo email
     *
     * @param email
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public Files findFilesByCodeEmail(ReceiveEmail email, int start, int count, String sortField) {

        String[] content = email.getContent().split(" ");
        String filesCode = content[0];

        String hql = " from Files t where t.isActive = 1 and (f.isTemp = null or f.isTemp = 0 ) ";
        List lstParam = new ArrayList();
        if (filesCode != null && filesCode.trim().length() > 0) {
            hql += " and lower(c.fileCode) = ? ";
            lstParam.add(filesCode);
        }
        Query countQuery = getSession().createQuery("select count(*) " + hql);
        Query query = getSession().createQuery("select t " + hql + " order by c.fileId desc");
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            countQuery.setParameter(i, lstParam.get(i));
        }

        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List<Files> lstResult = query.list();
        if (!lstResult.isEmpty()) {
            return lstResult.get(0);
        } else {
            return null;
        }
    }

    /**
     * Lưu trạng thái Hồ sơ vào Tin nhắn
     *
     * @param files
     * @param content
     */
    public void saveStatusFiles(Files files, String content) {
        UsersDAOHE userdao = new UsersDAOHE();
        Users users = userdao.findById(files.getUserCreateId());

        ReceiveEmail recEmail = new ReceiveEmail();

        recEmail.setContent(content);
        recEmail.setEmail(users.getEmail());
        recEmail.setIsProcess(-2);
        recEmail.setReceiveTime(new java.util.Date());
        recEmail.setMsgType(2);//cont

        getSession().save(recEmail);
    }

    /**
     * Nhập nội dung trình ký
     *
     * @param form
     * @param userId
     * @param userName
     * @return
     */
    public boolean onSendToSign(FilesForm form, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            if (file == null) {
                bReturn = false;
            } else {
                file.setStatus(Constants.FILE_STATUS.SIGNING);
                file.setStaffSigningId(userId);
                file.setStaffSigningName(userName);
                file.setSigningDate(form.getSigningDate());
                file.setSigningContent(form.getSigningContent());
                getSession().update(file);
                bReturn = true;
            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    /**
     * ký duyệt
     *
     * @param form
     * @param userId
     * @param userName
     * @return
     */
    public boolean onSign(FilesForm form, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            if (file == null) {
                bReturn = false;
            } else {

                file.setStatus(Constants.FILE_STATUS.SIGNED);
                file.setLeaderStaffSignId(userId);
                file.setLeaderStaffSignName(userName);
                file.setSigningDate(form.getSignDate());
                file.setSignContent(form.getSignContent());
                getSession().update(file);
                bReturn = true;
            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    /**
     * Từ chối kí duyệt
     *
     * @param form
     * @param userId
     * @param userName
     * @return
     */
    public boolean onReject(FilesForm form, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else {
                file.setStatus(Constants.FILE_STATUS.REJECT);
                file.setLeaderStaffSignId(userId);
                file.setLeaderStaffSignName(userName);
                file.setSignDate(form.getSignDate());
                file.setSignContent(form.getSignContent());
                file.setModifyDate(dateNow);
                getSession().update(file);
                bReturn = true;
            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    /**
     * get Tên trạng thái hồ sơ
     *
     * @param status
     * @return
     */
    public static String getFileStatusName(Long status) {
        String statusName = "";
        switch (status.intValue()) {
            case 0:
                statusName = "Mới tạo";
                break;
            case 1:
                statusName = "Mới nộp";
                break;
            case 2:
                statusName = "Đã được đề xuất xử lý";
                break;
            case 3:
                statusName = "Đã phân công";
                break;
            case 4:
                statusName = "Đã thẩm định";
                break;
            case 5:
                statusName = "Đã xem xét";
                break;
            case 6:
                statusName = "Đã phê duyệt";
                break;
            case 7:
                statusName = "Chuyên viên KL: SĐBS";
                break;
            case 8:
                statusName = "Trả thẩm định lại";
                break;
            case 9:
                statusName = "Trả xem xét lại";
                break;
            case 10:
                statusName = "Đã tạo giấy phép";
                break;
            case 11:
                statusName = "Đã trình ký giấy phép";
                break;
            case 12:
                statusName = "Đã ký giấy phép";
                break;
            case 13:
                statusName = "Đã từ chối giấy phép";
                break;
            case 14:
                statusName = "Đã tiếp nhận";
                break;
            case 15:
                statusName = "Đã đối chiếu";
                break;
            case 16:
                statusName = "Đã đối chiếu, có sai lệch";
                break;
            case 17:
                statusName = "Đã tiếp nhận hồ sơ sửa đổi bổ sung";
                break;
            case 18:
                statusName = "Mới nộp chờ tiếp nhận SĐBS";
                break;
            case 19:
                statusName = "Đã xem xét yêu cầu SĐBS";
                break;
            case 20:
                statusName = "Đã gửi công văn yêu cầu SĐBS";
                break;
            case 21:
                statusName = "Đã từ chối - Có HD bổ sung";
                break;
            case 22:
                statusName = "Đã trả bản công bố";
                break;
            case 23:
                statusName = "Đã thông báo đối chiếu hồ sơ";
                break;
            case 24:
                statusName = "Đã xem xét đối chiếu hồ sơ";
                break;
            case 25:
                statusName = "Đã trả lại để xem xét đối chiếu lại";
                break;
            case 26:
                statusName = "Đã xem xét công văn SĐBS";
                break;
            case 27:
                statusName = "Đã phê duyệt công văn SĐBS";
                break;
            case 28:
                statusName = "Đã soạn thảo công văn SĐBS";
                break;
            case 29:
                statusName = "Đã trình cục trưởng xem xét hồ sơ";
                break;
            case 30:
                statusName = "Hồ sơ cục trưởng đã quyết định";
                break;
            case 31:
                statusName = "Hồ sơ kế toán đã xác nhận phí thẩm định";
                break;
            case 32:
                statusName = "Hồ sơ kế toán đã xác nhận lệ phí cấp số";
            case 33:
                statusName = "Hồ sơ đã bị VT từ chối tiếp nhận SĐBS";
                break;
        }
        return statusName;
    }

    /**
     * Từ chối Tiếp nhận hồ sơ - Văn thư
     *
     * @param form
     * @param userId
     * @param userName
     * @return
     */
    public boolean rejectReveived(FilesForm form, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else {
                file.setStatus(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                file.setModifyDate(dateNow);
                file.setStaffSigningId(userId);
                file.setStaffSigningName(userName);
                if (form.getSignDate() == null) {
                    file.setSignDate(dateNow);
                } else {
                    file.setSignDate(form.getSignDate());
                }
                //file.setSignContent(form.getSignContent());
                getSession().update(file);
                bReturn = true;
            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    /**
     * tiep nhan ho so - vai tro van thu binhnt53
     *
     * @param form
     * @param deptId
     * @param deptName
     * @param userId
     * @param userName
     * @return
     */
    public boolean onReceivedFile(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Date dateNow = getSysdate();
            Files file = findById(form.getFileId());
            if (file == null) {
                bReturn = false;
            } else {
                //140404
                Long processStatus = file.getStatus();
                if (processStatus.equals(Constants.FILE_STATUS.NEW_TO_ADD)
                        || processStatus.equals(Constants.FILE_STATUS.RECEIVED_REJECT_TO_ADD)) {//neu ho so la nop sdbs k tao so
                    if (form.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT)) {
                        file.setStatus(Constants.FILE_STATUS.RECEIVED_REJECT_TO_ADD);
                        file.setDisplayStatus(getFileStatusName(Constants.FILE_STATUS.RECEIVED_REJECT_TO_ADD));
                        file.setClericalRequest(form.getClericalRequest());
                    } else {
                        file.setStatus(Constants.FILE_STATUS.RECEIVED_TO_ADD);
                        file.setDisplayStatus(getFileStatusName(Constants.FILE_STATUS.RECEIVED_TO_ADD));
                    }
                    //file.setReceiveNo(getNewReceiveNo(deptId));
                } else if (form.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT)) {
                    file.setStatus(Constants.FILE_STATUS.RECEIVED_REJECT);
                    file.setDisplayStatus(getFileStatusName(Constants.FILE_STATUS.RECEIVED_REJECT));
                    file.setClericalRequest(form.getClericalRequest());
                } else {
                    file.setStatus(Constants.FILE_STATUS.RECEIVED);
                    file.setDisplayStatus(getFileStatusName(Constants.FILE_STATUS.RECEIVED));
                    if (file.getReceiveNo() == null || file.getReceiveNo().trim().length() == 0) {
                        file.setReceiveNo(getNewReceiveNo(deptId));
                    }
                }
                file.setModifyDate(dateNow);
//                file.setIsFee(form.getIsFee());
                file.setStaffSigningId(userId);
                file.setStaffSigningName(userName);
                if (form.getSignDate() == null) {
                    file.setSignDate(dateNow);
                } else {
                    file.setSignDate(form.getSignDate());
                }
                //140627 UPDATE BO SUNG THOI GIAN HAN CONG BO, PHAN HOI HO SO
                if (!form.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT)) {
                    file.setReceivedDate(dateNow);
                    ResourceBundle rb = ResourceBundle.getBundle("config");
                    Procedure procedurebo = new Procedure();
                    ProcedureDAOHE procedureDAOHE = new ProcedureDAOHE();
                    procedurebo = procedureDAOHE.findById(file.getFileType());
                    if (procedurebo.getDescription().equalsIgnoreCase("announcement4star")) {
                        file.setDeadlineApprove(getDateWorkingTime(15));
                        file.setDeadlineComment(getDateWorkingTime(15));
                    } else {
                        int CB = 0, PH = 0;
                        try {
                            CB = Integer.parseInt(rb.getString(procedurebo.getDescription() + "_CB"));
                        } catch (NumberFormatException ex) {
                            log.error(ex.getMessage());
                        }
                        try {
                            PH = Integer.parseInt(rb.getString(procedurebo.getDescription() + "_PH"));
                        } catch (NumberFormatException ex) {
                            log.error(ex.getMessage());
                        }
                        if (CB > 0) {
                            file.setDeadlineApprove(getDateWorkingTime(CB));
                        }
                        if (PH > 0) {
                            file.setDeadlineComment(getDateWorkingTime(PH));
                        }
                    }

                }
                ProcessDAOHE psdhe = new ProcessDAOHE();
                Process ptmp = psdhe.getProcessByAction(file.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, processStatus, Constants.FILE_STATUS.NEW_CREATE);
                if (ptmp != null) {
                    if (processStatus.equals(Constants.FILE_STATUS.NEW_TO_ADD)) {
                        if (form.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT)) {
                            ptmp.setStatus(Constants.FILE_STATUS.RECEIVED_REJECT_TO_ADD);
                        } else {
                            ptmp.setStatus(Constants.FILE_STATUS.RECEIVED_TO_ADD);
                        }
                    } else if (form.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT)) {
                        ptmp.setStatus(form.getStatus());
                    } else {
                        ptmp.setStatus(Constants.FILE_STATUS.RECEIVED);
                    }
                    ptmp.setLastestComment(form.getStaffRequest());
                    getSession().update(ptmp);
                }
                Process newP = new Process();
                newP.setObjectId(form.getFileId());
                newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                newP.setSendDate(dateNow);
                newP.setSendGroup(deptName);
                newP.setSendGroupId(deptId);
                newP.setSendUserId(userId);
                newP.setSendUser(userName);
                // Neu khong co luong
                if (file.getStatus().equals(Constants.FILE_STATUS.RECEIVED)) {
                    // Gui toi chinh don vi quan ly de tiep nhan
                    newP.setReceiveDate(dateNow);
                    if (form.getProcessDeptName() != null && form.getProcessDeptId() != null) {
                        newP.setReceiveGroup(form.getProcessDeptName());
                        newP.setReceiveGroupId(form.getProcessDeptId());
                    } else {
                        newP.setReceiveGroup(deptName);
                        newP.setReceiveGroupId(deptId);
                    }
                    newP.setProcessStatus(file.getStatus()); // De xu ly
                    newP.setStatus(0l); // Moi den chua xu ly
                    newP.setIsActive(1l);
                } else {
                    if (file.getStatus().equals(Constants.FILE_STATUS.RECEIVED_TO_ADD)) {
                        //gui cho chuyen vien tham dinh ho so
                        newP.setReceiveDate(dateNow);
                        //140407
                        ProcessDAOHE processDAOHE = new ProcessDAOHE();
                        Process userAction = processDAOHE.findProcessByActionEvaluate(file.getFileId());
                        if (userAction != null) {
                            newP.setReceiveUser(userAction.getReceiveUser());
                            newP.setReceiveUserId(userAction.getReceiveUserId());
                            newP.setReceiveGroup(userAction.getReceiveGroup());
                            newP.setReceiveGroupId(userAction.getReceiveGroupId());
                        } else {
                            newP.setReceiveGroup(deptName);
                            newP.setReceiveGroupId(deptId);
                        }
                        //
                        newP.setProcessStatus(file.getStatus()); // De xu ly
                        newP.setStatus(0l); // Moi den chua xu ly
                        newP.setIsActive(1l);
                    }
                    if (file.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT)) {
                        //tu choi tiep nhan
                        newP.setReceiveDate(dateNow);
                        ProcessDAOHE processDAOHE = new ProcessDAOHE();//140407
                        //Process userAction = processDAOHE.findProcessByActionEvaluate(file.getFileId());//141217u binhnt update thay lai luong tra lai doanh nghiep khi tu choi tiep nhan ho so
                        Process userAction = processDAOHE.getProcessByAction(file.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.NEW, Constants.FILE_STATUS.NEW_CREATE);
                        if (userAction != null) {
                            //141217u binhnt update thay lai luong tra lai doanh nghiep khi tu choi tiep nhan ho so
                            newP.setReceiveUser(userAction.getSendUser());
                            newP.setReceiveUserId(userAction.getSendUserId());
                            newP.setReceiveGroup(userAction.getSendGroup());
                            newP.setReceiveGroupId(userAction.getSendGroupId());
                            /*
                             newP.setReceiveUser(userAction.getReceiveUser());
                             newP.setReceiveUserId(userAction.getReceiveUserId());
                             newP.setReceiveGroup(userAction.getReceiveGroup());
                             newP.setReceiveGroupId(userAction.getReceiveGroupId());
                             */
                        } else {
                            newP.setReceiveGroup(deptName);
                            newP.setReceiveGroupId(deptId);
                        }
                        //
                        newP.setProcessStatus(file.getStatus()); // De xu ly
                        newP.setStatus(0l); // Moi den chua xu ly
                        newP.setIsActive(1l);

                    }
                    if (file.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT_TO_ADD)) {//tu choi tiep nhan sdbs                        
                        newP.setReceiveDate(dateNow);
                        if (ptmp != null) {
                            //141217u binhnt update thay lai luong tra lai doanh nghiep khi tu choi tiep nhan ho so
                            newP.setReceiveUser(ptmp.getSendUser());
                            newP.setReceiveUserId(ptmp.getSendUserId());
                            newP.setReceiveGroup(ptmp.getSendGroup());
                            newP.setReceiveGroupId(ptmp.getSendGroupId());
                        } else {
                            newP.setReceiveGroup(deptName);
                            newP.setReceiveGroupId(deptId);
                        }
                        newP.setProcessStatus(file.getStatus()); // De xu ly
                        newP.setStatus(0l); // Moi den chua xu ly
                        newP.setIsActive(1l);
                    }
                }
                getSession().save(newP);
//                }
                getSession().update(file);
                MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                String msg = "Ho so ma: " + file.getFileCode() + " cua doanh nghiep: " + file.getBusinessName() + " dang trong trang thai: da tiep nhan";
                msdhe.saveMessageSMS(userId, file.getUserCreateId(), msg);
                //email
                MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                String msge = "";
                msge = "Hồ sơ mã: " + file.getFileCode() + " của doanh nghiệp: " + file.getBusinessName() + " đang trong trạng thái: Đã tiếp nhận";
                msedhe.saveMessageEmail(userId, file.getUserCreateId(), msge);
                //send group leader of dept 140915 binhnt53
                EmailSmsDAO emdao = new EmailSmsDAO();
                boolean ck = emdao.alertLeaderOfStaffAssignFiles(deptId, file.getFileCode(), file.getBusinessName());
                //
                bReturn = true;
            }
        } catch (Exception ex) {
            bReturn = false;
            log.error(ex.getMessage());
        }
        return bReturn;
    }

    /**
     * hieptq update 120114
     *
     * @param form
     * @param deptId
     * @param deptName
     * @param userId
     * @param userName
     * @param lstObjectId
     * @return
     */
    public boolean onReceivedMoreFile(FilesForm form, Long deptId, String deptName, Long userId, String userName, String lstObjectId) {
        boolean bReturn = true;
        boolean bCheckCount = true;
        String[] lstObjectIdSplit = lstObjectId.split(",");
        int countObj = lstObjectIdSplit.length;
        try {//form.setStatus(Constants.FILE_STATUS.RECEIVED);
            for (int i = 0; i < countObj; i++) {
                Long fileId = Long.parseLong(lstObjectIdSplit[i]);
                Files file = findById(fileId);
                if (file == null) {
                    bReturn = false;
                } else {
                    Long processStatus = file.getStatus();
                    ProcedureDepartmentDAOHE prodepdaohe
                            = new ProcedureDepartmentDAOHE();
                    List lstCQXL = prodepdaohe.getAllProcedureDepartmentByProcedureId(file.getFileType());
                    //binhnt update 150918
                    if ((lstCQXL == null || lstCQXL.size() > 1)
                            && !processStatus.equals(Constants.FILE_STATUS.NEW_TO_ADD)) {
                        bCheckCount = false;
                        continue;
                    }
                    //140404
                    if (processStatus.equals(Constants.FILE_STATUS.NEW_TO_ADD)) {//neu ho so la nop sdbs k tao so
                        file.setStatus(Constants.FILE_STATUS.RECEIVED_TO_ADD);
                        file.setDisplayStatus(getFileStatusName(Constants.FILE_STATUS.RECEIVED_TO_ADD));
                    } else {
                        file.setStatus(Constants.FILE_STATUS.RECEIVED);
                        file.setDisplayStatus(getFileStatusName(Constants.FILE_STATUS.RECEIVED));
                        if (file.getReceiveNo() == null
                                || file.getReceiveNo().trim().length() == 0) {
                            file.setReceiveNo(getNewReceiveNo(deptId));
                        }
                    }
                    file.setReceivedDate(getSysdate());
                    file.setStaffSigningId(userId);
                    file.setStaffSigningName(userName);
                    file.setSignDate(getSysdate());
                    //140627 UPDATE BO SUNG THOI GIAN HAN CONG BO, PHAN HOI HO SO
                    ResourceBundle rb = ResourceBundle.getBundle("config");
                    Procedure procedurebo = new Procedure();
                    ProcedureDAOHE procedureDAOHE = new ProcedureDAOHE();
                    procedurebo = procedureDAOHE.findById(file.getFileType());
                    int CB = 0, PH = 0;
                    try {
                        CB = Integer.parseInt(rb.getString(procedurebo.getDescription() + "_CB"));
                    } catch (NumberFormatException ex) {
                        log.error(ex.getMessage());;
                    }
                    try {
                        PH = Integer.parseInt(rb.getString(procedurebo.getDescription() + "_PH"));
                    } catch (NumberFormatException ex) {
                        log.error(ex.getMessage());;
                    }
                    if (CB > 0) {
                        file.setDeadlineApprove(getDateWorkingTime(CB));
                    }
                    if (PH > 0) {
                        file.setDeadlineComment(getDateWorkingTime(PH));
                    }
                    ProcessDAOHE psdhe = new ProcessDAOHE();
                    Process ptmp = psdhe.getProcessByAction(file.getFileId(),
                            Constants.Status.ACTIVE,
                            Constants.OBJECT_TYPE.FILES,
                            processStatus,
                            Constants.FILE_STATUS.NEW_CREATE);
                    if (ptmp != null) {
                        if (processStatus.equals(Constants.FILE_STATUS.NEW_TO_ADD)) {
                            ptmp.setStatus(Constants.FILE_STATUS.RECEIVED_TO_ADD);
                        } else {
                            ptmp.setStatus(Constants.FILE_STATUS.RECEIVED);
                        }
                        getSession().update(ptmp);
                    }
                    Process newP = new Process();
                    newP.setObjectId(fileId);
                    newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                    newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                    newP.setSendDate(getSysdate());
                    newP.setSendGroup(deptName);
                    newP.setSendGroupId(deptId);
                    newP.setSendUserId(userId);
                    newP.setSendUser(userName);
                    if (file.getStatus().equals(Constants.FILE_STATUS.RECEIVED)) {
                        // Gui toi chinh don vi quan ly de tiep nhan
                        newP.setReceiveDate(getSysdate());
                        if (form != null && form.getProcessDeptName() != null
                                && form.getProcessDeptId() != null) {
                            newP.setReceiveGroup(form.getProcessDeptName());
                            newP.setReceiveGroupId(form.getProcessDeptId());
                        } else if (!lstCQXL.isEmpty()) {
                            ProcedureDepartment prodept
                                    = (ProcedureDepartment) lstCQXL.get(0);
                            if (prodept != null && prodept.getProcessDeptId()
                                    != null && prodept.getProcessDeptName() != null) {
                                newP.setReceiveGroup(prodept.getProcessDeptName());
                                newP.setReceiveGroupId(prodept.getProcessDeptId());
                            }
                        } else {
                            newP.setReceiveGroup(deptName);
                            newP.setReceiveGroupId(deptId);
                        }

                        newP.setProcessStatus(file.getStatus()); // De xu ly
                        newP.setStatus(0l); // Moi den chua xu ly
                        newP.setIsActive(1l);
                    } else {//neu la sdbs gui cho chuyen vien tham dinh ho so
                        if (file.getStatus().equals(Constants.FILE_STATUS.RECEIVED_TO_ADD)) {
                            newP.setReceiveDate(getSysdate());
                            //140407
                            ProcessDAOHE processDAOHE = new ProcessDAOHE();
                            Process userAction = processDAOHE.
                                    findProcessByActionEvaluate(file.getFileId());
                            if (userAction != null) {
                                newP.setReceiveUser(userAction.getReceiveUser());
                                newP.setReceiveUserId(userAction.getReceiveUserId());
                                newP.setReceiveGroup(userAction.getReceiveGroup());
                                newP.setReceiveGroupId(userAction.getReceiveGroupId());
                            } else {
                                newP.setReceiveGroup(deptName);
                                newP.setReceiveGroupId(deptId);
                            }
                            //
                            newP.setProcessStatus(file.getStatus()); // De xu ly
                            newP.setStatus(0l); // Moi den chua xu ly
                            newP.setIsActive(1l);
                            getSession().save(newP);
                        }
                        if (file.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT)) {
                            //tu choi tiep nhan
                            newP.setReceiveDate(getSysdate());
                            ProcessDAOHE processDAOHE
                                    = new ProcessDAOHE();//140407
                            Process userAction
                                    = processDAOHE.getProcessByAction(file.getFileId(),
                                            Constants.Status.ACTIVE,
                                            Constants.OBJECT_TYPE.FILES,
                                            Constants.FILE_STATUS.NEW,
                                            Constants.FILE_STATUS.NEW_CREATE);
                            if (userAction != null) {
                                newP.setReceiveUser(userAction.getSendUser());
                                newP.setReceiveUserId(userAction.getSendUserId());
                                newP.setReceiveGroup(userAction.getSendGroup());
                                newP.setReceiveGroupId(userAction.getSendGroupId());
                            } else {
                                newP.setReceiveGroup(deptName);
                                newP.setReceiveGroupId(deptId);
                            }
                            newP.setProcessStatus(file.getStatus()); // De xu ly
                            newP.setStatus(0l); // Moi den chua xu ly
                            newP.setIsActive(1l);

                        }
                        // Tra lai cho doanh nghiep khong xu ly gi them
                    }
                    getSession().save(newP);
                    getSession().update(file);
                    MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                    String msg = "Ho so ma: " + file.getFileCode() + " cua doanh nghiep: " + file.getBusinessName() + " dang trong trang thai: da tiep nhan";
                    msdhe.saveMessageSMS(userId, file.getUserCreateId(), msg);
                    //email
                    MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                    String msge = "";
                    msge = "Hồ sơ mã: " + file.getFileCode() + " của doanh nghiệp: " + file.getBusinessName() + " đang trong trạng thái: Đã tiếp nhận";
                    msedhe.saveMessageEmail(userId, file.getUserCreateId(), msge);
                    //send group leader of dept 140915 binhnt53
                    EmailSmsDAO emdao = new EmailSmsDAO();
                    boolean ck = emdao.alertLeaderOfStaffAssignFiles(deptId, file.getFileCode(), file.getBusinessName());
                    bReturn = true;
                }
            }
        } catch (Exception en) {
            bReturn = false;
            log.error(en.getMessage());
        }
        if (!bCheckCount) {
            return false;
        }
        return bReturn;
    }

    /**
     * 140815 tra ho so - vai tro van thu - binhnt53
     *
     * @param form
     * @param deptId mã đơn vị
     * @param deptName tên đơn vị
     * @param userId mã tk đăng nhập
     * @param userName tk đăng nhập
     * @return
     */
    public boolean onReturnFiles(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        List<Long> action = new ArrayList();
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            if (file == null || file.getFileId() == null) {
                bReturn = false;
            } else {
                //140404
                Long processStatus = file.getStatus();
                file.setStatus(Constants.FILE_STATUS.GIVE_BACK);
                file.setDisplayStatus(getFileStatusName(Constants.FILE_STATUS.GIVE_BACK));
//                file.setClericalRequest(form.getClericalRequest());
                file.setClericalRequest("Văn thư đóng dấu số.");
                file.setModifyDate(dateNow);
                file.setStaffSigningId(userId);
                file.setStaffSigningName(userName);
                if (form.getSignDate() == null) {
                    file.setSignDate(dateNow);
                } else {
                    file.setSignDate(form.getSignDate());
                }
                //Cap nhat process
                ProcessDAOHE pdhe = new ProcessDAOHE();
                Process ptmp = pdhe.getProcessByAction(file.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, processStatus, Constants.FILE_STATUS.NEW_CREATE);
                if (ptmp != null) {
                    ptmp.setStatus(Constants.FILE_STATUS.GIVE_BACK);
                    ptmp.setLastestComment(form.getStaffRequest());
                    getSession().update(ptmp);
                }
                if (file.getStatus().equals(Constants.FILE_STATUS.GIVE_BACK)) {
                    Process newP = new Process();
                    newP.setObjectId(form.getFileId());
                    newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                    newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                    newP.setSendDate(dateNow);
                    newP.setSendGroup(deptName);
                    newP.setSendGroupId(deptId);
                    newP.setSendUserId(userId);
                    newP.setSendUser(userName);
                    // gui toi doanh nghiep nop ho so
                    ProcessDAOHE processDAOHE = new ProcessDAOHE();
                    action.add(Constants.FILE_STATUS.NEW);
                    Process userAction = processDAOHE.findProcessByAction(file.getFileId(), action);
                    if (userAction != null) {
                        newP.setReceiveUser(userAction.getSendUser());
                        newP.setReceiveUserId(userAction.getSendUserId());
                        newP.setReceiveGroup(userAction.getSendGroup());
                        newP.setReceiveGroupId(userAction.getSendGroupId());
                    } else {
                        newP.setReceiveGroup(deptName);
                        newP.setReceiveGroupId(deptId);
                    }
                    newP.setReceiveDate(dateNow);

                    newP.setProcessStatus(Constants.FILE_STATUS.GIVE_BACK); // De xu ly
                    newP.setStatus(0L); // ket thuc tra ho so cho doanh nghiep
                    newP.setIsActive(1l);
                    getSession().save(newP);
                } else {
                    //do nothing
                }

                getSession().update(file);
                MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                String msg = "Ho so ma: " + file.getFileCode() + " cua doanh nghiep: " + file.getBusinessName() + " dang trong trang thai: Da tra ban cong bo";
                msdhe.saveMessageSMS(userId, file.getUserCreateId(), msg);
                //email
                MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                String msge;
                msge = "Hồ sơ mã: " + file.getFileCode() + " của doanh nghiệp: " + file.getBusinessName() + " đang trong trạng thái: Đã trả bản công bố";
                msedhe.saveMessageEmail(userId, file.getUserCreateId(), msge);

                getSession().update(file);//cap nhat ho so
                bReturn = true;

            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    /**
     * Cấp số tiếp nhận/ xác nhận
     *
     * @param deptId
     * @param deptName
     * @param fileType
     * @return
     */
    public String getNewReceiptNo(Long deptId, Long fileType) {
        //check file type
        ProcedureDAOHE procedurehe = new ProcedureDAOHE();
        Procedure procedure = procedurehe.findById(fileType);
        //lay don vi
        DepartmentDAOHE departmentDaohe = new DepartmentDAOHE();
        Department deptBo = departmentDaohe.findBOById(deptId);
        Long nCount = 1L;
        try {//lay so cong bo cua don vi
            if (deptBo != null && deptBo.getDeptCode() != null) {
                CountNoDAOHE cndaohe = new CountNoDAOHE();
                CountNo cnbo = cndaohe.returnCountNoByCode(deptBo.getDeptCode());
                if (cnbo != null && cnbo.getAnnouncementNo() > 0L) {
                    nCount = cnbo.getAnnouncementNo();
                    cnbo.setAnnouncementNo(nCount + 1);
                    getSession().update(cnbo);
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            nCount = 1L;
        }
        String prefix = "";
        String fileTypeName = "";
        switch (procedure.getDescription()) {
            case "announcementFile01":
                fileTypeName = "TNCB";
                break;
            case "announcementFile03":
                fileTypeName = "TNCB";
                break;
            case "announcementFile05":
                fileTypeName = "CB";
                break;
            case "announcement4star":
                fileTypeName = "SP";
                break;
            case "confirmFuncImport":
                fileTypeName = "XNCB";
                break;
            case "confirmFuncVN":
                fileTypeName = "XNCB";
                break;
            case "confirmNormalVN":
                fileTypeName = "XNCB";
                break;
            case "confirmNormalImport":
                fileTypeName = "XNCB";
                break;
            default:
                fileTypeName = "XNCB";
        }
        if (deptBo != null && deptBo.getDeptCode() != null) {
            if (deptBo.getDeptCode().equals("ATTP")) {
                prefix = "ATTP-" + fileTypeName;
            } else {
                prefix = "YT" + deptBo.getDeptCode() + "-" + fileTypeName;
            }
        } else {
            prefix = "ATTP-" + fileTypeName;
        }
        Date currentDate = new Date();
        try {
            currentDate = getSysdate();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        String fileCode = nCount + "/" + DateTimeUtils.convertDateToString(currentDate, "yyyy") + "/" + prefix;
//        String fileCode = nCount + "         " + DateTimeUtils.convertDateToString(currentDate, "yyyy");
        return fileCode;
    }

    /**
     * Cấp số tiếp nhận
     *
     * @param deptId
     * @return
     */
    public String getNewReceiveNo(Long deptId) {
        //check file type
//        ProcedureDAOHE procedurehe = new ProcedureDAOHE();
//        Procedure procedure = procedurehe.findById(fileType);
        //lay don vi
        DepartmentDAOHE departmentDaohe = new DepartmentDAOHE();
        Department deptBo = departmentDaohe.findBOById(deptId);
        Long nCount = 1L;
        try {//lay so cong bo cua don vi
            if (deptBo != null && deptBo.getDeptCode() != null) {
                CountNoDAOHE cndaohe = new CountNoDAOHE();
                CountNo cnbo = cndaohe.returnCountNoByCode(deptBo.getDeptCode());
                if (cnbo != null && cnbo.getAnnouncementNo() > 0L) {
                    nCount = cnbo.getReceiveNo();
                    cnbo.setReceiveNo(nCount + 1);
                    getSession().update(cnbo);
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            nCount = 1L;
        }
        String fileCode = nCount.toString();
        return fileCode;
    }

    /**
     *
     * @param deptId
     * @return
     */
    public String getNewSendNo(Long deptId) {
        DepartmentDAOHE departmentDaohe = new DepartmentDAOHE();
        Department deptBo = departmentDaohe.findBOById(deptId);
        Long nCount = 1L;
        try {//lay so cong bo cua don vi
            if (deptBo != null && deptBo.getDeptCode() != null) {
                CountNoDAOHE cndaohe = new CountNoDAOHE();
                CountNo cnbo = cndaohe.returnCountNoByCode(deptBo.getDeptCode());
                if (cnbo != null && cnbo.getSendNo() > 0L) {
                    nCount = cnbo.getSendNo();
                    //cnbo.setSendNo(nCount + 1);
                    //getSession().update(cnbo);
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            nCount = 1L;
        }
        return nCount.toString();
    }

    /**
     * Kiểm tra tồn tại giấy tiếp nhận xác nhận
     *
     * @param reIssueForm
     * @return
     */
    public Announcement checkAnnouncementFilesExist(ReIssueForm reIssueForm) {
        if (reIssueForm == null) {
            return null;
        }
        if (reIssueForm.getFormNumber() != null && reIssueForm.getFormNumber().trim().length() > 0) {
            String hql = " from Announcement a where a.isActive = 1 ";
            List lstParam = new ArrayList();
            hql += " and lower(a.announcementNo) = ? ";
            lstParam.add(reIssueForm.getFormNumber());
            Query countQuery = getSession().createQuery("select count(*) " + hql);
            Query query = getSession().createQuery("select a " + hql + " order by a.announcementId desc");
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }
            List<Announcement> lstResult = query.list();
            if (!lstResult.isEmpty()) {
                return lstResult.get(0);
            } else {
                return null;
            }
        }
        return null;

    }

    /**
     * #260214 binhnt53 lay thong tin trang thai
     *
     * @param statusCode
     * @return
     */
    public Category getStatus(String statusCode) {
        Category bo = new Category();
        return bo;
    }

    /**
     * Cấp số tiếp nhận/ xác nhận cho Doanh nghiệp
     *
     * @param userId
     * @param userName
     * @param fileType
     * @return
     */
    public String getReceiptNoNew(Long userId, String userName, Long fileType) {
        ProcedureDAOHE procedurehe = new ProcedureDAOHE();
        Procedure procedure = procedurehe.findById(fileType);
        String hql = "select count(f) from Files f where userCreateId=? and (f.isTemp = null or f.isTemp = 0) and isActive=1";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, userId);
        int nCount = (int) (long) (Long) query.uniqueResult();
        nCount += 1;
        String prefix = "";
        if (userName != null && userName.trim().length() > 0) {
            if (procedure.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01) || procedure.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03) || procedure.getDescription().equals(Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT)) {
                prefix = userName + "-CBHQ";
            } else {
                prefix = userName + "-CBPH";
            }
        }
        Date currentDate = new Date();
        try {
            currentDate = getSysdate();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        String fileCode = nCount + "/" + DateTimeUtils.convertDateToString(currentDate, "yyyy") + "/" + prefix;
        return fileCode;
    }

    /**
     * trả về form hồ sơ
     *
     * @param entity
     * @return
     */
    public FilesForm returnFilesForm(Files entity) {
        FilesForm form = null;
        if (entity != null) {
            form = new FilesForm(entity);

            if (entity.getAnnouncementId() != null) {
                Announcement ann = (Announcement) findById(Announcement.class, "announcementId", entity.getAnnouncementId());
                form.setAnnouncement(
                        new AnnouncementForm(ann));
                form.setAnnouncementReceiptPaperForm(
                        new AnnouncementReceiptPaperForm());
            }

            if (entity.getReIssueFormId() != null) {
                ReIssueForm re = (ReIssueForm) findById(ReIssueForm.class, "reIssueFormId", entity.getReIssueFormId());
                form.setReIssueForm(
                        new ReIssueFormForm(re));
            }

            if (entity.getDetailProductId() != null) {
                DetailProduct ann = (DetailProduct) findById(DetailProduct.class, "detailProductId", entity.getDetailProductId());
                form.setDetailProduct(
                        new DetailProductForm(ann));
            }

            if (entity.getTestRegistrationId() != null) {
                TestRegistration ann = (TestRegistration) findById(TestRegistration.class, "testRegistrationId", entity.getTestRegistrationId());
                form.setTestRegistration(
                        new TestRegistrationForm(ann));
                form.setConfirmImportSatistPaperForm(
                        new ConfirmImportSatistPaperForm());
            }

            try {
                if (entity.getAnnouncementReceiptPaperId() != null) {
                    AnnouncementReceiptPaper annp = (AnnouncementReceiptPaper) findById(AnnouncementReceiptPaper.class, "announcementReceiptPaperId", entity.getAnnouncementReceiptPaperId());
                    form.setAnnouncementReceiptPaperForm(
                            new AnnouncementReceiptPaperForm(annp));
                }

                if (entity.getConfirmImportSatistPaperId() != null) {
                    ConfirmImportSatistPaper arp = (ConfirmImportSatistPaper) findById(AnnouncementReceiptPaper.class, "confirmImportSatistPaperId", entity.getAnnouncementReceiptPaperId());
                    form.setConfirmImportSatistPaperForm(
                            new ConfirmImportSatistPaperForm(arp));
                }
            } catch (Exception e) {
                log.error(e);
            }

        }
        return form;
    }

    //140404
    /**
     * Nhập kết quả thẩm định SDBS
     *
     * @param form
     * @param deptId
     * @param deptName
     * @param userId
     * @param userName
     * @return
     */
    public boolean onFeedbackEvaluate(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());//lay thong tin chi tiet ho so
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else {
                // Cap nhat trang thai ho so
                file.setStatus(form.getStatus());
                file.setDisplayStatus(getFileStatusName(form.getStatus()));
                String dateTime = DateTimeUtils.convertDateToString(dateNow, "dd/MM/yyyy HH:mm");
                file.setStaffRequest(userName + " " + dateTime + ":\n" + form.getStaffRequest());
                file.setDisplayRequest(form.getStaffRequest());
                file.setModifyDate(dateNow);
                file.setEffectiveDate(form.getEffectiveDate());
                // Cap nhat process
                ProcessDAOHE pdhe = new ProcessDAOHE();
                Process p = pdhe.getWorkingProcess(form.getFileId(), Constants.OBJECT_TYPE.FILES, deptId, userId);
                if (p != null) {
                    p.setStatus(form.getStatus());
                    p.setLastestComment(form.getStaffRequest());
                    getSession().update(p);
                }
                // Neu co luong thi chay theo luong
                if (file.getFlowId() != null && file.getFlowId() > 0) {
                    FlowDAOHE fdhe = new FlowDAOHE();
                    if (Constants.FILE_STATUS.EVALUATED.equals(form.getStatus())) {
                        fdhe.moveDocumentToNextNodeByAction(deptId, deptName, userId, userName, file.getFileId(), "thẩm định");
                    } else {
                        //fdhe.moveDocumentToPreviousNode(deptId, deptName, userId, userName, file.getFileId(), file.getPreviousNodeId());
                    }
                } else // Neu khong co luong thi tu xu thoi :-)
                 if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED)) {
                        // Tham dinh oke, gui tiep cho cho lanh dao don vi review
                        Process newP = new Process();
                        newP.setObjectId(form.getFileId());
                        newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                        newP.setSendDate(dateNow);
                        newP.setSendGroup(deptName);
                        newP.setSendGroupId(deptId);
                        newP.setSendUserId(userId);
                        newP.setSendUser(userName);
                        // Gui toi chinh don vi quan ly de xem xet
                        newP.setReceiveDate(dateNow);
                        newP.setReceiveGroup(deptName);
                        newP.setReceiveGroupId(deptId);

                        newP.setProcessStatus(form.getStatus()); // De xu ly
                        newP.setStatus(0l); // Moi den chua xu ly
                        newP.setIsActive(1l);
                        getSession().save(newP);
                    } else {
                        // Tra lai cho doanh nghiep khong xu ly gi them
                    }
                //insert noi dung tham dinh
                if (form.getEvaluationRecordsForm() != null) {
                    EvaluationRecordsForm evaluationRecordsForm = new EvaluationRecordsForm();

                    evaluationRecordsForm.setCreateDate(dateNow);
                    evaluationRecordsForm.setSendDate(file.getSendDate());
                    evaluationRecordsForm.setBusinessName(file.getBusinessName());
                    evaluationRecordsForm.setBusinessAddress(file.getBusinessAddress());
                    evaluationRecordsForm.setProductName(file.getProductName());
                    evaluationRecordsForm.setLegal(form.getEvaluationRecordsForm().getLegal());
                    evaluationRecordsForm.setLegalContent(form.getEvaluationRecordsForm().getLegalContent());
                    evaluationRecordsForm.setFoodSafetyQuality(form.getEvaluationRecordsForm().getFoodSafetyQuality());
                    evaluationRecordsForm.setFoodSafetyQualityContent(form.getEvaluationRecordsForm().getFoodSafetyQualityContent());
                    evaluationRecordsForm.setEffectUtility(form.getEvaluationRecordsForm().getEffectUtility());
                    evaluationRecordsForm.setEffectUtilityContent(form.getEvaluationRecordsForm().getEffectUtilityContent());
                    evaluationRecordsForm.setFilesStatus(form.getEvaluationRecordsForm().getFilesStatus());
//                    evaluationRecordsForm.setFilesStatusL(form.getEvaluationRecordsForm().getFilesStatusL());
                    evaluationRecordsForm.setMainContent(form.getEvaluationRecordsForm().getMainContent());
//                    evaluationRecordsForm.setMainContentL(form.getEvaluationRecordsForm().getMainContentL());
                    evaluationRecordsForm.setFirstStaffId(userId);
                    evaluationRecordsForm.setFirstStaffName(userName);
                    evaluationRecordsForm.setSecondStaffId(userId);
                    evaluationRecordsForm.setSecondStaffName(userName);
                    evaluationRecordsForm.setThirdStaffId(userId);
                    evaluationRecordsForm.setThirdStaffName(userName);
                    evaluationRecordsForm.setLeederStaffId(userId);
                    evaluationRecordsForm.setLeederStaffName(userName);
                    evaluationRecordsForm.setFilesId(file.getFileId());

                    EvaluationRecords evaluationRecordsBo;
                    evaluationRecordsBo = evaluationRecordsForm.convertToEntity();
                    getSession().save(evaluationRecordsBo);
                }
                update(file);
            }
        } catch (Exception en) {
            bReturn = false;
            log.error(en.getMessage());
        }
        return bReturn;
    }

    /**
     * Haitv21 Update repository of file via fileID
     *
     * @param fileId
     * @param repId
     * @return
     * @throws Exception
     */
    public boolean updateRepository(Long fileId, Long repId) throws Exception {
        boolean result = false;
        Files file = findById(fileId);
        file.setRepositoriesId(repId);
        file.setRepDate(getSysdate());
        getSession().update(file);
        return result;
    }

    /**
     * lấy hồ sơ có tiếp nhận công bố
     *
     * @param id
     * @return
     */
    public Files getFilesByAnnrpId(Long id) {
        List<Files> lstItem;
        Files item = null;
        try {
            if (id != null && id > 0) {
                StringBuilder stringBuilder = new StringBuilder(" from Files a ");
                stringBuilder.append("  where a.announcementReceiptPaperId=? ");
                Query query = getSession().createQuery(stringBuilder.toString());
                query.setParameter(0, id);
                lstItem = query.list();
                if (lstItem != null && lstItem.size() > 0) {
                    item = lstItem.get(0);
                }
            }
        } catch (HibernateException ex) {
            log.error(ex.getMessage());
        }
        return item;
    }

    /**
     * 140616 - binhnt53 lay thong tin toan bo file ho so trong ho so ca nhan
     *
     * @param input
     * @param userId
     * @return
     */
    public List getAttachsOfUserAttach(String input, Long userId) {
        String lstAttach[] = input.split(";");
        String hql = "select u from UserAttachs u where ";
        hql += " u.createdBy =? ";
        hql += " and u.attachName in (:lstAttach) ";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, userId);
        query.setParameterList("lstAttach", lstAttach);

        List lst = query.list();
        return lst;
    }

    /**
     * function tra ve thong tin ket qua xu ly hop quy phu hop -- hieptq 040614
     *
     * @param fileId
     * @return
     * @throws JAXBException
     */
    public String prepareAnnouceHandling(Long fileId) throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder(" from Files f ");
        stringBuilder.append("  where f.fileId=? ");
        Query query = getSession().createQuery(stringBuilder.toString());
        query.setParameter(0, fileId);
        List<Files> lstItem = new ArrayList();
        lstItem = query.list();
        Files item = null;
        if (lstItem != null && lstItem.size() > 0) {
            item = lstItem.get(0);
        }
        ANNOUCE_HANDLING ann = new ANNOUCE_HANDLING();
        if (item.getStatus() == 2 || item.getStatus() == 3 || item.getStatus() == 4 || item.getStatus() == 5
                || item.getStatus() == 8 || item.getStatus() == 9 || item.getStatus() == 10 || item.getStatus() == 11 || item.getStatus() == 12 || item.getStatus() == 13
                || item.getStatus() == 15 || item.getStatus() == 16 || item.getStatus() == 19) {
            ann.setSTATUS(2l);
        } else if (item.getStatus() == 17) {
            ann.setSTATUS(14l);
        } else if (item.getStatus() == 18) {
            ann.setSTATUS(1l);
        } else if (item.getStatus() == 19) {
            ann.setSTATUS(2l);
        } else if (item.getStatus() == 20) {
            ann.setSTATUS(7l);
        } else {
            ann.setSTATUS(item.getStatus());
        }

        ann.setSTATUS(item.getStatus());
        ann.setCONFIRM_ANNOUNCE_NUMBER("1");
        ann.setEFFECT_DATE(item.getSendDate());
        ann.setFILE_ATTP_CODE(item.getFileCode());
        ann.setFILE_CODE("3");
        ann.setPROCESS_DATE(item.getApproveDate());
        ann.setPROCESS_ID(3l);
        String result;
        java.io.StringWriter sw = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(ANNOUCE_HANDLING.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                true);
        jaxbMarshaller.marshal(ann, sw);
        result = sw.toString();

        return result;
    }

    /**
     * lấy ngày làm việc
     *
     * @param numberDate
     * @return
     */
    public Date getDateWorkingTime(int numberDate) {
        Date startDate = null;
        try {
            startDate = getSysdate();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        int balanceDay = numberDate % 5;
        int numberOfWeek = numberDate / 5;
        int numberDateWorking = numberOfWeek * 7;
        Calendar endCalendar = DateTimeUtils.DateToCalendar(startDate);
        //lay ra ngay lam viec nop ho so
        while (checkIsDayOff(DateTimeUtils.CalendarToDate(endCalendar)) == true || DateTimeUtils.checkIsWeekend(endCalendar) == true) {
            endCalendar.add(Calendar.DATE, 1);
        }
        //them ngay lam viec da tinh t7 cn
        endCalendar.add(Calendar.DATE, numberDateWorking);
        if (balanceDay > 0) {//neu co so du + phan du vao ngay
            endCalendar.add(Calendar.DATE, balanceDay);
        }
        endCalendar = DateTimeUtils.checkWeekend(endCalendar);
        Date endDate = DateTimeUtils.CalendarToDate(endCalendar);
        endCalendar.add(Calendar.DATE, findDateWorkOff(startDate, endDate));
        endCalendar.add(Calendar.DATE, -findDateWork(startDate, endDate));

        while (checkIsDayOff(endDate) == true || DateTimeUtils.checkIsWeekend(endCalendar) == true) {
            endCalendar.add(Calendar.DATE, 1);
            endDate = DateTimeUtils.CalendarToDate(endCalendar);
        }
        return DateTimeUtils.CalendarToDate(endCalendar);
    }

    /**
     * tìm ngày nghỉ lễ
     *
     * @param start
     * @param end
     * @return
     */
    public int findDateWorkOff(Date start, Date end) {
        String hql = " from TimeProcess t where t.isActive = 1 and t.isDayOff = 0 ";
        List lstParam = new ArrayList();

        if (start != null) {
            hql += " and t.timeProcessDate > ?";
            lstParam.add(start);
        }
        if (end != null) {
            hql += " and t.timeProcessDate <= ?";
            lstParam.add(end);
        }
        Query countQuery = getSession().createQuery("select count(t.timeProcessId) " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            countQuery.setParameter(i, lstParam.get(i));
        }
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        return total;
    }

    /**
     * tìm ngày làm việc bù
     *
     * @param start
     * @param end
     * @return
     */
    public int findDateWork(Date start, Date end) {
        String hql = " from TimeProcess t where t.isActive = 1 and t.isDayOff = 1 ";
        List lstParam = new ArrayList();

        if (start != null) {
            hql += " and t.timeProcessDate > ?";
            lstParam.add(start);
        }
        if (end != null) {
            hql += " and t.timeProcessDate <= ?";
            lstParam.add(end);
        }
        Query countQuery = getSession().createQuery("select count(t.timeProcessId) " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            countQuery.setParameter(i, lstParam.get(i));
        }
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        return total;
    }

    /**
     * là ngày nghỉ
     *
     * @param dayCheck
     * @return
     */
    public boolean checkIsDayOff(Date dayCheck) {
        boolean ck = false;
        String hql = " from TimeProcess t where t.isActive = 1 and t.isDayOff = 0";
        List lstParam = new ArrayList();
        if (dayCheck != null) {
            hql += " and t.timeProcessDate = ?";
            lstParam.add(dayCheck);
        }
        Query countQuery = getSession().createQuery("select count(t.timeProcessId) " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            countQuery.setParameter(i, lstParam.get(i));
        }
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        if (total > 0) {
            ck = true;
        }
        return ck;
    }
//140725

    /**
     * dem phien ban
     *
     * @param objId
     * @return
     */
    public Long getCountVersion(Long objId) {
        Long iresult;
        List lstParam = new ArrayList();
        String hql = "select count(t) from Files t where t.originalId = ? ";
        lstParam.add(objId);
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }
        iresult = Long.parseLong(query.uniqueResult().toString()) + 1L;
        return iresult;
    }

    /**
     * cap nhat khong phai la phien ban cuoi
     *
     * @param objId
     * @return
     */
    public int updateSetNotLastIsTemp(Long objId) {
        try {
            String hql = " update Files t set t.lastIsTemp = 0 where t.originalId = ?";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, objId);
            return query.executeUpdate();
        } catch (HibernateException e) {
            log.error(e);
            return 0;
        }
    }//!140725

    /**
     * tim phien ban theo sua doi bo sung
     *
     * @param objId
     * @param originalId
     * @param version
     * @param isLastVersion
     * @return
     */
    public Files findIsItempObj(Long objId, Long originalId, Long version, boolean isLastVersion) {//140726
        Files bo = new Files();
        try {

            List lstParam = new ArrayList();
            String hql = "select t from Files t where 1=1";
            if (objId != null && objId > 0L) {
                hql += " and t.fileId = ?";
                lstParam.add(objId);
            }
            if (originalId != null && originalId > 0L) {
                hql += " and t.originalId = ?";
                lstParam.add(originalId);
            }
            if (version != null && version > 0L) {
                hql += " and t.version = ?";
                lstParam.add(version);
            }
            if (isLastVersion) {
                hql += " and t.lastIsTemp = ?";
                lstParam.add(1L);
            }
            Query query = getSession().createQuery(hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
            }
            List<Files> lstObj;
            lstObj = query.list();
            if (!lstObj.isEmpty()) {
                bo = lstObj.get(0);
            }
        } catch (HibernateException e) {
            log.error(e);
        }
        return bo;
    }

    /**
     *
     * @param fileId
     * @return
     */
    public Files getLastVersionOfFile(Long fileId) {
        String hql = "select f from Files f where f.originalId = ? order by f.version desc";
        Query query = session.createQuery(hql);
        query.setParameter(0, fileId);
        List<Files> lstFiles = query.list();
        if (lstFiles != null && !lstFiles.isEmpty()) {
            return lstFiles.get(0);
        } else {
            return null;
        }
    }

    /**
     *
     * @param fileId
     * @return
     */
    public Long getLastVersionIdOfFile(Long fileId) {
        Files f = getLastVersionOfFile(fileId);
        if (f != null) {
            return f.getFileId();
        } else {
            return null;
        }
    }

    /**
     * lay danh sach ho so phien ban truoc
     *
     * @param filesId
     * @return
     */
    public List getLstOldVersionFiles(Long filesId) {//n140729 - get lst cac phien ban cua ho so
        List<Files> lstFiles;
        List<FilesForm> lstFilesForm;
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Files a ");
            stringBuilder.append(" where a.originalId = ?"
                    + " order by a.version DESC");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, filesId);
            lstFiles = query.list();
            lstFilesForm = new ArrayList();
            for (int i = 0; i < lstFiles.size(); i++) {
                lstFilesForm.add(new FilesForm(lstFiles.get(i)));
            }
        } catch (HibernateException ex) {
            log.error(ex.getMessage());
            return null;
        }
        return lstFilesForm;
    }

    /**
     * luu ho so
     *
     * @param vo
     * @throws Exception
     */
    public void saveDb(Files vo) throws Exception {
        getSession().update(vo);
        getSession().getTransaction().commit();
    }

    /**
     * luu ho so
     *
     * @param vo
     * @throws Exception
     */
    public void saveDbNoCommit(Files vo) throws Exception {
        getSession().update(vo);
    }

    /**
     * get loai ho so by Id ho so
     *
     * @param fileId
     * @return
     */
    public Long getFileTypeByFileId(Long fileId) {
        // 11/11/2014 viethd
        //String a = " select f.fileType from Files f where f.fileId=" + fileId;
        String a = " select f.fileType from Files f where f.fileId=:fileId";
        Query query = getSession().createQuery(a);
        query.setParameter("fileId", fileId);
        List<Long> result = query.list();
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    /**
     * phe duyet tham dinh
     *
     * @param form
     * @param deptId
     * @param deptName
     * @param userId
     * @param userName
     * @return
     */
    public boolean onApproveEvaluate(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else {
                file.setStatus(form.getStatus());
                file.setDisplayStatus(getFileStatusName(form.getStatus()));
                String dateTime = DateTimeUtils.convertDateToString(dateNow, "dd/MM/yyyy HH:mm");
                file.setLeaderRequest(userName + " " + dateTime + ":\n" + form.getLeaderRequest());
                file.setDisplayRequest(form.getLeaderRequest());
                file.setModifyDate(dateNow);

                file.setLeaderStaffSignId(userId);
                file.setLeaderStaffSignName(userName);
                //
                // Cap nhat process
                //
                ProcessDAOHE pdhe = new ProcessDAOHE();
                Process p = pdhe.getWorkingProcess(form.getFileId(), Constants.OBJECT_TYPE.FILES, deptId, userId);
                if (p != null) {
                    p.setStatus(form.getStatus());
                    p.setLastestComment(form.getLeaderRequest());
                    getSession().update(p);
                }
                // Neu khong co luong thi tu xu thoi :-)
                if (form.getStatus().equals(Constants.FILE_STATUS.APPROVED)) {
                    // Phe duyet oke, chuyen ho so xuong cho chuyen vien de thong bao doi chieu ho so
                    Process newP = new Process();
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
                    //lay process tham dinh ho so
                    Process pold = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.ASSIGNED, Constants.FILE_STATUS.EVALUATED);
                    if (pold != null) {
                        newP.setReceiveDate(dateNow);
                        newP.setReceiveGroup(pold.getSendGroup());
                        newP.setReceiveGroupId(pold.getSendGroupId());
                        newP.setReceiveUser(pold.getReceiveUser());
                        newP.setReceiveUserId(pold.getReceiveUserId());
                    }
                    getSession().save(newP);
//cap nhat noi dung thong bao - tao noi dung thong bao
                    RequestComment rcbo = new RequestComment();
                    rcbo.setContent(form.getLeaderRequest());
                    file.setComparisonContent(form.getLeaderRequest());

                    file.setIsFee(0L);//bay gio tinh phi giay cong bo
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
                    rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.TBDC);
//-150120
                    RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
                    RequestComment lastRQBo = rqdaohe.findLastRequestComment(file.getFileId(), Constants.ACTIVE_STATUS.ACTIVE);
                    if (lastRQBo != null) {
                        rcbo.setLastContent(lastRQBo.getContent());
                        lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                        getSession().update(lastRQBo);
                    }
                    getSession().save(rcbo);
//!cap nhat noi dung thong bao - tao noi dung thong bao
//-150120
                } else {
                    if (form.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_REVIEW)) {
                        // Tra lai cho lanh dao don vi xem xet lai
                        Process newP = new Process();
                        newP.setObjectId(form.getFileId());
                        newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                        newP.setSendDate(dateNow);
                        newP.setSendGroup(deptName);
                        newP.setSendGroupId(deptId);
                        newP.setSendUserId(userId);
                        newP.setSendUser(userName);
                        newP.setProcessStatus(form.getStatus()); // De xu ly
                        newP.setStatus(0l); // Moi den chua xu ly
                        newP.setIsActive(1l);
                        // Gui toi chinh don vi quan ly de xem xet
                        if (p != null) {
                            // Gui lai cho chinh nguoi gui
                            newP.setReceiveDate(dateNow);
                            newP.setReceiveGroup(p.getSendGroup());
                            newP.setReceiveGroupId(p.getSendGroupId());
                            newP.setReceiveUser(p.getSendUser());
                            newP.setReceiveUserId(p.getSendUserId());
                        }
                        getSession().save(newP);
                    }
                    if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {
                        // gui tra ho so cho doanh nghiep sua doi bo sung
                        Process newP = new Process();
                        newP.setObjectId(form.getFileId());
                        newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                        newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                        newP.setSendDate(dateNow);
                        newP.setSendGroup(deptName);
                        newP.setSendGroupId(deptId);
                        newP.setSendUserId(userId);
                        newP.setSendUser(userName);
                        //
                        newP.setReceiveDate(dateNow);
                        newP.setReceiveGroup(deptName);
                        newP.setReceiveGroupId(deptId);

                        newP.setProcessStatus(form.getStatus()); //De xu ly
                        newP.setStatus(0l); //Moi den chua xu ly
                        newP.setIsActive(1l);
                        getSession().save(newP);
                        //xóa bản ghi temp trước nếu có (lưu vào vùng lưu trữ)
                        updateSetNotLastIsTemp(file.getFileId());
                        try {

                            ResourceBundle rb = ResourceBundle.getBundle("config");
                            Procedure procedurebo;
                            ProcedureDAOHE procedureDAOHE = new ProcedureDAOHE();
                            procedurebo = procedureDAOHE.findById(file.getFileType());
                            int SD = 0;
                            try {
                                SD = Integer.parseInt(rb.getString(procedurebo.getDescription() + "_SD"));
                            } catch (NumberFormatException ex) {
                                log.error(ex.getMessage());
                            }
                            if (SD > 0) {
                                file.setDeadlineAddition(getDateWorkingTime(SD));
                            }
                        } catch (Exception ex) {
                            log.error(ex.getMessage());
                        }
                        //y kien lanh chuyen thanh y kien cua lanh dao
                    }
                }
                if (form.getStatus().equals(Constants.FILE_STATUS.APPROVED)) {
                    //===============================tao giay cong bo===============
                    file.setApproveDate(dateNow);
                    ProcedureDAOHE pcdaohe = new ProcedureDAOHE();
                    Procedure procedure = pcdaohe.findById(file.getFileType());
                    if (procedure != null && procedure.getProcedureId() > 0) {
                        if (!procedure.getCode().equals("12")) {//tao giay tiep nhan cong bo
                            if (file.getAnnouncementId() != null) {//Cấp giấy tiếp nhận bản công bố hợp quy (bên thứ nhất)
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
                                String strReceiptNo = getNewReceiptNo(file.getAgencyId(), file.getFileType());
                                arpForm.setReceiptNo(strReceiptNo);
                                if (file.getEffectiveDate() == 3) {//lay ngay ki + 3 nam
                                    arpForm.setEffectiveDate(DateUtils.addYears(dateNow, 3));
                                } else if (file.getEffectiveDate() == 5) {//lay ngay ki + 5 nam
                                    arpForm.setEffectiveDate(DateUtils.addYears(dateNow, 5));
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
                                        bReturn = false;
                                    } else {
                                        Long ObjId = arpForm.getAnnouncementReceiptPaperId();
                                        AnnouncementReceiptPaper bo = arpForm.convertToEntity();
                                        if (ObjId == null) {
                                            getSession().save(bo);
                                            file.setAnnouncementReceiptPaperId(bo.getAnnouncementReceiptPaperId());
                                            //getSession().update(file);
                                        } else {
                                            getSession().update(bo);
                                            file.setAnnouncementReceiptPaperId(bo.getAnnouncementReceiptPaperId());
                                            //getSession().update(file);
                                        }
                                    }
                                } catch (Exception ex) {
                                    log.error(ex.getMessage());
                                    bReturn = false;
                                }
                            }
                            //!tao giay tiep nhan
                        } else if (file.getTestRegistrationId() != null) {//Giấy xác nhận đạt yêu cầu nhập khẩu của cơ quan kiểm tra Nhà nước về chất lượng thực phẩm nhập khẩu
                            ConfirmImportSatistPaperForm cispForm = new ConfirmImportSatistPaperForm();
                            cispForm.setTestAgencyName(form.getTestRegistration().getTestAgency());
                            cispForm.setTestAdd(form.getTestRegistration().getTestAdd());
                            cispForm.setExportBusinessName(form.getTestRegistration().getExportBusinessName());
                            cispForm.setExportBusinessAdd(form.getTestRegistration().getExportBusinessAdd());
                            cispForm.setExportBusinessMail(form.getTestRegistration().getExportBusinessMail());
                            cispForm.setExportBusinessTel(form.getTestRegistration().getExportBusinessTel());
                            cispForm.setExportBusinessFax(form.getTestRegistration().getExportBusinessFax());
                            cispForm.setExportContractCode(form.getTestRegistration().getExportContractCode());
                            cispForm.setExportContractDate(form.getTestRegistration().getExportContractDate());
                            cispForm.setExportLadingCode(form.getTestRegistration().getExportLadingCode());
                            cispForm.setExportLadingDate(form.getTestRegistration().getExportLadingDate());
                            cispForm.setExportPort(form.getTestRegistration().getExportPort());
                            cispForm.setImportBusinessName(form.getTestRegistration().getImportBusinessName());
                            cispForm.setImportBusinessAddress(form.getTestRegistration().getImportBusinessAddress());
                            cispForm.setImportBusinessEmail(form.getTestRegistration().getImportBusinessEmail());
                            cispForm.setImportBusinessTel(form.getTestRegistration().getImportBusinessTel());
                            cispForm.setImportBusinessFax(form.getTestRegistration().getImportBusinessFax());
                            cispForm.setImportPort(form.getTestRegistration().getImportPort());
                            cispForm.setImportDate(form.getTestRegistration().getImportDate());
                            cispForm.setProductName(form.getTestRegistration().getProductName());
                            cispForm.setProductDescription(form.getTestRegistration().getProductDescription());
                            cispForm.setProductCode(form.getTestRegistration().getProductCode());
                            cispForm.setProductOrigin(form.getTestRegistration().getProductOrigin());
                            cispForm.setProductAmount(form.getTestRegistration().getProductAmount());
                            cispForm.setProductWeight(form.getTestRegistration().getProductWeight());
                            cispForm.setProductValue(form.getTestRegistration().getProductValue());
                            cispForm.setGatheringAdd(form.getTestRegistration().getGatheringAdd());
                            cispForm.setTestDate(form.getTestRegistration().getTestDate());
                            cispForm.setBusinessRepresent(form.getTestRegistration().getBusinessRepresent());
                            cispForm.setBusinessSignAdd(form.getTestRegistration().getBusinessSignAdd());
                            cispForm.setBusinessSigndate(form.getTestRegistration().getBusinessSigndate());
                            cispForm.setAgencyRepresent(form.getTestRegistration().getAgencyRepresent());
                            cispForm.setAgencySignAdd(form.getTestRegistration().getAgencySignAdd());
                            cispForm.setAgencySigndate(form.getTestRegistration().getAgencySigndate());
                            cispForm.setStandardTargetNo(form.getTestRegistration().getStandardTargetNo());
                            cispForm.setStandardTargetDate(form.getTestRegistration().getStandardTargetDate());
                            cispForm.setReleaseDocumentNo(form.getTestRegistration().getReleaseDocumentNo());
                            cispForm.setReleaseDocumentDate(form.getTestRegistration().getReleaseDocumentDate());
                            try {
                                ConfirmImportSatistPaperDAOHE cthe = new ConfirmImportSatistPaperDAOHE();
                                if (cthe.isDuplicate(cispForm) == true) {
                                    bReturn = false;
                                } else {
                                    Long ObjId = cispForm.getConfirmImportSatistPaperId();
                                    if (ObjId == null) {
                                        ConfirmImportSatistPaper bo = cispForm.convertToEntity();
                                        getSession().save(bo);
                                        file.setConfirmImportSatistPaperId(bo.getConfirmImportSatistPaperId());
                                        //getSession().update(file);
                                    } else {
                                        ConfirmImportSatistPaper bo = cispForm.convertToEntity();
                                        getSession().update(bo);
                                        file.setConfirmImportSatistPaperId(bo.getConfirmImportSatistPaperId());
                                        //getSession().update(file);
                                    }
                                }
                            } catch (Exception ex) {
                                log.error(ex.getMessage());
                                bReturn = false;
                            }
                        }
                    }
                    //=======================================end====================

                    //sms
                    MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                    String msg = "Ho so ma: " + file.getFileCode() + " cua doanh nghiep: " + file.getBusinessName() + " dang trong trang thai: da phe duyet";
                    msdhe.saveMessageSMS(userId, file.getUserCreateId(), msg);
                    //email
                    MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                    String msge = "";
                    msge = "Hồ sơ mã: " + file.getFileCode() + " của doanh nghiệp: " + file.getBusinessName() + " đang trong trạng thái: đã phê duyệt.";
                    msedhe.saveMessageEmail(userId, file.getUserCreateId(), msge);
                }
                update(file);
            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    /**
     * xem xét thẩm định hồ sơ.
     *
     * @param form
     * @param deptId
     * @param deptName
     * @param userId
     * @param userName
     * @return
     */
    public boolean onReviewEvaluate(FilesForm form, Long deptId, String deptName, Long userId, String userName) {//SOS
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else {
                Long processStatus = file.getStatus();//trang thai ho so truoc khi thay doi
                Long status = form.getStatus();

                if (processStatus != null//141225 BINHNT update phan quyen ho so tham dinh
                        && (processStatus.equals(Constants.FILE_STATUS.EVALUATED)//DA THAM DINH DAT
                        || processStatus.equals(Constants.FILE_STATUS.FEDBACK_TO_REVIEW)//TRA LAI XEM XET LAI
                        || processStatus.equals(Constants.FILE_STATUS.EVALUATE_TO_ADD)//TRA LAI XEM XET LAI
                        || processStatus.equals(Constants.FILE_STATUS.FEDBACK_TO_ADD))) {//DA THAM DINH YEU CA SDBS
                    // Cap nhat trang thai ho so
                    file.setStatus(status);
                    file.setDisplayStatus(getFileStatusName(status));
                    String dateTime = DateTimeUtils.convertDateToString(dateNow, "dd/MM/yyyy HH:mm");
                    if (!status.equals(Constants.FILE_STATUS.REVIEW_TO_ADD)) {
                        file.setLeaderStaffRequest(userName + " " + dateTime + ":\n" + form.getLeaderStaffRequest());
                        file.setDisplayRequest(form.getLeaderStaffRequest());
                    } else {
                        file.setLeaderStaffRequest("Trưởng phòng đã xem xét công vãn SÐBS");
                        file.setDisplayRequest("Trưởng phòng đã xem xét công vãn SÐBS");
                    }
                    file.setModifyDate(dateNow);
                    file.setEvaluateAddDate(dateNow);
                    file.setLeaderApproveName(form.getLeaderApproveName());
                    file.setLeaderApproveId(form.getLeaderApproveId());
                    // Cap nhat process
                    ProcessDAOHE pdhe = new ProcessDAOHE();
                    Process p = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, processStatus, Constants.FILE_STATUS.NEW_CREATE);
                    Process newP = new Process();
                    newP.setObjectId(form.getFileId());
                    newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                    newP.setSendDate(dateNow);
                    newP.setSendGroup(deptName);
                    newP.setSendGroupId(deptId);
                    newP.setSendUserId(userId);
                    newP.setSendUser(userName);
                    newP.setProcessStatus(status); // De xu ly
                    newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                    newP.setStatus(Constants.FILE_STATUS.NEW_CREATE); // Moi den chua xu ly
                    newP.setIsActive(Constants.ACTIVE_STATUS.ACTIVE);
                    newP.setReceiveDate(dateNow);
                    if (status.equals(Constants.FILE_STATUS.REVIEWED)) {
                        // Phe duyet oke, chuyen ho so cho lanh dao phe duyet
                        newP.setReceiveUser(form.getLeaderApproveName());
                        newP.setReceiveUserId(form.getLeaderApproveId());
                        newP.setReceiveGroup(file.getAgencyName());
                        newP.setReceiveGroupId(file.getAgencyId());
                    } else {
                        if (status.equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {
                            //140722 - da thong bao yeu cau sdbs
                            ProcessDAOHE psdhe = new ProcessDAOHE();
                            Process pold = psdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.NEW, Constants.FILE_STATUS.RECEIVED);
                            if (pold != null) {
                                newP.setReceiveGroupId(pold.getSendGroupId());
                                newP.setReceiveGroup(pold.getSendGroup());
                                newP.setReceiveUserId(pold.getSendUserId());
                                newP.setReceiveUser(pold.getSendUser());
                            } else {
                                newP.setReceiveGroup(deptName);
                                newP.setReceiveGroupId(deptId);
                            }
                            //xóa b?n ghi temp trý?c n?u có (lýu vào vùng lýu tr?)
                            updateSetNotLastIsTemp(file.getFileId());//
                            ProcessCommentDAOHE pcdaohe = new ProcessCommentDAOHE();
                            int a = pcdaohe.updateSetNotLastIsTemp(file.getFileId());

                            try {//140627 THIET LAP HAN SDBS HO SO
                                ResourceBundle rb = ResourceBundle.getBundle("config");
                                Procedure procedurebo;
                                ProcedureDAOHE procedureDAOHE = new ProcedureDAOHE();
                                procedurebo = procedureDAOHE.findById(file.getFileType());
                                int SD = 0;
                                try {
                                    SD = Integer.parseInt(rb.getString(procedurebo.getDescription() + "_SD"));
                                } catch (NumberFormatException ex) {
                                    log.error(ex.getMessage());
                                }
                                if (SD > 0) {
                                    file.setDeadlineAddition(getDateWorkingTime(SD));
                                }
                            } catch (Exception ex) {
                                log.error(ex.getMessage());
                            }//!140627 THIET LAP HAN SDBS HO SO
                            //sms
                            MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                            String msg = "";
                            msg = "Ho so ma: " + file.getFileCode() + " cua doanh nghiep: " + file.getBusinessName() + " dang trong trang thai: da thong bao yeu cau sdbs";
                            msdhe.saveMessageSMS(userId, file.getUserCreateId(), msg);
                            //email
                            MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                            String msge = "";
                            msge = "Hồ sơ mã: " + file.getFileCode() + " của doanh nghiệp: " + file.getBusinessName() + " đang trong trạng thái: Đã thông báo yêu cầu sửa đổi bổ sung.";
                            msedhe.saveMessageEmail(userId, file.getUserCreateId(), msge);
                        }
                        if (status.equals(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE)) {
                            // Tra lai cho chuyen vien tham dinh
                            if (p != null) {
                                //lay process tham dinh ho so
                                ProcessDAOHE psdhe = new ProcessDAOHE();
                                Process pold = psdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.EVALUATED, Constants.FILE_STATUS.NEW_CREATE);
                                if (pold != null) {
                                    newP.setReceiveGroup(pold.getSendGroup());
                                    newP.setReceiveGroupId(pold.getSendGroupId());
                                    if (file != null && file.getStaffProcess() != null && file.getNameStaffProcess() != null) {
                                        newP.setReceiveUser(file.getNameStaffProcess());
                                        newP.setReceiveUserId(file.getStaffProcess());
                                    } else {
                                        newP.setReceiveUser(pold.getSendUser());
                                        newP.setReceiveUserId(pold.getSendUserId());
                                    }
                                } else {//141218u binhnt53 fix loi ho so lanh dao phong tra lai voi
                                    pold = psdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.FEDBACK_TO_ADD, Constants.FILE_STATUS.NEW_CREATE);
                                    if (pold != null) {
                                        newP.setReceiveGroup(pold.getSendGroup());
                                        newP.setReceiveGroupId(pold.getSendGroupId());
                                        if (file != null && file.getStaffProcess() != null && file.getNameStaffProcess() != null) {
                                            newP.setReceiveUser(file.getNameStaffProcess());
                                            newP.setReceiveUserId(file.getStaffProcess());
                                        } else {
                                            newP.setReceiveUser(pold.getSendUser());
                                            newP.setReceiveUserId(pold.getSendUserId());
                                        }
                                    } else {
                                        pold = psdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.ASSIGNED, null);
                                        if (pold != null) {
                                            newP.setReceiveGroup(pold.getReceiveGroup());
                                            newP.setReceiveGroupId(pold.getReceiveGroupId());
                                            newP.setReceiveUser(pold.getReceiveUser());
                                            newP.setReceiveUserId(pold.getReceiveUserId());
                                        } else if (p != null) {// Gui lai cho chinh nguoi gui
                                            newP.setReceiveGroup(p.getSendGroup());
                                            newP.setReceiveGroupId(p.getSendGroupId());
                                            newP.setReceiveUser(p.getSendUser());
                                            newP.setReceiveUserId(p.getSendUserId());
                                        }
                                    }
                                }
                            }
                            //hieptq update 080515
                            // xoa ban ghi lanh dao comment khi bi tra lai
                            RequestCommentDAOHE rcdhe = new RequestCommentDAOHE();
                            RequestComment rc = rcdhe.findLeaderComment(form.getFileId(), 1l);
                            if (rc != null && !rc.getContent().equals("")) {
                                rc.setIsActive(0l);
                                getSession().update(rc);
                            }

                        }
                        /*
                         if (form.getStatus().equals(Constants.FILE_STATUS.REVIEWED_TO_ADD)) {//da xem xet yc sdbs
                         newP.setReceiveGroup(deptName);
                         newP.setReceiveGroupId(deptId);
                         newP.setReceiveUser(file.getNameStaffProcess());
                         newP.setReceiveUserId(file.getStaffProcess());
                         }*/
                        if (status.equals(Constants.FILE_STATUS.REVIEW_TO_ADD)) {
                            /*
                             lanh dao phong vao xem xet noi dung thong bao sua doi bo sung
                             chinh sua noi dung
                             luu va gui len lanh dao cuc
                             */
                            if (form.getIsTypeChange() != null) {
                                file.setIsTypeChange(form.getIsTypeChange());//chuyen loai ho so hay khong
                            }
                            newP.setReceiveUser(form.getLeaderApproveName());
                            newP.setReceiveUserId(form.getLeaderApproveId());
                            newP.setReceiveGroup(file.getAgencyName());
                            newP.setReceiveGroupId(file.getAgencyId());
//cap nhat noi dung thong bao - tao noi dung thong bao
                            RequestComment rcbo = new RequestComment();
                            if (form.getEvaluationRecordsForm() != null) {
                                String content = "";
                                if (form.getLeaderStaffRequest() != null && !form.getLeaderStaffRequest().equals("null")) {
                                    content += "* Ý kiến chung:" + "\n";
                                    content += form.getLeaderStaffRequest() + "\n";
                                }
                                if (form.getEvaluationRecordsForm().getLegalContentL() != null && !form.getEvaluationRecordsForm().getLegalContentL().equals("null")) {
                                    content += "* Về pháp chế:" + "\n";
                                    content += form.getEvaluationRecordsForm().getLegalContentL() + "\n";
                                }

                                if (form.getEvaluationRecordsForm().getFoodSafetyQualityContentL() != null && !form.getEvaluationRecordsForm().getFoodSafetyQualityContentL().equals("null")) {
                                    content += "* Về chỉ tiêu chất lượng an toàn thực phẩm:" + "\n";
                                    content += form.getEvaluationRecordsForm().getFoodSafetyQualityContentL() + "\n";
                                }

                                if (form.getEvaluationRecordsForm().getEffectUtilityContentL() != null && !form.getEvaluationRecordsForm().getEffectUtilityContentL().equals("null")) {
                                    content += "* Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng:" + "\n";
                                    content += form.getEvaluationRecordsForm().getEffectUtilityContentL() + "\n";
                                }
                                if (content != null && !content.equals("null")) {
                                    rcbo.setContent(content);
                                } else {
                                    rcbo.setContent("Không có nội dung.");
                                }
                            } else if (file.getLeaderStaffRequest() != null) {
                                rcbo.setContent(form.getLeaderStaffRequest());
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
//-150120
                            RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
                            RequestComment lastRQBo = rqdaohe.findLastRequestComment(file.getFileId(), Constants.ACTIVE_STATUS.ACTIVE);
                            if (lastRQBo != null) {
                                rcbo.setLastContent(lastRQBo.getContent());
                                lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                                getSession().update(lastRQBo);
                            }
                            getSession().save(rcbo);
//!cap nhat noi dung thong bao - tao noi dung thong bao
//-150120
                            //!luu noi dung du thao
                        }
                    }
                    getSession().save(newP);
                    //insert noi dung tham dinh
                    if (form.getEvaluationRecordsForm() != null) {
                        EvaluationRecordsDAOHE evaluationRecordsDAOHE = new EvaluationRecordsDAOHE();
                        EvaluationRecords evaluationRecords = evaluationRecordsDAOHE.findFilesByFileId(file);
                        if (evaluationRecords != null) {
                            evaluationRecords.setSendDate(file.getSendDate());
                            evaluationRecords.setLegalL(form.getEvaluationRecordsForm().getLegalL());
                            evaluationRecords.setLegalContentL(form.getEvaluationRecordsForm().getLegalContentL());
                            evaluationRecords.setFoodSafetyQualityL(form.getEvaluationRecordsForm().getFoodSafetyQualityL());
                            evaluationRecords.setFoodSafetyQualityContentL(form.getEvaluationRecordsForm().getFoodSafetyQualityContentL());
                            evaluationRecords.setEffectUtilityL(form.getEvaluationRecordsForm().getEffectUtilityL());
                            evaluationRecords.setEffectUtilityContentL(form.getEvaluationRecordsForm().getEffectUtilityContentL());
                            evaluationRecords.setFilesStatusL(status);
                            evaluationRecords.setMainContentL(form.getLeaderStaffRequest());
                            getSession().update(evaluationRecords);
                        }
                        boolean bInsertRC = insertRequestCommentLeader(file.getFileId(), form, userId, userName, deptId, deptName, dateNow);//binhnt53 150130
                    }

                    if (p != null) {
                        p.setStatus(status);
                        p.setLastestComment(form.getLeaderStaffRequest());
                        getSession().update(p);
                    } else {
                        Process pFeedbacktoAdd = null;
                        ProcessDAOHE psdhe = new ProcessDAOHE();
                        pFeedbacktoAdd = psdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.FEDBACK_TO_ADD, Constants.FILE_STATUS.NEW_CREATE);
                        if (pFeedbacktoAdd != null) {
                            pFeedbacktoAdd.setStatus(status);
                            pFeedbacktoAdd.setLastestComment(form.getLeaderStaffRequest());
                            getSession().update(pFeedbacktoAdd);
                        }
                    }
                    update(file);
                } else {
                    return false;
                }
            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    public boolean onAssignApprove(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else {
                ProcessDAOHE pdhe = new ProcessDAOHE();
                Process oldP = pdhe.getProcessByAction(file.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.REVIEWED, Constants.FILE_STATUS.NEW_CREATE);
                if (oldP != null) {
                    oldP.setReceiveUser(form.getLeaderApproveName());
                    oldP.setReceiveUserId(form.getLeaderApproveId());
                    getSession().update(oldP);
                }
                file.setModifyDate(dateNow);
                file.setLeaderApproveName(form.getLeaderApproveName());
                file.setLeaderApproveId(form.getLeaderApproveId());
                update(file);
            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    //cuc truong phan cong ho so sau khi co y kien cua
    public boolean onAssignApproveByCT(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else {
                Long processStatus = file.getStatus();
                file.setStatus(form.getStatus());
                file.setDisplayStatus(getFileStatusName(form.getStatus()));
                String dateTime = DateTimeUtils.convertDateToString(dateNow, "dd/MM/yyyy HH:mm");
                file.setLeaderStaffRequest(userName + " " + dateTime + ":\n" + form.getLeaderStaffRequest());
                file.setDisplayRequest(form.getLeaderStaffRequest());
                file.setModifyDate(dateNow);
                file.setLeaderApproveName(form.getLeaderApproveName());
                file.setLeaderApproveId(form.getLeaderApproveId());
                // Cap nhat process
                ProcessDAOHE pdhe = new ProcessDAOHE();
                Process oldP = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, processStatus, Constants.FILE_STATUS.NEW_CREATE);
                if (oldP != null) {
                    oldP.setStatus(form.getStatus());
                    oldP.setLastestComment(form.getLeaderStaffRequest());
                    getSession().update(oldP);
                }
                //tạo process mới với xử lý hiển tại
                Process newP = new Process();
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
                if (form.getStatus().equals(Constants.FILE_STATUS.REVIEWED)) {//trả về cho văn thư thông báo đói chiếu
                    newP.setReceiveGroup(deptName);
                    newP.setReceiveGroupId(deptId);
                    newP.setReceiveUser(form.getLeaderApproveName());
                    newP.setReceiveUserId(form.getLeaderApproveId());
//cap nhat noi dung thong bao - tao noi dung thong bao
                    RequestComment rcbo = new RequestComment();
                    if (file.getLeaderRequest() != null) {
                        rcbo.setContent(form.getLeaderStaffRequest());
                    } else {
                        rcbo.setContent("Cục trưởng không có nội dung.");
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
                    rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.YK);//trinh cuc truong
//-150120
                    RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
                    RequestComment lastRQBo = rqdaohe.findLastRequestComment(file.getFileId(), Constants.ACTIVE_STATUS.ACTIVE);
                    if (lastRQBo != null) {
                        rcbo.setLastContent(lastRQBo.getContent());
                        lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                        getSession().update(lastRQBo);
                    }
                    getSession().save(rcbo);
//-150120
//cap nhat noi dung thong bao - tao noi dung thong bao
                }
                getSession().save(newP);
                update(file);
            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    /**
     * phe duyet xem xet doi chieu
     *
     * @param form
     * @param deptId
     * @param deptName
     * @param userId
     * @param userName
     * @return
     */
    public boolean onApproveReviewComparison(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else {
                file.setStatus(form.getStatus());
                file.setDisplayStatus(getFileStatusName(form.getStatus()));
                String dateTime = DateTimeUtils.convertDateToString(dateNow, "dd/MM/yyyy HH:mm");
                file.setLeaderRequest(userName + " " + dateTime + ":\n" + form.getLeaderRequest());
                file.setDisplayRequest(form.getLeaderRequest());
                file.setModifyDate(dateNow);

                file.setLeaderStaffSignId(userId);
                file.setLeaderStaffSignName(userName);
                // Cap nhat process
                ProcessDAOHE pdhe = new ProcessDAOHE();
                Process p = pdhe.getWorkingProcess(form.getFileId(), Constants.OBJECT_TYPE.FILES, deptId, userId);
                if (p != null) {
                    p.setStatus(form.getStatus());
                    p.setLastestComment(form.getLeaderRequest());
                    getSession().update(p);
                }
                if (form.getStatus().equals(Constants.FILE_STATUS.APPROVED)) {// Tra lai cho lanh dao don vi xem xet lai
                    file.setIsFee(0L);//bay gio tinh phi giay cong bo
                    Process pReviewComparison = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.REVIEW_COMPARISON, Constants.FILE_STATUS.NEW_CREATE);
                    if (pReviewComparison != null) {
                        pReviewComparison.setStatus(form.getStatus());
                        pReviewComparison.setLastestComment(form.getLeaderRequest());
                        getSession().update(pReviewComparison);
                    }
                    Process newP = new Process();
                    newP.setObjectId(form.getFileId());
                    newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                    newP.setSendDate(dateNow);
                    newP.setSendGroup(deptName);
                    newP.setSendGroupId(deptId);
                    newP.setSendUserId(userId);
                    newP.setSendUser(userName);
                    newP.setReceiveDate(dateNow);
                    newP.setProcessStatus(form.getStatus()); // De xu ly
                    newP.setStatus(0l); // Moi den chua xu ly
                    newP.setIsActive(1l);
                    // chuyen xuong van thu tra ho so
                    Process oldP = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.RECEIVED, Constants.FILE_STATUS.ASSIGNED);
                    if (oldP != null) {
                        newP.setReceiveGroup(oldP.getSendGroup());
                        newP.setReceiveGroupId(oldP.getSendGroupId());
                        newP.setReceiveUser(oldP.getSendUser());
                        newP.setReceiveUserId(oldP.getSendUserId());
                    }
                    getSession().save(newP);

                    //sms
                    MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                    String msg = "Ho so ma: " + file.getFileCode() + " cua doanh nghiep: " + file.getBusinessName() + " dang trong trang thai: da phe duyet";
                    msdhe.saveMessageSMS(userId, file.getUserCreateId(), msg);
                    //email
                    MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                    String msge = "";
                    msge = "Hồ sơ mã: " + file.getFileCode() + " của doanh nghiệp: " + file.getBusinessName() + " đang trong trạng thái: đã phê duyệt.";
                    msedhe.saveMessageEmail(userId, file.getUserCreateId(), msge);
                }
                if (form.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_REVIEW)) {// Tra lai cho lanh dao don vi xem xet lai
                    Process newP = new Process();
                    newP.setObjectId(form.getFileId());
                    newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                    newP.setSendDate(dateNow);
                    newP.setSendGroup(deptName);
                    newP.setSendGroupId(deptId);
                    newP.setSendUserId(userId);
                    newP.setSendUser(userName);
                    // Gui toi chinh don vi quan ly de xem xet
                    if (p != null) {
                        // Gui lai cho chinh nguoi gui
                        newP.setReceiveDate(dateNow);
                        newP.setReceiveGroup(p.getSendGroup());
                        newP.setReceiveGroupId(p.getSendGroupId());
                        newP.setReceiveUser(p.getSendUser());
                        newP.setReceiveUserId(p.getSendUserId());
                    }
                    newP.setProcessStatus(form.getStatus()); // De xu ly
                    newP.setStatus(0l); // Moi den chua xu ly
                    newP.setIsActive(1l);
                    getSession().save(newP);
                }
                if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {// gui tra ho so cho doanh nghiep sua doi bo sung
                    Process newP = new Process();
                    newP.setObjectId(form.getFileId());
                    newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                    newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                    newP.setSendDate(dateNow);
                    newP.setSendGroup(deptName);
                    newP.setSendGroupId(deptId);
                    newP.setSendUserId(userId);
                    newP.setSendUser(userName);
                    //
                    newP.setReceiveDate(dateNow);
                    newP.setReceiveGroup(deptName);
                    newP.setReceiveGroupId(deptId);

                    newP.setProcessStatus(form.getStatus()); //De xu ly
                    newP.setStatus(0l); //Moi den chua xu ly
                    newP.setIsActive(1l);
                    getSession().save(newP);
                    //xóa bản ghi temp trước nếu có (lưu vào vùng lưu trữ)
                    updateSetNotLastIsTemp(file.getFileId());

                    try {

                        ResourceBundle rb = ResourceBundle.getBundle("config");
                        Procedure procedurebo;
                        ProcedureDAOHE procedureDAOHE = new ProcedureDAOHE();
                        procedurebo = procedureDAOHE.findById(file.getFileType());
                        int SD = 0;
                        try {
                            SD = Integer.parseInt(rb.getString(procedurebo.getDescription() + "_SD"));
                        } catch (NumberFormatException ex) {
                            log.error(ex.getMessage());
                        }
                        if (SD > 0) {
                            file.setDeadlineAddition(getDateWorkingTime(SD));
                        }
                    } catch (Exception ex) {
                        log.error(ex.getMessage());
                    }
                    //y kien lanh chuyen thanh y kien cua lanh dao
                }
            }
            update(file);

        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    /**
     * xem xet doi chieu lanh dao phong
     *
     * @param form
     * @param deptId
     * @param deptName
     * @param userId
     * @param userName
     * @return
     */
    public boolean onComparisonByLeaderOfStaff(FilesForm form, Long deptId, String deptName, Long userId, String userName) {//db140425
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else {
                if (form.getIsComparison().equals(1L)) {//xem xet ok
                    file.setStatus(Constants.FILE_STATUS.REVIEW_COMPARISON);
                    file.setDisplayStatus(getFileStatusName(Constants.FILE_STATUS.REVIEW_COMPARISON));
                    form.setStatus(Constants.FILE_STATUS.REVIEW_COMPARISON);
                } else {//xem xet not ok
                    file.setStatus(Constants.FILE_STATUS.COMPARED_FAIL);
                    file.setDisplayStatus(getFileStatusName(Constants.FILE_STATUS.COMPARED_FAIL));
                    form.setStatus(Constants.FILE_STATUS.COMPARED_FAIL);
                }
                file.setModifyDate(dateNow);
                file.setComparisonContent(form.getComparisonContent());
                file.setIsComparison(form.getIsComparison());
//cap nhat noi dung thong bao - tao noi dung thong bao
                RequestComment rcbo = new RequestComment();
                rcbo.setContent(form.getComparisonContent());
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
                rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.TBDC);
//-150120
                RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
                RequestComment lastRQBo = rqdaohe.findLastRequestComment(file.getFileId(), Constants.ACTIVE_STATUS.ACTIVE);
                if (lastRQBo != null) {
                    rcbo.setLastContent(lastRQBo.getContent());
                    lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                    getSession().update(lastRQBo);
                }
                getSession().save(rcbo);
//-150120
//!cap nhat noi dung thong bao - tao noi dung thong bao
                //update process cu
                ProcessDAOHE pdhe = new ProcessDAOHE();
                Process oldP = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.COMPARED, Constants.FILE_STATUS.NEW_CREATE);
                Process approveFail = null;
                if (oldP != null) {//truong hop la chuyen vien gui len
                    oldP.setStatus(form.getStatus());
                    if (form.getRequestCommentForm() != null) {
                        oldP.setLastestComment(form.getRequestCommentForm().getContent());
                    }
                    getSession().update(oldP);
                } else {//truong hop lanh dao cuc tra ve
                    approveFail = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL, Constants.FILE_STATUS.NEW_CREATE);
                    if (approveFail != null) {
                        approveFail.setStatus(form.getStatus());
                        if (form.getRequestCommentForm() != null) {
                            approveFail.setLastestComment(form.getRequestCommentForm().getContent());
                        }
                        getSession().update(approveFail);
                    }
                }
                //tao process moi
                Process newP = new Process();
                newP.setObjectId(form.getFileId());
                newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                newP.setSendDate(dateNow);
                newP.setSendGroup(deptName);
                newP.setSendGroupId(deptId);
                newP.setSendUserId(userId);
                newP.setSendUser(userName);

                newP.setReceiveDate(dateNow);
                newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                newP.setProcessStatus(form.getStatus()); // De xu ly
                newP.setStatus(0L); // Moi den chua xu ly
                newP.setIsActive(1l);
                newP.setReceiveDate(dateNow);
                //tim lanh dao co quan de phe duyet
                if (form.getIsComparison().equals(1L)) {
                    //approveFail = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL, Constants.FILE_STATUS.NEW_CREATE);
                    if (approveFail != null) {//neu la lanh dao tra ve - tra lai luon cho lanh dao
                        newP.setReceiveGroup(approveFail.getSendGroup());
                        newP.setReceiveGroupId(approveFail.getSendGroupId());
                        newP.setReceiveUser(approveFail.getSendUser());
                        newP.setReceiveUserId(approveFail.getSendUserId());
                    } else {//nop len lanh dao cuc - chua ro lanh dao
                        newP.setReceiveGroup(deptName);
                        newP.setReceiveGroupId(deptId);
                    }
                } else {
                    //tra lai cho chuyen vien de doi chieu
                    Process pold = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.ASSIGNED, Constants.FILE_STATUS.EVALUATED);
                    if (pold != null) {
                        newP.setReceiveGroup(pold.getSendGroup());
                        newP.setReceiveGroupId(pold.getSendGroupId());
                        newP.setReceiveUser(pold.getReceiveUser());
                        newP.setReceiveUserId(pold.getReceiveUserId());
                    } else {
                        newP.setReceiveGroup(deptName);
                        newP.setReceiveGroupId(deptId);
                    }
                }

                getSession().save(newP);
                getSession().update(file);
            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    /**
     * tao thong bao doi chieu ho so binhnt53 - 140817
     *
     * @param form
     * @param deptId
     * @param deptName
     * @param userId
     * @param userName
     * @return
     */
    public boolean onAlertComparison(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());//lay thong tin chi tiet ho so
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else {
                // Cap nhat trang thai ho so
                Long lastProcess = file.getStatus();
                file.setStatus(form.getStatus());
                file.setDisplayStatus(getFileStatusName(form.getStatus()));
                file.setModifyDate(dateNow);
                // Cap nhat process
                ProcessDAOHE pdhe = new ProcessDAOHE();
                Process oldP = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, lastProcess, Constants.FILE_STATUS.NEW_CREATE);
                if (oldP != null) {
                    oldP.setStatus(form.getStatus());
                    if (form.getRequestCommentForm() != null) {
                        oldP.setLastestComment(form.getRequestCommentForm().getContent());
                    }
                    getSession().update(oldP);
                }
                if (form.getStatus().equals(Constants.FILE_STATUS.ALERT_COMPARISON)) {
                    Process newP = new Process();
                    newP.setObjectId(form.getFileId());
                    newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                    newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                    newP.setSendDate(dateNow);
                    newP.setSendGroup(deptName);
                    newP.setSendGroupId(deptId);
                    newP.setSendUserId(userId);
                    newP.setSendUser(userName);
                    //
                    newP.setReceiveDate(dateNow);
//                    newP.setReceiveGroup(deptName);
//                    newP.setReceiveGroupId(deptId);
                    //tim doanh nghiep
                    Process pReceive = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.NEW, Constants.FILE_STATUS.RECEIVED);
                    if (pReceive != null) {
                        newP.setReceiveUser(pReceive.getSendUser());
                        newP.setReceiveUserId(pReceive.getSendUserId());
                        newP.setReceiveGroup(pReceive.getSendGroup());
                        newP.setReceiveGroupId(pReceive.getSendGroupId());
                    }
                    newP.setProcessStatus(form.getStatus()); // De xu ly
                    newP.setStatus(0l); // Moi den chua xu ly
                    newP.setIsActive(1l);
                    getSession().save(newP);
                    //tao noi dung thong bao
                    if (form.getComparisonContent() != null && form.getComparisonContent().trim().length() > 0) {
//cap nhat noi dung thong bao - tao noi dung thong bao
                        RequestComment rcbo = new RequestComment();
                        rcbo.setContent(form.getComparisonContent());
                        file.setComparisonContent(form.getComparisonContent());
                        rcbo.setCreateBy(userId);
                        rcbo.setCreateDate(dateNow);
                        rcbo.setUserId(userId);
                        rcbo.setUserName(userName);
                        rcbo.setStatus(1L);
                        rcbo.setIsActive(1L);
                        rcbo.setGroupId(deptId);
                        rcbo.setGroupName(deptName);
                        rcbo.setObjectId(form.getFileId());
                        rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.TBDC);//-150120
                        rcbo.setIsLastChange(Constants.ACTIVE_STATUS.ACTIVE);//-150120
//-150120
                        RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
                        RequestComment lastRQBo = rqdaohe.findLastRequestComment(file.getFileId(), Constants.ACTIVE_STATUS.ACTIVE);
                        if (lastRQBo != null) {
                            rcbo.setLastContent(lastRQBo.getContent());
                            lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                            getSession().update(lastRQBo);
                        }
                        getSession().save(rcbo);
//-150120
//!cap nhat noi dung thong bao - tao noi dung thong bao
                    }
                }
                update(file);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    /**
     * insert requestComment binhnt53 add 150130
     *
     * @param objectId
     * @param form
     * @param userId
     * @param dateNow
     * @param userName
     * @param deptName
     * @param deptId
     * @return
     */
    public boolean insertRequestComment(Long objectId, FilesForm form, Long userId, String userName, Long deptId, String deptName, Date dateNow) {
        boolean result = false;
        RequestComment rcbo = new RequestComment();
        if (form.getStatus() != null) {
            String staffContent = "";
            if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED)
                    || form.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_ADD)
                    || form.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE)) {

                if (form.getStaffRequest() != null && !form.getStaffRequest().equals("null")) {
                    staffContent += "* Ý kiến chung:" + "\n";
                    staffContent += form.getStaffRequest() + "\n";
                }
//                else {
//                    staffContent += "Không có nội dung." + "\n";
//                }

                if (form.getEvaluationRecordsForm().getLegalContent() != null
                        && !form.getEvaluationRecordsForm().getLegalContent().equals("null")) {
                    staffContent += "* Về pháp chế:" + "\n";
                    staffContent += form.getEvaluationRecordsForm().getLegalContent() + "\n";
                }
//                else {
//                    staffContent += "Không có nội dung." + "\n";
//                }

                if (form.getEvaluationRecordsForm().getFoodSafetyQualityContent() != null
                        && !form.getEvaluationRecordsForm().getFoodSafetyQualityContent().equals("null")) {
                    staffContent += "* Về chỉ tiêu chất lượng an toàn thực phẩm:" + "\n";
                    staffContent += form.getEvaluationRecordsForm().getFoodSafetyQualityContent() + "\n";
                }
//                else {
//                    staffContent += "Không có nội dung." + "\n";
//                }

                if (form.getEvaluationRecordsForm().getEffectUtilityContent() != null
                        && !form.getEvaluationRecordsForm().getEffectUtilityContent().equals("null")) {
                    staffContent += "* Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng:" + "\n";
                    staffContent += form.getEvaluationRecordsForm().getEffectUtilityContent() + "\n";
                }
//                else {
//                    staffContent += "Không có nội dung." + "\n";
//                }

                if (staffContent.trim().length() > 0) {
                    rcbo.setContent(staffContent);
                } else {
                    rcbo.setContent("Không có nội dung.");
                }

            } else {
                rcbo.setContent("Không có nội dung.");
            }
            rcbo.setCreateBy(userId);
            rcbo.setCreateDate(dateNow);
            rcbo.setUserId(userId);
            rcbo.setUserName(userName);
            rcbo.setStatus(1L);
            rcbo.setIsActive(1L);
            rcbo.setGroupId(deptId);
            rcbo.setGroupName(deptName);
            rcbo.setObjectId(form.getFileId());
            rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.TD);//-150120
            rcbo.setIsLastChange(Constants.ACTIVE_STATUS.ACTIVE);//-150120
            //!luu noi dung du thao
            RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
            RequestComment lastRQBo = rqdaohe.findLastRequestComment(objectId, Constants.ACTIVE_STATUS.ACTIVE);
            if (lastRQBo != null) {
                rcbo.setLastContent(lastRQBo.getContent());
                lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                getSession().update(lastRQBo);
            }
            getSession().save(rcbo);
        } else {
            return false;
        }
        return result;
    }

    public boolean insertRequestCommentOnGrid(
            Long objectId,
            FilesForm form,
            Long userId,
            String userName,
            Long deptId,
            String deptName,
            Date dateNow
    ) {
        boolean result = false;
        RequestComment rcbo = new RequestComment();
        if (form.getStatus() != null) {
            String staffContent = "";
            if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED)
                    || form.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_ADD)
                    || form.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE)) {

                if (form.getStaffRequest() != null && !form.getStaffRequest().equals("null")) {
                    staffContent += "* Ý kiến chung:" + "\n";
                    staffContent += form.getStaffRequest() + "\n";
                }
//                else {
//                    staffContent += "Không có nội dung." + "\n";
//                }

                if (form.getEvaluationRecordsFormOnGrid().getLegalContent() != null
                        && !form.getEvaluationRecordsFormOnGrid().getLegalContent().equals("null")) {
                    staffContent += "* Về pháp chế:" + "\n";
                    staffContent += form.getEvaluationRecordsFormOnGrid().getLegalContent() + "\n";
                }
//                else {
//                    staffContent += "Không có nội dung." + "\n";
//                }

                if (form.getEvaluationRecordsFormOnGrid().getFoodSafetyQualityContent() != null
                        && !form.getEvaluationRecordsFormOnGrid().getFoodSafetyQualityContent().equals("null")) {
                    staffContent += "* Về chỉ tiêu chất lượng an toàn thực phẩm:" + "\n";
                    staffContent += form.getEvaluationRecordsFormOnGrid().getFoodSafetyQualityContent() + "\n";
                }
//                else {
//                    staffContent += "Không có nội dung." + "\n";
//                }

                if (form.getEvaluationRecordsFormOnGrid().getEffectUtilityContent() != null
                        && !form.getEvaluationRecordsFormOnGrid().getEffectUtilityContent().equals("null")) {
                    staffContent += "* Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng:" + "\n";
                    staffContent += form.getEvaluationRecordsFormOnGrid().getEffectUtilityContent() + "\n";
                }
//                else {
//                    staffContent += "Không có nội dung." + "\n";
//                }

                if (staffContent.trim().length() > 0) {
                    rcbo.setContent(staffContent);
                } else {
                    rcbo.setContent("Không có nội dung.");
                }

            } else {
                rcbo.setContent("Không có nội dung.");
            }
            rcbo.setCreateBy(userId);
            rcbo.setCreateDate(dateNow);
            rcbo.setUserId(userId);
            rcbo.setUserName(userName);
            rcbo.setStatus(1L);
            rcbo.setIsActive(1L);
            rcbo.setGroupId(deptId);
            rcbo.setGroupName(deptName);
            rcbo.setObjectId(form.getFileId());
            rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.TD);//-150120
            rcbo.setIsLastChange(Constants.ACTIVE_STATUS.ACTIVE);//-150120
            //!luu noi dung du thao
            RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
            RequestComment lastRQBo = rqdaohe.findLastRequestComment(objectId, Constants.ACTIVE_STATUS.ACTIVE);
            if (lastRQBo != null) {
                rcbo.setLastContent(lastRQBo.getContent());
                lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                getSession().update(lastRQBo);
            }
            getSession().save(rcbo);
        } else {
            return false;
        }
        return result;
    }

    /**
     * insert requestComment binhnt53 add 150130
     *
     * @param objectId
     * @param form
     * @param userId
     * @param dateNow
     * @param userName
     * @param deptName
     * @param deptId
     * @return
     */
    public boolean insertRequestCommentLeader(Long objectId, FilesForm form, Long userId, String userName, Long deptId, String deptName, Date dateNow) {
        boolean result = false;
        RequestComment rcbo = new RequestComment();
        if (form.getStatus() != null) {
            String leaderContent = "";
            if (form.getStatus().equals(Constants.FILE_STATUS.REVIEWED)
                    || form.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE)
                    || form.getStatus().equals(Constants.FILE_STATUS.REVIEW_TO_ADD)) {

                if (form.getLeaderStaffRequest() != null && !form.getLeaderStaffRequest().equals("null")) {
                    leaderContent += "* Ý kiến chung:" + "\n";
                    leaderContent += form.getLeaderStaffRequest() + "\n";
                }
//                else {
//                    leaderContent += "Không có nội dung." + "\n";
//                }

                if (form.getEvaluationRecordsForm().getLegalContentL() != null
                        && !form.getEvaluationRecordsForm().getLegalContentL().equals("null")) {
                    leaderContent += "* Về pháp chế:" + "\n";
                    leaderContent += form.getEvaluationRecordsForm().getLegalContentL() + "\n";
                }
//                else {
//                    leaderContent += "Không có nội dung." + "\n";
//                }

                if (form.getEvaluationRecordsForm().getFoodSafetyQualityContentL() != null
                        && !form.getEvaluationRecordsForm().getFoodSafetyQualityContentL().equals("null")) {
                    leaderContent += "* Về chỉ tiêu chất lượng an toàn thực phẩm:" + "\n";
                    leaderContent += form.getEvaluationRecordsForm().getFoodSafetyQualityContentL() + "\n";
                }
//                else {
//                    leaderContent += "Không có nội dung." + "\n";
//                }

                if (form.getEvaluationRecordsForm().getEffectUtilityContentL() != null
                        && !form.getEvaluationRecordsForm().getEffectUtilityContentL().equals("null")) {
                    leaderContent += "* Về cơ chế tác dụng, công dụng và hướng dẫn sử dụng:" + "\n";
                    leaderContent += form.getEvaluationRecordsForm().getEffectUtilityContentL() + "\n";
                }
//                else {
//                    leaderContent += "Không có nội dung." + "\n";
//                }

                if (leaderContent.trim().length() > 0) {
                    rcbo.setContent(leaderContent);
                } else {
                    rcbo.setContent("Không có nội dung.");
                }

            } else {
                rcbo.setContent("Không có nội dung.");
            }
            rcbo.setCreateBy(userId);
            rcbo.setCreateDate(dateNow);
            rcbo.setUserId(userId);
            rcbo.setUserName(userName);
            rcbo.setStatus(1L);
            rcbo.setIsActive(Constants.ACTIVE_STATUS.ACTIVE);
            rcbo.setGroupId(deptId);
            rcbo.setGroupName(deptName);
            rcbo.setObjectId(form.getFileId());
            rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.TD);//-150120
            rcbo.setIsLastChange(Constants.ACTIVE_STATUS.ACTIVE);//-150120
            //!luu noi dung du thao
            RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
            RequestComment lastRQBo = rqdaohe.findLastRequestComment(objectId, Constants.ACTIVE_STATUS.ACTIVE);
            if (lastRQBo != null) {
                rcbo.setLastContent(lastRQBo.getContent());
                lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                getSession().update(lastRQBo);
            }
            getSession().save(rcbo);
        } else {
            return false;
        }
        return result;
    }

    /**
     *
     * @param objectId
     * @param form
     * @param deptId
     * @param deptName
     * @param userId
     * @param userName
     * @return
     */
    public boolean validateRoleUser(Long objectId, FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        Files fbo = null;
        FilesDAOHE fdaohe = new FilesDAOHE();
        Long status = -1L;
        ProcessDAOHE psdaohe = new ProcessDAOHE();
        Process pbo = null;
        if ((objectId == null && form == null) || deptId == null || userId == null) {
            return false;
        }
        if (objectId != null) {
            fbo = fdaohe.findById(objectId);
        } else if (form != null && form.getFileId() != null) {
            fbo = fdaohe.findById(form.getFileId());
        } else {
            return false;
        }
        if (fbo == null) {
            return false;
        } else {
            objectId = fbo.getFileId();
            status = fbo.getStatus();
        }
        pbo = psdaohe.getProcessByAction(objectId, Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, status, Constants.FILE_STATUS.NEW_CREATE);
        if (pbo != null) {
            if (pbo.getReceiveUserId() != null && pbo.getReceiveUserId().equals(userId)) {
                return true;
            } else if (pbo.getReceiveUserId() == null) {
                return pbo.getReceiveGroupId().equals(deptId);
                //CHECK ROLE RETURN
//                    UsersDAOHE udaohe = new UsersDAOHE();
//                    Users u = udaohe.findById(userId);
//                    String roleUser = p.getPosCode();                    
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Tra cứu hồ sơ bởi người dùng
     *
     * @param form
     * @param deptId
     * @param userId
     * @param userType
     * @param start
     * @param count
     * @param sortField
     * @return
     *
     * public GridResult searchLookupFiles(FilesForm form, Long deptId, Long
     * userId, String userType, int start, int count, String sortField) { String
     * hql = ""; List lstParam = new ArrayList(); if
     * (userType.equals(Constants.ROLES.LEAD_OFFICE_ROLE)) { hql = " from Files
     * f, Process p where f.isActive = 1 and f.fileId = p.objectId and (f.isTemp
     * = null or f.isTemp = 0 ) "; } if
     * (userType.equals(Constants.ROLES.STAFF_ROLE)) { hql = " from Files f,
     * Process p where f.isActive = 1 and f.fileId = p.objectId and (f.isTemp =
     * null or f.isTemp = 0 ) "; } if
     * (userType.equals(Constants.ROLES.LEAD_UNIT)) { hql = " from Files f,
     * Process p where f.isActive = 1 and f.fileId = p.objectId and (f.isTemp =
     * null or f.isTemp = 0 ) "; } if
     * (userType.equals(Constants.ROLES.CLERICAL_ROLE)) { hql = " from Files f,
     * Process p where f.isActive = 1 and f.fileId = p.objectId and (f.isTemp =
     * null or f.isTemp = 0 ) "; //140714 binhnt53 if (userId != null) { hql +=
     * " and p.sendUserId = ? "; lstParam.add(userId); }//!140714 binhnt53 }
     *
     * if (form != null) { if (form.getFileCode() != null &&
     * !"".equals(form.getFileCode().trim())) { hql += "AND lower(f.fileCode)
     * like ? ESCAPE '/' ";
     * lstParam.add(StringUtils.toLikeString(form.getFileCode().toLowerCase().trim()));
     * } if (form.getFileType() != null && form.getFileType().longValue() != -1)
     * { hql += " AND f.fileType = ? "; lstParam.add(form.getFileType()); }
     *
     * if (form.getSignDate() != null) { hql += "AND f.signDate = ? ";
     * lstParam.add(form.getSignDate()); } if (form.getStatus() != null &&
     * form.getStatus() >= 0l) { hql += "AND f.status = ? ";
     * lstParam.add(form.getStatus()); } if (form.getStatus() != null &&
     * form.getStatus() == 30l) { lstParam.clear(); hql = "from Files f, Process
     * p where f.isActive = 1 and f.fileId = p.objectId AND f.status = ? and
     * (f.isTemp = null or f.isTemp = 0 ) "; lstParam.add(20l); } // thong ke ho
     * so trong ngay if (form.getStatus() != null && form.getStatus() == 40l) {
     * lstParam.clear(); hql = "from Files f, Process p where f.isActive = 1 and
     * f.fileId = p.objectId AND (f.status = ? or f.status = ?) and
     * to_date(f.sendDate,'yyyy/mm/dd') = to_date(sysdate,'yyyy/mm/dd') and
     * p.receiveUserId=? and p.processType=1 and (f.isTemp = null or f.isTemp =
     * 0 ) "; lstParam.add(3l); lstParam.add(5l); lstParam.add(userId); }
     * //searchtype 5 if (form.getSearchType() != null && form.getSearchType()
     * == 5l) { lstParam.clear(); hql = "from Files f, Process p where
     * f.isActive = 1 and f.fileId = p.objectId AND f.status = ? and (f.isTemp =
     * null or f.isTemp = 0 ) "; lstParam.add(5l); }
     *
     * // ho so can doi chieu if (form.getSearchType() != null &&
     * form.getSearchType() == 23l) { lstParam.clear(); hql = "from Files f,
     * Process p where f.isActive = 1 and f.fileId = p.objectId AND f.status = ?
     * and (f.isTemp = null or f.isTemp = 0 ) and p.receiveGroupId = ? ";
     * lstParam.add(23l); lstParam.add(deptId);
     *
     * }
     * // ho so cho tham dinh sdbs if (form.getSearchType() != null &&
     * form.getSearchType() == 44l) { lstParam.clear(); // hql = "from Files f,
     * Process p where f.isActive = 1 and f.fileId = p.objectId AND f.status = ?
     * and (f.isTemp = null or f.isTemp = 0 ) "; // lstParam.add(17l); hql =
     * "from Files f, Process p where f.isActive=1 and f.fileId = p.objectId and
     * f.status = ? and p.receiveUserId=? and p.processType=1 and (f.isTemp =
     * null or f.isTemp = 0 ) ";
     * lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD);
     * lstParam.add(userId); }
     *
     * if (form.getStatus() != null && form.getStatus() == 41l) {
     * lstParam.clear(); hql = "from Files f, Process p where f.isActive = 1 and
     * f.fileId = p.objectId AND (f.status = ? or f.status = ?) and
     * to_date(f.sendDate,'yyyy/mm/dd') = to_date(sysdate,'yyyy/mm/dd') and
     * (f.isTemp = null or f.isTemp = 0 ) "; lstParam.add(4l); lstParam.add(5l);
     * } if (form.getStatus() != null && form.getStatus() == 42l) {
     * lstParam.clear(); hql = "from Files f, Process p where f.isActive = 1 and
     * f.fileId = p.objectId AND (f.status = ? or f.status = ?) and
     * to_date(f.sendDate,'yyyy/mm/dd') = to_date(sysdate,'yyyy/mm/dd') and
     * (f.isTemp = null or f.isTemp = 0 ) "; lstParam.add(6l); lstParam.add(5l);
     * } // ho so bi tra tham dinh lai if (form.getStatus() != null &&
     * form.getStatus() == 43l) { lstParam.clear(); hql = "from Files f, Process
     * p where f.isActive=1 and f.fileId = p.objectId and f.status = ? and
     * p.receiveUserId=? and p.processType=1 and (f.isTemp = null or f.isTemp =
     * 0 ) "; lstParam.add(8l); lstParam.add(userId); } // ho so cho phoi hop
     * tham dinh chua cho y kien if (form.getStatus() != null &&
     * form.getStatus() == 44l) { lstParam.clear(); hql = "from Files f, Process
     * p where f.isActive=1 and f.fileId = p.objectId and (f.status = ?) and
     * (p.processType=0 or p.processType=4) and (p.processId in (select
     * p.processId from Process p where p.processId not in (select distinct
     * pc.processId from ProcessComment pc))) and p.processStatus = ?";
     * lstParam.add(3l); lstParam.add(3l); // lstParam.add(userId); } //ho so
     * cho xem xet
     *
     * if (form.getStatus() != null && form.getStatus() == 45l) {
     * lstParam.clear(); hql = "from Files f, Process p where f.isActive=1 and
     * f.fileId = p.objectId and f.status = ? and p.receiveUserId=? and
     * (p.processType=1 or p.processType=0) and (f.isTemp = null or f.isTemp = 0
     * ) "; lstParam.add(4l); lstParam.add(userId); } // ho so lanh dao phong da
     * xem xet if (form.getStatus() != null && form.getStatus() == 46l) {
     * lstParam.clear(); hql = "from Files f, Process p where f.isActive=1 and
     * f.fileId = p.objectId and f.status = ? and p.receiveUserId=? and
     * p.processType=1 and (f.isTemp = null or f.isTemp = 0 ) ";
     * lstParam.add(5l); lstParam.add(userId); } //ho so da phe duyet if
     * (form.getStatus() != null && form.getStatus() == 47l) { lstParam.clear();
     * hql = "from Files f, Process p where f.isActive=1 and f.fileId =
     * p.objectId and f.status = ? and p.receiveUserId=? and p.processType=1 and
     * (f.isTemp = null or f.isTemp = 0 ) ";
     * lstParam.add(Constants.FILE_STATUS.APPROVED); lstParam.add(userId); } //
     * ho so tra lai yeu cau bo sung (status 9) if (form.getStatus() != null &&
     * form.getStatus() == 48l) { lstParam.clear(); hql = "from Files f, Process
     * p where f.isActive=1 and f.fileId = p.objectId and f.status = ? and
     * p.receiveUserId=? and p.processType=1 and (f.isTemp = null or f.isTemp =
     * 0 ) "; lstParam.add(9l); lstParam.add(userId); } // ho so tham dịnh da
     * gui ý kiễn phản hồi if (form.getStatus() != null && form.getStatus() ==
     * 49l) { lstParam.clear(); hql = "from Files f, Process p,ProcessComment pc
     * where f.isActive=1 and f.fileId = p.objectId and p.objectType = 30 and
     * (f.status = ?) and p.receiveGroupId = (select distinct p.receiveGroupId
     * from Process p where p.receiveUserId = ?) and (p.processType=0 or
     * p.processType=1) and pc.processId = p.processId and (f.isTemp = null or
     * f.isTemp = 0 ) "; lstParam.add(3l); lstParam.add(userId); } if
     * (form.getStatus() != null && form.getStatus() ==
     * Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL) { lstParam.clear(); hql =
     * "from Files f, Process p where f.isActive = 1 and f.fileId = p.objectId
     * AND f.status = ? and (f.isTemp = null or f.isTemp = 0 ) ";
     * lstParam.add(25l);
     *
     * }
     * // ho so cho phe duyet if (form.getStatus() != null && form.getStatus()
     * == 50l) { lstParam.clear(); hql = "from Files f, Process p where
     * f.isActive=1 and f.fileId = p.objectId and (f.isTemp = null or f.isTemp =
     * 0 ) "; hql += " and (f.status = ? or f.status = ? or f.status = ?)";
     * lstParam.add(Constants.FILE_STATUS.ASSIGNED);
     * lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
     * lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD); hql += " and
     * p.receiveGroupId = ? and p.receiveUserId = ? and (p.processStatus=? or
     * p.processStatus =? or p.processStatus =? )and p.status=? and
     * p.processType = ?"; lstParam.add(deptId); lstParam.add(userId);
     * lstParam.add(Constants.FILE_STATUS.ASSIGNED);
     * lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
     * lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD); lstParam.add(0l);
     * lstParam.add(Constants.PROCESS_TYPE.MAIN); }
     *
     * // Haitv21 thêm chỉ tiêu thông tin tìm kiếm // Do hieptq reset lại
     * params nên đoạn code này phải dưới các case if_ else bên trên if
     * (form.getSendDateFrom() != null) { hql += " AND f.sendDate >= ? ";
     * lstParam.add(form.getSendDateFrom()); } if (form.getSendDateTo() != null)
     * { hql += " AND f.sendDate <= ? ";
     * lstParam.add(addOneDay(form.getSendDateTo())); } // ngày thêm kho lưu trữ
     * if (form.getRepDateFrom() != null) { hql += " AND f.repDate >= ? ";
     * lstParam.add(form.getRepDateFrom()); } if (form.getRepDateTo() != null) {
     * hql += " AND f.repDate <= ? ";
     * lstParam.add(addOneDay(form.getRepDateTo())); }
     *
     * // Ngày phê duyệt từ ngày x tới ngày x if (form.getApproveDateFrom() !=
     * null) { hql += "AND f.approveDate >= ? ";
     * lstParam.add(form.getApproveDateFrom()); } if (form.getApproveDateTo() !=
     * null) { hql += " AND f.approveDate <= ? ";
     * lstParam.add(addOneDay(form.getApproveDateTo())); }
     *
     * // Người phê duyệt if (form.getLeaderStaffSignName() != null &&
     * form.getLeaderStaffSignName().length() > 0) { hql += "AND
     * lower(f.leaderStaffSignName) like ? ESCAPE '/' ";
     * lstParam.add(StringUtils.toLikeString(form.getLeaderStaffSignName().toLowerCase().trim()));
     * }
     *
     * // Tên doanh nghiệp if (form.getBusinessName() != null &&
     * form.getBusinessName().length() > 0) { hql += "AND lower(f.businessName)
     * like ? ESCAPE '/' ";
     * lstParam.add(StringUtils.toLikeString(form.getBusinessName().toLowerCase().trim()));
     * } // Xuất xứ if (form.getNationName() != null &&
     * form.getNationName().length() > 0) { hql += "AND lower(f.nationName) like
     * ? ESCAPE '/' ";
     * lstParam.add(StringUtils.toLikeString(form.getNationName().toLowerCase().trim()));
     * }
     *
     * // Nhà sản xuất if (form.getManufactureName() != null &&
     * form.getManufactureName().length() > 0) { hql += "AND (f.announcementId
     * in (select ann.announcementId from Announcement ann where
     * lower(ann.manufactureName) like ? ESCAPE '/'))";
     * lstParam.add(StringUtils.toLikeString(form.getManufactureName().toLowerCase().trim()));
     * } // Người thẩm định if (form.getReceiveUser() != null &&
     * form.getReceiveUser().length() > 0) { hql += "AND (f.fileId in (select
     * p.objectId from Process p where lower(p.receiveUser) like ? ESCAPE
     * '/'))";
     * lstParam.add(StringUtils.toLikeString(form.getReceiveUser().toLowerCase().trim()));
     * }
     *
     * // Số chứng nhận công bố if (form.getAnnouncementNo() != null &&
     * form.getAnnouncementNo().length() > 0) { hql += "AND
     * (f.announcementReceiptPaperId in (select a.announcementReceiptPaperId
     * from AnnouncementReceiptPaper a where lower(a.receiptNo) like ? ESCAPE
     * '/')) ";
     * lstParam.add(StringUtils.toLikeString(form.getAnnouncementNo().toLowerCase().trim()));
     * }
     *
     * // Số đăng ký kinh doanh if (form.getBusinessLicence() != null &&
     * form.getBusinessLicence().length() > 0) { hql += "AND (f.deptId in
     * (select b.businessId from Business b where lower(b.businessLicense) like
     * ? ESCAPE '/')) ";
     * lstParam.add(StringUtils.toLikeString(form.getBusinessLicence().toLowerCase().trim()));
     * }
     *
     * // Địa chỉ doanh nghiệp if (form.getBusinessAddress() != null &&
     * form.getBusinessAddress().length() > 0) { hql += "AND
     * lower(f.businessAddress) like ? ESCAPE '/' ";
     * lstParam.add(StringUtils.toLikeString(form.getBusinessAddress().toLowerCase().trim()));
     * }
     *
     * // Tên sản phẩm if (form.getProductName() != null &&
     * form.getProductName().length() > 0) { hql += "AND lower(f.productName)
     * like ? ESCAPE '/' ";
     * lstParam.add(StringUtils.toLikeString(form.getProductName().toLowerCase().trim()));
     * }
     *
     * // Nhóm sản phẩm if (form.getProductType() != null &&
     * form.getProductType() != -1l) { hql += "AND (f.detailProductId in (
     * select d.detailProductId from DetailProduct d where d.productType = ?))
     * "; lstParam.add(form.getProductType()); }
     *
     * // Lãnh đạo phòng if (form.getLeaderStaff() != null &&
     * form.getLeaderStaff().length() > 0) { hql += "AND (f.fileId in (select
     * p.objectId from Process p where lower(p.receiveUser) like ? ESCAPE '/'))
     * ";
     * lstParam.add(StringUtils.toLikeString(form.getLeaderStaff().toLowerCase().trim()));
     * }
     *
     * // Người thẩm xét if (form.getStaff() != null &&
     * form.getStaff().length() > 0) { hql += "AND (f.fileId in (select
     * p.objectId from Process p where lower(p.receiveUser) like ? ESCAPE '/'))
     * ";
     * lstParam.add(StringUtils.toLikeString(form.getStaff().toLowerCase().trim()));
     * }
     *
     * // Tỉnh - Thành Phố if (form.getProductType() != null &&
     * form.getBusinessProvinceId() != -1l) { hql += "AND (f.deptId in (select
     * b.businessId from Business b where b.businessProvinceId= ? )) ";
     * lstParam.add(form.getBusinessProvinceId()); }
     *
     * // Nơi lưu trữ if (form.getRepositoriesId() != null &&
     * form.getRepositoriesId() != -1l) { hql += "AND (f.repositoriesId = ? ) ";
     * lstParam.add(form.getRepositoriesId()); }
     *
     * // Lọc theo người tạo ( lưu trữ hồ sơ giấy ) if (form.getRepCreator() !=
     * null && (form.getSearchType() == 0 || form.getSearchType() == 2)) { hql
     * += "AND (f.fileId in (select p.objectId from Process p where
     * p.processStatus = 3 and p.receiveUserId = ?)) ";
     * lstParam.add(form.getRepCreator()); }
     *
     * // Kho lưu trữ if (form.getRepName() != null && form.getRepName() !=
     * -1l) { hql += "AND (f.repositoriesId = ? )";
     * lstParam.add(form.getRepName()); }
     *
     * // Trạng thái lưu trữ // Đã lưu trữ if (form.getRepStatus() != null &&
     * form.getRepStatus() == 1) { hql += "AND (f.repositoriesId <> null )"; }
     * // Chưa lưu trữ if (form.getRepStatus() != null && form.getRepStatus() ==
     * 2) { hql += "AND (f.repositoriesId = null )"; }
     *
     * if (userType.equals(Constants.ROLES.LEAD_OFFICE_ROLE)) { if (deptId !=
     * null) { hql += "AND f.agencyId = ? "; lstParam.add(deptId); } } if
     * (userType.equals(Constants.ROLES.STAFF_ROLE)) { if (deptId != null) { hql
     * += "and p.receiveGroupId = ? "; lstParam.add(deptId); } if (userId !=
     * null) { hql += "and p.receiveUserId = ? "; lstParam.add(userId); } } if
     * (userType.equals(Constants.ROLES.LEAD_UNIT)) { if (deptId != null) { hql
     * += " and p.receiveGroupId = ? and ( p.receiveUserId = null or
     * p.receiveUserId = ?) "; lstParam.add(deptId); lstParam.add(userId); } }
     * if (userType.equals(Constants.ROLES.CLERICAL_ROLE)) { if (deptId != null)
     * { // hql += "AND f.agencyId = ?)"; // lstParam.add(deptId); hql += "and
     * p.receiveGroupId = ? "; lstParam.add(deptId); } }
     *
     * }
     * if (userType.equals(Constants.ROLES.CLERICAL_ROLE) &&
     * form.getSearchType() != null) { switch
     * (Integer.parseInt(form.getSearchType().toString())) { case -4://5- Hồ sơ
     * đã yêu cầu nộp phí cấp số = Đã phê duyệt, chưa nộp lệ phí (files.status =
     * 6, fee_payment_info.status = 0,fee.fee_type = 1 , files.isSignPdf=1) hql
     * = " from Files f, FeePaymentInfo fpi where f.fileId = fpi.fileId and
     * (f.isTemp=null or f.isTemp=0) and f.isActive=1" + " and fpi.feeId in
     * (select fe.feeId from Fee fe where fe.feeType=1 and fe.isActive=1)" + "
     * and fpi.isActive=1" + " and fpi.status=0" + " and f.userSigned is not
     * null" + " and (f.status=?) and f.agencyId = ?"; lstParam.clear();
     * lstParam.add(Constants.FILE_STATUS.APPROVED); lstParam.add(deptId);
     * break; case -3://Hồ sơ đã nộp phí cấp số, chờ trả hồ sơ = Đã phê duyệt,
     * đã nộp lệ phí (files.status = 6, fee_payment_info.status = 1,
     * fee.fee_type=1, files.isSignPdf=2) hql = " from Files f, FeePaymentInfo
     * fpi where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and
     * f.isActive=1" + " and fpi.feeId in (select fe.feeId from Fee fe where
     * fe.feeType=1 and fe.isActive=1)" + " and fpi.isActive=1" + " and
     * fpi.status=1" + " and f.userSigned is not null" + " and f.isSignPdf = 2"
     * + " and (f.status=?)" + " and f.agencyId = ?";
     *
     * lstParam.clear(); lstParam.add(Constants.FILE_STATUS.APPROVED);
     * lstParam.add(deptId); break; case -2://Hồ sơ đã yêu cầu nộp phí cấp số =
     * Đã phê duyệt, chưa nộp lệ phí (files.status = 6, fee_payment_info.status
     * = 0,fee.fee_type = 1 , files.isSignPdf=1) hql = " from Files f,
     * FeePaymentInfo fpi where f.fileId = fpi.fileId and (f.isTemp=null or
     * f.isTemp=0) and f.isActive=1" + " and fpi.feeId in (select fe.feeId from
     * Fee fe where fe.feeType=1 and fe.isActive=1)" + " and fpi.isActive=1" + "
     * and fpi.status=0" + " and f.userSigned is not null" + " and f.isSignPdf =
     * 1" + " and (f.status=?)" + " and f.agencyId = ?"; lstParam.clear();
     * lstParam.add(Constants.FILE_STATUS.APPROVED); lstParam.add(deptId);
     * break; case -1://Hồ sơ chờ tiếp nhận = Mới nộp và đã xác nhận phí
     * (files.status = 1, fee_payment_info.status = 1, fee.fee_type=2), Mới nộp
     * SĐBS (18) hql = " from Files f, FeePaymentInfo fpi where f.fileId =
     * fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1" + " and
     * fpi.feeId in (select fe.feeId from Fee fe where fe.feeType=2 and
     * fe.isActive=1)" + " and fpi.isActive=1" + " and fpi.status=1" + " and
     * f.userSigned is not null" + " and (f.status=? or f.status=?) and
     * f.agencyId = ?"; lstParam.clear();
     * lstParam.add(Constants.FILE_STATUS.NEW);
     * lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD); lstParam.add(deptId);
     * break; case 0: hql = " from Files f, Process p where f.isActive = 1 and
     * f.fileId = p.objectId and (f.isTemp = null or f.isTemp = 0 ) ";
     * lstParam.clear(); hql += " and (f.status = ?)";
     * lstParam.add(form.getStatus()); hql += " and p.receiveGroupId = ? ";
     * lstParam.add(deptId); break; // case 14: // hql = " from Files f, Process
     * p where f.isActive = 1 and f.fileId = p.objectId and (f.isTemp = null or
     * f.isTemp = 0 ) "; // hql += " and (f.status = ?)"; // lstParam.clear();
     * // lstParam.add(status); // break; default:; } } // Query countQuery =
     * getSession().createQuery("select count(distinct f) " + hql); // Query
     * query = getSession().createQuery("select distinct f " + hql + " order by
     * f.fileId desc"); Query countQuery = getSession().createQuery("select
     * count(f) from Files f where f.fileId in (select distinct f.fileId " + hql
     * + ")"); Query query = getSession().createQuery("select f from Files f
     * where f.fileId in ( select distinct f.fileId " + hql + ") order by
     * f.sendDate ASC"); for (int i = 0; i < lstParam.size(); i++) {
     * query.setParameter(i, lstParam.get(i)); countQuery.setParameter(i,
     * lstParam.get(i)); }
     *
     * query.setFirstResult(start); query.setMaxResults(count); int total =
     * Integer.parseInt(countQuery.uniqueResult().toString()); List<Files>
     * lstResult = query.list(); // for (Files f : lstResult) { // hql = "select
     * p from Process p where p.objectType=? and p.objectId = ? and
     * p.processStatus=? and p.receiveUserId=?"; // query =
     * getSession().createQuery(hql); // query.setParameter(0,
     * Constants.OBJECT_TYPE.FILES); // query.setParameter(1, f.getFileId()); //
     * query.setParameter(2, Constants.FILE_STATUS.ASSIGNED); //
     * query.setParameter(3, userId); // List<Process> lstPro = query.list(); //
     * if (lstPro.size() > 0) { // for (int i = 0; i < lstPro.size(); i++) { //
     * if (lstPro.get(i).getProcessType().equals(1L)) { //
     * f.setProcessType(lstPro.get(i).getProcessType()); // break; // } else {
     * // f.setProcessType(lstPro.get(i).getProcessType()); // } // } // } // }
     * GridResult gr = new GridResult(total, lstResult); return gr; }
     *
     *
     */
    public boolean onReviewManyFiles(FilesForm form, Long deptId, String deptName, Long userId, String userName) {//SOS
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else {
                Long processStatus = file.getStatus();//trang thai ho so truoc khi thay doi
                Long status = form.getStatus();

                if (processStatus != null//141225 BINHNT update phan quyen ho so tham dinh
                        && (processStatus.equals(Constants.FILE_STATUS.EVALUATED)//DA THAM DINH DAT
                        || processStatus.equals(Constants.FILE_STATUS.FEDBACK_TO_REVIEW)//TRA LAI XEM XET LAI
                        || processStatus.equals(Constants.FILE_STATUS.EVALUATE_TO_ADD)//TRA LAI XEM XET LAI
                        || processStatus.equals(Constants.FILE_STATUS.FEDBACK_TO_ADD))) {//DA THAM DINH YEU CA SDBS
                    // Cap nhat trang thai ho so
                    file.setStatus(status);
                    file.setDisplayStatus(getFileStatusName(status));
                    String dateTime = DateTimeUtils.convertDateToString(dateNow, "dd/MM/yyyy HH:mm");
                    if (!status.equals(Constants.FILE_STATUS.REVIEW_TO_ADD)) {
                        file.setLeaderStaffRequest(userName + " " + dateTime + ":\n" + form.getLeaderStaffRequest());
                        file.setDisplayRequest(form.getLeaderStaffRequest());
                    }
                    file.setModifyDate(dateNow);
                    file.setEvaluateAddDate(dateNow);
                    file.setLeaderApproveName(form.getLeaderApproveName());
                    file.setLeaderApproveId(form.getLeaderApproveId());
                    // Cap nhat process
                    ProcessDAOHE pdhe = new ProcessDAOHE();
                    Process p = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, processStatus, Constants.FILE_STATUS.NEW_CREATE);
                    Process newP = new Process();
                    newP.setObjectId(form.getFileId());
                    newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                    newP.setSendDate(dateNow);
                    newP.setSendGroup(deptName);
                    newP.setSendGroupId(deptId);
                    newP.setSendUserId(userId);
                    newP.setSendUser(userName);
                    newP.setProcessStatus(status); // De xu ly
                    newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                    newP.setStatus(Constants.FILE_STATUS.NEW_CREATE); // Moi den chua xu ly
                    newP.setIsActive(Constants.ACTIVE_STATUS.ACTIVE);
                    newP.setReceiveDate(dateNow);
                    newP.setReceiveUser(form.getLeaderApproveName());
                    newP.setReceiveUserId(form.getLeaderApproveId());
                    newP.setReceiveGroup(file.getAgencyName());
                    newP.setReceiveGroupId(file.getAgencyId());
                    getSession().save(newP);
                    if (p != null) {
                        p.setStatus(status);
                        p.setLastestComment(form.getLeaderStaffRequest());
                        getSession().update(p);
                    } else {
                        Process pFeedbacktoAdd = null;
                        pFeedbacktoAdd = pdhe.getProcessByAction(form.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.FEDBACK_TO_ADD, Constants.FILE_STATUS.NEW_CREATE);
                        if (pFeedbacktoAdd != null) {
                            pFeedbacktoAdd.setStatus(status);
                            pFeedbacktoAdd.setLastestComment(form.getLeaderStaffRequest());
                            getSession().update(pFeedbacktoAdd);
                        }
                    }
                    update(file);
                } else {
                    return false;
                }
            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    //hieptq update 070515
    public boolean saveDraftComment(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            if (file == null) {
                bReturn = false;
            } else {
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
                rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.TPLN);
                rcbo.setIsLastChange(Constants.ACTIVE_STATUS.ACTIVE);
                RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
                RequestComment lastRQBo = rqdaohe.findLastRequestComment(file.getFileId(), Constants.ACTIVE_STATUS.ACTIVE);
                if (lastRQBo != null) {
                    rcbo.setLastContent(lastRQBo.getContent());
                    lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                    getSession().update(lastRQBo);
                }
                getSession().save(rcbo);
            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    //hieptq update phi thuoc la
    private Boolean saveFeeTL(Long fileId, Long fileType, Long productType, Procedure pro, Long productTypeIdOld) {
        if (fileId != null) {
            Date dateNow = null;
            try {
                dateNow = getSysdate();
            } catch (Exception ex) {
                log.error(ex.getMessage());
                return false;
            }

            String hql = "select fpif from FeePaymentInfo fpif where fpif.fileId =:fileId and fpif.isActive = 1";
            Query query = getSession().createQuery(hql);
            query.setParameter("fileId", fileId);
            List<FeePaymentInfo> FeePaymentInfo = query.list();
            //
            // truong hop sao chep va luu tam productType co the = null, vi the phai set = -1
            //

            if (FeePaymentInfo.isEmpty()) {
                FeeDAOHE fdhe = new FeeDAOHE();
                List<FeeProcedure> feenew = fdhe.findAllFeeByProcedureId(fileType);
                // check le phi cap so theo loai ho so
                if (feenew != null && feenew.size() > 0) {
                    FeePaymentInfo fpif = new FeePaymentInfo();
                    for (int i = 0; i < feenew.size(); i++) {
                        fpif = new FeePaymentInfo();
                        fpif.setCreateDate(dateNow);
                        fpif.setFeePaymentTypeId(3l);
                        fpif.setPaymentConfirm("admin");
                        fpif.setPaymentDate(dateNow);
                        fpif.setDateConfirm(dateNow);
                        fpif.setPaymentPerson("admin");
                        fpif.setPaymentInfo("hồ sơ thuốc là không đóng phí");
                        fpif.setStatus(1l);
                        fpif.setFileId(fileId);
                        fpif.setIsActive(1l);
                        fpif.setFeeId(feenew.get(i).getFeeId());
                        Fee feeTemp = (Fee) findById(Fee.class, "feeId", feenew.get(i).getFeeId());
                        fpif.setCost(feeTemp.getPrice());
                        getSession().save(fpif);
                    }
                }

                // hieptq update 280515 set phi ho so cap lai
                if (productType != null) {
                    if (pro != null && pro.getTypeFee() == 7l) {
                        FeePaymentInfo fpif = new FeePaymentInfo();
                        Fee findfee1 = fdhe.findFeeByCode("TL");
                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(1l);
                        fpif.setFileId(fileId);
                        fpif.setDateConfirm(dateNow);
                        fpif.setFeePaymentTypeId(3l);
                        fpif.setPaymentConfirm("admin");
                        fpif.setPaymentDate(dateNow);
                        fpif.setPaymentPerson("admin");
                        fpif.setPaymentInfo("hồ sơ thuốc là không đóng phí");
                        if (findfee1 != null) {
                            fpif.setFeeId(findfee1.getFeeId());
                            fpif.setCost(findfee1.getPrice());
                            fpif.setCostCheck(findfee1.getPrice());
                            fpif.setFeeIdOld(findfee1.getFeeId());
                        } else {
                            return false;
                        }
                        fpif.setIsActive(1l);
                        getSession().save(fpif);
                    }
                }
                FilesDAOHE filesdhe = new FilesDAOHE();
                Files f = filesdhe.findById(fileId);
                f.setIsFee(1l);
                getSession().save(f);
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /*
     *Hiepvv 08/01
     *Luu phi cua ho so sua doi bo sung sau cong bo
     */
    private Boolean saveFeeChangesAfterAnnounced(Long fileId, Long fileType) {
        if (fileId != null) {
            Date dateNow = getSysdate();
            String hql = "select fpif from FeePaymentInfo fpif"
                    + " where fpif.fileId =:fileId"
                    + " and fpif.isActive = 1";
            Query query = getSession().createQuery(hql);
            query.setParameter("fileId", fileId);
            List<FeePaymentInfo> FeePaymentInfo = query.list();
            // truong hop sao chep va luu tam productType co the = null, vi the phai set = -1
            if (FeePaymentInfo.isEmpty()) {
                FeeDAOHE fdhe = new FeeDAOHE();
                List<FeeProcedure> feenew = fdhe.findAllFeeByProcedureId(fileType);
                // check le phi cap so theo loai ho so
                if (feenew != null && feenew.size() > 0) {
                    FeePaymentInfo fpif = new FeePaymentInfo();
                    for (int i = 0; i < feenew.size(); i++) {
                        fpif = new FeePaymentInfo();
                        //Thong tin co ban, status=1 = da dong phi
                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(1l);
                        fpif.setFileId(fileId);
                        fpif.setIsActive(1l);
                        fpif.setFeeId(feenew.get(i).getFeeId());
                        Fee feeTemp = (Fee) findById(Fee.class, "feeId", feenew.get(i).getFeeId());
                        fpif.setCost(feeTemp.getPrice());

                        //Thong tin fix cung cho du du lieu
                        fpif.setPaymentPerson("ATTP");
                        fpif.setPaymentDate(dateNow);
                        fpif.setFeePaymentTypeId(3L);
                        fpif.setBillPath("");
                        fpif.setPaymentConfirm("ATTP");
                        fpif.setDateConfirm(dateNow);
                        fpif.setCostCheck(0L);

                        getSession().save(fpif);
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private Boolean saveFee4Star(Long fileId, Long fileType) {
        if (fileId != null) {
            Date dateNow = getSysdate();
            String hql = "select fpif from FeePaymentInfo fpif "
                    + "where fpif.fileId =:fileId "
                    + "and fpif.isActive = 1";
            Query query = getSession().createQuery(hql);
            query.setParameter("fileId", fileId);
            List<FeePaymentInfo> lstFeePaymentInfo = query.list();
            // truong hop sao chep va luu tam productType co the = null, vi the phai set = -1
            if (lstFeePaymentInfo.isEmpty()) {
                FeeDAOHE fdhe = new FeeDAOHE();
                List<FeeProcedure> lstFee = fdhe.findAllFeeByProcedureId(fileType);
                //check le phi cap so theo loai ho so
                if (lstFee != null && lstFee.size() > 0) {
                    FeePaymentInfo fpif;
                    for (int i = 0; i < lstFee.size(); i++) {
                        fpif = new FeePaymentInfo();
                        //Thong tin co ban, status=1 = da dong phi
                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(0l);
                        fpif.setFileId(fileId);
                        fpif.setIsActive(1l);
                        fpif.setFeeId(lstFee.get(i).getFeeId());
                        Fee feeTemp = (Fee) findById(Fee.class, "feeId", lstFee.get(i).getFeeId());
                        if (feeTemp.getFeeCode().equals(Constants.FEE_TYPE.KS4SLP)) {
                            fpif.setCost(feeTemp.getPrice());
                        } else if (feeTemp.getFeeCode().equals(Constants.FEE_TYPE.KS4STD)) {
                            String sql = "select count(*) from ProductInFile "
                                    + "where file_ID = :fileId";
                            Query qry1 = getSession().createQuery(sql);
                            qry1.setParameter("fileId", fileId);
                            int count = (int) (long) (Long) qry1.uniqueResult();
                            fpif.setCost(feeTemp.getPrice() * count);
                        } else if (feeTemp.getPrice() != null) {
                            fpif.setCost(feeTemp.getPrice());
                        } else {
                            fpif.setCost(0L);
                        }
                        getSession().save(fpif);
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean onEvaluate(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());//lay thong tin chi tiet ho so
            Date dateNow = getSysdate();
            UsersDAOHE udaohe = new UsersDAOHE();
            if (file == null) {
                bReturn = false;
            } else// Cap nhat trang thai ho so
             if ((file.getStatus() != null
                        && form.getStatus() != null)//141225 binhnt update phan quyen ho so tham dinh
                        && (file.getStatus().equals(Constants.FILE_STATUS.ASSIGNED)//da phan cong
                        || file.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE)//tra lai tham dinh lai
                        || file.getStatus().equals(Constants.FILE_STATUS.EVALUATED)//tham dinh
                        || file.getStatus().equals(Constants.FILE_STATUS.REVIEW_TO_ADD)//đã xem xét nội dung cv sđbs LDC
                        || file.getStatus().equals(Constants.FILE_STATUS.REVIEWED_TO_ADD)//Đã xem xét yêu cầu SĐBS LDC
                        || file.getStatus().equals(Constants.FILE_STATUS.APPROVE_TO_ADD)//đã phê duyệt nội dung thông báo VT
                        || file.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_ADD)//da tra lai bo sung ho so
                        || file.getStatus().equals(Constants.FILE_STATUS.RECEIVED_TO_ADD))) {//da tiep nhan ho so sdbs
                    Long processStatus = file.getStatus();
                    file.setStatus(form.getStatus());
                    /*
                 khi tham dinh ho so cvien thuc hien tham dinh
                 neu la cv chuyen pp hoac truong phong
                 neu la pp chuyen tp hoac ldc
                     */
                    boolean isReview = false;
                    if (form.getLeaderReviewId() != null) {//gui lanh dao tham dinh
                        udaohe = new UsersDAOHE();
                        Users uReceive = udaohe.findById(form.getLeaderReviewId());
                        if (form.getLeaderReviewName() == null || form.getLeaderReviewName().equals("")) {
                            form.setLeaderReviewName(uReceive.getFullName());
                        }
                        List<String> lstStaff = new ArrayList<String>();
                        lstStaff.add(Constants.POSITION.VFA_CV);
                        lstStaff.add(Constants.POSITION.NV);
                        if (udaohe.checkRoleUserOfLst(deptId, userId, lstStaff)) {
                            if (udaohe.checkTruongPhong(form.getLeaderReviewId())) {
                                file.setLeaderReviewId(form.getLeaderReviewId());
                                file.setLeaderReviewName(form.getLeaderReviewName());
//                            file.setLeaderEvaluateId(null);
//                            file.setLeaderEvaluateName(null);
                                file.setLeaderApproveId(null);
                                file.setLeaderApproveName(null);
                            } else {
                                file.setLeaderEvaluateId(form.getLeaderReviewId());
                                file.setLeaderEvaluateName(form.getLeaderReviewName());
                                file.setLeaderReviewId(null);
                                file.setLeaderReviewName(null);
                                file.setLeaderApproveId(null);
                                file.setLeaderApproveName(null);
                            }
                        } else {
                            List<String> lstTP = new ArrayList<String>();
                            lstTP.add(Constants.POSITION.LEADER_OF_STAFF_T);
                            lstTP.add(Constants.POSITION.GDTT);
                            if (udaohe.checkRoleUserOfLst(deptId, form.getLeaderReviewId(), lstTP)) {
                                file.setLeaderReviewId(form.getLeaderReviewId());
                                file.setLeaderReviewName(form.getLeaderReviewName());
//                            file.setLeaderEvaluateId(null);
//                            file.setLeaderEvaluateName(null);
                                file.setLeaderApproveId(null);
                                file.setLeaderApproveName(null);
                            } else {
//                            file.setLeaderReviewId(null);
//                            file.setLeaderReviewName(null);
//                            file.setLeaderEvaluateId(null);
//                            file.setLeaderEvaluateName(null);
                                file.setLeaderApproveId(form.getLeaderReviewId());
                                file.setLeaderApproveName(form.getLeaderReviewName());
                                if (file.getStatus().equals(Constants.FILE_STATUS.EVALUATED)) {
                                    file.setStatus(Constants.FILE_STATUS.REVIEWED);
                                } else if (file.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_ADD)) {
                                    file.setStatus(Constants.FILE_STATUS.REVIEW_TO_ADD);
                                }
                                isReview = true;
                            }
                        }
                    } else {
                        udaohe = new UsersDAOHE();
                        List<String> lstLeader = new ArrayList<String>();
                        lstLeader.add(Constants.POSITION.LEADER_OF_STAFF_T);
                        lstLeader.add(Constants.POSITION.GDTT);
                        Users ubo = null;
                        List<Users> lstLeaderOfDept = udaohe.findLstUserByLstPosition(deptId, lstLeader);
                        if (lstLeaderOfDept != null
                                && lstLeaderOfDept.size() > 0) {
                            ubo = lstLeaderOfDept.get(0);
                            file.setLeaderReviewId(ubo.getUserId());
                            file.setLeaderReviewName(ubo.getFullName());
                            file.setLeaderApproveId(null);
                            file.setLeaderApproveName(null);
                            form.setLeaderReviewId(ubo.getUserId());
                            form.setLeaderReviewName(ubo.getFullName());
                        }
                    }
                    // neu tra lai de bo sung -> thiet lap bien have_temp = 1 de biet ma tao ra history khi sua doi
                    if (file.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {
                        file.setHaveTemp(1l);
                    }
                    file.setDisplayStatus(getFileStatusName(file.getStatus()));
                    String dateTime = DateTimeUtils.convertDateToString(dateNow, "dd/MM/yyyy HH:mm");
                    //file.setStaffRequest(userName + " " + dateTime + ":\n" + form.getStaffRequest());
                    String prefix = userName + " " + dateTime + ":\n";
                    if (form.getStaffRequest()
                            != null && form.getStaffRequest().trim().length() > 0) {
                        file.setStaffRequest(prefix + form.getStaffRequest());
                    }
                    file.setModifyDate(dateNow);
                    if (form.getEffectiveDate() != null) {
                        file.setEffectiveDate(form.getEffectiveDate());
                    } else {
                        file.setEffectiveDate(Constants.EFFECTIVEDATE.THREE);
                    }
                    file.setIsTypeChange(form.getIsTypeChange());
                    file.setLastType(form.getLastType());
                    //Cap nhat process cu
                    ProcessDAOHE pdhe = new ProcessDAOHE();
                    Process p = pdhe.getProcessByAction(
                            form.getFileId(),
                            Constants.Status.ACTIVE,
                            Constants.OBJECT_TYPE.FILES,
                            processStatus,
                            Constants.FILE_STATUS.NEW_CREATE
                    );
                    if (p != null) {
                        p.setStatus(file.getStatus());
                        p.setLastestComment(form.getStaffRequest());
                        getSession().update(p);
                    }
                    //!Cap nhat process cu
                    if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED)
                            || form.getStatus().equals(Constants.FILE_STATUS.FEDBACK_TO_ADD)) {
                        //tham xet dat, tham xet khong dat deu gui len cho to truong to tham xet xem xet
                        Process newP = new Process();
                        newP.setObjectId(form.getFileId());
                        newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                        newP.setProcessType(Constants.PROCESS_TYPE.MAIN);

                        newP.setSendDate(dateNow);
                        newP.setSendGroup(deptName);
                        newP.setSendGroupId(deptId);
                        newP.setSendUserId(userId);
                        newP.setSendUser(userName);

                        newP.setReceiveDate(dateNow);
                        newP.setReceiveUserId(form.getLeaderReviewId());
                        newP.setReceiveUser(form.getLeaderReviewName());
//                    if (file.getLeaderEvaluateId() != null) {
//                        newP.setReceiveUserId(file.getLeaderEvaluateId());
//                        newP.setReceiveUser(file.getLeaderEvaluateName());
//                    } else if (file.getLeaderReviewId() != null) {
//                        newP.setReceiveUserId(file.getLeaderReviewId());
//                        newP.setReceiveUser(file.getLeaderReviewName());
//                    } else if (file.getLeaderApproveId() != null) {
//                        newP.setReceiveUserId(file.getLeaderApproveId());
//                        newP.setReceiveUser(file.getLeaderApproveName());
//                    }

                        if (!isReview) {
                            newP.setReceiveGroup(deptName);
                            newP.setReceiveGroupId(deptId);
                        } else {
                            Users leaderApprove = udaohe.findById(file.getLeaderApproveId());
                            newP.setReceiveGroup(leaderApprove.getDeptName());
                            newP.setReceiveGroupId(leaderApprove.getDeptId());
                        }

                        newP.setProcessStatus(file.getStatus()); // De xu ly
                        newP.setStatus(0l); // Moi den chua xu ly
                        newP.setIsActive(1l);
                        getSession().save(newP);
                    } else if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {
                        //tao thong bao yeu cau sdbs gui toi doanh nghiep
                        Process newP = new Process();
                        newP.setObjectId(form.getFileId());
                        newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                        newP.setProcessType(Constants.PROCESS_TYPE.MAIN);

                        newP.setSendDate(dateNow);
                        newP.setSendGroup(deptName);
                        newP.setSendGroupId(deptId);
                        newP.setSendUserId(userId);
                        newP.setSendUser(userName);

                        newP.setReceiveDate(dateNow);
                        ProcessDAOHE psdhe = new ProcessDAOHE();
                        Process pold = psdhe.getProcessByAction(
                                form.getFileId(),
                                Constants.Status.ACTIVE,
                                Constants.OBJECT_TYPE.FILES,
                                Constants.FILE_STATUS.NEW,
                                Constants.FILE_STATUS.RECEIVED
                        );
                        if (pold != null) {
                            newP.setReceiveGroupId(pold.getSendGroupId());
                            newP.setReceiveGroup(pold.getSendGroup());
                            newP.setReceiveUserId(pold.getSendUserId());
                            newP.setReceiveUser(pold.getSendUser());
                        } else {
                            newP.setReceiveGroup(deptName);
                            newP.setReceiveGroupId(deptId);
                        }

                        newP.setProcessStatus(file.getStatus()); //De xu ly
                        newP.setStatus(0l); //Moi den chua xu ly
                        newP.setIsActive(1l);

                        getSession().save(newP);
                        //xóa bản ghi temp trước nếu có (lưu vào vùng lưu trữ)
                        updateSetNotLastIsTemp(file.getFileId());
                        //xóa bản ghi temp trước nếu có (lưu vào vùng lưu trữ)
                        ProcessCommentDAOHE pcdaohe = new ProcessCommentDAOHE();
                        int a = pcdaohe.updateSetNotLastIsTemp(file.getFileId());
                    } else if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATE_TO_ADD)) {//da soan du thao thong bao sdbs ho so                           
                        /*
                         ho so sau khi da tra lai chuyen vien de soan du thao tb sdbs
                         cv vao tao ban du thao
                         sau khi tao xong luu
                         gui noi dung cho lanh dao phong xem xet
                         */
                        Process newP = new Process();
                        newP.setObjectId(form.getFileId());
                        newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                        newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                        newP.setProcessStatus(file.getStatus()); // De xu ly
                        newP.setStatus(Constants.ACTIVE_STATUS.DEACTIVE);// Moi den chua xu ly-150120
                        newP.setIsActive(Constants.ACTIVE_STATUS.ACTIVE);//-150120

                        newP.setSendDate(dateNow);
                        newP.setSendGroup(deptName);
                        newP.setSendGroupId(deptId);
                        newP.setSendUserId(userId);
                        newP.setSendUser(userName);

                        newP.setReceiveDate(dateNow);
                        if (form.getLeaderReviewId() != null) {
                            newP.setReceiveUserId(form.getLeaderReviewId());
                            file.setLeaderReviewId(form.getLeaderReviewId());

                        }
                        if (form.getLeaderReviewName() != null) {
                            newP.setReceiveUser(form.getLeaderReviewName());
                            file.setLeaderReviewName(form.getLeaderReviewName());
                        }
                        newP.setReceiveGroup(deptName);
                        newP.setReceiveGroupId(deptId);
                        getSession().save(newP);

//cap nhat noi dung thong bao - tao noi dung thong bao
                        RequestComment rcbo = new RequestComment();
                        if (form.getStaffRequest() != null) {
                            rcbo.setContent(form.getStaffRequest());
                        } else {
                            rcbo.setContent("Chuyên viên chưa có nội dung.");
                        }
                        rcbo.setCreateBy(userId);
                        rcbo.setCreateDate(dateNow);
                        rcbo.setUserId(userId);
                        rcbo.setUserName(userName);
                        rcbo.setStatus(1L);
                        rcbo.setIsActive(1L);
                        rcbo.setGroupId(deptId);
                        rcbo.setGroupName(deptName);
                        rcbo.setObjectId(form.getFileId());
                        rcbo.setRequestType(Constants.REQUEST_COMMENT_TYPE.TBSDBS);//-150120
                        rcbo.setIsLastChange(Constants.ACTIVE_STATUS.ACTIVE);//-150120
                        //!luu noi dung du thao
                        //u150119 binhnt53 update lại nội dung thông tin.
                        RequestCommentDAOHE rqdaohe = new RequestCommentDAOHE();
                        RequestComment lastRQBo = rqdaohe.findLastRequestComment(file.getFileId(), Constants.ACTIVE_STATUS.ACTIVE);
                        if (lastRQBo != null) {
                            rcbo.setLastContent(lastRQBo.getContent());
                            lastRQBo.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                            getSession().update(lastRQBo);
                        }
                        getSession().save(rcbo);
//!u150119 binhnt53 update lại nội dung thông tin.
                    } else if ((form.getStatus()).equals(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE)) {//hieptq update 230415
                        Process newP = new Process();
                        newP.setObjectId(form.getFileId());
                        newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                        newP.setSendDate(dateNow);
                        newP.setSendGroup(deptName);
                        newP.setSendGroupId(deptId);
                        newP.setSendUserId(userId);
                        newP.setSendUser(userName);
                        newP.setProcessStatus(p.getStatus()); // De xu ly
                        newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                        newP.setStatus(Constants.FILE_STATUS.NEW_CREATE); // Moi den chua xu ly
                        newP.setIsActive(Constants.ACTIVE_STATUS.ACTIVE);
                        newP.setReceiveDate(dateNow);
                        if (p != null) {
                            //lay process tham dinh ho so
                            ProcessDAOHE psdhe = new ProcessDAOHE();
                            Process pold = psdhe.getProcessByAction(
                                    form.getFileId(),
                                    Constants.Status.ACTIVE,
                                    Constants.OBJECT_TYPE.FILES,
                                    Constants.FILE_STATUS.EVALUATED,
                                    p.getStatus()
                            );
                            if (pold != null) {
                                newP.setReceiveGroup(pold.getSendGroup());
                                newP.setReceiveGroupId(pold.getSendGroupId());
                                if (file != null
                                        && file.getStaffProcess() != null
                                        && file.getNameStaffProcess() != null) {
                                    newP.setReceiveUser(file.getNameStaffProcess());
                                    newP.setReceiveUserId(file.getStaffProcess());
                                } else {
                                    newP.setReceiveUser(pold.getSendUser());
                                    newP.setReceiveUserId(pold.getSendUserId());
                                }
                            } else {//141218u binhnt53 fix loi ho so lanh dao phong tra lai voi
                                pold = psdhe.getProcessByAction(
                                        form.getFileId(),
                                        Constants.Status.ACTIVE,
                                        Constants.OBJECT_TYPE.FILES,
                                        Constants.FILE_STATUS.FEDBACK_TO_ADD,
                                        p.getStatus()
                                );
                                if (pold != null) {
                                    newP.setReceiveGroup(pold.getSendGroup());
                                    newP.setReceiveGroupId(pold.getSendGroupId());
                                    if (file != null
                                            && file.getStaffProcess() != null
                                            && file.getNameStaffProcess() != null) {
                                        newP.setReceiveUser(file.getNameStaffProcess());
                                        newP.setReceiveUserId(file.getStaffProcess());
                                    } else {
                                        newP.setReceiveUser(pold.getSendUser());
                                        newP.setReceiveUserId(pold.getSendUserId());
                                    }
                                } else if (p != null) {// Gui lai cho chinh nguoi gui
                                    newP.setReceiveGroup(p.getSendGroup());
                                    newP.setReceiveGroupId(p.getSendGroupId());
                                    newP.setReceiveUser(p.getSendUser());
                                    newP.setReceiveUserId(p.getSendUserId());
                                }
                            }
                        }
                        getSession().save(newP);
                    }
                    //insert noi dung tham dinh
                    if (form.getEvaluationRecordsForm() != null) {
                        EvaluationRecordsForm evaRecordForm = new EvaluationRecordsForm();

                        evaRecordForm.setCreateDate(dateNow);
                        evaRecordForm.setSendDate(file.getSendDate());
                        evaRecordForm.setBusinessName(file.getBusinessName());
                        evaRecordForm.setBusinessAddress(file.getBusinessAddress());
                        evaRecordForm.setProductName(file.getProductName());
                        evaRecordForm.setLegal(form.getEvaluationRecordsForm().getLegal());
                        evaRecordForm.setLegalContent(form.getEvaluationRecordsForm().getLegalContent());
                        evaRecordForm.setFoodSafetyQuality(form.getEvaluationRecordsForm().getFoodSafetyQuality());
                        evaRecordForm.setFoodSafetyQualityContent(form.getEvaluationRecordsForm().getFoodSafetyQualityContent());
                        evaRecordForm.setEffectUtility(form.getEvaluationRecordsForm().getEffectUtility());
                        evaRecordForm.setEffectUtilityContent(form.getEvaluationRecordsForm().getEffectUtilityContent());
                        evaRecordForm.setFilesStatus(file.getStatus());
                        evaRecordForm.setMainContent(file.getStaffRequest());
                        evaRecordForm.setFirstStaffId(userId);
                        evaRecordForm.setFirstStaffName(userName);
                        evaRecordForm.setSecondStaffId(userId);
                        evaRecordForm.setSecondStaffName(userName);
                        evaRecordForm.setThirdStaffId(userId);
                        evaRecordForm.setThirdStaffName(userName);
                        evaRecordForm.setLeederStaffId(userId);
                        evaRecordForm.setLeederStaffName(userName);
                        evaRecordForm.setFilesId(file.getFileId());

                        EvaluationRecords evaluationRecordsBo;
                        evaluationRecordsBo = evaRecordForm.convertToEntity();
                        getSession().save(evaluationRecordsBo);
                        boolean bInsertRC = insertRequestComment(
                                file.getFileId(),
                                form,
                                userId,
                                userName,
                                deptId,
                                deptName,
                                dateNow
                        );//binhnt53 150130
                    } else if (form.getEvaluationRecordsFormOnGrid() != null) {
                        EvaluationRecordsFormOnGrid evaRecordForm = new EvaluationRecordsFormOnGrid();

                        evaRecordForm.setCreateDate(dateNow);
                        evaRecordForm.setSendDate(file.getSendDate());
                        evaRecordForm.setBusinessName(file.getBusinessName());
                        evaRecordForm.setBusinessAddress(file.getBusinessAddress());
                        evaRecordForm.setProductName(file.getProductName());
                        evaRecordForm.setLegal(form.getEvaluationRecordsFormOnGrid().getLegal());
                        evaRecordForm.setLegalContent(form.getEvaluationRecordsFormOnGrid().getLegalContent());
                        evaRecordForm.setFoodSafetyQuality(form.getEvaluationRecordsFormOnGrid().getFoodSafetyQuality());
                        evaRecordForm.setFoodSafetyQualityContent(form.getEvaluationRecordsFormOnGrid().getFoodSafetyQualityContent());
                        evaRecordForm.setEffectUtility(form.getEvaluationRecordsFormOnGrid().getEffectUtility());
                        evaRecordForm.setEffectUtilityContent(form.getEvaluationRecordsFormOnGrid().getEffectUtilityContent());
                        evaRecordForm.setFilesStatus(file.getStatus());
                        evaRecordForm.setMainContent(file.getStaffRequest());
                        evaRecordForm.setFirstStaffId(userId);
                        evaRecordForm.setFirstStaffName(userName);
                        evaRecordForm.setSecondStaffId(userId);
                        evaRecordForm.setSecondStaffName(userName);
                        evaRecordForm.setThirdStaffId(userId);
                        evaRecordForm.setThirdStaffName(userName);
                        evaRecordForm.setLeederStaffId(userId);
                        evaRecordForm.setLeederStaffName(userName);
                        evaRecordForm.setFilesId(file.getFileId());

                        EvaluationRecords evaluationRecordsBo;
                        evaluationRecordsBo = evaRecordForm.convertToEntity();
                        getSession().save(evaluationRecordsBo);
                        boolean bInsertRC = insertRequestCommentOnGrid(
                                file.getFileId(),
                                form,
                                userId,
                                userName,
                                deptId,
                                deptName,
                                dateNow
                        );//binhnt53 150130
                    }

                    if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {//140721 binhnt
                        try {//140627 THIET LAP HAN SDBS HO SO
                            ResourceBundle rb = ResourceBundle.getBundle("config");
                            Procedure procedurebo;
                            ProcedureDAOHE procedureDAOHE = new ProcedureDAOHE();
                            procedurebo = procedureDAOHE.findById(file.getFileType());
                            int SD = 0;
                            try {
                                SD = Integer.parseInt(rb.getString(procedurebo.getDescription() + "_SD"));
                            } catch (NumberFormatException ex) {
                                log.error(ex.getMessage());
                            }
                            if (SD > 0) {
                                file.setDeadlineAddition(getDateWorkingTime(SD));
                            }
                        } catch (Exception ex) {
                            log.error(ex.getMessage());
                        }//!140627 THIET LAP HAN SDBS HO SO
                        //sms
                        /* disable send sms binhnt53 150205
                     MessageSmsDAOHE msdhe = new MessageSmsDAOHE();
                     String msg = "Ho so ma: " + file.getFileCode() + " cua doanh nghiep: " + file.getBusinessName() + " dang trong trang thai: da thong bao yeu cau sdbs";
                     msdhe.saveMessageSMS(userId, file.getUserCreateId(), msg);
                         */
                        //email
                        MessageEmailDAOHE msedhe = new MessageEmailDAOHE();
                        String msge = "Hồ sơ mã: " + file.getFileCode()
                                + " của doanh nghiệp: " + file.getBusinessName()
                                + " đang trong trạng thái: Đã thông báo yêu cầu sửa đổi bổ sung.";
                        msedhe.saveMessageEmail(userId, file.getUserCreateId(), msge);
                    }//!140721
                    update(file);
                } else {
                    log.error("Lỗi hệ thống: Phân quyền xử lý hồ sơ: " + file.getFileCode());
                    return false;
                }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    public boolean onEvaluateByLeaderManyFiles(FilesForm form, Long deptId, String deptName, Long userId, String userName) {//SOS
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            boolean isReview = false;
            if (file == null) {
                bReturn = false;
            } else {
                Long processStatus = file.getStatus();//trang thai ho so truoc khi thay doi
                Long status = form.getStatus();
                file.setStatus(status);
                file.setDisplayStatus(getFileStatusName(status));
                String dateTime = DateTimeUtils.convertDateToString(dateNow, "dd/MM/yyyy HH:mm");
                file.setLeaderStaffRequest(userName + " " + dateTime + ":\n" + form.getLeaderStaffRequest());
                file.setDisplayRequest(form.getLeaderStaffRequest());
                file.setModifyDate(dateNow);
                file.setEvaluateAddDate(dateNow);

                UsersDAOHE udaohe = new UsersDAOHE();
                List<String> lstTP = new ArrayList<String>();
                lstTP.add(Constants.POSITION.LEADER_OF_STAFF_T);
                lstTP.add(Constants.POSITION.GDTT);
                if (udaohe.checkRoleUserOfLst(deptId, form.getLeaderReviewId(), lstTP)) {
                    file.setLeaderReviewId(form.getLeaderReviewId());
                    file.setLeaderReviewName(form.getLeaderReviewName());
                    file.setLeaderApproveId(null);
                    file.setLeaderApproveName(null);
                } else {
                    file.setLeaderApproveId(form.getLeaderReviewId());
                    file.setLeaderApproveName(form.getLeaderReviewName());
                    file.setStatus(Constants.FILE_STATUS.REVIEWED);
                    file.setDisplayStatus(getFileStatusName(Constants.FILE_STATUS.REVIEWED));
                    isReview = true;
                }
                // Cap nhat process
                ProcessDAOHE pdhe = new ProcessDAOHE();

                Process newP = new Process();
                newP.setObjectId(form.getFileId());
                newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                newP.setSendDate(dateNow);
                newP.setSendGroup(deptName);
                newP.setSendGroupId(deptId);
                newP.setSendUserId(userId);
                newP.setSendUser(userName);
                newP.setProcessStatus(file.getStatus()); // De xu ly
                newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                newP.setStatus(Constants.FILE_STATUS.NEW_CREATE); // Moi den chua xu ly
                newP.setIsActive(Constants.ACTIVE_STATUS.ACTIVE);
                newP.setReceiveDate(dateNow);
                newP.setReceiveUser(form.getLeaderReviewName());
                newP.setReceiveUserId(form.getLeaderReviewId());
                if (isReview) {
                    Users leaderApprove = udaohe.findById(file.getLeaderApproveId());
                    newP.setReceiveGroup(leaderApprove.getDeptName());
                    newP.setReceiveGroupId(leaderApprove.getDeptId());
                } else {
                    newP.setReceiveGroup(deptName);
                    newP.setReceiveGroupId(deptId);
                }
                getSession().save(newP);

                Process p = pdhe.getProcessByAction(form.getFileId(),
                        Constants.Status.ACTIVE,
                        Constants.OBJECT_TYPE.FILES,
                        processStatus,
                        Constants.FILE_STATUS.NEW_CREATE);
                p.setStatus(file.getStatus());
                p.setLastestComment(form.getLeaderStaffRequest());
                getSession().update(p);

                update(file);
            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    public boolean onEvaluateByLeaderManyFilesToAdd(FilesForm form, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Files file = findById(form.getFileId());
            Date dateNow = getSysdate();
            boolean isReview = false;
            if (file == null) {
                bReturn = false;
            } else {
                Long processStatus = file.getStatus();//trang thai ho so truoc khi thay doi
                Long status = form.getStatus();
                file.setStatus(status);
                file.setDisplayStatus(getFileStatusName(status));
                String dateTime = DateTimeUtils.convertDateToString(dateNow, "dd/MM/yyyy HH:mm");
                file.setLeaderStaffRequest(userName + " " + dateTime + ":\n" + form.getLeaderStaffRequest());
                file.setDisplayRequest(form.getLeaderStaffRequest());
                file.setModifyDate(dateNow);
                file.setEvaluateAddDate(dateNow);

                UsersDAOHE udaohe = new UsersDAOHE();
                List<String> lstTP = new ArrayList<String>();
                lstTP.add(Constants.POSITION.LEADER_OF_STAFF_T);
                lstTP.add(Constants.POSITION.GDTT);
                if (udaohe.checkRoleUserOfLst(deptId, form.getLeaderReviewId(), lstTP)) {
                    file.setLeaderReviewId(form.getLeaderReviewId());
                    file.setLeaderReviewName(form.getLeaderReviewName());
                    file.setLeaderApproveId(null);
                    file.setLeaderApproveName(null);
                } else {
                    file.setLeaderApproveId(form.getLeaderReviewId());
                    file.setLeaderApproveName(form.getLeaderReviewName());
                    file.setStatus(Constants.FILE_STATUS.REVIEWED_TO_ADD);
                    file.setDisplayStatus(getFileStatusName(Constants.FILE_STATUS.REVIEWED_TO_ADD));
                    isReview = true;
                }
                // Cap nhat process
                ProcessDAOHE pdhe = new ProcessDAOHE();

                Process newP = new Process();
                newP.setObjectId(form.getFileId());
                newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                newP.setSendDate(dateNow);
                newP.setSendGroup(deptName);
                newP.setSendGroupId(deptId);
                newP.setSendUserId(userId);
                newP.setSendUser(userName);
                newP.setProcessStatus(file.getStatus()); // De xu ly
                newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                newP.setStatus(Constants.FILE_STATUS.NEW_CREATE); // Moi den chua xu ly
                newP.setIsActive(Constants.ACTIVE_STATUS.ACTIVE);
                newP.setReceiveDate(dateNow);
                newP.setReceiveUser(form.getLeaderReviewName());
                newP.setReceiveUserId(form.getLeaderReviewId());
                if (isReview) {
                    Users leaderApprove = udaohe.findById(file.getLeaderApproveId());
                    newP.setReceiveGroup(leaderApprove.getDeptName());
                    newP.setReceiveGroupId(leaderApprove.getDeptId());
                } else {
                    newP.setReceiveGroup(deptName);
                    newP.setReceiveGroupId(deptId);
                }
                getSession().save(newP);

                Process p = pdhe.getProcessByAction(form.getFileId(),
                        Constants.Status.ACTIVE,
                        Constants.OBJECT_TYPE.FILES,
                        processStatus,
                        Constants.FILE_STATUS.NEW_CREATE);
                p.setStatus(file.getStatus());
                p.setLastestComment(form.getLeaderStaffRequest());
                getSession().update(p);

                update(file);
            }
        } catch (Exception en) {
            log.error(en.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    public Files saveFilesWS(FilesForm createForm) {
        Files bo = null;
        Files boRollBack = null;
        Long filesId = createForm.getFileId();
        Boolean isCreateNew = true;
        Long status = 0L;
        Long announcementId = null;
        Long detailProductId = null;
        Long reIssueFormId = null;
        Long testRegistrationId = null;
        Long productTypeIdOld = null;
        if (createForm.getStatus() != null) {
            status = createForm.getStatus();
        }
        if (filesId != null) {
            String hql = "select dt.productType from DetailProduct dt "
                    + "where "
                    + "dt.detailProductId = (select f.detailProductId from Files f where f.fileId =?)";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, filesId);
            List<Long> lstProductType = query.list();
            if (lstProductType.size() > 0) {
                productTypeIdOld = lstProductType.get(0);
            }
        }
        if (filesId == null) {//la them moi            
            bo = createForm.convertToEntity();
        } else {//la sua
            isCreateNew = false;
            boRollBack = findById(filesId);
            bo = findById(filesId);

            if (status.equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)
                    && bo.getHaveTemp() != null
                    && bo.getHaveTemp().equals(1l)) {
                FilesForm cloneForm = getNewCloneFiles(filesId);
                cloneForm.setVersion(getCountVersion(bo.getFileId()));
                bo.setVersion(cloneForm.getVersion());//update version moi nhat cua ho so
                bo.setHaveTemp(null);
                saveFiles(cloneForm);
                //update toan bo process_comment cua lan tham dinh truoc thanh
                ProcessCommentDAOHE pcdhe = new ProcessCommentDAOHE();
                int r = pcdhe.updateVersion(filesId, cloneForm.getVersion());
            }
            bo = createForm.updateToEntity(bo);
        }

        //*Luu thong tin cac form chinh cua ho so
        if (createForm.getAnnouncement() != null) {
            Announcement ann = createForm.getAnnouncement().convertToEntity();
            ann.setIsTemp(0L);
            if (ann.getAnnouncementId() != null) {
                session.merge(ann);
            } else {
                session.save(ann);
            }
            announcementId = ann.getAnnouncementId();
        }

        if (createForm.getDetailProduct() != null) {
            DetailProduct detail = createForm.getDetailProduct().convertToEntity();
            detail.setIsTemp(0L);
            if (detail.getDetailProductId() != null) {
                session.merge(detail);
            } else {
                session.save(detail);
            }
            detailProductId = detail.getDetailProductId();
        }

        if (createForm.getReIssueForm() != null) {
            ReIssueForm reissue = createForm.getReIssueForm().convertToEntity();
            if (reissue.getReIssueFormId() != null) {
                session.merge(reissue);
            } else {
                session.save(reissue);
            }
            reIssueFormId = reissue.getReIssueFormId();
        }

        if (createForm.getTestRegistration() != null) {
            TestRegistration testReg = createForm.getTestRegistration().convertToEntity();
            if (testReg.getTestRegistrationId() != null) {
                getSession().merge(testReg);
            } else {
                getSession().save(testReg);
            }
            testRegistrationId = testReg.getTestRegistrationId();
        }
        bo.setAnnouncementId(announcementId);
        bo.setDetailProductId(detailProductId);
        bo.setReIssueFormId(reIssueFormId);
        bo.setTestRegistrationId(testRegistrationId);
        bo.setDisplayStatus(getFileStatusName(bo.getStatus()));
        if (bo.getFileId() != null) {
            //khi sua xoa toan bo chu ki CA
            bo.setStaffRequest("");
            bo.setLeaderRequest("");
            bo.setLeaderStaffRequest("");
            bo.setContentSigned("");
            bo.setUserSigned("");
            getSession().update(bo);
        } else {
            //update 15092015 binhnt cap nhat lay ma ho so
            if (createForm.getIsTemp() != null
                    && createForm.getIsTemp().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
            } else {
                bo.setFileCode(getNewFileCode(createForm.getFileType()));
            }
            getSession().save(bo);
        }

        filesId = bo.getFileId();

        saveMainlytarget(createForm.getLstMainlyTarget(), filesId);
        saveProductTarget(createForm.getLstBioTarget(), filesId, Constants.PRODUCT_TARGET_TYPE.BIO);
        saveProductTarget(createForm.getLstHeavyMetal(), filesId, Constants.PRODUCT_TARGET_TYPE.HEAVY_METAL);
        saveProductTarget(createForm.getLstChemical(), filesId, Constants.PRODUCT_TARGET_TYPE.CHEMICAL);
        saveAttachs(createForm.getLstAttachs(), filesId, createForm.getLstAttachLabel());
        saveQualityPlan(createForm.getLstQualityControl(), filesId);
        saveProductInFile(createForm.getLstProductInFile(), filesId);// Luu thong tin danh sach san pham nhap khau cho khach san 4 sao        

        try {//lưu phí thẩm định hồ sơ
            ProcedureDAOHE pdheCheck = new ProcedureDAOHE();
            Procedure pro = pdheCheck.getProcedureTypeFee(createForm.getFileType());
            if (pro != null
                    && (pro.getTypeFee() == 2 || pro.getTypeFee() == 3)
                    && !pro.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR)
                    && !pro.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05)) {
                if (!saveFeeForWS(filesId, createForm.getFileType(), null, pro, productTypeIdOld)) {
                    return null;
                }
            } //Sua doi sua cong bo. Tao mot ban ghi trong FeePaymentInfo de VanThu nhin thay hoso cua loai nay
            else if (pro != null
                    && pro.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05)) {
                if (!saveFeeChangesAfterAnnounced(filesId, createForm.getFileType())) {
                    return null;
                }
            } else if (pro != null
                    && pro.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR)) {
                if (!saveFee4StarForWS(filesId, createForm.getFileType())) {
                    return null;
                }
            } else if (pro != null
                    && pro.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_TL01)
                    && pro.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_TL03)
                    && pro.getDescription().equals(Constants.FILE_DESCRIPTION.CONFIRMTL)) {
                if (!saveFeeTLForWS(filesId, createForm.getFileType(),
                        createForm.getDetailProduct().getProductType(), pro, productTypeIdOld)) {
                    return null;
                }
            } else if (createForm.getDetailProduct() != null
                    && createForm.getDetailProduct().getProductType() != null) {
                if (!saveFeeForWS(filesId, createForm.getFileType(),
                        createForm.getDetailProduct().getProductType(), pro, productTypeIdOld)) {
                    return null;
                }
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
            if (isCreateNew) {
                bo.setIsActive(Constants.Status.INACTIVE);
                getSession().update(bo);
            } else {
                getSession().update(boRollBack);

            }
            return null;
        }

        if (createForm.getIsTemp() == null
                || (createForm.getIsTemp() != null
                && !createForm.getIsTemp().equals(1l))) {
            saveFileForSearch(filesId);
        }
        getSession().getTransaction().commit();
//        session.flush();
        return bo;
    }

    private Boolean saveFeeForWS(Long fileId,
            Long fileType,
            Long productType,
            Procedure pro,
            Long productTypeIdOld) {
        if (fileId != null) {
            Date dateNow = null;
            try {
                dateNow = getSysdate();
            } catch (Exception ex) {
                log.error(ex.getMessage());
                return false;
            }

            String hql = "select fpif"
                    + " from FeePaymentInfo fpif"
                    + " where"
                    + " fpif.fileId =:fileId"
                    + " and fpif.isActive = 1";
            Query query = getSession().createQuery(hql);
            query.setParameter("fileId", fileId);
            List<FeePaymentInfo> FeePaymentInfo = query.list();
            // truong hop sao chep va luu tam productType co the = null, vi the phai set = -1
            if (FeePaymentInfo.isEmpty()) {
                FeeDAOHE fdhe = new FeeDAOHE();
                List<FeeProcedure> feenew = fdhe.findAllFeeByProcedureId(fileType);
                // check le phi cap so theo loai ho so
                if (feenew != null && feenew.size() > 0) {
                    FeePaymentInfo fpif = new FeePaymentInfo();
                    for (int i = 0; i < feenew.size(); i++) {
                        fpif = new FeePaymentInfo();
                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(0l);
                        fpif.setFileId(fileId);
                        fpif.setIsActive(1l);
                        fpif.setFeeId(feenew.get(i).getFeeId());
                        Fee feeTemp = (Fee) findById(Fee.class, "feeId", feenew.get(i).getFeeId());
                        fpif.setCost(feeTemp.getPrice());
                        getSession().save(fpif);
                    }
                }
                // hieptq update 280515 set phi ho so cap lai
                if (productType == null) {
                    if (pro != null && pro.getTypeFee() == 2l) {
                        FeePaymentInfo fpif = new FeePaymentInfo();
                        Fee findfee1 = fdhe.findFeeByCode("CLT");
                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(0l);
                        fpif.setFileId(fileId);
                        if (findfee1 != null) {
                            fpif.setFeeId(findfee1.getFeeId());
                            fpif.setCost(findfee1.getPrice());
                            fpif.setCostCheck(findfee1.getPrice());
                            fpif.setFeeIdOld(findfee1.getFeeId());
                        } else {
                            return false;
                        }
                        fpif.setIsActive(1l);
                        getSession().save(fpif);
                        return true;
                    } else if (pro != null && pro.getTypeFee() == 3l) {
                        FeePaymentInfo fpif = new FeePaymentInfo();
                        Fee findfee1 = fdhe.findFeeByCode("CLCN");
                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(0l);
                        fpif.setFileId(fileId);
                        if (findfee1 != null) {
                            fpif.setFeeId(findfee1.getFeeId());
                            fpif.setCost(findfee1.getPrice());
                            fpif.setCostCheck(findfee1.getPrice());
                            fpif.setFeeIdOld(findfee1.getFeeId());
                        } else {
                            return false;
                        }
                        fpif.setIsActive(1l);
                        getSession().save(fpif);
                        return true;
                    } else {
                        return false;
                    }
                }
                //check loai thuc pham (thuc pham dac biet)
                CategoryDAOHE ctdhe = new CategoryDAOHE();
                Category cate = ctdhe.findCategoryByTypeAndCode("SP", "TPCN");
                List<Category> cate1 = ctdhe.findCategoryByTypeAndCodeNew("SP", "DBT");
                int check = 0;
                for (int i = 0; i < cate1.size(); i++) {
                    if (productType.equals(cate1.get(i).getCategoryId())) {
                        check = 1;
                        break;
                    }
                }

                if (check == 1) {
                    FeePaymentInfo fpif = new FeePaymentInfo();
                    Fee findfee1 = fdhe.findFeeByCode("TPDB");
                    fpif.setCreateDate(dateNow);
                    fpif.setStatus(0l);
                    fpif.setFileId(fileId);
                    if (findfee1 != null) {
                        fpif.setFeeId(findfee1.getFeeId());
                        fpif.setCost(findfee1.getPrice());
                        fpif.setCostCheck(findfee1.getPrice());
                        fpif.setFeeIdOld(findfee1.getFeeId());
                    } else {
                        return false;
                    }
                    fpif.setIsActive(1l);
                    getSession().save(fpif);
                } else {
                    // thuc pham chuc nang
                    FeePaymentInfo fpif = new FeePaymentInfo();
                    if (productType.equals(cate.getCategoryId())) {
                        Fee findfee2 = fdhe.findFeeByCode("TPCN");

                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(0l);
                        fpif.setFileId(fileId);
                        if (findfee2 != null) {
                            fpif.setFeeId(findfee2.getFeeId());
                            fpif.setCost(findfee2.getPrice());
                            fpif.setCostCheck(findfee2.getPrice());
                            fpif.setFeeIdOld(findfee2.getFeeId());
                        } else {
                            return false;
                        }
                        fpif.setIsActive(1l);
                        getSession().save(fpif);
                    } else {
                        Fee findfee3 = fdhe.findFeeByCode("TPK");
                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(0l);
                        fpif.setFileId(fileId);
                        if (findfee3 != null) {
                            fpif.setFeeId(findfee3.getFeeId());
                            fpif.setCost(findfee3.getPrice());
                            fpif.setCostCheck(findfee3.getPrice());
                            fpif.setFeeIdOld(findfee3.getFeeId());
                        } else {
                            return false;
                        }
                        fpif.setIsActive(1l);
                        getSession().save(fpif);
                    }
                }
                // }
            }
            // sua nhom san pham va chuyen loai ho so - map lai phi
            if ((productTypeIdOld != null) && (!productTypeIdOld.equals(productType))) {
                // hieptq update 310115
                // tim lai feeId cu 
                CategoryDAOHE ctdhe = new CategoryDAOHE();
                FeeDAOHE fdhe = new FeeDAOHE();
                Category cate = ctdhe.findCategoryByTypeAndCode("SP", "TPCN");
                List<Category> cate1 = ctdhe.findCategoryByTypeAndCodeNew("SP", "DBT");
                Long feeIdOld, feeIdNew;
                int check = 0, check1 = 0;
                FeePaymentInfo fpifOld = null;
                for (int i = 0; i < cate1.size(); i++) {
                    if (productTypeIdOld.equals(cate1.get(i).getCategoryId())) {
                        check = 1;
                        break;
                    }
                }
                if (check == 1) {
                    Fee findfee1 = fdhe.findFeeByCode("TPDB");
                    feeIdOld = findfee1.getFeeId();
                } else // thuc pham chuc nang
                 if (productTypeIdOld.equals(cate.getCategoryId())) {
                        Fee findfee2 = fdhe.findFeeByCode("TPCN");
                        feeIdOld = findfee2.getFeeId();
                    } else {
                        Fee findfee3 = fdhe.findFeeByCode("TPK");
                        feeIdOld = findfee3.getFeeId();
                    }

                fpifOld = fdhe.findFeePaymentInfoFileIdFeeIdIsActive(fileId, feeIdOld, 1l);

                // lay feeid moi
                for (int i = 0; i < cate1.size(); i++) {
                    if (productType.equals(cate1.get(i).getCategoryId())) {
                        check1 = 1;
                        break;
                    }
                }
                Long costNew = 0l;
                if (check1 == 1) {
                    Fee findfee1 = fdhe.findFeeByCode("TPDB");
                    feeIdNew = findfee1.getFeeId();
                    costNew = findfee1.getPrice();
                } else // thuc pham chuc nang
                 if (productType.equals(cate.getCategoryId())) {
                        Fee findfee2 = fdhe.findFeeByCode("TPCN");
                        feeIdNew = findfee2.getFeeId();
                        costNew = findfee2.getPrice();
                    } else {
                        Fee findfee3 = fdhe.findFeeByCode("TPK");
                        feeIdNew = findfee3.getFeeId();
                        costNew = findfee3.getPrice();
                    }
                FilesDAOHE filesdhe = new FilesDAOHE();
                Files filesnew = filesdhe.findById(fileId);
                // check gia cu va gia moi
                if (fpifOld.getCostCheck().equals(costNew)
                        || fpifOld.getCostCheck() > costNew) {
                    if (filesnew.getStatus() == 0l) {
                        fpifOld.setCost(fpifOld.getCostCheck());
                        fpifOld.setFeeId(fpifOld.getFeeIdOld());
                        getSession().update(fpifOld);
                    } else {
                        fpifOld.setCost(fpifOld.getCostCheck());
                        fpifOld.setFeeId(fpifOld.getFeeIdOld());
                        fpifOld.setStatus(1l);
                        filesnew.setIsFee(1l);
                        getSession().update(filesnew);
                        getSession().update(fpifOld);
                    }
                    return true;
                } else if (filesnew.getStatus() == 0l) {
                    fpifOld.setCost(costNew);
                    fpifOld.setFeeId(feeIdNew);
                    getSession().update(fpifOld);
                } else {
                    fpifOld.setCost(costNew);
                    fpifOld.setFeeId(feeIdNew);
                    fpifOld.setStatus(0l);
                    //Hiepvv 0803 Khong tinh phi SDBS sau cong bo
                    ProcedureDAOHE pHE = new ProcedureDAOHE();
                    Procedure pdu = new Procedure();
                    pdu = pHE.findById(filesnew.getFileType());
                    if (pdu != null
                            && pdu.getDescription() != null
                            && pdu.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05)) {
                        filesnew.setIsFee(1l);
                    } else {
                        filesnew.setIsFee(0l);
                    }
                    getSession().update(filesnew);
                    getSession().update(fpifOld);
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private Boolean saveFeeTLForWS(Long fileId, Long fileType, Long productType, Procedure pro, Long productTypeIdOld) {
        if (fileId != null) {
            Date dateNow = null;
            try {
                dateNow = getSysdate();
            } catch (Exception ex) {
                log.error(ex.getMessage());
                return false;
            }

            String hql = "select fpif from FeePaymentInfo fpif where fpif.fileId =:fileId and fpif.isActive = 1";
            Query query = getSession().createQuery(hql);
            query.setParameter("fileId", fileId);
            List<FeePaymentInfo> FeePaymentInfo = query.list();
            // truong hop sao chep va luu tam productType co the = null, vi the phai set = -1

            if (FeePaymentInfo.isEmpty()) {
                FeeDAOHE fdhe = new FeeDAOHE();
                List<FeeProcedure> feenew = fdhe.findAllFeeByProcedureId(fileType);
                // check le phi cap so theo loai ho so
                if (feenew != null && feenew.size() > 0) {
                    FeePaymentInfo fpif = new FeePaymentInfo();
                    for (int i = 0; i < feenew.size(); i++) {
                        fpif = new FeePaymentInfo();
                        fpif.setCreateDate(dateNow);
                        fpif.setFeePaymentTypeId(3l);
                        fpif.setPaymentConfirm("admin");
                        fpif.setPaymentDate(dateNow);
                        fpif.setDateConfirm(dateNow);
                        fpif.setPaymentPerson("admin");
                        fpif.setPaymentInfo("hồ sơ thuốc là không đóng phí");
                        fpif.setStatus(1l);
                        fpif.setFileId(fileId);
                        fpif.setIsActive(1l);
                        fpif.setFeeId(feenew.get(i).getFeeId());
                        Fee feeTemp = (Fee) findById(Fee.class, "feeId", feenew.get(i).getFeeId());
                        fpif.setCost(feeTemp.getPrice());
                        getSession().save(fpif);
                    }
                }

                // hieptq update 280515 set phi ho so cap lai
                if (productType != null) {
                    if (pro != null && pro.getTypeFee() == 7l) {
                        FeePaymentInfo fpif = new FeePaymentInfo();
                        Fee findfee1 = fdhe.findFeeByCode("TL");
                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(1l);
                        fpif.setFileId(fileId);
                        fpif.setDateConfirm(dateNow);
                        fpif.setFeePaymentTypeId(3l);
                        fpif.setPaymentConfirm("admin");
                        fpif.setPaymentDate(dateNow);
                        fpif.setPaymentPerson("admin");
                        fpif.setPaymentInfo("hồ sơ thuốc là không đóng phí");
                        if (findfee1 != null) {
                            fpif.setFeeId(findfee1.getFeeId());
                            fpif.setCost(findfee1.getPrice());
                            fpif.setCostCheck(findfee1.getPrice());
                            fpif.setFeeIdOld(findfee1.getFeeId());
                        } else {
                            return false;
                        }
                        fpif.setIsActive(1l);
                        getSession().save(fpif);
                    }
                }
                FilesDAOHE filesdhe = new FilesDAOHE();
                Files f = filesdhe.findById(fileId);
                f.setIsFee(1l);
                getSession().save(f);
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private Boolean saveFee4StarForWS(Long fileId, Long fileType) {
        if (fileId != null) {
            Date dateNow = getSysdate();
            String hql = "select fpif from FeePaymentInfo fpif "
                    + "where fpif.fileId =:fileId "
                    + "and fpif.isActive = 1";
            Query query = getSession().createQuery(hql);
            query.setParameter("fileId", fileId);
            List<FeePaymentInfo> lstFeePaymentInfo = query.list();
            // truong hop sao chep va luu tam productType co the = null, vi the phai set = -1
            if (lstFeePaymentInfo.isEmpty()) {
                FeeDAOHE fdhe = new FeeDAOHE();
                List<FeeProcedure> lstFee = fdhe.findAllFeeByProcedureId(fileType);
                //check le phi cap so theo loai ho so
                if (lstFee != null && lstFee.size() > 0) {
                    FeePaymentInfo fpif;
                    for (int i = 0; i < lstFee.size(); i++) {
                        fpif = new FeePaymentInfo();
                        //Thong tin co ban, status=1 = da dong phi
                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(0l);
                        fpif.setFileId(fileId);
                        fpif.setIsActive(1l);
                        fpif.setFeeId(lstFee.get(i).getFeeId());
                        Fee feeTemp = (Fee) findById(Fee.class, "feeId", lstFee.get(i).getFeeId());
                        if (feeTemp.getFeeCode().equals(Constants.FEE_TYPE.KS4SLP)) {
                            fpif.setCost(feeTemp.getPrice());
                        } else if (feeTemp.getFeeCode().equals(Constants.FEE_TYPE.KS4STD)) {
                            String sql = "select count(*) from ProductInFile "
                                    + "where file_ID = :fileId";
                            Query qry1 = getSession().createQuery(sql);
                            qry1.setParameter("fileId", fileId);
                            int count = (int) (long) (Long) qry1.uniqueResult();
                            fpif.setCost(feeTemp.getPrice() * count);
                        } else if (feeTemp.getPrice() != null) {
                            fpif.setCost(feeTemp.getPrice());
                        } else {
                            fpif.setCost(0L);
                        }
                        getSession().save(fpif);
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private Boolean saveFeeChangesAfterAnnouncedForWS(Long fileId, Long fileType) {
        if (fileId != null) {
            Date dateNow = getSysdate();
            String hql = "select fpif from FeePaymentInfo fpif"
                    + " where fpif.fileId =:fileId"
                    + " and fpif.isActive = 1";
            Query query = getSession().createQuery(hql);
            query.setParameter("fileId", fileId);
            List<FeePaymentInfo> FeePaymentInfo = query.list();
            // truong hop sao chep va luu tam productType co the = null, vi the phai set = -1
            if (FeePaymentInfo.isEmpty()) {
                FeeDAOHE fdhe = new FeeDAOHE();
                List<FeeProcedure> feenew = fdhe.findAllFeeByProcedureId(fileType);
                // check le phi cap so theo loai ho so
                if (feenew != null && feenew.size() > 0) {
                    FeePaymentInfo fpif = new FeePaymentInfo();
                    for (int i = 0; i < feenew.size(); i++) {
                        fpif = new FeePaymentInfo();
                        //Thong tin co ban, status=1 = da dong phi
                        fpif.setCreateDate(dateNow);
                        fpif.setStatus(1l);
                        fpif.setFileId(fileId);
                        fpif.setIsActive(1l);
                        fpif.setFeeId(feenew.get(i).getFeeId());
                        Fee feeTemp = (Fee) findById(Fee.class, "feeId", feenew.get(i).getFeeId());
                        fpif.setCost(feeTemp.getPrice());

                        //Thong tin fix cung cho du du lieu
                        fpif.setPaymentPerson("ATTP");
                        fpif.setPaymentDate(dateNow);
                        fpif.setFeePaymentTypeId(3L);
                        fpif.setBillPath("");
                        fpif.setPaymentConfirm("ATTP");
                        fpif.setDateConfirm(dateNow);
                        fpif.setCostCheck(0L);

                        getSession().save(fpif);
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
