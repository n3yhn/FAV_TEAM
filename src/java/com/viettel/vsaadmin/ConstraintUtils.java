package com.viettel.vsaadmin;

import com.viettel.common.util.LogUtil;
import com.viettel.database.DAO.BaseDAOMDBAction;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ConstraintUtils extends BaseDAOMDBAction {
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ConstraintUtils.class);

    public List getTableConstraintNames(String tableName) {
        try {
            Query query = getSession().createSQLQuery("SELECT b.table_name FROM user_constraints a INNER JOIN user_constraints b ON a.constraint_name = b.r_constraint_name WHERE a.table_name = ?");

            query.setParameter(0, tableName);
            List lst = query.list();
            return lst;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    public List getColumnNames(String tableName, String rTableName) {
        try {
            Query query = getSession().createSQLQuery("SELECT c.column_name FROM user_constraints a INNER JOIN user_constraints b ON a.constraint_name = b.r_constraint_name INNER JOIN user_cons_columns c ON b.constraint_name = c.constraint_name WHERE a.table_name = ? and b.table_name = ? ");

            query.setParameter(0, tableName);
            query.setParameter(1, rTableName);
            List lst = query.list();
            return lst;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    public Object getCount(String tableName, String columnName, Long value) {
        try {
            StringBuffer strBuffer = new StringBuffer(" select count(*) from ");
            strBuffer.append(tableName + " where ");
            strBuffer.append(columnName + " = ?");
            Query query = getSession().createSQLQuery(strBuffer.toString());
            query.setParameter(0, value);
            return query.uniqueResult();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    public List getTableConstraintNames(String sessionName, String tableName) {
        try {
            Query query = getSession(sessionName).createSQLQuery("SELECT b.table_name FROM user_constraints a INNER JOIN user_constraints b ON a.constraint_name = b.r_constraint_name WHERE a.table_name = ?");

            query.setParameter(0, tableName);
            List lst = query.list();
            return lst;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    public List getColumnNames(String sessionName, String tableName, String rTableName) {
        try {
            Query query = getSession(sessionName).createSQLQuery("SELECT c.column_name FROM user_constraints a INNER JOIN user_constraints b ON a.constraint_name = b.r_constraint_name INNER JOIN user_cons_columns c ON b.constraint_name = c.constraint_name WHERE a.table_name = ? and b.table_name = ? ");

            query.setParameter(0, tableName);
            query.setParameter(1, rTableName);
            List lst = query.list();
            return lst;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    public Object getCount(String sessionName, String tableName, String columnName, Long value) {
        try {
            StringBuffer strBuffer = new StringBuffer(" select count(*) from ");
            strBuffer.append(tableName + " where ");
            strBuffer.append(columnName + " = ?");
            Query query = getSession(sessionName).createSQLQuery(strBuffer.toString());
            query.setParameter(0, value);
            return query.uniqueResult();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }

    public boolean checkExistConstraint(String tableName, Long value) {
        boolean check = false;
        List lstTableName = getTableConstraintNames(tableName);
        if (lstTableName != null && lstTableName.size() > 0) {
            for (int i = 0; i < lstTableName.size(); i++) {
                List lstColumnName = getColumnNames(tableName, (String) lstTableName.get(i));
                for (int j = 0; j < lstColumnName.size(); j++) {
                    int count = new Integer(getCount(lstTableName.get(i).toString(), lstColumnName.get(j).toString(), value).toString()).intValue();
                    if (count > 0) {
                        check = true;
                        break;
                    }
                }
                if (check) {
                    break;
                }
            }
        }
        return check;
    }

    public boolean checkExistConstraint(String sessionName, String tableName, Long value) {
        boolean check = false;
        Session session = getSession(sessionName);
        if (session == null) {
            check = true;
        } else {
            List lstTableName = getTableConstraintNames(sessionName, tableName);
            if (lstTableName != null && lstTableName.size() > 0) {
                for (int i = 0; i < lstTableName.size(); i++) {
                    List lstColumnName = getColumnNames(sessionName, tableName, (String) lstTableName.get(i));
                    if (lstColumnName != null && lstColumnName.size() > 0) {
                        for (int j = 0; j < lstColumnName.size(); j++) {
                            int count = new Integer(getCount(sessionName, lstTableName.get(i).toString(), lstColumnName.get(j).toString(), value).toString()).intValue();
                            if (count > 0) {
                                check = true;
                                break;
                            }
                        }
                    }
                    if (check) {
                        break;
                    }
                }
            }
        }
        return check;
    }

    public boolean checkExistConstraintCategory(String tableName, Long value) {
        boolean check = false;
        List lstTableName = getTableConstraintNames(tableName);
        if (lstTableName != null && lstTableName.size() > 0) {
            for (int i = 0; i < lstTableName.size(); i++) {
                List lstColumnName = getColumnNames(tableName, (String) lstTableName.get(i));
                if (lstColumnName != null && lstColumnName.size() > 0) {
                    for (int j = 0; j < lstColumnName.size(); j++) {
                        int count = new Integer(getCountCategory(lstTableName.get(i).toString(), lstColumnName.get(j).toString(), value).toString()).intValue();
                        if (count > 0) {
                            check = true;
                            break;
                        }
                    }
                }
                if (check) {
                    break;
                }
            }
        }
        return check;
    }

    public boolean checkExistConstraintCategory(String tableName, Long value, String... ignoreTables) {
        boolean check = false;
        List lstTableName = getTableConstraintNames(tableName);
        if (ignoreTables != null && ignoreTables.length > 0) {
            for (String ignoreTable : ignoreTables) {
                lstTableName.remove(ignoreTable);
            }
        }

        if (lstTableName != null && lstTableName.size() > 0) {
            for (int i = 0; i < lstTableName.size(); i++) {
                List lstColumnName = getColumnNames(tableName, (String) lstTableName.get(i));
                if (lstColumnName != null && lstColumnName.size() > 0) {
                    for (int j = 0; j < lstColumnName.size(); j++) {
                        int count = new Integer(getCountCategory(lstTableName.get(i).toString(), lstColumnName.get(j).toString(), value).toString()).intValue();
                        if (count > 0) {
                            check = true;
                            break;
                        }
                    }
                }
                if (check) {
                    break;
                }
            }
        }
        return check;
    }

    /**
     * kiem tra co truong is active trong bang ko
     * @param tableName
     * @return
     */
    public boolean checkExistIsActiveColumn(String tableName) {
        boolean check = false;
        try {
            StringBuffer strBuffer = new StringBuffer("   SELECT COUNT(1) FROM ALL_TAB_COLUMNS  a WHERE  TABLE_NAME = ?");
            strBuffer.append(" and a.column_name ='IS_ACTIVE' ");
            Query query = getSession().createSQLQuery(strBuffer.toString());
            query.setParameter(0, tableName);
            int count = new Integer(query.uniqueResult().toString());
            if (count > 0) {
                check = true;
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return check;
    }

    /**
     * check cho danh muc
     * @param tableName
     * @param columnName
     * @param value
     * @return
     */
    public Object getCountCategory(String tableName, String columnName, Long value) {
        try {
            StringBuffer strBuffer = new StringBuffer(" select count(*) from ");
            strBuffer.append(tableName + " where ");
            strBuffer.append(columnName + " = ?");
            boolean existIsActiveColumn = checkExistIsActiveColumn(tableName);
            if (existIsActiveColumn) {
                strBuffer.append(" and is_active = 1 ");
            }
            Query query = getSession().createSQLQuery(strBuffer.toString());
            query.setParameter(0, value);
            return query.uniqueResult();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return null;
    }
}

/* Location:           C:\Program Files\Apache Software Foundation\TomcatVSA\webapps\vsaadminv3\WEB-INF\classes\
 * Qualified Name:     com.viettel.vsaadmin.ConstraintUtils
 * JD-Core Version:    0.6.0
 */
