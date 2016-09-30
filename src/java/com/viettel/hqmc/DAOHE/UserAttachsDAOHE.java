/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.DAOHE;

import com.viettel.common.util.StringUtils;
import com.viettel.hqmc.BO.UserAttachs;
import com.viettel.hqmc.FORM.UserAttachsForm;
import com.viettel.voffice.database.DAO.GridResult;
import com.viettel.voffice.database.DAOHibernate.GenericDAOHibernate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import com.viettel.common.util.LogUtil;

/**
 *
 * @author binhnt53
 */
public class UserAttachsDAOHE extends GenericDAOHibernate<UserAttachs, Long> {

    private static Logger log = Logger.getLogger(UserAttachs.class);
    private List keyList = new ArrayList();
    private List valueList = new ArrayList();
    private static HashMap<String, List> lstFactory = new HashMap();
    private List lstStandard;
//========================================================================

    /**
     *
     * @param UserId
     * @return
     */
    public List findAllUserAttach(Long UserId) {

        try {

            StringBuilder stringBuilder = new StringBuilder(" from UserAttachs u ");
            //stringBuilder.append("where u.isActive = 1 and u.status=1 and (u.effectiveDate <= sysdate) and (u.expireDate >= sysdate) order by t.createDate asc");
            stringBuilder.append("where u.isActive=1 and u.status=1 and u.createdBy = ? order by u.createDate asc");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, UserId);
            lstStandard = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<>();
        }
        return lstStandard;
    }

    public List findAllActiveUserAttach(Long userId) {
        try {

            StringBuilder stringBuilder = new StringBuilder(" from UserAttachs u ");
            //stringBuilder.append("where u.isActive = 1 and u.status=1 and (u.effectiveDate <= sysdate) and (u.expireDate >= sysdate) order by t.createDate asc");
            stringBuilder.append("where u.isActive=1 and u.status=1 and u.createdBy = ? and (u.expireDate is null or u.expireDate >= ?) order by u.createDate asc");
            Query query = getSession().createQuery(stringBuilder.toString());
            query.setParameter(0, userId);
            query.setParameter(1, new Date());
            lstStandard = query.list();
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new ArrayList<>();
        }
        return lstStandard;
    }

    /**
     *
     * @param bo
     * @return
     */
    public UserAttachsForm boToForm(UserAttachs bo) {
        UserAttachsForm frm = new UserAttachsForm(bo);
        return frm;
    }

    /**
     *
     * @param userId
     * @param start
     * @param nRecord
     * @param sortField
     * @return
     */
    public GridResult getLstUserAttach(Long userId, int start, int nRecord, String sortField) {
        List<UserAttachsForm> list = new ArrayList<>();
        List lstParam = new ArrayList();
        try {
            String countHql = "SELECT count(u) ";
            String selectHQL = "SELECT u";
            String hql = " FROM UserAttachs u WHERE (u.isActive=1) and (u.status = 1) and (u.createdBy = ?)";
            hql += " ORDER BY u.createDate ";
            Query query = getSession().createQuery(selectHQL + hql);
            Query countQuery = getSession().createQuery(countHql + hql);
            lstParam.add(userId);
            for (int i = 0; i < lstParam.size(); i++) {
                query.setParameter(i, lstParam.get(i));
                countQuery.setParameter(i, lstParam.get(i));
            }
            Long nCount = (Long) countQuery.uniqueResult();
            query.setFirstResult(start);
            query.setMaxResults(nRecord);
            List<UserAttachs> lst = query.list();
            for (UserAttachs bo : lst) {
                list.add(boToForm(bo));
            }
            GridResult result = new GridResult(nCount, list);
            return result;
        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
            return new GridResult(0, null);
        }
    }

    /**
     *
     * @param form
     * @param userId
     * @param start
     * @param count
     * @param sortField
     * @return
     */
    public GridResult findUserAttachs(UserAttachsForm form, Long userId, int start, int count, String sortField) {
        String hql = " from UserAttachs u where u.isActive=1 and u.createdBy = ?";
        List lstParam = new ArrayList();
        lstParam.add(userId);
        if (form != null) {
            if (form.getAttachName() != null && form.getAttachName().trim().length() > 0) {
                hql += " and lower(u.attachName) like ? ESCAPE '/' ";
                lstParam.add(StringUtils.toLikeString(form.getAttachName().trim()));
            }
//            if (form.getObjectType() != null && form.getObjectType().trim().length() > 0) {
//                hql += " and lower(u.OBJECT_TYPE) like ? ESCAPE '/' ";
//                lstParam.add(StringUtils.toLikeString(form.getObjectType().trim()));
//            }
//            if (form.getPublishDateFrom() != null) {
//                hql += " and u.publishDate >= ?";
//                lstParam.add(form.getPublishDateFrom());
//            }
//            if (form.getPublishDateTo() != null) {
//                hql += " and u.publishDate <= ?";
//                lstParam.add(form.getPublishDateTo());
//            }
            if (form.getEffectiveDateFrom() != null) {
                hql += " and u.effectiveDate >= ?";
                lstParam.add(form.getEffectiveDateFrom());
            }
            if (form.getEffectiveDateTo() != null) {
                hql += " and u.effectiveDate <= ?";
                lstParam.add(form.getEffectiveDateTo());
            }
            if (form.getExpireDateFrom() != null) {
                hql += " and u.expireDate >= ?";
                lstParam.add(form.getExpireDateFrom());
            }
            if (form.getExpireDateTo() != null) {
                hql += " and u.expireDate <= ?";
                lstParam.add(form.getExpireDateTo());
            }
            if (form.getStatus() != -1L) {
                hql += " and u.status = ?";
                lstParam.add(form.getStatus());
            }
        }

        Query countQuery = getSession().createQuery("select count(*) " + hql);
        Query query = getSession().createQuery("select u " + hql + " order by u.userAttachsId desc");
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
            countQuery.setParameter(i, lstParam.get(i));
        }

        query.setFirstResult(start);
        query.setMaxResults(count);
        int total = Integer.parseInt(countQuery.uniqueResult().toString());
        List lstResult = query.list();
        GridResult gr = new GridResult(total, lstResult);
        return gr;
    }

    //kiem tra doi tuong co trung khong   
    /**
     *
     * @param form
     * @param userId
     * @return
     */
    public boolean isDuplicate(UserAttachsForm form, Long userId) {
        if (form == null) {
            return false;
        }
        List lstParam = new ArrayList();
        String hql = "select count(u) from UserAttachs u where (u.isActive=1) and (u.status = 1) and (u.createdBy = ?)";
        lstParam.add(userId);
        if (form.getUserAttachsId() != null && form.getUserAttachsId() > 0l) {
            hql += " and u.userAttachsId <> ? ";
            lstParam.add(form.getUserAttachsId());
        }
        if (form.getAttachName() != null && form.getAttachName().trim().length() > 0) {
            hql += " and lower(u.attachName) = ?";
            lstParam.add(form.getAttachName().toLowerCase());
        }
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }

        Long count = Long.parseLong(query.uniqueResult().toString());
        boolean bReturn = false;
        if (count >= 1l) {
            bReturn = true;
        }
        return bReturn;
    }

    /**
     *
     * @param userAttachsId
     * @return
     */
    public List<UserAttachs> getAttachsByObject(Long userAttachsId) {
        List<UserAttachs> voAttachsList = new ArrayList<UserAttachs>();
        try {
            StringBuilder strBuf = new StringBuilder("from UserAttachs u where (u.isActive=1) and (u.status = 1)");
            strBuf.append(" and u.userAttachsId = ?");
            Query query = getSession().createQuery("SELECT u " + strBuf.toString());
//            query.setParameter(0, Constants.Status.ACTIVE);
            if (userAttachsId != null) {
                query.setParameter(0, userAttachsId);
            } else {
                query.setParameter(0, -1L);
            }
            voAttachsList = query.list();

        } catch (Exception ex) {
            LogUtil.addLog(ex);//binhnt sonar a160901
        }
        return voAttachsList;
    }

    /**
     *
     * @param fileName
     * @param userId
     * @return
     */
    public int isDuplicateFileName(String fileName, Long userId) {
        List lstParam = new ArrayList();
        String hql = "select count(u) from UserAttachs u where u.isActive=1 and u.status = 1 and u.userAttachsId = ?";
        lstParam.add(userId);
        if (fileName != null && fileName.trim().length() > 0) {
            hql += " and lower(u.fileName) = ?";
            lstParam.add(fileName.toLowerCase());
        }
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < lstParam.size(); i++) {
            query.setParameter(i, lstParam.get(i));
        }
        int count = Integer.parseInt(query.uniqueResult().toString());
        return count;
    }
//==============================================================================

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        UserAttachsDAOHE.log = log;
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
        UserAttachsDAOHE.lstFactory = lstFactory;
    }

    public UserAttachsDAOHE() {
        super(UserAttachs.class);
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
