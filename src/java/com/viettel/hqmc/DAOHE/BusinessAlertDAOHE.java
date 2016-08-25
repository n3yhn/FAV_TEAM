/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

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
}
