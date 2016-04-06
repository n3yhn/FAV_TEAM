package com.viettel.hqmc.DAOHE;

import com.viettel.hqmc.BO.VReportStaffAll;
import com.viettel.hqmc.BO.VReportStaffProcess;
import com.viettel.hqmc.FORM.FilesForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author GPCP_BINHNT53
 */
public class VReportStaffAllDAOHE  extends GenericDAOHibernate<VReportStaffAll, Long> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(VReportStaffProcessDAOHE.class);
//========================================================================

    public VReportStaffAllDAOHE() {
        super(VReportStaffAll.class);
    }

    public List returnVReportStaffProcessByDepId(Long deptId) {
        String hql = "FROM VReportStaffAll c WHERE c.deptId";
        List lstParam = new ArrayList();
        lstParam.add(deptId);
        Query query = getSession().createQuery("select c " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }
        List<VReportStaffProcess> lstResult = query.list();        
        return lstResult;
    }

    public VReportStaffProcess returnVReportStaffProcessByDeptId(Long deptId) {
        String hql = "FROM VReportStaffAll c WHERE c.deptId =? and c.isActive=1";
        List lstParam = new ArrayList();
        lstParam.add(deptId);
        Query query = getSession().createQuery("select c " + hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }
        List<VReportStaffProcess> lstResult = query.list();
        if (lstResult.size() > 0) {
            return lstResult.get(0);
        }
        return null;
    }
    public GridResult findVReportStaffAll(FilesForm searchForm, int start, int count, String sortField) {
        String hql = " from VReportStaffAll t";
        List lstParam = new ArrayList();        
        Query countQuery = getSession().createQuery("select count(t.deptId) " + hql);
        Query query = getSession().createQuery("select t" + hql + " order by t.deptId desc group by t.receiveUser, t.receiveUserId");
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
//          select u.dept_id, f.RECEIVE_USER_ID as STAFF_ID, u.FULL_NAME as STAFF_NAME,count(*) as TOTAL_PROCESS
//          from PROCESS f join users u on u.user_id = f.RECEIVE_USER_ID
//          where  f.PROCESS_STATUS = 3
//          group by f.RECEIVE_USER_ID,u.FULL_NAME, u.dept_id
    }
}
