/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

//import com.viettel.hqmc.BO.Files;
import com.viettel.common.util.Constants;
import com.viettel.hqmc.BO.RequestComment;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author GPCP_BINHNT53
 */
public class RequestCommentDAOHE extends GenericDAOHibernate<RequestComment, Long> {

    private static Logger log = Logger.getLogger(RequestComment.class);
    private List keyList = new ArrayList();
    private List valueList = new ArrayList();
    private static HashMap<String, List> lstFactory = new HashMap();
    private List lstStandard;

    /**
     * findLastRequestComment
     *
     * @param objId
     * @param isLastChange
     * @return
     */
    public RequestComment findLastRequestComment(Long objId, Long isLastChange) {//140726
        RequestComment bo = new RequestComment();
        try {
            List lstParam = new ArrayList();
            String hql = "select t from RequestComment t where 1=1";
            if (objId != null && objId > 0L) {
                hql += " and t.objectId = ?";
                lstParam.add(objId);
            }
            if (isLastChange != null && isLastChange > 0L) {
                hql += " and t.isLastChange = ?";
                lstParam.add(isLastChange);
            }
            hql += " ORDER BY t.requestComment DESC";
            Query query = getSession().createQuery(hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
            }
            List<RequestComment> lstObj;
            lstObj = query.list();
            if (!lstObj.isEmpty()) {
                return lstObj.get(0);
            }
        } catch (HibernateException e) {
            log.error(e);
        }
        return null;
    }
    //==============================================================================

    public List<RequestComment> findLstLastRequestComment(Long objId, Long isLastChange) {//140726
        try {
            List lstParam = new ArrayList();
            String hql = "select t from RequestComment t where 1=1";
            if (objId != null && objId > 0L) {
                hql += " and t.objectId = ?";
                lstParam.add(objId);
            }
            if (isLastChange != null && isLastChange > 0L) {
                hql += " and t.isLastChange = ?";
                lstParam.add(isLastChange);
            }
            hql += " ORDER BY t.requestComment DESC";
            Query query = getSession().createQuery(hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
            }
            List<RequestComment> lstObj;
            lstObj = query.list();
            if (!lstObj.isEmpty()) {
                return lstObj;
            }
        } catch (HibernateException e) {
            log.error(e);
        }
        return null;
    }

    public GridResult findLstRequestComment(Long objectId, int start, int count, String sortField) {//140726
        List<RequestComment> lst = new ArrayList();
        List lstParam = new ArrayList();
        try {
            String countHql = "SELECT count(pc) ";
            String selectHQL = "SELECT pc";
            String hql = " FROM RequestComment pc"
                    + " WHERE 1=1"
                    + " and  pc.objectId =?";
            lstParam.add(objectId);
            hql += " ORDER BY pc.version DESC, pc.createDate DESC";

            Query query = getSession().createQuery(selectHQL + hql);
            Query countQuery = getSession().createQuery(countHql + hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }
            query.setFirstResult(start);
            query.setMaxResults(count);
            Long nCount = (Long) countQuery.uniqueResult();
            lst = query.list();
            GridResult result = new GridResult(nCount, lst);
            return result;
        } catch (Exception e) {
            log.error(e);
            return new GridResult(0, null);
        }
    }

    public boolean updateLstRequestComment(List<RequestComment> lstObj) {
        boolean check = true;
        if (!lstObj.isEmpty()) {
            for (RequestComment requestComment : lstObj) {
                requestComment.setIsLastChange(Constants.ACTIVE_STATUS.DEACTIVE);
                getSession().update(requestComment);
            }
        }
        return check;
    }

    //hieptq update 070515
      public RequestComment findLeaderComment(Long objId, Long isLastChange) {
        RequestComment bo = new RequestComment();
        try {
            List lstParam = new ArrayList();
            String hql = "select t from RequestComment t where 1=1";
            if (objId != null && objId > 0L) {
                hql += " and t.objectId = ?";
                lstParam.add(objId);
            }
            if (isLastChange != null && isLastChange > 0L) {
                hql += " and t.isLastChange = ?";
                lstParam.add(isLastChange);
            }
            hql += " and t.requestType = 'TPLN' and t.isActive = 1 ORDER BY t.requestComment DESC";
            Query query = getSession().createQuery(hql);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
            }
            List<RequestComment> lstObj;
            lstObj = query.list();
            if (!lstObj.isEmpty()) {
                return lstObj.get(0);
            }
        } catch (HibernateException e) {
            log.error(e);
        }
        return null;
    }
    
    
    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        RequestCommentDAOHE.log = log;
    }

    public List getKeyList() {
        return keyList;
    }

    public void setKeyList(List keyList) {
        this.keyList = keyList;
    }

    public List getValueList() {
        return valueList;
    }

    public void setValueList(List valueList) {
        this.valueList = valueList;
    }

    public static HashMap<String, List> getLstFactory() {
        return lstFactory;
    }

    public static void setLstFactory(HashMap<String, List> lstFactory) {
        RequestCommentDAOHE.lstFactory = lstFactory;
    }

    public RequestCommentDAOHE() {
        super(RequestComment.class);
    }

    public static void removeCache(String type) {
        if (lstFactory == null) {
            return;
        }
        if (lstFactory.containsKey(type)) {
            lstFactory.remove(type);
        }
    }
}
