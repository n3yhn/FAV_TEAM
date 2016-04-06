/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.Fee;
import com.viettel.hqmc.BO.FeePaymentInfo;
import com.viettel.hqmc.BO.FeeProcedure;
import com.viettel.hqmc.BO.Procedure;
import com.viettel.hqmc.BO.VFeePaymentInfoFee;
import com.viettel.hqmc.FORM.FeeForm;
import com.viettel.hqmc.FORM.FeePaymentFileForm;
import com.viettel.hqmc.FORM.FeePaymentInfoForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import com.viettel.vsaadmin.database.BO.Users;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
//import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

/**
 *
 * @author binhnt53
 */
public class FeePaymentInfoDAOHE extends GenericDAOHibernate<FeePaymentInfo, Long> {

    private static final Logger log = Logger.getLogger(FeePaymentInfo.class);
    private final List keyList = new ArrayList();
    private final List valueList = new ArrayList();
    private static final HashMap<String, List> lstFactory = new HashMap();
    private List lstStandard;
//========================================================================

    public FeePaymentInfoDAOHE() {
        super(FeePaymentInfo.class);
    }

    /**
     * lay so luong thong tin phi
     *
     * @param objectId
     * @param objectType
     * @param feeId
     * @return
     */
    public Long getTotalFeePaymentInfo(Long objectId, Long objectType, Long feeId) {
        Long result = 0L;
        try {
            String selectHQL = "SELECT fpi";
            String hql = " FROM FeePaymentInfo fpi WHERE (fpi.isActive = 1) and (fpi.fileId = ?)";
            hql += " ORDER BY fpi.paymentDate ";
            Query query = getSession().createQuery(selectHQL + hql);
            query.setParameter(0, objectId);
            List<FeePaymentInfo> lst = query.list();
            for (FeePaymentInfo bo : lst) {
                result += bo.getCost();
            }
        } catch (Exception e) {
            e.getMessage();
            log.error(e);
        }
        return result;
    }

    /**
     * danh sach phi doanh nghiep
     *
     * @param objectId
     * @param objectType
     * @param feeType
     * @param start
     * @param nRecord
     * @param sortField
     * @return
     */
    public GridResult getLstFeePaymentInfo(Long objectId, Long objectType, Long feeType, int start, int nRecord, String sortField) {
        List lstParam = new ArrayList();
        try {
            String countHql = "SELECT count(v) ";
            String selectHQL = "SELECT v ";
            String hql = " FROM VFeePaymentInfoFee v WHERE v.fileId = ?";
            lstParam.add(objectId);
            if (feeType != 0L) {
                hql += " and v.feeType =?";
                lstParam.add(feeType);
            }
            hql += " ORDER BY v.paymentDate";

            Query query = getSession().createQuery(selectHQL + hql);
            Query countQuery = getSession().createQuery(countHql + hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }
            Long nCount = (Long) countQuery.uniqueResult();

            query.setFirstResult(start);
            query.setMaxResults(nRecord);
            List<VFeePaymentInfoFee> lst = query.list();
            GridResult result = new GridResult(nCount, lst);
            return result;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new GridResult(0, null);
        }
    }

    /**
     * tim kiem phi
     *
     * @param UserId
     * @return
     */
    public List findAllFee(Long UserId) {
        try {
            StringBuilder stringBuilder = new StringBuilder(" from FeePaymentInfo u ");
            stringBuilder.append("where u.isActive=1");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstStandard = query.list();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ArrayList<>();
        }
        return lstStandard;
    }

