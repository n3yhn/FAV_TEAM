/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAOHibernate;

import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.vsaadmin.database.BO.RoleUserDept;
import com.viettel.vsaadmin.database.DAOHibernate.DepartmentDAOHE;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author DiuLTT
 */
public class RoleUserDeptDAOHE extends GenericDAOHibernate<RoleUserDept, Long> {
    DepartmentDAOHE deptDaoHe = new DepartmentDAOHE();

    public RoleUserDeptDAOHE() {
        super(RoleUserDept.class);
    }

    public List<Long> getDeptIdLstOfLeader(Long userId, Long deptId) {
        List<Long> deptIdLstOfLeader = null;
        try {
            StringBuilder sql = new StringBuilder("SELECT ru.deptId from RoleUserDept ru  where 1=1 and ru.isActive = 1 and ru.userId = ? ");
            sql.append("and ru.roleId in (Select ro.roleId from Roles ro where ro.status = 1 and ro.roleCode like ? )");
            Query query = getSession().createQuery(sql.toString());
            query.setParameter(0, userId);
            query.setParameter(1, "%"+Constants.ROLES.LEAD_MONITOR_ROLE +"%");
//            deptIdLstOfLeader.add(deptId);
            deptIdLstOfLeader = query.list();
            if(deptIdLstOfLeader != null && !deptIdLstOfLeader.isEmpty()){
            }else {
                deptIdLstOfLeader = new ArrayList<Long>();
                deptIdLstOfLeader.add(deptId);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<Long>();
        }
        
        return deptIdLstOfLeader;
    }
}
