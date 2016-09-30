/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.common.util;

import com.viettel.database.DAO.BaseDAOMDBAction;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ebaymark
 */
public class HqlConstraint extends BaseDAOMDBAction {

    public boolean isDeleteEntry(Long id, String column, String table) {
        String hql = "select count(*) from " + table + " where isActive='1' and " + column + "=" + id;
        Long i = (Long) getSession().createQuery(hql).iterate().next();
        if (i > 0) {
            return false;
        }
        return true;
    }

    public boolean isDeleteEntry(String store, Long parameter) {
        boolean ok = false;
        try {
            String sql = "{call " + store + "}";
            CallableStatement cs;
            cs = getSession().connection().prepareCall(sql);
            //cs = getSession().connection().prepareCall("{call is_delete_scale_salary_group(?,?)}");
            cs.registerOutParameter("result", java.sql.Types.INTEGER);
            cs.registerOutParameter("temp", java.sql.Types.INTEGER);
            cs.setInt("id", parameter.intValue());
            cs.execute();
            ok = cs.getInt("result") > 0 ? false : true;
        } catch (SQLException ex) {
            LogUtil.addLog(ex);//binhnt a 160901
            return ok;
        }
        return ok;
    }
}
