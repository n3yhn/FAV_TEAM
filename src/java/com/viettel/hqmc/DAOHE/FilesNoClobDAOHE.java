/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.Constants;
import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.FilesNoClob;
import com.viettel.hqmc.BO.Procedure;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import java.util.Date;
import com.viettel.voffice.common.util.DateTimeUtils;
import org.hibernate.SQLQuery;

/**
 *
 * @author gpcp_dund1
 */
public class FilesNoClobDAOHE extends GenericDAOHibernate<FilesNoClob, Long> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FilesNoClobDAOHE.class);

    public FilesNoClobDAOHE() {
        super(FilesNoClob.class);
    }

    /**
     * Tra cuu ho so
     *
     * @return
     */
    public GridResult searchLookupFiles(FilesForm form, Long deptId, Long userId, String userType, int start, int count, String sortField, String sortCustom) {
        try {
            return createQueryLookupFiles(form, deptId, userId, userType, start, count, sortField, sortCustom);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new GridResult(0, null);
        }
    }

    public GridResult searchLookupReport(FilesForm form, Long deptId, Long userId, String userType, int start, int count, String sortField, String sortCustom) {
        try {
            return reportLookup(form, deptId, userId, userType, start, count, sortField, sortCustom);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new GridResult(0, null);
        }
    }

    /**
     * Tổng số hồ sơ chờ tiếp nhận
     *
     * @return
     */
    public int getCountSelectNewHomePage() {
        try {

            String hql = "select count(distinct f.fileId) from FilesNoClob f, Fee fe, FeePaymentInfo fpi"
                    + " where f.fileId = fpi.fileId and (f.isTemp is null or f.isTemp=0) and f.isActive=1"
                    + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                    + " and fpi.isActive=1"
                    + " and fpi.status=1"
                    + " and f.userSigned is not null"
                    + " and f.status= " + Constants.FILE_STATUS.NEW;

            Query query = getSession().createQuery(hql);
            int total = Integer.parseInt(query.list().get(0).toString());
            return total;
        } catch (NumberFormatException | HibernateException e) {
            log.error(e);
            return 0;
        }
    }

    /**
     * chua tiep nhan
     *
     * @return
     */
    public int getCountSelectNewSdbs() {
        try {
            String hql = "select count(distinct f.fileId) from FilesNoClob f "
                    + "where f.isActive = 1 "
                    + "and (f.isTemp is null or f.isTemp = 0) "
                    + "and (f.status = " + Constants.FILE_STATUS.NEW_TO_ADD + ") ";
            Query query = getSession().createQuery(hql);
            int total = Integer.parseInt(query.list().get(0).toString());
            return total;
        } catch (NumberFormatException | HibernateException e) {
            log.error(e);
            return 0;
        }
    }

    /**
     * ho so dang cho bo sung
     *
     * @return
     */
    public int getCountSelectEVALUATED_TO_ADDHomePage() {
        try {
            String hql = "select count(f.fileId) from FilesNoClob f "
                    + "where (f.status = " + Constants.FILE_STATUS.EVALUATED_TO_ADD + ") "
                    + "and f.isActive = 1 "
                    + "and (f.isTemp is null or f.isTemp = 0)";
            Query query = getSession().createQuery(hql);
            int total = Integer.parseInt(query.list().get(0).toString());
            return total;
        } catch (NumberFormatException | HibernateException e) {
            log.error(e);
            return 0;
        }
    }

    /**
     * ho so da tra lai
     *
     * @return
     */
    public int getCountSelectGIVE_BACKHomePage() {
        try {
            /*
             binhnt update 150211 tối ưu truy vấn
             */
            List lstParam = new ArrayList();
            String hql = "select count(f.fileId) from FilesNoClob f "
                    + " where "
                    + " f.status in (?,?,?,?,?,?)"
                    + " and f.isActive = 1 "
                    + " and (f.isTemp is null or f.isTemp = 0)";
            lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
            lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
            lstParam.add(Constants.FILE_STATUS.COMPARED);
            lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON);
            lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
            lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);
            Query query = getSession().createQuery(hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
            }
            int total = Integer.parseInt(query.list().get(0).toString());
            return total;
        } catch (NumberFormatException | HibernateException e) {
            log.error(e);
            return 0;
        }
    }

    //tong so ho so dang cho xac nhan phi tham xem
    public int getCountSelectRECEIVE_PAYHomePage() {
        try {
            String sql = "select count(distinct fpi.PAYMENT_INFO_ID)"
                    + " from files f"
                    + " inner join fee_payment_info fpi"
                    + " on f.file_id = fpi.file_id"
                    + " where fpi.fee_id in (select f.fee_id from fee f where f.fee_type = 2 and f.is_active = 1 )"
                    + " and fpi.is_active = 1"
                    + " and f.is_active = 1"
                    + " and (f.user_Signed is not null or f.status = 18)"
                    + " and f.status <> 0"
                    + " and fpi.status > 2 ";
//            String sql = "select count(distinct fpi.PAYMENT_INFO_ID) from Files f "
//                    + "inner join "
//                    + "fee_payment_info fpi on f.file_id = fpi.file_id "
//                    + "inner join "
//                    + "fee fe on fe.fee_id = fpi.fee_id and fe.fee_type = 2 and fe.is_active = 1 "
//                    + "where fpi.is_active = 1 and fpi.status <> 1 "
//                    + "and f.is_active = 1 "
//                    + "and (f.is_temp is null or f.is_temp = 0) "
//                    + "and (f.user_Signed is not null or f.status = 18) "
//                    + "and f.status <> 0 ";
            SQLQuery countQuery = (SQLQuery) getSession().createSQLQuery(sql);
            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            return total;
        } catch (NumberFormatException | HibernateException e) {
            log.error(e);
            return 0;
        }
    }

    //tong so ho so da phe duyet cho nop le phi
    public int getCountSelectAPPROVE_PAYHomePage() {
        try {
            String sql = "select count(distinct fpi.PAYMENT_INFO_ID) from Files f "
                    + "inner join "
                    + "fee_payment_info fpi on f.file_id = fpi.file_id "
                    + "inner join "
                    + "fee fe on fe.fee_id = fpi.fee_id and fe.fee_type = 1 and fe.is_active = 1 "
                    + "where fpi.is_active = 1 "
                    + "and f.is_active = 1 "
                    + "and (f.is_temp is null or f.is_temp = 0) "
                    + "and f.status = " + Constants.FILE_STATUS.APPROVED
                    + "and fpi.status > 2 ";
            SQLQuery countQuery = (SQLQuery) getSession().createSQLQuery(sql);
            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            return total;
        } catch (NumberFormatException | HibernateException e) {
            log.error(e);
            return 0;
        }
    }

    /**
     * ho so cho tra
     *
     * @return
     */
    public int getCountSelectWaitGiveBackHomePage() {
        try {
            List lstParam = new ArrayList();
            /*
             binhnt update 150211 tối ưu truy vấn
             */
            String sql = "select count(f.file_id) from Files f "
                    + "where f.is_active = 1"
                    + " and (f.is_temp is null or f.is_temp = 0) "
                    + " and f.status in (?,?,?,?,?)";
            lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
            lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON);
            lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);
            lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
            lstParam.add(Constants.FILE_STATUS.COMPARED);
            SQLQuery countQuery = (SQLQuery) getSession().createSQLQuery(sql);
            for (int i = 0; i < lstParam.size(); i++) {
                countQuery.setParameter(i, lstParam.get(i));
            }
            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            return total;
        } catch (NumberFormatException | HibernateException e) {
            log.error(e);
            return 0;
        }
    }

    /**
     * ho so cho tra
     *
     * @return
     */
    public int getCountSelectGiveBackHomePage() {
        try {
            String sql = "select count(f.file_id) from Files f "
                    + "where f.is_active = 1 "
                    + "and (f.is_temp is null or f.is_temp = 0) "
                    + "and (f.status = " + Constants.FILE_STATUS.GIVE_BACK
                    + ") ";
            SQLQuery countQuery = (SQLQuery) getSession().createSQLQuery(sql);
            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            return total;
        } catch (NumberFormatException | HibernateException e) {
            log.error(e);
            return 0;
        }
    }

    /**
     * ho so dang xu ly
     *
     * @return
     */
    public int getCountFileIsProcessHomePage() {
        try {//Bo sung trang thai 8, 14, 17, 23
            /*
             binhnt update 150211 tối ưu truy vấn
             */
            List lstParam = new ArrayList();
            String hql = "select count(f.fileId) from FilesNoClob f "
                    + "where"
                    + " f.isActive = 1 and (f.isTemp is null or f.isTemp = 0)"
                    + " and f.status in (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            lstParam.add(Constants.FILE_STATUS.ASSIGNED);
            lstParam.add(Constants.FILE_STATUS.EVALUATED);
            lstParam.add(Constants.FILE_STATUS.REVIEWED);
            lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
            lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_REVIEW);
            lstParam.add(Constants.FILE_STATUS.RECEIVED);
            lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
            lstParam.add(Constants.FILE_STATUS.REVIEW_TO_ADD);
            lstParam.add(Constants.FILE_STATUS.APPROVE_TO_ADD);
            lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD);
            lstParam.add(Constants.FILE_STATUS.REVIEWED_TO_ADD);
            lstParam.add(Constants.FILE_STATUS.REVIEW_TO_BOSS);
            lstParam.add(Constants.FILE_STATUS.APPROVE_TO_LEADER);
            Query query = getSession().createQuery(hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
            }
            int total = Integer.parseInt(query.list().get(0).toString());
            return total;
        } catch (NumberFormatException | HibernateException e) {
            log.error(e);
            return 0;
        }
    }

    /**
     * Tao cau truy van Lookup dung chung
     *
     * @param form
     * @param deptId
     * @param userId
     * @param userType
     * @param count
     * @param sortField
     * @param start
     * @param sortCustom
     * @return
     */
    public GridResult createQueryLookupFiles(FilesForm form, Long deptId, Long userId, String userType, int start, int count, String sortField, String sortCustom) {
        List lstParam = new ArrayList();
//        String hql = " from FilesNoClob f, Process p, DetailProduct d, Business b, Category c ";
        String hql = " from FilesNoClob f, Process p, DetailProduct d, Business b ";
        String condition = "";
        condition += " and f.fileId = p.objectId";
        condition += " and  f.detailProductId = d.detailProductId";
        // hieptq update 251214
        //condition += " and  f.productTypeId = c.categoryId";
        condition += " and f.deptId = b.businessId";
        condition += " and f.isActive = 1"
                + " and f.fileId = p.objectId"
                + "  and (f.isTemp = null or f.isTemp = 0 )";
        if (userType.equals(Constants.ROLES.LEAD_OFFICE_ROLE)
                || userType.equals(Constants.ROLES.STAFF_ROLE)
                || userType.equals(Constants.ROLES.LEAD_UNIT)) {
        }
//        if (userType.equals(Constants.ROLES.CLERICAL_ROLE)) {
//            if (userId != null) {
//                condition += " and p.sendUserId = ? ";
//                lstParam.add(userId);
//            }
//        }
        if (form != null) {
            if (userType.equals(Constants.ROLES.STAFF_ROLE) && form.getSearchType() != null) {//vcv
                switch (Integer.parseInt(form.getSearchType().toString())) {
                    case 42:
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED);
                        condition += " and f.staffProcess =?";
                        lstParam.add(userId);
                        break;
                    case 47:
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                        condition += " and f.staffProcess =?";
                        lstParam.add(userId);
                        break;
                    case -26://Hồ sơ đã xem xét cv sđbs chờ phê duyệt công văn
                        lstParam.clear();
                        hql = "from FilesNoClob f, Process p";
                        condition = " AND f.isActive = 1"
                                + " AND (f.isTemp = null or f.isTemp = 0 )"
                                + " AND f.fileId = p.objectId"
                                + " AND f.status = ?"
                                + " AND p.receiveGroupId = ?"
                                + " AND p.receiveUserId = ?";
                        lstParam.add(Constants.FILE_STATUS.REVIEW_TO_ADD);
                        lstParam.add(deptId);
                        lstParam.add(userId);
                        break;
                    case -20://Ho so da gui thong bao sdbs cho doanh nghiep
                        lstParam.clear();
                        hql = "from FilesNoClob f";
                        condition = " AND f.isActive=1";
                        condition += " AND (f.status = ?)";
                        condition += " AND f.staffProcess=?";
                        condition += " AND (f.isTemp = null or f.isTemp = 0 ) ";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED_TO_ADD);
                        lstParam.add(userId);
                        break;
                    case 33://Hồ sơ chờ thẩm định SĐBS CV
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and f.fileId = p.objectId  and (f.isTemp = null or f.isTemp = 0 )";
                        condition += " AND p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        if (userId != null) {
                            //condition += " and p.receiveUserId=?";//141217u binhnt53
                            condition += " and f.staffProcess=?";//141217u binhnt53 cap nhat ho so chuyen vien xu ly nhin thay ho so sdbs cua minh
                            lstParam.add(userId);
                        }
                        condition += " AND f.status = ?";
                        lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD);
                        break;
                    case 19://Hồ sơ lãnh đạo cục yêu cầu bổ sung CV
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0 )";
                        condition += " AND p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                        condition += " AND f.status = ?";
                        lstParam.add(Constants.FILE_STATUS.REVIEWED_TO_ADD);
                        break;
                    case 5://Hồ sơ cần thông báo đối chiếu
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " and f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and f.fileId = p.objectId  and (f.isTemp = null or f.isTemp = 0 )";
                        condition += " AND p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                        condition += " AND f.status = ?";
                        lstParam.add(Constants.FILE_STATUS.REVIEWED);
                        break;
                    case 50:
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " and f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and f.fileId = p.objectId  and (f.isTemp = null or f.isTemp = 0 )";
                        condition += " AND (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                        condition += " AND p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                        break;
                    case 417:
                        lstParam.clear();
                        hql = " from FilesNoClob f";
                        condition = " AND f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND (f.status =?)";
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        if (userId != null) {
                            condition += " and f.staffProcess=?";
                            lstParam.add(userId);
                        }
                        condition += " AND f.isSignPdf <> 2";
                        break;
                    case 422:
                        lstParam.clear();
                        hql = " from FilesNoClob f";
                        condition = " AND f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                        if (userId != null) {
                            condition += " and f.staffProcess=?";
                            lstParam.add(userId);
                        }
                        condition += " AND f.isSignPdf = 2";
                        break;
                    case 423:
                        lstParam.clear();
                        hql = " from FilesNoClob f";
                        condition = " AND f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND (f.status <> ?)";
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                        if (userId != null) {
                            condition += " and f.staffProcess=?";
                            lstParam.add(userId);
                        }
                        condition += " AND f.isSignPdf = 2";
                        break;
                    case 447:
                        lstParam.clear();
                        hql = " from FilesNoClob f";
                        condition = " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND f.staffProcess=?";
                        lstParam.add(userId);
                        condition += " AND (f.status =?)";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED);
                        break;
                    case 448:
                        lstParam.clear();
                        hql = " from FilesNoClob f";
                        condition = " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND f.staffProcess=?";
                        lstParam.add(userId);
                        condition += " AND (f.status =?)";
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                        break;
                    case 20://Hồ sơ chờ chuyên viên trong tổ thẩm định
                        lstParam.clear();
                        hql = "from FilesNoClob f, Process p";
                        condition = " AND f.isActive = 1";
                        condition += " AND (f.isTemp = null or f.isTemp = 0 )";
                        condition += " AND f.fileId = p.objectId";
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                        condition += " and p.processType=0";
                        condition += " and f.status = ?";
                        lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                        break;
                    case 21://Hồ sơ gửi phối hợp chưa cho ý kiến 21
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.isActive = 1 ";
                        condition += " AND (f.isTemp = null or f.isTemp = 0 ) ";
                        condition += " AND f.fileId = p.objectId ";
                        condition += " AND p.receiveGroupId = ? ";
                        lstParam.add(deptId);
                        if (userId != null) {
                            condition += " AND p.sendUserId = ? ";
                            lstParam.add(userId);
                        }
                        condition += " AND (f.status = ?) ";
                        condition += " AND p.processStatus = ?";
                        lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                        lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                        condition += " AND (p.processId in (select p.processId from Process p where p.processId not in (select distinct pc.processId from ProcessComment pc))) ";
                        condition += " AND (p.processType=0 or p.processType=4) ";
                        break;
                    case 22://Hồ sơ chuyên viên đã gửi thông báo sửa đổi bổ sung
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND p.receiveGroupId =?";
                        lstParam.add(deptId);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                        condition += " AND (f.status =?)";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED_TO_ADD);
                        break;
                    case 26:
                        lstParam.clear();
                        hql = "from FilesNoClob f, Process p";
                        condition = " AND f.isActive=1";
                        condition += " AND f.fileId = p.objectId";
                        condition += " AND f.status = ?";
                        condition += " AND p.receiveUserId=?";
                        condition += " AND p.processType=1";
                        condition += " AND (f.isTemp = null or f.isTemp = 0 ) ";
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                        lstParam.add(userId);
                        break;
                    case 27:
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.isActive = 1 ";
                        condition += " AND (f.isTemp = null or f.isTemp = 0 ) ";
                        condition += " AND f.fileId = p.objectId ";
                        condition += " AND p.receiveGroupId = ? ";
                        lstParam.add(deptId);
                        if (userId != null) {
                            condition += " AND p.sendUserId = ? ";
                            lstParam.add(userId);
                        }
                        condition += " AND (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED_TO_ADD);
                        condition += " AND sysdate - f.evaluateAddDate >= 54";
                        break;
                    case 28://Hồ sơ đã có ý kiến của tổ thẩm xét 28
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p, ProcessComment pc";
                        condition = " AND f.isActive = 1 ";
                        condition += " AND (f.isTemp = null or f.isTemp = 0 ) ";
                        condition += " AND f.fileId = p.objectId ";
                        condition += " AND pc.processId = p.processId";
                        condition += " AND (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                        if (userId != null) {
                            condition += " AND p.receiveGroupId = (select distinct p.receiveGroupId from Process p where p.receiveUserId = ?)";
                            lstParam.add(userId);
                        }
                        condition += " AND (p.processType=0 or p.processType=4)";
                        condition += " AND p.objectType = 30";
                        break;
                }
            }
            if (userType.equals(Constants.ROLES.CLERICAL_ROLE) && form.getSearchType() != null) {//vvt
                switch (Integer.parseInt(form.getSearchType().toString())) {
                    case -11:
                        /*tim kiem bao cao van thu xu ly ho so trong khoang thoi gian.
                         binhnt53 add 150211
                         */
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)";
                        if (form.getStatus() != null) {
                            condition += " AND p.status = ?";
                            lstParam.add(form.getStatus());
                        }
                        if (form.getSearchDateFrom() != null) {
                            condition += " and p.sendDate >= ?";
                            lstParam.add(form.getSearchDateFrom());
                        }
                        if (form.getSearchDateTo() != null) {
                            condition += " and p.sendDate <= ?";
                            lstParam.add(form.getSearchDateTo());
                        }
                        break;
                    case 1623://hồ sơ cần đối chiếu
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND (f.status =? )";
                        //lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                        lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
                        break;
                    case 22:
                        hql = " from FilesNoClob f, Process p";
                        lstParam.clear();
                        condition = " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0)";
                        condition += " and (f.status = ? or f.status = ? or f.status = ? or f.status = ? or f.status = ? or f.status = ?)";
//                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);
                        lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON);
                        lstParam.add(Constants.FILE_STATUS.COMPARED);
                        lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                        condition += " and p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        break;
                    case 15:
                        hql = " from FilesNoClob f, Process p";

                        lstParam.clear();
                        condition = " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0)"
                                + " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.COMPARED);
                        condition += " and p.receiveGroupId = ?";
                        condition += " and f.isFee = 1 and f.isSignPdf = 2";
                        lstParam.add(deptId);
                        break;
                    case 6:
                        hql = " from FilesNoClob f, Process p";

                        lstParam.clear();
                        condition = " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0)"
                                + " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        condition += " and p.receiveGroupId = ?";
                        condition += " and f.isFee = 1 and f.isSignPdf = 2";
                        condition += " and f.isDownload <> 1";//u150112 binhnt53
                        lstParam.add(deptId);
                        break;
                    case 20:
                        hql = " from FilesNoClob f, Process p";

                        lstParam.clear();
                        condition = " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0)"
                                + " and (f.status = ?)";
                        lstParam.add(form.getStatus());
                        condition += " and p.sendGroupId = ?";
                        lstParam.add(deptId);
                        break;
                    case -27:
                        hql = " from FilesNoClob f, Process p";

                        lstParam.clear();
                        condition = " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0)"
                                + " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.APPROVE_TO_ADD);
                        condition += " and p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        break;
                    case -6://hồ sơ cần kí xác thực văn thư: hồ sơ đã đối chiếu, đã thanh toán
                        hql = " from FilesNoClob f, Process p";
                        condition = " and f.fileId = p.objectId"
                                + " and (f.isTemp=null or f.isTemp=0)"
                                + " and f.isActive= 1"
                                + " and f.isSignPdf = 1"
                                + " and f.isFee = 1"
                                + " and f.status = ?"
                                + " and p.receiveGroupId = ?";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        lstParam.add(deptId);
                        break;
                    case -4://5- Hồ sơ đã yêu cầu nộp phí cấp số = Đã phê duyệt, chưa nộp lệ phí (files.status = 6, fee_payment_info.status = 0,fee.fee_type = 1 , files.isSignPdf=1)
                        /*hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi";
                         condition = " and f.fileId = fpi.fileId"
                         + " and (f.isTemp=null or f.isTemp=0)"
                         + " and fe.feeId = fpi.feeId"
                         + " and f.isActive=1 "
                         + " and fpi.isActive=1"
                         + " and fe.isActive=1"
                         + " and fe.feeType=1"
                         + " and fpi.status=0"
                         + " and f.userSigned is not null"
                         + " and (f.status=?)"
                         + " and f.agencyId = ?";*/
                        hql = " from FilesNoClob f";
                        condition = " and (f.isTemp=null or f.isTemp=0)"
                                + " and f.isActive=1 "
                                + " and f.isFee <> 1 and f.isSignPdf <> 2"
                                + " and (f.status=?)"
                                + " and f.agencyId = ?";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        lstParam.add(deptId);
                        break;
                    case -3://Hồ sơ đã nộp phí cấp số, chờ trả hồ sơ = Đã phê duyệt, đã nộp lệ phí (files.status = 6, fee_payment_info.status = 1, fee.fee_type=1, files.isSignPdf=2)
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi";
                        condition = " and f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                                + " and fe.feeId = fpi.feeId and fe.feeType=1 and fe.isActive=1"
                                + " and fpi.isActive=1"
                                + " and fpi.status=1"
                                + " and f.userSigned is not null"
                                + " and f.isSignPdf = 2"
                                + " and (f.status=?)"
                                + " and f.agencyId = ?";
                        lstParam.clear();
