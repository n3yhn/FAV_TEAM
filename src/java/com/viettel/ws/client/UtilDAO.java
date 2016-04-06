/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ws.client;

import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.vsaadmin.database.BO.Applications;
import com.viettel.vsaadmin.database.BO.Objects;
import com.viettel.vsaadmin.database.BO.Roles;
import com.viettel.vsaadmin.database.BO.Users;
import com.viettel.vsaadmin.database.DAOHibernate.UsersDAOHE;
import java.util.List;
import org.hibernate.Query;
import org.w3c.dom.Document;

/**
 *
 * @author KienDV4
 */
public class UtilDAO extends BaseDAO {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UtilDAO.class);
    public String validate(String userName, String domainCode) {
        String strResult = null;

        try {
            Document doc = JDBCUtil.createDocument();

            doc = getUserData(userName, doc);
            doc = getRolesData(userName, doc);
            doc = getListObjects(userName, domainCode, doc);

            strResult = JDBCUtil.serialize(doc);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            strResult = "no";
        }

        return strResult;
    }

    public String validate(String userName, Long deptId, String domainCode) {
        String strResult = null;

        try {
            Document doc = JDBCUtil.createDocument();

            doc = getUserData(userName, doc);
            doc = getRolesData(userName, deptId, doc);
            doc = getListObjects(userName, deptId, domainCode, doc);

            strResult = JDBCUtil.serialize(doc);
        } catch (Exception ex) {
            strResult = "no";
        }

        return strResult;
    }

    /**
     *
     * @param strUserName a user name
     * @param doc a Document instance
     * @return information of a user after add to exist document
     * @throws Exception If a error happen
     */
    public Document getUserData(String strUserName, Document doc) throws Exception {
        try {
            String strSQL = "SELECT u.* FROM users u WHERE u.user_name = ? AND u.status = 1 ";


            Query q = getSession().createSQLQuery(strSQL).addEntity(Users.class);
            q.setParameter(0, strUserName.toLowerCase());
            List rs = q.list();

            doc = JDBCUtil.add2Document(rs, doc, "UserData");
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return doc;
    }

    /**
     * @param strUserName a user name
     * @param doc a Document instance
     * @return roles list of a user after add to exist document
     * @throws Exception If a error happen
     */
    public Document getRolesData(String strUserName, Document doc) throws Exception {
        try {
            String strSQL = "select r.* from roles r, role_user ru, users u "
                    + "where is_active = 1 and is_admin = 0 and "
                    + "r.role_id = ru.role_id and ru.user_id = u.user_id "
                    + "and r.status = 1 and u.user_name = ?";

            Query q = getSession().createSQLQuery(strSQL).addEntity(Roles.class);
            q.setParameter(0, strUserName.toLowerCase());

            List rs = q.list();

            JDBCUtil.add2Document(rs, doc, "Roles");

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return doc;
    }

    public Document getRolesData(String strUserName, Long deptId, Document doc) throws Exception {
        try {
            UsersDAOHE udhe = new UsersDAOHE();
            Users user = udhe.findUserByName(strUserName);

            if (deptId.equals(user.getDeptId())) {
                String strSQL = "select r.* from roles r, role_user ru, users u "
                        + "where is_active = 1 and is_admin = 0 and "
                        + "r.role_id = ru.role_id and ru.user_id = u.user_id "
                        + "and r.status = 1 and u.user_name = ? and r.role_id not in (select rud.role_id from role_user_dept rud where rud.is_active=1 and rud.dept_id <> ? and rud.user_id = u.user_id)";

                Query q = getSession().createSQLQuery(strSQL).addEntity(Roles.class);
                q.setParameter(0, strUserName.toLowerCase());
                q.setParameter(1, deptId);

                List rs = q.list();
                JDBCUtil.add2Document(rs, doc, "Roles");
            } else {
                String strSQL = "select r.* from roles r, role_user_dept rud, users u "
                        + "where rud.is_active = 1 and "
                        + "r.role_id = rud.role_id and rud.user_id = u.user_id  and rud.dept_id=? "
                        + "and r.status = 1 and u.user_name = ?";

                Query q = getSession().createSQLQuery(strSQL).addEntity(Roles.class);
                q.setParameter(0, deptId);
                q.setParameter(1, strUserName.toLowerCase());

                List rs = q.list();
                JDBCUtil.add2Document(rs, doc, "Roles");
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return doc;
    }

    /**
     * *
     * @param strUserName a user name
     * @param appCode a application code
     * @param doc a Document instance
     * @return object list of a user after add to exist document
     * @throws Exception If a error happen
     */
    public Document getListObjects(String strUserName, String appCode, Document doc) throws Exception {

        try {

            Long appID = getAppID(appCode);

            String strSQL = "SELECT DISTINCT o.* "
                    + "from users u, role_user ru, role_object ro, objects o, roles r "
                    + "where u.user_id = ru.user_id "
                    + "and ru.role_id = r.role_id "
                    + "and ru.role_id = ro.role_id "
                    + "and ro.object_id = o.object_id "
                    + "and r.status = 1 "
                    + "and ru.is_active = 1 "
                    + "and ru.is_admin = 0 "
                    + "and ro.is_active = 1 "
                    + "and o.status = 1 "
                    + "and u.user_name = ? "
                    + "and o.app_id = ?";

            Query q = getSession().createSQLQuery(strSQL).addEntity(Objects.class);
            q.setParameter(0, strUserName.toLowerCase());
            q.setParameter(1, appID);

            List rs = q.list();

            JDBCUtil.add2Document(rs, doc, "ObjectAll");
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return doc;
    }

    public Document getListObjects(String strUserName, Long deptId, String appCode, Document doc) throws Exception {

        try {

            Long appID = getAppID(appCode);
            UsersDAOHE udhe = new UsersDAOHE();
            Users user = udhe.findUserByName(strUserName);

            if (deptId.equals(user.getDeptId())) {

                String strSQL = "SELECT DISTINCT o.* "
                        + "from users u, role_user ru, role_object ro, objects o, roles r "
                        + "where u.user_id = ru.user_id "
                        + "and ru.role_id = r.role_id "
                        + "and ru.role_id = ro.role_id "
                        + "and ro.object_id = o.object_id "
                        + "and r.status = 1 "
                        + "and ru.is_active = 1 "
                        + "and ru.is_admin = 0 "
                        + "and ro.is_active = 1 "
                        + "and o.status = 1 "
                        + "and u.user_name = ? "
                        + "and o.app_id = ? "
                        + "and r.role_id not in (select rud.role_id from role_user_dept rud where rud.is_active=1 and rud.user_id = u.user_id and rud.dept_id <> ?)";

                Query q = getSession().createSQLQuery(strSQL).addEntity(Objects.class);
                q.setParameter(0, strUserName.toLowerCase());
                q.setParameter(1, appID);
                q.setParameter(2, deptId);

                List rs = q.list();

                JDBCUtil.add2Document(rs, doc, "ObjectAll");
            } else {
                String strSQL = "SELECT DISTINCT o.* "
                        + "from users u, role_user_dept rud, role_object ro, objects o, roles r "
                        + "where u.user_id = rud.user_id "
                        + "and rud.role_id = r.role_id "
                        + "and rud.role_id = ro.role_id "
                        + "and ro.object_id = o.object_id "
                        + "and r.status = 1 "
                        + "and rud.is_active = 1 "
                        + "and ro.is_active = 1 "
                        + "and o.status = 1 "
                        + "and u.user_name = ? "
                        + "and o.app_id = ? "
                        + "and rud.dept_id=?";

                Query q = getSession().createSQLQuery(strSQL).addEntity(Objects.class);
                q.setParameter(0, strUserName.toLowerCase());
                q.setParameter(1, appID);
                q.setParameter(2, deptId);

                List rs = q.list();

                JDBCUtil.add2Document(rs, doc, "ObjectAll");
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return doc;
    }

    public Long getAppID(String appCode) throws Exception {
        Long iReturn = 0L;
        try {
            String strSQL = "select * from applications where lower(app_code)= ?";
            Query q = getSession().createSQLQuery(strSQL).addEntity(Applications.class);
            q.setParameter(0, appCode.toLowerCase());

            List<Applications> rs = q.list();
            if (!rs.isEmpty()) {
                if (rs.get(0).getStatus() == 1L) {
                    iReturn = rs.get(0).getAppId();
                }
            }
        } catch (Exception e) {
            log.error(e);
        }

        return iReturn;
    }
}
