/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.LogUtil;
import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.BusinessAlert;
import com.viettel.hqmc.FORM.BusinessAlertForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Administrator
 */
public class BusinessAlertDAOHE extends GenericDAOHibernate<BusinessAlert, Long> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(BusinessAlertDAOHE.class);

    public BusinessAlertDAOHE() {
        super(BusinessAlert.class);
    }

    public List<BusinessAlert> findByBusinessAlertId(Long id) {
        // Build sql
        String sqlBuilder = "SELECT b FROM BusinessAlert b"
                + " WHERE"
                + " b.isActive = 1"
                + " and b.seen = 0"
                + " and b.businessId = ?";

        Query query = session.createQuery(sqlBuilder);
        query.setParameter(0, id);
        List<BusinessAlert> lsObject = query.list();
        if (lsObject != null && lsObject.size() > 0) {
            return lsObject;
        }
        return null;
    }

    public List<BusinessAlert> getAllBusinessAlert() {
        String hql = " from BusinessAlert b"
                + " where b.isActive=1";
        Query query = getSession().createQuery("select b " + hql + " order by b.businessAlertId");
        List lstResult = query.list();
        return lstResult;
    }

    public GridResult findBusinessAlert(BusinessAlertForm form, int start, int count, String sortField) {
        String hql = " from BusinessAlert b where b.isActive <> -1";
        List lstParam = new ArrayList();
        if (form != null) {
            if (form.getContent() != null && form.getContent().trim().length() > 0) {
                hql += " and lower(b.content) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getContent().trim()));
            }
            if (form.getBusinessId() != null && form.getBusinessId() > 0) {
                hql += " and b.businessId = ? ";
                lstParam.add(form.getBusinessId());
            }
        }

        Query countQuery = getSession().createQuery("select count(b) " + hql);
        Query query = getSession().createQuery("select b " + hql + " order by b.businessId desc");
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

    public List<BusinessAlertForm> getLstCommentOfDocument(Long userId) {
        List<BusinessAlertForm> list = new ArrayList<BusinessAlertForm>();
        try {
            String countHql = "SELECT count(b) ";
            String selectHQL = "SELECT b";
            String hql = " FROM BusinessAlert b"
                    + " WHERE (b.seen = 0)"
                    + " and  b.businessId =" + userId;
            hql += " ORDER BY b.createdDate DESC";

            Query query = getSession().createQuery(selectHQL + hql);
//            Query countQuery = getSession().createQuery(countHql + hql);//sonar
//            Long nCount = (Long) countQuery.uniqueResult();
            List<BusinessAlert> lst = query.list();
            for (BusinessAlert bo : lst) {
                list.add(boToForm(bo));
            }
            return list;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            log.error(e);
            return null;
        }
    }

    public BusinessAlertForm boToForm(BusinessAlert bo) {
        BusinessAlertForm form = new BusinessAlertForm();
        form.setBusinessAlertId(bo.getBusinessAlertId());
        form.setBusinessId(bo.getBusinessId());
        form.setContent(bo.getContent());
        form.setCreatedById(bo.getCreatedById());
        form.setCreatedByName(bo.getCreatedByName());
        form.setCreatedDate(bo.getCreatedDate());
        form.setIsActive(bo.getIsActive());
        form.setSeen(bo.getSeen());
        return form;
    }

    public List<BusinessAlert> getAllBusinessAlertByUserId(Long userId) {
        List<BusinessAlert> list = new ArrayList<BusinessAlert>();
        try {
//            String countHql = "SELECT count(b) ";//sonar
            String selectHQL = "SELECT b";
            String hql = " FROM BusinessAlert b"
                    + " WHERE (b.seen = 0)"
                    + " and  b.businessId =" + userId;
            hql += " ORDER BY b.createdDate DESC";

            Query query = getSession().createQuery(selectHQL + hql);
            List<BusinessAlert> lst = query.list();
            for (BusinessAlert bo : lst) {
                list.add(bo);
            }
            return list;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }
}