//                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        lstParam.add(Constants.FILE_STATUS.COMPARED);//da doi chieu, thanh toan xong tra ho so binhnt53 140926
                        lstParam.add(deptId);
                        break;
                    case -2://Hồ sơ đã yêu cầu nộp phí cấp số = Đã phê duyệt, chưa nộp lệ phí (files.status = 6, fee_payment_info.status = 0,fee.fee_type = 1 , files.isSignPdf=1)
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi";
                        condition = " and f.fileId = fpi.fileId"
                                + " and (f.isTemp=null or f.isTemp=0)"
                                + " and f.isActive=1"
                                + " and fe.feeId = fpi.feeId and fe.feeType=1 and fe.isActive=1"
                                + " and fpi.isActive=1"
                                + " and fpi.status=0"
                                + " and f.userSigned is not null"
                                + " and f.isSignPdf = 1"
                                + " and (f.status=?)"
                                + " and f.agencyId = ?";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        lstParam.add(deptId);
                        break;
                    case -1://Hồ sơ chờ tiếp nhận = Mới nộp và đã xác nhận phí (files.status = 1, fee_payment_info.status = 1, fee.fee_type=2), Mới nộp SĐBS (18)
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi, Category c ";
                        condition = " and f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                                + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                                + " and  f.productTypeId = c.categoryId "
                                + " and fpi.isActive=1"
                                + " and fpi.status=1"
                                + " and f.userSigned is not null"
                                + " and f.status=? and f.agencyId = ?";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.NEW);
                        lstParam.add(deptId);
                        break;
                    case -7://Hồ sơ chờ kế toán xác nhận = Mới nộp và chưa đóng phí thẩm định (files.status = 1, fee_payment_info.status <> 1, fee.fee_type=2)
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi";
                        condition = " and f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                                + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                                + " and fpi.isActive=1"
                                + " and fpi.status > 2"
                                + " and f.userSigned is not null"
                                + " and (f.status=?) and f.agencyId = ?";
                        ;
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.NEW);
                        lstParam.add(deptId);
                        break;
                    case 0:
                        hql = " from FilesNoClob f, Process p";
                        lstParam.clear();
                        condition = " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0)"
                                + " and (f.status = ?)";
                        lstParam.add(form.getStatus());
                        condition += " and p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        break;
                    case 110://danh sach ho so can luu tru
                        hql = " from FilesNoClob f, Process p";
                        lstParam.clear();
                        condition = " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0)"
                                + " and f.status <> ?"
                                + " and f.isFee <> 2";
                        condition += " and p.receiveGroupId = ?";
                        lstParam.add(Constants.FILE_STATUS.NEW_CREATE);
                        lstParam.add(deptId);
                        break;
                    default:;
                }
            }
            if (userType.equals(Constants.ROLES.LEAD_UNIT) && form.getSearchType() != null) {//vldp
                switch (Integer.parseInt(form.getSearchType().toString())) {
                    case 39:
                        condition += " and f.isActive = 1 and f.fileId = p.objectId AND f.status = ?  and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_REVIEW);
                        if (userId != null) {
                            condition += " and f.leaderReviewId=?";
                            lstParam.add(userId);
                        }
                        break;
                    case 30:
                        condition += " and f.isActive = 1 and f.fileId = p.objectId AND f.status = ?  and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED_TO_ADD);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                        if (deptId != null) {
                            condition += " AND p.receiveGroupId = ? ";
                            lstParam.add(deptId);
                        }
                        break;
                    case 2:
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED);
                        condition += " and p.receiveGroupId = ? and p.receiveUserId = ?";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                        condition += " and ((f.leaderEvaluateId = ? and f.leaderReviewId = null) or f.leaderReviewId =?)";
                        lstParam.add(userId);
                        lstParam.add(userId);
                        break;
                    case 7:
                        condition += " and (f.status = ?)";////150204 binhnt53 update
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                        condition += " and p.receiveGroupId = ? and p.receiveUserId = ?";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                        condition += " and (f.leaderReviewId =?)";
                        lstParam.add(userId);
                        break;
                    case 8:// ho so cho xem xet du thao sdbs
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.EVALUATE_TO_ADD);
                        condition += " and p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        condition += " and p.receiveUserId = ?";
                        lstParam.add(userId);
                        condition += " and f.leaderReviewId = ?";
                        lstParam.add(userId);
                        break;
                    case 5://ho so cho lanh dao cuc phe duyet, lanh dao phong da duyet
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.REVIEWED);
                        condition += " and p.receiveGroupId = ? and p.receiveUserId = ?";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                        break;
                    case 22://ho so cho lanh dao cuc phe duyet, lanh dao phong da duyet
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
//                        condition += " and p.receiveGroupId = ? and p.receiveUserId = ?";
//                        lstParam.add(deptId);
//                        lstParam.add(userId);
                        break;
                }
            }
            if (userType.equals(Constants.ROLES.LEAD_OFFICE_ROLE) && form.getSearchType() != null) {//vldc
                switch (Integer.parseInt(form.getSearchType().toString())) {
                    case 3:
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.REVIEWED);
                        condition += " and p.receiveGroupId = ? and p.receiveUserId = ?";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                        break;
                    case 10:
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        condition += " and p.receiveGroupId = ? and p.receiveUserId = ?";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                        break;
                    case 26:
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.REVIEW_TO_ADD);
                        condition += " and p.receiveGroupId = ? and p.receiveUserId = ?";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                        break;
                    case 36://a260515 binhnt
                        condition += " and f.status in (?,?,?,?,?,?)"
                                + " and f.isActive = 1 "
                                + " and (f.isTemp is null or f.isTemp = 0)";
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                        lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
                        lstParam.add(Constants.FILE_STATUS.COMPARED);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON);
                        lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);
                        break;
                }
            }
            if (userType.equals(Constants.ROLES.ADMIN) && form.getSearchType() != null) {//vQTHT
                switch (Integer.parseInt(form.getSearchType().toString())) {
                    case 110://hồ sơ cần đối chiếu
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND (f.staffProcess <> null)";
                        break;
                }
            }
            if (form.getSearchType() == null) {
                if (form.getStatus() != null
                        && form.getSearchType() == null
                        && (form.getStatus() == 40l
                        || form.getStatus() == 41l
                        || form.getStatus() == 42l
                        || form.getStatus() == 43l
                        || form.getStatus() == 44l
                        || form.getStatus() == 45l
                        || form.getStatus() == 46l
                        || form.getStatus() == 47l
                        || form.getStatus() == 48l
                        || form.getStatus() == 49l
                        || form.getStatus() == 30l
                        || form.getStatus().equals(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL)
                        || form.getStatus() == 50l)) {
                    lstParam.clear();
                    hql = " from FilesNoClob f, Process p, DetailProduct d, Business b";
                    condition = " and f.fileId = p.objectId and f.detailProductId = d.detailProductId and f.deptId = b.businessId";
                    // Nhà sản xuất
                    if (form.getManufactureName() != null && form.getManufactureName().length() > 0) {
                        hql += ", Announcement ann";
                        condition += " and f.announcementId = ann.announcementId and lower(ann.manufactureName) like ? ESCAPE '/'";
                        lstParam.add(StringUtils.toLikeString(form.getManufactureName().toLowerCase().trim()));
                    }
                    // Số chứng nhận công bố
                    if (form.getAnnouncementNo() != null && form.getAnnouncementNo().length() > 0) {
                        hql += ", AnnouncementReceiptPaper ann";
                        condition += " and f.announcementReceiptPaperId = ann.announcementReceiptPaperId and lower(ann.receiptNo) like ? ESCAPE '/'";
                        lstParam.add(StringUtils.toLikeString(form.getAnnouncementNo().toLowerCase().trim()));
                    }
//                // thong ke ho so trong ngay
                    if (form.getStatus() != null && form.getStatus() == 40l) {
                        condition += " and f.isActive = 1 and (f.status = ? or f.status = ?) and  to_date(f.sendDate,'yyyy/mm/dd') = to_date(sysdate,'yyyy/mm/dd')  ";
                        lstParam.add(3l);
                        lstParam.add(5l);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }
                    if (form.getStatus() != null && form.getStatus() == 41l) {
                        condition += " AND f.isActive = 1"
                                + " AND (f.status = ? or f.status = ?)"
                                + " AND  to_date(f.sendDate,'yyyy/mm/dd') = to_date(sysdate,'yyyy/mm/dd')"
                                + " AND (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(4l);
                        lstParam.add(5l);
                    }
                    if (form.getStatus() != null && form.getStatus() == 42l) {
                        condition += " and f.isActive = 1"
                                + " and (f.status = ? or f.status = ?)"
                                + " and  to_date(f.sendDate,'yyyy/mm/dd') = to_date(sysdate,'yyyy/mm/dd')"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(6l);
                        lstParam.add(5l);
                    }
                    // ho so bi tra tham dinh lai
                    if (form.getStatus() != null && form.getStatus() == 43l) {
                        condition += " and f.isActive=1"
                                + " and f.status = ?"
                                + " and p.processType=1"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(8l);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }
                    // ho so cho phoi hop tham dinh chua cho y kien
                    if (form.getStatus() != null && form.getStatus() == 44l) {
                        condition += " and f.isActive=1"
                                + " and (f.status = ?)"
                                + " and (p.processType=0 or p.processType=4)"
                                + " and (p.processId in (select p.processId from Process p where p.processId not in (select distinct pc.processId from ProcessComment pc)))"
                                + " and p.processStatus = ?";
                        lstParam.add(3l);
                        lstParam.add(3l);
                    }

                    //ho so cho xem xet
                    if (form.getStatus() != null && form.getStatus() == 48L) {
                        condition += " and f.isActive=1"
                                + " and (f.status = ? or f.status = ?)"
                                + " and (p.processType=1 or p.processType=0)"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED);
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }
                    // ho so lanh dao phong da xem xet
                    if (form.getStatus() != null && form.getStatus() == 46l) {
                        condition += " and f.isActive=1"
                                + " and f.status = ?"
                                + " and p.processType=1"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(5l);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }
                    //ho so da phe duyet
                    if (form.getStatus() != null && form.getStatus() == 47l) {
                        condition += " and f.isActive=1"
                                + " and f.status = ?"
                                + " and p.processType=1"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }
                    // ho so tham dịnh da gui ý kiễn phản hồi
                    if (form.getStatus() != null && form.getStatus() == 49l) {
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p, ProcessComment pc";
                        condition = " AND f.isActive=1"
                                + " AND f.fileId = p.objectId"
                                + " AND p.objectType = 30"
                                + " AND (f.status = ?)"
                                + " AND (p.processType=0 or p.processType=1)"
                                + " AND pc.processId = p.processId"
                                + " AND (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(3l);
                        if (userId != null) {
                            condition += " AND p.receiveGroupId = (select distinct p.receiveGroupId from Process p where p.receiveUserId = ?)";
                            lstParam.add(userId);
                        }
                    }
                    // hieptq update 17.11.14 da phan cong

                    if (form.getStatus() != null && form.getStatus() == 50l) {
                        condition += " and f.isActive=1"
                                + " and f.status = ?"
                                + " and p.processType=1"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(3l);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }
                    // da thong bao SDBS
                    if (form.getStatus() != null && form.getStatus() == 30l) {
                        condition += " and f.isActive=1"
                                + " and f.status = ?"
                                + " and p.processType=1"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(20l);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }

                    // da tham dinh - hieptq
                    if (form.getStatus() != null && form.getStatus() == 45l) {
                        condition += " and f.isActive=1"
                                + " and f.status = ?"
                                + " and p.processType=1"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(4l);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }

                    //
                    if (form.getStatus() != null && form.getStatus() == Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL) {
                        condition += " and f.isActive = 1"
                                + " and f.status = ?"
                                + " and (f.isTemp = null or f.isTemp = 0)";
                        lstParam.add(25l);
                    }
                } else {
                    if (form.getStatus() != null && form.getStatus() >= 0l) {
                        condition += " AND f.status = ?";
                        lstParam.add(form.getStatus());
                    } else {
                    }
                }

                if (userType.equals(Constants.ROLES.LEAD_OFFICE_ROLE)) {
                    if (deptId != null) {
                        condition += " and p.receiveGroupId = ? ";
                        lstParam.add(deptId);
                    }
//                    if (userId != null) {
//                        condition += " and p.receiveUserId = ? ";
//                        lstParam.add(userId);
//                    }
                }
                if (userType.equals(Constants.ROLES.STAFF_ROLE)) {
                    if (deptId != null) {
                        if (form.getStatus() != null && form.getStatus() != 40l) {
                            condition += " and p.receiveGroupId = ? ";
                            lstParam.add(deptId);
                        }
                    }
                    if (userId != null) {
                        condition += " and p.receiveUserId = ? ";
                        lstParam.add(userId);
                    }
                }
                if (userType.equals(Constants.ROLES.LEAD_UNIT)) {
                    if (deptId != null) {
                        condition += " and p.receiveGroupId = ?"
                                + " and ( p.receiveUserId = null or p.receiveUserId = ?) ";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                    }
                }
                if (userType.equals(Constants.ROLES.CLERICAL_ROLE)) {
                    if (deptId != null) {
//                    hql += "AND f.agencyId = ?)";
//                    lstParam.add(deptId);
                        condition += " and p.receiveGroupId = ? ";
                        lstParam.add(deptId);
                    }
                }
            }
            // Nhà sản xuất
            if (form.getManufactureName() != null && form.getManufactureName().length() > 0) {
                String str = ", Announcement ann";
                if (!str.contains(hql)) {
                    hql += ", Announcement ann";
                }
                condition += " and f.announcementId = ann.announcementId and lower(ann.manufactureName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getManufactureName().toLowerCase().trim()));
            }
            // Số chứng nhận công bố
            if (form.getAnnouncementNo() != null && form.getAnnouncementNo().length() > 0) {
                String str = ", AnnouncementReceiptPaper arp";
                if (!str.contains(hql)) {
                    hql += ", AnnouncementReceiptPaper arp";
                    condition += " and f.announcementReceiptPaperId = arp.announcementReceiptPaperId";
                }
                condition += " and lower(arp.receiptNo) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getAnnouncementNo().toLowerCase().trim()));
            }
            // ngay cap
            if (form.getReceiptDate() != null) {
                String str = ", AnnouncementReceiptPaper arp";
                if (!str.contains(hql)) {
                    hql += ", AnnouncementReceiptPaper arp";
                    condition += " and f.announcementReceiptPaperId = arp.announcementReceiptPaperId";
                }
                condition += " and lower(arp.receiptDate) = ?";
                lstParam.add(form.getReceiptDate());
            }
            //thương nhân chịu trách nhiệm
            if (form.getOrigin() != null && form.getOrigin().length() > 0) {
                String str = ", DetailProduct d";
                if (!hql.contains(str)) {
                    hql += ", DetailProduct d";
                    condition += " and f.detailProductId = d.detailProductId";
                }
                condition += " and lower(d.origin) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getOrigin().toLowerCase().trim()));
            }
            // điện thoại doanh nghiệp
            if (form.getBusinessTelephone() != null && form.getBusinessTelephone().length() > 0) {
                String str = ", Announcement ann";
                if (!hql.contains(str)) {
                    hql += ", Announcement ann";
                    condition += " and f.announcementId = ann.announcementId";
                }
                condition += " and lower(ann.businessTelephone) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getBusinessTelephone().toLowerCase().trim()));
            }
            // fax doanh nghiệp
            if (form.getBusinessFax() != null && form.getBusinessFax().length() > 0) {
                String str = ", Announcement ann";
                if (!hql.contains(str)) {
                    hql += ", Announcement ann";
                    condition += " and f.announcementId = ann.announcementId";
                }
                condition += " and lower(ann.businessFax) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getBusinessFax().toLowerCase().trim()));
            }
            // mã hồ sơ
            if (form.getFileCode() != null && !"".equals(form.getFileCode().trim())) {
                condition += " AND lower(f.fileCode) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getFileCode().toLowerCase().trim()));
            }
            if (form.getFileType() != null && form.getFileType().longValue() != -1) {
                condition += " AND f.fileType = ?";
                lstParam.add(form.getFileType());
            }
            if (form.getSignDate() != null) {
                condition += " AND f.signDate = ?";
                lstParam.add(form.getSignDate());
            }
            // Su dung chung - DuND
            // Haitv21 thêm chỉ tiêu thông tin tìm kiếm
            // Do hieptq reset lại params nên đoạn code này phải dưới các case if_ else bên trên
            if (form.getSendDateFrom() != null) {
                condition += " AND f.sendDate >= ?";
                lstParam.add(minDayToCompare(form.getSendDateFrom()));
            }
            if (form.getSendDateTo() != null) {
                condition += " AND f.sendDate <= ?";
                lstParam.add(maxDayToCompare(form.getSendDateTo()));
            }

            // hieptq update 300115
            if (form.getSignDateFrom() != null) {
                condition += " AND f.signDate >= ? ";
                lstParam.add(minDayToCompare(form.getSignDateFrom()));
            }
            if (form.getSignDateTo() != null) {
                condition += " AND f.signDate <= ? ";
                lstParam.add(maxDayToCompare(form.getSignDateTo()));
            }
            if (form.getSignDateFrom() != null || form.getSignDateFrom() != null) {
                condition += " AND f.status = ? ";
                lstParam.add(22l);
            }

            // ngày thêm kho lưu trữ
            if (form.getRepDateFrom() != null) {
                condition += " AND f.repDate >= ?";
                lstParam.add(minDayToCompare(form.getRepDateFrom()));
            }
            if (form.getRepDateTo() != null) {
                condition += " AND f.repDate <= ?";
                lstParam.add(maxDayToCompare(form.getRepDateTo()));
            }

            // Ngày phê duyệt từ ngày x tới ngày x
            if (form.getApproveDateFrom() != null) {
                condition += " AND f.approveDate >= ?";
                lstParam.add(minDayToCompare(form.getApproveDateFrom()));
            }
            if (form.getApproveDateTo() != null) {
                condition += " AND f.approveDate <= ?";
                lstParam.add(maxDayToCompare(form.getApproveDateTo()));
            }
            // Người phê duyệt
            if (form.getLeaderStaffSignName() != null && form.getLeaderStaffSignName().length() > 0) {
                condition += " AND lower(f.leaderStaffSignName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getLeaderStaffSignName().toLowerCase().trim()));
            }
            // Tên doanh nghiệp
            if (form.getBusinessName() != null && form.getBusinessName().length() > 0) {
                condition += " AND lower(f.businessName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getBusinessName().toLowerCase().trim()));
            }
            // Xuất xứ
            if (form.getNationName() != null && form.getNationName().length() > 0) {
                condition += " AND lower(f.nationName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getNationName().toLowerCase().trim()));
            }
            // Người thẩm định
            if (form.getReceiveUser() != null && form.getReceiveUser().length() > 0) {
                condition += " AND lower(p.receiveUser) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getReceiveUser().toLowerCase().trim()));
            }
            // Số đăng ký kinh doanh
            if (form.getBusinessLicence() != null && form.getBusinessLicence().length() > 0) {
                condition += " AND lower(b.businessLicense) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getBusinessLicence().toLowerCase().trim()));
            }
            // Địa chỉ doanh nghiệp
            if (form.getBusinessAddress() != null && form.getBusinessAddress().length() > 0) {
                condition += " AND lower(f.businessAddress) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getBusinessAddress().toLowerCase().trim()));
            }
            // Tên sản phẩm
            if (form.getProductName() != null && form.getProductName().length() > 0) {
                condition += " AND lower(f.productName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getProductName().toLowerCase().trim()));
            }
            // Nhóm sản phẩm
            if (form.getProductType() != null && form.getProductType() != -1l) {
                condition += " AND d.productType = ?";
                lstParam.add(form.getProductType());
            }
            // Lãnh đạo phòng
            if (form.getLeaderStaff() != null && form.getLeaderStaff().length() > 0) {
                condition += " AND lower(p.receiveUser) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getLeaderStaff().toLowerCase().trim()));
            }
            // Người thẩm xét
            if (form.getStaff() != null && form.getStaff().trim().length() > 0) {
                condition += " AND lower(f.nameStaffProcess) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getStaff().toLowerCase().trim()));
            }
            if (form.getNameStaffProcess() != null && form.getNameStaffProcess().length() > 0) {
                condition += " AND lower(f.nameStaffProcess) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getNameStaffProcess().toLowerCase().trim()));
            }
            if (form.getLeaderApproveName() != null && form.getLeaderApproveName().length() > 0) {
                condition += " AND lower(f.leaderApproveName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getLeaderApproveName().toLowerCase().trim()));
            }
            if (form.getLeaderAssignName() != null && form.getLeaderAssignName().length() > 0) {
                condition += " AND lower(f.leaderAssignName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getLeaderAssignName().toLowerCase().trim()));
            }
            if (form.getLeaderReviewName() != null && form.getLeaderReviewName().length() > 0) {
                condition += " AND lower(f.leaderReviewName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getLeaderReviewName().toLowerCase().trim()));
            }
            // Tỉnh -  Thành Phố
            if (form.getProductType() != null && form.getBusinessProvinceId() != -1l) {
                condition += " AND b.businessProvinceId = ? ";
                lstParam.add(form.getBusinessProvinceId());
            }
            // Nơi lưu trữ
            if (form.getRepositoriesId() != null && form.getRepositoriesId() != -1l) {
                condition += " AND (f.repositoriesId = ? ) ";
                lstParam.add(form.getRepositoriesId());
            }
            // Lọc theo người tạo ( lưu trữ hồ sơ giấy )
            if (form.getRepCreator() != null && (form.getSearchType() == 0 || form.getSearchType() == 2)) {
                //condition += " AND (f.fileId in (select p.objectId  from Process p where p.processStatus = 3 and p.receiveUserId = ?)) ";
//                condition += " AND (p.processStatus = 3 and p.receiveUserId = ?) ";
//                lstParam.add(form.getRepCreator());
            }
            // Kho lưu trữ
            if (form.getRepName() != null && form.getRepName() != -1l) {
                condition += " AND (f.repositoriesId = ? )";
                lstParam.add(form.getRepName());
            }
            // Trạng thái lưu trữ
            // Đã lưu trữ
            if (form.getRepStatus() != null && form.getRepStatus() == 1) {
                condition += " AND (f.repositoriesId <> null )";
            }
            // Chưa lưu trữ
            if (form.getRepStatus() != null && form.getRepStatus() == 2) {
                condition += " AND (f.repositoriesId = null )";
            }
            //lãnh đạo phân công - 140915 binhnt53
            if (form.getLeaderAssignId() != null && form.getLeaderAssignId() > 0L) {
                condition += " AND (f.leaderAssignId = ? )";
                lstParam.add(form.getLeaderAssignId());
            }
            // tra cuc van thu ngay tiep nhan tu ngay den ngay
            if (form.getReceivedDate() != null) {
                condition += " and f.receivedDate >= ?";
                lstParam.add(minDayToCompare(form.getReceivedDate()));
            }
            if (form.getReceivedDateTo() != null) {
                condition += " and f.receivedDate <= ?";
                lstParam.add(maxDayToCompare(form.getReceivedDateTo()));
            }
            if (form.getReceiveNo() != null && !form.getReceiveNo().equals("") && !form.getReceiveNo().trim().equals("")) {
                condition += " and f.receiveNo like ?";
                lstParam.add(form.getReceiveNo().trim());
            }
            if (form.getIs30() != null && form.getIs30() != -1l) {
                if (form.getIs30() == 1) {
                    condition += " AND (f.is30 = 1 )";
                } else {
                    condition += " AND (f.is30 = null )";
                }
            }
            if (form.getSearchFullText() != null && form.getSearchFullText().trim().length() > 0) {
                condition += " and f.fileId in (select ffs.fileId from FileForSearch ffs where lower(ffs.content) like ? ESCAPE '/')";
                lstParam.add(StringUtils.toLikeString(form.getSearchFullText()));
            }
        }
        // loc file_id nhom san pham khac
        // hieptq update 251214
        if (form.getProductTypeNew() != null && form.getProductTypeNew().longValue() != -1) {
            String str = ", Category c";
            if (!hql.contains(str)) {
                hql += ", Category c";
                condition += " and  f.productTypeId = c.categoryId";
            }
            if (form.getProductTypeNew() == 1) {
                condition += " AND (c.code <> ? and c.code <> ?) ";
                lstParam.add(Constants.CATEGORY_TYPE.TPCN);
                lstParam.add(Constants.CATEGORY_TYPE.DBT);
            } else {
                condition += " AND (c.code = ? or c.code = ?) ";
                lstParam.add(Constants.CATEGORY_TYPE.TPCN);
                lstParam.add(Constants.CATEGORY_TYPE.DBT);
            }
        }
        //hieptq update 251114
        if (form.getSearchTypeNew() != null) {
            if (form.getSearchTypeNew() == 1) {
                condition += " AND (c.code <> ? and c.code <> ?) ";
                lstParam.add(Constants.CATEGORY_TYPE.TPCN);
                lstParam.add(Constants.CATEGORY_TYPE.DBT);
            } else {
                condition += " AND (c.code = ? or c.code = ?) ";
                lstParam.add(Constants.CATEGORY_TYPE.TPCN);
                lstParam.add(Constants.CATEGORY_TYPE.DBT);
            }
        }
        //!hieptq update
        Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql + " where 1=1 " + condition);
        String finalSql = "select distinct f " + hql + " where 1=1 " + condition + " order by ";
        if (sortCustom.isEmpty()) {
            finalSql += "f.modifyDate DESC";
        } else {
            finalSql += sortCustom;
        }
        Query query = getSession().createQuery(finalSql);

        for (int i = 0;
                i < lstParam.size();
                i++) {
            query.setParameter(i, lstParam.get(i));
            countQuery.setParameter(i, lstParam.get(i));
        }

        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        query.setFirstResult(start);
        if (count > 0) {
            query.setMaxResults(count);
        } else {
            query.setMaxResults(total);
        }
        List<FilesNoClob> lstResult = query.list();

        return new GridResult(total, lstResult);
    }

    /**
     * Tìm hồ sơ của doanh nghiệp
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult searchBusinessFiles(FilesForm form, int start, int count, String sortField, Long deptId) {
        String hql = " from FilesNoClob f where f.isActive=1 and (f.isTemp = null or f.isTemp = 0 )";
        List lstParam = new ArrayList();
        if (form != null) {
            if (form.getStatus() != null && form.getStatus() != -1L) {
                // ho so da doi chieu
                if (form.getStatus().equals(Constants.FILE_STATUS.COMPARED)) {
                    hql += " AND ( f.status = ? or f.status = ? )";
                    lstParam.add(Constants.FILE_STATUS.COMPARED);
                    lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                } else {
                    if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {
                        hql += " AND ( f.status = ? or f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED_TO_ADD);
                        lstParam.add(Constants.FILE_STATUS.RECEIVED_REJECT);
                    } else {
                        if (form.getStatus().equals(Constants.FILE_STATUS.NEW)) {
                            hql += " AND ( f.status = ? or f.status = ?)";
                            lstParam.add(Constants.FILE_STATUS.NEW);
                            lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
                        } else {
                            if (form.getStatus() != null) {
                                hql += " AND ( f.status = ?)";
                                lstParam.add(form.getStatus());
                            }
                        }
                    }
                }
            }
            if (form.getSearchType() != null) {
                switch (Integer.parseInt(form.getSearchType().toString())) {
                    case -77:
                        lstParam.clear();
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " AND (f.isTemp = null or f.isTemp = 0 )"
                                + " AND f.deptId = ? "
                                + " AND f.isFee = ? ";
                        lstParam.add(deptId);
                        lstParam.add(Constants.FEE_STATUS.DA_TU_CHOI);
                        break;
                    case -2:// binhnt53 update 150209
                        hql = "from FilesNoClob f where f.isActive = 1 and (f.isTemp = null or f.isTemp = 0 ) and (sysdate - 366  <= f.createDate) ";
                        hql += " and f.status in (?,?,?,?,?,?,?,?,?,?,?)"
                                + " and f.deptId = ? ";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                        lstParam.add(Constants.FILE_STATUS.EVALUATED);
                        lstParam.add(Constants.FILE_STATUS.REVIEWED);
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_REVIEW);
                        lstParam.add(Constants.FILE_STATUS.REVIEWED_TO_ADD);
                        lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_TO_ADD);
//lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
//lstParam.add(Constants.FILE_STATUS.RECEIVED);
//lstParam.add(Constants.FILE_STATUS.COMPARED);
                        lstParam.add(deptId);
                        break;
                    case -5:
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                                + " and fe.feeId = fpi.feeId and fe.feeType=1 and fe.isActive=1"
                                + " and fpi.isActive=1"
                                + " and (fpi.status=0)"
                                // + " and f.userSigned is not null"
                                + " and (f.status=?) and f.deptId =" + deptId + "";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        break;
                    case -4://3- Hồ sơ chờ tiếp nhận =
//  + Mới nộp + chờ xác nhận văn thư (files.status = 1, fee_payment_info.status = 3 or 4)
//  + Mới nộp SĐBS + chờ xác nhận văn thư(files.status = 18, fee_payment_info.status = 3 or 4)
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi "
                                + " where fpi.fileId = f.fileId "
                                + " and f.isActive = 1 "
                                + " and f.userSigned is not null"
                                + " and (f.isTemp is null or f.isTemp = 0) and f.isFee = 1"
                                //                                + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                                + " and (f.status = ?) "
                                + " and fpi.isActive = 1"
                                + " and f.deptId = ?";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.NEW);
                        //lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
                        lstParam.add(deptId);
                        break;

                    // ho so cho tiep nhan SDBS
                    case -7:
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi "
                                + " where fpi.fileId = f.fileId "
                                + " and f.isActive = 1 "
                                + " and f.userSigned is not null"
                                + " and (f.isTemp is null or f.isTemp = 0) and f.isFee = 1"
                                //                                + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                                + " and (f.status = ?) "
                                + " and fpi.isActive = 1"
                                + " and f.deptId = ?";
                        lstParam.clear();
                        //lstParam.add(Constants.FILE_STATUS.NEW);
                        lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
                        lstParam.add(deptId);
                        break;

                    // ho so cho ke toan xac nhan nop phi
                    case -100:
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi where f.fileId = fpi.fileId  and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                                + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                                + " and fpi.isActive=1"
                                + " and fpi.status not in (2,1)"//u150211 by binhnt //+ " and fpi.status <> 1"
                                + " and f.userSigned is not null"
                                + " and f.status=?"
                                + " and f.deptId = ? ";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.NEW);
                        lstParam.add(deptId);
                        break;
                    // hieptq update 161214 ho so cho ke toan xac nhan le phi cap so
                    case -109:
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                                + " and fe.feeId = fpi.feeId and fe.feeType=1 and fe.isActive=1"
                                + " and fpi.isActive=1"
                                + " and (fpi.status > 1)"
                                + " and f.userSigned is not null"
                                + " and (f.status=?) ";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        //lstParam.add(deptId);
                        break;
                    case -3://2- Hồ sơ chưa nộp phí  = Mới tạo, chưa nộp phí, đã ký or upload file ký(files.status = 0, fee_payment_info.status = 0, files.user_signed is
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi"
                                + " where  f.fileId = fpi.fileId and f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)"
                                + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                                + " and fpi.isActive=1"
                                + " and f.userSigned is not null"
                                + " and f.status=?";
                        lstParam.clear();
                        lstParam.add(form.getStatus());
                        break;
                    case 1:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) ";
                        hql += " AND f.status = ? ";
                        lstParam.clear();
                        lstParam.add(form.getStatus());
                        break;
                    case 33:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) ";
                        hql += " AND (f.status = ? or f.status = ?)";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.RECEIVED_REJECT);
                        lstParam.add(Constants.FILE_STATUS.RECEIVED_REJECT_TO_ADD);
                        break;
                    case 2:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) ";
                        hql += " AND f.status = ? ";
                        lstParam.clear();
                        lstParam.add(form.getStatus());
                        break;
                    case 3:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) ";
                        hql += " AND f.status = ? ";
                        lstParam.clear();
                        lstParam.add(form.getStatus());
                        break;
                    case 4:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) ";
                        hql += " AND f.status = ? ";
                        lstParam.clear();
                        lstParam.add(form.getStatus());
                        break;
                    case 5:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) "
                                + " and f.userSigned = null"
                                + " AND f.status = ? ";
                        lstParam.clear();
                        lstParam.add(form.getStatus());
                        break;
                    case 55:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) "
                                + " and f.userSigned = null"
                                + " AND (f.status = ? or f.status = ? )";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.NEW_CREATE);
                        lstParam.add(Constants.FILE_STATUS.NEW);
                        break;
                    case 6:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) "
                                + " and f.isFee = 1 and f.isSignPdf = 2"
                                + " AND f.status = ? ";
                        lstParam.clear();
                        lstParam.add(form.getStatus());
                        break;

                    ///Hồ sơ văn thư đã trả bản công bố (bản mềm) =  ho so da ky so + dong tien + dong dau so - binhnt53 150202
                    case 76:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1 AND (f.isTemp = null OR f.isTemp = 0 )"
                                + " AND f.isFee = 1 AND f.isSignPdf = 2"
                                + " AND f.deptId = ?"
                                + " AND f.status in (?,?,?,?,?,?,?)";
                        lstParam.clear();
                        lstParam.add(deptId);
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                        lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
                        lstParam.add(Constants.FILE_STATUS.COMPARED);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);
                        lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                        break;
                    default:
//                        hql += " AND f.status = ? ";
//                        lstParam.add(form.getStatus());
                }
            }
        }
        if (form.getFileCode() != null && !"".equals(form.getFileCode().trim())) {
            hql += " AND lower(f.fileCode) like ? ESCAPE '/' ";
            lstParam.add(StringUtils.toLikeString(form.getFileCode().toLowerCase().trim()));
        }
        if (form.getFileType() != null && form.getFileType().longValue() != -1) {
            hql += " AND f.fileType = ? ";
            lstParam.add(form.getFileType());
        }
        if (form.getDeptId() != null) {
            hql += " AND f.deptId = ? ";
            lstParam.add(form.getDeptId());
        }
        if (form.getSendDateFrom() != null) {
            hql += " AND f.sendDate >= ? ";
            lstParam.add(form.getSendDateFrom());
        }
        if (form.getSendDateTo() != null) {
            hql += " AND f.sendDate <= ? ";
            lstParam.add(addOneDay(form.getSendDateTo()));
        }

        if (form.getApproveDate() != null) {
            hql += " AND f.approveDate = ? ";
            lstParam.add(form.getApproveDate());
        }
        if (form.getProductName() != null && !"".equals(form.getProductName().trim())) {
            hql += " AND lower(f.productName) like ? ESCAPE '/' ";
            lstParam.add(StringUtils.toLikeString(form.getProductName().toLowerCase().trim()));
        }

        Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql);
        Query query = getSession().createQuery("select distinct f " + hql + " order by f.modifyDate DESC");
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            countQuery.setParameter(i, lstParam.get(i));
        }
        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List lstResult = query.list();
        GridResult gr = new GridResult(total, lstResult);
        return gr;
    }

    public GridResult searchBusinessFilesNoSDBSAnd4Star(FilesForm form, int start, int count, String sortField, Long deptId) {
        String hql = " from FilesNoClob f, Procedure p where f.isActive=1 and (f.isTemp = null or f.isTemp = 0 ) and f.fileType = p.procedureId and p.description!='announcementFile05' and p.description!='announcement4star' ";
        List lstParam = new ArrayList();
        if (form != null) {
            if (form.getStatus() != null && form.getStatus() != -1L) {
                // ho so da doi chieu
                if (form.getStatus().equals(Constants.FILE_STATUS.COMPARED)) {
                    hql += " AND ( f.status = ? or f.status = ? )";
                    lstParam.add(Constants.FILE_STATUS.COMPARED);
                    lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                } else {
                    if (form.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {
                        hql += " AND ( f.status = ? or f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED_TO_ADD);
                        lstParam.add(Constants.FILE_STATUS.RECEIVED_REJECT);
                    } else {
                        if (form.getStatus().equals(Constants.FILE_STATUS.NEW)) {
                            hql += " AND ( f.status = ? or f.status = ?)";
                            lstParam.add(Constants.FILE_STATUS.NEW);
                            lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
                        } else {
                            if (form.getStatus() != null) {
                                hql += " AND ( f.status = ?)";
                                lstParam.add(form.getStatus());
                            }
                        }
                    }
                }
            }
            if (form.getSearchType() != null) {
                switch (Integer.parseInt(form.getSearchType().toString())) {
                    case -77:
                        lstParam.clear();
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " AND (f.isTemp = null or f.isTemp = 0 )"
                                + " AND f.deptId = ? "
                                + " AND f.isFee = ? ";
                        lstParam.add(deptId);
                        lstParam.add(Constants.FEE_STATUS.DA_TU_CHOI);
                        break;
                    case -2:// binhnt53 update 150209
                        hql = "from FilesNoClob f where f.isActive = 1 and (f.isTemp = null or f.isTemp = 0 ) and (sysdate - 366  <= f.createDate) ";
                        hql += " and f.status in (?,?,?,?,?,?,?,?,?,?,?)"
                                + " and f.deptId = ? ";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                        lstParam.add(Constants.FILE_STATUS.EVALUATED);
                        lstParam.add(Constants.FILE_STATUS.REVIEWED);
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_REVIEW);
                        lstParam.add(Constants.FILE_STATUS.REVIEWED_TO_ADD);
                        lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_TO_ADD);
//lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
//lstParam.add(Constants.FILE_STATUS.RECEIVED);
//lstParam.add(Constants.FILE_STATUS.COMPARED);
                        lstParam.add(deptId);
                        break;
                    case -5:
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                                + " and fe.feeId = fpi.feeId and fe.feeType=1 and fe.isActive=1"
                                + " and fpi.isActive=1"
                                + " and (fpi.status=0)"
                                // + " and f.userSigned is not null"
                                + " and (f.status=?) and f.deptId =" + deptId + "";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        break;
                    case -4://3- Hồ sơ chờ tiếp nhận =
//  + Mới nộp + chờ xác nhận văn thư (files.status = 1, fee_payment_info.status = 3 or 4)
//  + Mới nộp SĐBS + chờ xác nhận văn thư(files.status = 18, fee_payment_info.status = 3 or 4)
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi "
                                + " where fpi.fileId = f.fileId "
                                + " and f.isActive = 1 "
                                + " and f.userSigned is not null"
                                + " and (f.isTemp is null or f.isTemp = 0) and f.isFee = 1"
                                //                                + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                                + " and (f.status = ?) "
                                + " and fpi.isActive = 1"
                                + " and f.deptId = ?";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.NEW);
                        //lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
                        lstParam.add(deptId);
                        break;

                    // ho so cho tiep nhan SDBS
                    case -7:
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi "
                                + " where fpi.fileId = f.fileId "
                                + " and f.isActive = 1 "
                                + " and f.userSigned is not null"
                                + " and (f.isTemp is null or f.isTemp = 0) and f.isFee = 1"
                                //                                + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                                + " and (f.status = ?) "
                                + " and fpi.isActive = 1"
                                + " and f.deptId = ?";
                        lstParam.clear();
                        //lstParam.add(Constants.FILE_STATUS.NEW);
                        lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
                        lstParam.add(deptId);
                        break;

                    // ho so cho ke toan xac nhan nop phi
                    case -100:
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi where f.fileId = fpi.fileId  and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                                + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                                + " and fpi.isActive=1"
                                + " and fpi.status not in (2,1)"//u150211 by binhnt //+ " and fpi.status <> 1"
                                + " and f.userSigned is not null"
                                + " and f.status=?"
                                + " and f.deptId = ? ";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.NEW);
                        lstParam.add(deptId);
                        break;
                    // hieptq update 161214 ho so cho ke toan xac nhan le phi cap so
                    case -109:
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                                + " and fe.feeId = fpi.feeId and fe.feeType=1 and fe.isActive=1"
                                + " and fpi.isActive=1"
                                + " and (fpi.status > 1)"
                                + " and f.userSigned is not null"
                                + " and (f.status=?) ";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        //lstParam.add(deptId);
                        break;
                    case -3://2- Hồ sơ chưa nộp phí  = Mới tạo, chưa nộp phí, đã ký or upload file ký(files.status = 0, fee_payment_info.status = 0, files.user_signed is
                        hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi"
                                + " where  f.fileId = fpi.fileId and f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)"
                                + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                                + " and fpi.isActive=1"
                                + " and f.userSigned is not null"
                                + " and f.status=?";
                        lstParam.clear();
                        lstParam.add(form.getStatus());
                        break;
                    case 1:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) ";
                        hql += " AND f.status = ? ";
                        lstParam.clear();
                        lstParam.add(form.getStatus());
                        break;
                    case 33:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) ";
                        hql += " AND (f.status = ? or f.status = ?)";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.RECEIVED_REJECT);
                        lstParam.add(Constants.FILE_STATUS.RECEIVED_REJECT_TO_ADD);
                        break;
                    case 2:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) ";
                        hql += " AND f.status = ? ";
                        lstParam.clear();
                        lstParam.add(form.getStatus());
                        break;
                    case 3:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) ";
                        hql += " AND f.status = ? ";
                        lstParam.clear();
                        lstParam.add(form.getStatus());
                        break;
                    case 4:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) ";
                        hql += " AND f.status = ? ";
                        lstParam.clear();
                        lstParam.add(form.getStatus());
                        break;
                    case 5:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) "
                                + " and f.userSigned = null"
                                + " AND f.status = ? ";
                        lstParam.clear();
                        lstParam.add(form.getStatus());
                        break;
                    case 55:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) "
                                + " and f.userSigned = null"
                                + " AND (f.status = ? or f.status = ? )";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.NEW_CREATE);
                        lstParam.add(Constants.FILE_STATUS.NEW);
                        break;
                    case 6:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )"
                                + " and (sysdate - 366  <= f.createDate) "
                                + " and f.isFee = 1 and f.isSignPdf = 2"
                                + " AND f.status = ? ";
                        lstParam.clear();
                        lstParam.add(form.getStatus());
                        break;

                    ///Hồ sơ văn thư đã trả bản công bố (bản mềm) =  ho so da ky so + dong tien + dong dau so - binhnt53 150202
                    case 76:
                        hql = "from FilesNoClob f"
                                + " where f.isActive = 1 AND (f.isTemp = null OR f.isTemp = 0 )"
                                + " AND f.isFee = 1 AND f.isSignPdf = 2"
                                + " AND f.deptId = ?"
                                + " AND f.status in (?,?,?,?,?,?,?)";
                        lstParam.clear();
                        lstParam.add(deptId);
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                        lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
                        lstParam.add(Constants.FILE_STATUS.COMPARED);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);
                        lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                        break;
                    default:
//                        hql += " AND f.status = ? ";
//                        lstParam.add(form.getStatus());
                }
            }
        }
        if (form.getFileCode() != null && !"".equals(form.getFileCode().trim())) {
            hql += " AND lower(f.fileCode) like ? ESCAPE '/' ";
            lstParam.add(StringUtils.toLikeString(form.getFileCode().toLowerCase().trim()));
        }
        if (form.getFileType() != null && form.getFileType().longValue() != -1) {
            hql += " AND f.fileType = ? ";
            lstParam.add(form.getFileType());
        }
        if (form.getDeptId() != null) {
            hql += " AND f.deptId = ? ";
            lstParam.add(form.getDeptId());
        }
        if (form.getSendDateFrom() != null) {
            hql += " AND f.sendDate >= ? ";
            lstParam.add(form.getSendDateFrom());
        }
        if (form.getSendDateTo() != null) {
            hql += " AND f.sendDate <= ? ";
            lstParam.add(addOneDay(form.getSendDateTo()));
        }

        if (form.getApproveDate() != null) {
            hql += " AND f.approveDate = ? ";
            lstParam.add(form.getApproveDate());
        }
        if (form.getProductName() != null && !"".equals(form.getProductName().trim())) {
            hql += " AND lower(f.productName) like ? ESCAPE '/' ";
            lstParam.add(StringUtils.toLikeString(form.getProductName().toLowerCase().trim()));
        }

        Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql);
        Query query = getSession().createQuery("select distinct f " + hql + " order by f.modifyDate DESC");
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            countQuery.setParameter(i, lstParam.get(i));
        }
        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List lstResult = query.list();
        GridResult gr = new GridResult(total, lstResult);
        return gr;
    }

    /**
     * Hiepvv Tìm danh sach hồ sơ bo sung sau cong bo cua mot ho so của doanh
     * nghiệp
     *
     * @param filesID
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult searchListFilesChangesAfterAnnouned(Long fileSourceID, int start, int count, String sortField, Long fileType) {
        String hql = " from FilesNoClob f where f.isActive=1 and f.filesSourceID=? and f.fileType = ? ";

        Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql);
        Query query = getSession().createQuery("select distinct f " + hql + " order by f.modifyDate DESC");
        if (fileSourceID != null && fileSourceID > 0L) {
            query.setParameter(0, fileSourceID);
            query.setParameter(1, fileType);
            countQuery.setParameter(0, fileSourceID);
            countQuery.setParameter(1, fileType);
        } else {
            return new GridResult(0, null);
        }
        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List lstResult = query.list();
        GridResult gr = new GridResult(total, lstResult);
        return gr;
    }

    /**
     * Tim ho so de phan cong tham dinh
     *
     * @param form
     * @param deptId
     * @param userId
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findAllFileForAssignEvaluation(FilesForm form, Long deptId, Long userId, int start, int count, String sortField) {
        try {
            String hql = " from FilesNoClob f, Process p, Category c"
                    + " where f.isActive=1"
                    + " and f.fileId = p.objectId"
                    + " and f.productTypeId = c.categoryId"
                    + " and p.objectType = ?"
                    + " and p.isActive = 1"
                    + " and (f.isTemp is null or f.isTemp = 0 ) ";
            //String hql = "from (select * from FilesNoClob where fileId in (select fileId from FilesNoClob f, Process p where f.isActive=1 and f.fileId = p.objectId and p.objectType = 30 and p.isActive = 1";
            /*
             *Hiepvv 13/01/16
             *Phan cong cong viec cho ho so sua doi sau cong bo
             */
            if (form != null && form.getFileType() != null && form.getFileType().longValue() != -1) {
                ProcedureDAOHE pdaohe = new ProcedureDAOHE();
                Procedure p = new Procedure();
                p = pdaohe.getProcedureById(form.getFileType());
                if (p != null && p.getDescription().equals("announcementFile05")) {
                    hql = " from FilesNoClob f, Process p "
                            + " where f.isActive=1"
                            + " and f.fileId = p.objectId"
                            + " and p.objectType = ?"
                            + " and p.isActive = 1"
                            + " and (f.isTemp is null or f.isTemp = 0 ) ";
                }
                if (p != null && p.getDescription().equals("announcement4star")) {
                    hql = " from FilesNoClob f, Process p "
                            + " where f.isActive=1"
                            + " and f.fileId = p.objectId"
                            + " and p.objectType = ?"
                            + " and p.isActive = 1"
                            + " and (f.isTemp is null or f.isTemp = 0 ) ";
                }
            }
            List lstParam = new ArrayList();
            lstParam.add(Constants.OBJECT_TYPE.FILES);
            if (form != null) {
                if (form.getStatus() == null) {
//                hql += " and (f.status = ? or f.status = ? or f.status = ? or f.status = ?)";
//                hql += " and (f.status = ? or f.status = ?)";
                    hql += " and (f.status = ?)";
//                lstParam.add(Constants.FILE_STATUS.NEW);
                    lstParam.add(Constants.FILE_STATUS.RECEIVED);
                    //lstParam.add(Constants.FILE_STATUS.PROPOSED);
                    //lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                } else {
                    hql += " and (f.status = ?)";
//                hql += " and (f.status = ? or f.status = ?)";
//                lstParam.add(Constants.FILE_STATUS.NEW);
                    lstParam.add(form.getStatus());
//                lstParam.add(Constants.FILE_STATUS.PROPOSED);
                    //lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                }
//binhnt53 add 160317
                if (form.getBusinessName() != null && !"".equals(form.getBusinessName().trim())) {
                    hql += " AND lower(f.businessName) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getBusinessName().toLowerCase().trim()));
                }

                if (form.getProductName() != null && form.getProductName().length() > 0) {
                    hql += " AND lower(f.productName) like ? ESCAPE '/'";
                    lstParam.add(StringUtils.toLikeString(form.getProductName().toLowerCase().trim()));
                }
//!binhnt53 add 160317
                if (form.getFileCode() != null && !"".equals(form.getFileCode().trim())) {
                    hql += "AND lower(f.fileCode) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getFileCode().toLowerCase().trim()));
                }
                if (form.getFileType() != null && form.getFileType().longValue() != -1) {
                    hql += "AND f.fileType = ? ";
                    lstParam.add(form.getFileType());
                }
                if (form.getSendDateFrom() != null) {
                    hql += "AND f.sendDate >= ? ";
                    lstParam.add(form.getSendDateFrom());
                }
                if (form.getSendDateTo() != null) {
                    hql += "AND f.sendDate <= ? ";
                    lstParam.add(form.getSendDateTo());
                }
                if (deptId != null) {
//                hql += "AND (f.agencyId = ? or p.receiveUserId = ? or p.receiveGroupId=? ) and (p.processStatus=? or p.processStatus=? or p.processStatus =? or p.processStatus =? ) ";
                    hql += "AND (f.agencyId = ? or p.receiveUserId = ? or p.receiveGroupId=? ) and (p.processStatus=? or p.processStatus=? or p.processStatus =? ) ";

                    lstParam.add(deptId);
                    lstParam.add(userId);
                    lstParam.add(deptId);
                    lstParam.add(Constants.FILE_STATUS.NEW);
                    lstParam.add(Constants.FILE_STATUS.RECEIVED);
                    lstParam.add(Constants.FILE_STATUS.PROPOSED);
                    //lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                } else {
//                hql += "AND (p.receiveUserId = ? and (p.processStatus=? or p.processStatus=? or p.processStatus =? )and p.status=? ) ";
                    hql += "AND (p.receiveUserId = ? and (p.processStatus=?) and p.status=? ) ";
                    lstParam.add(userId);
                    //lstParam.add(Constants.FILE_STATUS.NEW);
                    lstParam.add(Constants.FILE_STATUS.RECEIVED);
                    //lstParam.add(Constants.FILE_STATUS.PROPOSED);
                    lstParam.add(0l);
                }
            }

//            Query queryCheckFileId = getSession().createQuery("select f.fileId " + hql + "order by f.sendDate desc)");
//            // hieptq 181114 tach nhom san pham
//            for (int i = 0; i < lstParam.size(); i++) {
//                queryCheckFileId.setParameter(i, lstParam.get(i));
//            }
//            // loc fileId all
//            List<Long> lstResultCheckFileId = new ArrayList<Long>();
//            lstResultCheckFileId = queryCheckFileId.list();
//            String lstFileId = "";
//            for (int i = 0; i < lstResultCheckFileId.size(); i++) {
//                if (i == (lstResultCheckFileId.size() - 1)) {
//                    lstFileId += lstResultCheckFileId.get(i).toString();
//                } else {
//                    lstFileId += lstResultCheckFileId.get(i).toString() + ",";
//                }
//            }
//            if ("".equals(lstFileId)) {
//                lstFileId = "0";
//            }
            // loc file_id nhom san pham khac
            if (form.getProductTypeNew() != null && form.getProductTypeNew().longValue() != -1) {
                if (form.getProductTypeNew() == 1) {
//                    Query hql_1 = getSession().createQuery("select fpi.fileId from FeePaymentInfo fpi where fpi.fileId in (" + lstFileId + ") and fpi.cost = 500000 ");
//                    List<Long> lsthql_1 = new ArrayList<Long>();
//                    lsthql_1 = hql_1.list();
//                    String hql1_FileId = "";
//                    for (int i = 0; i < lsthql_1.size(); i++) {
//                        if (i == (lsthql_1.size() - 1)) {
//                            hql1_FileId += lsthql_1.get(i).toString();
//                        } else {
//                            hql1_FileId += lsthql_1.get(i).toString() + ",";
//                        }
//                    }
//                    if ("".equals(hql1_FileId)) {
//                        hql1_FileId = "0";
//                    }
                    // hql += "AND f.fileId in (" + hql1_FileId + ")";
                    hql += " and (c.code <> ? and c.code <> ?)";
                    lstParam.add("TPCN");
                    lstParam.add("DBT");
                } else {
//                    Query hql_1 = getSession().createQuery("select fpi.fileId from FeePaymentInfo fpi where fpi.fileId in (" + lstFileId + ") and fpi.cost = 1500000 ");
//                    List<Long> lsthql_1 = new ArrayList<Long>();
//                    lsthql_1 = hql_1.list();
//                    String hql1_FileId = "";
//                    for (int i = 0; i < lsthql_1.size(); i++) {
//                        if (i == (lsthql_1.size() - 1)) {
//                            hql1_FileId += lsthql_1.get(i).toString();
//                        } else {
//                            hql1_FileId += lsthql_1.get(i).toString() + ",";
//                        }
//                    }
//                    if ("".equals(hql1_FileId)) {
//                        hql1_FileId = "0";
//                    }
                    //hql += "AND f.fileId in (" + hql1_FileId + ")";
                    hql += " and (c.code = ? or c.code = ?)";
                    lstParam.add("TPCN");
                    lstParam.add("DBT");
                }
            }

            // hieptq update 251114
            if (form.getSearchTypeNew() != null) {
                if (form.getSearchTypeNew() == 1) {
//                    Query hql_1 = getSession().createQuery("select fpi.fileId from FeePaymentInfo fpi where fpi.fileId in (" + lstFileId + ") and fpi.cost = 500000 ");
//                    List<Long> lsthql_1 = new ArrayList<Long>();
//                    lsthql_1 = hql_1.list();
//                    String hql1_FileId = "";
//                    for (int i = 0; i < lsthql_1.size(); i++) {
//                        if (i == (lsthql_1.size() - 1)) {
//                            hql1_FileId += lsthql_1.get(i).toString();
//                        } else {
//                            hql1_FileId += lsthql_1.get(i).toString() + ",";
//                        }
//                    }
//                    if ("".equals(hql1_FileId)) {
//                        hql1_FileId = "0";
//                    }
                    hql += " and (c.code <> ? and c.code <> ?)";
                    lstParam.add("TPCN");
                    lstParam.add("DBT");
                } else {
//                    Query hql_1 = getSession().createQuery("select fpi.fileId from FeePaymentInfo fpi where fpi.fileId in (" + lstFileId + ") and fpi.cost = 1500000 ");
//                    List<Long> lsthql_1 = new ArrayList<Long>();
//                    lsthql_1 = hql_1.list();
//                    String hql1_FileId = "";
//                    for (int i = 0; i < lsthql_1.size(); i++) {
//                        if (i == (lsthql_1.size() - 1)) {
//                            hql1_FileId += lsthql_1.get(i).toString();
//                        } else {
//                            hql1_FileId += lsthql_1.get(i).toString() + ",";
//                        }
//                    }
//                    if ("".equals(hql1_FileId)) {
//                        hql1_FileId = "0";
//                    }
                    hql += " and (c.code = ? or c.code = ?)";
                    lstParam.add("TPCN");
                    lstParam.add("DBT");
                }
            }

            Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql);
            Query query = getSession().createQuery("select distinct f " + hql + "order by f.modifyDate DESC)");
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }

            query.setFirstResult(start);
            query.setMaxResults(count);
            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            List<FilesNoClob> lstResult = query.list();
            /*
             for (FilesNoClob f : lstResult) {
             hql = "select p from Process p where p.objectType=? and p.objectId = ? and (p.processStatus=? or p.processType=? ) ";
             query = getSession().createQuery(hql);
             query.setParameter(0, Constants.OBJECT_TYPE.FILES);
             query.setParameter(1, f.getFileId());
             query.setParameter(2, Constants.FILE_STATUS.ASSIGNED);
             query.setParameter(3, Constants.PROCESS_TYPE.PROPOSE);
             //                query.setParameter(3, Constants.PROCESS_TYPE.MAIN);
             List<Process> lstPro = query.list();
             if (lstPro.size() > 0) {
             StringBuilder processName = new StringBuilder("");
             for (int i = 0; i < lstPro.size(); i++) {
             if (lstPro.get(i).getReceiveUser() != null) {
             if (processName.length() == 0) {
             processName.append(lstPro.get(i).getReceiveUser());
             } else {
             processName.append(",").append(lstPro.get(i).getReceiveUser());
             }
             }
             }
             f.setProposeUserName(processName.toString());
             }
             }
             */
            GridResult gr = new GridResult(total, lstResult);
            return gr;
        } catch (Exception e) {
            log.error(e);
            return new GridResult(0, null);
        }
    }

    /**
     * Tim ho so de phan cong tham dinh
     *
     * @param form
     * @param deptId
     * @param userId
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findAllFileForReAssignEvaluation(FilesForm form, Long deptId, Long userId, int start, int count, String sortField) {
        try {
            String hql = " from FilesNoClob f, Process p, Category c"
                    + " where f.isActive=1"
                    + " and f.fileId = p.objectId"
                    + " and f.productTypeId = c.categoryId"
                    + " and p.objectType = ?"
                    + " and p.isActive = 1"
                    + " and (f.isTemp = null or f.isTemp = 0 ) ";
            List lstParam = new ArrayList();
            lstParam.add(Constants.OBJECT_TYPE.FILES);
            if (form != null) {
                if (form.getStatus() == null) {
                    hql += " and (f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                } else {
                    hql += " and (f.status = ?)";
                    lstParam.add(form.getStatus());
                }
                if (form.getBusinessName() != null && !"".equals(form.getBusinessName().trim())) {
                    hql += " AND lower(f.businessName) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getBusinessName().toLowerCase().trim()));
                }

                if (form.getProductName() != null && form.getProductName().length() > 0) {
                    hql += " AND lower(f.productName) like ? ESCAPE '/'";
                    lstParam.add(StringUtils.toLikeString(form.getProductName().toLowerCase().trim()));
                }
                if (form.getFileCode() != null && !"".equals(form.getFileCode().trim())) {
                    hql += "AND lower(f.fileCode) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getFileCode().toLowerCase().trim()));
                }
                if (form.getFileType() != null && form.getFileType().longValue() != -1) {
                    hql += "AND f.fileType = ? ";
                    lstParam.add(form.getFileType());
                }
                if (form.getSendDateFrom() != null) {
                    hql += "AND f.sendDate >= ? ";
                    lstParam.add(form.getSendDateFrom());
                }
                if (form.getSendDateTo() != null) {
                    hql += "AND f.sendDate <= ? ";
                    lstParam.add(form.getSendDateTo());
                }
                if (deptId != null) {
                    hql += "AND (f.agencyId = ? or p.receiveUserId = ? or p.receiveGroupId=? ) and (p.processStatus=? ) ";
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    lstParam.add(deptId);
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                } else {
                    hql += "AND (p.receiveUserId = ? and (p.processStatus=?) and p.status=? ) ";
                    lstParam.add(userId);
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    lstParam.add(0l);
                }
            }
            if (form.getProductTypeNew() != null && form.getProductTypeNew().longValue() != -1) {
                if (form.getProductTypeNew() == 1) {
                    hql += " and (c.code <> ? and c.code <> ?)";
                    lstParam.add("TPCN");
                    lstParam.add("DBT");
                } else {
                    hql += " and (c.code = ? or c.code = ?)";
                    lstParam.add("TPCN");
                    lstParam.add("DBT");
                }
            }
            if (form.getSearchTypeNew() != null) {
                if (form.getSearchTypeNew() == 1) {
                    hql += " and (c.code <> ? and c.code <> ?)";
                    lstParam.add("TPCN");
                    lstParam.add("DBT");
                } else {
                    hql += " and (c.code = ? or c.code = ?)";
                    lstParam.add("TPCN");
                    lstParam.add("DBT");
                }
            }

            Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql);
            Query query = getSession().createQuery("select distinct f " + hql + "order by f.modifyDate DESC)");
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }

            query.setFirstResult(start);
            query.setMaxResults(count);
            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            List<FilesNoClob> lstResult = query.list();
            GridResult gr = new GridResult(total, lstResult);
            return gr;
        } catch (Exception e) {
            log.error(e);
            return new GridResult(0, null);
        }
    }

    /**
     * Tìm hồ sơ để đề xuất thẩm định
     *
     * @param form
     * @param deptId
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findAllFileForProposeEvaluation(FilesForm form, Long deptId, int start, int count, String sortField) {
        try {
            String hql = " from FilesNoClob f where f.isActive=1 and (f.status=?) and (f.isTemp = null or f.isTemp = 0 ) ";
            List lstParam = new ArrayList();
//        lstParam.add(Constants.FILE_STATUS.RECEIVED);// ho sơ vừa tiếp nhận
//        lstParam.add(Constants.FILE_STATUS.PROPOSED);
            lstParam.add(Constants.FILE_STATUS.RECEIVED);
            //140404
//        lstParam.add(Constants.FILE_STATUS.REVIEWED_TO_ADD);
            //!140404
            if (form != null) {
                if (form.getFileCode() != null && !"".equals(form.getFileCode().trim())) {
                    hql += "AND lower(f.fileCode) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getFileCode().trim().toLowerCase()));
                }
                if (form.getFileType() != null && form.getFileType().longValue() != -1) {
                    hql += "AND f.fileType = ? ";
                    lstParam.add(form.getFileType());
                }
                if (form.getSendDateFrom() != null) {
                    hql += "AND f.sendDate >= ? ";
                    lstParam.add(form.getSendDateFrom());
                }
                if (form.getSendDateTo() != null) {
                    hql += "AND f.sendDate <= ? ";
                    lstParam.add(form.getSendDateTo());
                }

                if (deptId != null) {
                    hql += "AND f.agencyId = ? ";
                    lstParam.add(deptId);
                }
            }
            Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql);
            Query query = getSession().createQuery("select distinct f " + hql + " order by f.modifyDate desc ");
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }

            query.setFirstResult(start);
            query.setMaxResults(count);
            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            List lstResult = query.list();
            GridResult gr = new GridResult(total, lstResult);
            return gr;
        } catch (Exception e) {
            log.error(e);
            return new GridResult(0, null);
        }
    }

    /**
     * tìm các hồ sơ để xử lý
     *
     * @param form
     * @param deptId
     * @param userId
     * @param searchType
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult searchFilesToProcess(FilesForm form, Long deptId, Long userId, Long searchType, int start, int count, String sortField) {
        GridResult gr;
        try {
            String hql = " from FilesNoClob f, Process p"
                    + " where f.isActive=1"
                    + " and f.fileId = p.objectId"
                    + " and p.objectType = ?"
                    + " and p.isActive = 1"
                    + " and (f.isTemp = null or f.isTemp = 0 ) ";
            List lstParam = new ArrayList();
            lstParam.add(Constants.OBJECT_TYPE.FILES);
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
                //141215u binhnt53
                if (form.getStaff() != null && form.getStaff().length() > 0) {
                    hql += " AND lower(f.nameStaffProcess) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getStaff().toLowerCase().trim()));
                }
                if (form.getNameStaffProcess() != null && form.getNameStaffProcess().length() > 0) {
                    hql += " AND lower(f.nameStaffProcess) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getNameStaffProcess().toLowerCase().trim()));
                }
                //!141215u binhnt53
                if (form.getFileType() != null && form.getFileType().longValue() != -1) {
                    hql += " AND f.fileType = ? ";
                    lstParam.add(form.getFileType());
                }
            }
            switch (Integer.parseInt(searchType.toString())) {
                case 29:
                    //tim de cuc truong phe duyet
                    hql += " and (f.status = ?) and p.receiveGroupId = ?";
                    lstParam.add(Constants.FILE_STATUS.REVIEW_TO_BOSS);
                    lstParam.add(deptId);
                    hql += " and f.agencyId = ?";
                    lstParam.add(deptId);
                    break;
                case 1:
                    // tim de tham dinh
                    if (form.getStatus() != null) {
                        hql += " and (f.status = ?)";
                        lstParam.add(form.getStatus());
                    } else {
                        hql += " and f.status in (?,?,?,?,?)";
                        lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                        lstParam.add(Constants.FILE_STATUS.EVALUATED);
                        lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD);
                        lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
                    }
                    //Hiepvv Tach rieng SDBS sau cong bo
                    if (form != null && (form.getNoteEdit() == null || form.getNoteEdit().isEmpty())) {
                        hql += " and f.fileType NOT IN (select pr.procedureId from Procedure pr where pr.description=?)";
                        lstParam.add("announcementFile05");
                    }
                    //end hiepvv
                    // Chi tim ho so giao cho ca nhan xu ly thoi
                    hql += " and p.receiveGroupId = ?"
                            + " and p.receiveUserId = ?"
                            + " and (p.processStatus=? or p.processStatus =? or p.processStatus =?)"
                            + " and p.status=?"
                            + " and p.processType=?";//binhnt update 141211
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);//binhnt update 141211
                    lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD);
                    lstParam.add(0l);
                    lstParam.add(Constants.PROCESS_TYPE.MAIN);
                    break;
                case 2:
                    // tim de review
                    if (form.getStatus() == null) {
//                        hql += " and (f.status = ? or f.status = ?)";
                        hql += " and (f.status = ? or f.status = ? or f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED);
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_REVIEW);
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                    } else {
                        hql += " and f.status = ? ";
                        lstParam.add(form.getStatus());
                        hql += " and p.receiveGroupId = ?"
                                + " and ( p.receiveUserId = null or p.receiveUserId = ?) ";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                    }
                    if (userId != null) {
                        hql += " and f.leaderReviewId = ?";
                        lstParam.add(userId);
                    }
                    //Hiepvv Tach rieng SDBS sau cong bo
                    if (form != null && (form.getNoteEdit() == null || form.getNoteEdit().isEmpty())) {
                        hql += " and f.fileType NOT IN (select pr.procedureId from Procedure pr where pr.description=?)";
                        lstParam.add("announcementFile05");
                    }
                    //end hiepvv
                    break;
                case 3:
                    // tim de phe duyet
                    hql += " and (f.status = ?)"
                            + " and p.receiveGroupId = ?"
                            + " and f.leaderApproveId = ? ";
                    lstParam.add(Constants.FILE_STATUS.REVIEWED);
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    // Phe duyet cac ho so ban dau gui den cho don vi minh
                    hql += " and f.agencyId = ?";
                    lstParam.add(deptId);
                    //Hiepvv Tach rieng SDBS sau cong bo
                    if (form != null && (form.getNoteEdit() == null || form.getNoteEdit().isEmpty())) {
                        hql += " and f.fileType NOT IN (select pr.procedureId from Procedure pr where pr.description=?)";
                        lstParam.add("announcementFile05");
                    }
                    //end hiepvv
                    break;
                case -3:
                    // tim de phan cong phe duyet
                    hql += " and (f.status = ?)"
                            + " and p.receiveGroupId = ?";
                    lstParam.add(Constants.FILE_STATUS.REVIEWED);
                    lstParam.add(deptId);
                    // Phe duyet cac ho so ban dau gui den cho don vi minh
                    hql += " and f.agencyId = ?";
                    lstParam.add(deptId);
                    break;
                case 26:
                    // tim de phe duyet
                    hql += " and (f.status = ?)"
                            + " and p.receiveGroupId = ?"
                            + " and p.receiveUserId = ? ";
                    lstParam.add(Constants.FILE_STATUS.REVIEW_TO_ADD);
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    // Phe duyet cac ho so ban dau gui den cho don vi minh
                    hql += " and f.agencyId = ?";
                    lstParam.add(deptId);
                    break;
                case 30:
                    hql += " and f.status in(?,?,?,?,?,?,?) and p.receiveGroupId = ? ";
//                    hql += " and (f.status = ? or f.status = ?) and p.receiveGroupId = ? and p.receiveUserId = ? ";
                    lstParam.add(Constants.FILE_STATUS.APPROVED);
                    lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                    lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
                    lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON);
                    lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                    lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);
                    lstParam.add(Constants.FILE_STATUS.COMPARED);
                    lstParam.add(deptId);
                    //lstParam.add(userId);
                    /*
                     //140714 binhnt53
                     if (userId != null) {
                     hql += " and p.sendUserId = ? ";
                     lstParam.add(userId);
                     }//!140714 binhnt53
                     */
                    //
                    // Phe duyet cac ho so ban dau gui den cho don vi minh
                    //
