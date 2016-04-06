/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.hqmc.BO.CountNo;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author GPCP_BINHNT53
 */
public class CountNoDAOHE extends GenericDAOHibernate<CountNo, Long> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CountNoDAOHE.class);
//========================================================================

    public CountNoDAOHE() {
        super(CountNo.class);
    }

    public CountNo returnCountNoByCode(String code) {
        String hql = "FROM CountNo c WHERE c.deptCode =? and c.isActive=1";
        List lstParam = new ArrayList();
        lstParam.add(code);
        //Query countQuery = getSession().createQuery("select count(m) " + hql);
        Query query = getSession().createQuery("select c " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            //countQuery.setParameter(i, lstParam.get(i));
        }
        //query.setFirstResult(start);
        //query.setMaxResults(count);
        //int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List<CountNo> lstResult = query.list();
        if (lstResult.size() > 0) {
            return lstResult.get(0);
        }
        return null;
    }

    public CountNo returnCountNoByDeptId(Long deptId) {
        String hql = "FROM CountNo c WHERE c.deptId =? and c.isActive=1";
        List lstParam = new ArrayList();
        lstParam.add(deptId);
        //Query countQuery = getSession().createQuery("select count(m) " + hql);
        Query query = getSession().createQuery("select c " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            //countQuery.setParameter(i, lstParam.get(i));
        }
        //query.setFirstResult(start);
        //query.setMaxResults(count);
        //int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List<CountNo> lstResult = query.list();
        if (lstResult.size() > 0) {
            return lstResult.get(0);
        }
        return null;
    }
}