    /**
     * tim kiem phi
     *
     * @param procedureId
     * @return
     */
    public List findAllFeeByProcedureId(Long procedureId) {
        try {
            StringBuilder stringBuilder = new StringBuilder(" from FeeProcedure u ");
            stringBuilder.append("where u.isActive=1 and u.procedureId = ?");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, procedureId);
            lstStandard = query.list();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ArrayList<>();
        }
        return lstStandard;
    }

    /**
     * tim kiem phi
     *
     * @param procedureId
     * @return
     */
    public List findAllFeeByProcedureIdNew(Long procedureId) {
        try {
            StringBuilder stringBuilder = new StringBuilder(" from FeePaymentInfo f ");
            stringBuilder.append("where f.feeId in (\n"
                    + "\n"
                    + "select fp.feeId from FeeProcedure fp where fp.procedureId = ? )");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, procedureId);
            lstStandard = query.list();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ArrayList<>();
        }
        return lstStandard;
    }

    public FeePaymentInfoForm boToForm(FeePaymentInfo bo) {
        FeePaymentInfoForm frm = new FeePaymentInfoForm(bo);
        return frm;
    }

    /**
     * tim kiem phi
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findFee(FeeForm form, int start, int count, String sortField) {
        String hql = " from Fee u where (u.isActive = 1) ";
        List lstParam = new ArrayList();
        if (form != null) {
            if (form.getCreateDate() != null) {
                hql += " and lower(u.createDate) = ?";
                lstParam.add(form.getCreateDate());
            }
            if (form.getCreateUserId() != null) {
                hql += " and u.createUserId = ?";
                lstParam.add(form.getCreateUserId());
            }
            if (form.getDescription() != null) {
                hql += " AND lower(u.description) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getDescription().toLowerCase().trim()));
            }
//            if (form.getIsActive() != null && form.getIsActive() != -1) {
//                hql += " and u.isActive = ? ";
//                lstParam.add(form.getIsActive());
//            }
            if (form.getFeeName() != null) {
                hql += " AND lower(u.feeName) like ? ESCAPE '/'";
                lstParam.add(StringUtils.toLikeString(form.getFeeName().toLowerCase().trim()));
            }
            if (form.getPrice() != null) {
                hql += " and u.price = ? ";
                lstParam.add(form.getPrice());
            }
            if (form.getUpdateDate() != null) {
                hql += " and lower(u.updateDate) = ?";
                lstParam.add(form.getUpdateDate());
            }
            if (form.getUpdateUserId() != null) {
                hql += " and lower(u.updateUserId) = ?";
                lstParam.add(form.getUpdateUserId());
            }
        }
        Query countQuery = getSession().createQuery("select count(*) " + hql);
        Query query = getSession().createQuery("select u " + hql + " order by u.feeId desc");
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
     * tao moi phi
     *
     * @param feeForm
     * @param userId
     * @return
     */
    public boolean onCreateFee(FeeForm feeForm, Long userId) {
        boolean bReturn = true;
        try {

            Fee fee = new Fee();
            List procedure = null;

            Long stt = feeForm.getIsActive();
            Long feeType = feeForm.getFeeType();
            if (feeForm.getFeeId() == null) {
                fee.setIsActive(stt);
                fee.setFeeName(feeForm.getFeeName());
                fee.setCreateDate(getSysdate());
                fee.setCreateUserId(userId);
                fee.setDescription(feeForm.getDescription());
                fee.setPrice(feeForm.getPrice());
                fee.setUpdateDate(getSysdate());
                fee.setUpdateUserId(userId);
                fee.setProcedureId(feeForm.getProcedureId());
                fee.setFeeType(feeType);
                getSession().save(fee);
                String procedureId = feeForm.getProcedureId();
                String[] tokens = procedureId.split(";");
                List<String> list = Arrays.asList(tokens);
                String procedureName = "";
                for (int i = 0; i < list.size(); i++) {
                    String id = list.get(i);
                    ProcedureDAOHE pdhe = new ProcedureDAOHE();
                    Procedure pdh = pdhe.findById(Long.parseLong(id));
                    procedureName += pdh.getName() + ";";

                }
                if (procedureName != "") {
                    procedureName = procedureName.substring(0, procedureName.length() - 1);
                }
                fee.setProcedureName(procedureName);

                for (int i = 0; i < list.size(); i++) {
                    FeeProcedure feepro = new FeeProcedure();
                    String id = list.get(i);
                    feepro.setProcedureId(Long.parseLong(id));
                    feepro.setFeeId(fee.getFeeId());
                    feepro.setIsActive(1l);
                    getSession().save(feepro);
                }
                bReturn = true;
            } else {
                //fee = findById(feeForm.getFeeId());
                fee.setFeeId((fee.getFeeId()));
                fee.setFeeType(feeType);
                fee.setIsActive(stt);
                fee.setFeeName(feeForm.getFeeName());
                fee.setCreateUserId(userId);
                fee.setDescription(feeForm.getDescription());
                fee.setPrice(feeForm.getPrice());
                fee.setUpdateDate(getSysdate());
                fee.setUpdateUserId(userId);
                fee.setProcedureId(feeForm.getProcedureId());
                String procedureId = feeForm.getProcedureId();
                //ghi lai gia tri khi edit

                String[] tokens = procedureId.split(";");
                List<String> list = Arrays.asList(tokens);
                String procedureName = "";
                for (int i = 0; i < list.size(); i++) {
                    String id = list.get(i);
                    ProcedureDAOHE pdhe = new ProcedureDAOHE();
                    Procedure pdh = pdhe.findById(Long.parseLong(id));
                    procedureName += pdh.getName() + ";";

                }
                if (procedureName != "") {
                    procedureName = procedureName.substring(0, procedureName.length() - 1);
                }
                fee.setProcedureName(procedureName);

                getSession().update(fee);
//                String procedureId = feeForm.getProcedureId();
//                String[] tokens = procedureId.split(";");
//                List<String> list = Arrays.asList(tokens);
                //set active = 0 ( xoa tam )
                FeePaymentInfoDAOHE fdhe = new FeePaymentInfoDAOHE();
                fdhe.deleteProcedure(feeForm.getFeeId());
                for (int i = 0; i < list.size(); i++) {
                    FeeProcedure feepro1 = (FeeProcedure) findById(FeeProcedure.class, "feeId", feeForm.getFeeId());
                    String id = list.get(i);
                    feepro1.setIsActive(1l);
                    getSession().save(feepro1);
                }

                bReturn = true;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            bReturn = false;
        }
        return bReturn;
    }

     /**
     * xoa thu tuc hanh chinh map voi phi
     *
     * @param feeId
     * @return
     */
    public void deleteProcedure(Long feeId) {
        int iReturn = 0;
        // 11/11/2014 viethd
        //String a = " update FeeProcedure fp set fp.isActive = 0 where fp.feeId=" + feeId;
        String a = " update FeeProcedure fp set fp.isActive = 0 where fp.feeId= ? ";
        Query query = getSession().createQuery(a);
        query.setParameter(0, feeId);
        query.executeUpdate();
    }

    
    
     /**
     * danh sach hoa don doanh nghiep
     *
     * @param fileId
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult getLstPayment(Long fileId, int start, int count, String sortField) {

//        String sql = "from Fee f left join Fee_Payment_Info fpi on f.fee_Id = fpi.fee_Id where f.fee_Id "
//                     + "in (select fp.fee_Id from Fee_Procedure fp where fp.procedure_Id = ((select fi.file_Type from Files fi where fi.file_Id = ?)) union "
//                     + "select ff.fee_Id from Fee_File ff where ff.file_Id = ? )  and f.is_Active = 1";
        String sql = "from fee f inner join fee_payment_info fpi on f.fee_id = fpi.fee_id where fpi.file_id = ? and f.is_Active=1 and fpi.is_Active=1";
        SQLQuery countQuery = (SQLQuery) getSession().createSQLQuery("select count(*) " + sql);
        SQLQuery query = (SQLQuery) getSession().createSQLQuery("select f.fee_Id,f.fee_Name,f.description,fpi.cost,f.fee_Type,fpi.status,fpi.fee_Payment_Type_Id, f.price,fpi.payment_Person,fpi.payment_Date,fpi.payment_Info,fpi.bill_path  " + sql);

        query.setLong(0, fileId);
        //query.setLong(1, fileId);
        countQuery.setParameter(0, fileId);
        //countQuery.setParameter(1, fileId);
        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List lstResult = query.list();
        FeePaymentFileForm item = new FeePaymentFileForm();
        List result = new ArrayList<FeePaymentFileForm>();

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
                if (row[7] != null && !"".equals(row[7])) {
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
            }
            result.add(item);
            item = new FeePaymentFileForm();
        }
        GridResult gr = new GridResult(total, result);
        return gr;
    }
     /**
     * quan ly nop phi
     *
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult getLstFeeManage(int start, int count, String sortField) {
        String sql = "from files f inner join fee_payment_info fpi on f.file_id = fpi.file_id where fpi.fee_id in (select f.fee_id from fee f where f.fee_type = 1 )";
        SQLQuery countQuery = (SQLQuery) getSession().createSQLQuery("select count (distinct fpi.payment_info_id) " + sql);
        SQLQuery query = (SQLQuery) getSession().createSQLQuery("select distinct f.file_code,f.product_name,fpi.payment_date,fpi.cost,fpi.bill_path,fpi.fee_payment_type_id,fpi.status,fpi.fee_id,fpi.file_id,fpi.payment_info_id,fpi.payment_person " + sql);
        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List lstResult = query.list();
        FeePaymentFileForm item = new FeePaymentFileForm();
        List result = new ArrayList<FeePaymentFileForm>();

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
                    item.setPaymentDate(row[2].toString());
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

            }
            result.add(item);
            item = new FeePaymentFileForm();
        }
        GridResult gr = new GridResult(total, result);
        return gr;
    }
  
  /**
     * insert hoa don
     *
     * @param fileId
     * @param feeId
     * @param userId
     * @return
     */
    public boolean insertFeePaymentInfo(Long fileId, Long feeId, Long userId) {
        boolean bReturn = true;
        try {
            //FeePaymentInfo fpif = (FeePaymentInfo) findById(FeePaymentInfo.class, "fileId", fileId);
            // 11/11/2014 viethd
            //String a = " select fpif from FeePaymentInfo fpif where fpif.feeId = " + feeId + " and fpif.fileId=" + fileId;
            String a = " select fpif from FeePaymentInfo fpif where fpif.feeId = ? and fpif.fileId= ? ";
            Query query = getSession().createQuery(a);
            query.setParameter(0, feeId);
            query.setParameter(1, fileId);
            List<FeePaymentInfo> list = query.list();
            FeePaymentInfo fpif = list.get(0);
            fpif.setStatus(2l);
            fpif.setFeePaymentTypeId(1l);
            fpif.setPaymentDate(getSysdate());

            Users user = (Users) findById(Users.class, "userId", userId);
            fpif.setPaymentPerson(user.getUserName());
            //fpif.setPaymentPerson();
            getSession().saveOrUpdate(fpif);
            bReturn = true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            bReturn = false;
        }
        return bReturn;

    }

      /**
     * save hoa don
     *
     * @param paymentInfoId
     * @param paymentType
     * @return
     */
    public boolean savePaymentInfo(Long paymentInfoId, Long paymentType) {
        boolean bReturn = true;
        try {
            //FeePaymentInfo fpif = (FeePaymentInfo) findById(FeePaymentInfo.class, "fileId", fileId);
            // 11/11/2014 viethd
            //String a = " update FeePaymentInfo fpif set fpif.status = 1,fpif.feePaymentTypeId= " + paymentType + " where fpif.paymentInfoId=" + paymentInfoId;
            String a = " update FeePaymentInfo fpif set fpif.status = 1,fpif.feePaymentTypeId= ? "
                    + "where fpif.paymentInfoId=? ";
            Query query = getSession().createQuery(a);
            query.setParameter(0, paymentType);
            query.setParameter(1, paymentInfoId);
            query.executeUpdate();

            bReturn = true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            bReturn = false;
        }
        return bReturn;

    }
    /**
     * checkFPIByFileID
     *
     * @param fileId
     * @return
     */
    public boolean checkFPIByFileID(Long fileId) {
        boolean bReturn = true;
        List lstParam = new ArrayList();
        List<FeePaymentInfo> lstFeePaymentInfo = null;
        try {
            String hql = "select fpi from FeePaymentInfo fpi where fpi.fileId = ?";
            lstParam.add(fileId);
            Query query = getSession().createQuery(hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
            }
            lstFeePaymentInfo = query.list();
            if (lstFeePaymentInfo.isEmpty()) {
                return false;
            } else {
                for (int i = 0; i < lstFeePaymentInfo.size(); i++) {
                    if (lstFeePaymentInfo.get(i).getStatus() == 1L && lstFeePaymentInfo.get(i).getIsActive() == 1L) {
                        bReturn = true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            bReturn = false;
        }
        return bReturn;
    }
}
