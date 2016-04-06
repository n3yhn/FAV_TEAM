/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.vsaadmin.database.DAO;

import com.viettel.common.util.StringUtils;
import com.viettel.dojoTag.DojoJSON;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.vsaadmin.client.form.ApplicationsForm;
import com.viettel.vsaadmin.client.form.GridApplicationForm;
import com.viettel.vsaadmin.database.BO.Applications;
import com.viettel.vsaadmin.database.BO.UnitTreeBO;
import com.viettel.vsaadmin.database.BO.UnitTreeNodeBO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import viettel.passport.client.UserToken;

/**
 *
 * @author GPDN_THANHHH1
 */
public class ApplicationsDAO extends BaseDAO {
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ApplicationsDAO.class);
    private static final String PREPARE = "prepare";
    private static final String SEARCH_PAGE = "applicationSearch";
    private ApplicationsForm applicationsForm;
    private GridApplicationForm gridApplicationForm = new GridApplicationForm();
    private List<ApplicationsForm> applicationFormOnGrid;
    public static final Long IS_ACTIVE_CONSTANT = Long.valueOf(1L);
    public static final Long IS_NOT_ACTIVE_CONSTANT = Long.valueOf(0L);

    public List<ApplicationsForm> getApplicationFormOnGrid() {
        return applicationFormOnGrid;
    }

    public void setApplicationFormOnGrid(List<ApplicationsForm> applicationFormOnGrid) {
        this.applicationFormOnGrid = applicationFormOnGrid;
    }

    public GridApplicationForm getGridApplicationForm() {
        return gridApplicationForm;
    }

    public void setGridApplicationForm(GridApplicationForm gridApplicationForm) {
        this.gridApplicationForm = gridApplicationForm;
    }

    public ApplicationsForm getApplicationsForm() {
        return applicationsForm;
    }

    public void setApplicationsForm(ApplicationsForm applicationsForm) {
        this.applicationsForm = applicationsForm;
    }

    public String prepare() {
        getRequest().setAttribute("par", 0);
        try {
            GridResult result = getListApp(this.applicationsForm);
            this.jsonDataGrid.setItems(result.getLstResult());
            this.jsonDataGrid.setTotalRows(result.getnCount().intValue());
            if (getRequest().getSession().getAttribute("applicationsForm") != null) {
                getRequest().getSession().removeAttribute("applicationsForm");
            }
            getRequest().getSession().setAttribute("applicationsForm", this.applicationsForm);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return PREPARE;
    }

    public String onInit() {
        try {
            this.jsonDataGrid.setItems(new ArrayList());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return "gridData";
    }

    public String onSearch() {
        try {
            GridResult result = getListApp(this.applicationsForm);
            this.jsonDataGrid.setItems(result.getLstResult());
            this.jsonDataGrid.setTotalRows(result.getnCount().intValue());

            if (getRequest().getSession().getAttribute("applicationsForm") != null) {
                getRequest().getSession().removeAttribute("applicationsForm");
            }
            getRequest().getSession().setAttribute("applicationsForm", this.applicationsForm);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return "gridData";
    }

    public String onLoadResult() {
        List lst = new ArrayList();
        try {
            if (getRequest().getSession().getAttribute("lstErrorTokens") != null) {
                lst = (List) getRequest().getSession().getAttribute("lstErrorTokens");
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        this.jsonDataGrid.setItems(lst);
        return "gridData";
    }

    public String onApply() {
        Session session = null;

        List customInfo = new ArrayList();

        UserToken vsaUserToken = (UserToken) getRequest().getSession().getAttribute("vsaUserToken");
        try {
            session = getSession();
            try {
                Applications temp = createBO();
                try {
                    if (temp.getAppId() == null) {
                        temp.setAppId(getSequence("APP_SEQ"));
                        session.save(temp);
                    } else {
                        session.update(temp);
                    }
                    session.getTransaction().commit();
                    customInfo.add("success");
                } catch (Exception ex) {
                    customInfo.add("error");
                    customInfo.add(ex.getMessage());
                }
                GridResult result = getListApp(null);
                this.jsonDataGrid.setItems(result.getLstResult());
                this.jsonDataGrid.setTotalRows(result.getnCount().intValue());
            } catch (Exception ex) {
//                this.log.error(ex.getMessage());
            }
        } catch (Exception ex) {
//            this.log.error(ex.getMessage());
        }
        this.jsonDataGrid.setCustomInfo(customInfo);
        return "gridData";
    }

    public GridResult getListApp(ApplicationsForm applicationsForm) {
        List lst = new ArrayList();
        GridResult result = null;
        try {
            Session session = getSession();
            Criteria cri = session.createCriteria(Applications.class);


            String countHQL = "select count(a) ";
            String searchHQL = "select a ";
            String query = " From Applications a Where 1 = 1 ";
            List lstParam = new ArrayList();

            if (applicationsForm != null && (applicationsForm.getAppCode() != null)
                    && (!"".equals(applicationsForm.getAppCode().trim()))) {
                query += " AND lower(a.appCode) like ? escape'!'";
                lstParam.add("%" + StringUtils.escapeSql(applicationsForm.getAppCode()) + "%");
            }

            if (applicationsForm != null && (applicationsForm.getAppName() != null)
                    && (!"".equals(applicationsForm.getAppName().trim()))) {
                query += " AND lower(a.appName) like ? escape'!'";
                lstParam.add("%" + StringUtils.escapeSql(applicationsForm.getAppName()) + "%");
            }

            if (applicationsForm != null && (applicationsForm.getDescription() != null)
                    && (!"".equals(applicationsForm.getDescription().trim()))) {
                query += " AND lower(a.description) like ? escape'!'";
                lstParam.add("%" + StringUtils.escapeSql(applicationsForm.getDescription()) + "%");
            }


            if (applicationsForm != null && (applicationsForm.getStatus() != null) && (!applicationsForm.getStatus().equals(Long.valueOf(10L)))) {
                query += " AND a.status=?";
                lstParam.add(applicationsForm.getStatus());
            }

            countHQL = countHQL + query;
            searchHQL = searchHQL + query;
            Query countQuery = session.createQuery(countHQL);
            Query searchQuery = session.createQuery(searchHQL);
            for (int i = 0; i < lstParam.size(); i++) {
                countQuery.setParameter(i, lstParam.get(i));
                searchQuery.setParameter(i, lstParam.get(i));
            }
            Long total = (Long) countQuery.uniqueResult();
            searchQuery.setFirstResult(start);
            searchQuery.setMaxResults(count);
            lst = searchQuery.list();
            result = new GridResult(total, lst);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }

        return result;
    }

    private Applications createBO() {
        Applications temp = new Applications();
        try {
            if (this.applicationsForm.getAppId() != null && this.applicationsForm.getAppId().longValue() != 0) {
                temp.setAppId(this.applicationsForm.getAppId());
            }
            temp.setAppCode(this.applicationsForm.getAppCode().trim());
            temp.setAppName(this.applicationsForm.getAppName().trim());
            temp.setDescription(this.applicationsForm.getDescription().trim());
            temp.setStatus(1L);

        } catch (Exception ex) {
        }
        return temp;
    }

    public String onDelete() throws Exception {
        List customInfo = new ArrayList();
        String result = "";

        UserToken vsaUserToken = (UserToken) getRequest().getSession().getAttribute("vsaUserToken");
        String currentUserName = "";
        if (vsaUserToken != null) {
            currentUserName = vsaUserToken.getUserName();
        }

        String appIds = "";
        ApplicationsForm appForm;
        for (int i = 0; i < this.applicationFormOnGrid.size(); i++) {
            appForm = this.applicationFormOnGrid.get(i);

            //Long appId = Long.valueOf(Long.parseLong((String) this.gridApplicationForm.getAppId().get(i)));
            appIds += appForm.getAppId().toString() + ",";
        }
        if (appIds.length() > 0) {
            appIds = appIds.substring(0, appIds.length() - 1);
            try {
                // 11/11/2014 viethd
                //Query query = getSession().createQuery("DELETE FROM RoleObject ro WHERE ro.objectId in (select objectId from Objects where appId in(" + appIds + "))");
                Query query = getSession().createQuery("DELETE FROM RoleObject ro "
                        + "WHERE ro.objectId in (select objectId from Objects where appId in(?))");
                query.setParameter(0, appIds);
                query.executeUpdate();

                // 11/11/2014 viethd
                //query = getSession().createQuery("DELETE FROM Objects obj where obj.appId in(" + appIds + ")");
                query = getSession().createQuery("DELETE FROM Objects obj where obj.appId in(?)");
                query.setParameter(0, appIds);
                query.executeUpdate();

                // 11/11/2014 viethd
                //query = getSession().createQuery("DELETE FROM Applications app where appId in(" + appIds + ")");
                query = getSession().createQuery("DELETE FROM Applications app where appId in(?)");
                query.setParameter(0, appIds);
                query.executeUpdate();
                getSession().getTransaction().commit();
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
        }
        customInfo.add("deleteSucc");
        customInfo.add(result);
        try {
            ApplicationsForm frm = (ApplicationsForm) getRequest().getSession().getAttribute("applicationsForm");

            GridResult gridResult = getListApp(frm);
            this.jsonDataGrid.setItems(gridResult.getLstResult());
            this.jsonDataGrid.setTotalRows(gridResult.getnCount().intValue());

        } catch (Exception ex) {
            throw ex;
//            this.log.error("not get the list[" + ex.getMessage() + "]");
        }
        this.jsonDataGrid.setCustomInfo("deleteSucc");
        return "gridData";
    }

    private void removeNode(Long nodeId) {
        try {
            Criteria cri = getSession().createCriteria(UnitTreeNodeBO.class).add(Restrictions.eq("parentId", nodeId));

            List lst = cri.list();

            if (lst.size() > 0) {
                for (int i = 0; i < lst.size(); i++) {
                    UnitTreeNodeBO utnbo = (UnitTreeNodeBO) lst.get(i);
                    removeNode(utnbo.getNodeId());
                }
            }
            cri = getSession().createCriteria(UnitTreeNodeBO.class).add(Restrictions.eq("nodeId", nodeId));
            lst = cri.list();
            if (lst.size() > 0) {
                UnitTreeNodeBO utnbo = (UnitTreeNodeBO) lst.get(0);

                if (utnbo.getParentId() == null) {
                    cri = getSession().createCriteria(UnitTreeBO.class).add(Restrictions.eq("rootDeptId", nodeId));
                    List lstU = cri.list();
                    for (int i = 0; i < lstU.size(); i++) {
                        UnitTreeBO utbo = (UnitTreeBO) lstU.get(i);
                        utbo.setRootDeptId(null);
                        getSession().save(utbo);
                    }
                }

                getSession().delete(utnbo);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public String onLock() {
        String lockComment = "";
        if (this.applicationsForm != null) {
            lockComment = this.applicationsForm.getLockDescription();
        }
        List lst = new ArrayList();
        List customInfo = new ArrayList();

        UserToken vsaUserToken = (UserToken) getRequest().getSession().getAttribute("vsaUserToken");
        String currentUserName = "";
        if (vsaUserToken != null) {
            currentUserName = vsaUserToken.getUserName();
        }

//     for (int i = 0; i < this.gridApplicationForm.getAppId().size(); i++) {
//       String appCode = (String)this.gridApplicationForm.getAppCode().get(i);
//       String appName = (String)this.gridApplicationForm.getAppName().get(i);
//       try
//       {
//         LogUtil.lockApp(getSession(), getRequest(), currentUserName, appCode, appName, "LOCK_APP");
//       }
//       catch (Exception ex) {
//         this.log.error(ex.getMessage());
//       }
//     }
        try {
            String strAppId = "";
            ApplicationsForm appForm;
            for (int i = 0; i < this.applicationFormOnGrid.size(); i++) {
                appForm = this.applicationFormOnGrid.get(i);
                //Long appId = Long.valueOf(Long.parseLong((String) this.gridApplicationForm.getAppId().get(i)));
                strAppId += appForm.getAppId().toString() + ",";
            }
            if (strAppId.length() > 0) {
                strAppId = strAppId.substring(0, strAppId.length() - 1);
                lock(strAppId, lockComment);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        try {
            GridResult gridResult = getListApp(this.applicationsForm);
            this.jsonDataGrid.setItems(gridResult.getLstResult());
            this.jsonDataGrid.setTotalRows(gridResult.getnCount().intValue());

        } catch (Exception ex) {
            log.error(ex.getMessage());
//            this.log.error("not get the list[" + ex.getMessage() + "]");
        }
		customInfo.add("locksucc");
        this.jsonDataGrid.setCustomInfo(customInfo);
        return "gridData";
    }

    public void lock(String strAppId, String lockComment) {
        // 11/11/2014 viethd
        //String hql = "update Applications set status = " + IS_NOT_ACTIVE_CONSTANT + ", lockDescription = '" + lockComment + "' where appId in(" + strAppId + ")";
        String hql = "update Applications set status = ?, lockDescription = ? "
                + "where appId in(?)";
        List params = new ArrayList();
        params.add(IS_NOT_ACTIVE_CONSTANT);
        params.add(lockComment);
        params.add(strAppId);
        try {
            Query query = getSession().createQuery(hql);
            int paramSize = params.size();
            for(int i = 0; i < paramSize; i++){
                query.setParameter(i, params.get(i));
            }
            try {
                query.executeUpdate();
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public String onUnlock() {
        List lst = new ArrayList();
        List customInfo = new ArrayList();

        UserToken vsaUserToken = (UserToken) getRequest().getSession().getAttribute("vsaUserToken");
        String currentUserName = "";
        if (vsaUserToken != null) {
            currentUserName = vsaUserToken.getUserName();
        }

        //Ghi lai event cua user
//        for (int i = 0; i < this.gridApplicationForm.getAppId().size(); i++) {
//            String appCode = (String) this.gridApplicationForm.getAppCode().get(i);
//            String appName = (String) this.gridApplicationForm.getAppName().get(i);
//            try {
//                LogUtil.unLockApp(getSession(), getRequest(), currentUserName, appCode, appName, "UNLOCK_APP");
//            } catch (Exception ex) {
//                this.log.error(ex.getMessage());
//            }
//        }

        try {
            String strAppId = "";
            ApplicationsForm appForm;
            for (int i = 0; i < this.applicationFormOnGrid.size(); i++) {
                appForm = this.applicationFormOnGrid.get(i);
                //Long appId = Long.valueOf(Long.parseLong((String) this.gridApplicationForm.getAppId().get(i)));
                strAppId += appForm.getAppId().toString() + ",";
            }
            if (strAppId.length() > 0) {
                strAppId = strAppId.substring(0, strAppId.length() - 1);
                unLock(strAppId);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        try {
            GridResult gridResult = getListApp(this.applicationsForm);
            this.jsonDataGrid.setItems(gridResult.getLstResult());
            this.jsonDataGrid.setTotalRows(gridResult.getnCount().intValue());
        } catch (Exception ex) {
            log.error(ex.getMessage());
//            this.log.error("not get the list[" + ex.getMessage() + "]");
        }
		customInfo.add("unLocksucc");
        this.jsonDataGrid.setCustomInfo(customInfo);
        return "gridData";
    }

    public void unLock(String strAppId) {
        // 11/11/2014 viethd
        //String hql = "update Applications set status=" + IS_ACTIVE_CONSTANT + " where appId in(" + strAppId + ")";
        String hql = "update Applications set status=" + IS_ACTIVE_CONSTANT + " where appId in(" + strAppId + ")";
        List params = new ArrayList();
        params.add(IS_ACTIVE_CONSTANT);
        params.add(strAppId);
        try {
            Query query = getSession().createQuery(hql);
            int paramSize = params.size();
            for(int i = 0; i < paramSize; i++){
                query.setParameter(i, params.get(i));
            }
            try {
                query.executeUpdate();
                getSession().getTransaction().commit();
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
