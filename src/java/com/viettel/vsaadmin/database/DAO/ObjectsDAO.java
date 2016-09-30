/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.vsaadmin.database.DAO;

import com.viettel.common.util.LogUtil;
import com.viettel.dojoTag.DojoAjaxTreeNode;
import com.viettel.dojoTag.DojoJSON;
import com.viettel.vsaadmin.client.form.GridApplicationForm;
//import com.viettel.vsaadmin.client.form.GridObjectForm;
import com.viettel.vsaadmin.client.form.ObjectsForm;
//import com.viettel.vsaadmin.database.BO.Applications;
import com.viettel.vsaadmin.database.BO.Objects;
import java.util.ArrayList;
import java.util.List;
//import org.apache.commons.logging.Log;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.viettel.voffice.database.DAO.BaseDAO;
import com.viettel.voffice.database.DAO.GridResult;

/**
 *
 * @author GPDN_THANHHH1
 */
public class ObjectsDAO extends BaseDAO {

    private ObjectsForm objectsForm;
    //private GridObjectForm gridObjectForm = new GridObjectForm();
    private GridApplicationForm gridApplicationForm = new GridApplicationForm();
    private List<Objects> gridObjectForm;
//    private String forwardPage = "prepare";
    public DojoJSON jsonDataGrid = new DojoJSON();
    public DojoJSON json = new DojoJSON();
    private List childrenData = new ArrayList();
    private static final Long DELETE_CONSTANT = Long.valueOf(-1L);

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ObjectsDAO.class);

    public List getChildrenData() {
        return childrenData;
    }

    public void setChildrenData(List childrenData) {
        this.childrenData = childrenData;
    }

    public GridApplicationForm getGridApplicationForm() {
        return gridApplicationForm;
    }

    public void setGridApplicationForm(GridApplicationForm gridApplicationForm) {
        this.gridApplicationForm = gridApplicationForm;
    }

    public List<Objects> getGridObjectForm() {
        return gridObjectForm;
    }

    public void setGridObjectForm(List<Objects> gridObjectForm) {
        this.gridObjectForm = gridObjectForm;
    }

    public DojoJSON getJson() {
        return json;
    }

    public void setJson(DojoJSON json) {
        this.json = json;
    }

    public DojoJSON getJsonDataGrid() {
        return jsonDataGrid;
    }

    public void setJsonDataGrid(DojoJSON jsonDataGrid) {
        this.jsonDataGrid = jsonDataGrid;
    }

    public ObjectsForm getObjectsForm() {
        return objectsForm;
    }

    public void setObjectsForm(ObjectsForm objectsForm) {
        this.objectsForm = objectsForm;
    }

    public String prepare() {
        Long appId = Long.valueOf(Long.parseLong(getRequest().getParameter("appId")));

        if (getRequest().getSession().getAttribute("appId") != null) {
            getRequest().getSession().removeAttribute("appId");
        }
        getRequest().getSession().setAttribute("appId", appId);

        getRequest().setAttribute("par", appId);
        String appCode = getRequest().getParameter("appCode");
        if (getRequest().getSession().getAttribute("appCode") != null) {
            getRequest().getSession().removeAttribute("appCode");
        }
        getRequest().getSession().setAttribute("appCode", appCode);

        getRequest().setAttribute("appCode", appCode);
        String appName = getRequest().getParameter("appName");
        getRequest().setAttribute("appName", appName);
        return "preparePage";
    }

    public String refreshObjectDialogPage() {
        Long appId = Long.valueOf(Long.parseLong(getRequest().getParameter("appId")));

        if (getRequest().getSession().getAttribute("appId") != null) {
            getRequest().getSession().removeAttribute("appId");
        }
        getRequest().getSession().setAttribute("appId", appId);

        getRequest().setAttribute("par", appId);
        String appCode = getRequest().getParameter("appCode");
        if (getRequest().getSession().getAttribute("appCode") != null) {
            getRequest().getSession().removeAttribute("appCode");
        }
        getRequest().getSession().setAttribute("appCode", appCode);

        getRequest().setAttribute("appCode", appCode);
        String appName = getRequest().getParameter("appName");
        getRequest().setAttribute("appName", appName);
        return "refreshObjectDialogPage";
    }

    public String getChildrenDataByNode() {
        Long parentItemId = Long.valueOf(Long.parseLong(getRequest().getParameter("parentItemId")));
        Session session = getSession();
        List lst = new ArrayList();
        try {
            Criteria cri = session.createCriteria(Objects.class).add(Restrictions.eq("parentId", parentItemId));
            lst = cri.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        Objects bo;
        DojoAjaxTreeNode node;
        for (int i = 0; i < lst.size(); i++) {
            bo = (Objects) lst.get(i);
            node = new DojoAjaxTreeNode();
            node.setId(bo.getObjectId().toString());
            node.setName(bo.getObjectName());
            try {
                Criteria cri = session.createCriteria(Objects.class).add(Restrictions.eq("parentId", bo.getObjectId()));
                if (cri.list().size() > 0) {
                    node.setMayHaveChildren("true");
                } else {
                    node.setMayHaveChildren("false");
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
            this.childrenData.add(node);

        }

        return "childrenData";
    }

    public String onInit() {
        Long appId = Long.valueOf(Long.parseLong(getRequest().getParameter("appId")));
        Long parentId = Long.valueOf(Long.parseLong(getRequest().getParameter("parentId")));
        if (getRequest().getSession().getAttribute("parentId") != null) {
            getRequest().getSession().removeAttribute("parentId");
        }
        getRequest().getSession().setAttribute("parentId", Long.valueOf(0L));
        try {
            GridResult grid = getListObjById(appId, parentId);
            this.jsonDataGrid.setItems(grid.getLstResult());
            this.jsonDataGrid.setTotalRows(grid.getnCount().intValue());
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "gridData";
    }

    public GridResult getListObjById(Long appId, Long parentId) throws Exception {
        GridResult result;
        String countHQL = "select count(o) ";
        String searchHQL = "select o";
        String hql = " from Objects o where o.appId = ? and o.status != ?";
        String condition;
        if (parentId == 0L || parentId == null) {
            condition = " and (o.parentId is null or o.parentId = 0 )";
        } else {
            condition = " and o.parentId=" + parentId;
        }
        hql = hql + condition;
        countHQL = countHQL + hql;
        searchHQL = searchHQL + hql;

        try {
            Query searchQuery = getSession().createQuery(searchHQL);
            Query countQuery = getSession().createQuery(countHQL);
            searchQuery.setParameter(0, appId);
            searchQuery.setParameter(1, DELETE_CONSTANT);
            countQuery.setParameter(0, appId);
            countQuery.setParameter(1, DELETE_CONSTANT);
            Long total = (Long) countQuery.uniqueResult();
            searchQuery.setFirstResult(start);
            searchQuery.setMaxResults(count);
            List lst = searchQuery.list();
            result = new GridResult(total, lst);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            throw ex;
        }
        return result;
    }

    public String getData() {
        try {
            List temp = new ArrayList();
            Session session;
            Long appId = Long.valueOf(Long.parseLong(getRequest().getParameter("appId")));
            session = getSession();
            Criteria cri = session.createCriteria(Objects.class).add(Restrictions.isNull("parentId")).add(Restrictions.eq("appId", appId));
            List lst = cri.list();
            Objects bo;
            DojoAjaxTreeNode node;
            for (int i = 0; i < lst.size(); i++) {
                bo = (Objects) lst.get(i);
                node = new DojoAjaxTreeNode();
                node.setId(bo.getObjectId().toString());
                node.setName(bo.getObjectName());
                List temp1 = new ArrayList();
                node.setMayHaveChildren("true");
                node.setChildren(temp1);
                temp.add(node);

            }

            this.json.setIdentifier("id");
            this.json.setLabel("name");
            this.json.setItems(temp);
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "treeData";
    }

    public String onGetChildrenObj() {

        getGridInfo();

//        List lst = new ArrayList();
        Long appId = (Long) getRequest().getSession().getAttribute("appId");
        Long parentId = Long.valueOf(Long.parseLong(getRequest().getParameter("parentId")));
        if (getRequest().getSession().getAttribute("parentId") != null) {
            getRequest().getSession().removeAttribute("parentId");
        }
        getRequest().getSession().setAttribute("parentId", parentId);
        try {
            GridResult result = getListObjById(appId, parentId);
            this.jsonDataGrid.setItems(result.getLstResult());
            this.jsonDataGrid.setTotalRows(result.getnCount().intValue());
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return "gridData";
    }

    public String onApply() {
        List customInfo = new ArrayList();
        try {
            try {
                Objects bo = createBO();
                Long parent_id = Long.valueOf(bo.getParentId() != null ? bo.getParentId().longValue() : 0L);
                GridResult result = getListObjById(bo.getAppId(), parent_id);
                this.jsonDataGrid.setItems(result.getLstResult());
                this.jsonDataGrid.setTotalRows(result.getnCount().intValue());
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        this.jsonDataGrid.setCustomInfo(customInfo);
        return "gridData";
    }

    public String onDelete() {
        //140123 binhnt update sqlInjection
        String result = "";
        List lst = new ArrayList();
        List customInfo = new ArrayList();
        try {
            String idsStr = getRequest().getParameter("ids");
            String[] arrIds = idsStr.split(",");
            List<Long> lstIds = new ArrayList<Long>();
            if (arrIds.length > 0) {
                for (int i = 0; i < arrIds.length; i++) {
                    try {
                        Long l = Long.parseLong(arrIds[i]);
                        lstIds.add(l);
                    } catch (Exception ex) {
                        LogUtil.addLog(ex);//binhnt sonar a160901
                    }
                }
            }
            String hql = "DELETE FROM Objects o WHERE o.objectId in (:lstIds)";
            Query query = getSession().createQuery(hql);
            query.setParameterList("lstIds", lstIds);
            query.executeUpdate();
            getSession().getTransaction().commit();
            result = "success";
        } catch (org.hibernate.exception.ConstraintViolationException ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            result = "Bản ghi đang được sử dụng";
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            result = "error";
        }
        customInfo.add("delete");
        customInfo.add(result);

        this.jsonDataGrid.setCustomInfo(customInfo);
        this.jsonDataGrid.setItems(lst);
        return "gridData";
    }

    public String onUnlock() {
//        boolean yet = true;
//        List lst = new ArrayList();
        List customInfo = new ArrayList();
        Session sess = getSession();
        try {
            String ids = getRequest().getParameter("ids");
            String[] strIds = ids.split(",");
            if (strIds != null && strIds.length > 0) {
                for (int i = 0; i < strIds.length; i++) {
                    Long id = Long.parseLong(strIds[i]);
                    unlockRecursive(id, sess);
                }
            }
            sess.getTransaction().commit();
            customInfo.add("unlock");
            customInfo.add("success");
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            customInfo.add("unlock");
            customInfo.add("error");

        }
        this.jsonDataGrid.setCustomInfo(customInfo);
        return "gridData";
    }

    public String onLock() {
//        String result = "";
//        List lst = new ArrayList();
//        List customInfo = new ArrayList();
//        try {
//            String hql = "UPDATE Objects o SET o.status = 0 WHERE o.objectId in ";
//            String ids = getRequest().getParameter("ids");
//            if (ids == null || ids.trim().length() == 0) {
//            } else {
//                ids = "(" + ids + ")";
//            }
//            hql = hql + ids;
//            Query query = getSession().createQuery(hql);
//            query.executeUpdate();
//            getSession().getTransaction().commit();
//            result = "success";
//        } catch (Exception en) {
//            System.out.println(en.getMessage());
//            result = "error";
//        }
//        customInfo.add("delete");
//        customInfo.add(result);
//
//        this.jsonDataGrid.setCustomInfo(customInfo);
//        this.jsonDataGrid.setItems(lst);
//        return "gridData";

        //binhnt update sqlInjection
        String result = "";
        List lst = new ArrayList();
        List customInfo = new ArrayList();
        try {
            String idsStr = getRequest().getParameter("ids");
            String[] arrIds = idsStr.split(",");
            List<Long> lstIds = new ArrayList<Long>();
            if (arrIds.length > 0) {
                for (int i = 0; i < arrIds.length; i++) {
                    try {
                        Long l = Long.parseLong(arrIds[i]);
                        lstIds.add(l);
                    } catch (Exception ex) {
                        LogUtil.addLog(ex);//binhnt sonar a160901
                    }
                }
            }
            String hql = "UPDATE Objects o SET o.status = 0 WHERE o.objectId in (:lstIds)";
            Query query = getSession().createQuery(hql);
            query.setParameterList("lstIds", lstIds);
            query.executeUpdate();
            getSession().getTransaction().commit();
            result = "success";
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            result = "error";
        }
        customInfo.add("delete");
        customInfo.add(result);

        this.jsonDataGrid.setCustomInfo(customInfo);
        this.jsonDataGrid.setItems(lst);
        return "gridData";
        //!binhnt53
    }

    public void unlockRecursive(Long objectId, Session sess) {
        try {
            Objects bo = (Objects) sess.get(Objects.class, objectId);
            if (bo.getStatus() == 0L) {
                bo.setStatus(Long.valueOf(1L));
                try {
                    sess.saveOrUpdate(bo);
//                    String hql = "SELECT a FROM Objects a WHERE a.parentId = "+bo.getObjectId();
//                    Query query = sess.createQuery(hql);
//                    List<Objects> lst = query.list();
//
//                    if(lst == null || lst.size() == 0){
//
//                    } else {
//                        for(Objects item : lst){
//                            if(item == null || item.getObjectId() == null || item.getObjectId() <=0)
//                                continue;
//                            unlockRecursive(item.getObjectId(),sess);
//                        }
//                    }
                    if (bo.getParentId() != null && bo.getParentId() > 0) {
                        unlockRecursive(bo.getParentId(), sess);
                    }
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
                }
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
    }

    public void lockRecursive(Long objectId, Session sess) {
        try {
            Objects bo = (Objects) sess.get(Objects.class, objectId);
            bo.setStatus(Long.valueOf(0L));
            try {
                sess.saveOrUpdate(bo);
                try {
                    sess.getTransaction().commit();
                } catch (Exception ex) {
                    LogUtil.addLog(ex);//binhnt sonar a160901
                }
                if (bo.getParentId() != null) {
                    unlockRecursive(bo.getParentId(), sess);
                }
            } catch (Exception ex) {
                LogUtil.addLog(ex);//binhnt sonar a160901
            }
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
    }

    private Objects createBO() throws HibernateException {
        if (this.getObjectsForm() == null) {
            return null;
        }
        Objects bo;
        List customInfo = new ArrayList();
        try {
            boolean bUpdate = false;
            if (this.getObjectsForm().getObjectId() != null && this.getObjectsForm().getObjectId() > 0) {
                bo = (Objects) getSession().get(Objects.class, this.objectsForm.getObjectId());
                customInfo.add("update");
                bUpdate = true;
            } else {
                bo = new Objects();
                bo.setObjectId(getSequence("OBJECT_SEQ"));
                customInfo.add("insert");
            }
            bo.setObjectCode(this.objectsForm.getObjectCode());
            bo.setAppId(this.objectsForm.getAppId());
            bo.setDescription(this.objectsForm.getDescription());
            bo.setObjectName(this.objectsForm.getObjectName());
            bo.setObjectTypeId(this.objectsForm.getObjectType());
            bo.setObjectUrl(this.objectsForm.getObjectUrl());
            bo.setOrd(this.objectsForm.getOrd());
            bo.setParentId(this.objectsForm.getParentId());
            bo.setStatus(this.objectsForm.getStatus());
            if (bUpdate) {
                getSession().update(bo);
            } else {
                getSession().save(bo);
            }
            getSession().getTransaction().commit();
            customInfo.add("success");
            return bo;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            customInfo.add("error");
            return null;
        }
    }
}