//                hql += " and f.agencyId = ?";
//                lstParam.add(deptId);
//                hql += " and p.receiveGroupId = ? and ( p.receiveUserId = null or p.receiveUserId = ?) and p.processStatus=? and p.status=?";
//                lstParam.add(deptId);
//                lstParam.add(userId);
//                lstParam.add(Constants.FILE_STATUS.REVIEWED);
//                lstParam.add(0l);
                    break;
                case 4:
                    //
                    //
                    //
                    hql += " and f.status in (?,?,?,?)";
                    lstParam.add(Constants.FILE_STATUS.APPROVED);
                    lstParam.add(Constants.FILE_STATUS.SIGNING);
                    lstParam.add(Constants.FILE_STATUS.LICENSING);
                    lstParam.add(Constants.FILE_STATUS.REJECT);
                    //
                    //
                    //
                    hql += " and p.receiveGroupId = ?"
                            + " and p.receiveUserId = ?"
                            + " and p.processStatus=?"
                            + " and p.status=?";
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    lstParam.add(Constants.FILE_STATUS.EVALUATED);
                    break;
                case 5:
                    //
                    //
                    //
                    hql += " and f.status in (?,?,?,?)";
                    lstParam.add(Constants.FILE_STATUS.APPROVED);
                    lstParam.add(Constants.FILE_STATUS.SIGNING);
                    lstParam.add(Constants.FILE_STATUS.LICENSING);
                    lstParam.add(Constants.FILE_STATUS.SIGNED);
                    //
                    //
                    //
                    if (deptId != null) {
                        hql += " AND f.agencyId = ?";
                        lstParam.add(deptId);
                    }
                    break;
                case 6:
                    // Chi tim ho so giao cho ca nhan xu ly thoi!
                    hql += " and f.status in (?,?,?)"
                            + " and p.receiveGroupId =?"
                            + " and p.receiveUserId =?"
                            + " and (p.processStatus=? or p.processStatus=?)"
                            + " and p.status=?"
                            + " and p.processType=?";
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD);
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);

                    lstParam.add(deptId);
                    lstParam.add(userId);
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD);
                    lstParam.add(0l);
                    lstParam.add(Constants.PROCESS_TYPE.COOPERATE);
                    break;
                case 7:
                    //
                    // tim de thong bao ket qua tham dinh
                    //
                    if (form.getStatus() != null) {
                        hql += " and (f.status = ?)";
                        lstParam.add(form.getStatus());
                    } else {
                        hql += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.REVIEWED_TO_ADD);
                    }
                    //
                    // Chi tim ho so giao cho ca nhan xu ly thoi
                    //
                    hql += " and p.receiveGroupId = ?"
                            + " and p.receiveUserId = ?"
                            + " and (p.processStatus=? or p.processStatus =? )"
                            + " and p.status=?"
                            + " and p.processType=?";
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    lstParam.add(Constants.FILE_STATUS.REVIEWED_TO_ADD);
                    lstParam.add(0l);
                    lstParam.add(Constants.PROCESS_TYPE.MAIN);
                    break;
                case 8:// tim de review SDBS //150204 binhnt53 update    
                    hql += " and (f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                    hql += " and p.receiveGroupId = ? and p.receiveUserId = ?";
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    hql += " and (f.leaderReviewId =?)";
                    lstParam.add(userId);
                    /* 150204 binhnt53u 
                     if (form.getStatus() != null) {
                     hql += " and (f.status = ?)";
                     lstParam.add(form.getStatus());
                     } else {
                     hql += " and (f.status = ? or f.status = ?)";
                     lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                     lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                     }
                     // Lanh dao don vi duoc phan cong tham dinh, tim cac ho so duoc giao cho don vi minh tham dinh
                     hql += " and p.receiveGroupId = ? and ( p.receiveUserId = null or p.receiveUserId = ?) and (p.processStatus=? or p.processStatus =? or p.processStatus =?)and p.status=?";
                     lstParam.add(deptId);
                     lstParam.add(userId);
                     lstParam.add(Constants.FILE_STATUS.EVALUATED);
                     lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                     lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                     lstParam.add(0l);
                     */
                    break;
                case 9://tim de pho phong vao tham dinh
                    // tim de tham dinh
                    if (form.getStatus() != null) {
                        hql += " and (f.status = ?)";
                        lstParam.add(form.getStatus());
                    } else {
                        hql += " and (f.status = ? or f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                        lstParam.add(Constants.FILE_STATUS.EVALUATED);
                    }
                    // Chi tim ho so giao cho ca nhan xu ly thoi
                    hql += " and p.receiveGroupId = ?"
                            + " and p.receiveUserId = ?"
                            + " and (p.processStatus=? or p.processStatus =? )"
                            + " and p.status=?"
                            + " and p.processType=?";
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    lstParam.add(Constants.FILE_STATUS.EVALUATED);
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                    lstParam.add(0l);
                    lstParam.add(Constants.PROCESS_TYPE.MAIN);
                    if (userId != null) {
                        hql += " and f.leaderEvaluateId = ?";
                        lstParam.add(userId);
                    }
                    break;
                default:
            }
            Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql);
            Query query = getSession().createQuery("select distinct f " + hql + " order by f.modifyDate asc");
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }

            query.setFirstResult(start);
            query.setMaxResults(count);
            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            List<FilesNoClob> lstResult = query.list();
