/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.voffice.database.DAOHibernate;

import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.common.util.StringUtils;
import com.viettel.vsaadmin.database.BO.Roles;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author sytv
 */
public class RolesDAOHE extends GenericDAOHibernate<Roles, Long> {
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RolesDAOHE.class);

    public RolesDAOHE() {
        super(Roles.class);
    }

    //DIuLTT comment code
//    public List<Roles> findAllRoles1() {
//        try {
//            String sql = " from Roles v where 1=1 order by nlssort(lower(v.roleName),'nls_sort = Vietnamese')";
//            Query q = getSession().createQuery(sql);
//            //q.setParameter(0, Constants.Status.ACTIVE);
//            List<Roles> result = q.list();
//
//            Roles v = new Roles();
//            v.setRoleId(Constants.COMBOBOX_HEADER_VALUE);
//            v.setRoleName(Constants.COMBOBOX_HEADER_TEXT_SELECT);
//            List<Roles> lst = new ArrayList();
//            lst.add(v);
//            lst.addAll(result);
//
//            return lst;
//        } catch (Exception ex) {
//            String msg = ex.getMessage();
//            return null;
//        }
//    }
    //DiuLTT
    /**
     * Tim kiem tat ca Role
     *
     * @return List<Roles>
     */
    public List<Roles> findAllRoles() {
        try {
            StringBuilder strBuilder = new StringBuilder(" from Roles r ");
            strBuilder.append("  where r.status = ? order by nlssort(lower(r.roleName),'nls_sort = Vietnamese') ");
            Query q = getSession().createQuery(strBuilder.toString());
            q.setParameter(0, Constants.Status.ACTIVE);
            List<Roles> result = q.list();

            //escapeHTML
            if (result != null && result.size() > 0) {
                for (int i = 0; i < result.size(); i++) {
                    String roleName = result.get(i).getRoleName();
                    roleName = StringUtils.escapeHtml(roleName);
                    if (roleName.length() > 100) {
                        roleName = roleName.substring(0, 99);
                    }
                    result.get(i).setRoleName(roleName);
                }
            }
            Roles v = new Roles();
            v.setRoleId(Constants.COMBOBOX_HEADER_VALUE);
            v.setRoleName(Constants.COMBOBOX_HEADER_TEXT_SELECT);
            List<Roles> lst = new ArrayList();
            lst.add(v);
            lst.addAll(result);

            return lst;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return null;
        }
    }

    public String getNameById(Long rolesId) {
        Roles role = new Roles();
        try {
            StringBuilder strBuf = new StringBuilder(" from Roles a ");
            strBuf.append(" where  a.roleId=? ");

            Query query = getSession().createQuery(strBuf.toString());
            query.setParameter(0, rolesId);
            List<Roles> roles = query.list();
            if (roles != null && roles.size() > 0) {
                role = roles.get(0);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return role.getRoleName();
    }

    public Roles getRoleByCode(String code) {
        Roles role = new Roles();
        try {
            StringBuilder strBuf = new StringBuilder(" from Roles a ");
            strBuf.append(" where  a.roleCode=? ");

            Query query = getSession().createQuery(strBuf.toString());
            query.setParameter(0, code);
            List<Roles> roles = query.list();
            if (roles != null && roles.size() > 0) {
                role = roles.get(0);
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return role;
    }

//DiuLTT add - Khong can su dung nua
//    public Roles findById(Long roleId) {
//        Query query = getSession().createQuery("Select a from Roles a where a.status = ? and roleId=?");
//        query.setParameter(0, Constants.Status.ACTIVE);
//        query.setParameter(1, roleId);
//        Roles role = (Roles) query.uniqueResult();
//        return role;
//    }
}
