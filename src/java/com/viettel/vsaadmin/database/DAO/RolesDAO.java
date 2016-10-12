/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.vsaadmin.database.DAO;

//import com.viettel.common.util.Constants;
import com.viettel.common.util.LogUtil;
import com.viettel.dojoTag.DojoJSON;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.vsaadmin.VSAConst;
import com.viettel.vsaadmin.client.form.GridObjectForm;
import com.viettel.vsaadmin.client.form.ObjectsForm;
import com.viettel.vsaadmin.client.form.RolesForm;
import com.viettel.vsaadmin.common.util.StringUtils;
import com.viettel.vsaadmin.database.BO.Applications;
import com.viettel.vsaadmin.database.BO.Objects;
import com.viettel.vsaadmin.database.BO.RoleObject;
import com.viettel.vsaadmin.database.BO.RoleObjectPK;
import com.viettel.vsaadmin.database.BO.Roles;
import com.viettel.vsaadmin.database.BO.TreeNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import viettel.passport.client.UserToken;

/**
 *
 * @author HaVM
 */
public class RolesDAO extends BaseDAO {

    //<editor-fold desc="PROPERTIES">
    public DojoJSON jsonDataTree = new DojoJSON();
    private List childrenData = new ArrayList();
    private RolesForm rolesForm = new RolesForm();
    private List<RolesForm> roleFormSelection;
    private ObjectsForm objectsForm;
    private List<GridObjectForm> objectFormSelection;
    //</editor-fold>

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(RolesDAO.class);

    //<editor-fold desc="GETTERS AND SETTERS">
    public List getChildrenData() {
        return childrenData;
    }

    public void setChildrenData(List childrenData) {
        this.childrenData = childrenData;
    }

    public ObjectsForm getObjectsForm() {
        return objectsForm;
    }

    public void setObjectsForm(ObjectsForm objectsForm) {
        this.objectsForm = objectsForm;
    }

    public DojoJSON getJsonDataTree() {
        return jsonDataTree;
    }

    public void setJsonDataTree(DojoJSON jsonDataTree) {
        this.jsonDataTree = jsonDataTree;
    }

    public RolesForm getRolesForm() {
        return rolesForm;
    }

    public void setRolesForm(RolesForm rolesForm) {
        this.rolesForm = rolesForm;
    }

    public List<RolesForm> getRoleFormSelection() {
        return roleFormSelection;
    }

    public void setRoleFormSelection(List<RolesForm> roleFormSelection) {
        this.roleFormSelection = roleFormSelection;
    }

    public List<GridObjectForm> getObjectFormSelection() {
        return objectFormSelection;
    }

    public void setObjectFormSelection(List<GridObjectForm> objectFormSelection) {
        this.objectFormSelection = objectFormSelection;
    }

    //</editor-fold>
    /**
     * prepare goto searhc
     *
     * @return
     */
    public String prepare() {
        //UserToken vsaUserToken = (UserToken) getRequest().getSession().getAttribute("vsaUserToken");
        //getRequest().setAttribute("sys", Long.valueOf(vsaUserToken.getUserRight()));
        return "prepare";
    }

    /**
     *
     * @return
     */
    public String onInit() {
        return onSearch("", "");
//        return "gridData";
    }

    /**
     * LAY DU LIEU CHO CAY UNG DUNG
     */
    public String onInitFunction() {
        return "gridData";
    }