//            for (FilesNoClob f : lstResult) {
//                hql = "select p from Process p where p.isActive = 1 and p.objectType=? and p.objectId = ? and p.processStatus=? and (p.receiveUserId=? or p.sendUserId=?) order by p.processId ASC";
//                query = getSession().createQuery(hql);
//                query.setParameter(0, Constants.OBJECT_TYPE.FILES);
//                query.setParameter(1, f.getFileId());
//                query.setParameter(2, Constants.FILE_STATUS.ASSIGNED);
//                query.setParameter(3, userId);
//                query.setParameter(4, userId);
//                List<Process> lstPro = query.list();
//                if (lstPro.size() > 0) {
//                    String leaderprocess = "";
//                    String staffprocess = "";
//                    if (lstPro.get(0).getSendUser() != null && lstPro.get(0).getSendUserId() != userId) {
//                        if (lstPro.get(0).getSendUser().length() > 0) {
//                            leaderprocess = lstPro.get(0).getSendUser();
//                        } else {
//                            leaderprocess = "N/A";
//                        }
//                    }
//                    if (lstPro.get(0).getReceiveUser() != null && lstPro.get(0).getReceiveUserId() != userId) {
//                        if (lstPro.get(0).getReceiveUser().length() > 0) {
//                            staffprocess = lstPro.get(0).getReceiveUser();
//                        } else {
//                            staffprocess = "N/A";
//                        }
//                    }
//                    f.setLeaderProcess(leaderprocess);
//                    f.setStaffProcess(staffprocess);
//                }
//            }
            gr = new GridResult(total, lstResult);
            return gr;
        } catch (Exception en) {
            log.error(en.getMessage());
            gr = new GridResult(0, null);
        }
        return gr;
    }

    /**
     * Count hồ sơ cần gửi
     *
     * @param businessId
     * @param status
     * @return
     */
    public int getCountFileToSend(Long businessId, Long status) {
        try {
            String hql = "select count(distinct f.fileId)"
                    + " from FilesNoClob f"
                    + " where f.isActive = 1"
                    + " and f.deptId = ?"
                    + " and (f.isTemp = null or f.isTemp = 0 )"
                    + " and (sysdate - 366  <= f.createDate) ";
            List lstParam = new ArrayList();
            lstParam.add(businessId);
            switch (Integer.parseInt(status.toString())) {
                case -77:
                    lstParam.clear();
                    hql = "select count(distinct f.fileId) from FilesNoClob f"
                            + " where f.isActive = 1"
                            + " and (f.isTemp = null or f.isTemp = 0 )"
                            + " and f.deptId = ? "
                            + " AND f.isFee = ? ";
                    lstParam.clear();
                    lstParam.add(businessId);
                    lstParam.add(Constants.FEE_STATUS.DA_TU_CHOI);
                    break;
                case -100:
                    hql = "select count(distinct f.fileId)"
                            + " from FilesNoClob f, Fee fe, FeePaymentInfo fpi where f.fileId = fpi.fileId  and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                            + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                            + " and fpi.isActive=1"
                            + " and fpi.status not in (2,1)"//u150211 by binhnt //+ " and fpi.status <> 1"
                            + " and f.userSigned is not null"
                            + " and f.status=?"
                            + " and f.deptId = ? ";
                    lstParam.clear();
                    lstParam.add(Constants.FILE_STATUS.NEW);
                    lstParam.add(businessId);
                    break;
                case -6:// (files.status = 1, fee_payment_info.status = 1, fee.fee_type=2)
                    hql = "select count(distinct f.fileId)"
                            + " from FilesNoClob f, Fee fe, FeePaymentInfo fpi where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                            + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                            + " and fpi.isActive=1"
                            + " and (fpi.status=1)"
                            + " and f.userSigned is not null"
                            + " and (f.status=? or f.status=?) and f.deptId = ?";
                    lstParam.clear();
                    lstParam.add(Constants.FILE_STATUS.NEW);
                    lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
                    lstParam.add(businessId);
                    break;
                case -5:// 12- Hồ sơ chờ nộp lệ phí = Đã phê duyệt, chưa nộp lệ phí (files.status = 6, fee_payment_info.status = 0, loại lệ phí)
                    hql = "select count(distinct f.fileId)"
                            + " from FilesNoClob f, Fee fe, FeePaymentInfo fpi where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                            + " and fe.feeId = fpi.feeId and fe.feeType=1 and fe.isActive=1"
                            + " and fpi.isActive=1"
                            + " and (fpi.status=0)"
                            + " and f.userSigned is not null"
                            + " and (f.status=?) and f.deptId = ?";
                    lstParam.clear();
                    lstParam.add(Constants.FILE_STATUS.APPROVED);
                    lstParam.add(businessId);
                    break;

                // ho so cho ke toan xac nhan le phi cap so - hieptq 161214
                case -109:
                    hql = "select count(distinct f.fileId)"
                            + " from FilesNoClob f, Fee fe, FeePaymentInfo fpi where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                            + " and fe.feeId = fpi.feeId and fe.feeType=1 and fe.isActive=1"
                            + " and fpi.isActive = 1"
                            + " and (fpi.status > 1)"
                            + " and f.userSigned is not null"
                            + " and (f.status=?) and f.deptId = ?";
                    lstParam.clear();
                    lstParam.add(Constants.FILE_STATUS.APPROVED);
                    lstParam.add(businessId);
                    break;

                //Hồ sơ văn thư đã trả bản công bố (bản mềm) =  ho so da ky so + dong tien + dong dau so
                case 76:
                    hql = "select count(distinct f.fileId)"
                            + " from FilesNoClob f"
                            + " where  f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)"
                            + " and f.isFee = 1 and f.isSignPdf = 2"
                            + " and f.status in (?,?,?,?,?,?,?)"
                            + " and f.deptId = ?";
                    lstParam.clear();
                    lstParam.add(Constants.FILE_STATUS.APPROVED);
                    lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                    lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
                    lstParam.add(Constants.FILE_STATUS.COMPARED);
                    lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON);
                    lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);
                    lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                    lstParam.add(businessId);
                    break;
                case -4://3- Hồ sơ chờ tiếp nhận =
                    //  + Mới nộp + chờ xác nhận văn thư (files.status = 1, fee_payment_info.status = 3 or 4)
                    //  + Mới nộp SĐBS + chờ xác nhận văn thư(files.status = 18, fee_payment_info.status = 3 or 4)
//                    hql = "select count(distinct f.fileId)"
//                            + " from FilesNoClob f, Fee fe, FeePaymentInfo fpi where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
//                            + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
//                            + " and fpi.isActive=1"
//                            + " and (fpi.status=3 or fpi.status=4)"
//                            + " and f.userSigned is not null"
//                            + " and (f.status=? or f.status=?)"
//                            + " and f.userCreateId = ?";
                    hql = "select count(distinct f.fileId)"
                            + " from FilesNoClob f, Fee fe, FeePaymentInfo fpi "
                            + " where fpi.fileId = f.fileId "
                            + " and f.isActive = 1 "
                            + " and f.userSigned is not null"
                            + " and (f.isTemp is null or f.isTemp = 0) and f.isFee = 1"
                            //                            + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                            + " and (f.status = ?) "
                            + " and fpi.isActive = 1"
                            + " and f.deptId = ?";
                    lstParam.clear();
                    lstParam.add(Constants.FILE_STATUS.NEW);
                    //lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
                    lstParam.add(businessId);
                    break;

                // ho so cho tiep nhan SDBS
                case -7://3- Hồ sơ chờ tiếp nhận =
                    hql = "select count(distinct f.fileId)"
                            + " from FilesNoClob f, Fee fe, FeePaymentInfo fpi "
                            + " where fpi.fileId = f.fileId "
                            + " and f.isActive = 1 "
                            + " and f.userSigned is not null"
                            + " and (f.isTemp is null or f.isTemp = 0) and f.isFee = 1"
                            //                            + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                            + " and (f.status = ?) "
                            + " and fpi.isActive = 1"
                            + " and f.deptId = ?";
                    lstParam.clear();
                    //lstParam.add(Constants.FILE_STATUS.NEW);
                    lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
                    lstParam.add(businessId);
                    break;

                case -3://2- Hồ sơ chưa nộp phí  = Mới tạo, chưa nộp phí, đã ký or upload file ký(files.status = 0, fee_payment_info.status = 0, files.user_signed is not null)
                    hql = "select count(distinct f.fileId)"
                            + " from FilesNoClob f, Fee fe, FeePaymentInfo fpi"
                            + " where f.fileId = fpi.fileId"
                            + " and (f.isTemp = null or f.isTemp = 0)"
                            + " and f.isActive = 1"
                            + " and fe.feeId = fpi.feeId"
                            + " and fe.feeType = 2"
                            + " and fe.isActive = 1"
                            + " and fpi.isActive = 1"
                            + " and f.userSigned is not null"
                            + " and f.status = ?"
                            + " and f.deptId = ?";
                    lstParam.clear();
                    lstParam.add(Constants.FILE_STATUS.NEW_CREATE);
                    lstParam.add(businessId);
                    break;
                case -2://xxxx binhnt53 update 150209
                    hql = "select count(distinct f.fileId)"
                            + " from FilesNoClob f"
                            + " where f.isActive = 1"
                            + " and (f.isTemp = null or f.isTemp = 0 ) "
                            + " and f.status in (?,?,?,?,?,?,?,?,?,?,?)"
                            + " and f.deptId = ? ";
                    lstParam.clear();
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);//4
                    lstParam.add(Constants.FILE_STATUS.EVALUATED);//4
                    lstParam.add(Constants.FILE_STATUS.REVIEWED);//5
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);//7
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);//8
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_REVIEW);//9
                    lstParam.add(Constants.FILE_STATUS.REVIEWED_TO_ADD);//19
                    lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);//16
                    lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON);//24
                    lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);//25
                    lstParam.add(Constants.FILE_STATUS.REVIEW_TO_ADD);//26
//                    lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);//18
//                    lstParam.add(Constants.FILE_STATUS.RECEIVED);//14
//                    lstParam.add(Constants.FILE_STATUS.COMPARED);//15
                    lstParam.add(businessId);
                    break;
                case -1:
                    lstParam.clear();
                    hql = "select count(distinct f.fileId) from FilesNoClob f where f.isActive = 1 and f.deptId = ? and (f.isTemp = null or f.isTemp = 0 ) ";
                    lstParam.add(businessId);
                    hql += "and (f.status = ? or f.status = ?) ";
                    lstParam.add(Constants.FILE_STATUS.NEW);
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                    break;

                case 15:
                    hql += "and (f.status = ?) "
                            + " and f.deptId = ? ";
                    lstParam.add(Constants.FILE_STATUS.COMPARED);
                    lstParam.add(businessId);
                    break;
                case 20:
                    lstParam.clear();
