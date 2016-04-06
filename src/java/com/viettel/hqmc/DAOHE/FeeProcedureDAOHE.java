/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

//import com.viettel.common.util.Constants;
import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.Fee;
//import com.viettel.hqmc.BO.FeeProcedure;
import com.viettel.hqmc.FORM.FeeForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author binhnt53
 */
public class FeeProcedureDAOHE extends GenericDAOHibernate<Fee, Long> {

    private static Logger log = Logger.getLogger(Fee.class);
    private List keyList = new ArrayList();
    private List valueList = new ArrayList();
    private static HashMap<String, List> lstFactory = new HashMap();
    private List lstStandard;
//========================================================================

    public FeeProcedureDAOHE() {
        super(Fee.class);
    }
    /**
     * findAllFee
     *
     * @param UserId
     * @return
     */
    public List findAllFee(Long UserId) {
        try {
            StringBuilder stringBuilder = new StringBuilder(" from Fee u ");
            stringBuilder.append("where u.isActive=1");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstStandard = query.list();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ArrayList<>();
        }
        return lstStandard;
    }

    public FeeForm boToForm(Fee bo) {
        FeeForm frm = new FeeForm(bo);
        return frm;
    }
    /**
     * getLstFee
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
        } catch (Exception e) {
            e.getMessage();
            return new GridResult(0, null);
        }
    }
   /**
     * findFee
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

//    public boolean onCreateFee(FeeForm feeForm, Long userId) {
//        boolean bReturn = true;
//        try {
//
//            Fee fee = new Fee();
//            List procedure = null;
//
//            Long stt = feeForm.getIsActive();
//            if (feeForm.getFeeId() == null) {
//                fee.setIsActive(stt);
//                fee.setFeeName(feeForm.getFeeName());
//                fee.setCreateDate(getSysdate());
//                fee.setCreateUserId(userId);
//                fee.setDescription(feeForm.getDescription());
//                fee.setPrice(feeForm.getPrice());
//                fee.setUpdateDate(getSysdate());
//                fee.setUpdateUserId(userId);
//                fee.setProcedureId(feeForm.getProcedureId());
//                getSession().save(fee);
//                String procedureId = feeForm.getProcedureId();
//                String[] tokens = procedureId.split(";");
//                List<String> list = Arrays.asList(tokens);
//                for (int i = 0; i < list.size(); i++) {
//                    FeeProcedure feepro = new FeeProcedure();
//                    String id = list.get(i);
//                    feepro.setProcedureId(Long.parseLong(id));
//                    feepro.setFeeId(fee.getFeeId());
//                    getSession().save(feepro);
//                }
//                bReturn = true;
//            } else {
//                fee = findById(feeForm.getFeeId());
//                fee.setFeeId((fee.getFeeId()));
//                fee.setIsActive(stt);
//                fee.setFeeName(feeForm.getFeeName());
//                fee.setCreateUserId(userId);
//                fee.setDescription(feeForm.getDescription());
//                fee.setPrice(feeForm.getPrice());
//                fee.setUpdateDate(getSysdate());
//                fee.setUpdateUserId(userId);
//                getSession().update(fee);
//                String procedureId = feeForm.getProcedureId();
//                String[] tokens = procedureId.split(";");
//                List<String> list = Arrays.asList(tokens);
//                for (int i = 0; i < list.size(); i++) {
//                    FeeProcedure feepro = new FeeProcedure();
//                    String id = list.get(i);
//                     FeeProcedure bo = cthe.getById("feeId", form.getFeeId());
////                    feepro.setProcedureId(Long.parseLong(id));
////                    feepro.setFeeId(fee.getFeeId());
//                    getSession().save(feepro);
//                }
//
////                feepro = findById(fee.getFeeId());
////                feepro.setFeeId((fee.getFeeId()));
////                 feepro.setFeeId(fee.getFeeId());
////                feepro.setProcedureId(feeForm.getProcedureId());
////                getSession().save(fee);
//                bReturn = true;
//            }
//        } catch (Exception en) {
//            System.out.println(en.getMessage());
//            bReturn = false;
//        }
//        return bReturn;
//    }
//
//    public String onDelete(Long id)  {
//        List resultMessage = new ArrayList();
//        try {
//            FeeProcedureDAOHE cthe = new FeeProcedureDAOHE();
//            for (int i = 0; i < lstItemOnGrid.size(); i++) {
//                FeeForm form = lstItemOnGrid.get(i);
//                if (form != null && form.getFeeId() != null && form.getFeeId() != 0D) {
//                    Fee bo = cthe.getById("feeId", form.getFeeId());
//                    if (bo != null) {
//                        bo.setIsActive(Long.parseLong("0"));
//                        getSession().update(bo);
//                    }
//                }
//            }
//            resultMessage.add("1");
//            resultMessage.add("Xóa biểu phí thành công");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            resultMessage.add("3");
//            resultMessage.add("Xóa biểu phí không thành công");
//        }
//
//        jsonDataGrid.setItems(resultMessage);
//        return GRID_DATA;
//    }
}