    /**
     * Tim kiem cac chuc nang de add vao vai tro
     *
     * @return
     */
    public String onSearchFunctionToAdd() {
        try {
            getGridInfo();

            String parentItemId = getRequest().getParameter("parentId");
            String strRoleId = getRequest().getParameter("roleId");
            Long roleId = Long.parseLong(strRoleId);
            Long parentId;

            String[] strItems = parentItemId.split(":");
            if (strItems == null || strItems.length < 2) {
                throw new Exception("request error");
            } else {
                parentId = Long.parseLong(strItems[0]);
            }

            List params = new ArrayList();

            String sqlCommand;
            String countHQL = "select count(o) ";
            String searchHQL = "select o ";
            if ("app".equals(strItems[1])) {
                // 11/11/2014 viethd
                //sqlCommand = "from Objects o where o.status = 1 and o.parentId = null and o.appId = " + parentId;
                sqlCommand = "from Objects o where o.status = 1 and o.parentId = null and o.appId = ? ";
                params.add(parentId);
            } else if ("func".equals(strItems[1])) {
                // 11/11/2014
                //sqlCommand = "from Objects o where o.status = 1 and o.parentId = " + parentId;
                sqlCommand = "from Objects o where o.status = 1 and o.parentId = ?";
                params.add(parentId);
            } else {
                throw new Exception("request error");
            }

            // 11/11/2014 viethd
            //String command = " and o.objectId not in (select ro.id.objectId  from RoleObject ro where ro.id.roleId = " + roleId + ")";
            String command = " and o.objectId not in (select ro.id.objectId  from RoleObject ro where ro.id.roleId = ? )";
            params.add(roleId);

            sqlCommand = sqlCommand + command;
            countHQL = countHQL + sqlCommand;
            searchHQL = searchHQL + sqlCommand;

            Query searchQuery = getSession().createQuery(searchHQL);
            Query countQuery = getSession().createQuery(countHQL);
            int paramSize = params.size();
            for (int i = 0; i < paramSize; i++) {
                searchQuery.setParameter(i, params.get(i));
                countQuery.setParameter(i, params.get(i));
            }
            Long total = (Long) countQuery.uniqueResult();
            searchQuery.setFirstResult(start);
            searchQuery.setMaxResults(count);
            List<Objects> lst = searchQuery.list();
            if (lst != null) {
                for (int i = 0; i < lst.size(); i++) {
                    lst.get(i).setApp(null);
                }
            }
            this.jsonDataGrid.setItems(lst);
            this.jsonDataGrid.setTotalRows(total.intValue());

            //140123 binhnt53 update
//            getGridInfo();
//            String parentItemId = getRequest().getParameter("parentId");
//            String roleId = getRequest().getParameter("roleId");
//            Long parentId;
//
//            String[] strItems = parentItemId.split(":");
//            if (strItems == null || strItems.length < 2) {
//                throw new Exception("request error");
//            } else {
//                parentId = Long.parseLong(strItems[0]);
//            }
//            List lstParam = new ArrayList();
//            String sqlCommand = "";
//            String countHQL = "select count(o) ";
//            String searchHQL = "select o ";
//            if ("app".equals(strItems[1])) {
//                sqlCommand = "from Objects o where o.status = 1 and o.parentId = null and o.appId = ? ";
//                lstParam.add(parentId);
//            } else if ("func".equals(strItems[1])) {
//                sqlCommand = "from Objects o where o.status = 1 and o.parentId = ? ";
//                lstParam.add(parentId);
//            } else {
//                throw new Exception("request error");
//            }
//
//            String command = " and o.objectId not in (select ro.id.objectId  from RoleObject ro where ro.id.roleId = ? )";
//            lstParam.add(roleId);
//
//            sqlCommand = sqlCommand + command;
//            countHQL = countHQL + sqlCommand;
//            searchHQL = searchHQL + sqlCommand;
//
//            Query searchQuery = getSession().createQuery(searchHQL);
//            Query countQuery = getSession().createQuery(countHQL);
//            for (int i = 0; i < lstParam.size(); i++) {
//                searchQuery.setParameter(i, lstParam.get(i));
//                countQuery.setParameter(i, lstParam.get(i));
//            }
//            Long total = (Long) countQuery.uniqueResult();
//            searchQuery.setFirstResult(start);
//            searchQuery.setMaxResults(count);
//            List<Objects> lst = searchQuery.list();
//            if (lst != null) {
//                for (int i = 0; i < lst.size(); i++) {
//                    lst.get(i).setApp(null);
//                }
//            }
//            this.jsonDataGrid.setItems(lst);
//            this.jsonDataGrid.setTotalRows(total.intValue());
            //!binhnt53
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "gridData";
    }

    /**
     * Them vai tro vao chuc nang
     *
     * @return
     */
    public String onInsertFunction() {
        String userName = getLoginUser();
        Session session;
        try {
            session = getSession();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return "error";
        }
        try {
            if (this.objectFormSelection != null) {
                String[] roles = this.rolesForm.getRoleIdArr().split(",");
                Long[] rId = new Long[roles.length];
                for (int i = 0; i < roles.length; i++) {
                    rId[i] = Long.valueOf(roles[i]);
                }
                Iterator itObjectForm = objectFormSelection.iterator();
                while (itObjectForm.hasNext()) {
                    GridObjectForm objectForm = (GridObjectForm) itObjectForm.next();
                    if (objectForm.getObjectId() != null) {
                        String appCode = objectForm.getAppCode();
                        String appName = objectForm.getAppName();

                        String parentItemId = this.rolesForm.getParentIdStr();
                        String[] parentId = parentItemId.split(":");

                        Criteria cri = getSession().createCriteria(Roles.class).add(Restrictions.in("roleId", rId));
                        List lst = cri.list();

                        if ("func".equals(parentId[1])) {
                            for (int j = 0; j < lst.size(); j++) {
                                Roles ro = (Roles) lst.get(j);
                                insertFunctionToRole(ro, Long.valueOf(Long.parseLong(parentId[0])), session, userName, appCode, appName);
                            }
                        }
                        for (int j = 0; j < lst.size(); j++) {
                            Roles ro = (Roles) lst.get(j);
                            RoleObject rbo = new RoleObject();
                            RoleObjectPK pk = new RoleObjectPK(Long.valueOf(Long.parseLong((String) objectForm.getObjectId())), ro.getRoleId());
                            rbo.setRoleObjectPK(pk);
                            rbo.setIsActive(Long.valueOf(1L));
                            session.save(rbo);
                        }
                    }
                }
                session.flush();
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return onSearchFunction();
    }

    /**
     * Them moi vai tro vao chuc nang
     *
     * @param ro
     * @param objId
     * @param session
     * @param userName
     * @param appCode
     * @param appName
     */
    private void insertFunctionToRole(Roles ro, Long objId, Session session, String userName, String appCode, String appName) {
        Criteria cri = session.createCriteria(Objects.class).add(Restrictions.eq("objectId", objId));
        List lst = cri.list();
        try {
            if (lst.size() > 0) {
                Objects obo = (Objects) lst.get(0);
                RoleObject rbo = new RoleObject();
                rbo.setObjectId(objId);
                RoleObjectPK pk = new RoleObjectPK(objId, ro.getRoleId());
                rbo.setRoleObjectPK(pk);
                rbo.setIsActive(Long.valueOf(1L));
                session.saveOrUpdate(rbo);
//       LogUtil.addFunctionToRole(session, getRequest(), userName, ro.getCode(), ro.getRoleName(), obo.getObjectName(), appCode, appName, "");

                if ((obo.getParentId() != null) && (obo.getParentId().longValue() > -1L)) {
                    insertFunctionToRole(ro, obo.getParentId(), session, userName, appCode, appName);
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
    }

    /**
     * Load cay chuc nang
     *
     * @return
     */
    public String getData() {
        try {
            Session session = getSession();
            Criteria cri = session.createCriteria(Applications.class);
            cri.add(Restrictions.eq("status", Long.valueOf(1L)));
            List lst = cri.list();
            List tmp = new ArrayList();
            for (int i = 0; i < lst.size(); i++) {
                Applications lbo = (Applications) lst.get(i);
                TreeNode node = new TreeNode();
                node.setLevel("0");
                node.setId(lbo.getAppId() + ":app");
                node.setName(lbo.getAppName());
                node.setMayHaveChildren("true");
                tmp.add(node);
            }
            jsonDataTree.setLabel("name");
            jsonDataTree.setItems(tmp);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "jsonDataTree";
    }

    /**
     * Tim cac chuc nang con theo ung dung
     *
     * @return
     */
    public String getChildrenDataByNode() {
        String parentItemId = getRequest().getParameter("parentItemId");
        String[] parentId = parentItemId.split(":");
        try {
            Session session = getSession();
            if ("app".equals(parentId[1])) {
                Criteria cri = session.createCriteria(Objects.class);
                cri.add(Restrictions.eq("appId", Long.valueOf(Long.parseLong(parentId[0]))));
                cri.add(Restrictions.isNull("parentId"));
                cri.add(Restrictions.eq("status", Long.valueOf(1L)));
                List lst = cri.list();
                for (int i = 0; i < lst.size(); i++) {
                    Objects lbo = (Objects) lst.get(i);
                    TreeNode node = new TreeNode();
                    node.setLevel("1");
                    node.setId(lbo.getObjectId() + ":func");
                    node.setName(lbo.getObjectName());
                    node.setMayHaveChildren("true");
                    childrenData.add(node);
                }
            } else if ("func".equals(parentId[1])) {
                Criteria cri = session.createCriteria(Objects.class);
                cri.add(Restrictions.eq("parentId", Long.valueOf(Long.parseLong(parentId[0]))));
                cri.add(Restrictions.eq("status", Long.valueOf(1L)));
                List lst = cri.list();
                for (int i = 0; i < lst.size(); i++) {
                    Objects lbo = (Objects) lst.get(i);
                    TreeNode node = new TreeNode();
                    node.setLevel("2");
                    node.setId(lbo.getObjectId() + ":func");
                    node.setName(lbo.getObjectName());
                    node.setMayHaveChildren("true");
                    childrenData.add(node);
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "childrenData";
    }

    // <editor-fold defaultstate="collapsed" desc="Tim kiem du lieu Role">
    /**
     * TIM KIEM ROLES
     */
    public String onSearch() {
        return onSearch("", "");
    }

    public String onSearch(String customInfo) {
        return onSearch(customInfo, "");
    }

    public String onSearch(String customInfo, String message) {
        try {

            getGridInfo();

            Session session = getSession();
            List listParam = new ArrayList();

            // Dieu kien cua [ROLES]
            Criteria cri = session.createCriteria(Roles.class);
            String countHQL = "select count(r) ";
            String searchHQL = "select r ";
            String hql = " from Roles r where (1 = 1) ";
            if (rolesForm.getCodeSearch() != null && rolesForm.getCodeSearch().length() > 0) {
                hql += " and lower(r.roleCode) like ? escape'!'";
                listParam.add("%" + convertToLikeString(rolesForm.getCodeSearch()) + "%");
            }
            if (rolesForm.getDescriptionSearch() != null && rolesForm.getDescriptionSearch().length() > 0) {
                hql += " and lower(r.description) like ? escape'!'";
                listParam.add("%" + convertToLikeString(rolesForm.getDescriptionSearch()) + "%");
            }
            if (rolesForm.getNameSearch() != null && rolesForm.getNameSearch().length() > 0) {
                hql += " and lower(r.roleName) like ? escape'!'";
                listParam.add("%" + convertToLikeString(rolesForm.getNameSearch()) + "%");
            }
            if (rolesForm.getStatusSearch() != null && rolesForm.getStatusSearch().length() == 1) {
                cri.add(Restrictions.like("status", new Long(rolesForm.getStatusSearch())));
                hql += " and r.status = ? ";
                listParam.add(Long.parseLong(rolesForm.getStatusSearch()));
            }

            hql += " order by nlssort(lower(r.roleName),'nls_sort = Vietnamese') ";

            Query searchQuery = getSession().createQuery(searchHQL + hql);
            Query countQuery = getSession().createQuery(countHQL + hql);
            for (int i = 0; i < listParam.size(); i++) {
                searchQuery.setParameter(i, listParam.get(i));
                countQuery.setParameter(i, listParam.get(i));
            }

            Long total = (Long) countQuery.uniqueResult();
            searchQuery.setFirstResult(start);
            searchQuery.setMaxResults(count);
            List lst = searchQuery.list();

            // Dieu kien cua [ROLE_USER]
            /*
             UserToken vsaUserToken = (UserToken) getRequest().getSession().getAttribute("vsaUserToken");
             if (vsaUserToken.getUserRight() != 1L) {
             Criteria subAdminCri = session.createCriteria(RoleUser.class);
             subAdminCri.add(Restrictions.eq("userId", vsaUserToken.getUserID()));
             subAdminCri.add(Restrictions.eq("isAdmin", Long.valueOf(1L)));
             List roleList = subAdminCri.list();
             if (roleList.size() > 0) {
             Long[] tmp = new Long[roleList.size()];
             for (int i = 0; i < roleList.size(); i++) {
             tmp[i] = Long.valueOf(((RoleUser) roleList.get(i)).getRoleId());
             }
             cri.add(Restrictions.in("roleId", tmp));
             }
             }
             */
            // KET QUA TIM KIEM
//            List lst = cri.list();
//            List arr = new ArrayList();
//            arr.add(customInfo);
//            arr.add(message);
            jsonDataGrid.setCustomInfo(customInfo);
            jsonDataGrid.setItems(lst);
            jsonDataGrid.setTotalRows(total.intValue());

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "gridData";
    }

    /**
     * tim kiem role theo name
     *
     * @param roleName
     * @return
     */
    public Long getRoleIdByName(String roleName) {
//        String hql = "select r.roleId from Roles r where r.roleName ='" + roleName + "'";//binhnt53 24.11.14 sql injection
        List lstParam = new ArrayList();//binhnt53 24.11.14 sql injection
        String hql = "select r.roleId from Roles r where r.roleName = ?";//binhnt53 24.11.14 sql injection
        lstParam.add(roleName);
        List lstResult = null;
        try {
            Query query = getSession().createQuery(hql);
            for (int i = 0; i < lstParam.size(); i++) {//binhnt53 24.11.14 sql injection
                query.setParameter(i, lstParam.get(i));
            }
            lstResult = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (lstResult == null || lstResult.size() == 0) {
            return 0L;
        } else {
            return (Long) lstResult.get(0);
        }
    }

    /**
     * Tim kiem role theo code
     *
     * @param roleCode
     * @return
     */
    public Long getRoleIdByCode(String roleCode) {
        List lstParam = new ArrayList();//binhnt53 24.11.14 sql injection
//        String hql = "select r.roleId from Roles r where r.roleCode ='" + roleCode + "'";//binhnt53 24.11.14 sql injection
        String hql = "select r.roleId from Roles r where r.roleCode =?";//binhnt53 24.11.14 sql injection
        lstParam.add(roleCode);//binhnt53 24.11.14 sql injection
        List lstResult = null;
        try {
            Query query = getSession().createQuery(hql);
            for (int i = 0; i < lstParam.size(); i++) {//binhnt53 24.11.14 sql injection
                query.setParameter(i, lstParam.get(i));
            }
            lstResult = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        if (lstResult == null || lstResult.size() == 0) {
            return 0L;
        } else {
            return (Long) lstResult.get(0);
        }
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="XU LY DU LIEU LIEN QUAN ROLE">
    /**
     * action xu ly them moi vai tro
     *
     * @return
     */
    public String onInsert() {
        String userName = getLoginUser();
        Session session;
        try {
            session = getSession();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return onSearch("error");
        }

        Roles temp;
        try {
            temp = createBO();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return onSearch("error");
        }

        // Kiem tra trung ma
        Criteria criteria = session.createCriteria(Roles.class);
        criteria.add(Restrictions.eq("roleCode", temp.getRoleCode()).ignoreCase());
        List lst = criteria.list();

        if (lst.size() > 0) {
            return onSearch("existCode");
        }

        Long roleId = getRoleIdByName(temp.getRoleName());
        if (roleId > 0) {
            return onSearch("existName");
        }

        // Them moi du lieu
        try {
            temp.setRoleId(getSequence("ROLE_SEQ"));
            session.save(temp);
            session.flush();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return onSearch("error");
        }

        // Log
        try {
            LogUtil.createRole(session, getRequest(), userName, temp.getRoleCode(), temp.getRoleName(), "");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
//        jsonDataGrid.setCustomInfo("addOk");
//        return "gridData";
        return onSearch("addOk");
    }

    /**
     * Tao mot entity vai tro
     *
     * @return
     * @throws Exception
     */
    private Roles createBO() throws Exception {
        Roles temp = new Roles();
        try {
            if (rolesForm.getRoleId() != null && !rolesForm.getRoleId().equals(0L)) {
                temp.setRoleId(rolesForm.getRoleId());
            }
            if ((rolesForm.getCode() != null) && (rolesForm.getCode().length() > 0)) {
//         temp.setCode(rolesForm.getCode());
                temp.setRoleCode(rolesForm.getCode());
            }
            temp.setDescription(rolesForm.getDescription());
            if ((rolesForm.getRoleName() != null) && (rolesForm.getRoleName().length() > 0)) {
                temp.setRoleName(rolesForm.getRoleName());
            }
            if (rolesForm.getStatus() != null) {
                temp.setStatus(new Long(rolesForm.getStatus().longValue()));
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            throw ex;
        }
        return temp;
    }

    /**
     * update vai tro
     *
     * @return
     */
    public String onUpdate() {
        Session session;
        try {
            session = getSession();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return onSearch("error");
        }
        Roles temp = null;
        try {
            temp = createBO();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            onSearch("error");
        }

        // Kiem tra trung ma
//        Criteria criteria = session.createCriteria(Roles.class);
//        criteria.add(Restrictions.eq("roleCode", temp.getRoleCode()).ignoreCase());
//        List lst = criteria.list();
//
//        if (lst.size() > 0) {
//            Roles role = (Roles) lst.get(0);
//            if (!role.getRoleId().equals(temp.getRoleId())) {
//                return onSearch("existCode");
//            }
//        }
//        lst.clear();
        Long roleId = getRoleIdByCode(temp.getRoleCode());
        if (roleId > 0 && !roleId.equals(temp.getRoleId())) {
            return onSearch("existCode");
        }

        roleId = getRoleIdByName(temp.getRoleName());
        if (roleId > 0 && !roleId.equals(temp.getRoleId())) {
            return onSearch("existName");
        }

        try {
            session.update(temp);
            session.flush();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            onSearch("error");
        }
        return onSearch("updateOk");
    }

    /**
     * Xoa vai tro
     *
     * @return
     */
    public String onDelete() {
//        String userName = getLoginUser();//sonar

        Session session;
        try {
            session = getSession();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return onSearch("deleteNotOk");
        }

        String deleteFails = "deleteOk";
        try {

            RolesForm rolesItem;
            Roles rolesBO;
            Long countNotDel = 0L;
            for (int i = 0; i < roleFormSelection.size(); i++) {
                rolesItem = roleFormSelection.get(i);
                if (rolesItem.getRoleId() != 0L) {
                    /*
                     // XOA ROLE_OBJECT
                     Criteria criteria = session.createCriteria(RoleObject.class);
                     criteria.add(Restrictions.eq("roleId", rolesForm.getRoleId()));
                     List tmpList = criteria.list();
                     Iterator itTmpList = tmpList.iterator();
                     while (itTmpList.hasNext()) {
                     RoleObject rbo = (RoleObject) itTmpList.next();
                     session.delete(rbo);
                     }

                     // XOA ROLE_USER
                     criteria = session.createCriteria(RoleUser.class);
                     criteria.add(Restrictions.eq("roleId", rolesForm.getRoleId()));
                     tmpList = criteria.list();
                     itTmpList = tmpList.iterator();
                     while (itTmpList.hasNext()) {
                     RoleUser rUo = (RoleUser) itTmpList.next();
                     session.delete(rUo);
                     }
                     */

                    // XOA ROLES
                    rolesBO = new Roles(rolesItem.getRoleId());
                    rolesBO = getRoles(session, rolesBO);
                    if (rolesBO != null) {
                        try {
                            session.delete(rolesBO);
                            session.flush();
                        } catch (Exception ex) {
                            LogUtil.addLog(ex);//binhnt sonar a160901
                            deleteFails = "deleteNotOk";
                            countNotDel++;
                        }
                    }
                }
            }

            if (countNotDel > 0L) {
                deleteFails = "Xóa không thành công " + countNotDel + " bản ghi do có ràng buộc";
            }
//            jsonDataGrid.setCustomInfo("deleteOk");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
//            jsonDataGrid.setCustomInfo("deleteNotOk");
            return onSearch("deleteNotOk");
        }

        if ("deleteOk".equals(deleteFails)) {
            return onSearch("deleteOk");
        } else {
            return onSearch(deleteFails);
        }
//        return "gridData";
    }

    /**
     * Khoa vai tro
     *
     * @return
     */
    public String onLock() {
        String userName = getLoginUser();
        Session dbSession;
        try {
            dbSession = getSession();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return onSearch("lockNotOk");
        }

        String bStrReturn = "lockOk";
        try {
            //
            // tim kiem tat ca cac role cua minh dang dang nhap
            //
            List<Long> lstRoleId = new ArrayList<Long>();
            try {
                UserToken vsaUserToken = (UserToken) getRequest().getSession().getAttribute("vsaUserToken");
                Long userId = vsaUserToken.getUserID();
                String hql = "select ru.roleUserPK.roleId from RoleUser ru where ru.roleUserPK.userId = :userId";
                Query roleIdquery = getSession().createQuery(hql);
                roleIdquery.setParameter("userId", userId);
                lstRoleId = roleIdquery.list();
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
                lstRoleId = new ArrayList<Long>();
            }
            //
            // tien danh update
            //
            RolesForm rolesItem;
            Roles rolesBO;
            for (int i = 0; i < roleFormSelection.size(); i++) {
                rolesItem = roleFormSelection.get(i);
                boolean bLockItem = true;
                for (int j = 0; j < lstRoleId.size(); j++) {
                    if (lstRoleId.get(i).equals(rolesItem.getRoleId())) {
                        bLockItem = false;
                        break;
                    }
                }
                if (bLockItem == false) {
                    bStrReturn = "notLockByUser";
                    continue;
                }
                if (rolesItem.getRoleId() != 0L) {
                    rolesBO = new Roles(rolesItem.getRoleId());

                    rolesBO = getRoles(dbSession, rolesBO);
                    if (rolesBO != null && rolesBO.getStatus().equals(VSAConst.ENABLE_STATUS)) {
                        rolesBO.setStatus(VSAConst.DISABLE_STATUS);
                        dbSession.saveOrUpdate(rolesBO);
                        LogUtil.lockRole(dbSession, getRequest(), userName, rolesBO.getRoleCode(), rolesBO.getRoleName(), "");
                    }
                }
            }
            dbSession.getTransaction().commit();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            bStrReturn = "lockNotOk";
        }

        return onSearch(bStrReturn);
    }

    /**
     * Mo khoa vai tro
     *
     * @return
     */
    public String onUnLock() {
        String userName = getLoginUser();
        Session session;
        try {
            session = getSession();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return "error";
        }

        try {
            RolesForm rolesForm;
            Roles rolesBO;
            for (int i = 0; i < roleFormSelection.size(); i++) {
                rolesForm = roleFormSelection.get(i);
                if (rolesForm.getRoleId() != 0L) {
                    rolesBO = new Roles(rolesForm.getRoleId());

                    rolesBO = getRoles(session, rolesBO);
                    if (rolesBO != null) {
                        if (!rolesBO.getStatus().equals(VSAConst.DISABLE_STATUS)) {
                            continue;
                        }
                        rolesBO.setStatus(VSAConst.ENABLE_STATUS);
                        session.saveOrUpdate(rolesBO);
                        LogUtil.unLockRole(session, getRequest(), userName, rolesBO.getRoleCode(), rolesBO.getRoleName(), "");
                    }
                }
            }
            session.flush();
//            session.getTransaction().commit();
            return onSearch("unLockOk");
//            jsonDataGrid.setCustomInfo("unLockOk");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return onSearch("unLockNotOk");
        }

//        return "gridData";
    }

    /**
     * Lay vai tro
     *
     * @param session
     * @param rolesBO
     * @return
     */
    private Roles getRoles(Session session, Roles rolesBO) {
        Criteria cri = session.createCriteria(Roles.class);
        cri.add(Restrictions.eq("roleId", rolesBO.getRoleId()));
        List<Roles> rolesBOs = cri.list();

        if (!rolesBOs.isEmpty()) {
            return rolesBOs.get(0);
        }
        return null;
    }
    // Ket thuc thao tac du lieu Role

    /**
     * lay thong tin dang nhap
     *
     * @return
     */
    private String getLoginUser() {
        HttpSession httpSession = getRequest().getSession();
        String userName = (String) httpSession.getAttribute("user");
        return userName;
    }

    /**
     * Tim kiem chuc nang
     *
     * @return
     */
    public String onSearchFunction() {

        getGridInfo();

        Session session;
        try {
            session = getSession();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return "error";
        }

        try {
            Long role = Long.parseLong(rolesForm.getRoleIdArr());

            String countHQL = "select count(ro.objectId) ";
            String searchHQL = " select new com.viettel.vsaadmin.client.form.ObjectsForm(ro.object.app.appId,ro.object.description, ro.object.objectId, ro.object.objectName, ro.object.objectTypeId, ro.object.objectUrl, ro.isActive, ro.object.objectCode, ro.object.app.appName, ro.object.app.appCode)";
            String sqlCommand = " from RoleObject ro"
                    + " where 1 = 1";

            List lstParam = new ArrayList();

            if (StringUtils.validString(rolesForm.getFunctionCodeSearch())) {
                sqlCommand = sqlCommand + " and lower(ro.object.objectCode) like ? escape'!'";
                lstParam.add("%" + convertToLikeString(rolesForm.getFunctionCodeSearch()) + "%");
            }

            if (rolesForm.getFunctionNameSearch() != null && rolesForm.getFunctionNameSearch().length() > 0) {
                sqlCommand = sqlCommand + " and lower(ro.object.objectName) like ? escape'!'";
                lstParam.add("%" + convertToLikeString(rolesForm.getFunctionNameSearch()) + "%");
            }

            if (rolesForm.getAppInfo() != null && rolesForm.getAppInfo().length() > 0) {
                sqlCommand = sqlCommand + " and (lower(ro.object.app.appCode) like ? escape'!'";
                sqlCommand = sqlCommand + " or lower(ro.object.app.appName) like ? escape'!'";
                sqlCommand = sqlCommand + ")";
                lstParam.add("%" + convertToLikeString(this.rolesForm.getAppInfo()) + "%");
                lstParam.add("%" + convertToLikeString(this.rolesForm.getAppInfo()) + "%");
            }

            String command = " and ro.roleObjectPK.roleId = ?";
            lstParam.add(role);
            if (this.rolesForm.getFunctionStatusSearch().length() == 1) {
                command = command + " and ro.isActive = ";
                command = command + this.rolesForm.getFunctionStatusSearch();
            }

            command = command + ")";

            sqlCommand = sqlCommand + command;

            countHQL = countHQL + sqlCommand;
            searchHQL = searchHQL + sqlCommand;

            Query countQuery = session.createQuery(countHQL);
            Query searchQuery = session.createQuery(searchHQL);

            for (int i = 0; i < lstParam.size(); i++) {
                countQuery.setParameter(i, lstParam.get(i));
                searchQuery.setParameter(i, lstParam.get(i));
            }

            Long total = (Long) countQuery.uniqueResult();

            searchQuery.setFirstResult(start);
            searchQuery.setMaxResults(count);
            List<ObjectsForm> lst = searchQuery.list();

            this.jsonDataGrid.setItems(lst);
            this.jsonDataGrid.setTotalRows(total.intValue());
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "gridData";
    }

    /**
     * action khoa chuc nang cua vai tro
     *
     * @return
     */
    public String onLockFunction() {
        Session session;
        try {
            session = getSession();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return "error";
        }

        String msg = "lockFunctionOk";
        try {
            if (this.objectFormSelection != null) {
                String roleId = this.rolesForm.getRoleIdArr();

                Iterator itObjectForm = objectFormSelection.iterator();
                String objectId = "";
                while (itObjectForm.hasNext()) {
                    if ("".equals(objectId)) {
                        objectId = objectId + ((GridObjectForm) itObjectForm.next()).getObjectId();
                    } else {
                        objectId = objectId + "," + ((GridObjectForm) itObjectForm.next()).getObjectId();
                    }
                }

                if (!"".equals(objectId)) {
                    objectId = "(" + objectId + ")";
                }

                String hbl = "update RoleObject ro Set ro.isActive = " + VSAConst.DISABLE_STATUS + " where ro.roleId =" + roleId + " and ro.objectId in " + objectId;
                Query query = getSession().createQuery(hbl);
                query.executeUpdate();
                session.getTransaction().commit();
//14.11.24-binhnt53 fix attt
                /*
                List lstParam = new ArrayList();//14.11.24-binhnt53 fix attt
                String hbl = "update RoleObject ro Set ro.isActive = ? where ro.roleId =? and ro.objectId in (:objectId)";
                lstParam.add(VSAConst.DISABLE_STATUS);
                lstParam.add(roleId);
                Query query = getSession().createQuery(hbl);
                 */
 /*
                for (int i = 0; i < lstParam.size(); i++) {//binhnt53 24.11.14 sql injection
                    query.setParameter(i, lstParam.get(i));
                }
//!14.11.24-binhnt53 fix attt
                 */
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            msg = "lockFunctionError";
        }
        this.jsonDataGrid.setCustomInfo(msg);
        return onSearchFunction();
    }

    /**
     * action mo khoa chuc nang cua vai tro
     *
     * @return
     */
    public String onUnLockFunction() {
        Session session;
        try {
            session = getSession();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return "error";
        }
        String msg = "unlockFunctionOk";
        try {
            if (this.objectFormSelection != null) {
                String roleId = this.rolesForm.getRoleIdArr();

                Iterator itObjectForm = objectFormSelection.iterator();
                String objectId = "";
                while (itObjectForm.hasNext()) {
                    if ("".equals(objectId)) {
                        objectId = objectId + ((GridObjectForm) itObjectForm.next()).getObjectId();
                    } else {
                        objectId = objectId + "," + ((GridObjectForm) itObjectForm.next()).getObjectId();
                    }
                }

                if (!"".equals(objectId)) {
                    objectId = "(" + objectId + ")";
                }

                String hbl = "update RoleObject ro Set ro.isActive = " + VSAConst.ENABLE_STATUS + " where ro.roleId =" + roleId + " and ro.objectId in " + objectId;
                Query query = getSession().createQuery(hbl);
                query.executeUpdate();
                session.getTransaction().commit();
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            msg = "unlockFunctionError";
        }
        this.jsonDataGrid.setCustomInfo(msg);
        return onSearchFunction();
    }

//    public String onUnLockFunction()
//   {
//     String userName = getLoginUser();
//     Session session;
//     try {
//       session = getSession();
//     } catch (Exception ex) {
//       System.out.println("Loi Tao ket noi CSDL");
//       ex.printStackTrace();
//       return "error";
//     }
//     try {
//       if (this.objectFormSelection != null) {
//         String[] roles = this.rolesForm.getRoleId().split(",");
//         Long[] rId = new Long[roles.length];
//         for (int i = 0; i < roles.length; i++) {
//           rId[i] = Long.valueOf(roles[i]);
//         }
//         if (this.objectFormSelection.getObjectId() != null) {
//           Criteria cri = getSession().createCriteria(Roles.class).add(Restrictions.in("roleId", rId));
//           List lst = cri.list();
//           for (int j = 0; j < lst.size(); j++) {
//             Roles ro = (Roles)lst.get(j);
//             for (int i = 0; i < this.objectFormSelection.getObjectId().size(); i++) {
//               RoleObjectBOId id = new RoleObjectBOId();
//               id.setObjectId(Long.valueOf(Long.parseLong((String)this.objectFormSelection.getObjectId().get(i))));
//               id.setRoleId(ro.getRoleId());
//
//               List tmpList = getSession().createCriteria(RoleObjectBO.class).add(Restrictions.eq("id", id)).list();
//
//               if (tmpList.size() > 0) {
//                 RoleObjectBO rbo = (RoleObjectBO)tmpList.get(0);
//                 if (rbo.getIsActive().equals(VSAConst.DISABLE_STATUS)) {
//                   rbo.setIsActive(VSAConst.ENABLE_STATUS);
//                   session.saveOrUpdate(rbo);
//                   LogUtil.unLockFunctionInRole(session, getRequest(), userName, ro.getCode(), ro.getRoleName(), (String)this.objectFormSelection.getObjectName().get(i), (String)this.objectFormSelection.getAppCode().get(i), (String)this.objectFormSelection.getAppName().get(i), "");
//                 }
//               }
//             }
//           }
//         }
//       }
//     } catch (Exception ex) {
//       ex.printStackTrace();
//     }
//     return onSearchFunction();
//   }
    /**
     * Action xu ly xoa chuc nang cua role
     *
     * @return
     */
    public String onDeleteFunction() {
        Session session;
        try {
            session = getSession();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return "error";
        }
        String msg = "deleteOk";
        try {
            Long roleId = Long.parseLong(getRequest().getParameter("roleId"));

            if (this.objectFormSelection != null) {
                String objectId = "";
                List<Long> lstObjectId = new ArrayList<Long>();
                String hql = "delete from RoleObject r where r.roleObjectPK.roleId = :roleId";
                Iterator itObjectForm = objectFormSelection.iterator();
                while (itObjectForm.hasNext()) {
                    GridObjectForm objectForm = (GridObjectForm) itObjectForm.next();
                    if (objectForm.getObjectId() != null) {
                        if ("".equals(objectId)) {
                            objectId = objectForm.getObjectId();
                            Long l = Long.parseLong(objectForm.getObjectId());
                            lstObjectId.add(l);
                        } else {
                            objectId = objectId + "," + objectForm.getObjectId();
                            Long l = Long.parseLong(objectForm.getObjectId());
                            lstObjectId.add(l);
                        }
                    }
                }

                if (!"".equals(objectId)) {
//                    objectId = "(" + objectId + ")";//sonar
                    hql += " and r.roleObjectPK.objectId in (:objectId)";
                    Query query = getSession().createQuery(hql);
                    query.setParameter("roleId", roleId);
                    query.setParameterList("objectId", lstObjectId);
                    query.executeUpdate();
                    session.getTransaction().commit();
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            msg = "deleteNotOk";
        }
        this.jsonDataGrid.setCustomInfo(msg);
        return onSearchFunction();
    }
    //</editor-fold>
}
