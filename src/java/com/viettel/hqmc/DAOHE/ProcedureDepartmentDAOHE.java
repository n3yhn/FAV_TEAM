/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.hqmc.BO.ProcedureDepartment;
import com.viettel.voffice.database.BO.Category;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.List;
import org.hibernate.Query;
import com.viettel.common.util.LogUtil;

/**
 *
 * @author Administrator
 */
public class ProcedureDepartmentDAOHE extends GenericDAOHibernate<ProcedureDepartment, Long> {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ProcedureDepartmentDAOHE.class);

    public ProcedureDepartmentDAOHE() {
        super(ProcedureDepartment.class);
    }

    public List getAllProcedureDepartment() {
        List<Category> lstCategory = null;
        try {
            StringBuilder stringBuilder = new StringBuilder(" from ProcedureDepartment a ");
            stringBuilder.append("  where a.isActive = ?"
                    + " order by a.createDate ASC");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, "1");
            lstCategory = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return lstCategory;
    }

    public List getAllProcedureDepartmentByProcedureId(Long procedureId) {
        List<ProcedureDepartment> lstCategory = null;
        try {
            StringBuilder stringBuilder = new StringBuilder(" from ProcedureDepartment d");
            stringBuilder.append(" where d.procedureId =? and d.processDeptId is not null"
                    + " order by d.processDeptId ASC");
            Query query = getSession().createQuery(stringBuilder.toString());
//            query.setParameter(0, Constants.ACTIVE_STATUS.ACTIVE.toString());
            query.setParameter(0, procedureId);
            lstCategory = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return lstCategory;
    }
}
