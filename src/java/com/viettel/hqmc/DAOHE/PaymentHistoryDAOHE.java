/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.PaymentHistory;
import com.viettel.hqmc.FORM.PaymentHistoryForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author BINHNT53
 */
public class PaymentHistoryDAOHE extends GenericDAOHibernate<PaymentHistory, Long> {

    private static HashMap<String, List> lstFactory = new HashMap();

    public static HashMap<String, List> getLstFactory() {
        return lstFactory;
    }

    public static void setLstFactory(HashMap<String, List> lstFactory) {
        PaymentHistoryDAOHE.lstFactory = lstFactory;
    }
    private static Logger log = Logger.getLogger(PaymentHistory.class);
    private List keyList = new ArrayList();
    private List valueList = new ArrayList();
    //removeCache

    public static void removeCache(String type) {
        if (lstFactory == null) {
            return;
        }
        if (lstFactory.containsKey(type)) {
            lstFactory.remove(type);
        }
    }
    /*
     *
     */

    public PaymentHistoryDAOHE(Class<PaymentHistory> type) {
        super(type);
    }

    public PaymentHistoryDAOHE() {
        super(PaymentHistory.class);
    }
    /**
     * findPaymentHistory
     *
     * @param form
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findPaymentHistory(PaymentHistoryForm form, int start, int count, String sortField) {
        String hql = " from PaymentHistory c where status = 1 ";
        List lstParam = new ArrayList();
        if (form != null) {
            if (form.getPaymentNo() != null && form.getPaymentNo().trim().length() > 0) {
                hql += " and lower(c.paymentNo) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getPaymentNo().trim()));
            }
            if (form.getTaxCode() != null && form.getTaxCode().trim().length() > 0) {
                hql += " and lower(c.taxCode) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getTaxCode().trim()));
            }
        }

        Query countQuery = getSession().createQuery("select count(*) " + hql);
        Query query = getSession().createQuery("select c " + hql + " order by c.paymentHistoryId desc");
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
    /*
     * 
     */
    /**
     * findPaymentHistory
     *
     * @return
     */
    public List findAllPaymentHistory() {
        if (lstFactory == null) {
            lstFactory = new HashMap();
        }

        if (lstFactory.containsKey("type")) {
            return lstFactory.get("type");
        }

        List<PaymentHistory> lstPaymentHistory = null;
//        List<CategorySearchForm> lstResult = new ArrayList();
        try {
            StringBuilder stringBuilder = new StringBuilder(" from PaymentHistory a ");
            stringBuilder.append("  where a.isActive = 1 "
                    + " order by nlssort(lower(ltrim(a.categoryName)),'nls_sort = Vietnamese') ");
            Query query = getSession().createQuery(stringBuilder.toString());
            lstPaymentHistory = query.list();

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ArrayList<PaymentHistory>();
        }
//        lstCategoryFactory.put(type, lstResult);
        lstFactory.put("type", lstPaymentHistory);

        return lstPaymentHistory;
    }

    //kiem tra doi tuong co trung khong   
    public boolean isDuplicate(PaymentHistoryForm form) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(c) from PaymentHistory c where c.status = 1 ";
        if (form.getPaymentHistoryId() != null && form.getPaymentHistoryId() > 0l) {
            hql += " and c.paymentHistoryId <> ? ";
            lstParam.add(form.getPaymentHistoryId());
        }
        if (form.getPaymentNo() != null && form.getPaymentNo().trim().length() > 0) {
            hql += " and lower(c.categoryName) = ?";
            lstParam.add(form.getPaymentNo().toLowerCase());
        }
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }

        Long count = Long.parseLong(query.uniqueResult().toString());
        boolean bReturn = false;
        if (count >= 1l) {
            bReturn = true;
        }
        return bReturn;
    }

    public PaymentHistoryForm boToForm(PaymentHistory bo) {
        PaymentHistoryForm frm = new PaymentHistoryForm(bo);
        return frm;
    }

    /**
     * getLstPaymentHistory
     *
     * @param objectId
     * @param objectType
     * @param start
     * @param nRecord
     * @param sortField
     * @return
     */
    public GridResult getLstPaymentHistory(String objectId, Long objectType, int start, int nRecord, String sortField) {
        List<PaymentHistoryForm> list = new ArrayList<>();
        List lstParam = new ArrayList();
        try {
            String countHql = "SELECT count(ph) ";
            String selectHQL = "SELECT ph";
            String hql = " FROM PaymentHistory ph WHERE (ph.status = 1) and (ph.fileCode = ?)";
            lstParam.add(objectId);
            hql += " ORDER BY ph.paymentDate ";

            Query query = getSession().createQuery(selectHQL + hql);
            Query countQuery = getSession().createQuery(countHql + hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }
            Long nCount = (Long) countQuery.uniqueResult();

            query.setFirstResult(start);
            query.setMaxResults(nRecord);
            List<PaymentHistory> lst = query.list();
            for (PaymentHistory bo : lst) {
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
     * getTotalPaymentHistory
     *
     * @param objectId
     * @param objectType
     * @return
     */
    public Long getTotalPaymentHistory(String objectId, Long objectType) {
        Long result = 0L;
        try {
            String selectHQL = "SELECT ph";
            String hql = " FROM PaymentHistory ph WHERE (ph.status = 1) and (ph.fileCode = ?)";
            hql += " ORDER BY ph.paymentDate ";
            Query query = getSession().createQuery(selectHQL + hql);
            query.setParameter(0, objectId);
            List<PaymentHistory> lst = query.list();
            for (PaymentHistory bo : lst) {
                result += bo.getIncome();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }
}
