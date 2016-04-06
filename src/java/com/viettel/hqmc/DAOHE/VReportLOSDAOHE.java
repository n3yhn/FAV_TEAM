/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.hqmc.BO.VReportLos;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author binhninh
 */
public class VReportLOSDAOHE  extends GenericDAOHibernate<VReportLos, Long> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(VReportLOSDAOHE.class);
//========================================================================

    public VReportLOSDAOHE() {
        super(VReportLos.class);
    }

    public List returnVReportLOSProcessByDepId(Long deptId) {
        String hql = "FROM VReportLos c WHERE c.deptId";
        List lstParam = new ArrayList();
        lstParam.add(deptId);
        Query query = getSession().createQuery("select c " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }
        List<VReportLos> lstResult = query.list();        
        return lstResult;
    }

    public VReportLos returnVReportLOSProcessByDeptId(Long deptId) {
        String hql = "FROM VReportLos c WHERE c.deptId =? and c.isActive=1";
        List lstParam = new ArrayList();
        lstParam.add(deptId);
        Query query = getSession().createQuery("select c " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }
        List<VReportLos> lstResult = query.list();
        if (lstResult.size() > 0) {
            return lstResult.get(0);
        }
        return null;
    }
    public GridResult findVReportLOSProcess(FilesForm searchForm, int start, int count, String sortField) {
        String hql = " from VReportLos t";
        List lstParam = new ArrayList();        
        Query countQuery = getSession().createQuery("select count(t.deptId) " + hql);
        Query query = getSession().createQuery("select t" + hql + " order by t.deptId desc");
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