//                    hql = "select count(distinct f.fileId) from FilesNoClob f where f.isActive = 1 and f.deptId = ? and (f.isTemp = null or f.isTemp = 0 ) and (sysdate - 366  <= f.createDate) ";
                    hql = "select count(distinct f.fileId) from FilesNoClob f where f.isActive = 1 and f.deptId = ? and (f.isTemp = null or f.isTemp = 0 )";
                    lstParam.add(businessId);
                    hql += "and (f.status = ?) ";
                    lstParam.add(Constants.FILE_STATUS.EVALUATED_TO_ADD);
                    break;
                case 14:
                    lstParam.clear();
                    hql = "select count(distinct f.fileId) from FilesNoClob f where f.isActive = 1 and f.deptId = ? and (f.isTemp = null or f.isTemp = 0 ) and (sysdate - 366  <= f.createDate) ";
                    lstParam.add(businessId);
                    hql += "and (f.status = ?) ";
                    lstParam.add(Constants.FILE_STATUS.RECEIVED);
                    break;
                case 23:
                    lstParam.clear();
                    hql = "select count(distinct f.fileId) from FilesNoClob f where f.isActive = 1 and f.deptId = ? and (f.isTemp = null or f.isTemp = 0 ) and (sysdate - 366  <= f.createDate) ";
                    lstParam.add(businessId);
                    hql += "and (f.status = ?) ";
                    lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
                    break;
                case 0:
                    lstParam.clear();
                    hql = "select count(distinct f.fileId) from FilesNoClob f"
                            + " where f.isActive = 1"
                            + " AND (f.isTemp = null or f.isTemp = 0 )"
                            + " AND (sysdate - 366  <= f.createDate) "
                            + " AND f.userSigned = null"
                            + " AND f.deptId = ?"
                            + " AND (f.status = ? or f.status = ?)";
                    lstParam.clear();
                    lstParam.add(businessId);
                    lstParam.add(Constants.FILE_STATUS.NEW_CREATE);
                    lstParam.add(Constants.FILE_STATUS.NEW);
                    break;
                case 33:
                    lstParam.clear();
                    hql = "select count(distinct f.fileId)"
                            + " from FilesNoClob f"
                            + " where f.isActive = 1"
                            + " AND f.deptId = ?"
                            + " AND (f.isTemp = null or f.isTemp = 0 )"
                            + " AND (sysdate - 366  <= f.createDate)"
                            + " AND (f.status = ? or f.status = ?)";
                    lstParam.clear();
                    lstParam.add(businessId);
                    lstParam.add(Constants.FILE_STATUS.RECEIVED_REJECT);
                    lstParam.add(Constants.FILE_STATUS.RECEIVED_REJECT_TO_ADD);
                    break;
                default:
                    hql += "and f.status = ? "
                            + " and f.deptId = ? ";
                    lstParam.add(status);
                    lstParam.add(businessId);
                    break;
            }
            Query query = getSession().createQuery(hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
            }
            int total = Integer.parseInt(query.list().get(0).toString());
            return total;
        } catch (NumberFormatException | HibernateException e) {
            log.error(e);
            return 0;
        }
    }

    /**
     * Count hồ sơ cần đề xuất
     *
     * @param userId
     * @param deptId
     * @return
     */
    public int getCountFileToPropose(Long userId, Long deptId) {
        try {
            String hql = "select count(distinct f.fileId) from FilesNoClob f where f.isActive=1 and (f.status=? or f.status=? ) and f.agencyId = ? and (f.isTemp = null or f.isTemp = 0 ) ";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, Constants.FILE_STATUS.NEW);//hồ sơ văn thư đã tiếp nhận
            query.setParameter(1, Constants.FILE_STATUS.NEW_TO_ADD);
            query.setParameter(2, deptId);
            int total = Integer.parseInt(query.list().get(0).toString());
            return total;
        } catch (Exception e) {
            log.error(e);
            return 0;
        }
    }

    /**
     * count hồ sơ cần phân công
     *
     * @param userId
     * @param deptId
     * @return
     */
    public int getCountFileToAssign(Long userId, Long deptId) {
        try {
            String hql = "select count(distinct f.fileId) from FilesNoClob f"
                    + " where f.isActive=1"
                    + " and f.status in (?,?,?)"
                    + " and f.agencyId = ?"
                    + " and (f.isTemp = null or f.isTemp = 0 ) ";
            Query query = getSession().createQuery(hql);
            query.setParameter(0, Constants.FILE_STATUS.RECEIVED);
            query.setParameter(1, Constants.FILE_STATUS.PROPOSED);
            query.setParameter(2, Constants.FILE_STATUS.NEW);
            query.setParameter(3, deptId);
            int total = Integer.parseInt(query.list().get(0).toString());
            return total;
        } catch (HibernateException | NumberFormatException e) {
            log.error(e);
            return 0;
        }
    }

    /**
     * Count Hồ sơ cần thẩm định
     *
     * @param userId
     * @param deptId
     * @param searchType
     * @param status
     * @return
     */
    public int getCountFileToProcess(Long userId, Long deptId, Long searchType, Long status) {//
        try {
            String hql = " from FilesNoClob f, Process p"
                    + " where f.isActive=1"
                    + " and f.fileId = p.objectId"
                    + " and p.objectType = ?"
                    + " and (f.isTemp = null or f.isTemp = 0 ) ";
            List lstParam = new ArrayList();
            lstParam.add(Constants.OBJECT_TYPE.FILES);
            switch (Integer.parseInt(searchType.toString())) {
                case -29:
                    //tim de cuc truong phe duyet
                    hql += " and (f.status = ?) and p.receiveGroupId = ?";
                    lstParam.add(Constants.FILE_STATUS.REVIEW_TO_BOSS);
                    lstParam.add(deptId);
                    hql += " and f.agencyId = ?";
                    lstParam.add(deptId);
                    break;
                case -23:// tim de phan cong phe duyet
                    hql += " and (f.status = ?) and p.receiveGroupId = ?";
                    lstParam.add(Constants.FILE_STATUS.REVIEWED);
                    lstParam.add(deptId);
                    // Phe duyet cac ho so ban dau gui den cho don vi minh
                    hql += " and f.agencyId = ?";
                    lstParam.add(deptId);
                    break;
                case 1:// tim de tham dinh
                    hql += " and (f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    // Chi tim ho so giao cho ca nhan xu ly thoi
                    hql += " and p.receiveGroupId = ? and p.receiveUserId = ?";
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    break;
                case -20://Ho so da gui thong bao sdbs cho doanh nghiep
                    lstParam.clear();
                    hql = "from FilesNoClob f"
                            + " where f.isActive=1"
                            + " and (f.status = ?)"
                            + " and f.staffProcess=?"
                            + " and (f.isTemp = null or f.isTemp = 0 ) ";
                    lstParam.add(Constants.FILE_STATUS.EVALUATED_TO_ADD);
                    lstParam.add(userId);
                    break;
                case 39:// ho so cho xem xét
                    lstParam.clear();
                    hql = "from FilesNoClob f"
                            + " where f.isActive=1"
                            + " and (f.status = ?)"
                            + " and f.leaderReviewId=?"
                            + " and (f.isTemp = null or f.isTemp = 0 ) ";
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_REVIEW);
                    lstParam.add(userId);
                    break;
                case 447:// ho so cho xem xét
                    lstParam.clear();
                    hql = "from FilesNoClob f"
                            + " where f.isActive=1"
                            + " and (f.status = ?)"
                            + " and f.staffProcess=?"
                            + " and (f.isTemp = null or f.isTemp = 0 ) ";
                    lstParam.add(Constants.FILE_STATUS.EVALUATED);
                    lstParam.add(userId);
                    break;
                case 448:// ho so cho xem xét
                    lstParam.clear();
                    hql = "from FilesNoClob f"
                            + " where f.isActive=1"
                            + " and (f.status = ?)"
                            + " and f.staffProcess=?"
                            + " and (f.isTemp = null or f.isTemp = 0 ) ";
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                    lstParam.add(userId);
                    break;
                case 1623:// hồ sơ cần đối chiếu, stt = đã thông báo đối chiếu or đối chiếu có sai lệch
                    if (status == null) {
                        hql += " and (f.status = ?)";
                        //lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                        lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
                    }
                    if (deptId != null) {
                        hql += " and p.receiveGroupId = ? ";
                        lstParam.add(deptId);
                    }
//                    if (userId != null) {
//                        hql += " and p.receiveUserId = ? ";
//                        lstParam.add(userId);
//                    }
                    break;
                case 5://Hồ sơ đã trình, chờ lãnh đạo cục phê duyệt
                    lstParam.clear();
                    hql = "from FilesNoClob f, Process p where f.isActive = 1 and f.fileId = p.objectId AND f.status = ?  and (f.isTemp = null or f.isTemp = 0 ) ";
                    lstParam.add(Constants.FILE_STATUS.REVIEWED);
                    hql += " and p.receiveGroupId = ? ";
                    lstParam.add(deptId);
                    hql += " and p.receiveUserId = ? ";
                    lstParam.add(userId);
                    break;
                case -22://Hồ sơ đã cấp giấy chứng nhận
                    if (status == null) {
                        hql += " and f.status = ?";
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                    } else {
                        hql += " and f.status = ?";
                        lstParam.add(status);
                    }
                    break;
                case -3:/*Ho so da phan cong*/

                    hql += " and (f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    if (deptId != null) {
                        hql += " AND (f.agencyId = ? or p.receiveUserId = ? or p.receiveGroupId=? ) and (p.processStatus=?) ";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                        lstParam.add(deptId);
                        lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    } else {
                        hql += "AND (p.receiveUserId = ? and (p.processStatus=?) and p.status=? ) ";
                        lstParam.add(userId);
                        lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                        lstParam.add(0l);
                    }
                    break;

//                case 0:
//                    hql += " and (f.status = ?)";
//                    lstParam.add(Constants.FILE_STATUS.RECEIVED);
//                    if (deptId != null) {
////                    hql += " AND (f.agencyId = ? or p.receiveUserId = ? or p.receiveGroupId=? ) and (p.processStatus=? or p.processStatus=? or p.processStatus =? or p.processStatus =? ) ";
//                        hql += " AND (f.agencyId = ? or p.receiveUserId = ? or p.receiveGroupId=? ) and (p.processStatus=? or p.processStatus=? or p.processStatus =? ) ";
//                        lstParam.add(deptId);
//                        lstParam.add(userId);
//                        lstParam.add(deptId);
//                        lstParam.add(Constants.FILE_STATUS.NEW);
//                        lstParam.add(Constants.FILE_STATUS.RECEIVED);
//                        lstParam.add(Constants.FILE_STATUS.PROPOSED);
//                        //lstParam.add(Constants.FILE_STATUS.ASSIGNED);
//                    } else {
//                        hql += "AND (p.receiveUserId = ? and (p.processStatus=? or p.processStatus=? or p.processStatus =? )and p.status=? ) ";
////                hql += "AND (p.receiveUserId = ? and (p.processStatus=? or p.processStatus=? )and p.status=? ) ";
//                        lstParam.add(userId);
//                        lstParam.add(Constants.FILE_STATUS.NEW);
//                        lstParam.add(Constants.FILE_STATUS.RECEIVED);
//                        lstParam.add(Constants.FILE_STATUS.PROPOSED);
//                        lstParam.add(0l);
//                    }
//                    break;
                //hieptq update 191114 dem thuc pham thuong
                case 211:
                    hql = "from FilesNoClob f, Process p, Category c where f.isActive=1 and f.fileId = p.objectId and f.productTypeId = c.categoryId  and p.objectType = ? and p.isActive = 1 and (f.isTemp = null or f.isTemp = 0 )  and (f.status = ?)AND (f.agencyId = ? or p.receiveUserId = ? or p.receiveGroupId=? ) and (p.processStatus=? or p.processStatus=? or p.processStatus =? ) "
                            + " and (c.code <> ? and c.code <> ?) ";
                    lstParam.clear();
                    lstParam.add(30l);
                    lstParam.add(14l);
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    lstParam.add(deptId);
                    lstParam.add(1l);
                    lstParam.add(14l);
                    lstParam.add(2l);
                    lstParam.add("TPCN");
                    lstParam.add("DBT");

//                    Query queryCheckFileId = getSession().createQuery("select f.fileId " + hql + "order by f.sendDate desc");
//                    // hieptq 181114 tach nhom san pham
//                    for (int i = 0; i < lstParam.size(); i++) {
//                        queryCheckFileId.setParameter(i, lstParam.get(i));
//                    }
//                    // loc fileId all
//                    List<Long> lstResultCheckFileId = new ArrayList<Long>();
//                    lstResultCheckFileId = queryCheckFileId.list();
//                    String lstFileId = "";
//                    for (int i = 0; i < lstResultCheckFileId.size(); i++) {
//                        if (i == (lstResultCheckFileId.size() - 1)) {
//                            lstFileId += lstResultCheckFileId.get(i).toString();
//                        } else {
//                            lstFileId += lstResultCheckFileId.get(i).toString() + ",";
//                        }
//                    }
//
//                    // loc file_id nhom san pham thuong
//                    Query hql_1 = getSession().createQuery("select fpi.fileId from FeePaymentInfo fpi where fpi.fileId in (" + lstFileId + ") and fpi.cost = 500000 ");
//                    List<Long> lsthql_1 = new ArrayList<Long>();
//                    lsthql_1 = hql_1.list();
//                    String hql1_FileId = "";
//                    for (int i = 0; i < lsthql_1.size(); i++) {
//                        if (i == (lsthql_1.size() - 1)) {
//                            hql1_FileId += lsthql_1.get(i).toString();
//                        } else {
//                            hql1_FileId += lsthql_1.get(i).toString() + ",";
//                        }
//                    }
//                    if ("".equals(hql1_FileId)) {
//                        hql1_FileId = "0";
//                    }
//                    hql += "AND f.fileId in (" + hql1_FileId + ")";// ịn (1,2,3,4,5)
                    break;

                case 212:
                    hql = "from FilesNoClob f, Process p, Category c where f.isActive=1 and f.fileId = p.objectId and f.productTypeId = c.categoryId and p.objectType = ? and p.isActive = 1 and (f.isTemp = null or f.isTemp = 0 )  and (f.status = ?)AND (f.agencyId = ? or p.receiveUserId = ? or p.receiveGroupId=? ) and (p.processStatus=? or p.processStatus=? or p.processStatus =? )"
                            + " and (c.code = ? or c.code = ? )";
                    lstParam.clear();
                    lstParam.add(30l);
                    lstParam.add(14l);
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    lstParam.add(deptId);
                    lstParam.add(1l);
                    lstParam.add(14l);
                    lstParam.add(2l);
                    lstParam.add("TPCN");
                    lstParam.add("DBT");
//                    Query queryCheckFileId2 = getSession().createQuery("select f.fileId " + hql + "order by f.sendDate desc");
//                    // hieptq 181114 tach nhom san pham
//                    for (int i = 0; i < lstParam.size(); i++) {
//                        queryCheckFileId2.setParameter(i, lstParam.get(i));
//                    }
//                    // loc fileId all
//                    List<Long> lstResultCheckFileId2 = new ArrayList<Long>();
//                    lstResultCheckFileId2 = queryCheckFileId2.list();
//                    String lstFileId2 = "";
//                    for (int i = 0; i < lstResultCheckFileId2.size(); i++) {
//                        if (i == (lstResultCheckFileId2.size() - 1)) {
//                            lstFileId2 += lstResultCheckFileId2.get(i).toString();
//                        } else {
//                            lstFileId2 += lstResultCheckFileId2.get(i).toString() + ",";
//                        }
//                    }
//                    Query hql_2 = getSession().createQuery("select fpi.fileId from FeePaymentInfo fpi where fpi.fileId in (" + lstFileId2 + ") and fpi.cost = 1500000 ");
//                    List<Long> lsthql_2 = new ArrayList<Long>();
//                    lsthql_2 = hql_2.list();
//                    String hql1_FileId2 = "";
//                    for (int i = 0; i < lsthql_2.size(); i++) {
//                        if (i == (lsthql_2.size() - 1)) {
//                            hql1_FileId2 += lsthql_2.get(i).toString();
//                        } else {
//                            hql1_FileId2 += lsthql_2.get(i).toString() + ",";
//                        }
//                    }
//                    if ("".equals(hql1_FileId2)) {
//                        hql1_FileId2 = "0";
//                    }
//                    hql += "AND f.fileId in (" + hql1_FileId2 + ")";
                    break;

                case 4:
                    // tim de phoi hop tham dinh
                    if (status == null) {
                        hql += " and (f.status = ? or f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                    } else {
                        hql += " and f.status = ? ";
                        lstParam.add(status);
                    }
                    //
                    // Chi tim ho so giao cho ca nhan xu ly thoi
                    //
                    hql += " and p.receiveGroupId = ?"
                            + " and p.receiveUserId = ?"
                            + " and (p.processStatus=? or p.processStatus =? )"
                            + " and p.status=?"
                            + " and p.processType = ? ";
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                    lstParam.add(0l);
                    lstParam.add(Constants.PROCESS_TYPE.COOPERATE);
                    break;
                case -1:
                    // tim de tiep nhan
                    if (status == null) {
                        hql += " and f.status in (?,?)";
                        lstParam.add(Constants.FILE_STATUS.NEW);
                        lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
                    } else {
                        hql += " and f.status = ? ";
                        lstParam.add(status);
                    }
                    if (deptId != null) {
                        hql += "AND f.agencyId = ? ";
                        lstParam.add(deptId);
                    }
                    break;
                case 42:
                    lstParam.clear();
                    hql = "from FilesNoClob f"
                            + " where f.isActive=1 "
                            + " and (f.isTemp = null or f.isTemp = 0 ) "
                            + " and (f.status = ?)"
                            + " and f.staffProcess=?";
                    lstParam.add(Constants.FILE_STATUS.EVALUATED);
                    lstParam.add(userId);
                    break;
                case 2://
                    // tim de review
                    //
                    hql += " and (f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.EVALUATED);
//                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_REVIEW);
//                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                    hql += " and p.receiveGroupId = ?"
                            + " and p.receiveUserId = ?";
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    hql += " and ((f.leaderEvaluateId = ? and f.leaderReviewId = null)"
                            + " or f.leaderReviewId =?)";
                    lstParam.add(userId);
                    lstParam.add(userId);
                    // Lanh dao don vi duoc phan cong tham dinh, tim cac ho so duoc giao cho don vi minh tham dinh
                    //hql += " and p.receiveGroupId = ? and ( p.receiveUserId = null or p.receiveUserId = ?) and (p.processStatus=? or p.processStatus =? )and p.status=? ";
                    //hql += " and p.receiveGroupId = ? and (p.processStatus=? ";
                    //            lstParam.add(deptId);
                    //lstParam.add(userId);
                    //            lstParam.add(Constants.FILE_STATUS.EVALUATED);
                    //            lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_REVIEW);
                    //            lstParam.add(0l);
                    break;
                case 34:
                    // tim de review
                    hql += " and (f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.EVALUATED);
                    hql += " and p.receiveGroupId = ? and p.receiveUserId = ?";
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    hql += " and f.leaderReviewId = ?";
                    lstParam.add(userId);
                    break;
                case 417://Hồ sơ lãnh đạo cục đã phê duyệt
                    if (status == null) {
                        lstParam.clear();
                        hql = "from FilesNoClob f"
                                + " where f.isActive=1 "
                                + " and (f.isTemp = null or f.isTemp = 0 ) "
                                + " and f.isSignPdf <> 2"
                                + " and (f.status = ?)"
                                + " and f.staffProcess=?";
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        lstParam.add(userId);
                    }
                    break;
                case 422://Hồ sơ VT đã trả giấy công bản cứng
                    if (status == null) {
                        lstParam.clear();
                        hql = "from FilesNoClob f"
                                + " where f.isActive=1 "
                                + " and (f.isTemp = null or f.isTemp = 0 ) "
                                + " and f.isSignPdf = 2"
                                + " and (f.status = ?)"
                                + " and f.staffProcess=?";
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                        lstParam.add(userId);
                    }
                    break;
                case 423://Hồ sơ VT đã trả giấy công bản mềm
                    if (status == null) {
                        lstParam.clear();
                        hql = "from FilesNoClob f"
                                + " where f.isActive=1 "
                                + " and (f.isTemp = null or f.isTemp = 0 ) "
                                + " and f.isSignPdf = 2"
                                + " and (f.status <> ?)"
                                + " and f.staffProcess=?";
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                        lstParam.add(userId);
                    }
                    break;
                // ho so lanh dao phong da xem xet
                case 18:
                    if (status == null) {
                        lstParam.clear();
                        hql = "from FilesNoClob f, Process p"
                                + " where f.isActive=1"
                                + " and f.fileId = p.objectId"
                                + " and f.status = ?"
                                + " and p.receiveUserId=?"
                                + " and p.processType=1"
                                + " and (f.isTemp = null or f.isTemp = 0 ) ";
                        lstParam.add(Constants.FILE_STATUS.REVIEWED);
                        lstParam.add(userId);
                    } else {
                        hql += " and f.status = ? ";
                        lstParam.add(status);
                    }
                    break;
                case 45:
                    if (status == null) {
                        lstParam.clear();
                        hql = "from FilesNoClob f, Process p"
                                + " where f.isActive=1"
                                + " and f.fileId = p.objectId"
                                + " and f.status = ?"
                                + " and (f.isTemp = null or f.isTemp = 0 ) ";
                        lstParam.add(Constants.FILE_STATUS.REVIEWED);
                        hql += " and f.staffProcess=?";
                        lstParam.add(userId);
                    } else {
                        hql += " and f.status = ? ";
                        lstParam.add(status);
                    }
                    break;
                case 19://Hồ sơ lãnh đạo cục yêu cầu bổ sung CV
                    lstParam.clear();
                    hql = "from FilesNoClob f, Process p"
                            + " where f.isActive = 1"
                            + " AND (f.isTemp = null or f.isTemp = 0 )"
                            + " AND f.fileId = p.objectId"
                            + " AND f.status = ?"
                            + " AND p.receiveGroupId = ?"
                            + " AND p.receiveUserId = ?";
                    lstParam.add(Constants.FILE_STATUS.REVIEWED_TO_ADD);
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    break;
                case -26://Hồ sơ đã xem xét cv sđbs chờ phê duyệt công văn
                    lstParam.clear();
                    hql = "from FilesNoClob f, Process p"
                            + " where f.isActive = 1"
                            + " AND (f.isTemp = null or f.isTemp = 0 )"
                            + " AND f.fileId = p.objectId"
                            + " AND f.status = ?"
                            + " AND p.receiveGroupId = ?"
                            + " AND p.receiveUserId = ?";
                    lstParam.add(Constants.FILE_STATUS.REVIEW_TO_ADD);
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    break;
                case 33://Hồ sơ chờ thẩm định SĐBS CV
                    lstParam.clear();
                    hql = "from FilesNoClob f, Process p"
                            + " where f.isActive=1"
                            + " and f.fileId = p.objectId"
                            + " and f.status = ?"
                            + " and p.receiveGroupId =?"
                            //                            + " and p.receiveUserId=?"//141217u binhnt53
                            + " and f.staffProcess=?"//141217u binhnt53
                            + " and p.processType=1"
                            + " and (f.isTemp = null or f.isTemp = 0 ) ";
//                hql += " and (f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD);
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    break;
                case 20://Hồ sơ chờ chuyên viên trong tổ thẩm định
                    lstParam.clear();
                    hql = "from FilesNoClob f, Process p"
                            + " where f.isActive=1"
                            + " and f.fileId = p.objectId"
                            + " and f.status = ?"
                            + " and p.receiveUserId=?"
                            + " and p.processType=0"
                            + " and (f.isTemp = null or f.isTemp = 0 ) ";
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    lstParam.add(userId);
                    break;
                case 21://Hồ sơ gửi phối hợp chưa cho ý kiến
                    lstParam.clear();
                    hql = "from FilesNoClob f, Process p "
                            + "where f.isActive=1 "
                            + "and f.fileId = p.objectId "
                            + "and (f.status = ?) "
                            + "and (p.processType=0 or p.processType=4) "
                            + "and (p.processId in (select p.processId from Process p where p.processId not in (select distinct pc.processId from ProcessComment pc))) "
                            + "and p.processStatus = ?";
                    lstParam.add(3l);
                    lstParam.add(3l);
                    if (deptId != null) {
                        hql += "and p.receiveGroupId = ? ";
                        lstParam.add(deptId);
                    }
                    if (userId != null) {
                        hql += "and p.receiveUserId = ? ";
                        lstParam.add(userId);
                    }
                    break;
                case 22://Hồ sơ chuyên viên đã gửi thông báo sửa đổi bổ sung
                    lstParam.clear();
                    hql = "from FilesNoClob f, Process p"
                            + " where f.isActive = 1"
                            + " AND (f.isTemp = null or f.isTemp = 0 )"
                            + " AND f.fileId = p.objectId"
                            + " AND f.status = ?"
                            + " AND p.receiveGroupId = ?"
                            + " AND p.receiveUserId = ?";
                    lstParam.add(Constants.FILE_STATUS.EVALUATED_TO_ADD);
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    break;
                case 23:// dem ho so trong ngay chuyen vien
                    if (status == null) {
                        lstParam.clear();
                        hql = "from FilesNoClob f, Process p"
                                + " where f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " AND (f.status = ? or f.status = ?)"
                                + " and  to_date(f.sendDate,'yyyy/mm/dd') = to_date(sysdate,'yyyy/mm/dd')"
                                + " and p.receiveUserId=?"
                                + " and p.processType=1"
                                + " and (f.isTemp = null or f.isTemp = 0 ) ";
                        lstParam.add(3l);
                        lstParam.add(5l);
                        lstParam.add(userId);

                    } else {
                        hql += " and f.status = ? ";
                        lstParam.add(status);
                    }
                    break;
                case 24:
                    if (status == null) {
                        lstParam.clear();
                        hql = "from FilesNoClob f, Process p"
                                + " where f.isActive=1"
                                + " and f.fileId = p.objectId"
                                + " and p.objectType = ?"
                                + " and f.status in (?,?)"
                                + " and  to_date(f.sendDate,'yyyy/mm/dd') = to_date(sysdate,'yyyy/mm/dd')"
                                + " and (f.isTemp = null or f.isTemp = 0 ) ";
                        lstParam.add(Constants.OBJECT_TYPE.FILES);
                        lstParam.add(Constants.FILE_STATUS.REVIEWED);
                        lstParam.add(Constants.FILE_STATUS.EVALUATED);
                        hql += " and p.receiveGroupId = ?"
                                + " and ( p.receiveUserId = null or p.receiveUserId = ?) ";
                        lstParam.add(deptId);
                        lstParam.add(userId);

                    } else {
                        hql += " and f.status = ? ";
                        lstParam.add(status);
                    }
                    break;
                case 25://Tổng hồ sơ xử lý trong ngày Lãnh đạo cục
                    if (status == null) {
                        lstParam.clear();
                        hql = "from FilesNoClob f, Process p"
                                + " where f.isActive=1"
                                + " and f.fileId = p.objectId"
                                + " and p.objectType = ?"
                                + " and (f.status = ? or f.status = ? )"
                                + " and  to_date(f.sendDate,'yyyy/mm/dd') = to_date(sysdate,'yyyy/mm/dd')"
                                + " and (f.isTemp = null or f.isTemp = 0 ) ";
                        lstParam.add(Constants.OBJECT_TYPE.FILES);
                        lstParam.add(Constants.FILE_STATUS.REVIEWED);
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        hql += "AND f.agencyId = ?)";
                        lstParam.add(deptId);

                    } else {
                        hql += " and f.status = ? ";
                        lstParam.add(status);
                    }
                    break;
                case 26:// ho so bi tra tham dinh lai
                    if (status == null) {//binhnt update 141211
                        lstParam.clear();
                        hql = "from FilesNoClob f, Process p"
                                + " where f.isActive=1"
                                + " and f.fileId = p.objectId"
                                + " and f.status = ?"
                                + " and p.receiveUserId=?"
                                + " and p.processType=1"
                                + " and (f.isTemp = null or f.isTemp = 0 ) ";
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                        lstParam.add(userId);

                    } else {
                        hql += " and f.status = ? ";
                        lstParam.add(status);
                    }
                    break;
                case 28://Hồ sơ đã có ý kiến của tổ thẩm xét
                    lstParam.clear();
                    hql = "from FilesNoClob f, Process p, ProcessComment pc"
                            + " where f.isActive=1"
                            + " and f.fileId = p.objectId"
                            + " and p.objectType = 30"
                            + " and (f.status = ?)"
                            + " and p.receiveGroupId = (select distinct p.receiveGroupId from Process p where p.receiveUserId = ?) and (p.processType=0 or p.processType=4)"
                            + " and pc.processId = p.processId"
                            + " and (f.isTemp = null or f.isTemp = 0 ) ";
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    lstParam.add(userId);
                    break;
                case 29:// ho so cho xem xet SDBS
                    hql += " and f.status = ? and p.receiveGroupId = ? ";
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                    lstParam.add(deptId);
                    hql += " and p.receiveUserId = ? ";
                    lstParam.add(userId);
                    hql += " and (f.leaderReviewId =?)"; ////150204 binhnt53 update
                    lstParam.add(userId);
                    break;
                case 47:// ho so cho xem xet SDBS
                    hql += " and f.status = ?";
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                    hql += " and f.staffProcess = ? ";
                    lstParam.add(userId);
                    break;
                case 35:// ho so cho xem xet du thao sdbs
                    hql += " and f.status = ? ";
                    lstParam.add(Constants.FILE_STATUS.EVALUATE_TO_ADD);
                    hql += " and p.receiveGroupId = ? ";
                    lstParam.add(deptId);
                    hql += " and p.receiveUserId = ? ";
                    lstParam.add(userId);
                    hql += " and f.leaderReviewId = ? ";
                    lstParam.add(userId);
                    break;
                case 30:
                    if (status == null) {
                        hql += " and f.status = ? and p.receiveGroupId = ? ";
                        lstParam.add(Constants.FILE_STATUS.COMPARED);
                        lstParam.add(deptId);

                    } else {
                        hql += " and f.status = ? ";
                        lstParam.add(status);
                    }
                    break;
                case 31:
                    if (status == null) {
                        hql += " and f.status = ? and p.receiveGroupId = ? ";
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);
                        lstParam.add(deptId);

                    } else {
                        hql += " and f.status = ? ";
                        lstParam.add(status);
                    }
                    break;
                case 3:
                    // tim de phe duyet
                    if (status != null) {
                        hql += " and (f.status = ?)";
                        lstParam.add(status);
                    } else {
                        hql += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.REVIEWED);
                    }
                    if (deptId != null) {
                        hql += " and p.receiveGroupId = ? ";
                        lstParam.add(deptId);
                    }
                    if (userId != null) {
                        hql += " and f.leaderApproveId = ? ";
                        lstParam.add(userId);
                    }
                    // Phe duyet cac ho so ban dau gui den cho don vi minh
                    hql += " and f.agencyId = ?";
                    lstParam.add(deptId);
                    break;
                case 36:
                    hql += " and f.status in (?,?,?,?,?,?)"
                            + " and f.isActive = 1 "
                            + " and (f.isTemp is null or f.isTemp = 0)";
                    lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                    lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
                    lstParam.add(Constants.FILE_STATUS.COMPARED);
                    lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON);
                    lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                    lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);
                    break;
                case 10:
                    // tim de phe duyet
                    if (status != null) {
                        hql += " and (f.status = ?)";
                        lstParam.add(status);
                    } else {
                        hql += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                    }
                    if (deptId != null) {
                        hql += " and p.receiveGroupId = ? ";
                        lstParam.add(deptId);
                    }
                    if (userId != null) {
                        hql += " and p.receiveUserId = ? ";
                        lstParam.add(userId);
                    }
                    // Phe duyet cac ho so ban dau gui den cho don vi minh
                    hql += " and f.agencyId = ?";
                    lstParam.add(deptId);
                    break;
                case 226://ho so cho lanh dao phe duyet thong bao sdbs (26)
                    if (status != null) {
                        hql += " and (f.status = ?)";
                        lstParam.add(status);
                    } else {
                        hql += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.REVIEW_TO_ADD);
                    }
                    if (deptId != null) {
                        hql += " and p.receiveGroupId = ? ";
                        lstParam.add(deptId);
                    }
                    if (userId != null) {
                        hql += " and p.receiveUserId = ? ";
                        lstParam.add(userId);
                    }
                    // Phe duyet cac ho so ban dau gui den cho don vi minh
                    hql += " and f.agencyId = ?";
                    lstParam.add(deptId);
                    break;
                case 6:
                    if (status == null) {
                        hql += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.APPROVED);

                    } else {
                        hql = "from FilesNoClob f"
                                + " where f.isActive=1"
                                + " and (f.isTemp = null or f.isTemp = 0 ) ";
                        hql += " and f.status = ? ";
                        lstParam.add(status);
                    }
                    if (deptId != null) {
                        hql += "AND f.agencyId = ? ";
                        lstParam.add(deptId);
                    }
                    break;
                case 16:
                    // tim ho so doi chieu sai lech
                    if (status == null) {
                        hql += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                    } else {
                        hql += " and f.status = ? ";
                        lstParam.add(status);
                    }
                    if (deptId != null) {
                        hql += "AND f.agencyId = ? ";
                        lstParam.add(deptId);
                    }
                    break;
                case -2:
                    // tim ho so da doi chieu
                    lstParam.clear();
                    hql = "from FilesNoClob f, Process p "
                            + " where f.isActive = 1 "
                            + " AND (f.isTemp = null or f.isTemp = 0 ) "
                            + " AND f.fileId = p.objectId "
                            + " AND p.sendUserId = ? "
                            + " AND p.receiveGroupId = ? "
                            + " AND f.status = ? ";
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    lstParam.add(Constants.FILE_STATUS.APPROVED);
                    break;
                case 27://Hồ sơ sắp hết hạn bổ sung trước 5 ngày
                    lstParam.clear();
                    hql = "from FilesNoClob f, Process p "
                            + " where f.isActive = 1 "
                            + " AND (f.isTemp = null or f.isTemp = 0 ) "
                            + " AND f.fileId = p.objectId "
                            + " AND p.sendUserId = ? "
                            + " AND p.receiveGroupId = ? "
                            + " AND (f.status = ?)";

                    lstParam.add(deptId);
                    lstParam.add(userId);
                    lstParam.add(Constants.FILE_STATUS.EVALUATED_TO_ADD);
                    hql += " AND sysdate - f.evaluateAddDate >= 54";
                    break;
                case 32:
                    lstParam.clear();
                    hql = "from FilesNoClob f, Process p "
                            + " where f.isActive = 1 "
                            + " AND (f.isTemp = null or f.isTemp = 0 ) "
                            + " AND f.fileId = p.objectId "
                            + " AND p.sendUserId = ? "
                            + " AND p.receiveGroupId = ? ";
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    hql += " AND f.status in (?,?,?)";
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD);
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                    hql += " AND (p.processStatus=? or p.processStatus=?)";
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD);
                    hql += " AND p.status=?";
                    lstParam.add(0l);
                    hql += " AND p.processType=?";
                    lstParam.add(Constants.PROCESS_TYPE.COOPERATE);
                    break;
                case 9://tim de pho phong vao tham dinh
                    hql += " and (f.status = ? or f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                    lstParam.add(Constants.FILE_STATUS.EVALUATED);
                    // Chi tim ho so giao cho ca nhan xu ly thoi
                    hql += " and p.receiveGroupId = ?"
                            + " and p.receiveUserId = ?"
                            + " and (p.processStatus=? or p.processStatus =? )"
                            + " and p.status=?"
                            + " and p.processType=?";
                    lstParam.add(deptId);
                    lstParam.add(userId);
                    lstParam.add(Constants.FILE_STATUS.EVALUATED);
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                    lstParam.add(0l);
                    lstParam.add(Constants.PROCESS_TYPE.MAIN);
                    if (userId != null) {
                        hql += " and f.leaderEvaluateId = ?";
                        lstParam.add(userId);
                    }
                    break;
            }
            Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql);
            for (int i = 0;
                    i < lstParam.size();
                    i++) {
                countQuery.setParameter(i, lstParam.get(i));
            }

            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            return total;
        } catch (Exception e) {
            log.error(e);
            return 0;
        }
    }

    public int getCountFileOnHomePage(Long userId, Long deptId, Long searchType, Long status) {//firstpagecount
        try {
            String hql = " from FilesNoClob f, Process p where f.isActive=1 and f.fileId = p.objectId and p.objectType = ? and (f.isTemp = null or f.isTemp = 0 ) ";
            List lstParam = new ArrayList();
            lstParam.add(Constants.OBJECT_TYPE.FILES);
            switch (Integer.parseInt(searchType.toString())) { //dvanthu
                case 22://Ho so lanh dao cuc da phe duyet ho so
                    hql = " from FilesNoClob f, Process p"
                            + " where f.isActive = 1"
                            + " and f.fileId = p.objectId"
                            + " and (f.isTemp = null or f.isTemp = 0 ) ";
                    lstParam.clear();
                    hql += " and f.status in ( ?,?,?,?,?,?)";
//                    lstParam.add(Constants.FILE_STATUS.APPROVED);
                    lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);
                    lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                    lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON);
                    lstParam.add(Constants.FILE_STATUS.COMPARED);
                    lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
                    lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                    hql += " and p.receiveGroupId = ?";
                    lstParam.add(deptId);
                    break;
                case 15://Ho so can thong bao doi chieu
                    hql = " from FilesNoClob f, Process p"
                            + " where f.isActive = 1"
                            + " and f.fileId = p.objectId"
                            + " and (f.isTemp = null or f.isTemp = 0 ) ";
                    lstParam.clear();
                    hql += " and (f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.COMPARED);
                    hql += " and f.agencyId = ?";
                    lstParam.add(deptId);
                    hql += " and f.isFee = 1 and f.isSignPdf = 2";
                    break;
                case 6://Ho so can thong bao doi chieu
                    hql = " from FilesNoClob f, Process p"
                            + " where f.isActive = 1"
                            + " and f.fileId = p.objectId"
                            + " and (f.isTemp = null or f.isTemp = 0 ) ";
                    lstParam.clear();
                    hql += " and (f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.APPROVED);
                    hql += " and f.agencyId = ?";
                    lstParam.add(deptId);
                    hql += " and f.isFee = 1 and f.isSignPdf = 2";
                    hql += " and f.isDownload <> 1";//u150112 binhnt53
                    break;
                case 20://hồ sơ đã thông báo sdbs
                    hql = " from FilesNoClob f, Process p"
                            + " where f.isActive = 1"
                            + " and f.fileId = p.objectId"
                            + " and (f.isTemp = null or f.isTemp = 0 ) ";
                    lstParam.clear();
                    hql += " and (f.status = ?)";
                    lstParam.add(status);
                    hql += " and p.sendGroupId = ? ";
                    lstParam.add(deptId);
                    break;
                case -27://Hồ sơ cần gửi công văn SĐBS cho doanh nghiệp
                    hql = " from FilesNoClob f, Process p"
                            + " where f.isActive = 1"
                            + " and f.fileId = p.objectId"
                            + " and (f.isTemp = null or f.isTemp = 0 ) ";
                    lstParam.clear();
                    hql += " and (f.status = ?)";
                    lstParam.add(status);
                    hql += " and p.receiveGroupId = ? ";
                    lstParam.add(deptId);
                    break;
                case -6://hồ sơ cần văn thư kí xác nhận
                    hql = " from FilesNoClob f, Process p"
                            + " where f.isActive = 1"
                            + " and f.fileId = p.objectId"
                            + " and f.isSignPdf = 1"
                            + " and f.isFee = 1"
                            + " and (f.isTemp = null or f.isTemp = 0 ) ";
                    lstParam.clear();
                    hql += " and (f.status = ?)";//6
                    lstParam.add(Constants.FILE_STATUS.APPROVED);
                    hql += " and f.agencyId = ?";
                    lstParam.add(deptId);
                    break;
                case -4://5- Hồ sơ đã yêu cầu nộp phí cấp số = Đã phê duyệt, chưa nộp lệ phí (files.status = 6, fee_payment_info.status = 0,fee.fee_type = 1 , files.isSignPdf=1)
                    /*hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi"
                     + " where f.fileId = fpi.fileId"
                     + " and (f.isTemp=null or f.isTemp=0)"
                     + " and fe.feeId = fpi.feeId"
                     + " and fe.feeType=1"
                     + " and f.isActive=1"
                     + " and fe.isActive=1"
                     + " and fpi.isActive=1"
                     + " and fpi.status=0"
                     + " and f.userSigned is not null"
                     + " and (f.status=?) and f.agencyId = ?";*/
                    hql = " from FilesNoClob f"
                            + " where (f.isTemp=null or f.isTemp=0)"
                            + " and f.isActive=1"
                            + " and f.isFee <> 1 and f.isSignPdf <> 2"
                            + " and (f.status=?) and f.agencyId = ?";
                    lstParam.clear();
                    lstParam.add(Constants.FILE_STATUS.APPROVED);
                    lstParam.add(deptId);
                    break;
                case -3://Hồ sơ đã nộp phí cấp số, chờ trả hồ sơ = Đã phê duyệt, đã nộp lệ phí (files.status = 6, fee_payment_info.status = 1, fee.fee_type=1, files.isSignPdf=2)
                    hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                            + " and fe.feeId = fpi.feeId and fe.feeType=1 and fe.isActive=1"
                            + " and fpi.isActive=1"
                            + " and fpi.status=1"
                            + " and f.userSigned is not null"
                            + " and f.isSignPdf = 2"
                            + " and (f.status=?)"
                            + " and f.agencyId = ?";

                    lstParam.clear();
                    lstParam.add(Constants.FILE_STATUS.COMPARED);
//                    lstParam.add(Constants.FILE_STATUS.APPROVED);
                    lstParam.add(deptId);
                    break;
                case -2://Hồ sơ đã yêu cầu nộp phí cấp số = Đã phê duyệt, chưa nộp lệ phí (files.status = 6, fee_payment_info.status = 0,fee.fee_type = 1 , files.isSignPdf=1)
                    hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                            + " and fe.feeId = fpi.feeId and fe.feeType=1 and fe.isActive=1"
                            + " and fpi.isActive=1"
                            + " and fpi.status=0"
                            + " and f.userSigned is not null"
                            + " and f.isSignPdf = 1"
                            + " and (f.status=?)"
                            + " and f.agencyId = ?";

                    lstParam.clear();
                    lstParam.add(Constants.FILE_STATUS.APPROVED);
                    lstParam.add(deptId);
                    break;

                // hieptq update nhom thuc pham thuong 201114
                //hieptq update 261214 sua query nhomsp
                case -1://Hồ sơ chờ tiếp nhận = Mới nộp và đã xác nhận phí (files.status = 1, fee_payment_info.status = 1, fee.fee_type=2), Mới nộp SĐBS (18)
                    hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi, Category c where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
                            + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
                            + " and fpi.isActive=1"
                            + " and f.productTypeId = c.categoryId "
                            + " and fpi.status=1"
                            + " and f.userSigned is not null"
                            + " and (f.status=?) and f.agencyId = ?"
                            + " AND (c.code <> ? and c.code <> ?) ";

//                    hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi, Category c ";
//                    condition = " and f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
//                            + " and fe.feeId = fpi.feeId and fe.feeType=2 and fe.isActive=1"
//                            + " and  f.productTypeId = c.categoryId "
//                            + " and fpi.isActive=1"
//                            + " and fpi.status=1"
//                            + " and f.userSigned is not null"
//                            + " and f.status=? and f.agencyId = ?";
//                    ;
                    lstParam.clear();
                    lstParam.add(Constants.FILE_STATUS.NEW);
                    lstParam.add(deptId);
                    lstParam.add(Constants.CATEGORY_TYPE.TPCN);
                    lstParam.add(Constants.CATEGORY_TYPE.DBT);
//                    Query queryCheckFileId = getSession().createQuery("select f.fileId " + hql);
//
//                    // hieptq 181114 tach nhom san pham
//                    for (int i = 0; i < lstParam.size(); i++) {
//                        queryCheckFileId.setParameter(i, lstParam.get(i));
//                    }
//                    // loc fileId all
//                    List<Long> lstResultCheckFileId = new ArrayList<Long>();
//                    lstResultCheckFileId = queryCheckFileId.list();
//                    String lstFileId = "";
//                    for (int i = 0; i < lstResultCheckFileId.size(); i++) {
//                        if (i == (lstResultCheckFileId.size() - 1)) {
//                            lstFileId += lstResultCheckFileId.get(i).toString();
//                        } else {
//                            lstFileId += lstResultCheckFileId.get(i).toString() + ",";
//                        }
//                    }
//                    if ("".equals(lstFileId)) {
//                        lstFileId = "0";
//                    }
//                    // loc file_id nhom san pham khac
//                    Query hql_1 = getSession().createQuery("select fpi.fileId from FeePaymentInfo fpi where fpi.fileId in (" + lstFileId + ") and fpi.cost = 500000 ");
//                    List<Long> lsthql_1 = new ArrayList<Long>();
//                    lsthql_1 = hql_1.list();
//                    String hql1_FileId = "";
//                    for (int i = 0; i < lsthql_1.size(); i++) {
//                        if (i == (lsthql_1.size() - 1)) {
//                            hql1_FileId += lsthql_1.get(i).toString();
//                        } else {
//                            hql1_FileId += lsthql_1.get(i).toString() + ",";
//                        }
//                    }
//                    if ("".equals(hql1_FileId)) {
//                        hql1_FileId = "0";
//                    }
//                    hql += "AND f.fileId in (" + hql1_FileId + ")";

                    break;

                // hieptq update nhom thuc pham khac 201114
                case -8:
                    hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi,Category c"
                            + " where f.fileId = fpi.fileId"
                            + " AND (f.isTemp=null or f.isTemp=0)"
                            + " AND f.isActive=1"
                            + " AND fe.feeId = fpi.feeId"
                            + " AND fe.feeType=2"
                            + " AND fe.isActive=1"
                            + " AND f.productTypeId = c.categoryId "
                            + " AND fpi.isActive=1"
                            + " AND fpi.status=1"
                            + " AND f.userSigned is not null"
                            + " AND (f.status=?)"
                            + " AND f.agencyId = ?"
                            + " AND (c.code = ? or c.code = ?) ";

                    lstParam.clear();
                    lstParam.add(Constants.FILE_STATUS.NEW);
                    lstParam.add(deptId);
                    lstParam.add(Constants.CATEGORY_TYPE.TPCN);
                    lstParam.add(Constants.CATEGORY_TYPE.DBT);
//                    Query queryCheckFileId2 = getSession().createQuery("select f.fileId " + hql);
//                    // hieptq 181114 tach nhom san pham
//                    for (int i = 0; i < lstParam.size(); i++) {
//                        queryCheckFileId2.setParameter(i, lstParam.get(i));
//                    }
//                    // loc fileId all
//                    List<Long> lstResultCheckFileId2 = new ArrayList<Long>();
//                    lstResultCheckFileId2 = queryCheckFileId2.list();
//                    String lstFileId2 = "";
//                    for (int i = 0; i < lstResultCheckFileId2.size(); i++) {
//                        if (i == (lstResultCheckFileId2.size() - 1)) {
//                            lstFileId2 += lstResultCheckFileId2.get(i).toString();
//                        } else {
//                            lstFileId2 += lstResultCheckFileId2.get(i).toString() + ",";
//                        }
//                    }
//                    if ("".equals(lstFileId2)) {
//                        lstFileId2 = "0";
//                    }
//                    Query hql_2 = getSession().createQuery("select fpi.fileId from FeePaymentInfo fpi where fpi.fileId in (" + lstFileId2 + ") and fpi.cost = 1500000 ");
//                    List<Long> lsthql_2 = new ArrayList<Long>();
//                    lsthql_2 = hql_2.list();
//                    String hql1_FileId2 = "";
//                    for (int i = 0; i < lsthql_2.size(); i++) {
//                        if (i == (lsthql_2.size() - 1)) {
//                            hql1_FileId2 += lsthql_2.get(i).toString();
//                        } else {
//                            hql1_FileId2 += lsthql_2.get(i).toString() + ",";
//                        }
//                    }
//                    if ("".equals(hql1_FileId2)) {
//                        hql1_FileId2 = "0";
//                    }
//                    hql += "AND f.fileId in (" + hql1_FileId2 + ")";

                    break;

                case -7://Hồ sơ chờ kế toán xác nhận
                    hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi"
                            + " where f.fileId = fpi.fileId"
                            + " and (f.isTemp=null or f.isTemp=0)"
                            + " and f.isActive=1"
                            + " and fe.feeId = fpi.feeId"
                            + " and fe.feeType=2"
                            + " and fe.isActive=1"
                            + " and fpi.isActive=1"
                            + " and fpi.status > 2"
                            + " and f.userSigned is not null"
                            + " and (f.status=?)"
                            + " and f.agencyId = ?";
                    lstParam.clear();
                    lstParam.add(Constants.FILE_STATUS.NEW);
                    lstParam.add(deptId);
                    break;
                case 0:
                    hql = " from FilesNoClob f, Process p"
                            + " where f.isActive = 1"
                            + " and f.fileId = p.objectId"
                            + " and (f.isTemp = null or f.isTemp = 0 ) ";
                    lstParam.clear();
                    hql += " and (f.status = ?)";
                    lstParam.add(status);
                    hql += " and p.receiveGroupId = ? ";
                    lstParam.add(deptId);
                    break;
                default:
            }

            Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql);
            for (int i = 0; i < lstParam.size(); i++) {
                countQuery.setParameter(i, lstParam.get(i));
            }

            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            return total;
        } catch (Exception e) {
            log.error(e);
            return 0;
        }
    }

    /**
     * Hồ sơ sắp quá hạn
     *
     * @param userId
     * @param deptId
     * @param searchType
     * @param status
     * @return
     */
    public int getCountFileToProcessAlarm(Long userId, Long deptId, Long searchType, Long status) {
        try {
            Date alarmDate = new Date();
            try {
                alarmDate = getSysdate();
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
            //
            // tim cac ho so co deadline dien ra trong 2 ngay toi
            //
            alarmDate = DateTimeUtils.getAddDate(alarmDate, 0, 3);
            alarmDate.setHours(0);
            alarmDate.setMinutes(0);
            alarmDate.setSeconds(0);

            String hql = " from FilesNoClob f, Process p"
                    + " where f.isActive=1"
                    + " and f.fileId = p.objectId"
                    + " and p.objectType = ?"
                    + " and f.deadline <= ?"
                    + " and (f.isTemp = null or f.isTemp = 0 ) ";
            List lstParam = new ArrayList();
            lstParam.add(Constants.OBJECT_TYPE.FILES);
            lstParam.add(alarmDate);

            if (searchType == 1l) {
                //
                // tim de tham dinh
                //
                if (status == null) {
                    hql += " and f.status in (?,?)";
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                } else {
                    hql += " and f.status = ? ";
                    lstParam.add(status);
                }
                //
                // Chi tim ho so giao cho ca nhan xu ly thoi
                //
                hql += " and p.receiveGroupId = ?"
                        + " and p.receiveUserId = ?"
                        + " and (p.processStatus=? or p.processStatus =? )"
                        + " and p.status=? and p.processType = ?";
                lstParam.add(deptId);
                lstParam.add(userId);
                lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                lstParam.add(0l);
                lstParam.add(Constants.PROCESS_TYPE.MAIN);
            } else if (searchType == 4l) {
                //
                // tim de phoi hop tham dinh
                //
                if (status == null) {
                    hql += " and (f.status = ? or f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                } else {
                    hql += " and f.status = ? ";
                    lstParam.add(status);
                }
                //
                // Chi tim ho so giao cho ca nhan xu ly thoi
                //
                hql += " and p.receiveGroupId = ?"
                        + " and p.receiveUserId = ?"
                        + " and (p.processStatus=? or p.processStatus =? )"
                        + " and p.status=? and p.processType = ? ";
                lstParam.add(deptId);
                lstParam.add(userId);
                lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                lstParam.add(0l);
                lstParam.add(Constants.PROCESS_TYPE.COOPERATE);
            } else if (searchType == 2l) {
                //
                // tim de review
                //
//            if (status == null) {
                hql += " and (f.status = ? or f.status = ?)";
                lstParam.add(Constants.FILE_STATUS.EVALUATED);
                lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_REVIEW);
//            } else {
//                hql += " and f.status = ? ";
//                lstParam.add(status);
//            }
                //
                // Lanh dao don vi duoc phan cong tham dinh, tim cac ho so duoc giao cho don vi minh tham dinh
                //
                hql += " and p.receiveGroupId = ?"
                        + " and ( p.receiveUserId = null or p.receiveUserId = ?)"
                        + " and (p.processStatus=? or p.processStatus =? )"
                        + " and p.status=? ";
                lstParam.add(deptId);
                lstParam.add(userId);
                lstParam.add(Constants.FILE_STATUS.EVALUATED);
                lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_REVIEW);
                lstParam.add(0l);

            } else if (searchType == 3l) {
                //
                // tim de phe duyet
                //
                if (status == null) {
                    hql += " and (f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.REVIEWED);
                } else {
                    hql += " and (f.status = ?)";
                    lstParam.add(status);
                }
                //
                // Phe duyet cac ho so ban dau gui den cho don vi minh
                //
                hql += " and p.receiveGroupId = ?"
                        + " and ( p.receiveUserId = null or p.receiveUserId = ?)"
                        + " and p.processStatus=?"
                        + " and p.status=? ";
                lstParam.add(deptId);
                lstParam.add(userId);
                lstParam.add(Constants.FILE_STATUS.REVIEWED);
                lstParam.add(0l);
            }

            Query countQuery = getSession().createQuery("select count(f) " + hql);
            for (int i = 0; i < lstParam.size(); i++) {
                countQuery.setParameter(i, lstParam.get(i));
            }

            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            return total;
        } catch (HibernateException | NumberFormatException e) {
            log.error(e);
            return 0;
        }

    }

    /**
     * Tra cuu
     *
     *
     * @return
     */
    public GridResult searchLookupFilesDonothing(FilesForm form, Long deptId, Long userId, String userType, int start, int count, String sortField) {

        try {
            //return createQueryLookupFiles(form, deptId, userId, userType, start, count, sortField);
            return createQueryLookupFiles(form, deptId, null, userType, start, count, sortField, "");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new GridResult(0, null);
        }

        /*
         String hql = "";
         List lstParam = new ArrayList();
         if (userType.equals(Constants.ROLES.LEAD_OFFICE_ROLE)) {
         hql = " from FilesNoClob f, Process p where f.isActive = 1 and f.fileId = p.objectId and (f.isTemp = null or f.isTemp = 0 ) ";
         }
         if (userType.equals(Constants.ROLES.STAFF_ROLE)) {
         hql = " from FilesNoClob f, Process p where f.isActive = 1 and f.fileId = p.objectId and (f.isTemp = null or f.isTemp = 0 ) ";
         }
         if (userType.equals(Constants.ROLES.LEAD_UNIT)) {
         hql = " from FilesNoClob f, Process p where f.isActive = 1 and f.fileId = p.objectId and (f.isTemp = null or f.isTemp = 0 ) ";
         }
         if (userType.equals(Constants.ROLES.CLERICAL_ROLE)) {
         hql = " from FilesNoClob f, Process p where f.isActive = 1 and f.fileId = p.objectId  and (f.isTemp = null or f.isTemp = 0 ) ";
         //140714 binhnt53
         if (userId != null) {
         hql += " and p.sendUserId = ? ";
         lstParam.add(userId);
         }//!140714 binhnt53
         }

         if (form != null) {
         if (form.getFileCode() != null && !"".equals(form.getFileCode().trim())) {
         hql += "AND lower(f.fileCode) like ? ESCAPE '/' ";
         lstParam.add(StringUtils.toLikeString(form.getFileCode().toLowerCase().trim()));
         }
         if (form.getFileType() != null && form.getFileType().longValue() != -1) {
         hql += " AND f.fileType = ? ";
         lstParam.add(form.getFileType());
         }

         if (form.getSignDate() != null) {
         hql += "AND f.signDate = ? ";
         lstParam.add(form.getSignDate());
         }
         if (form.getStatus() != null && form.getStatus() >= 0l) {
         hql += "AND f.status = ? ";
         lstParam.add(form.getStatus());
         }
         if (form.getStatus() != null && form.getStatus() == 30l) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p where f.isActive = 1 and f.fileId = p.objectId AND f.status = ?  and (f.isTemp = null or f.isTemp = 0 ) ";
         lstParam.add(20l);
         }
         // thong ke ho so trong ngay
         if (form.getStatus() != null && form.getStatus() == 40l) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p where f.isActive = 1 and f.fileId = p.objectId AND (f.status = ? or f.status = ?) and  to_date(f.sendDate,'yyyy/mm/dd') = to_date(sysdate,'yyyy/mm/dd') and p.receiveUserId=? and p.processType=1 and (f.isTemp = null or f.isTemp = 0 ) ";
         lstParam.add(3l);
         lstParam.add(5l);
         lstParam.add(userId);
         }
         //searchtype 5
         if (form.getSearchType() != null && form.getSearchType() == 5l) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p where f.isActive = 1 and f.fileId = p.objectId AND f.status = ?  and (f.isTemp = null or f.isTemp = 0 ) ";
         lstParam.add(5l);
         }

         // ho so can doi chieu
         if (form.getSearchType() != null && form.getSearchType() == 23l) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p where f.isActive = 1 and f.fileId = p.objectId AND f.status = ?  and (f.isTemp = null or f.isTemp = 0 ) and p.receiveGroupId = ? ";
         lstParam.add(23l);
         lstParam.add(deptId);

         }
         // ho so cho tham dinh sdbs
         if (form.getSearchType() != null && form.getSearchType() == 44l) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p where f.isActive=1 and f.fileId = p.objectId and f.status = ? and p.receiveUserId=? and p.processType=1 and (f.isTemp = null or f.isTemp = 0 ) ";
         lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD);
         lstParam.add(userId);
         }

         if (form.getStatus() != null && form.getStatus() == 41l) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p where f.isActive = 1 and f.fileId = p.objectId AND (f.status = ? or f.status = ?) and  to_date(f.sendDate,'yyyy/mm/dd') = to_date(sysdate,'yyyy/mm/dd') and (f.isTemp = null or f.isTemp = 0 ) ";
         lstParam.add(4l);
         lstParam.add(5l);
         }
         if (form.getStatus() != null && form.getStatus() == 42l) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p where f.isActive = 1 and f.fileId = p.objectId AND (f.status = ? or f.status = ?) and  to_date(f.sendDate,'yyyy/mm/dd') = to_date(sysdate,'yyyy/mm/dd') and (f.isTemp = null or f.isTemp = 0 ) ";
         lstParam.add(6l);
         lstParam.add(5l);
         }
         // ho so bi tra tham dinh lai
         if (form.getStatus() != null && form.getStatus() == 43l) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p where f.isActive=1 and f.fileId = p.objectId and f.status = ? and p.receiveUserId=? and p.processType=1 and (f.isTemp = null or f.isTemp = 0 ) ";
         lstParam.add(8l);
         lstParam.add(userId);
         }
         // ho so cho phoi hop tham dinh chua cho y kien
         if (form.getStatus() != null && form.getStatus() == 44l) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p where f.isActive=1 and f.fileId = p.objectId and (f.status = ?) and (p.processType=0 or p.processType=4) and (p.processId in (select p.processId from Process p where p.processId not in (select distinct pc.processId from ProcessComment pc))) and p.processStatus = ?";
         lstParam.add(3l);
         lstParam.add(3l);
         // lstParam.add(userId);
         }
         //ho so cho xem xet

         if (form.getStatus() != null && form.getStatus() == 45l) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p where f.isActive=1 and f.fileId = p.objectId and f.status = ? and p.receiveUserId=? and (p.processType=1 or p.processType=0) and (f.isTemp = null or f.isTemp = 0 ) ";
         lstParam.add(4l);
         lstParam.add(userId);
         }
         // ho so lanh dao phong da xem xet
         if (form.getStatus() != null && form.getStatus() == 46l) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p where f.isActive=1 and f.fileId = p.objectId and f.status = ? and p.receiveUserId=? and p.processType=1 and (f.isTemp = null or f.isTemp = 0 ) ";
         lstParam.add(5l);
         lstParam.add(userId);
         }
         //ho so da phe duyet
         if (form.getStatus() != null && form.getStatus() == 47l) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p where f.isActive=1 and f.fileId = p.objectId and f.status = ? and p.receiveUserId=? and p.processType=1 and (f.isTemp = null or f.isTemp = 0 ) ";
         lstParam.add(Constants.FILE_STATUS.APPROVED);
         lstParam.add(userId);
         }
         // ho so tra lai yeu cau bo sung (status 9)
         if (form.getStatus() != null && form.getStatus() == 48l) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p where f.isActive=1 and f.fileId = p.objectId and f.status = ? and p.receiveUserId=? and p.processType=1 and (f.isTemp = null or f.isTemp = 0 ) ";
         lstParam.add(9l);
         lstParam.add(userId);
         }
         // ho so tham dịnh da gui ý kiễn phản hồi
         if (form.getStatus() != null && form.getStatus() == 49l) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p,ProcessComment pc where f.isActive=1 and f.fileId = p.objectId and p.objectType = 30  and (f.status = ?) and p.receiveGroupId = (select distinct p.receiveGroupId from Process p where p.receiveUserId = ?) and (p.processType=0 or p.processType=1) and pc.processId = p.processId and (f.isTemp = null or f.isTemp = 0 ) ";
         lstParam.add(3l);
         lstParam.add(userId);
         }
         if (form.getStatus() != null && form.getStatus() == Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p where f.isActive = 1 and f.fileId = p.objectId AND f.status = ?  and (f.isTemp = null or f.isTemp = 0 ) ";
         lstParam.add(25l);

         }
         // ho so cho phe duyet
         if (form.getStatus() != null && form.getStatus() == 50l) {
         lstParam.clear();
         hql = "from FilesNoClob f, Process p where f.isActive=1 and f.fileId = p.objectId and (f.isTemp = null or f.isTemp = 0 ) ";
         hql += " and (f.status = ? or f.status = ? or f.status = ?)";
         lstParam.add(Constants.FILE_STATUS.ASSIGNED);
         lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
         lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD);
         hql += " and p.receiveGroupId = ? and p.receiveUserId = ? and (p.processStatus=? or p.processStatus =? or p.processStatus =? )and p.status=? and p.processType = ?";
         lstParam.add(deptId);
         lstParam.add(userId);
         lstParam.add(Constants.FILE_STATUS.ASSIGNED);
         lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
         lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD);
         lstParam.add(0l);
         lstParam.add(Constants.PROCESS_TYPE.MAIN);
         }

         // Haitv21 thêm chỉ tiêu thông tin tìm kiếm
         // Do hieptq reset lại params nên đoạn code này phải dưới các case if_ else bên trên
         if (form.getSendDateFrom() != null) {
         hql += " AND f.sendDate >= ? ";
         lstParam.add(form.getSendDateFrom());
         }
         if (form.getSendDateTo() != null) {
         hql += " AND f.sendDate <= ? ";
         lstParam.add(addOneDay(form.getSendDateTo()));
         }
         // ngày thêm kho lưu trữ
         if (form.getRepDateFrom() != null) {
         hql += " AND f.repDate >= ? ";
         lstParam.add(form.getRepDateFrom());
         }
         if (form.getRepDateTo() != null) {
         hql += " AND f.repDate <= ? ";
         lstParam.add(addOneDay(form.getRepDateTo()));
         }

         // Ngày phê duyệt từ ngày x tới ngày x
         if (form.getApproveDateFrom() != null) {
         hql += "AND f.approveDate >= ? ";
         lstParam.add(form.getApproveDateFrom());
         }
         if (form.getApproveDateTo() != null) {
         hql += " AND f.approveDate <= ? ";
         lstParam.add(addOneDay(form.getApproveDateTo()));
         }

         // Người phê duyệt
         if (form.getLeaderStaffSignName() != null && form.getLeaderStaffSignName().length() > 0) {
         hql += "AND lower(f.leaderStaffSignName) like ? ESCAPE '/' ";
         lstParam.add(StringUtils.toLikeString(form.getLeaderStaffSignName().toLowerCase().trim()));
         }

         // Tên doanh nghiệp
         if (form.getBusinessName() != null && form.getBusinessName().length() > 0) {
         hql += "AND lower(f.businessName) like ? ESCAPE '/' ";
         lstParam.add(StringUtils.toLikeString(form.getBusinessName().toLowerCase().trim()));
         }
         // Xuất xứ
         if (form.getNationName() != null && form.getNationName().length() > 0) {
         hql += "AND lower(f.nationName) like ? ESCAPE '/' ";
         lstParam.add(StringUtils.toLikeString(form.getNationName().toLowerCase().trim()));
         }

         // Nhà sản xuất
         if (form.getManufactureName() != null && form.getManufactureName().length() > 0) {
         hql += "AND  (f.announcementId in (select ann.announcementId from Announcement ann where lower(ann.manufactureName) like ? ESCAPE '/'))";
         lstParam.add(StringUtils.toLikeString(form.getManufactureName().toLowerCase().trim()));
         }
         // Người thẩm định
         if (form.getReceiveUser() != null && form.getReceiveUser().length() > 0) {
         hql += "AND  (f.fileId in (select p.objectId from Process p where lower(p.receiveUser) like ? ESCAPE '/'))";
         lstParam.add(StringUtils.toLikeString(form.getReceiveUser().toLowerCase().trim()));
         }

         // Số chứng nhận công bố
         if (form.getAnnouncementNo() != null && form.getAnnouncementNo().length() > 0) {
         hql += "AND (f.announcementReceiptPaperId in (select a.announcementReceiptPaperId from AnnouncementReceiptPaper a where lower(a.receiptNo) like ? ESCAPE '/')) ";
         lstParam.add(StringUtils.toLikeString(form.getAnnouncementNo().toLowerCase().trim()));
         }

         // Số đăng ký kinh doanh
         if (form.getBusinessLicence() != null && form.getBusinessLicence().length() > 0) {
         hql += "AND (f.deptId in (select b.businessId from Business b where lower(b.businessLicense) like ? ESCAPE '/')) ";
         lstParam.add(StringUtils.toLikeString(form.getBusinessLicence().toLowerCase().trim()));
         }

         // Địa chỉ doanh nghiệp
         if (form.getBusinessAddress() != null && form.getBusinessAddress().length() > 0) {
         hql += "AND lower(f.businessAddress) like ? ESCAPE '/' ";
         lstParam.add(StringUtils.toLikeString(form.getBusinessAddress().toLowerCase().trim()));
         }

         // Tên sản phẩm
         if (form.getProductName() != null && form.getProductName().length() > 0) {
         hql += "AND lower(f.productName) like ? ESCAPE '/' ";
         lstParam.add(StringUtils.toLikeString(form.getProductName().toLowerCase().trim()));
         }

         // Nhóm sản phẩm
         if (form.getProductType() != null && form.getProductType() != -1l) {
         hql += "AND (f.detailProductId in ( select d.detailProductId from DetailProduct d where d.productType = ?)) ";
         lstParam.add(form.getProductType());
         }

         // Lãnh đạo phòng
         if (form.getLeaderStaff() != null && form.getLeaderStaff().length() > 0) {
         hql += "AND  (f.fileId in (select p.objectId from Process p where lower(p.receiveUser) like ? ESCAPE '/')) ";
         lstParam.add(StringUtils.toLikeString(form.getLeaderStaff().toLowerCase().trim()));
         }

         // Người thẩm xét
         if (form.getStaff() != null && form.getStaff().length() > 0) {
         hql += "AND  (f.fileId in (select p.objectId from Process p where lower(p.receiveUser) like ? ESCAPE '/')) ";
         lstParam.add(StringUtils.toLikeString(form.getStaff().toLowerCase().trim()));
         }

         // Tỉnh -  Thành Phố
         if (form.getProductType() != null && form.getBusinessProvinceId() != -1l) {
         hql += "AND (f.deptId in (select b.businessId from Business b where b.businessProvinceId= ? )) ";
         lstParam.add(form.getBusinessProvinceId());
         }

         // Nơi lưu trữ
         if (form.getRepositoriesId() != null && form.getRepositoriesId() != -1l) {
         hql += "AND (f.repositoriesId = ? ) ";
         lstParam.add(form.getRepositoriesId());
         }

         // Lọc theo người tạo ( lưu trữ hồ sơ giấy )
         if (form.getRepCreator() != null && (form.getSearchType() == 0 || form.getSearchType() == 2)) {
         hql += "AND (f.fileId in (select p.objectId  from Process p where p.processStatus = 3 and p.receiveUserId = ?)) ";
         lstParam.add(form.getRepCreator());
         }

         // Kho lưu trữ
         if (form.getRepName() != null && form.getRepName() != -1l) {
         hql += "AND (f.repositoriesId = ? )";
         lstParam.add(form.getRepName());
         }

         // Trạng thái lưu trữ
         // Đã lưu trữ
         if (form.getRepStatus() != null && form.getRepStatus() == 1) {
         hql += "AND (f.repositoriesId <> null )";
         }
         // Chưa lưu trữ
         if (form.getRepStatus() != null && form.getRepStatus() == 2) {
         hql += "AND (f.repositoriesId = null )";
         }

         if (userType.equals(Constants.ROLES.LEAD_OFFICE_ROLE)) {
         if (deptId != null) {
         hql += "AND f.agencyId = ? ";
         lstParam.add(deptId);
         }
         }
         if (userType.equals(Constants.ROLES.STAFF_ROLE)) {
         if (deptId != null) {
         hql += "and p.receiveGroupId = ? ";
         lstParam.add(deptId);
         }
         }
         if (userType.equals(Constants.ROLES.LEAD_UNIT)) {
         if (deptId != null) {
         hql += " and p.receiveGroupId = ? and ( p.receiveUserId = null or p.receiveUserId = ?) ";
         lstParam.add(deptId);
         lstParam.add(userId);
         }
         }
         if (userType.equals(Constants.ROLES.CLERICAL_ROLE)) {
         if (deptId != null) {
         //                    hql += "AND f.agencyId = ?)";
         //                    lstParam.add(deptId);
         hql += "and p.receiveGroupId = ? ";
         lstParam.add(deptId);
         }
         }

         }
         if (userType.equals(Constants.ROLES.CLERICAL_ROLE) && form.getSearchType() != null) {
         switch (Integer.parseInt(form.getSearchType().toString())) {
         case -4://5- Hồ sơ đã yêu cầu nộp phí cấp số = Đã phê duyệt, chưa nộp lệ phí (files.status = 6, fee_payment_info.status = 0,fee.fee_type = 1 , files.isSignPdf=1)
         hql = " from FilesNoClob f, FeePaymentInfo fpi where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
         + " and fpi.feeId in (select fe.feeId from Fee fe where fe.feeType=1 and fe.isActive=1)"
         + " and fpi.isActive=1"
         + " and fpi.status=0"
         + " and f.userSigned is not null"
         + " and (f.status=?) and f.agencyId = ?";
         lstParam.clear();
         lstParam.add(Constants.FILE_STATUS.APPROVED);
         lstParam.add(deptId);
         break;
         case -3://Hồ sơ đã nộp phí cấp số, chờ trả hồ sơ = Đã phê duyệt, đã nộp lệ phí (files.status = 6, fee_payment_info.status = 1, fee.fee_type=1, files.isSignPdf=2)
         hql = " from FilesNoClob f, FeePaymentInfo fpi where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
         + " and fpi.feeId in (select fe.feeId from Fee fe where fe.feeType=1 and fe.isActive=1)"
         + " and fpi.isActive=1"
         + " and fpi.status=1"
         + " and f.userSigned is not null"
         + " and f.isSignPdf = 2"
         + " and (f.status=?)"
         + " and f.agencyId = ?";

         lstParam.clear();
         lstParam.add(Constants.FILE_STATUS.APPROVED);
         lstParam.add(deptId);
         break;
         case -2://Hồ sơ đã yêu cầu nộp phí cấp số = Đã phê duyệt, chưa nộp lệ phí (files.status = 6, fee_payment_info.status = 0,fee.fee_type = 1 , files.isSignPdf=1)
         hql = " from FilesNoClob f, FeePaymentInfo fpi where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
         + " and fpi.feeId in (select fe.feeId from Fee fe where fe.feeType=1 and fe.isActive=1)"
         + " and fpi.isActive=1"
         + " and fpi.status=0"
         + " and f.userSigned is not null"
         + " and f.isSignPdf = 1"
         + " and (f.status=?)"
         + " and f.agencyId = ?";
         lstParam.clear();
         lstParam.add(Constants.FILE_STATUS.APPROVED);
         lstParam.add(deptId);
         break;
         case -1://Hồ sơ chờ tiếp nhận = Mới nộp và đã xác nhận phí (files.status = 1, fee_payment_info.status = 1, fee.fee_type=2), Mới nộp SĐBS (18)
         hql = " from FilesNoClob f, FeePaymentInfo fpi where f.fileId = fpi.fileId and (f.isTemp=null or f.isTemp=0) and f.isActive=1"
         + " and fpi.feeId in (select fe.feeId from Fee fe where fe.feeType=2 and fe.isActive=1)"
         + " and fpi.isActive=1"
         + " and fpi.status=1"
         + " and f.userSigned is not null"
         + " and (f.status=? or f.status=?) and f.agencyId = ?";
         lstParam.clear();
         lstParam.add(Constants.FILE_STATUS.NEW);
         lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
         lstParam.add(deptId);
         break;
         case 0:
         hql = " from FilesNoClob f, Process p where f.isActive = 1 and f.fileId = p.objectId and (f.isTemp = null or f.isTemp = 0 ) ";
         lstParam.clear();
         hql += " and (f.status = ?)";
         lstParam.add(form.getStatus());
         hql += " and p.receiveGroupId = ? ";
         lstParam.add(deptId);
         break;
         default:;
         }
         }
         Query countQuery = getSession().createQuery("select count(f) from FilesNoClob f where f.fileId in (select distinct f.fileId " + hql + ")");
         Query query = getSession().createQuery("select f from FilesNoClob f where f.fileId in ( select distinct f.fileId " + hql + ") order by f.sendDate ASC");
         for (int i = 0; i < lstParam.size(); i++) {
         query.setParameter(i, lstParam.get(i));
         countQuery.setParameter(i, lstParam.get(i));
         }

         query.setFirstResult(start);
         query.setMaxResults(count);
         int total = Integer.parseInt(countQuery.uniqueResult().toString());
         List<FilesNoClob> lstResult = query.list();
         GridResult gr = new GridResult(total, lstResult);
         return gr;

         */
    }

    public GridResult searchLookupFilesOnHomePage(FilesForm form,
            Long deptId, Long userId, String userType, int start, int count, String sortField) {
        try {
            String hql = "";
            List lstParam = new ArrayList();
            if (userType.equals(Constants.ROLES.CLERICAL_ROLE)) {
                if (form != null) {
                    if (form.getSearchType() != null) {
                        switch (Integer.parseInt(form.getSearchType().toString())) {
                            case 1:
                                hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi"
                                        + " where f.fileId = fpi.fileId"
                                        + " and (f.isTemp=null or f.isTemp=0)"
                                        + " and f.isActive=1"
                                        + " and fe.feeId = fpi.feeId"
                                        + " and fe.feeType=2"
                                        + " and fe.isActive=1"
                                        + " and fpi.isActive=1"
                                        + " and fpi.status=1"
                                        + " and f.userSigned is not null"
                                        + " and (f.status=? or f.status=?)"
                                        + " and f.deptId = ?";
                                lstParam.clear();
                                lstParam.add(Constants.FILE_STATUS.NEW);
                                lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
                                lstParam.add(deptId);
                                break;
                            default:
                        }
                    }
                }
            }
            Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql);
            Query query = getSession().createQuery("select distinct f " + hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }

            query.setFirstResult(start);
            query.setMaxResults(count);
            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            List<FilesNoClob> lstResult = query.list();
            GridResult gr = new GridResult(total, lstResult);
            return gr;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new GridResult(0, null);
        }
    }

    /**
     * Tìm hồ sơ để tiếp nhận hoặc từ chối tiếp nhận
     *
     * @param form
     * @param deptId
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findAllFileForReceived(FilesForm form, Long deptId, int start, int count, String sortField) {
        try {
            String hql = " from FilesNoClob f, Fee fe, FeePaymentInfo fpi"
                    + " where f.fileId = fpi.fileId"
                    + " and (f.isTemp=null or f.isTemp=0)"
                    + " and f.isActive=1"
                    + " and fe.feeId = fpi.feeId"
                    + " and fe.feeType=2"
                    + " and fe.isActive=1"
                    + " and fpi.isActive=1"
                    + " and fpi.status=1"
                    + " and f.userSigned is not null"
                    + " and (f.status=? or f.status=?)";
            List lstParam = new ArrayList();
            lstParam.add(Constants.FILE_STATUS.NEW);
            lstParam.add(Constants.FILE_STATUS.NEW_TO_ADD);
            if (form != null) {
                if (form.getFileCode() != null && !"".equals(form.getFileCode().trim())) {
                    hql += "AND lower(f.fileCode) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getFileCode().toLowerCase().trim()));
                }
                if (form.getFileType() != null && form.getFileType().longValue() != -1) {
                    hql += "AND f.fileType = ? ";
                    lstParam.add(form.getFileType());
                }
                if (form.getSendDateFrom() != null) {
                    hql += "AND f.sendDate >= ? ";
                    lstParam.add(form.getSendDateFrom());
                }
                if (form.getSendDateTo() != null) {
                    hql += "AND f.sendDate <= ? ";
                    lstParam.add(form.getSendDateTo());
                }
                if (deptId != null) {
                    hql += "AND f.agencyId = ? ";
                    lstParam.add(deptId);
                }
            }

            Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql);
            Query query = getSession().createQuery("select distinct f " + hql + " "
                    + "order by f.modifyDate DESC");
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }

            query.setFirstResult(start);
            query.setMaxResults(count);
            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            List<FilesNoClob> lstResult = query.list();
            GridResult gr = new GridResult(total, lstResult);
            return gr;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new GridResult(0, null);
        }
    }

    /**
     * Tìm hồ sơ để đối chiếu
     *
     * @param form
     * @param deptId
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findAllFileForComparison(FilesForm form, Long deptId, int start, int count, String sortField) {
        try {
            String hql = " from FilesNoClob f where f.isActive=1 and (f.isTemp = null or f.isTemp = 0 ) ";
            List lstParam = new ArrayList();
            if (form != null) {

                hql += " and (f.status = ?)";
                lstParam.add(Constants.FILE_STATUS.APPROVED);

                if (form.getFileCode() != null && !"".equals(form.getFileCode().trim())) {
                    hql += "AND lower(f.fileCode) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getFileCode().toLowerCase().trim()));
                }
                if (form.getFileType() != null && form.getFileType().longValue() != -1) {
                    hql += "AND f.fileType = ? ";
                    lstParam.add(form.getFileType());
                }
                if (form.getSendDateFrom() != null) {
                    hql += "AND f.sendDate >= ? ";
                    lstParam.add(form.getSendDateFrom());
                }
                if (form.getSendDateTo() != null) {
                    hql += "AND f.sendDate <= ? ";
                    lstParam.add(form.getSendDateTo());
                }
                if (deptId != null) {
                    hql += "AND f.agencyId = ? ";
                    lstParam.add(deptId);
                }
            }

            Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql);
            Query query = getSession().createQuery("select distinct f " + hql + " order by f.modifyDate desc");
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }

            query.setFirstResult(start);
            query.setMaxResults(count);
            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            List<FilesNoClob> lstResult = query.list();
//        for (FilesNoClob f : lstResult) {
//            hql = "select p from Process p where p.objectType=? and p.objectId = ? and (p.processStatus=? or p.processType=? ) ";
//            query = getSession().createQuery(hql);
//            query.setParameter(0, Constants.OBJECT_TYPE.FILES);
//            query.setParameter(1, f.getFileId());
//            query.setParameter(2, Constants.FILE_STATUS.ASSIGNED);
//            query.setParameter(3, Constants.PROCESS_TYPE.PROPOSE);
//            List<Process> lstPro = query.list();
//            if (lstPro.size() > 0) {
//                StringBuilder processName = new StringBuilder("");
//                for (int i = 0; i < lstPro.size(); i++) {
//                    if (lstPro.get(i).getReceiveUser() != null) {
//                        if (processName.length() == 0) {
//                            processName.append(lstPro.get(i).getReceiveUser());
//                        } else {
//                            processName.append(",").append(lstPro.get(i).getReceiveUser());
//                        }
//                    }
//                }
//                f.setProposeUserName(processName.toString());
//            }
//        }
            GridResult gr = new GridResult(total, lstResult);
            return gr;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new GridResult(0, null);
        }
    }

    /**
     * Đếm hồ sơ không có ý kiến thẩm xét
     *
     * @param userId
     * @param deptId
     * @param searchType
     * @param status
     * @return
     */
    public int getCountFileToProcessNotComment(Long userId, Long deptId, Long searchType, Long status) {
        try {
            String hql = " from FilesNoClob f, Process p where f.isActive=1 and f.fileId = p.objectId and p.objectType = ? and (f.isTemp = null or f.isTemp = 0 ) ";
            List lstParam = new ArrayList();
            lstParam.add(Constants.OBJECT_TYPE.FILES);

            if (searchType == 1l) {
                //
                // tim de tham dinh
                //
                if (status == null) {
                    hql += " and (f.status = ? or f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                } else {
                    hql += " and f.status = ? ";
                    lstParam.add(status);
                }
                //
                // Chi tim ho so giao cho ca nhan xu ly thoi
                //
                hql += " and p.receiveGroupId = ? and p.receiveUserId = ? and (p.processStatus=? or p.processStatus =? )and p.status=? and p.processType = ?";
                lstParam.add(deptId);
                lstParam.add(userId);
                lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                lstParam.add(0l);
                lstParam.add(Constants.PROCESS_TYPE.MAIN);
            } else if (searchType == 4l) {
                //
                // tim de phoi hop tham dinh
                //
                if (status == null) {
                    hql += " and (f.status = ? or f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                } else {
                    hql += " and f.status = ? ";
                    lstParam.add(status);
                }
                //
                // Chi tim ho so giao cho ca nhan xu ly thoi
                //
                hql += " and p.receiveGroupId = ? and p.receiveUserId = ? and (p.processStatus=? or p.processStatus =? )and p.status=? and p.processType = ? ";
                lstParam.add(deptId);
                lstParam.add(userId);
                lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                lstParam.add(0l);
                lstParam.add(Constants.PROCESS_TYPE.COOPERATE);
            } else if (searchType == -1l) {
                //
                // tim de tiep nhan
                //
                if (status == null) {
                    hql += " and (f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.NEW);
                } else {
                    hql += " and f.status = ? ";
                    lstParam.add(status);
                }
                if (deptId != null) {
                    hql += "AND f.agencyId = ? ";
                    lstParam.add(deptId);
                }
//            hql += " and ( p.receiveUserId = null or p.receiveUserId = ?) ";
//            lstParam.add(userId);
            } else if (searchType == 2l) {
                //
                // tim de review
                //
                if (status == null) {
                    hql += " and (f.status = ? or f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.EVALUATED);
                    lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_REVIEW);
                } else {
                    hql += " and f.status = ? ";
                    lstParam.add(status);
                }
                //
                // Lanh dao don vi duoc phan cong tham dinh, tim cac ho so duoc giao cho don vi minh tham dinh
                //
                hql += " and p.receiveGroupId = ? and ( p.receiveUserId = null or p.receiveUserId = ?) and (p.processStatus=? or p.processStatus =? )and p.status=? ";
                lstParam.add(deptId);
                lstParam.add(userId);
                lstParam.add(Constants.FILE_STATUS.EVALUATED);
                lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_REVIEW);
                lstParam.add(0l);

            } else if (searchType == 3l) {
                //
                // tim de phe duyet
                //
                if (status == null) {
                    hql += " and (f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.REVIEWED);
                } else {
                    hql += " and (f.status = ?)";
                    lstParam.add(status);
                }
                //
                // Phe duyet cac ho so ban dau gui den cho don vi minh
                //
                hql += " and p.receiveGroupId = ? and ( p.receiveUserId = null or p.receiveUserId = ?) and p.processStatus=? and p.status=? ";
                lstParam.add(deptId);
                lstParam.add(userId);
                lstParam.add(Constants.FILE_STATUS.REVIEWED);
                lstParam.add(0l);
            } else if (searchType == 0L) {
                /*tim de phan cong*/

                hql += " and (f.status = ? or f.status = ? or f.status = ?)";
//                hql += " and (f.status = ? or f.status = ?)";
                lstParam.add(Constants.FILE_STATUS.NEW);
                lstParam.add(Constants.FILE_STATUS.RECEIVED);
                lstParam.add(Constants.FILE_STATUS.PROPOSED);
                if (deptId != null) {
                    hql += "AND (f.agencyId = ? or ( (p.receiveUserId = ? or p.receiveGroupId=? )"
                            + " and p.processStatus in (?,?,?,?) ) )";

                    lstParam.add(deptId);
                    lstParam.add(userId);
                    lstParam.add(deptId);
                    lstParam.add(Constants.FILE_STATUS.NEW);
                    lstParam.add(Constants.FILE_STATUS.RECEIVED);
                    lstParam.add(Constants.FILE_STATUS.PROPOSED);
                    lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                } else {
                    hql += "AND (p.receiveUserId = ?"
                            + " and p.processStatus in (?,?,?)"
                            + " and p.status=? ) ";
                    lstParam.add(userId);
                    lstParam.add(Constants.FILE_STATUS.NEW);
                    lstParam.add(Constants.FILE_STATUS.RECEIVED);
                    lstParam.add(Constants.FILE_STATUS.PROPOSED);
                    lstParam.add(0l);
                }
            }

            Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql);
            for (int i = 0; i < lstParam.size(); i++) {
                countQuery.setParameter(i, lstParam.get(i));
            }

            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            return total;
        } catch (Exception e) {
            log.error(e);
            return 0;
        }
    }

    public GridResult reportLookup(FilesForm form, Long deptId, Long userId, String userType, int start, int count, String sortField, String sortCustom) {
        List lstParam = new ArrayList();
        String hql = " from FilesNoClob f, Process p";
        String condition = "";
        condition += " and f.isActive = 1"
                + " and f.fileId = p.objectId"
                + "  and (f.isTemp = null or f.isTemp = 0 )";
        if (form != null) {
            if (userType.equals(Constants.ROLES.CLERICAL_ROLE) && form.getSearchType() != null) {
                switch (Integer.parseInt(form.getSearchType().toString())) {
                    case 1:
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND p.processStatus = ?";
                        lstParam.add(Constants.FILE_STATUS.RECEIVED);
                        if (form.getSearchDateFrom() != null) {
                            condition += " and p.receiveDate >= to_date( ? ,'dd/MM/yyyy hh24:mi:ss') ";
                            String param = "" + com.viettel.common.util.DateTimeUtils.convertDateToString(form.getSearchDateFrom(), "dd/MM/yyyy") + " 00:00:00";
                            lstParam.add(param);
//                            condition += " and p.receiveDate >= ?";
//                            lstParam.add(form.getSearchDateFrom());
                        }
                        if (form.getSearchDateTo() != null) {
//                            condition += " and p.receiveDate <= ?";
//                            lstParam.add(form.getSearchDateTo());
                            condition += " and p.receiveDate <= to_date( ? ,'dd/MM/yyyy hh24:mi:ss') ";
                            String param = "" + com.viettel.common.util.DateTimeUtils.convertDateToString(form.getSearchDateTo(), "dd/MM/yyyy") + " 23:59:59";
                            lstParam.add(param);
                        }
                        break;
                    case 2:
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND p.processStatus = ?";
                        lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD);
//                        if (form.getSearchDateFrom() != null) {
//                            condition += " and p.receiveDate >= ?";
//                            lstParam.add(form.getSearchDateFrom());
//                        }
//                        if (form.getSearchDateTo() != null) {
//                            condition += " and p.receiveDate <= ?";
//                            lstParam.add(form.getSearchDateTo());
//                        }
                        if (form.getSearchDateFrom() != null) {
                            condition += " and p.receiveDate >= to_date( ? ,'dd/MM/yyyy hh24:mi:ss') ";
                            String param = "" + com.viettel.common.util.DateTimeUtils.convertDateToString(form.getSearchDateFrom(), "dd/MM/yyyy") + " 00:00:00";
                            lstParam.add(param);
//                            condition += " and p.receiveDate >= ?";
//                            lstParam.add(form.getSearchDateFrom());
                        }
                        if (form.getSearchDateTo() != null) {
//                            condition += " and p.receiveDate <= ?";
//                            lstParam.add(form.getSearchDateTo());
                            condition += " and p.receiveDate <= to_date( ? ,'dd/MM/yyyy hh24:mi:ss') ";
                            String param = "" + com.viettel.common.util.DateTimeUtils.convertDateToString(form.getSearchDateTo(), "dd/MM/yyyy") + " 23:59:59";
                            lstParam.add(param);
                        }
                        break;
                    case 3:
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND p.processStatus = ?";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED_TO_ADD);
//                        if (form.getSearchDateFrom() != null) {
//                            condition += " and p.receiveDate >= ?";
//                            lstParam.add(form.getSearchDateFrom());
//                        }
//                        if (form.getSearchDateTo() != null) {
//                            condition += " and p.receiveDate <= ?";
//                            lstParam.add(form.getSearchDateTo());
//                        }
                        if (form.getSearchDateFrom() != null) {
                            condition += " and p.receiveDate >= to_date( ? ,'dd/MM/yyyy hh24:mi:ss') ";
                            String param = "" + com.viettel.common.util.DateTimeUtils.convertDateToString(form.getSearchDateFrom(), "dd/MM/yyyy") + " 00:00:00";
                            lstParam.add(param);
//                            condition += " and p.receiveDate >= ?";
//                            lstParam.add(form.getSearchDateFrom());
                        }
                        if (form.getSearchDateTo() != null) {
//                            condition += " and p.receiveDate <= ?";
//                            lstParam.add(form.getSearchDateTo());
                            condition += " and p.receiveDate <= to_date( ? ,'dd/MM/yyyy hh24:mi:ss') ";
                            String param = "" + com.viettel.common.util.DateTimeUtils.convertDateToString(form.getSearchDateTo(), "dd/MM/yyyy") + " 23:59:59";
                            lstParam.add(param);
                        }
                        break;
                    case 4:
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND p.processStatus = ?";
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
//                        if (form.getSearchDateFrom() != null) {
//                            condition += " and p.receiveDate >= ?";
//                            lstParam.add(form.getSearchDateFrom());
//                        }
//                        if (form.getSearchDateTo() != null) {
//                            condition += " and p.receiveDate <= ?";
//                            lstParam.add(form.getSearchDateTo());
//                        }
                        if (form.getSearchDateFrom() != null) {
                            condition += " and p.receiveDate >= to_date( ? ,'dd/MM/yyyy hh24:mi:ss') ";
                            String param = "" + com.viettel.common.util.DateTimeUtils.convertDateToString(form.getSearchDateFrom(), "dd/MM/yyyy") + " 00:00:00";
                            lstParam.add(param);
//                            condition += " and p.receiveDate >= ?";
//                            lstParam.add(form.getSearchDateFrom());
                        }
                        if (form.getSearchDateTo() != null) {
//                            condition += " and p.receiveDate <= ?";
//                            lstParam.add(form.getSearchDateTo());
                            condition += " and p.receiveDate <= to_date( ? ,'dd/MM/yyyy hh24:mi:ss') ";
                            String param = "" + com.viettel.common.util.DateTimeUtils.convertDateToString(form.getSearchDateTo(), "dd/MM/yyyy") + " 23:59:59";
                            lstParam.add(param);
                        }
                        break;
                    default:;
                }
            }
            // mã hồ sơ
            if (form.getFileCode() != null && !"".equals(form.getFileCode().trim())) {
                condition += " AND lower(f.fileCode) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getFileCode().toLowerCase().trim()));
            }
            if (form.getFileType() != null && form.getFileType().longValue() != -1) {
                condition += " AND f.fileType = ?";
                lstParam.add(form.getFileType());
            }
            if (form.getBusinessName() != null && form.getBusinessName().length() > 0) {
                condition += " AND lower(f.businessName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getBusinessName().toLowerCase().trim()));
            }
            // Tên sản phẩm
            if (form.getProductName() != null && form.getProductName().length() > 0) {
                condition += " AND lower(f.productName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getProductName().toLowerCase().trim()));
            }
            // Nhóm sản phẩm
            if (form.getProductType() != null && form.getProductType() != -1l) {
                condition += " AND d.productType = ?";
                lstParam.add(form.getProductType());
            }
        }
        Query countQuery = getSession().createQuery("select count(f.fileId) " + hql + " where 1=1 " + condition);
        String finalSql = "select distinct f " + hql + " where 1=1 " + condition + " order by ";
        if (sortCustom.isEmpty()) {
            finalSql += "p.modifyDate DESC";
        } else {
            finalSql += sortCustom;
        }
        Query query = getSession().createQuery(finalSql);

        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            countQuery.setParameter(i, lstParam.get(i));
        }

        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        query.setFirstResult(start);
        if (count > 0) {
            query.setMaxResults(count);
        } else {
            query.setMaxResults(total);
        }
        List<FilesNoClob> lstResult = query.list();
        return new GridResult(total, lstResult);
    }

    public GridResult findAllFileForAssignEvaluationAfterAnnounced(FilesForm form, Long deptId, Long userId, int start, int count, String sortField) {
        try {
            String hql = " from FilesNoClob f, Process p "
                    + " where f.isActive=1"
                    + " and f.fileType = ?"
                    + " and f.fileId = p.objectId"
                    + " and p.objectType = ?"
                    + " and p.isActive = 1"
                    + " and (f.isTemp is null or f.isTemp = 0 ) ";
            /*
             *Hiepvv 13/01/16
             *Phan cong cong viec cho ho so sua doi sau cong bo
             */
            Procedure pbo = new Procedure();
            ProcedureDAOHE pdaohe = new ProcedureDAOHE();
            pbo = pdaohe.getProcedureByDescription(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05);
            List lstParam = new ArrayList();
            lstParam.add(pbo.getProcedureId());
            lstParam.add(Constants.OBJECT_TYPE.FILES);
            if (form != null) {
                if (form.getStatus() == null) {
                    hql += " and (f.status = ?)";
                    lstParam.add(Constants.FILE_STATUS.RECEIVED);
                } else {
                    hql += " and (f.status = ?)";
                    lstParam.add(form.getStatus());
                }
                if (form.getBusinessName() != null && !"".equals(form.getBusinessName().trim())) {
                    hql += " AND lower(f.businessName) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getBusinessName().toLowerCase().trim()));
                }

                if (form.getProductName() != null && form.getProductName().length() > 0) {
                    hql += " AND lower(f.productName) like ? ESCAPE '/'";
                    lstParam.add(StringUtils.toLikeString(form.getProductName().toLowerCase().trim()));
                }
                if (form.getFileCode() != null && !"".equals(form.getFileCode().trim())) {
                    hql += "AND lower(f.fileCode) like ? ESCAPE '/' ";
                    lstParam.add(StringUtils.toLikeString(form.getFileCode().toLowerCase().trim()));
                }
                if (form.getFileType() != null && form.getFileType().longValue() != -1) {
                    hql += "AND f.fileType = ? ";
                    lstParam.add(form.getFileType());
                }
                if (form.getSendDateFrom() != null) {
                    hql += "AND f.sendDate >= ? ";
                    lstParam.add(form.getSendDateFrom());
                }
                if (form.getSendDateTo() != null) {
                    hql += "AND f.sendDate <= ? ";
                    lstParam.add(form.getSendDateTo());
                }
                if (deptId != null) {
                    hql += "AND (f.agencyId = ? or p.receiveUserId = ? or p.receiveGroupId=? ) and (p.processStatus=? or p.processStatus=? or p.processStatus =? ) ";

                    lstParam.add(deptId);
                    lstParam.add(userId);
                    lstParam.add(deptId);
                    lstParam.add(Constants.FILE_STATUS.NEW);
                    lstParam.add(Constants.FILE_STATUS.RECEIVED);
                    lstParam.add(Constants.FILE_STATUS.PROPOSED);
                } else {
                    hql += "AND (p.receiveUserId = ? and (p.processStatus=?) and p.status=? ) ";
                    lstParam.add(userId);
                    lstParam.add(Constants.FILE_STATUS.RECEIVED);
                    lstParam.add(0l);
                }
            }
            // loc file_id nhom san pham khac
            if (form.getProductTypeNew() != null && form.getProductTypeNew().longValue() != -1) {
                if (form.getProductTypeNew() == 1) {
                    hql += " and (c.code <> ? and c.code <> ?)";
                    lstParam.add("TPCN");
                    lstParam.add("DBT");
                } else {
                    hql += " and (c.code = ? or c.code = ?)";
                    lstParam.add("TPCN");
                    lstParam.add("DBT");
                }
            }

            // hieptq update 251114
            if (form.getSearchTypeNew() != null) {
                if (form.getSearchTypeNew() == 1) {
                    hql += " and (c.code <> ? and c.code <> ?)";
                    lstParam.add("TPCN");
                    lstParam.add("DBT");
                } else {
                    hql += " and (c.code = ? or c.code = ?)";
                    lstParam.add("TPCN");
                    lstParam.add("DBT");
                }
            }

            Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql);
            Query query = getSession().createQuery("select distinct f " + hql + "order by f.modifyDate DESC)");
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }

            query.setFirstResult(start);
            query.setMaxResults(count);
            int total = Integer.parseInt(countQuery.uniqueResult().toString());
            List<FilesNoClob> lstResult = query.list();
            GridResult gr = new GridResult(total, lstResult);
            return gr;
        } catch (Exception e) {
            log.error(e);
            return new GridResult(0, null);
        }
    }

    public GridResult searchLookupFilesAfterAnnounced(
            FilesForm form,
            Long deptId,
            Long userId,
            String userType,
            int start,
            int count,
            String sortField,
            String sortCustom
    ) {
        try {
            return createQueryLookupFilesAfterAnnounced(form, deptId, userId, userType, start, count, sortField, sortCustom);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new GridResult(0, null);
        }
    }

    public GridResult createQueryLookupFilesAfterAnnounced(
            FilesForm form,
            Long deptId,
            Long userId,
            String userType,
            int start,
            int count,
            String sortField,
            String sortCustom
    ) {
        List lstParam = new ArrayList();
        String hql = " from FilesNoClob f, Process p, Business b ";
        String condition = "";
        condition += " and f.fileId = p.objectId";
        condition += " and f.deptId = b.businessId";
        condition += " and f.isActive = 1"
                + " and f.fileId = p.objectId"
                + "  and (f.isTemp = null or f.isTemp = 0 )";
        if (userType.equals(Constants.ROLES.LEAD_OFFICE_ROLE)
                || userType.equals(Constants.ROLES.STAFF_ROLE)
                || userType.equals(Constants.ROLES.LEAD_UNIT)) {
        }
        if (form != null) {
            if (userType.equals(Constants.ROLES.STAFF_ROLE)
                    && form.getSearchType() != null) {//vcv
                switch (Integer.parseInt(form.getSearchType().toString())) {
                    case 42:
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED);
                        condition += " and f.staffProcess =?";
                        lstParam.add(userId);
                        break;
                    case 47:
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                        condition += " and f.staffProcess =?";
                        lstParam.add(userId);
                        break;
                    case -26://Hồ sơ đã xem xét cv sđbs chờ phê duyệt công văn
                        lstParam.clear();
                        hql = "from FilesNoClob f, Process p";
                        condition = " AND f.isActive = 1"
                                + " AND (f.isTemp = null or f.isTemp = 0 )"
                                + " AND f.fileId = p.objectId"
                                + " AND f.status = ?"
                                + " AND p.receiveGroupId = ?"
                                + " AND p.receiveUserId = ?";
                        lstParam.add(Constants.FILE_STATUS.REVIEW_TO_ADD);
                        lstParam.add(deptId);
                        lstParam.add(userId);
                        break;
                    case -20://Ho so da gui thong bao sdbs cho doanh nghiep
                        lstParam.clear();
                        hql = "from FilesNoClob f";
                        condition = " AND f.isActive=1";
                        condition += " AND (f.status = ?)";
                        condition += " AND f.staffProcess=?";
                        condition += " AND (f.isTemp = null or f.isTemp = 0 ) ";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED_TO_ADD);
                        lstParam.add(userId);
                        break;
                    case 33://Hồ sơ chờ thẩm định SĐBS CV
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        condition += " AND p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        if (userId != null) {
                            condition += " and f.staffProcess=?";
                            lstParam.add(userId);
                        }
                        condition += " AND f.status = ?";
                        lstParam.add(Constants.FILE_STATUS.RECEIVED_TO_ADD);
                        break;
                    case 19://Hồ sơ lãnh đạo cục yêu cầu bổ sung CV
                        lstParam.clear();
                        hql = " from FilesNoClob f";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        condition += " AND p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                        condition += " AND f.status = ?";
                        lstParam.add(Constants.FILE_STATUS.REVIEWED_TO_ADD);
                        break;
                    case 5://Hồ sơ cần thông báo đối chiếu
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " and f.fileId = p.objectId";
                        condition += " AND f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        condition += " AND p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                        condition += " AND f.status = ?";
                        lstParam.add(Constants.FILE_STATUS.REVIEWED);
                        break;
                    case 50:
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " and f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        condition += " AND (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                        condition += " AND p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                        break;
                    case 417:
                        lstParam.clear();
                        hql = " from FilesNoClob f";
                        condition = " AND f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND (f.status =?)";
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        if (userId != null) {
                            condition += " and f.staffProcess=?";
                            lstParam.add(userId);
                        }
                        condition += " AND f.isSignPdf <> 2";
                        break;
                    case 422:
                        lstParam.clear();
                        hql = " from FilesNoClob f";
                        condition = " AND f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                        if (userId != null) {
                            condition += " and f.staffProcess=?";
                            lstParam.add(userId);
                        }
                        condition += " AND f.isSignPdf = 2";
                        break;
                    case 423:
                        lstParam.clear();
                        hql = " from FilesNoClob f";
                        condition = " AND f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND (f.status <> ?)";
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                        if (userId != null) {
                            condition += " and f.staffProcess=?";
                            lstParam.add(userId);
                        }
                        condition += " AND f.isSignPdf = 2";
                        break;
                    case 447:
                        lstParam.clear();
                        hql = " from FilesNoClob f";
                        condition = " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND f.staffProcess=?";
                        lstParam.add(userId);
                        condition += " AND (f.status =?)";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED);
                        break;
                    case 448:
                        lstParam.clear();
                        hql = " from FilesNoClob f";
                        condition = " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND f.staffProcess=?";
                        lstParam.add(userId);
                        condition += " AND (f.status =?)";
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                        break;
                    case 20://Hồ sơ chờ chuyên viên trong tổ thẩm định
                        lstParam.clear();
                        hql = "from FilesNoClob f, Process p";
                        condition = " AND f.isActive = 1";
                        condition += " AND (f.isTemp = null or f.isTemp = 0 )";
                        condition += " AND f.fileId = p.objectId";
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                        condition += " and p.processType=0";
                        condition += " and f.status = ?";
                        lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                        break;
                    case 21://Hồ sơ gửi phối hợp chưa cho ý kiến 21
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.isActive = 1 ";
                        condition += " AND (f.isTemp = null or f.isTemp = 0 ) ";
                        condition += " AND f.fileId = p.objectId ";
                        condition += " AND p.receiveGroupId = ? ";
                        lstParam.add(deptId);
                        if (userId != null) {
                            condition += " AND p.sendUserId = ? ";
                            lstParam.add(userId);
                        }
                        condition += " AND (f.status = ?) ";
                        condition += " AND p.processStatus = ?";
                        lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                        lstParam.add(Constants.FILE_STATUS.ASSIGNED);
                        condition += " AND (p.processId in (select p.processId from Process p where p.processId not in (select distinct pc.processId from ProcessComment pc))) ";
                        condition += " AND (p.processType=0 or p.processType=4) ";
                        break;
                    case 22://Hồ sơ chuyên viên đã gửi thông báo sửa đổi bổ sung
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND p.receiveGroupId =?";
                        lstParam.add(deptId);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                        condition += " AND (f.status =?)";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED_TO_ADD);
                        break;
                    case 26:
                        lstParam.clear();
                        hql = "from FilesNoClob f, Process p";
                        condition = " AND f.isActive=1";
                        condition += " AND f.fileId = p.objectId";
                        condition += " AND f.status = ?";
                        condition += " AND p.receiveUserId=?";
                        condition += " AND p.processType=1";
                        condition += " AND (f.isTemp = null or f.isTemp = 0 ) ";
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_EVALUATE);
                        lstParam.add(userId);
                        break;
                    case 27:
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.isActive = 1 ";
                        condition += " AND (f.isTemp = null or f.isTemp = 0 ) ";
                        condition += " AND f.fileId = p.objectId ";
                        condition += " AND p.receiveGroupId = ? ";
                        lstParam.add(deptId);
                        if (userId != null) {
                            condition += " AND p.sendUserId = ? ";
                            lstParam.add(userId);
                        }
                        condition += " AND (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED_TO_ADD);
                        condition += " AND sysdate - f.evaluateAddDate >= 54";
                        break;
                }
            }
            if (userType.equals(Constants.ROLES.CLERICAL_ROLE) && form.getSearchType() != null) {//vvt
                switch (Integer.parseInt(form.getSearchType().toString())) {
                    case -11:
                        /*tim kiem bao cao van thu xu ly ho so trong khoang thoi gian.
                         binhnt53 add 150211
                         */
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)";
                        if (form.getStatus() != null) {
                            condition += " AND p.status = ?";
                            lstParam.add(form.getStatus());
                        }
                        if (form.getSearchDateFrom() != null) {
                            condition += " and p.sendDate >= ?";
                            lstParam.add(form.getSearchDateFrom());
                        }
                        if (form.getSearchDateTo() != null) {
                            condition += " and p.sendDate <= ?";
                            lstParam.add(form.getSearchDateTo());
                        }
                        break;
                    case 1623://hồ sơ cần đối chiếu
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1 and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND (f.status =? )";
                        //lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                        lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
                        break;
                    case 22:
                        hql = " from FilesNoClob f, Process p";
                        lstParam.clear();
                        condition = " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0)";
                        condition += " and (f.status = ? or f.status = ? or f.status = ? or f.status = ? or f.status = ? or f.status = ?)";
//                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);
                        lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON);
                        lstParam.add(Constants.FILE_STATUS.COMPARED);
                        lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                        condition += " and p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        break;
                    case 15:
                        hql = " from FilesNoClob f, Process p";

                        lstParam.clear();
                        condition = " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0)"
                                + " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.COMPARED);
                        condition += " and p.receiveGroupId = ?";
                        condition += " and f.isFee = 1 and f.isSignPdf = 2";
                        lstParam.add(deptId);
                        break;
                    case 6:
                        hql = " from FilesNoClob f, Process p";

                        lstParam.clear();
                        condition = " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0)"
                                + " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        condition += " and p.receiveGroupId = ?";
                        condition += " and f.isFee = 1 and f.isSignPdf = 2";
                        condition += " and f.isDownload <> 1";//u150112 binhnt53
                        lstParam.add(deptId);
                        break;
                    case 20:
                        hql = " from FilesNoClob f, Process p";

                        lstParam.clear();
                        condition = " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0)"
                                + " and (f.status = ?)";
                        lstParam.add(form.getStatus());
                        condition += " and p.sendGroupId = ?";
                        lstParam.add(deptId);
                        break;
                    case -27:
                        hql = " from FilesNoClob f, Process p";

                        lstParam.clear();
                        condition = " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0)"
                                + " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.APPROVE_TO_ADD);
                        condition += " and p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        break;
                    case -6://hồ sơ cần kí xác thực văn thư: hồ sơ đã đối chiếu, đã thanh toán
                        hql = " from FilesNoClob f, Process p";
                        condition = " and f.fileId = p.objectId"
                                + " and (f.isTemp=null or f.isTemp=0)"
                                + " and f.isActive= 1"
                                + " and f.isSignPdf = 1"
                                + " and f.isFee = 1"
                                + " and f.status = ?"
                                + " and p.receiveGroupId = ?";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        lstParam.add(deptId);
                        break;
                    case -4:
                        hql = " from FilesNoClob f";
                        condition = " and (f.isTemp=null or f.isTemp=0)"
                                + " and f.isActive=1 "
                                + " and f.isFee <> 1 and f.isSignPdf <> 2"
                                + " and (f.status=?)"
                                + " and f.agencyId = ?";
                        lstParam.clear();
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        lstParam.add(deptId);
                        break;
                    case 0:
                        hql = " from FilesNoClob f, Process p";
                        lstParam.clear();
                        condition = " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0)"
                                + " and (f.status = ?)";
                        lstParam.add(form.getStatus());
                        condition += " and p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        break;
                    case 110://danh sach ho so can luu tru
                        hql = " from FilesNoClob f, Process p";
                        lstParam.clear();
                        condition = " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " and (f.isTemp = null or f.isTemp = 0)"
                                + " and f.status <> ?"
                                + " and f.isFee <> 2";
                        condition += " and p.receiveGroupId = ?";
                        lstParam.add(Constants.FILE_STATUS.NEW_CREATE);
                        lstParam.add(deptId);
                        break;
                    default:;
                }
            }
            if (userType.equals(Constants.ROLES.LEAD_UNIT) && form.getSearchType() != null) {//vldp
                switch (Integer.parseInt(form.getSearchType().toString())) {
                    case 39:
                        condition += " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " AND f.status = ?"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_REVIEW);
                        if (userId != null) {
                            condition += " and f.leaderReviewId=?";
                            lstParam.add(userId);
                        }
                        break;
                    case 30:
                        condition += " and f.isActive = 1"
                                + " and f.fileId = p.objectId"
                                + " AND f.status = ?"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED_TO_ADD);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                        if (deptId != null) {
                            condition += " AND p.receiveGroupId = ? ";
                            lstParam.add(deptId);
                        }
                        break;
                    case 2:
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED);
                        condition += " and p.receiveGroupId = ?"
                                + " and p.receiveUserId = ?";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                        condition += " and ((f.leaderEvaluateId = ?"
                                + " and f.leaderReviewId = null) or f.leaderReviewId =?)";
                        lstParam.add(userId);
                        lstParam.add(userId);
                        break;
                    case 7:
                        condition += " and (f.status = ?)";////150204 binhnt53 update
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                        condition += " and p.receiveGroupId = ?"
                                + " and p.receiveUserId = ?";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                        condition += " and (f.leaderReviewId =?)";
                        lstParam.add(userId);
                        break;
                    case 8:// ho so cho xem xet du thao sdbs
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.EVALUATE_TO_ADD);
                        condition += " and p.receiveGroupId = ?";
                        lstParam.add(deptId);
                        condition += " and p.receiveUserId = ?";
                        lstParam.add(userId);
                        condition += " and f.leaderReviewId = ?";
                        lstParam.add(userId);
                        break;
                    case 5://ho so cho lanh dao cuc phe duyet, lanh dao phong da duyet
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.REVIEWED);
                        condition += " and p.receiveGroupId = ?"
                                + " and p.receiveUserId = ?";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                        break;
                    case 22://ho so cho lanh dao cuc phe duyet, lanh dao phong da duyet
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                        break;
                }
            }
            if (userType.equals(Constants.ROLES.LEAD_OFFICE_ROLE) && form.getSearchType() != null) {//vldc
                switch (Integer.parseInt(form.getSearchType().toString())) {
                    case 3:
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.REVIEWED);
                        condition += " and p.receiveGroupId = ?"
                                + " and p.receiveUserId = ?";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                        break;
                    case 10:
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        condition += " and p.receiveGroupId = ?"
                                + " and p.receiveUserId = ?";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                        break;
                    case 26:
                        condition += " and (f.status = ?)";
                        lstParam.add(Constants.FILE_STATUS.REVIEW_TO_ADD);
                        condition += " and p.receiveGroupId = ?"
                                + " and p.receiveUserId = ?";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                        break;
                    case 36://a260515 binhnt
                        condition += " and f.status in (?,?,?,?,?,?)"
                                + " and f.isActive = 1 "
                                + " and (f.isTemp is null or f.isTemp = 0)";
                        lstParam.add(Constants.FILE_STATUS.GIVE_BACK);
                        lstParam.add(Constants.FILE_STATUS.ALERT_COMPARISON);
                        lstParam.add(Constants.FILE_STATUS.COMPARED);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON);
                        lstParam.add(Constants.FILE_STATUS.COMPARED_FAIL);
                        lstParam.add(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL);
                        break;
                }
            }
            if (userType.equals(Constants.ROLES.ADMIN)
                    && form.getSearchType() != null) {//vQTHT
                switch (Integer.parseInt(form.getSearchType().toString())) {
                    case 110://hồ sơ cần đối chiếu
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p";
                        condition = " AND f.fileId = p.objectId";
                        condition += " AND f.isActive = 1"
                                + " and (f.isTemp = null or f.isTemp = 0)";
                        condition += " AND (f.staffProcess <> null)";
                        break;
                }
            }
            if (form.getSearchType() == null) {
                if (form.getStatus() != null
                        && form.getSearchType() == null
                        && (form.getStatus() == 40l
                        || form.getStatus() == 41l
                        || form.getStatus() == 42l
                        || form.getStatus() == 43l
                        || form.getStatus() == 44l
                        || form.getStatus() == 45l
                        || form.getStatus() == 46l
                        || form.getStatus() == 47l
                        || form.getStatus() == 48l
                        || form.getStatus() == 49l
                        || form.getStatus() == 30l
                        || form.getStatus().equals(Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL)
                        || form.getStatus() == 50l)) {
                    lstParam.clear();
                    hql = " from FilesNoClob f, Process p, Business b";
                    condition = " and f.fileId = p.objectId"
                            + " and f.deptId = b.businessId";
                    // Nhà sản xuất
                    if (form.getManufactureName() != null
                            && form.getManufactureName().length() > 0) {
                        hql += ", Announcement ann";
                        condition += " and f.announcementId = ann.announcementId"
                                + " and lower(ann.manufactureName) like ? ESCAPE '/'";
                        lstParam.add(StringUtils.toLikeString(form.getManufactureName().toLowerCase().trim()));
                    }
                    // Số chứng nhận công bố
                    if (form.getAnnouncementNo() != null && form.getAnnouncementNo().length() > 0) {
                        hql += ", AnnouncementReceiptPaper ann";
                        condition += " and f.announcementReceiptPaperId = ann.announcementReceiptPaperId and lower(ann.receiptNo) like ? ESCAPE '/'";
                        lstParam.add(StringUtils.toLikeString(form.getAnnouncementNo().toLowerCase().trim()));
                    }
//                // thong ke ho so trong ngay
                    if (form.getStatus() != null && form.getStatus() == 40l) {
                        condition += " and f.isActive = 1"
                                + " and (f.status = ? or f.status = ?)"
                                + " and  to_date(f.sendDate,'yyyy/mm/dd') = to_date(sysdate,'yyyy/mm/dd')  ";
                        lstParam.add(3l);
                        lstParam.add(5l);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }
                    if (form.getStatus() != null && form.getStatus() == 41l) {
                        condition += " AND f.isActive = 1"
                                + " AND (f.status = ? or f.status = ?)"
                                + " AND  to_date(f.sendDate,'yyyy/mm/dd') = to_date(sysdate,'yyyy/mm/dd')"
                                + " AND (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(4l);
                        lstParam.add(5l);
                    }
                    if (form.getStatus() != null && form.getStatus() == 42l) {
                        condition += " and f.isActive = 1"
                                + " and (f.status = ? or f.status = ?)"
                                + " and  to_date(f.sendDate,'yyyy/mm/dd') = to_date(sysdate,'yyyy/mm/dd')"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(6l);
                        lstParam.add(5l);
                    }
                    // ho so bi tra tham dinh lai
                    if (form.getStatus() != null && form.getStatus() == 43l) {
                        condition += " and f.isActive=1"
                                + " and f.status = ?"
                                + " and p.processType=1"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(8l);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }
                    // ho so cho phoi hop tham dinh chua cho y kien
                    if (form.getStatus() != null && form.getStatus() == 44l) {
                        condition += " and f.isActive=1"
                                + " and (f.status = ?)"
                                + " and (p.processType=0 or p.processType=4)"
                                + " and (p.processId in (select p.processId from Process p where p.processId not in (select distinct pc.processId from ProcessComment pc)))"
                                + " and p.processStatus = ?";
                        lstParam.add(3l);
                        lstParam.add(3l);
                    }

                    //ho so cho xem xet
                    if (form.getStatus() != null && form.getStatus() == 48L) {
                        condition += " and f.isActive=1"
                                + " and (f.status = ? or f.status = ?)"
                                + " and (p.processType=1 or p.processType=0)"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(Constants.FILE_STATUS.EVALUATED);
                        lstParam.add(Constants.FILE_STATUS.FEDBACK_TO_ADD);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }
                    // ho so lanh dao phong da xem xet
                    if (form.getStatus() != null && form.getStatus() == 46l) {
                        condition += " and f.isActive=1"
                                + " and f.status = ?"
                                + " and p.processType=1"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(5l);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }
                    //ho so da phe duyet
                    if (form.getStatus() != null && form.getStatus() == 47l) {
                        condition += " and f.isActive=1"
                                + " and f.status = ?"
                                + " and p.processType=1"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(Constants.FILE_STATUS.APPROVED);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }
                    // ho so tham dịnh da gui ý kiễn phản hồi
                    if (form.getStatus() != null && form.getStatus() == 49l) {
                        lstParam.clear();
                        hql = " from FilesNoClob f, Process p, ProcessComment pc";
                        condition = " AND f.isActive=1"
                                + " AND f.fileId = p.objectId"
                                + " AND p.objectType = 30"
                                + " AND (f.status = ?)"
                                + " AND (p.processType=0 or p.processType=1)"
                                + " AND pc.processId = p.processId"
                                + " AND (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(3l);
                        if (userId != null) {
                            condition += " AND p.receiveGroupId = (select distinct p.receiveGroupId from Process p where p.receiveUserId = ?)";
                            lstParam.add(userId);
                        }
                    }
                    if (form.getStatus() != null && form.getStatus() == 50l) {
                        condition += " and f.isActive=1"
                                + " and f.status = ?"
                                + " and p.processType=1"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(3l);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }
                    // da thong bao SDBS
                    if (form.getStatus() != null && form.getStatus() == 30l) {
                        condition += " and f.isActive=1"
                                + " and f.status = ?"
                                + " and p.processType=1"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(20l);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }

                    // da tham dinh - hieptq
                    if (form.getStatus() != null && form.getStatus() == 45l) {
                        condition += " and f.isActive=1"
                                + " and f.status = ?"
                                + " and p.processType=1"
                                + " and (f.isTemp = null or f.isTemp = 0 )";
                        lstParam.add(4l);
                        if (userId != null) {
                            condition += " and p.receiveUserId=?";
                            lstParam.add(userId);
                        }
                    }

                    //
                    if (form.getStatus() != null
                            && form.getStatus() == Constants.FILE_STATUS.REVIEW_COMPARISON_FAIL) {
                        condition += " and f.isActive = 1"
                                + " and f.status = ?"
                                + " and (f.isTemp = null or f.isTemp = 0)";
                        lstParam.add(25l);
                    }
                } else {
                    if (form.getStatus() != null
                            && form.getStatus() >= 0l) {
                        condition += " AND f.status = ?";
                        lstParam.add(form.getStatus());
                    } else {
                    }
                }

                if (userType.equals(Constants.ROLES.LEAD_OFFICE_ROLE)) {
                    if (deptId != null) {
                        condition += " and p.receiveGroupId = ? ";
                        lstParam.add(deptId);
                    }
                }
                if (userType.equals(Constants.ROLES.STAFF_ROLE)) {
                    if (deptId != null) {
                        if (form.getStatus() != null && form.getStatus() != 40l) {
                            condition += " and p.receiveGroupId = ? ";
                            lstParam.add(deptId);
                        }
                    }
                    if (userId != null) {
                        condition += " and p.receiveUserId = ? ";
                        lstParam.add(userId);
                    }
                }
                if (userType.equals(Constants.ROLES.LEAD_UNIT)) {
                    if (deptId != null) {
                        condition += " and p.receiveGroupId = ?"
                                + " and ( p.receiveUserId = null or p.receiveUserId = ?) ";
                        lstParam.add(deptId);
                        lstParam.add(userId);
                    }
                }
                if (userType.equals(Constants.ROLES.CLERICAL_ROLE)) {
                    if (deptId != null) {
//                    hql += "AND f.agencyId = ?)";
//                    lstParam.add(deptId);
                        condition += " and p.receiveGroupId = ? ";
                        lstParam.add(deptId);
                    }
                }
            }
            // Nhà sản xuất
            if (form.getManufactureName() != null
                    && form.getManufactureName().length() > 0) {
                String str = ", Announcement ann";
                if (!str.contains(hql)) {
                    hql += ", Announcement ann";
                }
                condition += " and f.announcementId = ann.announcementId"
                        + " and lower(ann.manufactureName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getManufactureName().toLowerCase().trim()));
            }
            // Số chứng nhận công bố
            if (form.getAnnouncementNo() != null && form.getAnnouncementNo().length() > 0) {
                String str = ", AnnouncementReceiptPaper arp";
                if (!str.contains(hql)) {
                    hql += ", AnnouncementReceiptPaper arp";
                    condition += " and f.announcementReceiptPaperId = arp.announcementReceiptPaperId";
                }
                condition += " and lower(arp.receiptNo) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getAnnouncementNo().toLowerCase().trim()));
            }
            // ngay cap
            if (form.getReceiptDate() != null) {
                String str = ", AnnouncementReceiptPaper arp";
                if (!str.contains(hql)) {
                    hql += ", AnnouncementReceiptPaper arp";
                    condition += " and f.announcementReceiptPaperId = arp.announcementReceiptPaperId";
                }
                condition += " and lower(arp.receiptDate) = ?";
                lstParam.add(form.getReceiptDate());
            }
            //thương nhân chịu trách nhiệm
            if (form.getOrigin() != null && form.getOrigin().length() > 0) {
                String str = ", DetailProduct d";
                if (!hql.contains(str)) {
                    hql += ", DetailProduct d";
                    condition += " and f.detailProductId = d.detailProductId";
                }
                condition += " and lower(d.origin) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getOrigin().toLowerCase().trim()));
            }
            // điện thoại doanh nghiệp
            if (form.getBusinessTelephone() != null && form.getBusinessTelephone().length() > 0) {
                String str = ", Announcement ann";
                if (!hql.contains(str)) {
                    hql += ", Announcement ann";
                    condition += " and f.announcementId = ann.announcementId";
                }
                condition += " and lower(ann.businessTelephone) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getBusinessTelephone().toLowerCase().trim()));
            }
            // fax doanh nghiệp
            if (form.getBusinessFax() != null && form.getBusinessFax().length() > 0) {
                String str = ", Announcement ann";
                if (!hql.contains(str)) {
                    hql += ", Announcement ann";
                    condition += " and f.announcementId = ann.announcementId";
                }
                condition += " and lower(ann.businessFax) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getBusinessFax().toLowerCase().trim()));
            }
            // mã hồ sơ
            if (form.getFileCode() != null && !"".equals(form.getFileCode().trim())) {
                condition += " AND lower(f.fileCode) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getFileCode().toLowerCase().trim()));
            }
            if (form.getFileType() != null && form.getFileType().longValue() != -1) {
                condition += " AND f.fileType = ?";
                lstParam.add(form.getFileType());
            }
            if (form.getSignDate() != null) {
                condition += " AND f.signDate = ?";
                lstParam.add(form.getSignDate());
            }
            if (form.getSendDateFrom() != null) {
                condition += " AND f.sendDate >= ?";
                lstParam.add(minDayToCompare(form.getSendDateFrom()));
            }
            if (form.getSendDateTo() != null) {
                condition += " AND f.sendDate <= ?";
                lstParam.add(maxDayToCompare(form.getSendDateTo()));
            }
            if (form.getSignDateFrom() != null) {
                condition += " AND f.signDate >= ? ";
                lstParam.add(minDayToCompare(form.getSignDateFrom()));
            }
            if (form.getSignDateTo() != null) {
                condition += " AND f.signDate <= ? ";
                lstParam.add(maxDayToCompare(form.getSignDateTo()));
            }
            if (form.getSignDateFrom() != null || form.getSignDateFrom() != null) {
                condition += " AND f.status = ? ";
                lstParam.add(22l);
            }
            if (form.getRepDateFrom() != null) {
                condition += " AND f.repDate >= ?";
                lstParam.add(minDayToCompare(form.getRepDateFrom()));
            }
            if (form.getRepDateTo() != null) {
                condition += " AND f.repDate <= ?";
                lstParam.add(maxDayToCompare(form.getRepDateTo()));
            }
            if (form.getApproveDateFrom() != null) {
                condition += " AND f.approveDate >= ?";
                lstParam.add(minDayToCompare(form.getApproveDateFrom()));
            }
            if (form.getApproveDateTo() != null) {
                condition += " AND f.approveDate <= ?";
                lstParam.add(maxDayToCompare(form.getApproveDateTo()));
            }
            if (form.getLeaderStaffSignName() != null
                    && form.getLeaderStaffSignName().length() > 0) {
                condition += " AND lower(f.leaderStaffSignName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getLeaderStaffSignName().toLowerCase().trim()));
            }
            if (form.getBusinessName() != null
                    && form.getBusinessName().length() > 0) {
                condition += " AND lower(f.businessName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getBusinessName().toLowerCase().trim()));
            }
            if (form.getNationName() != null
                    && form.getNationName().length() > 0) {
                condition += " AND lower(f.nationName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getNationName().toLowerCase().trim()));
            }
            // Người thẩm định
            if (form.getReceiveUser() != null && form.getReceiveUser().length() > 0) {
                condition += " AND lower(p.receiveUser) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getReceiveUser().toLowerCase().trim()));
            }
            // Số đăng ký kinh doanh
            if (form.getBusinessLicence() != null
                    && form.getBusinessLicence().length() > 0) {
                condition += " AND lower(b.businessLicense) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getBusinessLicence().toLowerCase().trim()));
            }
            // Địa chỉ doanh nghiệp
            if (form.getBusinessAddress() != null
                    && form.getBusinessAddress().length() > 0) {
                condition += " AND lower(f.businessAddress) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getBusinessAddress().toLowerCase().trim()));
            }
            // Tên sản phẩm
            if (form.getProductName() != null
                    && form.getProductName().length() > 0) {
                condition += " AND lower(f.productName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getProductName().toLowerCase().trim()));
            }
            // Nhóm sản phẩm
            if (form.getProductType() != null
                    && form.getProductType() != -1l) {
                condition += " AND d.productType = ?";
                lstParam.add(form.getProductType());
            }
            // Lãnh đạo phòng
            if (form.getLeaderStaff() != null
                    && form.getLeaderStaff().length() > 0) {
                condition += " AND lower(p.receiveUser) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getLeaderStaff().toLowerCase().trim()));
            }
            // Người thẩm xét
            if (form.getStaff() != null
                    && form.getStaff().trim().length() > 0) {
                condition += " AND lower(f.nameStaffProcess) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getStaff().toLowerCase().trim()));
            }
            if (form.getNameStaffProcess() != null
                    && form.getNameStaffProcess().length() > 0) {
                condition += " AND lower(f.nameStaffProcess) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getNameStaffProcess().toLowerCase().trim()));
            }
            if (form.getLeaderApproveName() != null
                    && form.getLeaderApproveName().length() > 0) {
                condition += " AND lower(f.leaderApproveName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getLeaderApproveName().toLowerCase().trim()));
            }
            if (form.getLeaderAssignName() != null
                    && form.getLeaderAssignName().length() > 0) {
                condition += " AND lower(f.leaderAssignName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getLeaderAssignName().toLowerCase().trim()));
            }
            if (form.getLeaderReviewName() != null
                    && form.getLeaderReviewName().length() > 0) {
                condition += " AND lower(f.leaderReviewName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getLeaderReviewName().toLowerCase().trim()));
            }
            // Tỉnh -  Thành Phố
            if (form.getProductType() != null
                    && form.getBusinessProvinceId() != -1l) {
                condition += " AND b.businessProvinceId = ? ";
                lstParam.add(form.getBusinessProvinceId());
            }
            // Nơi lưu trữ
            if (form.getRepositoriesId() != null
                    && form.getRepositoriesId() != -1l) {
                condition += " AND (f.repositoriesId = ? ) ";
                lstParam.add(form.getRepositoriesId());
            }
            // Lọc theo người tạo ( lưu trữ hồ sơ giấy )
            if (form.getRepCreator() != null
                    && (form.getSearchType() == 0
                    || form.getSearchType() == 2)) {
            }
            // Kho lưu trữ
            if (form.getRepName() != null
                    && form.getRepName() != -1l) {
                condition += " AND (f.repositoriesId = ? )";
                lstParam.add(form.getRepName());
            }
            // Trạng thái lưu trữ
            // Đã lưu trữ
            if (form.getRepStatus() != null && form.getRepStatus() == 1) {
                condition += " AND (f.repositoriesId <> null )";
            }
            // Chưa lưu trữ
            if (form.getRepStatus() != null && form.getRepStatus() == 2) {
                condition += " AND (f.repositoriesId = null )";
            }
            //lãnh đạo phân công - 140915 binhnt53
            if (form.getLeaderAssignId() != null && form.getLeaderAssignId() > 0L) {
                condition += " AND (f.leaderAssignId = ? )";
                lstParam.add(form.getLeaderAssignId());
            }
            // tra cuc van thu ngay tiep nhan tu ngay den ngay
            if (form.getReceivedDate() != null) {
                condition += " and f.receivedDate >= ?";
                lstParam.add(minDayToCompare(form.getReceivedDate()));
            }
            if (form.getReceivedDateTo() != null) {
                condition += " and f.receivedDate <= ?";
                lstParam.add(maxDayToCompare(form.getReceivedDateTo()));
            }
            if (form.getReceiveNo() != null
                    && !form.getReceiveNo().equals("")
                    && !form.getReceiveNo().trim().equals("")) {
                condition += " and f.receiveNo like ?";
                lstParam.add(form.getReceiveNo().trim());
            }
            if (form.getIs30() != null && form.getIs30() != -1l) {
                if (form.getIs30() == 1) {
                    condition += " AND (f.is30 = 1 )";
                } else {
                    condition += " AND (f.is30 = null )";
                }
            }
            if (form.getSearchFullText() != null
                    && form.getSearchFullText().trim().length() > 0) {
                condition += " and f.fileId in (select ffs.fileId from FileForSearch ffs where lower(ffs.content) like ? ESCAPE '/')";
                lstParam.add(StringUtils.toLikeString(form.getSearchFullText()));
            }
        }
        if (form.getProductTypeNew() != null
                && form.getProductTypeNew().longValue() != -1) {
            String str = ", Category c";
            if (!hql.contains(str)) {
                hql += ", Category c";
                condition += " and  f.productTypeId = c.categoryId";
            }
            if (form.getProductTypeNew() == 1) {
                condition += " AND (c.code <> ? and c.code <> ?) ";
                lstParam.add(Constants.CATEGORY_TYPE.TPCN);
                lstParam.add(Constants.CATEGORY_TYPE.DBT);
            } else {
                condition += " AND (c.code = ? or c.code = ?) ";
                lstParam.add(Constants.CATEGORY_TYPE.TPCN);
                lstParam.add(Constants.CATEGORY_TYPE.DBT);
            }
        }
        if (form.getSearchTypeNew() != null) {
            if (form.getSearchTypeNew() == 1) {
                condition += " AND (c.code <> ? and c.code <> ?) ";
                lstParam.add(Constants.CATEGORY_TYPE.TPCN);
                lstParam.add(Constants.CATEGORY_TYPE.DBT);
            } else {
                condition += " AND (c.code = ? or c.code = ?) ";
                lstParam.add(Constants.CATEGORY_TYPE.TPCN);
                lstParam.add(Constants.CATEGORY_TYPE.DBT);
            }
        }
        //!hieptq update
        Query countQuery = getSession().createQuery("select count(distinct f.fileId) " + hql + " where 1=1 " + condition);
        String finalSql = "select distinct f " + hql + " where 1=1 " + condition + " order by ";
        if (sortCustom.isEmpty()) {
            finalSql += "f.modifyDate DESC";
        } else {
            finalSql += sortCustom;
        }
        Query query = getSession().createQuery(finalSql);

        for (int i = 0;
                i < lstParam.size();
                i++) {
            query.setParameter(i, lstParam.get(i));
            countQuery.setParameter(i, lstParam.get(i));
        }

        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        query.setFirstResult(start);
        if (count > 0) {
            query.setMaxResults(count);
        } else {
            query.setMaxResults(total);
        }
        List<FilesNoClob> lstResult = query.list();

        return new GridResult(total, lstResult);
    }
}
