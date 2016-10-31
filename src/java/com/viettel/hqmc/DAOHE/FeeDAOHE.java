/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.Constants;
import com.viettel.common.util.DateTimeUtils;
import com.viettel.common.util.LogUtil;
import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.CountNo;
import com.viettel.hqmc.BO.Fee;
import com.viettel.hqmc.BO.FeePaymentInfo;
import com.viettel.hqmc.BO.FeeProcedure;
import com.viettel.hqmc.BO.Files;
import com.viettel.hqmc.BO.Procedure;
import com.viettel.hqmc.FORM.FeeForm;
import com.viettel.hqmc.FORM.FeePaymentFileForm;
import com.viettel.voffice.database.BO.Process;
import com.viettel.voffice.database.BO.VoAttachs;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.EventLogDAOHE;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import com.viettel.voffice.database.DAOHibernate.ProcessDAOHE;
import com.viettel.voffice.database.DAOHibernate.VoAttachsDAOHE;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.apache.struts2.ServletActionContext.getRequest;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

/**
 *
 * @author hieptq
 */
public class FeeDAOHE extends GenericDAOHibernate<Fee, Long> {

    private List lstStandard;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FeeDAOHE.class);
//========================================================================

    public FeeDAOHE() {
        super(Fee.class);
    }

    public List findAllFee(Long UserId) {
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Fee u ");
            stringBuilder.append("where u.isActive=1");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstStandard = query.list();
        } catch (HibernateException ex) {
//            log.error(ex.getMessage());
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<>();
        }
        return lstStandard;
    }

    public List findAllFee() {
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Fee u ");
            stringBuilder.append("where u.isActive=1");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstStandard = query.list();
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return new ArrayList<>();
        }
        return lstStandard;
    }

    public List findAllFeeByProcedureId(Long procedureId) {
        try {
            StringBuilder stringBuilder = new StringBuilder(" from FeeProcedure u ");
            stringBuilder.append("where u.isActive=1 and u.procedureId = ?");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, procedureId);
            lstStandard = query.list();
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return new ArrayList<>();
        }
        return lstStandard;
    }

    //hieptq update 150115
    public CountNo getMaxFilesCode(String deptCode) {
        StringBuilder stringBuilder = new StringBuilder("select c from CountNo c where c.deptCode = ? and c.isActive = 1  ");
        Query query = getSession().createQuery(stringBuilder.toString());
        query.setParameter(0, deptCode);
        List<CountNo> result = query.list();
        if (result.get(0) == null) {
            return null;
        } else {
            return result.get(0);
        }
    }

    /**
     *
     * @param paymentInfoId
     * @return
     */
    public List findFeePaymentInfo(Long paymentInfoId) {
        try {
            StringBuilder stringBuilder = new StringBuilder(" from FeePaymentInfo u ");
            stringBuilder.append("where u.isActive=1 and u.paymentInfoId = ?");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, paymentInfoId);
            lstStandard = query.list();
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return new ArrayList<>();
        }
        return lstStandard;
    }

    /**
     *
     * @param fileId
     * @return
     */
    public List findFeePaymentInfoFileId(Long fileId) {
        try {
            StringBuilder stringBuilder = new StringBuilder(" from FeePaymentInfo u ");
            stringBuilder.append("where u.isActive=1 and u.fileId = ?");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, fileId);
            lstStandard = query.list();
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return new ArrayList<>();
        }
        return lstStandard;
    }

    /**
     *
     * @param fileId
     * @param feeId
     * @param isActive
     * @return
     */
    public FeePaymentInfo findFeePaymentInfoFileIdFeeIdIsActive(Long fileId, Long feeId, Long isActive) {
        try {
            List<FeePaymentInfo> lstStandardNew = null;
            StringBuilder stringBuilder = new StringBuilder(" from FeePaymentInfo u ");
            stringBuilder.append("where u.fileId = ? and u.isActive = ? and u.feeId = ?");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, fileId);
            query.setParameter(1, isActive);
            query.setParameter(2, feeId);

            lstStandardNew = query.list();
            if (!lstStandardNew.isEmpty() && lstStandardNew.size() > 0) {
                return lstStandardNew.get(0);
            } else {
                return null;
            }
        } catch (HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    /**
     *
     * @param procedureId
     * @return
     */
    public List findAllFeeByProcedureIdNew(Long procedureId) {
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Fee f ");
            stringBuilder.append("where f.feeId in (\n"
                    + "\n"
                    + "select fp.feeId from FeeProcedure fp where fp.procedureId = ? )");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, procedureId);
            lstStandard = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return new ArrayList<>();
        }
        return lstStandard;
    }

    public FeeForm boToForm(Fee bo) {
        FeeForm frm = new FeeForm(bo);
        return frm;
    }

    /**
     *
     * @param userId
     * @param start
     * @param nRecord
     * @param sortField
     * @return
     */
    public GridResult getLstFee(Long userId, int start, int nRecord, String sortField) {
        List<FeeForm> list = new ArrayList<>();
        List lstParam = new ArrayList();
        try {
            String countHql = "SELECT count(u) ";
            String selectHQL = "SELECT u";
            String hql = " FROM Fee u WHERE (u.isActive=1) and (u.status = 1) and (u.createdBy = ?)";
            hql += " ORDER BY u.createDate ";
            Query query = getSession().createQuery(selectHQL + hql);
            Query countQuery = getSession().createQuery(countHql + hql);
            lstParam.add(userId);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }
            Long nCount = (Long) countQuery.uniqueResult();
            query.setFirstResult(start);
            query.setMaxResults(nRecord);
            List<Fee> lst = query.list();
            for (Fee bo : lst) {
                list.add(boToForm(bo));
            }
            GridResult result = new GridResult(nCount, list);
            return result;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            return new GridResult(0, null);
        }
    }

    public Fee findFeeByCode(String feeCode) {
        String hql = "from Fee f where lower(f.feeCode) like ? ESCAPE '/' ";
        Query query = getSession().createQuery("select f " + hql);
        query.setParameter(0, StringUtils.toLikeString(feeCode.toLowerCase().trim()));
        List<Fee> lstResult = query.list();
        if (lstResult != null && lstResult.size() > 0) {
            return lstResult.get(0);
        }
        return null;
    }

    public GridResult findFee(FeeForm form, int start, int count, String sortField) {
        String hql = " from Fee u where ";
        List lstParam = new ArrayList();
        if (form != null) {
            if (form.getIsActive() != null && form.getIsActive() != -1) {
                hql = " from Fee u where (u.isActive = ?) AND";
                lstParam.add(form.getIsActive());
            }
            if (form.getFeeName() != null) {
                hql += " lower(u.feeName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getFeeName().toLowerCase().trim()));
            }

        }
        Query countQuery = getSession().createQuery("select count(*) " + hql);
        Query query = getSession().createQuery("select u " + hql + " order by u.isActive desc, u.feeId desc");
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

    public boolean onCreateFee(FeeForm feeForm, Long userId) {
        boolean bReturn = true;
        try {
            Date dateNow = getSysdate();
            Fee fee = new Fee();
            Long stt = feeForm.getIsActive();
            Long feeType = feeForm.getFeeType();
            if (feeForm.getFeeId() == null) {
                fee.setIsActive(stt);
                fee.setFeeName(feeForm.getFeeName());
                fee.setCreateDate(dateNow);
                fee.setCreateUserId(userId);
                fee.setDescription(feeForm.getDescription());
                fee.setPrice(feeForm.getPrice());
                fee.setUpdateDate(dateNow);
                fee.setUpdateUserId(userId);
                fee.setProcedureId(feeForm.getProcedureId());
                fee.setFeeType(feeType);
                getSession().save(fee);
                String procedureId = feeForm.getProcedureId();
                String[] tokens = procedureId.split(";");
                List<String> list = Arrays.asList(tokens);
                String procedureName = "";
                ProcedureDAOHE pdhe = new ProcedureDAOHE();
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        Procedure pdh = pdhe.findById(Long.parseLong(list.get(i)));
                        procedureName += pdh.getName() + ";";
                    }
                }
                if (!"".equals(procedureName)) {
                    procedureName = procedureName.substring(0, procedureName.length() - 1);
                }
                fee.setProcedureName(procedureName);
                for (int i = 0; i < list.size(); i++) {
                    FeeProcedure feepro = new FeeProcedure();
                    feepro.setProcedureId(Long.parseLong(list.get(i)));
                    feepro.setFeeId(fee.getFeeId());
                    feepro.setIsActive(1l);
                    getSession().save(feepro);
                    // Giai phong bo nho
//                    feepro = null;//sonar
                }
                bReturn = true;
            } else {
                fee = findById(feeForm.getFeeId());
                fee.setFeeId((fee.getFeeId()));
                fee.setFeeType(feeType);
                fee.setIsActive(stt);
                fee.setFeeName(feeForm.getFeeName());
                fee.setCreateUserId(userId);
                fee.setDescription(feeForm.getDescription());
                fee.setPrice(feeForm.getPrice());
                fee.setUpdateDate(dateNow);
                fee.setUpdateUserId(userId);
                fee.setProcedureId(feeForm.getProcedureId());
                String procedureId = feeForm.getProcedureId();
                //ghi lai gia tri khi edit

                String[] tokens = procedureId.split(";");
                List<String> list = Arrays.asList(tokens);
                String procedureName = "";
                ProcedureDAOHE pdhe = new ProcedureDAOHE();
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        Procedure pdh = pdhe.findById(Long.parseLong(list.get(i)));
                        procedureName += pdh.getName() + ";";
                    }
                }
                if (!"".equals(procedureName)) {
                    procedureName = procedureName.substring(0, procedureName.length() - 1);
                }
                fee.setProcedureName(procedureName);
                FeeDAOHE fdhe = new FeeDAOHE();

                fdhe.deleteProcedure(feeForm.getFeeId());
                if (stt == 1) {
                    for (int i = 0; i < list.size(); i++) {
                        FeeProcedure feepro = new FeeProcedure();
                        feepro.setProcedureId(Long.parseLong(list.get(i)));
                        feepro.setFeeId(fee.getFeeId());
                        feepro.setIsActive(1l);
                        getSession().save(feepro);
                        // Giai phong bo nho
//                        feepro = null;
                    }
                }
                if (stt == 0) {
                    for (int i = 0; i < list.size(); i++) {
                        FeeProcedure feepro = new FeeProcedure();
                        String id = list.get(i);
                        feepro.setProcedureId(Long.parseLong(id));
                        feepro.setFeeId(fee.getFeeId());
                        feepro.setIsActive(0l);
                        getSession().save(feepro);
                        // Giai phong bo nho
//                        feepro = null;
                    }
                }
                getSession().update(fee);
                bReturn = true;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    /**
     *
     * @param feeId
     */
    // 11/11/2014 viethd
    // modified SQL query by adding parameter feeId
    public void deleteProcedure(Long feeId) {
        //String a = " update FeeProcedure fp set fp.isActive = 0 where fp.feeId=" + feeId;
        String a = "DELETE FROM FeeProcedure fp WHERE fp.feeId = ? ";
        //String a = "DELETE FROM FeeProcedure fp WHERE fp.feeId = ?" + feeId; // comment 11/11/2014
        Query query = getSession().createQuery(a);
        query.setParameter(0, feeId);
        query.executeUpdate();
    }

    // danh sach phi doanh nghiep
    public GridResult getLstPayment(FeeForm searchFeeForm, Long fileId,
            int start, int count, String sortField) {

        FilesDAOHE fdhe = new FilesDAOHE();
        Files filesBo = fdhe.findById(fileId);
        String sql;
        if (filesBo != null
                && (filesBo.getStatus().equals(Constants.FILE_STATUS.APPROVED)
                || filesBo.getStatus().equals(Constants.FILE_STATUS.COMPARED)
                || filesBo.getStatus().equals(Constants.FILE_STATUS.COMPARED_FAIL)
                || filesBo.getStatus().equals(Constants.FILE_STATUS.ALERT_COMPARISON))
                && filesBo.getIsSignPdf() != null
                && (filesBo.getIsSignPdf() != 0)) {
            sql = "from fee f inner join fee_payment_info fpi "
                    + "on f.fee_id = fpi.fee_id "
                    + "where fpi.file_id = ? "
                    + "and f.is_Active=1 "
                    + "and fpi.is_Active=1";
        } else if (filesBo != null && filesBo.getStatus().equals(Constants.FILE_STATUS.GIVE_BACK)
                && filesBo.getIsSignPdf() != null && (filesBo.getIsSignPdf() != 0)) {
            sql = "from fee f inner join fee_payment_info fpi on f.fee_id = fpi.fee_id "
                    + "where fpi.file_id = ? "
                    + "and f.is_Active=1 "
                    + "and fpi.is_Active=1 "
                    + "and (f.fee_type = 2 or f.fee_type = 1)";
        } else {
            sql = "from fee f inner join fee_payment_info fpi on f.fee_id = fpi.fee_id "
                    + "where fpi.file_id = ? "
                    + "and f.is_Active=1 "
                    + "and fpi.is_Active=1 and f.fee_type = 2";
        }

        List lstParam = new ArrayList();
        lstParam.add(fileId);

        if (searchFeeForm.getFeeName() != null
                && !"".equals(searchFeeForm.getFeeName().trim())) {
            sql += "and  f.fee_name like ? ";
            String param = "%" + searchFeeForm.getFeeName() + "%";
            lstParam.add(param);
        }
        if (searchFeeForm.getPrice() != null
                && !"".equals(searchFeeForm.getPrice())) {
            sql += " and f.price = ?";
            lstParam.add(searchFeeForm.getPrice());
        }
        if (searchFeeForm.getFeeType() != null
                && searchFeeForm.getFeeType() != -1) {
            sql += " and f.fee_type = ?";
            lstParam.add(searchFeeForm.getFeeType());
        }
        if (searchFeeForm.getStatus() != null
                && searchFeeForm.getStatus() != -1) {
            sql += " and fpi.status = ?";
            lstParam.add(searchFeeForm.getStatus());
        }
        SQLQuery countQuery = (SQLQuery) getSession().createSQLQuery("select count(*) " + sql);
        SQLQuery query = (SQLQuery) getSession().createSQLQuery(
                "select f.fee_Id,"
                + "f.fee_Name,"
                + "f.description,"
                + "fpi.cost,"
                + "f.fee_Type,"
                + "fpi.status,"
                + "fpi.fee_Payment_Type_Id, "
                + "f.price,"
                + "fpi.payment_Person,"
                + "fpi.payment_Date,"
                + "fpi.payment_Info,"
                + "fpi.bill_path,"
                + "fpi.payment_info_id,"
                + "fpi.payment_code,"
                + "fpi.payment_confirm,"
                + "fpi.bill_code,"
                + "fpi.date_confirm,"
                + "fpi.comment_reject  " + sql);
        for (int i = 0; i < lstParam.size(); i++) {
            countQuery.setParameter(i, lstParam.get(i));
            query.setParameter(i, lstParam.get(i));
        }

        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List lstResult = query.list();
        FeePaymentFileForm item = new FeePaymentFileForm();
        List result = new ArrayList<FeePaymentFileForm>();

        //Hiepvv 4Star
        boolean isHaveFee = false;
        if (filesBo != null
                && filesBo.getFileType() != null
                && filesBo.getFileType() > 0L) {
            ProcedureDAOHE pDAO = new ProcedureDAOHE();
            Procedure p = pDAO.findById(filesBo.getFileType());
            if (p != null) {
                if (p.getDescription() != null
                        && p.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR)) {
                    isHaveFee = true;
                }
                if (p.getDescription() != null
                        && p.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05)) {
                    isHaveFee = true;
                }
            }
        }
        for (int i = 0; i < lstResult.size(); i++) {
            Object[] row = (Object[]) lstResult.get(i);
            if (row.length > 0) {
                if (row[0] != null && !"".equals(row[0])) {
                    item.setFeeId(Long.parseLong(row[0].toString()));
                }
                if (row[1] != null && !"".equals(row[1])) {
                    item.setFeeName(row[1].toString());
                }
                if (row[2] != null && !"".equals(row[2])) {
                    item.setDescription(row[2].toString());
                }
                //Hiepvv 4Star
                if (isHaveFee) {
                    if (row[3] != null && !"".equals(row[3])) {
                        item.setPrice(Long.parseLong(row[3].toString()));
                    }
                } else if (row[7] != null && !"".equals(row[7])) {
                    item.setPrice(Long.parseLong(row[7].toString()));
                }
                if (row[4] != null && !"".equals(row[4])) {
                    item.setFeeType(Long.parseLong(row[4].toString()));
                }
                if (row[5] != null && !"".equals(row[5])) {
                    item.setStatus(Long.parseLong(row[5].toString()));
                }
                if (row[6] != null && !"".equals(row[6])) {
                    item.setFeePaymentType(Long.parseLong(row[6].toString()));
                }
                if (row[8] != null && !"".equals(row[8])) {
                    item.setPaymentPerson(row[8].toString());
                }
                if (row[9] != null && !"".equals(row[9])) {
                    item.setPaymentDate(row[9].toString());
                }
                if (row[10] != null && !"".equals(row[10])) {
                    item.setPaymentInfo(row[10].toString());
                }
                if (row[11] != null && !"".equals(row[11])) {
                    item.setBillPath(row[11].toString());
                }
                if (row[12] != null && !"".equals(row[12])) {
                    item.setPaymentInfoId(Long.parseLong(row[12].toString()));
                }
                if (row[13] != null && !"".equals(row[13])) {
                    item.setPaymentCode(row[13].toString());
                }
                if (row[14] != null && !"".equals(row[14])) {
                    item.setPaymentConfirm(row[14].toString());
                }
                if (row[15] != null && !"".equals(row[15])) {
                    item.setBillCode(row[15].toString());
                }
                if (row[16] != null && !"".equals(row[16])) {
                    Date confirmDate = (Date) row[16];
                    item.setDateConfirm(DateTimeUtils.convertDateToString(confirmDate, "dd/MM/yyyy"));
                }
                if (row[17] != null && !"".equals(row[17])) {
                    item.setCommentReject(row[17].toString());
                }
            }
            result.add(item);
            item = new FeePaymentFileForm();
        }
        GridResult gr = new GridResult(total, result);
        return gr;
    }

    //hieptq update 130115
    public List getLstFpiId(String lstObjectId) {
//        FilesDAOHE fdhe = new FilesDAOHE();
        List lstParam = new ArrayList();
        String[] lstObjectIdSplit = lstObjectId.split(",");
        int countObj = lstObjectIdSplit.length;
        String sql = "from fee f inner join fee_payment_info fpi on f.fee_id = fpi.fee_id "
                + "where f.is_Active=1 "
                + "and fpi.is_Active=1 and f.fee_type = 2 and (";

        if (countObj > 0) {
            for (int i = 0; i < countObj; i++) {
                if (i == countObj - 1) {
                    sql += " fpi.file_id = ? )";
                } else {
                    sql += " fpi.file_id = ? or ";
                }
                lstParam.add(lstObjectIdSplit[i]);
            }
        }

        SQLQuery query = (SQLQuery) getSession().createSQLQuery(
                "select fpi.payment_info_id " + sql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }
        List lstResult = query.list();
        return lstResult;
    }

    //hieptq update 130115
    public Long getAmountKeyPay(String lstObjectId) {
//        FilesDAOHE fdhe = new FilesDAOHE();
        List lstParam = new ArrayList();
        String[] lstObjectIdSplit = lstObjectId.split(",");
        int countObj = lstObjectIdSplit.length;
        Long amount = 0l;
        String sql = "from fee f inner join fee_payment_info fpi on f.fee_id = fpi.fee_id "
                + "where f.is_Active=1 "
                + "and fpi.is_Active=1 and f.fee_type = 2 and (";

        if (countObj > 0) {
            for (int i = 0; i < countObj; i++) {
                if (i == countObj - 1) {
                    sql += " fpi.file_id = ? )";
                } else {
                    sql += " fpi.file_id = ? or ";
                }
                lstParam.add(lstObjectIdSplit[i]);
            }
        }

        SQLQuery query = (SQLQuery) getSession().createSQLQuery(
                "select fpi.cost " + sql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }
        List lstResult = query.list();
        for (int i = 0; i < lstResult.size(); i++) {
            amount += Long.parseLong(lstResult.get(i).toString());
        }
        return amount;
    }

    // quan ly le phi
    /**
     *
     * @param searchFeeFormNew
     * @param userId
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult getLstFeeManage(FeePaymentFileForm searchFeeFormNew, Long userId, int start, int count, String sortField) {

        UsersDAOHE udhe = new UsersDAOHE();
        Users user = udhe.findById(userId);

        String sql = "from files f inner join fee_payment_info fpi on f.file_id = fpi.file_id "
                + "where fpi.fee_id in (select f.fee_id from fee f "
                + "                         where f.fee_type = 1 and f.is_active = 1 ) "
                //+ "and f.agency_id =" + user.getDeptId() + " " // comment 11/11/2014 viethd
                + "and f.agency_id = ? "
                + "and (f.status = 15 or f.status=16 or f.status=6 or f.status=22 or f.status = 23) "
                //+ "and (f.status=6) "
                + "and fpi.is_active=1 "
                + "and f.is_active = 1 ";
        //+ "and fpi.status <> 0 ";

        List lstParam = new ArrayList();
        lstParam.add(user.getDeptId());

        if (searchFeeFormNew.getFileCode() != null && !"".equals(searchFeeFormNew.getFileCode().trim())) {
            //sql += " and lower(f.file_code) like '%" + searchFeeFormNew.getFileCode().toLowerCase().trim().replace("/", "//").replace("_", "/_").replace("%", "/%") + "%'"; // comment 11/11/2014 viethd
            sql += " and lower(f.file_code) like ? ";
            String param = "%" + searchFeeFormNew.getFileCode().toLowerCase().trim() + "%";
            lstParam.add(param);
        }
        if (searchFeeFormNew.getFilesCode() != null && !"".equals(searchFeeFormNew.getFilesCode())) {
            // comment 11/11/2014 viethd
            sql += " and lower(fpi.files_code) like ? ";
            String param = "%" + searchFeeFormNew.getFilesCode().toString().toLowerCase().trim() + "%";
            lstParam.add(param);
            //lstParam.add(searchFeeFormNew.getFilesCode());
        }
        if (searchFeeFormNew.getProductName() != null && !"".equals(searchFeeFormNew.getProductName().trim())) {
            //sql += " and lower(f.product_name) like '%" + searchFeeFormNew.getProductName().toLowerCase().trim().replace("/", "//").replace("_", "/_").replace("%", "/%") + "%'"; // comment 11/1/2014 viethd
            sql += " and lower(f.product_name) like ? ";
            String param = "%" + searchFeeFormNew.getProductName().toLowerCase().trim() + "%";
            lstParam.add(param);
        }
        if (searchFeeFormNew.getCost() != null && !"".equals(searchFeeFormNew.getCost())) {
            //sql += " and lower(fpi.cost) like '%" + searchFeeFormNew.getCost().toString().toLowerCase().trim().replace("/", "//").replace("_", "/_").replace("%", "/%") + "%'";
            sql += " and lower(fpi.cost) like ? ";
            String param = "%" + searchFeeFormNew.getCost().toString().toLowerCase().trim() + "%";
            lstParam.add(param);
        }
        if (searchFeeFormNew.getStatus() != null && searchFeeFormNew.getStatus() != -1) {
            if (searchFeeFormNew.getSearchType() == null) {
                if (searchFeeFormNew.getStatus() == 0) {
                    sql += " and fpi.status > 2";
                } else {
                    //sql += "and fpi.status = " + searchFeeFormNew.getStatus(); // 11/11/2014 viethd
                    sql += "and fpi.status = ? ";
                    lstParam.add(searchFeeFormNew.getStatus());
                }
            }
        }

        if (searchFeeFormNew.getBusinessName() != null && !"".equals(searchFeeFormNew.getBusinessName().trim())) {
            //sql += " and lower(f.business_name) like '%" + searchFeeFormNew.getBusinessName().toLowerCase().trim().replace("/", "//").replace("_", "/_").replace("%", "/%") + "%'";
            sql += " and lower(f.business_name) like ? ";
            String param = "%" + searchFeeFormNew.getBusinessName().toLowerCase().trim() + "%";
            lstParam.add(param);
        }
        if (searchFeeFormNew.getFeePaymentType() != null && searchFeeFormNew.getFeePaymentType() != -1) {
            // 11/11/2014 viethd
            //sql += " and fpi.fee_Payment_type_id = " + searchFeeFormNew.getFeePaymentType();
            sql += " and fpi.fee_Payment_type_id = ? ";
            lstParam.add(searchFeeFormNew.getFeePaymentType());

        }
        if (searchFeeFormNew.getPaymentConfirm() != null && !"".equals(searchFeeFormNew.getPaymentConfirm().trim())) {
            // comment 11/11/2014 viethd
            //sql += " and lower(f.product_name) like '%" + searchFeeFormNew.getProductName().toLowerCase().trim().replace("/", "//").replace("_", "/_").replace("%", "/%") + "%'";
            sql += " and lower(fpi.payment_confirm) like ? ";
            String param = "%" + searchFeeFormNew.getPaymentConfirm().toLowerCase().trim() + "%";
            lstParam.add(param);
        }

        if (searchFeeFormNew.getDateFrom() != null) {
            // 11/11/2014 viethd
            //sql += " and fpi.payment_date >= to_date('" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateFrom(), "dd/MM/yyyy") + " 00:00:00','dd/MM/yyyy hh24:mi:ss') ";
            sql += " and fpi.payment_date >= to_date( ? ,'dd/MM/yyyy hh24:mi:ss') ";
            String param = "" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateFrom(), "dd/MM/yyyy") + " 00:00:00";
            lstParam.add(param);
        }
        if (searchFeeFormNew.getDateTo() != null) {
            // 11/11/2014 viethd
            //sql += " and fpi.payment_date <= to_date('" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateTo(), "dd/MM/yyyy") + " 23:59:59','dd/MM/yyyy hh24:mi:ss') ";
            sql += " and fpi.payment_date <= to_date( ?,'dd/MM/yyyy hh24:mi:ss') ";
            String param = "" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateTo(), "dd/MM/yyyy") + " 23:59:59";
            lstParam.add(param);
        }
        // ngay xac nhan
        if (searchFeeFormNew.getDateConfirmSearchFrom() != null) {
            // 11/11/2014 viethd
            //sql += " and fpi.date_confirm >= to_date('" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateConfirmSearchFrom(), "dd/MM/yyyy") + " 00:00:00','dd/MM/yyyy hh24:mi:ss') ";
            sql += " and fpi.date_confirm >= to_date( ?,'dd/MM/yyyy hh24:mi:ss') ";
            String param = "" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateConfirmSearchFrom(), "dd/MM/yyyy") + " 00:00:00";
            lstParam.add(param);
        }
        if (searchFeeFormNew.getDateConfirmSearchTo() != null) {
            // 11/11/2014 viethd
            //sql += " and fpi.date_confirm <= to_date('" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateConfirmSearchTo(), "dd/MM/yyyy") + " 23:59:59','dd/MM/yyyy hh24:mi:ss') ";
            sql += " and fpi.date_confirm <= to_date( ?,'dd/MM/yyyy hh24:mi:ss') ";
            String param = "" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateConfirmSearchTo(), "dd/MM/yyyy") + " 23:59:59";
            lstParam.add(param);
        }

        if (searchFeeFormNew.getSearchType() != null && searchFeeFormNew.getSearchType() == 5) {
            sql += " and fpi.status = 1 ";
        }
        if (searchFeeFormNew.getSearchType() != null && searchFeeFormNew.getSearchType() == 6) {
            sql += " and fpi.status > 2 ";
        }

        //sql += " order by fpi.payment_date desc ";
        //sql += " order by fpi.payment_date asc ";
        sql += " order by f.send_date asc ";
        SQLQuery countQuery = (SQLQuery) getSession().createSQLQuery("select count (distinct fpi.payment_info_id) " + sql);
        SQLQuery query = (SQLQuery) getSession().createSQLQuery("select distinct f.file_code,"
                + "f.product_name,fpi.payment_date,fpi.cost,fpi.bill_path,"
                + "fpi.fee_payment_type_id,fpi.status,fpi.fee_id,fpi.file_id,fpi.payment_info_id,"
                + "fpi.payment_person,f.business_name,fpi.payment_code,fpi.payment_confirm,"
                + "fpi.bill_code,fpi.date_confirm,fpi.comment_reject,f.business_address,f.send_date,fpi.files_code " + sql);
        query.setFirstResult(start);
        query.setMaxResults(count);
        int paramSize = lstParam.size();
        for (int i = 0; i < paramSize; i++) {
            countQuery.setParameter(i, lstParam.get(i));
            query.setParameter(i, lstParam.get(i));
        }
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List lstResult = query.list();
        FeePaymentFileForm item = new FeePaymentFileForm();
        List result = new ArrayList<FeePaymentFileForm>();
        if (lstResult != null && lstResult.size() > 0) {
            for (int i = 0; i < lstResult.size(); i++) {
                Object[] row = (Object[]) lstResult.get(i);
                if (row.length > 0) {
                    if (row[0] != null && !"".equals(row[0])) {
                        item.setFileCode(row[0].toString());
                    }
                    if (row[1] != null && !"".equals(row[1])) {
                        item.setProductName(row[1].toString());
                    }
                    if (row[2] != null && !"".equals(row[2])) {
                        Date paymentDate = (Date) row[2];
                        item.setPaymentDate(DateTimeUtils.convertDateToString(paymentDate, "dd/MM/yyyy"));
                    }
                    if (row[3] != null && !"".equals(row[3])) {
                        item.setCost(Long.parseLong(row[3].toString()));
                    }
                    if (row[7] != null && !"".equals(row[7])) {
                        item.setFeeId(Long.parseLong(row[7].toString()));
                    }
                    if (row[4] != null && !"".equals(row[4])) {
                        item.setBillPath(row[4].toString());
                    }
                    if (row[5] != null && !"".equals(row[5])) {
                        item.setFeePaymentType(Long.parseLong(row[5].toString()));
                    }
                    if (row[6] != null && !"".equals(row[6])) {
                        item.setStatus(Long.parseLong(row[6].toString()));
                    }
                    if (row[8] != null && !"".equals(row[8])) {
                        item.setFileId(Long.parseLong(row[8].toString()));
                    }
                    if (row[9] != null && !"".equals(row[9])) {
                        item.setPaymentInfoId(Long.parseLong(row[9].toString()));
                    }
                    if (row[10] != null && !"".equals(row[10])) {
                        item.setPaymentPerson((row[10].toString()));
                    }
                    if (row[11] != null && !"".equals(row[11])) {
                        item.setBusinessName((row[11].toString()));
                    }
                    if (row[12] != null && !"".equals(row[12])) {
                        item.setPaymentCode(((row[12].toString())));
                    }
                    if (row[13] != null && !"".equals(row[13])) {
                        item.setPaymentConfirm((row[13].toString()));
                    }
                    if (row[14] != null && !"".equals(row[14])) {
                        item.setBillCode((row[14].toString()));
                    }
                    if (row[15] != null && !"".equals(row[15])) {
                        Date confirmDate = (Date) row[15];
                        item.setDateConfirm(DateTimeUtils.convertDateToString(confirmDate, "dd/MM/yyyy"));
                    }
                    if (row[16] != null && !"".equals(row[16])) {
                        item.setCommentReject(row[16].toString());
                    }
                    if (row[17] != null && !"".equals(row[17])) {
                        item.setBusinessAddress(row[17].toString());
                    }
                    if (row[19] != null && !"".equals(row[19])) {
                        item.setBusinessAddress(row[19].toString());
                    }

                }
                result.add(item);
                item = new FeePaymentFileForm();
            }
        }
        GridResult gr = new GridResult(total, result);
        return gr;
    }

    // quan ly nop phi - hieptq
    /**
     *
     * @param searchFeeFormNew
     * @param userId
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult getLstFeePayManage(FeePaymentFileForm searchFeeFormNew, Long userId, int start, int count, String sortField) {
        String sql = "from files f inner join fee_payment_info fpi on f.file_id = fpi.file_id "
                + "where fpi.fee_id in (select f.fee_id from fee f where f.fee_type = 2 and f.is_active = 1 ) "
                + "and fpi.is_active = 1 "
                + "and ((f.is_active = 1) or (f.is_active=2))  "
                //+ "and f.agency_id = ? "
                + "and (f.user_Signed is not null or f.status = 18) "
                + "and f.status <> -1";
        UsersDAOHE udhe = new UsersDAOHE();
//        Users user = udhe.findById(userId);
        List lstParam = new ArrayList();
//        lstParam.add(user.getDeptId());
        if (searchFeeFormNew.getBusinessName() != null && !"".equals(searchFeeFormNew.getBusinessName().trim())) {
            // comment 11/11/2014 viethd
            //sql += " and lower(f.business_name) like '%" + searchFeeFormNew.getBusinessName().toLowerCase().trim().replace("/", "//").replace("_", "/_").replace("%", "/%") + "%'";
            sql += " and lower(f.business_name) like ? ";
            String param = "%" + searchFeeFormNew.getBusinessName().toLowerCase().trim() + "%";
            lstParam.add(param);
        }
        if (searchFeeFormNew.getFileCode() != null && !"".equals(searchFeeFormNew.getFileCode().trim())) {
            // comment 11/11/2014 viethd
            //sql += " and lower(f.file_code) like '%" + searchFeeFormNew.getFileCode().toLowerCase().trim().replace("/", "//").replace("_", "/_").replace("%", "/%") + "%'";
            sql += " and lower(f.file_code) like ? ";
            String param = "%" + searchFeeFormNew.getFileCode().toLowerCase().trim() + "%";
            lstParam.add(param);
        }
        if (searchFeeFormNew.getProductName() != null && !"".equals(searchFeeFormNew.getProductName().trim())) {
            // comment 11/11/2014 viethd
            //sql += " and lower(f.product_name) like '%" + searchFeeFormNew.getProductName().toLowerCase().trim().replace("/", "//").replace("_", "/_").replace("%", "/%") + "%'";
            sql += " and lower(f.product_name) like ? ";
            String param = "%" + searchFeeFormNew.getProductName().toLowerCase().trim() + "%";
            lstParam.add(param);
        }
        if (searchFeeFormNew.getPaymentConfirm() != null && !"".equals(searchFeeFormNew.getPaymentConfirm().trim())) {
            sql += " and lower(fpi.payment_confirm) like ? ";
            String param = "%" + searchFeeFormNew.getPaymentConfirm().toLowerCase().trim() + "%";
            lstParam.add(param);
        }

        if (searchFeeFormNew.getCost() != null && !"".equals(searchFeeFormNew.getCost())) {
            // comment 11/11/2014 viethd
            //sql += " and lower(fpi.cost) like '%" + searchFeeFormNew.getCost().toString().toLowerCase().trim().replace("/", "//").replace("_", "/_").replace("%", "/%") + "%'";
            sql += " and lower(fpi.cost) like ? ";
            String param = "%" + searchFeeFormNew.getCost().toString().toLowerCase().trim() + "%";
            lstParam.add(param);
        }
        if (searchFeeFormNew.getFilesCode() != null && !"".equals(searchFeeFormNew.getFilesCode())) {
            // comment 11/11/2014 viethd
            sql += " and lower(fpi.files_code) like ? ";
            String param = "%" + searchFeeFormNew.getFilesCode().toString().toLowerCase().trim() + "%";
            lstParam.add(param);
            //lstParam.add(searchFeeFormNew.getFilesCode());
        }

        if (searchFeeFormNew.getStatus() != null && searchFeeFormNew.getStatus() != -1) {
            if (searchFeeFormNew.getSearchType() == null) {
                if (searchFeeFormNew.getStatus() == 0) {
                    sql += " and fpi.status > 2";
                } else {
                    //sql += "and fpi.status = " + searchFeeFormNew.getStatus(); // 11/11/2014 viethd
                    sql += "and fpi.status = ? ";
                    lstParam.add(searchFeeFormNew.getStatus());
                }
            }
        }
        if (searchFeeFormNew.getFeePaymentType() != null && searchFeeFormNew.getFeePaymentType() != -1) {
            // 11/11/2014 viethd
            //sql += " and fpi.fee_Payment_type_id = " + searchFeeFormNew.getFeePaymentType() + " ";
            sql += " and fpi.fee_Payment_type_id = ? ";
            lstParam.add(searchFeeFormNew.getFeePaymentType());
        }
        if (searchFeeFormNew.getDateFrom() != null) {
            // 11/11/2014 viethd
            //sql += " and fpi.payment_date >= to_date('" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateFrom(), "dd/MM/yyyy") + " 00:00:00','dd/MM/yyyy hh24:mi:ss') ";
            sql += " and fpi.payment_date >= to_date(?, 'dd/MM/yyyy hh24:mi:ss') ";
            lstParam.add("" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateFrom(), "dd/MM/yyyy") + " 00:00:00");
        }
        if (searchFeeFormNew.getDateTo() != null) {
            // 11/11/2014 viethd
            //sql += " and fpi.payment_date <= to_date('" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateTo(), "dd/MM/yyyy") + " 23:59:59','dd/MM/yyyy hh24:mi:ss') ";
            sql += " and fpi.payment_date <= to_date(?, 'dd/MM/yyyy hh24:mi:ss') ";
            lstParam.add("" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateTo(), "dd/MM/yyyy") + " 23:59:59");
        }
        // ngay xac nhan
        if (searchFeeFormNew.getDateConfirmSearchFrom() != null) {
            // 11/11/2014 viethd
            //sql += " and fpi.date_confirm >= to_date('" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateConfirmSearchFrom(), "dd/MM/yyyy") + " 00:00:00','dd/MM/yyyy hh24:mi:ss') ";
            sql += " and fpi.date_confirm >= to_date(?, 'dd/MM/yyyy hh24:mi:ss') ";
            lstParam.add("" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateConfirmSearchFrom(), "dd/MM/yyyy") + " 00:00:00");
        }
        if (searchFeeFormNew.getDateConfirmSearchTo() != null) {
            // 11/11/2014 viethd
            //sql += " and fpi.date_confirm <= to_date('" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateConfirmSearchTo(), "dd/MM/yyyy") + " 23:59:59','dd/MM/yyyy hh24:mi:ss') ";
            sql += " and fpi.date_confirm <= to_date(?, 'dd/MM/yyyy hh24:mi:ss') ";
            lstParam.add("" + DateTimeUtils.convertDateToString(searchFeeFormNew.getDateConfirmSearchTo(), "dd/MM/yyyy") + " 23:59:59");
        }
        // hieptq them nhom san pham 17.11.14
        if (searchFeeFormNew.getProductType() != null && searchFeeFormNew.getProductType() != -1) {
            if (searchFeeFormNew.getProductType() == 1) {
                sql += " and fpi.cost = 1500000 ";
            } else {
                sql += " and fpi.cost = 500000 ";
            }
        }

        //hieptq update searchType
        //searchType = 1 searchFeeFormNew.status=" + 1 + "&searchFeeFormNew.productType=" + 2 + "&searchFeeFormNew.feePaymentType=-1
        if (searchFeeFormNew.getSearchType() != null && searchFeeFormNew.getSearchType() == 1) {
            sql += " and fpi.status = 1 and fpi.cost = 500000 ";
        }
        //searchType = 2 searchFeeFormNew.status=" + 0 + "&searchFeeFormNew.productType=" + 2 + "&searchFeeFormNew.feePaymentType=-1
        if (searchFeeFormNew.getSearchType() != null && searchFeeFormNew.getSearchType() == 2) {
            sql += " and fpi.status > 2 and fpi.cost = 500000 ";
        }
        //searchType = 3 searchFeeFormNew.status=" + 1 + "&searchFeeFormNew.productType=" + 1 + "&searchFeeFormNew.feePaymentType=-1
        if (searchFeeFormNew.getSearchType() != null && searchFeeFormNew.getSearchType() == 3) {
            sql += " and fpi.status = 1 and fpi.cost = 1500000 ";
        }
        //searchType = 4 searchFeeFormNew.status=" + 0 + "&searchFeeFormNew.productType=" + 1 + "&searchFeeFormNew.feePaymentType=-1
        if (searchFeeFormNew.getSearchType() != null && searchFeeFormNew.getSearchType() == 4) {
            sql += " and fpi.status > 2 and fpi.cost = 1500000 ";
        }
        //sql += " order by fpi.payment_date desc ";
        //sql += " order by fpi.payment_date asc ";
        sql += " order by f.send_date asc ";
        SQLQuery countQuery = (SQLQuery) getSession().createSQLQuery("select count (distinct fpi.payment_info_id) " + sql);
        SQLQuery query = (SQLQuery) getSession().createSQLQuery("select distinct f.business_name,f.product_name,"
                + "fpi.payment_date,fpi.cost,fpi.bill_path,fpi.fee_payment_type_id,fpi.status,"
                + "fpi.fee_id,fpi.file_id,fpi.payment_info_id,fpi.payment_person,f.file_code,"
                + "fpi.payment_code,fpi.payment_confirm,fpi.bill_code,fpi.date_confirm,"
                + "fpi.comment_reject,f.business_address,f.send_date,fpi.files_code  " + sql);
        query.setFirstResult(start);
        query.setMaxResults(count);
        int paramSize = lstParam.size();
        for (int i = 0; i < paramSize; i++) {
            countQuery.setParameter(i, lstParam.get(i));
            query.setParameter(i, lstParam.get(i));
        }
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List lstResult = query.list();
        FeePaymentFileForm item = new FeePaymentFileForm();
        List result = new ArrayList<FeePaymentFileForm>();
        if (lstResult != null && lstResult.size() > 0) {
            for (int i = 0; i < lstResult.size(); i++) {
                Object[] row = (Object[]) lstResult.get(i);
                if (row.length > 0) {
                    if (row[0] != null && !"".equals(row[0])) {
                        item.setBusinessName(row[0].toString());
                    }
                    if (row[1] != null && !"".equals(row[1])) {
                        item.setProductName(row[1].toString());
                    }
                    if (row[2] != null && !"".equals(row[2])) {
                        Date paymentDate = (Date) row[2];
                        item.setPaymentDate(DateTimeUtils.convertDateToString(paymentDate, "dd/MM/yyyy"));
                    }
                    if (row[3] != null && !"".equals(row[3])) {
                        item.setCost(Long.parseLong(row[3].toString()));
                    }
                    if (row[7] != null && !"".equals(row[7])) {
                        item.setFeeId(Long.parseLong(row[7].toString()));
                    }
                    if (row[4] != null && !"".equals(row[4])) {
                        item.setBillPath(row[4].toString());
                    }
                    if (row[5] != null && !"".equals(row[5])) {
                        item.setFeePaymentType(Long.parseLong(row[5].toString()));
                    }
                    if (row[6] != null && !"".equals(row[6])) {
                        item.setStatus(Long.parseLong(row[6].toString()));
                    }
                    if (row[8] != null && !"".equals(row[8])) {
                        item.setFileId(Long.parseLong(row[8].toString()));
                    }
                    if (row[9] != null && !"".equals(row[9])) {
                        item.setPaymentInfoId(Long.parseLong(row[9].toString()));
                    }
                    if (row[10] != null && !"".equals(row[10])) {
                        item.setPaymentPerson((row[10].toString()));
                    }
                    if (row[11] != null && !"".equals(row[11])) {
                        item.setFileCode((row[11].toString()));
                    }
                    if (row[12] != null && !"".equals(row[12])) {
                        item.setPaymentCode((row[12].toString()));
                    }
                    if (row[13] != null && !"".equals(row[13])) {
                        item.setPaymentConfirm((row[13].toString()));
                    }
                    if (row[14] != null && !"".equals(row[14])) {
                        item.setBillCode((row[14].toString()));
                    }
                    if (row[15] != null && !"".equals(row[15])) {
                        Date confirmDate = (Date) row[15];
                        item.setDateConfirm(DateTimeUtils.convertDateToString(confirmDate, "dd/MM/yyyy"));
                    }
                    if (row[16] != null && !"".equals(row[16])) {
                        item.setCommentReject(row[16].toString());
                    }
                    if (row[17] != null && !"".equals(row[17])) {
                        item.setBusinessAddress(row[17].toString());
                    }
                    if (row[19] != null && !"".equals(row[19])) {
                        item.setFilesCode(row[19].toString());
                    }
                }
                result.add(item);
                item = new FeePaymentFileForm();
            }
        }
        GridResult gr = new GridResult(total, result);
        return gr;
    }

    /**
     *
     * @param fileId
     * @param feeId
     * @param feeInfoId
     * @param userId
     * @param paymentInfo
     * @return
     */
    public boolean insertFeePaymentInfo(Long feeInfoId, Long userId, String paymentInfo, String filescode) {
        boolean bReturn = true;
        try {
            Date dateNow = getSysdate();
            // 11/11/2014 viethd
            //String a = " select fpif from FeePaymentInfo fpif where fpif.paymentInfoId = " + feeInfoId + " and fpif.isActive = 1";
            String a = " select fpif from FeePaymentInfo fpif where fpif.paymentInfoId = ? and fpif.isActive = 1";
            Query query = getSession().createQuery(a);
            query.setParameter(0, feeInfoId);
            List<FeePaymentInfo> list = query.list();
            FeePaymentInfo fpif = list.get(0);
//            String filesCode = filescode;
//            if (!filescode.equals("null"){
            if (filescode != null && !filescode.isEmpty()) {
                if (!"null".equals(filescode)) {
                    fpif.setFilesCode(filescode);
                }
            }
            fpif.setStatus(4L);
            fpif.setStatus(1l);
            fpif.setFeePaymentTypeId(1L);
            fpif.setPaymentDate(dateNow);
            fpif.setPaymentInfo(paymentInfo);
            //Users user = (Users) findById(Users.class, "userId", userId);
            FilesDAOHE fdhe = new FilesDAOHE();
            Files file = fdhe.findById(fpif.getFileId());
            //fpif.setPaymentPerson(user.getUserName());
            fpif.setPaymentPerson(file.getUserCreateName());
            //fpif.setPaymentPerson();
            getSession().saveOrUpdate(fpif);
            // save is_fee in files
            bReturn = true;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

    /**
     *
     * @param paymentInfoId
     * @param paymentType
     * @param billPath
     * @param paymentPerson
     * @param paymentDate
     * @return
     */
    public boolean savePaymentInfo(Long paymentInfoId, Long paymentType, String billPath, String paymentPerson, Date paymentDate) {
        boolean bReturn = true;
        try {
            Date dateNow = getSysdate();
            String a;
            List params = new ArrayList();

            if (billPath.trim().length() == 0) {
                // 11/11/2014 viethd
//                a = " update FeePaymentInfo fpif "
//                        + "set fpif.status = 1,fpif.feePaymentTypeId= " + paymentType + " "
//                        + "where fpif.paymentInfoId=" + paymentInfoId + " and fpif.isActive = 1 ";
                a = " update FeePaymentInfo fpif "
                        + "set fpif.status = 1,fpif.feePaymentTypeId= ? "
                        + "where fpif.paymentInfoId=? and fpif.isActive = 1 ";
                params.add(paymentType);
                params.add(paymentInfoId);
            } else {
                // 11/11/2014 viethd
//                a = " update FeePaymentInfo fpif "
//                        + "set fpif.status = 1,fpif.feePaymentTypeId= " + paymentType + ", fpif.billPath = " + billPath + ", fpif.paymentPerson = '" + paymentPerson + "' "
//                        + "where fpif.paymentInfoId=" + paymentInfoId + " and fpif.isActive = 1 ";
                a = " update FeePaymentInfo fpif "
                        + "set fpif.status = 1,fpif.feePaymentTypeId= ? "
                        + ", fpif.billPath = ? "
                        + ", fpif.paymentPerson = ? "
                        + "where fpif.paymentInfoId=? and fpif.isActive = 1 ";
                params.add(paymentType);
                params.add(billPath);
                params.add(paymentPerson);
                params.add(paymentInfoId);
            }
            Query query = getSession().createQuery(a);
            int paramSize = params.size();
            for (int i = 0; i < paramSize; i++) {
                query.setParameter(i, params.get(i));
            }
            query.executeUpdate();
            String[] lstVo = billPath.split(";");
            FeeDAOHE fdhe = new FeeDAOHE();
            List<FeePaymentInfo> list = fdhe.findFeePaymentInfo(paymentInfoId);
            FeePaymentInfo fpif = list.get(0);
            fpif.setPaymentDate(paymentDate);
            getSession().update(fpif);
            VoAttachsDAOHE vadhe = new VoAttachsDAOHE();
            for (int i = 0; i < lstVo.length; i++) {
                if (lstVo[i] != null) {
                    VoAttachs voUpload = vadhe.findById(Long.parseLong(lstVo[i]));
                    voUpload.setObjectId(paymentInfoId);
                    voUpload.setObjectType(25L);
                    voUpload.setCreateDate(dateNow);
                    getSession().update(voUpload);
                }
            }

            FeeDAOHE fdhe1 = new FeeDAOHE();
            FilesDAOHE fdhe2 = new FilesDAOHE();
            FeePaymentInfoDAOHE fdhe3 = new FeePaymentInfoDAOHE();
            FeePaymentInfo fee = fdhe3.findById(paymentInfoId);
            Files file = fdhe2.findById(fee.getFileId());
            ProcedureDAOHE pdhe = new ProcedureDAOHE();
            Procedure pde = pdhe.findById(file.getFileType());
            if (file != null && file.getIsActive().equals(1L)) {
                file.setModifyDate(dateNow);
                if (file.getStatus().equals(Constants.FILE_STATUS.NEW_CREATE) || file.getStatus().equals(Constants.FILE_STATUS.NEW_TO_ADD)) {
                    file.setIsFee(1L);
                    getSession().update(file);
                } else if ("01".equals(pde.getCode()) || "02".equals(pde.getCode())) {
                    file.setIsFee(1L);
                    getSession().update(file);
                } else {
                    List<FeePaymentInfo> feeCheck = fdhe1.findFeePaymentInfoFileId(fee.getFileId());
                    FeePaymentInfo fpifnew;
                    int check = 0;
                    for (int i = 0; i < feeCheck.size(); i++) {
                        fpifnew = feeCheck.get(i);
                        if (fpifnew.getStatus() == 1 && fpifnew.getIsActive() == 1) {
                            check++;
                        }
                    }
                    if (check == 2) {
                        file.setIsFee(1L);
                        getSession().update(file);
                    }
                }
            }
            bReturn = true;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            bReturn = false;
        }
        return bReturn;

    }

    /**
     *
     * @param paymentInfoId
     * @param paymentType
     * @param billPath
     * @param paymentPerson
     * @param paymentDate
     * @param paymentCode
     * @param billCode
     * @return
     */
    public boolean savePaymentInfoNew(Long paymentInfoId, Long paymentType, String billPath, String paymentPerson, Date paymentDate, String paymentCode, String billCode) {
        boolean bReturn = true;
        try {
            String a = "";
            List params = new ArrayList();
            if (paymentType == 3) {
                if (billPath.trim().length() == 0) {
                    // 11/11/2014 viethd
//                    a = " update FeePaymentInfo fpif set fpif.status = 3,fpif.feePaymentTypeId= " + paymentType + " where fpif.paymentInfoId=" + paymentInfoId + " and fpif.isActive = 1 ";
                    a = " update FeePaymentInfo fpif "
                            + "set fpif.status = 3,fpif.feePaymentTypeId= ? "
                            + "where fpif.paymentInfoId=? and fpif.isActive = 1 ";
                    params.add(paymentType);
                    params.add(paymentInfoId);
                } else {
                    // 11/11/2014 viethd
//                    a = " update FeePaymentInfo fpif "
//                            + "set fpif.status = 3,fpif.feePaymentTypeId= " + paymentType
//                            + ", fpif.billPath = " + billPath
//                            + ", fpif.paymentPerson = '" + paymentPerson
//                            + "' where fpif.paymentInfoId=" + paymentInfoId + " and fpif.isActive = 1 ";
                    a = " update FeePaymentInfo fpif "
                            + "set fpif.status = 3,fpif.feePaymentTypeId= ? "
                            + ", fpif.billPath = ? "
                            + ", fpif.paymentPerson = ? "
                            + "where fpif.paymentInfoId= ? and fpif.isActive = 1 ";
                    params.add(paymentType);
                    params.add(billPath);
                    params.add(paymentPerson);
                    params.add(paymentInfoId);
                }
            }
            if (paymentType == 2) {
                if (billPath.trim().length() == 0) {
                    // 11/11/2014 viethd
//                    a = " update FeePaymentInfo fpif set fpif.status = 5,fpif.feePaymentTypeId= " + paymentType + ",fpif.billCode= '" + billCode + "',fpif.paymentCode='" + paymentCode + "' where fpif.paymentInfoId=" + paymentInfoId + " and fpif.isActive = 1 ";
                    a = " update FeePaymentInfo fpif "
                            + "set fpif.status = 5,fpif.feePaymentTypeId= ? ,fpif.billCode= ? ,fpif.paymentCode=? "
                            + "where fpif.paymentInfoId=? and fpif.isActive = 1 ";
                    params.add(paymentType);
                    params.add(billCode);
                    params.add(paymentCode);
                    params.add(paymentInfoId);
                } else {
                    // 11/11/2014 viethd
//                    a = " update FeePaymentInfo fpif set fpif.status = 5,fpif.feePaymentTypeId= " + paymentType + ",fpif.billCode= '" + billCode + "',fpif.paymentCode='" + paymentCode + "', fpif.billPath = " + billPath + ", fpif.paymentPerson = '" + paymentPerson + "' where fpif.paymentInfoId=" + paymentInfoId + " and fpif.isActive = 1 ";
                    a = " update FeePaymentInfo fpif "
                            + "set fpif.status = 5,fpif.feePaymentTypeId= ? ,fpif.billCode= ? ,"
                            + " fpif.paymentCode=? , fpif.billPath = ?, fpif.paymentPerson = ? "
                            + "where fpif.paymentInfoId=? and fpif.isActive = 1 ";
                    params.add(paymentType);
                    params.add(billCode);
                    params.add(paymentCode);
                    params.add(billPath);
                    params.add(paymentPerson);
                    params.add(paymentInfoId);
                }
            }
            Query query = getSession().createQuery(a);
            int paramSize = params.size();
            for (int i = 0; i < paramSize; i++) {
                query.setParameter(i, params.get(i));
            }
            query.executeUpdate();
            String[] lstVo = billPath.split(";");
            FeeDAOHE fdhe = new FeeDAOHE();
            List<FeePaymentInfo> list = fdhe.findFeePaymentInfo(paymentInfoId);
            FeePaymentInfo fpif = list.get(0);
            fpif.setPaymentDate(paymentDate);
            getSession().update(fpif);
            VoAttachsDAOHE vadhe = new VoAttachsDAOHE();
            for (int i = 0; i < lstVo.length; i++) {
                if (lstVo[i] != null) {
                    VoAttachs voUpload = vadhe.findById(Long.parseLong(lstVo[i]));
                    voUpload.setObjectId(paymentInfoId);
                    voUpload.setObjectType(25L);
                    getSession().update(voUpload);
                }
            }
            bReturn = true;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            bReturn = false;
        }
        return bReturn;

    }

    /**
     *
     * @param paymentInfoId
     * @param fullName
     * @return
     */
    public boolean savePaymentInfoFinal(Long paymentInfoId, String fullName, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Date dateNow = getSysdate();
            FeePaymentInfoDAOHE fdhe = new FeePaymentInfoDAOHE();
            FeePaymentInfo fee = fdhe.findById(paymentInfoId);
            if (fee != null && fee.getFeeId() > 0l && fee.getIsActive() == 1l) {
                fee.setStatus(1l);
                fee.setPaymentConfirm(fullName);
                fee.setDateConfirm(dateNow);
                getSession().update(fee);
            } else {
                bReturn = false;
            }
            FeeDAOHE fdhe1 = new FeeDAOHE();
            FilesDAOHE fdhe2 = new FilesDAOHE();
            Files file = fdhe2.findById(fee.getFileId());
            ProcedureDAOHE pdhe = new ProcedureDAOHE();
            Procedure pde = pdhe.findById(file.getFileType());
            if (file != null && file.getIsActive().equals(1L)) {
                Long confirmFeeStatus;
                file.setModifyDate(dateNow);
                if (file.getStatus().equals(Constants.FILE_STATUS.NEW)
                        || file.getStatus().equals(Constants.FILE_STATUS.NEW_TO_ADD)
                        || file.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT)
                        || file.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {
                    file.setIsFee(1L);
                    getSession().update(file);
                    confirmFeeStatus = Constants.FILE_STATUS.CONFIRM_FEE_EVALUATE;
                } else {
                    if ("01".equals(pde.getCode()) || "02".equals(pde.getCode())) {
                        file.setIsFee(1L);
                        getSession().update(file);
                    } else {
                        List<FeePaymentInfo> feeCheck = fdhe1.findFeePaymentInfoFileId(fee.getFileId());
                        int check = 0;
                        FeePaymentInfo fpifnew;
                        for (int i = 0; i < feeCheck.size(); i++) {
                            fpifnew = feeCheck.get(i);
                            if (fpifnew.getStatus() == 1 && fpifnew.getIsActive() == 1) {
                                check++;
                            }
                        }
                        if (check == 2) {
                            file.setIsFee(1L);
                            getSession().update(file);
                        }
                    }
                    confirmFeeStatus = Constants.FILE_STATUS.CONFIRM_FEE_APPROVED;
                }
                //140107-binhnt53-update bo sung luong xac nhan ke toan vao bang process
                if (file.getIsFee().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                    Process newP = new Process();
                    newP.setObjectId(fee.getFileId());
                    newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                    newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                    newP.setSendDate(dateNow);
                    newP.setSendGroup(deptName);
                    newP.setSendGroupId(deptId);
                    newP.setSendUserId(userId);
                    newP.setSendUser(userName);
                    newP.setReceiveDate(dateNow);
                    newP.setReceiveUserId(null);
                    newP.setReceiveUser(null);
                    newP.setReceiveGroup(deptName);
                    newP.setReceiveGroupId(deptId);
                    newP.setProcessStatus(confirmFeeStatus); // De xu ly
                    newP.setStatus(file.getStatus()); // Moi den chua xu ly
                    newP.setIsActive(1l);
                    getSession().save(newP);
                    //!140107-binhnt53-update bo sung luong xac nhan ke toan vao bang process
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            bReturn = false;
        }
        return bReturn;

    }

    // hieptq update check tien mat 011214
    /**
     *
     * @param paymentInfoId
     * @param fullName
     * @param paymentCode
     * @param billCode
     * @param paymentPerson
     * @return
     */
    public boolean savePaymentInfoCash(Long paymentInfoId, String fullName, String paymentCode, String billCode, String paymentPerson, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Date dateNow = getSysdate();
            FeePaymentInfoDAOHE fdhe = new FeePaymentInfoDAOHE();
            FeePaymentInfo fee = fdhe.findById(paymentInfoId);
            if (fee != null && fee.getFeeId() > 0l && fee.getIsActive() == 1l) {
                fee.setStatus(1l);
                fee.setFeePaymentTypeId(2l);
                fee.setPaymentConfirm(fullName);
                fee.setDateConfirm(dateNow);
                fee.setPaymentDate(dateNow);
                fee.setBillCode(billCode);
                fee.setPaymentPerson(paymentPerson);
                fee.setPaymentCode(paymentCode);
                getSession().update(fee);
            } else {
                bReturn = false;
            }
            FeeDAOHE fdhe1 = new FeeDAOHE();
            FilesDAOHE fdhe2 = new FilesDAOHE();
            Files file = fdhe2.findById(fee.getFileId());
            ProcedureDAOHE pdhe = new ProcedureDAOHE();
            Procedure pde = pdhe.findById(file.getFileType());
            if (file != null && file.getIsActive().equals(1L)) {
                Long confirmFeeStatus;
                file.setModifyDate(dateNow);
                if (file.getStatus().equals(Constants.FILE_STATUS.NEW_CREATE)
                        || file.getStatus().equals(Constants.FILE_STATUS.NEW)
                        || file.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT)
                        || file.getStatus().equals(Constants.FILE_STATUS.NEW_TO_ADD)) {
                    file.setIsFee(1L);
                    getSession().update(file);
                    confirmFeeStatus = Constants.FILE_STATUS.CONFIRM_FEE_EVALUATE;
                } else {
                    if ("01".equals(pde.getCode()) || "02".equals(pde.getCode())) {
                        file.setIsFee(1L);
                        getSession().update(file);
                    } else {
                        List<FeePaymentInfo> feeCheck = fdhe1.findFeePaymentInfoFileId(fee.getFileId());
                        int check = 0;
                        FeePaymentInfo fpifnew;
                        for (int i = 0; i < feeCheck.size(); i++) {
                            fpifnew = feeCheck.get(i);
                            if (fpifnew.getStatus() == 1 && fpifnew.getIsActive() == 1) {
                                check++;
                            }
                        }
                        if (check == 2) {
                            file.setIsFee(1L);
                            getSession().update(file);
                        }
                    }
                    confirmFeeStatus = Constants.FILE_STATUS.CONFIRM_FEE_APPROVED;
                }
                //140107-binhnt53-update bo sung luong xac nhan ke toan vao bang process
                if (file.getIsFee().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                    Process newP = new Process();
                    newP.setObjectId(fee.getFileId());
                    newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                    newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                    newP.setSendDate(dateNow);
                    newP.setSendGroup(deptName);
                    newP.setSendGroupId(deptId);
                    newP.setSendUserId(userId);
                    newP.setSendUser(userName);
                    newP.setReceiveDate(dateNow);
                    newP.setReceiveUserId(null);
                    newP.setReceiveUser(null);
                    newP.setReceiveGroup(deptName);
                    newP.setReceiveGroupId(deptId);
                    newP.setProcessStatus(confirmFeeStatus); // De xu ly
                    newP.setStatus(file.getStatus()); // Moi den chua xu ly
                    newP.setIsActive(1l);
                    getSession().save(newP);
                    //!140107-binhnt53-update bo sung luong xac nhan ke toan vao bang process
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            bReturn = false;
        }
        return bReturn;

    }

    /**
     *
     * @param paymentInfoId
     * @param commentReject
     * @return
     */
    public boolean denyFee(Long paymentInfoId, String commentReject) {
        boolean bReturn = true;
        try {
            Date dateNow = getSysdate();
            FeePaymentInfoDAOHE fdhe = new FeePaymentInfoDAOHE();
            FeePaymentInfo fee = fdhe.findById(paymentInfoId);
            if (fee != null && fee.getFeeId() > 0l && fee.getIsActive() == 1l) {
                fee.setStatus(2l);
                fee.setCommentReject(commentReject);
                getSession().update(fee);
                FilesDAOHE filedaohe = new FilesDAOHE();
                Files filesbo = filedaohe.findById(fee.getFileId());
                if (filesbo != null) {
                    ProcessDAOHE pdhe = new ProcessDAOHE();
                    Process p = pdhe.getProcessByAction(filesbo.getFileId(), Constants.Status.ACTIVE, Constants.OBJECT_TYPE.FILES, Constants.FILE_STATUS.NEW, Constants.FILE_STATUS.NEW_CREATE);
                    if (p != null) {//xoa process
                        p.setLastestComment(commentReject);
                        p.setIsActive(Constants.Status.INACTIVE);
                        getSession().update(p);
                    }
                    filesbo.setModifyDate(dateNow);
                    filesbo.setIsFee(2L);
                    getSession().update(filesbo);
                }
                VoAttachsDAOHE vadhe = new VoAttachsDAOHE();
                String attachId = vadhe.getAttachIDs(paymentInfoId, 25l);
                VoAttachs voUpload = vadhe.findById(Long.parseLong(attachId));
                voUpload.setIsActive(0l);
                getSession().update(voUpload);

            } else {
                bReturn = false;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            bReturn = false;
        }
        return bReturn;

    }

    /**
     * daohe xác nhận phí online kế toán
     *
     * @param paymentInfoId
     * @param fullName
     * @return
     */
    public boolean savePaymentInfoOnline(Long paymentInfoId, String fullName, Long deptId, String deptName, Long userId, String userName) {
        boolean bReturn = true;
        try {
            Date dateNow = getSysdate();
            FeePaymentInfoDAOHE fdhe = new FeePaymentInfoDAOHE();
            FeePaymentInfo fee = fdhe.findById(paymentInfoId);
            if (fee != null && fee.getFeeId() > 0l && fee.getIsActive() == 1l) {
                fee.setStatus(1l);
                fee.setPaymentConfirm(fullName);
                fee.setDateConfirm(dateNow);
                getSession().update(fee);
            } else {
                EventLogDAOHE edhe = new EventLogDAOHE();
                edhe.insertEventLog("KEYPAY", "ERR FeeID: null", getRequest());
                bReturn = false;
            }
            FilesDAOHE fdhe2 = new FilesDAOHE();
            FeeDAOHE fdhe1 = new FeeDAOHE();
            Files file = fdhe2.findById(fee.getFileId());
            ProcedureDAOHE pdhe = new ProcedureDAOHE();
            Procedure pde = pdhe.findById(file.getFileType());
            if (file != null && file.getIsActive().equals(1L)) {
                Long confirmFeeStatus;
                file.setModifyDate(dateNow);
                if (file.getStatus().equals(Constants.FILE_STATUS.NEW)
                        || file.getStatus().equals(Constants.FILE_STATUS.NEW_TO_ADD)
                        || file.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD)) {//check xac nhan le phi tham dinh.
                    file.setIsFee(1L);
                    getSession().update(file);
                    confirmFeeStatus = Constants.FILE_STATUS.CONFIRM_FEE_EVALUATE;
                } else {//la le phi cap giay
                    if ("01".equals(pde.getCode())
                            || "02".equals(pde.getCode())) {//check co phai ho so cong bo hop qui k
                        file.setIsFee(1L);
                        getSession().update(file);
                    } else {
                        List<FeePaymentInfo> feeCheck = fdhe1.findFeePaymentInfoFileId(fee.getFileId());
                        int check = 0;
                        FeePaymentInfo fpifnew;
                        for (int i = 0; i < feeCheck.size(); i++) {
                            fpifnew = feeCheck.get(i);
                            if (fpifnew.getStatus() == 1 && fpifnew.getIsActive() == 1) {
                                check++;
                            }
                        }
                        if (check == 2) {
                            file.setIsFee(1L);
                            getSession().update(file);
                        }
                    }
                    confirmFeeStatus = Constants.FILE_STATUS.CONFIRM_FEE_APPROVED;
                }
                //140107-binhnt53-update bo sung luong xac nhan ke toan vao bang process

                if (file.getIsFee().equals(Constants.ACTIVE_STATUS.ACTIVE)) {
                    Process newP = new Process();
                    newP.setObjectId(fee.getFileId());
                    newP.setObjectType(Constants.OBJECT_TYPE.FILES);
                    newP.setProcessType(Constants.PROCESS_TYPE.MAIN);
                    newP.setSendDate(dateNow);
                    newP.setSendGroup(deptName);
                    newP.setSendGroupId(deptId);
                    newP.setSendUserId(userId);
//                    newP.setSendUser(userName);
                    newP.setSendUser(Constants.KEYPAY.KEYPAY);
                    newP.setReceiveDate(dateNow);
                    newP.setReceiveUserId(null);
                    newP.setReceiveUser(null);
                    newP.setReceiveGroup(deptName);
                    newP.setReceiveGroupId(deptId);
                    newP.setProcessStatus(confirmFeeStatus); // De xu ly
                    newP.setStatus(file.getStatus()); // Moi den chua xu ly
                    newP.setIsActive(1l);
                    getSession().save(newP);
                    //!140107-binhnt53-update bo sung luong xac nhan ke toan vao bang process
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            EventLogDAOHE edhe = new EventLogDAOHE();
            edhe.insertEventLog("KEYPAY", "ERR: " + ex.getMessage(), getRequest());
            bReturn = false;
        }
        return bReturn;

    }

    // hieptq - 15.11.14 dem trang chu ke toan
    /**
     *
     * @param userId
     * @param status
     * @param type
     * @return
     */
    public int getCountFileToProcessFeePayManage(Long userId, Long status, Long type) {
        UsersDAOHE udhe = new UsersDAOHE();
        Users user = udhe.findById(userId);
        String sql = "from files f inner join fee_payment_info fpi on f.file_id = fpi.file_id "
                + "where fpi.fee_id in (select f.fee_id from fee f where f.fee_type = 2 and f.is_active = 1 ) "
                + "and fpi.is_active = 1 "
                + "and f.agency_id = ? "
                + "and f.is_active = 1 "
                + "and (f.user_Signed is not null or f.status = 18) "
                + "and f.status <> 0 ";

        List lstParam = new ArrayList();
        lstParam.add(user.getDeptId());
        if (status == 0) {
            sql += " and fpi.status > 2";
        } else {
            //sql += "and fpi.status = " + searchFeeFormNew.getStatus(); // 11/11/2014 viethd
            sql += "and fpi.status = 1 ";

        }
        // hieptq them nhom san pham 17.11.14
        if (type == 1) {
            sql += " and fpi.cost = 1500000";
        } else {
            sql += " and fpi.cost = 500000 ";
        }

        sql += " order by fpi.payment_date desc ";
        SQLQuery countQuery = (SQLQuery) getSession().createSQLQuery("select count (distinct fpi.payment_info_id) " + sql);
        //hieptq update them agencyId 28.11.14
        int paramSize = lstParam.size();
        for (int i = 0; i < paramSize; i++) {
            countQuery.setParameter(i, lstParam.get(i));
        }
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        return total;
    }

    /**
     *
     * @param userId
     * @param status
     * @return
     */
    // hieptq - 15.11.14 dem le phi man hinh ke toan
    public int getCountFileToProcessFeeManage(Long userId, Long status) {
        UsersDAOHE udhe = new UsersDAOHE();
        Users user = udhe.findById(userId);

        String sql = "from files f inner join fee_payment_info fpi on f.file_id = fpi.file_id "
                + "where fpi.fee_id in (select f.fee_id from fee f "
                + "                         where f.fee_type = 1 and f.is_active = 1 ) "
                //+ "and f.agency_id =" + user.getDeptId() + " " // comment 11/11/2014 viethd
                + "and f.agency_id = ? "
                + "and (f.status = 15 or f.status=16 or f.status=6 or f.status=22 or f.status = 23) "
                + "and fpi.is_active=1 "
                + "and f.is_active = 1 "
                + "and fpi.status <> 0 ";

        List lstParam = new ArrayList();
        lstParam.add(user.getDeptId());

        if (status == 0) {
            sql += " and fpi.status > 2";
        } else {
            //sql += "and fpi.status = " + searchFeeFormNew.getStatus(); // 11/11/2014 viethd
            sql += "and fpi.status = 1 ";
        }
        sql += " order by fpi.payment_date desc ";
        SQLQuery countQuery = (SQLQuery) getSession().createSQLQuery("select count (distinct fpi.payment_info_id) " + sql);
        int paramSize = lstParam.size();
        for (int i = 0; i < paramSize; i++) {
            countQuery.setParameter(i, lstParam.get(i));
        }
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        return total;
    }

    //hieptq update 210115
    public boolean onResetFeeTl(String fileCode) {
        boolean bReturn = true;
        try {
            FeeDAOHE fdhe = new FeeDAOHE();
            Long fileId = fdhe.getFileIdByFileCode(fileCode);
            FeePaymentInfoDAOHE fpidhe = new FeePaymentInfoDAOHE();
            List lstParam = new ArrayList();
            List<FeePaymentInfo> lstFeePaymentInfo;
            String hql = "select fpi from FeePaymentInfo fpi where fpi.fileId = ?";
            lstParam.add(fileId);
            Query query = getSession().createQuery(hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
            }
            lstFeePaymentInfo = query.list();

            for (int i = 0; i < lstFeePaymentInfo.size(); i++) {
                FeePaymentInfo fpif = fpidhe.findById(lstFeePaymentInfo.get(i).getPaymentInfoId());
                fpif.setCost(0l);
                getSession().update(fpif);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(ex.getMessage());
            bReturn = false;
        }
        return bReturn;

    }

    public Long getFileIdByFileCode(String fileCode) {
        String sql = "select f.fileId from Files f "
                + "where f.fileCode = ? "
                + "and f.isTemp = null "
                + "and f.isActive = 1";
        Query query = getSession().createQuery(sql);
        query.setParameter(0, fileCode);
        List<Long> result = query.list();
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return 0l;
    }

    public Long getFileByCode4Change(String fileCode, Long status) {
        String sql = "select f.fileId from Files f "
                + "where f.fileCode = ? "
                + "and f.status = ?"
                + "and f.isTemp = null "
                + "and f.isActive = 1";
        Query query = getSession().createQuery(sql);
        query.setParameter(0, fileCode);
        query.setParameter(1, status);
        List<Long> result = query.list();
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return 0l;
    }

    //hieptq update 090615
    public boolean onChangeFileType(String fileCode, String fileTypeNew) {
        try {
            FeeDAOHE feedhe = new FeeDAOHE();
            FilesDAOHE fdhe = new FilesDAOHE();
            ProcedureDAOHE pdhe = new ProcedureDAOHE();

            Long proceduceIdNew = Long.parseLong(fileTypeNew);
            Long fileId = feedhe.getFileByCode4Change(fileCode, Constants.FILE_STATUS.EVALUATED_TO_ADD);

            Fee feeWantChange = feedhe.findFeeByFeeType(1L);
            Files fileBo = fdhe.findById(fileId);
            // check hs o trang thai SDBS moi sua dc
            //if (file.getStatus().equals(Constants.FILE_STATUS.EVALUATED_TO_ADD) || file.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT) || file.getStatus().equals(Constants.FILE_STATUS.RECEIVED_REJECT_TO_ADD)) {
            Long fileType = fileBo.getFileType();
            Procedure proBoNew = pdhe.findById(proceduceIdNew);
            Procedure proBo = pdhe.findById(fileType);
            // check hop qui sang phu hop
            if (proBo.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01)
                    || proBo.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03)
                    || proBo.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01_TL)
                    || proBo.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03_TL)) {
                // check hop quy sang hop quy thi return
                if (proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01)
                        || proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03)
                        || proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01_TL)
                        || proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03_TL)) {
                    fileBo.setFileType(proBoNew.getProcedureId());
                    fileBo.setFileTypeName(proBoNew.getName());
                    fileBo.setUserSigned("");
                    getSession().update(fileBo);
                    return true;
                } else//hop quy sang phu hop
                // truong hop chuyen sang loai moi them tien - thuc pham chuc nang
                 if (proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP)
                            || proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN)) {
//                            // update phi ban ghi cu
                        String hql = "select fpif from FeePaymentInfo fpif "
                                + "where fpif.fileId =:fileId "
                                + "and fpif.isActive = 1";
                        Query query = getSession().createQuery(hql);
                        query.setParameter("fileId", fileId);
                        List<FeePaymentInfo> FeePaymentInfo = query.list();
                        FeePaymentInfo fpifOld = FeePaymentInfo.get(0);
                        fpifOld.setStatus(0L);
//fpifOld.setCost(Constants.PRICE.TPCN);
//fpifOld.setCostCheck(Constants.PRICE.TPCN);
                        getSession().update(fpifOld);
                        //them ban ghi moi
                        FeePaymentInfo fpif = new FeePaymentInfo();
                        fpif.setCreateDate(getSysdate());
                        fpif.setStatus(0L);
                        fpif.setFileId(fileId);
                        fpif.setIsActive(1l);
                        fpif.setFeeId(feeWantChange.getFeeId());
                        fpif.setCost(feeWantChange.getPrice());
                        getSession().save(fpif);
                        //update bang file
                        fileBo.setFileType(proceduceIdNew);
                        fileBo.setFileTypeName(proBoNew.getName());
                        fileBo.setIsFee(0l);
                        fileBo.setUserSigned("reset");
//                            DetailProductDAOHE dphe = new DetailProductDAOHE();
//                            DetailProduct dp = dphe.findById(file.getDetailProductId());
//                            dp.setProductType(null);
//                            getSession().update(dp);
                        getSession().update(fileBo);
                        return true;
                    } else if (!proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.REC_CONFIRM_NORMAL_IMP)
                            && !proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT)
                            && !proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_IMP)
                            && !proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_VN)
                            && !proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.RE_CONFIRM_NORMAL_VN)
                            && !proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05_PAPER)
                            && !proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR)) {

                        FeePaymentInfo fpif = new FeePaymentInfo();
                        fpif.setCreateDate(getSysdate());
                        fpif.setStatus(0l);
                        fpif.setFileId(fileId);
                        fpif.setIsActive(1l);
                        fpif.setFeeId(feeWantChange.getFeeId());
                        fpif.setCost(feeWantChange.getPrice());
                        getSession().save(fpif);
                        //update bang file
                        fileBo.setFileType(proBoNew.getProcedureId());
                        fileBo.setFileTypeName(proBoNew.getName());
                        fileBo.setUserSigned("");
                        getSession().update(fileBo);
                        return true;
                    } //}
            } else// phu hop sang phu hop or nguoc lai
             if (proBo.getDescription().equals(Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP)
                        || proBo.getDescription().equals(Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP)
                        || proBo.getDescription().equals(Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN)
                        || proBo.getDescription().equals(Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN)) {

                    if (proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP)
                            || proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN)
                            || proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01)
                            || proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03)
                            || proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE01_TL)
                            || proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE03_TL)) {

                        fileBo.setFileType(proBoNew.getProcedureId());
                        fileBo.setFileTypeName(proBoNew.getName());
                        fileBo.setUserSigned("");
                        getSession().update(fileBo);
                        return true;
                    } else if (proBo.getDescription().equals(Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_IMP)
                            || proBo.getDescription().equals(Constants.FILE_DESCRIPTION.CONFIRM_NORMAL_VN)) {

                        if (proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.CONFIRM_FUNC_IMP)
                                || proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.CONFIRM_FUNC_VN)) {

                            String hql = "select fpif from FeePaymentInfo fpif "
                                    + "where fpif.fileId =:fileId "
                                    + "and fpif.isActive = 1";
                            Query query = getSession().createQuery(hql);
                            query.setParameter("fileId", fileId);
                            List<FeePaymentInfo> FeePaymentInfo = query.list();
                            for (int i = 0; i < FeePaymentInfo.size(); i++) {
                                FeePaymentInfo fpif = FeePaymentInfo.get(i);
                                if (fpif.getFeeId().equals(feeWantChange.getFeeId())) {
                                    fpif.setIsActive(0l);
                                    getSession().update(fpif);
                                }
                                if (fpif.getCost().equals(Constants.PRICE.TPT)) {
                                    fpif.setStatus(0l);
                                    getSession().update(fpif);
                                }
                            }
                            fileBo.setFileType(proceduceIdNew);
                            fileBo.setFileTypeName(proBoNew.getName());
                            fileBo.setIsFee(0l);
                            fileBo.setUserSigned("reset");
                            getSession().update(fileBo);
                            return true;
                        }
                    } else if (!proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.REC_CONFIRM_NORMAL_IMP)
                            && !proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.RE_ANNOUNCEMENT)
                            && !proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_IMP)
                            && !proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.RE_CONFIRM_FUNC_VN)
                            && !proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.RE_CONFIRM_NORMAL_VN)
                            && !proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_FILE05_PAPER)
                            && !proBoNew.getDescription().equals(Constants.FILE_DESCRIPTION.ANNOUNCEMENT_4STAR)) {
                        String hql = "select fpif from FeePaymentInfo fpif "
                                + "where fpif.fileId =:fileId "
                                + "and fpif.isActive = 1";
                        Query query = getSession().createQuery(hql);
                        query.setParameter("fileId", fileId);
                        List<FeePaymentInfo> FeePaymentInfo = query.list();
                        for (int i = 0; i < FeePaymentInfo.size(); i++) {
                            FeePaymentInfo fpif = FeePaymentInfo.get(i);
                            if (fpif.getFeeId().equals(feeWantChange.getFeeId())) {
                                fpif.setIsActive(0l);
                                getSession().update(fpif);
                            }
                        }
                        fileBo.setFileType(proceduceIdNew);
                        fileBo.setFileTypeName(proBoNew.getName());
                        fileBo.setUserSigned("");
                        getSession().update(fileBo);
                        return true;
                    }
                }
        } catch (NumberFormatException | HibernateException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return false;
        }
        return false;
    }

    public Fee findFeeByFeeType(Long feeType) {
        String hql = "from Fee f where f.feeType = ? ";
        Query query = getSession().createQuery("select f " + hql);
        query.setParameter(0, feeType);
        List<Fee> lstResult = query.list();
        if (lstResult != null && lstResult.size() > 0) {
            return lstResult.get(0);
        }
        return null;
    }

}
